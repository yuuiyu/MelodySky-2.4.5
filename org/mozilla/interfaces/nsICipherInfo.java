//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICipherInfo extends nsISupports
{
    public static final String NS_ICIPHERINFO_IID = "{028e2b2a-1f0b-43a4-a1a7-365d2d7f35d0}";
    
    String getLongName();
    
    boolean getIsSSL2();
    
    boolean getIsFIPS();
    
    boolean getIsExportable();
    
    boolean getNonStandard();
    
    String getSymCipherName();
    
    String getAuthAlgorithmName();
    
    String getKeaTypeName();
    
    String getMacAlgorithmName();
    
    int getEffectiveKeyBits();
}
