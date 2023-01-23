//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDNSListener extends nsISupports
{
    public static final String NS_IDNSLISTENER_IID = "{41466a9f-f027-487d-a96c-af39e629b8d2}";
    
    void onLookupComplete(final nsICancelable p0, final nsIDNSRecord p1, final long p2);
}
