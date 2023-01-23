//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.*;

public class WebBrowserWindowOpeningEvent extends WebBrowserEvent
{
    private JWebBrowser newWebBrowser;
    private Point location;
    private Dimension size;
    
    public WebBrowserWindowOpeningEvent(final JWebBrowser webBrowser, final JWebBrowser newWebBrowser, final Point location, final Dimension size) {
        super(webBrowser);
        this.newWebBrowser = newWebBrowser;
        this.location = location;
        this.size = size;
    }
    
    public JWebBrowser getNewWebBrowser() {
        return this.newWebBrowser;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public Dimension getSize() {
        return this.size;
    }
}
