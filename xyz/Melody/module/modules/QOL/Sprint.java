//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.*;

public class Sprint extends Module
{
    public Sprint() {
        super("Sprint", new String[] { "run" }, ModuleType.QOL);
        this.setColor(new Color(158, 205, 125).getRGB());
        this.setModInfo("Toggle Sprint.");
    }
    
    @EventHandler
    private void onUpdate(final EventPreUpdate event) {
        if (this.mc.thePlayer.getFoodStats().getFoodLevel() > 6 && this.mc.thePlayer.moveForward > 0.0f) {
            this.mc.thePlayer.setSprinting(true);
        }
    }
}
