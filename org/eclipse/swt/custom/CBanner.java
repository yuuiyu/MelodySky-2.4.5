//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;

public class CBanner extends Composite
{
    Control left;
    Control right;
    Control bottom;
    boolean simple;
    int[] curve;
    int curveStart;
    Rectangle curveRect;
    int curve_width;
    int curve_indent;
    int rightWidth;
    int rightMinWidth;
    int rightMinHeight;
    Cursor resizeCursor;
    boolean dragging;
    int rightDragDisplacement;
    Listener listener;
    static final int OFFSCREEN = -200;
    static final int BORDER_BOTTOM = 2;
    static final int BORDER_TOP = 3;
    static final int BORDER_STRIPE = 1;
    static final int CURVE_TAIL = 200;
    static final int BEZIER_RIGHT = 30;
    static final int BEZIER_LEFT = 30;
    static final int MIN_LEFT = 10;
    static int BORDER1;
    
    public CBanner(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.simple = true;
        this.curve = new int[0];
        this.curveStart = 0;
        this.curveRect = new Rectangle(0, 0, 0, 0);
        this.curve_width = 5;
        this.curve_indent = -2;
        this.rightWidth = -1;
        this.rightMinWidth = 0;
        this.rightMinHeight = 0;
        this.dragging = false;
        this.rightDragDisplacement = 0;
        super.setLayout(new CBannerLayout());
        this.resizeCursor = this.getDisplay().getSystemCursor(9);
        this.listener = (e -> {
            switch (e.type) {
                case 12: {
                    this.onDispose(e);
                    break;
                }
                case 3: {
                    this.onMouseDown(e.x, e.y);
                    break;
                }
                case 7: {
                    this.onMouseExit();
                    break;
                }
                case 5: {
                    this.onMouseMove(e.x, e.y);
                    break;
                }
                case 4: {
                    this.onMouseUp();
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
        final int[] array = array2 = (events = new int[] { 12, 3, 7, 5, 4, 9, 11 });
        for (final int event : array2) {
            this.addListener(event, this.listener);
        }
    }
    
    static int[] bezier(final int x0, final int y0, final int x1, final int y1, final int x2, final int y2, final int x3, final int y3, final int count) {
        final double a0 = x0;
        final double a2 = 3 * (x1 - x0);
        final double a3 = 3 * (x0 + x2 - 2 * x1);
        final double a4 = x3 - x0 + 3 * x1 - 3 * x2;
        final double b0 = y0;
        final double b2 = 3 * (y1 - y0);
        final double b3 = 3 * (y0 + y2 - 2 * y1);
        final double b4 = y3 - y0 + 3 * y1 - 3 * y2;
        final int[] polygon = new int[2 * count + 2];
        for (int i = 0; i <= count; ++i) {
            final double t = i / (double)count;
            polygon[2 * i] = (int)(a0 + a2 * t + a3 * t * t + a4 * t * t * t);
            polygon[2 * i + 1] = (int)(b0 + b2 * t + b3 * t * t + b4 * t * t * t);
        }
        return polygon;
    }
    
    static int checkStyle(final int style) {
        return 0;
    }
    
    public Control getBottom() {
        this.checkWidget();
        return this.bottom;
    }
    
    @Override
    public Rectangle getClientArea() {
        return new Rectangle(0, 0, 0, 0);
    }
    
    public Control getLeft() {
        this.checkWidget();
        return this.left;
    }
    
    public Control getRight() {
        this.checkWidget();
        return this.right;
    }
    
    public Point getRightMinimumSize() {
        this.checkWidget();
        return new Point(this.rightMinWidth, this.rightMinHeight);
    }
    
    public int getRightWidth() {
        this.checkWidget();
        if (this.right == null) {
            return 0;
        }
        if (this.rightWidth == -1) {
            final Point size = this.right.computeSize(-1, -1, false);
            return size.x;
        }
        return this.rightWidth;
    }
    
    public boolean getSimple() {
        this.checkWidget();
        return this.simple;
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.resizeCursor = null;
        this.left = null;
        this.right = null;
        this.bottom = null;
    }
    
    void onMouseDown(final int x, final int y) {
        if (this.curveRect.contains(x, y)) {
            this.dragging = true;
            this.rightDragDisplacement = this.curveStart - x + this.curve_width - this.curve_indent;
        }
    }
    
    void onMouseExit() {
        if (!this.dragging) {
            this.setCursor(null);
        }
    }
    
    void onMouseMove(final int x, final int y) {
        if (!this.dragging) {
            if (this.curveRect.contains(x, y)) {
                this.setCursor(this.resizeCursor);
            }
            else {
                this.setCursor(null);
            }
            return;
        }
        final Point size = this.getSize();
        if (0 >= x || x >= size.x) {
            return;
        }
        this.rightWidth = Math.max(0, size.x - x - this.rightDragDisplacement);
        if (this.rightMinWidth == -1) {
            final Point minSize = this.right.computeSize(this.rightMinWidth, this.rightMinHeight);
            this.rightWidth = Math.max(minSize.x, this.rightWidth);
        }
        else {
            this.rightWidth = Math.max(this.rightMinWidth, this.rightWidth);
        }
        this.layout(false);
    }
    
    void onMouseUp() {
        this.dragging = false;
    }
    
    void onPaint(final GC gc) {
        if (this.left == null && this.right == null) {
            return;
        }
        final Point size = this.getSize();
        final Color border1 = this.getDisplay().getSystemColor(CBanner.BORDER1);
        if (this.bottom != null) {
            final int y = this.bottom.getBounds().y - 1 - 1;
            gc.setForeground(border1);
            gc.drawLine(0, y, size.x, y);
        }
        if (this.left == null || this.right == null) {
            return;
        }
        final int[] line1 = new int[this.curve.length + 6];
        int index = 0;
        final int x = this.curveStart;
        line1[index++] = x + 1;
        line1[index++] = size.y - 1;
        for (int i = 0; i < this.curve.length / 2; ++i) {
            line1[index++] = x + this.curve[2 * i];
            line1[index++] = this.curve[2 * i + 1];
        }
        line1[index++] = x + this.curve_width;
        line1[index++] = 0;
        line1[index++] = size.x;
        line1[index++] = 0;
        final Color background = this.getBackground();
        if (this.getDisplay().getDepth() >= 15) {
            final int[] line2 = new int[line1.length];
            index = 0;
            for (int j = 0; j < line1.length / 2; ++j) {
                line2[index] = line1[index++] - 1;
                line2[index] = line1[index++];
            }
            final int[] line3 = new int[line1.length];
            index = 0;
            for (int k = 0; k < line1.length / 2; ++k) {
                line3[index] = line1[index++] + 1;
                line3[index] = line1[index++];
            }
            final RGB from = border1.getRGB();
            final RGB to = background.getRGB();
            final int red = from.red + 3 * (to.red - from.red) / 4;
            final int green = from.green + 3 * (to.green - from.green) / 4;
            final int blue = from.blue + 3 * (to.blue - from.blue) / 4;
            final Color color = new Color(red, green, blue);
            gc.setForeground(color);
            gc.drawPolyline(line2);
            gc.drawPolyline(line3);
            final int x2 = Math.max(0, this.curveStart - 200);
            gc.setForeground(background);
            gc.setBackground(border1);
            gc.fillGradientRectangle(x2, size.y - 1, this.curveStart - x2 + 1, 1, false);
        }
        else {
            final int x3 = Math.max(0, this.curveStart - 200);
            gc.setForeground(border1);
            gc.drawLine(x3, size.y - 1, this.curveStart + 1, size.y - 1);
        }
        gc.setForeground(border1);
        gc.drawPolyline(line1);
    }
    
    void onResize() {
        this.updateCurve(this.getSize().y);
    }
    
    public void setBottom(final Control control) {
        this.checkWidget();
        if (control != null && control.getParent() != this) {
            SWT.error(5);
        }
        if (this.bottom != null && !this.bottom.isDisposed()) {
            final Point size = this.bottom.getSize();
            this.bottom.setLocation(-200 - size.x, -200 - size.y);
        }
        this.bottom = control;
        this.layout(false);
    }
    
    @Override
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setLeft(final Control control) {
        this.checkWidget();
        if (control != null && control.getParent() != this) {
            SWT.error(5);
        }
        if (this.left != null && !this.left.isDisposed()) {
            final Point size = this.left.getSize();
            this.left.setLocation(-200 - size.x, -200 - size.y);
        }
        this.left = control;
        this.layout(false);
    }
    
    public void setRight(final Control control) {
        this.checkWidget();
        if (control != null && control.getParent() != this) {
            SWT.error(5);
        }
        if (this.right != null && !this.right.isDisposed()) {
            final Point size = this.right.getSize();
            this.right.setLocation(-200 - size.x, -200 - size.y);
        }
        this.right = control;
        this.layout(false);
    }
    
    public void setRightMinimumSize(final Point size) {
        this.checkWidget();
        if (size == null || size.x < -1 || size.y < -1) {
            SWT.error(5);
        }
        this.rightMinWidth = size.x;
        this.rightMinHeight = size.y;
        this.layout(false);
    }
    
    public void setRightWidth(final int width) {
        this.checkWidget();
        if (width < -1) {
            SWT.error(5);
        }
        this.rightWidth = width;
        this.layout(false);
    }
    
    public void setSimple(final boolean simple) {
        this.checkWidget();
        if (this.simple != simple) {
            this.simple = simple;
            if (simple) {
                this.curve_width = 5;
                this.curve_indent = -2;
            }
            else {
                this.curve_width = 50;
                this.curve_indent = 5;
            }
            this.updateCurve(this.getSize().y);
            this.layout(false);
            this.redraw();
        }
    }
    
    void updateCurve(final int height) {
        final int h = height - 1;
        if (this.simple) {
            this.curve = new int[] { 0, h, 1, h, 2, h - 1, 3, h - 2, 3, 2, 4, 1, 5, 0 };
        }
        else {
            this.curve = bezier(0, h + 1, 30, h + 1, this.curve_width - 30, 0, this.curve_width, 0, this.curve_width);
        }
    }
    
    static {
        CBanner.BORDER1 = 20;
    }
}
