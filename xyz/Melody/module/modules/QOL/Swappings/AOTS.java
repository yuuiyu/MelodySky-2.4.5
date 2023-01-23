//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Swappings;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.Item.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.misc.*;

public class AOTS extends Module
{
    private boolean shouldSwitch;
    private TimerUtil timer;
    
    public AOTS() {
        super("AOTS", new String[] { "aots" }, ModuleType.Swapping);
        this.timer = new TimerUtil();
        this.setModInfo("Auto Swap Axe Of the Shredded.");
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        if (this.shouldSwitch && this.timer.hasReached(200.0)) {
            ItemUtils.useSBItem("AXE_OF_THE_SHREDDED");
            this.timer.reset();
        }
        this.shouldSwitch = false;
    }
    
    @EventHandler
    private void onKey(final EventKey event) {
        if (event.getKey() == this.getKey()) {
            this.setEnabled(this.shouldSwitch = true);
        }
    }
}
