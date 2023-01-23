//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import xyz.Melody.Event.*;
import java.awt.*;
import net.minecraft.client.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;

public class FPS extends HUDApi
{
    public FPS() {
        super("FPS", 5, 90);
        this.setEnabled(true);
    }
    
    @EventHandler
    public void onRender(final EventRender2D event) {
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        this.fpsRender();
    }
    
    @Override
    public void InScreenRender() {
        this.fpsRender();
    }
    
    private void fpsRender() {
        final int c2 = new Color(30, 30, 30, 100).getRGB();
        final CFontRenderer font = FontLoaders.NMSL20;
        RenderUtil.drawFastRoundedRect((float)this.x, (float)this.y, (float)(this.x + font.getStringWidth("FPS: " + Minecraft.getDebugFPS()) + 8), (float)(this.y + font.getStringHeight("FPS: " + Minecraft.getDebugFPS()) + 6), 1.0f, c2);
        FontLoaders.NMSL20.drawString("FPS: " + Minecraft.getDebugFPS(), (float)(this.x + 4), (float)(this.y + 4), -1);
    }
}
