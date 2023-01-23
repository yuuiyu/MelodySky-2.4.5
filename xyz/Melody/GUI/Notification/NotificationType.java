//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Notification;

import net.minecraft.util.*;
import xyz.Melody.Utils.*;

public enum NotificationType
{
    SUCCESS(Colors.GREEN.c, new ResourceLocation("Melody/noti/success.png")), 
    INFO(Colors.GRAY.c, new ResourceLocation("Melody/noti/info.png")), 
    WARN(Colors.YELLOW.c, new ResourceLocation("Melody/noti/warning.png")), 
    ERROR(Colors.RED.c, new ResourceLocation("Melody/noti/error.png"));
    
    private final int color;
    private final ResourceLocation icon;
    
    public ResourceLocation getIcon() {
        return this.icon;
    }
    
    private NotificationType(final int color, final ResourceLocation icon) {
        this.color = color;
        this.icon = icon;
    }
    
    public final int getColor() {
        return this.color;
    }
}
