//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IConnectionPoint extends IUnknown
{
    public IConnectionPoint(final long address) {
        super(address);
    }
    
    public int Advise(final long pUnk, final int[] pdwCookie) {
        return COM.VtblCall(5, this.address, pUnk, pdwCookie);
    }
    
    public int Unadvise(final int dwCookie) {
        return COM.VtblCall(6, this.address, dwCookie);
    }
}
