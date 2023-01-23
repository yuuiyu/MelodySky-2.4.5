//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAccessNode extends nsISupports
{
    public static final String NS_IACCESSNODE_IID = "{46820f9b-3088-4046-ab0f-56fdacdc7a82}";
    
    nsIDOMNode getDOMNode();
    
    int getNumChildren();
    
    nsIAccessNode getChildNodeAt(final int p0);
    
    nsIAccessNode getParentNode();
    
    nsIAccessNode getFirstChildNode();
    
    nsIAccessNode getLastChildNode();
    
    nsIAccessNode getPreviousSiblingNode();
    
    nsIAccessNode getNextSiblingNode();
    
    nsIAccessibleDocument getAccessibleDocument();
    
    String getInnerHTML();
    
    String getComputedStyleValue(final String p0, final String p1);
}
