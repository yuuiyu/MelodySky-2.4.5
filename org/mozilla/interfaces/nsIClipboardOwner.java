//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIClipboardOwner extends nsISupports
{
    public static final String NS_ICLIPBOARDOWNER_IID = "{5a31c7a1-e122-11d2-9a57-000064657374}";
    
    void losingOwnership(final nsITransferable p0);
}
