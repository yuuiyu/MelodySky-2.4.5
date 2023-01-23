//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.inventory.*;
import xyz.Melody.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import xyz.Melody.Event.*;
import net.minecraft.entity.player.*;
import net.minecraft.enchantment.*;
import xyz.Melody.Utils.game.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import java.util.*;

public class InvCleaner extends Module
{
    private Numbers<Double> BlockCap;
    private Numbers<Double> Delay;
    private Option<Boolean> UHC;
    private Numbers<Double> weaponSlot;
    private Numbers<Double> pickaxeSlot;
    private Numbers<Double> axeSlot;
    private Numbers<Double> shovelSlot;
    private TimerUtil timer;
    
    public InvCleaner() {
        super("InvManager", new String[] { "InvCleaner" }, ModuleType.Balance);
        this.BlockCap = (Numbers<Double>)new Numbers("MaxBlocks", (Number)128.0, (Number)0.0, (Number)256.0, (Number)8.0);
        this.Delay = (Numbers<Double>)new Numbers("Delay", (Number)100.0, (Number)50.0, (Number)1000.0, (Number)100.0);
        this.UHC = (Option<Boolean>)new Option("UHC", (Object)false);
        this.weaponSlot = (Numbers<Double>)new Numbers("WeaponSlot", (Number)36.0, (Number)36.0, (Number)44.0, (Number)1.0);
        this.pickaxeSlot = (Numbers<Double>)new Numbers("PickaxeSlot", (Number)37.0, (Number)36.0, (Number)44.0, (Number)1.0);
        this.axeSlot = (Numbers<Double>)new Numbers("AxeSlot", (Number)38.0, (Number)36.0, (Number)44.0, (Number)1.0);
        this.shovelSlot = (Numbers<Double>)new Numbers("ShovelSlot", (Number)39.0, (Number)36.0, (Number)44.0, (Number)1.0);
        this.timer = new TimerUtil();
        this.addValues((Value)this.BlockCap, (Value)this.Delay, (Value)this.weaponSlot, (Value)this.pickaxeSlot, (Value)this.axeSlot, (Value)this.shovelSlot, (Value)this.UHC);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    @EventHandler
    public void onEvent(final EventTick event) {
        if (this.mc.thePlayer.openContainer instanceof ContainerChest && this.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        final int weaponSlot = ((Double)this.weaponSlot.getValue()).intValue();
        final int pickaxeSlot = ((Double)this.weaponSlot.getValue()).intValue();
        final int shovelSlot = ((Double)this.weaponSlot.getValue()).intValue();
        final int axeSlot = ((Double)this.weaponSlot.getValue()).intValue();
        final long delay = ((Double)this.Delay.getValue()).longValue();
        final AutoArmor aar = (AutoArmor)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AutoArmor.class);
        final long Adelay = ((Double)aar.delay.getValue()).longValue();
        if (this.timer.hasReached((double)Adelay) && this.mc.currentScreen instanceof GuiInventory) {
            this.getBestArmor();
        }
        for (int type = 1; type < 5; ++type) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getStack();
                if (!aar.isBestArmor(is, type)) {
                    return;
                }
            }
            else if (this.invContainsType(type - 1)) {
                return;
            }
        }
        if (!(this.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        if (this.mc.currentScreen == null || this.mc.currentScreen instanceof GuiInventory || this.mc.currentScreen instanceof GuiChat) {
            if (this.timer.hasReached((double)delay) && weaponSlot >= 36) {
                if (!this.mc.thePlayer.inventoryContainer.getSlot(weaponSlot).getHasStack()) {
                    this.getBestWeapon(weaponSlot);
                }
                else if (!this.isBestWeapon(this.mc.thePlayer.inventoryContainer.getSlot(weaponSlot).getStack())) {
                    this.getBestWeapon(weaponSlot);
                }
            }
            if (this.timer.hasReached((double)delay) && pickaxeSlot >= 36) {
                this.getBestPickaxe(pickaxeSlot);
            }
            if (this.timer.hasReached((double)delay) && shovelSlot >= 36) {
                this.getBestShovel(shovelSlot);
            }
            if (this.timer.hasReached((double)delay) && axeSlot >= 36) {
                this.getBestAxe(axeSlot);
            }
            if (this.timer.hasReached((double)delay)) {
                for (int j = 9; j < 45; ++j) {
                    if (this.mc.thePlayer.inventoryContainer.getSlot(j).getHasStack()) {
                        final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(j).getStack();
                        if (this.shouldDrop(is, j)) {
                            this.drop(j);
                            this.timer.reset();
                            if (delay > 0L) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void shiftClick(final int slot) {
        this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, slot, 0, 1, (EntityPlayer)this.mc.thePlayer);
    }
    
    public void swap(final int slot1, final int hotbarSlot) {
        this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, slot1, hotbarSlot, 2, (EntityPlayer)this.mc.thePlayer);
    }
    
    public void drop(final int slot) {
        this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, slot, 1, 4, (EntityPlayer)this.mc.thePlayer);
    }
    
    public boolean isBestWeapon(final ItemStack stack) {
        final float damage = this.getDamage(stack);
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.getDamage(is) > damage && is.getItem() instanceof ItemSword) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void getBestWeapon(final int slot) {
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.isBestWeapon(is) && this.getDamage(is) > 0.0f && is.getItem() instanceof ItemSword) {
                    this.swap(i, slot - 36);
                    this.timer.reset();
                    break;
                }
            }
        }
    }
    
    private float getDamage(final ItemStack stack) {
        float damage = 0.0f;
        final Item item = stack.getItem();
        if (item instanceof ItemTool) {
            final ItemTool tool = (ItemTool)item;
            damage += tool.getMaxDamage();
        }
        if (item instanceof ItemSword) {
            final ItemSword sword = (ItemSword)item;
            damage += sword.getDamageVsEntity();
        }
        damage += EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25f + EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) * 0.01f;
        return damage;
    }
    
    public boolean shouldDrop(final ItemStack stack, final int slot) {
        if (stack.getDisplayName().contains("\u951f\u65a4\u62f7\u951f\ufffd")) {
            return false;
        }
        if (stack.getDisplayName().contains("\u951f\u63ed\u7877\u62f7")) {
            return false;
        }
        if (stack.getDisplayName().toLowerCase().contains("(right click)")) {
            return false;
        }
        if (this.UHC.getValue()) {
            if (stack.getDisplayName().toLowerCase().contains("\u5934")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("apple")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("head")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("gold")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("crafting table")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("stick")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("and") && stack.getDisplayName().toLowerCase().contains("ril")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("axe of perun")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("barbarian")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("bloodlust")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("dragonchest")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("dragon sword")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("dragon armor")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("excalibur")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("exodus")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("fusion armor")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("hermes boots")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("hide of leviathan")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("scythe")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("seven-league boots")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("shoes of vidar")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("apprentice")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("master")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("vorpal")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("enchanted")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("spiked")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("tarnhelm")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("philosopher")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("anvil")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("panacea")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("fusion")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("excalibur")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u5b66\u5f92")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u5e08\u951f\u65a4\u62f7\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u65a9\u951f\u65a4\u62f7\u4e4b\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u9b54")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u4e4b\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u4e4b\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u53eb\u7877\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u7aed\u7678\u62f7\u6218\u9774")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u82f9\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u4e4b\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u951f\u8857\ue1c6\u62f7\u951f\ufffd")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u7089")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("backpack")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u6854\u618b\u62f7\u4e4b\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u6c50")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u9636\u9769\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u4e4b\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u951f\ufffd")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u4e4b\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u7ef4\u951f\u65a4\u62f7\u6218\u9774")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u8857\ue1c6\u62f7\u951f\ufffd")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u4e4b\u951f\u65a4\u62f7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u4e4b\u9774")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("hermes")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("barbarian")) {
                return false;
            }
        }
        final int weaponSlot = ((Double)this.weaponSlot.getValue()).intValue();
        final int pickaxeSlot = ((Double)this.weaponSlot.getValue()).intValue();
        final int shovelSlot = ((Double)this.weaponSlot.getValue()).intValue();
        final int axeSlot = ((Double)this.weaponSlot.getValue()).intValue();
        if ((slot == weaponSlot && this.isBestWeapon(this.mc.thePlayer.inventoryContainer.getSlot(weaponSlot).getStack())) || (slot == pickaxeSlot && this.isBestPickaxe(this.mc.thePlayer.inventoryContainer.getSlot(pickaxeSlot).getStack()) && pickaxeSlot >= 0) || (slot == axeSlot && this.isBestAxe(this.mc.thePlayer.inventoryContainer.getSlot(axeSlot).getStack()) && axeSlot >= 0) || (slot == shovelSlot && this.isBestShovel(this.mc.thePlayer.inventoryContainer.getSlot(shovelSlot).getStack()) && shovelSlot >= 0)) {
            return false;
        }
        final AutoArmor aar = (AutoArmor)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AutoArmor.class);
        if (stack.getItem() instanceof ItemArmor) {
            for (int type = 1; type < 5; ++type) {
                if (this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getHasStack()) {
                    final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getStack();
                    if (aar.isBestArmor(is, type)) {
                        continue;
                    }
                }
                if (aar.isBestArmor(stack, type)) {
                    return false;
                }
            }
        }
        return (((Double)this.BlockCap.getValue()).intValue() != 0 && stack.getItem() instanceof ItemBlock && (this.getBlockCount() > ((Double)this.BlockCap.getValue()).intValue() || InventoryUtils.BLOCK_BLACKLIST.contains(((ItemBlock)stack.getItem()).getBlock()))) || (stack.getItem() instanceof ItemPotion && this.isBadPotion(stack)) || stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemBow || stack.getItem().getUnlocalizedName().contains("tnt") || stack.getItem().getUnlocalizedName().contains("stick") || stack.getItem().getUnlocalizedName().contains("egg") || stack.getItem().getUnlocalizedName().contains("string") || stack.getItem().getUnlocalizedName().contains("cake") || stack.getItem().getUnlocalizedName().contains("mushroom") || stack.getItem().getUnlocalizedName().contains("flint") || stack.getItem().getUnlocalizedName().contains("compass") || stack.getItem().getUnlocalizedName().contains("dyePowder") || stack.getItem().getUnlocalizedName().contains("feather") || stack.getItem().getUnlocalizedName().contains("bucket") || (stack.getItem().getUnlocalizedName().contains("chest") && !stack.getDisplayName().toLowerCase().contains("collect")) || stack.getItem().getUnlocalizedName().contains("snow") || stack.getItem().getUnlocalizedName().contains("fish") || stack.getItem().getUnlocalizedName().contains("enchant") || stack.getItem().getUnlocalizedName().contains("exp") || stack.getItem().getUnlocalizedName().contains("shears") || stack.getItem().getUnlocalizedName().contains("anvil") || stack.getItem().getUnlocalizedName().contains("torch") || stack.getItem().getUnlocalizedName().contains("seeds") || stack.getItem().getUnlocalizedName().contains("leather") || stack.getItem().getUnlocalizedName().contains("reeds") || stack.getItem().getUnlocalizedName().contains("skull") || stack.getItem().getUnlocalizedName().contains("record") || stack.getItem().getUnlocalizedName().contains("snowball") || stack.getItem() instanceof ItemGlassBottle || stack.getItem().getUnlocalizedName().contains("piston");
    }
    
    private int getBlockCount() {
        int blockCount = 0;
        for (int i = 0; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                final Item item = is.getItem();
                if (is.getItem() instanceof ItemBlock && !InventoryUtils.BLOCK_BLACKLIST.contains(((ItemBlock)item).getBlock())) {
                    blockCount += is.stackSize;
                }
            }
        }
        return blockCount;
    }
    
    private void getBestPickaxe(final int slot) {
        final int pickaxeSlot = ((Double)this.weaponSlot.getValue()).intValue();
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.isBestPickaxe(is) && pickaxeSlot != i && !this.isBestWeapon(is)) {
                    if (!this.mc.thePlayer.inventoryContainer.getSlot(pickaxeSlot).getHasStack()) {
                        this.swap(i, pickaxeSlot - 36);
                        this.timer.reset();
                        if (((Double)this.Delay.getValue()).longValue() > 0L) {
                            return;
                        }
                    }
                    else if (!this.isBestPickaxe(this.mc.thePlayer.inventoryContainer.getSlot(pickaxeSlot).getStack())) {
                        this.swap(i, pickaxeSlot - 36);
                        this.timer.reset();
                        if (((Double)this.Delay.getValue()).longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private void getBestShovel(final int slot) {
        final int shovelSlot = ((Double)this.weaponSlot.getValue()).intValue();
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.isBestShovel(is) && shovelSlot != i && !this.isBestWeapon(is)) {
                    if (!this.mc.thePlayer.inventoryContainer.getSlot(shovelSlot).getHasStack()) {
                        this.swap(i, shovelSlot - 36);
                        this.timer.reset();
                        if (((Double)this.Delay.getValue()).longValue() > 0L) {
                            return;
                        }
                    }
                    else if (!this.isBestShovel(this.mc.thePlayer.inventoryContainer.getSlot(shovelSlot).getStack())) {
                        this.swap(i, shovelSlot - 36);
                        this.timer.reset();
                        if (((Double)this.Delay.getValue()).longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private void getBestAxe(final int slot) {
        final int axeSlot = ((Double)this.weaponSlot.getValue()).intValue();
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.isBestAxe(is) && axeSlot != i && !this.isBestWeapon(is)) {
                    if (!this.mc.thePlayer.inventoryContainer.getSlot(axeSlot).getHasStack()) {
                        this.swap(i, axeSlot - 36);
                        this.timer.reset();
                        if (((Double)this.Delay.getValue()).longValue() > 0L) {
                            return;
                        }
                    }
                    else if (!this.isBestAxe(this.mc.thePlayer.inventoryContainer.getSlot(axeSlot).getStack())) {
                        this.swap(i, axeSlot - 36);
                        this.timer.reset();
                        if (((Double)this.Delay.getValue()).longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private boolean isBestPickaxe(final ItemStack stack) {
        final Item item = stack.getItem();
        if (!(item instanceof ItemPickaxe)) {
            return false;
        }
        final float value = this.getToolEffect(stack);
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.getToolEffect(is) > value && is.getItem() instanceof ItemPickaxe) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isBestShovel(final ItemStack stack) {
        final Item item = stack.getItem();
        if (!(item instanceof ItemSpade)) {
            return false;
        }
        final float value = this.getToolEffect(stack);
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.getToolEffect(is) > value && is.getItem() instanceof ItemSpade) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isBestAxe(final ItemStack stack) {
        final Item item = stack.getItem();
        if (!(item instanceof ItemAxe)) {
            return false;
        }
        final float value = this.getToolEffect(stack);
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.getToolEffect(is) > value && is.getItem() instanceof ItemAxe && !this.isBestWeapon(stack)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private float getToolEffect(final ItemStack stack) {
        final Item item = stack.getItem();
        if (!(item instanceof ItemTool)) {
            return 0.0f;
        }
        final String name = item.getUnlocalizedName();
        final ItemTool tool = (ItemTool)item;
        float value = 1.0f;
        if (item instanceof ItemPickaxe) {
            value = tool.getStrVsBlock(stack, Blocks.stone);
            if (name.toLowerCase().contains("gold")) {
                value -= 5.0f;
            }
        }
        else if (item instanceof ItemSpade) {
            value = tool.getStrVsBlock(stack, Blocks.dirt);
            if (name.toLowerCase().contains("gold")) {
                value -= 5.0f;
            }
        }
        else {
            if (!(item instanceof ItemAxe)) {
                return 1.0f;
            }
            value = tool.getStrVsBlock(stack, Blocks.log);
            if (name.toLowerCase().contains("gold")) {
                value -= 5.0f;
            }
        }
        value += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack) * 0.0075);
        value += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 100.0);
        return value;
    }
    
    private boolean isBadPotion(final ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ItemPotion) {
            final ItemPotion potion = (ItemPotion)stack.getItem();
            if (potion.getEffects(stack) == null) {
                return true;
            }
            for (final Object o : potion.getEffects(stack)) {
                final PotionEffect effect = (PotionEffect)o;
                if (effect.getPotionID() == Potion.poison.getId() || effect.getPotionID() == Potion.harm.getId() || effect.getPotionID() == Potion.moveSlowdown.getId() || effect.getPotionID() == Potion.weakness.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean invContainsType(final int type) {
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                final Item item = is.getItem();
                if (item instanceof ItemArmor) {
                    final ItemArmor armor = (ItemArmor)item;
                    if (type == armor.armorType) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void getBestArmor() {
        final AutoArmor aar = (AutoArmor)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AutoArmor.class);
        for (int type = 1; type < 5; ++type) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getStack();
                if (aar.isBestArmor(is, type)) {
                    continue;
                }
                this.drop(4 + type);
            }
            for (int i = 9; i < 45; ++i) {
                if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                    final ItemStack is2 = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                    if (aar.isBestArmor(is2, type) && AutoArmor.getProtection(is2) > 0.0f) {
                        this.shiftClick(i);
                        this.timer.reset();
                        if (((Double)this.Delay.getValue()).longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
