//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMUserDataHandler
{
    public static final String NS_IDOMUSERDATAHANDLER_IID = "{5470deff-03c9-41b7-a824-e3225266b343}";
    public static final int NODE_CLONED = 1;
    public static final int NODE_IMPORTED = 2;
    public static final int NODE_DELETED = 3;
    public static final int NODE_RENAMED = 4;
    
    void handle(final int p0, final String p1, final nsIVariant p2, final nsIDOMNode p3, final nsIDOMNode p4);
}
