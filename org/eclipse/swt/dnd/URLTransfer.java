//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.ole.win32.*;

public class URLTransfer extends ByteArrayTransfer
{
    static URLTransfer _instance;
    static final String CFSTR_INETURLW = "UniformResourceLocatorW";
    static final int CFSTR_INETURLIDW;
    static final String CFSTR_INETURL = "UniformResourceLocator";
    static final int CFSTR_INETURLID;
    
    private URLTransfer() {
    }
    
    public static URLTransfer getInstance() {
        return URLTransfer._instance;
    }
    
    public void javaToNative(final Object object, final TransferData transferData) {
        if (!this.checkURL(object) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        transferData.result = -2147467259;
        final String url = (String)object;
        if (transferData.type == URLTransfer.CFSTR_INETURLIDW) {
            final int charCount = url.length();
            final char[] chars = new char[charCount + 1];
            url.getChars(0, charCount, chars, 0);
            final int byteCount = chars.length * 2;
            final long newPtr = OS.GlobalAlloc(64, byteCount);
            OS.MoveMemory(newPtr, chars, byteCount);
            transferData.stgmedium = new STGMEDIUM();
            transferData.stgmedium.tymed = 1;
            transferData.stgmedium.unionField = newPtr;
            transferData.stgmedium.pUnkForRelease = 0L;
            transferData.result = 0;
        }
        else if (transferData.type == URLTransfer.CFSTR_INETURLID) {
            final int count = url.length();
            final char[] chars = new char[count + 1];
            url.getChars(0, count, chars, 0);
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
            if (transferData.type == URLTransfer.CFSTR_INETURLIDW) {
                final int size = OS.GlobalSize(hMem) / 2 * 2;
                if (size == 0) {
                    return null;
                }
                final char[] chars = new char[size / 2];
                final long ptr = OS.GlobalLock(hMem);
                if (ptr == 0L) {
                    return null;
                }
                try {
                    OS.MoveMemory(chars, ptr, size);
                    int length = chars.length;
                    for (int i = 0; i < chars.length; ++i) {
                        if (chars[i] == '\0') {
                            length = i;
                            break;
                        }
                    }
                    return new String(chars, 0, length);
                }
                finally {
                    OS.GlobalUnlock(hMem);
                }
            }
            if (transferData.type == URLTransfer.CFSTR_INETURLID) {
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
        }
        finally {
            OS.GlobalFree(hMem);
        }
        return null;
    }
    
    protected int[] getTypeIds() {
        return new int[] { URLTransfer.CFSTR_INETURLIDW, URLTransfer.CFSTR_INETURLID };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "UniformResourceLocatorW", "UniformResourceLocator" };
    }
    
    boolean checkURL(final Object object) {
        return object != null && object instanceof String && ((String)object).length() > 0;
    }
    
    protected boolean validate(final Object object) {
        return this.checkURL(object);
    }
    
    static {
        URLTransfer._instance = new URLTransfer();
        CFSTR_INETURLIDW = Transfer.registerType("UniformResourceLocatorW");
        CFSTR_INETURLID = Transfer.registerType("UniformResourceLocator");
    }
}
