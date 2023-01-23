//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLSSerializer extends nsISupports
{
    public static final String NS_IDOMLSSERIALIZER_IID = "{96ea4792-d362-4c28-a8c2-0337790d648a}";
    
    nsIDOMDOMConfiguration getDomConfig();
    
    String getNewLine();
    
    void setNewLine(final String p0);
    
    nsIDOMLSSerializerFilter getFilter();
    
    void setFilter(final nsIDOMLSSerializerFilter p0);
    
    boolean write(final nsIDOMNode p0, final nsIDOMLSOutput p1);
    
    boolean writeToURI(final nsIDOMNode p0, final String p1);
    
    String writeToString(final nsIDOMNode p0);
}
