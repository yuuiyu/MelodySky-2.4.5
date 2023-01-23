//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.Fishing;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.*;
import xyz.Melody.GUI.Notification.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraft.inventory.*;
import xyz.Melody.Event.*;
import net.minecraft.entity.player.*;

public class AutoSellInv extends Module
{
    private boolean shouldOpenBazaar;
    private boolean bazaarOpen;
    private boolean clickedSellInv;
    private boolean clickSell1;
    private boolean clickedBack;
    private boolean clickedSellSack;
    private boolean clickSell2;
    private Numbers<Double> tickValue;
    private Option<Boolean> openFBag;
    private int ticks;
    
    public AutoSellInv() {
        super("AutoSellInv", new String[] { "afb" }, ModuleType.Fishing);
        this.shouldOpenBazaar = false;
        this.bazaarOpen = false;
        this.clickedSellInv = false;
        this.clickSell1 = false;
        this.clickedBack = false;
        this.clickedSellSack = false;
        this.clickSell2 = false;
        this.tickValue = (Numbers<Double>)new Numbers("ClickTicks", (Number)20.0, (Number)10.0, (Number)60.0, (Number)5.0);
        this.openFBag = (Option<Boolean>)new Option("Sack", (Object)true);
        this.ticks = 0;
        this.addValues(new Value[] { (Value)this.tickValue, (Value)this.openFBag });
        this.setColor(new Color(191, 191, 191).getRGB());
        this.setModInfo("Auto Sell Inventory(Sacks).");
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.reset();
        super.onDisable();
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onChat(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (message.toLowerCase().equals("your inventory is full!")) {
            NotificationPublisher.queue("Inventory Full!", "Trying To Sell Inv and Sacks.", NotificationType.WARN, 3000);
            this.reset();
            this.shouldOpenBazaar = true;
        }
    }
    
    @EventHandler
    public void onDrawGuiBackground(final EventTick event) {
        if (this.ticks < ((Double)this.tickValue.getValue()).intValue()) {
            ++this.ticks;
            return;
        }
        this.ticks = 0;
        if (this.shouldOpenBazaar) {
            this.openBazaar();
        }
        final GuiScreen gui = this.mc.currentScreen;
        if (Client.inSkyblock && gui instanceof GuiChest) {
            final Container container = ((GuiChest)gui).inventorySlots;
            if (container instanceof ContainerChest) {
                final String chestName = this.getGuiName(gui);
                if (chestName.startsWith("Bazaar") && !this.bazaarOpen) {
                    this.bazaarOpen = true;
                }
                if (this.bazaarOpen && !this.clickedSellInv) {
                    this.clickSlot(47, 0);
                    this.clickedSellInv = true;
                    return;
                }
                if (this.clickedSellInv && !this.clickSell1) {
                    if (this.getGuiName(gui).startsWith("Are")) {
                        this.clickSlot(11, 0);
                    }
                    this.clickSell1 = true;
                    return;
                }
                if (this.clickSell1 && !this.clickedBack) {
                    if (this.getGuiName(gui).startsWith("Are")) {
                        this.clickSlot(22, 0);
                    }
                    this.clickedBack = true;
                    return;
                }
                if (this.clickedBack && !this.clickedSellSack) {
                    this.clickSlot(48, 0);
                    this.clickedSellSack = true;
                    return;
                }
                if (this.clickedSellSack && !this.clickSell2) {
                    if (this.getGuiName(gui).startsWith("Are")) {
                        this.clickSlot(11, 0);
                    }
                    this.clickSell2 = true;
                    return;
                }
                if (this.clickSell2) {
                    this.mc.thePlayer.closeScreen();
                    this.reset();
                }
            }
        }
    }
    
    private void reset() {
        this.bazaarOpen = false;
        this.clickedSellInv = false;
        this.clickSell1 = false;
        this.clickedBack = false;
        this.clickedSellSack = false;
        this.clickSell2 = false;
    }
    
    public void openBazaar() {
        this.mc.thePlayer.sendChatMessage("/bazaar");
        this.bazaarOpen = true;
        this.shouldOpenBazaar = false;
    }
    
    public String getGuiName(final GuiScreen gui) {
        if (gui instanceof GuiChest) {
            return ((ContainerChest)((GuiChest)gui).inventorySlots).getLowerChestInventory().getDisplayName().getUnformattedText();
        }
        return "";
    }
    
    private void clickSlot(final int slot, final int incrementWindowId) {
        this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId + incrementWindowId, slot, 2, 3, (EntityPlayer)this.mc.thePlayer);
    }
}
