//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.ole.win32.*;
import java.util.*;
import org.eclipse.swt.widgets.*;

public final class OleFrame extends Composite
{
    private COMObject iOleInPlaceFrame;
    private IOleInPlaceActiveObject objIOleInPlaceActiveObject;
    private OleClientSite currentdoc;
    private int refCount;
    private MenuItem[] fileMenuItems;
    private MenuItem[] containerMenuItems;
    private MenuItem[] windowMenuItems;
    private Listener listener;
    private long shellHandle;
    private long oldMenuHandle;
    private long newMenuHandle;
    private static long lastActivatedMenuHandle;
    private static String CHECK_FOCUS;
    private static String HHOOK;
    private static String HHOOKMSG;
    private static boolean ignoreNextKey;
    private static final short[] ACCENTS;
    private static final String CONSUME_KEY = "org.eclipse.swt.OleFrame.ConsumeKey";
    private static final String ACCEL_KEY_HIT = "org.eclipse.swt.internal.win32.accelKeyHit";
    
    public OleFrame(final Composite parent, final int style) {
        super(parent, style);
        this.refCount = 0;
        this.createCOMInterfaces();
        this.addListener(26, this.listener = (e -> {
            switch (e.type) {
                case 26: {
                    this.onActivate(e);
                    break;
                }
                case 27: {
                    this.onDeactivate(e);
                    break;
                }
                case 12: {
                    this.onDispose(e);
                    break;
                }
                case 10:
                case 11: {
                    this.onResize(e);
                    break;
                }
                default: {
                    OLE.error(20);
                    break;
                }
            }
            return;
        }));
        this.addListener(27, this.listener);
        this.addListener(12, this.listener);
        this.addListener(11, this.listener);
        this.addListener(10, this.listener);
        this.AddRef();
        final Display display = this.getDisplay();
        initCheckFocus(display);
        initMsgHook(display);
    }
    
    private static void initCheckFocus(final Display display) {
        if (display.getData(OleFrame.CHECK_FOCUS) != null) {
            return;
        }
        display.setData(OleFrame.CHECK_FOCUS, OleFrame.CHECK_FOCUS);
        final int time = 50;
        final Runnable[] timer = { null };
        final Control[] lastFocus = { null };
        final Object o;
        long hwnd;
        long ownerHwnd;
        final Object o2;
        Control currentFocus;
        OleFrame frame;
        Event event;
        display.timerExec(50, timer[0] = (() -> {
            if (o[0] instanceof OleClientSite && !o[0].isDisposed()) {
                hwnd = OS.GetFocus();
                while (hwnd != 0L) {
                    ownerHwnd = OS.GetWindow(hwnd, 4);
                    if (ownerHwnd != 0L) {
                        display.timerExec(50, o2[0]);
                        return;
                    }
                    else {
                        hwnd = OS.GetParent(hwnd);
                    }
                }
            }
            if (o[0] == null || o[0].isDisposed() || !o[0].isFocusControl()) {
                currentFocus = display.getFocusControl();
                if (currentFocus instanceof OleFrame) {
                    frame = (OleFrame)currentFocus;
                    currentFocus = (Control)frame.getCurrentDocument();
                }
                if (o[0] != currentFocus) {
                    event = new Event();
                    if (o[0] instanceof OleClientSite && !o[0].isDisposed()) {
                        o[0].notifyListeners(16, event);
                    }
                    if (currentFocus instanceof OleClientSite && !currentFocus.isDisposed()) {
                        currentFocus.notifyListeners(15, event);
                    }
                }
                o[0] = currentFocus;
            }
            display.timerExec(50, o2[0]);
        }));
    }
    
    private static void initMsgHook(final Display display) {
        if (display.getData(OleFrame.HHOOK) != null) {
            return;
        }
        final Callback callback = new Callback((Object)OleFrame.class, "getMsgProc", 3);
        final long address = callback.getAddress();
        final int threadId = OS.GetCurrentThreadId();
        final long hHook = OS.SetWindowsHookEx(3, address, 0L, threadId);
        if (hHook == 0L) {
            callback.dispose();
            return;
        }
        display.setData(OleFrame.HHOOK, new LONG(hHook));
        display.setData(OleFrame.HHOOKMSG, new MSG());
        final Object o;
        final Callback callback2;
        display.disposeExec(() -> {
            if (o != 0L) {
                OS.UnhookWindowsHookEx((long)o);
            }
            if (callback2 != null) {
                callback2.dispose();
            }
        });
    }
    
    static long getMsgProc(final long code, final long wParam, final long lParam) {
        final Display display = Display.getCurrent();
        if (display == null) {
            return 0L;
        }
        final LONG hHook = (LONG)display.getData(OleFrame.HHOOK);
        if (hHook == null) {
            return 0L;
        }
        if (code < 0L || (wParam & 0x1L) == 0x0L) {
            return OS.CallNextHookEx(hHook.value, (int)code, wParam, lParam);
        }
        final MSG msg = (MSG)display.getData(OleFrame.HHOOKMSG);
        OS.MoveMemory(msg, lParam, MSG.sizeof);
        final int message = msg.message;
        if (256 <= message && message <= 264 && display != null) {
            Widget widget = null;
            long hwnd;
            for (hwnd = msg.hwnd; hwnd != 0L; hwnd = OS.GetParent(hwnd)) {
                widget = display.findWidget(hwnd);
                if (widget != null) {
                    break;
                }
            }
            if (widget instanceof OleClientSite) {
                final OleClientSite site = (OleClientSite)widget;
                if (site.handle == hwnd) {
                    boolean consumed = false;
                    final int thread = OS.GetWindowThreadProcessId(msg.hwnd, (int[])null);
                    final GUITHREADINFO lpgui = new GUITHREADINFO();
                    lpgui.cbSize = GUITHREADINFO.sizeof;
                    final boolean rc = OS.GetGUIThreadInfo(thread, lpgui);
                    final int mask = 30;
                    if (!rc || (lpgui.flags & 0x1E) == 0x0) {
                        final OleFrame frame = site.frame;
                        frame.setData("org.eclipse.swt.OleFrame.ConsumeKey", null);
                        display.setData("org.eclipse.swt.internal.win32.accelKeyHit", Boolean.TRUE);
                        consumed = frame.translateOleAccelerator(msg);
                        if (display.isDisposed()) {
                            return 0L;
                        }
                        display.setData("org.eclipse.swt.internal.win32.accelKeyHit", Boolean.FALSE);
                        if (frame.isDisposed()) {
                            return 0L;
                        }
                        final String value = (String)frame.getData("org.eclipse.swt.OleFrame.ConsumeKey");
                        if (value != null) {
                            consumed = value.equals("true");
                        }
                        frame.setData("org.eclipse.swt.OleFrame.ConsumeKey", null);
                    }
                    boolean accentKey = false;
                    Label_0654: {
                        switch (msg.message) {
                            case 256:
                            case 260: {
                                switch ((int)msg.wParam) {
                                    case 16:
                                    case 17:
                                    case 18:
                                    case 20:
                                    case 144:
                                    case 145: {
                                        break Label_0654;
                                    }
                                    default: {
                                        final int mapKey = OS.MapVirtualKey((int)msg.wParam, 2);
                                        if (mapKey == 0) {
                                            break Label_0654;
                                        }
                                        accentKey = ((mapKey & Integer.MIN_VALUE) != 0x0);
                                        if (!accentKey) {
                                            for (final short element : OleFrame.ACCENTS) {
                                                final int value2 = OS.VkKeyScan(element);
                                                if (value2 != -1 && (value2 & 0xFF) == msg.wParam) {
                                                    final int state = value2 >> 8;
                                                    if (OS.GetKeyState(16) < 0 == ((state & 0x1) != 0x0) && OS.GetKeyState(17) < 0 == ((state & 0x2) != 0x0) && OS.GetKeyState(18) < 0 == ((state & 0x4) != 0x0)) {
                                                        if ((state & 0x7) != 0x0) {
                                                            accentKey = true;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                            break Label_0654;
                                        }
                                        break Label_0654;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if (!consumed && !accentKey && !OleFrame.ignoreNextKey) {
                        final long hwndOld = msg.hwnd;
                        msg.hwnd = site.handle;
                        consumed = (OS.DispatchMessage(msg) == 1L);
                        msg.hwnd = hwndOld;
                    }
                    Label_0819: {
                        switch (msg.message) {
                            case 256:
                            case 260: {
                                switch ((int)msg.wParam) {
                                    case 16:
                                    case 17:
                                    case 18:
                                    case 20:
                                    case 144:
                                    case 145: {
                                        break Label_0819;
                                    }
                                    default: {
                                        OleFrame.ignoreNextKey = accentKey;
                                        break Label_0819;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if (consumed) {
                        msg.message = 0;
                        final MSG msg2 = msg;
                        final MSG msg3 = msg;
                        final long n = 0L;
                        msg3.lParam = 0L;
                        msg2.wParam = 0L;
                        OS.MoveMemory(lParam, msg, MSG.sizeof);
                        return 0L;
                    }
                }
            }
        }
        return OS.CallNextHookEx(hHook.value, (int)code, wParam, lParam);
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    private int ContextSensitiveHelp(final int fEnterMode) {
        return 0;
    }
    
    private void createCOMInterfaces() {
        class llIl extends COMObject
        {
            final /* synthetic */ OleFrame this$0;
            
            llIl(final OleFrame this$0, final int[] argCounts) {
                this.this$0 = this$0;
                super(argCounts);
            }
            
            public long method0(final long[] args) {
                return this.this$0.QueryInterface(args[0], args[1]);
            }
            
            public long method1(final long[] args) {
                return this.this$0.AddRef();
            }
            
            public long method2(final long[] args) {
                return this.this$0.Release();
            }
            
            public long method3(final long[] args) {
                return this.this$0.GetWindow(args[0]);
            }
            
            public long method4(final long[] args) {
                return this.this$0.ContextSensitiveHelp((int)args[0]);
            }
            
            public long method5(final long[] args) {
                return this.this$0.GetBorder(args[0]);
            }
            
            public long method6(final long[] args) {
                return this.this$0.RequestBorderSpace(args[0]);
            }
            
            public long method7(final long[] args) {
                return this.this$0.SetBorderSpace(args[0]);
            }
            
            public long method8(final long[] args) {
                return this.this$0.SetActiveObject(args[0], args[1]);
            }
            
            public long method9(final long[] args) {
                return this.this$0.InsertMenus(args[0], args[1]);
            }
            
            public long method10(final long[] args) {
                return this.this$0.SetMenu(args[0], args[1], args[2]);
            }
            
            public long method11(final long[] args) {
                return this.this$0.RemoveMenus(args[0]);
            }
            
            public long method14(final long[] args) {
                return this.this$0.TranslateAccelerator(args[0], (int)args[1]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lorg/eclipse/swt/ole/win32/llIl;
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          15
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_1       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_1       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_1       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_1       
        //    38: iastore        
        //    39: dup            
        //    40: bipush          7
        //    42: iconst_1       
        //    43: iastore        
        //    44: dup            
        //    45: bipush          8
        //    47: iconst_2       
        //    48: iastore        
        //    49: dup            
        //    50: bipush          9
        //    52: iconst_2       
        //    53: iastore        
        //    54: dup            
        //    55: bipush          10
        //    57: iconst_3       
        //    58: iastore        
        //    59: dup            
        //    60: bipush          11
        //    62: iconst_1       
        //    63: iastore        
        //    64: dup            
        //    65: bipush          12
        //    67: iconst_1       
        //    68: iastore        
        //    69: dup            
        //    70: bipush          13
        //    72: iconst_1       
        //    73: iastore        
        //    74: dup            
        //    75: bipush          14
        //    77: iconst_2       
        //    78: iastore        
        //    79: invokespecial   org/eclipse/swt/ole/win32/llIl.<init>:(Lorg/eclipse/swt/ole/win32/OleFrame;[I)V
        //    82: putfield        org/eclipse/swt/ole/win32/OleFrame.iOleInPlaceFrame:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    85: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void disposeCOMInterfaces() {
        if (this.iOleInPlaceFrame != null) {
            this.iOleInPlaceFrame.dispose();
        }
        this.iOleInPlaceFrame = null;
    }
    
    private int GetBorder(final long lprectBorder) {
        if (lprectBorder == 0L) {
            return -2147024809;
        }
        final RECT rectBorder = new RECT();
        OS.GetClientRect(this.handle, rectBorder);
        OS.MoveMemory(lprectBorder, rectBorder, RECT.sizeof);
        return 0;
    }
    
    public MenuItem[] getContainerMenus() {
        return this.containerMenuItems;
    }
    
    public MenuItem[] getFileMenus() {
        return this.fileMenuItems;
    }
    
    long getIOleInPlaceFrame() {
        return this.iOleInPlaceFrame.getAddress();
    }
    
    private long getMenuItemID(final long hMenu, final int index) {
        long id = 0L;
        final MENUITEMINFO lpmii = new MENUITEMINFO();
        lpmii.cbSize = MENUITEMINFO.sizeof;
        lpmii.fMask = 7;
        OS.GetMenuItemInfo(hMenu, index, true, lpmii);
        if ((lpmii.fState & 0x10) == 0x10) {
            id = lpmii.hSubMenu;
        }
        else {
            id = lpmii.wID;
        }
        return id;
    }
    
    private int GetWindow(final long phwnd) {
        if (phwnd != 0L) {
            OS.MoveMemory(phwnd, new long[] { this.handle }, C.PTR_SIZEOF);
        }
        return 0;
    }
    
    public MenuItem[] getWindowMenus() {
        return this.windowMenuItems;
    }
    
    private int InsertMenus(final long hmenuShared, final long lpMenuWidths) {
        final Menu menubar = this.getShell().getMenuBar();
        if (menubar == null || menubar.isDisposed()) {
            OS.MoveMemory(lpMenuWidths, new int[] { 0 }, 4);
            return 0;
        }
        final long hMenu = menubar.handle;
        final MENUITEMINFO lpmii = new MENUITEMINFO();
        final long hHeap = OS.GetProcessHeap();
        final int cch = 128;
        final int byteCount = 256;
        final long pszText = OS.HeapAlloc(hHeap, 8, 256);
        lpmii.cbSize = MENUITEMINFO.sizeof;
        lpmii.fMask = 55;
        lpmii.dwTypeData = pszText;
        lpmii.cch = 128;
        int fileMenuCount = 0;
        int newindex = 0;
        if (this.fileMenuItems != null) {
            for (final MenuItem item : this.fileMenuItems) {
                if (item != null) {
                    final int index = item.getParent().indexOf(item);
                    lpmii.cch = 128;
                    if (OS.GetMenuItemInfo(hMenu, index, true, lpmii) && OS.InsertMenuItem(hmenuShared, newindex, true, lpmii)) {
                        ++fileMenuCount;
                        ++newindex;
                    }
                }
            }
        }
        OS.MoveMemory(lpMenuWidths, new int[] { fileMenuCount }, 4);
        int containerMenuCount = 0;
        if (this.containerMenuItems != null) {
            for (final MenuItem item2 : this.containerMenuItems) {
                if (item2 != null) {
                    final int index2 = item2.getParent().indexOf(item2);
                    lpmii.cch = 128;
                    if (OS.GetMenuItemInfo(hMenu, index2, true, lpmii) && OS.InsertMenuItem(hmenuShared, newindex, true, lpmii)) {
                        ++containerMenuCount;
                        ++newindex;
                    }
                }
            }
        }
        OS.MoveMemory(lpMenuWidths + 8L, new int[] { containerMenuCount }, 4);
        int windowMenuCount = 0;
        if (this.windowMenuItems != null) {
            for (final MenuItem item3 : this.windowMenuItems) {
                if (item3 != null) {
                    final int index3 = item3.getParent().indexOf(item3);
                    lpmii.cch = 128;
                    if (OS.GetMenuItemInfo(hMenu, index3, true, lpmii) && OS.InsertMenuItem(hmenuShared, newindex, true, lpmii)) {
                        ++windowMenuCount;
                        ++newindex;
                    }
                }
            }
        }
        OS.MoveMemory(lpMenuWidths + 16L, new int[] { windowMenuCount }, 4);
        if (pszText != 0L) {
            OS.HeapFree(hHeap, 0, pszText);
        }
        return 0;
    }
    
    void onActivate(final Event e) {
        if (this.objIOleInPlaceActiveObject != null) {
            this.objIOleInPlaceActiveObject.OnFrameWindowActivate(true);
        }
    }
    
    void onDeactivate(final Event e) {
        if (this.objIOleInPlaceActiveObject != null) {
            this.objIOleInPlaceActiveObject.OnFrameWindowActivate(false);
        }
    }
    
    private void onDispose(final Event e) {
        this.releaseObjectInterfaces();
        this.currentdoc = null;
        this.Release();
        this.removeListener(26, this.listener);
        this.removeListener(27, this.listener);
        this.removeListener(12, this.listener);
        this.removeListener(11, this.listener);
        this.removeListener(10, this.listener);
    }
    
    void onFocusIn(final Event e) {
        if (OleFrame.lastActivatedMenuHandle != this.newMenuHandle) {
            this.currentdoc.doVerb(-1);
        }
        if (OS.GetMenu(this.shellHandle) != this.newMenuHandle) {
            OS.SetMenu(this.shellHandle, this.newMenuHandle);
        }
    }
    
    void onFocusOut(final Event e) {
        final Control control = this.getDisplay().getFocusControl();
        if (OS.GetMenu(this.shellHandle) != this.oldMenuHandle && control != null && control.handle != this.shellHandle) {
            OS.SetMenu(this.shellHandle, this.oldMenuHandle);
        }
    }
    
    private void onResize(final Event e) {
        if (this.objIOleInPlaceActiveObject != null) {
            final RECT lpRect = new RECT();
            OS.GetClientRect(this.handle, lpRect);
            this.objIOleInPlaceActiveObject.ResizeBorder(lpRect, this.iOleInPlaceFrame.getAddress(), true);
        }
    }
    
    private int QueryInterface(final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIOleInPlaceFrame)) {
            OS.MoveMemory(ppvObject, new long[] { this.iOleInPlaceFrame.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
            if (COM.FreeUnusedLibraries) {
                COM.CoFreeUnusedLibraries();
            }
        }
        return this.refCount;
    }
    
    private void releaseObjectInterfaces() {
        if (this.objIOleInPlaceActiveObject != null) {
            this.objIOleInPlaceActiveObject.Release();
        }
        this.objIOleInPlaceActiveObject = null;
    }
    
    private int RemoveMenus(final long hmenuShared) {
        final Menu menubar = this.getShell().getMenuBar();
        if (menubar == null || menubar.isDisposed()) {
            return 1;
        }
        final long hMenu = menubar.handle;
        final List<LONG> ids = new ArrayList<LONG>();
        if (this.fileMenuItems != null) {
            for (final MenuItem item : this.fileMenuItems) {
                if (item != null && !item.isDisposed()) {
                    final int index = item.getParent().indexOf(item);
                    final long id = this.getMenuItemID(hMenu, index);
                    ids.add(new LONG(id));
                }
            }
        }
        if (this.containerMenuItems != null) {
            for (final MenuItem item : this.containerMenuItems) {
                if (item != null && !item.isDisposed()) {
                    final int index = item.getParent().indexOf(item);
                    final long id = this.getMenuItemID(hMenu, index);
                    ids.add(new LONG(id));
                }
            }
        }
        if (this.windowMenuItems != null) {
            for (final MenuItem item : this.windowMenuItems) {
                if (item != null && !item.isDisposed()) {
                    final int index = item.getParent().indexOf(item);
                    final long id = this.getMenuItemID(hMenu, index);
                    ids.add(new LONG(id));
                }
            }
        }
        int index2;
        for (int i = index2 = OS.GetMenuItemCount(hmenuShared) - 1; i >= 0; --i) {
            final long id2 = this.getMenuItemID(hmenuShared, i);
            if (ids.contains(new LONG(id2))) {
                OS.RemoveMenu(hmenuShared, i, 1024);
            }
        }
        return 0;
    }
    
    private int RequestBorderSpace(final long pborderwidths) {
        return 0;
    }
    
    int SetActiveObject(final long pActiveObject, final long pszObjName) {
        if (this.objIOleInPlaceActiveObject != null) {
            this.objIOleInPlaceActiveObject.Release();
            this.objIOleInPlaceActiveObject = null;
        }
        if (pActiveObject != 0L) {
            (this.objIOleInPlaceActiveObject = new IOleInPlaceActiveObject(pActiveObject)).AddRef();
        }
        return 0;
    }
    
    private int SetBorderSpace(final long pborderwidths) {
        if (this.objIOleInPlaceActiveObject == null) {
            return 0;
        }
        final RECT borderwidth = new RECT();
        if (pborderwidths == 0L || this.currentdoc == null) {
            return 0;
        }
        COM.MoveMemory(borderwidth, pborderwidths, RECT.sizeof);
        this.currentdoc.setBorderSpace(borderwidth);
        return 0;
    }
    
    public void setContainerMenus(final MenuItem[] containerMenus) {
        this.containerMenuItems = containerMenus;
    }
    
    OleClientSite getCurrentDocument() {
        return this.currentdoc;
    }
    
    void setCurrentDocument(final OleClientSite doc) {
        this.currentdoc = doc;
        if (this.currentdoc != null && this.objIOleInPlaceActiveObject != null) {
            final RECT lpRect = new RECT();
            OS.GetClientRect(this.handle, lpRect);
            this.objIOleInPlaceActiveObject.ResizeBorder(lpRect, this.iOleInPlaceFrame.getAddress(), true);
        }
    }
    
    public void setFileMenus(final MenuItem[] fileMenus) {
        this.fileMenuItems = fileMenus;
    }
    
    private int SetMenu(long hmenuShared, final long holemenu, final long hwndActiveObject) {
        long inPlaceActiveObject = 0L;
        if (this.objIOleInPlaceActiveObject != null) {
            inPlaceActiveObject = this.objIOleInPlaceActiveObject.getAddress();
        }
        final Menu menubar = this.getShell().getMenuBar();
        if (menubar == null || menubar.isDisposed()) {
            return COM.OleSetMenuDescriptor(0L, this.getShell().handle, hwndActiveObject, this.iOleInPlaceFrame.getAddress(), inPlaceActiveObject);
        }
        final long handle = menubar.getShell().handle;
        if (hmenuShared == 0L && holemenu == 0L) {
            hmenuShared = menubar.handle;
        }
        if (hmenuShared == 0L) {
            return -2147467259;
        }
        this.shellHandle = handle;
        this.oldMenuHandle = menubar.handle;
        this.newMenuHandle = hmenuShared;
        OleFrame.lastActivatedMenuHandle = this.newMenuHandle;
        return COM.OleSetMenuDescriptor(holemenu, handle, hwndActiveObject, this.iOleInPlaceFrame.getAddress(), inPlaceActiveObject);
    }
    
    public void setWindowMenus(final MenuItem[] windowMenus) {
        this.windowMenuItems = windowMenus;
    }
    
    private boolean translateOleAccelerator(final MSG msg) {
        if (this.objIOleInPlaceActiveObject == null) {
            return false;
        }
        final int result = this.objIOleInPlaceActiveObject.TranslateAccelerator(msg);
        return result != 1 && result != -2147467263;
    }
    
    private int TranslateAccelerator(final long lpmsg, final int wID) {
        final Menu menubar = this.getShell().getMenuBar();
        if (menubar == null || menubar.isDisposed() || !menubar.isEnabled()) {
            return 1;
        }
        if (wID < 0) {
            return 1;
        }
        final Shell shell = menubar.getShell();
        final long hwnd = shell.handle;
        final long hAccel = OS.SendMessage(hwnd, 32769, 0L, 0L);
        if (hAccel == 0L) {
            return 1;
        }
        final MSG msg = new MSG();
        OS.MoveMemory(msg, lpmsg, MSG.sizeof);
        final int result = OS.TranslateAccelerator(hwnd, hAccel, msg);
        return (result == 0) ? 1 : 0;
    }
    
    static {
        OleFrame.CHECK_FOCUS = "OLE_CHECK_FOCUS";
        OleFrame.HHOOK = "OLE_HHOOK";
        OleFrame.HHOOKMSG = "OLE_HHOOK_MSG";
        ACCENTS = new short[] { 126, 96, 39, 94, 34 };
    }
}
