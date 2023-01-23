//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.widgets.*;

final class OlePropertyChangeSink
{
    private OleControlSite controlSite;
    private COMObject iPropertyNotifySink;
    private int refCount;
    private int propertyCookie;
    private OleEventTable eventTable;
    
    OlePropertyChangeSink(final OleControlSite controlSite) {
        this.controlSite = controlSite;
        this.createCOMInterfaces();
    }
    
    void addListener(final int propertyID, final OleListener listener) {
        if (listener == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            this.eventTable = new OleEventTable();
        }
        this.eventTable.hook(propertyID, listener);
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void connect(final IUnknown objIUnknown) {
        final long[] ppvObject = { 0L };
        if (objIUnknown.QueryInterface(COM.IIDIConnectionPointContainer, ppvObject) == 0) {
            final IConnectionPointContainer cpc = new IConnectionPointContainer(ppvObject[0]);
            if (cpc.FindConnectionPoint(COM.IIDIPropertyNotifySink, ppvObject) == 0) {
                final IConnectionPoint cp = new IConnectionPoint(ppvObject[0]);
                final int[] cookie = { 0 };
                if (cp.Advise(this.iPropertyNotifySink.getAddress(), cookie) == 0) {
                    this.propertyCookie = cookie[0];
                }
                cp.Release();
            }
            cpc.Release();
        }
    }
    
    private void createCOMInterfaces() {
        class llII extends COMObject
        {
            final /* synthetic */ OlePropertyChangeSink this$0;
            
            llII(final OlePropertyChangeSink this$0, final int[] argCounts) {
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
            
            public long method3(final long[] args) {
                return this.this$0.OnChanged((int)args[0]);
            }
            
            public long method4(final long[] args) {
                return this.this$0.OnRequestEdit((int)args[0]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lorg/eclipse/swt/ole/win32/llII;
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: iconst_5       
        //     7: newarray        I
        //     9: dup            
        //    10: iconst_0       
        //    11: iconst_2       
        //    12: iastore        
        //    13: dup            
        //    14: iconst_1       
        //    15: iconst_0       
        //    16: iastore        
        //    17: dup            
        //    18: iconst_2       
        //    19: iconst_0       
        //    20: iastore        
        //    21: dup            
        //    22: iconst_3       
        //    23: iconst_1       
        //    24: iastore        
        //    25: dup            
        //    26: iconst_4       
        //    27: iconst_1       
        //    28: iastore        
        //    29: invokespecial   org/eclipse/swt/ole/win32/llII.<init>:(Lorg/eclipse/swt/ole/win32/OlePropertyChangeSink;[I)V
        //    32: putfield        org/eclipse/swt/ole/win32/OlePropertyChangeSink.iPropertyNotifySink:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    35: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void disconnect(final IUnknown objIUnknown) {
        if (this.propertyCookie != 0 && objIUnknown != null) {
            final long[] ppvObject = { 0L };
            if (objIUnknown.QueryInterface(COM.IIDIConnectionPointContainer, ppvObject) == 0) {
                final IConnectionPointContainer cpc = new IConnectionPointContainer(ppvObject[0]);
                if (cpc.FindConnectionPoint(COM.IIDIPropertyNotifySink, ppvObject) == 0) {
                    final IConnectionPoint cp = new IConnectionPoint(ppvObject[0]);
                    if (cp.Unadvise(this.propertyCookie) == 0) {
                        this.propertyCookie = 0;
                    }
                    cp.Release();
                }
                cpc.Release();
            }
        }
    }
    
    private void disposeCOMInterfaces() {
        if (this.iPropertyNotifySink != null) {
            this.iPropertyNotifySink.dispose();
        }
        this.iPropertyNotifySink = null;
    }
    
    private void notifyListener(final int eventType, final OleEvent event) {
        if (event == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        event.type = eventType;
        event.widget = (Widget)this.controlSite;
        this.eventTable.sendEvent(event);
    }
    
    private int OnChanged(final int dispID) {
        if (this.eventTable == null || !this.eventTable.hooks(dispID)) {
            return 0;
        }
        final OleEvent event = new OleEvent();
        event.detail = 1;
        this.notifyListener(dispID, event);
        return 0;
    }
    
    private int OnRequestEdit(final int dispID) {
        if (this.eventTable == null || !this.eventTable.hooks(dispID)) {
            return 0;
        }
        final OleEvent event = new OleEvent();
        event.doit = true;
        event.detail = 0;
        this.notifyListener(dispID, event);
        return event.doit ? 0 : 1;
    }
    
    private int QueryInterface(final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIPropertyNotifySink)) {
            OS.MoveMemory(ppvObject, new long[] { this.iPropertyNotifySink.getAddress() }, C.PTR_SIZEOF);
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
    
    void removeListener(final int propertyID, final OleListener listener) {
        if (listener == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(propertyID, listener);
    }
}
