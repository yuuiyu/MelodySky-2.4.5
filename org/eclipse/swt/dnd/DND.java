//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.*;

public class DND
{
    public static final int CLIPBOARD = 1;
    public static final int SELECTION_CLIPBOARD = 2;
    public static final int DROP_NONE = 0;
    public static final int DROP_COPY = 1;
    public static final int DROP_MOVE = 2;
    public static final int DROP_LINK = 4;
    public static final int DROP_TARGET_MOVE = 8;
    public static final int DROP_DEFAULT = 16;
    public static final int DragEnd = 2000;
    public static final int DragSetData = 2001;
    public static final int DragEnter = 2002;
    public static final int DragLeave = 2003;
    public static final int DragOver = 2004;
    public static final int DragOperationChanged = 2005;
    public static final int Drop = 2006;
    public static final int DropAccept = 2007;
    public static final int DragStart = 2008;
    public static final int FEEDBACK_NONE = 0;
    public static final int FEEDBACK_SELECT = 1;
    public static final int FEEDBACK_INSERT_BEFORE = 2;
    public static final int FEEDBACK_INSERT_AFTER = 4;
    public static final int FEEDBACK_SCROLL = 8;
    public static final int FEEDBACK_EXPAND = 16;
    public static final int ERROR_CANNOT_INIT_DRAG = 2000;
    public static final int ERROR_CANNOT_INIT_DROP = 2001;
    public static final int ERROR_CANNOT_SET_CLIPBOARD = 2002;
    public static final int ERROR_INVALID_DATA = 2003;
    public static final String DROP_TARGET_KEY = "DropTarget";
    public static final String DRAG_SOURCE_KEY = "DragSource";
    static final String INIT_DRAG_MESSAGE = "Cannot initialize Drag";
    static final String INIT_DROP_MESSAGE = "Cannot initialize Drop";
    static final String CANNOT_SET_CLIPBOARD_MESSAGE = "Cannot set data in clipboard";
    static final String INVALID_DATA_MESSAGE = "Data does not have correct format for type";
    
    public static void error(final int code) {
        error(code, 0);
    }
    
    public static void error(final int code, final int hresult) {
        switch (code) {
            case 2000: {
                String msg = "Cannot initialize Drag";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTError(code, msg);
            }
            case 2001: {
                String msg = "Cannot initialize Drop";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTError(code, msg);
            }
            case 2002: {
                String msg = "Cannot set data in clipboard";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTError(code, msg);
            }
            case 2003: {
                String msg = "Data does not have correct format for type";
                if (hresult != 0) {
                    msg = msg + " result = " + hresult;
                }
                throw new SWTException(code, msg);
            }
            default: {
                SWT.error(code);
            }
        }
    }
}
