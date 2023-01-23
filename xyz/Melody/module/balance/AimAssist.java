//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.util.*;
import xyz.Melody.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;

public class AimAssist extends Module
{
    private Random rand;
    private Numbers<Double> h;
    private Numbers<Double> v;
    private Numbers<Double> s;
    private Numbers<Double> r;
    private Numbers<Double> amin;
    private Numbers<Double> amax;
    private Option<Boolean> ca;
    private Option<Boolean> str;
    private Option<Boolean> team;
    
    public AimAssist() {
        super("AimAssist", new String[] { "AimAssist" }, ModuleType.Balance);
        this.rand = new Random();
        this.h = (Numbers<Double>)new Numbers("Horizontal", (Number)4.2, (Number)0.0, (Number)10.0, (Number)0.1);
        this.v = (Numbers<Double>)new Numbers("Vertical", (Number)2.4, (Number)0.0, (Number)10.0, (Number)0.1);
        this.s = (Numbers<Double>)new Numbers("Speed", (Number)0.2, (Number)0.0, (Number)1.5, (Number)0.01);
        this.r = (Numbers<Double>)new Numbers("AimRange", (Number)4.2, (Number)1.0, (Number)8.1, (Number)0.1);
        this.amin = (Numbers<Double>)new Numbers("MinAngle", (Number)0.0, (Number)0.0, (Number)1.0, (Number)1.0);
        this.amax = (Numbers<Double>)new Numbers("MaxAngle", (Number)100.0, (Number)20.0, (Number)360.0, (Number)1.0);
        this.ca = (Option<Boolean>)new Option("ClickAim", (Object)false);
        this.str = (Option<Boolean>)new Option("MoveStrafing", (Object)false);
        this.team = (Option<Boolean>)new Option("Team", (Object)true);
        this.addValues((Value)this.h, (Value)this.v, (Value)this.s, (Value)this.r, (Value)this.amin, (Value)this.amax, (Value)this.ca, (Value)this.str, (Value)this.team);
    }
    
    @EventHandler
    public void onUpdate(final EventPreUpdate event) {
        if ((boolean)this.ca.getValue() && !this.mc.gameSettings.keyBindAttack.isKeyDown()) {
            return;
        }
        Entity entity = null;
        double maxDistance = 360.0;
        final double maxAngle = (double)this.amax.getValue();
        final double minAngle = (double)this.amin.getValue();
        for (final Object e : this.mc.theWorld.getLoadedEntityList()) {
            final Entity en = (Entity)e;
            if (en != this.mc.thePlayer && this.isValid(en)) {
                final double yawdistance;
                if (maxDistance <= (yawdistance = this.getDistanceBetweenAngles(this.getAngles(en)[1], this.mc.thePlayer.rotationYaw))) {
                    continue;
                }
                entity = en;
                maxDistance = yawdistance;
            }
        }
        if (entity != null) {
            final float yaw2 = this.getAngles(entity)[1];
            final float pitch = this.getAngles(entity)[0];
            final double yawdistance2 = this.getDistanceBetweenAngles(yaw2, this.mc.thePlayer.rotationYaw);
            final double pitchdistance = this.getDistanceBetweenAngles(pitch, this.mc.thePlayer.rotationPitch);
            if (pitchdistance <= maxAngle && yawdistance2 >= minAngle && yawdistance2 <= maxAngle) {
                double horizontalSpeed = (double)this.h.getValue() * 3.0 + (((double)this.h.getValue() > 0.0) ? this.rand.nextDouble() : 0.0);
                double verticalSpeed = (double)this.v.getValue() * 3.0 + (((double)this.v.getValue() > 0.0) ? this.rand.nextDouble() : 0.0);
                if ((boolean)this.str.getValue() && this.mc.thePlayer.moveStrafing != 0.0f) {
                    horizontalSpeed *= 1.25;
                }
                if (this.getEntity(24.0) != null && this.getEntity(24.0).equals((Object)entity)) {
                    horizontalSpeed *= (double)this.s.getValue();
                    verticalSpeed *= (double)this.s.getValue();
                }
                this.faceTarget(entity, 0.0f, (float)verticalSpeed);
                this.faceTarget(entity, (float)horizontalSpeed, 0.0f);
            }
        }
    }
    
    protected float getRotation(final float currentRotation, final float targetRotation, final float maxIncrement) {
        float deltaAngle = MathHelper.wrapAngleTo180_float(targetRotation - currentRotation);
        if (deltaAngle > maxIncrement) {
            deltaAngle = maxIncrement;
        }
        if (deltaAngle < -maxIncrement) {
            deltaAngle = -maxIncrement;
        }
        return currentRotation + deltaAngle / 2.0f;
    }
    
    private void faceTarget(final Entity target, final float yawspeed, final float pitchspeed) {
        final EntityPlayerSP player = this.mc.thePlayer;
        final float yaw = this.getAngles(target)[1];
        final float pitch = this.getAngles(target)[0];
        player.rotationYaw = this.getRotation(player.rotationYaw, yaw, yawspeed);
        player.rotationPitch = this.getRotation(player.rotationPitch, pitch, pitchspeed);
    }
    
    public float[] getAngles(final Entity entity) {
        final double x = entity.posX - this.mc.thePlayer.posX;
        final double z = entity.posZ - this.mc.thePlayer.posZ;
        final double y = (entity instanceof EntityEnderman) ? (entity.posY - this.mc.thePlayer.posY) : (entity.posY + (entity.getEyeHeight() - 1.9) - this.mc.thePlayer.posY + (this.mc.thePlayer.getEyeHeight() - 1.9));
        final double helper = MathHelper.sqrt_double(x * x + z * z);
        float newYaw = (float)Math.toDegrees(-Math.atan(x / z));
        final float newPitch = (float)(-Math.toDegrees(Math.atan(y / helper)));
        if (z < 0.0 && x < 0.0) {
            newYaw = (float)(90.0 + Math.toDegrees(Math.atan(z / x)));
        }
        else if (z < 0.0 && x > 0.0) {
            newYaw = (float)(-90.0 + Math.toDegrees(Math.atan(z / x)));
        }
        return new float[] { newPitch, newYaw };
    }
    
    public double getDistanceBetweenAngles(final float angle1, final float angle2) {
        float distance = Math.abs(angle1 - angle2) % 360.0f;
        if (distance > 180.0f) {
            distance = 360.0f - distance;
        }
        return distance;
    }
    
    public Object[] getEntity(final double distance, final double expand, final float partialTicks) {
        final Entity var2 = this.mc.getRenderViewEntity();
        Entity entity = null;
        if (var2 == null || this.mc.theWorld == null) {
            return null;
        }
        this.mc.mcProfiler.startSection("pick");
        final double var3 = distance;
        final double var4 = distance;
        final Vec3 var5 = var2.getPositionEyes(0.0f);
        final Vec3 var6 = var2.getLook(0.0f);
        final Vec3 var7 = var5.addVector(var6.xCoord * var3, var6.yCoord * var3, var6.zCoord * var3);
        Vec3 var8 = null;
        final float var9 = 1.0f;
        final List<Entity> var10 = (List<Entity>)this.mc.theWorld.getEntitiesWithinAABBExcludingEntity(var2, var2.getEntityBoundingBox().addCoord(var6.xCoord * var3, var6.yCoord * var3, var6.zCoord * var3).expand((double)var9, (double)var9, (double)var9));
        double var11 = var4;
        for (int var12 = 0; var12 < var10.size(); ++var12) {
            final Entity var13 = var10.get(var12);
            if (var13.canBeCollidedWith()) {
                final float var14 = var13.getCollisionBorderSize();
                AxisAlignedBB var15 = var13.getEntityBoundingBox().expand((double)var14, (double)var14, (double)var14);
                var15 = var15.expand(expand, expand, expand);
                final MovingObjectPosition var16 = var15.calculateIntercept(var5, var7);
                if (var15.isVecInside(var5)) {
                    if (0.0 < var11 || var11 == 0.0) {
                        entity = var13;
                        var8 = ((var16 == null) ? var5 : var16.hitVec);
                        var11 = 0.0;
                    }
                }
                else {
                    final double var17;
                    if (var16 != null && ((var17 = var5.distanceTo(var16.hitVec)) < var11 || var11 == 0.0)) {
                        entity = var13;
                        var8 = var16.hitVec;
                        var11 = var17;
                    }
                }
            }
        }
        if (var11 < var4 && !(entity instanceof EntityLivingBase) && !(entity instanceof EntityItemFrame)) {
            entity = null;
        }
        this.mc.mcProfiler.endSection();
        if (entity == null || var8 == null) {
            return null;
        }
        return new Object[] { entity, var8 };
    }
    
    public Entity getEntity(final double distance) {
        if (this.getEntity(distance, 0.0, 0.0f) == null) {
            return null;
        }
        return (Entity)this.getEntity(distance, 0.0, 0.0f)[0];
    }
    
    public boolean isValid(final Entity e) {
        boolean flag1 = true;
        final AntiBot ab = (AntiBot)Client.instance.getModuleManager().getModuleByClass(AntiBot.class);
        if (ab.isEnabled()) {
            if (ab.isEnabled()) {
                flag1 = ab.isEntityBot(e);
            }
        }
        else {
            flag1 = true;
        }
        return e instanceof EntityLivingBase && !(e instanceof EntityArmorStand) && !(e instanceof EntityAnimal) && !(e instanceof EntityMob) && e != this.mc.thePlayer && !(e instanceof EntityVillager) && this.mc.thePlayer.getDistanceToEntity(e) <= (double)this.r.getValue() && !e.getName().contains("#") && (!(boolean)this.team.getValue() || !e.getDisplayName().getFormattedText().startsWith("\ufffd\ufffd" + this.mc.thePlayer.getDisplayName().getFormattedText().charAt(1))) && flag1;
    }
}
