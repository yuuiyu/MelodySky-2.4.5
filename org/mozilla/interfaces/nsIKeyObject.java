//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIKeyObject extends nsISupports
{
    public static final String NS_IKEYOBJECT_IID = "{4b31f4ed-9424-4710-b946-79b7e33cf3a8}";
    public static final short SYM_KEY = 1;
    public static final short PRIVATE_KEY = 2;
    public static final short PUBLIC_KEY = 3;
    public static final short RC4 = 1;
    public static final short AES_CBC = 2;
    
    short getType();
}
