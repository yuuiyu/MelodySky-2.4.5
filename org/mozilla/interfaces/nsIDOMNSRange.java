//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSRange extends nsISupports
{
    public static final String NS_IDOMNSRANGE_IID = "{a6cf90f2-15b3-11d2-932e-00805f8add32}";
    public static final int NODE_BEFORE = 0;
    public static final int NODE_AFTER = 1;
    public static final int NODE_BEFORE_AND_AFTER = 2;
    public static final int NODE_INSIDE = 3;
    
    nsIDOMDocumentFragment createContextualFragment(final String p0);
    
    boolean isPointInRange(final nsIDOMNode p0, final int p1);
    
    short comparePoint(final nsIDOMNode p0, final int p1);
    
    boolean intersectsNode(final nsIDOMNode p0);
    
    int compareNode(final nsIDOMNode p0);
    
    void nSDetach();
}
