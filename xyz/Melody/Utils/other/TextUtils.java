//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.other;

import java.util.regex.*;
import java.text.*;
import java.util.*;

public class TextUtils
{
    public static final NumberFormat NUMBER_FORMAT;
    private static final Pattern STRIP_COLOR_PATTERN;
    private static final Pattern REPEATED_COLOR_PATTERN;
    private static final Pattern NUMBERS_SLASHES;
    private static final Pattern SCOREBOARD_CHARACTERS;
    private static final Pattern FLOAT_CHARACTERS;
    private static final Pattern INTEGER_CHARACTERS;
    private static final Pattern TRIM_WHITESPACE_RESETS;
    private static final Pattern USERNAME_PATTERN;
    private static final Pattern RESET_CODE_PATTERN;
    private static final Pattern MAGNITUDE_PATTERN;
    private static final NavigableMap<Integer, String> suffixes;
    
    public static String formatDouble(final double number) {
        return TextUtils.NUMBER_FORMAT.format(number);
    }
    
    public static String stripColor(final String input) {
        return TextUtils.STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }
    
    public static boolean isZeroLength(final String input) {
        return input.length() == 0 || TextUtils.REPEATED_COLOR_PATTERN.matcher(input).matches();
    }
    
    public static String keepScoreboardCharacters(final String text) {
        return TextUtils.SCOREBOARD_CHARACTERS.matcher(text).replaceAll("");
    }
    
    public static String keepFloatCharactersOnly(final String text) {
        return TextUtils.FLOAT_CHARACTERS.matcher(text).replaceAll("");
    }
    
    public static String keepIntegerCharactersOnly(final String text) {
        return TextUtils.INTEGER_CHARACTERS.matcher(text).replaceAll("");
    }
    
    public static String getNumbersOnly(final String text) {
        return TextUtils.NUMBERS_SLASHES.matcher(text).replaceAll("");
    }
    
    public static String convertMagnitudes(final String text) throws ParseException {
        final Matcher matcher = TextUtils.MAGNITUDE_PATTERN.matcher(text);
        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            double parsedDouble = TextUtils.NUMBER_FORMAT.parse(matcher.group(1)).doubleValue();
            final String lowerCase;
            final String magnitude = lowerCase = matcher.group(2).toLowerCase(Locale.ROOT);
            switch (lowerCase) {
                case "k": {
                    parsedDouble *= 1000.0;
                    break;
                }
                case "m": {
                    parsedDouble *= 1000000.0;
                    break;
                }
                case "b": {
                    parsedDouble *= 1.0E9;
                    break;
                }
                case "t": {
                    parsedDouble *= 1.0E12;
                    break;
                }
            }
            matcher.appendReplacement(sb, TextUtils.NUMBER_FORMAT.format(parsedDouble));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    
    public static String removeDuplicateSpaces(final String text) {
        return text.replaceAll("\\s+", " ");
    }
    
    public static String reverseText(final String originalText) {
        final StringBuilder newString = new StringBuilder();
        final String[] parts = originalText.split(" ");
        for (int i = parts.length; i > 0; --i) {
            final String textPart = parts[i - 1];
            boolean foundCharacter = false;
            for (final char letter : textPart.toCharArray()) {
                if (letter > '?') {
                    foundCharacter = true;
                    newString.append((CharSequence)new StringBuilder(textPart).reverse());
                    break;
                }
            }
            newString.append(" ");
            if (!foundCharacter) {
                newString.insert(0, textPart);
            }
            newString.insert(0, " ");
        }
        return removeDuplicateSpaces(newString.toString().trim());
    }
    
    public static String getOrdinalSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1: {
                return "st";
            }
            case 2: {
                return "nd";
            }
            case 3: {
                return "rd";
            }
            default: {
                return "th";
            }
        }
    }
    
    public static String abbreviate(final int number) {
        if (number < 0) {
            return "-" + abbreviate(-number);
        }
        if (number < 1000) {
            return Long.toString(number);
        }
        final Map.Entry<Integer, String> entry = TextUtils.suffixes.floorEntry(number);
        final Integer divideBy = entry.getKey();
        final String suffix = entry.getValue();
        final int truncated = number / (divideBy / 10);
        final boolean hasDecimal = truncated < 100 && truncated / 10.0 != truncated / 10;
        return hasDecimal ? (truncated / 10.0 + suffix) : (truncated / 10 + suffix);
    }
    
    public static String trimWhitespaceAndResets(final String input) {
        return TextUtils.TRIM_WHITESPACE_RESETS.matcher(input).replaceAll("");
    }
    
    public static boolean isUsername(final String input) {
        return TextUtils.USERNAME_PATTERN.matcher(input).matches();
    }
    
    public static String stripResets(final String input) {
        return TextUtils.RESET_CODE_PATTERN.matcher(input).replaceAll("");
    }
    
    public static String toProperCase(final String inputString) {
        final StringBuffer sb = new StringBuffer();
        final Matcher match = Pattern.compile("([a-z])([a-z]*)", 2).matcher(inputString);
        while (match.find()) {
            match.appendReplacement(sb, match.group(1).toUpperCase() + match.group(2).toLowerCase());
        }
        final String ret = match.appendTail(sb).toString();
        return ret;
    }
    
    public static String getFormattedString(final String formatted, final String unformattedSubstring) {
        if (unformattedSubstring.length() == 0) {
            return "";
        }
        final String styles = "kKlLmMnNoO";
        StringBuilder preEnchantFormat = new StringBuilder();
        StringBuilder formattedEnchant = new StringBuilder();
        int i = -2;
        final int len = formatted.length();
        int unformattedEnchantIdx = 0;
        int k = 0;
        while (true) {
            i = formatted.indexOf(167, i + 2);
            if (i == -1) {
                break;
            }
            while (k < i) {
                if (formatted.charAt(k) == unformattedSubstring.charAt(unformattedEnchantIdx)) {
                    formattedEnchant.append(formatted.charAt(k));
                    if (++unformattedEnchantIdx == unformattedSubstring.length()) {
                        return preEnchantFormat.append((CharSequence)formattedEnchant).toString();
                    }
                }
                else {
                    unformattedEnchantIdx = 0;
                    preEnchantFormat = new StringBuilder(mergeFormats(preEnchantFormat.toString(), formattedEnchant.toString()));
                    formattedEnchant = new StringBuilder();
                }
                ++k;
            }
            if (i + 1 >= len) {
                continue;
            }
            final char formatChar = formatted.charAt(i + 1);
            if (unformattedEnchantIdx == 0) {
                if (styles.indexOf(formatChar) == -1) {
                    preEnchantFormat = new StringBuilder();
                }
                preEnchantFormat.append("§").append(formatChar);
            }
            else {
                formattedEnchant.append("§").append(formatChar);
            }
            k = i + 2;
        }
        while (k < len) {
            if (formatted.charAt(k) == unformattedSubstring.charAt(unformattedEnchantIdx)) {
                formattedEnchant.append(formatted.charAt(k));
                if (++unformattedEnchantIdx == unformattedSubstring.length()) {
                    return preEnchantFormat.append((CharSequence)formattedEnchant).toString();
                }
            }
            else {
                unformattedEnchantIdx = 0;
                preEnchantFormat = new StringBuilder(mergeFormats(preEnchantFormat.toString(), formattedEnchant.toString()));
                formattedEnchant = new StringBuilder();
            }
            ++k;
        }
        return null;
    }
    
    private static String mergeFormats(final String firstFormat, final String secondFormat) {
        if (secondFormat == null || secondFormat.length() == 0) {
            return firstFormat;
        }
        final String styles = "kKlLmMnNoO";
        StringBuilder builder = new StringBuilder(firstFormat);
        int i = -2;
        while ((i = secondFormat.indexOf(167, i + 2)) != -1) {
            if (i + 1 < secondFormat.length()) {
                final char c = secondFormat.charAt(i + 1);
                if (styles.indexOf(c) == -1) {
                    builder = new StringBuilder();
                }
                builder.append("§").append(c);
            }
        }
        return builder.toString();
    }
    
    public static long reverseFormat(final String str2) {
        final String str3 = str2.toLowerCase();
        String integerPart = str3.substring(0, str3.length() - 1);
        long multiplier = 1L;
        if (str3.endsWith("k")) {
            multiplier = 1000L;
        }
        else if (str3.endsWith("m")) {
            multiplier = 1000000L;
        }
        else if (str3.endsWith("b")) {
            multiplier = 1000000000L;
        }
        else {
            integerPart = str3;
        }
        return (long)(Double.parseDouble(integerPart) * multiplier);
    }
    
    static {
        NUMBER_FORMAT = NumberFormat.getInstance(Locale.US);
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)§[0-9A-FK-ORZ]");
        REPEATED_COLOR_PATTERN = Pattern.compile("(?i)(§[0-9A-FK-ORZ])+");
        NUMBERS_SLASHES = Pattern.compile("[^0-9 /]");
        SCOREBOARD_CHARACTERS = Pattern.compile("[^a-z A-Z:0-9_/'.!§\\[\\]\u2764]");
        FLOAT_CHARACTERS = Pattern.compile("[^.0-9\\-]");
        INTEGER_CHARACTERS = Pattern.compile("[^0-9]");
        TRIM_WHITESPACE_RESETS = Pattern.compile("^(?:\\s|§r)*|(?:\\s|§r)*$");
        USERNAME_PATTERN = Pattern.compile("[A-Za-z0-9_]+");
        RESET_CODE_PATTERN = Pattern.compile("(?i)§R");
        MAGNITUDE_PATTERN = Pattern.compile("(\\d[\\d,.]*\\d*)+([kKmMbBtT])");
        (suffixes = new TreeMap<Integer, String>()).put(1000, "k");
        TextUtils.suffixes.put(1000000, "M");
        TextUtils.suffixes.put(1000000000, "B");
        TextUtils.NUMBER_FORMAT.setMaximumFractionDigits(2);
    }
}
