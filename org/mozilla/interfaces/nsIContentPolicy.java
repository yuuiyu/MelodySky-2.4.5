//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContentPolicy extends nsISupports
{
    public static final String NS_ICONTENTPOLICY_IID = "{3bb1a3c8-3073-41e0-9a26-a7671955fb65}";
    public static final long TYPE_OTHER = 1L;
    public static final long TYPE_SCRIPT = 2L;
    public static final long TYPE_IMAGE = 3L;
    public static final long TYPE_STYLESHEET = 4L;
    public static final long TYPE_OBJECT = 5L;
    public static final long TYPE_DOCUMENT = 6L;
    public static final long TYPE_SUBDOCUMENT = 7L;
    public static final long TYPE_REFRESH = 8L;
    public static final short REJECT_REQUEST = -1;
    public static final short REJECT_TYPE = -2;
    public static final short REJECT_SERVER = -3;
    public static final short REJECT_OTHER = -4;
    public static final short ACCEPT = 1;
    
    short shouldLoad(final long p0, final nsIURI p1, final nsIURI p2, final nsISupports p3, final String p4, final nsISupports p5);
    
    short shouldProcess(final long p0, final nsIURI p1, final nsIURI p2, final nsISupports p3, final String p4, final nsISupports p5);
}
