//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.*;

public class MessageBox extends Dialog
{
    String message;
    
    public MessageBox(final Shell parent) {
        this(parent, 65570);
    }
    
    public MessageBox(final Shell parent, final int style) {
        super(parent, Dialog.checkStyle(parent, checkStyle(style)));
        this.message = "";
        this.checkSubclass();
    }
    
    static int checkStyle(int style) {
        final int mask = 4064;
        final int bits = style & 0xFE0;
        if (bits == 32 || bits == 256 || bits == 288) {
            return style;
        }
        if (bits == 64 || bits == 128 || bits == 192 || bits == 448) {
            return style;
        }
        if (bits == 1280 || bits == 3584) {
            return style;
        }
        style = ((style & 0xFFFFF01F) | 0x20);
        return style;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public int open() {
        int buttonBits = 0;
        if ((this.style & 0x20) == 0x20) {
            buttonBits = 0;
        }
        if ((this.style & 0x120) == 0x120) {
            buttonBits = 1;
        }
        if ((this.style & 0xC0) == 0xC0) {
            buttonBits = 4;
        }
        if ((this.style & 0x1C0) == 0x1C0) {
            buttonBits = 3;
        }
        if ((this.style & 0x500) == 0x500) {
            buttonBits = 5;
        }
        if ((this.style & 0xE00) == 0xE00) {
            buttonBits = 2;
        }
        if (buttonBits == 0) {
            buttonBits = 0;
        }
        int iconBits = 0;
        if ((this.style & 0x1) != 0x0) {
            iconBits = 16;
        }
        if ((this.style & 0x2) != 0x0) {
            iconBits = 64;
        }
        if ((this.style & 0x4) != 0x0) {
            iconBits = 32;
        }
        if ((this.style & 0x8) != 0x0) {
            iconBits = 48;
        }
        if ((this.style & 0x10) != 0x0) {
            iconBits = 64;
        }
        int modalBits = 0;
        if ((this.style & 0x8000) != 0x0) {
            modalBits = 0;
        }
        if ((this.style & 0x10000) != 0x0) {
            modalBits = 8192;
        }
        if ((this.style & 0x20000) != 0x0) {
            modalBits = 4096;
        }
        int bits = buttonBits | iconBits | modalBits;
        if ((this.style & 0x4000000) != 0x0) {
            bits |= 0x180000;
        }
        if ((this.style & 0x6000000) == 0x0 && this.parent != null && (this.parent.style & 0x8000000) != 0x0) {
            bits |= 0x180000;
        }
        if ((bits & 0x1000) != 0x0) {
            bits |= 0x2000;
            bits &= 0xFFFFEFFF;
            bits |= 0x40000;
        }
        final long hwndOwner = (this.parent != null) ? this.parent.handle : 0L;
        final Display display = (this.parent != null) ? this.parent.getDisplay() : Display.getCurrent();
        Dialog oldModal = null;
        if ((bits & 0x2000) != 0x0) {
            oldModal = display.getModalDialog();
            display.setModalDialog((Dialog)this);
        }
        display.sendPreExternalEventDispatchEvent();
        final TCHAR buffer1 = new TCHAR(0, this.message, true);
        final TCHAR buffer2 = new TCHAR(0, this.title, true);
        display.externalEventLoop = true;
        final int code = OS.MessageBox(hwndOwner, buffer1, buffer2, bits);
        display.externalEventLoop = false;
        display.sendPostExternalEventDispatchEvent();
        if ((bits & 0x2000) != 0x0) {
            display.setModalDialog(oldModal);
        }
        if (code != 0) {
            final int type = bits & 0xF;
            if (type == 0) {
                return 32;
            }
            if (type == 1) {
                return (code == 1) ? 32 : 256;
            }
            if (type == 4) {
                return (code == 6) ? 64 : 128;
            }
            if (type == 3) {
                if (code == 6) {
                    return 64;
                }
                if (code == 7) {
                    return 128;
                }
                return 256;
            }
            else {
                if (type == 5) {
                    return (code == 4) ? 1024 : 256;
                }
                if (type == 2) {
                    if (code == 4) {
                        return 1024;
                    }
                    if (code == 3) {
                        return 512;
                    }
                    return 2048;
                }
            }
        }
        return 256;
    }
    
    public void setMessage(final String string) {
        if (string == null) {
            this.error(4);
        }
        this.message = string;
    }
}
