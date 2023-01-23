//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import javax.swing.*;
import java.awt.*;

public abstract class WebBrowserDecorator extends JPanel
{
    public WebBrowserDecorator() {
        super(new BorderLayout());
    }
    
    public abstract void setStatusBarVisible(final boolean p0);
    
    public abstract boolean isStatusBarVisible();
    
    public abstract void setMenuBarVisible(final boolean p0);
    
    public abstract boolean isMenuBarVisible();
    
    public abstract void setButtonBarVisible(final boolean p0);
    
    public abstract boolean isButtonBarVisible();
    
    public abstract void setLocationBarVisible(final boolean p0);
    
    public abstract boolean isLocationBarVisible();
    
    public abstract void configureForWebBrowserWindow(final JWebBrowserWindow p0);
}
