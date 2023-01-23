//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUpdatePatch extends nsISupports
{
    public static final String NS_IUPDATEPATCH_IID = "{56863a67-bd69-42de-9f40-583e625b457d}";
    
    String getType();
    
    void setType(final String p0);
    
    String getURL();
    
    void setURL(final String p0);
    
    String getHashFunction();
    
    void setHashFunction(final String p0);
    
    String getHashValue();
    
    void setHashValue(final String p0);
    
    long getSize();
    
    void setSize(final long p0);
    
    String getState();
    
    void setState(final String p0);
    
    boolean getSelected();
    
    void setSelected(final boolean p0);
    
    nsIDOMElement serialize(final nsIDOMDocument p0);
}
