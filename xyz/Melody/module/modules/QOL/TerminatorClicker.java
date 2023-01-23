//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.Item.*;
import xyz.Melody.Utils.math.*;
import org.lwjgl.input.*;
import xyz.Melody.*;
import xyz.Melody.Event.*;

public class TerminatorClicker extends Module
{
    private TimerUtil timer;
    private Option<Boolean> juju;
    private Numbers<Double> cps;
    
    public TerminatorClicker() {
        super("TerminatorClicker", new String[] { "tc" }, ModuleType.QOL);
        this.timer = new TimerUtil();
        this.juju = (Option<Boolean>)new Option("Juju", (Object)false);
        this.cps = (Numbers<Double>)new Numbers("CPS", (Number)14.0, (Number)10.0, (Number)20.0, (Number)0.1);
        this.addValues(new Value[] { (Value)this.juju, (Value)this.cps });
        this.setColor(new Color(191, 191, 191).getRGB());
        this.setModInfo("Auto Right Click Terminator.");
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (this.mc.thePlayer.getHeldItem() == null) {
            return;
        }
        final String itemID = ItemUtils.getSkyBlockID(this.mc.thePlayer.getHeldItem());
        if (itemID.equals("TERMINATOR") || ((boolean)this.juju.getValue() && itemID.equals("JUJU_SHORTBOW"))) {
            final float rdelay = (float)(1000.0 / (((Double)this.cps.getValue()).floatValue() + MathUtil.randomDouble(-2.0, 2.0)));
            if (Mouse.isButtonDown(1) && this.timer.hasReached(rdelay) && this.mc.currentScreen == null) {
                Client.rightClick();
                this.timer.reset();
            }
        }
    }
}
