//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class Table extends Composite
{
    TableItem[] items;
    int[] keys;
    TableColumn[] columns;
    int columnCount;
    int customCount;
    int keyCount;
    ImageList imageList;
    ImageList headerImageList;
    TableItem currentItem;
    TableColumn sortColumn;
    RECT focusRect;
    boolean[] columnVisible;
    long headerToolTipHandle;
    long hwndHeader;
    long itemToolTipHandle;
    boolean ignoreCustomDraw;
    boolean ignoreDrawForeground;
    boolean ignoreDrawBackground;
    boolean ignoreDrawFocus;
    boolean ignoreDrawSelection;
    boolean ignoreDrawHot;
    boolean customDraw;
    boolean dragStarted;
    boolean explorerTheme;
    boolean firstColumnImage;
    boolean fixScrollWidth;
    boolean tipRequested;
    boolean wasSelected;
    boolean wasResized;
    boolean painted;
    boolean ignoreActivate;
    boolean ignoreSelect;
    boolean ignoreShrink;
    boolean ignoreResize;
    boolean ignoreColumnMove;
    boolean ignoreColumnResize;
    boolean fullRowSelect;
    boolean settingItemHeight;
    boolean headerItemDragging;
    int itemHeight;
    int lastIndexOf;
    int lastWidth;
    int sortDirection;
    int resizeCount;
    int selectionForeground;
    int hotIndex;
    int headerBackground;
    int headerForeground;
    static long HeaderProc;
    static final int INSET = 4;
    static final int GRID_WIDTH = 1;
    static final int SORT_WIDTH = 10;
    static final int HEADER_MARGIN = 12;
    static final int HEADER_EXTRA = 3;
    static final int VISTA_EXTRA = 2;
    static final int EXPLORER_EXTRA = 2;
    static final int H_SCROLL_LIMIT = 32;
    static final int V_SCROLL_LIMIT = 16;
    static final int DRAG_IMAGE_SIZE = 301;
    static boolean COMPRESS_ITEMS;
    static final long TableProc;
    static final TCHAR TableClass;
    static final TCHAR HeaderClass;
    
    public Table(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.headerBackground = -1;
        this.headerForeground = -1;
    }
    
    void _addListener(final int eventType, final Listener listener) {
        super._addListener(eventType, listener);
        switch (eventType) {
            case 40:
            case 41:
            case 42: {
                this.setCustomDraw(true);
                this.setBackgroundTransparent(true);
                break;
            }
        }
    }
    
    boolean _checkGrow(final int count) {
        if (this.keys == null) {
            if (count == this.items.length) {
                final boolean small = this.getDrawing() && OS.IsWindowVisible(this.handle);
                final int length = small ? (this.items.length + 4) : Math.max(4, this.items.length * 3 / 2);
                final TableItem[] newItems = new TableItem[length];
                System.arraycopy(this.items, 0, newItems, 0, this.items.length);
                this.items = newItems;
            }
        }
        else {
            if (!this.ignoreShrink && this.keyCount > count / 2) {
                final boolean small = this.getDrawing() && OS.IsWindowVisible(this.handle);
                final int length = small ? (count + 4) : Math.max(4, count * 3 / 2);
                final TableItem[] newItems = new TableItem[length];
                for (int i = 0; i < this.keyCount; ++i) {
                    newItems[this.keys[i]] = this.items[i];
                }
                this.items = newItems;
                this.keys = null;
                this.keyCount = 0;
                return true;
            }
            if (this.keyCount == this.keys.length) {
                final boolean small = this.getDrawing() && OS.IsWindowVisible(this.handle);
                final int length = small ? (this.keys.length + 4) : Math.max(4, this.keys.length * 3 / 2);
                final int[] newKeys = new int[length];
                System.arraycopy(this.keys, 0, newKeys, 0, this.keys.length);
                this.keys = newKeys;
                final TableItem[] newItems2 = new TableItem[length];
                System.arraycopy(this.items, 0, newItems2, 0, this.items.length);
                this.items = newItems2;
            }
        }
        return false;
    }
    
    void _checkShrink() {
        if (this.keys == null) {
            if (!this.ignoreShrink) {
                int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
                if (count == 0 && this.items.length > 4) {
                    while (count < this.items.length && this.items[count] != null && !this.items[count].isDisposed()) {
                        ++count;
                    }
                }
                if (this.items.length > 4 && this.items.length - count > 3) {
                    final int length = Math.max(4, (count + 3) / 4 * 4);
                    final TableItem[] newItems = new TableItem[length];
                    System.arraycopy(this.items, 0, newItems, 0, count);
                    this.items = newItems;
                }
            }
        }
        else if (!this.ignoreShrink && this.keys.length > 4 && this.keys.length - this.keyCount > 3) {
            final int length2 = Math.max(4, (this.keyCount + 3) / 4 * 4);
            final int[] newKeys = new int[length2];
            System.arraycopy(this.keys, 0, newKeys, 0, this.keyCount);
            this.keys = newKeys;
            final TableItem[] newItems = new TableItem[length2];
            System.arraycopy(this.items, 0, newItems, 0, this.keyCount);
            this.items = newItems;
        }
    }
    
    void _clearItems() {
        this.items = null;
        this.keys = null;
        this.keyCount = 0;
    }
    
    TableItem _getItem(final int index) {
        return this._getItem(index, true);
    }
    
    TableItem _getItem(final int index, final boolean create) {
        return this._getItem(index, create, -1);
    }
    
    TableItem _getItem(final int index, final boolean create, int count) {
        if (this.keys == null) {
            if (index >= this.items.length) {
                return null;
            }
            if ((this.style & 0x10000000) == 0x0 || !create) {
                return this.items[index];
            }
            if (this.items[index] != null) {
                return this.items[index];
            }
            return this.items[index] = new TableItem(this, 0, -1, false);
        }
        else {
            if ((this.style & 0x10000000) == 0x0 || !create) {
                if (this.keyCount == 0) {
                    return null;
                }
                if (index > this.keys[this.keyCount - 1]) {
                    return null;
                }
            }
            int keyIndex = this.binarySearch(this.keys, 0, this.keyCount, index);
            if ((this.style & 0x10000000) == 0x0 || !create) {
                return (keyIndex < 0) ? null : this.items[keyIndex];
            }
            if (keyIndex < 0) {
                if (count == -1) {
                    count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
                }
                if (this._checkGrow(count)) {
                    if (this.items[index] != null) {
                        return this.items[index];
                    }
                    return this.items[index] = new TableItem(this, 0, -1, false);
                }
                else {
                    keyIndex = -keyIndex - 1;
                    if (keyIndex < this.keyCount) {
                        System.arraycopy(this.keys, keyIndex, this.keys, keyIndex + 1, this.keyCount - keyIndex);
                        System.arraycopy(this.items, keyIndex, this.items, keyIndex + 1, this.keyCount - keyIndex);
                    }
                    ++this.keyCount;
                    this.keys[keyIndex] = index;
                }
            }
            else if (this.items[keyIndex] != null) {
                return this.items[keyIndex];
            }
            return this.items[keyIndex] = new TableItem(this, 0, -1, false);
        }
    }
    
    void _getItems(final TableItem[] result, final int count) {
        if (this.keys == null) {
            System.arraycopy(this.items, 0, result, 0, count);
        }
        else {
            for (int i = 0; i < this.keyCount; ++i) {
                if (this.keys[i] >= count) {
                    break;
                }
                result[this.keys[i]] = this.items[this.keys[i]];
            }
        }
    }
    
    boolean _hasItems() {
        return this.items != null;
    }
    
    void _initItems() {
        this.items = new TableItem[4];
        if (Table.COMPRESS_ITEMS && (this.style & 0x10000000) != 0x0) {
            this.keyCount = 0;
            this.keys = new int[4];
        }
    }
    
    void _insertItem(final int index, final TableItem item, final int count) {
        if (this.keys == null) {
            System.arraycopy(this.items, index, this.items, index + 1, count - index);
            this.items[index] = item;
        }
        else {
            int keyIndex = this.binarySearch(this.keys, 0, this.keyCount, index);
            if (keyIndex < 0) {
                keyIndex = -keyIndex - 1;
            }
            System.arraycopy(this.keys, keyIndex, this.keys, keyIndex + 1, this.keyCount - keyIndex);
            this.keys[keyIndex] = index;
            System.arraycopy(this.items, keyIndex, this.items, keyIndex + 1, this.keyCount - keyIndex);
            this.items[keyIndex] = item;
            ++this.keyCount;
            for (int i = keyIndex + 1; i < this.keyCount; ++i) {
                final int[] keys = this.keys;
                final int n = i;
                final int[] array = keys;
                final int n2 = n;
                ++array[n2];
            }
        }
    }
    
    void _removeItem(final int index, int count) {
        if (this.keys == null) {
            System.arraycopy(this.items, index + 1, this.items, index, --count - index);
            this.items[count] = null;
        }
        else {
            int keyIndex = this.binarySearch(this.keys, 0, this.keyCount, index);
            if (keyIndex < 0) {
                keyIndex = -keyIndex - 1;
            }
            else {
                --this.keyCount;
                System.arraycopy(this.keys, keyIndex + 1, this.keys, keyIndex, this.keyCount - keyIndex);
                this.keys[this.keyCount] = 0;
                System.arraycopy(this.items, keyIndex + 1, this.items, keyIndex, this.keyCount - keyIndex);
                this.items[this.keyCount] = null;
            }
            for (int i = keyIndex; i < this.keyCount; ++i) {
                final int[] keys = this.keys;
                final int n = i;
                final int[] array = keys;
                final int n2 = n;
                --array[n2];
            }
        }
    }
    
    void _removeItems(final int start, final int index, final int count) {
        if (this.keys == null) {
            System.arraycopy(this.items, index, this.items, start, count - index);
            for (int i = count - (index - start); i < count; ++i) {
                this.items[i] = null;
            }
        }
        else {
            final int end = index;
            int left = this.binarySearch(this.keys, 0, this.keyCount, start);
            if (left < 0) {
                left = -left - 1;
            }
            int right = this.binarySearch(this.keys, left, this.keyCount, end);
            if (right < 0) {
                right = -right - 1;
            }
            System.arraycopy(this.keys, right, this.keys, left, this.keyCount - right);
            for (int j = this.keyCount - (right - left); j < this.keyCount; ++j) {
                this.keys[j] = 0;
            }
            System.arraycopy(this.items, right, this.items, left, this.keyCount - right);
            for (int j = this.keyCount - (right - left); j < this.keyCount; ++j) {
                this.items[j] = null;
            }
            this.keyCount -= right - left;
            for (int j = left; j < this.keyCount; ++j) {
                final int[] keys = this.keys;
                final int n = j;
                final int[] array = keys;
                final int n2 = n;
                array[n2] -= right - left;
            }
        }
    }
    
    void _setItemCount(final int count, final int itemCount) {
        if (this.keys == null) {
            final int length = Math.max(4, (count + 3) / 4 * 4);
            final TableItem[] newItems = new TableItem[length];
            System.arraycopy(this.items, 0, newItems, 0, Math.min(count, itemCount));
            this.items = newItems;
        }
        else {
            final int index = Math.min(count, itemCount);
            this.keyCount = this.binarySearch(this.keys, 0, this.keyCount, index);
            if (this.keyCount < 0) {
                this.keyCount = -this.keyCount - 1;
            }
            final int length2 = Math.max(4, (this.keyCount + 3) / 4 * 4);
            final int[] newKeys = new int[length2];
            System.arraycopy(this.keys, 0, newKeys, 0, this.keyCount);
            this.keys = newKeys;
            final TableItem[] newItems2 = new TableItem[length2];
            System.arraycopy(this.items, 0, newItems2, 0, this.keyCount);
            this.items = newItems2;
        }
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(13, (Listener)typedListener);
        this.addListener(14, (Listener)typedListener);
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        return this.callWindowProc(hwnd, msg, wParam, lParam, false);
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam, final boolean forceSelect) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (this.hwndHeader != 0L && hwnd == this.hwndHeader) {
            return OS.CallWindowProc(Table.HeaderProc, hwnd, msg, wParam, lParam);
        }
        int topIndex = 0;
        boolean checkSelection = false;
        boolean checkActivate = false;
        boolean redraw = false;
        switch (msg) {
            case 256: {
                checkActivate = true;
            }
            case 71:
            case 257:
            case 258:
            case 260:
            case 261:
            case 262:
            case 276:
            case 277:
            case 646: {
                redraw = (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle));
                if (redraw) {
                    OS.DefWindowProc(this.handle, 11, 0L, 0L);
                    OS.SendMessage(this.handle, 4097, 0L, 16777215L);
                }
            }
            case 512:
            case 513:
            case 514:
            case 515:
            case 516:
            case 517:
            case 518:
            case 519:
            case 520:
            case 521:
            case 522:
            case 523:
            case 524:
            case 525:
            case 673:
            case 675: {
                checkSelection = true;
            }
            case 48:
            case 275: {
                if (this.findImageControl() != null) {
                    topIndex = (int)OS.SendMessage(this.handle, 4135, 0L, 0L);
                    break;
                }
                break;
            }
        }
        final boolean oldSelected = this.wasSelected;
        if (checkSelection) {
            this.wasSelected = false;
        }
        if (checkActivate) {
            this.ignoreActivate = true;
        }
        boolean fixPaint = false;
        if (msg == 15) {
            final int bits0 = OS.GetWindowLong(this.handle, -16);
            if ((bits0 & 0x4000) == 0x0) {
                long hwndParent = OS.GetParent(this.handle);
                long hwndOwner = 0L;
                while (hwndParent != 0L) {
                    final int bits2 = OS.GetWindowLong(hwndParent, -20);
                    if ((bits2 & 0x2000000) != 0x0) {
                        fixPaint = true;
                        break;
                    }
                    hwndOwner = OS.GetWindow(hwndParent, 4);
                    if (hwndOwner != 0L) {
                        break;
                    }
                    hwndParent = OS.GetParent(hwndParent);
                }
            }
        }
        boolean fixScroll = false;
        if ((this.style & 0x100) == 0x0 || (this.style & 0x200) == 0x0) {
            switch (msg) {
                case 15:
                case 70:
                case 133: {
                    int bits3 = OS.GetWindowLong(hwnd, -16);
                    if ((this.style & 0x100) == 0x0 && (bits3 & 0x100000) != 0x0) {
                        fixScroll = true;
                        bits3 &= 0xFFEFFFFF;
                    }
                    if ((this.style & 0x200) == 0x0 && (bits3 & 0x200000) != 0x0) {
                        fixScroll = true;
                        bits3 &= 0xFFDFFFFF;
                    }
                    if (fixScroll) {
                        OS.SetWindowLong(this.handle, -16, bits3);
                        break;
                    }
                    break;
                }
            }
        }
        long code = 0L;
        if (fixPaint) {
            final PAINTSTRUCT ps = new PAINTSTRUCT();
            final long hDC = OS.BeginPaint(hwnd, ps);
            code = OS.CallWindowProc(Table.TableProc, hwnd, 15, hDC, lParam);
            OS.EndPaint(hwnd, ps);
        }
        else {
            code = OS.CallWindowProc(Table.TableProc, hwnd, msg, wParam, lParam);
        }
        if (fixScroll) {
            final int flags = 1025;
            OS.RedrawWindow(this.handle, (RECT)null, 0L, 1025);
        }
        if (checkActivate) {
            this.ignoreActivate = false;
        }
        if (checkSelection) {
            if (this.wasSelected || forceSelect) {
                final Event event = new Event();
                final int index = (int)OS.SendMessage(this.handle, 4108, -1L, 1L);
                if (index != -1) {
                    event.item = (Widget)this._getItem(index);
                }
                this.sendSelectionEvent(13, event, false);
            }
            this.wasSelected = oldSelected;
        }
        Label_1142: {
            switch (msg) {
                case 71:
                case 256:
                case 257:
                case 258:
                case 260:
                case 261:
                case 262:
                case 276:
                case 277:
                case 646: {
                    if (!redraw) {
                        break Label_1142;
                    }
                    OS.SendMessage(this.handle, 4097, 0L, -1L);
                    OS.DefWindowProc(this.handle, 11, 1L, 0L);
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                    final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                    if (hwndHeader != 0L) {
                        OS.InvalidateRect(hwndHeader, (RECT)null, true);
                    }
                    break Label_1142;
                }
                case 48:
                case 275:
                case 512:
                case 513:
                case 514:
                case 515:
                case 516:
                case 517:
                case 518:
                case 519:
                case 520:
                case 521:
                case 522:
                case 523:
                case 524:
                case 525:
                case 673:
                case 675: {
                    if (this.findImageControl() != null && topIndex != OS.SendMessage(this.handle, 4135, 0L, 0L)) {
                        OS.InvalidateRect(this.handle, (RECT)null, true);
                        break;
                    }
                    break;
                }
                case 15: {
                    this.painted = true;
                    break;
                }
            }
        }
        return code;
    }
    
    static int checkStyle(int style) {
        if ((style & 0x10) == 0x0) {
            style |= 0x300;
        }
        return Widget.checkBits(style, 4, 2, 0, 0, 0, 0);
    }
    
    LRESULT CDDS_ITEMPOSTPAINT(final NMLVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        final long hDC = nmcd.hdc;
        if (this.explorerTheme && !this.ignoreCustomDraw) {
            this.hotIndex = -1;
            if (this.hooks(40) && nmcd.left != nmcd.right) {
                OS.RestoreDC(hDC, -1);
            }
        }
        if (!this.ignoreCustomDraw && !this.ignoreDrawFocus && nmcd.left != nmcd.right && OS.IsWindowVisible(this.handle) && OS.IsWindowEnabled(this.handle) && !this.explorerTheme && (this.style & 0x10000) != 0x0 && (int)OS.SendMessage(this.handle, 4096, 0L, 0L) == -1) {
            final int dwExStyle = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
            if ((dwExStyle & 0x20) == 0x0 && OS.SendMessage(this.handle, 4108, -1L, 1L) == nmcd.dwItemSpec && this.handle == OS.GetFocus()) {
                final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                if ((uiState & 0x1) == 0x0) {
                    final RECT rect = new RECT();
                    rect.left = 0;
                    final boolean oldIgnore = this.ignoreCustomDraw;
                    this.ignoreCustomDraw = true;
                    OS.SendMessage(this.handle, 4110, nmcd.dwItemSpec, rect);
                    final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                    final int index = (int)OS.SendMessage(hwndHeader, 4623, 0L, 0L);
                    final RECT itemRect = new RECT();
                    if (index == 0) {
                        itemRect.left = 2;
                        OS.SendMessage(this.handle, 4110, (long)index, itemRect);
                    }
                    else {
                        itemRect.top = index;
                        itemRect.left = 1;
                        OS.SendMessage(this.handle, 4152, nmcd.dwItemSpec, itemRect);
                    }
                    this.ignoreCustomDraw = oldIgnore;
                    rect.left = itemRect.left;
                    OS.DrawFocusRect(nmcd.hdc, rect);
                }
            }
        }
        return null;
    }
    
    LRESULT CDDS_ITEMPREPAINT(final NMLVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        if (!this.ignoreCustomDraw && OS.IsWindowVisible(this.handle) && OS.IsWindowEnabled(this.handle) && !this.explorerTheme && (this.style & 0x10000) != 0x0 && (int)OS.SendMessage(this.handle, 4096, 0L, 0L) == -1) {
            final int dwExStyle = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
            if ((dwExStyle & 0x20) == 0x0 && (nmcd.uItemState & 0x10) != 0x0) {
                nmcd.uItemState &= 0xFFFFFFEF;
                OS.MoveMemory(lParam, nmcd, NMLVCUSTOMDRAW.sizeof);
            }
        }
        if (this.explorerTheme && !this.ignoreCustomDraw) {
            this.hotIndex = (((nmcd.uItemState & 0x40) != 0x0) ? ((int)nmcd.dwItemSpec) : -1);
            if (this.hooks(40) && nmcd.left != nmcd.right) {
                OS.SaveDC(nmcd.hdc);
                final long hrgn = OS.CreateRectRgn(0, 0, 0, 0);
                OS.SelectClipRgn(nmcd.hdc, hrgn);
                OS.DeleteObject(hrgn);
            }
        }
        return new LRESULT(48L);
    }
    
    LRESULT CDDS_POSTPAINT(final NMLVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        if (this.ignoreCustomDraw) {
            return null;
        }
        final int customCount = this.customCount - 1;
        this.customCount = customCount;
        if (customCount == 0 && OS.IsWindowVisible(this.handle) && !this.explorerTheme && (this.style & 0x10000) != 0x0 && (int)OS.SendMessage(this.handle, 4096, 0L, 0L) == -1) {
            final int dwExStyle = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
            if ((dwExStyle & 0x20) == 0x0) {
                final int bits = 32;
                long hwndToolTip = OS.SendMessage(this.handle, 4170, 0L, 0L);
                final long rgn = OS.CreateRectRgn(0, 0, 0, 0);
                final int result = OS.GetUpdateRgn(this.handle, rgn, true);
                OS.SendMessage(this.handle, 4150, 32L, 32L);
                OS.ValidateRect(this.handle, (RECT)null);
                if (result != 1) {
                    OS.InvalidateRgn(this.handle, rgn, true);
                }
                OS.DeleteObject(rgn);
                hwndToolTip = OS.SendMessage(this.handle, 4170, hwndToolTip, hwndToolTip);
            }
        }
        return null;
    }
    
    LRESULT CDDS_PREPAINT(final NMLVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        if (this.ignoreCustomDraw) {
            return new LRESULT(48L);
        }
        if (this.customCount++ == 0 && OS.IsWindowVisible(this.handle) && !this.explorerTheme && (this.style & 0x10000) != 0x0 && (int)OS.SendMessage(this.handle, 4096, 0L, 0L) == -1) {
            final int dwExStyle = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
            if ((dwExStyle & 0x20) != 0x0) {
                final int bits = 32;
                long hwndToolTip = OS.SendMessage(this.handle, 4170, 0L, 0L);
                final long rgn = OS.CreateRectRgn(0, 0, 0, 0);
                final int result = OS.GetUpdateRgn(this.handle, rgn, true);
                OS.SendMessage(this.handle, 4150, 32L, 0L);
                OS.ValidateRect(this.handle, (RECT)null);
                if (result != 1) {
                    OS.InvalidateRgn(this.handle, rgn, true);
                }
                OS.DeleteObject(rgn);
                hwndToolTip = OS.SendMessage(this.handle, 4170, hwndToolTip, hwndToolTip);
            }
        }
        if (OS.IsWindowVisible(this.handle)) {
            final RECT rect = new RECT();
            OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
            if (this.explorerTheme && this.columnCount == 0) {
                final long hDC = nmcd.hdc;
                if (OS.IsWindowEnabled(this.handle) || this.findImageControl() != null || this.hasCustomBackground()) {
                    this.drawBackground(hDC, rect);
                }
                else {
                    this.fillBackground(hDC, OS.GetSysColor(15), rect);
                }
            }
            else {
                Control control = this.findBackgroundControl();
                if (control != null && control.backgroundImage != null) {
                    this.fillImageBackground(nmcd.hdc, control, rect, 0, 0);
                }
                else {
                    final boolean enabled = OS.IsWindowEnabled(this.handle);
                    if ((enabled && (int)OS.SendMessage(this.handle, 4096, 0L, 0L) == -1) || (!enabled && this.hasCustomBackground())) {
                        if (control == null) {
                            control = (Control)this;
                        }
                        this.fillBackground(nmcd.hdc, control.getBackgroundPixel(), rect);
                        if (OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0) {
                            final int index = this.indexOf(this.sortColumn);
                            if (index != -1) {
                                this.parent.forceResize();
                                final int clrSortBk = this.getSortColumnPixel();
                                final RECT columnRect = new RECT();
                                final RECT headerRect = new RECT();
                                OS.GetClientRect(this.handle, columnRect);
                                final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                                if (OS.SendMessage(hwndHeader, 4615, (long)index, headerRect) != 0L) {
                                    OS.MapWindowPoints(hwndHeader, this.handle, headerRect, 2);
                                    columnRect.left = headerRect.left;
                                    columnRect.right = headerRect.right;
                                    if (OS.IntersectRect(columnRect, columnRect, rect)) {
                                        this.fillBackground(nmcd.hdc, clrSortBk, columnRect);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return new LRESULT(48L);
    }
    
    LRESULT CDDS_SUBITEMPOSTPAINT(final NMLVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        if (this.ignoreCustomDraw) {
            return null;
        }
        if (nmcd.left == nmcd.right) {
            return new LRESULT(0L);
        }
        final long hDC = nmcd.hdc;
        if (this.ignoreDrawForeground) {
            OS.RestoreDC(hDC, -1);
        }
        if (OS.IsWindowVisible(this.handle)) {
            if ((int)OS.SendMessage(this.handle, 4096, 0L, 0L) != -1 && (this.sortDirection & 0x480) != 0x0 && this.sortColumn != null && !this.sortColumn.isDisposed()) {
                final int oldColumn = (int)OS.SendMessage(this.handle, 4270, 0L, 0L);
                if (oldColumn == -1) {
                    final int newColumn = this.indexOf(this.sortColumn);
                    final long rgn = OS.CreateRectRgn(0, 0, 0, 0);
                    final int result = OS.GetUpdateRgn(this.handle, rgn, true);
                    OS.SendMessage(this.handle, 4236, (long)newColumn, 0L);
                    OS.ValidateRect(this.handle, (RECT)null);
                    if (result != 1) {
                        OS.InvalidateRgn(this.handle, rgn, true);
                    }
                    OS.DeleteObject(rgn);
                }
            }
            if (this.hooks(42)) {
                final TableItem item = this._getItem((int)nmcd.dwItemSpec);
                this.sendPaintItemEvent(item, nmcd);
            }
            if (!this.ignoreDrawFocus && this.focusRect != null) {
                OS.SetTextColor(nmcd.hdc, 0);
                OS.SetBkColor(nmcd.hdc, 16777215);
                OS.DrawFocusRect(nmcd.hdc, this.focusRect);
                this.focusRect = null;
            }
        }
        return null;
    }
    
    LRESULT CDDS_SUBITEMPREPAINT(final NMLVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        final long hDC = nmcd.hdc;
        if (this.explorerTheme && !this.ignoreCustomDraw && this.hooks(40) && nmcd.left != nmcd.right) {
            OS.RestoreDC(hDC, -1);
        }
        final TableItem item = this._getItem((int)nmcd.dwItemSpec);
        if (item == null || item.isDisposed()) {
            return null;
        }
        long hFont = item.fontHandle(nmcd.iSubItem);
        if (hFont != -1L) {
            OS.SelectObject(hDC, hFont);
        }
        if (this.ignoreCustomDraw || nmcd.left == nmcd.right) {
            return new LRESULT((hFont == -1L) ? 0L : 2L);
        }
        int code = 0;
        this.selectionForeground = -1;
        final boolean b = false;
        this.ignoreDrawBackground = false;
        this.ignoreDrawFocus = false;
        this.ignoreDrawSelection = false;
        this.ignoreDrawForeground = false;
        if (OS.IsWindowVisible(this.handle)) {
            Event measureEvent = null;
            if (this.hooks(41)) {
                measureEvent = this.sendMeasureItemEvent(item, (int)nmcd.dwItemSpec, nmcd.iSubItem, nmcd.hdc);
                if (this.isDisposed() || item.isDisposed()) {
                    return null;
                }
            }
            if (this.hooks(40)) {
                this.sendEraseItemEvent(item, nmcd, lParam, measureEvent);
                if (this.isDisposed() || item.isDisposed()) {
                    return null;
                }
                code |= 0x10;
            }
            if (this.ignoreDrawForeground || this.hooks(42)) {
                code |= 0x10;
            }
        }
        int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
        if (clrText == -1) {
            clrText = item.foreground;
        }
        int clrTextBk = (item.cellBackground != null) ? item.cellBackground[nmcd.iSubItem] : -1;
        if (clrTextBk == -1) {
            clrTextBk = item.background;
        }
        if (this.selectionForeground != -1) {
            clrText = this.selectionForeground;
        }
        final boolean enabled = OS.IsWindowEnabled(this.handle);
        if (OS.IsWindowVisible(this.handle) && enabled && !this.explorerTheme && !this.ignoreDrawSelection && (this.style & 0x10000) != 0x0) {
            final int bits = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
            if ((bits & 0x20) == 0x0) {
                final LVITEM lvItem = new LVITEM();
                lvItem.mask = 8;
                lvItem.stateMask = 2;
                lvItem.iItem = (int)nmcd.dwItemSpec;
                final long result = OS.SendMessage(this.handle, 4171, 0L, lvItem);
                if (result != 0L && (lvItem.state & 0x2) != 0x0) {
                    int clrSelection = -1;
                    if (nmcd.iSubItem == 0) {
                        if (OS.GetFocus() == this.handle || this.display.getHighContrast()) {
                            clrSelection = OS.GetSysColor(13);
                        }
                        else if ((this.style & 0x8000) == 0x0) {
                            clrSelection = OS.GetSysColor(15);
                        }
                    }
                    else if (OS.GetFocus() == this.handle || this.display.getHighContrast()) {
                        clrText = OS.GetSysColor(14);
                        clrTextBk = (clrSelection = OS.GetSysColor(13));
                    }
                    else if ((this.style & 0x8000) == 0x0) {
                        clrTextBk = (clrSelection = OS.GetSysColor(15));
                    }
                    if (clrSelection != -1) {
                        final RECT rect = item.getBounds((int)nmcd.dwItemSpec, nmcd.iSubItem, true, nmcd.iSubItem != 0, true, false, hDC);
                        this.fillBackground(hDC, clrSelection, rect);
                    }
                }
            }
        }
        if (!this.ignoreDrawForeground) {
            boolean hasAttributes = true;
            if (hFont == -1L && clrText == -1 && clrTextBk == -1 && item.cellForeground == null && item.cellBackground == null && item.cellFont == null) {
                final int count = (int)OS.SendMessage(this.hwndHeader, 4608, 0L, 0L);
                if (count == 1) {
                    hasAttributes = false;
                }
            }
            if (hasAttributes) {
                if (hFont == -1L) {
                    hFont = OS.SendMessage(this.handle, 49, 0L, 0L);
                }
                OS.SelectObject(hDC, hFont);
                if (enabled) {
                    nmcd.clrText = ((clrText == -1) ? this.getForegroundPixel() : clrText);
                    if (clrTextBk == -1) {
                        nmcd.clrTextBk = -1;
                        if (this.selectionForeground == -1) {
                            Control control = this.findBackgroundControl();
                            if (control == null) {
                                control = (Control)this;
                            }
                            if (control.backgroundImage == null && (int)OS.SendMessage(this.handle, 4096, 0L, 0L) != -1) {
                                nmcd.clrTextBk = control.getBackgroundPixel();
                            }
                        }
                    }
                    else {
                        nmcd.clrTextBk = ((this.selectionForeground != -1) ? -1 : clrTextBk);
                    }
                    OS.MoveMemory(lParam, nmcd, NMLVCUSTOMDRAW.sizeof);
                }
                code |= 0x2;
            }
        }
        if ((enabled && clrTextBk != -1) || (!enabled && this.hasCustomBackground())) {
            final int oldColumn = (int)OS.SendMessage(this.handle, 4270, 0L, 0L);
            if (oldColumn != -1 && oldColumn == nmcd.iSubItem) {
                final long rgn = OS.CreateRectRgn(0, 0, 0, 0);
                final int result2 = OS.GetUpdateRgn(this.handle, rgn, true);
                OS.SendMessage(this.handle, 4236, -1L, 0L);
                OS.ValidateRect(this.handle, (RECT)null);
                if (result2 != 1) {
                    OS.InvalidateRgn(this.handle, rgn, true);
                }
                OS.DeleteObject(rgn);
                code |= 0x10;
            }
        }
        if (!enabled) {
            nmcd.clrText = OS.GetSysColor(17);
            if (this.findImageControl() != null || this.hasCustomBackground()) {
                nmcd.clrTextBk = -1;
            }
            nmcd.uItemState &= 0xFFFFFFFE;
            OS.MoveMemory(lParam, nmcd, NMLVCUSTOMDRAW.sizeof);
            code |= 0x2;
        }
        return new LRESULT((long)code);
    }
    
    void checkBuffered() {
        super.checkBuffered();
        this.style |= 0x20000000;
    }
    
    boolean checkData(final TableItem item, final boolean redraw) {
        return (this.style & 0x10000000) == 0x0 || this.checkData(item, this.indexOf(item), redraw);
    }
    
    boolean checkData(final TableItem item, final int index, final boolean redraw) {
        if ((this.style & 0x10000000) == 0x0) {
            return true;
        }
        if (!item.cached) {
            item.cached = true;
            final Event event = new Event();
            event.item = (Widget)item;
            event.index = index;
            this.currentItem = item;
            this.sendEvent(36, event);
            this.currentItem = null;
            if (this.isDisposed() || item.isDisposed()) {
                return false;
            }
            if (redraw && !this.setScrollWidth(item, false)) {
                item.redraw();
            }
        }
        return true;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public void clear(final int index) {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (0 > index || index >= count) {
            this.error(6);
        }
        final TableItem item = this._getItem(index, false);
        if (item != null) {
            if (item != this.currentItem) {
                item.clear();
            }
            if ((this.style & 0x10000000) == 0x0 && item.cached) {
                final LVITEM lvItem = new LVITEM();
                lvItem.mask = 17;
                lvItem.pszText = -1L;
                lvItem.iItem = index;
                OS.SendMessage(this.handle, 4172, 0L, lvItem);
                item.cached = false;
            }
            if (this.currentItem == null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                OS.SendMessage(this.handle, 4117, (long)index, (long)index);
            }
            this.setScrollWidth(item, false);
        }
    }
    
    public void clear(final int start, final int end) {
        this.checkWidget();
        if (start > end) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (0 > start || start > end || end >= count) {
            this.error(6);
        }
        if (start == 0 && end == count - 1) {
            this.clearAll();
        }
        else {
            LVITEM lvItem = null;
            boolean cleared = false;
            for (int i = start; i <= end; ++i) {
                final TableItem item = this._getItem(i, false);
                if (item != null) {
                    if (item != this.currentItem) {
                        cleared = true;
                        item.clear();
                    }
                    if ((this.style & 0x10000000) == 0x0 && item.cached) {
                        if (lvItem == null) {
                            lvItem = new LVITEM();
                            lvItem.mask = 17;
                            lvItem.pszText = -1L;
                        }
                        lvItem.iItem = i;
                        OS.SendMessage(this.handle, 4172, 0L, lvItem);
                        item.cached = false;
                    }
                }
            }
            if (cleared) {
                if (this.currentItem == null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                    OS.SendMessage(this.handle, 4117, (long)start, (long)end);
                }
                final TableItem item2 = (start == end) ? this._getItem(start, false) : null;
                this.setScrollWidth(item2, false);
            }
        }
    }
    
    public void clear(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        if (indices.length == 0) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        for (int i = 0; i < indices.length; ++i) {
            if (0 > indices[i] || indices[i] >= count) {
                this.error(6);
            }
        }
        LVITEM lvItem = null;
        boolean cleared = false;
        for (int j = 0; j < indices.length; ++j) {
            final int index = indices[j];
            final TableItem item = this._getItem(index, false);
            if (item != null) {
                if (item != this.currentItem) {
                    cleared = true;
                    item.clear();
                }
                if ((this.style & 0x10000000) == 0x0 && item.cached) {
                    if (lvItem == null) {
                        lvItem = new LVITEM();
                        lvItem.mask = 17;
                        lvItem.pszText = -1L;
                    }
                    lvItem.iItem = j;
                    OS.SendMessage(this.handle, 4172, 0L, lvItem);
                    item.cached = false;
                }
                if (this.currentItem == null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                    OS.SendMessage(this.handle, 4117, (long)index, (long)index);
                }
            }
        }
        if (cleared) {
            this.setScrollWidth(null, false);
        }
    }
    
    public void clearAll() {
        this.checkWidget();
        LVITEM lvItem = null;
        boolean cleared = false;
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        for (int i = 0; i < count; ++i) {
            final TableItem item = this._getItem(i, false);
            if (item != null) {
                if (item != this.currentItem) {
                    cleared = true;
                    item.clear();
                }
                if ((this.style & 0x10000000) == 0x0 && item.cached) {
                    if (lvItem == null) {
                        lvItem = new LVITEM();
                        lvItem.mask = 17;
                        lvItem.pszText = -1L;
                    }
                    lvItem.iItem = i;
                    OS.SendMessage(this.handle, 4172, 0L, lvItem);
                    item.cached = false;
                }
            }
        }
        if (cleared) {
            if (this.currentItem == null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                OS.SendMessage(this.handle, 4117, 0L, (long)(count - 1));
            }
            this.setScrollWidth(null, false);
        }
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        if (this.fixScrollWidth) {
            this.setScrollWidth(null, true);
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.hwndHeader, rect);
        int height = rect.bottom - rect.top;
        int bits = 0;
        if (wHint != -1) {
            bits |= (wHint & 0xFFFF);
        }
        else {
            int width = 0;
            for (int count = (int)OS.SendMessage(this.hwndHeader, 4608, 0L, 0L), i = 0; i < count; ++i) {
                width += (int)OS.SendMessage(this.handle, 4125, (long)i, 0L);
            }
            bits |= (width & 0xFFFF);
        }
        final long result = OS.SendMessage(this.handle, 4160, -1L, OS.MAKELPARAM(bits, 65535));
        int width2 = OS.LOWORD(result);
        final long empty = OS.SendMessage(this.handle, 4160, 0L, 0L);
        final long oneItem = OS.SendMessage(this.handle, 4160, 1L, 0L);
        final int itemHeight = OS.HIWORD(oneItem) - OS.HIWORD(empty);
        height += (int)OS.SendMessage(this.handle, 4100, 0L, 0L) * itemHeight;
        if (width2 == 0) {
            width2 = 64;
        }
        if (height == 0) {
            height = 64;
        }
        if (wHint != -1) {
            width2 = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        final int border = this.getBorderWidthInPixels();
        width2 += border * 2;
        height += border * 2;
        if ((this.style & 0x200) != 0x0) {
            width2 += OS.GetSystemMetrics(2);
        }
        if ((this.style & 0x100) != 0x0) {
            height += OS.GetSystemMetrics(3);
        }
        return new Point(width2, height);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        if (OS.IsAppThemed()) {
            this.explorerTheme = true;
            OS.SetWindowTheme(this.handle, Display.EXPLORER, (char[])null);
        }
        this.maybeEnableDarkSystemTheme(this.hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L));
        if ((this.style & 0x20) != 0x0) {
            final long empty = OS.SendMessage(this.handle, 4160, 0L, 0L);
            final long oneItem = OS.SendMessage(this.handle, 4160, 1L, 0L);
            final int width;
            final int height = width = OS.HIWORD(oneItem) - OS.HIWORD(empty);
            this.setCheckboxImageList(width, height, false);
            OS.SendMessage(this.handle, 4107, 61440L, 0L);
        }
        final long hFont = OS.GetStockObject(13);
        OS.SendMessage(this.handle, 48, hFont, 0L);
        final LVCOLUMN lvColumn = new LVCOLUMN();
        lvColumn.mask = 6;
        final long hHeap = OS.GetProcessHeap();
        final long pszText = OS.HeapAlloc(hHeap, 8, 2);
        lvColumn.pszText = pszText;
        OS.SendMessage(this.handle, 4193, 0L, lvColumn);
        OS.HeapFree(hHeap, 0, pszText);
        int bits1 = 81920;
        if ((this.style & 0x10000) != 0x0) {
            bits1 |= 0x20;
        }
        OS.SendMessage(this.handle, 4150, (long)bits1, (long)bits1);
        if ((this.style & 0x4000000) != 0x0) {
            final int bits2 = OS.GetWindowLong(this.hwndHeader, -20);
            OS.SetWindowLong(this.hwndHeader, -20, bits2 | 0x400000);
            final long hwndTooltip = OS.SendMessage(this.handle, 4174, 0L, 0L);
            final int bits3 = OS.GetWindowLong(hwndTooltip, -20);
            OS.SetWindowLong(hwndTooltip, -20, bits3 | 0x400000);
        }
    }
    
    int applyThemeBackground() {
        return -1;
    }
    
    void createHeaderToolTips() {
        if (this.headerToolTipHandle != 0L) {
            return;
        }
        int bits = 0;
        if ((this.style & 0x4000000) != 0x0) {
            bits |= 0x400000;
        }
        this.headerToolTipHandle = OS.CreateWindowEx(bits, new TCHAR(0, "tooltips_class32", true), (TCHAR)null, 2, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, this.handle, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
        if (this.headerToolTipHandle == 0L) {
            this.error(2);
        }
        this.maybeEnableDarkSystemTheme(this.headerToolTipHandle);
        OS.SendMessage(this.headerToolTipHandle, 1048, 0L, 32767L);
    }
    
    void createItem(final TableColumn column, final int index) {
        if (0 > index || index > this.columnCount) {
            this.error(6);
        }
        final int oldColumn = (int)OS.SendMessage(this.handle, 4270, 0L, 0L);
        if (oldColumn >= index) {
            OS.SendMessage(this.handle, 4236, (long)(oldColumn + 1), 0L);
        }
        if (this.columnCount == this.columns.length) {
            final TableColumn[] newColumns = new TableColumn[this.columns.length + 4];
            System.arraycopy(this.columns, 0, newColumns, 0, this.columns.length);
            this.columns = newColumns;
        }
        final int itemCount = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        for (int i = 0; i < itemCount; ++i) {
            final TableItem item = this._getItem(i, false);
            if (item != null) {
                final String[] strings = item.strings;
                if (strings != null) {
                    final String[] temp = new String[this.columnCount + 1];
                    System.arraycopy(strings, 0, temp, 0, index);
                    System.arraycopy(strings, index, temp, index + 1, this.columnCount - index);
                    item.strings = temp;
                }
                final Image[] images = item.images;
                if (images != null) {
                    final Image[] temp2 = new Image[this.columnCount + 1];
                    System.arraycopy(images, 0, temp2, 0, index);
                    System.arraycopy(images, index, temp2, index + 1, this.columnCount - index);
                    item.images = temp2;
                }
                if (index == 0 && this.columnCount != 0) {
                    if (strings == null) {
                        (item.strings = new String[this.columnCount + 1])[1] = item.text;
                    }
                    item.text = "";
                    if (images == null) {
                        (item.images = new Image[this.columnCount + 1])[1] = item.image;
                    }
                    item.image = null;
                }
                if (item.cellBackground != null) {
                    final int[] cellBackground = item.cellBackground;
                    final int[] temp3 = new int[this.columnCount + 1];
                    System.arraycopy(cellBackground, 0, temp3, 0, index);
                    System.arraycopy(cellBackground, index, temp3, index + 1, this.columnCount - index);
                    temp3[index] = -1;
                    item.cellBackground = temp3;
                }
                if (item.cellForeground != null) {
                    final int[] cellForeground = item.cellForeground;
                    final int[] temp3 = new int[this.columnCount + 1];
                    System.arraycopy(cellForeground, 0, temp3, 0, index);
                    System.arraycopy(cellForeground, index, temp3, index + 1, this.columnCount - index);
                    temp3[index] = -1;
                    item.cellForeground = temp3;
                }
                if (item.cellFont != null) {
                    final Font[] cellFont = item.cellFont;
                    final Font[] temp4 = new Font[this.columnCount + 1];
                    System.arraycopy(cellFont, 0, temp4, 0, index);
                    System.arraycopy(cellFont, index, temp4, index + 1, this.columnCount - index);
                    item.cellFont = temp4;
                }
            }
        }
        System.arraycopy(this.columns, index, this.columns, index + 1, this.columnCount++ - index);
        this.columns[index] = column;
        this.ignoreColumnResize = true;
        if (index == 0) {
            if (this.columnCount > 1) {
                final LVCOLUMN lvColumn = new LVCOLUMN();
                lvColumn.mask = 2;
                OS.SendMessage(this.handle, 4193, 1L, lvColumn);
                OS.SendMessage(this.handle, 4191, 1L, lvColumn);
                final int width = lvColumn.cx;
                final int cchTextMax = 1024;
                final long hHeap = OS.GetProcessHeap();
                final int byteCount = 2048;
                final long pszText = OS.HeapAlloc(hHeap, 8, 2048);
                lvColumn.mask = 23;
                lvColumn.pszText = pszText;
                lvColumn.cchTextMax = 1024;
                OS.SendMessage(this.handle, 4191, 0L, lvColumn);
                OS.SendMessage(this.handle, 4192, 1L, lvColumn);
                lvColumn.fmt = 2048;
                lvColumn.cx = width;
                lvColumn.iImage = -2;
                final LVCOLUMN lvcolumn = lvColumn;
                final LVCOLUMN lvcolumn2 = lvColumn;
                final int cchTextMax2 = 0;
                lvcolumn2.cchTextMax = 0;
                lvcolumn.pszText = 0L;
                OS.SendMessage(this.handle, 4192, 0L, lvColumn);
                lvColumn.mask = 1;
                lvColumn.fmt = 0;
                OS.SendMessage(this.handle, 4192, 0L, lvColumn);
                if (pszText != 0L) {
                    OS.HeapFree(hHeap, 0, pszText);
                }
            }
            else {
                OS.SendMessage(this.handle, 4126, 0L, 0L);
            }
            if ((this.style & 0x10000000) == 0x0) {
                final LVITEM lvItem = new LVITEM();
                lvItem.mask = 3;
                lvItem.pszText = -1L;
                lvItem.iImage = -1;
                for (int j = 0; j < itemCount; ++j) {
                    lvItem.iItem = j;
                    OS.SendMessage(this.handle, 4172, 0L, lvItem);
                }
            }
        }
        else {
            int fmt = 0;
            if ((column.style & 0x1000000) == 0x1000000) {
                fmt = 2;
            }
            if ((column.style & 0x20000) == 0x20000) {
                fmt = 1;
            }
            final LVCOLUMN lvColumn2 = new LVCOLUMN();
            lvColumn2.mask = 3;
            lvColumn2.fmt = fmt;
            OS.SendMessage(this.handle, 4193, (long)index, lvColumn2);
        }
        this.ignoreColumnResize = false;
        if (this.headerToolTipHandle != 0L) {
            final RECT rect = new RECT();
            if (OS.SendMessage(this.hwndHeader, 4615, (long)index, rect) != 0L) {
                final TOOLINFO lpti = new TOOLINFO();
                lpti.cbSize = TOOLINFO.sizeof;
                lpti.uFlags = 16;
                lpti.hwnd = this.hwndHeader;
                final TOOLINFO toolinfo = lpti;
                final int id = this.display.nextToolTipId++;
                column.id = id;
                toolinfo.uId = id;
                lpti.left = rect.left;
                lpti.top = rect.top;
                lpti.right = rect.right;
                lpti.bottom = rect.bottom;
                lpti.lpszText = -1L;
                OS.SendMessage(this.headerToolTipHandle, 1074, 0L, lpti);
            }
        }
    }
    
    void createItem(final TableItem item, final int index) {
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (0 > index || index > count) {
            this.error(6);
        }
        this._checkGrow(count);
        final LVITEM lvItem = new LVITEM();
        lvItem.mask = 3;
        lvItem.iItem = index;
        lvItem.pszText = -1L;
        lvItem.iImage = -1;
        this.setDeferResize(true);
        final boolean b = true;
        this.ignoreShrink = true;
        this.ignoreSelect = true;
        final int result = (int)OS.SendMessage(this.handle, 4173, 0L, lvItem);
        final boolean b2 = false;
        this.ignoreShrink = false;
        this.ignoreSelect = false;
        if (result == -1) {
            this.error(14);
        }
        this._insertItem(index, item, count);
        this.setDeferResize(false);
        if (count == 0) {
            this.setScrollWidth(item, false);
        }
    }
    
    void createWidget() {
        super.createWidget();
        final int n = -1;
        this.hotIndex = -1;
        this.itemHeight = -1;
        this._initItems();
        this.columns = new TableColumn[4];
    }
    
    private boolean customHeaderDrawing() {
        return this.headerBackground != -1 || this.headerForeground != -1;
    }
    
    int defaultBackground() {
        return OS.GetSysColor(5);
    }
    
    void deregister() {
        super.deregister();
        if (this.hwndHeader != 0L) {
            this.display.removeControl(this.hwndHeader);
        }
    }
    
    public void deselect(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        if (indices.length == 0) {
            return;
        }
        final LVITEM lvItem = new LVITEM();
        lvItem.stateMask = 2;
        for (final int index : indices) {
            if (index >= 0) {
                this.ignoreSelect = true;
                OS.SendMessage(this.handle, 4139, (long)index, lvItem);
                this.ignoreSelect = false;
            }
        }
    }
    
    public void deselect(final int index) {
        this.checkWidget();
        if (index < 0) {
            return;
        }
        final LVITEM lvItem = new LVITEM();
        lvItem.stateMask = 2;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, (long)index, lvItem);
        this.ignoreSelect = false;
    }
    
    public void deselect(int start, final int end) {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (start == 0 && end == count - 1) {
            this.deselectAll();
        }
        else {
            final LVITEM lvItem = new LVITEM();
            lvItem.stateMask = 2;
            for (int i = start = Math.max(0, start); i <= end; ++i) {
                this.ignoreSelect = true;
                OS.SendMessage(this.handle, 4139, (long)i, lvItem);
                this.ignoreSelect = false;
            }
        }
    }
    
    public void deselectAll() {
        this.checkWidget();
        final LVITEM lvItem = new LVITEM();
        lvItem.mask = 8;
        lvItem.stateMask = 2;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, -1L, lvItem);
        this.ignoreSelect = false;
    }
    
    void destroyItem(final TableColumn column) {
        int index;
        for (index = 0; index < this.columnCount && this.columns[index] != column; ++index) {}
        final int oldColumn = (int)OS.SendMessage(this.handle, 4270, 0L, 0L);
        if (oldColumn == index) {
            OS.SendMessage(this.handle, 4236, -1L, 0L);
        }
        else if (oldColumn > index) {
            OS.SendMessage(this.handle, 4236, (long)(oldColumn - 1), 0L);
        }
        int orderIndex = 0;
        final int[] oldOrder = new int[this.columnCount];
        OS.SendMessage(this.handle, 4155, (long)this.columnCount, oldOrder);
        while (orderIndex < this.columnCount && oldOrder[orderIndex] != index) {
            ++orderIndex;
        }
        this.ignoreColumnResize = true;
        boolean first = false;
        if (index == 0) {
            first = true;
            this.setRedraw(false);
            if (this.columnCount > 1) {
                index = 1;
                final int cchTextMax = 1024;
                final long hHeap = OS.GetProcessHeap();
                final int byteCount = 2048;
                final long pszText = OS.HeapAlloc(hHeap, 8, 2048);
                final LVCOLUMN lvColumn = new LVCOLUMN();
                lvColumn.mask = 23;
                lvColumn.pszText = pszText;
                lvColumn.cchTextMax = 1024;
                OS.SendMessage(this.handle, 4191, 1L, lvColumn);
                final LVCOLUMN lvcolumn3;
                final LVCOLUMN lvcolumn = lvcolumn3 = lvColumn;
                lvcolumn3.fmt &= 0xFFFFFFFC;
                final LVCOLUMN lvcolumn4;
                final LVCOLUMN lvcolumn2 = lvcolumn4 = lvColumn;
                lvcolumn4.fmt |= 0x0;
                OS.SendMessage(this.handle, 4192, 0L, lvColumn);
                if (pszText != 0L) {
                    OS.HeapFree(hHeap, 0, pszText);
                }
            }
            else {
                final long hHeap2 = OS.GetProcessHeap();
                final long pszText2 = OS.HeapAlloc(hHeap2, 8, 2);
                final LVCOLUMN lvColumn2 = new LVCOLUMN();
                lvColumn2.mask = 23;
                lvColumn2.pszText = pszText2;
                lvColumn2.iImage = -2;
                lvColumn2.fmt = 0;
                OS.SendMessage(this.handle, 4192, 0L, lvColumn2);
                if (pszText2 != 0L) {
                    OS.HeapFree(hHeap2, 0, pszText2);
                }
                final HDITEM hdItem = new HDITEM();
                hdItem.mask = 4;
                hdItem.fmt = 0;
                final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                OS.SendMessage(hwndHeader, 4620, (long)index, hdItem);
            }
            this.setRedraw(true);
            if ((this.style & 0x10000000) == 0x0) {
                final LVITEM lvItem = new LVITEM();
                lvItem.mask = 3;
                lvItem.pszText = -1L;
                lvItem.iImage = -1;
                for (int itemCount = (int)OS.SendMessage(this.handle, 4100, 0L, 0L), i = 0; i < itemCount; ++i) {
                    lvItem.iItem = i;
                    OS.SendMessage(this.handle, 4172, 0L, lvItem);
                }
            }
        }
        if (this.columnCount > 1 && OS.SendMessage(this.handle, 4124, (long)index, 0L) == 0L) {
            this.error(15);
        }
        if (first) {
            index = 0;
        }
        System.arraycopy(this.columns, index + 1, this.columns, index, --this.columnCount - index);
        this.columns[this.columnCount] = null;
        for (int itemCount2 = (int)OS.SendMessage(this.handle, 4100, 0L, 0L), j = 0; j < itemCount2; ++j) {
            final TableItem item = this._getItem(j, false);
            if (item != null) {
                if (this.columnCount == 0) {
                    item.strings = null;
                    item.images = null;
                    item.cellBackground = null;
                    item.cellForeground = null;
                    item.cellFont = null;
                }
                else {
                    if (item.strings != null) {
                        final String[] strings = item.strings;
                        if (index == 0) {
                            item.text = ((strings[1] != null) ? strings[1] : "");
                        }
                        final String[] temp = new String[this.columnCount];
                        System.arraycopy(strings, 0, temp, 0, index);
                        System.arraycopy(strings, index + 1, temp, index, this.columnCount - index);
                        item.strings = temp;
                    }
                    else if (index == 0) {
                        item.text = "";
                    }
                    if (item.images != null) {
                        final Image[] images = item.images;
                        if (index == 0) {
                            item.image = images[1];
                        }
                        final Image[] temp2 = new Image[this.columnCount];
                        System.arraycopy(images, 0, temp2, 0, index);
                        System.arraycopy(images, index + 1, temp2, index, this.columnCount - index);
                        item.images = temp2;
                    }
                    else if (index == 0) {
                        item.image = null;
                    }
                    if (item.cellBackground != null) {
                        final int[] cellBackground = item.cellBackground;
                        final int[] temp3 = new int[this.columnCount];
                        System.arraycopy(cellBackground, 0, temp3, 0, index);
                        System.arraycopy(cellBackground, index + 1, temp3, index, this.columnCount - index);
                        item.cellBackground = temp3;
                    }
                    if (item.cellForeground != null) {
                        final int[] cellForeground = item.cellForeground;
                        final int[] temp3 = new int[this.columnCount];
                        System.arraycopy(cellForeground, 0, temp3, 0, index);
                        System.arraycopy(cellForeground, index + 1, temp3, index, this.columnCount - index);
                        item.cellForeground = temp3;
                    }
                    if (item.cellFont != null) {
                        final Font[] cellFont = item.cellFont;
                        final Font[] temp4 = new Font[this.columnCount];
                        System.arraycopy(cellFont, 0, temp4, 0, index);
                        System.arraycopy(cellFont, index + 1, temp4, index, this.columnCount - index);
                        item.cellFont = temp4;
                    }
                }
            }
        }
        if (this.columnCount == 0) {
            this.setScrollWidth(null, true);
        }
        this.updateMoveable();
        this.ignoreColumnResize = false;
        if (this.columnCount != 0) {
            int count = 0;
            final int oldIndex = oldOrder[orderIndex];
            final int[] newOrder = new int[this.columnCount];
            for (final int element : oldOrder) {
                if (element != oldIndex) {
                    final int newIndex = (element <= oldIndex) ? element : (element - 1);
                    newOrder[count++] = newIndex;
                }
            }
            OS.SendMessage(this.handle, 4155, (long)this.columnCount, oldOrder);
            int k;
            for (k = 0; k < newOrder.length && oldOrder[k] == newOrder[k]; ++k) {}
            if (k != newOrder.length) {
                OS.SendMessage(this.handle, 4154, (long)newOrder.length, newOrder);
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
            final TableColumn[] newColumns = new TableColumn[this.columnCount - orderIndex];
            for (int l = orderIndex; l < newOrder.length; ++l) {
                (newColumns[l - orderIndex] = this.columns[newOrder[l]]).updateToolTip(newOrder[l]);
            }
            for (final TableColumn newColumn : newColumns) {
                if (!newColumn.isDisposed()) {
                    newColumn.sendEvent(10);
                }
            }
        }
        if (this.headerToolTipHandle != 0L) {
            final TOOLINFO lpti = new TOOLINFO();
            lpti.cbSize = TOOLINFO.sizeof;
            lpti.uId = column.id;
            lpti.hwnd = OS.SendMessage(this.handle, 4127, 0L, 0L);
            OS.SendMessage(this.headerToolTipHandle, 1075, 0L, lpti);
        }
    }
    
    void destroyItem(final TableItem item) {
        int count;
        int index;
        for (count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L), index = 0; index < count && this._getItem(index, false) != item; ++index) {}
        if (index == count) {
            return;
        }
        this.setDeferResize(true);
        final boolean b = true;
        this.ignoreShrink = true;
        this.ignoreSelect = true;
        final long code = OS.SendMessage(this.handle, 4104, (long)index, 0L);
        final boolean b2 = false;
        this.ignoreShrink = false;
        this.ignoreSelect = false;
        if (code == 0L) {
            this.error(15);
        }
        this._removeItem(index, count);
        if (--count == 0) {
            this.setTableEmpty();
        }
        this.setDeferResize(false);
    }
    
    void fixCheckboxImageList(final boolean fixScroll) {
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        final long hImageList = OS.SendMessage(this.handle, 4098, 1L, 0L);
        if (hImageList == 0L) {
            return;
        }
        final int[] cx = { 0 };
        final int[] cy = { 0 };
        OS.ImageList_GetIconSize(hImageList, cx, cy);
        final long hStateList = OS.SendMessage(this.handle, 4098, 2L, 0L);
        if (hStateList == 0L) {
            return;
        }
        final int[] stateCx = { 0 };
        final int[] stateCy = { 0 };
        OS.ImageList_GetIconSize(hStateList, stateCx, stateCy);
        if (cx[0] == stateCx[0] && cy[0] == stateCy[0]) {
            return;
        }
        this.setCheckboxImageList(cx[0], cy[0], fixScroll);
    }
    
    void fixCheckboxImageListColor(final boolean fixScroll) {
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        final long hStateList = OS.SendMessage(this.handle, 4098, 2L, 0L);
        if (hStateList == 0L) {
            return;
        }
        final int[] cx = { 0 };
        final int[] cy = { 0 };
        OS.ImageList_GetIconSize(hStateList, cx, cy);
        this.setCheckboxImageList(cx[0], cy[0], fixScroll);
    }
    
    public TableColumn getColumn(final int index) {
        this.checkWidget();
        if (0 > index || index >= this.columnCount) {
            this.error(6);
        }
        return this.columns[index];
    }
    
    public int getColumnCount() {
        this.checkWidget();
        return this.columnCount;
    }
    
    public int[] getColumnOrder() {
        this.checkWidget();
        if (this.columnCount == 0) {
            return new int[0];
        }
        final int[] order = new int[this.columnCount];
        OS.SendMessage(this.handle, 4155, (long)this.columnCount, order);
        return order;
    }
    
    public TableColumn[] getColumns() {
        this.checkWidget();
        final TableColumn[] result = new TableColumn[this.columnCount];
        System.arraycopy(this.columns, 0, result, 0, this.columnCount);
        return result;
    }
    
    int getFocusIndex() {
        return (int)OS.SendMessage(this.handle, 4108, -1L, 1L);
    }
    
    public int getGridLineWidth() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getGridLineWidthInPixels());
    }
    
    int getGridLineWidthInPixels() {
        return 1;
    }
    
    public Color getHeaderBackground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, this.getHeaderBackgroundPixel());
    }
    
    private int getHeaderBackgroundPixel() {
        return (this.headerBackground != -1) ? this.headerBackground : this.defaultBackground();
    }
    
    public Color getHeaderForeground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, this.getHeaderForegroundPixel());
    }
    
    private int getHeaderForegroundPixel() {
        return (this.headerForeground != -1) ? this.headerForeground : this.defaultForeground();
    }
    
    public int getHeaderHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getHeaderHeightInPixels());
    }
    
    int getHeaderHeightInPixels() {
        if (this.hwndHeader == 0L) {
            return 0;
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.hwndHeader, rect);
        return rect.bottom - rect.top;
    }
    
    public boolean getHeaderVisible() {
        this.checkWidget();
        final int bits = OS.GetWindowLong(this.handle, -16);
        return (bits & 0x4000) == 0x0;
    }
    
    public TableItem getItem(final int index) {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (0 > index || index >= count) {
            this.error(6);
        }
        return this._getItem(index);
    }
    
    public TableItem getItem(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        return this.getItemInPixels(DPIUtil.autoScaleUp(point));
    }
    
    TableItem getItemInPixels(final Point point) {
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (count == 0) {
            return null;
        }
        final LVHITTESTINFO pinfo = new LVHITTESTINFO();
        pinfo.x = point.x;
        pinfo.y = point.y;
        if ((this.style & 0x10000) == 0x0 && this.hooks(41)) {
            if (OS.SendMessage(this.handle, 4153, 0L, pinfo) < 0L) {
                final RECT rect = new RECT();
                rect.left = 1;
                this.ignoreCustomDraw = true;
                final long code = OS.SendMessage(this.handle, 4110, 0L, rect);
                this.ignoreCustomDraw = false;
                if (code != 0L) {
                    pinfo.x = rect.left;
                    OS.SendMessage(this.handle, 4153, 0L, pinfo);
                    if (pinfo.iItem < 0) {
                        pinfo.iItem = -1;
                    }
                }
            }
            if (pinfo.iItem != -1 && pinfo.iSubItem == 0 && this.hitTestSelection(pinfo.iItem, pinfo.x, pinfo.y)) {
                return this._getItem(pinfo.iItem);
            }
            return null;
        }
        else {
            OS.SendMessage(this.handle, 4114, 0L, pinfo);
            if (pinfo.iItem != -1) {
                if (pinfo.iItem == 0) {
                    final int bits = OS.GetWindowLong(this.handle, -16);
                    if ((bits & 0x4000) == 0x0 && this.hwndHeader != 0L) {
                        final RECT rect2 = new RECT();
                        OS.GetWindowRect(this.hwndHeader, rect2);
                        final POINT pt = new POINT();
                        pt.x = pinfo.x;
                        pt.y = pinfo.y;
                        OS.MapWindowPoints(this.handle, 0L, pt, 1);
                        if (OS.PtInRect(rect2, pt)) {
                            return null;
                        }
                    }
                }
                return this._getItem(pinfo.iItem);
            }
            return null;
        }
    }
    
    public int getItemCount() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
    }
    
    public int getItemHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getItemHeightInPixels());
    }
    
    int getItemHeightInPixels() {
        if (!this.painted && this.hooks(41)) {
            this.hitTestSelection(0, 0, 0);
        }
        final long empty = OS.SendMessage(this.handle, 4160, 0L, 0L);
        final long oneItem = OS.SendMessage(this.handle, 4160, 1L, 0L);
        return OS.HIWORD(oneItem) - OS.HIWORD(empty);
    }
    
    public TableItem[] getItems() {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        final TableItem[] result = new TableItem[count];
        if ((this.style & 0x10000000) != 0x0) {
            for (int i = 0; i < count; ++i) {
                result[i] = this._getItem(i);
            }
        }
        else {
            this._getItems(result, count);
        }
        return result;
    }
    
    public boolean getLinesVisible() {
        this.checkWidget();
        return this._getLinesVisible();
    }
    
    private boolean _getLinesVisible() {
        final int bits = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
        return (bits & 0x1) != 0x0;
    }
    
    public TableItem[] getSelection() {
        this.checkWidget();
        int i = -1;
        int j = 0;
        final int count = (int)OS.SendMessage(this.handle, 4146, 0L, 0L);
        final TableItem[] result = new TableItem[count];
        while ((i = (int)OS.SendMessage(this.handle, 4108, (long)i, 2L)) != -1) {
            result[j++] = this._getItem(i);
        }
        return result;
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 4146, 0L, 0L);
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        final int focusIndex = (int)OS.SendMessage(this.handle, 4108, -1L, 1L);
        final int selectedIndex = (int)OS.SendMessage(this.handle, 4108, -1L, 2L);
        if (focusIndex == selectedIndex) {
            return selectedIndex;
        }
        int i = -1;
        while ((i = (int)OS.SendMessage(this.handle, 4108, (long)i, 2L)) != -1) {
            if (i == focusIndex) {
                return i;
            }
        }
        return selectedIndex;
    }
    
    public int[] getSelectionIndices() {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 4146, 0L, 0L);
        final int[] result = new int[count];
        int lastIndex = -1;
        for (int i = 0; i < count; ++i) {
            lastIndex = (int)OS.SendMessage(this.handle, 4108, (long)lastIndex, 2L);
            if (lastIndex == -1) {
                break;
            }
            result[i] = lastIndex;
        }
        return result;
    }
    
    public TableColumn getSortColumn() {
        this.checkWidget();
        return this.sortColumn;
    }
    
    int getSortColumnPixel() {
        final int pixel = (OS.IsWindowEnabled(this.handle) || this.hasCustomBackground()) ? this.getBackgroundPixel() : OS.GetSysColor(15);
        return this.getSlightlyDifferentBackgroundColor(pixel);
    }
    
    public int getSortDirection() {
        this.checkWidget();
        return this.sortDirection;
    }
    
    public int getTopIndex() {
        this.checkWidget();
        return Math.max(0, (int)OS.SendMessage(this.handle, 4135, 0L, 0L));
    }
    
    boolean hasChildren() {
        for (long hwndChild = OS.GetWindow(this.handle, 5); hwndChild != 0L; hwndChild = OS.GetWindow(hwndChild, 2)) {
            if (hwndChild != this.hwndHeader) {
                return true;
            }
        }
        return false;
    }
    
    boolean hitTestSelection(final int index, final int x, final int y) {
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (count == 0) {
            return false;
        }
        if (!this.hooks(41)) {
            return false;
        }
        boolean result = false;
        if (0 <= index && index < count) {
            final TableItem item = this._getItem(index);
            final long hDC = OS.GetDC(this.handle);
            long oldFont = 0L;
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            long hFont = item.fontHandle(0);
            if (hFont != -1L) {
                hFont = OS.SelectObject(hDC, hFont);
            }
            final Event event = this.sendMeasureItemEvent(item, index, 0, hDC);
            if (event.getBoundsInPixels().contains(x, y)) {
                result = true;
            }
            if (hFont != -1L) {
                hFont = OS.SelectObject(hDC, hFont);
            }
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
        }
        return result;
    }
    
    int imageIndex(final Image image, final int column) {
        if (image == null) {
            return -2;
        }
        if (column == 0) {
            this.firstColumnImage = true;
        }
        else {
            this.setSubImagesVisible(true);
        }
        if (this.imageList == null) {
            final Rectangle bounds = image.getBoundsInPixels();
            this.imageList = this.display.getImageList(this.style & 0x4000000, bounds.width, bounds.height);
            int index = this.imageList.indexOf(image);
            if (index == -1) {
                index = this.imageList.add(image);
            }
            final long hImageList = this.imageList.getHandle();
            final int topIndex = this.getTopIndex();
            if (topIndex != 0) {
                this.setRedraw(false);
                this.setTopIndex(0);
            }
            OS.SendMessage(this.handle, 4099, 1L, hImageList);
            if (this.headerImageList != null) {
                final long hHeaderImageList = this.headerImageList.getHandle();
                OS.SendMessage(this.hwndHeader, 4616, 0L, hHeaderImageList);
            }
            this.fixCheckboxImageList(false);
            this.setItemHeight(false);
            if (topIndex != 0) {
                this.setTopIndex(topIndex);
                this.setRedraw(true);
            }
            return index;
        }
        final int index2 = this.imageList.indexOf(image);
        if (index2 != -1) {
            return index2;
        }
        return this.imageList.add(image);
    }
    
    int imageIndexHeader(final Image image) {
        if (image == null) {
            return -2;
        }
        if (this.headerImageList == null) {
            final Rectangle bounds = image.getBoundsInPixels();
            this.headerImageList = this.display.getImageList(this.style & 0x4000000, bounds.width, bounds.height);
            int index = this.headerImageList.indexOf(image);
            if (index == -1) {
                index = this.headerImageList.add(image);
            }
            final long hImageList = this.headerImageList.getHandle();
            OS.SendMessage(this.hwndHeader, 4616, 0L, hImageList);
            return index;
        }
        final int index2 = this.headerImageList.indexOf(image);
        if (index2 != -1) {
            return index2;
        }
        return this.headerImageList.add(image);
    }
    
    public int indexOf(final TableColumn column) {
        this.checkWidget();
        if (column == null) {
            this.error(4);
        }
        for (int i = 0; i < this.columnCount; ++i) {
            if (this.columns[i] == column) {
                return i;
            }
        }
        return -1;
    }
    
    public int indexOf(final TableItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (this.keys == null) {
            final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
            if (1 <= this.lastIndexOf && this.lastIndexOf < count - 1) {
                if (this._getItem(this.lastIndexOf, false) == item) {
                    return this.lastIndexOf;
                }
                if (this._getItem(this.lastIndexOf + 1, false) == item) {
                    return ++this.lastIndexOf;
                }
                if (this._getItem(this.lastIndexOf - 1, false) == item) {
                    return --this.lastIndexOf;
                }
            }
            if (this.lastIndexOf < count / 2) {
                for (int i = 0; i < count; ++i) {
                    if (this._getItem(i, false) == item) {
                        return this.lastIndexOf = i;
                    }
                }
            }
            else {
                for (int i = count - 1; i >= 0; --i) {
                    if (this._getItem(i, false) == item) {
                        return this.lastIndexOf = i;
                    }
                }
            }
        }
        else {
            for (int j = 0; j < this.keyCount; ++j) {
                if (this.items[j] == item) {
                    return this.keys[j];
                }
            }
        }
        return -1;
    }
    
    boolean isCustomToolTip() {
        return this.hooks(41);
    }
    
    boolean isOptimizedRedraw() {
        return (this.style & 0x100) != 0x0 && (this.style & 0x200) != 0x0 && !this.hasChildren() && !this.hooks(9) && !this.filters(9) && !this.customHeaderDrawing();
    }
    
    public boolean isSelected(final int index) {
        this.checkWidget();
        final LVITEM lvItem = new LVITEM();
        lvItem.mask = 8;
        lvItem.stateMask = 2;
        lvItem.iItem = index;
        final long result = OS.SendMessage(this.handle, 4171, 0L, lvItem);
        return result != 0L && (lvItem.state & 0x2) != 0x0;
    }
    
    boolean isUseWsBorder() {
        return super.isUseWsBorder() || (this.display != null && this.display.useWsBorderTable);
    }
    
    void register() {
        super.register();
        if (this.hwndHeader != 0L) {
            this.display.addControl(this.hwndHeader, (Control)this);
        }
    }
    
    void releaseChildren(final boolean destroy) {
        if (this._hasItems()) {
            final int itemCount = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
            if (this.keys == null) {
                for (int i = 0; i < itemCount; ++i) {
                    final TableItem item = this._getItem(i, false);
                    if (item != null && !item.isDisposed()) {
                        item.release(false);
                    }
                }
            }
            else {
                for (int i = 0; i < this.keyCount; ++i) {
                    final TableItem item = this.items[i];
                    if (item != null && !item.isDisposed()) {
                        item.release(false);
                    }
                }
            }
            this._clearItems();
        }
        if (this.columns != null) {
            for (int j = 0; j < this.columnCount; ++j) {
                final TableColumn column = this.columns[j];
                if (!column.isDisposed()) {
                    column.release(false);
                }
            }
            this.columns = null;
        }
        super.releaseChildren(destroy);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.customDraw = false;
        this.currentItem = null;
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 4099, 1L, 0L);
            this.display.releaseImageList(this.imageList);
        }
        if (this.headerImageList != null) {
            OS.SendMessage(this.hwndHeader, 4616, 0L, 0L);
            this.display.releaseImageList(this.headerImageList);
        }
        final ImageList list = null;
        this.headerImageList = list;
        this.imageList = list;
        final long hStateList = OS.SendMessage(this.handle, 4098, 2L, 0L);
        OS.SendMessage(this.handle, 4099, 2L, 0L);
        if (hStateList != 0L) {
            OS.ImageList_Destroy(hStateList);
        }
        if (this.headerToolTipHandle != 0L) {
            OS.DestroyWindow(this.headerToolTipHandle);
        }
        this.headerToolTipHandle = 0L;
    }
    
    public void remove(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        if (indices.length == 0) {
            return;
        }
        final int[] newIndices = new int[indices.length];
        System.arraycopy(indices, 0, newIndices, 0, indices.length);
        this.sort(newIndices);
        final int start = newIndices[newIndices.length - 1];
        final int end = newIndices[0];
        int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (0 > start || start > end || end >= count) {
            this.error(6);
        }
        this.setDeferResize(true);
        int last = -1;
        for (final int index : newIndices) {
            if (index != last) {
                final TableItem item = this._getItem(index, false);
                if (item != null && !item.isDisposed()) {
                    item.release(false);
                }
                final boolean b = true;
                this.ignoreShrink = true;
                this.ignoreSelect = true;
                final long code = OS.SendMessage(this.handle, 4104, (long)index, 0L);
                final boolean b2 = false;
                this.ignoreShrink = false;
                this.ignoreSelect = false;
                if (code == 0L) {
                    this.error(15);
                }
                this._removeItem(index, count);
                --count;
                last = index;
            }
        }
        if (count == 0) {
            this.setTableEmpty();
        }
        this.setDeferResize(false);
    }
    
    public void remove(final int index) {
        this.checkWidget();
        int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (0 > index || index >= count) {
            this.error(6);
        }
        final TableItem item = this._getItem(index, false);
        if (item != null && !item.isDisposed()) {
            item.release(false);
        }
        this.setDeferResize(true);
        final boolean b = true;
        this.ignoreShrink = true;
        this.ignoreSelect = true;
        final long code = OS.SendMessage(this.handle, 4104, (long)index, 0L);
        final boolean b2 = false;
        this.ignoreShrink = false;
        this.ignoreSelect = false;
        if (code == 0L) {
            this.error(15);
        }
        this._removeItem(index, count);
        if (--count == 0) {
            this.setTableEmpty();
        }
        this.setDeferResize(false);
    }
    
    public void remove(final int start, final int end) {
        this.checkWidget();
        if (start > end) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (0 > start || start > end || end >= count) {
            this.error(6);
        }
        if (start == 0 && end == count - 1) {
            this.removeAll();
        }
        else {
            this.setDeferResize(true);
            int index;
            for (index = start; index <= end; ++index) {
                final TableItem item = this._getItem(index, false);
                if (item != null && !item.isDisposed()) {
                    item.release(false);
                }
                final boolean b = true;
                this.ignoreShrink = true;
                this.ignoreSelect = true;
                final long code = OS.SendMessage(this.handle, 4104, (long)start, 0L);
                final boolean b2 = false;
                this.ignoreShrink = false;
                this.ignoreSelect = false;
                if (code == 0L) {
                    break;
                }
            }
            this._removeItems(start, index, count);
            if (index <= end) {
                this.error(15);
            }
            this.setDeferResize(false);
        }
    }
    
    public void removeAll() {
        this.checkWidget();
        for (int itemCount = (int)OS.SendMessage(this.handle, 4100, 0L, 0L), i = 0; i < itemCount; ++i) {
            final TableItem item = this._getItem(i, false);
            if (item != null && !item.isDisposed()) {
                item.release(false);
            }
        }
        this.setDeferResize(true);
        final boolean b = true;
        this.ignoreShrink = true;
        this.ignoreSelect = true;
        final long code = OS.SendMessage(this.handle, 4105, 0L, 0L);
        final boolean b2 = false;
        this.ignoreShrink = false;
        this.ignoreSelect = false;
        if (code == 0L) {
            this.error(15);
        }
        this.setTableEmpty();
        this.setDeferResize(false);
    }
    
    public void removeSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(13, (SWTEventListener)listener);
        this.eventTable.unhook(14, (SWTEventListener)listener);
    }
    
    public void select(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        final int length = indices.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        final LVITEM lvItem = new LVITEM();
        lvItem.state = 2;
        lvItem.stateMask = 2;
        for (int i = length - 1; i >= 0; --i) {
            if (indices[i] >= 0) {
                this.ignoreSelect = true;
                OS.SendMessage(this.handle, 4139, (long)indices[i], lvItem);
                this.ignoreSelect = false;
            }
        }
    }
    
    void reskinChildren(final int flags) {
        if (this._hasItems()) {
            for (int itemCount = (int)OS.SendMessage(this.handle, 4100, 0L, 0L), i = 0; i < itemCount; ++i) {
                final TableItem item = this._getItem(i, false);
                if (item != null) {
                    item.reskin(flags);
                }
            }
        }
        if (this.columns != null) {
            for (int j = 0; j < this.columnCount; ++j) {
                final TableColumn column = this.columns[j];
                if (!column.isDisposed()) {
                    column.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    public void select(final int index) {
        this.checkWidget();
        if (index < 0) {
            return;
        }
        final LVITEM lvItem = new LVITEM();
        lvItem.state = 2;
        lvItem.stateMask = 2;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, (long)index, lvItem);
        this.ignoreSelect = false;
    }
    
    public void select(int start, int end) {
        this.checkWidget();
        if (end < 0 || start > end || ((this.style & 0x4) != 0x0 && start != end)) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (count == 0 || start >= count) {
            return;
        }
        start = Math.max(0, start);
        end = Math.min(end, count - 1);
        if (start == 0 && end == count - 1) {
            this.selectAll();
        }
        else {
            final LVITEM lvItem = new LVITEM();
            lvItem.state = 2;
            lvItem.stateMask = 2;
            for (int i = start; i <= end; ++i) {
                this.ignoreSelect = true;
                OS.SendMessage(this.handle, 4139, (long)i, lvItem);
                this.ignoreSelect = false;
            }
        }
    }
    
    public void selectAll() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        final LVITEM lvItem = new LVITEM();
        lvItem.mask = 8;
        lvItem.state = 2;
        lvItem.stateMask = 2;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, -1L, lvItem);
        this.ignoreSelect = false;
    }
    
    void sendEraseItemEvent(final TableItem item, final NMLVCUSTOMDRAW nmcd, final long lParam, final Event measureEvent) {
        final long hDC = nmcd.hdc;
        int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
        if (clrText == -1) {
            clrText = item.foreground;
        }
        int clrTextBk = (item.cellBackground != null) ? item.cellBackground[nmcd.iSubItem] : -1;
        if (clrTextBk == -1) {
            clrTextBk = item.background;
        }
        final LVITEM lvItem = new LVITEM();
        lvItem.mask = 8;
        lvItem.stateMask = 2;
        lvItem.iItem = (int)nmcd.dwItemSpec;
        final long result = OS.SendMessage(this.handle, 4171, 0L, lvItem);
        final boolean selected = result != 0L && (lvItem.state & 0x2) != 0x0;
        final GCData data = new GCData();
        data.device = (Device)this.display;
        int clrSelectionBk = -1;
        boolean drawSelected = false;
        boolean drawBackground = false;
        boolean drawHot = false;
        boolean drawDrophilited = false;
        if (nmcd.iSubItem == 0 || (this.style & 0x10000) != 0x0) {
            drawHot = (this.hotIndex == nmcd.dwItemSpec);
            drawDrophilited = ((nmcd.uItemState & 0x1000) != 0x0);
        }
        if (OS.IsWindowEnabled(this.handle)) {
            if (selected && (nmcd.iSubItem == 0 || (this.style & 0x10000) != 0x0)) {
                if (OS.GetFocus() == this.handle || this.display.getHighContrast()) {
                    drawSelected = true;
                    data.foreground = OS.GetSysColor(14);
                    final GCData gcData = data;
                    final int getSysColor = OS.GetSysColor(13);
                    gcData.background = getSysColor;
                    clrSelectionBk = getSysColor;
                }
                else {
                    drawSelected = ((this.style & 0x8000) == 0x0);
                    data.foreground = OS.GetTextColor(hDC);
                    final GCData gcData2 = data;
                    final int getSysColor2 = OS.GetSysColor(15);
                    gcData2.background = getSysColor2;
                    clrSelectionBk = getSysColor2;
                }
                if (this.explorerTheme) {
                    data.foreground = ((clrText != -1) ? clrText : this.getForegroundPixel());
                }
            }
            else {
                drawBackground = (clrTextBk != -1);
                if (clrText == -1 || clrTextBk == -1) {
                    Control control = this.findBackgroundControl();
                    if (control == null) {
                        control = (Control)this;
                    }
                    if (clrText == -1) {
                        clrText = control.getForegroundPixel();
                    }
                    if (clrTextBk == -1) {
                        clrTextBk = control.getBackgroundPixel();
                    }
                }
                data.foreground = ((clrText != -1) ? clrText : OS.GetTextColor(hDC));
                data.background = ((clrTextBk != -1) ? clrTextBk : OS.GetBkColor(hDC));
            }
        }
        else {
            data.foreground = OS.GetSysColor(17);
            data.background = OS.GetSysColor(15);
            if (selected) {
                clrSelectionBk = data.background;
            }
        }
        data.font = item.getFont(nmcd.iSubItem);
        data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
        final int nSavedDC = OS.SaveDC(hDC);
        final GC gc = GC.win32_new(hDC, data);
        final RECT cellRect = item.getBounds((int)nmcd.dwItemSpec, nmcd.iSubItem, true, true, true, true, hDC);
        final Event event = new Event();
        event.item = (Widget)item;
        event.gc = gc;
        event.index = nmcd.iSubItem;
        final Event event7;
        final Event event2 = event7 = event;
        event7.detail |= 0x10;
        if (OS.SendMessage(this.handle, 4108, -1L, 1L) == nmcd.dwItemSpec && (nmcd.iSubItem == 0 || (this.style & 0x10000) != 0x0) && this.handle == OS.GetFocus()) {
            final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
            if ((uiState & 0x1) == 0x0) {
                final Event event8;
                final Event event3 = event8 = event;
                event8.detail |= 0x4;
            }
        }
        final boolean focused = (event.detail & 0x4) != 0x0;
        if (drawHot) {
            final Event event9;
            final Event event4 = event9 = event;
            event9.detail |= 0x20;
        }
        if (drawSelected) {
            final Event event10;
            final Event event5 = event10 = event;
            event10.detail |= 0x2;
        }
        if (drawBackground) {
            final Event event11;
            final Event event6 = event11 = event;
            event11.detail |= 0x8;
        }
        final Rectangle boundsInPixels = new Rectangle(cellRect.left, cellRect.top, cellRect.right - cellRect.left, cellRect.bottom - cellRect.top);
        event.setBoundsInPixels(boundsInPixels);
        gc.setClipping(DPIUtil.autoScaleDown(boundsInPixels));
        this.sendEvent(40, event);
        event.gc = null;
        final int clrSelectionText = data.foreground;
        gc.dispose();
        OS.RestoreDC(hDC, nSavedDC);
        if (this.isDisposed() || item.isDisposed()) {
            return;
        }
        if (event.doit) {
            this.ignoreDrawForeground = ((event.detail & 0x10) == 0x0);
            this.ignoreDrawBackground = ((event.detail & 0x8) == 0x0);
            this.ignoreDrawSelection = ((event.detail & 0x2) == 0x0);
            this.ignoreDrawFocus = ((event.detail & 0x4) == 0x0);
            this.ignoreDrawHot = ((event.detail & 0x20) == 0x0);
        }
        else {
            final boolean ignoreDrawForeground = true;
            this.ignoreDrawHot = true;
            this.ignoreDrawFocus = true;
            this.ignoreDrawSelection = true;
            this.ignoreDrawBackground = true;
            this.ignoreDrawForeground = true;
        }
        if (drawSelected) {
            if (this.ignoreDrawSelection) {
                this.ignoreDrawHot = true;
                if (nmcd.iSubItem == 0 || (this.style & 0x10000) != 0x0) {
                    this.selectionForeground = clrSelectionText;
                }
                nmcd.uItemState &= 0xFFFFFFFE;
                OS.MoveMemory(lParam, nmcd, NMLVCUSTOMDRAW.sizeof);
            }
        }
        else if (this.ignoreDrawSelection) {
            nmcd.uItemState |= 0x1;
            OS.MoveMemory(lParam, nmcd, NMLVCUSTOMDRAW.sizeof);
        }
        final boolean firstColumn = nmcd.iSubItem == OS.SendMessage(this.hwndHeader, 4623, 0L, 0L);
        if (this.ignoreDrawForeground && this.ignoreDrawHot && !drawDrophilited && !this.ignoreDrawBackground && drawBackground) {
            final RECT backgroundRect = item.getBounds((int)nmcd.dwItemSpec, nmcd.iSubItem, true, false, true, false, hDC);
            this.fillBackground(hDC, clrTextBk, backgroundRect);
        }
        this.focusRect = null;
        if (!this.ignoreDrawHot || !this.ignoreDrawSelection || !this.ignoreDrawFocus || drawDrophilited) {
            final boolean fullText = (this.style & 0x10000) != 0x0 || !firstColumn;
            final RECT textRect = item.getBounds((int)nmcd.dwItemSpec, nmcd.iSubItem, true, false, fullText, false, hDC);
            if ((this.style & 0x10000) == 0x0) {
                if (measureEvent != null) {
                    final Rectangle boundInPixels = measureEvent.getBoundsInPixels();
                    textRect.right = Math.min(cellRect.right, boundInPixels.x + boundInPixels.width);
                }
                if (!this.ignoreDrawFocus) {
                    nmcd.uItemState &= 0xFFFFFFEF;
                    OS.MoveMemory(lParam, nmcd, NMLVCUSTOMDRAW.sizeof);
                    this.focusRect = textRect;
                }
            }
            if (this.explorerTheme) {
                final boolean backgroundWanted = !this.ignoreDrawHot || drawDrophilited || (!this.ignoreDrawSelection && clrSelectionBk != -1);
                if (backgroundWanted) {
                    final RECT pClipRect = new RECT();
                    OS.SetRect(pClipRect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                    final RECT rect = new RECT();
                    OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                    if ((this.style & 0x10000) != 0x0) {
                        final int count = (int)OS.SendMessage(this.hwndHeader, 4608, 0L, 0L);
                        int index = (int)OS.SendMessage(this.hwndHeader, 4623, (long)(count - 1), 0L);
                        final RECT headerRect = new RECT();
                        OS.SendMessage(this.hwndHeader, 4615, (long)index, headerRect);
                        OS.MapWindowPoints(this.hwndHeader, this.handle, headerRect, 2);
                        rect.right = headerRect.right;
                        index = (int)OS.SendMessage(this.hwndHeader, 4623, 0L, 0L);
                        OS.SendMessage(this.hwndHeader, 4615, (long)index, headerRect);
                        OS.MapWindowPoints(this.hwndHeader, this.handle, headerRect, 2);
                        rect.left = headerRect.left;
                        pClipRect.left = cellRect.left;
                        final RECT rect5;
                        final RECT rect2 = rect5 = pClipRect;
                        rect5.right += 2;
                    }
                    else {
                        final RECT rect6;
                        final RECT rect3 = rect6 = rect;
                        rect6.right += 2;
                        final RECT rect7;
                        final RECT rect4 = rect7 = pClipRect;
                        rect7.right += 2;
                    }
                    final long hTheme = OS.OpenThemeData(this.handle, Display.TREEVIEW);
                    int iStateId = selected ? 3 : 2;
                    if (OS.GetFocus() != this.handle && selected && !drawHot) {
                        iStateId = 5;
                    }
                    if (drawDrophilited) {
                        iStateId = 3;
                    }
                    OS.DrawThemeBackground(hTheme, hDC, 1, iStateId, rect, pClipRect);
                    OS.CloseThemeData(hTheme);
                }
            }
            else if (!this.ignoreDrawSelection && clrSelectionBk != -1) {
                this.fillBackground(hDC, clrSelectionBk, textRect);
            }
        }
        if (focused && this.ignoreDrawFocus) {
            nmcd.uItemState &= 0xFFFFFFEF;
            OS.MoveMemory(lParam, nmcd, NMLVCUSTOMDRAW.sizeof);
        }
        if (this.ignoreDrawForeground) {
            final RECT clipRect = item.getBounds((int)nmcd.dwItemSpec, nmcd.iSubItem, true, true, true, false, hDC);
            OS.SaveDC(hDC);
            OS.SelectClipRgn(hDC, 0L);
            OS.ExcludeClipRect(hDC, clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
        }
    }
    
    Event sendEraseItemEvent(final TableItem item, final NMTTCUSTOMDRAW nmcd, final int column, final RECT cellRect) {
        final int nSavedDC = OS.SaveDC(nmcd.hdc);
        final RECT insetRect = this.toolTipInset(cellRect);
        OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, (POINT)null);
        final GCData data = new GCData();
        data.device = (Device)this.display;
        data.foreground = OS.GetTextColor(nmcd.hdc);
        data.background = OS.GetBkColor(nmcd.hdc);
        data.font = item.getFont(column);
        data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
        final GC gc = GC.win32_new(nmcd.hdc, data);
        final Event event = new Event();
        event.item = (Widget)item;
        event.index = column;
        event.gc = gc;
        final Event event3;
        final Event event2 = event3 = event;
        event3.detail |= 0x10;
        event.setBoundsInPixels(new Rectangle(cellRect.left, cellRect.top, cellRect.right - cellRect.left, cellRect.bottom - cellRect.top));
        this.sendEvent(40, event);
        event.gc = null;
        gc.dispose();
        OS.RestoreDC(nmcd.hdc, nSavedDC);
        return event;
    }
    
    Event sendMeasureItemEvent(final TableItem item, final int row, final int column, final long hDC) {
        final GCData data = new GCData();
        data.device = (Device)this.display;
        data.font = item.getFont(column);
        final int nSavedDC = OS.SaveDC(hDC);
        final GC gc = GC.win32_new(hDC, data);
        final RECT itemRect = item.getBounds(row, column, true, true, false, false, hDC);
        final Event event = new Event();
        event.item = (Widget)item;
        event.gc = gc;
        event.index = column;
        event.setBoundsInPixels(new Rectangle(itemRect.left, itemRect.top, itemRect.right - itemRect.left, itemRect.bottom - itemRect.top));
        boolean drawSelected = false;
        if (OS.IsWindowEnabled(this.handle)) {
            final LVITEM lvItem = new LVITEM();
            lvItem.mask = 8;
            lvItem.stateMask = 2;
            lvItem.iItem = row;
            final long result = OS.SendMessage(this.handle, 4171, 0L, lvItem);
            final boolean selected = result != 0L && (lvItem.state & 0x2) != 0x0;
            if (selected && (column == 0 || (this.style & 0x10000) != 0x0)) {
                drawSelected = (OS.GetFocus() == this.handle || this.display.getHighContrast() || (this.style & 0x8000) == 0x0);
            }
        }
        if (drawSelected) {
            final Event event3;
            final Event event2 = event3 = event;
            event3.detail |= 0x2;
        }
        this.sendEvent(41, event);
        event.gc = null;
        gc.dispose();
        OS.RestoreDC(hDC, nSavedDC);
        if (!this.isDisposed() && !item.isDisposed()) {
            final Rectangle boundsInPixels = event.getBoundsInPixels();
            if (this.columnCount == 0) {
                final int width = (int)OS.SendMessage(this.handle, 4125, 0L, 0L);
                if (boundsInPixels.x + boundsInPixels.width > width) {
                    this.setScrollWidth(boundsInPixels.x + boundsInPixels.width);
                }
            }
            final long empty = OS.SendMessage(this.handle, 4160, 0L, 0L);
            final long oneItem = OS.SendMessage(this.handle, 4160, 1L, 0L);
            final int itemHeight = OS.HIWORD(oneItem) - OS.HIWORD(empty);
            if (!this.settingItemHeight && boundsInPixels.height > itemHeight) {
                this.settingItemHeight = true;
                this.setItemHeight(boundsInPixels.height);
                this.settingItemHeight = false;
            }
        }
        return event;
    }
    
    LRESULT sendMouseDownEvent(final int type, final int button, final int msg, final long wParam, final long lParam) {
        final Display display = this.display;
        display.captureChanged = false;
        if (!this.sendMouseEvent(type, button, this.handle, lParam)) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            return LRESULT.ZERO;
        }
        final LVHITTESTINFO pinfo = new LVHITTESTINFO();
        pinfo.x = OS.GET_X_LPARAM(lParam);
        pinfo.y = OS.GET_Y_LPARAM(lParam);
        OS.SendMessage(this.handle, 4114, 0L, pinfo);
        if ((this.style & 0x10000) == 0x0 && this.hooks(41)) {
            if (OS.SendMessage(this.handle, 4153, 0L, pinfo) < 0L) {
                final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
                if (count != 0) {
                    final RECT rect = new RECT();
                    rect.left = 1;
                    this.ignoreCustomDraw = true;
                    final long code = OS.SendMessage(this.handle, 4110, 0L, rect);
                    this.ignoreCustomDraw = false;
                    if (code != 0L) {
                        pinfo.x = rect.left;
                        OS.SendMessage(this.handle, 4153, 0L, pinfo);
                        if (pinfo.iItem < 0) {
                            pinfo.iItem = -1;
                        }
                        final LVHITTESTINFO lvhittestinfo2;
                        final LVHITTESTINFO lvhittestinfo = lvhittestinfo2 = pinfo;
                        lvhittestinfo2.flags &= 0xFFFFFFF9;
                    }
                }
            }
            else if (pinfo.iSubItem != 0) {
                pinfo.iItem = -1;
            }
        }
        if (((this.style & 0x4) != 0x0 || this.hooks(3) || this.hooks(4)) && pinfo.iItem == -1) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            OS.SetFocus(this.handle);
            return LRESULT.ZERO;
        }
        boolean forceSelect = false;
        final int count2 = (int)OS.SendMessage(this.handle, 4146, 0L, 0L);
        if (count2 == 1 && pinfo.iItem != -1) {
            final LVITEM lvItem = new LVITEM();
            lvItem.mask = 8;
            lvItem.stateMask = 2;
            lvItem.iItem = pinfo.iItem;
            OS.SendMessage(this.handle, 4171, 0L, lvItem);
            if ((lvItem.state & 0x2) != 0x0) {
                forceSelect = true;
            }
        }
        this.fullRowSelect = false;
        if (pinfo.iItem != -1 && (this.style & 0x10000) == 0x0 && this.hooks(41)) {
            this.fullRowSelect = this.hitTestSelection(pinfo.iItem, pinfo.x, pinfo.y);
            if (this.fullRowSelect) {
                final int flags = 6;
                if ((pinfo.flags & 0x6) != 0x0) {
                    this.fullRowSelect = false;
                }
            }
        }
        boolean dragDetect = (this.state & 0x8000) != 0x0 && this.hooks(29);
        if (!dragDetect) {
            final int flags2 = 6;
            dragDetect = (pinfo.iItem == -1 || (pinfo.flags & 0x6) == 0x0);
            if (this.fullRowSelect) {
                dragDetect = true;
            }
        }
        if (this.fullRowSelect) {
            OS.UpdateWindow(this.handle);
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
            OS.SendMessage(this.handle, 4150, 32L, 32L);
        }
        this.dragStarted = false;
        display.dragCancelled = false;
        if (!dragDetect) {
            display.runDragDrop = false;
        }
        final long code2 = this.callWindowProc(this.handle, msg, wParam, lParam, forceSelect);
        if (!dragDetect) {
            display.runDragDrop = true;
        }
        if (this.fullRowSelect) {
            this.fullRowSelect = false;
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            OS.SendMessage(this.handle, 4150, 32L, 0L);
        }
        if (this.dragStarted || display.dragCancelled) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
        }
        else {
            final int flags3 = 6;
            boolean fakeMouseUp = (pinfo.flags & 0x6) != 0x0;
            if (!fakeMouseUp && (this.style & 0x2) != 0x0) {
                fakeMouseUp = ((pinfo.flags & 0x8) == 0x0);
            }
            if (fakeMouseUp) {
                this.sendMouseEvent(4, button, this.handle, lParam);
            }
        }
        return new LRESULT(code2);
    }
    
    void sendPaintItemEvent(final TableItem item, final NMLVCUSTOMDRAW nmcd) {
        final long hDC = nmcd.hdc;
        final GCData data = new GCData();
        data.device = (Device)this.display;
        data.font = item.getFont(nmcd.iSubItem);
        final LVITEM lvItem = new LVITEM();
        lvItem.mask = 8;
        lvItem.stateMask = 2;
        lvItem.iItem = (int)nmcd.dwItemSpec;
        final long result = OS.SendMessage(this.handle, 4171, 0L, lvItem);
        final boolean selected = result != 0L && (lvItem.state & 0x2) != 0x0;
        boolean drawSelected = false;
        boolean drawBackground = false;
        boolean drawHot = false;
        if (nmcd.iSubItem == 0 || (this.style & 0x10000) != 0x0) {
            drawHot = (this.hotIndex == nmcd.dwItemSpec);
        }
        if (OS.IsWindowEnabled(this.handle)) {
            if (selected && (nmcd.iSubItem == 0 || (this.style & 0x10000) != 0x0)) {
                if (OS.GetFocus() == this.handle || this.display.getHighContrast()) {
                    drawSelected = true;
                    if (this.selectionForeground != -1) {
                        data.foreground = this.selectionForeground;
                    }
                    else {
                        data.foreground = OS.GetSysColor(14);
                    }
                    data.background = OS.GetSysColor(13);
                }
                else {
                    drawSelected = ((this.style & 0x8000) == 0x0);
                    data.foreground = OS.GetTextColor(hDC);
                    data.background = OS.GetSysColor(15);
                }
                if (this.explorerTheme && this.selectionForeground == -1) {
                    int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
                    if (clrText == -1) {
                        clrText = item.foreground;
                    }
                    data.foreground = ((clrText != -1) ? clrText : this.getForegroundPixel());
                }
            }
            else {
                int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
                if (clrText == -1) {
                    clrText = item.foreground;
                }
                int clrTextBk = (item.cellBackground != null) ? item.cellBackground[nmcd.iSubItem] : -1;
                if (clrTextBk == -1) {
                    clrTextBk = item.background;
                }
                drawBackground = (clrTextBk != -1);
                if (clrText == -1 || clrTextBk == -1) {
                    Control control = this.findBackgroundControl();
                    if (control == null) {
                        control = (Control)this;
                    }
                    if (clrText == -1) {
                        clrText = control.getForegroundPixel();
                    }
                    if (clrTextBk == -1) {
                        clrTextBk = control.getBackgroundPixel();
                    }
                }
                data.foreground = ((clrText != -1) ? clrText : OS.GetTextColor(hDC));
                data.background = ((clrTextBk != -1) ? clrTextBk : OS.GetBkColor(hDC));
            }
        }
        else {
            data.foreground = OS.GetSysColor(17);
            data.background = OS.GetSysColor(15);
        }
        data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
        final int nSavedDC = OS.SaveDC(hDC);
        final GC gc = GC.win32_new(hDC, data);
        final RECT itemRect = item.getBounds((int)nmcd.dwItemSpec, nmcd.iSubItem, true, true, false, false, hDC);
        final Event event = new Event();
        event.item = (Widget)item;
        event.gc = gc;
        event.index = nmcd.iSubItem;
        final Event event7;
        final Event event2 = event7 = event;
        event7.detail |= 0x10;
        if (OS.SendMessage(this.handle, 4108, -1L, 1L) == nmcd.dwItemSpec && (nmcd.iSubItem == 0 || (this.style & 0x10000) != 0x0) && this.handle == OS.GetFocus()) {
            final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
            if ((uiState & 0x1) == 0x0) {
                final Event event8;
                final Event event3 = event8 = event;
                event8.detail |= 0x4;
            }
        }
        if (drawHot) {
            final Event event9;
            final Event event4 = event9 = event;
            event9.detail |= 0x20;
        }
        if (drawSelected) {
            final Event event10;
            final Event event5 = event10 = event;
            event10.detail |= 0x2;
        }
        if (drawBackground) {
            final Event event11;
            final Event event6 = event11 = event;
            event11.detail |= 0x8;
        }
        event.setBoundsInPixels(new Rectangle(itemRect.left, itemRect.top, itemRect.right - itemRect.left, itemRect.bottom - itemRect.top));
        final RECT cellRect = item.getBounds((int)nmcd.dwItemSpec, nmcd.iSubItem, true, true, true, true, hDC);
        final int cellWidth = cellRect.right - cellRect.left;
        final int cellHeight = cellRect.bottom - cellRect.top;
        gc.setClipping(DPIUtil.autoScaleDown(new Rectangle(cellRect.left, cellRect.top, cellWidth, cellHeight)));
        this.sendEvent(42, event);
        if (data.focusDrawn) {
            this.focusRect = null;
        }
        event.gc = null;
        gc.dispose();
        OS.RestoreDC(hDC, nSavedDC);
    }
    
    Event sendPaintItemEvent(final TableItem item, final NMTTCUSTOMDRAW nmcd, final int column, final RECT itemRect) {
        final int nSavedDC = OS.SaveDC(nmcd.hdc);
        final RECT insetRect = this.toolTipInset(itemRect);
        OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, (POINT)null);
        final GCData data = new GCData();
        data.device = (Device)this.display;
        data.font = item.getFont(column);
        data.foreground = OS.GetTextColor(nmcd.hdc);
        data.background = OS.GetBkColor(nmcd.hdc);
        data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
        final GC gc = GC.win32_new(nmcd.hdc, data);
        final Event event = new Event();
        event.item = (Widget)item;
        event.index = column;
        event.gc = gc;
        final Event event3;
        final Event event2 = event3 = event;
        event3.detail |= 0x10;
        event.setBoundsInPixels(new Rectangle(itemRect.left, itemRect.top, itemRect.right - itemRect.left, itemRect.bottom - itemRect.top));
        this.sendEvent(42, event);
        event.gc = null;
        gc.dispose();
        OS.RestoreDC(nmcd.hdc, nSavedDC);
        return event;
    }
    
    void setBackgroundImage(final long hBitmap) {
        super.setBackgroundImage(hBitmap);
        if (hBitmap != 0L) {
            this.setBackgroundTransparent(true);
        }
        else if (!this.hooks(41) && !this.hooks(40) && !this.hooks(42)) {
            this.setBackgroundTransparent(false);
        }
    }
    
    void setBackgroundPixel(int newPixel) {
        final int oldPixel = (int)OS.SendMessage(this.handle, 4096, 0L, 0L);
        if (oldPixel != -1) {
            if (this.findImageControl() != null) {
                return;
            }
            if (newPixel == -1) {
                newPixel = this.defaultBackground();
            }
            if (oldPixel != newPixel) {
                OS.SendMessage(this.handle, 4097, 0L, (long)newPixel);
                OS.SendMessage(this.handle, 4134, 0L, (long)newPixel);
                if ((this.style & 0x20) != 0x0) {
                    this.fixCheckboxImageListColor(true);
                }
            }
        }
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    void setBackgroundTransparent(final boolean transparent) {
        final int oldPixel = (int)OS.SendMessage(this.handle, 4096, 0L, 0L);
        if (transparent) {
            if (oldPixel != -1) {
                OS.SendMessage(this.handle, 4097, 0L, -1L);
                OS.SendMessage(this.handle, 4134, 0L, -1L);
                OS.InvalidateRect(this.handle, (RECT)null, true);
                if (!this.explorerTheme && (this.style & 0x10000) != 0x0) {
                    final int bits = 32;
                    OS.SendMessage(this.handle, 4150, 32L, 0L);
                }
                if ((this.sortDirection & 0x480) != 0x0 && this.sortColumn != null && !this.sortColumn.isDisposed()) {
                    OS.SendMessage(this.handle, 4236, -1L, 0L);
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                }
            }
        }
        else if (oldPixel == -1) {
            Control control = this.findBackgroundControl();
            if (control == null) {
                control = (Control)this;
            }
            if (control.backgroundImage == null) {
                final int newPixel = control.getBackgroundPixel();
                OS.SendMessage(this.handle, 4097, 0L, (long)newPixel);
                OS.SendMessage(this.handle, 4134, 0L, (long)newPixel);
                if ((this.style & 0x20) != 0x0) {
                    this.fixCheckboxImageListColor(true);
                }
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
            if (!this.explorerTheme && (this.style & 0x10000) != 0x0 && !this.hooks(40) && !this.hooks(42)) {
                final int bits2 = 32;
                OS.SendMessage(this.handle, 4150, 32L, 32L);
            }
            if ((this.sortDirection & 0x480) != 0x0 && this.sortColumn != null && !this.sortColumn.isDisposed()) {
                final int column = this.indexOf(this.sortColumn);
                if (column != -1) {
                    OS.SendMessage(this.handle, 4236, (long)column, 0L);
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                }
            }
        }
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, final int flags, final boolean defer) {
        this.setDeferResize(true);
        super.setBoundsInPixels(x, y, width, height, flags, false);
        this.setDeferResize(false);
    }
    
    public void setColumnOrder(final int[] order) {
        this.checkWidget();
        if (order == null) {
            this.error(4);
        }
        if (this.columnCount == 0) {
            if (order.length != 0) {
                this.error(5);
            }
            return;
        }
        if (order.length != this.columnCount) {
            this.error(5);
        }
        final int[] oldOrder = new int[this.columnCount];
        OS.SendMessage(this.handle, 4155, (long)this.columnCount, oldOrder);
        boolean reorder = false;
        final boolean[] seen = new boolean[this.columnCount];
        for (int i = 0; i < order.length; ++i) {
            final int index = order[i];
            if (index < 0 || index >= this.columnCount) {
                this.error(6);
            }
            if (seen[index]) {
                this.error(5);
            }
            seen[index] = true;
            if (index != oldOrder[i]) {
                reorder = true;
            }
        }
        if (reorder) {
            final RECT[] oldRects = new RECT[this.columnCount];
            for (int j = 0; j < this.columnCount; ++j) {
                oldRects[j] = new RECT();
                OS.SendMessage(this.hwndHeader, 4615, (long)j, oldRects[j]);
            }
            OS.SendMessage(this.handle, 4154, (long)order.length, order);
            OS.InvalidateRect(this.handle, (RECT)null, true);
            final TableColumn[] newColumns = new TableColumn[this.columnCount];
            System.arraycopy(this.columns, 0, newColumns, 0, this.columnCount);
            final RECT newRect = new RECT();
            for (int k = 0; k < this.columnCount; ++k) {
                final TableColumn column = newColumns[k];
                if (!column.isDisposed()) {
                    OS.SendMessage(this.hwndHeader, 4615, (long)k, newRect);
                    if (newRect.left != oldRects[k].left) {
                        column.updateToolTip(k);
                        column.sendEvent(10);
                    }
                }
            }
        }
    }
    
    void setCustomDraw(final boolean customDraw) {
        if (this.customDraw == customDraw) {
            return;
        }
        if (!this.customDraw && customDraw && this.currentItem != null) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        this.customDraw = customDraw;
    }
    
    void setDeferResize(final boolean defer) {
        if (defer) {
            if (this.resizeCount++ == 0) {
                this.wasResized = false;
                if ((this.hooks(41) || this.hooks(40) || this.hooks(42)) && this.drawCount++ == 0 && OS.IsWindowVisible(this.handle)) {
                    OS.DefWindowProc(this.handle, 11, 0L, 0L);
                    OS.SendMessage(this.handle, 4097, 0L, 16777215L);
                }
            }
        }
        else if (--this.resizeCount == 0) {
            if ((this.hooks(41) || this.hooks(40) || this.hooks(42)) && --this.drawCount == 0) {
                OS.SendMessage(this.handle, 4097, 0L, -1L);
                OS.DefWindowProc(this.handle, 11, 1L, 0L);
                final int flags = 1157;
                OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
            }
            if (this.wasResized) {
                this.setResizeChildren(this.wasResized = false);
                this.sendEvent(11);
                if (this.isDisposed()) {
                    return;
                }
                if (this.layout != null) {
                    this.markLayout(false, false);
                    this.updateLayout(false, false);
                }
                this.setResizeChildren(true);
            }
        }
    }
    
    void setCheckboxImageList(final int width, final int height, final boolean fixScroll) {
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        final int count = 8;
        int flags = 32;
        if ((this.style & 0x4000000) != 0x0) {
            flags |= 0x2000;
        }
        if (!OS.IsAppThemed()) {
            flags |= 0x1;
        }
        final long hStateList = OS.ImageList_Create(width, height, flags, 8, 8);
        final long hDC = OS.GetDC(this.handle);
        final long memDC = OS.CreateCompatibleDC(hDC);
        final long hBitmap = OS.CreateCompatibleBitmap(hDC, width * 8, height);
        final long hOldBitmap = OS.SelectObject(memDC, hBitmap);
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, width * 8, height);
        int clrBackground;
        if (OS.IsAppThemed()) {
            Control control = this.findBackgroundControl();
            if (control == null) {
                control = (Control)this;
            }
            clrBackground = control.getBackgroundPixel();
        }
        else {
            clrBackground = 33554687;
            if ((clrBackground & 0xFFFFFF) == OS.GetSysColor(5)) {
                clrBackground = 33619712;
            }
        }
        final long hBrush = OS.CreateSolidBrush(clrBackground);
        OS.FillRect(memDC, rect, hBrush);
        OS.DeleteObject(hBrush);
        final long oldFont = OS.SelectObject(hDC, this.defaultFont());
        final TEXTMETRIC tm = new TEXTMETRIC();
        OS.GetTextMetrics(hDC, tm);
        OS.SelectObject(hDC, oldFont);
        int itemWidth = Math.min(tm.tmHeight, width);
        int itemHeight = Math.min(tm.tmHeight, height);
        if (OS.IsAppThemed()) {
            final SIZE size = new SIZE();
            OS.GetThemePartSize(this.display.hButtonTheme(), memDC, 3, 0, (RECT)null, 1, size);
            itemWidth = Math.min(size.cx, itemWidth);
            itemHeight = Math.min(size.cy, itemHeight);
        }
        final int left = (width - itemWidth) / 2;
        final int top = (height - itemHeight) / 2;
        OS.SetRect(rect, left, top, left + itemWidth, top + itemHeight);
        if (OS.IsAppThemed()) {
            final long hTheme = this.display.hButtonTheme();
            OS.DrawThemeBackground(hTheme, memDC, 3, 1, rect, (RECT)null);
            final RECT rect30;
            final RECT rect2 = rect30 = rect;
            rect30.left += width;
            final RECT rect31;
            final RECT rect3 = rect31 = rect;
            rect31.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 5, rect, (RECT)null);
            final RECT rect32;
            final RECT rect4 = rect32 = rect;
            rect32.left += width;
            final RECT rect33;
            final RECT rect5 = rect33 = rect;
            rect33.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 1, rect, (RECT)null);
            final RECT rect34;
            final RECT rect6 = rect34 = rect;
            rect34.left += width;
            final RECT rect35;
            final RECT rect7 = rect35 = rect;
            rect35.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 9, rect, (RECT)null);
            final RECT rect36;
            final RECT rect8 = rect36 = rect;
            rect36.left += width;
            final RECT rect37;
            final RECT rect9 = rect37 = rect;
            rect37.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 4, rect, (RECT)null);
            final RECT rect38;
            final RECT rect10 = rect38 = rect;
            rect38.left += width;
            final RECT rect39;
            final RECT rect11 = rect39 = rect;
            rect39.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 8, rect, (RECT)null);
            final RECT rect40;
            final RECT rect12 = rect40 = rect;
            rect40.left += width;
            final RECT rect41;
            final RECT rect13 = rect41 = rect;
            rect41.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 4, rect, (RECT)null);
            final RECT rect42;
            final RECT rect14 = rect42 = rect;
            rect42.left += width;
            final RECT rect43;
            final RECT rect15 = rect43 = rect;
            rect43.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 12, rect, (RECT)null);
        }
        else {
            OS.DrawFrameControl(memDC, rect, 4, 16384);
            final RECT rect44;
            final RECT rect16 = rect44 = rect;
            rect44.left += width;
            final RECT rect45;
            final RECT rect17 = rect45 = rect;
            rect45.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 17408);
            final RECT rect46;
            final RECT rect18 = rect46 = rect;
            rect46.left += width;
            final RECT rect47;
            final RECT rect19 = rect47 = rect;
            rect47.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 16640);
            final RECT rect48;
            final RECT rect20 = rect48 = rect;
            rect48.left += width;
            final RECT rect49;
            final RECT rect21 = rect49 = rect;
            rect49.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 17664);
            final RECT rect50;
            final RECT rect22 = rect50 = rect;
            rect50.left += width;
            final RECT rect51;
            final RECT rect23 = rect51 = rect;
            rect51.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 16384);
            final RECT rect52;
            final RECT rect24 = rect52 = rect;
            rect52.left += width;
            final RECT rect53;
            final RECT rect25 = rect53 = rect;
            rect53.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 17408);
            final RECT rect54;
            final RECT rect26 = rect54 = rect;
            rect54.left += width;
            final RECT rect55;
            final RECT rect27 = rect55 = rect;
            rect55.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 16640);
            final RECT rect56;
            final RECT rect28 = rect56 = rect;
            rect56.left += width;
            final RECT rect57;
            final RECT rect29 = rect57 = rect;
            rect57.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 17664);
        }
        OS.SelectObject(memDC, hOldBitmap);
        OS.DeleteDC(memDC);
        OS.ReleaseDC(this.handle, hDC);
        if (OS.IsAppThemed()) {
            OS.ImageList_Add(hStateList, hBitmap, 0L);
        }
        else {
            OS.ImageList_AddMasked(hStateList, hBitmap, clrBackground);
        }
        OS.DeleteObject(hBitmap);
        final int topIndex = this.getTopIndex();
        if (fixScroll && topIndex != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        final long hOldStateList = OS.SendMessage(this.handle, 4098, 2L, 0L);
        OS.SendMessage(this.handle, 4099, 2L, hStateList);
        if (hOldStateList != 0L) {
            OS.ImageList_Destroy(hOldStateList);
        }
        final long hImageList = OS.SendMessage(this.handle, 4098, 1L, 0L);
        OS.SendMessage(this.handle, 4099, 1L, hImageList);
        if (fixScroll && topIndex != 0) {
            this.setTopIndex(topIndex);
            this.setRedraw(true);
        }
    }
    
    void setFocusIndex(final int index) {
        if (index < 0) {
            return;
        }
        final LVITEM lvItem = new LVITEM();
        lvItem.state = 1;
        lvItem.stateMask = 1;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, (long)index, lvItem);
        this.ignoreSelect = false;
        OS.SendMessage(this.handle, 4163, 0L, (long)index);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        final int topIndex = this.getTopIndex();
        if (topIndex != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        if (this.itemHeight != -1) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            OS.SetWindowLong(this.handle, -16, bits | 0x400);
        }
        super.setFont(font);
        if (this.itemHeight != -1) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            OS.SetWindowLong(this.handle, -16, bits & 0xFFFFFBFF);
        }
        this.setScrollWidth(null, true);
        if (topIndex != 0) {
            this.setTopIndex(topIndex);
            this.setRedraw(true);
        }
        OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
    }
    
    void setForegroundPixel(int pixel) {
        if (pixel == -1) {
            pixel = -16777216;
        }
        OS.SendMessage(this.handle, 4132, 0L, (long)pixel);
        OS.InvalidateRect(this.handle, (RECT)null, true);
        OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
    }
    
    public void setHeaderBackground(final Color color) {
        this.checkWidget();
        int pixel = -1;
        if (color != null) {
            if (color.isDisposed()) {
                this.error(5);
            }
            pixel = color.handle;
        }
        if (pixel == this.headerBackground) {
            return;
        }
        this.headerBackground = pixel;
        if (this.getHeaderVisible()) {
            OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
        }
    }
    
    public void setHeaderForeground(final Color color) {
        this.checkWidget();
        int pixel = -1;
        if (color != null) {
            if (color.isDisposed()) {
                this.error(5);
            }
            pixel = color.handle;
        }
        if (pixel == this.headerForeground) {
            return;
        }
        this.headerForeground = pixel;
        if (this.getHeaderVisible()) {
            OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
        }
    }
    
    public void setHeaderVisible(final boolean show) {
        this.checkWidget();
        int newBits = OS.GetWindowLong(this.handle, -16);
        newBits &= 0xFFFFBFFF;
        if (!show) {
            newBits |= 0x4000;
        }
        final int oldIndex = this.getTopIndex();
        OS.SetWindowLong(this.handle, -16, newBits);
        final int newIndex = this.getTopIndex();
        if (newIndex != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        this.setTopIndex(oldIndex);
        if (newIndex != 0) {
            this.setRedraw(true);
        }
        this.updateHeaderToolTips();
    }
    
    public void setItemCount(int count) {
        this.checkWidget();
        count = Math.max(0, count);
        final int itemCount = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (count == itemCount) {
            return;
        }
        this.setDeferResize(true);
        final boolean isVirtual = (this.style & 0x10000000) != 0x0;
        if (!isVirtual) {
            this.setRedraw(false);
        }
        int index;
        for (index = count; index < itemCount; ++index) {
            final TableItem item = this._getItem(index, false);
            if (item != null && !item.isDisposed()) {
                item.release(false);
            }
            if (!isVirtual) {
                final boolean b = true;
                this.ignoreShrink = true;
                this.ignoreSelect = true;
                final long code = OS.SendMessage(this.handle, 4104, (long)count, 0L);
                final boolean b2 = false;
                this.ignoreShrink = false;
                this.ignoreSelect = false;
                if (code == 0L) {
                    break;
                }
            }
        }
        if (index < itemCount) {
            this.error(15);
        }
        this._setItemCount(count, itemCount);
        if (isVirtual) {
            final int flags = 3;
            OS.SendMessage(this.handle, 4143, (long)count, 3L);
            if (count == 0 && itemCount != 0) {
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
        }
        else {
            for (int i = itemCount; i < count; ++i) {
                new TableItem(this, 0, i, true);
            }
        }
        if (!isVirtual) {
            this.setRedraw(true);
        }
        if (itemCount == 0) {
            this.setScrollWidth(null, false);
        }
        this.setDeferResize(false);
    }
    
    void setItemHeight(final boolean fixScroll) {
        final int topIndex = this.getTopIndex();
        if (fixScroll && topIndex != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        if (this.itemHeight == -1) {
            final long hFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            OS.SendMessage(this.handle, 48, hFont, 0L);
        }
        else {
            this.forceResize();
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            final int width = rect.right - rect.left;
            final int height = rect.bottom - rect.top;
            final int bits = OS.GetWindowLong(this.handle, -16);
            OS.SetWindowLong(this.handle, -16, bits | 0x400);
            final int flags = 30;
            this.ignoreResize = true;
            OS.SetWindowPos(this.handle, 0L, 0, 0, width, height + 1, 30);
            OS.SetWindowPos(this.handle, 0L, 0, 0, width, height, 30);
            this.ignoreResize = false;
            OS.SetWindowLong(this.handle, -16, bits);
        }
        if (fixScroll && topIndex != 0) {
            this.setTopIndex(topIndex);
            this.setRedraw(true);
        }
    }
    
    void setItemHeight(final int itemHeight) {
        this.checkWidget();
        if (itemHeight < -1) {
            this.error(5);
        }
        this.itemHeight = itemHeight;
        this.setItemHeight(true);
        this.setScrollWidth(null, true);
    }
    
    public void setLinesVisible(final boolean show) {
        this.checkWidget();
        final int newBits = show ? 1 : 0;
        OS.SendMessage(this.handle, 4150, 1L, (long)newBits);
        OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
    }
    
    public void setRedraw(final boolean redraw) {
        this.checkWidget();
        if (this.drawCount == 0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x10000000) == 0x0) {
                this.state |= 0x10;
            }
        }
        if (redraw) {
            if (--this.drawCount == 0) {
                this.setScrollWidth(null, true);
                this.setDeferResize(true);
                OS.SendMessage(this.handle, 11, 1L, 0L);
                if (this.hwndHeader != 0L) {
                    OS.SendMessage(this.hwndHeader, 11, 1L, 0L);
                }
                if ((this.state & 0x10) != 0x0) {
                    this.state &= 0xFFFFFFEF;
                    OS.ShowWindow(this.handle, 0);
                }
                else {
                    final int flags = 1157;
                    OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
                }
                this.setDeferResize(false);
            }
        }
        else if (this.drawCount++ == 0) {
            OS.SendMessage(this.handle, 11, 0L, 0L);
            if (this.hwndHeader != 0L) {
                OS.SendMessage(this.hwndHeader, 11, 0L, 0L);
            }
        }
    }
    
    void setScrollWidth(final int width) {
        if (width != (int)OS.SendMessage(this.handle, 4125, 0L, 0L)) {
            boolean redraw = false;
            if (this.hooks(41)) {
                redraw = (this.getDrawing() && OS.IsWindowVisible(this.handle));
            }
            if (redraw) {
                OS.DefWindowProc(this.handle, 11, 0L, 0L);
            }
            OS.SendMessage(this.handle, 4126, 0L, (long)width);
            if (redraw) {
                OS.DefWindowProc(this.handle, 11, 1L, 0L);
                final int flags = 1157;
                OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
            }
        }
    }
    
    boolean setScrollWidth(final TableItem item, final boolean force) {
        if (this.currentItem != null) {
            if (this.currentItem != item) {
                this.fixScrollWidth = true;
            }
            return false;
        }
        if (!force && (!this.getDrawing() || !OS.IsWindowVisible(this.handle))) {
            this.fixScrollWidth = true;
            return false;
        }
        this.fixScrollWidth = false;
        if (this.columnCount == 0) {
            int newWidth = 0;
            int imageIndent = 0;
            for (int index = 0, itemCount = (int)OS.SendMessage(this.handle, 4100, 0L, 0L); index < itemCount; ++index) {
                String string = null;
                long hFont = -1L;
                if (item != null) {
                    string = item.text;
                    imageIndent = Math.max(imageIndent, item.imageIndent);
                    hFont = item.fontHandle(0);
                }
                else {
                    final TableItem tableItem = this._getItem(index, false);
                    if (tableItem != null) {
                        string = tableItem.text;
                        imageIndent = Math.max(imageIndent, tableItem.imageIndent);
                        hFont = tableItem.fontHandle(0);
                    }
                }
                if (string != null && string.length() != 0) {
                    if (hFont != -1L) {
                        final long hDC = OS.GetDC(this.handle);
                        final long oldFont = OS.SelectObject(hDC, hFont);
                        final int flags = 3104;
                        final char[] buffer = string.toCharArray();
                        final RECT rect = new RECT();
                        OS.DrawText(hDC, buffer, buffer.length, rect, 3104);
                        OS.SelectObject(hDC, oldFont);
                        OS.ReleaseDC(this.handle, hDC);
                        newWidth = Math.max(newWidth, rect.right - rect.left);
                    }
                    else {
                        final TCHAR buffer2 = new TCHAR(this.getCodePage(), string, true);
                        newWidth = Math.max(newWidth, (int)OS.SendMessage(this.handle, 4183, 0L, buffer2));
                    }
                }
                if (item != null) {
                    break;
                }
            }
            if (newWidth == 0) {
                final char[] buffer3 = { ' ', '\0' };
                newWidth = Math.max(newWidth, (int)OS.SendMessage(this.handle, 4183, 0L, buffer3));
            }
            final long hStateList = OS.SendMessage(this.handle, 4098, 2L, 0L);
            if (hStateList != 0L) {
                final int[] cx = { 0 };
                final int[] cy = { 0 };
                OS.ImageList_GetIconSize(hStateList, cx, cy);
                newWidth += cx[0] + 4;
            }
            final long hImageList = OS.SendMessage(this.handle, 4098, 1L, 0L);
            if (hImageList != 0L) {
                final int[] cx2 = { 0 };
                final int[] cy2 = { 0 };
                OS.ImageList_GetIconSize(hImageList, cx2, cy2);
                newWidth += (imageIndent + 1) * cx2[0];
            }
            else {
                ++newWidth;
            }
            newWidth += 10;
            final int oldWidth = (int)OS.SendMessage(this.handle, 4125, 0L, 0L);
            if (newWidth > oldWidth) {
                this.setScrollWidth(newWidth);
                return true;
            }
        }
        return false;
    }
    
    public void setSelection(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        this.deselectAll();
        final int length = indices.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        this.select(indices);
        final int focusIndex = indices[0];
        if (focusIndex != -1) {
            this.setFocusIndex(focusIndex);
        }
        this.showSelection();
    }
    
    public void setSelection(final TableItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        this.setSelection(new TableItem[] { item });
    }
    
    public void setSelection(final TableItem[] items) {
        this.checkWidget();
        if (items == null) {
            this.error(4);
        }
        this.deselectAll();
        final int length = items.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        int focusIndex = -1;
        for (int i = length - 1; i >= 0; --i) {
            final int index = this.indexOf(items[i]);
            if (index != -1) {
                this.select(focusIndex = index);
            }
        }
        if (focusIndex != -1) {
            this.setFocusIndex(focusIndex);
        }
        this.showSelection();
    }
    
    public void setSelection(final int index) {
        this.checkWidget();
        this.deselectAll();
        this.select(index);
        if (index != -1) {
            this.setFocusIndex(index);
        }
        this.showSelection();
    }
    
    public void setSelection(int start, int end) {
        this.checkWidget();
        this.deselectAll();
        if (end < 0 || start > end || ((this.style & 0x4) != 0x0 && start != end)) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (count == 0 || start >= count) {
            return;
        }
        start = Math.max(0, start);
        end = Math.min(end, count - 1);
        this.select(start, end);
        this.setFocusIndex(start);
        this.showSelection();
    }
    
    public void setSortColumn(final TableColumn column) {
        this.checkWidget();
        if (column != null && column.isDisposed()) {
            this.error(5);
        }
        if (this.sortColumn != null && !this.sortColumn.isDisposed()) {
            this.sortColumn.setSortDirection(0);
        }
        this.sortColumn = column;
        if (this.sortColumn != null && this.sortDirection != 0) {
            this.sortColumn.setSortDirection(this.sortDirection);
        }
    }
    
    public void setSortDirection(final int direction) {
        this.checkWidget();
        if ((direction & 0x480) == 0x0 && direction != 0) {
            return;
        }
        this.sortDirection = direction;
        if (this.sortColumn != null && !this.sortColumn.isDisposed()) {
            this.sortColumn.setSortDirection(direction);
        }
    }
    
    void setSubImagesVisible(final boolean visible) {
        final int dwExStyle = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
        if ((dwExStyle & 0x2) != 0x0 == visible) {
            return;
        }
        final int bits = visible ? 2 : 0;
        OS.SendMessage(this.handle, 4150, 2L, (long)bits);
    }
    
    void setTableEmpty() {
        if (this.imageList != null) {
            final long hImageList = OS.ImageList_Create(1, 1, 0, 0, 0);
            OS.SendMessage(this.handle, 4099, 1L, hImageList);
            OS.SendMessage(this.handle, 4099, 1L, 0L);
            if (this.headerImageList != null) {
                final long hHeaderImageList = this.headerImageList.getHandle();
                final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                OS.SendMessage(hwndHeader, 4616, 0L, hHeaderImageList);
            }
            OS.ImageList_Destroy(hImageList);
            this.display.releaseImageList(this.imageList);
            this.imageList = null;
            if (this.itemHeight != -1) {
                this.setItemHeight(false);
            }
        }
        if (!this.hooks(41) && !this.hooks(40) && !this.hooks(42)) {
            Control control = this.findBackgroundControl();
            if (control == null) {
                control = (Control)this;
            }
            if (control.backgroundImage == null) {
                this.setCustomDraw(false);
                this.setBackgroundTransparent(false);
            }
        }
        this._initItems();
        if (this.columnCount == 0) {
            OS.SendMessage(this.handle, 4126, 0L, 0L);
            this.setScrollWidth(null, false);
        }
    }
    
    public void setTopIndex(final int index) {
        this.checkWidget();
        final int topIndex = (int)OS.SendMessage(this.handle, 4135, 0L, 0L);
        if (index == topIndex) {
            return;
        }
        if (!this.painted && this.hooks(41)) {
            this.hitTestSelection(index, 0, 0);
        }
        if (OS.SendMessage(this.handle, 4136, 0L, 0L) <= 0L) {
            OS.SendMessage(this.handle, 4115, (long)index, 1L);
            if (index != OS.SendMessage(this.handle, 4135, 0L, 0L)) {
                OS.SendMessage(this.handle, 4115, (long)index, 1L);
            }
            return;
        }
        final RECT rect = new RECT();
        rect.left = 0;
        this.ignoreCustomDraw = true;
        OS.SendMessage(this.handle, 4110, 0L, rect);
        this.ignoreCustomDraw = false;
        final int dy = (index - topIndex) * (rect.bottom - rect.top);
        OS.SendMessage(this.handle, 4116, 0L, (long)dy);
    }
    
    public void showColumn(final TableColumn column) {
        this.checkWidget();
        if (column == null) {
            this.error(4);
        }
        if (column.isDisposed()) {
            this.error(5);
        }
        if (column.parent != this) {
            return;
        }
        final int index = this.indexOf(column);
        if (0 > index || index >= this.columnCount) {
            return;
        }
        final RECT itemRect = new RECT();
        itemRect.left = 0;
        if (index == 0) {
            itemRect.top = 1;
            this.ignoreCustomDraw = true;
            OS.SendMessage(this.handle, 4152, -1L, itemRect);
            this.ignoreCustomDraw = false;
            itemRect.right = itemRect.left;
            final int width = (int)OS.SendMessage(this.handle, 4125, 0L, 0L);
            itemRect.left = itemRect.right - width;
        }
        else {
            itemRect.top = index;
            this.ignoreCustomDraw = true;
            OS.SendMessage(this.handle, 4152, -1L, itemRect);
            this.ignoreCustomDraw = false;
        }
        int oldPos = 0;
        if (this._getLinesVisible()) {
            final SCROLLINFO info = new SCROLLINFO();
            info.cbSize = SCROLLINFO.sizeof;
            info.fMask = 4;
            OS.GetScrollInfo(this.handle, 0, info);
            oldPos = info.nPos;
        }
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        if (itemRect.left < rect.left) {
            final int dx = itemRect.left - rect.left;
            OS.SendMessage(this.handle, 4116, (long)dx, 0L);
        }
        else {
            final int width2 = Math.min(rect.right - rect.left, itemRect.right - itemRect.left);
            if (itemRect.left + width2 > rect.right) {
                final int dx2 = itemRect.left + width2 - rect.right;
                OS.SendMessage(this.handle, 4116, (long)dx2, 0L);
            }
        }
        if (this._getLinesVisible()) {
            final SCROLLINFO info2 = new SCROLLINFO();
            info2.cbSize = SCROLLINFO.sizeof;
            info2.fMask = 4;
            OS.GetScrollInfo(this.handle, 0, info2);
            final int newPos = info2.nPos;
            if (newPos < oldPos) {
                rect.right = oldPos - newPos + 1;
                OS.InvalidateRect(this.handle, rect, true);
            }
        }
    }
    
    void showItem(final int index) {
        if (!this.painted && this.hooks(41)) {
            this.hitTestSelection(index, 0, 0);
        }
        final long counterPage = OS.SendMessage(this.handle, 4136, 0L, 0L);
        if (counterPage <= 0L) {
            OS.SendMessage(this.handle, 4115, (long)index, 1L);
            if (index != OS.SendMessage(this.handle, 4135, 0L, 0L)) {
                OS.SendMessage(this.handle, 4115, (long)index, 1L);
            }
        }
        else {
            final long topIndex = OS.SendMessage(this.handle, 4135, 0L, 0L);
            if (topIndex > index || index >= topIndex + counterPage) {
                OS.SendMessage(this.handle, 4115, (long)index, 0L);
            }
        }
    }
    
    public void showItem(final TableItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        final int index = this.indexOf(item);
        if (index != -1) {
            this.showItem(index);
        }
    }
    
    public void showSelection() {
        this.checkWidget();
        final int index = (int)OS.SendMessage(this.handle, 4108, -1L, 2L);
        if (index != -1) {
            if (this.display.getActiveShell() == this.getShell() && (this.style & 0x10) == 0x0 && (this.verticalBar == null || !this.verticalBar.isVisible())) {
                this.showItem(0);
            }
            else {
                this.showItem(index);
            }
        }
    }
    
    void sort() {
        this.checkWidget();
    }
    
    void subclass() {
        super.subclass();
        if (Table.HeaderProc != 0L) {
            OS.SetWindowLongPtr(this.hwndHeader, -4, this.display.windowProc);
        }
    }
    
    RECT toolTipInset(final RECT rect) {
        final RECT insetRect = new RECT();
        OS.SetRect(insetRect, rect.left - 1, rect.top - 1, rect.right + 1, rect.bottom + 1);
        return insetRect;
    }
    
    RECT toolTipRect(final RECT rect) {
        final RECT toolRect = new RECT();
        OS.SetRect(toolRect, rect.left - 1, rect.top - 1, rect.right + 1, rect.bottom + 1);
        return toolRect;
    }
    
    String toolTipText(final NMTTDISPINFO hdr) {
        final long hwndToolTip = OS.SendMessage(this.handle, 4174, 0L, 0L);
        if (hwndToolTip == hdr.hwndFrom && this.toolTipText != null) {
            return "";
        }
        if (this.headerToolTipHandle == hdr.hwndFrom) {
            for (int i = 0; i < this.columnCount; ++i) {
                final TableColumn column = this.columns[i];
                if (column.id == hdr.idFrom) {
                    return column.toolTipText;
                }
            }
        }
        return super.toolTipText(hdr);
    }
    
    void unsubclass() {
        super.unsubclass();
        if (Table.HeaderProc != 0L) {
            OS.SetWindowLongPtr(this.hwndHeader, -4, Table.HeaderProc);
        }
    }
    
    void update(final boolean all) {
        long oldHeaderProc = 0L;
        long oldTableProc = 0L;
        final boolean fixSubclass = this.isOptimizedRedraw();
        if (fixSubclass) {
            oldTableProc = OS.SetWindowLongPtr(this.handle, -4, Table.TableProc);
            oldHeaderProc = OS.SetWindowLongPtr(this.hwndHeader, -4, Table.HeaderProc);
        }
        super.update(all);
        if (fixSubclass) {
            OS.SetWindowLongPtr(this.handle, -4, oldTableProc);
            OS.SetWindowLongPtr(this.hwndHeader, -4, oldHeaderProc);
        }
    }
    
    void updateHeaderToolTips() {
        if (this.headerToolTipHandle == 0L) {
            return;
        }
        final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
        final RECT rect = new RECT();
        final TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        lpti.uFlags = 16;
        lpti.hwnd = hwndHeader;
        lpti.lpszText = -1L;
        for (int i = 0; i < this.columnCount; ++i) {
            final TableColumn column = this.columns[i];
            if (OS.SendMessage(hwndHeader, 4615, (long)i, rect) != 0L) {
                final TOOLINFO toolinfo = lpti;
                final TableColumn tableColumn = column;
                final int id = this.display.nextToolTipId++;
                tableColumn.id = id;
                toolinfo.uId = id;
                lpti.left = rect.left;
                lpti.top = rect.top;
                lpti.right = rect.right;
                lpti.bottom = rect.bottom;
                OS.SendMessage(this.headerToolTipHandle, 1074, 0L, lpti);
            }
        }
    }
    
    void updateMenuLocation(final Event event) {
        final Rectangle clientArea = this.getClientAreaInPixels();
        int x = clientArea.x;
        int y = clientArea.y;
        final int focusIndex = this.getFocusIndex();
        if (focusIndex != -1) {
            final TableItem focusItem = this.getItem(focusIndex);
            Rectangle bounds = focusItem.getBoundsInPixels(0);
            if (focusItem.text != null && focusItem.text.length() != 0) {
                bounds = focusItem.getBoundsInPixels();
            }
            x = Math.max(x, bounds.x + bounds.width / 2);
            x = Math.min(x, clientArea.x + clientArea.width);
            y = Math.max(y, bounds.y + bounds.height);
            y = Math.min(y, clientArea.y + clientArea.height);
        }
        final Point pt = this.toDisplayInPixels(x, y);
        event.setLocationInPixels(pt.x, pt.y);
    }
    
    void updateMoveable() {
        int index;
        for (index = 0; index < this.columnCount && !this.columns[index].moveable; ++index) {}
        final int newBits = (index < this.columnCount) ? 16 : 0;
        OS.SendMessage(this.handle, 4150, 16L, (long)newBits);
    }
    
    void updateOrientation() {
        super.updateOrientation();
        final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
        if (hwndHeader != 0L) {
            int bits = OS.GetWindowLong(hwndHeader, -20);
            if ((this.style & 0x4000000) != 0x0) {
                bits |= 0x400000;
            }
            else {
                bits &= 0xFFBFFFFF;
            }
            bits &= 0xFFFFDFFF;
            OS.SetWindowLong(hwndHeader, -20, bits);
            OS.InvalidateRect(hwndHeader, (RECT)null, true);
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            final int width = rect.right - rect.left;
            final int height = rect.bottom - rect.top;
            OS.SetWindowPos(this.handle, 0L, 0, 0, width - 1, height - 1, 6);
            OS.SetWindowPos(this.handle, 0L, 0, 0, width, height, 6);
        }
        if ((this.style & 0x20) != 0x0) {
            this.fixCheckboxImageListColor(false);
        }
        if (this.imageList != null) {
            final Point size = this.imageList.getImageSize();
            this.display.releaseImageList(this.imageList);
            this.imageList = this.display.getImageList(this.style & 0x4000000, size.x, size.y);
            for (int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L), i = 0; i < count; ++i) {
                final TableItem item = this._getItem(i, false);
                if (item != null) {
                    final Image image = item.image;
                    if (image != null) {
                        final int index = this.imageList.indexOf(image);
                        if (index == -1) {
                            this.imageList.add(image);
                        }
                    }
                }
            }
            final long hImageList = this.imageList.getHandle();
            OS.SendMessage(this.handle, 4099, 1L, hImageList);
        }
        if (hwndHeader != 0L && this.headerImageList != null) {
            final Point size = this.headerImageList.getImageSize();
            this.display.releaseImageList(this.headerImageList);
            this.headerImageList = this.display.getImageList(this.style & 0x4000000, size.x, size.y);
            if (this.columns != null) {
                for (int j = 0; j < this.columns.length; ++j) {
                    final TableColumn column = this.columns[j];
                    if (column != null) {
                        final Image image2 = column.image;
                        if (image2 != null) {
                            final LVCOLUMN lvColumn = new LVCOLUMN();
                            lvColumn.mask = 1;
                            OS.SendMessage(hwndHeader, 4191, (long)j, lvColumn);
                            if ((lvColumn.fmt & 0x800) != 0x0) {
                                final int index = this.headerImageList.indexOf(image2);
                                if (index == -1) {
                                    this.headerImageList.add(image2);
                                }
                                lvColumn.iImage = index;
                                lvColumn.mask = 16;
                                OS.SendMessage(hwndHeader, 4192, (long)j, lvColumn);
                            }
                        }
                    }
                }
            }
            final long hHeaderImageList = this.headerImageList.getHandle();
            OS.SendMessage(hwndHeader, 4616, 0L, hHeaderImageList);
        }
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            if (textDirection == 100663296 || (this.state & 0x400000) != 0x0) {
                for (final TableItem item : this.items) {
                    if (item != null) {
                        item.updateTextDirection((textDirection == 100663296) ? 100663296 : (this.style & Integer.MIN_VALUE));
                    }
                }
            }
            OS.InvalidateRect(this.handle, (RECT)null, true);
            return true;
        }
        return false;
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x40;
        if ((this.style & 0x8000) == 0x0) {
            bits |= 0x8;
        }
        if ((this.style & 0x4) != 0x0) {
            bits |= 0x4;
        }
        bits |= 0x4001;
        if ((this.style & 0x10000000) != 0x0) {
            bits |= 0x1000;
        }
        return bits;
    }
    
    TCHAR windowClass() {
        return Table.TableClass;
    }
    
    long windowProc() {
        return Table.TableProc;
    }
    
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (hwnd != this.handle) {
            Label_0337: {
                switch (msg) {
                    case 123: {
                        final LRESULT result = this.wmContextMenu(hwnd, wParam, lParam);
                        if (result != null) {
                            return result.value;
                        }
                        break;
                    }
                    case 675: {
                        this.updateHeaderToolTips();
                        this.updateHeaderToolTips();
                        break;
                    }
                    case 78: {
                        final NMHDR hdr = new NMHDR();
                        OS.MoveMemory(hdr, lParam, NMHDR.sizeof);
                        switch (hdr.code) {
                            case -530:
                            case -522:
                            case -521: {
                                return OS.SendMessage(this.handle, msg, wParam, lParam);
                            }
                            default: {
                                break Label_0337;
                            }
                        }
                        break;
                    }
                    case 32: {
                        if (wParam != hwnd) {
                            break;
                        }
                        final int hitTest = (short)OS.LOWORD(lParam);
                        if (hitTest != 1) {
                            break;
                        }
                        final HDHITTESTINFO pinfo = new HDHITTESTINFO();
                        final int pos = OS.GetMessagePos();
                        final POINT pt = new POINT();
                        OS.POINTSTOPOINT(pt, (long)pos);
                        OS.ScreenToClient(hwnd, pt);
                        pinfo.x = pt.x;
                        pinfo.y = pt.y;
                        final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                        final int index = (int)OS.SendMessage(hwndHeader, 4614, 0L, pinfo);
                        if (0 <= index && index < this.columnCount && !this.columns[index].resizable && (pinfo.flags & 0xC) != 0x0) {
                            OS.SetCursor(OS.LoadCursor(0L, 32512L));
                            return 1L;
                        }
                        break;
                    }
                }
            }
            return this.callWindowProc(hwnd, msg, wParam, lParam);
        }
        if (msg != Display.DI_GETDRAGIMAGE) {
            return super.windowProc(hwnd, msg, wParam, lParam);
        }
        final int topIndex = (int)OS.SendMessage(this.handle, 4135, 0L, 0L);
        int selection = (int)OS.SendMessage(this.handle, 4108, (long)(topIndex - 1), 2L);
        if (selection == -1) {
            return 0L;
        }
        final POINT mousePos = new POINT();
        OS.POINTSTOPOINT(mousePos, (long)OS.GetMessagePos());
        OS.MapWindowPoints(0L, this.handle, mousePos, 1);
        final RECT clientRect = new RECT();
        OS.GetClientRect(this.handle, clientRect);
        final TableItem item = this._getItem(selection);
        final RECT rect = item.getBounds(selection, 0, true, true, true);
        if ((this.style & 0x10000) != 0x0) {
            final int width = 301;
            rect.left = Math.max(clientRect.left, mousePos.x - 150);
            if (clientRect.right > rect.left + 301) {
                rect.right = rect.left + 301;
            }
            else {
                rect.right = clientRect.right;
                rect.left = Math.max(clientRect.left, rect.right - 301);
            }
        }
        final long hRgn = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
        while ((selection = (int)OS.SendMessage(this.handle, 4108, (long)selection, 2L)) != -1) {
            if (rect.bottom - rect.top > 301) {
                break;
            }
            if (rect.bottom > clientRect.bottom) {
                break;
            }
            final RECT itemRect = item.getBounds(selection, 0, true, true, true);
            final long rectRgn = OS.CreateRectRgn(rect.left, itemRect.top, rect.right, itemRect.bottom);
            OS.CombineRgn(hRgn, hRgn, rectRgn, 2);
            OS.DeleteObject(rectRgn);
            rect.bottom = itemRect.bottom;
        }
        OS.GetRgnBox(hRgn, rect);
        final long hdc = OS.GetDC(this.handle);
        final long memHdc = OS.CreateCompatibleDC(hdc);
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biWidth = rect.right - rect.left;
        bmiHeader.biHeight = -(rect.bottom - rect.top);
        bmiHeader.biPlanes = 1;
        bmiHeader.biBitCount = 32;
        bmiHeader.biCompression = 0;
        final byte[] bmi = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
        final long[] pBits = { 0L };
        final long memDib = OS.CreateDIBSection(0L, bmi, 0, pBits, 0L, 0);
        if (memDib == 0L) {
            this.error(2);
        }
        final long oldMemBitmap = OS.SelectObject(memHdc, memDib);
        final int colorKey = 253;
        final POINT pt2 = new POINT();
        OS.SetWindowOrgEx(memHdc, rect.left, rect.top, pt2);
        OS.FillRect(memHdc, rect, this.findBrush(253L, 0));
        OS.OffsetRgn(hRgn, -rect.left, -rect.top);
        OS.SelectClipRgn(memHdc, hRgn);
        OS.PrintWindow(this.handle, memHdc, 0);
        OS.SetWindowOrgEx(memHdc, pt2.x, pt2.y, (POINT)null);
        OS.SelectObject(memHdc, oldMemBitmap);
        OS.DeleteDC(memHdc);
        OS.ReleaseDC(0L, hdc);
        OS.DeleteObject(hRgn);
        final SHDRAGIMAGE shdi = new SHDRAGIMAGE();
        shdi.hbmpDragImage = memDib;
        shdi.crColorKey = 253;
        shdi.sizeDragImage.cx = bmiHeader.biWidth;
        shdi.sizeDragImage.cy = -bmiHeader.biHeight;
        shdi.ptOffset.x = mousePos.x - rect.left;
        shdi.ptOffset.y = mousePos.y - rect.top;
        if ((this.style & 0x8000000) != 0x0) {
            shdi.ptOffset.x = shdi.sizeDragImage.cx - shdi.ptOffset.x;
        }
        OS.MoveMemory(lParam, shdi, SHDRAGIMAGE.sizeof);
        return 1L;
    }
    
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CHAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 32: {
                if ((this.style & 0x20) != 0x0) {
                    int index = -1;
                    while ((index = (int)OS.SendMessage(this.handle, 4108, (long)index, 2L)) != -1) {
                        final TableItem item = this._getItem(index);
                        item.setChecked(!item.getChecked(), true);
                        OS.NotifyWinEvent(32773, this.handle, -4, index + 1);
                    }
                }
                final long code = this.callWindowProc(this.handle, 256, wParam, lParam);
                return new LRESULT(code);
            }
            case 13: {
                final int index2 = (int)OS.SendMessage(this.handle, 4108, -1L, 1L);
                if (index2 != -1) {
                    final Event event = new Event();
                    event.item = (Widget)this._getItem(index2);
                    this.sendSelectionEvent(14, event, false);
                }
                return LRESULT.ZERO;
            }
            default: {
                return result;
            }
        }
    }
    
    LRESULT WM_CONTEXTMENU(final long wParam, final long lParam) {
        if (!this.display.runDragDrop) {
            return LRESULT.ZERO;
        }
        return super.WM_CONTEXTMENU(wParam, lParam);
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
        if (this.findImageControl() != null) {
            return LRESULT.ONE;
        }
        return result;
    }
    
    LRESULT WM_GETOBJECT(final long wParam, final long lParam) {
        if ((this.style & 0x20) != 0x0 && this.accessible == null) {
            this.accessible = this.new_Accessible((Control)this);
        }
        return super.WM_GETOBJECT(wParam, lParam);
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 32: {
                return LRESULT.ZERO;
            }
            case 107: {
                if (OS.GetKeyState(17) >= 0) {
                    break;
                }
                int index;
                for (index = 0; index < this.columnCount && this.columns[index].getResizable(); ++index) {}
                if (index != this.columnCount || this.hooks(41)) {
                    final TableColumn[] newColumns = new TableColumn[this.columnCount];
                    System.arraycopy(this.columns, 0, newColumns, 0, this.columnCount);
                    for (final TableColumn column : newColumns) {
                        if (!column.isDisposed() && column.getResizable()) {
                            column.pack();
                        }
                    }
                    return LRESULT.ZERO;
                }
                break;
            }
            case 33:
            case 34:
            case 35:
            case 36: {
                long oldHeaderProc = 0L;
                long oldTableProc = 0L;
                final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                final boolean fixSubclass = this.isOptimizedRedraw();
                if (fixSubclass) {
                    oldTableProc = OS.SetWindowLongPtr(this.handle, -4, Table.TableProc);
                    oldHeaderProc = OS.SetWindowLongPtr(hwndHeader, -4, Table.HeaderProc);
                }
                final long code = this.callWindowProc(this.handle, 256, wParam, lParam);
                result = ((code == 0L) ? LRESULT.ZERO : new LRESULT(code));
                if (fixSubclass) {
                    OS.SetWindowLongPtr(this.handle, -4, oldTableProc);
                    OS.SetWindowLongPtr(hwndHeader, -4, oldHeaderProc);
                }
            }
            case 38:
            case 40: {
                OS.SendMessage(this.handle, 295, 3L, 0L);
                break;
            }
        }
        return result;
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KILLFOCUS(wParam, lParam);
        if (this.imageList != null || (this.style & 0x20) != 0x0) {
            OS.InvalidateRect(this.handle, (RECT)null, false);
        }
        return result;
    }
    
    LRESULT WM_LBUTTONDBLCLK(final long wParam, final long lParam) {
        final LVHITTESTINFO pinfo = new LVHITTESTINFO();
        pinfo.x = OS.GET_X_LPARAM(lParam);
        pinfo.y = OS.GET_Y_LPARAM(lParam);
        final int index = (int)OS.SendMessage(this.handle, 4114, 0L, pinfo);
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 1, this.handle, lParam);
        if (!this.sendMouseEvent(8, 1, this.handle, lParam)) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            return LRESULT.ZERO;
        }
        if (pinfo.iItem != -1) {
            this.callWindowProc(this.handle, 515, wParam, lParam);
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
            OS.SetCapture(this.handle);
        }
        if ((this.style & 0x20) != 0x0 && index != -1 && pinfo.flags == 8) {
            final TableItem item = this._getItem(index);
            if (item != null && !item.isDisposed()) {
                item.setChecked(!item.getChecked(), true);
                OS.NotifyWinEvent(32773, this.handle, -4, index + 1);
            }
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        final LRESULT result = this.sendMouseDownEvent(3, 1, 513, wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        if ((this.style & 0x20) != 0x0) {
            final LVHITTESTINFO pinfo = new LVHITTESTINFO();
            pinfo.x = OS.GET_X_LPARAM(lParam);
            pinfo.y = OS.GET_Y_LPARAM(lParam);
            final int index = (int)OS.SendMessage(this.handle, 4114, 0L, pinfo);
            if (index != -1 && pinfo.flags == 8) {
                final TableItem item = this._getItem(index);
                if (item != null && !item.isDisposed()) {
                    item.setChecked(!item.getChecked(), true);
                    OS.NotifyWinEvent(32773, this.handle, -4, index + 1);
                }
            }
        }
        return result;
    }
    
    LRESULT WM_MOUSEHOVER(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSEHOVER(wParam, lParam);
        final int bits = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
        final int mask = 200;
        if ((bits & 0xC8) != 0x0) {
            return result;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PAINT(final long wParam, final long lParam) {
        if ((this.state & 0x1000) != 0x0) {
            return LRESULT.ZERO;
        }
        this._checkShrink();
        if (this.fixScrollWidth) {
            this.setScrollWidth(null, true);
        }
        return super.WM_PAINT(wParam, lParam);
    }
    
    LRESULT WM_RBUTTONDBLCLK(final long wParam, final long lParam) {
        final LVHITTESTINFO pinfo = new LVHITTESTINFO();
        pinfo.x = OS.GET_X_LPARAM(lParam);
        pinfo.y = OS.GET_Y_LPARAM(lParam);
        OS.SendMessage(this.handle, 4114, 0L, pinfo);
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 3, this.handle, lParam);
        if (this.sendMouseEvent(8, 3, this.handle, lParam) && pinfo.iItem != -1) {
            this.callWindowProc(this.handle, 518, wParam, lParam);
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
            OS.SetCapture(this.handle);
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_RBUTTONDOWN(final long wParam, final long lParam) {
        return this.sendMouseDownEvent(3, 3, 516, wParam, lParam);
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETFOCUS(wParam, lParam);
        if (this.imageList != null || (this.style & 0x20) != 0x0) {
            OS.InvalidateRect(this.handle, (RECT)null, false);
        }
        final int count = (int)OS.SendMessage(this.handle, 4100, 0L, 0L);
        if (count == 0) {
            return result;
        }
        final int index = (int)OS.SendMessage(this.handle, 4108, -1L, 1L);
        if (index == -1) {
            final LVITEM lvItem = new LVITEM();
            lvItem.state = 1;
            lvItem.stateMask = 1;
            this.ignoreSelect = true;
            OS.SendMessage(this.handle, 4139, 0L, lvItem);
            this.ignoreSelect = false;
        }
        return result;
    }
    
    LRESULT WM_SETFONT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETFONT(wParam, lParam);
        if (result != null) {
            return result;
        }
        OS.SendMessage(this.hwndHeader, 48, 0L, lParam);
        if (this.headerToolTipHandle != 0L) {
            OS.SendMessage(this.headerToolTipHandle, 48, wParam, lParam);
        }
        return result;
    }
    
    LRESULT WM_SETREDRAW(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETREDRAW(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (wParam == 1L && (int)OS.SendMessage(this.handle, 4096, 0L, 0L) != -1 && (this.hooks(41) || this.hooks(40) || this.hooks(42))) {
            OS.SendMessage(this.handle, 4097, 0L, -1L);
        }
        OS.DefWindowProc(this.handle, 11, wParam, lParam);
        final long code = this.callWindowProc(this.handle, 11, wParam, lParam);
        if (wParam == 0L && (int)OS.SendMessage(this.handle, 4096, 0L, 0L) == -1) {
            OS.SendMessage(this.handle, 4097, 0L, 16777215L);
        }
        return (code == 0L) ? LRESULT.ZERO : new LRESULT(code);
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        if (this.ignoreResize) {
            return null;
        }
        if (this.hooks(40) || this.hooks(42)) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        if (this.resizeCount != 0) {
            this.wasResized = true;
            return null;
        }
        return super.WM_SIZE(wParam, lParam);
    }
    
    LRESULT WM_SYSCOLORCHANGE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SYSCOLORCHANGE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.findBackgroundControl() == null) {
            this.setBackgroundPixel(this.defaultBackground());
        }
        else {
            final int oldPixel = (int)OS.SendMessage(this.handle, 4096, 0L, 0L);
            if (oldPixel != -1 && this.findImageControl() == null && (this.style & 0x20) != 0x0) {
                this.fixCheckboxImageListColor(true);
            }
        }
        return result;
    }
    
    LRESULT WM_HSCROLL(final long wParam, final long lParam) {
        int oldPos = 0;
        if (this._getLinesVisible()) {
            final SCROLLINFO info = new SCROLLINFO();
            info.cbSize = SCROLLINFO.sizeof;
            info.fMask = 4;
            OS.GetScrollInfo(this.handle, 0, info);
            oldPos = info.nPos;
        }
        long oldHeaderProc = 0L;
        long oldTableProc = 0L;
        final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
        final boolean fixSubclass = this.isOptimizedRedraw();
        if (fixSubclass) {
            oldTableProc = OS.SetWindowLongPtr(this.handle, -4, Table.TableProc);
            oldHeaderProc = OS.SetWindowLongPtr(hwndHeader, -4, Table.HeaderProc);
        }
        boolean fixScroll = false;
        if (OS.LOWORD(wParam) != 8 && this.columnCount > 32) {
            final int rowCount = (int)OS.SendMessage(this.handle, 4136, 0L, 0L);
            if (rowCount > 16) {
                fixScroll = (this.getDrawing() && OS.IsWindowVisible(this.handle));
            }
        }
        if (fixScroll) {
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
        }
        final LRESULT result = super.WM_HSCROLL(wParam, lParam);
        if (fixScroll) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            final int flags = 1157;
            OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
            if (OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                final RECT headerRect = new RECT();
                final RECT rect = new RECT();
                OS.GetClientRect(this.handle, rect);
                final boolean[] visible = new boolean[this.columnCount];
                for (int i = 0; i < this.columnCount; ++i) {
                    visible[i] = true;
                    headerRect.top = i;
                    headerRect.left = 0;
                    if (OS.SendMessage(this.handle, 4152, 0L, headerRect) != 0L) {
                        headerRect.top = rect.top;
                        headerRect.bottom = rect.bottom;
                        visible[i] = OS.IntersectRect(headerRect, rect, headerRect);
                    }
                }
                try {
                    this.columnVisible = visible;
                    OS.UpdateWindow(this.handle);
                }
                finally {
                    this.columnVisible = null;
                }
            }
        }
        if (fixSubclass) {
            OS.SetWindowLongPtr(this.handle, -4, oldTableProc);
            OS.SetWindowLongPtr(hwndHeader, -4, oldHeaderProc);
        }
        if (this._getLinesVisible()) {
            final SCROLLINFO info2 = new SCROLLINFO();
            info2.cbSize = SCROLLINFO.sizeof;
            info2.fMask = 4;
            OS.GetScrollInfo(this.handle, 0, info2);
            final int newPos = info2.nPos;
            if (newPos < oldPos) {
                final RECT rect = new RECT();
                OS.GetClientRect(this.handle, rect);
                rect.right = oldPos - newPos + 1;
                OS.InvalidateRect(this.handle, rect, true);
            }
        }
        return result;
    }
    
    LRESULT WM_VSCROLL(final long wParam, final long lParam) {
        long oldHeaderProc = 0L;
        long oldTableProc = 0L;
        final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
        final boolean fixSubclass = this.isOptimizedRedraw();
        if (fixSubclass) {
            oldTableProc = OS.SetWindowLongPtr(this.handle, -4, Table.TableProc);
            oldHeaderProc = OS.SetWindowLongPtr(hwndHeader, -4, Table.HeaderProc);
        }
        boolean fixScroll = false;
        if (OS.LOWORD(wParam) != 8 && this.columnCount > 32) {
            final int rowCount = (int)OS.SendMessage(this.handle, 4136, 0L, 0L);
            if (rowCount > 16) {
                fixScroll = (this.getDrawing() && OS.IsWindowVisible(this.handle));
            }
        }
        if (fixScroll) {
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
        }
        final LRESULT result = super.WM_VSCROLL(wParam, lParam);
        if (fixScroll) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            final int flags = 1157;
            OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
            if (OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                final RECT headerRect = new RECT();
                final RECT rect = new RECT();
                OS.GetClientRect(this.handle, rect);
                final boolean[] visible = new boolean[this.columnCount];
                for (int i = 0; i < this.columnCount; ++i) {
                    visible[i] = true;
                    headerRect.top = i;
                    headerRect.left = 0;
                    if (OS.SendMessage(this.handle, 4152, 0L, headerRect) != 0L) {
                        headerRect.top = rect.top;
                        headerRect.bottom = rect.bottom;
                        visible[i] = OS.IntersectRect(headerRect, rect, headerRect);
                    }
                }
                try {
                    this.columnVisible = visible;
                    OS.UpdateWindow(this.handle);
                }
                finally {
                    this.columnVisible = null;
                }
            }
        }
        if (fixSubclass) {
            OS.SetWindowLongPtr(this.handle, -4, oldTableProc);
            OS.SetWindowLongPtr(hwndHeader, -4, oldHeaderProc);
        }
        if (this._getLinesVisible()) {
            final int code = OS.LOWORD(wParam);
            switch (code) {
                case 0:
                case 1: {
                    final RECT rect2 = new RECT();
                    OS.GetWindowRect(hwndHeader, rect2);
                    final int headerHeight = rect2.bottom - rect2.top;
                    final RECT clientRect = new RECT();
                    OS.GetClientRect(this.handle, clientRect);
                    final RECT rect4;
                    final RECT rect3 = rect4 = clientRect;
                    rect4.top += headerHeight;
                    final long empty = OS.SendMessage(this.handle, 4160, 0L, 0L);
                    final long oneItem = OS.SendMessage(this.handle, 4160, 1L, 0L);
                    final int itemHeight = OS.HIWORD(oneItem) - OS.HIWORD(empty);
                    if (code == 1) {
                        clientRect.top = clientRect.bottom - itemHeight - 1;
                    }
                    else {
                        clientRect.bottom = clientRect.top + itemHeight + 1;
                    }
                    OS.InvalidateRect(this.handle, clientRect, true);
                    break;
                }
                case 2:
                case 3: {
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                    break;
                }
            }
        }
        return result;
    }
    
    LRESULT wmMeasureChild(final long wParam, final long lParam) {
        final MEASUREITEMSTRUCT struct = new MEASUREITEMSTRUCT();
        OS.MoveMemory(struct, lParam, MEASUREITEMSTRUCT.sizeof);
        if (this.itemHeight == -1) {
            final long empty = OS.SendMessage(this.handle, 4160, 0L, 0L);
            final long oneItem = OS.SendMessage(this.handle, 4160, 1L, 0L);
            struct.itemHeight = OS.HIWORD(oneItem) - OS.HIWORD(empty);
        }
        else {
            struct.itemHeight = this.itemHeight;
        }
        OS.MoveMemory(lParam, struct, MEASUREITEMSTRUCT.sizeof);
        return null;
    }
    
    LRESULT wmNotify(final NMHDR hdr, final long wParam, final long lParam) {
        final long hwndToolTip = OS.SendMessage(this.handle, 4174, 0L, 0L);
        if (hdr.hwndFrom == hwndToolTip) {
            if (hdr.hwndFrom != this.itemToolTipHandle) {
                this.maybeEnableDarkSystemTheme(hdr.hwndFrom);
                this.itemToolTipHandle = hdr.hwndFrom;
            }
            final LRESULT result = this.wmNotifyToolTip(hdr, wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        if (hdr.hwndFrom == this.hwndHeader) {
            final LRESULT result = this.wmNotifyHeader(hdr, wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        return super.wmNotify(hdr, wParam, lParam);
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        Label_1943: {
            switch (hdr.code) {
                case -179: {
                    if ((this.style & 0x10000000) != 0x0) {
                        return new LRESULT(-1L);
                    }
                    break;
                }
                case -115: {
                    if ((this.style & 0x10000000) == 0x0 || this.ignoreSelect) {
                        break;
                    }
                    final NMLVODSTATECHANGE lpStateChange = new NMLVODSTATECHANGE();
                    OS.MoveMemory(lpStateChange, lParam, NMLVODSTATECHANGE.sizeof);
                    final boolean oldSelected = (lpStateChange.uOldState & 0x2) != 0x0;
                    final boolean newSelected = (lpStateChange.uNewState & 0x2) != 0x0;
                    if (oldSelected != newSelected) {
                        this.wasSelected = true;
                        break;
                    }
                    break;
                }
                case -177: {
                    final NMLVDISPINFO plvfi = new NMLVDISPINFO();
                    OS.MoveMemory(plvfi, lParam, NMLVDISPINFO.sizeof);
                    if (this.columnVisible != null && !this.columnVisible[plvfi.iSubItem]) {
                        break;
                    }
                    final TableItem item = this._getItem(plvfi.iItem);
                    if (item == null) {
                        break;
                    }
                    if (this.ignoreShrink) {
                        if (OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                            final RECT rect = new RECT();
                            rect.left = 0;
                            this.ignoreCustomDraw = true;
                            final long code = OS.SendMessage(this.handle, 4110, (long)plvfi.iItem, rect);
                            this.ignoreCustomDraw = false;
                            if (code != 0L) {
                                OS.InvalidateRect(this.handle, rect, true);
                                break;
                            }
                            break;
                        }
                        else if ((this.style & 0x10000000) != 0x0 && !item.cached) {
                            OS.SendMessage(this.handle, 4117, (long)plvfi.iItem, (long)plvfi.iItem);
                            break;
                        }
                    }
                    if (!item.cached) {
                        if ((this.style & 0x10000000) != 0x0) {
                            this.lastIndexOf = plvfi.iItem;
                            if (!this.checkData(item, this.lastIndexOf, false)) {
                                break;
                            }
                            final TableItem newItem = this.fixScrollWidth ? null : item;
                            if (this.setScrollWidth(newItem, true)) {
                                OS.InvalidateRect(this.handle, (RECT)null, true);
                            }
                        }
                        item.cached = true;
                    }
                    if ((plvfi.mask & 0x1) != 0x0) {
                        String string = null;
                        if (plvfi.iSubItem == 0) {
                            string = item.text;
                        }
                        else {
                            final String[] strings = item.strings;
                            if (strings != null && plvfi.iSubItem < strings.length) {
                                string = strings[plvfi.iSubItem];
                            }
                        }
                        if (string != null) {
                            int length = Math.min(string.length(), Math.max(0, plvfi.cchTextMax - 1));
                            if (!this.tipRequested && plvfi.iSubItem == 0 && length == 0) {
                                string = " ";
                                length = 1;
                            }
                            if (length > 1 && (this.state & 0x400000) != 0x0) {
                                switch (BidiUtil.resolveTextDirection(string)) {
                                    case 33554432: {
                                        string = "\u202a" + string;
                                        ++length;
                                        break;
                                    }
                                    case 67108864: {
                                        string = "\u202b" + string;
                                        ++length;
                                        break;
                                    }
                                }
                            }
                            char[] buffer = this.display.tableBuffer;
                            if (buffer == null || plvfi.cchTextMax > buffer.length) {
                                final Display display = this.display;
                                final char[] tableBuffer = new char[plvfi.cchTextMax];
                                display.tableBuffer = tableBuffer;
                                buffer = tableBuffer;
                            }
                            string.getChars(0, length, buffer, 0);
                            if (this.tipRequested) {
                                int shift = 0;
                                for (int i = 0; i < length; ++i) {
                                    switch (buffer[i]) {
                                        case '\n':
                                        case '\r': {
                                            ++shift;
                                            break;
                                        }
                                        default: {
                                            if (shift != 0) {
                                                buffer[i - shift] = buffer[i];
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                }
                                length -= shift;
                            }
                            buffer[length++] = '\0';
                            OS.MoveMemory(plvfi.pszText, buffer, length * 2);
                        }
                    }
                    boolean move = false;
                    if ((plvfi.mask & 0x2) != 0x0) {
                        Image image = null;
                        if (plvfi.iSubItem == 0) {
                            image = item.image;
                        }
                        else {
                            final Image[] images = item.images;
                            if (images != null && plvfi.iSubItem < images.length) {
                                image = images[plvfi.iSubItem];
                            }
                        }
                        if (image != null) {
                            plvfi.iImage = this.imageIndex(image, plvfi.iSubItem);
                            move = true;
                        }
                    }
                    if ((plvfi.mask & 0x8) != 0x0 && plvfi.iSubItem == 0) {
                        int state = 1;
                        if (item.checked) {
                            ++state;
                        }
                        if (item.grayed) {
                            state += 2;
                        }
                        if (!OS.IsWindowEnabled(this.handle)) {
                            state += 4;
                        }
                        plvfi.state = state << 12;
                        plvfi.stateMask = 61440;
                        move = true;
                    }
                    if ((plvfi.mask & 0x10) != 0x0 && plvfi.iSubItem == 0) {
                        plvfi.iIndent = item.imageIndent;
                        move = true;
                    }
                    if (move) {
                        OS.MoveMemory(lParam, plvfi, NMLVDISPINFO.sizeof);
                        break;
                    }
                    break;
                }
                case -12: {
                    final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                    if (hdr.hwndFrom == hwndHeader) {
                        break;
                    }
                    if (!this.customDraw && this.findImageControl() == null && OS.IsWindowEnabled(this.handle)) {
                        if (!this.explorerTheme) {
                            break;
                        }
                        if (this.columnCount != 0) {
                            break;
                        }
                    }
                    final NMLVCUSTOMDRAW nmcd = new NMLVCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMLVCUSTOMDRAW.sizeof);
                    switch (nmcd.dwDrawStage) {
                        case 1: {
                            return this.CDDS_PREPAINT(nmcd, wParam, lParam);
                        }
                        case 65537: {
                            return this.CDDS_ITEMPREPAINT(nmcd, wParam, lParam);
                        }
                        case 65538: {
                            return this.CDDS_ITEMPOSTPAINT(nmcd, wParam, lParam);
                        }
                        case 196609: {
                            return this.CDDS_SUBITEMPREPAINT(nmcd, wParam, lParam);
                        }
                        case 196610: {
                            return this.CDDS_SUBITEMPOSTPAINT(nmcd, wParam, lParam);
                        }
                        case 2: {
                            return this.CDDS_POSTPAINT(nmcd, wParam, lParam);
                        }
                        default: {
                            break Label_1943;
                        }
                    }
                    break;
                }
                case -156: {
                    if ((this.style & 0x4) != 0x0) {
                        return LRESULT.ONE;
                    }
                    if (this.hooks(3) || this.hooks(4)) {
                        return LRESULT.ONE;
                    }
                    if ((this.style & 0x4000000) != 0x0 && this.findImageControl() != null) {
                        return LRESULT.ONE;
                    }
                    break;
                }
                case -111:
                case -109: {
                    if (OS.GetKeyState(1) >= 0) {
                        break;
                    }
                    this.dragStarted = true;
                    if (hdr.code == -109) {
                        final int pos = OS.GetMessagePos();
                        final POINT pt = new POINT();
                        OS.POINTSTOPOINT(pt, (long)pos);
                        OS.ScreenToClient(this.handle, pt);
                        this.sendDragEvent(1, pt.x, pt.y);
                        break;
                    }
                    break;
                }
                case -108: {
                    final NMLISTVIEW pnmlv = new NMLISTVIEW();
                    OS.MoveMemory(pnmlv, lParam, NMLISTVIEW.sizeof);
                    final TableColumn column = this.columns[pnmlv.iSubItem];
                    if (column != null) {
                        column.sendSelectionEvent(13);
                        break;
                    }
                    break;
                }
                case -114: {
                    if (this.ignoreActivate) {
                        break;
                    }
                    final NMLISTVIEW pnmlv = new NMLISTVIEW();
                    OS.MoveMemory(pnmlv, lParam, NMLISTVIEW.sizeof);
                    if (pnmlv.iItem != -1) {
                        final Event event = new Event();
                        event.item = (Widget)this._getItem(pnmlv.iItem);
                        this.sendSelectionEvent(14, event, false);
                        break;
                    }
                    break;
                }
                case -101: {
                    if (this.fullRowSelect) {
                        this.fullRowSelect = false;
                        OS.DefWindowProc(this.handle, 11, 1L, 0L);
                        OS.SendMessage(this.handle, 4150, 32L, 0L);
                    }
                    if (!this.ignoreSelect) {
                        final NMLISTVIEW pnmlv = new NMLISTVIEW();
                        OS.MoveMemory(pnmlv, lParam, NMLISTVIEW.sizeof);
                        if ((pnmlv.uChanged & 0x8) != 0x0) {
                            if (pnmlv.iItem == -1) {
                                this.wasSelected = true;
                            }
                            else {
                                final boolean oldSelected = (pnmlv.uOldState & 0x2) != 0x0;
                                final boolean newSelected = (pnmlv.uNewState & 0x2) != 0x0;
                                if (oldSelected != newSelected) {
                                    this.wasSelected = true;
                                }
                            }
                        }
                    }
                    if (!this.hooks(40) && !this.hooks(42)) {
                        break;
                    }
                    final long hwndHeader = OS.SendMessage(this.handle, 4127, 0L, 0L);
                    final int count = (int)OS.SendMessage(hwndHeader, 4608, 0L, 0L);
                    if (count == 0) {
                        break;
                    }
                    this.forceResize();
                    final RECT rect2 = new RECT();
                    OS.GetClientRect(this.handle, rect2);
                    final NMLISTVIEW pnmlv2 = new NMLISTVIEW();
                    OS.MoveMemory(pnmlv2, lParam, NMLISTVIEW.sizeof);
                    if (pnmlv2.iItem != -1) {
                        final RECT itemRect = new RECT();
                        itemRect.left = 0;
                        this.ignoreCustomDraw = true;
                        OS.SendMessage(this.handle, 4110, (long)pnmlv2.iItem, itemRect);
                        this.ignoreCustomDraw = false;
                        final RECT headerRect = new RECT();
                        final int index = (int)OS.SendMessage(hwndHeader, 4623, (long)(count - 1), 0L);
                        OS.SendMessage(hwndHeader, 4615, (long)index, headerRect);
                        OS.MapWindowPoints(hwndHeader, this.handle, headerRect, 2);
                        rect2.left = headerRect.right;
                        rect2.top = itemRect.top;
                        rect2.bottom = itemRect.bottom;
                        OS.InvalidateRect(this.handle, rect2, true);
                        break;
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(hdr, wParam, lParam);
    }
    
    LRESULT wmNotifyHeader(final NMHDR hdr, final long wParam, final long lParam) {
        Label_2355: {
            switch (hdr.code) {
                case -326:
                case -325: {
                    if (this.columnCount == 0) {
                        return LRESULT.ONE;
                    }
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    final TableColumn column = this.columns[phdn.iItem];
                    if (column != null && !column.getResizable()) {
                        return LRESULT.ONE;
                    }
                    this.ignoreColumnMove = true;
                    if (hdr.code == -325 && column != null && this.hooks(41)) {
                        column.pack();
                        return LRESULT.ONE;
                    }
                    break;
                }
                case -12: {
                    final NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMCUSTOMDRAW.sizeof);
                    switch (nmcd.dwDrawStage) {
                        case 1: {
                            return new LRESULT(this.customHeaderDrawing() ? 48L : 0L);
                        }
                        case 65537: {
                            final RECT rect = new RECT();
                            OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                            int pixel = this.getHeaderBackgroundPixel();
                            if ((nmcd.uItemState & 0x1) != 0x0) {
                                pixel = this.getDifferentColor(pixel);
                            }
                            final long brush = OS.CreateSolidBrush(pixel);
                            OS.FillRect(nmcd.hdc, rect, brush);
                            OS.DeleteObject(brush);
                            return new LRESULT(4L);
                        }
                        case 2: {
                            final POINT cursorPos = new POINT();
                            OS.GetCursorPos(cursorPos);
                            OS.MapWindowPoints(0L, this.hwndHeader, cursorPos, 1);
                            int highlightedHeaderDividerX = -1;
                            int lastColumnRight = -1;
                            final RECT[] rects = new RECT[this.columnCount];
                            for (int i = 0; i < this.columnCount; ++i) {
                                rects[i] = new RECT();
                                OS.SendMessage(this.hwndHeader, 4615, (long)i, rects[i]);
                                if (rects[i].right > lastColumnRight) {
                                    lastColumnRight = rects[i].right;
                                }
                                if (this.columns[i] == this.sortColumn && this.sortDirection != 0) {
                                    final long pen = OS.CreatePen(0, 1, this.getHeaderForegroundPixel());
                                    final long oldPen = OS.SelectObject(nmcd.hdc, pen);
                                    final int center = rects[i].left + (rects[i].right - rects[i].left) / 2;
                                    final int leg = DPIUtil.autoScaleUpUsingNativeDPI(3);
                                    if (this.sortDirection == 128) {
                                        OS.Polyline(nmcd.hdc, new int[] { center - leg, 1 + leg, center + 1, 0 }, 2);
                                        OS.Polyline(nmcd.hdc, new int[] { center + leg, 1 + leg, center - 1, 0 }, 2);
                                    }
                                    else if (this.sortDirection == 1024) {
                                        OS.Polyline(nmcd.hdc, new int[] { center - leg, 1, center + 1, 1 + leg + 1 }, 2);
                                        OS.Polyline(nmcd.hdc, new int[] { center + leg, 1, center - 1, 1 + leg + 1 }, 2);
                                    }
                                    OS.SelectObject(nmcd.hdc, oldPen);
                                    OS.DeleteObject(pen);
                                }
                                final int alignmentCorrection = this._getLinesVisible() ? 0 : 1;
                                final int lineColor = (this.display.tableHeaderLinePixel != -1) ? this.display.tableHeaderLinePixel : OS.GetSysColor(15);
                                final long pen2 = OS.CreatePen(0, this.getGridLineWidthInPixels(), lineColor);
                                final long oldPen2 = OS.SelectObject(nmcd.hdc, pen2);
                                OS.Polyline(nmcd.hdc, new int[] { rects[i].right - alignmentCorrection, rects[i].top, rects[i].right - alignmentCorrection, rects[i].bottom }, 2);
                                if (i == 0) {
                                    OS.Polyline(nmcd.hdc, new int[] { nmcd.left, nmcd.bottom - 1, nmcd.right + 1, nmcd.bottom - 1 }, 2);
                                }
                                OS.SelectObject(nmcd.hdc, oldPen2);
                                OS.DeleteObject(pen2);
                                if (this.headerItemDragging && highlightedHeaderDividerX == -1) {
                                    final int distanceToLeftBorder = cursorPos.x - rects[i].left;
                                    final int distanceToRightBorder = rects[i].right - cursorPos.x;
                                    if (distanceToLeftBorder >= 0 && distanceToRightBorder >= 0) {
                                        highlightedHeaderDividerX = ((distanceToLeftBorder <= distanceToRightBorder) ? (rects[i].left - 1) : rects[i].right);
                                    }
                                }
                                int x = rects[i].left + 4 + 2;
                                if (this.columns[i].image != null) {
                                    final GCData data = new GCData();
                                    data.device = (Device)this.display;
                                    final GC gc = GC.win32_new(nmcd.hdc, data);
                                    final int y = Math.max(0, (nmcd.bottom - this.columns[i].image.getBoundsInPixels().height) / 2);
                                    gc.drawImage(this.columns[i].image, DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(y));
                                    x += this.columns[i].image.getBoundsInPixels().width + 12;
                                    gc.dispose();
                                }
                                if (this.columns[i].text != null) {
                                    int flags = 2084;
                                    if ((this.columns[i].style & 0x1000000) != 0x0) {
                                        flags |= 0x1;
                                    }
                                    if ((this.columns[i].style & 0x20000) != 0x0) {
                                        flags |= 0x2;
                                    }
                                    final char[] buffer = this.columns[i].text.toCharArray();
                                    OS.SetBkMode(nmcd.hdc, 1);
                                    OS.SetTextColor(nmcd.hdc, this.getHeaderForegroundPixel());
                                    final RECT textRect = new RECT();
                                    textRect.left = x;
                                    textRect.top = rects[i].top;
                                    textRect.right = rects[i].right - (x - rects[i].left);
                                    textRect.bottom = rects[i].bottom;
                                    OS.DrawText(nmcd.hdc, buffer, buffer.length, textRect, flags);
                                }
                            }
                            if (lastColumnRight < nmcd.right) {
                                final RECT rect2 = new RECT();
                                lastColumnRight += (this._getLinesVisible() ? 1 : 0);
                                OS.SetRect(rect2, lastColumnRight, nmcd.top, nmcd.right, nmcd.bottom - 1);
                                final long brush2 = OS.CreateSolidBrush(this.getHeaderBackgroundPixel());
                                OS.FillRect(nmcd.hdc, rect2, brush2);
                                OS.DeleteObject(brush2);
                            }
                            if (highlightedHeaderDividerX != -1) {
                                final long pen3 = OS.CreatePen(0, 4, OS.GetSysColor(13));
                                final long oldPen3 = OS.SelectObject(nmcd.hdc, pen3);
                                OS.Polyline(nmcd.hdc, new int[] { highlightedHeaderDividerX, nmcd.top, highlightedHeaderDividerX, nmcd.bottom }, 2);
                                OS.SelectObject(nmcd.hdc, oldPen3);
                                OS.DeleteObject(pen3);
                            }
                            return new LRESULT(0L);
                        }
                        default: {
                            break Label_2355;
                        }
                    }
                    break;
                }
                case -16: {
                    if (!this.ignoreColumnMove) {
                        for (int j = 0; j < this.columnCount; ++j) {
                            final TableColumn column = this.columns[j];
                            column.updateToolTip(j);
                        }
                    }
                    this.ignoreColumnMove = false;
                    break;
                }
                case -310: {
                    if (this.ignoreColumnMove) {
                        return LRESULT.ONE;
                    }
                    final int bits = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
                    if ((bits & 0x10) == 0x0) {
                        break;
                    }
                    if (this.columnCount == 0) {
                        return LRESULT.ONE;
                    }
                    final NMHEADER phdn2 = new NMHEADER();
                    OS.MoveMemory(phdn2, lParam, NMHEADER.sizeof);
                    if (phdn2.iItem != -1) {
                        final TableColumn column2 = this.columns[phdn2.iItem];
                        if (column2 != null && !column2.getMoveable()) {
                            this.ignoreColumnMove = true;
                            return LRESULT.ONE;
                        }
                    }
                    this.headerItemDragging = true;
                    break;
                }
                case -311: {
                    this.headerItemDragging = false;
                    final int bits = (int)OS.SendMessage(this.handle, 4151, 0L, 0L);
                    if ((bits & 0x10) == 0x0) {
                        break;
                    }
                    final NMHEADER phdn2 = new NMHEADER();
                    OS.MoveMemory(phdn2, lParam, NMHEADER.sizeof);
                    if (phdn2.iItem == -1 || phdn2.pitem == 0L) {
                        break;
                    }
                    final HDITEM pitem = new HDITEM();
                    OS.MoveMemory(pitem, phdn2.pitem, HDITEM.sizeof);
                    if ((pitem.mask & 0x80) != 0x0 && pitem.iOrder != -1 && this.columnCount != 0) {
                        final int[] order = new int[this.columnCount];
                        OS.SendMessage(this.handle, 4155, (long)this.columnCount, order);
                        int index;
                        for (index = 0; index < order.length && order[index] != phdn2.iItem; ++index) {}
                        if (index == order.length) {
                            index = 0;
                        }
                        if (index != pitem.iOrder) {
                            final int start = Math.min(index, pitem.iOrder);
                            final int end = Math.max(index, pitem.iOrder);
                            this.ignoreColumnMove = false;
                            for (int k = start; k <= end; ++k) {
                                final TableColumn column3 = this.columns[order[k]];
                                if (!column3.isDisposed()) {
                                    column3.postEvent(10);
                                }
                            }
                        }
                        break;
                    }
                    break;
                }
                case -321: {
                    final int width = (int)OS.SendMessage(this.handle, 4125, 0L, 0L);
                    if (this.lastWidth == 0 && width > 0 && this._getLinesVisible()) {
                        final RECT rect = new RECT();
                        OS.GetClientRect(this.handle, rect);
                        rect.right = rect.left + width;
                        OS.InvalidateRect(this.handle, rect, true);
                    }
                    this.lastWidth = width;
                    if (this.ignoreColumnResize) {
                        break;
                    }
                    final NMHEADER phdn2 = new NMHEADER();
                    OS.MoveMemory(phdn2, lParam, NMHEADER.sizeof);
                    if (phdn2.pitem != 0L) {
                        final HDITEM pitem = new HDITEM();
                        OS.MoveMemory(pitem, phdn2.pitem, HDITEM.sizeof);
                        if ((pitem.mask & 0x1) != 0x0) {
                            final TableColumn column4 = this.columns[phdn2.iItem];
                            if (column4 != null) {
                                column4.updateToolTip(phdn2.iItem);
                                column4.sendEvent(11);
                                if (this.isDisposed()) {
                                    return LRESULT.ZERO;
                                }
                                final TableColumn[] newColumns = new TableColumn[this.columnCount];
                                System.arraycopy(this.columns, 0, newColumns, 0, this.columnCount);
                                final int[] order2 = new int[this.columnCount];
                                OS.SendMessage(this.handle, 4155, (long)this.columnCount, order2);
                                boolean moved = false;
                                for (int k = 0; k < this.columnCount; ++k) {
                                    final TableColumn nextColumn = newColumns[order2[k]];
                                    if (moved && !nextColumn.isDisposed()) {
                                        nextColumn.updateToolTip(order2[k]);
                                        nextColumn.sendEvent(10);
                                    }
                                    if (nextColumn == column4) {
                                        moved = true;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    break;
                }
                case -323: {
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    final TableColumn column = this.columns[phdn.iItem];
                    if (column != null) {
                        column.sendSelectionEvent(14);
                        break;
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    LRESULT wmNotifyToolTip(final NMHDR hdr, final long wParam, final long lParam) {
        switch (hdr.code) {
            case -12: {
                if (this.toolTipText != null) {
                    break;
                }
                if (this.isCustomToolTip()) {
                    final NMTTCUSTOMDRAW nmcd = new NMTTCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMTTCUSTOMDRAW.sizeof);
                    return this.wmNotifyToolTip(nmcd, lParam);
                }
                break;
            }
            case -530:
            case -521: {
                final LRESULT result = super.wmNotify(hdr, wParam, lParam);
                if (result != null) {
                    return result;
                }
                if (hdr.code != -521) {
                    this.tipRequested = true;
                }
                final long code = this.callWindowProc(this.handle, 78, wParam, lParam);
                if (hdr.code != -521) {
                    this.tipRequested = false;
                }
                if (this.toolTipText != null) {
                    break;
                }
                if (this.isCustomToolTip()) {
                    final LVHITTESTINFO pinfo = new LVHITTESTINFO();
                    final int pos = OS.GetMessagePos();
                    final POINT pt = new POINT();
                    OS.POINTSTOPOINT(pt, (long)pos);
                    OS.ScreenToClient(this.handle, pt);
                    pinfo.x = pt.x;
                    pinfo.y = pt.y;
                    if (OS.SendMessage(this.handle, 4153, 0L, pinfo) >= 0L) {
                        final TableItem item = this._getItem(pinfo.iItem);
                        final long hDC = OS.GetDC(this.handle);
                        long oldFont = 0L;
                        final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
                        if (newFont != 0L) {
                            oldFont = OS.SelectObject(hDC, newFont);
                        }
                        long hFont = item.fontHandle(pinfo.iSubItem);
                        if (hFont != -1L) {
                            hFont = OS.SelectObject(hDC, hFont);
                        }
                        final Event event = this.sendMeasureItemEvent(item, pinfo.iItem, pinfo.iSubItem, hDC);
                        if (!this.isDisposed() && !item.isDisposed()) {
                            final RECT itemRect = new RECT();
                            final Rectangle boundsInPixels = event.getBoundsInPixels();
                            OS.SetRect(itemRect, boundsInPixels.x, boundsInPixels.y, boundsInPixels.x + boundsInPixels.width, boundsInPixels.y + boundsInPixels.height);
                            if (hdr.code == -521) {
                                final RECT toolRect = this.toolTipRect(itemRect);
                                OS.MapWindowPoints(this.handle, 0L, toolRect, 2);
                                final long hwndToolTip = OS.SendMessage(this.handle, 4174, 0L, 0L);
                                final int flags = 20;
                                final int width = toolRect.right - toolRect.left;
                                final int height = toolRect.bottom - toolRect.top;
                                OS.SetWindowPos(hwndToolTip, 0L, toolRect.left, toolRect.top, width, height, 20);
                            }
                            else {
                                final NMTTDISPINFO lpnmtdi = new NMTTDISPINFO();
                                OS.MoveMemory(lpnmtdi, lParam, NMTTDISPINFO.sizeof);
                                if (lpnmtdi.lpszText != 0L) {
                                    OS.MoveMemory(lpnmtdi.lpszText, new char[1], 2);
                                    OS.MoveMemory(lParam, lpnmtdi, NMTTDISPINFO.sizeof);
                                }
                                final RECT cellRect = item.getBounds(pinfo.iItem, pinfo.iSubItem, true, true, true, true, hDC);
                                final RECT clientRect = new RECT();
                                OS.GetClientRect(this.handle, clientRect);
                                if (itemRect.right > cellRect.right || itemRect.right > clientRect.right) {
                                    final String string = " ";
                                    if (" " != null) {
                                        final Shell shell = this.getShell();
                                        final char[] chars = new char[" ".length() + 1];
                                        " ".getChars(0, " ".length(), chars, 0);
                                        shell.setToolTipText(lpnmtdi, chars);
                                        OS.MoveMemory(lParam, lpnmtdi, NMTTDISPINFO.sizeof);
                                    }
                                }
                            }
                        }
                        if (hFont != -1L) {
                            hFont = OS.SelectObject(hDC, hFont);
                        }
                        if (newFont != 0L) {
                            OS.SelectObject(hDC, oldFont);
                        }
                        OS.ReleaseDC(this.handle, hDC);
                    }
                }
                return new LRESULT(code);
            }
        }
        return null;
    }
    
    LRESULT wmNotifyToolTip(final NMTTCUSTOMDRAW nmcd, final long lParam) {
        switch (nmcd.dwDrawStage) {
            case 1: {
                if (this.isCustomToolTip()) {
                    return new LRESULT(18L);
                }
                break;
            }
            case 2: {
                final LVHITTESTINFO pinfo = new LVHITTESTINFO();
                final int pos = OS.GetMessagePos();
                final POINT pt = new POINT();
                OS.POINTSTOPOINT(pt, (long)pos);
                OS.ScreenToClient(this.handle, pt);
                pinfo.x = pt.x;
                pinfo.y = pt.y;
                if (OS.SendMessage(this.handle, 4153, 0L, pinfo) >= 0L) {
                    final TableItem item = this._getItem(pinfo.iItem);
                    final long hDC = OS.GetDC(this.handle);
                    long hFont = item.fontHandle(pinfo.iSubItem);
                    if (hFont == -1L) {
                        hFont = OS.SendMessage(this.handle, 49, 0L, 0L);
                    }
                    final long oldFont = OS.SelectObject(hDC, hFont);
                    boolean drawForeground = true;
                    final RECT cellRect = item.getBounds(pinfo.iItem, pinfo.iSubItem, true, true, false, false, hDC);
                    if (this.hooks(40)) {
                        final Event event = this.sendEraseItemEvent(item, nmcd, pinfo.iSubItem, cellRect);
                        if (this.isDisposed()) {
                            break;
                        }
                        if (item.isDisposed()) {
                            break;
                        }
                        drawForeground = (event.doit && (event.detail & 0x10) != 0x0);
                    }
                    if (drawForeground) {
                        final int nSavedDC = OS.SaveDC(nmcd.hdc);
                        final int gridWidth = this.getLinesVisible() ? 1 : 0;
                        final RECT insetRect = this.toolTipInset(cellRect);
                        OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, (POINT)null);
                        final GCData data = new GCData();
                        data.device = (Device)this.display;
                        data.foreground = OS.GetTextColor(nmcd.hdc);
                        data.background = OS.GetBkColor(nmcd.hdc);
                        data.font = Font.win32_new((Device)this.display, hFont);
                        final GC gc = GC.win32_new(nmcd.hdc, data);
                        int x = cellRect.left;
                        if (pinfo.iSubItem != 0) {
                            x -= gridWidth;
                        }
                        final Image image = item.getImage(pinfo.iSubItem);
                        if (image != null) {
                            Rectangle rect = image.getBoundsInPixels();
                            final RECT imageRect = item.getBounds(pinfo.iItem, pinfo.iSubItem, false, true, false, false, hDC);
                            final Point size = (this.imageList == null) ? new Point(rect.width, rect.height) : this.imageList.getImageSize();
                            final int y = imageRect.top + Math.max(0, (imageRect.bottom - imageRect.top - size.y) / 2);
                            rect = DPIUtil.autoScaleDown(rect);
                            gc.drawImage(image, rect.x, rect.y, rect.width, rect.height, DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(y), DPIUtil.autoScaleDown(size.x), DPIUtil.autoScaleDown(size.y));
                            x += size.x + 4 + ((pinfo.iSubItem == 0) ? -2 : 4);
                        }
                        else {
                            x += 6;
                        }
                        final String string = item.getText(pinfo.iSubItem);
                        if (string != null) {
                            int flags = 2084;
                            final TableColumn column = (this.columns != null) ? this.columns[pinfo.iSubItem] : null;
                            if (column != null) {
                                if ((column.style & 0x1000000) != 0x0) {
                                    flags |= 0x1;
                                }
                                if ((column.style & 0x20000) != 0x0) {
                                    flags |= 0x2;
                                }
                            }
                            final char[] buffer = string.toCharArray();
                            final RECT textRect = new RECT();
                            OS.SetRect(textRect, x, cellRect.top, cellRect.right, cellRect.bottom);
                            OS.DrawText(nmcd.hdc, buffer, buffer.length, textRect, flags);
                        }
                        gc.dispose();
                        OS.RestoreDC(nmcd.hdc, nSavedDC);
                    }
                    if (this.hooks(42)) {
                        final RECT itemRect = item.getBounds(pinfo.iItem, pinfo.iSubItem, true, true, false, false, hDC);
                        this.sendPaintItemEvent(item, nmcd, pinfo.iSubItem, itemRect);
                    }
                    OS.SelectObject(hDC, oldFont);
                    OS.ReleaseDC(this.handle, hDC);
                    break;
                }
                break;
            }
        }
        return null;
    }
    
    static {
        Table.COMPRESS_ITEMS = true;
        TableClass = new TCHAR(0, "SysListView32", true);
        HeaderClass = new TCHAR(0, "SysHeader32", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Table.TableClass, lpWndClass);
        TableProc = lpWndClass.lpfnWndProc;
        OS.GetClassInfo(0L, Table.HeaderClass, lpWndClass);
        Table.HeaderProc = lpWndClass.lpfnWndProc;
    }
}
