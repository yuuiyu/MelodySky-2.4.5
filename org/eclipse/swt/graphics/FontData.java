//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;

public final class FontData
{
    public LOGFONT data;
    public float height;
    String lang;
    String country;
    String variant;
    
    public FontData() {
        this.data = new LOGFONT();
        this.data.lfCharSet = 1;
        this.height = 12.0f;
    }
    
    FontData(final LOGFONT data, final float height) {
        this.data = data;
        this.height = height;
    }
    
    public FontData(final String string) {
        if (string == null) {
            SWT.error(4);
        }
        int start = 0;
        int end = string.indexOf(124);
        if (end == -1) {
            SWT.error(5);
        }
        final String version1 = string.substring(start, end);
        try {
            if (Integer.parseInt(version1) != 1) {
                SWT.error(5);
            }
        }
        catch (NumberFormatException e) {
            SWT.error(5);
        }
        start = end + 1;
        end = string.indexOf(124, start);
        if (end == -1) {
            SWT.error(5);
        }
        final String name = string.substring(start, end);
        start = end + 1;
        end = string.indexOf(124, start);
        if (end == -1) {
            SWT.error(5);
        }
        float height = 0.0f;
        try {
            height = Float.parseFloat(string.substring(start, end));
        }
        catch (NumberFormatException e2) {
            SWT.error(5);
        }
        start = end + 1;
        end = string.indexOf(124, start);
        if (end == -1) {
            SWT.error(5);
        }
        int style = 0;
        try {
            style = Integer.parseInt(string.substring(start, end));
        }
        catch (NumberFormatException e3) {
            SWT.error(5);
        }
        start = end + 1;
        end = string.indexOf(124, start);
        this.data = new LOGFONT();
        this.data.lfCharSet = 1;
        this.setName(name);
        this.setHeight(height);
        this.setStyle(style);
        if (end == -1) {
            return;
        }
        final String platform = string.substring(start, end);
        start = end + 1;
        end = string.indexOf(124, start);
        if (end == -1) {
            return;
        }
        final String version2 = string.substring(start, end);
        if (platform.equals("WINDOWS") && version2.equals("1")) {
            final LOGFONT newData = new LOGFONT();
            try {
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfHeight = Integer.parseInt(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfWidth = Integer.parseInt(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfEscapement = Integer.parseInt(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfOrientation = Integer.parseInt(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfWeight = Integer.parseInt(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfItalic = Byte.parseByte(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfUnderline = Byte.parseByte(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfStrikeOut = Byte.parseByte(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfCharSet = Byte.parseByte(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfOutPrecision = Byte.parseByte(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfClipPrecision = Byte.parseByte(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfQuality = Byte.parseByte(string.substring(start, end));
                start = end + 1;
                end = string.indexOf(124, start);
                if (end == -1) {
                    return;
                }
                newData.lfPitchAndFamily = Byte.parseByte(string.substring(start, end));
                start = end + 1;
            }
            catch (NumberFormatException e4) {
                this.setName(name);
                this.setHeight(height);
                this.setStyle(style);
                return;
            }
            final int length = Math.min(newData.lfFaceName.length - 1, string.length() - start);
            string.getChars(start, start + length, newData.lfFaceName, 0);
            this.data = newData;
        }
    }
    
    public FontData(final String name, final int height, final int style) {
        if (name == null) {
            SWT.error(4);
        }
        this.data = new LOGFONT();
        this.setName(name);
        this.setHeight(height);
        this.setStyle(style);
        this.data.lfCharSet = 1;
    }
    
    FontData(final String name, final float height, final int style) {
        if (name == null) {
            SWT.error(4);
        }
        this.data = new LOGFONT();
        this.setName(name);
        this.setHeight(height);
        this.setStyle(style);
        this.data.lfCharSet = 1;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof FontData)) {
            return false;
        }
        final FontData fd = (FontData)object;
        final LOGFONT lf = fd.data;
        return this.data.lfCharSet == lf.lfCharSet && this.height == fd.height && this.data.lfWidth == lf.lfWidth && this.data.lfEscapement == lf.lfEscapement && this.data.lfOrientation == lf.lfOrientation && this.data.lfWeight == lf.lfWeight && this.data.lfItalic == lf.lfItalic && this.data.lfUnderline == lf.lfUnderline && this.data.lfStrikeOut == lf.lfStrikeOut && this.data.lfCharSet == lf.lfCharSet && this.data.lfOutPrecision == lf.lfOutPrecision && this.data.lfClipPrecision == lf.lfClipPrecision && this.data.lfQuality == lf.lfQuality && this.data.lfPitchAndFamily == lf.lfPitchAndFamily && this.getName().equals(fd.getName());
    }
    
    long EnumLocalesProc(final long lpLocaleString) {
        final int length = 8;
        final TCHAR buffer = new TCHAR(0, 8);
        final int byteCount = 16;
        OS.MoveMemory(buffer, lpLocaleString, 16);
        final int lcid = Integer.parseInt(buffer.toString(0, buffer.strlen()), 16);
        int size = OS.GetLocaleInfo(lcid, 89, buffer, 8);
        if (size <= 0 || !this.lang.equals(buffer.toString(0, size - 1))) {
            return 1L;
        }
        if (this.country != null) {
            size = OS.GetLocaleInfo(lcid, 90, buffer, 8);
            if (size <= 0 || !this.country.equals(buffer.toString(0, size - 1))) {
                return 1L;
            }
        }
        size = OS.GetLocaleInfo(lcid, 4100, buffer, 8);
        if (size <= 0) {
            return 1L;
        }
        final int cp = Integer.parseInt(buffer.toString(0, size - 1));
        final int[] lpCs = new int[8];
        OS.TranslateCharsetInfo(cp, lpCs, 2);
        this.data.lfCharSet = (byte)lpCs[0];
        return 0L;
    }
    
    public int getHeight() {
        return (int)(0.5f + this.height);
    }
    
    float getHeightF() {
        return this.height;
    }
    
    public String getLocale() {
        final StringBuilder buffer = new StringBuilder();
        final char sep = '_';
        if (this.lang != null) {
            buffer.append(this.lang);
            buffer.append('_');
        }
        if (this.country != null) {
            buffer.append(this.country);
            buffer.append('_');
        }
        if (this.variant != null) {
            buffer.append(this.variant);
        }
        String result = buffer.toString();
        final int length = result.length();
        if (length > 0 && result.charAt(length - 1) == '_') {
            result = result.substring(0, length - 1);
        }
        return result;
    }
    
    public String getName() {
        char[] chars;
        int index;
        for (chars = this.data.lfFaceName, index = 0; index < chars.length && chars[index] != '\0'; ++index) {}
        return new String(chars, 0, index);
    }
    
    public int getStyle() {
        int style = 0;
        if (this.data.lfWeight == 700) {
            style |= 0x1;
        }
        if (this.data.lfItalic != 0) {
            style |= 0x2;
        }
        return style;
    }
    
    @Override
    public int hashCode() {
        return this.data.lfCharSet ^ this.getHeight() << 8 ^ this.data.lfWidth ^ this.data.lfEscapement ^ this.data.lfOrientation ^ this.data.lfWeight ^ this.data.lfItalic ^ this.data.lfUnderline ^ this.data.lfStrikeOut ^ this.data.lfCharSet ^ this.data.lfOutPrecision ^ this.data.lfClipPrecision ^ this.data.lfQuality ^ this.data.lfPitchAndFamily ^ this.getName().hashCode();
    }
    
    public void setHeight(final int height) {
        if (height < 0) {
            SWT.error(5);
        }
        this.height = (float)height;
        this.data.lfWidth = 0;
    }
    
    void setHeight(final float height) {
        if (height < 0.0f) {
            SWT.error(5);
        }
        this.height = height;
    }
    
    public void setLocale(final String locale) {
        final String lang = null;
        this.variant = lang;
        this.country = lang;
        this.lang = lang;
        if (locale != null) {
            final char sep = '_';
            final int length = locale.length();
            int firstSep = locale.indexOf(95);
            int secondSep;
            if (firstSep == -1) {
                firstSep = (secondSep = length);
            }
            else {
                secondSep = locale.indexOf(95, firstSep + 1);
                if (secondSep == -1) {
                    secondSep = length;
                }
            }
            if (firstSep > 0) {
                this.lang = locale.substring(0, firstSep);
            }
            if (secondSep > firstSep + 1) {
                this.country = locale.substring(firstSep + 1, secondSep);
            }
            if (length > secondSep + 1) {
                this.variant = locale.substring(secondSep + 1);
            }
        }
        if (this.lang == null) {
            this.data.lfCharSet = 1;
        }
        else {
            final Callback callback = new Callback(this, "EnumLocalesProc", 1);
            OS.EnumSystemLocales(callback.getAddress(), 2);
            callback.dispose();
        }
    }
    
    public void setName(final String name) {
        if (name == null) {
            SWT.error(4);
        }
        final char[] lfFaceName = this.data.lfFaceName;
        final int length = Math.min(lfFaceName.length - 1, name.length());
        name.getChars(0, length, lfFaceName, 0);
        for (int i = length; i < lfFaceName.length; ++i) {
            lfFaceName[i] = '\0';
        }
    }
    
    public void setStyle(final int style) {
        if ((style & 0x1) == 0x1) {
            this.data.lfWeight = 700;
        }
        else {
            this.data.lfWeight = 0;
        }
        if ((style & 0x2) == 0x2) {
            this.data.lfItalic = 1;
        }
        else {
            this.data.lfItalic = 0;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder(128);
        buffer.append("1|");
        final String name = this.getName();
        buffer.append(name);
        buffer.append("|");
        buffer.append(this.getHeightF());
        buffer.append("|");
        buffer.append(this.getStyle());
        buffer.append("|");
        buffer.append("WINDOWS|1|");
        buffer.append(this.data.lfHeight);
        buffer.append("|");
        buffer.append(this.data.lfWidth);
        buffer.append("|");
        buffer.append(this.data.lfEscapement);
        buffer.append("|");
        buffer.append(this.data.lfOrientation);
        buffer.append("|");
        buffer.append(this.data.lfWeight);
        buffer.append("|");
        buffer.append(this.data.lfItalic);
        buffer.append("|");
        buffer.append(this.data.lfUnderline);
        buffer.append("|");
        buffer.append(this.data.lfStrikeOut);
        buffer.append("|");
        buffer.append(this.data.lfCharSet);
        buffer.append("|");
        buffer.append(this.data.lfOutPrecision);
        buffer.append("|");
        buffer.append(this.data.lfClipPrecision);
        buffer.append("|");
        buffer.append(this.data.lfQuality);
        buffer.append("|");
        buffer.append(this.data.lfPitchAndFamily);
        buffer.append("|");
        buffer.append(name);
        return buffer.toString();
    }
    
    public static FontData win32_new(final LOGFONT data, final float height) {
        return new FontData(data, height);
    }
}
