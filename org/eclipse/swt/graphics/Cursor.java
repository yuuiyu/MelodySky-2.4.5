//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;

public final class Cursor extends Resource
{
    public long handle;
    boolean isIcon;
    
    Cursor(final Device device) {
        super(device);
    }
    
    public Cursor(final Device device, final int style) {
        super(device);
        long lpCursorName = 0L;
        switch (style) {
            case 21: {
                lpCursorName = 32649L;
                break;
            }
            case 0: {
                lpCursorName = 32512L;
                break;
            }
            case 1: {
                lpCursorName = 32514L;
                break;
            }
            case 2: {
                lpCursorName = 32515L;
                break;
            }
            case 3: {
                lpCursorName = 32650L;
                break;
            }
            case 4: {
                lpCursorName = 32651L;
                break;
            }
            case 5: {
                lpCursorName = 32646L;
                break;
            }
            case 6: {
                lpCursorName = 32643L;
                break;
            }
            case 7: {
                lpCursorName = 32645L;
                break;
            }
            case 8: {
                lpCursorName = 32642L;
                break;
            }
            case 9: {
                lpCursorName = 32644L;
                break;
            }
            case 10: {
                lpCursorName = 32645L;
                break;
            }
            case 11: {
                lpCursorName = 32645L;
                break;
            }
            case 12: {
                lpCursorName = 32644L;
                break;
            }
            case 13: {
                lpCursorName = 32644L;
                break;
            }
            case 14: {
                lpCursorName = 32643L;
                break;
            }
            case 15: {
                lpCursorName = 32642L;
                break;
            }
            case 16: {
                lpCursorName = 32643L;
                break;
            }
            case 17: {
                lpCursorName = 32642L;
                break;
            }
            case 18: {
                lpCursorName = 32516L;
                break;
            }
            case 19: {
                lpCursorName = 32513L;
                break;
            }
            case 20: {
                lpCursorName = 32648L;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.handle = OS.LoadCursor(0L, lpCursorName);
        if (this.handle == 0L) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Cursor(final Device device, ImageData source, ImageData mask, final int hotspotX, final int hotspotY) {
        super(device);
        if (source == null) {
            SWT.error(4);
        }
        if (mask == null) {
            if (source.getTransparencyType() != 2) {
                SWT.error(4);
            }
            mask = source.getTransparencyMask();
        }
        if (mask.width != source.width || mask.height != source.height) {
            SWT.error(5);
        }
        if (hotspotX >= source.width || hotspotX < 0 || hotspotY >= source.height || hotspotY < 0) {
            SWT.error(5);
        }
        mask = ImageData.convertMask(mask);
        source = ImageData.convertMask(source);
        final byte[] sourceData = ImageData.convertPad(source.data, source.width, source.height, source.depth, source.scanlinePad, 2);
        final byte[] maskData = ImageData.convertPad(mask.data, mask.width, mask.height, mask.depth, mask.scanlinePad, 2);
        final long hInst = OS.GetModuleHandle(null);
        this.handle = OS.CreateCursor(hInst, hotspotX, hotspotY, source.width, source.height, sourceData, maskData);
        if (this.handle == 0L) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Cursor(final Device device, final ImageData source, final int hotspotX, final int hotspotY) {
        super(device);
        if (source == null) {
            SWT.error(4);
        }
        if (hotspotX >= source.width || hotspotX < 0 || hotspotY >= source.height || hotspotY < 0) {
            SWT.error(5);
        }
        long hBitmap = 0L;
        long hMask = 0L;
        if (source.maskData == null && source.transparentPixel == -1 && (source.alpha != -1 || source.alphaData != null)) {
            final PaletteData palette = source.palette;
            final PaletteData newPalette = new PaletteData(65280, 16711680, -16777216);
            final ImageData img = new ImageData(source.width, source.height, 32, newPalette);
            if (palette.isDirect) {
                ImageData.blit(1, source.data, source.depth, source.bytesPerLine, source.getByteOrder(), 0, 0, source.width, source.height, palette.redMask, palette.greenMask, palette.blueMask, 255, null, 0, 0, 0, img.data, img.depth, img.bytesPerLine, img.getByteOrder(), 0, 0, img.width, img.height, newPalette.redMask, newPalette.greenMask, newPalette.blueMask, false, false);
            }
            else {
                final RGB[] rgbs = palette.getRGBs();
                final int length = rgbs.length;
                final byte[] srcReds = new byte[length];
                final byte[] srcGreens = new byte[length];
                final byte[] srcBlues = new byte[length];
                for (int i = 0; i < rgbs.length; ++i) {
                    final RGB rgb = rgbs[i];
                    if (rgb != null) {
                        srcReds[i] = (byte)rgb.red;
                        srcGreens[i] = (byte)rgb.green;
                        srcBlues[i] = (byte)rgb.blue;
                    }
                }
                ImageData.blit(1, source.data, source.depth, source.bytesPerLine, source.getByteOrder(), 0, 0, source.width, source.height, srcReds, srcGreens, srcBlues, 255, null, 0, 0, 0, img.data, img.depth, img.bytesPerLine, img.getByteOrder(), 0, 0, img.width, img.height, newPalette.redMask, newPalette.greenMask, newPalette.blueMask, false, false);
            }
            hBitmap = Image.createDIB(source.width, source.height, 32);
            if (hBitmap == 0L) {
                SWT.error(2);
            }
            final BITMAP dibBM = new BITMAP();
            OS.GetObject(hBitmap, BITMAP.sizeof, dibBM);
            final byte[] srcData = img.data;
            if (source.alpha != -1) {
                for (int j = 3; j < srcData.length; j += 4) {
                    srcData[j] = (byte)source.alpha;
                }
            }
            else if (source.alphaData != null) {
                for (int sp = 3, ap = 0; sp < srcData.length; sp += 4, ++ap) {
                    srcData[sp] = source.alphaData[ap];
                }
            }
            OS.MoveMemory(dibBM.bmBits, srcData, srcData.length);
            hMask = OS.CreateBitmap(source.width, source.height, 1, 1, new byte[((source.width + 7) / 8 + 3) / 4 * 4 * source.height]);
            if (hMask == 0L) {
                SWT.error(2);
            }
        }
        else {
            final ImageData mask = source.getTransparencyMask();
            final long[] result = Image.init(this.device, null, source, mask);
            hBitmap = result[0];
            hMask = result[1];
        }
        final ICONINFO info = new ICONINFO();
        info.fIcon = false;
        info.hbmColor = hBitmap;
        info.hbmMask = hMask;
        info.xHotspot = hotspotX;
        info.yHotspot = hotspotY;
        this.handle = OS.CreateIconIndirect(info);
        OS.DeleteObject(hBitmap);
        OS.DeleteObject(hMask);
        if (this.handle == 0L) {
            SWT.error(2);
        }
        this.isIcon = true;
        this.init();
    }
    
    @Override
    void destroy() {
        if (this.isIcon) {
            OS.DestroyIcon(this.handle);
        }
        else {
            OS.DestroyCursor(this.handle);
        }
        this.handle = 0L;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Cursor)) {
            return false;
        }
        final Cursor cursor = (Cursor)object;
        return this.device == cursor.device && this.handle == cursor.handle;
    }
    
    @Override
    public int hashCode() {
        return (int)this.handle;
    }
    
    @Override
    public boolean isDisposed() {
        return this.handle == 0L;
    }
    
    @Override
    public String toString() {
        if (this.isDisposed()) {
            return "Cursor {*DISPOSED*}";
        }
        return "Cursor {" + this.handle;
    }
    
    public static Cursor win32_new(final Device device, final int handle) {
        final Cursor cursor = new Cursor(device);
        cursor.handle = handle;
        return cursor;
    }
}
