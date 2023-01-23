//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITransport extends nsISupports
{
    public static final String NS_ITRANSPORT_IID = "{cbb0baeb-5fcb-408b-a2be-9f8fc98d0af1}";
    public static final long OPEN_BLOCKING = 1L;
    public static final long OPEN_UNBUFFERED = 2L;
    public static final long STATUS_READING = 2152398856L;
    public static final long STATUS_WRITING = 2152398857L;
    
    nsIInputStream openInputStream(final long p0, final long p1, final long p2);
    
    nsIOutputStream openOutputStream(final long p0, final long p1, final long p2);
    
    void close(final long p0);
    
    void setEventSink(final nsITransportEventSink p0, final nsIEventTarget p1);
}
