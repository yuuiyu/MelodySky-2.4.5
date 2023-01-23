//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import xyz.Melody.Event.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;

public class AutoArmor extends Module
{
    public Numbers<Double> delay;
    private TimerUtil timer;
    
    public AutoArmor() {
        super("AutoArmor", new String[] { "AutoArmor" }, ModuleType.Balance);
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)100.0, (Number)50.0, (Number)1000.0, (Number)50.0);
        this.timer = new TimerUtil();
        super.addValues((Value)this.delay);
    }
    
    @EventHandler
    public void onEvent(final EventTick event) {
        final float delay = ((Double)this.delay.getValue()).floatValue();
        if (!(this.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        if ((this.mc.currentScreen == null || this.mc.currentScreen instanceof GuiInventory || this.mc.currentScreen instanceof GuiChat) && this.timer.hasReached(delay)) {
            this.getBestArmor();
        }
    }
    
    public void getBestArmor() {
        for (int type = 1; type < 5; ++type) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getStack();
                if (this.isBestArmor(is, type)) {
                    continue;
                }
                this.drop(4 + type);
            }
            for (int i = 9; i < 45; ++i) {
                if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                    final ItemStack is2 = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                    if (this.isBestArmor(is2, type) && getProtection(is2) > 0.0f) {
                        this.shiftClick(i);
                        this.timer.reset();
                    }
                }
            }
        }
    }
    
    public boolean isBestArmor(final ItemStack stack, final int type) {
        final float prot = getProtection(stack);
        String strType = "";
        if (type == 1) {
            strType = "helmet";
        }
        else if (type == 2) {
            strType = "chestplate";
        }
        else if (type == 3) {
            strType = "leggings";
        }
        else if (type == 4) {
            strType = "boots";
        }
        if (!stack.getUnlocalizedName().contains(strType)) {
            return false;
        }
        for (int i = 5; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (getProtection(is) > prot && is.getUnlocalizedName().contains(strType)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void shiftClick(final int slot) {
        this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, slot, 0, 1, (EntityPlayer)this.mc.thePlayer);
    }
    
    public void drop(final int slot) {
        this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, slot, 1, 4, (EntityPlayer)this.mc.thePlayer);
    }
    
    public static float getProtection(final ItemStack stack) {
        float prot = 0.0f;
        if (stack.getItem() instanceof ItemArmor) {
            final ItemArmor armor = (ItemArmor)stack.getItem();
            prot += (float)(armor.damageReduceAmount + (100 - armor.damageReduceAmount) * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack) * 0.0075);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, stack) / 100.0);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, stack) / 100.0);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, stack) / 100.0);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 50.0);
            prot += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, stack) / 100.0);
        }
        return prot;
    }
    
    public enum EMode
    {
        OpenInv;
    }
}
