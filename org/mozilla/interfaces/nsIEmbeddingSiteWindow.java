//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEmbeddingSiteWindow extends nsISupports
{
    public static final String NS_IEMBEDDINGSITEWINDOW_IID = "{3e5432cd-9568-4bd1-8cbe-d50aba110743}";
    public static final long DIM_FLAGS_POSITION = 1L;
    public static final long DIM_FLAGS_SIZE_INNER = 2L;
    public static final long DIM_FLAGS_SIZE_OUTER = 4L;
    
    void setDimensions(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    void getDimensions(final long p0, final int[] p1, final int[] p2, final int[] p3, final int[] p4);
    
    void setFocus();
    
    boolean getVisibility();
    
    void setVisibility(final boolean p0);
    
    String getTitle();
    
    void setTitle(final String p0);
    
    long getSiteWindow();
}
