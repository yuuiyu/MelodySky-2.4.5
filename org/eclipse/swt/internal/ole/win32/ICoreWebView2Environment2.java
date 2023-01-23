//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2Environment2 extends ICoreWebView2Environment
{
    public ICoreWebView2Environment2(final long address) {
        super(address);
    }
    
    public int CreateWebResourceRequest(final char[] uri, final char[] method, final IStream postData, final char[] headers, final long[] request) {
        return COM.VtblCall(8, this.address, uri, method, (postData != null) ? postData.address : 0L, headers, request);
    }
}
