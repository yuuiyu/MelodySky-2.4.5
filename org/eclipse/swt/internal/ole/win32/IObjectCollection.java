//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IObjectCollection extends IObjectArray
{
    public IObjectCollection(final long address) {
        super(address);
    }
    
    public int AddObject(final IUnknown punk) {
        return COM.VtblCall(5, this.address, punk.address);
    }
}
