//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULTreeElement extends nsIDOMXULElement
{
    public static final String NS_IDOMXULTREEELEMENT_IID = "{1f8111b2-d44d-4d11-845a-a70ae06b7d04}";
    
    nsITreeColumns getColumns();
    
    nsITreeView getView();
    
    void setView(final nsITreeView p0);
    
    nsIDOMElement getBody();
    
    boolean getEditable();
    
    void setEditable(final boolean p0);
    
    nsIDOMXULTextBoxElement getInputField();
}
