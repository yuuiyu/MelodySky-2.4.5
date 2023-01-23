//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import xyz.Melody.GUI.Font.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import xyz.Melody.Event.*;
import java.awt.*;
import org.lwjgl.input.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.client.settings.*;

public class KeyStrokes extends HUDApi
{
    int lastA;
    int lastW;
    int lastS;
    int lastD;
    double lastX;
    double lastZ;
    CFontRenderer ufr;
    
    public KeyStrokes() {
        super("KeyStrokes", 66, 247);
        this.lastA = 0;
        this.lastW = 0;
        this.lastS = 0;
        this.lastD = 0;
        this.lastX = 0.0;
        this.lastZ = 0.0;
        this.ufr = FontLoaders.NMSL19;
        this.setEnabled(true);
    }
    
    @EventHandler
    public void onRender(final EventRender2D event) {
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        this.keyRender();
    }
    
    @Override
    public void InScreenRender() {
        this.keyRender();
    }
    
    private void keyRender() {
        final GameSettings set = this.mc.gameSettings;
        final int press = new Color(230, 230, 230, 120).getRGB();
        final int unPress = new Color(30, 30, 30, 120).getRGB();
        final int colorW = set.keyBindForward.isKeyDown() ? press : unPress;
        final int colorS = set.keyBindBack.isKeyDown() ? press : unPress;
        final int colorA = set.keyBindLeft.isKeyDown() ? press : unPress;
        final int colorD = set.keyBindRight.isKeyDown() ? press : unPress;
        final int colorSpace = set.keyBindJump.isKeyDown() ? press : unPress;
        final int colorL = Mouse.isButtonDown(0) ? press : unPress;
        final int colorR = Mouse.isButtonDown(1) ? press : unPress;
        final int colorW2 = set.keyBindForward.isKeyDown() ? unPress : press;
        final int colorS2 = set.keyBindBack.isKeyDown() ? unPress : press;
        final int colorA2 = set.keyBindLeft.isKeyDown() ? unPress : press;
        final int colorD2 = set.keyBindRight.isKeyDown() ? unPress : press;
        final int colorSpace2 = set.keyBindJump.isKeyDown() ? unPress : press;
        final int colorLMB = Mouse.isButtonDown(0) ? unPress : press;
        final int colorRMB = Mouse.isButtonDown(1) ? unPress : press;
        RenderUtil.drawFastRoundedRect((float)this.x, (float)this.y, (float)(this.x + 25), (float)(this.y + 25), 2.0f, colorW);
        FontLoaders.NMSL20.drawString("W", (float)(this.x + 8), (float)(this.y + 9), colorW2);
        RenderUtil.drawFastRoundedRect((float)this.x, (float)(this.y + 29), (float)(this.x + 25), (float)(this.y + 54), 2.0f, colorS);
        FontLoaders.NMSL20.drawString("S", (float)(this.x + 10), (float)(this.y + 32 + 6), colorS2);
        RenderUtil.drawFastRoundedRect((float)(this.x - 29), (float)(this.y + 29), (float)(this.x - 4), (float)(this.y + 54), 2.0f, colorA);
        FontLoaders.NMSL20.drawString("A", this.x - 32 + 12.5f, (float)(this.y + 32 + 6), colorA2);
        RenderUtil.drawFastRoundedRect((float)(this.x + 29), (float)(this.y + 29), (float)(this.x + 54), (float)(this.y + 54), 2.0f, colorD);
        FontLoaders.NMSL20.drawString("D", (float)(this.x + 32 + 6), (float)(this.y + 32 + 6), colorD2);
        RenderUtil.drawFastRoundedRect((float)(this.x - 29), (float)(this.y + 58), (float)(this.x + 11), (float)(this.y + 79), 2.0f, colorL);
        FontLoaders.NMSL20.drawString("LMB", (float)(this.x - 32 + 13), (float)(this.y + 64 + 1), colorLMB);
        RenderUtil.drawFastRoundedRect((float)(this.x + 15), (float)(this.y + 58), (float)(this.x + 54), (float)(this.y + 79), 2.0f, colorR);
        FontLoaders.NMSL20.drawString("RMB", (float)(this.x + 18 + 6), (float)(this.y + 64 + 1), colorRMB);
        RenderUtil.drawFastRoundedRect((float)(this.x - 29), (float)(this.y + 81), (float)(this.x + 54), (float)(this.y + 94), 2.0f, colorSpace);
        FontLoaders.NMSL20.drawString("-----", (float)(this.x - 29 + 31), (float)(this.y + 83), colorSpace2);
    }
    
    public int flop(final int a, final int b) {
        return b - a;
    }
}
