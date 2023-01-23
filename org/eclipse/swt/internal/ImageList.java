//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;

public class ImageList
{
    long handle;
    int style;
    int refCount;
    Image[] images;
    
    public ImageList(final int style) {
        this(style, 32, 32);
    }
    
    public ImageList(final int style, final int width, final int height) {
        this.style = style;
        int flags = 33;
        if ((style & 0x4000000) != 0x0) {
            flags |= 0x2000;
        }
        this.handle = OS.ImageList_Create(width, height, flags, 16, 16);
        this.images = new Image[4];
    }
    
    public int add(final Image image) {
        int count;
        int index;
        for (count = OS.ImageList_GetImageCount(this.handle), index = 0; index < count; ++index) {
            if (this.images[index] != null && this.images[index].isDisposed()) {
                this.images[index] = null;
            }
            if (this.images[index] == null) {
                break;
            }
        }
        if (count == 0) {
            final Rectangle rect = image.getBoundsInPixels();
            OS.ImageList_SetIconSize(this.handle, rect.width, rect.height);
        }
        this.set(index, image, count);
        if (index == this.images.length) {
            final Image[] newImages = new Image[this.images.length + 4];
            System.arraycopy(this.images, 0, newImages, 0, this.images.length);
            this.images = newImages;
        }
        this.images[index] = image;
        return index;
    }
    
    public int addRef() {
        return ++this.refCount;
    }
    
    long copyBitmap(final long hImage, final int width, final int height) {
        final BITMAP bm = new BITMAP();
        OS.GetObject(hImage, BITMAP.sizeof, bm);
        final long hDC = OS.GetDC(0L);
        final long hdc1 = OS.CreateCompatibleDC(hDC);
        OS.SelectObject(hdc1, hImage);
        final long hdc2 = OS.CreateCompatibleDC(hDC);
        long hBitmap;
        if (bm.bmBitsPixel == 32) {
            final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
            bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
            bmiHeader.biWidth = width;
            bmiHeader.biHeight = -height;
            bmiHeader.biPlanes = 1;
            bmiHeader.biBitCount = 24;
            bmiHeader.biCompression = 0;
            final byte[] bmi = new byte[BITMAPINFOHEADER.sizeof];
            OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
            final long[] pBits = { 0L };
            hBitmap = OS.CreateDIBSection(0L, bmi, 0, pBits, 0L, 0);
        }
        else {
            hBitmap = OS.CreateCompatibleBitmap(hDC, width, height);
        }
        OS.SelectObject(hdc2, hBitmap);
        if (width != bm.bmWidth || height != bm.bmHeight) {
            OS.SetStretchBltMode(hdc2, 3);
            OS.StretchBlt(hdc2, 0, 0, width, height, hdc1, 0, 0, bm.bmWidth, bm.bmHeight, 13369376);
        }
        else {
            OS.BitBlt(hdc2, 0, 0, width, height, hdc1, 0, 0, 13369376);
        }
        OS.DeleteDC(hdc1);
        OS.DeleteDC(hdc2);
        OS.ReleaseDC(0L, hDC);
        return hBitmap;
    }
    
    long copyIcon(final long hImage, final int width, final int height) {
        final long hIcon = OS.CopyImage(hImage, 1, width, height, 0);
        return (hIcon != 0L) ? hIcon : hImage;
    }
    
    long copyWithAlpha(final long hBitmap, final int background, final byte[] alphaData, final int destWidth, final int destHeight) {
        final BITMAP bm = new BITMAP();
        OS.GetObject(hBitmap, BITMAP.sizeof, bm);
        final int srcWidth = bm.bmWidth;
        final int srcHeight = bm.bmHeight;
        final long hdc = OS.GetDC(0L);
        final long srcHdc = OS.CreateCompatibleDC(hdc);
        final long oldSrcBitmap = OS.SelectObject(srcHdc, hBitmap);
        final long memHdc = OS.CreateCompatibleDC(hdc);
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biWidth = srcWidth;
        bmiHeader.biHeight = -srcHeight;
        bmiHeader.biPlanes = 1;
        bmiHeader.biBitCount = 32;
        bmiHeader.biCompression = 0;
        final byte[] bmi = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
        final long[] pBits = { 0L };
        long memDib = OS.CreateDIBSection(0L, bmi, 0, pBits, 0L, 0);
        if (memDib == 0L) {
            SWT.error(2);
        }
        final long oldMemBitmap = OS.SelectObject(memHdc, memDib);
        final BITMAP dibBM = new BITMAP();
        OS.GetObject(memDib, BITMAP.sizeof, dibBM);
        final int sizeInBytes = dibBM.bmWidthBytes * dibBM.bmHeight;
        OS.BitBlt(memHdc, 0, 0, srcWidth, srcHeight, srcHdc, 0, 0, 13369376);
        final byte[] srcData = new byte[sizeInBytes];
        OS.MoveMemory(srcData, dibBM.bmBits, sizeInBytes);
        if (alphaData != null) {
            final int spinc = dibBM.bmWidthBytes - srcWidth * 4;
            int ap = 0;
            int sp = 0;
            for (int y = 0; y < srcHeight; ++y) {
                for (int x = 0; x < srcWidth; ++x) {
                    final int a = alphaData[ap++] & 0xFF;
                    if (a != 0) {
                        srcData[sp] = (byte)(((srcData[sp] & 0xFF) * 255 + a / 2) / a);
                        srcData[sp + 1] = (byte)(((srcData[sp + 1] & 0xFF) * 255 + a / 2) / a);
                        srcData[sp + 2] = (byte)(((srcData[sp + 2] & 0xFF) * 255 + a / 2) / a);
                    }
                    srcData[sp + 3] = (byte)a;
                    sp += 4;
                }
                sp += spinc;
            }
        }
        else {
            final byte transRed = (byte)(background & 0xFF);
            final byte transGreen = (byte)(background >> 8 & 0xFF);
            final byte transBlue = (byte)(background >> 16 & 0xFF);
            final int spinc2 = dibBM.bmWidthBytes - srcWidth * 4;
            int sp2 = 3;
            for (int y2 = 0; y2 < srcHeight; ++y2) {
                for (int x2 = 0; x2 < srcWidth; ++x2) {
                    srcData[sp2] = (byte)((srcData[sp2 - 1] == transRed && srcData[sp2 - 2] == transGreen && srcData[sp2 - 3] == transBlue) ? 0 : -1);
                    sp2 += 4;
                }
                sp2 += spinc2;
            }
        }
        OS.MoveMemory(dibBM.bmBits, srcData, sizeInBytes);
        if (srcWidth != destWidth || srcHeight != destHeight) {
            final BITMAPINFOHEADER bmiHeader2 = new BITMAPINFOHEADER();
            bmiHeader2.biSize = BITMAPINFOHEADER.sizeof;
            bmiHeader2.biWidth = destWidth;
            bmiHeader2.biHeight = -destHeight;
            bmiHeader2.biPlanes = 1;
            bmiHeader2.biBitCount = 32;
            bmiHeader2.biCompression = 0;
            final byte[] bmi2 = new byte[BITMAPINFOHEADER.sizeof];
            OS.MoveMemory(bmi2, bmiHeader2, BITMAPINFOHEADER.sizeof);
            final long[] pBits2 = { 0L };
            final long memDib2 = OS.CreateDIBSection(0L, bmi2, 0, pBits2, 0L, 0);
            final long memHdc2 = OS.CreateCompatibleDC(hdc);
            final long oldMemBitmap2 = OS.SelectObject(memHdc2, memDib2);
            OS.SetStretchBltMode(memHdc2, 3);
            OS.StretchBlt(memHdc2, 0, 0, destWidth, destHeight, memHdc, 0, 0, srcWidth, srcHeight, 13369376);
            OS.SelectObject(memHdc2, oldMemBitmap2);
            OS.DeleteDC(memHdc2);
            OS.SelectObject(memHdc, oldMemBitmap);
            OS.DeleteDC(memHdc);
            OS.DeleteObject(memDib);
            memDib = memDib2;
        }
        else {
            OS.SelectObject(memHdc, oldMemBitmap);
            OS.DeleteDC(memHdc);
        }
        OS.SelectObject(srcHdc, oldSrcBitmap);
        OS.DeleteDC(srcHdc);
        OS.ReleaseDC(0L, hdc);
        return memDib;
    }
    
    long createMaskFromAlpha(final ImageData data, final int destWidth, final int destHeight) {
        final int srcWidth = data.width;
        final int srcHeight = data.height;
        final ImageData mask = ImageData.internal_new(srcWidth, srcHeight, 1, new PaletteData(new RGB[] { new RGB(0, 0, 0), new RGB(255, 255, 255) }), 2, (byte[])null, 1, (byte[])null, (byte[])null, -1, -1, -1, 0, 0, 0, 0);
        int ap = 0;
        for (int y = 0; y < mask.height; ++y) {
            for (int x = 0; x < mask.width; ++x) {
                mask.setPixel(x, y, (int)(((data.alphaData[ap++] & 0xFF) <= 127) ? 1 : 0));
            }
        }
        long hMask = OS.CreateBitmap(srcWidth, srcHeight, 1, 1, mask.data);
        if (srcWidth != destWidth || srcHeight != destHeight) {
            final long hdc = OS.GetDC(0L);
            final long hdc2 = OS.CreateCompatibleDC(hdc);
            OS.SelectObject(hdc2, hMask);
            final long hdc3 = OS.CreateCompatibleDC(hdc);
            final long hMask2 = OS.CreateBitmap(destWidth, destHeight, 1, 1, null);
            OS.SelectObject(hdc3, hMask2);
            OS.SetStretchBltMode(hdc3, 3);
            OS.StretchBlt(hdc3, 0, 0, destWidth, destHeight, hdc2, 0, 0, srcWidth, srcHeight, 13369376);
            OS.DeleteDC(hdc2);
            OS.DeleteDC(hdc3);
            OS.ReleaseDC(0L, hdc);
            OS.DeleteObject(hMask);
            hMask = hMask2;
        }
        return hMask;
    }
    
    long createMask(final long hBitmap, final int destWidth, final int destHeight, final int background, final int transparentPixel) {
        final BITMAP bm = new BITMAP();
        OS.GetObject(hBitmap, BITMAP.sizeof, bm);
        final int srcWidth = bm.bmWidth;
        final int srcHeight = bm.bmHeight;
        final long hMask = OS.CreateBitmap(destWidth, destHeight, 1, 1, null);
        final long hDC = OS.GetDC(0L);
        final long hdc1 = OS.CreateCompatibleDC(hDC);
        if (background != -1) {
            OS.SelectObject(hdc1, hBitmap);
            final boolean isDib = bm.bmBits != 0L;
            byte[] originalColors = null;
            if (transparentPixel != -1 && isDib && bm.bmBitsPixel <= 8) {
                final int maxColors = 1 << bm.bmBitsPixel;
                final byte[] oldColors = new byte[maxColors * 4];
                OS.GetDIBColorTable(hdc1, 0, maxColors, oldColors);
                final int offset = transparentPixel * 4;
                final byte[] newColors = new byte[oldColors.length];
                newColors[offset] = -1;
                newColors[offset + 2] = (newColors[offset + 1] = -1);
                OS.SetDIBColorTable(hdc1, 0, maxColors, newColors);
                originalColors = oldColors;
                OS.SetBkColor(hdc1, 16777215);
            }
            else {
                OS.SetBkColor(hdc1, background);
            }
            final long hdc2 = OS.CreateCompatibleDC(hDC);
            OS.SelectObject(hdc2, hMask);
            if (destWidth != srcWidth || destHeight != srcHeight) {
                OS.SetStretchBltMode(hdc2, 3);
                OS.StretchBlt(hdc2, 0, 0, destWidth, destHeight, hdc1, 0, 0, srcWidth, srcHeight, 13369376);
            }
            else {
                OS.BitBlt(hdc2, 0, 0, destWidth, destHeight, hdc1, 0, 0, 13369376);
            }
            OS.DeleteDC(hdc2);
            if (originalColors != null) {
                OS.SetDIBColorTable(hdc1, 0, 1 << bm.bmBitsPixel, originalColors);
            }
        }
        else {
            final long hOldBitmap = OS.SelectObject(hdc1, hMask);
            OS.PatBlt(hdc1, 0, 0, destWidth, destHeight, 66);
            OS.SelectObject(hdc1, hOldBitmap);
        }
        OS.ReleaseDC(0L, hDC);
        OS.DeleteDC(hdc1);
        return hMask;
    }
    
    public void dispose() {
        if (this.handle != 0L) {
            OS.ImageList_Destroy(this.handle);
        }
        this.handle = 0L;
        this.images = null;
    }
    
    public Image get(final int index) {
        return this.images[index];
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public long getHandle() {
        return this.handle;
    }
    
    public Point getImageSize() {
        final int[] cx = { 0 };
        final int[] cy = { 0 };
        OS.ImageList_GetIconSize(this.handle, cx, cy);
        return new Point(cx[0], cy[0]);
    }
    
    public int indexOf(final Image image) {
        for (int count = OS.ImageList_GetImageCount(this.handle), i = 0; i < count; ++i) {
            if (this.images[i] != null) {
                if (this.images[i].isDisposed()) {
                    this.images[i] = null;
                }
                if (this.images[i] != null && this.images[i].equals((Object)image)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void put(final int index, final Image image) {
        if (0 <= index && index < this.images.length && this.images[index] == image) {
            return;
        }
        final int count = OS.ImageList_GetImageCount(this.handle);
        if (0 > index || index >= count) {
            return;
        }
        if (image != null) {
            this.set(index, image, count);
        }
        this.images[index] = image;
    }
    
    public void remove(final int index) {
        int count = OS.ImageList_GetImageCount(this.handle);
        if (0 > index || index >= count) {
            return;
        }
        OS.ImageList_Remove(this.handle, index);
        System.arraycopy(this.images, index + 1, this.images, index, --count - index);
        this.images[index] = null;
    }
    
    public int removeRef() {
        return --this.refCount;
    }
    
    void set(final int index, final Image image, final int count) {
        final long hImage = image.handle;
        final int[] cx = { 0 };
        final int[] cy = { 0 };
        OS.ImageList_GetIconSize(this.handle, cx, cy);
        switch (image.type) {
            case 0: {
                long hBitmap = 0L;
                long hMask = 0L;
                final ImageData data = image.getImageData(DPIUtil.getDeviceZoom());
                switch (data.getTransparencyType()) {
                    case 1: {
                        boolean fullyTransparent = true;
                        if (data.alphaData == null) {
                            fullyTransparent = false;
                        }
                        else {
                            for (final byte alphaData : data.alphaData) {
                                if (alphaData != 0) {
                                    fullyTransparent = false;
                                    break;
                                }
                            }
                        }
                        if (!fullyTransparent) {
                            hBitmap = this.copyWithAlpha(hImage, -1, data.alphaData, cx[0], cy[0]);
                            break;
                        }
                        hBitmap = this.copyBitmap(hImage, cx[0], cy[0]);
                        hMask = this.createMaskFromAlpha(data, cx[0], cy[0]);
                        break;
                    }
                    case 4: {
                        int background = -1;
                        final Color color = image.getBackground();
                        if (color != null) {
                            background = color.handle;
                        }
                        hBitmap = this.copyBitmap(hImage, cx[0], cy[0]);
                        hMask = this.createMask(hImage, cx[0], cy[0], background, data.transparentPixel);
                        break;
                    }
                    default: {
                        hBitmap = this.copyBitmap(hImage, cx[0], cy[0]);
                        if (index != count) {
                            hMask = this.createMask(hImage, cx[0], cy[0], -1, -1);
                            break;
                        }
                        break;
                    }
                }
                if (index == count) {
                    OS.ImageList_Add(this.handle, hBitmap, hMask);
                }
                else {
                    OS.ImageList_Replace(this.handle, index, hBitmap, hMask);
                }
                if (hMask != 0L) {
                    OS.DeleteObject(hMask);
                }
                if (hBitmap != hImage) {
                    OS.DeleteObject(hBitmap);
                    break;
                }
                break;
            }
            case 1: {
                final long hIcon = this.copyIcon(hImage, cx[0], cy[0]);
                OS.ImageList_ReplaceIcon(this.handle, (index == count) ? -1 : index, hIcon);
                OS.DestroyIcon(hIcon);
                break;
            }
        }
    }
    
    public int size() {
        int result = 0;
        for (int count = OS.ImageList_GetImageCount(this.handle), i = 0; i < count; ++i) {
            if (this.images[i] != null) {
                if (this.images[i].isDisposed()) {
                    this.images[i] = null;
                }
                if (this.images[i] != null) {
                    ++result;
                }
            }
        }
        return result;
    }
}
