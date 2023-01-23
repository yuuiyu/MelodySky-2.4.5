//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IPersistFile extends IPersist
{
    public IPersistFile(final long address) {
        super(address);
    }
    
    public int IsDirty() {
        return COM.VtblCall(4, this.address);
    }
}
