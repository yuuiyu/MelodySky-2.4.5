//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.mixin.refmap.*;
import java.util.*;

@InjectionPoint.AtCode("TAIL")
public class BeforeFinalReturn extends InjectionPoint
{
    private final MixinTargetContext mixin;
    
    public BeforeFinalReturn(final InjectionPointData data) {
        super(data);
        this.mixin = data.getMixin();
    }
    
    public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
        AbstractInsnNode ret = null;
        final int returnOpcode = Type.getReturnType(desc).getOpcode(172);
        for (final AbstractInsnNode insn : insns) {
            if (insn instanceof InsnNode && insn.getOpcode() == returnOpcode) {
                ret = insn;
            }
        }
        if (ret == null) {
            throw new InvalidInjectionException(this.mixin, "TAIL could not locate a valid RETURN in the target method!");
        }
        nodes.add(ret);
        return true;
    }
}
