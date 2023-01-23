//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;

public final class PaletteData
{
    public boolean isDirect;
    public RGB[] colors;
    public int redMask;
    public int greenMask;
    public int blueMask;
    public int redShift;
    public int greenShift;
    public int blueShift;
    
    public PaletteData(final RGB... colors) {
        if (colors == null) {
            SWT.error(4);
        }
        this.colors = colors;
        this.isDirect = false;
    }
    
    public PaletteData(final int redMask, final int greenMask, final int blueMask) {
        this.redMask = redMask;
        this.greenMask = greenMask;
        this.blueMask = blueMask;
        this.isDirect = true;
        this.redShift = this.shiftForMask(redMask);
        this.greenShift = this.shiftForMask(greenMask);
        this.blueShift = this.shiftForMask(blueMask);
    }
    
    public int getPixel(final RGB rgb) {
        if (rgb == null) {
            SWT.error(4);
        }
        if (this.isDirect) {
            int pixel = 0;
            pixel |= (((this.redShift < 0) ? (rgb.red << -this.redShift) : (rgb.red >>> this.redShift)) & this.redMask);
            pixel |= (((this.greenShift < 0) ? (rgb.green << -this.greenShift) : (rgb.green >>> this.greenShift)) & this.greenMask);
            pixel |= (((this.blueShift < 0) ? (rgb.blue << -this.blueShift) : (rgb.blue >>> this.blueShift)) & this.blueMask);
            return pixel;
        }
        for (int i = 0; i < this.colors.length; ++i) {
            if (this.colors[i].equals(rgb)) {
                return i;
            }
        }
        SWT.error(5);
        return 0;
    }
    
    public RGB getRGB(final int pixel) {
        if (this.isDirect) {
            int r = pixel & this.redMask;
            r = ((this.redShift < 0) ? (r >>> -this.redShift) : (r << this.redShift));
            int g = pixel & this.greenMask;
            g = ((this.greenShift < 0) ? (g >>> -this.greenShift) : (g << this.greenShift));
            int b = pixel & this.blueMask;
            b = ((this.blueShift < 0) ? (b >>> -this.blueShift) : (b << this.blueShift));
            return new RGB(r, g, b);
        }
        if (pixel < 0 || pixel >= this.colors.length) {
            SWT.error(5);
        }
        return this.colors[pixel];
    }
    
    public RGB[] getRGBs() {
        return this.colors;
    }
    
    int shiftForMask(final int mask) {
        for (int i = 31; i >= 0; --i) {
            if ((mask >> i & 0x1) != 0x0) {
                return 7 - i;
            }
        }
        return 32;
    }
}
