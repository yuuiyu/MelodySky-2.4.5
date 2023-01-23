//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2MoveFocusRequestedEventArgs extends IUnknown
{
    public ICoreWebView2MoveFocusRequestedEventArgs(final long address) {
        super(address);
    }
    
    public int get_Reason(final int[] value) {
        return COM.VtblCall(3, this.address, value);
    }
    
    public int put_Handled(final boolean value) {
        return COM.VtblCall(5, this.address, (int)(value ? 1 : 0));
    }
}
