//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import java.io.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.graphics.*;

public class OleControlSite extends OleClientSite
{
    private COMObject iOleControlSite;
    private COMObject iDispatch;
    private OlePropertyChangeSink olePropertyChangeSink;
    private OleEventSink[] oleEventSink;
    private GUID[] oleEventSinkGUID;
    private long[] oleEventSinkIUnknown;
    private CONTROLINFO currentControlInfo;
    private int[] sitePropertyIds;
    private Variant[] sitePropertyValues;
    private Font font;
    static int SWT_RESTORECARET;
    static final String SHELL_PROG_ID = "Shell.Explorer";
    
    public OleControlSite(final Composite parent, final int style, final File file) {
        super(parent, style, file);
        this.oleEventSink = new OleEventSink[0];
        this.oleEventSinkGUID = new GUID[0];
        this.oleEventSinkIUnknown = new long[0];
        this.sitePropertyIds = new int[0];
        this.sitePropertyValues = new Variant[0];
        this.setSiteProperty(-709, new Variant(true));
        this.setSiteProperty(-710, new Variant(false));
    }
    
    public OleControlSite(final Composite parent, final int style, final String progId) {
        super(parent, style);
        this.oleEventSink = new OleEventSink[0];
        this.oleEventSinkGUID = new GUID[0];
        this.oleEventSinkIUnknown = new long[0];
        this.sitePropertyIds = new int[0];
        this.sitePropertyValues = new Variant[0];
        try {
            this.appClsid = this.getClassID(progId);
            if (this.appClsid == null) {
                OLE.error(1004);
            }
            final long licinfo = this.getLicenseInfo(this.appClsid);
            if (licinfo == 0L) {
                this.tempStorage = this.createTempStorage();
                final long[] address = { 0L };
                final long clientSite = this.isICAClient() ? 0L : this.iOleClientSite.getAddress();
                final int result = COM.OleCreate(this.appClsid, COM.IIDIUnknown, 1, (FORMATETC)null, clientSite, this.tempStorage.getAddress(), address);
                if (result != 0) {
                    OLE.error(1001, result);
                }
                this.objIUnknown = new IUnknown(address[0]);
            }
            else {
                long[] ppvObject = { 0L };
                try {
                    int result2 = COM.CoGetClassObject(this.appClsid, 3, 0L, COM.IIDIClassFactory2, ppvObject);
                    if (result2 != 0) {
                        OLE.error(1005, result2);
                    }
                    final IClassFactory2 classFactory = new IClassFactory2(ppvObject[0]);
                    ppvObject = new long[] { 0L };
                    result2 = classFactory.CreateInstanceLic(0L, 0L, COM.IIDIUnknown, licinfo, ppvObject);
                    classFactory.Release();
                    if (result2 != 0) {
                        OLE.error(1006, result2);
                    }
                }
                finally {
                    COM.SysFreeString(licinfo);
                }
                this.objIUnknown = new IUnknown(ppvObject[0]);
                ppvObject = new long[] { 0L };
                if (this.objIUnknown.QueryInterface(COM.IIDIPersistStorage, ppvObject) == 0) {
                    final IPersistStorage persist = new IPersistStorage(ppvObject[0]);
                    this.tempStorage = this.createTempStorage();
                    persist.InitNew(this.tempStorage.getAddress());
                    persist.Release();
                }
            }
            this.addObjectReferences();
            this.setSiteProperty(-709, new Variant(true));
            this.setSiteProperty(-710, new Variant(false));
            if (COM.OleRun(this.objIUnknown.getAddress()) == 0) {
                this.state = 1;
            }
        }
        catch (SWTError e) {
            this.dispose();
            this.disposeCOMInterfaces();
            throw e;
        }
    }
    
    public OleControlSite(final Composite parent, final int style, final String progId, final File file) {
        super(parent, style, progId, file);
        this.oleEventSink = new OleEventSink[0];
        this.oleEventSinkGUID = new GUID[0];
        this.oleEventSinkIUnknown = new long[0];
        this.sitePropertyIds = new int[0];
        this.sitePropertyValues = new Variant[0];
        this.setSiteProperty(-709, new Variant(true));
        this.setSiteProperty(-710, new Variant(false));
    }
    
    public void addEventListener(final int eventID, final OleListener listener) {
        if (listener == null) {
            OLE.error(4);
        }
        final GUID riid = getDefaultEventSinkGUID(this.objIUnknown);
        if (riid != null) {
            this.addEventListener(this.objIUnknown.getAddress(), riid, eventID, listener);
        }
    }
    
    static GUID getDefaultEventSinkGUID(final IUnknown unknown) {
        final long[] ppvObject = { 0L };
        if (unknown.QueryInterface(COM.IIDIProvideClassInfo2, ppvObject) == 0) {
            final IProvideClassInfo2 pci2 = new IProvideClassInfo2(ppvObject[0]);
            final GUID riid = new GUID();
            final int result = pci2.GetGUID(1, riid);
            pci2.Release();
            if (result == 0) {
                return riid;
            }
        }
        if (unknown.QueryInterface(COM.IIDIProvideClassInfo, ppvObject) == 0) {
            final IProvideClassInfo pci3 = new IProvideClassInfo(ppvObject[0]);
            final long[] ppTI = { 0L };
            final long[] ppEI = { 0L };
            int result2 = pci3.GetClassInfo(ppTI);
            pci3.Release();
            if (result2 == 0 && ppTI[0] != 0L) {
                final ITypeInfo classInfo = new ITypeInfo(ppTI[0]);
                long[] ppTypeAttr = { 0L };
                result2 = classInfo.GetTypeAttr(ppTypeAttr);
                if (result2 == 0 && ppTypeAttr[0] != 0L) {
                    final TYPEATTR typeAttribute = new TYPEATTR();
                    COM.MoveMemory(typeAttribute, ppTypeAttr[0], TYPEATTR.sizeof);
                    classInfo.ReleaseTypeAttr(ppTypeAttr[0]);
                    final int implMask = 7;
                    final int implBits = 3;
                    for (int i = 0; i < typeAttribute.cImplTypes; ++i) {
                        final int[] pImplTypeFlags = { 0 };
                        if (classInfo.GetImplTypeFlags(i, pImplTypeFlags) == 0 && (pImplTypeFlags[0] & 0x7) == 0x3) {
                            final int[] pRefType = { 0 };
                            if (classInfo.GetRefTypeOfImplType(i, pRefType) == 0) {
                                classInfo.GetRefTypeInfo(pRefType[0], ppEI);
                            }
                        }
                    }
                }
                classInfo.Release();
                if (ppEI[0] != 0L) {
                    final ITypeInfo eventInfo = new ITypeInfo(ppEI[0]);
                    ppTypeAttr = new long[] { 0L };
                    result2 = eventInfo.GetTypeAttr(ppTypeAttr);
                    GUID riid2 = null;
                    if (result2 == 0 && ppTypeAttr[0] != 0L) {
                        riid2 = new GUID();
                        COM.MoveMemory(riid2, ppTypeAttr[0], GUID.sizeof);
                        eventInfo.ReleaseTypeAttr(ppTypeAttr[0]);
                    }
                    eventInfo.Release();
                    return riid2;
                }
            }
        }
        return null;
    }
    
    public void addEventListener(final OleAutomation automation, final int eventID, final OleListener listener) {
        if (listener == null || automation == null) {
            OLE.error(4);
        }
        final long address = automation.getAddress();
        final IUnknown unknown = new IUnknown(address);
        final GUID riid = getDefaultEventSinkGUID(unknown);
        if (riid != null) {
            this.addEventListener(address, riid, eventID, listener);
        }
    }
    
    public void addEventListener(final OleAutomation automation, final String eventSinkId, final int eventID, final OleListener listener) {
        if (listener == null || automation == null || eventSinkId == null) {
            OLE.error(4);
        }
        final long address = automation.getAddress();
        if (address == 0L) {
            return;
        }
        final char[] buffer = eventSinkId.toCharArray();
        final GUID guid = new GUID();
        if (COM.IIDFromString(buffer, guid) != 0) {
            return;
        }
        this.addEventListener(address, guid, eventID, listener);
    }
    
    void addEventListener(final long iunknown, final GUID guid, final int eventID, final OleListener listener) {
        if (listener == null || iunknown == 0L || guid == null) {
            OLE.error(4);
        }
        int index = -1;
        for (int i = 0; i < this.oleEventSinkGUID.length; ++i) {
            if (COM.IsEqualGUID(this.oleEventSinkGUID[i], guid) && iunknown == this.oleEventSinkIUnknown[i]) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            this.oleEventSink[index].addListener(eventID, listener);
        }
        else {
            final int oldLength = this.oleEventSink.length;
            final OleEventSink[] newOleEventSink = new OleEventSink[oldLength + 1];
            final GUID[] newOleEventSinkGUID = new GUID[oldLength + 1];
            final long[] newOleEventSinkIUnknown = new long[oldLength + 1];
            System.arraycopy(this.oleEventSink, 0, newOleEventSink, 0, oldLength);
            System.arraycopy(this.oleEventSinkGUID, 0, newOleEventSinkGUID, 0, oldLength);
            System.arraycopy(this.oleEventSinkIUnknown, 0, newOleEventSinkIUnknown, 0, oldLength);
            this.oleEventSink = newOleEventSink;
            this.oleEventSinkGUID = newOleEventSinkGUID;
            this.oleEventSinkIUnknown = newOleEventSinkIUnknown;
            this.oleEventSink[oldLength] = new OleEventSink(this, iunknown, guid);
            this.oleEventSinkGUID[oldLength] = guid;
            this.oleEventSinkIUnknown[oldLength] = iunknown;
            this.oleEventSink[oldLength].AddRef();
            this.oleEventSink[oldLength].connect();
            this.oleEventSink[oldLength].addListener(eventID, listener);
        }
    }
    
    protected void addObjectReferences() {
        super.addObjectReferences();
        this.connectPropertyChangeSink();
        final long[] ppvObject = { 0L };
        if (this.objIUnknown.QueryInterface(COM.IIDIOleControl, ppvObject) == 0) {
            final IOleControl objIOleControl = new IOleControl(ppvObject[0]);
            objIOleControl.GetControlInfo(this.currentControlInfo = new CONTROLINFO());
            objIOleControl.Release();
        }
    }
    
    public void addPropertyListener(final int propertyID, final OleListener listener) {
        if (listener == null) {
            SWT.error(4);
        }
        this.olePropertyChangeSink.addListener(propertyID, listener);
    }
    
    private void connectPropertyChangeSink() {
        (this.olePropertyChangeSink = new OlePropertyChangeSink(this)).AddRef();
        this.olePropertyChangeSink.connect(this.objIUnknown);
    }
    
    protected void createCOMInterfaces() {
        class lIll extends COMObject
        {
            final /* synthetic */ OleControlSite this$0;
            
            lIll(final OleControlSite this$0, final int[] argCounts) {
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
                return this.this$0.OnControlInfoChanged();
            }
            
            public long method8(final long[] args) {
                return this.this$0.OnFocus((int)args[0]);
            }
        }
        class lIlI extends COMObject
        {
            final /* synthetic */ OleControlSite this$0;
            
            lIlI(final OleControlSite this$0, final int[] argCounts) {
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
        //     1: invokespecial   org/eclipse/swt/ole/win32/OleClientSite.createCOMInterfaces:()V
        //     4: aload_0         /* this */
        //     5: new             Lorg/eclipse/swt/ole/win32/lIll;
        //     8: dup            
        //     9: aload_0         /* this */
        //    10: bipush          10
        //    12: newarray        I
        //    14: dup            
        //    15: iconst_0       
        //    16: iconst_2       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_1       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_2       
        //    24: iconst_0       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_3       
        //    28: iconst_0       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_4       
        //    32: iconst_1       
        //    33: iastore        
        //    34: dup            
        //    35: iconst_5       
        //    36: iconst_1       
        //    37: iastore        
        //    38: dup            
        //    39: bipush          6
        //    41: iconst_3       
        //    42: iastore        
        //    43: dup            
        //    44: bipush          7
        //    46: iconst_2       
        //    47: iastore        
        //    48: dup            
        //    49: bipush          8
        //    51: iconst_1       
        //    52: iastore        
        //    53: dup            
        //    54: bipush          9
        //    56: iconst_0       
        //    57: iastore        
        //    58: invokespecial   org/eclipse/swt/ole/win32/lIll.<init>:(Lorg/eclipse/swt/ole/win32/OleControlSite;[I)V
        //    61: putfield        org/eclipse/swt/ole/win32/OleControlSite.iOleControlSite:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    64: aload_0         /* this */
        //    65: new             Lorg/eclipse/swt/ole/win32/lIlI;
        //    68: dup            
        //    69: aload_0         /* this */
        //    70: bipush          7
        //    72: newarray        I
        //    74: dup            
        //    75: iconst_0       
        //    76: iconst_2       
        //    77: iastore        
        //    78: dup            
        //    79: iconst_1       
        //    80: iconst_0       
        //    81: iastore        
        //    82: dup            
        //    83: iconst_2       
        //    84: iconst_0       
        //    85: iastore        
        //    86: dup            
        //    87: iconst_3       
        //    88: iconst_1       
        //    89: iastore        
        //    90: dup            
        //    91: iconst_4       
        //    92: iconst_3       
        //    93: iastore        
        //    94: dup            
        //    95: iconst_5       
        //    96: iconst_5       
        //    97: iastore        
        //    98: dup            
        //    99: bipush          6
        //   101: bipush          8
        //   103: iastore        
        //   104: invokespecial   org/eclipse/swt/ole/win32/lIlI.<init>:(Lorg/eclipse/swt/ole/win32/OleControlSite;[I)V
        //   107: putfield        org/eclipse/swt/ole/win32/OleControlSite.iDispatch:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //   110: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void disconnectEventSinks() {
        for (final OleEventSink sink : this.oleEventSink) {
            sink.disconnect();
            sink.Release();
        }
        this.oleEventSink = new OleEventSink[0];
        this.oleEventSinkGUID = new GUID[0];
        this.oleEventSinkIUnknown = new long[0];
    }
    
    private void disconnectPropertyChangeSink() {
        if (this.olePropertyChangeSink != null) {
            this.olePropertyChangeSink.disconnect(this.objIUnknown);
            this.olePropertyChangeSink.Release();
        }
        this.olePropertyChangeSink = null;
    }
    
    protected void disposeCOMInterfaces() {
        super.disposeCOMInterfaces();
        if (this.iOleControlSite != null) {
            this.iOleControlSite.dispose();
        }
        this.iOleControlSite = null;
        if (this.iDispatch != null) {
            this.iDispatch.dispose();
        }
        this.iDispatch = null;
    }
    
    public Color getBackground() {
        if (this.objIUnknown != null) {
            final OleAutomation oleObject = new OleAutomation((OleClientSite)this);
            final Variant varBackColor = oleObject.getProperty(-501);
            oleObject.dispose();
            if (varBackColor != null) {
                final int[] colorRef = { 0 };
                if (COM.OleTranslateColor(varBackColor.getInt(), 0L, colorRef) == 0) {
                    return Color.win32_new((Device)this.getDisplay(), colorRef[0]);
                }
            }
        }
        return super.getBackground();
    }
    
    public Font getFont() {
        if (this.font != null && !this.font.isDisposed()) {
            return this.font;
        }
        if (this.objIUnknown != null) {
            final OleAutomation oleObject = new OleAutomation((OleClientSite)this);
            final Variant varDispFont = oleObject.getProperty(-512);
            oleObject.dispose();
            if (varDispFont != null) {
                final OleAutomation iDispFont = varDispFont.getAutomation();
                final Variant lfFaceName = iDispFont.getProperty(0);
                final Variant lfHeight = iDispFont.getProperty(2);
                final Variant lfItalic = iDispFont.getProperty(4);
                final Variant lfBold = iDispFont.getProperty(3);
                iDispFont.dispose();
                if (lfFaceName != null && lfHeight != null && lfItalic != null && lfBold != null) {
                    final int style = 3 * lfBold.getInt() + 2 * lfItalic.getInt();
                    return this.font = new Font((Device)this.getShell().getDisplay(), lfFaceName.getString(), lfHeight.getInt(), style);
                }
            }
        }
        return super.getFont();
    }
    
    public Color getForeground() {
        if (this.objIUnknown != null) {
            final OleAutomation oleObject = new OleAutomation((OleClientSite)this);
            final Variant varForeColor = oleObject.getProperty(-513);
            oleObject.dispose();
            if (varForeColor != null) {
                final int[] colorRef = { 0 };
                if (COM.OleTranslateColor(varForeColor.getInt(), 0L, colorRef) == 0) {
                    return Color.win32_new((Device)this.getDisplay(), colorRef[0]);
                }
            }
        }
        return super.getForeground();
    }
    
    protected long getLicenseInfo(final GUID clsid) {
        final long[] ppvObject = { 0L };
        if (COM.CoGetClassObject(clsid, 3, 0L, COM.IIDIClassFactory, ppvObject) != 0) {
            return 0L;
        }
        long result = 0L;
        final IUnknown unknown = new IUnknown(ppvObject[0]);
        if (unknown.QueryInterface(COM.IIDIClassFactory2, ppvObject) == 0) {
            final IClassFactory2 classFactory = new IClassFactory2(ppvObject[0]);
            final LICINFO licinfo = new LICINFO();
            if (classFactory.GetLicInfo(licinfo) == 0) {
                final long[] pBstrKey = { 0L };
                if (licinfo != null && licinfo.fRuntimeKeyAvail && classFactory.RequestLicKey(0, pBstrKey) == 0) {
                    result = pBstrKey[0];
                }
            }
            classFactory.Release();
        }
        unknown.Release();
        return result;
    }
    
    public Variant getSiteProperty(final int dispId) {
        for (int i = 0; i < this.sitePropertyIds.length; ++i) {
            if (this.sitePropertyIds[i] == dispId) {
                return this.sitePropertyValues[i];
            }
        }
        return null;
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
    
    private int Invoke(final int dispIdMember, final long riid, final int lcid, final int dwFlags, final long pDispParams, final long pVarResult, final long pExcepInfo, final long pArgErr) {
        if (pVarResult == 0L || dwFlags != 2) {
            if (pExcepInfo != 0L) {
                OS.MoveMemory(pExcepInfo, new long[] { 0L }, C.PTR_SIZEOF);
            }
            if (pArgErr != 0L) {
                OS.MoveMemory(pArgErr, new int[] { 0 }, 4);
            }
            return -2147352573;
        }
        final Variant result = this.getSiteProperty(dispIdMember);
        if (result != null) {
            if (pVarResult != 0L) {
                result.getData(pVarResult);
            }
            return 0;
        }
        switch (dispIdMember) {
            case -714:
            case -712:
            case -711: {
                if (pVarResult != 0L) {
                    OS.MoveMemory(pVarResult, new long[] { 0L }, C.PTR_SIZEOF);
                }
                if (pExcepInfo != 0L) {
                    OS.MoveMemory(pExcepInfo, new long[] { 0L }, C.PTR_SIZEOF);
                }
                if (pArgErr != 0L) {
                    OS.MoveMemory(pArgErr, new int[] { 0 }, 4);
                }
                return 1;
            }
            case -5502:
            case -5501:
            case -706:
            case -705:
            case -704:
            case -703:
            case -701: {
                if (pVarResult != 0L) {
                    OS.MoveMemory(pVarResult, new long[] { 0L }, C.PTR_SIZEOF);
                }
                if (pExcepInfo != 0L) {
                    OS.MoveMemory(pExcepInfo, new long[] { 0L }, C.PTR_SIZEOF);
                }
                if (pArgErr != 0L) {
                    OS.MoveMemory(pArgErr, new int[] { 0 }, 4);
                }
                return -2147467263;
            }
            default: {
                if (pVarResult != 0L) {
                    OS.MoveMemory(pVarResult, new long[] { 0L }, C.PTR_SIZEOF);
                }
                if (pExcepInfo != 0L) {
                    OS.MoveMemory(pExcepInfo, new long[] { 0L }, C.PTR_SIZEOF);
                }
                if (pArgErr != 0L) {
                    OS.MoveMemory(pArgErr, new int[] { 0 }, 4);
                }
                return -2147352573;
            }
        }
    }
    
    private int OnControlInfoChanged() {
        final long[] ppvObject = { 0L };
        if (this.objIUnknown.QueryInterface(COM.IIDIOleControl, ppvObject) == 0) {
            final IOleControl objIOleControl = new IOleControl(ppvObject[0]);
            objIOleControl.GetControlInfo(this.currentControlInfo = new CONTROLINFO());
            objIOleControl.Release();
        }
        return 0;
    }
    
    protected int OnUIDeactivate(final int fUndoable) {
        return super.OnUIDeactivate(fUndoable);
    }
    
    void onFocusIn(final Event e) {
        final String progID = this.getProgramID();
        if (progID == null) {
            return;
        }
        if (!progID.startsWith("Shell.Explorer")) {
            super.onFocusIn(e);
            return;
        }
        if (this.objIOleInPlaceObject == null) {
            return;
        }
        if (!this.isActivated) {
            this.doVerb(-4);
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
        if (this.objIOleInPlaceObject == null) {
            return;
        }
        final String progID = this.getProgramID();
        if (progID == null) {
            return;
        }
        if (!progID.startsWith("Shell.Explorer")) {
            super.onFocusOut(e);
            return;
        }
        if (this.isFocusControl()) {
            return;
        }
        final int threadId = OS.GetCurrentThreadId();
        final GUITHREADINFO lpgui1 = new GUITHREADINFO();
        lpgui1.cbSize = GUITHREADINFO.sizeof;
        OS.GetGUIThreadInfo(threadId, lpgui1);
        this.objIOleInPlaceObject.UIDeactivate();
        if (OleControlSite.SWT_RESTORECARET == 0) {
            OleControlSite.SWT_RESTORECARET = OS.RegisterWindowMessage(new TCHAR(0, "SWT_RESTORECARET", true));
        }
        if (lpgui1.hwndCaret != 0L) {
            final GUITHREADINFO lpgui2 = new GUITHREADINFO();
            lpgui2.cbSize = GUITHREADINFO.sizeof;
            OS.GetGUIThreadInfo(threadId, lpgui2);
            if (lpgui2.hwndCaret == 0L && lpgui1.hwndCaret == OS.GetFocus() && OS.SendMessage(lpgui1.hwndCaret, OleControlSite.SWT_RESTORECARET, 0L, 0L) == 0L) {
                final int width = lpgui1.right - lpgui1.left;
                final int height = lpgui1.bottom - lpgui1.top;
                OS.CreateCaret(lpgui1.hwndCaret, 0L, width, height);
                OS.SetCaretPos(lpgui1.left, lpgui1.top);
                OS.ShowCaret(lpgui1.hwndCaret);
            }
        }
        else if (lpgui1.hwndFocus != 0L && lpgui1.hwndFocus == OS.GetFocus()) {
            OS.SendMessage(lpgui1.hwndFocus, OleControlSite.SWT_RESTORECARET, 0L, 0L);
        }
    }
    
    private int OnFocus(final int fGotFocus) {
        return 0;
    }
    
    protected int QueryInterface(final long riid, final long ppvObject) {
        final int result = super.QueryInterface(riid, ppvObject);
        if (result == 0) {
            return result;
        }
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIOleControlSite)) {
            OS.MoveMemory(ppvObject, new long[] { this.iOleControlSite.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIDispatch)) {
            OS.MoveMemory(ppvObject, new long[] { this.iDispatch.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        return -2147467262;
    }
    
    protected int Release() {
        final int result = super.Release();
        if (result == 0) {
            for (int i = 0; i < this.sitePropertyIds.length; ++i) {
                this.sitePropertyValues[i].dispose();
            }
            this.sitePropertyIds = new int[0];
            this.sitePropertyValues = new Variant[0];
        }
        return result;
    }
    
    protected void releaseObjectInterfaces() {
        this.disconnectEventSinks();
        this.disconnectPropertyChangeSink();
        super.releaseObjectInterfaces();
    }
    
    public void removeEventListener(final int eventID, final OleListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        final GUID riid = getDefaultEventSinkGUID(this.objIUnknown);
        if (riid != null) {
            this.removeEventListener(this.objIUnknown.getAddress(), riid, eventID, listener);
        }
    }
    
    @Deprecated
    public void removeEventListener(final OleAutomation automation, final GUID guid, final int eventID, final OleListener listener) {
        this.checkWidget();
        if (automation == null || listener == null || guid == null) {
            SWT.error(4);
        }
        this.removeEventListener(automation.getAddress(), guid, eventID, listener);
    }
    
    public void removeEventListener(final OleAutomation automation, final int eventID, final OleListener listener) {
        this.checkWidget();
        if (automation == null || listener == null) {
            SWT.error(4);
        }
        final long address = automation.getAddress();
        final IUnknown unknown = new IUnknown(address);
        final GUID riid = getDefaultEventSinkGUID(unknown);
        if (riid != null) {
            this.removeEventListener(address, riid, eventID, listener);
        }
    }
    
    void removeEventListener(final long iunknown, final GUID guid, final int eventID, final OleListener listener) {
        if (listener == null || guid == null) {
            SWT.error(4);
        }
        for (int i = 0; i < this.oleEventSink.length; ++i) {
            if (COM.IsEqualGUID(this.oleEventSinkGUID[i], guid) && iunknown == this.oleEventSinkIUnknown[i]) {
                this.oleEventSink[i].removeListener(eventID, listener);
                if (!this.oleEventSink[i].hasListeners()) {
                    this.oleEventSink[i].disconnect();
                    this.oleEventSink[i].Release();
                    final int oldLength = this.oleEventSink.length;
                    if (oldLength == 1) {
                        this.oleEventSink = new OleEventSink[0];
                        this.oleEventSinkGUID = new GUID[0];
                        this.oleEventSinkIUnknown = new long[0];
                    }
                    else {
                        final OleEventSink[] newOleEventSink = new OleEventSink[oldLength - 1];
                        System.arraycopy(this.oleEventSink, 0, newOleEventSink, 0, i);
                        System.arraycopy(this.oleEventSink, i + 1, newOleEventSink, i, oldLength - i - 1);
                        this.oleEventSink = newOleEventSink;
                        final GUID[] newOleEventSinkGUID = new GUID[oldLength - 1];
                        System.arraycopy(this.oleEventSinkGUID, 0, newOleEventSinkGUID, 0, i);
                        System.arraycopy(this.oleEventSinkGUID, i + 1, newOleEventSinkGUID, i, oldLength - i - 1);
                        this.oleEventSinkGUID = newOleEventSinkGUID;
                        final long[] newOleEventSinkIUnknown = new long[oldLength - 1];
                        System.arraycopy(this.oleEventSinkIUnknown, 0, newOleEventSinkIUnknown, 0, i);
                        System.arraycopy(this.oleEventSinkIUnknown, i + 1, newOleEventSinkIUnknown, i, oldLength - i - 1);
                        this.oleEventSinkIUnknown = newOleEventSinkIUnknown;
                    }
                }
                return;
            }
        }
    }
    
    public void removePropertyListener(final int propertyID, final OleListener listener) {
        if (listener == null) {
            SWT.error(4);
        }
        this.olePropertyChangeSink.removeListener(propertyID, listener);
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        if (this.objIUnknown != null) {
            final OleAutomation oleObject = new OleAutomation((OleClientSite)this);
            oleObject.setProperty(-501, new Variant(color.handle));
            oleObject.dispose();
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        if (this.objIUnknown != null) {
            final OleAutomation oleObject = new OleAutomation((OleClientSite)this);
            final Variant varDispFont = oleObject.getProperty(-512);
            oleObject.dispose();
            if (varDispFont != null) {
                final OleAutomation iDispFont = varDispFont.getAutomation();
                final FontData[] fdata = font.getFontData();
                iDispFont.setProperty(0, new Variant(fdata[0].getName()));
                iDispFont.setProperty(2, new Variant(fdata[0].getHeight()));
                iDispFont.setProperty(4, new Variant(fdata[0].getStyle() & 0x2));
                iDispFont.setProperty(3, new Variant(fdata[0].getStyle() & 0x1));
                iDispFont.dispose();
            }
        }
        this.font = font;
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        if (this.objIUnknown != null) {
            final OleAutomation oleObject = new OleAutomation((OleClientSite)this);
            oleObject.setProperty(-513, new Variant(color.handle));
            oleObject.dispose();
        }
    }
    
    public void setSiteProperty(final int dispId, final Variant value) {
        for (int i = 0; i < this.sitePropertyIds.length; ++i) {
            if (this.sitePropertyIds[i] == dispId) {
                if (this.sitePropertyValues[i] != null) {
                    this.sitePropertyValues[i].dispose();
                }
                if (value != null) {
                    this.sitePropertyValues[i] = value;
                }
                else {
                    final int oldLength = this.sitePropertyIds.length;
                    final int[] newSitePropertyIds = new int[oldLength - 1];
                    final Variant[] newSitePropertyValues = new Variant[oldLength - 1];
                    System.arraycopy(this.sitePropertyIds, 0, newSitePropertyIds, 0, i);
                    System.arraycopy(this.sitePropertyIds, i + 1, newSitePropertyIds, i, oldLength - i - 1);
                    System.arraycopy(this.sitePropertyValues, 0, newSitePropertyValues, 0, i);
                    System.arraycopy(this.sitePropertyValues, i + 1, newSitePropertyValues, i, oldLength - i - 1);
                    this.sitePropertyIds = newSitePropertyIds;
                    this.sitePropertyValues = newSitePropertyValues;
                }
                return;
            }
        }
        final int oldLength2 = this.sitePropertyIds.length;
        final int[] newSitePropertyIds2 = new int[oldLength2 + 1];
        final Variant[] newSitePropertyValues2 = new Variant[oldLength2 + 1];
        System.arraycopy(this.sitePropertyIds, 0, newSitePropertyIds2, 0, oldLength2);
        System.arraycopy(this.sitePropertyValues, 0, newSitePropertyValues2, 0, oldLength2);
        newSitePropertyIds2[oldLength2] = dispId;
        newSitePropertyValues2[oldLength2] = value;
        this.sitePropertyIds = newSitePropertyIds2;
        this.sitePropertyValues = newSitePropertyValues2;
    }
}
