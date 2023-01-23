//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IStream extends IUnknown
{
    public IStream(final long address) {
        super(address);
    }
    
    public int Commit(final int grfCommitFlags) {
        return COM.VtblCall(8, this.address, grfCommitFlags);
    }
    
    public int Read(final long pv, final int cb, final int[] pcbWritten) {
        return COM.VtblCall(3, this.address, pv, cb, pcbWritten);
    }
    
    public int Write(final long pv, final int cb, final int[] pcbWritten) {
        return COM.VtblCall(4, this.address, pv, cb, pcbWritten);
    }
}
