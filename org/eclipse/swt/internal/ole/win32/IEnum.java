//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IEnum extends IUnknown
{
    public IEnum(final long address) {
        super(address);
    }
    
    public int Clone(final long[] ppenum) {
        return COM.VtblCall(6, this.address, ppenum);
    }
    
    public int Next(final int celt, final long rgelt, final int[] pceltFetched) {
        return COM.VtblCall(3, this.address, celt, rgelt, pceltFetched);
    }
    
    public int Reset() {
        return COM.VtblCall(5, this.address);
    }
    
    public int Skip(final int celt) {
        return COM.VtblCall(4, this.address, celt);
    }
}
