//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import xyz.Melody.*;
import xyz.Melody.injection.mixins.packets.*;
import net.minecraft.network.play.server.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.Item.*;
import org.lwjgl.input.*;

public class AntiVelocity extends Module
{
    private TimerUtil timer;
    private Mode<Enum> mode;
    private Option<Boolean> autoMode;
    private Numbers<Double> explosionTime;
    private Option<Boolean> sbonly;
    private Option<Boolean> dungeonLava;
    private Option<Boolean> jerryChineVC;
    
    public AntiVelocity() {
        super("AntiKB", new String[] { "antivelocity", "antiknockback", "antikb" }, ModuleType.Balance);
        this.timer = new TimerUtil();
        this.mode = (Mode<Enum>)new Mode("Mode", (Enum[])idk.values(), (Enum)idk.Turtle_Shelmet);
        this.autoMode = (Option<Boolean>)new Option("Auto Switch Mode", (Object)true);
        this.explosionTime = (Numbers<Double>)new Numbers("WaitingTime", (Number)500.0, (Number)0.0, (Number)1000.0, (Number)10.0);
        this.sbonly = (Option<Boolean>)new Option("Skyblock Only", (Object)true);
        this.dungeonLava = (Option<Boolean>)new Option("Dungeon Lava Check", (Object)true);
        this.jerryChineVC = (Option<Boolean>)new Option("JerryChine Vertical", (Object)true);
        this.addValues((Value)this.mode, (Value)this.autoMode, (Value)this.sbonly, (Value)this.dungeonLava, (Value)this.jerryChineVC, (Value)this.explosionTime);
        this.setColor(new Color(191, 191, 191).getRGB());
    }
    
    @EventHandler
    private void onPacket(final EventPacketRecieve e) {
        if (this.mc.theWorld == null || this.mc.thePlayer == null) {
            return;
        }
        if ((boolean)this.sbonly.getValue() && !Client.inSkyblock) {
            return;
        }
        final boolean ignExplosion = !this.timer.hasReached((double)this.explosionTime.getValue()) || (this.mc.thePlayer.isInLava() && (Client.inDungeons || !(boolean)this.dungeonLava.getValue()));
        if (ignExplosion) {
            if (e.getPacket() instanceof S12PacketEntityVelocity) {
                final S12PacketEntityVelocity velocity = (S12PacketEntityVelocity)e.getPacket();
                if (velocity.getEntityID() == this.mc.thePlayer.getEntityId() && (boolean)this.jerryChineVC.getValue() && this.holdingJC()) {
                    final S12PacketEntityVelocity vp = (S12PacketEntityVelocity)e.getPacket();
                    ((S12Accessor)vp).setMotionX(0);
                    ((S12Accessor)vp).setMotionZ(0);
                }
            }
            return;
        }
        if (e.getPacket() instanceof S27PacketExplosion) {
            e.setCancelled(true);
        }
        if (e.getPacket() instanceof S12PacketEntityVelocity) {
            final S12PacketEntityVelocity vp2 = (S12PacketEntityVelocity)e.getPacket();
            if (vp2.getEntityID() == this.mc.thePlayer.getEntityId()) {
                if (this.mode.getValue() == idk.Reverse) {
                    ((S12Accessor)vp2).setMotionX(-vp2.getMotionX());
                    ((S12Accessor)vp2).setMotionZ(-vp2.getMotionZ());
                    return;
                }
                if (this.autoMode.getValue()) {
                    if (Client.inDungeons) {
                        ((S12Accessor)vp2).setMotionX(1);
                        ((S12Accessor)vp2).setMotionZ(1);
                    }
                    else {
                        e.setCancelled(true);
                    }
                }
                else {
                    if (this.mode.getValue() == idk.Cancel) {
                        e.setCancelled(true);
                    }
                    if (this.mode.getValue() == idk.Turtle_Shelmet) {
                        ((S12Accessor)vp2).setMotionX(1);
                        ((S12Accessor)vp2).setMotionZ(1);
                    }
                }
            }
        }
    }
    
    @EventHandler
    private void onRC(final EventTick event) {
        final String heldItemID = (this.mc.thePlayer.getHeldItem() != null) ? ItemUtils.getSkyBlockID(this.mc.thePlayer.getHeldItem()) : "notHoldingItem";
        final boolean holdingStaff = heldItemID.contains("BONZO_STAFF") || heldItemID.equals("JERRY_STAFF");
        if ((double)this.explosionTime.getValue() == 0.0) {
            this.timer.reset();
            return;
        }
        if (holdingStaff && Mouse.isButtonDown(1)) {
            this.timer.reset();
        }
    }
    
    private boolean holdingJC() {
        final String heldItemID = (this.mc.thePlayer.getHeldItem() != null) ? ItemUtils.getSkyBlockID(this.mc.thePlayer.getHeldItem()) : "notHoldingItem";
        return heldItemID.equals("JERRY_STAFF");
    }
    
    enum idk
    {
        Cancel, 
        Turtle_Shelmet, 
        Reverse;
    }
}
