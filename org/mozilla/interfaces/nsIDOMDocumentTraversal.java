//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDocumentTraversal extends nsISupports
{
    public static final String NS_IDOMDOCUMENTTRAVERSAL_IID = "{13f236c0-47f8-11d5-b6a3-009027446e84}";
    
    nsIDOMNodeIterator createNodeIterator(final nsIDOMNode p0, final long p1, final nsIDOMNodeFilter p2, final boolean p3);
    
    nsIDOMTreeWalker createTreeWalker(final nsIDOMNode p0, final long p1, final nsIDOMNodeFilter p2, final boolean p3);
}
