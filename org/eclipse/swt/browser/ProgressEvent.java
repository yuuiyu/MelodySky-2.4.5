//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class ProgressEvent extends TypedEvent
{
    public int current;
    public int total;
    static final long serialVersionUID = 3977018427045393972L;
    
    public ProgressEvent(final Widget widget) {
        super(widget);
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " current=" + this.current + " total=" + this.total;
    }
}
