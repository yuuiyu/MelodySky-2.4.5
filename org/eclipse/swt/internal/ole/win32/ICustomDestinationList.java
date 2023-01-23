//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICustomDestinationList extends IUnknown
{
    public ICustomDestinationList(final long address) {
        super(address);
    }
    
    public int SetAppID(final char[] pszAppID) {
        return COM.VtblCall(3, this.address, pszAppID);
    }
    
    public int BeginList(final int[] pcMinSlots, final GUID riid, final long[] ppv) {
        return COM.VtblCall(4, this.address, pcMinSlots, riid, ppv);
    }
    
    public int AppendCategory(final char[] pszCategory, final IObjectArray poa) {
        return COM.VtblCall(5, this.address, pszCategory, poa.address);
    }
    
    public int AddUserTasks(final IUnknown poa) {
        return COM.VtblCall(7, this.address, poa.address);
    }
    
    public int CommitList() {
        return COM.VtblCall(8, this.address);
    }
    
    public int DeleteList(final char[] pszAppID) {
        return COM.VtblCall(10, this.address, pszAppID);
    }
}
