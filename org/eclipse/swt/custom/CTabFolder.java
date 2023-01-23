//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.accessibility.*;

public class CTabFolder extends Composite
{
    public int marginWidth;
    public int marginHeight;
    @Deprecated
    public int MIN_TAB_WIDTH;
    @Deprecated
    public static RGB borderInsideRGB;
    @Deprecated
    public static RGB borderMiddleRGB;
    @Deprecated
    public static RGB borderOutsideRGB;
    boolean onBottom;
    boolean single;
    boolean simple;
    int fixedTabHeight;
    int tabHeight;
    int minChars;
    boolean borderVisible;
    CTabFolderRenderer renderer;
    CTabItem[] items;
    int firstIndex;
    int selectedIndex;
    int[] priority;
    boolean mru;
    Listener listener;
    boolean ignoreTraverse;
    boolean useDefaultRenderer;
    CTabFolder2Listener[] folderListeners;
    CTabFolderListener[] tabListeners;
    Image selectionBgImage;
    Color[] selectionGradientColors;
    int[] selectionGradientPercents;
    boolean selectionGradientVertical;
    Color selectionForeground;
    Color selectionBackground;
    Color[] gradientColors;
    int[] gradientPercents;
    boolean gradientVertical;
    boolean showUnselectedImage;
    boolean showClose;
    boolean showUnselectedClose;
    boolean showMin;
    boolean minimized;
    boolean showMax;
    boolean maximized;
    ToolBar minMaxTb;
    ToolItem maxItem;
    ToolItem minItem;
    Image maxImage;
    Image minImage;
    boolean hoverTb;
    Rectangle hoverRect;
    boolean hovering;
    boolean hoverTimerRunning;
    boolean highlight;
    boolean highlightEnabled;
    boolean showChevron;
    Menu showMenu;
    ToolBar chevronTb;
    ToolItem chevronItem;
    int chevronCount;
    boolean chevronVisible;
    Image chevronImage;
    Control topRight;
    int topRightAlignment;
    boolean ignoreResize;
    Control[] controls;
    int[] controlAlignments;
    Rectangle[] controlRects;
    Rectangle[] bkImageBounds;
    Image[] controlBkImages;
    int updateFlags;
    static final int REDRAW = 2;
    static final int REDRAW_TABS = 4;
    static final int UPDATE_TAB_HEIGHT = 8;
    Runnable updateRun;
    boolean inDispose;
    Point oldSize;
    Font oldFont;
    static final int DEFAULT_WIDTH = 64;
    static final int DEFAULT_HEIGHT = 64;
    static final int SELECTION_FOREGROUND = 24;
    static final int SELECTION_BACKGROUND = 25;
    static final int FOREGROUND = 21;
    static final int BACKGROUND = 22;
    static final int SPACING = 3;
    
    public CTabFolder(final Composite parent, final int style) {
        super(parent, checkStyle(parent, style));
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.MIN_TAB_WIDTH = 4;
        this.onBottom = false;
        this.single = false;
        this.simple = true;
        this.fixedTabHeight = -1;
        this.minChars = 20;
        this.borderVisible = false;
        this.items = new CTabItem[0];
        this.firstIndex = -1;
        this.selectedIndex = -1;
        this.priority = new int[0];
        this.mru = false;
        this.folderListeners = new CTabFolder2Listener[0];
        this.tabListeners = new CTabFolderListener[0];
        this.showUnselectedImage = true;
        this.showClose = false;
        this.showUnselectedClose = true;
        this.showMin = false;
        this.minimized = false;
        this.showMax = false;
        this.maximized = false;
        this.hoverRect = new Rectangle(0, 0, 0, 0);
        this.highlightEnabled = true;
        this.showChevron = false;
        this.chevronVisible = true;
        this.topRightAlignment = 131072;
        this.inDispose = false;
        this.init(style);
    }
    
    void init(final int style) {
        super.setLayout(new CTabFolderLayout());
        final int style2 = super.getStyle();
        this.oldFont = this.getFont();
        this.onBottom = ((style2 & 0x400) != 0x0);
        this.showClose = ((style2 & 0x40) != 0x0);
        this.single = ((style2 & 0x4) != 0x0);
        this.borderVisible = ((style & 0x800) != 0x0);
        final Display display = this.getDisplay();
        this.selectionForeground = display.getSystemColor(24);
        this.selectionBackground = display.getSystemColor(25);
        this.renderer = new CTabFolderRenderer(this);
        this.useDefaultRenderer = true;
        this.controls = new Control[0];
        this.controlAlignments = new int[0];
        this.controlRects = new Rectangle[0];
        this.controlBkImages = new Image[0];
        this.updateTabHeight(false);
        this.listener = (event -> {
            switch (event.type) {
                case 12: {
                    this.onDispose(event);
                    break;
                }
                case 29: {
                    this.onDragDetect(event);
                    break;
                }
                case 15: {
                    this.onFocus(event);
                    break;
                }
                case 16: {
                    this.onFocus(event);
                    break;
                }
                case 1: {
                    this.onKeyDown(event);
                    break;
                }
                case 35: {
                    this.onMenuDetect(event);
                    break;
                }
                case 8: {
                    this.onMouseDoubleClick(event);
                    break;
                }
                case 3: {
                    this.onMouse(event);
                    break;
                }
                case 6: {
                    this.onMouse(event);
                    break;
                }
                case 7: {
                    this.onMouse(event);
                    break;
                }
                case 32: {
                    this.onMouse(event);
                    break;
                }
                case 5: {
                    this.onMouse(event);
                    break;
                }
                case 4: {
                    this.onMouse(event);
                    break;
                }
                case 9: {
                    this.onPaint(event);
                    break;
                }
                case 11: {
                    this.onResize(event);
                    break;
                }
                case 31: {
                    this.onTraverse(event);
                    break;
                }
                case 13: {
                    this.onSelection(event);
                    break;
                }
                case 26: {
                    this.onActivate(event);
                    break;
                }
                case 27: {
                    this.onDeactivate(event);
                    break;
                }
            }
            return;
        });
        final int[] folderEvents;
        final int[] array2;
        final int[] array = array2 = (folderEvents = new int[] { 12, 29, 15, 16, 1, 35, 8, 3, 6, 7, 32, 5, 4, 9, 11, 31, 26, 27 });
        for (final int folderEvent : array2) {
            this.addListener(folderEvent, this.listener);
        }
        this.initAccessible();
    }
    
    void onDeactivate(final Event event) {
        if (!this.highlightEnabled) {
            return;
        }
        this.highlight = false;
        this.redraw();
    }
    
    void onActivate(final Event event) {
        if (!this.highlightEnabled) {
            return;
        }
        this.highlight = true;
        this.redraw();
    }
    
    static int checkStyle(final Composite parent, int style) {
        final int mask = 109053126;
        style &= 0x68004C6;
        if ((style & 0x80) != 0x0) {
            style &= 0xFFFFFBFF;
        }
        if ((style & 0x2) != 0x0) {
            style &= 0xFFFFFFFB;
        }
        style |= 0x100000;
        if ((style & 0x4000000) != 0x0) {
            return style;
        }
        if ((parent.getStyle() & 0x8000000) != 0x0 && (style & 0x2000000) == 0x0) {
            return style;
        }
        return style | 0x20000000;
    }
    
    public void addCTabFolder2Listener(final CTabFolder2Listener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        final CTabFolder2Listener[] newListeners = new CTabFolder2Listener[this.folderListeners.length + 1];
        System.arraycopy(this.folderListeners, 0, newListeners, 0, this.folderListeners.length);
        (this.folderListeners = newListeners)[this.folderListeners.length - 1] = listener;
    }
    
    @Deprecated
    public void addCTabFolderListener(final CTabFolderListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        final CTabFolderListener[] newTabListeners = new CTabFolderListener[this.tabListeners.length + 1];
        System.arraycopy(this.tabListeners, 0, newTabListeners, 0, this.tabListeners.length);
        (this.tabListeners = newTabListeners)[this.tabListeners.length - 1] = listener;
        if (!this.showClose) {
            this.showClose = true;
            this.updateFolder(2);
        }
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
    
    Rectangle[] computeControlBounds(final Point size, final boolean[][] position) {
        if (this.controls == null || this.controls.length == 0) {
            return new Rectangle[0];
        }
        final Rectangle[] rects = new Rectangle[this.controls.length];
        for (int i = 0; i < rects.length; ++i) {
            rects[i] = new Rectangle(0, 0, 0, 0);
        }
        final Rectangle trim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
        final int borderRight = trim.width + trim.x;
        final int borderLeft = -trim.x;
        final int borderBottom = trim.height + trim.y;
        final int borderTop = -trim.y;
        final Point[] tabControlSize = new Point[this.controls.length];
        final boolean[] overflow = new boolean[this.controls.length];
        int leftWidth = 0;
        int x = borderLeft + 3;
        int rightWidth = 0;
        int allWidth = 0;
        boolean spacingRight = false;
        for (int j = 0; j < this.controls.length; ++j) {
            final Point[] array = tabControlSize;
            final int n = j;
            final Point point = (!this.controls[j].isDisposed() && this.controls[j].getVisible()) ? this.controls[j].computeSize(-1, -1) : new Point(0, 0);
            array[n] = point;
            final Point ctrlSize = point;
            final int alignment = this.controlAlignments[j];
            if ((alignment & 0x4000) != 0x0) {
                rects[j].width = ctrlSize.x;
                rects[j].height = this.getControlHeight(ctrlSize);
                rects[j].x = x;
                rects[j].y = this.getControlY(size, rects, borderBottom, borderTop, j);
                x += ctrlSize.x;
                leftWidth += ctrlSize.x;
            }
            else {
                if ((alignment & 0x40) == 0x0 && ctrlSize.x > 0) {
                    spacingRight = true;
                }
                if ((alignment & 0x44) == 0x0) {
                    rightWidth += ctrlSize.x;
                }
                allWidth += ctrlSize.x;
            }
        }
        if (leftWidth > 0) {
            leftWidth += 6;
        }
        int itemWidth = 0;
        for (final CTabItem item : this.items) {
            if (item.showing) {
                itemWidth += item.width;
            }
        }
        final int maxWidth = size.x - borderLeft - leftWidth - borderRight;
        int availableWidth = Math.max(0, maxWidth - itemWidth - rightWidth);
        if (spacingRight) {
            availableWidth -= 6;
        }
        x = size.x - borderRight - 3;
        if (itemWidth + allWidth <= maxWidth) {
            for (int k = 0; k < this.controls.length; ++k) {
                final int alignment2 = this.controlAlignments[k];
                if ((alignment2 & 0x20000) != 0x0) {
                    final Point ctrlSize2 = tabControlSize[k];
                    x -= ctrlSize2.x;
                    rects[k].width = ctrlSize2.x;
                    rects[k].height = this.getControlHeight(ctrlSize2);
                    rects[k].x = x;
                    rects[k].y = this.getControlY(size, rects, borderBottom, borderTop, k);
                    if ((alignment2 & 0x44) != 0x0) {
                        availableWidth -= ctrlSize2.x;
                    }
                }
                if (tabControlSize[k].y >= this.tabHeight && this.fixedTabHeight == -1) {
                    overflow[k] = true;
                }
            }
        }
        else {
            for (int k = 0; k < this.controls.length; ++k) {
                final int alignment2 = this.controlAlignments[k];
                final Point ctrlSize2 = tabControlSize[k];
                if ((alignment2 & 0x20000) != 0x0) {
                    if ((alignment2 & 0x44) == 0x0) {
                        x -= ctrlSize2.x;
                        rects[k].width = ctrlSize2.x;
                        rects[k].height = this.getControlHeight(ctrlSize2);
                        rects[k].x = x;
                        rects[k].y = this.getControlY(size, rects, borderBottom, borderTop, k);
                    }
                    else if ((alignment2 & 0x40) != 0x0 && ctrlSize2.x < availableWidth) {
                        x -= ctrlSize2.x;
                        rects[k].width = ctrlSize2.x;
                        rects[k].height = this.getControlHeight(ctrlSize2);
                        rects[k].x = x;
                        rects[k].y = this.getControlY(size, rects, borderBottom, borderTop, k);
                        availableWidth -= ctrlSize2.x;
                    }
                    else if ((alignment2 & 0x4) != 0x0 && (alignment2 & 0x40) == 0x0) {
                        rects[k].width = 0;
                        rects[k].height = this.getControlHeight(ctrlSize2);
                        rects[k].x = x;
                        rects[k].y = this.getControlY(size, rects, borderBottom, borderTop, k);
                    }
                    else if ((alignment2 & 0x40) != 0x0) {
                        overflow[k] = true;
                    }
                }
            }
        }
        if (availableWidth > 0) {
            int fillCount = 0;
            for (int l = 0; l < this.controls.length; ++l) {
                final int alignment3 = this.controlAlignments[l];
                if ((alignment3 & 0x20000) != 0x0 && (alignment3 & 0x4) != 0x0 && !overflow[l]) {
                    ++fillCount;
                }
            }
            if (fillCount != 0) {
                final int extraSpace = availableWidth / fillCount;
                int addedSpace = 0;
                for (int m = 0; m < this.controls.length; ++m) {
                    final int alignment4 = this.controlAlignments[m];
                    if ((alignment4 & 0x20000) != 0x0) {
                        if ((alignment4 & 0x4) != 0x0 && !overflow[m]) {
                            final Rectangle rectangle3;
                            final Rectangle rectangle = rectangle3 = rects[m];
                            rectangle3.width += extraSpace;
                            addedSpace += extraSpace;
                        }
                        if (!overflow[m]) {
                            final Rectangle rectangle4;
                            final Rectangle rectangle2 = rectangle4 = rects[m];
                            rectangle4.x -= addedSpace;
                        }
                    }
                }
            }
        }
        final Rectangle bodyTrim = this.renderer.computeTrim(-1, 0, 0, 0, 0, 0);
        final int bodyRight = bodyTrim.width + bodyTrim.x;
        final int bodyLeft = -bodyTrim.x;
        final int bodyWidth = size.x - bodyLeft - bodyRight;
        x = size.x - bodyRight;
        int y = this.onBottom ? (this.getSize().y - this.getTabHeight() + 2 * bodyTrim.y) : (-bodyTrim.y);
        availableWidth = bodyWidth;
        int maxHeight = 0;
        for (int i2 = 0; i2 < this.controls.length; ++i2) {
            Point ctrlSize3 = tabControlSize[i2];
            if (overflow[i2]) {
                if (availableWidth > ctrlSize3.x) {
                    x -= ctrlSize3.x;
                    rects[i2].width = ctrlSize3.x;
                    rects[i2].y = (this.onBottom ? (y - ctrlSize3.y) : y);
                    rects[i2].height = ctrlSize3.y;
                    rects[i2].x = x;
                    availableWidth -= ctrlSize3.x;
                    maxHeight = Math.max(maxHeight, ctrlSize3.y);
                }
                else {
                    x = size.x - bodyRight;
                    y += maxHeight;
                    maxHeight = 0;
                    availableWidth = bodyWidth;
                    if (availableWidth > ctrlSize3.x) {
                        --i2;
                    }
                    else {
                        ctrlSize3 = (this.controls[i2].isDisposed() ? new Point(0, 0) : this.controls[i2].computeSize(bodyWidth, -1));
                        rects[i2].width = bodyWidth;
                        rects[i2].y = (this.onBottom ? (y - ctrlSize3.y) : y);
                        rects[i2].height = ctrlSize3.y;
                        rects[i2].x = size.x - ctrlSize3.x - bodyRight;
                        y += ctrlSize3.y;
                    }
                }
            }
        }
        if (this.showChevron) {
            int i2;
            int lastIndex;
            for (i2 = 0, lastIndex = -1; i2 < this.priority.length && this.items[this.priority[i2]].showing; lastIndex = Math.max(lastIndex, this.priority[i2++])) {}
            if (lastIndex == -1) {
                lastIndex = this.selectedIndex;
            }
            if (lastIndex != -1) {
                final CTabItem lastItem = this.items[lastIndex];
                int w = lastItem.x + lastItem.width + 3;
                if (!this.simple && lastIndex == this.selectedIndex) {
                    w -= this.renderer.curveIndent - 7;
                }
                rects[this.controls.length - 1].x = w;
            }
        }
        if (position != null) {
            position[0] = overflow;
        }
        return rects;
    }
    
    int getControlHeight(final Point ctrlSize) {
        return (this.fixedTabHeight == -1) ? Math.max(this.tabHeight - 1, ctrlSize.y) : ctrlSize.y;
    }
    
    @Override
    public Rectangle computeTrim(final int x, final int y, final int width, final int height) {
        this.checkWidget();
        final Rectangle trim = this.renderer.computeTrim(-1, 0, x, y, width, height);
        final Point size = new Point(width, height);
        final int wrapHeight = this.getWrappedHeight(size);
        if (this.onBottom) {
            final Rectangle rectangle4;
            final Rectangle rectangle = rectangle4 = trim;
            rectangle4.height += wrapHeight;
        }
        else {
            final Rectangle rectangle5;
            final Rectangle rectangle2 = rectangle5 = trim;
            rectangle5.y -= wrapHeight;
            final Rectangle rectangle6;
            final Rectangle rectangle3 = rectangle6 = trim;
            rectangle6.height += wrapHeight;
        }
        return trim;
    }
    
    Image createButtonImage(final Display display, final int button) {
        final GC tempGC = new GC(this);
        final Point size = this.renderer.computeSize(button, 0, tempGC, -1, -1);
        tempGC.dispose();
        final Rectangle trim = this.renderer.computeTrim(button, 0, 0, 0, 0, 0);
        Image image = new Image(display, size.x - trim.width, size.y - trim.height);
        final GC gc = new GC(image);
        final Color transColor = this.renderer.parent.getBackground();
        gc.setBackground(transColor);
        gc.fillRectangle(image.getBounds());
        this.renderer.draw(button, 0, new Rectangle(trim.x, trim.y, size.x, size.y), gc);
        gc.dispose();
        final ImageData imageData = image.getImageData(DPIUtil.getDeviceZoom());
        imageData.transparentPixel = imageData.palette.getPixel(transColor.getRGB());
        image.dispose();
        image = new Image(display, new DPIUtil.AutoScaleImageDataProvider(display, imageData, DPIUtil.getDeviceZoom()));
        return image;
    }
    
    void createItem(final CTabItem item, final int index) {
        if (0 > index || index > this.getItemCount()) {
            SWT.error(6);
        }
        item.parent = this;
        final CTabItem[] newItems = new CTabItem[this.items.length + 1];
        System.arraycopy(this.items, 0, newItems, 0, index);
        newItems[index] = item;
        System.arraycopy(this.items, index, newItems, index + 1, this.items.length - index);
        this.items = newItems;
        if (this.selectedIndex >= index) {
            ++this.selectedIndex;
        }
        final int[] newPriority = new int[this.priority.length + 1];
        int next = 0;
        int priorityIndex = this.priority.length;
        for (final int element : this.priority) {
            if (!this.mru && element == index) {
                priorityIndex = next++;
            }
            newPriority[next++] = ((element >= index) ? (element + 1) : element);
        }
        newPriority[priorityIndex] = index;
        this.priority = newPriority;
        if (this.items.length == 1) {
            this.updateFolder(10);
        }
        else {
            this.updateFolder(4);
        }
    }
    
    void destroyItem(final CTabItem item) {
        if (this.inDispose) {
            return;
        }
        final int index = this.indexOf(item);
        if (index == -1) {
            return;
        }
        if (this.items.length == 1) {
            this.items = new CTabItem[0];
            this.priority = new int[0];
            this.firstIndex = -1;
            this.selectedIndex = -1;
            final Control control = item.control;
            if (control != null && !control.isDisposed()) {
                control.setVisible(false);
            }
            this.setToolTipText(null);
            this.updateButtons();
            this.setButtonBounds();
            this.redraw();
            return;
        }
        final CTabItem[] newItems = new CTabItem[this.items.length - 1];
        System.arraycopy(this.items, 0, newItems, 0, index);
        System.arraycopy(this.items, index + 1, newItems, index, this.items.length - index - 1);
        this.items = newItems;
        final int[] newPriority = new int[this.priority.length - 1];
        int next = 0;
        for (final int element : this.priority) {
            if (element != index) {
                newPriority[next++] = ((element > index) ? (element - 1) : element);
            }
        }
        this.priority = newPriority;
        if (this.selectedIndex == index) {
            final Control control2 = item.getControl();
            this.selectedIndex = -1;
            final int nextSelection = this.mru ? this.priority[0] : Math.max(0, index - 1);
            this.setSelection(nextSelection, true);
            if (control2 != null && !control2.isDisposed()) {
                control2.setVisible(false);
            }
        }
        else if (this.selectedIndex > index) {
            --this.selectedIndex;
        }
        this.requestLayout();
        this.updateFolder(12);
    }
    
    public boolean getBorderVisible() {
        this.checkWidget();
        return this.borderVisible;
    }
    
    ToolBar getChevron() {
        if (this.chevronTb == null) {
            this.chevronTb = new ToolBar(this, 8388608);
            this.initAccessibleChevronTb();
            this.addTabControl(this.chevronTb, 131072, -1, false);
        }
        if (this.chevronItem == null) {
            (this.chevronItem = new ToolItem(this.chevronTb, 8)).setToolTipText(SWT.getMessage("SWT_ShowList"));
            this.chevronItem.addListener(13, this.listener);
        }
        return this.chevronTb;
    }
    
    boolean getChevronVisible() {
        this.checkWidget();
        return this.chevronVisible;
    }
    
    @Override
    public Rectangle getClientArea() {
        this.checkWidget();
        final Rectangle trim = this.renderer.computeTrim(-1, 4, 0, 0, 0, 0);
        final Point size = this.getSize();
        final int wrapHeight = this.getWrappedHeight(size);
        if (this.onBottom) {
            final Rectangle rectangle4;
            final Rectangle rectangle = rectangle4 = trim;
            rectangle4.height += wrapHeight;
        }
        else {
            final Rectangle rectangle5;
            final Rectangle rectangle2 = rectangle5 = trim;
            rectangle5.y -= wrapHeight;
            final Rectangle rectangle6;
            final Rectangle rectangle3 = rectangle6 = trim;
            rectangle6.height += wrapHeight;
        }
        if (this.minimized) {
            return new Rectangle(-trim.x, -trim.y, 0, 0);
        }
        final int width = size.x - trim.width;
        final int height = size.y - trim.height;
        return new Rectangle(-trim.x, -trim.y, width, height);
    }
    
    public CTabItem getItem(final int index) {
        if (index < 0 || index >= this.items.length) {
            SWT.error(6);
        }
        return this.items[index];
    }
    
    public CTabItem getItem(final Point pt) {
        if (this.items.length == 0) {
            return null;
        }
        this.runUpdate();
        final Point size = this.getSize();
        final Rectangle trim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
        if (size.x <= trim.width) {
            return null;
        }
        for (final int element : this.priority) {
            final CTabItem item = this.items[element];
            final Rectangle rect = item.getBounds();
            if (rect.contains(pt)) {
                return item;
            }
        }
        return null;
    }
    
    public int getItemCount() {
        return this.items.length;
    }
    
    public CTabItem[] getItems() {
        final CTabItem[] tabItems = new CTabItem[this.items.length];
        System.arraycopy(this.items, 0, tabItems, 0, this.items.length);
        return tabItems;
    }
    
    int getLeftItemEdge(final GC gc, final int part) {
        final Rectangle trim = this.renderer.computeTrim(part, 0, 0, 0, 0, 0);
        int x = -trim.x;
        int width = 0;
        for (int i = 0; i < this.controls.length; ++i) {
            if ((this.controlAlignments[i] & 0x4000) != 0x0 && !this.controls[i].isDisposed() && this.controls[i].getVisible()) {
                width += this.controls[i].computeSize(-1, -1).x;
            }
        }
        if (width != 0) {
            width += 6;
        }
        x += width;
        return Math.max(0, x);
    }
    
    char _findMnemonic(final String string) {
        if (string == null) {
            return '\0';
        }
        int index = 0;
        final int length = string.length();
        while (true) {
            if (index < length && string.charAt(index) != '&') {
                ++index;
            }
            else {
                if (++index >= length) {
                    return '\0';
                }
                if (string.charAt(index) != '&') {
                    return Character.toLowerCase(string.charAt(index));
                }
                if (++index >= length) {
                    return '\0';
                }
                continue;
            }
        }
    }
    
    String stripMnemonic(final String string) {
        int index = 0;
        final int length = string.length();
        while (true) {
            if (index < length && string.charAt(index) != '&') {
                ++index;
            }
            else {
                if (++index >= length) {
                    return string;
                }
                if (string.charAt(index) != '&') {
                    return string.substring(0, index - 1) + string.substring(index, length);
                }
                if (++index >= length) {
                    return string;
                }
                continue;
            }
        }
    }
    
    public boolean getMinimized() {
        this.checkWidget();
        return this.minimized;
    }
    
    public boolean getMinimizeVisible() {
        this.checkWidget();
        return this.showMin;
    }
    
    public int getMinimumCharacters() {
        this.checkWidget();
        return this.minChars;
    }
    
    public boolean getMaximized() {
        this.checkWidget();
        return this.maximized;
    }
    
    public boolean getMaximizeVisible() {
        this.checkWidget();
        return this.showMax;
    }
    
    public boolean getMRUVisible() {
        this.checkWidget();
        return this.mru;
    }
    
    public CTabFolderRenderer getRenderer() {
        this.checkWidget();
        return this.renderer;
    }
    
    int getRightItemEdge(final GC gc) {
        final Rectangle trim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
        int x = this.getSize().x - (trim.width + trim.x);
        int width = 0;
        for (int i = 0; i < this.controls.length; ++i) {
            final int align = this.controlAlignments[i];
            if ((align & 0x40) == 0x0 && (align & 0x4000) == 0x0 && !this.controls[i].isDisposed() && this.controls[i].getVisible()) {
                final Point rightSize = this.controls[i].computeSize(-1, -1);
                width += rightSize.x;
            }
        }
        if (width != 0) {
            width += 6;
        }
        x -= width;
        return Math.max(0, x);
    }
    
    public CTabItem getSelection() {
        if (this.selectedIndex == -1) {
            return null;
        }
        return this.items[this.selectedIndex];
    }
    
    public Color getSelectionBackground() {
        this.checkWidget();
        return this.selectionBackground;
    }
    
    public Color getSelectionForeground() {
        this.checkWidget();
        return this.selectionForeground;
    }
    
    public int getSelectionIndex() {
        return this.selectedIndex;
    }
    
    public boolean getSimple() {
        this.checkWidget();
        return this.simple;
    }
    
    public boolean getSingle() {
        this.checkWidget();
        return this.single;
    }
    
    @Override
    public int getStyle() {
        int style = super.getStyle();
        style &= 0xFFFFFB7F;
        style |= (this.onBottom ? 1024 : 128);
        style &= 0xFFFFFFF9;
        style |= (this.single ? 4 : 2);
        if (this.borderVisible) {
            style |= 0x800;
        }
        style &= 0xFFFFFFBF;
        if (this.showClose) {
            style |= 0x40;
        }
        return style;
    }
    
    public int getTabHeight() {
        this.checkWidget();
        if (this.fixedTabHeight != -1) {
            return this.fixedTabHeight;
        }
        return this.tabHeight - 1;
    }
    
    public int getTabPosition() {
        this.checkWidget();
        return this.onBottom ? 1024 : 128;
    }
    
    public Control getTopRight() {
        this.checkWidget();
        return this.topRight;
    }
    
    public int getTopRightAlignment() {
        this.checkWidget();
        return this.topRightAlignment;
    }
    
    public boolean getUnselectedCloseVisible() {
        this.checkWidget();
        return this.showUnselectedClose;
    }
    
    public boolean getUnselectedImageVisible() {
        this.checkWidget();
        return this.showUnselectedImage;
    }
    
    public int indexOf(final CTabItem item) {
        this.checkWidget();
        if (item == null) {
            SWT.error(4);
        }
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == item) {
                return i;
            }
        }
        return -1;
    }
    
    void initAccessible() {
        class lIIIllI extends AccessibleAdapter
        {
            final /* synthetic */ CTabFolder this$0;
            
            lIIIllI(final CTabFolder this$0) {
                this.this$0 = this$0;
            }
            
            public void getName(final AccessibleEvent e) {
                CTabItem item = null;
                final int childID = e.childID;
                if (childID == -1) {
                    if (this.this$0.selectedIndex != -1) {
                        item = this.this$0.items[this.this$0.selectedIndex];
                    }
                }
                else if (childID >= 0 && childID < this.this$0.items.length) {
                    item = this.this$0.items[childID];
                }
                e.result = ((item == null) ? null : this.this$0.stripMnemonic(item.getText()));
            }
            
            public void getHelp(final AccessibleEvent e) {
                String help = null;
                final int childID = e.childID;
                if (childID == -1) {
                    help = this.this$0.getToolTipText();
                }
                else if (childID >= 0 && childID < this.this$0.items.length) {
                    help = this.this$0.items[childID].getToolTipText();
                }
                e.result = help;
            }
            
            public void getKeyboardShortcut(final AccessibleEvent e) {
                String shortcut = null;
                final int childID = e.childID;
                if (childID >= 0 && childID < this.this$0.items.length) {
                    final String text = this.this$0.items[childID].getText();
                    if (text != null) {
                        final char mnemonic = this.this$0._findMnemonic(text);
                        if (mnemonic != '\0') {
                            shortcut = SWT.getMessage("SWT_Page_Mnemonic", new Object[] { mnemonic });
                        }
                    }
                }
                if (childID == -1) {
                    shortcut = SWT.getMessage("SWT_SwitchPage_Shortcut");
                }
                e.result = shortcut;
            }
        }
        class lllllI extends AccessibleControlAdapter
        {
            final /* synthetic */ CTabFolder this$0;
            
            lllllI(final CTabFolder this$0) {
                this.this$0 = this$0;
            }
            
            public void getChildAtPoint(final AccessibleControlEvent e) {
                final Point testPoint = this.this$0.toControl(e.x, e.y);
                int childID = -2;
                for (int i = 0; i < this.this$0.items.length; ++i) {
                    if (this.this$0.items[i].getBounds().contains(testPoint)) {
                        childID = i;
                        break;
                    }
                }
                if (childID == -2) {
                    final Rectangle bounds;
                    final Rectangle location;
                    final Rectangle rectangle = location = (bounds = this.this$0.getBounds());
                    final int n = 0;
                    rectangle.y = 0;
                    bounds.x = 0;
                    final Rectangle rectangle2 = location;
                    rectangle2.height -= this.this$0.getClientArea().height;
                    if (location.contains(testPoint)) {
                        childID = -1;
                    }
                }
                e.childID = childID;
            }
            
            public void getLocation(final AccessibleControlEvent e) {
                Rectangle location = null;
                Point pt = null;
                final int childID = e.childID;
                if (childID == -1) {
                    location = this.this$0.getBounds();
                    pt = this.this$0.getParent().toDisplay(location.x, location.y);
                }
                else {
                    if (childID >= 0 && childID < this.this$0.items.length && this.this$0.items[childID].showing && !this.this$0.items[childID].isDisposed()) {
                        location = this.this$0.items[childID].getBounds();
                    }
                    if (location != null) {
                        pt = this.this$0.toDisplay(location.x, location.y);
                    }
                }
                if (location != null && pt != null) {
                    e.x = pt.x;
                    e.y = pt.y;
                    e.width = location.width;
                    e.height = location.height;
                }
            }
            
            public void getChildCount(final AccessibleControlEvent e) {
                e.detail = this.this$0.items.length;
            }
            
            public void getDefaultAction(final AccessibleControlEvent e) {
                String action = null;
                final int childID = e.childID;
                if (childID >= 0 && childID < this.this$0.items.length) {
                    action = SWT.getMessage("SWT_Switch");
                }
                e.result = action;
            }
            
            public void getFocus(final AccessibleControlEvent e) {
                int childID = -2;
                if (this.this$0.isFocusControl()) {
                    if (this.this$0.selectedIndex == -1) {
                        childID = -1;
                    }
                    else {
                        childID = this.this$0.selectedIndex;
                    }
                }
                e.childID = childID;
            }
            
            public void getRole(final AccessibleControlEvent e) {
                int role = 0;
                final int childID = e.childID;
                if (childID == -1) {
                    role = 60;
                }
                else if (childID >= 0 && childID < this.this$0.items.length) {
                    role = 37;
                }
                e.detail = role;
            }
            
            public void getSelection(final AccessibleControlEvent e) {
                e.childID = ((this.this$0.selectedIndex == -1) ? -2 : this.this$0.selectedIndex);
            }
            
            public void getState(final AccessibleControlEvent e) {
                int state = 0;
                final int childID = e.childID;
                if (childID == -1) {
                    state = 0;
                }
                else if (childID >= 0 && childID < this.this$0.items.length) {
                    state = 2097152;
                    if (this.this$0.isFocusControl()) {
                        state |= 0x100000;
                    }
                    if (this.this$0.selectedIndex == childID) {
                        state |= 0x2;
                        if (this.this$0.isFocusControl()) {
                            state |= 0x4;
                        }
                    }
                }
                e.detail = state;
            }
            
            public void getChildren(final AccessibleControlEvent e) {
                final int childIdCount = this.this$0.items.length;
                final Object[] children = new Object[childIdCount];
                for (int i = 0; i < childIdCount; ++i) {
                    children[i] = i;
                }
                e.children = children;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: astore_1        /* accessible */
        //     5: aload_1         /* accessible */
        //     6: new             Lorg/eclipse/swt/custom/lIIIllI;
        //     9: dup            
        //    10: aload_0         /* this */
        //    11: invokespecial   org/eclipse/swt/custom/lIIIllI.<init>:(Lorg/eclipse/swt/custom/CTabFolder;)V
        //    14: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //    17: aload_1         /* accessible */
        //    18: new             Lorg/eclipse/swt/custom/lllllI;
        //    21: dup            
        //    22: aload_0         /* this */
        //    23: invokespecial   org/eclipse/swt/custom/lllllI.<init>:(Lorg/eclipse/swt/custom/CTabFolder;)V
        //    26: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleControlListener:(Lorg/eclipse/swt/accessibility/AccessibleControlListener;)V
        //    29: aload_0         /* this */
        //    30: bipush          13
        //    32: aload_0         /* this */
        //    33: aload_1         /* accessible */
        //    34: invokedynamic   BootstrapMethod #1, handleEvent:(Lorg/eclipse/swt/custom/CTabFolder;Lorg/eclipse/swt/accessibility/Accessible;)Lorg/eclipse/swt/widgets/Listener;
        //    39: invokevirtual   org/eclipse/swt/custom/CTabFolder.addListener:(ILorg/eclipse/swt/widgets/Listener;)V
        //    42: aload_0         /* this */
        //    43: bipush          15
        //    45: aload_0         /* this */
        //    46: aload_1         /* accessible */
        //    47: invokedynamic   BootstrapMethod #2, handleEvent:(Lorg/eclipse/swt/custom/CTabFolder;Lorg/eclipse/swt/accessibility/Accessible;)Lorg/eclipse/swt/widgets/Listener;
        //    52: invokevirtual   org/eclipse/swt/custom/CTabFolder.addListener:(ILorg/eclipse/swt/widgets/Listener;)V
        //    55: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void initAccessibleMinMaxTb() {
        class lllIll extends AccessibleAdapter
        {
            final /* synthetic */ CTabFolder this$0;
            
            lllIll(final CTabFolder this$0) {
                this.this$0 = this$0;
            }
            
            public void getName(final AccessibleEvent e) {
                if (e.childID != -1) {
                    if (this.this$0.minItem != null && e.childID == this.this$0.minMaxTb.indexOf(this.this$0.minItem)) {
                        e.result = this.this$0.minItem.getToolTipText();
                    }
                    else if (this.this$0.maxItem != null && e.childID == this.this$0.minMaxTb.indexOf(this.this$0.maxItem)) {
                        e.result = this.this$0.maxItem.getToolTipText();
                    }
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: invokevirtual   org/eclipse/swt/widgets/ToolBar.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //     7: new             Lorg/eclipse/swt/custom/lllIll;
        //    10: dup            
        //    11: aload_0         /* this */
        //    12: invokespecial   org/eclipse/swt/custom/lllIll.<init>:(Lorg/eclipse/swt/custom/CTabFolder;)V
        //    15: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //    18: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void initAccessibleChevronTb() {
        class lIllIl extends AccessibleAdapter
        {
            final /* synthetic */ CTabFolder this$0;
            
            lIllIl(final CTabFolder this$0) {
                this.this$0 = this$0;
            }
            
            public void getName(final AccessibleEvent e) {
                if (e.childID != -1 && this.this$0.chevronItem != null && e.childID == this.this$0.chevronTb.indexOf(this.this$0.chevronItem)) {
                    e.result = this.this$0.chevronItem.getToolTipText();
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: invokevirtual   org/eclipse/swt/widgets/ToolBar.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //     7: new             Lorg/eclipse/swt/custom/lIllIl;
        //    10: dup            
        //    11: aload_0         /* this */
        //    12: invokespecial   org/eclipse/swt/custom/lIllIl.<init>:(Lorg/eclipse/swt/custom/CTabFolder;)V
        //    15: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //    18: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void onKeyDown(final Event event) {
        this.runUpdate();
        switch (event.keyCode) {
            case 16777219:
            case 16777220: {
                final int count = this.items.length;
                if (count == 0) {
                    return;
                }
                if (this.selectedIndex == -1) {
                    return;
                }
                final int leadKey = ((this.getStyle() & 0x4000000) != 0x0) ? 16777220 : 16777219;
                final int offset = (event.keyCode == leadKey) ? -1 : 1;
                int index;
                if (!this.mru) {
                    index = this.selectedIndex + offset;
                }
                else {
                    final int[] visible = new int[this.items.length];
                    int idx = 0;
                    int current = -1;
                    for (int i = 0; i < this.items.length; ++i) {
                        if (this.items[i].showing) {
                            if (i == this.selectedIndex) {
                                current = idx;
                            }
                            visible[idx++] = i;
                        }
                    }
                    if (current + offset < 0 || current + offset >= idx) {
                        if (this.showChevron) {
                            Rectangle chevronRect = this.chevronItem.getBounds();
                            chevronRect = event.display.map(this.chevronTb, this, chevronRect);
                            final CTabFolderEvent e = new CTabFolderEvent(this);
                            e.widget = this;
                            e.time = event.time;
                            e.x = chevronRect.x;
                            e.y = chevronRect.y;
                            e.width = chevronRect.width;
                            e.height = chevronRect.height;
                            e.doit = true;
                            for (final CTabFolder2Listener folderListener : this.folderListeners) {
                                folderListener.showList(e);
                            }
                            if (e.doit && !this.isDisposed()) {
                                this.showList(chevronRect);
                            }
                        }
                        return;
                    }
                    index = visible[current + offset];
                }
                if (index < 0 || index >= count) {
                    return;
                }
                this.setSelection(index, true);
                this.forceFocus();
                break;
            }
        }
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.inDispose = true;
        if (this.showMenu != null && !this.showMenu.isDisposed()) {
            this.showMenu.dispose();
            this.showMenu = null;
        }
        for (int length = this.items.length, i = 0; i < length; ++i) {
            if (this.items[i] != null) {
                this.items[i].dispose();
            }
        }
        this.gradientColors = null;
        this.selectionGradientColors = null;
        this.selectionGradientPercents = null;
        this.selectionBgImage = null;
        this.selectionBackground = null;
        this.selectionForeground = null;
        if (this.controlBkImages != null) {
            for (int j = 0; j < this.controlBkImages.length; ++j) {
                if (this.controlBkImages[j] != null) {
                    this.controlBkImages[j].dispose();
                    this.controlBkImages[j] = null;
                }
            }
            this.controlBkImages = null;
        }
        this.controls = null;
        this.controlAlignments = null;
        this.controlRects = null;
        if (this.maxImage != null) {
            this.maxImage.dispose();
        }
        this.maxImage = null;
        if (this.minImage != null) {
            this.minImage.dispose();
        }
        this.minImage = null;
        if (this.chevronImage != null) {
            this.chevronImage.dispose();
        }
        this.chevronImage = null;
        if (this.renderer != null) {
            this.renderer.dispose();
        }
        this.renderer = null;
        this.minItem = null;
        this.maxItem = null;
        this.minMaxTb = null;
        this.chevronItem = null;
        this.chevronTb = null;
        if (this.folderListeners.length != 0) {
            this.folderListeners = new CTabFolder2Listener[0];
        }
        if (this.tabListeners.length != 0) {
            this.tabListeners = new CTabFolderListener[0];
        }
    }
    
    void onDragDetect(final Event event) {
        boolean consume = false;
        for (final CTabItem item : this.items) {
            if (item.closeRect.contains(event.x, event.y)) {
                consume = true;
                break;
            }
        }
        if (consume) {
            event.type = 0;
        }
    }
    
    void onFocus(final Event event) {
        this.checkWidget();
        if (this.selectedIndex >= 0) {
            this.redraw();
        }
        else {
            this.setSelection(0, true);
        }
    }
    
    boolean onMnemonic(final Event event, final boolean doit) {
        final char key = event.character;
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                final char mnemonic = this._findMnemonic(this.items[i].getText());
                if (mnemonic != '\0' && Character.toLowerCase(key) == mnemonic) {
                    if (doit) {
                        this.setSelection(i, true);
                        this.forceFocus();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    void onMenuDetect(final Event event) {
        if (event.detail == 1 && this.selectedIndex != -1) {
            final CTabItem item = this.items[this.selectedIndex];
            final Rectangle rect = this.getDisplay().map(this, null, item.getBounds());
            if (!rect.contains(event.x, event.y)) {
                final Rectangle itemTrim = this.renderer.computeTrim(this.selectedIndex, 0, 0, 0, 0, 0);
                final Rectangle closeTrim = this.renderer.computeTrim(-8, 0, 0, 0, 0, 0);
                event.x = rect.x + rect.width - item.closeRect.width + itemTrim.x - closeTrim.width;
                event.y = rect.y - itemTrim.y - closeTrim.y;
            }
        }
    }
    
    void onMouseDoubleClick(final Event event) {
        if (event.button != 1 || (event.stateMask & 0x100000) != 0x0 || (event.stateMask & 0x200000) != 0x0) {
            return;
        }
        final Event e = new Event();
        e.item = this.getItem(new Point(event.x, event.y));
        if (e.item != null) {
            this.notifyListeners(14, e);
        }
    }
    
    void onMouse(final Event event) {
        class lIlIIl implements Runnable
        {
            final /* synthetic */ CTabFolder this$0;
            
            lIlIIl(final CTabFolder this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                if (this.this$0.isDisposed()) {
                    return;
                }
                if (this.this$0.hovering) {
                    final Display display = this.this$0.getDisplay();
                    final Control c = display.getCursorControl();
                    boolean reschedule = false;
                    if (c != null) {
                        for (final Control control : this.this$0.controls) {
                            Control temp = c;
                            do {
                                if (temp.equals(control)) {
                                    reschedule = true;
                                }
                                else {
                                    temp = temp.getParent();
                                    if (temp == null) {
                                        break;
                                    }
                                    if (temp.equals(this.this$0)) {
                                        break;
                                    }
                                    continue;
                                }
                            } while (!reschedule);
                            if (reschedule) {
                                break;
                            }
                        }
                    }
                    if (reschedule && this.this$0.hoverTimerRunning) {
                        display.timerExec(2000, this);
                    }
                    else {
                        this.this$0.hovering = false;
                        this.this$0.updateItems();
                    }
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: ifeq            8
        //     7: return         
        //     8: aload_1         /* event */
        //     9: getfield        org/eclipse/swt/widgets/Event.x:I
        //    12: istore_2        /* x */
        //    13: aload_1         /* event */
        //    14: getfield        org/eclipse/swt/widgets/Event.y:I
        //    17: istore_3        /* y */
        //    18: aload_1         /* event */
        //    19: getfield        org/eclipse/swt/widgets/Event.type:I
        //    22: lookupswitch {
        //                3: 294
        //                4: 1024
        //                5: 587
        //                6: 80
        //                7: 88
        //               32: 294
        //          default: 1558
        //        }
        //    80: aload_0         /* this */
        //    81: aconst_null    
        //    82: invokevirtual   org/eclipse/swt/custom/CTabFolder.setToolTipText:(Ljava/lang/String;)V
        //    85: goto            1558
        //    88: iconst_0       
        //    89: istore          i
        //    91: iload           i
        //    93: aload_0         /* this */
        //    94: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //    97: arraylength    
        //    98: if_icmpge       291
        //   101: aload_0         /* this */
        //   102: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //   105: iload           i
        //   107: aaload         
        //   108: astore          item
        //   110: iload           i
        //   112: aload_0         /* this */
        //   113: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   116: if_icmpeq       173
        //   119: aload           item
        //   121: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   124: bipush          8
        //   126: if_icmpeq       173
        //   129: aload           item
        //   131: bipush          8
        //   133: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   136: aload_0         /* this */
        //   137: aload           item
        //   139: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   142: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //   145: aload           item
        //   147: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   150: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //   153: aload           item
        //   155: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   158: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //   161: aload           item
        //   163: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   166: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //   169: iconst_0       
        //   170: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   173: aload           item
        //   175: getfield        org/eclipse/swt/custom/CTabItem.state:I
        //   178: bipush          32
        //   180: iand           
        //   181: ifeq            225
        //   184: aload           item
        //   186: astore          cTabItem
        //   188: aload           cTabItem
        //   190: dup            
        //   191: getfield        org/eclipse/swt/custom/CTabItem.state:I
        //   194: bipush          -33
        //   196: iand           
        //   197: putfield        org/eclipse/swt/custom/CTabItem.state:I
        //   200: aload_0         /* this */
        //   201: aload           item
        //   203: getfield        org/eclipse/swt/custom/CTabItem.x:I
        //   206: aload           item
        //   208: getfield        org/eclipse/swt/custom/CTabItem.y:I
        //   211: aload           item
        //   213: getfield        org/eclipse/swt/custom/CTabItem.width:I
        //   216: aload           item
        //   218: getfield        org/eclipse/swt/custom/CTabItem.height:I
        //   221: iconst_0       
        //   222: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   225: iload           i
        //   227: aload_0         /* this */
        //   228: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   231: if_icmpne       285
        //   234: aload           item
        //   236: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   239: ifeq            285
        //   242: aload           item
        //   244: iconst_0       
        //   245: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   248: aload_0         /* this */
        //   249: aload           item
        //   251: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   254: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //   257: aload           item
        //   259: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   262: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //   265: aload           item
        //   267: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   270: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //   273: aload           item
        //   275: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   278: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //   281: iconst_0       
        //   282: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   285: iinc            i, 1
        //   288: goto            91
        //   291: goto            1558
        //   294: aload_0         /* this */
        //   295: getfield        org/eclipse/swt/custom/CTabFolder.hoverTb:Z
        //   298: ifeq            354
        //   301: aload_0         /* this */
        //   302: getfield        org/eclipse/swt/custom/CTabFolder.hoverRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   305: iload_2         /* x */
        //   306: iload_3         /* y */
        //   307: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //   310: ifeq            354
        //   313: aload_0         /* this */
        //   314: getfield        org/eclipse/swt/custom/CTabFolder.hovering:Z
        //   317: ifne            354
        //   320: aload_0         /* this */
        //   321: iconst_1       
        //   322: putfield        org/eclipse/swt/custom/CTabFolder.hovering:Z
        //   325: aload_0         /* this */
        //   326: invokevirtual   org/eclipse/swt/custom/CTabFolder.updateItems:()Z
        //   329: pop            
        //   330: aload_0         /* this */
        //   331: iconst_1       
        //   332: putfield        org/eclipse/swt/custom/CTabFolder.hoverTimerRunning:Z
        //   335: aload_1         /* event */
        //   336: getfield        org/eclipse/swt/widgets/Event.display:Lorg/eclipse/swt/widgets/Display;
        //   339: sipush          2000
        //   342: new             Lorg/eclipse/swt/custom/lIlIIl;
        //   345: dup            
        //   346: aload_0         /* this */
        //   347: invokespecial   org/eclipse/swt/custom/lIlIIl.<init>:(Lorg/eclipse/swt/custom/CTabFolder;)V
        //   350: invokevirtual   org/eclipse/swt/widgets/Display.timerExec:(ILjava/lang/Runnable;)V
        //   353: return         
        //   354: aload_1         /* event */
        //   355: getfield        org/eclipse/swt/widgets/Event.button:I
        //   358: iconst_1       
        //   359: if_icmpeq       363
        //   362: return         
        //   363: aconst_null    
        //   364: astore          item2
        //   366: aload_0         /* this */
        //   367: getfield        org/eclipse/swt/custom/CTabFolder.single:Z
        //   370: ifeq            419
        //   373: aload_0         /* this */
        //   374: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   377: iconst_m1      
        //   378: if_icmpeq       474
        //   381: aload_0         /* this */
        //   382: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //   385: aload_0         /* this */
        //   386: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   389: aaload         
        //   390: invokevirtual   org/eclipse/swt/custom/CTabItem.getBounds:()Lorg/eclipse/swt/graphics/Rectangle;
        //   393: astore          bounds
        //   395: aload           bounds
        //   397: iload_2         /* x */
        //   398: iload_3         /* y */
        //   399: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //   402: ifeq            416
        //   405: aload_0         /* this */
        //   406: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //   409: aload_0         /* this */
        //   410: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   413: aaload         
        //   414: astore          item2
        //   416: goto            474
        //   419: aload_0         /* this */
        //   420: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //   423: astore          5
        //   425: aload           5
        //   427: arraylength    
        //   428: istore          6
        //   430: iconst_0       
        //   431: istore          7
        //   433: iload           7
        //   435: iload           6
        //   437: if_icmpge       474
        //   440: aload           5
        //   442: iload           7
        //   444: aaload         
        //   445: astore          tabItem
        //   447: aload           tabItem
        //   449: invokevirtual   org/eclipse/swt/custom/CTabItem.getBounds:()Lorg/eclipse/swt/graphics/Rectangle;
        //   452: astore          bounds2
        //   454: aload           bounds2
        //   456: iload_2         /* x */
        //   457: iload_3         /* y */
        //   458: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //   461: ifeq            468
        //   464: aload           tabItem
        //   466: astore          item2
        //   468: iinc            7, 1
        //   471: goto            433
        //   474: aload           item2
        //   476: ifnonnull       482
        //   479: goto            1558
        //   482: aload           item2
        //   484: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   487: iload_2         /* x */
        //   488: iload_3         /* y */
        //   489: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //   492: ifeq            543
        //   495: aload           item2
        //   497: iconst_2       
        //   498: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   501: aload_0         /* this */
        //   502: aload           item2
        //   504: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   507: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //   510: aload           item2
        //   512: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   515: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //   518: aload           item2
        //   520: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   523: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //   526: aload           item2
        //   528: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   531: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //   534: iconst_0       
        //   535: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   538: aload_0         /* this */
        //   539: invokevirtual   org/eclipse/swt/custom/CTabFolder.update:()V
        //   542: return         
        //   543: aload_0         /* this */
        //   544: aload           item2
        //   546: invokevirtual   org/eclipse/swt/custom/CTabFolder.indexOf:(Lorg/eclipse/swt/custom/CTabItem;)I
        //   549: istore          index
        //   551: aload           item2
        //   553: getfield        org/eclipse/swt/custom/CTabItem.showing:Z
        //   556: ifeq            586
        //   559: aload_0         /* this */
        //   560: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   563: istore          oldSelectedIndex
        //   565: aload_0         /* this */
        //   566: iload           index
        //   568: iconst_1       
        //   569: invokevirtual   org/eclipse/swt/custom/CTabFolder.setSelection:(IZ)V
        //   572: iload           oldSelectedIndex
        //   574: aload_0         /* this */
        //   575: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   578: if_icmpne       586
        //   581: aload_0         /* this */
        //   582: invokevirtual   org/eclipse/swt/custom/CTabFolder.forceFocus:()Z
        //   585: pop            
        //   586: return         
        //   587: aload_0         /* this */
        //   588: aload_1         /* event */
        //   589: getfield        org/eclipse/swt/widgets/Event.x:I
        //   592: aload_1         /* event */
        //   593: getfield        org/eclipse/swt/widgets/Event.y:I
        //   596: invokevirtual   org/eclipse/swt/custom/CTabFolder._setToolTipText:(II)V
        //   599: iconst_0       
        //   600: istore          close
        //   602: iconst_0       
        //   603: istore          j
        //   605: iload           j
        //   607: aload_0         /* this */
        //   608: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //   611: arraylength    
        //   612: if_icmpge       1021
        //   615: aload_0         /* this */
        //   616: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //   619: iload           j
        //   621: aaload         
        //   622: astore          item3
        //   624: iconst_0       
        //   625: istore          close
        //   627: aload           item3
        //   629: invokevirtual   org/eclipse/swt/custom/CTabItem.getBounds:()Lorg/eclipse/swt/graphics/Rectangle;
        //   632: iload_2         /* x */
        //   633: iload_3         /* y */
        //   634: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //   637: ifeq            825
        //   640: iconst_1       
        //   641: istore          close
        //   643: aload           item3
        //   645: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   648: iload_2         /* x */
        //   649: iload_3         /* y */
        //   650: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //   653: ifeq            722
        //   656: aload           item3
        //   658: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   661: iconst_2       
        //   662: if_icmpeq       773
        //   665: aload           item3
        //   667: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   670: bipush          32
        //   672: if_icmpeq       773
        //   675: aload           item3
        //   677: bipush          32
        //   679: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   682: aload_0         /* this */
        //   683: aload           item3
        //   685: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   688: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //   691: aload           item3
        //   693: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   696: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //   699: aload           item3
        //   701: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   704: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //   707: aload           item3
        //   709: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   712: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //   715: iconst_0       
        //   716: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   719: goto            773
        //   722: aload           item3
        //   724: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   727: ifeq            773
        //   730: aload           item3
        //   732: iconst_0       
        //   733: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   736: aload_0         /* this */
        //   737: aload           item3
        //   739: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   742: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //   745: aload           item3
        //   747: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   750: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //   753: aload           item3
        //   755: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   758: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //   761: aload           item3
        //   763: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   766: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //   769: iconst_0       
        //   770: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   773: aload           item3
        //   775: getfield        org/eclipse/swt/custom/CTabItem.state:I
        //   778: bipush          32
        //   780: iand           
        //   781: ifne            825
        //   784: aload           item3
        //   786: astore          cTabItem2
        //   788: aload           cTabItem2
        //   790: dup            
        //   791: getfield        org/eclipse/swt/custom/CTabItem.state:I
        //   794: bipush          32
        //   796: ior            
        //   797: putfield        org/eclipse/swt/custom/CTabItem.state:I
        //   800: aload_0         /* this */
        //   801: aload           item3
        //   803: getfield        org/eclipse/swt/custom/CTabItem.x:I
        //   806: aload           item3
        //   808: getfield        org/eclipse/swt/custom/CTabItem.y:I
        //   811: aload           item3
        //   813: getfield        org/eclipse/swt/custom/CTabItem.width:I
        //   816: aload           item3
        //   818: getfield        org/eclipse/swt/custom/CTabItem.height:I
        //   821: iconst_0       
        //   822: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   825: iload           j
        //   827: aload_0         /* this */
        //   828: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   831: if_icmpeq       893
        //   834: aload           item3
        //   836: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   839: bipush          8
        //   841: if_icmpeq       893
        //   844: iload           close
        //   846: ifne            893
        //   849: aload           item3
        //   851: bipush          8
        //   853: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   856: aload_0         /* this */
        //   857: aload           item3
        //   859: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   862: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //   865: aload           item3
        //   867: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   870: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //   873: aload           item3
        //   875: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   878: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //   881: aload           item3
        //   883: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   886: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //   889: iconst_0       
        //   890: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   893: aload           item3
        //   895: getfield        org/eclipse/swt/custom/CTabItem.state:I
        //   898: bipush          32
        //   900: iand           
        //   901: ifeq            950
        //   904: iload           close
        //   906: ifne            950
        //   909: aload           item3
        //   911: astore          cTabItem3
        //   913: aload           cTabItem3
        //   915: dup            
        //   916: getfield        org/eclipse/swt/custom/CTabItem.state:I
        //   919: bipush          -33
        //   921: iand           
        //   922: putfield        org/eclipse/swt/custom/CTabItem.state:I
        //   925: aload_0         /* this */
        //   926: aload           item3
        //   928: getfield        org/eclipse/swt/custom/CTabItem.x:I
        //   931: aload           item3
        //   933: getfield        org/eclipse/swt/custom/CTabItem.y:I
        //   936: aload           item3
        //   938: getfield        org/eclipse/swt/custom/CTabItem.width:I
        //   941: aload           item3
        //   943: getfield        org/eclipse/swt/custom/CTabItem.height:I
        //   946: iconst_0       
        //   947: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //   950: iload           j
        //   952: aload_0         /* this */
        //   953: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //   956: if_icmpne       1015
        //   959: aload           item3
        //   961: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   964: ifeq            1015
        //   967: iload           close
        //   969: ifne            1015
        //   972: aload           item3
        //   974: iconst_0       
        //   975: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //   978: aload_0         /* this */
        //   979: aload           item3
        //   981: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   984: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //   987: aload           item3
        //   989: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //   992: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //   995: aload           item3
        //   997: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1000: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //  1003: aload           item3
        //  1005: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1008: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //  1011: iconst_0       
        //  1012: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //  1015: iinc            j, 1
        //  1018: goto            605
        //  1021: goto            1558
        //  1024: aload_1         /* event */
        //  1025: getfield        org/eclipse/swt/widgets/Event.button:I
        //  1028: iconst_1       
        //  1029: if_icmpeq       1033
        //  1032: return         
        //  1033: aconst_null    
        //  1034: astore          item2
        //  1036: aload_0         /* this */
        //  1037: getfield        org/eclipse/swt/custom/CTabFolder.single:Z
        //  1040: ifeq            1089
        //  1043: aload_0         /* this */
        //  1044: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //  1047: iconst_m1      
        //  1048: if_icmpeq       1144
        //  1051: aload_0         /* this */
        //  1052: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //  1055: aload_0         /* this */
        //  1056: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //  1059: aaload         
        //  1060: invokevirtual   org/eclipse/swt/custom/CTabItem.getBounds:()Lorg/eclipse/swt/graphics/Rectangle;
        //  1063: astore          bounds
        //  1065: aload           bounds
        //  1067: iload_2         /* x */
        //  1068: iload_3         /* y */
        //  1069: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //  1072: ifeq            1086
        //  1075: aload_0         /* this */
        //  1076: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //  1079: aload_0         /* this */
        //  1080: getfield        org/eclipse/swt/custom/CTabFolder.selectedIndex:I
        //  1083: aaload         
        //  1084: astore          item2
        //  1086: goto            1144
        //  1089: aload_0         /* this */
        //  1090: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //  1093: astore          5
        //  1095: aload           5
        //  1097: arraylength    
        //  1098: istore          6
        //  1100: iconst_0       
        //  1101: istore          7
        //  1103: iload           7
        //  1105: iload           6
        //  1107: if_icmpge       1144
        //  1110: aload           5
        //  1112: iload           7
        //  1114: aaload         
        //  1115: astore          tabItem
        //  1117: aload           tabItem
        //  1119: invokevirtual   org/eclipse/swt/custom/CTabItem.getBounds:()Lorg/eclipse/swt/graphics/Rectangle;
        //  1122: astore          bounds2
        //  1124: aload           bounds2
        //  1126: iload_2         /* x */
        //  1127: iload_3         /* y */
        //  1128: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //  1131: ifeq            1138
        //  1134: aload           tabItem
        //  1136: astore          item2
        //  1138: iinc            7, 1
        //  1141: goto            1103
        //  1144: aload           item2
        //  1146: ifnull          1558
        //  1149: aload           item2
        //  1151: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1154: iload_2         /* x */
        //  1155: iload_3         /* y */
        //  1156: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(II)Z
        //  1159: ifne            1165
        //  1162: goto            1558
        //  1165: aload           item2
        //  1167: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //  1170: iconst_2       
        //  1171: if_icmpne       1178
        //  1174: iconst_1       
        //  1175: goto            1179
        //  1178: iconst_0       
        //  1179: istore          selected
        //  1181: aload           item2
        //  1183: bipush          32
        //  1185: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //  1188: aload_0         /* this */
        //  1189: aload           item2
        //  1191: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1194: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //  1197: aload           item2
        //  1199: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1202: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //  1205: aload           item2
        //  1207: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1210: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //  1213: aload           item2
        //  1215: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1218: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //  1221: iconst_0       
        //  1222: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //  1225: iload           selected
        //  1227: ifne            1231
        //  1230: return         
        //  1231: new             Lorg/eclipse/swt/custom/CTabFolderEvent;
        //  1234: dup            
        //  1235: aload_0         /* this */
        //  1236: invokespecial   org/eclipse/swt/custom/CTabFolderEvent.<init>:(Lorg/eclipse/swt/widgets/Widget;)V
        //  1239: astore          e
        //  1241: aload           e
        //  1243: aload_0         /* this */
        //  1244: putfield        org/eclipse/swt/custom/CTabFolderEvent.widget:Lorg/eclipse/swt/widgets/Widget;
        //  1247: aload           e
        //  1249: aload_1         /* event */
        //  1250: getfield        org/eclipse/swt/widgets/Event.time:I
        //  1253: putfield        org/eclipse/swt/custom/CTabFolderEvent.time:I
        //  1256: aload           e
        //  1258: aload           item2
        //  1260: putfield        org/eclipse/swt/custom/CTabFolderEvent.item:Lorg/eclipse/swt/widgets/Widget;
        //  1263: aload           e
        //  1265: iconst_1       
        //  1266: putfield        org/eclipse/swt/custom/CTabFolderEvent.doit:Z
        //  1269: aload_0         /* this */
        //  1270: getfield        org/eclipse/swt/custom/CTabFolder.folderListeners:[Lorg/eclipse/swt/custom/CTabFolder2Listener;
        //  1273: astore          7
        //  1275: aload           7
        //  1277: arraylength    
        //  1278: istore          8
        //  1280: iconst_0       
        //  1281: istore          9
        //  1283: iload           9
        //  1285: iload           8
        //  1287: if_icmpge       1312
        //  1290: aload           7
        //  1292: iload           9
        //  1294: aaload         
        //  1295: astore          listener
        //  1297: aload           listener
        //  1299: aload           e
        //  1301: invokeinterface org/eclipse/swt/custom/CTabFolder2Listener.close:(Lorg/eclipse/swt/custom/CTabFolderEvent;)V
        //  1306: iinc            9, 1
        //  1309: goto            1283
        //  1312: aload_0         /* this */
        //  1313: getfield        org/eclipse/swt/custom/CTabFolder.tabListeners:[Lorg/eclipse/swt/custom/CTabFolderListener;
        //  1316: astore          7
        //  1318: aload           7
        //  1320: arraylength    
        //  1321: istore          8
        //  1323: iconst_0       
        //  1324: istore          9
        //  1326: iload           9
        //  1328: iload           8
        //  1330: if_icmpge       1355
        //  1333: aload           7
        //  1335: iload           9
        //  1337: aaload         
        //  1338: astore          listener2
        //  1340: aload           listener2
        //  1342: aload           e
        //  1344: invokeinterface org/eclipse/swt/custom/CTabFolderListener.itemClosed:(Lorg/eclipse/swt/custom/CTabFolderEvent;)V
        //  1349: iinc            9, 1
        //  1352: goto            1326
        //  1355: aload           e
        //  1357: getfield        org/eclipse/swt/custom/CTabFolderEvent.doit:Z
        //  1360: ifeq            1368
        //  1363: aload           item2
        //  1365: invokevirtual   org/eclipse/swt/custom/CTabItem.dispose:()V
        //  1368: aload_0         /* this */
        //  1369: invokevirtual   org/eclipse/swt/custom/CTabFolder.isDisposed:()Z
        //  1372: ifne            1558
        //  1375: aload           item2
        //  1377: invokevirtual   org/eclipse/swt/custom/CTabItem.isDisposed:()Z
        //  1380: ifeq            1558
        //  1383: aload_0         /* this */
        //  1384: invokevirtual   org/eclipse/swt/custom/CTabFolder.getDisplay:()Lorg/eclipse/swt/widgets/Display;
        //  1387: astore          display
        //  1389: aload           display
        //  1391: invokevirtual   org/eclipse/swt/widgets/Display.getCursorLocation:()Lorg/eclipse/swt/graphics/Point;
        //  1394: astore          pt
        //  1396: aload           display
        //  1398: aconst_null    
        //  1399: aload_0         /* this */
        //  1400: aload           pt
        //  1402: getfield        org/eclipse/swt/graphics/Point.x:I
        //  1405: aload           pt
        //  1407: getfield        org/eclipse/swt/graphics/Point.y:I
        //  1410: invokevirtual   org/eclipse/swt/widgets/Display.map:(Lorg/eclipse/swt/widgets/Control;Lorg/eclipse/swt/widgets/Control;II)Lorg/eclipse/swt/graphics/Point;
        //  1413: astore          pt
        //  1415: aload_0         /* this */
        //  1416: aload           pt
        //  1418: invokevirtual   org/eclipse/swt/custom/CTabFolder.getItem:(Lorg/eclipse/swt/graphics/Point;)Lorg/eclipse/swt/custom/CTabItem;
        //  1421: astore          nextItem
        //  1423: aload           nextItem
        //  1425: ifnull          1558
        //  1428: aload           nextItem
        //  1430: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1433: aload           pt
        //  1435: invokevirtual   org/eclipse/swt/graphics/Rectangle.contains:(Lorg/eclipse/swt/graphics/Point;)Z
        //  1438: ifeq            1507
        //  1441: aload           nextItem
        //  1443: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //  1446: iconst_2       
        //  1447: if_icmpeq       1558
        //  1450: aload           nextItem
        //  1452: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //  1455: bipush          32
        //  1457: if_icmpeq       1558
        //  1460: aload           nextItem
        //  1462: bipush          32
        //  1464: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //  1467: aload_0         /* this */
        //  1468: aload           nextItem
        //  1470: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1473: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //  1476: aload           nextItem
        //  1478: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1481: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //  1484: aload           nextItem
        //  1486: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1489: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //  1492: aload           nextItem
        //  1494: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1497: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //  1500: iconst_0       
        //  1501: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //  1504: goto            1558
        //  1507: aload           nextItem
        //  1509: getfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //  1512: ifeq            1558
        //  1515: aload           nextItem
        //  1517: iconst_0       
        //  1518: putfield        org/eclipse/swt/custom/CTabItem.closeImageState:I
        //  1521: aload_0         /* this */
        //  1522: aload           nextItem
        //  1524: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1527: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //  1530: aload           nextItem
        //  1532: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1535: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //  1538: aload           nextItem
        //  1540: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1543: getfield        org/eclipse/swt/graphics/Rectangle.width:I
        //  1546: aload           nextItem
        //  1548: getfield        org/eclipse/swt/custom/CTabItem.closeRect:Lorg/eclipse/swt/graphics/Rectangle;
        //  1551: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //  1554: iconst_0       
        //  1555: invokevirtual   org/eclipse/swt/custom/CTabFolder.redraw:(IIIIZ)V
        //  1558: return         
        //    StackMapTable: 00 2E 08 FD 00 47 01 01 07 FC 00 02 01 FC 00 51 07 00 A9 33 FA 00 3B FA 00 05 02 3B 08 FC 00 34 07 00 A9 02 FE 00 0D 07 01 A6 01 01 22 F8 00 05 07 3C FC 00 2A 01 F9 00 00 FD 00 11 01 01 FC 00 74 07 00 A9 32 33 FB 00 43 38 FA 00 40 FA 00 05 FA 00 02 08 FC 00 34 07 00 A9 02 FE 00 0D 07 01 A6 01 01 22 F8 00 05 14 0C 40 01 FC 00 33 01 FF 00 33 00 0A 07 00 02 07 03 0D 01 01 07 00 A9 01 07 03 1C 07 03 2F 01 01 00 00 F8 00 1C FE 00 0D 07 01 67 01 01 F8 00 1C 0C FE 00 8A 07 00 FD 07 01 88 07 00 A9 FF 00 32 00 04 07 00 02 07 03 0D 01 01 00 00
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:480)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void onPageTraversal(final Event event) {
        final int count = this.items.length;
        if (count == 0) {
            return;
        }
        int index = this.selectedIndex;
        if (index == -1) {
            index = 0;
        }
        else {
            final int offset = (event.detail == 512) ? 1 : -1;
            if (!this.mru) {
                index = (this.selectedIndex + offset + count) % count;
            }
            else {
                final int[] visible = new int[this.items.length];
                int idx = 0;
                int current = -1;
                for (int i = 0; i < this.items.length; ++i) {
                    if (this.items[i].showing) {
                        if (i == this.selectedIndex) {
                            current = idx;
                        }
                        visible[idx++] = i;
                    }
                }
                if (current + offset >= 0 && current + offset < idx) {
                    index = visible[current + offset];
                }
                else if (this.showChevron) {
                    Rectangle chevronRect = this.chevronItem.getBounds();
                    chevronRect = event.display.map(this.chevronTb, this, chevronRect);
                    final CTabFolderEvent e = new CTabFolderEvent(this);
                    e.widget = this;
                    e.time = event.time;
                    e.x = chevronRect.x;
                    e.y = chevronRect.y;
                    e.width = chevronRect.width;
                    e.height = chevronRect.height;
                    e.doit = true;
                    for (final CTabFolder2Listener folderListener : this.folderListeners) {
                        folderListener.showList(e);
                    }
                    if (e.doit && !this.isDisposed()) {
                        this.showList(chevronRect);
                    }
                }
            }
        }
        this.setSelection(index, true);
    }
    
    void onPaint(final Event event) {
        if (this.inDispose) {
            return;
        }
        final Font font = this.getFont();
        if (this.oldFont == null || !this.oldFont.equals(font)) {
            this.oldFont = font;
            if (!this.updateTabHeight(false)) {
                this.updateItems();
                this.redraw();
                return;
            }
        }
        final GC gc = event.gc;
        final Font gcFont = gc.getFont();
        final Color gcBackground = gc.getBackground();
        final Color gcForeground = gc.getForeground();
        final Point size = this.getSize();
        final Rectangle bodyRect = new Rectangle(0, 0, size.x, size.y);
        this.renderer.draw(-1, 24, bodyRect, gc);
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
        this.renderer.draw(-2, 24, bodyRect, gc);
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
        if (!this.single) {
            for (int i = 0; i < this.items.length; ++i) {
                final Rectangle itemBounds = this.items[i].getBounds();
                if (i != this.selectedIndex && event.getBounds().intersects(itemBounds)) {
                    this.renderer.draw(i, 0x18 | this.items[i].state, itemBounds, gc);
                }
            }
        }
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
        if (this.selectedIndex != -1) {
            this.renderer.draw(this.selectedIndex, this.items[this.selectedIndex].state | 0x8 | 0x10, this.items[this.selectedIndex].getBounds(), gc);
        }
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
        if (this.hoverTb) {
            final Rectangle trim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
            int x = this.getSize().x - (trim.width + trim.x);
            this.hoverRect = new Rectangle(x - 16 - 3, 2, 16, this.getTabHeight() - 2);
            gc.setForeground(gc.getDevice().getSystemColor(18));
            x = this.hoverRect.x;
            final int y = this.hoverRect.y;
            gc.setBackground(gc.getDevice().getSystemColor(1));
            gc.fillRectangle(x + this.hoverRect.width - 6, y, 5, 5);
            gc.drawRectangle(x + this.hoverRect.width - 6, y, 5, 5);
            gc.drawLine(x + this.hoverRect.width - 6, y + 2, x + this.hoverRect.width - 6 + 5, y + 2);
            gc.fillRectangle(x, y, 5, 2);
            gc.drawRectangle(x, y, 5, 2);
        }
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
    }
    
    void onResize(final Event event) {
        if (this.inDispose) {
            return;
        }
        if (this.ignoreResize) {
            return;
        }
        if (this.updateItems()) {
            this.redrawTabs();
        }
        final Point size = this.getSize();
        if (this.oldSize == null) {
            this.redraw();
        }
        else if (this.onBottom && size.y != this.oldSize.y) {
            this.redraw();
        }
        else {
            int x1 = Math.min(size.x, this.oldSize.x);
            final Rectangle trim = this.renderer.computeTrim(-1, 0, 0, 0, 0, 0);
            if (size.x != this.oldSize.x) {
                x1 -= trim.width + trim.x - this.marginWidth + 2;
            }
            if (!this.simple) {
                x1 -= 5;
            }
            int y1 = Math.min(size.y, this.oldSize.y);
            if (size.y != this.oldSize.y) {
                y1 -= trim.height + trim.y - this.marginHeight;
            }
            final int x2 = Math.max(size.x, this.oldSize.x);
            final int y2 = Math.max(size.y, this.oldSize.y);
            this.redraw(0, y1, x2, y2 - y1, false);
            this.redraw(x1, 0, x2 - x1, y2, false);
            if (this.hoverTb) {
                this.redraw(this.hoverRect.x, this.hoverRect.y, this.hoverRect.width, this.hoverRect.height, false);
            }
        }
        this.oldSize = size;
    }
    
    void onSelection(final Event event) {
        if (this.hovering) {
            this.hovering = false;
            this.updateItems();
        }
        if (event.widget == this.maxItem) {
            final CTabFolderEvent e = new CTabFolderEvent(this);
            e.widget = this;
            e.time = event.time;
            for (final CTabFolder2Listener folderListener : this.folderListeners) {
                if (this.maximized) {
                    folderListener.restore(e);
                }
                else {
                    folderListener.maximize(e);
                }
            }
        }
        else if (event.widget == this.minItem) {
            final CTabFolderEvent e = new CTabFolderEvent(this);
            e.widget = this;
            e.time = event.time;
            for (final CTabFolder2Listener folderListener : this.folderListeners) {
                if (this.minimized) {
                    folderListener.restore(e);
                }
                else {
                    folderListener.minimize(e);
                }
            }
        }
        else if (event.widget == this.chevronItem) {
            Rectangle chevronRect = this.chevronItem.getBounds();
            chevronRect = event.display.map(this.chevronTb, this, chevronRect);
            final CTabFolderEvent e2 = new CTabFolderEvent(this);
            e2.widget = this;
            e2.time = event.time;
            e2.x = chevronRect.x;
            e2.y = chevronRect.y;
            e2.width = chevronRect.width;
            e2.height = chevronRect.height;
            e2.doit = true;
            for (final CTabFolder2Listener folderListener2 : this.folderListeners) {
                folderListener2.showList(e2);
            }
            if (e2.doit && !this.isDisposed()) {
                this.showList(chevronRect);
            }
        }
    }
    
    void onTraverse(final Event event) {
        if (this.ignoreTraverse) {
            return;
        }
        this.runUpdate();
        switch (event.detail) {
            case 2:
            case 4:
            case 8:
            case 16: {
                final Control focusControl = this.getDisplay().getFocusControl();
                if (focusControl == this) {
                    event.doit = true;
                    break;
                }
                break;
            }
            case 128: {
                event.doit = this.onMnemonic(event, false);
                break;
            }
            case 256:
            case 512: {
                event.doit = (this.items.length > 0);
                break;
            }
        }
        this.ignoreTraverse = true;
        this.notifyListeners(31, event);
        this.ignoreTraverse = false;
        event.type = 0;
        if (this.isDisposed()) {
            return;
        }
        if (!event.doit) {
            return;
        }
        switch (event.detail) {
            case 128: {
                this.onMnemonic(event, true);
                event.detail = 0;
                break;
            }
            case 256:
            case 512: {
                this.onPageTraversal(event);
                event.detail = 0;
                break;
            }
        }
    }
    
    void redrawTabs() {
        final Point size = this.getSize();
        final Rectangle trim = this.renderer.computeTrim(-1, 0, 0, 0, 0, 0);
        if (this.onBottom) {
            final int h = trim.height + trim.y - this.marginHeight;
            this.redraw(0, size.y - h - 1, size.x, h + 1, false);
        }
        else {
            this.redraw(0, 0, size.x, -trim.y - this.marginHeight + 1, false);
        }
    }
    
    public void removeCTabFolder2Listener(final CTabFolder2Listener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.folderListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.folderListeners.length; ++i) {
            if (listener == this.folderListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.folderListeners.length == 1) {
            this.folderListeners = new CTabFolder2Listener[0];
            return;
        }
        final CTabFolder2Listener[] newTabListeners = new CTabFolder2Listener[this.folderListeners.length - 1];
        System.arraycopy(this.folderListeners, 0, newTabListeners, 0, index);
        System.arraycopy(this.folderListeners, index + 1, newTabListeners, index, this.folderListeners.length - index - 1);
        this.folderListeners = newTabListeners;
    }
    
    @Deprecated
    public void removeCTabFolderListener(final CTabFolderListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.tabListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.tabListeners.length; ++i) {
            if (listener == this.tabListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.tabListeners.length == 1) {
            this.tabListeners = new CTabFolderListener[0];
            return;
        }
        final CTabFolderListener[] newTabListeners = new CTabFolderListener[this.tabListeners.length - 1];
        System.arraycopy(this.tabListeners, 0, newTabListeners, 0, index);
        System.arraycopy(this.tabListeners, index + 1, newTabListeners, index, this.tabListeners.length - index - 1);
        this.tabListeners = newTabListeners;
    }
    
    public void removeSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(13, listener);
        this.removeListener(14, listener);
    }
    
    @Override
    public void reskin(final int flags) {
        super.reskin(flags);
        for (final CTabItem item : this.items) {
            item.reskin(flags);
        }
    }
    
    @Override
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.renderer.createAntialiasColors();
        this.updateBkImages(true);
        this.redraw();
    }
    
    public void setBackground(final Color[] colors, final int[] percents) {
        this.setBackground(colors, percents, false);
    }
    
    public void setBackground(Color[] colors, int[] percents, final boolean vertical) {
        this.checkWidget();
        if (colors != null) {
            if (percents == null || percents.length != colors.length - 1) {
                SWT.error(5);
            }
            for (int i = 0; i < percents.length; ++i) {
                if (percents[i] < 0 || percents[i] > 100) {
                    SWT.error(5);
                }
                if (i > 0 && percents[i] < percents[i - 1]) {
                    SWT.error(5);
                }
            }
            if (this.getDisplay().getDepth() < 15) {
                colors = new Color[] { colors[colors.length - 1] };
                percents = new int[0];
            }
        }
        if (this.gradientColors != null && colors != null && this.gradientColors.length == colors.length) {
            boolean same = false;
            for (int j = 0; j < this.gradientColors.length; ++j) {
                if (this.gradientColors[j] == null) {
                    same = (colors[j] == null);
                }
                else {
                    same = this.gradientColors[j].equals(colors[j]);
                }
                if (!same) {
                    break;
                }
            }
            if (same) {
                for (int j = 0; j < this.gradientPercents.length; ++j) {
                    same = (this.gradientPercents[j] == percents[j]);
                    if (!same) {
                        break;
                    }
                }
            }
            if (same && this.gradientVertical == vertical) {
                return;
            }
        }
        if (colors == null) {
            this.gradientColors = null;
            this.gradientPercents = null;
            this.gradientVertical = false;
            this.setBackground(null);
        }
        else {
            this.gradientColors = new Color[colors.length];
            for (int i = 0; i < colors.length; ++i) {
                this.gradientColors[i] = colors[i];
            }
            this.gradientPercents = new int[percents.length];
            for (int i = 0; i < percents.length; ++i) {
                this.gradientPercents[i] = percents[i];
            }
            this.gradientVertical = vertical;
            this.setBackground(this.gradientColors[this.gradientColors.length - 1]);
        }
        this.redraw();
    }
    
    @Override
    public void setBackgroundImage(final Image image) {
        super.setBackgroundImage(image);
        this.renderer.createAntialiasColors();
        this.redraw();
    }
    
    public void setBorderVisible(final boolean show) {
        this.checkWidget();
        if (this.borderVisible == show) {
            return;
        }
        this.borderVisible = show;
        this.updateFolder(2);
    }
    
    void updateButtons() {
        final Display display = this.getDisplay();
        if (this.showMax) {
            if (this.minMaxTb == null) {
                this.minMaxTb = new ToolBar(this, 8388608);
                this.initAccessibleMinMaxTb();
                this.addTabControl(this.minMaxTb, 131072, 0, false);
            }
            if (this.maxItem == null) {
                this.maxItem = new ToolItem(this.minMaxTb, 8);
                if (this.maxImage == null) {
                    this.maxImage = this.createButtonImage(display, -5);
                }
                this.maxItem.setImage(this.maxImage);
                this.maxItem.setToolTipText(this.maximized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Maximize"));
                this.maxItem.addListener(13, this.listener);
            }
        }
        else if (this.maxItem != null) {
            this.maxItem.dispose();
            this.maxItem = null;
        }
        if (this.showMin) {
            if (this.minMaxTb == null) {
                this.minMaxTb = new ToolBar(this, 8388608);
                this.initAccessibleMinMaxTb();
                this.addTabControl(this.minMaxTb, 131072, 0, false);
            }
            if (this.minItem == null) {
                this.minItem = new ToolItem(this.minMaxTb, 8, 0);
                if (this.minImage == null) {
                    this.minImage = this.createButtonImage(display, -6);
                }
                this.minItem.setImage(this.minImage);
                this.minItem.setToolTipText(this.minimized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Minimize"));
                this.minItem.addListener(13, this.listener);
            }
        }
        else if (this.minItem != null) {
            this.minItem.dispose();
            this.minItem = null;
        }
        if (this.minMaxTb != null && this.minMaxTb.getItemCount() == 0) {
            this.removeTabControl(this.minMaxTb, false);
            this.minMaxTb.dispose();
            this.minMaxTb = null;
        }
    }
    
    void setButtonBounds() {
        if (this.showChevron) {
            this.updateChevronImage(false);
        }
        final Point size = this.getSize();
        final boolean[][] overflow = new boolean[1][0];
        final Rectangle[] rects = this.computeControlBounds(size, overflow);
        if (this.fixedTabHeight != -1) {
            final int height = this.fixedTabHeight;
            if (!this.hovering) {
                this.hoverTb = false;
                final Rectangle tabBounds = this.getBounds();
                for (int i = 0; i < rects.length; ++i) {
                    if (!overflow[0][i] && rects[i].height > height) {
                        this.hoverTb = true;
                        break;
                    }
                }
                if (this.hoverTb) {
                    for (int i = 0; i < rects.length; ++i) {
                        if (!overflow[0][i] && rects[i].height > height) {
                            rects[i].x = tabBounds.width + 20;
                        }
                    }
                }
            }
        }
        int headerHeight = 0;
        for (int j = 0; j < rects.length; ++j) {
            if (!overflow[0][j]) {
                headerHeight = Math.max(rects[j].height, headerHeight);
            }
        }
        boolean changed = false;
        this.ignoreResize = true;
        for (int i = 0; i < this.controls.length; ++i) {
            if (!this.controls[i].isDisposed()) {
                if (overflow[0][i]) {
                    this.controls[i].setBounds(rects[i]);
                }
                else {
                    this.controls[i].moveAbove(null);
                    this.controls[i].setBounds(rects[i].x, rects[i].y, rects[i].width, headerHeight);
                }
            }
            if (!changed && !rects[i].equals(this.controlRects[i])) {
                changed = true;
            }
        }
        this.ignoreResize = false;
        this.controlRects = rects;
        if (changed || this.hovering) {
            this.updateBkImages(false);
        }
    }
    
    int getChevronCount() {
        final int itemCount = this.items.length;
        int count;
        if (this.single) {
            count = ((this.selectedIndex == -1) ? itemCount : (itemCount - 1));
        }
        else {
            int showCount;
            for (showCount = 0; showCount < this.priority.length && this.items[this.priority[showCount]].showing; ++showCount) {}
            count = itemCount - showCount;
        }
        return count;
    }
    
    private void updateChevronImage(final boolean styleChange) {
        if (styleChange && this.chevronImage == null) {
            return;
        }
        final int newCount = this.getChevronCount();
        if (!styleChange && this.chevronCount == newCount) {
            return;
        }
        if (this.chevronImage != null) {
            this.chevronImage.dispose();
        }
        this.chevronImage = this.createButtonImage(this.getDisplay(), -7);
        this.chevronItem.setImage(this.chevronImage);
        this.chevronCount = newCount;
    }
    
    @Override
    public boolean setFocus() {
        this.checkWidget();
        final Control focusControl = this.getDisplay().getFocusControl();
        final boolean fixFocus = this.isAncestor(focusControl);
        if (fixFocus) {
            final CTabItem item = this.getSelection();
            if (item != null && item.setFocus()) {
                return true;
            }
        }
        return super.setFocus();
    }
    
    boolean isAncestor(Control control) {
        while (control != null && control != this && !(control instanceof Shell)) {
            control = control.getParent();
        }
        return control == this;
    }
    
    @Override
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.equals(this.getFont())) {
            return;
        }
        super.setFont(font);
        this.oldFont = this.getFont();
        this.renderer.resetChevronFont();
        this.updateChevronImage(true);
        this.updateFolder(2);
    }
    
    @Override
    public void setForeground(final Color color) {
        super.setForeground(color);
        this.updateChevronImage(true);
        this.redraw();
    }
    
    public void setInsertMark(final CTabItem item, final boolean after) {
        this.checkWidget();
    }
    
    public void setInsertMark(final int index, final boolean after) {
        this.checkWidget();
        if (index < -1 || index >= this.getItemCount()) {
            SWT.error(5);
        }
    }
    
    boolean setItemLocation(final GC gc) {
        boolean changed = false;
        if (this.items.length == 0) {
            return false;
        }
        final Rectangle trim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
        final int borderBottom = trim.height + trim.y;
        final int borderTop = -trim.y;
        final Point size = this.getSize();
        final int y = this.onBottom ? Math.max(borderBottom, size.y - borderBottom - this.tabHeight) : borderTop;
        final Point closeButtonSize = this.renderer.computeSize(-8, 0, gc, -1, -1);
        final int leftItemEdge = this.getLeftItemEdge(gc, -3);
        if (this.single) {
            final int defaultX = this.getDisplay().getBounds().width + 10;
            for (int i = 0; i < this.items.length; ++i) {
                final CTabItem item = this.items[i];
                if (i == this.selectedIndex) {
                    this.firstIndex = this.selectedIndex;
                    final int oldX = item.x;
                    final int oldY = item.y;
                    item.x = leftItemEdge;
                    item.y = y;
                    item.showing = true;
                    if (this.showClose || item.showClose) {
                        item.closeRect.x = leftItemEdge - this.renderer.computeTrim(i, 0, 0, 0, 0, 0).x;
                        item.closeRect.y = (this.onBottom ? (size.y - borderBottom - this.tabHeight + (this.tabHeight - closeButtonSize.y) / 2) : (borderTop + (this.tabHeight - closeButtonSize.y) / 2));
                    }
                    if (item.x != oldX || item.y != oldY) {
                        changed = true;
                    }
                }
                else {
                    item.x = defaultX;
                    item.showing = false;
                }
            }
        }
        else {
            final int rightItemEdge = this.getRightItemEdge(gc);
            final int maxWidth = rightItemEdge - leftItemEdge;
            int width = 0;
            for (int j = 0; j < this.priority.length; ++j) {
                final CTabItem item2 = this.items[this.priority[j]];
                width += item2.width;
                item2.showing = (j == 0 || (item2.width > 0 && width <= maxWidth));
            }
            int x = this.getLeftItemEdge(gc, -2);
            final int defaultX2 = this.getDisplay().getBounds().width + 10;
            this.firstIndex = this.items.length - 1;
            for (int k = 0; k < this.items.length; ++k) {
                final CTabItem item3 = this.items[k];
                if (!item3.showing) {
                    if (item3.x != defaultX2) {
                        changed = true;
                    }
                    item3.x = defaultX2;
                }
                else {
                    this.firstIndex = Math.min(this.firstIndex, k);
                    if (item3.x != x || item3.y != y) {
                        changed = true;
                    }
                    item3.x = x;
                    item3.y = y;
                    int state = 0;
                    if (k == this.selectedIndex) {
                        state |= 0x2;
                    }
                    final Rectangle edgeTrim = this.renderer.computeTrim(k, state, 0, 0, 0, 0);
                    item3.closeRect.x = item3.x + item3.width - (edgeTrim.width + edgeTrim.x) - closeButtonSize.x;
                    item3.closeRect.y = (this.onBottom ? (size.y - borderBottom - this.tabHeight + (this.tabHeight - closeButtonSize.y) / 2) : (borderTop + (this.tabHeight - closeButtonSize.y) / 2));
                    x += item3.width;
                    if (!this.simple && k == this.selectedIndex) {
                        x -= this.renderer.curveIndent;
                    }
                }
            }
        }
        return changed;
    }
    
    void setItemOrder(final int[] indices) {
        this.checkWidget();
        if (indices == null) {
            SWT.error(4);
        }
        if (indices.length != this.items.length) {
            SWT.error(5);
        }
        int newSelectedIndex = -1;
        final boolean[] seen = new boolean[this.items.length];
        final CTabItem[] temp = new CTabItem[this.items.length];
        for (int i = 0; i < indices.length; ++i) {
            final int index = indices[i];
            if (0 > index || index >= this.items.length) {
                SWT.error(5);
            }
            if (seen[index]) {
                SWT.error(5);
            }
            seen[index] = true;
            if (index == this.selectedIndex) {
                newSelectedIndex = i;
            }
            temp[i] = this.items[index];
        }
        this.items = temp;
        this.selectedIndex = newSelectedIndex;
        this.updateFolder(2);
    }
    
    boolean setItemSize(final GC gc) {
        boolean changed = false;
        if (this.isDisposed()) {
            return changed;
        }
        final Point size = this.getSize();
        if (size.x <= 0 || size.y <= 0) {
            return changed;
        }
        final ToolBar chevron = this.getChevron();
        if (chevron != null) {
            chevron.setVisible(false);
        }
        this.showChevron = false;
        if (this.single) {
            this.showChevron = (this.chevronVisible && this.items.length > 1);
            if (this.showChevron) {
                chevron.setVisible(true);
            }
            if (this.selectedIndex != -1) {
                final CTabItem tab = this.items[this.selectedIndex];
                int width = this.renderer.computeSize(this.selectedIndex, 2, gc, -1, -1).x;
                width = Math.min(width, this.getRightItemEdge(gc) - this.getLeftItemEdge(gc, -3));
                if (tab.height != this.tabHeight || tab.width != width) {
                    changed = true;
                    tab.shortenedText = null;
                    tab.shortenedTextWidth = 0;
                    tab.height = this.tabHeight;
                    tab.width = width;
                    final Rectangle closeRect = tab.closeRect;
                    final Rectangle closeRect2 = tab.closeRect;
                    final int n = 0;
                    closeRect2.height = 0;
                    closeRect.width = 0;
                    if (this.showClose || tab.showClose) {
                        final Point closeSize = this.renderer.computeSize(-8, 2, gc, -1, -1);
                        tab.closeRect.width = closeSize.x;
                        tab.closeRect.height = closeSize.y;
                    }
                }
            }
            return changed;
        }
        if (this.items.length == 0) {
            return changed;
        }
        int tabAreaWidth = Math.max(0, this.getRightItemEdge(gc) - this.getLeftItemEdge(gc, -3));
        int minWidth = 0;
        final int[] minWidths = new int[this.items.length];
        for (final int element : this.priority) {
            final int index = element;
            int state = 16777216;
            if (index == this.selectedIndex) {
                state |= 0x2;
            }
            minWidths[index] = this.renderer.computeSize(index, state, gc, -1, -1).x;
            minWidth += minWidths[index];
            if (minWidth > tabAreaWidth) {
                break;
            }
        }
        int[] widths;
        if (minWidth > tabAreaWidth) {
            this.showChevron = (this.chevronVisible && this.items.length > 1);
            if (this.showChevron) {
                tabAreaWidth -= chevron.computeSize(-1, -1).x;
                chevron.setVisible(true);
            }
            widths = minWidths;
            final int index2 = (this.selectedIndex != -1) ? this.selectedIndex : 0;
            if (tabAreaWidth < widths[index2]) {
                widths[index2] = Math.max(0, tabAreaWidth);
            }
        }
        else {
            int maxWidth = 0;
            final int[] maxWidths = new int[this.items.length];
            for (int i = 0; i < this.items.length; ++i) {
                int state2 = 0;
                if (i == this.selectedIndex) {
                    state2 |= 0x2;
                }
                maxWidths[i] = this.renderer.computeSize(i, state2, gc, -1, -1).x;
                maxWidth += maxWidths[i];
            }
            if (maxWidth <= tabAreaWidth) {
                widths = maxWidths;
            }
            else {
                int extra = (tabAreaWidth - minWidth) / this.items.length;
                while (true) {
                    int large = 0;
                    int totalWidth = 0;
                    for (int j = 0; j < this.items.length; ++j) {
                        if (maxWidths[j] > minWidths[j] + extra) {
                            totalWidth += minWidths[j] + extra;
                            ++large;
                        }
                        else {
                            totalWidth += maxWidths[j];
                        }
                    }
                    if (totalWidth >= tabAreaWidth) {
                        --extra;
                        break;
                    }
                    if (large == 0) {
                        break;
                    }
                    if (tabAreaWidth - totalWidth < large) {
                        break;
                    }
                    ++extra;
                }
                widths = new int[this.items.length];
                for (int k = 0; k < this.items.length; ++k) {
                    widths[k] = Math.min(maxWidths[k], minWidths[k] + extra);
                }
            }
        }
        for (int l = 0; l < this.items.length; ++l) {
            final CTabItem tab2 = this.items[l];
            final int width2 = widths[l];
            if (tab2.height != this.tabHeight || tab2.width != width2) {
                changed = true;
                tab2.shortenedText = null;
                tab2.shortenedTextWidth = 0;
                tab2.height = this.tabHeight;
                tab2.width = width2;
                final Rectangle closeRect3 = tab2.closeRect;
                final Rectangle closeRect4 = tab2.closeRect;
                final int n2 = 0;
                closeRect4.height = 0;
                closeRect3.width = 0;
                if ((this.showClose || tab2.showClose) && (l == this.selectedIndex || this.showUnselectedClose)) {
                    final Point closeSize2 = this.renderer.computeSize(-8, 0, gc, -1, -1);
                    tab2.closeRect.width = closeSize2.x;
                    tab2.closeRect.height = closeSize2.y;
                }
            }
        }
        return changed;
    }
    
    public void setMaximizeVisible(final boolean visible) {
        this.checkWidget();
        if (this.showMax == visible) {
            return;
        }
        this.showMax = visible;
        this.updateFolder(10);
    }
    
    @Override
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setMaximized(final boolean maximize) {
        this.checkWidget();
        if (this.maximized == maximize) {
            return;
        }
        if (maximize && this.minimized) {
            this.setMinimized(false);
        }
        this.maximized = maximize;
        if (this.minMaxTb != null && this.maxItem != null) {
            if (this.maxImage != null) {
                this.maxImage.dispose();
            }
            this.maxImage = this.createButtonImage(this.getDisplay(), -5);
            this.maxItem.setImage(this.maxImage);
            this.maxItem.setToolTipText(this.maximized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Maximize"));
        }
    }
    
    public void setMinimizeVisible(final boolean visible) {
        this.checkWidget();
        if (this.showMin == visible) {
            return;
        }
        this.showMin = visible;
        this.updateFolder(10);
    }
    
    public void setMinimized(final boolean minimize) {
        this.checkWidget();
        if (this.minimized == minimize) {
            return;
        }
        if (minimize && this.maximized) {
            this.setMaximized(false);
        }
        this.minimized = minimize;
        if (this.minMaxTb != null && this.minItem != null) {
            if (this.minImage != null) {
                this.minImage.dispose();
            }
            this.minImage = this.createButtonImage(this.getDisplay(), -6);
            this.minItem.setImage(this.minImage);
            this.minItem.setToolTipText(this.minimized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Minimize"));
        }
    }
    
    public void setMinimumCharacters(final int count) {
        this.checkWidget();
        if (count < 0) {
            SWT.error(6);
        }
        if (this.minChars == count) {
            return;
        }
        this.minChars = count;
        this.updateFolder(4);
    }
    
    public void setMRUVisible(final boolean show) {
        this.checkWidget();
        if (this.mru == show) {
            return;
        }
        if (!(this.mru = show)) {
            if (this.firstIndex == -1) {
                return;
            }
            final int idx = this.firstIndex;
            int next = 0;
            for (int i = this.firstIndex; i < this.items.length; ++i) {
                this.priority[next++] = i;
            }
            for (int i = 0; i < idx; ++i) {
                this.priority[next++] = i;
            }
            this.updateFolder(4);
        }
    }
    
    public void setRenderer(CTabFolderRenderer renderer) {
        this.checkWidget();
        if (this.renderer == renderer || (this.useDefaultRenderer && renderer == null)) {
            return;
        }
        if (this.renderer != null) {
            this.renderer.dispose();
        }
        this.useDefaultRenderer = (renderer == null);
        if (this.useDefaultRenderer) {
            renderer = new CTabFolderRenderer(this);
        }
        this.renderer = renderer;
        this.updateFolder(2);
    }
    
    public void setSelection(final CTabItem item) {
        this.checkWidget();
        if (item == null) {
            SWT.error(4);
        }
        final int index = this.indexOf(item);
        this.setSelection(index);
    }
    
    public void setSelection(final int index) {
        this.checkWidget();
        if (index < 0 || index >= this.items.length) {
            return;
        }
        final CTabItem selection = this.items[index];
        if (this.selectedIndex == index) {
            this.showItem(selection);
            return;
        }
        final int oldIndex = this.selectedIndex;
        this.selectedIndex = index;
        if (oldIndex != -1) {
            this.items[oldIndex].closeImageState = 8;
            final CTabItem cTabItem3;
            final CTabItem cTabItem = cTabItem3 = this.items[oldIndex];
            cTabItem3.state &= 0xFFFFFFFD;
        }
        selection.closeImageState = 0;
        selection.showing = false;
        final CTabItem cTabItem4;
        final CTabItem cTabItem2 = cTabItem4 = selection;
        cTabItem4.state |= 0x2;
        final Control newControl = selection.control;
        Control oldControl = null;
        if (oldIndex != -1) {
            oldControl = this.items[oldIndex].control;
        }
        if (newControl != oldControl) {
            if (newControl != null && !newControl.isDisposed()) {
                newControl.setBounds(this.getClientArea());
                newControl.setVisible(true);
            }
            if (oldControl != null && !oldControl.isDisposed()) {
                oldControl.setVisible(false);
            }
        }
        this.showItem(selection);
        this.redraw();
    }
    
    void setSelection(final int index, final boolean notify) {
        final int oldSelectedIndex = this.selectedIndex;
        this.setSelection(index);
        if (notify && this.selectedIndex != oldSelectedIndex && this.selectedIndex != -1) {
            final Event event = new Event();
            event.item = this.getItem(this.selectedIndex);
            this.notifyListeners(13, event);
        }
    }
    
    public void setSelectionBackground(Color color) {
        if (this.inDispose) {
            return;
        }
        this.checkWidget();
        this.setSelectionHighlightGradientColor(null);
        if (this.selectionBackground == color) {
            return;
        }
        if (color == null) {
            color = this.getDisplay().getSystemColor(25);
        }
        this.selectionBackground = color;
        this.renderer.createAntialiasColors();
        if (this.selectedIndex > -1) {
            this.redraw();
        }
    }
    
    public void setSelectionBackground(final Color[] colors, final int[] percents) {
        this.setSelectionBackground(colors, percents, false);
    }
    
    public void setSelectionBackground(Color[] colors, int[] percents, final boolean vertical) {
        this.checkWidget();
        Color highlightBeginColor = null;
        int colorsLength;
        if (colors != null) {
            if (percents == null || (percents.length != colors.length - 1 && percents.length != colors.length - 2)) {
                SWT.error(5);
            }
            for (int i = 0; i < percents.length; ++i) {
                if (percents[i] < 0 || percents[i] > 100) {
                    SWT.error(5);
                }
                if (i > 0 && percents[i] < percents[i - 1]) {
                    SWT.error(5);
                }
            }
            if (percents.length == colors.length - 2) {
                highlightBeginColor = colors[colors.length - 1];
                colorsLength = colors.length - 1;
            }
            else {
                colorsLength = colors.length;
            }
            if (this.getDisplay().getDepth() < 15) {
                colors = new Color[] { colors[colorsLength - 1] };
                colorsLength = colors.length;
                percents = new int[0];
            }
        }
        else {
            colorsLength = 0;
        }
        if (this.selectionBgImage == null) {
            if (this.selectionGradientColors != null && colors != null && this.selectionGradientColors.length == colorsLength) {
                boolean same = false;
                for (int j = 0; j < this.selectionGradientColors.length; ++j) {
                    if (this.selectionGradientColors[j] == null) {
                        same = (colors[j] == null);
                    }
                    else {
                        same = this.selectionGradientColors[j].equals(colors[j]);
                    }
                    if (!same) {
                        break;
                    }
                }
                if (same) {
                    for (int j = 0; j < this.selectionGradientPercents.length; ++j) {
                        same = (this.selectionGradientPercents[j] == percents[j]);
                        if (!same) {
                            break;
                        }
                    }
                }
                if (same && this.selectionGradientVertical == vertical) {
                    return;
                }
            }
        }
        else {
            this.selectionBgImage = null;
        }
        if (colors == null) {
            this.selectionGradientColors = null;
            this.selectionGradientPercents = null;
            this.selectionGradientVertical = false;
            this.setSelectionBackground((Color)null);
            this.setSelectionHighlightGradientColor(null);
        }
        else {
            this.selectionGradientColors = new Color[colorsLength];
            for (int i = 0; i < colorsLength; ++i) {
                this.selectionGradientColors[i] = colors[i];
            }
            this.selectionGradientPercents = new int[percents.length];
            for (int i = 0; i < percents.length; ++i) {
                this.selectionGradientPercents[i] = percents[i];
            }
            this.selectionGradientVertical = vertical;
            this.setSelectionBackground(this.selectionGradientColors[this.selectionGradientColors.length - 1]);
            this.setSelectionHighlightGradientColor(highlightBeginColor);
        }
        if (this.selectedIndex > -1) {
            this.redraw();
        }
    }
    
    void setSelectionHighlightGradientColor(final Color start) {
        if (this.inDispose) {
            return;
        }
        this.renderer.setSelectionHighlightGradientColor(start);
    }
    
    public void setSelectionBackground(final Image image) {
        this.checkWidget();
        this.setSelectionHighlightGradientColor(null);
        if (image == this.selectionBgImage) {
            return;
        }
        if (image != null) {
            this.selectionGradientColors = null;
            this.selectionGradientPercents = null;
            this.renderer.disposeSelectionHighlightGradientColors();
        }
        this.selectionBgImage = image;
        this.renderer.createAntialiasColors();
        if (this.selectedIndex > -1) {
            this.redraw();
        }
    }
    
    public void setSelectionForeground(Color color) {
        this.checkWidget();
        if (this.selectionForeground == color) {
            return;
        }
        if (color == null) {
            color = this.getDisplay().getSystemColor(24);
        }
        this.selectionForeground = color;
        if (this.selectedIndex > -1) {
            this.redraw();
        }
    }
    
    public void setSimple(final boolean simple) {
        this.checkWidget();
        if (this.simple != simple) {
            this.simple = simple;
            this.updateFolder(10);
        }
    }
    
    public void setSingle(final boolean single) {
        this.checkWidget();
        if (this.single != single) {
            if (!(this.single = single)) {
                for (int i = 0; i < this.items.length; ++i) {
                    if (i != this.selectedIndex && this.items[i].closeImageState == 0) {
                        this.items[i].closeImageState = 8;
                    }
                }
            }
            this.updateFolder(2);
        }
    }
    
    int getControlY(final Point size, final Rectangle[] rects, final int borderBottom, final int borderTop, final int i) {
        final int center = (this.fixedTabHeight != -1) ? 0 : ((this.tabHeight - rects[i].height) / 2);
        return this.onBottom ? (size.y - borderBottom - this.tabHeight + center) : (1 + borderTop + center);
    }
    
    public void setTabHeight(final int height) {
        this.checkWidget();
        if (height < -1) {
            SWT.error(5);
        }
        this.fixedTabHeight = height;
        this.updateFolder(8);
    }
    
    public void setTabPosition(final int position) {
        this.checkWidget();
        if (position != 128 && position != 1024) {
            SWT.error(5);
        }
        if (this.onBottom != (position == 1024)) {
            this.onBottom = (position == 1024);
            this.updateFolder(2);
        }
    }
    
    public void setTopRight(final Control control) {
        this.setTopRight(control, 131072);
    }
    
    public void setTopRight(final Control control, int alignment) {
        this.checkWidget();
        if (alignment != 131072 && alignment != 4 && alignment != 131136) {
            SWT.error(5);
        }
        if (control != null && (control.isDisposed() || control.getParent() != this)) {
            SWT.error(5);
        }
        if (this.topRight == control && this.topRightAlignment == alignment) {
            return;
        }
        if (this.topRight != null && !this.topRight.isDisposed()) {
            this.removeTabControl(this.topRight, false);
        }
        this.topRight = control;
        this.topRightAlignment = alignment;
        alignment &= 0xFFFDFFFF;
        if (control != null) {
            this.addTabControl(control, 0x20000 | alignment, -1, false);
        }
        this.updateFolder(10);
    }
    
    public void setUnselectedCloseVisible(final boolean visible) {
        this.checkWidget();
        if (this.showUnselectedClose == visible) {
            return;
        }
        this.showUnselectedClose = visible;
        this.updateFolder(2);
    }
    
    public void setUnselectedImageVisible(final boolean visible) {
        this.checkWidget();
        if (this.showUnselectedImage == visible) {
            return;
        }
        this.showUnselectedImage = visible;
        this.updateFolder(2);
    }
    
    public void showItem(final CTabItem item) {
        this.checkWidget();
        if (item == null) {
            SWT.error(4);
        }
        if (item.isDisposed()) {
            SWT.error(5);
        }
        final int index = this.indexOf(item);
        if (index == -1) {
            SWT.error(5);
        }
        int idx = -1;
        for (int i = 0; i < this.priority.length; ++i) {
            if (this.priority[i] == index) {
                idx = i;
                break;
            }
        }
        if (this.mru) {
            final int[] newPriority = new int[this.priority.length];
            System.arraycopy(this.priority, 0, newPriority, 1, idx);
            System.arraycopy(this.priority, idx + 1, newPriority, idx + 1, this.priority.length - idx - 1);
            newPriority[0] = index;
            this.priority = newPriority;
        }
        if (item.showing) {
            return;
        }
        this.updateFolder(4);
    }
    
    void showList(final Rectangle rect) {
        class llIIII extends SelectionAdapter
        {
            final /* synthetic */ CTabFolder this$0;
            
            llIIII(final CTabFolder this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final MenuItem menuItem = (MenuItem)e.widget;
                final int index = this.this$0.indexOf((CTabItem)menuItem.getData("CTabFolder_showList_Index"));
                this.this$0.setSelection(index, true);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: arraylength    
        //     5: ifeq            15
        //     8: aload_0         /* this */
        //     9: getfield        org/eclipse/swt/custom/CTabFolder.showChevron:Z
        //    12: ifne            16
        //    15: return         
        //    16: aload_0         /* this */
        //    17: getfield        org/eclipse/swt/custom/CTabFolder.showMenu:Lorg/eclipse/swt/widgets/Menu;
        //    20: ifnull          33
        //    23: aload_0         /* this */
        //    24: getfield        org/eclipse/swt/custom/CTabFolder.showMenu:Lorg/eclipse/swt/widgets/Menu;
        //    27: invokevirtual   org/eclipse/swt/widgets/Menu.isDisposed:()Z
        //    30: ifeq            59
        //    33: aload_0         /* this */
        //    34: new             Lorg/eclipse/swt/widgets/Menu;
        //    37: dup            
        //    38: aload_0         /* this */
        //    39: invokevirtual   org/eclipse/swt/custom/CTabFolder.getShell:()Lorg/eclipse/swt/widgets/Shell;
        //    42: aload_0         /* this */
        //    43: invokevirtual   org/eclipse/swt/custom/CTabFolder.getStyle:()I
        //    46: ldc_w           100663296
        //    49: iand           
        //    50: invokespecial   org/eclipse/swt/widgets/Menu.<init>:(Lorg/eclipse/swt/widgets/Decorations;I)V
        //    53: putfield        org/eclipse/swt/custom/CTabFolder.showMenu:Lorg/eclipse/swt/widgets/Menu;
        //    56: goto            96
        //    59: aload_0         /* this */
        //    60: getfield        org/eclipse/swt/custom/CTabFolder.showMenu:Lorg/eclipse/swt/widgets/Menu;
        //    63: invokevirtual   org/eclipse/swt/widgets/Menu.getItems:()[Lorg/eclipse/swt/widgets/MenuItem;
        //    66: astore_2       
        //    67: aload_2        
        //    68: arraylength    
        //    69: istore_3       
        //    70: iconst_0       
        //    71: istore          4
        //    73: iload           4
        //    75: iload_3        
        //    76: if_icmpge       96
        //    79: aload_2        
        //    80: iload           4
        //    82: aaload         
        //    83: astore          item
        //    85: aload           item
        //    87: invokevirtual   org/eclipse/swt/widgets/MenuItem.dispose:()V
        //    90: iinc            4, 1
        //    93: goto            73
        //    96: ldc_w           "CTabFolder_showList_Index"
        //    99: astore_2        /* id */
        //   100: aload_0         /* this */
        //   101: getfield        org/eclipse/swt/custom/CTabFolder.items:[Lorg/eclipse/swt/custom/CTabItem;
        //   104: astore_3       
        //   105: aload_3        
        //   106: arraylength    
        //   107: istore          4
        //   109: iconst_0       
        //   110: istore          5
        //   112: iload           5
        //   114: iload           4
        //   116: if_icmpge       205
        //   119: aload_3        
        //   120: iload           5
        //   122: aaload         
        //   123: astore          tab
        //   125: aload           tab
        //   127: getfield        org/eclipse/swt/custom/CTabItem.showing:Z
        //   130: ifne            199
        //   133: new             Lorg/eclipse/swt/widgets/MenuItem;
        //   136: dup            
        //   137: aload_0         /* this */
        //   138: getfield        org/eclipse/swt/custom/CTabFolder.showMenu:Lorg/eclipse/swt/widgets/Menu;
        //   141: iconst_0       
        //   142: invokespecial   org/eclipse/swt/widgets/MenuItem.<init>:(Lorg/eclipse/swt/widgets/Menu;I)V
        //   145: astore          item2
        //   147: aload           item2
        //   149: aload           tab
        //   151: invokevirtual   org/eclipse/swt/custom/CTabItem.getText:()Ljava/lang/String;
        //   154: ldc_w           "\n"
        //   157: ldc_w           " "
        //   160: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   163: invokevirtual   org/eclipse/swt/widgets/MenuItem.setText:(Ljava/lang/String;)V
        //   166: aload           item2
        //   168: aload           tab
        //   170: invokevirtual   org/eclipse/swt/custom/CTabItem.getImage:()Lorg/eclipse/swt/graphics/Image;
        //   173: invokevirtual   org/eclipse/swt/widgets/MenuItem.setImage:(Lorg/eclipse/swt/graphics/Image;)V
        //   176: aload           item2
        //   178: ldc_w           "CTabFolder_showList_Index"
        //   181: aload           tab
        //   183: invokevirtual   org/eclipse/swt/widgets/MenuItem.setData:(Ljava/lang/String;Ljava/lang/Object;)V
        //   186: aload           item2
        //   188: new             Lorg/eclipse/swt/custom/llIIII;
        //   191: dup            
        //   192: aload_0         /* this */
        //   193: invokespecial   org/eclipse/swt/custom/llIIII.<init>:(Lorg/eclipse/swt/custom/CTabFolder;)V
        //   196: invokevirtual   org/eclipse/swt/widgets/MenuItem.addSelectionListener:(Lorg/eclipse/swt/events/SelectionListener;)V
        //   199: iinc            5, 1
        //   202: goto            112
        //   205: aload_1         /* rect */
        //   206: getfield        org/eclipse/swt/graphics/Rectangle.x:I
        //   209: istore_3        /* x */
        //   210: aload_1         /* rect */
        //   211: getfield        org/eclipse/swt/graphics/Rectangle.y:I
        //   214: aload_1         /* rect */
        //   215: getfield        org/eclipse/swt/graphics/Rectangle.height:I
        //   218: iadd           
        //   219: istore          y
        //   221: aload_0         /* this */
        //   222: invokevirtual   org/eclipse/swt/custom/CTabFolder.getDisplay:()Lorg/eclipse/swt/widgets/Display;
        //   225: aload_0         /* this */
        //   226: aconst_null    
        //   227: iload_3         /* x */
        //   228: iload           y
        //   230: invokevirtual   org/eclipse/swt/widgets/Display.map:(Lorg/eclipse/swt/widgets/Control;Lorg/eclipse/swt/widgets/Control;II)Lorg/eclipse/swt/graphics/Point;
        //   233: astore          location
        //   235: aload_0         /* this */
        //   236: getfield        org/eclipse/swt/custom/CTabFolder.showMenu:Lorg/eclipse/swt/widgets/Menu;
        //   239: aload           location
        //   241: getfield        org/eclipse/swt/graphics/Point.x:I
        //   244: aload           location
        //   246: getfield        org/eclipse/swt/graphics/Point.y:I
        //   249: invokevirtual   org/eclipse/swt/widgets/Menu.setLocation:(II)V
        //   252: aload_0         /* this */
        //   253: getfield        org/eclipse/swt/custom/CTabFolder.showMenu:Lorg/eclipse/swt/widgets/Menu;
        //   256: iconst_1       
        //   257: invokevirtual   org/eclipse/swt/widgets/Menu.setVisible:(Z)V
        //   260: return         
        //    StackMapTable: 00 09 0F 00 10 19 FE 00 0D 07 05 19 01 01 F8 00 16 FF 00 0F 00 06 07 00 02 07 00 CB 07 02 AC 07 01 A6 01 01 00 00 FB 00 56 F8 00 05
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void showSelection() {
        this.checkWidget();
        if (this.selectedIndex != -1) {
            this.showItem(this.getSelection());
        }
    }
    
    void _setToolTipText(final int x, final int y) {
        final String oldTip = this.getToolTipText();
        final String newTip = this._getToolTip(x, y);
        if (newTip == null || !newTip.equals(oldTip)) {
            this.setToolTipText(newTip);
        }
    }
    
    boolean updateItems() {
        return this.updateItems(this.selectedIndex);
    }
    
    boolean updateItems(final int showIndex) {
        final GC gc = new GC(this);
        if (!this.single && !this.mru && showIndex != -1) {
            int firstIndex;
            if (this.priority[0] < (firstIndex = showIndex)) {
                final int maxWidth = this.getRightItemEdge(gc) - this.getLeftItemEdge(gc, -3);
                int width = 0;
                final int[] widths = new int[this.items.length];
                for (int i = this.priority[0]; i <= showIndex; ++i) {
                    int state = 16777216;
                    if (i == this.selectedIndex) {
                        state |= 0x2;
                    }
                    widths[i] = this.renderer.computeSize(i, state, gc, -1, -1).x;
                    width += widths[i];
                    if (width > maxWidth) {
                        break;
                    }
                }
                if (width > maxWidth) {
                    width = 0;
                    for (int i = showIndex; i >= 0; --i) {
                        int state = 16777216;
                        if (i == this.selectedIndex) {
                            state |= 0x2;
                        }
                        if (widths[i] == 0) {
                            widths[i] = this.renderer.computeSize(i, state, gc, -1, -1).x;
                        }
                        width += widths[i];
                        if (width > maxWidth) {
                            break;
                        }
                        firstIndex = i;
                    }
                }
                else {
                    firstIndex = this.priority[0];
                    for (int i = showIndex + 1; i < this.items.length; ++i) {
                        int state = 16777216;
                        if (i == this.selectedIndex) {
                            state |= 0x2;
                        }
                        widths[i] = this.renderer.computeSize(i, state, gc, -1, -1).x;
                        width += widths[i];
                        if (width >= maxWidth) {
                            break;
                        }
                    }
                    if (width < maxWidth) {
                        for (int i = this.priority[0] - 1; i >= 0; --i) {
                            int state = 16777216;
                            if (i == this.selectedIndex) {
                                state |= 0x2;
                            }
                            if (widths[i] == 0) {
                                widths[i] = this.renderer.computeSize(i, state, gc, -1, -1).x;
                            }
                            width += widths[i];
                            if (width > maxWidth) {
                                break;
                            }
                            firstIndex = i;
                        }
                    }
                }
            }
            if (firstIndex != this.priority[0]) {
                int index = 0;
                for (int j = firstIndex; j < this.items.length; ++j) {
                    this.priority[index++] = j;
                }
                for (int j = firstIndex - 1; j >= 0; --j) {
                    this.priority[index++] = j;
                }
            }
        }
        final boolean oldShowChevron = this.showChevron;
        boolean changed = this.setItemSize(gc);
        this.updateButtons();
        final boolean chevronChanged = this.showChevron != oldShowChevron;
        if (chevronChanged && this.updateTabHeight(false)) {
            changed |= this.setItemSize(gc);
        }
        changed |= this.setItemLocation(gc);
        this.setButtonBounds();
        changed |= chevronChanged;
        if (changed && this.getToolTipText() != null) {
            Point pt = this.getDisplay().getCursorLocation();
            pt = this.toControl(pt);
            this._setToolTipText(pt.x, pt.y);
        }
        gc.dispose();
        return changed;
    }
    
    boolean updateTabHeight(final boolean force) {
        final int oldHeight = this.tabHeight;
        final GC gc = new GC(this);
        this.tabHeight = this.renderer.computeSize(-2, 0, gc, -1, -1).y;
        gc.dispose();
        if (this.fixedTabHeight == -1 && this.controls != null && this.controls.length > 0) {
            for (int i = 0; i < this.controls.length; ++i) {
                if ((this.controlAlignments[i] & 0x40) == 0x0 && !this.controls[i].isDisposed() && this.controls[i].getVisible()) {
                    int topHeight = this.controls[i].computeSize(-1, -1).y;
                    topHeight += this.renderer.computeTrim(-2, 0, 0, 0, 0, 0).height + 1;
                    this.tabHeight = Math.max(topHeight, this.tabHeight);
                }
            }
        }
        if (!force && this.tabHeight == oldHeight) {
            return false;
        }
        this.oldSize = null;
        return true;
    }
    
    void updateFolder(final int flags) {
        this.updateFlags |= flags;
        if (this.updateRun != null) {
            return;
        }
        this.updateRun = (() -> {
            this.updateRun = null;
            if (this.isDisposed()) {
                return;
            }
            else {
                this.runUpdate();
                return;
            }
        });
        this.getDisplay().asyncExec(this.updateRun);
    }
    
    void runUpdate() {
        if (this.updateFlags == 0) {
            return;
        }
        final int flags = this.updateFlags;
        this.updateFlags = 0;
        final Rectangle rectBefore = this.getClientArea();
        this.updateButtons();
        this.updateTabHeight(false);
        this.updateItems(this.selectedIndex);
        if ((flags & 0x2) != 0x0) {
            this.redraw();
        }
        else if ((flags & 0x4) != 0x0) {
            this.redrawTabs();
        }
        final Rectangle rectAfter = this.getClientArea();
        if (!rectBefore.equals(rectAfter)) {
            this.notifyListeners(11, new Event());
            this.layout();
        }
    }
    
    void updateBkImages(final boolean colorChanged) {
        if (this.controls != null && this.controls.length > 0) {
            if (this.bkImageBounds == null) {
                this.bkImageBounds = new Rectangle[this.controls.length];
            }
            if (this.bkImageBounds.length != this.controls.length) {
                this.bkImageBounds = new Rectangle[this.controls.length];
            }
            for (int i = 0; i < this.controls.length; ++i) {
                final Control control = this.controls[i];
                if (!control.isDisposed()) {
                    if (this.hovering) {
                        if (control instanceof Composite) {
                            ((Composite)control).setBackgroundMode(0);
                        }
                        control.setBackgroundImage(null);
                        control.setBackground(this.getBackground());
                    }
                    else {
                        if (control instanceof Composite) {
                            ((Composite)control).setBackgroundMode(1);
                        }
                        final Rectangle bounds = control.getBounds();
                        final int tabHeight = this.getTabHeight();
                        final int height = this.getSize().y;
                        final boolean wrapped = this.onBottom ? (bounds.y + bounds.height < height - tabHeight) : (bounds.y > tabHeight);
                        if (wrapped || this.gradientColors == null) {
                            this.bkImageBounds[i] = null;
                            control.setBackgroundImage(null);
                            control.setBackground(this.getBackground());
                        }
                        else {
                            bounds.width = 10;
                            if (!this.onBottom) {
                                bounds.y = -bounds.y;
                                final Rectangle rectangle3;
                                final Rectangle rectangle = rectangle3 = bounds;
                                rectangle3.height -= 2 * bounds.y - 1;
                            }
                            else {
                                final Rectangle rectangle4;
                                final Rectangle rectangle2 = rectangle4 = bounds;
                                rectangle4.height += height - (bounds.y + bounds.height);
                                bounds.y = -1;
                            }
                            bounds.x = 0;
                            if (colorChanged || !bounds.equals(this.bkImageBounds[i])) {
                                this.bkImageBounds[i] = bounds;
                                if (this.controlBkImages[i] != null) {
                                    this.controlBkImages[i].dispose();
                                }
                                this.controlBkImages[i] = new Image(control.getDisplay(), bounds);
                                final GC gc = new GC(this.controlBkImages[i]);
                                this.renderer.draw(-4, 0, bounds, gc);
                                gc.dispose();
                                control.setBackground(null);
                                control.setBackgroundImage(this.controlBkImages[i]);
                            }
                        }
                    }
                }
            }
        }
    }
    
    String _getToolTip(final int x, final int y) {
        final CTabItem item = this.getItem(new Point(x, y));
        if (item == null) {
            return null;
        }
        if (!item.showing) {
            return null;
        }
        if ((this.showClose || item.showClose) && item.closeRect.contains(x, y)) {
            return SWT.getMessage("SWT_Close");
        }
        return item.getToolTipText();
    }
    
    void addTabControl(final Control control, final int flags) {
        this.checkWidget();
        this.addTabControl(control, flags, -1, true);
    }
    
    void addTabControl(final Control control, final int flags, int index, final boolean update) {
        switch (flags) {
            case 16384:
            case 131072:
            case 131076:
            case 131136:
            case 131140: {
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        if (control != null && control.getParent() != this) {
            SWT.error(5);
        }
        for (final Control ctrl : this.controls) {
            if (ctrl == control) {
                SWT.error(5);
            }
        }
        final int length = this.controls.length;
        control.addListener(11, this.listener);
        final Control[] newControls = new Control[length + 1];
        System.arraycopy(this.controls, 0, newControls, 0, length);
        this.controls = newControls;
        final int[] newAlignment = new int[length + 1];
        System.arraycopy(this.controlAlignments, 0, newAlignment, 0, length);
        this.controlAlignments = newAlignment;
        final Rectangle[] newRect = new Rectangle[length + 1];
        System.arraycopy(this.controlRects, 0, newRect, 0, length);
        this.controlRects = newRect;
        final Image[] newImage = new Image[length + 1];
        System.arraycopy(this.controlBkImages, 0, newImage, 0, length);
        this.controlBkImages = newImage;
        if (index == -1) {
            index = length;
            if (this.chevronTb != null && control != this.chevronTb) {
                --index;
            }
        }
        System.arraycopy(this.controls, index, this.controls, index + 1, length - index);
        System.arraycopy(this.controlAlignments, index, this.controlAlignments, index + 1, length - index);
        System.arraycopy(this.controlRects, index, this.controlRects, index + 1, length - index);
        System.arraycopy(this.controlBkImages, index, this.controlBkImages, index + 1, length - index);
        this.controls[index] = control;
        this.controlAlignments[index] = flags;
        this.controlRects[index] = new Rectangle(0, 0, 0, 0);
        if (update) {
            this.updateFolder(10);
        }
    }
    
    void removeTabControl(final Control control) {
        this.checkWidget();
        this.removeTabControl(control, true);
    }
    
    void removeTabControl(final Control control, final boolean update) {
        if (control != null && control.getParent() != this) {
            SWT.error(5);
        }
        int index = -1;
        for (int i = 0; i < this.controls.length; ++i) {
            if (this.controls[i] == control) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (!control.isDisposed()) {
            control.removeListener(11, this.listener);
            control.setBackground(null);
            control.setBackgroundImage(null);
            if (control instanceof Composite) {
                ((Composite)control).setBackgroundMode(0);
            }
        }
        if (this.controlBkImages[index] != null && !this.controlBkImages[index].isDisposed()) {
            this.controlBkImages[index].dispose();
        }
        if (this.controls.length == 1) {
            this.controls = new Control[0];
            this.controlAlignments = new int[0];
            this.controlRects = new Rectangle[0];
            this.controlBkImages = new Image[0];
        }
        else {
            final Control[] newControls = new Control[this.controls.length - 1];
            System.arraycopy(this.controls, 0, newControls, 0, index);
            System.arraycopy(this.controls, index + 1, newControls, index, this.controls.length - index - 1);
            this.controls = newControls;
            final int[] newAlignments = new int[this.controls.length];
            System.arraycopy(this.controlAlignments, 0, newAlignments, 0, index);
            System.arraycopy(this.controlAlignments, index + 1, newAlignments, index, this.controls.length - index);
            this.controlAlignments = newAlignments;
            final Rectangle[] newRects = new Rectangle[this.controls.length];
            System.arraycopy(this.controlRects, 0, newRects, 0, index);
            System.arraycopy(this.controlRects, index + 1, newRects, index, this.controls.length - index);
            this.controlRects = newRects;
            final Image[] newBkImages = new Image[this.controls.length];
            System.arraycopy(this.controlBkImages, 0, newBkImages, 0, index);
            System.arraycopy(this.controlBkImages, index + 1, newBkImages, index, this.controls.length - index);
            this.controlBkImages = newBkImages;
        }
        if (update) {
            this.updateFolder(10);
        }
    }
    
    int getWrappedHeight(final Point size) {
        final boolean[][] positions = { null };
        final Rectangle[] rects = this.computeControlBounds(size, positions);
        int minY = Integer.MAX_VALUE;
        int maxY = 0;
        int wrapHeight = 0;
        for (int i = 0; i < rects.length; ++i) {
            if (positions[0][i]) {
                minY = Math.min(minY, rects[i].y);
                maxY = Math.max(maxY, rects[i].y + rects[i].height);
                wrapHeight = maxY - minY;
            }
        }
        return wrapHeight;
    }
    
    void setChevronVisible(final boolean visible) {
        this.checkWidget();
        if (this.chevronVisible == visible) {
            return;
        }
        this.chevronVisible = visible;
        this.updateFolder(10);
    }
    
    boolean shouldHighlight() {
        return this.highlight && this.highlightEnabled;
    }
    
    public void setHighlightEnabled(final boolean enabled) {
        this.checkWidget();
        if (this.highlightEnabled == enabled) {
            return;
        }
        this.highlightEnabled = enabled;
        this.updateFolder(2);
    }
    
    public boolean getHighlightEnabled() {
        this.checkWidget();
        return this.highlightEnabled;
    }
    
    static {
        CTabFolder.borderInsideRGB = new RGB(132, 130, 132);
        CTabFolder.borderMiddleRGB = new RGB(143, 141, 138);
        CTabFolder.borderOutsideRGB = new RGB(171, 168, 165);
    }
}
