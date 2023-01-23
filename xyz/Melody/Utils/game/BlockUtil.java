//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.game;

import net.minecraft.client.*;
import net.minecraft.block.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.block.state.*;

public class BlockUtil
{
    private static Minecraft mc;
    public static Vec3 bp;
    
    public static Block getBlock(final int x2, final int y2, final int z2) {
        return BlockUtil.mc.theWorld.getBlockState(new BlockPos(x2, y2, z2)).getBlock();
    }
    
    public static Block getBlock(final BlockPos pos) {
        return BlockUtil.mc.theWorld.getBlockState(pos).getBlock();
    }
    
    public static ArrayList<Vec3> whereToMineBlock(final BlockPos pos) {
        final Vec3 center = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        final ArrayList<Vec3> vec3s = new ArrayList<Vec3>();
        MovingObjectPosition position = rayTrace(center, 4.5f);
        if (position != null && position.getBlockPos().equals((Object)pos)) {
            vec3s.add(position.hitVec);
        }
        for (int x = 1; x < 5; ++x) {
            for (int y = 1; y < 5; ++y) {
                for (int z = 1; z < 5; ++z) {
                    final Vec3 vec = new Vec3(pos.getX() + x / 4.0 - 0.125, pos.getY() + y / 4.0 - 0.125, pos.getZ() + z / 4.0 - 0.125);
                    position = rayTrace(vec, 4.5f);
                    if (position != null) {
                        BlockUtil.bp = position.hitVec;
                        if (position.getBlockPos().equals((Object)pos)) {
                            vec3s.add(position.hitVec);
                        }
                    }
                }
            }
        }
        return vec3s;
    }
    
    public static MovingObjectPosition rayTrace(final Vec3 target, final float range) {
        final Vec3 vec3 = BlockUtil.mc.thePlayer.getPositionEyes(1.0f);
        final Vec3 vec4 = getLook(target);
        return fastRayTrace(vec3, vec3.addVector(vec4.xCoord * range, vec4.yCoord * range, vec4.zCoord * range));
    }
    
    public static Vec3 getLook(final Vec3 vec) {
        final double diffX = vec.xCoord - BlockUtil.mc.thePlayer.posX;
        final double diffY = vec.yCoord - (BlockUtil.mc.thePlayer.posY + BlockUtil.mc.thePlayer.getEyeHeight());
        final double diffZ = vec.zCoord - BlockUtil.mc.thePlayer.posZ;
        final double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        return getVectorForRotation((float)(-(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793)), (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793 - 90.0));
    }
    
    public static Vec3 getVectorForRotation(final float pitch, final float yaw) {
        final double f2 = -Math.cos(-pitch * 0.017453292f);
        return new Vec3(Math.sin(-yaw * 0.017453292f - 3.1415927f) * f2, Math.sin(-pitch * 0.017453292f), Math.cos(-yaw * 0.017453292f - 3.1415927f) * f2);
    }
    
    private static MovingObjectPosition fastRayTrace(Vec3 vec31, final Vec3 vec32) {
        final int i = (int)Math.floor(vec32.xCoord);
        final int j = (int)Math.floor(vec32.yCoord);
        final int k = (int)Math.floor(vec32.zCoord);
        int l = (int)Math.floor(vec31.xCoord);
        int i2;
        int j2;
        BlockPos blockpos = new BlockPos(l, i2 = (int)Math.floor(vec31.yCoord), j2 = (int)Math.floor(vec31.zCoord));
        final IBlockState iblockstate = BlockUtil.mc.theWorld.getBlockState(blockpos);
        final Block block = iblockstate.getBlock();
        final MovingObjectPosition movingobjectposition;
        if (block.canCollideCheck(iblockstate, false) && (movingobjectposition = block.collisionRayTrace((World)BlockUtil.mc.theWorld, blockpos, vec31, vec32)) != null) {
            return movingobjectposition;
        }
        MovingObjectPosition movingobjectposition2 = null;
        int k2 = 200;
        while (k2-- >= 0) {
            if (l == i && i2 == j && j2 == k) {
                return movingobjectposition2;
            }
            boolean flag2 = true;
            boolean flag3 = true;
            boolean flag4 = true;
            double d0 = 999.0;
            double d2 = 999.0;
            double d3 = 999.0;
            if (i > l) {
                d0 = l + 1.0;
            }
            else if (i < l) {
                d0 = l + 0.0;
            }
            else {
                flag2 = false;
            }
            if (j > i2) {
                d2 = i2 + 1.0;
            }
            else if (j < i2) {
                d2 = i2 + 0.0;
            }
            else {
                flag3 = false;
            }
            if (k > j2) {
                d3 = j2 + 1.0;
            }
            else if (k < j2) {
                d3 = j2 + 0.0;
            }
            else {
                flag4 = false;
            }
            double d4 = 999.0;
            double d5 = 999.0;
            double d6 = 999.0;
            final double d7 = vec32.xCoord - vec31.xCoord;
            final double d8 = vec32.yCoord - vec31.yCoord;
            final double d9 = vec32.zCoord - vec31.zCoord;
            if (flag2) {
                d4 = (d0 - vec31.xCoord) / d7;
            }
            if (flag3) {
                d5 = (d2 - vec31.yCoord) / d8;
            }
            if (flag4) {
                d6 = (d3 - vec31.zCoord) / d9;
            }
            if (d4 == -0.0) {
                d4 = -1.0E-4;
            }
            if (d5 == -0.0) {
                d5 = -1.0E-4;
            }
            if (d6 == -0.0) {
                d6 = -1.0E-4;
            }
            EnumFacing enumfacing;
            if (d4 < d5 && d4 < d6) {
                enumfacing = ((i > l) ? EnumFacing.WEST : EnumFacing.EAST);
                vec31 = new Vec3(d0, vec31.yCoord + d8 * d4, vec31.zCoord + d9 * d4);
            }
            else if (d5 < d6) {
                enumfacing = ((j > i2) ? EnumFacing.DOWN : EnumFacing.UP);
                vec31 = new Vec3(vec31.xCoord + d7 * d5, d2, vec31.zCoord + d9 * d5);
            }
            else {
                enumfacing = ((k > j2) ? EnumFacing.NORTH : EnumFacing.SOUTH);
                vec31 = new Vec3(vec31.xCoord + d7 * d6, vec31.yCoord + d8 * d6, d3);
            }
            l = MathHelper.floor_double(vec31.xCoord) - ((enumfacing == EnumFacing.EAST) ? 1 : 0);
            i2 = MathHelper.floor_double(vec31.yCoord) - ((enumfacing == EnumFacing.UP) ? 1 : 0);
            j2 = MathHelper.floor_double(vec31.zCoord) - ((enumfacing == EnumFacing.SOUTH) ? 1 : 0);
            blockpos = new BlockPos(l, i2, j2);
            final IBlockState iblockstate2 = BlockUtil.mc.theWorld.getBlockState(blockpos);
            final Block block2 = iblockstate2.getBlock();
            if (block2.canCollideCheck(iblockstate2, false)) {
                final MovingObjectPosition movingobjectposition3 = block2.collisionRayTrace((World)BlockUtil.mc.theWorld, blockpos, vec31, vec32);
                if (movingobjectposition3 == null) {
                    continue;
                }
                return movingobjectposition3;
            }
            else {
                movingobjectposition2 = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec31, enumfacing, blockpos);
            }
        }
        return movingobjectposition2;
    }
    
    static {
        BlockUtil.mc = Minecraft.getMinecraft();
        BlockUtil.bp = null;
    }
}
