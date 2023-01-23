//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2Deferral extends IUnknown
{
    public ICoreWebView2Deferral(final long address) {
        super(address);
    }
    
    public int Complete() {
        return COM.VtblCall(3, this.address);
    }
}
