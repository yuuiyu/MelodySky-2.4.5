//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IDataObject extends IUnknown
{
    public IDataObject(final long address) {
        super(address);
    }
    
    public int EnumFormatEtc(final int dwDirection, final long[] ppenumFormatetc) {
        return COM.VtblCall(8, this.address, dwDirection, ppenumFormatetc);
    }
    
    public int GetData(final FORMATETC pFormatetc, final STGMEDIUM pmedium) {
        return COM.VtblCall(3, this.address, pFormatetc, pmedium);
    }
    
    public int QueryGetData(final FORMATETC pFormatetc) {
        return COM.VtblCall(5, this.address, pFormatetc);
    }
}
