//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.struct.*;
import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.refmap.*;
import java.util.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.mixin.injection.code.*;
import org.spongepowered.asm.mixin.transformer.meta.*;
import java.lang.annotation.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;

public abstract class InjectionInfo extends SpecialMethodInfo
{
    protected final boolean isStatic;
    protected final Deque<MethodNode> targets;
    protected final MethodSlices slices;
    protected final List<InjectionPoint> injectionPoints;
    protected final Map<Target, List<InjectionNodes.InjectionNode>> targetNodes;
    protected Injector injector;
    protected InjectorGroupInfo group;
    private final List<MethodNode> injectedMethods;
    private int expectedCallbackCount;
    private int requiredCallbackCount;
    private int injectedCallbackCount;
    
    protected InjectionInfo(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode annotation) {
        super(mixin, method, annotation);
        this.targets = new ArrayDeque<MethodNode>();
        this.injectionPoints = new ArrayList<InjectionPoint>();
        this.targetNodes = new LinkedHashMap<Target, List<InjectionNodes.InjectionNode>>();
        this.injectedMethods = new ArrayList<MethodNode>(0);
        this.expectedCallbackCount = 1;
        this.requiredCallbackCount = 0;
        this.injectedCallbackCount = 0;
        this.isStatic = ASMHelper.methodIsStatic(method);
        this.slices = MethodSlices.parse(this);
        this.readAnnotation();
    }
    
    protected void readAnnotation() {
        if (this.annotation == null) {
            return;
        }
        final String type = "@" + ASMHelper.getSimpleName(this.annotation);
        final List<AnnotationNode> injectionPoints = this.readInjectionPoints(type);
        this.findMethods(this.parseTarget(type), type);
        this.parseInjectionPoints(injectionPoints);
        this.parseRequirements();
        this.injector = this.parseInjector(this.annotation);
    }
    
    protected MemberInfo parseTarget(final String type) {
        final String method = ASMHelper.getAnnotationValue(this.annotation, "method");
        if (method == null) {
            throw new InvalidInjectionException(this, type + " annotation on " + this.method.name + " is missing method name");
        }
        try {
            final MemberInfo targetMember = MemberInfo.parseAndValidate(method, this.mixin);
            if (targetMember.owner != null && !targetMember.owner.equals(this.mixin.getTargetClassRef())) {
                throw new InvalidInjectionException(this, type + " annotation on " + this.method.name + " specifies a target class '" + targetMember.owner + "', which is not supported");
            }
            return targetMember;
        }
        catch (InvalidMemberDescriptorException ex) {
            throw new InvalidInjectionException(this, type + " annotation on " + this.method.name + ", has invalid target descriptor: \"" + method + "\"");
        }
    }
    
    protected List<AnnotationNode> readInjectionPoints(final String type) {
        final Object atValue = ASMHelper.getAnnotationValue(this.annotation, "at");
        if (atValue instanceof List) {
            return (List<AnnotationNode>)atValue;
        }
        if (atValue instanceof AnnotationNode) {
            final List<AnnotationNode> ats = new ArrayList<AnnotationNode>();
            ats.add((AnnotationNode)atValue);
            return ats;
        }
        throw new InvalidInjectionException(this, type + " annotation on " + this.method.name + " is missing 'at' value(s)");
    }
    
    protected void parseInjectionPoints(final List<AnnotationNode> ats) {
        this.injectionPoints.addAll(InjectionPoint.parse(this.mixin, this.method, this.annotation, (List)ats));
    }
    
    protected void parseRequirements() {
        this.group = this.mixin.getInjectorGroups().parseGroup(this.method, this.mixin.getDefaultInjectorGroup()).add(this);
        final Integer expect = ASMHelper.getAnnotationValue(this.annotation, "expect");
        if (expect != null) {
            this.expectedCallbackCount = expect;
        }
        final Integer require = ASMHelper.getAnnotationValue(this.annotation, "require");
        if (require != null && require > -1) {
            this.requiredCallbackCount = require;
        }
        else if (this.group.isDefault()) {
            this.requiredCallbackCount = this.mixin.getDefaultRequiredInjections();
        }
    }
    
    protected abstract Injector parseInjector(final AnnotationNode p0);
    
    public boolean isValid() {
        return this.targets.size() > 0 && this.injectionPoints.size() > 0;
    }
    
    public void prepare() {
        this.targetNodes.clear();
        for (final MethodNode targetMethod : this.targets) {
            final Target target = this.mixin.getTargetMethod(targetMethod);
            final InjectorTarget injectorTarget = new InjectorTarget(this, target);
            this.targetNodes.put(target, this.injector.find(injectorTarget, (List)this.injectionPoints));
            injectorTarget.dispose();
        }
    }
    
    public void inject() {
        for (final Map.Entry<Target, List<InjectionNodes.InjectionNode>> entry : this.targetNodes.entrySet()) {
            this.injector.inject((Target)entry.getKey(), (List)entry.getValue());
        }
        this.targets.clear();
    }
    
    public void postInject() {
        for (final MethodNode method : this.injectedMethods) {
            this.classNode.methods.add(method);
        }
        if (MixinEnvironment.getCurrentEnvironment().getOption(MixinEnvironment.Option.DEBUG_INJECTORS) && this.injectedCallbackCount < this.expectedCallbackCount) {
            throw new InvalidInjectionException(this, String.format("Injection validation failed: %s %s%s in %s expected %d invocation(s) but %d succeeded", this.getDescription(), this.method.name, this.method.desc, this.mixin, this.expectedCallbackCount, this.injectedCallbackCount));
        }
        if (this.injectedCallbackCount < this.requiredCallbackCount) {
            throw new InjectionError(String.format("Critical injection failure: %s %s%s in %s failed injection check, (%d/%d) succeeded", this.getDescription(), this.method.name, this.method.desc, this.mixin, this.injectedCallbackCount, this.requiredCallbackCount));
        }
    }
    
    public void notifyInjected(final Target target) {
    }
    
    protected String getDescription() {
        return "Callback method";
    }
    
    @Override
    public String toString() {
        return describeInjector(this.mixin, this.annotation, this.method);
    }
    
    public Collection<MethodNode> getTargets() {
        return this.targets;
    }
    
    public MethodSlice getSlice(final String id) {
        return this.slices.get(this.getSliceId(id));
    }
    
    public String getSliceId(final String id) {
        return "";
    }
    
    public int getInjectedCallbackCount() {
        return this.injectedCallbackCount;
    }
    
    public MethodNode addMethod(final int access, final String name, final String desc) {
        final MethodNode method = new MethodNode(327680, access | 0x1000, name, desc, (String)null, (String[])null);
        this.injectedMethods.add(method);
        return method;
    }
    
    public void addCallbackInvocation(final MethodNode handler) {
        ++this.injectedCallbackCount;
    }
    
    private void findMethods(final MemberInfo searchFor, final String type) {
        this.targets.clear();
        int ordinal = 0;
        for (final MethodNode target : this.classNode.methods) {
            if (searchFor.matches(target.name, target.desc, ordinal)) {
                final boolean isMixinMethod = ASMHelper.getVisibleAnnotation(target, MixinMerged.class) != null;
                if (searchFor.matchAll) {
                    if (ASMHelper.methodIsStatic(target) != this.isStatic || target == this.method) {
                        continue;
                    }
                    if (isMixinMethod) {
                        continue;
                    }
                }
                this.checkTarget(target);
                this.targets.add(target);
                ++ordinal;
            }
        }
        if (this.targets.size() == 0) {
            throw new InvalidInjectionException(this, type + " annotation on " + this.method.name + " could not find '" + searchFor.name + "'");
        }
    }
    
    private void checkTarget(final MethodNode target) {
        final AnnotationNode merged = ASMHelper.getVisibleAnnotation(target, MixinMerged.class);
        if (merged == null) {
            return;
        }
        final String owner = ASMHelper.getAnnotationValue(merged, "mixin");
        final int priority = ASMHelper.getAnnotationValue(merged, "priority");
        if (priority >= this.mixin.getPriority() && !owner.equals(this.mixin.getClassName())) {
            throw new InvalidInjectionException(this, this + " cannot inject into " + this.classNode.name + "::" + target.name + target.desc + " merged by " + owner + " with priority " + priority);
        }
        if (ASMHelper.getVisibleAnnotation(target, (Class<? extends Annotation>)Final.class) != null) {
            throw new InvalidInjectionException(this, this + " cannot inject into @Final method " + this.classNode.name + "::" + target.name + target.desc + " merged by " + owner);
        }
    }
    
    public static InjectionInfo parse(final MixinTargetContext mixin, final MethodNode method) {
        final AnnotationNode annotation = getInjectorAnnotation(mixin.getMixin(), method);
        if (annotation == null) {
            return null;
        }
        if (annotation.desc.endsWith(Inject.class.getSimpleName() + ";")) {
            return (InjectionInfo)new CallbackInjectionInfo(mixin, method, annotation);
        }
        if (annotation.desc.endsWith(ModifyArg.class.getSimpleName() + ";")) {
            return new ModifyArgInjectionInfo(mixin, method, annotation);
        }
        if (annotation.desc.endsWith(Redirect.class.getSimpleName() + ";")) {
            return new RedirectInjectionInfo(mixin, method, annotation);
        }
        if (annotation.desc.endsWith(ModifyVariable.class.getSimpleName() + ";")) {
            return new ModifyVariableInjectionInfo(mixin, method, annotation);
        }
        if (annotation.desc.endsWith(ModifyConstant.class.getSimpleName() + ";")) {
            return new ModifyConstantInjectionInfo(mixin, method, annotation);
        }
        return null;
    }
    
    public static AnnotationNode getInjectorAnnotation(final IMixinInfo mixin, final MethodNode method) {
        AnnotationNode annotation = null;
        try {
            annotation = ASMHelper.getSingleVisibleAnnotation(method, Inject.class, ModifyArg.class, Redirect.class, ModifyVariable.class, ModifyConstant.class);
        }
        catch (IllegalArgumentException ex) {
            throw new InvalidMixinException(mixin, "Error parsing annotations on " + method.name + " in " + mixin.getClassName() + ": " + ex.getMessage());
        }
        return annotation;
    }
    
    public static String getInjectorPrefix(final AnnotationNode annotation) {
        if (annotation != null) {
            if (annotation.desc.endsWith(ModifyArg.class.getSimpleName() + ";")) {
                return "modify";
            }
            if (annotation.desc.endsWith(Redirect.class.getSimpleName() + ";")) {
                return "redirect";
            }
            if (annotation.desc.endsWith(ModifyVariable.class.getSimpleName() + ";")) {
                return "localvar";
            }
            if (annotation.desc.endsWith(ModifyConstant.class.getSimpleName() + ";")) {
                return "constant";
            }
        }
        return "handler";
    }
    
    static String describeInjector(final MixinTargetContext mixin, final AnnotationNode annotation, final MethodNode method) {
        return String.format("%s->@%s::%s%s", mixin.toString(), ASMHelper.getSimpleName(annotation), method.name, method.desc);
    }
}
