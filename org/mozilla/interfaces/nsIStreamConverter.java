//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStreamConverter extends nsIStreamListener
{
    public static final String NS_ISTREAMCONVERTER_IID = "{0b6e2c69-5cf5-48b0-9dfd-c95950e2cc7b}";
    
    nsIInputStream convert(final nsIInputStream p0, final String p1, final String p2, final nsISupports p3);
    
    void asyncConvertData(final String p0, final String p1, final nsIStreamListener p2, final nsISupports p3);
}
