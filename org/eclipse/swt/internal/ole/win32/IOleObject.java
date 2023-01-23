//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class IOleObject extends IUnknown
{
    public IOleObject(final long address) {
        super(address);
    }
    
    public int Advise(final long pAdvSink, final int[] pdwConnection) {
        return COM.VtblCall(19, this.address, pAdvSink, pdwConnection);
    }
    
    public int Close(final int dwSaveOption) {
        return COM.VtblCall(6, this.address, dwSaveOption);
    }
    
    public int DoVerb(final int iVerb, final MSG lpmsg, final long pActiveSite, final int lindex, final long hwndParent, final RECT lprcPosRect) {
        return COM.VtblCall(11, this.address, iVerb, lpmsg, pActiveSite, lindex, hwndParent, lprcPosRect);
    }
    
    public int GetClientSite(final long[] ppvClientSite) {
        return COM.VtblCall(4, this.address, ppvClientSite);
    }
    
    public int GetExtent(final int dwDrawAspect, final SIZE pSizel) {
        return COM.VtblCall(18, this.address, dwDrawAspect, pSizel);
    }
    
    public int SetClientSite(final long pClientSite) {
        return COM.VtblCall(3, this.address, pClientSite);
    }
    
    public int SetExtent(final int dwDrawAspect, final SIZE pSizel) {
        return COM.VtblCall(17, this.address, dwDrawAspect, pSizel);
    }
    
    public int SetHostNames(final String szContainerApp, final String szContainerObj) {
        char[] buffer1 = null;
        if (szContainerApp != null) {
            final int count1 = szContainerApp.length();
            buffer1 = new char[count1 + 1];
            szContainerApp.getChars(0, count1, buffer1, 0);
        }
        char[] buffer2 = null;
        if (szContainerObj != null) {
            final int count2 = szContainerObj.length();
            buffer2 = new char[count2 + 1];
            szContainerObj.getChars(0, count2, buffer2, 0);
        }
        return COM.VtblCall(5, this.address, buffer1, buffer2);
    }
    
    public int Unadvise(final int token) {
        return COM.VtblCall(20, this.address, token);
    }
    
    public int Update() {
        return COM.VtblCall(13, this.address);
    }
}
