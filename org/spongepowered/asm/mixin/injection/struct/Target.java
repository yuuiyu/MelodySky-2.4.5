//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import java.util.*;

public class Target implements Comparable<Target>, Iterable<AbstractInsnNode>
{
    public final ClassNode classNode;
    public final MethodNode method;
    public final InsnList insns;
    public final boolean isStatic;
    public final Type[] arguments;
    public final int[] argIndices;
    public final Type returnType;
    public final String callbackDescriptor;
    public final String callbackInfoClass;
    public final InjectionNodes injectionNodes;
    private final int maxStack;
    private final int maxLocals;
    
    public Target(final ClassNode classNode, final MethodNode method) {
        this.injectionNodes = new InjectionNodes();
        this.classNode = classNode;
        this.method = method;
        this.insns = method.instructions;
        this.isStatic = ASMHelper.methodIsStatic(method);
        this.arguments = Type.getArgumentTypes(method.desc);
        this.argIndices = this.calcArgIndices(this.isStatic ? 0 : 1);
        this.returnType = Type.getReturnType(method.desc);
        this.maxStack = method.maxStack;
        this.maxLocals = method.maxLocals;
        this.callbackInfoClass = CallbackInfo.getCallInfoClassName(this.returnType);
        this.callbackDescriptor = String.format("(%sL%s;)V", method.desc.substring(1, method.desc.indexOf(41)), this.callbackInfoClass);
    }
    
    public int getMaxLocals() {
        return this.maxLocals;
    }
    
    public int getMaxStack() {
        return this.maxStack;
    }
    
    public int getCurrentMaxLocals() {
        return this.method.maxLocals;
    }
    
    public int getCurrentMaxStack() {
        return this.method.maxStack;
    }
    
    public int allocateLocal() {
        return this.allocateLocals(1);
    }
    
    public int allocateLocals(final int locals) {
        final int nextLocal = this.method.maxLocals;
        final MethodNode method = this.method;
        method.maxLocals += locals;
        return nextLocal;
    }
    
    public void addToLocals(final int locals) {
        this.setMaxLocals(this.maxLocals + locals);
    }
    
    public void setMaxLocals(final int maxLocals) {
        if (maxLocals > this.method.maxLocals) {
            this.method.maxLocals = maxLocals;
        }
    }
    
    public void addToStack(final int stack) {
        this.setMaxStack(this.maxStack + stack);
    }
    
    public void setMaxStack(final int maxStack) {
        if (maxStack > this.method.maxStack) {
            this.method.maxStack = maxStack;
        }
    }
    
    public int[] generateArgMap(final Type[] args, final int start) {
        int local = this.maxLocals;
        final int[] argMap = new int[args.length];
        for (int arg = start; arg < args.length; ++arg) {
            argMap[arg] = local;
            local += args[arg].getSize();
        }
        return argMap;
    }
    
    private int[] calcArgIndices(int local) {
        final int[] argIndices = new int[this.arguments.length];
        for (int arg = 0; arg < this.arguments.length; ++arg) {
            argIndices[arg] = local;
            local += this.arguments[arg].getSize();
        }
        return argIndices;
    }
    
    public String getSimpleCallbackDescriptor() {
        return String.format("(L%s;)V", this.callbackInfoClass);
    }
    
    public String getCallbackDescriptor(final Type[] locals, final Type[] argumentTypes) {
        return this.getCallbackDescriptor(false, locals, argumentTypes, 0, 32767);
    }
    
    public String getCallbackDescriptor(final boolean captureLocals, final Type[] locals, final Type[] argumentTypes, final int startIndex, int extra) {
        if (!captureLocals || locals == null) {
            return this.callbackDescriptor;
        }
        String descriptor = this.callbackDescriptor.substring(0, this.callbackDescriptor.indexOf(41));
        for (int l = startIndex; l < locals.length && extra > 0; ++l) {
            if (locals[l] != null) {
                descriptor += locals[l].getDescriptor();
                --extra;
            }
        }
        return descriptor + ")V";
    }
    
    @Override
    public String toString() {
        return String.format("%s::%s%s", this.classNode.name, this.method.name, this.method.desc);
    }
    
    @Override
    public int compareTo(final Target o) {
        if (o == null) {
            return Integer.MAX_VALUE;
        }
        return this.toString().compareTo(o.toString());
    }
    
    public int indexOf(final AbstractInsnNode insn) {
        return this.insns.indexOf(insn);
    }
    
    public AbstractInsnNode get(final int index) {
        return this.insns.get(index);
    }
    
    @Override
    public Iterator<AbstractInsnNode> iterator() {
        return (Iterator<AbstractInsnNode>)this.insns.iterator();
    }
    
    public void replaceNode(final AbstractInsnNode location, final AbstractInsnNode insn) {
        this.insns.insertBefore(location, insn);
        this.insns.remove(location);
        this.injectionNodes.replace(location, insn);
    }
    
    public void replaceNode(final AbstractInsnNode location, final AbstractInsnNode champion, final InsnList insns) {
        this.insns.insertBefore(location, insns);
        this.insns.remove(location);
        this.injectionNodes.replace(location, champion);
    }
    
    public void wrapNode(final AbstractInsnNode location, final AbstractInsnNode champion, final InsnList before, final InsnList after) {
        this.insns.insertBefore(location, before);
        this.insns.insert(location, after);
        this.injectionNodes.replace(location, champion);
    }
    
    public void replaceNode(final AbstractInsnNode location, final InsnList insns) {
        this.insns.insertBefore(location, insns);
        this.removeNode(location);
    }
    
    public void removeNode(final AbstractInsnNode insn) {
        this.insns.remove(insn);
        this.injectionNodes.remove(insn);
    }
}
