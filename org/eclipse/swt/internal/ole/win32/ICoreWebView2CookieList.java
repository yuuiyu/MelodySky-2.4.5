//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2CookieList extends IUnknown
{
    public ICoreWebView2CookieList(final long address) {
        super(address);
    }
    
    public int get_Count(final int[] count) {
        return COM.VtblCall(3, this.address, count);
    }
    
    public int GetValueAtIndex(final int index, final long[] cookie) {
        return COM.VtblCall(4, this.address, index, cookie);
    }
}
