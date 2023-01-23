//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXTFElementWrapper extends nsISupports
{
    public static final String NS_IXTFELEMENTWRAPPER_IID = "{444d0276-3302-4d35-a74e-25c4e9c483c9}";
    
    nsIDOMElement getElementNode();
    
    nsIDOMElement getDocumentFrameElement();
    
    long getNotificationMask();
    
    void setNotificationMask(final long p0);
    
    void setIntrinsicState(final int p0);
}
