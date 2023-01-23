//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;

public class ImageTransfer extends ByteArrayTransfer
{
    private static ImageTransfer _instance;
    private static final String CF_DIB = "CF_DIB";
    private static final int CF_DIBID = 8;
    
    private ImageTransfer() {
    }
    
    public static ImageTransfer getInstance() {
        return ImageTransfer._instance;
    }
    
    public void javaToNative(final Object object, final TransferData transferData) {
        if (!this.checkImage(object) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final ImageData imgData = (ImageData)object;
        if (imgData == null) {
            SWT.error(4);
        }
        final int imageSize = imgData.data.length;
        final int imageHeight = imgData.height;
        final int bytesPerLine = imgData.bytesPerLine;
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biSizeImage = imageSize;
        bmiHeader.biWidth = imgData.width;
        bmiHeader.biHeight = imageHeight;
        bmiHeader.biPlanes = 1;
        bmiHeader.biBitCount = (short)imgData.depth;
        bmiHeader.biCompression = 0;
        int colorSize = 0;
        if (bmiHeader.biBitCount <= 8) {
            colorSize += (1 << bmiHeader.biBitCount) * 4;
        }
        final byte[] bmi = new byte[BITMAPINFOHEADER.sizeof + colorSize];
        OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
        final RGB[] rgbs = imgData.palette.getRGBs();
        if (rgbs != null && colorSize > 0) {
            int offset = BITMAPINFOHEADER.sizeof;
            for (final RGB rgb : rgbs) {
                bmi[offset] = (byte)rgb.blue;
                bmi[offset + 1] = (byte)rgb.green;
                bmi[offset + 2] = (byte)rgb.red;
                bmi[offset + 3] = 0;
                offset += 4;
            }
        }
        final long newPtr = OS.GlobalAlloc(64, BITMAPINFOHEADER.sizeof + colorSize + imageSize);
        OS.MoveMemory(newPtr, bmi, bmi.length);
        long pBitDest = newPtr + BITMAPINFOHEADER.sizeof + colorSize;
        if (imageHeight <= 0) {
            OS.MoveMemory(pBitDest, imgData.data, imageSize);
        }
        else {
            int offset2 = 0;
            pBitDest += bytesPerLine * (imageHeight - 1);
            final byte[] scanline = new byte[bytesPerLine];
            for (int i = 0; i < imageHeight; ++i) {
                System.arraycopy(imgData.data, offset2, scanline, 0, bytesPerLine);
                OS.MoveMemory(pBitDest, scanline, bytesPerLine);
                offset2 += bytesPerLine;
                pBitDest -= bytesPerLine;
            }
        }
        transferData.stgmedium = new STGMEDIUM();
        transferData.stgmedium.tymed = 1;
        transferData.stgmedium.unionField = newPtr;
        transferData.stgmedium.pUnkForRelease = 0L;
        transferData.result = 0;
    }
    
    public Object nativeToJava(final TransferData transferData) {
        if (!this.isSupportedType(transferData) || transferData.pIDataObject == 0L) {
            return null;
        }
        final IDataObject dataObject = new IDataObject(transferData.pIDataObject);
        dataObject.AddRef();
        final FORMATETC formatetc = new FORMATETC();
        formatetc.cfFormat = 8;
        formatetc.ptd = 0L;
        formatetc.dwAspect = 1;
        formatetc.lindex = -1;
        formatetc.tymed = 1;
        final STGMEDIUM stgmedium = new STGMEDIUM();
        stgmedium.tymed = 1;
        transferData.result = this.getData(dataObject, formatetc, stgmedium);
        if (transferData.result != 0) {
            return null;
        }
        final long hMem = stgmedium.unionField;
        dataObject.Release();
        try {
            final long ptr = OS.GlobalLock(hMem);
            if (ptr == 0L) {
                return null;
            }
            try {
                final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
                OS.MoveMemory(bmiHeader, ptr, BITMAPINFOHEADER.sizeof);
                final long[] pBits = { 0L };
                final long memDib = OS.CreateDIBSection(0L, ptr, 0, pBits, 0L, 0);
                if (memDib == 0L) {
                    SWT.error(2);
                }
                long bits = ptr + bmiHeader.biSize;
                if (bmiHeader.biBitCount <= 8) {
                    bits += ((bmiHeader.biClrUsed == 0) ? (1 << bmiHeader.biBitCount) : bmiHeader.biClrUsed) * 4;
                }
                else if (bmiHeader.biCompression == 3) {
                    bits += 12L;
                }
                if (bmiHeader.biHeight < 0) {
                    OS.MoveMemory(pBits[0], bits, bmiHeader.biSizeImage);
                }
                else {
                    final DIBSECTION dib = new DIBSECTION();
                    OS.GetObject(memDib, DIBSECTION.sizeof, dib);
                    final int biHeight = dib.biHeight;
                    final int scanline = dib.biSizeImage / biHeight;
                    long pDestBits = pBits[0];
                    long pSourceBits = bits + scanline * (biHeight - 1);
                    for (int i = 0; i < biHeight; ++i) {
                        OS.MoveMemory(pDestBits, pSourceBits, scanline);
                        pDestBits += scanline;
                        pSourceBits -= scanline;
                    }
                }
                final Image image = Image.win32_new(null, 0, memDib);
                final ImageData data = image.getImageData(DPIUtil.getDeviceZoom());
                OS.DeleteObject(memDib);
                image.dispose();
                return data;
            }
            finally {
                OS.GlobalUnlock(hMem);
            }
        }
        finally {
            OS.GlobalFree(hMem);
        }
    }
    
    protected int[] getTypeIds() {
        return new int[] { 8 };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "CF_DIB" };
    }
    
    boolean checkImage(final Object object) {
        return object != null && object instanceof ImageData;
    }
    
    protected boolean validate(final Object object) {
        return this.checkImage(object);
    }
    
    static {
        ImageTransfer._instance = new ImageTransfer();
    }
}
