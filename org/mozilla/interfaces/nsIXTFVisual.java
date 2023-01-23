//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXTFVisual extends nsIXTFElement
{
    public static final String NS_IXTFVISUAL_IID = "{2ee5520b-6593-43c1-b660-4885939a6b68}";
    public static final long NOTIFY_DID_LAYOUT = 131072L;
    
    nsIDOMElement getVisualContent();
    
    nsIDOMElement getInsertionPoint();
    
    boolean getApplyDocumentStyleSheets();
    
    void didLayout();
}
