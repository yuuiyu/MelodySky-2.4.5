//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.Utils.Item.*;
import xyz.Melody.Utils.*;
import com.google.gson.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import java.util.*;

public class ShowItemSBID extends Command
{
    private Gson gson;
    
    public ShowItemSBID() {
        super("sbid", new String[] { "id" }, "", "FUCK YOU!");
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public String execute(final String[] args) {
        final ItemStack holding = (this.mc.thePlayer.getHeldItem() != null) ? this.mc.thePlayer.getHeldItem() : null;
        if (holding != null) {
            final NBTTagCompound compound = holding.getTagCompound();
            final String id = ItemUtils.getSkyBlockID(holding);
            if (id.equals("NotSBItem")) {
                Helper.sendMessage("Error: " + id);
                return null;
            }
            if (id.equals("ENCHANTED_BOOK")) {
                final NBTTagCompound enchants = compound.getCompoundTag("ExtraAttributes").getCompoundTag("enchantments");
                final Set<String> keys = (Set<String>)enchants.getKeySet();
                for (final String key : keys) {
                    final String id2 = "ENCHANTMENT_" + key.toUpperCase() + "_" + enchants.getInteger(key);
                    Helper.sendMessage("Attribute Enchant Info: " + id2);
                }
                Helper.sendMessage("Skyblock ID: " + id);
                return null;
            }
            if (id.equals("PET")) {
                if (ItemUtils.getPetInfo(holding) != null) {
                    final NBTTagCompound info = compound.getCompoundTag("ExtraAttributes");
                    final JsonObject petInfo = (JsonObject)this.gson.fromJson(info.getString("petInfo"), (Class)JsonObject.class);
                    String petID = "idk";
                    if (petInfo.has("type") && petInfo.has("tier")) {
                        petID = "PET_" + petInfo.get("type").getAsString() + "_" + petInfo.get("tier").getAsString();
                    }
                    Helper.sendMessage("Attribute Pet Info: " + petID);
                    Helper.sendMessage("Skyblock ID: " + id);
                    return null;
                }
            }
            else {
                if (id.equals("RUNE")) {
                    final NBTTagCompound enchants = compound.getCompoundTag("ExtraAttributes").getCompoundTag("runes");
                    final Set<String> keys = (Set<String>)enchants.getKeySet();
                    for (final String key : keys) {
                        final String id2 = "RUNE_" + key.toUpperCase() + "_" + enchants.getInteger(key);
                        Helper.sendMessage("Attribute Rune Info: " + id2);
                    }
                    Helper.sendMessage("Skyblock ID: " + id);
                    return null;
                }
                Helper.sendMessage(id);
            }
        }
        else {
            Helper.sendMessage("Please Hold an Item to View it's Skyblock ID.");
        }
        return null;
    }
}
