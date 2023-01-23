//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.*;

public class TransferData
{
    public int type;
    public FORMATETC formatetc;
    public STGMEDIUM stgmedium;
    public int result;
    public long pIDataObject;
    
    public TransferData() {
        this.result = -2147467259;
    }
    
    static boolean sameType(final TransferData data1, final TransferData data2) {
        return data1 == data2 || (data1 != null && data2 != null && data1.type == data2.type && data1.formatetc.cfFormat == data2.formatetc.cfFormat && data1.formatetc.dwAspect == data2.formatetc.dwAspect && data1.formatetc.tymed == data2.formatetc.tymed);
    }
}
