//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAtom extends nsISupports
{
    public static final String NS_IATOM_IID = "{3d1b15b0-93b4-11d1-895b-006008911b81}";
    
    String toString();
    
    String toUTF8String();
    
    boolean _equals(final String p0);
    
    boolean equalsUTF8(final String p0);
}
