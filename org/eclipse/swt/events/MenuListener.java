//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface MenuListener extends SWTEventListener
{
    void menuHidden(final MenuEvent p0);
    
    void menuShown(final MenuEvent p0);
    
    default MenuListener menuHiddenAdapter(final Consumer<MenuEvent> c) {
        return (MenuListener)new lIlIII(this, (Consumer)c);
    }
    
    default MenuListener menuShownAdapter(final Consumer<MenuEvent> c) {
        return (MenuListener)new lIlIIl(this, (Consumer)c);
    }
}
