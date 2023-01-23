//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIExternalHelperAppService extends nsISupports
{
    public static final String NS_IEXTERNALHELPERAPPSERVICE_IID = "{0ea90cf3-2dd9-470f-8f76-f141743c5678}";
    
    nsIStreamListener doContent(final String p0, final nsIRequest p1, final nsIInterfaceRequestor p2);
    
    boolean applyDecodingForExtension(final String p0, final String p1);
}
