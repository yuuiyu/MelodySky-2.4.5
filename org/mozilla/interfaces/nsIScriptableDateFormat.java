//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableDateFormat extends nsISupports
{
    public static final String NS_ISCRIPTABLEDATEFORMAT_IID = "{0c89efb0-1aae-11d3-9141-006008a6edf6}";
    public static final int dateFormatNone = 0;
    public static final int dateFormatLong = 1;
    public static final int dateFormatShort = 2;
    public static final int dateFormatYearMonth = 3;
    public static final int dateFormatWeekday = 4;
    public static final int timeFormatNone = 0;
    public static final int timeFormatSeconds = 1;
    public static final int timeFormatNoSeconds = 2;
    public static final int timeFormatSecondsForce24Hour = 3;
    public static final int timeFormatNoSecondsForce24Hour = 4;
    
    String formatDateTime(final String p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    String formatDate(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    String formatTime(final String p0, final int p1, final int p2, final int p3, final int p4);
}
