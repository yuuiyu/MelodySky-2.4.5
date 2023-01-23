//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ITfDisplayAttributeProvider extends IUnknown
{
    public ITfDisplayAttributeProvider(final long address) {
        super(address);
    }
    
    public int EnumDisplayAttributeInfo(final long[] ppEnum) {
        return COM.VtblCall(3, this.address, ppEnum);
    }
}
