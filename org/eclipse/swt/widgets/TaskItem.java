//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class TaskItem extends Item
{
    TaskBar parent;
    Shell shell;
    int progress;
    int progressState;
    Image overlayImage;
    String overlayText;
    boolean showingText;
    Menu menu;
    static final int PROGRESS_MAX = 100;
    
    TaskItem(final TaskBar parent, final int style) {
        super((Widget)parent, style);
        this.progressState = -1;
        this.overlayText = "";
        this.showingText = false;
        (this.parent = parent).createItem(this, -1);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Menu getMenu() {
        this.checkWidget();
        return this.menu;
    }
    
    public Image getOverlayImage() {
        this.checkWidget();
        return this.overlayImage;
    }
    
    public String getOverlayText() {
        this.checkWidget();
        return this.overlayText;
    }
    
    public TaskBar getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public int getProgress() {
        this.checkWidget();
        return this.progress;
    }
    
    public int getProgressState() {
        this.checkWidget();
        return this.progressState;
    }
    
    void recreate() {
        if (this.showingText) {
            if (this.overlayText.length() != 0) {
                this.updateText();
            }
        }
        else if (this.overlayImage != null) {
            this.updateImage();
        }
        if (this.progress != 0) {
            this.updateProgress();
        }
        if (this.progressState != -1) {
            this.updateProgressState();
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.overlayImage = null;
        this.overlayText = null;
    }
    
    public void setMenu(final Menu menu) {
        this.checkWidget();
        if (menu != null) {
            if (menu.isDisposed()) {
                this.error(5);
            }
            if ((menu.style & 0x8) == 0x0) {
                this.error(37);
            }
        }
        if (this.shell != null) {
            return;
        }
        this.menu = menu;
        this.parent.setMenu(menu);
    }
    
    public void setOverlayImage(final Image overlayImage) {
        this.checkWidget();
        if (overlayImage != null && overlayImage.isDisposed()) {
            this.error(5);
        }
        if (this.shell == null) {
            return;
        }
        if ((this.overlayImage = overlayImage) != null) {
            this.updateImage();
        }
        else if (this.overlayText.length() != 0) {
            this.updateText();
        }
        else {
            this.parent.mTaskbarList3.SetOverlayIcon(this.shell.handle, 0L, 0L);
        }
    }
    
    public void setOverlayText(final String overlayText) {
        this.checkWidget();
        if (overlayText == null) {
            this.error(4);
        }
        if (this.shell == null) {
            return;
        }
        this.overlayText = overlayText;
        if (overlayText.length() != 0) {
            this.updateText();
        }
        else if (this.overlayImage != null) {
            this.updateImage();
        }
        else {
            this.parent.mTaskbarList3.SetOverlayIcon(this.shell.handle, 0L, 0L);
        }
    }
    
    public void setProgress(int progress) {
        this.checkWidget();
        if (this.shell == null) {
            return;
        }
        progress = Math.max(0, Math.min(progress, 100));
        if (this.progress == progress) {
            return;
        }
        this.progress = progress;
        this.updateProgress();
    }
    
    public void setProgressState(final int progressState) {
        this.checkWidget();
        if (this.shell == null) {
            return;
        }
        if (this.progressState == progressState) {
            return;
        }
        this.progressState = progressState;
        this.updateProgressState();
    }
    
    void setShell(final Shell shell) {
        (this.shell = shell).addListener(12, (Listener)new l(this));
    }
    
    void updateImage() {
        this.showingText = false;
        Image image2 = null;
        long hIcon = 0L;
        switch (this.overlayImage.type) {
            case 0: {
                image2 = Display.createIcon(this.overlayImage);
                hIcon = image2.handle;
                break;
            }
            case 1: {
                hIcon = this.overlayImage.handle;
                break;
            }
        }
        this.parent.mTaskbarList3.SetOverlayIcon(this.shell.handle, hIcon, 0L);
        if (image2 != null) {
            image2.dispose();
        }
    }
    
    void updateProgress() {
        if (this.progressState == 2) {
            return;
        }
        if (this.progressState == -1) {
            return;
        }
        this.parent.mTaskbarList3.SetProgressValue(this.shell.handle, (long)this.progress, 100L);
    }
    
    void updateProgressState() {
        int tbpFlags = 0;
        switch (this.progressState) {
            case 0: {
                tbpFlags = 2;
                break;
            }
            case 1: {
                tbpFlags = 4;
                break;
            }
            case 4: {
                tbpFlags = 8;
                break;
            }
            case 2: {
                tbpFlags = 1;
                break;
            }
        }
        this.parent.mTaskbarList3.SetProgressValue(this.shell.handle, (long)this.progress, 100L);
        this.parent.mTaskbarList3.SetProgressState(this.shell.handle, tbpFlags);
    }
    
    void updateText() {
        this.showingText = true;
        final int width = 16;
        final int height = 16;
        final long hdc = OS.GetDC(0L);
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biWidth = 16;
        bmiHeader.biHeight = -16;
        bmiHeader.biPlanes = 1;
        bmiHeader.biBitCount = 32;
        bmiHeader.biCompression = 0;
        final byte[] bmi = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
        final long[] pBits = { 0L };
        final long hBitmap = OS.CreateDIBSection(0L, bmi, 0, pBits, 0L, 0);
        if (hBitmap == 0L) {
            this.error(2);
        }
        final long dstHdc = OS.CreateCompatibleDC(hdc);
        final long oldBitmap = OS.SelectObject(dstHdc, hBitmap);
        final long hMask = OS.CreateBitmap(16, 16, 1, 1, (byte[])null);
        if (hMask == 0L) {
            this.error(2);
        }
        final long maskHdc = OS.CreateCompatibleDC(hdc);
        final long oldMask = OS.SelectObject(maskHdc, hMask);
        OS.PatBlt(maskHdc, 0, 0, 16, 16, 16711778);
        long oldBrush = OS.SelectObject(maskHdc, OS.GetStockObject(4));
        OS.RoundRect(maskHdc, 0, 0, 16, 16, 8, 8);
        OS.SelectObject(maskHdc, oldBrush);
        final long brush = OS.CreateSolidBrush(OS.GetSysColor(13));
        oldBrush = OS.SelectObject(dstHdc, brush);
        OS.RoundRect(dstHdc, 0, 0, 16, 16, 8, 8);
        OS.SelectObject(dstHdc, oldBrush);
        OS.DeleteObject(brush);
        final int uFormat = 2080;
        final RECT rect = new RECT();
        final char[] buffer = this.overlayText.toCharArray();
        final int length = buffer.length;
        long hFont = 0L;
        long oldHFont = 0L;
        final NONCLIENTMETRICS info = new NONCLIENTMETRICS();
        info.cbSize = NONCLIENTMETRICS.sizeof;
        if (OS.SystemParametersInfo(41, 0, info, 0)) {
            final LOGFONT logFont = info.lfMessageFont;
            logFont.lfHeight = -10;
            hFont = OS.CreateFontIndirect(logFont);
            oldHFont = OS.SelectObject(dstHdc, hFont);
            OS.DrawText(dstHdc, buffer, length, rect, 3104);
            if (rect.right > 14) {
                OS.SelectObject(dstHdc, oldHFont);
                OS.DeleteObject(hFont);
                logFont.lfHeight = -8;
                hFont = OS.CreateFontIndirect(logFont);
                OS.SelectObject(dstHdc, hFont);
            }
        }
        OS.DrawText(dstHdc, buffer, length, rect, 3104);
        OS.OffsetRect(rect, (16 - rect.right) / 2, (16 - rect.bottom) / 2);
        final int oldBkMode = OS.SetBkMode(dstHdc, 1);
        OS.SetTextColor(dstHdc, OS.GetSysColor(14));
        OS.DrawText(dstHdc, buffer, length, rect, 2080);
        if (hFont != 0L) {
            OS.SelectObject(dstHdc, oldHFont);
            OS.DeleteObject(hFont);
        }
        OS.SetBkMode(dstHdc, oldBkMode);
        OS.SelectObject(dstHdc, oldBitmap);
        OS.DeleteDC(dstHdc);
        OS.SelectObject(maskHdc, oldMask);
        OS.DeleteDC(maskHdc);
        OS.ReleaseDC(0L, hdc);
        final ICONINFO iconInfo = new ICONINFO();
        iconInfo.fIcon = true;
        iconInfo.hbmColor = hBitmap;
        iconInfo.hbmMask = hMask;
        final long hIcon = OS.CreateIconIndirect(iconInfo);
        if (hIcon == 0L) {
            this.error(2);
        }
        OS.DeleteObject(hBitmap);
        OS.DeleteObject(hMask);
        this.parent.mTaskbarList3.SetOverlayIcon(this.shell.handle, hIcon, 0L);
        OS.DestroyIcon(hIcon);
    }
}
