//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;

public interface WebBrowserListener extends EventListener
{
    void windowWillOpen(final WebBrowserWindowWillOpenEvent p0);
    
    void windowOpening(final WebBrowserWindowOpeningEvent p0);
    
    void windowClosing(final WebBrowserEvent p0);
    
    void locationChanging(final WebBrowserNavigationEvent p0);
    
    void locationChanged(final WebBrowserNavigationEvent p0);
    
    void locationChangeCanceled(final WebBrowserNavigationEvent p0);
    
    void loadingProgressChanged(final WebBrowserEvent p0);
    
    void titleChanged(final WebBrowserEvent p0);
    
    void statusChanged(final WebBrowserEvent p0);
    
    void commandReceived(final WebBrowserCommandEvent p0);
}
