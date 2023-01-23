//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import java.util.*;

public final class BorderData
{
    private final Map<Control, Point> cachedSize;
    public int hHint;
    public int wHint;
    public int region;
    
    public BorderData() {
        this.cachedSize = new IdentityHashMap<Control, Point>(1);
        this.hHint = -1;
        this.wHint = -1;
        this.region = 16777216;
    }
    
    public BorderData(final int region) {
        this.cachedSize = new IdentityHashMap<Control, Point>(1);
        this.hHint = -1;
        this.wHint = -1;
        this.region = 16777216;
        this.region = region;
    }
    
    public BorderData(final int region, final int widthHint, final int heightHint) {
        this.cachedSize = new IdentityHashMap<Control, Point>(1);
        this.hHint = -1;
        this.wHint = -1;
        this.region = 16777216;
        this.region = region;
        this.wHint = widthHint;
        this.hHint = heightHint;
    }
    
    Point getSize(final Control control) {
        return this.cachedSize.computeIfAbsent(control, c -> c.computeSize(this.wHint, this.hHint, true));
    }
    
    Point computeSize(final Control control, int wHint, int hHint, final boolean changed) {
        if (wHint == -1) {
            wHint = this.wHint;
        }
        if (hHint == -1) {
            hHint = this.hHint;
        }
        return control.computeSize(wHint, hHint, changed);
    }
    
    void flushCache(final Control control) {
        this.cachedSize.remove(control);
    }
    
    @Override
    public String toString() {
        return "BorderData [region=" + getRegionString(this.region) + ", hHint=" + this.hHint + ", wHint=" + this.wHint;
    }
    
    static String getRegionString(final int region) {
        switch (region) {
            case 128: {
                return "SWT.TOP";
            }
            case 131072: {
                return "SWT.RIGHT";
            }
            case 1024: {
                return "SWT.BOTTOM";
            }
            case 16384: {
                return "SWT.LEFT";
            }
            case 16777216: {
                return "SWT.CENTER";
            }
            default: {
                return "SWT.NONE";
            }
        }
    }
    
    int getRegion() {
        switch (this.region) {
            case 128:
            case 1024:
            case 16384:
            case 131072:
            case 16777216: {
                return this.region;
            }
            default: {
                return 0;
            }
        }
    }
}
