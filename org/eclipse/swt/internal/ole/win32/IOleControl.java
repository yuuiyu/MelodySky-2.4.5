//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IOleControl extends IUnknown
{
    public IOleControl(final long address) {
        super(address);
    }
    
    public int GetControlInfo(final CONTROLINFO pCI) {
        return COM.VtblCall(3, this.address, pCI);
    }
}
