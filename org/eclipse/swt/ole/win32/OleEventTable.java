//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

class OleEventTable
{
    int[] types;
    OleListener[] handlers;
    
    void hook(final int eventType, final OleListener handler) {
        if (this.types == null) {
            this.types = new int[4];
        }
        if (this.handlers == null) {
            this.handlers = new OleListener[4];
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == 0) {
                this.types[i] = eventType;
                this.handlers[i] = handler;
                return;
            }
        }
        final int size = this.types.length;
        final int[] newTypes = new int[size + 4];
        final OleListener[] newHandlers = new OleListener[size + 4];
        System.arraycopy(this.types, 0, newTypes, 0, size);
        System.arraycopy(this.handlers, 0, newHandlers, 0, size);
        this.types = newTypes;
        this.handlers = newHandlers;
        this.types[size] = eventType;
        this.handlers[size] = handler;
    }
    
    boolean hooks(final int eventType) {
        if (this.handlers == null) {
            return false;
        }
        for (final int type : this.types) {
            if (type == eventType) {
                return true;
            }
        }
        return false;
    }
    
    void sendEvent(final OleEvent event) {
        if (this.handlers == null) {
            return;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == event.type) {
                final OleListener listener = this.handlers[i];
                if (listener != null) {
                    listener.handleEvent(event);
                }
            }
        }
    }
    
    void unhook(final int eventType, final OleListener handler) {
        if (this.handlers == null) {
            return;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == eventType && this.handlers[i] == handler) {
                this.types[i] = 0;
                this.handlers[i] = null;
                return;
            }
        }
    }
    
    boolean hasEntries() {
        for (final int type : this.types) {
            if (type != 0) {
                return true;
            }
        }
        return false;
    }
}
