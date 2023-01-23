//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import java.util.*;

class DefaultContent implements StyledTextContent
{
    private static final String LineDelimiter;
    List<StyledTextListener> textListeners;
    char[] textStore;
    int gapStart;
    int gapEnd;
    int gapLine;
    int highWatermark;
    int lowWatermark;
    int[][] lines;
    int lineCount;
    int expandExp;
    int replaceExpandExp;
    
    DefaultContent() {
        this.textListeners = new ArrayList<StyledTextListener>();
        this.textStore = new char[0];
        this.gapStart = -1;
        this.gapEnd = -1;
        this.gapLine = -1;
        this.highWatermark = 300;
        this.lowWatermark = 50;
        this.lines = new int[50][2];
        this.lineCount = 0;
        this.expandExp = 1;
        this.replaceExpandExp = 1;
        this.setText("");
    }
    
    void addLineIndex(final int start, final int length) {
        final int size = this.lines.length;
        if (this.lineCount == size) {
            final int[][] newLines = new int[size + Compatibility.pow2(this.expandExp)][2];
            System.arraycopy(this.lines, 0, newLines, 0, size);
            this.lines = newLines;
            ++this.expandExp;
        }
        final int[] range = { start, length };
        this.lines[this.lineCount] = range;
        ++this.lineCount;
    }
    
    int[][] addLineIndex(final int start, final int length, final int[][] linesArray, final int count) {
        final int size = linesArray.length;
        int[][] newLines = linesArray;
        if (count == size) {
            newLines = new int[size + Compatibility.pow2(this.replaceExpandExp)][2];
            ++this.replaceExpandExp;
            System.arraycopy(linesArray, 0, newLines, 0, size);
        }
        final int[] range = { start, length };
        newLines[count] = range;
        return newLines;
    }
    
    @Override
    public void addTextChangeListener(final TextChangeListener listener) {
        if (listener == null) {
            this.error(4);
        }
        final StyledTextListener typedListener = new StyledTextListener(listener);
        this.textListeners.add(typedListener);
    }
    
    void adjustGap(final int position, final int sizeHint, final int line) {
        if (position == this.gapStart) {
            final int size = this.gapEnd - this.gapStart - sizeHint;
            if (this.lowWatermark <= size && size <= this.highWatermark) {
                return;
            }
        }
        else if (position + sizeHint == this.gapStart && sizeHint < 0) {
            final int size = this.gapEnd - this.gapStart - sizeHint;
            if (this.lowWatermark <= size && size <= this.highWatermark) {
                return;
            }
        }
        this.moveAndResizeGap(position, sizeHint, line);
    }
    
    void indexLines() {
        int start = 0;
        this.lineCount = 0;
        int textLength;
        int i;
        for (textLength = this.textStore.length, i = start; i < textLength; ++i) {
            char ch = this.textStore[i];
            if (ch == '\r') {
                if (i + 1 < textLength) {
                    ch = this.textStore[i + 1];
                    if (ch == '\n') {
                        ++i;
                    }
                }
                this.addLineIndex(start, i - start + 1);
                start = i + 1;
            }
            else if (ch == '\n') {
                this.addLineIndex(start, i - start + 1);
                start = i + 1;
            }
        }
        this.addLineIndex(start, i - start);
    }
    
    boolean isDelimiter(final char ch) {
        return ch == '\r' || ch == '\n';
    }
    
    private boolean isInsideCRLF(final int pos) {
        if (pos == 0) {
            return false;
        }
        if (pos == this.getCharCount()) {
            return false;
        }
        final char charBefore = this.getTextRange(pos - 1, 1).charAt(0);
        if (charBefore != '\r') {
            return false;
        }
        final char charAfter = this.getTextRange(pos, 1).charAt(0);
        return charAfter == '\n' && this.getLineAtOffset(pos - 1) == this.getLineAtOffset(pos);
    }
    
    private void validateReplace(final int start, final int replaceLength) {
        if (replaceLength == 0) {
            if (this.isInsideCRLF(start)) {
                final String message = " [0: start=" + start + " len=" + replaceLength;
                SWT.error(5, null, message);
            }
        }
        else {
            if (this.isInsideCRLF(start)) {
                final String message = " [1: start=" + start + " len=" + replaceLength;
                SWT.error(5, null, message);
            }
            if (this.isInsideCRLF(start + replaceLength)) {
                final String message = " [2: start=" + start + " len=" + replaceLength;
                SWT.error(5, null, message);
            }
        }
    }
    
    int[][] indexLines(final int offset, final int length, final int numLines) {
        int[][] indexedLines = new int[numLines][2];
        int start = 0;
        int lineCount = 0;
        this.replaceExpandExp = 1;
        int i;
        for (i = start; i < length; ++i) {
            final int location = i + offset;
            if (location < this.gapStart || location >= this.gapEnd) {
                char ch = this.textStore[location];
                if (ch == '\r') {
                    if (location + 1 < this.textStore.length) {
                        ch = this.textStore[location + 1];
                        if (ch == '\n') {
                            ++i;
                        }
                    }
                    indexedLines = this.addLineIndex(start, i - start + 1, indexedLines, lineCount);
                    ++lineCount;
                    start = i + 1;
                }
                else if (ch == '\n') {
                    indexedLines = this.addLineIndex(start, i - start + 1, indexedLines, lineCount);
                    ++lineCount;
                    start = i + 1;
                }
            }
        }
        final int[][] newLines = new int[lineCount + 1][2];
        System.arraycopy(indexedLines, 0, newLines, 0, lineCount);
        final int[] range = { start, i - start };
        newLines[lineCount] = range;
        return newLines;
    }
    
    void insert(final int position, final String text) {
        if (text.length() == 0) {
            return;
        }
        final int startLine = this.getLineAtOffset(position);
        final int change = text.length();
        final boolean endInsert = position == this.getCharCount();
        this.adjustGap(position, change, startLine);
        final int startLineOffset = this.getOffsetAtLine(startLine);
        final int startLineLength = this.getPhysicalLine(startLine).length();
        if (change > 0) {
            this.gapStart += change;
            for (int i = 0; i < text.length(); ++i) {
                this.textStore[position + i] = text.charAt(i);
            }
        }
        final int[][] newLines = this.indexLines(startLineOffset, startLineLength, 10);
        int numNewLines = newLines.length - 1;
        if (newLines[numNewLines][1] == 0) {
            if (endInsert) {
                ++numNewLines;
            }
            else {
                --numNewLines;
            }
        }
        this.expandLinesBy(numNewLines);
        for (int j = this.lineCount - 1; j > startLine; --j) {
            this.lines[j + numNewLines] = this.lines[j];
        }
        for (int j = 0; j < numNewLines; ++j) {
            final int[] array = newLines[j];
            final int n = 0;
            final int[] array3 = array;
            final int n3 = 0;
            array3[n3] += startLineOffset;
            this.lines[startLine + j] = newLines[j];
        }
        if (numNewLines < newLines.length) {
            final int[] array2 = newLines[numNewLines];
            final int n2 = 0;
            final int[] array4 = array2;
            final int n4 = 0;
            array4[n4] += startLineOffset;
            this.lines[startLine + numNewLines] = newLines[numNewLines];
        }
        this.lineCount += numNewLines;
        this.gapLine = this.getLineAtPhysicalOffset(this.gapStart);
    }
    
    void moveAndResizeGap(final int position, final int size, final int newGapLine) {
        char[] content = null;
        final int oldSize = this.gapEnd - this.gapStart;
        int newSize;
        if (size > 0) {
            newSize = this.highWatermark + size;
        }
        else {
            newSize = this.lowWatermark - size;
        }
        if (this.gapExists()) {
            final int[] array = this.lines[this.gapLine];
            final int n = 1;
            array[n] -= oldSize;
            for (int i = this.gapLine + 1; i < this.lineCount; ++i) {
                final int[] array2 = this.lines[i];
                final int n2 = 0;
                array2[n2] -= oldSize;
            }
        }
        if (newSize < 0) {
            if (oldSize > 0) {
                content = new char[this.textStore.length - oldSize];
                System.arraycopy(this.textStore, 0, content, 0, this.gapStart);
                System.arraycopy(this.textStore, this.gapEnd, content, this.gapStart, content.length - this.gapStart);
                this.textStore = content;
            }
            this.gapEnd = position;
            this.gapStart = position;
            return;
        }
        content = new char[this.textStore.length + (newSize - oldSize)];
        final int newGapStart = position;
        final int newGapEnd = newGapStart + newSize;
        if (oldSize == 0) {
            System.arraycopy(this.textStore, 0, content, 0, newGapStart);
            System.arraycopy(this.textStore, newGapStart, content, newGapEnd, content.length - newGapEnd);
        }
        else if (newGapStart < this.gapStart) {
            final int delta = this.gapStart - newGapStart;
            System.arraycopy(this.textStore, 0, content, 0, newGapStart);
            System.arraycopy(this.textStore, newGapStart, content, newGapEnd, delta);
            System.arraycopy(this.textStore, this.gapEnd, content, newGapEnd + delta, this.textStore.length - this.gapEnd);
        }
        else {
            final int delta = newGapStart - this.gapStart;
            System.arraycopy(this.textStore, 0, content, 0, this.gapStart);
            System.arraycopy(this.textStore, this.gapEnd, content, this.gapStart, delta);
            System.arraycopy(this.textStore, this.gapEnd + delta, content, newGapEnd, content.length - newGapEnd);
        }
        this.textStore = content;
        this.gapStart = newGapStart;
        this.gapEnd = newGapEnd;
        if (this.gapExists()) {
            this.gapLine = newGapLine;
            final int gapLength = this.gapEnd - this.gapStart;
            final int[] array3 = this.lines[this.gapLine];
            final int n3 = 1;
            array3[n3] += gapLength;
            for (int j = this.gapLine + 1; j < this.lineCount; ++j) {
                final int[] array4 = this.lines[j];
                final int n4 = 0;
                array4[n4] += gapLength;
            }
        }
    }
    
    int lineCount(final int startOffset, final int length) {
        if (length == 0) {
            return 0;
        }
        int lineCount = 0;
        int count = 0;
        int i = startOffset;
        if (i >= this.gapStart) {
            i += this.gapEnd - this.gapStart;
        }
        while (count < length) {
            if (i < this.gapStart || i >= this.gapEnd) {
                char ch = this.textStore[i];
                if (ch == '\r') {
                    if (i + 1 < this.textStore.length) {
                        ch = this.textStore[i + 1];
                        if (ch == '\n') {
                            ++i;
                            ++count;
                        }
                    }
                    ++lineCount;
                }
                else if (ch == '\n') {
                    ++lineCount;
                }
                ++count;
            }
            ++i;
        }
        return lineCount;
    }
    
    int lineCount(final String text) {
        int lineCount = 0;
        for (int length = text.length(), i = 0; i < length; ++i) {
            final char ch = text.charAt(i);
            if (ch == '\r') {
                if (i + 1 < length && text.charAt(i + 1) == '\n') {
                    ++i;
                }
                ++lineCount;
            }
            else if (ch == '\n') {
                ++lineCount;
            }
        }
        return lineCount;
    }
    
    @Override
    public int getCharCount() {
        final int length = this.gapEnd - this.gapStart;
        return this.textStore.length - length;
    }
    
    @Override
    public String getLine(final int index) {
        if (index >= this.lineCount || index < 0) {
            this.error(5);
        }
        final int start = this.lines[index][0];
        int length = this.lines[index][1];
        final int end = start + length - 1;
        if (!this.gapExists() || end < this.gapStart || start >= this.gapEnd) {
            while (length - 1 >= 0 && this.isDelimiter(this.textStore[start + length - 1])) {
                --length;
            }
            return new String(this.textStore, start, length);
        }
        final StringBuilder buf = new StringBuilder();
        final int gapLength = this.gapEnd - this.gapStart;
        buf.append(this.textStore, start, this.gapStart - start);
        buf.append(this.textStore, this.gapEnd, length - gapLength - (this.gapStart - start));
        for (length = buf.length(); length - 1 >= 0 && this.isDelimiter(buf.charAt(length - 1)); --length) {}
        return buf.toString().substring(0, length);
    }
    
    @Override
    public String getLineDelimiter() {
        return DefaultContent.LineDelimiter;
    }
    
    String getFullLine(final int index) {
        final int start = this.lines[index][0];
        final int length = this.lines[index][1];
        final int end = start + length - 1;
        if (!this.gapExists() || end < this.gapStart || start >= this.gapEnd) {
            return new String(this.textStore, start, length);
        }
        final StringBuilder buffer = new StringBuilder();
        final int gapLength = this.gapEnd - this.gapStart;
        buffer.append(this.textStore, start, this.gapStart - start);
        buffer.append(this.textStore, this.gapEnd, length - gapLength - (this.gapStart - start));
        return buffer.toString();
    }
    
    String getPhysicalLine(final int index) {
        final int start = this.lines[index][0];
        final int length = this.lines[index][1];
        return this.getPhysicalText(start, length);
    }
    
    @Override
    public int getLineCount() {
        return this.lineCount;
    }
    
    @Override
    public int getLineAtOffset(final int charPosition) {
        if (charPosition > this.getCharCount() || charPosition < 0) {
            this.error(5);
        }
        int position;
        if (charPosition < this.gapStart) {
            position = charPosition;
        }
        else {
            position = charPosition + (this.gapEnd - this.gapStart);
        }
        if (this.lineCount > 0) {
            final int lastLine = this.lineCount - 1;
            if (position == this.lines[lastLine][0] + this.lines[lastLine][1]) {
                return lastLine;
            }
        }
        int high = this.lineCount;
        int low = -1;
        int index = this.lineCount;
        while (high - low > 1) {
            index = (high + low) / 2;
            final int lineStart = this.lines[index][0];
            final int lineEnd = lineStart + this.lines[index][1] - 1;
            if (position <= lineStart) {
                high = index;
            }
            else {
                if (position <= lineEnd) {
                    high = index;
                    break;
                }
                low = index;
            }
        }
        return high;
    }
    
    int getLineAtPhysicalOffset(final int position) {
        int high = this.lineCount;
        int low = -1;
        int index = this.lineCount;
        while (high - low > 1) {
            index = (high + low) / 2;
            final int lineStart = this.lines[index][0];
            final int lineEnd = lineStart + this.lines[index][1] - 1;
            if (position <= lineStart) {
                high = index;
            }
            else {
                if (position <= lineEnd) {
                    high = index;
                    break;
                }
                low = index;
            }
        }
        return high;
    }
    
    @Override
    public int getOffsetAtLine(final int lineIndex) {
        if (lineIndex == 0) {
            return 0;
        }
        if (lineIndex >= this.lineCount || lineIndex < 0) {
            this.error(5);
        }
        final int start = this.lines[lineIndex][0];
        if (start > this.gapEnd) {
            return start - (this.gapEnd - this.gapStart);
        }
        return start;
    }
    
    void expandLinesBy(final int numLines) {
        final int size = this.lines.length;
        if (size - this.lineCount >= numLines) {
            return;
        }
        final int[][] newLines = new int[size + Math.max(10, numLines)][2];
        System.arraycopy(this.lines, 0, newLines, 0, size);
        this.lines = newLines;
    }
    
    void error(final int code) {
        SWT.error(code);
    }
    
    boolean gapExists() {
        return this.gapStart != this.gapEnd;
    }
    
    String getPhysicalText(final int start, final int length) {
        return new String(this.textStore, start, length);
    }
    
    @Override
    public String getTextRange(final int start, final int length) {
        if (this.textStore == null) {
            return "";
        }
        if (length == 0) {
            return "";
        }
        final int end = start + length;
        if (!this.gapExists() || end < this.gapStart) {
            return new String(this.textStore, start, length);
        }
        if (this.gapStart < start) {
            final int gapLength = this.gapEnd - this.gapStart;
            return new String(this.textStore, start + gapLength, length);
        }
        final StringBuilder buf = new StringBuilder();
        buf.append(this.textStore, start, this.gapStart - start);
        buf.append(this.textStore, this.gapEnd, end - this.gapStart);
        return buf.toString();
    }
    
    @Override
    public void removeTextChangeListener(final TextChangeListener listener) {
        if (listener == null) {
            this.error(4);
        }
        for (int i = 0; i < this.textListeners.size(); ++i) {
            final TypedListener typedListener = this.textListeners.get(i);
            if (typedListener.getEventListener() == listener) {
                this.textListeners.remove(i);
                break;
            }
        }
    }
    
    @Override
    public void replaceTextRange(final int start, final int replaceLength, final String newText) {
        this.validateReplace(start, replaceLength);
        StyledTextEvent event = new StyledTextEvent(this);
        event.type = 3003;
        event.start = start;
        event.replaceLineCount = this.lineCount(start, replaceLength);
        event.text = newText;
        event.newLineCount = this.lineCount(newText);
        event.replaceCharCount = replaceLength;
        event.newCharCount = newText.length();
        this.sendTextEvent(event);
        this.delete(start, replaceLength, event.replaceLineCount + 1);
        this.insert(start, newText);
        event = new StyledTextEvent(this);
        event.type = 3006;
        this.sendTextEvent(event);
    }
    
    void sendTextEvent(final StyledTextEvent event) {
        for (final StyledTextListener textListener : this.textListeners) {
            textListener.handleEvent(event);
        }
    }
    
    @Override
    public void setText(final String text) {
        this.textStore = text.toCharArray();
        this.gapStart = -1;
        this.gapEnd = -1;
        this.expandExp = 1;
        this.indexLines();
        final StyledTextEvent event = new StyledTextEvent(this);
        event.type = 3004;
        event.text = "";
        this.sendTextEvent(event);
    }
    
    void delete(final int position, final int length, final int numLines) {
        if (length == 0) {
            return;
        }
        final int startLine = this.getLineAtOffset(position);
        final int startLineOffset = this.getOffsetAtLine(startLine);
        final int endLine = this.getLineAtOffset(position + length);
        String endText = "";
        boolean splittingDelimiter = false;
        if (position + length < this.getCharCount()) {
            endText = this.getTextRange(position + length - 1, 2);
            if (endText.charAt(0) == '\r' && endText.charAt(1) == '\n') {
                splittingDelimiter = true;
            }
        }
        this.adjustGap(position + length, -length, startLine);
        final int[][] oldLines = this.indexLines(position, length + (this.gapEnd - this.gapStart), numLines);
        if (position + length == this.gapStart) {
            this.gapStart -= length;
        }
        else {
            this.gapEnd += length;
        }
        int j = position;
        for (boolean eol = false; j < this.textStore.length && !eol; ++j) {
            if (j < this.gapStart || j >= this.gapEnd) {
                final char ch = this.textStore[j];
                if (this.isDelimiter(ch)) {
                    if (j + 1 < this.textStore.length && ch == '\r' && this.textStore[j + 1] == '\n') {
                        ++j;
                    }
                    eol = true;
                }
            }
        }
        this.lines[startLine][1] = position - startLineOffset + (j - position);
        int numOldLines = oldLines.length - 1;
        if (splittingDelimiter) {
            --numOldLines;
        }
        for (int i = endLine + 1; i < this.lineCount; ++i) {
            this.lines[i - numOldLines] = this.lines[i];
        }
        this.lineCount -= numOldLines;
        this.gapLine = this.getLineAtPhysicalOffset(this.gapStart);
    }
    
    static {
        LineDelimiter = System.lineSeparator();
    }
}
