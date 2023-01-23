//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.dnd.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class StyledTextDropTargetEffect extends DropTargetEffect
{
    static final int CARET_WIDTH = 2;
    static final int SCROLL_HYSTERESIS = 100;
    static final int SCROLL_TOLERANCE = 20;
    int currentOffset;
    long scrollBeginTime;
    int scrollX;
    int scrollY;
    Listener paintListener;
    
    public StyledTextDropTargetEffect(final StyledText styledText) {
        super((Control)styledText);
        this.currentOffset = -1;
        this.scrollX = -1;
        this.scrollY = -1;
        StyledText text;
        Point position;
        int height;
        this.paintListener = (event -> {
            if (this.currentOffset != -1) {
                text = (StyledText)this.getControl();
                position = text.getLocationAtOffset(this.currentOffset);
                height = text.getLineHeight(this.currentOffset);
                event.gc.setBackground(event.display.getSystemColor(2));
                event.gc.fillRectangle(position.x, position.y, 2, height);
            }
        });
    }
    
    @Override
    public void dragEnter(final DropTargetEvent event) {
        this.currentOffset = -1;
        this.scrollBeginTime = 0L;
        this.scrollX = -1;
        this.scrollY = -1;
        this.getControl().removeListener(9, this.paintListener);
        this.getControl().addListener(9, this.paintListener);
    }
    
    @Override
    public void dragLeave(final DropTargetEvent event) {
        final StyledText text = (StyledText)this.getControl();
        if (this.currentOffset != -1) {
            this.refreshCaret(text, this.currentOffset, -1);
        }
        text.removeListener(9, this.paintListener);
        this.scrollBeginTime = 0L;
        this.scrollX = -1;
        this.scrollY = -1;
    }
    
    @Override
    public void dragOver(final DropTargetEvent event) {
        final int effect = event.feedback;
        final StyledText text = (StyledText)this.getControl();
        final Point pt = text.getDisplay().map(null, (Control)text, event.x, event.y);
        if ((effect & 0x8) == 0x0) {
            this.scrollBeginTime = 0L;
            final int n = -1;
            this.scrollY = -1;
            this.scrollX = -1;
        }
        else if (text.getCharCount() == 0) {
            this.scrollBeginTime = 0L;
            final int n2 = -1;
            this.scrollY = -1;
            this.scrollX = -1;
        }
        else if (this.scrollX != -1 && this.scrollY != -1 && this.scrollBeginTime != 0L && ((pt.x >= this.scrollX && pt.x <= this.scrollX + 20) || (pt.y >= this.scrollY && pt.y <= this.scrollY + 20))) {
            if (System.currentTimeMillis() >= this.scrollBeginTime) {
                final Rectangle area = text.getClientArea();
                final GC gc = new GC((Drawable)text);
                final FontMetrics fm = gc.getFontMetrics();
                gc.dispose();
                final double charWidth = fm.getAverageCharacterWidth();
                final int scrollAmount = (int)(10.0 * charWidth);
                if (pt.x < area.x + 3.0 * charWidth) {
                    final int leftPixel = text.getHorizontalPixel();
                    text.setHorizontalPixel(leftPixel - scrollAmount);
                }
                if (pt.x > area.width - 3.0 * charWidth) {
                    final int leftPixel = text.getHorizontalPixel();
                    text.setHorizontalPixel(leftPixel + scrollAmount);
                }
                final int lineHeight = text.getLineHeight();
                if (pt.y < area.y + lineHeight) {
                    final int topPixel = text.getTopPixel();
                    text.setTopPixel(topPixel - lineHeight);
                }
                if (pt.y > area.height - lineHeight) {
                    final int topPixel = text.getTopPixel();
                    text.setTopPixel(topPixel + lineHeight);
                }
                this.scrollBeginTime = 0L;
                final int n3 = -1;
                this.scrollY = -1;
                this.scrollX = -1;
            }
        }
        else {
            this.scrollBeginTime = System.currentTimeMillis() + 100L;
            this.scrollX = pt.x;
            this.scrollY = pt.y;
        }
        if ((effect & 0x1) != 0x0) {
            final int[] trailing = { 0 };
            int newOffset = text.getOffsetAtPoint(pt.x, pt.y, trailing, false);
            newOffset += trailing[0];
            if (newOffset != this.currentOffset) {
                this.refreshCaret(text, this.currentOffset, newOffset);
                this.currentOffset = newOffset;
            }
        }
    }
    
    void refreshCaret(final StyledText text, final int oldOffset, final int newOffset) {
        if (oldOffset != newOffset) {
            if (oldOffset != -1) {
                final Point oldPos = text.getLocationAtOffset(oldOffset);
                final int oldHeight = text.getLineHeight(oldOffset);
                text.redraw(oldPos.x, oldPos.y, 2, oldHeight, false);
            }
            if (newOffset != -1) {
                final Point newPos = text.getLocationAtOffset(newOffset);
                final int newHeight = text.getLineHeight(newOffset);
                text.redraw(newPos.x, newPos.y, 2, newHeight, false);
            }
        }
    }
    
    @Override
    public void dropAccept(final DropTargetEvent event) {
        if (this.currentOffset != -1) {
            final StyledText text = (StyledText)this.getControl();
            text.setSelection(this.currentOffset);
            this.currentOffset = -1;
        }
    }
}
