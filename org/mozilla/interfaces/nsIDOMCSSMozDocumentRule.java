//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCSSMozDocumentRule extends nsIDOMCSSRule
{
    public static final String NS_IDOMCSSMOZDOCUMENTRULE_IID = "{4eb9adac-afaf-4b8a-8640-7340863c1587}";
    
    nsIDOMCSSRuleList getCssRules();
    
    long insertRule(final String p0, final long p1);
    
    void deleteRule(final long p0);
}
