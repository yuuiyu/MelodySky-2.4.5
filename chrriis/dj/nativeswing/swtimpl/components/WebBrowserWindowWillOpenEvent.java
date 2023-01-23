//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

public class WebBrowserWindowWillOpenEvent extends WebBrowserEvent
{
    private JWebBrowser newWebBrowser;
    private boolean isDialogWindow;
    
    public WebBrowserWindowWillOpenEvent(final JWebBrowser webBrowser, final JWebBrowser newWebBrowser) {
        super(webBrowser);
        this.newWebBrowser = newWebBrowser;
    }
    
    public JWebBrowser getNewWebBrowser() {
        return this.newWebBrowser;
    }
    
    public void setNewWebBrowser(final JWebBrowser newWebBrowser) {
        if (newWebBrowser == null) {
            throw new IllegalArgumentException("The new web browser cannot be null. To prevent the window to open, use the consume() method.");
        }
        this.newWebBrowser = newWebBrowser;
    }
    
    public void setDialogWindow(final boolean isDialogWindow) {
        this.isDialogWindow = isDialogWindow;
    }
    
    public boolean isDialogWindow() {
        return this.isDialogWindow;
    }
    
    public void consume() {
        this.newWebBrowser = null;
    }
    
    public boolean isConsumed() {
        return this.newWebBrowser == null;
    }
}
