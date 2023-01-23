//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.microsoft;

import net.minecraft.util.*;
import xyz.Melody.*;
import xyz.Melody.System.Melody.Account.altimpl.*;
import xyz.Melody.System.Melody.Account.*;

class lI extends Thread
{
    final /* synthetic */ GuiAddMicrosoftAlt this$0;
    
    lI(final GuiAddMicrosoftAlt this$0, final String x0) {
        this.this$0 = this$0;
        super(x0);
    }
    
    @Override
    public void run() {
        try {
            GuiAddMicrosoftAlt.access$000(this.this$0).reset();
            GuiAddMicrosoftAlt.access$102(this.this$0, new MicrosoftLogin(false));
            while (this.this$0.mc.currentScreen instanceof GuiAddMicrosoftAlt) {
                if (GuiAddMicrosoftAlt.access$000(this.this$0).hasReached(10000.0) && !GuiAddMicrosoftAlt.access$100(this.this$0).initDone) {
                    GuiAddMicrosoftAlt.access$202(this.this$0, "Failed: Could not Initialize XBoxLive.");
                    GuiAddMicrosoftAlt.access$100(this.this$0).close();
                    GuiAddMicrosoftAlt.access$100(this.this$0).status = EnumChatFormatting.RED + "Failed: Timed out.";
                    GuiAddMicrosoftAlt.access$302(this.this$0, true);
                }
                else {
                    if (!GuiAddMicrosoftAlt.access$100(this.this$0).logged) {
                        continue;
                    }
                    GuiAddMicrosoftAlt.access$100(this.this$0).close();
                    GuiAddMicrosoftAlt.access$100(this.this$0).status = EnumChatFormatting.GREEN + "Success! " + GuiAddMicrosoftAlt.access$100(this.this$0).userName;
                    Client.instance.getAccountManager().addAlt((Alt)new MicrosoftAlt(GuiAddMicrosoftAlt.access$100(this.this$0).userName, GuiAddMicrosoftAlt.access$100(this.this$0).refreshToken, GuiAddMicrosoftAlt.access$100(this.this$0).msToken, GuiAddMicrosoftAlt.access$100(this.this$0).xblToken, GuiAddMicrosoftAlt.access$100(this.this$0).xsts1, GuiAddMicrosoftAlt.access$100(this.this$0).xsts2, GuiAddMicrosoftAlt.access$100(this.this$0).accessToken, GuiAddMicrosoftAlt.access$100(this.this$0).uuid));
                    GuiAddMicrosoftAlt.access$302(this.this$0, true);
                }
                break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            if (GuiAddMicrosoftAlt.access$100(this.this$0) != null) {
                GuiAddMicrosoftAlt.access$100(this.this$0).status = EnumChatFormatting.RED + "Failed " + e.getClass().getName() + ":" + e.getMessage();
                GuiAddMicrosoftAlt.access$100(this.this$0).close();
            }
            GuiAddMicrosoftAlt.access$302(this.this$0, true);
        }
        this.interrupt();
    }
}
