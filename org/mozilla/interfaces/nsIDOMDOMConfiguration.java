//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDOMConfiguration extends nsISupports
{
    public static final String NS_IDOMDOMCONFIGURATION_IID = "{cfb5b821-9016-4a79-9d98-87b57c3ea0c7}";
    
    void setParameter(final String p0, final nsIVariant p1);
    
    nsIVariant getParameter(final String p0);
    
    boolean canSetParameter(final String p0, final nsIVariant p1);
}
