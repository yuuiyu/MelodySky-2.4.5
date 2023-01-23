//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPromptService extends nsISupports
{
    public static final String NS_IPROMPTSERVICE_IID = "{1630c61a-325e-49ca-8759-a31b16c47aa5}";
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
    public static final long STD_YES_NO_BUTTONS = 1027L;
    
    void alert(final nsIDOMWindow p0, final String p1, final String p2);
    
    void alertCheck(final nsIDOMWindow p0, final String p1, final String p2, final String p3, final boolean[] p4);
    
    boolean confirm(final nsIDOMWindow p0, final String p1, final String p2);
    
    boolean confirmCheck(final nsIDOMWindow p0, final String p1, final String p2, final String p3, final boolean[] p4);
    
    int confirmEx(final nsIDOMWindow p0, final String p1, final String p2, final long p3, final String p4, final String p5, final String p6, final String p7, final boolean[] p8);
    
    boolean prompt(final nsIDOMWindow p0, final String p1, final String p2, final String[] p3, final String p4, final boolean[] p5);
    
    boolean promptUsernameAndPassword(final nsIDOMWindow p0, final String p1, final String p2, final String[] p3, final String[] p4, final String p5, final boolean[] p6);
    
    boolean promptPassword(final nsIDOMWindow p0, final String p1, final String p2, final String[] p3, final String p4, final boolean[] p5);
    
    boolean select(final nsIDOMWindow p0, final String p1, final String p2, final long p3, final String[] p4, final int[] p5);
}
