//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLSParserFilter extends nsISupports
{
    public static final String NS_IDOMLSPARSERFILTER_IID = "{10e8893d-ddf5-45d1-8872-615d72065fb4}";
    public static final short FILTER_ACCEPT = 1;
    public static final short FILTER_REJECT = 2;
    public static final short FILTER_SKIP = 3;
    public static final short FILTER_INTERRUPT = 4;
    
    int startElement(final nsIDOMElement p0);
    
    int acceptNode(final nsIDOMNode p0);
    
    long getWhatToShow();
}
