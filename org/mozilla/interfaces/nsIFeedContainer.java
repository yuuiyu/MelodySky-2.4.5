//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedContainer extends nsIFeedElementBase
{
    public static final String NS_IFEEDCONTAINER_IID = "{577a1b4c-b3d4-4c76-9cf8-753e6606114f}";
    
    String getId();
    
    void setId(final String p0);
    
    nsIWritablePropertyBag2 getFields();
    
    void setFields(final nsIWritablePropertyBag2 p0);
    
    nsIFeedTextConstruct getTitle();
    
    void setTitle(final nsIFeedTextConstruct p0);
    
    nsIURI getLink();
    
    void setLink(final nsIURI p0);
    
    nsIArray getLinks();
    
    void setLinks(final nsIArray p0);
    
    nsIArray getCategories();
    
    void setCategories(final nsIArray p0);
    
    nsIFeedTextConstruct getRights();
    
    void setRights(final nsIFeedTextConstruct p0);
    
    nsIArray getAuthors();
    
    void setAuthors(final nsIArray p0);
    
    nsIArray getContributors();
    
    void setContributors(final nsIArray p0);
    
    String getUpdated();
    
    void setUpdated(final String p0);
    
    void normalize();
}
