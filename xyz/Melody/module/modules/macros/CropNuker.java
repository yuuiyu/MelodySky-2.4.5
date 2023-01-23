//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.macros;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.render.*;
import net.minecraftforge.event.world.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.block.state.*;

public class CropNuker extends Module
{
    private BlockPos crop;
    private final ArrayList<BlockPos> broken;
    private TimerUtil timer;
    private Mode<Enum> mode;
    private Numbers<Double> range;
    
    public CropNuker() {
        super("CropNuker", new String[] { "gn" }, ModuleType.Macros);
        this.crop = null;
        this.broken = new ArrayList<BlockPos>();
        this.timer = new TimerUtil();
        this.mode = (Mode<Enum>)new Mode("Mode", (Enum[])crops.values(), (Enum)crops.Cane);
        this.range = (Numbers<Double>)new Numbers("Range", (Number)5.0, (Number)1.0, (Number)6.0, (Number)0.1);
        this.addValues(new Value[] { (Value)this.mode, (Value)this.range });
        this.setModInfo("Auto Break Crops Around You.");
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.crop = null;
        this.broken.clear();
        super.onDisable();
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        if (this.mc.thePlayer == null) {
            this.broken.clear();
            return;
        }
        if (this.timer.hasReached(30000.0)) {
            this.broken.clear();
            this.timer.reset();
        }
        this.crop = this.closestCrop();
        if (this.crop != null) {
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, this.crop, EnumFacing.DOWN));
            this.mc.thePlayer.swingItem();
            this.broken.add(this.crop);
        }
    }
    
    @EventHandler
    public void onTick(final EventRender3D event) {
        if (this.crop != null) {
            RenderUtil.drawSolidBlockESP(this.crop, Colors.MAGENTA.c, event.getPartialTicks());
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[MacroProtection] Auto Disabled " + EnumChatFormatting.GREEN + this.getName() + EnumChatFormatting.GRAY + " due to World Change.");
        this.setEnabled(false);
    }
    
    private BlockPos closestCrop() {
        if (this.mc.theWorld == null) {
            return null;
        }
        final double r = (double)this.range.getValue();
        BlockPos playerPos = this.mc.thePlayer.getPosition();
        playerPos = playerPos.add(0, 1, 0);
        final Vec3 playerVec = this.mc.thePlayer.getPositionVector();
        final Vec3i vec3i = new Vec3i(r, 2.0, r);
        final Vec3i vec3iCane = new Vec3i(r, 0.0, r);
        final ArrayList<Vec3> warts = new ArrayList<Vec3>();
        if (playerPos != null) {
            final String lowerCase = ((Enum)this.mode.getValue()).toString().toLowerCase();
            switch (lowerCase) {
                case "all": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if ((blockState.getBlock() == Blocks.nether_wart || blockState.getBlock() == Blocks.potatoes || blockState.getBlock() == Blocks.wheat || blockState.getBlock() == Blocks.carrots || blockState.getBlock() == Blocks.pumpkin || blockState.getBlock() == Blocks.melon_block || blockState.getBlock() == Blocks.brown_mushroom || blockState.getBlock() == Blocks.red_mushroom || blockState.getBlock() == Blocks.cocoa || blockState.getBlock() == Blocks.cactus || blockState.getBlock() == Blocks.reeds) && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "cane": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3iCane), playerPos.subtract(vec3iCane))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.reeds && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "cactus": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3iCane), playerPos.subtract(vec3iCane))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.cactus && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "netherwart": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.nether_wart && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "wheat": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.wheat && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "carrot": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.carrots && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "potato": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.potatoes && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "pumpkin": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.pumpkin && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "melon": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.melon_block && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "mushroom": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if ((blockState.getBlock() == Blocks.brown_mushroom || blockState.getBlock() == Blocks.red_mushroom) && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
                case "cocoa": {
                    for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                        final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                        if (blockState.getBlock() == Blocks.cocoa && !this.broken.contains(blockPos)) {
                            warts.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                        }
                    }
                    break;
                }
            }
        }
        double smallest = 9999.0;
        Vec3 closest = null;
        for (final Vec3 wart : warts) {
            final double dist = wart.distanceTo(playerVec);
            if (dist < smallest) {
                smallest = dist;
                closest = wart;
            }
        }
        if (closest != null && smallest < 5.0) {
            return new BlockPos(closest.xCoord, closest.yCoord, closest.zCoord);
        }
        return null;
    }
    
    private enum crops
    {
        ALL, 
        Cane, 
        Cactus, 
        NetherWart, 
        Wheat, 
        Carrot, 
        Potato, 
        Pumpkin, 
        Melon, 
        Mushroom, 
        Cocoa;
    }
}
