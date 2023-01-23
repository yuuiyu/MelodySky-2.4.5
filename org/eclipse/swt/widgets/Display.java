//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import java.util.function.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.ole.win32.*;
import java.net.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import java.util.*;
import org.eclipse.swt.*;

public class Display extends Device
{
    public MSG msg;
    static String APP_NAME;
    static String APP_VERSION;
    String appLocalDir;
    Event[] eventQueue;
    Callback windowCallback;
    long windowProc;
    int threadId;
    TCHAR windowClass;
    TCHAR windowShadowClass;
    TCHAR windowOwnDCClass;
    static int WindowClassCount;
    static final String WindowName = "SWT_Window";
    static final String WindowShadowName = "SWT_WindowShadow";
    static final String WindowOwnDCName = "SWT_WindowOwnDC";
    EventTable eventTable;
    EventTable filterTable;
    boolean useOwnDC;
    boolean externalEventLoop;
    int freeSlot;
    int[] indexTable;
    Control lastControl;
    Control lastGetControl;
    long lastHwnd;
    long lastGetHwnd;
    Control[] controlTable;
    static final int GROW_SIZE = 1024;
    static final int SWT_OBJECT_INDEX;
    static STARTUPINFO lpStartupInfo;
    long hButtonTheme;
    long hButtonThemeDark;
    long hEditTheme;
    long hExplorerBarTheme;
    long hScrollBarTheme;
    long hTabTheme;
    static final char[] EXPLORER;
    static final char[] TREEVIEW;
    static final boolean disableCustomThemeTweaks;
    static final String USE_DARKMODE_EXPLORER_THEME_KEY = "org.eclipse.swt.internal.win32.useDarkModeExplorerTheme";
    boolean useDarkModeExplorerTheme;
    static final String USE_SHELL_TITLE_COLORING = "org.eclipse.swt.internal.win32.useShellTitleColoring";
    boolean useShellTitleColoring;
    static final String MENUBAR_FOREGROUND_COLOR_KEY = "org.eclipse.swt.internal.win32.menuBarForegroundColor";
    int menuBarForegroundPixel;
    static final String MENUBAR_BACKGROUND_COLOR_KEY = "org.eclipse.swt.internal.win32.menuBarBackgroundColor";
    int menuBarBackgroundPixel;
    static final String MENUBAR_BORDER_COLOR_KEY = "org.eclipse.swt.internal.win32.menuBarBorderColor";
    long menuBarBorderPen;
    static final String USE_WS_BORDER_ALL_KEY = "org.eclipse.swt.internal.win32.all.use_WS_BORDER";
    boolean useWsBorderAll;
    static final String USE_WS_BORDER_CANVAS_KEY = "org.eclipse.swt.internal.win32.Canvas.use_WS_BORDER";
    boolean useWsBorderCanvas;
    static final String USE_WS_BORDER_LABEL_KEY = "org.eclipse.swt.internal.win32.Label.use_WS_BORDER";
    boolean useWsBorderLabel;
    static final String USE_WS_BORDER_LIST_KEY = "org.eclipse.swt.internal.win32.List.use_WS_BORDER";
    boolean useWsBorderList;
    static final String USE_WS_BORDER_TABLE_KEY = "org.eclipse.swt.internal.win32.Table.use_WS_BORDER";
    boolean useWsBorderTable;
    static final String USE_WS_BORDER_TEXT_KEY = "org.eclipse.swt.internal.win32.Text.use_WS_BORDER";
    boolean useWsBorderText;
    static final String TABLE_HEADER_LINE_COLOR_KEY = "org.eclipse.swt.internal.win32.Table.headerLineColor";
    int tableHeaderLinePixel;
    static final String LABEL_DISABLED_FOREGROUND_COLOR_KEY = "org.eclipse.swt.internal.win32.Label.disabledForegroundColor";
    int disabledLabelForegroundPixel;
    static final String COMBO_USE_DARK_THEME = "org.eclipse.swt.internal.win32.Combo.useDarkTheme";
    boolean comboUseDarkTheme;
    static final String PROGRESSBAR_USE_COLORS = "org.eclipse.swt.internal.win32.ProgressBar.useColors";
    boolean progressbarUseColors;
    static final String USE_DARKTHEME_TEXT_ICONS = "org.eclipse.swt.internal.win32.Text.useDarkThemeIcons";
    boolean textUseDarkthemeIcons;
    long hIconSearch;
    long hIconCancel;
    int focusEvent;
    Control focusControl;
    Menu[] bars;
    Menu[] popups;
    MenuItem[] items;
    static final int ID_START = 108;
    Callback msgFilterCallback;
    long msgFilterProc;
    long filterHook;
    MSG hookMsg;
    boolean runDragDrop;
    boolean dragCancelled;
    Callback foregroundIdleCallback;
    long foregroundIdleProc;
    long idleHook;
    boolean ignoreNextKey;
    Callback getMsgCallback;
    Callback embeddedCallback;
    long getMsgProc;
    long msgHook;
    long embeddedHwnd;
    long embeddedProc;
    static final String AWT_WINDOW_CLASS = "SunAwtWindow";
    static final short[] ACCENTS;
    Synchronizer synchronizer;
    Consumer<RuntimeException> runtimeExceptionHandler;
    Consumer<Error> errorHandler;
    boolean runMessagesInIdle;
    boolean runMessagesInMessageProc;
    static final String RUN_MESSAGES_IN_IDLE_KEY = "org.eclipse.swt.internal.win32.runMessagesInIdle";
    static final String RUN_MESSAGES_IN_MESSAGE_PROC_KEY = "org.eclipse.swt.internal.win32.runMessagesInMessageProc";
    static final String USE_OWNDC_KEY = "org.eclipse.swt.internal.win32.useOwnDC";
    static final String ACCEL_KEY_HIT = "org.eclipse.swt.internal.win32.accelKeyHit";
    static final String EXTERNAL_EVENT_LOOP_KEY = "org.eclipse.swt.internal.win32.externalEventLoop";
    static final String APPLOCAL_DIR_KEY = "org.eclipse.swt.internal.win32.appLocalDir";
    Thread thread;
    Runnable[] disposeList;
    Composite[] layoutDeferred;
    int layoutDeferredCount;
    Tray tray;
    int nextTrayId;
    TaskBar taskBar;
    static final String TASKBAR_EVENT = "/SWTINTERNAL_ID";
    static final String LAUNCHER_PREFIX = "--launcher.openFile ";
    long[] timerIds;
    Runnable[] timerList;
    long nextTimerId;
    static final long SETTINGS_ID = 100L;
    static final int SETTINGS_DELAY = 2000;
    RECT clickRect;
    int clickCount;
    int lastTime;
    int lastButton;
    long lastClickHwnd;
    Point scrollRemainderEvt;
    Point scrollRemainderBar;
    int lastKey;
    int lastMouse;
    int lastAscii;
    boolean lastVirtual;
    boolean lastNull;
    boolean lastDead;
    byte[] keyboard;
    boolean accelKeyHit;
    boolean mnemonicKeyHit;
    boolean lockActiveWindow;
    boolean captureChanged;
    boolean xMouse;
    double magStartDistance;
    double lastDistance;
    double rotationAngle;
    int lastX;
    int lastY;
    TouchSource[] touchSources;
    int nextToolTipId;
    boolean ignoreRestoreFocus;
    Control lastHittestControl;
    int lastHittest;
    Callback messageCallback;
    long hwndMessage;
    long messageProc;
    LOGFONT lfSystemFont;
    Font systemFont;
    Image errorImage;
    Image infoImage;
    Image questionImage;
    Image warningIcon;
    Cursor[] cursors;
    Resource[] resources;
    static final int RESOURCE_SIZE = 27;
    ImageList[] imageList;
    ImageList[] toolImageList;
    ImageList[] toolHotImageList;
    ImageList[] toolDisabledImageList;
    long lpCustColors;
    char[] tableBuffer;
    int resizeCount;
    static final int RESIZE_LIMIT = 4;
    Object data;
    String[] keys;
    Object[] values;
    static final int[][] KeyTable;
    static Display Default;
    static Display[] Displays;
    Monitor[] monitors;
    int monitorCount;
    Shell[] modalShells;
    Dialog modalDialog;
    static boolean TrimEnabled;
    static final int SWT_GETACCELCOUNT = 32768;
    static final int SWT_GETACCEL = 32769;
    static final int SWT_KEYMSG = 32770;
    static final int SWT_DESTROY = 32771;
    static final int SWT_TRAYICONMSG = 32772;
    static final int SWT_NULL = 32773;
    static final int SWT_RUNASYNC = 32774;
    static int TASKBARCREATED;
    static int TASKBARBUTTONCREATED;
    static int SWT_RESTORECARET;
    static int DI_GETDRAGIMAGE;
    static int SWT_OPENDOC;
    Widget[] skinList;
    int skinCount;
    static final String PACKAGE_PREFIX = "org.eclipse.swt.widgets.";
    
    static void setDevice(final Device device) {
        Display.CurrentDevice = device;
    }
    
    public Display() {
        this(null);
    }
    
    public Display(final DeviceData data) {
        super(data);
        this.msg = new MSG();
        this.menuBarForegroundPixel = -1;
        this.menuBarBackgroundPixel = -1;
        this.useWsBorderAll = false;
        this.useWsBorderCanvas = false;
        this.useWsBorderLabel = false;
        this.useWsBorderList = false;
        this.useWsBorderTable = false;
        this.useWsBorderText = false;
        this.tableHeaderLinePixel = -1;
        this.disabledLabelForegroundPixel = -1;
        this.comboUseDarkTheme = false;
        this.progressbarUseColors = false;
        this.textUseDarkthemeIcons = false;
        this.hookMsg = new MSG();
        this.runDragDrop = true;
        this.dragCancelled = false;
        this.runtimeExceptionHandler = (Consumer<RuntimeException>)DefaultExceptionHandler.RUNTIME_EXCEPTION_HANDLER;
        this.errorHandler = (Consumer<Error>)DefaultExceptionHandler.RUNTIME_ERROR_HANDLER;
        this.runMessagesInIdle = false;
        this.runMessagesInMessageProc = true;
        this.nextTimerId = 101L;
        this.scrollRemainderEvt = new Point(0, 0);
        this.scrollRemainderBar = new Point(0, 0);
        this.keyboard = new byte[256];
        this.cursors = new Cursor[22];
        this.monitors = null;
        this.monitorCount = 0;
        this.skinList = new Widget[1024];
    }
    
    Control _getFocusControl() {
        return this.findControl(OS.GetFocus());
    }
    
    void addBar(final Menu menu) {
        if (this.bars == null) {
            this.bars = new Menu[4];
        }
        final int length = this.bars.length;
        for (int i = 0; i < length; ++i) {
            if (this.bars[i] == menu) {
                return;
            }
        }
        int index;
        for (index = 0; index < length && this.bars[index] != null; ++index) {}
        if (index == length) {
            final Menu[] newBars = new Menu[length + 4];
            System.arraycopy(this.bars, 0, newBars, 0, length);
            this.bars = newBars;
        }
        this.bars[index] = menu;
    }
    
    void addControl(final long handle, final Control control) {
        if (handle == 0L) {
            return;
        }
        if (this.freeSlot == -1) {
            final int length2 = this.indexTable.length;
            this.freeSlot = length2;
            final int length3 = length2 + 1024;
            final int[] newIndexTable = new int[length3];
            final Control[] newControlTable = new Control[length3];
            System.arraycopy(this.indexTable, 0, newIndexTable, 0, this.freeSlot);
            System.arraycopy(this.controlTable, 0, newControlTable, 0, this.freeSlot);
            for (int i = this.freeSlot; i < length3 - 1; ++i) {
                newIndexTable[i] = i + 1;
            }
            newIndexTable[length3 - 1] = -1;
            this.indexTable = newIndexTable;
            this.controlTable = newControlTable;
        }
        OS.SetProp(handle, (long)Display.SWT_OBJECT_INDEX, (long)(this.freeSlot + 1));
        final int oldSlot = this.freeSlot;
        this.freeSlot = this.indexTable[oldSlot];
        this.indexTable[oldSlot] = -2;
        this.controlTable[oldSlot] = control;
    }
    
    void addSkinnableWidget(final Widget widget) {
        if (this.skinCount >= this.skinList.length) {
            final Widget[] newSkinWidgets = new Widget[(this.skinList.length + 1) * 3 / 2];
            System.arraycopy(this.skinList, 0, newSkinWidgets, 0, this.skinList.length);
            this.skinList = newSkinWidgets;
        }
        this.skinList[this.skinCount++] = widget;
    }
    
    public void addFilter(final int eventType, final Listener listener) {
        this.checkDevice();
        if (listener == null) {
            this.error(4);
        }
        if (this.filterTable == null) {
            this.filterTable = new EventTable();
        }
        this.filterTable.hook(eventType, listener);
    }
    
    void addLayoutDeferred(final Composite comp) {
        if (this.layoutDeferred == null) {
            this.layoutDeferred = new Composite[64];
        }
        if (this.layoutDeferredCount == this.layoutDeferred.length) {
            final Composite[] temp = new Composite[this.layoutDeferred.length + 64];
            System.arraycopy(this.layoutDeferred, 0, temp, 0, this.layoutDeferred.length);
            this.layoutDeferred = temp;
        }
        this.layoutDeferred[this.layoutDeferredCount++] = comp;
    }
    
    public void addListener(final int eventType, final Listener listener) {
        this.checkDevice();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            this.eventTable = new EventTable();
        }
        this.eventTable.hook(eventType, listener);
    }
    
    void addMenuItem(final MenuItem item) {
        if (this.items == null) {
            this.items = new MenuItem[64];
        }
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == null) {
                item.id = i + 108;
                this.items[i] = item;
                return;
            }
        }
        item.id = this.items.length + 108;
        final MenuItem[] newItems = new MenuItem[this.items.length + 64];
        newItems[this.items.length] = item;
        System.arraycopy(this.items, 0, newItems, 0, this.items.length);
        this.items = newItems;
    }
    
    void addPopup(final Menu menu) {
        if (this.popups == null) {
            this.popups = new Menu[4];
        }
        final int length = this.popups.length;
        for (int i = 0; i < length; ++i) {
            if (this.popups[i] == menu) {
                return;
            }
        }
        int index;
        for (index = 0; index < length && this.popups[index] != null; ++index) {}
        if (index == length) {
            final Menu[] newPopups = new Menu[length + 4];
            System.arraycopy(this.popups, 0, newPopups, 0, length);
            this.popups = newPopups;
        }
        this.popups[index] = menu;
    }
    
    int asciiKey(final int key) {
        for (int i = 0; i < this.keyboard.length; ++i) {
            this.keyboard[i] = 0;
        }
        if (!OS.GetKeyboardState(this.keyboard)) {
            return 0;
        }
        char[] buffer;
        int len;
        for (buffer = new char[] { '\0' }, len = OS.ToUnicode(key, key, this.keyboard, buffer, 1, 0); len == -1; len = OS.ToUnicode(key, key, this.keyboard, buffer, 1, 0)) {}
        return (len != 0) ? buffer[0] : '\0';
    }
    
    public void asyncExec(final Runnable runnable) {
        synchronized (Device.class) {
            if (this.isDisposed()) {
                this.error(45);
            }
            this.synchronizer.asyncExec(runnable);
        }
    }
    
    public void beep() {
        this.checkDevice();
        OS.MessageBeep(0);
    }
    
    protected void checkSubclass() {
        if (!isValidClass(this.getClass())) {
            this.error(43);
        }
    }
    
    protected void checkDevice() {
        if (this.thread == null) {
            this.error(24);
        }
        if (this.thread != Thread.currentThread()) {
            this.error(22);
        }
        if (this.isDisposed()) {
            this.error(45);
        }
    }
    
    static void checkDisplay(final Thread thread, final boolean multiple) {
        synchronized (Device.class) {
            for (final Display display : Display.Displays) {
                if (display != null) {
                    if (!multiple) {
                        SWT.error(20, (Throwable)null, " [multiple displays]");
                    }
                    if (display.thread == thread) {
                        SWT.error(22);
                    }
                }
            }
        }
    }
    
    void clearModal(final Shell shell) {
        if (this.modalShells == null) {
            return;
        }
        int index;
        int length;
        for (index = 0, length = this.modalShells.length; index < length && this.modalShells[index] != shell; ++index) {
            if (this.modalShells[index] == null) {
                return;
            }
        }
        if (index == length) {
            return;
        }
        System.arraycopy(this.modalShells, index + 1, this.modalShells, index, --length - index);
        this.modalShells[length] = null;
        if (index == 0 && this.modalShells[0] == null) {
            this.modalShells = null;
        }
        for (final Shell activeShell : this.getShells()) {
            activeShell.updateModal();
        }
    }
    
    int controlKey(final int key) {
        final int upper = (int)OS.CharUpper((long)(short)key);
        if (64 <= upper && upper <= 95) {
            return upper & 0xBF;
        }
        return key;
    }
    
    public void close() {
        this.checkDevice();
        final Event event = new Event();
        this.sendEvent(21, event);
        if (event.doit) {
            this.dispose();
        }
    }
    
    protected void create(final DeviceData data) {
        this.checkSubclass();
        checkDisplay(this.thread = Thread.currentThread(), true);
        this.createDisplay(data);
        register(this);
        if (Display.Default == null) {
            Display.Default = this;
        }
    }
    
    void createDisplay(final DeviceData data) {
    }
    
    static long create32bitDIB(final Image image) {
        int transparentPixel = -1;
        int alpha = -1;
        long hMask = 0L;
        long hBitmap = 0L;
        byte[] alphaData = null;
        switch (image.type) {
            case 1: {
                final ICONINFO info = new ICONINFO();
                OS.GetIconInfo(image.handle, info);
                hBitmap = info.hbmColor;
                hMask = info.hbmMask;
                break;
            }
            case 0: {
                final ImageData data = image.getImageData(DPIUtil.getDeviceZoom());
                hBitmap = image.handle;
                alpha = data.alpha;
                alphaData = data.alphaData;
                transparentPixel = data.transparentPixel;
                break;
            }
        }
        final BITMAP bm = new BITMAP();
        OS.GetObject(hBitmap, BITMAP.sizeof, bm);
        final int imgWidth = bm.bmWidth;
        final int imgHeight = bm.bmHeight;
        final long hDC = OS.GetDC(0L);
        final long srcHdc = OS.CreateCompatibleDC(hDC);
        final long oldSrcBitmap = OS.SelectObject(srcHdc, hBitmap);
        final long memHdc = OS.CreateCompatibleDC(hDC);
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biWidth = imgWidth;
        bmiHeader.biHeight = -imgHeight;
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
        OS.BitBlt(memHdc, 0, 0, imgWidth, imgHeight, srcHdc, 0, 0, 13369376);
        byte red = 0;
        byte green = 0;
        byte blue = 0;
        if (transparentPixel != -1) {
            if (bm.bmBitsPixel <= 8) {
                final byte[] color = new byte[4];
                OS.GetDIBColorTable(srcHdc, transparentPixel, 1, color);
                blue = color[0];
                green = color[1];
                red = color[2];
            }
            else {
                switch (bm.bmBitsPixel) {
                    case 16: {
                        blue = (byte)((transparentPixel & 0x1F) << 3);
                        green = (byte)((transparentPixel & 0x3E0) >> 2);
                        red = (byte)((transparentPixel & 0x7C00) >> 7);
                        break;
                    }
                    case 24: {
                        blue = (byte)((transparentPixel & 0xFF0000) >> 16);
                        green = (byte)((transparentPixel & 0xFF00) >> 8);
                        red = (byte)(transparentPixel & 0xFF);
                        break;
                    }
                    case 32: {
                        blue = (byte)((transparentPixel & 0xFF000000) >>> 24);
                        green = (byte)((transparentPixel & 0xFF0000) >> 16);
                        red = (byte)((transparentPixel & 0xFF00) >> 8);
                        break;
                    }
                }
            }
        }
        final byte[] srcData = new byte[sizeInBytes];
        OS.MoveMemory(srcData, pBits[0], sizeInBytes);
        if (hMask != 0L) {
            OS.SelectObject(srcHdc, hMask);
            int y = 0;
            int dp = 0;
            while (y < imgHeight) {
                for (int x = 0; x < imgWidth; ++x) {
                    if (OS.GetPixel(srcHdc, x, y) != 0) {
                        final byte[] array = srcData;
                        final int n = dp + 0;
                        final byte[] array2 = srcData;
                        final int n2 = dp + 1;
                        final byte[] array3 = srcData;
                        final int n3 = dp + 2;
                        final byte[] array4 = srcData;
                        final int n4 = dp + 3;
                        final byte b = 0;
                        array3[n3] = (array4[n4] = 0);
                        array[n] = (array2[n2] = 0);
                    }
                    else {
                        srcData[dp + 3] = -1;
                    }
                    dp += 4;
                }
                ++y;
            }
        }
        else if (transparentPixel != -1) {
            int y = 0;
            int dp = 0;
            while (y < imgHeight) {
                for (int x = 0; x < imgWidth; ++x) {
                    if (srcData[dp] == blue && srcData[dp + 1] == green && srcData[dp + 2] == red) {
                        final byte[] array5 = srcData;
                        final int n5 = dp + 0;
                        final byte[] array6 = srcData;
                        final int n6 = dp + 1;
                        final byte[] array7 = srcData;
                        final int n7 = dp + 2;
                        final byte[] array8 = srcData;
                        final int n8 = dp + 3;
                        final byte b2 = 0;
                        array7[n7] = (array8[n8] = 0);
                        array5[n5] = (array6[n6] = 0);
                    }
                    else {
                        srcData[dp + 3] = -1;
                    }
                    dp += 4;
                }
                ++y;
            }
        }
        else if (alpha == -1 && alphaData == null) {
            int y = 0;
            int dp = 0;
            while (y < imgHeight) {
                for (int x = 0; x < imgWidth; ++x) {
                    srcData[dp + 3] = -1;
                    dp += 4;
                }
                ++y;
            }
        }
        OS.MoveMemory(pBits[0], srcData, sizeInBytes);
        OS.SelectObject(srcHdc, oldSrcBitmap);
        OS.SelectObject(memHdc, oldMemBitmap);
        OS.DeleteObject(srcHdc);
        OS.DeleteObject(memHdc);
        OS.ReleaseDC(0L, hDC);
        if (hBitmap != image.handle && hBitmap != 0L) {
            OS.DeleteObject(hBitmap);
        }
        if (hMask != 0L) {
            OS.DeleteObject(hMask);
        }
        return memDib;
    }
    
    static long create32bitDIB(final long hBitmap, final int alpha, final byte[] alphaData, final int transparentPixel) {
        final BITMAP bm = new BITMAP();
        OS.GetObject(hBitmap, BITMAP.sizeof, bm);
        final int imgWidth = bm.bmWidth;
        final int imgHeight = bm.bmHeight;
        final long hDC = OS.GetDC(0L);
        final long srcHdc = OS.CreateCompatibleDC(hDC);
        final long oldSrcBitmap = OS.SelectObject(srcHdc, hBitmap);
        final long memHdc = OS.CreateCompatibleDC(hDC);
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biWidth = imgWidth;
        bmiHeader.biHeight = -imgHeight;
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
        OS.BitBlt(memHdc, 0, 0, imgWidth, imgHeight, srcHdc, 0, 0, 13369376);
        byte red = 0;
        byte green = 0;
        byte blue = 0;
        if (transparentPixel != -1) {
            if (bm.bmBitsPixel <= 8) {
                final byte[] color = new byte[4];
                OS.GetDIBColorTable(srcHdc, transparentPixel, 1, color);
                blue = color[0];
                green = color[1];
                red = color[2];
            }
            else {
                switch (bm.bmBitsPixel) {
                    case 16: {
                        blue = (byte)((transparentPixel & 0x1F) << 3);
                        green = (byte)((transparentPixel & 0x3E0) >> 2);
                        red = (byte)((transparentPixel & 0x7C00) >> 7);
                        break;
                    }
                    case 24: {
                        blue = (byte)((transparentPixel & 0xFF0000) >> 16);
                        green = (byte)((transparentPixel & 0xFF00) >> 8);
                        red = (byte)(transparentPixel & 0xFF);
                        break;
                    }
                    case 32: {
                        blue = (byte)((transparentPixel & 0xFF000000) >>> 24);
                        green = (byte)((transparentPixel & 0xFF0000) >> 16);
                        red = (byte)((transparentPixel & 0xFF00) >> 8);
                        break;
                    }
                }
            }
        }
        OS.SelectObject(srcHdc, oldSrcBitmap);
        OS.SelectObject(memHdc, oldMemBitmap);
        OS.DeleteObject(srcHdc);
        OS.DeleteObject(memHdc);
        OS.ReleaseDC(0L, hDC);
        final byte[] srcData = new byte[sizeInBytes];
        OS.MoveMemory(srcData, pBits[0], sizeInBytes);
        if (alpha != -1) {
            int y = 0;
            int dp = 0;
            while (y < imgHeight) {
                for (int x = 0; x < imgWidth; ++x) {
                    if (alpha != 0) {
                        srcData[dp] = (byte)(((srcData[dp] & 0xFF) * 255 + alpha / 2) / alpha);
                        srcData[dp + 1] = (byte)(((srcData[dp + 1] & 0xFF) * 255 + alpha / 2) / alpha);
                        srcData[dp + 2] = (byte)(((srcData[dp + 2] & 0xFF) * 255 + alpha / 2) / alpha);
                    }
                    srcData[dp + 3] = (byte)alpha;
                    dp += 4;
                }
                ++y;
            }
        }
        else if (alphaData != null) {
            int y = 0;
            int dp = 0;
            int ap = 0;
            while (y < imgHeight) {
                for (int x2 = 0; x2 < imgWidth; ++x2) {
                    final int a = alphaData[ap++] & 0xFF;
                    if (a != 0) {
                        srcData[dp] = (byte)(((srcData[dp] & 0xFF) * 255 + a / 2) / a);
                        srcData[dp + 1] = (byte)(((srcData[dp + 1] & 0xFF) * 255 + a / 2) / a);
                        srcData[dp + 2] = (byte)(((srcData[dp + 2] & 0xFF) * 255 + a / 2) / a);
                    }
                    srcData[dp + 3] = (byte)a;
                    dp += 4;
                }
                ++y;
            }
        }
        else if (transparentPixel != -1) {
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
        }
        OS.MoveMemory(pBits[0], srcData, sizeInBytes);
        return memDib;
    }
    
    static Image createIcon(final Image image) {
        final Device device = image.getDevice();
        final ImageData data = image.getImageData(DPIUtil.getDeviceZoom());
        if (data.alpha == -1 && data.alphaData == null) {
            final ImageData mask = data.getTransparencyMask();
            return new Image(device, data, mask);
        }
        final int width = data.width;
        final int height = data.height;
        final long hDC = device.internal_new_GC((GCData)null);
        final long dstHdc = OS.CreateCompatibleDC(hDC);
        final long hBitmap = create32bitDIB(image.handle, data.alpha, data.alphaData, data.transparentPixel);
        final long hMask = OS.CreateBitmap(width, height, 1, 1, (byte[])null);
        final long oldDstBitmap = OS.SelectObject(dstHdc, hMask);
        OS.PatBlt(dstHdc, 0, 0, width, height, 66);
        OS.SelectObject(dstHdc, oldDstBitmap);
        OS.DeleteDC(dstHdc);
        device.internal_dispose_GC(hDC, (GCData)null);
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
        return Image.win32_new(device, 1, hIcon);
    }
    
    static void deregister(final Display display) {
        synchronized (Device.class) {
            for (int i = 0; i < Display.Displays.length; ++i) {
                if (display == Display.Displays[i]) {
                    Display.Displays[i] = null;
                }
            }
        }
    }
    
    protected void destroy() {
        if (this == Display.Default) {
            Display.Default = null;
        }
        deregister(this);
        this.destroyDisplay();
    }
    
    void destroyDisplay() {
    }
    
    public void disposeExec(final Runnable runnable) {
        this.checkDevice();
        if (this.disposeList == null) {
            this.disposeList = new Runnable[4];
        }
        for (int i = 0; i < this.disposeList.length; ++i) {
            if (this.disposeList[i] == null) {
                this.disposeList[i] = runnable;
                return;
            }
        }
        final Runnable[] newDisposeList = new Runnable[this.disposeList.length + 4];
        System.arraycopy(this.disposeList, 0, newDisposeList, 0, this.disposeList.length);
        newDisposeList[this.disposeList.length] = runnable;
        this.disposeList = newDisposeList;
    }
    
    void drawMenuBars() {
        if (this.bars == null) {
            return;
        }
        for (final Menu menu : this.bars) {
            if (menu != null && !menu.isDisposed()) {
                menu.update();
            }
        }
        this.bars = null;
    }
    
    long embeddedProc(final long hwnd, final long msg, final long wParam, final long lParam) {
        switch ((int)msg) {
            case 32770: {
                final MSG keyMsg = new MSG();
                OS.MoveMemory(keyMsg, lParam, MSG.sizeof);
                OS.TranslateMessage(keyMsg);
                OS.DispatchMessage(keyMsg);
                final long hHeap = OS.GetProcessHeap();
                OS.HeapFree(hHeap, 0, lParam);
                break;
            }
            case 32771: {
                OS.DestroyWindow(hwnd);
                if (this.embeddedCallback != null) {
                    this.embeddedCallback.dispose();
                }
                if (this.getMsgCallback != null) {
                    this.getMsgCallback.dispose();
                }
                final Callback callback = null;
                this.getMsgCallback = callback;
                this.embeddedCallback = callback;
                final long n = 0L;
                this.getMsgProc = 0L;
                this.embeddedProc = 0L;
                break;
            }
        }
        return OS.DefWindowProc(hwnd, (int)msg, wParam, lParam);
    }
    
    void error(final int code) {
        SWT.error(code);
    }
    
    boolean filterEvent(final Event event) {
        if (this.filterTable != null) {
            final int type = event.type;
            this.sendPreEvent(type);
            try {
                this.filterTable.sendEvent(event);
            }
            finally {
                this.sendPostEvent(type);
            }
        }
        return false;
    }
    
    boolean filters(final int eventType) {
        return this.filterTable != null && this.filterTable.hooks(eventType);
    }
    
    boolean filterMessage(final MSG msg) {
        final int message = msg.message;
        if (256 <= message && message <= 264) {
            final Control control = this.findControl(msg.hwnd);
            if (control != null && (this.translateAccelerator(msg, control) || this.translateMnemonic(msg, control) || this.translateTraversal(msg, control))) {
                final int n = 0;
                this.lastKey = 0;
                this.lastAscii = 0;
                final boolean lastVirtual = false;
                this.lastDead = false;
                this.lastNull = false;
                this.lastVirtual = false;
                return true;
            }
        }
        return false;
    }
    
    Control findControl(long handle) {
        if (handle == 0L) {
            return null;
        }
        long hwndOwner = 0L;
        do {
            final Control control = this.getControl(handle);
            if (control != null) {
                return control;
            }
            hwndOwner = OS.GetWindow(handle, 4);
            handle = OS.GetParent(handle);
        } while (handle != 0L && handle != hwndOwner);
        return null;
    }
    
    public Widget findWidget(final long handle) {
        this.checkDevice();
        return (Widget)this.getControl(handle);
    }
    
    public Widget findWidget(final long handle, final long id) {
        this.checkDevice();
        final Control control = this.getControl(handle);
        return (control != null) ? control.findItem(id) : null;
    }
    
    public Widget findWidget(final Widget widget, final long id) {
        this.checkDevice();
        if (widget instanceof Control) {
            return this.findWidget(((Control)widget).handle, id);
        }
        return null;
    }
    
    long foregroundIdleProc(final long code, final long wParam, final long lParam) {
        if (code >= 0L && !this.synchronizer.isMessagesEmpty()) {
            this.sendPostExternalEventDispatchEvent();
            if (this.runMessagesInIdle) {
                if (this.runMessagesInMessageProc) {
                    OS.PostMessage(this.hwndMessage, 32774, 0L, 0L);
                }
                else {
                    this.runAsyncMessages(false);
                }
            }
            final MSG msg = new MSG();
            final int flags = 458754;
            if (!OS.PeekMessage(msg, 0L, 0, 0, 458754)) {
                this.wakeThread();
            }
            this.sendPreExternalEventDispatchEvent();
        }
        return OS.CallNextHookEx(this.idleHook, (int)code, wParam, lParam);
    }
    
    public static Display findDisplay(final Thread thread) {
        synchronized (Device.class) {
            for (final Display display : Display.Displays) {
                if (display != null && display.thread == thread) {
                    return display;
                }
            }
            return null;
        }
    }
    
    TouchSource findTouchSource(final long touchDevice, final Monitor monitor) {
        if (this.touchSources == null) {
            this.touchSources = new TouchSource[4];
        }
        final int length = this.touchSources.length;
        for (int i = 0; i < length; ++i) {
            if (this.touchSources[i] != null && this.touchSources[i].handle == touchDevice) {
                return this.touchSources[i];
            }
        }
        int index;
        for (index = 0; index < length && this.touchSources[index] != null; ++index) {}
        if (index == length) {
            final TouchSource[] newTouchSources = new TouchSource[length + 4];
            System.arraycopy(this.touchSources, 0, newTouchSources, 0, length);
            this.touchSources = newTouchSources;
        }
        return this.touchSources[index] = new TouchSource(touchDevice, true, monitor.getBounds());
    }
    
    public Shell getActiveShell() {
        this.checkDevice();
        final Control control = this.findControl(OS.GetActiveWindow());
        return (control != null) ? control.getShell() : null;
    }
    
    public Menu getMenuBar() {
        this.checkDevice();
        return null;
    }
    
    public Rectangle getBounds() {
        this.checkDevice();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    Rectangle getBoundsInPixels() {
        this.checkDevice();
        if (OS.GetSystemMetrics(80) < 2) {
            final int width = OS.GetSystemMetrics(0);
            final int height = OS.GetSystemMetrics(1);
            return new Rectangle(0, 0, width, height);
        }
        final int x = OS.GetSystemMetrics(76);
        final int y = OS.GetSystemMetrics(77);
        final int width2 = OS.GetSystemMetrics(78);
        final int height2 = OS.GetSystemMetrics(79);
        return new Rectangle(x, y, width2, height2);
    }
    
    public static Display getCurrent() {
        return findDisplay(Thread.currentThread());
    }
    
    int getClickCount(final int type, final int button, final long hwnd, final long lParam) {
        Label_0143: {
            switch (type) {
                case 3: {
                    final int doubleClick = OS.GetDoubleClickTime();
                    if (this.clickRect == null) {
                        this.clickRect = new RECT();
                    }
                    final int deltaTime = Math.abs(this.lastTime - this.getLastEventTime());
                    final POINT pt = new POINT();
                    OS.POINTSTOPOINT(pt, lParam);
                    if (this.lastClickHwnd == hwnd && this.lastButton == button && deltaTime <= doubleClick && OS.PtInRect(this.clickRect, pt)) {
                        ++this.clickCount;
                        break Label_0143;
                    }
                    this.clickCount = 1;
                    break Label_0143;
                }
                case 8: {
                    this.lastButton = button;
                    this.lastClickHwnd = hwnd;
                    this.lastTime = this.getLastEventTime();
                    final int xInset = OS.GetSystemMetrics(36) / 2;
                    final int yInset = OS.GetSystemMetrics(37) / 2;
                    final int x = OS.GET_X_LPARAM(lParam);
                    final int y = OS.GET_Y_LPARAM(lParam);
                    OS.SetRect(this.clickRect, x - xInset, y - yInset, x + xInset, y + yInset);
                }
                case 4: {
                    return this.clickCount;
                }
                default: {
                    return 0;
                }
            }
        }
    }
    
    public Rectangle getClientArea() {
        this.checkDevice();
        return DPIUtil.autoScaleDown(this.getClientAreaInPixels());
    }
    
    Rectangle getClientAreaInPixels() {
        this.checkDevice();
        if (OS.GetSystemMetrics(80) < 2) {
            final RECT rect = new RECT();
            OS.SystemParametersInfo(48, 0, rect, 0);
            final int width = rect.right - rect.left;
            final int height = rect.bottom - rect.top;
            return new Rectangle(rect.left, rect.top, width, height);
        }
        final int x = OS.GetSystemMetrics(76);
        final int y = OS.GetSystemMetrics(77);
        final int width2 = OS.GetSystemMetrics(78);
        final int height2 = OS.GetSystemMetrics(79);
        return new Rectangle(x, y, width2, height2);
    }
    
    Control getControl(final long handle) {
        if (handle == 0L) {
            return null;
        }
        if (this.lastControl != null && this.lastHwnd == handle) {
            return this.lastControl;
        }
        if (this.lastGetControl != null && this.lastGetHwnd == handle) {
            return this.lastGetControl;
        }
        final int index = (int)OS.GetProp(handle, (long)Display.SWT_OBJECT_INDEX) - 1;
        if (0 <= index && index < this.controlTable.length) {
            this.lastGetHwnd = handle;
            return this.lastGetControl = this.controlTable[index];
        }
        return null;
    }
    
    public Control getCursorControl() {
        this.checkDevice();
        final POINT pt = new POINT();
        if (!OS.GetCursorPos(pt)) {
            return null;
        }
        return this.findControl(OS.WindowFromPoint(pt));
    }
    
    public Point getCursorLocation() {
        this.checkDevice();
        return DPIUtil.autoScaleDown(this.getCursorLocationInPixels());
    }
    
    Point getCursorLocationInPixels() {
        final POINT pt = new POINT();
        OS.GetCursorPos(pt);
        return new Point(pt.x, pt.y);
    }
    
    public Point[] getCursorSizes() {
        this.checkDevice();
        return new Point[] { new Point(OS.GetSystemMetrics(13), OS.GetSystemMetrics(14)) };
    }
    
    public static Display getDefault() {
        synchronized (Device.class) {
            if (Display.Default == null) {
                Display.Default = new Display();
            }
            return Display.Default;
        }
    }
    
    protected int getDeviceZoom() {
        if (OS.WIN32_VERSION >= OS.VERSION(6, 3)) {
            return this.getPrimaryMonitor().getZoom();
        }
        return super.getDeviceZoom();
    }
    
    static boolean isValidClass(final Class<?> clazz) {
        final String name = clazz.getName();
        final int index = name.lastIndexOf(46);
        return name.substring(0, index + 1).equals("org.eclipse.swt.widgets.");
    }
    
    public Object getData(final String key) {
        this.checkDevice();
        if (key == null) {
            this.error(4);
        }
        if (key.equals("org.eclipse.swt.internal.win32.runMessagesInIdle")) {
            return this.runMessagesInIdle;
        }
        if (key.equals("org.eclipse.swt.internal.win32.runMessagesInMessageProc")) {
            return this.runMessagesInMessageProc;
        }
        if (key.equals("org.eclipse.swt.internal.win32.useOwnDC")) {
            return this.useOwnDC;
        }
        if (key.equals("org.eclipse.swt.internal.win32.accelKeyHit")) {
            return this.accelKeyHit;
        }
        if (key.equals("org.eclipse.swt.internal.win32.appLocalDir")) {
            return this.appLocalDir;
        }
        if (this.keys == null) {
            return null;
        }
        for (int i = 0; i < this.keys.length; ++i) {
            if (this.keys[i].equals(key)) {
                return this.values[i];
            }
        }
        return null;
    }
    
    public Object getData() {
        this.checkDevice();
        return this.data;
    }
    
    public int getDismissalAlignment() {
        this.checkDevice();
        return 16384;
    }
    
    public int getDoubleClickTime() {
        this.checkDevice();
        return OS.GetDoubleClickTime();
    }
    
    public Control getFocusControl() {
        this.checkDevice();
        if (this.focusControl != null && !this.focusControl.isDisposed()) {
            return this.focusControl;
        }
        return this._getFocusControl();
    }
    
    String getFontName(final LOGFONT logFont) {
        char[] chars;
        int index;
        for (chars = logFont.lfFaceName, index = 0; index < chars.length && chars[index] != '\0'; ++index) {}
        return new String(chars, 0, index);
    }
    
    public boolean getHighContrast() {
        this.checkDevice();
        final HIGHCONTRAST pvParam = new HIGHCONTRAST();
        pvParam.cbSize = HIGHCONTRAST.sizeof;
        OS.SystemParametersInfo(66, 0, pvParam, 0);
        return (pvParam.dwFlags & 0x1) != 0x0;
    }
    
    public int getIconDepth() {
        this.checkDevice();
        if (this.getDepth() >= 24) {
            return 32;
        }
        final TCHAR buffer1 = new TCHAR(0, "Control Panel\\Desktop\\WindowMetrics", true);
        final long[] phkResult = { 0L };
        int result = OS.RegOpenKeyEx(-2147483647L, buffer1, 0, 131097, phkResult);
        if (result != 0) {
            return 4;
        }
        int depth = 4;
        final int[] lpcbData = { 0 };
        final TCHAR buffer2 = new TCHAR(0, "Shell Icon BPP", true);
        result = OS.RegQueryValueEx(phkResult[0], buffer2, 0L, (int[])null, (TCHAR)null, lpcbData);
        if (result == 0) {
            final TCHAR lpData = new TCHAR(0, lpcbData[0] / 2);
            result = OS.RegQueryValueEx(phkResult[0], buffer2, 0L, (int[])null, lpData, lpcbData);
            if (result == 0) {
                try {
                    depth = Integer.parseInt(lpData.toString(0, lpData.strlen()));
                }
                catch (NumberFormatException ex) {}
            }
        }
        OS.RegCloseKey(phkResult[0]);
        return depth;
    }
    
    public Point[] getIconSizes() {
        this.checkDevice();
        return new Point[] { new Point(OS.GetSystemMetrics(49), OS.GetSystemMetrics(50)), new Point(OS.GetSystemMetrics(11), OS.GetSystemMetrics(12)) };
    }
    
    ImageList getImageList(final int style, final int width, final int height) {
        if (this.imageList == null) {
            this.imageList = new ImageList[4];
        }
        int i;
        int length;
        for (i = 0, length = this.imageList.length; i < length; ++i) {
            final ImageList list = this.imageList[i];
            if (list == null) {
                break;
            }
            final Point size = list.getImageSize();
            if (size.x == width && size.y == height && list.getStyle() == style) {
                list.addRef();
                return list;
            }
        }
        if (i == length) {
            final ImageList[] newList = new ImageList[length + 4];
            System.arraycopy(this.imageList, 0, newList, 0, length);
            this.imageList = newList;
        }
        final ImageList list = new ImageList(style, width, height);
        (this.imageList[i] = list).addRef();
        return list;
    }
    
    ImageList getImageListToolBar(final int style, final int width, final int height) {
        if (this.toolImageList == null) {
            this.toolImageList = new ImageList[4];
        }
        int i;
        int length;
        for (i = 0, length = this.toolImageList.length; i < length; ++i) {
            final ImageList list = this.toolImageList[i];
            if (list == null) {
                break;
            }
            final Point size = list.getImageSize();
            if (size.x == width && size.y == height && list.getStyle() == style) {
                list.addRef();
                return list;
            }
        }
        if (i == length) {
            final ImageList[] newList = new ImageList[length + 4];
            System.arraycopy(this.toolImageList, 0, newList, 0, length);
            this.toolImageList = newList;
        }
        final ImageList list = new ImageList(style, width, height);
        (this.toolImageList[i] = list).addRef();
        return list;
    }
    
    ImageList getImageListToolBarDisabled(final int style, final int width, final int height) {
        if (this.toolDisabledImageList == null) {
            this.toolDisabledImageList = new ImageList[4];
        }
        int i;
        int length;
        for (i = 0, length = this.toolDisabledImageList.length; i < length; ++i) {
            final ImageList list = this.toolDisabledImageList[i];
            if (list == null) {
                break;
            }
            final Point size = list.getImageSize();
            if (size.x == width && size.y == height && list.getStyle() == style) {
                list.addRef();
                return list;
            }
        }
        if (i == length) {
            final ImageList[] newList = new ImageList[length + 4];
            System.arraycopy(this.toolDisabledImageList, 0, newList, 0, length);
            this.toolDisabledImageList = newList;
        }
        final ImageList list = new ImageList(style, width, height);
        (this.toolDisabledImageList[i] = list).addRef();
        return list;
    }
    
    ImageList getImageListToolBarHot(final int style, final int width, final int height) {
        if (this.toolHotImageList == null) {
            this.toolHotImageList = new ImageList[4];
        }
        int i;
        int length;
        for (i = 0, length = this.toolHotImageList.length; i < length; ++i) {
            final ImageList list = this.toolHotImageList[i];
            if (list == null) {
                break;
            }
            final Point size = list.getImageSize();
            if (size.x == width && size.y == height && list.getStyle() == style) {
                list.addRef();
                return list;
            }
        }
        if (i == length) {
            final ImageList[] newList = new ImageList[length + 4];
            System.arraycopy(this.toolHotImageList, 0, newList, 0, length);
            this.toolHotImageList = newList;
        }
        final ImageList list = new ImageList(style, width, height);
        (this.toolHotImageList[i] = list).addRef();
        return list;
    }
    
    public static boolean isSystemDarkTheme() {
        boolean isDarkTheme = false;
        if (OS.WIN32_BUILD >= 17763) {
            final int[] result = OS.readRegistryDwords(-2147483647, "Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize", "AppsUseLightTheme");
            if (result != null) {
                isDarkTheme = (result[0] == 0);
            }
        }
        return isDarkTheme;
    }
    
    int getLastEventTime() {
        return OS.GetMessageTime();
    }
    
    MenuItem getMenuItem(int id) {
        if (this.items == null) {
            return null;
        }
        id -= 108;
        if (0 <= id && id < this.items.length) {
            return this.items[id];
        }
        return null;
    }
    
    Shell getModalShell() {
        if (this.modalShells == null) {
            return null;
        }
        int index = this.modalShells.length;
        while (--index >= 0) {
            final Shell shell = this.modalShells[index];
            if (shell != null) {
                return shell;
            }
        }
        return null;
    }
    
    Dialog getModalDialog() {
        return this.modalDialog;
    }
    
    Monitor getMonitor(final long hmonitor) {
        final MONITORINFO lpmi = new MONITORINFO();
        lpmi.cbSize = MONITORINFO.sizeof;
        OS.GetMonitorInfo(hmonitor, lpmi);
        final Monitor monitor = new Monitor();
        monitor.handle = hmonitor;
        final Rectangle boundsInPixels = new Rectangle(lpmi.rcMonitor_left, lpmi.rcMonitor_top, lpmi.rcMonitor_right - lpmi.rcMonitor_left, lpmi.rcMonitor_bottom - lpmi.rcMonitor_top);
        monitor.setBounds(DPIUtil.autoScaleDown(boundsInPixels));
        final Rectangle clientAreaInPixels = new Rectangle(lpmi.rcWork_left, lpmi.rcWork_top, lpmi.rcWork_right - lpmi.rcWork_left, lpmi.rcWork_bottom - lpmi.rcWork_top);
        monitor.setClientArea(DPIUtil.autoScaleDown(clientAreaInPixels));
        if (OS.WIN32_VERSION >= OS.VERSION(6, 3)) {
            final int[] dpiX = { 0 };
            final int[] dpiY = { 0 };
            int result = OS.GetDpiForMonitor(monitor.handle, 0, dpiX, dpiY);
            result = ((result == 0) ? DPIUtil.mapDPIToZoom(dpiX[0]) : 100);
            monitor.zoom = result;
        }
        else {
            monitor.zoom = this.getDeviceZoom();
        }
        return monitor;
    }
    
    public Monitor[] getMonitors() {
        this.checkDevice();
        this.monitors = new Monitor[4];
        final Callback callback = new Callback((Object)this, "monitorEnumProc", 4);
        OS.EnumDisplayMonitors(0L, (RECT)null, callback.getAddress(), 0);
        callback.dispose();
        final Monitor[] result = new Monitor[this.monitorCount];
        System.arraycopy(this.monitors, 0, result, 0, this.monitorCount);
        this.monitors = null;
        this.monitorCount = 0;
        return result;
    }
    
    long getMsgProc(final long code, final long wParam, final long lParam) {
        if (this.embeddedHwnd == 0L) {
            final long hInstance = OS.GetModuleHandle((char[])null);
            this.embeddedHwnd = OS.CreateWindowEx(0, this.windowClass, (TCHAR)null, 0, 0, 0, 0, 0, 0L, 0L, hInstance, (CREATESTRUCT)null);
            this.embeddedCallback = new Callback((Object)this, "embeddedProc", 4);
            this.embeddedProc = this.embeddedCallback.getAddress();
            OS.SetWindowLongPtr(this.embeddedHwnd, -4, this.embeddedProc);
        }
        Label_0302: {
            if (code >= 0L && (wParam & 0x1L) != 0x0L) {
                final MSG msg = new MSG();
                OS.MoveMemory(msg, lParam, MSG.sizeof);
                switch (msg.message) {
                    case 256:
                    case 257:
                    case 260:
                    case 261: {
                        final Control control = this.findControl(msg.hwnd);
                        if (control == null) {
                            break;
                        }
                        final long hHeap = OS.GetProcessHeap();
                        final long keyMsg = OS.HeapAlloc(hHeap, 8, MSG.sizeof);
                        OS.MoveMemory(keyMsg, msg, MSG.sizeof);
                        OS.PostMessage(this.hwndMessage, 32770, wParam, keyMsg);
                        switch ((int)msg.wParam) {
                            case 16:
                            case 17:
                            case 18:
                            case 20:
                            case 144:
                            case 145: {
                                break Label_0302;
                            }
                            default: {
                                msg.message = 0;
                                OS.MoveMemory(lParam, msg, MSG.sizeof);
                                break Label_0302;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return OS.CallNextHookEx(this.msgHook, (int)code, wParam, lParam);
    }
    
    public Monitor getPrimaryMonitor() {
        this.checkDevice();
        final long hmonitor = OS.MonitorFromWindow(0L, 1);
        return this.getMonitor(hmonitor);
    }
    
    public Shell[] getShells() {
        this.checkDevice();
        int index = 0;
        Shell[] result = new Shell[16];
        for (final Control control : this.controlTable) {
            if (control instanceof Shell) {
                int j;
                for (j = 0; j < index && result[j] != control; ++j) {}
                if (j == index) {
                    if (index == result.length) {
                        final Shell[] newResult = new Shell[index + 16];
                        System.arraycopy(result, 0, newResult, 0, index);
                        result = newResult;
                    }
                    result[index++] = (Shell)control;
                }
            }
        }
        if (index == result.length) {
            return result;
        }
        final Shell[] newResult2 = new Shell[index];
        System.arraycopy(result, 0, newResult2, 0, index);
        return newResult2;
    }
    
    public Synchronizer getSynchronizer() {
        this.checkDevice();
        return this.synchronizer;
    }
    
    public Thread getSyncThread() {
        synchronized (Device.class) {
            if (this.isDisposed()) {
                this.error(45);
            }
            return this.synchronizer.syncThread;
        }
    }
    
    public Color getSystemColor(final int id) {
        this.checkDevice();
        int pixel = 0;
        switch (id) {
            case 17: {
                pixel = OS.GetSysColor(21);
                break;
            }
            case 39: {
                pixel = OS.GetSysColor(17);
                break;
            }
            case 18: {
                pixel = OS.GetSysColor(16);
                break;
            }
            case 19: {
                pixel = OS.GetSysColor(22);
                break;
            }
            case 20: {
                pixel = OS.GetSysColor(20);
                break;
            }
            case 22:
            case 38: {
                pixel = OS.GetSysColor(15);
                break;
            }
            case 23: {
                pixel = OS.GetSysColor(6);
                break;
            }
            case 21:
            case 24: {
                pixel = OS.GetSysColor(8);
                break;
            }
            case 25: {
                pixel = OS.GetSysColor(5);
                break;
            }
            case 26: {
                pixel = OS.GetSysColor(13);
                break;
            }
            case 27: {
                pixel = OS.GetSysColor(14);
                break;
            }
            case 36: {
                pixel = OS.GetSysColor(26);
                break;
            }
            case 28: {
                pixel = OS.GetSysColor(23);
                break;
            }
            case 29: {
                pixel = OS.GetSysColor(24);
                break;
            }
            case 30: {
                pixel = OS.GetSysColor(9);
                break;
            }
            case 31: {
                pixel = OS.GetSysColor(2);
                break;
            }
            case 32: {
                pixel = OS.GetSysColor(27);
                if (pixel == 0) {
                    pixel = OS.GetSysColor(2);
                    break;
                }
                break;
            }
            case 33: {
                pixel = OS.GetSysColor(19);
                break;
            }
            case 34: {
                pixel = OS.GetSysColor(3);
                break;
            }
            case 35: {
                pixel = OS.GetSysColor(28);
                if (pixel == 0) {
                    pixel = OS.GetSysColor(3);
                    break;
                }
                break;
            }
            default: {
                return super.getSystemColor(id);
            }
        }
        return Color.win32_new((Device)this, pixel);
    }
    
    public Cursor getSystemCursor(final int id) {
        this.checkDevice();
        if (0 > id || id >= this.cursors.length) {
            return null;
        }
        if (this.cursors[id] == null) {
            this.cursors[id] = new Cursor((Device)this, id);
        }
        return this.cursors[id];
    }
    
    public Font getSystemFont() {
        this.checkDevice();
        if (this.systemFont != null) {
            return this.systemFont;
        }
        long hFont = 0L;
        final NONCLIENTMETRICS info = new NONCLIENTMETRICS();
        info.cbSize = NONCLIENTMETRICS.sizeof;
        if (OS.SystemParametersInfo(41, 0, info, 0)) {
            final LOGFONT logFont = info.lfMessageFont;
            hFont = OS.CreateFontIndirect(logFont);
            this.lfSystemFont = ((hFont != 0L) ? logFont : null);
        }
        if (hFont == 0L) {
            hFont = OS.GetStockObject(17);
        }
        if (hFont == 0L) {
            hFont = OS.GetStockObject(13);
        }
        return this.systemFont = Font.win32_new((Device)this, hFont);
    }
    
    public Image getSystemImage(final int id) {
        this.checkDevice();
        switch (id) {
            case 1: {
                if (this.errorImage != null) {
                    return this.errorImage;
                }
                final long hIcon = OS.LoadImage(0L, 32513L, 1, 0, 0, 32768);
                return this.errorImage = Image.win32_new((Device)this, 1, hIcon);
            }
            case 2:
            case 16: {
                if (this.infoImage != null) {
                    return this.infoImage;
                }
                final long hIcon = OS.LoadImage(0L, 32516L, 1, 0, 0, 32768);
                return this.infoImage = Image.win32_new((Device)this, 1, hIcon);
            }
            case 4: {
                if (this.questionImage != null) {
                    return this.questionImage;
                }
                final long hIcon = OS.LoadImage(0L, 32514L, 1, 0, 0, 32768);
                return this.questionImage = Image.win32_new((Device)this, 1, hIcon);
            }
            case 8: {
                if (this.warningIcon != null) {
                    return this.warningIcon;
                }
                final long hIcon = OS.LoadImage(0L, 32515L, 1, 0, 0, 32768);
                return this.warningIcon = Image.win32_new((Device)this, 1, hIcon);
            }
            default: {
                return null;
            }
        }
    }
    
    public Menu getSystemMenu() {
        this.checkDevice();
        return null;
    }
    
    public TaskBar getSystemTaskBar() {
        this.checkDevice();
        if (this.taskBar != null) {
            return this.taskBar;
        }
        if (OS.WIN32_VERSION >= OS.VERSION(6, 1)) {
            try {
                this.taskBar = new TaskBar(this, 0);
            }
            catch (SWTError e) {
                if (e.code == 20) {
                    return null;
                }
                throw e;
            }
        }
        return this.taskBar;
    }
    
    public Tray getSystemTray() {
        this.checkDevice();
        if (this.tray == null) {
            this.tray = new Tray(this, 0);
        }
        return this.tray;
    }
    
    public Thread getThread() {
        synchronized (Device.class) {
            if (this.isDisposed()) {
                this.error(45);
            }
            return this.thread;
        }
    }
    
    public boolean getTouchEnabled() {
        this.checkDevice();
        final int value = OS.GetSystemMetrics(94);
        return (value & 0xC0) == 0xC0;
    }
    
    long hButtonTheme() {
        if (this.hButtonTheme != 0L) {
            return this.hButtonTheme;
        }
        final char[] themeName = "BUTTON\u0000".toCharArray();
        return this.hButtonTheme = OS.OpenThemeData(this.hwndMessage, themeName);
    }
    
    long hButtonThemeDark() {
        if (this.hButtonThemeDark != 0L) {
            return this.hButtonThemeDark;
        }
        final char[] themeName = "Darkmode_Explorer::BUTTON\u0000".toCharArray();
        return this.hButtonThemeDark = OS.OpenThemeData(this.hwndMessage, themeName);
    }
    
    long hButtonThemeAuto() {
        if (this.useDarkModeExplorerTheme) {
            return this.hButtonThemeDark();
        }
        return this.hButtonTheme();
    }
    
    long hEditTheme() {
        if (this.hEditTheme != 0L) {
            return this.hEditTheme;
        }
        final char[] themeName = "EDIT\u0000".toCharArray();
        return this.hEditTheme = OS.OpenThemeData(this.hwndMessage, themeName);
    }
    
    long hExplorerBarTheme() {
        if (this.hExplorerBarTheme != 0L) {
            return this.hExplorerBarTheme;
        }
        final char[] themeName = "EXPLORERBAR\u0000".toCharArray();
        return this.hExplorerBarTheme = OS.OpenThemeData(this.hwndMessage, themeName);
    }
    
    long hScrollBarTheme() {
        if (this.hScrollBarTheme != 0L) {
            return this.hScrollBarTheme;
        }
        final char[] themeName = "SCROLLBAR\u0000".toCharArray();
        return this.hScrollBarTheme = OS.OpenThemeData(this.hwndMessage, themeName);
    }
    
    long hTabTheme() {
        if (this.hTabTheme != 0L) {
            return this.hTabTheme;
        }
        final char[] themeName = "TAB\u0000".toCharArray();
        return this.hTabTheme = OS.OpenThemeData(this.hwndMessage, themeName);
    }
    
    void resetThemes() {
        if (this.hButtonTheme != 0L) {
            OS.CloseThemeData(this.hButtonTheme);
            this.hButtonTheme = 0L;
        }
        if (this.hButtonThemeDark != 0L) {
            OS.CloseThemeData(this.hButtonThemeDark);
            this.hButtonThemeDark = 0L;
        }
        if (this.hEditTheme != 0L) {
            OS.CloseThemeData(this.hEditTheme);
            this.hEditTheme = 0L;
        }
        if (this.hExplorerBarTheme != 0L) {
            OS.CloseThemeData(this.hExplorerBarTheme);
            this.hExplorerBarTheme = 0L;
        }
        if (this.hScrollBarTheme != 0L) {
            OS.CloseThemeData(this.hScrollBarTheme);
            this.hScrollBarTheme = 0L;
        }
        if (this.hTabTheme != 0L) {
            OS.CloseThemeData(this.hTabTheme);
            this.hTabTheme = 0L;
        }
    }
    
    public long internal_new_GC(final GCData data) {
        if (this.isDisposed()) {
            this.error(45);
        }
        final long hDC = OS.GetDC(0L);
        if (hDC == 0L) {
            this.error(2);
        }
        if (data != null) {
            final int mask = 100663296;
            if ((data.style & 0x6000000) != 0x0) {
                data.layout = (((data.style & 0x4000000) != 0x0) ? 1 : 0);
            }
            else {
                data.style |= 0x2000000;
            }
            data.device = this;
            data.font = this.getSystemFont();
        }
        return hDC;
    }
    
    protected void init() {
        this.synchronizer = new Synchronizer(this);
        super.init();
        DPIUtil.setDeviceZoom(this.getDeviceZoom());
        char[] appName = null;
        if (Display.APP_NAME != null && !"SWT".equalsIgnoreCase(Display.APP_NAME) && OS.WIN32_VERSION >= OS.VERSION(6, 1)) {
            final int length = Display.APP_NAME.length();
            appName = new char[length + 1];
            Display.APP_NAME.getChars(0, length, appName, 0);
            final long[] appID = { 0L };
            if (OS.GetCurrentProcessExplicitAppUserModelID(appID) != 0) {
                OS.SetCurrentProcessExplicitAppUserModelID(appName);
            }
            if (appID[0] != 0L) {
                OS.CoTaskMemFree(appID[0]);
            }
        }
        this.windowCallback = new Callback((Object)this, "windowProc", 4);
        this.windowProc = this.windowCallback.getAddress();
        this.threadId = OS.GetCurrentThreadId();
        this.windowClass = new TCHAR(0, "SWT_Window" + Display.WindowClassCount, true);
        this.windowShadowClass = new TCHAR(0, "SWT_WindowShadow" + Display.WindowClassCount, true);
        this.windowOwnDCClass = new TCHAR(0, "SWT_WindowOwnDC" + Display.WindowClassCount, true);
        ++Display.WindowClassCount;
        final long hInstance = OS.GetModuleHandle((char[])null);
        final WNDCLASS lpWndClass = new WNDCLASS();
        lpWndClass.hInstance = hInstance;
        lpWndClass.lpfnWndProc = this.windowProc;
        lpWndClass.style = 8;
        lpWndClass.hCursor = OS.LoadCursor(0L, 32512L);
        lpWndClass.hIcon = OS.LoadIcon(0L, 32512L);
        OS.RegisterClass(this.windowClass, lpWndClass);
        final WNDCLASS wndclass3;
        final WNDCLASS wndclass = wndclass3 = lpWndClass;
        wndclass3.style |= 0x20000;
        OS.RegisterClass(this.windowShadowClass, lpWndClass);
        final WNDCLASS wndclass4;
        final WNDCLASS wndclass2 = wndclass4 = lpWndClass;
        wndclass4.style |= 0x20;
        OS.RegisterClass(this.windowOwnDCClass, lpWndClass);
        this.hwndMessage = OS.CreateWindowEx(0, this.windowClass, (TCHAR)null, 0, 0, 0, 0, 0, 0L, 0L, hInstance, (CREATESTRUCT)null);
        final String title = "SWT_Window_" + Display.APP_NAME;
        OS.SetWindowText(this.hwndMessage, new TCHAR(0, title, true));
        this.messageCallback = new Callback((Object)this, "messageProc", 4);
        this.messageProc = this.messageCallback.getAddress();
        OS.SetWindowLongPtr(this.hwndMessage, -4, this.messageProc);
        this.msgFilterCallback = new Callback((Object)this, "msgFilterProc", 3);
        this.msgFilterProc = this.msgFilterCallback.getAddress();
        this.filterHook = OS.SetWindowsHookEx(-1, this.msgFilterProc, 0L, this.threadId);
        this.foregroundIdleCallback = new Callback((Object)this, "foregroundIdleProc", 3);
        this.foregroundIdleProc = this.foregroundIdleCallback.getAddress();
        this.idleHook = OS.SetWindowsHookEx(11, this.foregroundIdleProc, 0L, this.threadId);
        Display.TASKBARCREATED = OS.RegisterWindowMessage(new TCHAR(0, "TaskbarCreated", true));
        Display.TASKBARBUTTONCREATED = OS.RegisterWindowMessage(new TCHAR(0, "TaskbarButtonCreated", true));
        Display.SWT_RESTORECARET = OS.RegisterWindowMessage(new TCHAR(0, "SWT_RESTORECARET", true));
        Display.DI_GETDRAGIMAGE = OS.RegisterWindowMessage(new TCHAR(0, "ShellGetDragImage", true));
        Display.SWT_OPENDOC = OS.RegisterWindowMessage(new TCHAR(0, "SWT_OPENDOC", true));
        OS.OleInitialize(0L);
        if (appName != null) {
            final long[] ppv = { 0L };
            final int hr = COM.CoCreateInstance(COM.CLSID_DestinationList, 0L, 1, COM.IID_ICustomDestinationList, ppv);
            if (hr == 0) {
                final ICustomDestinationList pList = new ICustomDestinationList(ppv[0]);
                pList.DeleteList(appName);
                pList.Release();
            }
        }
        this.appLocalDir = System.getenv("LOCALAPPDATA") + "\\" + Display.APP_NAME.replaceAll("[\\\\/:*?\"<>|]", "_");
        OS.BufferedPaintInit();
        this.indexTable = new int[1024];
        this.controlTable = new Control[1024];
        for (int i = 0; i < 1023; ++i) {
            this.indexTable[i] = i + 1;
        }
        this.indexTable[1023] = -1;
    }
    
    public void internal_dispose_GC(final long hDC, final GCData data) {
        OS.ReleaseDC(0L, hDC);
    }
    
    boolean isXMouseActive() {
        boolean xMouseActive = false;
        final int[] result = OS.readRegistryDwords(-2147483647, "Control Panel\\Desktop", "UserPreferencesMask");
        if (result != null) {
            xMouseActive = ((result[0] & 0x1) != 0x0);
        }
        return xMouseActive;
    }
    
    boolean isValidThread() {
        return this.thread == Thread.currentThread();
    }
    
    public Point map(final Control from, final Control to, Point point) {
        this.checkDevice();
        if (point == null) {
            this.error(4);
        }
        point = DPIUtil.autoScaleUp(point);
        return DPIUtil.autoScaleDown(this.mapInPixels(from, to, point));
    }
    
    Point mapInPixels(final Control from, final Control to, final Point point) {
        return this.mapInPixels(from, to, point.x, point.y);
    }
    
    public Point map(final Control from, final Control to, int x, int y) {
        this.checkDevice();
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        return DPIUtil.autoScaleDown(this.mapInPixels(from, to, x, y));
    }
    
    Point mapInPixels(final Control from, final Control to, final int x, final int y) {
        if (from != null && from.isDisposed()) {
            this.error(5);
        }
        if (to != null && to.isDisposed()) {
            this.error(5);
        }
        if (from == to) {
            return new Point(x, y);
        }
        final long hwndFrom = (from != null) ? from.handle : 0L;
        final long hwndTo = (to != null) ? to.handle : 0L;
        final POINT point = new POINT();
        point.x = x;
        point.y = y;
        OS.MapWindowPoints(hwndFrom, hwndTo, point, 1);
        return new Point(point.x, point.y);
    }
    
    public Rectangle map(final Control from, final Control to, Rectangle rectangle) {
        this.checkDevice();
        if (rectangle == null) {
            this.error(4);
        }
        rectangle = DPIUtil.autoScaleUp(rectangle);
        return DPIUtil.autoScaleDown(this.mapInPixels(from, to, rectangle));
    }
    
    Rectangle mapInPixels(final Control from, final Control to, final Rectangle rectangle) {
        return this.mapInPixels(from, to, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public Rectangle map(final Control from, final Control to, int x, int y, int width, int height) {
        this.checkDevice();
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        return DPIUtil.autoScaleDown(this.mapInPixels(from, to, x, y, width, height));
    }
    
    Rectangle mapInPixels(final Control from, final Control to, final int x, final int y, final int width, final int height) {
        if (from != null && from.isDisposed()) {
            this.error(5);
        }
        if (to != null && to.isDisposed()) {
            this.error(5);
        }
        if (from == to) {
            return new Rectangle(x, y, width, height);
        }
        final long hwndFrom = (from != null) ? from.handle : 0L;
        final long hwndTo = (to != null) ? to.handle : 0L;
        final RECT rect = new RECT();
        rect.left = x;
        rect.top = y;
        rect.right = x + width;
        rect.bottom = y + height;
        OS.MapWindowPoints(hwndFrom, hwndTo, rect, 2);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    long messageProc(final long hwnd, final long msg, final long wParam, final long lParam) {
        switch ((int)msg) {
            case 32774: {
                if (this.runMessagesInIdle) {
                    this.runAsyncMessages(false);
                    break;
                }
                break;
            }
            case 32770: {
                boolean consumed = false;
                final MSG keyMsg = new MSG();
                OS.MoveMemory(keyMsg, lParam, MSG.sizeof);
                final Control control = this.findControl(keyMsg.hwnd);
                Label_0656: {
                    if (control != null) {
                        boolean accentKey = false;
                        Label_0458: {
                            switch (keyMsg.message) {
                                case 256:
                                case 260: {
                                    switch ((int)keyMsg.wParam) {
                                        case 16:
                                        case 17:
                                        case 18:
                                        case 20:
                                        case 144:
                                        case 145: {
                                            break Label_0458;
                                        }
                                        default: {
                                            final int mapKey = OS.MapVirtualKey((int)keyMsg.wParam, 2);
                                            if (mapKey == 0) {
                                                break Label_0458;
                                            }
                                            accentKey = ((mapKey & Integer.MIN_VALUE) != 0x0);
                                            if (!accentKey) {
                                                for (final short accent : Display.ACCENTS) {
                                                    final int value = OS.VkKeyScan(accent);
                                                    if (value != -1 && (value & 0xFF) == keyMsg.wParam) {
                                                        final int state = value >> 8;
                                                        if (OS.GetKeyState(16) < 0 == ((state & 0x1) != 0x0) && OS.GetKeyState(17) < 0 == ((state & 0x2) != 0x0) && OS.GetKeyState(18) < 0 == ((state & 0x4) != 0x0)) {
                                                            if ((state & 0x7) != 0x0) {
                                                                accentKey = true;
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                    }
                                                }
                                                break Label_0458;
                                            }
                                            break Label_0458;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if (!accentKey && !this.ignoreNextKey) {
                            keyMsg.hwnd = control.handle;
                            final int flags = 10420227;
                            do {
                                if (!(consumed |= this.filterMessage(keyMsg))) {
                                    OS.TranslateMessage(keyMsg);
                                    consumed |= (OS.DispatchMessage(keyMsg) == 1L);
                                }
                            } while (OS.PeekMessage(keyMsg, keyMsg.hwnd, 256, 264, 10420227));
                        }
                        switch (keyMsg.message) {
                            case 256:
                            case 260: {
                                switch ((int)keyMsg.wParam) {
                                    case 16:
                                    case 17:
                                    case 18:
                                    case 20:
                                    case 144:
                                    case 145: {
                                        break Label_0656;
                                    }
                                    default: {
                                        this.ignoreNextKey = accentKey;
                                        break Label_0656;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                switch ((int)keyMsg.wParam) {
                    case 16:
                    case 17:
                    case 18:
                    case 20:
                    case 144:
                    case 145: {
                        consumed = true;
                        break;
                    }
                }
                if (consumed) {
                    final long hHeap = OS.GetProcessHeap();
                    OS.HeapFree(hHeap, 0, lParam);
                }
                else {
                    OS.PostMessage(this.embeddedHwnd, 32770, wParam, lParam);
                }
                return 0L;
            }
            case 32772: {
                if (this.tray != null) {
                    for (final TrayItem item : this.tray.items) {
                        if (item != null && item.id == wParam) {
                            return item.messageProc(hwnd, (int)msg, wParam, lParam);
                        }
                    }
                }
                return 0L;
            }
            case 28: {
                if (wParam == 0L) {
                    break;
                }
                if (this.isXMouseActive()) {
                    break;
                }
                final long hwndActive = OS.GetActiveWindow();
                if (hwndActive != 0L && OS.IsWindowEnabled(hwndActive)) {
                    break;
                }
                final Shell modal = (this.modalDialog != null) ? this.modalDialog.parent : this.getModalShell();
                if (modal != null && !modal.isDisposed()) {
                    final long hwndModal = modal.handle;
                    if (OS.IsWindowEnabled(hwndModal)) {
                        modal.bringToTop();
                        if (modal.isDisposed()) {
                            break;
                        }
                    }
                    final long hwndPopup = OS.GetLastActivePopup(hwndModal);
                    if (hwndPopup != 0L && hwndPopup != modal.handle && this.getControl(hwndPopup) == null && OS.IsWindowEnabled(hwndPopup)) {
                        OS.SetActiveWindow(hwndPopup);
                    }
                    break;
                }
                break;
            }
            case 22: {
                if (wParam != 0L) {
                    this.dispose();
                    break;
                }
                break;
            }
            case 17: {
                final Event event = new Event();
                this.sendEvent(21, event);
                if (!event.doit) {
                    return 0L;
                }
                break;
            }
            case 26:
            case 800: {
                OS.SetTimer(this.hwndMessage, 100L, 2000, 0L);
                break;
            }
            case 794: {
                this.resetThemes();
                break;
            }
            case 275: {
                if (wParam == 100L) {
                    OS.KillTimer(this.hwndMessage, 100L);
                    this.runSettings();
                    break;
                }
                this.runTimer(wParam);
                break;
            }
            default: {
                if ((int)msg == Display.TASKBARCREATED && this.tray != null) {
                    for (final TrayItem item : this.tray.items) {
                        if (item != null) {
                            item.recreate();
                        }
                    }
                }
                if ((int)msg != Display.SWT_OPENDOC) {
                    break;
                }
                final String filename = this.getSharedData((int)wParam, (int)lParam);
                if (filename != null) {
                    if (filename.startsWith("/SWTINTERNAL_ID")) {
                        final String text = filename.substring("/SWTINTERNAL_ID".length());
                        final int id = Integer.parseInt(text);
                        final MenuItem item2 = this.getMenuItem(id);
                        if (item2 != null) {
                            item2.sendSelectionEvent(13);
                        }
                    }
                    else {
                        final Event event2 = new Event();
                        event2.text = filename;
                        try {
                            new URI(filename);
                            this.sendEvent(54, event2);
                        }
                        catch (URISyntaxException e) {
                            this.sendEvent(46, event2);
                        }
                    }
                    this.wakeThread();
                    break;
                }
                break;
            }
        }
        return OS.DefWindowProc(hwnd, (int)msg, wParam, lParam);
    }
    
    String getSharedData(final int pid, final int handle) {
        final long[] mapHandle = { 0L };
        if (pid == OS.GetCurrentProcessId()) {
            mapHandle[0] = handle;
        }
        else {
            final long processHandle = OS.OpenProcess(80, false, pid);
            if (processHandle == 0L) {
                return null;
            }
            OS.DuplicateHandle(processHandle, (long)handle, OS.GetCurrentProcess(), mapHandle, 2, false, 2);
            OS.CloseHandle(processHandle);
        }
        final long sharedData = OS.MapViewOfFile(mapHandle[0], 4, 0, 0, 0);
        if (sharedData == 0L) {
            return null;
        }
        final int length = OS.wcslen(sharedData);
        final TCHAR buffer = new TCHAR(0, length);
        final int byteCount = buffer.length() * 2;
        OS.MoveMemory(buffer, sharedData, byteCount);
        final String result = buffer.toString(0, length);
        OS.UnmapViewOfFile(sharedData);
        if (handle != mapHandle[0]) {
            OS.CloseHandle(mapHandle[0]);
        }
        return result;
    }
    
    long monitorEnumProc(final long hmonitor, final long hdc, final long lprcMonitor, final long dwData) {
        if (this.monitorCount >= this.monitors.length) {
            final Monitor[] newMonitors = new Monitor[this.monitors.length + 4];
            System.arraycopy(this.monitors, 0, newMonitors, 0, this.monitors.length);
            this.monitors = newMonitors;
        }
        this.monitors[this.monitorCount++] = this.getMonitor(hmonitor);
        return 1L;
    }
    
    long msgFilterProc(final long code, final long wParam, final long lParam) {
        switch ((int)code) {
            case 16896: {
                if (this.runDragDrop) {
                    break;
                }
                if (this.dragCancelled) {
                    break;
                }
                OS.MoveMemory(this.hookMsg, lParam, MSG.sizeof);
                if (this.hookMsg.message == 512) {
                    this.dragCancelled = true;
                    OS.SendMessage(this.hookMsg.hwnd, 31, 0L, 0L);
                    break;
                }
                break;
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8: {
                OS.MoveMemory(this.hookMsg, lParam, MSG.sizeof);
                if (this.hookMsg.message != 0) {
                    break;
                }
                final MSG msg = new MSG();
                final int flags = 10420226;
                if (!OS.PeekMessage(msg, 0L, 0, 0, 10420226) && this.runAsyncMessages(false)) {
                    this.wakeThread();
                    break;
                }
                break;
            }
        }
        return OS.CallNextHookEx(this.filterHook, (int)code, wParam, lParam);
    }
    
    int numpadKey(final int key) {
        switch (key) {
            case 96: {
                return 48;
            }
            case 97: {
                return 49;
            }
            case 98: {
                return 50;
            }
            case 99: {
                return 51;
            }
            case 100: {
                return 52;
            }
            case 101: {
                return 53;
            }
            case 102: {
                return 54;
            }
            case 103: {
                return 55;
            }
            case 104: {
                return 56;
            }
            case 105: {
                return 57;
            }
            case 106: {
                return 42;
            }
            case 107: {
                return 43;
            }
            case 108: {
                return 0;
            }
            case 109: {
                return 45;
            }
            case 110: {
                return 46;
            }
            case 111: {
                return 47;
            }
            default: {
                return 0;
            }
        }
    }
    
    public boolean post(final Event event) {
        synchronized (Device.class) {
            if (this.isDisposed()) {
                this.error(45);
            }
            if (event == null) {
                this.error(4);
            }
            final int type = event.type;
            switch (type) {
                case 1:
                case 2: {
                    final KEYBDINPUT inputs = new KEYBDINPUT();
                    inputs.wVk = (short)untranslateKey(event.keyCode);
                    if (inputs.wVk == 0) {
                        final char key = event.character;
                        switch (key) {
                            case '\b': {
                                inputs.wVk = 8;
                                break;
                            }
                            case '\r': {
                                inputs.wVk = 13;
                                break;
                            }
                            case '\u007f': {
                                inputs.wVk = 46;
                                break;
                            }
                            case '\u001b': {
                                inputs.wVk = 27;
                                break;
                            }
                            case '\t': {
                                inputs.wVk = 9;
                                break;
                            }
                            case '\n': {
                                return false;
                            }
                            default: {
                                inputs.wVk = OS.VkKeyScan((short)key);
                                if (inputs.wVk == -1) {
                                    return false;
                                }
                                final KEYBDINPUT keybdinput3;
                                final KEYBDINPUT keybdinput = keybdinput3 = inputs;
                                keybdinput3.wVk &= 0xFF;
                                break;
                            }
                        }
                    }
                    inputs.dwFlags = ((type == 2) ? 2 : 0);
                    switch (inputs.wVk) {
                        case 3:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 44:
                        case 45:
                        case 46:
                        case 111:
                        case 144: {
                            final KEYBDINPUT keybdinput4;
                            final KEYBDINPUT keybdinput2 = keybdinput4 = inputs;
                            keybdinput4.dwFlags |= 0x1;
                            break;
                        }
                    }
                    final INPUT pInputs = new INPUT();
                    pInputs.type = 1;
                    pInputs.ki = inputs;
                    return OS.SendInput(1, pInputs, INPUT.sizeof) != 0;
                }
                case 3:
                case 4:
                case 5:
                case 37: {
                    final MOUSEINPUT inputs2 = new MOUSEINPUT();
                    if (type == 5) {
                        inputs2.dwFlags = 49153;
                        final int x = OS.GetSystemMetrics(76);
                        final int y = OS.GetSystemMetrics(77);
                        final int width = OS.GetSystemMetrics(78);
                        final int height = OS.GetSystemMetrics(79);
                        final Point loc = event.getLocationInPixels();
                        inputs2.dx = ((loc.x - x) * 65535 + width - 2) / (width - 1);
                        inputs2.dy = ((loc.y - y) * 65535 + height - 2) / (height - 1);
                    }
                    else if (type == 37) {
                        inputs2.dwFlags = 2048;
                        switch (event.detail) {
                            case 2: {
                                inputs2.mouseData = event.count * 120;
                                break;
                            }
                            case 1: {
                                final int[] value = { 0 };
                                OS.SystemParametersInfo(104, 0, value, 0);
                                inputs2.mouseData = event.count * 120 / value[0];
                                break;
                            }
                            default: {
                                return false;
                            }
                        }
                    }
                    else {
                        switch (event.button) {
                            case 1: {
                                inputs2.dwFlags = ((type == 3) ? 2 : 4);
                                break;
                            }
                            case 2: {
                                inputs2.dwFlags = ((type == 3) ? 32 : 64);
                                break;
                            }
                            case 3: {
                                inputs2.dwFlags = ((type == 3) ? 8 : 16);
                                break;
                            }
                            case 4: {
                                inputs2.dwFlags = ((type == 3) ? 128 : 256);
                                inputs2.mouseData = 1;
                                break;
                            }
                            case 5: {
                                inputs2.dwFlags = ((type == 3) ? 128 : 256);
                                inputs2.mouseData = 2;
                                break;
                            }
                            default: {
                                return false;
                            }
                        }
                    }
                    final INPUT pInputs = new INPUT();
                    pInputs.type = 0;
                    pInputs.mi = inputs2;
                    return OS.SendInput(1, pInputs, INPUT.sizeof) != 0;
                }
                default: {
                    return false;
                }
            }
        }
    }
    
    void postEvent(final Event event) {
        if (this.eventQueue == null) {
            this.eventQueue = new Event[4];
        }
        int index;
        int length;
        for (index = 0, length = this.eventQueue.length; index < length && this.eventQueue[index] != null; ++index) {}
        if (index == length) {
            final Event[] newQueue = new Event[length + 4];
            System.arraycopy(this.eventQueue, 0, newQueue, 0, length);
            this.eventQueue = newQueue;
        }
        this.eventQueue[index] = event;
    }
    
    public boolean readAndDispatch() {
        this.checkDevice();
        Display.lpStartupInfo = null;
        this.drawMenuBars();
        this.runSkin();
        this.runDeferredLayouts();
        this.runPopups();
        if (OS.PeekMessage(this.msg, 0L, 0, 0, 1)) {
            if (!this.filterMessage(this.msg)) {
                OS.TranslateMessage(this.msg);
                OS.DispatchMessage(this.msg);
            }
            this.runDeferredEvents();
            return true;
        }
        return this.isDisposed() || this.runAsyncMessages(false);
    }
    
    static void register(final Display display) {
        synchronized (Device.class) {
            for (int i = 0; i < Display.Displays.length; ++i) {
                if (Display.Displays[i] == null) {
                    Display.Displays[i] = display;
                    return;
                }
            }
            final Display[] newDisplays = new Display[Display.Displays.length + 4];
            System.arraycopy(Display.Displays, 0, newDisplays, 0, Display.Displays.length);
            newDisplays[Display.Displays.length] = display;
            Display.Displays = newDisplays;
        }
    }
    
    protected void release() {
        try (final ExceptionStash exceptions = new ExceptionStash()) {
            try {
                this.sendEvent(12, new Event());
            }
            catch (Error | RuntimeException error) {
                final Throwable t2;
                final Throwable ex = t2;
                exceptions.stash(ex);
            }
            for (final Shell shell : this.getShells()) {
                try {
                    if (!shell.isDisposed()) {
                        shell.dispose();
                    }
                }
                catch (Error | RuntimeException error2) {
                    final Throwable t3;
                    final Throwable ex2 = t3;
                    exceptions.stash(ex2);
                }
            }
            try {
                if (this.tray != null) {
                    this.tray.dispose();
                }
            }
            catch (Error | RuntimeException error3) {
                final Throwable t4;
                final Throwable ex = t4;
                exceptions.stash(ex);
            }
            this.tray = null;
            try {
                if (this.taskBar != null) {
                    this.taskBar.dispose();
                }
            }
            catch (Error | RuntimeException error4) {
                final Throwable t5;
                final Throwable ex = t5;
                exceptions.stash(ex);
            }
            this.taskBar = null;
            while (true) {
                try {
                    while (this.readAndDispatch()) {}
                }
                catch (Error | RuntimeException error5) {
                    final Throwable t6;
                    final Throwable ex = t6;
                    exceptions.stash(ex);
                    continue;
                }
                break;
            }
            if (this.disposeList != null) {
                for (final Runnable next : this.disposeList) {
                    if (next != null) {
                        try {
                            next.run();
                        }
                        catch (Error | RuntimeException error6) {
                            final Throwable t7;
                            final Throwable ex2 = t7;
                            exceptions.stash(ex2);
                        }
                    }
                }
            }
            this.disposeList = null;
            this.synchronizer.releaseSynchronizer();
            this.synchronizer = null;
            this.releaseDisplay();
            super.release();
        }
    }
    
    void releaseDisplay() {
        if (this.embeddedHwnd != 0L) {
            OS.PostMessage(this.embeddedHwnd, 32771, 0L, 0L);
        }
        if (this.hIconSearch != 0L) {
            OS.DestroyIcon(this.hIconSearch);
        }
        if (this.hIconCancel != 0L) {
            OS.DestroyIcon(this.hIconCancel);
        }
        this.resetThemes();
        if (this.menuBarBorderPen != 0L) {
            OS.DeleteObject(this.menuBarBorderPen);
        }
        this.menuBarBorderPen = 0L;
        if (this.msgHook != 0L) {
            OS.UnhookWindowsHookEx(this.msgHook);
        }
        this.msgHook = 0L;
        if (this.filterHook != 0L) {
            OS.UnhookWindowsHookEx(this.filterHook);
        }
        this.filterHook = 0L;
        this.msgFilterCallback.dispose();
        this.msgFilterCallback = null;
        this.msgFilterProc = 0L;
        if (this.idleHook != 0L) {
            OS.UnhookWindowsHookEx(this.idleHook);
        }
        this.idleHook = 0L;
        this.foregroundIdleCallback.dispose();
        this.foregroundIdleCallback = null;
        this.foregroundIdleProc = 0L;
        OS.KillTimer(this.hwndMessage, 100L);
        if (this.hwndMessage != 0L) {
            OS.DestroyWindow(this.hwndMessage);
        }
        this.hwndMessage = 0L;
        this.messageCallback.dispose();
        this.messageCallback = null;
        this.messageProc = 0L;
        final long hHeap = OS.GetProcessHeap();
        final long hInstance = OS.GetModuleHandle((char[])null);
        OS.UnregisterClass(this.windowClass, hInstance);
        OS.UnregisterClass(this.windowShadowClass, hInstance);
        OS.UnregisterClass(this.windowOwnDCClass, hInstance);
        final TCHAR windowClass = null;
        this.windowOwnDCClass = windowClass;
        this.windowShadowClass = windowClass;
        this.windowClass = windowClass;
        this.windowCallback.dispose();
        this.windowCallback = null;
        this.windowProc = 0L;
        if (this.systemFont != null) {
            this.systemFont.dispose();
        }
        this.systemFont = null;
        this.lfSystemFont = null;
        if (this.errorImage != null) {
            this.errorImage.dispose();
        }
        if (this.infoImage != null) {
            this.infoImage.dispose();
        }
        if (this.questionImage != null) {
            this.questionImage.dispose();
        }
        if (this.warningIcon != null) {
            this.warningIcon.dispose();
        }
        final Image image = null;
        this.warningIcon = image;
        this.questionImage = image;
        this.infoImage = image;
        this.errorImage = image;
        for (final Cursor cursor : this.cursors) {
            if (cursor != null) {
                cursor.dispose();
            }
        }
        this.cursors = null;
        if (this.resources != null) {
            for (final Resource resource : this.resources) {
                if (resource != null) {
                    resource.dispose();
                }
            }
            this.resources = null;
        }
        if (this.lpCustColors != 0L) {
            OS.HeapFree(hHeap, 0, this.lpCustColors);
        }
        this.lpCustColors = 0L;
        OS.OleUninitialize();
        OS.BufferedPaintUnInit();
        this.thread = null;
        final MSG msg = null;
        this.hookMsg = msg;
        this.msg = msg;
        this.keyboard = null;
        this.modalDialog = null;
        this.modalShells = null;
        this.data = null;
        this.keys = null;
        this.values = null;
        final Menu[] array = null;
        this.popups = array;
        this.bars = array;
        this.indexTable = null;
        this.timerIds = null;
        this.controlTable = null;
        final Control lastControl = null;
        this.lastHittestControl = lastControl;
        this.lastGetControl = lastControl;
        this.lastControl = lastControl;
        final ImageList[] array2 = null;
        this.toolDisabledImageList = array2;
        this.toolHotImageList = array2;
        this.toolImageList = array2;
        this.imageList = array2;
        this.timerList = null;
        this.tableBuffer = null;
        final EventTable eventTable = null;
        this.filterTable = eventTable;
        this.eventTable = eventTable;
        this.items = null;
        this.clickRect = null;
        this.monitors = null;
        this.touchSources = null;
        this.threadId = 0;
    }
    
    void releaseImageList(final ImageList list) {
        int i = 0;
        int length = this.imageList.length;
        while (i < length) {
            if (this.imageList[i] == list) {
                if (list.removeRef() > 0) {
                    return;
                }
                list.dispose();
                System.arraycopy(this.imageList, i + 1, this.imageList, i, --length - i);
                this.imageList[length] = null;
                for (int j = 0; j < length; ++j) {
                    if (this.imageList[j] != null) {
                        return;
                    }
                }
                this.imageList = null;
            }
            else {
                ++i;
            }
        }
    }
    
    void releaseToolImageList(final ImageList list) {
        int i = 0;
        int length = this.toolImageList.length;
        while (i < length) {
            if (this.toolImageList[i] == list) {
                if (list.removeRef() > 0) {
                    return;
                }
                list.dispose();
                System.arraycopy(this.toolImageList, i + 1, this.toolImageList, i, --length - i);
                this.toolImageList[length] = null;
                for (int j = 0; j < length; ++j) {
                    if (this.toolImageList[j] != null) {
                        return;
                    }
                }
                this.toolImageList = null;
            }
            else {
                ++i;
            }
        }
    }
    
    void releaseToolHotImageList(final ImageList list) {
        int i = 0;
        int length = this.toolHotImageList.length;
        while (i < length) {
            if (this.toolHotImageList[i] == list) {
                if (list.removeRef() > 0) {
                    return;
                }
                list.dispose();
                System.arraycopy(this.toolHotImageList, i + 1, this.toolHotImageList, i, --length - i);
                this.toolHotImageList[length] = null;
                for (int j = 0; j < length; ++j) {
                    if (this.toolHotImageList[j] != null) {
                        return;
                    }
                }
                this.toolHotImageList = null;
            }
            else {
                ++i;
            }
        }
    }
    
    void releaseToolDisabledImageList(final ImageList list) {
        int i = 0;
        int length = this.toolDisabledImageList.length;
        while (i < length) {
            if (this.toolDisabledImageList[i] == list) {
                if (list.removeRef() > 0) {
                    return;
                }
                list.dispose();
                System.arraycopy(this.toolDisabledImageList, i + 1, this.toolDisabledImageList, i, --length - i);
                this.toolDisabledImageList[length] = null;
                for (int j = 0; j < length; ++j) {
                    if (this.toolDisabledImageList[j] != null) {
                        return;
                    }
                }
                this.toolDisabledImageList = null;
            }
            else {
                ++i;
            }
        }
    }
    
    public void removeFilter(final int eventType, final Listener listener) {
        this.checkDevice();
        if (listener == null) {
            this.error(4);
        }
        if (this.filterTable == null) {
            return;
        }
        this.filterTable.unhook(eventType, listener);
        if (this.filterTable.size() == 0) {
            this.filterTable = null;
        }
    }
    
    public void removeListener(final int eventType, final Listener listener) {
        this.checkDevice();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(eventType, listener);
    }
    
    void removeBar(final Menu menu) {
        if (this.bars == null) {
            return;
        }
        for (int i = 0; i < this.bars.length; ++i) {
            if (this.bars[i] == menu) {
                this.bars[i] = null;
                return;
            }
        }
    }
    
    Control removeControl(final long handle) {
        if (handle == 0L) {
            return null;
        }
        final Control control2 = null;
        this.lastGetControl = control2;
        this.lastControl = control2;
        Control control3 = null;
        final int index = (int)OS.RemoveProp(handle, (long)Display.SWT_OBJECT_INDEX) - 1;
        if (0 <= index && index < this.controlTable.length) {
            control3 = this.controlTable[index];
            this.controlTable[index] = null;
            this.indexTable[index] = this.freeSlot;
            this.freeSlot = index;
        }
        return control3;
    }
    
    void removeMenuItem(final MenuItem item) {
        if (this.items == null) {
            return;
        }
        this.items[item.id - 108] = null;
    }
    
    void removePopup(final Menu menu) {
        if (this.popups == null) {
            return;
        }
        for (int i = 0; i < this.popups.length; ++i) {
            if (this.popups[i] == menu) {
                this.popups[i] = null;
                return;
            }
        }
    }
    
    boolean runAsyncMessages(final boolean all) {
        return this.synchronizer.runAsyncMessages(all);
    }
    
    boolean runDeferredEvents() {
        boolean run = false;
        while (this.eventQueue != null) {
            final Event event = this.eventQueue[0];
            if (event == null) {
                break;
            }
            int length = this.eventQueue.length;
            System.arraycopy(this.eventQueue, 1, this.eventQueue, 0, --length);
            this.eventQueue[length] = null;
            final Widget widget = event.widget;
            if (widget == null) {
                continue;
            }
            if (widget.isDisposed()) {
                continue;
            }
            final Widget item = event.item;
            if (item != null && item.isDisposed()) {
                continue;
            }
            run = true;
            widget.sendEvent(event);
        }
        this.eventQueue = null;
        return run;
    }
    
    boolean runDeferredLayouts() {
        if (this.layoutDeferredCount != 0) {
            final Composite[] temp = this.layoutDeferred;
            final int count = this.layoutDeferredCount;
            this.layoutDeferred = null;
            this.layoutDeferredCount = 0;
            for (final Composite comp : temp) {
                if (!comp.isDisposed()) {
                    comp.setLayoutDeferred(false);
                }
            }
            return true;
        }
        return false;
    }
    
    boolean runPopups() {
        if (this.popups == null) {
            return false;
        }
        boolean result = false;
        while (this.popups != null) {
            final Menu menu = this.popups[0];
            if (menu == null) {
                break;
            }
            int length = this.popups.length;
            System.arraycopy(this.popups, 1, this.popups, 0, --length);
            this.popups[length] = null;
            this.runDeferredEvents();
            if (!menu.isDisposed()) {
                menu._setVisible(true);
            }
            result = true;
        }
        this.popups = null;
        return result;
    }
    
    void runSettings() {
        final Font oldFont = this.getSystemFont();
        this.saveResources();
        this.sendEvent(39, null);
        final Font newFont = this.getSystemFont();
        final boolean sameFont = oldFont.equals((Object)newFont);
        for (final Shell shell : this.getShells()) {
            if (!shell.isDisposed()) {
                if (!sameFont) {
                    shell.updateFont(oldFont, newFont);
                }
                shell.layout(true, true);
            }
        }
    }
    
    boolean runSkin() {
        if (this.skinCount > 0) {
            final Widget[] oldSkinWidgets = this.skinList;
            final int count = this.skinCount;
            this.skinList = new Widget[1024];
            this.skinCount = 0;
            if (this.eventTable != null && this.eventTable.hooks(45)) {
                for (int i = 0; i < count; ++i) {
                    final Widget widget = oldSkinWidgets[i];
                    if (widget != null && !widget.isDisposed()) {
                        final Widget widget3;
                        final Widget widget2 = widget3 = widget;
                        widget3.state &= 0xFFDFFFFF;
                        oldSkinWidgets[i] = null;
                        final Event event = new Event();
                        event.widget = widget;
                        this.sendEvent(45, event);
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    boolean runTimer(final long id) {
        if (this.timerList != null && this.timerIds != null) {
            for (int index = 0; index < this.timerIds.length; ++index) {
                if (this.timerIds[index] == id) {
                    OS.KillTimer(this.hwndMessage, this.timerIds[index]);
                    this.timerIds[index] = 0L;
                    final Runnable runnable = this.timerList[index];
                    this.timerList[index] = null;
                    if (runnable != null) {
                        try {
                            runnable.run();
                        }
                        catch (RuntimeException exception) {
                            this.runtimeExceptionHandler.accept(exception);
                        }
                        catch (Error exception2) {
                            this.errorHandler.accept(exception2);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    void saveResources() {
        int resourceCount = 0;
        if (this.resources == null) {
            this.resources = new Resource[27];
        }
        else {
            resourceCount = this.resources.length;
            final Resource[] newResources = new Resource[resourceCount + 27];
            System.arraycopy(this.resources, 0, newResources, 0, resourceCount);
            this.resources = newResources;
        }
        if (this.systemFont != null) {
            final NONCLIENTMETRICS info = new NONCLIENTMETRICS();
            info.cbSize = NONCLIENTMETRICS.sizeof;
            if (OS.SystemParametersInfo(41, 0, info, 0)) {
                final LOGFONT logFont = info.lfMessageFont;
                if (this.lfSystemFont == null || logFont.lfCharSet != this.lfSystemFont.lfCharSet || logFont.lfHeight != this.lfSystemFont.lfHeight || logFont.lfWidth != this.lfSystemFont.lfWidth || logFont.lfEscapement != this.lfSystemFont.lfEscapement || logFont.lfOrientation != this.lfSystemFont.lfOrientation || logFont.lfWeight != this.lfSystemFont.lfWeight || logFont.lfItalic != this.lfSystemFont.lfItalic || logFont.lfUnderline != this.lfSystemFont.lfUnderline || logFont.lfStrikeOut != this.lfSystemFont.lfStrikeOut || logFont.lfCharSet != this.lfSystemFont.lfCharSet || logFont.lfOutPrecision != this.lfSystemFont.lfOutPrecision || logFont.lfClipPrecision != this.lfSystemFont.lfClipPrecision || logFont.lfQuality != this.lfSystemFont.lfQuality || logFont.lfPitchAndFamily != this.lfSystemFont.lfPitchAndFamily || !this.getFontName(logFont).equals(this.getFontName(this.lfSystemFont))) {
                    this.resources[resourceCount++] = (Resource)this.systemFont;
                    this.lfSystemFont = logFont;
                    this.systemFont = null;
                }
            }
        }
        if (this.errorImage != null) {
            this.resources[resourceCount++] = (Resource)this.errorImage;
        }
        if (this.infoImage != null) {
            this.resources[resourceCount++] = (Resource)this.infoImage;
        }
        if (this.questionImage != null) {
            this.resources[resourceCount++] = (Resource)this.questionImage;
        }
        if (this.warningIcon != null) {
            this.resources[resourceCount++] = (Resource)this.warningIcon;
        }
        final Image image = null;
        this.warningIcon = image;
        this.questionImage = image;
        this.infoImage = image;
        this.errorImage = image;
        for (int i = 0; i < this.cursors.length; ++i) {
            if (this.cursors[i] != null) {
                this.resources[resourceCount++] = (Resource)this.cursors[i];
            }
            this.cursors[i] = null;
        }
        if (resourceCount < 27) {
            final Resource[] newResources2 = new Resource[resourceCount];
            System.arraycopy(this.resources, 0, newResources2, 0, resourceCount);
            this.resources = newResources2;
        }
    }
    
    void sendEvent(final int eventType, Event event) {
        if (this.eventTable == null && this.filterTable == null) {
            return;
        }
        if (event == null) {
            event = new Event();
        }
        event.display = this;
        event.type = eventType;
        if (event.time == 0) {
            event.time = this.getLastEventTime();
        }
        if (!this.filterEvent(event) && this.eventTable != null) {
            this.sendEvent(this.eventTable, event);
        }
    }
    
    void sendEvent(final EventTable eventTable, final Event event) {
        final int type = event.type;
        this.sendPreEvent(type);
        try {
            eventTable.sendEvent(event);
        }
        finally {
            this.sendPostEvent(type);
        }
    }
    
    void sendPreEvent(final int eventType) {
        if (eventType != 50 && eventType != 51 && eventType != 52 && eventType != 53 && this.eventTable != null && this.eventTable.hooks(50)) {
            final Event event = new Event();
            event.detail = eventType;
            this.sendEvent(50, event);
        }
    }
    
    void sendPostEvent(final int eventType) {
        if (eventType != 50 && eventType != 51 && eventType != 52 && eventType != 53 && this.eventTable != null && this.eventTable.hooks(51)) {
            final Event event = new Event();
            event.detail = eventType;
            this.sendEvent(51, event);
        }
    }
    
    public void sendPreExternalEventDispatchEvent() {
        if (this.eventTable != null && this.eventTable.hooks(52)) {
            this.sendEvent(52, null);
        }
    }
    
    public void sendPostExternalEventDispatchEvent() {
        if (this.eventTable != null && this.eventTable.hooks(53)) {
            this.sendEvent(53, null);
        }
    }
    
    public void setCursorLocation(final int x, final int y) {
        this.checkDevice();
        this.setCursorLocationInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y));
    }
    
    void setCursorLocationInPixels(final int x, final int y) {
        OS.SetCursorPos(x, y);
    }
    
    public void setCursorLocation(final Point point) {
        this.checkDevice();
        if (point == null) {
            this.error(4);
        }
        this.setCursorLocation(point.x, point.y);
    }
    
    boolean _toBoolean(final Object value) {
        return value != null && (boolean)value;
    }
    
    int _toColorPixel(final Object value) {
        if (value == null) {
            return -1;
        }
        return ((Color)value).handle;
    }
    
    public void setData(final String key, final Object value) {
        this.checkDevice();
        if (key == null) {
            this.error(4);
        }
        switch (key) {
            case "org.eclipse.swt.internal.win32.runMessagesInIdle": {
                this.runMessagesInIdle = this._toBoolean(value);
                return;
            }
            case "org.eclipse.swt.internal.win32.runMessagesInMessageProc": {
                this.runMessagesInMessageProc = this._toBoolean(value);
                return;
            }
            case "org.eclipse.swt.internal.win32.useOwnDC": {
                this.useOwnDC = this._toBoolean(value);
                return;
            }
            case "org.eclipse.swt.internal.win32.accelKeyHit": {
                this.accelKeyHit = this._toBoolean(value);
                return;
            }
            case "org.eclipse.swt.internal.win32.externalEventLoop": {
                this.externalEventLoop = this._toBoolean(value);
                return;
            }
            case "org.eclipse.swt.internal.win32.useDarkModeExplorerTheme": {
                this.useDarkModeExplorerTheme = (this._toBoolean(value) && !Display.disableCustomThemeTweaks && OS.IsDarkModeAvailable());
                final int PreferredAppMode_Default = 0;
                final int PreferredAppMode_ForceDark = 2;
                if (this.useDarkModeExplorerTheme) {
                    OS.SetPreferredAppMode(2);
                }
                else {
                    OS.SetPreferredAppMode(0);
                }
                return;
            }
            case "org.eclipse.swt.internal.win32.useShellTitleColoring": {
                this.useShellTitleColoring = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.menuBarForegroundColor": {
                this.menuBarForegroundPixel = (Display.disableCustomThemeTweaks ? -1 : this._toColorPixel(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.menuBarBackgroundColor": {
                this.menuBarBackgroundPixel = (Display.disableCustomThemeTweaks ? -1 : this._toColorPixel(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.menuBarBorderColor": {
                if (this.menuBarBorderPen != 0L) {
                    OS.DeleteObject(this.menuBarBorderPen);
                }
                final int pixel = this._toColorPixel(value);
                if (Display.disableCustomThemeTweaks || pixel == -1) {
                    this.menuBarBorderPen = 0L;
                }
                else {
                    this.menuBarBorderPen = OS.CreatePen(0, 1, pixel);
                }
                return;
            }
            case "org.eclipse.swt.internal.win32.all.use_WS_BORDER": {
                this.useWsBorderAll = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.Canvas.use_WS_BORDER": {
                this.useWsBorderCanvas = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.Label.use_WS_BORDER": {
                this.useWsBorderLabel = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.List.use_WS_BORDER": {
                this.useWsBorderList = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.Table.use_WS_BORDER": {
                this.useWsBorderTable = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.Text.use_WS_BORDER": {
                this.useWsBorderText = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.Table.headerLineColor": {
                this.tableHeaderLinePixel = (Display.disableCustomThemeTweaks ? -1 : this._toColorPixel(value));
                return;
            }
            case "org.eclipse.swt.internal.win32.Label.disabledForegroundColor": {
                this.disabledLabelForegroundPixel = (Display.disableCustomThemeTweaks ? -1 : this._toColorPixel(value));
                break;
            }
            case "org.eclipse.swt.internal.win32.Combo.useDarkTheme": {
                this.comboUseDarkTheme = (this._toBoolean(value) && !Display.disableCustomThemeTweaks && OS.IsDarkModeAvailable());
                break;
            }
            case "org.eclipse.swt.internal.win32.ProgressBar.useColors": {
                this.progressbarUseColors = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                break;
            }
            case "org.eclipse.swt.internal.win32.Text.useDarkThemeIcons": {
                this.textUseDarkthemeIcons = (!Display.disableCustomThemeTweaks && this._toBoolean(value));
                break;
            }
        }
        if (value == null) {
            if (this.keys == null) {
                return;
            }
            int index;
            for (index = 0; index < this.keys.length && !this.keys[index].equals(key); ++index) {}
            if (index == this.keys.length) {
                return;
            }
            if (this.keys.length == 1) {
                this.keys = null;
                this.values = null;
            }
            else {
                final String[] newKeys = new String[this.keys.length - 1];
                final Object[] newValues = new Object[this.values.length - 1];
                System.arraycopy(this.keys, 0, newKeys, 0, index);
                System.arraycopy(this.keys, index + 1, newKeys, index, newKeys.length - index);
                System.arraycopy(this.values, 0, newValues, 0, index);
                System.arraycopy(this.values, index + 1, newValues, index, newValues.length - index);
                this.keys = newKeys;
                this.values = newValues;
            }
        }
        else {
            if (this.keys == null) {
                this.keys = new String[] { key };
                this.values = new Object[] { value };
                return;
            }
            for (int i = 0; i < this.keys.length; ++i) {
                if (this.keys[i].equals(key)) {
                    this.values[i] = value;
                    return;
                }
            }
            final String[] newKeys2 = new String[this.keys.length + 1];
            final Object[] newValues2 = new Object[this.values.length + 1];
            System.arraycopy(this.keys, 0, newKeys2, 0, this.keys.length);
            System.arraycopy(this.values, 0, newValues2, 0, this.values.length);
            newKeys2[this.keys.length] = key;
            newValues2[this.values.length] = value;
            this.keys = newKeys2;
            this.values = newValues2;
        }
    }
    
    public void setData(final Object data) {
        this.checkDevice();
        this.data = data;
    }
    
    public static String getAppName() {
        return Display.APP_NAME;
    }
    
    public static String getAppVersion() {
        return Display.APP_VERSION;
    }
    
    public static void setAppName(final String name) {
        Display.APP_NAME = name;
    }
    
    public static void setAppVersion(final String version) {
        Display.APP_VERSION = version;
    }
    
    void setModalDialog(final Dialog modalDailog) {
        this.modalDialog = modalDailog;
        for (final Shell shell : this.getShells()) {
            shell.updateModal();
        }
    }
    
    void setModalShell(final Shell shell) {
        if (this.modalShells == null) {
            this.modalShells = new Shell[4];
        }
        int index;
        int length;
        for (index = 0, length = this.modalShells.length; index < length; ++index) {
            if (this.modalShells[index] == shell) {
                return;
            }
            if (this.modalShells[index] == null) {
                break;
            }
        }
        if (index == length) {
            final Shell[] newModalShells = new Shell[length + 4];
            System.arraycopy(this.modalShells, 0, newModalShells, 0, length);
            this.modalShells = newModalShells;
        }
        this.modalShells[index] = shell;
        for (final Shell activeShell : this.getShells()) {
            activeShell.updateModal();
        }
    }
    
    public void setSynchronizer(final Synchronizer synchronizer) {
        this.checkDevice();
        if (synchronizer == null) {
            this.error(4);
        }
        if (synchronizer == this.synchronizer) {
            return;
        }
        final Synchronizer oldSynchronizer;
        synchronized (Device.class) {
            oldSynchronizer = this.synchronizer;
            this.synchronizer = synchronizer;
        }
        if (oldSynchronizer != null) {
            oldSynchronizer.moveAllEventsTo(synchronizer);
        }
    }
    
    public final void setRuntimeExceptionHandler(final Consumer<RuntimeException> runtimeExceptionHandler) {
        this.checkDevice();
        this.runtimeExceptionHandler = Objects.requireNonNull(runtimeExceptionHandler);
    }
    
    public final Consumer<RuntimeException> getRuntimeExceptionHandler() {
        return this.runtimeExceptionHandler;
    }
    
    public final void setErrorHandler(final Consumer<Error> errorHandler) {
        this.checkDevice();
        this.errorHandler = Objects.requireNonNull(errorHandler);
    }
    
    public final Consumer<Error> getErrorHandler() {
        return this.errorHandler;
    }
    
    int shiftedKey(final int key) {
        for (int i = 0; i < this.keyboard.length; ++i) {
            this.keyboard[i] = 0;
        }
        final byte[] keyboard = this.keyboard;
        final int n = 16;
        final byte[] array = keyboard;
        final int n2 = 16;
        array[n2] |= 0xFFFFFF80;
        final char[] result = { '\0' };
        if (OS.ToUnicode(key, key, this.keyboard, result, 1, 0) == 1) {
            return result[0];
        }
        return 0;
    }
    
    public boolean sleep() {
        this.checkDevice();
        if (!this.synchronizer.isMessagesEmpty()) {
            return true;
        }
        this.sendPreExternalEventDispatchEvent();
        final boolean result = OS.WaitMessage();
        this.sendPostExternalEventDispatchEvent();
        return result;
    }
    
    public void syncExec(final Runnable runnable) {
        final Synchronizer synchronizer;
        synchronized (Device.class) {
            if (this.isDisposed()) {
                this.error(45);
            }
            synchronizer = this.synchronizer;
        }
        synchronizer.syncExec(runnable);
    }
    
    public <T, E extends Exception> T syncCall(final SwtCallable<T, E> callable) throws E, Exception {
        Objects.nonNull(callable);
        final T[] t = (T[])new Object[] { null };
        final Object[] ex = { null };
        final Object o;
        final Object o2;
        this.syncExec(() -> {
            try {
                o[0] = callable.call();
            }
            catch (Exception e2) {
                o2[0] = e2;
            }
            return;
        });
        if (ex[0] != null) {
            final E e3 = (E)ex[0];
            throw e3;
        }
        return t[0];
    }
    
    public void timerExec(final int milliseconds, final Runnable runnable) {
        this.checkDevice();
        if (runnable == null) {
            this.error(4);
        }
        if (this.timerList == null) {
            this.timerList = new Runnable[4];
        }
        if (this.timerIds == null) {
            this.timerIds = new long[4];
        }
        int index;
        for (index = 0; index < this.timerList.length && this.timerList[index] != runnable; ++index) {}
        long timerId = 0L;
        if (index != this.timerList.length) {
            timerId = this.timerIds[index];
            if (milliseconds < 0) {
                OS.KillTimer(this.hwndMessage, timerId);
                this.timerList[index] = null;
                this.timerIds[index] = 0L;
                return;
            }
        }
        else {
            if (milliseconds < 0) {
                return;
            }
            for (index = 0; index < this.timerList.length && this.timerList[index] != null; ++index) {}
            timerId = this.nextTimerId++;
            if (index == this.timerList.length) {
                final Runnable[] newTimerList = new Runnable[this.timerList.length + 4];
                System.arraycopy(this.timerList, 0, newTimerList, 0, this.timerList.length);
                this.timerList = newTimerList;
                final long[] newTimerIds = new long[this.timerIds.length + 4];
                System.arraycopy(this.timerIds, 0, newTimerIds, 0, this.timerIds.length);
                this.timerIds = newTimerIds;
            }
        }
        final long newTimerID = OS.SetTimer(this.hwndMessage, timerId, milliseconds, 0L);
        if (newTimerID != 0L) {
            this.timerList[index] = runnable;
            this.timerIds[index] = newTimerID;
        }
    }
    
    boolean translateAccelerator(final MSG msg, final Control control) {
        this.accelKeyHit = true;
        final boolean result = control.translateAccelerator(msg);
        this.accelKeyHit = false;
        return result;
    }
    
    static int translateKey(final int key) {
        for (final int[] element : Display.KeyTable) {
            if (element[0] == key) {
                return element[1];
            }
        }
        return 0;
    }
    
    boolean translateMnemonic(final MSG msg, final Control control) {
        switch (msg.message) {
            case 258:
            case 262: {
                return control.translateMnemonic(msg);
            }
            default: {
                return false;
            }
        }
    }
    
    boolean translateTraversal(final MSG msg, final Control control) {
        Label_0221: {
            switch (msg.message) {
                case 256: {
                    switch ((int)msg.wParam) {
                        case 9:
                        case 13:
                        case 27:
                        case 33:
                        case 34:
                        case 37:
                        case 38:
                        case 39:
                        case 40: {
                            return control.translateTraversal(msg);
                        }
                        default: {
                            break Label_0221;
                        }
                    }
                    break;
                }
                case 260: {
                    switch ((int)msg.wParam) {
                        case 18: {
                            return control.translateTraversal(msg);
                        }
                        default: {
                            break Label_0221;
                        }
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    static int untranslateKey(final int key) {
        for (final int[] element : Display.KeyTable) {
            if (element[1] == key) {
                return element[0];
            }
        }
        return 0;
    }
    
    public void update() {
        this.checkDevice();
        if (OS.IsHungAppWindow(this.hwndMessage)) {
            final MSG msg = new MSG();
            final int flags = 3;
            OS.PeekMessage(msg, this.hwndMessage, 32773, 32773, 3);
        }
        for (final Shell shell : this.getShells()) {
            if (!shell.isDisposed()) {
                shell.update(true);
            }
        }
    }
    
    public void wake() {
        synchronized (Device.class) {
            if (this.isDisposed()) {
                this.error(45);
            }
            if (this.thread == Thread.currentThread()) {
                return;
            }
            this.wakeThread();
        }
    }
    
    void wakeThread() {
        OS.PostThreadMessage(this.threadId, 0, 0L, 0L);
    }
    
    long windowProc(final long hwnd, final long msg, final long wParam, final long lParam) {
        if (this.lastControl != null && this.lastHwnd == hwnd) {
            return this.lastControl.windowProc(hwnd, (int)msg, wParam, lParam);
        }
        final int index = (int)OS.GetProp(hwnd, (long)Display.SWT_OBJECT_INDEX) - 1;
        if (0 <= index && index < this.controlTable.length) {
            final Control control = this.controlTable[index];
            if (control != null) {
                this.lastHwnd = hwnd;
                this.lastControl = control;
                return control.windowProc(hwnd, (int)msg, wParam, lParam);
            }
        }
        return OS.DefWindowProc(hwnd, (int)msg, wParam, lParam);
    }
    
    int textWidth(final String text, final long handle) {
        long oldFont = 0L;
        final RECT rect = new RECT();
        final long hDC = OS.GetDC(handle);
        final long newFont = OS.SendMessage(handle, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        final int flags = 3104;
        final char[] buffer = text.toCharArray();
        OS.DrawText(hDC, buffer, buffer.length, rect, 3104);
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(handle, hDC);
        return rect.right - rect.left;
    }
    
    String wrapText(String text, final long handle, final int width) {
        final String Lf = "\r\n";
        text = withCrLf(text);
        final int length = text.length();
        if (width <= 0 || length == 0 || length == 1) {
            return text;
        }
        final StringBuilder result = new StringBuilder();
        int lineStart = 0;
        int lineEnd = 0;
        while (lineStart < length) {
            lineEnd = text.indexOf("\r\n", lineStart);
            final boolean noLf = lineEnd == -1;
            if (noLf) {
                lineEnd = length;
            }
            final int nextStart = lineEnd + "\r\n".length();
            while (lineEnd > lineStart + 1 && Character.isWhitespace(text.charAt(lineEnd - 1))) {
                --lineEnd;
            }
            int wordStart = lineStart;
            int wordEnd = lineStart;
            for (int i = lineStart; i < lineEnd; i = wordStart, lineStart = wordStart, wordEnd = wordStart) {
                final int lastStart = wordStart;
                int lastEnd = wordEnd;
                wordStart = i;
                while (i < lineEnd && !Character.isWhitespace(text.charAt(i))) {
                    ++i;
                }
                wordEnd = i - 1;
                String line = text.substring(lineStart, wordEnd + 1);
                int lineWidth = this.textWidth(line, handle);
                while (i < lineEnd && Character.isWhitespace(text.charAt(i))) {
                    ++i;
                }
                if (lineWidth > width) {
                    if (lastStart == wordStart) {
                        while (wordStart < wordEnd) {
                            line = text.substring(lineStart, wordStart + 1);
                            lineWidth = this.textWidth(line, handle);
                            if (lineWidth >= width) {
                                break;
                            }
                            ++wordStart;
                        }
                        if (wordStart == lastStart) {
                            ++wordStart;
                        }
                        lastEnd = wordStart - 1;
                    }
                    line = text.substring(lineStart, lastEnd + 1);
                    result.append(line);
                    result.append("\r\n");
                }
            }
            if (lineStart < lineEnd) {
                result.append(text.substring(lineStart, lineEnd));
            }
            if (!noLf) {
                result.append("\r\n");
            }
            lineStart = nextStart;
        }
        return result.toString();
    }
    
    static String withCrLf(final String string) {
        final int length = string.length();
        if (length == 0) {
            return string;
        }
        int i = string.indexOf(10, 0);
        if (i == -1) {
            return string;
        }
        if (i > 0 && string.charAt(i - 1) == '\r') {
            return string;
        }
        ++i;
        int count = 1;
        while (i < length && (i = string.indexOf(10, i)) != -1) {
            ++count;
            ++i;
        }
        count += length;
        i = 0;
        final StringBuilder result = new StringBuilder(count);
        while (i < length) {
            int j = string.indexOf(10, i);
            if (j == -1) {
                j = length;
            }
            result.append(string.substring(i, j));
            if ((i = j) < length) {
                result.append("\r\n");
                ++i;
            }
        }
        return result.toString();
    }
    
    static char[] withCrLf(final char[] string) {
        final int length = string.length;
        if (length == 0) {
            return string;
        }
        int count = 0;
        for (int i = 0; i < string.length; ++i) {
            if (string[i] == '\n' && ++count == 1 && i > 0 && string[i - 1] == '\r') {
                return string;
            }
        }
        if (count == 0) {
            return string;
        }
        count += length;
        final char[] result = new char[count];
        for (int j = 0, k = 0; j < length && k < count; result[k++] = string[j], ++j) {
            if (string[j] == '\n') {
                result[k++] = '\r';
            }
        }
        return result;
    }
    
    static {
        Display.APP_NAME = "SWT";
        Display.APP_VERSION = "";
        SWT_OBJECT_INDEX = OS.GlobalAddAtom(new TCHAR(0, "SWT_OBJECT_INDEX", true));
        Display.lpStartupInfo = new STARTUPINFO();
        Display.lpStartupInfo.cb = STARTUPINFO.sizeof;
        OS.GetStartupInfo(Display.lpStartupInfo);
        EXPLORER = new char[] { 'E', 'X', 'P', 'L', 'O', 'R', 'E', 'R', '\0' };
        TREEVIEW = new char[] { 'T', 'R', 'E', 'E', 'V', 'I', 'E', 'W', '\0' };
        disableCustomThemeTweaks = Boolean.valueOf(System.getProperty("org.eclipse.swt.internal.win32.disableCustomThemeTweaks"));
        ACCENTS = new short[] { 126, 96, 39, 94, 34 };
        KeyTable = new int[][] { { 18, 65536 }, { 16, 131072 }, { 17, 262144 }, { 38, 16777217 }, { 40, 16777218 }, { 37, 16777219 }, { 39, 16777220 }, { 33, 16777221 }, { 34, 16777222 }, { 36, 16777223 }, { 35, 16777224 }, { 45, 16777225 }, { 8, 8 }, { 13, 13 }, { 46, 127 }, { 27, 27 }, { 13, 10 }, { 9, 9 }, { 112, 16777226 }, { 113, 16777227 }, { 114, 16777228 }, { 115, 16777229 }, { 116, 16777230 }, { 117, 16777231 }, { 118, 16777232 }, { 119, 16777233 }, { 120, 16777234 }, { 121, 16777235 }, { 122, 16777236 }, { 123, 16777237 }, { 124, 16777238 }, { 125, 16777239 }, { 126, 16777240 }, { 127, 16777241 }, { 128, 16777242 }, { 129, 16777243 }, { 130, 16777244 }, { 131, 16777245 }, { 106, 16777258 }, { 107, 16777259 }, { 13, 16777296 }, { 109, 16777261 }, { 110, 16777262 }, { 111, 16777263 }, { 96, 16777264 }, { 97, 16777265 }, { 98, 16777266 }, { 99, 16777267 }, { 100, 16777268 }, { 101, 16777269 }, { 102, 16777270 }, { 103, 16777271 }, { 104, 16777272 }, { 105, 16777273 }, { 20, 16777298 }, { 144, 16777299 }, { 145, 16777300 }, { 19, 16777301 }, { 3, 16777302 }, { 44, 16777303 } };
        Display.Displays = new Display[1];
        Display.TrimEnabled = false;
        Display device;
        Display.DeviceFinder = (() -> {
            device = getCurrent();
            if (device == null) {
                device = getDefault();
            }
            setDevice(device);
        });
    }
}
