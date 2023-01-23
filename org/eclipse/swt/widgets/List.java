//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class List extends Scrollable
{
    static final int INSET = 3;
    static final long ListProc;
    static final TCHAR ListClass;
    boolean addedUCC;
    
    public List(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.addedUCC = false;
    }
    
    public void add(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        final int result = (int)OS.SendMessage(this.handle, 384, 0L, buffer);
        if (result == -1) {
            this.error(14);
        }
        if (result == -2) {
            this.error(14);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(buffer.chars, true);
        }
    }
    
    public void add(final String string, final int index) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if (index == -1) {
            this.error(6);
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        final int result = (int)OS.SendMessage(this.handle, 385, (long)index, buffer);
        if (result == -2) {
            this.error(14);
        }
        if (result == -1) {
            final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
            if (0 <= index && index <= count) {
                this.error(14);
            }
            else {
                this.error(6);
            }
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(buffer.chars, true);
        }
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(13, (Listener)typedListener);
        this.addListener(14, (Listener)typedListener);
    }
    
    @Override
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        boolean redraw = false;
        switch (msg) {
            case 276:
            case 277: {
                redraw = (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle));
                if (redraw) {
                    OS.DefWindowProc(this.handle, 11, 0L, 0L);
                    break;
                }
                break;
            }
        }
        final long code = OS.CallWindowProc(List.ListProc, hwnd, msg, wParam, lParam);
        switch (msg) {
            case 276:
            case 277: {
                if (redraw) {
                    OS.DefWindowProc(this.handle, 11, 1L, 0L);
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                    break;
                }
                break;
            }
        }
        return code;
    }
    
    static int checkStyle(final int style) {
        return Widget.checkBits(style, 4, 2, 0, 0, 0, 0);
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int width = 0;
        int height = 0;
        if (wHint == -1) {
            if ((this.style & 0x100) != 0x0) {
                width = (int)OS.SendMessage(this.handle, 403, 0L, 0L);
                width -= 3;
            }
            else {
                final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
                long oldFont = 0L;
                final long hDC = OS.GetDC(this.handle);
                final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
                if (newFont != 0L) {
                    oldFont = OS.SelectObject(hDC, newFont);
                }
                final RECT rect = new RECT();
                final int flags = 3104;
                char[] buffer = new char[65];
                for (int i = 0; i < count; ++i) {
                    final int length = (int)OS.SendMessage(this.handle, 394, (long)i, 0L);
                    if (length != -1) {
                        if (length + 1 > buffer.length) {
                            buffer = new char[length + 1];
                        }
                        final int result = (int)OS.SendMessage(this.handle, 393, (long)i, buffer);
                        if (result != -1) {
                            OS.DrawText(hDC, buffer, length, rect, 3104);
                            width = Math.max(width, rect.right - rect.left);
                        }
                    }
                }
                if (newFont != 0L) {
                    OS.SelectObject(hDC, oldFont);
                }
                OS.ReleaseDC(this.handle, hDC);
            }
        }
        if (hHint == -1) {
            final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
            final int itemHeight = (int)OS.SendMessage(this.handle, 417, 0L, 0L);
            height = count * itemHeight;
        }
        if (width == 0) {
            width = 64;
        }
        if (height == 0) {
            height = 64;
        }
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        final int border = this.getBorderWidthInPixels();
        width += border * 2 + 3;
        height += border * 2;
        if ((this.style & 0x200) != 0x0) {
            width += OS.GetSystemMetrics(2);
        }
        if ((this.style & 0x100) != 0x0) {
            height += OS.GetSystemMetrics(3);
        }
        return new Point(width, height);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(5);
    }
    
    public void deselect(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        if (indices.length == 0) {
            return;
        }
        if ((this.style & 0x4) == 0x0) {
            for (final int index : indices) {
                if (index != -1) {
                    OS.SendMessage(this.handle, 389, 0L, (long)index);
                }
            }
            return;
        }
        final int oldIndex = (int)OS.SendMessage(this.handle, 392, 0L, 0L);
        if (oldIndex == -1) {
            return;
        }
        for (final int index2 : indices) {
            if (oldIndex == index2) {
                OS.SendMessage(this.handle, 390, -1L, 0L);
                return;
            }
        }
    }
    
    public void deselect(final int index) {
        this.checkWidget();
        if (index == -1) {
            return;
        }
        if ((this.style & 0x4) == 0x0) {
            OS.SendMessage(this.handle, 389, 0L, (long)index);
            return;
        }
        final int oldIndex = (int)OS.SendMessage(this.handle, 392, 0L, 0L);
        if (oldIndex == -1) {
            return;
        }
        if (oldIndex == index) {
            OS.SendMessage(this.handle, 390, -1L, 0L);
        }
    }
    
    public void deselect(int start, int end) {
        this.checkWidget();
        if (start > end) {
            return;
        }
        if ((this.style & 0x4) != 0x0) {
            final int oldIndex = (int)OS.SendMessage(this.handle, 392, 0L, 0L);
            if (oldIndex == -1) {
                return;
            }
            if (start <= oldIndex && oldIndex <= end) {
                OS.SendMessage(this.handle, 390, -1L, 0L);
            }
        }
        else {
            final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
            if (start < 0 && end < 0) {
                return;
            }
            if (start >= count && end >= count) {
                return;
            }
            start = Math.min(count - 1, Math.max(0, start));
            end = Math.min(count - 1, Math.max(0, end));
            OS.SendMessage(this.handle, 387, (long)end, (long)start);
        }
    }
    
    public void deselectAll() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            OS.SendMessage(this.handle, 390, -1L, 0L);
        }
        else {
            OS.SendMessage(this.handle, 389, 0L, -1L);
        }
    }
    
    public int getFocusIndex() {
        this.checkWidget();
        final int result = (int)OS.SendMessage(this.handle, 415, 0L, 0L);
        if (result == 0) {
            final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
            if (count == 0) {
                return -1;
            }
        }
        return result;
    }
    
    public String getItem(final int index) {
        this.checkWidget();
        final int length = (int)OS.SendMessage(this.handle, 394, (long)index, 0L);
        if (length != -1) {
            final char[] buffer = new char[length + 1];
            final int result = (int)OS.SendMessage(this.handle, 393, (long)index, buffer);
            if (result != -1) {
                return ((this.state & 0x400000) != 0x0) ? new String(buffer, 1, length - 1) : new String(buffer, 0, length);
            }
        }
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (0 <= index && index < count) {
            this.error(8);
        }
        this.error(6);
        return "";
    }
    
    public int getItemCount() {
        this.checkWidget();
        final int result = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (result == -1) {
            this.error(36);
        }
        return result;
    }
    
    public int getItemHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getItemHeightInPixels());
    }
    
    int getItemHeightInPixels() {
        final int result = (int)OS.SendMessage(this.handle, 417, 0L, 0L);
        if (result == -1) {
            this.error(11);
        }
        return result;
    }
    
    public String[] getItems() {
        this.checkWidget();
        final int count = this.getItemCount();
        final String[] result = new String[count];
        for (int i = 0; i < count; ++i) {
            result[i] = this.getItem(i);
        }
        return result;
    }
    
    public String[] getSelection() {
        this.checkWidget();
        final int[] indices = this.getSelectionIndices();
        final String[] result = new String[indices.length];
        for (int i = 0; i < indices.length; ++i) {
            result[i] = this.getItem(indices[i]);
        }
        return result;
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        if ((this.style & 0x4) == 0x0) {
            final int result = (int)OS.SendMessage(this.handle, 400, 0L, 0L);
            if (result == -1) {
                this.error(36);
            }
            return result;
        }
        final int result = (int)OS.SendMessage(this.handle, 392, 0L, 0L);
        if (result == -1) {
            return 0;
        }
        return 1;
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return (int)OS.SendMessage(this.handle, 392, 0L, 0L);
        }
        final int count = (int)OS.SendMessage(this.handle, 400, 0L, 0L);
        if (count == -1) {
            this.error(9);
        }
        if (count == 0) {
            return -1;
        }
        final int index = (int)OS.SendMessage(this.handle, 415, 0L, 0L);
        int result = (int)OS.SendMessage(this.handle, 391, (long)index, 0L);
        if (result == -1) {
            this.error(9);
        }
        if (result != 0) {
            return index;
        }
        final int[] buffer = { 0 };
        result = (int)OS.SendMessage(this.handle, 401, 1L, buffer);
        if (result != 1) {
            this.error(9);
        }
        return buffer[0];
    }
    
    public int[] getSelectionIndices() {
        this.checkWidget();
        if ((this.style & 0x4) == 0x0) {
            final int length = (int)OS.SendMessage(this.handle, 400, 0L, 0L);
            if (length == -1) {
                this.error(9);
            }
            final int[] indices = new int[length];
            final int result = (int)OS.SendMessage(this.handle, 401, (long)length, indices);
            if (result != length) {
                this.error(9);
            }
            return indices;
        }
        final int result2 = (int)OS.SendMessage(this.handle, 392, 0L, 0L);
        if (result2 == -1) {
            return new int[0];
        }
        return new int[] { result2 };
    }
    
    public int getTopIndex() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 398, 0L, 0L);
    }
    
    public int indexOf(final String string) {
        return this.indexOf(string, 0);
    }
    
    public int indexOf(final String string, final int start) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if (string.length() == 0) {
            for (int count = this.getItemCount(), i = start; i < count; ++i) {
                if (string.equals(this.getItem(i))) {
                    return i;
                }
            }
            return -1;
        }
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (0 > start || start >= count) {
            return -1;
        }
        int index = start - 1;
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        do {
            final int last;
            index = (int)OS.SendMessage(this.handle, 418, (long)(last = index), buffer);
            if (index == -1 || index <= last) {
                return -1;
            }
        } while (!string.equals(this.getItem(index)));
        return index;
    }
    
    public boolean isSelected(final int index) {
        this.checkWidget();
        final int result = (int)OS.SendMessage(this.handle, 391, (long)index, 0L);
        return result != 0 && result != -1;
    }
    
    boolean isUseWsBorder() {
        return super.isUseWsBorder() || (this.display != null && this.display.useWsBorderList);
    }
    
    public void remove(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        if (indices.length == 0) {
            return;
        }
        final int[] newIndices = new int[indices.length];
        System.arraycopy(indices, 0, newIndices, 0, indices.length);
        this.sort(newIndices);
        final int start = newIndices[newIndices.length - 1];
        final int end = newIndices[0];
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (0 > start || start > end || end >= count) {
            this.error(6);
        }
        int topIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
        RECT rect = null;
        long hDC = 0L;
        long oldFont = 0L;
        long newFont = 0L;
        int newWidth = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            hDC = OS.GetDC(this.handle);
            newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
        }
        int i = 0;
        int topCount = 0;
        int last = -1;
        while (i < newIndices.length) {
            final int index = newIndices[i];
            if (index != last) {
                char[] buffer = null;
                int length = 0;
                if ((this.style & 0x100) != 0x0) {
                    length = (int)OS.SendMessage(this.handle, 394, (long)index, 0L);
                    if (length == -1) {
                        break;
                    }
                    buffer = new char[length + 1];
                    final int result = (int)OS.SendMessage(this.handle, 393, (long)index, buffer);
                    if (result == -1) {
                        break;
                    }
                }
                final int result = (int)OS.SendMessage(this.handle, 386, (long)index, 0L);
                if (result == -1) {
                    break;
                }
                if ((this.style & 0x100) != 0x0) {
                    final int flags = 3104;
                    OS.DrawText(hDC, buffer, length, rect, 3104);
                    newWidth = Math.max(newWidth, rect.right - rect.left);
                }
                if (index < topIndex) {
                    ++topCount;
                }
                last = index;
            }
            ++i;
        }
        if ((this.style & 0x100) != 0x0) {
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            this.setScrollWidth(newWidth, false);
        }
        if (topCount > 0) {
            topIndex -= topCount;
        }
        OS.SendMessage(this.handle, 407, (long)topIndex, 0L);
        if (i < newIndices.length) {
            this.error(15);
        }
    }
    
    public void remove(final int index) {
        this.checkWidget();
        char[] buffer = null;
        if ((this.style & 0x100) != 0x0) {
            final int length = (int)OS.SendMessage(this.handle, 394, (long)index, 0L);
            if (length == -1) {
                final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
                if (0 <= index && index < count) {
                    this.error(15);
                }
                this.error(6);
            }
            buffer = new char[length + 1];
            final int result = (int)OS.SendMessage(this.handle, 393, (long)index, buffer);
            if (result == -1) {
                final int count2 = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
                if (0 <= index && index < count2) {
                    this.error(15);
                }
                this.error(6);
            }
        }
        int topIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
        final int result = (int)OS.SendMessage(this.handle, 386, (long)index, 0L);
        if (result == -1) {
            final int count2 = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
            if (0 <= index && index < count2) {
                this.error(15);
            }
            this.error(6);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(buffer, false);
        }
        if (index < topIndex) {
            --topIndex;
        }
        OS.SendMessage(this.handle, 407, (long)topIndex, 0L);
    }
    
    public void remove(final int start, final int end) {
        this.checkWidget();
        if (start > end) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (0 > start || start > end || end >= count) {
            this.error(6);
        }
        if (start == 0 && end == count - 1) {
            this.removeAll();
            return;
        }
        int topIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
        RECT rect = null;
        long hDC = 0L;
        long oldFont = 0L;
        long newFont = 0L;
        int newWidth = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            hDC = OS.GetDC(this.handle);
            newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
        }
        int index = start;
        final int flags = 3104;
        while (index <= end) {
            char[] buffer = null;
            int length = 0;
            if ((this.style & 0x100) != 0x0) {
                length = (int)OS.SendMessage(this.handle, 394, (long)start, 0L);
                if (length == -1) {
                    break;
                }
                buffer = new char[length + 1];
                final int result = (int)OS.SendMessage(this.handle, 393, (long)start, buffer);
                if (result == -1) {
                    break;
                }
            }
            final int result = (int)OS.SendMessage(this.handle, 386, (long)start, 0L);
            if (result == -1) {
                break;
            }
            if ((this.style & 0x100) != 0x0) {
                OS.DrawText(hDC, buffer, length, rect, 3104);
                newWidth = Math.max(newWidth, rect.right - rect.left);
            }
            ++index;
        }
        if ((this.style & 0x100) != 0x0) {
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            this.setScrollWidth(newWidth, false);
        }
        if (end < topIndex) {
            topIndex -= end - start + 1;
        }
        OS.SendMessage(this.handle, 407, (long)topIndex, 0L);
        if (index <= end) {
            this.error(15);
        }
    }
    
    public void remove(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        final int index = this.indexOf(string, 0);
        if (index == -1) {
            this.error(5);
        }
        this.remove(index);
    }
    
    public void removeAll() {
        this.checkWidget();
        OS.SendMessage(this.handle, 388, 0L, 0L);
        if ((this.style & 0x100) != 0x0) {
            OS.SendMessage(this.handle, 404, 0L, 0L);
        }
    }
    
    public void removeSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(13, (SWTEventListener)listener);
        this.eventTable.unhook(14, (SWTEventListener)listener);
    }
    
    public void select(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        final int length = indices.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        this.select(indices, false);
    }
    
    void select(final int[] indices, final boolean scroll) {
        for (int i = 0; i < indices.length; ++i) {
            final int index = indices[i];
            if (index != -1) {
                this.select(index, false);
            }
        }
        if (scroll) {
            this.showSelection();
        }
    }
    
    public void select(final int index) {
        this.checkWidget();
        this.select(index, false);
    }
    
    void select(final int index, final boolean scroll) {
        if (index < 0) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (index >= count) {
            return;
        }
        if (scroll) {
            if ((this.style & 0x4) != 0x0) {
                OS.SendMessage(this.handle, 390, (long)index, 0L);
            }
            else {
                OS.SendMessage(this.handle, 389, 1L, (long)index);
            }
            return;
        }
        final int topIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
        final RECT itemRect = new RECT();
        RECT selectedRect = null;
        OS.SendMessage(this.handle, 408, (long)index, itemRect);
        final boolean redraw = this.getDrawing() && OS.IsWindowVisible(this.handle);
        if (redraw) {
            OS.UpdateWindow(this.handle);
            OS.SendMessage(this.handle, 11, 0L, 0L);
        }
        int focusIndex = -1;
        if ((this.style & 0x4) != 0x0) {
            final int oldIndex = (int)OS.SendMessage(this.handle, 392, 0L, 0L);
            if (oldIndex != -1) {
                selectedRect = new RECT();
                OS.SendMessage(this.handle, 408, (long)oldIndex, selectedRect);
            }
            OS.SendMessage(this.handle, 390, (long)index, 0L);
        }
        else {
            focusIndex = (int)OS.SendMessage(this.handle, 415, 0L, 0L);
            OS.SendMessage(this.handle, 389, 1L, (long)index);
        }
        if ((this.style & 0x2) != 0x0 && focusIndex != -1) {
            OS.SendMessage(this.handle, 414, (long)focusIndex, 0L);
        }
        OS.SendMessage(this.handle, 407, (long)topIndex, 0L);
        if (redraw) {
            OS.SendMessage(this.handle, 11, 1L, 0L);
            OS.ValidateRect(this.handle, (RECT)null);
            OS.InvalidateRect(this.handle, itemRect, true);
            if (selectedRect != null) {
                OS.InvalidateRect(this.handle, selectedRect, true);
            }
        }
    }
    
    public void select(int start, int end) {
        this.checkWidget();
        if (end < 0 || start > end || ((this.style & 0x4) != 0x0 && start != end)) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (count == 0 || start >= count) {
            return;
        }
        start = Math.max(0, start);
        end = Math.min(end, count - 1);
        if ((this.style & 0x4) != 0x0) {
            this.select(start, false);
        }
        else {
            this.select(start, end, false);
        }
    }
    
    void select(final int start, final int end, final boolean scroll) {
        if (start == end) {
            this.select(start, scroll);
            return;
        }
        OS.SendMessage(this.handle, 387, (long)start, (long)end);
        if (scroll) {
            this.showSelection();
        }
    }
    
    public void selectAll() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 389, 1L, -1L);
    }
    
    void setFocusIndex(final int index) {
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (0 > index || index >= count) {
            return;
        }
        OS.SendMessage(this.handle, 414, (long)index, 0L);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        super.setFont(font);
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth();
        }
    }
    
    public void setItem(final int index, final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        final int topIndex = this.getTopIndex();
        final boolean isSelected = this.isSelected(index);
        this.remove(index);
        this.add(string, index);
        if (isSelected) {
            this.select(index, false);
        }
        this.setTopIndex(topIndex);
    }
    
    public void setItems(final String... items) {
        this.checkWidget();
        if (items == null) {
            this.error(4);
        }
        for (final String item : items) {
            if (item == null) {
                this.error(5);
            }
        }
        final long oldProc = OS.GetWindowLongPtr(this.handle, -4);
        OS.SetWindowLongPtr(this.handle, -4, List.ListProc);
        final boolean redraw = this.getDrawing() && OS.IsWindowVisible(this.handle);
        if (redraw) {
            OS.SendMessage(this.handle, 11, 0L, 0L);
        }
        RECT rect = null;
        long hDC = 0L;
        long oldFont = 0L;
        long newFont = 0L;
        int newWidth = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            hDC = OS.GetDC(this.handle);
            newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            OS.SendMessage(this.handle, 404, 0L, 0L);
        }
        final int length = items.length;
        OS.SendMessage(this.handle, 388, 0L, 0L);
        OS.SendMessage(this.handle, 424, (long)length, (long)(length * 32));
        int index = 0;
        final int cp = this.getCodePage();
        while (index < length) {
            final String string = items[index];
            final TCHAR buffer = new TCHAR(cp, string, true);
            final int result = (int)OS.SendMessage(this.handle, 384, 0L, buffer);
            if (result == -1) {
                break;
            }
            if (result == -2) {
                break;
            }
            if ((this.style & 0x100) != 0x0) {
                final int flags = 3104;
                OS.DrawText(hDC, buffer, -1, rect, 3104);
                newWidth = Math.max(newWidth, rect.right - rect.left);
            }
            ++index;
        }
        if ((this.style & 0x100) != 0x0) {
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            OS.SendMessage(this.handle, 404, (long)(newWidth + 3), 0L);
        }
        if (redraw) {
            OS.SendMessage(this.handle, 11, 1L, 0L);
        }
        OS.SetWindowLongPtr(this.handle, -4, oldProc);
        if (index < items.length) {
            this.error(14);
        }
    }
    
    void setScrollWidth() {
        int newWidth = 0;
        final RECT rect = new RECT();
        long oldFont = 0L;
        final long hDC = OS.GetDC(this.handle);
        final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        final int flags = 3104;
        for (int i = 0; i < count; ++i) {
            final int length = (int)OS.SendMessage(this.handle, 394, (long)i, 0L);
            if (length != -1) {
                final char[] buffer = new char[length + 1];
                final int result = (int)OS.SendMessage(this.handle, 393, (long)i, buffer);
                if (result != -1) {
                    OS.DrawText(hDC, buffer, length, rect, 3104);
                    newWidth = Math.max(newWidth, rect.right - rect.left);
                }
            }
        }
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(this.handle, hDC);
        OS.SendMessage(this.handle, 404, (long)(newWidth + 3), 0L);
    }
    
    void setScrollWidth(final char[] buffer, final boolean grow) {
        final RECT rect = new RECT();
        long oldFont = 0L;
        final long hDC = OS.GetDC(this.handle);
        final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        final int flags = 3104;
        OS.DrawText(hDC, buffer, -1, rect, 3104);
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(this.handle, hDC);
        this.setScrollWidth(rect.right - rect.left, grow);
    }
    
    void setScrollWidth(int newWidth, final boolean grow) {
        newWidth += 3;
        final int width = (int)OS.SendMessage(this.handle, 403, 0L, 0L);
        if (grow) {
            if (newWidth <= width) {
                return;
            }
            OS.SendMessage(this.handle, 404, (long)newWidth, 0L);
        }
        else {
            if (newWidth < width) {
                return;
            }
            this.setScrollWidth();
        }
    }
    
    public void setSelection(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            this.error(4);
        }
        this.deselectAll();
        final int length = indices.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        this.select(indices, true);
        if ((this.style & 0x2) != 0x0) {
            final int focusIndex = indices[0];
            if (focusIndex >= 0) {
                this.setFocusIndex(focusIndex);
            }
        }
    }
    
    public void setSelection(final String[] items) {
        this.checkWidget();
        if (items == null) {
            this.error(4);
        }
        this.deselectAll();
        final int length = items.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        int focusIndex = -1;
        for (int i = length - 1; i >= 0; --i) {
            final String string = items[i];
            int index = 0;
            if (string != null) {
                int localFocus = -1;
                while ((index = this.indexOf(string, index)) != -1) {
                    if (localFocus == -1) {
                        localFocus = index;
                    }
                    this.select(index, false);
                    if ((this.style & 0x4) != 0x0 && this.isSelected(index)) {
                        this.showSelection();
                        return;
                    }
                    ++index;
                }
                if (localFocus != -1) {
                    focusIndex = localFocus;
                }
            }
        }
        if ((this.style & 0x2) != 0x0 && focusIndex >= 0) {
            this.setFocusIndex(focusIndex);
        }
    }
    
    public void setSelection(final int index) {
        this.checkWidget();
        this.deselectAll();
        this.select(index, true);
        if ((this.style & 0x2) != 0x0 && index >= 0) {
            this.setFocusIndex(index);
        }
    }
    
    public void setSelection(int start, int end) {
        this.checkWidget();
        this.deselectAll();
        if (end < 0 || start > end || ((this.style & 0x4) != 0x0 && start != end)) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (count == 0 || start >= count) {
            return;
        }
        start = Math.max(0, start);
        end = Math.min(end, count - 1);
        if ((this.style & 0x4) != 0x0) {
            this.select(start, true);
        }
        else {
            this.select(start, end, true);
            this.setFocusIndex(start);
        }
    }
    
    public void setTopIndex(int index) {
        this.checkWidget();
        final int result = (int)OS.SendMessage(this.handle, 407, (long)index, 0L);
        if (result == -1) {
            final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
            index = Math.min(count - 1, Math.max(0, index));
            OS.SendMessage(this.handle, 407, (long)index, 0L);
        }
    }
    
    public void showSelection() {
        this.checkWidget();
        int index;
        if ((this.style & 0x4) != 0x0) {
            index = (int)OS.SendMessage(this.handle, 392, 0L, 0L);
        }
        else {
            final int[] indices = { 0 };
            final int result = (int)OS.SendMessage(this.handle, 401, 1L, indices);
            index = indices[0];
            if (result != 1) {
                index = -1;
            }
        }
        if (index == -1) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (count == 0) {
            return;
        }
        final int height = (int)OS.SendMessage(this.handle, 417, 0L, 0L);
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int topIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
        final int visibleCount = Math.max(rect.bottom / height, 1);
        final int bottomIndex = Math.min(topIndex + visibleCount, count) - 1;
        if (topIndex <= index && index <= bottomIndex) {
            return;
        }
        final int newTop = Math.min(Math.max(index - visibleCount / 2, 0), count - 1);
        OS.SendMessage(this.handle, 407, (long)newTop, 0L);
    }
    
    void updateMenuLocation(final Event event) {
        final Rectangle clientArea = this.getClientAreaInPixels();
        int x = clientArea.x;
        int y = clientArea.y;
        final int focusIndex = this.getFocusIndex();
        if (focusIndex != -1) {
            final RECT rect = new RECT();
            long oldFont = 0L;
            final long hDC = OS.GetDC(this.handle);
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            final int flags = 3104;
            char[] buffer = new char[65];
            final int length = (int)OS.SendMessage(this.handle, 394, (long)focusIndex, 0L);
            if (length != -1) {
                if (length + 1 > buffer.length) {
                    buffer = new char[length + 1];
                }
                final int result = (int)OS.SendMessage(this.handle, 393, (long)focusIndex, buffer);
                if (result != -1) {
                    OS.DrawText(hDC, buffer, length, rect, 3104);
                }
            }
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            x = Math.max(x, rect.right / 2);
            x = Math.min(x, clientArea.x + clientArea.width);
            OS.SendMessage(this.handle, 408, (long)focusIndex, rect);
            y = Math.max(y, rect.bottom);
            y = Math.min(y, clientArea.y + clientArea.height);
        }
        final Point pt = this.toDisplayInPixels(x, y);
        event.setLocationInPixels(pt.x, pt.y);
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (textDirection == 100663296) {
            if ((this.state & 0x400000) != 0x0) {
                return false;
            }
            this.state |= 0x400000;
        }
        else {
            this.state &= 0xFFBFFFFF;
            if (!this.addedUCC) {
                return super.updateTextDirection(textDirection);
            }
        }
        int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
        if (count == -1) {
            return false;
        }
        final int selection = (int)OS.SendMessage(this.handle, 392, 0L, 0L);
        this.addedUCC = false;
        while (count-- > 0) {
            final int length = (int)OS.SendMessage(this.handle, 394, (long)count, 0L);
            if (length == -1) {
                break;
            }
            if (length == 0) {
                continue;
            }
            final char[] buffer = new char[length + 1];
            if (OS.SendMessage(this.handle, 393, (long)count, buffer) == -1L) {
                break;
            }
            if (OS.SendMessage(this.handle, 386, (long)count, 0L) == -1L) {
                break;
            }
            if ((this.state & 0x400000) == 0x0) {
                System.arraycopy(buffer, 1, buffer, 0, length);
            }
            if (OS.SendMessage(this.handle, 385, (long)count, buffer) == -1L) {
                break;
            }
        }
        if (selection != -1) {
            OS.SendMessage(this.handle, 390, (long)selection, 0L);
        }
        return textDirection == 100663296 || super.updateTextDirection(textDirection);
    }
    
    @Override
    int widgetStyle() {
        final int bits = super.widgetStyle() | 0x1 | 0x100;
        if ((this.style & 0x4) != 0x0) {
            return bits;
        }
        if ((this.style & 0x2) == 0x0) {
            return bits;
        }
        if ((this.style & 0x40) != 0x0) {
            return bits | 0x8;
        }
        return bits | 0x800;
    }
    
    @Override
    TCHAR windowClass() {
        return List.ListClass;
    }
    
    @Override
    long windowProc() {
        return List.ListProc;
    }
    
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle != 0L && lParam != 0L && (this.state & 0x400000) != 0x0) {
            switch (msg) {
                case 384:
                case 385:
                case 418: {
                    int length = OS.wcslen(lParam);
                    final int cp = this.getCodePage();
                    TCHAR buffer = new TCHAR(cp, length);
                    OS.MoveMemory(buffer, lParam, buffer.length() * 2);
                    String string = buffer.toString(0, length);
                    int direction = BidiUtil.resolveTextDirection(string);
                    if (direction == 0) {
                        direction = (((this.style & 0x4000000) != 0x0) ? 67108864 : 33554432);
                    }
                    string = ((direction == 67108864) ? 8235 : 8234) + string;
                    buffer = new TCHAR(cp, string, true);
                    final long hHeap = OS.GetProcessHeap();
                    length = buffer.length() * 2;
                    final long pszText = OS.HeapAlloc(hHeap, 8, length);
                    OS.MoveMemory(pszText, buffer, length);
                    final long code = super.windowProc(hwnd, msg, wParam, pszText);
                    OS.HeapFree(hHeap, 0, pszText);
                    this.addedUCC = true;
                    return code;
                }
            }
        }
        return super.windowProc(hwnd, msg, wParam, lParam);
    }
    
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CHAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (OS.GetKeyState(17) < 0 && OS.GetKeyState(16) >= 0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x800) != 0x0) {
                switch ((int)wParam) {
                    case 32: {
                        final int index = (int)OS.SendMessage(this.handle, 415, 0L, 0L);
                        final int code = (int)OS.SendMessage(this.handle, 391, (long)index, 0L);
                        if (code == -1) {
                            break;
                        }
                        OS.SendMessage(this.handle, 389, (long)((code == 0) ? 1 : 0), (long)index);
                        OS.SendMessage(this.handle, 61852, (long)index, 0L);
                        this.sendSelectionEvent(13);
                        return LRESULT.ZERO;
                    }
                }
            }
        }
        return result;
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (OS.GetKeyState(17) < 0 && OS.GetKeyState(16) >= 0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x800) != 0x0) {
                int newIndex = -1;
                switch ((int)wParam) {
                    case 32: {
                        return LRESULT.ZERO;
                    }
                    case 38:
                    case 40: {
                        final int oldIndex = (int)OS.SendMessage(this.handle, 415, 0L, 0L);
                        newIndex = Math.max(0, oldIndex + (((int)wParam == 38) ? -1 : 1));
                        break;
                    }
                    case 33: {
                        final int topIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
                        final int oldIndex2 = (int)OS.SendMessage(this.handle, 415, 0L, 0L);
                        if (oldIndex2 != topIndex) {
                            newIndex = topIndex;
                            break;
                        }
                        this.forceResize();
                        final RECT rect = new RECT();
                        OS.GetClientRect(this.handle, rect);
                        final int itemHeight = (int)OS.SendMessage(this.handle, 417, 0L, 0L);
                        final int pageSize = Math.max(2, rect.bottom / itemHeight);
                        newIndex = Math.max(0, topIndex - (pageSize - 1));
                        break;
                    }
                    case 34: {
                        final int topIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
                        final int oldIndex2 = (int)OS.SendMessage(this.handle, 415, 0L, 0L);
                        this.forceResize();
                        final RECT rect = new RECT();
                        OS.GetClientRect(this.handle, rect);
                        final int itemHeight = (int)OS.SendMessage(this.handle, 417, 0L, 0L);
                        final int pageSize = Math.max(2, rect.bottom / itemHeight);
                        final int bottomIndex = topIndex + pageSize - 1;
                        if (oldIndex2 != bottomIndex) {
                            newIndex = bottomIndex;
                        }
                        else {
                            newIndex = bottomIndex + pageSize - 1;
                        }
                        final int count = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
                        if (count != -1) {
                            newIndex = Math.min(count - 1, newIndex);
                            break;
                        }
                        break;
                    }
                    case 36: {
                        newIndex = 0;
                        break;
                    }
                    case 35: {
                        final int count2 = (int)OS.SendMessage(this.handle, 395, 0L, 0L);
                        if (count2 == -1) {
                            break;
                        }
                        newIndex = count2 - 1;
                        break;
                    }
                }
                if (newIndex != -1) {
                    final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                    if ((uiState & 0x1) != 0x0) {
                        OS.SendMessage(this.handle, 295, 3L, 0L);
                        final RECT itemRect = new RECT();
                        final int oldIndex3 = (int)OS.SendMessage(this.handle, 415, 0L, 0L);
                        OS.SendMessage(this.handle, 408, (long)oldIndex3, itemRect);
                        OS.InvalidateRect(this.handle, itemRect, true);
                    }
                    OS.SendMessage(this.handle, 414, (long)newIndex, 0L);
                    return LRESULT.ZERO;
                }
            }
        }
        return result;
    }
    
    LRESULT WM_SETREDRAW(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETREDRAW(wParam, lParam);
        if (result != null) {
            return result;
        }
        OS.DefWindowProc(this.handle, 11, wParam, lParam);
        return result;
    }
    
    @Override
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final int oldIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        if (!this.isDisposed()) {
            final SCROLLINFO info = new SCROLLINFO();
            info.cbSize = SCROLLINFO.sizeof;
            info.fMask = 4;
            if (OS.GetScrollInfo(this.handle, 0, info) && info.nPos != 0) {
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
            final int newIndex = (int)OS.SendMessage(this.handle, 398, 0L, 0L);
            if (oldIndex != newIndex) {
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
        }
        return result;
    }
    
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        final int code = OS.HIWORD(wParam);
        switch (code) {
            case 1: {
                this.sendSelectionEvent(13);
                break;
            }
            case 2: {
                this.sendSelectionEvent(14);
                break;
            }
        }
        return super.wmCommandChild(wParam, lParam);
    }
    
    static {
        ListClass = new TCHAR(0, "LISTBOX", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, List.ListClass, lpWndClass);
        ListProc = lpWndClass.lpfnWndProc;
    }
}
