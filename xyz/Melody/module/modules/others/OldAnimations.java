//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.block.*;
import net.minecraft.potion.*;
import xyz.Melody.Event.*;

public class OldAnimations extends Module
{
    public Option<Boolean> oldRod;
    public Option<Boolean> oldBow;
    public Option<Boolean> oldEating;
    public Option<Boolean> oldModel;
    public Option<Boolean> punching;
    public Option<Boolean> oldBlockhitting;
    public Numbers<Double> handX;
    public Numbers<Double> handY;
    public Numbers<Double> handZ;
    
    public OldAnimations() {
        super("OldAnimations", new String[] { "alc", "asc", "lobby" }, ModuleType.Others);
        this.oldRod = (Option<Boolean>)new Option("Rod", (Object)true);
        this.oldBow = (Option<Boolean>)new Option("Bow", (Object)true);
        this.oldEating = (Option<Boolean>)new Option("Eating", (Object)true);
        this.oldModel = (Option<Boolean>)new Option("Model", (Object)true);
        this.punching = (Option<Boolean>)new Option("Punching", (Object)true);
        this.oldBlockhitting = (Option<Boolean>)new Option("BlockHit", (Object)true);
        this.handX = (Numbers<Double>)new Numbers("HandX", (Number)0.0, (Number)(-1.0), (Number)1.0, (Number)0.1);
        this.handY = (Numbers<Double>)new Numbers("HandY", (Number)0.0, (Number)(-1.0), (Number)1.0, (Number)0.1);
        this.handZ = (Numbers<Double>)new Numbers("HandZ", (Number)0.0, (Number)(-1.0), (Number)1.0, (Number)0.1);
        this.addValues(new Value[] { (Value)this.oldBlockhitting, (Value)this.punching, (Value)this.oldModel, (Value)this.oldEating, (Value)this.oldBow, (Value)this.oldRod, (Value)this.handX, (Value)this.handY, (Value)this.handZ });
        this.setModInfo("1.7 Animations");
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (this.mc.objectMouseOver == null) {
            return;
        }
        if (this.mc.objectMouseOver.getBlockPos() == null) {
            return;
        }
        if ((this.mc.thePlayer.isBlocking() || this.mc.thePlayer.isUsingItem()) && !(this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() instanceof BlockAir)) {
            final int idk = this.mc.thePlayer.isPotionActive(Potion.digSpeed) ? (6 - (1 + this.mc.thePlayer.getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1) : (this.mc.thePlayer.isPotionActive(Potion.digSlowdown) ? (6 + (1 + this.mc.thePlayer.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2) : 6);
            if (this.mc.gameSettings.keyBindAttack.isKeyDown() && (!this.mc.thePlayer.isSwingInProgress || this.mc.thePlayer.swingProgressInt >= idk / 2 || this.mc.thePlayer.swingProgressInt < 0)) {
                this.mc.thePlayer.swingProgressInt = -1;
                this.mc.thePlayer.isSwingInProgress = true;
            }
        }
    }
}
