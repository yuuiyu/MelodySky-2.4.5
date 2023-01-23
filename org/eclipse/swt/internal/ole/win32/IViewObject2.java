//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class IViewObject2 extends IUnknown
{
    public IViewObject2(final long address) {
        super(address);
    }
    
    public int GetExtent(final int dwAspect, final int lindex, final long ptd, final SIZE lpsizel) {
        return COM.VtblCall(9, this.address, dwAspect, lindex, ptd, lpsizel);
    }
    
    public int SetAdvise(final int dwAspects, final int dwAdvf, final long pIAdviseSink) {
        return COM.VtblCall(7, this.address, dwAspects, (long)dwAdvf, pIAdviseSink);
    }
}
