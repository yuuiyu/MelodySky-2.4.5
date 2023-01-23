//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gdip.*;
import org.eclipse.swt.internal.win32.*;

public class Path extends Resource
{
    public long handle;
    PointF currentPoint;
    PointF startPoint;
    
    public Path(final Device device) {
        super(device);
        this.currentPoint = new PointF();
        this.startPoint = new PointF();
        this.device.checkGDIP();
        this.handle = Gdip.GraphicsPath_new(0);
        if (this.handle == 0L) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Path(final Device device, final Path path, float flatness) {
        super(device);
        this.currentPoint = new PointF();
        this.startPoint = new PointF();
        if (path == null) {
            SWT.error(4);
        }
        if (path.isDisposed()) {
            SWT.error(5);
        }
        flatness = Math.max(0.0f, flatness);
        this.handle = Gdip.GraphicsPath_Clone(path.handle);
        if (flatness != 0.0f) {
            Gdip.GraphicsPath_Flatten(this.handle, 0L, flatness);
        }
        if (this.handle == 0L) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Path(final Device device, final PathData data) {
        this(device);
        if (data == null) {
            SWT.error(4);
        }
        this.init(data);
    }
    
    public void addArc(float x, float y, float width, float height, final float startAngle, final float arcAngle) {
        if (width == 0.0f || height == 0.0f || arcAngle == 0.0f) {
            return;
        }
        final Drawable drawable = (Drawable)this.getDevice();
        x = DPIUtil.autoScaleUp(drawable, x);
        y = DPIUtil.autoScaleUp(drawable, y);
        width = DPIUtil.autoScaleUp(drawable, width);
        height = DPIUtil.autoScaleUp(drawable, height);
        this.addArcInPixels(x, y, width, height, startAngle, arcAngle);
    }
    
    void addArcInPixels(float x, float y, float width, float height, final float startAngle, final float arcAngle) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (width < 0.0f) {
            x += width;
            width = -width;
        }
        if (height < 0.0f) {
            y += height;
            height = -height;
        }
        if (width == height) {
            Gdip.GraphicsPath_AddArc(this.handle, x, y, width, height, -startAngle, -arcAngle);
        }
        else {
            final long path = Gdip.GraphicsPath_new(0);
            if (path == 0L) {
                SWT.error(2);
            }
            final long matrix = Gdip.Matrix_new(width, 0.0f, 0.0f, height, x, y);
            if (matrix == 0L) {
                SWT.error(2);
            }
            Gdip.GraphicsPath_AddArc(path, 0.0f, 0.0f, 1.0f, 1.0f, -startAngle, -arcAngle);
            Gdip.GraphicsPath_Transform(path, matrix);
            Gdip.GraphicsPath_AddPath(this.handle, path, true);
            Gdip.Matrix_delete(matrix);
            Gdip.GraphicsPath_delete(path);
        }
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
    }
    
    public void addPath(final Path path) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (path == null) {
            SWT.error(4);
        }
        if (path.isDisposed()) {
            SWT.error(5);
        }
        Gdip.GraphicsPath_AddPath(this.handle, path.handle, false);
        this.currentPoint.X = path.currentPoint.X;
        this.currentPoint.Y = path.currentPoint.Y;
    }
    
    public void addRectangle(float x, float y, float width, float height) {
        final Drawable drawable = (Drawable)this.getDevice();
        x = DPIUtil.autoScaleUp(drawable, x);
        y = DPIUtil.autoScaleUp(drawable, y);
        width = DPIUtil.autoScaleUp(drawable, width);
        height = DPIUtil.autoScaleUp(drawable, height);
        this.addRectangleInPixels(x, y, width, height);
    }
    
    void addRectangleInPixels(final float x, final float y, final float width, final float height) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final RectF rect = new RectF();
        rect.X = x;
        rect.Y = y;
        rect.Width = width;
        rect.Height = height;
        Gdip.GraphicsPath_AddRectangle(this.handle, rect);
        this.currentPoint.X = x;
        this.currentPoint.Y = y;
    }
    
    public void addString(final String string, float x, float y, final Font font) {
        final Drawable drawable = (Drawable)this.getDevice();
        x = DPIUtil.autoScaleUp(drawable, x);
        y = DPIUtil.autoScaleUp(drawable, y);
        this.addStringInPixels(string, x, y, font);
    }
    
    void addStringInPixels(final String string, final float x, final float y, final Font font) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (font == null) {
            SWT.error(4);
        }
        if (font.isDisposed()) {
            SWT.error(5);
        }
        final char[] buffer = string.toCharArray();
        final long hDC = this.device.internal_new_GC((GCData)null);
        final long[] family = { 0L };
        final long gdipFont = GC.createGdipFont(hDC, font.handle, 0L, this.device.fontCollection, family, (long[])null);
        final PointF point = new PointF();
        point.X = x - Gdip.Font_GetSize(gdipFont) / 6.0f;
        point.Y = y;
        final int style = Gdip.Font_GetStyle(gdipFont);
        final float size = Gdip.Font_GetSize(gdipFont);
        Gdip.GraphicsPath_AddString(this.handle, buffer, buffer.length, family[0], style, size, point, 0L);
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
        Gdip.FontFamily_delete(family[0]);
        Gdip.Font_delete(gdipFont);
        this.device.internal_dispose_GC(hDC, (GCData)null);
    }
    
    public void close() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.GraphicsPath_CloseFigure(this.handle);
        this.currentPoint.X = this.startPoint.X;
        this.currentPoint.Y = this.startPoint.Y;
    }
    
    public boolean contains(float x, float y, final GC gc, final boolean outline) {
        final Drawable drawable = (Drawable)this.getDevice();
        x = DPIUtil.autoScaleUp(drawable, x);
        y = DPIUtil.autoScaleUp(drawable, y);
        return this.containsInPixels(x, y, gc, outline);
    }
    
    boolean containsInPixels(final float x, final float y, final GC gc, final boolean outline) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (gc == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        gc.initGdip();
        gc.checkGC(120);
        final int mode = (OS.GetPolyFillMode(gc.handle) == 2) ? 1 : 0;
        Gdip.GraphicsPath_SetFillMode(this.handle, mode);
        if (outline) {
            return Gdip.GraphicsPath_IsOutlineVisible(this.handle, x, y, gc.data.gdipPen, gc.data.gdipGraphics);
        }
        return Gdip.GraphicsPath_IsVisible(this.handle, x, y, gc.data.gdipGraphics);
    }
    
    public void cubicTo(float cx1, float cy1, float cx2, float cy2, float x, float y) {
        final Drawable drawable = (Drawable)this.getDevice();
        cx1 = DPIUtil.autoScaleUp(drawable, cx1);
        cy1 = DPIUtil.autoScaleUp(drawable, cy1);
        cx2 = DPIUtil.autoScaleUp(drawable, cx2);
        cy2 = DPIUtil.autoScaleUp(drawable, cy2);
        x = DPIUtil.autoScaleUp(drawable, x);
        y = DPIUtil.autoScaleUp(drawable, y);
        this.cubicToInPixels(cx1, cy1, cx2, cy2, x, y);
    }
    
    void cubicToInPixels(final float cx1, final float cy1, final float cx2, final float cy2, final float x, final float y) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.GraphicsPath_AddBezier(this.handle, this.currentPoint.X, this.currentPoint.Y, cx1, cy1, cx2, cy2, x, y);
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
    }
    
    @Override
    void destroy() {
        Gdip.GraphicsPath_delete(this.handle);
        this.handle = 0L;
    }
    
    public void getBounds(final float[] bounds) {
        if (bounds == null) {
            SWT.error(4);
        }
        this.getBoundsInPixels(bounds);
        final float[] scaledbounds = DPIUtil.autoScaleDown((Drawable)this.getDevice(), bounds);
        System.arraycopy(scaledbounds, 0, bounds, 0, 4);
    }
    
    void getBoundsInPixels(final float[] bounds) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (bounds.length < 4) {
            SWT.error(5);
        }
        final RectF rect = new RectF();
        Gdip.GraphicsPath_GetBounds(this.handle, rect, 0L, 0L);
        bounds[0] = rect.X;
        bounds[1] = rect.Y;
        bounds[2] = rect.Width;
        bounds[3] = rect.Height;
    }
    
    public void getCurrentPoint(final float[] point) {
        if (point == null) {
            SWT.error(4);
        }
        this.getCurrentPointInPixels(point);
        final float[] scaledpoint = DPIUtil.autoScaleDown((Drawable)this.getDevice(), point);
        System.arraycopy(scaledpoint, 0, point, 0, 2);
    }
    
    void getCurrentPointInPixels(final float[] point) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (point.length < 2) {
            SWT.error(5);
        }
        point[0] = this.currentPoint.X;
        point[1] = this.currentPoint.Y;
    }
    
    public PathData getPathData() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final PathData result = this.getPathDataInPixels();
        result.points = DPIUtil.autoScaleDown((Drawable)this.getDevice(), result.points);
        return result;
    }
    
    PathData getPathDataInPixels() {
        final int count = Gdip.GraphicsPath_GetPointCount(this.handle);
        final byte[] gdipTypes = new byte[count];
        final float[] points = new float[count * 2];
        Gdip.GraphicsPath_GetPathTypes(this.handle, gdipTypes, count);
        Gdip.GraphicsPath_GetPathPoints(this.handle, points, count);
        byte[] types = new byte[count * 2];
        int index = 0;
        int typesIndex = 0;
        while (index < count) {
            final byte type = gdipTypes[index];
            boolean close = false;
            switch (type & 0x7) {
                case 0: {
                    types[typesIndex++] = 1;
                    close = ((type & 0x80) != 0x0);
                    ++index;
                    break;
                }
                case 1: {
                    types[typesIndex++] = 2;
                    close = ((type & 0x80) != 0x0);
                    ++index;
                    break;
                }
                case 3: {
                    types[typesIndex++] = 4;
                    close = ((gdipTypes[index + 2] & 0x80) != 0x0);
                    index += 3;
                    break;
                }
                default: {
                    ++index;
                    break;
                }
            }
            if (close) {
                types[typesIndex++] = 5;
            }
        }
        if (typesIndex != types.length) {
            final byte[] newTypes = new byte[typesIndex];
            System.arraycopy(types, 0, newTypes, 0, typesIndex);
            types = newTypes;
        }
        final PathData result = new PathData();
        result.types = types;
        result.points = points;
        return result;
    }
    
    public void lineTo(final float x, final float y) {
        final Drawable drawable = (Drawable)this.getDevice();
        this.lineToInPixels(DPIUtil.autoScaleUp(drawable, x), DPIUtil.autoScaleUp(drawable, y));
    }
    
    void lineToInPixels(final float x, final float y) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.GraphicsPath_AddLine(this.handle, this.currentPoint.X, this.currentPoint.Y, x, y);
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
    }
    
    void init(final PathData data) {
        final byte[] types = data.types;
        final float[] points = data.points;
        int i = 0;
        int j = 0;
        while (i < types.length) {
            switch (types[i]) {
                case 1: {
                    this.moveTo(points[j++], points[j++]);
                    break;
                }
                case 2: {
                    this.lineTo(points[j++], points[j++]);
                    break;
                }
                case 4: {
                    this.cubicTo(points[j++], points[j++], points[j++], points[j++], points[j++], points[j++]);
                    break;
                }
                case 3: {
                    this.quadTo(points[j++], points[j++], points[j++], points[j++]);
                    break;
                }
                case 5: {
                    this.close();
                    break;
                }
                default: {
                    this.dispose();
                    SWT.error(5);
                    break;
                }
            }
            ++i;
        }
    }
    
    @Override
    public boolean isDisposed() {
        return this.handle == 0L;
    }
    
    public void moveTo(final float x, final float y) {
        final Drawable drawable = (Drawable)this.getDevice();
        this.moveToInPixels(DPIUtil.autoScaleUp(drawable, x), DPIUtil.autoScaleUp(drawable, y));
    }
    
    void moveToInPixels(final float x, final float y) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.GraphicsPath_StartFigure(this.handle);
        final PointF currentPoint = this.currentPoint;
        this.startPoint.X = x;
        currentPoint.X = x;
        final PointF currentPoint2 = this.currentPoint;
        this.startPoint.Y = y;
        currentPoint2.Y = y;
    }
    
    public void quadTo(float cx, float cy, float x, float y) {
        final Drawable drawable = (Drawable)this.getDevice();
        cx = DPIUtil.autoScaleUp(drawable, cx);
        cy = DPIUtil.autoScaleUp(drawable, cy);
        x = DPIUtil.autoScaleUp(drawable, x);
        y = DPIUtil.autoScaleUp(drawable, y);
        this.quadToInPixels(cx, cy, x, y);
    }
    
    void quadToInPixels(final float cx, final float cy, final float x, final float y) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final float cx2 = this.currentPoint.X + 2.0f * (cx - this.currentPoint.X) / 3.0f;
        final float cy2 = this.currentPoint.Y + 2.0f * (cy - this.currentPoint.Y) / 3.0f;
        final float cx3 = cx2 + (x - this.currentPoint.X) / 3.0f;
        final float cy3 = cy2 + (y - this.currentPoint.Y) / 3.0f;
        Gdip.GraphicsPath_AddBezier(this.handle, this.currentPoint.X, this.currentPoint.Y, cx2, cy2, cx3, cy3, x, y);
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
    }
    
    @Override
    public String toString() {
        if (this.isDisposed()) {
            return "Path {*DISPOSED*}";
        }
        return "Path {" + this.handle;
    }
}
