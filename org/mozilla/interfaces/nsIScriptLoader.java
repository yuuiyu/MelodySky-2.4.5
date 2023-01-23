//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptLoader extends nsISupports
{
    public static final String NS_ISCRIPTLOADER_IID = "{339a4eb5-dac6-4034-8c43-f4f8c645ce57}";
    
    void init(final nsISupports p0);
    
    void dropDocumentReference();
    
    void addObserver(final nsIScriptLoaderObserver p0);
    
    void removeObserver(final nsIScriptLoaderObserver p0);
    
    void processScriptElement(final nsISupports p0, final nsIScriptLoaderObserver p1);
    
    nsISupports getCurrentScript();
    
    boolean getEnabled();
    
    void setEnabled(final boolean p0);
}
