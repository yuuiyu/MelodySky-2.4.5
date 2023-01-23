//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.event.world.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiAFK extends Module
{
    private Numbers<Double> delay;
    private Option<Boolean> move;
    private Option<Boolean> rot;
    private TimerUtil timer;
    private boolean shouldMove;
    private boolean shouldRotate;
    private boolean rotated;
    private TimerUtil rotationTimer;
    private TimerUtil moveTimer;
    private boolean moveDone;
    private boolean moved;
    private boolean moveBack;
    
    public AntiAFK() {
        super("AntiAFK", new String[] { "cc", "ccm", "command" }, ModuleType.Others);
        this.delay = (Numbers<Double>)new Numbers("Delay", (Number)5000.0, (Number)1000.0, (Number)20000.0, (Number)500.0);
        this.move = (Option<Boolean>)new Option("Move", (Object)true);
        this.rot = (Option<Boolean>)new Option("Rotation", (Object)true);
        this.timer = new TimerUtil();
        this.shouldMove = false;
        this.shouldRotate = false;
        this.rotated = false;
        this.rotationTimer = new TimerUtil();
        this.moveTimer = new TimerUtil();
        this.moveDone = false;
        this.moved = false;
        this.moveBack = false;
        this.addValues(new Value[] { (Value)this.delay, (Value)this.move, (Value)this.rot });
        this.setModInfo("Anti Moving or Rotation AFK.");
    }
    
    @EventHandler
    private void tickTimer(final EventTick event) {
        this.mc.thePlayer.setSneaking(true);
        if (this.timer.hasReached((double)this.delay.getValue())) {
            this.shouldMove = (boolean)this.move.getValue();
            this.shouldRotate = (boolean)this.rot.getValue();
            this.timer.reset();
        }
    }
    
    @EventHandler
    private void tickRotation(final EventTick event) {
        if (this.shouldRotate) {
            if (!this.rotated) {
                final EntityPlayerSP thePlayer = this.mc.thePlayer;
                thePlayer.rotationYaw += 2.0f;
                this.rotated = true;
            }
            if (this.rotated && this.rotationTimer.hasReached(250.0)) {
                final EntityPlayerSP thePlayer2 = this.mc.thePlayer;
                thePlayer2.rotationYaw -= 2.0f;
                this.rotated = false;
                this.shouldRotate = false;
                this.rotationTimer.reset();
            }
        }
        else {
            this.rotationTimer.reset();
        }
    }
    
    @EventHandler
    private void tickMove(final EventTick event) {
        if (this.shouldMove) {
            final int firstStep = this.mc.gameSettings.keyBindLeft.getKeyCode();
            final int secondStep = this.mc.gameSettings.keyBindRight.getKeyCode();
            if (!this.moveDone) {
                if (!this.moved) {
                    this.moveTimer.reset();
                    KeyBinding.setKeyBindState(firstStep, true);
                    this.moved = true;
                }
                if (this.moved && this.moveTimer.hasReached(50.0)) {
                    KeyBinding.setKeyBindState(firstStep, false);
                    if (this.moveTimer.hasReached(100.0)) {
                        KeyBinding.setKeyBindState(secondStep, true);
                        if (this.moveTimer.hasReached(150.0)) {
                            KeyBinding.setKeyBindState(secondStep, false);
                            this.moveDone = true;
                        }
                    }
                }
            }
            else {
                this.moved = false;
                this.moveTimer.reset();
                this.moveDone = false;
                KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindLeft.getKeyCode(), false);
                KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindRight.getKeyCode(), false);
                this.shouldMove = false;
            }
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[Ant1AFK] Auto Disabled due to World Change.");
        this.setEnabled(false);
    }
}
