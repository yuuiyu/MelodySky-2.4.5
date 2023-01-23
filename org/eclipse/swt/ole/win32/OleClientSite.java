//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import java.io.*;
import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.*;

public class OleClientSite extends Composite
{
    COMObject iOleClientSite;
    private COMObject iAdviseSink;
    private COMObject iOleInPlaceSite;
    private COMObject iOleDocumentSite;
    protected GUID appClsid;
    private GUID objClsid;
    private int refCount;
    protected OleFrame frame;
    protected IUnknown objIUnknown;
    protected IOleObject objIOleObject;
    protected IViewObject2 objIViewObject2;
    protected IOleInPlaceObject objIOleInPlaceObject;
    protected IOleCommandTarget objIOleCommandTarget;
    protected IOleDocumentView objDocumentView;
    protected IStorage tempStorage;
    private int aspect;
    private int type;
    private boolean isStatic;
    boolean isActivated;
    private RECT borderWidths;
    private RECT indent;
    private boolean inUpdate;
    private boolean inInit;
    private boolean inDispose;
    private static final String WORDPROGID = "Word.Document";
    private Listener listener;
    static final int STATE_NONE = 0;
    static final int STATE_RUNNING = 1;
    static final int STATE_INPLACEACTIVE = 2;
    static final int STATE_UIACTIVE = 3;
    static final int STATE_ACTIVE = 4;
    int state;
    
    protected OleClientSite(Composite parent, final int style) {
        super(parent, style);
        this.borderWidths = new RECT();
        this.indent = new RECT();
        this.inUpdate = false;
        this.inInit = true;
        this.inDispose = false;
        this.state = 0;
        this.createCOMInterfaces();
        while (parent != null) {
            if (parent instanceof OleFrame) {
                this.frame = (OleFrame)parent;
                break;
            }
            parent = parent.getParent();
        }
        if (this.frame == null) {
            OLE.error(5);
        }
        this.frame.AddRef();
        this.aspect = 1;
        this.type = 1;
        this.isStatic = false;
        this.listener = (Listener)new lllI(this);
        this.frame.addListener(11, this.listener);
        this.frame.addListener(10, this.listener);
        this.addListener(12, this.listener);
        this.addListener(15, this.listener);
        this.addListener(16, this.listener);
        this.addListener(9, this.listener);
        this.addListener(31, this.listener);
        this.addListener(1, this.listener);
        this.addListener(26, this.listener);
        this.addListener(27, this.listener);
    }
    
    public OleClientSite(final Composite parent, final int style, final File file) {
        this(parent, style);
        try {
            if (file == null || file.isDirectory() || !file.exists()) {
                OLE.error(5);
            }
            final GUID fileClsid = new GUID();
            final char[] fileName = file.getAbsolutePath().toCharArray();
            final int result = COM.GetClassFile(fileName, fileClsid);
            if (result != 0) {
                OLE.error(1004, result);
            }
            final String progID = this.getProgID(fileClsid);
            if (progID == null) {
                OLE.error(1004, result);
            }
            this.OleCreate(this.appClsid = fileClsid, fileClsid, fileName, file);
        }
        catch (SWTException e) {
            this.dispose();
            this.disposeCOMInterfaces();
            throw e;
        }
    }
    
    public OleClientSite(final Composite parent, final int style, final String progId) {
        this(parent, style);
        try {
            this.appClsid = this.getClassID(progId);
            if (this.appClsid == null) {
                OLE.error(1004);
            }
            this.tempStorage = this.createTempStorage();
            final long[] address = { 0L };
            final long clientSite = this.isICAClient() ? 0L : this.iOleClientSite.getAddress();
            final int result = COM.OleCreate(this.appClsid, COM.IIDIUnknown, 1, (FORMATETC)null, clientSite, this.tempStorage.getAddress(), address);
            if (result != 0) {
                OLE.error(1001, result);
            }
            this.objIUnknown = new IUnknown(address[0]);
            this.addObjectReferences();
            if (COM.OleRun(this.objIUnknown.getAddress()) == 0) {
                this.state = 1;
            }
        }
        catch (SWTException e) {
            this.dispose();
            this.disposeCOMInterfaces();
            throw e;
        }
    }
    
    public OleClientSite(final Composite parent, final int style, final String progId, final File file) {
        this(parent, style);
        try {
            if (file == null || file.isDirectory() || !file.exists()) {
                OLE.error(5);
            }
            this.appClsid = this.getClassID(progId);
            if (this.appClsid == null) {
                OLE.error(1004);
            }
            final char[] fileName = file.getAbsolutePath().toCharArray();
            final GUID fileClsid = new GUID();
            COM.GetClassFile(fileName, fileClsid);
            this.OleCreate(this.appClsid, fileClsid, fileName, file);
        }
        catch (SWTException e) {
            this.dispose();
            this.disposeCOMInterfaces();
            throw e;
        }
    }
    
    void OleCreate(final GUID appClsid, final GUID fileClsid, final char[] fileName, final File file) {
        final boolean isOffice2007 = this.isOffice2007(true);
        if (!isOffice2007 && COM.IsEqualGUID(appClsid, fileClsid)) {
            this.tempStorage = this.createTempStorage();
            final long[] address = { 0L };
            final int result = COM.OleCreateFromFile(appClsid, fileName, COM.IIDIUnknown, 1, (FORMATETC)null, this.iOleClientSite.getAddress(), this.tempStorage.getAddress(), address);
            if (result != 0) {
                OLE.error(1001, result);
            }
            this.objIUnknown = new IUnknown(address[0]);
        }
        else {
            IStorage storage = null;
            if (COM.StgIsStorageFile(fileName) == 0) {
                final long[] address2 = { 0L };
                final int mode = 65552;
                final int result2 = COM.StgOpenStorage(fileName, 0L, 65552, 0L, 0, address2);
                if (result2 != 0) {
                    OLE.error(1002, result2);
                }
                storage = new IStorage(address2[0]);
            }
            else {
                long[] address2 = { 0L };
                final int mode = 4114;
                int result2 = COM.StgCreateDocfile((char[])null, 67112978, 0, address2);
                if (result2 != 0) {
                    OLE.error(1002, result2);
                }
                storage = new IStorage(address2[0]);
                String streamName = "CONTENTS";
                final GUID wordGUID = this.getClassID("Word.Document");
                if (wordGUID != null && COM.IsEqualGUID(appClsid, wordGUID)) {
                    streamName = "WordDocument";
                }
                if (isOffice2007) {
                    streamName = "Package";
                }
                address2 = new long[] { 0L };
                result2 = storage.CreateStream(streamName, 4114, 0, 0, address2);
                if (result2 != 0) {
                    storage.Release();
                    OLE.error(1002, result2);
                }
                final IStream stream = new IStream(address2[0]);
                try {
                    final FileInputStream fileInput = new FileInputStream(file);
                    final int increment = 4096;
                    final byte[] buffer = new byte[4096];
                    int count = 0;
                    while ((count = fileInput.read(buffer)) > 0) {
                        final long pv = OS.CoTaskMemAlloc(count);
                        OS.MoveMemory(pv, buffer, count);
                        result2 = stream.Write(pv, count, (int[])null);
                        OS.CoTaskMemFree(pv);
                        if (result2 != 0) {
                            fileInput.close();
                            stream.Release();
                            storage.Release();
                            OLE.error(1002, result2);
                        }
                    }
                    fileInput.close();
                    stream.Commit(0);
                    stream.Release();
                }
                catch (IOException err) {
                    stream.Release();
                    storage.Release();
                    OLE.error(1002);
                }
            }
            this.tempStorage = this.createTempStorage();
            int result = storage.CopyTo(0, (GUID)null, (String[])null, this.tempStorage.getAddress());
            storage.Release();
            if (result != 0) {
                OLE.error(1002, result);
            }
            long[] ppv = { 0L };
            result = COM.CoCreateInstance(appClsid, 0L, 3, COM.IIDIUnknown, ppv);
            if (result != 0) {
                OLE.error(1001, result);
            }
            this.objIUnknown = new IUnknown(ppv[0]);
            ppv = new long[] { 0L };
            result = this.objIUnknown.QueryInterface(COM.IIDIPersistStorage, ppv);
            if (result != 0) {
                OLE.error(1001, result);
            }
            final IPersistStorage iPersistStorage = new IPersistStorage(ppv[0]);
            result = iPersistStorage.Load(this.tempStorage.getAddress());
            iPersistStorage.Release();
            if (result != 0) {
                OLE.error(1001, result);
            }
        }
        this.addObjectReferences();
        if (COM.OleRun(this.objIUnknown.getAddress()) == 0) {
            this.state = 1;
        }
    }
    
    protected void addObjectReferences() {
        long[] ppvObject = { 0L };
        if (this.objIUnknown.QueryInterface(COM.IIDIPersist, ppvObject) == 0) {
            final IPersist objIPersist = new IPersist(ppvObject[0]);
            final GUID tempid = new GUID();
            if (objIPersist.GetClassID(tempid) == 0) {
                this.objClsid = tempid;
            }
            objIPersist.Release();
        }
        ppvObject = new long[] { 0L };
        int result = this.objIUnknown.QueryInterface(COM.IIDIViewObject2, ppvObject);
        if (result != 0) {
            OLE.error(1003, result);
        }
        (this.objIViewObject2 = new IViewObject2(ppvObject[0])).SetAdvise(this.aspect, 0, this.iAdviseSink.getAddress());
        ppvObject = new long[] { 0L };
        result = this.objIUnknown.QueryInterface(COM.IIDIOleObject, ppvObject);
        if (result != 0) {
            OLE.error(1003, result);
        }
        this.objIOleObject = new IOleObject(ppvObject[0]);
        final long[] ppvClientSite = { 0L };
        result = this.objIOleObject.GetClientSite(ppvClientSite);
        if (ppvClientSite[0] == 0L) {
            this.objIOleObject.SetClientSite(this.iOleClientSite.getAddress());
        }
        else {
            this.Release();
        }
        final int[] pdwConnection = { 0 };
        this.objIOleObject.Advise(this.iAdviseSink.getAddress(), pdwConnection);
        this.objIOleObject.SetHostNames("main", "main");
        COM.OleSetContainedObject(this.objIUnknown.getAddress(), true);
        ppvObject = new long[] { 0L };
        if (this.objIUnknown.QueryInterface(COM.IIDIOleLink, ppvObject) == 0) {
            final IOleLink objIOleLink = new IOleLink(ppvObject[0]);
            final long[] ppmk = { 0L };
            if (objIOleLink.GetSourceMoniker(ppmk) == 0) {
                new IUnknown(ppmk[0]).Release();
                this.type = 0;
                objIOleLink.BindIfRunning();
            }
            else {
                this.isStatic = true;
            }
            objIOleLink.Release();
        }
    }
    
    protected int AddRef() {
        return ++this.refCount;
    }
    
    private int CanInPlaceActivate() {
        if (this.aspect == 1 && this.type == 1) {
            return 0;
        }
        return 1;
    }
    
    private int ContextSensitiveHelp(final int fEnterMode) {
        return 0;
    }
    
    protected void createCOMInterfaces() {
        class lIIlI extends COMObject
        {
            final /* synthetic */ OleClientSite this$0;
            
            lIIlI(final OleClientSite this$0, final int[] argCounts) {
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
                return this.this$0.SaveObject();
            }
            
            public long method5(final long[] args) {
                return this.this$0.GetContainer(args[0]);
            }
            
            public long method6(final long[] args) {
                return this.this$0.ShowObject();
            }
            
            public long method7(final long[] args) {
                return this.this$0.OnShowWindow((int)args[0]);
            }
        }
        class llll extends COMObject
        {
            final /* synthetic */ OleClientSite this$0;
            
            llll(final OleClientSite this$0, final int[] argCounts) {
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
                return this.this$0.OnDataChange(args[0], args[1]);
            }
            
            public long method4(final long[] args) {
                return this.this$0.OnViewChange((int)args[0], (int)args[1]);
            }
            
            public long method6(final long[] args) {
                this.this$0.OnSave();
                return 0L;
            }
            
            public long method7(final long[] args) {
                return this.this$0.OnClose();
            }
        }
        class lIIll extends COMObject
        {
            final /* synthetic */ OleClientSite this$0;
            
            lIIll(final OleClientSite this$0, final int[] argCounts) {
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
                return this.this$0.GetWindow(args[0]);
            }
            
            public long method4(final long[] args) {
                return this.this$0.ContextSensitiveHelp((int)args[0]);
            }
            
            public long method5(final long[] args) {
                return this.this$0.CanInPlaceActivate();
            }
            
            public long method6(final long[] args) {
                return this.this$0.OnInPlaceActivate();
            }
            
            public long method7(final long[] args) {
                return this.this$0.OnUIActivate();
            }
            
            public long method8(final long[] args) {
                return this.this$0.GetWindowContext(args[0], args[1], args[2], args[3], args[4]);
            }
            
            public long method9(final long[] args) {
                return this.this$0.Scroll(args[0]);
            }
            
            public long method10(final long[] args) {
                return this.this$0.OnUIDeactivate((int)args[0]);
            }
            
            public long method11(final long[] args) {
                return this.this$0.OnInPlaceDeactivate();
            }
            
            public long method14(final long[] args) {
                return this.this$0.OnPosRectChange(args[0]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lorg/eclipse/swt/ole/win32/lIIlI;
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          9
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
        //    24: iconst_0       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_3       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_1       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_0       
        //    38: iastore        
        //    39: dup            
        //    40: bipush          7
        //    42: iconst_1       
        //    43: iastore        
        //    44: dup            
        //    45: bipush          8
        //    47: iconst_0       
        //    48: iastore        
        //    49: invokespecial   org/eclipse/swt/ole/win32/lIIlI.<init>:(Lorg/eclipse/swt/ole/win32/OleClientSite;[I)V
        //    52: putfield        org/eclipse/swt/ole/win32/OleClientSite.iOleClientSite:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    55: aload_0         /* this */
        //    56: new             Lorg/eclipse/swt/ole/win32/llll;
        //    59: dup            
        //    60: aload_0         /* this */
        //    61: bipush          8
        //    63: newarray        I
        //    65: dup            
        //    66: iconst_0       
        //    67: iconst_2       
        //    68: iastore        
        //    69: dup            
        //    70: iconst_1       
        //    71: iconst_0       
        //    72: iastore        
        //    73: dup            
        //    74: iconst_2       
        //    75: iconst_0       
        //    76: iastore        
        //    77: dup            
        //    78: iconst_3       
        //    79: iconst_2       
        //    80: iastore        
        //    81: dup            
        //    82: iconst_4       
        //    83: iconst_2       
        //    84: iastore        
        //    85: dup            
        //    86: iconst_5       
        //    87: iconst_1       
        //    88: iastore        
        //    89: dup            
        //    90: bipush          6
        //    92: iconst_0       
        //    93: iastore        
        //    94: dup            
        //    95: bipush          7
        //    97: iconst_0       
        //    98: iastore        
        //    99: invokespecial   org/eclipse/swt/ole/win32/llll.<init>:(Lorg/eclipse/swt/ole/win32/OleClientSite;[I)V
        //   102: putfield        org/eclipse/swt/ole/win32/OleClientSite.iAdviseSink:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //   105: aload_0         /* this */
        //   106: new             Lorg/eclipse/swt/ole/win32/lIIll;
        //   109: dup            
        //   110: aload_0         /* this */
        //   111: bipush          15
        //   113: newarray        I
        //   115: dup            
        //   116: iconst_0       
        //   117: iconst_2       
        //   118: iastore        
        //   119: dup            
        //   120: iconst_1       
        //   121: iconst_0       
        //   122: iastore        
        //   123: dup            
        //   124: iconst_2       
        //   125: iconst_0       
        //   126: iastore        
        //   127: dup            
        //   128: iconst_3       
        //   129: iconst_1       
        //   130: iastore        
        //   131: dup            
        //   132: iconst_4       
        //   133: iconst_1       
        //   134: iastore        
        //   135: dup            
        //   136: iconst_5       
        //   137: iconst_0       
        //   138: iastore        
        //   139: dup            
        //   140: bipush          6
        //   142: iconst_0       
        //   143: iastore        
        //   144: dup            
        //   145: bipush          7
        //   147: iconst_0       
        //   148: iastore        
        //   149: dup            
        //   150: bipush          8
        //   152: iconst_5       
        //   153: iastore        
        //   154: dup            
        //   155: bipush          9
        //   157: iconst_1       
        //   158: iastore        
        //   159: dup            
        //   160: bipush          10
        //   162: iconst_1       
        //   163: iastore        
        //   164: dup            
        //   165: bipush          11
        //   167: iconst_0       
        //   168: iastore        
        //   169: dup            
        //   170: bipush          12
        //   172: iconst_0       
        //   173: iastore        
        //   174: dup            
        //   175: bipush          13
        //   177: iconst_0       
        //   178: iastore        
        //   179: dup            
        //   180: bipush          14
        //   182: iconst_1       
        //   183: iastore        
        //   184: invokespecial   org/eclipse/swt/ole/win32/lIIll.<init>:(Lorg/eclipse/swt/ole/win32/OleClientSite;[I)V
        //   187: putfield        org/eclipse/swt/ole/win32/OleClientSite.iOleInPlaceSite:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //   190: aload_0         /* this */
        //   191: new             Lorg/eclipse/swt/ole/win32/lIIII;
        //   194: dup            
        //   195: aload_0         /* this */
        //   196: iconst_4       
        //   197: newarray        I
        //   199: dup            
        //   200: iconst_0       
        //   201: iconst_2       
        //   202: iastore        
        //   203: dup            
        //   204: iconst_1       
        //   205: iconst_0       
        //   206: iastore        
        //   207: dup            
        //   208: iconst_2       
        //   209: iconst_0       
        //   210: iastore        
        //   211: dup            
        //   212: iconst_3       
        //   213: iconst_1       
        //   214: iastore        
        //   215: invokespecial   org/eclipse/swt/ole/win32/lIIII.<init>:(Lorg/eclipse/swt/ole/win32/OleClientSite;[I)V
        //   218: putfield        org/eclipse/swt/ole/win32/OleClientSite.iOleDocumentSite:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //   221: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected IStorage createTempStorage() {
        final long[] tempStorage = { 0L };
        final int grfMode = 67108882;
        final int result = COM.StgCreateDocfile((char[])null, 67108882, 0, tempStorage);
        if (result != 0) {
            OLE.error(1000, result);
        }
        return new IStorage(tempStorage[0]);
    }
    
    public void deactivateInPlaceClient() {
        if (this.objIOleInPlaceObject != null) {
            this.objIOleInPlaceObject.InPlaceDeactivate();
        }
    }
    
    private void deleteTempStorage() {
        if (this.tempStorage != null) {
            this.tempStorage.Release();
        }
        this.tempStorage = null;
    }
    
    protected void disposeCOMInterfaces() {
        if (this.iOleClientSite != null) {
            this.iOleClientSite.dispose();
        }
        this.iOleClientSite = null;
        if (this.iAdviseSink != null) {
            this.iAdviseSink.dispose();
        }
        this.iAdviseSink = null;
        if (this.iOleInPlaceSite != null) {
            this.iOleInPlaceSite.dispose();
        }
        this.iOleInPlaceSite = null;
        if (this.iOleDocumentSite != null) {
            this.iOleDocumentSite.dispose();
        }
        this.iOleDocumentSite = null;
    }
    
    public int doVerb(final int verb) {
        if (this.state == 0 && COM.OleRun(this.objIUnknown.getAddress()) == 0) {
            this.state = 1;
        }
        if (this.state == 0 || this.isStatic) {
            return -2147467259;
        }
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int result = this.objIOleObject.DoVerb(verb, (MSG)null, this.iOleClientSite.getAddress(), 0, this.handle, rect);
        if (this.state != 1 && this.inInit) {
            this.updateStorage();
            this.inInit = false;
        }
        return result;
    }
    
    public int exec(final int cmdID, final int options, final Variant in, final Variant out) {
        if (this.objIOleCommandTarget == null) {
            final long[] address = { 0L };
            if (this.objIUnknown.QueryInterface(COM.IIDIOleCommandTarget, address) != 0) {
                return 1003;
            }
            this.objIOleCommandTarget = new IOleCommandTarget(address[0]);
        }
        long inAddress = 0L;
        if (in != null) {
            inAddress = OS.GlobalAlloc(64, VARIANT.sizeof);
            in.getData(inAddress);
        }
        long outAddress = 0L;
        if (out != null) {
            outAddress = OS.GlobalAlloc(64, VARIANT.sizeof);
            out.getData(outAddress);
        }
        final int result = this.objIOleCommandTarget.Exec((GUID)null, cmdID, options, inAddress, outAddress);
        if (inAddress != 0L) {
            COM.VariantClear(inAddress);
            OS.GlobalFree(inAddress);
        }
        if (outAddress != 0L) {
            out.setData(outAddress);
            COM.VariantClear(outAddress);
            OS.GlobalFree(outAddress);
        }
        return result;
    }
    
    IDispatch getAutomationObject() {
        final long[] ppvObject = { 0L };
        if (this.objIUnknown.QueryInterface(COM.IIDIDispatch, ppvObject) != 0) {
            return null;
        }
        return new IDispatch(ppvObject[0]);
    }
    
    protected GUID getClassID(final String clientName) {
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
    
    private int GetContainer(final long ppContainer) {
        if (ppContainer != 0L) {
            OS.MoveMemory(ppContainer, new long[] { 0L }, C.PTR_SIZEOF);
        }
        return -2147467262;
    }
    
    private SIZE getExtent() {
        final SIZE sizel = new SIZE();
        if (this.objIOleObject != null) {
            if (this.objIViewObject2 != null && !COM.OleIsRunning(this.objIOleObject.getAddress())) {
                this.objIViewObject2.GetExtent(this.aspect, -1, 0L, sizel);
            }
            else {
                this.objIOleObject.GetExtent(this.aspect, sizel);
            }
        }
        return this.xFormHimetricToPixels(sizel);
    }
    
    public Rectangle getIndent() {
        return new Rectangle(this.indent.left, this.indent.right, this.indent.top, this.indent.bottom);
    }
    
    public String getProgramID() {
        return this.getProgID(this.appClsid);
    }
    
    String getProgID(final GUID clsid) {
        if (clsid != null) {
            final long[] lplpszProgID = { 0L };
            if (COM.ProgIDFromCLSID(clsid, lplpszProgID) == 0) {
                final long hMem = lplpszProgID[0];
                final int length = OS.GlobalSize(hMem);
                final long ptr = OS.GlobalLock(hMem);
                final char[] buffer = new char[length];
                OS.MoveMemory(buffer, ptr, length);
                OS.GlobalUnlock(hMem);
                OS.GlobalFree(hMem);
                final String result = new String(buffer);
                final int index = result.indexOf("\u0000");
                return result.substring(0, index);
            }
        }
        return null;
    }
    
    int ActivateMe(final long pViewToActivate) {
        if (pViewToActivate == 0L) {
            final long[] ppvObject = { 0L };
            if (this.objIUnknown.QueryInterface(COM.IIDIOleDocument, ppvObject) != 0) {
                return -2147467259;
            }
            final IOleDocument objOleDocument = new IOleDocument(ppvObject[0]);
            if (objOleDocument.CreateView(this.iOleInPlaceSite.getAddress(), 0L, 0, ppvObject) != 0) {
                return -2147467259;
            }
            objOleDocument.Release();
            this.objDocumentView = new IOleDocumentView(ppvObject[0]);
        }
        else {
            (this.objDocumentView = new IOleDocumentView(pViewToActivate)).AddRef();
            this.objDocumentView.SetInPlaceSite(this.iOleInPlaceSite.getAddress());
        }
        this.objDocumentView.UIActivate(1);
        final RECT rect = this.getRect();
        this.objDocumentView.SetRect(rect);
        this.objDocumentView.Show(1);
        return 0;
    }
    
    protected int GetWindow(final long phwnd) {
        if (phwnd == 0L) {
            return -2147024809;
        }
        if (this.frame == null) {
            OS.MoveMemory(phwnd, new long[] { 0L }, C.PTR_SIZEOF);
            return -2147467263;
        }
        OS.MoveMemory(phwnd, new long[] { this.handle }, C.PTR_SIZEOF);
        return 0;
    }
    
    RECT getRect() {
        final Rectangle area = DPIUtil.autoScaleUp(this.getClientArea());
        final RECT rect = new RECT();
        rect.left = area.x;
        rect.top = area.y;
        rect.right = area.x + area.width;
        rect.bottom = area.y + area.height;
        return rect;
    }
    
    private int GetWindowContext(final long ppFrame, final long ppDoc, final long lprcPosRect, final long lprcClipRect, final long lpFrameInfo) {
        if (this.frame == null || ppFrame == 0L) {
            return -2147467263;
        }
        final long iOleInPlaceFrame = this.frame.getIOleInPlaceFrame();
        OS.MoveMemory(ppFrame, new long[] { iOleInPlaceFrame }, C.PTR_SIZEOF);
        this.frame.AddRef();
        if (ppDoc != 0L) {
            OS.MoveMemory(ppDoc, new long[] { 0L }, C.PTR_SIZEOF);
        }
        final RECT rect = this.getRect();
        if (lprcPosRect != 0L) {
            OS.MoveMemory(lprcPosRect, rect, RECT.sizeof);
        }
        if (lprcClipRect != 0L) {
            OS.MoveMemory(lprcClipRect, rect, RECT.sizeof);
        }
        final OLEINPLACEFRAMEINFO frameInfo = new OLEINPLACEFRAMEINFO();
        frameInfo.cb = OLEINPLACEFRAMEINFO.sizeof;
        frameInfo.fMDIApp = 0;
        frameInfo.hwndFrame = this.frame.handle;
        final Shell shell = this.getShell();
        final Menu menubar = shell.getMenuBar();
        if (menubar != null && !menubar.isDisposed()) {
            final long hwnd = shell.handle;
            final int cAccel = (int)OS.SendMessage(hwnd, 32768, 0L, 0L);
            if (cAccel != 0) {
                final long hAccel = OS.SendMessage(hwnd, 32769, 0L, 0L);
                if (hAccel != 0L) {
                    frameInfo.cAccelEntries = cAccel;
                    frameInfo.haccel = hAccel;
                }
            }
        }
        COM.MoveMemory(lpFrameInfo, frameInfo, OLEINPLACEFRAMEINFO.sizeof);
        return 0;
    }
    
    boolean isICAClient() {
        return this.getProgramID().startsWith("Citrix.ICAClient");
    }
    
    public boolean isDirty() {
        final long[] address = { 0L };
        if (this.objIOleObject.QueryInterface(COM.IIDIPersistFile, address) != 0) {
            return true;
        }
        final IPersistFile permStorage = new IPersistFile(address[0]);
        final int result = permStorage.IsDirty();
        permStorage.Release();
        return result != 1;
    }
    
    @Override
    public boolean isFocusControl() {
        this.checkWidget();
        long focusHwnd = OS.GetFocus();
        if (this.objIOleInPlaceObject == null) {
            return this.handle == focusHwnd;
        }
        final long[] phwnd = { 0L };
        this.objIOleInPlaceObject.GetWindow(phwnd);
        while (focusHwnd != 0L) {
            if (phwnd[0] == focusHwnd) {
                return true;
            }
            focusHwnd = OS.GetParent(focusHwnd);
        }
        return false;
    }
    
    private boolean isOffice2007(final boolean program) {
        String programID = this.getProgramID();
        if (programID == null) {
            return false;
        }
        if (program) {
            final int lastDot = programID.lastIndexOf(46);
            if (lastDot != -1) {
                programID = programID.substring(0, lastDot);
                final GUID guid = this.getClassID(programID);
                programID = this.getProgID(guid);
                if (programID == null) {
                    return false;
                }
            }
        }
        return programID.equals("Word.Document.12") || programID.equals("Excel.Sheet.12") || programID.equals("PowerPoint.Show.12");
    }
    
    private int OnClose() {
        return 0;
    }
    
    private int OnDataChange(final long pFormatetc, final long pStgmed) {
        return 0;
    }
    
    private void onDispose(final Event e) {
        this.inDispose = true;
        this.removeListener(12, this.listener);
        this.removeListener(15, this.listener);
        this.removeListener(16, this.listener);
        this.removeListener(9, this.listener);
        this.removeListener(31, this.listener);
        this.removeListener(1, this.listener);
        if (this.state != 0) {
            this.doVerb(-6);
        }
        this.deactivateInPlaceClient();
        this.releaseObjectInterfaces();
        this.deleteTempStorage();
        this.frame.removeListener(11, this.listener);
        this.frame.removeListener(10, this.listener);
        this.frame.Release();
        this.frame = null;
    }
    
    void onFocusIn(final Event e) {
        if (this.inDispose) {
            return;
        }
        if (this.state != 3) {
            final long[] ppvObject = { 0L };
            if (this.objIUnknown.QueryInterface(COM.IIDIOleInPlaceObject, ppvObject) == 0) {
                final IOleInPlaceObject objIOleInPlaceObject = new IOleInPlaceObject(ppvObject[0]);
                objIOleInPlaceObject.Release();
                this.doVerb(-1);
            }
        }
        if (this.objIOleInPlaceObject == null) {
            return;
        }
        if (this.isFocusControl()) {
            return;
        }
        final long[] phwnd = { 0L };
        this.objIOleInPlaceObject.GetWindow(phwnd);
        if (phwnd[0] == 0L) {
            return;
        }
        OS.SetFocus(phwnd[0]);
    }
    
    void onFocusOut(final Event e) {
    }
    
    private int OnInPlaceActivate() {
        this.state = 2;
        this.frame.setCurrentDocument(this);
        if (this.objIOleObject == null) {
            return 0;
        }
        final long[] ppvObject = { 0L };
        if (this.objIOleObject.QueryInterface(COM.IIDIOleInPlaceObject, ppvObject) == 0) {
            this.objIOleInPlaceObject = new IOleInPlaceObject(ppvObject[0]);
        }
        return 0;
    }
    
    private int OnInPlaceDeactivate() {
        if (this.objIOleInPlaceObject != null) {
            this.objIOleInPlaceObject.Release();
        }
        this.objIOleInPlaceObject = null;
        this.state = 1;
        this.redraw();
        final Shell shell = this.getShell();
        if (this.isFocusControl() || this.frame.isFocusControl()) {
            shell.traverse(16);
        }
        return 0;
    }
    
    private int OnPosRectChange(final long lprcPosRect) {
        final Point size = DPIUtil.autoScaleUp(this.getSize());
        this.setExtent(size.x, size.y);
        return 0;
    }
    
    private void onPaint(final Event e) {
        if (this.state == 1 || this.state == 2) {
            final SIZE size = this.getExtent();
            final Rectangle area = DPIUtil.autoScaleUp(this.getClientArea());
            final RECT rect = new RECT();
            if (this.getProgramID().startsWith("Excel.Sheet")) {
                rect.left = area.x;
                rect.right = area.x + area.height * size.cx / size.cy;
                rect.top = area.y;
                rect.bottom = area.y + area.height;
            }
            else {
                rect.left = area.x;
                rect.right = area.x + size.cx;
                rect.top = area.y;
                rect.bottom = area.y + size.cy;
            }
            final long pArea = OS.GlobalAlloc(64, RECT.sizeof);
            OS.MoveMemory(pArea, rect, RECT.sizeof);
            COM.OleDraw(this.objIUnknown.getAddress(), this.aspect, e.gc.handle, pArea);
            OS.GlobalFree(pArea);
        }
    }
    
    private void onResize(final Event e) {
        this.setBounds();
    }
    
    private void OnSave() {
    }
    
    private int OnShowWindow(final int fShow) {
        return 0;
    }
    
    private int OnUIActivate() {
        if (this.objIOleInPlaceObject == null) {
            return -2147467259;
        }
        this.state = 3;
        final long[] phwnd = { 0L };
        if (this.objIOleInPlaceObject.GetWindow(phwnd) == 0) {
            OS.SetWindowPos(phwnd[0], 0L, 0, 0, 0, 0, 3);
        }
        return 0;
    }
    
    int OnUIDeactivate(final int fUndoable) {
        if (this.frame == null || this.frame.isDisposed()) {
            return 0;
        }
        this.state = 2;
        this.frame.SetActiveObject(0L, 0L);
        this.redraw();
        final Shell shell = this.getShell();
        if (this.isFocusControl() || this.frame.isFocusControl()) {
            shell.traverse(16);
        }
        final Menu menubar = shell.getMenuBar();
        if (menubar == null || menubar.isDisposed()) {
            return 0;
        }
        final long shellHandle = shell.handle;
        OS.SetMenu(shellHandle, menubar.handle);
        return COM.OleSetMenuDescriptor(0L, shellHandle, 0L, 0L, 0L);
    }
    
    private void onTraverse(final Event event) {
        switch (event.detail) {
            case 2:
            case 4:
            case 8:
            case 16:
            case 128:
            case 256:
            case 512: {
                event.doit = true;
                break;
            }
        }
    }
    
    private int OnViewChange(final int dwAspect, final int lindex) {
        return 0;
    }
    
    protected int QueryInterface(final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147467262;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIOleClientSite)) {
            OS.MoveMemory(ppvObject, new long[] { this.iOleClientSite.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIAdviseSink)) {
            OS.MoveMemory(ppvObject, new long[] { this.iAdviseSink.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIOleInPlaceSite)) {
            OS.MoveMemory(ppvObject, new long[] { this.iOleInPlaceSite.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIOleDocumentSite)) {
            final String progID = this.getProgramID();
            if (!progID.startsWith("PowerPoint")) {
                OS.MoveMemory(ppvObject, new long[] { this.iOleDocumentSite.getAddress() }, C.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        return -2147467262;
    }
    
    public int queryStatus(final int cmd) {
        if (this.objIOleCommandTarget == null) {
            final long[] address = { 0L };
            if (this.objIUnknown.QueryInterface(COM.IIDIOleCommandTarget, address) != 0) {
                return 0;
            }
            this.objIOleCommandTarget = new IOleCommandTarget(address[0]);
        }
        final OLECMD olecmd = new OLECMD();
        olecmd.cmdID = cmd;
        final int result = this.objIOleCommandTarget.QueryStatus((GUID)null, 1, olecmd, 0L);
        if (result != 0) {
            return 0;
        }
        return olecmd.cmdf;
    }
    
    protected int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    protected void releaseObjectInterfaces() {
        if (this.objIOleInPlaceObject != null) {
            this.objIOleInPlaceObject.Release();
        }
        this.objIOleInPlaceObject = null;
        if (this.objIOleObject != null) {
            this.objIOleObject.Close(1);
            this.objIOleObject.Release();
        }
        this.objIOleObject = null;
        if (this.objDocumentView != null) {
            this.objDocumentView.Release();
        }
        this.objDocumentView = null;
        if (this.objIViewObject2 != null) {
            this.objIViewObject2.SetAdvise(this.aspect, 0, 0L);
            this.objIViewObject2.Release();
        }
        this.objIViewObject2 = null;
        if (this.objIOleCommandTarget != null) {
            this.objIOleCommandTarget.Release();
        }
        this.objIOleCommandTarget = null;
        if (this.objIUnknown != null) {
            this.objIUnknown.Release();
        }
        this.objIUnknown = null;
        if (COM.FreeUnusedLibraries) {
            COM.CoFreeUnusedLibraries();
        }
    }
    
    public boolean save(final File file, final boolean includeOleInfo) {
        if (this.isOffice2007(false)) {
            return this.saveOffice2007(file);
        }
        if (includeOleInfo) {
            return this.saveToStorageFile(file);
        }
        return this.saveToTraditionalFile(file);
    }
    
    private boolean saveFromContents(final long address, final File file) {
        boolean success = false;
        final IStream tempContents = new IStream(address);
        tempContents.AddRef();
        try {
            final FileOutputStream writer = new FileOutputStream(file);
            final int increment = 4096;
            final long pv = OS.CoTaskMemAlloc(4096);
            final int[] pcbWritten = { 0 };
            while (tempContents.Read(pv, 4096, pcbWritten) == 0 && pcbWritten[0] > 0) {
                final byte[] buffer = new byte[pcbWritten[0]];
                OS.MoveMemory(buffer, pv, pcbWritten[0]);
                writer.write(buffer);
                success = true;
            }
            OS.CoTaskMemFree(pv);
            writer.close();
        }
        catch (IOException ex) {}
        tempContents.Release();
        return success;
    }
    
    private boolean saveFromOle10Native(final long address, final File file) {
        boolean success = false;
        final IStream tempContents = new IStream(address);
        tempContents.AddRef();
        long pv = OS.CoTaskMemAlloc(4);
        final int[] size = { 0 };
        int rc = tempContents.Read(pv, 4, (int[])null);
        OS.MoveMemory(size, pv, 4);
        OS.CoTaskMemFree(pv);
        if (rc == 0 && size[0] > 0) {
            final byte[] buffer = new byte[size[0]];
            pv = OS.CoTaskMemAlloc(size[0]);
            rc = tempContents.Read(pv, size[0], (int[])null);
            OS.MoveMemory(buffer, pv, size[0]);
            OS.CoTaskMemFree(pv);
            try {
                final FileOutputStream writer = new FileOutputStream(file);
                writer.write(buffer);
                writer.close();
                success = true;
            }
            catch (IOException ex) {}
        }
        tempContents.Release();
        return success;
    }
    
    private int SaveObject() {
        this.updateStorage();
        return 0;
    }
    
    private boolean saveOffice2007(final File file) {
        if (file == null || file.isDirectory()) {
            return false;
        }
        if (!this.updateStorage()) {
            return false;
        }
        boolean result = false;
        final long[] ppv = { 0L };
        IPersistStorage iPersistStorage = null;
        if (this.objIUnknown.QueryInterface(COM.IIDIPersistStorage, ppv) == 0) {
            iPersistStorage = new IPersistStorage(ppv[0]);
            this.tempStorage.AddRef();
            iPersistStorage.HandsOffStorage();
        }
        final long[] address = { 0L };
        final int grfMode = 16;
        if (this.tempStorage.OpenStream("Package", 0L, 16, 0, address) == 0) {
            result = this.saveFromContents(address[0], file);
        }
        if (iPersistStorage != null) {
            iPersistStorage.SaveCompleted(this.tempStorage.getAddress());
            this.tempStorage.Release();
            iPersistStorage.Release();
        }
        return result;
    }
    
    private boolean saveToStorageFile(final File file) {
        if (file == null || file.isDirectory()) {
            return false;
        }
        if (!this.updateStorage()) {
            return false;
        }
        long[] address = { 0L };
        if (this.objIOleObject.QueryInterface(COM.IIDIPersistStorage, address) != 0) {
            return false;
        }
        final IPersistStorage permStorage = new IPersistStorage(address[0]);
        try {
            address = new long[] { 0L };
            final char[] path = file.getAbsolutePath().toCharArray();
            final int mode = 69650;
            final int result = COM.StgCreateDocfile(path, 69650, 0, address);
            if (result != 0) {
                return false;
            }
            final IStorage storage = new IStorage(address[0]);
            try {
                if (COM.OleSave(permStorage.getAddress(), storage.getAddress(), false) == 0 && storage.Commit(0) == 0) {
                    return true;
                }
            }
            finally {
                storage.Release();
            }
        }
        finally {
            permStorage.Release();
        }
        return false;
    }
    
    private boolean saveToTraditionalFile(final File file) {
        if (file == null || file.isDirectory()) {
            return false;
        }
        if (!this.updateStorage()) {
            return false;
        }
        final long[] address = { 0L };
        if (this.tempStorage.OpenStream("CONTENTS", 0L, 16, 0, address) == 0) {
            return this.saveFromContents(address[0], file);
        }
        return this.tempStorage.OpenStream("\u0001Ole10Native", 0L, 16, 0, address) == 0 && this.saveFromOle10Native(address[0], file);
    }
    
    private int Scroll(final long scrollExtent) {
        return 0;
    }
    
    void setBorderSpace(final RECT newBorderwidth) {
        this.borderWidths = newBorderwidth;
        this.setBounds();
    }
    
    void setBounds() {
        final Rectangle area = DPIUtil.autoScaleUp(this.frame.getClientArea());
        this.setBounds(DPIUtil.autoScaleDown(this.borderWidths.left), DPIUtil.autoScaleDown(this.borderWidths.top), DPIUtil.autoScaleDown(area.width - this.borderWidths.left - this.borderWidths.right), DPIUtil.autoScaleDown(area.height - this.borderWidths.top - this.borderWidths.bottom));
        this.setObjectRects();
    }
    
    private void setExtent(final int width, final int height) {
        if (this.objIOleObject == null || this.isStatic || this.inUpdate) {
            return;
        }
        final SIZE currentExtent = this.getExtent();
        if (width == currentExtent.cx && height == currentExtent.cy) {
            return;
        }
        SIZE newExtent = new SIZE();
        newExtent.cx = width;
        newExtent.cy = height;
        newExtent = this.xFormPixelsToHimetric(newExtent);
        final boolean alreadyRunning = COM.OleIsRunning(this.objIOleObject.getAddress());
        if (!alreadyRunning) {
            COM.OleRun(this.objIOleObject.getAddress());
        }
        if (this.objIOleObject.SetExtent(this.aspect, newExtent) == 0) {
            this.inUpdate = true;
            this.objIOleObject.Update();
            this.inUpdate = false;
            if (!alreadyRunning) {
                this.objIOleObject.Close(0);
            }
        }
    }
    
    public void setIndent(final Rectangle newIndent) {
        this.indent = new RECT();
        this.indent.left = newIndent.x;
        this.indent.right = newIndent.width;
        this.indent.top = newIndent.y;
        this.indent.bottom = newIndent.height;
    }
    
    private void setObjectRects() {
        if (this.objIOleInPlaceObject == null) {
            return;
        }
        final RECT rect = this.getRect();
        this.objIOleInPlaceObject.SetObjectRects(rect, rect);
    }
    
    private int ShowObject() {
        this.setBounds();
        return 0;
    }
    
    public void showProperties(final String title) {
        final long[] ppvObject = { 0L };
        if (this.objIUnknown.QueryInterface(COM.IIDISpecifyPropertyPages, ppvObject) != 0) {
            return;
        }
        final ISpecifyPropertyPages objISPP = new ISpecifyPropertyPages(ppvObject[0]);
        final CAUUID caGUID = new CAUUID();
        int result = objISPP.GetPages(caGUID);
        objISPP.Release();
        if (result != 0) {
            return;
        }
        char[] chTitle = null;
        if (title != null) {
            chTitle = new char[title.length()];
            title.getChars(0, title.length(), chTitle, 0);
        }
        result = COM.OleCreatePropertyFrame(this.frame.handle, 10, 10, chTitle, 1, new long[] { this.objIUnknown.getAddress() }, caGUID.cElems, caGUID.pElems, 2048, 0, 0L);
        OS.CoTaskMemFree(caGUID.pElems);
    }
    
    private boolean updateStorage() {
        if (this.tempStorage == null) {
            return false;
        }
        final long[] ppv = { 0L };
        if (this.objIUnknown.QueryInterface(COM.IIDIPersistStorage, ppv) != 0) {
            return false;
        }
        final IPersistStorage iPersistStorage = new IPersistStorage(ppv[0]);
        int result = COM.OleSave(iPersistStorage.getAddress(), this.tempStorage.getAddress(), true);
        if (result != 0) {
            COM.WriteClassStg(this.tempStorage.getAddress(), this.objClsid);
            result = iPersistStorage.Save(this.tempStorage.getAddress(), true);
        }
        this.tempStorage.Commit(0);
        result = iPersistStorage.SaveCompleted(0L);
        iPersistStorage.Release();
        return true;
    }
    
    private SIZE xFormHimetricToPixels(final SIZE aSize) {
        final long hDC = OS.GetDC(0L);
        final int xppi = OS.GetDeviceCaps(hDC, 88);
        final int yppi = OS.GetDeviceCaps(hDC, 90);
        OS.ReleaseDC(0L, hDC);
        final int cx = Compatibility.round(aSize.cx * xppi, 2540);
        final int cy = Compatibility.round(aSize.cy * yppi, 2540);
        final SIZE size = new SIZE();
        size.cx = cx;
        size.cy = cy;
        return size;
    }
    
    private SIZE xFormPixelsToHimetric(final SIZE aSize) {
        final long hDC = OS.GetDC(0L);
        final int xppi = OS.GetDeviceCaps(hDC, 88);
        final int yppi = OS.GetDeviceCaps(hDC, 90);
        OS.ReleaseDC(0L, hDC);
        final int cx = Compatibility.round(aSize.cx * 2540, xppi);
        final int cy = Compatibility.round(aSize.cy * 2540, yppi);
        final SIZE size = new SIZE();
        size.cx = cx;
        size.cy = cy;
        return size;
    }
}
