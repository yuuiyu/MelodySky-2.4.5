//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import chrriis.dj.nativeswing.common.*;
import java.util.*;
import java.awt.image.*;
import java.awt.*;

public abstract class NativeComponent extends Canvas
{
    private static ObjectRegistry nativeComponentRegistry;
    private static ObjectRegistry controlRegistry;
    
    public abstract void runInSequence(final Runnable p0);
    
    public abstract Object runSync(final CommandMessage p0, final Object... p1);
    
    public abstract void runAsync(final CommandMessage p0, final Object... p1);
    
    public static NativeComponent[] getNativeComponents() {
        final List<NativeComponent> nativeComponentList = new ArrayList<NativeComponent>();
        for (final int instanceID : NativeComponent.nativeComponentRegistry.getInstanceIDs()) {
            final NativeComponent nativeComponent = (NativeComponent)NativeComponent.nativeComponentRegistry.get(instanceID);
            if (nativeComponent != null) {
                nativeComponentList.add(nativeComponent);
            }
        }
        return nativeComponentList.toArray(new NativeComponent[0]);
    }
    
    protected static ObjectRegistry getNativeComponentRegistry() {
        return NativeComponent.nativeComponentRegistry;
    }
    
    protected static ObjectRegistry getControlRegistry() {
        return NativeComponent.controlRegistry;
    }
    
    protected abstract int getComponentID();
    
    public abstract void initializeNativePeer();
    
    protected abstract Object[] getNativePeerCreationParameters();
    
    protected abstract void disposeNativePeer();
    
    public abstract boolean isNativePeerDisposed();
    
    public abstract boolean isNativePeerInitialized();
    
    public abstract boolean isNativePeerValid();
    
    protected abstract Component createEmbeddableComponent(final Map<Object, Object> p0);
    
    public abstract void paintComponent(final BufferedImage p0);
    
    public abstract void paintComponent(final BufferedImage p0, final Rectangle[] p1);
    
    public abstract void createBackBuffer();
    
    public abstract boolean hasBackBuffer();
    
    public abstract void updateBackBufferOnVisibleTranslucentAreas();
    
    public abstract void updateBackBuffer(final Rectangle[] p0);
    
    public abstract void destroyBackBuffer();
    
    static {
        if (NativeInterface.isInProcess()) {
            NativeComponent.nativeComponentRegistry = new ObjectRegistry();
            NativeComponent.controlRegistry = new ObjectRegistry();
        }
        else if (NativeInterface.isOutProcessNativeSide()) {
            NativeComponent.controlRegistry = new ObjectRegistry();
        }
        else {
            NativeComponent.nativeComponentRegistry = new ObjectRegistry();
        }
    }
}
