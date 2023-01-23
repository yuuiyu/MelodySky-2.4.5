//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.ole.win32.*;

public class TextTransfer extends ByteArrayTransfer
{
    private static TextTransfer _instance;
    private static final String CF_UNICODETEXT = "CF_UNICODETEXT";
    private static final String CF_TEXT = "CF_TEXT";
    private static final int CF_UNICODETEXTID = 13;
    private static final int CF_TEXTID = 1;
    
    private TextTransfer() {
    }
    
    public static TextTransfer getInstance() {
        return TextTransfer._instance;
    }
    
    public void javaToNative(final Object object, final TransferData transferData) {
        if (!this.checkText(object) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        transferData.result = -2147467259;
        final String string = (String)object;
        switch (transferData.type) {
            case 13: {
                final int charCount = string.length();
                final char[] chars = new char[charCount + 1];
                string.getChars(0, charCount, chars, 0);
                final int byteCount = chars.length * 2;
                final long newPtr = OS.GlobalAlloc(64, byteCount);
                OS.MoveMemory(newPtr, chars, byteCount);
                transferData.stgmedium = new STGMEDIUM();
                transferData.stgmedium.tymed = 1;
                transferData.stgmedium.unionField = newPtr;
                transferData.stgmedium.pUnkForRelease = 0L;
                transferData.result = 0;
                break;
            }
            case 1: {
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
                break;
            }
        }
    }
    
    public Object nativeToJava(final TransferData transferData) {
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
        try {
            switch (transferData.type) {
                case 13: {
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
                case 1: {
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
                    break;
                }
            }
        }
        finally {
            OS.GlobalFree(hMem);
        }
        return null;
    }
    
    protected int[] getTypeIds() {
        return new int[] { 13, 1 };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "CF_UNICODETEXT", "CF_TEXT" };
    }
    
    boolean checkText(final Object object) {
        return object instanceof String && ((String)object).length() > 0;
    }
    
    protected boolean validate(final Object object) {
        return this.checkText(object);
    }
    
    static {
        TextTransfer._instance = new TextTransfer();
    }
}
