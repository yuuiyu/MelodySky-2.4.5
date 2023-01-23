//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import java.util.regex.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.Item.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.System.Managers.Auctions.*;
import net.minecraft.nbt.*;
import java.util.*;

public class DungeonChestProfit extends Module
{
    private Option<Boolean> format;
    private Option<Boolean> showCost;
    private final Pattern STRIP_COLOR_PATTERN;
    private static final NavigableMap<Long, String> suffixes;
    
    public DungeonChestProfit() {
        super("ChestProfit", new String[] { "as" }, ModuleType.Dungeons);
        this.format = (Option<Boolean>)new Option("Format", (Object)false);
        this.showCost = (Option<Boolean>)new Option("ShowCost", (Object)false);
        this.STRIP_COLOR_PATTERN = Pattern.compile("(?i)\ufffd\ufffd[0-9A-FK-OR]");
        this.addValues(new Value[] { (Value)this.format, (Value)this.showCost });
        this.setModInfo("Dungeon Chest Profit.");
    }
    
    @SubscribeEvent
    public void onGuiBGRender(final GuiScreenEvent.BackgroundDrawnEvent rendered) {
        if (!(rendered.gui instanceof GuiChest)) {
            return;
        }
        GlStateManager.disableLighting();
        if (!Client.inDungeons) {
            return;
        }
        final ContainerChest chest = (ContainerChest)((GuiChest)rendered.gui).inventorySlots;
        final String chestName = chest.getLowerChestInventory().getName();
        final String wood = "Wood Chest";
        final String gold = "Gold Chest";
        final String diamond = "Diamond Chest";
        final String emerald = "Emerald Chest";
        final String obsidian = "Obsidian Chest";
        final String bedrock = "Bedrock Chest";
        if (!chestName.equals(wood) && !chestName.equals(gold) && !chestName.equals(diamond) && !chestName.equals(emerald) && !chestName.equals(obsidian) && !chestName.equals(bedrock)) {
            return;
        }
        final IInventory actualChest = chest.getLowerChestInventory();
        int chestCost = 0;
        int itemPrice = 0;
        for (int i = 0; i < actualChest.getSizeInventory(); ++i) {
            final ItemStack item = actualChest.getStackInSlot(i);
            if (item != null) {
                itemPrice += (int)(this.getPrice(item) * item.stackSize);
            }
        }
        final ItemStack rewardChest = actualChest.getStackInSlot(31);
        if (rewardChest != null && rewardChest.getDisplayName().endsWith(EnumChatFormatting.GREEN + "Open Reward Chest")) {
            try {
                final String line6 = this.cleanColor(ItemUtils.getLoreFromNBT(rewardChest.getTagCompound())[6]);
                final StringBuilder cost = new StringBuilder();
                for (int j = 0; j < line6.length(); ++j) {
                    final char c = line6.charAt(j);
                    if ("0123456789".indexOf(c) >= 0) {
                        cost.append(c);
                    }
                }
                if (cost.length() > 0) {
                    chestCost = Integer.parseInt(cost.toString());
                }
            }
            catch (Exception ex) {}
        }
        final int k = 222;
        final int l = k - 108;
        final int ySize = l + actualChest.getSizeInventory() / 9 * 18;
        final int left = (rendered.gui.width - 10) / 2;
        final int top = (rendered.gui.height - ySize - 18) / 2;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)left, (float)top, 0.0f);
        final FontRenderer fr = this.mc.fontRendererObj;
        final String str = ((itemPrice > chestCost) ? "+" : "") + this.format(itemPrice - chestCost);
        fr.drawString("Profit: " + ((itemPrice > chestCost) ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + str, 5, 15, -1);
        if (this.showCost.getValue()) {
            fr.drawString("Cost: " + EnumChatFormatting.RED + chestCost, 5, -2, -1);
        }
        GlStateManager.popMatrix();
    }
    
    public long getPrice(final ItemStack itemStack) {
        if (itemStack == null) {
            return 0L;
        }
        final NBTTagCompound compound = itemStack.getTagCompound();
        if (compound == null) {
            return 0L;
        }
        if (!compound.hasKey("ExtraAttributes")) {
            return 0L;
        }
        final String id = compound.getCompoundTag("ExtraAttributes").getString("id");
        if (id.equals("ENCHANTED_BOOK")) {
            final NBTTagCompound enchants = compound.getCompoundTag("ExtraAttributes").getCompoundTag("enchantments");
            final Set<String> keys = (Set<String>)enchants.getKeySet();
            final Iterator<String> iterator = keys.iterator();
            if (!iterator.hasNext()) {
                return 0L;
            }
            final String key = iterator.next();
            final String id2 = "ENCHANTMENT_" + key.toUpperCase() + "_" + enchants.getInteger(key);
            final AhBzManager.AuctionData auctionData = AhBzManager.auctions.get(id2);
            if (auctionData == null) {
                return 0L;
            }
            return auctionData.getSellPrice();
        }
        else {
            final AhBzManager.AuctionData auctionData2 = AhBzManager.auctions.get(id);
            if (auctionData2 == null) {
                return 0L;
            }
            if (auctionData2.getSellPrice() == -1L) {
                return (auctionData2.getPrices().size() != 0) ? auctionData2.getPrices().first() : 0L;
            }
            if (auctionData2.getPrices().size() == 0) {
                return auctionData2.getSellPrice();
            }
            return 0L;
        }
    }
    
    public String stripColor(final String input) {
        return this.STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }
    
    public String cleanColor(final String in) {
        return in.replaceAll("(?i)\\u00A7.", "");
    }
    
    public String format(final long value) {
        if (!(boolean)this.format.getValue()) {
            final String number = value + "";
            final StringBuffer numStr = new StringBuffer(number);
            for (int i = number.length() - 3; i >= 0; i -= 3) {
                numStr.insert(i, ",");
            }
            String finalStr = numStr.toString();
            if (finalStr.startsWith(",")) {
                finalStr = finalStr.replaceFirst(",", "");
            }
            if (finalStr.startsWith("-,")) {
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
        final Map.Entry<Long, String> e = DungeonChestProfit.suffixes.floorEntry(value);
        final Long divideBy = e.getKey();
        final String suffix = e.getValue();
        final long truncated = value / (divideBy / 10L);
        final boolean hasDecimal = truncated < 100L && truncated / 10.0 != truncated / 10L;
        return hasDecimal ? (truncated / 10.0 + suffix) : (truncated / 10L + suffix);
    }
    
    static {
        (suffixes = new TreeMap<Long, String>()).put(1000L, "k");
        DungeonChestProfit.suffixes.put(1000000L, "m");
        DungeonChestProfit.suffixes.put(1000000000L, "b");
    }
}
