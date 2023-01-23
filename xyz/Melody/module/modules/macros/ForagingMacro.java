//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.macros;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.misc.*;
import org.lwjgl.input.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.game.*;
import xyz.Melody.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;

public class ForagingMacro extends Module
{
    private Option<Boolean> useRod;
    private Numbers<Double> treeSlot;
    private Numbers<Double> bonemealSlot;
    private Numbers<Double> axeSlot;
    private Numbers<Double> rodSlot;
    private Numbers<Double> delay;
    private Numbers<Double> timeBreak;
    private Numbers<Double> breakDelay;
    private ArrayList<BlockPos> dirtPos;
    private TimerUtil yepTimer;
    private TimerUtil failSafeTimer;
    private TimerUtil shabTimer;
    private ForagingState foragingState;
    private int currentTree;
    private int treeWait;
    
    public ForagingMacro() {
        super("ForagingMacro", new String[] { "am" }, ModuleType.Macros);
        this.useRod = (Option<Boolean>)new Option("Use Rod", (Object)false);
        this.treeSlot = (Numbers<Double>)new Numbers("Sapling Slot", (Number)0.0, (Number)0.0, (Number)8.0, (Number)1.0);
        this.bonemealSlot = (Numbers<Double>)new Numbers("Bonemeal Slot", (Number)1.0, (Number)0.0, (Number)8.0, (Number)1.0);
        this.axeSlot = (Numbers<Double>)new Numbers("Axe Slot", (Number)2.0, (Number)0.0, (Number)8.0, (Number)1.0);
        this.rodSlot = (Numbers<Double>)new Numbers("Rod Slot", (Number)3.0, (Number)0.0, (Number)8.0, (Number)1.0);
        this.delay = (Numbers<Double>)new Numbers("PlaceDelay", (Number)500.0, (Number)0.0, (Number)5000.0, (Number)100.0);
        this.timeBreak = (Numbers<Double>)new Numbers("TimeBeforeBreak", (Number)500.0, (Number)100.0, (Number)1000.0, (Number)10.0);
        this.breakDelay = (Numbers<Double>)new Numbers("BreakDelay", (Number)2000.0, (Number)1000.0, (Number)3000.0, (Number)50.0);
        this.dirtPos = new ArrayList<BlockPos>();
        this.yepTimer = new TimerUtil();
        this.failSafeTimer = new TimerUtil();
        this.shabTimer = new TimerUtil();
        this.currentTree = 1;
        this.addValues(new Value[] { (Value)this.useRod, (Value)this.treeSlot, (Value)this.bonemealSlot, (Value)this.axeSlot, (Value)this.rodSlot, (Value)this.delay, (Value)this.timeBreak, (Value)this.breakDelay });
        this.setModInfo("Auto Place -> Grow -> Break Trees.");
    }
    
    public void onEnable() {
        Helper.sendMessage("[ForagingMacro] Aim a Block And Press ALT to Set Dirt Position.");
        this.foragingState = ForagingState.TREE;
        this.currentTree = 1;
        this.yepTimer.reset();
        this.failSafeTimer.reset();
        this.shabTimer.reset();
        this.treeWait = ((Double)this.delay.getValue()).intValue();
        this.dirtPos.clear();
        super.onEnable();
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    @EventHandler
    private void onKey(final EventKey event) {
        if (Keyboard.getKeyName(event.getKey()).toLowerCase().contains("lmenu") && this.dirtPos.size() < 4) {
            this.dirtPos.add(this.mc.objectMouseOver.getBlockPos());
        }
    }
    
    @EventHandler
    private void drawBlocks(final EventRender3D event) {
        for (final BlockPos pos : this.dirtPos) {
            RenderUtil.drawFullBlockESP(pos, new Color(Colors.MAGENTA.c), event.getPartialTicks());
        }
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (this.dirtPos.size() < 4) {
            return;
        }
        final int saplingCount = InventoryUtils.getAmountInHotbar("Jungle Sapling");
        final int boneMealCount = InventoryUtils.getAmountInHotbar("Enchanted Bone Meal");
        if (saplingCount < 5 || boneMealCount < 2) {
            return;
        }
        switch (l.$SwitchMap$xyz$Melody$module$modules$macros$ForagingMacro$ForagingState[this.foragingState.ordinal()]) {
            case 1: {
                if (!this.failSafeTimer.hasReached((double)this.breakDelay.getValue())) {
                    break;
                }
                if (!this.yepTimer.hasReached(this.treeWait)) {
                    break;
                }
                this.swapSlot(((Double)this.treeSlot.getValue()).intValue());
                final BlockPos cur = this.dirtPos.get(this.currentTree - 1);
                final float[] rots = this.getRotations(cur, EnumFacing.DOWN);
                this.mc.thePlayer.rotationYaw = rots[0];
                this.mc.thePlayer.rotationPitch = rots[1];
                this.yepTimer.reset();
                this.foragingState = ForagingState.LOOKING;
                break;
            }
            case 2: {
                if (!this.yepTimer.hasReached(((Double)this.delay.getValue()).intValue())) {
                    break;
                }
                this.swapSlot(((Double)this.bonemealSlot.getValue()).intValue());
                Client.rightClick();
                this.yepTimer.reset();
                this.shabTimer.reset();
                this.foragingState = ForagingState.RODSWAP;
                break;
            }
            case 3: {
                if (!(boolean)this.useRod.getValue()) {
                    this.yepTimer.reset();
                    this.failSafeTimer.reset();
                    this.shabTimer.reset();
                    this.foragingState = ForagingState.HARVEST;
                    this.swapSlot(((Double)this.axeSlot.getValue()).intValue());
                    break;
                }
                if (!this.yepTimer.hasReached(((Double)this.delay.getValue()).intValue())) {
                    break;
                }
                this.silentUse(((Double)this.axeSlot.getValue()).intValue(), ((Double)this.rodSlot.getValue()).intValue());
                Client.rightClick();
                this.yepTimer.reset();
                this.failSafeTimer.reset();
                this.shabTimer.reset();
                this.foragingState = ForagingState.HARVEST;
                this.swapSlot(((Double)this.axeSlot.getValue()).intValue());
                break;
            }
            case 4: {
                if (this.failSafeTimer.hasReached((double)this.breakDelay.getValue())) {
                    this.foragingState = ForagingState.TREE;
                    this.currentTree = 1;
                }
                if (!this.shabTimer.hasReached((double)this.timeBreak.getValue()) || this.mc.objectMouseOver == null || this.mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
                    break;
                }
                if (!this.yepTimer.hasReached(((Double)this.timeBreak.getValue()).intValue())) {
                    break;
                }
                if (this.closestLog() != null) {
                    this.harvest();
                }
                else {
                    this.yepTimer.reset();
                    this.foragingState = ForagingState.TREE;
                    this.currentTree = 1;
                    this.treeWait = 500;
                }
                this.shabTimer.reset();
                break;
            }
        }
    }
    
    @EventHandler
    public void onTickWorld(final EventTick event) {
        if (this.foragingState == ForagingState.LOOKING && this.yepTimer.hasReached((double)this.delay.getValue())) {
            Client.rightClick();
            this.yepTimer.reset();
            if (this.currentTree < 4) {
                ++this.currentTree;
                this.foragingState = ForagingState.TREE;
                this.treeWait = ((Double)this.delay.getValue()).intValue();
            }
            else {
                this.foragingState = ForagingState.BONEMEAL;
            }
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[MacroProtection] Auto Disabled " + EnumChatFormatting.GREEN + this.getName() + EnumChatFormatting.GRAY + " due to World Change.");
        this.setEnabled(false);
    }
    
    private void harvest() {
        final MovingObjectPosition fake = this.mc.objectMouseOver;
        final BlockPos bp = this.closestLog();
        if (bp == null) {
            return;
        }
        fake.hitVec = new Vec3((Vec3i)bp);
        final EnumFacing enumFacing = fake.sideHit;
        if (enumFacing != null && this.mc.thePlayer != null) {
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, bp, enumFacing));
        }
        this.mc.thePlayer.swingItem();
    }
    
    private BlockPos closestLog() {
        if (this.mc.theWorld == null || this.mc.thePlayer == null) {
            return null;
        }
        final float r = 2.0f;
        final BlockPos playerPos = this.mc.thePlayer.getPosition();
        final Vec3i vec3i = new Vec3i((double)r, (double)r, (double)r);
        final ArrayList<Vec3> logs = new ArrayList<Vec3>();
        if (playerPos != null) {
            for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                if (blockState.getBlock() == Blocks.log || blockState.getBlock() == Blocks.vine) {
                    logs.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                }
            }
        }
        logs.sort(Comparator.comparingDouble(vec -> this.mc.thePlayer.getDistance(vec.xCoord, vec.yCoord, vec.zCoord)));
        if (!logs.isEmpty()) {
            return new BlockPos(logs.get(0).xCoord, logs.get(0).yCoord, logs.get(0).zCoord);
        }
        return null;
    }
    
    public float[] getRotations(final BlockPos block, final EnumFacing face) {
        final double x = block.getX() + 0.5 - this.mc.thePlayer.posX + face.getFrontOffsetX() / 2.0;
        final double z = block.getZ() + 0.5 - this.mc.thePlayer.posZ + face.getFrontOffsetZ() / 2.0;
        final double d1 = this.mc.thePlayer.posY + this.mc.thePlayer.getEyeHeight() - (block.getY() + 0.5);
        final double d2 = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(Math.atan2(d1, d2) * 180.0 / 3.141592653589793);
        if (yaw < 0.0f) {
            yaw += 360.0f;
        }
        return new float[] { yaw, pitch };
    }
    
    private void swapSlot(final int slot) {
        if (slot > 0 && slot <= 8) {
            this.mc.thePlayer.inventory.currentItem = slot - 1;
        }
    }
    
    public void silentUse(final int mainSlot, final int useSlot) {
        final int oldSlot = this.mc.thePlayer.inventory.currentItem;
        if (useSlot > 0 && useSlot <= 8) {
            this.mc.thePlayer.inventory.currentItem = useSlot - 1;
            this.mc.playerController.sendUseItem((EntityPlayer)this.mc.thePlayer, (World)this.mc.theWorld, this.mc.thePlayer.getHeldItem());
        }
        if (mainSlot > 0 && mainSlot <= 8) {
            this.mc.thePlayer.inventory.currentItem = mainSlot - 1;
        }
        else if (mainSlot == 0) {
            this.mc.thePlayer.inventory.currentItem = oldSlot;
        }
    }
    
    enum dir
    {
        NORTH, 
        EAST, 
        SOUTH, 
        WEST;
    }
    
    enum ForagingState
    {
        TREE, 
        BONEMEAL, 
        RODSWAP, 
        HARVEST, 
        LOOKING;
    }
}
