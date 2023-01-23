//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.ole.win32.*;

public class DragSource extends Widget
{
    Control control;
    Listener controlListener;
    Transfer[] transferAgents;
    DragSourceEffect dragEffect;
    Composite topControl;
    long hwndDrag;
    COMIDropSource iDropSource;
    COMIDataObject iDataObject;
    int dataEffect;
    static final String DEFAULT_DRAG_SOURCE_EFFECT = "DEFAULT_DRAG_SOURCE_EFFECT";
    static final int CFSTR_PERFORMEDDROPEFFECT;
    static final TCHAR WindowClass;
    
    public DragSource(final Control control, final int style) {
        super(control, checkStyle(style));
        this.transferAgents = new Transfer[0];
        this.dataEffect = 0;
        this.control = control;
        if (control.getData("DragSource") != null) {
            DND.error(2000);
        }
        control.setData("DragSource", this);
        control.addListener(12, this.controlListener = (event -> {
            if (event.type == 12 && !this.isDisposed()) {
                this.dispose();
            }
            if (event.type == 29 && !this.isDisposed()) {
                this.drag(event);
            }
            return;
        }));
        control.addListener(29, this.controlListener);
        this.addListener(12, e -> this.onDispose());
        final Object effect = control.getData("DEFAULT_DRAG_SOURCE_EFFECT");
        if (effect instanceof DragSourceEffect) {
            this.dragEffect = (DragSourceEffect)effect;
        }
        else if (control instanceof Tree) {
            this.dragEffect = new TreeDragSourceEffect((Tree)control);
        }
        else if (control instanceof Table) {
            this.dragEffect = new TableDragSourceEffect((Table)control);
        }
    }
    
    static int checkStyle(final int style) {
        if (style == 0) {
            return 2;
        }
        return style;
    }
    
    public void addDragListener(final DragSourceListener listener) {
        if (listener == null) {
            DND.error(4);
        }
        final DNDListener typedListener = new DNDListener((SWTEventListener)listener);
        (typedListener.dndWidget = this).addListener(2008, (Listener)typedListener);
        this.addListener(2001, (Listener)typedListener);
        this.addListener(2000, (Listener)typedListener);
    }
    
    private void createCOMInterfaces() {
        this.releaseCOMInterfaces();
        this.iDropSource = new COMIDropSource();
        this.iDataObject = new COMIDataObject(this.transferAgents);
    }
    
    @Override
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        final String validName = DragSource.class.getName();
        if (!validName.equals(name)) {
            DND.error(43);
        }
    }
    
    private void releaseCOMInterfaces() {
        if (this.iDropSource != null) {
            this.iDropSource.Release();
        }
        this.iDropSource = null;
        if (this.iDataObject != null) {
            this.iDataObject.Release();
        }
        this.iDataObject = null;
    }
    
    boolean canBeginDrag() {
        return this.transferAgents != null && this.transferAgents.length != 0;
    }
    
    private void drag(final Event dragEvent) {
        DNDEvent event = new DNDEvent();
        event.widget = this;
        event.x = dragEvent.x;
        event.y = dragEvent.y;
        event.time = OS.GetMessageTime();
        event.doit = true;
        this.notifyListeners(2008, (Event)event);
        if (!event.doit || !this.canBeginDrag()) {
            return;
        }
        final int[] pdwEffect = { 0 };
        final int operations = this.opToOs(this.getStyle());
        final Display display = this.control.getDisplay();
        final String key = "org.eclipse.swt.internal.win32.runMessagesInIdle";
        final Object oldValue = display.getData("org.eclipse.swt.internal.win32.runMessagesInIdle");
        display.setData("org.eclipse.swt.internal.win32.runMessagesInIdle", Boolean.TRUE);
        ImageList imagelist = null;
        final Image image = event.image;
        this.hwndDrag = 0L;
        this.topControl = null;
        if (image != null) {
            imagelist = new ImageList(0);
            imagelist.add(image);
            this.topControl = this.control.getShell();
            int offsetX = event.offsetX;
            this.hwndDrag = this.topControl.handle;
            if ((this.topControl.getStyle() & 0x4000000) != 0x0) {
                offsetX = image.getBoundsInPixels().width - offsetX;
                final RECT rect = new RECT();
                OS.GetClientRect(this.topControl.handle, rect);
                OS.ShowWindow(this.hwndDrag = OS.CreateWindowEx(1048608, DragSource.WindowClass, null, 1140850688, 0, 0, rect.right - rect.left, rect.bottom - rect.top, this.topControl.handle, 0L, OS.GetModuleHandle(null), null), 5);
            }
            OS.ImageList_BeginDrag(imagelist.getHandle(), 0, offsetX, event.offsetY);
            final int flags = 384;
            OS.RedrawWindow(this.topControl.handle, null, 0L, 384);
            final POINT pt = new POINT();
            pt.x = DPIUtil.autoScaleUp(dragEvent.x);
            pt.y = DPIUtil.autoScaleUp(dragEvent.y);
            OS.MapWindowPoints(this.control.handle, 0L, pt, 1);
            final RECT rect2 = new RECT();
            OS.GetWindowRect(this.hwndDrag, rect2);
            OS.ImageList_DragEnter(this.hwndDrag, pt.x - rect2.left, pt.y - rect2.top);
        }
        final String externalLoopKey = "org.eclipse.swt.internal.win32.externalEventLoop";
        int result = 262401;
        try {
            this.createCOMInterfaces();
            display.setData("org.eclipse.swt.internal.win32.externalEventLoop", Boolean.TRUE);
            result = COM.DoDragDrop(this.iDataObject.getAddress(), this.iDropSource.getAddress(), operations, pdwEffect);
        }
        finally {
            display.setData("org.eclipse.swt.internal.win32.externalEventLoop", Boolean.FALSE);
            if (this.hwndDrag != 0L) {
                OS.ImageList_DragLeave(this.hwndDrag);
                OS.ImageList_EndDrag();
                imagelist.dispose();
                if (this.hwndDrag != this.topControl.handle) {
                    OS.DestroyWindow(this.hwndDrag);
                }
                this.hwndDrag = 0L;
                this.topControl = null;
            }
            display.setData("org.eclipse.swt.internal.win32.runMessagesInIdle", oldValue);
            this.releaseCOMInterfaces();
        }
        int operation = this.osToOp(pdwEffect[0]);
        if (this.dataEffect == 2) {
            operation = ((operation == 0 || operation == 1) ? 8 : 2);
        }
        else if (this.dataEffect != 0) {
            operation = this.dataEffect;
        }
        event = new DNDEvent();
        event.widget = this;
        event.time = OS.GetMessageTime();
        event.doit = (result == 262400);
        event.detail = operation;
        this.notifyListeners(2000, (Event)event);
        this.dataEffect = 0;
    }
    
    private static int EnumFormatEtc(final Transfer[] transferAgents, final int dwDirection, final long ppenumFormatetc) {
        if (dwDirection == 2) {
            return -2147467263;
        }
        TransferData[] allowedDataTypes = new TransferData[0];
        for (final Transfer transferAgent : transferAgents) {
            if (transferAgent != null) {
                final TransferData[] formats = transferAgent.getSupportedTypes();
                final TransferData[] newAllowedDataTypes = new TransferData[allowedDataTypes.length + formats.length];
                System.arraycopy(allowedDataTypes, 0, newAllowedDataTypes, 0, allowedDataTypes.length);
                System.arraycopy(formats, 0, newAllowedDataTypes, allowedDataTypes.length, formats.length);
                allowedDataTypes = newAllowedDataTypes;
            }
        }
        final OleEnumFORMATETC enumFORMATETC = new OleEnumFORMATETC();
        enumFORMATETC.AddRef();
        final FORMATETC[] formats2 = new FORMATETC[allowedDataTypes.length];
        for (int i = 0; i < formats2.length; ++i) {
            formats2[i] = allowedDataTypes[i].formatetc;
        }
        enumFORMATETC.setFormats(formats2);
        OS.MoveMemory(ppenumFormatetc, new long[] { enumFORMATETC.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
    
    public Control getControl() {
        return this.control;
    }
    
    public DragSourceListener[] getDragListeners() {
        final Listener[] listeners = this.getListeners(2008);
        final int length = listeners.length;
        final DragSourceListener[] dragListeners = new DragSourceListener[length];
        int count = 0;
        for (final Listener listener : listeners) {
            if (listener instanceof DNDListener) {
                dragListeners[count] = (DragSourceListener)((DNDListener)listener).getEventListener();
                ++count;
            }
        }
        if (count == length) {
            return dragListeners;
        }
        final DragSourceListener[] result = new DragSourceListener[count];
        System.arraycopy(dragListeners, 0, result, 0, count);
        return result;
    }
    
    public DragSourceEffect getDragSourceEffect() {
        return this.dragEffect;
    }
    
    public Transfer[] getTransfer() {
        return this.transferAgents;
    }
    
    private int GiveFeedback(final int dwEffect) {
        return 262402;
    }
    
    private int QueryContinueDrag(final int fEscapePressed, final int grfKeyState) {
        if (this.topControl != null && this.topControl.isDisposed()) {
            return 262401;
        }
        if (fEscapePressed != 0) {
            if (this.hwndDrag != 0L) {
                OS.ImageList_DragLeave(this.hwndDrag);
            }
            return 262401;
        }
        final int mask = 19;
        if ((grfKeyState & 0x13) == 0x0) {
            if (this.hwndDrag != 0L) {
                OS.ImageList_DragLeave(this.hwndDrag);
            }
            return 262400;
        }
        if (this.hwndDrag != 0L) {
            final POINT pt = new POINT();
            OS.GetCursorPos(pt);
            final RECT rect = new RECT();
            OS.GetWindowRect(this.hwndDrag, rect);
            OS.ImageList_DragMove(pt.x - rect.left, pt.y - rect.top);
        }
        return 0;
    }
    
    private void onDispose() {
        if (this.control == null) {
            return;
        }
        this.releaseCOMInterfaces();
        if (this.controlListener != null) {
            this.control.removeListener(12, this.controlListener);
            this.control.removeListener(29, this.controlListener);
        }
        this.controlListener = null;
        this.control.setData("DragSource", null);
        this.control = null;
        this.transferAgents = null;
    }
    
    private int opToOs(final int operation) {
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
    
    private int osToOp(final int osOperation) {
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
    
    private static int QueryGetData(final Transfer[] transferAgents, final long pFormatetc) {
        if (transferAgents == null) {
            return -2147467259;
        }
        final TransferData transferData = new TransferData();
        COM.MoveMemory(transferData.formatetc = new FORMATETC(), pFormatetc, FORMATETC.sizeof);
        transferData.type = transferData.formatetc.cfFormat;
        for (final Transfer transferAgent : transferAgents) {
            if (transferAgent != null && transferAgent.isSupportedType(transferData)) {
                return 0;
            }
        }
        return -2147221404;
    }
    
    private static int QueryInterface(final COMObject comObject, final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if ((comObject != null && COM.IsEqualGUID(guid, COM.IIDIUnknown)) || (COM.IsEqualGUID(guid, COM.IIDIDropSource) && comObject instanceof COMIDropSource) || (COM.IsEqualGUID(guid, COM.IIDIDataObject) && comObject instanceof COMIDataObject)) {
            OS.MoveMemory(ppvObject, new long[] { comObject.getAddress() }, C.PTR_SIZEOF);
            comObject.method1(null);
            return 0;
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        return -2147467262;
    }
    
    public void removeDragListener(final DragSourceListener listener) {
        if (listener == null) {
            DND.error(4);
        }
        this.removeListener(2008, listener);
        this.removeListener(2001, listener);
        this.removeListener(2000, listener);
    }
    
    public void setDragSourceEffect(final DragSourceEffect effect) {
        this.dragEffect = effect;
    }
    
    public void setTransfer(final Transfer... transferAgents) {
        this.transferAgents = transferAgents;
    }
    
    static {
        CFSTR_PERFORMEDDROPEFFECT = Transfer.registerType("Performed DropEffect");
        WindowClass = new TCHAR(0, "#32770", true);
    }
    
    private class COMIDropSource extends COMObject
    {
        private long refCount;
        
        public COMIDropSource() {
            super(new int[] { 2, 0, 0, 2, 1 });
            this.refCount = 0L;
            this.AddRef();
        }
        
        @Override
        public long method0(final long[] args) {
            return QueryInterface(this, args[0], args[1]);
        }
        
        @Override
        public long method1(final long[] args) {
            return this.AddRef();
        }
        
        @Override
        public long method2(final long[] args) {
            return this.Release();
        }
        
        @Override
        public long method3(final long[] args) {
            return DragSource.this.QueryContinueDrag((int)args[0], (int)args[1]);
        }
        
        @Override
        public long method4(final long[] args) {
            return DragSource.this.GiveFeedback((int)args[0]);
        }
        
        public long AddRef() {
            return ++this.refCount;
        }
        
        public long Release() {
            --this.refCount;
            if (this.refCount == 0L) {
                if (DragSource.this.iDropSource == this) {
                    DragSource.this.iDropSource = null;
                }
                this.dispose();
                if (COM.FreeUnusedLibraries) {
                    COM.CoFreeUnusedLibraries();
                }
            }
            return this.refCount;
        }
    }
    
    private class COMIDataObject extends COMObject
    {
        private long refCount;
        private final Transfer[] transferAgents;
        private Object lastData;
        
        public COMIDataObject(final Transfer[] transferAgents) {
            super(new int[] { 2, 0, 0, 2, 2, 1, 2, 3, 2, 4, 1, 1 });
            this.refCount = 0L;
            this.lastData = null;
            this.AddRef();
            this.transferAgents = transferAgents;
        }
        
        @Override
        public long method0(final long[] args) {
            return QueryInterface(this, args[0], args[1]);
        }
        
        @Override
        public long method1(final long[] args) {
            return this.AddRef();
        }
        
        @Override
        public long method2(final long[] args) {
            return this.Release();
        }
        
        @Override
        public long method3(final long[] args) {
            return this.GetData(args[0], args[1]);
        }
        
        @Override
        public long method5(final long[] args) {
            return QueryGetData(this.transferAgents, args[0]);
        }
        
        @Override
        public long method7(final long[] args) {
            return this.SetData(args[0], args[1], (int)args[2]);
        }
        
        @Override
        public long method8(final long[] args) {
            return EnumFormatEtc(this.transferAgents, (int)args[0], args[1]);
        }
        
        public long AddRef() {
            return ++this.refCount;
        }
        
        public long Release() {
            --this.refCount;
            if (this.refCount == 0L) {
                if (DragSource.this.iDataObject == this) {
                    DragSource.this.iDataObject = null;
                }
                this.dispose();
                if (COM.FreeUnusedLibraries) {
                    COM.CoFreeUnusedLibraries();
                }
            }
            return this.refCount;
        }
        
        private boolean isActive() {
            return DragSource.this.iDataObject == this;
        }
        
        private int GetData(final long pFormatetc, final long pmedium) {
            if (pFormatetc == 0L || pmedium == 0L) {
                return -2147024809;
            }
            if (QueryGetData(this.transferAgents, pFormatetc) != 0) {
                return -2147221404;
            }
            final TransferData transferData = new TransferData();
            COM.MoveMemory(transferData.formatetc = new FORMATETC(), pFormatetc, FORMATETC.sizeof);
            transferData.type = transferData.formatetc.cfFormat;
            transferData.stgmedium = new STGMEDIUM();
            transferData.result = -2147467259;
            Object data;
            if (this.isActive()) {
                final DNDEvent event = new DNDEvent();
                event.widget = DragSource.this;
                event.time = OS.GetMessageTime();
                event.dataType = transferData;
                DragSource.this.notifyListeners(2001, (Event)event);
                if (!event.doit) {
                    return -2147467259;
                }
                this.lastData = event.data;
                data = event.data;
            }
            else {
                if (this.lastData == null) {
                    return -2147467259;
                }
                data = this.lastData;
            }
            Transfer transfer = null;
            for (final Transfer transferAgent : this.transferAgents) {
                if (transferAgent != null && transferAgent.isSupportedType(transferData)) {
                    transfer = transferAgent;
                    break;
                }
            }
            if (transfer == null) {
                return -2147221404;
            }
            transfer.javaToNative(data, transferData);
            if (transferData.result != 0) {
                return transferData.result;
            }
            COM.MoveMemory(pmedium, transferData.stgmedium, STGMEDIUM.sizeof);
            return transferData.result;
        }
        
        private int SetData(final long pFormatetc, final long pmedium, final int fRelease) {
            if (pFormatetc == 0L || pmedium == 0L) {
                return -2147024809;
            }
            final FORMATETC formatetc = new FORMATETC();
            COM.MoveMemory(formatetc, pFormatetc, FORMATETC.sizeof);
            if (formatetc.cfFormat == DragSource.CFSTR_PERFORMEDDROPEFFECT && formatetc.tymed == 1) {
                final STGMEDIUM stgmedium = new STGMEDIUM();
                COM.MoveMemory(stgmedium, pmedium, STGMEDIUM.sizeof);
                final long[] ptrEffect = { 0L };
                OS.MoveMemory(ptrEffect, stgmedium.unionField, C.PTR_SIZEOF);
                final int[] effect = { 0 };
                OS.MoveMemory(effect, ptrEffect[0], 4);
                if (this.isActive()) {
                    DragSource.this.dataEffect = DragSource.this.osToOp(effect[0]);
                }
            }
            if (fRelease == 1) {
                COM.ReleaseStgMedium(pmedium);
            }
            return 0;
        }
    }
}
