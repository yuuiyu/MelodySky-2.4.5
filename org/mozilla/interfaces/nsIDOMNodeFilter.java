//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNodeFilter extends nsISupports
{
    public static final String NS_IDOMNODEFILTER_IID = "{e4723748-1dd1-11b2-8ee6-866a532a6237}";
    public static final short FILTER_ACCEPT = 1;
    public static final short FILTER_REJECT = 2;
    public static final short FILTER_SKIP = 3;
    public static final long SHOW_ALL = 4294967295L;
    public static final long SHOW_ELEMENT = 1L;
    public static final long SHOW_ATTRIBUTE = 2L;
    public static final long SHOW_TEXT = 4L;
    public static final long SHOW_CDATA_SECTION = 8L;
    public static final long SHOW_ENTITY_REFERENCE = 16L;
    public static final long SHOW_ENTITY = 32L;
    public static final long SHOW_PROCESSING_INSTRUCTION = 64L;
    public static final long SHOW_COMMENT = 128L;
    public static final long SHOW_DOCUMENT = 256L;
    public static final long SHOW_DOCUMENT_TYPE = 512L;
    public static final long SHOW_DOCUMENT_FRAGMENT = 1024L;
    public static final long SHOW_NOTATION = 2048L;
    
    short acceptNode(final nsIDOMNode p0);
}
