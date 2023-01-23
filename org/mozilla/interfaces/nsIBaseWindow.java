//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBaseWindow extends nsISupports
{
    public static final String NS_IBASEWINDOW_IID = "{046bc8a0-8015-11d3-af70-00a024ffc08c}";
    
    void initWindow(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5);
    
    void create();
    
    void destroy();
    
    void setPosition(final int p0, final int p1);
    
    void getPosition(final int[] p0, final int[] p1);
    
    void setSize(final int p0, final int p1, final boolean p2);
    
    void getSize(final int[] p0, final int[] p1);
    
    void setPositionAndSize(final int p0, final int p1, final int p2, final int p3, final boolean p4);
    
    void getPositionAndSize(final int[] p0, final int[] p1, final int[] p2, final int[] p3);
    
    void repaint(final boolean p0);
    
    long getParentWidget();
    
    void setParentWidget(final long p0);
    
    long getParentNativeWindow();
    
    void setParentNativeWindow(final long p0);
    
    boolean getVisibility();
    
    void setVisibility(final boolean p0);
    
    boolean getEnabled();
    
    void setEnabled(final boolean p0);
    
    boolean getBlurSuppression();
    
    void setBlurSuppression(final boolean p0);
    
    long getMainWidget();
    
    void setFocus();
    
    String getTitle();
    
    void setTitle(final String p0);
}
