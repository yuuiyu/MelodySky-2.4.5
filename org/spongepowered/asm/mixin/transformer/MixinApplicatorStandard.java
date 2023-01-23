//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import java.lang.annotation.*;
import org.apache.logging.log4j.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.transformer.meta.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;
import org.spongepowered.asm.util.throwables.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.injection.*;
import com.google.common.collect.*;

class MixinApplicatorStandard
{
    protected static final List<Class<? extends Annotation>> CONSTRAINED_ANNOTATIONS;
    protected static final int[] INITIALISER_OPCODE_BLACKLIST;
    protected final Logger logger;
    protected final TargetClassContext context;
    protected final String targetName;
    protected final ClassNode targetClass;
    
    MixinApplicatorStandard(final TargetClassContext context) {
        this.logger = LogManager.getLogger("mixin");
        this.context = context;
        this.targetName = context.getClassName();
        this.targetClass = context.getClassNode();
    }
    
    void apply(final SortedSet<MixinInfo> mixins) {
        final List<MixinTargetContext> mixinContexts = new ArrayList<MixinTargetContext>();
        for (final MixinInfo mixin : mixins) {
            this.logger.log(mixin.getLoggingLevel(), "Mixing {} from {} into {}", new Object[] { mixin.getName(), mixin.getParent(), this.targetName });
            mixinContexts.add(mixin.createContextFor(this.context));
        }
        MixinTargetContext current = null;
        try {
            for (final MixinTargetContext context : mixinContexts) {
                (current = context).preApply(this.targetName, this.targetClass);
            }
            for (final ApplicatorPass pass : ApplicatorPass.values()) {
                for (final MixinTargetContext context2 : mixinContexts) {
                    this.applyMixin(current = context2, pass);
                }
            }
            for (final MixinTargetContext context : mixinContexts) {
                (current = context).postApply(this.targetName, this.targetClass);
            }
        }
        catch (InvalidMixinException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new InvalidMixinException((IReferenceMapperContext)current, "Unexpecteded " + ex2.getClass().getSimpleName() + " whilst applying the mixin class: " + ex2.getMessage(), ex2);
        }
        this.applySourceMap(this.context);
        this.context.processDebugTasks();
    }
    
    protected final void applyMixin(final MixinTargetContext mixin, final ApplicatorPass pass) {
        switch (MixinApplicatorStandard.llII.$SwitchMap$org$spongepowered$asm$mixin$transformer$MixinApplicatorStandard$ApplicatorPass[pass.ordinal()]) {
            case 1: {
                this.applyInterfaces(mixin);
                this.applyAttributes(mixin);
                this.applyAnnotations(mixin);
                this.applyFields(mixin);
                this.applyMethods(mixin);
                this.applyInitialisers(mixin);
                break;
            }
            case 2: {
                this.prepareInjections(mixin);
                break;
            }
            case 3: {
                this.applyAccessors(mixin);
                this.applyInjections(mixin);
                break;
            }
            default: {
                throw new IllegalStateException("Invalid pass specified " + pass);
            }
        }
    }
    
    protected void applyInterfaces(final MixinTargetContext mixin) {
        for (final String interfaceName : mixin.getInterfaces()) {
            if (!this.targetClass.interfaces.contains(interfaceName)) {
                this.targetClass.interfaces.add(interfaceName);
                mixin.getTargetClassInfo().addInterface(interfaceName);
            }
        }
    }
    
    protected void applyAttributes(final MixinTargetContext mixin) {
        if (mixin.shouldSetSourceFile()) {
            this.targetClass.sourceFile = mixin.getSourceFile();
        }
        this.targetClass.version = Math.max(this.targetClass.version, mixin.getMinRequiredClassVersion());
    }
    
    protected void applyAnnotations(final MixinTargetContext mixin) {
        final ClassNode sourceClass = mixin.getClassNode();
        this.mergeAnnotations(sourceClass, this.targetClass);
    }
    
    protected void applyFields(final MixinTargetContext mixin) {
        this.mergeShadowFields(mixin);
        this.mergeNewFields(mixin);
    }
    
    protected void mergeShadowFields(final MixinTargetContext mixin) {
        for (final Map.Entry<FieldNode, ClassInfo.Field> entry : mixin.getShadowFields()) {
            final FieldNode shadow = entry.getKey();
            final FieldNode target = this.findTargetField(shadow);
            if (target != null) {
                this.mergeAnnotations(shadow, target);
                if (!entry.getValue().isDecoratedMutable() || ASMHelper.hasFlag(target, 2)) {
                    continue;
                }
                final FieldNode fieldNode = target;
                fieldNode.access &= 0xFFFFFFEF;
            }
        }
    }
    
    protected void mergeNewFields(final MixinTargetContext mixin) {
        for (final FieldNode field : mixin.getFields()) {
            final FieldNode target = this.findTargetField(field);
            if (target == null) {
                this.targetClass.fields.add(field);
            }
        }
    }
    
    protected void applyMethods(final MixinTargetContext mixin) {
        for (final MethodNode shadow : mixin.getShadowMethods()) {
            this.applyShadowMethod(mixin, shadow);
        }
        for (final MethodNode mixinMethod : mixin.getMethods()) {
            this.applyNormalMethod(mixin, mixinMethod);
        }
    }
    
    protected void applyShadowMethod(final MixinTargetContext mixin, final MethodNode shadow) {
        final MethodNode target = this.findTargetMethod(shadow);
        if (target != null) {
            this.mergeAnnotations(shadow, target);
        }
    }
    
    protected void applyNormalMethod(final MixinTargetContext mixin, final MethodNode mixinMethod) {
        mixin.transformMethod(mixinMethod);
        if (!mixinMethod.name.startsWith("<")) {
            this.checkMethodVisibility(mixin, mixinMethod);
            this.checkMethodConstraints(mixin, mixinMethod);
            this.mergeMethod(mixin, mixinMethod);
        }
        else if ("<clinit>".equals(mixinMethod.name)) {
            this.appendInsns(mixinMethod);
        }
    }
    
    protected void mergeMethod(final MixinTargetContext mixin, final MethodNode method) {
        final boolean isOverwrite = ASMHelper.getVisibleAnnotation(method, (Class<? extends Annotation>)Overwrite.class) != null;
        final MethodNode target = this.findTargetMethod(method);
        if (target != null) {
            if (this.isAlreadyMerged(mixin, method, isOverwrite, target)) {
                return;
            }
            final AnnotationNode intrinsic = ASMHelper.getInvisibleAnnotation(method, (Class<? extends Annotation>)Intrinsic.class);
            if (intrinsic != null) {
                if (this.mergeIntrinsic(mixin, method, isOverwrite, target, intrinsic)) {
                    return;
                }
            }
            else {
                this.targetClass.methods.remove(target);
            }
        }
        else if (isOverwrite) {
            throw new InvalidMixinException((IReferenceMapperContext)mixin, String.format("Overwrite target \"%s\" was not located in target class %s", method.name, mixin.getTargetClassRef()));
        }
        this.targetClass.methods.add(method);
        mixin.addMergedMethod(method);
    }
    
    protected boolean isAlreadyMerged(final MixinTargetContext mixin, final MethodNode method, final boolean isOverwrite, final MethodNode target) {
        final AnnotationNode merged = ASMHelper.getVisibleAnnotation(target, (Class<? extends Annotation>)MixinMerged.class);
        if (merged == null) {
            if (ASMHelper.getVisibleAnnotation(target, (Class<? extends Annotation>)Final.class) != null) {
                this.logger.warn("Overwrite prohibited for @Final method {} in {}. Skipping method.", new Object[] { method.name, mixin });
                return true;
            }
            return false;
        }
        else {
            final String sessionId = ASMHelper.getAnnotationValue(merged, "sessionId");
            if (!this.context.getSessionId().equals(sessionId)) {
                throw new ClassFormatError("Invalid @MixinMerged annotation found in" + mixin + " at " + method.name + " in " + this.targetClass.name);
            }
            if (ASMHelper.hasFlag(target, 4160) && ASMHelper.hasFlag(method, 4160)) {
                if (mixin.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
                    this.logger.warn("Synthetic bridge method clash for {} in {}", new Object[] { method.name, mixin });
                }
                return true;
            }
            final String owner = ASMHelper.getAnnotationValue(merged, "mixin");
            final int priority = ASMHelper.getAnnotationValue(merged, "priority");
            if (priority >= mixin.getPriority() && !owner.equals(mixin.getClassName())) {
                this.logger.warn("Method overwrite conflict for {} in {}, previously written by {}. Skipping method.", new Object[] { method.name, mixin, owner });
                return true;
            }
            if (ASMHelper.getVisibleAnnotation(target, (Class<? extends Annotation>)Final.class) != null) {
                this.logger.warn("Method overwrite conflict for @Final method {} in {} declared by {}. Skipping method.", new Object[] { method.name, mixin, owner });
                return true;
            }
            return false;
        }
    }
    
    protected boolean mergeIntrinsic(final MixinTargetContext mixin, final MethodNode method, final boolean isOverwrite, final MethodNode target, final AnnotationNode intrinsic) {
        if (isOverwrite) {
            throw new InvalidMixinException((IReferenceMapperContext)mixin, "@Intrinsic is not compatible with @Overwrite, remove one of these annotations on " + method.name + " in " + mixin);
        }
        final String methodName = method.name + method.desc;
        if (ASMHelper.hasFlag(method, 8)) {
            throw new InvalidMixinException((IReferenceMapperContext)mixin, "@Intrinsic method cannot be static, found " + methodName + " in " + mixin);
        }
        final AnnotationNode renamed = ASMHelper.getVisibleAnnotation(method, (Class<? extends Annotation>)MixinRenamed.class);
        if (renamed == null || !ASMHelper.getAnnotationValue(renamed, "isInterfaceMember", false)) {
            throw new InvalidMixinException((IReferenceMapperContext)mixin, "@Intrinsic method must be prefixed interface method, no rename encountered on " + methodName + " in " + mixin);
        }
        if (!ASMHelper.getAnnotationValue(intrinsic, "displace", false)) {
            this.logger.log(mixin.getLoggingLevel(), "Skipping Intrinsic mixin method {} for {}", new Object[] { methodName, mixin.getTargetClassRef() });
            return true;
        }
        this.displaceIntrinsic(mixin, method, target);
        return false;
    }
    
    protected void displaceIntrinsic(final MixinTargetContext mixin, final MethodNode method, final MethodNode target) {
        final String proxyName = "proxy+" + target.name;
        for (final AbstractInsnNode insn : method.instructions) {
            if (insn instanceof MethodInsnNode && insn.getOpcode() != 184) {
                final MethodInsnNode methodNode = (MethodInsnNode)insn;
                if (!methodNode.owner.equals(this.targetClass.name) || !methodNode.name.equals(target.name) || !methodNode.desc.equals(target.desc)) {
                    continue;
                }
                methodNode.name = proxyName;
            }
        }
        target.name = proxyName;
    }
    
    protected final void appendInsns(final MethodNode method) {
        if (Type.getReturnType(method.desc) != Type.VOID_TYPE) {
            throw new IllegalArgumentException("Attempted to merge insns from a method which does not return void");
        }
        final MethodNode target = this.findTargetMethod(method);
        if (target != null) {
            final AbstractInsnNode returnNode = findInsn(target, 177);
            if (returnNode != null) {
                for (final AbstractInsnNode insn : method.instructions) {
                    if (!(insn instanceof LineNumberNode) && insn.getOpcode() != 177) {
                        target.instructions.insertBefore(returnNode, insn);
                    }
                }
                target.maxLocals = Math.max(target.maxLocals, method.maxLocals);
                target.maxStack = Math.max(target.maxStack, method.maxStack);
            }
            return;
        }
        this.targetClass.methods.add(method);
    }
    
    protected void applyInitialisers(final MixinTargetContext mixin) {
        final MethodNode ctor = this.getConstructor(mixin);
        if (ctor == null) {
            return;
        }
        final Deque<AbstractInsnNode> initialiser = this.getInitialiser(mixin, ctor);
        if (initialiser == null || initialiser.size() == 0) {
            return;
        }
        for (final MethodNode method : this.targetClass.methods) {
            if ("<init>".equals(method.name)) {
                method.maxStack = Math.max(method.maxStack, ctor.maxStack);
                this.injectInitialiser(mixin, method, initialiser);
            }
        }
    }
    
    protected MethodNode getConstructor(final MixinTargetContext mixin) {
        MethodNode ctor = null;
        for (final MethodNode mixinMethod : mixin.getMethods()) {
            if ("<init>".equals(mixinMethod.name) && hasLineNumbers(mixinMethod)) {
                if (ctor == null) {
                    ctor = mixinMethod;
                }
                else {
                    this.logger.warn(String.format("Mixin %s has multiple constructors, %s was selected\n", mixin, ctor.desc));
                }
            }
        }
        return ctor;
    }
    
    private Range getConstructorRange(final MethodNode ctor) {
        boolean lineNumberIsValid = false;
        AbstractInsnNode endReturn = null;
        int line = 0;
        int start = 0;
        int end = 0;
        int superIndex = -1;
        for (final AbstractInsnNode insn : ctor.instructions) {
            if (insn instanceof LineNumberNode) {
                line = ((LineNumberNode)insn).line;
                lineNumberIsValid = true;
            }
            else if (insn instanceof MethodInsnNode) {
                if (insn.getOpcode() != 183 || !"<init>".equals(((MethodInsnNode)insn).name) || superIndex != -1) {
                    continue;
                }
                superIndex = ctor.instructions.indexOf(insn);
                start = line;
            }
            else if (insn.getOpcode() == 181) {
                lineNumberIsValid = false;
            }
            else {
                if (insn.getOpcode() != 177) {
                    continue;
                }
                if (lineNumberIsValid) {
                    end = line;
                }
                else {
                    end = start;
                    endReturn = insn;
                }
            }
        }
        if (endReturn != null) {
            final LabelNode label = new LabelNode(new Label());
            ctor.instructions.insertBefore(endReturn, (AbstractInsnNode)label);
            ctor.instructions.insertBefore(endReturn, (AbstractInsnNode)new LineNumberNode(start, label));
        }
        return new Range(start, end, superIndex);
    }
    
    protected final Deque<AbstractInsnNode> getInitialiser(final MixinTargetContext mixin, final MethodNode ctor) {
        final Range init = this.getConstructorRange(ctor);
        if (!init.isValid()) {
            return null;
        }
        int line = 0;
        final Deque<AbstractInsnNode> initialiser = new ArrayDeque<AbstractInsnNode>();
        boolean gatherNodes = false;
        int trimAtOpcode = -1;
        LabelNode optionalInsn = null;
        final Iterator<AbstractInsnNode> iter = (Iterator<AbstractInsnNode>)ctor.instructions.iterator(init.marker);
        while (iter.hasNext()) {
            final AbstractInsnNode insn = iter.next();
            if (insn instanceof LineNumberNode) {
                line = ((LineNumberNode)insn).line;
                final AbstractInsnNode next = ctor.instructions.get(ctor.instructions.indexOf(insn) + 1);
                if (line == init.end && next.getOpcode() != 177) {
                    gatherNodes = true;
                    trimAtOpcode = 177;
                }
                else {
                    gatherNodes = init.excludes(line);
                    trimAtOpcode = -1;
                }
            }
            else {
                if (!gatherNodes) {
                    continue;
                }
                if (optionalInsn != null) {
                    initialiser.add((AbstractInsnNode)optionalInsn);
                    optionalInsn = null;
                }
                if (insn instanceof LabelNode) {
                    optionalInsn = (LabelNode)insn;
                }
                else {
                    final int opcode = insn.getOpcode();
                    if (opcode == trimAtOpcode) {
                        trimAtOpcode = -1;
                    }
                    else {
                        for (final int ivalidOp : MixinApplicatorStandard.INITIALISER_OPCODE_BLACKLIST) {
                            if (opcode == ivalidOp) {
                                throw new InvalidMixinException((IReferenceMapperContext)mixin, "Cannot handle " + ASMHelper.getOpcodeName(opcode) + " opcode (0x" + Integer.toHexString(opcode).toUpperCase() + ") in class initialiser");
                            }
                        }
                        initialiser.add(insn);
                    }
                }
            }
        }
        final AbstractInsnNode last = initialiser.peekLast();
        if (last != null && last.getOpcode() != 181) {
            throw new InvalidMixinException((IReferenceMapperContext)mixin, "Could not parse initialiser, expected 0xB5, found 0x" + Integer.toHexString(last.getOpcode()) + " in " + mixin);
        }
        return initialiser;
    }
    
    protected final void injectInitialiser(final MixinTargetContext mixin, final MethodNode ctor, final Deque<AbstractInsnNode> initialiser) {
        final Map<LabelNode, LabelNode> labels = ASMHelper.cloneLabels(ctor.instructions);
        AbstractInsnNode insn = this.findInitialiserInjectionPoint(mixin, ctor, initialiser);
        if (insn == null) {
            this.logger.warn("Failed to locate initialiser injection point in <init>{}, initialiser was not mixed in.", new Object[] { ctor.desc });
            return;
        }
        for (final AbstractInsnNode node : initialiser) {
            if (node instanceof LabelNode) {
                continue;
            }
            if (node instanceof JumpInsnNode) {
                throw new InvalidMixinException((IReferenceMapperContext)mixin, "Unsupported JUMP opcode in initialiser in " + mixin);
            }
            final AbstractInsnNode imACloneNow = node.clone((Map)labels);
            ctor.instructions.insert(insn, imACloneNow);
            insn = imACloneNow;
        }
    }
    
    protected AbstractInsnNode findInitialiserInjectionPoint(final MixinTargetContext mixin, final MethodNode ctor, final Deque<AbstractInsnNode> initialiser) {
        final Set<String> initialisedFields = new HashSet<String>();
        for (final AbstractInsnNode initialiserInsn : initialiser) {
            if (initialiserInsn.getOpcode() == 181) {
                initialisedFields.add(fieldKey((FieldInsnNode)initialiserInsn));
            }
        }
        final InitialiserInjectionMode mode = this.getInitialiserInjectionMode(mixin.getEnvironment());
        final String targetName = mixin.getTargetClassInfo().getName();
        final String targetSuperName = mixin.getTargetClassInfo().getSuperName();
        AbstractInsnNode targetInsn = null;
        for (final AbstractInsnNode insn : ctor.instructions) {
            if (insn.getOpcode() == 183 && "<init>".equals(((MethodInsnNode)insn).name)) {
                final String owner = ((MethodInsnNode)insn).owner;
                if (!owner.equals(targetName) && !owner.equals(targetSuperName)) {
                    continue;
                }
                targetInsn = insn;
                if (mode == InitialiserInjectionMode.SAFE) {
                    break;
                }
                continue;
            }
            else {
                if (insn.getOpcode() != 181 || mode != InitialiserInjectionMode.DEFAULT) {
                    continue;
                }
                final String key = fieldKey((FieldInsnNode)insn);
                if (!initialisedFields.contains(key)) {
                    continue;
                }
                targetInsn = insn;
            }
        }
        return targetInsn;
    }
    
    private InitialiserInjectionMode getInitialiserInjectionMode(final MixinEnvironment environment) {
        final String strMode = environment.getOptionValue(MixinEnvironment.Option.INITIALISER_INJECTION_MODE);
        if (strMode == null) {
            return InitialiserInjectionMode.DEFAULT;
        }
        try {
            return InitialiserInjectionMode.valueOf(strMode.toUpperCase());
        }
        catch (Exception ex) {
            this.logger.warn("Could not parse unexpected value \"{}\" for mixin.initialiserInjectionMode, reverting to DEFAULT", new Object[] { strMode });
            return InitialiserInjectionMode.DEFAULT;
        }
    }
    
    private static String fieldKey(final FieldInsnNode fieldNode) {
        return String.format("%s:%s", fieldNode.desc, fieldNode.name);
    }
    
    protected void prepareInjections(final MixinTargetContext mixin) {
        mixin.prepareInjections();
    }
    
    protected void applyInjections(final MixinTargetContext mixin) {
        mixin.applyInjections();
    }
    
    protected void applyAccessors(final MixinTargetContext mixin) {
        final List<MethodNode> accessorMethods = mixin.generateAccessors();
        for (final MethodNode method : accessorMethods) {
            if (!method.name.startsWith("<")) {
                this.mergeMethod(mixin, method);
            }
        }
    }
    
    protected void checkMethodVisibility(final MixinTargetContext mixin, final MethodNode mixinMethod) {
        if (ASMHelper.hasFlag(mixinMethod, 8) && !ASMHelper.hasFlag(mixinMethod, 2) && !ASMHelper.hasFlag(mixinMethod, 4096) && ASMHelper.getVisibleAnnotation(mixinMethod, (Class<? extends Annotation>)Overwrite.class) == null) {
            throw new InvalidMixinException((IReferenceMapperContext)mixin, String.format("Mixin %s contains non-private static method %s", mixin, mixinMethod));
        }
    }
    
    protected void applySourceMap(final TargetClassContext context) {
        this.targetClass.sourceDebug = context.getSourceMap().toString();
    }
    
    protected void checkMethodConstraints(final MixinTargetContext mixin, final MethodNode method) {
        for (final Class<? extends Annotation> annotationType : MixinApplicatorStandard.CONSTRAINED_ANNOTATIONS) {
            final AnnotationNode annotation = ASMHelper.getVisibleAnnotation(method, annotationType);
            if (annotation != null) {
                this.checkConstraints(mixin, method, annotation);
            }
        }
    }
    
    protected final void checkConstraints(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode annotation) {
        try {
            final ConstraintParser.Constraint constraint = ConstraintParser.parse(annotation);
            final MixinEnvironment environment = MixinEnvironment.getCurrentEnvironment();
            try {
                constraint.check((ITokenProvider)environment);
            }
            catch (ConstraintViolationException ex) {
                final String message = String.format("Constraint violation: %s on %s in %s", ex.getMessage(), method, mixin);
                this.logger.warn(message);
                if (!environment.getOption(MixinEnvironment.Option.IGNORE_CONSTRAINTS)) {
                    throw new InvalidMixinException((IReferenceMapperContext)mixin, message, ex);
                }
            }
        }
        catch (InvalidConstraintException ex2) {
            throw new InvalidMixinException((IReferenceMapperContext)mixin, ex2.getMessage());
        }
    }
    
    protected final void mergeAnnotations(final ClassNode from, final ClassNode to) {
        to.visibleAnnotations = this.mergeAnnotations(from.visibleAnnotations, to.visibleAnnotations, from.name);
        to.invisibleAnnotations = this.mergeAnnotations(from.invisibleAnnotations, to.invisibleAnnotations, from.name);
    }
    
    protected final void mergeAnnotations(final MethodNode from, final MethodNode to) {
        to.visibleAnnotations = this.mergeAnnotations(from.visibleAnnotations, to.visibleAnnotations, from.name);
        to.invisibleAnnotations = this.mergeAnnotations(from.invisibleAnnotations, to.invisibleAnnotations, from.name);
    }
    
    protected final void mergeAnnotations(final FieldNode from, final FieldNode to) {
        to.visibleAnnotations = this.mergeAnnotations(from.visibleAnnotations, to.visibleAnnotations, from.name);
        to.invisibleAnnotations = this.mergeAnnotations(from.invisibleAnnotations, to.invisibleAnnotations, from.name);
    }
    
    private List<AnnotationNode> mergeAnnotations(final List<AnnotationNode> from, List<AnnotationNode> to, final String name) {
        try {
            if (from == null) {
                return to;
            }
            if (to == null) {
                to = new ArrayList<AnnotationNode>();
            }
            for (final AnnotationNode annotation : from) {
                if (!this.isMergeableAnnotation(annotation)) {
                    continue;
                }
                final Iterator<AnnotationNode> iter = to.iterator();
                while (iter.hasNext()) {
                    if (iter.next().desc.equals(annotation.desc)) {
                        iter.remove();
                        break;
                    }
                }
                to.add(annotation);
            }
        }
        catch (Exception ex) {
            this.logger.warn("Exception encountered whilst merging annotations for {}", new Object[] { name });
        }
        return to;
    }
    
    private boolean isMergeableAnnotation(final AnnotationNode annotation) {
        return !annotation.desc.startsWith("L" + Constants.MIXIN_PACKAGE_REF) || annotation.desc.endsWith("Debug;");
    }
    
    protected static AbstractInsnNode findInsn(final MethodNode method, final int opcode) {
        for (final AbstractInsnNode insn : method.instructions) {
            if (insn.getOpcode() == opcode) {
                return insn;
            }
        }
        return null;
    }
    
    private static boolean hasLineNumbers(final MethodNode method) {
        final Iterator<AbstractInsnNode> iter = (Iterator<AbstractInsnNode>)method.instructions.iterator();
        while (iter.hasNext()) {
            if (iter.next() instanceof LineNumberNode) {
                return true;
            }
        }
        return false;
    }
    
    protected final MethodNode findTargetMethod(final MethodNode searchFor) {
        for (final MethodNode target : this.targetClass.methods) {
            if (target.name.equals(searchFor.name) && target.desc.equals(searchFor.desc)) {
                return target;
            }
        }
        return null;
    }
    
    protected final FieldNode findTargetField(final FieldNode searchFor) {
        for (final FieldNode target : this.targetClass.fields) {
            if (target.name.equals(searchFor.name)) {
                return target;
            }
        }
        return null;
    }
    
    static {
        CONSTRAINED_ANNOTATIONS = (List)ImmutableList.of((Object)Overwrite.class, (Object)Inject.class, (Object)ModifyArg.class, (Object)Redirect.class, (Object)ModifyVariable.class, (Object)ModifyConstant.class);
        INITIALISER_OPCODE_BLACKLIST = new int[] { 177, 21, 22, 23, 24, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 79, 80, 81, 82, 83, 84, 85, 86 };
    }
    
    enum ApplicatorPass
    {
        MAIN, 
        PREINJECT, 
        INJECT;
    }
    
    enum InitialiserInjectionMode
    {
        DEFAULT, 
        SAFE;
    }
    
    class Range
    {
        final int start;
        final int end;
        final int marker;
        
        Range(final int start, final int end, final int marker) {
            this.start = start;
            this.end = end;
            this.marker = marker;
        }
        
        boolean isValid() {
            return this.start != 0 && this.end != 0 && this.end >= this.start;
        }
        
        boolean contains(final int value) {
            return value >= this.start && value <= this.end;
        }
        
        boolean excludes(final int value) {
            return value < this.start || value > this.end;
        }
        
        @Override
        public String toString() {
            return String.format("Range[%d-%d,%d,valid=%s)", this.start, this.end, this.marker, this.isValid());
        }
    }
}
