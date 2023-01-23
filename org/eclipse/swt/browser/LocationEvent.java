//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class LocationEvent extends TypedEvent
{
    public String location;
    public boolean top;
    public boolean doit;
    static final long serialVersionUID = 3906644198244299574L;
    
    public LocationEvent(final Widget widget) {
        super(widget);
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " location=" + this.location + " top=" + this.top + " doit=" + this.doit;
    }
}
