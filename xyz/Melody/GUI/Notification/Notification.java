//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Notification;

import xyz.Melody.Utils.*;
import xyz.Melody.Utils.animate.*;
import xyz.Melody.GUI.Font.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;

public final class Notification
{
    public static final int HEIGHT = 30;
    private final String title;
    private final String content;
    private final int time;
    private final NotificationType type;
    private final TimerUtil timer;
    private final Translate translate;
    private final CFontRenderer fontRenderer;
    public double scissorBoxWidth;
    public boolean showTime;
    
    public Notification(final String title, final String content, final NotificationType type, final CFontRenderer fr, final int time, final boolean showTime) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.type = type;
        this.showTime = showTime;
        this.timer = new TimerUtil();
        this.fontRenderer = fr;
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        this.translate = new Translate(sr.getScaledWidth() - this.getWidth(), (float)(sr.getScaledHeight() - 40));
    }
    
    public Notification(final String title, final String content, final NotificationType type, final CFontRenderer fr, final int time) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.type = type;
        this.showTime = false;
        this.timer = new TimerUtil();
        this.fontRenderer = fr;
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        this.translate = new Translate(sr.getScaledWidth() - this.getWidth(), (float)(sr.getScaledHeight() - 40));
    }
    
    public float getWidth() {
        return (float)Math.max(100, Math.max(this.fontRenderer.getStringWidth(this.title), this.fontRenderer.getStringWidth(this.content)) + 10);
    }
    
    public final String getTitle() {
        return this.title;
    }
    
    public final String getContent() {
        return this.content;
    }
    
    public final int getTime() {
        return this.time;
    }
    
    public final double getDBTime() {
        final double lol = this.time / 1000;
        return lol;
    }
    
    public final NotificationType getType() {
        return this.type;
    }
    
    public final TimerUtil getTimer() {
        return this.timer;
    }
    
    public final Translate getTranslate() {
        return this.translate;
    }
}
