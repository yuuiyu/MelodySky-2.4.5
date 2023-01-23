//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Swappings;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import org.lwjgl.input.*;
import xyz.Melody.Event.*;
import xyz.Melody.Utils.Item.*;
import xyz.Melody.Event.events.misc.*;

public class SoulWhip extends Module
{
    private boolean shouldSwitch;
    private TimerUtil timer;
    private Mode<Enum> mode;
    private Option<Boolean> mouse;
    private Option<Boolean> both;
    
    public SoulWhip() {
        super("SoulWhip", new String[] { "aots" }, ModuleType.Swapping);
        this.timer = new TimerUtil();
        this.mode = (Mode<Enum>)new Mode("Mouse", (Enum[])ddd.values(), (Enum)ddd.Right);
        this.mouse = (Option<Boolean>)new Option("MouseClick", (Object)false);
        this.both = (Option<Boolean>)new Option("Both", (Object)false);
        this.addValues(new Value[] { (Value)this.mode, (Value)this.mouse, (Value)this.both });
        this.setModInfo("Auto Swap And Use Soul Whip.");
    }
    
    @EventHandler
    private void onMouse(final EventTick e) {
        if (this.mc.currentScreen != null) {
            return;
        }
        final int dik = (this.mode.getValue() != ddd.Left) ? 1 : 0;
        if (Mouse.isButtonDown(dik) && ((boolean)this.mouse.getValue() || (boolean)this.both.getValue())) {
            this.shouldSwitch = true;
        }
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        if (this.shouldSwitch && this.timer.hasReached(200.0)) {
            ItemUtils.useSBItem("SOUL_WHIP");
            this.timer.reset();
        }
        this.shouldSwitch = false;
    }
    
    @EventHandler
    private void onKey(final EventKey event) {
        if (event.getKey() == this.getKey() && (!(boolean)this.mouse.getValue() || (boolean)this.both.getValue())) {
            this.setEnabled(this.shouldSwitch = true);
        }
    }
    
    enum ddd
    {
        Left, 
        Right;
    }
}
