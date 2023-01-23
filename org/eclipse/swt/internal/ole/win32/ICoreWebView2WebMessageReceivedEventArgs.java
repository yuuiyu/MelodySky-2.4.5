//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2WebMessageReceivedEventArgs extends IUnknown
{
    public ICoreWebView2WebMessageReceivedEventArgs(final long address) {
        super(address);
    }
    
    public int get_Source(final long[] source) {
        return COM.VtblCall(3, this.address, source);
    }
    
    public int get_WebMessageAsJson(final long[] webMessageAsJson) {
        return COM.VtblCall(4, this.address, webMessageAsJson);
    }
}
