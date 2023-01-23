//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.entity;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ EntityLookHelper.class })
public interface EntityLookHelperAccessor
{
    @Accessor("entity")
    EntityLiving getEntity();
    
    @Accessor("deltaLookYaw")
    float getDeltaLookYaw();
    
    @Accessor("deltaLookYaw")
    void setDeltaLookYaw(final float p0);
    
    @Accessor("deltaLookPitch")
    float getDeltaLookPitch();
    
    @Accessor("deltaLookPitch")
    void setDeltaLookPitch(final float p0);
    
    @Accessor("isLooking")
    boolean isLooking();
    
    @Accessor("isLooking")
    void setLooking(final boolean p0);
    
    @Accessor("posX")
    double getPosX();
    
    @Accessor("posX")
    void setPosX(final double p0);
    
    @Accessor("posY")
    double getPosY();
    
    @Accessor("posY")
    void setPosY(final double p0);
    
    @Accessor("posZ")
    double getPosZ();
    
    @Accessor("posZ")
    void setPosZ(final double p0);
}
