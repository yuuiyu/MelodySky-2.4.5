//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.util.*;

@Mixin({ Minecraft.class })
public interface MCA
{
    @Accessor("session")
    void setSession(final Session p0);
    
    @Accessor("timer")
    Timer getTimer();
}
