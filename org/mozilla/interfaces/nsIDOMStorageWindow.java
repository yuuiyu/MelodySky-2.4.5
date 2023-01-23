//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMStorageWindow extends nsISupports
{
    public static final String NS_IDOMSTORAGEWINDOW_IID = "{55e9c181-2476-47cf-97f8-efdaaf7b6f7a}";
    
    nsIDOMStorage getSessionStorage();
    
    nsIDOMStorageList getGlobalStorage();
}
