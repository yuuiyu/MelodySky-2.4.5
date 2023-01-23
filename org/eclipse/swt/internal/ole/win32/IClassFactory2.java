//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IClassFactory2 extends IUnknown
{
    public IClassFactory2(final long address) {
        super(address);
    }
    
    public int CreateInstanceLic(final long pUnkOuter, final long pUnkReserved, final GUID riid, final long bstrKey, final long[] ppvObject) {
        return COM.VtblCall(7, this.address, pUnkOuter, pUnkReserved, riid, bstrKey, ppvObject);
    }
    
    public int GetLicInfo(final LICINFO licInfo) {
        return COM.VtblCall(5, this.address, licInfo);
    }
    
    public int RequestLicKey(final int dwReserved, final long[] pBstrKey) {
        return COM.VtblCall(6, this.address, dwReserved, pBstrKey);
    }
}
