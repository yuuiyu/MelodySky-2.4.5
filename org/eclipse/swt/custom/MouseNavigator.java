//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class MouseNavigator
{
    private final StyledText parent;
    boolean navigationActivated;
    private GC gc;
    private static final int CIRCLE_RADIUS = 15;
    private static final int CENTRAL_POINT_RADIUS = 2;
    private Point originalMouseLocation;
    private final Listener mouseDownListener;
    private final Listener mouseUpListener;
    private final Listener paintListener;
    private final Listener mouseMoveListener;
    private final Listener focusOutListener;
    private boolean hasHBar;
    private boolean hasVBar;
    private Cursor previousCursor;
    
    MouseNavigator(final StyledText styledText) {
        this.navigationActivated = false;
        if (styledText == null) {
            SWT.error(4);
        }
        if (styledText.isDisposed()) {
            SWT.error(24);
        }
        this.parent = styledText;
        this.mouseDownListener = (event -> this.onMouseDown(event));
        this.parent.addListener(3, this.mouseDownListener);
        this.mouseUpListener = (event -> this.onMouseUp(event));
        this.parent.addListener(4, this.mouseUpListener);
        this.paintListener = (event -> this.onPaint(event));
        this.parent.addListener(9, this.paintListener);
        this.mouseMoveListener = (event -> this.onMouseMove(event));
        this.parent.addListener(5, this.mouseMoveListener);
        this.focusOutListener = (event -> this.onFocusOut(event));
        this.parent.addListener(16, this.focusOutListener);
    }
    
    void onMouseDown(final Event e) {
        if (e.button != 2 || this.navigationActivated) {
            return;
        }
        if (!this.parent.isVisible() || !this.parent.getEnabled() || this.parent.middleClickPressed) {
            return;
        }
        this.initBarState();
        if (!this.hasHBar && !this.hasVBar) {
            return;
        }
        this.navigationActivated = true;
        this.previousCursor = this.parent.getCursor();
        this.parent.setCursor(this.parent.getDisplay().getSystemCursor(0));
        this.originalMouseLocation = this.getMouseLocation();
        this.parent.redraw();
    }
    
    private void initBarState() {
        this.hasHBar = this.computeHasHorizontalBar();
        this.hasVBar = this.computeHasVerticalBar();
    }
    
    private boolean computeHasHorizontalBar() {
        final ScrollBar horizontalBar = this.parent.getHorizontalBar();
        final boolean hasHorizontalBar = horizontalBar != null && horizontalBar.isVisible();
        final boolean exceedHorizontalSpace = this.parent.computeSize(-1, -1).x > this.parent.getSize().x;
        return hasHorizontalBar && exceedHorizontalSpace;
    }
    
    private boolean computeHasVerticalBar() {
        final ScrollBar verticalBar = this.parent.getVerticalBar();
        final boolean hasVerticalBar = verticalBar != null && verticalBar.isEnabled();
        final boolean exceedVerticalSpace = this.parent.computeSize(-1, -1).y > this.parent.getSize().y;
        return hasVerticalBar && exceedVerticalSpace;
    }
    
    private void onMouseUp(final Event e) {
        if (this.computeDist() < 15 && this.computeDist() >= 0) {
            return;
        }
        this.deactivate();
    }
    
    public int computeDist() {
        if (this.originalMouseLocation == null) {
            return -1;
        }
        final Point mouseLocation = this.getMouseLocation();
        final int deltaX = this.originalMouseLocation.x - mouseLocation.x;
        final int deltaY = this.originalMouseLocation.y - mouseLocation.y;
        final int dist = (int)Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return dist;
    }
    
    private void deactivate() {
        this.parent.setCursor(this.previousCursor);
        this.navigationActivated = false;
        this.originalMouseLocation = null;
        this.parent.redraw();
    }
    
    private void onFocusOut(final Event e) {
        this.deactivate();
    }
    
    private void onMouseMove(final Event e) {
        if (!this.navigationActivated) {
            return;
        }
        final Point mouseLocation = this.getMouseLocation();
        final int deltaX = this.originalMouseLocation.x - mouseLocation.x;
        final int deltaY = this.originalMouseLocation.y - mouseLocation.y;
        final int dist = (int)Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (dist < 15) {
            return;
        }
        this.parent.setRedraw(false);
        if (this.hasHBar) {
            final ScrollBar bar = this.parent.getHorizontalBar();
            bar.setSelection((int)(bar.getSelection() - deltaX * 0.1));
            this.fireSelectionEvent(e, bar);
        }
        if (this.hasVBar) {
            final ScrollBar bar = this.parent.getVerticalBar();
            bar.setSelection((int)(bar.getSelection() - deltaY * 0.1));
            this.fireSelectionEvent(e, bar);
        }
        this.parent.setRedraw(true);
        this.parent.redraw();
    }
    
    private void fireSelectionEvent(final Event e, final ScrollBar bar) {
        final Event event = new Event();
        event.widget = bar;
        event.display = this.parent.getDisplay();
        event.type = 13;
        event.time = e.time;
        for (final Listener selectionListener : bar.getListeners(13)) {
            selectionListener.handleEvent(event);
        }
    }
    
    private Point getMouseLocation() {
        final Point cursorLocation = Display.getCurrent().getCursorLocation();
        final Point relativeCursorLocation = this.parent.toControl(cursorLocation);
        return relativeCursorLocation;
    }
    
    private void onPaint(final Event e) {
        if (!this.navigationActivated) {
            return;
        }
        final Rectangle rect = this.parent.getClientArea();
        if (rect.width == 0 || rect.height == 0) {
            return;
        }
        (this.gc = e.gc).setAntialias(1);
        this.gc.setAdvanced(true);
        final Color oldForegroundColor = this.gc.getForeground();
        final Color oldBackgroundColor = this.gc.getBackground();
        this.gc.setBackground(this.parent.getForeground());
        this.drawCircle();
        this.drawCentralPoint();
        this.drawArrows();
        this.gc.setForeground(oldForegroundColor);
        this.gc.setBackground(oldBackgroundColor);
    }
    
    private void drawCircle() {
        this.gc.setBackground(this.parent.getBackground());
        this.gc.setForeground(this.parent.getForeground());
        this.gc.setAlpha(200);
        this.gc.fillOval(this.originalMouseLocation.x - 15, this.originalMouseLocation.y - 15, 30, 30);
        this.gc.setBackground(this.parent.getForeground());
        this.gc.setAlpha(255);
        this.gc.drawOval(this.originalMouseLocation.x - 15, this.originalMouseLocation.y - 15, 30, 30);
    }
    
    private void drawCentralPoint() {
        this.gc.fillOval(this.originalMouseLocation.x - 2, this.originalMouseLocation.y - 2, 4, 4);
    }
    
    private void drawArrows() {
        this.gc.setLineWidth(2);
        if (this.hasHBar) {
            this.drawHorizontalArrows();
        }
        if (this.hasVBar) {
            this.drawVerticalArrows();
        }
    }
    
    private void drawHorizontalArrows() {
        final int[] points = { this.originalMouseLocation.x - 6, this.originalMouseLocation.y + 3, this.originalMouseLocation.x - 9, this.originalMouseLocation.y, this.originalMouseLocation.x - 6, this.originalMouseLocation.y - 3 };
        this.gc.drawPolyline(points);
        points[0] = this.originalMouseLocation.x + 7;
        points[1] = this.originalMouseLocation.y + 3;
        points[2] = this.originalMouseLocation.x + 10;
        points[3] = this.originalMouseLocation.y;
        points[4] = this.originalMouseLocation.x + 7;
        points[5] = this.originalMouseLocation.y - 3;
        this.gc.drawPolyline(points);
    }
    
    private void drawVerticalArrows() {
        final int[] points = { this.originalMouseLocation.x - 3, this.originalMouseLocation.y - 6, this.originalMouseLocation.x, this.originalMouseLocation.y - 10, this.originalMouseLocation.x + 3, this.originalMouseLocation.y - 6 };
        this.gc.drawPolyline(points);
        points[0] = this.originalMouseLocation.x - 3;
        points[1] = this.originalMouseLocation.y + 7;
        points[2] = this.originalMouseLocation.x;
        points[3] = this.originalMouseLocation.y + 11;
        points[4] = this.originalMouseLocation.x + 3;
        points[5] = this.originalMouseLocation.y + 7;
        this.gc.drawPolyline(points);
    }
    
    void dispose() {
        if (this.parent.isDisposed()) {
            return;
        }
        this.parent.removeListener(3, this.mouseDownListener);
        this.parent.removeListener(4, this.mouseUpListener);
        this.parent.removeListener(9, this.paintListener);
        this.parent.removeListener(5, this.mouseMoveListener);
        this.parent.removeListener(7, this.focusOutListener);
    }
}
