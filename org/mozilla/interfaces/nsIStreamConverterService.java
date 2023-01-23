//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStreamConverterService extends nsISupports
{
    public static final String NS_ISTREAMCONVERTERSERVICE_IID = "{e086e1e2-40ff-4193-8b8c-bd548babe70d}";
    
    nsIInputStream convert(final nsIInputStream p0, final String p1, final String p2, final nsISupports p3);
    
    nsIStreamListener asyncConvertData(final String p0, final String p1, final nsIStreamListener p2, final nsISupports p3);
}
