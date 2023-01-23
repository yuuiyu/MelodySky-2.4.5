//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPKCS11Module extends nsISupports
{
    public static final String NS_IPKCS11MODULE_IID = "{8a44bdf9-d1a5-4734-bd5a-34ed7fe564c2}";
    
    String getName();
    
    String getLibName();
    
    nsIPKCS11Slot findSlotByName(final String p0);
    
    nsIEnumerator listSlots();
}
