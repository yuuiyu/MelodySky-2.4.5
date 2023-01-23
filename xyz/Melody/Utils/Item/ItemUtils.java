//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.Item;

import net.minecraft.client.*;
import xyz.Melody.Utils.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import xyz.Melody.*;
import net.minecraft.nbt.*;

public class ItemUtils
{
    public static Minecraft mc;
    
    public static boolean useSBItem(final String itm, final boolean left) {
        if (useItem(itm.toUpperCase(), !left)) {
            return true;
        }
        Helper.sendMessage((Object)("Missing Item in Hotbar: " + itm));
        return false;
    }
    
    public static boolean useSBItem(final String itm) {
        if (useItem(itm.toUpperCase(), true)) {
            return true;
        }
        Helper.sendMessage((Object)("Missing Item in Hotbar: " + itm));
        return false;
    }
    
    public static boolean hasItemInHotbar(final String itemId) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = ItemUtils.mc.thePlayer.inventory.getStackInSlot(i);
            if (itemId.equals(getSkyBlockID(item))) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean useItem(final String itemId, final boolean rightClick) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = ItemUtils.mc.thePlayer.inventory.getStackInSlot(i);
            if (itemId.equals(getSkyBlockID(item))) {
                final int previousItem = ItemUtils.mc.thePlayer.inventory.currentItem;
                ItemUtils.mc.thePlayer.inventory.currentItem = i;
                if (rightClick) {
                    ItemUtils.mc.playerController.sendUseItem((EntityPlayer)ItemUtils.mc.thePlayer, (World)ItemUtils.mc.theWorld, item);
                }
                else {
                    Client.leftClick();
                }
                ItemUtils.mc.thePlayer.inventory.currentItem = previousItem;
                return true;
            }
        }
        return false;
    }
    
    public static NBTTagCompound getExtraAttributes(final ItemStack item) {
        if (!item.hasTagCompound()) {
            return null;
        }
        return item.getSubCompound("ExtraAttributes", false);
    }
    
    public static String getSkyBlockID(final ItemStack item) {
        final NBTTagCompound extraAttributes;
        if (item != null && (extraAttributes = item.getSubCompound("ExtraAttributes", false)) != null && extraAttributes.hasKey("id")) {
            return extraAttributes.getString("id");
        }
        return "NotSBItem";
    }
    
    public static NBTTagCompound getPetInfo(final ItemStack item) {
        if (getSkyBlockID(item) != "NotSBItem") {
            final NBTTagCompound compound = item.getTagCompound();
            final NBTTagCompound info = compound.getCompoundTag("ExtraAttributes").getCompoundTag("petInfo");
            return info;
        }
        return null;
    }
    
    public static String[] getLoreFromNBT(final NBTTagCompound tag) {
        String[] lore = new String[0];
        final NBTTagCompound display = tag.getCompoundTag("display");
        if (display.hasKey("Lore", 9)) {
            final NBTTagList list = display.getTagList("Lore", 8);
            lore = new String[list.tagCount()];
            for (int k = 0; k < list.tagCount(); ++k) {
                lore[k] = list.getStringTagAt(k);
            }
        }
        return lore;
    }
    
    static {
        ItemUtils.mc = Minecraft.getMinecraft();
    }
}
