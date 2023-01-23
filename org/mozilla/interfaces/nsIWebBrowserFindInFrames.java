//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebBrowserFindInFrames extends nsISupports
{
    public static final String NS_IWEBBROWSERFINDINFRAMES_IID = "{e0f5d182-34bc-11d5-be5b-b760676c6ebc}";
    
    nsIDOMWindow getCurrentSearchFrame();
    
    void setCurrentSearchFrame(final nsIDOMWindow p0);
    
    nsIDOMWindow getRootSearchFrame();
    
    void setRootSearchFrame(final nsIDOMWindow p0);
    
    boolean getSearchSubframes();
    
    void setSearchSubframes(final boolean p0);
    
    boolean getSearchParentFrames();
    
    void setSearchParentFrames(final boolean p0);
}
