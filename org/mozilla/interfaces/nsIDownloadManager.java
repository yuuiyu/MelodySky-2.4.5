//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDownloadManager extends nsISupports
{
    public static final String NS_IDOWNLOADMANAGER_IID = "{1f280341-30f4-4009-bb0d-a78f2936d1fb}";
    public static final short DOWNLOAD_NOTSTARTED = -1;
    public static final short DOWNLOAD_DOWNLOADING = 0;
    public static final short DOWNLOAD_FINISHED = 1;
    public static final short DOWNLOAD_FAILED = 2;
    public static final short DOWNLOAD_CANCELED = 3;
    public static final short DOWNLOAD_PAUSED = 4;
    public static final short DOWNLOAD_TYPE_DOWNLOAD = 0;
    
    nsIDownload addDownload(final short p0, final nsIURI p1, final nsIURI p2, final String p3, final String p4, final nsIMIMEInfo p5, final double p6, final nsILocalFile p7, final nsICancelable p8);
    
    nsIDownload getDownload(final String p0);
    
    void cancelDownload(final String p0);
    
    void removeDownload(final String p0);
    
    void pauseDownload(final String p0);
    
    void resumeDownload(final String p0);
    
    void open(final nsIDOMWindow p0, final String p1);
    
    nsIDownloadProgressListener getListener();
    
    void setListener(final nsIDownloadProgressListener p0);
    
    void startBatchUpdate();
    
    void endBatchUpdate();
    
    boolean getCanCleanUp();
    
    void cleanUp();
    
    int getActiveDownloadCount();
    
    nsISupportsArray getActiveDownloads();
    
    void saveState();
    
    void flush();
    
    nsIRDFDataSource getDatasource();
}
