//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;

public class ViewForm extends Composite
{
    public int marginWidth;
    public int marginHeight;
    public int horizontalSpacing;
    public int verticalSpacing;
    @Deprecated
    public static RGB borderInsideRGB;
    @Deprecated
    public static RGB borderMiddleRGB;
    @Deprecated
    public static RGB borderOutsideRGB;
    Control topLeft;
    Control topCenter;
    Control topRight;
    Control content;
    boolean separateTopCenter;
    boolean showBorder;
    int separator;
    int borderTop;
    int borderBottom;
    int borderLeft;
    int borderRight;
    int highlight;
    Point oldSize;
    Color selectionBackground;
    Listener listener;
    static final int OFFSCREEN = -200;
    static final int BORDER1_COLOR = 18;
    static final int SELECTION_BACKGROUND = 25;
    
    public ViewForm(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.horizontalSpacing = 1;
        this.verticalSpacing = 1;
        this.separateTopCenter = false;
        this.showBorder = false;
        this.separator = -1;
        this.borderTop = 0;
        this.borderBottom = 0;
        this.borderLeft = 0;
        this.borderRight = 0;
        this.highlight = 0;
        super.setLayout(new ViewFormLayout());
        this.setBorderVisible((style & 0x800) != 0x0);
        this.listener = (e -> {
            switch (e.type) {
                case 12: {
                    this.onDispose(e);
                    break;
                }
                case 9: {
                    this.onPaint(e.gc);
                    break;
                }
                case 11: {
                    this.onResize();
                    break;
                }
            }
            return;
        });
        final int[] events;
        final int[] array2;
        final int[] array = array2 = (events = new int[] { 12, 9, 11 });
        for (final int event : array2) {
            this.addListener(event, this.listener);
        }
    }
    
    static int checkStyle(final int style) {
        final int mask = 109051904;
        return (style & 0x6800000) | 0x100000;
    }
    
    @Override
    public Rectangle computeTrim(final int x, final int y, final int width, final int height) {
        this.checkWidget();
        final int trimX = x - this.borderLeft - this.highlight;
        final int trimY = y - this.borderTop - this.highlight;
        final int trimWidth = width + this.borderLeft + this.borderRight + 2 * this.highlight;
        final int trimHeight = height + this.borderTop + this.borderBottom + 2 * this.highlight;
        return new Rectangle(trimX, trimY, trimWidth, trimHeight);
    }
    
    @Override
    public Rectangle getClientArea() {
        this.checkWidget();
        final Rectangle clientArea3;
        final Rectangle rectangle4;
        final Rectangle clientArea2 = rectangle4 = (clientArea3 = super.getClientArea());
        rectangle4.x += this.borderLeft;
        final Rectangle rectangle5;
        final Rectangle rectangle = rectangle5 = clientArea3;
        rectangle5.y += this.borderTop;
        final Rectangle rectangle6;
        final Rectangle rectangle2 = rectangle6 = clientArea3;
        rectangle6.width -= this.borderLeft + this.borderRight;
        final Rectangle rectangle7;
        final Rectangle rectangle3 = rectangle7 = clientArea3;
        rectangle7.height -= this.borderTop + this.borderBottom;
        return clientArea3;
    }
    
    public Control getContent() {
        return this.content;
    }
    
    public Control getTopCenter() {
        return this.topCenter;
    }
    
    public Control getTopLeft() {
        return this.topLeft;
    }
    
    public Control getTopRight() {
        return this.topRight;
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.topLeft = null;
        this.topCenter = null;
        this.topRight = null;
        this.content = null;
        this.oldSize = null;
        this.selectionBackground = null;
    }
    
    void onPaint(final GC gc) {
        final Color gcForeground = gc.getForeground();
        final Point size = this.getSize();
        final Color border = this.getDisplay().getSystemColor(18);
        if (this.showBorder) {
            gc.setForeground(border);
            gc.drawRectangle(0, 0, size.x - 1, size.y - 1);
            if (this.highlight > 0) {
                final int x1 = 1;
                final int y1 = 1;
                final int x2 = size.x - 1;
                final int y2 = size.y - 1;
                final int[] shape = { 1, 1, x2, 1, x2, y2, 1, y2, 1, 1 + this.highlight, 1 + this.highlight, 1 + this.highlight, 1 + this.highlight, y2 - this.highlight, x2 - this.highlight, y2 - this.highlight, x2 - this.highlight, 1 + this.highlight, 1, 1 + this.highlight };
                final Color highlightColor = this.getDisplay().getSystemColor(26);
                gc.setBackground(highlightColor);
                gc.fillPolygon(shape);
            }
        }
        if (this.separator > -1) {
            gc.setForeground(border);
            gc.drawLine(this.borderLeft + this.highlight, this.separator, size.x - this.borderLeft - this.borderRight - this.highlight, this.separator);
        }
        gc.setForeground(gcForeground);
    }
    
    void onResize() {
        final Point size = this.getSize();
        if (this.oldSize == null || this.oldSize.x == 0 || this.oldSize.y == 0) {
            this.redraw();
        }
        else {
            int width = 0;
            if (this.oldSize.x < size.x) {
                width = size.x - this.oldSize.x + this.borderRight + this.highlight;
            }
            else if (this.oldSize.x > size.x) {
                width = this.borderRight + this.highlight;
            }
            this.redraw(size.x - width, 0, width, size.y, false);
            int height = 0;
            if (this.oldSize.y < size.y) {
                height = size.y - this.oldSize.y + this.borderBottom + this.highlight;
            }
            if (this.oldSize.y > size.y) {
                height = this.borderBottom + this.highlight;
            }
            this.redraw(0, size.y - height, size.x, height, false);
        }
        this.oldSize = size;
    }
    
    public void setContent(final Control content) {
        this.checkWidget();
        if (content != null && content.getParent() != this) {
            SWT.error(5);
        }
        if (this.content != null && !this.content.isDisposed()) {
            this.content.setBounds(-200, -200, 0, 0);
        }
        this.content = content;
        this.layout(false);
    }
    
    @Override
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    void setSelectionBackground(Color color) {
        this.checkWidget();
        if (this.selectionBackground == color) {
            return;
        }
        if (color == null) {
            color = this.getDisplay().getSystemColor(25);
        }
        this.selectionBackground = color;
        this.redraw();
    }
    
    public void setTopCenter(final Control topCenter) {
        this.checkWidget();
        if (topCenter != null && topCenter.getParent() != this) {
            SWT.error(5);
        }
        if (this.topCenter != null && !this.topCenter.isDisposed()) {
            final Point size = this.topCenter.getSize();
            this.topCenter.setLocation(-200 - size.x, -200 - size.y);
        }
        this.topCenter = topCenter;
        this.layout(false);
    }
    
    public void setTopLeft(final Control c) {
        this.checkWidget();
        if (c != null && c.getParent() != this) {
            SWT.error(5);
        }
        if (this.topLeft != null && !this.topLeft.isDisposed()) {
            final Point size = this.topLeft.getSize();
            this.topLeft.setLocation(-200 - size.x, -200 - size.y);
        }
        this.topLeft = c;
        this.layout(false);
    }
    
    public void setTopRight(final Control c) {
        this.checkWidget();
        if (c != null && c.getParent() != this) {
            SWT.error(5);
        }
        if (this.topRight != null && !this.topRight.isDisposed()) {
            final Point size = this.topRight.getSize();
            this.topRight.setLocation(-200 - size.x, -200 - size.y);
        }
        this.topRight = c;
        this.layout(false);
    }
    
    public void setBorderVisible(final boolean show) {
        this.checkWidget();
        if (this.showBorder == show) {
            return;
        }
        this.showBorder = show;
        if (this.showBorder) {
            final int n = 1;
            this.borderBottom = 1;
            this.borderRight = 1;
            this.borderTop = 1;
            this.borderLeft = 1;
            if ((this.getStyle() & 0x800000) == 0x0) {
                this.highlight = 2;
            }
        }
        else {
            final int n2 = 0;
            this.borderRight = 0;
            this.borderLeft = 0;
            this.borderTop = 0;
            this.borderBottom = 0;
            this.highlight = 0;
        }
        this.layout(false);
        this.redraw();
    }
    
    public void setTopCenterSeparate(final boolean show) {
        this.checkWidget();
        this.separateTopCenter = show;
        this.layout(false);
    }
    
    static {
        ViewForm.borderInsideRGB = new RGB(132, 130, 132);
        ViewForm.borderMiddleRGB = new RGB(143, 141, 138);
        ViewForm.borderOutsideRGB = new RGB(171, 168, 165);
    }
}
