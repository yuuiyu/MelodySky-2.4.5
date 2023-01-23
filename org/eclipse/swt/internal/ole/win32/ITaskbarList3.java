//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ITaskbarList3 extends IUnknown
{
    public ITaskbarList3(final long address) {
        super(address);
    }
    
    public int SetProgressValue(final long hwnd, final long ullCompleted, final long ullTotal) {
        return COM.VtblCall(9, this.address, hwnd, ullCompleted, ullTotal);
    }
    
    public int SetProgressState(final long hwnd, final int tbpFlags) {
        return COM.VtblCall(10, this.address, hwnd, tbpFlags);
    }
    
    public int SetOverlayIcon(final long hwnd, final long hIcon, final long pszDescription) {
        return COM.VtblCall(18, this.address, hwnd, hIcon, pszDescription);
    }
}
