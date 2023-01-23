//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

import java.util.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;

public class OS extends C
{
    public static final boolean IsDBLocale;
    public static final int WIN32_VERSION;
    public static final int WIN32_BUILD;
    public static final int WIN32_BUILD_WIN10_1809 = 17763;
    public static final int WIN32_BUILD_WIN10_2004 = 19041;
    public static final int WIN32_BUILD_WIN11_21H2 = 22000;
    public static final String NO_MANIFEST = "org.eclipse.swt.internal.win32.OS.NO_MANIFEST";
    public static final int ACTCTX_FLAG_RESOURCE_NAME_VALID = 8;
    public static final int ACTCTX_FLAG_SET_PROCESS_DEFAULT = 16;
    public static final int ACTCTX_FLAG_HMODULE_VALID = 128;
    public static final int MANIFEST_RESOURCE_ID = 2;
    public static final int SM_IMMENABLED = 82;
    public static final int ABS_DOWNDISABLED = 8;
    public static final int ABS_DOWNHOT = 6;
    public static final int ABS_DOWNNORMAL = 5;
    public static final int ABS_DOWNPRESSED = 7;
    public static final int ABS_LEFTDISABLED = 12;
    public static final int ABS_LEFTHOT = 10;
    public static final int ABS_LEFTNORMAL = 9;
    public static final int ABS_LEFTPRESSED = 11;
    public static final int ABS_RIGHTDISABLED = 16;
    public static final int ABS_RIGHTHOT = 14;
    public static final int ABS_RIGHTNORMAL = 13;
    public static final int ABS_RIGHTPRESSED = 15;
    public static final int ABS_UPDISABLED = 4;
    public static final int ABS_UPHOT = 2;
    public static final int ABS_UPNORMAL = 1;
    public static final int ABS_UPPRESSED = 3;
    public static final int AC_SRC_OVER = 0;
    public static final int AC_SRC_ALPHA = 1;
    public static final int ALTERNATE = 1;
    public static final int ASSOCF_NOTRUNCATE = 32;
    public static final int ASSOCF_INIT_IGNOREUNKNOWN = 1024;
    public static final int ASSOCSTR_COMMAND = 1;
    public static final int ASSOCSTR_DEFAULTICON = 15;
    public static final int ASSOCSTR_FRIENDLYAPPNAME = 4;
    public static final int ASSOCSTR_FRIENDLYDOCNAME = 3;
    public static final int ATTR_INPUT = 0;
    public static final int ATTR_TARGET_CONVERTED = 1;
    public static final int ATTR_CONVERTED = 2;
    public static final int ATTR_TARGET_NOTCONVERTED = 3;
    public static final int ATTR_INPUT_ERROR = 4;
    public static final int ATTR_FIXEDCONVERTED = 5;
    public static final int BCM_FIRST = 5632;
    public static final int BCM_GETIDEALSIZE = 5633;
    public static final int BCM_GETIMAGELIST = 5635;
    public static final int BCM_GETNOTE = 5642;
    public static final int BCM_GETNOTELENGTH = 5643;
    public static final int BCM_SETIMAGELIST = 5634;
    public static final int BCM_SETNOTE = 5641;
    public static final int BDR_SUNKENINNER = 8;
    public static final int BF_LEFT = 1;
    public static final int BF_TOP = 2;
    public static final int BF_RIGHT = 4;
    public static final int BF_BOTTOM = 8;
    public static final int BITSPIXEL = 12;
    public static final int BI_BITFIELDS = 3;
    public static final int BI_RGB = 0;
    public static final int BLACKNESS = 66;
    public static final int BLACK_BRUSH = 4;
    public static final int BUTTON_IMAGELIST_ALIGN_LEFT = 0;
    public static final int BUTTON_IMAGELIST_ALIGN_RIGHT = 1;
    public static final int BUTTON_IMAGELIST_ALIGN_CENTER = 4;
    public static final int BM_CLICK = 245;
    public static final int BM_GETCHECK = 240;
    public static final int BM_SETCHECK = 241;
    public static final int BM_SETIMAGE = 247;
    public static final int BM_SETSTYLE = 244;
    public static final int BN_CLICKED = 0;
    public static final int BN_DOUBLECLICKED = 5;
    public static final int BPBF_COMPATIBLEBITMAP = 0;
    public static final int BP_PUSHBUTTON = 1;
    public static final int BP_RADIOBUTTON = 2;
    public static final int BP_CHECKBOX = 3;
    public static final int BP_GROUPBOX = 4;
    public static final int BST_CHECKED = 1;
    public static final int BST_INDETERMINATE = 2;
    public static final int BST_UNCHECKED = 0;
    public static final int BS_3STATE = 5;
    public static final int BS_BITMAP = 128;
    public static final int BS_CENTER = 768;
    public static final int BS_CHECKBOX = 2;
    public static final int BS_COMMANDLINK = 14;
    public static final int BS_DEFPUSHBUTTON = 1;
    public static final int BS_FLAT = 32768;
    public static final int BS_GROUPBOX = 7;
    public static final int BS_ICON = 64;
    public static final int BS_LEFT = 256;
    public static final int BS_MULTILINE = 8192;
    public static final int BS_NOTIFY = 16384;
    public static final int BS_OWNERDRAW = 11;
    public static final int BS_PATTERN = 3;
    public static final int BS_PUSHBUTTON = 0;
    public static final int BS_PUSHLIKE = 4096;
    public static final int BS_RADIOBUTTON = 4;
    public static final int BS_RIGHT = 512;
    public static final int BS_SOLID = 0;
    public static final int BTNS_AUTOSIZE = 16;
    public static final int BTNS_BUTTON = 0;
    public static final int BTNS_CHECK = 2;
    public static final int BTNS_CHECKGROUP = 6;
    public static final int BTNS_DROPDOWN = 8;
    public static final int BTNS_GROUP = 4;
    public static final int BTNS_SEP = 1;
    public static final int BTNS_SHOWTEXT = 64;
    public static final int CBN_DROPDOWN = 7;
    public static final int CBN_EDITCHANGE = 5;
    public static final int CBN_KILLFOCUS = 4;
    public static final int CBN_SELCHANGE = 1;
    public static final int CBN_SETFOCUS = 3;
    public static final int CBS_AUTOHSCROLL = 64;
    public static final int CBS_DROPDOWN = 2;
    public static final int CBS_DROPDOWNLIST = 3;
    public static final int CBS_CHECKEDNORMAL = 5;
    public static final int CBS_MIXEDNORMAL = 9;
    public static final int CBS_NOINTEGRALHEIGHT = 1024;
    public static final int CBS_SIMPLE = 1;
    public static final int CBS_UNCHECKEDNORMAL = 1;
    public static final int CBS_CHECKEDDISABLED = 8;
    public static final int CBS_CHECKEDHOT = 6;
    public static final int CBS_CHECKEDPRESSED = 7;
    public static final int CBS_MIXEDDISABLED = 12;
    public static final int CBS_MIXEDHOT = 10;
    public static final int CBS_MIXEDPRESSED = 11;
    public static final int CBS_UNCHECKEDDISABLED = 4;
    public static final int CBS_UNCHECKEDHOT = 2;
    public static final int CBS_UNCHECKEDPRESSED = 3;
    public static final int CB_ADDSTRING = 323;
    public static final int CB_DELETESTRING = 324;
    public static final int CB_ERR = -1;
    public static final int CB_ERRSPACE = -2;
    public static final int CB_FINDSTRINGEXACT = 344;
    public static final int CB_GETCOUNT = 326;
    public static final int CB_GETCURSEL = 327;
    public static final int CB_GETDROPPEDCONTROLRECT = 338;
    public static final int CB_GETDROPPEDSTATE = 343;
    public static final int CB_GETDROPPEDWIDTH = 351;
    public static final int CB_GETEDITSEL = 320;
    public static final int CB_GETHORIZONTALEXTENT = 349;
    public static final int CB_GETITEMHEIGHT = 340;
    public static final int CB_GETLBTEXT = 328;
    public static final int CB_GETLBTEXTLEN = 329;
    public static final int CB_INSERTSTRING = 330;
    public static final int CB_LIMITTEXT = 321;
    public static final int CB_RESETCONTENT = 331;
    public static final int CB_SELECTSTRING = 333;
    public static final int CB_SETCURSEL = 334;
    public static final int CB_SETDROPPEDWIDTH = 352;
    public static final int CB_SETEDITSEL = 322;
    public static final int CB_SETHORIZONTALEXTENT = 350;
    public static final int CB_SETITEMHEIGHT = 339;
    public static final int CB_SHOWDROPDOWN = 335;
    public static final int CCHDEVICENAME = 32;
    public static final int CCHFORMNAME = 32;
    public static final int CCHILDREN_SCROLLBAR = 5;
    public static final int CCS_NODIVIDER = 64;
    public static final int CCS_NORESIZE = 4;
    public static final int CCS_VERT = 128;
    public static final int CC_ANYCOLOR = 256;
    public static final int CC_ENABLEHOOK = 16;
    public static final int CC_FULLOPEN = 2;
    public static final int CC_RGBINIT = 1;
    public static final int CDDS_POSTERASE = 4;
    public static final int CDDS_POSTPAINT = 2;
    public static final int CDDS_PREERASE = 3;
    public static final int CDDS_PREPAINT = 1;
    public static final int CDDS_ITEM = 65536;
    public static final int CDDS_ITEMPOSTPAINT = 65538;
    public static final int CDDS_ITEMPREPAINT = 65537;
    public static final int CDDS_SUBITEM = 131072;
    public static final int CDDS_SUBITEMPOSTPAINT = 196610;
    public static final int CDDS_SUBITEMPREPAINT = 196609;
    public static final int CDIS_SELECTED = 1;
    public static final int CDIS_GRAYED = 2;
    public static final int CDIS_DISABLED = 4;
    public static final int CDIS_CHECKED = 8;
    public static final int CDIS_FOCUS = 16;
    public static final int CDIS_DEFAULT = 32;
    public static final int CDIS_HOT = 64;
    public static final int CDIS_MARKED = 128;
    public static final int CDIS_INDETERMINATE = 256;
    public static final int CDIS_SHOWKEYBOARDCUES = 512;
    public static final int CDIS_DROPHILITED = 4096;
    public static final int CDM_FIRST = 1124;
    public static final int CDM_GETSPEC = 1124;
    public static final int CDN_FIRST = -601;
    public static final int CDN_SELCHANGE = -602;
    public static final int CDRF_DODEFAULT = 0;
    public static final int CDRF_DOERASE = 8;
    public static final int CDRF_NEWFONT = 2;
    public static final int CDRF_NOTIFYITEMDRAW = 32;
    public static final int CDRF_NOTIFYPOSTERASE = 64;
    public static final int CDRF_NOTIFYPOSTPAINT = 16;
    public static final int CDRF_NOTIFYSUBITEMDRAW = 32;
    public static final int CDRF_SKIPDEFAULT = 4;
    public static final int CDRF_SKIPPOSTPAINT = 256;
    public static final int CFS_RECT = 1;
    public static final int CFS_EXCLUDE = 128;
    public static final int CF_EFFECTS = 256;
    public static final int CF_INITTOLOGFONTSTRUCT = 64;
    public static final int CF_SCREENFONTS = 1;
    public static final int CF_TEXT = 1;
    public static final int CF_UNICODETEXT = 13;
    public static final int CF_USESTYLE = 128;
    public static final int CLR_DEFAULT = -16777216;
    public static final int CLR_INVALID = -1;
    public static final int CLR_NONE = -1;
    public static final int COLORONCOLOR = 3;
    public static final int COLOR_3DDKSHADOW = 21;
    public static final int COLOR_3DFACE = 15;
    public static final int COLOR_3DHIGHLIGHT = 20;
    public static final int COLOR_3DHILIGHT = 20;
    public static final int COLOR_3DLIGHT = 22;
    public static final int COLOR_3DSHADOW = 16;
    public static final int COLOR_ACTIVECAPTION = 2;
    public static final int COLOR_BTNFACE = 15;
    public static final int COLOR_BTNHIGHLIGHT = 20;
    public static final int COLOR_BTNSHADOW = 16;
    public static final int COLOR_BTNTEXT = 18;
    public static final int COLOR_CAPTIONTEXT = 9;
    public static final int COLOR_GRADIENTACTIVECAPTION = 27;
    public static final int COLOR_GRADIENTINACTIVECAPTION = 28;
    public static final int COLOR_GRAYTEXT = 17;
    public static final int COLOR_HIGHLIGHT = 13;
    public static final int COLOR_HIGHLIGHTTEXT = 14;
    public static final int COLOR_HOTLIGHT = 26;
    public static final int COLOR_INACTIVECAPTION = 3;
    public static final int COLOR_INACTIVECAPTIONTEXT = 19;
    public static final int COLOR_INFOBK = 24;
    public static final int COLOR_INFOTEXT = 23;
    public static final int COLOR_MENU = 4;
    public static final int COLOR_MENUTEXT = 7;
    public static final int COLOR_SCROLLBAR = 0;
    public static final int COLOR_WINDOW = 5;
    public static final int COLOR_WINDOWFRAME = 6;
    public static final int COLOR_WINDOWTEXT = 8;
    public static final int COMPLEXREGION = 3;
    public static final int CP_ACP = 0;
    public static final int CP_UTF8 = 65001;
    public static final int CP_DROPDOWNBUTTON = 1;
    public static final int CPS_COMPLETE = 1;
    public static final int CS_DBLCLKS = 8;
    public static final int CS_DROPSHADOW = 131072;
    public static final int CS_GLOBALCLASS = 16384;
    public static final int CS_HREDRAW = 2;
    public static final int CS_VREDRAW = 1;
    public static final int CS_OWNDC = 32;
    public static final int CW_USEDEFAULT = Integer.MIN_VALUE;
    public static final int CWP_SKIPINVISIBLE = 1;
    public static final String DATETIMEPICK_CLASS = "SysDateTimePick32";
    public static final int DC_BRUSH = 18;
    public static final int DCX_CACHE = 2;
    public static final int DEFAULT_CHARSET = 1;
    public static final int DEFAULT_GUI_FONT = 17;
    public static final int DFCS_BUTTONCHECK = 0;
    public static final int DFCS_CHECKED = 1024;
    public static final int DFCS_FLAT = 16384;
    public static final int DFCS_INACTIVE = 256;
    public static final int DFCS_PUSHED = 512;
    public static final int DFCS_SCROLLDOWN = 1;
    public static final int DFCS_SCROLLLEFT = 2;
    public static final int DFCS_SCROLLRIGHT = 3;
    public static final int DFCS_SCROLLUP = 0;
    public static final int DFC_BUTTON = 4;
    public static final int DFC_SCROLL = 3;
    public static final int DIB_RGB_COLORS = 0;
    public static final int DI_NORMAL = 3;
    public static final int DI_NOMIRROR = 16;
    public static final int DLGC_BUTTON = 8192;
    public static final int DLGC_HASSETSEL = 8;
    public static final int DLGC_STATIC = 256;
    public static final int DLGC_WANTALLKEYS = 4;
    public static final int DLGC_WANTARROWS = 1;
    public static final int DLGC_WANTCHARS = 128;
    public static final int DLGC_WANTTAB = 2;
    public static final short DMCOLLATE_FALSE = 0;
    public static final short DMCOLLATE_TRUE = 1;
    public static final int DM_SETDEFID = 1025;
    public static final int DM_COLLATE = 32768;
    public static final int DM_COPIES = 256;
    public static final int DM_DUPLEX = 4096;
    public static final int DM_ORIENTATION = 1;
    public static final int DM_OUT_BUFFER = 2;
    public static final short DMORIENT_PORTRAIT = 1;
    public static final short DMORIENT_LANDSCAPE = 2;
    public static final short DMDUP_SIMPLEX = 1;
    public static final short DMDUP_VERTICAL = 2;
    public static final short DMDUP_HORIZONTAL = 3;
    public static final int DSTINVERT = 5570569;
    public static final int DT_BOTTOM = 8;
    public static final int DT_CALCRECT = 1024;
    public static final int DT_CENTER = 1;
    public static final int DT_EDITCONTROL = 8192;
    public static final int DT_EXPANDTABS = 64;
    public static final int DT_ENDELLIPSIS = 32768;
    public static final int DT_HIDEPREFIX = 1048576;
    public static final int DT_LEFT = 0;
    public static final int DT_NOPREFIX = 2048;
    public static final int DT_RASPRINTER = 2;
    public static final int DT_RIGHT = 2;
    public static final int DT_RTLREADING = 131072;
    public static final int DT_SINGLELINE = 32;
    public static final int DT_TOP = 0;
    public static final int DT_VCENTER = 4;
    public static final int DT_WORDBREAK = 16;
    public static final int DTM_FIRST = 4096;
    public static final int DTM_GETSYSTEMTIME = 4097;
    public static final int DTM_SETMCSTYLE = 4107;
    public static final int DTM_GETIDEALSIZE = 4111;
    public static final int DTM_SETFORMAT = 4146;
    public static final int DTM_SETSYSTEMTIME = 4098;
    public static final int DTN_FIRST = -760;
    public static final int DTN_DATETIMECHANGE = -759;
    public static final int DTN_CLOSEUP = -753;
    public static final int DTN_DROPDOWN = -754;
    public static final int DTS_LONGDATEFORMAT = 4;
    public static final int DTS_SHORTDATECENTURYFORMAT = 12;
    public static final int DTS_SHORTDATEFORMAT = 0;
    public static final int DTS_TIMEFORMAT = 9;
    public static final int DTS_UPDOWN = 1;
    public static final int E_POINTER = -2147467261;
    public static final int EBP_NORMALGROUPBACKGROUND = 5;
    public static final int EBP_NORMALGROUPCOLLAPSE = 6;
    public static final int EBP_NORMALGROUPEXPAND = 7;
    public static final int EBP_NORMALGROUPHEAD = 8;
    public static final int EBNGC_NORMAL = 1;
    public static final int EBNGC_HOT = 2;
    public static final int EBNGC_PRESSED = 3;
    public static final int EBP_HEADERBACKGROUND = 1;
    public static final int EC_LEFTMARGIN = 1;
    public static final int EC_RIGHTMARGIN = 2;
    public static final int EDGE_SUNKEN = 10;
    public static final int EDGE_ETCHED = 6;
    public static final int EM_CANUNDO = 198;
    public static final int EM_CHARFROMPOS = 215;
    public static final int EM_DISPLAYBAND = 1075;
    public static final int EM_GETFIRSTVISIBLELINE = 206;
    public static final int EM_GETLIMITTEXT = 213;
    public static final int EM_GETLINE = 196;
    public static final int EM_GETLINECOUNT = 186;
    public static final int EM_GETMARGINS = 212;
    public static final int EM_GETPASSWORDCHAR = 210;
    public static final int EM_GETSCROLLPOS = 1245;
    public static final int EM_GETSEL = 176;
    public static final int EM_LIMITTEXT = 197;
    public static final int EM_LINEFROMCHAR = 201;
    public static final int EM_LINEINDEX = 187;
    public static final int EM_LINELENGTH = 193;
    public static final int EM_LINESCROLL = 182;
    public static final int EM_POSFROMCHAR = 214;
    public static final int EM_REPLACESEL = 194;
    public static final int EM_SCROLLCARET = 183;
    public static final int EM_SETBKGNDCOLOR = 1091;
    public static final int EM_SETLIMITTEXT = 197;
    public static final int EM_SETMARGINS = 211;
    public static final int EM_SETOPTIONS = 1101;
    public static final int EM_SETPARAFORMAT = 1095;
    public static final int EM_SETPASSWORDCHAR = 204;
    public static final int EM_SETCUEBANNER = 5377;
    public static final int EM_SETREADONLY = 207;
    public static final int EM_SETRECT = 179;
    public static final int EM_SETSEL = 177;
    public static final int EM_SETTABSTOPS = 203;
    public static final int EM_UNDO = 199;
    public static final int EMR_EXTCREATEFONTINDIRECTW = 82;
    public static final int EMR_EXTTEXTOUTW = 84;
    public static final int EN_ALIGN_LTR_EC = 1792;
    public static final int EN_ALIGN_RTL_EC = 1793;
    public static final int EN_CHANGE = 768;
    public static final int EP_EDITTEXT = 1;
    public static final int ERROR_FILE_NOT_FOUND = 2;
    public static final int ERROR_NO_MORE_ITEMS = 259;
    public static final int ESB_DISABLE_BOTH = 3;
    public static final int ESB_ENABLE_BOTH = 0;
    public static final int ES_AUTOHSCROLL = 128;
    public static final int ES_AUTOVSCROLL = 64;
    public static final int ES_CENTER = 1;
    public static final int ES_MULTILINE = 4;
    public static final int ES_NOHIDESEL = 256;
    public static final int ES_PASSWORD = 32;
    public static final int ES_READONLY = 2048;
    public static final int ES_RIGHT = 2;
    public static final int ETO_CLIPPED = 4;
    public static final int ETS_NORMAL = 1;
    public static final int ETS_HOT = 2;
    public static final int ETS_SELECTED = 3;
    public static final int ETS_DISABLED = 4;
    public static final int ETS_FOCUSED = 5;
    public static final int ETS_READONLY = 6;
    public static final int EVENT_OBJECT_FOCUS = 32773;
    public static final short FADF_FIXEDSIZE = 16;
    public static final int FALT = 16;
    public static final int FCONTROL = 8;
    public static final int FE_FONTSMOOTHINGCLEARTYPE = 2;
    public static final int FEATURE_DISABLE_NAVIGATION_SOUNDS = 21;
    public static final int FILE_ATTRIBUTE_NORMAL = 128;
    public static final int FILE_MAP_READ = 4;
    public static final int FLICKDIRECTION_RIGHT = 0;
    public static final int FLICKDIRECTION_UPRIGHT = 1;
    public static final int FLICKDIRECTION_UP = 2;
    public static final int FLICKDIRECTION_UPLEFT = 3;
    public static final int FLICKDIRECTION_LEFT = 4;
    public static final int FLICKDIRECTION_DOWNLEFT = 5;
    public static final int FLICKDIRECTION_DOWN = 6;
    public static final int FLICKDIRECTION_DOWNRIGHT = 7;
    public static final int FLICKDIRECTION_INVALID = 8;
    public static final int FOS_OVERWRITEPROMPT = 2;
    public static final int FOS_NOCHANGEDIR = 8;
    public static final int FOS_PICKFOLDERS = 32;
    public static final int FOS_FORCEFILESYSTEM = 64;
    public static final int FOS_ALLOWMULTISELECT = 512;
    public static final int FOS_FILEMUSTEXIST = 4096;
    public static final int FR_PRIVATE = 16;
    public static final int FSHIFT = 4;
    public static final int FVIRTKEY = 1;
    public static final int GCP_REORDER = 2;
    public static final int GCP_GLYPHSHAPE = 16;
    public static final int GCP_CLASSIN = 524288;
    public static final int GCP_LIGATE = 32;
    public static final int GCS_COMPSTR = 8;
    public static final int GCS_RESULTSTR = 2048;
    public static final int GCS_COMPATTR = 16;
    public static final int GCS_COMPCLAUSE = 32;
    public static final int GCS_CURSORPOS = 128;
    public static final int GET_FEATURE_FROM_PROCESS = 2;
    public static final int GF_BEGIN = 1;
    public static final int GF_INERTIA = 2;
    public static final int GF_END = 4;
    public static final int GGI_MARK_NONEXISTING_GLYPHS = 1;
    public static final int GID_BEGIN = 1;
    public static final int GID_END = 2;
    public static final int GID_ZOOM = 3;
    public static final int GID_PAN = 4;
    public static final int GID_ROTATE = 5;
    public static final int GID_TWOFINGERTAP = 6;
    public static final int GID_PRESSANDTAP = 7;
    public static final int GM_ADVANCED = 2;
    public static final int GMDI_USEDISABLED = 1;
    public static final int GMEM_FIXED = 0;
    public static final int GMEM_MOVEABLE = 2;
    public static final int GMEM_ZEROINIT = 64;
    public static final int GRADIENT_FILL_RECT_H = 0;
    public static final int GRADIENT_FILL_RECT_V = 1;
    public static final int GUI_INMENUMODE = 4;
    public static final int GUI_INMOVESIZE = 2;
    public static final int GUI_POPUPMENUMODE = 16;
    public static final int GUI_SYSTEMMENUMODE = 8;
    public static final int GWL_EXSTYLE = -20;
    public static final int GWL_ID = -12;
    public static final int GWL_HWNDPARENT = -8;
    public static final int GWL_STYLE = -16;
    public static final int GWL_USERDATA = -21;
    public static final int GWL_WNDPROC = -4;
    public static final int GWLP_ID = -12;
    public static final int GWLP_HWNDPARENT = -8;
    public static final int GWLP_USERDATA = -21;
    public static final int GWLP_WNDPROC = -4;
    public static final int GW_CHILD = 5;
    public static final int GW_HWNDFIRST = 0;
    public static final int GW_HWNDLAST = 1;
    public static final int GW_HWNDNEXT = 2;
    public static final int GW_HWNDPREV = 3;
    public static final int GW_OWNER = 4;
    public static final long HBMMENU_CALLBACK = -1L;
    public static final int HCBT_CREATEWND = 3;
    public static final int HCF_HIGHCONTRASTON = 1;
    public static final int HDF_BITMAP = 8192;
    public static final int HDF_BITMAP_ON_RIGHT = 4096;
    public static final int HDF_CENTER = 2;
    public static final int HDF_JUSTIFYMASK = 3;
    public static final int HDF_IMAGE = 2048;
    public static final int HDF_LEFT = 0;
    public static final int HDF_OWNERDRAW = 32768;
    public static final int HDF_RIGHT = 1;
    public static final int HDF_SORTUP = 1024;
    public static final int HDF_SORTDOWN = 512;
    public static final int HDI_BITMAP = 16;
    public static final int HDI_IMAGE = 32;
    public static final int HDI_ORDER = 128;
    public static final int HDI_TEXT = 2;
    public static final int HDI_WIDTH = 1;
    public static final int HDI_FORMAT = 4;
    public static final int HDM_FIRST = 4608;
    public static final int HDM_DELETEITEM = 4610;
    public static final int HDM_GETBITMAPMARGIN = 4629;
    public static final int HDM_GETITEMCOUNT = 4608;
    public static final int HDM_GETITEM = 4619;
    public static final int HDM_GETITEMRECT = 4615;
    public static final int HDM_GETORDERARRAY = 4625;
    public static final int HDM_HITTEST = 4614;
    public static final int HDM_INSERTITEM = 4618;
    public static final int HDM_LAYOUT = 4613;
    public static final int HDM_ORDERTOINDEX = 4623;
    public static final int HDM_SETIMAGELIST = 4616;
    public static final int HDM_SETITEM = 4620;
    public static final int HDM_SETORDERARRAY = 4626;
    public static final int HDN_FIRST = -300;
    public static final int HDN_BEGINDRAG = -310;
    public static final int HDN_BEGINTRACK = -326;
    public static final int HDN_DIVIDERDBLCLICK = -325;
    public static final int HDN_ENDDRAG = -311;
    public static final int HDN_ITEMCHANGED = -321;
    public static final int HDN_ITEMCHANGING = -320;
    public static final int HDN_ITEMCLICK = -322;
    public static final int HDN_ITEMDBLCLICK = -323;
    public static final int HDS_BUTTONS = 2;
    public static final int HDS_DRAGDROP = 64;
    public static final int HDS_FULLDRAG = 128;
    public static final int HDS_HIDDEN = 8;
    public static final int HEAP_ZERO_MEMORY = 8;
    public static final int HELPINFO_MENUITEM = 2;
    public static final int HHT_ONDIVIDER = 4;
    public static final int HHT_ONDIVOPEN = 8;
    public static final int HICF_ARROWKEYS = 2;
    public static final int HICF_LEAVING = 32;
    public static final int HICF_MOUSE = 1;
    public static final int HKEY_CLASSES_ROOT = Integer.MIN_VALUE;
    public static final int HKEY_CURRENT_USER = -2147483647;
    public static final int HKEY_LOCAL_MACHINE = -2147483646;
    public static final int HORZRES = 8;
    public static final int HTBORDER = 18;
    public static final int HTCAPTION = 2;
    public static final int HTCLIENT = 1;
    public static final int HTERROR = -2;
    public static final int HTHSCROLL = 6;
    public static final int HTMENU = 5;
    public static final int HTNOWHERE = 0;
    public static final int HTSYSMENU = 3;
    public static final int HTTRANSPARENT = -1;
    public static final int HTVSCROLL = 7;
    public static final int HWND_BOTTOM = 1;
    public static final int HWND_TOP = 0;
    public static final int HWND_TOPMOST = -1;
    public static final int HWND_NOTOPMOST = -2;
    public static final int ICON_BIG = 1;
    public static final int ICON_SMALL = 0;
    public static final int I_IMAGECALLBACK = -1;
    public static final int I_IMAGENONE = -2;
    public static final int IDABORT = 3;
    public static final int IDC_APPSTARTING = 32650;
    public static final int IDC_ARROW = 32512;
    public static final int IDC_CROSS = 32515;
    public static final int IDC_HAND = 32649;
    public static final int IDC_HELP = 32651;
    public static final int IDC_IBEAM = 32513;
    public static final int IDC_NO = 32648;
    public static final int IDC_SIZE = 32640;
    public static final int IDC_SIZEALL = 32646;
    public static final int IDC_SIZENESW = 32643;
    public static final int IDC_SIZENS = 32645;
    public static final int IDC_SIZENWSE = 32642;
    public static final int IDC_SIZEWE = 32644;
    public static final int IDC_UPARROW = 32516;
    public static final int IDC_WAIT = 32514;
    public static final int IDI_APPLICATION = 32512;
    public static final int IDNO = 7;
    public static final int IDOK = 1;
    public static final int IDRETRY = 4;
    public static final int IDYES = 6;
    public static final int ILC_COLOR = 0;
    public static final int ILC_COLOR16 = 16;
    public static final int ILC_COLOR24 = 24;
    public static final int ILC_COLOR32 = 32;
    public static final int ILC_COLOR4 = 4;
    public static final int ILC_COLOR8 = 8;
    public static final int ILC_MASK = 1;
    public static final int ILC_MIRROR = 8192;
    public static final int IMAGE_ICON = 1;
    public static final int IME_CMODE_FULLSHAPE = 8;
    public static final int IME_CMODE_KATAKANA = 2;
    public static final int IME_CMODE_NATIVE = 1;
    public static final int IME_CMODE_ROMAN = 16;
    public static final int IME_ESC_HANJA_MODE = 4104;
    public static final int IMEMOUSE_LDOWN = 1;
    public static final int INPUT_KEYBOARD = 1;
    public static final int INPUT_MOUSE = 0;
    public static final int INTERNET_MAX_URL_LENGTH = 2084;
    public static final int INTERNET_OPTION_END_BROWSER_SESSION = 42;
    public static final int KEY_QUERY_VALUE = 1;
    public static final int KEY_READ = 131097;
    public static final int KEY_WRITE = 131078;
    public static final int KEYEVENTF_EXTENDEDKEY = 1;
    public static final int KEYEVENTF_KEYUP = 2;
    public static final int L_MAX_URL_LENGTH = 2084;
    public static final int LANG_JAPANESE = 17;
    public static final int LANG_KOREAN = 18;
    public static final int LANG_NEUTRAL = 0;
    public static final int LAYOUT_RTL = 1;
    public static final int LBN_DBLCLK = 2;
    public static final int LBN_SELCHANGE = 1;
    public static final int LBS_EXTENDEDSEL = 2048;
    public static final int LBS_MULTIPLESEL = 8;
    public static final int LBS_NOINTEGRALHEIGHT = 256;
    public static final int LBS_NOTIFY = 1;
    public static final int LB_ADDSTRING = 384;
    public static final int LB_DELETESTRING = 386;
    public static final int LB_ERR = -1;
    public static final int LB_ERRSPACE = -2;
    public static final int LB_FINDSTRINGEXACT = 418;
    public static final int LB_GETCARETINDEX = 415;
    public static final int LB_GETCOUNT = 395;
    public static final int LB_GETCURSEL = 392;
    public static final int LB_GETHORIZONTALEXTENT = 403;
    public static final int LB_GETITEMHEIGHT = 417;
    public static final int LB_GETITEMRECT = 408;
    public static final int LB_GETSEL = 391;
    public static final int LB_GETSELCOUNT = 400;
    public static final int LB_GETSELITEMS = 401;
    public static final int LB_GETTEXT = 393;
    public static final int LB_GETTEXTLEN = 394;
    public static final int LB_GETTOPINDEX = 398;
    public static final int LB_INITSTORAGE = 424;
    public static final int LB_INSERTSTRING = 385;
    public static final int LB_RESETCONTENT = 388;
    public static final int LB_SELITEMRANGE = 411;
    public static final int LB_SELITEMRANGEEX = 387;
    public static final int LB_SETANCHORINDEX = 61852;
    public static final int LB_SETCARETINDEX = 414;
    public static final int LB_SETCURSEL = 390;
    public static final int LB_SETHORIZONTALEXTENT = 404;
    public static final int LB_SETSEL = 389;
    public static final int LB_SETTOPINDEX = 407;
    public static final int LF_FACESIZE = 32;
    public static final int LGRPID_ARABIC = 13;
    public static final int LGRPID_HEBREW = 12;
    public static final int LGRPID_INSTALLED = 1;
    public static final int LIF_ITEMINDEX = 1;
    public static final int LIF_STATE = 2;
    public static final int LIM_SMALL = 0;
    public static final int LIS_FOCUSED = 1;
    public static final int LIS_ENABLED = 2;
    public static final int LISS_HOT = 2;
    public static final int LISS_SELECTED = 3;
    public static final int LISS_SELECTEDNOTFOCUS = 5;
    public static final int LM_GETIDEALSIZE = 1793;
    public static final int LM_SETITEM = 1794;
    public static final int LM_GETITEM = 1795;
    public static final int LCID_SUPPORTED = 2;
    public static final int LOCALE_IDEFAULTANSICODEPAGE = 4100;
    public static final int LOCALE_SDECIMAL = 14;
    public static final int LOCALE_SISO3166CTRYNAME = 90;
    public static final int LOCALE_SISO639LANGNAME = 89;
    public static final int LOCALE_STIMEFORMAT = 4099;
    public static final int LOCALE_SYEARMONTH = 4102;
    public static final int LOCALE_USER_DEFAULT = 1024;
    public static final int LOGPIXELSX = 88;
    public static final int LOGPIXELSY = 90;
    public static final int LPSTR_TEXTCALLBACK = -1;
    public static final int LR_DEFAULTCOLOR = 0;
    public static final int LR_SHARED = 32768;
    public static final int LVCFMT_BITMAP_ON_RIGHT = 4096;
    public static final int LVCFMT_CENTER = 2;
    public static final int LVCFMT_IMAGE = 2048;
    public static final int LVCFMT_LEFT = 0;
    public static final int LVCFMT_RIGHT = 1;
    public static final int LVCF_FMT = 1;
    public static final int LVCF_IMAGE = 16;
    public static final int LVCFMT_JUSTIFYMASK = 3;
    public static final int LVCF_TEXT = 4;
    public static final int LVCF_WIDTH = 2;
    public static final int LVHT_ONITEM = 14;
    public static final int LVHT_ONITEMICON = 2;
    public static final int LVHT_ONITEMLABEL = 4;
    public static final int LVHT_ONITEMSTATEICON = 8;
    public static final int LVIF_IMAGE = 2;
    public static final int LVIF_INDENT = 16;
    public static final int LVIF_STATE = 8;
    public static final int LVIF_TEXT = 1;
    public static final int LVIM_AFTER = 1;
    public static final int LVIR_BOUNDS = 0;
    public static final int LVIR_ICON = 1;
    public static final int LVIR_LABEL = 2;
    public static final int LVIR_SELECTBOUNDS = 3;
    public static final int LVIS_DROPHILITED = 8;
    public static final int LVIS_FOCUSED = 1;
    public static final int LVIS_SELECTED = 2;
    public static final int LVIS_STATEIMAGEMASK = 61440;
    public static final int LVM_FIRST = 4096;
    public static final int LVM_APPROXIMATEVIEWRECT = 4160;
    public static final int LVM_CREATEDRAGIMAGE = 4129;
    public static final int LVM_DELETEALLITEMS = 4105;
    public static final int LVM_DELETECOLUMN = 4124;
    public static final int LVM_DELETEITEM = 4104;
    public static final int LVM_ENSUREVISIBLE = 4115;
    public static final int LVM_GETBKCOLOR = 4096;
    public static final int LVM_GETCOLUMN = 4191;
    public static final int LVM_GETCOLUMNORDERARRAY = 4155;
    public static final int LVM_GETCOLUMNWIDTH = 4125;
    public static final int LVM_GETCOUNTPERPAGE = 4136;
    public static final int LVM_GETEXTENDEDLISTVIEWSTYLE = 4151;
    public static final int LVM_GETHEADER = 4127;
    public static final int LVM_GETIMAGELIST = 4098;
    public static final int LVM_GETITEM = 4171;
    public static final int LVM_GETITEMCOUNT = 4100;
    public static final int LVM_GETITEMRECT = 4110;
    public static final int LVM_GETITEMSTATE = 4140;
    public static final int LVM_GETNEXTITEM = 4108;
    public static final int LVM_GETSELECTEDCOLUMN = 4270;
    public static final int LVM_GETSELECTEDCOUNT = 4146;
    public static final int LVM_GETSTRINGWIDTH = 4183;
    public static final int LVM_GETSUBITEMRECT = 4152;
    public static final int LVM_GETTEXTCOLOR = 4131;
    public static final int LVM_GETTOOLTIPS = 4174;
    public static final int LVM_GETTOPINDEX = 4135;
    public static final int LVM_HITTEST = 4114;
    public static final int LVM_INSERTCOLUMN = 4193;
    public static final int LVM_INSERTITEM = 4173;
    public static final int LVM_REDRAWITEMS = 4117;
    public static final int LVM_SCROLL = 4116;
    public static final int LVM_SETBKCOLOR = 4097;
    public static final int LVM_SETCALLBACKMASK = 4107;
    public static final int LVM_SETCOLUMN = 4192;
    public static final int LVM_SETCOLUMNORDERARRAY = 4154;
    public static final int LVM_SETCOLUMNWIDTH = 4126;
    public static final int LVM_SETEXTENDEDLISTVIEWSTYLE = 4150;
    public static final int LVM_SETIMAGELIST = 4099;
    public static final int LVM_SETINSERTMARK = 4262;
    public static final int LVM_SETITEM = 4172;
    public static final int LVM_SETITEMCOUNT = 4143;
    public static final int LVM_SETITEMSTATE = 4139;
    public static final int LVM_SETSELECTIONMARK = 4163;
    public static final int LVM_SETSELECTEDCOLUMN = 4236;
    public static final int LVM_SETTEXTBKCOLOR = 4134;
    public static final int LVM_SETTEXTCOLOR = 4132;
    public static final int LVM_SETTOOLTIPS = 4170;
    public static final int LVM_SUBITEMHITTEST = 4153;
    public static final int LVNI_FOCUSED = 1;
    public static final int LVNI_SELECTED = 2;
    public static final int LVN_BEGINDRAG = -109;
    public static final int LVN_BEGINRDRAG = -111;
    public static final int LVN_COLUMNCLICK = -108;
    public static final int LVN_FIRST = -100;
    public static final int LVN_GETDISPINFO = -177;
    public static final int LVN_ITEMACTIVATE = -114;
    public static final int LVN_ITEMCHANGED = -101;
    public static final int LVN_MARQUEEBEGIN = -156;
    public static final int LVN_ODFINDITEM = -179;
    public static final int LVN_ODSTATECHANGED = -115;
    public static final int LVP_LISTITEM = 1;
    public static final int LVSCW_AUTOSIZE = -1;
    public static final int LVSCW_AUTOSIZE_USEHEADER = -2;
    public static final int LVSICF_NOINVALIDATEALL = 1;
    public static final int LVSICF_NOSCROLL = 2;
    public static final int LVSIL_SMALL = 1;
    public static final int LVSIL_STATE = 2;
    public static final int LVS_EX_DOUBLEBUFFER = 65536;
    public static final int LVS_EX_FULLROWSELECT = 32;
    public static final int LVS_EX_GRIDLINES = 1;
    public static final int LVS_EX_HEADERDRAGDROP = 16;
    public static final int LVS_EX_LABELTIP = 16384;
    public static final int LVS_EX_ONECLICKACTIVATE = 64;
    public static final int LVS_EX_SUBITEMIMAGES = 2;
    public static final int LVS_EX_TRACKSELECT = 8;
    public static final int LVS_EX_TRANSPARENTBKGND = 8388608;
    public static final int LVS_EX_TWOCLICKACTIVATE = 128;
    public static final int LVS_NOCOLUMNHEADER = 16384;
    public static final int LVS_NOSCROLL = 8192;
    public static final int LVS_OWNERDATA = 4096;
    public static final int LVS_OWNERDRAWFIXED = 1024;
    public static final int LVS_REPORT = 1;
    public static final int LVS_SHAREIMAGELISTS = 64;
    public static final int LVS_SHOWSELALWAYS = 8;
    public static final int LVS_SINGLESEL = 4;
    public static final int LWA_COLORKEY = 1;
    public static final int LWA_ALPHA = 2;
    public static final int MAX_LINKID_TEXT = 48;
    public static final int MAX_PATH = 260;
    public static final int MA_NOACTIVATE = 3;
    public static final int MB_ABORTRETRYIGNORE = 2;
    public static final int MB_APPLMODAL = 0;
    public static final int MB_ICONERROR = 16;
    public static final int MB_ICONINFORMATION = 64;
    public static final int MB_ICONQUESTION = 32;
    public static final int MB_ICONWARNING = 48;
    public static final int MB_OK = 0;
    public static final int MB_OKCANCEL = 1;
    public static final int MB_PRECOMPOSED = 1;
    public static final int MB_RETRYCANCEL = 5;
    public static final int MB_RIGHT = 524288;
    public static final int MB_RTLREADING = 1048576;
    public static final int MB_SYSTEMMODAL = 4096;
    public static final int MB_TASKMODAL = 8192;
    public static final int MB_TOPMOST = 262144;
    public static final int MB_YESNO = 4;
    public static final int MB_YESNOCANCEL = 3;
    public static final int MCHT_CALENDAR = 131072;
    public static final int MCHT_CALENDARDATE = 131073;
    public static final int MCM_FIRST = 4096;
    public static final int MCM_GETCURSEL = 4097;
    public static final int MCM_GETMINREQRECT = 4105;
    public static final int MCM_HITTEST = 4110;
    public static final int MCM_SETCURSEL = 4098;
    public static final int MCN_FIRST = -750;
    public static final int MCN_SELCHANGE = -749;
    public static final int MCN_SELECT = -746;
    public static final int MCS_NOTODAY = 16;
    public static final int MCS_WEEKNUMBERS = 4;
    public static final int MDIS_ALLCHILDSTYLES = 1;
    public static final int MDT_EFFECTIVE_DPI = 0;
    public static final int MFS_CHECKED = 8;
    public static final int MFS_DISABLED = 3;
    public static final int MFS_GRAYED = 3;
    public static final int MFT_RADIOCHECK = 512;
    public static final int MFT_RIGHTJUSTIFY = 16384;
    public static final int MFT_RIGHTORDER = 8192;
    public static final int MFT_SEPARATOR = 2048;
    public static final int MFT_STRING = 0;
    public static final int MF_BYCOMMAND = 0;
    public static final int MF_BYPOSITION = 1024;
    public static final int MF_CHECKED = 8;
    public static final int MF_DISABLED = 2;
    public static final int MF_ENABLED = 0;
    public static final int MF_GRAYED = 1;
    public static final int MF_HILITE = 128;
    public static final int MF_POPUP = 16;
    public static final int MF_SEPARATOR = 2048;
    public static final int MF_SYSMENU = 8192;
    public static final int MF_UNCHECKED = 0;
    public static final int MIIM_BITMAP = 128;
    public static final int MIIM_DATA = 32;
    public static final int MIIM_FTYPE = 256;
    public static final int MIIM_ID = 2;
    public static final int MIIM_STATE = 1;
    public static final int MIIM_STRING = 64;
    public static final int MIIM_SUBMENU = 4;
    public static final int MIIM_TYPE = 16;
    public static final int MIM_BACKGROUND = 2;
    public static final int MIM_STYLE = 16;
    public static final int MK_ALT = 32;
    public static final int MK_CONTROL = 8;
    public static final int MK_LBUTTON = 1;
    public static final int MK_MBUTTON = 16;
    public static final int MK_RBUTTON = 2;
    public static final int MK_SHIFT = 4;
    public static final int MK_XBUTTON1 = 32;
    public static final int MK_XBUTTON2 = 64;
    public static final int MM_TEXT = 1;
    public static final int MNC_CLOSE = 1;
    public static final int MNS_CHECKORBMP = 67108864;
    public static final int MONITOR_DEFAULTTOPRIMARY = 1;
    public static final int MONITOR_DEFAULTTONEAREST = 2;
    public static final String MONTHCAL_CLASS = "SysMonthCal32";
    public static final int MOUSEEVENTF_ABSOLUTE = 32768;
    public static final int MOUSEEVENTF_LEFTDOWN = 2;
    public static final int MOUSEEVENTF_LEFTUP = 4;
    public static final int MOUSEEVENTF_MIDDLEDOWN = 32;
    public static final int MOUSEEVENTF_MIDDLEUP = 64;
    public static final int MOUSEEVENTF_MOVE = 1;
    public static final int MOUSEEVENTF_RIGHTDOWN = 8;
    public static final int MOUSEEVENTF_RIGHTUP = 16;
    public static final int MOUSEEVENTF_VIRTUALDESK = 16384;
    public static final int MOUSEEVENTF_WHEEL = 2048;
    public static final int MOUSEEVENTF_XDOWN = 128;
    public static final int MOUSEEVENTF_XUP = 256;
    public static final int MSGF_DIALOGBOX = 0;
    public static final int MSGF_COMMCTRL_BEGINDRAG = 16896;
    public static final int MSGF_COMMCTRL_SIZEHEADER = 16897;
    public static final int MSGF_COMMCTRL_DRAGSELECT = 16898;
    public static final int MSGF_COMMCTRL_TOOLBARCUST = 16899;
    public static final int MSGF_MAINLOOP = 8;
    public static final int MSGF_MENU = 2;
    public static final int MSGF_MOVE = 3;
    public static final int MSGF_MESSAGEBOX = 1;
    public static final int MSGF_NEXTWINDOW = 6;
    public static final int MSGF_SCROLLBAR = 5;
    public static final int MSGF_SIZE = 4;
    public static final int MSGF_USER = 4096;
    public static final int MWT_LEFTMULTIPLY = 2;
    public static final int NI_COMPOSITIONSTR = 21;
    public static final int NID_READY = 128;
    public static final int NID_MULTI_INPUT = 64;
    public static final int NIF_ICON = 2;
    public static final int NIF_INFO = 16;
    public static final int NIF_MESSAGE = 1;
    public static final int NIF_STATE = 8;
    public static final int NIF_TIP = 4;
    public static final int NIIF_ERROR = 3;
    public static final int NIIF_INFO = 1;
    public static final int NIIF_NONE = 0;
    public static final int NIIF_WARNING = 2;
    public static final int NIM_ADD = 0;
    public static final int NIM_DELETE = 2;
    public static final int NIM_MODIFY = 1;
    public static final int NIN_SELECT = 1024;
    public static final int NINF_KEY = 1;
    public static final int NIN_KEYSELECT = 1025;
    public static final int NIN_BALLOONSHOW = 1026;
    public static final int NIN_BALLOONHIDE = 1027;
    public static final int NIN_BALLOONTIMEOUT = 1028;
    public static final int NIN_BALLOONUSERCLICK = 1029;
    public static final int NIS_HIDDEN = 1;
    public static final int NM_FIRST = 0;
    public static final int NM_CLICK = -2;
    public static final int NM_CUSTOMDRAW = -12;
    public static final int NM_DBLCLK = -3;
    public static final int NM_RECOGNIZEGESTURE = -16;
    public static final int NM_RELEASEDCAPTURE = -16;
    public static final int NM_RETURN = -4;
    public static final int NOTIFYICONDATA_V2_SIZE;
    public static final int NULLREGION = 1;
    public static final int NULL_BRUSH = 5;
    public static final int NULL_PEN = 8;
    public static final int OBJID_WINDOW = 0;
    public static final int OBJID_SYSMENU = -1;
    public static final int OBJID_TITLEBAR = -2;
    public static final int OBJID_MENU = -3;
    public static final int OBJID_CLIENT = -4;
    public static final int OBJID_VSCROLL = -5;
    public static final int OBJID_HSCROLL = -6;
    public static final int OBJID_SIZEGRIP = -7;
    public static final int OBJID_CARET = -8;
    public static final int OBJID_CURSOR = -9;
    public static final int OBJID_ALERT = -10;
    public static final int OBJID_SOUND = -11;
    public static final int OBJID_QUERYCLASSNAMEIDX = -12;
    public static final int OBJID_NATIVEOM = -16;
    public static final int OBJ_BITMAP = 7;
    public static final int OBJ_FONT = 6;
    public static final int OBJ_PEN = 1;
    public static final int OBM_CHECKBOXES = 32759;
    public static final int ODS_SELECTED = 1;
    public static final int ODT_MENU = 1;
    public static final int OIC_BANG = 32515;
    public static final int OIC_HAND = 32513;
    public static final int OIC_INFORMATION = 32516;
    public static final int OIC_QUES = 32514;
    public static final int OIC_WINLOGO = 32517;
    public static final int OPAQUE = 2;
    public static final int PATCOPY = 15728673;
    public static final int PATINVERT = 5898313;
    public static final int PBM_GETPOS = 1032;
    public static final int PBM_GETRANGE = 1031;
    public static final int PBM_GETSTATE = 1041;
    public static final int PBM_SETBARCOLOR = 1033;
    public static final int PBM_SETBKCOLOR = 8193;
    public static final int PBM_SETMARQUEE = 1034;
    public static final int PBM_SETPOS = 1026;
    public static final int PBM_SETRANGE32 = 1030;
    public static final int PBM_SETSTATE = 1040;
    public static final int PBM_STEPIT = 1029;
    public static final int PBS_MARQUEE = 8;
    public static final int PBS_SMOOTH = 1;
    public static final int PBS_VERTICAL = 4;
    public static final int PBS_NORMAL = 1;
    public static final int PBS_HOT = 2;
    public static final int PBS_PRESSED = 3;
    public static final int PBS_DISABLED = 4;
    public static final int PBS_DEFAULTED = 5;
    public static final int PBST_NORMAL = 1;
    public static final int PBST_ERROR = 2;
    public static final int PBST_PAUSED = 3;
    public static final int PD_ALLPAGES = 0;
    public static final int PD_COLLATE = 16;
    public static final int PD_PAGENUMS = 2;
    public static final int PD_PRINTTOFILE = 32;
    public static final int PD_RETURNDEFAULT = 1024;
    public static final int PD_SELECTION = 1;
    public static final int PD_USEDEVMODECOPIESANDCOLLATE = 262144;
    public static final int PFM_TABSTOPS = 16;
    public static final int PHYSICALHEIGHT = 111;
    public static final int PHYSICALOFFSETX = 112;
    public static final int PHYSICALOFFSETY = 113;
    public static final int PHYSICALWIDTH = 110;
    public static final int PLANES = 14;
    public static final int PM_NOREMOVE = 0;
    public static final int PM_NOYIELD = 2;
    public static final int QS_HOTKEY = 128;
    public static final int QS_KEY = 1;
    public static final int QS_MOUSEMOVE = 2;
    public static final int QS_MOUSEBUTTON = 4;
    public static final int QS_MOUSE = 6;
    public static final int QS_INPUT = 7;
    public static final int QS_POSTMESSAGE = 8;
    public static final int QS_TIMER = 16;
    public static final int QS_PAINT = 32;
    public static final int QS_SENDMESSAGE = 64;
    public static final int QS_ALLINPUT = 127;
    public static final int PM_QS_INPUT = 458752;
    public static final int PM_QS_POSTMESSAGE = 9961472;
    public static final int PM_QS_PAINT = 2097152;
    public static final int PM_QS_SENDMESSAGE = 4194304;
    public static final int PM_REMOVE = 1;
    public static final String PROGRESS_CLASS = "msctls_progress32";
    public static final int PRF_CHILDREN = 16;
    public static final int PRF_CLIENT = 4;
    public static final int PRF_ERASEBKGND = 8;
    public static final int PRF_NONCLIENT = 2;
    public static final int PROGRESSCHUNKSIZE = 2411;
    public static final int PROGRESSSPACESIZE = 2412;
    public static final int PS_DASH = 1;
    public static final int PS_DASHDOT = 3;
    public static final int PS_DASHDOTDOT = 4;
    public static final int PS_DOT = 2;
    public static final int PS_ENDCAP_FLAT = 512;
    public static final int PS_ENDCAP_SQUARE = 256;
    public static final int PS_ENDCAP_ROUND = 0;
    public static final int PS_ENDCAP_MASK = 3840;
    public static final int PS_GEOMETRIC = 65536;
    public static final int PS_JOIN_BEVEL = 4096;
    public static final int PS_JOIN_MASK = 61440;
    public static final int PS_JOIN_MITER = 8192;
    public static final int PS_JOIN_ROUND = 0;
    public static final int PS_SOLID = 0;
    public static final int PS_STYLE_MASK = 15;
    public static final int PS_TYPE_MASK = 983040;
    public static final int PS_USERSTYLE = 7;
    public static final int R2_COPYPEN = 13;
    public static final int R2_XORPEN = 7;
    public static final int RASTERCAPS = 38;
    public static final int RASTER_FONTTYPE = 1;
    public static final int RBBIM_CHILD = 16;
    public static final int RBBIM_CHILDSIZE = 32;
    public static final int RBBIM_COLORS = 2;
    public static final int RBBIM_HEADERSIZE = 2048;
    public static final int RBBIM_ID = 256;
    public static final int RBBIM_IDEALSIZE = 512;
    public static final int RBBIM_SIZE = 64;
    public static final int RBBIM_STYLE = 1;
    public static final int RBBIM_TEXT = 4;
    public static final int RBBS_BREAK = 1;
    public static final int RBBS_GRIPPERALWAYS = 128;
    public static final int RBBS_NOGRIPPER = 256;
    public static final int RBBS_USECHEVRON = 512;
    public static final int RBBS_VARIABLEHEIGHT = 64;
    public static final int RBN_FIRST = -831;
    public static final int RBN_BEGINDRAG = -835;
    public static final int RBN_CHILDSIZE = -839;
    public static final int RBN_CHEVRONPUSHED = -841;
    public static final int RBN_HEIGHTCHANGE = -831;
    public static final int RBS_UNCHECKEDNORMAL = 1;
    public static final int RBS_UNCHECKEDHOT = 2;
    public static final int RBS_UNCHECKEDPRESSED = 3;
    public static final int RBS_UNCHECKEDDISABLED = 4;
    public static final int RBS_CHECKEDNORMAL = 5;
    public static final int RBS_CHECKEDHOT = 6;
    public static final int RBS_CHECKEDPRESSED = 7;
    public static final int RBS_CHECKEDDISABLED = 8;
    public static final int RBS_DBLCLKTOGGLE = 32768;
    public static final int RBS_BANDBORDERS = 1024;
    public static final int RBS_VARHEIGHT = 512;
    public static final int RB_DELETEBAND = 1026;
    public static final int RB_GETBANDBORDERS = 1058;
    public static final int RB_GETBANDCOUNT = 1036;
    public static final int RB_GETBANDINFO = 1052;
    public static final int RB_GETBANDMARGINS = 1064;
    public static final int RB_GETBARHEIGHT = 1051;
    public static final int RB_GETBKCOLOR = 1044;
    public static final int RB_GETRECT = 1033;
    public static final int RB_GETTEXTCOLOR = 1046;
    public static final int RB_IDTOINDEX = 1040;
    public static final int RB_INSERTBAND = 1034;
    public static final int RB_MOVEBAND = 1063;
    public static final int RB_SETBANDINFO = 1035;
    public static final int RB_SETBKCOLOR = 1043;
    public static final int RB_SETTEXTCOLOR = 1045;
    public static final int RDW_ALLCHILDREN = 128;
    public static final int RDW_ERASE = 4;
    public static final int RDW_FRAME = 1024;
    public static final int RDW_INVALIDATE = 1;
    public static final int RDW_UPDATENOW = 256;
    public static final String REBARCLASSNAME = "ReBarWindow32";
    public static final int REG_DWORD = 4;
    public static final int REG_OPTION_VOLATILE = 1;
    public static final int RGN_AND = 1;
    public static final int RGN_COPY = 5;
    public static final int RGN_DIFF = 4;
    public static final int RGN_ERROR = 0;
    public static final int RGN_OR = 2;
    public static final int SBP_ARROWBTN = 1;
    public static final int SBS_HORZ = 0;
    public static final int SBS_VERT = 1;
    public static final int SB_BOTH = 3;
    public static final int SB_BOTTOM = 7;
    public static final int SB_NONE = 0;
    public static final int SB_CONST_ALPHA = 1;
    public static final int SB_PIXEL_ALPHA = 2;
    public static final int SB_PREMULT_ALPHA = 4;
    public static final int SB_CTL = 2;
    public static final int SB_ENDSCROLL = 8;
    public static final int SB_HORZ = 0;
    public static final int SB_LINEDOWN = 1;
    public static final int SB_LINEUP = 0;
    public static final int SB_PAGEDOWN = 3;
    public static final int SB_PAGEUP = 2;
    public static final int SB_THUMBPOSITION = 4;
    public static final int SB_THUMBTRACK = 5;
    public static final int SB_TOP = 6;
    public static final int SB_VERT = 1;
    public static final int SC_CLOSE = 61536;
    public static final int SC_MOVE = 61456;
    public static final int SC_HSCROLL = 61568;
    public static final int SC_KEYMENU = 61696;
    public static final int SC_MAXIMIZE = 61488;
    public static final int SC_MINIMIZE = 61472;
    public static final int SC_NEXTWINDOW = 61504;
    public static final int SC_RESTORE = 61728;
    public static final int SC_SIZE = 61440;
    public static final int SC_TASKLIST = 61744;
    public static final int SC_VSCROLL = 61552;
    public static final int SCRBS_NORMAL = 1;
    public static final int SCRBS_HOT = 2;
    public static final int SCRBS_PRESSED = 3;
    public static final int SCRBS_DISABLED = 4;
    public static final int SET_FEATURE_ON_PROCESS = 2;
    public static final int SHADEBLENDCAPS = 120;
    public static final int SHGFI_ICON = 256;
    public static final int SHGFI_SMALLICON = 1;
    public static final int SHGFI_USEFILEATTRIBUTES = 16;
    public static final int SIGDN_FILESYSPATH = -2147123200;
    public static final int SIF_ALL = 23;
    public static final int SIF_DISABLENOSCROLL = 8;
    public static final int SIF_PAGE = 2;
    public static final int SIF_POS = 4;
    public static final int SIF_RANGE = 1;
    public static final int SIF_TRACKPOS = 16;
    public static final int SIZE_RESTORED = 0;
    public static final int SIZE_MINIMIZED = 1;
    public static final int SIZE_MAXIMIZED = 2;
    public static final int SM_CMONITORS = 80;
    public static final int SM_CXBORDER = 5;
    public static final int SM_CXCURSOR = 13;
    public static final int SM_CXDOUBLECLK = 36;
    public static final int SM_CYDOUBLECLK = 37;
    public static final int SM_CXEDGE = 45;
    public static final int SM_CXFOCUSBORDER = 83;
    public static final int SM_CXHSCROLL = 21;
    public static final int SM_CXICON = 11;
    public static final int SM_CYICON = 12;
    public static final int SM_CXVIRTUALSCREEN = 78;
    public static final int SM_CYVIRTUALSCREEN = 79;
    public static final int SM_CXSMICON = 49;
    public static final int SM_CYSMICON = 50;
    public static final int SM_CXSCREEN = 0;
    public static final int SM_XVIRTUALSCREEN = 76;
    public static final int SM_YVIRTUALSCREEN = 77;
    public static final int SM_CXVSCROLL = 2;
    public static final int SM_CYBORDER = 6;
    public static final int SM_CYCURSOR = 14;
    public static final int SM_CYEDGE = 46;
    public static final int SM_CYFOCUSBORDER = 84;
    public static final int SM_CYHSCROLL = 3;
    public static final int SM_CYMENU = 15;
    public static final int SM_CXMINTRACK = 34;
    public static final int SM_CYMINTRACK = 35;
    public static final int SM_CXMAXTRACK = 59;
    public static final int SM_CYMAXTRACK = 60;
    public static final int SM_CMOUSEBUTTONS = 43;
    public static final int SM_CYSCREEN = 1;
    public static final int SM_CYVSCROLL = 20;
    public static final int SM_DIGITIZER = 94;
    public static final int SM_MAXIMUMTOUCHES = 95;
    public static final int SPI_GETFONTSMOOTHINGTYPE = 8202;
    public static final int SPI_GETHIGHCONTRAST = 66;
    public static final int SPI_GETWORKAREA = 48;
    public static final int SPI_GETMOUSEVANISH = 4128;
    public static final int SPI_GETNONCLIENTMETRICS = 41;
    public static final int SPI_GETWHEELSCROLLCHARS = 108;
    public static final int SPI_GETWHEELSCROLLLINES = 104;
    public static final int SPI_GETCARETWIDTH = 8198;
    public static final int SPI_SETSIPINFO = 224;
    public static final int SPI_SETHIGHCONTRAST = 67;
    public static final int SRCAND = 8913094;
    public static final int SRCCOPY = 13369376;
    public static final int SRCINVERT = 6684742;
    public static final int SRCPAINT = 15597702;
    public static final int SS_BITMAP = 14;
    public static final int SS_CENTER = 1;
    public static final int SS_CENTERIMAGE = 512;
    public static final int SS_EDITCONTROL = 8192;
    public static final int SS_ICON = 3;
    public static final int SS_LEFT = 0;
    public static final int SS_LEFTNOWORDWRAP = 12;
    public static final int SS_NOTIFY = 256;
    public static final int SS_OWNERDRAW = 13;
    public static final int SS_REALSIZEIMAGE = 2048;
    public static final int SS_RIGHT = 2;
    public static final int SSA_FALLBACK = 32;
    public static final int SSA_GLYPHS = 128;
    public static final int SSA_METAFILE = 2048;
    public static final int SSA_LINK = 4096;
    public static final int STARTF_USESHOWWINDOW = 1;
    public static final int STATE_SYSTEM_INVISIBLE = 32768;
    public static final int STATE_SYSTEM_OFFSCREEN = 65536;
    public static final int STATE_SYSTEM_UNAVAILABLE = 1;
    public static final int STD_COPY = 1;
    public static final int STD_CUT = 0;
    public static final int STD_FILENEW = 6;
    public static final int STD_FILEOPEN = 7;
    public static final int STD_FILESAVE = 8;
    public static final int STD_PASTE = 2;
    public static final int STM_GETIMAGE = 371;
    public static final int STM_SETIMAGE = 370;
    public static final int SWP_ASYNCWINDOWPOS = 16384;
    public static final int SWP_DRAWFRAME = 32;
    public static final int SWP_FRAMECHANGED = 32;
    public static final int SWP_NOACTIVATE = 16;
    public static final int SWP_NOCOPYBITS = 256;
    public static final int SWP_NOMOVE = 2;
    public static final int SWP_NOREDRAW = 8;
    public static final int SWP_NOSIZE = 1;
    public static final int SWP_NOZORDER = 4;
    public static final int SW_ERASE = 4;
    public static final int SW_HIDE = 0;
    public static final int SW_INVALIDATE = 2;
    public static final int SW_MINIMIZE = 6;
    public static final int SW_PARENTOPENING = 3;
    public static final int SW_RESTORE = 9;
    public static final int SW_SCROLLCHILDREN = 1;
    public static final int SW_SHOW = 5;
    public static final int SW_SHOWMAXIMIZED = 3;
    public static final int SW_SHOWMINIMIZED = 2;
    public static final int SW_SHOWMINNOACTIVE = 7;
    public static final int SW_SHOWNA = 8;
    public static final int SW_SHOWNOACTIVATE = 4;
    public static final int SYSRGN = 4;
    public static final int SYSTEM_FONT = 13;
    public static final int S_OK = 0;
    public static final int TABP_BODY = 10;
    public static final int TBCDRF_USECDCOLORS = 8388608;
    public static final int TBCDRF_NOBACKGROUND = 4194304;
    public static final int TBIF_COMMAND = 32;
    public static final int TBIF_STATE = 4;
    public static final int TBIF_IMAGE = 1;
    public static final int TBIF_LPARAM = 16;
    public static final int TBIF_SIZE = 64;
    public static final int TBIF_STYLE = 8;
    public static final int TBIF_TEXT = 2;
    public static final int TB_GETEXTENDEDSTYLE = 1109;
    public static final int TB_GETRECT = 1075;
    public static final int TBM_GETLINESIZE = 1048;
    public static final int TBM_GETPAGESIZE = 1046;
    public static final int TBM_GETPOS = 1024;
    public static final int TBM_GETRANGEMAX = 1026;
    public static final int TBM_GETRANGEMIN = 1025;
    public static final int TBM_GETTHUMBRECT = 1049;
    public static final int TBM_SETLINESIZE = 1047;
    public static final int TBM_SETPAGESIZE = 1045;
    public static final int TBM_SETPOS = 1029;
    public static final int TBM_SETRANGEMAX = 1032;
    public static final int TBM_SETRANGEMIN = 1031;
    public static final int TBM_SETTICFREQ = 1044;
    public static final int TBN_DROPDOWN = -710;
    public static final int TBN_FIRST = -700;
    public static final int TBN_HOTITEMCHANGE = -713;
    public static final int TBSTATE_CHECKED = 1;
    public static final int TBSTATE_PRESSED = 2;
    public static final int TBSTYLE_CUSTOMERASE = 8192;
    public static final int TBSTYLE_DROPDOWN = 8;
    public static final int TBSTATE_ENABLED = 4;
    public static final int TBSTYLE_AUTOSIZE = 16;
    public static final int TBSTYLE_EX_DOUBLEBUFFER = 128;
    public static final int TBSTYLE_EX_DRAWDDARROWS = 1;
    public static final int TBSTYLE_EX_HIDECLIPPEDBUTTONS = 16;
    public static final int TBSTYLE_EX_MIXEDBUTTONS = 8;
    public static final int TBSTYLE_FLAT = 2048;
    public static final int TBSTYLE_LIST = 4096;
    public static final int TBSTYLE_TOOLTIPS = 256;
    public static final int TBSTYLE_TRANSPARENT = 32768;
    public static final int TBSTYLE_WRAPABLE = 512;
    public static final int TBS_AUTOTICKS = 1;
    public static final int TBS_BOTH = 8;
    public static final int TBS_DOWNISLEFT = 1024;
    public static final int TBS_HORZ = 0;
    public static final int TBS_VERT = 2;
    public static final int TB_ADDSTRING = 1101;
    public static final int TB_AUTOSIZE = 1057;
    public static final int TB_BUTTONCOUNT = 1048;
    public static final int TB_BUTTONSTRUCTSIZE = 1054;
    public static final int TB_COMMANDTOINDEX = 1049;
    public static final int TB_DELETEBUTTON = 1046;
    public static final int TB_ENDTRACK = 8;
    public static final int TB_GETBUTTON = 1047;
    public static final int TB_GETBUTTONINFO = 1087;
    public static final int TB_GETBUTTONSIZE = 1082;
    public static final int TB_GETBUTTONTEXT = 1099;
    public static final int TB_GETDISABLEDIMAGELIST = 1079;
    public static final int TB_GETHOTIMAGELIST = 1077;
    public static final int TB_GETHOTITEM = 1095;
    public static final int TB_GETIMAGELIST = 1073;
    public static final int TB_GETITEMRECT = 1053;
    public static final int TB_GETPADDING = 1110;
    public static final int TB_GETROWS = 1064;
    public static final int TB_GETSTATE = 1042;
    public static final int TB_GETTOOLTIPS = 1059;
    public static final int TB_INSERTBUTTON = 1091;
    public static final int TB_LOADIMAGES = 1074;
    public static final int TB_MAPACCELERATOR = 1114;
    public static final int TB_SETBITMAPSIZE = 1056;
    public static final int TB_SETBUTTONINFO = 1088;
    public static final int TB_SETBUTTONSIZE = 1055;
    public static final int TB_SETDISABLEDIMAGELIST = 1078;
    public static final int TB_SETEXTENDEDSTYLE = 1108;
    public static final int TB_SETHOTIMAGELIST = 1076;
    public static final int TB_SETHOTITEM = 1096;
    public static final int TB_SETIMAGELIST = 1072;
    public static final int TB_SETPARENT = 1061;
    public static final int TB_SETROWS = 1063;
    public static final int TB_SETSTATE = 1041;
    public static final int TB_THUMBPOSITION = 4;
    public static final int TBPF_NOPROGRESS = 0;
    public static final int TBPF_INDETERMINATE = 1;
    public static final int TBPF_NORMAL = 2;
    public static final int TBPF_ERROR = 4;
    public static final int TBPF_PAUSED = 8;
    public static final int TCIF_IMAGE = 2;
    public static final int TCIF_TEXT = 1;
    public static final int TCI_SRCCHARSET = 1;
    public static final int TCI_SRCCODEPAGE = 2;
    public static final int TCM_ADJUSTRECT = 4904;
    public static final int TCM_DELETEITEM = 4872;
    public static final int TCM_GETCURSEL = 4875;
    public static final int TCM_GETITEMCOUNT = 4868;
    public static final int TCM_GETITEMRECT = 4874;
    public static final int TCM_GETTOOLTIPS = 4909;
    public static final int TCM_HITTEST = 4877;
    public static final int TCM_INSERTITEM = 4926;
    public static final int TCM_SETCURSEL = 4876;
    public static final int TCM_SETIMAGELIST = 4867;
    public static final int TCM_SETITEM = 4925;
    public static final int TCN_SELCHANGE = -551;
    public static final int TCN_SELCHANGING = -552;
    public static final int TCS_BOTTOM = 2;
    public static final int TCS_FOCUSNEVER = 32768;
    public static final int TCS_MULTILINE = 512;
    public static final int TCS_TABS = 0;
    public static final int TCS_TOOLTIPS = 16384;
    public static final int TECHNOLOGY = 2;
    public static final int TF_ATTR_INPUT = 0;
    public static final int TF_ATTR_TARGET_CONVERTED = 1;
    public static final int TF_ATTR_CONVERTED = 2;
    public static final int TF_ATTR_TARGET_NOTCONVERTED = 3;
    public static final int TF_ATTR_INPUT_ERROR = 4;
    public static final int TF_ATTR_FIXEDCONVERTED = 5;
    public static final int TF_ATTR_OTHER = -1;
    public static final int TF_CT_NONE = 0;
    public static final int TF_CT_SYSCOLOR = 1;
    public static final int TF_CT_COLORREF = 2;
    public static final int TF_LS_NONE = 0;
    public static final int TF_LS_SOLID = 1;
    public static final int TF_LS_DOT = 2;
    public static final int TF_LS_DASH = 3;
    public static final int TF_LS_SQUIGGLE = 4;
    public static final int TME_HOVER = 1;
    public static final int TME_LEAVE = 2;
    public static final int TME_QUERY = 1073741824;
    public static final int TMPF_VECTOR = 2;
    public static final int TMT_CONTENTMARGINS = 3602;
    public static final int TOUCHEVENTF_MOVE = 1;
    public static final int TOUCHEVENTF_DOWN = 2;
    public static final int TOUCHEVENTF_UP = 4;
    public static final int TOUCHEVENTF_INRANGE = 8;
    public static final int TOUCHEVENTF_PRIMARY = 16;
    public static final int TOUCHEVENTF_NOCOALESCE = 32;
    public static final int TOUCHEVENTF_PALM = 128;
    public static final String TOOLBARCLASSNAME = "ToolbarWindow32";
    public static final String TOOLTIPS_CLASS = "tooltips_class32";
    public static final int TPM_LEFTALIGN = 0;
    public static final int TPM_LEFTBUTTON = 0;
    public static final int TPM_RIGHTBUTTON = 2;
    public static final int TPM_RIGHTALIGN = 8;
    public static final String TRACKBAR_CLASS = "msctls_trackbar32";
    public static final int TRANSPARENT = 1;
    public static final int TREIS_DISABLED = 4;
    public static final int TREIS_HOT = 2;
    public static final int TREIS_NORMAL = 1;
    public static final int TREIS_SELECTED = 3;
    public static final int TREIS_SELECTEDNOTFOCUS = 5;
    public static final int TS_TRUE = 1;
    public static final int TTDT_AUTOMATIC = 0;
    public static final int TTDT_RESHOW = 1;
    public static final int TTDT_AUTOPOP = 2;
    public static final int TTDT_INITIAL = 3;
    public static final int TTF_ABSOLUTE = 128;
    public static final int TTF_IDISHWND = 1;
    public static final int TTF_SUBCLASS = 16;
    public static final int TTF_RTLREADING = 4;
    public static final int TTF_TRACK = 32;
    public static final int TTF_TRANSPARENT = 256;
    public static final int TTI_NONE = 0;
    public static final int TTI_INFO = 1;
    public static final int TTI_WARNING = 2;
    public static final int TTI_ERROR = 3;
    public static final int TTM_ACTIVATE = 1025;
    public static final int TTM_ADDTOOL = 1074;
    public static final int TTM_ADJUSTRECT = 1055;
    public static final int TTM_GETCURRENTTOOL = 1083;
    public static final int TTM_GETDELAYTIME = 1045;
    public static final int TTM_DELTOOL = 1075;
    public static final int TTM_GETTOOLINFO = 1077;
    public static final int TTM_GETTOOLCOUNT = 1037;
    public static final int TTM_NEWTOOLRECT = 1076;
    public static final int TTM_POP = 1052;
    public static final int TTM_SETDELAYTIME = 1027;
    public static final int TTM_SETMAXTIPWIDTH = 1048;
    public static final int TTM_SETTITLE = 1057;
    public static final int TTM_TRACKPOSITION = 1042;
    public static final int TTM_TRACKACTIVATE = 1041;
    public static final int TTM_UPDATE = 1053;
    public static final int TTM_UPDATETIPTEXT = 1081;
    public static final int TTN_FIRST = -520;
    public static final int TTN_GETDISPINFO = -530;
    public static final int TTN_POP = -522;
    public static final int TTN_SHOW = -521;
    public static final int TTS_ALWAYSTIP = 1;
    public static final int TTS_BALLOON = 64;
    public static final int TTS_NOANIMATE = 16;
    public static final int TTS_NOFADE = 32;
    public static final int TTS_NOPREFIX = 2;
    public static final int TV_FIRST = 4352;
    public static final int TVE_COLLAPSE = 1;
    public static final int TVE_COLLAPSERESET = 32768;
    public static final int TVE_EXPAND = 2;
    public static final int TVGN_CARET = 9;
    public static final int TVGN_CHILD = 4;
    public static final int TVGN_DROPHILITED = 8;
    public static final int TVGN_FIRSTVISIBLE = 5;
    public static final int TVGN_LASTVISIBLE = 10;
    public static final int TVGN_NEXT = 1;
    public static final int TVGN_NEXTVISIBLE = 6;
    public static final int TVGN_PARENT = 3;
    public static final int TVGN_PREVIOUS = 2;
    public static final int TVGN_PREVIOUSVISIBLE = 7;
    public static final int TVGN_ROOT = 0;
    public static final int TVHT_ONITEM = 70;
    public static final int TVHT_ONITEMBUTTON = 16;
    public static final int TVHT_ONITEMICON = 2;
    public static final int TVHT_ONITEMINDENT = 8;
    public static final int TVHT_ONITEMRIGHT = 32;
    public static final int TVHT_ONITEMLABEL = 4;
    public static final int TVHT_ONITEMSTATEICON = 64;
    public static final int TVIF_HANDLE = 16;
    public static final int TVIF_IMAGE = 2;
    public static final int TVIF_INTEGRAL = 128;
    public static final int TVIF_PARAM = 4;
    public static final int TVIF_SELECTEDIMAGE = 32;
    public static final int TVIF_STATE = 8;
    public static final int TVIF_TEXT = 1;
    public static final int TVIS_DROPHILITED = 8;
    public static final int TVIS_EXPANDED = 32;
    public static final int TVIS_SELECTED = 2;
    public static final int TVIS_STATEIMAGEMASK = 61440;
    public static final long TVI_FIRST = -65535L;
    public static final long TVI_LAST = -65534L;
    public static final long TVI_ROOT = -65536L;
    public static final long TVI_SORT = -65533L;
    public static final int TVM_CREATEDRAGIMAGE = 4370;
    public static final int TVM_DELETEITEM = 4353;
    public static final int TVM_ENSUREVISIBLE = 4372;
    public static final int TVM_EXPAND = 4354;
    public static final int TVM_GETBKCOLOR = 4383;
    public static final int TVM_GETCOUNT = 4357;
    public static final int TVM_GETEXTENDEDSTYLE = 4397;
    public static final int TVM_GETIMAGELIST = 4360;
    public static final int TVM_GETITEM = 4414;
    public static final int TVM_GETITEMHEIGHT = 4380;
    public static final int TVM_GETITEMRECT = 4356;
    public static final int TVM_GETITEMSTATE = 4391;
    public static final int TVM_GETNEXTITEM = 4362;
    public static final int TVM_GETTEXTCOLOR = 4384;
    public static final int TVM_GETTOOLTIPS = 4377;
    public static final int TVM_GETVISIBLECOUNT = 4368;
    public static final int TVM_HITTEST = 4369;
    public static final int TVM_INSERTITEM = 4402;
    public static final int TVM_MAPACCIDTOHTREEITEM = 4394;
    public static final int TVM_MAPHTREEITEMTOACCID = 4395;
    public static final int TVM_SELECTITEM = 4363;
    public static final int TVM_SETBKCOLOR = 4381;
    public static final int TVM_SETEXTENDEDSTYLE = 4396;
    public static final int TVM_SETIMAGELIST = 4361;
    public static final int TVM_SETINDENT = 4359;
    public static final int TVM_SETINSERTMARK = 4378;
    public static final int TVM_SETITEM = 4415;
    public static final int TVM_SETITEMHEIGHT = 4379;
    public static final int TVM_SETSCROLLTIME = 4385;
    public static final int TVM_SETTEXTCOLOR = 4382;
    public static final int TVM_SORTCHILDREN = 4371;
    public static final int TVM_SORTCHILDRENCB = 4373;
    public static final int TVN_BEGINDRAG = -456;
    public static final int TVN_BEGINRDRAG = -457;
    public static final int TVN_FIRST = -400;
    public static final int TVN_GETDISPINFO = -452;
    public static final int TVN_ITEMCHANGING = -417;
    public static final int TVN_ITEMEXPANDED = -455;
    public static final int TVN_ITEMEXPANDING = -454;
    public static final int TVN_SELCHANGED = -451;
    public static final int TVN_SELCHANGING = -450;
    public static final int TVP_GLYPH = 2;
    public static final int TVP_TREEITEM = 1;
    public static final int TVSIL_NORMAL = 0;
    public static final int TVSIL_STATE = 2;
    public static final int TVS_DISABLEDRAGDROP = 16;
    public static final int TVS_EX_AUTOHSCROLL = 32;
    public static final int TVS_EX_DOUBLEBUFFER = 4;
    public static final int TVS_EX_DIMMEDCHECKBOXES = 512;
    public static final int TVS_EX_DRAWIMAGEASYNC = 1024;
    public static final int TVS_EX_EXCLUSIONCHECKBOXES = 256;
    public static final int TVS_EX_FADEINOUTEXPANDOS = 64;
    public static final int TVS_EX_MULTISELECT = 2;
    public static final int TVS_EX_NOINDENTSTATE = 8;
    public static final int TVS_EX_PARTIALCHECKBOXES = 128;
    public static final int TVS_EX_RICHTOOLTIP = 16;
    public static final int TVS_FULLROWSELECT = 4096;
    public static final int TVS_HASBUTTONS = 1;
    public static final int TVS_HASLINES = 2;
    public static final int TVS_LINESATROOT = 4;
    public static final int TVS_NOHSCROLL = 32768;
    public static final int TVS_NONEVENHEIGHT = 16384;
    public static final int TVS_NOSCROLL = 8192;
    public static final int TVS_NOTOOLTIPS = 128;
    public static final int TVS_SHOWSELALWAYS = 32;
    public static final int TVS_TRACKSELECT = 512;
    public static final int UDM_GETACCEL = 1132;
    public static final int UDM_GETRANGE32 = 1136;
    public static final int UDM_GETPOS32 = 1138;
    public static final int UDM_SETACCEL = 1131;
    public static final int UDM_SETRANGE32 = 1135;
    public static final int UDM_SETPOS32 = 1137;
    public static final int UDN_DELTAPOS = -722;
    public static final int UDS_ALIGNLEFT = 8;
    public static final int UDS_ALIGNRIGHT = 4;
    public static final int UDS_AUTOBUDDY = 16;
    public static final int UDS_WRAP = 1;
    public static final int UIS_CLEAR = 2;
    public static final int UIS_INITIALIZE = 3;
    public static final int UIS_SET = 1;
    public static final int UISF_HIDEACCEL = 2;
    public static final int UISF_HIDEFOCUS = 1;
    public static final String UPDOWN_CLASS = "msctls_updown32";
    public static final int USP_E_SCRIPT_NOT_IN_FONT = -2147220992;
    public static final int VERTRES = 10;
    public static final int VK_BACK = 8;
    public static final int VK_CANCEL = 3;
    public static final int VK_CAPITAL = 20;
    public static final int VK_CONTROL = 17;
    public static final int VK_DECIMAL = 110;
    public static final int VK_DELETE = 46;
    public static final int VK_DIVIDE = 111;
    public static final int VK_DOWN = 40;
    public static final int VK_END = 35;
    public static final int VK_ESCAPE = 27;
    public static final int VK_F1 = 112;
    public static final int VK_F10 = 121;
    public static final int VK_F11 = 122;
    public static final int VK_F12 = 123;
    public static final int VK_F13 = 124;
    public static final int VK_F14 = 125;
    public static final int VK_F15 = 126;
    public static final int VK_F16 = 127;
    public static final int VK_F17 = 128;
    public static final int VK_F18 = 129;
    public static final int VK_F19 = 130;
    public static final int VK_F20 = 131;
    public static final int VK_F2 = 113;
    public static final int VK_F3 = 114;
    public static final int VK_F4 = 115;
    public static final int VK_F5 = 116;
    public static final int VK_F6 = 117;
    public static final int VK_F7 = 118;
    public static final int VK_F8 = 119;
    public static final int VK_F9 = 120;
    public static final int VK_HANJA = 25;
    public static final int VK_HOME = 36;
    public static final int VK_INSERT = 45;
    public static final int VK_L = 76;
    public static final int VK_LBUTTON = 1;
    public static final int VK_LEFT = 37;
    public static final int VK_LCONTROL = 162;
    public static final int VK_LMENU = 164;
    public static final int VK_LSHIFT = 160;
    public static final int VK_MBUTTON = 4;
    public static final int VK_MENU = 18;
    public static final int VK_MULTIPLY = 106;
    public static final int VK_N = 78;
    public static final int VK_O = 79;
    public static final int VK_NEXT = 34;
    public static final int VK_NUMLOCK = 144;
    public static final int VK_NUMPAD0 = 96;
    public static final int VK_NUMPAD1 = 97;
    public static final int VK_NUMPAD2 = 98;
    public static final int VK_NUMPAD3 = 99;
    public static final int VK_NUMPAD4 = 100;
    public static final int VK_NUMPAD5 = 101;
    public static final int VK_NUMPAD6 = 102;
    public static final int VK_NUMPAD7 = 103;
    public static final int VK_NUMPAD8 = 104;
    public static final int VK_NUMPAD9 = 105;
    public static final int VK_PAUSE = 19;
    public static final int VK_PRIOR = 33;
    public static final int VK_RBUTTON = 2;
    public static final int VK_RETURN = 13;
    public static final int VK_RIGHT = 39;
    public static final int VK_RCONTROL = 163;
    public static final int VK_RMENU = 165;
    public static final int VK_RSHIFT = 161;
    public static final int VK_SCROLL = 145;
    public static final int VK_SEPARATOR = 108;
    public static final int VK_SHIFT = 16;
    public static final int VK_SNAPSHOT = 44;
    public static final int VK_SPACE = 32;
    public static final int VK_SUBTRACT = 109;
    public static final int VK_TAB = 9;
    public static final int VK_UP = 38;
    public static final int VK_XBUTTON1 = 5;
    public static final int VK_XBUTTON2 = 6;
    public static final int VK_ADD = 107;
    public static final int VT_BOOL = 11;
    public static final int VT_LPWSTR = 31;
    public static final short VARIANT_TRUE = -1;
    public static final short VARIANT_FALSE = 0;
    public static final short WA_CLICKACTIVE = 2;
    public static final String WC_HEADER = "SysHeader32";
    public static final String WC_LINK = "SysLink";
    public static final String WC_LISTVIEW = "SysListView32";
    public static final String WC_TABCONTROL = "SysTabControl32";
    public static final String WC_TREEVIEW = "SysTreeView32";
    public static final int WINDING = 2;
    public static final int WH_CBT = 5;
    public static final int WH_GETMESSAGE = 3;
    public static final int WH_MSGFILTER = -1;
    public static final int WH_FOREGROUNDIDLE = 11;
    public static final int WHEEL_DELTA = 120;
    public static final int WHEEL_PAGESCROLL = -1;
    public static final int WHITE_BRUSH = 0;
    public static final int WHITENESS = 16711778;
    public static final int WM_ACTIVATE = 6;
    public static final int WM_ACTIVATEAPP = 28;
    public static final int WM_APP = 32768;
    public static final int WM_DWMCOLORIZATIONCOLORCHANGED = 800;
    public static final int WM_CANCELMODE = 31;
    public static final int WM_CAPTURECHANGED = 533;
    public static final int WM_CHANGEUISTATE = 295;
    public static final int WM_CHAR = 258;
    public static final int WM_CLEAR = 771;
    public static final int WM_CLOSE = 16;
    public static final int WM_COMMAND = 273;
    public static final int WM_CONTEXTMENU = 123;
    public static final int WM_COPY = 769;
    public static final int WM_CREATE = 1;
    public static final int WM_CTLCOLORBTN = 309;
    public static final int WM_CTLCOLORDLG = 310;
    public static final int WM_CTLCOLOREDIT = 307;
    public static final int WM_CTLCOLORLISTBOX = 308;
    public static final int WM_CTLCOLORMSGBOX = 306;
    public static final int WM_CTLCOLORSCROLLBAR = 311;
    public static final int WM_CTLCOLORSTATIC = 312;
    public static final int WM_CUT = 768;
    public static final int WM_DEADCHAR = 259;
    public static final int WM_DESTROY = 2;
    public static final int WM_DPICHANGED = 736;
    public static final int WM_DRAWITEM = 43;
    public static final int WM_ENDSESSION = 22;
    public static final int WM_ENTERIDLE = 289;
    public static final int WM_ERASEBKGND = 20;
    public static final int WM_GESTURE = 281;
    public static final int WM_GETDLGCODE = 135;
    public static final int WM_GETFONT = 49;
    public static final int WM_GETOBJECT = 61;
    public static final int WM_GETMINMAXINFO = 36;
    public static final int WM_HELP = 83;
    public static final int WM_HOTKEY = 786;
    public static final int WM_HSCROLL = 276;
    public static final int WM_IME_CHAR = 646;
    public static final int WM_IME_COMPOSITION = 271;
    public static final int WM_IME_COMPOSITION_START = 269;
    public static final int WM_IME_ENDCOMPOSITION = 270;
    public static final int WM_INITDIALOG = 272;
    public static final int WM_INITMENUPOPUP = 279;
    public static final int WM_INPUTLANGCHANGE = 81;
    public static final int WM_KEYDOWN = 256;
    public static final int WM_KEYFIRST = 256;
    public static final int WM_KEYLAST = 264;
    public static final int WM_KEYUP = 257;
    public static final int WM_KILLFOCUS = 8;
    public static final int WM_LBUTTONDBLCLK = 515;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_MBUTTONDBLCLK = 521;
    public static final int WM_MBUTTONDOWN = 519;
    public static final int WM_MBUTTONUP = 520;
    public static final int WM_MEASUREITEM = 44;
    public static final int WM_MENUCHAR = 288;
    public static final int WM_MENUSELECT = 287;
    public static final int WM_MOUSEACTIVATE = 33;
    public static final int WM_MOUSEFIRST = 512;
    public static final int WM_MOUSEHOVER = 673;
    public static final int WM_MOUSELEAVE = 675;
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_MOUSEWHEEL = 522;
    public static final int WM_MOUSEHWHEEL = 526;
    public static final int WM_MOUSELAST = 525;
    public static final int WM_MOVE = 3;
    public static final int WM_NCACTIVATE = 134;
    public static final int WM_NCCALCSIZE = 131;
    public static final int WM_NCHITTEST = 132;
    public static final int WM_NCLBUTTONDOWN = 161;
    public static final int WM_NCPAINT = 133;
    public static final int WM_NOTIFY = 78;
    public static final int WM_NULL = 0;
    public static final int WM_PAINT = 15;
    public static final int WM_PARENTNOTIFY = 528;
    public static final int WM_ENTERMENULOOP = 529;
    public static final int WM_EXITMENULOOP = 530;
    public static final int WM_ENTERSIZEMOVE = 561;
    public static final int WM_EXITSIZEMOVE = 562;
    public static final int WM_PASTE = 770;
    public static final int WM_PRINT = 791;
    public static final int WM_PRINTCLIENT = 792;
    public static final int WM_QUERYENDSESSION = 17;
    public static final int WM_QUERYOPEN = 19;
    public static final int WM_QUERYUISTATE = 297;
    public static final int WM_RBUTTONDBLCLK = 518;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_SETCURSOR = 32;
    public static final int WM_SETFOCUS = 7;
    public static final int WM_SETFONT = 48;
    public static final int WM_SETICON = 128;
    public static final int WM_SETREDRAW = 11;
    public static final int WM_SETTEXT = 12;
    public static final int WM_SETTINGCHANGE = 26;
    public static final int WM_SHOWWINDOW = 24;
    public static final int WM_SIZE = 5;
    public static final int WM_SYSCHAR = 262;
    public static final int WM_SYSCOLORCHANGE = 21;
    public static final int WM_SYSCOMMAND = 274;
    public static final int WM_SYSKEYDOWN = 260;
    public static final int WM_SYSKEYUP = 261;
    public static final int WM_TABLET_FLICK = 715;
    public static final int WM_TIMER = 275;
    public static final int WM_THEMECHANGED = 794;
    public static final int WM_TOUCH = 576;
    public static final int WM_UNDO = 772;
    public static final int WM_UNINITMENUPOPUP = 293;
    public static final int WM_UPDATEUISTATE = 296;
    public static final int WM_USER = 1024;
    public static final int WM_VSCROLL = 277;
    public static final int WM_WINDOWPOSCHANGED = 71;
    public static final int WM_WINDOWPOSCHANGING = 70;
    public static final int WPF_RESTORETOMAXIMIZED = 2;
    public static final int WS_BORDER = 8388608;
    public static final int WS_CAPTION = 12582912;
    public static final int WS_CHILD = 1073741824;
    public static final int WS_CLIPCHILDREN = 33554432;
    public static final int WS_CLIPSIBLINGS = 67108864;
    public static final int WS_DISABLED = 67108864;
    public static final int WS_EX_APPWINDOW = 262144;
    public static final int WS_EX_CAPTIONOKBTN = Integer.MIN_VALUE;
    public static final int WS_EX_CLIENTEDGE = 512;
    public static final int WS_EX_COMPOSITED = 33554432;
    public static final int WS_EX_DLGMODALFRAME = 1;
    public static final int WS_EX_LAYERED = 524288;
    public static final int WS_EX_LAYOUTRTL = 4194304;
    public static final int WS_EX_LEFTSCROLLBAR = 16384;
    public static final int WS_EX_MDICHILD = 64;
    public static final int WS_EX_NOINHERITLAYOUT = 1048576;
    public static final int WS_EX_NOACTIVATE = 134217728;
    public static final int WS_EX_RIGHT = 4096;
    public static final int WS_EX_RTLREADING = 8192;
    public static final int WS_EX_STATICEDGE = 131072;
    public static final int WS_EX_TOOLWINDOW = 128;
    public static final int WS_EX_TOPMOST = 8;
    public static final int WS_EX_TRANSPARENT = 32;
    public static final int WS_HSCROLL = 1048576;
    public static final int WS_MAXIMIZEBOX = 65536;
    public static final int WS_MINIMIZEBOX = 131072;
    public static final int WS_OVERLAPPED = 0;
    public static final int WS_OVERLAPPEDWINDOW = 13565952;
    public static final int WS_POPUP = Integer.MIN_VALUE;
    public static final int WS_SYSMENU = 524288;
    public static final int WS_TABSTOP = 65536;
    public static final int WS_THICKFRAME = 262144;
    public static final int WS_VISIBLE = 268435456;
    public static final int WS_VSCROLL = 2097152;
    public static final int WM_XBUTTONDOWN = 523;
    public static final int WM_XBUTTONUP = 524;
    public static final int WM_XBUTTONDBLCLK = 525;
    public static final int XBUTTON1 = 1;
    public static final int XBUTTON2 = 2;
    public static final int PROCESS_DUP_HANDLE = 64;
    public static final int PROCESS_VM_READ = 16;
    public static final int DUPLICATE_SAME_ACCESS = 2;
    
    public static int VERSION(final int major, final int minor) {
        return major << 16 | minor;
    }
    
    public static final native int ACCEL_sizeof();
    
    public static final native int ACTCTX_sizeof();
    
    public static final native int BITMAP_sizeof();
    
    public static final native int BITMAPINFOHEADER_sizeof();
    
    public static final native int BLENDFUNCTION_sizeof();
    
    public static final native int BP_PAINTPARAMS_sizeof();
    
    public static final native int BUTTON_IMAGELIST_sizeof();
    
    public static final native int CANDIDATEFORM_sizeof();
    
    public static final native int CHOOSECOLOR_sizeof();
    
    public static final native int CHOOSEFONT_sizeof();
    
    public static final native int COMBOBOXINFO_sizeof();
    
    public static final native int COMPOSITIONFORM_sizeof();
    
    public static final native int CREATESTRUCT_sizeof();
    
    public static final native int DEVMODE_sizeof();
    
    public static final native int DIBSECTION_sizeof();
    
    public static final native int DOCHOSTUIINFO_sizeof();
    
    public static final native int DOCINFO_sizeof();
    
    public static final native int DRAWITEMSTRUCT_sizeof();
    
    public static final native int DROPFILES_sizeof();
    
    public static final native int EMR_sizeof();
    
    public static final native int EMREXTCREATEFONTINDIRECTW_sizeof();
    
    public static final native int EXTLOGFONTW_sizeof();
    
    public static final native int FLICK_DATA_sizeof();
    
    public static final native int FLICK_POINT_sizeof();
    
    public static final native int GCP_RESULTS_sizeof();
    
    public static final native int GESTURECONFIG_sizeof();
    
    public static final native int GESTUREINFO_sizeof();
    
    public static final native int GRADIENT_RECT_sizeof();
    
    public static final native int GUITHREADINFO_sizeof();
    
    public static final native int HDITEM_sizeof();
    
    public static final native int HDLAYOUT_sizeof();
    
    public static final native int HDHITTESTINFO_sizeof();
    
    public static final native int HELPINFO_sizeof();
    
    public static final native int HIGHCONTRAST_sizeof();
    
    public static final native int ICONINFO_sizeof();
    
    public static final native int CIDA_sizeof();
    
    public static final native int INITCOMMONCONTROLSEX_sizeof();
    
    public static final native int INPUT_sizeof();
    
    public static final native int KEYBDINPUT_sizeof();
    
    public static final native int LITEM_sizeof();
    
    public static final native int LOGBRUSH_sizeof();
    
    public static final native int LOGFONT_sizeof();
    
    public static final native int LOGPEN_sizeof();
    
    public static final native int LVCOLUMN_sizeof();
    
    public static final native int LVHITTESTINFO_sizeof();
    
    public static final native int LVITEM_sizeof();
    
    public static final native int LVINSERTMARK_sizeof();
    
    public static final native int MARGINS_sizeof();
    
    public static final native int MCHITTESTINFO_sizeof();
    
    public static final native int MEASUREITEMSTRUCT_sizeof();
    
    public static final native int MENUBARINFO_sizeof();
    
    public static final native int MENUINFO_sizeof();
    
    public static final native int MENUITEMINFO_sizeof();
    
    public static final native int MINMAXINFO_sizeof();
    
    public static final native int MOUSEINPUT_sizeof();
    
    public static final native int MONITORINFO_sizeof();
    
    public static final native int MSG_sizeof();
    
    public static final native int NMCUSTOMDRAW_sizeof();
    
    public static final native int NMHDR_sizeof();
    
    public static final native int NMHEADER_sizeof();
    
    public static final native int NMLINK_sizeof();
    
    public static final native int NMLISTVIEW_sizeof();
    
    public static final native int NMLVCUSTOMDRAW_sizeof();
    
    public static final native int NMLVDISPINFO_sizeof();
    
    public static final native int NMLVFINDITEM_sizeof();
    
    public static final native int NMLVODSTATECHANGE_sizeof();
    
    public static final native int NMREBARCHEVRON_sizeof();
    
    public static final native int NMREBARCHILDSIZE_sizeof();
    
    public static final native int NMTBHOTITEM_sizeof();
    
    public static final native int NMTREEVIEW_sizeof();
    
    public static final native int NMTOOLBAR_sizeof();
    
    public static final native int NMTTDISPINFO_sizeof();
    
    public static final native int NMTTCUSTOMDRAW_sizeof();
    
    public static final native int NMTBCUSTOMDRAW_sizeof();
    
    public static final native int NMTVCUSTOMDRAW_sizeof();
    
    public static final native int NMTVDISPINFO_sizeof();
    
    public static final native int NMTVITEMCHANGE_sizeof();
    
    public static final native int NMUPDOWN_sizeof();
    
    public static final native int NONCLIENTMETRICS_sizeof();
    
    public static final native int NOTIFYICONDATA_V2_SIZE();
    
    public static final native int OUTLINETEXTMETRIC_sizeof();
    
    public static final native int OSVERSIONINFOEX_sizeof();
    
    public static final native int PAINTSTRUCT_sizeof();
    
    public static final native int POINT_sizeof();
    
    public static final native int PRINTDLG_sizeof();
    
    public static final native int PROCESS_INFORMATION_sizeof();
    
    public static final native int PROPVARIANT_sizeof();
    
    public static final native int PROPERTYKEY_sizeof();
    
    public static final native int REBARBANDINFO_sizeof();
    
    public static final native int RECT_sizeof();
    
    public static final native int SAFEARRAY_sizeof();
    
    public static final native int SAFEARRAYBOUND_sizeof();
    
    public static final native int SCRIPT_ANALYSIS_sizeof();
    
    public static final native int SCRIPT_CONTROL_sizeof();
    
    public static final native int SCRIPT_FONTPROPERTIES_sizeof();
    
    public static final native int SCRIPT_ITEM_sizeof();
    
    public static final native int SCRIPT_LOGATTR_sizeof();
    
    public static final native int SCRIPT_PROPERTIES_sizeof();
    
    public static final native int SCRIPT_STATE_sizeof();
    
    public static final native int SCRIPT_STRING_ANALYSIS_sizeof();
    
    public static final native int SCROLLBARINFO_sizeof();
    
    public static final native int SCROLLINFO_sizeof();
    
    public static final native int SHDRAGIMAGE_sizeof();
    
    public static final native int SHELLEXECUTEINFO_sizeof();
    
    public static final native int SHFILEINFO_sizeof();
    
    public static final native int SIZE_sizeof();
    
    public static final native int STARTUPINFO_sizeof();
    
    public static final native int SYSTEMTIME_sizeof();
    
    public static final native int TBBUTTON_sizeof();
    
    public static final native int TBBUTTONINFO_sizeof();
    
    public static final native int TCITEM_sizeof();
    
    public static final native int TCHITTESTINFO_sizeof();
    
    public static final native int TEXTMETRIC_sizeof();
    
    public static final native int TF_DA_COLOR_sizeof();
    
    public static final native int TF_DISPLAYATTRIBUTE_sizeof();
    
    public static final native int TOOLINFO_sizeof();
    
    public static final native int TOUCHINPUT_sizeof();
    
    public static final native int TRACKMOUSEEVENT_sizeof();
    
    public static final native int TRIVERTEX_sizeof();
    
    public static final native int TVHITTESTINFO_sizeof();
    
    public static final native int TVINSERTSTRUCT_sizeof();
    
    public static final native int TVITEM_sizeof();
    
    public static final native int TVSORTCB_sizeof();
    
    public static final native int UDACCEL_sizeof();
    
    public static final native int WINDOWPLACEMENT_sizeof();
    
    public static final native int WINDOWPOS_sizeof();
    
    public static final native int WNDCLASS_sizeof();
    
    public static final long AddFontResourceEx(final TCHAR lpszFilename, final int fl, final long pdv) {
        final char[] lpszFilename2 = (char[])((lpszFilename == null) ? null : lpszFilename.chars);
        return AddFontResourceEx(lpszFilename2, fl, pdv);
    }
    
    public static final int AssocQueryString(final int flags, final int str, final TCHAR pszAssoc, final TCHAR pszExtra, final TCHAR pszOut, final int[] pcchOut) {
        final char[] pszAssoc2 = (char[])((pszAssoc == null) ? null : pszAssoc.chars);
        final char[] pszExtra2 = (char[])((pszExtra == null) ? null : pszExtra.chars);
        final char[] pszOut2 = (char[])((pszOut == null) ? null : pszOut.chars);
        return AssocQueryString(flags, str, pszAssoc2, pszExtra2, pszOut2, pcchOut);
    }
    
    public static final long CreateDC(final TCHAR lpszDriver, final TCHAR lpszDevice, final long lpszOutput, final long lpInitData) {
        final char[] lpszDriver2 = (char[])((lpszDriver == null) ? null : lpszDriver.chars);
        final char[] lpszDevice2 = (char[])((lpszDevice == null) ? null : lpszDevice.chars);
        return CreateDC(lpszDriver2, lpszDevice2, lpszOutput, lpInitData);
    }
    
    public static final long CreateWindowEx(final int dwExStyle, final TCHAR lpClassName, final TCHAR lpWindowName, final int dwStyle, final int X, final int Y, final int nWidth, final int nHeight, final long hWndParent, final long hMenu, final long hInstance, final CREATESTRUCT lpParam) {
        final char[] lpClassName2 = (char[])((lpClassName == null) ? null : lpClassName.chars);
        final char[] lpWindowName2 = (char[])((lpWindowName == null) ? null : lpWindowName.chars);
        return CreateWindowEx(dwExStyle, lpClassName2, lpWindowName2, dwStyle, X, Y, nWidth, nHeight, hWndParent, hMenu, hInstance, lpParam);
    }
    
    public static final int DocumentProperties(final long hWnd, final long hPrinter, final TCHAR pDeviceName, final long pDevModeOutput, final long pDevModeInput, final int fMode) {
        final char[] pDeviceName2 = (char[])((pDeviceName == null) ? null : pDeviceName.chars);
        return DocumentProperties(hWnd, hPrinter, pDeviceName2, pDevModeOutput, pDevModeInput, fMode);
    }
    
    public static final int DrawText(final long hDC, final TCHAR lpString, final int nCount, final RECT lpRect, final int uFormat) {
        final char[] lpString2 = (char[])((lpString == null) ? null : lpString.chars);
        return DrawText(hDC, lpString2, nCount, lpRect, uFormat);
    }
    
    public static final int ExpandEnvironmentStrings(final TCHAR lpSrc, final TCHAR lpDst, final int nSize) {
        final char[] lpSrc2 = (char[])((lpSrc == null) ? null : lpSrc.chars);
        final char[] lpDst2 = (char[])((lpDst == null) ? null : lpDst.chars);
        return ExpandEnvironmentStrings(lpSrc2, lpDst2, nSize);
    }
    
    public static final int ExtractIconEx(final TCHAR lpszFile, final int nIconIndex, final long[] phiconLarge, final long[] phiconSmall, final int nIcons) {
        final char[] lpszFile2 = (char[])((lpszFile == null) ? null : lpszFile.chars);
        return ExtractIconEx(lpszFile2, nIconIndex, phiconLarge, phiconSmall, nIcons);
    }
    
    public static final boolean GetClassInfo(final long hInstance, final TCHAR lpClassName, final WNDCLASS lpWndClass) {
        final char[] lpClassName2 = (char[])((lpClassName == null) ? null : lpClassName.chars);
        final boolean result = GetClassInfo(hInstance, lpClassName2, lpWndClass);
        lpWndClass.lpszClassName = 0L;
        return result;
    }
    
    public static final int GetLocaleInfo(final int Locale, final int LCType, final TCHAR lpLCData, final int cchData) {
        final char[] lpLCData2 = (char[])((lpLCData == null) ? null : lpLCData.chars);
        return GetLocaleInfo(Locale, LCType, lpLCData2, cchData);
    }
    
    public static final int GetModuleFileName(final long hModule, final TCHAR lpFilename, final int inSize) {
        final char[] lpFilename2 = (char[])((lpFilename == null) ? null : lpFilename.chars);
        return GetModuleFileName(hModule, lpFilename2, inSize);
    }
    
    public static final int GetProfileString(final TCHAR lpAppName, final TCHAR lpKeyName, final TCHAR lpDefault, final TCHAR lpReturnedString, final int nSize) {
        final char[] lpAppName2 = (char[])((lpAppName == null) ? null : lpAppName.chars);
        final char[] lpKeyName2 = (char[])((lpKeyName == null) ? null : lpKeyName.chars);
        final char[] lpDefault2 = (char[])((lpDefault == null) ? null : lpDefault.chars);
        final char[] lpReturnedString2 = (char[])((lpReturnedString == null) ? null : lpReturnedString.chars);
        return GetProfileString(lpAppName2, lpKeyName2, lpDefault2, lpReturnedString2, nSize);
    }
    
    public static final int GetWindowText(final long hWnd, final TCHAR lpString, final int nMaxCount) {
        final char[] lpString2 = (char[])((lpString == null) ? null : lpString.chars);
        return GetWindowText(hWnd, lpString2, nMaxCount);
    }
    
    public static final int GlobalAddAtom(final TCHAR lpString) {
        final char[] lpString2 = (char[])((lpString == null) ? null : lpString.chars);
        return GlobalAddAtom(lpString2);
    }
    
    public static final long ImmEscape(final long hKL, final long hIMC, final int uEscape, final TCHAR lpData) {
        final char[] lpData2 = (char[])((lpData == null) ? null : lpData.chars);
        return ImmEscape(hKL, hIMC, uEscape, lpData2);
    }
    
    public static final boolean InternetGetCookie(final TCHAR lpszUrl, final TCHAR lpszCookieName, final TCHAR lpszCookieData, final int[] lpdwSize) {
        final char[] url = (char[])((lpszUrl == null) ? null : lpszUrl.chars);
        final char[] cookieName = (char[])((lpszCookieName == null) ? null : lpszCookieName.chars);
        final char[] cookieData = (char[])((lpszCookieData == null) ? null : lpszCookieData.chars);
        return InternetGetCookie(url, cookieName, cookieData, lpdwSize);
    }
    
    public static final boolean InternetSetCookie(final TCHAR lpszUrl, final TCHAR lpszCookieName, final TCHAR lpszCookieData) {
        final char[] url = (char[])((lpszUrl == null) ? null : lpszUrl.chars);
        final char[] cookieName = (char[])((lpszCookieName == null) ? null : lpszCookieName.chars);
        final char[] cookieData = (char[])((lpszCookieData == null) ? null : lpszCookieData.chars);
        return InternetSetCookie(url, cookieName, cookieData);
    }
    
    public static final int MessageBox(final long hWnd, final TCHAR lpText, final TCHAR lpCaption, final int uType) {
        final char[] lpText2 = (char[])((lpText == null) ? null : lpText.chars);
        final char[] lpCaption2 = (char[])((lpCaption == null) ? null : lpCaption.chars);
        return MessageBox(hWnd, lpText2, lpCaption2, uType);
    }
    
    public static final void MoveMemory(final long Destination, final TCHAR Source, final int Length) {
        final char[] Source2 = (char[])((Source == null) ? null : Source.chars);
        MoveMemory(Destination, Source2, Length);
    }
    
    public static final void MoveMemory(final TCHAR Destination, final long Source, final int Length) {
        final char[] Destination2 = (char[])((Destination == null) ? null : Destination.chars);
        MoveMemory(Destination2, Source, Length);
    }
    
    public static final boolean OpenPrinter(final TCHAR pPrinterName, final long[] phPrinter, final long pDefault) {
        final char[] pPrinterName2 = (char[])((pPrinterName == null) ? null : pPrinterName.chars);
        return OpenPrinter(pPrinterName2, phPrinter, pDefault);
    }
    
    public static final int[] readRegistryDwords(final int hkeyLocation, final String key, final String valueName) {
        final int ERROR_MORE_DATA = 234;
        Objects.requireNonNull("key", key);
        Objects.requireNonNull("valueName", valueName);
        final long[] phkResult = { 0L };
        final TCHAR regKey = new TCHAR(0, key, true);
        final TCHAR lpValueName = new TCHAR(0, valueName, true);
        if (RegOpenKeyEx(hkeyLocation, regKey, 0, 131097, phkResult) != 0) {
            return null;
        }
        int size = 2;
        int result;
        do {
            final int[] lpcbData = { 4 * size };
            final int[] lpData = new int[size];
            result = RegQueryValueEx(phkResult[0], lpValueName, 0L, null, lpData, lpcbData);
            RegCloseKey(phkResult[0]);
            if (result == 0) {
                return lpData;
            }
            size *= 2;
        } while (result == 234);
        return null;
    }
    
    public static final int RegCreateKeyEx(final long hKey, final TCHAR lpSubKey, final int Reserved, final TCHAR lpClass, final int dwOptions, final int samDesired, final long lpSecurityAttributes, final long[] phkResult, final long[] lpdwDisposition) {
        final char[] lpClass2 = (char[])((lpClass == null) ? null : lpClass.chars);
        final char[] lpSubKey2 = (char[])((lpSubKey == null) ? null : lpSubKey.chars);
        return RegCreateKeyEx(hKey, lpSubKey2, Reserved, lpClass2, dwOptions, samDesired, lpSecurityAttributes, phkResult, lpdwDisposition);
    }
    
    public static final int RegDeleteValue(final long hKey, final TCHAR lpValueName) {
        final char[] lpValueName2 = (char[])((lpValueName == null) ? null : lpValueName.chars);
        return RegDeleteValue(hKey, lpValueName2);
    }
    
    public static final int RegEnumKeyEx(final long hKey, final int dwIndex, final TCHAR lpName, final int[] lpcName, final int[] lpReserved, final TCHAR lpClass, final int[] lpcClass, final long lpftLastWriteTime) {
        final char[] lpName2 = (char[])((lpName == null) ? null : lpName.chars);
        final char[] lpClass2 = (char[])((lpClass == null) ? null : lpClass.chars);
        return RegEnumKeyEx(hKey, dwIndex, lpName2, lpcName, lpReserved, lpClass2, lpcClass, lpftLastWriteTime);
    }
    
    public static final int RegisterClass(final TCHAR lpszClassName, final WNDCLASS lpWndClass) {
        final long hHeap = GetProcessHeap();
        final int byteCount = lpszClassName.length() * 2;
        MoveMemory(lpWndClass.lpszClassName = HeapAlloc(hHeap, 8, byteCount), lpszClassName, byteCount);
        final int result = RegisterClass(lpWndClass);
        HeapFree(hHeap, 0, lpWndClass.lpszClassName);
        lpWndClass.lpszClassName = 0L;
        return result;
    }
    
    public static final int RegisterClipboardFormat(final TCHAR lpszFormat) {
        final char[] lpszFormat2 = (char[])((lpszFormat == null) ? null : lpszFormat.chars);
        return RegisterClipboardFormat(lpszFormat2);
    }
    
    public static final int RegisterWindowMessage(final TCHAR lpString) {
        final char[] lpString2 = (char[])((lpString == null) ? null : lpString.chars);
        return RegisterWindowMessage(lpString2);
    }
    
    public static final int RegOpenKeyEx(final long hKey, final TCHAR lpSubKey, final int ulOptions, final int samDesired, final long[] phkResult) {
        final char[] lpSubKey2 = (char[])((lpSubKey == null) ? null : lpSubKey.chars);
        return RegOpenKeyEx(hKey, lpSubKey2, ulOptions, samDesired, phkResult);
    }
    
    public static final int RegQueryValueEx(final long hKey, final TCHAR lpValueName, final long lpReserved, final int[] lpType, final TCHAR lpData, final int[] lpcbData) {
        final char[] lpValueName2 = (char[])((lpValueName == null) ? null : lpValueName.chars);
        final char[] lpData2 = (char[])((lpData == null) ? null : lpData.chars);
        return RegQueryValueEx(hKey, lpValueName2, lpReserved, lpType, lpData2, lpcbData);
    }
    
    public static final int RegQueryValueEx(final long hKey, final TCHAR lpValueName, final long lpReserved, final int[] lpType, final int[] lpData, final int[] lpcbData) {
        final char[] lpValueName2 = (char[])((lpValueName == null) ? null : lpValueName.chars);
        return RegQueryValueEx(hKey, lpValueName2, lpReserved, lpType, lpData, lpcbData);
    }
    
    public static final int RegSetValueEx(final long hKey, final TCHAR lpValueName, final int Reserved, final int dwType, final int[] lpData, final int cbData) {
        final char[] lpValueName2 = (char[])((lpValueName == null) ? null : lpValueName.chars);
        return RegSetValueEx(hKey, lpValueName2, Reserved, dwType, lpData, cbData);
    }
    
    public static final long SendMessage(final long hWnd, final int Msg, final long wParam, final TCHAR lParam) {
        final char[] lParam2 = (char[])((lParam == null) ? null : lParam.chars);
        return SendMessage(hWnd, Msg, wParam, lParam2);
    }
    
    public static final void setTheme(final boolean isDarkTheme) {
        final Display display = Display.getCurrent();
        if (display == null) {
            throw new NullPointerException("Display must be already created before you call OS.setTheme()");
        }
        display.setData("org.eclipse.swt.internal.win32.useDarkModeExplorerTheme", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.useShellTitleColoring", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.menuBarForegroundColor", isDarkTheme ? new Color((Device)display, 208, 208, 208) : null);
        display.setData("org.eclipse.swt.internal.win32.menuBarBackgroundColor", isDarkTheme ? new Color((Device)display, 48, 48, 48) : null);
        display.setData("org.eclipse.swt.internal.win32.menuBarBorderColor", isDarkTheme ? new Color((Device)display, 80, 80, 80) : null);
        display.setData("org.eclipse.swt.internal.win32.Canvas.use_WS_BORDER", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.List.use_WS_BORDER", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.Table.use_WS_BORDER", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.Text.use_WS_BORDER", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.Tree.use_WS_BORDER", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.Table.headerLineColor", isDarkTheme ? new Color((Device)display, 80, 80, 80) : null);
        display.setData("org.eclipse.swt.internal.win32.Label.disabledForegroundColor", isDarkTheme ? new Color((Device)display, 128, 128, 128) : null);
        display.setData("org.eclipse.swt.internal.win32.Combo.useDarkTheme", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.ProgressBar.useColors", isDarkTheme);
        display.setData("org.eclipse.swt.internal.win32.Text.useDarkThemeIcons", isDarkTheme);
    }
    
    public static final boolean SetWindowText(final long hWnd, final TCHAR lpString) {
        final char[] lpString2 = (char[])((lpString == null) ? null : lpString.chars);
        return SetWindowText(hWnd, lpString2);
    }
    
    public static final boolean UnregisterClass(final TCHAR lpClassName, final long hInstance) {
        final char[] lpClassName2 = (char[])((lpClassName == null) ? null : lpClassName.chars);
        return UnregisterClass(lpClassName2, hInstance);
    }
    
    public static final int UrlCreateFromPath(final TCHAR pszPath, final TCHAR pszURL, final int[] pcchUrl, final int flags) {
        final char[] path = (char[])((pszPath == null) ? null : pszPath.chars);
        final char[] url = (char[])((pszURL == null) ? null : pszURL.chars);
        return UrlCreateFromPath(path, url, pcchUrl, flags);
    }
    
    public static final int GET_WHEEL_DELTA_WPARAM(final long wParam) {
        return (short)HIWORD(wParam);
    }
    
    public static final int GET_X_LPARAM(final long lp) {
        return (short)LOWORD(lp);
    }
    
    public static final int GET_Y_LPARAM(final long lp) {
        return (short)HIWORD(lp);
    }
    
    public static final int HIWORD(final long l) {
        return (int)l >>> 16;
    }
    
    public static final int LOWORD(final long l) {
        return (int)l & 0xFFFF;
    }
    
    public static final int MAKEWORD(final int l, final int h) {
        return (l & 0xFF) | (h & 0xFF) << 8;
    }
    
    public static final long MAKELPARAM(final int l, final int h) {
        return (long)((l & 0xFFFF) | h << 16) & 0xFFFFFFFFL;
    }
    
    public static final long MAKELRESULT(final int l, final int h) {
        return MAKELPARAM(l, h);
    }
    
    public static final long MAKEWPARAM(final int l, final int h) {
        return MAKELPARAM(l, h);
    }
    
    public static final void POINTSTOPOINT(final POINT pt, final long pts) {
        pt.x = (short)LOWORD(pts);
        pt.y = (short)HIWORD(pts);
    }
    
    public static final int PRIMARYLANGID(final int lgid) {
        return lgid & 0x3FF;
    }
    
    public static final int TOUCH_COORD_TO_PIXEL(final int touchCoord) {
        return touchCoord / 100;
    }
    
    public static int HRESULT_FROM_WIN32(final int x) {
        return (x <= 0) ? x : ((x & 0xFFFF) | 0x80070000);
    }
    
    public static final native int AbortDoc(final long p0);
    
    public static final native boolean ActivateActCtx(final long p0, final long[] p1);
    
    public static final native long ActivateKeyboardLayout(final long p0, final int p1);
    
    public static final native int AddFontResourceEx(final char[] p0, final int p1, final long p2);
    
    public static final native boolean AdjustWindowRectEx(final RECT p0, final int p1, final boolean p2, final int p3);
    
    public static final native boolean AllowDarkModeForWindow(final long p0, final boolean p1);
    
    public static final native boolean AllowSetForegroundWindow(final int p0);
    
    public static final native boolean AlphaBlend(final long p0, final int p1, final int p2, final int p3, final int p4, final long p5, final int p6, final int p7, final int p8, final int p9, final BLENDFUNCTION p10);
    
    public static final native boolean Arc(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    public static final native int AssocQueryString(final int p0, final int p1, final char[] p2, final char[] p3, final char[] p4, final int[] p5);
    
    public static final native long BeginBufferedPaint(final long p0, final RECT p1, final int p2, final BP_PAINTPARAMS p3, final long[] p4);
    
    public static final native long BeginDeferWindowPos(final int p0);
    
    public static final native long BeginPaint(final long p0, final PAINTSTRUCT p1);
    
    public static final native boolean BitBlt(final long p0, final int p1, final int p2, final int p3, final int p4, final long p5, final int p6, final int p7, final int p8);
    
    public static final native boolean BringWindowToTop(final long p0);
    
    public static final native int BufferedPaintInit();
    
    public static final native int BufferedPaintUnInit();
    
    public static final native long CallNextHookEx(final long p0, final int p1, final long p2, final long p3);
    
    public static final native long CallWindowProc(final long p0, final long p1, final int p2, final long p3, final long p4);
    
    public static final native long CharLower(final long p0);
    
    public static final native long CharUpper(final long p0);
    
    public static final native long ChildWindowFromPointEx(final long p0, final POINT p1, final int p2);
    
    public static final native boolean ChooseColor(final CHOOSECOLOR p0);
    
    public static final native boolean ChooseFont(final CHOOSEFONT p0);
    
    public static final native boolean ClientToScreen(final long p0, final POINT p1);
    
    public static final native boolean CloseClipboard();
    
    public static final native long CloseEnhMetaFile(final long p0);
    
    public static final native long CloseGestureInfoHandle(final long p0);
    
    public static final native boolean CloseHandle(final long p0);
    
    public static final native boolean ClosePrinter(final long p0);
    
    public static final native int CloseThemeData(final long p0);
    
    public static final native boolean CloseTouchInputHandle(final long p0);
    
    public static final native int CoInternetIsFeatureEnabled(final int p0, final int p1);
    
    public static final native int CoInternetSetFeatureEnabled(final int p0, final int p1, final boolean p2);
    
    public static final native int CombineRgn(final long p0, final long p1, final long p2, final int p3);
    
    public static final native long CopyImage(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native long CoTaskMemAlloc(final int p0);
    
    public static final native void CoTaskMemFree(final long p0);
    
    public static final native long CreateAcceleratorTable(final byte[] p0, final int p1);
    
    public static final native long CreateActCtx(final ACTCTX p0);
    
    public static final native long CreateBitmap(final int p0, final int p1, final int p2, final int p3, final byte[] p4);
    
    public static final native boolean CreateCaret(final long p0, final long p1, final int p2, final int p3);
    
    public static final native long CreateCompatibleBitmap(final long p0, final int p1, final int p2);
    
    public static final native long CreateCompatibleDC(final long p0);
    
    public static final native long CreateCursor(final long p0, final int p1, final int p2, final int p3, final int p4, final byte[] p5, final byte[] p6);
    
    public static final native long CreateDC(final char[] p0, final char[] p1, final long p2, final long p3);
    
    public static final native long CreateDIBSection(final long p0, final byte[] p1, final int p2, final long[] p3, final long p4, final int p5);
    
    public static final native long CreateDIBSection(final long p0, final long p1, final int p2, final long[] p3, final long p4, final int p5);
    
    public static final native long CreateEnhMetaFile(final long p0, final char[] p1, final RECT p2, final char[] p3);
    
    public static final native long CreateFontIndirect(final long p0);
    
    public static final native long CreateFontIndirect(final LOGFONT p0);
    
    public static final native long CreateIconIndirect(final ICONINFO p0);
    
    public static final native long CreateMenu();
    
    public static final native long CreatePatternBrush(final long p0);
    
    public static final native long CreatePen(final int p0, final int p1, final int p2);
    
    public static final native long CreatePolygonRgn(final int[] p0, final int p1, final int p2);
    
    public static final native long CreatePopupMenu();
    
    public static final native boolean CreateProcess(final long p0, final long p1, final long p2, final long p3, final boolean p4, final int p5, final long p6, final long p7, final STARTUPINFO p8, final PROCESS_INFORMATION p9);
    
    public static final native long CreateRectRgn(final int p0, final int p1, final int p2, final int p3);
    
    public static final native long CreateSolidBrush(final int p0);
    
    public static final native int CreateStreamOnHGlobal(final long p0, final boolean p1, final long[] p2);
    
    public static final native long CreateWindowEx(final int p0, final char[] p1, final char[] p2, final int p3, final int p4, final int p5, final int p6, final int p7, final long p8, final long p9, final long p10, final CREATESTRUCT p11);
    
    public static final native long DeferWindowPos(final long p0, final long p1, final long p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    public static final native long DefMDIChildProc(final long p0, final int p1, final long p2, final long p3);
    
    public static final native long DefFrameProc(final long p0, final long p1, final int p2, final long p3, final long p4);
    
    public static final native long DefWindowProc(final long p0, final int p1, final long p2, final long p3);
    
    public static final native boolean DeleteDC(final long p0);
    
    public static final native boolean DeleteEnhMetaFile(final long p0);
    
    public static final native boolean DeleteMenu(final long p0, final int p1, final int p2);
    
    public static final native boolean DeleteObject(final long p0);
    
    public static final native boolean DestroyAcceleratorTable(final long p0);
    
    public static final native boolean DestroyCaret();
    
    public static final native boolean DestroyCursor(final long p0);
    
    public static final native boolean DestroyIcon(final long p0);
    
    public static final native boolean DestroyMenu(final long p0);
    
    public static final native boolean DestroyWindow(final long p0);
    
    public static final native long DispatchMessage(final MSG p0);
    
    public static final native int DocumentProperties(final long p0, final long p1, final char[] p2, final long p3, final long p4, final int p5);
    
    public static final native boolean DragDetect(final long p0, final POINT p1);
    
    public static final native void DragFinish(final long p0);
    
    public static final native int DragQueryFile(final long p0, final int p1, final char[] p2, final int p3);
    
    public static final native boolean DrawEdge(final long p0, final RECT p1, final int p2, final int p3);
    
    public static final native boolean DrawFocusRect(final long p0, final RECT p1);
    
    public static final native boolean DrawFrameControl(final long p0, final RECT p1, final int p2, final int p3);
    
    public static final native boolean DrawIconEx(final long p0, final int p1, final int p2, final long p3, final int p4, final int p5, final int p6, final long p7, final int p8);
    
    public static final native boolean DrawMenuBar(final long p0);
    
    public static final native int DrawText(final long p0, final char[] p1, final int p2, final RECT p3, final int p4);
    
    public static final native int DrawThemeBackground(final long p0, final long p1, final int p2, final int p3, final RECT p4, final RECT p5);
    
    public static final native int DrawThemeText(final long p0, final long p1, final int p2, final int p3, final char[] p4, final int p5, final int p6, final int p7, final RECT p8);
    
    public static final native boolean DwmSetWindowAttribute(final long p0, final int p1, final int[] p2, final int p3);
    
    public static final native boolean Ellipse(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean EnableMenuItem(final long p0, final int p1, final int p2);
    
    public static final native boolean EnableScrollBar(final long p0, final int p1, final int p2);
    
    public static final native boolean EnableWindow(final long p0, final boolean p1);
    
    public static final native boolean EnumSystemLanguageGroups(final long p0, final int p1, final long p2);
    
    public static final native boolean EnumSystemLocales(final long p0, final int p1);
    
    public static final native boolean EndDeferWindowPos(final long p0);
    
    public static final native int EndBufferedPaint(final long p0, final boolean p1);
    
    public static final native int EndDoc(final long p0);
    
    public static final native int EndPage(final long p0);
    
    public static final native int EndPaint(final long p0, final PAINTSTRUCT p1);
    
    public static final native boolean EnumDisplayMonitors(final long p0, final RECT p1, final long p2, final int p3);
    
    public static final native boolean EnumEnhMetaFile(final long p0, final long p1, final long p2, final long p3, final RECT p4);
    
    public static final native int EnumFontFamilies(final long p0, final char[] p1, final long p2, final long p3);
    
    public static final native boolean EqualRect(final RECT p0, final RECT p1);
    
    public static final native int ExcludeClipRect(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int ExpandEnvironmentStrings(final char[] p0, final char[] p1, final int p2);
    
    public static final native long ExtCreatePen(final int p0, final int p1, final LOGBRUSH p2, final int p3, final int[] p4);
    
    public static final native long ExtCreateRegion(final float[] p0, final int p1, final int[] p2);
    
    public static final native boolean ExtTextOut(final long p0, final int p1, final int p2, final int p3, final RECT p4, final char[] p5, final int p6, final int[] p7);
    
    public static final native int ExtractIconEx(final char[] p0, final int p1, final long[] p2, final long[] p3, final int p4);
    
    public static final native int FillRect(final long p0, final RECT p1, final long p2);
    
    public static final native int GdiSetBatchLimit(final int p0);
    
    public static final native int GetACP();
    
    public static final native long GetActiveWindow();
    
    public static final native int GetBkColor(final long p0);
    
    public static final native long GetCapture();
    
    public static final native boolean GetCaretPos(final POINT p0);
    
    public static final native boolean GetCharABCWidths(final long p0, final int p1, final int p2, final int[] p3);
    
    public static final native int GetCharacterPlacement(final long p0, final char[] p1, final int p2, final int p3, final GCP_RESULTS p4, final int p5);
    
    public static final native boolean GetCharWidth(final long p0, final int p1, final int p2, final int[] p3);
    
    public static final native boolean GetClassInfo(final long p0, final char[] p1, final WNDCLASS p2);
    
    public static final native int GetClassName(final long p0, final char[] p1, final int p2);
    
    public static final native boolean GetClientRect(final long p0, final RECT p1);
    
    public static final native long GetClipboardData(final int p0);
    
    public static final native int GetClipboardFormatName(final int p0, final char[] p1, final int p2);
    
    public static final native int GetClipBox(final long p0, final RECT p1);
    
    public static final native int GetClipRgn(final long p0, final long p1);
    
    public static final native boolean GetComboBoxInfo(final long p0, final COMBOBOXINFO p1);
    
    public static final native long GetCurrentObject(final long p0, final int p1);
    
    public static final native int GetCurrentProcessId();
    
    public static final native int GetCurrentThreadId();
    
    public static final native int GetCurrentProcessExplicitAppUserModelID(final long[] p0);
    
    public static final native long GetCursor();
    
    public static final native boolean GetCursorPos(final POINT p0);
    
    public static final native long GetDC(final long p0);
    
    public static final native long GetDCEx(final long p0, final long p1, final int p2);
    
    public static final native long GetDesktopWindow();
    
    public static final native int GetDeviceCaps(final long p0, final int p1);
    
    public static final native int GetDialogBaseUnits();
    
    public static final native int GetDIBColorTable(final long p0, final int p1, final int p2, final byte[] p3);
    
    public static final native int GetDIBits(final long p0, final long p1, final int p2, final int p3, final byte[] p4, final byte[] p5, final int p6);
    
    public static final native long GetDlgItem(final long p0, final int p1);
    
    public static final native int GetDoubleClickTime();
    
    public static final native int GetDpiForMonitor(final long p0, final int p1, final int[] p2, final int[] p3);
    
    public static final native long GetFocus();
    
    public static final native int GetFontLanguageInfo(final long p0);
    
    public static final native long GetForegroundWindow();
    
    public static final native boolean GetGestureInfo(final long p0, final GESTUREINFO p1);
    
    public static final native int GetGraphicsMode(final long p0);
    
    public static final native int GetGlyphIndices(final long p0, final char[] p1, final int p2, final short[] p3, final int p4);
    
    public static final native boolean GetGUIThreadInfo(final int p0, final GUITHREADINFO p1);
    
    public static final native boolean GetIconInfo(final long p0, final ICONINFO p1);
    
    public static final native int GetKeyboardLayoutList(final int p0, final long[] p1);
    
    public static final native long GetKeyboardLayout(final int p0);
    
    public static final native short GetKeyState(final int p0);
    
    public static final native boolean GetKeyboardState(final byte[] p0);
    
    public static final native long GetLastActivePopup(final long p0);
    
    public static final native int GetLastError();
    
    public static final native boolean GetLayeredWindowAttributes(final long p0, final int[] p1, final byte[] p2, final int[] p3);
    
    public static final native int GetLayout(final long p0);
    
    public static final native long GetLibraryHandle();
    
    public static final native int GetLocaleInfo(final int p0, final int p1, final char[] p2, final int p3);
    
    public static final native long GetMenu(final long p0);
    
    public static final native boolean GetMenuBarInfo(final long p0, final int p1, final int p2, final MENUBARINFO p3);
    
    public static final native int GetMenuDefaultItem(final long p0, final int p1, final int p2);
    
    public static final native boolean GetMenuInfo(final long p0, final MENUINFO p1);
    
    public static final native int GetMenuItemCount(final long p0);
    
    public static final native boolean GetMenuItemInfo(final long p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native boolean GetMenuItemRect(final long p0, final long p1, final int p2, final RECT p3);
    
    public static final native boolean GetMessage(final MSG p0, final long p1, final int p2, final int p3);
    
    public static final native int GetMessagePos();
    
    public static final native int GetMessageTime();
    
    public static final native int GetMetaRgn(final long p0, final long p1);
    
    public static final native int GetThemePartSize(final long p0, final long p1, final int p2, final int p3, final RECT p4, final int p5, final SIZE p6);
    
    public static final native int GetThemeTextExtent(final long p0, final long p1, final int p2, final int p3, final char[] p4, final int p5, final int p6, final RECT p7, final RECT p8);
    
    public static final native int GetModuleFileName(final long p0, final char[] p1, final int p2);
    
    public static final native long GetModuleHandle(final char[] p0);
    
    public static final native boolean GetMonitorInfo(final long p0, final MONITORINFO p1);
    
    public static final native int GetObject(final long p0, final int p1, final BITMAP p2);
    
    public static final native int GetObject(final long p0, final int p1, final DIBSECTION p2);
    
    public static final native int GetObject(final long p0, final int p1, final LOGBRUSH p2);
    
    public static final native int GetObject(final long p0, final int p1, final LOGFONT p2);
    
    public static final native int GetObject(final long p0, final int p1, final long p2);
    
    public static final native int GetOutlineTextMetrics(final long p0, final int p1, final OUTLINETEXTMETRIC p2);
    
    public static final native long GetParent(final long p0);
    
    public static final native int GetPixel(final long p0, final int p1, final int p2);
    
    public static final native int GetPolyFillMode(final long p0);
    
    public static final native boolean OpenPrinter(final char[] p0, final long[] p1, final long p2);
    
    public static final native long GetProcessHeap();
    
    public static final native int GetProfileString(final char[] p0, final char[] p1, final char[] p2, final char[] p3, final int p4);
    
    public static final native long GetProp(final long p0, final long p1);
    
    public static final native int GetRandomRgn(final long p0, final long p1, final int p2);
    
    public static final native int GetRegionData(final long p0, final int p1, final int[] p2);
    
    public static final native int GetRgnBox(final long p0, final RECT p1);
    
    public static final native int GetROP2(final long p0);
    
    public static final native boolean GetScrollBarInfo(final long p0, final int p1, final SCROLLBARINFO p2);
    
    public static final native boolean GetScrollInfo(final long p0, final int p1, final SCROLLINFO p2);
    
    public static final native void GetStartupInfo(final STARTUPINFO p0);
    
    public static final native long GetStockObject(final int p0);
    
    public static final native int GetSysColor(final int p0);
    
    public static final native long GetSysColorBrush(final int p0);
    
    public static final native long GetSystemMenu(final long p0, final boolean p1);
    
    public static final native int GetSystemMetrics(final int p0);
    
    public static final native int GetTextColor(final long p0);
    
    public static final native boolean GetTextExtentPoint32(final long p0, final char[] p1, final int p2, final SIZE p3);
    
    public static final native boolean GetTextMetrics(final long p0, final TEXTMETRIC p1);
    
    public static final native boolean GetTouchInputInfo(final long p0, final int p1, final long p2, final int p3);
    
    public static final native boolean GetUpdateRect(final long p0, final RECT p1, final boolean p2);
    
    public static final native int GetUpdateRgn(final long p0, final long p1, final boolean p2);
    
    public static final native int GetVersion();
    
    public static final native long GetWindow(final long p0, final int p1);
    
    public static final native int GetWindowLong(final long p0, final int p1);
    
    public static final native long GetWindowLongPtr(final long p0, final int p1);
    
    public static final native long GetWindowDC(final long p0);
    
    public static final native boolean GetWindowOrgEx(final long p0, final POINT p1);
    
    public static final native boolean GetWindowPlacement(final long p0, final WINDOWPLACEMENT p1);
    
    public static final native boolean GetWindowRect(final long p0, final RECT p1);
    
    public static final native int GetWindowRgn(final long p0, final long p1);
    
    public static final native int GetWindowText(final long p0, final char[] p1, final int p2);
    
    public static final native int GetWindowTextLength(final long p0);
    
    public static final native int GetWindowThreadProcessId(final long p0, final int[] p1);
    
    public static final native double GID_ROTATE_ANGLE_FROM_ARGUMENT(final long p0);
    
    public static final native int GlobalAddAtom(final char[] p0);
    
    public static final native long GlobalAlloc(final int p0, final int p1);
    
    public static final native long GlobalFree(final long p0);
    
    public static final native long GlobalLock(final long p0);
    
    public static final native int GlobalSize(final long p0);
    
    public static final native boolean GlobalUnlock(final long p0);
    
    public static final native boolean GradientFill(final long p0, final long p1, final int p2, final long p3, final int p4, final int p5);
    
    public static final native long HeapAlloc(final long p0, final int p1, final int p2);
    
    public static final native boolean HeapFree(final long p0, final int p1, final long p2);
    
    public static final native boolean HideCaret(final long p0);
    
    public static final native int IIDFromString(final char[] p0, final byte[] p1);
    
    public static final native int ILGetSize(final long p0);
    
    public static final native int ImageList_Add(final long p0, final long p1, final long p2);
    
    public static final native int ImageList_AddMasked(final long p0, final long p1, final int p2);
    
    public static final native boolean ImageList_BeginDrag(final long p0, final int p1, final int p2, final int p3);
    
    public static final native long ImageList_Create(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean ImageList_Destroy(final long p0);
    
    public static final native boolean ImageList_DragEnter(final long p0, final int p1, final int p2);
    
    public static final native boolean ImageList_DragLeave(final long p0);
    
    public static final native boolean ImageList_DragMove(final int p0, final int p1);
    
    public static final native boolean ImageList_DragShowNolock(final boolean p0);
    
    public static final native void ImageList_EndDrag();
    
    public static final native boolean ImageList_GetIconSize(final long p0, final int[] p1, final int[] p2);
    
    public static final native int ImageList_GetImageCount(final long p0);
    
    public static final native boolean ImageList_Remove(final long p0, final int p1);
    
    public static final native boolean ImageList_Replace(final long p0, final int p1, final long p2, final long p3);
    
    public static final native int ImageList_ReplaceIcon(final long p0, final int p1, final long p2);
    
    public static final native boolean ImageList_SetIconSize(final long p0, final int p1, final int p2);
    
    public static final native long ImmEscape(final long p0, final long p1, final int p2, final char[] p3);
    
    public static final native boolean ImmGetCompositionFont(final long p0, final LOGFONT p1);
    
    public static final native int ImmGetCompositionString(final long p0, final int p1, final char[] p2, final int p3);
    
    public static final native int ImmGetCompositionString(final long p0, final int p1, final int[] p2, final int p3);
    
    public static final native int ImmGetCompositionString(final long p0, final int p1, final byte[] p2, final int p3);
    
    public static final native long ImmGetContext(final long p0);
    
    public static final native boolean ImmGetConversionStatus(final long p0, final int[] p1, final int[] p2);
    
    public static final native long ImmGetDefaultIMEWnd(final long p0);
    
    public static final native boolean ImmGetOpenStatus(final long p0);
    
    public static final native boolean ImmNotifyIME(final long p0, final int p1, final int p2, final int p3);
    
    public static final native boolean ImmReleaseContext(final long p0, final long p1);
    
    public static final native boolean ImmSetCompositionFont(final long p0, final LOGFONT p1);
    
    public static final native boolean ImmSetCompositionWindow(final long p0, final COMPOSITIONFORM p1);
    
    public static final native boolean ImmSetCandidateWindow(final long p0, final CANDIDATEFORM p1);
    
    public static final native boolean ImmSetConversionStatus(final long p0, final int p1, final int p2);
    
    public static final native boolean ImmSetOpenStatus(final long p0, final boolean p1);
    
    public static final native boolean InitCommonControlsEx(final INITCOMMONCONTROLSEX p0);
    
    public static final native boolean InsertMenuItem(final long p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native boolean InternetGetCookie(final char[] p0, final char[] p1, final char[] p2, final int[] p3);
    
    public static final native boolean InternetSetCookie(final char[] p0, final char[] p1, final char[] p2);
    
    public static final native boolean InternetSetOption(final long p0, final int p1, final long p2, final int p3);
    
    public static final native int IntersectClipRect(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean IntersectRect(final RECT p0, final RECT p1, final RECT p2);
    
    public static final native boolean InvalidateRect(final long p0, final RECT p1, final boolean p2);
    
    public static final native boolean InvalidateRgn(final long p0, final long p1, final boolean p2);
    
    public static final native boolean IsAppThemed();
    
    public static final native boolean IsDarkModeAvailable();
    
    public static final native boolean IsHungAppWindow(final long p0);
    
    public static final native boolean IsIconic(final long p0);
    
    public static final native boolean IsTouchWindow(final long p0, final long[] p1);
    
    public static final native boolean IsWindowEnabled(final long p0);
    
    public static final native boolean IsWindowVisible(final long p0);
    
    public static final native boolean IsZoomed(final long p0);
    
    public static final native boolean KillTimer(final long p0, final long p1);
    
    public static final native boolean LineTo(final long p0, final int p1, final int p2);
    
    public static final native long LoadBitmap(final long p0, final long p1);
    
    public static final native long LoadCursor(final long p0, final long p1);
    
    public static final native long LoadIcon(final long p0, final long p1);
    
    public static final native int LoadIconMetric(final long p0, final long p1, final int p2, final long[] p3);
    
    public static final native long LoadImage(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native long LocalFree(final long p0);
    
    public static final native boolean LPtoDP(final long p0, final POINT p1, final int p2);
    
    public static final native int MapVirtualKey(final int p0, final int p1);
    
    public static final native int MapWindowPoints(final long p0, final long p1, final POINT p2, final int p3);
    
    public static final native int MapWindowPoints(final long p0, final long p1, final RECT p2, final int p3);
    
    public static final native boolean MessageBeep(final int p0);
    
    public static final native int MessageBox(final long p0, final char[] p1, final char[] p2, final int p3);
    
    public static final native boolean ModifyWorldTransform(final long p0, final float[] p1, final int p2);
    
    public static final native long MonitorFromWindow(final long p0, final int p1);
    
    public static final native void MoveMemory(final char[] p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final byte[] p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final byte[] p0, final ACCEL p1, final int p2);
    
    public static final native void MoveMemory(final byte[] p0, final BITMAPINFOHEADER p1, final int p2);
    
    public static final native void MoveMemory(final int[] p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final long[] p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final double[] p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final float[] p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final short[] p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final byte[] p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final char[] p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final int[] p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final DEVMODE p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final DOCHOSTUIINFO p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final GRADIENT_RECT p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final LOGFONT p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final MEASUREITEMSTRUCT p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final MINMAXINFO p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final MSG p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final UDACCEL p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final NMTTDISPINFO p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final RECT p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final SAFEARRAY p1, final int p2);
    
    public static final native void MoveMemory(final SAFEARRAY p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final TRIVERTEX p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final WINDOWPOS p1, final int p2);
    
    public static final native void MoveMemory(final BITMAPINFOHEADER p0, final byte[] p1, final int p2);
    
    public static final native void MoveMemory(final BITMAPINFOHEADER p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final DEVMODE p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final DOCHOSTUIINFO p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final DRAWITEMSTRUCT p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final FLICK_DATA p0, final long[] p1, final int p2);
    
    public static final native void MoveMemory(final FLICK_POINT p0, final long[] p1, final int p2);
    
    public static final native void MoveMemory(final HDITEM p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final HELPINFO p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final LOGFONT p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final MEASUREITEMSTRUCT p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final MINMAXINFO p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final POINT p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final POINT p0, final long[] p1, final int p2);
    
    public static final native void MoveMemory(final NMHDR p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMCUSTOMDRAW p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMLVCUSTOMDRAW p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTBCUSTOMDRAW p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTBHOTITEM p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTREEVIEW p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTVCUSTOMDRAW p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTVITEMCHANGE p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMUPDOWN p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final NMLVCUSTOMDRAW p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final NMTBCUSTOMDRAW p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final NMTVCUSTOMDRAW p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final NMLVDISPINFO p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final NMTVDISPINFO p1, final int p2);
    
    public static final native void MoveMemory(final NMLVDISPINFO p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTVDISPINFO p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMLVODSTATECHANGE p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMHEADER p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMLINK p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMLISTVIEW p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMREBARCHILDSIZE p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMREBARCHEVRON p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTOOLBAR p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTTCUSTOMDRAW p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final NMTTDISPINFO p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final EMR p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final EMREXTCREATEFONTINDIRECTW p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final SHDRAGIMAGE p1, final int p2);
    
    public static final native void MoveMemory(final TEXTMETRIC p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final TOUCHINPUT p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final WINDOWPOS p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final MSG p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final UDACCEL p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final DROPFILES p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final double[] p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final float[] p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final long[] p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final short[] p1, final int p2);
    
    public static final native void MoveMemory(final SCRIPT_ITEM p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final SCRIPT_LOGATTR p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final SCRIPT_PROPERTIES p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final long p0, final CIDA p1, final int p2);
    
    public static final native void MoveMemory(final CIDA p0, final long p1, final int p2);
    
    public static final native boolean MoveToEx(final long p0, final int p1, final int p2, final long p3);
    
    public static final native int MultiByteToWideChar(final int p0, final int p1, final byte[] p2, final int p3, final char[] p4, final int p5);
    
    public static final native int MultiByteToWideChar(final int p0, final int p1, final long p2, final int p3, final char[] p4, final int p5);
    
    public static final native void NotifyWinEvent(final int p0, final long p1, final int p2, final int p3);
    
    public static final native boolean OffsetRect(final RECT p0, final int p1, final int p2);
    
    public static final native int OffsetRgn(final long p0, final int p1, final int p2);
    
    public static final native int OleInitialize(final long p0);
    
    public static final native void OleUninitialize();
    
    public static final native boolean OpenClipboard(final long p0);
    
    public static final native long OpenThemeData(final long p0, final char[] p1);
    
    public static final native boolean PatBlt(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native boolean PathIsExe(final long p0);
    
    public static final native boolean PeekMessage(final MSG p0, final long p1, final int p2, final int p3, final int p4);
    
    public static final native boolean Pie(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    public static final native boolean Polygon(final long p0, final int[] p1, final int p2);
    
    public static final native boolean Polyline(final long p0, final int[] p1, final int p2);
    
    public static final native boolean PostMessage(final long p0, final int p1, final long p2, final long p3);
    
    public static final native boolean PostThreadMessage(final int p0, final int p1, final long p2, final long p3);
    
    public static final native boolean PrintDlg(final PRINTDLG p0);
    
    public static final native boolean PrintWindow(final long p0, final long p1, final int p2);
    
    public static final native int PSPropertyKeyFromString(final char[] p0, final PROPERTYKEY p1);
    
    public static final native boolean PtInRect(final RECT p0, final POINT p1);
    
    public static final native boolean PtInRegion(final long p0, final int p1, final int p2);
    
    public static final native boolean Rectangle(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean RectInRegion(final long p0, final RECT p1);
    
    public static final native boolean RedrawWindow(final long p0, final RECT p1, final long p2, final int p3);
    
    public static final native int RegCloseKey(final long p0);
    
    public static final native int RegCreateKeyEx(final long p0, final char[] p1, final int p2, final char[] p3, final int p4, final int p5, final long p6, final long[] p7, final long[] p8);
    
    public static final native int RegDeleteValue(final long p0, final char[] p1);
    
    public static final native int RegEnumKeyEx(final long p0, final int p1, final char[] p2, final int[] p3, final int[] p4, final char[] p5, final int[] p6, final long p7);
    
    public static final native int RegisterClass(final WNDCLASS p0);
    
    public static final native boolean RegisterTouchWindow(final long p0, final int p1);
    
    public static final native int RegisterWindowMessage(final char[] p0);
    
    public static final native int RegisterClipboardFormat(final char[] p0);
    
    public static final native int RegOpenKeyEx(final long p0, final char[] p1, final int p2, final int p3, final long[] p4);
    
    public static final native int RegQueryValueEx(final long p0, final char[] p1, final long p2, final int[] p3, final char[] p4, final int[] p5);
    
    public static final native int RegQueryValueEx(final long p0, final char[] p1, final long p2, final int[] p3, final int[] p4, final int[] p5);
    
    public static final native int RegSetValueEx(final long p0, final char[] p1, final int p2, final int p3, final int[] p4, final int p5);
    
    public static final native boolean ReleaseCapture();
    
    public static final native int ReleaseDC(final long p0, final long p1);
    
    public static final native boolean RemoveMenu(final long p0, final int p1, final int p2);
    
    public static final native long RemoveProp(final long p0, final long p1);
    
    public static final native boolean ReplyMessage(final long p0);
    
    public static final native boolean RestoreDC(final long p0, final int p1);
    
    public static final native boolean RoundRect(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    public static final native int RtlGetVersion(final OSVERSIONINFOEX p0);
    
    public static final native int SaveDC(final long p0);
    
    public static final native boolean ScreenToClient(final long p0, final POINT p1);
    
    public static final native int ScriptApplyDigitSubstitution(final long p0, final SCRIPT_CONTROL p1, final SCRIPT_STATE p2);
    
    public static final native int ScriptBreak(final char[] p0, final int p1, final SCRIPT_ANALYSIS p2, final long p3);
    
    public static final native int ScriptGetProperties(final long[] p0, final int[] p1);
    
    public static final native int ScriptCacheGetHeight(final long p0, final long p1, final int[] p2);
    
    public static final native int ScriptCPtoX(final int p0, final boolean p1, final int p2, final int p3, final long p4, final long p5, final long p6, final SCRIPT_ANALYSIS p7, final int[] p8);
    
    public static final native int ScriptFreeCache(final long p0);
    
    public static final native int ScriptGetFontProperties(final long p0, final long p1, final SCRIPT_FONTPROPERTIES p2);
    
    public static final native int ScriptGetLogicalWidths(final SCRIPT_ANALYSIS p0, final int p1, final int p2, final long p3, final long p4, final long p5, final int[] p6);
    
    public static final native int ScriptItemize(final char[] p0, final int p1, final int p2, final SCRIPT_CONTROL p3, final SCRIPT_STATE p4, final long p5, final int[] p6);
    
    public static final native int ScriptJustify(final long p0, final long p1, final int p2, final int p3, final int p4, final long p5);
    
    public static final native int ScriptLayout(final int p0, final byte[] p1, final int[] p2, final int[] p3);
    
    public static final native int ScriptPlace(final long p0, final long p1, final long p2, final int p3, final long p4, final SCRIPT_ANALYSIS p5, final long p6, final long p7, final int[] p8);
    
    public static final native int ScriptGetCMap(final long p0, final long p1, final char[] p2, final int p3, final int p4, final short[] p5);
    
    public static final native int ScriptShape(final long p0, final long p1, final char[] p2, final int p3, final int p4, final SCRIPT_ANALYSIS p5, final long p6, final long p7, final long p8, final int[] p9);
    
    public static final native int ScriptStringAnalyse(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5, final int p6, final SCRIPT_CONTROL p7, final SCRIPT_STATE p8, final long p9, final long p10, final long p11, final long p12);
    
    public static final native int ScriptStringOut(final long p0, final int p1, final int p2, final int p3, final RECT p4, final int p5, final int p6, final boolean p7);
    
    public static final native int ScriptStringFree(final long p0);
    
    public static final native int ScriptTextOut(final long p0, final long p1, final int p2, final int p3, final int p4, final RECT p5, final SCRIPT_ANALYSIS p6, final long p7, final int p8, final long p9, final int p10, final long p11, final long p12, final long p13);
    
    public static final native int ScriptXtoCP(final int p0, final int p1, final int p2, final long p3, final long p4, final long p5, final SCRIPT_ANALYSIS p6, final int[] p7, final int[] p8);
    
    public static final native int ScrollWindowEx(final long p0, final int p1, final int p2, final RECT p3, final RECT p4, final long p5, final RECT p6, final int p7);
    
    public static final native int SelectClipRgn(final long p0, final long p1);
    
    public static final native long SelectObject(final long p0, final long p1);
    
    public static final native int SendInput(final int p0, final INPUT p1, final int p2);
    
    public static final native long SendMessage(final long p0, final int p1, final int[] p2, final int[] p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final char[] p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final int[] p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final long p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final LVCOLUMN p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final LVHITTESTINFO p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final LITEM p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final LVITEM p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final LVINSERTMARK p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final MARGINS p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final MCHITTESTINFO p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final REBARBANDINFO p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final RECT p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final SYSTEMTIME p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final SHDRAGIMAGE p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TBBUTTON p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TBBUTTONINFO p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TCITEM p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TCHITTESTINFO p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TOOLINFO p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TVHITTESTINFO p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TVINSERTSTRUCT p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TVITEM p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final TVSORTCB p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final UDACCEL p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final HDHITTESTINFO p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final HDITEM p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final HDLAYOUT p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final BUTTON_IMAGELIST p3);
    
    public static final native long SendMessage(final long p0, final int p1, final long p2, final SIZE p3);
    
    public static final native long SetActiveWindow(final long p0);
    
    public static final native int SetBkColor(final long p0, final int p1);
    
    public static final native int SetBkMode(final long p0, final int p1);
    
    public static final native boolean SetBrushOrgEx(final long p0, final int p1, final int p2, final POINT p3);
    
    public static final native long SetCapture(final long p0);
    
    public static final native boolean SetCaretPos(final int p0, final int p1);
    
    public static final native int SetCurrentProcessExplicitAppUserModelID(final char[] p0);
    
    public static final native long SetCursor(final long p0);
    
    public static final native boolean SetCursorPos(final int p0, final int p1);
    
    public static final native int SetDCBrushColor(final long p0, final int p1);
    
    public static final native int SetDIBColorTable(final long p0, final int p1, final int p2, final byte[] p3);
    
    public static final native long SetFocus(final long p0);
    
    public static final native boolean SetForegroundWindow(final long p0);
    
    public static final native boolean SetGestureConfig(final long p0, final int p1, final int p2, final GESTURECONFIG p3, final int p4);
    
    public static final native int SetGraphicsMode(final long p0, final int p1);
    
    public static final native boolean SetLayeredWindowAttributes(final long p0, final int p1, final byte p2, final int p3);
    
    public static final native int SetLayout(final long p0, final int p1);
    
    public static final native boolean SetMenu(final long p0, final long p1);
    
    public static final native boolean SetMenuDefaultItem(final long p0, final int p1, final int p2);
    
    public static final native boolean SetMenuInfo(final long p0, final MENUINFO p1);
    
    public static final native boolean SetMenuItemInfo(final long p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native int SetMetaRgn(final long p0);
    
    public static final native long SetParent(final long p0, final long p1);
    
    public static final native int SetPixel(final long p0, final int p1, final int p2, final int p3);
    
    public static final native int SetPolyFillMode(final long p0, final int p1);
    
    public static final native boolean SetProcessDPIAware();
    
    public static final native int SetPreferredAppMode(final int p0);
    
    public static final native boolean SetRect(final RECT p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean SetRectRgn(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int SetROP2(final long p0, final int p1);
    
    public static final native boolean SetScrollInfo(final long p0, final int p1, final SCROLLINFO p2, final boolean p3);
    
    public static final native int SetStretchBltMode(final long p0, final int p1);
    
    public static final native boolean SetProp(final long p0, final long p1, final long p2);
    
    public static final native int SetTextColor(final long p0, final int p1);
    
    public static final native long SetTimer(final long p0, final long p1, final int p2, final long p3);
    
    public static final native int SetWindowLong(final long p0, final int p1, final int p2);
    
    public static final native long SetWindowLongPtr(final long p0, final int p1, final long p2);
    
    public static final native boolean SetWindowOrgEx(final long p0, final int p1, final int p2, final POINT p3);
    
    public static final native boolean SetWindowPlacement(final long p0, final WINDOWPLACEMENT p1);
    
    public static final native boolean SetWindowPos(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    public static final native int SetWindowRgn(final long p0, final long p1, final boolean p2);
    
    public static final native boolean SetWindowText(final long p0, final char[] p1);
    
    public static final native int SetWindowTheme(final long p0, final char[] p1, final char[] p2);
    
    public static final native long SetWindowsHookEx(final int p0, final long p1, final long p2, final int p3);
    
    public static final native boolean SetWorldTransform(final long p0, final float[] p1);
    
    public static final native long SHGetFileInfo(final char[] p0, final int p1, final SHFILEINFO p2, final int p3, final int p4);
    
    public static final native boolean ShellExecuteEx(final SHELLEXECUTEINFO p0);
    
    public static final native boolean Shell_NotifyIcon(final int p0, final NOTIFYICONDATA p1);
    
    public static final native boolean ShowCaret(final long p0);
    
    public static final native boolean ShowOwnedPopups(final long p0, final boolean p1);
    
    public static final native boolean ShowScrollBar(final long p0, final int p1, final boolean p2);
    
    public static final native boolean ShowWindow(final long p0, final int p1);
    
    public static final native int StartDoc(final long p0, final DOCINFO p1);
    
    public static final native int StartPage(final long p0);
    
    public static final native boolean StretchBlt(final long p0, final int p1, final int p2, final int p3, final int p4, final long p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    public static final native boolean SystemParametersInfo(final int p0, final int p1, final HIGHCONTRAST p2, final int p3);
    
    public static final native boolean SystemParametersInfo(final int p0, final int p1, final RECT p2, final int p3);
    
    public static final native boolean SystemParametersInfo(final int p0, final int p1, final NONCLIENTMETRICS p2, final int p3);
    
    public static final native boolean SystemParametersInfo(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native int ToUnicode(final int p0, final int p1, final byte[] p2, final char[] p3, final int p4, final int p5);
    
    public static final native boolean TreeView_GetItemRect(final long p0, final long p1, final RECT p2, final boolean p3);
    
    public static final native boolean TrackMouseEvent(final TRACKMOUSEEVENT p0);
    
    public static final native boolean TrackPopupMenu(final long p0, final int p1, final int p2, final int p3, final int p4, final long p5, final RECT p6);
    
    public static final native int TranslateAccelerator(final long p0, final long p1, final MSG p2);
    
    public static final native boolean TranslateCharsetInfo(final long p0, final int[] p1, final int p2);
    
    public static final native boolean TranslateMDISysAccel(final long p0, final MSG p1);
    
    public static final native boolean TranslateMessage(final MSG p0);
    
    public static final native boolean TransparentBlt(final long p0, final int p1, final int p2, final int p3, final int p4, final long p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    public static final native boolean UnhookWindowsHookEx(final long p0);
    
    public static final native boolean UnregisterClass(final char[] p0, final long p1);
    
    public static final native boolean UnregisterTouchWindow(final long p0);
    
    public static final native boolean UpdateWindow(final long p0);
    
    public static final native int UrlCreateFromPath(final char[] p0, final char[] p1, final int[] p2, final int p3);
    
    public static final native boolean ValidateRect(final long p0, final RECT p1);
    
    public static final native short VkKeyScan(final short p0);
    
    public static final native boolean WaitMessage();
    
    public static final native int WideCharToMultiByte(final int p0, final int p1, final char[] p2, final int p3, final byte[] p4, final int p5, final byte[] p6, final int[] p7);
    
    public static final native int WideCharToMultiByte(final int p0, final int p1, final char[] p2, final int p3, final long p4, final int p5, final byte[] p6, final int[] p7);
    
    public static final native long WindowFromDC(final long p0);
    
    public static final native long WindowFromPoint(final POINT p0);
    
    public static final native int wcslen(final long p0);
    
    public static final native long MapViewOfFile(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean UnmapViewOfFile(final long p0);
    
    public static final native long OpenProcess(final int p0, final boolean p1, final int p2);
    
    public static final native long GetCurrentProcess();
    
    public static final native boolean DuplicateHandle(final long p0, final long p1, final long p2, final long[] p3, final int p4, final boolean p5, final int p6);
    
    static {
        Library.loadLibrary("swt");
        final int dwVersion = GetVersion();
        WIN32_VERSION = VERSION(dwVersion & 0xFF, dwVersion >> 8 & 0xFF);
        final OSVERSIONINFOEX osVersionInfoEx = new OSVERSIONINFOEX();
        osVersionInfoEx.dwOSVersionInfoSize = OSVERSIONINFOEX.sizeof;
        if (0 == RtlGetVersion(osVersionInfoEx)) {
            WIN32_BUILD = osVersionInfoEx.dwBuildNumber;
        }
        else {
            System.err.println("SWT: OS: Failed to detect Windows build number");
            WIN32_BUILD = 0;
        }
        if (System.getProperty("org.eclipse.swt.internal.win32.OS.NO_MANIFEST") == null) {
            final ACTCTX pActCtx = new ACTCTX();
            pActCtx.cbSize = ACTCTX.sizeof;
            pActCtx.dwFlags = 152;
            pActCtx.hModule = GetLibraryHandle();
            pActCtx.lpResourceName = 2L;
            final long hActCtx = CreateActCtx(pActCtx);
            final long[] lpCookie = { 0L };
            ActivateActCtx(hActCtx, lpCookie);
        }
        SetProcessDPIAware();
        IsDBLocale = (GetSystemMetrics(82) != 0);
        NOTIFYICONDATA_V2_SIZE = NOTIFYICONDATA_V2_SIZE();
    }
}
