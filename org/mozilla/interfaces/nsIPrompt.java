//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrompt extends nsISupports
{
    public static final String NS_IPROMPT_IID = "{a63f70c0-148b-11d3-9333-00104ba0fd40}";
    public static final long BUTTON_POS_0 = 1L;
    public static final long BUTTON_POS_1 = 256L;
    public static final long BUTTON_POS_2 = 65536L;
    public static final long BUTTON_TITLE_OK = 1L;
    public static final long BUTTON_TITLE_CANCEL = 2L;
    public static final long BUTTON_TITLE_YES = 3L;
    public static final long BUTTON_TITLE_NO = 4L;
    public static final long BUTTON_TITLE_SAVE = 5L;
    public static final long BUTTON_TITLE_DONT_SAVE = 6L;
    public static final long BUTTON_TITLE_REVERT = 7L;
    public static final long BUTTON_TITLE_IS_STRING = 127L;
    public static final long BUTTON_POS_0_DEFAULT = 0L;
    public static final long BUTTON_POS_1_DEFAULT = 16777216L;
    public static final long BUTTON_POS_2_DEFAULT = 33554432L;
    public static final long BUTTON_DELAY_ENABLE = 67108864L;
    public static final long STD_OK_CANCEL_BUTTONS = 513L;
    
    void alert(final String p0, final String p1);
    
    void alertCheck(final String p0, final String p1, final String p2, final boolean[] p3);
    
    boolean confirm(final String p0, final String p1);
    
    boolean confirmCheck(final String p0, final String p1, final String p2, final boolean[] p3);
    
    int confirmEx(final String p0, final String p1, final long p2, final String p3, final String p4, final String p5, final String p6, final boolean[] p7);
    
    boolean prompt(final String p0, final String p1, final String[] p2, final String p3, final boolean[] p4);
    
    boolean promptPassword(final String p0, final String p1, final String[] p2, final String p3, final boolean[] p4);
    
    boolean promptUsernameAndPassword(final String p0, final String p1, final String[] p2, final String[] p3, final String p4, final boolean[] p5);
    
    boolean select(final String p0, final String p1, final long p2, final String[] p3, final int[] p4);
}
