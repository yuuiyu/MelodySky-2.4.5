//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface LocationListener extends SWTEventListener
{
    void changing(final LocationEvent p0);
    
    void changed(final LocationEvent p0);
    
    default LocationListener changingAdapter(final Consumer<LocationEvent> c) {
        return (LocationListener)new lllI(this, (Consumer)c);
    }
    
    default LocationListener changedAdapter(final Consumer<LocationEvent> c) {
        return (LocationListener)new lIlIl(this, (Consumer)c);
    }
}
