//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditActionListener extends nsISupports
{
    public static final String NS_IEDITACTIONLISTENER_IID = "{b22907b1-ee93-11d2-8d50-000064657374}";
    
    void willCreateNode(final String p0, final nsIDOMNode p1, final int p2);
    
    void didCreateNode(final String p0, final nsIDOMNode p1, final nsIDOMNode p2, final int p3, final long p4);
    
    void willInsertNode(final nsIDOMNode p0, final nsIDOMNode p1, final int p2);
    
    void didInsertNode(final nsIDOMNode p0, final nsIDOMNode p1, final int p2, final long p3);
    
    void willDeleteNode(final nsIDOMNode p0);
    
    void didDeleteNode(final nsIDOMNode p0, final long p1);
    
    void willSplitNode(final nsIDOMNode p0, final int p1);
    
    void didSplitNode(final nsIDOMNode p0, final int p1, final nsIDOMNode p2, final long p3);
    
    void willJoinNodes(final nsIDOMNode p0, final nsIDOMNode p1, final nsIDOMNode p2);
    
    void didJoinNodes(final nsIDOMNode p0, final nsIDOMNode p1, final nsIDOMNode p2, final long p3);
    
    void willInsertText(final nsIDOMCharacterData p0, final int p1, final String p2);
    
    void didInsertText(final nsIDOMCharacterData p0, final int p1, final String p2, final long p3);
    
    void willDeleteText(final nsIDOMCharacterData p0, final int p1, final int p2);
    
    void didDeleteText(final nsIDOMCharacterData p0, final int p1, final int p2, final long p3);
    
    void willDeleteSelection(final nsISelection p0);
    
    void didDeleteSelection(final nsISelection p0);
}
