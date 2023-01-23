//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class lIIIlIll extends WebBrowserAdapter
{
    final /* synthetic */ JHTMLEditorTinyMCE this$0;
    
    lIIIlIll(final JHTMLEditorTinyMCE this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void windowWillOpen(final WebBrowserWindowWillOpenEvent e) {
        e.setDialogWindow(true);
    }
}
