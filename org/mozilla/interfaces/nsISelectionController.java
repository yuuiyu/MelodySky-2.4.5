//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISelectionController extends nsISelectionDisplay
{
    public static final String NS_ISELECTIONCONTROLLER_IID = "{93aaa4a9-b78e-42eb-9d67-5de77ee2f54b}";
    public static final short SELECTION_NONE = 0;
    public static final short SELECTION_NORMAL = 1;
    public static final short SELECTION_SPELLCHECK = 2;
    public static final short SELECTION_IME_RAWINPUT = 4;
    public static final short SELECTION_IME_SELECTEDRAWTEXT = 8;
    public static final short SELECTION_IME_CONVERTEDTEXT = 16;
    public static final short SELECTION_IME_SELECTEDCONVERTEDTEXT = 32;
    public static final short SELECTION_ACCESSIBILITY = 64;
    public static final short NUM_SELECTIONTYPES = 8;
    public static final short SELECTION_ANCHOR_REGION = 0;
    public static final short SELECTION_FOCUS_REGION = 1;
    public static final short NUM_SELECTION_REGIONS = 2;
    public static final short SELECTION_OFF = 0;
    public static final short SELECTION_HIDDEN = 1;
    public static final short SELECTION_ON = 2;
    public static final short SELECTION_DISABLED = 3;
    public static final short SELECTION_ATTENTION = 4;
    
    void setDisplaySelection(final short p0);
    
    short getDisplaySelection();
    
    nsISelection getSelection(final short p0);
    
    void scrollSelectionIntoView(final short p0, final short p1, final boolean p2);
    
    void repaintSelection(final short p0);
    
    void setCaretEnabled(final boolean p0);
    
    void setCaretReadOnly(final boolean p0);
    
    boolean getCaretEnabled();
    
    void setCaretVisibilityDuringSelection(final boolean p0);
    
    void characterMove(final boolean p0, final boolean p1);
    
    void wordMove(final boolean p0, final boolean p1);
    
    void lineMove(final boolean p0, final boolean p1);
    
    void intraLineMove(final boolean p0, final boolean p1);
    
    void pageMove(final boolean p0, final boolean p1);
    
    void completeScroll(final boolean p0);
    
    void completeMove(final boolean p0, final boolean p1);
    
    void scrollPage(final boolean p0);
    
    void scrollLine(final boolean p0);
    
    void scrollHorizontal(final boolean p0);
    
    void selectAll();
    
    boolean checkVisibility(final nsIDOMNode p0, final short p1, final short p2);
}
