//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.microsoft;

import xyz.Melody.injection.mixins.client.*;
import net.minecraft.util.*;

class ll extends Thread
{
    final /* synthetic */ GuiMicrosoftLogin this$0;
    
    ll(final GuiMicrosoftLogin this$0, final String x0) {
        this.this$0 = this$0;
        super(x0);
    }
    
    @Override
    public void run() {
        try {
            GuiMicrosoftLogin.access$000(this.this$0).reset();
            GuiMicrosoftLogin.access$102(this.this$0, new MicrosoftLogin(false));
            while (this.this$0.mc.currentScreen instanceof GuiMicrosoftLogin) {
                if (GuiMicrosoftLogin.access$000(this.this$0).hasReached(10000.0) && !GuiMicrosoftLogin.access$100(this.this$0).initDone) {
                    GuiMicrosoftLogin.access$202(this.this$0, "Failed: Could not Initialize XBoxLive.");
                    GuiMicrosoftLogin.access$100(this.this$0).close();
                    GuiMicrosoftLogin.access$100(this.this$0).status = EnumChatFormatting.RED + "Failed: Timed out.";
                    GuiMicrosoftLogin.access$302(this.this$0, true);
                }
                else {
                    if (!GuiMicrosoftLogin.access$100(this.this$0).logged) {
                        continue;
                    }
                    GuiMicrosoftLogin.access$100(this.this$0).close();
                    GuiMicrosoftLogin.access$100(this.this$0).status = EnumChatFormatting.GREEN + "Success! " + GuiMicrosoftLogin.access$100(this.this$0).userName;
                    ((MCA)this.this$0.mc).setSession(new Session(GuiMicrosoftLogin.access$100(this.this$0).userName, GuiMicrosoftLogin.access$100(this.this$0).uuid, GuiMicrosoftLogin.access$100(this.this$0).accessToken, "mojang"));
                    GuiMicrosoftLogin.access$302(this.this$0, true);
                }
                break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            if (GuiMicrosoftLogin.access$100(this.this$0) != null) {
                GuiMicrosoftLogin.access$202(this.this$0, "Failed: " + e.getClass().getName() + ":" + e.getMessage());
                GuiMicrosoftLogin.access$100(this.this$0).status = EnumChatFormatting.RED + "Failed " + e.getClass().getName() + ":" + e.getMessage();
                GuiMicrosoftLogin.access$100(this.this$0).close();
            }
            GuiMicrosoftLogin.access$302(this.this$0, true);
        }
        this.interrupt();
    }
}
