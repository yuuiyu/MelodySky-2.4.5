//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFormatConverter extends nsISupports
{
    public static final String NS_IFORMATCONVERTER_IID = "{948a0023-e3a7-11d2-96cf-0060b0fb9956}";
    
    nsISupportsArray getInputDataFlavors();
    
    nsISupportsArray getOutputDataFlavors();
    
    boolean canConvert(final String p0, final String p1);
    
    void convert(final String p0, final nsISupports p1, final long p2, final String p3, final nsISupports[] p4, final long[] p5);
}
