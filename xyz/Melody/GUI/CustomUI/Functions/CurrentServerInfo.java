//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import xyz.Melody.Event.*;
import java.awt.*;
import xyz.Melody.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.entity.player.*;
import xyz.Melody.GUI.Font.*;
import java.util.*;

public class CurrentServerInfo extends HUDApi
{
    public CurrentServerInfo() {
        super("Area", 5, 50);
        this.setEnabled(true);
    }
    
    @EventHandler
    public void onRender(final EventRender2D event) {
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        this.render();
    }
    
    @Override
    public void InScreenRender() {
        this.render();
    }
    
    private void render() {
        final int c2 = new Color(30, 30, 30, 100).getRGB();
        if (!Client.inSkyblock) {
            return;
        }
        final CFontRenderer font = FontLoaders.NMSL20;
        if (!Client.inDungeons) {
            RenderUtil.drawFastRoundedRect((float)this.x, (float)this.y, (float)(this.x + font.getStringWidth("Area: " + Client.instance.sbArea.getCurrentArea()) + 8), (float)(this.y + 10 + 6), 1.0f, c2);
            FontLoaders.NMSL20.drawString("Area: " + Client.instance.sbArea.getCurrentArea(), (float)(this.x + 4), (float)(this.y + 4), -1);
        }
        else if (Client.inDungeons) {
            if (Client.inDungeons) {
                int row = 0;
                FontLoaders.NMSL18.drawString("Score: " + Client.instance.dungeonUtils.score, (float)(this.x + 3), (float)(this.y + 3), -1);
                FontLoaders.NMSL18.drawString("Mimic: " + Client.instance.dungeonUtils.foundMimic, (float)(this.x + 4), (float)(this.y + 13), -1);
                FontLoaders.NMSL18.drawString("Secrets Found:" + Client.instance.dungeonUtils.secretsFound, (float)(this.x + 3), (float)(this.y + 23), -1);
                FontLoaders.NMSL18.drawString("Crypts:" + Client.instance.dungeonUtils.cryptsFound, (float)(this.x + 3), (float)(this.y + 33), -1);
                FontLoaders.NMSL18.drawString("Deaths:" + Client.instance.dungeonUtils.deaths, (float)(this.x + 3), (float)(this.y + 43), -1);
                FontLoaders.NMSL18.drawString("Teams:", (float)(this.x + 3), (float)(this.y + 53), -1);
                for (final EntityPlayer teammate : Client.instance.dungeonUtils.teammates) {
                    FontLoaders.NMSL18.drawString(" - " + teammate.getName(), (float)(this.x + 3), (float)(this.y + 63 + row * 10), -1);
                    ++row;
                }
                FontLoaders.NMSL18.drawString("Floor: " + Client.instance.dungeonUtils.floor.name(), (float)(this.x + 3), (float)(this.y + 63 + row * 10), -1);
                FontLoaders.NMSL18.drawString("Master: " + Client.isMMD, (float)(this.x + 3), (float)(this.y + 73 + row * 10), -1);
                FontLoaders.NMSL18.drawString("In Boss: " + Client.instance.dungeonUtils.inBoss, (float)(this.x + 3), (float)(this.y + 83 + row * 10), -1);
            }
            else {
                FontLoaders.NMSL20.drawString("Unexpected Error.", (float)(this.x + 4), (float)(this.y + 4), -1);
            }
        }
    }
}
