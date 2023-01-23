//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2WindowFeatures extends IUnknown
{
    public ICoreWebView2WindowFeatures(final long address) {
        super(address);
    }
    
    public int get_HasPosition(final int[] value) {
        return COM.VtblCall(3, this.address, value);
    }
    
    public int get_HasSize(final int[] value) {
        return COM.VtblCall(4, this.address, value);
    }
    
    public int get_Left(final int[] value) {
        return COM.VtblCall(5, this.address, value);
    }
    
    public int get_Top(final int[] value) {
        return COM.VtblCall(6, this.address, value);
    }
    
    public int get_Height(final int[] value) {
        return COM.VtblCall(7, this.address, value);
    }
    
    public int get_Width(final int[] value) {
        return COM.VtblCall(8, this.address, value);
    }
    
    public int get_ShouldDisplayMenuBar(final int[] value) {
        return COM.VtblCall(9, this.address, value);
    }
    
    public int get_ShouldDisplayStatus(final int[] value) {
        return COM.VtblCall(10, this.address, value);
    }
    
    public int get_ShouldDisplayToolbar(final int[] value) {
        return COM.VtblCall(11, this.address, value);
    }
    
    public int get_ShouldDisplayScrollBars(final int[] value) {
        return COM.VtblCall(12, this.address, value);
    }
}
