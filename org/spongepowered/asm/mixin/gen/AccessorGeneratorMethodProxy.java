//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.lib.tree.*;

public class AccessorGeneratorMethodProxy extends AccessorGenerator
{
    private final MethodNode targetMethod;
    private final Type[] argTypes;
    private final Type returnType;
    private final boolean isInstanceMethod;
    
    public AccessorGeneratorMethodProxy(final AccessorInfo info) {
        super(info);
        this.targetMethod = info.getTargetMethod();
        this.argTypes = info.getArgTypes();
        this.returnType = info.getReturnType();
        this.isInstanceMethod = !ASMHelper.hasFlag(this.targetMethod, 8);
    }
    
    public MethodNode generate() {
        final int size = ASMHelper.getArgsSize(this.argTypes) + this.returnType.getSize();
        final MethodNode method = this.createMethod(size, size);
        if (this.isInstanceMethod) {
            method.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
        }
        ASMHelper.loadArgs(this.argTypes, method.instructions, this.isInstanceMethod ? 1 : 0);
        final boolean isPrivate = ASMHelper.hasFlag(this.targetMethod, 2);
        final int opcode = this.isInstanceMethod ? (isPrivate ? 183 : 182) : 184;
        method.instructions.add((AbstractInsnNode)new MethodInsnNode(opcode, this.info.getClassNode().name, this.targetMethod.name, this.targetMethod.desc, false));
        method.instructions.add((AbstractInsnNode)new InsnNode(this.returnType.getOpcode(172)));
        return method;
    }
}
