//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISemanticUnitScanner extends nsISupports
{
    public static final String NS_ISEMANTICUNITSCANNER_IID = "{9f620be4-e535-11d6-b254-00039310a47a}";
    
    void start(final String p0);
    
    boolean next(final String p0, final int p1, final int p2, final boolean p3, final int[] p4, final int[] p5);
}
