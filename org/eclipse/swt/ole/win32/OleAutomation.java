//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.ole.win32.*;

public final class OleAutomation
{
    private IUnknown objIUnknown;
    private IDispatch objIDispatch;
    private String exceptionDescription;
    private ITypeInfo objITypeInfo;
    
    OleAutomation(final IDispatch idispatch) {
        if (idispatch == null) {
            OLE.error(1011);
        }
        (this.objIDispatch = idispatch).AddRef();
        final long[] ppv = { 0L };
        final int result = this.objIDispatch.GetTypeInfo(0, 2048, ppv);
        if (result == 0) {
            this.objITypeInfo = new ITypeInfo(ppv[0]);
        }
    }
    
    public OleAutomation(final OleClientSite clientSite) {
        if (clientSite == null) {
            OLE.error(1011);
        }
        this.objIDispatch = clientSite.getAutomationObject();
        final long[] ppv = { 0L };
        final int result = this.objIDispatch.GetTypeInfo(0, 2048, ppv);
        if (result == 0) {
            this.objITypeInfo = new ITypeInfo(ppv[0]);
        }
    }
    
    public OleAutomation(final String progId) {
        try {
            OS.OleInitialize(0L);
            final GUID appClsid = this.getClassID(progId);
            if (appClsid == null) {
                OS.OleUninitialize();
                OLE.error(1004);
            }
            final int flags = 5;
            final long[] ppvObject = { 0L };
            int result = COM.CoCreateInstance(appClsid, 0L, 5, COM.IIDIUnknown, ppvObject);
            if (result != 0) {
                OS.OleUninitialize();
                OLE.error(1001, result);
            }
            this.objIUnknown = new IUnknown(ppvObject[0]);
            ppvObject[0] = 0L;
            result = this.objIUnknown.QueryInterface(COM.IIDIDispatch, ppvObject);
            if (result != 0) {
                OLE.error(1003);
            }
            this.objIDispatch = new IDispatch(ppvObject[0]);
            ppvObject[0] = 0L;
            result = this.objIDispatch.GetTypeInfo(0, 2048, ppvObject);
            if (result == 0) {
                this.objITypeInfo = new ITypeInfo(ppvObject[0]);
            }
        }
        catch (SWTException e) {
            this.dispose();
            throw e;
        }
    }
    
    public void dispose() {
        if (this.objIDispatch != null) {
            this.objIDispatch.Release();
        }
        this.objIDispatch = null;
        if (this.objITypeInfo != null) {
            this.objITypeInfo.Release();
        }
        this.objITypeInfo = null;
        if (this.objIUnknown != null) {
            this.objIUnknown.Release();
            OS.OleUninitialize();
        }
        this.objIUnknown = null;
    }
    
    long getAddress() {
        return this.objIDispatch.getAddress();
    }
    
    GUID getClassID(final String clientName) {
        final GUID guid = new GUID();
        char[] buffer = null;
        if (clientName != null) {
            final int count = clientName.length();
            buffer = new char[count + 1];
            clientName.getChars(0, count, buffer, 0);
        }
        if (COM.CLSIDFromProgID(buffer, guid) != 0) {
            final int result = COM.CLSIDFromString(buffer, guid);
            if (result != 0) {
                return null;
            }
        }
        return guid;
    }
    
    public String getHelpFile(final int dispId) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final String[] file = { null };
        final int rc = this.objITypeInfo.GetDocumentation(dispId, (String[])null, (String[])null, (int[])null, file);
        if (rc == 0) {
            return file[0];
        }
        return null;
    }
    
    public String getDocumentation(final int dispId) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final String[] doc = { null };
        final int rc = this.objITypeInfo.GetDocumentation(dispId, (String[])null, doc, (int[])null, (String[])null);
        if (rc == 0) {
            return doc[0];
        }
        return null;
    }
    
    public OlePropertyDescription getPropertyDescription(final int index) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final long[] ppVarDesc = { 0L };
        final int rc = this.objITypeInfo.GetVarDesc(index, ppVarDesc);
        if (rc != 0) {
            return null;
        }
        final VARDESC vardesc = new VARDESC();
        COM.MoveMemory(vardesc, ppVarDesc[0], VARDESC.sizeof);
        final OlePropertyDescription data = new OlePropertyDescription();
        data.id = vardesc.memid;
        data.name = this.getName(vardesc.memid);
        data.type = vardesc.elemdescVar_tdesc_vt;
        if (data.type == 26) {
            final short[] vt = { 0 };
            OS.MoveMemory(vt, vardesc.elemdescVar_tdesc_union + C.PTR_SIZEOF, 2);
            data.type = vt[0];
        }
        data.flags = vardesc.wVarFlags;
        data.kind = vardesc.varkind;
        data.description = this.getDocumentation(vardesc.memid);
        data.helpFile = this.getHelpFile(vardesc.memid);
        this.objITypeInfo.ReleaseVarDesc(ppVarDesc[0]);
        return data;
    }
    
    public OleFunctionDescription getFunctionDescription(final int index) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final long[] ppFuncDesc = { 0L };
        final int rc = this.objITypeInfo.GetFuncDesc(index, ppFuncDesc);
        if (rc != 0) {
            return null;
        }
        final FUNCDESC funcdesc = new FUNCDESC();
        COM.MoveMemory(funcdesc, ppFuncDesc[0], FUNCDESC.sizeof);
        final OleFunctionDescription data = new OleFunctionDescription();
        data.id = funcdesc.memid;
        data.optionalArgCount = funcdesc.cParamsOpt;
        data.invokeKind = funcdesc.invkind;
        data.funcKind = funcdesc.funckind;
        data.flags = funcdesc.wFuncFlags;
        data.callingConvention = funcdesc.callconv;
        data.documentation = this.getDocumentation(funcdesc.memid);
        data.helpFile = this.getHelpFile(funcdesc.memid);
        final String[] names = this.getNames(funcdesc.memid, funcdesc.cParams + 1);
        if (names.length > 0) {
            data.name = names[0];
        }
        data.args = new OleParameterDescription[funcdesc.cParams];
        for (int i = 0; i < data.args.length; ++i) {
            data.args[i] = new OleParameterDescription();
            if (names.length > i + 1) {
                data.args[i].name = names[i + 1];
            }
            final short[] vt = { 0 };
            OS.MoveMemory(vt, funcdesc.lprgelemdescParam + i * COM.ELEMDESC_sizeof() + C.PTR_SIZEOF, 2);
            if (vt[0] == 26) {
                final long[] pTypedesc = { 0L };
                OS.MoveMemory(pTypedesc, funcdesc.lprgelemdescParam + i * COM.ELEMDESC_sizeof(), C.PTR_SIZEOF);
                final short[] vt2 = { 0 };
                OS.MoveMemory(vt2, pTypedesc[0] + C.PTR_SIZEOF, 2);
                vt[0] = (short)(vt2[0] | 0x4000);
            }
            data.args[i].type = vt[0];
            final short[] wParamFlags = { 0 };
            OS.MoveMemory(wParamFlags, funcdesc.lprgelemdescParam + i * COM.ELEMDESC_sizeof() + COM.TYPEDESC_sizeof() + C.PTR_SIZEOF, 2);
            data.args[i].flags = wParamFlags[0];
        }
        data.returnType = funcdesc.elemdescFunc_tdesc_vt;
        if (data.returnType == 26) {
            final short[] vt3 = { 0 };
            OS.MoveMemory(vt3, funcdesc.elemdescFunc_tdesc_union + C.PTR_SIZEOF, 2);
            data.returnType = vt3[0];
        }
        this.objITypeInfo.ReleaseFuncDesc(ppFuncDesc[0]);
        return data;
    }
    
    public TYPEATTR getTypeInfoAttributes() {
        if (this.objITypeInfo == null) {
            return null;
        }
        final long[] ppTypeAttr = { 0L };
        final int rc = this.objITypeInfo.GetTypeAttr(ppTypeAttr);
        if (rc != 0) {
            return null;
        }
        final TYPEATTR typeattr = new TYPEATTR();
        COM.MoveMemory(typeattr, ppTypeAttr[0], TYPEATTR.sizeof);
        this.objITypeInfo.ReleaseTypeAttr(ppTypeAttr[0]);
        return typeattr;
    }
    
    public String getName(final int dispId) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final String[] name = { null };
        final int rc = this.objITypeInfo.GetDocumentation(dispId, name, (String[])null, (int[])null, (String[])null);
        if (rc == 0) {
            return name[0];
        }
        return null;
    }
    
    public String[] getNames(final int dispId, final int maxSize) {
        if (this.objITypeInfo == null) {
            return new String[0];
        }
        final String[] names = new String[maxSize];
        final int[] count = { 0 };
        final int rc = this.objITypeInfo.GetNames(dispId, names, maxSize, count);
        if (rc == 0) {
            final String[] newNames = new String[count[0]];
            System.arraycopy(names, 0, newNames, 0, count[0]);
            return newNames;
        }
        return new String[0];
    }
    
    public int[] getIDsOfNames(final String[] names) {
        final int[] rgdispid = new int[names.length];
        final int result = this.objIDispatch.GetIDsOfNames(new GUID(), names, names.length, 2048, rgdispid);
        if (result != 0) {
            return null;
        }
        return rgdispid;
    }
    
    public String getLastError() {
        return this.exceptionDescription;
    }
    
    public Variant getProperty(final int dispIdMember) {
        final Variant pVarResult = new Variant();
        final int result = this.invoke(dispIdMember, 2, null, null, pVarResult);
        return (result == 0) ? pVarResult : null;
    }
    
    public Variant getProperty(final int dispIdMember, final Variant[] rgvarg) {
        final Variant pVarResult = new Variant();
        final int result = this.invoke(dispIdMember, 2, rgvarg, null, pVarResult);
        return (result == 0) ? pVarResult : null;
    }
    
    public Variant getProperty(final int dispIdMember, final Variant[] rgvarg, final int[] rgdispidNamedArgs) {
        final Variant pVarResult = new Variant();
        final int result = this.invoke(dispIdMember, 2, rgvarg, rgdispidNamedArgs, pVarResult);
        return (result == 0) ? pVarResult : null;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof OleAutomation)) {
            return false;
        }
        if (this.objIDispatch == null) {
            return false;
        }
        final OleAutomation oleAutomation = (OleAutomation)object;
        if (oleAutomation.objIDispatch == null) {
            return false;
        }
        final long address1 = this.objIDispatch.getAddress();
        final long address2 = oleAutomation.objIDispatch.getAddress();
        return address1 == address2;
    }
    
    public Variant invoke(final int dispIdMember) {
        final Variant pVarResult = new Variant();
        final int result = this.invoke(dispIdMember, 1, null, null, pVarResult);
        return (result == 0) ? pVarResult : null;
    }
    
    public Variant invoke(final int dispIdMember, final Variant[] rgvarg) {
        final Variant pVarResult = new Variant();
        final int result = this.invoke(dispIdMember, 1, rgvarg, null, pVarResult);
        return (result == 0) ? pVarResult : null;
    }
    
    public Variant invoke(final int dispIdMember, final Variant[] rgvarg, final int[] rgdispidNamedArgs) {
        final Variant pVarResult = new Variant();
        final int result = this.invoke(dispIdMember, 1, rgvarg, rgdispidNamedArgs, pVarResult);
        return (result == 0) ? pVarResult : null;
    }
    
    private int invoke(final int dispIdMember, final int wFlags, final Variant[] rgvarg, final int[] rgdispidNamedArgs, final Variant pVarResult) {
        if (this.objIDispatch == null) {
            return -2147467259;
        }
        final DISPPARAMS pDispParams = new DISPPARAMS();
        if (rgvarg != null && rgvarg.length > 0) {
            pDispParams.cArgs = rgvarg.length;
            pDispParams.rgvarg = OS.GlobalAlloc(64, VARIANT.sizeof * rgvarg.length);
            int offset = 0;
            for (int i = rgvarg.length - 1; i >= 0; --i) {
                rgvarg[i].getData(pDispParams.rgvarg + offset);
                offset += VARIANT.sizeof;
            }
        }
        if (rgdispidNamedArgs != null && rgdispidNamedArgs.length > 0) {
            pDispParams.cNamedArgs = rgdispidNamedArgs.length;
            pDispParams.rgdispidNamedArgs = OS.GlobalAlloc(64, 4 * rgdispidNamedArgs.length);
            int offset = 0;
            for (int i = rgdispidNamedArgs.length; i > 0; --i) {
                OS.MoveMemory(pDispParams.rgdispidNamedArgs + offset, new int[] { rgdispidNamedArgs[i - 1] }, 4);
                offset += 4;
            }
        }
        final EXCEPINFO excepInfo = new EXCEPINFO();
        final int[] pArgErr = { 0 };
        long pVarResultAddress = 0L;
        if (pVarResult != null) {
            pVarResultAddress = OS.GlobalAlloc(64, VARIANT.sizeof);
        }
        final int result = this.objIDispatch.Invoke(dispIdMember, new GUID(), 2048, wFlags, pDispParams, pVarResultAddress, excepInfo, pArgErr);
        if (pVarResultAddress != 0L) {
            pVarResult.setData(pVarResultAddress);
            COM.VariantClear(pVarResultAddress);
            OS.GlobalFree(pVarResultAddress);
        }
        if (pDispParams.rgdispidNamedArgs != 0L) {
            OS.GlobalFree(pDispParams.rgdispidNamedArgs);
        }
        if (pDispParams.rgvarg != 0L) {
            int offset2 = 0;
            for (int j = 0, length = rgvarg.length; j < length; ++j) {
                COM.VariantClear(pDispParams.rgvarg + offset2);
                offset2 += VARIANT.sizeof;
            }
            OS.GlobalFree(pDispParams.rgvarg);
        }
        this.manageExcepinfo(result, excepInfo);
        return result;
    }
    
    public void invokeNoReply(final int dispIdMember) {
        final int result = this.invoke(dispIdMember, 1, null, null, null);
        if (result != 0) {
            OLE.error(1014, result);
        }
    }
    
    public void invokeNoReply(final int dispIdMember, final Variant[] rgvarg) {
        final int result = this.invoke(dispIdMember, 1, rgvarg, null, null);
        if (result != 0) {
            OLE.error(1014, result);
        }
    }
    
    public void invokeNoReply(final int dispIdMember, final Variant[] rgvarg, final int[] rgdispidNamedArgs) {
        final int result = this.invoke(dispIdMember, 1, rgvarg, rgdispidNamedArgs, null);
        if (result != 0) {
            OLE.error(1014, result);
        }
    }
    
    private void manageExcepinfo(final int hResult, final EXCEPINFO excepInfo) {
        if (hResult == 0) {
            this.exceptionDescription = "No Error";
            return;
        }
        if (hResult == -2147352567) {
            if (excepInfo.bstrDescription != 0L) {
                final int size = COM.SysStringByteLen(excepInfo.bstrDescription);
                final char[] buffer = new char[(size + 1) / 2];
                OS.MoveMemory(buffer, excepInfo.bstrDescription, size);
                this.exceptionDescription = new String(buffer);
            }
            else {
                this.exceptionDescription = "OLE Automation Error Exception ";
                if (excepInfo.wCode != 0) {
                    this.exceptionDescription = this.exceptionDescription + "code = " + excepInfo.wCode;
                }
                else if (excepInfo.scode != 0) {
                    this.exceptionDescription = this.exceptionDescription + "code = " + excepInfo.scode;
                }
            }
        }
        else {
            this.exceptionDescription = "OLE Automation Error HResult : " + hResult;
        }
        if (excepInfo.bstrDescription != 0L) {
            COM.SysFreeString(excepInfo.bstrDescription);
        }
        if (excepInfo.bstrHelpFile != 0L) {
            COM.SysFreeString(excepInfo.bstrHelpFile);
        }
        if (excepInfo.bstrSource != 0L) {
            COM.SysFreeString(excepInfo.bstrSource);
        }
    }
    
    public boolean setProperty(final int dispIdMember, final Variant rgvarg) {
        final Variant[] rgvarg2 = { rgvarg };
        final int[] rgdispidNamedArgs = { -3 };
        int dwFlags = 4;
        if ((rgvarg.getType() & 0x4000) == 0x4000) {
            dwFlags = 8;
        }
        final Variant pVarResult = new Variant();
        final int result = this.invoke(dispIdMember, dwFlags, rgvarg2, rgdispidNamedArgs, pVarResult);
        return result == 0;
    }
    
    public boolean setProperty(final int dispIdMember, final Variant[] rgvarg) {
        final int[] rgdispidNamedArgs = { -3 };
        int dwFlags = 4;
        for (final Variant element : rgvarg) {
            if ((element.getType() & 0x4000) == 0x4000) {
                dwFlags = 8;
            }
        }
        final Variant pVarResult = new Variant();
        final int result = this.invoke(dispIdMember, dwFlags, rgvarg, rgdispidNamedArgs, pVarResult);
        return result == 0;
    }
}
