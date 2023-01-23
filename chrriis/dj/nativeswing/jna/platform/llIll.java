//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.jna.platform;

import java.awt.*;

class llIll implements Runnable
{
    final /* synthetic */ Window val$w;
    final /* synthetic */ float val$alpha;
    final /* synthetic */ WindowUtils.MacWindowUtils this$0;
    
    llIll(final WindowUtils.MacWindowUtils this$0, final Window val$w, final float val$alpha) {
        this.this$0 = this$0;
        this.val$w = val$w;
        this.val$alpha = val$alpha;
    }
    
    @Override
    public void run() {
        final Object peer = WindowUtils.getPeer(this.val$w);
        try {
            peer.getClass().getMethod("setAlpha", Float.TYPE).invoke(peer, new Float(this.val$alpha));
        }
        catch (Exception ex) {}
    }
}
