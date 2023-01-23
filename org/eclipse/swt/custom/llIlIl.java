//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class llIlIl extends AccessibleTextExtendedAdapter
{
    final /* synthetic */ StyledText this$0;
    
    llIlIl(final StyledText this$0) {
        this.this$0 = this$0;
    }
    
    public void getCaretOffset(final AccessibleTextEvent e) {
        e.offset = this.this$0.getCaretOffset();
    }
    
    public void setCaretOffset(final AccessibleTextEvent e) {
        this.this$0.setCaretOffset(e.offset);
        e.result = "OK";
    }
    
    public void getSelectionRange(final AccessibleTextEvent e) {
        final Point selection = this.this$0.getSelectionRange();
        e.offset = selection.x;
        e.length = selection.y;
    }
    
    public void addSelection(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        final Point point = st.getSelection();
        if (point.x == point.y) {
            int end = e.end;
            if (end == -1) {
                end = st.getCharCount();
            }
            st.setSelection(e.start, end);
            e.result = "OK";
        }
    }
    
    public void getSelection(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        if (st.blockSelection && st.blockXLocation != -1) {
            final Rectangle rect = st.getBlockSelectionPosition();
            final int lineIndex = rect.y + e.index;
            final int linePixel = st.getLinePixel(lineIndex);
            e.ranges = this.getRanges(rect.x, linePixel, rect.width, linePixel);
            if (e.ranges.length > 0) {
                e.start = e.ranges[0];
                e.end = e.ranges[e.ranges.length - 1];
            }
        }
        else if (e.index == 0) {
            final Point point = st.getSelection();
            e.start = point.x;
            e.end = point.y;
            if (e.start > e.end) {
                final int temp = e.start;
                e.start = e.end;
                e.end = temp;
            }
        }
    }
    
    public void getSelectionCount(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        if (st.blockSelection && st.blockXLocation != -1) {
            final Rectangle rect = st.getBlockSelectionPosition();
            e.count = rect.height - rect.y + 1;
        }
        else {
            final Point point = st.getSelection();
            e.count = ((point.x != point.y) ? 1 : 0);
        }
    }
    
    public void removeSelection(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        if (e.index == 0) {
            if (st.blockSelection) {
                st.clearBlockSelection(true, false);
            }
            else {
                st.clearSelection(false);
            }
            e.result = "OK";
        }
    }
    
    public void setSelection(final AccessibleTextEvent e) {
        if (e.index != 0) {
            return;
        }
        final StyledText st = this.this$0;
        final Point point = st.getSelection();
        if (point.x == point.y) {
            return;
        }
        int end = e.end;
        if (end == -1) {
            end = st.getCharCount();
        }
        st.setSelection(e.start, end);
        e.result = "OK";
    }
    
    public void getCharacterCount(final AccessibleTextEvent e) {
        e.count = this.this$0.getCharCount();
    }
    
    public void getOffsetAtPoint(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        Point point = new Point(e.x, e.y);
        final Display display = st.getDisplay();
        point = display.map(null, st, point);
        e.offset = st.getOffsetAtPoint(point.x, point.y, null, true);
    }
    
    public void getTextBounds(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        int start = e.start;
        int end = e.end;
        final int contentLength = st.getCharCount();
        start = Math.max(0, Math.min(start, contentLength));
        end = Math.max(0, Math.min(end, contentLength));
        if (start > end) {
            final int temp = start;
            start = end;
            end = temp;
        }
        final int startLine = st.getLineAtOffset(start);
        final int endLine = st.getLineAtOffset(end);
        final Rectangle[] rects = new Rectangle[endLine - startLine + 1];
        Rectangle bounds = null;
        int index = 0;
        final Display display = st.getDisplay();
        for (int lineIndex = startLine; lineIndex <= endLine; ++lineIndex) {
            Rectangle rect = new Rectangle(0, 0, 0, 0);
            rect.y = st.getLinePixel(lineIndex);
            rect.height = st.renderer.getLineHeight(lineIndex);
            if (lineIndex == startLine) {
                rect.x = st.getPointAtOffset(start).x;
            }
            else {
                rect.x = st.leftMargin - st.horizontalScrollOffset;
            }
            if (lineIndex == endLine) {
                rect.width = st.getPointAtOffset(end).x - rect.x;
            }
            else {
                final TextLayout layout = st.renderer.getTextLayout(lineIndex);
                rect.width = layout.getBounds().width - rect.x;
                st.renderer.disposeTextLayout(layout);
            }
            final Rectangle[] array = rects;
            final int n = index++;
            final Rectangle map = display.map(st, null, rect);
            array[n] = map;
            rect = map;
            if (bounds == null) {
                bounds = new Rectangle(rect.x, rect.y, rect.width, rect.height);
            }
            else {
                bounds.add(rect);
            }
        }
        e.rectangles = rects;
        if (bounds != null) {
            e.x = bounds.x;
            e.y = bounds.y;
            e.width = bounds.width;
            e.height = bounds.height;
        }
    }
    
    int[] getRanges(final int left, final int top, final int right, final int bottom) {
        final StyledText st = this.this$0;
        final int lineStart = st.getLineIndex(top);
        final int lineEnd = st.getLineIndex(bottom);
        final int count = lineEnd - lineStart + 1;
        final int[] ranges = new int[count * 2];
        int index = 0;
        for (int lineIndex = lineStart; lineIndex <= lineEnd; ++lineIndex) {
            final String line = st.content.getLine(lineIndex);
            final int lineOffset = st.content.getOffsetAtLine(lineIndex);
            final int lineEndOffset = lineOffset + line.length();
            final int linePixel = st.getLinePixel(lineIndex);
            int start = st.getOffsetAtPoint(left, linePixel, null, true);
            if (start == -1) {
                start = ((left < st.leftMargin) ? lineOffset : lineEndOffset);
            }
            final int[] trailing = { 0 };
            int end = st.getOffsetAtPoint(right, linePixel, trailing, true);
            if (end == -1) {
                end = ((right < st.leftMargin) ? lineOffset : lineEndOffset);
            }
            else {
                end += trailing[0];
            }
            if (start > end) {
                final int temp = start;
                start = end;
                end = temp;
            }
            ranges[index++] = start;
            ranges[index++] = end;
        }
        return ranges;
    }
    
    public void getRanges(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        Point point = new Point(e.x, e.y);
        final Display display = st.getDisplay();
        point = display.map(null, st, point);
        e.ranges = this.getRanges(point.x, point.y, point.x + e.width, point.y + e.height);
        if (e.ranges.length > 0) {
            e.start = e.ranges[0];
            e.end = e.ranges[e.ranges.length - 1];
        }
    }
    
    public void getText(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        int start = e.start;
        int end = e.end;
        final int contentLength = st.getCharCount();
        if (end == -1) {
            end = contentLength;
        }
        start = Math.max(0, Math.min(start, contentLength));
        end = Math.max(0, Math.min(end, contentLength));
        if (start > end) {
            final int temp = start;
            start = end;
            end = temp;
        }
        int count = e.count;
        switch (e.type) {
            case 0: {
                int newCount = 0;
                if (count > 0) {
                    while (count-- > 0) {
                        final int newEnd = st.getWordNext(end, 2);
                        if (newEnd == contentLength) {
                            break;
                        }
                        if (newEnd == end) {
                            break;
                        }
                        end = newEnd;
                        ++newCount;
                    }
                    start = end;
                    end = st.getWordNext(end, 2);
                }
                else {
                    while (count++ < 0) {
                        final int newStart = st.getWordPrevious(start, 2);
                        if (newStart == start) {
                            break;
                        }
                        start = newStart;
                        --newCount;
                    }
                    end = st.getWordNext(start, 2);
                }
                count = newCount;
                break;
            }
            case 1: {
                int newCount = 0;
                if (count > 0) {
                    while (count-- > 0) {
                        final int newEnd = st.getWordNext(end, 16, true);
                        if (newEnd == end) {
                            break;
                        }
                        ++newCount;
                        end = newEnd;
                    }
                    start = end;
                    end = st.getWordNext(start, 8, true);
                }
                else {
                    if (st.getWordPrevious(Math.min(start + 1, contentLength), 16, true) == start) {
                        ++count;
                    }
                    while (count <= 0) {
                        final int newStart = st.getWordPrevious(start, 16, true);
                        if (newStart == start) {
                            break;
                        }
                        ++count;
                        start = newStart;
                        if (count == 0) {
                            continue;
                        }
                        --newCount;
                    }
                    if (count <= 0 && start == 0) {
                        end = start;
                    }
                    else {
                        end = st.getWordNext(start, 8, true);
                    }
                }
                count = newCount;
                break;
            }
            case 2:
            case 3:
            case 4: {
                final int offset = (count > 0) ? end : start;
                int lineIndex = st.getLineAtOffset(offset) + count;
                lineIndex = Math.max(0, Math.min(lineIndex, st.getLineCount() - 1));
                start = st.getOffsetAtLine(lineIndex);
                final String line = st.getLine(lineIndex);
                end = start + line.length();
                count = lineIndex - st.getLineAtOffset(offset);
                break;
            }
        }
        e.start = start;
        e.end = end;
        e.count = count;
        e.result = st.content.getTextRange(start, end - start);
    }
    
    public void getVisibleRanges(final AccessibleTextEvent e) {
        e.ranges = this.getRanges(this.this$0.leftMargin, this.this$0.topMargin, this.this$0.clientAreaWidth - this.this$0.rightMargin, this.this$0.clientAreaHeight - this.this$0.bottomMargin);
        if (e.ranges.length > 0) {
            e.start = e.ranges[0];
            e.end = e.ranges[e.ranges.length - 1];
        }
    }
    
    public void scrollText(final AccessibleTextEvent e) {
        final StyledText st = this.this$0;
        int topPixel = this.this$0.getTopPixel();
        int horizontalPixel = st.getHorizontalPixel();
        switch (e.type) {
            case 0:
            case 2:
            case 4:
            case 6: {
                final Rectangle rect = st.getBoundsAtOffset(e.start);
                if (e.type != 2) {
                    horizontalPixel = horizontalPixel + rect.x - st.leftMargin;
                }
                if (e.type != 4) {
                    topPixel = topPixel + rect.y - st.topMargin;
                    break;
                }
                break;
            }
            case 1:
            case 3:
            case 5: {
                final Rectangle rect = st.getBoundsAtOffset(e.end - 1);
                if (e.type != 3) {
                    horizontalPixel = horizontalPixel - st.clientAreaWidth + rect.x + rect.width + st.rightMargin;
                }
                if (e.type != 5) {
                    topPixel = topPixel - st.clientAreaHeight + rect.y + rect.height + st.bottomMargin;
                    break;
                }
                break;
            }
            case 7: {
                Point point = new Point(e.x, e.y);
                final Display display = st.getDisplay();
                point = display.map(null, st, point);
                final Rectangle rect2 = st.getBoundsAtOffset(e.start);
                topPixel = topPixel - point.y + rect2.y;
                horizontalPixel = horizontalPixel - point.x + rect2.x;
                break;
            }
        }
        st.setTopPixel(topPixel);
        st.setHorizontalPixel(horizontalPixel);
        e.result = "OK";
    }
}
