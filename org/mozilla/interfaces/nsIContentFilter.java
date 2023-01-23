//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContentFilter extends nsISupports
{
    public static final String NS_ICONTENTFILTER_IID = "{c18c49a8-62f0-4045-9884-4aa91e388f14}";
    
    void notifyOfInsertion(final String p0, final nsIURL p1, final nsIDOMDocument p2, final boolean p3, final nsIDOMNode[] p4, final nsIDOMNode[] p5, final int[] p6, final nsIDOMNode[] p7, final int[] p8, final nsIDOMNode[] p9, final int[] p10, final boolean[] p11);
}
