//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMMediaList extends nsISupports
{
    public static final String NS_IDOMMEDIALIST_IID = "{9b0c2ed7-111c-4824-adf9-ef0da6dad371}";
    
    String getMediaText();
    
    void setMediaText(final String p0);
    
    long getLength();
    
    String item(final long p0);
    
    void deleteMedium(final String p0);
    
    void appendMedium(final String p0);
}
