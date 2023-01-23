//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.ole.win32.*;

public class RTFTransfer extends ByteArrayTransfer
{
    private static RTFTransfer _instance;
    private static final String CF_RTF = "Rich Text Format";
    private static final int CF_RTFID;
    
    private RTFTransfer() {
    }
    
    public static RTFTransfer getInstance() {
        return RTFTransfer._instance;
    }
    
    public void javaToNative(final Object object, final TransferData transferData) {
        if (!this.checkRTF(object) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final String string = (String)object;
        final int count = string.length();
        final char[] chars = new char[count + 1];
        string.getChars(0, count, chars, 0);
        final int codePage = OS.GetACP();
        final int cchMultiByte = OS.WideCharToMultiByte(codePage, 0, chars, -1, null, 0, null, null);
        if (cchMultiByte == 0) {
            transferData.stgmedium = new STGMEDIUM();
            transferData.result = -2147221402;
            return;
        }
        final long lpMultiByteStr = OS.GlobalAlloc(64, cchMultiByte);
        OS.WideCharToMultiByte(codePage, 0, chars, -1, lpMultiByteStr, cchMultiByte, null, null);
        transferData.stgmedium = new STGMEDIUM();
        transferData.stgmedium.tymed = 1;
        transferData.stgmedium.unionField = lpMultiByteStr;
        transferData.stgmedium.pUnkForRelease = 0L;
        transferData.result = 0;
    }
    
    public Object nativeToJava(final TransferData transferData) {
        if (!this.isSupportedType(transferData) || transferData.pIDataObject == 0L) {
            return null;
        }
        final IDataObject data = new IDataObject(transferData.pIDataObject);
        data.AddRef();
        final STGMEDIUM stgmedium = new STGMEDIUM();
        final FORMATETC formatetc = transferData.formatetc;
        stgmedium.tymed = 1;
        transferData.result = this.getData(data, formatetc, stgmedium);
        data.Release();
        if (transferData.result != 0) {
            return null;
        }
        final long hMem = stgmedium.unionField;
        try {
            final long lpMultiByteStr = OS.GlobalLock(hMem);
            if (lpMultiByteStr == 0L) {
                return null;
            }
            try {
                final int codePage = OS.GetACP();
                final int cchWideChar = OS.MultiByteToWideChar(codePage, 1, lpMultiByteStr, -1, null, 0);
                if (cchWideChar == 0) {
                    return null;
                }
                final char[] lpWideCharStr = new char[cchWideChar - 1];
                OS.MultiByteToWideChar(codePage, 1, lpMultiByteStr, -1, lpWideCharStr, lpWideCharStr.length);
                return new String(lpWideCharStr);
            }
            finally {
                OS.GlobalUnlock(hMem);
            }
        }
        finally {
            OS.GlobalFree(hMem);
        }
    }
    
    protected int[] getTypeIds() {
        return new int[] { RTFTransfer.CF_RTFID };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "Rich Text Format" };
    }
    
    boolean checkRTF(final Object object) {
        return object instanceof String && ((String)object).length() > 0;
    }
    
    protected boolean validate(final Object object) {
        return this.checkRTF(object);
    }
    
    static {
        RTFTransfer._instance = new RTFTransfer();
        CF_RTFID = Transfer.registerType("Rich Text Format");
    }
}
