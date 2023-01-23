//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSEvent extends nsISupports
{
    public static final String NS_IDOMNSEVENT_IID = "{e565d518-4510-407f-a3d9-3b4107549c6d}";
    public static final int MOUSEDOWN = 1;
    public static final int MOUSEUP = 2;
    public static final int MOUSEOVER = 4;
    public static final int MOUSEOUT = 8;
    public static final int MOUSEMOVE = 16;
    public static final int MOUSEDRAG = 32;
    public static final int CLICK = 64;
    public static final int DBLCLICK = 128;
    public static final int KEYDOWN = 256;
    public static final int KEYUP = 512;
    public static final int KEYPRESS = 1024;
    public static final int DRAGDROP = 2048;
    public static final int FOCUS = 4096;
    public static final int BLUR = 8192;
    public static final int SELECT = 16384;
    public static final int CHANGE = 32768;
    public static final int RESET = 65536;
    public static final int SUBMIT = 131072;
    public static final int SCROLL = 262144;
    public static final int LOAD = 524288;
    public static final int UNLOAD = 1048576;
    public static final int XFER_DONE = 2097152;
    public static final int ABORT = 4194304;
    public static final int ERROR = 8388608;
    public static final int LOCATE = 16777216;
    public static final int MOVE = 33554432;
    public static final int RESIZE = 67108864;
    public static final int FORWARD = 134217728;
    public static final int HELP = 268435456;
    public static final int BACK = 536870912;
    public static final int TEXT = 1073741824;
    public static final int ALT_MASK = 1;
    public static final int CONTROL_MASK = 2;
    public static final int SHIFT_MASK = 4;
    public static final int META_MASK = 8;
    
    nsIDOMEventTarget getOriginalTarget();
    
    nsIDOMEventTarget getExplicitOriginalTarget();
    
    void preventBubble();
    
    void preventCapture();
    
    boolean getIsTrusted();
}
