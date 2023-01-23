//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IShellItemArray extends IUnknown
{
    public IShellItemArray(final long address) {
        super(address);
    }
    
    public int GetCount(final int[] pdwNumItems) {
        return COM.VtblCall(7, this.address, pdwNumItems);
    }
    
    public int GetItemAt(final int dwIndex, final long[] ppsi) {
        return COM.VtblCall(8, this.address, dwIndex, ppsi);
    }
}
