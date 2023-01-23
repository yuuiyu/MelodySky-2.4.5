//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISAXAttributes extends nsISupports
{
    public static final String NS_ISAXATTRIBUTES_IID = "{e347005e-6cd0-11da-be43-001422106990}";
    
    int getIndexFromName(final String p0, final String p1);
    
    int getIndexFromQName(final String p0);
    
    int getLength();
    
    String getLocalName(final long p0);
    
    String getQName(final long p0);
    
    String getType(final long p0);
    
    String getTypeFromName(final String p0, final String p1);
    
    String getTypeFromQName(final String p0);
    
    String getURI(final long p0);
    
    String getValue(final long p0);
    
    String getValueFromName(final String p0, final String p1);
    
    String getValueFromQName(final String p0);
}
