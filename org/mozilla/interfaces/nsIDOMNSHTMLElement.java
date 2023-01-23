//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSHTMLElement extends nsISupports
{
    public static final String NS_IDOMNSHTMLELEMENT_IID = "{da83b2ec-8264-4410-8496-ada3acd2ae42}";
    
    int getOffsetTop();
    
    int getOffsetLeft();
    
    int getOffsetWidth();
    
    int getOffsetHeight();
    
    nsIDOMElement getOffsetParent();
    
    String getInnerHTML();
    
    void setInnerHTML(final String p0);
    
    int getScrollTop();
    
    void setScrollTop(final int p0);
    
    int getScrollLeft();
    
    void setScrollLeft(final int p0);
    
    int getScrollHeight();
    
    int getScrollWidth();
    
    int getClientHeight();
    
    int getClientWidth();
    
    int getTabIndex();
    
    void setTabIndex(final int p0);
    
    void blur();
    
    void focus();
    
    void scrollIntoView(final boolean p0);
}
