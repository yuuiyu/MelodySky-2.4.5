//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPService extends nsISupports
{
    public static final String NS_ISOAPSERVICE_IID = "{9927fa40-1dd1-11b2-a8d1-857ad21b872c}";
    
    nsISupports getConfiguration();
    
    void setConfiguration(final nsISupports p0);
    
    boolean process(final nsISOAPMessage p0, final nsISOAPResponseListener p1);
}
