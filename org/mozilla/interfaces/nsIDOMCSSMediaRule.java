//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCSSMediaRule extends nsIDOMCSSRule
{
    public static final String NS_IDOMCSSMEDIARULE_IID = "{a6cf90bc-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMMediaList getMedia();
    
    nsIDOMCSSRuleList getCssRules();
    
    long insertRule(final String p0, final long p1);
    
    void deleteRule(final long p0);
}
