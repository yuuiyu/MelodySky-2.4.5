//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class ScrolledCompositeLayout extends Layout
{
    boolean inLayout;
    static final int DEFAULT_WIDTH = 64;
    static final int DEFAULT_HEIGHT = 64;
    
    ScrolledCompositeLayout() {
        this.inLayout = false;
    }
    
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final ScrolledComposite sc = (ScrolledComposite)composite;
        final Point size = new Point(64, 64);
        if (sc.content != null) {
            final Point preferredSize = sc.content.computeSize(wHint, hHint, flushCache);
            final Point currentSize = sc.content.getSize();
            size.x = (sc.getExpandHorizontal() ? preferredSize.x : currentSize.x);
            size.y = (sc.getExpandVertical() ? preferredSize.y : currentSize.y);
        }
        size.x = Math.max(size.x, sc.minWidth);
        size.y = Math.max(size.y, sc.minHeight);
        if (wHint != -1) {
            size.x = wHint;
        }
        if (hHint != -1) {
            size.y = hHint;
        }
        return size;
    }
    
    @Override
    protected boolean flushCache(final Control control) {
        return true;
    }
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        if (this.inLayout) {
            return;
        }
        final ScrolledComposite sc = (ScrolledComposite)composite;
        if (sc.content == null) {
            return;
        }
        final ScrollBar hBar = sc.getHorizontalBar();
        final ScrollBar vBar = sc.getVerticalBar();
        if (hBar != null && hBar.getSize().y >= sc.getSize().y) {
            return;
        }
        if (vBar != null && vBar.getSize().x >= sc.getSize().x) {
            return;
        }
        this.inLayout = true;
        final Rectangle contentRect = sc.content.getBounds();
        if (!sc.alwaysShowScroll) {
            boolean hVisible = sc.needHScroll(contentRect, false);
            final boolean vVisible = sc.needVScroll(contentRect, hVisible);
            if (!hVisible && vVisible) {
                hVisible = sc.needHScroll(contentRect, vVisible);
            }
            if (hBar != null) {
                hBar.setVisible(hVisible);
            }
            if (vBar != null) {
                vBar.setVisible(vVisible);
            }
        }
        final Rectangle hostRect = sc.getClientArea();
        if (sc.expandHorizontal) {
            contentRect.width = Math.max(sc.minWidth, hostRect.width);
        }
        if (sc.expandVertical) {
            contentRect.height = Math.max(sc.minHeight, hostRect.height);
        }
        final GC gc = new GC((Drawable)sc);
        if (hBar != null) {
            hBar.setMaximum(contentRect.width);
            hBar.setThumb(Math.min(contentRect.width, hostRect.width));
            hBar.setIncrement((int)gc.getFontMetrics().getAverageCharacterWidth());
            hBar.setPageIncrement(hostRect.width);
            final int hPage = contentRect.width - hostRect.width;
            int hSelection = hBar.getSelection();
            if (hSelection >= hPage) {
                if (hPage <= 0) {
                    hSelection = 0;
                    hBar.setSelection(0);
                }
                contentRect.x = -hSelection;
            }
        }
        if (vBar != null) {
            vBar.setMaximum(contentRect.height);
            vBar.setThumb(Math.min(contentRect.height, hostRect.height));
            vBar.setIncrement(gc.getFontMetrics().getHeight());
            vBar.setPageIncrement(hostRect.height);
            final int vPage = contentRect.height - hostRect.height;
            int vSelection = vBar.getSelection();
            if (vSelection >= vPage) {
                if (vPage <= 0) {
                    vSelection = 0;
                    vBar.setSelection(0);
                }
                contentRect.y = -vSelection;
            }
        }
        gc.dispose();
        sc.content.setBounds(contentRect);
        this.inLayout = false;
    }
}
