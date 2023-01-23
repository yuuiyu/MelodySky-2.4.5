//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import chrriis.dj.nativeswing.swtimpl.components.*;

class llIlI implements Runnable
{
    final /* synthetic */ lIIlII this$1;
    
    llIlI(final lIIlII this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        final JWebBrowserWindow browserWindow = this.this$1.val$newWebBrowser.getWebBrowserWindow();
        if (browserWindow != null && !this.this$1.val$newWebBrowser.getNativeComponent().isNativePeerDisposed()) {
            browserWindow.setVisible(true);
        }
    }
}
