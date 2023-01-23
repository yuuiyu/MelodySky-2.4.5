//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.widgets.*;

class lIIlllI implements Runnable
{
    final /* synthetic */ Control val$control;
    final /* synthetic */ InProcessMessagingInterface this$0;
    
    lIIlllI(final InProcessMessagingInterface this$0, final Control val$control) {
        this.this$0 = this$0;
        this.val$control = val$control;
    }
    
    @Override
    public void run() {
        this.val$control.getShell().dispose();
    }
}
