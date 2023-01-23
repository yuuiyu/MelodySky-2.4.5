//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

public class EncryptionUtil
{
    public static String xorCrypt(final String input) {
        final char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            chars[i] ^= '\u0002';
        }
        final String crypted = String.valueOf(chars);
        return crypted;
    }
}
