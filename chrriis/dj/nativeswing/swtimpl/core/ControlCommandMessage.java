//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.widgets.*;
import chrriis.dj.nativeswing.swtimpl.*;
import chrriis.dj.nativeswing.common.*;
import org.eclipse.swt.*;

public abstract class ControlCommandMessage extends CommandMessage
{
    private int componentID;
    private transient Boolean isTargetNativeSide;
    
    int getComponentID() {
        return this.componentID;
    }
    
    public void setControl(final Control control) {
        this.componentID = (int)control.getData("NS_ID");
        this.setTargetNativeSide(false);
    }
    
    public void setNativeComponent(final NativeComponent nativeComponent) {
        this.componentID = ((SWTNativeComponent)nativeComponent).getComponentID();
        this.setTargetNativeSide(true);
    }
    
    private boolean isTargetNativeSide() {
        if (this.isTargetNativeSide == null) {
            throw new IllegalStateException("The target must be specified!");
        }
        return this.isTargetNativeSide;
    }
    
    private void setTargetNativeSide(final boolean isTargetNativeSide) {
        this.isTargetNativeSide = isTargetNativeSide;
    }
    
    public Control getControl() {
        final ObjectRegistry controlRegistry = SWTNativeComponent.getControlRegistry();
        return (controlRegistry == null) ? null : ((Control)controlRegistry.get(this.componentID));
    }
    
    public NativeComponent getNativeComponent() {
        final ObjectRegistry nativeComponentRegistry = SWTNativeComponent.getNativeComponentRegistry();
        return (nativeComponentRegistry == null) ? null : ((NativeComponent)nativeComponentRegistry.get(this.componentID));
    }
    
    public void asyncExec(final NativeComponent nativeComponent, final Object... args) {
        this.setNativeComponent(nativeComponent);
        this.asyncExec(args);
    }
    
    public void asyncExec(final Control control, final Object... args) {
        this.setControl(control);
        this.asyncExec(args);
    }
    
    public Object syncExec(final NativeComponent nativeComponent, final Object... args) {
        this.setNativeComponent(nativeComponent);
        return this.syncExec(args);
    }
    
    public Object syncExec(final Control control, final Object... args) {
        this.setControl(control);
        return this.syncExec(args);
    }
    
    private Object syncExec(final Object... args) {
        return this.syncExec(this.isTargetNativeSide(), args);
    }
    
    public Object syncExec(final boolean isTargetNativeSide, final Object... args) {
        this.checkComponentID();
        return super.syncExec(isTargetNativeSide, args);
    }
    
    private void asyncExec(final Object... args) {
        super.asyncExec(this.isTargetNativeSide(), args);
    }
    
    public void asyncExec(final boolean isTargetNativeSide, final Object... args) {
        this.checkComponentID();
        super.asyncExec(isTargetNativeSide, args);
    }
    
    private void checkComponentID() {
        if (this.componentID == 0) {
            throw new IllegalStateException("The component was not specified!");
        }
    }
    
    protected Object runCommand() throws Exception {
        try {
            return super.runCommand();
        }
        catch (RuntimeException e) {
            final SWTNativeInterface nativeInterface = SWTNativeInterface.getInstance();
            if (nativeInterface.isInProcess_() || nativeInterface.isOutProcessNativeSide_()) {
                for (Throwable ex = e; ex != null; ex = ex.getCause()) {
                    if (ex instanceof SWTException && ((SWTException)ex).code == 24) {
                        throw new DisposedControlException(ex);
                    }
                }
            }
            throw e;
        }
    }
    
    protected boolean isValid() {
        final SWTNativeInterface nativeInterface = SWTNativeInterface.getInstance();
        if (nativeInterface.isInProcess_()) {
            return this.getControl() != null || this.getNativeComponent() != null;
        }
        if (nativeInterface.isOutProcessNativeSide_()) {
            return this.getControl() != null;
        }
        return this.getNativeComponent() != null;
    }
    
    static class DisposedControlException extends IllegalStateException
    {
        public DisposedControlException(final Throwable t) {
            super("Widget is disposed", t);
        }
    }
}
