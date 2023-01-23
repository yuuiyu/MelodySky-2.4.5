//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIComponentManagerObsolete extends nsISupports
{
    public static final String NS_ICOMPONENTMANAGEROBSOLETE_IID = "{8458a740-d5dc-11d2-92fb-00e09805570f}";
    public static final int NS_Startup = 0;
    public static final int NS_Script = 1;
    public static final int NS_Timer = 2;
    public static final int NS_Shutdown = 3;
    
    nsIFactory findFactory(final String p0);
    
    String cLSIDToContractID(final String p0, final String[] p1);
    
    String registryLocationForSpec(final nsIFile p0);
    
    nsIFile specForRegistryLocation(final String p0);
    
    void registerFactory(final String p0, final String p1, final String p2, final nsIFactory p3, final boolean p4);
    
    void registerComponent(final String p0, final String p1, final String p2, final String p3, final boolean p4, final boolean p5);
    
    void registerComponentWithType(final String p0, final String p1, final String p2, final nsIFile p3, final String p4, final boolean p5, final boolean p6, final String p7);
    
    void registerComponentSpec(final String p0, final String p1, final String p2, final nsIFile p3, final boolean p4, final boolean p5);
    
    void registerComponentLib(final String p0, final String p1, final String p2, final String p3, final boolean p4, final boolean p5);
    
    void unregisterFactory(final String p0, final nsIFactory p1);
    
    void unregisterComponent(final String p0, final String p1);
    
    void unregisterComponentSpec(final String p0, final nsIFile p1);
    
    void freeLibraries();
    
    void autoRegister(final int p0, final nsIFile p1);
    
    void autoRegisterComponent(final int p0, final nsIFile p1);
    
    void autoUnregisterComponent(final int p0, final nsIFile p1);
    
    boolean isRegistered(final String p0);
    
    nsIEnumerator enumerateCLSIDs();
    
    nsIEnumerator enumerateContractIDs();
}
