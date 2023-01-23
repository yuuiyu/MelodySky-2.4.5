//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.ref.*;
import java.util.*;
import java.awt.*;

public class NativeComponentWrapper
{
    private static final boolean IS_DEBUGGING_OPTIONS;
    private boolean isRegistered;
    private Component nativeComponent;
    private Reference<NativeComponentProxy> nativeComponentProxy;
    private BackBufferManager backBufferManager;
    
    private void checkParent() {
        final Container parent = this.nativeComponent.getParent();
        if (parent != null && !(parent instanceof NativeComponentHolder)) {
            throw new IllegalStateException("The native component cannot be added directly! Use the createEmbeddableComponent() method to get a component that can be added.");
        }
        if (parent != null && SwingUtilities.getWindowAncestor(parent) != null) {
            if (!this.isRegistered) {
                NativeSwing.addNativeComponentWrapper(this);
                this.isRegistered = true;
            }
        }
        else if (this.isRegistered && NativeSwing.removeNativeComponentWrapper(this)) {
            this.isRegistered = false;
        }
    }
    
    public NativeComponentWrapper(final Component nativeComponent) {
        this.nativeComponent = nativeComponent;
        this.checkParent();
        nativeComponent.addHierarchyListener((HierarchyListener)new lIlllI(this));
    }
    
    Component getNativeComponent() {
        return this.nativeComponent;
    }
    
    protected void paintNativeComponent(final BufferedImage image, final Rectangle[] rectangles) {
    }
    
    void setNativeComponentProxy(final NativeComponentProxy nativeComponentProxy) {
        if (nativeComponentProxy == null) {
            this.nativeComponentProxy = null;
        }
        else {
            this.nativeComponentProxy = new WeakReference<NativeComponentProxy>(nativeComponentProxy);
        }
    }
    
    NativeComponentProxy getNativeComponentProxy() {
        return (this.nativeComponentProxy == null) ? null : this.nativeComponentProxy.get();
    }
    
    protected void setNativeComponentEnabled(final boolean isEnabled) {
    }
    
    protected boolean isNativeComponentEnabled() {
        return true;
    }
    
    protected String getComponentDescription() {
        return this.getClass().getName() + "[" + this.hashCode() + "]";
    }
    
    public Component createEmbeddableComponent(final NSOption... options) {
        return this.createEmbeddableComponent(NSOption.createOptionMap(options));
    }
    
    public Component createEmbeddableComponent(final Map<Object, Object> optionMap) {
        if (NativeComponentWrapper.IS_DEBUGGING_OPTIONS) {
            final StringBuilder sb = new StringBuilder();
            sb.append("NativeComponent ").append(this.getComponentDescription()).append(" options: ");
            boolean isFirst = true;
            for (final Object key : optionMap.keySet()) {
                if (isFirst) {
                    isFirst = false;
                }
                else {
                    sb.append(", ");
                }
                final Object value = optionMap.get(key);
                if (value instanceof NSOption) {
                    sb.append(value);
                }
                else {
                    sb.append(key).append('=').append(value);
                }
            }
            if (isFirst) {
                sb.append("<none>");
            }
            System.err.println(sb);
        }
        final String isActive = NSSystemProperty.INTEGRATION_ACTIVE.get();
        if (optionMap.get("Deactivate Native Integration") != null || (isActive != null && !Boolean.parseBoolean(isActive))) {
            this.isRegistered = true;
            return (Component)new SimpleNativeComponentHolder(this);
        }
        final Boolean deferredDestruction = (optionMap.get("Destroy On Finalization") != null) ? Boolean.TRUE : null;
        Boolean componentHierarchyProxying = (optionMap.get("Proxy Component Hierarchy") != null) ? Boolean.TRUE : null;
        Boolean visibilityConstraint = (optionMap.get("Constrain Visibility") != null) ? Boolean.TRUE : null;
        if (Boolean.valueOf(NSSystemProperty.INTEGRATION_USEDEFAULTCLIPPING.get()) || (visibilityConstraint == null && componentHierarchyProxying == null)) {
            if (deferredDestruction != null && componentHierarchyProxying == null) {
                componentHierarchyProxying = true;
            }
            if (Boolean.TRUE.equals(componentHierarchyProxying)) {
                return (Component)new NativeComponentProxyFinalizationPanel(this);
            }
            return (Component)new SimpleNativeComponentHolder(this);
        }
        else {
            final boolean isJNAPresent = isJNAPresent();
            if (visibilityConstraint == null && isJNAPresent && componentHierarchyProxying != null) {
                visibilityConstraint = true;
            }
            if (visibilityConstraint != null && !isJNAPresent) {
                throw new IllegalStateException("The JNA libraries are required to use the visibility constraints!");
            }
            if (deferredDestruction != null && componentHierarchyProxying == null) {
                componentHierarchyProxying = true;
            }
            if (componentHierarchyProxying != null) {
                return (Component)new NativeComponentProxyPanel(this, Boolean.TRUE.equals(visibilityConstraint), Boolean.TRUE.equals(deferredDestruction), Boolean.TRUE.equals(componentHierarchyProxying));
            }
            if (visibilityConstraint == null) {
                return (Component)new SimpleNativeComponentHolder(this);
            }
            return (Component)new NativeComponentProxyPanel(this, Boolean.TRUE.equals(visibilityConstraint), Boolean.TRUE.equals(deferredDestruction), Boolean.TRUE.equals(componentHierarchyProxying));
        }
    }
    
    private static boolean isJNAPresent() {
        try {
            Class.forName("chrriis.dj.nativeswing.jna.platform.WindowUtils");
            Class.forName("com.sun.jna.platform.WindowUtils");
            Class.forName("com.sun.jna.Platform");
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public void disposeNativeComponent() {
        final NativeComponentProxy nativeComponentProxy = this.getNativeComponentProxy();
        if (nativeComponentProxy != null) {
            nativeComponentProxy.dispose();
        }
    }
    
    BackBufferManager getBackBufferManager() {
        final NativeComponentProxy nativeComponentProxy = this.getNativeComponentProxy();
        if (nativeComponentProxy != null) {
            return nativeComponentProxy.getBackBufferManager();
        }
        if (this.backBufferManager == null) {
            this.backBufferManager = new BackBufferManager(this, this.getNativeComponent());
        }
        return this.backBufferManager;
    }
    
    public void paintBackBuffer(final Graphics g, final boolean isPaintingProxy) {
        final NativeComponentProxy nativeComponentProxy = this.getNativeComponentProxy();
        if (nativeComponentProxy != null) {
            final BackBufferManager backBufferManager = nativeComponentProxy.getBackBufferManager();
            if (backBufferManager != null) {
                backBufferManager.paintBackBuffer(g);
            }
            return;
        }
        if (this.backBufferManager != null) {
            this.backBufferManager.paintBackBuffer(g);
        }
    }
    
    public boolean hasBackBuffer() {
        final NativeComponentProxy nativeComponentProxy = this.getNativeComponentProxy();
        if (nativeComponentProxy != null) {
            final BackBufferManager backBufferManager = nativeComponentProxy.getBackBufferManager();
            return backBufferManager != null && backBufferManager.hasBackBuffer();
        }
        return this.backBufferManager != null && this.backBufferManager.hasBackBuffer();
    }
    
    public void createBackBuffer() {
        this.getBackBufferManager().createBackBuffer();
    }
    
    public void updateBackBufferOnVisibleTranslucentAreas() {
        this.getBackBufferManager().updateBackBufferOnVisibleTranslucentAreas();
    }
    
    public void updateBackBuffer(final Rectangle[] rectangles) {
        this.getBackBufferManager().updateBackBuffer(rectangles);
    }
    
    public void destroyBackBuffer() {
        this.getBackBufferManager().destroyBackBuffer();
    }
    
    public void transferFocus(final boolean isForward) {
        Component c = (Component)this.getNativeComponentProxy();
        if (c == null) {
            c = this.getNativeComponent();
        }
        if (isForward) {
            c.transferFocus();
        }
        else {
            c.transferFocusBackward();
        }
    }
    
    protected void storeInHiddenParent() {
        throw new IllegalStateException("Storing to a hidden parent is not supported!");
    }
    
    protected void restoreFromHiddenParent() {
    }
    
    static {
        IS_DEBUGGING_OPTIONS = Boolean.parseBoolean(NSSystemProperty.COMPONENTS_DEBUG_PRINTOPTIONS.get());
    }
    
    static class SimpleNativeComponentHolder extends EmbeddableComponent implements NativeComponentHolder
    {
        private NativeComponentWrapper nativeComponent;
        
        public SimpleNativeComponentHolder(final NativeComponentWrapper nativeComponent) {
            this.nativeComponent = nativeComponent;
            this.add(nativeComponent.getNativeComponent());
            this.enableEvents(131072L);
        }
        
        protected void printComponent(final Graphics g) {
            this.nativeComponent.getNativeComponent().print(g);
        }
    }
    
    interface NativeComponentHolder
    {
    }
}
