//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAccessibleEvent extends nsISupports
{
    public static final String NS_IACCESSIBLEEVENT_IID = "{87f29033-c4a6-40a3-ac7a-3ba391f9992d}";
    public static final long EVENT_CREATE = 32768L;
    public static final long EVENT_DESTROY = 32769L;
    public static final long EVENT_SHOW = 32770L;
    public static final long EVENT_HIDE = 32771L;
    public static final long EVENT_REORDER = 32772L;
    public static final long EVENT_FOCUS = 32773L;
    public static final long EVENT_STATE_CHANGE = 32778L;
    public static final long EVENT_LOCATION_CHANGE = 32779L;
    public static final long EVENT_NAME_CHANGE = 32780L;
    public static final long EVENT_DESCRIPTIONCHANGE = 32781L;
    public static final long EVENT_VALUE_CHANGE = 32782L;
    public static final long EVENT_PARENTCHANGE = 32783L;
    public static final long EVENT_HELPCHANGE = 32784L;
    public static final long EVENT_DEFACTIONCHANGE = 32785L;
    public static final long EVENT_ACCELERATORCHANGE = 32786L;
    public static final long EVENT_SELECTION = 32774L;
    public static final long EVENT_SELECTION_ADD = 32775L;
    public static final long EVENT_SELECTION_REMOVE = 32776L;
    public static final long EVENT_SELECTION_WITHIN = 32777L;
    public static final long EVENT_ALERT = 2L;
    public static final long EVENT_FOREGROUND = 3L;
    public static final long EVENT_MENUSTART = 4L;
    public static final long EVENT_MENUEND = 5L;
    public static final long EVENT_MENUPOPUPSTART = 6L;
    public static final long EVENT_MENUPOPUPEND = 7L;
    public static final long EVENT_CAPTURESTART = 8L;
    public static final long EVENT_CAPTUREEND = 9L;
    public static final long EVENT_MOVESIZESTART = 10L;
    public static final long EVENT_MOVESIZEEND = 11L;
    public static final long EVENT_CONTEXTHELPSTART = 12L;
    public static final long EVENT_CONTEXTHELPEND = 13L;
    public static final long EVENT_DRAGDROPSTART = 14L;
    public static final long EVENT_DRAGDROPEND = 15L;
    public static final long EVENT_DIALOGSTART = 16L;
    public static final long EVENT_DIALOGEND = 17L;
    public static final long EVENT_SCROLLINGSTART = 18L;
    public static final long EVENT_SCROLLINGEND = 19L;
    public static final long EVENT_MINIMIZESTART = 22L;
    public static final long EVENT_MINIMIZEEND = 23L;
    public static final long EVENT_ATK_PROPERTY_CHANGE = 256L;
    public static final long EVENT_ATK_SELECTION_CHANGE = 257L;
    public static final long EVENT_ATK_TEXT_CHANGE = 258L;
    public static final long EVENT_ATK_TEXT_SELECTION_CHANGE = 259L;
    public static final long EVENT_ATK_TEXT_CARET_MOVE = 260L;
    public static final long EVENT_ATK_VISIBLE_DATA_CHANGE = 261L;
    public static final long EVENT_ATK_TABLE_MODEL_CHANGE = 272L;
    public static final long EVENT_ATK_TABLE_ROW_INSERT = 273L;
    public static final long EVENT_ATK_TABLE_ROW_DELETE = 274L;
    public static final long EVENT_ATK_TABLE_ROW_REORDER = 275L;
    public static final long EVENT_ATK_TABLE_COLUMN_INSERT = 276L;
    public static final long EVENT_ATK_TABLE_COLUMN_DELETE = 277L;
    public static final long EVENT_ATK_TABLE_COLUMN_REORDER = 278L;
    public static final long EVENT_ATK_LINK_SELECTED = 279L;
    public static final long EVENT_ATK_WINDOW_ACTIVATE = 280L;
    public static final long EVENT_ATK_WINDOW_CREATE = 281L;
    public static final long EVENT_ATK_WINDOW_DEACTIVATE = 288L;
    public static final long EVENT_ATK_WINDOW_DESTROY = 289L;
    public static final long EVENT_ATK_WINDOW_MAXIMIZE = 290L;
    public static final long EVENT_ATK_WINDOW_MINIMIZE = 291L;
    public static final long EVENT_ATK_WINDOW_RESIZE = 292L;
    public static final long EVENT_ATK_WINDOW_RESTORE = 293L;
    
    long getEventType();
    
    nsIAccessible getAccessible();
    
    nsIAccessibleDocument getAccessibleDocument();
    
    nsIDOMNode getDOMNode();
}
