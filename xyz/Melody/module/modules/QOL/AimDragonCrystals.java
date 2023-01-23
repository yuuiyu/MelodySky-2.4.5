//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import net.minecraft.entity.item.*;
import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import net.minecraft.item.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.entity.*;
import java.util.*;

public class AimDragonCrystals extends Module
{
    private EntityEnderCrystal curCrystal;
    private TimerUtil timer;
    
    public AimDragonCrystals() {
        super("AimCrystals", new String[] { "shootcs" }, ModuleType.QOL);
        this.curCrystal = null;
        this.timer = new TimerUtil();
        this.setModInfo("Auto Aim Crystals in Dragon Fucking.");
    }
    
    @EventHandler
    private void onTick(final EventPreUpdate event) {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.The_End) {
            return;
        }
        if (this.curCrystal == null || !this.curCrystal.isEntityAlive() || this.timer.hasReached(500.0)) {
            this.curCrystal = this.getCrystal();
            this.timer.reset();
        }
        final ItemStack is = this.mc.thePlayer.getHeldItem();
        if (this.curCrystal != null && is != null && is.getItem() instanceof ItemBow && this.curCrystal.isEntityAlive()) {
            final float bowVelocity = 1.0f;
            final double v = bowVelocity * 3.0f;
            final double g = 0.05000000074505806;
            final double xDistance = this.curCrystal.posX - this.mc.thePlayer.posX + (this.curCrystal.posX - this.curCrystal.lastTickPosX) * (bowVelocity * 10.0f);
            final double zDistance = this.curCrystal.posZ - this.mc.thePlayer.posZ + (this.curCrystal.posZ - this.curCrystal.lastTickPosZ) * (bowVelocity * 10.0f);
            final float trajectoryTheta90 = (float)(Math.atan2(zDistance, xDistance) * 180.0 / 3.141592653589793) - 90.0f;
            final float bowTrajectory = (float)((float)(-Math.toDegrees(this.getLaunchAngle(this.curCrystal, v, g))) - 3.8);
            if (trajectoryTheta90 <= 360.0f && bowTrajectory <= 360.0f) {
                event.setYaw(trajectoryTheta90);
                event.setPitch(bowTrajectory);
            }
        }
    }
    
    @EventHandler
    private void on3D(final EventRender3D event) {
        if (this.curCrystal != null && this.curCrystal.isEntityAlive()) {
            RenderUtil.drawFilledESP((Entity)this.curCrystal, Color.PINK, event, 3.0f);
        }
    }
    
    private EntityEnderCrystal getCrystal() {
        final ArrayList<EntityEnderCrystal> cs = new ArrayList<EntityEnderCrystal>();
        for (final Entity e2 : this.mc.theWorld.loadedEntityList) {
            if (e2 instanceof EntityEnderCrystal) {
                cs.add((EntityEnderCrystal)e2);
            }
        }
        cs.sort(Comparator.comparingDouble(e -> this.mc.thePlayer.getDistanceToEntity(e)));
        if (!cs.isEmpty()) {
            return cs.get(0);
        }
        return null;
    }
    
    private float getLaunchAngle(final EntityEnderCrystal e, final double v, final double g) {
        final double yDif = e.posY + e.getEyeHeight() / 2.0f - (this.mc.thePlayer.posY + this.mc.thePlayer.getEyeHeight());
        final double xDif = e.posX - this.mc.thePlayer.posX;
        final double zDif = e.posZ - this.mc.thePlayer.posZ;
        final double xCoord = Math.sqrt(xDif * xDif + zDif * zDif);
        return this.theta(v + 2.0, g, xCoord, yDif);
    }
    
    private float theta(final double v, final double g, final double x, final double y) {
        final double yv = 2.0 * y * (v * v);
        final double gx = g * (x * x);
        final double g2 = g * (gx + yv);
        final double insqrt = v * v * v * v - g2;
        final double sqrt = Math.sqrt(insqrt);
        final double numerator = v * v + sqrt;
        final double numerator2 = v * v - sqrt;
        final double atan1 = Math.atan2(numerator, g * x);
        final double atan2 = Math.atan2(numerator2, g * x);
        return (float)Math.min(atan1, atan2);
    }
}
