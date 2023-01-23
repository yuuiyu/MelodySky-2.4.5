//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICommandParams extends nsISupports
{
    public static final String NS_ICOMMANDPARAMS_IID = "{83f892cf-7ed3-490e-967a-62640f3158e1}";
    public static final short eNoType = 0;
    public static final short eBooleanType = 1;
    public static final short eLongType = 2;
    public static final short eDoubleType = 3;
    public static final short eWStringType = 4;
    public static final short eISupportsType = 5;
    public static final short eStringType = 6;
    
    short getValueType(final String p0);
    
    boolean getBooleanValue(final String p0);
    
    int getLongValue(final String p0);
    
    double getDoubleValue(final String p0);
    
    String getStringValue(final String p0);
    
    String getCStringValue(final String p0);
    
    nsISupports getISupportsValue(final String p0);
    
    void setBooleanValue(final String p0, final boolean p1);
    
    void setLongValue(final String p0, final int p1);
    
    void setDoubleValue(final String p0, final double p1);
    
    void setStringValue(final String p0, final String p1);
    
    void setCStringValue(final String p0, final String p1);
    
    void setISupportsValue(final String p0, final nsISupports p1);
    
    void removeValue(final String p0);
    
    boolean hasMoreElements();
    
    void first();
    
    String getNext();
}
