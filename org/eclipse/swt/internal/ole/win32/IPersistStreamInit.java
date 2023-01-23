//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IPersistStreamInit extends IPersist
{
    public IPersistStreamInit(final long address) {
        super(address);
    }
    
    public int Load(final long pStm) {
        return COM.VtblCall(5, this.address, pStm);
    }
    
    public int InitNew() {
        return COM.VtblCall(8, this.address);
    }
}
