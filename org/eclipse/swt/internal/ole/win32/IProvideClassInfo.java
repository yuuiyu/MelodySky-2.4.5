//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IProvideClassInfo extends IUnknown
{
    public IProvideClassInfo(final long address) {
        super(address);
    }
    
    public int GetClassInfo(final long[] ppTI) {
        return COM.VtblCall(3, this.address, ppTI);
    }
}
