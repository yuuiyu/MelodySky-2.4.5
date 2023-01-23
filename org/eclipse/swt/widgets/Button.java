//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Button extends Control
{
    String text;
    String message;
    Image image;
    Image disabledImage;
    ImageList imageList;
    boolean ignoreMouse;
    boolean grayed;
    boolean useDarkModeExplorerTheme;
    static final int MARGIN = 4;
    static final int CHECK_WIDTH;
    static final int CHECK_HEIGHT;
    static final int ICON_WIDTH = 128;
    static final int ICON_HEIGHT = 128;
    static boolean COMMAND_LINK;
    static final char[] STRING_WITH_ZERO_CHAR;
    static final long ButtonProc;
    static final TCHAR ButtonClass;
    
    public Button(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.text = "";
        this.message = "";
    }
    
    void _setImage(final Image image) {
        if ((this.style & 0x400000) != 0x0) {
            return;
        }
        if (this.imageList != null) {
            this.imageList.dispose();
        }
        this.imageList = null;
        if (image != null) {
            this.imageList = new ImageList(this.style & 0x4000000);
            if (OS.IsWindowEnabled(this.handle)) {
                this.imageList.add(image);
            }
            else {
                if (this.disabledImage != null) {
                    this.disabledImage.dispose();
                }
                this.disabledImage = new Image((Device)this.display, image, 1);
                this.imageList.add(this.disabledImage);
            }
            final BUTTON_IMAGELIST buttonImageList = new BUTTON_IMAGELIST();
            buttonImageList.himl = this.imageList.getHandle();
            int newBits;
            final int oldBits = newBits = OS.GetWindowLong(this.handle, -16);
            newBits &= 0xFFFFFCFF;
            if ((this.style & 0x4000) != 0x0) {
                newBits |= 0x100;
            }
            if ((this.style & 0x1000000) != 0x0) {
                newBits |= 0x300;
            }
            if ((this.style & 0x20000) != 0x0) {
                newBits |= 0x200;
            }
            if (this.text.length() == 0) {
                if ((this.style & 0x4000) != 0x0) {
                    buttonImageList.uAlign = 0;
                }
                if ((this.style & 0x1000000) != 0x0) {
                    buttonImageList.uAlign = 4;
                }
                if ((this.style & 0x20000) != 0x0) {
                    buttonImageList.uAlign = 1;
                }
            }
            else {
                buttonImageList.uAlign = 0;
                buttonImageList.margin_left = this.computeLeftMargin();
                buttonImageList.margin_right = 4;
                newBits &= 0xFFFFFCFF;
                newBits |= 0x100;
            }
            if (newBits != oldBits) {
                OS.SetWindowLong(this.handle, -16, newBits);
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
            OS.SendMessage(this.handle, 5634, 0L, buttonImageList);
        }
        else {
            OS.SendMessage(this.handle, 5634, 0L, 0L);
        }
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    void _setText(String text) {
        int newBits;
        final int oldBits = newBits = OS.GetWindowLong(this.handle, -16);
        newBits &= 0xFFFFFCFF;
        if ((this.style & 0x4000) != 0x0) {
            newBits |= 0x100;
        }
        if ((this.style & 0x1000000) != 0x0) {
            newBits |= 0x300;
        }
        if ((this.style & 0x20000) != 0x0) {
            newBits |= 0x200;
        }
        if (this.imageList != null) {
            final BUTTON_IMAGELIST buttonImageList = new BUTTON_IMAGELIST();
            buttonImageList.himl = this.imageList.getHandle();
            if (text.length() == 0) {
                if ((this.style & 0x4000) != 0x0) {
                    buttonImageList.uAlign = 0;
                }
                if ((this.style & 0x1000000) != 0x0) {
                    buttonImageList.uAlign = 4;
                }
                if ((this.style & 0x20000) != 0x0) {
                    buttonImageList.uAlign = 1;
                }
            }
            else {
                buttonImageList.uAlign = 0;
                buttonImageList.margin_left = this.computeLeftMargin();
                buttonImageList.margin_right = 4;
                newBits &= 0xFFFFFCFF;
                newBits |= 0x100;
            }
            OS.SendMessage(this.handle, 5634, 0L, buttonImageList);
        }
        if (newBits != oldBits) {
            OS.SetWindowLong(this.handle, -16, newBits);
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        if ((this.style & 0x4000000) != 0x0 && !OS.IsAppThemed()) {
            text = (OS.IsWindowEnabled(this.handle) ? text : (text + " "));
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), text, true);
        OS.SetWindowText(this.handle, buffer);
        if ((this.state & 0x400000) != 0x0) {
            this.updateTextDirection(100663296);
        }
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    @Override
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        return OS.CallWindowProc(Button.ButtonProc, hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(int style) {
        if (((style = Widget.checkBits(style, 8, 4, 32, 16, 2, Button.COMMAND_LINK ? 4194304 : 0)) & 0xA) != 0x0) {
            return Widget.checkBits(style, 16777216, 16384, 131072, 0, 0, 0);
        }
        if ((style & 0x30) != 0x0) {
            return Widget.checkBits(style, 16384, 131072, 16777216, 0, 0, 0);
        }
        if ((style & 0x4) != 0x0) {
            return Widget.checkBits(style |= 0x80000, 128, 1024, 16384, 131072, 0, 0);
        }
        return style;
    }
    
    void click() {
        this.ignoreMouse = true;
        OS.SendMessage(this.handle, 245, 0L, 0L);
        this.ignoreMouse = false;
    }
    
    int computeLeftMargin() {
        if ((this.style & 0xA) == 0x0) {
            return 4;
        }
        int margin = 0;
        if (this.image != null && this.text.length() != 0) {
            final Rectangle bounds = this.image.getBoundsInPixels();
            margin += bounds.width + 8;
            long oldFont = 0L;
            final long hDC = OS.GetDC(this.handle);
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            final char[] buffer = this.text.toCharArray();
            final RECT rect = new RECT();
            final int flags = 1056;
            OS.DrawText(hDC, (char[])buffer, buffer.length, rect, flags);
            margin += rect.right - rect.left;
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            OS.GetClientRect(this.handle, rect);
            margin = Math.max(4, (rect.right - rect.left - margin) / 2);
        }
        return margin;
    }
    
    @Override
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int width = 0;
        int height = 0;
        final int border = this.getBorderWidthInPixels();
        if ((this.style & 0x4) != 0x0) {
            if ((this.style & 0x480) != 0x0) {
                width += OS.GetSystemMetrics(2);
                height += OS.GetSystemMetrics(20);
            }
            else {
                width += OS.GetSystemMetrics(21);
                height += OS.GetSystemMetrics(3);
            }
        }
        else if ((this.style & 0x400000) != 0x0) {
            final SIZE size = new SIZE();
            if (wHint != -1) {
                size.cx = wHint;
                OS.SendMessage(this.handle, 5633, 0L, size);
                width = size.cx;
                height = size.cy;
            }
            else {
                OS.SendMessage(this.handle, 5633, 0L, size);
                width = size.cy;
                height = size.cy;
                size.cy = 0;
                while (size.cy != height) {
                    size.cx = width++;
                    size.cy = 0;
                    OS.SendMessage(this.handle, 5633, 0L, size);
                }
            }
        }
        else {
            int extra = 0;
            final boolean hasImage = this.image != null;
            final boolean hasText = true;
            if (hasImage && this.image != null) {
                final Rectangle rect = this.image.getBoundsInPixels();
                width = rect.width;
                if (hasText && this.text.length() != 0) {
                    width += 8;
                }
                height = rect.height;
                extra = 8;
            }
            if (hasText) {
                long oldFont = 0L;
                final long hDC = OS.GetDC(this.handle);
                final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
                if (newFont != 0L) {
                    oldFont = OS.SelectObject(hDC, newFont);
                }
                final TEXTMETRIC lptm = new TEXTMETRIC();
                OS.GetTextMetrics(hDC, lptm);
                final int length = this.text.length();
                if (length == 0) {
                    height = Math.max(height, lptm.tmHeight);
                }
                else {
                    extra = Math.max(8, lptm.tmAveCharWidth);
                    final char[] buffer = this.text.toCharArray();
                    final RECT rect2 = new RECT();
                    int flags = 1056;
                    if ((this.style & 0x40) != 0x0 && wHint != -1) {
                        flags = 1040;
                        rect2.right = wHint - width - 2 * border;
                        final RECT rect3 = rect2;
                        int right;
                        if (this.isRadioOrCheck()) {
                            final RECT rect4 = rect2;
                            right = (rect4.right -= Button.CHECK_WIDTH + 3);
                        }
                        else {
                            final RECT rect5 = rect2;
                            right = (rect5.right -= 6);
                        }
                        rect3.right = right;
                        if (!OS.IsAppThemed()) {
                            final RECT rect6 = rect2;
                            rect6.right -= 2;
                            if (this.isRadioOrCheck()) {
                                final RECT rect7 = rect2;
                                rect7.right -= 2;
                            }
                        }
                    }
                    OS.DrawText(hDC, (char[])buffer, buffer.length, rect2, flags);
                    width += rect2.right - rect2.left;
                    height = Math.max(height, rect2.bottom - rect2.top);
                }
                if (newFont != 0L) {
                    OS.SelectObject(hDC, oldFont);
                }
                OS.ReleaseDC(this.handle, hDC);
            }
            if (this.isRadioOrCheck()) {
                width += Button.CHECK_WIDTH + extra;
                height = Math.max(height, Button.CHECK_HEIGHT + 3);
            }
            if ((this.style & 0xA) != 0x0) {
                width += 12;
                height += 10;
            }
        }
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        width += border * 2;
        return new Point(width, height += border * 2);
    }
    
    @Override
    void createHandle() {
        final Composite parent = this.parent;
        parent.state |= 0x100000;
        super.createHandle();
        final Composite parent2 = this.parent;
        parent2.state &= 0xFFEFFFFF;
        if (OS.IsAppThemed()) {
            if ((this.style & 0xA) == 0x0) {
                this.state |= 0x100;
            }
            if ((this.style & 0x10) != 0x0) {
                this.state |= 0x200;
            }
            this.useDarkModeExplorerTheme = this.display.useDarkModeExplorerTheme;
            this.maybeEnableDarkSystemTheme();
        }
    }
    
    private boolean customBackgroundDrawing() {
        return this.background != -1 && !this.isRadioOrCheck();
    }
    
    private boolean customDrawing() {
        return this.customBackgroundDrawing() || this.customForegroundDrawing();
    }
    
    private boolean customForegroundDrawing() {
        return this.foreground != -1 && !this.text.isEmpty() && OS.IsWindowEnabled(this.handle);
    }
    
    @Override
    int defaultBackground() {
        if ((this.style & 0xA) != 0x0) {
            return OS.GetSysColor(15);
        }
        return super.defaultBackground();
    }
    
    @Override
    int defaultForeground() {
        return OS.GetSysColor(18);
    }
    
    @Override
    void enableWidget(final boolean enabled) {
        super.enableWidget(enabled);
        if ((this.style & 0x4000000) != 0x0 && !OS.IsAppThemed()) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            final boolean bl;
            final boolean hasImage = bl = ((bits & 0xC0) != 0x0);
            if (!hasImage) {
                final String string = enabled ? this.text : (this.text + " ");
                final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
                OS.SetWindowText(this.handle, buffer);
            }
        }
        this.updateImageList();
    }
    
    public int getAlignment() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            if ((this.style & 0x80) != 0x0) {
                return 128;
            }
            if ((this.style & 0x400) != 0x0) {
                return 1024;
            }
            if ((this.style & 0x4000) != 0x0) {
                return 16384;
            }
            if ((this.style & 0x20000) != 0x0) {
                return 131072;
            }
            return 128;
        }
        else {
            if ((this.style & 0x4000) != 0x0) {
                return 16384;
            }
            if ((this.style & 0x1000000) != 0x0) {
                return 16777216;
            }
            if ((this.style & 0x20000) != 0x0) {
                return 131072;
            }
            return 16384;
        }
    }
    
    boolean getDefault() {
        if ((this.style & 0x8) == 0x0) {
            return false;
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        return (bits & 0x1) != 0x0;
    }
    
    public boolean getGrayed() {
        this.checkWidget();
        return (this.style & 0x20) != 0x0 && this.grayed;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    String getMessage() {
        this.checkWidget();
        return this.message;
    }
    
    @Override
    String getNameText() {
        return this.getText();
    }
    
    public boolean getSelection() {
        this.checkWidget();
        return (this.style & 0x32) != 0x0 && this.isChecked();
    }
    
    public String getText() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return "";
        }
        return this.text;
    }
    
    private boolean isChecked() {
        final long flags = OS.SendMessage(this.handle, 240, 0L, 0L);
        return flags != 0L;
    }
    
    private boolean isRadioOrCheck() {
        return (this.style & 0x30) != 0x0;
    }
    
    @Override
    boolean isTabItem() {
        if ((this.style & 0x8) != 0x0) {
            return this.isTabGroup();
        }
        return super.isTabItem();
    }
    
    @Override
    boolean mnemonicHit(final char ch) {
        if ((this.style & 0x10) == 0x0 && !this.setFocus()) {
            return false;
        }
        this.click();
        return true;
    }
    
    @Override
    boolean mnemonicMatch(final char key) {
        final char mnemonic = this.findMnemonic(this.getText());
        return mnemonic != '\0' && Character.toUpperCase(key) == Character.toUpperCase(mnemonic);
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        if (this.imageList != null) {
            this.imageList.dispose();
        }
        this.imageList = null;
        if (this.disabledImage != null) {
            this.disabledImage.dispose();
        }
        this.disabledImage = null;
        this.text = null;
        this.image = null;
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
    
    @Override
    int resolveTextDirection() {
        return ((this.style & 0x4) != 0x0) ? 0 : BidiUtil.resolveTextDirection(this.text);
    }
    
    void selectRadio() {
        for (final Control child : this.parent._getChildren()) {
            if (this != child) {
                child.setRadioSelection(false);
            }
        }
        this.setSelection(true);
    }
    
    public void setAlignment(final int alignment) {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            if ((this.style & 0x24480) == 0x0) {
                return;
            }
            this.style &= 0xFFFDBB7F;
            this.style |= (alignment & 0x24480);
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        else {
            if ((alignment & 0x1024000) == 0x0) {
                return;
            }
            this.style &= 0xFEFDBFFF;
            this.style |= (alignment & 0x1024000);
            int newBits;
            final int oldBits = newBits = OS.GetWindowLong(this.handle, -16);
            newBits &= 0xFFFFFCFF;
            if ((this.style & 0x4000) != 0x0) {
                newBits |= 0x100;
            }
            if ((this.style & 0x1000000) != 0x0) {
                newBits |= 0x300;
            }
            if ((this.style & 0x20000) != 0x0) {
                newBits |= 0x200;
            }
            if (this.imageList != null) {
                final BUTTON_IMAGELIST buttonImageList = new BUTTON_IMAGELIST();
                buttonImageList.himl = this.imageList.getHandle();
                if (this.text.length() == 0) {
                    if ((this.style & 0x4000) != 0x0) {
                        buttonImageList.uAlign = 0;
                    }
                    if ((this.style & 0x1000000) != 0x0) {
                        buttonImageList.uAlign = 4;
                    }
                    if ((this.style & 0x20000) != 0x0) {
                        buttonImageList.uAlign = 1;
                    }
                }
                else {
                    buttonImageList.uAlign = 0;
                    buttonImageList.margin_left = this.computeLeftMargin();
                    buttonImageList.margin_right = 4;
                    newBits &= 0xFFFFFCFF;
                    newBits |= 0x100;
                }
                OS.SendMessage(this.handle, 5634, 0L, buttonImageList);
            }
            if (newBits != oldBits) {
                OS.SetWindowLong(this.handle, -16, newBits);
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
        }
    }
    
    @Override
    public void setBackground(final Color color) {
        super.setBackground(color);
    }
    
    void setDefault(final boolean value) {
        if ((this.style & 0x8) == 0x0) {
            return;
        }
        final long hwndShell = this.menuShell().handle;
        int bits = OS.GetWindowLong(this.handle, -16);
        if (value) {
            bits |= 0x1;
            OS.SendMessage(hwndShell, 1025, this.handle, 0L);
        }
        else {
            bits &= 0xFFFFFFFE;
            OS.SendMessage(hwndShell, 1025, 0L, 0L);
        }
        OS.SendMessage(this.handle, 244, (long)bits, 1L);
    }
    
    @Override
    public boolean setFocus() {
        this.checkWidget();
        return ((this.style & 0x10) == 0x0 || this.isChecked()) && super.setFocus();
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        this._setImage(this.image = image);
    }
    
    public void setGrayed(final boolean grayed) {
        this.checkWidget();
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        this.grayed = grayed;
        final long flags = OS.SendMessage(this.handle, 240, 0L, 0L);
        if (grayed) {
            if (flags == 1L) {
                this.updateSelection(2);
            }
        }
        else if (flags == 2L) {
            this.updateSelection(1);
        }
    }
    
    void setMessage(final String message) {
        this.checkWidget();
        if (message == null) {
            this.error(4);
        }
        this.message = message;
        if ((this.style & 0x400000) != 0x0) {
            final int length = message.length();
            final char[] chars = new char[length + 1];
            message.getChars(0, length, chars, 0);
            OS.SendMessage(this.handle, 5641, 0L, (char[])chars);
        }
    }
    
    @Override
    boolean setRadioFocus(final boolean tabbing) {
        return (this.style & 0x10) != 0x0 && this.getSelection() && (tabbing ? this.setTabItemFocus() : this.setFocus());
    }
    
    @Override
    boolean setRadioSelection(final boolean value) {
        if ((this.style & 0x10) == 0x0) {
            return false;
        }
        if (this.getSelection() != value) {
            this.setSelection(value);
            this.sendSelectionEvent(13);
        }
        return true;
    }
    
    public void setSelection(final boolean selected) {
        this.checkWidget();
        if ((this.style & 0x32) == 0x0) {
            return;
        }
        final int n;
        int flags = n = (selected ? 1 : 0);
        if ((this.style & 0x20) != 0x0 && selected && this.grayed) {
            flags = 2;
        }
        this.updateSelection(flags);
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        this._setText(this.text = string);
    }
    
    @Override
    boolean updateTextDirection(final int textDirection) {
        return super.updateTextDirection(textDirection);
    }
    
    void updateImageList() {
        if (this.imageList != null) {
            final BUTTON_IMAGELIST buttonImageList = new BUTTON_IMAGELIST();
            OS.SendMessage(this.handle, 5635, 0L, buttonImageList);
            if (this.imageList != null) {
                this.imageList.dispose();
            }
            this.imageList = new ImageList(this.style & 0x4000000);
            if (OS.IsWindowEnabled(this.handle)) {
                this.imageList.add(this.image);
            }
            else {
                if (this.disabledImage != null) {
                    this.disabledImage.dispose();
                }
                this.disabledImage = new Image((Device)this.display, this.image, 1);
                this.imageList.add(this.disabledImage);
            }
            buttonImageList.himl = this.imageList.getHandle();
            OS.SendMessage(this.handle, 5634, 0L, buttonImageList);
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
    }
    
    @Override
    void updateOrientation() {
        super.updateOrientation();
        this.updateImageList();
    }
    
    void updateSelection(final int flags) {
        if (flags != OS.SendMessage(this.handle, 240, 0L, 0L)) {
            int bits = OS.GetWindowLong(this.handle, -16);
            if ((this.style & 0x20) != 0x0) {
                if (flags == 2) {
                    bits &= 0xFFFFFFFD;
                    bits |= 0x5;
                }
                else {
                    bits |= 0x2;
                    bits &= 0xFFFFFFFA;
                }
                if (bits != OS.GetWindowLong(this.handle, -16)) {
                    OS.SetWindowLong(this.handle, -16, bits);
                }
            }
            OS.SendMessage(this.handle, 241, (long)flags, 0L);
            if (bits != OS.GetWindowLong(this.handle, -16)) {
                OS.SetWindowLong(this.handle, -16, bits);
            }
        }
    }
    
    @Override
    int widgetStyle() {
        int bits = super.widgetStyle();
        if ((this.style & 0x800000) != 0x0) {
            bits |= 0x8000;
        }
        if ((this.style & 0x4) != 0x0) {
            return bits | 0xB;
        }
        if ((this.style & 0x4000) != 0x0) {
            bits |= 0x100;
        }
        if ((this.style & 0x1000000) != 0x0) {
            bits |= 0x300;
        }
        if ((this.style & 0x20000) != 0x0) {
            bits |= 0x200;
        }
        if ((this.style & 0x40) != 0x0) {
            bits |= 0x2000;
        }
        if ((this.style & 0x8) != 0x0) {
            return bits | 0x0 | 0x10000;
        }
        if ((this.style & 0x20) != 0x0) {
            return bits | 0x2 | 0x10000;
        }
        if ((this.style & 0x10) != 0x0) {
            return bits | 0x4;
        }
        if ((this.style & 0x2) != 0x0) {
            return bits | 0x1000 | 0x2 | 0x10000;
        }
        if ((this.style & 0x400000) != 0x0) {
            return bits | 0xE | 0x10000;
        }
        return bits | 0x0 | 0x10000;
    }
    
    @Override
    TCHAR windowClass() {
        return Button.ButtonClass;
    }
    
    @Override
    long windowProc() {
        return Button.ButtonProc;
    }
    
    @Override
    LRESULT wmColorChild(final long wParam, final long lParam) {
        if (this.isRadioOrCheck()) {
            return super.wmColorChild(wParam, lParam);
        }
        return this.parent.wmColorChild(wParam, lParam);
    }
    
    @Override
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_GETDLGCODE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x4) != 0x0) {
            return new LRESULT(256L);
        }
        return result;
    }
    
    @Override
    LRESULT WM_GETOBJECT(final long wParam, final long lParam) {
        if ((this.style & 0x10) != 0x0 && this.accessible == null) {
            this.accessible = this.new_Accessible(this);
        }
        return super.WM_GETOBJECT(wParam, lParam);
    }
    
    @Override
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KILLFOCUS(wParam, lParam);
        if ((this.style & 0x8) != 0x0 && this.getDefault()) {
            this.menuShell().setDefaultButton(null, false);
        }
        return result;
    }
    
    @Override
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        if (this.ignoreMouse) {
            return null;
        }
        return super.WM_LBUTTONDOWN(wParam, lParam);
    }
    
    @Override
    LRESULT WM_LBUTTONUP(final long wParam, final long lParam) {
        if (this.ignoreMouse) {
            return null;
        }
        return super.WM_LBUTTONUP(wParam, lParam);
    }
    
    @Override
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        int bits = 0;
        if ((this.style & 0x10) != 0x0) {
            bits = OS.GetWindowLong(this.handle, -16);
        }
        final LRESULT result = super.WM_SETFOCUS(wParam, lParam);
        if ((this.style & 0x10) != 0x0) {
            OS.SetWindowLong(this.handle, -16, bits);
        }
        if ((this.style & 0x8) != 0x0) {
            this.menuShell().setDefaultButton(this, false);
        }
        return result;
    }
    
    @Override
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0xA) != 0x0 && this.imageList != null && this.text.length() != 0) {
            final BUTTON_IMAGELIST buttonImageList = new BUTTON_IMAGELIST();
            OS.SendMessage(this.handle, 5635, 0L, buttonImageList);
            buttonImageList.uAlign = 0;
            buttonImageList.margin_left = this.computeLeftMargin();
            buttonImageList.margin_right = 4;
            OS.SendMessage(this.handle, 5634, 0L, buttonImageList);
        }
        return result;
    }
    
    @Override
    LRESULT WM_SYSCOLORCHANGE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SYSCOLORCHANGE(wParam, lParam);
        if (result != null) {
            return result;
        }
        return result;
    }
    
    @Override
    LRESULT WM_UPDATEUISTATE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_UPDATEUISTATE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x32) != 0x0) {
            final boolean bl;
            boolean redraw = bl = (this.findImageControl() != null);
            if (!redraw) {
                if ((this.state & 0x100) != 0x0 && OS.IsAppThemed()) {
                    boolean b = false;
                    if (this.findThemeControl() == null) {
                        b = false;
                    }
                    redraw = b;
                }
                if (!redraw) {
                    boolean b2 = false;
                    if (this.findBackgroundControl() == null) {
                        b2 = false;
                    }
                    redraw = b2;
                }
            }
            if (redraw) {
                OS.InvalidateRect(this.handle, (RECT)null, false);
                final long code = OS.DefWindowProc(this.handle, 296, wParam, lParam);
                return new LRESULT(code);
            }
        }
        if ((this.style & 0xA) != 0x0 && (this.hooks(9) || this.filters(9) || this.customDrawing())) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        return result;
    }
    
    @Override
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        final int code = OS.HIWORD(wParam);
        switch (code) {
            case 0:
            case 5: {
                if ((this.style & 0x22) != 0x0) {
                    this.setSelection(!this.getSelection());
                }
                else if ((this.style & 0x10) != 0x0) {
                    if ((this.parent.getStyle() & 0x400000) != 0x0) {
                        this.setSelection(!this.getSelection());
                    }
                    else {
                        this.selectRadio();
                    }
                }
                this.sendSelectionEvent(13);
                break;
            }
        }
        return super.wmCommandChild(wParam, lParam);
    }
    
    private int getCheckboxTextOffset(final long hdc) {
        int result = 0;
        final SIZE size = new SIZE();
        if (OS.IsAppThemed()) {
            OS.GetThemePartSize(this.display.hButtonTheme(), hdc, 3, 1, (RECT)null, 1, size);
            result += size.cx;
        }
        else {
            result += DPIUtil.autoScaleUpUsingNativeDPI(13);
        }
        OS.GetTextExtentPoint32(hdc, (char[])Button.STRING_WITH_ZERO_CHAR, 1, size);
        return result += size.cx / 2;
    }
    
    @Override
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        Label_1069: {
            switch (hdr.code) {
                case -12: {
                    final NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMCUSTOMDRAW.sizeof);
                    switch (nmcd.dwDrawStage) {
                        case 1: {
                            if (this.customBackgroundDrawing()) {
                                int pixel = this.background;
                                if ((nmcd.uItemState & 0x1) != 0x0) {
                                    pixel = this.getDifferentColor(this.background);
                                }
                                else if ((nmcd.uItemState & 0x40) != 0x0) {
                                    pixel = this.getSlightlyDifferentColor(this.background);
                                }
                                if ((this.style & 0x2) != 0x0 && this.isChecked()) {
                                    pixel = this.getDifferentColor(this.background);
                                }
                                final long brush = OS.CreateSolidBrush(pixel);
                                int inset = 2;
                                int radius = 3;
                                if (this.useDarkModeExplorerTheme && OS.WIN32_BUILD >= 22000) {
                                    inset = 1;
                                    radius = 4;
                                }
                                int l = nmcd.left + inset;
                                int t = nmcd.top + inset;
                                final int r = nmcd.right - inset;
                                final int b = nmcd.bottom - inset;
                                if (OS.WIN32_BUILD >= 22000) {
                                    OS.SaveDC(nmcd.hdc);
                                    OS.SelectObject(nmcd.hdc, brush);
                                    OS.SelectObject(nmcd.hdc, OS.GetStockObject(8));
                                    OS.RoundRect(nmcd.hdc, ++l, ++t, r, b, radius, radius);
                                    OS.RestoreDC(nmcd.hdc, -1);
                                }
                                else {
                                    final RECT rect = new RECT(l, t, r, b);
                                    OS.FillRect(nmcd.hdc, rect, brush);
                                }
                                OS.DeleteObject(brush);
                            }
                            if (this.customForegroundDrawing()) {
                                final int radioOrCheckTextPadding = this.getCheckboxTextOffset(nmcd.hdc);
                                final int border = this.isRadioOrCheck() ? 0 : 3;
                                int left = nmcd.left + border;
                                final int right = nmcd.right - border;
                                if (this.image != null) {
                                    final GCData data = new GCData();
                                    data.device = this.display;
                                    final GC gc = GC.win32_new(nmcd.hdc, data);
                                    final int margin = this.computeLeftMargin();
                                    final int imageWidth = this.image.getBoundsInPixels().width;
                                    left += imageWidth + (this.isRadioOrCheck() ? 8 : 4);
                                    final int x = margin + (this.isRadioOrCheck() ? radioOrCheckTextPadding : 3);
                                    final int y = Math.max(0, (nmcd.bottom - this.image.getBoundsInPixels().height) / 2);
                                    gc.drawImage(this.image, DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(y));
                                    gc.dispose();
                                }
                                final int n = this.isRadioOrCheck() ? radioOrCheckTextPadding : 0;
                                final RECT textRect = new RECT();
                                OS.SetRect(textRect, left += n, nmcd.top + border, right, nmcd.bottom - border);
                                final char[] buffer = this.text.toCharArray();
                                int flags = 0;
                                if ((this.style & 0x40) != 0x0) {
                                    flags |= 0x10;
                                    if (!this.isRadioOrCheck() && this.image != null) {
                                        final RECT rect2 = textRect;
                                        rect2.right -= 4;
                                    }
                                }
                                else {
                                    flags |= 0x20;
                                }
                                OS.DrawText(nmcd.hdc, (char[])buffer, buffer.length, textRect, flags | 0x400);
                                OS.OffsetRect(textRect, 0, Math.max(0, (nmcd.bottom - textRect.bottom - border) / 2));
                                if (this.image != null) {
                                    flags |= 0x0;
                                    if (!this.isRadioOrCheck()) {
                                        OS.OffsetRect(textRect, Math.max(4, (right - textRect.right) / 2 + 1), 0);
                                    }
                                }
                                else if ((this.style & 0x4000) != 0x0) {
                                    flags |= 0x0;
                                }
                                else if ((this.style & 0x20000) != 0x0) {
                                    flags |= 0x2;
                                    OS.OffsetRect(textRect, right - textRect.right, 0);
                                }
                                else {
                                    flags |= 0x1;
                                    OS.OffsetRect(textRect, (right - textRect.right) / 2, 0);
                                }
                                OS.SetBkMode(nmcd.hdc, 1);
                                OS.SetTextColor(nmcd.hdc, this.foreground);
                                OS.DrawText(nmcd.hdc, (char[])buffer, buffer.length, textRect, flags);
                                if ((nmcd.uItemState & 0x10) != 0x0) {
                                    final RECT focusRect = new RECT();
                                    if (this.isRadioOrCheck()) {
                                        if (this.text.length() > 0) {
                                            OS.SetRect(focusRect, textRect.left - 1, textRect.top, Math.min(nmcd.right, textRect.right + 1), Math.min(nmcd.bottom, textRect.bottom + 1));
                                        }
                                        else {
                                            OS.SetRect(focusRect, nmcd.left + 1 + radioOrCheckTextPadding, nmcd.top, nmcd.right - 2, nmcd.bottom - 1);
                                        }
                                    }
                                    else {
                                        OS.SetRect(focusRect, nmcd.left + 4, nmcd.top + 4, nmcd.right - 4, nmcd.bottom - 4);
                                    }
                                    OS.DrawFocusRect(nmcd.hdc, focusRect);
                                }
                                return new LRESULT(4L);
                            }
                            return new LRESULT(0L);
                        }
                        default: {
                            break Label_1069;
                        }
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(hdr, wParam, lParam);
    }
    
    static int getThemeStateId(final int style, final boolean pressed, final boolean enabled) {
        int direction = style & 0x24480;
        if ((style & 0x8000000) != 0x0) {
            if (direction == 16384) {
                direction = 131072;
            }
            else if (direction == 131072) {
                direction = 16384;
            }
        }
        boolean hot = false;
        if (OS.WIN32_BUILD >= 22000 && !pressed && enabled) {
            hot = true;
        }
        if (hot) {
            switch (direction) {
                case 128: {
                    return 2;
                }
                case 1024: {
                    return 6;
                }
                case 16384: {
                    return 10;
                }
                case 131072: {
                    return 14;
                }
            }
        }
        if (pressed) {
            switch (direction) {
                case 128: {
                    return 3;
                }
                case 1024: {
                    return 7;
                }
                case 16384: {
                    return 11;
                }
                case 131072: {
                    return 15;
                }
            }
        }
        if (!enabled) {
            switch (direction) {
                case 128: {
                    return 4;
                }
                case 1024: {
                    return 8;
                }
                case 16384: {
                    return 12;
                }
                case 131072: {
                    return 16;
                }
            }
        }
        switch (direction) {
            case 128: {
                return 1;
            }
            case 1024: {
                return 5;
            }
            case 16384: {
                return 9;
            }
            case 131072: {
                return 13;
            }
            default: {
                return 9;
            }
        }
    }
    
    @Override
    LRESULT wmDrawChild(final long wParam, final long lParam) {
        if ((this.style & 0x4) == 0x0) {
            return super.wmDrawChild(wParam, lParam);
        }
        final DRAWITEMSTRUCT struct = new DRAWITEMSTRUCT();
        OS.MoveMemory(struct, lParam, DRAWITEMSTRUCT.sizeof);
        final RECT rect = new RECT();
        OS.SetRect(rect, struct.left, struct.top, struct.right, struct.bottom);
        if (OS.IsAppThemed()) {
            final boolean pressed = (struct.itemState & 0x1) != 0x0;
            final boolean enabled = this.getEnabled();
            final int iStateId = getThemeStateId(this.style, pressed, enabled);
            OS.DrawThemeBackground(this.display.hScrollBarTheme(), struct.hDC, 1, iStateId, rect, (RECT)null);
        }
        else {
            int uState = 2;
            switch (this.style & 0x24480) {
                case 128: {
                    uState = 0;
                    break;
                }
                case 1024: {
                    uState = 1;
                    break;
                }
                case 16384: {
                    uState = 2;
                    break;
                }
                case 131072: {
                    uState = 3;
                    break;
                }
            }
            if (!this.getEnabled()) {
                uState |= 0x100;
            }
            if ((this.style & 0x800000) == 0x800000) {
                uState |= 0x4000;
            }
            if ((struct.itemState & 0x1) != 0x0) {
                uState |= 0x200;
            }
            OS.DrawFrameControl(struct.hDC, rect, 3, uState);
        }
        return null;
    }
    
    static {
        Button.COMMAND_LINK = false;
        STRING_WITH_ZERO_CHAR = new char[] { '0' };
        ButtonClass = new TCHAR(0, "BUTTON", true);
        final long hBitmap = OS.LoadBitmap(0L, 32759L);
        if (hBitmap == 0L) {
            CHECK_WIDTH = OS.GetSystemMetrics(2);
            CHECK_HEIGHT = OS.GetSystemMetrics(20);
        }
        else {
            final BITMAP bitmap = new BITMAP();
            OS.GetObject(hBitmap, BITMAP.sizeof, bitmap);
            OS.DeleteObject(hBitmap);
            CHECK_WIDTH = bitmap.bmWidth / 4;
            CHECK_HEIGHT = bitmap.bmHeight / 3;
        }
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Button.ButtonClass, lpWndClass);
        ButtonProc = lpWndClass.lpfnWndProc;
    }
}
