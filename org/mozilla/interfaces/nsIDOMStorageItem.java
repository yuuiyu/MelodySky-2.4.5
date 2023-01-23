//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMStorageItem extends nsISupports
{
    public static final String NS_IDOMSTORAGEITEM_IID = "{0cc37c78-4c5f-48e1-adfc-7480b8fe9dc4}";
    
    boolean getSecure();
    
    void setSecure(final boolean p0);
    
    String getValue();
    
    void setValue(final String p0);
}
