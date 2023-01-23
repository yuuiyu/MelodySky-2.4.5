//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class AuthenticationEvent extends TypedEvent
{
    public String location;
    public String user;
    public String password;
    public boolean doit;
    static final long serialVersionUID = -8322331206780057921L;
    
    public AuthenticationEvent(final Widget widget) {
        super(widget);
        this.doit = true;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " name=" + this.user + " password=" + this.password + " location=" + this.location;
    }
}
