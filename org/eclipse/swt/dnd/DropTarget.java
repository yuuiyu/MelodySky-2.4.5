//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.ole.win32.*;

public class DropTarget extends Widget
{
    Control control;
    Listener controlListener;
    Transfer[] transferAgents;
    DropTargetEffect dropEffect;
    TransferData selectedDataType;
    int selectedOperation;
    int keyOperation;
    IDataObject iDataObject;
    COMObject iDropTarget;
    int refCount;
    static final String DEFAULT_DROP_TARGET_EFFECT = "DEFAULT_DROP_TARGET_EFFECT";
    
    public DropTarget(final Control control, final int style) {
        super(control, checkStyle(style));
        this.transferAgents = new Transfer[0];
        this.keyOperation = -1;
        this.control = control;
        if (control.getData("DropTarget") != null) {
            DND.error(2001);
        }
        control.setData("DropTarget", this);
        this.createCOMInterfaces();
        this.AddRef();
        if (COM.CoLockObjectExternal(this.iDropTarget.getAddress(), true, true) != 0) {
            DND.error(2001);
        }
        if (COM.RegisterDragDrop(control.handle, this.iDropTarget.getAddress()) != 0) {
            DND.error(2001);
        }
        control.addListener(12, this.controlListener = (event -> {
            if (!this.isDisposed()) {
                this.dispose();
            }
            return;
        }));
        this.addListener(12, event -> this.onDispose());
        final Object effect = control.getData("DEFAULT_DROP_TARGET_EFFECT");
        if (effect instanceof DropTargetEffect) {
            this.dropEffect = (DropTargetEffect)effect;
        }
        else if (control instanceof Table) {
            this.dropEffect = new TableDropTargetEffect((Table)control);
        }
        else if (control instanceof Tree) {
            this.dropEffect = new TreeDropTargetEffect((Tree)control);
        }
    }
    
    static int checkStyle(final int style) {
        if (style == 0) {
            return 2;
        }
        return style;
    }
    
    public void addDropListener(final DropTargetListener listener) {
        if (listener == null) {
            DND.error(4);
        }
        final DNDListener typedListener = new DNDListener((SWTEventListener)listener);
        (typedListener.dndWidget = this).addListener(2002, (Listener)typedListener);
        this.addListener(2003, (Listener)typedListener);
        this.addListener(2004, (Listener)typedListener);
        this.addListener(2005, (Listener)typedListener);
        this.addListener(2006, (Listener)typedListener);
        this.addListener(2007, (Listener)typedListener);
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    @Override
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        final String validName = DropTarget.class.getName();
        if (!validName.equals(name)) {
            DND.error(43);
        }
    }
    
    void createCOMInterfaces() {
        class ll extends COMObject
        {
            final /* synthetic */ DropTarget this$0;
            
            ll(final DropTarget this$0, final int[] argCounts) {
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
                if (args.length == 5) {
                    return this.this$0.DragEnter(args[0], (int)args[1], (int)args[2], (int)args[3], args[4]);
                }
                return this.this$0.DragEnter_64(args[0], (int)args[1], args[2], args[3]);
            }
            
            @Override
            public long method4(final long[] args) {
                if (args.length == 4) {
                    return this.this$0.DragOver((int)args[0], (int)args[1], (int)args[2], args[3]);
                }
                return this.this$0.DragOver_64((int)args[0], args[1], args[2]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.DragLeave();
            }
            
            @Override
            public long method6(final long[] args) {
                if (args.length == 5) {
                    return this.this$0.Drop(args[0], (int)args[1], (int)args[2], (int)args[3], args[4]);
                }
                return this.this$0.Drop_64(args[0], (int)args[1], args[2], args[3]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: if_icmpne       11
        //     7: iconst_1       
        //     8: goto            12
        //    11: iconst_0       
        //    12: istore_1        /* is32 */
        //    13: aload_0         /* this */
        //    14: new             Lorg/eclipse/swt/dnd/ll;
        //    17: dup            
        //    18: aload_0         /* this */
        //    19: bipush          7
        //    21: newarray        I
        //    23: dup            
        //    24: iconst_0       
        //    25: iconst_2       
        //    26: iastore        
        //    27: dup            
        //    28: iconst_1       
        //    29: iconst_0       
        //    30: iastore        
        //    31: dup            
        //    32: iconst_2       
        //    33: iconst_0       
        //    34: iastore        
        //    35: dup            
        //    36: iconst_3       
        //    37: iload_1         /* is32 */
        //    38: ifeq            45
        //    41: iconst_5       
        //    42: goto            46
        //    45: iconst_4       
        //    46: iastore        
        //    47: dup            
        //    48: iconst_4       
        //    49: iload_1         /* is32 */
        //    50: ifeq            57
        //    53: iconst_4       
        //    54: goto            58
        //    57: iconst_3       
        //    58: iastore        
        //    59: dup            
        //    60: iconst_5       
        //    61: iconst_0       
        //    62: iastore        
        //    63: dup            
        //    64: bipush          6
        //    66: iload_1         /* is32 */
        //    67: ifeq            74
        //    70: iconst_5       
        //    71: goto            75
        //    74: iconst_4       
        //    75: iastore        
        //    76: invokespecial   org/eclipse/swt/dnd/ll.<init>:(Lorg/eclipse/swt/dnd/DropTarget;[I)V
        //    79: putfield        org/eclipse/swt/dnd/DropTarget.iDropTarget:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    82: return         
        //    StackMapTable: 00 08 0B 40 01 FF 00 20 00 02 07 00 02 01 00 07 07 00 02 08 00 0E 08 00 0E 07 00 02 07 00 C4 07 00 C4 01 FF 00 00 00 02 07 00 02 01 00 08 07 00 02 08 00 0E 08 00 0E 07 00 02 07 00 C4 07 00 C4 01 01 FF 00 0A 00 02 07 00 02 01 00 07 07 00 02 08 00 0E 08 00 0E 07 00 02 07 00 C4 07 00 C4 01 FF 00 00 00 02 07 00 02 01 00 08 07 00 02 08 00 0E 08 00 0E 07 00 02 07 00 C4 07 00 C4 01 01 FF 00 0F 00 02 07 00 02 01 00 07 07 00 02 08 00 0E 08 00 0E 07 00 02 07 00 C4 07 00 C4 01 FF 00 00 00 02 07 00 02 01 00 08 07 00 02 08 00 0E 08 00 0E 07 00 02 07 00 C4 07 00 C4 01 01
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void disposeCOMInterfaces() {
        if (this.iDropTarget != null) {
            this.iDropTarget.dispose();
        }
        this.iDropTarget = null;
    }
    
    int DragEnter_64(final long pDataObject, final int grfKeyState, final long pt, final long pdwEffect) {
        final POINT point = new POINT();
        OS.MoveMemory(point, new long[] { pt }, 8);
        return this.DragEnter(pDataObject, grfKeyState, point.x, point.y, pdwEffect);
    }
    
    int DragEnter(final long pDataObject, final int grfKeyState, int pt_x, int pt_y, final long pdwEffect) {
        pt_x = DPIUtil.autoScaleDown(pt_x);
        pt_y = DPIUtil.autoScaleDown(pt_y);
        this.selectedDataType = null;
        this.selectedOperation = 0;
        if (this.iDataObject != null) {
            this.iDataObject.Release();
        }
        this.iDataObject = null;
        final DNDEvent event = new DNDEvent();
        if (!this.setEventData(event, pDataObject, grfKeyState, pt_x, pt_y, pdwEffect)) {
            OS.MoveMemory(pdwEffect, new int[] { 0 }, 4);
            return 1;
        }
        (this.iDataObject = new IDataObject(pDataObject)).AddRef();
        final int allowedOperations = event.operations;
        final TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
        System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
        this.notifyListeners(2002, (Event)event);
        this.refresh();
        if (event.detail == 16) {
            event.detail = (((allowedOperations & 0x2) != 0x0) ? 2 : 0);
        }
        this.selectedDataType = null;
        for (final TransferData allowedDataType : allowedDataTypes) {
            if (TransferData.sameType(allowedDataType, event.dataType)) {
                this.selectedDataType = allowedDataType;
                break;
            }
        }
        this.selectedOperation = 0;
        if (this.selectedDataType != null && (allowedOperations & event.detail) != 0x0) {
            this.selectedOperation = event.detail;
        }
        OS.MoveMemory(pdwEffect, new int[] { this.opToOs(this.selectedOperation) }, 4);
        return 0;
    }
    
    int DragLeave() {
        this.keyOperation = -1;
        if (this.iDataObject == null) {
            return 1;
        }
        final DNDEvent event = new DNDEvent();
        event.widget = this;
        event.time = OS.GetMessageTime();
        event.detail = 0;
        this.notifyListeners(2003, (Event)event);
        this.refresh();
        this.iDataObject.Release();
        this.iDataObject = null;
        return 0;
    }
    
    int DragOver_64(final int grfKeyState, final long pt, final long pdwEffect) {
        final POINT point = new POINT();
        OS.MoveMemory(point, new long[] { pt }, 8);
        return this.DragOver(grfKeyState, point.x, point.y, pdwEffect);
    }
    
    int DragOver(final int grfKeyState, int pt_x, int pt_y, final long pdwEffect) {
        pt_x = DPIUtil.autoScaleDown(pt_x);
        pt_y = DPIUtil.autoScaleDown(pt_y);
        if (this.iDataObject == null) {
            return 1;
        }
        final int oldKeyOperation = this.keyOperation;
        final DNDEvent event = new DNDEvent();
        if (!this.setEventData(event, this.iDataObject.getAddress(), grfKeyState, pt_x, pt_y, pdwEffect)) {
            this.keyOperation = -1;
            OS.MoveMemory(pdwEffect, new int[] { 0 }, 4);
            return 1;
        }
        final int allowedOperations = event.operations;
        final TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
        System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
        if (this.keyOperation == oldKeyOperation) {
            event.type = 2004;
            event.dataType = this.selectedDataType;
            event.detail = this.selectedOperation;
        }
        else {
            event.type = 2005;
            event.dataType = this.selectedDataType;
        }
        this.notifyListeners(event.type, (Event)event);
        this.refresh();
        if (event.detail == 16) {
            event.detail = (((allowedOperations & 0x2) != 0x0) ? 2 : 0);
        }
        this.selectedDataType = null;
        for (final TransferData allowedDataType : allowedDataTypes) {
            if (TransferData.sameType(allowedDataType, event.dataType)) {
                this.selectedDataType = allowedDataType;
                break;
            }
        }
        this.selectedOperation = 0;
        if (this.selectedDataType != null && (allowedOperations & event.detail) == event.detail) {
            this.selectedOperation = event.detail;
        }
        OS.MoveMemory(pdwEffect, new int[] { this.opToOs(this.selectedOperation) }, 4);
        return 0;
    }
    
    int Drop_64(final long pDataObject, final int grfKeyState, final long pt, final long pdwEffect) {
        final POINT point = new POINT();
        OS.MoveMemory(point, new long[] { pt }, 8);
        return this.Drop(pDataObject, grfKeyState, point.x, point.y, pdwEffect);
    }
    
    int Drop(final long pDataObject, final int grfKeyState, int pt_x, int pt_y, final long pdwEffect) {
        try {
            pt_x = DPIUtil.autoScaleDown(pt_x);
            pt_y = DPIUtil.autoScaleDown(pt_y);
            DNDEvent event = new DNDEvent();
            event.widget = this;
            event.time = OS.GetMessageTime();
            if (this.dropEffect != null) {
                event.item = this.dropEffect.getItem(pt_x, pt_y);
            }
            event.detail = 0;
            this.notifyListeners(2003, (Event)event);
            this.refresh();
            event = new DNDEvent();
            if (!this.setEventData(event, pDataObject, grfKeyState, pt_x, pt_y, pdwEffect)) {
                this.keyOperation = -1;
                OS.MoveMemory(pdwEffect, new int[] { 0 }, 4);
                return 1;
            }
            this.keyOperation = -1;
            final int allowedOperations = event.operations;
            final TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
            System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
            event.dataType = this.selectedDataType;
            event.detail = this.selectedOperation;
            this.notifyListeners(2007, (Event)event);
            this.refresh();
            this.selectedDataType = null;
            for (final TransferData allowedDataType : allowedDataTypes) {
                if (TransferData.sameType(allowedDataType, event.dataType)) {
                    this.selectedDataType = allowedDataType;
                    break;
                }
            }
            this.selectedOperation = 0;
            if (this.selectedDataType != null && (allowedOperations & event.detail) == event.detail) {
                this.selectedOperation = event.detail;
            }
            if (this.selectedOperation == 0) {
                OS.MoveMemory(pdwEffect, new int[] { 0 }, 4);
                return 0;
            }
            Object object = null;
            for (final Transfer transfer : this.transferAgents) {
                if (transfer != null && transfer.isSupportedType(this.selectedDataType)) {
                    object = transfer.nativeToJava(this.selectedDataType);
                    break;
                }
            }
            if (object == null) {
                this.selectedOperation = 0;
            }
            event.detail = this.selectedOperation;
            event.dataType = this.selectedDataType;
            event.data = object;
            OS.ImageList_DragShowNolock(false);
            try {
                this.notifyListeners(2006, (Event)event);
            }
            finally {
                OS.ImageList_DragShowNolock(true);
            }
            this.refresh();
            this.selectedOperation = 0;
            if ((allowedOperations & event.detail) == event.detail) {
                this.selectedOperation = event.detail;
            }
            OS.MoveMemory(pdwEffect, new int[] { this.opToOs(this.selectedOperation) }, 4);
            return 0;
        }
        finally {
            if (this.iDataObject != null) {
                this.iDataObject.Release();
                this.iDataObject = null;
            }
        }
    }
    
    public Control getControl() {
        return this.control;
    }
    
    public DropTargetListener[] getDropListeners() {
        final Listener[] listeners = this.getListeners(2002);
        final int length = listeners.length;
        final DropTargetListener[] dropListeners = new DropTargetListener[length];
        int count = 0;
        for (final Listener listener : listeners) {
            if (listener instanceof DNDListener) {
                dropListeners[count] = (DropTargetListener)((DNDListener)listener).getEventListener();
                ++count;
            }
        }
        if (count == length) {
            return dropListeners;
        }
        final DropTargetListener[] result = new DropTargetListener[count];
        System.arraycopy(dropListeners, 0, result, 0, count);
        return result;
    }
    
    public DropTargetEffect getDropTargetEffect() {
        return this.dropEffect;
    }
    
    int getOperationFromKeyState(final int grfKeyState) {
        final boolean ctrl = (grfKeyState & 0x8) != 0x0;
        final boolean shift = (grfKeyState & 0x4) != 0x0;
        final boolean alt = (grfKeyState & 0x20) != 0x0;
        if (alt) {
            if (ctrl || shift) {
                return 16;
            }
            return 4;
        }
        else {
            if (ctrl && shift) {
                return 4;
            }
            if (ctrl) {
                return 1;
            }
            if (shift) {
                return 2;
            }
            return 16;
        }
    }
    
    public Transfer[] getTransfer() {
        return this.transferAgents;
    }
    
    void onDispose() {
        if (this.control == null) {
            return;
        }
        COM.RevokeDragDrop(this.control.handle);
        if (this.controlListener != null) {
            this.control.removeListener(12, this.controlListener);
        }
        this.controlListener = null;
        this.control.setData("DropTarget", null);
        this.transferAgents = null;
        this.control = null;
        COM.CoLockObjectExternal(this.iDropTarget.getAddress(), false, true);
        this.Release();
        if (this.iDataObject != null) {
            this.iDataObject.Release();
        }
        this.iDataObject = null;
        if (COM.FreeUnusedLibraries) {
            COM.CoFreeUnusedLibraries();
        }
    }
    
    int opToOs(final int operation) {
        int osOperation = 0;
        if ((operation & 0x1) != 0x0) {
            osOperation |= 0x1;
        }
        if ((operation & 0x4) != 0x0) {
            osOperation |= 0x4;
        }
        if ((operation & 0x2) != 0x0) {
            osOperation |= 0x2;
        }
        return osOperation;
    }
    
    int osToOp(final int osOperation) {
        int operation = 0;
        if ((osOperation & 0x1) != 0x0) {
            operation |= 0x1;
        }
        if ((osOperation & 0x4) != 0x0) {
            operation |= 0x4;
        }
        if ((osOperation & 0x2) != 0x0) {
            operation |= 0x2;
        }
        return operation;
    }
    
    int QueryInterface(final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDropTarget)) {
            OS.MoveMemory(ppvObject, new long[] { this.iDropTarget.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
            if (COM.FreeUnusedLibraries) {
                COM.CoFreeUnusedLibraries();
            }
        }
        return this.refCount;
    }
    
    void refresh() {
        if (this.control == null || this.control.isDisposed()) {
            return;
        }
        final long handle = this.control.handle;
        final RECT lpRect = new RECT();
        if (OS.GetUpdateRect(handle, lpRect, false)) {
            OS.ImageList_DragShowNolock(false);
            OS.RedrawWindow(handle, lpRect, 0L, 257);
            OS.ImageList_DragShowNolock(true);
        }
    }
    
    public void removeDropListener(final DropTargetListener listener) {
        if (listener == null) {
            DND.error(4);
        }
        this.removeListener(2002, listener);
        this.removeListener(2003, listener);
        this.removeListener(2004, listener);
        this.removeListener(2005, listener);
        this.removeListener(2006, listener);
        this.removeListener(2007, listener);
    }
    
    public void setDropTargetEffect(final DropTargetEffect effect) {
        this.dropEffect = effect;
    }
    
    boolean setEventData(final DNDEvent event, final long pDataObject, final int grfKeyState, final int pt_x, final int pt_y, final long pdwEffect) {
        if (pDataObject == 0L || pdwEffect == 0L) {
            return false;
        }
        final int style = this.getStyle();
        final int[] operations = { 0 };
        OS.MoveMemory(operations, pdwEffect, 4);
        operations[0] = (this.osToOp(operations[0]) & style);
        if (operations[0] == 0) {
            return false;
        }
        int operation = this.getOperationFromKeyState(grfKeyState);
        if ((this.keyOperation = operation) == 16) {
            if ((style & 0x10) == 0x0) {
                operation = (((operations[0] & 0x2) != 0x0) ? 2 : 0);
            }
        }
        else if ((operation & operations[0]) == 0x0) {
            operation = 0;
        }
        TransferData[] dataTypes = new TransferData[0];
        final IDataObject dataObject = new IDataObject(pDataObject);
        dataObject.AddRef();
        try {
            final long[] address = { 0L };
            if (dataObject.EnumFormatEtc(1, address) != 0) {
                return false;
            }
            final IEnumFORMATETC enumFormatetc = new IEnumFORMATETC(address[0]);
            try {
                final long rgelt = OS.GlobalAlloc(64, FORMATETC.sizeof);
                try {
                    final int[] pceltFetched = { 0 };
                    enumFormatetc.Reset();
                    while (enumFormatetc.Next(1, rgelt, pceltFetched) == 0 && pceltFetched[0] == 1) {
                        final TransferData transferData = new TransferData();
                        COM.MoveMemory(transferData.formatetc = new FORMATETC(), rgelt, FORMATETC.sizeof);
                        transferData.type = transferData.formatetc.cfFormat;
                        transferData.pIDataObject = pDataObject;
                        for (final Transfer transfer : this.transferAgents) {
                            if (transfer != null && transfer.isSupportedType(transferData)) {
                                final TransferData[] newDataTypes = new TransferData[dataTypes.length + 1];
                                System.arraycopy(dataTypes, 0, newDataTypes, 0, dataTypes.length);
                                newDataTypes[dataTypes.length] = transferData;
                                dataTypes = newDataTypes;
                                break;
                            }
                        }
                    }
                }
                finally {
                    OS.GlobalFree(rgelt);
                }
            }
            finally {
                enumFormatetc.Release();
            }
        }
        finally {
            dataObject.Release();
        }
        if (dataTypes.length == 0) {
            return false;
        }
        event.widget = this;
        event.x = pt_x;
        event.y = pt_y;
        event.time = OS.GetMessageTime();
        event.feedback = 1;
        event.dataTypes = dataTypes;
        event.dataType = dataTypes[0];
        if (this.dropEffect != null) {
            event.item = this.dropEffect.getItem(pt_x, pt_y);
        }
        event.operations = operations[0];
        event.detail = operation;
        return true;
    }
    
    public void setTransfer(final Transfer... transferAgents) {
        if (transferAgents == null) {
            DND.error(4);
        }
        this.transferAgents = transferAgents;
    }
}
