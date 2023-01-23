//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import java.util.*;

public final class StringUtils
{
    public static String toCompleteString(final String[] args, final int start) {
        if (args.length <= start) {
            return "";
        }
        return String.join(" ", (CharSequence[])Arrays.copyOfRange(args, start, args.length));
    }
    
    public static String replace(final String string, final String searchChars, String replaceChars) {
        if (string.isEmpty() || searchChars.isEmpty() || searchChars.equals(replaceChars)) {
            return string;
        }
        if (replaceChars == null) {
            replaceChars = "";
        }
        final int stringLength = string.length();
        final int searchCharsLength = searchChars.length();
        final StringBuilder stringBuilder = new StringBuilder(string);
        int i = 0;
        while (i < stringLength) {
            final int start = stringBuilder.indexOf(searchChars, i);
            if (start == -1) {
                if (i == 0) {
                    return string;
                }
                return stringBuilder.toString();
            }
            else {
                stringBuilder.replace(start, start + searchCharsLength, replaceChars);
                ++i;
            }
        }
        return stringBuilder.toString();
    }
}
