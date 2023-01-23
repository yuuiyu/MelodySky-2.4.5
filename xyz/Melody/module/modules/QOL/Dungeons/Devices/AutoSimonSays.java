//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons.Devices;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.*;
import net.minecraft.init.*;
import java.util.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.block.*;

public class AutoSimonSays extends Module
{
    private final List<BlockPos> simonSaysQueue;
    public final BlockPos simonSaysStart;
    public boolean clickedSimonSays;
    private long lastInteractTime;
    private TimerUtil timer;
    private Numbers<Double> delay;
    
    public AutoSimonSays() {
        super("AutoSimonSays", new String[] { "ss" }, ModuleType.Dungeons);
        this.simonSaysQueue = new ArrayList<BlockPos>();
        this.simonSaysStart = new BlockPos(110, 121, 91);
        this.timer = new TimerUtil();
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)350.0, (Number)200.0, (Number)1000.0, (Number)10.0);
        this.addValues(new Value[] { (Value)this.delay });
        this.setModInfo("Auto Do Simon Says Device.");
    }
    
    public void onDisable() {
        this.simonSaysQueue.clear();
        this.clickedSimonSays = false;
        super.onDisable();
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        if (!Client.inDungeons) {
            return;
        }
        if (this.simonSaysQueue.size() != 0 && System.currentTimeMillis() - this.lastInteractTime >= ((Double)this.delay.getValue()).longValue() && this.mc.theWorld.getBlockState(new BlockPos(110, 121, 92)).getBlock() == Blocks.stone_button) {
            for (final BlockPos pos : new ArrayList<BlockPos>(this.simonSaysQueue)) {
                final MovingObjectPosition intercept = this.calculateInterceptLook(pos, 5.5f);
                if (intercept != null && this.mc.playerController.onPlayerRightClick(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.inventory.getCurrentItem(), pos, intercept.sideHit, intercept.hitVec)) {
                    this.mc.thePlayer.swingItem();
                    this.simonSaysQueue.remove(pos);
                    this.lastInteractTime = System.currentTimeMillis();
                    break;
                }
            }
        }
    }
    
    @EventHandler
    public void onPacket(final BlockChangeEvent event) {
        if (!Client.inDungeons) {
            return;
        }
        if (event.getPosition().getX() == 111 && event.getNewBlock().getBlock() == Blocks.sea_lantern && (this.simonSaysQueue.size() == 0 || !this.simonSaysQueue.get(this.simonSaysQueue.size() - 1).equals((Object)event.getPosition()))) {
            this.simonSaysQueue.add(new BlockPos(110, event.getPosition().getY(), event.getPosition().getZ()));
            this.clickedSimonSays = true;
        }
    }
    
    @EventHandler
    private void onReset(final BlockChangeEvent event) {
        if (!Client.inDungeons) {
            return;
        }
        if (event.getPosition() == this.simonSaysStart && this.timer.hasReached(750.0)) {
            this.simonSaysQueue.clear();
            this.clickedSimonSays = false;
            Helper.sendMessage("[AutoSS] AutoSS Reset.");
            this.timer.reset();
        }
    }
    
    @SubscribeEvent
    public void onWorldChange(final WorldEvent.Load event) {
        this.simonSaysQueue.clear();
        this.clickedSimonSays = false;
    }
    
    public MovingObjectPosition calculateInterceptLook(final BlockPos pos, final float range) {
        final AxisAlignedBB aabb = this.getBlockAABB(pos);
        final Vec3 vec3 = this.getPositionEyes();
        final Vec3 look = getMiddleOfAABB(aabb);
        if (vec3.squareDistanceTo(look) > range * range) {
            return null;
        }
        return aabb.calculateIntercept(vec3, look);
    }
    
    public Vec3 getPositionEyes() {
        return new Vec3(this.mc.thePlayer.posX, this.mc.thePlayer.posY + this.fastEyeHeight(), this.mc.thePlayer.posZ);
    }
    
    public AxisAlignedBB getBlockAABB(final BlockPos pos) {
        final Block block = this.mc.theWorld.getBlockState(pos).getBlock();
        block.setBlockBoundsBasedOnState((IBlockAccess)this.mc.theWorld, pos);
        return block.getSelectedBoundingBox((World)this.mc.theWorld, pos);
    }
    
    public float fastEyeHeight() {
        return this.mc.thePlayer.isSneaking() ? 1.54f : 1.62f;
    }
    
    public static Vec3 getMiddleOfAABB(final AxisAlignedBB aabb) {
        return new Vec3((aabb.maxX + aabb.minX) / 2.0, (aabb.maxY + aabb.minY) / 2.0, (aabb.maxZ + aabb.minZ) / 2.0);
    }
}
