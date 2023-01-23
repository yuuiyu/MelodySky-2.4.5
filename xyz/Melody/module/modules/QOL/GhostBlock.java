//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import net.minecraft.util.*;
import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import xyz.Melody.Event.*;
import net.minecraft.item.*;
import java.util.*;
import org.lwjgl.input.*;

public class GhostBlock extends Module
{
    private boolean shouldSet;
    public static ArrayList<BlockPos> blockposs;
    private TimerUtil timer;
    private Option<Boolean> pickaxe;
    
    public GhostBlock() {
        super("GhostBlock", new String[] { "gb" }, ModuleType.QOL);
        this.shouldSet = false;
        this.timer = new TimerUtil();
        this.pickaxe = (Option<Boolean>)new Option("rcPickaxe", (Object)true);
        this.addValues(new Value[] { (Value)this.pickaxe });
        this.setColor(new Color(244, 255, 149).getRGB());
        this.setModInfo("Create Ghost Block Where You are Looking.");
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        if (this.mc.objectMouseOver == null) {
            return;
        }
        if (this.mc.objectMouseOver.entityHit != null) {
            return;
        }
        if (Client.inDungeons && (this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() instanceof BlockChest || this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() instanceof BlockLever)) {
            return;
        }
        if (!this.shouldSet) {
            return;
        }
        if (!this.timer.hasReached(60.0)) {
            return;
        }
        if (this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() != Blocks.air.getDefaultState().getBlock()) {
            final BlockPos pos = this.mc.objectMouseOver.getBlockPos();
            this.mc.theWorld.setBlockToAir(pos);
            GhostBlock.blockposs.add(pos);
            this.timer.reset();
        }
        this.shouldSet = false;
    }
    
    @EventHandler
    private void tickBlock(final EventTick e) {
        if (this.mc.objectMouseOver == null) {
            return;
        }
        if (this.mc.objectMouseOver.entityHit == null && Mouse.isButtonDown(1)) {
            final BlockPos p = this.mc.objectMouseOver.getBlockPos();
            if (this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemPickaxe) {
                return;
            }
            for (final BlockPos pos : GhostBlock.blockposs) {
                if (Math.abs(p.getX() - pos.getX()) <= 1 && Math.abs(p.getY() - pos.getY()) <= 1) {
                    if (Math.abs(p.getZ() - pos.getZ()) > 1) {
                        continue;
                    }
                    GhostBlock.blockposs.remove(pos);
                    break;
                }
            }
        }
        for (int i = 0; i < GhostBlock.blockposs.size(); ++i) {
            this.mc.theWorld.setBlockToAir((BlockPos)GhostBlock.blockposs.get(i));
        }
    }
    
    @EventHandler
    private void tickUpdate(final EventTick e) {
        if (this.mc.objectMouseOver == null) {
            return;
        }
        if (this.mc.objectMouseOver.entityHit != null) {
            return;
        }
        if (this.mc.currentScreen != null) {
            return;
        }
        if (Client.inDungeons && this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() instanceof BlockChest) {
            return;
        }
        if (!this.timer.hasReached(50.0)) {
            return;
        }
        if ((boolean)this.pickaxe.getValue() && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemPickaxe && Mouse.isButtonDown(1)) {
            this.mc.theWorld.setBlockToAir(this.mc.objectMouseOver.getBlockPos());
            GhostBlock.blockposs.add(this.mc.objectMouseOver.getBlockPos());
            this.timer.reset();
        }
    }
    
    @EventHandler
    private void onKey(final EventTick event) {
        if (this.mc.currentScreen != null) {
            return;
        }
        if (this.getKey() == 0) {
            return;
        }
        if (!this.timer.hasReached(50.0)) {
            return;
        }
        if (Keyboard.isKeyDown(this.getKey())) {
            this.shouldSet = true;
        }
    }
    
    public void onDisable() {
        this.setEnabled(true);
        super.onDisable();
    }
    
    static {
        GhostBlock.blockposs = new ArrayList<BlockPos>();
    }
}
