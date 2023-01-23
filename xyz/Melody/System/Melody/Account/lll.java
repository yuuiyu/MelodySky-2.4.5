//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account;

import xyz.Melody.System.Melody.Account.microsoft.*;
import net.minecraft.util.*;
import xyz.Melody.*;
import xyz.Melody.System.Melody.Account.altimpl.*;
import xyz.Melody.GUI.Notification.*;
import xyz.Melody.Utils.*;

class lll extends Thread
{
    final /* synthetic */ GuiAltManager this$0;
    
    lll(final GuiAltManager this$0, final String x0) {
        this.this$0 = this$0;
        super(x0);
    }
    
    @Override
    public void run() {
        try {
            GuiAltManager.access$002(this.this$0, new MicrosoftLogin(true));
            while (this.this$0.mc.currentScreen instanceof GuiAltManager) {
                if (GuiAltManager.access$000(this.this$0).logged) {
                    GuiAltManager.access$000(this.this$0).close();
                    GuiAltManager.access$000(this.this$0).status = EnumChatFormatting.GREEN + "Success! " + GuiAltManager.access$000(this.this$0).userName;
                    Client.instance.getAccountManager().addAlt((Alt)new MicrosoftAlt(GuiAltManager.access$000(this.this$0).userName, GuiAltManager.access$000(this.this$0).refreshToken, GuiAltManager.access$000(this.this$0).msToken, GuiAltManager.access$000(this.this$0).xblToken, GuiAltManager.access$000(this.this$0).xsts1, GuiAltManager.access$000(this.this$0).xsts2, GuiAltManager.access$000(this.this$0).accessToken, GuiAltManager.access$000(this.this$0).uuid));
                    NotificationPublisher.queue("Success!", "Added Alt as " + GuiAltManager.access$000(this.this$0).userName + ".", NotificationType.SUCCESS, 10000);
                    WindowsNotification.show("MelodySky", "Success! Now you can return to the game.");
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            this.this$0.status = EnumChatFormatting.RED + e.getClass().getName() + ":" + e.getMessage();
            if (GuiAltManager.access$000(this.this$0) != null) {
                GuiAltManager.access$000(this.this$0).status = EnumChatFormatting.RED + "Failed " + e.getClass().getName() + ":" + e.getMessage();
                GuiAltManager.access$000(this.this$0).close();
                NotificationPublisher.queue("Failed.", e.getClass().getName() + ":" + e.getMessage(), NotificationType.ERROR, 5000);
            }
            GuiAltManager.access$002(this.this$0, (MicrosoftLogin)null);
        }
        this.interrupt();
    }
}
