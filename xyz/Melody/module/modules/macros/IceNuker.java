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
import xyz.Melody.module.modules.QOL.*;
import xyz.Melody.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.block.state.*;
import net.minecraftforge.event.world.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class IceNuker extends Module
{
    private ArrayList<BlockPos> broken;
    private BlockPos closestIce;
    private int ticks;
    private TimerUtil timer;
    private Numbers<Double> range;
    private Numbers<Double> depth;
    private Numbers<Double> bps;
    private Option<Boolean> fto;
    
    public IceNuker() {
        super("IceNuker", new String[] { "" }, ModuleType.Macros);
        this.broken = new ArrayList<BlockPos>();
        this.ticks = 0;
        this.timer = new TimerUtil();
        this.range = (Numbers<Double>)new Numbers("Range", (Number)3.8, (Number)2.0, (Number)6.0, (Number)0.1);
        this.depth = (Numbers<Double>)new Numbers("Depth", (Number)1.0, (Number)0.0, (Number)4.0, (Number)0.5);
        this.bps = (Numbers<Double>)new Numbers("Block Per Sec.", (Number)10.0, (Number)1.0, (Number)50.0, (Number)1.0);
        this.fto = (Option<Boolean>)new Option("Treasure Only", (Object)false);
        this.addValues(new Value[] { (Value)this.bps, (Value)this.depth, (Value)this.range, (Value)this.fto });
        this.setModInfo("Auto Fuck Ice");
    }
    
    public void onDisable() {
        this.broken.clear();
        super.onDisable();
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        ++this.ticks;
        if (this.broken.size() > 10) {
            this.broken.clear();
        }
        if (this.ticks > 20) {
            this.broken.clear();
            this.ticks = 0;
        }
        if (!this.timer.hasReached(1000 / ((Double)this.bps.getValue()).intValue())) {
            return;
        }
        this.closestIce = this.closestIce();
        if (this.closestIce != null) {
            final MovingObjectPosition fake = this.mc.objectMouseOver;
            fake.hitVec = new Vec3((Vec3i)this.closestIce);
            final EnumFacing enumFacing = fake.sideHit;
            if (enumFacing != null && this.mc.thePlayer != null) {
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, this.closestIce, enumFacing));
            }
            this.mc.thePlayer.swingItem();
            this.broken.add(this.closestIce);
        }
        this.timer.reset();
    }
    
    @EventHandler
    public void renderWorld(final EventRender3D event) {
        if (this.closestIce != null) {
            RenderUtil.drawSolidBlockESP(this.closestIce, Colors.MAGENTA.c, 3.5f, event.getPartialTicks());
        }
    }
    
    private BlockPos closestIce() {
        if (this.mc.theWorld == null || this.mc.thePlayer == null) {
            return null;
        }
        if (this.fto.getValue()) {
            final ArrayList<BlockPos> ices = ((FrozenTreasureESP)Client.instance.getModuleManager().getModuleByClass(FrozenTreasureESP.class)).ices;
            ices.removeIf(pos -> this.mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()) > (double)this.range.getValue());
            ices.sort(Comparator.comparingDouble(pos -> this.mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ())));
            if (!ices.isEmpty()) {
                return ices.get(0);
            }
            return null;
        }
        else {
            final float r = ((Double)this.range.getValue()).floatValue();
            final BlockPos playerPos = this.mc.thePlayer.getPosition().add(0, 1, 0);
            final Vec3i vec3i = new Vec3i((double)r, (double)r, (double)r);
            final Vec3i depth = new Vec3i((double)r, (double)this.depth.getValue(), (double)r);
            final ArrayList<Vec3> stones = new ArrayList<Vec3>();
            if (playerPos != null) {
                for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(depth))) {
                    final IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
                    if (blockState.getBlock() == Blocks.ice && !this.broken.contains(blockPos)) {
                        stones.add(new Vec3(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5));
                    }
                }
            }
            stones.sort(Comparator.comparingDouble(vec -> this.mc.thePlayer.getDistance(vec.xCoord, vec.yCoord, vec.zCoord)));
            if (!stones.isEmpty()) {
                return new BlockPos(stones.get(0).xCoord, stones.get(0).yCoord, stones.get(0).zCoord);
            }
            return null;
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[MacroProtection] Auto Disabled " + EnumChatFormatting.GREEN + this.getName() + EnumChatFormatting.GRAY + " due to World Change.");
        KeyBinding.unPressAllKeys();
        this.setEnabled(false);
    }
}
