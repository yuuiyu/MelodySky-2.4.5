//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProfileChangeStatus extends nsISupports
{
    public static final String NS_IPROFILECHANGESTATUS_IID = "{2f977d43-5485-11d4-87e2-0010a4e75ef2}";
    
    void vetoChange();
    
    void changeFailed();
}
