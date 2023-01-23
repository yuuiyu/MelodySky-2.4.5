//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IShellLink extends IUnknown
{
    public IShellLink(final long address) {
        super(address);
    }
    
    public int SetDescription(final char[] pszName) {
        return COM.VtblCall(7, this.address, pszName);
    }
    
    public int SetArguments(final char[] pszArgs) {
        return COM.VtblCall(11, this.address, pszArgs);
    }
    
    public int SetIconLocation(final char[] pszIconPath, final int iIcon) {
        return COM.VtblCall(17, this.address, pszIconPath, iIcon);
    }
    
    public int SetPath(final char[] pszFile) {
        return COM.VtblCall(20, this.address, pszFile);
    }
}
