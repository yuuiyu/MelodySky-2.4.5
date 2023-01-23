//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.widgets.*;

final class OleEventSink
{
    private OleControlSite widget;
    private COMObject iDispatch;
    private int refCount;
    private IUnknown objIUnknown;
    private int eventCookie;
    private GUID eventGuid;
    private OleEventTable eventTable;
    
    OleEventSink(final OleControlSite widget, final long iUnknown, final GUID riid) {
        this.widget = widget;
        this.eventGuid = riid;
        this.objIUnknown = new IUnknown(iUnknown);
        this.createCOMInterfaces();
    }
    
    void connect() {
        final long[] ppvObject = { 0L };
        if (this.objIUnknown.QueryInterface(COM.IIDIConnectionPointContainer, ppvObject) == 0) {
            final IConnectionPointContainer cpc = new IConnectionPointContainer(ppvObject[0]);
            final long[] ppCP = { 0L };
            if (cpc.FindConnectionPoint(this.eventGuid, ppCP) == 0) {
                final IConnectionPoint cp = new IConnectionPoint(ppCP[0]);
                final int[] pCookie = { 0 };
                if (cp.Advise(this.iDispatch.getAddress(), pCookie) == 0) {
                    this.eventCookie = pCookie[0];
                }
                cp.Release();
            }
            cpc.Release();
        }
    }
    
    void addListener(final int eventID, final OleListener listener) {
        if (listener == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            this.eventTable = new OleEventTable();
        }
        this.eventTable.hook(eventID, listener);
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    private void createCOMInterfaces() {
        class lIIIl extends COMObject
        {
            final /* synthetic */ OleEventSink this$0;
            
            lIIIl(final OleEventSink this$0, final int[] argCounts) {
                this.this$0 = this$0;
                super(argCounts);
            }
            
            public long method0(final long[] args) {
                return this.this$0.QueryInterface(args[0], args[1]);
            }
            
            public long method1(final long[] args) {
                return this.this$0.AddRef();
            }
            
            public long method2(final long[] args) {
                return this.this$0.Release();
            }
            
            public long method6(final long[] args) {
                return this.this$0.Invoke((int)args[0], args[1], (int)args[2], (int)args[3], args[4], args[5], args[6], args[7]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lorg/eclipse/swt/ole/win32/lIIIl;
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          7
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
        //    24: iconst_1       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_3       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_4       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: bipush          8
        //    39: iastore        
        //    40: invokespecial   org/eclipse/swt/ole/win32/lIIIl.<init>:(Lorg/eclipse/swt/ole/win32/OleEventSink;[I)V
        //    43: putfield        org/eclipse/swt/ole/win32/OleEventSink.iDispatch:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    46: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void disconnect() {
        if (this.eventCookie != 0 && this.objIUnknown != null) {
            final long[] ppvObject = { 0L };
            if (this.objIUnknown.QueryInterface(COM.IIDIConnectionPointContainer, ppvObject) == 0) {
                final IConnectionPointContainer cpc = new IConnectionPointContainer(ppvObject[0]);
                if (cpc.FindConnectionPoint(this.eventGuid, ppvObject) == 0) {
                    final IConnectionPoint cp = new IConnectionPoint(ppvObject[0]);
                    if (cp.Unadvise(this.eventCookie) == 0) {
                        this.eventCookie = 0;
                    }
                    cp.Release();
                }
                cpc.Release();
            }
        }
    }
    
    private void disposeCOMInterfaces() {
        if (this.iDispatch != null) {
            this.iDispatch.dispose();
        }
        this.iDispatch = null;
    }
    
    private int Invoke(final int dispIdMember, final long riid, final int lcid, final int dwFlags, final long pDispParams, final long pVarResult, final long pExcepInfo, final long pArgErr) {
        if (this.eventTable == null || !this.eventTable.hooks(dispIdMember)) {
            return 0;
        }
        Variant[] eventInfo = null;
        if (pDispParams != 0L) {
            final DISPPARAMS dispParams = new DISPPARAMS();
            COM.MoveMemory(dispParams, pDispParams, DISPPARAMS.sizeof);
            eventInfo = new Variant[dispParams.cArgs];
            final int size = VARIANT.sizeof;
            long offset = (dispParams.cArgs - 1) * size;
            for (int j = 0; j < dispParams.cArgs; ++j) {
                (eventInfo[j] = new Variant()).setData(dispParams.rgvarg + offset);
                offset -= size;
            }
        }
        final OleEvent event = new OleEvent();
        event.arguments = eventInfo;
        this.notifyListener(dispIdMember, event);
        if (eventInfo != null) {
            for (final Variant element : eventInfo) {
                element.dispose();
            }
        }
        return 0;
    }
    
    private void notifyListener(final int eventType, final OleEvent event) {
        if (event == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        event.type = eventType;
        event.widget = (Widget)this.widget;
        this.eventTable.sendEvent(event);
    }
    
    private int QueryInterface(final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDispatch) || COM.IsEqualGUID(guid, this.eventGuid)) {
            OS.MoveMemory(ppvObject, new long[] { this.iDispatch.getAddress() }, C.PTR_SIZEOF);
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
        }
        return this.refCount;
    }
    
    void removeListener(final int eventID, final OleListener listener) {
        if (listener == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(eventID, listener);
    }
    
    boolean hasListeners() {
        return this.eventTable.hasEntries();
    }
}
