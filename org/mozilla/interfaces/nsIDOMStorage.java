//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMStorage extends nsISupports
{
    public static final String NS_IDOMSTORAGE_IID = "{95cc1383-3b62-4b89-aaef-1004a513ef47}";
    
    long getLength();
    
    String key(final long p0);
    
    nsIDOMStorageItem getItem(final String p0);
    
    void setItem(final String p0, final String p1);
    
    void removeItem(final String p0);
}
