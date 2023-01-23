//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IServiceProvider extends IUnknown
{
    public IServiceProvider(final long address) {
        super(address);
    }
    
    public int QueryService(final GUID iid1, final GUID iid2, final long[] ppvObject) {
        return COM.VtblCall(3, this.address, iid1, iid2, ppvObject);
    }
}
