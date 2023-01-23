//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPKCS11ModuleDB extends nsISupports
{
    public static final String NS_IPKCS11MODULEDB_IID = "{ff9fbcd7-9517-4334-b97a-ceed78909974}";
    
    nsIPKCS11Module getInternal();
    
    nsIPKCS11Module getInternalFIPS();
    
    nsIPKCS11Module findModuleByName(final String p0);
    
    nsIPKCS11Slot findSlotByName(final String p0);
    
    nsIEnumerator listModules();
    
    boolean getCanToggleFIPS();
    
    void toggleFIPSMode();
    
    boolean getIsFIPSEnabled();
}
