//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public abstract class Widget
{
    int style;
    int state;
    Display display;
    EventTable eventTable;
    Object data;
    static final int DISPOSED = 1;
    static final int CANVAS = 2;
    static final int KEYED_DATA = 4;
    static final int DISABLED = 8;
    static final int HIDDEN = 16;
    static final int LAYOUT_NEEDED = 32;
    static final int LAYOUT_CHANGED = 64;
    static final int LAYOUT_CHILD = 128;
    static final int THEME_BACKGROUND = 256;
    static final int DRAW_BACKGROUND = 512;
    static final int PARENT_BACKGROUND = 1024;
    static final int RELEASED = 2048;
    static final int DISPOSE_SENT = 4096;
    static final int TRACK_MOUSE = 8192;
    static final int FOREIGN_HANDLE = 16384;
    static final int DRAG_DETECT = 32768;
    static final int MOVE_OCCURRED = 65536;
    static final int MOVE_DEFERRED = 131072;
    static final int RESIZE_OCCURRED = 262144;
    static final int RESIZE_DEFERRED = 524288;
    static final int IGNORE_WM_CHANGEUISTATE = 1048576;
    static final int SKIN_NEEDED = 2097152;
    static final int HAS_AUTO_DIRECTION = 4194304;
    static final int MOUSE_OVER = 8388608;
    static final int CUSTOM_DRAW_ITEM = 16777216;
    static final int DEFAULT_WIDTH = 64;
    static final int DEFAULT_HEIGHT = 64;
    static final char LRE = '\u202a';
    static final char RLE = '\u202b';
    static final int AUTO_TEXT_DIRECTION = 100663296;
    
    Widget() {
        this.notifyCreationTracker();
    }
    
    public Widget(final Widget parent, final int style) {
        this.checkSubclass();
        this.checkParent(parent);
        this.style = style;
        this.display = parent.display;
        this.reskinWidget();
        this.notifyCreationTracker();
    }
    
    void _addListener(final int eventType, final Listener listener) {
        if (this.eventTable == null) {
            this.eventTable = new EventTable();
        }
        this.eventTable.hook(eventType, listener);
    }
    
    void _removeListener(final int eventType, final Listener listener) {
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(eventType, listener);
    }
    
    public void addListener(final int eventType, final Listener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        this._addListener(eventType, listener);
    }
    
    public void addDisposeListener(final DisposeListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(12, (Listener)typedListener);
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        return 0L;
    }
    
    static int checkBits(int style, final int int0, final int int1, final int int2, final int int3, final int int4, final int int5) {
        final int mask = int0 | int1 | int2 | int3 | int4 | int5;
        if ((style & mask) == 0x0) {
            style |= int0;
        }
        if ((style & int0) != 0x0) {
            style = ((style & ~mask) | int0);
        }
        if ((style & int1) != 0x0) {
            style = ((style & ~mask) | int1);
        }
        if ((style & int2) != 0x0) {
            style = ((style & ~mask) | int2);
        }
        if ((style & int3) != 0x0) {
            style = ((style & ~mask) | int3);
        }
        if ((style & int4) != 0x0) {
            style = ((style & ~mask) | int4);
        }
        if ((style & int5) != 0x0) {
            style = ((style & ~mask) | int5);
        }
        return style;
    }
    
    void checkOrientation(final Widget parent) {
        this.style &= 0xF7FFFFFF;
        if ((this.style & 0x6000000) == 0x0 && parent != null) {
            if ((parent.style & 0x2000000) != 0x0) {
                this.style |= 0x2000000;
            }
            if ((parent.style & 0x4000000) != 0x0) {
                this.style |= 0x4000000;
            }
        }
        this.style = checkBits(this.style, 33554432, 67108864, 0, 0, 0, 0);
    }
    
    void checkOpened() {
    }
    
    void checkParent(final Widget parent) {
        if (parent == null) {
            this.error(4);
        }
        if (parent.isDisposed()) {
            this.error(5);
        }
        parent.checkWidget();
        parent.checkOpened();
    }
    
    void maybeEnableDarkSystemTheme(final long handle) {
        if (this.display.useDarkModeExplorerTheme) {
            OS.AllowDarkModeForWindow(handle, true);
            OS.SetWindowTheme(handle, Display.EXPLORER, (char[])null);
        }
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    protected void checkWidget() {
        final Display display = this.display;
        if (display == null) {
            this.error(24);
        }
        if (display.thread != Thread.currentThread()) {
            this.error(22);
        }
        if ((this.state & 0x1) != 0x0) {
            this.error(24);
        }
    }
    
    void destroyWidget() {
        this.releaseHandle();
    }
    
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        if (!this.isValidThread()) {
            this.error(22);
        }
        this.release(true);
    }
    
    boolean dragDetect(final long hwnd, final int x, final int y, final boolean filter, final boolean[] detect, final boolean[] consume) {
        if (consume != null) {
            consume[0] = false;
        }
        if (detect != null) {
            detect[0] = true;
        }
        final POINT pt = new POINT();
        pt.x = x;
        pt.y = y;
        OS.ClientToScreen(hwnd, pt);
        return OS.DragDetect(hwnd, pt);
    }
    
    void error(final int code) {
        SWT.error(code);
    }
    
    boolean filters(final int eventType) {
        return this.display.filters(eventType);
    }
    
    Widget findItem(final long id) {
        return null;
    }
    
    char[] fixMnemonic(final String string) {
        return this.fixMnemonic(string, false, false);
    }
    
    char[] fixMnemonic(final String string, final boolean spaces) {
        return this.fixMnemonic(string, spaces, false);
    }
    
    char[] fixMnemonic(final String string, final boolean spaces, final boolean removeAppended) {
        final char[] buffer = new char[string.length() + 1];
        string.getChars(0, string.length(), buffer, 0);
        int i = 0;
        int j = 0;
        while (i < buffer.length) {
            if (buffer[i] == '&') {
                if (i + 1 < buffer.length && buffer[i + 1] == '&') {
                    buffer[j++] = (spaces ? ' ' : buffer[i]);
                    ++i;
                }
                ++i;
            }
            else if (buffer[i] == '(' && removeAppended && i + 4 == string.length() && buffer[i + 1] == '&' && buffer[i + 3] == ')') {
                if (spaces) {
                    buffer[j++] = ' ';
                }
                i += 4;
            }
            else {
                buffer[j++] = buffer[i++];
            }
        }
        while (j < buffer.length) {
            buffer[j++] = '\0';
        }
        return buffer;
    }
    
    public Object getData() {
        this.checkWidget();
        return ((this.state & 0x4) != 0x0) ? ((Object[])this.data)[0] : this.data;
    }
    
    public Object getData(final String key) {
        this.checkWidget();
        if (key == null) {
            this.error(4);
        }
        if ((this.state & 0x4) != 0x0) {
            final Object[] table = (Object[])this.data;
            for (int i = 1; i < table.length; i += 2) {
                if (key.equals(table[i])) {
                    return table[i + 1];
                }
            }
        }
        return null;
    }
    
    public Display getDisplay() {
        final Display display = this.display;
        if (display == null) {
            this.error(24);
        }
        return display;
    }
    
    public Listener[] getListeners(final int eventType) {
        this.checkWidget();
        if (this.eventTable == null) {
            return new Listener[0];
        }
        return this.eventTable.getListeners(eventType);
    }
    
    Menu getMenu() {
        return null;
    }
    
    String getName() {
        final String string = this.getClass().getName();
        final int index = string.lastIndexOf(46);
        if (index == -1) {
            return string;
        }
        return string.substring(index + 1, string.length());
    }
    
    String getNameText() {
        return "";
    }
    
    public int getStyle() {
        this.checkWidget();
        return this.style;
    }
    
    boolean hooks(final int eventType) {
        return this.eventTable != null && this.eventTable.hooks(eventType);
    }
    
    public boolean isAutoDirection() {
        return (this.state & 0x400000) != 0x0;
    }
    
    public boolean isDisposed() {
        return (this.state & 0x1) != 0x0;
    }
    
    public boolean isListening(final int eventType) {
        this.checkWidget();
        return this.hooks(eventType);
    }
    
    boolean isValidSubclass() {
        return Display.isValidClass((Class)this.getClass());
    }
    
    boolean isValidThread() {
        return this.getDisplay().isValidThread();
    }
    
    void mapEvent(final long hwnd, final Event event) {
    }
    
    GC new_GC(final GCData data) {
        return null;
    }
    
    public void notifyListeners(final int eventType, Event event) {
        this.checkWidget();
        if (event == null) {
            event = new Event();
        }
        this.sendEvent(eventType, event);
    }
    
    void postEvent(final int eventType) {
        this.sendEvent(eventType, null, false);
    }
    
    void postEvent(final int eventType, final Event event) {
        this.sendEvent(eventType, event, false);
    }
    
    void release(final boolean destroy) {
        try (final ExceptionStash exceptions = new ExceptionStash()) {
            if ((this.state & 0x1000) == 0x0) {
                this.state |= 0x1000;
                try {
                    this.sendEvent(12);
                }
                catch (Error | RuntimeException error) {
                    final Throwable t2;
                    final Throwable ex = t2;
                    exceptions.stash(ex);
                }
            }
            if ((this.state & 0x1) == 0x0) {
                try {
                    this.releaseChildren(destroy);
                }
                catch (Error | RuntimeException error2) {
                    final Throwable t3;
                    final Throwable ex = t3;
                    exceptions.stash(ex);
                }
            }
            if ((this.state & 0x800) == 0x0) {
                this.state |= 0x800;
                if (destroy) {
                    this.releaseParent();
                    this.releaseWidget();
                    this.destroyWidget();
                }
                else {
                    this.releaseWidget();
                    this.releaseHandle();
                }
            }
            this.notifyDisposalTracker();
        }
    }
    
    void releaseChildren(final boolean destroy) {
    }
    
    void releaseHandle() {
        this.state |= 0x1;
        this.display = null;
    }
    
    void releaseParent() {
    }
    
    void releaseWidget() {
        this.eventTable = null;
        this.data = null;
    }
    
    public void removeListener(final int eventType, final Listener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        this._removeListener(eventType, listener);
    }
    
    protected void removeListener(final int eventType, final SWTEventListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(eventType, listener);
    }
    
    public void removeDisposeListener(final DisposeListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(12, (SWTEventListener)listener);
    }
    
    public void reskin(final int flags) {
        this.checkWidget();
        this.reskinWidget();
        if ((flags & 0x1) != 0x0) {
            this.reskinChildren(flags);
        }
    }
    
    void reskinChildren(final int flags) {
    }
    
    void reskinWidget() {
        if ((this.state & 0x200000) != 0x200000) {
            this.state |= 0x200000;
            this.display.addSkinnableWidget(this);
        }
    }
    
    boolean sendDragEvent(final int button, final int x, final int y) {
        final Event event = new Event();
        event.button = button;
        event.setLocationInPixels(x, y);
        this.setInputState(event, 29);
        this.postEvent(29, event);
        return !this.isDisposed() && event.doit;
    }
    
    boolean sendDragEvent(final int button, final int stateMask, final int x, final int y) {
        final Event event = new Event();
        event.button = button;
        event.setLocationInPixels(x, y);
        event.stateMask = stateMask;
        this.postEvent(29, event);
        return !this.isDisposed() && event.doit;
    }
    
    void sendEvent(final Event event) {
        final Display display = event.display;
        if (!display.filterEvent(event) && this.eventTable != null) {
            display.sendEvent(this.eventTable, event);
        }
    }
    
    void sendEvent(final int eventType) {
        this.sendEvent(eventType, null, true);
    }
    
    void sendEvent(final int eventType, final Event event) {
        this.sendEvent(eventType, event, true);
    }
    
    void sendEvent(final int eventType, Event event, final boolean send) {
        if (this.eventTable == null && !this.display.filters(eventType)) {
            return;
        }
        if (event == null) {
            event = new Event();
        }
        event.type = eventType;
        event.display = this.display;
        event.widget = this;
        if (event.time == 0) {
            event.time = this.display.getLastEventTime();
        }
        if (send) {
            this.sendEvent(event);
        }
        else {
            this.display.postEvent(event);
        }
    }
    
    void sendSelectionEvent(final int type) {
        this.sendSelectionEvent(type, null, false);
    }
    
    void sendSelectionEvent(final int type, Event event, final boolean send) {
        if (this.eventTable == null && !this.display.filters(type)) {
            return;
        }
        if (event == null) {
            event = new Event();
        }
        this.setInputState(event, type);
        this.sendEvent(type, event, send);
    }
    
    boolean sendKeyEvent(final int type, final int msg, final long wParam, final long lParam) {
        final Event event = new Event();
        return !this.setKeyState(event, type, wParam, lParam) || this.sendKeyEvent(type, msg, wParam, lParam, event);
    }
    
    boolean sendKeyEvent(final int type, final int msg, final long wParam, final long lParam, final Event event) {
        this.sendEvent(type, event);
        return !this.isDisposed() && event.doit;
    }
    
    boolean sendMouseEvent(final int type, final int button, final long hwnd, final long lParam) {
        return this.sendMouseEvent(type, button, this.display.getClickCount(type, button, hwnd, lParam), 0, false, hwnd, lParam);
    }
    
    boolean sendMouseEvent(final int type, final int button, final int count, final int detail, final boolean send, final long hwnd, final long lParam) {
        if (!this.hooks(type) && !this.filters(type)) {
            return true;
        }
        final Event event = new Event();
        event.button = button;
        event.detail = detail;
        event.count = count;
        event.setLocationInPixels(OS.GET_X_LPARAM(lParam), OS.GET_Y_LPARAM(lParam));
        this.setInputState(event, type);
        this.mapEvent(hwnd, event);
        if (send) {
            this.sendEvent(type, event);
            if (this.isDisposed()) {
                return false;
            }
        }
        else {
            this.postEvent(type, event);
        }
        return event.doit;
    }
    
    boolean sendMouseWheelEvent(final int type, final long hwnd, final long wParam, long lParam) {
        if (!this.hooks(type) && !this.filters(type)) {
            return true;
        }
        final boolean vertical = type == 37;
        final MouseWheelData wheelData = new MouseWheelData(vertical, null, wParam, this.display.scrollRemainderEvt);
        if (wheelData.count == 0) {
            return true;
        }
        if (!vertical) {
            wheelData.count = -wheelData.count;
        }
        final POINT pt = new POINT();
        OS.POINTSTOPOINT(pt, lParam);
        OS.ScreenToClient(hwnd, pt);
        lParam = OS.MAKELPARAM(pt.x, pt.y);
        return this.sendMouseEvent(type, 0, wheelData.count, wheelData.detail, true, hwnd, lParam);
    }
    
    public void setData(final Object data) {
        this.checkWidget();
        if ((this.state & 0x4) != 0x0) {
            ((Object[])this.data)[0] = data;
        }
        else {
            this.data = data;
        }
    }
    
    public void setData(final String key, final Object value) {
        this.checkWidget();
        if (key == null) {
            this.error(4);
        }
        int index = 1;
        Object[] table = null;
        if ((this.state & 0x4) != 0x0) {
            for (table = (Object[])this.data; index < table.length; index += 2) {
                if (key.equals(table[index])) {
                    break;
                }
            }
        }
        if (value != null) {
            if ((this.state & 0x4) != 0x0) {
                if (index == table.length) {
                    final Object[] newTable = new Object[table.length + 2];
                    System.arraycopy(table, 0, newTable, 0, table.length);
                    final Object[] data = newTable;
                    this.data = data;
                    table = data;
                }
            }
            else {
                table = new Object[] { this.data, null, null };
                this.data = table;
                this.state |= 0x4;
            }
            table[index] = key;
            table[index + 1] = value;
        }
        else if ((this.state & 0x4) != 0x0 && index != table.length) {
            final int length = table.length - 2;
            if (length == 1) {
                this.data = table[0];
                this.state &= 0xFFFFFFFB;
            }
            else {
                final Object[] newTable2 = new Object[length];
                System.arraycopy(table, 0, newTable2, 0, index);
                System.arraycopy(table, index + 2, newTable2, index, length - index);
                this.data = newTable2;
            }
        }
        if (key.equals("org.eclipse.swt.skin.class") || key.equals("org.eclipse.swt.skin.id")) {
            this.reskin(1);
        }
    }
    
    boolean sendFocusEvent(final int type) {
        this.sendEvent(type);
        return true;
    }
    
    boolean setInputState(final Event event, final int type) {
        if (OS.GetKeyState(18) < 0) {
            event.stateMask |= 0x10000;
        }
        if (OS.GetKeyState(16) < 0) {
            event.stateMask |= 0x20000;
        }
        if (OS.GetKeyState(17) < 0) {
            event.stateMask |= 0x40000;
        }
        if (OS.GetKeyState(1) < 0) {
            event.stateMask |= 0x80000;
        }
        if (OS.GetKeyState(4) < 0) {
            event.stateMask |= 0x100000;
        }
        if (OS.GetKeyState(2) < 0) {
            event.stateMask |= 0x200000;
        }
        if (this.display.xMouse) {
            if (OS.GetKeyState(5) < 0) {
                event.stateMask |= 0x800000;
            }
            if (OS.GetKeyState(6) < 0) {
                event.stateMask |= 0x2000000;
            }
        }
        switch (type) {
            case 3:
            case 8: {
                if (event.button == 1) {
                    event.stateMask &= 0xFFF7FFFF;
                }
                if (event.button == 2) {
                    event.stateMask &= 0xFFEFFFFF;
                }
                if (event.button == 3) {
                    event.stateMask &= 0xFFDFFFFF;
                }
                if (event.button == 4) {
                    event.stateMask &= 0xFF7FFFFF;
                }
                if (event.button == 5) {
                    event.stateMask &= 0xFDFFFFFF;
                    break;
                }
                break;
            }
            case 4: {
                if (event.button == 1) {
                    event.stateMask |= 0x80000;
                }
                if (event.button == 2) {
                    event.stateMask |= 0x100000;
                }
                if (event.button == 3) {
                    event.stateMask |= 0x200000;
                }
                if (event.button == 4) {
                    event.stateMask |= 0x800000;
                }
                if (event.button == 5) {
                    event.stateMask |= 0x2000000;
                    break;
                }
                break;
            }
            case 1:
            case 31: {
                if (event.keyCode == 65536) {
                    event.stateMask &= 0xFFFEFFFF;
                }
                if (event.keyCode == 131072) {
                    event.stateMask &= 0xFFFDFFFF;
                }
                if (event.keyCode == 262144) {
                    event.stateMask &= 0xFFFBFFFF;
                    break;
                }
                break;
            }
            case 2: {
                if (event.keyCode == 65536) {
                    event.stateMask |= 0x10000;
                }
                if (event.keyCode == 131072) {
                    event.stateMask |= 0x20000;
                }
                if (event.keyCode == 262144) {
                    event.stateMask |= 0x40000;
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    boolean setKeyState(final Event event, final int type, final long wParam, final long lParam) {
        switch (this.display.lastAscii) {
            case 127: {
                if (this.display.lastKey == 8) {
                    this.display.lastAscii = 8;
                    break;
                }
                break;
            }
            case 10: {
                if (this.display.lastKey == 13) {
                    this.display.lastAscii = 13;
                    break;
                }
                break;
            }
        }
        if (this.display.lastKey == 13 && this.display.lastAscii == 13 && (lParam & 0x1000000L) != 0x0L) {
            this.display.lastKey = 16777296;
        }
        this.setLocationMask(event, type, wParam, lParam);
        if (this.display.lastVirtual) {
            if (this.display.lastKey == 46) {
                this.display.lastAscii = 127;
            }
            if (this.display.lastKey == 3) {
                this.display.lastAscii = 0;
            }
            event.keyCode = Display.translateKey(this.display.lastKey);
        }
        else {
            event.keyCode = this.display.lastKey;
        }
        if (this.display.lastAscii != 0 || this.display.lastNull) {
            event.character = (char)this.display.lastAscii;
        }
        return (event.keyCode != 0 || event.character != '\0' || this.display.lastNull) && this.setInputState(event, type);
    }
    
    int setLocationMask(final Event event, final int type, final long wParam, final long lParam) {
        int location = 0;
        if (this.display.lastVirtual) {
            switch (this.display.lastKey) {
                case 16: {
                    if (OS.GetKeyState(160) < 0) {
                        location = 16384;
                    }
                    if (OS.GetKeyState(161) < 0) {
                        location = 131072;
                        break;
                    }
                    break;
                }
                case 144: {
                    location = 2;
                    break;
                }
                case 17:
                case 18: {
                    location = (((lParam & 0x1000000L) == 0x0L) ? 16384 : 131072);
                    break;
                }
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 45:
                case 46: {
                    if ((lParam & 0x1000000L) == 0x0L) {
                        location = 2;
                        break;
                    }
                    break;
                }
            }
            if (this.display.numpadKey(this.display.lastKey) != 0) {
                location = 2;
            }
        }
        else if (this.display.lastKey == 16777296) {
            location = 2;
        }
        return event.keyLocation = location;
    }
    
    boolean setTabGroupFocus() {
        return this.setTabItemFocus();
    }
    
    boolean setTabItemFocus() {
        return false;
    }
    
    boolean showMenu(final int x, final int y) {
        return this.showMenu(x, y, 0);
    }
    
    boolean showMenu(final int x, final int y, final int detail) {
        final Event event = new Event();
        event.setLocationInPixels(x, y);
        event.detail = detail;
        if (event.detail == 1) {
            this.updateMenuLocation(event);
        }
        this.sendEvent(35, event);
        if (this.isDisposed()) {
            return false;
        }
        if (!event.doit) {
            return true;
        }
        final Menu menu = this.getMenu();
        if (menu != null && !menu.isDisposed()) {
            final Point loc = event.getLocationInPixels();
            if (x != loc.x || y != loc.y) {
                menu.setLocation(event.getLocation());
            }
            menu.setVisible(true);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        String string = "*Disposed*";
        if (!this.isDisposed()) {
            string = "*Wrong Thread*";
            if (this.isValidThread()) {
                string = this.getNameText();
            }
        }
        return this.getName() + " {" + string;
    }
    
    void updateMenuLocation(final Event event) {
    }
    
    LRESULT wmCaptureChanged(final long hwnd, final long wParam, final long lParam) {
        this.display.captureChanged = true;
        return null;
    }
    
    LRESULT wmChar(final long hwnd, final long wParam, final long lParam) {
        this.display.lastAscii = (int)wParam;
        this.display.lastNull = (wParam == 0L);
        if (!this.sendKeyEvent(1, 258, wParam, lParam)) {
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT wmContextMenu(final long hwnd, final long wParam, final long lParam) {
        if (wParam != hwnd) {
            return null;
        }
        int x = 0;
        int y = 0;
        int detail = 0;
        if (lParam != -1L) {
            final POINT pt = new POINT();
            OS.POINTSTOPOINT(pt, lParam);
            x = pt.x;
            y = pt.y;
            detail = 0;
            OS.ScreenToClient(hwnd, pt);
            final RECT rect = new RECT();
            OS.GetClientRect(hwnd, rect);
            if (!OS.PtInRect(rect, pt)) {
                return null;
            }
        }
        else {
            final int pos = OS.GetMessagePos();
            x = OS.GET_X_LPARAM((long)pos);
            y = OS.GET_Y_LPARAM((long)pos);
            detail = 1;
        }
        return this.showMenu(x, y, detail) ? LRESULT.ZERO : null;
    }
    
    LRESULT wmIMEChar(final long hwnd, final long wParam, final long lParam) {
        final Display display = this.display;
        display.lastKey = 0;
        display.lastAscii = (int)wParam;
        final Display display2 = display;
        final Display display3 = display;
        final Display display4 = display;
        final boolean lastVirtual = false;
        display4.lastDead = false;
        display3.lastNull = false;
        display2.lastVirtual = false;
        if (!this.sendKeyEvent(1, 646, wParam, lParam)) {
            return LRESULT.ONE;
        }
        this.sendKeyEvent(2, 646, wParam, lParam);
        final Display display5 = display;
        final Display display6 = display;
        final int n = 0;
        display6.lastAscii = 0;
        display5.lastKey = 0;
        return LRESULT.ONE;
    }
    
    LRESULT wmKeyDown(final long hwnd, final long wParam, final long lParam) {
        switch ((int)wParam) {
            case 16:
            case 17:
            case 18:
            case 20:
            case 144:
            case 145: {
                if ((lParam & 0x40000000L) != 0x0L) {
                    return null;
                }
                break;
            }
        }
        final Display display = this.display;
        final Display display2 = this.display;
        final int n = 0;
        display2.lastKey = 0;
        display.lastAscii = 0;
        final Display display3 = this.display;
        final Display display4 = this.display;
        final Display display5 = this.display;
        final boolean lastVirtual = false;
        display5.lastDead = false;
        display4.lastNull = false;
        display3.lastVirtual = false;
        int mapKey = OS.MapVirtualKey((int)wParam, 2);
        if ((2534 <= mapKey && mapKey <= 2543) || (2406 <= mapKey && mapKey <= 2415)) {
            mapKey = (int)wParam;
        }
        if ((mapKey & Integer.MIN_VALUE) != 0x0) {
            return null;
        }
        final MSG msg = new MSG();
        final int flags = 2;
        if (OS.PeekMessage(msg, hwnd, 259, 259, 2)) {
            this.display.lastDead = true;
            this.display.lastVirtual = (mapKey == 0);
            this.display.lastKey = (this.display.lastVirtual ? ((int)wParam) : mapKey);
            return null;
        }
        if (this.isDisposed()) {
            return LRESULT.ONE;
        }
        this.display.lastVirtual = (mapKey == 0 || this.display.numpadKey((int)wParam) != 0);
        if (this.display.lastVirtual) {
            this.display.lastKey = (int)wParam;
            if (this.display.lastKey == 46) {
                this.display.lastAscii = 127;
            }
            if (96 <= this.display.lastKey && this.display.lastKey <= 111) {
                if (this.display.asciiKey(this.display.lastKey) != 0) {
                    return null;
                }
                this.display.lastAscii = this.display.numpadKey(this.display.lastKey);
            }
        }
        else {
            this.display.lastKey = (int)OS.CharLower((long)(short)mapKey);
            if (wParam == 3L) {
                this.display.lastVirtual = true;
            }
            final int asciiKey = this.display.asciiKey((int)wParam);
            if (asciiKey != 0) {
                if (asciiKey == 32) {
                    return null;
                }
                if (asciiKey != (int)wParam) {
                    return null;
                }
                if (wParam == 3L) {
                    return null;
                }
            }
            if (OS.GetKeyState(17) >= 0) {
                return null;
            }
            if (OS.GetKeyState(16) < 0) {
                this.display.lastAscii = this.display.shiftedKey((int)wParam);
                if (this.display.lastAscii == 0) {
                    this.display.lastAscii = mapKey;
                }
            }
            else {
                this.display.lastAscii = (int)OS.CharLower((long)(short)mapKey);
            }
            if (this.display.lastAscii == 64) {
                return null;
            }
            this.display.lastAscii = this.display.controlKey(this.display.lastAscii);
        }
        if (!this.sendKeyEvent(1, 256, wParam, lParam)) {
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT wmKeyUp(final long hwnd, final long wParam, final long lParam) {
        final Display display = this.display;
        if (!this.hooks(2) && !display.filters(2)) {
            final Display display2 = display;
            final Display display3 = display;
            final int n = 0;
            display3.lastAscii = 0;
            display2.lastKey = 0;
            final Display display4 = display;
            final Display display5 = display;
            final Display display6 = display;
            final boolean lastVirtual = false;
            display6.lastDead = false;
            display5.lastNull = false;
            display4.lastVirtual = false;
            return null;
        }
        final int mapKey = OS.MapVirtualKey((int)wParam, 2);
        if ((mapKey & Integer.MIN_VALUE) != 0x0) {
            return null;
        }
        if (display.lastDead) {
            return null;
        }
        display.lastVirtual = (mapKey == 0 || display.numpadKey((int)wParam) != 0);
        if (display.lastVirtual) {
            display.lastKey = (int)wParam;
        }
        else {
            if (wParam == 3L) {
                display.lastVirtual = true;
            }
            if (display.lastKey == 0) {
                display.lastAscii = 0;
                final Display display7 = display;
                final Display display8 = display;
                final boolean b = false;
                display8.lastDead = false;
                display7.lastNull = false;
                return null;
            }
        }
        LRESULT result = null;
        if (!this.sendKeyEvent(2, 257, wParam, lParam)) {
            result = LRESULT.ONE;
        }
        final Display display9 = display;
        final Display display10 = display;
        final int n2 = 0;
        display10.lastAscii = 0;
        display9.lastKey = 0;
        final Display display11 = display;
        final Display display12 = display;
        final Display display13 = display;
        final boolean lastVirtual2 = false;
        display13.lastDead = false;
        display12.lastNull = false;
        display11.lastVirtual = false;
        return result;
    }
    
    LRESULT wmKillFocus(final long hwnd, final long wParam, final long lParam) {
        this.display.scrollRemainderEvt.x = 0;
        this.display.scrollRemainderEvt.y = 0;
        this.display.scrollRemainderBar.x = 0;
        this.display.scrollRemainderBar.y = 0;
        final long code = this.callWindowProc(hwnd, 8, wParam, lParam);
        this.sendFocusEvent(16);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (code == 0L) {
            return LRESULT.ZERO;
        }
        return new LRESULT(code);
    }
    
    LRESULT wmLButtonDblClk(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = null;
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 1, hwnd, lParam);
        if (this.sendMouseEvent(8, 1, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 515, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwnd) {
            OS.SetCapture(hwnd);
        }
        return result;
    }
    
    LRESULT wmLButtonDown(final long hwnd, final long wParam, final long lParam) {
        final Display display = this.display;
        LRESULT result = null;
        final int x = OS.GET_X_LPARAM(lParam);
        final int y = OS.GET_Y_LPARAM(lParam);
        boolean[] consume = null;
        boolean[] detect = null;
        boolean dragging = false;
        boolean mouseDown = true;
        final int count = display.getClickCount(3, 1, hwnd, lParam);
        if (count == 1 && (this.state & 0x8000) != 0x0 && this.hooks(29)) {
            detect = new boolean[] { false };
            consume = new boolean[] { false };
            dragging = this.dragDetect(hwnd, x, y, true, detect, consume);
            if (this.isDisposed()) {
                return LRESULT.ZERO;
            }
            mouseDown = (OS.GetKeyState(1) < 0);
        }
        display.captureChanged = false;
        final boolean dispatch = this.sendMouseEvent(3, 1, count, 0, false, hwnd, lParam);
        if (dispatch && (consume == null || !consume[0])) {
            result = new LRESULT(this.callWindowProc(hwnd, 513, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        if (mouseDown && !display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwnd) {
            OS.SetCapture(hwnd);
        }
        if (dragging) {
            this.sendDragEvent(1, x, y);
        }
        else if (detect != null && detect[0] && OS.GetKeyState(27) >= 0) {
            OS.SendMessage(hwnd, 514, wParam, lParam);
        }
        return result;
    }
    
    LRESULT wmLButtonUp(final long hwnd, final long wParam, final long lParam) {
        final Display display = this.display;
        LRESULT result = null;
        if (this.sendMouseEvent(4, 1, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 514, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        int mask = 19;
        if (display.xMouse) {
            mask |= 0x60;
        }
        if ((wParam & (long)mask) == 0x0L && OS.GetCapture() == hwnd) {
            OS.ReleaseCapture();
        }
        return result;
    }
    
    LRESULT wmMButtonDblClk(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = null;
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 2, hwnd, lParam);
        if (this.sendMouseEvent(8, 2, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 521, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwnd) {
            OS.SetCapture(hwnd);
        }
        return result;
    }
    
    LRESULT wmMButtonDown(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = null;
        final Display display = this.display;
        display.captureChanged = false;
        if (this.sendMouseEvent(3, 2, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 519, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwnd) {
            OS.SetCapture(hwnd);
        }
        return result;
    }
    
    LRESULT wmMButtonUp(final long hwnd, final long wParam, final long lParam) {
        final Display display = this.display;
        LRESULT result = null;
        if (this.sendMouseEvent(4, 2, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 520, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        int mask = 19;
        if (display.xMouse) {
            mask |= 0x60;
        }
        if ((wParam & (long)mask) == 0x0L && OS.GetCapture() == hwnd) {
            OS.ReleaseCapture();
        }
        return result;
    }
    
    LRESULT wmMouseHover(final long hwnd, final long wParam, final long lParam) {
        if (!this.sendMouseEvent(32, 0, hwnd, lParam)) {
            return LRESULT.ZERO;
        }
        return null;
    }
    
    LRESULT wmMouseLeave(final long hwnd, final long wParam, long lParam) {
        this.state &= 0xFF7FFFFF;
        if (!this.hooks(7) && !this.filters(7)) {
            return null;
        }
        final int pos = OS.GetMessagePos();
        final POINT pt = new POINT();
        OS.POINTSTOPOINT(pt, (long)pos);
        OS.ScreenToClient(hwnd, pt);
        lParam = OS.MAKELPARAM(pt.x, pt.y);
        if (!this.sendMouseEvent(7, 0, hwnd, lParam)) {
            return LRESULT.ZERO;
        }
        return null;
    }
    
    LRESULT wmMouseMove(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = null;
        final Display display = this.display;
        final int pos = OS.GetMessagePos();
        if (pos != display.lastMouse || display.captureChanged) {
            final boolean trackMouse = (this.state & 0x2000) != 0x0;
            final boolean mouseEnter = this.hooks(6) || display.filters(6);
            final boolean mouseExit = this.hooks(7) || display.filters(7);
            final boolean mouseHover = this.hooks(32) || display.filters(32);
            if (trackMouse || mouseEnter || mouseExit || mouseHover) {
                final TRACKMOUSEEVENT lpEventTrack = new TRACKMOUSEEVENT();
                lpEventTrack.cbSize = TRACKMOUSEEVENT.sizeof;
                lpEventTrack.dwFlags = 3;
                lpEventTrack.hwndTrack = hwnd;
                OS.TrackMouseEvent(lpEventTrack);
                if (mouseEnter && (this.state & 0x800000) == 0x0) {
                    final MSG msg = new MSG();
                    final int flags = 10420227;
                    while (OS.PeekMessage(msg, 0L, 675, 675, 10420227)) {
                        OS.TranslateMessage(msg);
                        OS.DispatchMessage(msg);
                    }
                    this.sendMouseEvent(6, 0, hwnd, lParam);
                }
                this.state |= 0x800000;
            }
            if (pos != display.lastMouse) {
                display.lastMouse = pos;
                if (!this.sendMouseEvent(5, 0, hwnd, lParam)) {
                    result = LRESULT.ZERO;
                }
            }
        }
        display.captureChanged = false;
        return result;
    }
    
    LRESULT wmMouseWheel(final long hwnd, final long wParam, final long lParam) {
        return this.sendMouseWheelEvent(37, hwnd, wParam, lParam) ? null : LRESULT.ZERO;
    }
    
    LRESULT wmMouseHWheel(final long hwnd, final long wParam, final long lParam) {
        return this.sendMouseWheelEvent(38, hwnd, wParam, lParam) ? null : LRESULT.ZERO;
    }
    
    LRESULT wmNCPaint(final long hwnd, final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT wmPaint(final long hwnd, final long wParam, final long lParam) {
        if (!this.hooks(9) && !this.filters(9)) {
            return null;
        }
        final long rgn = OS.CreateRectRgn(0, 0, 0, 0);
        OS.GetUpdateRgn(hwnd, rgn, false);
        final long result = this.callWindowProc(hwnd, 15, wParam, lParam);
        final GCData data = new GCData();
        data.hwnd = hwnd;
        final GC gc = this.new_GC(data);
        if (gc != null) {
            OS.HideCaret(hwnd);
            final RECT rect = new RECT();
            OS.GetRgnBox(rgn, rect);
            final int width = rect.right - rect.left;
            final int height = rect.bottom - rect.top;
            if (width != 0 && height != 0) {
                final long hDC = gc.handle;
                OS.SelectClipRgn(hDC, rgn);
                OS.SetMetaRgn(hDC);
                final Event event = new Event();
                event.gc = gc;
                event.setBoundsInPixels(new Rectangle(rect.left, rect.top, width, height));
                this.sendEvent(9, event);
                event.gc = null;
            }
            gc.dispose();
            OS.ShowCaret(hwnd);
        }
        OS.DeleteObject(rgn);
        if (result == 0L) {
            return LRESULT.ZERO;
        }
        return new LRESULT(result);
    }
    
    LRESULT wmPrint(final long hwnd, final long wParam, final long lParam) {
        if ((lParam & 0x2L) != 0x0L && OS.IsAppThemed()) {
            final int bits = OS.GetWindowLong(hwnd, -20);
            if ((bits & 0x200) != 0x0) {
                final long code = this.callWindowProc(hwnd, 791, wParam, lParam);
                final RECT rect = new RECT();
                OS.GetWindowRect(hwnd, rect);
                final RECT rect6;
                final RECT rect2 = rect6 = rect;
                rect6.right -= rect.left;
                final RECT rect7;
                final RECT rect3 = rect7 = rect;
                rect7.bottom -= rect.top;
                final RECT rect4 = rect;
                final RECT rect5 = rect;
                final int n = 0;
                rect5.top = 0;
                rect4.left = 0;
                final int border = OS.GetSystemMetrics(45);
                OS.ExcludeClipRect(wParam, border, border, rect.right - border, rect.bottom - border);
                OS.DrawThemeBackground(this.display.hEditTheme(), wParam, 1, 1, rect, (RECT)null);
                return new LRESULT(code);
            }
        }
        return null;
    }
    
    LRESULT wmRButtonDblClk(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = null;
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 3, hwnd, lParam);
        if (this.sendMouseEvent(8, 3, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 518, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwnd) {
            OS.SetCapture(hwnd);
        }
        return result;
    }
    
    LRESULT wmRButtonDown(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = null;
        final Display display = this.display;
        display.captureChanged = false;
        if (this.sendMouseEvent(3, 3, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 516, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwnd) {
            OS.SetCapture(hwnd);
        }
        return result;
    }
    
    LRESULT wmRButtonUp(final long hwnd, final long wParam, final long lParam) {
        final Display display = this.display;
        LRESULT result = null;
        if (this.sendMouseEvent(4, 3, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 517, wParam, lParam));
        }
        else {
            OS.DefWindowProc(hwnd, 517, wParam, lParam);
            result = LRESULT.ZERO;
        }
        int mask = 19;
        if (display.xMouse) {
            mask |= 0x60;
        }
        if ((wParam & (long)mask) == 0x0L && OS.GetCapture() == hwnd) {
            OS.ReleaseCapture();
        }
        return result;
    }
    
    LRESULT wmSetFocus(final long hwnd, final long wParam, final long lParam) {
        final long code = this.callWindowProc(hwnd, 7, wParam, lParam);
        this.sendFocusEvent(15);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (code == 0L) {
            return LRESULT.ZERO;
        }
        return new LRESULT(code);
    }
    
    LRESULT wmSysChar(final long hwnd, final long wParam, final long lParam) {
        final Display display = this.display;
        display.lastAscii = (int)wParam;
        display.lastNull = (wParam == 0L);
        if (!this.hooks(1) && !display.filters(1)) {
            return null;
        }
        final boolean oldKeyHit = display.mnemonicKeyHit;
        display.mnemonicKeyHit = true;
        final long result = this.callWindowProc(hwnd, 262, wParam, lParam);
        boolean consumed = false;
        if (!display.mnemonicKeyHit) {
            consumed = !this.sendKeyEvent(1, 262, wParam, lParam);
        }
        consumed |= display.mnemonicKeyHit;
        display.mnemonicKeyHit = oldKeyHit;
        return consumed ? LRESULT.ONE : new LRESULT(result);
    }
    
    LRESULT wmSysKeyDown(final long hwnd, final long wParam, final long lParam) {
        if (wParam != 121L && (lParam & 0x20000000L) == 0x0L) {
            return null;
        }
        switch ((int)wParam) {
            case 115: {
                long hwndShell;
                for (hwndShell = hwnd; OS.GetParent(hwndShell) != 0L && OS.GetWindow(hwndShell, 4) == 0L; hwndShell = OS.GetParent(hwndShell)) {}
                final int bits = OS.GetWindowLong(hwndShell, -16);
                if ((bits & 0x80000) != 0x0) {
                    return null;
                }
                break;
            }
        }
        switch ((int)wParam) {
            case 16:
            case 17:
            case 18:
            case 20:
            case 144:
            case 145: {
                if ((lParam & 0x40000000L) != 0x0L) {
                    return null;
                }
                break;
            }
        }
        final Display display = this.display;
        final Display display2 = this.display;
        final int n = 0;
        display2.lastKey = 0;
        display.lastAscii = 0;
        final Display display3 = this.display;
        final Display display4 = this.display;
        final Display display5 = this.display;
        final boolean lastVirtual = false;
        display5.lastDead = false;
        display4.lastNull = false;
        display3.lastVirtual = false;
        final int mapKey = OS.MapVirtualKey((int)wParam, 2);
        if (!(this.display.lastVirtual = (mapKey == 0 || this.display.numpadKey((int)wParam) != 0))) {
            this.display.lastKey = (int)OS.CharLower((long)(short)mapKey);
            return null;
        }
        this.display.lastKey = (int)wParam;
        if (this.display.lastKey == 46) {
            this.display.lastAscii = 127;
        }
        if (96 <= this.display.lastKey && this.display.lastKey <= 111) {
            switch (this.display.lastKey) {
                case 106:
                case 107:
                case 109:
                case 110:
                case 111: {
                    return null;
                }
                default: {
                    this.display.lastAscii = this.display.numpadKey(this.display.lastKey);
                    break;
                }
            }
        }
        if (!this.sendKeyEvent(1, 260, wParam, lParam)) {
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT wmSysKeyUp(final long hwnd, final long wParam, final long lParam) {
        return this.wmKeyUp(hwnd, wParam, lParam);
    }
    
    LRESULT wmXButtonDblClk(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = null;
        final Display display = this.display;
        display.captureChanged = false;
        final int button = (OS.HIWORD(wParam) == 1) ? 4 : 5;
        this.sendMouseEvent(3, button, hwnd, lParam);
        if (this.sendMouseEvent(8, button, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 525, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwnd) {
            OS.SetCapture(hwnd);
        }
        return result;
    }
    
    LRESULT wmXButtonDown(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = null;
        final Display display = this.display;
        display.captureChanged = false;
        display.xMouse = true;
        final int button = (OS.HIWORD(wParam) == 1) ? 4 : 5;
        if (this.sendMouseEvent(3, button, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 523, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwnd) {
            OS.SetCapture(hwnd);
        }
        return result;
    }
    
    LRESULT wmXButtonUp(final long hwnd, final long wParam, final long lParam) {
        final Display display = this.display;
        LRESULT result = null;
        final int button = (OS.HIWORD(wParam) == 1) ? 4 : 5;
        if (this.sendMouseEvent(4, button, hwnd, lParam)) {
            result = new LRESULT(this.callWindowProc(hwnd, 524, wParam, lParam));
        }
        else {
            result = LRESULT.ZERO;
        }
        int mask = 19;
        if (display.xMouse) {
            mask |= 0x60;
        }
        if ((wParam & (long)mask) == 0x0L && OS.GetCapture() == hwnd) {
            OS.ReleaseCapture();
        }
        return result;
    }
    
    void notifyCreationTracker() {
        if (WidgetSpy.isEnabled) {
            WidgetSpy.getInstance().widgetCreated(this);
        }
    }
    
    void notifyDisposalTracker() {
        if (WidgetSpy.isEnabled) {
            WidgetSpy.getInstance().widgetDisposed(this);
        }
    }
    
    static {
        final INITCOMMONCONTROLSEX icce = new INITCOMMONCONTROLSEX();
        icce.dwSize = INITCOMMONCONTROLSEX.sizeof;
        icce.dwICC = 65535;
        OS.InitCommonControlsEx(icce);
    }
    
    class MouseWheelData
    {
        int count;
        int detail;
        
        MouseWheelData(final boolean isVertical, final ScrollBar scrollBar, final long wParam, final Point remainder) {
            int delta = OS.GET_WHEEL_DELTA_WPARAM(wParam);
            if (isVertical) {
                final int[] wheelSpeed = { 0 };
                OS.SystemParametersInfo(104, 0, wheelSpeed, 0);
                if (wheelSpeed[0] == -1) {
                    this.detail = 2;
                }
                else {
                    delta *= wheelSpeed[0];
                    this.detail = 1;
                }
            }
            else {
                final int[] wheelSpeed = { 0 };
                OS.SystemParametersInfo(108, 0, wheelSpeed, 0);
                delta *= wheelSpeed[0];
                this.detail = 0;
            }
            if (scrollBar != null) {
                if (this.detail == 2) {
                    delta *= scrollBar.getPageIncrement();
                }
                else {
                    delta *= scrollBar.getIncrement();
                }
            }
            if (isVertical) {
                if ((delta ^ remainder.y) >= 0) {
                    delta += remainder.y;
                }
                remainder.y = delta % 120;
            }
            else {
                if ((delta ^ remainder.x) >= 0) {
                    delta += remainder.x;
                }
                remainder.x = delta % 120;
            }
            this.count = delta / 120;
        }
    }
}
