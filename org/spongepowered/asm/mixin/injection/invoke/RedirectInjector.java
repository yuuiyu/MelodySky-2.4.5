//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.invoke;

import org.spongepowered.asm.mixin.injection.points.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.util.*;
import java.lang.annotation.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.code.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import java.util.*;
import org.spongepowered.asm.lib.*;
import com.google.common.collect.*;
import com.google.common.primitives.*;
import com.google.common.base.*;
import org.spongepowered.asm.lib.tree.*;

public class RedirectInjector extends InvokeInjector
{
    private static final String KEY_NOMINATORS = "nominators";
    private static final String KEY_WILD = "wildcard";
    protected Meta meta;
    private Map<BeforeNew, ConstructorRedirectData> ctorRedirectors;
    
    public RedirectInjector(final InjectionInfo info) {
        this(info, "@Redirect");
    }
    
    protected RedirectInjector(final InjectionInfo info, final String annotationType) {
        super(info, annotationType);
        this.ctorRedirectors = new HashMap<BeforeNew, ConstructorRedirectData>();
        final int priority = info.getContext().getPriority();
        final boolean isFinal = ASMHelper.getVisibleAnnotation(this.methodNode, (Class<? extends Annotation>)Final.class) != null;
        this.meta = new Meta(priority, isFinal, this.info.toString(), this.methodNode.desc);
    }
    
    protected void checkTarget(final Target target) {
    }
    
    protected void addTargetNode(final Target target, final List<InjectionNodes.InjectionNode> myNodes, final AbstractInsnNode insn, final Set<InjectionPoint> nominators) {
        final InjectionNodes.InjectionNode node = target.injectionNodes.get(insn);
        ConstructorRedirectData ctorData = null;
        if (node != null) {
            final Meta other = (Meta)node.getDecoration("redirector");
            if (other != null && other.getOwner() != this) {
                if (other.priority >= this.meta.priority) {
                    Injector.logger.warn("{} conflict. Skipping {} with priority {}, already redirected by {} with priority {}", new Object[] { this.annotationType, this.info, this.meta.priority, other.name, other.priority });
                    return;
                }
                if (other.isFinal) {
                    throw new InvalidInjectionException(this.info, this.annotationType + " conflict: " + this + " failed because target was already remapped by " + other.name);
                }
            }
        }
        for (final InjectionPoint ip : nominators) {
            if (ip instanceof BeforeNew && !((BeforeNew)ip).hasDescriptor()) {
                ctorData = this.getCtorRedirect((BeforeNew)ip);
            }
        }
        final InjectionNodes.InjectionNode targetNode = target.injectionNodes.add(insn);
        targetNode.decorate("redirector", (Object)this.meta);
        targetNode.decorate("nominators", (Object)nominators);
        if (insn instanceof TypeInsnNode && insn.getOpcode() == 187) {
            targetNode.decorate("wildcard", (Object)(ctorData != null));
            targetNode.decorate("ctor", (Object)ctorData);
        }
        myNodes.add(targetNode);
    }
    
    private ConstructorRedirectData getCtorRedirect(final BeforeNew ip) {
        ConstructorRedirectData ctorRedirect = this.ctorRedirectors.get(ip);
        if (ctorRedirect == null) {
            ctorRedirect = new ConstructorRedirectData();
            this.ctorRedirectors.put(ip, ctorRedirect);
        }
        return ctorRedirect;
    }
    
    protected void inject(final Target target, final InjectionNodes.InjectionNode node) {
        if (!this.preInject(node)) {
            return;
        }
        if (node.isReplaced()) {
            throw new UnsupportedOperationException("Redirector target failure for " + this.info);
        }
        if (node.getCurrentTarget() instanceof MethodInsnNode) {
            super.checkTarget(target);
            this.injectAtInvoke(target, node);
            return;
        }
        if (node.getCurrentTarget() instanceof FieldInsnNode) {
            super.checkTarget(target);
            this.injectAtFieldAccess(target, node);
            return;
        }
        if (!(node.getCurrentTarget() instanceof TypeInsnNode) || node.getCurrentTarget().getOpcode() != 187) {
            throw new InvalidInjectionException(this.info, this.annotationType + " annotation on is targetting an invalid insn in " + target + " in " + this);
        }
        if (!this.isStatic && target.isStatic) {
            throw new InvalidInjectionException(this.info, "non-static callback method " + this + " has a static target which is not supported");
        }
        this.injectAtConstructor(target, node);
    }
    
    protected boolean preInject(final InjectionNodes.InjectionNode node) {
        final Meta other = (Meta)node.getDecoration("redirector");
        if (other.getOwner() != this) {
            Injector.logger.warn("{} conflict. Skipping {} with priority {}, already redirected by {} with priority {}", new Object[] { this.annotationType, this.info, this.meta.priority, other.name, other.priority });
            return false;
        }
        return true;
    }
    
    protected void postInject(final Target target, final InjectionNodes.InjectionNode node) {
        super.postInject(target, node);
        if (node.getOriginalTarget() instanceof TypeInsnNode && node.getOriginalTarget().getOpcode() == 187) {
            final ConstructorRedirectData meta = (ConstructorRedirectData)node.getDecoration("ctor");
            final boolean wildcard = (boolean)node.getDecoration("wildcard");
            if (wildcard && meta.injected == 0) {
                throw new InvalidInjectionException(this.info, this.annotationType + " ctor invocation was not found in " + target);
            }
        }
    }
    
    protected void injectAtInvoke(final Target target, final InjectionNodes.InjectionNode node) {
        final MethodInsnNode methodNode = (MethodInsnNode)node.getCurrentTarget();
        final boolean targetIsStatic = methodNode.getOpcode() == 184;
        final Type ownerType = Type.getType("L" + methodNode.owner + ";");
        final Type returnType = Type.getReturnType(methodNode.desc);
        final Type[] args = Type.getArgumentTypes(methodNode.desc);
        final Type[] stackVars = (Type[])(targetIsStatic ? args : ObjectArrays.concat((Object)ownerType, (Object[])args));
        boolean injectTargetParams = false;
        final String desc = Injector.printArgs(stackVars) + returnType;
        if (!desc.equals(this.methodNode.desc)) {
            final String alternateDesc = Injector.printArgs((Type[])ObjectArrays.concat((Object[])stackVars, (Object[])target.arguments, (Class)Type.class)) + returnType;
            if (!alternateDesc.equals(this.methodNode.desc)) {
                throw new InvalidInjectionException(this.info, this.annotationType + " handler method " + this + " has an invalid signature, expected " + desc + " found " + this.methodNode.desc);
            }
            injectTargetParams = true;
        }
        final InsnList insns = new InsnList();
        int extraLocals = ASMHelper.getArgsSize(stackVars) + 1;
        int extraStack = 1;
        int[] argMap = this.storeArgs(target, stackVars, insns, 0);
        if (injectTargetParams) {
            final int argSize = ASMHelper.getArgsSize(target.arguments);
            extraLocals += argSize;
            extraStack += argSize;
            argMap = Ints.concat(new int[][] { argMap, target.argIndices });
        }
        final AbstractInsnNode insn = this.invokeHandlerWithArgs(this.methodArgs, insns, argMap);
        target.replaceNode((AbstractInsnNode)methodNode, insn, insns);
        target.addToLocals(extraLocals);
        target.addToStack(extraStack);
    }
    
    private void injectAtFieldAccess(final Target target, final InjectionNodes.InjectionNode node) {
        final FieldInsnNode fieldNode = (FieldInsnNode)node.getCurrentTarget();
        final int opCode = fieldNode.getOpcode();
        final boolean staticField = opCode == 178 || opCode == 179;
        final Type ownerType = Type.getType("L" + fieldNode.owner + ";");
        final Type fieldType = Type.getType(fieldNode.desc);
        AbstractInsnNode invoke = null;
        final InsnList insns = new InsnList();
        if (opCode == 178 || opCode == 180) {
            invoke = this.injectAtGetField(insns, target, fieldNode, staticField, ownerType, fieldType);
        }
        else {
            if (opCode != 179 && opCode != 181) {
                throw new InvalidInjectionException(this.info, "Unspported opcode " + opCode + " on FieldInsnNode for " + this.info);
            }
            invoke = this.injectAtPutField(insns, target, fieldNode, staticField, ownerType, fieldType);
        }
        target.replaceNode((AbstractInsnNode)fieldNode, invoke, insns);
    }
    
    private AbstractInsnNode injectAtGetField(final InsnList insns, final Target target, final FieldInsnNode node, final boolean staticField, final Type owner, final Type fieldType) {
        final String handlerDesc = staticField ? ASMHelper.generateDescriptor(fieldType, new Object[0]) : ASMHelper.generateDescriptor(fieldType, owner);
        final boolean withArgs = this.checkDescriptor(handlerDesc, target, "getter");
        if (!this.isStatic) {
            insns.add((AbstractInsnNode)new VarInsnNode(25, 0));
            if (!staticField) {
                insns.add((AbstractInsnNode)new InsnNode(95));
            }
        }
        if (withArgs) {
            this.pushArgs(target.arguments, insns, target.argIndices, 0, target.arguments.length);
            target.addToStack(ASMHelper.getArgsSize(target.arguments));
        }
        target.addToStack(this.isStatic ? 0 : 1);
        return this.invokeHandler(insns);
    }
    
    private AbstractInsnNode injectAtPutField(final InsnList insns, final Target target, final FieldInsnNode node, final boolean staticField, final Type owner, final Type fieldType) {
        final String handlerDesc = staticField ? ASMHelper.generateDescriptor(null, fieldType) : ASMHelper.generateDescriptor(null, owner, fieldType);
        final boolean withArgs = this.checkDescriptor(handlerDesc, target, "setter");
        if (!this.isStatic) {
            if (staticField) {
                insns.add((AbstractInsnNode)new VarInsnNode(25, 0));
                insns.add((AbstractInsnNode)new InsnNode(95));
            }
            else {
                final int marshallVar = target.allocateLocals(fieldType.getSize());
                insns.add((AbstractInsnNode)new VarInsnNode(fieldType.getOpcode(54), marshallVar));
                insns.add((AbstractInsnNode)new VarInsnNode(25, 0));
                insns.add((AbstractInsnNode)new InsnNode(95));
                insns.add((AbstractInsnNode)new VarInsnNode(fieldType.getOpcode(21), marshallVar));
            }
        }
        if (withArgs) {
            this.pushArgs(target.arguments, insns, target.argIndices, 0, target.arguments.length);
            target.addToStack(ASMHelper.getArgsSize(target.arguments));
        }
        target.addToStack((!this.isStatic && !staticField) ? 1 : 0);
        return this.invokeHandler(insns);
    }
    
    protected boolean checkDescriptor(final String desc, final Target target, final String type) {
        if (this.methodNode.desc.equals(desc)) {
            return false;
        }
        final int pos = desc.indexOf(41);
        final String alternateDesc = String.format("%s%s%s", desc.substring(0, pos), Joiner.on("").join((Object[])target.arguments), desc.substring(pos));
        if (this.methodNode.desc.equals(alternateDesc)) {
            return true;
        }
        throw new InvalidInjectionException(this.info, this.annotationType + " method " + type + " " + this + " has an invalid signature. Expected " + desc + " but found " + this.methodNode.desc);
    }
    
    protected void injectAtConstructor(final Target target, final InjectionNodes.InjectionNode node) {
        final ConstructorRedirectData meta = (ConstructorRedirectData)node.getDecoration("ctor");
        final boolean wildcard = (boolean)node.getDecoration("wildcard");
        final TypeInsnNode newNode = (TypeInsnNode)node.getCurrentTarget();
        final AbstractInsnNode dupNode = target.get(target.indexOf((AbstractInsnNode)newNode) + 1);
        final MethodInsnNode initNode = this.findInitNode(target, newNode);
        if (initNode != null) {
            final boolean isAssigned = dupNode.getOpcode() == 89;
            final String desc = initNode.desc.replace(")V", ")L" + newNode.desc + ";");
            boolean withArgs = false;
            try {
                withArgs = this.checkDescriptor(desc, target, "constructor");
            }
            catch (InvalidInjectionException ex) {
                if (!wildcard) {
                    throw ex;
                }
                return;
            }
            if (isAssigned) {
                target.removeNode(dupNode);
            }
            if (this.isStatic) {
                target.removeNode((AbstractInsnNode)newNode);
            }
            else {
                target.replaceNode((AbstractInsnNode)newNode, (AbstractInsnNode)new VarInsnNode(25, 0));
            }
            final InsnList insns = new InsnList();
            if (withArgs) {
                this.pushArgs(target.arguments, insns, target.argIndices, 0, target.arguments.length);
                target.addToStack(ASMHelper.getArgsSize(target.arguments));
            }
            this.invokeHandler(insns);
            if (isAssigned) {
                final LabelNode nullCheckSucceeded = new LabelNode();
                insns.add((AbstractInsnNode)new InsnNode(89));
                insns.add((AbstractInsnNode)new JumpInsnNode(199, nullCheckSucceeded));
                this.throwException(insns, "java/lang/NullPointerException", this.annotationType + " constructor handler " + this + " returned null for " + newNode.desc.replace('/', '.'));
                insns.add((AbstractInsnNode)nullCheckSucceeded);
                target.addToStack(1);
            }
            else {
                insns.add((AbstractInsnNode)new InsnNode(87));
            }
            target.replaceNode((AbstractInsnNode)initNode, insns);
            final ConstructorRedirectData constructorRedirectData = meta;
            ++constructorRedirectData.injected;
            return;
        }
        if (!wildcard) {
            throw new InvalidInjectionException(this.info, this.annotationType + " ctor invocation was not found in " + target);
        }
    }
    
    protected MethodInsnNode findInitNode(final Target target, final TypeInsnNode newNode) {
        final int indexOf = target.indexOf((AbstractInsnNode)newNode);
        final Iterator<AbstractInsnNode> iter = (Iterator<AbstractInsnNode>)target.insns.iterator(indexOf);
        while (iter.hasNext()) {
            final AbstractInsnNode insn = iter.next();
            if (insn instanceof MethodInsnNode && insn.getOpcode() == 183) {
                final MethodInsnNode methodNode = (MethodInsnNode)insn;
                if ("<init>".equals(methodNode.name) && methodNode.owner.equals(newNode.desc)) {
                    return methodNode;
                }
                continue;
            }
        }
        return null;
    }
    
    class Meta
    {
        public static final String KEY = "redirector";
        final int priority;
        final boolean isFinal;
        final String name;
        final String desc;
        
        public Meta(final int priority, final boolean isFinal, final String name, final String desc) {
            this.priority = priority;
            this.isFinal = isFinal;
            this.name = name;
            this.desc = desc;
        }
        
        RedirectInjector getOwner() {
            return RedirectInjector.this;
        }
    }
    
    class ConstructorRedirectData
    {
        public static final String KEY = "ctor";
        public int injected;
        
        ConstructorRedirectData() {
            this.injected = 0;
        }
    }
}
