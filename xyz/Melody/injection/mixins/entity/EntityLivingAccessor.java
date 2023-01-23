//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.entity;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ EntityLiving.class })
public interface EntityLivingAccessor
{
    @Accessor("lookHelper")
    void setLookHelper(final EntityLookHelper p0);
}
