//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import xyz.Melody.Event.*;
import net.minecraft.client.gui.*;
import xyz.Melody.*;
import java.awt.*;

public class BigRat extends HUDApi
{
    private float xxx;
    
    public BigRat() {
        super("RainbowRat", 10, 200);
        this.xxx = 0.0f;
        this.setEnabled(true);
    }
    
    @EventHandler
    public void onRender(final EventRender2D event) {
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        this.RAT();
    }
    
    @Override
    public void InScreenRender() {
        this.RAT();
    }
    
    private void RAT() {
        final float width = (float)new ScaledResolution(this.mc).getScaledWidth();
        if (this.xxx < width) {
            ++this.xxx;
        }
        if (this.xxx >= width) {
            this.xxx = -200.0f;
        }
        if (this.mc.currentScreen instanceof HUDScreen) {
            this.xxx = 0.0f;
        }
        this.mc.fontRendererObj.drawString("§l" + Client.instance.rat[0], (int)this.xxx, this.y + 2, this.rainbow());
        this.mc.fontRendererObj.drawString("§l" + Client.instance.rat[1], (int)this.xxx, this.y + 14, this.rainbow());
        this.mc.fontRendererObj.drawString("§l" + Client.instance.rat[2], (int)this.xxx, this.y + 26, this.rainbow());
        this.mc.fontRendererObj.drawString("§l" + Client.instance.rat[3], (int)this.xxx, this.y + 38, this.rainbow());
    }
    
    private int rainbow() {
        final float hue = System.currentTimeMillis() % 3000L / 3000.0f;
        return Color.getHSBColor(hue, 0.75f, 1.0f).getRGB();
    }
}
