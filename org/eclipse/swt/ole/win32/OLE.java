//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import java.io.*;
import org.eclipse.swt.internal.ole.win32.*;

public class OLE extends SWT
{
    public static final int S_FALSE = 1;
    public static final int S_OK = 0;
    public static final int E_FAIL = -2147467259;
    public static final int E_INVALIDARG = -2147024809;
    public static final int E_NOINTERFACE = -2147467262;
    public static final int E_NOTIMPL = -2147467263;
    public static final String IID_IUNKNOWN = "{00000000-0000-0000-C000-000000000046}";
    public static final String IID_IDISPATCH = "{00020400-0000-0000-C000-000000000046}";
    public static final int OLEIVERB_DISCARDUNDOSTATE = -6;
    public static final int OLEIVERB_HIDE = -3;
    public static final int OLEIVERB_INPLACEACTIVATE = -5;
    public static final int OLEIVERB_OPEN = -2;
    public static final int OLEIVERB_PRIMARY = 0;
    public static final int OLEIVERB_PROPERTIES = -7;
    public static final int OLEIVERB_SHOW = -1;
    public static final int OLEIVERB_UIACTIVATE = -4;
    public static final int PROPERTY_CHANGING = 0;
    public static final int PROPERTY_CHANGED = 1;
    public static final int HRESULT_UNSPECIFIED = 0;
    public static final int ERROR_CANNOT_CREATE_FILE = 1000;
    public static final int ERROR_CANNOT_CREATE_OBJECT = 1001;
    public static final int ERROR_CANNOT_OPEN_FILE = 1002;
    public static final int ERROR_INTERFACE_NOT_FOUND = 1003;
    public static final int ERROR_INVALID_CLASSID = 1004;
    public static final int ERROR_CANNOT_ACCESS_CLASSFACTORY = 1005;
    public static final int ERROR_CANNOT_CREATE_LICENSED_OBJECT = 1006;
    public static final int ERROR_OUT_OF_MEMORY = 1007;
    public static final int ERROR_CANNOT_CHANGE_VARIANT_TYPE = 1010;
    public static final int ERROR_INVALID_INTERFACE_ADDRESS = 1011;
    public static final int ERROR_APPLICATION_NOT_FOUND = 1013;
    public static final int ERROR_ACTION_NOT_PERFORMED = 1014;
    public static final int OLECMDF_SUPPORTED = 1;
    public static final int OLECMDF_ENABLED = 2;
    public static final int OLECMDF_LATCHED = 4;
    public static final int OLECMDF_NINCHED = 8;
    public static final int OLECMDTEXTF_NONE = 0;
    public static final int OLECMDTEXTF_NAME = 1;
    public static final int OLECMDTEXTF_STATUS = 2;
    public static final int OLECMDEXECOPT_DODEFAULT = 0;
    public static final int OLECMDEXECOPT_PROMPTUSER = 1;
    public static final int OLECMDEXECOPT_DONTPROMPTUSER = 2;
    public static final int OLECMDEXECOPT_SHOWHELP = 3;
    public static final int OLECMDID_OPEN = 1;
    public static final int OLECMDID_NEW = 2;
    public static final int OLECMDID_SAVE = 3;
    public static final int OLECMDID_SAVEAS = 4;
    public static final int OLECMDID_SAVECOPYAS = 5;
    public static final int OLECMDID_PRINT = 6;
    public static final int OLECMDID_PRINTPREVIEW = 7;
    public static final int OLECMDID_PAGESETUP = 8;
    public static final int OLECMDID_SPELL = 9;
    public static final int OLECMDID_PROPERTIES = 10;
    public static final int OLECMDID_CUT = 11;
    public static final int OLECMDID_COPY = 12;
    public static final int OLECMDID_PASTE = 13;
    public static final int OLECMDID_PASTESPECIAL = 14;
    public static final int OLECMDID_UNDO = 15;
    public static final int OLECMDID_REDO = 16;
    public static final int OLECMDID_SELECTALL = 17;
    public static final int OLECMDID_CLEARSELECTION = 18;
    public static final int OLECMDID_ZOOM = 19;
    public static final int OLECMDID_GETZOOMRANGE = 20;
    public static final int OLECMDID_UPDATECOMMANDS = 21;
    public static final int OLECMDID_REFRESH = 22;
    public static final int OLECMDID_STOP = 23;
    public static final int OLECMDID_HIDETOOLBARS = 24;
    public static final int OLECMDID_SETPROGRESSMAX = 25;
    public static final int OLECMDID_SETPROGRESSPOS = 26;
    public static final int OLECMDID_SETPROGRESSTEXT = 27;
    public static final int OLECMDID_SETTITLE = 28;
    public static final int OLECMDID_SETDOWNLOADSTATE = 29;
    public static final int OLECMDID_STOPDOWNLOAD = 30;
    public static int VARFLAG_FREADONLY;
    public static int VARFLAG_FSOURCE;
    public static int VARFLAG_FBINDABLE;
    public static int VARFLAG_FREQUESTEDIT;
    public static int VARFLAG_FDISPLAYBIND;
    public static int VARFLAG_FDEFAULTBIND;
    public static int VARFLAG_FHIDDEN;
    public static int VARFLAG_FRESTRICTED;
    public static int VARFLAG_FDEFAULTCOLLELEM;
    public static int VARFLAG_FUIDEFAULT;
    public static int VARFLAG_FNONBROWSABLE;
    public static int VARFLAG_FREPLACEABLE;
    public static int VARFLAG_FIMMEDIATEBIND;
    public static int VAR_PERINSTANCE;
    public static int VAR_STATIC;
    public static int VAR_CONST;
    public static int VAR_DISPATCH;
    public static short IDLFLAG_NONE;
    public static short IDLFLAG_FIN;
    public static short IDLFLAG_FOUT;
    public static short IDLFLAG_FLCID;
    public static short IDLFLAG_FRETVAL;
    public static final short VT_BOOL = 11;
    public static final short VT_BSTR = 8;
    public static final short VT_BYREF = 16384;
    public static final short VT_CY = 6;
    public static final short VT_DATE = 7;
    public static final short VT_DISPATCH = 9;
    public static final short VT_EMPTY = 0;
    public static final short VT_ERROR = 10;
    public static final short VT_I2 = 2;
    public static final short VT_I4 = 3;
    public static final short VT_NULL = 1;
    public static final short VT_R4 = 4;
    public static final short VT_R8 = 5;
    public static final short VT_UI1 = 17;
    public static final short VT_UI4 = 19;
    public static final short VT_UNKNOWN = 13;
    public static final short VT_VARIANT = 12;
    public static final short VT_PTR = 26;
    public static final short VT_USERDEFINED = 29;
    public static final short VT_HRESULT = 25;
    public static final short VT_DECIMAL = 14;
    public static final short VT_I1 = 16;
    public static final short VT_UI2 = 18;
    public static final short VT_I8 = 20;
    public static final short VT_UI8 = 21;
    public static final short VT_INT = 22;
    public static final short VT_UINT = 23;
    public static final short VT_VOID = 24;
    public static final short VT_SAFEARRAY = 27;
    public static final short VT_CARRAY = 28;
    public static final short VT_LPSTR = 30;
    public static final short VT_LPWSTR = 31;
    public static final short VT_RECORD = 36;
    public static final short VT_FILETIME = 64;
    public static final short VT_BLOB = 65;
    public static final short VT_STREAM = 66;
    public static final short VT_STORAGE = 67;
    public static final short VT_STREAMED_OBJECT = 68;
    public static final short VT_STORED_OBJECT = 69;
    public static final short VT_BLOB_OBJECT = 70;
    public static final short VT_CF = 71;
    public static final short VT_CLSID = 72;
    public static final short VT_VERSIONED_STREAM = 73;
    public static final short VT_BSTR_BLOB = 4095;
    public static final short VT_VECTOR = 4096;
    public static final short VT_ARRAY = 8192;
    public static final int INVOKE_FUNC = 1;
    public static final int INVOKE_PROPERTYGET = 2;
    public static final int INVOKE_PROPERTYPUT = 4;
    public static final int INVOKE_PROPERTYPUTREF = 8;
    public static final int FUNC_VIRTUAL = 0;
    public static final int FUNC_PUREVIRTUAL = 1;
    public static final int FUNC_NONVIRTUAL = 2;
    public static final int FUNC_STATIC = 3;
    public static final int FUNC_DISPATCH = 4;
    public static final short FUNCFLAG_FRESTRICTED = 1;
    public static final short FUNCFLAG_FSOURCE = 2;
    public static final short FUNCFLAG_FBINDABLE = 4;
    public static final short FUNCFLAG_FREQUESTEDIT = 8;
    public static final short FUNCFLAG_FDISPLAYBIND = 16;
    public static final short FUNCFLAG_FDEFAULTBIND = 32;
    public static final short FUNCFLAG_FHIDDEN = 64;
    public static final short FUNCFLAG_FUSESGETLASTERROR = 128;
    public static final short FUNCFLAG_FDEFAULTCOLLELEM = 256;
    public static final short FUNCFLAG_FUIDEFAULT = 512;
    public static final short FUNCFLAG_FNONBROWSABLE = 1024;
    public static final short FUNCFLAG_FREPLACEABLE = 2048;
    public static final short FUNCFLAG_FIMMEDIATEBIND = 4096;
    public static final int CC_FASTCALL = 0;
    public static final int CC_CDECL = 1;
    public static final int CC_MSCPASCAL = 2;
    public static final int CC_PASCAL = 2;
    public static final int CC_MACPASCAL = 3;
    public static final int CC_STDCALL = 4;
    public static final int CC_FPFASTCALL = 5;
    public static final int CC_SYSCALL = 6;
    public static final int CC_MPWCDECL = 7;
    public static final int CC_MPWPASCAL = 8;
    public static final int CC_MAX = 9;
    static final String ERROR_NOT_IMPLEMENTED_MSG = "Required functionality not currently supported.";
    static final String ERROR_CANNOT_CREATE_FILE_MSG = "Failed to create file.";
    static final String ERROR_CANNOT_CREATE_OBJECT_MSG = "Failed to create Ole Client.";
    static final String ERROR_CANNOT_OPEN_FILE_MSG = "File does not exist, is not accessible to user or does not have the correct format.";
    static final String ERROR_INTERFACE_NOT_FOUND_MSG = "Failed to find requested interface on OLE Object.";
    static final String ERROR_INVALID_CLASSID_MSG = "Class ID not found in registry";
    static final String ERROR_CANNOT_ACCESS_CLASSFACTORY_MSG = "Failed to get the class factory for the specified classID";
    static final String ERROR_CANNOT_CREATE_LICENSED_OBJECT_MSG = "Failed to create Licensed instance";
    static final String ERROR_OUT_OF_MEMORY_MSG = "Out of Memory";
    static final String ERROR_CANNOT_CHANGE_VARIANT_TYPE_MSG = "Failed to change Variant type";
    static final String ERROR_INVALID_INTERFACE_ADDRESS_MSG = "Invalid address received for Ole Interface.";
    static final String ERROR_APPLICATION_NOT_FOUND_MSG = "Unable to find Application.";
    static final String ERROR_ACTION_NOT_PERFORMED_MSG = "Action can not be performed.";
    
    public static void error(final int code) {
        error(code, 0);
    }
    
    public static void error(final int code, final int hresult) {
        switch (code) {
            case 1011: {
                throw new IllegalArgumentException("Invalid address received for Ole Interface.");
            }
            case 1000: {
                String msg = "Failed to create file.";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1001: {
                String msg = "Failed to create Ole Client.";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1002: {
                String msg = "File does not exist, is not accessible to user or does not have the correct format.";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1003: {
                String msg = "Failed to find requested interface on OLE Object.";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1004: {
                String msg = "Class ID not found in registry";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1005: {
                String msg = "Failed to get the class factory for the specified classID";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1006: {
                String msg = "Failed to create Licensed instance";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1010: {
                String msg = "Failed to change Variant type";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1013: {
                String msg = "Unable to find Application.";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1014: {
                String msg = "Action can not be performed.";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            case 1007: {
                String msg = "Action can not be performed.";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTError(code, msg);
            }
            default: {
                SWT.error(code);
            }
        }
    }
    
    public static String findProgramID(String extension) {
        if (extension == null) {
            SWT.error(4);
        }
        if (extension.length() == 0) {
            return "";
        }
        if (extension.charAt(0) != '.') {
            extension = "." + extension;
        }
        final TCHAR extensionKey = new TCHAR(0, extension, true);
        final String result = getKeyValue(extensionKey);
        if (result != null) {
            final TCHAR notInsertableKey = new TCHAR(0, result + "\\NotInsertable", true);
            if (getKeyExists(notInsertableKey)) {
                return "";
            }
            final TCHAR insertableKey = new TCHAR(0, result + "\\Insertable", true);
            if (getKeyExists(insertableKey)) {
                return result;
            }
            final TCHAR serverKey = new TCHAR(0, result + "\\protocol\\StdFileEditing\\server", true);
            if (getKeyExists(serverKey)) {
                return result;
            }
        }
        return "";
    }
    
    static String getKeyValue(final TCHAR key) {
        final long[] phkResult = { 0L };
        if (OS.RegOpenKeyEx(-2147483648L, key, 0, 131097, phkResult) != 0) {
            return null;
        }
        String result = null;
        final int[] lpcbData = { 0 };
        if (OS.RegQueryValueEx(phkResult[0], (TCHAR)null, 0L, (int[])null, (TCHAR)null, lpcbData) == 0) {
            int length = lpcbData[0] / 2;
            if (length == 0) {
                result = "";
            }
            else {
                final TCHAR lpData = new TCHAR(0, length);
                if (OS.RegQueryValueEx(phkResult[0], (TCHAR)null, 0L, (int[])null, lpData, lpcbData) == 0) {
                    length = Math.max(0, lpData.length() - 1);
                    result = lpData.toString(0, length);
                }
            }
        }
        if (phkResult[0] != 0L) {
            OS.RegCloseKey(phkResult[0]);
        }
        return result;
    }
    
    private static boolean getKeyExists(final TCHAR key) {
        final long[] phkResult = { 0L };
        if (OS.RegOpenKeyEx(-2147483648L, key, 0, 131097, phkResult) != 0) {
            return false;
        }
        if (phkResult[0] != 0L) {
            OS.RegCloseKey(phkResult[0]);
        }
        return true;
    }
    
    public static boolean isOleFile(final File file) {
        return file != null && file.exists() && !file.isDirectory() && COM.StgIsStorageFile(file.getAbsolutePath().toCharArray()) == 0;
    }
    
    static {
        OLE.VARFLAG_FREADONLY = 1;
        OLE.VARFLAG_FSOURCE = 2;
        OLE.VARFLAG_FBINDABLE = 4;
        OLE.VARFLAG_FREQUESTEDIT = 8;
        OLE.VARFLAG_FDISPLAYBIND = 16;
        OLE.VARFLAG_FDEFAULTBIND = 32;
        OLE.VARFLAG_FHIDDEN = 64;
        OLE.VARFLAG_FRESTRICTED = 128;
        OLE.VARFLAG_FDEFAULTCOLLELEM = 256;
        OLE.VARFLAG_FUIDEFAULT = 512;
        OLE.VARFLAG_FNONBROWSABLE = 1024;
        OLE.VARFLAG_FREPLACEABLE = 2048;
        OLE.VARFLAG_FIMMEDIATEBIND = 4096;
        OLE.VAR_PERINSTANCE = 0;
        OLE.VAR_STATIC = 1;
        OLE.VAR_CONST = 2;
        OLE.VAR_DISPATCH = 3;
        OLE.IDLFLAG_NONE = 0;
        OLE.IDLFLAG_FIN = 1;
        OLE.IDLFLAG_FOUT = 2;
        OLE.IDLFLAG_FLCID = 4;
        OLE.IDLFLAG_FRETVAL = 8;
    }
}
