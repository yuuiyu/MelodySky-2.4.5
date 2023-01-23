//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class TableDropTargetEffect extends DropTargetEffect
{
    static final int SCROLL_HYSTERESIS = 200;
    int scrollIndex;
    long scrollBeginTime;
    TableItem dropHighlight;
    int iItemInsert;
    
    public TableDropTargetEffect(final Table table) {
        super((Control)table);
        this.scrollIndex = -1;
        this.iItemInsert = -1;
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
        this.scrollBeginTime = 0L;
        this.scrollIndex = -1;
        this.dropHighlight = null;
        this.iItemInsert = -1;
    }
    
    public void dragLeave(final DropTargetEvent event) {
        final Table table = (Table)this.control;
        final long handle = table.handle;
        if (this.dropHighlight != null) {
            final LVITEM lvItem = new LVITEM();
            lvItem.stateMask = 8;
            OS.SendMessage(handle, 4139, -1L, lvItem);
            this.dropHighlight = null;
        }
        if (this.iItemInsert != -1) {
            final LVINSERTMARK plvim = new LVINSERTMARK();
            plvim.cbSize = LVINSERTMARK.sizeof;
            plvim.iItem = -1;
            OS.SendMessage(handle, 4262, 0L, plvim);
            this.iItemInsert = -1;
        }
        this.scrollBeginTime = 0L;
        this.scrollIndex = -1;
    }
    
    public void dragOver(final DropTargetEvent event) {
        final Table table = (Table)this.getControl();
        final int effect = this.checkEffect(event.feedback);
        final long handle = table.handle;
        Point coordinates = new Point(event.x, event.y);
        coordinates = DPIUtil.autoScaleUp(table.toControl(coordinates));
        final LVHITTESTINFO pinfo = new LVHITTESTINFO();
        pinfo.x = coordinates.x;
        pinfo.y = coordinates.y;
        OS.SendMessage(handle, 4114, 0L, pinfo);
        if ((effect & 0x8) == 0x0) {
            this.scrollBeginTime = 0L;
            this.scrollIndex = -1;
        }
        else if (pinfo.iItem != -1 && this.scrollIndex == pinfo.iItem && this.scrollBeginTime != 0L) {
            if (System.currentTimeMillis() >= this.scrollBeginTime) {
                final int top = Math.max(0, (int)OS.SendMessage(handle, 4135, 0L, 0L));
                final int count = (int)OS.SendMessage(handle, 4100, 0L, 0L);
                final int index = (this.scrollIndex - 1 < top) ? Math.max(0, this.scrollIndex - 1) : Math.min(count - 1, this.scrollIndex + 1);
                boolean scroll = true;
                if (pinfo.iItem == top) {
                    scroll = (pinfo.iItem != index);
                }
                else {
                    final RECT itemRect = new RECT();
                    itemRect.left = 0;
                    if (OS.SendMessage(handle, 4110, pinfo.iItem, itemRect) != 0L) {
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
                    OS.SendMessage(handle, 4115, index, 0L);
                    table.redraw();
                }
                this.scrollBeginTime = 0L;
                this.scrollIndex = -1;
            }
        }
        else {
            this.scrollBeginTime = System.currentTimeMillis() + 200L;
            this.scrollIndex = pinfo.iItem;
        }
        if (pinfo.iItem != -1 && (effect & 0x1) != 0x0) {
            final TableItem item = table.getItem(pinfo.iItem);
            if (this.dropHighlight != item) {
                final LVITEM lvItem = new LVITEM();
                lvItem.stateMask = 8;
                OS.SendMessage(handle, 4139, -1L, lvItem);
                lvItem.state = 8;
                OS.SendMessage(handle, 4139, pinfo.iItem, lvItem);
                this.dropHighlight = item;
            }
        }
        else if (this.dropHighlight != null) {
            final LVITEM lvItem2 = new LVITEM();
            lvItem2.stateMask = 8;
            OS.SendMessage(handle, 4139, -1L, lvItem2);
            this.dropHighlight = null;
        }
        if (pinfo.iItem != -1 && (effect & 0x6) != 0x0) {
            final LVINSERTMARK plvim = new LVINSERTMARK();
            plvim.cbSize = LVINSERTMARK.sizeof;
            plvim.dwFlags = (((effect & 0x4) != 0x0) ? 1 : 0);
            plvim.iItem = pinfo.iItem;
            if (OS.SendMessage(handle, 4262, 0L, plvim) != 0L) {
                this.iItemInsert = pinfo.iItem;
            }
        }
        else if (this.iItemInsert != -1) {
            final LVINSERTMARK plvim = new LVINSERTMARK();
            plvim.cbSize = LVINSERTMARK.sizeof;
            plvim.iItem = -1;
            OS.SendMessage(handle, 4262, 0L, plvim);
            this.iItemInsert = -1;
        }
    }
}
