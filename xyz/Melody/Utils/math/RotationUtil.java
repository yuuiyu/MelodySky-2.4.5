//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.math;

import net.minecraft.client.*;
import xyz.Melody.Utils.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;

public class RotationUtil
{
    private static Minecraft mc;
    
    public static float pitch() {
        return Helper.mc.thePlayer.rotationPitch;
    }
    
    public static void pitch(final float pitch) {
        Helper.mc.thePlayer.rotationPitch = pitch;
    }
    
    public static float yaw() {
        return Helper.mc.thePlayer.rotationYaw;
    }
    
    public static void yaw(final float yaw) {
        Helper.mc.thePlayer.rotationYaw = yaw;
    }
    
    public static double isInFov(final float yaw, final float pitch, final double x, final double y, final double z) {
        final Vec3 var7 = new Vec3((double)yaw, (double)pitch, 0.0);
        final float[] var8 = getAngleBetweenVecs(new Vec3(RotationUtil.mc.thePlayer.posX, RotationUtil.mc.thePlayer.posY, RotationUtil.mc.thePlayer.posZ), new Vec3(x, y, z));
        final double var9 = MathHelper.wrapAngleTo180_double(var7.xCoord - var8[0]);
        return Math.abs(var9) * 2.0;
    }
    
    public static float[] getAngleBetweenVecs(final Vec3 var0, final Vec3 var1) {
        final double var2 = var1.xCoord - var0.xCoord;
        final double var3 = var1.yCoord - var0.yCoord;
        final double var4 = var1.zCoord - var0.zCoord;
        final double var5 = Math.sqrt(var2 * var2 + var4 * var4);
        final float var6 = (float)(Math.atan2(var4, var2) * 180.0 / 3.141592653589793) - 90.0f;
        final float var7 = (float)(-(Math.atan2(var3, var5) * 180.0 / 3.141592653589793));
        return new float[] { var6, var7 };
    }
    
    public static float[] getRotationFromPosition(final double x, final double z, final double y) {
        final double xDiff = x - Minecraft.getMinecraft().thePlayer.posX;
        final double zDiff = z - Minecraft.getMinecraft().thePlayer.posZ;
        final double yDiff = y - Minecraft.getMinecraft().thePlayer.posY - 1.2;
        final double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        final float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(yDiff, dist) * 180.0 / 3.141592653589793));
        return new float[] { yaw, pitch };
    }
    
    public static float[] faceTarget(final Entity target, final float p_706252, final float p_706253, final boolean miss) {
        final double var4 = target.posX - Helper.mc.thePlayer.posX;
        final double var5 = target.posZ - Helper.mc.thePlayer.posZ;
        double var7;
        if (target instanceof EntityLivingBase) {
            final EntityLivingBase var6 = (EntityLivingBase)target;
            var7 = var6.posY + var6.getEyeHeight() - (Helper.mc.thePlayer.posY + Helper.mc.thePlayer.getEyeHeight());
        }
        else {
            var7 = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0 - (Helper.mc.thePlayer.posY + Helper.mc.thePlayer.getEyeHeight());
        }
        final double var8 = MathHelper.sqrt_double(var4 * var4 + var5 * var5);
        final float var9 = (float)(Math.atan2(var5, var4) * 180.0 / 3.141592653589793) - 90.0f;
        final float var10 = (float)(-Math.atan2(var7 - ((target instanceof EntityPlayer) ? 0.25 : 0.0), var8) * 180.0 / 3.141592653589793);
        final float pitch = changeRotation(Helper.mc.thePlayer.rotationPitch, var10, p_706253);
        final float yaw = changeRotation(Helper.mc.thePlayer.rotationYaw, var9, p_706252);
        return new float[] { yaw, pitch };
    }
    
    public static float changeRotation(final float p_706631, final float p_706632, final float p_706633) {
        float var4 = MathHelper.wrapAngleTo180_float(p_706632 - p_706631);
        if (var4 > p_706633) {
            var4 = p_706633;
        }
        if (var4 < -p_706633) {
            var4 = -p_706633;
        }
        return p_706631 + var4;
    }
    
    public static float[] getRotationToEntity(final Entity entity) {
        final double pX = Helper.mc.thePlayer.posX;
        final double pY = Helper.mc.thePlayer.posY + Helper.mc.thePlayer.getEyeHeight();
        final double pZ = Helper.mc.thePlayer.posZ;
        final double eX = entity.posX;
        final double eY = entity.posY + entity.height / 2.0f;
        final double eZ = entity.posZ;
        final double dX = pX - eX;
        final double dY = pY - eY;
        final double dZ = pZ - eZ;
        final double dH = Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0));
        final double yaw = Math.toDegrees(Math.atan2(dZ, dX)) + 90.0;
        final double pitch = Math.toDegrees(Math.atan2(dH, dY));
        return new float[] { (float)yaw, (float)(90.0 - pitch) };
    }
    
    public static float[] getRotations(final Entity entity) {
        if (entity == null) {
            return null;
        }
        final double diffX = entity.posX - Helper.mc.thePlayer.posX;
        final double diffZ = entity.posZ - Helper.mc.thePlayer.posZ;
        double diffY;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase elb = (EntityLivingBase)entity;
            diffY = elb.posY + (elb.getEyeHeight() - 0.4) - (Helper.mc.thePlayer.posY + Helper.mc.thePlayer.getEyeHeight());
        }
        else {
            diffY = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - (Helper.mc.thePlayer.posY + Helper.mc.thePlayer.getEyeHeight());
        }
        final double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-Math.atan2(diffY, dist) * 180.0 / 3.141592653589793);
        return new float[] { yaw, pitch };
    }
    
    public static float getDistanceBetweenAngles(final float angle1, final float angle2) {
        float angle3 = Math.abs(angle1 - angle2) % 360.0f;
        if (angle3 > 180.0f) {
            angle3 = 0.0f;
        }
        return angle3;
    }
    
    public static int wrapAngleToDirection(final float yaw, final int zones) {
        int angle = (int)(yaw + 360 / (2 * zones) + 0.5) % 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle / (360 / zones);
    }
    
    public static Rotation getVecRotationByRotation(final Vec3 pos) {
        return new Rotation(getVecRotation(pos)[0], getVecRotation(pos)[1]);
    }
    
    public static float[] getVecRotation(final Vec3 position) {
        return getVecRotation(Helper.mc.thePlayer.getPositionVector().addVector(0.0, (double)Helper.mc.thePlayer.getEyeHeight(), 0.0), position);
    }
    
    public static float[] getVecRotation(final Vec3 origin, final Vec3 position) {
        final Vec3 difference = position.subtract(origin);
        final double distance = difference.normalize().lengthVector();
        final float yaw = (float)Math.toDegrees(Math.atan2(difference.zCoord, difference.xCoord)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(difference.yCoord, distance)));
        return new float[] { yaw, pitch };
    }
    
    public static float[] getPredictedRotations(final EntityLivingBase ent) {
        final double x = ent.posX + (ent.posX - ent.lastTickPosX);
        final double z = ent.posZ + (ent.posZ - ent.lastTickPosZ);
        final double y = ent.posY + ent.getEyeHeight() / 2.0f;
        return getRotationFromPosition(x, z, y);
    }
    
    static {
        RotationUtil.mc = Minecraft.getMinecraft();
    }
}
