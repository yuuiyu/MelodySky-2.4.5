//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.widgets.*;

class llIllII implements Runnable
{
    final /* synthetic */ Shell val$shell;
    final /* synthetic */ SWTNativeComponent.CMN_createControl this$0;
    
    llIllII(final SWTNativeComponent.CMN_createControl this$0, final Shell val$shell) {
        this.this$0 = this$0;
        this.val$shell = val$shell;
    }
    
    @Override
    public void run() {
        if (!this.val$shell.isDisposed()) {
            this.val$shell.setLocation(0, 0);
        }
    }
}
