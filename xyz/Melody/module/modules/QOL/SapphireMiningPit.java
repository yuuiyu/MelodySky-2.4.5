//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import xyz.Melody.GUI.Notification.*;
import java.util.*;
import net.minecraft.block.state.*;
import org.lwjgl.opengl.*;

public class SapphireMiningPit extends Module
{
    private Option<Boolean> debug;
    private ArrayList<BlockPos> blockPoss;
    private Thread thread;
    private Thread scanThread0;
    private Thread scanThread1;
    private Thread scanThread2;
    private Thread scanThread3;
    private int ticks;
    
    public SapphireMiningPit() {
        super("SapphirePitESP", new String[] { "spe" }, ModuleType.QOL);
        this.debug = (Option<Boolean>)new Option("Debug", (Object)false);
        this.blockPoss = new ArrayList<BlockPos>();
        this.ticks = 0;
        this.setEnabled(false);
        this.addValues(new Value[] { (Value)this.debug });
        this.setModInfo("Sapphire Mining Pit ESP.");
    }
    
    public void onDisable() {
        Helper.sendMessage("[SapphirePitESP] Scanning Abort.");
        this.thread = null;
        this.blockPoss.clear();
        super.onDisable();
    }
    
    @EventHandler
    private void onUpdate(final EventTick event) {
        if (this.ticks < 10) {
            ++this.ticks;
            return;
        }
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            this.thread = null;
            this.blockPoss.clear();
            return;
        }
        if (this.mc.theWorld != null && this.mc.thePlayer != null && this.thread == null) {
            (this.thread = new Thread(() -> {
                Helper.sendMessage("[SapphirePitESP] This May Take Some Time.");
                if (!this.isEnabled() || Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
                    return;
                }
                else if (this.mc.theWorld == null) {
                    return;
                }
                else {
                    (this.scanThread0 = new Thread(() -> {
                        while (this.mc.theWorld != null && this.isEnabled() && Client.instance.sbArea.getCurrentArea() == SkyblockArea.Areas.Crystal_Hollows) {
                            this.updateBlocks0();
                        }
                        return;
                    }, "MS-SE-Scan 0")).start();
                    (this.scanThread1 = new Thread(() -> {
                        while (this.mc.theWorld != null && this.isEnabled() && Client.instance.sbArea.getCurrentArea() == SkyblockArea.Areas.Crystal_Hollows) {
                            this.updateBlocks1();
                        }
                        return;
                    }, "MS-SE-Scan 1")).start();
                    (this.scanThread2 = new Thread(() -> {
                        while (this.mc.theWorld != null && this.isEnabled() && Client.instance.sbArea.getCurrentArea() == SkyblockArea.Areas.Crystal_Hollows) {
                            this.updateBlocks2();
                        }
                        return;
                    }, "MS-SE-Scan 2")).start();
                    (this.scanThread3 = new Thread(() -> {
                        while (this.mc.theWorld != null && this.isEnabled() && Client.instance.sbArea.getCurrentArea() == SkyblockArea.Areas.Crystal_Hollows) {
                            this.updateBlocks3();
                        }
                    }, "MS-SE-Scan 3")).start();
                    return;
                }
            }, "MelodySky-SapphireFinder Call")).start();
        }
        this.ticks = 0;
    }
    
    @EventHandler
    private void on3D(final EventRender3D event) {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        for (int i = 0; i < this.blockPoss.size(); ++i) {
            final BlockPos pos = this.blockPoss.get(i);
            final Color orange = new Color(Colors.BLUE.c);
            final int c1 = new Color(orange.getRed(), orange.getGreen(), orange.getBlue(), 200).getRGB();
            RenderUtil.drawSolidBlockESP(pos, c1, event.getPartialTicks());
            final double posX = pos.getX() - this.mc.getRenderManager().viewerPosX + 0.5;
            final double posY = pos.getY() - this.mc.getRenderManager().viewerPosY + 0.5;
            final double posZ = pos.getZ() - this.mc.getRenderManager().viewerPosZ + 0.5;
            RenderUtil.startDrawing();
            this.trace(pos, Colors.BLUE.c, posX, posY, posZ);
            RenderUtil.stopDrawing();
        }
    }
    
    private void updateBlocks0() {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        int dick = 0;
        for (final BlockPos blockPos : BlockPos.getAllInBox(new BlockPos(473, 31, 473), new BlockPos(648, 188, 648))) {
            if (this.mc.theWorld == null) {
                break;
            }
            if (!this.isEnabled()) {
                break;
            }
            final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
            if (!Client.instance.getModuleManager().getModuleByClass(SapphireMiningPit.class).isEnabled()) {
                this.blockPoss.clear();
                return;
            }
            ++dick;
            try {
                if (!this.mc.theWorld.isBlockLoaded(blockPos)) {
                    continue;
                }
                if (blockState.getBlock() != Blocks.double_stone_slab) {
                    continue;
                }
                final IBlockState bsup2 = this.mc.theWorld.getBlockState(blockPos.up(6));
                final IBlockState bsup3 = this.mc.theWorld.getBlockState(blockPos.up(2));
                final IBlockState bs = this.mc.theWorld.getBlockState(blockPos.up(1));
                final IBlockState bs2 = this.mc.theWorld.getBlockState(blockPos.down(1));
                final IBlockState bs3 = this.mc.theWorld.getBlockState(blockPos.down(2));
                if (bsup2.getBlock() == Blocks.stone_slab && bsup3.getBlock() == Blocks.double_stone_slab && bs.getBlock() == Blocks.stone_slab && bs3.getBlock() == Blocks.stone_brick_stairs && bs2.getBlock() == Blocks.double_stone_slab) {
                    if (this.debug.getValue()) {
                        Helper.sendMessage(blockPos + " " + EnumChatFormatting.GREEN + "TRUE");
                    }
                    if (this.blockPoss.contains(blockPos)) {
                        continue;
                    }
                    Helper.sendMessage("[SapphirePitESP] Sapphire Pit Found.");
                    Helper.sendMessage("[SapphirePitESP] Coords: " + EnumChatFormatting.GREEN + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ());
                    NotificationPublisher.queue("Sapphire Pit ESP", "Coords: " + EnumChatFormatting.GREEN + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ(), NotificationType.INFO, 7500);
                    this.blockPoss.add(blockPos);
                }
                else {
                    if (!(boolean)this.debug.getValue()) {
                        continue;
                    }
                    Helper.sendMessage(blockPos + " " + EnumChatFormatting.RED + "FALSE");
                    Helper.sendMessage(bs3.getBlock().getRegistryName());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.blockPoss.isEmpty() && (boolean)this.debug.getValue()) {
            Helper.sendMessage("[SapphirePitESP] Scanned " + dick + " Blocks.");
            Helper.sendMessage("[SapphirePitESP] " + this.blockPoss.size() + " Coords Possible.");
        }
    }
    
    private void updateBlocks1() {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        int dick = 0;
        for (final BlockPos blockPos : BlockPos.getAllInBox(new BlockPos(648, 31, 648), new BlockPos(823, 188, 823))) {
            if (this.mc.theWorld == null) {
                break;
            }
            if (!this.isEnabled()) {
                break;
            }
            final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
            if (!Client.instance.getModuleManager().getModuleByClass(SapphireMiningPit.class).isEnabled()) {
                this.blockPoss.clear();
                return;
            }
            ++dick;
            try {
                if (!this.mc.theWorld.isBlockLoaded(blockPos)) {
                    continue;
                }
                if (blockState.getBlock() != Blocks.double_stone_slab) {
                    continue;
                }
                final IBlockState bsup2 = this.mc.theWorld.getBlockState(blockPos.up(6));
                final IBlockState bsup3 = this.mc.theWorld.getBlockState(blockPos.up(2));
                final IBlockState bs = this.mc.theWorld.getBlockState(blockPos.up(1));
                final IBlockState bs2 = this.mc.theWorld.getBlockState(blockPos.down(1));
                final IBlockState bs3 = this.mc.theWorld.getBlockState(blockPos.down(2));
                if (bsup2.getBlock() == Blocks.stone_slab && bsup3.getBlock() == Blocks.double_stone_slab && bs.getBlock() == Blocks.stone_slab && bs3.getBlock() == Blocks.stone_brick_stairs && bs2.getBlock() == Blocks.double_stone_slab) {
                    if (this.debug.getValue()) {
                        Helper.sendMessage(blockPos + " " + EnumChatFormatting.GREEN + "TRUE");
                    }
                    if (this.blockPoss.contains(blockPos)) {
                        continue;
                    }
                    Helper.sendMessage("[SapphirePitESP] Sapphire Pit Found.");
                    Helper.sendMessage("[SapphirePitESP] Coords: " + EnumChatFormatting.GREEN + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ());
                    NotificationPublisher.queue("Sapphire Pit ESP", "Coords: " + EnumChatFormatting.GREEN + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ(), NotificationType.INFO, 7500);
                    this.blockPoss.add(blockPos);
                }
                else {
                    if (!(boolean)this.debug.getValue()) {
                        continue;
                    }
                    Helper.sendMessage(blockPos + " " + EnumChatFormatting.RED + "FALSE");
                    Helper.sendMessage(bs3.getBlock().getRegistryName());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.blockPoss.isEmpty() && (boolean)this.debug.getValue()) {
            Helper.sendMessage("[SapphirePitESP] Scanned " + dick + " Blocks.");
            Helper.sendMessage("[SapphirePitESP] " + this.blockPoss.size() + " Coords Possible.");
        }
    }
    
    private void updateBlocks2() {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        int dick = 0;
        for (final BlockPos blockPos : BlockPos.getAllInBox(new BlockPos(473, 31, 823), new BlockPos(648, 188, 648))) {
            if (this.mc.theWorld == null) {
                break;
            }
            if (!this.isEnabled()) {
                break;
            }
            final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
            if (!Client.instance.getModuleManager().getModuleByClass(SapphireMiningPit.class).isEnabled()) {
                this.blockPoss.clear();
                return;
            }
            ++dick;
            try {
                if (!this.mc.theWorld.isBlockLoaded(blockPos)) {
                    continue;
                }
                if (blockState.getBlock() != Blocks.double_stone_slab) {
                    continue;
                }
                final IBlockState bsup2 = this.mc.theWorld.getBlockState(blockPos.up(6));
                final IBlockState bsup3 = this.mc.theWorld.getBlockState(blockPos.up(2));
                final IBlockState bs = this.mc.theWorld.getBlockState(blockPos.up(1));
                final IBlockState bs2 = this.mc.theWorld.getBlockState(blockPos.down(1));
                final IBlockState bs3 = this.mc.theWorld.getBlockState(blockPos.down(2));
                if (bsup2.getBlock() == Blocks.stone_slab && bsup3.getBlock() == Blocks.double_stone_slab && bs.getBlock() == Blocks.stone_slab && bs3.getBlock() == Blocks.stone_brick_stairs && bs2.getBlock() == Blocks.double_stone_slab) {
                    if (this.debug.getValue()) {
                        Helper.sendMessage(blockPos + " " + EnumChatFormatting.GREEN + "TRUE");
                    }
                    if (this.blockPoss.contains(blockPos)) {
                        continue;
                    }
                    Helper.sendMessage("[SapphirePitESP] Sapphire Pit Found.");
                    Helper.sendMessage("[SapphirePitESP] Coords: " + EnumChatFormatting.GREEN + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ());
                    NotificationPublisher.queue("Sapphire Pit ESP", "Coords: " + EnumChatFormatting.GREEN + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ(), NotificationType.INFO, 7500);
                    this.blockPoss.add(blockPos);
                }
                else {
                    if (!(boolean)this.debug.getValue()) {
                        continue;
                    }
                    Helper.sendMessage(blockPos + " " + EnumChatFormatting.RED + "FALSE");
                    Helper.sendMessage(bs3.getBlock().getRegistryName());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.blockPoss.isEmpty() && (boolean)this.debug.getValue()) {
            Helper.sendMessage("[SapphirePitESP] Scanned " + dick + " Blocks.");
            Helper.sendMessage("[SapphirePitESP] " + this.blockPoss.size() + " Coords Possible.");
        }
    }
    
    private void updateBlocks3() {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        int dick = 0;
        for (final BlockPos blockPos : BlockPos.getAllInBox(new BlockPos(648, 31, 648), new BlockPos(823, 188, 473))) {
            if (this.mc.theWorld == null) {
                break;
            }
            if (!this.isEnabled()) {
                break;
            }
            final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
            if (!Client.instance.getModuleManager().getModuleByClass(SapphireMiningPit.class).isEnabled()) {
                this.blockPoss.clear();
                return;
            }
            ++dick;
            try {
                if (!this.mc.theWorld.isBlockLoaded(blockPos)) {
                    continue;
                }
                if (blockState.getBlock() != Blocks.double_stone_slab) {
                    continue;
                }
                final IBlockState bsup2 = this.mc.theWorld.getBlockState(blockPos.up(6));
                final IBlockState bsup3 = this.mc.theWorld.getBlockState(blockPos.up(2));
                final IBlockState bs = this.mc.theWorld.getBlockState(blockPos.up(1));
                final IBlockState bs2 = this.mc.theWorld.getBlockState(blockPos.down(1));
                final IBlockState bs3 = this.mc.theWorld.getBlockState(blockPos.down(2));
                if (bsup2.getBlock() == Blocks.stone_slab && bsup3.getBlock() == Blocks.double_stone_slab && bs.getBlock() == Blocks.stone_slab && bs3.getBlock() == Blocks.stone_brick_stairs && bs2.getBlock() == Blocks.double_stone_slab) {
                    if (this.debug.getValue()) {
                        Helper.sendMessage(blockPos + " " + EnumChatFormatting.GREEN + "TRUE");
                    }
                    if (this.blockPoss.contains(blockPos)) {
                        continue;
                    }
                    Helper.sendMessage("[SapphirePitESP] Sapphire Pit Found.");
                    Helper.sendMessage("[SapphirePitESP] Coords: " + EnumChatFormatting.GREEN + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ());
                    NotificationPublisher.queue("Sapphire Pit ESP", "Coords: " + EnumChatFormatting.GREEN + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ(), NotificationType.INFO, 7500);
                    this.blockPoss.add(blockPos);
                }
                else {
                    if (!(boolean)this.debug.getValue()) {
                        continue;
                    }
                    Helper.sendMessage(blockPos + " " + EnumChatFormatting.RED + "FALSE");
                    Helper.sendMessage(bs3.getBlock().getRegistryName());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.blockPoss.isEmpty() && (boolean)this.debug.getValue()) {
            Helper.sendMessage("[SapphirePitESP] Scanned " + dick + " Blocks.");
            Helper.sendMessage("[SapphirePitESP] " + this.blockPoss.size() + " Coords Possible.");
        }
    }
    
    private void trace(final BlockPos pos, final int color, final double x, final double y, final double z) {
        GL11.glEnable(2848);
        RenderUtil.setColor(color);
        GL11.glLineWidth(3.0f);
        GL11.glBegin(1);
        GL11.glVertex3d(0.0, (double)this.mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(2848);
    }
}
