//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.macros;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.render.*;
import java.util.*;

public class GlowingMushroomNuker extends Module
{
    private Numbers<Double> range;
    private ArrayList<BlockPos> mushrooms;
    private BlockPos clothestMushroom;
    
    public GlowingMushroomNuker() {
        super("GlowingMushroomNuker", new String[] { "chest" }, ModuleType.Macros);
        this.range = (Numbers<Double>)new Numbers("Range", (Number)5.0, (Number)1.0, (Number)6.0, (Number)0.1);
        this.mushrooms = new ArrayList<BlockPos>();
        this.addValues(new Value[] { (Value)this.range });
        this.setModInfo("Auto Mine Glowing Mushrooms.");
    }
    
    @EventHandler
    private void onRotation(final EventTick event) {
        this.updateClothest();
        if (this.clothestMushroom != null) {
            final MovingObjectPosition fake = this.mc.objectMouseOver;
            fake.hitVec = new Vec3((Vec3i)this.clothestMushroom);
            final EnumFacing enumFacing = fake.sideHit;
            if (enumFacing != null && this.mc.thePlayer != null) {
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, this.clothestMushroom, enumFacing));
            }
            this.mc.thePlayer.swingItem();
            this.mushrooms.remove(this.clothestMushroom);
            this.clothestMushroom = null;
        }
    }
    
    @EventHandler
    private void onBlockDestory(final BlockChangeEvent event) {
        if (this.mushrooms.contains(event.getPosition())) {
            this.mushrooms.remove(event.getPosition());
        }
    }
    
    @EventHandler
    public void receivePacket(final EventPacketRecieve event) {
        if (event.getPacket() instanceof S2APacketParticles) {
            final S2APacketParticles packet = (S2APacketParticles)event.getPacket();
            if (packet.getParticleType() == EnumParticleTypes.SPELL_MOB) {
                final Vec3 particlePos = new Vec3(packet.getXCoordinate(), packet.getYCoordinate(), packet.getZCoordinate());
                final BlockPos b = new BlockPos(particlePos);
                if ((this.mc.theWorld.getBlockState(b).getBlock() == Blocks.red_mushroom || this.mc.theWorld.getBlockState(b).getBlock() == Blocks.brown_mushroom) && !this.mushrooms.contains(b)) {
                    this.mushrooms.add(b);
                }
            }
        }
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.mushrooms.clear();
        super.onDisable();
    }
    
    @EventHandler
    public void onR3D(final EventRender3D event) {
        for (final BlockPos pos : this.mushrooms) {
            RenderUtil.drawSolidBlockESP(pos, Colors.MAGENTA.c, event.getPartialTicks());
        }
        if (this.clothestMushroom != null) {
            RenderUtil.drawSolidBlockESP(this.clothestMushroom, Colors.GREEN.c, event.getPartialTicks());
        }
    }
    
    private void updateClothest() {
        if (this.mc.thePlayer == null || this.mc.theWorld == null || this.mushrooms.isEmpty()) {
            return;
        }
        this.mushrooms.sort(Comparator.comparingDouble(pos -> this.mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ())));
        final BlockPos pos2 = this.mushrooms.get(0);
        if (this.mc.thePlayer.getDistance((double)pos2.getX(), (double)pos2.getY(), (double)pos2.getZ()) < (double)this.range.getValue()) {
            this.clothestMushroom = pos2;
            return;
        }
        this.clothestMushroom = null;
    }
}
