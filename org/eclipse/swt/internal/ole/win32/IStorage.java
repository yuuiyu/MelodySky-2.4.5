//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IStorage extends IUnknown
{
    public IStorage(final long address) {
        super(address);
    }
    
    public int Commit(final int grfCommitFlag) {
        return COM.VtblCall(9, this.address, grfCommitFlag);
    }
    
    public int CopyTo(final int ciidExclude, final GUID rgiidExclude, final String[] snbExclude, final long pstgDest) {
        if (snbExclude != null) {
            return -2147024809;
        }
        return COM.VtblCall(7, this.address, ciidExclude, rgiidExclude, 0L, pstgDest);
    }
    
    public int CreateStream(final String pwcsName, final int grfMode, final int reserved1, final int reserved2, final long[] ppStm) {
        char[] buffer = null;
        if (pwcsName != null) {
            buffer = pwcsName.toCharArray();
        }
        return COM.VtblCall(3, this.address, buffer, grfMode, reserved1, reserved2, ppStm);
    }
    
    public int OpenStream(final String pwcsName, final long reserved1, final int grfMode, final int reserved2, final long[] ppStm) {
        char[] buffer = null;
        if (pwcsName != null) {
            buffer = pwcsName.toCharArray();
        }
        return COM.VtblCall(4, this.address, buffer, reserved1, grfMode, reserved2, ppStm);
    }
}
