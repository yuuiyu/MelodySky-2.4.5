//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIContext extends jsdIEphemeral
{
    public static final String JSDICONTEXT_IID = "{a2dd25a4-1dd1-11b2-bda6-ed525acd4c35}";
    public static final int OPT_STRICT = 1;
    public static final int OPT_WERR = 2;
    public static final int OPT_VAROBJFIX = 4;
    public static final int OPT_ISUPPORTS = 8;
    
    long getOptions();
    
    void setOptions(final long p0);
    
    int getVersion();
    
    void setVersion(final int p0);
    
    long getTag();
    
    nsISupports getPrivateData();
    
    nsISupports getWrappedContext();
    
    jsdIValue getGlobalObject();
    
    boolean getScriptsEnabled();
    
    void setScriptsEnabled(final boolean p0);
}
