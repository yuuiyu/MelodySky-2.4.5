//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.transformers.*;
import org.spongepowered.asm.mixin.transformer.debug.*;
import org.apache.logging.log4j.*;
import org.apache.commons.io.*;
import java.io.*;
import java.lang.reflect.*;
import net.minecraft.launchwrapper.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.lib.tree.*;
import java.text.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;
import org.spongepowered.asm.mixin.throwables.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.extensibility.*;
import java.util.regex.*;

public class MixinTransformer extends TreeTransformer
{
    static final File DEBUG_OUTPUT;
    private final Logger logger;
    private final List<MixinConfig> configs;
    private final List<MixinConfig> pendingConfigs;
    private final List<IMixinTransformerModule> modules;
    private MixinEnvironment currentEnvironment;
    private final ReEntranceState lock;
    private final String sessionId;
    private Level verboseLoggingLevel;
    private boolean errorState;
    private final File classExportDir;
    private final IDecompiler decompiler;
    private final IHotSwap hotSwapper;
    
    MixinTransformer() {
        this.logger = LogManager.getLogger("mixin");
        this.configs = new ArrayList<MixinConfig>();
        this.pendingConfigs = new ArrayList<MixinConfig>();
        this.modules = new ArrayList<IMixinTransformerModule>();
        this.lock = new ReEntranceState(1);
        this.sessionId = UUID.randomUUID().toString();
        this.verboseLoggingLevel = Level.DEBUG;
        this.errorState = false;
        this.classExportDir = new File(MixinTransformer.DEBUG_OUTPUT, "class");
        final MixinEnvironment environment = MixinEnvironment.getCurrentEnvironment();
        final Object globalMixinTransformer = environment.getActiveTransformer();
        if (globalMixinTransformer instanceof IClassTransformer) {
            throw new MixinException("Terminating MixinTransformer instance " + this);
        }
        environment.setActiveTransformer((IClassTransformer)this);
        TreeInfo.setLock(this.lock);
        this.decompiler = this.initDecompiler(new File(MixinTransformer.DEBUG_OUTPUT, "java"));
        this.hotSwapper = this.initHotSwapper();
        try {
            FileUtils.deleteDirectory(this.classExportDir);
        }
        catch (IOException ex) {
            this.logger.warn("Error cleaning class output directory: {}", new Object[] { ex.getMessage() });
        }
    }
    
    private IDecompiler initDecompiler(final File outputPath) {
        final MixinEnvironment env = MixinEnvironment.getCurrentEnvironment();
        if (!env.getOption(MixinEnvironment.Option.DEBUG_EXPORT_DECOMPILE)) {
            return null;
        }
        try {
            final boolean as = env.getOption(MixinEnvironment.Option.DEBUG_EXPORT_DECOMPILE_THREADED);
            this.logger.info("Attempting to load Fernflower decompiler{}", new Object[] { as ? " (Threaded mode)" : "" });
            final Class<? extends IDecompiler> clazz = (Class<? extends IDecompiler>)Class.forName("org.spongepowered.asm.mixin.transformer.debug.RuntimeDecompiler" + (as ? "Async" : ""));
            final Constructor<? extends IDecompiler> ctor = clazz.getDeclaredConstructor(File.class);
            final IDecompiler decompiler = (IDecompiler)ctor.newInstance(outputPath);
            this.logger.info("Fernflower decompiler was successfully initialised, exported classes will be decompiled{}", new Object[] { as ? " in a separate thread" : "" });
            return decompiler;
        }
        catch (Throwable th) {
            this.logger.info("Fernflower could not be loaded, exported classes will not be decompiled. {}: {}", new Object[] { th.getClass().getSimpleName(), th.getMessage() });
            return null;
        }
    }
    
    private IHotSwap initHotSwapper() {
        if (!MixinEnvironment.getCurrentEnvironment().getOption(MixinEnvironment.Option.HOT_SWAP)) {
            return null;
        }
        try {
            this.logger.info("Attempting to load Hot-Swap agent");
            final Class<? extends IHotSwap> clazz = (Class<? extends IHotSwap>)Class.forName("org.spongepowered.tools.agent.MixinAgent");
            final Constructor<? extends IHotSwap> ctor = clazz.getDeclaredConstructor(MixinTransformer.class);
            return (IHotSwap)ctor.newInstance(this);
        }
        catch (Throwable th) {
            this.logger.info("Hot-swap agent could not be loaded, hot swapping of mixins won't work. {}: {}", new Object[] { th.getClass().getSimpleName(), th.getMessage() });
            return null;
        }
    }
    
    public void audit() {
        final Set<String> unhandled = new HashSet<String>();
        for (final MixinConfig config : this.configs) {
            unhandled.addAll(config.getUnhandledTargets());
        }
        final Logger auditLogger = LogManager.getLogger("mixin/audit");
        for (final String target : unhandled) {
            try {
                auditLogger.info("Force-loading class {}", new Object[] { target });
                Class.forName(target, true, (ClassLoader)Launch.classLoader);
            }
            catch (ClassNotFoundException ex) {
                auditLogger.error("Could not force-load " + target, (Throwable)ex);
            }
        }
        for (final MixinConfig config2 : this.configs) {
            for (final String target2 : config2.getUnhandledTargets()) {
                final ClassAlreadyLoadedException ex2 = new ClassAlreadyLoadedException(target2 + " was already classloaded");
                auditLogger.error("Could not force-load " + target2, (Throwable)ex2);
            }
        }
    }
    
    public synchronized byte[] transform(final String name, final String transformedName, byte[] basicClass) {
        if (basicClass == null || transformedName == null || this.errorState) {
            return basicClass;
        }
        final boolean locked = this.lock.push().check();
        final MixinEnvironment environment = MixinEnvironment.getCurrentEnvironment();
        if (this.currentEnvironment != environment && !locked) {
            try {
                this.select(environment);
            }
            catch (Exception ex) {
                this.lock.pop();
                throw new MixinException((Throwable)ex);
            }
        }
        try {
            SortedSet<MixinInfo> mixins = null;
            boolean invalidRef = false;
            for (final MixinConfig config : this.configs) {
                if (config.packageMatch(transformedName)) {
                    if (config.canPassThrough(transformedName)) {
                        return this.passThrough(config, name, transformedName, basicClass);
                    }
                    invalidRef = true;
                }
                else {
                    if (!config.hasMixinsFor(transformedName)) {
                        continue;
                    }
                    if (mixins == null) {
                        mixins = new TreeSet<MixinInfo>();
                    }
                    mixins.addAll((Collection<?>)config.getMixinsFor(transformedName));
                }
            }
            if (invalidRef) {
                throw new NoClassDefFoundError(String.format("%s is a mixin class and cannot be referenced directly", transformedName));
            }
            if (mixins != null) {
                if (locked) {
                    this.logger.warn("Re-entrance detected, this will cause serious problems.", (Throwable)new MixinException());
                    throw new MixinApplyError("Re-entrance error.");
                }
                if (this.hotSwapper != null) {
                    this.hotSwapper.registerTargetClass(transformedName, basicClass);
                }
                try {
                    final ClassNode targetClassNode = this.readClass(basicClass, true);
                    final TargetClassContext context = new TargetClassContext(this.sessionId, transformedName, targetClassNode, mixins);
                    basicClass = this.applyMixins(context);
                }
                catch (InvalidMixinException th) {
                    this.dumpClassOnFailure(transformedName, basicClass, environment);
                    this.handleMixinApplyError(transformedName, th, environment);
                }
            }
            return basicClass;
        }
        catch (Throwable th2) {
            th2.printStackTrace();
            this.dumpClassOnFailure(transformedName, basicClass, environment);
            throw new MixinTransformerError("An unexpected critical error was encountered", th2);
        }
        finally {
            this.lock.pop();
        }
    }
    
    public List<String> reload(final String mixinClass, final byte[] bytes) {
        if (this.lock.getDepth() > 0) {
            throw new MixinApplyError("Cannot reload mixin if re-entrant lock entered");
        }
        final List<String> targets = new ArrayList<String>();
        for (final MixinConfig config : this.configs) {
            targets.addAll(config.reloadMixin(mixinClass, bytes));
        }
        return targets;
    }
    
    private void select(final MixinEnvironment environment) {
        this.verboseLoggingLevel = (environment.getOption(MixinEnvironment.Option.DEBUG_VERBOSE) ? Level.INFO : Level.DEBUG);
        this.logger.log(this.verboseLoggingLevel, "Preparing mixins for {}", new Object[] { environment });
        final long startTime = System.currentTimeMillis();
        this.selectConfigs(environment);
        this.selectModules(environment);
        final int totalMixins = this.prepareConfigs(environment);
        this.currentEnvironment = environment;
        final double elapsedTime = (System.currentTimeMillis() - startTime) * 0.001;
        if (elapsedTime > 0.25) {
            final String elapsed = new DecimalFormat("###0.000").format(elapsedTime);
            final String perMixinTime = new DecimalFormat("###0.0").format(elapsedTime / totalMixins * 1000.0);
            this.logger.log(this.verboseLoggingLevel, "Prepared {} mixins in {} sec ({} msec avg.)", new Object[] { totalMixins, elapsed, perMixinTime });
        }
    }
    
    private void selectConfigs(final MixinEnvironment environment) {
        final Iterator<Config> iter = Mixins.getConfigs().iterator();
        while (iter.hasNext()) {
            final Config handle = iter.next();
            try {
                final MixinConfig config = handle.get();
                if (!config.select(environment)) {
                    continue;
                }
                iter.remove();
                this.logger.log(this.verboseLoggingLevel, "Selecting config {}", new Object[] { config });
                config.onSelect();
                this.pendingConfigs.add(config);
            }
            catch (Exception ex) {
                this.logger.warn(String.format("Failed to select mixin config: %s", handle), (Throwable)ex);
            }
        }
        Collections.sort(this.pendingConfigs);
    }
    
    private void selectModules(final MixinEnvironment environment) {
        this.modules.clear();
        if (environment.getOption(MixinEnvironment.Option.DEBUG_VERIFY)) {
            this.modules.add((IMixinTransformerModule)new MixinTransformerModuleCheckClass());
        }
        if (environment.getOption(MixinEnvironment.Option.CHECK_IMPLEMENTS)) {
            this.modules.add((IMixinTransformerModule)new MixinTransformerModuleInterfaceChecker());
        }
    }
    
    private int prepareConfigs(final MixinEnvironment environment) {
        int totalMixins = 0;
        for (final MixinConfig config : this.pendingConfigs) {
            try {
                this.logger.log(this.verboseLoggingLevel, "Preparing {} ({})", new Object[] { config, config.getDeclaredMixinCount() });
                config.prepare(this.hotSwapper);
                totalMixins += config.getMixinCount();
            }
            catch (InvalidMixinException ex) {
                this.handleMixinPrepareError(config, ex, environment);
            }
            catch (Exception ex2) {
                this.logger.error("Error encountered whilst initialising mixin config '" + config.getName() + "': " + ex2.getMessage(), (Throwable)ex2);
            }
        }
        for (final MixinConfig config : this.pendingConfigs) {
            final IMixinConfigPlugin plugin = config.getPlugin();
            if (plugin == null) {
                continue;
            }
            final Set<String> otherTargets = new HashSet<String>();
            for (final MixinConfig otherConfig : this.pendingConfigs) {
                if (!otherConfig.equals(config)) {
                    otherTargets.addAll(otherConfig.getTargets());
                }
            }
            plugin.acceptTargets(config.getTargets(), (Set)Collections.unmodifiableSet((Set<?>)otherTargets));
        }
        for (final MixinConfig config : this.pendingConfigs) {
            try {
                config.postInitialise(this.hotSwapper);
            }
            catch (InvalidMixinException ex) {
                this.handleMixinPrepareError(config, ex, environment);
            }
            catch (Exception ex2) {
                this.logger.error("Error encountered during mixin config postInit step'" + config.getName() + "': " + ex2.getMessage(), (Throwable)ex2);
            }
        }
        this.configs.addAll(this.pendingConfigs);
        Collections.sort(this.configs);
        this.pendingConfigs.clear();
        return totalMixins;
    }
    
    private byte[] passThrough(final MixinConfig config, final String name, final String transformedName, final byte[] basicClass) {
        final ClassNode classNode = config.passThrough(transformedName, this.readClass(basicClass, true));
        return this.writeClass(transformedName, classNode, false);
    }
    
    private byte[] applyMixins(final TargetClassContext context) {
        this.preApply(context);
        this.apply(context);
        try {
            this.postApply(context);
        }
        catch (MixinTransformerModuleCheckClass.ValidationFailedException ex) {
            this.logger.info(ex.getMessage());
            if (context.isExportForced() || MixinEnvironment.getCurrentEnvironment().getOption(MixinEnvironment.Option.DEBUG_EXPORT)) {
                this.writeClass(context);
            }
        }
        return this.writeClass(context);
    }
    
    private void preApply(final TargetClassContext context) {
        for (final IMixinTransformerModule module : this.modules) {
            module.preApply(context);
        }
    }
    
    private void apply(final TargetClassContext context) {
        context.applyMixins();
    }
    
    private void postApply(final TargetClassContext context) {
        for (final IMixinTransformerModule module : this.modules) {
            module.postApply(context);
        }
    }
    
    private void handleMixinPrepareError(final MixinConfig config, final InvalidMixinException ex, final MixinEnvironment environment) throws MixinPrepareError {
        this.handleMixinError(config.getName(), ex, environment, ErrorPhase.PREPARE);
    }
    
    private void handleMixinApplyError(final String targetClass, final InvalidMixinException ex, final MixinEnvironment environment) throws MixinApplyError {
        this.handleMixinError(targetClass, ex, environment, ErrorPhase.APPLY);
    }
    
    private void handleMixinError(final String context, final InvalidMixinException ex, final MixinEnvironment environment, final ErrorPhase errorPhase) throws Error {
        this.errorState = true;
        final IMixinInfo mixin = ex.getMixin();
        if (mixin == null) {
            this.logger.error("InvalidMixinException has no mixin!", (Throwable)ex);
            throw ex;
        }
        final IMixinConfig config = mixin.getConfig();
        final MixinEnvironment.Phase phase = mixin.getPhase();
        IMixinErrorHandler.ErrorAction action = config.isRequired() ? IMixinErrorHandler.ErrorAction.ERROR : IMixinErrorHandler.ErrorAction.WARN;
        if (environment.getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
            new PrettyPrinter().add("Invalid Mixin").centre().hr('-').kvWidth(10).kv("Action", (Object)errorPhase.name()).kv("Mixin", (Object)mixin.getClassName()).kv("Config", (Object)config.getName()).kv("Phase", phase).hr('-').add("    %s", ex.getClass().getName()).hr('-').addWrapped("    %s", ex.getMessage()).hr('-').add(ex.getStackTrace(), 8).trace(action.logLevel);
        }
        for (final IMixinErrorHandler handler : this.getErrorHandlers(mixin.getPhase())) {
            final IMixinErrorHandler.ErrorAction newAction = errorPhase.onError(handler, context, ex, mixin, action);
            if (newAction != null) {
                action = newAction;
            }
        }
        this.logger.log(action.logLevel, errorPhase.getLogMessage(context, ex, mixin), (Throwable)ex);
        this.errorState = false;
        if (action == IMixinErrorHandler.ErrorAction.ERROR) {
            throw new MixinApplyError(errorPhase.getErrorMessage(mixin, config, phase), (Throwable)ex);
        }
    }
    
    private List<IMixinErrorHandler> getErrorHandlers(final MixinEnvironment.Phase phase) {
        final List<IMixinErrorHandler> handlers = new ArrayList<IMixinErrorHandler>();
        for (final String handlerClassName : Mixins.getErrorHandlerClasses()) {
            try {
                this.logger.info("Instancing error handler class {}", new Object[] { handlerClassName });
                final Class<?> handlerClass = Class.forName(handlerClassName, true, (ClassLoader)Launch.classLoader);
                final IMixinErrorHandler handler = (IMixinErrorHandler)handlerClass.newInstance();
                if (handler == null) {
                    continue;
                }
                handlers.add(handler);
            }
            catch (Throwable t) {}
        }
        return handlers;
    }
    
    private String prepareFilter(String filter) {
        filter = "^\\Q" + filter.replace("**", "\u0081").replace("*", "\u0082").replace("?", "\u0083") + "\\E$";
        return filter.replace("\u0081", "\\E.*\\Q").replace("\u0082", "\\E[^\\.]+\\Q").replace("\u0083", "\\E.\\Q").replace("\\Q\\E", "");
    }
    
    private boolean applyFilter(final String filter, final String subject) {
        return Pattern.compile(this.prepareFilter(filter), 2).matcher(subject).matches();
    }
    
    private byte[] writeClass(final TargetClassContext context) {
        return this.writeClass(context.getClassName(), context.getClassNode(), context.isExportForced());
    }
    
    private byte[] writeClass(final String transformedName, final ClassNode targetClass, final boolean forceExport) {
        final byte[] bytes = this.writeClass(targetClass);
        final MixinEnvironment environment = MixinEnvironment.getCurrentEnvironment();
        if (forceExport || environment.getOption(MixinEnvironment.Option.DEBUG_EXPORT)) {
            final String filter = environment.getOptionValue(MixinEnvironment.Option.DEBUG_EXPORT_FILTER);
            if (forceExport || filter == null || this.applyFilter(filter, transformedName)) {
                final File outputFile = this.dumpClass(transformedName.replace('.', '/'), bytes);
                if (this.decompiler != null) {
                    this.decompiler.decompile(outputFile);
                }
            }
        }
        return bytes;
    }
    
    private void dumpClassOnFailure(final String className, final byte[] bytes, final MixinEnvironment env) {
        if (env.getOption(MixinEnvironment.Option.DUMP_TARGET_ON_FAILURE)) {
            this.dumpClass(className.replace('.', '/') + ".target", bytes);
        }
    }
    
    private File dumpClass(final String fileName, final byte[] bytes) {
        final File outputFile = new File(this.classExportDir, fileName + ".class");
        try {
            FileUtils.writeByteArrayToFile(outputFile, bytes);
        }
        catch (IOException ex) {}
        return outputFile;
    }
    
    static {
        DEBUG_OUTPUT = new File(".mixin.out");
    }
    
    enum ErrorPhase
    {
        PREPARE("PREPARE", 0), 
        APPLY("APPLY", 1);
        
        private final String text;
        
        private ErrorPhase() {
            this.text = this.name().toLowerCase();
        }
        
        abstract IMixinErrorHandler.ErrorAction onError(final IMixinErrorHandler p0, final String p1, final InvalidMixinException p2, final IMixinInfo p3, final IMixinErrorHandler.ErrorAction p4);
        
        protected abstract String getContext(final IMixinInfo p0, final String p1);
        
        public String getLogMessage(final String context, final InvalidMixinException ex, final IMixinInfo mixin) {
            return String.format("Mixin %s failed %s: %s %s", this.text, this.getContext(mixin, context), ex.getClass().getName(), ex.getMessage());
        }
        
        public String getErrorMessage(final IMixinInfo mixin, final IMixinConfig config, final MixinEnvironment.Phase phase) {
            return String.format("Mixin [%s] from phase [%s] in config [%s] FAILED during %s", mixin, phase, config, this.name());
        }
    }
    
    public static class Proxy implements IClassTransformer
    {
        private static List<Proxy> proxies;
        private static MixinTransformer transformer;
        private boolean isActive;
        
        public Proxy() {
            this.isActive = true;
            for (final Proxy hook : Proxy.proxies) {
                hook.isActive = false;
            }
            Proxy.proxies.add(this);
            LogManager.getLogger("mixin").debug("Adding new mixin transformer proxy #{}", new Object[] { Proxy.proxies.size() });
        }
        
        public byte[] transform(final String name, final String transformedName, final byte[] basicClass) {
            if (this.isActive) {
                return Proxy.transformer.transform(name, transformedName, basicClass);
            }
            return basicClass;
        }
        
        static {
            Proxy.proxies = new ArrayList<Proxy>();
            Proxy.transformer = new MixinTransformer();
        }
    }
    
    class ReEntranceState
    {
        private final int maxDepth;
        private int depth;
        private boolean semaphore;
        
        public ReEntranceState(final int maxDepth) {
            this.depth = 0;
            this.semaphore = false;
            this.maxDepth = maxDepth;
        }
        
        public int getMaxDepth() {
            return this.maxDepth;
        }
        
        public int getDepth() {
            return this.depth;
        }
        
        ReEntranceState push() {
            ++this.depth;
            this.checkAndSet();
            return this;
        }
        
        ReEntranceState pop() {
            if (this.depth == 0) {
                throw new IllegalStateException("ReEntranceState pop() with zero depth");
            }
            --this.depth;
            return this;
        }
        
        boolean check() {
            return this.depth > this.maxDepth;
        }
        
        boolean checkAndSet() {
            return this.semaphore |= this.check();
        }
        
        ReEntranceState set() {
            this.semaphore = true;
            return this;
        }
        
        boolean isSet() {
            return this.semaphore;
        }
        
        ReEntranceState clear() {
            this.semaphore = false;
            return this;
        }
    }
}
