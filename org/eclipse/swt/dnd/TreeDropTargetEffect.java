//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.widgets.*;

public class TreeDropTargetEffect extends DropTargetEffect
{
    static final int SCROLL_HYSTERESIS = 200;
    static final int EXPAND_HYSTERESIS = 1000;
    long dropIndex;
    long scrollIndex;
    long scrollBeginTime;
    long expandIndex;
    long expandBeginTime;
    TreeItem insertItem;
    boolean insertBefore;
    
    public TreeDropTargetEffect(final Tree tree) {
        super((Control)tree);
    }
    
    int checkEffect(int effect) {
        if ((effect & 0x1) != 0x0) {
            effect = (effect & 0xFFFFFFFB & 0xFFFFFFFD);
        }
        if ((effect & 0x2) != 0x0) {
            effect &= 0xFFFFFFFB;
        }
        return effect;
    }
    
    public void dragEnter(final DropTargetEvent event) {
        this.dropIndex = -1L;
        this.insertItem = null;
        this.expandBeginTime = 0L;
        this.expandIndex = -1L;
        this.scrollBeginTime = 0L;
        this.scrollIndex = -1L;
    }
    
    public void dragLeave(final DropTargetEvent event) {
        final Tree tree = (Tree)this.control;
        final long handle = tree.handle;
        if (this.dropIndex != -1L) {
            final TVITEM tvItem = new TVITEM();
            tvItem.hItem = this.dropIndex;
            tvItem.mask = 8;
            tvItem.stateMask = 8;
            tvItem.state = 0;
            OS.SendMessage(handle, 4415, 0L, tvItem);
            this.dropIndex = -1L;
        }
        if (this.insertItem != null) {
            tree.setInsertMark(null, false);
            this.insertItem = null;
        }
        this.expandBeginTime = 0L;
        this.expandIndex = -1L;
        this.scrollBeginTime = 0L;
        this.scrollIndex = -1L;
    }
    
    public void dragOver(final DropTargetEvent event) {
        final Tree tree = (Tree)this.getControl();
        final int effect = this.checkEffect(event.feedback);
        final long handle = tree.handle;
        Point coordinates = new Point(event.x, event.y);
        coordinates = DPIUtil.autoScaleUp(tree.toControl(coordinates));
        final TVHITTESTINFO lpht = new TVHITTESTINFO();
        lpht.x = coordinates.x;
        lpht.y = coordinates.y;
        OS.SendMessage(handle, 4369, 0L, lpht);
        final long hItem = lpht.hItem;
        if ((effect & 0x8) == 0x0) {
            this.scrollBeginTime = 0L;
            this.scrollIndex = -1L;
        }
        else if (hItem != -1L && this.scrollIndex == hItem && this.scrollBeginTime != 0L) {
            if (System.currentTimeMillis() >= this.scrollBeginTime) {
                final long topItem = OS.SendMessage(handle, 4362, 5L, 0L);
                final long nextItem = OS.SendMessage(handle, 4362, (hItem == topItem) ? 7L : 6L, hItem);
                boolean scroll = true;
                if (hItem == topItem) {
                    scroll = (nextItem != 0L);
                }
                else {
                    final RECT itemRect = new RECT();
                    if (OS.TreeView_GetItemRect(handle, nextItem, itemRect, true)) {
                        final RECT rect = new RECT();
                        OS.GetClientRect(handle, rect);
                        final POINT pt = new POINT();
                        pt.x = itemRect.left;
                        pt.y = itemRect.top;
                        if (OS.PtInRect(rect, pt)) {
                            pt.y = itemRect.bottom;
                            if (OS.PtInRect(rect, pt)) {
                                scroll = false;
                            }
                        }
                    }
                }
                if (scroll) {
                    OS.SendMessage(handle, 4372, 0L, nextItem);
                    tree.redraw();
                }
                this.scrollBeginTime = 0L;
                this.scrollIndex = -1L;
            }
        }
        else {
            this.scrollBeginTime = System.currentTimeMillis() + 200L;
            this.scrollIndex = hItem;
        }
        if ((effect & 0x10) == 0x0) {
            this.expandBeginTime = 0L;
            this.expandIndex = -1L;
        }
        else if (hItem != -1L && this.expandIndex == hItem && this.expandBeginTime != 0L) {
            if (System.currentTimeMillis() >= this.expandBeginTime) {
                if (OS.SendMessage(handle, 4362, 4L, hItem) != 0L) {
                    final TreeItem item = (TreeItem)tree.getDisplay().findWidget(tree.handle, hItem);
                    if (item != null && !item.getExpanded()) {
                        item.setExpanded(true);
                        tree.redraw();
                        final Event expandEvent = new Event();
                        expandEvent.item = item;
                        tree.notifyListeners(17, expandEvent);
                    }
                }
                this.expandBeginTime = 0L;
                this.expandIndex = -1L;
            }
        }
        else {
            this.expandBeginTime = System.currentTimeMillis() + 1000L;
            this.expandIndex = hItem;
        }
        if (this.dropIndex != -1L && (this.dropIndex != hItem || (effect & 0x1) == 0x0)) {
            final TVITEM tvItem = new TVITEM();
            tvItem.hItem = this.dropIndex;
            tvItem.mask = 8;
            tvItem.stateMask = 8;
            tvItem.state = 0;
            OS.SendMessage(handle, 4415, 0L, tvItem);
            this.dropIndex = -1L;
        }
        if (hItem != -1L && hItem != this.dropIndex && (effect & 0x1) != 0x0) {
            final TVITEM tvItem = new TVITEM();
            tvItem.hItem = hItem;
            tvItem.mask = 8;
            tvItem.stateMask = 8;
            tvItem.state = 8;
            OS.SendMessage(handle, 4415, 0L, tvItem);
            this.dropIndex = hItem;
        }
        if ((effect & 0x2) != 0x0 || (effect & 0x4) != 0x0) {
            final boolean before = (effect & 0x2) != 0x0;
            final TreeItem item2 = (TreeItem)tree.getDisplay().findWidget(tree.handle, hItem);
            if (item2 != null) {
                if (item2 != this.insertItem || before != this.insertBefore) {
                    tree.setInsertMark(item2, before);
                }
                this.insertItem = item2;
                this.insertBefore = before;
            }
            else {
                if (this.insertItem != null) {
                    tree.setInsertMark(null, false);
                }
                this.insertItem = null;
            }
        }
        else {
            if (this.insertItem != null) {
                tree.setInsertMark(null, false);
            }
            this.insertItem = null;
        }
    }
}
