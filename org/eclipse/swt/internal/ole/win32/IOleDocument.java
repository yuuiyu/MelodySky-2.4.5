//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IOleDocument extends IUnknown
{
    public IOleDocument(final long address) {
        super(address);
    }
    
    public int CreateView(final long pIPSite, final long pstm, final int dwReserved, final long[] ppView) {
        return COM.VtblCall(3, this.address, pIPSite, pstm, dwReserved, ppView);
    }
}
