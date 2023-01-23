//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFilePicker extends nsISupports
{
    public static final String NS_IFILEPICKER_IID = "{80faf095-c807-4558-a2cc-185ed70754ea}";
    public static final short modeOpen = 0;
    public static final short modeSave = 1;
    public static final short modeGetFolder = 2;
    public static final short modeOpenMultiple = 3;
    public static final short returnOK = 0;
    public static final short returnCancel = 1;
    public static final short returnReplace = 2;
    public static final int filterAll = 1;
    public static final int filterHTML = 2;
    public static final int filterText = 4;
    public static final int filterImages = 8;
    public static final int filterXML = 16;
    public static final int filterXUL = 32;
    public static final int filterApps = 64;
    
    void init(final nsIDOMWindow p0, final String p1, final short p2);
    
    void appendFilters(final int p0);
    
    void appendFilter(final String p0, final String p1);
    
    String getDefaultString();
    
    void setDefaultString(final String p0);
    
    String getDefaultExtension();
    
    void setDefaultExtension(final String p0);
    
    int getFilterIndex();
    
    void setFilterIndex(final int p0);
    
    nsILocalFile getDisplayDirectory();
    
    void setDisplayDirectory(final nsILocalFile p0);
    
    nsILocalFile getFile();
    
    nsIFileURL getFileURL();
    
    nsISimpleEnumerator getFiles();
    
    short show();
}
