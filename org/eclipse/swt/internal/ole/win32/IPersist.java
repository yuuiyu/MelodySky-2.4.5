//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IPersist extends IUnknown
{
    public IPersist(final long address) {
        super(address);
    }
    
    public int GetClassID(final GUID pClassID) {
        return COM.VtblCall(3, this.address, pClassID);
    }
}
