//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IOleCommandTarget extends IUnknown
{
    public IOleCommandTarget(final long address) {
        super(address);
    }
    
    public int Exec(final GUID pguidCmdGroup, final int nCmdID, final int nCmdExecOpt, final long pvaIn, final long pvaOut) {
        return COM.VtblCall(4, this.address, pguidCmdGroup, nCmdID, nCmdExecOpt, pvaIn, pvaOut);
    }
    
    public int QueryStatus(final GUID pguidCmdGroup, final int cCmds, final OLECMD prgCmds, final long pCmdText) {
        if (cCmds > 1) {
            return -2147024809;
        }
        return COM.VtblCall(3, this.address, pguidCmdGroup, cCmds, prgCmds, pCmdText);
    }
}
