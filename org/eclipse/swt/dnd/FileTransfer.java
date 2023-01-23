//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.ole.win32.*;

public class FileTransfer extends ByteArrayTransfer
{
    private static FileTransfer _instance;
    private static final String CF_HDROP = "CF_HDROP";
    private static final int CF_HDROPID = 15;
    private static final String CFSTR_SHELLIDLIST = "Shell IDList Array";
    private static final int CFSTR_SHELLIDLISTID;
    
    private FileTransfer() {
    }
    
    public static FileTransfer getInstance() {
        return FileTransfer._instance;
    }
    
    public void javaToNative(final Object object, final TransferData transferData) {
        if (!this.checkFile(object) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final String[] fileNames = (String[])object;
        long newPtr = 0L;
        if (transferData.type == 15) {
            final StringBuilder allFiles = new StringBuilder();
            for (final String fileName : fileNames) {
                allFiles.append(fileName);
                allFiles.append('\0');
            }
            allFiles.append('\0');
            final char[] buffer = new char[allFiles.length()];
            allFiles.getChars(0, allFiles.length(), buffer, 0);
            final DROPFILES dropfiles = new DROPFILES();
            dropfiles.pFiles = DROPFILES.sizeof;
            final DROPFILES dropfiles2 = dropfiles;
            final DROPFILES dropfiles3 = dropfiles;
            final int n = 0;
            dropfiles3.pt_y = 0;
            dropfiles2.pt_x = 0;
            dropfiles.fNC = 0;
            dropfiles.fWide = 1;
            final int byteCount = buffer.length * 2;
            newPtr = OS.GlobalAlloc(64, DROPFILES.sizeof + byteCount);
            if (newPtr != 0L) {
                OS.MoveMemory(newPtr, dropfiles, DROPFILES.sizeof);
                OS.MoveMemory(newPtr + DROPFILES.sizeof, buffer, byteCount);
            }
        }
        else if (transferData.type == FileTransfer.CFSTR_SHELLIDLISTID) {
            newPtr = this.generateCidaFromFilepaths(fileNames);
        }
        transferData.stgmedium = new STGMEDIUM();
        transferData.stgmedium.tymed = 1;
        transferData.stgmedium.unionField = newPtr;
        transferData.stgmedium.pUnkForRelease = 0L;
        transferData.result = ((newPtr != 0L) ? 0 : -2147467259);
    }
    
    public Object nativeToJava(final TransferData transferData) {
        if (!this.isSupportedType(transferData) || transferData.pIDataObject == 0L) {
            return null;
        }
        final IDataObject dataObject = new IDataObject(transferData.pIDataObject);
        dataObject.AddRef();
        final FORMATETC formatetc = new FORMATETC();
        formatetc.cfFormat = 15;
        formatetc.ptd = 0L;
        formatetc.dwAspect = 1;
        formatetc.lindex = -1;
        formatetc.tymed = 1;
        final STGMEDIUM stgmedium = new STGMEDIUM();
        stgmedium.tymed = 1;
        transferData.result = this.getData(dataObject, formatetc, stgmedium);
        dataObject.Release();
        if (transferData.result != 0) {
            return null;
        }
        final int count = OS.DragQueryFile(stgmedium.unionField, -1, null, 0);
        final String[] fileNames = new String[count];
        for (int i = 0; i < count; ++i) {
            final int size = OS.DragQueryFile(stgmedium.unionField, i, null, 0);
            final char[] lpszFile = new char[size + 1];
            OS.DragQueryFile(stgmedium.unionField, i, lpszFile, size + 1);
            fileNames[i] = new String(lpszFile, 0, size);
        }
        OS.DragFinish(stgmedium.unionField);
        return fileNames;
    }
    
    private long generateCidaFromFilepaths(final String[] fileNames) {
        final int n = fileNames.length;
        final long[] pidls = new long[n];
        try {
            final CIDA cida = new CIDA();
            cida.cidl = n;
            final int cidaSize = CIDA.sizeof + 4 * n;
            cida.aoffset = cidaSize;
            final int[] pidlOffsets = new int[n];
            final int[] pidlSizes = new int[n];
            int pidlSizeSum = 2;
            for (int i = 0; i < n; ++i) {
                final TCHAR szfileName = new TCHAR(0, fileNames[i], true);
                final long[] ppv = { 0L };
                final int hr = COM.PathToPIDL(szfileName.chars, ppv);
                if (hr != 0) {
                    return 0L;
                }
                pidls[i] = ppv[0];
                pidlSizes[i] = OS.ILGetSize(pidls[i]);
                pidlSizeSum += pidlSizes[i];
                if (i == 0) {
                    pidlOffsets[0] = cidaSize + 2;
                }
                else {
                    pidlOffsets[i] = pidlOffsets[i - 1] + pidlSizes[i - 1];
                }
            }
            final long newPtr = OS.GlobalAlloc(64, cidaSize + pidlSizeSum);
            if (newPtr != 0L) {
                OS.MoveMemory(newPtr, cida, CIDA.sizeof);
                OS.MoveMemory(newPtr + CIDA.sizeof, pidlOffsets, 4 * cida.cidl);
                for (int j = 0; j < n; ++j) {
                    OS.MoveMemory(newPtr + pidlOffsets[j], pidls[j], pidlSizes[j]);
                }
            }
            return newPtr;
        }
        finally {
            for (int k = 0; k < n; ++k) {
                if (pidls[k] != 0L) {
                    OS.CoTaskMemFree(pidls[k]);
                }
            }
        }
    }
    
    public boolean isSupportedType(final TransferData transferData) {
        return (transferData == null || transferData.pIDataObject == 0L || transferData.type != FileTransfer.CFSTR_SHELLIDLISTID) && super.isSupportedType(transferData);
    }
    
    protected int[] getTypeIds() {
        return new int[] { 15, FileTransfer.CFSTR_SHELLIDLISTID };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "CF_HDROP", "Shell IDList Array" };
    }
    
    boolean checkFile(final Object object) {
        if (object == null || !(object instanceof String[]) || ((String[])object).length == 0) {
            return false;
        }
        for (final String string : (String[])object) {
            if (string == null || string.length() == 0) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean validate(final Object object) {
        return this.checkFile(object);
    }
    
    static {
        FileTransfer._instance = new FileTransfer();
        CFSTR_SHELLIDLISTID = Transfer.registerType("Shell IDList Array");
    }
}
