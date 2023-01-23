//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.graphics.*;

public final class RowData
{
    public int width;
    public int height;
    public boolean exclude;
    
    public RowData() {
        this.width = -1;
        this.height = -1;
        this.exclude = false;
    }
    
    public RowData(final int width, final int height) {
        this.width = -1;
        this.height = -1;
        this.exclude = false;
        this.width = width;
        this.height = height;
    }
    
    public RowData(final Point point) {
        this(point.x, point.y);
    }
    
    String getName() {
        final String string = this.getClass().getName();
        final int index = string.lastIndexOf(46);
        if (index == -1) {
            return string;
        }
        return string.substring(index + 1, string.length());
    }
    
    @Override
    public String toString() {
        String string = this.getName() + " {";
        if (this.width != -1) {
            string = string + "width=" + this.width + " ";
        }
        if (this.height != -1) {
            string = string + "height=" + this.height + " ";
        }
        if (this.exclude) {
            string = string + "exclude=" + this.exclude + " ";
        }
        string = string.trim();
        string += "}";
        return string;
    }
}
