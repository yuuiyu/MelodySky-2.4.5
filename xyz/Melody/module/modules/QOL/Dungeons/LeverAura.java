//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import java.util.*;
import net.minecraft.block.state.*;

public class LeverAura extends Module
{
    public static BlockPos blockPos;
    private TimerUtil timer;
    private Option<Boolean> dungeon;
    private Option<Boolean> bossEntry;
    private Option<Boolean> clickedCheck;
    private Option<Boolean> poweredCheck;
    private Numbers<Double> delay;
    private Numbers<Double> range;
    public static ArrayList<BlockPos> allLevers;
    public static ArrayList<BlockPos> clicked;
    
    public LeverAura() {
        super("LeverAura", new String[] { "la" }, ModuleType.Dungeons);
        this.timer = new TimerUtil();
        this.dungeon = (Option<Boolean>)new Option("DungeonOnly", (Object)true);
        this.bossEntry = (Option<Boolean>)new Option("BossOnly", (Object)true);
        this.clickedCheck = (Option<Boolean>)new Option("ClickedCheck", (Object)false);
        this.poweredCheck = (Option<Boolean>)new Option("PoweredCheck", (Object)true);
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)50.0, (Number)10.0, (Number)200.0, (Number)1.0);
        this.range = (Numbers<Double>)new Numbers("Range", (Number)4.0, (Number)0.0, (Number)4.5, (Number)0.1);
        this.addValues(new Value[] { (Value)this.dungeon, (Value)this.bossEntry, (Value)this.clickedCheck, (Value)this.poweredCheck, (Value)this.range, (Value)this.delay });
        this.setModInfo("Auto Pull Levers Around You.");
    }
    
    @EventHandler
    private void destoryBlock(final EventTick event) {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        if ((boolean)this.dungeon.getValue() && !Client.inDungeons) {
            return;
        }
        if (!Client.instance.dungeonUtils.inBoss && (boolean)this.bossEntry.getValue()) {
            return;
        }
        if (LeverAura.blockPos == null) {
            LeverAura.blockPos = this.getLever();
            this.timer.reset();
            return;
        }
        if (LeverAura.blockPos != null && this.timer.hasReached((double)this.delay.getValue())) {
            LeverAura.clicked.add(LeverAura.blockPos);
            this.mc.thePlayer.swingItem();
            this.mc.getNetHandler().getNetworkManager().sendPacket((Packet)new C08PacketPlayerBlockPlacement(LeverAura.blockPos, this.getClosestEnum(LeverAura.blockPos).getIndex(), this.mc.thePlayer.getCurrentEquippedItem(), (float)LeverAura.blockPos.getX(), (float)LeverAura.blockPos.getY(), (float)LeverAura.blockPos.getZ()));
            LeverAura.blockPos = null;
            this.timer.reset();
        }
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
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        LeverAura.blockPos = null;
        LeverAura.clicked.clear();
        LeverAura.allLevers.clear();
        super.onDisable();
    }
    
    @EventHandler
    public void onTick(final EventRender3D event) {
        for (final BlockPos pos : LeverAura.allLevers) {
            RenderUtil.drawSolidBlockESP(pos, Colors.RED.c, event.getPartialTicks());
        }
    }
    
    @EventHandler
    private void onWorldLoad(final EventTick event) {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            LeverAura.blockPos = null;
            LeverAura.clicked.clear();
            LeverAura.allLevers.clear();
        }
    }
    
    private BlockPos getLever() {
        final float r = ((Double)this.range.getValue()).floatValue();
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return null;
        }
        BlockPos playerPos = this.mc.thePlayer.getPosition();
        playerPos = playerPos.add(0, 1, 0);
        final Vec3i vec3i = new Vec3i((double)r, (double)r, (double)r);
        final ArrayList<Vec3> levers = new ArrayList<Vec3>();
        if (playerPos != null) {
            LeverAura.allLevers.clear();
            for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                if (blockState.getBlock() instanceof BlockLever && (!LeverAura.clicked.contains(blockPos) || !(boolean)this.clickedCheck.getValue())) {
                    final BlockLever lever = (BlockLever)blockState.getBlock();
                    if ((boolean)(boolean)blockState.getValue((IProperty)BlockLever.POWERED) && (boolean)this.poweredCheck.getValue()) {
                        continue;
                    }
                    LeverAura.allLevers.add(blockPos);
                    levers.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                }
            }
        }
        levers.sort(Comparator.comparingDouble(vec -> this.mc.thePlayer.getDistance(vec.xCoord, vec.yCoord, vec.zCoord)));
        if (!levers.isEmpty()) {
            return new BlockPos((Vec3)levers.get(0));
        }
        return null;
    }
    
    static {
        LeverAura.allLevers = new ArrayList<BlockPos>();
        LeverAura.clicked = new ArrayList<BlockPos>();
    }
}
