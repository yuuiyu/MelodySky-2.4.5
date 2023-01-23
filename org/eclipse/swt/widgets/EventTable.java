//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;

class EventTable
{
    int[] types;
    Listener[] listeners;
    int level;
    static final int GROW_SIZE = 4;
    
    public Listener[] getListeners(final int eventType) {
        if (this.types == null) {
            return new Listener[0];
        }
        int count = 0;
        for (final int type : this.types) {
            if (type == eventType) {
                ++count;
            }
        }
        if (count == 0) {
            return new Listener[0];
        }
        final Listener[] result = new Listener[count];
        count = 0;
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == eventType) {
                result[count++] = this.listeners[i];
            }
        }
        return result;
    }
    
    public void hook(final int eventType, final Listener listener) {
        if (this.types == null) {
            this.types = new int[4];
        }
        if (this.listeners == null) {
            this.listeners = new Listener[4];
        }
        final int length = this.types.length;
        int index;
        for (index = length - 1; index >= 0 && this.types[index] == 0; --index) {}
        if (++index == length) {
            final int[] newTypes = new int[length + 4];
            System.arraycopy(this.types, 0, newTypes, 0, length);
            this.types = newTypes;
            final Listener[] newListeners = new Listener[length + 4];
            System.arraycopy(this.listeners, 0, newListeners, 0, length);
            this.listeners = newListeners;
        }
        this.types[index] = eventType;
        this.listeners[index] = listener;
    }
    
    public boolean hooks(final int eventType) {
        if (this.types == null) {
            return false;
        }
        for (final int type : this.types) {
            if (type == eventType) {
                return true;
            }
        }
        return false;
    }
    
    public void sendEvent(final Event event) {
        if (this.types == null) {
            return;
        }
        this.level += ((this.level >= 0) ? 1 : -1);
        try (final ExceptionStash exceptions = new ExceptionStash()) {
            for (int i = 0; i < this.types.length; ++i) {
                if (event.type == 0) {
                    exceptions.close();
                    return;
                }
                if (this.types[i] == event.type) {
                    final Listener listener = this.listeners[i];
                    if (listener != null) {
                        try {
                            listener.handleEvent(event);
                        }
                        catch (Error | RuntimeException error) {
                            final Throwable t2;
                            final Throwable ex = t2;
                            exceptions.stash(ex);
                        }
                    }
                }
            }
        }
        finally {
            final boolean compact = this.level < 0;
            this.level -= ((this.level >= 0) ? 1 : -1);
            if (compact && this.level == 0) {
                int index = 0;
                for (int j = 0; j < this.types.length; ++j) {
                    if (this.types[j] != 0) {
                        this.types[index] = this.types[j];
                        this.listeners[index] = this.listeners[j];
                        ++index;
                    }
                }
                for (int j = index; j < this.types.length; ++j) {
                    this.types[j] = 0;
                    this.listeners[j] = null;
                }
            }
        }
    }
    
    public int size() {
        if (this.types == null) {
            return 0;
        }
        int count = 0;
        for (final int type : this.types) {
            if (type != 0) {
                ++count;
            }
        }
        return count;
    }
    
    void remove(int index) {
        if (this.level == 0) {
            final int end = this.types.length - 1;
            System.arraycopy(this.types, index + 1, this.types, index, end - index);
            System.arraycopy(this.listeners, index + 1, this.listeners, index, end - index);
            index = end;
        }
        else if (this.level > 0) {
            this.level = -this.level;
        }
        this.types[index] = 0;
        this.listeners[index] = null;
    }
    
    public void unhook(final int eventType, final Listener listener) {
        if (this.types == null) {
            return;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == eventType && this.listeners[i] == listener) {
                this.remove(i);
                return;
            }
        }
    }
    
    public void unhook(final int eventType, final SWTEventListener listener) {
        if (this.types == null) {
            return;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == eventType && this.listeners[i] instanceof TypedListener) {
                final TypedListener typedListener = (TypedListener)this.listeners[i];
                if (typedListener.getEventListener() == listener) {
                    this.remove(i);
                    return;
                }
            }
        }
    }
}
