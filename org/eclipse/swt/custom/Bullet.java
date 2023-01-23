//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.*;

public class Bullet
{
    public int type;
    public StyleRange style;
    public String text;
    int[] linesIndices;
    int count;
    
    public Bullet(final StyleRange style) {
        this(1, style);
    }
    
    public Bullet(final int type, final StyleRange style) {
        if (style == null) {
            SWT.error(4);
        }
        if (style.metrics == null) {
            SWT.error(4);
        }
        this.type = type;
        this.style = style;
    }
    
    void addIndices(final int startLine, final int lineCount) {
        if (this.linesIndices == null) {
            this.linesIndices = new int[lineCount];
            this.count = lineCount;
            for (int i = 0; i < lineCount; ++i) {
                this.linesIndices[i] = startLine + i;
            }
        }
        else {
            int modifyStart;
            for (modifyStart = 0; modifyStart < this.count && startLine > this.linesIndices[modifyStart]; ++modifyStart) {}
            int modifyEnd;
            for (modifyEnd = modifyStart; modifyEnd < this.count && startLine + lineCount > this.linesIndices[modifyEnd]; ++modifyEnd) {}
            final int newSize = modifyStart + lineCount + this.count - modifyEnd;
            if (newSize > this.linesIndices.length) {
                final int[] newLinesIndices = new int[newSize];
                System.arraycopy(this.linesIndices, 0, newLinesIndices, 0, this.count);
                this.linesIndices = newLinesIndices;
            }
            System.arraycopy(this.linesIndices, modifyEnd, this.linesIndices, modifyStart + lineCount, this.count - modifyEnd);
            for (int j = 0; j < lineCount; ++j) {
                this.linesIndices[modifyStart + j] = startLine + j;
            }
            this.count = newSize;
        }
    }
    
    int indexOf(final int lineIndex) {
        for (int i = 0; i < this.count; ++i) {
            if (this.linesIndices[i] == lineIndex) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public int hashCode() {
        return this.style.hashCode() ^ this.type;
    }
    
    int[] removeIndices(final int startLine, final int replaceLineCount, final int newLineCount, final boolean update) {
        if (this.count == 0) {
            return null;
        }
        if (startLine > this.linesIndices[this.count - 1]) {
            return null;
        }
        final int endLine = startLine + replaceLineCount;
        final int delta = newLineCount - replaceLineCount;
        for (int i = 0; i < this.count; ++i) {
            final int index = this.linesIndices[i];
            if (startLine <= index) {
                int j;
                for (j = i; j < this.count && this.linesIndices[j] < endLine; ++j) {}
                if (update) {
                    for (int k = j; k < this.count; ++k) {
                        final int[] linesIndices = this.linesIndices;
                        final int n = k;
                        final int[] array = linesIndices;
                        final int n3 = n;
                        array[n3] += delta;
                    }
                }
                final int[] redrawLines = new int[this.count - j];
                System.arraycopy(this.linesIndices, j, redrawLines, 0, this.count - j);
                System.arraycopy(this.linesIndices, j, this.linesIndices, i, this.count - j);
                this.count -= j - i;
                return redrawLines;
            }
        }
        for (int i = 0; i < this.count; ++i) {
            final int[] linesIndices2 = this.linesIndices;
            final int n2 = i;
            final int[] array2 = linesIndices2;
            final int n4 = n2;
            array2[n4] += delta;
        }
        return null;
    }
    
    int size() {
        return this.count;
    }
}
