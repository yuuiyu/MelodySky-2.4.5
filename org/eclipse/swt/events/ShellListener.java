//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface ShellListener extends SWTEventListener
{
    void shellActivated(final ShellEvent p0);
    
    void shellClosed(final ShellEvent p0);
    
    void shellDeactivated(final ShellEvent p0);
    
    void shellDeiconified(final ShellEvent p0);
    
    void shellIconified(final ShellEvent p0);
    
    default ShellListener shellActivatedAdapter(final Consumer<ShellEvent> c) {
        return (ShellListener)new lIllII(this, (Consumer)c);
    }
    
    default ShellListener shellClosedAdapter(final Consumer<ShellEvent> c) {
        return (ShellListener)new lIlIll(this, (Consumer)c);
    }
    
    default ShellListener shellDeactivatedAdapter(final Consumer<ShellEvent> c) {
        return (ShellListener)new lllIl(this, (Consumer)c);
    }
    
    default ShellListener shellDeiconifiedAdapter(final Consumer<ShellEvent> c) {
        return (ShellListener)new llllI(this, (Consumer)c);
    }
    
    default ShellListener shellIconifiedAdapter(final Consumer<ShellEvent> c) {
        return (ShellListener)new lIIIIl(this, (Consumer)c);
    }
}
