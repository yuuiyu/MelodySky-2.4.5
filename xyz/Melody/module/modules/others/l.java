//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import java.util.*;

class l extends TimerTask
{
    final /* synthetic */ String val$game;
    final /* synthetic */ AutoGG this$0;
    
    l(final AutoGG this$0, final String val$game) {
        this.this$0 = this$0;
        this.val$game = val$game;
    }
    
    @Override
    public void run() {
        this.this$0.mc.thePlayer.sendChatMessage("/play " + this.val$game);
    }
}
