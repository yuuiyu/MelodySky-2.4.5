//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gdip.*;
import org.eclipse.swt.internal.win32.*;

public final class GC extends Resource
{
    public long handle;
    Drawable drawable;
    GCData data;
    static final int FOREGROUND = 1;
    static final int BACKGROUND = 2;
    static final int FONT = 4;
    static final int LINE_STYLE = 8;
    static final int LINE_WIDTH = 16;
    static final int LINE_CAP = 32;
    static final int LINE_JOIN = 64;
    static final int LINE_MITERLIMIT = 128;
    static final int FOREGROUND_TEXT = 256;
    static final int BACKGROUND_TEXT = 512;
    static final int BRUSH = 1024;
    static final int PEN = 2048;
    static final int NULL_BRUSH = 4096;
    static final int NULL_PEN = 8192;
    static final int DRAW_OFFSET = 16384;
    static final int DRAW = 22777;
    static final int FILL = 9218;
    static final float[] LINE_DOT_ZERO;
    static final float[] LINE_DASH_ZERO;
    static final float[] LINE_DASHDOT_ZERO;
    static final float[] LINE_DASHDOTDOT_ZERO;
    
    public GC() {
    }
    
    public GC(final Drawable drawable) {
        this(drawable, 0);
    }
    
    public GC(final Drawable drawable, final int style) {
        if (drawable == null) {
            SWT.error(4);
        }
        final GCData data = new GCData();
        data.style = checkStyle(style);
        final long hDC = drawable.internal_new_GC(data);
        Device device = data.device;
        if (device == null) {
            device = Device.getDevice();
        }
        if (device == null) {
            SWT.error(4);
        }
        final GCData gcData = data;
        final Device device2 = device;
        gcData.device = device2;
        this.device = device2;
        this.init(drawable, data, hDC);
        this.init();
    }
    
    static int checkStyle(int style) {
        if ((style & 0x2000000) != 0x0) {
            style &= 0xFBFFFFFF;
        }
        return style & 0x6000000;
    }
    
    void checkGC(final int mask) {
        int state = this.data.state;
        if ((state & mask) == mask) {
            return;
        }
        state = ((state ^ mask) & mask);
        final GCData data23;
        final GCData data = data23 = this.data;
        data23.state |= mask;
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            long pen = this.data.gdipPen;
            final float width = this.data.lineWidth;
            if ((state & 0x1) != 0x0 || (pen == 0L && (state & 0xF8) != 0x0)) {
                if (this.data.gdipFgBrush != 0L) {
                    Gdip.SolidBrush_delete(this.data.gdipFgBrush);
                }
                this.data.gdipFgBrush = 0L;
                final Pattern pattern = this.data.foregroundPattern;
                long brush;
                if (pattern != null) {
                    if (this.data.alpha == 255) {
                        brush = pattern.handle;
                    }
                    else {
                        brush = ((this.data.gdipFgPatternBrushAlpha != 0L) ? Gdip.Brush_Clone(this.data.gdipFgPatternBrushAlpha) : createAlphaTextureBrush(pattern.handle, this.data.alpha));
                        this.data.gdipFgPatternBrushAlpha = brush;
                    }
                    if ((this.data.style & 0x8000000) != 0x0) {
                        switch (Gdip.Brush_GetType(brush)) {
                            case 2: {
                                brush = Gdip.Brush_Clone(brush);
                                if (brush == 0L) {
                                    SWT.error(2);
                                }
                                Gdip.TextureBrush_ScaleTransform(brush, -1.0f, 1.0f, 0);
                                this.data.gdipFgBrush = brush;
                                break;
                            }
                        }
                    }
                }
                else {
                    final int foreground = this.data.foreground;
                    final int color = this.data.alpha << 24 | (foreground >> 16 & 0xFF) | (foreground & 0xFF00) | (foreground & 0xFF) << 16;
                    brush = Gdip.SolidBrush_new(color);
                    if (brush == 0L) {
                        SWT.error(2);
                    }
                    this.data.gdipFgBrush = brush;
                }
                if (pen != 0L) {
                    Gdip.Pen_SetBrush(pen, brush);
                }
                else {
                    final GCData data2 = this.data;
                    final long pen_new = Gdip.Pen_new(brush, width);
                    data2.gdipPen = pen_new;
                    pen = pen_new;
                }
            }
            if ((state & 0x10) != 0x0) {
                Gdip.Pen_SetWidth(pen, width);
                switch (this.data.lineStyle) {
                    case 6: {
                        state |= 0x8;
                        break;
                    }
                }
            }
            if ((state & 0x8) != 0x0) {
                float[] dashes = null;
                float dashOffset = 0.0f;
                int dashStyle = 0;
                switch (this.data.lineStyle) {
                    case 3: {
                        dashStyle = 2;
                        if (width == 0.0f) {
                            dashes = GC.LINE_DOT_ZERO;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        dashStyle = 1;
                        if (width == 0.0f) {
                            dashes = GC.LINE_DASH_ZERO;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        dashStyle = 3;
                        if (width == 0.0f) {
                            dashes = GC.LINE_DASHDOT_ZERO;
                            break;
                        }
                        break;
                    }
                    case 5: {
                        dashStyle = 4;
                        if (width == 0.0f) {
                            dashes = GC.LINE_DASHDOTDOT_ZERO;
                            break;
                        }
                        break;
                    }
                    case 6: {
                        if (this.data.lineDashes != null) {
                            dashOffset = this.data.lineDashesOffset / Math.max(1.0f, width);
                            dashes = new float[this.data.lineDashes.length * 2];
                            for (int i = 0; i < this.data.lineDashes.length; ++i) {
                                final float dash = this.data.lineDashes[i] / Math.max(1.0f, width);
                                dashes[i] = dash;
                                dashes[i + this.data.lineDashes.length] = dash;
                            }
                            break;
                        }
                        break;
                    }
                }
                if (dashes != null) {
                    Gdip.Pen_SetDashPattern(pen, dashes, dashes.length);
                    Gdip.Pen_SetDashStyle(pen, 5);
                    Gdip.Pen_SetDashOffset(pen, dashOffset);
                }
                else {
                    Gdip.Pen_SetDashStyle(pen, dashStyle);
                }
            }
            if ((state & 0x80) != 0x0) {
                Gdip.Pen_SetMiterLimit(pen, this.data.lineMiterLimit);
            }
            if ((state & 0x40) != 0x0) {
                int joinStyle = 0;
                switch (this.data.lineJoin) {
                    case 1: {
                        joinStyle = 0;
                        break;
                    }
                    case 3: {
                        joinStyle = 1;
                        break;
                    }
                    case 2: {
                        joinStyle = 2;
                        break;
                    }
                }
                Gdip.Pen_SetLineJoin(pen, joinStyle);
            }
            if ((state & 0x20) != 0x0) {
                int dashCap = 0;
                int capStyle = 0;
                switch (this.data.lineCap) {
                    case 1: {
                        capStyle = 0;
                        break;
                    }
                    case 2: {
                        capStyle = 2;
                        dashCap = 2;
                        break;
                    }
                    case 3: {
                        capStyle = 1;
                        break;
                    }
                }
                Gdip.Pen_SetLineCap(pen, capStyle, capStyle, dashCap);
            }
            if ((state & 0x2) != 0x0) {
                if (this.data.gdipBgBrush != 0L) {
                    Gdip.SolidBrush_delete(this.data.gdipBgBrush);
                }
                this.data.gdipBgBrush = 0L;
                final Pattern pattern2 = this.data.backgroundPattern;
                if (pattern2 != null) {
                    if (this.data.alpha == 255) {
                        this.data.gdipBrush = pattern2.handle;
                    }
                    else {
                        final long brush2 = (this.data.gdipBgPatternBrushAlpha != 0L) ? Gdip.Brush_Clone(this.data.gdipBgPatternBrushAlpha) : createAlphaTextureBrush(pattern2.handle, this.data.alpha);
                        final GCData data3 = this.data;
                        final GCData data4 = this.data;
                        final long n = brush2;
                        data4.gdipBgBrush = n;
                        data3.gdipBrush = n;
                    }
                    if ((this.data.style & 0x8000000) != 0x0) {
                        switch (Gdip.Brush_GetType(this.data.gdipBrush)) {
                            case 2: {
                                final long brush2 = Gdip.Brush_Clone(this.data.gdipBrush);
                                if (brush2 == 0L) {
                                    SWT.error(2);
                                }
                                Gdip.TextureBrush_ScaleTransform(brush2, -1.0f, 1.0f, 0);
                                final GCData data5 = this.data;
                                final GCData data6 = this.data;
                                final long n2 = brush2;
                                data6.gdipBgBrush = n2;
                                data5.gdipBrush = n2;
                                break;
                            }
                        }
                    }
                }
                else {
                    final int background = this.data.background;
                    final int color2 = this.data.alpha << 24 | (background >> 16 & 0xFF) | (background & 0xFF00) | (background & 0xFF) << 16;
                    final long brush3 = Gdip.SolidBrush_new(color2);
                    if (brush3 == 0L) {
                        SWT.error(2);
                    }
                    final GCData data7 = this.data;
                    final GCData data8 = this.data;
                    final long n3 = brush3;
                    data8.gdipBgBrush = n3;
                    data7.gdipBrush = n3;
                }
            }
            if ((state & 0x4) != 0x0) {
                final Font font = this.data.font;
                OS.SelectObject(this.handle, font.handle);
                final long[] hFont = { 0L };
                final long gdipFont = createGdipFont(this.handle, font.handle, gdipGraphics, this.device.fontCollection, null, hFont);
                if (hFont[0] != 0L) {
                    OS.SelectObject(this.handle, hFont[0]);
                }
                if (this.data.hGDIFont != 0L) {
                    OS.DeleteObject(this.data.hGDIFont);
                }
                this.data.hGDIFont = hFont[0];
                if (this.data.gdipFont != 0L) {
                    Gdip.Font_delete(this.data.gdipFont);
                }
                this.data.gdipFont = gdipFont;
            }
            if ((state & 0x4000) != 0x0) {
                final GCData data9 = this.data;
                final GCData data10 = this.data;
                final float n4 = 0.0f;
                data10.gdipYOffset = 0.0f;
                data9.gdipXOffset = 0.0f;
                final long matrix = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
                final PointF pointF2;
                final PointF point;
                final PointF pointF = point = (pointF2 = new PointF());
                final float n5 = 1.0f;
                pointF.Y = 1.0f;
                pointF2.X = 1.0f;
                Gdip.Graphics_GetTransform(gdipGraphics, matrix);
                Gdip.Matrix_TransformVectors(matrix, point, 1);
                Gdip.Matrix_delete(matrix);
                float scaling = point.X;
                if (scaling < 0.0f) {
                    scaling = -scaling;
                }
                float penWidth = this.data.lineWidth * scaling;
                if (penWidth == 0.0f || ((int)penWidth & 0x1) == 0x1) {
                    this.data.gdipXOffset = 0.5f / scaling;
                }
                scaling = point.Y;
                if (scaling < 0.0f) {
                    scaling = -scaling;
                }
                penWidth = this.data.lineWidth * scaling;
                if (penWidth == 0.0f || ((int)penWidth & 0x1) == 0x1) {
                    this.data.gdipYOffset = 0.5f / scaling;
                }
            }
            return;
        }
        if ((state & 0x79) != 0x0) {
            final int color3 = this.data.foreground;
            final int width2 = (int)this.data.lineWidth;
            int[] dashes2 = null;
            int lineStyle = 0;
            switch (this.data.lineStyle) {
                case 2: {
                    lineStyle = 1;
                    break;
                }
                case 3: {
                    lineStyle = 2;
                    break;
                }
                case 4: {
                    lineStyle = 3;
                    break;
                }
                case 5: {
                    lineStyle = 4;
                    break;
                }
                case 6: {
                    if (this.data.lineDashes != null) {
                        lineStyle = 7;
                        dashes2 = new int[this.data.lineDashes.length];
                        for (int j = 0; j < dashes2.length; ++j) {
                            dashes2[j] = (int)this.data.lineDashes[j];
                        }
                        break;
                    }
                    break;
                }
            }
            if ((state & 0x8) != 0x0) {
                OS.SetBkMode(this.handle, (this.data.lineStyle == 1) ? 2 : 1);
            }
            int joinStyle2 = 0;
            switch (this.data.lineJoin) {
                case 1: {
                    joinStyle2 = 8192;
                    break;
                }
                case 2: {
                    joinStyle2 = 0;
                    break;
                }
                case 3: {
                    joinStyle2 = 4096;
                    break;
                }
            }
            int capStyle2 = 0;
            switch (this.data.lineCap) {
                case 2: {
                    capStyle2 = 0;
                    break;
                }
                case 1: {
                    capStyle2 = 512;
                    break;
                }
                case 3: {
                    capStyle2 = 256;
                    break;
                }
            }
            final int style = lineStyle | joinStyle2 | capStyle2;
            long newPen;
            if ((width2 == 0 && lineStyle != 7) || style == 0) {
                newPen = OS.CreatePen(style & 0xF, width2, color3);
            }
            else {
                final LOGBRUSH logBrush = new LOGBRUSH();
                logBrush.lbStyle = 0;
                logBrush.lbColor = color3;
                newPen = OS.ExtCreatePen(style | 0x10000, Math.max(1, width2), logBrush, (dashes2 != null) ? dashes2.length : 0, dashes2);
            }
            OS.SelectObject(this.handle, newPen);
            final GCData data24;
            final GCData data11 = data24 = this.data;
            data24.state |= 0x800;
            final GCData data25;
            final GCData data12 = data25 = this.data;
            data25.state &= 0xFFFFDFFF;
            if (this.data.hPen != 0L) {
                OS.DeleteObject(this.data.hPen);
            }
            final GCData data13 = this.data;
            final GCData data14 = this.data;
            final long n6 = newPen;
            data14.hOldPen = n6;
            data13.hPen = n6;
        }
        else if ((state & 0x800) != 0x0) {
            OS.SelectObject(this.handle, this.data.hOldPen);
            final GCData data26;
            final GCData data15 = data26 = this.data;
            data26.state &= 0xFFFFDFFF;
        }
        else if ((state & 0x2000) != 0x0) {
            this.data.hOldPen = OS.SelectObject(this.handle, OS.GetStockObject(8));
            final GCData data27;
            final GCData data16 = data27 = this.data;
            data27.state &= 0xFFFFF7FF;
        }
        if ((state & 0x2) != 0x0) {
            final long newBrush = OS.CreateSolidBrush(this.data.background);
            OS.SelectObject(this.handle, newBrush);
            final GCData data28;
            final GCData data17 = data28 = this.data;
            data28.state |= 0x400;
            final GCData data29;
            final GCData data18 = data29 = this.data;
            data29.state &= 0xFFFFEFFF;
            if (this.data.hBrush != 0L) {
                OS.DeleteObject(this.data.hBrush);
            }
            final GCData data19 = this.data;
            final GCData data20 = this.data;
            final long n7 = newBrush;
            data20.hBrush = n7;
            data19.hOldBrush = n7;
        }
        else if ((state & 0x400) != 0x0) {
            OS.SelectObject(this.handle, this.data.hOldBrush);
            final GCData data30;
            final GCData data21 = data30 = this.data;
            data30.state &= 0xFFFFEFFF;
        }
        else if ((state & 0x1000) != 0x0) {
            this.data.hOldBrush = OS.SelectObject(this.handle, OS.GetStockObject(5));
            final GCData data31;
            final GCData data22 = data31 = this.data;
            data31.state &= 0xFFFFFBFF;
        }
        if ((state & 0x200) != 0x0) {
            OS.SetBkColor(this.handle, this.data.background);
        }
        if ((state & 0x100) != 0x0) {
            OS.SetTextColor(this.handle, this.data.foreground);
        }
        if ((state & 0x4) != 0x0) {
            final Font font2 = this.data.font;
            OS.SelectObject(this.handle, font2.handle);
        }
    }
    
    public void copyArea(final Image image, int x, int y) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        this.copyAreaInPixels(image, x, y);
    }
    
    void copyAreaInPixels(final Image image, final int x, final int y) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (image == null) {
            SWT.error(4);
        }
        if (image.type != 0 || image.isDisposed()) {
            SWT.error(5);
        }
        final Rectangle rect = image.getBoundsInPixels();
        final long memHdc = OS.CreateCompatibleDC(this.handle);
        final long hOldBitmap = OS.SelectObject(memHdc, image.handle);
        OS.BitBlt(memHdc, 0, 0, rect.width, rect.height, this.handle, x, y, 13369376);
        OS.SelectObject(memHdc, hOldBitmap);
        OS.DeleteDC(memHdc);
    }
    
    public void copyArea(final int srcX, final int srcY, final int width, final int height, final int destX, final int destY) {
        this.copyArea(srcX, srcY, width, height, destX, destY, true);
    }
    
    public void copyArea(int srcX, int srcY, int width, int height, int destX, int destY, final boolean paint) {
        srcX = DPIUtil.autoScaleUp(this.drawable, srcX);
        srcY = DPIUtil.autoScaleUp(this.drawable, srcY);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        destX = DPIUtil.autoScaleUp(this.drawable, destX);
        destY = DPIUtil.autoScaleUp(this.drawable, destY);
        this.copyAreaInPixels(srcX, srcY, width, height, destX, destY, paint);
    }
    
    void copyAreaInPixels(final int srcX, final int srcY, final int width, final int height, final int destX, final int destY, final boolean paint) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        final long hwnd = this.data.hwnd;
        if (hwnd == 0L) {
            OS.BitBlt(this.handle, destX, destY, width, height, this.handle, srcX, srcY, 13369376);
        }
        else {
            RECT lprcClip = null;
            final long hrgn = OS.CreateRectRgn(0, 0, 0, 0);
            if (OS.GetClipRgn(this.handle, hrgn) == 1) {
                lprcClip = new RECT();
                OS.GetRgnBox(hrgn, lprcClip);
            }
            OS.DeleteObject(hrgn);
            final RECT lprcScroll = new RECT();
            OS.SetRect(lprcScroll, srcX, srcY, srcX + width, srcY + height);
            final int flags = paint ? 6 : 0;
            OS.ScrollWindowEx(hwnd, destX - srcX, destY - srcY, lprcScroll, lprcClip, 0L, null, flags);
        }
    }
    
    static long createGdipFont(final long hDC, final long hFont, final long graphics, final long fontCollection, final long[] outFamily, final long[] outFont) {
        long font = Gdip.Font_new(hDC, hFont);
        if (font == 0L) {
            SWT.error(2);
        }
        long family = 0L;
        if (!Gdip.Font_IsAvailable(font)) {
            Gdip.Font_delete(font);
            final LOGFONT logFont = new LOGFONT();
            OS.GetObject(hFont, LOGFONT.sizeof, logFont);
            final int size = Math.abs(logFont.lfHeight);
            int style = 0;
            if (logFont.lfWeight == 700) {
                style |= 0x1;
            }
            if (logFont.lfItalic != 0) {
                style |= 0x2;
            }
            char[] chars;
            int index;
            for (chars = logFont.lfFaceName, index = 0; index < chars.length && chars[index] != '\0'; ++index) {}
            String name = new String(chars, 0, index);
            if (name.equalsIgnoreCase("Courier")) {
                name = "Courier New";
            }
            final char[] buffer = new char[name.length() + 1];
            name.getChars(0, name.length(), buffer, 0);
            if (fontCollection != 0L) {
                family = Gdip.FontFamily_new(buffer, fontCollection);
                if (!Gdip.FontFamily_IsAvailable(family)) {
                    Gdip.FontFamily_delete(family);
                    family = Gdip.FontFamily_new(buffer, 0L);
                    if (!Gdip.FontFamily_IsAvailable(family)) {
                        Gdip.FontFamily_delete(family);
                        family = 0L;
                    }
                }
            }
            if (family != 0L) {
                font = Gdip.Font_new(family, (float)size, style, 2);
            }
            else {
                font = Gdip.Font_new(buffer, (float)size, style, 2, 0L);
            }
            if (outFont != null && font != 0L) {
                final long hHeap = OS.GetProcessHeap();
                final long pLogFont = OS.HeapAlloc(hHeap, 8, LOGFONT.sizeof);
                Gdip.Font_GetLogFontW(font, graphics, pLogFont);
                outFont[0] = OS.CreateFontIndirect(pLogFont);
                OS.HeapFree(hHeap, 0, pLogFont);
            }
        }
        if (outFamily != null && font != 0L) {
            if (family == 0L) {
                family = Gdip.FontFamily_new();
                Gdip.Font_GetFamily(font, family);
            }
            outFamily[0] = family;
        }
        else if (family != 0L) {
            Gdip.FontFamily_delete(family);
        }
        if (font == 0L) {
            SWT.error(2);
        }
        return font;
    }
    
    static long createAlphaTextureBrush(final long brush, final int alpha) {
        if (Gdip.Brush_GetType(brush) != 2) {
            return Gdip.Brush_Clone(brush);
        }
        final long hatchImage = Gdip.TextureBrush_GetImage(brush);
        if (hatchImage == 0L) {
            SWT.error(7);
        }
        final long transparentHatchImage = Gdip.Image_Clone(hatchImage);
        if (transparentHatchImage == 0L) {
            SWT.error(2);
        }
        final long attrib = Gdip.ImageAttributes_new();
        Gdip.ImageAttributes_SetWrapMode(attrib, 0);
        final float[] matrix = { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, alpha / 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        Gdip.ImageAttributes_SetColorMatrix(attrib, matrix, 0, 1);
        final Rect rect = new Rect();
        rect.X = 0;
        rect.Y = 0;
        rect.Width = Gdip.Image_GetWidth(transparentHatchImage);
        rect.Height = Gdip.Image_GetHeight(transparentHatchImage);
        final long transparentBrush = Gdip.TextureBrush_new(transparentHatchImage, rect, attrib);
        if (brush == 0L) {
            SWT.error(2);
        }
        Gdip.ImageAttributes_delete(attrib);
        Gdip.Image_delete(transparentHatchImage);
        return transparentBrush;
    }
    
    static void destroyGdipBrush(final long brush) {
        final int type = Gdip.Brush_GetType(brush);
        switch (type) {
            case 0: {
                Gdip.SolidBrush_delete(brush);
                break;
            }
            case 1: {
                Gdip.HatchBrush_delete(brush);
                break;
            }
            case 4: {
                Gdip.LinearGradientBrush_delete(brush);
                break;
            }
            case 2: {
                Gdip.TextureBrush_delete(brush);
                break;
            }
        }
    }
    
    @Override
    void destroy() {
        final boolean gdip = this.data.gdipGraphics != 0L;
        this.disposeGdip();
        if (gdip && (this.data.style & 0x8000000) != 0x0) {
            OS.SetLayout(this.handle, OS.GetLayout(this.handle) | 0x1);
        }
        if (this.data.hPen != 0L) {
            OS.SelectObject(this.handle, OS.GetStockObject(8));
            OS.DeleteObject(this.data.hPen);
            this.data.hPen = 0L;
        }
        if (this.data.hBrush != 0L) {
            OS.SelectObject(this.handle, OS.GetStockObject(5));
            OS.DeleteObject(this.data.hBrush);
            this.data.hBrush = 0L;
        }
        final long hNullBitmap = this.data.hNullBitmap;
        if (hNullBitmap != 0L) {
            OS.SelectObject(this.handle, hNullBitmap);
            this.data.hNullBitmap = 0L;
        }
        final Image image = this.data.image;
        if (image != null) {
            image.memGC = null;
        }
        if (this.drawable != null) {
            this.drawable.internal_dispose_GC(this.handle, this.data);
        }
        this.drawable = null;
        this.handle = 0L;
        this.data.image = null;
        this.data.ps = null;
        this.data = null;
    }
    
    void disposeGdip() {
        if (this.data.gdipPen != 0L) {
            Gdip.Pen_delete(this.data.gdipPen);
        }
        if (this.data.gdipBgBrush != 0L) {
            destroyGdipBrush(this.data.gdipBgBrush);
        }
        if (this.data.gdipFgBrush != 0L) {
            destroyGdipBrush(this.data.gdipFgBrush);
        }
        if (this.data.gdipFont != 0L) {
            Gdip.Font_delete(this.data.gdipFont);
        }
        if (this.data.hGDIFont != 0L) {
            OS.DeleteObject(this.data.hGDIFont);
        }
        if (this.data.gdipGraphics != 0L) {
            Gdip.Graphics_delete(this.data.gdipGraphics);
        }
        if (this.data.gdipBgPatternBrushAlpha != 0L) {
            destroyGdipBrush(this.data.gdipBgPatternBrushAlpha);
        }
        if (this.data.gdipFgPatternBrushAlpha != 0L) {
            destroyGdipBrush(this.data.gdipFgPatternBrushAlpha);
        }
        final GCData data = this.data;
        final GCData data2 = this.data;
        final GCData data3 = this.data;
        final GCData data4 = this.data;
        final GCData data5 = this.data;
        final GCData data6 = this.data;
        final GCData data7 = this.data;
        final GCData data8 = this.data;
        final GCData data9 = this.data;
        final long gdipGraphics = 0L;
        data9.gdipFgPatternBrushAlpha = 0L;
        data8.gdipBgPatternBrushAlpha = 0L;
        data7.hGDIFont = 0L;
        data6.gdipPen = 0L;
        data5.gdipFont = 0L;
        data4.gdipFgBrush = 0L;
        data3.gdipBgBrush = 0L;
        data2.gdipBrush = 0L;
        data.gdipGraphics = 0L;
    }
    
    public void drawArc(int x, int y, int width, int height, final int startAngle, final int arcAngle) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.drawArcInPixels(x, y, width, height, startAngle, arcAngle);
    }
    
    void drawArcInPixels(int x, int y, int width, int height, int startAngle, int arcAngle) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(22777);
        if (width < 0) {
            x += width;
            width = -width;
        }
        if (height < 0) {
            y += height;
            height = -height;
        }
        if (width == 0 || height == 0 || arcAngle == 0) {
            return;
        }
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            if (width == height) {
                Gdip.Graphics_DrawArc(gdipGraphics, this.data.gdipPen, x, y, width, height, (float)(-startAngle), (float)(-arcAngle));
            }
            else {
                final long path = Gdip.GraphicsPath_new(0);
                if (path == 0L) {
                    SWT.error(2);
                }
                final long matrix = Gdip.Matrix_new((float)width, 0.0f, 0.0f, (float)height, (float)x, (float)y);
                if (matrix == 0L) {
                    SWT.error(2);
                }
                Gdip.GraphicsPath_AddArc(path, 0.0f, 0.0f, 1.0f, 1.0f, (float)(-startAngle), (float)(-arcAngle));
                Gdip.GraphicsPath_Transform(path, matrix);
                Gdip.Graphics_DrawPath(gdipGraphics, this.data.gdipPen, path);
                Gdip.Matrix_delete(matrix);
                Gdip.GraphicsPath_delete(path);
            }
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            --x;
        }
        int x3;
        int x2;
        int y3;
        int y2;
        if (arcAngle >= 360 || arcAngle <= -360) {
            x2 = (x3 = x + width);
            y2 = (y3 = y + height / 2);
        }
        else {
            final boolean isNegative = arcAngle < 0;
            arcAngle += startAngle;
            if (isNegative) {
                final int tmp = startAngle;
                startAngle = arcAngle;
                arcAngle = tmp;
            }
            x2 = cos(startAngle, width) + x + width / 2;
            y2 = -1 * sin(startAngle, height) + y + height / 2;
            x3 = cos(arcAngle, width) + x + width / 2;
            y3 = -1 * sin(arcAngle, height) + y + height / 2;
        }
        OS.Arc(this.handle, x, y, x + width + 1, y + height + 1, x2, y2, x3, y3);
    }
    
    public void drawFocus(int x, int y, int width, int height) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.drawFocusInPixels(x, y, width, height);
    }
    
    void drawFocusInPixels(final int x, final int y, final int width, final int height) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if ((this.data.uiState & 0x1) != 0x0) {
            return;
        }
        this.data.focusDrawn = true;
        long hdc = this.handle;
        int state = 0;
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            long clipRgn = 0L;
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 3);
            final long rgn = Gdip.Region_new();
            if (rgn == 0L) {
                SWT.error(2);
            }
            Gdip.Graphics_GetClip(gdipGraphics, rgn);
            if (!Gdip.Region_IsInfinite(rgn, gdipGraphics)) {
                clipRgn = Gdip.Region_GetHRGN(rgn, gdipGraphics);
            }
            Gdip.Region_delete(rgn);
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
            float[] lpXform = null;
            final long matrix = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
            if (matrix == 0L) {
                SWT.error(2);
            }
            Gdip.Graphics_GetTransform(gdipGraphics, matrix);
            if (!Gdip.Matrix_IsIdentity(matrix)) {
                lpXform = new float[6];
                Gdip.Matrix_GetElements(matrix, lpXform);
            }
            Gdip.Matrix_delete(matrix);
            hdc = Gdip.Graphics_GetHDC(gdipGraphics);
            state = OS.SaveDC(hdc);
            if (lpXform != null) {
                OS.SetGraphicsMode(hdc, 2);
                OS.SetWorldTransform(hdc, lpXform);
            }
            if (clipRgn != 0L) {
                OS.SelectClipRgn(hdc, clipRgn);
                OS.DeleteObject(clipRgn);
            }
        }
        OS.SetBkColor(hdc, 16777215);
        OS.SetTextColor(hdc, 0);
        final RECT rect = new RECT();
        OS.SetRect(rect, x, y, x + width, y + height);
        OS.DrawFocusRect(hdc, rect);
        if (gdipGraphics != 0L) {
            OS.RestoreDC(hdc, state);
            Gdip.Graphics_ReleaseHDC(gdipGraphics, hdc);
        }
        else {
            final GCData data2;
            final GCData data = data2 = this.data;
            data2.state &= 0xFFFFFCFF;
        }
    }
    
    public void drawImage(final Image image, int x, int y) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        this.drawImageInPixels(image, x, y);
    }
    
    void drawImageInPixels(final Image image, final int x, final int y) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (image == null) {
            SWT.error(4);
        }
        if (image.isDisposed()) {
            SWT.error(5);
        }
        this.drawImage(image, 0, 0, -1, -1, x, y, -1, -1, true);
    }
    
    public void drawImage(final Image image, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final int destX, final int destY, final int destWidth, final int destHeight) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (srcWidth == 0 || srcHeight == 0 || destWidth == 0 || destHeight == 0) {
            return;
        }
        if (srcX < 0 || srcY < 0 || srcWidth < 0 || srcHeight < 0 || destWidth < 0 || destHeight < 0) {
            SWT.error(5);
        }
        if (image == null) {
            SWT.error(4);
        }
        if (image.isDisposed()) {
            SWT.error(5);
        }
        final Rectangle src = DPIUtil.autoScaleUp(this.drawable, new Rectangle(srcX, srcY, srcWidth, srcHeight));
        final Rectangle dest = DPIUtil.autoScaleUp(this.drawable, new Rectangle(destX, destY, destWidth, destHeight));
        final int deviceZoom = DPIUtil.getDeviceZoom();
        if (deviceZoom != 100) {
            final Rectangle b = image.getBoundsInPixels();
            final int errX = src.x + src.width - b.width;
            final int errY = src.y + src.height - b.height;
            if (errX != 0 || errY != 0) {
                if (errX <= deviceZoom / 100 && errY <= deviceZoom / 100) {
                    src.intersect(b);
                }
                else {
                    SWT.error(5);
                }
            }
        }
        this.drawImage(image, src.x, src.y, src.width, src.height, dest.x, dest.y, dest.width, dest.height, false);
    }
    
    void drawImage(final Image srcImage, final int srcX, final int srcY, int srcWidth, int srcHeight, final int destX, final int destY, int destWidth, int destHeight, boolean simple) {
        srcImage.refreshImageForZoom();
        if (this.data.gdipGraphics != 0L) {
            final long[] gdipImage = srcImage.createGdipImage();
            final long img = gdipImage[0];
            final int imgWidth = Gdip.Image_GetWidth(img);
            final int imgHeight = Gdip.Image_GetHeight(img);
            if (simple) {
                srcWidth = (destWidth = imgWidth);
                srcHeight = (destHeight = imgHeight);
            }
            else {
                if (srcX + srcWidth > imgWidth || srcY + srcHeight > imgHeight) {
                    SWT.error(5);
                }
                simple = (srcX == 0 && srcY == 0 && srcWidth == destWidth && destWidth == imgWidth && srcHeight == destHeight && destHeight == imgHeight);
            }
            final Rect rect = new Rect();
            rect.X = destX;
            rect.Y = destY;
            rect.Width = destWidth;
            rect.Height = destHeight;
            final long attrib = Gdip.ImageAttributes_new();
            Gdip.ImageAttributes_SetWrapMode(attrib, 3);
            if (this.data.alpha != 255) {
                final float[] matrix = { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.data.alpha / 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
                Gdip.ImageAttributes_SetColorMatrix(attrib, matrix, 0, 1);
            }
            int gstate = 0;
            if ((this.data.style & 0x8000000) != 0x0) {
                gstate = Gdip.Graphics_Save(this.data.gdipGraphics);
                Gdip.Graphics_ScaleTransform(this.data.gdipGraphics, -1.0f, 1.0f, 0);
                Gdip.Graphics_TranslateTransform(this.data.gdipGraphics, (float)(-2 * destX - destWidth), 0.0f, 0);
            }
            Gdip.Graphics_DrawImage(this.data.gdipGraphics, img, rect, srcX, srcY, srcWidth, srcHeight, 2, attrib, 0L, 0L);
            if ((this.data.style & 0x8000000) != 0x0) {
                Gdip.Graphics_Restore(this.data.gdipGraphics, gstate);
            }
            Gdip.ImageAttributes_delete(attrib);
            Gdip.Bitmap_delete(img);
            if (gdipImage[1] != 0L) {
                final long hHeap = OS.GetProcessHeap();
                OS.HeapFree(hHeap, 0, gdipImage[1]);
            }
            return;
        }
        switch (srcImage.type) {
            case 0: {
                this.drawBitmap(srcImage, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight, simple);
                break;
            }
            case 1: {
                this.drawIcon(srcImage, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight, simple);
                break;
            }
        }
    }
    
    void drawIcon(final Image srcImage, final int srcX, final int srcY, int srcWidth, int srcHeight, final int destX, final int destY, int destWidth, int destHeight, boolean simple) {
        final int technology = OS.GetDeviceCaps(this.handle, 2);
        final boolean drawIcon = true;
        int flags = 3;
        int offsetX = 0;
        int offsetY = 0;
        if ((OS.GetLayout(this.handle) & 0x1) != 0x0) {
            flags |= 0x10;
            final POINT pt = new POINT();
            OS.GetWindowOrgEx(this.handle, pt);
            offsetX = pt.x;
            offsetY = pt.y;
        }
        if (simple && technology != 2) {
            if (offsetX != 0 || offsetY != 0) {
                OS.SetWindowOrgEx(this.handle, 0, 0, null);
            }
            OS.DrawIconEx(this.handle, destX - offsetX, destY - offsetY, srcImage.handle, 0, 0, 0, 0L, flags);
            if (offsetX != 0 || offsetY != 0) {
                OS.SetWindowOrgEx(this.handle, offsetX, offsetY, null);
            }
            return;
        }
        final ICONINFO srcIconInfo = new ICONINFO();
        OS.GetIconInfo(srcImage.handle, srcIconInfo);
        long hBitmap = srcIconInfo.hbmColor;
        if (hBitmap == 0L) {
            hBitmap = srcIconInfo.hbmMask;
        }
        final BITMAP bm = new BITMAP();
        OS.GetObject(hBitmap, BITMAP.sizeof, bm);
        final int iconWidth = bm.bmWidth;
        int iconHeight = bm.bmHeight;
        if (hBitmap == srcIconInfo.hbmMask) {
            iconHeight /= 2;
        }
        if (simple) {
            srcWidth = (destWidth = iconWidth);
            srcHeight = (destHeight = iconHeight);
        }
        final boolean failed = srcX + srcWidth > iconWidth || srcY + srcHeight > iconHeight;
        if (!failed) {
            simple = (srcX == 0 && srcY == 0 && srcWidth == destWidth && srcHeight == destHeight && srcWidth == iconWidth && srcHeight == iconHeight);
            if (simple && technology != 2) {
                if (offsetX != 0 || offsetY != 0) {
                    OS.SetWindowOrgEx(this.handle, 0, 0, null);
                }
                OS.DrawIconEx(this.handle, destX - offsetX, destY - offsetY, srcImage.handle, 0, 0, 0, 0L, flags);
                if (offsetX != 0 || offsetY != 0) {
                    OS.SetWindowOrgEx(this.handle, offsetX, offsetY, null);
                }
            }
            else {
                final ICONINFO newIconInfo = new ICONINFO();
                newIconInfo.fIcon = true;
                final long srcHdc = OS.CreateCompatibleDC(this.handle);
                final long dstHdc = OS.CreateCompatibleDC(this.handle);
                int srcColorY = srcY;
                long srcColor = srcIconInfo.hbmColor;
                if (srcColor == 0L) {
                    srcColor = srcIconInfo.hbmMask;
                    srcColorY += iconHeight;
                }
                final long oldSrcBitmap = OS.SelectObject(srcHdc, srcColor);
                newIconInfo.hbmColor = OS.CreateCompatibleBitmap(srcHdc, destWidth, destHeight);
                if (newIconInfo.hbmColor == 0L) {
                    SWT.error(2);
                }
                final long oldDestBitmap = OS.SelectObject(dstHdc, newIconInfo.hbmColor);
                final boolean stretch = !simple && (srcWidth != destWidth || srcHeight != destHeight);
                if (stretch) {
                    OS.SetStretchBltMode(dstHdc, 3);
                    OS.StretchBlt(dstHdc, 0, 0, destWidth, destHeight, srcHdc, srcX, srcColorY, srcWidth, srcHeight, 13369376);
                }
                else {
                    OS.BitBlt(dstHdc, 0, 0, destWidth, destHeight, srcHdc, srcX, srcColorY, 13369376);
                }
                OS.SelectObject(srcHdc, srcIconInfo.hbmMask);
                newIconInfo.hbmMask = OS.CreateBitmap(destWidth, destHeight, 1, 1, null);
                if (newIconInfo.hbmMask == 0L) {
                    SWT.error(2);
                }
                OS.SelectObject(dstHdc, newIconInfo.hbmMask);
                if (stretch) {
                    OS.StretchBlt(dstHdc, 0, 0, destWidth, destHeight, srcHdc, srcX, srcY, srcWidth, srcHeight, 13369376);
                }
                else {
                    OS.BitBlt(dstHdc, 0, 0, destWidth, destHeight, srcHdc, srcX, srcY, 13369376);
                }
                if (technology == 2) {
                    OS.SelectObject(srcHdc, newIconInfo.hbmColor);
                    OS.SelectObject(dstHdc, newIconInfo.hbmMask);
                    this.drawBitmapTransparentByClipping(srcHdc, dstHdc, 0, 0, destWidth, destHeight, destX, destY, destWidth, destHeight, true, destWidth, destHeight);
                    OS.SelectObject(srcHdc, oldSrcBitmap);
                    OS.SelectObject(dstHdc, oldDestBitmap);
                }
                else {
                    OS.SelectObject(srcHdc, oldSrcBitmap);
                    OS.SelectObject(dstHdc, oldDestBitmap);
                    final long hIcon = OS.CreateIconIndirect(newIconInfo);
                    if (hIcon == 0L) {
                        SWT.error(2);
                    }
                    if (offsetX != 0 || offsetY != 0) {
                        OS.SetWindowOrgEx(this.handle, 0, 0, null);
                    }
                    OS.DrawIconEx(this.handle, destX - offsetX, destY - offsetY, hIcon, destWidth, destHeight, 0, 0L, flags);
                    if (offsetX != 0 || offsetY != 0) {
                        OS.SetWindowOrgEx(this.handle, offsetX, offsetY, null);
                    }
                    OS.DestroyIcon(hIcon);
                }
                OS.DeleteObject(newIconInfo.hbmMask);
                OS.DeleteObject(newIconInfo.hbmColor);
                OS.DeleteDC(dstHdc);
                OS.DeleteDC(srcHdc);
            }
        }
        OS.DeleteObject(srcIconInfo.hbmMask);
        if (srcIconInfo.hbmColor != 0L) {
            OS.DeleteObject(srcIconInfo.hbmColor);
        }
        if (failed) {
            SWT.error(5);
        }
    }
    
    void drawBitmap(final Image srcImage, final int srcX, final int srcY, int srcWidth, int srcHeight, final int destX, final int destY, int destWidth, int destHeight, boolean simple) {
        final BITMAP bm = new BITMAP();
        OS.GetObject(srcImage.handle, BITMAP.sizeof, bm);
        final int imgWidth = bm.bmWidth;
        final int imgHeight = bm.bmHeight;
        if (simple) {
            srcWidth = (destWidth = imgWidth);
            srcHeight = (destHeight = imgHeight);
        }
        else {
            if (srcX + srcWidth > imgWidth || srcY + srcHeight > imgHeight) {
                SWT.error(5);
            }
            simple = (srcX == 0 && srcY == 0 && srcWidth == destWidth && destWidth == imgWidth && srcHeight == destHeight && destHeight == imgHeight);
        }
        boolean mustRestore = false;
        final GC memGC = srcImage.memGC;
        if (memGC != null && !memGC.isDisposed()) {
            memGC.flush();
            mustRestore = true;
            final GCData data = memGC.data;
            if (data.hNullBitmap != 0L) {
                OS.SelectObject(memGC.handle, data.hNullBitmap);
                data.hNullBitmap = 0L;
            }
        }
        final boolean isDib = bm.bmBits != 0L;
        final int depth = bm.bmPlanes * bm.bmBitsPixel;
        if (isDib && depth == 32) {
            this.drawBitmapAlpha(srcImage, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight, simple);
        }
        else if (srcImage.transparentPixel != -1) {
            this.drawBitmapTransparent(srcImage, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight, simple, bm, imgWidth, imgHeight);
        }
        else {
            this.drawBitmapColor(srcImage, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight, simple);
        }
        if (mustRestore) {
            final long hOldBitmap = OS.SelectObject(memGC.handle, srcImage.handle);
            memGC.data.hNullBitmap = hOldBitmap;
        }
    }
    
    void drawBitmapAlpha(final Image srcImage, int srcX, int srcY, int srcWidth, int srcHeight, int destX, int destY, int destWidth, int destHeight, final boolean simple) {
        boolean alphaBlendSupport = true;
        final boolean isPrinter = OS.GetDeviceCaps(this.handle, 2) == 2;
        int sourceAlpha = -1;
        if (isPrinter) {
            final int caps = OS.GetDeviceCaps(this.handle, 120);
            if (caps != 0) {
                final long srcHdc = OS.CreateCompatibleDC(this.handle);
                final long oldSrcBitmap = OS.SelectObject(srcHdc, srcImage.handle);
                final long memDib = Image.createDIB(srcWidth, srcHeight, 32);
                if (memDib == 0L) {
                    SWT.error(2);
                }
                final long memHdc = OS.CreateCompatibleDC(this.handle);
                final long oldMemBitmap = OS.SelectObject(memHdc, memDib);
                final BITMAP dibBM = new BITMAP();
                OS.GetObject(memDib, BITMAP.sizeof, dibBM);
                OS.BitBlt(memHdc, 0, 0, srcWidth, srcHeight, srcHdc, srcX, srcY, 13369376);
                final byte[] srcData = new byte[dibBM.bmWidthBytes * dibBM.bmHeight];
                OS.MoveMemory(srcData, dibBM.bmBits, srcData.length);
                final int size = srcData.length;
                sourceAlpha = (srcData[3] & 0xFF);
                for (int sp = 7; sp < size; sp += 4) {
                    final int currentAlpha = srcData[sp] & 0xFF;
                    if (sourceAlpha != currentAlpha) {
                        sourceAlpha = -1;
                        break;
                    }
                }
                OS.SelectObject(memHdc, oldMemBitmap);
                OS.DeleteDC(memHdc);
                OS.DeleteObject(memDib);
                OS.SelectObject(srcHdc, oldSrcBitmap);
                OS.DeleteDC(srcHdc);
                if (sourceAlpha != -1) {
                    if (sourceAlpha == 0) {
                        return;
                    }
                    if (sourceAlpha == 255) {
                        this.drawBitmapColor(srcImage, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight, simple);
                        return;
                    }
                    alphaBlendSupport = ((caps & 0x1) != 0x0);
                }
                else {
                    alphaBlendSupport = ((caps & 0x2) != 0x0);
                }
            }
        }
        if (alphaBlendSupport) {
            final BLENDFUNCTION blend = new BLENDFUNCTION();
            blend.BlendOp = 0;
            final long srcHdc = OS.CreateCompatibleDC(this.handle);
            final long oldSrcBitmap = OS.SelectObject(srcHdc, srcImage.handle);
            blend.SourceConstantAlpha = (byte)sourceAlpha;
            blend.AlphaFormat = 1;
            OS.AlphaBlend(this.handle, destX, destY, destWidth, destHeight, srcHdc, srcX, srcY, srcWidth, srcHeight, blend);
            OS.SelectObject(srcHdc, oldSrcBitmap);
            OS.DeleteDC(srcHdc);
            return;
        }
        Rectangle rect = this.getClippingInPixels();
        rect = rect.intersection(new Rectangle(destX, destY, destWidth, destHeight));
        if (rect.isEmpty()) {
            return;
        }
        final int sx1 = srcX + (rect.x - destX) * srcWidth / destWidth;
        final int sx2 = srcX + (rect.x + rect.width - destX) * srcWidth / destWidth;
        final int sy1 = srcY + (rect.y - destY) * srcHeight / destHeight;
        final int sy2 = srcY + (rect.y + rect.height - destY) * srcHeight / destHeight;
        destX = rect.x;
        destY = rect.y;
        destWidth = rect.width;
        destHeight = rect.height;
        srcX = sx1;
        srcY = sy1;
        srcWidth = Math.max(1, sx2 - sx1);
        srcHeight = Math.max(1, sy2 - sy1);
        final long srcHdc2 = OS.CreateCompatibleDC(this.handle);
        final long oldSrcBitmap2 = OS.SelectObject(srcHdc2, srcImage.handle);
        final long memHdc2 = OS.CreateCompatibleDC(this.handle);
        final long memDib2 = Image.createDIB(Math.max(srcWidth, destWidth), Math.max(srcHeight, destHeight), 32);
        if (memDib2 == 0L) {
            SWT.error(2);
        }
        final long oldMemBitmap2 = OS.SelectObject(memHdc2, memDib2);
        final BITMAP dibBM2 = new BITMAP();
        OS.GetObject(memDib2, BITMAP.sizeof, dibBM2);
        final int sizeInBytes = dibBM2.bmWidthBytes * dibBM2.bmHeight;
        OS.BitBlt(memHdc2, 0, 0, destWidth, destHeight, this.handle, destX, destY, 13369376);
        final byte[] destData = new byte[sizeInBytes];
        OS.MoveMemory(destData, dibBM2.bmBits, sizeInBytes);
        OS.BitBlt(memHdc2, 0, 0, srcWidth, srcHeight, srcHdc2, srcX, srcY, 13369376);
        final byte[] srcData2 = new byte[sizeInBytes];
        OS.MoveMemory(srcData2, dibBM2.bmBits, sizeInBytes);
        if (isPrinter) {
            final long tempHdc = OS.CreateCompatibleDC(this.handle);
            final long tempDib = Image.createDIB(destWidth, destHeight, 32);
            if (tempDib == 0L) {
                SWT.error(2);
            }
            final long oldTempBitmap = OS.SelectObject(tempHdc, tempDib);
            if (!simple && (srcWidth != destWidth || srcHeight != destHeight)) {
                OS.SetStretchBltMode(memHdc2, 3);
                OS.StretchBlt(tempHdc, 0, 0, destWidth, destHeight, memHdc2, 0, 0, srcWidth, srcHeight, 13369376);
            }
            else {
                OS.BitBlt(tempHdc, 0, 0, destWidth, destHeight, memHdc2, 0, 0, 13369376);
            }
            OS.BitBlt(memHdc2, 0, 0, destWidth, destHeight, tempHdc, 0, 0, 13369376);
            OS.SelectObject(tempHdc, oldTempBitmap);
            OS.DeleteObject(tempDib);
            OS.DeleteDC(tempHdc);
        }
        else if (!simple && (srcWidth != destWidth || srcHeight != destHeight)) {
            OS.SetStretchBltMode(memHdc2, 3);
            OS.StretchBlt(memHdc2, 0, 0, destWidth, destHeight, memHdc2, 0, 0, srcWidth, srcHeight, 13369376);
        }
        else {
            OS.BitBlt(memHdc2, 0, 0, destWidth, destHeight, memHdc2, 0, 0, 13369376);
        }
        OS.MoveMemory(srcData2, dibBM2.bmBits, sizeInBytes);
        final int dpinc = dibBM2.bmWidthBytes - destWidth * 4;
        int dp = 0;
        for (int y = 0; y < destHeight; ++y) {
            for (int x = 0; x < destWidth; ++x) {
                final int alpha = srcData2[dp + 3] & 0xFF;
                final byte[] array = destData;
                final int n = dp;
                final byte[] array4 = array;
                final int n4 = n;
                array4[n4] += (byte)((srcData2[dp] & 0xFF) - (destData[dp] & 0xFF) * alpha / 255);
                final byte[] array2 = destData;
                final int n2 = dp + 1;
                final byte[] array5 = array2;
                final int n5 = n2;
                array5[n5] += (byte)((srcData2[dp + 1] & 0xFF) - (destData[dp + 1] & 0xFF) * alpha / 255);
                final byte[] array3 = destData;
                final int n3 = dp + 2;
                final byte[] array6 = array3;
                final int n6 = n3;
                array6[n6] += (byte)((srcData2[dp + 2] & 0xFF) - (destData[dp + 2] & 0xFF) * alpha / 255);
                dp += 4;
            }
            dp += dpinc;
        }
        OS.MoveMemory(dibBM2.bmBits, destData, sizeInBytes);
        OS.BitBlt(this.handle, destX, destY, destWidth, destHeight, memHdc2, 0, 0, 13369376);
        OS.SelectObject(memHdc2, oldMemBitmap2);
        OS.DeleteDC(memHdc2);
        OS.DeleteObject(memDib2);
        OS.SelectObject(srcHdc2, oldSrcBitmap2);
        OS.DeleteDC(srcHdc2);
    }
    
    void drawBitmapTransparentByClipping(final long srcHdc, final long maskHdc, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final int destX, final int destY, final int destWidth, final int destHeight, final boolean simple, final int imgWidth, final int imgHeight) {
        long rgn = OS.CreateRectRgn(0, 0, 0, 0);
        for (int y = 0; y < imgHeight; ++y) {
            for (int x = 0; x < imgWidth; ++x) {
                if (OS.GetPixel(maskHdc, x, y) == 0) {
                    final long tempRgn = OS.CreateRectRgn(x, y, x + 1, y + 1);
                    OS.CombineRgn(rgn, rgn, tempRgn, 2);
                    OS.DeleteObject(tempRgn);
                }
            }
        }
        if (destWidth != srcWidth || destHeight != srcHeight) {
            final int nBytes = OS.GetRegionData(rgn, 0, null);
            final int[] lpRgnData = new int[nBytes / 4];
            OS.GetRegionData(rgn, nBytes, lpRgnData);
            final float[] lpXform = { destWidth / (float)srcWidth, 0.0f, 0.0f, destHeight / (float)srcHeight, 0.0f, 0.0f };
            final long tmpRgn = OS.ExtCreateRegion(lpXform, nBytes, lpRgnData);
            OS.DeleteObject(rgn);
            rgn = tmpRgn;
        }
        OS.OffsetRgn(rgn, destX, destY);
        final long clip = OS.CreateRectRgn(0, 0, 0, 0);
        final int result = OS.GetClipRgn(this.handle, clip);
        if (result == 1) {
            OS.CombineRgn(rgn, rgn, clip, 1);
        }
        OS.SelectClipRgn(this.handle, rgn);
        final int dwRop = (OS.GetROP2(this.handle) == 7) ? 6684742 : 13369376;
        if (!simple && (srcWidth != destWidth || srcHeight != destHeight)) {
            final int mode = OS.SetStretchBltMode(this.handle, 3);
            OS.StretchBlt(this.handle, destX, destY, destWidth, destHeight, srcHdc, srcX, srcY, srcWidth, srcHeight, dwRop);
            OS.SetStretchBltMode(this.handle, mode);
        }
        else {
            OS.BitBlt(this.handle, destX, destY, destWidth, destHeight, srcHdc, srcX, srcY, dwRop);
        }
        OS.SelectClipRgn(this.handle, (result == 1) ? clip : 0L);
        OS.DeleteObject(clip);
        OS.DeleteObject(rgn);
    }
    
    void drawBitmapMask(final Image srcImage, long srcColor, final long srcMask, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final int destX, final int destY, final int destWidth, final int destHeight, final boolean simple, final int imgWidth, final int imgHeight, final boolean offscreen) {
        int srcColorY = srcY;
        if (srcColor == 0L) {
            srcColor = srcMask;
            srcColorY += imgHeight;
        }
        final long srcHdc = OS.CreateCompatibleDC(this.handle);
        final long oldSrcBitmap = OS.SelectObject(srcHdc, srcColor);
        long destHdc = this.handle;
        int x = destX;
        int y = destY;
        long tempHdc = 0L;
        long tempBitmap = 0L;
        long oldTempBitmap = 0L;
        int oldBkColor = 0;
        int oldTextColor = 0;
        if (offscreen) {
            tempHdc = OS.CreateCompatibleDC(this.handle);
            tempBitmap = OS.CreateCompatibleBitmap(this.handle, destWidth, destHeight);
            oldTempBitmap = OS.SelectObject(tempHdc, tempBitmap);
            OS.BitBlt(tempHdc, 0, 0, destWidth, destHeight, this.handle, destX, destY, 13369376);
            destHdc = tempHdc;
            x = (y = 0);
        }
        else {
            oldBkColor = OS.SetBkColor(this.handle, 16777215);
            oldTextColor = OS.SetTextColor(this.handle, 0);
        }
        if (!simple && (srcWidth != destWidth || srcHeight != destHeight)) {
            final int mode = OS.SetStretchBltMode(this.handle, 3);
            OS.StretchBlt(destHdc, x, y, destWidth, destHeight, srcHdc, srcX, srcColorY, srcWidth, srcHeight, 6684742);
            OS.SelectObject(srcHdc, srcMask);
            OS.StretchBlt(destHdc, x, y, destWidth, destHeight, srcHdc, srcX, srcY, srcWidth, srcHeight, 8913094);
            OS.SelectObject(srcHdc, srcColor);
            OS.StretchBlt(destHdc, x, y, destWidth, destHeight, srcHdc, srcX, srcColorY, srcWidth, srcHeight, 6684742);
            OS.SetStretchBltMode(this.handle, mode);
        }
        else {
            OS.BitBlt(destHdc, x, y, destWidth, destHeight, srcHdc, srcX, srcColorY, 6684742);
            OS.SetTextColor(destHdc, 0);
            OS.SelectObject(srcHdc, srcMask);
            OS.BitBlt(destHdc, x, y, destWidth, destHeight, srcHdc, srcX, srcY, 8913094);
            OS.SelectObject(srcHdc, srcColor);
            OS.BitBlt(destHdc, x, y, destWidth, destHeight, srcHdc, srcX, srcColorY, 6684742);
        }
        if (offscreen) {
            OS.BitBlt(this.handle, destX, destY, destWidth, destHeight, tempHdc, 0, 0, 13369376);
            OS.SelectObject(tempHdc, oldTempBitmap);
            OS.DeleteDC(tempHdc);
            OS.DeleteObject(tempBitmap);
        }
        else {
            OS.SetBkColor(this.handle, oldBkColor);
            OS.SetTextColor(this.handle, oldTextColor);
        }
        OS.SelectObject(srcHdc, oldSrcBitmap);
        OS.DeleteDC(srcHdc);
    }
    
    void drawBitmapTransparent(final Image srcImage, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final int destX, final int destY, final int destWidth, final int destHeight, final boolean simple, final BITMAP bm, final int imgWidth, final int imgHeight) {
        final boolean isDib = bm.bmBits != 0L;
        final long hBitmap = srcImage.handle;
        final long srcHdc = OS.CreateCompatibleDC(this.handle);
        final long oldSrcBitmap = OS.SelectObject(srcHdc, hBitmap);
        byte[] originalColors = null;
        int transparentColor = srcImage.transparentColor;
        if (transparentColor == -1) {
            int transBlue = 0;
            int transGreen = 0;
            int transRed = 0;
            boolean fixPalette = false;
            if (bm.bmBitsPixel <= 8) {
                if (isDib) {
                    final int maxColors = 1 << bm.bmBitsPixel;
                    final byte[] oldColors = new byte[maxColors * 4];
                    OS.GetDIBColorTable(srcHdc, 0, maxColors, oldColors);
                    final int offset = srcImage.transparentPixel * 4;
                    for (int i = 0; i < oldColors.length; i += 4) {
                        if (i != offset && oldColors[offset] == oldColors[i] && oldColors[offset + 1] == oldColors[i + 1] && oldColors[offset + 2] == oldColors[i + 2]) {
                            fixPalette = true;
                            break;
                        }
                    }
                    if (fixPalette) {
                        final byte[] newColors = new byte[oldColors.length];
                        transRed = (transGreen = (transBlue = 255));
                        newColors[offset] = (byte)transBlue;
                        newColors[offset + 1] = (byte)transGreen;
                        newColors[offset + 2] = (byte)transRed;
                        OS.SetDIBColorTable(srcHdc, 0, maxColors, newColors);
                        originalColors = oldColors;
                    }
                    else {
                        transBlue = (oldColors[offset] & 0xFF);
                        transGreen = (oldColors[offset + 1] & 0xFF);
                        transRed = (oldColors[offset + 2] & 0xFF);
                    }
                }
                else {
                    final int numColors = 1 << bm.bmBitsPixel;
                    final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
                    bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
                    bmiHeader.biPlanes = bm.bmPlanes;
                    bmiHeader.biBitCount = bm.bmBitsPixel;
                    final byte[] bmi = new byte[BITMAPINFOHEADER.sizeof + numColors * 4];
                    OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
                    OS.GetDIBits(srcHdc, srcImage.handle, 0, 0, null, bmi, 0);
                    final int offset2 = BITMAPINFOHEADER.sizeof + 4 * srcImage.transparentPixel;
                    transRed = (bmi[offset2 + 2] & 0xFF);
                    transGreen = (bmi[offset2 + 1] & 0xFF);
                    transBlue = (bmi[offset2] & 0xFF);
                }
            }
            else {
                final int pixel = srcImage.transparentPixel;
                switch (bm.bmBitsPixel) {
                    case 16: {
                        transBlue = (pixel & 0x1F) << 3;
                        transGreen = (pixel & 0x3E0) >> 2;
                        transRed = (pixel & 0x7C00) >> 7;
                        break;
                    }
                    case 24: {
                        transBlue = (pixel & 0xFF0000) >> 16;
                        transGreen = (pixel & 0xFF00) >> 8;
                        transRed = (pixel & 0xFF);
                        break;
                    }
                    case 32: {
                        transBlue = (pixel & 0xFF000000) >>> 24;
                        transGreen = (pixel & 0xFF0000) >> 16;
                        transRed = (pixel & 0xFF00) >> 8;
                        break;
                    }
                }
            }
            transparentColor = (transBlue << 16 | transGreen << 8 | transRed);
            if (!fixPalette) {
                srcImage.transparentColor = transparentColor;
            }
        }
        if (originalColors == null) {
            final int mode = OS.SetStretchBltMode(this.handle, 3);
            OS.TransparentBlt(this.handle, destX, destY, destWidth, destHeight, srcHdc, srcX, srcY, srcWidth, srcHeight, transparentColor);
            OS.SetStretchBltMode(this.handle, mode);
        }
        else {
            final long maskHdc = OS.CreateCompatibleDC(this.handle);
            final long maskBitmap = OS.CreateBitmap(imgWidth, imgHeight, 1, 1, null);
            final long oldMaskBitmap = OS.SelectObject(maskHdc, maskBitmap);
            OS.SetBkColor(srcHdc, transparentColor);
            OS.BitBlt(maskHdc, 0, 0, imgWidth, imgHeight, srcHdc, 0, 0, 13369376);
            if (originalColors != null) {
                OS.SetDIBColorTable(srcHdc, 0, 1 << bm.bmBitsPixel, originalColors);
            }
            if (OS.GetDeviceCaps(this.handle, 2) == 2) {
                this.drawBitmapTransparentByClipping(srcHdc, maskHdc, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight, simple, imgWidth, imgHeight);
            }
            else {
                final long tempHdc = OS.CreateCompatibleDC(this.handle);
                final long tempBitmap = OS.CreateCompatibleBitmap(this.handle, destWidth, destHeight);
                final long oldTempBitmap = OS.SelectObject(tempHdc, tempBitmap);
                OS.BitBlt(tempHdc, 0, 0, destWidth, destHeight, this.handle, destX, destY, 13369376);
                if (!simple && (srcWidth != destWidth || srcHeight != destHeight)) {
                    OS.SetStretchBltMode(tempHdc, 3);
                    OS.StretchBlt(tempHdc, 0, 0, destWidth, destHeight, srcHdc, srcX, srcY, srcWidth, srcHeight, 6684742);
                    OS.StretchBlt(tempHdc, 0, 0, destWidth, destHeight, maskHdc, srcX, srcY, srcWidth, srcHeight, 8913094);
                    OS.StretchBlt(tempHdc, 0, 0, destWidth, destHeight, srcHdc, srcX, srcY, srcWidth, srcHeight, 6684742);
                }
                else {
                    OS.BitBlt(tempHdc, 0, 0, destWidth, destHeight, srcHdc, srcX, srcY, 6684742);
                    OS.BitBlt(tempHdc, 0, 0, destWidth, destHeight, maskHdc, srcX, srcY, 8913094);
                    OS.BitBlt(tempHdc, 0, 0, destWidth, destHeight, srcHdc, srcX, srcY, 6684742);
                }
                OS.BitBlt(this.handle, destX, destY, destWidth, destHeight, tempHdc, 0, 0, 13369376);
                OS.SelectObject(tempHdc, oldTempBitmap);
                OS.DeleteDC(tempHdc);
                OS.DeleteObject(tempBitmap);
            }
            OS.SelectObject(maskHdc, oldMaskBitmap);
            OS.DeleteDC(maskHdc);
            OS.DeleteObject(maskBitmap);
        }
        OS.SelectObject(srcHdc, oldSrcBitmap);
        if (hBitmap != srcImage.handle) {
            OS.DeleteObject(hBitmap);
        }
        OS.DeleteDC(srcHdc);
    }
    
    void drawBitmapColor(final Image srcImage, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final int destX, final int destY, final int destWidth, final int destHeight, final boolean simple) {
        final long srcHdc = OS.CreateCompatibleDC(this.handle);
        final long oldSrcBitmap = OS.SelectObject(srcHdc, srcImage.handle);
        final int dwRop = (OS.GetROP2(this.handle) == 7) ? 6684742 : 13369376;
        if (!simple && (srcWidth != destWidth || srcHeight != destHeight)) {
            final int mode = OS.SetStretchBltMode(this.handle, 3);
            OS.StretchBlt(this.handle, destX, destY, destWidth, destHeight, srcHdc, srcX, srcY, srcWidth, srcHeight, dwRop);
            OS.SetStretchBltMode(this.handle, mode);
        }
        else {
            OS.BitBlt(this.handle, destX, destY, destWidth, destHeight, srcHdc, srcX, srcY, dwRop);
        }
        OS.SelectObject(srcHdc, oldSrcBitmap);
        OS.DeleteDC(srcHdc);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2) {
        x1 = DPIUtil.autoScaleUp(this.drawable, x1);
        x2 = DPIUtil.autoScaleUp(this.drawable, x2);
        y1 = DPIUtil.autoScaleUp(this.drawable, y1);
        y2 = DPIUtil.autoScaleUp(this.drawable, y2);
        this.drawLineInPixels(x1, y1, x2, y2);
    }
    
    void drawLineInPixels(int x1, final int y1, int x2, final int y2) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(22777);
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawLine(gdipGraphics, this.data.gdipPen, x1, y1, x2, y2);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            --x1;
            --x2;
        }
        OS.MoveToEx(this.handle, x1, y1, 0L);
        OS.LineTo(this.handle, x2, y2);
        if (this.data.lineWidth <= 1.0f) {
            OS.SetPixel(this.handle, x2, y2, this.data.foreground);
        }
    }
    
    public void drawOval(int x, int y, int width, int height) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.drawOvalInPixels(x, y, width, height);
    }
    
    void drawOvalInPixels(int x, final int y, final int width, final int height) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(22777);
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawEllipse(gdipGraphics, this.data.gdipPen, x, y, width, height);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            --x;
        }
        OS.Ellipse(this.handle, x, y, x + width + 1, y + height + 1);
    }
    
    public void drawPath(final Path path) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (path == null) {
            SWT.error(4);
        }
        if (path.handle == 0L) {
            SWT.error(5);
        }
        this.initGdip();
        this.checkGC(22777);
        final long gdipGraphics = this.data.gdipGraphics;
        Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
        Gdip.Graphics_DrawPath(gdipGraphics, this.data.gdipPen, path.handle);
        Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
    }
    
    public void drawPoint(int x, int y) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        this.drawPointInPixels(x, y);
    }
    
    void drawPointInPixels(final int x, final int y) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics != 0L) {
            this.checkGC(22777);
            Gdip.Graphics_FillRectangle(this.data.gdipGraphics, this.getFgBrush(), x, y, 1, 1);
            return;
        }
        OS.SetPixel(this.handle, x, y, this.data.foreground);
    }
    
    public void drawPolygon(final int[] pointArray) {
        if (pointArray == null) {
            SWT.error(4);
        }
        this.drawPolygonInPixels(DPIUtil.autoScaleUp(this.drawable, pointArray));
    }
    
    void drawPolygonInPixels(final int[] pointArray) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(22777);
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawPolygon(gdipGraphics, this.data.gdipPen, pointArray, pointArray.length / 2);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            for (int i = 0; i < pointArray.length; i += 2) {
                final int n3;
                final int n = n3 = i;
                --pointArray[n3];
            }
        }
        OS.Polygon(this.handle, pointArray, pointArray.length / 2);
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            for (int i = 0; i < pointArray.length; i += 2) {
                final int n4;
                final int n2 = n4 = i;
                ++pointArray[n4];
            }
        }
    }
    
    public void drawPolyline(final int[] pointArray) {
        this.drawPolylineInPixels(DPIUtil.autoScaleUp(this.drawable, pointArray));
    }
    
    void drawPolylineInPixels(final int[] pointArray) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (pointArray == null) {
            SWT.error(4);
        }
        this.checkGC(22777);
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawLines(gdipGraphics, this.data.gdipPen, pointArray, pointArray.length / 2);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            for (int i = 0; i < pointArray.length; i += 2) {
                final int n3;
                final int n = n3 = i;
                --pointArray[n3];
            }
        }
        OS.Polyline(this.handle, pointArray, pointArray.length / 2);
        final int length = pointArray.length;
        if (length >= 2 && this.data.lineWidth <= 1.0f) {
            OS.SetPixel(this.handle, pointArray[length - 2], pointArray[length - 1], this.data.foreground);
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            for (int j = 0; j < pointArray.length; j += 2) {
                final int n4;
                final int n2 = n4 = j;
                ++pointArray[n4];
            }
        }
    }
    
    public void drawRectangle(int x, int y, int width, int height) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.drawRectangleInPixels(x, y, width, height);
    }
    
    void drawRectangleInPixels(int x, int y, int width, int height) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(22777);
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            if (width < 0) {
                x += width;
                width = -width;
            }
            if (height < 0) {
                y += height;
                height = -height;
            }
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawRectangle(gdipGraphics, this.data.gdipPen, x, y, width, height);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            if (this.data.lineWidth > 1.0f) {
                if (this.data.lineWidth % 2.0f == 1.0f) {
                    ++x;
                }
            }
            else if (this.data.hPen != 0L && OS.GetObject(this.data.hPen, 0, 0L) != OS.LOGPEN_sizeof()) {
                ++x;
            }
        }
        OS.Rectangle(this.handle, x, y, x + width + 1, y + height + 1);
    }
    
    public void drawRectangle(Rectangle rect) {
        if (rect == null) {
            SWT.error(4);
        }
        rect = DPIUtil.autoScaleUp(this.drawable, rect);
        this.drawRectangleInPixels(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void drawRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        arcWidth = DPIUtil.autoScaleUp(this.drawable, arcWidth);
        arcHeight = DPIUtil.autoScaleUp(this.drawable, arcHeight);
        this.drawRoundRectangleInPixels(x, y, width, height, arcWidth, arcHeight);
    }
    
    void drawRoundRectangleInPixels(int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(22777);
        if (this.data.gdipGraphics != 0L) {
            this.drawRoundRectangleGdip(this.data.gdipGraphics, this.data.gdipPen, x, y, width, height, arcWidth, arcHeight);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            --x;
        }
        OS.RoundRect(this.handle, x, y, x + width + 1, y + height + 1, arcWidth, arcHeight);
    }
    
    void drawRoundRectangleGdip(final long gdipGraphics, final long pen, final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        int nx = x;
        int ny = y;
        int nw = width;
        int nh = height;
        int naw = arcWidth;
        int nah = arcHeight;
        if (nw < 0) {
            nw = 0 - nw;
            nx -= nw;
        }
        if (nh < 0) {
            nh = 0 - nh;
            ny -= nh;
        }
        if (naw < 0) {
            naw = 0 - naw;
        }
        if (nah < 0) {
            nah = 0 - nah;
        }
        Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
        if (naw == 0 || nah == 0) {
            Gdip.Graphics_DrawRectangle(gdipGraphics, this.data.gdipPen, x, y, width, height);
        }
        else {
            final long path = Gdip.GraphicsPath_new(0);
            if (path == 0L) {
                SWT.error(2);
            }
            if (nw > naw) {
                if (nh > nah) {
                    Gdip.GraphicsPath_AddArc(path, (float)(nx + nw - naw), (float)ny, (float)naw, (float)nah, 0.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(path, (float)nx, (float)ny, (float)naw, (float)nah, -90.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(path, (float)nx, (float)(ny + nh - nah), (float)naw, (float)nah, -180.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(path, (float)(nx + nw - naw), (float)(ny + nh - nah), (float)naw, (float)nah, -270.0f, -90.0f);
                }
                else {
                    Gdip.GraphicsPath_AddArc(path, (float)(nx + nw - naw), (float)ny, (float)naw, (float)nh, -270.0f, -180.0f);
                    Gdip.GraphicsPath_AddArc(path, (float)nx, (float)ny, (float)naw, (float)nh, -90.0f, -180.0f);
                }
            }
            else if (nh > nah) {
                Gdip.GraphicsPath_AddArc(path, (float)nx, (float)ny, (float)nw, (float)nah, 0.0f, -180.0f);
                Gdip.GraphicsPath_AddArc(path, (float)nx, (float)(ny + nh - nah), (float)nw, (float)nah, -180.0f, -180.0f);
            }
            else {
                Gdip.GraphicsPath_AddArc(path, (float)nx, (float)ny, (float)nw, (float)nh, 0.0f, 360.0f);
            }
            Gdip.GraphicsPath_CloseFigure(path);
            Gdip.Graphics_DrawPath(gdipGraphics, pen, path);
            Gdip.GraphicsPath_delete(path);
        }
        Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
    }
    
    public void drawString(final String string, int x, int y) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        this.drawStringInPixels(string, x, y, false);
    }
    
    public void drawString(final String string, int x, int y, final boolean isTransparent) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        this.drawStringInPixels(string, x, y, isTransparent);
    }
    
    void drawStringInPixels(final String string, int x, final int y, final boolean isTransparent) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (string == null) {
            SWT.error(4);
        }
        if (string.isEmpty()) {
            return;
        }
        final char[] buffer = string.toCharArray();
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            this.checkGC(0x5 | (isTransparent ? 0 : 2));
            this.drawText(gdipGraphics, string, x, y, isTransparent ? 1 : 0, null);
            return;
        }
        this.checkGC(772);
        final int oldBkMode = OS.SetBkMode(this.handle, isTransparent ? 1 : 2);
        RECT rect = null;
        SIZE size = null;
        int flags = 0;
        if ((this.data.style & 0x8000000) != 0x0) {
            if (!isTransparent) {
                size = new SIZE();
                OS.GetTextExtentPoint32(this.handle, buffer, buffer.length, size);
                rect = new RECT();
                rect.left = x;
                rect.right = x + size.cx;
                rect.top = y;
                rect.bottom = y + size.cy;
                flags = 4;
            }
            --x;
        }
        if (OS.GetROP2(this.handle) != 7) {
            OS.ExtTextOut(this.handle, x, y, flags, rect, buffer, buffer.length, null);
        }
        else {
            final int foreground = OS.GetTextColor(this.handle);
            if (isTransparent) {
                if (size == null) {
                    size = new SIZE();
                    OS.GetTextExtentPoint32(this.handle, buffer, buffer.length, size);
                }
                final int width = size.cx;
                final int height = size.cy;
                final long hBitmap = OS.CreateCompatibleBitmap(this.handle, width, height);
                if (hBitmap == 0L) {
                    SWT.error(2);
                }
                final long memDC = OS.CreateCompatibleDC(this.handle);
                final long hOldBitmap = OS.SelectObject(memDC, hBitmap);
                OS.PatBlt(memDC, 0, 0, width, height, 66);
                OS.SetBkMode(memDC, 1);
                OS.SetTextColor(memDC, foreground);
                OS.SelectObject(memDC, OS.GetCurrentObject(this.handle, 6));
                OS.ExtTextOut(memDC, 0, 0, 0, null, buffer, buffer.length, null);
                OS.BitBlt(this.handle, x, y, width, height, memDC, 0, 0, 6684742);
                OS.SelectObject(memDC, hOldBitmap);
                OS.DeleteDC(memDC);
                OS.DeleteObject(hBitmap);
            }
            else {
                final int background = OS.GetBkColor(this.handle);
                OS.SetTextColor(this.handle, foreground ^ background);
                OS.ExtTextOut(this.handle, x, y, flags, rect, buffer, buffer.length, null);
                OS.SetTextColor(this.handle, foreground);
            }
        }
        OS.SetBkMode(this.handle, oldBkMode);
    }
    
    public void drawText(final String string, int x, int y) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        this.drawTextInPixels(string, x, y);
    }
    
    void drawTextInPixels(final String string, final int x, final int y) {
        this.drawTextInPixels(string, x, y, 6);
    }
    
    public void drawText(final String string, int x, int y, final boolean isTransparent) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        this.drawTextInPixels(string, x, y, isTransparent);
    }
    
    void drawTextInPixels(final String string, final int x, final int y, final boolean isTransparent) {
        int flags = 6;
        if (isTransparent) {
            flags |= 0x1;
        }
        this.drawTextInPixels(string, x, y, flags);
    }
    
    public void drawText(final String string, int x, int y, final int flags) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        this.drawTextInPixels(string, x, y, flags);
    }
    
    void drawTextInPixels(final String string, final int x, final int y, final int flags) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (string == null) {
            SWT.error(4);
        }
        if (string.isEmpty()) {
            return;
        }
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            this.checkGC(0x5 | (((flags & 0x1) != 0x0) ? 0 : 2));
            this.drawText(gdipGraphics, string, x, y, flags, null);
            return;
        }
        final char[] buffer = string.toCharArray();
        final RECT rect = new RECT();
        OS.SetRect(rect, x, y, 117440511, 117440511);
        int uFormat = 0;
        if ((flags & 0x2) == 0x0) {
            uFormat |= 0x20;
        }
        if ((flags & 0x4) != 0x0) {
            uFormat |= 0x40;
        }
        if ((flags & 0x8) == 0x0) {
            uFormat |= 0x800;
        }
        if ((flags & 0x8) != 0x0 && (this.data.uiState & 0x2) != 0x0) {
            uFormat |= 0x100000;
        }
        this.checkGC(772);
        final int oldBkMode = OS.SetBkMode(this.handle, ((flags & 0x1) != 0x0) ? 1 : 2);
        if (OS.GetROP2(this.handle) != 7) {
            OS.DrawText(this.handle, buffer, buffer.length, rect, uFormat);
        }
        else {
            final int foreground = OS.GetTextColor(this.handle);
            if ((flags & 0x1) != 0x0) {
                OS.DrawText(this.handle, buffer, buffer.length, rect, uFormat | 0x400);
                final int width = rect.right - rect.left;
                final int height = rect.bottom - rect.top;
                final long hBitmap = OS.CreateCompatibleBitmap(this.handle, width, height);
                if (hBitmap == 0L) {
                    SWT.error(2);
                }
                final long memDC = OS.CreateCompatibleDC(this.handle);
                final long hOldBitmap = OS.SelectObject(memDC, hBitmap);
                OS.PatBlt(memDC, 0, 0, width, height, 66);
                OS.SetBkMode(memDC, 1);
                OS.SetTextColor(memDC, foreground);
                OS.SelectObject(memDC, OS.GetCurrentObject(this.handle, 6));
                OS.SetRect(rect, 0, 0, 32767, 32767);
                OS.DrawText(memDC, buffer, buffer.length, rect, uFormat);
                OS.BitBlt(this.handle, x, y, width, height, memDC, 0, 0, 6684742);
                OS.SelectObject(memDC, hOldBitmap);
                OS.DeleteDC(memDC);
                OS.DeleteObject(hBitmap);
            }
            else {
                final int background = OS.GetBkColor(this.handle);
                OS.SetTextColor(this.handle, foreground ^ background);
                OS.DrawText(this.handle, buffer, buffer.length, rect, uFormat);
                OS.SetTextColor(this.handle, foreground);
            }
        }
        OS.SetBkMode(this.handle, oldBkMode);
    }
    
    boolean useGDIP(final long hdc, final char[] buffer) {
        final short[] glyphs = new short[buffer.length];
        OS.GetGlyphIndices(hdc, buffer, buffer.length, glyphs, 1);
        for (int i = 0; i < glyphs.length; ++i) {
            if (glyphs[i] == -1) {
                switch (buffer[i]) {
                    case '\t':
                    case '\n':
                    case '\r': {
                        break;
                    }
                    default: {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    void drawText(final long gdipGraphics, final String string, final int x, final int y, final int flags, final Point size) {
        int length = string.length();
        final char[] chars = string.toCharArray();
        final long hdc = Gdip.Graphics_GetHDC(gdipGraphics);
        long hFont = this.data.hGDIFont;
        if (hFont == 0L && this.data.font != null) {
            hFont = this.data.font.handle;
        }
        long oldFont = 0L;
        if (hFont != 0L) {
            oldFont = OS.SelectObject(hdc, hFont);
        }
        final TEXTMETRIC lptm = new TEXTMETRIC();
        OS.GetTextMetrics(hdc, lptm);
        final boolean gdip = this.useGDIP(hdc, chars);
        if (hFont != 0L) {
            OS.SelectObject(hdc, oldFont);
        }
        Gdip.Graphics_ReleaseHDC(gdipGraphics, hdc);
        if (gdip) {
            this.drawTextGDIP(gdipGraphics, string, x, y, flags, size == null, size);
            return;
        }
        int i = 0;
        int start = 0;
        int end = 0;
        int drawX = x;
        int drawY = y;
        int width = 0;
        int mnemonicIndex = -1;
        if ((flags & 0xE) != 0x0) {
            final int tabWidth = lptm.tmAveCharWidth * 8;
            while (i < length) {
                final char[] array = chars;
                final int n = end++;
                final char c2 = chars[i++];
                array[n] = c2;
                final char c3 = c2;
                switch (c3) {
                    case '\t': {
                        if ((flags & 0x4) != 0x0) {
                            final int l = end - start - 1;
                            final RectF bounds = this.drawText(gdipGraphics, chars, start, l, drawX, drawY, flags, mnemonicIndex, lptm, size == null);
                            drawX += (int)Math.ceil(bounds.Width);
                            drawX = x + ((drawX - x) / tabWidth + 1) * tabWidth;
                            mnemonicIndex = -1;
                            start = end;
                            continue;
                        }
                        continue;
                    }
                    case '&': {
                        if ((flags & 0x8) == 0x0) {
                            continue;
                        }
                        if (i == length) {
                            --end;
                            continue;
                        }
                        if (chars[i] == '&') {
                            ++i;
                            continue;
                        }
                        mnemonicIndex = --end - start;
                        continue;
                    }
                    case '\n':
                    case '\r': {
                        if ((flags & 0x2) != 0x0) {
                            final int l = end - start - 1;
                            if (c3 == '\r' && end != length && chars[end] == '\n') {
                                ++end;
                                ++i;
                            }
                            final RectF bounds = this.drawText(gdipGraphics, chars, start, l, drawX, drawY, flags, mnemonicIndex, lptm, size == null);
                            drawY += (int)Math.ceil(bounds.Height);
                            width = Math.max(width, drawX + (int)Math.ceil(bounds.Width));
                            drawX = x;
                            mnemonicIndex = -1;
                            start = end;
                            continue;
                        }
                        continue;
                    }
                    default: {
                        continue;
                    }
                }
            }
            length = end;
        }
        final RectF bounds2 = this.drawText(gdipGraphics, chars, start, length - start, drawX, drawY, flags, mnemonicIndex, lptm, size == null);
        if (size != null) {
            drawY += (int)Math.ceil(bounds2.Height);
            width = Math.max(width, drawX + (int)Math.ceil(bounds2.Width));
            size.x = width;
            size.y = drawY;
        }
    }
    
    RectF drawText(final long gdipGraphics, char[] buffer, final int start, final int length, final int x, final int y, final int flags, final int mnemonicIndex, final TEXTMETRIC lptm, final boolean draw) {
        final boolean drawMnemonic = draw && mnemonicIndex != -1 && (this.data.uiState & 0x2) == 0x0;
        final boolean needsBounds = !draw || drawMnemonic || (flags & 0x1) == 0x0 || (this.data.style & 0x8000000) != 0x0 || (flags & 0x2) != 0x0;
        if (length <= 0) {
            RectF bounds = null;
            if (needsBounds) {
                bounds = new RectF();
                bounds.Height = (float)lptm.tmHeight;
            }
            return bounds;
        }
        int nGlyphs = length * 3 / 2 + 16;
        final GCP_RESULTS result = new GCP_RESULTS();
        result.lStructSize = GCP_RESULTS.sizeof;
        result.nGlyphs = nGlyphs;
        final long hHeap = OS.GetProcessHeap();
        final GCP_RESULTS gcp_RESULTS = result;
        final long heapAlloc = OS.HeapAlloc(hHeap, 8, nGlyphs * 4);
        gcp_RESULTS.lpDx = heapAlloc;
        final long lpDx = heapAlloc;
        final GCP_RESULTS gcp_RESULTS2 = result;
        final long heapAlloc2 = OS.HeapAlloc(hHeap, 8, nGlyphs * 2);
        gcp_RESULTS2.lpGlyphs = heapAlloc2;
        final long lpGlyphs = heapAlloc2;
        long lpOrder = 0L;
        final int dwFlags = 50;
        if (drawMnemonic) {
            final GCP_RESULTS gcp_RESULTS3 = result;
            final long heapAlloc3 = OS.HeapAlloc(hHeap, 8, nGlyphs * 4);
            gcp_RESULTS3.lpOrder = heapAlloc3;
            lpOrder = heapAlloc3;
        }
        final long hdc = Gdip.Graphics_GetHDC(gdipGraphics);
        long hFont = this.data.hGDIFont;
        if (hFont == 0L && this.data.font != null) {
            hFont = this.data.font.handle;
        }
        long oldFont = 0L;
        if (hFont != 0L) {
            oldFont = OS.SelectObject(hdc, hFont);
        }
        if (start != 0) {
            final char[] temp = new char[length];
            System.arraycopy(buffer, start, temp, 0, length);
            buffer = temp;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            OS.SetLayout(hdc, OS.GetLayout(hdc) | 0x1);
        }
        OS.GetCharacterPlacement(hdc, buffer, length, 0, result, 50);
        if ((this.data.style & 0x8000000) != 0x0) {
            OS.SetLayout(hdc, OS.GetLayout(hdc) & 0xFFFFFFFE);
        }
        if (hFont != 0L) {
            OS.SelectObject(hdc, oldFont);
        }
        Gdip.Graphics_ReleaseHDC(gdipGraphics, hdc);
        nGlyphs = result.nGlyphs;
        int drawX = x;
        final int drawY = y + lptm.tmAscent;
        final int[] dx = new int[nGlyphs];
        OS.MoveMemory(dx, result.lpDx, nGlyphs * 4);
        final float[] points = new float[dx.length * 2];
        int i = 0;
        int j = 0;
        while (i < dx.length) {
            points[j++] = (float)drawX;
            points[j++] = (float)drawY;
            drawX += dx[i];
            ++i;
        }
        RectF bounds2 = null;
        if (needsBounds) {
            bounds2 = new RectF();
            Gdip.Graphics_MeasureDriverString(gdipGraphics, lpGlyphs, nGlyphs, this.data.gdipFont, points, 0, 0L, bounds2);
        }
        if (draw) {
            if ((flags & 0x1) == 0x0) {
                Gdip.Graphics_FillRectangle(gdipGraphics, this.data.gdipBrush, x, y, (int)Math.ceil(bounds2.Width), (int)Math.ceil(bounds2.Height));
            }
            int gstate = 0;
            final long brush = this.getFgBrush();
            if ((this.data.style & 0x8000000) != 0x0) {
                switch (Gdip.Brush_GetType(brush)) {
                    case 4: {
                        Gdip.LinearGradientBrush_ScaleTransform(brush, -1.0f, 1.0f, 0);
                        Gdip.LinearGradientBrush_TranslateTransform(brush, -2 * x - bounds2.Width, 0.0f, 0);
                        break;
                    }
                    case 2: {
                        Gdip.TextureBrush_ScaleTransform(brush, -1.0f, 1.0f, 0);
                        Gdip.TextureBrush_TranslateTransform(brush, -2 * x - bounds2.Width, 0.0f, 0);
                        break;
                    }
                }
                gstate = Gdip.Graphics_Save(gdipGraphics);
                Gdip.Graphics_ScaleTransform(gdipGraphics, -1.0f, 1.0f, 0);
                Gdip.Graphics_TranslateTransform(gdipGraphics, -2 * x - bounds2.Width, 0.0f, 0);
            }
            Gdip.Graphics_DrawDriverString(gdipGraphics, lpGlyphs, result.nGlyphs, this.data.gdipFont, brush, points, 0, 0L);
            if ((this.data.style & 0x8000000) != 0x0) {
                switch (Gdip.Brush_GetType(brush)) {
                    case 4: {
                        Gdip.LinearGradientBrush_ResetTransform(brush);
                        break;
                    }
                    case 2: {
                        Gdip.TextureBrush_ResetTransform(brush);
                        break;
                    }
                }
                Gdip.Graphics_Restore(gdipGraphics, gstate);
            }
            if (drawMnemonic) {
                final long pen = Gdip.Pen_new(brush, 1.0f);
                if (pen != 0L) {
                    final int[] order = { 0 };
                    OS.MoveMemory(order, result.lpOrder + mnemonicIndex * 4, 4);
                    int mnemonicLeft;
                    int mnemonicRight;
                    if ((this.data.style & 0x8000000) != 0x0) {
                        mnemonicLeft = (int)Math.ceil(bounds2.Width) - (int)points[order[0] * 2] + 2 * x;
                        mnemonicRight = mnemonicLeft - dx[order[0]];
                    }
                    else {
                        mnemonicLeft = (int)points[order[0] * 2];
                        mnemonicRight = mnemonicLeft + dx[order[0]];
                    }
                    final int mnemonicY = y + lptm.tmAscent + 2;
                    final int smoothingMode = Gdip.Graphics_GetSmoothingMode(gdipGraphics);
                    Gdip.Graphics_SetSmoothingMode(gdipGraphics, 3);
                    Gdip.Graphics_DrawLine(gdipGraphics, pen, mnemonicLeft, mnemonicY, mnemonicRight, mnemonicY);
                    Gdip.Graphics_SetSmoothingMode(gdipGraphics, smoothingMode);
                    Gdip.Pen_delete(pen);
                }
            }
        }
        if (lpOrder != 0L) {
            OS.HeapFree(hHeap, 0, lpOrder);
        }
        OS.HeapFree(hHeap, 0, lpGlyphs);
        OS.HeapFree(hHeap, 0, lpDx);
        return bounds2;
    }
    
    void drawTextGDIP(final long gdipGraphics, final String string, final int x, final int y, final int flags, final boolean draw, final Point size) {
        final boolean needsBounds = !draw || (flags & 0x1) == 0x0;
        final int length = string.length();
        char[] buffer;
        if (length != 0) {
            buffer = string.toCharArray();
        }
        else {
            if (draw) {
                return;
            }
            buffer = new char[] { ' ' };
        }
        final PointF pt = new PointF();
        final long format = Gdip.StringFormat_Clone(Gdip.StringFormat_GenericTypographic());
        int formatFlags = Gdip.StringFormat_GetFormatFlags(format) | 0x800;
        if ((this.data.style & 0x8000000) != 0x0) {
            formatFlags |= 0x1;
        }
        Gdip.StringFormat_SetFormatFlags(format, formatFlags);
        final float[] tabs = ((flags & 0x4) != 0x0) ? new float[] { this.measureSpace(this.data.gdipFont, format) * 8.0f } : new float[] { 0.0f };
        Gdip.StringFormat_SetTabStops(format, 0.0f, tabs.length, tabs);
        int hotkeyPrefix = ((flags & 0x8) != 0x0) ? 1 : 0;
        if ((flags & 0x8) != 0x0 && (this.data.uiState & 0x2) != 0x0) {
            hotkeyPrefix = 2;
        }
        Gdip.StringFormat_SetHotkeyPrefix(format, hotkeyPrefix);
        RectF bounds = null;
        if (needsBounds) {
            bounds = new RectF();
            Gdip.Graphics_MeasureString(gdipGraphics, buffer, buffer.length, this.data.gdipFont, pt, format, bounds);
        }
        if (draw) {
            if ((flags & 0x1) == 0x0) {
                Gdip.Graphics_FillRectangle(gdipGraphics, this.data.gdipBrush, x, y, (int)Math.ceil(bounds.Width), (int)Math.ceil(bounds.Height));
            }
            int gstate = 0;
            final long brush = this.getFgBrush();
            if ((this.data.style & 0x8000000) != 0x0) {
                switch (Gdip.Brush_GetType(brush)) {
                    case 4: {
                        Gdip.LinearGradientBrush_ScaleTransform(brush, -1.0f, 1.0f, 0);
                        Gdip.LinearGradientBrush_TranslateTransform(brush, (float)(-2 * x), 0.0f, 0);
                        break;
                    }
                    case 2: {
                        Gdip.TextureBrush_ScaleTransform(brush, -1.0f, 1.0f, 0);
                        Gdip.TextureBrush_TranslateTransform(brush, (float)(-2 * x), 0.0f, 0);
                        break;
                    }
                }
                gstate = Gdip.Graphics_Save(gdipGraphics);
                Gdip.Graphics_ScaleTransform(gdipGraphics, -1.0f, 1.0f, 0);
                Gdip.Graphics_TranslateTransform(gdipGraphics, (float)(-2 * x), 0.0f, 0);
            }
            pt.X = (float)x;
            pt.Y = (float)y;
            Gdip.Graphics_DrawString(gdipGraphics, buffer, buffer.length, this.data.gdipFont, pt, format, brush);
            if ((this.data.style & 0x8000000) != 0x0) {
                switch (Gdip.Brush_GetType(brush)) {
                    case 4: {
                        Gdip.LinearGradientBrush_ResetTransform(brush);
                        break;
                    }
                    case 2: {
                        Gdip.TextureBrush_ResetTransform(brush);
                        break;
                    }
                }
                Gdip.Graphics_Restore(gdipGraphics, gstate);
            }
        }
        Gdip.StringFormat_delete(format);
        if (length == 0) {
            bounds.Width = 0.0f;
        }
        if (size != null) {
            size.x = (int)Math.ceil(bounds.Width);
            size.y = (int)Math.ceil(bounds.Height);
        }
    }
    
    @Override
    public boolean equals(final Object object) {
        return object == this || (object instanceof GC && this.handle == ((GC)object).handle);
    }
    
    public void fillArc(int x, int y, int width, int height, final int startAngle, final int arcAngle) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.fillArcInPixels(x, y, width, height, startAngle, arcAngle);
    }
    
    void fillArcInPixels(int x, int y, int width, int height, int startAngle, int arcAngle) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (width < 0) {
            x += width;
            width = -width;
        }
        if (height < 0) {
            y += height;
            height = -height;
        }
        if (width == 0 || height == 0 || arcAngle == 0) {
            return;
        }
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            if (width == height) {
                Gdip.Graphics_FillPie(gdipGraphics, this.data.gdipBrush, x, y, width, height, (float)(-startAngle), (float)(-arcAngle));
            }
            else {
                final int state = Gdip.Graphics_Save(gdipGraphics);
                Gdip.Graphics_TranslateTransform(gdipGraphics, (float)x, (float)y, 0);
                Gdip.Graphics_ScaleTransform(gdipGraphics, (float)width, (float)height, 0);
                Gdip.Graphics_FillPie(gdipGraphics, this.data.gdipBrush, 0, 0, 1, 1, (float)(-startAngle), (float)(-arcAngle));
                Gdip.Graphics_Restore(gdipGraphics, state);
            }
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            --x;
        }
        int x3;
        int x2;
        int y3;
        int y2;
        if (arcAngle >= 360 || arcAngle <= -360) {
            x2 = (x3 = x + width);
            y2 = (y3 = y + height / 2);
        }
        else {
            final boolean isNegative = arcAngle < 0;
            arcAngle += startAngle;
            if (isNegative) {
                final int tmp = startAngle;
                startAngle = arcAngle;
                arcAngle = tmp;
            }
            x2 = cos(startAngle, width) + x + width / 2;
            y2 = -1 * sin(startAngle, height) + y + height / 2;
            x3 = cos(arcAngle, width) + x + width / 2;
            y3 = -1 * sin(arcAngle, height) + y + height / 2;
        }
        OS.Pie(this.handle, x, y, x + width + 1, y + height + 1, x2, y2, x3, y3);
    }
    
    public void fillGradientRectangle(int x, int y, int width, int height, final boolean vertical) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.fillGradientRectangleInPixels(x, y, width, height, vertical);
    }
    
    void fillGradientRectangleInPixels(int x, int y, int width, int height, final boolean vertical) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (width == 0 || height == 0) {
            return;
        }
        final RGB backgroundRGB = this.getBackground().getRGB();
        final RGB foregroundRGB;
        RGB fromRGB = foregroundRGB = this.getForeground().getRGB();
        RGB toRGB = backgroundRGB;
        boolean swapColors = false;
        if (width < 0) {
            x += width;
            width = -width;
            if (!vertical) {
                swapColors = true;
            }
        }
        if (height < 0) {
            y += height;
            height = -height;
            if (vertical) {
                swapColors = true;
            }
        }
        if (swapColors) {
            fromRGB = backgroundRGB;
            toRGB = foregroundRGB;
        }
        if (fromRGB.equals(toRGB)) {
            this.fillRectangleInPixels(x, y, width, height);
            return;
        }
        if (this.data.gdipGraphics != 0L) {
            this.initGdip();
            final PointF p1 = new PointF();
            final PointF p2 = new PointF();
            p1.X = (float)x;
            p1.Y = (float)y;
            if (vertical) {
                p2.X = p1.X;
                p2.Y = p1.Y + height;
            }
            else {
                p2.X = p1.X + width;
                p2.Y = p1.Y;
            }
            final int fromGpColor = this.data.alpha << 24 | (fromRGB.red & 0xFF) << 16 | (fromRGB.green & 0xFF) << 8 | (fromRGB.blue & 0xFF);
            final int toGpColor = this.data.alpha << 24 | (toRGB.red & 0xFF) << 16 | (toRGB.green & 0xFF) << 8 | (toRGB.blue & 0xFF);
            final long brush = Gdip.LinearGradientBrush_new(p1, p2, fromGpColor, toGpColor);
            Gdip.Graphics_FillRectangle(this.data.gdipGraphics, brush, x, y, width, height);
            Gdip.LinearGradientBrush_delete(brush);
            return;
        }
        if (OS.GetROP2(this.handle) != 7 && OS.GetDeviceCaps(this.handle, 2) != 2) {
            final long hHeap = OS.GetProcessHeap();
            final long pMesh = OS.HeapAlloc(hHeap, 8, GRADIENT_RECT.sizeof + TRIVERTEX.sizeof * 2);
            if (pMesh == 0L) {
                SWT.error(2);
            }
            final long pVertex = pMesh + GRADIENT_RECT.sizeof;
            final GRADIENT_RECT gradientRect = new GRADIENT_RECT();
            gradientRect.UpperLeft = 0;
            gradientRect.LowerRight = 1;
            OS.MoveMemory(pMesh, gradientRect, GRADIENT_RECT.sizeof);
            final TRIVERTEX trivertex = new TRIVERTEX();
            trivertex.x = x;
            trivertex.y = y;
            trivertex.Red = (short)(fromRGB.red << 8 | fromRGB.red);
            trivertex.Green = (short)(fromRGB.green << 8 | fromRGB.green);
            trivertex.Blue = (short)(fromRGB.blue << 8 | fromRGB.blue);
            trivertex.Alpha = -1;
            OS.MoveMemory(pVertex, trivertex, TRIVERTEX.sizeof);
            trivertex.x = x + width;
            trivertex.y = y + height;
            trivertex.Red = (short)(toRGB.red << 8 | toRGB.red);
            trivertex.Green = (short)(toRGB.green << 8 | toRGB.green);
            trivertex.Blue = (short)(toRGB.blue << 8 | toRGB.blue);
            trivertex.Alpha = -1;
            OS.MoveMemory(pVertex + TRIVERTEX.sizeof, trivertex, TRIVERTEX.sizeof);
            final boolean success = OS.GradientFill(this.handle, pVertex, 2, pMesh, 1, vertical ? 1 : 0);
            OS.HeapFree(hHeap, 0, pMesh);
            if (success) {
                return;
            }
        }
        final int depth = OS.GetDeviceCaps(this.handle, 12);
        final int bitResolution = (depth >= 24) ? 8 : ((depth >= 15) ? 5 : 0);
        ImageData.fillGradientRectangle(this, this.data.device, x, y, width, height, vertical, fromRGB, toRGB, bitResolution, bitResolution, bitResolution);
    }
    
    public void fillOval(int x, int y, int width, int height) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.fillOvalInPixels(x, y, width, height);
    }
    
    void fillOvalInPixels(int x, final int y, final int width, final int height) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (this.data.gdipGraphics != 0L) {
            Gdip.Graphics_FillEllipse(this.data.gdipGraphics, this.data.gdipBrush, x, y, width, height);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            --x;
        }
        OS.Ellipse(this.handle, x, y, x + width + 1, y + height + 1);
    }
    
    public void fillPath(final Path path) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (path == null) {
            SWT.error(4);
        }
        if (path.handle == 0L) {
            SWT.error(5);
        }
        this.initGdip();
        this.checkGC(9218);
        final int mode = (OS.GetPolyFillMode(this.handle) == 2) ? 1 : 0;
        Gdip.GraphicsPath_SetFillMode(path.handle, mode);
        Gdip.Graphics_FillPath(this.data.gdipGraphics, this.data.gdipBrush, path.handle);
    }
    
    public void fillPolygon(final int[] pointArray) {
        if (pointArray == null) {
            SWT.error(4);
        }
        this.fillPolygonInPixels(DPIUtil.autoScaleUp(this.drawable, pointArray));
    }
    
    void fillPolygonInPixels(final int[] pointArray) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (this.data.gdipGraphics != 0L) {
            final int mode = (OS.GetPolyFillMode(this.handle) == 2) ? 1 : 0;
            final float offsetCorrection = 0.5f;
            Gdip.Graphics_TranslateTransform(this.data.gdipGraphics, this.data.gdipXOffset + 0.5f, this.data.gdipYOffset + 0.5f, 0);
            Gdip.Graphics_FillPolygon(this.data.gdipGraphics, this.data.gdipBrush, pointArray, pointArray.length / 2, mode);
            Gdip.Graphics_TranslateTransform(this.data.gdipGraphics, -(this.data.gdipXOffset + 0.5f), -(this.data.gdipYOffset + 0.5f), 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            for (int i = 0; i < pointArray.length; i += 2) {
                final int n3;
                final int n = n3 = i;
                --pointArray[n3];
            }
        }
        OS.Polygon(this.handle, pointArray, pointArray.length / 2);
        if ((this.data.style & 0x8000000) != 0x0) {
            for (int i = 0; i < pointArray.length; i += 2) {
                final int n4;
                final int n2 = n4 = i;
                ++pointArray[n4];
            }
        }
    }
    
    public void fillRectangle(int x, int y, int width, int height) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.fillRectangleInPixels(x, y, width, height);
    }
    
    void fillRectangleInPixels(int x, int y, int width, int height) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (this.data.gdipGraphics != 0L) {
            if (width < 0) {
                x += width;
                width = -width;
            }
            if (height < 0) {
                y += height;
                height = -height;
            }
            Gdip.Graphics_FillRectangle(this.data.gdipGraphics, this.data.gdipBrush, x, y, width, height);
            return;
        }
        final int dwRop = (OS.GetROP2(this.handle) == 7) ? 5898313 : 15728673;
        OS.PatBlt(this.handle, x, y, width, height, dwRop);
    }
    
    public void fillRectangle(Rectangle rect) {
        if (rect == null) {
            SWT.error(4);
        }
        rect = DPIUtil.autoScaleUp(this.drawable, rect);
        this.fillRectangleInPixels(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void fillRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        arcWidth = DPIUtil.autoScaleUp(this.drawable, arcWidth);
        arcHeight = DPIUtil.autoScaleUp(this.drawable, arcHeight);
        this.fillRoundRectangleInPixels(x, y, width, height, arcWidth, arcHeight);
    }
    
    void fillRoundRectangleInPixels(int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (this.data.gdipGraphics != 0L) {
            this.fillRoundRectangleGdip(this.data.gdipGraphics, this.data.gdipBrush, x, y, width, height, arcWidth, arcHeight);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            --x;
        }
        OS.RoundRect(this.handle, x, y, x + width + 1, y + height + 1, arcWidth, arcHeight);
    }
    
    void fillRoundRectangleGdip(final long gdipGraphics, final long brush, final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        int nx = x;
        int ny = y;
        int nw = width;
        int nh = height;
        int naw = arcWidth;
        int nah = arcHeight;
        if (nw < 0) {
            nw = 0 - nw;
            nx -= nw;
        }
        if (nh < 0) {
            nh = 0 - nh;
            ny -= nh;
        }
        if (naw < 0) {
            naw = 0 - naw;
        }
        if (nah < 0) {
            nah = 0 - nah;
        }
        if (naw == 0 || nah == 0) {
            Gdip.Graphics_FillRectangle(this.data.gdipGraphics, this.data.gdipBrush, x, y, width, height);
        }
        else {
            final long path = Gdip.GraphicsPath_new(0);
            if (path == 0L) {
                SWT.error(2);
            }
            if (nw > naw) {
                if (nh > nah) {
                    Gdip.GraphicsPath_AddArc(path, (float)(nx + nw - naw), (float)ny, (float)naw, (float)nah, 0.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(path, (float)nx, (float)ny, (float)naw, (float)nah, -90.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(path, (float)nx, (float)(ny + nh - nah), (float)naw, (float)nah, -180.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(path, (float)(nx + nw - naw), (float)(ny + nh - nah), (float)naw, (float)nah, -270.0f, -90.0f);
                }
                else {
                    Gdip.GraphicsPath_AddArc(path, (float)(nx + nw - naw), (float)ny, (float)naw, (float)nh, -270.0f, -180.0f);
                    Gdip.GraphicsPath_AddArc(path, (float)nx, (float)ny, (float)naw, (float)nh, -90.0f, -180.0f);
                }
            }
            else if (nh > nah) {
                Gdip.GraphicsPath_AddArc(path, (float)nx, (float)ny, (float)nw, (float)nah, 0.0f, -180.0f);
                Gdip.GraphicsPath_AddArc(path, (float)nx, (float)(ny + nh - nah), (float)nw, (float)nah, -180.0f, -180.0f);
            }
            else {
                Gdip.GraphicsPath_AddArc(path, (float)nx, (float)ny, (float)nw, (float)nh, 0.0f, 360.0f);
            }
            Gdip.GraphicsPath_CloseFigure(path);
            Gdip.Graphics_FillPath(gdipGraphics, brush, path);
            Gdip.GraphicsPath_delete(path);
        }
    }
    
    void flush() {
        if (this.data.gdipGraphics != 0L) {
            Gdip.Graphics_Flush(this.data.gdipGraphics, 0);
            final long hdc = Gdip.Graphics_GetHDC(this.data.gdipGraphics);
            Gdip.Graphics_ReleaseHDC(this.data.gdipGraphics, hdc);
        }
    }
    
    public int getAdvanceWidth(final char ch) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(4);
        final int[] width = { 0 };
        OS.GetCharWidth(this.handle, ch, ch, width);
        return width[0];
    }
    
    public boolean getAdvanced() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.gdipGraphics != 0L;
    }
    
    public int getAlpha() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.alpha;
    }
    
    public int getAntialias() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0L) {
            return -1;
        }
        final int mode = Gdip.Graphics_GetSmoothingMode(this.data.gdipGraphics);
        switch (mode) {
            case 0: {
                return -1;
            }
            case 1:
            case 3: {
                return 0;
            }
            case 2:
            case 4:
            case 5: {
                return 1;
            }
            default: {
                return -1;
            }
        }
    }
    
    public Color getBackground() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return Color.win32_new(this.data.device, this.data.background);
    }
    
    public Pattern getBackgroundPattern() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.backgroundPattern;
    }
    
    public int getCharWidth(final char ch) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(4);
        final int[] width = new int[3];
        if (OS.GetCharABCWidths(this.handle, ch, ch, width)) {
            return width[1];
        }
        final TEXTMETRIC lptm = new TEXTMETRIC();
        OS.GetTextMetrics(this.handle, lptm);
        final SIZE size = new SIZE();
        OS.GetTextExtentPoint32(this.handle, new char[] { ch }, 1, size);
        return size.cx - lptm.tmOverhang;
    }
    
    public Rectangle getClipping() {
        return DPIUtil.autoScaleDown(this.drawable, this.getClippingInPixels());
    }
    
    Rectangle getClippingInPixels() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            final Rect rect = new Rect();
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 3);
            Gdip.Graphics_GetVisibleClipBounds(gdipGraphics, rect);
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
            return new Rectangle(rect.X, rect.Y, rect.Width, rect.Height);
        }
        final RECT rect2 = new RECT();
        OS.GetClipBox(this.handle, rect2);
        return new Rectangle(rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
    }
    
    public void getClipping(final Region region) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (region == null) {
            SWT.error(4);
        }
        if (region.isDisposed()) {
            SWT.error(5);
        }
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            final long rgn = Gdip.Region_new();
            Gdip.Graphics_GetClip(this.data.gdipGraphics, rgn);
            if (Gdip.Region_IsInfinite(rgn, gdipGraphics)) {
                final Rect rect = new Rect();
                Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 3);
                Gdip.Graphics_GetVisibleClipBounds(gdipGraphics, rect);
                Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
                OS.SetRectRgn(region.handle, rect.X, rect.Y, rect.X + rect.Width, rect.Y + rect.Height);
            }
            else {
                final long matrix = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
                final long identity = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
                Gdip.Graphics_GetTransform(gdipGraphics, matrix);
                Gdip.Graphics_SetTransform(gdipGraphics, identity);
                final long hRgn = Gdip.Region_GetHRGN(rgn, this.data.gdipGraphics);
                Gdip.Graphics_SetTransform(gdipGraphics, matrix);
                Gdip.Matrix_delete(identity);
                Gdip.Matrix_delete(matrix);
                final POINT pt = new POINT();
                OS.GetWindowOrgEx(this.handle, pt);
                OS.OffsetRgn(hRgn, pt.x, pt.y);
                OS.CombineRgn(region.handle, hRgn, 0L, 5);
                OS.DeleteObject(hRgn);
            }
            Gdip.Region_delete(rgn);
            return;
        }
        final POINT pt2 = new POINT();
        OS.GetWindowOrgEx(this.handle, pt2);
        final int result = OS.GetClipRgn(this.handle, region.handle);
        if (result != 1) {
            final RECT rect2 = new RECT();
            OS.GetClipBox(this.handle, rect2);
            OS.SetRectRgn(region.handle, rect2.left, rect2.top, rect2.right, rect2.bottom);
        }
        else {
            OS.OffsetRgn(region.handle, pt2.x, pt2.y);
        }
        final long metaRgn = OS.CreateRectRgn(0, 0, 0, 0);
        if (OS.GetMetaRgn(this.handle, metaRgn) != 0) {
            OS.OffsetRgn(metaRgn, pt2.x, pt2.y);
            OS.CombineRgn(region.handle, metaRgn, region.handle, 1);
        }
        OS.DeleteObject(metaRgn);
        final long hwnd = this.data.hwnd;
        if (hwnd != 0L && this.data.ps != null) {
            long sysRgn = OS.CreateRectRgn(0, 0, 0, 0);
            if (OS.GetRandomRgn(this.handle, sysRgn, 4) == 1) {
                if ((OS.GetLayout(this.handle) & 0x1) != 0x0) {
                    final int nBytes = OS.GetRegionData(sysRgn, 0, null);
                    final int[] lpRgnData = new int[nBytes / 4];
                    OS.GetRegionData(sysRgn, nBytes, lpRgnData);
                    final long newSysRgn = OS.ExtCreateRegion(new float[] { -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f }, nBytes, lpRgnData);
                    OS.DeleteObject(sysRgn);
                    sysRgn = newSysRgn;
                }
                OS.MapWindowPoints(0L, hwnd, pt2, 1);
                OS.OffsetRgn(sysRgn, pt2.x, pt2.y);
                OS.CombineRgn(region.handle, sysRgn, region.handle, 1);
            }
            OS.DeleteObject(sysRgn);
        }
    }
    
    long getFgBrush() {
        return (this.data.foregroundPattern != null) ? this.data.foregroundPattern.handle : this.data.gdipFgBrush;
    }
    
    public int getFillRule() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return (OS.GetPolyFillMode(this.handle) == 2) ? 2 : 1;
    }
    
    public Font getFont() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.font;
    }
    
    public FontMetrics getFontMetrics() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(4);
        final TEXTMETRIC lptm = new TEXTMETRIC();
        OS.GetTextMetrics(this.handle, lptm);
        return FontMetrics.win32_new(lptm);
    }
    
    public Color getForeground() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return Color.win32_new(this.data.device, this.data.foreground);
    }
    
    public Pattern getForegroundPattern() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.foregroundPattern;
    }
    
    public GCData getGCData() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data;
    }
    
    public int getInterpolation() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0L) {
            return -1;
        }
        final int mode = Gdip.Graphics_GetInterpolationMode(this.data.gdipGraphics);
        switch (mode) {
            case 0: {
                return -1;
            }
            case 5: {
                return 0;
            }
            case 1:
            case 3: {
                return 1;
            }
            case 2:
            case 4:
            case 6:
            case 7: {
                return 2;
            }
            default: {
                return -1;
            }
        }
    }
    
    public LineAttributes getLineAttributes() {
        final LineAttributes attributes = this.getLineAttributesInPixels();
        attributes.width = DPIUtil.autoScaleDown(this.drawable, attributes.width);
        return attributes;
    }
    
    LineAttributes getLineAttributesInPixels() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        float[] dashes = null;
        if (this.data.lineDashes != null) {
            dashes = new float[this.data.lineDashes.length];
            System.arraycopy(this.data.lineDashes, 0, dashes, 0, dashes.length);
        }
        return new LineAttributes(this.data.lineWidth, this.data.lineCap, this.data.lineJoin, this.data.lineStyle, dashes, this.data.lineDashesOffset, this.data.lineMiterLimit);
    }
    
    public int getLineCap() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.lineCap;
    }
    
    public int[] getLineDash() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.lineDashes == null) {
            return null;
        }
        final int[] lineDashes = new int[this.data.lineDashes.length];
        for (int i = 0; i < lineDashes.length; ++i) {
            lineDashes[i] = (int)this.data.lineDashes[i];
        }
        return lineDashes;
    }
    
    public int getLineJoin() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.lineJoin;
    }
    
    public int getLineStyle() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.lineStyle;
    }
    
    public int getLineWidth() {
        return DPIUtil.autoScaleDown(this.drawable, this.getLineWidthInPixels());
    }
    
    int getLineWidthInPixels() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return (int)this.data.lineWidth;
    }
    
    public int getStyle() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return this.data.style;
    }
    
    public int getTextAntialias() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0L) {
            return -1;
        }
        final int mode = Gdip.Graphics_GetTextRenderingHint(this.data.gdipGraphics);
        switch (mode) {
            case 0: {
                return -1;
            }
            case 1:
            case 2: {
                return 0;
            }
            case 3:
            case 4:
            case 5: {
                return 1;
            }
            default: {
                return -1;
            }
        }
    }
    
    public void getTransform(final Transform transform) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (transform == null) {
            SWT.error(4);
        }
        if (transform.isDisposed()) {
            SWT.error(5);
        }
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            Gdip.Graphics_GetTransform(gdipGraphics, transform.handle);
            final long identity = this.identity();
            Gdip.Matrix_Invert(identity);
            Gdip.Matrix_Multiply(transform.handle, identity, 1);
            Gdip.Matrix_delete(identity);
        }
        else {
            transform.setElements(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        }
    }
    
    public boolean getXORMode() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        return OS.GetROP2(this.handle) == 7;
    }
    
    void initGdip() {
        this.data.device.checkGDIP();
        long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            return;
        }
        final long hRgn = OS.CreateRectRgn(0, 0, 0, 0);
        final int result = OS.GetClipRgn(this.handle, hRgn);
        final POINT pt = new POINT();
        OS.GetWindowOrgEx(this.handle, pt);
        OS.OffsetRgn(hRgn, pt.x, pt.y);
        OS.SelectClipRgn(this.handle, 0L);
        if ((this.data.style & 0x8000000) != 0x0) {
            OS.SetLayout(this.handle, OS.GetLayout(this.handle) & 0xFFFFFFFE);
        }
        final GCData data = this.data;
        final long graphics_new = Gdip.Graphics_new(this.handle);
        data.gdipGraphics = graphics_new;
        gdipGraphics = graphics_new;
        if (gdipGraphics == 0L) {
            SWT.error(2);
        }
        Gdip.Graphics_SetPageUnit(gdipGraphics, 2);
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
        if ((this.data.style & 0x8000000) != 0x0) {
            final long matrix = this.identity();
            Gdip.Graphics_SetTransform(gdipGraphics, matrix);
            Gdip.Matrix_delete(matrix);
        }
        if (result == 1) {
            this.setClipping(hRgn);
        }
        OS.DeleteObject(hRgn);
        this.data.state = 0;
        if (this.data.hPen != 0L) {
            OS.SelectObject(this.handle, OS.GetStockObject(8));
            OS.DeleteObject(this.data.hPen);
            this.data.hPen = 0L;
        }
        if (this.data.hBrush != 0L) {
            OS.SelectObject(this.handle, OS.GetStockObject(5));
            OS.DeleteObject(this.data.hBrush);
            this.data.hBrush = 0L;
        }
    }
    
    long identity() {
        if ((this.data.style & 0x8000000) != 0x0) {
            int width = 0;
            final int technology = OS.GetDeviceCaps(this.handle, 2);
            if (technology == 2) {
                width = OS.GetDeviceCaps(this.handle, 110);
            }
            else {
                final Image image = this.data.image;
                if (image != null) {
                    final BITMAP bm = new BITMAP();
                    OS.GetObject(image.handle, BITMAP.sizeof, bm);
                    width = bm.bmWidth;
                }
                else {
                    final long hwnd = OS.WindowFromDC(this.handle);
                    if (hwnd != 0L) {
                        final RECT rect = new RECT();
                        OS.GetClientRect(hwnd, rect);
                        width = rect.right - rect.left;
                    }
                    else {
                        final long hBitmap = OS.GetCurrentObject(this.handle, 7);
                        final BITMAP bm2 = new BITMAP();
                        OS.GetObject(hBitmap, BITMAP.sizeof, bm2);
                        width = bm2.bmWidth;
                    }
                }
            }
            final POINT pt = new POINT();
            OS.GetWindowOrgEx(this.handle, pt);
            return Gdip.Matrix_new(-1.0f, 0.0f, 0.0f, 1.0f, (float)(width + 2 * pt.x), 0.0f);
        }
        return Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
    }
    
    void init(final Drawable drawable, final GCData data, final long hDC) {
        final int foreground = data.foreground;
        if (foreground != -1) {
            data.state &= 0xFFFFF6FE;
        }
        else {
            data.foreground = OS.GetTextColor(hDC);
        }
        final int background = data.background;
        if (background != -1) {
            data.state &= 0xFFFFF9FD;
        }
        else {
            data.background = OS.GetBkColor(hDC);
        }
        data.state &= 0xFFFFCFFF;
        final Font font = data.font;
        if (font != null) {
            data.state &= 0xFFFFFFFB;
        }
        else {
            data.font = Font.win32_new(this.device, OS.GetCurrentObject(hDC, 6));
        }
        final Image image = data.image;
        if (image != null) {
            data.hNullBitmap = OS.SelectObject(hDC, image.handle);
            image.memGC = this;
        }
        final int layout = data.layout;
        if (layout != -1) {
            int flags = OS.GetLayout(hDC);
            if ((flags & 0x1) != (layout & 0x1)) {
                flags &= 0xFFFFFFFE;
                OS.SetLayout(hDC, flags | layout);
            }
            if ((data.style & 0x4000000) != 0x0) {
                data.style |= 0x8000000;
            }
        }
        this.drawable = drawable;
        this.data = data;
        this.handle = hDC;
    }
    
    @Override
    public int hashCode() {
        return (int)this.handle;
    }
    
    public boolean isClipped() {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            final long rgn = Gdip.Region_new();
            Gdip.Graphics_GetClip(this.data.gdipGraphics, rgn);
            final boolean isInfinite = Gdip.Region_IsInfinite(rgn, gdipGraphics);
            Gdip.Region_delete(rgn);
            return !isInfinite;
        }
        final long region = OS.CreateRectRgn(0, 0, 0, 0);
        final int result = OS.GetClipRgn(this.handle, region);
        OS.DeleteObject(region);
        return result > 0;
    }
    
    @Override
    public boolean isDisposed() {
        return this.handle == 0L;
    }
    
    float measureSpace(final long font, final long format) {
        final PointF pt = new PointF();
        final RectF bounds = new RectF();
        Gdip.Graphics_MeasureString(this.data.gdipGraphics, new char[] { ' ' }, 1, font, pt, format, bounds);
        return bounds.Width;
    }
    
    public void setAdvanced(final boolean advanced) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (advanced && this.data.gdipGraphics != 0L) {
            return;
        }
        if (advanced) {
            this.initGdip();
        }
        else {
            this.disposeGdip();
            this.data.alpha = 255;
            final GCData data = this.data;
            final GCData data2 = this.data;
            final Pattern pattern = null;
            data2.foregroundPattern = pattern;
            data.backgroundPattern = pattern;
            this.data.state = 0;
            this.setClipping(0L);
            if ((this.data.style & 0x8000000) != 0x0) {
                OS.SetLayout(this.handle, OS.GetLayout(this.handle) | 0x1);
            }
        }
    }
    
    public void setAntialias(final int antialias) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0L && antialias == -1) {
            return;
        }
        int mode = 0;
        switch (antialias) {
            case -1: {
                mode = 0;
                break;
            }
            case 0: {
                mode = 3;
                break;
            }
            case 1: {
                mode = 4;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.initGdip();
        Gdip.Graphics_SetSmoothingMode(this.data.gdipGraphics, mode);
    }
    
    public void setAlpha(final int alpha) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0L && (alpha & 0xFF) == 0xFF) {
            return;
        }
        this.initGdip();
        this.data.alpha = (alpha & 0xFF);
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFFFC;
        if (this.data.gdipFgPatternBrushAlpha != 0L) {
            Gdip.TextureBrush_delete(this.data.gdipFgPatternBrushAlpha);
            this.data.gdipFgPatternBrushAlpha = 0L;
        }
        if (this.data.gdipBgPatternBrushAlpha != 0L) {
            Gdip.TextureBrush_delete(this.data.gdipBgPatternBrushAlpha);
            this.data.gdipBgPatternBrushAlpha = 0L;
        }
    }
    
    public void setBackground(final Color color) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (color == null) {
            SWT.error(4);
        }
        if (color.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.backgroundPattern == null && this.data.background == color.handle) {
            return;
        }
        this.data.backgroundPattern = null;
        this.data.background = color.handle;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFDFD;
    }
    
    public void setBackgroundPattern(final Pattern pattern) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (pattern != null && pattern.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.gdipGraphics == 0L && pattern == null) {
            return;
        }
        this.initGdip();
        if (this.data.backgroundPattern == pattern) {
            return;
        }
        this.data.backgroundPattern = pattern;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFFFD;
        if (this.data.gdipBgPatternBrushAlpha != 0L) {
            Gdip.TextureBrush_delete(this.data.gdipBgPatternBrushAlpha);
            this.data.gdipBgPatternBrushAlpha = 0L;
        }
    }
    
    void setClipping(final long clipRgn) {
        final long hRgn = clipRgn;
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            if (hRgn != 0L) {
                final long region = Gdip.Region_new(hRgn);
                Gdip.Graphics_SetClip(gdipGraphics, region, 0);
                Gdip.Region_delete(region);
            }
            else {
                Gdip.Graphics_ResetClip(gdipGraphics);
            }
        }
        else {
            POINT pt = null;
            if (hRgn != 0L) {
                pt = new POINT();
                OS.GetWindowOrgEx(this.handle, pt);
                OS.OffsetRgn(hRgn, -pt.x, -pt.y);
            }
            OS.SelectClipRgn(this.handle, hRgn);
            if (hRgn != 0L) {
                OS.OffsetRgn(hRgn, pt.x, pt.y);
            }
        }
    }
    
    public void setClipping(int x, int y, int width, int height) {
        x = DPIUtil.autoScaleUp(this.drawable, x);
        y = DPIUtil.autoScaleUp(this.drawable, y);
        width = DPIUtil.autoScaleUp(this.drawable, width);
        height = DPIUtil.autoScaleUp(this.drawable, height);
        this.setClippingInPixels(x, y, width, height);
    }
    
    void setClippingInPixels(final int x, final int y, final int width, final int height) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        final long hRgn = OS.CreateRectRgn(x, y, x + width, y + height);
        this.setClipping(hRgn);
        OS.DeleteObject(hRgn);
    }
    
    public void setClipping(final Path path) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (path != null && path.isDisposed()) {
            SWT.error(5);
        }
        this.setClipping(0L);
        if (path != null) {
            this.initGdip();
            final int mode = (OS.GetPolyFillMode(this.handle) == 2) ? 1 : 0;
            Gdip.GraphicsPath_SetFillMode(path.handle, mode);
            Gdip.Graphics_SetClipPath(this.data.gdipGraphics, path.handle);
        }
    }
    
    public void setClipping(Rectangle rect) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (rect == null) {
            this.setClipping(0L);
        }
        else {
            rect = DPIUtil.autoScaleUp(this.drawable, rect);
            this.setClippingInPixels(rect.x, rect.y, rect.width, rect.height);
        }
    }
    
    public void setClipping(final Region region) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (region != null && region.isDisposed()) {
            SWT.error(5);
        }
        this.setClipping((region != null) ? region.handle : 0L);
    }
    
    public void setFillRule(final int rule) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        int mode = 1;
        switch (rule) {
            case 2: {
                mode = 2;
                break;
            }
            case 1: {
                mode = 1;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        OS.SetPolyFillMode(this.handle, mode);
    }
    
    public void setFont(final Font font) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        this.data.font = ((font != null) ? font : this.data.device.systemFont);
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFFFB;
    }
    
    public void setForeground(final Color color) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (color == null) {
            SWT.error(4);
        }
        if (color.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.foregroundPattern == null && color.handle == this.data.foreground) {
            return;
        }
        this.data.foregroundPattern = null;
        this.data.foreground = color.handle;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFEFE;
    }
    
    public void setForegroundPattern(final Pattern pattern) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (pattern != null && pattern.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.gdipGraphics == 0L && pattern == null) {
            return;
        }
        this.initGdip();
        if (this.data.foregroundPattern == pattern) {
            return;
        }
        this.data.foregroundPattern = pattern;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFFFE;
        if (this.data.gdipFgPatternBrushAlpha != 0L) {
            Gdip.TextureBrush_delete(this.data.gdipFgPatternBrushAlpha);
            this.data.gdipFgPatternBrushAlpha = 0L;
        }
    }
    
    public void setInterpolation(final int interpolation) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0L && interpolation == -1) {
            return;
        }
        int mode = 0;
        switch (interpolation) {
            case -1: {
                mode = 0;
                break;
            }
            case 0: {
                mode = 5;
                break;
            }
            case 1: {
                mode = 1;
                break;
            }
            case 2: {
                mode = 2;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.initGdip();
        Gdip.Graphics_SetInterpolationMode(this.data.gdipGraphics, mode);
    }
    
    public void setLineAttributes(final LineAttributes attributes) {
        if (attributes == null) {
            SWT.error(4);
        }
        attributes.width = DPIUtil.autoScaleUp(this.drawable, attributes.width);
        this.setLineAttributesInPixels(attributes);
    }
    
    void setLineAttributesInPixels(final LineAttributes attributes) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        int mask = 0;
        final float lineWidth = attributes.width;
        if (lineWidth != this.data.lineWidth) {
            mask |= 0x4010;
        }
        int lineStyle = attributes.style;
        if (lineStyle != this.data.lineStyle) {
            mask |= 0x8;
            switch (lineStyle) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    break;
                }
                case 6: {
                    if (attributes.dash == null) {
                        lineStyle = 1;
                        break;
                    }
                    break;
                }
                default: {
                    SWT.error(5);
                    break;
                }
            }
        }
        final int join = attributes.join;
        if (join != this.data.lineJoin) {
            mask |= 0x40;
            switch (join) {
                case 1:
                case 2:
                case 3: {
                    break;
                }
                default: {
                    SWT.error(5);
                    break;
                }
            }
        }
        final int cap = attributes.cap;
        if (cap != this.data.lineCap) {
            mask |= 0x20;
            switch (cap) {
                case 1:
                case 2:
                case 3: {
                    break;
                }
                default: {
                    SWT.error(5);
                    break;
                }
            }
        }
        float[] dashes = attributes.dash;
        final float[] lineDashes = this.data.lineDashes;
        if (dashes != null && dashes.length > 0) {
            boolean changed = lineDashes == null || lineDashes.length != dashes.length;
            for (int i = 0; i < dashes.length; ++i) {
                final float dash = dashes[i];
                if (dash <= 0.0f) {
                    SWT.error(5);
                }
                if (!changed && lineDashes[i] != dash) {
                    changed = true;
                }
            }
            if (changed) {
                final float[] newDashes = new float[dashes.length];
                System.arraycopy(dashes, 0, newDashes, 0, dashes.length);
                dashes = newDashes;
                mask |= 0x8;
            }
            else {
                dashes = lineDashes;
            }
        }
        else if (lineDashes != null && lineDashes.length > 0) {
            mask |= 0x8;
        }
        else {
            dashes = lineDashes;
        }
        final float dashOffset = attributes.dashOffset;
        if (dashOffset != this.data.lineDashesOffset) {
            mask |= 0x8;
        }
        final float miterLimit = attributes.miterLimit;
        if (miterLimit != this.data.lineMiterLimit) {
            mask |= 0x80;
        }
        this.initGdip();
        if (mask == 0) {
            return;
        }
        this.data.lineWidth = lineWidth;
        this.data.lineStyle = lineStyle;
        this.data.lineCap = cap;
        this.data.lineJoin = join;
        this.data.lineDashes = dashes;
        this.data.lineDashesOffset = dashOffset;
        this.data.lineMiterLimit = miterLimit;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= ~mask;
    }
    
    public void setLineCap(final int cap) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.lineCap == cap) {
            return;
        }
        switch (cap) {
            case 1:
            case 2:
            case 3: {
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.data.lineCap = cap;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFFDF;
    }
    
    public void setLineDash(final int[] dashes) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        final float[] lineDashes = this.data.lineDashes;
        if (dashes != null && dashes.length > 0) {
            boolean changed = this.data.lineStyle != 6 || lineDashes == null || lineDashes.length != dashes.length;
            for (int i = 0; i < dashes.length; ++i) {
                final int dash = dashes[i];
                if (dash <= 0) {
                    SWT.error(5);
                }
                if (!changed && lineDashes[i] != dash) {
                    changed = true;
                }
            }
            if (!changed) {
                return;
            }
            this.data.lineDashes = new float[dashes.length];
            for (int i = 0; i < dashes.length; ++i) {
                this.data.lineDashes[i] = (float)dashes[i];
            }
            this.data.lineStyle = 6;
        }
        else {
            if (this.data.lineStyle == 1 && (lineDashes == null || lineDashes.length == 0)) {
                return;
            }
            this.data.lineDashes = null;
            this.data.lineStyle = 1;
        }
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFFF7;
    }
    
    public void setLineJoin(final int join) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.lineJoin == join) {
            return;
        }
        switch (join) {
            case 1:
            case 2:
            case 3: {
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.data.lineJoin = join;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFFBF;
    }
    
    public void setLineStyle(int lineStyle) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.lineStyle == lineStyle) {
            return;
        }
        switch (lineStyle) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                break;
            }
            case 6: {
                if (this.data.lineDashes == null) {
                    lineStyle = 1;
                    break;
                }
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.data.lineStyle = lineStyle;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFFFF7;
    }
    
    public void setLineWidth(int lineWidth) {
        lineWidth = DPIUtil.autoScaleUp(this.drawable, lineWidth);
        this.setLineWidthInPixels(lineWidth);
    }
    
    void setLineWidthInPixels(final int lineWidth) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.lineWidth == lineWidth) {
            return;
        }
        this.data.lineWidth = (float)lineWidth;
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFBFEF;
    }
    
    public void setXORMode(final boolean xor) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        OS.SetROP2(this.handle, xor ? 7 : 13);
    }
    
    public void setTextAntialias(final int antialias) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0L && antialias == -1) {
            return;
        }
        int textMode = 0;
        switch (antialias) {
            case -1: {
                textMode = 0;
                break;
            }
            case 0: {
                textMode = 1;
                break;
            }
            case 1: {
                final int[] type = { 0 };
                OS.SystemParametersInfo(8202, 0, type, 0);
                if (type[0] == 2) {
                    textMode = 5;
                    break;
                }
                textMode = 3;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.initGdip();
        Gdip.Graphics_SetTextRenderingHint(this.data.gdipGraphics, textMode);
    }
    
    public void setTransform(final Transform transform) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (transform != null && transform.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.gdipGraphics == 0L && transform == null) {
            return;
        }
        this.initGdip();
        final long identity = this.identity();
        if (transform != null) {
            Gdip.Matrix_Multiply(identity, transform.handle, 0);
        }
        Gdip.Graphics_SetTransform(this.data.gdipGraphics, identity);
        Gdip.Matrix_delete(identity);
        final GCData data2;
        final GCData data = data2 = this.data;
        data2.state &= 0xFFFFBFFF;
    }
    
    public Point stringExtent(final String string) {
        if (string == null) {
            SWT.error(4);
        }
        return DPIUtil.autoScaleDown(this.drawable, this.stringExtentInPixels(string));
    }
    
    Point stringExtentInPixels(final String string) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        this.checkGC(4);
        final int length = string.length();
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            final Point size = new Point(0, 0);
            this.drawText(gdipGraphics, string, 0, 0, 0, size);
            return size;
        }
        final SIZE size2 = new SIZE();
        if (length == 0) {
            OS.GetTextExtentPoint32(this.handle, new char[] { ' ' }, 1, size2);
            return new Point(0, size2.cy);
        }
        final char[] buffer = string.toCharArray();
        OS.GetTextExtentPoint32(this.handle, buffer, length, size2);
        return new Point(size2.cx, size2.cy);
    }
    
    public Point textExtent(final String string) {
        return DPIUtil.autoScaleDown(this.drawable, this.textExtentInPixels(string, 6));
    }
    
    public Point textExtent(final String string, final int flags) {
        return DPIUtil.autoScaleDown(this.drawable, this.textExtentInPixels(string, flags));
    }
    
    Point textExtentInPixels(final String string, final int flags) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (string == null) {
            SWT.error(4);
        }
        this.checkGC(4);
        final long gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0L) {
            final Point size = new Point(0, 0);
            this.drawText(gdipGraphics, string, 0, 0, flags, size);
            return size;
        }
        if (string.length() == 0) {
            final SIZE size2 = new SIZE();
            OS.GetTextExtentPoint32(this.handle, new char[] { ' ' }, 1, size2);
            return new Point(0, size2.cy);
        }
        final RECT rect = new RECT();
        final char[] buffer = string.toCharArray();
        int uFormat = 1024;
        if ((flags & 0x2) == 0x0) {
            uFormat |= 0x20;
        }
        if ((flags & 0x4) != 0x0) {
            uFormat |= 0x40;
        }
        if ((flags & 0x8) == 0x0) {
            uFormat |= 0x800;
        }
        OS.DrawText(this.handle, buffer, buffer.length, rect, uFormat);
        return new Point(rect.right, rect.bottom);
    }
    
    @Override
    public String toString() {
        if (this.isDisposed()) {
            return "GC {*DISPOSED*}";
        }
        return "GC {" + this.handle;
    }
    
    public static GC win32_new(final Drawable drawable, final GCData data) {
        final GC gc = new GC();
        final long hDC = drawable.internal_new_GC(data);
        gc.device = data.device;
        gc.init(drawable, data, hDC);
        return gc;
    }
    
    public static GC win32_new(final long hDC, final GCData data) {
        final GC gc = new GC();
        gc.device = data.device;
        data.style |= 0x2000000;
        final int flags = OS.GetLayout(hDC);
        if ((flags & 0x1) != 0x0) {
            data.style |= 0xC000000;
        }
        gc.init(null, data, hDC);
        return gc;
    }
    
    private static int cos(final int angle, final int length) {
        return (int)(Math.cos(angle * 0.017453292519943295) * length);
    }
    
    private static int sin(final int angle, final int length) {
        return (int)(Math.sin(angle * 0.017453292519943295) * length);
    }
    
    static {
        LINE_DOT_ZERO = new float[] { 3.0f, 3.0f };
        LINE_DASH_ZERO = new float[] { 18.0f, 6.0f };
        LINE_DASHDOT_ZERO = new float[] { 9.0f, 6.0f, 3.0f, 6.0f };
        LINE_DASHDOTDOT_ZERO = new float[] { 9.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f };
    }
}
