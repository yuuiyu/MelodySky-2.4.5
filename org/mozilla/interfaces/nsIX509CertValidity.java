//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIX509CertValidity extends nsISupports
{
    public static final String NS_IX509CERTVALIDITY_IID = "{e701dfd8-1dd1-11b2-a172-ffa6cc6156ad}";
    
    double getNotBefore();
    
    String getNotBeforeLocalTime();
    
    String getNotBeforeLocalDay();
    
    String getNotBeforeGMT();
    
    double getNotAfter();
    
    String getNotAfterLocalTime();
    
    String getNotAfterLocalDay();
    
    String getNotAfterGMT();
}
