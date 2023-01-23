//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.misc.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import org.apache.commons.lang3.math.*;
import net.minecraft.client.entity.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class AutoTerminals extends Module
{
    private Option<Boolean> delayy;
    private Numbers<Double> delay;
    private TimerUtil timer;
    private ArrayList<Slot> clickQueue;
    private long lastClickTime;
    public TerminalType currentTerminal;
    private int windowId;
    private int windowClicks;
    private boolean recalculate;
    private Option<Boolean> debug;
    private Option<Boolean> maze;
    private Option<Boolean> numbers;
    private Option<Boolean> ca;
    private Option<Boolean> letter;
    private Option<Boolean> color;
    private Option<Boolean> cot;
    private Option<Boolean> sameColor;
    private String letterNeeded;
    private int targetColorIndex;
    static List<Integer> colorOrder;
    private boolean foundColor;
    private int correctColor;
    
    public AutoTerminals() {
        super("AutoTerminals", new String[] { "at" }, ModuleType.Dungeons);
        this.delayy = (Option<Boolean>)new Option("NoDelay", (Object)true);
        this.delay = (Numbers<Double>)new Numbers("ClickDelay", (Number)120.0, (Number)1.0, (Number)200.0, (Number)1.0);
        this.timer = new TimerUtil();
        this.clickQueue = new ArrayList<Slot>(28);
        this.lastClickTime = 0L;
        this.currentTerminal = TerminalType.NONE;
        this.windowId = 0;
        this.windowClicks = 0;
        this.recalculate = false;
        this.debug = (Option<Boolean>)new Option("Debug", (Object)false);
        this.maze = (Option<Boolean>)new Option("Maze", (Object)true);
        this.numbers = (Option<Boolean>)new Option("ClickInOrder", (Object)true);
        this.ca = (Option<Boolean>)new Option("CorrectAll", (Object)true);
        this.letter = (Option<Boolean>)new Option("Letter", (Object)true);
        this.color = (Option<Boolean>)new Option("SelectColor", (Object)true);
        this.cot = (Option<Boolean>)new Option("ClickOnTime", (Object)true);
        this.sameColor = (Option<Boolean>)new Option("SameColor", (Object)true);
        this.letterNeeded = null;
        this.targetColorIndex = -1;
        this.foundColor = false;
        this.addValues(new Value[] { (Value)this.debug, (Value)this.delayy, (Value)this.delay, (Value)this.maze, (Value)this.numbers, (Value)this.ca, (Value)this.letter, (Value)this.color, (Value)this.cot, (Value)this.sameColor });
        this.setModInfo("Auto Do Terminals in F7/M7.");
    }
    
    @EventHandler
    private void onClickSlot(final EventClickSlot event) {
        if (this.debug.getValue()) {
            Helper.sendMessage("WindowID: " + event.getWindowID() + ", SlotNumber: " + event.getSlotNumber() + ", Button: " + event.getButton() + ", Mode: " + event.getMode() + ", Player: " + event.getPlayer());
        }
    }
    
    @EventHandler
    public void onGuiDraw(final EventTick event) {
        final GuiScreen gui = this.mc.currentScreen;
        if (gui instanceof GuiChest) {
            final Container container = ((GuiChest)gui).inventorySlots;
            if (container instanceof ContainerChest) {
                final List<Slot> invSlots = (List<Slot>)container.inventorySlots;
                if (this.currentTerminal == TerminalType.NONE) {
                    final String chestName = ((ContainerChest)container).getLowerChestInventory().getDisplayName().getUnformattedText();
                    if (chestName.equals("Navigate the maze!")) {
                        this.currentTerminal = TerminalType.MAZE;
                    }
                    else if (chestName.equals("Click in order!")) {
                        this.currentTerminal = TerminalType.NUMBERS;
                    }
                    else if (chestName.equals("Correct all the panes!")) {
                        this.currentTerminal = TerminalType.CORRECT_ALL;
                    }
                    else if (chestName.startsWith("What starts with: '")) {
                        this.currentTerminal = TerminalType.LETTER;
                    }
                    else if (chestName.startsWith("Select all the")) {
                        this.currentTerminal = TerminalType.COLOR;
                    }
                    else if (chestName.startsWith("Click the button on time!")) {
                        this.currentTerminal = TerminalType.TIMING;
                    }
                    else if (chestName.startsWith("Change all to same color!")) {
                        this.currentTerminal = TerminalType.CHANGEATSC;
                    }
                }
                if (this.currentTerminal != TerminalType.NONE) {
                    if (this.currentTerminal == TerminalType.TIMING && (boolean)this.cot.getValue()) {
                        this.timingClicks((ContainerChest)container);
                    }
                    if (this.clickQueue.isEmpty() || this.recalculate) {
                        this.recalculate = this.getClicks((ContainerChest)container);
                    }
                    else {
                        switch (lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[this.currentTerminal.ordinal()]) {
                            case 1:
                            case 2:
                            case 3: {
                                final List<Slot> list;
                                this.clickQueue.removeIf(slot -> list.get(slot.slotNumber).getHasStack() && list.get(slot.slotNumber).getStack().getItemDamage() == 5);
                                break;
                            }
                            case 4:
                            case 5: {
                                final List<Slot> list2;
                                this.clickQueue.removeIf(slot -> list2.get(slot.slotNumber).getHasStack() && list2.get(slot.slotNumber).getStack().isItemEnchanted());
                                break;
                            }
                            case 6: {
                                final List<Slot> list3;
                                this.clickQueue.removeIf(slot -> list3.get(slot.slotNumber).getHasStack() && list3.get(slot.slotNumber).getStack().getItemDamage() == this.targetColorIndex);
                                break;
                            }
                        }
                    }
                    if (!this.clickQueue.isEmpty() && System.currentTimeMillis() - this.lastClickTime > ((Double)this.delay.getValue()).longValue()) {
                        this.clickSlot(this.clickQueue.get(0));
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onMaxFans(final EventTick event) {
        if (this.foundColor || this.currentTerminal != TerminalType.CHANGEATSC) {
            return;
        }
        final EntityPlayerSP player = this.mc.thePlayer;
        if (this.mc.currentScreen instanceof GuiChest) {
            if (player == null) {
                return;
            }
            final ContainerChest chest = (ContainerChest)player.openContainer;
            final List<Slot> invSlots = (List<Slot>)((GuiChest)this.mc.currentScreen).inventorySlots.inventorySlots;
            final String chestName = chest.getLowerChestInventory().getDisplayName().getUnformattedText().trim();
            if (chestName.equals("Change all to same color!")) {
                int red = 0;
                int orange = 0;
                int yellow = 0;
                int green = 0;
                int blue = 0;
                for (int i = 12; i <= 32; ++i) {
                    final ItemStack stack = invSlots.get(i).getStack();
                    if (stack != null) {
                        if (stack.getItem() == Item.getItemFromBlock((Block)Blocks.stained_glass_pane)) {
                            if (stack.getItemDamage() != 7) {
                                switch (stack.getItemDamage()) {
                                    case 1: {
                                        ++orange;
                                        break;
                                    }
                                    case 4: {
                                        ++yellow;
                                        break;
                                    }
                                    case 11: {
                                        ++blue;
                                        break;
                                    }
                                    case 13: {
                                        ++green;
                                        break;
                                    }
                                    case 14: {
                                        ++red;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                final int max = NumberUtils.max(new int[] { red, orange, yellow, green, blue });
                if (max == red) {
                    this.correctColor = 14;
                }
                else if (max == orange) {
                    this.correctColor = 1;
                }
                else if (max == yellow) {
                    this.correctColor = 4;
                }
                else if (max == green) {
                    this.correctColor = 13;
                }
                else {
                    this.correctColor = 11;
                }
                this.foundColor = true;
            }
        }
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        if (!(this.mc.currentScreen instanceof GuiChest)) {
            this.reset();
        }
    }
    
    private void reset() {
        this.currentTerminal = TerminalType.NONE;
        this.clickQueue.clear();
        this.foundColor = false;
        this.correctColor = 0;
        this.windowClicks = 0;
    }
    
    private boolean getClicks(final ContainerChest container) {
        final List<Slot> invSlots = (List<Slot>)container.inventorySlots;
        final String chestName = container.getLowerChestInventory().getDisplayName().getUnformattedText().trim();
        this.clickQueue.clear();
        Label_1377: {
            switch (lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[this.currentTerminal.ordinal()]) {
                case 1: {
                    if (this.maze.getValue()) {
                        final int[] mazeDirection = { -9, -1, 1, 9 };
                        final boolean[] isStartSlot = new boolean[54];
                        int endSlot = -1;
                        for (final Slot slot : invSlots) {
                            final ItemStack itemStack;
                            if (slot.inventory != this.mc.thePlayer.inventory && (itemStack = slot.getStack()) != null) {
                                if (itemStack.getItem() != Item.getItemFromBlock((Block)Blocks.stained_glass_pane)) {
                                    continue;
                                }
                                if (itemStack.getItemDamage() == 5) {
                                    isStartSlot[slot.slotNumber] = true;
                                }
                                else {
                                    if (itemStack.getItemDamage() != 14) {
                                        continue;
                                    }
                                    endSlot = slot.slotNumber;
                                }
                            }
                        }
                        for (int slot2 = 0; slot2 < 54; ++slot2) {
                            if (isStartSlot[slot2]) {
                                final boolean[] mazeVisited = new boolean[54];
                                int startSlot = slot2;
                                while (startSlot != endSlot) {
                                    boolean newSlotChosen = false;
                                    for (final int i : mazeDirection) {
                                        final int nextSlot = startSlot + i;
                                        if (nextSlot >= 0 && nextSlot <= 53 && (i != -1 || startSlot % 9 != 0)) {
                                            if (i != 1 || startSlot % 9 != 8) {
                                                if (nextSlot == endSlot) {
                                                    return false;
                                                }
                                                final ItemStack itemStack2;
                                                if (!mazeVisited[nextSlot] && (itemStack2 = invSlots.get(nextSlot).getStack()) != null && itemStack2.getItem() == Item.getItemFromBlock((Block)Blocks.stained_glass_pane)) {
                                                    if (itemStack2.getItemDamage() == 0) {
                                                        this.clickQueue.add(invSlots.get(nextSlot));
                                                        startSlot = nextSlot;
                                                        mazeVisited[nextSlot] = true;
                                                        newSlotChosen = true;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (newSlotChosen) {
                                        continue;
                                    }
                                    System.out.println("Maze calculation aborted");
                                    return true;
                                }
                            }
                        }
                        return true;
                    }
                }
                case 2: {
                    if (!(boolean)this.numbers.getValue()) {
                        break Label_1377;
                    }
                    int neededClick = 0;
                    final Slot[] slotOrder = new Slot[14];
                    for (int j = 10; j <= 25; ++j) {
                        final ItemStack itemStack3;
                        if (j != 17 && j != 18 && (itemStack3 = invSlots.get(j).getStack()) != null && itemStack3.getItem() == Item.getItemFromBlock((Block)Blocks.stained_glass_pane)) {
                            if (itemStack3.stackSize < 15) {
                                if (itemStack3.getItemDamage() == 14) {
                                    slotOrder[itemStack3.stackSize - 1] = invSlots.get(j);
                                }
                                else if (itemStack3.getItemDamage() == 5) {
                                    if (neededClick < itemStack3.stackSize) {
                                        neededClick = itemStack3.stackSize;
                                    }
                                }
                            }
                        }
                    }
                    this.clickQueue.addAll(Arrays.stream(slotOrder).filter(Objects::nonNull).collect((Collector<? super Slot, ?, Collection<? extends Slot>>)Collectors.toList()));
                    if (this.clickQueue.size() == 14 - neededClick) {
                        break;
                    }
                    return true;
                }
                case 3: {
                    if (this.ca.getValue()) {
                        for (final Slot slot3 : invSlots) {
                            if (slot3.inventory != this.mc.thePlayer.inventory && slot3.slotNumber >= 9 && slot3.slotNumber <= 35 && slot3.slotNumber % 9 > 1) {
                                if (slot3.slotNumber % 9 >= 7) {
                                    continue;
                                }
                                final ItemStack itemStack4 = slot3.getStack();
                                if (itemStack4 == null) {
                                    return true;
                                }
                                if (itemStack4.getItem() != Item.getItemFromBlock((Block)Blocks.stained_glass_pane)) {
                                    continue;
                                }
                                if (itemStack4.getItemDamage() != 14) {
                                    continue;
                                }
                                this.clickQueue.add(slot3);
                            }
                        }
                        break;
                    }
                    break;
                }
                case 4: {
                    if (!(boolean)this.letter.getValue()) {
                        break Label_1377;
                    }
                    if (chestName.length() <= chestName.indexOf("'") + 1) {
                        break;
                    }
                    final char letter = chestName.charAt(chestName.indexOf("'") + 1);
                    if (this.letterNeeded != String.valueOf(letter)) {
                        this.letterNeeded = String.valueOf(letter);
                    }
                    for (final Slot slot4 : invSlots) {
                        if (slot4.inventory == this.mc.thePlayer.inventory) {
                            continue;
                        }
                        final ItemStack itemStack3 = slot4.getStack();
                        if (itemStack3 == null) {
                            return true;
                        }
                        if (itemStack3.isItemEnchanted()) {
                            continue;
                        }
                        if (slot4.slotNumber < 9 || slot4.slotNumber > 44 || slot4.slotNumber % 9 == 0) {
                            continue;
                        }
                        if (slot4.slotNumber % 9 == 8) {
                            continue;
                        }
                        if (!StringUtils.stripControlCodes(itemStack3.getDisplayName()).startsWith(this.letterNeeded)) {
                            continue;
                        }
                        this.clickQueue.add(slot4);
                    }
                    break;
                }
                case 5: {
                    if (!(boolean)this.color.getValue()) {
                        break Label_1377;
                    }
                    String colorNeeded = null;
                    for (final EnumDyeColor color : EnumDyeColor.values()) {
                        final String colorName = color.getName().replaceAll(" ", "_").toUpperCase();
                        if (chestName.contains(colorName)) {
                            colorNeeded = color.getUnlocalizedName();
                            break;
                        }
                    }
                    if (colorNeeded == null) {
                        break;
                    }
                    Helper.sendMessage(colorNeeded);
                    for (final Slot slot4 : invSlots) {
                        if (slot4.inventory != this.mc.thePlayer.inventory && slot4.slotNumber >= 9 && slot4.slotNumber <= 44 && slot4.slotNumber % 9 != 0) {
                            if (slot4.slotNumber % 9 == 8) {
                                continue;
                            }
                            final ItemStack itemStack3 = slot4.getStack();
                            if (itemStack3 == null) {
                                return true;
                            }
                            if (itemStack3.isItemEnchanted()) {
                                continue;
                            }
                            if (!itemStack3.getUnlocalizedName().contains(colorNeeded)) {
                                continue;
                            }
                            this.clickQueue.add(slot4);
                        }
                    }
                    break;
                }
                case 7: {
                    if (this.cot.getValue()) {
                        break;
                    }
                    break;
                }
                case 6: {
                    if ((boolean)this.sameColor.getValue() && this.foundColor) {
                        final ArrayList<Slot> slotOrder2 = new ArrayList<Slot>();
                        for (int k = 12; k <= 32; ++k) {
                            final Slot slot4 = invSlots.get(k);
                            final ItemStack stack = slot4.getStack();
                            if (stack != null) {
                                if (stack.getItem() == Item.getItemFromBlock((Block)Blocks.stained_glass_pane)) {
                                    if (stack.getItemDamage() != 7) {
                                        final int distance = Math.abs(this.getDiff(stack.getItemDamage(), this.correctColor));
                                        if (distance != 0) {
                                            for (int dick = 0; dick < distance; ++dick) {
                                                slotOrder2.add(slot4);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.clickQueue.addAll(slotOrder2);
                        break;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private void timingClicks(final ContainerChest container) {
        if (System.currentTimeMillis() - this.lastClickTime > ((Double)this.delay.getValue()).longValue()) {
            final List<Slot> invSlots = (List<Slot>)container.inventorySlots;
            int greenSlot = -1;
            int purpleSlot = -1;
            int clickSlot2 = 0;
            Slot slotNeedClick = null;
            for (int k = 1; k < 51; ++k) {
                final ItemStack stack = invSlots.get(k).getStack();
                if (stack != null) {
                    final EnumDyeColor color = EnumDyeColor.byMetadata(stack.getItemDamage());
                    switch (lI.$SwitchMap$net$minecraft$item$EnumDyeColor[color.ordinal()]) {
                        case 1: {
                            if (purpleSlot == -1) {
                                purpleSlot = k % 9;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            final Item item3 = stack.getItem();
                            if (item3 == Item.getItemFromBlock((Block)Blocks.stained_glass_pane)) {
                                if (greenSlot == -1) {
                                    greenSlot = k % 9;
                                    break;
                                }
                                break;
                            }
                            else {
                                if (item3 == Item.getItemFromBlock(Blocks.stained_hardened_clay)) {
                                    clickSlot2 = k;
                                    slotNeedClick = invSlots.get(k);
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                    }
                }
            }
            if (purpleSlot != -1 && clickSlot2 != 0 && greenSlot == purpleSlot && slotNeedClick != null) {
                this.windowClick(this.mc.thePlayer.openContainer.windowId, slotNeedClick, 2, 3);
                this.lastClickTime = System.currentTimeMillis();
            }
        }
    }
    
    public int getDiff(final int color, final int endColor) {
        final int index = AutoTerminals.colorOrder.indexOf(color);
        final int finalIndex = AutoTerminals.colorOrder.indexOf(endColor);
        if (index == -1 || finalIndex == -1) {
            return 0;
        }
        if (finalIndex < index) {
            return finalIndex - index + 5;
        }
        return finalIndex - index;
    }
    
    private void clickSlot(final Slot slot) {
        this.clickSlot(slot, 2, 3);
    }
    
    private void clickSlot(final Slot slot, final int clickButton, final int clickMode) {
        if (this.windowClicks == 0) {
            this.windowId = this.mc.thePlayer.openContainer.windowId;
        }
        this.windowClick(this.windowId + this.windowClicks, slot, clickButton, clickMode);
        this.lastClickTime = System.currentTimeMillis();
        this.timer.reset();
        if (this.delayy.getValue()) {
            ++this.windowClicks;
            this.clickQueue.remove(slot);
        }
    }
    
    private void windowClick(final int windowId, final Slot slot, final int mouseButtonClicked, final int mode) {
        final short tid = this.mc.thePlayer.openContainer.getNextTransactionID(this.mc.thePlayer.inventory);
        final ItemStack itemstack = slot.getStack();
        this.mc.getNetHandler().addToSendQueue((Packet)new C0EPacketClickWindow(windowId, slot.slotNumber, mouseButtonClicked, mode, itemstack, tid));
    }
    
    static {
        AutoTerminals.colorOrder = Arrays.asList(14, 1, 4, 13, 11);
    }
    
    public enum TerminalType
    {
        MAZE, 
        NUMBERS, 
        CORRECT_ALL, 
        LETTER, 
        COLOR, 
        TIMING, 
        CHANGEATSC, 
        NONE;
    }
}
