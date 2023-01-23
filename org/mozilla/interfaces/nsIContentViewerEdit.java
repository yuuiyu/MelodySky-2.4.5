//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContentViewerEdit extends nsISupports
{
    public static final String NS_ICONTENTVIEWEREDIT_IID = "{1691a02f-53b2-4cb8-8769-48e7efc908b8}";
    public static final int COPY_IMAGE_TEXT = 1;
    public static final int COPY_IMAGE_HTML = 2;
    public static final int COPY_IMAGE_DATA = 4;
    public static final int COPY_IMAGE_ALL = -1;
    
    void search();
    
    boolean getSearchable();
    
    void clearSelection();
    
    void selectAll();
    
    void copySelection();
    
    boolean getCopyable();
    
    void copyLinkLocation();
    
    boolean getInLink();
    
    void copyImage(final int p0);
    
    boolean getInImage();
    
    void cutSelection();
    
    boolean getCutable();
    
    void paste();
    
    boolean getPasteable();
    
    String getContents(final String p0, final boolean p1);
    
    boolean getCanGetContents();
}
