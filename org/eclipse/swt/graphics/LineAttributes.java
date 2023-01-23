//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

public class LineAttributes
{
    public float width;
    public int style;
    public int cap;
    public int join;
    public float[] dash;
    public float dashOffset;
    public float miterLimit;
    
    public LineAttributes(final float width) {
        this(width, 1, 1, 1, null, 0.0f, 10.0f);
    }
    
    public LineAttributes(final float width, final int cap, final int join) {
        this(width, cap, join, 1, null, 0.0f, 10.0f);
    }
    
    public LineAttributes(final float width, final int cap, final int join, final int style, final float[] dash, final float dashOffset, final float miterLimit) {
        this.width = width;
        this.cap = cap;
        this.join = join;
        this.style = style;
        this.dash = dash;
        this.dashOffset = dashOffset;
        this.miterLimit = miterLimit;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof LineAttributes)) {
            return false;
        }
        final LineAttributes p = (LineAttributes)object;
        if (p.width != this.width) {
            return false;
        }
        if (p.cap != this.cap) {
            return false;
        }
        if (p.join != this.join) {
            return false;
        }
        if (p.style != this.style) {
            return false;
        }
        if (p.dashOffset != this.dashOffset) {
            return false;
        }
        if (p.miterLimit != this.miterLimit) {
            return false;
        }
        if (p.dash != null && this.dash != null) {
            if (p.dash.length != this.dash.length) {
                return false;
            }
            for (int i = 0; i < this.dash.length; ++i) {
                if (p.dash[i] != this.dash[i]) {
                    return false;
                }
            }
        }
        else if (p.dash != null || this.dash != null) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = Float.floatToIntBits(this.width);
        hashCode = 31 * hashCode + this.cap;
        hashCode = 31 * hashCode + this.join;
        hashCode = 31 * hashCode + this.style;
        hashCode = 31 * hashCode + Float.floatToIntBits(this.dashOffset);
        hashCode = 31 * hashCode + Float.floatToIntBits(this.miterLimit);
        if (this.dash != null) {
            for (final float element : this.dash) {
                hashCode = 31 * hashCode + Float.floatToIntBits(element);
            }
        }
        return hashCode;
    }
}
