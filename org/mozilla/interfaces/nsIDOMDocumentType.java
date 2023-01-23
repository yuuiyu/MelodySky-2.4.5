//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDocumentType extends nsIDOMNode
{
    public static final String NS_IDOMDOCUMENTTYPE_IID = "{a6cf9077-15b3-11d2-932e-00805f8add32}";
    
    String getName();
    
    nsIDOMNamedNodeMap getEntities();
    
    nsIDOMNamedNodeMap getNotations();
    
    String getPublicId();
    
    String getSystemId();
    
    String getInternalSubset();
}
