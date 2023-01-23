//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;

public class IDispatch extends IUnknown
{
    public IDispatch(final long address) {
        super(address);
    }
    
    public int GetIDsOfNames(final GUID riid, final String[] rgszNames, final int cNames, final int lcid, final int[] rgDispId) {
        final int size = rgszNames.length;
        final long hHeap = OS.GetProcessHeap();
        final long ppNames = OS.HeapAlloc(hHeap, 8, size * C.PTR_SIZEOF);
        final long[] memTracker = new long[size];
        try {
            for (int i = 0; i < size; ++i) {
                final int nameSize = rgszNames[i].length();
                final char[] buffer = new char[nameSize + 1];
                rgszNames[i].getChars(0, nameSize, buffer, 0);
                final long pName = OS.HeapAlloc(hHeap, 8, buffer.length * 2);
                OS.MoveMemory(pName, buffer, buffer.length * 2);
                OS.MoveMemory(ppNames + C.PTR_SIZEOF * i, new long[] { pName }, C.PTR_SIZEOF);
                memTracker[i] = pName;
            }
            return COM.VtblCall(5, this.address, new GUID(), ppNames, cNames, lcid, rgDispId);
        }
        finally {
            for (final long tracker : memTracker) {
                OS.HeapFree(hHeap, 0, tracker);
            }
            OS.HeapFree(hHeap, 0, ppNames);
        }
    }
    
    public int GetTypeInfo(final int iTInfo, final int lcid, final long[] ppTInfo) {
        return COM.VtblCall(4, this.address, (long)iTInfo, lcid, ppTInfo);
    }
    
    public int Invoke(final int dispIdMember, final GUID riid, final int lcid, final int dwFlags, final DISPPARAMS pDispParams, final long pVarResult, final EXCEPINFO pExcepInfo, final int[] pArgErr) {
        return COM.VtblCall(6, this.address, dispIdMember, riid, lcid, dwFlags, pDispParams, pVarResult, pExcepInfo, pArgErr);
    }
}
