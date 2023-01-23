//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIURIRefObject extends nsISupports
{
    public static final String NS_IURIREFOBJECT_IID = "{2226927e-1dd2-11b2-b57f-faab47288563}";
    
    nsIDOMNode getNode();
    
    void setNode(final nsIDOMNode p0);
    
    void reset();
    
    String getNextURI();
    
    void rewriteAllURIs(final String p0, final String p1, final boolean p2);
}
