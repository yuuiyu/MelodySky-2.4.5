//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICollation extends nsISupports
{
    public static final String NS_ICOLLATION_IID = "{b0132cc0-3786-4557-9874-910d7def5f93}";
    public static final int kCollationStrengthDefault = 0;
    public static final int kCollationCaseInsensitiveAscii = 1;
    public static final int kCollationAccentInsenstive = 2;
    public static final int kCollationCaseSensitive = 0;
    public static final int kCollationCaseInSensitive = 3;
    
    void initialize(final nsILocale p0);
    
    int compareString(final int p0, final String p1, final String p2);
}
