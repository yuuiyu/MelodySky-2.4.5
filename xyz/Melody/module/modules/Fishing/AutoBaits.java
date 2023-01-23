//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.Fishing;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraft.inventory.*;
import xyz.Melody.Event.*;
import net.minecraft.entity.player.*;

public class AutoBaits extends Module
{
    private boolean bazaarOpen;
    private boolean fishingCatOpen;
    private boolean baitsOpen;
    private boolean fishBaitsOpen;
    private boolean buyingFishBaits;
    private boolean boughtBaits;
    private boolean petOpen;
    private boolean sbmenuOpen;
    private boolean baitStorageOpen;
    private Numbers<Double> tickValue;
    private Option<Boolean> openFBag;
    private int ticks;
    
    public AutoBaits() {
        super("AutoFishBaits", new String[] { "afb" }, ModuleType.Fishing);
        this.bazaarOpen = false;
        this.fishingCatOpen = false;
        this.baitsOpen = false;
        this.fishBaitsOpen = false;
        this.buyingFishBaits = false;
        this.boughtBaits = false;
        this.petOpen = false;
        this.sbmenuOpen = false;
        this.baitStorageOpen = false;
        this.tickValue = (Numbers<Double>)new Numbers("ClickTicks", (Number)20.0, (Number)10.0, (Number)60.0, (Number)5.0);
        this.openFBag = (Option<Boolean>)new Option("OpenBaitsBag", (Object)true);
        this.ticks = 0;
        this.addValues(new Value[] { (Value)this.tickValue, (Value)this.openFBag });
        this.setColor(new Color(191, 191, 191).getRGB());
        this.setModInfo("Auto Buy Fish Baits(Fill Inv).");
    }
    
    public void onEnable() {
        this.openBazaar();
        super.onEnable();
    }
    
    public void onDisable() {
        this.reset();
        super.onDisable();
    }
    
    @EventHandler
    public void onDrawGuiBackground(final EventTick event) {
        if (this.ticks < ((Double)this.tickValue.getValue()).intValue()) {
            ++this.ticks;
            return;
        }
        this.ticks = 0;
        final GuiScreen gui = this.mc.currentScreen;
        if (Client.inSkyblock && gui instanceof GuiChest) {
            final Container container = ((GuiChest)gui).inventorySlots;
            if (container instanceof ContainerChest) {
                final String chestName = this.getGuiName(gui);
                if (chestName.startsWith("Bazaar") && !this.bazaarOpen) {
                    this.bazaarOpen = true;
                }
                if (this.bazaarOpen && !this.fishingCatOpen) {
                    this.clickSlot(27, 0);
                    this.fishingCatOpen = true;
                    return;
                }
                if (this.fishingCatOpen && !this.baitsOpen) {
                    this.clickSlot(30, 0);
                    this.baitsOpen = true;
                    return;
                }
                if (this.baitsOpen && !this.fishBaitsOpen) {
                    this.clickSlot(12, 0);
                    this.fishBaitsOpen = true;
                    return;
                }
                if (this.fishBaitsOpen && !this.buyingFishBaits) {
                    this.clickSlot(10, 0);
                    this.buyingFishBaits = true;
                    return;
                }
                if (this.buyingFishBaits && !this.boughtBaits) {
                    this.clickSlot(14, 0);
                    this.boughtBaits = true;
                    return;
                }
                if (this.boughtBaits && !this.petOpen) {
                    this.mc.thePlayer.closeScreen();
                    this.petOpen = true;
                    if (!(boolean)this.openFBag.getValue()) {
                        this.setEnabled(false);
                    }
                }
                if (this.openFBag.getValue()) {
                    if (this.sbmenuOpen && !this.baitStorageOpen) {
                        this.clickSlot(48, 0);
                        this.baitStorageOpen = true;
                        return;
                    }
                    if (this.baitStorageOpen) {
                        this.clickSlot(43, 0);
                        this.setEnabled(false);
                        return;
                    }
                }
            }
        }
        if (Client.inSkyblock && this.petOpen && !this.sbmenuOpen) {
            this.mc.thePlayer.sendChatMessage("/pet");
            this.sbmenuOpen = true;
        }
    }
    
    private void reset() {
        this.petOpen = false;
        this.baitsOpen = false;
        this.sbmenuOpen = false;
        this.bazaarOpen = false;
        this.boughtBaits = false;
        this.fishBaitsOpen = false;
        this.fishingCatOpen = false;
        this.buyingFishBaits = false;
        this.baitStorageOpen = false;
    }
    
    public void openBazaar() {
        this.mc.thePlayer.sendChatMessage("/bazaar");
        this.bazaarOpen = true;
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
