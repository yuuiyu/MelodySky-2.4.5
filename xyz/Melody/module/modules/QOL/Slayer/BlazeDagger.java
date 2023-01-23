//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Slayer;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import net.minecraft.client.multiplayer.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class BlazeDagger extends Module
{
    private long lastClickTime;
    private MethodReflectionHelper CONTROLLER;
    
    public BlazeDagger() {
        super("AutoBlazeDagger", new String[] { "cb" }, ModuleType.Nether);
        this.lastClickTime = 0L;
        this.CONTROLLER = new MethodReflectionHelper(PlayerControllerMP.class, "syncCurrentPlayItem", "syncCurrentPlayItem", (Class<?>[])new Class[0]);
        this.setColor(new Color(158, 205, 125).getRGB());
        this.setModInfo("Auto Swap Dagger Mode.");
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void onRenderEntity(final RenderLivingEvent.Pre<EntityLivingBase> event) {
        if (event.entity instanceof EntityArmorStand) {
            final EntityArmorStand entity = (EntityArmorStand)event.entity;
            if (!entity.hasCustomName()) {
                return;
            }
            final String entityName = StringUtils.stripControlCodes(entity.getCustomNameTag());
            final double x = event.entity.posX;
            final double y = event.entity.posY;
            final double z = event.entity.posZ;
            if (Client.instance.sbArea.getCurrentArea() == SkyblockArea.Areas.Crimson_Island && this.mc.currentScreen == null && this.shouldClick()) {
                if (entityName.startsWith("CRYSTAL")) {
                    if (this.isFacingAABB(new AxisAlignedBB(x - 0.5, y - 3.0, z - 0.5, x + 0.5, y + 1.0, z + 0.5), 5.0f)) {
                        this.swapToCrystal();
                    }
                    return;
                }
                if (entityName.startsWith("ASHEN")) {
                    if (this.isFacingAABB(new AxisAlignedBB(x - 0.5, y - 3.0, z - 0.5, x + 0.5, y + 1.0, z + 0.5), 5.0f)) {
                        this.swapToAshen();
                    }
                    return;
                }
                if (entityName.startsWith("AURIC")) {
                    if (this.isFacingAABB(new AxisAlignedBB(x - 0.5, y - 3.0, z - 0.5, x + 0.5, y + 1.0, z + 0.5), 5.0f)) {
                        this.swapToAuric();
                    }
                    return;
                }
                if (entityName.startsWith("SPIRIT")) {
                    if (this.isFacingAABB(new AxisAlignedBB(x - 0.5, y - 3.0, z - 0.5, x + 0.5, y + 1.0, z + 0.5), 5.0f)) {
                        this.swapToSprit();
                    }
                }
            }
        }
    }
    
    public void swapToCrystal() {
        for (int i = 0; i < 8; ++i) {
            final ItemStack item = this.mc.thePlayer.inventory.mainInventory[i];
            if (item != null) {
                final String name = item.getDisplayName();
                if (name.contains("Deathripper Dagger") || name.contains("Mawdredge Dagger") || name.contains("Twilight Dagger")) {
                    this.mc.thePlayer.inventory.currentItem = i;
                    if (item.getItem() != Items.diamond_sword) {
                        this.CONTROLLER.invoke(this.mc.playerController);
                        Client.rightClick();
                    }
                    this.lastClickTime = System.currentTimeMillis();
                    break;
                }
            }
        }
    }
    
    public void swapToSprit() {
        for (int i = 0; i < 8; ++i) {
            final ItemStack item = this.mc.thePlayer.inventory.mainInventory[i];
            if (item != null) {
                final String name = item.getDisplayName();
                if (name.contains("Deathripper Dagger") || name.contains("Mawdredge Dagger") || name.contains("Twilight Dagger")) {
                    this.mc.thePlayer.inventory.currentItem = i;
                    if (item.getItem() != Items.iron_sword) {
                        this.CONTROLLER.invoke(this.mc.playerController);
                        Client.rightClick();
                    }
                    this.lastClickTime = System.currentTimeMillis();
                    break;
                }
            }
        }
    }
    
    public void swapToAshen() {
        for (int i = 0; i < 8; ++i) {
            final ItemStack item = this.mc.thePlayer.inventory.mainInventory[i];
            if (item != null) {
                final String name = item.getDisplayName();
                if (name.contains("Pyrochaos Dagger") || name.contains("Kindlebane Dagger") || name.contains("Firedust Dagger")) {
                    this.mc.thePlayer.inventory.currentItem = i;
                    if (item.getItem() != Items.stone_sword) {
                        this.CONTROLLER.invoke(this.mc.playerController);
                        Client.rightClick();
                    }
                    this.lastClickTime = System.currentTimeMillis();
                    break;
                }
            }
        }
    }
    
    public void swapToAuric() {
        for (int i = 0; i < 8; ++i) {
            final ItemStack item = this.mc.thePlayer.inventory.mainInventory[i];
            if (item != null) {
                final String name = item.getDisplayName();
                if (name.contains("Pyrochaos Dagger") || name.contains("Kindlebane Dagger") || name.contains("Firedust Dagger")) {
                    this.mc.thePlayer.inventory.currentItem = i;
                    if (item.getItem() != Items.golden_sword) {
                        this.CONTROLLER.invoke(this.mc.playerController);
                        Client.rightClick();
                    }
                    this.lastClickTime = System.currentTimeMillis();
                    break;
                }
            }
        }
    }
    
    public boolean shouldClick() {
        return System.currentTimeMillis() - this.lastClickTime >= 500L;
    }
    
    public boolean isFacingAABB(final AxisAlignedBB aabb, final float range) {
        return this.isInterceptable(aabb, range);
    }
    
    public Vec3 getPositionEyes() {
        return new Vec3(this.mc.thePlayer.posX, this.mc.thePlayer.posY + this.fastEyeHeight(), this.mc.thePlayer.posZ);
    }
    
    public float fastEyeHeight() {
        return this.mc.thePlayer.isSneaking() ? 1.54f : 1.62f;
    }
    
    public boolean isInterceptable(final AxisAlignedBB aabb, final float range) {
        final Vec3 position = this.getPositionEyes();
        final Vec3 look = this.getVectorForRotation();
        return this.isInterceptable(position, position.addVector(look.xCoord * range, look.yCoord * range, look.zCoord * range), aabb);
    }
    
    private Vec3 getVectorForRotation() {
        final float f2 = -MathHelper.cos(-this.mc.thePlayer.rotationPitch * 0.017453292f);
        return new Vec3((double)(MathHelper.sin(-this.mc.thePlayer.rotationYaw * 0.017453292f - 3.1415927f) * f2), (double)MathHelper.sin(-this.mc.thePlayer.rotationPitch * 0.017453292f), (double)(MathHelper.cos(-this.mc.thePlayer.rotationYaw * 0.017453292f - 3.1415927f) * f2));
    }
    
    public boolean isInterceptable(final Vec3 start, final Vec3 goal, final AxisAlignedBB aabb) {
        return this.isVecInYZ(start.getIntermediateWithXValue(goal, aabb.minX), aabb) || this.isVecInYZ(start.getIntermediateWithXValue(goal, aabb.maxX), aabb) || this.isVecInXZ(start.getIntermediateWithYValue(goal, aabb.minY), aabb) || this.isVecInXZ(start.getIntermediateWithYValue(goal, aabb.maxY), aabb) || this.isVecInXY(start.getIntermediateWithZValue(goal, aabb.minZ), aabb) || this.isVecInXY(start.getIntermediateWithZValue(goal, aabb.maxZ), aabb);
    }
    
    public boolean isVecInYZ(final Vec3 vec, final AxisAlignedBB aabb) {
        return vec != null && vec.yCoord >= aabb.minY && vec.yCoord <= aabb.maxY && vec.zCoord >= aabb.minZ && vec.zCoord <= aabb.maxZ;
    }
    
    public boolean isVecInXZ(final Vec3 vec, final AxisAlignedBB aabb) {
        return vec != null && vec.xCoord >= aabb.minX && vec.xCoord <= aabb.maxX && vec.zCoord >= aabb.minZ && vec.zCoord <= aabb.maxZ;
    }
    
    public boolean isVecInXY(final Vec3 vec, final AxisAlignedBB aabb) {
        return vec != null && vec.xCoord >= aabb.minX && vec.xCoord <= aabb.maxX && vec.yCoord >= aabb.minY && vec.yCoord <= aabb.maxY;
    }
}
