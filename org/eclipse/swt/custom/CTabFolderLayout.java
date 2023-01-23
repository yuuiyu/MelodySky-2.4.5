//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class CTabFolderLayout extends Layout
{
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final CTabFolder folder = (CTabFolder)composite;
        final CTabItem[] items = folder.items;
        final CTabFolderRenderer renderer = folder.renderer;
        int tabW = 0;
        int selectedIndex = folder.selectedIndex;
        if (selectedIndex == -1) {
            selectedIndex = 0;
        }
        final GC gc = new GC((Drawable)folder);
        for (int i = 0; i < items.length; ++i) {
            if (folder.single) {
                tabW = Math.max(tabW, renderer.computeSize(i, 2, gc, -1, -1).x);
            }
            else {
                int state = 0;
                if (i == selectedIndex) {
                    state |= 0x2;
                }
                tabW += renderer.computeSize(i, state, gc, -1, -1).x;
            }
        }
        int width = 0;
        int wrapHeight = 0;
        boolean leftControl = false;
        boolean rightControl = false;
        if (wHint == -1) {
            for (int j = 0; j < folder.controls.length; ++j) {
                final Control control = folder.controls[j];
                if (!control.isDisposed() && control.getVisible()) {
                    if ((folder.controlAlignments[j] & 0x4000) != 0x0) {
                        leftControl = true;
                    }
                    else {
                        rightControl = true;
                    }
                    width += control.computeSize(-1, -1).x;
                }
            }
        }
        else {
            final Point size = new Point(wHint, hHint);
            final boolean[][] positions = { null };
            final Rectangle[] rects = folder.computeControlBounds(size, positions);
            int minY = Integer.MAX_VALUE;
            int maxY = 0;
            for (int k = 0; k < rects.length; ++k) {
                if (positions[0][k]) {
                    minY = Math.min(minY, rects[k].y);
                    maxY = Math.max(maxY, rects[k].y + rects[k].height);
                    wrapHeight = maxY - minY;
                }
                else {
                    if ((folder.controlAlignments[k] & 0x4000) != 0x0) {
                        leftControl = true;
                    }
                    else {
                        rightControl = true;
                    }
                    width += rects[k].width;
                }
            }
        }
        if (leftControl) {
            width += 6;
        }
        if (rightControl) {
            width += 6;
        }
        tabW += width;
        gc.dispose();
        int controlW = 0;
        int controlH = 0;
        for (final CTabItem item : items) {
            final Control control2 = item.control;
            if (control2 != null && !control2.isDisposed()) {
                final Point size2 = control2.computeSize(wHint, hHint, flushCache);
                controlW = Math.max(controlW, size2.x);
                controlH = Math.max(controlH, size2.y);
            }
        }
        int minWidth = Math.max(tabW, controlW + folder.marginWidth);
        int minHeight = folder.minimized ? 0 : (controlH + wrapHeight);
        if (minWidth == 0) {
            minWidth = 64;
        }
        if (minHeight == 0) {
            minHeight = 64;
        }
        if (wHint != -1) {
            minWidth = wHint;
        }
        if (hHint != -1) {
            minHeight = hHint;
        }
        return new Point(minWidth, minHeight);
    }
    
    @Override
    protected boolean flushCache(final Control control) {
        return true;
    }
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        final CTabFolder folder = (CTabFolder)composite;
        if (folder.selectedIndex != -1) {
            final Control control = folder.items[folder.selectedIndex].control;
            if (control != null && !control.isDisposed()) {
                control.setBounds(folder.getClientArea());
            }
        }
    }
}
