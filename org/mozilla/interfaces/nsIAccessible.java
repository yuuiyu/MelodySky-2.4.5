//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAccessible extends nsISupports
{
    public static final String NS_IACCESSIBLE_IID = "{db717db4-37e9-42f1-a3b0-2579dd7c3814}";
    public static final long STATE_UNAVAILABLE = 1L;
    public static final long STATE_SELECTED = 2L;
    public static final long STATE_FOCUSED = 4L;
    public static final long STATE_PRESSED = 8L;
    public static final long STATE_CHECKED = 16L;
    public static final long STATE_MIXED = 32L;
    public static final long STATE_READONLY = 64L;
    public static final long STATE_HOTTRACKED = 128L;
    public static final long STATE_DEFAULT = 256L;
    public static final long STATE_EXPANDED = 512L;
    public static final long STATE_COLLAPSED = 1024L;
    public static final long STATE_BUSY = 2048L;
    public static final long STATE_FLOATING = 4096L;
    public static final long STATE_MARQUEED = 8192L;
    public static final long STATE_ANIMATED = 16384L;
    public static final long STATE_INVISIBLE = 32768L;
    public static final long STATE_OFFSCREEN = 65536L;
    public static final long STATE_SIZEABLE = 131072L;
    public static final long STATE_MOVEABLE = 262144L;
    public static final long STATE_SELFVOICING = 524288L;
    public static final long STATE_FOCUSABLE = 1048576L;
    public static final long STATE_SELECTABLE = 2097152L;
    public static final long STATE_LINKED = 4194304L;
    public static final long STATE_TRAVERSED = 8388608L;
    public static final long STATE_MULTISELECTABLE = 16777216L;
    public static final long STATE_EXTSELECTABLE = 33554432L;
    public static final long STATE_ALERT_LOW = 67108864L;
    public static final long STATE_ALERT_MEDIUM = 134217728L;
    public static final long STATE_ALERT_HIGH = 268435456L;
    public static final long STATE_PROTECTED = 536870912L;
    public static final long STATE_HASPOPUP = 1073741824L;
    public static final long STATE_REQUIRED = 67108864L;
    public static final long STATE_IMPORTANT = 134217728L;
    public static final long STATE_INVALID = 268435456L;
    public static final long STATE_CHECKABLE = 8192L;
    public static final long EXT_STATE_EDITABLE = 2097152L;
    public static final long EXT_STATE_ACTIVE = 4194304L;
    public static final long EXT_STATE_EXPANDABLE = 8388608L;
    public static final long EXT_STATE_MODAL = 16777216L;
    public static final long EXT_STATE_MULTI_LINE = 33554432L;
    public static final long EXT_STATE_SENSITIVE = 67108864L;
    public static final long EXT_STATE_SHOWING = 268435456L;
    public static final long EXT_STATE_SINGLE_LINE = 536870912L;
    public static final long EXT_STATE_TRANSIENT = 1073741824L;
    public static final long EXT_STATE_VERTICAL = 2147483648L;
    public static final long RELATION_NUL = 0L;
    public static final long RELATION_CONTROLLED_BY = 1L;
    public static final long RELATION_CONTROLLER_FOR = 2L;
    public static final long RELATION_LABEL_FOR = 3L;
    public static final long RELATION_LABELLED_BY = 4L;
    public static final long RELATION_MEMBER_OF = 5L;
    public static final long RELATION_NODE_CHILD_OF = 6L;
    public static final long RELATION_FLOWS_TO = 7L;
    public static final long RELATION_FLOWS_FROM = 8L;
    public static final long RELATION_SUBWINDOW_OF = 9L;
    public static final long RELATION_EMBEDS = 10L;
    public static final long RELATION_EMBEDDED_BY = 11L;
    public static final long RELATION_POPUP_FOR = 12L;
    public static final long RELATION_PARENT_WINDOW_OF = 13L;
    public static final long RELATION_DEFAULT_BUTTON = 16384L;
    public static final long RELATION_DESCRIBED_BY = 16385L;
    public static final long RELATION_DESCRIPTION_FOR = 16386L;
    
    nsIAccessible getParent();
    
    nsIAccessible getNextSibling();
    
    nsIAccessible getPreviousSibling();
    
    nsIAccessible getFirstChild();
    
    nsIAccessible getLastChild();
    
    int getChildCount();
    
    int getIndexInParent();
    
    String getName();
    
    void setName(final String p0);
    
    String getFinalValue();
    
    String getDescription();
    
    String getKeyboardShortcut();
    
    String getKeyBinding();
    
    long getRole();
    
    long getFinalRole();
    
    long getFinalState();
    
    long getExtState();
    
    String getHelp();
    
    nsIAccessible getFocusedChild();
    
    nsIAccessible getChildAtPoint(final int p0, final int p1);
    
    nsIAccessible getChildAt(final int p0);
    
    nsIAccessible getAccessibleToRight();
    
    nsIAccessible getAccessibleToLeft();
    
    nsIAccessible getAccessibleAbove();
    
    nsIAccessible getAccessibleBelow();
    
    nsIAccessible getAccessibleRelated(final long p0);
    
    void getBounds(final int[] p0, final int[] p1, final int[] p2, final int[] p3);
    
    void addSelection();
    
    void removeSelection();
    
    void extendSelection();
    
    void takeSelection();
    
    void takeFocus();
    
    short getNumActions();
    
    String getActionName(final short p0);
    
    void doAction(final short p0);
}
