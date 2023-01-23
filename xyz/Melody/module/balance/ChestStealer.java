//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.inventory.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import xyz.Melody.Event.*;
import net.minecraft.item.*;

public class ChestStealer extends Module
{
    private Numbers<Double> delay;
    private Option<Boolean> menucheck;
    private TimerUtil timer;
    
    public ChestStealer() {
        super("ChestStealer", new String[] { "cheststeal", "chests", "stealer" }, ModuleType.Balance);
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)70.0, (Number)0.0, (Number)1000.0, (Number)10.0);
        this.menucheck = (Option<Boolean>)new Option("MenuCheck", (Object)true);
        this.timer = new TimerUtil();
        this.addValues((Value)this.delay, (Value)this.menucheck);
        this.setColor(new Color(218, 97, 127).getRGB());
    }
    
    @EventHandler
    private void onUpdate(final EventTick event) {
        this.setSuffix(this.delay.getValue());
        if (this.mc.thePlayer != null && this.mc.thePlayer.openContainer != null && this.mc.thePlayer.openContainer instanceof ContainerChest) {
            int i = 0;
            final ContainerChest container = (ContainerChest)this.mc.thePlayer.openContainer;
            if (!StatCollector.translateToLocal("container.chest").equalsIgnoreCase(container.getLowerChestInventory().getDisplayName().getUnformattedText()) && (!StatCollector.translateToLocal("container.chestDouble").equalsIgnoreCase(container.getLowerChestInventory().getDisplayName().getUnformattedText()) || !(boolean)this.menucheck.getValue())) {
                return;
            }
            for (i = 0; i < container.getLowerChestInventory().getSizeInventory(); ++i) {
                if (container.getLowerChestInventory().getStackInSlot(i) != null) {
                    if (this.timer.hasReached((double)this.delay.getValue())) {
                        this.mc.playerController.windowClick(container.windowId, i, 0, 1, (EntityPlayer)this.mc.thePlayer);
                        this.timer.reset();
                    }
                }
            }
            if (this.isEmpty()) {
                this.mc.thePlayer.closeScreen();
            }
        }
    }
    
    private boolean isEmpty() {
        if (this.mc.thePlayer.openContainer != null && this.mc.thePlayer.openContainer instanceof ContainerChest) {
            final ContainerChest container = (ContainerChest)this.mc.thePlayer.openContainer;
            for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); ++i) {
                final ItemStack itemStack = container.getLowerChestInventory().getStackInSlot(i);
                if (itemStack != null && itemStack.getItem() != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
