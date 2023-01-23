//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.win32.*;

public class TrayItem extends Item
{
    Tray parent;
    int id;
    Image image2;
    Image highlightImage;
    ToolTip toolTip;
    String toolTipText;
    boolean visible;
    
    public TrayItem(final Tray parent, final int style) {
        super((Widget)parent, style);
        this.visible = true;
        (this.parent = parent).createItem(this, parent.getItemCount());
        this.createUpdateWidget(true);
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
    
    public void addMenuDetectListener(final MenuDetectListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(35, (Listener)typedListener);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void createUpdateWidget(final boolean newIcon) {
        final NOTIFYICONDATA iconData = new NOTIFYICONDATA();
        iconData.cbSize = NOTIFYICONDATA.sizeof;
        final NOTIFYICONDATA notifyicondata = iconData;
        int nextTrayId;
        int id;
        if (newIcon) {
            final Display display = this.display;
            display.nextTrayId = (id = (nextTrayId = display.nextTrayId)) + 1;
        }
        else {
            id = (nextTrayId = this.id);
        }
        this.id = id;
        notifyicondata.uID = nextTrayId;
        iconData.hWnd = this.display.hwndMessage;
        iconData.uFlags = 1;
        iconData.uCallbackMessage = 32772;
        OS.Shell_NotifyIcon((int)(newIcon ? 0 : 1), iconData);
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Image getHighlightImage() {
        this.checkWidget();
        return this.highlightImage;
    }
    
    public Tray getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public ToolTip getToolTip() {
        this.checkWidget();
        return this.toolTip;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    public boolean getVisible() {
        this.checkWidget();
        return this.visible;
    }
    
    long messageProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        switch ((int)lParam) {
            case 513: {
                if (this.hooks(13)) {
                    OS.SetForegroundWindow(hwnd);
                    this.sendSelectionEvent(13);
                    break;
                }
                break;
            }
            case 515:
            case 518: {
                if (this.hooks(14)) {
                    OS.SetForegroundWindow(hwnd);
                    this.sendSelectionEvent(14);
                    break;
                }
                break;
            }
            case 517: {
                if (!this.hooks(35)) {
                    break;
                }
                OS.SetForegroundWindow(hwnd);
                this.sendEvent(35);
                if (this.isDisposed()) {
                    return 0L;
                }
                break;
            }
            case 1026: {
                if (this.toolTip == null) {
                    break;
                }
                if (this.toolTip.visible) {
                    break;
                }
                this.toolTip.visible = true;
                if (!this.toolTip.hooks(22)) {
                    break;
                }
                OS.SetForegroundWindow(hwnd);
                this.toolTip.sendEvent(22);
                if (this.isDisposed()) {
                    return 0L;
                }
                break;
            }
            case 1027:
            case 1028:
            case 1029: {
                if (this.toolTip == null) {
                    break;
                }
                if (this.toolTip.visible) {
                    this.toolTip.visible = false;
                    if (this.toolTip.hooks(23)) {
                        OS.SetForegroundWindow(hwnd);
                        this.toolTip.sendEvent(23);
                        if (this.isDisposed()) {
                            return 0L;
                        }
                    }
                }
                if (lParam != 1029L) {
                    break;
                }
                if (!this.toolTip.hooks(13)) {
                    break;
                }
                OS.SetForegroundWindow(hwnd);
                this.toolTip.sendSelectionEvent(13);
                if (this.isDisposed()) {
                    return 0L;
                }
                break;
            }
        }
        this.display.wakeThread();
        return 0L;
    }
    
    void recreate() {
        this.createUpdateWidget(false);
        if (!this.visible) {
            this.setVisible(false);
        }
        if (this.text.length() != 0) {
            this.setText(this.text);
        }
        if (this.image != null) {
            this.setImage(this.image);
        }
        if (this.toolTipText != null) {
            this.setToolTipText(this.toolTipText);
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.toolTip != null) {
            this.toolTip.item = null;
        }
        this.toolTip = null;
        if (this.image2 != null) {
            this.image2.dispose();
        }
        this.image2 = null;
        this.highlightImage = null;
        this.toolTipText = null;
        final NOTIFYICONDATA iconData = new NOTIFYICONDATA();
        iconData.cbSize = NOTIFYICONDATA.sizeof;
        iconData.uID = this.id;
        iconData.hWnd = this.display.hwndMessage;
        OS.Shell_NotifyIcon(2, iconData);
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
    
    public void removeMenuDetectListener(final MenuDetectListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(35, (SWTEventListener)listener);
    }
    
    public void setHighlightImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.highlightImage = image;
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        super.setImage(image);
        if (this.image2 != null) {
            this.image2.dispose();
        }
        this.image2 = null;
        long hIcon = 0L;
        final Image icon = image;
        if (icon != null) {
            switch (icon.type) {
                case 0: {
                    this.image2 = Display.createIcon(image);
                    hIcon = this.image2.handle;
                    break;
                }
                case 1: {
                    hIcon = icon.handle;
                    break;
                }
            }
        }
        final NOTIFYICONDATA iconData = new NOTIFYICONDATA();
        iconData.cbSize = NOTIFYICONDATA.sizeof;
        iconData.uID = this.id;
        iconData.hWnd = this.display.hwndMessage;
        iconData.hIcon = hIcon;
        iconData.uFlags = 2;
        OS.Shell_NotifyIcon(1, iconData);
    }
    
    public void setToolTip(final ToolTip toolTip) {
        this.checkWidget();
        final ToolTip oldTip = this.toolTip;
        final ToolTip newTip = toolTip;
        if (oldTip != null) {
            oldTip.item = null;
        }
        if ((this.toolTip = newTip) != null) {
            newTip.item = this;
        }
    }
    
    public void setToolTipText(final String string) {
        this.checkWidget();
        this.toolTipText = string;
        final NOTIFYICONDATA iconData = new NOTIFYICONDATA();
        if (string != null) {
            final char[] szTip = iconData.szTip;
            final int length = Math.min(szTip.length - 1, string.length());
            string.getChars(0, length, szTip, 0);
        }
        iconData.cbSize = NOTIFYICONDATA.sizeof;
        iconData.uID = this.id;
        iconData.hWnd = this.display.hwndMessage;
        iconData.uFlags = 4;
        OS.Shell_NotifyIcon(1, iconData);
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (this.visible == visible) {
            return;
        }
        if (visible) {
            this.sendEvent(22);
            if (this.isDisposed()) {
                return;
            }
        }
        this.visible = visible;
        final NOTIFYICONDATA iconData = new NOTIFYICONDATA();
        iconData.cbSize = NOTIFYICONDATA.sizeof;
        iconData.uID = this.id;
        iconData.hWnd = this.display.hwndMessage;
        iconData.uFlags = 8;
        iconData.dwState = (visible ? 0 : 1);
        OS.Shell_NotifyIcon(iconData.dwStateMask = 1, iconData);
        if (!visible) {
            this.sendEvent(23);
        }
    }
}
