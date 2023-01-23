//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.module.FMLModules.*;
import net.minecraft.util.*;
import xyz.Melody.*;
import xyz.Melody.Event.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Alerts extends Module
{
    private TimerUtil bonzoTimer;
    private TimerUtil bonzo2Timer;
    private TimerUtil spiritTimer;
    public boolean spiritMaskPoped;
    public boolean bonzoMaskPoped;
    public boolean bonzoMask2Poped;
    public boolean spiritMaskReady;
    public boolean bonzoMaskReady;
    public boolean bonzoMask2Ready;
    public boolean shouldShowSpiritMaskReady;
    public boolean shouldShowBonzoMaskReady;
    public boolean shouldShowBonzoMask2Ready;
    public Option<Boolean> rases;
    public Option<Boolean> watcher;
    public Option<Boolean> bonzo;
    public Option<Boolean> spirit;
    
    public Alerts() {
        super("Alerts", ModuleType.Dungeons);
        this.bonzoTimer = new TimerUtil();
        this.bonzo2Timer = new TimerUtil();
        this.spiritTimer = new TimerUtil();
        this.spiritMaskPoped = false;
        this.bonzoMaskPoped = false;
        this.bonzoMask2Poped = false;
        this.spiritMaskReady = true;
        this.bonzoMaskReady = true;
        this.bonzoMask2Ready = true;
        this.shouldShowSpiritMaskReady = false;
        this.shouldShowBonzoMaskReady = false;
        this.shouldShowBonzoMask2Ready = false;
        this.rases = (Option<Boolean>)new Option("Rases Mode", (Object)true);
        this.watcher = (Option<Boolean>)new Option("WatcherReady", (Object)true);
        this.bonzo = (Option<Boolean>)new Option("BonzoMask", (Object)true);
        this.spirit = (Option<Boolean>)new Option("SpiritMask", (Object)true);
        this.addValues(new Value[] { (Value)this.rases, (Value)this.watcher, (Value)this.bonzo, (Value)this.spirit });
        this.setModInfo("Create Alert Titles.");
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.bonzoTimer.reset();
        this.bonzo2Timer.reset();
        this.spiritTimer.reset();
        this.spiritMaskPoped = false;
        this.bonzoMaskPoped = false;
        this.bonzoMask2Poped = false;
        this.spiritMaskReady = true;
        this.bonzoMaskReady = true;
        this.bonzoMask2Ready = true;
        this.shouldShowSpiritMaskReady = false;
        this.shouldShowBonzoMaskReady = false;
        this.shouldShowBonzoMask2Ready = false;
        super.onDisable();
    }
    
    @EventHandler
    private void onTickPop(final EventTick event) {
        if (AlertsListener.shouldShowWatcherReady) {
            Client.drawTitle("Watcher Ready", EnumChatFormatting.RED);
            AlertsListener.shouldShowWatcherReady = false;
        }
        if (AlertsListener.shouldShowSpiritMaskPoped) {
            if (!this.spiritMaskPoped) {
                this.spiritMaskPoped = true;
            }
            Client.drawTitle("Spirit Mask Poped!", EnumChatFormatting.RED);
            this.spiritMaskReady = false;
            this.shouldShowSpiritMaskReady = false;
            this.spiritTimer.reset();
            AlertsListener.shouldShowSpiritMaskPoped = false;
        }
        if (AlertsListener.shouldShowBonzoMaskPoped) {
            if (!this.bonzoMaskPoped) {
                this.bonzoMaskPoped = true;
            }
            Client.drawTitle("Bonzo Mask Poped!", EnumChatFormatting.RED);
            this.bonzoMaskReady = false;
            this.shouldShowBonzoMaskReady = false;
            this.bonzoTimer.reset();
            AlertsListener.shouldShowBonzoMaskPoped = false;
        }
        if (AlertsListener.shouldShowBonzoMask2Poped) {
            if (!this.bonzoMask2Poped) {
                this.bonzoMask2Poped = true;
            }
            Client.drawTitle("\u269a Bonzo Mask Poped!", EnumChatFormatting.RED);
            this.bonzoMask2Ready = false;
            this.shouldShowBonzoMask2Ready = false;
            this.bonzo2Timer.reset();
            AlertsListener.shouldShowBonzoMask2Poped = false;
        }
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (this.spiritMaskPoped && this.spiritTimer.hasReached(33000.0)) {
            this.shouldShowSpiritMaskReady = true;
            this.spiritTimer.reset();
        }
        if (this.bonzoMaskPoped && this.bonzoTimer.hasReached(211000.0)) {
            this.shouldShowBonzoMaskReady = true;
            this.bonzoTimer.reset();
        }
        if (this.bonzoMask2Poped && this.bonzo2Timer.hasReached(211000.0)) {
            this.shouldShowBonzoMask2Ready = true;
            this.bonzo2Timer.reset();
        }
    }
    
    @EventHandler
    private void onTickReady(final EventTick event) {
        if (this.shouldShowSpiritMaskReady) {
            if (!this.spiritMaskReady) {
                this.spiritMaskReady = true;
            }
            Client.drawTitle("Spirit Mask Ready!", EnumChatFormatting.GREEN);
            this.spiritMaskPoped = false;
            Helper.sendMessage("Spirit Mask Ready!");
            this.shouldShowSpiritMaskReady = false;
        }
        if (this.shouldShowBonzoMaskReady) {
            if (!this.bonzoMaskReady) {
                this.bonzoMaskReady = true;
            }
            Client.drawTitle("Bonzo Mask Ready!", EnumChatFormatting.GREEN);
            this.bonzoMaskPoped = false;
            Helper.sendMessage("Bonzo Mask Ready!");
            this.shouldShowBonzoMaskReady = false;
        }
        if (this.shouldShowBonzoMask2Ready) {
            if (!this.bonzoMask2Ready) {
                this.bonzoMask2Ready = true;
            }
            Client.drawTitle("\u269a Bonzo Mask Ready!", EnumChatFormatting.GREEN);
            this.bonzoMask2Poped = false;
            Helper.sendMessage("\u269a Bonzo Mask Ready!");
            this.shouldShowBonzoMask2Ready = false;
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        this.spiritMaskPoped = false;
        this.bonzoMaskPoped = false;
        this.bonzoMask2Poped = false;
        this.spiritMaskReady = true;
        this.bonzoMaskReady = true;
        this.bonzoMask2Ready = true;
        this.shouldShowSpiritMaskReady = false;
        this.shouldShowBonzoMaskReady = false;
        this.shouldShowBonzoMask2Ready = false;
        this.bonzoTimer.reset();
        this.bonzo2Timer.reset();
        this.spiritTimer.reset();
    }
}
