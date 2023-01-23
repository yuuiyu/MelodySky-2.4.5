//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgILoad extends nsISupports
{
    public static final String IMGILOAD_IID = "{e6273acc-1dd1-11b2-a08b-824ad1b1628d}";
    
    imgIContainer getImage();
    
    void setImage(final imgIContainer p0);
    
    boolean getIsMultiPartChannel();
}
