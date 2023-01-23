//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContextMenuInfo extends nsISupports
{
    public static final String NS_ICONTEXTMENUINFO_IID = "{2f977d56-5485-11d4-87e2-0010a4e75ef2}";
    
    nsIDOMEvent getMouseEvent();
    
    nsIDOMNode getTargetNode();
    
    String getAssociatedLink();
    
    imgIContainer getImageContainer();
    
    nsIURI getImageSrc();
    
    imgIContainer getBackgroundImageContainer();
    
    nsIURI getBackgroundImageSrc();
}
