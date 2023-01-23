//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditorMailSupport extends nsISupports
{
    public static final String NS_IEDITORMAILSUPPORT_IID = "{fdf23301-4a94-11d3-9ce4-9960496c41bc}";
    
    void pasteAsQuotation(final int p0);
    
    nsIDOMNode insertAsQuotation(final String p0);
    
    void insertTextWithQuotations(final String p0);
    
    void pasteAsCitedQuotation(final String p0, final int p1);
    
    nsIDOMNode insertAsCitedQuotation(final String p0, final String p1, final boolean p2);
    
    void rewrap(final boolean p0);
    
    void stripCites();
    
    nsISupportsArray getEmbeddedObjects();
}
