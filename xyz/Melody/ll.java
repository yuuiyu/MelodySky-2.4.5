//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody;

import java.util.*;

class ll extends TimerTask
{
    final /* synthetic */ Client this$0;
    
    ll(final Client this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!Client.access$000(this.this$0).enabledNeededMod) {
            this.this$0.saveConfig(false);
            this.this$0.saveCustomName();
            this.this$0.saveMenuMode();
            this.this$0.saveUISettings(false);
        }
    }
}
