//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebBrowserPrint extends nsISupports
{
    public static final String NS_IWEBBROWSERPRINT_IID = "{9a7ca4b0-fbba-11d4-a869-00105a183419}";
    public static final short PRINTPREVIEW_GOTO_PAGENUM = 0;
    public static final short PRINTPREVIEW_PREV_PAGE = 1;
    public static final short PRINTPREVIEW_NEXT_PAGE = 2;
    public static final short PRINTPREVIEW_HOME = 3;
    public static final short PRINTPREVIEW_END = 4;
    
    nsIPrintSettings getGlobalPrintSettings();
    
    nsIPrintSettings getCurrentPrintSettings();
    
    nsIDOMWindow getCurrentChildDOMWindow();
    
    boolean getDoingPrint();
    
    boolean getDoingPrintPreview();
    
    boolean getIsFramesetDocument();
    
    boolean getIsFramesetFrameSelected();
    
    boolean getIsIFrameSelected();
    
    boolean getIsRangeSelection();
    
    int getPrintPreviewNumPages();
    
    void print(final nsIPrintSettings p0, final nsIWebProgressListener p1);
    
    void printPreview(final nsIPrintSettings p0, final nsIDOMWindow p1, final nsIWebProgressListener p2);
    
    void printPreviewNavigate(final short p0, final int p1);
    
    void cancel();
    
    String[] enumerateDocumentNames(final long[] p0);
    
    void exitPrintPreview();
}
