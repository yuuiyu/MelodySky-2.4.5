//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import xyz.Melody.Event.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;

public class Day extends HUDApi
{
    private float xxx;
    
    public Day() {
        super("DayCounter", 300, 200);
        this.xxx = 0.0f;
        this.setEnabled(true);
    }
    
    @EventHandler
    public void onRender(final EventRender2D event) {
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        this.DAY();
    }
    
    @Override
    public void InScreenRender() {
        this.DAY();
    }
    
    private void DAY() {
        final int c2 = new Color(30, 30, 30, 100).getRGB();
        final CFontRenderer font = FontLoaders.NMSL20;
        RenderUtil.drawFastRoundedRect((float)this.x, (float)this.y, (float)(this.x + font.getStringWidth("Day " + (Object)(this.mc.theWorld.getWorldTime() / 24000L)) + 8), (float)(this.y + font.getStringHeight("Day " + (Object)(this.mc.theWorld.getWorldTime() / 24000L)) + 6), 1.0f, c2);
        FontLoaders.NMSL20.drawString("Day " + (Object)(this.mc.theWorld.getWorldTime() / 24000L), (float)(this.x + 4), (float)(this.y + 4), -1);
    }
}
