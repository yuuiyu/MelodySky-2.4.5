//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.macros;

import net.minecraft.entity.player.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Utils.*;
import xyz.Melody.*;
import net.minecraft.world.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.block.state.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.client.settings.*;
import xyz.Melody.Event.events.rendering.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import java.util.*;
import xyz.Melody.module.balance.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;

public class MithrilNuker extends Module
{
    private ArrayList<EntityPlayer> shabs;
    private BlockPos blockPos;
    private BlockPos lastBlockPos;
    private Numbers<Double> range;
    private Option<Boolean> rot;
    private Option<Boolean> sneak;
    private Option<Boolean> protect;
    private float currentDamage;
    private int blockHitDelay;
    private boolean tempDisable;
    private boolean sneaked;
    
    public MithrilNuker() {
        super("MithrilNuker", new String[] { "am" }, ModuleType.Macros);
        this.shabs = new ArrayList<EntityPlayer>();
        this.lastBlockPos = null;
        this.range = (Numbers<Double>)new Numbers("Range", (Number)5.0, (Number)1.0, (Number)6.0, (Number)0.1);
        this.rot = (Option<Boolean>)new Option("Rotation", (Object)true);
        this.sneak = (Option<Boolean>)new Option("Sneak", (Object)true);
        this.protect = (Option<Boolean>)new Option("MacroProtect", (Object)true);
        this.currentDamage = 0.0f;
        this.blockHitDelay = 0;
        this.tempDisable = false;
        this.sneaked = false;
        this.addValues(new Value[] { (Value)this.range, (Value)this.sneak, (Value)this.protect, (Value)this.rot });
        this.setModInfo("Auto Mine Mithril Around You.");
    }
    
    @EventHandler
    private void destoryBlock(final EventPreUpdate event) {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        if (this.playerWithin20B() && (boolean)this.protect.getValue()) {
            this.currentDamage = 0.0f;
            Helper.sendMessage("[Mithril Fucker] Player Detected in 20 Blocks, Auto Disabled.");
            this.setEnabled(false);
            return;
        }
        if (Client.pickaxeAbilityReady && this.mc.playerController != null && this.mc.thePlayer.inventory.getStackInSlot(this.mc.thePlayer.inventory.currentItem) != null) {
            this.mc.playerController.sendUseItem((EntityPlayer)this.mc.thePlayer, (World)this.mc.theWorld, this.mc.thePlayer.inventory.getStackInSlot(this.mc.thePlayer.inventory.currentItem));
            Client.pickaxeAbilityReady = false;
        }
        if (this.currentDamage > 100.0f) {
            this.currentDamage = 0.0f;
        }
        if (this.blockPos != null && this.mc.theWorld != null) {
            final IBlockState blockState = this.mc.theWorld.getBlockState(this.blockPos);
            if (blockState.getBlock() == Blocks.bedrock || blockState.getBlock() == Blocks.air) {
                this.currentDamage = 0.0f;
            }
        }
        if (this.currentDamage == 0.0f) {
            this.lastBlockPos = this.blockPos;
            this.blockPos = this.getBlock();
        }
        if (this.blockPos != null) {
            if (this.blockHitDelay > 0) {
                --this.blockHitDelay;
                return;
            }
            if (this.currentDamage == 0.0f) {
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, this.blockPos, EnumFacing.DOWN));
            }
            this.mc.thePlayer.swingItem();
            ++this.currentDamage;
        }
        if (this.rot.getValue()) {
            final float yaw = this.getRotations(this.blockPos, this.getClosestEnum(this.blockPos))[0];
            final float pitch = this.getRotations(this.blockPos, this.getClosestEnum(this.blockPos))[1];
            event.setYaw(this.mc.thePlayer.rotationYaw = this.smoothRotation(this.mc.thePlayer.rotationYaw, yaw, 70.0f));
            event.setPitch(this.mc.thePlayer.rotationPitch = this.smoothRotation(this.mc.thePlayer.rotationPitch, pitch, 70.0f));
        }
    }
    
    @EventHandler
    private void tick(final EventTick event) {
        if (this.sneak.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSneak.getKeyCode(), true);
        }
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.currentDamage = 0.0f;
        this.shabs.clear();
        this.sneaked = false;
        this.tempDisable = false;
        if (this.sneak.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }
        super.onDisable();
    }
    
    @EventHandler
    public void onTick(final EventRender3D event) {
        if (this.getBlock() != null) {
            RenderUtil.drawSolidBlockESP(this.getBlock(), new Color(198, 139, 255, 190).getRGB(), event.getPartialTicks());
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[MacroProtection] Auto Disabled " + EnumChatFormatting.GREEN + this.getName() + EnumChatFormatting.GRAY + " due to World Change.");
        this.setEnabled(false);
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
    
    private EnumFacing getClosestEnum(final BlockPos pos) {
        EnumFacing closestEnum = EnumFacing.UP;
        final float rotations = MathHelper.wrapAngleTo180_float(this.getRotations(pos, EnumFacing.UP)[0]);
        if (rotations >= 45.0f && rotations <= 135.0f) {
            closestEnum = EnumFacing.EAST;
        }
        else if ((rotations >= 135.0f && rotations <= 180.0f) || (rotations <= -135.0f && rotations >= -180.0f)) {
            closestEnum = EnumFacing.SOUTH;
        }
        else if (rotations <= -45.0f && rotations >= -135.0f) {
            closestEnum = EnumFacing.WEST;
        }
        else if ((rotations >= -45.0f && rotations <= 0.0f) || (rotations <= 45.0f && rotations >= 0.0f)) {
            closestEnum = EnumFacing.NORTH;
        }
        if (MathHelper.wrapAngleTo180_float(this.getRotations(pos, EnumFacing.UP)[1]) > 75.0f || MathHelper.wrapAngleTo180_float(this.getRotations(pos, EnumFacing.UP)[1]) < -75.0f) {
            closestEnum = EnumFacing.UP;
        }
        return closestEnum;
    }
    
    private BlockPos getBlock() {
        final int r = ((Double)this.range.getValue()).intValue();
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return null;
        }
        BlockPos playerPos = this.mc.thePlayer.getPosition();
        playerPos = playerPos.add(0, 1, 0);
        final Vec3 playerVec = this.mc.thePlayer.getPositionVector();
        final Vec3i vec3i = new Vec3i(r, r, r);
        final ArrayList<Vec3> chests = new ArrayList<Vec3>();
        if (playerPos != null) {
            for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                if (this.isMithril(blockState)) {
                    chests.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                }
            }
        }
        double smallest = 9999.0;
        Vec3 closest = null;
        for (int i = 0; i < chests.size(); ++i) {
            final double dist = chests.get(i).distanceTo(playerVec);
            if (dist < smallest) {
                smallest = dist;
                closest = chests.get(i);
            }
        }
        if (closest != null && smallest < 5.0) {
            return new BlockPos(closest.xCoord, closest.yCoord, closest.zCoord);
        }
        return null;
    }
    
    private boolean playerWithin20B() {
        for (final EntityPlayer player : this.mc.theWorld.playerEntities) {
            if (((AntiBot)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AntiBot.class)).isEntityBot((Entity)player)) {
                continue;
            }
            if (this.mc.thePlayer.getDistanceToEntity((Entity)player) < 20.0f && player != this.mc.thePlayer) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isMithril(final IBlockState blockState) {
        return blockState.getBlock() == Blocks.prismarine || blockState.getBlock() == Blocks.wool || blockState.getBlock() == Blocks.stained_hardened_clay || (blockState.getBlock() == Blocks.stone && blockState.getValue((IProperty)BlockStone.VARIANT) == BlockStone.EnumType.DIORITE_SMOOTH);
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
}
