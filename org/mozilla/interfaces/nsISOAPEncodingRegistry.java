//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPEncodingRegistry extends nsISupports
{
    public static final String NS_ISOAPENCODINGREGISTRY_IID = "{9fe91a61-3048-40e3-99ef-e39ab946ae0b}";
    
    nsISOAPEncoding getAssociatedEncoding(final String p0, final boolean p1);
    
    nsISchemaCollection getSchemaCollection();
    
    void setSchemaCollection(final nsISchemaCollection p0);
}
