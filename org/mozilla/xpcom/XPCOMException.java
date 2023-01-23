//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

public class XPCOMException extends RuntimeException
{
    public long errorcode;
    private static final long serialVersionUID = 198521829884000593L;
    
    public XPCOMException() {
        this(2147500037L, "Unspecified internal XPCOM error");
    }
    
    public XPCOMException(final String s) {
        this(2147500037L, s);
    }
    
    public XPCOMException(final long n) {
        this(n, "Internal XPCOM error");
    }
    
    public XPCOMException(final long errorcode, final String s) {
        super(s + "  (0x" + Long.toHexString(errorcode) + ")");
        this.errorcode = errorcode;
    }
}
