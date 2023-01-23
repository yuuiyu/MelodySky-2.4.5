//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import javax.swing.*;
import chrriis.dj.nativeswing.*;
import java.awt.*;

public abstract class NSPanelComponent extends JPanel implements NSComponent
{
    private NativeComponent nativeComponent;
    
    public static NSOption destroyOnFinalization() {
        return NSComponentOptions.destroyOnFinalization();
    }
    
    public static NSOption proxyComponentHierarchy() {
        return NSComponentOptions.proxyComponentHierarchy();
    }
    
    public static NSOption constrainVisibility() {
        return NSComponentOptions.constrainVisibility();
    }
    
    public NSPanelComponent() {
        super(new BorderLayout());
    }
    
    protected void initialize(final NativeComponent nativeComponent) {
        if (this.nativeComponent != null) {
            throw new IllegalStateException("The native component is already initialized!");
        }
        this.nativeComponent = nativeComponent;
    }
    
    public void initializeNativePeer() {
        this.nativeComponent.initializeNativePeer();
    }
    
    public void disposeNativePeer() {
        this.nativeComponent.disposeNativePeer();
    }
    
    public boolean isNativePeerDisposed() {
        return this.nativeComponent.isNativePeerDisposed();
    }
    
    public boolean isNativePeerInitialized() {
        return this.nativeComponent.isNativePeerInitialized();
    }
    
    public boolean isNativePeerValid() {
        return this.nativeComponent.isNativePeerValid();
    }
    
    public void runInSequence(final Runnable runnable) {
        this.nativeComponent.runInSequence(runnable);
    }
    
    public NativeComponent getNativeComponent() {
        return this.nativeComponent;
    }
}
