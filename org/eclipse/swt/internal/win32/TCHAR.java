//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

import java.util.*;

public class TCHAR
{
    public char[] chars;
    public static final int sizeof = 2;
    
    public TCHAR(final int codePage, final int length) {
        this.chars = new char[length];
    }
    
    public TCHAR(final int codePage, final char ch, final boolean terminate) {
        this(codePage, terminate ? new char[] { ch, '\0' } : new char[] { ch }, false);
    }
    
    public TCHAR(final int codePage, char[] chars, final boolean terminate) {
        final int charCount = chars.length;
        if (terminate && (charCount == 0 || (charCount > 0 && chars[charCount - 1] != '\0'))) {
            final char[] newChars = new char[charCount + 1];
            System.arraycopy(chars, 0, newChars, 0, charCount);
            chars = newChars;
        }
        this.chars = chars;
    }
    
    public TCHAR(final int codePage, final String string, final boolean terminate) {
        this(codePage, getChars(string, terminate), false);
    }
    
    static char[] getChars(final String string, final boolean terminate) {
        final int length = string.length();
        final char[] chars = new char[length + (terminate ? 1 : 0)];
        string.getChars(0, length, chars, 0);
        return chars;
    }
    
    public void clear() {
        Arrays.fill(this.chars, '\0');
    }
    
    public int length() {
        return this.chars.length;
    }
    
    public int strlen() {
        for (int i = 0; i < this.chars.length; ++i) {
            if (this.chars[i] == '\0') {
                return i;
            }
        }
        return this.chars.length;
    }
    
    public int tcharAt(final int index) {
        return this.chars[index];
    }
    
    @Override
    public String toString() {
        return this.toString(0, this.length());
    }
    
    public String toString(final int start, final int length) {
        return new String(this.chars, start, length);
    }
}
