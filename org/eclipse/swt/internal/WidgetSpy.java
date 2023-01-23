//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import org.eclipse.swt.widgets.*;
import java.util.*;

public class WidgetSpy
{
    public static boolean isEnabled;
    private static final WidgetSpy instance;
    private WidgetTracker widgetTracker;
    
    private WidgetSpy() {
    }
    
    public static WidgetSpy getInstance() {
        return WidgetSpy.instance;
    }
    
    public void setWidgetTracker(final WidgetTracker tracker) {
        WidgetSpy.isEnabled = (tracker != null);
        this.widgetTracker = tracker;
    }
    
    public void widgetCreated(final Widget widget) {
        if (this.widgetTracker != null) {
            this.widgetTracker.widgetCreated(widget);
        }
    }
    
    public void widgetDisposed(final Widget widget) {
        if (this.widgetTracker != null) {
            this.widgetTracker.widgetDisposed(widget);
        }
    }
    
    static {
        instance = new WidgetSpy();
    }
    
    public interface WidgetTracker
    {
        default void widgetCreated(final Widget widget) {
        }
        
        default void widgetDisposed(final Widget widget) {
        }
    }
    
    public static class NonDisposedWidgetTracker implements WidgetTracker
    {
        private final Map<Widget, Error> nonDisposedWidgets;
        private final Set<Class<? extends Widget>> trackedTypes;
        
        public NonDisposedWidgetTracker() {
            this.nonDisposedWidgets = new LinkedHashMap<Widget, Error>();
            this.trackedTypes = new HashSet<Class<? extends Widget>>();
        }
        
        @Override
        public void widgetCreated(final Widget widget) {
            final boolean isTracked = this.isTracked(widget);
            if (isTracked) {
                final Error creationException = new Error("Created widget of type: " + widget.getClass().getSimpleName());
                this.nonDisposedWidgets.put(widget, creationException);
            }
        }
        
        @Override
        public void widgetDisposed(final Widget widget) {
            final boolean isTracked = this.isTracked(widget);
            if (isTracked) {
                this.nonDisposedWidgets.remove(widget);
            }
        }
        
        public Map<Widget, Error> getNonDisposedWidgets() {
            return Collections.unmodifiableMap((Map<? extends Widget, ? extends Error>)this.nonDisposedWidgets);
        }
        
        public void startTracking() {
            this.clearNonDisposedWidgets();
            WidgetSpy.getInstance().setWidgetTracker(this);
        }
        
        private void clearNonDisposedWidgets() {
            this.nonDisposedWidgets.clear();
        }
        
        public void stopTracking() {
            WidgetSpy.getInstance().setWidgetTracker(null);
        }
        
        public void setTrackingEnabled(final boolean enabled) {
            if (enabled) {
                this.startTracking();
            }
            else {
                this.stopTracking();
            }
        }
        
        public void setTrackedTypes(final List<Class<? extends Widget>> types) {
            this.trackedTypes.clear();
            this.trackedTypes.addAll(types);
        }
        
        private boolean isTracked(final Widget widget) {
            final boolean isTrackingAllTypes = this.trackedTypes.isEmpty();
            if (isTrackingAllTypes) {
                return true;
            }
            if (widget != null) {
                final Class<? extends Widget> widgetType = widget.getClass();
                if (this.trackedTypes.contains(widgetType)) {
                    return true;
                }
                for (final Class<? extends Widget> filteredType : this.trackedTypes) {
                    if (filteredType.isAssignableFrom(widgetType)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
