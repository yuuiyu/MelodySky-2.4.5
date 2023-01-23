//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.MainWorld;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.client.gui.inventory.*;
import xyz.Melody.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import java.util.*;
import xyz.Melody.Event.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.*;

public class MelodyPlayer extends Module
{
    private long lastInteractTime;
    public boolean click;
    private String harpTag;
    private Numbers<Double> delay;
    private Mode<Enum> clickMode;
    
    public MelodyPlayer() {
        super("AutoMelody", new String[] { "am" }, ModuleType.QOL);
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)100.0, (Number)0.0, (Number)300.0, (Number)10.0);
        this.clickMode = (Mode<Enum>)new Mode("ClickMode", (Enum[])cm.values(), (Enum)cm.Middle);
        this.addValues(new Value[] { (Value)this.clickMode, (Value)this.delay });
        this.setModInfo("Auto Play Melody.");
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (this.mc.currentScreen != null && this.mc.currentScreen instanceof GuiChest && Client.inSkyblock && this.getGuiName(this.mc.currentScreen).startsWith("Harp -")) {
            final ContainerChest chest = (ContainerChest)this.mc.thePlayer.openContainer;
            final IInventory chestInv = chest.getLowerChestInventory();
            final List<Slot> invSlots = (List<Slot>)chest.inventorySlots;
            if (!this.click) {
                final StringBuilder currentTag = new StringBuilder();
                for (int i = 1; i <= 34; ++i) {
                    if (chestInv.getStackInSlot(i) != null) {
                        currentTag.append(chestInv.getStackInSlot(i).getItem());
                    }
                }
                if (!currentTag.toString().equals(this.harpTag)) {
                    this.harpTag = currentTag.toString();
                    this.lastInteractTime = 0L;
                    this.click = true;
                }
            }
            if (this.click) {
                if (this.lastInteractTime == 0L) {
                    this.lastInteractTime = System.currentTimeMillis();
                    return;
                }
                if (System.currentTimeMillis() - this.lastInteractTime >= (double)this.delay.getValue()) {
                    int woolPos = -1;
                    Slot woolSlot = null;
                    for (int j = 28; j <= 34; ++j) {
                        if (chestInv.getStackInSlot(j) != null && chestInv.getStackInSlot(j).getItem() == Item.getItemFromBlock(Blocks.wool)) {
                            woolPos = j + 9;
                            woolSlot = invSlots.get(j + 9);
                            break;
                        }
                    }
                    if (woolPos == -1) {
                        this.lastInteractTime = 0L;
                        this.click = false;
                        return;
                    }
                    final String lowerCase = ((Enum)this.clickMode.getValue()).toString().toLowerCase();
                    switch (lowerCase) {
                        case "middle": {
                            this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, woolPos, 2, 3, (EntityPlayer)this.mc.thePlayer);
                            break;
                        }
                        case "left": {
                            this.windowClick(this.mc.thePlayer.openContainer.windowId, woolSlot, 0, 0);
                            break;
                        }
                        case "right": {
                            this.windowClick(this.mc.thePlayer.openContainer.windowId, woolSlot, 1, 0);
                            break;
                        }
                    }
                    this.lastInteractTime = 0L;
                    this.click = false;
                }
            }
        }
    }
    
    private void windowClick(final int windowId, final Slot slot, final int mouseButtonClicked, final int mode) {
        final short tid = this.mc.thePlayer.openContainer.getNextTransactionID(this.mc.thePlayer.inventory);
        final ItemStack itemstack = slot.getStack();
        this.mc.getNetHandler().addToSendQueue((Packet)new C0EPacketClickWindow(windowId, slot.slotNumber, mouseButtonClicked, mode, itemstack, tid));
    }
    
    public String getGuiName(final GuiScreen gui) {
        if (gui instanceof GuiChest) {
            return ((ContainerChest)((GuiChest)gui).inventorySlots).getLowerChestInventory().getDisplayName().getUnformattedText();
        }
        return "";
    }
    
    public String getInventoryName() {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return "null";
        }
        return this.mc.thePlayer.openContainer.inventorySlots.get(0).inventory.getName();
    }
    
    enum cm
    {
        Middle, 
        Left, 
        Right;
    }
}
