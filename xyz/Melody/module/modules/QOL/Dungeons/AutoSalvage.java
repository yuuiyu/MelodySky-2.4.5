//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import xyz.Melody.Utils.*;
import net.minecraft.block.*;
import java.util.*;
import xyz.Melody.Event.*;
import net.minecraft.inventory.*;
import xyz.Melody.Event.events.container.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.item.*;
import xyz.Melody.Utils.Item.*;
import net.minecraft.nbt.*;

public class AutoSalvage extends Module
{
    private boolean inSalvageGui;
    private boolean salvaging;
    private int tickCount;
    private int currentSlot;
    
    public AutoSalvage() {
        super("AutoSalvage", new String[] { "as" }, ModuleType.Dungeons);
        this.inSalvageGui = false;
        this.salvaging = false;
        this.tickCount = 0;
        this.currentSlot = 0;
        this.setModInfo("Auto Salvage Items.");
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        if (this.tickCount % 5 == 0 && this.inSalvageGui && this.salvaging && this.mc.currentScreen instanceof GuiChest) {
            final List<Slot> chestInventory = (List<Slot>)((GuiChest)this.mc.currentScreen).inventorySlots.inventorySlots;
            if (chestInventory != null && chestInventory.get(31).getStack() != null && chestInventory.get(22).getStack() != null) {
                if (chestInventory.get(22) != null && (chestInventory.get(22).getStack() != null & chestInventory.get(31).getStack().getItem() == Item.getItemFromBlock(Blocks.stained_hardened_clay))) {
                    this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, 31, 0, 0, (EntityPlayer)this.mc.thePlayer);
                    Helper.sendMessage("");
                }
                if (chestInventory.get(22) != null && chestInventory.get(22).getStack() != null && chestInventory.get(22) != null && chestInventory.get(22).getStack() != null && chestInventory.get(31).getStack().getItem() == Item.getItemFromBlock((Block)Blocks.beacon)) {
                    this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, 31, 0, 0, (EntityPlayer)this.mc.thePlayer);
                }
            }
            if (chestInventory.get(22).getStack() == null) {
                final ArrayList<Slot> itemsToSalvage = new ArrayList<Slot>(this.mc.thePlayer.inventoryContainer.inventorySlots);
                itemsToSalvage.removeIf(slot -> !shouldSalvage(slot.getStack()));
                if (itemsToSalvage.isEmpty()) {
                    this.salvaging = false;
                }
                else {
                    this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, 45 + itemsToSalvage.get(0).slotNumber, 0, 1, (EntityPlayer)this.mc.thePlayer);
                    this.currentSlot = 45 + itemsToSalvage.get(0).slotNumber;
                }
            }
        }
        ++this.tickCount;
    }
    
    @EventHandler
    public void onBackgroundRender(final EventTick event) {
        final GuiScreen gui = this.mc.currentScreen;
        if (gui instanceof GuiChest) {
            final Container container = ((GuiChest)gui).inventorySlots;
            if (container instanceof ContainerChest) {
                final String chestName = ((ContainerChest)container).getLowerChestInventory().getDisplayName().getUnformattedText();
                this.inSalvageGui = chestName.equals("Salvage Item");
                if (this.inSalvageGui) {
                    this.salvaging = true;
                }
                else {
                    this.salvaging = false;
                }
            }
        }
        else {
            this.inSalvageGui = false;
        }
    }
    
    @EventHandler
    public void onDrawSlot(final DrawSlotEvent event) {
        if (this.inSalvageGui && shouldSalvage(event.slot.getStack())) {
            final int x = event.slot.xDisplayPosition;
            final int y = event.slot.yDisplayPosition;
            Gui.drawRect(x, y, x + 16, y + 16, new Color(0, 255, 255, 120).getRGB());
        }
        if (this.inSalvageGui && event.slot.slotNumber == this.currentSlot) {
            final int x = event.slot.xDisplayPosition;
            final int y = event.slot.yDisplayPosition;
            Gui.drawRect(x, y, x + 16, y + 16, new Color(0, 105, 255, 120).getRGB());
        }
    }
    
    public static boolean shouldSalvage(final ItemStack item) {
        if (item == null) {
            return false;
        }
        final NBTTagCompound attributes = item.getSubCompound("ExtraAttributes", false);
        return attributes != null && attributes.hasKey("baseStatBoostPercentage") && !attributes.hasKey("dungeon_item_level") && !ItemUtils.getSkyBlockID(item).equals("ICE_SPRAY_WAND");
    }
}
