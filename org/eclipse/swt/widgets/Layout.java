//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;

public abstract class Layout
{
    protected abstract Point computeSize(final Composite p0, final int p1, final int p2, final boolean p3);
    
    protected boolean flushCache(final Control control) {
        return false;
    }
    
    protected abstract void layout(final Composite p0, final boolean p1);
}
