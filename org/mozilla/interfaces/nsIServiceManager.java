//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIServiceManager extends nsISupports
{
    public static final String NS_ISERVICEMANAGER_IID = "{8bb35ed9-e332-462d-9155-4a002ab5c958}";
    
    nsISupports getService(final String p0, final String p1);
    
    nsISupports getServiceByContractID(final String p0, final String p1);
    
    boolean isServiceInstantiated(final String p0, final String p1);
    
    boolean isServiceInstantiatedByContractID(final String p0, final String p1);
}
