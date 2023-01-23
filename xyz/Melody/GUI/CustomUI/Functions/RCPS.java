//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import xyz.Melody.Event.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;

public class RCPS extends HUDApi
{
    private ArrayList<Long> clicks;
    
    public RCPS() {
        super("RCPS", 5, 30);
        this.clicks = new ArrayList<Long>();
        this.setEnabled(true);
    }
    
    @SubscribeEvent
    public void onClick(final InputEvent.MouseInputEvent event) {
        if (Mouse.getEventButtonState() && this.mc.gameSettings.keyBindUseItem.isKeyDown() && Mouse.getEventButton() == this.mc.gameSettings.keyBindUseItem.getKeyCode() + 100) {
            this.clicks.add(System.currentTimeMillis());
        }
    }
    
    @EventHandler
    public void onRender(final EventRender2D event) {
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        this.cpsRender();
    }
    
    @Override
    public void InScreenRender() {
        this.cpsRender();
    }
    
    private void cpsRender() {
        final int c2 = new Color(30, 30, 30, 100).getRGB();
        final CFontRenderer font = FontLoaders.NMSL20;
        RenderUtil.drawFastRoundedRect((float)this.x, (float)this.y, (float)(this.x + font.getStringWidth("RCPS: " + this.getRightCPS()) + 8), (float)(this.y + font.getStringHeight("RCPS: " + this.getRightCPS()) + 6), 1.0f, c2);
        FontLoaders.NMSL20.drawString("RCPS: " + this.getRightCPS(), (float)(this.x + 4), (float)(this.y + 4), -1);
    }
    
    public int getRightCPS() {
        final long curTime = System.currentTimeMillis();
        this.clicks.removeIf(e -> e + 1000L < curTime);
        return this.clicks.size();
    }
}
