//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;
import javax.swing.*;
import chrriis.dj.nativeswing.common.*;
import java.awt.*;

public class WebBrowserWindowFactory
{
    public static JWebBrowserWindow create(final JWebBrowser webBrowser) {
        return create(null, webBrowser);
    }
    
    public static JWebBrowserWindow create(final Window parentWindow, final JWebBrowser webBrowser) {
        final WebBrowserWindowStrategy webBrowserWindowStrategy = new WebBrowserWindowStrategy(webBrowser);
        final JWebBrowserWindow webBrowserWindow = createWindow(webBrowserWindowStrategy, parentWindow, parentWindow != null);
        webBrowser.getWebBrowserDecorator().configureForWebBrowserWindow(webBrowserWindow);
        ((RootPaneContainer)webBrowserWindow).getContentPane().add(webBrowser, "Center");
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        size.width = size.width * 80 / 100;
        size.height = size.height * 80 / 100;
        final Window window = (Window)webBrowserWindow;
        window.setSize(size);
        window.setLocationByPlatform(true);
        window.addWindowListener((WindowListener)new lIIIllIl(webBrowserWindowStrategy));
        return webBrowserWindow;
    }
    
    private static JWebBrowserWindow createWindow(final WebBrowserWindowStrategy webBrowserWindowStrategy, final Window parentWindow, final boolean isDialog) {
        JWebBrowserWindow window;
        if (isDialog) {
            if (parentWindow instanceof Frame) {
                window = (JWebBrowserWindow)new WebBrowserDialog(webBrowserWindowStrategy, (Frame)parentWindow);
            }
            else {
                window = (JWebBrowserWindow)new WebBrowserDialog(webBrowserWindowStrategy, (Dialog)parentWindow);
            }
        }
        else {
            window = (JWebBrowserWindow)new WebBrowserFrame(webBrowserWindowStrategy);
        }
        return window;
    }
    
    private static class WebBrowserFrame extends JFrame implements JWebBrowserWindow
    {
        private WebBrowserWindowStrategy webBrowserWindowStrategy;
        
        public WebBrowserFrame(final WebBrowserWindowStrategy webBrowserWindowStrategy) {
            this.webBrowserWindowStrategy = webBrowserWindowStrategy;
            this.setDefaultCloseOperation(2);
        }
        
        public void show() {
            final boolean isLocationByPlatform = this.isLocationByPlatform();
            super.show();
            if (isLocationByPlatform) {
                adjustInScreen(this);
            }
        }
        
        public JWebBrowser getWebBrowser() {
            return this.webBrowserWindowStrategy.getWebBrowser();
        }
        
        public void setBarsVisible(final boolean areBarsVisible) {
            this.webBrowserWindowStrategy.setBarsVisible(areBarsVisible);
        }
        
        public void setStatusBarVisible(final boolean isStatusBarVisible) {
            this.webBrowserWindowStrategy.setStatusBarVisible(isStatusBarVisible);
        }
        
        public boolean isStatusBarVisisble() {
            return this.webBrowserWindowStrategy.isStatusBarVisisble();
        }
        
        public void setMenuBarVisible(final boolean isMenuBarVisible) {
            this.webBrowserWindowStrategy.setMenuBarVisible(isMenuBarVisible);
        }
        
        public boolean isMenuBarVisisble() {
            return this.webBrowserWindowStrategy.isMenuBarVisisble();
        }
        
        public void setButtonBarVisible(final boolean isButtonBarVisible) {
            this.webBrowserWindowStrategy.setButtonBarVisible(isButtonBarVisible);
        }
        
        public boolean isButtonBarVisisble() {
            return this.webBrowserWindowStrategy.isButtonBarVisisble();
        }
        
        public void setLocationBarVisible(final boolean isLocationBarVisible) {
            this.webBrowserWindowStrategy.setLocationBarVisible(isLocationBarVisible);
        }
        
        public boolean isLocationBarVisisble() {
            return this.webBrowserWindowStrategy.isLocationBarVisisble();
        }
    }
    
    private static class WebBrowserDialog extends JDialog implements JWebBrowserWindow
    {
        private WebBrowserWindowStrategy webBrowserWindowStrategy;
        
        public WebBrowserDialog(final WebBrowserWindowStrategy webBrowserWindowStrategy, final Frame parentWindow) {
            super(parentWindow);
            this.webBrowserWindowStrategy = webBrowserWindowStrategy;
            this.setDefaultCloseOperation(2);
        }
        
        public WebBrowserDialog(final WebBrowserWindowStrategy webBrowserWindowStrategy, final Dialog parentWindow) {
            super(parentWindow);
            this.webBrowserWindowStrategy = webBrowserWindowStrategy;
            this.setDefaultCloseOperation(2);
        }
        
        public void show() {
            final boolean isLocationByPlatform = this.isLocationByPlatform();
            super.show();
            if (isLocationByPlatform) {
                adjustInScreen(this);
            }
        }
        
        public void setIconImage(final Image image) {
            if (Utils.IS_JAVA_6_OR_GREATER) {
                super.setIconImage(image);
            }
        }
        
        public JWebBrowser getWebBrowser() {
            return this.webBrowserWindowStrategy.getWebBrowser();
        }
        
        public void setBarsVisible(final boolean areBarsVisible) {
            this.webBrowserWindowStrategy.setBarsVisible(areBarsVisible);
        }
        
        public void setStatusBarVisible(final boolean isStatusBarVisible) {
            this.webBrowserWindowStrategy.setStatusBarVisible(isStatusBarVisible);
        }
        
        public boolean isStatusBarVisisble() {
            return this.webBrowserWindowStrategy.isStatusBarVisisble();
        }
        
        public void setMenuBarVisible(final boolean isMenuBarVisible) {
            this.webBrowserWindowStrategy.setMenuBarVisible(isMenuBarVisible);
        }
        
        public boolean isMenuBarVisisble() {
            return this.webBrowserWindowStrategy.isMenuBarVisisble();
        }
        
        public void setButtonBarVisible(final boolean isButtonBarVisible) {
            this.webBrowserWindowStrategy.setButtonBarVisible(isButtonBarVisible);
        }
        
        public boolean isButtonBarVisisble() {
            return this.webBrowserWindowStrategy.isButtonBarVisisble();
        }
        
        public void setLocationBarVisible(final boolean isLocationBarVisible) {
            this.webBrowserWindowStrategy.setLocationBarVisible(isLocationBarVisible);
        }
        
        public boolean isLocationBarVisisble() {
            return this.webBrowserWindowStrategy.isLocationBarVisisble();
        }
    }
    
    private static class WebBrowserWindowStrategy
    {
        private JWebBrowser webBrowser;
        
        public WebBrowserWindowStrategy(final JWebBrowser webBrowser) {
            this.webBrowser = webBrowser;
        }
        
        public JWebBrowser getWebBrowser() {
            return this.webBrowser;
        }
        
        public void setBarsVisible(final boolean areBarsVisible) {
            this.webBrowser.setBarsVisible(areBarsVisible);
        }
        
        public void setStatusBarVisible(final boolean isStatusBarVisible) {
            this.webBrowser.setStatusBarVisible(isStatusBarVisible);
        }
        
        public boolean isStatusBarVisisble() {
            return this.webBrowser.isStatusBarVisible();
        }
        
        public void setMenuBarVisible(final boolean isMenuBarVisible) {
            this.webBrowser.setMenuBarVisible(isMenuBarVisible);
        }
        
        public boolean isMenuBarVisisble() {
            return this.webBrowser.isMenuBarVisible();
        }
        
        public void setButtonBarVisible(final boolean isButtonBarVisible) {
            this.webBrowser.setButtonBarVisible(isButtonBarVisible);
        }
        
        public boolean isButtonBarVisisble() {
            return this.webBrowser.isButtonBarVisible();
        }
        
        public void setLocationBarVisible(final boolean isLocationBarVisible) {
            this.webBrowser.setLocationBarVisible(isLocationBarVisible);
        }
        
        public boolean isLocationBarVisisble() {
            return this.webBrowser.isLocationBarVisible();
        }
        
        private static void adjustInScreen(final Window window) {
            final GraphicsConfiguration gc = window.getGraphicsConfiguration();
            final Rectangle gcBounds = gc.getBounds();
            final Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
            final Rectangle rectangle = gcBounds;
            rectangle.x += screenInsets.left;
            final Rectangle rectangle2 = gcBounds;
            rectangle2.width -= screenInsets.left + screenInsets.right;
            final Rectangle rectangle3 = gcBounds;
            rectangle3.y += screenInsets.top;
            final Rectangle rectangle4 = gcBounds;
            rectangle4.height -= screenInsets.top + screenInsets.bottom;
            final Rectangle bounds = window.getBounds();
            if (gcBounds.x + gcBounds.width < bounds.x + bounds.width) {
                bounds.x = gcBounds.x + gcBounds.width - bounds.width;
            }
            if (bounds.x < gcBounds.x) {
                bounds.x = gcBounds.x;
            }
            if (gcBounds.y + gcBounds.height < bounds.y + bounds.height) {
                bounds.y = gcBounds.y + gcBounds.height - bounds.height;
            }
            if (bounds.y < gcBounds.y) {
                bounds.y = gcBounds.y;
            }
            if (!window.getBounds().equals(bounds)) {
                window.setBounds(bounds);
            }
        }
    }
}
