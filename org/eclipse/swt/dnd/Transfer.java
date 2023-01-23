//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.win32.*;

public abstract class Transfer
{
    private static final int RETRY_LIMIT = 10;
    
    int getData(final IDataObject dataObject, final FORMATETC pFormatetc, final STGMEDIUM pmedium) {
        if (dataObject.GetData(pFormatetc, pmedium) == 0) {
            return 0;
        }
        try {
            Thread.sleep(50L);
        }
        catch (Throwable t) {}
        int result = dataObject.GetData(pFormatetc, pmedium);
        for (int retryCount = 0; result != 0 && retryCount++ < 10; result = dataObject.GetData(pFormatetc, pmedium)) {
            final MSG msg = new MSG();
            OS.PeekMessage(msg, 0L, 0, 0, 2);
            try {
                Thread.sleep(50L);
            }
            catch (Throwable t2) {}
        }
        return result;
    }
    
    public abstract TransferData[] getSupportedTypes();
    
    public abstract boolean isSupportedType(final TransferData p0);
    
    protected abstract int[] getTypeIds();
    
    protected abstract String[] getTypeNames();
    
    protected abstract void javaToNative(final Object p0, final TransferData p1);
    
    protected abstract Object nativeToJava(final TransferData p0);
    
    public static int registerType(final String formatName) {
        final TCHAR chFormatName = new TCHAR(0, formatName, true);
        return OS.RegisterClipboardFormat(chFormatName);
    }
    
    protected boolean validate(final Object object) {
        return true;
    }
}
