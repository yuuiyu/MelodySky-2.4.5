//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface VisibilityWindowListener extends SWTEventListener
{
    void hide(final WindowEvent p0);
    
    void show(final WindowEvent p0);
    
    default VisibilityWindowListener hideAdapter(final Consumer<WindowEvent> c) {
        return (VisibilityWindowListener)new lIlII(this, (Consumer)c);
    }
    
    default VisibilityWindowListener showAdapter(final Consumer<WindowEvent> c) {
        return (VisibilityWindowListener)new llll(this, (Consumer)c);
    }
}
