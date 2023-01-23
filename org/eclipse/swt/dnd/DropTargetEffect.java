//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class DropTargetEffect extends DropTargetAdapter
{
    Control control;
    
    public DropTargetEffect(final Control control) {
        if (control == null) {
            SWT.error(4);
        }
        this.control = control;
    }
    
    public Control getControl() {
        return this.control;
    }
    
    public Widget getItem(final int x, final int y) {
        if (this.control instanceof Table) {
            return this.getItem((Table)this.control, x, y);
        }
        if (this.control instanceof Tree) {
            return this.getItem((Tree)this.control, x, y);
        }
        return null;
    }
    
    Widget getItem(final Table table, final int x, final int y) {
        Point coordinates = new Point(x, y);
        coordinates = table.toControl(coordinates);
        TableItem item = table.getItem(coordinates);
        if (item != null) {
            return item;
        }
        final Rectangle area = table.getClientArea();
        final int tableBottom = area.y + area.height;
        for (int itemCount = table.getItemCount(), i = table.getTopIndex(); i < itemCount; ++i) {
            item = table.getItem(i);
            final Rectangle rect = item.getBounds();
            rect.x = area.x;
            rect.width = area.width;
            if (rect.contains(coordinates)) {
                return item;
            }
            if (rect.y > tableBottom) {
                break;
            }
        }
        return null;
    }
    
    Widget getItem(final Tree tree, final int x, final int y) {
        Point point = new Point(x, y);
        point = tree.toControl(point);
        TreeItem item = tree.getItem(point);
        if (item == null) {
            final Rectangle area = tree.getClientArea();
            if (area.contains(point)) {
                final int treeBottom = area.y + area.height;
                for (item = tree.getTopItem(); item != null; item = this.nextItem(tree, item)) {
                    final Rectangle rect = item.getBounds();
                    final int itemBottom = rect.y + rect.height;
                    if (rect.y <= point.y && point.y < itemBottom) {
                        return item;
                    }
                    if (itemBottom > treeBottom) {
                        break;
                    }
                }
                return null;
            }
        }
        return item;
    }
    
    TreeItem nextItem(final Tree tree, final TreeItem item) {
        if (item == null) {
            return null;
        }
        if (item.getExpanded() && item.getItemCount() > 0) {
            return item.getItem(0);
        }
        TreeItem childItem = item;
        TreeItem parentItem = childItem.getParentItem();
        int index = (parentItem == null) ? tree.indexOf(childItem) : parentItem.indexOf(childItem);
        for (int count = (parentItem == null) ? tree.getItemCount() : parentItem.getItemCount(); index + 1 >= count; index = ((parentItem == null) ? tree.indexOf(childItem) : parentItem.indexOf(childItem)), count = ((parentItem == null) ? tree.getItemCount() : parentItem.getItemCount())) {
            if (parentItem == null) {
                return null;
            }
            childItem = parentItem;
            parentItem = childItem.getParentItem();
        }
        return (parentItem == null) ? tree.getItem(index + 1) : parentItem.getItem(index + 1);
    }
    
    TreeItem previousItem(final Tree tree, final TreeItem item) {
        if (item == null) {
            return null;
        }
        final TreeItem childItem = item;
        final TreeItem parentItem = childItem.getParentItem();
        final int index = (parentItem == null) ? tree.indexOf(childItem) : parentItem.indexOf(childItem);
        if (index == 0) {
            return parentItem;
        }
        TreeItem nextItem = (parentItem == null) ? tree.getItem(index - 1) : parentItem.getItem(index - 1);
        for (int count = nextItem.getItemCount(); count > 0 && nextItem.getExpanded(); nextItem = nextItem.getItem(count - 1), count = nextItem.getItemCount()) {}
        return nextItem;
    }
}
