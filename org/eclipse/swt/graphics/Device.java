//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.gdip.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;

public abstract class Device implements Drawable
{
    public static boolean DEBUG;
    boolean debug;
    boolean tracking;
    Error[] errors;
    Object[] objects;
    Object trackingLock;
    Font systemFont;
    int nFonts;
    LOGFONT[] logFonts;
    TEXTMETRIC metrics;
    int[] pixels;
    long[] scripts;
    long[] gdipToken;
    long fontCollection;
    String[] loadedFonts;
    volatile boolean disposed;
    boolean enableAutoScaling;
    protected static Device CurrentDevice;
    protected static Runnable DeviceFinder;
    
    static synchronized Device getDevice() {
        if (Device.DeviceFinder != null) {
            Device.DeviceFinder.run();
        }
        final Device device = Device.CurrentDevice;
        Device.CurrentDevice = null;
        return device;
    }
    
    public Device() {
        this(null);
    }
    
    public Device(final DeviceData data) {
        this.debug = Device.DEBUG;
        this.tracking = Device.DEBUG;
        this.nFonts = 256;
        this.enableAutoScaling = true;
        synchronized (Device.class) {
            if (data != null) {
                this.debug = data.debug;
                this.tracking = data.tracking;
            }
            if (this.tracking) {
                this.startTracking();
            }
            this.create(data);
            this.init();
        }
    }
    
    public boolean isTracking() {
        this.checkDevice();
        return this.tracking;
    }
    
    public void setTracking(final boolean tracking) {
        this.checkDevice();
        if (tracking == this.tracking) {
            return;
        }
        this.tracking = tracking;
        if (tracking) {
            this.startTracking();
        }
        else {
            this.stopTracking();
        }
    }
    
    private void startTracking() {
        this.errors = new Error[128];
        this.objects = new Object[128];
        this.trackingLock = new Object();
    }
    
    private void stopTracking() {
        synchronized (this.trackingLock) {
            this.objects = null;
            this.errors = null;
            this.trackingLock = null;
        }
    }
    
    void addFont(final String font) {
        if (this.loadedFonts == null) {
            this.loadedFonts = new String[4];
        }
        final int length = this.loadedFonts.length;
        for (int i = 0; i < length; ++i) {
            if (font.equals(this.loadedFonts[i])) {
                return;
            }
        }
        int index;
        for (index = 0; index < length && this.loadedFonts[index] != null; ++index) {}
        if (index == length) {
            final String[] temp = new String[length + 4];
            System.arraycopy(this.loadedFonts, 0, temp, 0, length);
            this.loadedFonts = temp;
        }
        this.loadedFonts[index] = font;
    }
    
    protected void checkDevice() {
        if (this.disposed) {
            SWT.error(45);
        }
    }
    
    void checkGDIP() {
        if (this.gdipToken != null) {
            return;
        }
        final long[] token = { 0L };
        final GdiplusStartupInput input = new GdiplusStartupInput();
        input.GdiplusVersion = 1;
        if (Gdip.GdiplusStartup(token, input, 0L) != 0) {
            SWT.error(2);
        }
        this.gdipToken = token;
        if (this.loadedFonts != null) {
            this.fontCollection = Gdip.PrivateFontCollection_new();
            if (this.fontCollection == 0L) {
                SWT.error(2);
            }
            for (final String path : this.loadedFonts) {
                if (path == null) {
                    break;
                }
                final int length = path.length();
                final char[] buffer = new char[length + 1];
                path.getChars(0, length, buffer, 0);
                Gdip.PrivateFontCollection_AddFontFile(this.fontCollection, buffer);
            }
            this.loadedFonts = null;
        }
    }
    
    protected void create(final DeviceData data) {
    }
    
    int computePixels(final float height) {
        final long hDC = this.internal_new_GC(null);
        final int pixels = -(int)(0.5f + height * OS.GetDeviceCaps(hDC, 90) / 72.0f);
        this.internal_dispose_GC(hDC, null);
        return pixels;
    }
    
    float computePoints(final LOGFONT logFont, final long hFont) {
        final long hDC = this.internal_new_GC(null);
        final int logPixelsY = OS.GetDeviceCaps(hDC, 90);
        int pixels = 0;
        if (logFont.lfHeight > 0) {
            final long oldFont = OS.SelectObject(hDC, hFont);
            final TEXTMETRIC lptm = new TEXTMETRIC();
            OS.GetTextMetrics(hDC, lptm);
            OS.SelectObject(hDC, oldFont);
            pixels = logFont.lfHeight - lptm.tmInternalLeading;
        }
        else {
            pixels = -logFont.lfHeight;
        }
        this.internal_dispose_GC(hDC, null);
        return pixels * 72.0f / logPixelsY;
    }
    
    protected void destroy() {
    }
    
    public void dispose() {
        synchronized (Device.class) {
            try (final ExceptionStash exceptions = new ExceptionStash()) {
                if (this.isDisposed()) {
                    exceptions.close();
                    return;
                }
                this.checkDevice();
                try {
                    this.release();
                }
                catch (Error | RuntimeException error) {
                    final Throwable t2;
                    final Throwable ex = t2;
                    exceptions.stash(ex);
                }
                this.destroy();
                this.disposed = true;
                if (this.tracking) {
                    synchronized (this.trackingLock) {
                        this.printErrors();
                        this.objects = null;
                        this.errors = null;
                        this.trackingLock = null;
                    }
                }
            }
        }
    }
    
    void dispose_Object(final Object object) {
        synchronized (this.trackingLock) {
            for (int i = 0; i < this.objects.length; ++i) {
                if (this.objects[i] == object) {
                    this.objects[i] = null;
                    this.errors[i] = null;
                    return;
                }
            }
        }
    }
    
    long EnumFontFamProc(final long lpelfe, final long lpntme, final long FontType, final long lParam) {
        final boolean isScalable = ((int)FontType & 0x1) == 0x0;
        final boolean scalable = lParam == 1L;
        if (isScalable == scalable) {
            if (this.nFonts == this.logFonts.length) {
                final LOGFONT[] newLogFonts = new LOGFONT[this.logFonts.length + 128];
                System.arraycopy(this.logFonts, 0, newLogFonts, 0, this.nFonts);
                this.logFonts = newLogFonts;
                final int[] newPixels = new int[newLogFonts.length];
                System.arraycopy(this.pixels, 0, newPixels, 0, this.nFonts);
                this.pixels = newPixels;
            }
            LOGFONT logFont = this.logFonts[this.nFonts];
            if (logFont == null) {
                logFont = new LOGFONT();
            }
            OS.MoveMemory(logFont, lpelfe, LOGFONT.sizeof);
            this.logFonts[this.nFonts] = logFont;
            if (logFont.lfHeight > 0) {
                OS.MoveMemory(this.metrics, lpntme, TEXTMETRIC.sizeof);
                this.pixels[this.nFonts] = logFont.lfHeight - this.metrics.tmInternalLeading;
            }
            else {
                this.pixels[this.nFonts] = -logFont.lfHeight;
            }
            ++this.nFonts;
        }
        return 1L;
    }
    
    public Rectangle getBounds() {
        this.checkDevice();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    private Rectangle getBoundsInPixels() {
        final long hDC = this.internal_new_GC(null);
        final int width = OS.GetDeviceCaps(hDC, 8);
        final int height = OS.GetDeviceCaps(hDC, 10);
        this.internal_dispose_GC(hDC, null);
        return new Rectangle(0, 0, width, height);
    }
    
    public DeviceData getDeviceData() {
        this.checkDevice();
        final DeviceData data = new DeviceData();
        data.debug = this.debug;
        data.tracking = this.tracking;
        if (this.tracking) {
            synchronized (this.trackingLock) {
                int count = 0;
                final int length = this.objects.length;
                for (int i = 0; i < length; ++i) {
                    if (this.objects[i] != null) {
                        ++count;
                    }
                }
                int index = 0;
                data.objects = new Object[count];
                data.errors = new Error[count];
                for (int j = 0; j < length; ++j) {
                    if (this.objects[j] != null) {
                        data.objects[index] = this.objects[j];
                        data.errors[index] = this.errors[j];
                        ++index;
                    }
                }
            }
        }
        else {
            data.objects = new Object[0];
            data.errors = new Error[0];
        }
        return data;
    }
    
    public Rectangle getClientArea() {
        return this.getBounds();
    }
    
    public int getDepth() {
        this.checkDevice();
        final long hDC = this.internal_new_GC(null);
        final int bits = OS.GetDeviceCaps(hDC, 12);
        final int planes = OS.GetDeviceCaps(hDC, 14);
        this.internal_dispose_GC(hDC, null);
        return bits * planes;
    }
    
    public Point getDPI() {
        this.checkDevice();
        final long hDC = this.internal_new_GC(null);
        final int dpiX = OS.GetDeviceCaps(hDC, 88);
        final int dpiY = OS.GetDeviceCaps(hDC, 90);
        this.internal_dispose_GC(hDC, null);
        return DPIUtil.autoScaleDown(new Point(dpiX, dpiY));
    }
    
    int _getDPIx() {
        final long hDC = this.internal_new_GC(null);
        final int dpi = OS.GetDeviceCaps(hDC, 88);
        this.internal_dispose_GC(hDC, null);
        return dpi;
    }
    
    public FontData[] getFontList(final String faceName, final boolean scalable) {
        this.checkDevice();
        final Callback callback = new Callback(this, "EnumFontFamProc", 4);
        final long lpEnumFontFamProc = callback.getAddress();
        this.metrics = new TEXTMETRIC();
        this.pixels = new int[this.nFonts];
        this.logFonts = new LOGFONT[this.nFonts];
        for (int i = 0; i < this.logFonts.length; ++i) {
            this.logFonts[i] = new LOGFONT();
        }
        this.nFonts = 0;
        int offset = 0;
        final long hDC = this.internal_new_GC(null);
        if (faceName == null) {
            OS.EnumFontFamilies(hDC, null, lpEnumFontFamProc, scalable ? 1 : 0);
            offset = this.nFonts;
            for (int j = 0; j < offset; ++j) {
                final LOGFONT lf = this.logFonts[j];
                OS.EnumFontFamilies(hDC, lf.lfFaceName, lpEnumFontFamProc, scalable ? 1 : 0);
            }
        }
        else {
            final TCHAR lpFaceName = new TCHAR(0, faceName, true);
            OS.EnumFontFamilies(hDC, lpFaceName.chars, lpEnumFontFamProc, scalable ? 1 : 0);
        }
        final int logPixelsY = OS.GetDeviceCaps(hDC, 90);
        this.internal_dispose_GC(hDC, null);
        int count = 0;
        FontData[] result = new FontData[this.nFonts - offset];
        for (int k = offset; k < this.nFonts; ++k) {
            FontData fd;
            int l;
            for (fd = FontData.win32_new(this.logFonts[k], this.pixels[k] * 72.0f / logPixelsY), l = 0; l < count && !fd.equals(result[l]); ++l) {}
            if (l == count) {
                result[count++] = fd;
            }
        }
        if (count != result.length) {
            final FontData[] newResult = new FontData[count];
            System.arraycopy(result, 0, newResult, 0, count);
            result = newResult;
        }
        callback.dispose();
        this.logFonts = null;
        this.pixels = null;
        this.metrics = null;
        return result;
    }
    
    String getLastError() {
        final int error = OS.GetLastError();
        if (error == 0) {
            return "";
        }
        return " [GetLastError=0x" + Integer.toHexString(error);
    }
    
    public Color getSystemColor(final int id) {
        this.checkDevice();
        int pixel = 0;
        int alpha = 255;
        switch (id) {
            case 37: {
                alpha = 0;
            }
            case 1: {
                pixel = 16777215;
                break;
            }
            case 2: {
                pixel = 0;
                break;
            }
            case 3: {
                pixel = 255;
                break;
            }
            case 4: {
                pixel = 128;
                break;
            }
            case 5: {
                pixel = 65280;
                break;
            }
            case 6: {
                pixel = 32768;
                break;
            }
            case 7: {
                pixel = 65535;
                break;
            }
            case 8: {
                pixel = 32896;
                break;
            }
            case 9: {
                pixel = 16711680;
                break;
            }
            case 10: {
                pixel = 8388608;
                break;
            }
            case 11: {
                pixel = 16711935;
                break;
            }
            case 12: {
                pixel = 8388736;
                break;
            }
            case 13: {
                pixel = 16776960;
                break;
            }
            case 14: {
                pixel = 8421376;
                break;
            }
            case 15: {
                pixel = 12632256;
                break;
            }
            case 16: {
                pixel = 8421504;
                break;
            }
        }
        return Color.win32_new(this, pixel, alpha);
    }
    
    public Font getSystemFont() {
        this.checkDevice();
        final long hFont = OS.GetStockObject(13);
        return Font.win32_new(this, hFont);
    }
    
    public boolean getWarnings() {
        this.checkDevice();
        return false;
    }
    
    protected void init() {
        if (this.debug) {
            OS.GdiSetBatchLimit(1);
        }
        this.systemFont = this.getSystemFont();
        final long[] ppSp = { 0L };
        final int[] piNumScripts = { 0 };
        OS.ScriptGetProperties(ppSp, piNumScripts);
        OS.MoveMemory(this.scripts = new long[piNumScripts[0]], ppSp[0], this.scripts.length * C.PTR_SIZEOF);
    }
    
    @Override
    public abstract long internal_new_GC(final GCData p0);
    
    @Override
    public abstract void internal_dispose_GC(final long p0, final GCData p1);
    
    public boolean isDisposed() {
        return this.disposed;
    }
    
    public boolean loadFont(final String path) {
        this.checkDevice();
        if (path == null) {
            SWT.error(4);
        }
        final TCHAR lpszFilename = new TCHAR(0, path, true);
        final boolean loaded = OS.AddFontResourceEx(lpszFilename, 16, 0L) != 0L;
        if (loaded) {
            if (this.gdipToken != null) {
                if (this.fontCollection == 0L) {
                    this.fontCollection = Gdip.PrivateFontCollection_new();
                    if (this.fontCollection == 0L) {
                        SWT.error(2);
                    }
                }
                final int length = path.length();
                final char[] buffer = new char[length + 1];
                path.getChars(0, length, buffer, 0);
                Gdip.PrivateFontCollection_AddFontFile(this.fontCollection, buffer);
            }
            else {
                this.addFont(path);
            }
        }
        return loaded;
    }
    
    void new_Object(final Object object) {
        synchronized (this.trackingLock) {
            for (int i = 0; i < this.objects.length; ++i) {
                if (this.objects[i] == null) {
                    this.objects[i] = object;
                    this.errors[i] = new Error();
                    return;
                }
            }
            final Object[] newObjects = new Object[this.objects.length + 128];
            System.arraycopy(this.objects, 0, newObjects, 0, this.objects.length);
            newObjects[this.objects.length] = object;
            this.objects = newObjects;
            final Error[] newErrors = new Error[this.errors.length + 128];
            System.arraycopy(this.errors, 0, newErrors, 0, this.errors.length);
            newErrors[this.errors.length] = new Error();
            this.errors = newErrors;
        }
    }
    
    void printErrors() {
        if (!Device.DEBUG) {
            return;
        }
        if (this.tracking) {
            synchronized (this.trackingLock) {
                if (this.objects == null || this.errors == null) {
                    return;
                }
                int objectCount = 0;
                int colors = 0;
                int cursors = 0;
                int fonts = 0;
                int gcs = 0;
                int images = 0;
                int paths = 0;
                int patterns = 0;
                int regions = 0;
                int textLayouts = 0;
                int transforms = 0;
                for (final Object object : this.objects) {
                    if (object != null) {
                        ++objectCount;
                        if (object instanceof Color) {
                            ++colors;
                        }
                        if (object instanceof Cursor) {
                            ++cursors;
                        }
                        if (object instanceof Font) {
                            ++fonts;
                        }
                        if (object instanceof GC) {
                            ++gcs;
                        }
                        if (object instanceof Image) {
                            ++images;
                        }
                        if (object instanceof Path) {
                            ++paths;
                        }
                        if (object instanceof Pattern) {
                            ++patterns;
                        }
                        if (object instanceof Region) {
                            ++regions;
                        }
                        if (object instanceof TextLayout) {
                            ++textLayouts;
                        }
                        if (object instanceof Transform) {
                            ++transforms;
                        }
                    }
                }
                if (objectCount != 0) {
                    String string = "Summary: ";
                    if (colors != 0) {
                        string = string + colors + " Color(s), ";
                    }
                    if (cursors != 0) {
                        string = string + cursors + " Cursor(s), ";
                    }
                    if (fonts != 0) {
                        string = string + fonts + " Font(s), ";
                    }
                    if (gcs != 0) {
                        string = string + gcs + " GC(s), ";
                    }
                    if (images != 0) {
                        string = string + images + " Image(s), ";
                    }
                    if (paths != 0) {
                        string = string + paths + " Path(s), ";
                    }
                    if (patterns != 0) {
                        string = string + patterns + " Pattern(s), ";
                    }
                    if (regions != 0) {
                        string = string + regions + " Region(s), ";
                    }
                    if (textLayouts != 0) {
                        string = string + textLayouts + " TextLayout(s), ";
                    }
                    if (transforms != 0) {
                        string = string + transforms + " Transforms(s), ";
                    }
                    if (string.length() != 0) {
                        string = string.substring(0, string.length() - 2);
                        System.err.println(string);
                    }
                    for (final Error error : this.errors) {
                        if (error != null) {
                            error.printStackTrace(System.err);
                        }
                    }
                }
            }
        }
    }
    
    protected void release() {
        if (this.gdipToken != null) {
            if (this.fontCollection != 0L) {
                Gdip.PrivateFontCollection_delete(this.fontCollection);
            }
            this.fontCollection = 0L;
            Gdip.GdiplusShutdown(this.gdipToken[0]);
        }
        this.gdipToken = null;
        this.scripts = null;
        this.logFonts = null;
        this.nFonts = 0;
    }
    
    public void setWarnings(final boolean warnings) {
        this.checkDevice();
    }
    
    boolean getEnableAutoScaling() {
        return this.enableAutoScaling;
    }
    
    void setEnableAutoScaling(final boolean value) {
        this.enableAutoScaling = value;
    }
    
    protected int getDeviceZoom() {
        return DPIUtil.mapDPIToZoom(this._getDPIx());
    }
    
    static {
        try {
            Class.forName("org.eclipse.swt.widgets.Display");
        }
        catch (ClassNotFoundException ex) {}
    }
}
