//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class IOleInPlaceActiveObject extends IOleWindow
{
    public IOleInPlaceActiveObject(final long address) {
        super(address);
    }
    
    public int TranslateAccelerator(final MSG lpmsg) {
        return COM.VtblCall(5, this.address, lpmsg);
    }
    
    public void OnFrameWindowActivate(final boolean fActivate) {
        COM.VtblCall(6, this.address, (int)(fActivate ? 1 : 0));
    }
    
    public int ResizeBorder(final RECT prcBorder, final long pUIWindow, final boolean fFrameWindow) {
        return COM.VtblCall(8, this.address, prcBorder, pUIWindow, (int)(fFrameWindow ? 1 : 0));
    }
}
