//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIImageLoadingContent extends imgIDecoderObserver
{
    public static final String NS_IIMAGELOADINGCONTENT_IID = "{da19c86d-08aa-421c-8c37-12ec2ba5a2c3}";
    public static final int UNKNOWN_REQUEST = -1;
    public static final int CURRENT_REQUEST = 0;
    public static final int PENDING_REQUEST = 1;
    
    boolean getLoadingEnabled();
    
    void setLoadingEnabled(final boolean p0);
    
    short getImageBlockingStatus();
    
    void addObserver(final imgIDecoderObserver p0);
    
    void removeObserver(final imgIDecoderObserver p0);
    
    imgIRequest getRequest(final int p0);
    
    int getRequestType(final imgIRequest p0);
    
    nsIURI getCurrentURI();
    
    nsIStreamListener loadImageWithChannel(final nsIChannel p0);
}
