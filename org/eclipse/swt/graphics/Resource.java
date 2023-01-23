//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import java.util.function.*;
import org.eclipse.swt.*;

public abstract class Resource
{
    Device device;
    private static Consumer<Error> nonDisposedReporter;
    private ResourceTracker tracker;
    
    public Resource() {
        this.initNonDisposeTracking();
    }
    
    Resource(Device device) {
        if (device == null) {
            device = Device.getDevice();
        }
        if (device == null) {
            SWT.error(4);
        }
        this.device = device;
        this.initNonDisposeTracking();
    }
    
    void destroy() {
    }
    
    public void dispose() {
        if (this.device == null) {
            return;
        }
        if (this.device.isDisposed()) {
            return;
        }
        this.destroy();
        if (this.device.tracking) {
            this.device.dispose_Object((Object)this);
        }
        this.device = null;
    }
    
    public Device getDevice() {
        final Device device = this.device;
        if (device == null || this.isDisposed()) {
            SWT.error(44);
        }
        return device;
    }
    
    void ignoreNonDisposed() {
        if (this.tracker != null) {
            this.tracker.ignoreMe = true;
        }
    }
    
    void init() {
        if (this.device.tracking) {
            this.device.new_Object((Object)this);
        }
    }
    
    void initNonDisposeTracking() {
        if (this instanceof Color) {
            return;
        }
        if (Resource.nonDisposedReporter == null) {
            return;
        }
        final Error error = new Error("SWT Resource was not properly disposed");
        this.tracker = new ResourceTracker(this, error);
    }
    
    public abstract boolean isDisposed();
    
    public static void setNonDisposeHandler(final Consumer<Error> reporter) {
        Resource.nonDisposedReporter = reporter;
    }
    
    static {
        final boolean trackingEnabled = Boolean.getBoolean("org.eclipse.swt.graphics.Resource.reportNonDisposed");
        if (trackingEnabled) {
            setNonDisposeHandler(exception -> {
                if (exception != null) {
                    exception.printStackTrace();
                }
                else {
                    System.err.println("SWT Resource was not properly disposed");
                }
            });
        }
    }
    
    private static class ResourceTracker
    {
        private Resource resource;
        private Error allocationStack;
        boolean ignoreMe;
        
        ResourceTracker(final Resource resource, final Error allocationStack) {
            this.resource = resource;
            this.allocationStack = allocationStack;
        }
        
        @Override
        protected void finalize() {
            if (this.ignoreMe) {
                return;
            }
            if (Resource.nonDisposedReporter == null) {
                return;
            }
            if (!this.resource.isDisposed()) {
                Resource.nonDisposedReporter.accept(this.allocationStack);
            }
        }
    }
}
