//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISerializable extends nsISupports
{
    public static final String NS_ISERIALIZABLE_IID = "{91cca981-c26d-44a8-bebe-d9ed4891503a}";
    
    void read(final nsIObjectInputStream p0);
    
    void write(final nsIObjectOutputStream p0);
}
