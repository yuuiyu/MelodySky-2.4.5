//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class TreeDragSourceEffect extends DragSourceEffect
{
    Image dragSourceImage;
    
    public TreeDragSourceEffect(final Tree tree) {
        super((Control)tree);
        this.dragSourceImage = null;
    }
    
    public void dragFinished(final DragSourceEvent event) {
        if (this.dragSourceImage != null) {
            this.dragSourceImage.dispose();
        }
        this.dragSourceImage = null;
    }
    
    public void dragStart(final DragSourceEvent event) {
        event.image = this.getDragSourceImage(event);
    }
    
    Image getDragSourceImage(final DragSourceEvent event) {
        if (this.dragSourceImage != null) {
            this.dragSourceImage.dispose();
        }
        this.dragSourceImage = null;
        final SHDRAGIMAGE shdi = new SHDRAGIMAGE();
        final int DI_GETDRAGIMAGE = OS.RegisterWindowMessage(new TCHAR(0, "ShellGetDragImage", true));
        if (OS.SendMessage(this.control.handle, DI_GETDRAGIMAGE, 0L, shdi) != 0L) {
            if ((this.control.getStyle() & 0x8000000) != 0x0) {
                event.offsetX = shdi.sizeDragImage.cx - shdi.ptOffset.x;
            }
            else {
                event.offsetX = shdi.ptOffset.x;
            }
            event.offsetY = shdi.ptOffset.y;
            final long hImage = shdi.hbmpDragImage;
            if (hImage != 0L) {
                final BITMAP bm = new BITMAP();
                OS.GetObject(hImage, BITMAP.sizeof, bm);
                final int srcWidth = bm.bmWidth;
                final int srcHeight = bm.bmHeight;
                final long hdc = OS.GetDC(0L);
                final long srcHdc = OS.CreateCompatibleDC(hdc);
                final long oldSrcBitmap = OS.SelectObject(srcHdc, hImage);
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
                final long memDib = OS.CreateDIBSection(0L, bmi, 0, pBits, 0L, 0);
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
                final PaletteData palette = new PaletteData(65280, 16711680, -16777216);
                final ImageData data = new ImageData(srcWidth, srcHeight, bm.bmBitsPixel, palette, bm.bmWidthBytes, srcData);
                if (shdi.crColorKey == -1) {
                    final byte[] alphaData = new byte[srcWidth * srcHeight];
                    final int spinc = dibBM.bmWidthBytes - srcWidth * 4;
                    int ap = 0;
                    int sp = 3;
                    for (int y = 0; y < srcHeight; ++y) {
                        for (int x = 0; x < srcWidth; ++x) {
                            alphaData[ap++] = srcData[sp];
                            sp += 4;
                        }
                        sp += spinc;
                    }
                    data.alphaData = alphaData;
                }
                else {
                    data.transparentPixel = shdi.crColorKey << 8;
                }
                final Display display = this.control.getDisplay();
                this.dragSourceImage = new Image(display, new DPIUtil.AutoScaleImageDataProvider(display, data, DPIUtil.getDeviceZoom()));
                OS.SelectObject(memHdc, oldMemBitmap);
                OS.DeleteDC(memHdc);
                OS.DeleteObject(memDib);
                OS.SelectObject(srcHdc, oldSrcBitmap);
                OS.DeleteDC(srcHdc);
                OS.ReleaseDC(0L, hdc);
                OS.DeleteObject(hImage);
                return this.dragSourceImage;
            }
        }
        return null;
    }
}
