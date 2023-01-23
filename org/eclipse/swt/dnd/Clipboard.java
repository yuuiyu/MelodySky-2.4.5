//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.ole.win32.*;

public class Clipboard
{
    private static final int RETRY_LIMIT = 10;
    private Display display;
    private COMObject iDataObject;
    private int refCount;
    private Transfer[] transferAgents;
    private Object[] data;
    private int CFSTR_PREFERREDDROPEFFECT;
    
    public Clipboard(Display display) {
        this.transferAgents = new Transfer[0];
        this.data = new Object[0];
        this.checkSubclass();
        if (display == null) {
            display = Display.getCurrent();
            if (display == null) {
                display = Display.getDefault();
            }
        }
        if (display.getThread() != Thread.currentThread()) {
            DND.error(22);
        }
        this.display = display;
        final TCHAR chFormatName = new TCHAR(0, "Preferred DropEffect", true);
        this.CFSTR_PREFERREDDROPEFFECT = OS.RegisterClipboardFormat(chFormatName);
        this.createCOMInterfaces();
        this.AddRef();
    }
    
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        final String validName = Clipboard.class.getName();
        if (!validName.equals(name)) {
            DND.error(43);
        }
    }
    
    protected void checkWidget() {
        final Display display = this.display;
        if (display == null) {
            DND.error(24);
        }
        if (display.getThread() != Thread.currentThread()) {
            DND.error(22);
        }
        if (display.isDisposed()) {
            DND.error(24);
        }
    }
    
    public void clearContents() {
        this.clearContents(1);
    }
    
    public void clearContents(final int clipboards) {
        this.checkWidget();
        if ((clipboards & 0x1) != 0x0 && COM.OleIsCurrentClipboard(this.iDataObject.getAddress()) == 0) {
            COM.OleSetClipboard(0L);
        }
    }
    
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        if (this.display.getThread() != Thread.currentThread()) {
            DND.error(22);
        }
        int result = 0;
        if (COM.OleIsCurrentClipboard(this.iDataObject.getAddress()) == 0) {
            result = COM.OleFlushClipboard();
        }
        for (int retryCount = 0; result != 0 && retryCount++ < 10; result = COM.OleFlushClipboard()) {
            try {
                Thread.sleep(25L);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            if (COM.OleIsCurrentClipboard(this.iDataObject.getAddress()) != 0) {
                break;
            }
            final MSG msg = new MSG();
            OS.PeekMessage(msg, 0L, 0, 0, 2);
        }
        this.Release();
        this.display = null;
    }
    
    public Object getContents(final Transfer transfer) {
        return this.getContents(transfer, 1);
    }
    
    public Object getContents(final Transfer transfer, final int clipboards) {
        this.checkWidget();
        if (transfer == null) {
            DND.error(4);
        }
        if ((clipboards & 0x1) == 0x0) {
            return null;
        }
        long[] ppv;
        int retryCount;
        int result;
        MSG msg;
        for (ppv = new long[] { 0L }, retryCount = 0, result = COM.OleGetClipboard(ppv); result != 0 && retryCount++ < 10; result = COM.OleGetClipboard(ppv)) {
            try {
                Thread.sleep(50L);
            }
            catch (Throwable t) {}
            msg = new MSG();
            OS.PeekMessage(msg, 0L, 0, 0, 2);
        }
        if (result != 0) {
            return null;
        }
        final IDataObject dataObject = new IDataObject(ppv[0]);
        try {
            for (final TransferData data : transfer.getSupportedTypes()) {
                if (dataObject.QueryGetData(data.formatetc) == 0) {
                    data.pIDataObject = ppv[0];
                    return transfer.nativeToJava(data);
                }
            }
        }
        finally {
            dataObject.Release();
        }
        return null;
    }
    
    public boolean isDisposed() {
        return this.display == null;
    }
    
    public void setContents(final Object[] data, final Transfer[] dataTypes) {
        this.setContents(data, dataTypes, 1);
    }
    
    public void setContents(final Object[] data, final Transfer[] dataTypes, final int clipboards) {
        this.checkWidget();
        if (data == null || dataTypes == null || data.length != dataTypes.length || data.length == 0) {
            DND.error(5);
        }
        for (int i = 0; i < data.length; ++i) {
            if (data[i] == null || dataTypes[i] == null || !dataTypes[i].validate(data[i])) {
                DND.error(5);
            }
        }
        if ((clipboards & 0x1) == 0x0) {
            return;
        }
        this.data = data;
        this.transferAgents = dataTypes;
        int result = COM.OleSetClipboard(this.iDataObject.getAddress());
        for (int retryCount = 0; result != 0 && retryCount++ < 10; result = COM.OleSetClipboard(this.iDataObject.getAddress())) {
            try {
                Thread.sleep(50L);
            }
            catch (Throwable t) {}
            final MSG msg = new MSG();
            OS.PeekMessage(msg, 0L, 0, 0, 2);
        }
        if (result != 0) {
            DND.error(2002);
        }
    }
    
    private int AddRef() {
        return ++this.refCount;
    }
    
    private void createCOMInterfaces() {
        class lII extends COMObject
        {
            final /* synthetic */ Clipboard this$0;
            
            lII(final Clipboard this$0, final int[] argCounts) {
                this.this$0 = this$0;
                super(argCounts);
            }
            
            @Override
            public long method0(final long[] args) {
                return this.this$0.QueryInterface(args[0], args[1]);
            }
            
            @Override
            public long method1(final long[] args) {
                return this.this$0.AddRef();
            }
            
            @Override
            public long method2(final long[] args) {
                return this.this$0.Release();
            }
            
            @Override
            public long method3(final long[] args) {
                return this.this$0.GetData(args[0], args[1]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.QueryGetData(args[0]);
            }
            
            @Override
            public long method8(final long[] args) {
                return this.this$0.EnumFormatEtc((int)args[0], args[1]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lorg/eclipse/swt/dnd/lII;
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          12
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_2       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_2       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_1       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_2       
        //    38: iastore        
        //    39: dup            
        //    40: bipush          7
        //    42: iconst_3       
        //    43: iastore        
        //    44: dup            
        //    45: bipush          8
        //    47: iconst_2       
        //    48: iastore        
        //    49: dup            
        //    50: bipush          9
        //    52: iconst_4       
        //    53: iastore        
        //    54: dup            
        //    55: bipush          10
        //    57: iconst_1       
        //    58: iastore        
        //    59: dup            
        //    60: bipush          11
        //    62: iconst_1       
        //    63: iastore        
        //    64: invokespecial   org/eclipse/swt/dnd/lII.<init>:(Lorg/eclipse/swt/dnd/Clipboard;[I)V
        //    67: putfield        org/eclipse/swt/dnd/Clipboard.iDataObject:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    70: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void disposeCOMInterfaces() {
        if (this.iDataObject != null) {
            this.iDataObject.dispose();
        }
        this.iDataObject = null;
    }
    
    private int EnumFormatEtc(final int dwDirection, final long ppenumFormatetc) {
        if (dwDirection == 2) {
            return -2147467263;
        }
        TransferData[] allowedDataTypes = new TransferData[0];
        for (final Transfer transferAgent : this.transferAgents) {
            final TransferData[] formats = transferAgent.getSupportedTypes();
            final TransferData[] newAllowedDataTypes = new TransferData[allowedDataTypes.length + formats.length];
            System.arraycopy(allowedDataTypes, 0, newAllowedDataTypes, 0, allowedDataTypes.length);
            System.arraycopy(formats, 0, newAllowedDataTypes, allowedDataTypes.length, formats.length);
            allowedDataTypes = newAllowedDataTypes;
        }
        final OleEnumFORMATETC enumFORMATETC = new OleEnumFORMATETC();
        enumFORMATETC.AddRef();
        final FORMATETC[] formats2 = new FORMATETC[allowedDataTypes.length + 1];
        for (int i = 0; i < allowedDataTypes.length; ++i) {
            formats2[i] = allowedDataTypes[i].formatetc;
        }
        final FORMATETC dropeffect = new FORMATETC();
        dropeffect.cfFormat = this.CFSTR_PREFERREDDROPEFFECT;
        dropeffect.dwAspect = 1;
        dropeffect.lindex = -1;
        dropeffect.tymed = 1;
        formats2[formats2.length - 1] = dropeffect;
        enumFORMATETC.setFormats(formats2);
        OS.MoveMemory(ppenumFormatetc, new long[] { enumFORMATETC.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
    
    private int GetData(final long pFormatetc, final long pmedium) {
        if (pFormatetc == 0L || pmedium == 0L) {
            return -2147024809;
        }
        if (this.QueryGetData(pFormatetc) != 0) {
            return -2147221404;
        }
        final TransferData transferData = new TransferData();
        COM.MoveMemory(transferData.formatetc = new FORMATETC(), pFormatetc, FORMATETC.sizeof);
        transferData.type = transferData.formatetc.cfFormat;
        transferData.stgmedium = new STGMEDIUM();
        transferData.result = -2147467259;
        if (transferData.type == this.CFSTR_PREFERREDDROPEFFECT) {
            final STGMEDIUM stgmedium = new STGMEDIUM();
            stgmedium.tymed = 1;
            OS.MoveMemory(stgmedium.unionField = OS.GlobalAlloc(64, 4), new int[] { 1 }, 4);
            stgmedium.pUnkForRelease = 0L;
            COM.MoveMemory(pmedium, stgmedium, STGMEDIUM.sizeof);
            return 0;
        }
        int transferIndex = -1;
        for (int i = 0; i < this.transferAgents.length; ++i) {
            if (this.transferAgents[i].isSupportedType(transferData)) {
                transferIndex = i;
                break;
            }
        }
        if (transferIndex == -1) {
            return -2147221404;
        }
        this.transferAgents[transferIndex].javaToNative(this.data[transferIndex], transferData);
        COM.MoveMemory(pmedium, transferData.stgmedium, STGMEDIUM.sizeof);
        return transferData.result;
    }
    
    private int QueryGetData(final long pFormatetc) {
        if (this.transferAgents == null) {
            return -2147467259;
        }
        final TransferData transferData = new TransferData();
        COM.MoveMemory(transferData.formatetc = new FORMATETC(), pFormatetc, FORMATETC.sizeof);
        transferData.type = transferData.formatetc.cfFormat;
        if (transferData.type == this.CFSTR_PREFERREDDROPEFFECT) {
            return 0;
        }
        for (final Transfer transferAgent : this.transferAgents) {
            if (transferAgent.isSupportedType(transferData)) {
                return 0;
            }
        }
        return -2147221404;
    }
    
    private int QueryInterface(final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDataObject)) {
            OS.MoveMemory(ppvObject, new long[] { this.iDataObject.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        return -2147467262;
    }
    
    private int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.data = new Object[0];
            this.transferAgents = new Transfer[0];
            this.disposeCOMInterfaces();
            if (COM.FreeUnusedLibraries) {
                COM.CoFreeUnusedLibraries();
            }
        }
        return this.refCount;
    }
    
    public TransferData[] getAvailableTypes() {
        return this.getAvailableTypes(1);
    }
    
    public TransferData[] getAvailableTypes(final int clipboards) {
        this.checkWidget();
        if ((clipboards & 0x1) == 0x0) {
            return new TransferData[0];
        }
        final FORMATETC[] types = this._getAvailableTypes();
        final TransferData[] data = new TransferData[types.length];
        for (int i = 0; i < types.length; ++i) {
            data[i] = new TransferData();
            data[i].type = types[i].cfFormat;
            data[i].formatetc = types[i];
        }
        return data;
    }
    
    public String[] getAvailableTypeNames() {
        this.checkWidget();
        final FORMATETC[] types = this._getAvailableTypes();
        final String[] names = new String[types.length];
        final int maxSize = 128;
        for (int i = 0; i < types.length; ++i) {
            final char[] buffer = new char[128];
            final int size = OS.GetClipboardFormatName(types[i].cfFormat, buffer, 128);
            if (size != 0) {
                names[i] = new String(buffer, 0, size);
            }
            else {
                switch (types[i].cfFormat) {
                    case 15: {
                        names[i] = "CF_HDROP";
                        break;
                    }
                    case 1: {
                        names[i] = "CF_TEXT";
                        break;
                    }
                    case 2: {
                        names[i] = "CF_BITMAP";
                        break;
                    }
                    case 3: {
                        names[i] = "CF_METAFILEPICT";
                        break;
                    }
                    case 4: {
                        names[i] = "CF_SYLK";
                        break;
                    }
                    case 5: {
                        names[i] = "CF_DIF";
                        break;
                    }
                    case 6: {
                        names[i] = "CF_TIFF";
                        break;
                    }
                    case 7: {
                        names[i] = "CF_OEMTEXT";
                        break;
                    }
                    case 8: {
                        names[i] = "CF_DIB";
                        break;
                    }
                    case 9: {
                        names[i] = "CF_PALETTE";
                        break;
                    }
                    case 10: {
                        names[i] = "CF_PENDATA";
                        break;
                    }
                    case 11: {
                        names[i] = "CF_RIFF";
                        break;
                    }
                    case 12: {
                        names[i] = "CF_WAVE";
                        break;
                    }
                    case 13: {
                        names[i] = "CF_UNICODETEXT";
                        break;
                    }
                    case 14: {
                        names[i] = "CF_ENHMETAFILE";
                        break;
                    }
                    case 16: {
                        names[i] = "CF_LOCALE";
                        break;
                    }
                    case 17: {
                        names[i] = "CF_MAX";
                        break;
                    }
                    default: {
                        names[i] = "UNKNOWN";
                        break;
                    }
                }
            }
        }
        return names;
    }
    
    private FORMATETC[] _getAvailableTypes() {
        FORMATETC[] types = new FORMATETC[0];
        final long[] ppv = { 0L };
        if (COM.OleGetClipboard(ppv) != 0) {
            return types;
        }
        final IDataObject dataObject = new IDataObject(ppv[0]);
        final long[] ppFormatetc = { 0L };
        final int rc = dataObject.EnumFormatEtc(1, ppFormatetc);
        dataObject.Release();
        if (rc != 0) {
            return types;
        }
        final IEnumFORMATETC enumFormatetc = new IEnumFORMATETC(ppFormatetc[0]);
        final long rgelt = OS.GlobalAlloc(64, FORMATETC.sizeof);
        final int[] pceltFetched = { 0 };
        enumFormatetc.Reset();
        while (enumFormatetc.Next(1, rgelt, pceltFetched) == 0 && pceltFetched[0] == 1) {
            final FORMATETC formatetc = new FORMATETC();
            COM.MoveMemory(formatetc, rgelt, FORMATETC.sizeof);
            final FORMATETC[] newTypes = new FORMATETC[types.length + 1];
            System.arraycopy(types, 0, newTypes, 0, types.length);
            newTypes[types.length] = formatetc;
            types = newTypes;
        }
        OS.GlobalFree(rgelt);
        enumFormatetc.Release();
        return types;
    }
}
