//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class CLayoutData
{
    int defaultWidth;
    int defaultHeight;
    int currentWhint;
    int currentHhint;
    int currentWidth;
    int currentHeight;
    
    CLayoutData() {
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
    }
    
    Point computeSize(final Control control, final int wHint, final int hHint, final boolean flushCache) {
        if (flushCache) {
            this.flushCache();
        }
        if (wHint == -1 && hHint == -1) {
            if (this.defaultWidth == -1 || this.defaultHeight == -1) {
                final Point size = control.computeSize(wHint, hHint, flushCache);
                this.defaultWidth = size.x;
                this.defaultHeight = size.y;
            }
            return new Point(this.defaultWidth, this.defaultHeight);
        }
        if (this.currentWidth == -1 || this.currentHeight == -1 || wHint != this.currentWhint || hHint != this.currentHhint) {
            final Point size = control.computeSize(wHint, hHint, flushCache);
            this.currentWhint = wHint;
            this.currentHhint = hHint;
            this.currentWidth = size.x;
            this.currentHeight = size.y;
        }
        return new Point(this.currentWidth, this.currentHeight);
    }
    
    void flushCache() {
        final int n = -1;
        this.defaultHeight = -1;
        this.defaultWidth = -1;
        final int n2 = -1;
        this.currentHeight = -1;
        this.currentWidth = -1;
    }
}
