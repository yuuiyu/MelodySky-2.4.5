//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXPathEvaluator extends nsISupports
{
    public static final String NS_IDOMXPATHEVALUATOR_IID = "{75506f8a-b504-11d5-a7f2-ca108ab8b6fc}";
    
    nsIDOMXPathExpression createExpression(final String p0, final nsIDOMXPathNSResolver p1);
    
    nsIDOMXPathNSResolver createNSResolver(final nsIDOMNode p0);
    
    nsISupports evaluate(final String p0, final nsIDOMNode p1, final nsIDOMXPathNSResolver p2, final int p3, final nsISupports p4);
}
