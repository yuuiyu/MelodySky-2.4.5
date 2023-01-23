//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDOMImplementation extends nsISupports
{
    public static final String NS_IDOMDOMIMPLEMENTATION_IID = "{a6cf9074-15b3-11d2-932e-00805f8add32}";
    
    boolean hasFeature(final String p0, final String p1);
    
    nsIDOMDocumentType createDocumentType(final String p0, final String p1, final String p2);
    
    nsIDOMDocument createDocument(final String p0, final String p1, final nsIDOMDocumentType p2);
}
