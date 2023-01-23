//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMultiPartChannel extends nsISupports
{
    public static final String NS_IMULTIPARTCHANNEL_IID = "{ba78db7b-b88c-4b76-baf9-3c2296a585ae}";
    
    nsIChannel getBaseChannel();
    
    String getContentDisposition();
    
    void setContentDisposition(final String p0);
    
    long getPartID();
    
    boolean getIsLastPart();
}
