//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import xyz.Melody.Event.*;

public class NoSlowDown extends Module
{
    public NoSlowDown() {
        super("NoSlowDown", new String[] { "ab", "autob", "ablock" }, ModuleType.Balance);
        this.setModInfo("No Slow Down When Using Item.");
    }
    
    @EventHandler
    private void onPost(final EventPostUpdate e) {
        if (this.mc.thePlayer.isBlocking() && (this.mc.thePlayer.moveForward != 0.0f || this.mc.thePlayer.moveStrafing != 0.0f)) {
            this.mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, this.mc.thePlayer.inventory.getCurrentItem(), 0.0f, 0.0f, 0.0f));
        }
    }
}
