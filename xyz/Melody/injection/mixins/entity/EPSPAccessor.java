//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.entity;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.player.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ EntityPlayer.class })
public interface EPSPAccessor
{
    @Accessor("itemInUseCount")
    void setItemInUseCount(final int p0);
}
