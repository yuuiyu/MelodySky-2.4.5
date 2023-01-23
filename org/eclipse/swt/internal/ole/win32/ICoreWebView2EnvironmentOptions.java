//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2EnvironmentOptions extends IUnknown
{
    public ICoreWebView2EnvironmentOptions(final long address) {
        super(address);
    }
    
    public int put_AdditionalBrowserArguments(final char[] value) {
        return COM.VtblCall(4, this.address, value);
    }
    
    public int put_Language(final char[] value) {
        return COM.VtblCall(6, this.address, value);
    }
    
    public int put_TargetCompatibleBrowserVersion(final char[] value) {
        return COM.VtblCall(8, this.address, value);
    }
    
    public int put_AllowSingleSignOnUsingOSPrimaryAccount(final int[] allow) {
        return COM.VtblCall(10, this.address, allow);
    }
}
