//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class lIIIllll extends WebBrowserAdapter
{
    final /* synthetic */ JWebBrowserWindow val$webBrowserWindow;
    final /* synthetic */ DefaultWebBrowserDecorator this$0;
    
    lIIIllll(final DefaultWebBrowserDecorator this$0, final JWebBrowserWindow val$webBrowserWindow) {
        this.this$0 = this$0;
        this.val$webBrowserWindow = val$webBrowserWindow;
    }
    
    @Override
    public void titleChanged(final WebBrowserEvent e) {
        this.this$0.setWebBrowserWindowTitle(this.val$webBrowserWindow, e.getWebBrowser().getPageTitle());
    }
}
