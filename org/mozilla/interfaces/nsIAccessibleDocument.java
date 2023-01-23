//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAccessibleDocument extends nsISupports
{
    public static final String NS_IACCESSIBLEDOCUMENT_IID = "{8781fc88-355f-4439-881f-6504a0a1ceb6}";
    
    String getURL();
    
    String getTitle();
    
    String getMimeType();
    
    String getDocType();
    
    boolean getIsEditable();
    
    nsIDOMDocument getDocument();
    
    nsIDOMWindow getWindow();
    
    nsIAccessible getCaretAccessible();
    
    String getNameSpaceURIForID(final short p0);
    
    nsIAccessible getAccessibleInParentChain(final nsIDOMNode p0);
}
