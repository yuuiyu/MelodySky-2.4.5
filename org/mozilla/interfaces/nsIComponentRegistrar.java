//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIComponentRegistrar extends nsISupports
{
    public static final String NS_ICOMPONENTREGISTRAR_IID = "{2417cbfe-65ad-48a6-b4b6-eb84db174392}";
    
    void autoRegister(final nsIFile p0);
    
    void autoUnregister(final nsIFile p0);
    
    void registerFactory(final String p0, final String p1, final String p2, final nsIFactory p3);
    
    void unregisterFactory(final String p0, final nsIFactory p1);
    
    void registerFactoryLocation(final String p0, final String p1, final String p2, final nsIFile p3, final String p4, final String p5);
    
    void unregisterFactoryLocation(final String p0, final nsIFile p1);
    
    boolean isCIDRegistered(final String p0);
    
    boolean isContractIDRegistered(final String p0);
    
    nsISimpleEnumerator enumerateCIDs();
    
    nsISimpleEnumerator enumerateContractIDs();
    
    String cIDToContractID(final String p0);
    
    String contractIDToCID(final String p0);
}
