//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class ToolTip extends Widget
{
    Shell parent;
    TrayItem item;
    String text;
    String message;
    int id;
    int x;
    int y;
    boolean autoHide;
    boolean hasLocation;
    boolean visible;
    static final int TIMER_ID = 100;
    
    public ToolTip(final Shell parent, final int style) {
        super((Widget)parent, checkStyle(style));
        this.text = "";
        this.message = "";
        this.autoHide = true;
        this.checkOrientation((Widget)(this.parent = parent));
        parent.createToolTip(this);
    }
    
    static int checkStyle(final int style) {
        final int mask = 11;
        if ((style & 0xB) == 0x0) {
            return style;
        }
        return Widget.checkBits(style, 2, 8, 1, 0, 0, 0);
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
    
    @Override
    void destroyWidget() {
        if (this.parent != null) {
            this.parent.destroyToolTip(this);
        }
        this.releaseHandle();
    }
    
    public boolean getAutoHide() {
        this.checkWidget();
        return this.autoHide;
    }
    
    public String getMessage() {
        this.checkWidget();
        return this.message;
    }
    
    public Shell getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    public boolean getVisible() {
        this.checkWidget();
        if (this.item != null) {
            return this.visible;
        }
        final long hwndToolTip = this.hwndToolTip();
        if (OS.SendMessage(hwndToolTip, 1083, 0L, 0L) != 0L) {
            final TOOLINFO lpti = new TOOLINFO();
            lpti.cbSize = TOOLINFO.sizeof;
            if (OS.SendMessage(hwndToolTip, 1083, 0L, lpti) != 0L) {
                return (lpti.uFlags & 0x1) == 0x0 && lpti.uId == this.id;
            }
        }
        return false;
    }
    
    int getWidth() {
        final long hwnd = this.parent.handle;
        final long hmonitor = OS.MonitorFromWindow(hwnd, 2);
        final MONITORINFO lpmi = new MONITORINFO();
        lpmi.cbSize = MONITORINFO.sizeof;
        OS.GetMonitorInfo(hmonitor, lpmi);
        final int maxWidth = lpmi.rcWork_right - lpmi.rcWork_left;
        return maxWidth / 4;
    }
    
    long hwndToolTip() {
        return ((this.style & 0x1000) != 0x0) ? this.parent.balloonTipHandle() : this.parent.toolTipHandle();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        if (this.item != null) {
            return this.getVisible() && this.item.getVisible();
        }
        return this.getVisible();
    }
    
    @Override
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
        this.item = null;
        this.id = -1;
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        if (this.item == null && this.autoHide) {
            final long hwndToolTip = this.hwndToolTip();
            if (OS.SendMessage(hwndToolTip, 1083, 0L, 0L) != 0L) {
                final TOOLINFO lpti = new TOOLINFO();
                lpti.cbSize = TOOLINFO.sizeof;
                if (OS.SendMessage(hwndToolTip, 1083, 0L, lpti) != 0L && (lpti.uFlags & 0x1) == 0x0 && lpti.uId == this.id) {
                    OS.SendMessage(hwndToolTip, 1041, 0L, lpti);
                    OS.SendMessage(hwndToolTip, 1052, 0L, 0L);
                    OS.KillTimer(hwndToolTip, 100L);
                }
            }
        }
        if (this.item != null && this.item.toolTip == this) {
            this.item.toolTip = null;
        }
        this.item = null;
        final String s = null;
        this.message = s;
        this.text = s;
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
    
    public void setAutoHide(final boolean autoHide) {
        this.checkWidget();
        this.autoHide = autoHide;
    }
    
    public void setLocation(final int x, final int y) {
        this.checkWidget();
        this.setLocationInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y));
    }
    
    void setLocationInPixels(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.hasLocation = true;
    }
    
    public void setLocation(Point location) {
        this.checkWidget();
        if (location == null) {
            this.error(4);
        }
        location = DPIUtil.autoScaleUp(location);
        this.setLocationInPixels(location.x, location.y);
    }
    
    public void setMessage(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        this.message = string;
        this.updateMessage();
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        this.text = string;
    }
    
    void updateMessage() {
        final long hwnd = this.hwndToolTip();
        if (OS.SendMessage(hwnd, 1083, 0L, 0L) != 0L) {
            final TOOLINFO lpti = new TOOLINFO();
            lpti.cbSize = TOOLINFO.sizeof;
            if (OS.SendMessage(hwnd, 1083, 0L, lpti) != 0L) {
                if (this.message != null && this.message.length() > 0) {
                    final long hHeap = OS.GetProcessHeap();
                    final TCHAR buffer = new TCHAR(0, this.message, true);
                    final int byteCount = buffer.length() * 2;
                    OS.MoveMemory(lpti.lpszText = OS.HeapAlloc(hHeap, 8, byteCount), buffer, byteCount);
                    OS.SendMessage(hwnd, 1081, 0L, lpti);
                    OS.HeapFree(hHeap, 0, lpti.lpszText);
                }
                else {
                    lpti.lpszText = -1L;
                    OS.SendMessage(hwnd, 1081, 0L, lpti);
                }
            }
        }
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (visible == this.getVisible()) {
            return;
        }
        if (this.item == null) {
            final long hwnd = this.parent.handle;
            final TOOLINFO lpti = new TOOLINFO();
            lpti.cbSize = TOOLINFO.sizeof;
            lpti.uId = this.id;
            lpti.hwnd = hwnd;
            final long hwndToolTip = this.hwndToolTip();
            final Shell shell = this.parent.getShell();
            if (this.text.length() != 0) {
                int icon = 0;
                if ((this.style & 0x2) != 0x0) {
                    icon = 1;
                }
                if ((this.style & 0x8) != 0x0) {
                    icon = 2;
                }
                if ((this.style & 0x1) != 0x0) {
                    icon = 3;
                }
                shell.setToolTipTitle(hwndToolTip, this.text, icon);
            }
            else {
                shell.setToolTipTitle(hwndToolTip, (String)null, 0);
            }
            OS.SendMessage(hwndToolTip, 1048, 0L, (long)this.getWidth());
            if (visible) {
                int nX = this.x;
                int nY = this.y;
                if (!this.hasLocation) {
                    final POINT pt = new POINT();
                    if (OS.GetCursorPos(pt)) {
                        nX = pt.x;
                        nY = pt.y;
                    }
                }
                final long lParam = OS.MAKELPARAM(nX, nY);
                OS.SendMessage(hwndToolTip, 1042, 0L, lParam);
                final POINT pt2 = new POINT();
                OS.GetCursorPos(pt2);
                final RECT rect = new RECT();
                OS.GetClientRect(hwnd, rect);
                OS.MapWindowPoints(hwnd, 0L, rect, 2);
                if (!OS.PtInRect(rect, pt2)) {
                    final long hCursor = OS.GetCursor();
                    OS.SetCursor(0L);
                    OS.SetCursorPos(rect.left, rect.top);
                    OS.SendMessage(hwndToolTip, 1041, 1L, lpti);
                    OS.SetCursorPos(pt2.x, pt2.y);
                    OS.SetCursor(hCursor);
                }
                else {
                    OS.SendMessage(hwndToolTip, 1041, 1L, lpti);
                }
                final int time = (int)OS.SendMessage(hwndToolTip, 1045, 2L, 0L);
                OS.SetTimer(hwndToolTip, 100L, time, 0L);
                this.updateMessage();
            }
            else {
                OS.SendMessage(hwndToolTip, 1041, 0L, lpti);
                OS.SendMessage(hwndToolTip, 1052, 0L, 0L);
                OS.KillTimer(hwndToolTip, 100L);
            }
            return;
        }
        if (this.item != null && visible) {
            final NOTIFYICONDATA iconData = new NOTIFYICONDATA();
            final char[] szInfoTitle = iconData.szInfoTitle;
            final int length1 = Math.min(szInfoTitle.length - 1, this.text.length());
            this.text.getChars(0, length1, szInfoTitle, 0);
            final char[] szInfo = iconData.szInfo;
            final int length2 = Math.min(szInfo.length - 1, this.message.length());
            this.message.getChars(0, length2, szInfo, 0);
            final Display display = this.item.getDisplay();
            iconData.cbSize = NOTIFYICONDATA.sizeof;
            iconData.uID = this.item.id;
            iconData.hWnd = display.hwndMessage;
            iconData.uFlags = 16;
            if ((this.style & 0x2) != 0x0) {
                iconData.dwInfoFlags = 1;
            }
            if ((this.style & 0x8) != 0x0) {
                iconData.dwInfoFlags = 2;
            }
            if ((this.style & 0x1) != 0x0) {
                iconData.dwInfoFlags = 3;
            }
            this.sendEvent(22);
            this.visible = OS.Shell_NotifyIcon(1, iconData);
        }
    }
}
