//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IUnknown
{
    long address;
    
    public IUnknown(final long address) {
        this.address = address;
    }
    
    public int AddRef() {
        return COM.VtblCall(1, this.address);
    }
    
    public long getAddress() {
        return this.address;
    }
    
    public int QueryInterface(final GUID riid, final long[] ppvObject) {
        return COM.VtblCall(0, this.address, riid, ppvObject);
    }
    
    public int Release() {
        return COM.VtblCall(2, this.address);
    }
}
