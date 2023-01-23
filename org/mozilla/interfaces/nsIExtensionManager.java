//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIExtensionManager extends nsISupports
{
    public static final String NS_IEXTENSIONMANAGER_IID = "{a3f5396c-a6e8-414a-8fbc-c8d831746328}";
    
    boolean start(final nsICommandLine p0);
    
    boolean checkForMismatches();
    
    void handleCommandLineArgs(final nsICommandLine p0);
    
    nsIInstallLocation getInstallLocation(final String p0);
    
    nsISimpleEnumerator getInstallLocations();
    
    void installItemFromFile(final nsIFile p0, final String p1);
    
    void uninstallItem(final String p0);
    
    void enableItem(final String p0);
    
    void disableItem(final String p0);
    
    void update(final nsIUpdateItem[] p0, final long p1, final long p2, final nsIAddonUpdateCheckListener p3);
    
    nsIUpdateItem getItemForID(final String p0);
    
    nsIUpdateItem[] getItemList(final long p0, final long[] p1);
    
    nsIUpdateItem[] getIncompatibleItemList(final String p0, final String p1, final long p2, final boolean p3, final long[] p4);
    
    nsIRDFDataSource getDatasource();
    
    void addDownloads(final nsIUpdateItem[] p0, final long p1, final boolean p2);
    
    void removeDownload(final String p0);
    
    int addUpdateListener(final nsIAddonUpdateListener p0);
    
    void removeUpdateListenerAt(final int p0);
    
    void moveToIndexOf(final String p0, final String p1);
}
