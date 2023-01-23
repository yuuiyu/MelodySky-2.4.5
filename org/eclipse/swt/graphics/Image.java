//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import java.io.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gdip.*;
import org.eclipse.swt.internal.win32.*;

public final class Image extends Resource implements Drawable
{
    public int type;
    public long handle;
    int transparentPixel;
    int transparentColor;
    GC memGC;
    private ImageFileNameProvider imageFileNameProvider;
    private ImageDataProvider imageDataProvider;
    private int styleFlag;
    private int currentDeviceZoom;
    int width;
    int height;
    static final int DEFAULT_SCANLINE_PAD = 4;
    
    Image(final Device device) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
    }
    
    public Image(final Device device, int width, int height) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        this.init(width, height);
        this.init();
    }
    
    public Image(Device device, final Image srcImage, final int flag) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        device = this.device;
        if (srcImage == null) {
            SWT.error(4);
        }
        if (srcImage.isDisposed()) {
            SWT.error(5);
        }
        final Rectangle rect = srcImage.getBoundsInPixels();
        this.type = srcImage.type;
        this.imageDataProvider = srcImage.imageDataProvider;
        this.imageFileNameProvider = srcImage.imageFileNameProvider;
        this.styleFlag = (srcImage.styleFlag | flag);
        this.currentDeviceZoom = srcImage.currentDeviceZoom;
        Label_1539: {
            switch (flag) {
                case 0: {
                    switch (this.type) {
                        case 0: {
                            final long hDC = device.internal_new_GC((GCData)null);
                            final long hdcSource = OS.CreateCompatibleDC(hDC);
                            final long hdcDest = OS.CreateCompatibleDC(hDC);
                            final long hOldSrc = OS.SelectObject(hdcSource, srcImage.handle);
                            final BITMAP bm = new BITMAP();
                            OS.GetObject(srcImage.handle, BITMAP.sizeof, bm);
                            this.handle = OS.CreateCompatibleBitmap(hdcSource, rect.width, (bm.bmBits != 0L) ? (-rect.height) : rect.height);
                            if (this.handle == 0L) {
                                SWT.error(2);
                            }
                            final long hOldDest = OS.SelectObject(hdcDest, this.handle);
                            OS.BitBlt(hdcDest, 0, 0, rect.width, rect.height, hdcSource, 0, 0, 13369376);
                            OS.SelectObject(hdcSource, hOldSrc);
                            OS.SelectObject(hdcDest, hOldDest);
                            OS.DeleteDC(hdcSource);
                            OS.DeleteDC(hdcDest);
                            device.internal_dispose_GC(hDC, (GCData)null);
                            this.transparentPixel = srcImage.transparentPixel;
                            break Label_1539;
                        }
                        case 1: {
                            this.handle = OS.CopyImage(srcImage.handle, 1, rect.width, rect.height, 0);
                            if (this.handle == 0L) {
                                SWT.error(2);
                                break Label_1539;
                            }
                            break Label_1539;
                        }
                        default: {
                            SWT.error(40);
                            break Label_1539;
                        }
                    }
                    break;
                }
                case 1: {
                    final ImageData data = srcImage.getImageDataAtCurrentZoom();
                    final PaletteData palette = data.palette;
                    final RGB[] rgbs = { device.getSystemColor(2).getRGB(), device.getSystemColor(18).getRGB(), device.getSystemColor(22).getRGB() };
                    final ImageData newData = new ImageData(rect.width, rect.height, 8, new PaletteData(rgbs));
                    newData.alpha = data.alpha;
                    newData.alphaData = data.alphaData;
                    newData.maskData = data.maskData;
                    newData.maskPad = data.maskPad;
                    if (data.transparentPixel != -1) {
                        newData.transparentPixel = 0;
                    }
                    final int[] scanline = new int[rect.width];
                    int[] maskScanline = null;
                    ImageData mask = null;
                    if (data.maskData != null) {
                        mask = data.getTransparencyMask();
                    }
                    if (mask != null) {
                        maskScanline = new int[rect.width];
                    }
                    final int redMask = palette.redMask;
                    final int greenMask = palette.greenMask;
                    final int blueMask = palette.blueMask;
                    final int redShift = palette.redShift;
                    final int greenShift = palette.greenShift;
                    final int blueShift = palette.blueShift;
                    for (int y = 0; y < rect.height; ++y) {
                        int offset = y * newData.bytesPerLine;
                        data.getPixels(0, y, rect.width, scanline, 0);
                        if (mask != null) {
                            mask.getPixels(0, y, rect.width, maskScanline, 0);
                        }
                        for (int x = 0; x < rect.width; ++x) {
                            final int pixel = scanline[x];
                            if ((data.transparentPixel == -1 || pixel != data.transparentPixel) && (mask == null || maskScanline[x] != 0)) {
                                int red;
                                int green;
                                int blue;
                                if (palette.isDirect) {
                                    red = (pixel & redMask);
                                    red = ((redShift < 0) ? (red >>> -redShift) : (red << redShift));
                                    green = (pixel & greenMask);
                                    green = ((greenShift < 0) ? (green >>> -greenShift) : (green << greenShift));
                                    blue = (pixel & blueMask);
                                    blue = ((blueShift < 0) ? (blue >>> -blueShift) : (blue << blueShift));
                                }
                                else {
                                    red = palette.colors[pixel].red;
                                    green = palette.colors[pixel].green;
                                    blue = palette.colors[pixel].blue;
                                }
                                final int intensity = red * red + green * green + blue * blue;
                                if (intensity < 98304) {
                                    newData.data[offset] = 1;
                                }
                                else {
                                    newData.data[offset] = 2;
                                }
                            }
                            ++offset;
                        }
                    }
                    this.init(newData);
                    break;
                }
                case 2: {
                    final ImageData data = srcImage.getImageDataAtCurrentZoom();
                    final PaletteData palette = data.palette;
                    ImageData newData2 = data;
                    if (!palette.isDirect) {
                        final RGB[] rgbs2 = palette.getRGBs();
                        for (int i = 0; i < rgbs2.length; ++i) {
                            if (data.transparentPixel != i) {
                                final RGB color = rgbs2[i];
                                final int red2 = color.red;
                                final int green2 = color.green;
                                final int blue2 = color.blue;
                                final int intensity2 = red2 + red2 + green2 + green2 + green2 + green2 + green2 + blue2 >> 3;
                                final RGB rgb = color;
                                final RGB rgb2 = color;
                                final RGB rgb3 = color;
                                final int red3 = intensity2;
                                rgb3.blue = red3;
                                rgb2.green = red3;
                                rgb.red = red3;
                            }
                        }
                        newData2.palette = new PaletteData(rgbs2);
                    }
                    else {
                        final RGB[] rgbs2 = new RGB[256];
                        for (int i = 0; i < rgbs2.length; ++i) {
                            rgbs2[i] = new RGB(i, i, i);
                        }
                        newData2 = new ImageData(rect.width, rect.height, 8, new PaletteData(rgbs2));
                        newData2.alpha = data.alpha;
                        newData2.alphaData = data.alphaData;
                        newData2.maskData = data.maskData;
                        newData2.maskPad = data.maskPad;
                        if (data.transparentPixel != -1) {
                            newData2.transparentPixel = 254;
                        }
                        final int[] scanline = new int[rect.width];
                        final int redMask2 = palette.redMask;
                        final int greenMask2 = palette.greenMask;
                        final int blueMask2 = palette.blueMask;
                        final int redShift2 = palette.redShift;
                        final int greenShift2 = palette.greenShift;
                        final int blueShift2 = palette.blueShift;
                        for (int y2 = 0; y2 < rect.height; ++y2) {
                            int offset2 = y2 * newData2.bytesPerLine;
                            data.getPixels(0, y2, rect.width, scanline, 0);
                            for (int x2 = 0; x2 < rect.width; ++x2) {
                                final int pixel2 = scanline[x2];
                                if (pixel2 != data.transparentPixel) {
                                    int red4 = pixel2 & redMask2;
                                    red4 = ((redShift2 < 0) ? (red4 >>> -redShift2) : (red4 << redShift2));
                                    int green3 = pixel2 & greenMask2;
                                    green3 = ((greenShift2 < 0) ? (green3 >>> -greenShift2) : (green3 << greenShift2));
                                    int blue3 = pixel2 & blueMask2;
                                    blue3 = ((blueShift2 < 0) ? (blue3 >>> -blueShift2) : (blue3 << blueShift2));
                                    int intensity3 = red4 + red4 + green3 + green3 + green3 + green3 + green3 + blue3 >> 3;
                                    if (newData2.transparentPixel == intensity3) {
                                        intensity3 = 255;
                                    }
                                    newData2.data[offset2] = (byte)intensity3;
                                }
                                else {
                                    newData2.data[offset2] = -2;
                                }
                                ++offset2;
                            }
                        }
                    }
                    this.init(newData2);
                    break;
                }
                default: {
                    SWT.error(5);
                    break;
                }
            }
        }
        this.init();
    }
    
    public Image(final Device device, Rectangle bounds) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        if (bounds == null) {
            SWT.error(4);
        }
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
        bounds = DPIUtil.autoScaleUp(bounds);
        this.init(bounds.width, bounds.height);
        this.init();
    }
    
    public Image(final Device device, ImageData data) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        if (data == null) {
            SWT.error(4);
        }
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
        data = DPIUtil.autoScaleUp(device, data);
        this.init(data);
        this.init();
    }
    
    public Image(final Device device, ImageData source, ImageData mask) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        if (source == null) {
            SWT.error(4);
        }
        if (mask == null) {
            SWT.error(4);
        }
        if (source.width != mask.width || source.height != mask.height) {
            SWT.error(5);
        }
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
        source = DPIUtil.autoScaleUp(device, source);
        mask = DPIUtil.autoScaleUp(device, mask);
        mask = ImageData.convertMask(mask);
        init(this.device, this, source, mask);
        this.init();
    }
    
    public Image(final Device device, final InputStream stream) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
        final ImageData data = DPIUtil.autoScaleUp(device, new ImageData(stream));
        this.init(data);
        this.init();
    }
    
    public Image(final Device device, final String filename) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        if (filename == null) {
            SWT.error(4);
        }
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
        final ImageData data = DPIUtil.autoScaleUp(device, new ImageData(filename));
        this.init(data);
        this.init();
    }
    
    public Image(final Device device, final ImageFileNameProvider imageFileNameProvider) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        this.imageFileNameProvider = imageFileNameProvider;
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
        final boolean[] found = { false };
        final String fileName = DPIUtil.validateAndGetImagePathAtZoom(imageFileNameProvider, this.currentDeviceZoom, found);
        if (found[0]) {
            this.initNative(fileName);
            if (this.handle == 0L) {
                this.init(new ImageData(fileName));
            }
        }
        else {
            final ImageData resizedData = DPIUtil.autoScaleUp(device, new ImageData(fileName));
            this.init(resizedData);
        }
        this.init();
    }
    
    public Image(final Device device, final ImageDataProvider imageDataProvider) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.styleFlag = 0;
        this.currentDeviceZoom = 100;
        this.width = -1;
        this.height = -1;
        this.imageDataProvider = imageDataProvider;
        this.currentDeviceZoom = DPIUtil.getDeviceZoom();
        final boolean[] found = { false };
        final ImageData data = DPIUtil.validateAndGetImageDataAtZoom(imageDataProvider, this.currentDeviceZoom, found);
        if (found[0]) {
            this.init(data);
        }
        else {
            final ImageData resizedData = DPIUtil.autoScaleUp(device, data);
            this.init(resizedData);
        }
        this.init();
    }
    
    boolean refreshImageForZoom() {
        boolean refreshed = false;
        final int deviceZoomLevel = DPIUtil.getDeviceZoom();
        if (this.imageFileNameProvider != null) {
            if (deviceZoomLevel != this.currentDeviceZoom) {
                final boolean[] found = { false };
                final String filename = DPIUtil.validateAndGetImagePathAtZoom(this.imageFileNameProvider, deviceZoomLevel, found);
                if (found[0] || this.currentDeviceZoom != 100) {
                    this.destroy();
                    this.initNative(filename);
                    if (this.handle == 0L) {
                        this.init(new ImageData(filename));
                    }
                    this.init();
                    refreshed = true;
                }
                if (!found[0]) {
                    this.destroy();
                    final ImageData resizedData = DPIUtil.autoScaleUp(this.device, new ImageData(filename));
                    this.init(resizedData);
                    this.init();
                    refreshed = true;
                }
                this.currentDeviceZoom = deviceZoomLevel;
            }
        }
        else if (this.imageDataProvider != null) {
            if (deviceZoomLevel != this.currentDeviceZoom) {
                final boolean[] found = { false };
                final ImageData data = DPIUtil.validateAndGetImageDataAtZoom(this.imageDataProvider, deviceZoomLevel, found);
                if (found[0] || this.currentDeviceZoom != 100) {
                    this.destroy();
                    this.init(data);
                    this.init();
                    refreshed = true;
                }
                if (!found[0]) {
                    this.destroy();
                    final ImageData resizedData = DPIUtil.autoScaleUp(this.device, data);
                    this.init(resizedData);
                    this.init();
                    refreshed = true;
                }
                this.currentDeviceZoom = deviceZoomLevel;
            }
        }
        else if (deviceZoomLevel != this.currentDeviceZoom) {
            final ImageData data2 = this.getImageDataAtCurrentZoom();
            this.destroy();
            final ImageData resizedData2 = DPIUtil.autoScaleImageData(this.device, data2, deviceZoomLevel, this.currentDeviceZoom);
            this.init(resizedData2);
            this.init();
            refreshed = true;
            this.currentDeviceZoom = deviceZoomLevel;
        }
        return refreshed;
    }
    
    void initNative(final String filename) {
        this.device.checkGDIP();
        boolean gdip = true;
        if (gdip && C.PTR_SIZEOF == 8 && filename.toLowerCase().endsWith(".gif")) {
            gdip = false;
        }
        if (OS.WIN32_VERSION >= OS.VERSION(6, 1) && filename.toLowerCase().endsWith(".gif")) {
            gdip = false;
        }
        if (gdip) {
            final int length = filename.length();
            final char[] chars = new char[length + 1];
            filename.getChars(0, length, chars, 0);
            final long bitmap = Gdip.Bitmap_new(chars, false);
            if (bitmap != 0L) {
                int error = 2;
                int status = Gdip.Image_GetLastStatus(bitmap);
                if (status == 0) {
                    if (filename.toLowerCase().endsWith(".ico")) {
                        this.type = 1;
                        final long[] hicon = { 0L };
                        status = Gdip.Bitmap_GetHICON(bitmap, hicon);
                        this.handle = hicon[0];
                    }
                    else {
                        this.type = 0;
                        final int width = Gdip.Image_GetWidth(bitmap);
                        final int height = Gdip.Image_GetHeight(bitmap);
                        final int pixelFormat = Gdip.Image_GetPixelFormat(bitmap);
                        switch (pixelFormat) {
                            case 135173:
                            case 135174: {
                                this.handle = createDIB(width, height, 16);
                                break;
                            }
                            case 8207:
                            case 137224: {
                                this.handle = createDIB(width, height, 24);
                                break;
                            }
                            case 139273:
                            case 925707:
                            case 1052676:
                            case 1060876:
                            case 1851406:
                            case 3424269: {
                                this.handle = createDIB(width, height, 32);
                                break;
                            }
                        }
                        if (this.handle != 0L) {
                            final long hDC = this.device.internal_new_GC((GCData)null);
                            final long srcHDC = OS.CreateCompatibleDC(hDC);
                            final long oldSrcBitmap = OS.SelectObject(srcHDC, this.handle);
                            final long graphics = Gdip.Graphics_new(srcHDC);
                            if (graphics != 0L) {
                                final Rect rect = new Rect();
                                rect.Width = width;
                                rect.Height = height;
                                status = Gdip.Graphics_DrawImage(graphics, bitmap, rect, 0, 0, width, height, 2, 0L, 0L, 0L);
                                if (status != 0) {
                                    error = 40;
                                    OS.DeleteObject(this.handle);
                                    this.handle = 0L;
                                }
                                Gdip.Graphics_delete(graphics);
                            }
                            OS.SelectObject(srcHDC, oldSrcBitmap);
                            OS.DeleteDC(srcHDC);
                            this.device.internal_dispose_GC(hDC, (GCData)null);
                        }
                        else {
                            final long lockedBitmapData = Gdip.BitmapData_new();
                            if (lockedBitmapData != 0L) {
                                status = Gdip.Bitmap_LockBits(bitmap, 0L, 0, pixelFormat, lockedBitmapData);
                                if (status == 0) {
                                    final BitmapData bitmapData = new BitmapData();
                                    Gdip.MoveMemory(bitmapData, lockedBitmapData);
                                    final int stride = bitmapData.Stride;
                                    final long pixels = bitmapData.Scan0;
                                    int depth = 0;
                                    final int scanlinePad = 4;
                                    int transparentPixel = -1;
                                    switch (bitmapData.PixelFormat) {
                                        case 196865: {
                                            depth = 1;
                                            break;
                                        }
                                        case 197634: {
                                            depth = 4;
                                            break;
                                        }
                                        case 198659: {
                                            depth = 8;
                                            break;
                                        }
                                        case 135173:
                                        case 135174:
                                        case 397319: {
                                            depth = 16;
                                            break;
                                        }
                                        case 137224: {
                                            depth = 24;
                                            break;
                                        }
                                        case 139273:
                                        case 2498570: {
                                            depth = 32;
                                            break;
                                        }
                                    }
                                    if (depth != 0) {
                                        PaletteData paletteData = null;
                                        switch (bitmapData.PixelFormat) {
                                            case 196865:
                                            case 197634:
                                            case 198659: {
                                                final int paletteSize = Gdip.Image_GetPaletteSize(bitmap);
                                                final long hHeap = OS.GetProcessHeap();
                                                final long palette = OS.HeapAlloc(hHeap, 8, paletteSize);
                                                if (palette == 0L) {
                                                    SWT.error(2);
                                                }
                                                Gdip.Image_GetPalette(bitmap, palette, paletteSize);
                                                final ColorPalette colorPalette = new ColorPalette();
                                                Gdip.MoveMemory(colorPalette, palette, ColorPalette.sizeof);
                                                final int[] entries = new int[colorPalette.Count];
                                                OS.MoveMemory(entries, palette + 8L, entries.length * 4);
                                                OS.HeapFree(hHeap, 0, palette);
                                                final RGB[] rgbs = new RGB[colorPalette.Count];
                                                paletteData = new PaletteData(rgbs);
                                                for (int i = 0; i < entries.length; ++i) {
                                                    if ((entries[i] >> 24 & 0xFF) == 0x0 && (colorPalette.Flags & 0x1) != 0x0) {
                                                        transparentPixel = i;
                                                    }
                                                    rgbs[i] = new RGB((entries[i] & 0xFF0000) >> 16, (entries[i] & 0xFF00) >> 8, (entries[i] & 0xFF) >> 0);
                                                }
                                                break;
                                            }
                                            case 135173:
                                            case 397319: {
                                                paletteData = new PaletteData(31744, 992, 31);
                                                break;
                                            }
                                            case 135174: {
                                                paletteData = new PaletteData(63488, 2016, 31);
                                                break;
                                            }
                                            case 137224: {
                                                paletteData = new PaletteData(255, 65280, 16711680);
                                                break;
                                            }
                                            case 139273:
                                            case 2498570: {
                                                paletteData = new PaletteData(65280, 16711680, -16777216);
                                                break;
                                            }
                                        }
                                        final byte[] data = new byte[stride * height];
                                        byte[] alphaData = null;
                                        OS.MoveMemory(data, pixels, data.length);
                                        switch (bitmapData.PixelFormat) {
                                            case 397319: {
                                                alphaData = new byte[width * height];
                                                for (int j = 1, k = 0; j < data.length; j += 2, ++k) {
                                                    alphaData[k] = (byte)(((data[j] & 0x80) != 0x0) ? 255 : 0);
                                                }
                                                break;
                                            }
                                            case 2498570: {
                                                alphaData = new byte[width * height];
                                                for (int j = 3, k = 0; j < data.length; j += 4, ++k) {
                                                    alphaData[k] = data[j];
                                                }
                                                break;
                                            }
                                        }
                                        final ImageData img = new ImageData(width, height, depth, paletteData, 4, data);
                                        img.transparentPixel = transparentPixel;
                                        img.alphaData = alphaData;
                                        this.init(img);
                                    }
                                    Gdip.Bitmap_UnlockBits(bitmap, lockedBitmapData);
                                }
                                else {
                                    error = 40;
                                }
                                Gdip.BitmapData_delete(lockedBitmapData);
                            }
                        }
                    }
                }
                Gdip.Bitmap_delete(bitmap);
                if (status == 0 && this.handle == 0L) {
                    SWT.error(error);
                }
            }
        }
    }
    
    long[] createGdipImage() {
        switch (this.type) {
            case 0: {
                final BITMAP bm = new BITMAP();
                OS.GetObject(this.handle, BITMAP.sizeof, bm);
                final int depth = bm.bmPlanes * bm.bmBitsPixel;
                final boolean isDib = bm.bmBits != 0L;
                final boolean hasAlpha = isDib && depth == 32;
                if (hasAlpha || this.transparentPixel != -1) {
                    final int imgWidth = bm.bmWidth;
                    final int imgHeight = bm.bmHeight;
                    final long hDC = this.device.internal_new_GC((GCData)null);
                    if (hDC == 0L) {
                        SWT.error(2);
                    }
                    final long srcHdc = OS.CreateCompatibleDC(hDC);
                    final long memHdc = OS.CreateCompatibleDC(hDC);
                    this.device.internal_dispose_GC(hDC, (GCData)null);
                    if (srcHdc == 0L) {
                        SWT.error(2);
                    }
                    if (memHdc == 0L) {
                        SWT.error(2);
                    }
                    final long oldSrcBitmap = OS.SelectObject(srcHdc, this.handle);
                    final long memDib = createDIB(imgWidth, imgHeight, 32);
                    if (memDib == 0L) {
                        SWT.error(2);
                    }
                    final long oldMemBitmap = OS.SelectObject(memHdc, memDib);
                    final BITMAP dibBM = new BITMAP();
                    OS.GetObject(memDib, BITMAP.sizeof, dibBM);
                    final int sizeInBytes = dibBM.bmWidthBytes * dibBM.bmHeight;
                    OS.BitBlt(memHdc, 0, 0, imgWidth, imgHeight, srcHdc, 0, 0, 13369376);
                    final long hHeap = OS.GetProcessHeap();
                    final long pixels = OS.HeapAlloc(hHeap, 8, sizeInBytes);
                    if (pixels == 0L) {
                        SWT.error(2);
                    }
                    byte red = 0;
                    byte green = 0;
                    byte blue = 0;
                    if (hasAlpha) {
                        OS.MoveMemory(pixels, bm.bmBits, sizeInBytes);
                    }
                    else {
                        if (bm.bmBitsPixel <= 8) {
                            final byte[] color = new byte[4];
                            OS.GetDIBColorTable(srcHdc, this.transparentPixel, 1, color);
                            blue = color[0];
                            green = color[1];
                            red = color[2];
                        }
                        else {
                            switch (bm.bmBitsPixel) {
                                case 16: {
                                    final int blueMask = 31;
                                    final int blueShift = ImageData.getChannelShift(31);
                                    final byte[] blues = ImageData.ANY_TO_EIGHT[ImageData.getChannelWidth(31, blueShift)];
                                    blue = blues[(this.transparentPixel & 0x1F) >> blueShift];
                                    final int greenMask = 992;
                                    final int greenShift = ImageData.getChannelShift(992);
                                    final byte[] greens = ImageData.ANY_TO_EIGHT[ImageData.getChannelWidth(992, greenShift)];
                                    green = greens[(this.transparentPixel & 0x3E0) >> greenShift];
                                    final int redMask = 31744;
                                    final int redShift = ImageData.getChannelShift(31744);
                                    final byte[] reds = ImageData.ANY_TO_EIGHT[ImageData.getChannelWidth(31744, redShift)];
                                    red = reds[(this.transparentPixel & 0x7C00) >> redShift];
                                    break;
                                }
                                case 24: {
                                    blue = (byte)((this.transparentPixel & 0xFF0000) >> 16);
                                    green = (byte)((this.transparentPixel & 0xFF00) >> 8);
                                    red = (byte)(this.transparentPixel & 0xFF);
                                    break;
                                }
                                case 32: {
                                    blue = (byte)((this.transparentPixel & 0xFF000000) >>> 24);
                                    green = (byte)((this.transparentPixel & 0xFF0000) >> 16);
                                    red = (byte)((this.transparentPixel & 0xFF00) >> 8);
                                    break;
                                }
                            }
                        }
                        final byte[] srcData = new byte[sizeInBytes];
                        OS.MoveMemory(srcData, dibBM.bmBits, sizeInBytes);
                        int y = 0;
                        int dp = 0;
                        while (y < imgHeight) {
                            for (int x = 0; x < imgWidth; ++x) {
                                if (srcData[dp] == blue && srcData[dp + 1] == green && srcData[dp + 2] == red) {
                                    srcData[dp + 3] = 0;
                                }
                                else {
                                    srcData[dp + 3] = -1;
                                }
                                dp += 4;
                            }
                            ++y;
                        }
                        OS.MoveMemory(pixels, srcData, sizeInBytes);
                    }
                    OS.SelectObject(srcHdc, oldSrcBitmap);
                    OS.SelectObject(memHdc, oldMemBitmap);
                    OS.DeleteObject(srcHdc);
                    OS.DeleteObject(memHdc);
                    OS.DeleteObject(memDib);
                    final int pixelFormat = hasAlpha ? 925707 : 2498570;
                    return new long[] { Gdip.Bitmap_new(imgWidth, imgHeight, dibBM.bmWidthBytes, pixelFormat, pixels), pixels };
                }
                return new long[] { Gdip.Bitmap_new(this.handle, 0L), 0L };
            }
            case 1: {
                final ICONINFO iconInfo = new ICONINFO();
                OS.GetIconInfo(this.handle, iconInfo);
                long hBitmap = iconInfo.hbmColor;
                if (hBitmap == 0L) {
                    hBitmap = iconInfo.hbmMask;
                }
                final BITMAP bm2 = new BITMAP();
                OS.GetObject(hBitmap, BITMAP.sizeof, bm2);
                final int imgWidth = bm2.bmWidth;
                final int imgHeight = (hBitmap == iconInfo.hbmMask) ? (bm2.bmHeight / 2) : bm2.bmHeight;
                long img = 0L;
                long pixels2 = 0L;
                if (imgWidth > imgHeight || bm2.bmBitsPixel == 32) {
                    final long hDC2 = this.device.internal_new_GC((GCData)null);
                    final long srcHdc2 = OS.CreateCompatibleDC(hDC2);
                    final long oldSrcBitmap2 = OS.SelectObject(srcHdc2, hBitmap);
                    final long memHdc2 = OS.CreateCompatibleDC(hDC2);
                    final long memDib2 = createDIB(imgWidth, imgHeight, 32);
                    if (memDib2 == 0L) {
                        SWT.error(2);
                    }
                    final long oldMemBitmap2 = OS.SelectObject(memHdc2, memDib2);
                    final BITMAP dibBM2 = new BITMAP();
                    OS.GetObject(memDib2, BITMAP.sizeof, dibBM2);
                    OS.BitBlt(memHdc2, 0, 0, imgWidth, imgHeight, srcHdc2, 0, (hBitmap == iconInfo.hbmMask) ? imgHeight : 0, 13369376);
                    OS.SelectObject(memHdc2, oldMemBitmap2);
                    OS.DeleteObject(memHdc2);
                    final byte[] srcData2 = new byte[dibBM2.bmWidthBytes * dibBM2.bmHeight];
                    OS.MoveMemory(srcData2, dibBM2.bmBits, srcData2.length);
                    OS.DeleteObject(memDib2);
                    OS.SelectObject(srcHdc2, iconInfo.hbmMask);
                    int y2 = 0;
                    int dp2 = 3;
                    while (y2 < imgHeight) {
                        for (int x2 = 0; x2 < imgWidth; ++x2) {
                            if (srcData2[dp2] == 0) {
                                if (OS.GetPixel(srcHdc2, x2, y2) != 0) {
                                    srcData2[dp2] = 0;
                                }
                                else {
                                    srcData2[dp2] = -1;
                                }
                            }
                            dp2 += 4;
                        }
                        ++y2;
                    }
                    OS.SelectObject(srcHdc2, oldSrcBitmap2);
                    OS.DeleteObject(srcHdc2);
                    this.device.internal_dispose_GC(hDC2, (GCData)null);
                    final long hHeap2 = OS.GetProcessHeap();
                    pixels2 = OS.HeapAlloc(hHeap2, 8, srcData2.length);
                    if (pixels2 == 0L) {
                        SWT.error(2);
                    }
                    OS.MoveMemory(pixels2, srcData2, srcData2.length);
                    img = Gdip.Bitmap_new(imgWidth, imgHeight, dibBM2.bmWidthBytes, 2498570, pixels2);
                }
                else {
                    img = Gdip.Bitmap_new(this.handle);
                }
                if (iconInfo.hbmColor != 0L) {
                    OS.DeleteObject(iconInfo.hbmColor);
                }
                if (iconInfo.hbmMask != 0L) {
                    OS.DeleteObject(iconInfo.hbmMask);
                }
                return new long[] { img, pixels2 };
            }
            default: {
                SWT.error(40);
                return null;
            }
        }
    }
    
    @Override
    void destroy() {
        if (this.memGC != null) {
            this.memGC.dispose();
        }
        if (this.type == 1) {
            OS.DestroyIcon(this.handle);
        }
        else {
            OS.DeleteObject(this.handle);
        }
        this.handle = 0L;
        this.memGC = null;
    }
    
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Image)) {
            return false;
        }
        final Image image = (Image)object;
        if (this.device != image.device || this.transparentPixel != image.transparentPixel) {
            return false;
        }
        if (this.imageDataProvider != null && image.imageDataProvider != null) {
            return this.styleFlag == image.styleFlag && this.imageDataProvider.equals(image.imageDataProvider);
        }
        if (this.imageFileNameProvider != null && image.imageFileNameProvider != null) {
            return this.styleFlag == image.styleFlag && this.imageFileNameProvider.equals(image.imageFileNameProvider);
        }
        return this.handle == image.handle;
    }
    
    public Color getBackground() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (this.transparentPixel == -1) {
            return null;
        }
        final long hDC = this.device.internal_new_GC((GCData)null);
        final BITMAP bm = new BITMAP();
        OS.GetObject(this.handle, BITMAP.sizeof, bm);
        final long hdcMem = OS.CreateCompatibleDC(hDC);
        final long hOldObject = OS.SelectObject(hdcMem, this.handle);
        int red = 0;
        int green = 0;
        int blue = 0;
        if (bm.bmBitsPixel <= 8) {
            final byte[] color = new byte[4];
            OS.GetDIBColorTable(hdcMem, this.transparentPixel, 1, color);
            blue = (color[0] & 0xFF);
            green = (color[1] & 0xFF);
            red = (color[2] & 0xFF);
        }
        else {
            switch (bm.bmBitsPixel) {
                case 16: {
                    blue = (this.transparentPixel & 0x1F) << 3;
                    green = (this.transparentPixel & 0x3E0) >> 2;
                    red = (this.transparentPixel & 0x7C00) >> 7;
                    break;
                }
                case 24: {
                    blue = (this.transparentPixel & 0xFF0000) >> 16;
                    green = (this.transparentPixel & 0xFF00) >> 8;
                    red = (this.transparentPixel & 0xFF);
                    break;
                }
                case 32: {
                    blue = (this.transparentPixel & 0xFF000000) >>> 24;
                    green = (this.transparentPixel & 0xFF0000) >> 16;
                    red = (this.transparentPixel & 0xFF00) >> 8;
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        OS.SelectObject(hdcMem, hOldObject);
        OS.DeleteDC(hdcMem);
        this.device.internal_dispose_GC(hDC, (GCData)null);
        return Color.win32_new(this.device, blue << 16 | green << 8 | red);
    }
    
    public Rectangle getBounds() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return this.getBounds(100);
    }
    
    Rectangle getBounds(final int zoom) {
        Rectangle bounds = this.getBoundsInPixels();
        if (bounds != null && zoom != this.currentDeviceZoom) {
            bounds = DPIUtil.autoScaleBounds(bounds, zoom, this.currentDeviceZoom);
        }
        return bounds;
    }
    
    @Deprecated
    public Rectangle getBoundsInPixels() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (this.width != -1 && this.height != -1) {
            return new Rectangle(0, 0, this.width, this.height);
        }
        switch (this.type) {
            case 0: {
                final BITMAP bm = new BITMAP();
                OS.GetObject(this.handle, BITMAP.sizeof, bm);
                final int x = 0;
                final int y = 0;
                final int bmWidth = bm.bmWidth;
                this.width = bmWidth;
                return new Rectangle(0, 0, bmWidth, this.height = bm.bmHeight);
            }
            case 1: {
                final ICONINFO info = new ICONINFO();
                OS.GetIconInfo(this.handle, info);
                long hBitmap = info.hbmColor;
                if (hBitmap == 0L) {
                    hBitmap = info.hbmMask;
                }
                final BITMAP bm2 = new BITMAP();
                OS.GetObject(hBitmap, BITMAP.sizeof, bm2);
                if (hBitmap == info.hbmMask) {
                    final BITMAP bitmap2;
                    final BITMAP bitmap = bitmap2 = bm2;
                    bitmap2.bmHeight /= 2;
                }
                if (info.hbmColor != 0L) {
                    OS.DeleteObject(info.hbmColor);
                }
                if (info.hbmMask != 0L) {
                    OS.DeleteObject(info.hbmMask);
                }
                final int x2 = 0;
                final int y2 = 0;
                final int bmWidth2 = bm2.bmWidth;
                this.width = bmWidth2;
                return new Rectangle(0, 0, bmWidth2, this.height = bm2.bmHeight);
            }
            default: {
                SWT.error(40);
                return null;
            }
        }
    }
    
    public ImageData getImageData() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return this.getImageData(100);
    }
    
    public ImageData getImageData(final int zoom) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (zoom == this.currentDeviceZoom) {
            return this.getImageDataAtCurrentZoom();
        }
        if (this.imageDataProvider != null) {
            final boolean[] found = { false };
            final ImageData data = DPIUtil.validateAndGetImageDataAtZoom(this.imageDataProvider, zoom, found);
            if (found[0]) {
                return data;
            }
            return DPIUtil.autoScaleImageData(this.device, data, zoom, 100);
        }
        else {
            if (this.imageFileNameProvider == null) {
                return DPIUtil.autoScaleImageData(this.device, this.getImageDataAtCurrentZoom(), zoom, this.currentDeviceZoom);
            }
            final boolean[] found = { false };
            final String fileName = DPIUtil.validateAndGetImagePathAtZoom(this.imageFileNameProvider, zoom, found);
            if (found[0]) {
                return new ImageData(fileName);
            }
            return DPIUtil.autoScaleImageData(this.device, new ImageData(fileName), zoom, 100);
        }
    }
    
    @Deprecated
    public ImageData getImageDataAtCurrentZoom() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        switch (this.type) {
            case 1: {
                final ICONINFO info = new ICONINFO();
                OS.GetIconInfo(this.handle, info);
                long hBitmap = info.hbmColor;
                if (hBitmap == 0L) {
                    hBitmap = info.hbmMask;
                }
                final BITMAP bm = new BITMAP();
                OS.GetObject(hBitmap, BITMAP.sizeof, bm);
                final int depth = bm.bmPlanes * bm.bmBitsPixel;
                final int width = bm.bmWidth;
                if (hBitmap == info.hbmMask) {
                    final BITMAP bitmap2;
                    final BITMAP bitmap = bitmap2 = bm;
                    bitmap2.bmHeight /= 2;
                }
                final int height = bm.bmHeight;
                int numColors = 0;
                if (depth <= 8) {
                    numColors = 1 << depth;
                }
                BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
                bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
                bmiHeader.biWidth = width;
                bmiHeader.biHeight = -height;
                bmiHeader.biPlanes = 1;
                bmiHeader.biBitCount = (short)depth;
                bmiHeader.biCompression = 0;
                byte[] bmi = new byte[BITMAPINFOHEADER.sizeof + numColors * 4];
                OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
                final long hDC = this.device.internal_new_GC((GCData)null);
                final long hBitmapDC = OS.CreateCompatibleDC(hDC);
                final long hOldBitmap = OS.SelectObject(hBitmapDC, hBitmap);
                OS.GetDIBits(hBitmapDC, hBitmap, 0, height, null, bmi, 0);
                OS.MoveMemory(bmiHeader, bmi, BITMAPINFOHEADER.sizeof);
                int imageSize = bmiHeader.biSizeImage;
                final byte[] data = new byte[imageSize];
                OS.GetDIBits(hBitmapDC, hBitmap, 0, height, data, bmi, 0);
                PaletteData palette = null;
                if (depth <= 8) {
                    final RGB[] rgbs = new RGB[numColors];
                    int srcIndex = 40;
                    for (int i = 0; i < numColors; ++i) {
                        rgbs[i] = new RGB(bmi[srcIndex + 2] & 0xFF, bmi[srcIndex + 1] & 0xFF, bmi[srcIndex] & 0xFF);
                        srcIndex += 4;
                    }
                    palette = new PaletteData(rgbs);
                }
                else if (depth == 16) {
                    palette = new PaletteData(31744, 992, 31);
                }
                else if (depth == 24) {
                    palette = new PaletteData(255, 65280, 16711680);
                }
                else if (depth == 32) {
                    palette = new PaletteData(65280, 16711680, -16777216);
                }
                else {
                    SWT.error(38);
                }
                byte[] maskData = null;
                if (info.hbmColor == 0L) {
                    maskData = new byte[imageSize];
                    OS.GetDIBits(hBitmapDC, hBitmap, height, height, maskData, bmi, 0);
                }
                else {
                    bmiHeader = new BITMAPINFOHEADER();
                    bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
                    bmiHeader.biWidth = width;
                    bmiHeader.biHeight = -height;
                    bmiHeader.biPlanes = 1;
                    bmiHeader.biBitCount = 1;
                    bmiHeader.biCompression = 0;
                    bmi = new byte[BITMAPINFOHEADER.sizeof + 8];
                    OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
                    final int offset = BITMAPINFOHEADER.sizeof;
                    final byte[] array = bmi;
                    final int n = offset + 4;
                    final byte[] array2 = bmi;
                    final int n2 = offset + 5;
                    final byte[] array3 = bmi;
                    final int n3 = offset + 6;
                    final byte b2 = -1;
                    array3[n3] = -1;
                    array[n] = (array2[n2] = -1);
                    bmi[offset + 7] = 0;
                    OS.SelectObject(hBitmapDC, info.hbmMask);
                    OS.GetDIBits(hBitmapDC, info.hbmMask, 0, height, null, bmi, 0);
                    OS.MoveMemory(bmiHeader, bmi, BITMAPINFOHEADER.sizeof);
                    imageSize = bmiHeader.biSizeImage;
                    maskData = new byte[imageSize];
                    OS.GetDIBits(hBitmapDC, info.hbmMask, 0, height, maskData, bmi, 0);
                    for (int j = 0; j < maskData.length; ++j) {
                        final byte[] array4 = maskData;
                        final int n4 = j;
                        final byte[] array5 = array4;
                        final int n5 = n4;
                        array5[n5] ^= -1;
                    }
                    final int bpl = imageSize / height;
                    int maskPad;
                    for (maskPad = 1; maskPad < 128; ++maskPad) {
                        final int calcBpl = ((width + 7) / 8 + (maskPad - 1)) / maskPad * maskPad;
                        if (calcBpl == bpl) {
                            break;
                        }
                    }
                    maskData = ImageData.convertPad(maskData, width, height, 1, maskPad, 2);
                }
                OS.SelectObject(hBitmapDC, hOldBitmap);
                OS.DeleteDC(hBitmapDC);
                this.device.internal_dispose_GC(hDC, (GCData)null);
                if (info.hbmColor != 0L) {
                    OS.DeleteObject(info.hbmColor);
                }
                if (info.hbmMask != 0L) {
                    OS.DeleteObject(info.hbmMask);
                }
                final ImageData imageData = new ImageData(width, height, depth, palette, 4, data);
                imageData.maskData = maskData;
                imageData.maskPad = 2;
                return imageData;
            }
            case 0: {
                final BITMAP bm2 = new BITMAP();
                OS.GetObject(this.handle, BITMAP.sizeof, bm2);
                final int depth2 = bm2.bmPlanes * bm2.bmBitsPixel;
                final int width2 = bm2.bmWidth;
                final int height2 = bm2.bmHeight;
                final boolean isDib = bm2.bmBits != 0L;
                final long hDC2 = this.device.internal_new_GC((GCData)null);
                DIBSECTION dib = null;
                if (isDib) {
                    dib = new DIBSECTION();
                    OS.GetObject(this.handle, DIBSECTION.sizeof, dib);
                }
                int numColors2 = 0;
                if (depth2 <= 8) {
                    if (isDib) {
                        numColors2 = dib.biClrUsed;
                    }
                    else {
                        numColors2 = 1 << depth2;
                    }
                }
                byte[] bmi = null;
                BITMAPINFOHEADER bmiHeader2 = null;
                if (!isDib) {
                    bmiHeader2 = new BITMAPINFOHEADER();
                    bmiHeader2.biSize = BITMAPINFOHEADER.sizeof;
                    bmiHeader2.biWidth = width2;
                    bmiHeader2.biHeight = -height2;
                    bmiHeader2.biPlanes = 1;
                    bmiHeader2.biBitCount = (short)depth2;
                    bmiHeader2.biCompression = 0;
                    bmi = new byte[BITMAPINFOHEADER.sizeof + numColors2 * 4];
                    OS.MoveMemory(bmi, bmiHeader2, BITMAPINFOHEADER.sizeof);
                }
                final long hBitmapDC2 = OS.CreateCompatibleDC(hDC2);
                final long hOldBitmap2 = OS.SelectObject(hBitmapDC2, this.handle);
                int imageSize2;
                if (isDib) {
                    imageSize2 = dib.biSizeImage;
                }
                else {
                    OS.GetDIBits(hBitmapDC2, this.handle, 0, height2, null, bmi, 0);
                    OS.MoveMemory(bmiHeader2, bmi, BITMAPINFOHEADER.sizeof);
                    imageSize2 = bmiHeader2.biSizeImage;
                }
                final byte[] data2 = new byte[imageSize2];
                if (isDib) {
                    OS.MoveMemory(data2, bm2.bmBits, imageSize2);
                }
                else {
                    OS.GetDIBits(hBitmapDC2, this.handle, 0, height2, data2, bmi, 0);
                }
                PaletteData palette2 = null;
                if (depth2 <= 8) {
                    final RGB[] rgbs2 = new RGB[numColors2];
                    if (isDib) {
                        final byte[] colors = new byte[numColors2 * 4];
                        OS.GetDIBColorTable(hBitmapDC2, 0, numColors2, colors);
                        int colorIndex = 0;
                        for (int i = 0; i < rgbs2.length; ++i) {
                            rgbs2[i] = new RGB(colors[colorIndex + 2] & 0xFF, colors[colorIndex + 1] & 0xFF, colors[colorIndex] & 0xFF);
                            colorIndex += 4;
                        }
                    }
                    else {
                        int srcIndex2 = BITMAPINFOHEADER.sizeof;
                        for (int k = 0; k < numColors2; ++k) {
                            rgbs2[k] = new RGB(bmi[srcIndex2 + 2] & 0xFF, bmi[srcIndex2 + 1] & 0xFF, bmi[srcIndex2] & 0xFF);
                            srcIndex2 += 4;
                        }
                    }
                    palette2 = new PaletteData(rgbs2);
                }
                else if (depth2 == 16) {
                    palette2 = new PaletteData(31744, 992, 31);
                }
                else if (depth2 == 24) {
                    palette2 = new PaletteData(255, 65280, 16711680);
                }
                else if (depth2 == 32) {
                    palette2 = new PaletteData(65280, 16711680, -16777216);
                }
                else {
                    SWT.error(38);
                }
                OS.SelectObject(hBitmapDC2, hOldBitmap2);
                OS.DeleteDC(hBitmapDC2);
                this.device.internal_dispose_GC(hDC2, (GCData)null);
                final ImageData imageData2 = new ImageData(width2, height2, depth2, palette2, 4, data2);
                imageData2.transparentPixel = this.transparentPixel;
                if (isDib && depth2 == 32) {
                    final byte[] straightData = new byte[imageSize2];
                    final byte[] alphaData = new byte[width2 * height2];
                    boolean validAlpha = true;
                    for (int ap = 0, dp = 0; validAlpha && ap < alphaData.length; ++ap, dp += 4) {
                        final int b3 = data2[dp] & 0xFF;
                        final int g = data2[dp + 1] & 0xFF;
                        final int r = data2[dp + 2] & 0xFF;
                        final int a = data2[dp + 3] & 0xFF;
                        alphaData[ap] = (byte)a;
                        validAlpha = (validAlpha && b3 <= a && g <= a && r <= a);
                        if (a != 0) {
                            straightData[dp] = (byte)((b3 * 255 + a / 2) / a);
                            straightData[dp + 1] = (byte)((g * 255 + a / 2) / a);
                            straightData[dp + 2] = (byte)((r * 255 + a / 2) / a);
                        }
                    }
                    if (validAlpha) {
                        imageData2.data = straightData;
                        imageData2.alphaData = alphaData;
                    }
                    else {
                        for (int dp2 = 3; dp2 < imageSize2; dp2 += 4) {
                            data2[dp2] = -1;
                        }
                    }
                }
                return imageData2;
            }
            default: {
                SWT.error(40);
                return null;
            }
        }
    }
    
    public int hashCode() {
        if (this.imageDataProvider != null) {
            return this.imageDataProvider.hashCode();
        }
        if (this.imageFileNameProvider != null) {
            return this.imageFileNameProvider.hashCode();
        }
        return (int)this.handle;
    }
    
    void init(final int width, final int height) {
        if (width <= 0 || height <= 0) {
            SWT.error(5);
        }
        this.type = 0;
        final long hDC = this.device.internal_new_GC((GCData)null);
        this.handle = OS.CreateCompatibleBitmap(hDC, width, height);
        if (this.handle == 0L) {
            final int bits = OS.GetDeviceCaps(hDC, 12);
            final int planes = OS.GetDeviceCaps(hDC, 14);
            int depth = bits * planes;
            if (depth < 16) {
                depth = 16;
            }
            if (depth > 24) {
                depth = 24;
            }
            this.handle = createDIB(width, height, depth);
        }
        if (this.handle != 0L) {
            final long memDC = OS.CreateCompatibleDC(hDC);
            final long hOldBitmap = OS.SelectObject(memDC, this.handle);
            OS.PatBlt(memDC, 0, 0, width, height, 15728673);
            OS.SelectObject(memDC, hOldBitmap);
            OS.DeleteDC(memDC);
        }
        this.device.internal_dispose_GC(hDC, (GCData)null);
        if (this.handle == 0L) {
            SWT.error(2, null, this.device.getLastError());
        }
    }
    
    static long createDIB(final int width, final int height, final int depth) {
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biWidth = width;
        bmiHeader.biHeight = -height;
        bmiHeader.biPlanes = 1;
        bmiHeader.biBitCount = (short)depth;
        bmiHeader.biCompression = 0;
        final byte[] bmi = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
        final long[] pBits = { 0L };
        return OS.CreateDIBSection(0L, bmi, 0, pBits, 0L, 0);
    }
    
    static long[] init(final Device device, final Image image, ImageData i) {
        if (i.depth == 2) {
            final ImageData img = new ImageData(i.width, i.height, 4, i.palette);
            ImageData.blit(1, i.data, i.depth, i.bytesPerLine, i.getByteOrder(), 0, 0, i.width, i.height, null, null, null, 255, null, 0, 0, 0, img.data, img.depth, img.bytesPerLine, i.getByteOrder(), 0, 0, img.width, img.height, null, null, null, false, false);
            img.transparentPixel = i.transparentPixel;
            img.maskPad = i.maskPad;
            img.maskData = i.maskData;
            img.alpha = i.alpha;
            img.alphaData = i.alphaData;
            i = img;
        }
        final boolean hasAlpha = i.alpha != -1 || i.alphaData != null;
        if (i.palette.isDirect) {
            final PaletteData palette = i.palette;
            final int redMask = palette.redMask;
            final int greenMask = palette.greenMask;
            final int blueMask = palette.blueMask;
            int newDepth = i.depth;
            int newOrder = 1;
            PaletteData newPalette = null;
            if (hasAlpha) {
                newDepth = 32;
                newPalette = new PaletteData(65280, 16711680, -16777216);
            }
            else {
                switch (i.depth) {
                    case 8: {
                        final int minDepth = ImageData.getChannelWidth(redMask, palette.redShift) + ImageData.getChannelWidth(greenMask, palette.greenShift) + ImageData.getChannelWidth(blueMask, palette.blueShift);
                        if (minDepth <= 16) {
                            newDepth = 16;
                            newOrder = 0;
                            newPalette = new PaletteData(31744, 992, 31);
                            break;
                        }
                        newDepth = 24;
                        newPalette = new PaletteData(255, 65280, 16711680);
                        break;
                    }
                    case 16: {
                        newOrder = 0;
                        if (redMask != 31744 || greenMask != 992 || blueMask != 31) {
                            newPalette = new PaletteData(31744, 992, 31);
                            break;
                        }
                        break;
                    }
                    case 24: {
                        if (redMask != 255 || greenMask != 65280 || blueMask != 16711680) {
                            newPalette = new PaletteData(255, 65280, 16711680);
                            break;
                        }
                        break;
                    }
                    case 32: {
                        newDepth = 24;
                        newPalette = new PaletteData(255, 65280, 16711680);
                        break;
                    }
                    default: {
                        SWT.error(38);
                        break;
                    }
                }
            }
            if (newPalette != null) {
                final ImageData img2 = new ImageData(i.width, i.height, newDepth, newPalette);
                ImageData.blit(1, i.data, i.depth, i.bytesPerLine, i.getByteOrder(), 0, 0, i.width, i.height, redMask, greenMask, blueMask, 255, null, 0, 0, 0, img2.data, img2.depth, img2.bytesPerLine, newOrder, 0, 0, img2.width, img2.height, newPalette.redMask, newPalette.greenMask, newPalette.blueMask, false, false);
                if (i.transparentPixel != -1) {
                    img2.transparentPixel = newPalette.getPixel(palette.getRGB(i.transparentPixel));
                }
                img2.maskPad = i.maskPad;
                img2.maskData = i.maskData;
                img2.alpha = i.alpha;
                img2.alphaData = i.alphaData;
                i = img2;
            }
        }
        else if (hasAlpha) {
            final int newDepth2 = 32;
            final PaletteData newPalette2 = new PaletteData(65280, 16711680, -16777216);
            final int newOrder2 = 1;
            final RGB[] rgbs = i.palette.getRGBs();
            final int length = rgbs.length;
            final byte[] srcReds = new byte[length];
            final byte[] srcGreens = new byte[length];
            final byte[] srcBlues = new byte[length];
            for (int j = 0; j < rgbs.length; ++j) {
                final RGB rgb = rgbs[j];
                if (rgb != null) {
                    srcReds[j] = (byte)rgb.red;
                    srcGreens[j] = (byte)rgb.green;
                    srcBlues[j] = (byte)rgb.blue;
                }
            }
            final ImageData img3 = new ImageData(i.width, i.height, 32, newPalette2);
            ImageData.blit(1, i.data, i.depth, i.bytesPerLine, i.getByteOrder(), 0, 0, i.width, i.height, srcReds, srcGreens, srcBlues, 255, null, 0, 0, 0, img3.data, img3.depth, img3.bytesPerLine, 1, 0, 0, img3.width, img3.height, newPalette2.redMask, newPalette2.greenMask, newPalette2.blueMask, false, false);
            if (i.transparentPixel != -1) {
                img3.transparentPixel = newPalette2.getPixel(i.palette.getRGB(i.transparentPixel));
            }
            img3.maskPad = i.maskPad;
            img3.maskData = i.maskData;
            img3.alpha = i.alpha;
            img3.alphaData = i.alphaData;
            i = img3;
        }
        if (i.alpha != -1) {
            final int alpha = i.alpha & 0xFF;
            final byte[] data = i.data;
            for (int dp = 0; dp < i.data.length; dp += 4) {
                int r = (data[dp] & 0xFF) * alpha + 128;
                r = r + (r >> 8) >> 8;
                int g = (data[dp + 1] & 0xFF) * alpha + 128;
                g = g + (g >> 8) >> 8;
                int b = (data[dp + 2] & 0xFF) * alpha + 128;
                b = b + (b >> 8) >> 8;
                data[dp] = (byte)b;
                data[dp + 1] = (byte)g;
                data[dp + 2] = (byte)r;
                data[dp + 3] = (byte)alpha;
            }
        }
        else if (i.alphaData != null) {
            final byte[] data2 = i.data;
            int ap = 0;
            for (int dp = 0; dp < i.data.length; dp += 4) {
                final int a = i.alphaData[ap] & 0xFF;
                int r2 = (data2[dp] & 0xFF) * a + 128;
                r2 = r2 + (r2 >> 8) >> 8;
                int g2 = (data2[dp + 1] & 0xFF) * a + 128;
                g2 = g2 + (g2 >> 8) >> 8;
                int b2 = (data2[dp + 2] & 0xFF) * a + 128;
                b2 = b2 + (b2 >> 8) >> 8;
                data2[dp] = (byte)r2;
                data2[dp + 1] = (byte)g2;
                data2[dp + 2] = (byte)b2;
                data2[dp + 3] = (byte)a;
                ++ap;
            }
        }
        final RGB[] rgbs2 = i.palette.getRGBs();
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biWidth = i.width;
        bmiHeader.biHeight = -i.height;
        bmiHeader.biPlanes = 1;
        bmiHeader.biBitCount = (short)i.depth;
        bmiHeader.biCompression = 0;
        bmiHeader.biClrUsed = ((rgbs2 == null) ? 0 : rgbs2.length);
        byte[] bmi;
        if (i.palette.isDirect) {
            bmi = new byte[BITMAPINFOHEADER.sizeof];
        }
        else {
            bmi = new byte[BITMAPINFOHEADER.sizeof + rgbs2.length * 4];
        }
        OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
        int offset = BITMAPINFOHEADER.sizeof;
        if (!i.palette.isDirect) {
            for (final RGB rgb2 : rgbs2) {
                bmi[offset] = (byte)rgb2.blue;
                bmi[offset + 1] = (byte)rgb2.green;
                bmi[offset + 2] = (byte)rgb2.red;
                bmi[offset + 3] = 0;
                offset += 4;
            }
        }
        final long[] pBits = { 0L };
        final long hDib = OS.CreateDIBSection(0L, bmi, 0, pBits, 0L, 0);
        if (hDib == 0L) {
            SWT.error(2);
        }
        byte[] data3 = i.data;
        if (i.scanlinePad != 4 && i.bytesPerLine % 4 != 0) {
            data3 = ImageData.convertPad(data3, i.width, i.height, i.depth, i.scanlinePad, 4);
        }
        OS.MoveMemory(pBits[0], data3, data3.length);
        long[] result = null;
        if (i.getTransparencyType() == 2) {
            final long hDC = device.internal_new_GC((GCData)null);
            final long hdcSrc = OS.CreateCompatibleDC(hDC);
            OS.SelectObject(hdcSrc, hDib);
            final long hBitmap = OS.CreateCompatibleBitmap(hDC, i.width, i.height);
            if (hBitmap == 0L) {
                SWT.error(2);
            }
            final long hdcDest = OS.CreateCompatibleDC(hDC);
            OS.SelectObject(hdcDest, hBitmap);
            OS.BitBlt(hdcDest, 0, 0, i.width, i.height, hdcSrc, 0, 0, 13369376);
            device.internal_dispose_GC(hDC, (GCData)null);
            final byte[] maskData = ImageData.convertPad(i.maskData, i.width, i.height, 1, i.maskPad, 2);
            final long hMask = OS.CreateBitmap(i.width, i.height, 1, 1, maskData);
            if (hMask == 0L) {
                SWT.error(2);
            }
            OS.SelectObject(hdcSrc, hMask);
            OS.PatBlt(hdcSrc, 0, 0, i.width, i.height, 5570569);
            OS.DeleteDC(hdcSrc);
            OS.DeleteDC(hdcDest);
            OS.DeleteObject(hDib);
            if (image == null) {
                result = new long[] { hBitmap, hMask };
            }
            else {
                final ICONINFO info = new ICONINFO();
                info.fIcon = true;
                info.hbmColor = hBitmap;
                info.hbmMask = hMask;
                final long hIcon = OS.CreateIconIndirect(info);
                if (hIcon == 0L) {
                    SWT.error(2);
                }
                OS.DeleteObject(hBitmap);
                OS.DeleteObject(hMask);
                image.handle = hIcon;
                image.type = 1;
            }
        }
        else if (image == null) {
            result = new long[] { hDib };
        }
        else {
            image.handle = hDib;
            image.type = 0;
            image.transparentPixel = i.transparentPixel;
        }
        return result;
    }
    
    static long[] init(final Device device, final Image image, final ImageData source, final ImageData mask) {
        int blackIndex = 0;
        ImageData imageData;
        if (source.palette.isDirect) {
            imageData = new ImageData(source.width, source.height, source.depth, source.palette);
        }
        else {
            final RGB black = new RGB(0, 0, 0);
            RGB[] rgbs = source.getRGBs();
            if (source.transparentPixel != -1) {
                final RGB[] newRGBs = new RGB[rgbs.length];
                System.arraycopy(rgbs, 0, newRGBs, 0, rgbs.length);
                if (source.transparentPixel >= newRGBs.length) {
                    rgbs = new RGB[source.transparentPixel + 1];
                    System.arraycopy(newRGBs, 0, rgbs, 0, newRGBs.length);
                    for (int i = newRGBs.length; i <= source.transparentPixel; ++i) {
                        rgbs[i] = new RGB(0, 0, 0);
                    }
                }
                else {
                    newRGBs[source.transparentPixel] = black;
                    rgbs = newRGBs;
                }
                blackIndex = source.transparentPixel;
                imageData = new ImageData(source.width, source.height, source.depth, new PaletteData(rgbs));
            }
            else {
                while (blackIndex < rgbs.length && !rgbs[blackIndex].equals(black)) {
                    ++blackIndex;
                }
                if (blackIndex == rgbs.length) {
                    if (1 << source.depth > rgbs.length) {
                        final RGB[] newRGBs = new RGB[rgbs.length + 1];
                        System.arraycopy(rgbs, 0, newRGBs, 0, rgbs.length);
                        newRGBs[rgbs.length] = black;
                        rgbs = newRGBs;
                    }
                    else {
                        blackIndex = -1;
                    }
                }
                imageData = new ImageData(source.width, source.height, source.depth, new PaletteData(rgbs));
            }
        }
        if (blackIndex == -1) {
            System.arraycopy(source.data, 0, imageData.data, 0, imageData.data.length);
        }
        else {
            final int[] imagePixels = new int[imageData.width];
            final int[] maskPixels = new int[mask.width];
            for (int y = 0; y < imageData.height; ++y) {
                source.getPixels(0, y, imageData.width, imagePixels, 0);
                mask.getPixels(0, y, mask.width, maskPixels, 0);
                for (int i = 0; i < imagePixels.length; ++i) {
                    if (maskPixels[i] == 0) {
                        imagePixels[i] = blackIndex;
                    }
                }
                imageData.setPixels(0, y, source.width, imagePixels, 0);
            }
        }
        imageData.maskPad = mask.scanlinePad;
        imageData.maskData = mask.data;
        return init(device, image, imageData);
    }
    
    void init(final ImageData i) {
        if (i == null) {
            SWT.error(4);
        }
        init(this.device, this, i);
    }
    
    public long internal_new_GC(final GCData data) {
        if (this.handle == 0L) {
            SWT.error(44);
        }
        if (this.type != 0 || this.memGC != null) {
            SWT.error(5);
        }
        final long hDC = this.device.internal_new_GC((GCData)null);
        final long imageDC = OS.CreateCompatibleDC(hDC);
        this.device.internal_dispose_GC(hDC, (GCData)null);
        if (imageDC == 0L) {
            SWT.error(2);
        }
        if (data != null) {
            final int mask = 100663296;
            if ((data.style & 0x6000000) != 0x0) {
                data.layout = (((data.style & 0x4000000) != 0x0) ? 1 : 0);
            }
            else {
                data.style |= 0x2000000;
            }
            data.device = this.device;
            data.image = this;
            data.font = this.device.systemFont;
        }
        return imageDC;
    }
    
    public void internal_dispose_GC(final long hDC, final GCData data) {
        OS.DeleteDC(hDC);
    }
    
    @Override
    public boolean isDisposed() {
        return this.handle == 0L;
    }
    
    public void setBackground(final Color color) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (color == null) {
            SWT.error(4);
        }
        if (color.isDisposed()) {
            SWT.error(5);
        }
        if (this.transparentPixel == -1) {
            return;
        }
        this.transparentColor = -1;
        final long hDC = this.device.internal_new_GC((GCData)null);
        final BITMAP bm = new BITMAP();
        OS.GetObject(this.handle, BITMAP.sizeof, bm);
        final long hdcMem = OS.CreateCompatibleDC(hDC);
        OS.SelectObject(hdcMem, this.handle);
        final int maxColors = 1 << bm.bmBitsPixel;
        final byte[] colors = new byte[maxColors * 4];
        final int numColors = OS.GetDIBColorTable(hdcMem, 0, maxColors, colors);
        final int offset = this.transparentPixel * 4;
        colors[offset] = (byte)color.getBlue();
        colors[offset + 1] = (byte)color.getGreen();
        colors[offset + 2] = (byte)color.getRed();
        OS.SetDIBColorTable(hdcMem, 0, numColors, colors);
        OS.DeleteDC(hdcMem);
        this.device.internal_dispose_GC(hDC, (GCData)null);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Image {*DISPOSED*}";
        }
        return "Image {" + this.handle;
    }
    
    public static Image win32_new(final Device device, final int type, final long handle) {
        final Image image = new Image(device);
        image.type = type;
        image.handle = handle;
        return image;
    }
}
