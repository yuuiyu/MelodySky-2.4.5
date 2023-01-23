//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocShell_MOZILLA_1_8_BRANCH extends nsISupports
{
    public static final String NS_IDOCSHELL_MOZILLA_1_8_BRANCH_IID = "{45988a14-b240-4d07-ae64-50ecca26e6d8}";
    
    nsIDOMStorage getSessionStorageForURI(final nsIURI p0);
    
    void addSessionStorage(final String p0, final nsIDOMStorage p1);
    
    nsIChannel getCurrentDocumentChannel();
}
