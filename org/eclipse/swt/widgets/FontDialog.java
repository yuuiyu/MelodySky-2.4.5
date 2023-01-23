//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class FontDialog extends Dialog
{
    FontData fontData;
    RGB rgb;
    boolean effectsVisible;
    
    public FontDialog(final Shell parent) {
        this(parent, 65536);
    }
    
    public FontDialog(final Shell parent, final int style) {
        super(parent, Dialog.checkStyle(parent, style));
        this.effectsVisible = true;
        this.checkSubclass();
    }
    
    public boolean getEffectsVisible() {
        return this.effectsVisible;
    }
    
    @Deprecated
    public FontData getFontData() {
        return this.fontData;
    }
    
    public FontData[] getFontList() {
        if (this.fontData == null) {
            return null;
        }
        final FontData[] result = { this.fontData };
        return result;
    }
    
    public RGB getRGB() {
        return this.rgb;
    }
    
    public FontData open() {
        long hwndOwner = this.parent.handle;
        final long hwndParent = this.parent.handle;
        boolean enabled = false;
        final int dialogOrientation = this.style & 0x6000000;
        final int parentOrientation = this.parent.style & 0x6000000;
        if (dialogOrientation != parentOrientation) {
            int exStyle = 1048576;
            if (dialogOrientation == 67108864) {
                exStyle |= 0x400000;
            }
            hwndOwner = OS.CreateWindowEx(exStyle, Shell.DialogClass, (TCHAR)null, 0, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, hwndParent, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
            enabled = OS.IsWindowEnabled(hwndParent);
            if (enabled) {
                OS.EnableWindow(hwndParent, false);
            }
        }
        final long hHeap = OS.GetProcessHeap();
        final CHOOSEFONT lpcf = new CHOOSEFONT();
        lpcf.lStructSize = CHOOSEFONT.sizeof;
        lpcf.hwndOwner = hwndOwner;
        lpcf.Flags = 1;
        if (this.effectsVisible) {
            final CHOOSEFONT choosefont3;
            final CHOOSEFONT choosefont = choosefont3 = lpcf;
            choosefont3.Flags |= 0x100;
        }
        final long lpLogFont = OS.HeapAlloc(hHeap, 8, LOGFONT.sizeof);
        if (this.fontData != null && this.fontData.data != null) {
            final LOGFONT logFont = this.fontData.data;
            final int lfHeight = logFont.lfHeight;
            final long hDC = OS.GetDC(0L);
            final int pixels = -(int)(0.5f + this.fontData.height * OS.GetDeviceCaps(hDC, 90) / 72.0f);
            OS.ReleaseDC(0L, hDC);
            logFont.lfHeight = pixels;
            final CHOOSEFONT choosefont4;
            final CHOOSEFONT choosefont2 = choosefont4 = lpcf;
            choosefont4.Flags |= 0x40;
            OS.MoveMemory(lpLogFont, logFont, LOGFONT.sizeof);
            logFont.lfHeight = lfHeight;
        }
        lpcf.lpLogFont = lpLogFont;
        if (this.rgb != null) {
            final int red = this.rgb.red & 0xFF;
            final int green = this.rgb.green << 8 & 0xFF00;
            final int blue = this.rgb.blue << 16 & 0xFF0000;
            lpcf.rgbColors = (red | green | blue);
        }
        Dialog oldModal = null;
        final Display display = this.parent.getDisplay();
        if ((this.style & 0x30000) != 0x0) {
            oldModal = display.getModalDialog();
            display.setModalDialog((Dialog)this);
        }
        display.externalEventLoop = true;
        display.sendPreExternalEventDispatchEvent();
        final boolean success = OS.ChooseFont(lpcf);
        display.externalEventLoop = false;
        display.sendPostExternalEventDispatchEvent();
        if ((this.style & 0x30000) != 0x0) {
            display.setModalDialog(oldModal);
        }
        if (success) {
            final LOGFONT logFont2 = new LOGFONT();
            OS.MoveMemory(logFont2, lpLogFont, LOGFONT.sizeof);
            final long hDC2 = OS.GetDC(0L);
            final int logPixelsY = OS.GetDeviceCaps(hDC2, 90);
            int pixels2 = 0;
            if (logFont2.lfHeight > 0) {
                final long hFont = OS.CreateFontIndirect(logFont2);
                final long oldFont = OS.SelectObject(hDC2, hFont);
                final TEXTMETRIC lptm = new TEXTMETRIC();
                OS.GetTextMetrics(hDC2, lptm);
                OS.SelectObject(hDC2, oldFont);
                OS.DeleteObject(hFont);
                pixels2 = logFont2.lfHeight - lptm.tmInternalLeading;
            }
            else {
                pixels2 = -logFont2.lfHeight;
            }
            OS.ReleaseDC(0L, hDC2);
            final float points = pixels2 * 72.0f / logPixelsY;
            this.fontData = FontData.win32_new(logFont2, points);
            if (this.effectsVisible) {
                final int red2 = lpcf.rgbColors & 0xFF;
                final int green2 = lpcf.rgbColors >> 8 & 0xFF;
                final int blue2 = lpcf.rgbColors >> 16 & 0xFF;
                this.rgb = new RGB(red2, green2, blue2);
            }
        }
        if (lpLogFont != 0L) {
            OS.HeapFree(hHeap, 0, lpLogFont);
        }
        if (hwndParent != hwndOwner) {
            if (enabled) {
                OS.EnableWindow(hwndParent, true);
            }
            OS.SetActiveWindow(hwndParent);
            OS.DestroyWindow(hwndOwner);
        }
        if (!success) {
            return null;
        }
        return this.fontData;
    }
    
    public void setEffectsVisible(final boolean visible) {
        this.effectsVisible = visible;
    }
    
    @Deprecated
    public void setFontData(final FontData fontData) {
        this.fontData = fontData;
    }
    
    public void setFontList(final FontData[] fontData) {
        if (fontData != null && fontData.length > 0) {
            this.fontData = fontData[0];
        }
        else {
            this.fontData = null;
        }
    }
    
    public void setRGB(final RGB rgb) {
        this.rgb = rgb;
    }
}
