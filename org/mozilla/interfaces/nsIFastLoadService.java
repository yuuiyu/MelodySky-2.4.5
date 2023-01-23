//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFastLoadService extends nsISupports
{
    public static final String NS_IFASTLOADSERVICE_IID = "{759e475e-0c23-4dbf-b1b8-78c9369e3072}";
    public static final int NS_FASTLOAD_READ = 1;
    public static final int NS_FASTLOAD_WRITE = 2;
    
    nsIFile newFastLoadFile(final String p0);
    
    nsIObjectInputStream newInputStream(final nsIInputStream p0);
    
    nsIObjectOutputStream newOutputStream(final nsIOutputStream p0);
    
    nsIObjectInputStream getInputStream();
    
    void setInputStream(final nsIObjectInputStream p0);
    
    nsIObjectOutputStream getOutputStream();
    
    void setOutputStream(final nsIObjectOutputStream p0);
    
    nsIFastLoadFileIO getFileIO();
    
    void setFileIO(final nsIFastLoadFileIO p0);
    
    int getDirection();
    
    void startMuxedDocument(final nsISupports p0, final String p1, final int p2);
    
    nsISupports selectMuxedDocument(final nsISupports p0);
    
    void endMuxedDocument(final nsISupports p0);
    
    void addDependency(final nsIFile p0);
    
    long computeChecksum(final nsIFile p0, final nsIFastLoadReadControl p1);
    
    void cacheChecksum(final nsIFile p0, final nsIObjectOutputStream p1);
    
    boolean hasMuxedDocument(final String p0);
}
