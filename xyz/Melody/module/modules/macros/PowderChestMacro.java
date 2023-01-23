//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.macros;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import xyz.Melody.Event.events.rendering.*;
import net.minecraft.tileentity.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.render.*;
import java.util.*;
import net.minecraft.util.*;

public class PowderChestMacro extends Module
{
    public static Vec3 nextRotation;
    public static ArrayList<BlockPos> done;
    public static Option<Boolean> autoClear;
    public Option<Boolean> silent;
    private Numbers<Double> velocity;
    public static Vec3 chest;
    public static BlockPos chestPos;
    private float silentYaw;
    private float silentPitch;
    
    public PowderChestMacro() {
        super("PowderChest", new String[] { "chest" }, ModuleType.Macros);
        this.silent = (Option<Boolean>)new Option("Silent", (Object)false);
        this.velocity = (Numbers<Double>)new Numbers("Speed", (Number)50.0, (Number)30.0, (Number)100.0, (Number)1.0);
        this.addValues(new Value[] { (Value)PowderChestMacro.autoClear, (Value)this.silent, (Value)this.velocity });
        this.setModInfo("Auto Unlock Powder Chests.");
    }
    
    @EventHandler
    private void onUpdate(final EventPreUpdate event) {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        PowderChestMacro.chest = this.getChest();
        PowderChestMacro.chestPos = this.getChestPos();
    }
    
    @EventHandler
    private void onRotation(final EventPreUpdate event) {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        if (PowderChestMacro.chest != null && PowderChestMacro.nextRotation != null) {
            final float yaw = this.vec3ToRotation(PowderChestMacro.nextRotation).yaw;
            final float pitch = this.vec3ToRotation(PowderChestMacro.nextRotation).pitch;
            final float speed = ((Double)this.velocity.getValue()).floatValue();
            if (this.silent.getValue()) {
                event.setYaw(this.silentYaw = this.smoothRotation(this.silentYaw, yaw, speed));
                event.setPitch(this.silentPitch = this.smoothRotation(this.silentPitch, pitch, speed));
            }
            else {
                this.mc.thePlayer.rotationYaw = this.smoothRotation(this.mc.thePlayer.rotationYaw, yaw, speed);
                this.mc.thePlayer.rotationPitch = this.smoothRotation(this.mc.thePlayer.rotationPitch, pitch, speed);
            }
        }
        else {
            this.silentYaw = this.mc.thePlayer.rotationYaw;
            this.silentPitch = this.mc.thePlayer.rotationPitch;
        }
    }
    
    @EventHandler
    public void receivePacket(final EventPacketRecieve event) {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        if (event.getPacket() instanceof S2APacketParticles) {
            final S2APacketParticles packet = (S2APacketParticles)event.getPacket();
            if (packet.getParticleType().equals((Object)EnumParticleTypes.CRIT)) {
                final Vec3 particlePos = new Vec3(packet.getXCoordinate(), packet.getYCoordinate(), packet.getZCoordinate());
                if (PowderChestMacro.chest != null) {
                    final double dist = PowderChestMacro.chest.distanceTo(particlePos);
                    if (dist < 1.0) {
                        PowderChestMacro.nextRotation = particlePos;
                    }
                }
            }
        }
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        PowderChestMacro.done.clear();
        super.onDisable();
    }
    
    @EventHandler
    public void onR3D(final EventRender3D event) {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        for (final TileEntity entity : this.mc.theWorld.loadedTileEntityList) {
            if (!(entity instanceof TileEntityChest)) {
                continue;
            }
            final TileEntityChest chouShaBi = (TileEntityChest)entity;
            RenderUtil.drawSolidBlockESP(chouShaBi.getPos(), Colors.BLUE.c, event.getPartialTicks());
        }
        if (this.getChestPos() != null) {
            RenderUtil.drawSolidBlockESP(this.getChestPos(), Colors.ORANGE.c, event.getPartialTicks());
        }
    }
    
    private Vec3 getChest() {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return null;
        }
        final ArrayList<Vec3> chests = new ArrayList<Vec3>();
        if (!chests.isEmpty()) {
            chests.clear();
        }
        for (final TileEntity entity : this.mc.theWorld.loadedTileEntityList) {
            if (entity instanceof TileEntityChest) {
                final TileEntityChest chest = (TileEntityChest)entity;
                final Vec3 chestVec = new Vec3((double)(chest.getPos().getX() + 0.5f), (double)chest.getPos().getY(), (double)(chest.getPos().getZ() + 0.5f));
                final BlockPos chestPos = chest.getPos();
                if (PowderChestMacro.done.contains(chestPos)) {
                    continue;
                }
                if (this.mc.thePlayer.getDistance((double)chest.getPos().getX(), (double)chest.getPos().getY(), (double)chest.getPos().getZ()) >= 4.0) {
                    continue;
                }
                chests.add(chestVec);
            }
        }
        chests.sort(Comparator.comparingDouble(vec -> this.mc.thePlayer.getDistance(vec.xCoord, vec.yCoord, vec.zCoord)));
        if (!chests.isEmpty()) {
            return chests.get(0);
        }
        return null;
    }
    
    private BlockPos getChestPos() {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return null;
        }
        BlockPos chest = null;
        if (PowderChestMacro.chest != null) {
            chest = new BlockPos(PowderChestMacro.chest);
        }
        return chest;
    }
    
    private float smoothRotation(final float current, final float target, final float maxIncrement) {
        float deltaAngle = MathHelper.wrapAngleTo180_float(target - current);
        if (deltaAngle > maxIncrement) {
            deltaAngle = maxIncrement;
        }
        if (deltaAngle < -maxIncrement) {
            deltaAngle = -maxIncrement;
        }
        return current + deltaAngle / 2.0f;
    }
    
    public Rotation vec3ToRotation(final Vec3 vec) {
        final double diffX = vec.xCoord - this.mc.thePlayer.posX;
        final double diffY = vec.yCoord - this.mc.thePlayer.posY - this.mc.thePlayer.getEyeHeight();
        final double diffZ = vec.zCoord - this.mc.thePlayer.posZ;
        final double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float pitch = (float)(-Math.atan2(dist, diffY));
        float yaw = (float)Math.atan2(diffZ, diffX);
        pitch = (float)wrapAngleTo180((pitch * 180.0f / 3.141592653589793 + 90.0) * -1.0);
        yaw = (float)wrapAngleTo180(yaw * 180.0f / 3.141592653589793 - 90.0);
        return new Rotation(pitch, yaw);
    }
    
    private static double wrapAngleTo180(final double angle) {
        return angle - Math.floor(angle / 360.0 + 0.5) * 360.0;
    }
    
    static {
        PowderChestMacro.nextRotation = null;
        PowderChestMacro.done = new ArrayList<BlockPos>();
        PowderChestMacro.autoClear = (Option<Boolean>)new Option("AutoClear", (Object)true);
    }
    
    private static class Rotation
    {
        public float pitch;
        public float yaw;
        
        public Rotation(final float pitch, final float yaw) {
            this.pitch = pitch;
            this.yaw = yaw;
        }
    }
}
