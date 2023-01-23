//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.*;

public class FullBright extends Module
{
    private float old;
    
    public FullBright() {
        super("FullBright", new String[] { "fbright", "brightness", "bright" }, ModuleType.Render);
        this.setColor(new Color(244, 255, 149).getRGB());
        this.setModInfo("Night Vision.");
    }
    
    public void onEnable() {
        this.old = this.mc.gameSettings.gammaSetting;
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        this.mc.gameSettings.gammaSetting = 1.5999999E7f;
    }
    
    public void onDisable() {
        this.mc.gameSettings.gammaSetting = this.old;
    }
}
