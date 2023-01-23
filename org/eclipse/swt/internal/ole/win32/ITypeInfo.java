//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class ITypeInfo extends IUnknown
{
    public ITypeInfo(final long address) {
        super(address);
    }
    
    public int GetDocumentation(final int index, final String[] name, final String[] docString, final int[] pdwHelpContext, final String[] helpFile) {
        long[] pBstrName = null;
        if (name != null) {
            pBstrName = new long[] { 0L };
        }
        long[] pBstrDocString = null;
        if (docString != null) {
            pBstrDocString = new long[] { 0L };
        }
        long[] pBstrHelpFile = null;
        if (helpFile != null) {
            pBstrHelpFile = new long[] { 0L };
        }
        final int rc = COM.VtblCall(12, this.address, index, pBstrName, pBstrDocString, pdwHelpContext, pBstrHelpFile);
        if (name != null && pBstrName[0] != 0L) {
            final int size = COM.SysStringByteLen(pBstrName[0]);
            if (size > 0) {
                final char[] buffer = new char[(size + 1) / 2];
                OS.MoveMemory(buffer, pBstrName[0], size);
                name[0] = new String(buffer);
                final int subindex = name[0].indexOf("\u0000");
                if (subindex > 0) {
                    name[0] = name[0].substring(0, subindex);
                }
            }
            COM.SysFreeString(pBstrName[0]);
        }
        if (docString != null && pBstrDocString[0] != 0L) {
            final int size = COM.SysStringByteLen(pBstrDocString[0]);
            if (size > 0) {
                final char[] buffer = new char[(size + 1) / 2];
                OS.MoveMemory(buffer, pBstrDocString[0], size);
                docString[0] = new String(buffer);
                final int subindex = docString[0].indexOf("\u0000");
                if (subindex > 0) {
                    docString[0] = docString[0].substring(0, subindex);
                }
            }
            COM.SysFreeString(pBstrDocString[0]);
        }
        if (helpFile != null && pBstrHelpFile[0] != 0L) {
            final int size = COM.SysStringByteLen(pBstrHelpFile[0]);
            if (size > 0) {
                final char[] buffer = new char[(size + 1) / 2];
                OS.MoveMemory(buffer, pBstrHelpFile[0], size);
                helpFile[0] = new String(buffer);
                final int subindex = helpFile[0].indexOf("\u0000");
                if (subindex > 0) {
                    helpFile[0] = helpFile[0].substring(0, subindex);
                }
            }
            COM.SysFreeString(pBstrHelpFile[0]);
        }
        return rc;
    }
    
    public int GetFuncDesc(final int index, final long[] ppFuncDesc) {
        return COM.VtblCall(5, this.address, index, ppFuncDesc);
    }
    
    public int GetImplTypeFlags(final int index, final int[] pImplTypeFlags) {
        return COM.VtblCall(9, this.address, index, pImplTypeFlags);
    }
    
    public int GetNames(final int memid, final String[] names, final int cMaxNames, final int[] pcNames) {
        final int nameSize = names.length;
        final long[] rgBstrNames = new long[nameSize];
        final int rc = COM.VtblCall(7, this.address, memid, rgBstrNames, nameSize, pcNames);
        if (rc == 0) {
            for (int i = 0; i < pcNames[0]; ++i) {
                final int size = COM.SysStringByteLen(rgBstrNames[i]);
                if (size > 0) {
                    final char[] buffer = new char[(size + 1) / 2];
                    OS.MoveMemory(buffer, rgBstrNames[i], size);
                    names[i] = new String(buffer);
                    final int subindex = names[i].indexOf("\u0000");
                    if (subindex > 0) {
                        names[i] = names[i].substring(0, subindex);
                    }
                }
                COM.SysFreeString(rgBstrNames[i]);
            }
        }
        return rc;
    }
    
    public int GetRefTypeInfo(final int hRefType, final long[] ppTInfo) {
        return COM.VtblCall(14, this.address, hRefType, ppTInfo);
    }
    
    public int GetRefTypeOfImplType(final int index, final int[] pRefType) {
        return COM.VtblCall(8, this.address, index, pRefType);
    }
    
    public int GetTypeAttr(final long[] ppTypeAttr) {
        return COM.VtblCall(3, this.address, ppTypeAttr);
    }
    
    public int GetVarDesc(final int index, final long[] ppVarDesc) {
        return COM.VtblCall(6, this.address, index, ppVarDesc);
    }
    
    public int ReleaseFuncDesc(final long pFuncDesc) {
        return COM.VtblCall(20, this.address, pFuncDesc);
    }
    
    public int ReleaseTypeAttr(final long pTypeAttr) {
        return COM.VtblCall(19, this.address, pTypeAttr);
    }
    
    public int ReleaseVarDesc(final long pVarDesc) {
        return COM.VtblCall(21, this.address, pVarDesc);
    }
}
