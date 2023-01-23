//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;

public final class Font extends Resource
{
    public long handle;
    
    Font(final Device device) {
        super(device);
    }
    
    public Font(final Device device, final FontData fd) {
        super(device);
        this.init(fd);
        this.init();
    }
    
    public Font(final Device device, final FontData[] fds) {
        super(device);
        if (fds == null) {
            SWT.error(4);
        }
        if (fds.length == 0) {
            SWT.error(5);
        }
        for (final FontData fd : fds) {
            if (fd == null) {
                SWT.error(5);
            }
        }
        this.init(fds[0]);
        this.init();
    }
    
    public Font(final Device device, final String name, final int height, final int style) {
        super(device);
        if (name == null) {
            SWT.error(4);
        }
        this.init(new FontData(name, height, style));
        this.init();
    }
    
    Font(final Device device, final String name, final float height, final int style) {
        super(device);
        if (name == null) {
            SWT.error(4);
        }
        this.init(new FontData(name, height, style));
        this.init();
    }
    
    @Override
    void destroy() {
        OS.DeleteObject(this.handle);
        this.handle = 0L;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Font)) {
            return false;
        }
        final Font font = (Font)object;
        return this.device == font.device && this.handle == font.handle;
    }
    
    public FontData[] getFontData() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final LOGFONT logFont = new LOGFONT();
        OS.GetObject(this.handle, LOGFONT.sizeof, logFont);
        return new FontData[] { FontData.win32_new(logFont, this.device.computePoints(logFont, this.handle)) };
    }
    
    @Override
    public int hashCode() {
        return (int)this.handle;
    }
    
    void init(final FontData fd) {
        if (fd == null) {
            SWT.error(4);
        }
        final LOGFONT logFont = fd.data;
        final int lfHeight = logFont.lfHeight;
        logFont.lfHeight = this.device.computePixels(fd.height);
        this.handle = OS.CreateFontIndirect(logFont);
        logFont.lfHeight = lfHeight;
        if (this.handle == 0L) {
            SWT.error(2);
        }
    }
    
    @Override
    public boolean isDisposed() {
        return this.handle == 0L;
    }
    
    @Override
    public String toString() {
        if (this.isDisposed()) {
            return "Font {*DISPOSED*}";
        }
        return "Font {" + this.handle;
    }
    
    public static Font win32_new(final Device device, final long handle) {
        final Font font = new Font(device);
        font.handle = handle;
        font.ignoreNonDisposed();
        return font;
    }
}
