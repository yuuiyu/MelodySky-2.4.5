//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons.Devices;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.*;
import net.minecraft.init.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import xyz.Melody.Utils.*;
import java.util.*;
import net.minecraft.block.state.*;

public class AutoShootTheTarget extends Module
{
    private BlockPos plate;
    private TimerUtil timer;
    private BlockPos eme;
    private int curPlateState;
    private boolean rotated;
    private Numbers<Double> delay;
    int ticks;
    
    public AutoShootTheTarget() {
        super("AutoShootTheTarget", ModuleType.Dungeons);
        this.plate = new BlockPos(63, 127, 35);
        this.timer = new TimerUtil();
        this.eme = null;
        this.curPlateState = 0;
        this.rotated = false;
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)150.0, (Number)100.0, (Number)500.0, (Number)10.0);
        this.ticks = 0;
        this.setModInfo("Auto Do Shoot The Target Device.");
        this.addValues(new Value[] { (Value)this.delay });
    }
    
    @EventHandler
    private void onBlockChange(final BlockChangeEvent event) {
        if (!Client.inDungeons) {
            return;
        }
        final BlockPos newPos = event.getPosition();
        final Block newBlock = event.getNewBlock().getBlock();
        this.curPlateState = this.getPlateState();
        if (this.curPlateState == 1 && newBlock == Blocks.emerald_block) {
            this.eme = newPos;
            this.timer.reset();
            this.rotated = false;
        }
    }
    
    @EventHandler
    private void tick(final EventTick event) {
        if (this.ticks < 2) {
            ++this.ticks;
            return;
        }
        if (!Client.inDungeons) {
            return;
        }
        this.curPlateState = this.getPlateState();
        if (this.curPlateState == 1) {
            if (this.eme == null) {
                return;
            }
            for (int i = 0; i < 8; ++i) {
                final ItemStack itemStack = this.mc.thePlayer.inventory.mainInventory[i];
                if (itemStack != null && itemStack.getItem() != null && itemStack.getItem() instanceof ItemBow) {
                    this.mc.thePlayer.inventory.currentItem = i;
                    break;
                }
            }
            final float[] rots = this.getRotations(this.eme, EnumFacing.UP);
            if (rots.length == 2) {
                this.mc.thePlayer.rotationYaw = rots[0];
                this.mc.thePlayer.rotationPitch = rots[1] - 2.3f;
                this.rotated = true;
                if (this.timer.hasReached(((Double)this.delay.getValue()).intValue())) {
                    Client.rightClick();
                    this.rotated = false;
                    this.eme = null;
                }
            }
        }
        if (!this.rotated) {
            this.timer.reset();
        }
        this.ticks = 0;
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
    
    private int getPlateState() {
        final int r = 2;
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return 0;
        }
        BlockPos playerPos = this.mc.thePlayer.getPosition();
        playerPos = playerPos.add(0, 1, 0);
        final Vec3i vec3i = new Vec3i(r, r, r);
        if (playerPos != null) {
            for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                if (blockState.getBlock() instanceof BlockPressurePlateWeighted) {
                    if (blockPos == this.plate) {
                        Helper.sendMessage(blockState.getValue((IProperty)BlockPressurePlateWeighted.POWER));
                    }
                    return (int)blockState.getValue((IProperty)BlockPressurePlateWeighted.POWER);
                }
            }
        }
        return 0;
    }
    
    private BlockPos getEmeraldBlock() {
        final int r = 15;
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return null;
        }
        BlockPos playerPos = this.mc.thePlayer.getPosition();
        playerPos = playerPos.add(0, 1, 0);
        final Vec3i vec3i = new Vec3i(8, 4, r);
        if (playerPos != null) {
            for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                if (blockState.getBlock() == Blocks.emerald_block) {
                    return blockPos;
                }
            }
        }
        return null;
    }
}
