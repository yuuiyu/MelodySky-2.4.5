//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULTemplateBuilder extends nsISupports
{
    public static final String NS_IXULTEMPLATEBUILDER_IID = "{9da147a7-5854-49e3-a397-22ecdd93e96d}";
    
    nsIDOMElement getRoot();
    
    nsIRDFCompositeDataSource getDatabase();
    
    void rebuild();
    
    void refresh();
    
    void addListener(final nsIXULBuilderListener p0);
    
    void removeListener(final nsIXULBuilderListener p0);
}
