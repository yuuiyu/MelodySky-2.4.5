//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface inISearchProcess extends nsISupports
{
    public static final String INISEARCHPROCESS_IID = "{d5fa765b-2448-4686-b7c1-5ff13acb0fc9}";
    
    boolean getIsActive();
    
    int getResultCount();
    
    boolean getHoldResults();
    
    void setHoldResults(final boolean p0);
    
    void searchSync();
    
    void searchAsync(final inISearchObserver p0);
    
    void searchStop();
    
    boolean searchStep();
    
    String getStringResultAt(final int p0);
    
    int getIntResultAt(final int p0);
    
    long getUIntResultAt(final int p0);
}
