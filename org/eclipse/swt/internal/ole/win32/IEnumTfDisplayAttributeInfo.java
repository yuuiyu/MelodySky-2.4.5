//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IEnumTfDisplayAttributeInfo extends IUnknown
{
    public IEnumTfDisplayAttributeInfo(final long address) {
        super(address);
    }
    
    public int Next(final int celt, final long[] rgelt, final int[] pceltFetched) {
        return COM.VtblCall(4, this.address, celt, rgelt, pceltFetched);
    }
}
