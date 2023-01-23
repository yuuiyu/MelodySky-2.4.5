//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import net.minecraft.entity.*;
import xyz.Melody.Event.*;

public class InvisEntity extends Module
{
    public InvisEntity() {
        super("InvisEntity", new String[] { "ie" }, ModuleType.QOL);
        this.setColor(new Color(244, 255, 149).getRGB());
        this.setModInfo("Remove The Entity What You are Looking.");
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        if (this.mc.objectMouseOver == null) {
            return;
        }
        if (this.mc.objectMouseOver.entityHit == null) {
            return;
        }
        if (Client.instance.alt.isPressed()) {
            final Entity jb = this.mc.objectMouseOver.entityHit;
            this.mc.theWorld.removeEntity(jb);
        }
        this.setEnabled(false);
    }
}
