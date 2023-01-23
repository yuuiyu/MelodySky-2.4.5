//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class ColorDialog extends Dialog
{
    static final int CUSTOM_COLOR_COUNT = 16;
    RGB rgb;
    RGB[] rgbs;
    int[] colors;
    
    public ColorDialog(final Shell parent) {
        this(parent, 65536);
    }
    
    public ColorDialog(final Shell parent, final int style) {
        super(parent, Dialog.checkStyle(parent, style));
        this.colors = new int[16];
        this.checkSubclass();
    }
    
    long CCHookProc(final long hdlg, final long uiMsg, final long lParam, final long lpData) {
        switch ((int)uiMsg) {
            case 272: {
                if (this.title != null && this.title.length() != 0) {
                    final TCHAR buffer = new TCHAR(0, this.title, true);
                    OS.SetWindowText(hdlg, buffer);
                    break;
                }
                break;
            }
        }
        return 0L;
    }
    
    public RGB getRGB() {
        return this.rgb;
    }
    
    public RGB[] getRGBs() {
        return this.rgbs;
    }
    
    public RGB open() {
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
        final Callback callback = new Callback((Object)this, "CCHookProc", 4);
        final long lpfnHook = callback.getAddress();
        final Display display = this.parent.display;
        if (display.lpCustColors == 0L) {
            final long hHeap = OS.GetProcessHeap();
            display.lpCustColors = OS.HeapAlloc(hHeap, 8, 64);
            for (int i = 0; i < 16; ++i) {
                this.colors[i] = 16777215;
            }
            OS.MoveMemory(display.lpCustColors, this.colors, 64);
        }
        if (this.rgbs != null) {
            final int length = (this.rgbs.length > 16) ? 16 : this.rgbs.length;
            for (int j = 0; j < length; ++j) {
                final RGB rgb = this.rgbs[j];
                final int red = rgb.red & 0xFF;
                final int green = rgb.green << 8 & 0xFF00;
                final int blue = rgb.blue << 16 & 0xFF0000;
                this.colors[j] = (red | green | blue);
            }
            for (int j = length; j < 16; ++j) {
                this.colors[j] = 16777215;
            }
            OS.MoveMemory(display.lpCustColors, this.colors, 64);
        }
        final CHOOSECOLOR lpcc = new CHOOSECOLOR();
        lpcc.lStructSize = CHOOSECOLOR.sizeof;
        lpcc.Flags = 272;
        lpcc.lpfnHook = lpfnHook;
        lpcc.hwndOwner = hwndOwner;
        lpcc.lpCustColors = display.lpCustColors;
        if (this.rgb != null) {
            final CHOOSECOLOR choosecolor2;
            final CHOOSECOLOR choosecolor = choosecolor2 = lpcc;
            choosecolor2.Flags |= 0x1;
            final int red2 = this.rgb.red & 0xFF;
            final int green2 = this.rgb.green << 8 & 0xFF00;
            final int blue2 = this.rgb.blue << 16 & 0xFF0000;
            lpcc.rgbResult = (red2 | green2 | blue2);
        }
        Dialog oldModal = null;
        if ((this.style & 0x30000) != 0x0) {
            oldModal = display.getModalDialog();
            display.setModalDialog(this);
        }
        display.externalEventLoop = true;
        display.sendPreExternalEventDispatchEvent();
        final boolean success = OS.ChooseColor(lpcc);
        display.externalEventLoop = false;
        display.sendPostExternalEventDispatchEvent();
        if ((this.style & 0x30000) != 0x0) {
            display.setModalDialog(oldModal);
        }
        boolean customColor = false;
        OS.MoveMemory(this.colors, display.lpCustColors, this.colors.length * 4);
        for (final int color : this.colors) {
            if (color != 16777215) {
                customColor = true;
                break;
            }
        }
        if (customColor) {
            this.rgbs = new RGB[16];
            for (int k = 0; k < this.colors.length; ++k) {
                final int color2 = this.colors[k];
                final int red3 = color2 & 0xFF;
                final int green3 = color2 >> 8 & 0xFF;
                final int blue3 = color2 >> 16 & 0xFF;
                this.rgbs[k] = new RGB(red3, green3, blue3);
            }
        }
        if (success) {
            final int red4 = lpcc.rgbResult & 0xFF;
            final int green4 = lpcc.rgbResult >> 8 & 0xFF;
            final int blue4 = lpcc.rgbResult >> 16 & 0xFF;
            this.rgb = new RGB(red4, green4, blue4);
        }
        callback.dispose();
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
        return this.rgb;
    }
    
    public void setRGB(final RGB rgb) {
        this.rgb = rgb;
    }
    
    public void setRGBs(final RGB[] rgbs) {
        if (rgbs != null) {
            for (final RGB rgb : rgbs) {
                if (rgb == null) {
                    this.error(5);
                }
            }
        }
        this.rgbs = rgbs;
    }
}
