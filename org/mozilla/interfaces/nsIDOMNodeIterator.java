//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNodeIterator extends nsISupports
{
    public static final String NS_IDOMNODEITERATOR_IID = "{354b5f02-1dd2-11b2-b053-b8c2997022a0}";
    
    nsIDOMNode getRoot();
    
    long getWhatToShow();
    
    nsIDOMNodeFilter getFilter();
    
    boolean getExpandEntityReferences();
    
    nsIDOMNode nextNode();
    
    nsIDOMNode previousNode();
    
    void detach();
}
