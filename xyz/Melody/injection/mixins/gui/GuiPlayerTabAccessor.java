//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ GuiPlayerTabOverlay.class })
public interface GuiPlayerTabAccessor
{
    @Accessor("footer")
    IChatComponent getFooter();
    
    @Accessor("header")
    IChatComponent getHeader();
}
