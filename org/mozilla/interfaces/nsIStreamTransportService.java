//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStreamTransportService extends nsISupports
{
    public static final String NS_ISTREAMTRANSPORTSERVICE_IID = "{8268d474-efbf-494f-a152-e8a8616f4e52}";
    
    nsITransport createInputTransport(final nsIInputStream p0, final long p1, final long p2, final boolean p3);
    
    nsITransport createOutputTransport(final nsIOutputStream p0, final long p1, final long p2, final boolean p3);
}
