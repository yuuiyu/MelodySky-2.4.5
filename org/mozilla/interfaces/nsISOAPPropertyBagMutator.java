//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPPropertyBagMutator extends nsISupports
{
    public static final String NS_ISOAPPROPERTYBAGMUTATOR_IID = "{f34cb3c8-1dd1-11b2-8a18-a93a99d92c08}";
    
    nsIPropertyBag getPropertyBag();
    
    void addProperty(final String p0, final nsIVariant p1);
}
