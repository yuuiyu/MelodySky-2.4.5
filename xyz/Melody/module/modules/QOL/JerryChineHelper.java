//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import xyz.Melody.injection.mixins.packets.*;
import xyz.Melody.Utils.Item.*;

public class JerryChineHelper extends Module
{
    private TimerUtil timer;
    
    public JerryChineHelper() {
        super("JerryChineHelper", new String[] { "jch" }, ModuleType.QOL);
        this.timer = new TimerUtil();
        this.setColor(new Color(158, 205, 125).getRGB());
        this.setModInfo("Help You Using JyrreChineGun.");
    }
    
    @EventHandler
    private void tick(final EventTick event) {
        if (this.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            this.timer.reset();
        }
    }
    
    @EventHandler
    private void OP(final EventPacketRecieve e) {
        if (this.timer.hasReached(450.0)) {
            return;
        }
        if (e.getPacket() instanceof S12PacketEntityVelocity) {
            final S12PacketEntityVelocity velocity = (S12PacketEntityVelocity)e.getPacket();
            if (velocity.getEntityID() == this.mc.thePlayer.getEntityId() && this.holdingJC()) {
                final S12PacketEntityVelocity vp = (S12PacketEntityVelocity)e.getPacket();
                ((S12Accessor)vp).setMotionX(0);
                ((S12Accessor)vp).setMotionZ(0);
            }
        }
    }
    
    private boolean holdingJC() {
        final String heldItemID = (this.mc.thePlayer.getHeldItem() != null) ? ItemUtils.getSkyBlockID(this.mc.thePlayer.getHeldItem()) : "notHoldingItem";
        return heldItemID.equals("JERRY_STAFF");
    }
}
