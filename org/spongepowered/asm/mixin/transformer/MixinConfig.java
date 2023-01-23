//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import com.google.gson.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.launch.*;
import java.util.regex.*;
import org.spongepowered.asm.util.*;
import net.minecraft.launchwrapper.*;
import org.spongepowered.asm.mixin.transformer.debug.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.mixin.extensibility.*;
import java.util.*;
import org.apache.logging.log4j.*;
import org.spongepowered.asm.lib.tree.*;
import com.google.gson.*;
import java.io.*;

class MixinConfig implements Comparable<MixinConfig>, IMixinConfig
{
    private static int configOrder;
    private static final Set<String> globalMixinList;
    private final Logger logger;
    private final transient Map<String, List<MixinInfo>> mixinMapping;
    private final transient Set<String> unhandledTargets;
    private final transient List<MixinInfo> mixins;
    private final transient Set<String> syntheticInnerClasses;
    private final transient Set<String> passThroughClasses;
    private transient Config handle;
    @SerializedName("target")
    private String selector;
    @SerializedName("minVersion")
    private String version;
    @SerializedName("compatibilityLevel")
    private String compatibility;
    @SerializedName("required")
    private boolean required;
    @SerializedName("priority")
    private int priority;
    @SerializedName("mixinPriority")
    private int mixinPriority;
    @SerializedName("package")
    private String mixinPackage;
    @SerializedName("mixins")
    private List<String> mixinClasses;
    @SerializedName("client")
    private List<String> mixinClassesClient;
    @SerializedName("server")
    private List<String> mixinClassesServer;
    @SerializedName("setSourceFile")
    private boolean setSourceFile;
    @SerializedName("refmap")
    private String refMapperConfig;
    @SerializedName("verbose")
    private boolean verboseLogging;
    private final transient int order;
    private transient MixinEnvironment env;
    private transient String name;
    @SerializedName("plugin")
    private String pluginClassName;
    @SerializedName("injectors")
    private InjectorOptions injectorOptions;
    private transient IMixinConfigPlugin plugin;
    private transient ReferenceMapper refMapper;
    private transient boolean prepared;
    
    private MixinConfig() {
        this.logger = LogManager.getLogger("mixin");
        this.mixinMapping = new HashMap<String, List<MixinInfo>>();
        this.unhandledTargets = new HashSet<String>();
        this.mixins = new ArrayList<MixinInfo>();
        this.syntheticInnerClasses = new HashSet<String>();
        this.passThroughClasses = new HashSet<String>();
        this.priority = 1000;
        this.mixinPriority = 1000;
        this.setSourceFile = false;
        this.order = MixinConfig.configOrder++;
        this.injectorOptions = new InjectorOptions();
        this.prepared = false;
    }
    
    private boolean onLoad(final String name, final MixinEnvironment fallbackEnvironment) {
        this.name = name;
        this.env = this.parseSelector(this.selector, fallbackEnvironment);
        this.initCompatibilityLevel();
        return this.checkVersion();
    }
    
    private void initCompatibilityLevel() {
        if (this.compatibility == null) {
            return;
        }
        final MixinEnvironment.CompatibilityLevel level = MixinEnvironment.CompatibilityLevel.valueOf(this.compatibility.trim().toUpperCase());
        final MixinEnvironment.CompatibilityLevel current = MixinEnvironment.getCompatibilityLevel();
        if (level == current) {
            return;
        }
        if (current.isAtLeast(level) && !current.canSupport(level)) {
            throw new MixinInitialisationError("Mixin config " + this.name + " requires compatibility level " + level + " which is too old");
        }
        if (!current.canElevateTo(level)) {
            throw new MixinInitialisationError("Mixin config " + this.name + " requires compatibility level " + level + " which is prohibited by " + current);
        }
        MixinEnvironment.setCompatibilityLevel(level);
    }
    
    private MixinEnvironment parseSelector(final String target, final MixinEnvironment fallbackEnvironment) {
        if (target != null) {
            final String[] arr$;
            final String[] selectors = arr$ = target.split("[&\\| ]");
            for (String sel : arr$) {
                sel = sel.trim();
                final Pattern environmentSelector = Pattern.compile("^@env(?:ironment)?\\(([A-Z]+)\\)$");
                final Matcher environmentSelectorMatcher = environmentSelector.matcher(sel);
                if (environmentSelectorMatcher.matches()) {
                    return MixinEnvironment.getEnvironment(MixinEnvironment.Phase.forName(environmentSelectorMatcher.group(1)));
                }
            }
            final MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(target);
            if (phase != null) {
                return MixinEnvironment.getEnvironment(phase);
            }
        }
        return fallbackEnvironment;
    }
    
    private boolean checkVersion() throws MixinInitialisationError {
        final VersionNumber minVersion = VersionNumber.parse(this.version);
        final VersionNumber curVersion = VersionNumber.parse(this.env.getVersion());
        if (minVersion.compareTo(curVersion) <= 0) {
            return true;
        }
        this.logger.warn("Mixin config {} requires mixin subsystem version {} but {} was found. The mixin config will not be applied.", new Object[] { this.name, minVersion, curVersion });
        if (this.required) {
            throw new MixinInitialisationError("Required mixin config " + this.name + " requires mixin subsystem version " + minVersion);
        }
        return false;
    }
    
    void onSelect() {
        if (this.pluginClassName != null) {
            try {
                final Class<?> pluginClass = Class.forName(this.pluginClassName, true, (ClassLoader)Launch.classLoader);
                this.plugin = (IMixinConfigPlugin)pluginClass.newInstance();
                if (this.plugin != null) {
                    this.plugin.onLoad(this.mixinPackage);
                }
            }
            catch (Throwable th) {
                th.printStackTrace();
                this.plugin = null;
            }
        }
        if (!this.mixinPackage.endsWith(".")) {
            this.mixinPackage += ".";
        }
        if (this.refMapperConfig == null) {
            if (this.plugin != null) {
                this.refMapperConfig = this.plugin.getRefMapperConfig();
            }
            if (this.refMapperConfig == null) {
                this.refMapperConfig = "mixin.refmap.json";
            }
        }
        this.refMapper = ReferenceMapper.read(this.refMapperConfig);
        this.verboseLogging |= this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE);
    }
    
    void prepare(final IHotSwap hotSwapper) {
        if (this.prepared) {
            return;
        }
        this.prepared = true;
        this.prepareMixins(this.mixinClasses, false, hotSwapper);
        switch (MixinConfig.lIlI.$SwitchMap$org$spongepowered$asm$mixin$MixinEnvironment$Side[this.env.getSide().ordinal()]) {
            case 1: {
                this.prepareMixins(this.mixinClassesClient, false, hotSwapper);
                break;
            }
            case 2: {
                this.prepareMixins(this.mixinClassesServer, false, hotSwapper);
                break;
            }
            default: {
                this.logger.warn("Mixin environment was unable to detect the current side, sided mixins will not be applied");
                break;
            }
        }
    }
    
    void postInitialise(final IHotSwap hotSwapper) {
        if (this.plugin != null) {
            final List<String> pluginMixins = (List<String>)this.plugin.getMixins();
            this.prepareMixins(pluginMixins, true, hotSwapper);
        }
        final Iterator<MixinInfo> iter = this.mixins.iterator();
        while (iter.hasNext()) {
            final MixinInfo mixin = iter.next();
            try {
                mixin.validate();
                for (final String innerClass : mixin.getSyntheticInnerClasses()) {
                    this.syntheticInnerClasses.add(innerClass.replace('/', '.'));
                }
            }
            catch (InvalidMixinException ex) {
                this.logger.error(ex.getMixin() + ": " + ex.getMessage(), (Throwable)ex);
                this.removeMixin(mixin);
                iter.remove();
            }
            catch (Exception ex2) {
                this.logger.error(ex2.getMessage(), (Throwable)ex2);
                this.removeMixin(mixin);
                iter.remove();
            }
        }
    }
    
    private void removeMixin(final MixinInfo remove) {
        for (final List<MixinInfo> mixinsFor : this.mixinMapping.values()) {
            final Iterator<MixinInfo> iter = mixinsFor.iterator();
            while (iter.hasNext()) {
                if (remove == iter.next()) {
                    iter.remove();
                }
            }
        }
    }
    
    private void prepareMixins(final List<String> mixinClasses, final boolean suppressPlugin, final IHotSwap hotSwapper) {
        if (mixinClasses == null) {
            return;
        }
        for (final String mixinClass : mixinClasses) {
            final String fqMixinClass = this.mixinPackage + mixinClass;
            if (mixinClass != null) {
                if (MixinConfig.globalMixinList.contains(fqMixinClass)) {
                    continue;
                }
                MixinInfo mixin = null;
                try {
                    mixin = new MixinInfo(this, mixinClass, true, this.plugin, suppressPlugin);
                    if (mixin.getTargetClasses().size() <= 0) {
                        continue;
                    }
                    MixinConfig.globalMixinList.add(fqMixinClass);
                    for (final String targetClass : mixin.getTargetClasses()) {
                        final String targetClassName = targetClass.replace('/', '.');
                        this.mixinsFor(targetClassName).add(mixin);
                        this.unhandledTargets.add(targetClassName);
                    }
                    if (hotSwapper != null) {
                        hotSwapper.registerMixinClass(mixin.getClassName());
                    }
                    if (mixin.isLoadable()) {
                        this.passThroughClasses.add(mixin.getClassName());
                    }
                    this.mixins.add(mixin);
                }
                catch (InvalidMixinException ex) {
                    if (this.required) {
                        throw ex;
                    }
                    this.logger.error(ex.getMessage(), (Throwable)ex);
                }
                catch (Exception ex2) {
                    if (this.required) {
                        throw new InvalidMixinException((IMixinInfo)mixin, "Error initialising mixin " + mixin + " - " + ex2.getClass() + ": " + ex2.getMessage(), ex2);
                    }
                    this.logger.error(ex2.getMessage(), (Throwable)ex2);
                }
            }
        }
    }
    
    void postApply(final String transformedName, final ClassNode targetClass) {
        this.unhandledTargets.remove(transformedName);
    }
    
    public Config getHandle() {
        if (this.handle == null) {
            this.handle = new Config(this);
        }
        return this.handle;
    }
    
    public boolean isRequired() {
        return this.required;
    }
    
    public MixinEnvironment getEnvironment() {
        return this.env;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getMixinPackage() {
        return this.mixinPackage;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public int getDefaultMixinPriority() {
        return this.mixinPriority;
    }
    
    public int getDefaultRequiredInjections() {
        return this.injectorOptions.defaultRequireValue;
    }
    
    public String getDefaultInjectorGroup() {
        final String defaultGroup = this.injectorOptions.defaultGroup;
        return (defaultGroup != null && !defaultGroup.isEmpty()) ? defaultGroup : "default";
    }
    
    public boolean select(final MixinEnvironment environment) {
        return this.env == environment;
    }
    
    int getDeclaredMixinCount() {
        return getCollectionSize(this.mixinClasses, this.mixinClassesClient, this.mixinClassesServer);
    }
    
    int getMixinCount() {
        return this.mixins.size();
    }
    
    public List<String> getClasses() {
        return Collections.unmodifiableList((List<? extends String>)this.mixinClasses);
    }
    
    public boolean shouldSetSourceFile() {
        return this.setSourceFile;
    }
    
    public ReferenceMapper getReferenceMapper() {
        if (this.env.getOption(MixinEnvironment.Option.DISABLE_REFMAP)) {
            return ReferenceMapper.DEFAULT_MAPPER;
        }
        this.refMapper.setContext(this.env.getRefmapObfuscationContext());
        return this.refMapper;
    }
    
    String remapClassName(final String className, final String reference) {
        return this.getReferenceMapper().remap(className, reference);
    }
    
    public IMixinConfigPlugin getPlugin() {
        return this.plugin;
    }
    
    public Set<String> getTargets() {
        return Collections.unmodifiableSet((Set<? extends String>)this.mixinMapping.keySet());
    }
    
    public Set<String> getUnhandledTargets() {
        return Collections.unmodifiableSet((Set<? extends String>)this.unhandledTargets);
    }
    
    public Level getLoggingLevel() {
        return this.verboseLogging ? Level.INFO : Level.DEBUG;
    }
    
    public boolean packageMatch(final String className) {
        return className.startsWith(this.mixinPackage);
    }
    
    public boolean canPassThrough(final String className) {
        return this.syntheticInnerClasses.contains(className) || this.passThroughClasses.contains(className);
    }
    
    public ClassNode passThrough(final String className, final ClassNode passThroughClassNode) {
        if (this.syntheticInnerClasses.contains(className)) {
            return this.passThroughSyntheticInner(passThroughClassNode);
        }
        return passThroughClassNode;
    }
    
    private ClassNode passThroughSyntheticInner(final ClassNode classNode) {
        classNode.access |= 0x1;
        for (final FieldNode field : classNode.fields) {
            if ((field.access & 0x6) == 0x0) {
                final FieldNode fieldNode = field;
                fieldNode.access |= 0x1;
            }
        }
        for (final MethodNode method : classNode.methods) {
            if ((method.access & 0x6) == 0x0) {
                final MethodNode methodNode = method;
                methodNode.access |= 0x1;
            }
        }
        return classNode;
    }
    
    public boolean hasMixinsFor(final String targetClass) {
        return this.mixinMapping.containsKey(targetClass);
    }
    
    public List<MixinInfo> getMixinsFor(final String targetClass) {
        return this.mixinsFor(targetClass);
    }
    
    private List<MixinInfo> mixinsFor(final String targetClass) {
        List<MixinInfo> mixins = this.mixinMapping.get(targetClass);
        if (mixins == null) {
            mixins = new ArrayList<MixinInfo>();
            this.mixinMapping.put(targetClass, mixins);
        }
        return mixins;
    }
    
    public List<String> reloadMixin(final String mixinClass, final byte[] bytes) {
        for (final MixinInfo mixin : this.mixins) {
            if (mixin.getClassName().equals(mixinClass)) {
                mixin.reloadMixin(bytes);
                return mixin.getTargetClasses();
            }
        }
        return Collections.emptyList();
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public int compareTo(final MixinConfig other) {
        if (other == null) {
            return 0;
        }
        if (other.priority == this.priority) {
            return this.order - other.order;
        }
        return this.priority - other.priority;
    }
    
    static Config create(final String configFile, final MixinEnvironment outer) {
        try {
            final MixinConfig config = (MixinConfig)new Gson().fromJson((Reader)new InputStreamReader(Launch.classLoader.getResourceAsStream(configFile)), (Class)MixinConfig.class);
            if (config.onLoad(configFile, outer)) {
                return config.getHandle();
            }
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(String.format("The specified resource '%s' was invalid or could not be read", configFile), ex);
        }
    }
    
    private static int getCollectionSize(final Collection<?>... collections) {
        int total = 0;
        for (final Collection<?> collection : collections) {
            if (collection != null) {
                total += collection.size();
            }
        }
        return total;
    }
    
    static {
        MixinConfig.configOrder = 0;
        globalMixinList = new HashSet<String>();
    }
    
    static class InjectorOptions
    {
        @SerializedName("defaultRequire")
        int defaultRequireValue;
        @SerializedName("defaultGroup")
        String defaultGroup;
        
        InjectorOptions() {
            this.defaultRequireValue = 0;
            this.defaultGroup = "default";
        }
    }
}
