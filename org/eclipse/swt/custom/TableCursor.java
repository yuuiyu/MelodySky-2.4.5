//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class TableCursor extends Canvas
{
    Table table;
    TableItem row;
    TableColumn column;
    Listener listener;
    Listener tableListener;
    Listener resizeListener;
    Listener disposeItemListener;
    Listener disposeColumnListener;
    Color background;
    Color foreground;
    static final int BACKGROUND = 27;
    static final int FOREGROUND = 26;
    
    public TableCursor(final Table parent, final int style) {
        super(parent, style);
        this.row = null;
        this.column = null;
        this.background = null;
        this.foreground = null;
        this.table = parent;
        this.setBackground(null);
        this.setForeground(null);
        int event2 = 0;
        this.listener = (event -> {
            Label_0148_1: {
                switch (event.type) {
                    case 12: {
                        this.onDispose(event);
                        break;
                    }
                    case 15:
                    case 16: {
                        this.redraw();
                        break;
                    }
                    case 1: {
                        this.keyDown(event);
                        break;
                    }
                    case 9: {
                        this.paint(event);
                        break;
                    }
                    case 31: {
                        event.doit = true;
                        switch (event.detail) {
                            case 4:
                            case 32:
                            case 64: {
                                event.doit = false;
                                break Label_0148_1;
                            }
                        }
                        break;
                    }
                }
            }
            return;
        });
        final int[] events;
        final int[] array = events = new int[] { 12, 15, 16, 1, 9, 31 };
        for (int length = array.length, i = 0; i < length; ++i) {
            event2 = array[i];
            this.addListener(event2, this.listener);
        }
        this.tableListener = (event -> {
            switch (event.type) {
                case 3: {
                    this.tableMouseDown(event);
                    break;
                }
                case 15: {
                    this.tableFocusIn(event);
                    break;
                }
            }
            return;
        });
        this.table.addListener(15, this.tableListener);
        this.table.addListener(3, this.tableListener);
        this.disposeItemListener = (event -> {
            this.unhookRowColumnListeners();
            this.row = null;
            this.column = null;
            this._resize();
            return;
        });
        this.disposeColumnListener = (event -> {
            this.unhookRowColumnListeners();
            this.row = null;
            this.column = null;
            this._resize();
            return;
        });
        this.resizeListener = (event -> this._resize());
        final ScrollBar hBar = this.table.getHorizontalBar();
        if (hBar != null) {
            hBar.addListener(13, this.resizeListener);
        }
        final ScrollBar vBar = this.table.getVerticalBar();
        if (vBar != null) {
            vBar.addListener(13, this.resizeListener);
        }
        this.getAccessible().addAccessibleControlListener((AccessibleControlListener)new llllII(this));
        this.getAccessible().addAccessibleListener((AccessibleListener)new lIIIIll(this));
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        final TypedListener typedListener = new TypedListener(listener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.table.removeListener(15, this.tableListener);
        this.table.removeListener(3, this.tableListener);
        this.unhookRowColumnListeners();
        final ScrollBar hBar = this.table.getHorizontalBar();
        if (hBar != null) {
            hBar.removeListener(13, this.resizeListener);
        }
        final ScrollBar vBar = this.table.getVerticalBar();
        if (vBar != null) {
            vBar.removeListener(13, this.resizeListener);
        }
    }
    
    void keyDown(final Event event) {
        if (this.row == null) {
            return;
        }
        switch (event.character) {
            case '\r': {
                this.notifyListeners(14, new Event());
            }
            default: {
                final int rowIndex = this.table.indexOf(this.row);
                final int columnIndex = (this.column == null) ? 0 : this.table.indexOf(this.column);
                switch (event.keyCode) {
                    case 16777217: {
                        this.setRowColumn(Math.max(0, rowIndex - 1), columnIndex, true);
                        break;
                    }
                    case 16777218: {
                        this.setRowColumn(Math.min(rowIndex + 1, this.table.getItemCount() - 1), columnIndex, true);
                        break;
                    }
                    case 16777219:
                    case 16777220: {
                        final int columnCount = this.table.getColumnCount();
                        if (columnCount == 0) {
                            break;
                        }
                        int[] order;
                        int index;
                        for (order = this.table.getColumnOrder(), index = 0; index < order.length && order[index] != columnIndex; ++index) {}
                        if (index == order.length) {
                            index = 0;
                        }
                        final int leadKey = ((this.getStyle() & 0x4000000) != 0x0) ? 16777220 : 16777219;
                        if (event.keyCode == leadKey) {
                            this.setRowColumn(rowIndex, order[Math.max(0, index - 1)], true);
                            break;
                        }
                        this.setRowColumn(rowIndex, order[Math.min(columnCount - 1, index + 1)], true);
                        break;
                    }
                    case 16777223: {
                        this.setRowColumn(0, columnIndex, true);
                        break;
                    }
                    case 16777224: {
                        final int i = this.table.getItemCount() - 1;
                        this.setRowColumn(i, columnIndex, true);
                        break;
                    }
                    case 16777221: {
                        int index2 = this.table.getTopIndex();
                        if (index2 == rowIndex) {
                            final Rectangle rect = this.table.getClientArea();
                            final TableItem item = this.table.getItem(index2);
                            final Rectangle itemRect = item.getBounds(0);
                            final Rectangle rectangle3;
                            final Rectangle rectangle = rectangle3 = rect;
                            rectangle3.height -= itemRect.y;
                            final int height = this.table.getItemHeight();
                            final int page = Math.max(1, rect.height / height);
                            index2 = Math.max(0, index2 - page + 1);
                        }
                        this.setRowColumn(index2, columnIndex, true);
                        break;
                    }
                    case 16777222: {
                        int index2 = this.table.getTopIndex();
                        final Rectangle rect = this.table.getClientArea();
                        final TableItem item = this.table.getItem(index2);
                        final Rectangle itemRect = item.getBounds(0);
                        final Rectangle rectangle4;
                        final Rectangle rectangle2 = rectangle4 = rect;
                        rectangle4.height -= itemRect.y;
                        final int height = this.table.getItemHeight();
                        final int page = Math.max(1, rect.height / height);
                        final int end = this.table.getItemCount() - 1;
                        index2 = Math.min(end, index2 + page - 1);
                        if (index2 == rowIndex) {
                            index2 = Math.min(end, index2 + page - 1);
                        }
                        this.setRowColumn(index2, columnIndex, true);
                        break;
                    }
                }
            }
        }
    }
    
    void paint(final Event event) {
        if (this.row == null) {
            return;
        }
        final int columnIndex = (this.column == null) ? 0 : this.table.indexOf(this.column);
        final GC gc = event.gc;
        gc.setBackground(this.getBackground());
        gc.setForeground(this.getForeground());
        gc.fillRectangle(event.x, event.y, event.width, event.height);
        int x = 0;
        final Point size = this.getSize();
        final Image image = this.row.getImage(columnIndex);
        if (image != null) {
            final Rectangle imageSize = image.getBounds();
            final int imageY = (size.y - imageSize.height) / 2;
            gc.drawImage(image, x, imageY);
            x += imageSize.width;
        }
        final String text = this.row.getText(columnIndex);
        if (text.length() > 0) {
            final Rectangle bounds = this.row.getBounds(columnIndex);
            final Point extent = gc.stringExtent(text);
            final String platform = SWT.getPlatform();
            if ("win32".equals(platform)) {
                if (this.table.getColumnCount() == 0 || columnIndex == 0) {
                    x += 2;
                }
                else {
                    final int alignmnent = this.column.getAlignment();
                    switch (alignmnent) {
                        case 16384: {
                            x += 6;
                            break;
                        }
                        case 131072: {
                            x = bounds.width - extent.x - 6;
                            break;
                        }
                        case 16777216: {
                            x += (bounds.width - x - extent.x) / 2;
                            break;
                        }
                    }
                }
            }
            else if (this.table.getColumnCount() == 0) {
                x += 5;
            }
            else {
                final int alignmnent = this.column.getAlignment();
                switch (alignmnent) {
                    case 16384: {
                        x += 5;
                        break;
                    }
                    case 131072: {
                        x = bounds.width - extent.x - 2;
                        break;
                    }
                    case 16777216: {
                        x += (bounds.width - x - extent.x) / 2 + 2;
                        break;
                    }
                }
            }
            final int textY = (size.y - extent.y) / 2;
            gc.drawString(text, x, textY);
        }
        if (this.isFocusControl()) {
            final Display display = this.getDisplay();
            gc.setBackground(display.getSystemColor(2));
            gc.setForeground(display.getSystemColor(1));
            gc.drawFocus(0, 0, size.x, size.y);
        }
    }
    
    void tableFocusIn(final Event event) {
        if (this.isDisposed()) {
            return;
        }
        if (this.isVisible()) {
            if (this.row == null && this.column == null) {
                return;
            }
            this.setFocus();
        }
    }
    
    void tableMouseDown(final Event event) {
        if (this.isDisposed() || !this.isVisible()) {
            return;
        }
        final Point pt = new Point(event.x, event.y);
        final int lineWidth = this.table.getLinesVisible() ? this.table.getGridLineWidth() : 0;
        TableItem item = this.table.getItem(pt);
        if ((this.table.getStyle() & 0x10000) != 0x0) {
            if (item == null) {
                return;
            }
        }
        else {
            final int start = (item != null) ? this.table.indexOf(item) : this.table.getTopIndex();
            final int end = this.table.getItemCount();
            final Rectangle clientRect = this.table.getClientArea();
            for (int i = start; i < end; ++i) {
                final TableItem nextItem = this.table.getItem(i);
                final Rectangle rect = nextItem.getBounds(0);
                if (pt.y >= rect.y && pt.y < rect.y + rect.height + lineWidth) {
                    item = nextItem;
                    break;
                }
                if (rect.y > clientRect.y + clientRect.height) {
                    return;
                }
            }
            if (item == null) {
                return;
            }
        }
        TableColumn newColumn = null;
        final int columnCount = this.table.getColumnCount();
        if (columnCount == 0) {
            if ((this.table.getStyle() & 0x10000) == 0x0) {
                final Rectangle rect2;
                final Rectangle rectangle3;
                final Rectangle bounds = rectangle3 = (rect2 = item.getBounds(0));
                rectangle3.width += lineWidth;
                final Rectangle rectangle4;
                final Rectangle rectangle = rectangle4 = rect2;
                rectangle4.height += lineWidth;
                if (!rect2.contains(pt)) {
                    return;
                }
            }
        }
        else {
            for (int j = 0; j < columnCount; ++j) {
                final Rectangle rect3;
                final Rectangle rectangle5;
                final Rectangle bounds2 = rectangle5 = (rect3 = item.getBounds(j));
                rectangle5.width += lineWidth;
                final Rectangle rectangle6;
                final Rectangle rectangle2 = rectangle6 = rect3;
                rectangle6.height += lineWidth;
                if (rect3.contains(pt)) {
                    newColumn = this.table.getColumn(j);
                    break;
                }
            }
            if (newColumn == null) {
                if ((this.table.getStyle() & 0x10000) == 0x0) {
                    return;
                }
                newColumn = this.table.getColumn(0);
            }
        }
        this.setRowColumn(item, newColumn, true);
        this.setFocus();
    }
    
    void setRowColumn(final int row, final int column, final boolean notify) {
        final TableItem item = (row == -1) ? null : this.table.getItem(row);
        final TableColumn col = (column == -1 || this.table.getColumnCount() == 0) ? null : this.table.getColumn(column);
        this.setRowColumn(item, col, notify);
    }
    
    void setRowColumn(final TableItem row, final TableColumn column, final boolean notify) {
        if (this.row == row && this.column == column) {
            return;
        }
        if (this.row != null && this.row != row) {
            this.row.removeListener(12, this.disposeItemListener);
            this.row = null;
        }
        if (this.column != null && this.column != column) {
            this.column.removeListener(12, this.disposeColumnListener);
            this.column.removeListener(10, this.resizeListener);
            this.column.removeListener(11, this.resizeListener);
            this.column = null;
        }
        if (row != null) {
            if (this.row != row) {
                (this.row = row).addListener(12, this.disposeItemListener);
                this.table.showItem(row);
            }
            if (this.column != column && column != null) {
                (this.column = column).addListener(12, this.disposeColumnListener);
                column.addListener(10, this.resizeListener);
                column.addListener(11, this.resizeListener);
                this.table.showColumn(column);
            }
            final int columnIndex = (column == null) ? 0 : this.table.indexOf(column);
            this.setBounds(row.getBounds(columnIndex));
            this.redraw();
            if (notify) {
                this.notifyListeners(13, new Event());
            }
        }
        this.getAccessible().setFocus(-1);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (visible) {
            this._resize();
        }
        super.setVisible(visible);
    }
    
    public void removeSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(13, listener);
        this.removeListener(14, listener);
    }
    
    void _resize() {
        if (this.row == null) {
            this.setBounds(-200, -200, 0, 0);
        }
        else {
            final int columnIndex = (this.column == null) ? 0 : this.table.indexOf(this.column);
            this.setBounds(this.row.getBounds(columnIndex));
        }
    }
    
    public int getColumn() {
        this.checkWidget();
        return (this.column == null) ? 0 : this.table.indexOf(this.column);
    }
    
    @Override
    public Color getBackground() {
        this.checkWidget();
        if (this.background == null) {
            return this.getDisplay().getSystemColor(27);
        }
        return this.background;
    }
    
    @Override
    public Color getForeground() {
        this.checkWidget();
        if (this.foreground == null) {
            return this.getDisplay().getSystemColor(26);
        }
        return this.foreground;
    }
    
    public TableItem getRow() {
        this.checkWidget();
        return this.row;
    }
    
    @Override
    public void setBackground(final Color color) {
        this.background = color;
        super.setBackground(this.getBackground());
        this.redraw();
    }
    
    @Override
    public void setForeground(final Color color) {
        this.foreground = color;
        super.setForeground(this.getForeground());
        this.redraw();
    }
    
    public void setSelection(final int row, final int column) {
        this.checkWidget();
        final int columnCount = this.table.getColumnCount();
        final int maxColumnIndex = (columnCount == 0) ? 0 : (columnCount - 1);
        if (row < 0 || row >= this.table.getItemCount() || column < 0 || column > maxColumnIndex) {
            SWT.error(5);
        }
        this.setRowColumn(row, column, false);
    }
    
    public void setSelection(final TableItem row, final int column) {
        this.checkWidget();
        final int columnCount = this.table.getColumnCount();
        final int maxColumnIndex = (columnCount == 0) ? 0 : (columnCount - 1);
        if (row == null || row.isDisposed() || column < 0 || column > maxColumnIndex) {
            SWT.error(5);
        }
        this.setRowColumn(this.table.indexOf(row), column, false);
    }
    
    void unhookRowColumnListeners() {
        if (this.column != null) {
            this.column.removeListener(12, this.disposeColumnListener);
            this.column.removeListener(10, this.resizeListener);
            this.column.removeListener(11, this.resizeListener);
            this.column = null;
        }
        if (this.row != null) {
            this.row.removeListener(12, this.disposeItemListener);
            this.row = null;
        }
    }
}
