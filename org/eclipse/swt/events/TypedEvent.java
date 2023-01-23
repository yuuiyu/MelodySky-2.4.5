//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import java.util.*;
import org.eclipse.swt.widgets.*;

public class TypedEvent extends EventObject
{
    public Display display;
    public Widget widget;
    public int time;
    public Object data;
    static final long serialVersionUID = 3257285846578377524L;
    
    public TypedEvent(final Object object) {
        super(object);
    }
    
    public TypedEvent(final Event e) {
        super(e.widget);
        this.display = e.display;
        this.widget = e.widget;
        this.time = e.time;
        this.data = e.data;
    }
    
    String getName() {
        final String string = this.getClass().getName();
        final int index = string.lastIndexOf(46);
        if (index == -1) {
            return string;
        }
        return string.substring(index + 1, string.length());
    }
    
    @Override
    public String toString() {
        return this.getName() + "{" + this.widget + " time=" + this.time + " data=" + this.data;
    }
}
