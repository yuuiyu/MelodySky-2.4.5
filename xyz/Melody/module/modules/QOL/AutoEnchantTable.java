//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.client.entity.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.block.*;
import net.minecraft.client.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.nbt.*;
import xyz.Melody.Utils.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class AutoEnchantTable extends Module
{
    private Mode<Enum> clickMode;
    private Numbers<Double> delay;
    private Option<Boolean> chronomatron;
    private Option<Boolean> ultrasequencer;
    private Option<Boolean> superpairs;
    private Option<Boolean> bug;
    private TimerUtil timer;
    public expType currentType;
    private boolean addedAll;
    private Map<Integer, ItemStack> superpairStacks;
    private int lastSlotClicked;
    private HashSet<Integer> successfulMatches;
    private HashSet<Integer> possibleMatches;
    private HashSet<Integer> powerupMatches;
    private List<String> chronomatronPattern;
    private int lastChronomatronRound;
    private int chronomatronMouseClicks;
    private Slot[] clickInOrderSlots;
    private ArrayList<Slot> clickQueue;
    private int windowId;
    
    public AutoEnchantTable() {
        super("AutoExperiment", new String[] { "enchant" }, ModuleType.QOL);
        this.clickMode = (Mode<Enum>)new Mode("ClickMode", (Enum[])cm.values(), (Enum)cm.Middle);
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)150.0, (Number)100.0, (Number)500.0, (Number)1.0);
        this.chronomatron = (Option<Boolean>)new Option("Chronomatron", (Object)true);
        this.ultrasequencer = (Option<Boolean>)new Option("Ultrasequencer", (Object)true);
        this.superpairs = (Option<Boolean>)new Option("SuperPairsSolver", (Object)true);
        this.bug = (Option<Boolean>)new Option("dEBuG", (Object)false);
        this.timer = new TimerUtil();
        this.currentType = expType.NONE;
        this.addedAll = false;
        this.superpairStacks = new HashMap<Integer, ItemStack>();
        this.lastSlotClicked = -1;
        this.successfulMatches = new HashSet<Integer>();
        this.possibleMatches = new HashSet<Integer>();
        this.powerupMatches = new HashSet<Integer>();
        this.chronomatronPattern = new ArrayList<String>();
        this.lastChronomatronRound = 0;
        this.chronomatronMouseClicks = 0;
        this.clickInOrderSlots = new Slot[36];
        this.clickQueue = new ArrayList<Slot>();
        this.windowId = 0;
        this.addValues(new Value[] { (Value)this.clickMode, (Value)this.delay, (Value)this.chronomatron, (Value)this.ultrasequencer, (Value)this.superpairs, (Value)this.bug });
        this.setModInfo("Auto Do Experimentation Table.");
    }
    
    @EventHandler
    private void tickContainer(final EventTick event) {
        if (this.currentType != expType.NONE && this.clickMode.getValue() != cm.Middle) {
            this.mc.thePlayer.inventory.setItemStack((ItemStack)null);
        }
    }
    
    @EventHandler
    public void onGuiDraw(final EventTick event) {
        final GuiScreen gui = this.mc.currentScreen;
        if (gui instanceof GuiChest) {
            final Container container = ((GuiChest)gui).inventorySlots;
            if (container instanceof ContainerChest) {
                if (this.currentType == expType.NONE) {
                    final String chestName = ((ContainerChest)container).getLowerChestInventory().getDisplayName().getUnformattedText();
                    if (chestName.startsWith("Ultrasequencer (")) {
                        this.currentType = expType.Ultrasequencer;
                    }
                    else if (chestName.startsWith("Chronomatron (")) {
                        this.currentType = expType.Chronomatron;
                    }
                    else if (chestName.startsWith("Superpairs (")) {
                        this.currentType = expType.Superpairs;
                    }
                }
                else if (this.currentType != expType.NONE && !this.clickQueue.isEmpty() && this.timer.hasReached((double)((Double)this.delay.getValue()).longValue())) {
                    this.clickSlot(this.clickQueue.get(0), true);
                }
            }
        }
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        final EntityPlayerSP player = this.mc.thePlayer;
        if (this.mc.currentScreen instanceof GuiChest) {
            if (player == null) {
                return;
            }
            final List<Slot> invSlots = (List<Slot>)((GuiChest)this.mc.currentScreen).inventorySlots.inventorySlots;
            if (this.currentType == expType.Ultrasequencer && (boolean)this.ultrasequencer.getValue()) {
                if (invSlots.get(49).getStack() != null && invSlots.get(49).getStack().getDisplayName().contains("Remember the pattern!")) {
                    this.addedAll = false;
                    for (int i = 9; i <= 44; ++i) {
                        if (invSlots.get(i) != null) {
                            if (invSlots.get(i).getStack() != null) {
                                final String itemName = StringUtils.stripControlCodes(invSlots.get(i).getStack().getDisplayName());
                                if (itemName.matches("\\d+")) {
                                    final int number = Integer.parseInt(itemName);
                                    this.clickInOrderSlots[number - 1] = invSlots.get(i);
                                }
                            }
                        }
                    }
                }
                else if (invSlots.get(49).getStack().getDisplayName().startsWith("§7Timer: §a") && !this.addedAll) {
                    this.clickQueue.addAll(Arrays.stream(this.clickInOrderSlots).filter(Objects::nonNull).collect((Collector<? super Slot, ?, Collection<? extends Slot>>)Collectors.toList()));
                    this.clickInOrderSlots = new Slot[36];
                    this.addedAll = true;
                }
            }
        }
    }
    
    @EventHandler
    public void onGuiRender(final EventTick event) {
        if (this.currentType != expType.Chronomatron) {
            return;
        }
        if (this.mc.currentScreen instanceof GuiChest) {
            final GuiChest inventory = (GuiChest)this.mc.currentScreen;
            final Container containerChest = inventory.inventorySlots;
            if (containerChest instanceof ContainerChest) {
                final EntityPlayerSP player = this.mc.thePlayer;
                final List<Slot> invSlots = (List<Slot>)containerChest.inventorySlots;
                if ((boolean)this.chronomatron.getValue() && this.currentType == expType.Chronomatron && player.inventory.getItemStack() == null && invSlots.size() > 48 && invSlots.get(49).getStack() != null) {
                    if (invSlots.get(49).getStack().getDisplayName().startsWith("§7Timer: §a") && invSlots.get(4).getStack() != null) {
                        final int round = invSlots.get(4).getStack().stackSize;
                        final int timerSeconds = Integer.parseInt(StringUtils.stripControlCodes(invSlots.get(49).getStack().getDisplayName()).replaceAll("[^\\d]", ""));
                        if (round != this.lastChronomatronRound && timerSeconds == round + 2) {
                            this.lastChronomatronRound = round;
                            for (int i = 10; i <= 43; ++i) {
                                final ItemStack stack = invSlots.get(i).getStack();
                                if (stack != null && stack.getItem() == Item.getItemFromBlock(Blocks.stained_hardened_clay)) {
                                    this.chronomatronPattern.add(stack.getDisplayName());
                                    break;
                                }
                            }
                        }
                        if (this.chronomatronMouseClicks < this.chronomatronPattern.size() && player.inventory.getItemStack() == null) {
                            for (int i = 10; i <= 43; ++i) {
                                final ItemStack glass = invSlots.get(i).getStack();
                                if (glass != null) {
                                    if (player.inventory.getItemStack() == null) {
                                        final Slot glassSlot = invSlots.get(i);
                                        if (glass.getDisplayName().equals(this.chronomatronPattern.get(this.chronomatronMouseClicks))) {
                                            if (this.timer.hasReached((double)this.delay.getValue())) {
                                                this.clickSlot(glassSlot, false);
                                                ++this.chronomatronMouseClicks;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (invSlots.get(49).getStack().getDisplayName().equals("§aRemember the pattern!")) {
                        this.chronomatronMouseClicks = 0;
                    }
                }
            }
        }
    }
    
    public ItemStack overrideStack(final IInventory inventory, final int slotIndex, final ItemStack stack) {
        if (stack != null && stack.getDisplayName() != null && this.mc.currentScreen instanceof GuiChest) {
            final GuiChest chest = (GuiChest)this.mc.currentScreen;
            final ContainerChest container = (ContainerChest)chest.inventorySlots;
            final IInventory lower = container.getLowerChestInventory();
            if (lower != inventory) {
                return null;
            }
            if (this.currentType == expType.Superpairs && stack.getItem() == Item.getItemFromBlock((Block)Blocks.stained_glass) && this.superpairStacks.containsKey(slotIndex)) {
                return this.superpairStacks.get(slotIndex);
            }
        }
        return null;
    }
    
    public boolean onStackRender(final ItemStack stack, final IInventory inventory, final int slotIndex, final int x, final int y) {
        if (stack != null && stack.getDisplayName() != null && Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
            final GuiChest chest = (GuiChest)Minecraft.getMinecraft().currentScreen;
            final ContainerChest container = (ContainerChest)chest.inventorySlots;
            final IInventory lower = container.getLowerChestInventory();
            if (lower != inventory) {
                return false;
            }
            if (this.currentType == expType.Superpairs) {
                int meta = 0;
                if (stack.getItem() == Item.getItemFromBlock((Block)Blocks.stained_glass) && this.superpairStacks.containsKey(slotIndex)) {
                    if (this.possibleMatches.contains(slotIndex)) {
                        meta = 2;
                    }
                    else {
                        meta = 5;
                    }
                }
                else if (this.powerupMatches.contains(slotIndex)) {
                    meta = 11;
                }
                else if (this.successfulMatches.contains(slotIndex)) {
                    meta = 6;
                }
                if (meta > 0) {
                    RenderUtil.drawItemStack(new ItemStack(Item.getItemFromBlock((Block)Blocks.stained_glass_pane), 1, meta - 1), x, y);
                }
            }
        }
        return false;
    }
    
    public boolean onStackClick(final ItemStack stack, final int windowId, final int slotId, final int mouseButtonClicked, final int mode) {
        if (stack != null && stack.getDisplayName() != null && this.mc.currentScreen instanceof GuiChest && this.currentType == expType.Superpairs) {
            this.lastSlotClicked = slotId;
        }
        return false;
    }
    
    public void processInventoryContents() {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
            final GuiChest chest = (GuiChest)Minecraft.getMinecraft().currentScreen;
            final ContainerChest container = (ContainerChest)chest.inventorySlots;
            final IInventory lower = container.getLowerChestInventory();
            if (this.currentType == expType.Superpairs) {
                this.successfulMatches.clear();
                this.possibleMatches.clear();
                this.powerupMatches.clear();
            Label_0723:
                for (int index = 0; index < lower.getSizeInventory(); ++index) {
                    final ItemStack stack = lower.getStackInSlot(index);
                    if (stack != null) {
                        if (stack.getItem() != Item.getItemFromBlock((Block)Blocks.stained_glass) && stack.getItem() != Item.getItemFromBlock((Block)Blocks.stained_glass_pane)) {
                            this.superpairStacks.put(index, stack);
                            final NBTTagCompound tag = stack.getTagCompound();
                            if (tag != null) {
                                final NBTTagCompound display = tag.getCompoundTag("display");
                                if (display.hasKey("Lore", 9)) {
                                    final NBTTagList list = display.getTagList("Lore", 8);
                                    for (int i = 0; i < list.tagCount(); ++i) {
                                        if (list.getStringTagAt(i).toLowerCase().contains("powerup")) {
                                            this.powerupMatches.add(index);
                                            continue Label_0723;
                                        }
                                    }
                                }
                            }
                            int numMatches = 0;
                            for (int index2 = 0; index2 < lower.getSizeInventory(); ++index2) {
                                final ItemStack stack2 = lower.getStackInSlot(index2);
                                if (stack2 != null && stack2.getDisplayName().equals(stack.getDisplayName()) && stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage()) {
                                    ++numMatches;
                                }
                            }
                            final boolean oddMatches = numMatches % 2 == 1;
                            if ((!oddMatches || index != this.lastSlotClicked) && !this.successfulMatches.contains(index)) {
                                for (int index3 = 0; index3 < lower.getSizeInventory(); ++index3) {
                                    if (index != index3) {
                                        if (!oddMatches || index3 != this.lastSlotClicked) {
                                            final ItemStack stack3 = lower.getStackInSlot(index3);
                                            if (stack3 != null && stack3.getDisplayName().equals(stack.getDisplayName()) && stack.getItem() == stack3.getItem() && stack.getItemDamage() == stack3.getItemDamage()) {
                                                this.successfulMatches.add(index);
                                                this.successfulMatches.add(index3);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else if (this.superpairStacks.containsKey(index) && this.superpairStacks.get(index) != null && !this.possibleMatches.contains(index)) {
                            final ItemStack stack4 = this.superpairStacks.get(index);
                            for (int index4 = 0; index4 < lower.getSizeInventory(); ++index4) {
                                if (index != index4) {
                                    if (this.superpairStacks.containsKey(index4) && this.superpairStacks.get(index4) != null) {
                                        final ItemStack stack5 = this.superpairStacks.get(index4);
                                        if (stack4.getDisplayName().equals(stack5.getDisplayName()) && stack4.getItem() == stack5.getItem() && stack4.getItemDamage() == stack5.getItemDamage()) {
                                            this.possibleMatches.add(index);
                                            this.possibleMatches.add(index4);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else {
                this.superpairStacks.clear();
                this.successfulMatches.clear();
                this.powerupMatches.clear();
                this.lastSlotClicked = -1;
            }
        }
    }
    
    @EventHandler
    public void onGuiClosed(final EventTick event) {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        if (!(this.mc.currentScreen instanceof GuiChest)) {
            this.currentType = expType.NONE;
            this.addedAll = false;
            this.chronomatronMouseClicks = 0;
            this.lastChronomatronRound = 0;
            this.chronomatronPattern.clear();
            this.clickInOrderSlots = new Slot[36];
            this.clickQueue.clear();
        }
    }
    
    private void clickSlot(final Slot slot, final boolean remove) {
        if (this.clickMode.getValue() == cm.Middle) {
            this.clickSlot(slot, 2, 3, remove);
        }
        if (this.clickMode.getValue() == cm.Left) {
            this.clickSlot(slot, 0, 0, remove);
        }
        if (this.clickMode.getValue() == cm.Right) {
            this.clickSlot(slot, 1, 0, remove);
        }
    }
    
    private void clickSlot(final Slot slot, final int clickButton, final int clickMode, final boolean remove) {
        this.windowClick(this.windowId = this.mc.thePlayer.openContainer.windowId, slot, clickButton, clickMode);
        if (this.bug.getValue()) {
            Helper.sendMessage("Clicked: " + slot.slotNumber);
        }
        this.timer.reset();
        if (remove) {
            this.clickQueue.remove(slot);
        }
    }
    
    private void windowClick(final int windowId, final Slot slot, final int mouseButtonClicked, final int mode) {
        final short tid = this.mc.thePlayer.openContainer.getNextTransactionID(this.mc.thePlayer.inventory);
        final ItemStack itemstack = slot.getStack();
        this.mc.getNetHandler().addToSendQueue((Packet)new C0EPacketClickWindow(windowId, slot.slotNumber, mouseButtonClicked, mode, itemstack, tid));
    }
    
    enum cm
    {
        Middle, 
        Left, 
        Right;
    }
    
    public enum expType
    {
        NONE, 
        Chronomatron, 
        Ultrasequencer, 
        Superpairs;
    }
}
