//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class lllIIIl extends WebBrowserAdapter
{
    final /* synthetic */ JFlashPlayer this$0;
    
    lllIIIl(final JFlashPlayer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void commandReceived(final WebBrowserCommandEvent e) {
        final String command = e.getCommand();
        final Object[] parameters = e.getParameters();
        final boolean isInternal = command.startsWith("[Chrriis]");
        FlashPlayerCommandEvent ev = null;
        for (final FlashPlayerListener listener : this.this$0.getFlashPlayerListeners()) {
            if (!isInternal || listener.getClass().getName().startsWith("chrriis.")) {
                if (ev == null) {
                    ev = new FlashPlayerCommandEvent(this.this$0, command, parameters);
                }
                listener.commandReceived(ev);
            }
        }
    }
}
