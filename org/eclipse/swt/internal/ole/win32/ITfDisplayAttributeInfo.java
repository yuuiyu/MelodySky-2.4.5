//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class ITfDisplayAttributeInfo extends IUnknown
{
    public ITfDisplayAttributeInfo(final long address) {
        super(address);
    }
    
    public int GetAttributeInfo(final TF_DISPLAYATTRIBUTE pda) {
        return COM.VtblCall(5, this.address, pda);
    }
}
