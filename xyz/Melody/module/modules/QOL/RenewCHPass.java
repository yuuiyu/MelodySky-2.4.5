//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class RenewCHPass extends Module
{
    private Option<Boolean> shab;
    
    public RenewCHPass() {
        super("AutoRenewCHPass", new String[] { "rchp" }, ModuleType.QOL);
        this.shab = (Option<Boolean>)new Option("Compatible Mode", (Object)false);
        this.addValues(new Value[] { (Value)this.shab });
        this.setColor(new Color(158, 205, 125).getRGB());
        this.setModInfo("Auto Renew Crystal Hollows Pass.");
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onChat(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (message.equals("Your pass to the Crystal Hollows will expire in 1 minute")) {
            this.mc.thePlayer.sendChatMessage("/purchasecrystallhollowspass");
        }
        if (this.shab.getValue()) {
            if (message.contains("Your pass to the Crystal Hollows will expire in 1 minute")) {
                this.mc.thePlayer.sendChatMessage("/purchasecrystallhollowspass");
            }
            if (message.contains("remaining on your pass.")) {
                this.mc.thePlayer.sendChatMessage("/purchasecrystallhollowspass");
            }
        }
    }
}
