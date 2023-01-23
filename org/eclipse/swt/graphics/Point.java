//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import java.io.*;

public final class Point implements Serializable
{
    public int x;
    public int y;
    static final long serialVersionUID = 3257002163938146354L;
    
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Point)) {
            return false;
        }
        final Point p = (Point)object;
        return p.x == this.x && p.y == this.y;
    }
    
    @Override
    public int hashCode() {
        return this.x ^ this.y;
    }
    
    @Override
    public String toString() {
        return "Point {" + this.x + ", " + this.y;
    }
}
