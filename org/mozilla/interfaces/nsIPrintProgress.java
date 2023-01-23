//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrintProgress extends nsIWebProgressListener
{
    public static final String NS_IPRINTPROGRESS_IID = "{7e46bc35-fb7d-4b45-ab35-82fd61015380}";
    
    void openProgressDialog(final nsIDOMWindowInternal p0, final String p1, final nsISupports p2, final nsIObserver p3, final boolean[] p4);
    
    void closeProgressDialog(final boolean p0);
    
    void registerListener(final nsIWebProgressListener p0);
    
    void unregisterListener(final nsIWebProgressListener p0);
    
    void doneIniting();
    
    nsIPrompt getPrompter();
    
    boolean getProcessCanceledByUser();
    
    void setProcessCanceledByUser(final boolean p0);
}
