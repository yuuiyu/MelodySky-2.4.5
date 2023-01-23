//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IOleWindow extends IUnknown
{
    public IOleWindow(final long address) {
        super(address);
    }
    
    public int GetWindow(final long[] phwnd) {
        return COM.VtblCall(3, this.address, phwnd);
    }
}
