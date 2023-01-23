//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableRegion extends nsISupports
{
    public static final String NS_ISCRIPTABLEREGION_IID = "{82d8f400-5bde-11d3-b033-b27a62766bbc}";
    
    void init();
    
    void setToRegion(final nsIScriptableRegion p0);
    
    void setToRect(final int p0, final int p1, final int p2, final int p3);
    
    void intersectRegion(final nsIScriptableRegion p0);
    
    void intersectRect(final int p0, final int p1, final int p2, final int p3);
    
    void unionRegion(final nsIScriptableRegion p0);
    
    void unionRect(final int p0, final int p1, final int p2, final int p3);
    
    void subtractRegion(final nsIScriptableRegion p0);
    
    void subtractRect(final int p0, final int p1, final int p2, final int p3);
    
    boolean isEmpty();
    
    boolean isEqualRegion(final nsIScriptableRegion p0);
    
    void getBoundingBox(final int[] p0, final int[] p1, final int[] p2, final int[] p3);
    
    void offset(final int p0, final int p1);
    
    boolean containsRect(final int p0, final int p1, final int p2, final int p3);
}
