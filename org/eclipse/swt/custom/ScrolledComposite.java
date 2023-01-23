//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class ScrolledComposite extends Composite
{
    Control content;
    Listener contentListener;
    Listener filter;
    int minHeight;
    int minWidth;
    boolean expandHorizontal;
    boolean expandVertical;
    boolean alwaysShowScroll;
    boolean showFocusedControl;
    boolean showNextFocusedControl;
    
    public ScrolledComposite(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.minHeight = 0;
        this.minWidth = 0;
        this.expandHorizontal = false;
        this.expandVertical = false;
        this.alwaysShowScroll = false;
        this.showFocusedControl = false;
        this.showNextFocusedControl = true;
        super.setLayout(new ScrolledCompositeLayout());
        final ScrollBar hBar = this.getHorizontalBar();
        if (hBar != null) {
            hBar.setVisible(false);
            hBar.addListener(13, e -> this.hScroll());
        }
        final ScrollBar vBar = this.getVerticalBar();
        if (vBar != null) {
            vBar.setVisible(false);
            vBar.addListener(13, e -> this.vScroll());
        }
        this.contentListener = (e -> {
            if (e.type != 11) {
                return;
            }
            else {
                this.layout(false);
                return;
            }
        });
        Control control;
        Widget w;
        this.filter = (event -> {
            if (event.type == 15) {
                if (!this.showNextFocusedControl) {
                    this.showNextFocusedControl = true;
                }
                else if (event.widget instanceof Control) {
                    control = (Control)event.widget;
                    if (this.contains(control)) {
                        this.showControl(control);
                    }
                }
            }
            else {
                w = event.widget;
                if (w instanceof Control) {
                    this.showNextFocusedControl = (w.getDisplay().getActiveShell() == ((Control)w).getShell());
                }
            }
            return;
        });
        this.addDisposeListener(e -> {
            this.getDisplay().removeFilter(15, this.filter);
            this.getDisplay().removeFilter(16, this.filter);
        });
    }
    
    static int checkStyle(final int style) {
        final int mask = 100666112;
        return style & 0x6000B00;
    }
    
    boolean contains(final Control control) {
        if (control == null || control.isDisposed()) {
            return false;
        }
        for (Composite parent = control.getParent(); parent != null && !(parent instanceof Shell); parent = parent.getParent()) {
            if (this == parent) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getAlwaysShowScrollBars() {
        return this.alwaysShowScroll;
    }
    
    public boolean getExpandHorizontal() {
        this.checkWidget();
        return this.expandHorizontal;
    }
    
    public boolean getExpandVertical() {
        this.checkWidget();
        return this.expandVertical;
    }
    
    public int getMinWidth() {
        this.checkWidget();
        return this.minWidth;
    }
    
    public int getMinHeight() {
        this.checkWidget();
        return this.minHeight;
    }
    
    public Control getContent() {
        return this.content;
    }
    
    public boolean getShowFocusedControl() {
        this.checkWidget();
        return this.showFocusedControl;
    }
    
    void hScroll() {
        if (this.content == null) {
            return;
        }
        final Point location = this.content.getLocation();
        final ScrollBar hBar = this.getHorizontalBar();
        final int hSelection = hBar.getSelection();
        this.content.setLocation(-hSelection, location.y);
    }
    
    boolean needHScroll(final Rectangle contentRect, final boolean vVisible) {
        final ScrollBar hBar = this.getHorizontalBar();
        if (hBar == null) {
            return false;
        }
        final Rectangle hostRect = this.getBounds();
        final int border = this.getBorderWidth();
        final Rectangle rectangle3;
        final Rectangle rectangle = rectangle3 = hostRect;
        rectangle3.width -= 2 * border;
        final ScrollBar vBar = this.getVerticalBar();
        if (vVisible && vBar != null) {
            final Rectangle rectangle4;
            final Rectangle rectangle2 = rectangle4 = hostRect;
            rectangle4.width -= vBar.getSize().x;
        }
        return (!this.expandHorizontal && contentRect.width > hostRect.width) || (this.expandHorizontal && this.minWidth > hostRect.width);
    }
    
    boolean needVScroll(final Rectangle contentRect, final boolean hVisible) {
        final ScrollBar vBar = this.getVerticalBar();
        if (vBar == null) {
            return false;
        }
        final Rectangle hostRect = this.getBounds();
        final int border = this.getBorderWidth();
        final Rectangle rectangle3;
        final Rectangle rectangle = rectangle3 = hostRect;
        rectangle3.height -= 2 * border;
        final ScrollBar hBar = this.getHorizontalBar();
        if (hVisible && hBar != null) {
            final Rectangle rectangle4;
            final Rectangle rectangle2 = rectangle4 = hostRect;
            rectangle4.height -= hBar.getSize().y;
        }
        return (!this.expandVertical && contentRect.height > hostRect.height) || (this.expandVertical && this.minHeight > hostRect.height);
    }
    
    public Point getOrigin() {
        this.checkWidget();
        if (this.content == null) {
            return new Point(0, 0);
        }
        final Point location = this.content.getLocation();
        return new Point(-location.x, -location.y);
    }
    
    public void setOrigin(final Point origin) {
        this.setOrigin(origin.x, origin.y);
    }
    
    public void setOrigin(int x, int y) {
        this.checkWidget();
        if (this.content == null) {
            return;
        }
        final ScrollBar hBar = this.getHorizontalBar();
        if (hBar != null) {
            hBar.setSelection(x);
            x = -hBar.getSelection();
        }
        else {
            x = 0;
        }
        final ScrollBar vBar = this.getVerticalBar();
        if (vBar != null) {
            vBar.setSelection(y);
            y = -vBar.getSelection();
        }
        else {
            y = 0;
        }
        this.content.setLocation(x, y);
    }
    
    public void setAlwaysShowScrollBars(final boolean show) {
        this.checkWidget();
        if (show == this.alwaysShowScroll) {
            return;
        }
        this.alwaysShowScroll = show;
        final ScrollBar hBar = this.getHorizontalBar();
        if (hBar != null && this.alwaysShowScroll) {
            hBar.setVisible(true);
        }
        final ScrollBar vBar = this.getVerticalBar();
        if (vBar != null && this.alwaysShowScroll) {
            vBar.setVisible(true);
        }
        this.layout(false);
    }
    
    public void setContent(final Control content) {
        this.checkWidget();
        if (this.content != null && !this.content.isDisposed()) {
            this.content.removeListener(11, this.contentListener);
            this.content.setBounds(new Rectangle(-200, -200, 0, 0));
        }
        this.content = content;
        final ScrollBar vBar = this.getVerticalBar();
        final ScrollBar hBar = this.getHorizontalBar();
        if (this.content != null) {
            if (vBar != null) {
                vBar.setMaximum(0);
                vBar.setThumb(0);
                vBar.setSelection(0);
            }
            if (hBar != null) {
                hBar.setMaximum(0);
                hBar.setThumb(0);
                hBar.setSelection(0);
            }
            content.setLocation(0, 0);
            this.layout(false);
            this.content.addListener(11, this.contentListener);
        }
        else {
            if (hBar != null) {
                hBar.setVisible(this.alwaysShowScroll);
            }
            if (vBar != null) {
                vBar.setVisible(this.alwaysShowScroll);
            }
        }
    }
    
    public void setExpandHorizontal(final boolean expand) {
        this.checkWidget();
        if (expand == this.expandHorizontal) {
            return;
        }
        this.expandHorizontal = expand;
        this.layout(false);
    }
    
    public void setExpandVertical(final boolean expand) {
        this.checkWidget();
        if (expand == this.expandVertical) {
            return;
        }
        this.expandVertical = expand;
        this.layout(false);
    }
    
    @Override
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setMinHeight(final int height) {
        this.setMinSize(this.minWidth, height);
    }
    
    public void setMinSize(final Point size) {
        if (size == null) {
            this.setMinSize(0, 0);
        }
        else {
            this.setMinSize(size.x, size.y);
        }
    }
    
    public void setMinSize(final int width, final int height) {
        this.checkWidget();
        if (width == this.minWidth && height == this.minHeight) {
            return;
        }
        this.minWidth = Math.max(0, width);
        this.minHeight = Math.max(0, height);
        this.layout(false);
    }
    
    public void setMinWidth(final int width) {
        this.setMinSize(width, this.minHeight);
    }
    
    public void setShowFocusedControl(final boolean show) {
        this.checkWidget();
        if (this.showFocusedControl == show) {
            return;
        }
        final Display display = this.getDisplay();
        display.removeFilter(15, this.filter);
        display.removeFilter(16, this.filter);
        if (!(this.showFocusedControl = show)) {
            return;
        }
        display.addFilter(15, this.filter);
        display.addFilter(16, this.filter);
        final Control control = display.getFocusControl();
        if (this.contains(control)) {
            this.showControl(control);
        }
    }
    
    public void showControl(final Control control) {
        this.checkWidget();
        if (control == null) {
            SWT.error(4);
        }
        if (control.isDisposed()) {
            SWT.error(5);
        }
        if (!this.contains(control)) {
            SWT.error(5);
        }
        final Rectangle itemRect = this.getDisplay().map(control.getParent(), this, control.getBounds());
        final Rectangle area = this.getClientArea();
        final Point origin = this.getOrigin();
        if (itemRect.x < 0) {
            origin.x = Math.max(0, origin.x + itemRect.x);
        }
        else if (area.width < itemRect.x + itemRect.width) {
            origin.x = Math.max(0, origin.x + itemRect.x + Math.min(itemRect.width, area.width) - area.width);
        }
        if (itemRect.y < 0) {
            origin.y = Math.max(0, origin.y + itemRect.y);
        }
        else if (area.height < itemRect.y + itemRect.height) {
            origin.y = Math.max(0, origin.y + itemRect.y + Math.min(itemRect.height, area.height) - area.height);
        }
        this.setOrigin(origin);
    }
    
    void vScroll() {
        if (this.content == null) {
            return;
        }
        final Point location = this.content.getLocation();
        final ScrollBar vBar = this.getVerticalBar();
        final int vSelection = vBar.getSelection();
        this.content.setLocation(location.x, -vSelection);
    }
}
