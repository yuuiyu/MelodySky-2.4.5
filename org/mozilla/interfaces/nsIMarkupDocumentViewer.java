//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMarkupDocumentViewer extends nsISupports
{
    public static final String NS_IMARKUPDOCUMENTVIEWER_IID = "{18cbdb18-3917-42fd-9c4a-0b2112d41a6d}";
    
    void scrollToNode(final nsIDOMNode p0);
    
    float getTextZoom();
    
    void setTextZoom(final float p0);
    
    boolean getAuthorStyleDisabled();
    
    void setAuthorStyleDisabled(final boolean p0);
    
    String getDefaultCharacterSet();
    
    void setDefaultCharacterSet(final String p0);
    
    String getForceCharacterSet();
    
    void setForceCharacterSet(final String p0);
    
    String getHintCharacterSet();
    
    void setHintCharacterSet(final String p0);
    
    int getHintCharacterSetSource();
    
    void setHintCharacterSetSource(final int p0);
    
    String getPrevDocCharacterSet();
    
    void setPrevDocCharacterSet(final String p0);
    
    void sizeToContent();
    
    byte getBidiTextDirection();
    
    void setBidiTextDirection(final byte p0);
    
    byte getBidiTextType();
    
    void setBidiTextType(final byte p0);
    
    byte getBidiControlsTextMode();
    
    void setBidiControlsTextMode(final byte p0);
    
    byte getBidiNumeral();
    
    void setBidiNumeral(final byte p0);
    
    byte getBidiSupport();
    
    void setBidiSupport(final byte p0);
    
    byte getBidiCharacterSet();
    
    void setBidiCharacterSet(final byte p0);
    
    long getBidiOptions();
    
    void setBidiOptions(final long p0);
}
