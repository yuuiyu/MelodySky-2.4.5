//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.util.launchwrapper.*;
import com.google.common.collect.*;
import org.spongepowered.asm.util.*;
import java.lang.annotation.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.extensibility.*;
import org.apache.logging.log4j.*;
import java.io.*;
import net.minecraft.launchwrapper.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import java.util.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;

class MixinInfo extends TreeInfo implements Comparable<MixinInfo>, IMixinInfo
{
    private static final LaunchClassLoaderUtil classLoaderUtil;
    static int mixinOrder;
    private final transient Logger logger;
    private final transient MixinConfig parent;
    private final String name;
    private final String className;
    private final int priority;
    private final boolean virtual;
    private final List<ClassInfo> targetClasses;
    private final List<String> targetClassNames;
    private final transient int order;
    private final transient IMixinConfigPlugin plugin;
    private final transient MixinEnvironment.Phase phase;
    private final transient ClassInfo info;
    private final transient SubType type;
    private final transient boolean strict;
    private transient State pendingState;
    private transient State state;
    
    MixinInfo(final MixinConfig parent, final String mixinName, final boolean runTransformers, final IMixinConfigPlugin plugin, final boolean suppressPlugin) {
        this.logger = LogManager.getLogger("mixin");
        this.order = MixinInfo.mixinOrder++;
        this.parent = parent;
        this.name = mixinName;
        this.className = parent.getMixinPackage() + mixinName;
        this.plugin = plugin;
        this.phase = parent.getEnvironment().getPhase();
        this.strict = parent.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_TARGETS);
        try {
            final byte[] mixinBytes = this.loadMixinClass(this.className, runTransformers);
            this.pendingState = new State(mixinBytes);
            this.info = this.pendingState.getClassInfo();
            this.type = SubType.getTypeFor(this);
        }
        catch (InvalidMixinException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new InvalidMixinException((IMixinInfo)this, ex2);
        }
        if (!this.type.isLoadable()) {
            MixinInfo.classLoaderUtil.registerInvalidClass(this.className);
        }
        try {
            this.priority = this.readPriority(this.pendingState.getClassNode());
            this.virtual = this.readPseudo(this.pendingState.getClassNode());
            this.targetClasses = this.readTargetClasses(this.pendingState.getClassNode(), suppressPlugin);
            this.targetClassNames = Collections.unmodifiableList((List<? extends String>)Lists.transform((List)this.targetClasses, Functions.toStringFunction()));
        }
        catch (InvalidMixinException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new InvalidMixinException((IMixinInfo)this, ex2);
        }
    }
    
    void validate() {
        if (this.pendingState == null) {
            throw new IllegalStateException("No pending validation state for " + this);
        }
        try {
            this.pendingState.validate(this.type, this.targetClasses);
            this.state = this.pendingState;
        }
        finally {
            this.pendingState = null;
        }
    }
    
    protected List<ClassInfo> readTargetClasses(final MixinClassNode classNode, final boolean suppressPlugin) {
        if (classNode == null) {
            return Collections.emptyList();
        }
        final AnnotationNode mixin = ASMHelper.getInvisibleAnnotation(classNode, (Class<? extends Annotation>)Mixin.class);
        if (mixin == null) {
            throw new InvalidMixinException((IMixinInfo)this, String.format("The mixin '%s' is missing an @Mixin annotation", this.className));
        }
        final List<ClassInfo> targets = new ArrayList<ClassInfo>();
        final List<Type> publicTargets = ASMHelper.getAnnotationValue(mixin, "value");
        final List<String> privateTargets = ASMHelper.getAnnotationValue(mixin, "targets");
        if (publicTargets != null) {
            this.readTargets(targets, Lists.transform((List)publicTargets, (Function)new llIl(this)), suppressPlugin, false);
        }
        if (privateTargets != null) {
            this.readTargets(targets, Lists.transform((List)privateTargets, (Function)new lll(this)), suppressPlugin, true);
        }
        return targets;
    }
    
    private void readTargets(final Collection<ClassInfo> outTargets, final Collection<String> inTargets, final boolean suppressPlugin, final boolean checkPublic) {
        for (final String targetRef : inTargets) {
            final String targetName = targetRef.replace('/', '.');
            if (MixinInfo.classLoaderUtil.isClassLoaded(targetName) && !this.isReloading()) {
                final String message = String.format("Critical problem: %s target %s was already transformed.", this, targetName);
                if (this.parent.isRequired()) {
                    throw new MixinTargetAlreadyLoadedException((IMixinInfo)this, message, targetName);
                }
                this.logger.error(message);
            }
            if (this.plugin == null || suppressPlugin || this.plugin.shouldApplyMixin(targetName, this.className)) {
                final ClassInfo targetInfo = this.getTarget(targetName, checkPublic);
                if (targetInfo == null || outTargets.contains(targetInfo)) {
                    continue;
                }
                outTargets.add(targetInfo);
                targetInfo.addMixin(this);
            }
        }
    }
    
    private ClassInfo getTarget(final String targetName, final boolean checkPublic) throws InvalidMixinException {
        final ClassInfo targetInfo = ClassInfo.forName(targetName);
        if (targetInfo == null) {
            if (this.isVirtual()) {
                this.logger.debug("Skipping virtual target {} for {}", new Object[] { targetName, this });
            }
            else {
                this.handleTargetError(String.format("@Mixin target %s was not found %s", targetName, this));
            }
            return null;
        }
        this.type.validateTarget(targetName, targetInfo);
        if (checkPublic && targetInfo.isPublic() && !this.isVirtual()) {
            this.handleTargetError(String.format("@Mixin target %s is public in %s and should be specified in value", targetName, this));
        }
        return targetInfo;
    }
    
    private void handleTargetError(final String message) {
        if (this.strict) {
            this.logger.error(message);
            throw new InvalidMixinException((IMixinInfo)this, message);
        }
        this.logger.warn(message);
    }
    
    protected int readPriority(final ClassNode classNode) {
        if (classNode == null) {
            return this.parent.getDefaultMixinPriority();
        }
        final AnnotationNode mixin = ASMHelper.getInvisibleAnnotation(classNode, (Class<? extends Annotation>)Mixin.class);
        if (mixin == null) {
            throw new InvalidMixinException((IMixinInfo)this, String.format("The mixin '%s' is missing an @Mixin annotation", this.className));
        }
        final Integer priority = ASMHelper.getAnnotationValue(mixin, "priority");
        return (priority == null) ? this.parent.getDefaultMixinPriority() : priority;
    }
    
    protected boolean readPseudo(final ClassNode classNode) {
        return ASMHelper.getInvisibleAnnotation(classNode, (Class<? extends Annotation>)Pseudo.class) != null;
    }
    
    private boolean isReloading() {
        return this.pendingState instanceof Reloaded;
    }
    
    private State getState() {
        return (this.state != null) ? this.state : this.pendingState;
    }
    
    ClassInfo getClassInfo() {
        return this.info;
    }
    
    public IMixinConfig getConfig() {
        return (IMixinConfig)this.parent;
    }
    
    MixinConfig getParent() {
        return this.parent;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getClassRef() {
        return this.getClassInfo().getName();
    }
    
    public byte[] getClassBytes() {
        return this.getState().getClassBytes();
    }
    
    public boolean isDetachedSuper() {
        return this.getState().isDetachedSuper();
    }
    
    public boolean isUnique() {
        return this.getState().isUnique();
    }
    
    public boolean isVirtual() {
        return this.virtual;
    }
    
    public boolean isLoadable() {
        return this.type.isLoadable();
    }
    
    public Level getLoggingLevel() {
        return this.parent.getLoggingLevel();
    }
    
    public MixinEnvironment.Phase getPhase() {
        return this.phase;
    }
    
    public MixinClassNode getClassNode(final int flags) {
        return this.getState().createClassNode(flags);
    }
    
    public List<String> getTargetClasses() {
        return this.targetClassNames;
    }
    
    List<InterfaceInfo> getSoftImplements() {
        return Collections.unmodifiableList(this.getState().getSoftImplements());
    }
    
    Set<String> getSyntheticInnerClasses() {
        return Collections.unmodifiableSet((Set<? extends String>)this.getState().getSyntheticInnerClasses());
    }
    
    List<ClassInfo> getTargets() {
        return Collections.unmodifiableList((List<? extends ClassInfo>)this.targetClasses);
    }
    
    Set<String> getInterfaces() {
        return this.getState().getInterfaces();
    }
    
    MixinTargetContext createContextFor(final TargetClassContext target) {
        final MixinClassNode classNode = this.getClassNode(8);
        return this.type.createPreProcessor(classNode).prepare().createContextFor(target);
    }
    
    private byte[] loadMixinClass(final String mixinClassName, final boolean runTransformers) throws ClassNotFoundException {
        byte[] mixinBytes = null;
        try {
            mixinBytes = TreeInfo.loadClass(mixinClassName, runTransformers);
        }
        catch (ClassNotFoundException ex2) {
            throw new ClassNotFoundException(String.format("The specified mixin '%s' was not found", mixinClassName));
        }
        catch (IOException ex) {
            this.logger.warn("Failed to load mixin %s, the specified mixin will not be applied", new Object[] { mixinClassName });
            throw new InvalidMixinException((IMixinInfo)this, "An error was encountered whilst loading the mixin class", ex);
        }
        return mixinBytes;
    }
    
    void reloadMixin(final byte[] mixinBytes) {
        if (this.pendingState != null) {
            throw new IllegalStateException("Cannot reload mixin while it is initialising");
        }
        this.pendingState = new Reloaded(this.state, mixinBytes);
        this.validate();
    }
    
    @Override
    public int compareTo(final MixinInfo other) {
        if (other == null) {
            return 0;
        }
        if (other.priority == this.priority) {
            return this.order - other.order;
        }
        return this.priority - other.priority;
    }
    
    public void preApply(final String transformedName, final ClassNode targetClass) {
        if (this.plugin != null) {
            this.plugin.preApply(transformedName, targetClass, this.className, (IMixinInfo)this);
        }
    }
    
    public void postApply(final String transformedName, final ClassNode targetClass) {
        if (this.plugin != null) {
            this.plugin.postApply(transformedName, targetClass, this.className, (IMixinInfo)this);
        }
        this.parent.postApply(transformedName, targetClass);
    }
    
    public String toString() {
        return String.format("%s:%s", this.parent.getName(), this.name);
    }
    
    static {
        classLoaderUtil = LaunchClassLoaderUtil.forClassLoader(Launch.classLoader);
        MixinInfo.mixinOrder = 0;
    }
    
    class MixinMethodNode extends MethodNode
    {
        private final String originalName;
        
        public MixinMethodNode(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
            super(327680, access, name, desc, signature, exceptions);
            this.originalName = name;
        }
        
        public String toString() {
            return String.format("%s%s", this.originalName, this.desc);
        }
        
        public String getOriginalName() {
            return this.originalName;
        }
        
        public boolean isInjector() {
            return this.getInjectorAnnotation() != null || this.isSurrogate();
        }
        
        public boolean isSurrogate() {
            return this.getVisibleAnnotation((Class<? extends Annotation>)Surrogate.class) != null;
        }
        
        public AnnotationNode getVisibleAnnotation(final Class<? extends Annotation> annotationClass) {
            return ASMHelper.getVisibleAnnotation(this, annotationClass);
        }
        
        public AnnotationNode getInjectorAnnotation() {
            return InjectionInfo.getInjectorAnnotation((IMixinInfo)MixinInfo.this, (MethodNode)this);
        }
        
        public IMixinInfo getOwner() {
            return (IMixinInfo)MixinInfo.this;
        }
    }
    
    class MixinClassNode extends ClassNode
    {
        public final List<MixinMethodNode> mixinMethods;
        
        public MixinClassNode(final MixinInfo mixinInfo, final MixinInfo mixin) {
            this(mixinInfo, 327680);
        }
        
        public MixinClassNode(final int api) {
            super(api);
            this.mixinMethods = (List<MixinMethodNode>)this.methods;
        }
        
        public MixinInfo getMixin() {
            return MixinInfo.this;
        }
        
        public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
            final MethodNode method = new MixinMethodNode(access, name, desc, signature, exceptions);
            this.methods.add(method);
            return (MethodVisitor)method;
        }
    }
    
    class State
    {
        private byte[] mixinBytes;
        private final ClassInfo classInfo;
        private boolean detachedSuper;
        private boolean unique;
        protected final Set<String> interfaces;
        protected final List<InterfaceInfo> softImplements;
        protected final Set<String> syntheticInnerClasses;
        protected MixinClassNode classNode;
        
        State(final MixinInfo mixinInfo, final byte[] mixinBytes) {
            this(mixinInfo, mixinBytes, null);
        }
        
        State(final byte[] mixinBytes, final ClassInfo classInfo) {
            this.interfaces = new HashSet<String>();
            this.softImplements = new ArrayList<InterfaceInfo>();
            this.syntheticInnerClasses = new HashSet<String>();
            this.mixinBytes = mixinBytes;
            this.connect();
            this.classInfo = ((classInfo != null) ? classInfo : ClassInfo.fromClassNode((ClassNode)this.getClassNode()));
        }
        
        private void connect() {
            this.classNode = this.createClassNode(0);
        }
        
        private void complete() {
            this.classNode = null;
        }
        
        ClassInfo getClassInfo() {
            return this.classInfo;
        }
        
        byte[] getClassBytes() {
            return this.mixinBytes;
        }
        
        MixinClassNode getClassNode() {
            return this.classNode;
        }
        
        boolean isDetachedSuper() {
            return this.detachedSuper;
        }
        
        boolean isUnique() {
            return this.unique;
        }
        
        List<? extends InterfaceInfo> getSoftImplements() {
            return this.softImplements;
        }
        
        Set<String> getSyntheticInnerClasses() {
            return this.syntheticInnerClasses;
        }
        
        Set<String> getInterfaces() {
            return this.interfaces;
        }
        
        MixinClassNode createClassNode(final int flags) {
            final MixinClassNode classNode = new MixinClassNode(MixinInfo.this);
            final ClassReader classReader = new ClassReader(this.mixinBytes);
            classReader.accept((ClassVisitor)classNode, flags);
            return classNode;
        }
        
        void validate(final SubType type, final List<ClassInfo> targetClasses) {
            final MixinPreProcessorStandard preProcessor = type.createPreProcessor(this.getClassNode()).prepare();
            for (final ClassInfo target : targetClasses) {
                preProcessor.conform(target);
            }
            type.validate(this, targetClasses);
            this.detachedSuper = type.isDetachedSuper();
            this.unique = (ASMHelper.getVisibleAnnotation(this.getClassNode(), Unique.class) != null);
            this.validateInner();
            this.validateClassVersion();
            this.validateRemappables(targetClasses);
            this.readImplementations(type);
            this.readInnerClasses();
            this.validateChanges(type, targetClasses);
            this.complete();
        }
        
        private void validateInner() {
            if (!this.classInfo.isProbablyStatic()) {
                throw new InvalidMixinException((IMixinInfo)MixinInfo.this, "Inner class mixin must be declared static");
            }
        }
        
        private void validateClassVersion() {
            if (this.classNode.version > MixinEnvironment.getCompatibilityLevel().classVersion()) {
                String helpText = ".";
                for (final MixinEnvironment.CompatibilityLevel level : MixinEnvironment.CompatibilityLevel.values()) {
                    if (level.classVersion() >= this.classNode.version) {
                        helpText = String.format(". Mixin requires compatibility level %s or above.", level.name());
                    }
                }
                throw new InvalidMixinException((IMixinInfo)MixinInfo.this, "Unsupported mixin class version " + this.classNode.version + helpText);
            }
        }
        
        private void validateRemappables(final List<ClassInfo> targetClasses) {
            if (targetClasses.size() > 1) {
                for (final FieldNode field : this.classNode.fields) {
                    this.validateRemappable(Shadow.class, field.name, ASMHelper.getVisibleAnnotation(field, (Class<? extends Annotation>)Shadow.class));
                }
                for (final MethodNode method : this.classNode.methods) {
                    this.validateRemappable(Shadow.class, method.name, ASMHelper.getVisibleAnnotation(method, (Class<? extends Annotation>)Shadow.class));
                    final AnnotationNode overwrite = ASMHelper.getVisibleAnnotation(method, (Class<? extends Annotation>)Overwrite.class);
                    if (overwrite != null && ((method.access & 0x8) == 0x0 || (method.access & 0x1) == 0x0)) {
                        throw new InvalidMixinException((IMixinInfo)MixinInfo.this, "Found @Overwrite annotation on " + method.name + " in " + this);
                    }
                }
            }
        }
        
        private void validateRemappable(final Class<Shadow> annotationClass, final String name, final AnnotationNode annotation) {
            if (annotation != null && ASMHelper.getAnnotationValue(annotation, "remap", Boolean.TRUE)) {
                throw new InvalidMixinException((IMixinInfo)MixinInfo.this, "Found a remappable @" + annotationClass.getSimpleName() + " annotation on " + name + " in " + this);
            }
        }
        
        void readImplementations(final SubType type) {
            this.interfaces.addAll(this.classNode.interfaces);
            this.interfaces.addAll(type.getInterfaces());
            final AnnotationNode implementsAnnotation = ASMHelper.getInvisibleAnnotation(this.classNode, (Class<? extends Annotation>)Implements.class);
            if (implementsAnnotation == null) {
                return;
            }
            final List<AnnotationNode> interfaces = ASMHelper.getAnnotationValue(implementsAnnotation);
            if (interfaces == null) {
                return;
            }
            for (final AnnotationNode interfaceNode : interfaces) {
                final InterfaceInfo interfaceInfo = InterfaceInfo.fromAnnotation(MixinInfo.this, interfaceNode);
                this.softImplements.add(interfaceInfo);
                this.interfaces.add(interfaceInfo.getInternalName());
                if (!(this instanceof Reloaded)) {
                    this.classInfo.addInterface(interfaceInfo.getInternalName());
                }
            }
        }
        
        void readInnerClasses() {
            for (final InnerClassNode inner : this.classNode.innerClasses) {
                final ClassInfo innerClass = ClassInfo.forName(inner.name);
                if (innerClass.isSynthetic() && innerClass.isProbablyStatic()) {
                    if ((inner.outerName == null || !inner.outerName.equals(this.classInfo.getName())) && !inner.name.startsWith(this.classNode.name + "$")) {
                        throw new InvalidMixinException((IMixinInfo)MixinInfo.this, "Unhandled synthetic inner class found: " + inner.name);
                    }
                    this.syntheticInnerClasses.add(inner.name);
                }
            }
        }
        
        protected void validateChanges(final SubType type, final List<ClassInfo> targetClasses) {
            type.createPreProcessor(this.classNode).prepare();
        }
    }
    
    class Reloaded extends State
    {
        private final State previous;
        
        Reloaded(final State previous, final byte[] mixinBytes) {
            super(mixinBytes, previous.getClassInfo());
            this.previous = previous;
        }
        
        @Override
        protected void validateChanges(final SubType type, final List<ClassInfo> targetClasses) {
            if (!this.syntheticInnerClasses.equals(this.previous.syntheticInnerClasses)) {
                throw new MixinReloadException((IMixinInfo)MixinInfo.this, "Cannot change inner classes");
            }
            if (!this.interfaces.equals(this.previous.interfaces)) {
                throw new MixinReloadException((IMixinInfo)MixinInfo.this, "Cannot change interfaces");
            }
            if (!new HashSet(this.softImplements).equals(new HashSet(this.previous.softImplements))) {
                throw new MixinReloadException((IMixinInfo)MixinInfo.this, "Cannot change soft interfaces");
            }
            final List<ClassInfo> targets = MixinInfo.this.readTargetClasses(this.classNode, true);
            if (!new HashSet(targets).equals(new HashSet(targetClasses))) {
                throw new MixinReloadException((IMixinInfo)MixinInfo.this, "Cannot change target classes");
            }
            final int priority = MixinInfo.this.readPriority(this.classNode);
            if (priority != MixinInfo.this.getPriority()) {
                throw new MixinReloadException((IMixinInfo)MixinInfo.this, "Cannot change mixin priority");
            }
        }
    }
    
    abstract static class SubType
    {
        protected final MixinInfo mixin;
        protected final String annotationType;
        protected final boolean targetMustBeInterface;
        protected boolean detached;
        
        SubType(final MixinInfo info, final String annotationType, final boolean targetMustBeInterface) {
            this.mixin = info;
            this.annotationType = annotationType;
            this.targetMustBeInterface = targetMustBeInterface;
        }
        
        Collection<String> getInterfaces() {
            return (Collection<String>)Collections.emptyList();
        }
        
        boolean isDetachedSuper() {
            return this.detached;
        }
        
        boolean isLoadable() {
            return false;
        }
        
        void validateTarget(final String targetName, final ClassInfo targetInfo) {
            final boolean targetIsInterface = targetInfo.isInterface();
            if (targetIsInterface != this.targetMustBeInterface) {
                final String not = targetIsInterface ? "" : "not ";
                throw new InvalidMixinException((IMixinInfo)this.mixin, this.annotationType + " target type mismatch: " + targetName + " is " + not + "an interface in " + this);
            }
        }
        
        abstract void validate(final State p0, final List<ClassInfo> p1);
        
        abstract MixinPreProcessorStandard createPreProcessor(final MixinClassNode p0);
        
        static SubType getTypeFor(final MixinInfo mixin) {
            if (!mixin.getClassInfo().isInterface()) {
                return new Standard(mixin);
            }
            boolean containsNonAccessorMethod = false;
            for (final ClassInfo.Method method : mixin.getClassInfo().getMethods()) {
                containsNonAccessorMethod |= !method.isAccessor();
            }
            if (containsNonAccessorMethod) {
                return new Interface(mixin);
            }
            return new Accessor(mixin);
        }
        
        static class Standard extends SubType
        {
            Standard(final MixinInfo info) {
                super(info, "@Mixin", false);
            }
            
            @Override
            void validate(final State state, final List<ClassInfo> targetClasses) {
                final ClassNode classNode = state.getClassNode();
                for (final ClassInfo targetClass : targetClasses) {
                    if (classNode.superName.equals(targetClass.getSuperName())) {
                        continue;
                    }
                    if (!targetClass.hasSuperClass(classNode.superName, ClassInfo.Traversal.SUPER)) {
                        final ClassInfo superClass = ClassInfo.forName(classNode.superName);
                        if (superClass.isMixin()) {
                            for (final ClassInfo superTarget : superClass.getTargets()) {
                                if (targetClasses.contains(superTarget)) {
                                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Illegal hierarchy detected. Derived mixin " + this + " targets the same class " + superTarget.getClassName() + " as its superclass " + superClass.getClassName());
                                }
                            }
                        }
                        throw new InvalidMixinException((IMixinInfo)this.mixin, "Super class '" + classNode.superName.replace('/', '.') + "' of " + this.mixin.getName() + " was not found in the hierarchy of target class '" + targetClass + "'");
                    }
                    this.detached = true;
                }
            }
            
            @Override
            MixinPreProcessorStandard createPreProcessor(final MixinClassNode classNode) {
                return new MixinPreProcessorStandard(this.mixin, classNode);
            }
        }
        
        static class Interface extends SubType
        {
            Interface(final MixinInfo info) {
                super(info, "@Mixin", true);
            }
            
            @Override
            void validate(final State state, final List<ClassInfo> targetClasses) {
                if (!MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces()) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Interface mixin not supported in current enviromnment");
                }
                final ClassNode classNode = state.getClassNode();
                if (!"java/lang/Object".equals(classNode.superName)) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Super class of " + this + " is invalid, found " + classNode.superName.replace('/', '.'));
                }
            }
            
            @Override
            MixinPreProcessorStandard createPreProcessor(final MixinClassNode classNode) {
                return new MixinPreProcessorInterface(this.mixin, classNode);
            }
        }
        
        static class Accessor extends SubType
        {
            private final Collection<String> interfaces;
            
            Accessor(final MixinInfo info) {
                super(info, "@Mixin", false);
                (this.interfaces = new ArrayList<String>()).add(info.getClassRef());
            }
            
            @Override
            boolean isLoadable() {
                return true;
            }
            
            @Override
            Collection<String> getInterfaces() {
                return this.interfaces;
            }
            
            @Override
            void validateTarget(final String targetName, final ClassInfo targetInfo) {
                final boolean targetIsInterface = targetInfo.isInterface();
                if (targetIsInterface && !MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces()) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Accessor mixin targetting an interface is not supported in current enviromnment");
                }
            }
            
            @Override
            void validate(final State state, final List<ClassInfo> targetClasses) {
                final ClassNode classNode = state.getClassNode();
                if (!"java/lang/Object".equals(classNode.superName)) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Super class of " + this + " is invalid, found " + classNode.superName.replace('/', '.'));
                }
            }
            
            @Override
            MixinPreProcessorStandard createPreProcessor(final MixinClassNode classNode) {
                return new MixinPreProcessorAccessor(this.mixin, classNode);
            }
        }
    }
}
