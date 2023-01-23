//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.inventory.*;
import xyz.Melody.Event.*;
import net.minecraft.init.*;
import xyz.Melody.Utils.Item.*;
import java.util.concurrent.atomic.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;
import java.util.concurrent.*;

public class CombindBooks extends Module
{
    private static final Map<String, Integer> books;
    public static boolean threadRunning;
    private Numbers<Double> delay;
    
    public CombindBooks() {
        super("CombindBooks", new String[] { "cb" }, ModuleType.QOL);
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)200.0, (Number)100.0, (Number)1000.0, (Number)10.0);
        this.addValues(new Value[] { (Value)this.delay });
        this.setColor(new Color(158, 205, 125).getRGB());
        this.setModInfo("Auto Combind Enchant Books.");
    }
    
    @SubscribeEvent
    public void onOpenGui(final GuiOpenEvent event) {
        CombindBooks.books.clear();
        CombindBooks.threadRunning = false;
    }
    
    @EventHandler
    public void onGuiDraw(final EventTick event) {
        final GuiScreen gui = this.mc.currentScreen;
        if (gui instanceof GuiChest) {
            final Container container = ((GuiChest)gui).inventorySlots;
            if (container instanceof ContainerChest) {
                final String chestName = ((ContainerChest)container).getLowerChestInventory().getDisplayName().getUnformattedText();
                if (chestName.contains("Anvil")) {
                    final List<Slot> chestInventory = (List<Slot>)((GuiChest)this.mc.currentScreen).inventorySlots.inventorySlots;
                    this.combineBooks(chestInventory);
                }
            }
        }
    }
    
    public void combineBooks(final List<Slot> invSlots) {
        if (CombindBooks.threadRunning) {
            return;
        }
        for (int i = 54; i <= 89; ++i) {
            final Slot slot = invSlots.get(i);
            if (slot.getStack() != null && slot.getStack().getItem() == Items.enchanted_book) {
                final NBTTagCompound extraAttr = ItemUtils.getExtraAttributes(slot.getStack());
                final NBTTagCompound enchantments = extraAttr.getCompoundTag("enchantments");
                if (enchantments.getKeySet().size() == 1) {
                    if (CombindBooks.books.containsKey(enchantments.toString()) && CombindBooks.books.get(enchantments.toString()) != i) {
                        if (invSlots.get(CombindBooks.books.get(enchantments.toString())).getStack() != null) {
                            final AtomicInteger atomicInteger = new AtomicInteger(i);
                            CombindBooks.threadRunning = true;
                            final String pair = enchantments.toString();
                            final String name;
                            final AtomicInteger atomicInteger2;
                            new Thread(() -> {
                                this.sleep(150 + ((Double)this.delay.getValue()).intValue());
                                if (this.mc.currentScreen != null) {
                                    this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, (int)CombindBooks.books.get(name), 0, 1, (EntityPlayer)this.mc.thePlayer);
                                }
                                CombindBooks.books.remove(name);
                                this.sleep(300 + ((Double)this.delay.getValue()).intValue());
                                if (invSlots.get(atomicInteger2.get()).getStack() == null) {
                                    atomicInteger2.set(this.fix(name, invSlots));
                                }
                                if (this.mc.currentScreen != null) {
                                    this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, atomicInteger2.get(), 0, 1, (EntityPlayer)this.mc.thePlayer);
                                }
                                while (invSlots.get(13).getStack().getItem() != Items.enchanted_book) {
                                    if (this.mc.currentScreen == null) {
                                        return;
                                    }
                                }
                                this.sleep(50);
                                if (this.mc.currentScreen != null) {
                                    this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, 22, 2, 3, (EntityPlayer)this.mc.thePlayer);
                                }
                                this.sleep(250 + ((Double)this.delay.getValue()).intValue());
                                if (this.mc.currentScreen != null) {
                                    this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, 13, 0, 1, (EntityPlayer)this.mc.thePlayer);
                                }
                                this.sleep(50);
                                CombindBooks.threadRunning = false;
                            }).start();
                            return;
                        }
                        CombindBooks.books.remove(enchantments.toString());
                    }
                    else {
                        int value;
                        try {
                            value = Integer.parseInt(String.valueOf(enchantments.toString().charAt(enchantments.toString().indexOf(":") + 2)));
                        }
                        catch (Exception e) {
                            value = Integer.parseInt(String.valueOf(enchantments.toString().charAt(enchantments.toString().indexOf(":") + 1)));
                        }
                        if (enchantments.toString().contains("feather_falling") || enchantments.toString().contains("infinite_quiver")) {
                            if (value >= 10) {
                                continue;
                            }
                        }
                        else if (value >= 5) {
                            continue;
                        }
                        CombindBooks.books.put(enchantments.toString(), i);
                    }
                }
            }
        }
    }
    
    private int fix(final String name, final List<Slot> invSlots) {
        for (int i = 54; i <= 89; ++i) {
            final Slot slot = invSlots.get(i);
            if (slot.getStack() != null && slot.getStack().getItem() == Items.enchanted_book) {
                final NBTTagCompound extraAttr = ItemUtils.getExtraAttributes(slot.getStack());
                final NBTTagCompound enchantments = extraAttr.getCompoundTag("enchantments");
                if (enchantments.getKeySet().size() == 1 && enchantments.toString().equals(name)) {
                    return i;
                }
            }
        }
        return 0;
    }
    
    public void sleep(final int sleeptime) {
        try {
            Thread.sleep(sleeptime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    static {
        books = new ConcurrentHashMap<String, Integer>();
    }
}
