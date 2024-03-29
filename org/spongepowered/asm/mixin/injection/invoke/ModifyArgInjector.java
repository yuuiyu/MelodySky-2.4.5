//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.invoke;

import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;
import org.spongepowered.asm.mixin.injection.code.*;

public class ModifyArgInjector extends InvokeInjector
{
    private final int index;
    private final boolean singleArgMode;
    
    public ModifyArgInjector(final InjectionInfo info, final int index) {
        super(info, "@ModifyArg");
        this.index = index;
        this.singleArgMode = (this.methodArgs.length == 1);
    }
    
    protected void sanityCheck(final Target target, final List<InjectionPoint> injectionPoints) {
        super.sanityCheck(target, (List)injectionPoints);
        if (this.singleArgMode && !this.methodArgs[0].equals((Object)this.returnType)) {
            throw new InvalidInjectionException(this.info, "@ModifyArg return type on " + this + " must match the parameter type." + " ARG=" + this.methodArgs[0] + " RETURN=" + this.returnType);
        }
    }
    
    protected void checkTarget(final Target target) {
        if (!this.isStatic && target.isStatic) {
            throw new InvalidInjectionException(this.info, "non-static callback method " + this + " targets a static method which is not supported");
        }
    }
    
    protected void injectAtInvoke(final Target target, final InjectionNodes.InjectionNode node) {
        final MethodInsnNode methodNode = (MethodInsnNode)node.getCurrentTarget();
        final Type[] args = Type.getArgumentTypes(methodNode.desc);
        final int argIndex = this.findArgIndex(target, args);
        final InsnList insns = new InsnList();
        int extraLocals = 0;
        if (this.singleArgMode) {
            extraLocals = this.injectSingleArgHandler(target, args, argIndex, insns);
        }
        else {
            extraLocals = this.injectMultiArgHandler(target, args, argIndex, insns);
        }
        target.insns.insertBefore((AbstractInsnNode)methodNode, insns);
        target.addToLocals(extraLocals);
        target.addToStack(2 - (extraLocals - 1));
    }
    
    private int injectSingleArgHandler(final Target target, final Type[] args, final int argIndex, final InsnList insns) {
        final int[] argMap = this.storeArgs(target, args, insns, argIndex);
        this.invokeHandlerWithArgs(args, insns, argMap, argIndex, argIndex + 1);
        this.pushArgs(args, insns, argMap, argIndex + 1, args.length);
        return argMap[argMap.length - 1] - target.getMaxLocals() + args[args.length - 1].getSize();
    }
    
    private int injectMultiArgHandler(final Target target, final Type[] args, final int argIndex, final InsnList insns) {
        if (!Arrays.equals(args, this.methodArgs)) {
            throw new InvalidInjectionException(this.info, "@ModifyArg method " + this + " targets a method with an invalid signature " + Injector.printArgs(args) + ", expected " + Injector.printArgs(this.methodArgs));
        }
        final int[] argMap = this.storeArgs(target, args, insns, 0);
        this.pushArgs(args, insns, argMap, 0, argIndex);
        this.invokeHandlerWithArgs(args, insns, argMap, 0, args.length);
        this.pushArgs(args, insns, argMap, argIndex + 1, args.length);
        return argMap[argMap.length - 1] - target.getMaxLocals() + args[args.length - 1].getSize();
    }
    
    protected int findArgIndex(final Target target, final Type[] args) {
        if (this.index > -1) {
            if (this.index >= args.length || !args[this.index].equals((Object)this.returnType)) {
                throw new InvalidInjectionException(this.info, "Specified index " + this.index + " for @ModifyArg is invalid for args " + Injector.printArgs(args) + ", expected " + this.returnType + " on " + this);
            }
            return this.index;
        }
        else {
            int argIndex = -1;
            for (int arg = 0; arg < args.length; ++arg) {
                if (args[arg].equals((Object)this.returnType)) {
                    if (argIndex != -1) {
                        throw new InvalidInjectionException(this.info, "Found duplicate args with index [" + argIndex + ", " + arg + "] matching type " + this.returnType + " for @ModifyArg target " + target + " in " + this + ". Please specify index of desired arg.");
                    }
                    argIndex = arg;
                }
            }
            if (argIndex == -1) {
                throw new InvalidInjectionException(this.info, "Could not find arg matching type " + this.returnType + " for @ModifyArg target " + target + " in " + this);
            }
            return argIndex;
        }
    }
}
