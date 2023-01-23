//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class TreeCursor extends Canvas
{
    Tree tree;
    TreeItem row;
    TreeColumn column;
    Listener listener;
    Listener treeListener;
    Listener resizeListener;
    Listener disposeItemListener;
    Listener disposeColumnListener;
    Color background;
    Color foreground;
    static final int BACKGROUND = 27;
    static final int FOREGROUND = 26;
    
    public TreeCursor(final Tree parent, final int style) {
        super(parent, style);
        this.background = null;
        this.foreground = null;
        this.tree = parent;
        this.setBackground(null);
        this.setForeground(null);
        int event2 = 0;
        TreeItem current;
        TreeItem parentItem;
        this.listener = (event -> {
            if (this.row != null) {
                if (this.row.isDisposed()) {
                    this.unhookRowColumnListeners();
                    this._resize();
                    this.tree.setFocus();
                    return;
                }
                else {
                    for (current = this.row, parentItem = this.row.getParentItem(); parentItem != null && !parentItem.getExpanded(); parentItem = current.getParentItem()) {
                        current = parentItem;
                    }
                    if (current != this.row) {
                        this.setRowColumn(current, this.column, false);
                    }
                }
            }
            Label_0232_1: {
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
                                break Label_0232_1;
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
        this.treeListener = (event -> {
            switch (event.type) {
                case 18: {
                    this.treeCollapse(event);
                    break;
                }
                case 17: {
                    this.treeExpand(event);
                    break;
                }
                case 15: {
                    this.treeFocusIn(event);
                    break;
                }
                case 3: {
                    this.treeMouseDown(event);
                    break;
                }
            }
            return;
        });
        this.tree.addListener(18, this.treeListener);
        this.tree.addListener(17, this.treeListener);
        this.tree.addListener(15, this.treeListener);
        this.tree.addListener(3, this.treeListener);
        TreeItem currentItem;
        final TreeItem disposedItem;
        final TreeItem parentItem2;
        TreeItem newFocus;
        int rowIndex;
        TreeItem previousItem;
        TreeItem nextItem;
        this.disposeItemListener = (event -> {
            for (currentItem = this.row; currentItem != null; currentItem = currentItem.getParentItem()) {
                currentItem.removeListener(12, this.disposeItemListener);
            }
            disposedItem = (TreeItem)event.widget;
            parentItem2 = disposedItem.getParentItem();
            if (parentItem2 != null) {
                this.setRowColumn(parentItem2, this.column, true);
            }
            else if (this.tree.getItemCount() == 1) {
                this.unhookRowColumnListeners();
            }
            else {
                newFocus = null;
                rowIndex = this.tree.indexOf(disposedItem);
                if (rowIndex != 0) {
                    previousItem = this.tree.getItem(rowIndex - 1);
                    if (!previousItem.isDisposed()) {
                        newFocus = previousItem;
                    }
                }
                if (newFocus == null && rowIndex + 1 < this.tree.getItemCount()) {
                    nextItem = this.tree.getItem(rowIndex + 1);
                    if (!nextItem.isDisposed()) {
                        newFocus = nextItem;
                    }
                }
                if (newFocus != null) {
                    this.setRowColumn(newFocus, this.column, true);
                }
                else {
                    this.unhookRowColumnListeners();
                }
            }
            this._resize();
            return;
        });
        int columnIndex;
        int positionIndex;
        int[] columnOrder;
        int j;
        this.disposeColumnListener = (event -> {
            if (this.column != null) {
                if (this.tree.getColumnCount() == 1) {
                    this.column = null;
                }
                else {
                    positionIndex = (columnIndex = this.tree.indexOf(this.column));
                    columnOrder = this.tree.getColumnOrder();
                    j = 0;
                    while (j < columnOrder.length) {
                        if (columnOrder[j] == columnIndex) {
                            positionIndex = j;
                            break;
                        }
                        else {
                            ++j;
                        }
                    }
                    if (positionIndex == columnOrder.length - 1) {
                        this.setRowColumn(this.row, this.tree.getColumn(columnOrder[positionIndex - 1]), true);
                    }
                    else {
                        this.setRowColumn(this.row, this.tree.getColumn(columnOrder[positionIndex + 1]), true);
                    }
                }
            }
            this._resize();
            return;
        });
        this.resizeListener = (event -> this._resize());
        final ScrollBar hBar = this.tree.getHorizontalBar();
        if (hBar != null) {
            hBar.addListener(13, this.resizeListener);
        }
        final ScrollBar vBar = this.tree.getVerticalBar();
        if (vBar != null) {
            vBar.addListener(13, this.resizeListener);
        }
        this.getAccessible().addAccessibleControlListener((AccessibleControlListener)new lIIIlII(this));
        this.getAccessible().addAccessibleListener((AccessibleListener)new lIlIII(this));
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
    
    int countSubTreePages(final TreeItem root) {
        int pages = 1;
        if (root == null) {
            return 0;
        }
        if (root.getItemCount() == 0) {
            return 1;
        }
        if (!root.getExpanded()) {
            return 1;
        }
        for (final TreeItem item : root.getItems()) {
            pages += this.countSubTreePages(item);
        }
        return pages;
    }
    
    int findIndex(final TreeItem[] items, final TreeItem treeItem) {
        if (items == null || treeItem == null) {
            return -1;
        }
        final Rectangle rect = treeItem.getBounds();
        int index = 0;
        for (int i = 0; i < items.length; ++i) {
            TreeItem previousItem = null;
            final TreeItem currentItem = items[i];
            if (i > 0) {
                previousItem = items[i - 1];
            }
            final Rectangle rect2 = currentItem.getBounds();
            if (rect.y == rect2.y) {
                return index;
            }
            if (rect.y < rect2.y) {
                return index - 1 + this.findIndex(previousItem.getItems(), treeItem);
            }
            if (rect.y > rect2.y && i == items.length - 1) {
                return index + this.findIndex(currentItem.getItems(), treeItem);
            }
            if (rect.y >= rect2.y + (1 + currentItem.getItemCount()) * this.tree.getItemHeight() && currentItem.getExpanded()) {
                index += this.countSubTreePages(currentItem);
            }
            else {
                ++index;
            }
        }
        return -1;
    }
    
    TreeItem findItem(final TreeItem[] items, final Point pt) {
        int start = 0;
        int end = items.length - 1;
        int index = end / 2;
        while (end - start > 1) {
            final TreeItem currentItem = items[index];
            final Rectangle bounds = currentItem.getBounds();
            if (pt.y < bounds.y) {
                end = index;
                index = (end - start) / 2;
            }
            else {
                start = index;
                index = start + (end - start) / 2;
            }
        }
        final Rectangle endBounds = items[end].getBounds();
        if (endBounds.y < pt.y) {
            if (endBounds.y + endBounds.height >= pt.y) {
                final int[] columnOrder = this.tree.getColumnOrder();
                Rectangle bounds2 = null;
                if (columnOrder.length > 0) {
                    final Rectangle rect1 = items[end].getBounds(columnOrder[0]);
                    final Rectangle rect2 = items[end].getBounds(columnOrder[columnOrder.length - 1]);
                    final Rectangle rectangle;
                    final Rectangle union = rectangle = (bounds2 = rect1.union(rect2));
                    rectangle.height += (this.tree.getLinesVisible() ? this.tree.getGridLineWidth() : 0);
                }
                else {
                    bounds2 = items[end].getBounds();
                }
                return bounds2.contains(pt) ? items[end] : null;
            }
            if (!items[end].getExpanded()) {
                return null;
            }
            return this.findItem(items[end].getItems(), pt);
        }
        else {
            final Rectangle startBounds = items[start].getBounds();
            if (startBounds.y + startBounds.height < pt.y) {
                return this.findItem(items[start].getItems(), pt);
            }
            final int[] columnOrder2 = this.tree.getColumnOrder();
            Rectangle bounds3 = null;
            if (columnOrder2.length > 0) {
                final Rectangle rect3 = items[start].getBounds(columnOrder2[0]);
                final Rectangle rect4 = items[start].getBounds(columnOrder2[columnOrder2.length - 1]);
                final Rectangle rectangle2;
                final Rectangle union2 = rectangle2 = (bounds3 = rect3.union(rect4));
                rectangle2.height += (this.tree.getLinesVisible() ? this.tree.getGridLineWidth() : 0);
            }
            else {
                bounds3 = items[start].getBounds();
            }
            return bounds3.contains(pt) ? items[start] : null;
        }
    }
    
    @Override
    public Color getBackground() {
        this.checkWidget();
        if (this.background == null) {
            return this.getDisplay().getSystemColor(27);
        }
        return this.background;
    }
    
    public int getColumn() {
        this.checkWidget();
        return (this.column == null) ? 0 : this.tree.indexOf(this.column);
    }
    
    @Override
    public Color getForeground() {
        this.checkWidget();
        if (this.foreground == null) {
            return this.getDisplay().getSystemColor(26);
        }
        return this.foreground;
    }
    
    TreeItem getLastVisibleItem(final TreeItem[] items) {
        if (items == null) {
            return null;
        }
        final TreeItem last = items[items.length - 1];
        if (last.getExpanded() && last.getItemCount() > 0) {
            return this.getLastVisibleItem(last.getItems());
        }
        return last;
    }
    
    TreeItem getNextItem(TreeItem item) {
        if (item == null) {
            return null;
        }
        if (item.getExpanded() && item.getItemCount() > 0) {
            return item.getItem(0);
        }
        for (TreeItem parentItem = item.getParentItem(); parentItem != null; parentItem = item.getParentItem()) {
            final int index = parentItem.indexOf(item);
            if (index == -1) {
                return null;
            }
            if (index < parentItem.getItemCount() - 1) {
                return parentItem.getItem(index + 1);
            }
            item = parentItem;
        }
        final int index2 = this.tree.indexOf(item);
        if (index2 == -1) {
            return null;
        }
        if (index2 == this.tree.getItemCount() - 1) {
            return null;
        }
        return this.tree.getItem(index2 + 1);
    }
    
    TreeItem getPreviousItem(TreeItem item) {
        if (item == null) {
            return null;
        }
        final TreeItem parentItem = item.getParentItem();
        if (parentItem == null) {
            final int index = this.tree.indexOf(item);
            if (index == -1 || index == 0) {
                return null;
            }
            item = this.tree.getItem(index - 1);
            if (item.getExpanded() && item.getItemCount() > 0) {
                return this.getLastVisibleItem(item.getItems());
            }
            return item;
        }
        else {
            final int index = parentItem.indexOf(item);
            if (index == -1) {
                return null;
            }
            if (index == 0) {
                return parentItem;
            }
            item = parentItem.getItem(index - 1);
            if (item.getExpanded() && item.getItemCount() > 0) {
                return this.getLastVisibleItem(item.getItems());
            }
            return item;
        }
    }
    
    public TreeItem getRow() {
        this.checkWidget();
        return this.row;
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
                switch (event.keyCode) {
                    case 16777217: {
                        final TreeItem previousItem = this.getPreviousItem(this.row);
                        if (previousItem != null) {
                            this.setRowColumn(previousItem, this.column, true);
                            break;
                        }
                        break;
                    }
                    case 16777218: {
                        final TreeItem nextItem = this.getNextItem(this.row);
                        if (nextItem != null) {
                            this.setRowColumn(nextItem, this.column, true);
                            break;
                        }
                        break;
                    }
                    case 16777219:
                    case 16777220: {
                        if ((event.stateMask & SWT.MOD1) != 0x0) {
                            this.row.setExpanded(event.keyCode == 16777220);
                            break;
                        }
                        final int columnCount = this.tree.getColumnCount();
                        if (columnCount == 0) {
                            break;
                        }
                        int columnIndex;
                        int[] columnOrder;
                        int index;
                        for (columnIndex = ((this.column == null) ? 0 : this.tree.indexOf(this.column)), columnOrder = this.tree.getColumnOrder(), index = 0; index < columnOrder.length && columnOrder[index] != columnIndex; ++index) {}
                        if (index == columnOrder.length) {
                            index = 0;
                        }
                        final int leadKey = ((this.getStyle() & 0x4000000) != 0x0) ? 16777220 : 16777219;
                        final TreeItem parentRow = this.row.getParentItem();
                        final int rowIndex = this.tree.indexOf(this.row);
                        if (event.keyCode == leadKey) {
                            if (parentRow != null) {
                                this.setRowColumn(this.row, this.tree.getColumn(columnOrder[Math.max(0, index - 1)]), true);
                                break;
                            }
                            this.setRowColumn(rowIndex, columnOrder[Math.max(0, index - 1)], true);
                            break;
                        }
                        else {
                            if (parentRow != null) {
                                this.setRowColumn(this.row, this.tree.getColumn(columnOrder[Math.min(columnCount - 1, index + 1)]), true);
                                break;
                            }
                            this.setRowColumn(rowIndex, columnOrder[Math.min(columnCount - 1, index + 1)], true);
                            break;
                        }
                        break;
                    }
                    case 16777223: {
                        final int columnIndex2 = (this.column == null) ? 0 : this.tree.indexOf(this.column);
                        this.setRowColumn(0, columnIndex2, true);
                        break;
                    }
                    case 16777224: {
                        final TreeItem[] items = this.tree.getItems();
                        this.setRowColumn(this.getLastVisibleItem(items), this.column, true);
                        break;
                    }
                    case 16777221: {
                        final Rectangle rect = this.tree.getClientArea();
                        final Rectangle itemRect = this.tree.getTopItem().getBounds();
                        TreeItem item = this.row;
                        final int index2 = this.findIndex(this.tree.getItems(), item);
                        final int itemHeight = this.tree.getItemHeight();
                        final Rectangle rectangle3;
                        final Rectangle rectangle = rectangle3 = rect;
                        rectangle3.height -= itemRect.y;
                        final int page = Math.max(1, rect.height / itemHeight);
                        if (index2 - page <= 0) {
                            final TreeItem first = this.tree.getItem(0);
                            this.setRowColumn(first, this.column, true);
                            break;
                        }
                        for (int i = 0; i < page; ++i) {
                            item = this.getPreviousItem(item);
                        }
                        this.setRowColumn(item, this.column, true);
                        break;
                    }
                    case 16777222: {
                        final Rectangle rect = this.tree.getClientArea();
                        final Rectangle itemRect = this.tree.getTopItem().getBounds();
                        TreeItem item = this.row;
                        final int index2 = this.findIndex(this.tree.getItems(), item);
                        final int height = this.tree.getItemHeight();
                        final Rectangle rectangle4;
                        final Rectangle rectangle2 = rectangle4 = rect;
                        rectangle4.height -= itemRect.y;
                        final TreeItem last = this.getLastVisibleItem(this.tree.getItems());
                        final int page2 = Math.max(1, rect.height / height);
                        final int end = this.findIndex(this.tree.getItems(), last);
                        if (end <= index2 + page2) {
                            this.setRowColumn(last, this.column, true);
                            break;
                        }
                        for (int j = 0; j < page2; ++j) {
                            item = this.getNextItem(item);
                        }
                        this.setRowColumn(item, this.column, true);
                        break;
                    }
                }
            }
        }
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.tree.removeListener(18, this.treeListener);
        this.tree.removeListener(17, this.treeListener);
        this.tree.removeListener(15, this.treeListener);
        this.tree.removeListener(3, this.treeListener);
        this.unhookRowColumnListeners();
        final ScrollBar hBar = this.tree.getHorizontalBar();
        if (hBar != null) {
            hBar.removeListener(13, this.resizeListener);
        }
        final ScrollBar vBar = this.tree.getVerticalBar();
        if (vBar != null) {
            vBar.removeListener(13, this.resizeListener);
        }
    }
    
    void paint(final Event event) {
        if (this.row == null) {
            return;
        }
        final int columnIndex;
        int orderedIndex = columnIndex = ((this.column == null) ? 0 : this.tree.indexOf(this.column));
        final int[] columnOrder = this.tree.getColumnOrder();
        for (int i = 0; i < columnOrder.length; ++i) {
            if (columnOrder[i] == columnIndex) {
                orderedIndex = i;
                break;
            }
        }
        final GC gc = event.gc;
        gc.setBackground(this.getBackground());
        gc.setForeground(this.getForeground());
        gc.fillRectangle(event.x, event.y, event.width, event.height);
        final Image image = this.row.getImage(columnIndex);
        int x = 0;
        final String platform = SWT.getPlatform();
        if (image != null) {
            if ("win32".equals(platform)) {
                if (orderedIndex > 0) {
                    x += 2;
                }
            }
            else {
                x += 2;
            }
        }
        final Point size = this.getSize();
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
            if ("win32".equals(platform)) {
                if (this.tree.getColumnCount() == 0 || orderedIndex == 0) {
                    x += ((image == null) ? 2 : 5);
                }
                else {
                    final int alignmnent = this.column.getAlignment();
                    switch (alignmnent) {
                        case 16384: {
                            x += ((image == null) ? 5 : 3);
                            break;
                        }
                        case 131072: {
                            x = bounds.width - extent.x - 2;
                            break;
                        }
                        case 16777216: {
                            x += (int)Math.ceil((bounds.width - x - extent.x) / 2.0);
                            break;
                        }
                    }
                }
            }
            else if (this.tree.getColumnCount() == 0) {
                x += ((image == null) ? 4 : 3);
            }
            else {
                final int alignmnent = this.column.getAlignment();
                switch (alignmnent) {
                    case 16384: {
                        x += ((image == null) ? 5 : 3);
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
            final int columnIndex = (this.column == null) ? 0 : this.tree.indexOf(this.column);
            this.setBounds(this.row.getBounds(columnIndex));
        }
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
    
    void setRowColumn(final int row, final int column, final boolean notify) {
        final TreeItem item = (row == -1) ? null : this.tree.getItem(row);
        final TreeColumn col = (column == -1 || this.tree.getColumnCount() == 0) ? null : this.tree.getColumn(column);
        this.setRowColumn(item, col, notify);
    }
    
    void setRowColumn(final TreeItem row, final TreeColumn column, final boolean notify) {
        if (this.row != null && this.row != row) {
            for (TreeItem currentItem = this.row; currentItem != null; currentItem = currentItem.getParentItem()) {
                currentItem.removeListener(12, this.disposeItemListener);
            }
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
                this.row = row;
                for (TreeItem currentItem = row; currentItem != null; currentItem = currentItem.getParentItem()) {
                    currentItem.addListener(12, this.disposeItemListener);
                }
                this.tree.showItem(row);
            }
            if (this.column != column && column != null) {
                (this.column = column).addListener(12, this.disposeColumnListener);
                column.addListener(10, this.resizeListener);
                column.addListener(11, this.resizeListener);
                this.tree.showColumn(column);
            }
            final int columnIndex = (column == null) ? 0 : this.tree.indexOf(column);
            this.setBounds(row.getBounds(columnIndex));
            this.redraw();
            if (notify) {
                this.notifyListeners(13, new Event());
            }
        }
    }
    
    public void setSelection(final int row, final int column) {
        this.checkWidget();
        final int columnCount = this.tree.getColumnCount();
        final int maxColumnIndex = (columnCount == 0) ? 0 : (columnCount - 1);
        if (row < 0 || row >= this.tree.getItemCount() || column < 0 || column > maxColumnIndex) {
            SWT.error(5);
        }
        this.setRowColumn(row, column, false);
    }
    
    public void setSelection(final TreeItem row, final int column) {
        this.checkWidget();
        final int columnCount = this.tree.getColumnCount();
        final int maxColumnIndex = (columnCount == 0) ? 0 : (columnCount - 1);
        if (row == null || row.isDisposed() || column < 0 || column > maxColumnIndex) {
            SWT.error(5);
        }
        final TreeColumn col = (this.tree.getColumnCount() == 0) ? null : this.tree.getColumn(column);
        this.setRowColumn(row, col, false);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (visible) {
            this._resize();
        }
        super.setVisible(visible);
    }
    
    void treeCollapse(final Event event) {
        if (this.row == null) {
            return;
        }
        final TreeItem root = (TreeItem)event.item;
        for (TreeItem parentItem = this.row.getParentItem(); parentItem != null; parentItem = parentItem.getParentItem()) {
            if (parentItem == root) {
                this.setRowColumn(root, this.column, true);
                return;
            }
        }
        this.getDisplay().asyncExec(() -> {
            if (!this.isDisposed()) {
                this.setRowColumn(this.row, this.column, true);
            }
        });
    }
    
    void treeExpand(final Event event) {
        this.getDisplay().asyncExec(() -> {
            if (!this.isDisposed()) {
                this.setRowColumn(this.row, this.column, true);
            }
        });
    }
    
    void treeFocusIn(final Event event) {
        if (this.isVisible()) {
            if (this.row == null && this.column == null) {
                return;
            }
            this.setFocus();
        }
    }
    
    void treeMouseDown(final Event event) {
        if (this.tree.getItemCount() == 0) {
            return;
        }
        final Point pt = new Point(event.x, event.y);
        TreeItem item = this.tree.getItem(pt);
        if (item == null && (this.tree.getStyle() & 0x10000) == 0x0) {
            TreeItem currentItem = this.tree.getTopItem();
            for (TreeItem parentItem = currentItem.getParentItem(); parentItem != null; parentItem = currentItem.getParentItem()) {
                currentItem = parentItem;
            }
            final int start = this.tree.indexOf(currentItem);
            final int viewportItemCount = this.tree.getClientArea().height / this.tree.getItemHeight();
            final int end = Math.min(start + viewportItemCount, this.tree.getItemCount() - 1);
            final TreeItem[] allItems = this.tree.getItems();
            final TreeItem[] items = new TreeItem[end - start + 1];
            System.arraycopy(allItems, start, items, 0, end - start + 1);
            item = this.findItem(items, pt);
        }
        if (item == null) {
            return;
        }
        TreeColumn newColumn = null;
        final int lineWidth = this.tree.getLinesVisible() ? this.tree.getGridLineWidth() : 0;
        final int columnCount = this.tree.getColumnCount();
        if (columnCount > 0) {
            for (int i = 0; i < columnCount; ++i) {
                final Rectangle rect;
                final Rectangle rectangle2;
                final Rectangle bounds = rectangle2 = (rect = item.getBounds(i));
                rectangle2.width += lineWidth;
                final Rectangle rectangle3;
                final Rectangle rectangle = rectangle3 = rect;
                rectangle3.height += lineWidth;
                if (rect.contains(pt)) {
                    newColumn = this.tree.getColumn(i);
                    break;
                }
            }
            if (newColumn == null) {
                newColumn = this.tree.getColumn(0);
            }
        }
        this.setRowColumn(item, newColumn, true);
        this.setFocus();
    }
    
    void unhookRowColumnListeners() {
        if (this.column != null && !this.column.isDisposed()) {
            this.column.removeListener(12, this.disposeColumnListener);
            this.column.removeListener(10, this.resizeListener);
            this.column.removeListener(11, this.resizeListener);
        }
        this.column = null;
        if (this.row != null && !this.row.isDisposed()) {
            for (TreeItem currentItem = this.row; currentItem != null; currentItem = currentItem.getParentItem()) {
                currentItem.removeListener(12, this.disposeItemListener);
            }
        }
        this.row = null;
    }
}
