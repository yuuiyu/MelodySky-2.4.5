//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSerializer extends nsISupports
{
    public static final String NS_IDOMSERIALIZER_IID = "{9fd4ba15-e67c-4c98-b52c-7715f62c9196}";
    
    String serializeToString(final nsIDOMNode p0);
    
    void serializeToStream(final nsIDOMNode p0, final nsIOutputStream p1, final String p2);
}
