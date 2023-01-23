//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import java.io.*;
import org.eclipse.swt.*;

public final class RGBA implements Serializable
{
    public final RGB rgb;
    public int alpha;
    static final long serialVersionUID = 1049467103126495855L;
    
    public RGBA(final int red, final int green, final int blue, final int alpha) {
        if (alpha > 255 || alpha < 0) {
            SWT.error(5);
        }
        this.rgb = new RGB(red, green, blue);
        this.alpha = alpha;
    }
    
    public RGBA(final float hue, final float saturation, final float brightness, final float alpha) {
        if (alpha > 255.0f || alpha < 0.0f) {
            SWT.error(5);
        }
        this.rgb = new RGB(hue, saturation, brightness);
        this.alpha = (int)(alpha + 0.5);
    }
    
    public float[] getHSBA() {
        final float[] hsb = this.rgb.getHSB();
        return new float[] { hsb[0], hsb[1], hsb[2], (float)this.alpha };
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof RGBA)) {
            return false;
        }
        final RGBA rgba = (RGBA)object;
        return rgba.rgb.red == this.rgb.red && rgba.rgb.green == this.rgb.green && rgba.rgb.blue == this.rgb.blue && rgba.alpha == this.alpha;
    }
    
    @Override
    public int hashCode() {
        return this.alpha << 24 | this.rgb.blue << 16 | this.rgb.green << 8 | this.rgb.red;
    }
    
    @Override
    public String toString() {
        return "RGBA {" + this.rgb.red + ", " + this.rgb.green + ", " + this.rgb.blue + ", " + this.alpha;
    }
}
