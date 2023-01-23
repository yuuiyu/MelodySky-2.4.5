//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgILoader extends nsISupports
{
    public static final String IMGILOADER_IID = "{a32826ff-9e56-4425-a811-97a8dba64ff5}";
    
    imgIRequest loadImage(final nsIURI p0, final nsIURI p1, final nsIURI p2, final nsILoadGroup p3, final imgIDecoderObserver p4, final nsISupports p5, final long p6, final nsISupports p7, final imgIRequest p8);
    
    imgIRequest loadImageWithChannel(final nsIChannel p0, final imgIDecoderObserver p1, final nsISupports p2, final nsIStreamListener[] p3);
    
    boolean supportImageWithMimeType(final String p0);
}
