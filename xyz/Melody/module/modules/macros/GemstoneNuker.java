//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.macros;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.*;
import xyz.Melody.Utils.Item.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.block.state.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.events.rendering.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraftforge.event.world.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.math.*;
import java.util.*;
import xyz.Melody.module.balance.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.*;

public class GemstoneNuker extends Module
{
    public ArrayList<BlockPos> gemstones;
    private BlockPos blockPos;
    private BlockPos lastBlockPos;
    public Mode<Enum> mode;
    public Numbers<Double> range;
    public Option<Boolean> blueEgg;
    public Numbers<Double> blueSlot;
    public Option<Boolean> under;
    private Option<Boolean> rot;
    private Option<Boolean> pickaxeCheck;
    private Option<Boolean> pane;
    public Option<Boolean> protect;
    private float currentDamage;
    private int blockHitDelay;
    private boolean tempDisable;
    private boolean holdingBlueEgg;
    private TimerUtil timer;
    private int lastSlot;
    
    public GemstoneNuker() {
        super("GemstoneNuker", new String[] { "gm" }, ModuleType.Macros);
        this.gemstones = new ArrayList<BlockPos>();
        this.lastBlockPos = null;
        this.mode = (Mode<Enum>)new Mode("Mode", (Enum[])Gemstone.values(), (Enum)Gemstone.JADE);
        this.range = (Numbers<Double>)new Numbers("Range", (Number)5.0, (Number)1.0, (Number)6.0, (Number)0.1);
        this.blueEgg = (Option<Boolean>)new Option("BlueEggDill", (Object)false);
        this.blueSlot = (Numbers<Double>)new Numbers("EggDrill Slot", (Number)5.0, (Number)1.0, (Number)6.0, (Number)0.1);
        this.under = (Option<Boolean>)new Option("Under", (Object)false);
        this.rot = (Option<Boolean>)new Option("Rotation", (Object)true);
        this.pickaxeCheck = (Option<Boolean>)new Option("Pickaxe", (Object)true);
        this.pane = (Option<Boolean>)new Option("Pane", (Object)false);
        this.protect = (Option<Boolean>)new Option("MacroProtect(10)", (Object)true);
        this.currentDamage = 0.0f;
        this.blockHitDelay = 0;
        this.tempDisable = false;
        this.holdingBlueEgg = false;
        this.timer = new TimerUtil();
        this.lastSlot = 0;
        this.addValues(new Value[] { (Value)this.mode, (Value)this.range, (Value)this.blueEgg, (Value)this.blueSlot, (Value)this.under, (Value)this.pickaxeCheck, (Value)this.protect, (Value)this.pane, (Value)this.rot });
        this.setModInfo("Auto Mine Gemstones Around You.");
    }
    
    @EventHandler
    private void destoryBlock(final EventPreUpdate event) {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        if (this.tempDisable) {
            return;
        }
        final AutoRuby ar = (AutoRuby)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AutoRuby.class);
        if (!ar.started && ar.isEnabled()) {
            return;
        }
        if (this.blockPos == null) {
            this.blockPos = this.getBlock();
            return;
        }
        if (this.mc.thePlayer.getDistance((double)this.blockPos.getX(), (double)this.blockPos.getY(), (double)this.blockPos.getZ()) > 6.0) {
            this.gemstones.remove(this.blockPos);
            this.blockPos = null;
            return;
        }
        if (this.pickaxeCheck.getValue()) {
            if (this.mc.thePlayer.getHeldItem() == null) {
                return;
            }
            final String id = ItemUtils.getSkyBlockID(this.mc.thePlayer.getHeldItem());
            if (this.mc.thePlayer.getHeldItem().getItem() != Items.prismarine_shard && !id.contains("GEMSTONE_GAUNTLET") && !(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemPickaxe)) {
                return;
            }
        }
        if (Client.pickaxeAbilityReady && this.mc.playerController != null) {
            if (this.blueEgg.getValue()) {
                if (!this.holdingBlueEgg) {
                    this.lastSlot = this.mc.thePlayer.inventory.currentItem;
                    for (int i = 0; i < 9; ++i) {
                        final ItemStack itemStack = this.mc.thePlayer.inventory.mainInventory[i];
                        if (itemStack != null && itemStack.getItem() != null) {
                            this.mc.thePlayer.inventory.currentItem = ((Double)this.blueSlot.getValue()).intValue();
                            this.holdingBlueEgg = true;
                            this.timer.reset();
                            break;
                        }
                    }
                }
                if (this.timer.hasReached(100.0)) {
                    if (this.mc.thePlayer.inventory.getStackInSlot(this.mc.thePlayer.inventory.currentItem) != null) {
                        this.mc.playerController.sendUseItem((EntityPlayer)this.mc.thePlayer, (World)this.mc.theWorld, this.mc.thePlayer.inventory.getStackInSlot(this.mc.thePlayer.inventory.currentItem));
                        Client.pickaxeAbilityReady = false;
                    }
                    if (this.timer.hasReached(150.0)) {
                        this.mc.thePlayer.inventory.currentItem = this.lastSlot;
                        this.holdingBlueEgg = false;
                        this.timer.reset();
                    }
                }
            }
            else if (this.mc.thePlayer.inventory.getStackInSlot(this.mc.thePlayer.inventory.currentItem) != null) {
                this.mc.playerController.sendUseItem((EntityPlayer)this.mc.thePlayer, (World)this.mc.theWorld, this.mc.thePlayer.inventory.getStackInSlot(this.mc.thePlayer.inventory.currentItem));
                Client.pickaxeAbilityReady = false;
            }
        }
        if (this.currentDamage > 40.0f) {
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
            final float[] r = this.getRotations(this.blockPos, this.getClosestEnum(this.blockPos));
            if (r == null) {
                return;
            }
            final float yaw = r[0];
            final float pitch = r[1];
            this.mc.thePlayer.rotationYaw = this.smoothRotation(this.mc.thePlayer.rotationYaw, yaw, 70.0f);
            this.mc.thePlayer.rotationPitch = this.smoothRotation(this.mc.thePlayer.rotationPitch, pitch, 70.0f);
        }
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (!(boolean)this.protect.getValue()) {
            return;
        }
        if (this.playerWithin10B() && !this.tempDisable) {
            this.tempDisable = true;
        }
        if (!this.playerWithin10B() && this.tempDisable) {
            this.tempDisable = false;
        }
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.currentDamage = 0.0f;
        this.gemstones.clear();
        this.tempDisable = false;
        super.onDisable();
    }
    
    @EventHandler
    public void onTick(final EventRender3D event) {
        if (this.getBlock() != null) {
            RenderUtil.drawSolidBlockESP(this.getBlock(), new Color(198, 139, 255, 190).getRGB(), event.getPartialTicks());
        }
        final BlockPos posUnder = new BlockPos(this.mc.thePlayer.getPositionVector()).down();
        RenderUtil.drawSolidBlockESP(posUnder, new Color(0, 139, 255, 190).getRGB(), event.getPartialTicks());
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[MacroProtection] Auto Disabled " + EnumChatFormatting.GREEN + this.getName() + EnumChatFormatting.GRAY + " due to World Change.");
        this.setEnabled(false);
    }
    
    public float[] getRotations(final BlockPos block, final EnumFacing face) {
        if (block == null) {
            return null;
        }
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
    
    public EnumFacing getClosestEnum(final BlockPos pos) {
        if (pos == null) {
            return EnumFacing.UP;
        }
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
        final Vec3i up = new Vec3i(r, r, r);
        final Vec3i down = new Vec3i((double)r, (double)(r + 1.1f), (double)r);
        final ArrayList<Vec3> chests = new ArrayList<Vec3>();
        final BlockPos posUnder = new BlockPos(this.mc.thePlayer.getPositionVector()).down();
        if (playerPos != null) {
            for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(up), playerPos.subtract(down))) {
                final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                if (!(boolean)this.under.getValue() && blockPos.getX() == posUnder.getX() && blockPos.getZ() == posUnder.getZ()) {
                    continue;
                }
                if (!this.isNeededBlock(blockState)) {
                    continue;
                }
                chests.add(new Vec3((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()));
            }
        }
        final ArrayList<BlockPos> gs = new ArrayList<BlockPos>();
        for (final Vec3 v : chests) {
            final BlockPos p = new BlockPos(v);
            final int x = this.mc.thePlayer.getPosition().getX();
            final int y = (int)(this.mc.thePlayer.getPosition().getY() + this.mc.thePlayer.getEyeHeight());
            final int z = this.mc.thePlayer.getPosition().getZ();
            if (MathUtil.distanceToPos(new BlockPos(x - 0.3, (double)y, z - 0.3), p) < (double)this.range.getValue()) {
                gs.add(p);
            }
        }
        this.gemstones = gs;
        if (!gs.isEmpty()) {
            return new BlockPos((Vec3i)gs.get(0));
        }
        return null;
    }
    
    private boolean playerWithin10B() {
        for (final EntityPlayer player : this.mc.theWorld.playerEntities) {
            if (((AntiBot)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AntiBot.class)).isEntityBot((Entity)player)) {
                continue;
            }
            if (this.mc.thePlayer.getDistanceToEntity((Entity)player) < 10.0f && player != this.mc.thePlayer) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isNeededBlock(final IBlockState blockState) {
        final Gemstone stone = this.getGemstone(blockState);
        return stone != null && stone.name().contains(((Enum)this.mode.getValue()).name());
    }
    
    private Gemstone getGemstone(final IBlockState block) {
        if (block.getBlock() != Blocks.stained_glass && block.getBlock() != Blocks.stained_glass_pane) {
            return null;
        }
        if (!(boolean)this.pane.getValue() && block.getBlock() == Blocks.stained_glass_pane) {
            return null;
        }
        final EnumDyeColor color = this.firstNotNull((EnumDyeColor)block.getValue((IProperty)BlockStainedGlass.COLOR), (EnumDyeColor)block.getValue((IProperty)BlockStainedGlassPane.COLOR));
        if (color == Gemstone.RUBY.dyeColor) {
            return Gemstone.RUBY;
        }
        if (color == Gemstone.AMETHYST.dyeColor) {
            return Gemstone.AMETHYST;
        }
        if (color == Gemstone.JADE.dyeColor) {
            return Gemstone.JADE;
        }
        if (color == Gemstone.SAPPHIRE.dyeColor) {
            return Gemstone.SAPPHIRE;
        }
        if (color == Gemstone.AMBER.dyeColor) {
            return Gemstone.AMBER;
        }
        if (color == Gemstone.TOPAZ.dyeColor) {
            return Gemstone.TOPAZ;
        }
        if (color == Gemstone.JASPER.dyeColor) {
            return Gemstone.JASPER;
        }
        if (color == Gemstone.OPAL.dyeColor) {
            return Gemstone.OPAL;
        }
        return null;
    }
    
    public <T> T firstNotNull(final T... args) {
        for (final T arg : args) {
            if (arg != null) {
                return arg;
            }
        }
        return null;
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
    
    enum Gemstone
    {
        RUBY(new Color(188, 3, 29), EnumDyeColor.RED), 
        AMETHYST(new Color(137, 0, 201), EnumDyeColor.PURPLE), 
        JADE(new Color(157, 249, 32), EnumDyeColor.LIME), 
        SAPPHIRE(new Color(60, 121, 224), EnumDyeColor.LIGHT_BLUE), 
        AMBER(new Color(237, 139, 35), EnumDyeColor.ORANGE), 
        TOPAZ(new Color(249, 215, 36), EnumDyeColor.YELLOW), 
        JASPER(new Color(214, 15, 150), EnumDyeColor.MAGENTA), 
        OPAL(new Color(245, 245, 240), EnumDyeColor.WHITE);
        
        public Color color;
        public EnumDyeColor dyeColor;
        
        private Gemstone(final Color color, final EnumDyeColor dyeColor) {
            this.color = color;
            this.dyeColor = dyeColor;
        }
    }
}
