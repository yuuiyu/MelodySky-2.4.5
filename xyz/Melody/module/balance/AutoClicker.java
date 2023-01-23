//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.init.*;
import org.lwjgl.input.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import xyz.Melody.Event.*;
import xyz.Melody.*;

public class AutoClicker extends Module
{
    private TimerUtil timer;
    private Option<Boolean> left;
    private Option<Boolean> right;
    private Numbers<Double> lcps;
    private Numbers<Double> rcps;
    
    public AutoClicker() {
        super("AutoClicker", new String[] { "ac" }, ModuleType.Balance);
        this.timer = new TimerUtil();
        this.left = (Option<Boolean>)new Option("LMB", (Object)true);
        this.right = (Option<Boolean>)new Option("RMB", (Object)false);
        this.lcps = (Numbers<Double>)new Numbers("LCps", (Number)14.0, (Number)1.0, (Number)20.0, (Number)0.1);
        this.rcps = (Numbers<Double>)new Numbers("RCps", (Number)14.0, (Number)1.0, (Number)20.0, (Number)0.1);
        this.addValues((Value)this.left, (Value)this.right, (Value)this.lcps, (Value)this.rcps);
        this.setColor(new Color(244, 255, 149).getRGB());
    }
    
    @Override
    public void onEnable() {
        this.timer.reset();
    }
    
    @EventHandler
    private void onLMB(final EventTick e) {
        if (this.mc.objectMouseOver == null) {
            return;
        }
        if (this.mc.objectMouseOver.entityHit == null && this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() != Blocks.air.getDefaultState().getBlock() && this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() != Blocks.flowing_water.getDefaultState().getBlock() && this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() != Blocks.water.getDefaultState().getBlock() && this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() != Blocks.flowing_lava.getDefaultState().getBlock() && this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() != Blocks.lava.getDefaultState().getBlock()) {
            return;
        }
        final float ldelay = 1000.0f / ((Double)this.lcps.getValue()).floatValue();
        if (Mouse.isButtonDown(0) && this.timer.delay(ldelay) && this.mc.currentScreen == null && (boolean)this.left.getValue()) {
            this.mc.thePlayer.swingItem();
            if (this.mc.objectMouseOver.entityHit != null) {
                this.mc.getNetHandler().addToSendQueue((Packet)new C02PacketUseEntity(this.mc.objectMouseOver.entityHit, C02PacketUseEntity.Action.ATTACK));
            }
            this.timer.reset();
        }
    }
    
    @EventHandler
    private void onRMB(final EventTick event) {
        final float rdelay = 1000.0f / ((Double)this.rcps.getValue()).floatValue();
        if (Mouse.isButtonDown(1) && this.timer.delay(rdelay) && this.mc.currentScreen == null && (boolean)this.right.getValue()) {
            Client.rightClick();
            this.timer.reset();
        }
    }
    
    @Override
    public void onDisable() {
        this.timer.reset();
    }
}
