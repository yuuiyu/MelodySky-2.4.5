//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Nether;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import xyz.Melody.Utils.*;
import java.util.*;
import net.minecraft.block.state.*;

public class SulphurESP extends Module
{
    private Numbers<Double> range;
    private Option<Boolean> debug;
    private ArrayList<BlockPos> blockPoss;
    private Thread thread;
    private int ticks;
    
    public SulphurESP() {
        super("SulphurESP", new String[] { "sulesp" }, ModuleType.Nether);
        this.range = (Numbers<Double>)new Numbers("Range", (Number)200.0, (Number)100.0, (Number)500.0, (Number)10.0);
        this.debug = (Option<Boolean>)new Option("Debug", (Object)false);
        this.blockPoss = new ArrayList<BlockPos>();
        this.ticks = 0;
        this.addValues(new Value[] { (Value)this.range, (Value)this.debug });
        this.setModInfo("Sulphur ESP (Crimson Island).");
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        this.blockPoss.clear();
    }
    
    public void onDisable() {
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
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crimson_Island) {
            this.thread = null;
            this.blockPoss.clear();
            return;
        }
        if (this.mc.theWorld != null && this.mc.thePlayer != null && this.thread == null) {
            (this.thread = new Thread(() -> {
                while (this.isEnabled()) {
                    if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crimson_Island) {
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
            }, "MelodySky-SulphurESP Thread")).start();
        }
        this.ticks = 0;
    }
    
    @EventHandler
    private void on3D(final EventRender3D event) {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crimson_Island) {
            return;
        }
        for (int i = 0; i < this.blockPoss.size(); ++i) {
            final BlockPos pos = this.blockPoss.get(i);
            final Color orange = new Color(Colors.YELLOW.c);
            final int c1 = new Color(orange.getRed(), orange.getGreen(), orange.getBlue(), 200).getRGB();
            RenderUtil.drawSolidBlockESP(pos, c1, event.getPartialTicks());
        }
    }
    
    @EventHandler
    private void onBlockChange(final BlockChangeEvent event) {
        if (this.blockPoss.contains(event.getPosition())) {
            this.blockPoss.remove(event.getPosition());
        }
    }
    
    private void updateBlocks() {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crimson_Island) {
            return;
        }
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        final Vec3i sub = new Vec3i((double)this.range.getValue(), (double)(int)(this.mc.thePlayer.posY - (this.mc.thePlayer.posY - 32.0)), (double)this.range.getValue());
        final Vec3i add = new Vec3i((double)this.range.getValue(), this.mc.thePlayer.posY - (this.mc.thePlayer.posY - 200.0), (double)this.range.getValue());
        for (final BlockPos blockPos : BlockPos.getAllInBox(this.mc.thePlayer.getPosition().add(add), this.mc.thePlayer.getPosition().subtract(sub))) {
            if (this.mc.theWorld == null) {
                break;
            }
            final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
            if (!Client.instance.getModuleManager().getModuleByClass(SulphurESP.class).isEnabled() || !this.thread.isAlive()) {
                this.blockPoss.clear();
                return;
            }
            if (this.mc.thePlayer.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()) > 250.0) {
                continue;
            }
            if (blockState.getBlock() != Blocks.sponge || this.blockPoss.contains(blockPos)) {
                continue;
            }
            if (this.debug.getValue()) {
                Helper.sendMessage(blockPos);
            }
            this.blockPoss.add(blockPos);
        }
    }
}
