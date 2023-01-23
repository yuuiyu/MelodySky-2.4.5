//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

abstract class NativeComponentProxy extends EmbeddableComponent
{
    private BackBufferManager backBufferManager;
    protected NativeComponentWrapper nativeComponentWrapper;
    
    protected NativeComponentProxy(final NativeComponentWrapper nativeComponentWrapper) {
        (this.nativeComponentWrapper = nativeComponentWrapper).setNativeComponentProxy(this);
        this.backBufferManager = new BackBufferManager(nativeComponentWrapper, (Component)this);
        this.setFocusable(true);
        this.addFocusListener((FocusListener)new lIIIIl(this));
    }
    
    protected void printComponent(final Graphics g) {
        this.nativeComponentWrapper.getNativeComponent().print(g);
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.backBufferManager.paintBackBuffer(g);
    }
    
    public BackBufferManager getBackBufferManager() {
        return this.backBufferManager;
    }
    
    protected abstract void dispose();
    
    protected static JLayeredPane findLayeredPane(final Component c) {
        Component parent = c;
        while ((parent = parent.getParent()) != null) {
            if (!parent.isLightweight() && parent instanceof RootPaneContainer) {
                return ((RootPaneContainer)parent).getLayeredPane();
            }
        }
        throw new IllegalStateException("The window ancestor must be a root pane container!");
    }
}
