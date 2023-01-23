//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGElement extends nsIDOMElement
{
    public static final String NS_IDOMSVGELEMENT_IID = "{e0be7cbb-81c1-4663-8f95-109d96a60b6b}";
    
    String getId();
    
    void setId(final String p0);
    
    nsIDOMSVGSVGElement getOwnerSVGElement();
    
    nsIDOMSVGElement getViewportElement();
}
