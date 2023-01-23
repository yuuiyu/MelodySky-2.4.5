//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMTreeWalker extends nsISupports
{
    public static final String NS_IDOMTREEWALKER_IID = "{400af3ca-1dd2-11b2-a50a-887ecca2e63a}";
    
    nsIDOMNode getRoot();
    
    long getWhatToShow();
    
    nsIDOMNodeFilter getFilter();
    
    boolean getExpandEntityReferences();
    
    nsIDOMNode getCurrentNode();
    
    void setCurrentNode(final nsIDOMNode p0);
    
    nsIDOMNode parentNode();
    
    nsIDOMNode firstChild();
    
    nsIDOMNode lastChild();
    
    nsIDOMNode previousSibling();
    
    nsIDOMNode nextSibling();
    
    nsIDOMNode previousNode();
    
    nsIDOMNode nextNode();
}
