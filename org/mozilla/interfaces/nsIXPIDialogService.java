//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXPIDialogService extends nsISupports
{
    public static final String NS_IXPIDIALOGSERVICE_IID = "{8cdd8baa-1dd2-11b2-909a-f0178da5c5ff}";
    
    boolean confirmInstall(final nsIDOMWindow p0, final String[] p1, final long p2);
    
    void openProgressDialog(final String[] p0, final long p1, final nsIObserver p2);
}
