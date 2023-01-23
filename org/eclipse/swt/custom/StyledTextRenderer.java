//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import java.util.*;
import java.util.function.*;

class StyledTextRenderer
{
    Device device;
    StyledText styledText;
    StyledTextContent content;
    StyledTextLineSpacingProvider lineSpacingProvider;
    boolean lineSpacingComputing;
    Font regularFont;
    Font boldFont;
    Font italicFont;
    Font boldItalicFont;
    int tabWidth;
    int ascent;
    int descent;
    int averageCharWidth;
    int tabLength;
    int topIndex;
    TextLayout[] layouts;
    int lineCount;
    LineSizeInfo[] lineSizes;
    LineInfo[] lines;
    int maxWidth;
    int maxWidthLineIndex;
    float averageLineHeight;
    int linesInAverageLineHeight;
    boolean idleRunning;
    Bullet[] bullets;
    int[] bulletsIndices;
    int[] redrawLines;
    int[] ranges;
    int styleCount;
    StyleRange[] styles;
    StyleRange[] stylesSet;
    int stylesSetCount;
    boolean hasLinks;
    boolean fixedPitch;
    static final int BULLET_MARGIN = 8;
    static final boolean COMPACT_STYLES = true;
    static final boolean MERGE_STYLES = true;
    static final int GROW = 32;
    static final int IDLE_TIME = 50;
    static final int CACHE_SIZE = 300;
    static final int BACKGROUND = 1;
    static final int ALIGNMENT = 2;
    static final int INDENT = 4;
    static final int JUSTIFY = 8;
    static final int SEGMENTS = 32;
    static final int TABSTOPS = 64;
    static final int WRAP_INDENT = 128;
    static final int SEGMENT_CHARS = 256;
    static final int VERTICAL_INDENT = 512;
    
    static int cap(final TextLayout layout, final int offset) {
        if (layout == null) {
            return offset;
        }
        return Math.min(layout.getText().length() - 1, Math.max(0, offset));
    }
    
    StyledTextRenderer(final Device device, final StyledText styledText) {
        this.topIndex = -1;
        this.stylesSetCount = 0;
        this.device = device;
        this.styledText = styledText;
    }
    
    int addMerge(final int[] mergeRanges, final StyleRange[] mergeStyles, int mergeCount, final int modifyStart, int modifyEnd) {
        final int rangeCount = this.styleCount << 1;
        StyleRange endStyle = null;
        int endStart = 0;
        int endLength = 0;
        if (modifyEnd < rangeCount) {
            endStyle = this.styles[modifyEnd >> 1];
            endStart = this.ranges[modifyEnd];
            endLength = this.ranges[modifyEnd + 1];
        }
        int grow = mergeCount - (modifyEnd - modifyStart);
        if (rangeCount + grow >= this.ranges.length) {
            final int[] tmpRanges = new int[this.ranges.length + grow + 64];
            System.arraycopy(this.ranges, 0, tmpRanges, 0, modifyStart);
            final StyleRange[] tmpStyles = new StyleRange[this.styles.length + (grow >> 1) + 32];
            System.arraycopy(this.styles, 0, tmpStyles, 0, modifyStart >> 1);
            if (rangeCount > modifyEnd) {
                System.arraycopy(this.ranges, modifyEnd, tmpRanges, modifyStart + mergeCount, rangeCount - modifyEnd);
                System.arraycopy(this.styles, modifyEnd >> 1, tmpStyles, modifyStart + mergeCount >> 1, this.styleCount - (modifyEnd >> 1));
            }
            this.ranges = tmpRanges;
            this.styles = tmpStyles;
        }
        else if (rangeCount > modifyEnd) {
            System.arraycopy(this.ranges, modifyEnd, this.ranges, modifyStart + mergeCount, rangeCount - modifyEnd);
            System.arraycopy(this.styles, modifyEnd >> 1, this.styles, modifyStart + mergeCount >> 1, this.styleCount - (modifyEnd >> 1));
        }
        int j = modifyStart;
        for (int i = 0; i < mergeCount; i += 2) {
            if (j > 0 && this.ranges[j - 2] + this.ranges[j - 1] == mergeRanges[i] && mergeStyles[i >> 1].similarTo(this.styles[j - 2 >> 1])) {
                final int[] ranges = this.ranges;
                final int n = j - 1;
                final int[] array = ranges;
                final int n3 = n;
                array[n3] += mergeRanges[i + 1];
            }
            else {
                this.styles[j >> 1] = mergeStyles[i >> 1];
                this.ranges[j++] = mergeRanges[i];
                this.ranges[j++] = mergeRanges[i + 1];
            }
        }
        if (endStyle != null && this.ranges[j - 2] + this.ranges[j - 1] == endStart && endStyle.similarTo(this.styles[j - 2 >> 1])) {
            final int[] ranges2 = this.ranges;
            final int n2 = j - 1;
            final int[] array2 = ranges2;
            final int n4 = n2;
            array2[n4] += endLength;
            modifyEnd += 2;
            mergeCount += 2;
        }
        if (rangeCount > modifyEnd) {
            System.arraycopy(this.ranges, modifyStart + mergeCount, this.ranges, j, rangeCount - modifyEnd);
            System.arraycopy(this.styles, modifyStart + mergeCount >> 1, this.styles, j >> 1, this.styleCount - (modifyEnd >> 1));
        }
        grow = j - modifyStart - (modifyEnd - modifyStart);
        this.styleCount += grow >> 1;
        return grow;
    }
    
    int addMerge(final StyleRange[] mergeStyles, int mergeCount, final int modifyStart, int modifyEnd) {
        int grow = mergeCount - (modifyEnd - modifyStart);
        StyleRange endStyle = null;
        if (modifyEnd < this.styleCount) {
            endStyle = this.styles[modifyEnd];
        }
        if (this.styleCount + grow >= this.styles.length) {
            final StyleRange[] tmpStyles = new StyleRange[this.styles.length + grow + 32];
            System.arraycopy(this.styles, 0, tmpStyles, 0, modifyStart);
            if (this.styleCount > modifyEnd) {
                System.arraycopy(this.styles, modifyEnd, tmpStyles, modifyStart + mergeCount, this.styleCount - modifyEnd);
            }
            this.styles = tmpStyles;
        }
        else if (this.styleCount > modifyEnd) {
            System.arraycopy(this.styles, modifyEnd, this.styles, modifyStart + mergeCount, this.styleCount - modifyEnd);
        }
        int j = modifyStart;
        for (final StyleRange newStyle : mergeStyles) {
            final StyleRange style;
            if (j > 0 && (style = this.styles[j - 1]).start + style.length == newStyle.start && newStyle.similarTo(style)) {
                final StyleRange styleRange3;
                final StyleRange styleRange = styleRange3 = style;
                styleRange3.length += newStyle.length;
            }
            else {
                this.styles[j++] = newStyle;
            }
        }
        final StyleRange style2 = this.styles[j - 1];
        if (endStyle != null && style2.start + style2.length == endStyle.start && endStyle.similarTo(style2)) {
            final StyleRange styleRange4;
            final StyleRange styleRange2 = styleRange4 = style2;
            styleRange4.length += endStyle.length;
            ++modifyEnd;
            ++mergeCount;
        }
        if (this.styleCount > modifyEnd) {
            System.arraycopy(this.styles, modifyStart + mergeCount, this.styles, j, this.styleCount - modifyEnd);
        }
        grow = j - modifyStart - (modifyEnd - modifyStart);
        this.styleCount += grow;
        return grow;
    }
    
    void calculate(final int startLine, final int lineCount) {
        final int endLine = startLine + lineCount;
        if (startLine < 0 || endLine > this.lineSizes.length) {
            return;
        }
        final int hTrim = this.styledText.leftMargin + this.styledText.rightMargin + this.styledText.getCaretWidth();
        for (int i = startLine; i < endLine; ++i) {
            final LineSizeInfo line = this.getLineSize(i);
            if (line.needsRecalculateSize()) {
                final TextLayout layout = this.getTextLayout(i);
                final Rectangle rect = layout.getBounds();
                line.width = rect.width + hTrim;
                line.height = rect.height;
                this.averageLineHeight += (line.height - Math.round(this.averageLineHeight)) / ++this.linesInAverageLineHeight;
                this.disposeTextLayout(layout);
            }
            if (line.width > this.maxWidth) {
                this.maxWidth = line.width;
                this.maxWidthLineIndex = i;
            }
        }
    }
    
    LineSizeInfo getLineSize(final int i) {
        if (this.lineSizes[i] == null) {
            this.lineSizes[i] = new LineSizeInfo();
        }
        return this.lineSizes[i];
    }
    
    void calculateClientArea() {
        for (int index = Math.max(0, this.styledText.getTopIndex()), lineCount = this.content.getLineCount(), height = this.styledText.getClientArea().height, y = 0; height > y && lineCount > index && this.lineSizes.length > index; y += this.lineSizes[index++].height) {
            this.calculate(index, 1);
        }
    }
    
    void calculateIdle() {
        if (this.idleRunning) {
            return;
        }
        final Runnable runnable = (Runnable)new lIIlIII(this);
        final Display display = this.styledText.getDisplay();
        display.asyncExec(runnable);
        this.idleRunning = true;
    }
    
    void clearLineBackground(final int startLine, final int count) {
        if (this.lines == null) {
            return;
        }
        for (int i = startLine; i < startLine + count; ++i) {
            final LineInfo info = this.lines[i];
            if (info != null) {
                final LineInfo lineInfo2;
                final LineInfo lineInfo = lineInfo2 = info;
                lineInfo2.flags &= 0xFFFFFFFE;
                info.background = null;
                if (info.flags == 0) {
                    this.lines[i] = null;
                }
            }
        }
    }
    
    void clearLineStyle(final int startLine, final int count) {
        if (this.lines == null) {
            return;
        }
        for (int i = startLine; i < startLine + count; ++i) {
            final LineInfo info = this.lines[i];
            if (info != null) {
                final LineInfo lineInfo2;
                final LineInfo lineInfo = lineInfo2 = info;
                lineInfo2.flags &= 0xFFFFFD31;
                if (info.flags == 0) {
                    this.lines[i] = null;
                }
            }
        }
    }
    
    void copyInto(final StyledTextRenderer renderer) {
        if (this.ranges != null) {
            final int[] ranges = new int[this.styleCount << 1];
            renderer.ranges = ranges;
            final int[] newRanges = ranges;
            System.arraycopy(this.ranges, 0, newRanges, 0, newRanges.length);
        }
        if (this.styles != null) {
            final StyleRange[] styles = new StyleRange[this.styleCount];
            renderer.styles = styles;
            final StyleRange[] newStyles = styles;
            for (int i = 0; i < newStyles.length; ++i) {
                newStyles[i] = (StyleRange)this.styles[i].clone();
            }
            renderer.styleCount = this.styleCount;
        }
        if (this.lines != null) {
            final LineInfo[] lines = new LineInfo[this.lineCount];
            renderer.lines = lines;
            final LineInfo[] newLines = lines;
            for (int i = 0; i < newLines.length; ++i) {
                newLines[i] = new LineInfo(this.lines[i]);
            }
            renderer.lineCount = this.lineCount;
        }
    }
    
    void dispose() {
        if (this.boldFont != null) {
            this.boldFont.dispose();
        }
        if (this.italicFont != null) {
            this.italicFont.dispose();
        }
        if (this.boldItalicFont != null) {
            this.boldItalicFont.dispose();
        }
        final Font boldFont = null;
        this.boldItalicFont = boldFont;
        this.italicFont = boldFont;
        this.boldFont = boldFont;
        this.reset();
        this.content = null;
        this.device = null;
        this.styledText = null;
    }
    
    void disposeTextLayout(final TextLayout layout) {
        if (this.layouts != null) {
            for (final TextLayout l : this.layouts) {
                if (l == layout) {
                    return;
                }
            }
        }
        layout.dispose();
    }
    
    void drawBullet(final Bullet bullet, final GC gc, final int paintX, final int paintY, final int index, final int lineAscent, final int lineDescent) {
        StyleRange style = bullet.style;
        final GlyphMetrics metrics = style.metrics;
        final Color color = style.foreground;
        if (color != null) {
            gc.setForeground(color);
        }
        final Font font = style.font;
        if (font != null) {
            gc.setFont(font);
        }
        String string = "";
        final int type = bullet.type & 0xF;
        switch (type) {
            case 1: {
                string = "\u2022";
                break;
            }
            case 2: {
                string = String.valueOf(index + 1);
                break;
            }
            case 4: {
                string = String.valueOf((char)(index % 26 + 97));
                break;
            }
            case 8: {
                string = String.valueOf((char)(index % 26 + 65));
                break;
            }
        }
        if ((bullet.type & 0x10) != 0x0) {
            string += bullet.text;
        }
        final Display display = this.styledText.getDisplay();
        final TextLayout layout = new TextLayout(display);
        layout.setText(string);
        layout.setAscent(lineAscent);
        layout.setDescent(lineDescent);
        style = (StyleRange)style.clone();
        style.metrics = null;
        if (style.font == null) {
            style.font = this.getFont(style.fontStyle);
        }
        layout.setStyle(style, 0, string.length());
        final int x = paintX + Math.max(0, metrics.width - layout.getBounds().width - 8);
        layout.draw(gc, x, paintY);
        layout.dispose();
    }
    
    int drawLine(final int lineIndex, final int paintX, final int paintY, final GC gc, final Color widgetBackground, final Color widgetForeground) {
        final TextLayout layout = this.getTextLayout(lineIndex);
        final String line = this.content.getLine(lineIndex);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final int lineLength = line.length();
        final Rectangle client = this.styledText.getClientArea();
        Color lineBackground = this.getLineBackground(lineIndex, null);
        final StyledTextEvent event = this.styledText.getLineBackgroundData(lineOffset, line);
        if (event != null && event.lineBackground != null) {
            lineBackground = event.lineBackground;
        }
        final int height = layout.getBounds().height;
        final int verticalIndent = layout.getVerticalIndent();
        if (lineBackground != null) {
            if (verticalIndent > 0) {
                gc.setBackground(widgetBackground);
                gc.fillRectangle(client.x, paintY, client.width, verticalIndent);
            }
            gc.setBackground(lineBackground);
            gc.fillRectangle(client.x, paintY + verticalIndent, client.width, height - verticalIndent);
        }
        else {
            gc.setBackground(widgetBackground);
            this.styledText.drawBackground(gc, client.x, paintY, client.width, height);
        }
        gc.setForeground(widgetForeground);
        final Point[] selection = this.intersectingRelativeNonEmptySelections(lineOffset, lineOffset + lineLength);
        if (this.styledText.getBlockSelection() || selection.length == 0) {
            layout.draw(gc, paintX, paintY);
        }
        else {
            final Color selectionFg = this.styledText.getSelectionForeground();
            final Color selectionBg = this.styledText.getSelectionBackground();
            final int baseFlags = ((this.styledText.getStyle() & 0x10000) != 0x0) ? 65536 : 131072;
            for (final Point relativeSelection : selection) {
                final int start = Math.max(0, relativeSelection.x);
                final int end = Math.min(lineLength, relativeSelection.y);
                int flags = baseFlags;
                if (relativeSelection.x <= lineLength && lineLength < relativeSelection.y) {
                    flags |= 0x100000;
                }
                layout.draw(gc, paintX, paintY, start, end - 1, selectionFg, selectionBg, flags);
            }
        }
        Bullet bullet = null;
        int bulletIndex = -1;
        if (this.bullets != null) {
            if (this.bulletsIndices != null) {
                final int index = lineIndex - this.topIndex;
                if (0 <= index && index < 300) {
                    bullet = this.bullets[index];
                    bulletIndex = this.bulletsIndices[index];
                }
            }
            else {
                for (final Bullet b : this.bullets) {
                    bullet = b;
                    bulletIndex = bullet.indexOf(lineIndex);
                    if (bulletIndex != -1) {
                        break;
                    }
                }
            }
        }
        if (bulletIndex != -1 && bullet != null) {
            final FontMetrics metrics = layout.getLineMetrics(0);
            final int lineAscent = metrics.getAscent() + metrics.getLeading();
            if (bullet.type == 32) {
                bullet.style.start = lineOffset;
                this.styledText.paintObject(gc, paintX, paintY, lineAscent, metrics.getDescent(), bullet.style, bullet, bulletIndex);
            }
            else {
                this.drawBullet(bullet, gc, paintX, paintY, bulletIndex, lineAscent, metrics.getDescent());
            }
        }
        final TextStyle[] styles = layout.getStyles();
        int[] ranges = null;
        for (int i = 0; i < styles.length; ++i) {
            if (styles[i].metrics != null) {
                if (ranges == null) {
                    ranges = layout.getRanges();
                }
                final int start2 = ranges[i << 1];
                final int length4 = ranges[(i << 1) + 1] - start2 + 1;
                final Point point = layout.getLocation(start2, false);
                final FontMetrics metrics2 = layout.getLineMetrics(layout.getLineIndex(start2));
                final StyleRange style = (StyleRange)((StyleRange)styles[i]).clone();
                style.start = start2 + lineOffset;
                style.length = length4;
                final int lineAscent2 = metrics2.getAscent() + metrics2.getLeading();
                this.styledText.paintObject(gc, point.x + paintX, point.y + paintY, lineAscent2, metrics2.getDescent(), style, (Bullet)null, 0);
            }
        }
        this.disposeTextLayout(layout);
        return height;
    }
    
    private Point[] intersectingRelativeNonEmptySelections(final int fromOffset, final int toOffset) {
        final int[] selectionRanges = this.styledText.getSelectionRanges();
        final int lineLength = toOffset - fromOffset;
        final List<Point> res = new ArrayList<Point>();
        for (int i = 0; i < selectionRanges.length; i += 2) {
            final Point relativeSelection = new Point(selectionRanges[i] - fromOffset, selectionRanges[i] + selectionRanges[i + 1] - fromOffset);
            if (relativeSelection.x != relativeSelection.y && relativeSelection.x <= lineLength && relativeSelection.y >= 0) {
                res.add(relativeSelection);
            }
        }
        return res.toArray(new Point[res.size()]);
    }
    
    int getBaseline() {
        return this.ascent;
    }
    
    int getCachedLineHeight(final int lineIndex) {
        return this.getLineHeight(lineIndex, false);
    }
    
    Font getFont(final int style) {
        switch (style) {
            case 1: {
                if (this.boldFont != null) {
                    return this.boldFont;
                }
                return this.boldFont = new Font(this.device, this.getFontData(style));
            }
            case 2: {
                if (this.italicFont != null) {
                    return this.italicFont;
                }
                return this.italicFont = new Font(this.device, this.getFontData(style));
            }
            case 3: {
                if (this.boldItalicFont != null) {
                    return this.boldItalicFont;
                }
                return this.boldItalicFont = new Font(this.device, this.getFontData(style));
            }
            default: {
                return this.regularFont;
            }
        }
    }
    
    FontData[] getFontData(final int style) {
        final FontData[] fontDatas;
        final FontData[] array;
        final FontData[] fontData2 = array = (fontDatas = this.regularFont.getFontData());
        for (final FontData fontData3 : array) {
            fontData3.setStyle(style);
        }
        return fontDatas;
    }
    
    int getHeight() {
        final int defaultLineHeight = this.getLineHeight();
        if (this.styledText.isFixedLineHeight()) {
            return this.lineCount * defaultLineHeight + this.styledText.topMargin + this.styledText.bottomMargin;
        }
        int totalHeight = 0;
        final int width = this.styledText.getWrapWidth();
        for (int i = 0; i < this.lineCount; ++i) {
            final LineSizeInfo line = this.getLineSize(i);
            int height = line.height;
            if (line.needsRecalculateHeight()) {
                if (width > 0) {
                    final int length = this.content.getLine(i).length();
                    height = (length * this.averageCharWidth / width + 1) * defaultLineHeight;
                }
                else {
                    height = defaultLineHeight;
                }
            }
            totalHeight += height;
        }
        return totalHeight + this.styledText.topMargin + this.styledText.bottomMargin;
    }
    
    boolean hasLink(final int offset) {
        if (offset == -1) {
            return false;
        }
        final int lineIndex = this.content.getLineAtOffset(offset);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final String line = this.content.getLine(lineIndex);
        final StyledTextEvent event = this.styledText.getLineStyleData(lineOffset, line);
        if (event != null) {
            final StyleRange[] styles = event.styles;
            if (styles != null) {
                final int[] ranges = event.ranges;
                if (ranges != null) {
                    for (int i = 0; i < ranges.length; i += 2) {
                        if (ranges[i] <= offset && offset < ranges[i] + ranges[i + 1] && styles[i >> 1].underline && styles[i >> 1].underlineStyle == 4) {
                            return true;
                        }
                    }
                }
                else {
                    for (final StyleRange style : styles) {
                        if (style.start <= offset && offset < style.start + style.length && style.underline && style.underlineStyle == 4) {
                            return true;
                        }
                    }
                }
            }
        }
        else if (this.ranges != null) {
            final int rangeCount = this.styleCount << 1;
            final int index = this.getRangeIndex(offset, -1, rangeCount);
            if (index >= rangeCount) {
                return false;
            }
            final int rangeStart = this.ranges[index];
            final int rangeLength = this.ranges[index + 1];
            final StyleRange rangeStyle = this.styles[index >> 1];
            if (rangeStart <= offset && offset < rangeStart + rangeLength && rangeStyle.underline && rangeStyle.underlineStyle == 4) {
                return true;
            }
        }
        return false;
    }
    
    int getLineAlignment(final int index, final int defaultAlignment) {
        if (this.lines == null) {
            return defaultAlignment;
        }
        final LineInfo info = this.lines[index];
        if (info != null && (info.flags & 0x2) != 0x0) {
            return info.alignment;
        }
        return defaultAlignment;
    }
    
    Color getLineBackground(final int index, final Color defaultBackground) {
        if (this.lines == null) {
            return defaultBackground;
        }
        final LineInfo info = this.lines[index];
        if (info != null && (info.flags & 0x1) != 0x0) {
            return info.background;
        }
        return defaultBackground;
    }
    
    Bullet getLineBullet(final int index, final Bullet defaultBullet) {
        if (this.bullets == null) {
            return defaultBullet;
        }
        if (this.bulletsIndices != null) {
            return defaultBullet;
        }
        for (final Bullet bullet : this.bullets) {
            if (bullet.indexOf(index) != -1) {
                return bullet;
            }
        }
        return defaultBullet;
    }
    
    int getLineHeight() {
        return this.ascent + this.descent;
    }
    
    int getLineHeight(final int lineIndex) {
        return this.getLineHeight(lineIndex, true);
    }
    
    int getLineHeight(final int lineIndex, final boolean exact) {
        final LineSizeInfo line = this.getLineSize(lineIndex);
        if (line.needsRecalculateHeight()) {
            if (this.isVariableHeight(lineIndex)) {
                if (!exact) {
                    return Math.round(this.averageLineHeight);
                }
                this.calculate(lineIndex, 1);
            }
            else {
                line.height = this.getLineHeight() + this.getLineSpacing(lineIndex) + this.getLineVerticalIndent(lineIndex);
            }
        }
        return line.height;
    }
    
    private boolean isVariableHeight(final int lineIndex) {
        if (this.styledText.isWordWrap()) {
            return true;
        }
        final StyleRange[] styles = this.getStylesForLine(lineIndex);
        if (styles != null) {
            for (final StyleRange style : styles) {
                if (style.isVariableHeight()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private int getLineSpacing(final int lineIndex) {
        if (this.styledText.lineSpacing > 0) {
            return this.styledText.lineSpacing;
        }
        if (this.lineSpacingProvider != null) {
            final Integer lineSpacing = this.lineSpacingProvider.getLineSpacing(lineIndex);
            if (lineSpacing != null) {
                return lineSpacing;
            }
        }
        return 0;
    }
    
    private StyleRange[] getStylesForLine(final int lineIndex) {
        final int start = this.styledText.getOffsetAtLine(lineIndex);
        final int length = this.styledText.getLine(lineIndex).length();
        return this.getStyleRanges(start, length, false);
    }
    
    int getLineIndent(final int index, final int defaultIndent) {
        if (this.lines == null) {
            return defaultIndent;
        }
        final LineInfo info = this.lines[index];
        if (info != null && (info.flags & 0x4) != 0x0) {
            return info.indent;
        }
        return defaultIndent;
    }
    
    int getLineVerticalIndent(final int index) {
        if (this.lines == null) {
            return 0;
        }
        final LineInfo info = this.lines[index];
        if (info != null && (info.flags & 0x200) != 0x0) {
            return info.verticalIndent;
        }
        return 0;
    }
    
    int getLineWrapIndent(final int index, final int defaultWrapIndent) {
        if (this.lines == null) {
            return defaultWrapIndent;
        }
        final LineInfo info = this.lines[index];
        if (info != null && (info.flags & 0x80) != 0x0) {
            return info.wrapIndent;
        }
        return defaultWrapIndent;
    }
    
    boolean getLineJustify(final int index, final boolean defaultJustify) {
        if (this.lines == null) {
            return defaultJustify;
        }
        final LineInfo info = this.lines[index];
        if (info != null && (info.flags & 0x8) != 0x0) {
            return info.justify;
        }
        return defaultJustify;
    }
    
    int[] getLineTabStops(final int index, final int[] defaultTabStops) {
        if (this.lines == null) {
            return defaultTabStops;
        }
        final LineInfo info = this.lines[index];
        if (info != null && (info.flags & 0x40) != 0x0) {
            return info.tabStops;
        }
        return defaultTabStops;
    }
    
    StyledTextLineSpacingProvider getLineSpacingProvider() {
        return this.lineSpacingProvider;
    }
    
    int getRangeIndex(final int offset, int low, int high) {
        if (this.styleCount == 0) {
            return 0;
        }
        if (this.ranges != null) {
            while (high - low > 2) {
                final int index = (high + low) / 2 / 2 * 2;
                final int end = this.ranges[index] + this.ranges[index + 1];
                if (end > offset) {
                    high = index;
                }
                else {
                    low = index;
                }
            }
        }
        else {
            while (high - low > 1) {
                final int index = (high + low) / 2;
                final int end = this.styles[index].start + this.styles[index].length;
                if (end > offset) {
                    high = index;
                }
                else {
                    low = index;
                }
            }
        }
        return high;
    }
    
    int[] getRanges(final int start, final int length) {
        if (length == 0) {
            return null;
        }
        final int end = start + length - 1;
        int[] newRanges;
        if (this.ranges != null) {
            final int rangeCount = this.styleCount << 1;
            final int rangeStart = this.getRangeIndex(start, -1, rangeCount);
            if (rangeStart >= rangeCount) {
                return null;
            }
            if (this.ranges[rangeStart] > end) {
                return null;
            }
            int rangeEnd = Math.min(rangeCount - 2, this.getRangeIndex(end, rangeStart - 1, rangeCount));
            if (this.ranges[rangeEnd] > end) {
                rangeEnd = Math.max(rangeStart, rangeEnd - 2);
            }
            newRanges = new int[rangeEnd - rangeStart + 2];
            System.arraycopy(this.ranges, rangeStart, newRanges, 0, newRanges.length);
        }
        else {
            final int rangeStart2 = this.getRangeIndex(start, -1, this.styleCount);
            if (rangeStart2 >= this.styleCount) {
                return null;
            }
            if (this.styles[rangeStart2].start > end) {
                return null;
            }
            int rangeEnd2 = Math.min(this.styleCount - 1, this.getRangeIndex(end, rangeStart2 - 1, this.styleCount));
            if (this.styles[rangeEnd2].start > end) {
                rangeEnd2 = Math.max(rangeStart2, rangeEnd2 - 1);
            }
            newRanges = new int[rangeEnd2 - rangeStart2 + 1 << 1];
            for (int i = rangeStart2, j = 0; i <= rangeEnd2; ++i, j += 2) {
                final StyleRange style = this.styles[i];
                newRanges[j] = style.start;
                newRanges[j + 1] = style.length;
            }
        }
        if (start > newRanges[0]) {
            newRanges[1] = newRanges[0] + newRanges[1] - start;
            newRanges[0] = start;
        }
        if (end < newRanges[newRanges.length - 2] + newRanges[newRanges.length - 1] - 1) {
            newRanges[newRanges.length - 1] = end - newRanges[newRanges.length - 2] + 1;
        }
        return newRanges;
    }
    
    StyleRange[] getStyleRanges(final int start, final int length, final boolean includeRanges) {
        if (length == 0) {
            return null;
        }
        final int end = start + length - 1;
        StyleRange[] newStyles;
        if (this.ranges != null) {
            final int rangeCount = this.styleCount << 1;
            final int rangeStart = this.getRangeIndex(start, -1, rangeCount);
            if (rangeStart >= rangeCount) {
                return null;
            }
            if (this.ranges[rangeStart] > end) {
                return null;
            }
            int rangeEnd = Math.min(rangeCount - 2, this.getRangeIndex(end, rangeStart - 1, rangeCount));
            if (this.ranges[rangeEnd] > end) {
                rangeEnd = Math.max(rangeStart, rangeEnd - 2);
            }
            newStyles = new StyleRange[(rangeEnd - rangeStart >> 1) + 1];
            if (includeRanges) {
                for (int i = rangeStart, j = 0; i <= rangeEnd; i += 2, ++j) {
                    newStyles[j] = (StyleRange)this.styles[i >> 1].clone();
                    newStyles[j].start = this.ranges[i];
                    newStyles[j].length = this.ranges[i + 1];
                }
            }
            else {
                System.arraycopy(this.styles, rangeStart >> 1, newStyles, 0, newStyles.length);
            }
        }
        else {
            final int rangeStart2 = this.getRangeIndex(start, -1, this.styleCount);
            if (rangeStart2 >= this.styleCount) {
                return null;
            }
            if (this.styles[rangeStart2].start > end) {
                return null;
            }
            int rangeEnd2 = Math.min(this.styleCount - 1, this.getRangeIndex(end, rangeStart2 - 1, this.styleCount));
            if (this.styles[rangeEnd2].start > end) {
                rangeEnd2 = Math.max(rangeStart2, rangeEnd2 - 1);
            }
            newStyles = new StyleRange[rangeEnd2 - rangeStart2 + 1];
            System.arraycopy(this.styles, rangeStart2, newStyles, 0, newStyles.length);
        }
        if (includeRanges || this.ranges == null) {
            StyleRange style = newStyles[0];
            if (start > style.start) {
                final StyleRange[] array = newStyles;
                final int n = 0;
                final StyleRange styleRange = (StyleRange)style.clone();
                array[n] = styleRange;
                style = styleRange;
                style.length = style.start + style.length - start;
                style.start = start;
            }
            style = newStyles[newStyles.length - 1];
            if (end < style.start + style.length - 1) {
                final StyleRange[] array2 = newStyles;
                final int n2 = newStyles.length - 1;
                final StyleRange styleRange2 = (StyleRange)style.clone();
                array2[n2] = styleRange2;
                style = styleRange2;
                style.length = end - style.start + 1;
            }
        }
        return newStyles;
    }
    
    StyleRange getStyleRange(final StyleRange style) {
        if (style.underline && style.underlineStyle == 4) {
            this.hasLinks = true;
        }
        if (style.start == 0 && style.length == 0 && style.fontStyle == 0) {
            return style;
        }
        final StyleRange styleRange2;
        final StyleRange clone;
        final StyleRange styleRange = clone = (styleRange2 = (StyleRange)style.clone());
        final int n = 0;
        styleRange.length = 0;
        styleRange2.start = 0;
        clone.fontStyle = 0;
        if (clone.font == null) {
            clone.font = this.getFont(style.fontStyle);
        }
        return clone;
    }
    
    TextLayout getTextLayout(final int lineIndex) {
        if (this.lineSpacingProvider == null) {
            return this.getTextLayout(lineIndex, this.styledText.getOrientation(), this.styledText.getWrapWidth(), this.styledText.lineSpacing);
        }
        int newLineSpacing = this.styledText.lineSpacing;
        final Integer spacing = this.lineSpacingProvider.getLineSpacing(lineIndex);
        if (spacing != null && spacing >= 0) {
            newLineSpacing = spacing;
        }
        if (this.isSameLineSpacing(lineIndex, newLineSpacing)) {
            return this.getTextLayout(lineIndex, this.styledText.getOrientation(), this.styledText.getWrapWidth(), newLineSpacing);
        }
        final TextLayout layout = this.getTextLayout(lineIndex, this.styledText.getOrientation(), this.styledText.getWrapWidth(), this.styledText.lineSpacing);
        if (layout.getSpacing() != newLineSpacing) {
            layout.setSpacing(newLineSpacing);
            if (this.lineSpacingComputing) {
                return layout;
            }
            try {
                this.lineSpacingComputing = true;
                this.styledText.resetCache(lineIndex, 1);
                this.styledText.setCaretLocations();
                this.styledText.redraw();
            }
            finally {
                this.lineSpacingComputing = false;
            }
        }
        return layout;
    }
    
    boolean isSameLineSpacing(final int lineIndex, final int newLineSpacing) {
        if (this.layouts == null) {
            return false;
        }
        final int layoutIndex = lineIndex - this.topIndex;
        if (0 <= layoutIndex && layoutIndex < this.layouts.length) {
            final TextLayout layout = this.layouts[layoutIndex];
            return layout != null && !layout.isDisposed() && layout.getSpacing() == newLineSpacing;
        }
        return false;
    }
    
    TextLayout getTextLayout(final int lineIndex, final int orientation, final int width, final int lineSpacing) {
        TextLayout layout = null;
        if (this.styledText != null) {
            final int topIndex = (this.styledText.topIndex > 0) ? (this.styledText.topIndex - 1) : 0;
            if (this.layouts == null || topIndex != this.topIndex) {
                final TextLayout[] newLayouts = new TextLayout[300];
                if (this.layouts != null) {
                    for (int i = 0; i < this.layouts.length; ++i) {
                        if (this.layouts[i] != null) {
                            final int layoutIndex = i + this.topIndex - topIndex;
                            if (0 <= layoutIndex && layoutIndex < newLayouts.length) {
                                newLayouts[layoutIndex] = this.layouts[i];
                            }
                            else {
                                this.layouts[i].dispose();
                            }
                        }
                    }
                }
                if (this.bullets != null && this.bulletsIndices != null && topIndex != this.topIndex) {
                    final int delta = topIndex - this.topIndex;
                    if (delta > 0) {
                        if (delta < this.bullets.length) {
                            System.arraycopy(this.bullets, delta, this.bullets, 0, this.bullets.length - delta);
                            System.arraycopy(this.bulletsIndices, delta, this.bulletsIndices, 0, this.bulletsIndices.length - delta);
                        }
                        int startIndex;
                        for (int j = startIndex = Math.max(0, this.bullets.length - delta); j < this.bullets.length; ++j) {
                            this.bullets[j] = null;
                        }
                    }
                    else {
                        if (-delta < this.bullets.length) {
                            System.arraycopy(this.bullets, 0, this.bullets, -delta, this.bullets.length + delta);
                            System.arraycopy(this.bulletsIndices, 0, this.bulletsIndices, -delta, this.bulletsIndices.length + delta);
                        }
                        for (int endIndex = Math.min(this.bullets.length, -delta), k = 0; k < endIndex; ++k) {
                            this.bullets[k] = null;
                        }
                    }
                }
                this.topIndex = topIndex;
                this.layouts = newLayouts;
            }
            if (this.layouts != null) {
                final int layoutIndex2 = lineIndex - topIndex;
                if (0 <= layoutIndex2 && layoutIndex2 < this.layouts.length) {
                    layout = this.layouts[layoutIndex2];
                    if (layout != null) {
                        if (lineIndex < this.lineSizes.length && this.getLineSize(lineIndex).canLayout()) {
                            return layout;
                        }
                    }
                    else {
                        final TextLayout[] layouts = this.layouts;
                        final int n = layoutIndex2;
                        final TextLayout textLayout = new TextLayout(this.device);
                        layouts[n] = textLayout;
                        layout = textLayout;
                    }
                }
            }
        }
        if (layout == null) {
            layout = new TextLayout(this.device);
        }
        String line = this.content.getLine(lineIndex);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        int[] segments = null;
        char[] segmentChars = null;
        int indent = 0;
        int wrapIndent = 0;
        int verticalIndent = 0;
        int alignment = 16384;
        int textDirection = orientation;
        boolean justify = false;
        int[] tabs = { this.tabWidth };
        Bullet bullet = null;
        int[] ranges = null;
        StyleRange[] styles = null;
        int rangeStart = 0;
        int styleCount = 0;
        StyledTextEvent event = null;
        if (this.styledText != null) {
            event = this.styledText.getBidiSegments(lineOffset, line);
            if (event != null) {
                segments = event.segments;
                segmentChars = event.segmentsChars;
            }
            event = this.styledText.getLineStyleData(lineOffset, line);
            indent = this.styledText.indent;
            wrapIndent = this.styledText.wrapIndent;
            alignment = this.styledText.alignment;
            if (this.styledText.isAutoDirection()) {
                textDirection = 100663296;
            }
            else if ((this.styledText.getStyle() & Integer.MIN_VALUE) != 0x0) {
                textDirection = ((orientation == 67108864) ? 33554432 : 67108864);
            }
            justify = this.styledText.justify;
            if (this.styledText.tabs != null) {
                tabs = this.styledText.tabs;
            }
        }
        if (event != null) {
            indent = event.indent;
            verticalIndent = event.verticalIndent;
            wrapIndent = event.wrapIndent;
            alignment = event.alignment;
            justify = event.justify;
            bullet = event.bullet;
            ranges = event.ranges;
            styles = event.styles;
            if (event.tabStops != null) {
                tabs = event.tabStops;
            }
            if (styles != null) {
                styleCount = styles.length;
                if (this.styledText.isFixedLineHeight()) {
                    for (int l = 0; l < styleCount; ++l) {
                        if (styles[l].isVariableHeight()) {
                            this.styledText.hasStyleWithVariableHeight = true;
                            this.styledText.verticalScrollOffset = -1;
                            this.styledText.redraw();
                            break;
                        }
                    }
                }
            }
            if (this.bullets == null || this.bulletsIndices == null) {
                this.bullets = new Bullet[300];
                this.bulletsIndices = new int[300];
            }
            final int index = lineIndex - this.topIndex;
            if (0 <= index && index < 300) {
                this.bullets[index] = bullet;
                this.bulletsIndices[index] = event.bulletIndex;
            }
        }
        else {
            if (this.lines != null) {
                final LineInfo info = this.lines[lineIndex];
                if (info != null) {
                    if ((info.flags & 0x4) != 0x0) {
                        indent = info.indent;
                    }
                    if ((info.flags & 0x200) != 0x0) {
                        verticalIndent = info.verticalIndent;
                    }
                    if ((info.flags & 0x80) != 0x0) {
                        wrapIndent = info.wrapIndent;
                    }
                    if ((info.flags & 0x2) != 0x0) {
                        alignment = info.alignment;
                    }
                    if ((info.flags & 0x8) != 0x0) {
                        justify = info.justify;
                    }
                    if ((info.flags & 0x20) != 0x0) {
                        segments = info.segments;
                    }
                    if ((info.flags & 0x100) != 0x0) {
                        segmentChars = info.segmentsChars;
                    }
                    if ((info.flags & 0x40) != 0x0) {
                        tabs = info.tabStops;
                    }
                }
            }
            if (this.bulletsIndices != null) {
                this.bullets = null;
                this.bulletsIndices = null;
            }
            if (this.bullets != null) {
                for (final Bullet b : this.bullets) {
                    if (b.indexOf(lineIndex) != -1) {
                        bullet = b;
                        break;
                    }
                }
            }
            ranges = this.ranges;
            styles = this.styles;
            styleCount = this.styleCount;
            if (ranges != null) {
                rangeStart = this.getRangeIndex(lineOffset, -1, styleCount << 1);
            }
            else {
                rangeStart = this.getRangeIndex(lineOffset, -1, styleCount);
            }
        }
        if (bullet != null) {
            final StyleRange style = bullet.style;
            final GlyphMetrics metrics = style.metrics;
            indent += metrics.width;
        }
        final List<StyleEntry> styleEntries = new ArrayList<StyleEntry>();
        int lastOffset = 0;
        final int length = line.length();
        if (styles != null) {
            if (ranges != null) {
                for (int rangeCount = styleCount << 1, m = rangeStart; m < rangeCount; m += 2) {
                    int start;
                    int end;
                    if (lineOffset > ranges[m]) {
                        start = 0;
                        end = Math.min(length, ranges[m + 1] - lineOffset + ranges[m]);
                    }
                    else {
                        start = ranges[m] - lineOffset;
                        end = Math.min(length, start + ranges[m + 1]);
                    }
                    if (start >= length) {
                        break;
                    }
                    if (lastOffset < start) {
                        styleEntries.add(new StyleEntry(null, lastOffset, start - 1));
                    }
                    final TextStyle style2 = this.getStyleRange(styles[m >> 1]);
                    final int endIndex2 = Math.max(start, Math.min(length, end + 1));
                    if (style2.metrics != null && line.substring(start, endIndex2).contains("\t")) {
                        line = line.substring(0, start) + line.substring(start, endIndex2).replace('\t', ' ') + ((end < line.length()) ? line.substring(end + 1, line.length()) : "");
                    }
                    styleEntries.add(new StyleEntry(style2, start, end));
                    lastOffset = Math.max(lastOffset, end);
                }
            }
            else {
                for (int m2 = rangeStart; m2 < styleCount; ++m2) {
                    int start2;
                    int end2;
                    if (lineOffset > styles[m2].start) {
                        start2 = 0;
                        end2 = Math.min(length, styles[m2].length - lineOffset + styles[m2].start);
                    }
                    else {
                        start2 = styles[m2].start - lineOffset;
                        end2 = Math.min(length, start2 + styles[m2].length);
                    }
                    if (start2 >= length) {
                        break;
                    }
                    if (lastOffset < start2) {
                        styleEntries.add(new StyleEntry(null, lastOffset, start2 - 1));
                    }
                    final TextStyle style3 = this.getStyleRange(styles[m2]);
                    final int endIndex3 = Math.max(start2, Math.min(length, end2 + 1));
                    if (style3.metrics != null && line.substring(start2, endIndex3).contains("\t")) {
                        line = line.substring(0, start2) + line.substring(start2, endIndex3).replace('\t', ' ') + ((end2 < line.length()) ? line.substring(end2 + 1, line.length()) : "");
                    }
                    styleEntries.add(new StyleEntry(style3, start2, end2));
                    lastOffset = Math.max(lastOffset, end2);
                }
            }
        }
        if (lastOffset < length) {
            styleEntries.add(new StyleEntry(null, lastOffset, length));
        }
        layout.setFont(this.regularFont);
        layout.setAscent(this.ascent);
        layout.setDescent(this.descent);
        layout.setText(line);
        layout.setOrientation(orientation);
        layout.setSegments(segments);
        layout.setSegmentsChars(segmentChars);
        layout.setWidth(width);
        layout.setSpacing(lineSpacing);
        layout.setTabs(tabs);
        layout.setDefaultTabWidth(this.tabLength);
        layout.setIndent(indent);
        layout.setVerticalIndent(verticalIndent);
        layout.setWrapIndent(wrapIndent);
        layout.setAlignment(alignment);
        layout.setJustify(justify);
        layout.setTextDirection(textDirection);
        for (final StyleEntry styleEntry : styleEntries) {
            layout.setStyle(styleEntry.style, styleEntry.start, styleEntry.end);
        }
        if (this.styledText != null && this.styledText.ime != null) {
            final IME ime = this.styledText.ime;
            final int compositionOffset = ime.getCompositionOffset();
            if (compositionOffset != -1) {
                final int commitCount = ime.getCommitCount();
                final int compositionLength = ime.getText().length();
                if (compositionLength != commitCount) {
                    final int compositionLine = this.content.getLineAtOffset(compositionOffset);
                    if (compositionLine == lineIndex) {
                        final int[] imeRanges = ime.getRanges();
                        final TextStyle[] imeStyles = ime.getStyles();
                        if (imeRanges.length > 0) {
                            for (int i2 = 0; i2 < imeStyles.length; ++i2) {
                                final int start3 = imeRanges[i2 * 2] - lineOffset;
                                final int end3 = imeRanges[i2 * 2 + 1] - lineOffset;
                                final TextStyle imeStyle = imeStyles[i2];
                                for (int j2 = start3; j2 <= end3 && 0 <= j2 && j2 < length; ++j2) {
                                    TextStyle userStyle = layout.getStyle(cap(layout, j2));
                                    if (userStyle == null && j2 > 0) {
                                        userStyle = layout.getStyle(cap(layout, j2 - 1));
                                    }
                                    if (userStyle == null && j2 + 1 < length) {
                                        userStyle = layout.getStyle(cap(layout, j2 + 1));
                                    }
                                    if (userStyle == null) {
                                        layout.setStyle(imeStyle, j2, j2);
                                    }
                                    else {
                                        final TextStyle newStyle = new TextStyle(imeStyle);
                                        if (newStyle.font == null) {
                                            newStyle.font = userStyle.font;
                                        }
                                        if (newStyle.foreground == null) {
                                            newStyle.foreground = userStyle.foreground;
                                        }
                                        if (newStyle.background == null) {
                                            newStyle.background = userStyle.background;
                                        }
                                        layout.setStyle(newStyle, j2, j2);
                                    }
                                }
                            }
                        }
                        else {
                            final int start4 = compositionOffset - lineOffset;
                            final int end4 = start4 + compositionLength - 1;
                            TextStyle userStyle2 = layout.getStyle(cap(layout, start4));
                            if (userStyle2 == null) {
                                if (start4 > 0) {
                                    userStyle2 = layout.getStyle(cap(layout, start4 - 1));
                                }
                                if (userStyle2 == null && end4 + 1 < length) {
                                    userStyle2 = layout.getStyle(cap(layout, end4 + 1));
                                }
                                if (userStyle2 != null) {
                                    final TextStyle newStyle2 = new TextStyle();
                                    newStyle2.font = userStyle2.font;
                                    newStyle2.foreground = userStyle2.foreground;
                                    newStyle2.background = userStyle2.background;
                                    layout.setStyle(newStyle2, start4, end4);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.styledText != null && this.styledText.isFixedLineHeight()) {
            int index2 = -1;
            final int lineCount = layout.getLineCount();
            int height = this.getLineHeight();
            for (int i3 = 0; i3 < lineCount; ++i3) {
                final int lineHeight = layout.getLineBounds(i3).height;
                if (lineHeight > height) {
                    height = lineHeight;
                    index2 = i3;
                }
            }
            if (index2 != -1) {
                final FontMetrics metrics2 = layout.getLineMetrics(index2);
                this.ascent = metrics2.getAscent() + metrics2.getLeading();
                this.descent = metrics2.getDescent();
                if (this.layouts != null) {
                    for (final TextLayout l2 : this.layouts) {
                        if (l2 != null && l2 != layout) {
                            l2.setAscent(this.ascent);
                            l2.setDescent(this.descent);
                        }
                    }
                }
                this.styledText.calculateScrollBars();
                if (this.styledText.verticalScrollOffset != 0) {
                    final int topIndex2 = this.styledText.topIndex;
                    final int topIndexY = this.styledText.topIndexY;
                    final int lineHeight2 = this.getLineHeight();
                    int newVerticalScrollOffset;
                    if (topIndexY >= 0) {
                        newVerticalScrollOffset = (topIndex2 - 1) * lineHeight2 + lineHeight2 - topIndexY;
                    }
                    else {
                        newVerticalScrollOffset = topIndex2 * lineHeight2 - topIndexY;
                    }
                    this.styledText.scrollVertical(newVerticalScrollOffset - this.styledText.verticalScrollOffset, true);
                }
                if (this.styledText.isBidiCaret()) {
                    this.styledText.createCaretBitmaps();
                }
                this.styledText.caretDirection = 0;
                this.styledText.setCaretLocations();
                this.styledText.redraw();
            }
        }
        return layout;
    }
    
    int getWidth() {
        return this.maxWidth;
    }
    
    void reset() {
        if (this.layouts != null) {
            for (final TextLayout layout : this.layouts) {
                if (layout != null) {
                    layout.dispose();
                }
            }
            this.layouts = null;
        }
        this.topIndex = -1;
        final int stylesSetCount = 0;
        this.lineCount = 0;
        this.styleCount = 0;
        this.stylesSetCount = 0;
        this.ranges = null;
        this.styles = null;
        this.stylesSet = null;
        this.lines = null;
        this.lineSizes = null;
        this.bullets = null;
        this.bulletsIndices = null;
        this.redrawLines = null;
        this.hasLinks = false;
    }
    
    void reset(final int startLine, final int lineCount) {
        final int endLine = startLine + lineCount;
        if (startLine < 0 || endLine > this.lineSizes.length) {
            return;
        }
        final SortedSet<Integer> lines = new TreeSet<Integer>();
        for (int i = startLine; i < endLine; ++i) {
            lines.add(i);
        }
        this.reset(lines);
    }
    
    void reset(final Set<Integer> lines) {
        if (lines == null || lines.isEmpty()) {
            return;
        }
        int resetLineCount = 0;
        for (final Integer line : lines) {
            if (line >= 0 || line < this.lineCount) {
                ++resetLineCount;
                this.getLineSize(line).resetSize();
            }
        }
        if (this.linesInAverageLineHeight > resetLineCount) {
            this.linesInAverageLineHeight -= resetLineCount;
        }
        else {
            this.linesInAverageLineHeight = 0;
            this.averageLineHeight = 0.0f;
        }
        if (lines.contains(this.maxWidthLineIndex)) {
            this.maxWidth = 0;
            this.maxWidthLineIndex = -1;
            if (resetLineCount != this.lineCount) {
                for (int i = 0; i < this.lineCount; ++i) {
                    final LineSizeInfo lineSize = this.getLineSize(i);
                    if (lineSize.width > this.maxWidth) {
                        this.maxWidth = lineSize.width;
                        this.maxWidthLineIndex = i;
                    }
                }
            }
        }
    }
    
    void setContent(final StyledTextContent content) {
        this.reset();
        this.content = content;
        this.lineCount = content.getLineCount();
        this.lineSizes = new LineSizeInfo[this.lineCount];
        this.maxWidth = 0;
        this.maxWidthLineIndex = -1;
        this.reset(0, this.lineCount);
    }
    
    void setFont(final Font font, final int tabs) {
        TextLayout layout = new TextLayout(this.device);
        layout.setFont(this.regularFont);
        this.tabLength = tabs;
        if (font != null) {
            if (this.boldFont != null) {
                this.boldFont.dispose();
            }
            if (this.italicFont != null) {
                this.italicFont.dispose();
            }
            if (this.boldItalicFont != null) {
                this.boldItalicFont.dispose();
            }
            final Font boldFont = null;
            this.boldItalicFont = boldFont;
            this.italicFont = boldFont;
            this.boldFont = boldFont;
            this.regularFont = font;
            layout.setText("    ");
            layout.setFont(font);
            layout.setStyle(new TextStyle(this.getFont(0), null, null), 0, 0);
            layout.setStyle(new TextStyle(this.getFont(1), null, null), 1, 1);
            layout.setStyle(new TextStyle(this.getFont(2), null, null), 2, 2);
            layout.setStyle(new TextStyle(this.getFont(3), null, null), 3, 3);
            final FontMetrics metrics = layout.getLineMetrics(0);
            this.ascent = metrics.getAscent() + metrics.getLeading();
            this.descent = metrics.getDescent();
            this.boldFont.dispose();
            this.italicFont.dispose();
            this.boldItalicFont.dispose();
            final Font boldFont2 = null;
            this.boldItalicFont = boldFont2;
            this.italicFont = boldFont2;
            this.boldFont = boldFont2;
        }
        layout.dispose();
        layout = new TextLayout(this.device);
        layout.setFont(this.regularFont);
        final StringBuilder tabBuffer = new StringBuilder(tabs);
        for (int i = 0; i < tabs; ++i) {
            tabBuffer.append(' ');
        }
        layout.setText(tabBuffer.toString());
        this.tabWidth = layout.getBounds().width;
        layout.dispose();
        if (this.styledText != null) {
            final GC gc = new GC((Drawable)this.styledText);
            this.averageCharWidth = (int)gc.getFontMetrics().getAverageCharacterWidth();
            this.fixedPitch = (gc.stringExtent("l").x == gc.stringExtent("W").x);
            gc.dispose();
        }
    }
    
    void setLineAlignment(final int startLine, final int count, final int alignment) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = startLine; i < startLine + count; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo2;
            final LineInfo lineInfo = lineInfo2 = this.lines[i];
            lineInfo2.flags |= 0x2;
            this.lines[i].alignment = alignment;
        }
    }
    
    void setLineBackground(final int startLine, final int count, final Color background) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = startLine; i < startLine + count; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo2;
            final LineInfo lineInfo = lineInfo2 = this.lines[i];
            lineInfo2.flags |= 0x1;
            this.lines[i].background = background;
        }
    }
    
    void setLineBullet(final int startLine, final int count, final Bullet bullet) {
        if (this.bulletsIndices != null) {
            this.bulletsIndices = null;
            this.bullets = null;
        }
        if (this.bullets == null) {
            if (bullet == null) {
                return;
            }
            (this.bullets = new Bullet[] { null })[0] = bullet;
        }
        int index;
        for (index = 0; index < this.bullets.length && bullet != this.bullets[index]; ++index) {}
        if (bullet != null) {
            if (index == this.bullets.length) {
                final Bullet[] newBulletsList = new Bullet[this.bullets.length + 1];
                System.arraycopy(this.bullets, 0, newBulletsList, 0, this.bullets.length);
                newBulletsList[index] = bullet;
                this.bullets = newBulletsList;
            }
            bullet.addIndices(startLine, count);
        }
        else {
            this.updateBullets(startLine, count, 0, false);
            this.styledText.redrawLinesBullet(this.redrawLines);
            this.redrawLines = null;
        }
    }
    
    void setLineIndent(final int startLine, final int count, final int indent) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = startLine; i < startLine + count; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo2;
            final LineInfo lineInfo = lineInfo2 = this.lines[i];
            lineInfo2.flags |= 0x4;
            this.lines[i].indent = indent;
        }
    }
    
    void setLineVerticalIndent(final int lineIndex, final int verticalLineIndent) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        if (this.lines[lineIndex] == null) {
            this.lines[lineIndex] = new LineInfo();
        }
        final LineInfo lineInfo2;
        final LineInfo lineInfo = lineInfo2 = this.lines[lineIndex];
        lineInfo2.flags |= 0x200;
        final int delta = verticalLineIndent - this.lines[lineIndex].verticalIndent;
        this.lines[lineIndex].verticalIndent = verticalLineIndent;
        final LineSizeInfo info = this.getLineSize(lineIndex);
        if (!info.needsRecalculateHeight()) {
            final LineSizeInfo lineSizeInfo2;
            final LineSizeInfo lineSizeInfo = lineSizeInfo2 = info;
            lineSizeInfo2.height += delta;
        }
    }
    
    void setLineWrapIndent(final int startLine, final int count, final int wrapIndent) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = startLine; i < startLine + count; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo2;
            final LineInfo lineInfo = lineInfo2 = this.lines[i];
            lineInfo2.flags |= 0x80;
            this.lines[i].wrapIndent = wrapIndent;
        }
    }
    
    void setLineJustify(final int startLine, final int count, final boolean justify) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = startLine; i < startLine + count; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo2;
            final LineInfo lineInfo = lineInfo2 = this.lines[i];
            lineInfo2.flags |= 0x8;
            this.lines[i].justify = justify;
        }
    }
    
    void setLineSegments(final int startLine, final int count, final int[] segments) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = startLine; i < startLine + count; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo2;
            final LineInfo lineInfo = lineInfo2 = this.lines[i];
            lineInfo2.flags |= 0x20;
            this.lines[i].segments = segments;
        }
    }
    
    void setLineSegmentChars(final int startLine, final int count, final char[] segmentChars) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = startLine; i < startLine + count; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo2;
            final LineInfo lineInfo = lineInfo2 = this.lines[i];
            lineInfo2.flags |= 0x100;
            this.lines[i].segmentsChars = segmentChars;
        }
    }
    
    void setLineTabStops(final int startLine, final int count, final int[] tabStops) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = startLine; i < startLine + count; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo2;
            final LineInfo lineInfo = lineInfo2 = this.lines[i];
            lineInfo2.flags |= 0x40;
            this.lines[i].tabStops = tabStops;
        }
    }
    
    void setLineSpacingProvider(final StyledTextLineSpacingProvider lineSpacingProvider) {
        this.lineSpacingProvider = lineSpacingProvider;
    }
    
    void setStyleRanges(int[] newRanges, StyleRange[] newStyles) {
        if (newStyles == null) {
            final int n = 0;
            this.styleCount = 0;
            this.stylesSetCount = 0;
            this.ranges = null;
            this.styles = null;
            this.stylesSet = null;
            this.hasLinks = false;
            return;
        }
        if (newRanges == null) {
            newRanges = new int[newStyles.length << 1];
            final StyleRange[] tmpStyles = new StyleRange[newStyles.length];
            if (this.stylesSet == null) {
                this.stylesSet = new StyleRange[4];
            }
            int i = 0;
            int j = 0;
            while (i < newStyles.length) {
                final StyleRange newStyle = newStyles[i];
                newRanges[j++] = newStyle.start;
                newRanges[j++] = newStyle.length;
                int index;
                for (index = 0; index < this.stylesSetCount && !this.stylesSet[index].similarTo(newStyle); ++index) {}
                if (index == this.stylesSetCount) {
                    if (this.stylesSetCount == this.stylesSet.length) {
                        final StyleRange[] tmpStylesSet = new StyleRange[this.stylesSetCount + 4];
                        System.arraycopy(this.stylesSet, 0, tmpStylesSet, 0, this.stylesSetCount);
                        this.stylesSet = tmpStylesSet;
                    }
                    this.stylesSet[this.stylesSetCount++] = newStyle;
                }
                tmpStyles[i] = this.stylesSet[index];
                ++i;
            }
            newStyles = tmpStyles;
        }
        if (this.styleCount == 0) {
            if (newRanges != null) {
                System.arraycopy(newRanges, 0, this.ranges = new int[newRanges.length], 0, this.ranges.length);
            }
            System.arraycopy(newStyles, 0, this.styles = new StyleRange[newStyles.length], 0, this.styles.length);
            this.styleCount = newStyles.length;
            return;
        }
        if (newRanges != null && this.ranges == null) {
            this.ranges = new int[this.styles.length << 1];
            int k = 0;
            int l = 0;
            while (k < this.styleCount) {
                this.ranges[l++] = this.styles[k].start;
                this.ranges[l++] = this.styles[k].length;
                ++k;
            }
        }
        if (newRanges == null && this.ranges != null) {
            newRanges = new int[newStyles.length << 1];
            int k = 0;
            int l = 0;
            while (k < newStyles.length) {
                newRanges[l++] = newStyles[k].start;
                newRanges[l++] = newStyles[k].length;
                ++k;
            }
        }
        if (this.ranges != null) {
            int rangeCount = this.styleCount << 1;
            final int start = newRanges[0];
            int modifyStart = this.getRangeIndex(start, -1, rangeCount);
            boolean insert = modifyStart == rangeCount;
            if (!insert) {
                final int end = newRanges[newRanges.length - 2] + newRanges[newRanges.length - 1];
                final int modifyEnd = this.getRangeIndex(end, modifyStart - 1, rangeCount);
                insert = (modifyStart == modifyEnd && this.ranges[modifyStart] >= end);
            }
            if (insert) {
                this.addMerge(newRanges, newStyles, newRanges.length, modifyStart, modifyStart);
                return;
            }
            int modifyEnd2 = modifyStart;
            final int[] mergeRanges = new int[6];
            final StyleRange[] mergeStyles = new StyleRange[3];
            for (int m = 0; m < newRanges.length; m += 2) {
                final int newStart = newRanges[m];
                final int newEnd = newStart + newRanges[m + 1];
                if (newStart != newEnd) {
                    int modifyLast = 0;
                    int mergeCount = 0;
                    while (modifyEnd2 < rangeCount) {
                        if (newStart >= this.ranges[modifyStart] + this.ranges[modifyStart + 1]) {
                            modifyStart += 2;
                        }
                        if (this.ranges[modifyEnd2] + this.ranges[modifyEnd2 + 1] > newEnd) {
                            break;
                        }
                        modifyEnd2 += 2;
                    }
                    if (this.ranges[modifyStart] < newStart && newStart < this.ranges[modifyStart] + this.ranges[modifyStart + 1]) {
                        mergeStyles[mergeCount >> 1] = this.styles[modifyStart >> 1];
                        mergeRanges[mergeCount] = this.ranges[modifyStart];
                        mergeRanges[mergeCount + 1] = newStart - this.ranges[modifyStart];
                        mergeCount += 2;
                    }
                    mergeStyles[mergeCount >> 1] = newStyles[m >> 1];
                    mergeRanges[mergeCount] = newStart;
                    mergeRanges[mergeCount + 1] = newRanges[m + 1];
                    mergeCount += 2;
                    if (modifyEnd2 < rangeCount && this.ranges[modifyEnd2] < newEnd && newEnd < this.ranges[modifyEnd2] + this.ranges[modifyEnd2 + 1]) {
                        mergeStyles[mergeCount >> 1] = this.styles[modifyEnd2 >> 1];
                        mergeRanges[mergeCount] = newEnd;
                        mergeRanges[mergeCount + 1] = this.ranges[modifyEnd2] + this.ranges[modifyEnd2 + 1] - newEnd;
                        mergeCount += 2;
                        modifyLast = 2;
                    }
                    final int grow = this.addMerge(mergeRanges, mergeStyles, mergeCount, modifyStart, modifyEnd2 + modifyLast);
                    rangeCount += grow;
                    modifyStart = (modifyEnd2 += grow);
                }
            }
        }
        else {
            final int start2 = newStyles[0].start;
            int modifyStart2 = this.getRangeIndex(start2, -1, this.styleCount);
            boolean insert2 = modifyStart2 == this.styleCount;
            if (!insert2) {
                final int end2 = newStyles[newStyles.length - 1].start + newStyles[newStyles.length - 1].length;
                final int modifyEnd3 = this.getRangeIndex(end2, modifyStart2 - 1, this.styleCount);
                insert2 = (modifyStart2 == modifyEnd3 && this.styles[modifyStart2].start >= end2);
            }
            if (insert2) {
                this.addMerge(newStyles, newStyles.length, modifyStart2, modifyStart2);
                return;
            }
            int modifyEnd4 = modifyStart2;
            final StyleRange[] mergeStyles2 = new StyleRange[3];
            for (final StyleRange newStyle2 : newStyles) {
                final int newStart2 = newStyle2.start;
                final int newEnd2 = newStart2 + newStyle2.length;
                if (newStart2 != newEnd2) {
                    int modifyLast2 = 0;
                    int mergeCount2 = 0;
                    while (modifyEnd4 < this.styleCount) {
                        if (newStart2 >= this.styles[modifyStart2].start + this.styles[modifyStart2].length) {
                            ++modifyStart2;
                        }
                        if (this.styles[modifyEnd4].start + this.styles[modifyEnd4].length > newEnd2) {
                            break;
                        }
                        ++modifyEnd4;
                    }
                    StyleRange style = this.styles[modifyStart2];
                    if (style.start < newStart2 && newStart2 < style.start + style.length) {
                        final StyleRange[] array2 = mergeStyles2;
                        final int n2 = mergeCount2++;
                        final StyleRange styleRange = (StyleRange)style.clone();
                        array2[n2] = styleRange;
                        style = styleRange;
                        style.length = newStart2 - style.start;
                    }
                    mergeStyles2[mergeCount2++] = newStyle2;
                    if (modifyEnd4 < this.styleCount) {
                        style = this.styles[modifyEnd4];
                        if (style.start < newEnd2 && newEnd2 < style.start + style.length) {
                            final StyleRange[] array3 = mergeStyles2;
                            final int n3 = mergeCount2++;
                            final StyleRange styleRange2 = (StyleRange)style.clone();
                            array3[n3] = styleRange2;
                            final StyleRange styleRange4;
                            final StyleRange styleRange3 = styleRange4 = (style = styleRange2);
                            styleRange4.length += style.start - newEnd2;
                            style.start = newEnd2;
                            modifyLast2 = 1;
                        }
                    }
                    final int grow2 = this.addMerge(mergeStyles2, mergeCount2, modifyStart2, modifyEnd4 + modifyLast2);
                    modifyStart2 = (modifyEnd4 += grow2);
                }
            }
        }
    }
    
    void textChanging(final TextChangingEvent event) {
        final int start = event.start;
        final int newCharCount = event.newCharCount;
        final int replaceCharCount = event.replaceCharCount;
        final int newLineCount = event.newLineCount;
        final int replaceLineCount = event.replaceLineCount;
        this.updateRanges(start, replaceCharCount, newCharCount);
        int startLine = this.content.getLineAtOffset(start);
        if (replaceCharCount == this.content.getCharCount()) {
            this.lines = null;
        }
        if (replaceLineCount == this.lineCount) {
            this.lineCount = newLineCount;
            this.lineSizes = new LineSizeInfo[this.lineCount];
            this.reset(0, this.lineCount);
        }
        else {
            int startIndex = startLine + replaceLineCount + 1;
            int endIndex = startLine + newLineCount + 1;
            if (this.lineCount < startLine) {
                SWT.error(6, null, "bug 478020: lineCount < startLine: " + this.lineCount + ":" + startLine);
            }
            if (this.lineCount < startIndex) {
                SWT.error(6, null, "bug 478020: lineCount < startIndex: " + this.lineCount + ":" + startIndex);
            }
            final int delta = newLineCount - replaceLineCount;
            if (this.lineCount + delta > this.lineSizes.length) {
                final LineSizeInfo[] newLineSizes = new LineSizeInfo[this.lineCount + delta + 32];
                System.arraycopy(this.lineSizes, 0, newLineSizes, 0, this.lineCount);
                this.lineSizes = newLineSizes;
            }
            if (this.lines != null && this.lineCount + delta > this.lines.length) {
                final LineInfo[] newLines = new LineInfo[this.lineCount + delta + 32];
                System.arraycopy(this.lines, 0, newLines, 0, this.lineCount);
                this.lines = newLines;
            }
            System.arraycopy(this.lineSizes, startIndex, this.lineSizes, endIndex, this.lineCount - startIndex);
            for (int i = startLine; i < endIndex; ++i) {
                this.lineSizes[i] = null;
            }
            for (int i = this.lineCount + delta; i < this.lineCount; ++i) {
                this.lineSizes[i] = null;
            }
            if (this.layouts != null) {
                final int layoutStartLine = startLine - this.topIndex;
                final int layoutEndLine = layoutStartLine + replaceLineCount + 1;
                for (int j = layoutStartLine; j < layoutEndLine; ++j) {
                    if (0 <= j && j < this.layouts.length) {
                        if (this.layouts[j] != null) {
                            this.layouts[j].dispose();
                        }
                        this.layouts[j] = null;
                        if (this.bullets != null && this.bulletsIndices != null) {
                            this.bullets[j] = null;
                        }
                    }
                }
                if (delta > 0) {
                    for (int j = this.layouts.length - 1; j >= layoutEndLine; --j) {
                        if (0 <= j && j < this.layouts.length) {
                            endIndex = j + delta;
                            if (0 <= endIndex && endIndex < this.layouts.length) {
                                this.layouts[endIndex] = this.layouts[j];
                                this.layouts[j] = null;
                                if (this.bullets != null && this.bulletsIndices != null) {
                                    this.bullets[endIndex] = this.bullets[j];
                                    this.bulletsIndices[endIndex] = this.bulletsIndices[j];
                                    this.bullets[j] = null;
                                }
                            }
                            else {
                                if (this.layouts[j] != null) {
                                    this.layouts[j].dispose();
                                }
                                this.layouts[j] = null;
                                if (this.bullets != null && this.bulletsIndices != null) {
                                    this.bullets[j] = null;
                                }
                            }
                        }
                    }
                }
                else if (delta < 0) {
                    for (int j = layoutEndLine; j < this.layouts.length; ++j) {
                        if (0 <= j && j < this.layouts.length) {
                            endIndex = j + delta;
                            if (0 <= endIndex && endIndex < this.layouts.length) {
                                this.layouts[endIndex] = this.layouts[j];
                                this.layouts[j] = null;
                                if (this.bullets != null && this.bulletsIndices != null) {
                                    this.bullets[endIndex] = this.bullets[j];
                                    this.bulletsIndices[endIndex] = this.bulletsIndices[j];
                                    this.bullets[j] = null;
                                }
                            }
                            else {
                                if (this.layouts[j] != null) {
                                    this.layouts[j].dispose();
                                }
                                this.layouts[j] = null;
                                if (this.bullets != null && this.bulletsIndices != null) {
                                    this.bullets[j] = null;
                                }
                            }
                        }
                    }
                }
            }
            if (replaceLineCount != 0 || newLineCount != 0) {
                final int startLineOffset = this.content.getOffsetAtLine(startLine);
                if (startLineOffset != start) {
                    ++startLine;
                }
                this.updateBullets(startLine, replaceLineCount, newLineCount, true);
                if (this.lines != null) {
                    startIndex = startLine + replaceLineCount;
                    endIndex = startLine + newLineCount;
                    System.arraycopy(this.lines, startIndex, this.lines, endIndex, this.lineCount - startIndex);
                    for (int k = startLine; k < endIndex; ++k) {
                        this.lines[k] = null;
                    }
                    for (int k = this.lineCount + delta; k < this.lineCount; ++k) {
                        this.lines[k] = null;
                    }
                }
            }
            this.lineCount += delta;
            if (this.maxWidthLineIndex != -1 && startLine <= this.maxWidthLineIndex && this.maxWidthLineIndex <= startLine + replaceLineCount) {
                this.maxWidth = 0;
                this.maxWidthLineIndex = -1;
                for (int i = 0; i < this.lineCount; ++i) {
                    final LineSizeInfo lineSize = this.getLineSize(i);
                    if (lineSize.width > this.maxWidth) {
                        this.maxWidth = lineSize.width;
                        this.maxWidthLineIndex = i;
                    }
                }
            }
        }
    }
    
    void updateBullets(final int startLine, final int replaceLineCount, final int newLineCount, final boolean update) {
        if (this.bullets == null) {
            return;
        }
        if (this.bulletsIndices != null) {
            return;
        }
        for (final Bullet bullet : this.bullets) {
            final int[] lines = bullet.removeIndices(startLine, replaceLineCount, newLineCount, update);
            if (lines != null) {
                if (this.redrawLines == null) {
                    this.redrawLines = lines;
                }
                else {
                    final int[] newRedrawBullets = new int[this.redrawLines.length + lines.length];
                    System.arraycopy(this.redrawLines, 0, newRedrawBullets, 0, this.redrawLines.length);
                    System.arraycopy(lines, 0, newRedrawBullets, this.redrawLines.length, lines.length);
                    this.redrawLines = newRedrawBullets;
                }
            }
        }
        int removed = 0;
        for (final Bullet bullet2 : this.bullets) {
            if (bullet2.size() == 0) {
                ++removed;
            }
        }
        if (removed > 0) {
            if (removed == this.bullets.length) {
                this.bullets = null;
            }
            else {
                final Bullet[] newBulletsList = new Bullet[this.bullets.length - removed];
                int i = 0;
                int j = 0;
                while (i < this.bullets.length) {
                    final Bullet bullet2 = this.bullets[i];
                    if (bullet2.size() > 0) {
                        newBulletsList[j++] = bullet2;
                    }
                    ++i;
                }
                this.bullets = newBulletsList;
            }
        }
    }
    
    void updateRanges(final int start, final int replaceCharCount, final int newCharCount) {
        if (this.styleCount == 0 || (replaceCharCount == 0 && newCharCount == 0)) {
            return;
        }
        if (this.ranges != null) {
            int rangeCount = this.styleCount << 1;
            int modifyStart = this.getRangeIndex(start, -1, rangeCount);
            if (modifyStart == rangeCount) {
                return;
            }
            final int end = start + replaceCharCount;
            int modifyEnd = this.getRangeIndex(end, modifyStart - 1, rangeCount);
            final int offset = newCharCount - replaceCharCount;
            if (modifyStart == modifyEnd && this.ranges[modifyStart] < start && end < this.ranges[modifyEnd] + this.ranges[modifyEnd + 1]) {
                if (newCharCount == 0) {
                    final int[] ranges = this.ranges;
                    final int n = modifyStart + 1;
                    final int[] array = ranges;
                    final int n4 = n;
                    array[n4] -= replaceCharCount;
                    modifyEnd += 2;
                }
                else {
                    if (rangeCount + 2 > this.ranges.length) {
                        final int[] newRanges = new int[this.ranges.length + 64];
                        System.arraycopy(this.ranges, 0, newRanges, 0, rangeCount);
                        this.ranges = newRanges;
                        final StyleRange[] newStyles = new StyleRange[this.styles.length + 32];
                        System.arraycopy(this.styles, 0, newStyles, 0, this.styleCount);
                        this.styles = newStyles;
                    }
                    System.arraycopy(this.ranges, modifyStart + 2, this.ranges, modifyStart + 4, rangeCount - (modifyStart + 2));
                    System.arraycopy(this.styles, modifyStart + 2 >> 1, this.styles, modifyStart + 4 >> 1, this.styleCount - (modifyStart + 2 >> 1));
                    this.ranges[modifyStart + 3] = this.ranges[modifyStart] + this.ranges[modifyStart + 1] - end;
                    this.ranges[modifyStart + 2] = start + newCharCount;
                    this.ranges[modifyStart + 1] = start - this.ranges[modifyStart];
                    this.styles[(modifyStart >> 1) + 1] = this.styles[modifyStart >> 1];
                    rangeCount += 2;
                    ++this.styleCount;
                    modifyEnd += 4;
                }
                if (offset != 0) {
                    for (int i = modifyEnd; i < rangeCount; i += 2) {
                        final int[] ranges2 = this.ranges;
                        final int n2 = i;
                        final int[] array2 = ranges2;
                        final int n5 = n2;
                        array2[n5] += offset;
                    }
                }
            }
            else {
                if (this.ranges[modifyStart] < start && start < this.ranges[modifyStart] + this.ranges[modifyStart + 1]) {
                    this.ranges[modifyStart + 1] = start - this.ranges[modifyStart];
                    modifyStart += 2;
                }
                if (modifyEnd < rangeCount && this.ranges[modifyEnd] < end && end < this.ranges[modifyEnd] + this.ranges[modifyEnd + 1]) {
                    this.ranges[modifyEnd + 1] = this.ranges[modifyEnd] + this.ranges[modifyEnd + 1] - end;
                    this.ranges[modifyEnd] = end;
                }
                if (offset != 0) {
                    for (int i = modifyEnd; i < rangeCount; i += 2) {
                        final int[] ranges3 = this.ranges;
                        final int n3 = i;
                        final int[] array3 = ranges3;
                        final int n6 = n3;
                        array3[n6] += offset;
                    }
                }
                System.arraycopy(this.ranges, modifyEnd, this.ranges, modifyStart, rangeCount - modifyEnd);
                System.arraycopy(this.styles, modifyEnd >> 1, this.styles, modifyStart >> 1, this.styleCount - (modifyEnd >> 1));
                this.styleCount -= modifyEnd - modifyStart >> 1;
            }
        }
        else {
            int modifyStart2 = this.getRangeIndex(start, -1, this.styleCount);
            if (modifyStart2 == this.styleCount) {
                return;
            }
            final int end2 = start + replaceCharCount;
            int modifyEnd2 = this.getRangeIndex(end2, modifyStart2 - 1, this.styleCount);
            final int offset2 = newCharCount - replaceCharCount;
            if (modifyStart2 == modifyEnd2 && this.styles[modifyStart2].start < start && end2 < this.styles[modifyEnd2].start + this.styles[modifyEnd2].length) {
                if (newCharCount == 0) {
                    final StyleRange styleRange4;
                    final StyleRange styleRange = styleRange4 = this.styles[modifyStart2];
                    styleRange4.length -= replaceCharCount;
                    ++modifyEnd2;
                }
                else {
                    if (this.styleCount + 1 > this.styles.length) {
                        final StyleRange[] newStyles2 = new StyleRange[this.styles.length + 32];
                        System.arraycopy(this.styles, 0, newStyles2, 0, this.styleCount);
                        this.styles = newStyles2;
                    }
                    System.arraycopy(this.styles, modifyStart2 + 1, this.styles, modifyStart2 + 2, this.styleCount - (modifyStart2 + 1));
                    this.styles[modifyStart2 + 1] = (StyleRange)this.styles[modifyStart2].clone();
                    this.styles[modifyStart2 + 1].length = this.styles[modifyStart2].start + this.styles[modifyStart2].length - end2;
                    this.styles[modifyStart2 + 1].start = start + newCharCount;
                    this.styles[modifyStart2].length = start - this.styles[modifyStart2].start;
                    ++this.styleCount;
                    modifyEnd2 += 2;
                }
                if (offset2 != 0) {
                    for (int j = modifyEnd2; j < this.styleCount; ++j) {
                        final StyleRange styleRange5;
                        final StyleRange styleRange2 = styleRange5 = this.styles[j];
                        styleRange5.start += offset2;
                    }
                }
            }
            else {
                if (this.styles[modifyStart2].start < start && start < this.styles[modifyStart2].start + this.styles[modifyStart2].length) {
                    this.styles[modifyStart2].length = start - this.styles[modifyStart2].start;
                    ++modifyStart2;
                }
                if (modifyEnd2 < this.styleCount && this.styles[modifyEnd2].start < end2 && end2 < this.styles[modifyEnd2].start + this.styles[modifyEnd2].length) {
                    this.styles[modifyEnd2].length = this.styles[modifyEnd2].start + this.styles[modifyEnd2].length - end2;
                    this.styles[modifyEnd2].start = end2;
                }
                if (offset2 != 0) {
                    for (int j = modifyEnd2; j < this.styleCount; ++j) {
                        final StyleRange styleRange6;
                        final StyleRange styleRange3 = styleRange6 = this.styles[j];
                        styleRange6.start += offset2;
                    }
                }
                System.arraycopy(this.styles, modifyEnd2, this.styles, modifyStart2, this.styleCount - modifyEnd2);
                this.styleCount -= modifyEnd2 - modifyStart2;
            }
        }
    }
    
    public boolean hasVerticalIndent() {
        return Arrays.stream(this.lines).filter(Objects::nonNull).mapToInt(line -> line.verticalIndent).anyMatch(n -> n != 0);
    }
    
    static class LineSizeInfo
    {
        private static final int RESETED_SIZE = -1;
        int height;
        int width;
        
        public LineSizeInfo() {
            this.resetSize();
        }
        
        void resetSize() {
            this.height = -1;
            this.width = -1;
        }
        
        boolean canLayout() {
            return !this.needsRecalculateWidth();
        }
        
        boolean needsRecalculateSize() {
            return this.needsRecalculateWidth() || this.needsRecalculateHeight();
        }
        
        boolean needsRecalculateWidth() {
            return this.width == -1;
        }
        
        boolean needsRecalculateHeight() {
            return this.height == -1;
        }
    }
    
    static class LineInfo
    {
        int flags;
        Color background;
        int alignment;
        int indent;
        int wrapIndent;
        boolean justify;
        int[] segments;
        char[] segmentsChars;
        int[] tabStops;
        int verticalIndent;
        
        public LineInfo() {
        }
        
        public LineInfo(final LineInfo info) {
            if (info != null) {
                this.flags = info.flags;
                this.background = info.background;
                this.alignment = info.alignment;
                this.indent = info.indent;
                this.wrapIndent = info.wrapIndent;
                this.justify = info.justify;
                this.segments = info.segments;
                this.segmentsChars = info.segmentsChars;
                this.tabStops = info.tabStops;
                this.verticalIndent = info.verticalIndent;
            }
        }
    }
    
    private static final class StyleEntry
    {
        public final int start;
        public final int end;
        public final TextStyle style;
        
        public StyleEntry(final TextStyle style, final int start, final int end) {
            this.style = style;
            this.start = start;
            this.end = end;
        }
    }
}
