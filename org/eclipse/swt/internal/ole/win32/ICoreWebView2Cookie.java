//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2Cookie extends IUnknown
{
    public ICoreWebView2Cookie(final long address) {
        super(address);
    }
    
    public int get_Name(final long[] name) {
        return COM.VtblCall(3, this.address, name);
    }
    
    public int get_Value(final long[] value) {
        return COM.VtblCall(4, this.address, value);
    }
    
    public int put_Value(final char[] value) {
        return COM.VtblCall(5, this.address, value);
    }
    
    public int put_Expires(final double expires) {
        return COM.VtblCall(9, this.address, expires);
    }
    
    public int put_IsHttpOnly(final boolean isHttpOnly) {
        return COM.VtblCall(11, this.address, (int)(isHttpOnly ? 1 : 0));
    }
    
    public int put_IsSecure(final boolean isSecure) {
        return COM.VtblCall(15, this.address, (int)(isSecure ? 1 : 0));
    }
    
    public int get_IsSession(final int[] isSession) {
        return COM.VtblCall(16, this.address, isSession);
    }
}
