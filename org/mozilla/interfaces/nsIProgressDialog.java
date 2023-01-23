//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProgressDialog extends nsIDownload
{
    public static final String NS_IPROGRESSDIALOG_IID = "{20e790a2-76c6-462d-851a-22ab6cbbe48b}";
    
    void open(final nsIDOMWindow p0);
    
    boolean getCancelDownloadOnClose();
    
    void setCancelDownloadOnClose(final boolean p0);
    
    nsIObserver getObserver();
    
    void setObserver(final nsIObserver p0);
    
    nsIDOMWindow getDialog();
    
    void setDialog(final nsIDOMWindow p0);
}
