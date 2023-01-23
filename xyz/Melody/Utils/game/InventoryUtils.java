//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.game;

import net.minecraft.block.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.*;
import com.google.common.collect.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import java.util.*;

public class InventoryUtils
{
    public static final List<Block> BLOCK_BLACKLIST;
    public static Minecraft mc;
    
    public void dropSlot(final int slot) {
        final int windowId = new GuiInventory((EntityPlayer)InventoryUtils.mc.thePlayer).inventorySlots.windowId;
        InventoryUtils.mc.playerController.windowClick(windowId, slot, 1, 4, (EntityPlayer)InventoryUtils.mc.thePlayer);
    }
    
    public static void updateInventory() {
        for (int index = 0; index < 44; ++index) {
            try {
                final int offset = (index < 9) ? 36 : 0;
                Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue((Packet)new C10PacketCreativeInventoryAction(index + offset, Minecraft.getMinecraft().thePlayer.inventory.mainInventory[index]));
            }
            catch (Exception ex) {}
        }
    }
    
    public static ItemStack getStackInSlot(final int slot) {
        return InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot);
    }
    
    public static boolean isBestArmorOfTypeInInv(final ItemStack is) {
        try {
            if (is == null) {
                return false;
            }
            if (is.getItem() == null) {
                return false;
            }
            if (is.getItem() != null && !(is.getItem() instanceof ItemArmor)) {
                return false;
            }
            final ItemArmor ia = (ItemArmor)is.getItem();
            final int prot = getArmorProt(is);
            for (int i = 0; i < 4; ++i) {
                final ItemStack stack = InventoryUtils.mc.thePlayer.inventory.armorInventory[i];
                if (stack != null) {
                    final ItemArmor otherArmor = (ItemArmor)stack.getItem();
                    if (otherArmor.armorType == ia.armorType && getArmorProt(stack) >= prot) {
                        return false;
                    }
                }
            }
            for (int i = 0; i < InventoryUtils.mc.thePlayer.inventory.getSizeInventory() - 4; ++i) {
                final ItemStack stack = InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i);
                if (stack != null && stack.getItem() instanceof ItemArmor) {
                    final ItemArmor otherArmor = (ItemArmor)stack.getItem();
                    if (otherArmor.armorType == ia.armorType && otherArmor != ia && getArmorProt(stack) >= prot) {
                        return false;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public static boolean hotbarHas(final Item item) {
        for (int index = 0; index <= 36; ++index) {
            final ItemStack stack = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(index);
            if (stack != null && stack.getItem() == item) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean hotbarHas(final Item item, final int slotID) {
        for (int index = 0; index <= 36; ++index) {
            final ItemStack stack = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(index);
            if (stack != null && stack.getItem() == item && getSlotID(stack.getItem()) == slotID) {
                return true;
            }
        }
        return false;
    }
    
    public static int getSlotID(final Item item) {
        for (int index = 0; index <= 36; ++index) {
            final ItemStack stack = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(index);
            if (stack != null && stack.getItem() == item) {
                return index;
            }
        }
        return -1;
    }
    
    public static ItemStack getItemBySlotID(final int slotID) {
        for (int index = 0; index <= 36; ++index) {
            final ItemStack stack = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(index);
            if (stack != null && getSlotID(stack.getItem()) == slotID) {
                return stack;
            }
        }
        return null;
    }
    
    public static int getArmorProt(final ItemStack i) {
        int armorprot = -1;
        if (i != null && i.getItem() != null && i.getItem() instanceof ItemArmor) {
            armorprot = ((ItemArmor)i.getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(i)) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { i }, DamageSource.generic);
        }
        return armorprot;
    }
    
    public static int getBestSwordSlotID(final ItemStack item, final double damage) {
        for (int index = 0; index <= 36; ++index) {
            final ItemStack stack = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(index);
            if (stack != null && stack == item && getSwordDamage(stack) == getSwordDamage(item)) {
                return index;
            }
        }
        return -1;
    }
    
    private static double getSwordDamage(final ItemStack itemStack) {
        double damage = 0.0;
        final Optional<?> attributeModifier = itemStack.getAttributeModifiers().values().stream().findFirst();
        if (attributeModifier.isPresent()) {
            damage = ((AttributeModifier)attributeModifier.get()).getAmount();
        }
        return damage += EnchantmentHelper.getModifierForCreature(itemStack, EnumCreatureAttribute.UNDEFINED);
    }
    
    public boolean isBestChest(final int slot) {
        if (getStackInSlot(slot) != null && getStackInSlot(slot).getItem() != null && getStackInSlot(slot).getItem() instanceof ItemArmor) {
            final int slotProtection = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot) }, DamageSource.generic);
            if (InventoryUtils.mc.thePlayer.inventory.armorInventory[2] != null) {
                final ItemStack is = InventoryUtils.mc.thePlayer.inventory.armorInventory[2];
                final ItemArmor ia1 = (ItemArmor)getStackInSlot(slot).getItem();
                final int otherProtection = ((ItemArmor)is.getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(is)) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { is }, DamageSource.generic);
                if (otherProtection > slotProtection || otherProtection == slotProtection) {
                    return false;
                }
            }
            for (int i = 0; i < InventoryUtils.mc.thePlayer.inventory.getSizeInventory(); ++i) {
                if (getStackInSlot(i) != null && InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof ItemArmor) {
                    final int otherProtection = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i) }, DamageSource.generic);
                    final ItemArmor ia1 = (ItemArmor)getStackInSlot(slot).getItem();
                    final ItemArmor ia2 = (ItemArmor)getStackInSlot(i).getItem();
                    if (ia1.armorType == 1 && ia2.armorType == 1 && otherProtection > slotProtection) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean isBestHelmet(final int slot) {
        if (getStackInSlot(slot) != null && getStackInSlot(slot).getItem() != null && getStackInSlot(slot).getItem() instanceof ItemArmor) {
            final int slotProtection = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot) }, DamageSource.generic);
            if (InventoryUtils.mc.thePlayer.inventory.armorInventory[3] != null) {
                final ItemStack is = InventoryUtils.mc.thePlayer.inventory.armorInventory[3];
                final ItemArmor ia1 = (ItemArmor)getStackInSlot(slot).getItem();
                final int otherProtection = ((ItemArmor)is.getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(is)) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { is }, DamageSource.generic);
                if (otherProtection > slotProtection || otherProtection == slotProtection) {
                    return false;
                }
            }
            for (int i = 0; i < InventoryUtils.mc.thePlayer.inventory.getSizeInventory(); ++i) {
                if (getStackInSlot(i) != null && InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof ItemArmor) {
                    final int otherProtection = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i) }, DamageSource.generic);
                    final ItemArmor ia1 = (ItemArmor)getStackInSlot(slot).getItem();
                    final ItemArmor ia2 = (ItemArmor)getStackInSlot(i).getItem();
                    if (ia1.armorType == 0 && ia2.armorType == 0 && otherProtection > slotProtection) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean isBestLeggings(final int slot) {
        if (getStackInSlot(slot) != null && getStackInSlot(slot).getItem() != null && getStackInSlot(slot).getItem() instanceof ItemArmor) {
            final int slotProtection = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot) }, DamageSource.generic);
            if (InventoryUtils.mc.thePlayer.inventory.armorInventory[1] != null) {
                final ItemStack is = InventoryUtils.mc.thePlayer.inventory.armorInventory[1];
                final ItemArmor ia1 = (ItemArmor)getStackInSlot(slot).getItem();
                final int otherProtection = ((ItemArmor)is.getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(is)) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { is }, DamageSource.generic);
                if (otherProtection > slotProtection || otherProtection == slotProtection) {
                    return false;
                }
            }
            for (int i = 0; i < InventoryUtils.mc.thePlayer.inventory.getSizeInventory(); ++i) {
                if (getStackInSlot(i) != null && InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof ItemArmor) {
                    final int otherProtection = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i) }, DamageSource.generic);
                    final ItemArmor ia1 = (ItemArmor)getStackInSlot(slot).getItem();
                    final ItemArmor ia2 = (ItemArmor)getStackInSlot(i).getItem();
                    if (ia1.armorType == 2 && ia2.armorType == 2 && otherProtection > slotProtection) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean isBestBoots(final int slot) {
        if (getStackInSlot(slot) != null && getStackInSlot(slot).getItem() != null && getStackInSlot(slot).getItem() instanceof ItemArmor) {
            final int slotProtection = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot) }, DamageSource.generic);
            if (InventoryUtils.mc.thePlayer.inventory.armorInventory[0] != null) {
                final ItemStack is = InventoryUtils.mc.thePlayer.inventory.armorInventory[0];
                final ItemArmor ia1 = (ItemArmor)getStackInSlot(slot).getItem();
                final int otherProtection = ((ItemArmor)is.getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(is)) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { is }, DamageSource.generic);
                if (otherProtection > slotProtection || otherProtection == slotProtection) {
                    return false;
                }
            }
            for (int i = 0; i < InventoryUtils.mc.thePlayer.inventory.getSizeInventory(); ++i) {
                if (getStackInSlot(i) != null && InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof ItemArmor) {
                    final int otherProtection = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i) }, DamageSource.generic);
                    final ItemArmor ia1 = (ItemArmor)getStackInSlot(slot).getItem();
                    final ItemArmor ia2 = (ItemArmor)getStackInSlot(i).getItem();
                    if (ia1.armorType == 3 && ia2.armorType == 3 && otherProtection > slotProtection) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean isBestSword(final int slotIn) {
        return this.getBestWeapon() == slotIn;
    }
    
    public static int getItemType(final ItemStack itemStack) {
        if (itemStack.getItem() instanceof ItemArmor) {
            final ItemArmor armor = (ItemArmor)itemStack.getItem();
            return armor.armorType;
        }
        return -1;
    }
    
    public static float getItemDamage(final ItemStack itemStack) {
        final Multimap multimap = itemStack.getAttributeModifiers();
        final Iterator iterator;
        if (!multimap.isEmpty() && (iterator = multimap.entries().iterator()).hasNext()) {
            final Map.Entry entry = iterator.next();
            final AttributeModifier attributeModifier = entry.getValue();
            final double damage = (attributeModifier.getOperation() != 1 && attributeModifier.getOperation() != 2) ? attributeModifier.getAmount() : (attributeModifier.getAmount() * 100.0);
            return (attributeModifier.getAmount() > 1.0) ? (1.0f + (float)damage) : 1.0f;
        }
        return 1.0f;
    }
    
    public boolean hasItemMoreTimes(final int slotIn) {
        final ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        stacks.clear();
        for (int i = 0; i < InventoryUtils.mc.thePlayer.inventory.getSizeInventory(); ++i) {
            if (!stacks.contains(getStackInSlot(i))) {
                stacks.add(getStackInSlot(i));
            }
            else if (getStackInSlot(i) == getStackInSlot(slotIn)) {
                return true;
            }
        }
        return false;
    }
    
    public int getBestWeaponInHotbar() {
        final int originalSlot = InventoryUtils.mc.thePlayer.inventory.currentItem;
        int weaponSlot = -1;
        float weaponDamage = 1.0f;
        for (int slot = 0; slot < 9; slot = (byte)(slot + 1)) {
            final ItemStack itemStack = InventoryUtils.mc.thePlayer.inventory.getStackInSlot(slot);
            if (itemStack != null) {
                float damage = getItemDamage(itemStack);
                if ((damage += EnchantmentHelper.getModifierForCreature(itemStack, EnumCreatureAttribute.UNDEFINED)) > weaponDamage) {
                    weaponDamage = damage;
                    weaponSlot = slot;
                }
            }
        }
        if (weaponSlot != -1) {
            return weaponSlot;
        }
        return originalSlot;
    }
    
    public int getBestWeapon() {
        final int originalSlot = InventoryUtils.mc.thePlayer.inventory.currentItem;
        int weaponSlot = -1;
        float weaponDamage = 1.0f;
        for (int slot = 0; slot < InventoryUtils.mc.thePlayer.inventory.getSizeInventory(); slot = (byte)(slot + 1)) {
            final ItemStack itemStack;
            if (getStackInSlot(slot) != null && (itemStack = getStackInSlot(slot)) != null && itemStack.getItem() != null && itemStack.getItem() instanceof ItemSword) {
                float damage = getItemDamage(itemStack);
                if ((damage += EnchantmentHelper.getModifierForCreature(itemStack, EnumCreatureAttribute.UNDEFINED)) > weaponDamage) {
                    weaponDamage = damage;
                    weaponSlot = slot;
                }
            }
        }
        if (weaponSlot != -1) {
            return weaponSlot;
        }
        return originalSlot;
    }
    
    public int getArmorProt(final int i) {
        int armorprot = -1;
        if (getStackInSlot(i) != null && getStackInSlot(i).getItem() != null && getStackInSlot(i).getItem() instanceof ItemArmor) {
            armorprot = ((ItemArmor)InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i).getItem()).getArmorMaterial().getDamageReductionAmount(getItemType(InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i))) + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[] { InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i) }, DamageSource.generic);
        }
        return armorprot;
    }
    
    public static int getFirstItem(final Item i1) {
        for (int j = 0; j < InventoryUtils.mc.thePlayer.inventory.getSizeInventory(); ++j) {
            if (getStackInSlot(j) != null && getStackInSlot(j).getItem() != null && getStackInSlot(j).getItem() == i1) {
                return j;
            }
        }
        return -1;
    }
    
    public static boolean isBestSword(final ItemStack itemSword, final int slot) {
        if (itemSword != null && itemSword.getItem() instanceof ItemSword) {
            for (int i = 0; i < InventoryUtils.mc.thePlayer.inventory.getSizeInventory(); ++i) {
                final ItemStack iStack = InventoryUtils.mc.thePlayer.inventory.getStackInSlot(i);
                if (iStack != null && iStack.getItem() instanceof ItemSword && getItemDamage(iStack) >= getItemDamage(itemSword) && slot != i) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static int getAmountInHotbar(final String item) {
        for (int i = 0; i < 8; ++i) {
            final ItemStack is = InventoryUtils.mc.thePlayer.inventory.mainInventory[i];
            if (is != null && StringUtils.stripControlCodes(is.getDisplayName()).equals(item)) {
                return is.stackSize;
            }
        }
        return 0;
    }
    
    static {
        BLOCK_BLACKLIST = Arrays.asList(Blocks.enchanting_table, (Block)Blocks.chest, Blocks.ender_chest, Blocks.trapped_chest, Blocks.anvil, (Block)Blocks.sand, Blocks.web, Blocks.torch, Blocks.crafting_table, Blocks.furnace, Blocks.waterlily, Blocks.dispenser, Blocks.stone_pressure_plate, Blocks.wooden_pressure_plate, Blocks.noteblock, Blocks.dropper, Blocks.tnt, Blocks.standing_banner, Blocks.wall_banner);
        InventoryUtils.mc = Minecraft.getMinecraft();
    }
}
