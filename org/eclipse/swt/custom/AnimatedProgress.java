//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;

@Deprecated
public class AnimatedProgress extends Canvas
{
    static final int SLEEP = 70;
    static final int DEFAULT_WIDTH = 160;
    static final int DEFAULT_HEIGHT = 18;
    boolean active;
    boolean showStripes;
    int value;
    int orientation;
    boolean showBorder;
    
    public AnimatedProgress(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.active = false;
        this.showStripes = false;
        this.orientation = 256;
        this.showBorder = false;
        if ((style & 0x200) != 0x0) {
            this.orientation = 512;
        }
        this.showBorder = ((style & 0x800) != 0x0);
        this.addControlListener(ControlListener.controlResizedAdapter(e -> this.redraw()));
        this.addPaintListener(this::paint);
        this.addDisposeListener(e -> this.stop());
    }
    
    private static int checkStyle(final int style) {
        final int mask = 0;
        return style & 0x0;
    }
    
    public synchronized void clear() {
        this.checkWidget();
        if (this.active) {
            this.stop();
        }
        this.showStripes = false;
        this.redraw();
    }
    
    @Override
    public Point computeSize(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        Point size = null;
        if (this.orientation == 256) {
            size = new Point(160, 18);
        }
        else {
            size = new Point(18, 160);
        }
        if (wHint != -1) {
            size.x = wHint;
        }
        if (hHint != -1) {
            size.y = hHint;
        }
        return size;
    }
    
    private void drawBevelRect(final GC gc, final int x, final int y, final int w, final int h, final Color topleft, final Color bottomright) {
        gc.setForeground(topleft);
        gc.drawLine(x, y, x + w - 1, y);
        gc.drawLine(x, y, x, y + h - 1);
        gc.setForeground(bottomright);
        gc.drawLine(x + w, y, x + w, y + h);
        gc.drawLine(x, y + h, x + w, y + h);
    }
    
    void paint(final PaintEvent event) {
        final GC gc = event.gc;
        final Display disp = this.getDisplay();
        final Rectangle rect = this.getClientArea();
        gc.fillRectangle(rect);
        if (this.showBorder) {
            this.drawBevelRect(gc, rect.x, rect.y, rect.width - 1, rect.height - 1, disp.getSystemColor(18), disp.getSystemColor(20));
        }
        this.paintStripes(gc);
    }
    
    void paintStripes(final GC gc) {
        if (!this.showStripes) {
            return;
        }
        Rectangle rect = this.getClientArea();
        rect = new Rectangle(rect.x + 2, rect.y + 2, rect.width - 4, rect.height - 4);
        gc.setLineWidth(2);
        gc.setClipping(rect);
        final Color color = this.getDisplay().getSystemColor(26);
        gc.setBackground(color);
        gc.fillRectangle(rect);
        gc.setForeground(this.getBackground());
        final int step = 12;
        final int foregroundValue = (this.value == 0) ? 10 : (this.value - 2);
        if (this.orientation == 256) {
            final int y = rect.y - 1;
            final int w = rect.width;
            final int h = rect.height + 2;
            for (int i = 0; i < w; i += 12) {
                final int x = i + foregroundValue;
                gc.drawLine(x, y, x, h);
            }
        }
        else {
            final int x2 = rect.x - 1;
            final int w = rect.width + 2;
            for (int h = rect.height, i = 0; i < h; i += 12) {
                final int y2 = i + foregroundValue;
                gc.drawLine(x2, y2, w, y2);
            }
        }
        if (this.active) {
            this.value = (this.value + 2) % 12;
        }
    }
    
    public synchronized void start() {
        this.checkWidget();
        if (this.active) {
            return;
        }
        this.active = true;
        this.showStripes = true;
        final Display display = this.getDisplay();
        final Runnable[] timer = { null };
        GC gc;
        final Display display2;
        final Object o;
        display.timerExec(70, timer[0] = (() -> {
            if (this.active) {
                gc = new GC(this);
                this.paintStripes(gc);
                gc.dispose();
                display2.timerExec(70, o[0]);
            }
        }));
    }
    
    public synchronized void stop() {
        this.active = false;
    }
}
