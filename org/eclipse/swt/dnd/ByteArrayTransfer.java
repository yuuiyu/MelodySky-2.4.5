//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.ole.win32.*;

public abstract class ByteArrayTransfer extends Transfer
{
    @Override
    public TransferData[] getSupportedTypes() {
        final int[] types = this.getTypeIds();
        final TransferData[] data = new TransferData[types.length];
        for (int i = 0; i < types.length; ++i) {
            data[i] = new TransferData();
            data[i].type = types[i];
            data[i].formatetc = new FORMATETC();
            data[i].formatetc.cfFormat = types[i];
            data[i].formatetc.dwAspect = 1;
            data[i].formatetc.lindex = -1;
            data[i].formatetc.tymed = 1;
        }
        return data;
    }
    
    @Override
    public boolean isSupportedType(final TransferData transferData) {
        if (transferData == null) {
            return false;
        }
        for (final int type : this.getTypeIds()) {
            final FORMATETC format = transferData.formatetc;
            if (format.cfFormat == type && (format.dwAspect & 0x1) == 0x1 && (format.tymed & 0x1) == 0x1) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected void javaToNative(final Object object, final TransferData transferData) {
        if (!this.checkByteArray(object) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final byte[] data = (byte[])object;
        final int size = data.length;
        final long newPtr = OS.GlobalAlloc(64, size);
        OS.MoveMemory(newPtr, data, size);
        transferData.stgmedium = new STGMEDIUM();
        transferData.stgmedium.tymed = 1;
        transferData.stgmedium.unionField = newPtr;
        transferData.stgmedium.pUnkForRelease = 0L;
        transferData.result = 0;
    }
    
    @Override
    protected Object nativeToJava(final TransferData transferData) {
        if (!this.isSupportedType(transferData) || transferData.pIDataObject == 0L) {
            return null;
        }
        final IDataObject data = new IDataObject(transferData.pIDataObject);
        data.AddRef();
        final FORMATETC formatetc = transferData.formatetc;
        final STGMEDIUM stgmedium = new STGMEDIUM();
        stgmedium.tymed = 1;
        transferData.result = this.getData(data, formatetc, stgmedium);
        data.Release();
        if (transferData.result != 0) {
            return null;
        }
        final long hMem = stgmedium.unionField;
        final int size = OS.GlobalSize(hMem);
        final byte[] buffer = new byte[size];
        final long ptr = OS.GlobalLock(hMem);
        OS.MoveMemory(buffer, ptr, size);
        OS.GlobalUnlock(hMem);
        OS.GlobalFree(hMem);
        return buffer;
    }
    
    boolean checkByteArray(final Object object) {
        return object instanceof byte[] && ((byte[])object).length > 0;
    }
}
