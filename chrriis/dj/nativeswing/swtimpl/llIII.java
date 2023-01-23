//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import chrriis.dj.nativeswing.swtimpl.components.*;

class llIII extends WebBrowserAdapter
{
    final /* synthetic */ WebBrowserObject this$0;
    
    llIII(final WebBrowserObject this$0) {
        this.this$0 = this$0;
    }
    
    public void commandReceived(final WebBrowserCommandEvent e) {
        if ("[Chrriis]WB_setLoaded".equals(e.getCommand())) {
            final Object[] listeners = this.this$0.listenerList.getListenerList();
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserObject.InitializationListener.class) {
                    ((WebBrowserObject.InitializationListener)listeners[i + 1]).objectInitialized();
                }
            }
        }
    }
}
