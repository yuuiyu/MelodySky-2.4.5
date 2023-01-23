//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IShellItem extends IUnknown
{
    public IShellItem(final long address) {
        super(address);
    }
    
    public int GetDisplayName(final int sigdnName, final long[] ppszName) {
        return COM.VtblCall(5, this.address, sigdnName, ppszName);
    }
}
