//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedEntry extends nsIFeedContainer
{
    public static final String NS_IFEEDENTRY_IID = "{31bfd5b4-8ff5-4bfd-a8cb-b3dfbd4f0a5b}";
    
    nsIFeedTextConstruct getSummary();
    
    void setSummary(final nsIFeedTextConstruct p0);
    
    String getPublished();
    
    void setPublished(final String p0);
    
    nsIFeedTextConstruct getContent();
    
    void setContent(final nsIFeedTextConstruct p0);
    
    nsIArray getEnclosures();
    
    void setEnclosures(final nsIArray p0);
    
    nsIArray getMediaContent();
    
    void setMediaContent(final nsIArray p0);
}
