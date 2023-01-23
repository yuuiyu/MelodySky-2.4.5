//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;
import xyz.Melody.Utils.*;
import net.minecraft.util.*;
import java.util.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import xyz.Melody.module.balance.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;

public class NPlayerList extends HUDApi
{
    private TimerUtil timer;
    private Map<String, Float> playerList;
    private float range;
    private int oof;
    
    public NPlayerList() {
        super("PlayerList", 300, 300);
        this.timer = new TimerUtil();
        this.playerList = new HashMap<String, Float>();
        this.range = 0.0f;
        this.oof = 0;
        this.setEnabled(true);
    }
    
    @Override
    public void InScreenRender() {
        final float ax = 3.0f;
        final float ay = 3.0f;
        RenderUtil.drawFastRoundedRect((float)this.x, (float)this.y, (float)(this.x + 200), (float)(this.y + this.oof * 13 + 18), 2.0f, new Color(10, 10, 10, 50).getRGB());
        FontLoaders.CNMD24.drawString("Players Within " + this.range + " Blocks", this.x + ax, this.y + ay, Colors.GRAY.c);
        int row = 0;
        for (final String name : this.playerList.keySet()) {
            final float distance = this.playerList.get(name);
            final EnumChatFormatting format = (distance <= 15.0f) ? EnumChatFormatting.RED : EnumChatFormatting.AQUA;
            FontLoaders.CNMD20.drawString(" -" + name + " " + format + distance + "m", this.x + ax + 10.0f, this.y + row * 13 + 16 + ay, -1);
            ++row;
            this.oof = row;
        }
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        final PlayerList mod = (PlayerList)Client.instance.getModuleManager().getModuleByClass(PlayerList.class);
        if (!mod.isEnabled()) {
            this.playerList.clear();
            return;
        }
        if (this.timer.hasReached((double)mod.scanDelay.getValue())) {
            this.range = ((Double)mod.range.getValue()).floatValue();
            this.playerList = this.getPlayersIn(this.range);
            this.timer.reset();
        }
    }
    
    @EventHandler
    private void onR2D(final EventRender2D event) {
        if (this.playerList.isEmpty()) {
            return;
        }
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        final float ax = 5.0f;
        final float ay = 4.0f;
        RenderUtil.drawFastRoundedRect((float)this.x, (float)this.y, (float)(this.x + 200), (float)(this.y + this.oof * 13 + 20), 2.0f, new Color(10, 10, 10, 50).getRGB());
        FontLoaders.CNMD24.drawString("Players Within " + this.range + " Blocks", this.x + ax, this.y + ay, Colors.ORANGE.c);
        int row = 0;
        for (final String name : this.playerList.keySet()) {
            final float distance = this.playerList.get(name);
            final EnumChatFormatting format = (distance <= 15.0f) ? EnumChatFormatting.RED : EnumChatFormatting.AQUA;
            FontLoaders.CNMD20.drawString(" -" + name + " " + format + distance + "m", this.x + ax + 6.0f, this.y + row * 13 + 16 + ay, -1);
            ++row;
            this.oof = row;
        }
    }
    
    private Map<String, Float> getPlayersIn(final float range) {
        final Map<String, Float> playerMap = new HashMap<String, Float>();
        final AntiBot ab = (AntiBot)Client.instance.getModuleManager().getModuleByClass(AntiBot.class);
        for (final EntityPlayer player : this.mc.theWorld.playerEntities) {
            final float distance = this.mc.thePlayer.getDistanceToEntity((Entity)player);
            if (distance <= range && player != this.mc.thePlayer && player.getName() != null) {
                if (!ab.isInTablist(player)) {
                    continue;
                }
                playerMap.put(player.getName(), distance);
            }
        }
        return playerMap;
    }
}
