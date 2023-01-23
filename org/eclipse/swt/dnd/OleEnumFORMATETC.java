//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.*;

final class OleEnumFORMATETC
{
    private COMObject iEnumFORMATETC;
    private int refCount;
    private int index;
    private FORMATETC[] formats;
    
    OleEnumFORMATETC() {
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    private void createCOMInterfaces() {
        class lIl extends COMObject
        {
            final /* synthetic */ OleEnumFORMATETC this$0;
            
            lIl(final OleEnumFORMATETC this$0, final int[] argCounts) {
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
                return this.this$0.Next((int)args[0], args[1], args[2]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.Skip((int)args[0]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.Reset();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lorg/eclipse/swt/dnd/lIl;
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
        //    24: iconst_3       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_1       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_0       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_1       
        //    38: iastore        
        //    39: invokespecial   org/eclipse/swt/dnd/lIl.<init>:(Lorg/eclipse/swt/dnd/OleEnumFORMATETC;[I)V
        //    42: putfield        org/eclipse/swt/dnd/OleEnumFORMATETC.iEnumFORMATETC:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    45: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void disposeCOMInterfaces() {
        if (this.iEnumFORMATETC != null) {
            this.iEnumFORMATETC.dispose();
        }
        this.iEnumFORMATETC = null;
    }
    
    long getAddress() {
        return this.iEnumFORMATETC.getAddress();
    }
    
    private FORMATETC[] getNextItems(final int numItems) {
        if (this.formats == null || numItems < 1) {
            return null;
        }
        int endIndex = this.index + numItems - 1;
        if (endIndex > this.formats.length - 1) {
            endIndex = this.formats.length - 1;
        }
        if (this.index > endIndex) {
            return null;
        }
        final FORMATETC[] items = new FORMATETC[endIndex - this.index + 1];
        for (int i = 0; i < items.length; ++i) {
            items[i] = this.formats[this.index];
            ++this.index;
        }
        return items;
    }
    
    private int Next(final int celt, final long rgelt, final long pceltFetched) {
        if (rgelt == 0L) {
            return -2147024809;
        }
        if (pceltFetched == 0L && celt != 1) {
            return -2147024809;
        }
        final FORMATETC[] nextItems = this.getNextItems(celt);
        if (nextItems != null) {
            for (int i = 0; i < nextItems.length; ++i) {
                COM.MoveMemory(rgelt + i * FORMATETC.sizeof, nextItems[i], FORMATETC.sizeof);
            }
            if (pceltFetched != 0L) {
                OS.MoveMemory(pceltFetched, new int[] { nextItems.length }, 4);
            }
            if (nextItems.length == celt) {
                return 0;
            }
        }
        else {
            if (pceltFetched != 0L) {
                OS.MoveMemory(pceltFetched, new int[] { 0 }, 4);
            }
            COM.MoveMemory(rgelt, new FORMATETC(), FORMATETC.sizeof);
        }
        return 1;
    }
    
    private int QueryInterface(final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147467262;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIEnumFORMATETC)) {
            OS.MoveMemory(ppvObject, new long[] { this.iEnumFORMATETC.getAddress() }, C.PTR_SIZEOF);
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
    
    private int Reset() {
        return this.index = 0;
    }
    
    void setFormats(final FORMATETC[] newFormats) {
        this.formats = newFormats;
        this.index = 0;
    }
    
    private int Skip(final int celt) {
        if (celt < 1) {
            return -2147024809;
        }
        this.index += celt;
        if (this.index > this.formats.length - 1) {
            this.index = this.formats.length - 1;
            return 1;
        }
        return 0;
    }
}
