//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface inICSSValueSearch extends inISearchProcess
{
    public static final String INICSSVALUESEARCH_IID = "{e0d39e48-1dd1-11b2-81bd-9a0c117f0736}";
    
    nsIDOMDocument getDocument();
    
    void setDocument(final nsIDOMDocument p0);
    
    String getBaseURL();
    
    void setBaseURL(final String p0);
    
    boolean getReturnRelativeURLs();
    
    void setReturnRelativeURLs(final boolean p0);
    
    boolean getNormalizeChromeURLs();
    
    void setNormalizeChromeURLs(final boolean p0);
    
    void addPropertyCriteria(final String p0);
    
    String getTextCriteria();
    
    void setTextCriteria(final String p0);
}
