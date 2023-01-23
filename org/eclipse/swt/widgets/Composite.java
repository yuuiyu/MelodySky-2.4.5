//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Composite extends Scrollable
{
    Layout layout;
    WINDOWPOS[] lpwp;
    Control[] tabList;
    int layoutCount;
    int backgroundMode;
    static final int TOOLTIP_LIMIT = 4096;
    
    Composite() {
    }
    
    public Composite(final Composite parent, final int style) {
        super(parent, style);
    }
    
    Control[] _getChildren() {
        int count = 0;
        long hwndChild = OS.GetWindow(this.handle, 5);
        if (hwndChild == 0L) {
            return new Control[0];
        }
        while (hwndChild != 0L) {
            ++count;
            hwndChild = OS.GetWindow(hwndChild, 2);
        }
        final Control[] children = new Control[count];
        int index = 0;
        for (hwndChild = OS.GetWindow(this.handle, 5); hwndChild != 0L; hwndChild = OS.GetWindow(hwndChild, 2)) {
            final Control control = this.display.getControl(hwndChild);
            if (control != null && control != this) {
                children[index++] = control;
            }
        }
        if (count == index) {
            return children;
        }
        final Control[] newChildren = new Control[index];
        System.arraycopy(children, 0, newChildren, 0, index);
        return newChildren;
    }
    
    Control[] _getTabList() {
        if (this.tabList == null) {
            return this.tabList;
        }
        int count = 0;
        for (final Control element : this.tabList) {
            if (!element.isDisposed()) {
                ++count;
            }
        }
        if (count == this.tabList.length) {
            return this.tabList;
        }
        final Control[] newList = new Control[count];
        int index = 0;
        for (final Control element2 : this.tabList) {
            if (!element2.isDisposed()) {
                newList[index++] = element2;
            }
        }
        return this.tabList = newList;
    }
    
    @Deprecated
    public void changed(final Control[] changed) {
        this.layout(changed, 4);
    }
    
    @Override
    void checkBuffered() {
        if ((this.state & 0x2) == 0x0) {
            super.checkBuffered();
        }
    }
    
    @Override
    void checkComposited() {
        if ((this.state & 0x2) != 0x0 && (this.style & 0x40000000) != 0x0) {
            final long hwndParent = this.parent.handle;
            int bits = OS.GetWindowLong(hwndParent, -20);
            bits |= 0x2000000;
            OS.SetWindowLong(hwndParent, -20, bits);
        }
    }
    
    @Override
    protected void checkSubclass() {
    }
    
    @Override
    Widget[] computeTabList() {
        Widget[] result = super.computeTabList();
        if (result.length == 0) {
            return result;
        }
        final Control[] list;
        final Control[] array2;
        final Control[] array = array2 = (list = ((this.tabList != null) ? this._getTabList() : this._getChildren()));
        for (final Control child : array2) {
            final Widget[] childList = child.computeTabList();
            if (childList.length != 0) {
                final Widget[] newResult = new Widget[result.length + childList.length];
                System.arraycopy(result, 0, newResult, 0, result.length);
                System.arraycopy(childList, 0, newResult, result.length, childList.length);
                result = newResult;
            }
        }
        return result;
    }
    
    @Override
    Point computeSizeInPixels(final int wHint, final int hHint, boolean changed) {
        this.display.runSkin();
        Point size;
        if (this.layout != null) {
            if (wHint == -1 || hHint == -1) {
                changed |= ((this.state & 0x40) != 0x0);
                this.state &= 0xFFFFFFBF;
                size = DPIUtil.autoScaleUp(this.layout.computeSize(this, DPIUtil.autoScaleDown(wHint), DPIUtil.autoScaleDown(hHint), changed));
            }
            else {
                size = new Point(wHint, hHint);
            }
        }
        else {
            size = this.minimumSize(wHint, hHint, changed);
            if (size.x == 0) {
                size.x = 64;
            }
            if (size.y == 0) {
                size.y = 64;
            }
        }
        if (wHint != -1) {
            size.x = wHint;
        }
        if (hHint != -1) {
            size.y = hHint;
        }
        final Rectangle trim = DPIUtil.autoScaleUp(this.computeTrim(0, 0, DPIUtil.autoScaleDown(size.x), DPIUtil.autoScaleDown(size.y)));
        return new Point(trim.width, trim.height);
    }
    
    void copyArea(final GC gc, int x, int y, final int width, final int height) {
        this.checkWidget();
        if (gc == null) {
            this.error(4);
        }
        if (gc.isDisposed()) {
            this.error(5);
        }
        final long hDC = gc.handle;
        final int nSavedDC = OS.SaveDC(hDC);
        OS.IntersectClipRect(hDC, 0, 0, width, height);
        final POINT lpPoint = new POINT();
        final long hwndParent = OS.GetParent(this.handle);
        OS.MapWindowPoints(this.handle, hwndParent, lpPoint, 1);
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final POINT lpPoint2 = new POINT();
        final POINT lpPoint3 = new POINT();
        x += lpPoint.x - rect.left;
        y += lpPoint.y - rect.top;
        OS.SetWindowOrgEx(hDC, x, y, lpPoint2);
        OS.SetBrushOrgEx(hDC, x, y, lpPoint3);
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x10000000) == 0x0) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
        }
        OS.RedrawWindow(this.handle, (RECT)null, 0L, 384);
        OS.PrintWindow(this.handle, hDC, 0);
        if ((bits & 0x10000000) == 0x0) {
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
        }
        OS.RestoreDC(hDC, nSavedDC);
    }
    
    @Override
    void createHandle() {
        super.createHandle();
        this.state |= 0x2;
        if ((this.style & 0x300) == 0x0 || this.findThemeControl() == this.parent) {
            this.state |= 0x100;
        }
        if ((this.style & 0x40000000) != 0x0) {
            int bits = OS.GetWindowLong(this.handle, -20);
            bits |= 0x20;
            OS.SetWindowLong(this.handle, -20, bits);
        }
    }
    
    @Override
    int applyThemeBackground() {
        return (this.backgroundAlpha == 0 || (this.style & 0x300) == 0x0 || this.findThemeControl() == this.parent) ? 1 : 0;
    }
    
    public void drawBackground(final GC gc, int x, int y, int width, int height, int offsetX, int offsetY) {
        this.checkWidget();
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        offsetX = DPIUtil.autoScaleUp(offsetX);
        offsetY = DPIUtil.autoScaleUp(offsetY);
        this.drawBackgroundInPixels(gc, x, y, width, height, offsetX, offsetY);
    }
    
    void drawBackgroundInPixels(final GC gc, final int x, final int y, final int width, final int height, final int offsetX, final int offsetY) {
        if (gc == null) {
            this.error(4);
        }
        if (gc.isDisposed()) {
            this.error(5);
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, x, y, x + width, y + height);
        final long hDC = gc.handle;
        final int pixel = (this.background == -1) ? gc.getBackground().handle : -1;
        this.drawBackground(hDC, rect, pixel, offsetX, offsetY);
    }
    
    Composite findDeferredControl() {
        return (this.layoutCount > 0) ? this : this.parent.findDeferredControl();
    }
    
    @Override
    Menu[] findMenus(final Control control) {
        if (control == this) {
            return new Menu[0];
        }
        Menu[] result = super.findMenus(control);
        for (final Control child : this._getChildren()) {
            final Menu[] menuList = child.findMenus(control);
            if (menuList.length != 0) {
                final Menu[] newResult = new Menu[result.length + menuList.length];
                System.arraycopy(result, 0, newResult, 0, result.length);
                System.arraycopy(menuList, 0, newResult, result.length, menuList.length);
                result = newResult;
            }
        }
        return result;
    }
    
    @Override
    void fixChildren(final Shell newShell, final Shell oldShell, final Decorations newDecorations, final Decorations oldDecorations, final Menu[] menus) {
        super.fixChildren(newShell, oldShell, newDecorations, oldDecorations, menus);
        for (final Control child : this._getChildren()) {
            child.fixChildren(newShell, oldShell, newDecorations, oldDecorations, menus);
        }
    }
    
    void fixTabList(final Control control) {
        if (this.tabList == null) {
            return;
        }
        int count = 0;
        for (final Control element : this.tabList) {
            if (element == control) {
                ++count;
            }
        }
        if (count == 0) {
            return;
        }
        Control[] newList = null;
        final int length = this.tabList.length - count;
        if (length != 0) {
            newList = new Control[length];
            int index = 0;
            for (final Control element2 : this.tabList) {
                if (element2 != control) {
                    newList[index++] = element2;
                }
            }
        }
        this.tabList = newList;
    }
    
    public int getBackgroundMode() {
        this.checkWidget();
        return this.backgroundMode;
    }
    
    public Control[] getChildren() {
        this.checkWidget();
        return this._getChildren();
    }
    
    int getChildrenCount() {
        int count = 0;
        for (long hwndChild = OS.GetWindow(this.handle, 5); hwndChild != 0L; hwndChild = OS.GetWindow(hwndChild, 2)) {
            ++count;
        }
        return count;
    }
    
    public Layout getLayout() {
        this.checkWidget();
        return this.layout;
    }
    
    public Control[] getTabList() {
        this.checkWidget();
        Control[] tabList = this._getTabList();
        if (tabList == null) {
            int count = 0;
            final Control[] list;
            final Control[] array;
            final Control[] getChildren = array = (list = this._getChildren());
            for (final Control element : array) {
                if (element.isTabGroup()) {
                    ++count;
                }
            }
            tabList = new Control[count];
            int index = 0;
            for (final Control element2 : list) {
                if (element2.isTabGroup()) {
                    tabList[index++] = element2;
                }
            }
        }
        return tabList;
    }
    
    boolean hooksKeys() {
        return this.hooks(1) || this.hooks(2);
    }
    
    public boolean getLayoutDeferred() {
        this.checkWidget();
        return this.layoutCount > 0;
    }
    
    public boolean isLayoutDeferred() {
        this.checkWidget();
        return this.findDeferredControl() != null;
    }
    
    public void layout() {
        this.checkWidget();
        this.layout(true);
    }
    
    public void layout(final boolean changed) {
        this.checkWidget();
        if (this.layout == null) {
            return;
        }
        this.layout(changed, false);
    }
    
    public void layout(final boolean changed, final boolean all) {
        this.checkWidget();
        if (this.layout == null && !all) {
            return;
        }
        this.markLayout(changed, all);
        this.updateLayout(all);
    }
    
    public void layout(final Control[] changed) {
        this.checkWidget();
        if (changed == null) {
            this.error(5);
        }
        this.layout(changed, 0);
    }
    
    public void layout(final Control[] changed, final int flags) {
        this.checkWidget();
        if (changed != null) {
            for (final Control control : changed) {
                if (control == null) {
                    this.error(5);
                }
                if (control.isDisposed()) {
                    this.error(5);
                }
                boolean ancestor = false;
                for (Composite composite = control.parent; composite != null; composite = composite.parent) {
                    ancestor = (composite == this);
                    if (ancestor) {
                        break;
                    }
                }
                if (!ancestor) {
                    this.error(32);
                }
            }
            int updateCount = 0;
            Composite[] update = new Composite[16];
            for (final Control element : changed) {
                Control child = element;
                Composite composite2 = child.parent;
                child.markLayout(false, false);
                while (child != this) {
                    if (composite2.layout != null) {
                        final Composite composite6;
                        final Composite composite3 = composite6 = composite2;
                        composite6.state |= 0x20;
                        if (!composite2.layout.flushCache(child)) {
                            final Composite composite7;
                            final Composite composite4 = composite7 = composite2;
                            composite7.state |= 0x40;
                        }
                    }
                    if (updateCount == update.length) {
                        final Composite[] newUpdate = new Composite[update.length + 16];
                        System.arraycopy(update, 0, newUpdate, 0, update.length);
                        update = newUpdate;
                    }
                    final Composite[] array = update;
                    final int n = updateCount++;
                    final Composite composite5 = composite2;
                    array[n] = composite5;
                    child = composite5;
                    composite2 = child.parent;
                }
            }
            if (!this.display.externalEventLoop && (flags & 0x4) != 0x0) {
                this.setLayoutDeferred(true);
                this.display.addLayoutDeferred(this);
            }
            for (int i = updateCount - 1; i >= 0; --i) {
                update[i].updateLayout(false);
            }
        }
        else {
            if (this.layout == null && (flags & 0x1) == 0x0) {
                return;
            }
            this.markLayout((flags & 0x2) != 0x0, (flags & 0x1) != 0x0);
            if (!this.display.externalEventLoop && (flags & 0x4) != 0x0) {
                this.setLayoutDeferred(true);
                this.display.addLayoutDeferred(this);
            }
            this.updateLayout((flags & 0x1) != 0x0);
        }
    }
    
    @Override
    void markLayout(final boolean changed, final boolean all) {
        if (this.layout != null) {
            this.state |= 0x20;
            if (changed) {
                this.state |= 0x40;
            }
        }
        if (all) {
            for (final Control element : this._getChildren()) {
                element.markLayout(changed, all);
            }
        }
    }
    
    Point minimumSize(final int wHint, final int hHint, final boolean changed) {
        final Rectangle clientArea = DPIUtil.autoScaleUp(this.getClientArea());
        int width = 0;
        int height = 0;
        for (final Control element : this._getChildren()) {
            final Rectangle rect = DPIUtil.autoScaleUp(element.getBounds());
            width = Math.max(width, rect.x - clientArea.x + rect.width);
            height = Math.max(height, rect.y - clientArea.y + rect.height);
        }
        return new Point(width, height);
    }
    
    @Override
    boolean redrawChildren() {
        if (!super.redrawChildren()) {
            return false;
        }
        for (final Control element : this._getChildren()) {
            element.redrawChildren();
        }
        return true;
    }
    
    @Override
    void releaseParent() {
        super.releaseParent();
        if ((this.state & 0x2) != 0x0 && (this.style & 0x40000000) != 0x0) {
            final long hwndParent = this.parent.handle;
            for (long hwndChild = OS.GetWindow(hwndParent, 5); hwndChild != 0L; hwndChild = OS.GetWindow(hwndChild, 2)) {
                if (hwndChild != this.handle) {
                    final int bits = OS.GetWindowLong(hwndParent, -20);
                    if ((bits & 0x20) != 0x0) {
                        return;
                    }
                }
            }
            int bits2 = OS.GetWindowLong(hwndParent, -20);
            bits2 &= 0xFDFFFFFF;
            OS.SetWindowLong(hwndParent, -20, bits2);
        }
    }
    
    @Override
    void releaseChildren(final boolean destroy) {
        try (final ExceptionStash exceptions = new ExceptionStash()) {
            for (final Control child : this._getChildren()) {
                if (child != null && !child.isDisposed()) {
                    try {
                        child.release(false);
                    }
                    catch (Error | RuntimeException error) {
                        final Throwable t2;
                        final Throwable ex = t2;
                        exceptions.stash(ex);
                    }
                }
            }
            super.releaseChildren(destroy);
        }
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        if ((this.state & 0x2) != 0x0 && (this.style & 0x1000000) != 0x0) {
            final long hwndChild = OS.GetWindow(this.handle, 5);
            if (hwndChild != 0L) {
                final int threadId = OS.GetWindowThreadProcessId(hwndChild, (int[])null);
                if (threadId != OS.GetCurrentThreadId()) {
                    OS.ShowWindow(hwndChild, 0);
                    OS.SetParent(hwndChild, 0L);
                }
            }
        }
        this.layout = null;
        this.tabList = null;
        this.lpwp = null;
    }
    
    void removeControl(final Control control) {
        this.fixTabList(control);
        this.resizeChildren();
    }
    
    @Override
    void reskinChildren(final int flags) {
        super.reskinChildren(flags);
        for (final Control child : this._getChildren()) {
            if (child != null) {
                child.reskin(flags);
            }
        }
    }
    
    void resizeChildren() {
        if (this.lpwp == null) {
            return;
        }
        do {
            final WINDOWPOS[] currentLpwp = this.lpwp;
            this.lpwp = null;
            if (!this.resizeChildren(true, currentLpwp)) {
                this.resizeChildren(false, currentLpwp);
            }
        } while (this.lpwp != null);
    }
    
    boolean resizeChildren(final boolean defer, final WINDOWPOS[] pwp) {
        if (pwp == null) {
            return true;
        }
        long hdwp = 0L;
        if (defer) {
            hdwp = OS.BeginDeferWindowPos(pwp.length);
            if (hdwp == 0L) {
                return false;
            }
        }
        for (final WINDOWPOS wp : pwp) {
            if (wp != null) {
                if (defer) {
                    hdwp = OS.DeferWindowPos(hdwp, wp.hwnd, 0L, wp.x, wp.y, wp.cx, wp.cy, wp.flags);
                    if (hdwp == 0L) {
                        return false;
                    }
                }
                else {
                    OS.SetWindowPos(wp.hwnd, 0L, wp.x, wp.y, wp.cx, wp.cy, wp.flags);
                }
            }
        }
        return !defer || OS.EndDeferWindowPos(hdwp);
    }
    
    void resizeEmbeddedHandle(final long embeddedHandle, final int width, final int height) {
        if (embeddedHandle == 0L) {
            return;
        }
        final int[] processID = { 0 };
        final int threadId = OS.GetWindowThreadProcessId(embeddedHandle, processID);
        if (threadId != OS.GetCurrentThreadId()) {
            if (processID[0] == OS.GetCurrentProcessId() && this.display.msgHook == 0L) {
                this.display.getMsgCallback = new Callback((Object)this.display, "getMsgProc", 3);
                this.display.getMsgProc = this.display.getMsgCallback.getAddress();
                this.display.msgHook = OS.SetWindowsHookEx(3, this.display.getMsgProc, OS.GetLibraryHandle(), threadId);
                OS.PostThreadMessage(threadId, 0, 0L, 0L);
            }
            final int flags = 16436;
            OS.SetWindowPos(embeddedHandle, 0L, 0, 0, width, height, 16436);
        }
    }
    
    @Override
    void sendResize() {
        this.setResizeChildren(false);
        super.sendResize();
        if (this.isDisposed()) {
            return;
        }
        if (this.layout != null) {
            this.markLayout(false, false);
            this.updateLayout(false, false);
        }
        this.setResizeChildren(true);
    }
    
    public void setBackgroundMode(final int mode) {
        this.checkWidget();
        this.backgroundMode = mode;
        for (final Control element : this._getChildren()) {
            element.updateBackgroundMode();
        }
    }
    
    @Override
    void setBoundsInPixels(final int x, final int y, final int width, final int height, final int flags, boolean defer) {
        if (this.display.resizeCount > 4) {
            defer = false;
        }
        if (!defer && (this.state & 0x2) != 0x0) {
            this.state &= 0xFFFAFFFF;
            this.state |= 0xA0000;
        }
        super.setBoundsInPixels(x, y, width, height, flags, defer);
        if (!defer && (this.state & 0x2) != 0x0) {
            final boolean wasMoved = (this.state & 0x10000) != 0x0;
            final boolean wasResized = (this.state & 0x40000) != 0x0;
            this.state &= 0xFFF5FFFF;
            if (wasMoved && !this.isDisposed()) {
                this.sendMove();
            }
            if (wasResized && !this.isDisposed()) {
                this.sendResize();
            }
        }
    }
    
    @Override
    public boolean setFocus() {
        this.checkWidget();
        final Control[] children;
        final Control[] array;
        final Control[] getChildren = array = (children = this._getChildren());
        for (final Control child : array) {
            if (child.setRadioFocus(false)) {
                return true;
            }
        }
        for (final Control child : children) {
            if (child.setFocus()) {
                return true;
            }
        }
        return super.setFocus();
    }
    
    public void setLayout(final Layout layout) {
        this.checkWidget();
        this.layout = layout;
    }
    
    public void setLayoutDeferred(final boolean defer) {
        this.checkWidget();
        if (!defer) {
            final int layoutCount = this.layoutCount - 1;
            this.layoutCount = layoutCount;
            if (layoutCount == 0 && ((this.state & 0x80) != 0x0 || (this.state & 0x20) != 0x0)) {
                this.updateLayout(true);
            }
        }
        else {
            ++this.layoutCount;
        }
    }
    
    public void setTabList(Control[] tabList) {
        this.checkWidget();
        if (tabList != null) {
            for (final Control control : tabList) {
                if (control == null) {
                    this.error(5);
                }
                if (control.isDisposed()) {
                    this.error(5);
                }
                if (control.parent != this) {
                    this.error(32);
                }
            }
            final Control[] newList = new Control[tabList.length];
            System.arraycopy(tabList, 0, newList, 0, tabList.length);
            tabList = newList;
        }
        this.tabList = tabList;
    }
    
    void setResizeChildren(final boolean resize) {
        if (resize) {
            this.resizeChildren();
        }
        else {
            if (this.display.resizeCount > 4) {
                return;
            }
            final int count = this.getChildrenCount();
            if (count > 1 && this.lpwp == null) {
                this.lpwp = new WINDOWPOS[count];
            }
        }
    }
    
    @Override
    boolean setTabGroupFocus() {
        if (this.isTabItem()) {
            return this.setTabItemFocus();
        }
        boolean takeFocus = (this.style & 0x80000) == 0x0;
        if ((this.state & 0x2) != 0x0) {
            takeFocus = this.hooksKeys();
            if ((this.style & 0x1000000) != 0x0) {
                takeFocus = true;
            }
        }
        if (takeFocus && this.setTabItemFocus()) {
            return true;
        }
        final Control[] children;
        final Control[] array;
        final Control[] getChildren = array = (children = this._getChildren());
        for (final Control child : array) {
            if (!child.isDisposed() && child.isTabItem() && child.setRadioFocus(true)) {
                return true;
            }
        }
        for (final Control child : children) {
            if (!child.isDisposed() && child.isTabItem() && !child.isTabGroup() && child.setTabItemFocus()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    boolean updateTextDirection(final int textDirection) {
        super.updateTextDirection(textDirection);
        final Control[] children = this._getChildren();
        int i = children.length;
        while (i-- > 0) {
            if (children[i] != null && !children[i].isDisposed()) {
                children[i].updateTextDirection(textDirection);
            }
        }
        return true;
    }
    
    String toolTipText(final NMTTDISPINFO hdr) {
        final Shell shell = this.getShell();
        if ((hdr.uFlags & 0x1) == 0x0) {
            String string = null;
            final ToolTip toolTip = shell.findToolTip((int)hdr.idFrom);
            if (toolTip != null) {
                string = toolTip.message;
                if (string == null || string.length() == 0) {
                    string = " ";
                }
                if (string.length() > 1024) {
                    string = this.display.wrapText(string, this.handle, toolTip.getWidth());
                }
            }
            return string;
        }
        shell.setToolTipTitle(hdr.hwndFrom, null, 0);
        OS.SendMessage(hdr.hwndFrom, 1048, 0L, 32767L);
        final Control control = this.display.getControl(hdr.idFrom);
        return (control != null) ? control.toolTipText : null;
    }
    
    @Override
    boolean translateMnemonic(final Event event, final Control control) {
        if (super.translateMnemonic(event, control)) {
            return true;
        }
        if (control != null) {
            for (final Control child : this._getChildren()) {
                if (child.translateMnemonic(event, control)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    boolean translateTraversal(final MSG msg) {
        if ((this.state & 0x2) != 0x0) {
            if ((this.style & 0x1000000) != 0x0) {
                return false;
            }
            switch ((int)msg.wParam) {
                case 33:
                case 34:
                case 37:
                case 38:
                case 39:
                case 40: {
                    final int uiState = (int)OS.SendMessage(msg.hwnd, 297, 0L, 0L);
                    if ((uiState & 0x1) != 0x0) {
                        OS.SendMessage(msg.hwnd, 296, OS.MAKEWPARAM(2, 1), 0L);
                        break;
                    }
                    break;
                }
            }
        }
        return super.translateTraversal(msg);
    }
    
    @Override
    void updateBackgroundColor() {
        super.updateBackgroundColor();
        for (final Control element : this._getChildren()) {
            if ((element.state & 0x400) != 0x0) {
                element.updateBackgroundColor();
            }
        }
    }
    
    @Override
    void updateBackgroundImage() {
        super.updateBackgroundImage();
        for (final Control element : this._getChildren()) {
            if ((element.state & 0x400) != 0x0) {
                element.updateBackgroundImage();
            }
        }
    }
    
    @Override
    void updateBackgroundMode() {
        super.updateBackgroundMode();
        for (final Control element : this._getChildren()) {
            element.updateBackgroundMode();
        }
    }
    
    @Override
    void updateFont(final Font oldFont, final Font newFont) {
        super.updateFont(oldFont, newFont);
        for (final Control control : this._getChildren()) {
            if (!control.isDisposed()) {
                control.updateFont(oldFont, newFont);
            }
        }
    }
    
    void updateLayout(final boolean all) {
        this.updateLayout(true, all);
    }
    
    @Override
    void updateLayout(final boolean resize, final boolean all) {
        final Composite parent = this.findDeferredControl();
        if (parent != null) {
            final Composite composite2;
            final Composite composite = composite2 = parent;
            composite2.state |= 0x80;
            return;
        }
        if ((this.state & 0x20) != 0x0) {
            final boolean changed = (this.state & 0x40) != 0x0;
            this.state &= 0xFFFFFF9F;
            this.display.runSkin();
            if (resize) {
                this.setResizeChildren(false);
            }
            this.layout.layout(this, changed);
            if (resize) {
                this.setResizeChildren(true);
            }
        }
        if (all) {
            this.state &= 0xFFFFFF7F;
            for (final Control element : this._getChildren()) {
                element.updateLayout(resize, all);
            }
        }
    }
    
    @Override
    void updateOrientation() {
        final Control[] controls = this._getChildren();
        final RECT[] rects = new RECT[controls.length];
        for (int i = 0; i < controls.length; ++i) {
            final Control control = controls[i];
            final RECT[] array = rects;
            final int n = i;
            final RECT rect3 = new RECT();
            array[n] = rect3;
            final RECT rect4 = rect3;
            control.forceResize();
            OS.GetWindowRect(control.topHandle(), rect4);
            OS.MapWindowPoints(0L, this.handle, rect4, 2);
        }
        final int orientation = this.style & 0x6000000;
        super.updateOrientation();
        for (int j = 0; j < controls.length; ++j) {
            final Control control2 = controls[j];
            final RECT rect5 = rects[j];
            control2.setOrientation(orientation);
            final int flags = 21;
            OS.SetWindowPos(control2.topHandle(), 0L, rect5.left, rect5.top, 0, 0, 21);
        }
    }
    
    void updateUIState() {
        final long hwndShell = this.getShell().handle;
        final int uiState = (int)OS.SendMessage(hwndShell, 297, 0L, 0L);
        if ((uiState & 0x1) != 0x0) {
            OS.SendMessage(hwndShell, 295, OS.MAKEWPARAM(2, 1), 0L);
        }
    }
    
    @Override
    int widgetStyle() {
        return super.widgetStyle() | 0x2000000;
    }
    
    @Override
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.state & 0x2) != 0x0 && (this.style & 0x40040000) != 0x0) {
            return LRESULT.ZERO;
        }
        return result;
    }
    
    @Override
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_GETDLGCODE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.state & 0x2) != 0x0) {
            int flags = 0;
            if (this.hooksKeys()) {
                flags |= 0x7;
            }
            if ((this.style & 0x80000) != 0x0) {
                flags |= 0x100;
            }
            if (OS.GetWindow(this.handle, 5) != 0L) {
                flags |= 0x100;
            }
            if (flags != 0) {
                return new LRESULT((long)flags);
            }
        }
        return result;
    }
    
    @Override
    LRESULT WM_GETFONT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_GETFONT(wParam, lParam);
        if (result != null) {
            return result;
        }
        final long code = this.callWindowProc(this.handle, 49, wParam, lParam);
        if (code != 0L) {
            return new LRESULT(code);
        }
        return new LRESULT((this.font != null) ? this.font.handle : this.defaultFont());
    }
    
    @Override
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        if ((this.state & 0x2) != 0x0 && (this.style & 0x80000) == 0x0 && this.hooksKeys() && OS.GetWindow(this.handle, 5) == 0L) {
            this.setFocus();
        }
        return result;
    }
    
    @Override
    LRESULT WM_NCHITTEST(final long wParam, final long lParam) {
        final LRESULT result = super.WM_NCHITTEST(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (OS.IsAppThemed() && (this.state & 0x2) != 0x0) {
            final long code = this.callWindowProc(this.handle, 132, wParam, lParam);
            if (code == 1L) {
                final RECT rect = new RECT();
                OS.GetClientRect(this.handle, rect);
                final POINT pt = new POINT();
                pt.x = OS.GET_X_LPARAM(lParam);
                pt.y = OS.GET_Y_LPARAM(lParam);
                OS.MapWindowPoints(0L, this.handle, pt, 1);
                if (!OS.PtInRect(rect, pt)) {
                    final int flags = 1025;
                    OS.RedrawWindow(this.handle, (RECT)null, 0L, 1025);
                }
            }
            return new LRESULT(code);
        }
        return result;
    }
    
    @Override
    LRESULT WM_PARENTNOTIFY(final long wParam, final long lParam) {
        if ((this.state & 0x2) != 0x0 && (this.style & 0x1000000) != 0x0 && OS.LOWORD(wParam) == 1) {
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            this.resizeEmbeddedHandle(lParam, rect.right - rect.left, rect.bottom - rect.top);
        }
        return super.WM_PARENTNOTIFY(wParam, lParam);
    }
    
    @Override
    LRESULT WM_PAINT(final long wParam, final long lParam) {
        if ((this.state & 0x1000) != 0x0) {
            return LRESULT.ZERO;
        }
        if ((this.state & 0x2) == 0x0 || (this.state & 0x4000) != 0x0) {
            return super.WM_PAINT(wParam, lParam);
        }
        final int oldBits = OS.GetWindowLong(this.handle, -16);
        final int newBits = oldBits | 0x4000000 | 0x2000000;
        if (newBits != oldBits) {
            OS.SetWindowLong(this.handle, -16, newBits);
        }
        final PAINTSTRUCT ps = new PAINTSTRUCT();
        if (this.hooks(9) || this.filters(9)) {
            boolean bufferedPaint = false;
            if ((this.style & 0x20000000) != 0x0 && (this.style & 0x44200000) == 0x0) {
                bufferedPaint = true;
            }
            if (bufferedPaint) {
                final long hDC = OS.BeginPaint(this.handle, ps);
                final int width = ps.right - ps.left;
                final int height = ps.bottom - ps.top;
                if (width != 0 && height != 0) {
                    final long[] phdc = { 0L };
                    final int flags = 0;
                    final RECT prcTarget = new RECT();
                    OS.SetRect(prcTarget, ps.left, ps.top, ps.right, ps.bottom);
                    final long hBufferedPaint = OS.BeginBufferedPaint(hDC, prcTarget, 0, (BP_PAINTPARAMS)null, phdc);
                    final GCData data = new GCData();
                    data.device = this.display;
                    data.foreground = this.getForegroundPixel();
                    Control control = this.findBackgroundControl();
                    if (control == null) {
                        control = this;
                    }
                    data.background = control.getBackgroundPixel();
                    data.font = Font.win32_new((Device)this.display, OS.SendMessage(this.handle, 49, 0L, 0L));
                    data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                    if ((this.style & 0x40000) == 0x0) {
                        final RECT rect = new RECT();
                        OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                        this.drawBackground(phdc[0], rect);
                    }
                    final GC gc = GC.win32_new(phdc[0], data);
                    final Event event = new Event();
                    event.gc = gc;
                    event.setBoundsInPixels(new Rectangle(ps.left, ps.top, width, height));
                    this.sendEvent(9, event);
                    if (data.focusDrawn && !this.isDisposed()) {
                        this.updateUIState();
                    }
                    gc.dispose();
                    OS.EndBufferedPaint(hBufferedPaint, true);
                }
                OS.EndPaint(this.handle, ps);
            }
            else {
                final GCData data2 = new GCData();
                data2.ps = ps;
                data2.hwnd = this.handle;
                GC gc2 = GC.win32_new((Drawable)this, data2);
                long sysRgn = 0L;
                if ((this.style & 0x60000000) != 0x0 || (this.style & 0x200000) != 0x0) {
                    sysRgn = OS.CreateRectRgn(0, 0, 0, 0);
                    if (OS.GetRandomRgn(gc2.handle, sysRgn, 4) == 1) {
                        if ((OS.GetLayout(gc2.handle) & 0x1) != 0x0) {
                            final int nBytes = OS.GetRegionData(sysRgn, 0, (int[])null);
                            final int[] lpRgnData = new int[nBytes / 4];
                            OS.GetRegionData(sysRgn, nBytes, lpRgnData);
                            final long newSysRgn = OS.ExtCreateRegion(new float[] { -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f }, nBytes, lpRgnData);
                            OS.DeleteObject(sysRgn);
                            sysRgn = newSysRgn;
                        }
                        final POINT pt = new POINT();
                        OS.MapWindowPoints(0L, this.handle, pt, 1);
                        OS.OffsetRgn(sysRgn, pt.x, pt.y);
                    }
                }
                final int width2 = ps.right - ps.left;
                final int height2 = ps.bottom - ps.top;
                if (width2 != 0 && height2 != 0) {
                    GC paintGC = null;
                    Image image = null;
                    if ((this.style & 0x60000000) != 0x0) {
                        image = new Image((Device)this.display, width2, height2);
                        paintGC = gc2;
                        gc2 = new GC((Drawable)image, paintGC.getStyle() & 0x4000000);
                        final GCData gcData = gc2.getGCData();
                        gcData.uiState = data2.uiState;
                        gc2.setForeground(this.getForeground());
                        gc2.setBackground(this.getBackground());
                        gc2.setFont(this.getFont());
                        if ((this.style & 0x40000000) != 0x0) {
                            OS.BitBlt(gc2.handle, 0, 0, width2, height2, paintGC.handle, ps.left, ps.top, 13369376);
                        }
                        OS.OffsetRgn(sysRgn, -ps.left, -ps.top);
                        OS.SelectClipRgn(gc2.handle, sysRgn);
                        OS.OffsetRgn(sysRgn, ps.left, ps.top);
                        OS.SetMetaRgn(gc2.handle);
                        OS.SetWindowOrgEx(gc2.handle, ps.left, ps.top, (POINT)null);
                        OS.SetBrushOrgEx(gc2.handle, ps.left, ps.top, (POINT)null);
                        if ((this.style & 0x40040000) == 0x0) {
                            final RECT rect2 = new RECT();
                            OS.SetRect(rect2, ps.left, ps.top, ps.right, ps.bottom);
                            this.drawBackground(gc2.handle, rect2);
                        }
                    }
                    final Event event2 = new Event();
                    event2.gc = gc2;
                    RECT rect2 = null;
                    if ((this.style & 0x200000) != 0x0 && OS.GetRgnBox(sysRgn, rect2 = new RECT()) == 3) {
                        final int nBytes2 = OS.GetRegionData(sysRgn, 0, (int[])null);
                        final int[] lpRgnData2 = new int[nBytes2 / 4];
                        OS.GetRegionData(sysRgn, nBytes2, lpRgnData2);
                        for (int count = lpRgnData2[2], i = 0; i < count; ++i) {
                            final int offset = 8 + (i << 2);
                            OS.SetRect(rect2, lpRgnData2[offset], lpRgnData2[offset + 1], lpRgnData2[offset + 2], lpRgnData2[offset + 3]);
                            if ((this.style & 0x60040000) == 0x0) {
                                this.drawBackground(gc2.handle, rect2);
                            }
                            event2.setBoundsInPixels(new Rectangle(rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top));
                            event2.count = count - 1 - i;
                            this.sendEvent(9, event2);
                        }
                    }
                    else {
                        if ((this.style & 0x60040000) == 0x0) {
                            if (rect2 == null) {
                                rect2 = new RECT();
                            }
                            OS.SetRect(rect2, ps.left, ps.top, ps.right, ps.bottom);
                            this.drawBackground(gc2.handle, rect2);
                        }
                        event2.setBoundsInPixels(new Rectangle(ps.left, ps.top, width2, height2));
                        this.sendEvent(9, event2);
                    }
                    event2.gc = null;
                    if ((this.style & 0x60000000) != 0x0) {
                        if (!gc2.isDisposed()) {
                            final GCData gcData2 = gc2.getGCData();
                            if (gcData2.focusDrawn && !this.isDisposed()) {
                                this.updateUIState();
                            }
                        }
                        gc2.dispose();
                        if (!this.isDisposed()) {
                            paintGC.drawImage(image, DPIUtil.autoScaleDown(ps.left), DPIUtil.autoScaleDown(ps.top));
                        }
                        image.dispose();
                        gc2 = paintGC;
                    }
                }
                if (sysRgn != 0L) {
                    OS.DeleteObject(sysRgn);
                }
                if (data2.focusDrawn && !this.isDisposed()) {
                    this.updateUIState();
                }
                gc2.dispose();
            }
        }
        else {
            final long hDC2 = OS.BeginPaint(this.handle, ps);
            if ((this.style & 0x40040000) == 0x0) {
                final RECT rect3 = new RECT();
                OS.SetRect(rect3, ps.left, ps.top, ps.right, ps.bottom);
                this.drawBackground(hDC2, rect3);
            }
            OS.EndPaint(this.handle, ps);
        }
        if (!this.isDisposed() && newBits != oldBits && !this.isDisposed()) {
            OS.SetWindowLong(this.handle, -16, oldBits);
        }
        return LRESULT.ZERO;
    }
    
    @Override
    LRESULT WM_PRINTCLIENT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_PRINTCLIENT(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.state & 0x2) != 0x0) {
            this.forceResize();
            final int nSavedDC = OS.SaveDC(wParam);
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            if ((this.style & 0x40040000) == 0x0) {
                this.drawBackground(wParam, rect);
            }
            if (this.hooks(9) || this.filters(9)) {
                final GCData data = new GCData();
                data.device = this.display;
                data.foreground = this.getForegroundPixel();
                Control control = this.findBackgroundControl();
                if (control == null) {
                    control = this;
                }
                data.background = control.getBackgroundPixel();
                data.font = Font.win32_new((Device)this.display, OS.SendMessage(this.handle, 49, 0L, 0L));
                data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                final GC gc = GC.win32_new(wParam, data);
                final Event event = new Event();
                event.gc = gc;
                event.setBoundsInPixels(new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top));
                this.sendEvent(9, event);
                event.gc = null;
                gc.dispose();
            }
            OS.RestoreDC(wParam, nSavedDC);
        }
        return result;
    }
    
    @Override
    LRESULT WM_SETFONT(final long wParam, final long lParam) {
        if (lParam != 0L) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        return super.WM_SETFONT(wParam, lParam);
    }
    
    @Override
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        LRESULT result = null;
        if ((this.state & 0x80000) != 0x0) {
            result = super.WM_SIZE(wParam, lParam);
        }
        else {
            this.setResizeChildren(false);
            result = super.WM_SIZE(wParam, lParam);
            if (this.isDisposed()) {
                return result;
            }
            if (this.layout != null) {
                this.markLayout(false, false);
                this.updateLayout(false, false);
            }
            this.setResizeChildren(true);
        }
        if (OS.IsWindowVisible(this.handle)) {
            if ((this.state & 0x2) != 0x0 && (this.style & 0x100000) == 0x0 && this.hooks(9)) {
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
            if (OS.IsAppThemed() && this.findThemeControl() != null) {
                this.redrawChildren();
            }
        }
        if ((this.state & 0x2) != 0x0 && (this.style & 0x1000000) != 0x0) {
            this.resizeEmbeddedHandle(OS.GetWindow(this.handle, 5), OS.LOWORD(lParam), OS.HIWORD(lParam));
        }
        return result;
    }
    
    @Override
    LRESULT WM_SYSCOLORCHANGE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SYSCOLORCHANGE(wParam, lParam);
        if (result != null) {
            return result;
        }
        for (long hwndChild = OS.GetWindow(this.handle, 5); hwndChild != 0L; hwndChild = OS.GetWindow(hwndChild, 2)) {
            OS.SendMessage(hwndChild, 21, 0L, 0L);
        }
        return result;
    }
    
    @Override
    LRESULT WM_SYSCOMMAND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SYSCOMMAND(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((wParam & 0xF000L) == 0x0L) {
            return result;
        }
        final int cmd = (int)wParam & 0xFFF0;
        switch (cmd) {
            case 61552:
            case 61568: {
                final boolean showHBar = this.horizontalBar != null && this.horizontalBar.getVisible();
                final boolean showVBar = this.verticalBar != null && this.verticalBar.getVisible();
                final long code = this.callWindowProc(this.handle, 274, wParam, lParam);
                if (showHBar != (this.horizontalBar != null && this.horizontalBar.getVisible()) || showVBar != (this.verticalBar != null && this.verticalBar.getVisible())) {
                    final int flags = 1281;
                    OS.RedrawWindow(this.handle, (RECT)null, 0L, 1281);
                }
                if (code == 0L) {
                    return LRESULT.ZERO;
                }
                return new LRESULT(code);
            }
            default: {
                return result;
            }
        }
    }
    
    @Override
    LRESULT WM_UPDATEUISTATE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_UPDATEUISTATE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.state & 0x2) != 0x0 && this.hooks(9)) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        return result;
    }
    
    @Override
    LRESULT wmNCPaint(final long hwnd, final long wParam, final long lParam) {
        final LRESULT result = super.wmNCPaint(hwnd, wParam, lParam);
        if (result != null) {
            return result;
        }
        final long borderHandle = this.borderHandle();
        if (((this.state & 0x2) != 0x0 || (hwnd == borderHandle && this.handle != borderHandle)) && OS.IsAppThemed()) {
            final int bits1 = OS.GetWindowLong(hwnd, -20);
            if ((bits1 & 0x200) != 0x0) {
                long code = 0L;
                final int bits2 = OS.GetWindowLong(hwnd, -16);
                if ((bits2 & 0x300000) != 0x0) {
                    code = this.callWindowProc(hwnd, 133, wParam, lParam);
                }
                final long hDC = OS.GetWindowDC(hwnd);
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
                OS.ExcludeClipRect(hDC, border, border, rect.right - border, rect.bottom - border);
                OS.DrawThemeBackground(this.display.hEditTheme(), hDC, 1, 1, rect, (RECT)null);
                OS.ReleaseDC(hwnd, hDC);
                return new LRESULT(code);
            }
        }
        return result;
    }
    
    @Override
    LRESULT wmNotify(final NMHDR hdr, final long wParam, final long lParam) {
        switch (hdr.code) {
            case -522:
            case -521: {
                long hwndParent = hdr.hwndFrom;
                int bits;
                do {
                    hwndParent = OS.GetParent(hwndParent);
                    if (hwndParent == 0L) {
                        break;
                    }
                    bits = OS.GetWindowLong(hwndParent, -20);
                } while ((bits & 0x8) == 0x0);
                if (hwndParent != 0L) {
                    break;
                }
                if (this.display.getActiveShell() == null) {
                    return LRESULT.ONE;
                }
                this.display.lockActiveWindow = true;
                final int flags = 19;
                final long hwndInsertAfter = (hdr.code == -521) ? -1L : -2L;
                OS.SetWindowPos(hdr.hwndFrom, hwndInsertAfter, 0, 0, 0, 0, 19);
                this.display.lockActiveWindow = false;
                break;
            }
            case -530: {
                final NMTTDISPINFO lpnmtdi = new NMTTDISPINFO();
                OS.MoveMemory(lpnmtdi, lParam, NMTTDISPINFO.sizeof);
                String string = this.toolTipText(lpnmtdi);
                if (string != null) {
                    final Shell shell = this.getShell();
                    string = Display.withCrLf(string);
                    if (string.length() > 4096) {
                        string = string.substring(0, 4096);
                    }
                    final char[] chars = this.fixMnemonic(string, false, true);
                    Widget widget = null;
                    final long hwnd = hdr.idFrom;
                    if ((lpnmtdi.uFlags & 0x1) != 0x0) {
                        widget = this.display.getControl(hwnd);
                    }
                    else if (hdr.hwndFrom == shell.toolTipHandle || hdr.hwndFrom == shell.balloonTipHandle) {
                        widget = shell.findToolTip((int)hdr.idFrom);
                    }
                    if (widget != null) {
                        final int style = widget.getStyle();
                        final int flags2 = -2080374784;
                        if ((style & 0x84000000) != 0x0 && (style & 0x84000000) != 0x84000000) {
                            final NMTTDISPINFO nmttdispinfo3;
                            final NMTTDISPINFO nmttdispinfo = nmttdispinfo3 = lpnmtdi;
                            nmttdispinfo3.uFlags |= 0x4;
                        }
                        else {
                            final NMTTDISPINFO nmttdispinfo4;
                            final NMTTDISPINFO nmttdispinfo2 = nmttdispinfo4 = lpnmtdi;
                            nmttdispinfo4.uFlags &= 0xFFFFFFFB;
                        }
                    }
                    shell.setToolTipText(lpnmtdi, chars);
                    OS.MoveMemory(lParam, lpnmtdi, NMTTDISPINFO.sizeof);
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        return super.wmNotify(hdr, wParam, lParam);
    }
    
    @Override
    public String toString() {
        return super.toString() + " [layout=" + this.layout;
    }
}
