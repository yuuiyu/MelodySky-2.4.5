//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;

public class WebBrowserEvent extends EventObject
{
    private JWebBrowser webBrowser;
    
    public WebBrowserEvent(final JWebBrowser webBrowser) {
        super(webBrowser);
        this.webBrowser = webBrowser;
    }
    
    public JWebBrowser getWebBrowser() {
        return this.webBrowser;
    }
}
