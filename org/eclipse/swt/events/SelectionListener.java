//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface SelectionListener extends SWTEventListener
{
    void widgetSelected(final SelectionEvent p0);
    
    void widgetDefaultSelected(final SelectionEvent p0);
    
    default SelectionListener widgetSelectedAdapter(final Consumer<SelectionEvent> c) {
        return (SelectionListener)new llIIIl(this, (Consumer)c);
    }
    
    default SelectionListener widgetDefaultSelectedAdapter(final Consumer<SelectionEvent> c) {
        return (SelectionListener)new lIIlll(this, (Consumer)c);
    }
}
