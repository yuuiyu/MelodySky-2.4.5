//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;

public final class Color extends Resource
{
    public int handle;
    int alpha;
    
    Color() {
        this.alpha = 255;
    }
    
    Color(final Device device) {
        super(device);
        this.alpha = 255;
    }
    
    public Color(final Device device, final int red, final int green, final int blue) {
        super(device);
        this.init(red, green, blue, this.alpha = 255);
        this.init();
    }
    
    public Color(final int red, final int green, final int blue) {
        this.init(red, green, blue, this.alpha = 255);
    }
    
    public Color(final Device device, final int red, final int green, final int blue, final int alpha) {
        super(device);
        this.alpha = 255;
        this.init(red, green, blue, alpha);
        this.init();
    }
    
    public Color(final int red, final int green, final int blue, final int alpha) {
        this.alpha = 255;
        this.init(red, green, blue, alpha);
    }
    
    public Color(final Device device, final RGB rgb) {
        super(device);
        this.alpha = 255;
        if (rgb == null) {
            SWT.error(4);
        }
        this.init(rgb.red, rgb.green, rgb.blue, 255);
        this.init();
    }
    
    public Color(final RGB rgb) {
        this.alpha = 255;
        if (rgb == null) {
            SWT.error(4);
        }
        this.init(rgb.red, rgb.green, rgb.blue, 255);
    }
    
    public Color(final Device device, final RGBA rgba) {
        super(device);
        this.alpha = 255;
        if (rgba == null) {
            SWT.error(4);
        }
        this.init(rgba.rgb.red, rgba.rgb.green, rgba.rgb.blue, rgba.alpha);
        this.init();
    }
    
    public Color(final RGBA rgba) {
        this.alpha = 255;
        if (rgba == null) {
            SWT.error(4);
        }
        this.init(rgba.rgb.red, rgba.rgb.green, rgba.rgb.blue, rgba.alpha);
    }
    
    public Color(final Device device, final RGB rgb, final int alpha) {
        super(device);
        this.alpha = 255;
        if (rgb == null) {
            SWT.error(4);
        }
        this.init(rgb.red, rgb.green, rgb.blue, alpha);
        this.init();
    }
    
    public Color(final RGB rgb, final int alpha) {
        this.alpha = 255;
        if (rgb == null) {
            SWT.error(4);
        }
        this.init(rgb.red, rgb.green, rgb.blue, alpha);
    }
    
    @Override
    void destroy() {
        this.handle = -1;
    }
    
    @Override
    public void dispose() {
        this.destroy();
        this.device = null;
    }
    
    @Override
    public Device getDevice() {
        if (this.device == null && this.handle != -1) {
            return Device.getDevice();
        }
        return super.getDevice();
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Color)) {
            return false;
        }
        final Color color = (Color)object;
        return !this.isDisposed() && !color.isDisposed() && (this.handle & 0xFFFFFF) == (color.handle & 0xFFFFFF) && this.alpha == color.alpha;
    }
    
    public int getAlpha() {
        return this.alpha;
    }
    
    public int getBlue() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return (this.handle & 0xFF0000) >> 16;
    }
    
    public int getGreen() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return (this.handle & 0xFF00) >> 8;
    }
    
    public int getRed() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return this.handle & 0xFF;
    }
    
    public RGB getRGB() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return new RGB(this.handle & 0xFF, (this.handle & 0xFF00) >> 8, (this.handle & 0xFF0000) >> 16);
    }
    
    public RGBA getRGBA() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return new RGBA(this.handle & 0xFF, (this.handle & 0xFF00) >> 8, (this.handle & 0xFF0000) >> 16, this.alpha);
    }
    
    @Override
    public int hashCode() {
        if (this.isDisposed()) {
            return 0;
        }
        return this.handle ^ this.alpha;
    }
    
    void init(final int red, final int green, final int blue, final int alpha) {
        if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0 || alpha > 255 || alpha < 0) {
            SWT.error(5);
        }
        this.handle = ((red & 0xFF) | (green & 0xFF) << 8 | (blue & 0xFF) << 16);
        this.alpha = alpha;
    }
    
    @Override
    void init() {
    }
    
    @Override
    public boolean isDisposed() {
        return this.handle == -1;
    }
    
    @Override
    public String toString() {
        if (this.isDisposed()) {
            return "Color {*DISPOSED*}";
        }
        return "Color {" + this.getRed() + ", " + this.getGreen() + ", " + this.getBlue() + ", " + this.getAlpha();
    }
    
    public static Color win32_new(final Device device, final int handle) {
        return win32_new(device, handle, 255);
    }
    
    public static Color win32_new(final Device device, final int handle, final int alpha) {
        final Color color = new Color(device);
        color.handle = handle;
        color.alpha = alpha;
        return color;
    }
}
