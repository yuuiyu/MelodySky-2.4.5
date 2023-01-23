//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface inIDOMUtils extends nsISupports
{
    public static final String INIDOMUTILS_IID = "{78fd16c2-bdfb-4b1d-8738-d536d0a8f430}";
    
    nsISupportsArray getCSSStyleRules(final nsIDOMElement p0);
    
    long getRuleLine(final nsIDOMCSSStyleRule p0);
    
    boolean isIgnorableWhitespace(final nsIDOMCharacterData p0);
    
    nsIDOMNode getParentForNode(final nsIDOMNode p0, final boolean p1);
    
    nsIArray getBindingURLs(final nsIDOMElement p0);
    
    int getContentState(final nsIDOMElement p0);
    
    void setContentState(final nsIDOMElement p0, final int p1);
}
