//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAccessibleRetrieval extends nsISupports
{
    public static final String NS_IACCESSIBLERETRIEVAL_IID = "{663ca4a8-d219-4000-925d-d8f66406b626}";
    
    nsIAccessible getAccessibleFor(final nsIDOMNode p0);
    
    nsIAccessible getAccessibleInWindow(final nsIDOMNode p0, final nsIDOMWindow p1);
    
    nsIAccessible getAccessibleInWeakShell(final nsIDOMNode p0, final nsISupports p1);
    
    nsIAccessible getAccessibleInShell(final nsIDOMNode p0, final nsISupports p1);
    
    nsIAccessNode getCachedAccessNode(final nsIDOMNode p0, final nsISupports p1);
    
    nsIAccessible getCachedAccessible(final nsIDOMNode p0, final nsISupports p1);
}
