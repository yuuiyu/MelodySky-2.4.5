//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class IOleInPlaceObject extends IOleWindow
{
    public IOleInPlaceObject(final long address) {
        super(address);
    }
    
    public int InPlaceDeactivate() {
        return COM.VtblCall(5, this.address);
    }
    
    public int UIDeactivate() {
        return COM.VtblCall(6, this.address);
    }
    
    public int SetObjectRects(final RECT lprcPosRect, final RECT lprcClipRect) {
        return COM.VtblCall(7, this.address, lprcPosRect, lprcClipRect);
    }
}
