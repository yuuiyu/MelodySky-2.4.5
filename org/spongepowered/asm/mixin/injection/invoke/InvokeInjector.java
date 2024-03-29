//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.invoke;

import org.spongepowered.asm.mixin.injection.code.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import java.util.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.tree.*;

public abstract class InvokeInjector extends Injector
{
    protected final String annotationType;
    
    public InvokeInjector(final InjectionInfo info, final String annotationType) {
        super(info);
        this.annotationType = annotationType;
    }
    
    protected void sanityCheck(final Target target, final List<InjectionPoint> injectionPoints) {
        super.sanityCheck(target, (List)injectionPoints);
        this.checkTarget(target);
    }
    
    protected void checkTarget(final Target target) {
        if (target.isStatic != this.isStatic) {
            throw new InvalidInjectionException(this.info, "'static' modifier of callback method does not match target in " + this);
        }
    }
    
    protected void inject(final Target target, final InjectionNodes.InjectionNode node) {
        if (!(node.getCurrentTarget() instanceof MethodInsnNode)) {
            throw new InvalidInjectionException(this.info, this.annotationType + " annotation on is targetting a non-method insn in " + target + " in " + this);
        }
        this.injectAtInvoke(target, node);
    }
    
    protected abstract void injectAtInvoke(final Target p0, final InjectionNodes.InjectionNode p1);
    
    protected AbstractInsnNode invokeHandlerWithArgs(final Type[] args, final InsnList insns, final int[] argMap) {
        return this.invokeHandlerWithArgs(args, insns, argMap, 0, args.length);
    }
    
    protected AbstractInsnNode invokeHandlerWithArgs(final Type[] args, final InsnList insns, final int[] argMap, final int startArg, final int endArg) {
        if (!this.isStatic) {
            insns.add((AbstractInsnNode)new VarInsnNode(25, 0));
        }
        this.pushArgs(args, insns, argMap, startArg, endArg);
        return this.invokeHandler(insns);
    }
    
    protected int[] storeArgs(final Target target, final Type[] args, final InsnList insns, final int start) {
        final int[] argMap = target.generateArgMap(args, start);
        this.storeArgs(args, insns, argMap, start, args.length);
        return argMap;
    }
    
    protected void storeArgs(final Type[] args, final InsnList insns, final int[] argMap, final int start, final int end) {
        for (int arg = end - 1; arg >= start; --arg) {
            insns.add((AbstractInsnNode)new VarInsnNode(args[arg].getOpcode(54), argMap[arg]));
        }
    }
    
    protected void pushArgs(final Type[] args, final InsnList insns, final int[] argMap, final int start, final int end) {
        for (int arg = start; arg < end; ++arg) {
            insns.add((AbstractInsnNode)new VarInsnNode(args[arg].getOpcode(21), argMap[arg]));
        }
    }
}
