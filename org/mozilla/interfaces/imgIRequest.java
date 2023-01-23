//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgIRequest extends nsIRequest
{
    public static final String IMGIREQUEST_IID = "{ccf705f6-1dd1-11b2-82ef-e18eccf7f7ec}";
    public static final int STATUS_NONE = 0;
    public static final int STATUS_SIZE_AVAILABLE = 1;
    public static final int STATUS_LOAD_PARTIAL = 2;
    public static final int STATUS_LOAD_COMPLETE = 4;
    public static final int STATUS_ERROR = 8;
    public static final int STATUS_FRAME_COMPLETE = 16;
    
    imgIContainer getImage();
    
    long getImageStatus();
    
    nsIURI getURI();
    
    imgIDecoderObserver getDecoderObserver();
    
    String getMimeType();
    
    imgIRequest _clone(final imgIDecoderObserver p0);
}
