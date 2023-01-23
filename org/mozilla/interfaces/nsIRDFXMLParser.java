//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFXMLParser extends nsISupports
{
    public static final String NS_IRDFXMLPARSER_IID = "{1831dd2e-1dd2-11b2-bdb3-86b7b50b70b5}";
    
    nsIStreamListener parseAsync(final nsIRDFDataSource p0, final nsIURI p1);
    
    void parseString(final nsIRDFDataSource p0, final nsIURI p1, final String p2);
}
