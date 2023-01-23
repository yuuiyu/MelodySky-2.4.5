//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableUnescapeHTML extends nsISupports
{
    public static final String NS_ISCRIPTABLEUNESCAPEHTML_IID = "{3ab244a9-f09d-44da-9e3f-ee4d67367f2d}";
    
    String unescape(final String p0);
    
    nsIDOMDocumentFragment parseFragment(final String p0, final boolean p1, final nsIURI p2, final nsIDOMElement p3);
}
