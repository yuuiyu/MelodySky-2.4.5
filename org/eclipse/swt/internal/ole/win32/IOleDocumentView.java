//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class IOleDocumentView extends IUnknown
{
    public IOleDocumentView(final long address) {
        super(address);
    }
    
    public int SetInPlaceSite(final long pIPSite) {
        return COM.VtblCall(3, this.address, pIPSite);
    }
    
    public int SetRect(final RECT prcView) {
        return COM.VtblCall(6, this.address, prcView);
    }
    
    public int Show(final int fShow) {
        return COM.VtblCall(9, this.address, fShow);
    }
    
    public int UIActivate(final int fUIActivate) {
        return COM.VtblCall(10, this.address, fUIActivate);
    }
}
