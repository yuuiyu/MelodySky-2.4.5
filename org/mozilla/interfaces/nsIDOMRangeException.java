//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMRangeException extends nsISupports
{
    public static final String NS_IDOMRANGEEXCEPTION_IID = "{0f807301-39d2-11d6-a7f2-8f504ff870dc}";
    public static final int BAD_BOUNDARYPOINTS_ERR = 1;
    public static final int INVALID_NODE_TYPE_ERR = 2;
    
    int getCode();
}
