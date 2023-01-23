//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.*;

public interface JWebBrowserWindow
{
    void setTitle(final String p0);
    
    String getTitle();
    
    void setIconImage(final Image p0);
    
    void setSize(final Dimension p0);
    
    Dimension getSize();
    
    void setLocation(final Point p0);
    
    void dispose();
    
    void setVisible(final boolean p0);
    
    JWebBrowser getWebBrowser();
    
    void setBarsVisible(final boolean p0);
    
    void setStatusBarVisible(final boolean p0);
    
    boolean isStatusBarVisisble();
    
    void setMenuBarVisible(final boolean p0);
    
    boolean isMenuBarVisisble();
    
    void setButtonBarVisible(final boolean p0);
    
    boolean isButtonBarVisisble();
    
    void setLocationBarVisible(final boolean p0);
    
    boolean isLocationBarVisisble();
}
