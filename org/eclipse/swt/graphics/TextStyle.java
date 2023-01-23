//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;

public class TextStyle
{
    public Font font;
    public Color foreground;
    public Color background;
    public boolean underline;
    public Color underlineColor;
    public int underlineStyle;
    public boolean strikeout;
    public Color strikeoutColor;
    public int borderStyle;
    public Color borderColor;
    public GlyphMetrics metrics;
    public int rise;
    public Object data;
    
    public TextStyle() {
    }
    
    public TextStyle(final Font font, final Color foreground, final Color background) {
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        if (foreground != null && foreground.isDisposed()) {
            SWT.error(5);
        }
        if (background != null && background.isDisposed()) {
            SWT.error(5);
        }
        this.font = font;
        this.foreground = foreground;
        this.background = background;
    }
    
    public TextStyle(final TextStyle style) {
        if (style == null) {
            SWT.error(5);
        }
        this.font = style.font;
        this.foreground = style.foreground;
        this.background = style.background;
        this.underline = style.underline;
        this.underlineColor = style.underlineColor;
        this.underlineStyle = style.underlineStyle;
        this.strikeout = style.strikeout;
        this.strikeoutColor = style.strikeoutColor;
        this.borderStyle = style.borderStyle;
        this.borderColor = style.borderColor;
        this.metrics = style.metrics;
        this.rise = style.rise;
        this.data = style.data;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof TextStyle)) {
            return false;
        }
        final TextStyle style = (TextStyle)object;
        if (this.foreground != null) {
            if (!this.foreground.equals((Object)style.foreground)) {
                return false;
            }
        }
        else if (style.foreground != null) {
            return false;
        }
        if (this.background != null) {
            if (!this.background.equals((Object)style.background)) {
                return false;
            }
        }
        else if (style.background != null) {
            return false;
        }
        if (this.font != null) {
            if (!this.font.equals((Object)style.font)) {
                return false;
            }
        }
        else if (style.font != null) {
            return false;
        }
        if (this.metrics != null) {
            if (!this.metrics.equals((Object)style.metrics)) {
                return false;
            }
        }
        else if (style.metrics != null) {
            return false;
        }
        if (this.underline != style.underline) {
            return false;
        }
        if (this.underlineStyle != style.underlineStyle) {
            return false;
        }
        if (this.borderStyle != style.borderStyle) {
            return false;
        }
        if (this.strikeout != style.strikeout) {
            return false;
        }
        if (this.rise != style.rise) {
            return false;
        }
        if (this.underlineColor != null) {
            if (!this.underlineColor.equals((Object)style.underlineColor)) {
                return false;
            }
        }
        else if (style.underlineColor != null) {
            return false;
        }
        if (this.strikeoutColor != null) {
            if (!this.strikeoutColor.equals((Object)style.strikeoutColor)) {
                return false;
            }
        }
        else if (style.strikeoutColor != null) {
            return false;
        }
        if (this.underlineStyle != style.underlineStyle) {
            return false;
        }
        if (this.borderColor != null) {
            if (!this.borderColor.equals((Object)style.borderColor)) {
                return false;
            }
        }
        else if (style.borderColor != null) {
            return false;
        }
        if (this.data != null) {
            if (!this.data.equals(style.data)) {
                return false;
            }
        }
        else if (style.data != null) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        if (this.foreground != null) {
            hash ^= this.foreground.hashCode();
        }
        if (this.background != null) {
            hash ^= this.background.hashCode();
        }
        if (this.font != null) {
            hash ^= this.font.hashCode();
        }
        if (this.metrics != null) {
            hash ^= this.metrics.hashCode();
        }
        if (this.underline) {
            hash ^= hash << 1;
        }
        if (this.strikeout) {
            hash ^= hash << 2;
        }
        hash ^= this.rise;
        if (this.underlineColor != null) {
            hash ^= this.underlineColor.hashCode();
        }
        if (this.strikeoutColor != null) {
            hash ^= this.strikeoutColor.hashCode();
        }
        if (this.borderColor != null) {
            hash ^= this.borderColor.hashCode();
        }
        hash ^= this.underlineStyle;
        return hash;
    }
    
    boolean isAdherentBorder(final TextStyle style) {
        if (this == style) {
            return true;
        }
        if (style == null) {
            return false;
        }
        if (this.borderStyle != style.borderStyle) {
            return false;
        }
        if (this.borderColor != null) {
            if (!this.borderColor.equals((Object)style.borderColor)) {
                return false;
            }
        }
        else {
            if (style.borderColor != null) {
                return false;
            }
            if (this.foreground != null) {
                if (!this.foreground.equals((Object)style.foreground)) {
                    return false;
                }
            }
            else if (style.foreground != null) {
                return false;
            }
        }
        return true;
    }
    
    boolean isAdherentUnderline(final TextStyle style) {
        if (this == style) {
            return true;
        }
        if (style == null) {
            return false;
        }
        if (this.underline != style.underline) {
            return false;
        }
        if (this.underlineStyle != style.underlineStyle) {
            return false;
        }
        if (this.underlineColor != null) {
            if (!this.underlineColor.equals((Object)style.underlineColor)) {
                return false;
            }
        }
        else {
            if (style.underlineColor != null) {
                return false;
            }
            if (this.foreground != null) {
                if (!this.foreground.equals((Object)style.foreground)) {
                    return false;
                }
            }
            else if (style.foreground != null) {
                return false;
            }
        }
        return true;
    }
    
    boolean isAdherentStrikeout(final TextStyle style) {
        if (this == style) {
            return true;
        }
        if (style == null) {
            return false;
        }
        if (this.strikeout != style.strikeout) {
            return false;
        }
        if (this.strikeoutColor != null) {
            if (!this.strikeoutColor.equals((Object)style.strikeoutColor)) {
                return false;
            }
        }
        else {
            if (style.strikeoutColor != null) {
                return false;
            }
            if (this.foreground != null) {
                if (!this.foreground.equals((Object)style.foreground)) {
                    return false;
                }
            }
            else if (style.foreground != null) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder("TextStyle {");
        final int startLength = buffer.length();
        if (this.font != null) {
            if (buffer.length() > startLength) {
                buffer.append(", ");
            }
            buffer.append("font=");
            buffer.append(this.font);
        }
        if (this.foreground != null) {
            if (buffer.length() > startLength) {
                buffer.append(", ");
            }
            buffer.append("foreground=");
            buffer.append(this.foreground);
        }
        if (this.background != null) {
            if (buffer.length() > startLength) {
                buffer.append(", ");
            }
            buffer.append("background=");
            buffer.append(this.background);
        }
        if (this.underline) {
            if (buffer.length() > startLength) {
                buffer.append(", ");
            }
            buffer.append("underline=");
            switch (this.underlineStyle) {
                case 0: {
                    buffer.append("single");
                    break;
                }
                case 1: {
                    buffer.append("double");
                    break;
                }
                case 3: {
                    buffer.append("squiggle");
                    break;
                }
                case 2: {
                    buffer.append("error");
                    break;
                }
                case 4: {
                    buffer.append("link");
                    break;
                }
            }
            if (this.underlineColor != null) {
                buffer.append(", underlineColor=");
                buffer.append(this.underlineColor);
            }
        }
        if (this.strikeout) {
            if (buffer.length() > startLength) {
                buffer.append(", ");
            }
            buffer.append("striked out");
            if (this.strikeoutColor != null) {
                buffer.append(", strikeoutColor=");
                buffer.append(this.strikeoutColor);
            }
        }
        if (this.borderStyle != 0) {
            if (buffer.length() > startLength) {
                buffer.append(", ");
            }
            buffer.append("border=");
            switch (this.borderStyle) {
                case 1: {
                    buffer.append("solid");
                    break;
                }
                case 4: {
                    buffer.append("dot");
                    break;
                }
                case 2: {
                    buffer.append("dash");
                    break;
                }
            }
            if (this.borderColor != null) {
                buffer.append(", borderColor=");
                buffer.append(this.borderColor);
            }
        }
        if (this.rise != 0) {
            if (buffer.length() > startLength) {
                buffer.append(", ");
            }
            buffer.append("rise=");
            buffer.append(this.rise);
        }
        if (this.metrics != null) {
            if (buffer.length() > startLength) {
                buffer.append(", ");
            }
            buffer.append("metrics=");
            buffer.append(this.metrics);
        }
        buffer.append("}");
        return buffer.toString();
    }
}
