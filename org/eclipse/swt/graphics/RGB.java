//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import java.io.*;
import org.eclipse.swt.*;

public final class RGB implements Serializable
{
    public int red;
    public int green;
    public int blue;
    static final long serialVersionUID = 3258415023461249074L;
    
    public RGB(final int red, final int green, final int blue) {
        if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
            SWT.error(5);
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public RGB(float hue, final float saturation, final float brightness) {
        if (hue < 0.0f || hue > 360.0f || saturation < 0.0f || saturation > 1.0f || brightness < 0.0f || brightness > 1.0f) {
            SWT.error(5);
        }
        float r = 0.0f;
        float b;
        float g;
        if (saturation == 0.0f) {
            b = brightness;
            g = brightness;
            r = brightness;
        }
        else {
            if (hue == 360.0f) {
                hue = 0.0f;
            }
            hue /= 60.0f;
            final int i = (int)hue;
            final float f = hue - i;
            final float p = brightness * (1.0f - saturation);
            final float q = brightness * (1.0f - saturation * f);
            final float t = brightness * (1.0f - saturation * (1.0f - f));
            switch (i) {
                case 0: {
                    r = brightness;
                    g = t;
                    b = p;
                    break;
                }
                case 1: {
                    r = q;
                    g = brightness;
                    b = p;
                    break;
                }
                case 2: {
                    r = p;
                    g = brightness;
                    b = t;
                    break;
                }
                case 3: {
                    r = p;
                    g = q;
                    b = brightness;
                    break;
                }
                case 4: {
                    r = t;
                    g = p;
                    b = brightness;
                    break;
                }
                default: {
                    r = brightness;
                    g = p;
                    b = q;
                    break;
                }
            }
        }
        this.red = (int)(r * 255.0f + 0.5);
        this.green = (int)(g * 255.0f + 0.5);
        this.blue = (int)(b * 255.0f + 0.5);
    }
    
    public float[] getHSB() {
        final float r = this.red / 255.0f;
        final float g = this.green / 255.0f;
        final float b = this.blue / 255.0f;
        final float max = Math.max(Math.max(r, g), b);
        final float min = Math.min(Math.min(r, g), b);
        final float delta = max - min;
        float hue = 0.0f;
        final float brightness = max;
        final float saturation = (max == 0.0f) ? 0.0f : ((max - min) / max);
        if (delta != 0.0f) {
            if (r == max) {
                hue = (g - b) / delta;
            }
            else if (g == max) {
                hue = 2.0f + (b - r) / delta;
            }
            else {
                hue = 4.0f + (r - g) / delta;
            }
            hue *= 60.0f;
            if (hue < 0.0f) {
                hue += 360.0f;
            }
        }
        return new float[] { hue, saturation, brightness };
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof RGB)) {
            return false;
        }
        final RGB rgb = (RGB)object;
        return rgb.red == this.red && rgb.green == this.green && rgb.blue == this.blue;
    }
    
    @Override
    public int hashCode() {
        return this.blue << 16 | this.green << 8 | this.red;
    }
    
    @Override
    public String toString() {
        return "RGB {" + this.red + ", " + this.green + ", " + this.blue;
    }
}
