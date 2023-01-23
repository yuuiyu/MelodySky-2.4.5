//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULTreeBuilder extends nsISupports
{
    public static final String NS_IXULTREEBUILDER_IID = "{06b31b15-ebf5-4e74-a0e2-6bc0a18a3969}";
    
    nsIRDFResource getResourceAtIndex(final int p0);
    
    int getIndexOfResource(final nsIRDFResource p0);
    
    void addObserver(final nsIXULTreeBuilderObserver p0);
    
    void removeObserver(final nsIXULTreeBuilderObserver p0);
    
    void sort(final nsIDOMElement p0);
}
