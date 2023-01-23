//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.*;

public class StyleRange extends TextStyle implements Cloneable
{
    public int start;
    public int length;
    public int fontStyle;
    
    public StyleRange() {
        this.fontStyle = 0;
    }
    
    public StyleRange(final TextStyle style) {
        super(style);
        this.fontStyle = 0;
    }
    
    public StyleRange(final int start, final int length, final Color foreground, final Color background) {
        super(null, foreground, background);
        this.fontStyle = 0;
        this.start = start;
        this.length = length;
    }
    
    public StyleRange(final int start, final int length, final Color foreground, final Color background, final int fontStyle) {
        this(start, length, foreground, background);
        this.fontStyle = fontStyle;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof StyleRange) {
            final StyleRange style = (StyleRange)object;
            return this.start == style.start && this.length == style.length && this.similarTo(style);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.fontStyle;
    }
    
    boolean isVariableHeight() {
        return this.font != null || (this.metrics != null && (this.metrics.ascent != 0 || this.metrics.descent != 0)) || this.rise != 0;
    }
    
    public boolean isUnstyled() {
        return this.font == null && this.rise == 0 && this.metrics == null && this.foreground == null && this.background == null && this.fontStyle == 0 && !this.underline && !this.strikeout && this.borderStyle == 0;
    }
    
    public boolean similarTo(final StyleRange style) {
        return super.equals(style) && this.fontStyle == style.fontStyle;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        buffer.append("StyleRange {");
        buffer.append(this.start);
        buffer.append(", ");
        buffer.append(this.length);
        buffer.append(", fontStyle=");
        switch (this.fontStyle) {
            case 1: {
                buffer.append("bold");
                break;
            }
            case 2: {
                buffer.append("italic");
                break;
            }
            case 3: {
                buffer.append("bold-italic");
                break;
            }
            default: {
                buffer.append("normal");
                break;
            }
        }
        String str = super.toString();
        final int index = str.indexOf(123);
        str = str.substring(index + 1);
        if (str.length() > 1) {
            buffer.append(", ");
        }
        buffer.append(str);
        return buffer.toString();
    }
}
