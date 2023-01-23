//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeed extends nsIFeedContainer
{
    public static final String NS_IFEED_IID = "{3b8aae33-80e2-4efa-99c8-a6c5b99f76ea}";
    public static final long TYPE_FEED = 0L;
    public static final long TYPE_AUDIO = 1L;
    public static final long TYPE_IMAGE = 2L;
    public static final long TYPE_VIDEO = 4L;
    
    nsIFeedTextConstruct getSubtitle();
    
    void setSubtitle(final nsIFeedTextConstruct p0);
    
    long getType();
    
    int getEnclosureCount();
    
    void setEnclosureCount(final int p0);
    
    nsIArray getItems();
    
    void setItems(final nsIArray p0);
    
    nsIWritablePropertyBag2 getCloud();
    
    void setCloud(final nsIWritablePropertyBag2 p0);
    
    nsIFeedGenerator getGenerator();
    
    void setGenerator(final nsIFeedGenerator p0);
    
    nsIWritablePropertyBag2 getImage();
    
    void setImage(final nsIWritablePropertyBag2 p0);
    
    nsIWritablePropertyBag2 getTextInput();
    
    void setTextInput(final nsIWritablePropertyBag2 p0);
    
    nsIArray getSkipDays();
    
    void setSkipDays(final nsIArray p0);
    
    nsIArray getSkipHours();
    
    void setSkipHours(final nsIArray p0);
}
