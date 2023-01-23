//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.graphics.*;

class llIllI extends AccessibleAttributeAdapter
{
    final /* synthetic */ StyledText this$0;
    
    llIllI(final StyledText this$0) {
        this.this$0 = this$0;
    }
    
    public void getAttributes(final AccessibleAttributeEvent e) {
        final StyledText st = this.this$0;
        e.leftMargin = st.getLeftMargin();
        e.topMargin = st.getTopMargin();
        e.rightMargin = st.getRightMargin();
        e.bottomMargin = st.getBottomMargin();
        e.tabStops = st.getTabStops();
        e.justify = st.getJustify();
        e.alignment = st.getAlignment();
        e.indent = st.getIndent();
    }
    
    public void getTextAttributes(final AccessibleTextAttributeEvent e) {
        final StyledText st = this.this$0;
        final int contentLength = st.getCharCount();
        if (!this.this$0.isListening(3002) && st.renderer.styleCount == 0) {
            e.start = 0;
            e.end = contentLength;
            e.textStyle = new TextStyle(st.getFont(), st.foreground, st.background);
            return;
        }
        int offset = Math.max(0, Math.min(e.offset, contentLength - 1));
        final int lineIndex = st.getLineAtOffset(offset);
        final int lineOffset = st.getOffsetAtLine(lineIndex);
        final int lineCount = st.getLineCount();
        offset -= lineOffset;
        final TextLayout layout = st.renderer.getTextLayout(lineIndex);
        final int lineLength = layout.getText().length();
        if (lineLength > 0) {
            e.textStyle = layout.getStyle(Math.max(0, Math.min(offset, lineLength - 1)));
        }
        if (e.textStyle == null) {
            e.textStyle = new TextStyle(st.getFont(), st.foreground, st.background);
        }
        else if (e.textStyle.foreground == null || e.textStyle.background == null || e.textStyle.font == null) {
            final TextStyle textStyle = new TextStyle(e.textStyle);
            if (textStyle.foreground == null) {
                textStyle.foreground = st.foreground;
            }
            if (textStyle.background == null) {
                textStyle.background = st.background;
            }
            if (textStyle.font == null) {
                textStyle.font = st.getFont();
            }
            e.textStyle = textStyle;
        }
        if (offset >= lineLength) {
            e.start = lineOffset + lineLength;
            if (lineIndex + 1 < lineCount) {
                e.end = st.getOffsetAtLine(lineIndex + 1);
            }
            else {
                e.end = contentLength;
            }
            return;
        }
        final int[] ranges = layout.getRanges();
        st.renderer.disposeTextLayout(layout);
        int index = 0;
        int end = 0;
        while (index < ranges.length) {
            final int styleStart = ranges[index++];
            final int styleEnd = ranges[index++];
            if (styleStart <= offset && offset <= styleEnd) {
                e.start = lineOffset + styleStart;
                e.end = lineOffset + styleEnd + 1;
                return;
            }
            if (styleStart > offset) {
                e.start = lineOffset + end;
                e.end = lineOffset + styleStart;
                return;
            }
            end = styleEnd + 1;
        }
        if (index == ranges.length) {
            e.start = lineOffset + end;
            if (lineIndex + 1 < lineCount) {
                e.end = st.getOffsetAtLine(lineIndex + 1);
            }
            else {
                e.end = contentLength;
            }
        }
    }
}
