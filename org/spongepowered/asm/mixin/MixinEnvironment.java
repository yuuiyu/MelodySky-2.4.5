//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin;

import org.spongepowered.asm.obfuscation.*;
import org.spongepowered.asm.mixin.throwables.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.extensibility.*;
import java.util.*;
import org.spongepowered.asm.mixin.transformer.*;
import javax.annotation.*;
import org.spongepowered.asm.launch.*;
import com.google.common.collect.*;
import java.lang.reflect.*;
import org.apache.logging.log4j.core.helpers.*;
import net.minecraft.launchwrapper.*;
import java.io.*;
import org.apache.logging.log4j.core.appender.*;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.*;

public final class MixinEnvironment implements ITokenProvider
{
    private static final Set<String> excludeTransformers;
    private static MixinEnvironment currentEnvironment;
    private static Phase currentPhase;
    private static CompatibilityLevel compatibility;
    private static boolean showHeader;
    private static final Logger logger;
    private final Phase phase;
    private final String configsKey;
    private final boolean[] options;
    private final Set<String> tokenProviderClasses;
    private final List<TokenProviderWrapper> tokenProviders;
    private final Map<String, Integer> internalTokens;
    private final RemapperChain remappers;
    private Side side;
    private List<IClassTransformer> transformers;
    private IClassNameTransformer nameTransformer;
    private String obfuscationContext;
    
    MixinEnvironment(final Phase phase) {
        this.tokenProviderClasses = new HashSet<String>();
        this.tokenProviders = new ArrayList<TokenProviderWrapper>();
        this.internalTokens = new HashMap<String, Integer>();
        this.remappers = new RemapperChain();
        this.obfuscationContext = null;
        this.phase = phase;
        this.configsKey = "mixin.configs." + this.phase.name.toLowerCase();
        final Object version = this.getVersion();
        if (version == null || !"0.6.5".equals(version)) {
            throw new MixinException("Environment conflict, mismatched versions or you didn't call MixinBootstrap.init()");
        }
        if (this.getClass().getClassLoader() != Launch.class.getClassLoader()) {
            throw new MixinException("Attempted to init the mixin environment in the wrong classloader");
        }
        this.options = new boolean[Option.values().length];
        for (final Option option : Option.values()) {
            this.options[option.ordinal()] = option.getBooleanValue();
        }
        if (MixinEnvironment.showHeader) {
            MixinEnvironment.showHeader = false;
            this.printHeader(version);
        }
    }
    
    private void printHeader(final Object version) {
        final Side side = this.getSide();
        final String codeSource = this.getCodeSource();
        MixinEnvironment.logger.info("SpongePowered MIXIN Subsystem Version={} Source={} Env={}", new Object[] { version, codeSource, side });
        if (this.getOption(Option.DEBUG_VERBOSE)) {
            final PrettyPrinter printer = new PrettyPrinter(32);
            printer.add("SpongePowered MIXIN (Verbose debugging enabled)").centre().hr();
            printer.kv("Code source", (Object)codeSource);
            printer.kv("Internal Version", version);
            printer.kv("Java 8 Supported", CompatibilityLevel.JAVA_8.isSupported()).hr();
            for (final Option option : Option.values()) {
                final StringBuilder indent = new StringBuilder();
                for (int i = 0; i < option.depth; ++i) {
                    indent.append("- ");
                }
                printer.kv(option.property, "%s<%s>", indent, option);
            }
            printer.hr().kv("Detected Side", side);
            printer.print(System.err);
        }
    }
    
    private String getCodeSource() {
        try {
            return this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        }
        catch (Throwable th) {
            return "Unknown";
        }
    }
    
    public Phase getPhase() {
        return this.phase;
    }
    
    @Deprecated
    public List<String> getMixinConfigs() {
        List<String> mixinConfigs = (List<String>)Blackboard.get(this.configsKey);
        if (mixinConfigs == null) {
            mixinConfigs = new ArrayList<String>();
            Launch.blackboard.put(this.configsKey, mixinConfigs);
        }
        return mixinConfigs;
    }
    
    @Deprecated
    public MixinEnvironment addConfiguration(final String config) {
        MixinEnvironment.logger.warn("MixinEnvironment::addConfiguration is deprecated and will be removed. Use Mixins::addConfiguration instead!");
        Mixins.addConfiguration(config, this);
        return this;
    }
    
    void registerConfig(final String config) {
        final List<String> configs = this.getMixinConfigs();
        if (!configs.contains(config)) {
            configs.add(config);
        }
    }
    
    @Deprecated
    public MixinEnvironment registerErrorHandlerClass(final String handlerName) {
        Mixins.registerErrorHandlerClass(handlerName);
        return this;
    }
    
    public MixinEnvironment registerTokenProviderClass(final String providerName) {
        if (!this.tokenProviderClasses.contains(providerName)) {
            try {
                final Class<? extends IEnvironmentTokenProvider> providerClass = (Class<? extends IEnvironmentTokenProvider>)Class.forName(providerName, true, (ClassLoader)Launch.classLoader);
                final IEnvironmentTokenProvider provider = (IEnvironmentTokenProvider)providerClass.newInstance();
                this.registerTokenProvider(provider);
            }
            catch (Throwable th) {
                MixinEnvironment.logger.error("Error instantiating " + providerName, th);
            }
        }
        return this;
    }
    
    public MixinEnvironment registerTokenProvider(final IEnvironmentTokenProvider provider) {
        if (provider != null && !this.tokenProviderClasses.contains(provider.getClass().getName())) {
            final String providerName = provider.getClass().getName();
            final TokenProviderWrapper wrapper = new TokenProviderWrapper(provider, this);
            MixinEnvironment.logger.info("Adding new token provider {} to {}", new Object[] { providerName, this });
            this.tokenProviders.add(wrapper);
            this.tokenProviderClasses.add(providerName);
            Collections.sort(this.tokenProviders);
        }
        return this;
    }
    
    @Override
    public Integer getToken(String token) {
        token = token.toUpperCase();
        for (final TokenProviderWrapper provider : this.tokenProviders) {
            final Integer value = provider.getToken(token);
            if (value != null) {
                return value;
            }
        }
        return this.internalTokens.get(token);
    }
    
    @Deprecated
    public Set<String> getErrorHandlerClasses() {
        return Mixins.getErrorHandlerClasses();
    }
    
    public Object getActiveTransformer() {
        return Blackboard.get("mixin.transformer");
    }
    
    public void setActiveTransformer(final IClassTransformer transformer) {
        if (transformer != null) {
            Blackboard.put("mixin.transformer", (Object)transformer);
        }
    }
    
    public MixinEnvironment setSide(final Side side) {
        if (side != null && this.getSide() == Side.UNKNOWN && side != Side.UNKNOWN) {
            this.side = side;
        }
        return this;
    }
    
    public Side getSide() {
        if (this.side == null) {
            for (final Side side : Side.values()) {
                if (side.detect()) {
                    this.side = side;
                    break;
                }
            }
        }
        return (this.side != null) ? this.side : Side.UNKNOWN;
    }
    
    public String getVersion() {
        return (String)Blackboard.get("mixin.initialised");
    }
    
    public boolean getOption(final Option option) {
        return this.options[option.ordinal()];
    }
    
    public void setOption(final Option option, final boolean value) {
        this.options[option.ordinal()] = value;
    }
    
    public String getOptionValue(final Option option) {
        return option.getStringValue();
    }
    
    public void setObfuscationContext(final String context) {
        this.obfuscationContext = context;
    }
    
    public String getObfuscationContext() {
        return this.obfuscationContext;
    }
    
    public String getRefmapObfuscationContext() {
        final String overrideObfuscationType = Option.OBFUSCATION_TYPE.getStringValue();
        if (overrideObfuscationType != null) {
            return overrideObfuscationType;
        }
        return this.obfuscationContext;
    }
    
    public RemapperChain getRemappers() {
        return this.remappers;
    }
    
    public void audit() {
        final Object activeTransformer = this.getActiveTransformer();
        if (activeTransformer instanceof MixinTransformer) {
            final MixinTransformer transformer = (MixinTransformer)activeTransformer;
            transformer.audit();
        }
    }
    
    public List<IClassTransformer> getTransformers() {
        if (this.transformers == null) {
            this.buildTransformerDelegationList();
        }
        return Collections.unmodifiableList((List<? extends IClassTransformer>)this.transformers);
    }
    
    public void addTransformerExclusion(final String name) {
        MixinEnvironment.excludeTransformers.add(name);
        this.transformers = null;
    }
    
    public String unmap(final String className) {
        if (this.transformers == null) {
            this.buildTransformerDelegationList();
        }
        if (this.nameTransformer != null) {
            return this.nameTransformer.unmapClassName(className);
        }
        return className;
    }
    
    private void buildTransformerDelegationList() {
        MixinEnvironment.logger.debug("Rebuilding transformer delegation list:");
        this.transformers = new ArrayList<IClassTransformer>();
        for (final IClassTransformer transformer : Launch.classLoader.getTransformers()) {
            final String transformerName = transformer.getClass().getName();
            boolean include = true;
            for (final String excludeClass : MixinEnvironment.excludeTransformers) {
                if (transformerName.contains(excludeClass)) {
                    include = false;
                    break;
                }
            }
            final boolean ignoreTransformer = transformer.getClass().getAnnotation(Resource.class) != null;
            if (include && !ignoreTransformer && !transformerName.contains(MixinTransformer.class.getName())) {
                MixinEnvironment.logger.debug("  Adding:    {}", new Object[] { transformerName });
                this.transformers.add(transformer);
            }
            else {
                MixinEnvironment.logger.debug("  Excluding: {}", new Object[] { transformerName });
            }
        }
        MixinEnvironment.logger.debug("Transformer delegation list created with {} entries", new Object[] { this.transformers.size() });
        for (final IClassTransformer transformer : Launch.classLoader.getTransformers()) {
            if (transformer instanceof IClassNameTransformer) {
                MixinEnvironment.logger.debug("Found name transformer: {}", new Object[] { transformer.getClass().getName() });
                this.nameTransformer = (IClassNameTransformer)transformer;
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.phase);
    }
    
    private static Phase getCurrentPhase() {
        if (MixinEnvironment.currentPhase == Phase.NOT_INITIALISED) {
            init(Phase.PREINIT);
        }
        return MixinEnvironment.currentPhase;
    }
    
    public static void init(final Phase phase) {
        if (MixinEnvironment.currentPhase == Phase.NOT_INITIALISED) {
            getEnvironment(MixinEnvironment.currentPhase = phase);
            final MixinLogger logSpy = new MixinLogger();
        }
    }
    
    public static MixinEnvironment getEnvironment(final Phase phase) {
        if (phase == null) {
            return Phase.DEFAULT.getEnvironment();
        }
        return phase.getEnvironment();
    }
    
    public static MixinEnvironment getDefaultEnvironment() {
        return getEnvironment(Phase.DEFAULT);
    }
    
    public static MixinEnvironment getCurrentEnvironment() {
        if (MixinEnvironment.currentEnvironment == null) {
            MixinEnvironment.currentEnvironment = getEnvironment(getCurrentPhase());
        }
        return MixinEnvironment.currentEnvironment;
    }
    
    public static CompatibilityLevel getCompatibilityLevel() {
        return MixinEnvironment.compatibility;
    }
    
    @Deprecated
    public static void setCompatibilityLevel(final CompatibilityLevel level) throws IllegalArgumentException {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (!"org.spongepowered.asm.mixin.transformer.MixinConfig".equals(stackTrace[2].getClassName())) {
            MixinEnvironment.logger.warn("MixinEnvironment::setCompatibilityLevel is deprecated and will be removed. Set level via config instead!");
        }
        if (level != MixinEnvironment.compatibility && level.isAtLeast(MixinEnvironment.compatibility)) {
            if (!level.isSupported()) {
                throw new IllegalArgumentException("The requested compatibility level " + level + " could not be set. Level is not supported");
            }
            MixinEnvironment.compatibility = level;
            MixinEnvironment.logger.info("Compatibility level set to {}", new Object[] { level });
        }
    }
    
    static void gotoPhase(final Phase phase) {
        if (phase == null || phase.ordinal < 0) {
            throw new IllegalArgumentException("Cannot go to the specified phase, phase is null or invalid");
        }
        if (phase.ordinal > getCurrentPhase().ordinal) {
            MixinBootstrap.addProxy();
        }
        if (phase == Phase.DEFAULT) {
            final org.apache.logging.log4j.core.Logger log = (org.apache.logging.log4j.core.Logger)LogManager.getLogger("FML");
            log.removeAppender((Appender)MixinLogger.appender);
        }
        MixinEnvironment.currentPhase = phase;
        MixinEnvironment.currentEnvironment = getEnvironment(getCurrentPhase());
    }
    
    static {
        excludeTransformers = Sets.newHashSet((Object[])new String[] { "net.minecraftforge.fml.common.asm.transformers.EventSubscriptionTransformer", "cpw.mods.fml.common.asm.transformers.EventSubscriptionTransformer", "net.minecraftforge.fml.common.asm.transformers.TerminalTransformer", "cpw.mods.fml.common.asm.transformers.TerminalTransformer" });
        MixinEnvironment.currentPhase = Phase.NOT_INITIALISED;
        MixinEnvironment.compatibility = Option.DEFAULT_COMPATIBILITY_LEVEL.getEnumValue(CompatibilityLevel.JAVA_6);
        MixinEnvironment.showHeader = true;
        logger = LogManager.getLogger("mixin");
    }
    
    public static class Phase
    {
        static final Phase NOT_INITIALISED;
        public static final Phase PREINIT;
        public static final Phase INIT;
        public static final Phase DEFAULT;
        static final List<Phase> phases;
        final int ordinal;
        final String name;
        private MixinEnvironment environment;
        
        private Phase(final int ordinal, final String name) {
            this.ordinal = ordinal;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
        
        public static Phase forName(final String name) {
            for (final Phase phase : Phase.phases) {
                if (phase.name.equals(name)) {
                    return phase;
                }
            }
            return null;
        }
        
        MixinEnvironment getEnvironment() {
            if (this.ordinal < 0) {
                throw new IllegalArgumentException("Cannot access the NOT_INITIALISED environment");
            }
            if (this.environment == null) {
                this.environment = new MixinEnvironment(this);
            }
            return this.environment;
        }
        
        static {
            NOT_INITIALISED = new Phase(-1, "NOT_INITIALISED");
            PREINIT = new Phase(0, "PREINIT");
            INIT = new Phase(1, "INIT");
            DEFAULT = new Phase(2, "DEFAULT");
            phases = (List)ImmutableList.of((Object)Phase.PREINIT, (Object)Phase.INIT, (Object)Phase.DEFAULT);
        }
    }
    
    public enum Side
    {
        UNKNOWN("UNKNOWN", 0), 
        CLIENT("CLIENT", 1), 
        SERVER("SERVER", 2);
        
        protected abstract boolean detect();
        
        protected final String getSideName() {
            for (final ITweaker tweaker : (List)Blackboard.get("Tweaks")) {
                if (tweaker.getClass().getName().endsWith(".common.launcher.FMLServerTweaker")) {
                    return "SERVER";
                }
                if (tweaker.getClass().getName().endsWith(".common.launcher.FMLTweaker")) {
                    return "CLIENT";
                }
            }
            String name = this.getSideName("net.minecraftforge.fml.relauncher.FMLLaunchHandler", "side");
            if (name != null) {
                return name;
            }
            name = this.getSideName("cpw.mods.fml.relauncher.FMLLaunchHandler", "side");
            if (name != null) {
                return name;
            }
            name = this.getSideName("com.mumfrey.liteloader.launch.LiteLoaderTweaker", "getEnvironmentType");
            if (name != null) {
                return name;
            }
            return "UNKNOWN";
        }
        
        private String getSideName(final String className, final String methodName) {
            try {
                final Class<?> clazz = Class.forName(className, false, (ClassLoader)Launch.classLoader);
                final Method method = clazz.getDeclaredMethod(methodName, (Class<?>[])new Class[0]);
                return ((Enum)method.invoke(null, new Object[0])).name();
            }
            catch (Exception ex) {
                return null;
            }
        }
    }
    
    public enum Option
    {
        DEBUG_ALL("debug"), 
        DEBUG_EXPORT(Option.DEBUG_ALL, "export"), 
        DEBUG_EXPORT_FILTER(Option.DEBUG_EXPORT, "filter", false), 
        DEBUG_EXPORT_DECOMPILE("DEBUG_EXPORT_DECOMPILE", 3, Option.DEBUG_EXPORT, "decompile"), 
        DEBUG_EXPORT_DECOMPILE_THREADED("DEBUG_EXPORT_DECOMPILE_THREADED", 4, Option.DEBUG_EXPORT_DECOMPILE, "async"), 
        DEBUG_VERIFY(Option.DEBUG_ALL, "verify"), 
        DEBUG_VERBOSE(Option.DEBUG_ALL, "verbose"), 
        DEBUG_INJECTORS(Option.DEBUG_ALL, "countInjections"), 
        DEBUG_STRICT("DEBUG_STRICT", 8, Option.DEBUG_ALL, "strict"), 
        DEBUG_UNIQUE(Option.DEBUG_STRICT, "unique"), 
        DEBUG_TARGETS(Option.DEBUG_STRICT, "targets"), 
        DUMP_TARGET_ON_FAILURE("dumpTargetOnFailure"), 
        CHECK_ALL("checks"), 
        CHECK_IMPLEMENTS(Option.CHECK_ALL, "interfaces"), 
        CHECK_IMPLEMENTS_STRICT("CHECK_IMPLEMENTS_STRICT", 14, Option.CHECK_IMPLEMENTS, "strict"), 
        IGNORE_CONSTRAINTS("ignoreConstraints"), 
        HOT_SWAP("hotSwap"), 
        ENVIRONMENT("ENVIRONMENT", 17, "env"), 
        OBFUSCATION_TYPE(Option.ENVIRONMENT, "obf"), 
        DISABLE_REFMAP(Option.ENVIRONMENT, "disableRefMap"), 
        DEFAULT_COMPATIBILITY_LEVEL(Option.ENVIRONMENT, "compatLevel"), 
        INITIALISER_INJECTION_MODE("initialiserInjectionMode", "default");
        
        private static final String PREFIX = "mixin";
        final Option parent;
        final String property;
        final String defaultValue;
        final boolean flag;
        final int depth;
        
        private Option(final String property) {
            this(null, property, true);
        }
        
        private Option(final String property, final boolean flag) {
            this(null, property, flag);
        }
        
        private Option(final String property, final String defaultStringValue) {
            this(null, property, false, defaultStringValue);
        }
        
        private Option(final Option parent, final String property) {
            this(parent, property, true);
        }
        
        private Option(final Option parent, final String property, final boolean flag) {
            this(parent, property, flag, null);
        }
        
        private Option(final Option parent, final String property, final String defaultStringValue) {
            this(parent, property, false, defaultStringValue);
        }
        
        private Option(Option parent, final String property, final boolean flag, final String defaultStringValue) {
            this.parent = parent;
            this.property = ((parent != null) ? parent.property : "mixin") + "." + property;
            this.defaultValue = defaultStringValue;
            this.flag = flag;
            int depth;
            for (depth = 0; parent != null; parent = parent.parent, ++depth) {}
            this.depth = depth;
        }
        
        Option getParent() {
            return this.parent;
        }
        
        String getProperty() {
            return this.property;
        }
        
        @Override
        public String toString() {
            return this.flag ? String.valueOf(this.getBooleanValue()) : this.getStringValue();
        }
        
        protected boolean getLocalBooleanValue() {
            return Booleans.parseBoolean(System.getProperty(this.property), false);
        }
        
        protected boolean getInheritedBooleanValue() {
            return this.parent != null && this.parent.getBooleanValue();
        }
        
        boolean getBooleanValue() {
            return this.getLocalBooleanValue() || this.getInheritedBooleanValue();
        }
        
        String getStringValue() {
            return (this.parent == null || this.parent.getBooleanValue()) ? System.getProperty(this.property, this.defaultValue) : this.defaultValue;
        }
        
         <E extends Enum<E>> E getEnumValue(final E defaultValue) {
            final String value = System.getProperty(this.property, defaultValue.name());
            try {
                return Enum.valueOf(defaultValue.getClass(), value.toUpperCase());
            }
            catch (IllegalArgumentException ex) {
                return defaultValue;
            }
        }
    }
    
    public enum CompatibilityLevel
    {
        JAVA_6(6, 50, false), 
        JAVA_7("JAVA_7", 1, 7, 51, false), 
        JAVA_8("JAVA_8", 2, 8, 52, true);
        
        private final int ver;
        private final int classVersion;
        private final boolean supportsMethodsInInterfaces;
        private CompatibilityLevel maxCompatibleLevel;
        
        private CompatibilityLevel(final int ver, final int classVersion, final boolean resolveMethodsInInterfaces) {
            this.ver = ver;
            this.classVersion = classVersion;
            this.supportsMethodsInInterfaces = resolveMethodsInInterfaces;
        }
        
        private void setMaxCompatibleLevel(final CompatibilityLevel maxCompatibleLevel) {
            this.maxCompatibleLevel = maxCompatibleLevel;
        }
        
        boolean isSupported() {
            return true;
        }
        
        public int classVersion() {
            return this.classVersion;
        }
        
        public boolean supportsMethodsInInterfaces() {
            return this.supportsMethodsInInterfaces;
        }
        
        public boolean isAtLeast(final CompatibilityLevel level) {
            return this.ver >= level.ver;
        }
        
        public boolean canElevateTo(final CompatibilityLevel level) {
            return level == null || this.maxCompatibleLevel == null || level.ver <= this.maxCompatibleLevel.ver;
        }
        
        public boolean canSupport(final CompatibilityLevel level) {
            return level == null || level.canElevateTo(this);
        }
    }
    
    public static class EnvironmentStateTweaker implements ITweaker
    {
        public void acceptOptions(final List<String> args, final File gameDir, final File assetsDir, final String profile) {
        }
        
        public void injectIntoClassLoader(final LaunchClassLoader classLoader) {
            MixinBootstrap.getPlatform().injectIntoClassLoader(classLoader);
        }
        
        public String getLaunchTarget() {
            return "";
        }
        
        public String[] getLaunchArguments() {
            MixinEnvironment.gotoPhase(Phase.DEFAULT);
            return new String[0];
        }
    }
    
    static class TokenProviderWrapper implements Comparable<TokenProviderWrapper>
    {
        private static int nextOrder;
        private final int priority;
        private final int order;
        private final IEnvironmentTokenProvider provider;
        private final MixinEnvironment environment;
        
        public TokenProviderWrapper(final IEnvironmentTokenProvider provider, final MixinEnvironment environment) {
            this.provider = provider;
            this.environment = environment;
            this.order = TokenProviderWrapper.nextOrder++;
            this.priority = provider.getPriority();
        }
        
        @Override
        public int compareTo(final TokenProviderWrapper other) {
            if (other == null) {
                return 0;
            }
            if (other.priority == this.priority) {
                return other.order - this.order;
            }
            return other.priority - this.priority;
        }
        
        public IEnvironmentTokenProvider getProvider() {
            return this.provider;
        }
        
        Integer getToken(final String token) {
            return this.provider.getToken(token, this.environment);
        }
        
        static {
            TokenProviderWrapper.nextOrder = 0;
        }
    }
    
    static class MixinLogger
    {
        static MixinAppender appender;
        
        public MixinLogger() {
            final org.apache.logging.log4j.core.Logger log = (org.apache.logging.log4j.core.Logger)LogManager.getLogger("FML");
            MixinLogger.appender.start();
            log.addAppender((Appender)MixinLogger.appender);
        }
        
        static {
            MixinLogger.appender = new MixinAppender("MixinLogger", null, null);
        }
        
        static class MixinAppender extends AbstractAppender
        {
            protected MixinAppender(final String name, final Filter filter, final Layout<? extends Serializable> layout) {
                super(name, filter, (Layout)layout);
            }
            
            public void append(final LogEvent event) {
                if (event.getLevel() == Level.DEBUG && "Validating minecraft".equals(event.getMessage().getFormat())) {
                    MixinEnvironment.gotoPhase(Phase.INIT);
                }
            }
        }
    }
}
