//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.tree.*;

@InjectionPoint.AtCode("FIELD")
public class BeforeFieldAccess extends BeforeInvoke
{
    private final int opcode;
    
    public BeforeFieldAccess(final InjectionPointData data) {
        super(data);
        this.opcode = data.getOpcode(-1, 180, 181, 178, 179, -1);
    }
    
    @Override
    protected boolean matchesInsn(final AbstractInsnNode insn) {
        return insn instanceof FieldInsnNode && (((FieldInsnNode)insn).getOpcode() == this.opcode || this.opcode == -1);
    }
}
