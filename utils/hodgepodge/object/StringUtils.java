//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object;

import java.util.concurrent.*;

public final class StringUtils
{
    public static boolean isEmpty(final String str) {
        return str.length() == 0;
    }
    
    public static boolean isNullOrEmpty(final String str) {
        return str == null || isEmpty(str);
    }
    
    public static String buildString(final Object... objects) {
        final StringBuilder builder = new StringBuilder();
        for (final Object object : objects) {
            builder.append(object);
        }
        return builder.toString();
    }
    
    public static String compileString(String code, final Object... objects) {
        for (int i = 0; i < objects.length; ++i) {
            code = code.replace(buildString("{", i, "}"), objects[i].toString());
        }
        return code;
    }
    
    public static String duplicateCopy(final String str, final int copyTicks) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < copyTicks; ++i) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
    
    public static boolean isNumber(final String str) {
        if (isEmpty(str)) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        final int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1 && chars[start] == '0') {
            if (chars[start + 1] == 'x' || chars[start + 1] == 'X') {
                int i = start + 2;
                if (i == sz) {
                    return false;
                }
                while (i < chars.length) {
                    if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                    ++i;
                }
                return true;
            }
            else if (Character.isDigit(chars[start + 1])) {
                for (int i = start + 1; i < chars.length; ++i) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        --sz;
        int i;
        for (i = start; i < sz || (i < sz + 1 && allowSigns && !foundDigit); ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;
            }
            else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    return false;
                }
                hasDecPoint = true;
            }
            else if (chars[i] != 'e' && chars[i] != 'E') {
                if (chars[i] != '+' && chars[i] != '-') {
                    return false;
                }
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false;
            }
            else {
                if (hasExp) {
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            }
        }
        if (i >= chars.length) {
            return !allowSigns && foundDigit;
        }
        if (chars[i] >= '0' && chars[i] <= '9') {
            return true;
        }
        if (chars[i] == 'e' || chars[i] == 'E') {
            return false;
        }
        if (chars[i] == '.') {
            return !hasDecPoint && !hasExp && foundDigit;
        }
        if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
            return foundDigit;
        }
        return (chars[i] == 'l' || chars[i] == 'L') && foundDigit && !hasExp && !hasDecPoint;
    }
    
    public static String randomString(final int length, final String seed) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(seed.charAt(ThreadLocalRandom.current().nextInt(0, seed.length() - 1)));
        }
        return sb.toString();
    }
}
