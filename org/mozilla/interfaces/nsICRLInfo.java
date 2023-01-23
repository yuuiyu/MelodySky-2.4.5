//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICRLInfo extends nsISupports
{
    public static final String NS_ICRLINFO_IID = "{c185d920-4a3e-11d5-ba27-00108303b117}";
    
    String getOrganization();
    
    String getOrganizationalUnit();
    
    double getLastUpdate();
    
    double getNextUpdate();
    
    String getLastUpdateLocale();
    
    String getNextUpdateLocale();
    
    String getNameInDb();
    
    String getLastFetchURL();
}
