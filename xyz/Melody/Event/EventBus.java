//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event;

import java.lang.annotation.*;
import java.util.concurrent.*;
import java.lang.reflect.*;
import java.util.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.module.*;
import xyz.Melody.Utils.*;
import java.lang.invoke.*;

public class EventBus
{
    private ConcurrentHashMap<Class<? extends Event>, List<Handler>> registry;
    private final Comparator<Handler> comparator;
    private final MethodHandles.Lookup lookup;
    private static final EventBus instance;
    
    public EventBus() {
        this.registry = new ConcurrentHashMap<Class<? extends Event>, List<Handler>>();
        this.comparator = ((h, h1) -> Byte.compare(h.priority, h1.priority));
        this.lookup = MethodHandles.lookup();
    }
    
    public static EventBus getInstance() {
        return EventBus.instance;
    }
    
    public void register(final Object... objs) {
        for (final Object obj : objs) {
            for (final Method m : obj.getClass().getDeclaredMethods()) {
                if (m.getParameterCount() == 1 && m.isAnnotationPresent(EventHandler.class)) {
                    final Class<?> eventClass = m.getParameterTypes()[0];
                    if (!this.registry.containsKey(eventClass)) {
                        this.registry.put((Class<? extends Event>)eventClass, new CopyOnWriteArrayList<Handler>());
                    }
                    this.registry.get(eventClass).add(new Handler(m, obj, m.getDeclaredAnnotation(EventHandler.class).priority()));
                    this.registry.get(eventClass).sort(this.comparator);
                }
            }
        }
    }
    
    public void unregister(final Object... objs) {
        for (final Object obj : objs) {
            for (final List<Handler> list : this.registry.values()) {
                for (final Handler data : list) {
                    if (data.parent != obj) {
                        continue;
                    }
                    list.remove(data);
                }
            }
        }
    }
    
    public <E extends Event> E call(final E event) {
        final boolean whiteListedEvents = event instanceof EventTick || event instanceof EventPreUpdate || event instanceof EventPostUpdate;
        final List<Handler> list = this.registry.get(event.getClass());
        if (list != null && !list.isEmpty()) {
            for (final Handler data : list) {
                try {
                    if (list instanceof Module) {
                        if (((Module)list).isEnabled()) {
                            if (whiteListedEvents) {
                                Helper.mc.mcProfiler.startSection(((Module)list).getName());
                            }
                            if (whiteListedEvents) {
                                Helper.mc.mcProfiler.endSection();
                            }
                        }
                    }
                    else {
                        if (whiteListedEvents) {
                            Helper.mc.mcProfiler.startSection("non module");
                        }
                        if (whiteListedEvents) {
                            Helper.mc.mcProfiler.endSection();
                        }
                    }
                    data.handler.invokeExact(data.parent, (Event)event);
                }
                catch (Throwable e1) {
                    e1.printStackTrace();
                }
            }
        }
        return event;
    }
    
    static {
        instance = new EventBus();
    }
    
    private class Handler
    {
        private MethodHandle handler;
        private Object parent;
        private byte priority;
        
        public Handler(final Method method, final Object parent, final byte priority) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            MethodHandle m = null;
            try {
                m = EventBus.this.lookup.unreflect(method);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (m != null) {
                this.handler = m.asType(m.type().changeParameterType(0, Object.class).changeParameterType(1, Event.class));
            }
            this.parent = parent;
            this.priority = priority;
        }
    }
}
