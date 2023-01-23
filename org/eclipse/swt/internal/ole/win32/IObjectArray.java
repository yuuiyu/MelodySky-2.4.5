//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IObjectArray extends IUnknown
{
    public IObjectArray(final long address) {
        super(address);
    }
    
    public int GetCount(final int[] count) {
        return COM.VtblCall(3, this.address, count);
    }
}
