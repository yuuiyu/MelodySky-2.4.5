//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgIDecoderObserver_MOZILLA_1_8_BRANCH extends nsISupports
{
    public static final String IMGIDECODEROBSERVER_MOZILLA_1_8_BRANCH_IID = "{d3ab9070-b5d2-410f-977d-36b1788de1e5}";
    
    void onStartRequest(final imgIRequest p0);
    
    void onStopRequest(final imgIRequest p0, final boolean p1);
}
