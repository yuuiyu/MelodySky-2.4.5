//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class llIllll extends WebBrowserAdapter
{
    final /* synthetic */ JHTMLEditor this$0;
    
    llIllll(final JHTMLEditor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void commandReceived(final WebBrowserCommandEvent e) {
        final String command = e.getCommand();
        if ("[Chrriis]JH_setLoaded".equals(command)) {
            final Object[] listeners = JHTMLEditor.access$000(this.this$0).getListenerList();
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == JHTMLEditor.InitializationListener.class) {
                    ((JHTMLEditor.InitializationListener)listeners[i + 1]).objectInitialized();
                }
            }
        }
        else if ("[Chrriis]JH_setDirty".equals(command)) {
            JHTMLEditor.access$100(this.this$0, true);
        }
    }
}
