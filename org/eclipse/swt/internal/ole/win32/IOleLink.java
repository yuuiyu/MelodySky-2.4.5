//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IOleLink extends IUnknown
{
    public IOleLink(final long address) {
        super(address);
    }
    
    public int BindIfRunning() {
        return COM.VtblCall(10, this.address);
    }
    
    public int GetSourceMoniker(final long[] ppmk) {
        return COM.VtblCall(6, this.address, ppmk);
    }
}
