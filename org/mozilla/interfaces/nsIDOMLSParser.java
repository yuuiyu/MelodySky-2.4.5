//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLSParser extends nsISupports
{
    public static final String NS_IDOMLSPARSER_IID = "{2a31a3a0-be68-40af-9f64-914192f0fba2}";
    public static final int ACTION_APPEND_AS_CHILDREN = 1;
    public static final int ACTION_REPLACE_CHILDREN = 2;
    public static final int ACTION_INSERT_BEFORE = 3;
    public static final int ACTION_INSERT_AFTER = 4;
    public static final int ACTION_REPLACE = 5;
    
    nsIDOMDOMConfiguration getDomConfig();
    
    nsIDOMLSParserFilter getFilter();
    
    void setFilter(final nsIDOMLSParserFilter p0);
    
    boolean getAsync();
    
    boolean getBusy();
    
    nsIDOMDocument parse(final nsIDOMLSInput p0);
    
    nsIDOMDocument parseURI(final String p0);
    
    nsIDOMNode parseWithContext(final nsIDOMLSInput p0, final nsIDOMNode p1, final int p2);
    
    void abort();
}
