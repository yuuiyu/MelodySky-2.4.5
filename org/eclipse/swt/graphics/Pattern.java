//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gdip.*;

public class Pattern extends Resource
{
    public long handle;
    
    public Pattern(final Device device, final Image image) {
        super(device);
        if (image == null) {
            SWT.error(4);
        }
        if (image.isDisposed()) {
            SWT.error(5);
        }
        this.device.checkGDIP();
        final long[] gdipImage = image.createGdipImage();
        final long img = gdipImage[0];
        final int width = Gdip.Image_GetWidth(img);
        final int height = Gdip.Image_GetHeight(img);
        this.handle = Gdip.TextureBrush_new(img, 0, 0.0f, 0.0f, (float)width, (float)height);
        Gdip.Bitmap_delete(img);
        if (gdipImage[1] != 0L) {
            final long hHeap = OS.GetProcessHeap();
            OS.HeapFree(hHeap, 0, gdipImage[1]);
        }
        if (this.handle == 0L) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Pattern(final Device device, final float x1, final float y1, final float x2, final float y2, final Color color1, final Color color2) {
        this(device, x1, y1, x2, y2, color1, 255, color2, 255);
    }
    
    public Pattern(final Device device, float x1, float y1, float x2, float y2, final Color color1, final int alpha1, final Color color2, final int alpha2) {
        super(device);
        x1 = DPIUtil.autoScaleUp(x1);
        y1 = DPIUtil.autoScaleUp(y1);
        x2 = DPIUtil.autoScaleUp(x2);
        y2 = DPIUtil.autoScaleUp(y2);
        if (color1 == null) {
            SWT.error(4);
        }
        if (color1.isDisposed()) {
            SWT.error(5);
        }
        if (color2 == null) {
            SWT.error(4);
        }
        if (color2.isDisposed()) {
            SWT.error(5);
        }
        this.device.checkGDIP();
        final int colorRef1 = color1.handle;
        final int foreColor = (alpha1 & 0xFF) << 24 | (colorRef1 >> 16 & 0xFF) | (colorRef1 & 0xFF00) | (colorRef1 & 0xFF) << 16;
        if (x1 == x2 && y1 == y2) {
            this.handle = Gdip.SolidBrush_new(foreColor);
            if (this.handle == 0L) {
                SWT.error(2);
            }
        }
        else {
            final int colorRef2 = color2.handle;
            final int backColor = (alpha2 & 0xFF) << 24 | (colorRef2 >> 16 & 0xFF) | (colorRef2 & 0xFF00) | (colorRef2 & 0xFF) << 16;
            final PointF p1 = new PointF();
            p1.X = x1;
            p1.Y = y1;
            final PointF p2 = new PointF();
            p2.X = x2;
            p2.Y = y2;
            this.handle = Gdip.LinearGradientBrush_new(p1, p2, foreColor, backColor);
            if (this.handle == 0L) {
                SWT.error(2);
            }
            if (alpha1 != 255 || alpha2 != 255) {
                final int a = (int)((alpha1 & 0xFF) * 0.5f + (alpha2 & 0xFF) * 0.5f);
                final int r = (int)(((colorRef1 & 0xFF) >> 0) * 0.5f + ((colorRef2 & 0xFF) >> 0) * 0.5f);
                final int g = (int)(((colorRef1 & 0xFF00) >> 8) * 0.5f + ((colorRef2 & 0xFF00) >> 8) * 0.5f);
                final int b = (int)(((colorRef1 & 0xFF0000) >> 16) * 0.5f + ((colorRef2 & 0xFF0000) >> 16) * 0.5f);
                final int midColor = a << 24 | r << 16 | g << 8 | b;
                Gdip.LinearGradientBrush_SetInterpolationColors(this.handle, new int[] { foreColor, midColor, backColor }, new float[] { 0.0f, 0.5f, 1.0f }, 3);
            }
        }
        this.init();
    }
    
    @Override
    void destroy() {
        final int type = Gdip.Brush_GetType(this.handle);
        switch (type) {
            case 0: {
                Gdip.SolidBrush_delete(this.handle);
                break;
            }
            case 1: {
                Gdip.HatchBrush_delete(this.handle);
                break;
            }
            case 4: {
                Gdip.LinearGradientBrush_delete(this.handle);
                break;
            }
            case 2: {
                Gdip.TextureBrush_delete(this.handle);
                break;
            }
        }
        this.handle = 0L;
    }
    
    @Override
    public boolean isDisposed() {
        return this.handle == 0L;
    }
    
    @Override
    public String toString() {
        if (this.isDisposed()) {
            return "Pattern {*DISPOSED*}";
        }
        return "Pattern {" + this.handle;
    }
}
