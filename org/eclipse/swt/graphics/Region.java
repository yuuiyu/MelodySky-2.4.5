//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public final class Region extends Resource
{
    public long handle;
    
    public Region() {
        this(null);
    }
    
    public Region(final Device device) {
        super(device);
        this.handle = OS.CreateRectRgn(0, 0, 0, 0);
        if (this.handle == 0L) {
            SWT.error(2);
        }
        this.init();
    }
    
    Region(final Device device, final int handle) {
        super(device);
        this.handle = handle;
    }
    
    public void add(final int[] pointArray) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (pointArray == null) {
            SWT.error(4);
        }
        this.addInPixels(DPIUtil.autoScaleUp(pointArray));
    }
    
    void addInPixels(final int[] pointArray) {
        final long polyRgn = OS.CreatePolygonRgn(pointArray, pointArray.length / 2, 1);
        OS.CombineRgn(this.handle, this.handle, polyRgn, 2);
        OS.DeleteObject(polyRgn);
    }
    
    public void add(Rectangle rect) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (rect == null) {
            SWT.error(4);
        }
        rect = DPIUtil.autoScaleUp(rect);
        this.addInPixels(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void add(final int x, final int y, final int width, final int height) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        this.addInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y), DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void addInPixels(final int x, final int y, final int width, final int height) {
        if (width < 0 || height < 0) {
            SWT.error(5);
        }
        final long rectRgn = OS.CreateRectRgn(x, y, x + width, y + height);
        OS.CombineRgn(this.handle, this.handle, rectRgn, 2);
        OS.DeleteObject(rectRgn);
    }
    
    public void add(final Region region) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (region == null) {
            SWT.error(4);
        }
        if (region.isDisposed()) {
            SWT.error(5);
        }
        OS.CombineRgn(this.handle, this.handle, region.handle, 2);
    }
    
    public boolean contains(final int x, final int y) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return this.containsInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y));
    }
    
    boolean containsInPixels(final int x, final int y) {
        return OS.PtInRegion(this.handle, x, y);
    }
    
    public boolean contains(Point pt) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (pt == null) {
            SWT.error(4);
        }
        pt = DPIUtil.autoScaleUp(pt);
        return this.containsInPixels(pt.x, pt.y);
    }
    
    @Override
    void destroy() {
        OS.DeleteObject(this.handle);
        this.handle = 0L;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Region)) {
            return false;
        }
        final Region rgn = (Region)object;
        return this.handle == rgn.handle;
    }
    
    public Rectangle getBounds() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    Rectangle getBoundsInPixels() {
        final RECT rect = new RECT();
        OS.GetRgnBox(this.handle, rect);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    @Override
    public int hashCode() {
        return (int)this.handle;
    }
    
    public void intersect(Rectangle rect) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (rect == null) {
            SWT.error(4);
        }
        rect = DPIUtil.autoScaleUp(rect);
        this.intersectInPixels(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void intersect(final int x, final int y, final int width, final int height) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        this.intersectInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y), DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void intersectInPixels(final int x, final int y, final int width, final int height) {
        if (width < 0 || height < 0) {
            SWT.error(5);
        }
        final long rectRgn = OS.CreateRectRgn(x, y, x + width, y + height);
        OS.CombineRgn(this.handle, this.handle, rectRgn, 1);
        OS.DeleteObject(rectRgn);
    }
    
    public void intersect(final Region region) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (region == null) {
            SWT.error(4);
        }
        if (region.isDisposed()) {
            SWT.error(5);
        }
        OS.CombineRgn(this.handle, this.handle, region.handle, 1);
    }
    
    public boolean intersects(final int x, final int y, final int width, final int height) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return this.intersectsInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y), DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    boolean intersectsInPixels(final int x, final int y, final int width, final int height) {
        final RECT r = new RECT();
        OS.SetRect(r, x, y, x + width, y + height);
        return OS.RectInRegion(this.handle, r);
    }
    
    public boolean intersects(Rectangle rect) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (rect == null) {
            SWT.error(4);
        }
        rect = DPIUtil.autoScaleUp(rect);
        return this.intersectsInPixels(rect.x, rect.y, rect.width, rect.height);
    }
    
    @Override
    public boolean isDisposed() {
        return this.handle == 0L;
    }
    
    public boolean isEmpty() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final RECT rect = new RECT();
        final int result = OS.GetRgnBox(this.handle, rect);
        return result == 1 || rect.right - rect.left <= 0 || rect.bottom - rect.top <= 0;
    }
    
    public void subtract(final int[] pointArray) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (pointArray == null) {
            SWT.error(4);
        }
        this.subtractInPixels(DPIUtil.autoScaleUp(pointArray));
    }
    
    void subtractInPixels(final int[] pointArray) {
        final long polyRgn = OS.CreatePolygonRgn(pointArray, pointArray.length / 2, 1);
        OS.CombineRgn(this.handle, this.handle, polyRgn, 4);
        OS.DeleteObject(polyRgn);
    }
    
    public void subtract(Rectangle rect) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (rect == null) {
            SWT.error(4);
        }
        rect = DPIUtil.autoScaleUp(rect);
        this.subtractInPixels(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void subtract(final int x, final int y, final int width, final int height) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        this.subtractInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y), DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void subtractInPixels(final int x, final int y, final int width, final int height) {
        if (width < 0 || height < 0) {
            SWT.error(5);
        }
        final long rectRgn = OS.CreateRectRgn(x, y, x + width, y + height);
        OS.CombineRgn(this.handle, this.handle, rectRgn, 4);
        OS.DeleteObject(rectRgn);
    }
    
    public void subtract(final Region region) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (region == null) {
            SWT.error(4);
        }
        if (region.isDisposed()) {
            SWT.error(5);
        }
        OS.CombineRgn(this.handle, this.handle, region.handle, 4);
    }
    
    public void translate(final int x, final int y) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        this.translateInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y));
    }
    
    void translateInPixels(final int x, final int y) {
        OS.OffsetRgn(this.handle, x, y);
    }
    
    public void translate(Point pt) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (pt == null) {
            SWT.error(4);
        }
        pt = DPIUtil.autoScaleUp(pt);
        this.translateInPixels(pt.x, pt.y);
    }
    
    @Override
    public String toString() {
        if (this.isDisposed()) {
            return "Region {*DISPOSED*}";
        }
        return "Region {" + this.handle;
    }
    
    public static Region win32_new(final Device device, final int handle) {
        return new Region(device, handle);
    }
}
