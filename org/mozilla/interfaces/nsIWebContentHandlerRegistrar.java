//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebContentHandlerRegistrar extends nsISupports
{
    public static final String NS_IWEBCONTENTHANDLERREGISTRAR_IID = "{e6a75410-c93e-42bf-84ca-a5c3ec34a2f1}";
    
    void registerContentHandler(final String p0, final String p1, final String p2, final nsIDOMWindow p3);
    
    void registerProtocolHandler(final String p0, final String p1, final String p2, final nsIDOMWindow p3);
}
