//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.ole.win32.*;

public class HTMLTransfer extends ByteArrayTransfer
{
    static HTMLTransfer _instance;
    static final String HTML_FORMAT = "HTML Format";
    static final int HTML_FORMATID;
    static final String NUMBER = "00000000";
    static final String HEADER = "Version:0.9\r\nStartHTML:00000000\r\nEndHTML:00000000\r\nStartFragment:00000000\r\nEndFragment:00000000\r\n";
    static final String PREFIX = "<html><body><!--StartFragment-->";
    static final String SUFFIX = "<!--EndFragment--></body></html>";
    static final String StartFragment = "StartFragment:";
    static final String EndFragment = "EndFragment:";
    
    private HTMLTransfer() {
    }
    
    public static HTMLTransfer getInstance() {
        return HTMLTransfer._instance;
    }
    
    public void javaToNative(final Object object, final TransferData transferData) {
        if (!this.checkHTML(object) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final String string = (String)object;
        int count = string.length();
        char[] chars = new char[count + 1];
        string.getChars(0, count, chars, 0);
        int cchMultiByte = OS.WideCharToMultiByte(65001, 0, chars, -1, null, 0, null, null);
        if (cchMultiByte == 0) {
            transferData.stgmedium = new STGMEDIUM();
            transferData.result = -2147221402;
            return;
        }
        final int startHTML = "Version:0.9\r\nStartHTML:00000000\r\nEndHTML:00000000\r\nStartFragment:00000000\r\nEndFragment:00000000\r\n".length();
        final int startFragment = startHTML + "<html><body><!--StartFragment-->".length();
        final int endFragment = startFragment + cchMultiByte - 1;
        final int endHTML = endFragment + "<!--EndFragment--></body></html>".length();
        final StringBuilder buffer = new StringBuilder("Version:0.9\r\nStartHTML:00000000\r\nEndHTML:00000000\r\nStartFragment:00000000\r\nEndFragment:00000000\r\n");
        final int maxLength = "00000000".length();
        int start = buffer.toString().indexOf("00000000");
        String temp = Integer.toString(startHTML);
        buffer.replace(start + maxLength - temp.length(), start + maxLength, temp);
        start = buffer.toString().indexOf("00000000", start);
        temp = Integer.toString(endHTML);
        buffer.replace(start + maxLength - temp.length(), start + maxLength, temp);
        start = buffer.toString().indexOf("00000000", start);
        temp = Integer.toString(startFragment);
        buffer.replace(start + maxLength - temp.length(), start + maxLength, temp);
        start = buffer.toString().indexOf("00000000", start);
        temp = Integer.toString(endFragment);
        buffer.replace(start + maxLength - temp.length(), start + maxLength, temp);
        buffer.append("<html><body><!--StartFragment-->");
        buffer.append(string);
        buffer.append("<!--EndFragment--></body></html>");
        count = buffer.length();
        chars = new char[count + 1];
        buffer.getChars(0, count, chars, 0);
        cchMultiByte = OS.WideCharToMultiByte(65001, 0, chars, -1, null, 0, null, null);
        final long lpMultiByteStr = OS.GlobalAlloc(64, cchMultiByte);
        OS.WideCharToMultiByte(65001, 0, chars, -1, lpMultiByteStr, cchMultiByte, null, null);
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
                int cchWideChar = OS.MultiByteToWideChar(65001, 0, lpMultiByteStr, -1, null, 0);
                if (cchWideChar == 0) {
                    return null;
                }
                final char[] lpWideCharStr = new char[cchWideChar - 1];
                OS.MultiByteToWideChar(65001, 0, lpMultiByteStr, -1, lpWideCharStr, lpWideCharStr.length);
                final String string = new String(lpWideCharStr);
                int fragmentStart = 0;
                int fragmentEnd = 0;
                int start = string.indexOf("StartFragment:") + "StartFragment:".length();
                int end = start + 1;
                while (end < string.length()) {
                    final String s = string.substring(start, end);
                    try {
                        fragmentStart = Integer.parseInt(s);
                        ++end;
                    }
                    catch (NumberFormatException e) {
                        break;
                    }
                }
                start = string.indexOf("EndFragment:") + "EndFragment:".length();
                end = start + 1;
                while (end < string.length()) {
                    final String s = string.substring(start, end);
                    try {
                        fragmentEnd = Integer.parseInt(s);
                        ++end;
                    }
                    catch (NumberFormatException e) {
                        break;
                    }
                }
                if (fragmentEnd <= fragmentStart || fragmentEnd > C.strlen(lpMultiByteStr)) {
                    return null;
                }
                cchWideChar = OS.MultiByteToWideChar(65001, 0, lpMultiByteStr + fragmentStart, fragmentEnd - fragmentStart, lpWideCharStr, lpWideCharStr.length);
                if (cchWideChar == 0) {
                    return null;
                }
                String s = new String(lpWideCharStr, 0, cchWideChar);
                final String foxStart = "<!--StartFragment -->\r\n";
                int prefix = s.indexOf("<!--StartFragment -->\r\n");
                if (prefix != -1) {
                    prefix += "<!--StartFragment -->\r\n".length();
                    s = s.substring(prefix);
                }
                return s;
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
        return new int[] { HTMLTransfer.HTML_FORMATID };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "HTML Format" };
    }
    
    boolean checkHTML(final Object object) {
        return object instanceof String && ((String)object).length() > 0;
    }
    
    protected boolean validate(final Object object) {
        return this.checkHTML(object);
    }
    
    static {
        HTMLTransfer._instance = new HTMLTransfer();
        HTML_FORMATID = Transfer.registerType("HTML Format");
    }
}
