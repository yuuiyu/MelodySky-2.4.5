//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import net.minecraftforge.event.entity.player.*;
import xyz.Melody.*;
import xyz.Melody.Utils.Item.*;
import xyz.Melody.System.Managers.Auctions.*;
import com.google.gson.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;

public class FetchLBinData extends Module
{
    private Gson gson;
    public Option<Boolean> sbonly;
    public Option<Boolean> hbin;
    public Option<Boolean> fmt;
    public Numbers<Double> delay;
    public static String colorPrefix;
    private TimerUtil timer;
    private static final NavigableMap<Long, String> suffixes;
    
    public FetchLBinData() {
        super("FetchLBinData", new String[] { "lbin" }, ModuleType.Others);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.sbonly = (Option<Boolean>)new Option("Skyblock Only", (Object)true);
        this.hbin = (Option<Boolean>)new Option("HBin", (Object)false);
        this.fmt = (Option<Boolean>)new Option("Format", (Object)false);
        this.delay = (Numbers<Double>)new Numbers("Delay(Min)", (Number)1.0, (Number)0.5, (Number)10.0, (Number)0.5);
        this.timer = new TimerUtil();
        this.addValues(new Value[] { (Value)this.delay, (Value)this.sbonly, (Value)this.fmt, (Value)this.hbin });
        this.setModInfo("Show Auction or Bazaar Data as ToolTip.");
    }
    
    @SubscribeEvent
    public void onItemTooltip(final ItemTooltipEvent e) {
        final ItemStack hoveredItem = e.itemStack;
        if (Client.inSkyblock || !(boolean)this.sbonly.getValue()) {
            final NBTTagCompound compound = hoveredItem.getTagCompound();
            final String id = ItemUtils.getSkyBlockID(hoveredItem);
            if (id.equals("NotSBItem")) {
                return;
            }
            if (id.equals("ENCHANTED_BOOK")) {
                final NBTTagCompound enchants = compound.getCompoundTag("ExtraAttributes").getCompoundTag("enchantments");
                final Set<String> keys = (Set<String>)enchants.getKeySet();
                int iterations = 0;
                for (final String key : keys) {
                    ++iterations;
                    final String id2 = "ENCHANTMENT_" + key.toUpperCase() + "_" + enchants.getInteger(key);
                    final AhBzManager.AuctionData auctionData = AhBzManager.auctions.get(id2);
                    if (auctionData == null) {
                        continue;
                    }
                    if (iterations >= 10) {
                        continue;
                    }
                    if (auctionData.getPrices().size() == 0) {
                        e.toolTip.add("§" + FetchLBinData.colorPrefix + "Bazaar Sell§7: " + ((auctionData.getSellPrice() == -1L) ? "§8N/A" : ("§e" + this.format(auctionData.getSellPrice()))));
                        e.toolTip.add("§" + FetchLBinData.colorPrefix + "Bazaar Buy§7: " + ((auctionData.getBuyPrice() == -1L) ? "§8N/A" : ("§e" + this.format(auctionData.getBuyPrice()))));
                    }
                    else if (auctionData.getSellPrice() == -1L) {
                        e.toolTip.add("§" + FetchLBinData.colorPrefix + "Lowest Bin§7: " + ((auctionData.getPrices().size() != 0) ? ("§e" + this.format(auctionData.getPrices().first())) : "§8N/A"));
                        if (!(boolean)this.hbin.getValue()) {
                            continue;
                        }
                        e.toolTip.add("§" + FetchLBinData.colorPrefix + "Highest Bin§7: " + ((auctionData.getPrices().size() != 0) ? ("§e" + this.format(auctionData.getPrices().last())) : "§8N/A"));
                    }
                    else {
                        e.toolTip.add("§8Failed to Fetch Action/Bazaar Data.");
                    }
                }
                if (iterations >= 10) {
                    e.toolTip.add("§7" + (iterations - 10) + " more enchants... ");
                }
            }
            else if (id.equals("PET")) {
                if (ItemUtils.getPetInfo(hoveredItem) != null) {
                    final NBTTagCompound info = compound.getCompoundTag("ExtraAttributes");
                    final JsonObject petInfo = (JsonObject)this.gson.fromJson(info.getString("petInfo"), (Class)JsonObject.class);
                    String petID = "idk";
                    if (petInfo != null) {
                        if (petInfo.has("type") && petInfo.has("tier")) {
                            petID = "PET_" + petInfo.get("type").getAsString() + "_" + petInfo.get("tier").getAsString();
                        }
                        if (petID != "idk") {
                            final AhBzManager.AuctionData auctionData2 = AhBzManager.auctions.get(petID);
                            if (auctionData2 != null && auctionData2.getSellPrice() == -1L) {
                                e.toolTip.add("§" + FetchLBinData.colorPrefix + "Lowest Bin§7: " + ((auctionData2.getPrices().size() != 0) ? ("§e" + this.format(auctionData2.getPrices().first())) : "§8N/A"));
                                if (this.hbin.getValue()) {
                                    e.toolTip.add("§" + FetchLBinData.colorPrefix + "Highest Bin§7: " + ((auctionData2.getPrices().size() != 0) ? ("§e" + this.format(auctionData2.getPrices().last())) : "§8N/A"));
                                }
                            }
                        }
                    }
                }
            }
            else if (id.equals("RUNE")) {
                final NBTTagCompound runes = compound.getCompoundTag("ExtraAttributes").getCompoundTag("runes");
                final Set<String> keys = (Set<String>)runes.getKeySet();
                for (final String key2 : keys) {
                    final String id3 = "RUNE_" + key2.toUpperCase() + "_" + runes.getInteger(key2);
                    final AhBzManager.AuctionData auctionData3 = AhBzManager.auctions.get(id3);
                    if (auctionData3 != null && auctionData3.getSellPrice() == -1L) {
                        e.toolTip.add("§" + FetchLBinData.colorPrefix + "Lowest Bin§7: " + ((auctionData3.getPrices().size() != 0) ? ("§e" + this.format(auctionData3.getPrices().first())) : "§8N/A"));
                        if (!(boolean)this.hbin.getValue()) {
                            continue;
                        }
                        e.toolTip.add("§" + FetchLBinData.colorPrefix + "Highest Bin§7: " + ((auctionData3.getPrices().size() != 0) ? ("§e" + this.format(auctionData3.getPrices().last())) : "§8N/A"));
                    }
                }
            }
            else if (id.equals("POTION")) {
                final NBTTagCompound attributes = compound.getCompoundTag("ExtraAttributes");
                if (attributes.hasKey("potion") && attributes.hasKey("potion_level")) {
                    final String enhanced = attributes.hasKey("enhanced") ? "ENHANCED" : "NOTENHANCED";
                    final String extended = attributes.hasKey("extended") ? "EXTENDED" : "UNEXTENDED";
                    final String splash = attributes.hasKey("splash") ? "SPLASH" : "DRINKABLE";
                    final String pot_id = "POTION_" + attributes.getString("potion").toUpperCase() + "_" + attributes.getInteger("potion_level") + "_" + enhanced + "_" + extended + "_" + splash;
                    final AhBzManager.AuctionData auctionData3 = AhBzManager.auctions.get(pot_id);
                    if (auctionData3 != null && auctionData3.getSellPrice() == -1L) {
                        e.toolTip.add("§" + FetchLBinData.colorPrefix + "Lowest Bin§7: " + ((auctionData3.getPrices().size() != 0) ? ("§e" + this.format(auctionData3.getPrices().first())) : "§8N/A"));
                        if (this.hbin.getValue()) {
                            e.toolTip.add("§" + FetchLBinData.colorPrefix + "Highest Bin§7: " + ((auctionData3.getPrices().size() != 0) ? ("§e" + this.format(auctionData3.getPrices().last())) : "§8N/A"));
                        }
                    }
                }
            }
            else {
                final AhBzManager.AuctionData auctionData4 = AhBzManager.auctions.get(id);
                e.toolTip.add("§f");
                if (auctionData4 == null) {
                    e.toolTip.add("§8Failed to Fetch Action/Bazaar Data.");
                }
                else if (auctionData4.getSellPrice() == -1L) {
                    e.toolTip.add("§" + FetchLBinData.colorPrefix + "Lowest Bin§7: " + ((auctionData4.getPrices().size() != 0) ? ("§e" + this.format(auctionData4.getPrices().first())) : "§8N/A"));
                    if (this.hbin.getValue()) {
                        e.toolTip.add("§" + FetchLBinData.colorPrefix + "Highest Bin§7: " + ((auctionData4.getPrices().size() != 0) ? ("§e" + this.format(auctionData4.getPrices().last())) : "§8N/A"));
                    }
                }
                else if (auctionData4.getPrices().size() == 0) {
                    e.toolTip.add("§" + FetchLBinData.colorPrefix + "Bazaar Sell§7: " + ((auctionData4.getSellPrice() == -1L) ? "§8N/A" : ("§e" + this.format(auctionData4.getSellPrice()))));
                    e.toolTip.add("§" + FetchLBinData.colorPrefix + "Bazaar Buy§7: " + ((auctionData4.getBuyPrice() == -1L) ? "§8N/A" : ("§e" + this.format(auctionData4.getBuyPrice()))));
                }
                else {
                    e.toolTip.add("§8Failed to Fetch Action/Bazaar Data.");
                }
            }
        }
    }
    
    public String format(final long value) {
        if (!(boolean)this.fmt.getValue()) {
            final String number = value + "";
            final StringBuffer numStr = new StringBuffer(number);
            for (int i = number.length() - 3; i >= 0; i -= 3) {
                numStr.insert(i, ",");
            }
            String finalStr = numStr.toString();
            if (finalStr.startsWith(",")) {
                finalStr = finalStr.replaceFirst(",", "");
            }
            return finalStr;
        }
        if (value == Long.MIN_VALUE) {
            return this.format(-9223372036854775807L);
        }
        if (value < 0L) {
            return "-" + this.format(-value);
        }
        if (value < 1000L) {
            return Long.toString(value);
        }
        final Map.Entry<Long, String> e = FetchLBinData.suffixes.floorEntry(value);
        final Long divideBy = e.getKey();
        final String suffix = e.getValue();
        final long truncated = value / (divideBy / 10L);
        final boolean hasDecimal = truncated < 100L && truncated / 10.0 != truncated / 10L;
        return hasDecimal ? (truncated / 10.0 + suffix) : (truncated / 10L + suffix);
    }
    
    static {
        FetchLBinData.colorPrefix = "6";
        (suffixes = new TreeMap<Long, String>()).put(1000L, "k");
        FetchLBinData.suffixes.put(1000000L, "m");
        FetchLBinData.suffixes.put(1000000000L, "b");
    }
}
