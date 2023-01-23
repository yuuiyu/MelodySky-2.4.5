//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface ProgressListener extends SWTEventListener
{
    void changed(final ProgressEvent p0);
    
    void completed(final ProgressEvent p0);
    
    default ProgressListener changedAdapter(final Consumer<ProgressEvent> c) {
        return (ProgressListener)new lIIlI(this, (Consumer)c);
    }
    
    default ProgressListener completedAdapter(final Consumer<ProgressEvent> c) {
        return (ProgressListener)new llIl(this, (Consumer)c);
    }
}
