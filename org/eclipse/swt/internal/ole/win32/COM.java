//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class COM extends OS
{
    public static final GUID CLSID_CMultiLanguage;
    public static final GUID CLSID_DestinationList;
    public static final GUID CLSID_DragDropHelper;
    public static final GUID CLSID_EnumerableObjectCollection;
    public static final GUID CLSID_FileOpenDialog;
    public static final GUID CLSID_FileSaveDialog;
    public static final GUID CLSID_ShellLink;
    public static final GUID CLSID_TaskbarList;
    public static final GUID CLSID_TF_InputProcessorProfiles;
    public static final GUID GUID_TFCAT_TIP_KEYBOARD;
    public static final GUID IID_ICustomDestinationList;
    public static final GUID IID_IDropTargetHelper;
    public static final GUID IID_IFileOpenDialog;
    public static final GUID IID_IFileSaveDialog;
    public static final GUID IID_IMLangFontLink2;
    public static final GUID IID_IObjectArray;
    public static final GUID IID_IObjectCollection;
    public static final GUID IID_IPropertyStore;
    public static final GUID IID_IShellItem;
    public static final GUID IID_IShellLinkW;
    public static final GUID IID_ITaskbarList3;
    public static final GUID IID_ITfDisplayAttributeProvider;
    public static final GUID IID_ITfInputProcessorProfiles;
    public static final GUID IIDJavaBeansBridge;
    public static final GUID IIDShockwaveActiveXControl;
    public static final GUID IIDIAccessible;
    public static final GUID IIDIAdviseSink;
    public static final GUID IIDIClassFactory;
    public static final GUID IIDIClassFactory2;
    public static final GUID IIDIConnectionPointContainer;
    public static final GUID IIDIDataObject;
    public static final GUID IIDIDispatch;
    public static final GUID IIDIDispatchEx;
    public static final GUID IIDIDocHostUIHandler;
    public static final GUID IIDIDocHostShowUI;
    public static final GUID IIDIDropSource;
    public static final GUID IIDIDropTarget;
    public static final GUID IIDIEnumFORMATETC;
    public static final GUID IIDIEnumVARIANT;
    public static final String IIDIHTMLDocumentEvents2 = "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}";
    public static final GUID IIDIInternetSecurityManager;
    public static final GUID IIDIAuthenticate;
    public static final GUID IIDIJScriptTypeInfo;
    public static final GUID IIDIOleClientSite;
    public static final GUID IIDIOleCommandTarget;
    public static final GUID IIDIOleControl;
    public static final GUID IIDIOleControlSite;
    public static final GUID IIDIOleDocument;
    public static final GUID IIDIOleDocumentSite;
    public static final GUID IIDIOleInPlaceFrame;
    public static final GUID IIDIOleInPlaceObject;
    public static final GUID IIDIOleInPlaceSite;
    public static final GUID IIDIOleLink;
    public static final GUID IIDIOleObject;
    public static final GUID IIDIPersist;
    public static final GUID IIDIPersistFile;
    public static final GUID IIDIPersistStorage;
    public static final GUID IIDIPersistStreamInit;
    public static final GUID IIDIPropertyNotifySink;
    public static final GUID IIDIProvideClassInfo;
    public static final GUID IIDIProvideClassInfo2;
    public static final GUID IIDIServiceProvider;
    public static final GUID IIDISpecifyPropertyPages;
    public static final GUID IIDIUnknown;
    public static final GUID IIDIViewObject2;
    public static final GUID CGID_DocHostCommandHandler;
    public static final GUID CGID_Explorer;
    public static final GUID IID_ICoreWebView2Environment2;
    public static final GUID IID_ICoreWebView2_2;
    public static final GUID IIDIAccessible2;
    public static final GUID IIDIAccessibleRelation;
    public static final GUID IIDIAccessibleAction;
    public static final GUID IIDIAccessibleComponent;
    public static final GUID IIDIAccessibleValue;
    public static final GUID IIDIAccessibleText;
    public static final GUID IIDIAccessibleEditableText;
    public static final GUID IIDIAccessibleHyperlink;
    public static final GUID IIDIAccessibleHypertext;
    public static final GUID IIDIAccessibleTable;
    public static final GUID IIDIAccessibleTable2;
    public static final GUID IIDIAccessibleTableCell;
    public static final GUID IIDIAccessibleImage;
    public static final GUID IIDIAccessibleApplication;
    public static final GUID IIDIAccessibleContext;
    public static final int CF_TEXT = 1;
    public static final int CF_BITMAP = 2;
    public static final int CF_METAFILEPICT = 3;
    public static final int CF_SYLK = 4;
    public static final int CF_DIF = 5;
    public static final int CF_TIFF = 6;
    public static final int CF_OEMTEXT = 7;
    public static final int CF_DIB = 8;
    public static final int CF_PALETTE = 9;
    public static final int CF_PENDATA = 10;
    public static final int CF_RIFF = 11;
    public static final int CF_WAVE = 12;
    public static final int CF_UNICODETEXT = 13;
    public static final int CF_ENHMETAFILE = 14;
    public static final int CF_HDROP = 15;
    public static final int CF_LOCALE = 16;
    public static final int CF_MAX = 17;
    public static final int CLSCTX_INPROC_HANDLER = 2;
    public static final int CLSCTX_INPROC_SERVER = 1;
    public static final int CLSCTX_LOCAL_SERVER = 4;
    public static final int DATADIR_GET = 1;
    public static final int DATADIR_SET = 2;
    public static final int DISPATCH_CONSTRUCT = 16384;
    public static final int DISP_E_EXCEPTION = -2147352567;
    public static final int DISP_E_MEMBERNOTFOUND = -2147352573;
    public static final int DISP_E_UNKNOWNINTERFACE = -2147352575;
    public static final int DISP_E_UNKNOWNNAME = -2147352570;
    public static final int DISPID_AMBIENT_BACKCOLOR = -701;
    public static final int DISPID_AMBIENT_FONT = -703;
    public static final int DISPID_AMBIENT_FORECOLOR = -704;
    public static final int DISPID_AMBIENT_LOCALEID = -705;
    public static final int DISPID_AMBIENT_MESSAGEREFLECT = -706;
    public static final int DISPID_AMBIENT_OFFLINEIFNOTCONNECTED = -5501;
    public static final int DISPID_AMBIENT_SHOWGRABHANDLES = -711;
    public static final int DISPID_AMBIENT_SHOWHATCHING = -712;
    public static final int DISPID_AMBIENT_SILENT = -5502;
    public static final int DISPID_AMBIENT_SUPPORTSMNEMONICS = -714;
    public static final int DISPID_AMBIENT_UIDEAD = -710;
    public static final int DISPID_AMBIENT_USERMODE = -709;
    public static final int DISPID_BACKCOLOR = -501;
    public static final int DISPID_FONT = -512;
    public static final int DISPID_FONT_BOLD = 3;
    public static final int DISPID_FONT_CHARSET = 8;
    public static final int DISPID_FONT_ITALIC = 4;
    public static final int DISPID_FONT_NAME = 0;
    public static final int DISPID_FONT_SIZE = 2;
    public static final int DISPID_FONT_STRIKE = 6;
    public static final int DISPID_FONT_UNDER = 5;
    public static final int DISPID_FONT_WEIGHT = 7;
    public static final int DISPID_FORECOLOR = -513;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONDBLCLICK = -601;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONDRAGEND = -2147418091;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONDRAGSTART = -2147418101;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONKEYDOWN = -602;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONKEYPRESS = -603;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONKEYUP = -604;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEOUT = -2147418103;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEOVER = -2147418104;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEMOVE = -606;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEDOWN = -605;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEUP = -607;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEWHEEL = 1033;
    public static final int DRAGDROP_S_DROP = 262400;
    public static final int DRAGDROP_S_CANCEL = 262401;
    public static final int DRAGDROP_S_USEDEFAULTCURSORS = 262402;
    public static final int DROPEFFECT_NONE = 0;
    public static final int DROPEFFECT_COPY = 1;
    public static final int DROPEFFECT_MOVE = 2;
    public static final int DROPEFFECT_LINK = 4;
    public static final int DV_E_FORMATETC = -2147221404;
    public static final int DV_E_STGMEDIUM = -2147221402;
    public static final int DV_E_TYMED = -2147221399;
    public static final int DVASPECT_CONTENT = 1;
    public static final int E_ACCESSDENIED = -2147024891;
    public static final int E_FAIL = -2147467259;
    public static final int E_INVALIDARG = -2147024809;
    public static final int E_NOINTERFACE = -2147467262;
    public static final int E_NOTIMPL = -2147467263;
    public static final int E_NOTSUPPORTED = -2147221248;
    public static final int E_OUTOFMEMORY = -2147024882;
    public static final int GMEM_FIXED = 0;
    public static final int GMEM_ZEROINIT = 64;
    public static final int GUIDKIND_DEFAULT_SOURCE_DISP_IID = 1;
    public static final int IMPLTYPEFLAG_FDEFAULT = 1;
    public static final int IMPLTYPEFLAG_FRESTRICTED = 4;
    public static final int IMPLTYPEFLAG_FSOURCE = 2;
    public static final int LOCALE_USER_DEFAULT = 2048;
    public static final int OLECLOSE_NOSAVE = 1;
    public static final int OLECLOSE_SAVEIFDIRTY = 0;
    public static final int OLEEMBEDDED = 1;
    public static final int OLELINKED = 0;
    public static final int OLERENDER_DRAW = 1;
    public static final int REGDB_E_CLASSNOTREG = -2147221164;
    public static final int S_FALSE = 1;
    public static final int S_OK = 0;
    public static final int STGC_DEFAULT = 0;
    public static final int STGM_CREATE = 4096;
    public static final int STGM_DELETEONRELEASE = 67108864;
    public static final int STGM_DIRECT = 0;
    public static final int STGM_READ = 0;
    public static final int STGM_READWRITE = 2;
    public static final int STGM_SHARE_EXCLUSIVE = 16;
    public static final int STGM_TRANSACTED = 65536;
    public static final int TYMED_HGLOBAL = 1;
    public static final short DISPATCH_METHOD = 1;
    public static final short DISPATCH_PROPERTYGET = 2;
    public static final short DISPATCH_PROPERTYPUT = 4;
    public static final short DISPATCH_PROPERTYPUTREF = 8;
    public static final short DISPID_PROPERTYPUT = -3;
    public static final short DISPID_UNKNOWN = -1;
    public static final short DISPID_VALUE = 0;
    public static final short VT_BOOL = 11;
    public static final short VT_BSTR = 8;
    public static final short VT_BYREF = 16384;
    public static final short VT_DATE = 7;
    public static final short VT_DISPATCH = 9;
    public static final short VT_EMPTY = 0;
    public static final short VT_I1 = 16;
    public static final short VT_I2 = 2;
    public static final short VT_I4 = 3;
    public static final short VT_I8 = 20;
    public static final short VT_NULL = 1;
    public static final short VT_R4 = 4;
    public static final short VT_R8 = 5;
    public static final short VT_UI1 = 17;
    public static final short VT_UI2 = 18;
    public static final short VT_UI4 = 19;
    public static final short VT_UNKNOWN = 13;
    public static final short VT_VARIANT = 12;
    public static final int COREWEBVIEW2_MOVE_FOCUS_REASON_PROGRAMMATIC = 0;
    public static final int COREWEBVIEW2_MOVE_FOCUS_REASON_NEXT = 1;
    public static final int COREWEBVIEW2_MOVE_FOCUS_REASON_PREVIOUS = 2;
    public static boolean FreeUnusedLibraries;
    public static final int CHILDID_SELF = 0;
    public static final int CO_E_OBJNOTCONNECTED = -2147220995;
    public static final int ROLE_SYSTEM_MENUBAR = 2;
    public static final int ROLE_SYSTEM_SCROLLBAR = 3;
    public static final int ROLE_SYSTEM_ALERT = 8;
    public static final int ROLE_SYSTEM_WINDOW = 9;
    public static final int ROLE_SYSTEM_CLIENT = 10;
    public static final int ROLE_SYSTEM_MENUPOPUP = 11;
    public static final int ROLE_SYSTEM_MENUITEM = 12;
    public static final int ROLE_SYSTEM_TOOLTIP = 13;
    public static final int ROLE_SYSTEM_DOCUMENT = 15;
    public static final int ROLE_SYSTEM_DIALOG = 18;
    public static final int ROLE_SYSTEM_GROUPING = 20;
    public static final int ROLE_SYSTEM_SEPARATOR = 21;
    public static final int ROLE_SYSTEM_TOOLBAR = 22;
    public static final int ROLE_SYSTEM_STATUSBAR = 23;
    public static final int ROLE_SYSTEM_TABLE = 24;
    public static final int ROLE_SYSTEM_COLUMNHEADER = 25;
    public static final int ROLE_SYSTEM_ROWHEADER = 26;
    public static final int ROLE_SYSTEM_COLUMN = 27;
    public static final int ROLE_SYSTEM_ROW = 28;
    public static final int ROLE_SYSTEM_CELL = 29;
    public static final int ROLE_SYSTEM_LINK = 30;
    public static final int ROLE_SYSTEM_LIST = 33;
    public static final int ROLE_SYSTEM_LISTITEM = 34;
    public static final int ROLE_SYSTEM_OUTLINE = 35;
    public static final int ROLE_SYSTEM_OUTLINEITEM = 36;
    public static final int ROLE_SYSTEM_PAGETAB = 37;
    public static final int ROLE_SYSTEM_GRAPHIC = 40;
    public static final int ROLE_SYSTEM_STATICTEXT = 41;
    public static final int ROLE_SYSTEM_TEXT = 42;
    public static final int ROLE_SYSTEM_PUSHBUTTON = 43;
    public static final int ROLE_SYSTEM_CHECKBUTTON = 44;
    public static final int ROLE_SYSTEM_RADIOBUTTON = 45;
    public static final int ROLE_SYSTEM_COMBOBOX = 46;
    public static final int ROLE_SYSTEM_DROPLIST = 47;
    public static final int ROLE_SYSTEM_PROGRESSBAR = 48;
    public static final int ROLE_SYSTEM_SLIDER = 51;
    public static final int ROLE_SYSTEM_SPINBUTTON = 52;
    public static final int ROLE_SYSTEM_ANIMATION = 54;
    public static final int ROLE_SYSTEM_PAGETABLIST = 60;
    public static final int ROLE_SYSTEM_CLOCK = 61;
    public static final int ROLE_SYSTEM_SPLITBUTTON = 62;
    public static final int STATE_SYSTEM_NORMAL = 0;
    public static final int STATE_SYSTEM_UNAVAILABLE = 1;
    public static final int STATE_SYSTEM_SELECTED = 2;
    public static final int STATE_SYSTEM_FOCUSED = 4;
    public static final int STATE_SYSTEM_PRESSED = 8;
    public static final int STATE_SYSTEM_CHECKED = 16;
    public static final int STATE_SYSTEM_MIXED = 32;
    public static final int STATE_SYSTEM_READONLY = 64;
    public static final int STATE_SYSTEM_HOTTRACKED = 128;
    public static final int STATE_SYSTEM_EXPANDED = 512;
    public static final int STATE_SYSTEM_COLLAPSED = 1024;
    public static final int STATE_SYSTEM_BUSY = 2048;
    public static final int STATE_SYSTEM_INVISIBLE = 32768;
    public static final int STATE_SYSTEM_OFFSCREEN = 65536;
    public static final int STATE_SYSTEM_SIZEABLE = 131072;
    public static final int STATE_SYSTEM_FOCUSABLE = 1048576;
    public static final int STATE_SYSTEM_SELECTABLE = 2097152;
    public static final int STATE_SYSTEM_LINKED = 4194304;
    public static final int STATE_SYSTEM_MULTISELECTABLE = 16777216;
    public static final int EVENT_OBJECT_SELECTIONWITHIN = 32777;
    public static final int EVENT_OBJECT_STATECHANGE = 32778;
    public static final int EVENT_OBJECT_LOCATIONCHANGE = 32779;
    public static final int EVENT_OBJECT_NAMECHANGE = 32780;
    public static final int EVENT_OBJECT_DESCRIPTIONCHANGE = 32781;
    public static final int EVENT_OBJECT_VALUECHANGE = 32782;
    public static final int EVENT_OBJECT_TEXTSELECTIONCHANGED = 32788;
    public static final int IA2_COORDTYPE_SCREEN_RELATIVE = 0;
    public static final int IA2_COORDTYPE_PARENT_RELATIVE = 1;
    public static final int IA2_STATE_ACTIVE = 1;
    public static final int IA2_STATE_SINGLE_LINE = 8192;
    public static final int IA2_STATE_MULTI_LINE = 512;
    public static final int IA2_STATE_REQUIRED = 2048;
    public static final int IA2_STATE_INVALID_ENTRY = 64;
    public static final int IA2_STATE_SUPPORTS_AUTOCOMPLETION = 32768;
    public static final int IA2_STATE_EDITABLE = 8;
    public static final int IA2_EVENT_DOCUMENT_LOAD_COMPLETE = 261;
    public static final int IA2_EVENT_DOCUMENT_LOAD_STOPPED = 262;
    public static final int IA2_EVENT_DOCUMENT_RELOAD = 263;
    public static final int IA2_EVENT_PAGE_CHANGED = 273;
    public static final int IA2_EVENT_SECTION_CHANGED = 274;
    public static final int IA2_EVENT_ACTION_CHANGED = 257;
    public static final int IA2_EVENT_HYPERLINK_START_INDEX_CHANGED = 269;
    public static final int IA2_EVENT_HYPERLINK_END_INDEX_CHANGED = 264;
    public static final int IA2_EVENT_HYPERLINK_ANCHOR_COUNT_CHANGED = 265;
    public static final int IA2_EVENT_HYPERLINK_SELECTED_LINK_CHANGED = 266;
    public static final int IA2_EVENT_HYPERLINK_ACTIVATED = 267;
    public static final int IA2_EVENT_HYPERTEXT_LINK_SELECTED = 268;
    public static final int IA2_EVENT_HYPERTEXT_LINK_COUNT_CHANGED = 271;
    public static final int IA2_EVENT_ATTRIBUTE_CHANGED = 272;
    public static final int IA2_EVENT_TABLE_CAPTION_CHANGED = 275;
    public static final int IA2_EVENT_TABLE_COLUMN_DESCRIPTION_CHANGED = 276;
    public static final int IA2_EVENT_TABLE_COLUMN_HEADER_CHANGED = 277;
    public static final int IA2_EVENT_TABLE_CHANGED = 278;
    public static final int IA2_EVENT_TABLE_ROW_DESCRIPTION_CHANGED = 279;
    public static final int IA2_EVENT_TABLE_ROW_HEADER_CHANGED = 280;
    public static final int IA2_EVENT_TABLE_SUMMARY_CHANGED = 281;
    public static final int IA2_EVENT_TEXT_ATTRIBUTE_CHANGED = 282;
    public static final int IA2_EVENT_TEXT_CARET_MOVED = 283;
    public static final int IA2_EVENT_TEXT_COLUMN_CHANGED = 285;
    public static final int IA2_EVENT_TEXT_INSERTED = 286;
    public static final int IA2_EVENT_TEXT_REMOVED = 287;
    public static final int IA2_TEXT_BOUNDARY_CHAR = 0;
    public static final int IA2_TEXT_BOUNDARY_WORD = 1;
    public static final int IA2_TEXT_BOUNDARY_SENTENCE = 2;
    public static final int IA2_TEXT_BOUNDARY_PARAGRAPH = 3;
    public static final int IA2_TEXT_BOUNDARY_LINE = 4;
    public static final int IA2_TEXT_BOUNDARY_ALL = 5;
    public static final int IA2_TEXT_OFFSET_LENGTH = -1;
    public static final int IA2_TEXT_OFFSET_CARET = -2;
    public static final int IA2_SCROLL_TYPE_TOP_LEFT = 0;
    public static final int IA2_SCROLL_TYPE_BOTTOM_RIGHT = 1;
    public static final int IA2_SCROLL_TYPE_TOP_EDGE = 2;
    public static final int IA2_SCROLL_TYPE_BOTTOM_EDGE = 3;
    public static final int IA2_SCROLL_TYPE_LEFT_EDGE = 4;
    public static final int IA2_SCROLL_TYPE_RIGHT_EDGE = 5;
    public static final int IA2_SCROLL_TYPE_ANYWHERE = 6;
    
    private static GUID IIDFromString(final String lpsz) {
        final int length = lpsz.length();
        final char[] buffer = new char[length + 1];
        lpsz.getChars(0, length, buffer, 0);
        final GUID lpiid = new GUID();
        if (IIDFromString(buffer, lpiid) == 0) {
            return lpiid;
        }
        return null;
    }
    
    public static final native int CLSIDFromProgID(final char[] p0, final GUID p1);
    
    public static final native int CLSIDFromString(final char[] p0, final GUID p1);
    
    public static final native int CoCreateInstance(final GUID p0, final long p1, final int p2, final GUID p3, final long[] p4);
    
    public static final native void CoFreeUnusedLibraries();
    
    public static final native int CoGetClassObject(final GUID p0, final int p1, final long p2, final GUID p3, final long[] p4);
    
    public static final native int CoLockObjectExternal(final long p0, final boolean p1, final boolean p2);
    
    public static final native int DoDragDrop(final long p0, final long p1, final int p2, final int[] p3);
    
    public static final native int GetClassFile(final char[] p0, final GUID p1);
    
    public static final native int IIDFromString(final char[] p0, final GUID p1);
    
    public static final native boolean IsEqualGUID(final GUID p0, final GUID p1);
    
    public static final native void MoveMemory(final long p0, final FORMATETC p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final OLEINPLACEFRAMEINFO p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final STGMEDIUM p1, final int p2);
    
    public static final native void MoveMemory(final STGMEDIUM p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final DISPPARAMS p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final FORMATETC p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final GUID p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final TYPEATTR p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final RECT p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final FUNCDESC p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final VARDESC p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final VARIANT p0, final long p1, final int p2);
    
    public static final native int OleCreate(final GUID p0, final GUID p1, final int p2, final FORMATETC p3, final long p4, final long p5, final long[] p6);
    
    public static final native int OleCreateFromFile(final GUID p0, final char[] p1, final GUID p2, final int p3, final FORMATETC p4, final long p5, final long p6, final long[] p7);
    
    public static final native int OleCreatePropertyFrame(final long p0, final int p1, final int p2, final char[] p3, final int p4, final long[] p5, final int p6, final long p7, final int p8, final int p9, final long p10);
    
    public static final native int OleDraw(final long p0, final int p1, final long p2, final long p3);
    
    public static final native int OleFlushClipboard();
    
    public static final native int OleGetClipboard(final long[] p0);
    
    public static final native int OleIsCurrentClipboard(final long p0);
    
    public static final native boolean OleIsRunning(final long p0);
    
    public static final native int OleRun(final long p0);
    
    public static final native int OleSave(final long p0, final long p1, final boolean p2);
    
    public static final native int OleSetClipboard(final long p0);
    
    public static final native int OleSetContainedObject(final long p0, final boolean p1);
    
    public static final native int OleSetMenuDescriptor(final long p0, final long p1, final long p2, final long p3, final long p4);
    
    public static final native int OleTranslateColor(final int p0, final long p1, final int[] p2);
    
    public static final native int PathToPIDL(final char[] p0, final long[] p1);
    
    public static final native int ProgIDFromCLSID(final GUID p0, final long[] p1);
    
    public static final native int RegisterDragDrop(final long p0, final long p1);
    
    public static final native void ReleaseStgMedium(final long p0);
    
    public static final native int RevokeDragDrop(final long p0);
    
    public static final native int SHCreateItemFromParsingName(final char[] p0, final long p1, final GUID p2, final long[] p3);
    
    public static final native int StgCreateDocfile(final char[] p0, final int p1, final int p2, final long[] p3);
    
    public static final native long SHCreateMemStream(final byte[] p0, final int p1);
    
    public static final native int StgIsStorageFile(final char[] p0);
    
    public static final native int StgOpenStorage(final char[] p0, final long p1, final int p2, final long p3, final int p4, final long[] p5);
    
    public static final native long SysAllocString(final char[] p0);
    
    public static final native long SysAllocStringLen(final char[] p0, final int p1);
    
    public static final native void SysFreeString(final long p0);
    
    public static final native int SysStringByteLen(final long p0);
    
    public static final native int SysStringLen(final long p0);
    
    public static final native int VariantChangeType(final long p0, final long p1, final short p2, final short p3);
    
    public static final native int VariantClear(final long p0);
    
    public static final native void VariantInit(final long p0);
    
    public static final native int WriteClassStg(final long p0, final GUID p1);
    
    public static final native int CreateCoreWebView2EnvironmentWithOptions(final char[] p0, final char[] p1, final long p2, final long p3);
    
    public static final native long CreateSwtWebView2Callback(final ICoreWebView2SwtCallback p0);
    
    public static final native long CreateSwtWebView2Host(final ICoreWebView2SwtHost p0);
    
    public static final native long CreateSwtWebView2Options();
    
    public static final native int VtblCall(final int p0, final long p1);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final long p3, final int p4, final long[] p5);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final long p3, final int p4, final long[] p5);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final int p3, final int p4, final long[] p5);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final int p3, final int p4, final int[] p5, final int[] p6);
    
    public static final native int VtblCall(final int p0, final long p1, final int[] p2);
    
    public static final native int VtblCall(final int p0, final long p1, final long[] p2);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final long[] p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final long p1, final TF_DISPLAYATTRIBUTE p2);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final long p3, final long p4);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final long p3, final long p4);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final long p3);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final long p3);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final int p3);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final long p3);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final long[] p3);
    
    public static final native int VtblCall(final int p0, final long p1, final PROPERTYKEY p2, final long p3);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final int[] p3);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final int[] p3);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final long[] p3);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final char[] p3);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final long p3, final POINT p4, final int p5);
    
    public static final native int VtblCall(final int p0, final long p1, final int[] p2, final GUID p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final POINT p3, final long p4);
    
    public static final native int VtblCall(final int p0, final long p1, final POINT p2, final int p3);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final int p3, final int p4, final int p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final long p3, final int p4, final int p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final long p3, final int p4, final int p5, final int p6, final long[] p7);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final char[] p3, final long p4, final char[] p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final long[] p3);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final int p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final long p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final long p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final int p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final int p3, final long p4, final SIZE p5);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final long p3, final GUID p4, final long p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final GUID p3);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final GUID p3, final long p4, final long p5);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final GUID p3, final int p4, final int p5, final DISPPARAMS p6, final long p7, final EXCEPINFO p8, final int[] p9);
    
    public static final native int VtblCall(final int p0, final long p1, final MSG p2);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final MSG p3, final long p4, final int p5, final long p6, final RECT p7);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final SIZE p3);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final int p3);
    
    public static final native int VtblCall(final int p0, final long p1, final CAUUID p2);
    
    public static final native int VtblCall(final int p0, final long p1, final CONTROLINFO p2);
    
    public static final native int VtblCall(final int p0, final long p1, final FORMATETC p2);
    
    public static final native int VtblCall(final int p0, final long p1, final FORMATETC p2, final STGMEDIUM p3);
    
    public static final native int VtblCall(final int p0, final long p1, final GUID p2);
    
    public static final native int VtblCall(final int p0, final long p1, final GUID p2, final long[] p3);
    
    public static final native int VtblCall(final int p0, final long p1, final GUID p2, final GUID p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final long p1, final GUID p2, final long p3, final int p4, final int p5, final int[] p6);
    
    public static final native int VtblCall(final int p0, final long p1, final GUID p2, final int p3, final int p4, final long p5, final long p6);
    
    public static final native int VtblCall(final int p0, final long p1, final GUID p2, final int p3, final OLECMD p4, final long p5);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final GUID p3, final GUID p4, final GUID p5);
    
    public static final native int VtblCall(final int p0, final long p1, final LICINFO p2);
    
    public static final native int VtblCall(final int p0, final long p1, final RECT p2, final long p3, final int p4);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final long p3, final long p4, final long p5, final long p6);
    
    public static final native int VtblCall(final int p0, final long p1, final RECT p2, final RECT p3);
    
    public static final native int VtblCall(final int p0, final long p1, final RECT p2);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final long[] p3, final long[] p4, final int[] p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final long[] p3, final int p4, final int[] p5);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final int p3, final int p4, final DISPPARAMS p5, final long p6, final EXCEPINFO p7, final long p8);
    
    public static final native int VtblCall(final int p0, final long p1, final char[] p2, final char[] p3, final char[] p4, final char[] p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final long p1, final double p2);
    
    public static final native int VtblCall(final int p0, final long p1, final RECT p2, final long p3, final long p4);
    
    public static final native int VtblCall_put_Bounds(final int p0, final long p1, final RECT p2);
    
    public static final native int CreateStdAccessibleObject(final long p0, final int p1, final GUID p2, final long[] p3);
    
    public static final native long LresultFromObject(final GUID p0, final long p1, final long p2);
    
    public static final native int CAUUID_sizeof();
    
    public static final native int CONTROLINFO_sizeof();
    
    public static final native int DISPPARAMS_sizeof();
    
    public static final native int ELEMDESC_sizeof();
    
    public static final native int EXCEPINFO_sizeof();
    
    public static final native int FORMATETC_sizeof();
    
    public static final native int FUNCDESC_sizeof();
    
    public static final native int GUID_sizeof();
    
    public static final native int LICINFO_sizeof();
    
    public static final native int OLECMD_sizeof();
    
    public static final native int OLEINPLACEFRAMEINFO_sizeof();
    
    public static final native int STGMEDIUM_sizeof();
    
    public static final native int TYPEATTR_sizeof();
    
    public static final native int TYPEDESC_sizeof();
    
    public static final native int VARDESC_sizeof();
    
    public static final native int VARIANT_sizeof();
    
    static {
        CLSID_CMultiLanguage = IIDFromString("{275c23e2-3747-11d0-9fea-00aa003f8646}");
        CLSID_DestinationList = IIDFromString("{77f10cf0-3db5-4966-b520-b7c54fd35ed6}");
        CLSID_DragDropHelper = IIDFromString("{4657278A-411B-11d2-839A-00C04FD918D0}");
        CLSID_EnumerableObjectCollection = IIDFromString("{2d3468c1-36a7-43b6-ac24-d3f02fd9607a}");
        CLSID_FileOpenDialog = IIDFromString("{DC1C5A9C-E88A-4dde-A5A1-60F82A20AEF7}");
        CLSID_FileSaveDialog = IIDFromString("{C0B4E2F3-BA21-4773-8DBA-335EC946EB8B}");
        CLSID_ShellLink = IIDFromString("{00021401-0000-0000-C000-000000000046}");
        CLSID_TaskbarList = IIDFromString("{56FDF344-FD6D-11d0-958A-006097C9A090}");
        CLSID_TF_InputProcessorProfiles = IIDFromString("{33C53A50-F456-4884-B049-85FD643ECFED}");
        GUID_TFCAT_TIP_KEYBOARD = IIDFromString("{34745C63-B2F0-4784-8B67-5E12C8701A31}");
        IID_ICustomDestinationList = IIDFromString("{6332debf-87b5-4670-90c0-5e57b408a49e}");
        IID_IDropTargetHelper = IIDFromString("{4657278B-411B-11D2-839A-00C04FD918D0}");
        IID_IFileOpenDialog = IIDFromString("{d57c7288-d4ad-4768-be02-9d969532d960}");
        IID_IFileSaveDialog = IIDFromString("{84bccd23-5fde-4cdb-aea4-af64b83d78ab}");
        IID_IMLangFontLink2 = IIDFromString("{DCCFC162-2B38-11d2-B7EC-00C04F8F5D9A}");
        IID_IObjectArray = IIDFromString("{92CA9DCD-5622-4bba-A805-5E9F541BD8C9}");
        IID_IObjectCollection = IIDFromString("{5632b1a4-e38a-400a-928a-d4cd63230295}");
        IID_IPropertyStore = IIDFromString("{886d8eeb-8cf2-4446-8d02-cdba1dbdcf99}");
        IID_IShellItem = IIDFromString("{43826d1e-e718-42ee-bc55-a1e261c37bfe}");
        IID_IShellLinkW = IIDFromString("{000214F9-0000-0000-C000-000000000046}");
        IID_ITaskbarList3 = IIDFromString("{ea1afb91-9e28-4b86-90e9-9e9f8a5eefaf}");
        IID_ITfDisplayAttributeProvider = IIDFromString("{fee47777-163c-4769-996a-6e9c50ad8f54}");
        IID_ITfInputProcessorProfiles = IIDFromString("{1F02B6C5-7842-4EE6-8A0B-9A24183A95CA}");
        IIDJavaBeansBridge = IIDFromString("{8AD9C840-044E-11D1-B3E9-00805F499D93}");
        IIDShockwaveActiveXControl = IIDFromString("{166B1BCA-3F9C-11CF-8075-444553540000}");
        IIDIAccessible = IIDFromString("{618736E0-3C3D-11CF-810C-00AA00389B71}");
        IIDIAdviseSink = IIDFromString("{0000010F-0000-0000-C000-000000000046}");
        IIDIClassFactory = IIDFromString("{00000001-0000-0000-C000-000000000046}");
        IIDIClassFactory2 = IIDFromString("{B196B28F-BAB4-101A-B69C-00AA00341D07}");
        IIDIConnectionPointContainer = IIDFromString("{B196B284-BAB4-101A-B69C-00AA00341D07}");
        IIDIDataObject = IIDFromString("{0000010E-0000-0000-C000-000000000046}");
        IIDIDispatch = IIDFromString("{00020400-0000-0000-C000-000000000046}");
        IIDIDispatchEx = IIDFromString("{A6EF9860-C720-11D0-9337-00A0C90DCAA9}");
        IIDIDocHostUIHandler = IIDFromString("{BD3F23C0-D43E-11CF-893B-00AA00BDCE1A}");
        IIDIDocHostShowUI = IIDFromString("{C4D244B0-D43E-11CF-893B-00AA00BDCE1A}");
        IIDIDropSource = IIDFromString("{00000121-0000-0000-C000-000000000046}");
        IIDIDropTarget = IIDFromString("{00000122-0000-0000-C000-000000000046}");
        IIDIEnumFORMATETC = IIDFromString("{00000103-0000-0000-C000-000000000046}");
        IIDIEnumVARIANT = IIDFromString("{00020404-0000-0000-C000-000000000046}");
        IIDIInternetSecurityManager = IIDFromString("{79eac9ee-baf9-11ce-8c82-00aa004ba90b}");
        IIDIAuthenticate = IIDFromString("{79eac9d0-baf9-11ce-8c82-00aa004ba90b}");
        IIDIJScriptTypeInfo = IIDFromString("{C59C6B12-F6C1-11CF-8835-00A0C911E8B2}");
        IIDIOleClientSite = IIDFromString("{00000118-0000-0000-C000-000000000046}");
        IIDIOleCommandTarget = IIDFromString("{B722BCCB-4E68-101B-A2BC-00AA00404770}");
        IIDIOleControl = IIDFromString("{B196B288-BAB4-101A-B69C-00AA00341D07}");
        IIDIOleControlSite = IIDFromString("{B196B289-BAB4-101A-B69C-00AA00341D07}");
        IIDIOleDocument = IIDFromString("{B722BCC5-4E68-101B-A2BC-00AA00404770}");
        IIDIOleDocumentSite = IIDFromString("{B722BCC7-4E68-101B-A2BC-00AA00404770}");
        IIDIOleInPlaceFrame = IIDFromString("{00000116-0000-0000-C000-000000000046}");
        IIDIOleInPlaceObject = IIDFromString("{00000113-0000-0000-C000-000000000046}");
        IIDIOleInPlaceSite = IIDFromString("{00000119-0000-0000-C000-000000000046}");
        IIDIOleLink = IIDFromString("{0000011D-0000-0000-C000-000000000046}");
        IIDIOleObject = IIDFromString("{00000112-0000-0000-C000-000000000046}");
        IIDIPersist = IIDFromString("{0000010C-0000-0000-C000-000000000046}");
        IIDIPersistFile = IIDFromString("{0000010B-0000-0000-C000-000000000046}");
        IIDIPersistStorage = IIDFromString("{0000010A-0000-0000-C000-000000000046}");
        IIDIPersistStreamInit = IIDFromString("{7FD52380-4E07-101B-AE2D-08002B2EC713}");
        IIDIPropertyNotifySink = IIDFromString("{9BFBBC02-EFF1-101A-84ED-00AA00341D07}");
        IIDIProvideClassInfo = IIDFromString("{B196B283-BAB4-101A-B69C-00AA00341D07}");
        IIDIProvideClassInfo2 = IIDFromString("{A6BC3AC0-DBAA-11CE-9DE3-00AA004BB851}");
        IIDIServiceProvider = IIDFromString("{6d5140c1-7436-11ce-8034-00aa006009fa}");
        IIDISpecifyPropertyPages = IIDFromString("{B196B28B-BAB4-101A-B69C-00AA00341D07}");
        IIDIUnknown = IIDFromString("{00000000-0000-0000-C000-000000000046}");
        IIDIViewObject2 = IIDFromString("{00000127-0000-0000-C000-000000000046}");
        CGID_DocHostCommandHandler = IIDFromString("{f38bc242-b950-11d1-8918-00c04fc2c836}");
        CGID_Explorer = IIDFromString("{000214D0-0000-0000-C000-000000000046}");
        IID_ICoreWebView2Environment2 = IIDFromString("{41F3632B-5EF4-404F-AD82-2D606C5A9A21}");
        IID_ICoreWebView2_2 = IIDFromString("{9E8F0CF8-E670-4B5E-B2BC-73E061E3184C}");
        IIDIAccessible2 = IIDFromString("{E89F726E-C4F4-4c19-BB19-B647D7FA8478}");
        IIDIAccessibleRelation = IIDFromString("{7CDF86EE-C3DA-496a-BDA4-281B336E1FDC}");
        IIDIAccessibleAction = IIDFromString("{B70D9F59-3B5A-4dba-AB9E-22012F607DF5}");
        IIDIAccessibleComponent = IIDFromString("{1546D4B0-4C98-4bda-89AE-9A64748BDDE4}");
        IIDIAccessibleValue = IIDFromString("{35855B5B-C566-4fd0-A7B1-E65465600394}");
        IIDIAccessibleText = IIDFromString("{24FD2FFB-3AAD-4a08-8335-A3AD89C0FB4B}");
        IIDIAccessibleEditableText = IIDFromString("{A59AA09A-7011-4b65-939D-32B1FB5547E3}");
        IIDIAccessibleHyperlink = IIDFromString("{01C20F2B-3DD2-400f-949F-AD00BDAB1D41}");
        IIDIAccessibleHypertext = IIDFromString("{6B4F8BBF-F1F2-418a-B35E-A195BC4103B9}");
        IIDIAccessibleTable = IIDFromString("{35AD8070-C20C-4fb4-B094-F4F7275DD469}");
        IIDIAccessibleTable2 = IIDFromString("{6167f295-06f0-4cdd-a1fa-02e25153d869}");
        IIDIAccessibleTableCell = IIDFromString("{594116B1-C99F-4847-AD06-0A7A86ECE645}");
        IIDIAccessibleImage = IIDFromString("{FE5ABB3D-615E-4f7b-909F-5F0EDA9E8DDE}");
        IIDIAccessibleApplication = IIDFromString("{D49DED83-5B25-43F4-9B95-93B44595979E}");
        IIDIAccessibleContext = IIDFromString("{77A123E4-5794-44e0-B8BF-DE600C9D29BD}");
        COM.FreeUnusedLibraries = true;
    }
}
