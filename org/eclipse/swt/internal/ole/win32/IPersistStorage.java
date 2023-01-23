//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IPersistStorage extends IPersist
{
    public IPersistStorage(final long address) {
        super(address);
    }
    
    public int InitNew(final long pStg) {
        return COM.VtblCall(5, this.address, pStg);
    }
    
    public int Load(final long pStg) {
        return COM.VtblCall(6, this.address, pStg);
    }
    
    public int Save(final long pStgSave, final boolean fSameAsLoad) {
        return COM.VtblCall(7, this.address, pStgSave, (int)(fSameAsLoad ? 1 : 0));
    }
    
    public int SaveCompleted(final long pStgNew) {
        return COM.VtblCall(8, this.address, pStgNew);
    }
    
    public int HandsOffStorage() {
        return COM.VtblCall(9, this.address);
    }
}
