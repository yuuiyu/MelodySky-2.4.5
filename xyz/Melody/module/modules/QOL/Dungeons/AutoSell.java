//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import java.util.*;
import xyz.Melody.Event.*;
import net.minecraft.inventory.*;
import xyz.Melody.Event.events.container.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.item.*;
import xyz.Melody.Utils.Item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class AutoSell extends Module
{
    private Option<Boolean> DJ;
    private Option<Boolean> salvable;
    private Option<Boolean> boooom;
    private Option<Boolean> rs;
    private Option<Boolean> runes;
    private boolean inTradeMenu;
    private int tickCount;
    private static final String[] dungeonJunk;
    
    public AutoSell() {
        super("AutoSell", new String[] { "as" }, ModuleType.Dungeons);
        this.DJ = (Option<Boolean>)new Option("DungeonJunks", (Object)true);
        this.salvable = (Option<Boolean>)new Option("SalvageAble", (Object)false);
        this.boooom = (Option<Boolean>)new Option("SuperBoooom", (Object)true);
        this.rs = (Option<Boolean>)new Option("ReviveStones", (Object)true);
        this.runes = (Option<Boolean>)new Option("Runes", (Object)true);
        this.inTradeMenu = false;
        this.tickCount = 0;
        this.addValues(new Value[] { (Value)this.DJ, (Value)this.salvable, (Value)this.boooom, (Value)this.runes, (Value)this.rs });
        this.setModInfo("Auto Sell Useless Items.");
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        if (this.tickCount % 2 == 0 && Client.inSkyblock && this.inTradeMenu && this.mc.currentScreen instanceof GuiChest) {
            final List<Slot> chestInventory = (List<Slot>)((GuiChest)this.mc.currentScreen).inventorySlots.inventorySlots;
            if (chestInventory.get(49).getStack() != null && chestInventory.get(49).getStack().getItem() != Item.getItemFromBlock(Blocks.barrier)) {
                for (final Slot slot : this.mc.thePlayer.inventoryContainer.inventorySlots) {
                    if (this.shouldSell(slot.getStack())) {
                        this.windowClick(this.mc.thePlayer.openContainer.windowId, slot, 2, 3);
                        break;
                    }
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
                this.inTradeMenu = chestName.equals("Trades");
            }
        }
    }
    
    @EventHandler
    public void onDrawSlot(final DrawSlotEvent event) {
        if (this.inTradeMenu && this.shouldSell(event.slot.getStack())) {
            final int x = event.slot.xDisplayPosition;
            final int y = event.slot.yDisplayPosition;
            Gui.drawRect(x, y, x + 16, y + 16, new Color(128, 0, 128, 120).getRGB());
        }
    }
    
    private boolean shouldSell(final ItemStack item) {
        if (item != null) {
            if ((boolean)this.salvable.getValue() && AutoSalvage.shouldSalvage(item)) {
                return true;
            }
            if ((boolean)this.boooom.getValue() && ItemUtils.getSkyBlockID(item).equals("SUPERBOOM_TNT")) {
                return true;
            }
            if ((boolean)this.rs.getValue() && ItemUtils.getSkyBlockID(item).equals("REVIVE_STONE")) {
                return true;
            }
            if ((boolean)this.runes.getValue() && item.getDisplayName().contains("Rune")) {
                return true;
            }
            if (this.DJ.getValue()) {
                for (final String name : AutoSell.dungeonJunk) {
                    if (item.getDisplayName().contains(name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void windowClick(final int windowId, final Slot slot, final int mouseButtonClicked, final int mode) {
        final short tid = this.mc.thePlayer.openContainer.getNextTransactionID(this.mc.thePlayer.inventory);
        final ItemStack itemstack = slot.getStack();
        this.mc.getNetHandler().addToSendQueue((Packet)new C0EPacketClickWindow(windowId, 45 + slot.slotNumber, mouseButtonClicked, mode, itemstack, tid));
    }
    
    static {
        dungeonJunk = new String[] { "Training Weight", "Healing VIII Splash Potion", "Healing 8 Slash Potion", "Premium Flesh", "Mimic Fragment", "Enchanted Rotten Flesh", "Enchanted Bone", "Defuse Kit", "Enchanted Ice", "Optic Lense", "Tripwire Hook", "Button", "Carpet", "Lever", "Journal Entry", "Sign" };
    }
}
