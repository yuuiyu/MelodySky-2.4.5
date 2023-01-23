//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class WindowEvent extends TypedEvent
{
    public boolean required;
    public Browser browser;
    public Point location;
    public Point size;
    public boolean addressBar;
    public boolean menuBar;
    public boolean statusBar;
    public boolean toolBar;
    static final long serialVersionUID = 3617851997387174969L;
    
    public WindowEvent(final Widget widget) {
        super(widget);
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " required=" + this.required + " browser=" + this.browser + " location=" + this.location + " size=" + this.size + " addressBar=" + this.addressBar + " menuBar=" + this.menuBar + " statusBar=" + this.statusBar + " toolBar=" + this.toolBar;
    }
}
