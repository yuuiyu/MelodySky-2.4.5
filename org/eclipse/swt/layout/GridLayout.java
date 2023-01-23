//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public final class GridLayout extends Layout
{
    public int numColumns;
    public boolean makeColumnsEqualWidth;
    public int marginWidth;
    public int marginHeight;
    public int marginLeft;
    public int marginTop;
    public int marginRight;
    public int marginBottom;
    public int horizontalSpacing;
    public int verticalSpacing;
    
    public GridLayout() {
        this.numColumns = 1;
        this.makeColumnsEqualWidth = false;
        this.marginWidth = 5;
        this.marginHeight = 5;
        this.marginLeft = 0;
        this.marginTop = 0;
        this.marginRight = 0;
        this.marginBottom = 0;
        this.horizontalSpacing = 5;
        this.verticalSpacing = 5;
    }
    
    public GridLayout(final int numColumns, final boolean makeColumnsEqualWidth) {
        this.numColumns = 1;
        this.makeColumnsEqualWidth = false;
        this.marginWidth = 5;
        this.marginHeight = 5;
        this.marginLeft = 0;
        this.marginTop = 0;
        this.marginRight = 0;
        this.marginBottom = 0;
        this.horizontalSpacing = 5;
        this.verticalSpacing = 5;
        this.numColumns = numColumns;
        this.makeColumnsEqualWidth = makeColumnsEqualWidth;
    }
    
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final Point size = this.layout(composite, false, 0, 0, wHint, hHint, flushCache);
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
        final Object data = control.getLayoutData();
        if (data != null) {
            ((GridData)data).flushCache();
        }
        return true;
    }
    
    GridData getData(final Control[][] grid, final int row, final int column, final int rowCount, final int columnCount, final boolean first) {
        final Control control = grid[row][column];
        if (control != null) {
            final GridData data = (GridData)control.getLayoutData();
            final int hSpan = Math.max(1, Math.min(data.horizontalSpan, columnCount));
            final int vSpan = Math.max(1, data.verticalSpan);
            final int i = first ? (row + vSpan - 1) : (row - vSpan + 1);
            final int j = first ? (column + hSpan - 1) : (column - hSpan + 1);
            if (0 <= i && i < rowCount && 0 <= j && j < columnCount && control == grid[i][j]) {
                return data;
            }
        }
        return null;
    }
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        final Rectangle rect = composite.getClientArea();
        this.layout(composite, true, rect.x, rect.y, rect.width, rect.height, flushCache);
    }
    
    Point layout(final Composite composite, final boolean move, final int x, final int y, final int width, final int height, final boolean flushCache) {
        if (this.numColumns < 1) {
            return new Point(this.marginLeft + this.marginWidth * 2 + this.marginRight, this.marginTop + this.marginHeight * 2 + this.marginBottom);
        }
        final Control[] children = composite.getChildren();
        int count = 0;
        for (int i4 = 0; i4 < children.length; ++i4) {
            final Control control = children[i4];
            final GridData data = (GridData)control.getLayoutData();
            if (data == null || !data.exclude) {
                children[count++] = children[i4];
            }
        }
        if (count == 0) {
            return new Point(this.marginLeft + this.marginWidth * 2 + this.marginRight, this.marginTop + this.marginHeight * 2 + this.marginBottom);
        }
        for (final Control child : children) {
            GridData data = (GridData)child.getLayoutData();
            if (data == null) {
                data = new GridData();
                child.setLayoutData(data);
            }
            if (flushCache) {
                data.flushCache();
            }
            data.computeSize(child, data.widthHint, data.heightHint, flushCache);
            if (data.grabExcessHorizontalSpace && data.minimumWidth > 0 && data.cacheWidth < data.minimumWidth) {
                int trim = 0;
                if (child instanceof Scrollable) {
                    final Rectangle rect = ((Scrollable)child).computeTrim(0, 0, 0, 0);
                    trim = rect.width;
                }
                else {
                    trim = child.getBorderWidth() * 2;
                }
                data.cacheHeight = -1;
                data.cacheWidth = -1;
                data.computeSize(child, Math.max(0, data.minimumWidth - trim), data.heightHint, false);
            }
            if (data.grabExcessVerticalSpace) {
                if (data.minimumHeight > 0) {
                    data.cacheHeight = Math.max(data.cacheHeight, data.minimumHeight);
                }
            }
        }
        int row = 0;
        int column = 0;
        int rowCount = 0;
        final int columnCount = this.numColumns;
        Control[][] grid = new Control[4][columnCount];
        for (final Control child2 : children) {
            final GridData data2 = (GridData)child2.getLayoutData();
            final int hSpan = Math.max(1, Math.min(data2.horizontalSpan, columnCount));
            final int vSpan = Math.max(1, data2.verticalSpan);
            while (true) {
                final int lastRow;
                if ((lastRow = row + vSpan) >= grid.length) {
                    final Control[][] newGrid = new Control[lastRow + 4][columnCount];
                    System.arraycopy(grid, 0, newGrid, 0, grid.length);
                    grid = newGrid;
                }
                if (grid[row] == null) {
                    grid[row] = new Control[columnCount];
                }
                while (column < columnCount && grid[row][column] != null) {
                    ++column;
                }
                final int endCount = column + hSpan;
                if (endCount <= columnCount) {
                    int index;
                    for (index = column; index < endCount && grid[row][index] == null; ++index) {}
                    if (index == endCount) {
                        break;
                    }
                    column = index;
                }
                if (column + hSpan < columnCount) {
                    continue;
                }
                column = 0;
                ++row;
            }
            for (int j = 0; j < vSpan; ++j) {
                if (grid[row + j] == null) {
                    grid[row + j] = new Control[columnCount];
                }
                for (int k4 = 0; k4 < hSpan; ++k4) {
                    grid[row + j][column + k4] = child2;
                }
            }
            rowCount = Math.max(rowCount, row + vSpan);
            column += hSpan;
        }
        final int availableWidth = width - this.horizontalSpacing * (columnCount - 1) - (this.marginLeft + this.marginWidth * 2 + this.marginRight);
        int expandCount = 0;
        final int[] widths = new int[columnCount];
        final int[] minWidths = new int[columnCount];
        final boolean[] expandColumn = new boolean[columnCount];
        for (int j = 0; j < columnCount; ++j) {
            for (int i6 = 0; i6 < rowCount; ++i6) {
                final GridData data3 = this.getData(grid, i6, j, rowCount, columnCount, true);
                if (data3 != null) {
                    final int hSpan2;
                    if ((hSpan2 = Math.max(1, Math.min(data3.horizontalSpan, columnCount))) == 1) {
                        int w = data3.cacheWidth + data3.horizontalIndent;
                        widths[j] = Math.max(widths[j], w);
                        if (data3.grabExcessHorizontalSpace) {
                            if (!expandColumn[j]) {
                                ++expandCount;
                            }
                            expandColumn[j] = true;
                        }
                        if (!data3.grabExcessHorizontalSpace || data3.minimumWidth != 0) {
                            w = ((!data3.grabExcessHorizontalSpace || data3.minimumWidth == -1) ? data3.cacheWidth : data3.minimumWidth);
                            minWidths[j] = Math.max(minWidths[j], w += data3.horizontalIndent);
                        }
                    }
                }
            }
            for (int i6 = 0; i6 < rowCount; ++i6) {
                final GridData data3 = this.getData(grid, i6, j, rowCount, columnCount, false);
                if (data3 != null) {
                    final int hSpan2;
                    if ((hSpan2 = Math.max(1, Math.min(data3.horizontalSpan, columnCount))) > 1) {
                        int spanWidth = 0;
                        int spanMinWidth = 0;
                        int spanExpandCount = 0;
                        for (int k5 = 0; k5 < hSpan2; ++k5) {
                            spanWidth += widths[j - k5];
                            spanMinWidth += minWidths[j - k5];
                            if (expandColumn[j - k5]) {
                                ++spanExpandCount;
                            }
                        }
                        if (data3.grabExcessHorizontalSpace && spanExpandCount == 0) {
                            ++expandCount;
                            expandColumn[j] = true;
                        }
                        int w2;
                        if ((w2 = data3.cacheWidth + data3.horizontalIndent - spanWidth - (hSpan2 - 1) * this.horizontalSpacing) > 0) {
                            if (this.makeColumnsEqualWidth) {
                                final int equalWidth = (w2 + spanWidth) / hSpan2;
                                final int remainder = (w2 + spanWidth) % hSpan2;
                                int last = -1;
                                for (int k6 = 0; k6 < hSpan2; ++k6) {
                                    last = j - k6;
                                    widths[last] = Math.max(equalWidth, widths[j - k6]);
                                }
                                if (last > -1) {
                                    final int n = last;
                                    widths[n] += remainder;
                                }
                            }
                            else if (spanExpandCount == 0) {
                                final int n2 = j;
                                widths[n2] += w2;
                            }
                            else {
                                final int delta = w2 / spanExpandCount;
                                final int remainder = w2 % spanExpandCount;
                                int last = -1;
                                for (int k6 = 0; k6 < hSpan2; ++k6) {
                                    if (expandColumn[j - k6]) {
                                        last = j - k6;
                                        widths[last] += delta;
                                    }
                                }
                                if (last > -1) {
                                    final int n2 = last;
                                    widths[n2] += remainder;
                                }
                            }
                        }
                        if (!data3.grabExcessHorizontalSpace || data3.minimumWidth != 0) {
                            w2 = ((!data3.grabExcessHorizontalSpace || data3.minimumWidth == -1) ? data3.cacheWidth : data3.minimumWidth);
                            if ((w2 += data3.horizontalIndent - spanMinWidth - (hSpan2 - 1) * this.horizontalSpacing) > 0) {
                                if (spanExpandCount == 0) {
                                    final int n3 = j;
                                    minWidths[n3] += w2;
                                }
                                else {
                                    final int delta = w2 / spanExpandCount;
                                    final int remainder = w2 % spanExpandCount;
                                    int last = -1;
                                    for (int k6 = 0; k6 < hSpan2; ++k6) {
                                        if (expandColumn[j - k6]) {
                                            last = j - k6;
                                            minWidths[last] += delta;
                                        }
                                    }
                                    if (last > -1) {
                                        final int n4 = last;
                                        minWidths[n4] += remainder;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.makeColumnsEqualWidth) {
            int minColumnWidth = 0;
            int columnWidth = 0;
            for (int i7 = 0; i7 < columnCount; ++i7) {
                minColumnWidth = Math.max(minColumnWidth, minWidths[i7]);
                columnWidth = Math.max(columnWidth, widths[i7]);
            }
            columnWidth = ((width == -1 || expandCount == 0) ? columnWidth : Math.max(minColumnWidth, availableWidth / columnCount));
            for (int i7 = 0; i7 < columnCount; ++i7) {
                expandColumn[i7] = (expandCount > 0);
                widths[i7] = columnWidth;
            }
        }
        else if (width != -1 && expandCount > 0) {
            int totalWidth = 0;
            for (int i6 = 0; i6 < columnCount; ++i6) {
                totalWidth += widths[i6];
            }
            int c = expandCount;
            int delta = (availableWidth - totalWidth) / c;
            int remainder = (availableWidth - totalWidth) % c;
            int last2 = -1;
            while (totalWidth != availableWidth) {
                for (int j2 = 0; j2 < columnCount; ++j2) {
                    if (expandColumn[j2]) {
                        if (widths[j2] + delta > minWidths[j2]) {
                            last2 = j2;
                            widths[last2] = widths[j2] + delta;
                        }
                        else {
                            widths[j2] = minWidths[j2];
                            expandColumn[j2] = false;
                            --c;
                        }
                    }
                }
                if (last2 > -1) {
                    final int n5 = last2;
                    widths[n5] += remainder;
                }
                for (int j2 = 0; j2 < columnCount; ++j2) {
                    for (int i8 = 0; i8 < rowCount; ++i8) {
                        final GridData data4 = this.getData(grid, i8, j2, rowCount, columnCount, false);
                        final int hSpan3;
                        if (data4 != null && (hSpan3 = Math.max(1, Math.min(data4.horizontalSpan, columnCount))) > 1) {
                            if (!data4.grabExcessHorizontalSpace || data4.minimumWidth != 0) {
                                int spanWidth2 = 0;
                                int spanExpandCount2 = 0;
                                for (int k6 = 0; k6 < hSpan3; ++k6) {
                                    spanWidth2 += widths[j2 - k6];
                                    if (expandColumn[j2 - k6]) {
                                        ++spanExpandCount2;
                                    }
                                }
                                int w3 = (!data4.grabExcessHorizontalSpace || data4.minimumWidth == -1) ? data4.cacheWidth : data4.minimumWidth;
                                if ((w3 += data4.horizontalIndent - spanWidth2 - (hSpan3 - 1) * this.horizontalSpacing) > 0) {
                                    if (spanExpandCount2 == 0) {
                                        final int n6 = j2;
                                        widths[n6] += w3;
                                    }
                                    else {
                                        final int delta2 = w3 / spanExpandCount2;
                                        final int remainder2 = w3 % spanExpandCount2;
                                        int last3 = -1;
                                        for (int k7 = 0; k7 < hSpan3; ++k7) {
                                            if (expandColumn[j2 - k7]) {
                                                last3 = j2 - k7;
                                                widths[last3] += delta2;
                                            }
                                        }
                                        if (last3 > -1) {
                                            final int n7 = last3;
                                            widths[n7] += remainder2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (c == 0) {
                    break;
                }
                totalWidth = 0;
                for (int i9 = 0; i9 < columnCount; ++i9) {
                    totalWidth += widths[i9];
                }
                delta = (availableWidth - totalWidth) / c;
                remainder = (availableWidth - totalWidth) % c;
                last2 = -1;
            }
        }
        GridData[] flush = null;
        int flushLength = 0;
        if (width != -1) {
            for (int j3 = 0; j3 < columnCount; ++j3) {
                for (int i10 = 0; i10 < rowCount; ++i10) {
                    final GridData data5 = this.getData(grid, i10, j3, rowCount, columnCount, false);
                    if (data5 != null) {
                        if (data5.heightHint == -1) {
                            final Control child3 = grid[i10][j3];
                            final int hSpan4 = Math.max(1, Math.min(data5.horizontalSpan, columnCount));
                            int currentWidth = 0;
                            for (int k8 = 0; k8 < hSpan4; ++k8) {
                                currentWidth += widths[j3 - k8];
                            }
                            if (((currentWidth += (hSpan4 - 1) * this.horizontalSpacing - data5.horizontalIndent) != data5.cacheWidth && data5.horizontalAlignment == 4) || data5.cacheWidth > currentWidth) {
                                int trim2 = 0;
                                if (child3 instanceof Scrollable) {
                                    final Rectangle rect2 = ((Scrollable)child3).computeTrim(0, 0, 0, 0);
                                    trim2 = rect2.width;
                                }
                                else {
                                    trim2 = child3.getBorderWidth() * 2;
                                }
                                data5.cacheHeight = -1;
                                data5.cacheWidth = -1;
                                data5.computeSize(child3, Math.max(0, currentWidth - trim2), data5.heightHint, false);
                                if (data5.grabExcessVerticalSpace && data5.minimumHeight > 0) {
                                    data5.cacheHeight = Math.max(data5.cacheHeight, data5.minimumHeight);
                                }
                                if (flush == null) {
                                    flush = new GridData[count];
                                }
                                flush[flushLength++] = data5;
                            }
                        }
                    }
                }
            }
        }
        final int availableHeight = height - this.verticalSpacing * (rowCount - 1) - (this.marginTop + this.marginHeight * 2 + this.marginBottom);
        expandCount = 0;
        final int[] heights = new int[rowCount];
        final int[] minHeights = new int[rowCount];
        final boolean[] expandRow = new boolean[rowCount];
        for (int i8 = 0; i8 < rowCount; ++i8) {
            for (int j4 = 0; j4 < columnCount; ++j4) {
                final GridData data6 = this.getData(grid, i8, j4, rowCount, columnCount, true);
                if (data6 != null) {
                    final int vSpan2;
                    if ((vSpan2 = Math.max(1, Math.min(data6.verticalSpan, rowCount))) == 1) {
                        int h = data6.cacheHeight + data6.verticalIndent;
                        heights[i8] = Math.max(heights[i8], h);
                        if (data6.grabExcessVerticalSpace) {
                            if (!expandRow[i8]) {
                                ++expandCount;
                            }
                            expandRow[i8] = true;
                        }
                        if (!data6.grabExcessVerticalSpace || data6.minimumHeight != 0) {
                            h = ((!data6.grabExcessVerticalSpace || data6.minimumHeight == -1) ? data6.cacheHeight : data6.minimumHeight);
                            minHeights[i8] = Math.max(minHeights[i8], h += data6.verticalIndent);
                        }
                    }
                }
            }
            for (int j4 = 0; j4 < columnCount; ++j4) {
                final GridData data6 = this.getData(grid, i8, j4, rowCount, columnCount, false);
                if (data6 != null) {
                    final int vSpan2;
                    if ((vSpan2 = Math.max(1, Math.min(data6.verticalSpan, rowCount))) > 1) {
                        int spanHeight = 0;
                        int spanMinHeight = 0;
                        int spanExpandCount3 = 0;
                        for (int k9 = 0; k9 < vSpan2; ++k9) {
                            spanHeight += heights[i8 - k9];
                            spanMinHeight += minHeights[i8 - k9];
                            if (expandRow[i8 - k9]) {
                                ++spanExpandCount3;
                            }
                        }
                        if (data6.grabExcessVerticalSpace && spanExpandCount3 == 0) {
                            ++expandCount;
                            expandRow[i8] = true;
                        }
                        int h2;
                        if ((h2 = data6.cacheHeight + data6.verticalIndent - spanHeight - (vSpan2 - 1) * this.verticalSpacing) > 0) {
                            if (spanExpandCount3 == 0) {
                                final int n8 = i8;
                                heights[n8] += h2;
                            }
                            else {
                                final int delta3 = h2 / spanExpandCount3;
                                final int remainder3 = h2 % spanExpandCount3;
                                int last4 = -1;
                                for (int l = 0; l < vSpan2; ++l) {
                                    if (expandRow[i8 - l]) {
                                        last4 = i8 - l;
                                        heights[last4] += delta3;
                                    }
                                }
                                if (last4 > -1) {
                                    final int n8 = last4;
                                    heights[n8] += remainder3;
                                }
                            }
                        }
                        if (!data6.grabExcessVerticalSpace || data6.minimumHeight != 0) {
                            h2 = ((!data6.grabExcessVerticalSpace || data6.minimumHeight == -1) ? data6.cacheHeight : data6.minimumHeight);
                            if ((h2 += data6.verticalIndent - spanMinHeight - (vSpan2 - 1) * this.verticalSpacing) > 0) {
                                if (spanExpandCount3 == 0) {
                                    final int n9 = i8;
                                    minHeights[n9] += h2;
                                }
                                else {
                                    final int delta3 = h2 / spanExpandCount3;
                                    final int remainder3 = h2 % spanExpandCount3;
                                    int last4 = -1;
                                    for (int l = 0; l < vSpan2; ++l) {
                                        if (expandRow[i8 - l]) {
                                            last4 = i8 - l;
                                            minHeights[last4] += delta3;
                                        }
                                    }
                                    if (last4 > -1) {
                                        final int n10 = last4;
                                        minHeights[n10] += remainder3;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (height != -1 && expandCount > 0) {
            int totalHeight = 0;
            for (int i11 = 0; i11 < rowCount; ++i11) {
                totalHeight += heights[i11];
            }
            int c2 = expandCount;
            int delta4 = (availableHeight - totalHeight) / c2;
            int remainder4 = (availableHeight - totalHeight) % c2;
            int last = -1;
            while (totalHeight != availableHeight) {
                for (int i12 = 0; i12 < rowCount; ++i12) {
                    if (expandRow[i12]) {
                        if (heights[i12] + delta4 > minHeights[i12]) {
                            last = i12;
                            heights[last] = heights[i12] + delta4;
                        }
                        else {
                            heights[i12] = minHeights[i12];
                            expandRow[i12] = false;
                            --c2;
                        }
                    }
                }
                if (last > -1) {
                    final int n11 = last;
                    heights[n11] += remainder4;
                }
                for (int i12 = 0; i12 < rowCount; ++i12) {
                    for (int j5 = 0; j5 < columnCount; ++j5) {
                        final GridData data7 = this.getData(grid, i12, j5, rowCount, columnCount, false);
                        final int vSpan3;
                        if (data7 != null && (vSpan3 = Math.max(1, Math.min(data7.verticalSpan, rowCount))) > 1) {
                            if (!data7.grabExcessVerticalSpace || data7.minimumHeight != 0) {
                                int spanHeight2 = 0;
                                int spanExpandCount4 = 0;
                                for (int l = 0; l < vSpan3; ++l) {
                                    spanHeight2 += heights[i12 - l];
                                    if (expandRow[i12 - l]) {
                                        ++spanExpandCount4;
                                    }
                                }
                                int h2 = (!data7.grabExcessVerticalSpace || data7.minimumHeight == -1) ? data7.cacheHeight : data7.minimumHeight;
                                if ((h2 += data7.verticalIndent - spanHeight2 - (vSpan3 - 1) * this.verticalSpacing) > 0) {
                                    if (spanExpandCount4 == 0) {
                                        final int n12 = i12;
                                        heights[n12] += h2;
                                    }
                                    else {
                                        final int delta5 = h2 / spanExpandCount4;
                                        final int remainder5 = h2 % spanExpandCount4;
                                        int last5 = -1;
                                        for (int k10 = 0; k10 < vSpan3; ++k10) {
                                            if (expandRow[i12 - k10]) {
                                                last5 = i12 - k10;
                                                heights[last5] += delta5;
                                            }
                                        }
                                        if (last5 > -1) {
                                            final int n13 = last5;
                                            heights[n13] += remainder5;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (c2 == 0) {
                    break;
                }
                totalHeight = 0;
                for (int i12 = 0; i12 < rowCount; ++i12) {
                    totalHeight += heights[i12];
                }
                delta4 = (availableHeight - totalHeight) / c2;
                remainder4 = (availableHeight - totalHeight) % c2;
                last = -1;
            }
        }
        if (move) {
            int gridY = y + this.marginTop + this.marginHeight;
            for (int i13 = 0; i13 < rowCount; ++i13) {
                int gridX = x + this.marginLeft + this.marginWidth;
                for (int j6 = 0; j6 < columnCount; ++j6) {
                    final GridData data8 = this.getData(grid, i13, j6, rowCount, columnCount, true);
                    if (data8 != null) {
                        final int hSpan5 = Math.max(1, Math.min(data8.horizontalSpan, columnCount));
                        final int vSpan4 = Math.max(1, data8.verticalSpan);
                        int cellWidth = 0;
                        int cellHeight = 0;
                        for (int k7 = 0; k7 < hSpan5; ++k7) {
                            cellWidth += widths[j6 + k7];
                        }
                        for (int k7 = 0; k7 < vSpan4; ++k7) {
                            cellHeight += heights[i13 + k7];
                        }
                        int childX = gridX + data8.horizontalIndent;
                        int childWidth = Math.min(data8.cacheWidth, cellWidth += this.horizontalSpacing * (hSpan5 - 1));
                        switch (data8.horizontalAlignment) {
                            case 2:
                            case 16777216: {
                                childX += Math.max(0, (cellWidth - data8.horizontalIndent - childWidth) / 2);
                                break;
                            }
                            case 3:
                            case 131072:
                            case 16777224: {
                                childX += Math.max(0, cellWidth - data8.horizontalIndent - childWidth);
                                break;
                            }
                            case 4: {
                                childWidth = cellWidth - data8.horizontalIndent;
                                break;
                            }
                        }
                        int childY = gridY + data8.verticalIndent;
                        int childHeight = Math.min(data8.cacheHeight, cellHeight += this.verticalSpacing * (vSpan4 - 1));
                        switch (data8.verticalAlignment) {
                            case 2:
                            case 16777216: {
                                childY += Math.max(0, (cellHeight - data8.verticalIndent - childHeight) / 2);
                                break;
                            }
                            case 3:
                            case 1024:
                            case 16777224: {
                                childY += Math.max(0, cellHeight - data8.verticalIndent - childHeight);
                                break;
                            }
                            case 4: {
                                childHeight = cellHeight - data8.verticalIndent;
                                break;
                            }
                        }
                        final Control child4 = grid[i13][j6];
                        if (child4 != null) {
                            child4.setBounds(childX, childY, childWidth, childHeight);
                        }
                    }
                    gridX += widths[j6] + this.horizontalSpacing;
                }
                gridY += heights[i13] + this.verticalSpacing;
            }
        }
        for (int i8 = 0; i8 < flushLength; ++i8) {
            flush[i8].cacheHeight = -1;
            flush[i8].cacheWidth = -1;
        }
        int totalDefaultWidth = 0;
        int totalDefaultHeight = 0;
        for (int m = 0; m < columnCount; ++m) {
            totalDefaultWidth += widths[m];
        }
        for (int m = 0; m < rowCount; ++m) {
            totalDefaultHeight += heights[m];
        }
        totalDefaultWidth += this.horizontalSpacing * (columnCount - 1) + this.marginLeft + this.marginWidth * 2 + this.marginRight;
        return new Point(totalDefaultWidth, totalDefaultHeight += this.verticalSpacing * (rowCount - 1) + this.marginTop + this.marginHeight * 2 + this.marginBottom);
    }
    
    String getName() {
        final String string = this.getClass().getName();
        final int index = string.lastIndexOf(46);
        if (index == -1) {
            return string;
        }
        return string.substring(index + 1, string.length());
    }
    
    @Override
    public String toString() {
        String string = this.getName() + " {";
        if (this.numColumns != 1) {
            string = string + "numColumns=" + this.numColumns + " ";
        }
        if (this.makeColumnsEqualWidth) {
            string = string + "makeColumnsEqualWidth=" + this.makeColumnsEqualWidth + " ";
        }
        if (this.marginWidth != 0) {
            string = string + "marginWidth=" + this.marginWidth + " ";
        }
        if (this.marginHeight != 0) {
            string = string + "marginHeight=" + this.marginHeight + " ";
        }
        if (this.marginLeft != 0) {
            string = string + "marginLeft=" + this.marginLeft + " ";
        }
        if (this.marginRight != 0) {
            string = string + "marginRight=" + this.marginRight + " ";
        }
        if (this.marginTop != 0) {
            string = string + "marginTop=" + this.marginTop + " ";
        }
        if (this.marginBottom != 0) {
            string = string + "marginBottom=" + this.marginBottom + " ";
        }
        if (this.horizontalSpacing != 0) {
            string = string + "horizontalSpacing=" + this.horizontalSpacing + " ";
        }
        if (this.verticalSpacing != 0) {
            string = string + "verticalSpacing=" + this.verticalSpacing + " ";
        }
        string = string.trim();
        string += "}";
        return string;
    }
}
