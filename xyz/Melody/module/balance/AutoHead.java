//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import net.minecraft.client.settings.*;
import xyz.Melody.Event.events.world.*;
import org.lwjgl.input.*;
import net.minecraft.potion.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.player.*;
import xyz.Melody.Event.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;

public class AutoHead extends Module
{
    private boolean eatingApple;
    private int switched;
    public static boolean doingStuff;
    private final TimerUtil timer;
    private final Option<Boolean> eatHeads;
    private final Option<Boolean> eatApples;
    private final Numbers<Double> health;
    private final Numbers<Double> delay;
    
    public AutoHead() {
        super("AutoHead", new String[] { "AutoHead", "EH", "eathead" }, ModuleType.Balance);
        this.switched = -1;
        this.timer = new TimerUtil();
        this.eatHeads = (Option<Boolean>)new Option("Eatheads", (Object)true);
        this.eatApples = (Option<Boolean>)new Option("Eatapples", (Object)true);
        this.health = (Numbers<Double>)new Numbers("HpPercentage", (Number)50.0, (Number)10.0, (Number)100.0, (Number)1.0);
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)750.0, (Number)10.0, (Number)2000.0, (Number)25.0);
        this.addValues((Value)this.health, (Value)this.delay, (Value)this.eatApples, (Value)this.eatHeads);
    }
    
    @Override
    public void onEnable() {
        this.eatingApple = (AutoHead.doingStuff = false);
        this.switched = -1;
        this.timer.reset();
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        AutoHead.doingStuff = false;
        if (this.eatingApple) {
            this.repairItemPress();
            this.repairItemSwitch();
        }
        super.onDisable();
    }
    
    private void repairItemPress() {
        if (this.mc.gameSettings != null) {
            final KeyBinding keyBindUseItem = this.mc.gameSettings.keyBindUseItem;
            if (keyBindUseItem != null) {
                KeyBinding.setKeyBindState(keyBindUseItem.getKeyCode(), false);
            }
        }
    }
    
    @EventHandler
    public void onUpdate(final EventPreUpdate event) {
        if (this.mc.thePlayer == null) {
            return;
        }
        final InventoryPlayer inventory = this.mc.thePlayer.inventory;
        if (inventory == null) {
            return;
        }
        AutoHead.doingStuff = false;
        if (!Mouse.isButtonDown(0) && !Mouse.isButtonDown(1)) {
            final KeyBinding useItem = this.mc.gameSettings.keyBindUseItem;
            if (!this.timer.hasReached((double)this.delay.getValue())) {
                this.eatingApple = false;
                this.repairItemPress();
                this.repairItemSwitch();
                return;
            }
            if (this.mc.thePlayer.capabilities.isCreativeMode || this.mc.thePlayer.isPotionActive(Potion.regeneration) || this.mc.thePlayer.getHealth() >= this.mc.thePlayer.getMaxHealth() * ((double)this.health.getValue() / 100.0)) {
                this.timer.reset();
                if (this.eatingApple) {
                    this.eatingApple = false;
                    this.repairItemPress();
                    this.repairItemSwitch();
                }
                return;
            }
            for (int i = 0; i < 2; ++i) {
                final boolean doEatHeads = i != 0;
                if (doEatHeads) {
                    if (!(boolean)this.eatHeads.getValue()) {
                        continue;
                    }
                }
                else if (!(boolean)this.eatApples.getValue()) {
                    this.eatingApple = false;
                    this.repairItemPress();
                    this.repairItemSwitch();
                    continue;
                }
                int slot;
                if (doEatHeads) {
                    slot = this.getItemFromHotbar(397);
                }
                else {
                    slot = this.getItemFromHotbar(322);
                }
                if (slot != -1) {
                    final int tempSlot = inventory.currentItem;
                    AutoHead.doingStuff = true;
                    if (doEatHeads) {
                        this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C09PacketHeldItemChange(slot));
                        this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(inventory.getCurrentItem()));
                        this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C09PacketHeldItemChange(tempSlot));
                        this.timer.reset();
                    }
                    else {
                        inventory.currentItem = slot;
                        KeyBinding.setKeyBindState(useItem.getKeyCode(), true);
                        if (!this.eatingApple) {
                            this.eatingApple = true;
                            this.switched = tempSlot;
                        }
                    }
                }
            }
        }
    }
    
    private void repairItemSwitch() {
        final EntityPlayerSP p = this.mc.thePlayer;
        if (p == null) {
            return;
        }
        final InventoryPlayer inventory = p.inventory;
        if (inventory == null) {
            return;
        }
        int switched = this.switched;
        if (switched == -1) {
            return;
        }
        inventory.currentItem = switched;
        switched = -1;
        this.switched = switched;
    }
    
    private int getItemFromHotbar(final int id) {
        for (int i = 0; i < 9; ++i) {
            if (this.mc.thePlayer.inventory.mainInventory[i] != null) {
                final ItemStack is = this.mc.thePlayer.inventory.mainInventory[i];
                final Item item = is.getItem();
                if (Item.getIdFromItem(item) == id) {
                    return i;
                }
            }
        }
        return -1;
    }
}
