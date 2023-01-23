//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import java.util.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.Utils.*;
import net.minecraft.util.*;
import xyz.Melody.GUI.Font.*;

public class MiningOverlay extends HUDApi
{
    private ArrayList<Long> clicks;
    private String mithrilPowder;
    private String gemstonePowder;
    private int tickTimer;
    
    public MiningOverlay() {
        super("MiningOverlay", 50, 50);
        this.clicks = new ArrayList<Long>();
        this.mithrilPowder = null;
        this.gemstonePowder = null;
        this.tickTimer = 0;
        this.setEnabled(true);
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        final SkyblockArea area = Client.instance.sbArea;
        if (this.tickTimer <= 20) {
            ++this.tickTimer;
            return;
        }
        if (area.getCurrentArea() != SkyblockArea.Areas.Dwarven_Mines && area.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            this.mithrilPowder = "Failed to Fetch Mithril Powder Info From The Player List.";
            this.gemstonePowder = "Failed to Fetch Gemstone Powder Info From The Player List.";
            return;
        }
        if (PlayerListUtils.tabContains("Mithril Powder:")) {
            (this.mithrilPowder = PlayerListUtils.copyContainsLine("Mithril Powder:")).replaceFirst(" ", "");
        }
        else {
            this.mithrilPowder = "Failed to Fetch Mithril Powder Info From The Player List.";
        }
        if (PlayerListUtils.tabContains("Gemstone Powder:")) {
            (this.gemstonePowder = PlayerListUtils.copyContainsLine("Gemstone Powder:")).replaceFirst(" ", "");
        }
        else {
            this.gemstonePowder = "Failed to Fetch Gemstone Powder Info From The Player List.";
        }
        this.tickTimer = 0;
    }
    
    @EventHandler
    public void onRender(final EventRender2D event) {
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        final SkyblockArea area = Client.instance.sbArea;
        if (area.getCurrentArea() == SkyblockArea.Areas.Dwarven_Mines || area.getCurrentArea() == SkyblockArea.Areas.Crystal_Hollows) {
            this.render();
        }
    }
    
    @Override
    public void InScreenRender() {
        this.render();
    }
    
    private void render() {
        final int c2 = new Color(30, 30, 30, 170).getRGB();
        final CFontRenderer font = FontLoaders.NMSL20;
        final int longest = Math.max(font.getStringWidth(this.mithrilPowder), font.getStringWidth(" Pickaxe CD Ready: " + Client.pickaxeAbilityReady));
        final int finalWidth = Math.max(longest, font.getStringWidth(this.gemstonePowder));
        RenderUtil.drawFastRoundedRect((float)this.x, (float)this.y, (float)(this.x + finalWidth + 10), (float)(this.y + 43), 1.0f, c2);
        RenderUtil.drawBorderedRect((float)this.x, (float)this.y, (float)(this.x + finalWidth + 10), (float)(this.y + 43), 1.5f, Client.pickaxeAbilityReady ? Colors.GREEN.c : Colors.RED.c, 0);
        FontLoaders.NMSL20.drawString(this.mithrilPowder, (float)(this.x + 2), (float)(this.y + 5), -1);
        FontLoaders.NMSL20.drawString(this.gemstonePowder, (float)(this.x + 2), (float)(this.y + 18), -1);
        FontLoaders.NMSL20.drawString(" Pickaxe CD Ready: " + (Client.pickaxeAbilityReady ? (EnumChatFormatting.GREEN + "true") : (EnumChatFormatting.RED + "false")), (float)(this.x + 2), (float)(this.y + 31), -1);
    }
    
    public int getRightCPS() {
        final long curTime = System.currentTimeMillis();
        this.clicks.removeIf(e -> e + 1000L < curTime);
        return this.clicks.size();
    }
}
