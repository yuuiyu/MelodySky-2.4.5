//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.packets;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ S12PacketEntityVelocity.class })
public interface S12Accessor
{
    @Accessor("motionX")
    void setMotionX(final int p0);
    
    @Accessor("motionY")
    void setMotionY(final int p0);
    
    @Accessor("motionZ")
    void setMotionZ(final int p0);
}
