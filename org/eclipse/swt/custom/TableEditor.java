//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class TableEditor extends ControlEditor
{
    Table table;
    TableItem item;
    int column;
    ControlListener columnListener;
    Runnable timer;
    static final int TIMEOUT = 1500;
    
    public TableEditor(final Table table) {
        super((Composite)table);
        this.column = -1;
        this.table = table;
        this.columnListener = (ControlListener)new lIlIll(this);
        this.timer = this::layout;
        this.grabVertical = true;
    }
    
    Rectangle computeBounds() {
        if (this.item == null || this.column == -1 || this.item.isDisposed()) {
            return new Rectangle(0, 0, 0, 0);
        }
        final Rectangle cell = this.item.getBounds(this.column);
        final Rectangle rect = this.item.getImageBounds(this.column);
        if (rect.width != 0) {
            final int imageGap = Math.max(rect.x - cell.x, 0);
            cell.x = rect.x + rect.width;
            final Rectangle rectangle6;
            final Rectangle rectangle = rectangle6 = cell;
            rectangle6.width -= imageGap + rect.width;
        }
        final Rectangle area = this.table.getClientArea();
        if (cell.x < area.x + area.width && cell.x + cell.width > area.x + area.width) {
            cell.width = area.x + area.width - cell.x;
        }
        final Rectangle editorRect = new Rectangle(cell.x, cell.y, this.minimumWidth, this.minimumHeight);
        if (this.grabHorizontal) {
            editorRect.width = Math.max(cell.width, this.minimumWidth);
        }
        if (this.grabVertical) {
            editorRect.height = Math.max(cell.height, this.minimumHeight);
        }
        if (this.horizontalAlignment == 131072) {
            final Rectangle rectangle7;
            final Rectangle rectangle2 = rectangle7 = editorRect;
            rectangle7.x += cell.width - editorRect.width;
        }
        else if (this.horizontalAlignment != 16384) {
            final Rectangle rectangle8;
            final Rectangle rectangle3 = rectangle8 = editorRect;
            rectangle8.x += (cell.width - editorRect.width) / 2;
        }
        if (this.verticalAlignment == 1024) {
            final Rectangle rectangle9;
            final Rectangle rectangle4 = rectangle9 = editorRect;
            rectangle9.y += cell.height - editorRect.height;
        }
        else if (this.verticalAlignment != 128) {
            final Rectangle rectangle10;
            final Rectangle rectangle5 = rectangle10 = editorRect;
            rectangle10.y += (cell.height - editorRect.height) / 2;
        }
        return editorRect;
    }
    
    public void dispose() {
        if (this.table != null && !this.table.isDisposed() && this.column > -1 && this.column < this.table.getColumnCount()) {
            final TableColumn tableColumn = this.table.getColumn(this.column);
            tableColumn.removeControlListener(this.columnListener);
        }
        this.columnListener = null;
        this.table = null;
        this.item = null;
        this.column = -1;
        this.timer = null;
        super.dispose();
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public TableItem getItem() {
        return this.item;
    }
    
    void resize() {
        this.layout();
        if (this.table != null) {
            final Display display = this.table.getDisplay();
            display.timerExec(-1, this.timer);
            display.timerExec(1500, this.timer);
        }
    }
    
    public void setColumn(final int column) {
        final int columnCount = this.table.getColumnCount();
        if (columnCount == 0) {
            this.column = ((column == 0) ? 0 : -1);
            this.resize();
            return;
        }
        if (this.column > -1 && this.column < columnCount) {
            final TableColumn tableColumn = this.table.getColumn(this.column);
            tableColumn.removeControlListener(this.columnListener);
            this.column = -1;
        }
        if (column < 0 || column >= this.table.getColumnCount()) {
            return;
        }
        this.column = column;
        final TableColumn tableColumn = this.table.getColumn(this.column);
        tableColumn.addControlListener(this.columnListener);
        this.resize();
    }
    
    public void setItem(final TableItem item) {
        this.item = item;
        this.resize();
    }
    
    public void setEditor(final Control editor) {
        super.setEditor(editor);
        this.resize();
    }
    
    public void setEditor(final Control editor, final TableItem item, final int column) {
        this.setItem(item);
        this.setColumn(column);
        this.setEditor(editor);
    }
    
    public void layout() {
        if (this.table == null || this.table.isDisposed()) {
            return;
        }
        if (this.item == null || this.item.isDisposed()) {
            return;
        }
        final int columnCount = this.table.getColumnCount();
        if (columnCount == 0 && this.column != 0) {
            return;
        }
        if (columnCount > 0 && (this.column < 0 || this.column >= columnCount)) {
            return;
        }
        super.layout();
    }
}
