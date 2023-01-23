//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAuthPrompt extends nsISupports
{
    public static final String NS_IAUTHPROMPT_IID = "{2f977d45-5485-11d4-87e2-0010a4e75ef2}";
    public static final long SAVE_PASSWORD_NEVER = 0L;
    public static final long SAVE_PASSWORD_FOR_SESSION = 1L;
    public static final long SAVE_PASSWORD_PERMANENTLY = 2L;
    
    boolean prompt(final String p0, final String p1, final String p2, final long p3, final String p4, final String[] p5);
    
    boolean promptUsernameAndPassword(final String p0, final String p1, final String p2, final long p3, final String[] p4, final String[] p5);
    
    boolean promptPassword(final String p0, final String p1, final String p2, final long p3, final String[] p4);
}
