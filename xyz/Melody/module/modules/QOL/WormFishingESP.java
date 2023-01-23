//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import net.minecraft.util.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.init.*;
import xyz.Melody.Utils.*;
import java.util.*;
import net.minecraft.block.state.*;

public class WormFishingESP extends Module
{
    private Option<Boolean> debug;
    private ArrayList<BlockPos> blockPoss;
    private Thread thread;
    private int ticks;
    
    public WormFishingESP() {
        super("WormFishingESP", new String[] { "wfe" }, ModuleType.QOL);
        this.debug = (Option<Boolean>)new Option("Debug", (Object)false);
        this.blockPoss = new ArrayList<BlockPos>();
        this.ticks = 0;
        this.setEnabled(false);
        this.addValues(new Value[] { (Value)this.debug });
        this.setModInfo("Worm Fishing Lava ESP.");
    }
    
    public void onDisable() {
        this.thread = null;
        this.blockPoss.clear();
        this.thread = null;
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
                while (this.isEnabled()) {
                    if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
                        break;
                    }
                    else if (this.mc.theWorld == null) {
                        break;
                    }
                    else {
                        this.updateBlocks();
                    }
                }
                return;
            }, "MelodySky-LavaFishingESP Thread")).start();
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
            if (this.mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()) > 250.0) {
                this.blockPoss.remove(pos);
                break;
            }
        }
        for (int i = 0; i < this.blockPoss.size(); ++i) {
            final BlockPos pos = this.blockPoss.get(i);
            final Color orange = new Color(Colors.ORANGE.c);
            final int c1 = new Color(orange.getRed(), orange.getGreen(), orange.getBlue(), 200).getRGB();
            if (this.mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()) >= 5.0) {
                RenderUtil.drawSolidBlockESP(pos, c1, event.getPartialTicks());
            }
        }
    }
    
    private void updateBlocks() {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        for (final BlockPos blockPos : BlockPos.getAllInBox(new BlockPos(473, 150, 473), new BlockPos(823, 64, 823))) {
            if (this.mc.theWorld == null) {
                break;
            }
            final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
            if (!Client.instance.getModuleManager().getModuleByClass(WormFishingESP.class).isEnabled() || !this.thread.isAlive()) {
                this.blockPoss.clear();
                return;
            }
            if (this.mc.thePlayer.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()) > 250.0) {
                continue;
            }
            if (blockState.getBlock() != Blocks.lava || this.blockPoss.contains(blockPos)) {
                continue;
            }
            if (this.debug.getValue()) {
                Helper.sendMessage(blockPos);
            }
            this.blockPoss.add(blockPos);
        }
    }
}
