//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

public class CTabFolderRenderer
{
    protected CTabFolder parent;
    int[] curve;
    int[] topCurveHighlightStart;
    int[] topCurveHighlightEnd;
    int curveWidth;
    int curveIndent;
    int lastTabHeight;
    Color fillColor;
    Color selectionHighlightGradientBegin;
    Color[] selectionHighlightGradientColorsCache;
    Color selectedOuterColor;
    Color selectedInnerColor;
    Color tabAreaColor;
    Color lastBorderColor;
    private Font chevronFont;
    static final int[] TOP_LEFT_CORNER_HILITE;
    static final int[] TOP_LEFT_CORNER;
    static final int[] TOP_RIGHT_CORNER;
    static final int[] BOTTOM_LEFT_CORNER;
    static final int[] BOTTOM_RIGHT_CORNER;
    static final int[] SIMPLE_TOP_LEFT_CORNER;
    static final int[] SIMPLE_TOP_RIGHT_CORNER;
    static final int[] SIMPLE_BOTTOM_LEFT_CORNER;
    static final int[] SIMPLE_BOTTOM_RIGHT_CORNER;
    static final int[] SIMPLE_UNSELECTED_INNER_CORNER;
    static final int[] TOP_LEFT_CORNER_BORDERLESS;
    static final int[] TOP_RIGHT_CORNER_BORDERLESS;
    static final int[] BOTTOM_LEFT_CORNER_BORDERLESS;
    static final int[] BOTTOM_RIGHT_CORNER_BORDERLESS;
    static final int[] SIMPLE_TOP_LEFT_CORNER_BORDERLESS;
    static final int[] SIMPLE_TOP_RIGHT_CORNER_BORDERLESS;
    static final int[] SIMPLE_BOTTOM_LEFT_CORNER_BORDERLESS;
    static final int[] SIMPLE_BOTTOM_RIGHT_CORNER_BORDERLESS;
    static final RGB CLOSE_FILL;
    static final int BUTTON_SIZE = 16;
    static final int BUTTON_TRIM = 1;
    static final int BUTTON_BORDER = 17;
    static final int BUTTON_FILL = 25;
    static final int BORDER1_COLOR = 18;
    static final int ITEM_TOP_MARGIN = 2;
    static final int ITEM_BOTTOM_MARGIN = 2;
    static final int ITEM_LEFT_MARGIN = 4;
    static final int ITEM_RIGHT_MARGIN = 4;
    static final int INTERNAL_SPACING = 4;
    static final int FLAGS = 11;
    static final String ELLIPSIS = "...";
    private static final String CHEVRON_ELLIPSIS = "99+";
    private static final int CHEVRON_FONT_HEIGHT = 10;
    public static final int PART_BODY = -1;
    public static final int PART_HEADER = -2;
    public static final int PART_BORDER = -3;
    public static final int PART_BACKGROUND = -4;
    public static final int PART_MAX_BUTTON = -5;
    public static final int PART_MIN_BUTTON = -6;
    public static final int PART_CHEVRON_BUTTON = -7;
    public static final int PART_CLOSE_BUTTON = -8;
    public static final int MINIMUM_SIZE = 16777216;
    
    protected CTabFolderRenderer(final CTabFolder parent) {
        this.curveWidth = 0;
        this.curveIndent = 0;
        this.lastTabHeight = -1;
        this.selectionHighlightGradientBegin = null;
        this.selectionHighlightGradientColorsCache = null;
        this.selectedOuterColor = null;
        this.selectedInnerColor = null;
        this.tabAreaColor = null;
        this.lastBorderColor = null;
        this.chevronFont = null;
        if (parent == null) {
            return;
        }
        if (parent.isDisposed()) {
            SWT.error(5);
        }
        this.parent = parent;
    }
    
    void antialias(final int[] shape, final Color innerColor, final Color outerColor, final GC gc) {
        if (this.parent.simple) {
            return;
        }
        final String platform = SWT.getPlatform();
        if ("cocoa".equals(platform)) {
            return;
        }
        if (this.parent.getDisplay().getDepth() < 15) {
            return;
        }
        if (outerColor != null) {
            int index = 0;
            boolean left = true;
            int oldY = this.parent.onBottom ? 0 : this.parent.getSize().y;
            final int[] outer = new int[shape.length];
            for (int i = 0; i < shape.length / 2; ++i) {
                if (left && index + 3 < shape.length) {
                    left = (this.parent.onBottom ? (oldY <= shape[index + 3]) : (oldY >= shape[index + 3]));
                    oldY = shape[index + 1];
                }
                outer[index] = shape[index++] + (left ? -1 : 1);
                outer[index] = shape[index++];
            }
            gc.setForeground(outerColor);
            gc.drawPolyline(outer);
        }
        if (innerColor != null) {
            final int[] inner = new int[shape.length];
            int index2 = 0;
            boolean left2 = true;
            int oldY2 = this.parent.onBottom ? 0 : this.parent.getSize().y;
            for (int i = 0; i < shape.length / 2; ++i) {
                if (left2 && index2 + 3 < shape.length) {
                    left2 = (this.parent.onBottom ? (oldY2 <= shape[index2 + 3]) : (oldY2 >= shape[index2 + 3]));
                    oldY2 = shape[index2 + 1];
                }
                inner[index2] = shape[index2++] + (left2 ? 1 : -1);
                inner[index2] = shape[index2++];
            }
            gc.setForeground(innerColor);
            gc.drawPolyline(inner);
        }
    }
    
    protected Point computeSize(final int part, final int state, final GC gc, final int wHint, final int hHint) {
        int width = 0;
        int height = 0;
        switch (part) {
            case -2: {
                if (this.parent.fixedTabHeight != -1) {
                    height = ((this.parent.fixedTabHeight == 0) ? 0 : (this.parent.fixedTabHeight + 1));
                    break;
                }
                final CTabItem[] items = this.parent.items;
                if (items.length == 0) {
                    height = gc.textExtent("Default", 11).y + 2 + 2;
                }
                else {
                    for (int i = 0; i < items.length; ++i) {
                        height = Math.max(height, this.computeSize(i, 0, gc, wHint, hHint).y);
                    }
                }
                gc.dispose();
                break;
            }
            case -8:
            case -6:
            case -5: {
                width = (height = 16);
                break;
            }
            case -7: {
                final Font prevFont = gc.getFont();
                gc.setFont(this.getChevronFont(this.parent.getDisplay()));
                final int widthOfDoubleArrows = 8;
                width = gc.textExtent("99+").x + 8;
                height = 16;
                gc.setFont(prevFont);
                break;
            }
            default: {
                if (0 > part) {
                    break;
                }
                if (part >= this.parent.getItemCount()) {
                    break;
                }
                this.updateCurves();
                final CTabItem item = this.parent.items[part];
                if (item.isDisposed()) {
                    return new Point(0, 0);
                }
                final Image image = item.getImage();
                if (image != null && !image.isDisposed()) {
                    final Rectangle bounds = image.getBounds();
                    if ((state & 0x2) != 0x0 || this.parent.showUnselectedImage) {
                        width += bounds.width;
                    }
                    height = bounds.height;
                }
                String text = null;
                if ((state & 0x1000000) != 0x0) {
                    final int minChars = this.parent.minChars;
                    text = ((minChars == 0) ? null : item.getText());
                    if (text != null && text.length() > minChars) {
                        if (this.useEllipses()) {
                            final int end = (minChars < "...".length() + 1) ? minChars : (minChars - "...".length());
                            text = text.substring(0, end);
                            if (minChars > "...".length() + 1) {
                                text += "...";
                            }
                        }
                        else {
                            final int end = minChars;
                            text = text.substring(0, end);
                        }
                    }
                }
                else {
                    text = item.getText();
                }
                if (text != null) {
                    if (width > 0) {
                        width += 4;
                    }
                    if (item.font == null) {
                        final Point size = gc.textExtent(text, 11);
                        width += size.x;
                        height = Math.max(height, size.y);
                    }
                    else {
                        final Font gcFont = gc.getFont();
                        gc.setFont(item.font);
                        final Point size2 = gc.textExtent(text, 11);
                        width += size2.x;
                        height = Math.max(height, size2.y);
                        gc.setFont(gcFont);
                    }
                }
                if ((this.parent.showClose || item.showClose) && ((state & 0x2) != 0x0 || this.parent.showUnselectedClose)) {
                    if (width > 0) {
                        width += 4;
                    }
                    width += this.computeSize(-8, 0, gc, -1, -1).x;
                    break;
                }
                break;
            }
        }
        final Rectangle trim = this.computeTrim(part, state, 0, 0, width, height);
        width = trim.width;
        height = trim.height;
        return new Point(width, height);
    }
    
    protected Rectangle computeTrim(final int part, final int state, int x, int y, int width, int height) {
        final int borderLeft;
        final int borderRight = borderLeft = (this.parent.borderVisible ? 1 : 0);
        final int borderTop = this.parent.onBottom ? borderLeft : 0;
        final int borderBottom = this.parent.onBottom ? 0 : borderLeft;
        final int tabHeight = this.parent.tabHeight;
        switch (part) {
            case -1: {
                final int style = this.parent.getStyle();
                int highlight_header = ((style & 0x800000) != 0x0) ? 1 : 3;
                final int highlight_margin = ((style & 0x800000) != 0x0) ? 0 : 2;
                if (this.parent.fixedTabHeight == 0 && (style & 0x800000) != 0x0 && (style & 0x800) == 0x0) {
                    highlight_header = 0;
                }
                final int marginWidth = this.parent.marginWidth;
                final int marginHeight = this.parent.marginHeight;
                x = x - marginWidth - highlight_margin - borderLeft;
                width = width + borderLeft + borderRight + 2 * marginWidth + 2 * highlight_margin;
                if (this.parent.minimized) {
                    y = (this.parent.onBottom ? (y - borderTop) : (y - highlight_header - tabHeight - borderTop));
                    height = borderTop + borderBottom + tabHeight + highlight_header;
                    break;
                }
                y = (this.parent.onBottom ? (y - marginHeight - highlight_margin - borderTop) : (y - marginHeight - highlight_header - tabHeight - borderTop));
                height = height + borderTop + borderBottom + 2 * marginHeight + tabHeight + highlight_header + highlight_margin;
                break;
            }
            case -2: {
                break;
            }
            case -8:
            case -7:
            case -6:
            case -5: {
                --x;
                --y;
                width += 2;
                height += 2;
                break;
            }
            case -3: {
                x -= borderLeft;
                width = width + borderLeft + borderRight;
                if (!this.parent.simple) {
                    width += 2;
                }
                y -= borderTop;
                height = height + borderTop + borderBottom;
                break;
            }
            default: {
                if (0 <= part && part < this.parent.getItemCount()) {
                    this.updateCurves();
                    x -= 4;
                    width = width + 4 + 4;
                    if (!this.parent.simple && !this.parent.single && (state & 0x2) != 0x0) {
                        width += this.curveWidth - this.curveIndent;
                    }
                    y -= 2;
                    height = height + 2 + 2;
                    break;
                }
                break;
            }
        }
        return new Rectangle(x, y, width, height);
    }
    
    void createAntialiasColors() {
        this.disposeAntialiasColors();
        this.lastBorderColor = this.parent.getDisplay().getSystemColor(18);
        final RGB lineRGB = this.lastBorderColor.getRGB();
        RGB innerRGB = this.parent.selectionBackground.getRGB();
        if (this.parent.selectionBgImage != null || (this.parent.selectionGradientColors != null && this.parent.selectionGradientColors.length > 1)) {
            innerRGB = null;
        }
        RGB outerRGB = this.parent.getBackground().getRGB();
        if (this.parent.gradientColors != null && this.parent.gradientColors.length > 1) {
            outerRGB = null;
        }
        if (outerRGB != null) {
            final RGB from = lineRGB;
            final RGB to = outerRGB;
            final int red = from.red + 2 * (to.red - from.red) / 3;
            final int green = from.green + 2 * (to.green - from.green) / 3;
            final int blue = from.blue + 2 * (to.blue - from.blue) / 3;
            this.selectedOuterColor = new Color(red, green, blue);
        }
        if (innerRGB != null) {
            final RGB from = lineRGB;
            final RGB to = innerRGB;
            final int red = from.red + 2 * (to.red - from.red) / 3;
            final int green = from.green + 2 * (to.green - from.green) / 3;
            final int blue = from.blue + 2 * (to.blue - from.blue) / 3;
            this.selectedInnerColor = new Color(red, green, blue);
        }
        outerRGB = this.parent.getParent().getBackground().getRGB();
        if (outerRGB != null) {
            final RGB from = lineRGB;
            final RGB to = outerRGB;
            final int red = from.red + 2 * (to.red - from.red) / 3;
            final int green = from.green + 2 * (to.green - from.green) / 3;
            final int blue = from.blue + 2 * (to.blue - from.blue) / 3;
            this.tabAreaColor = new Color(red, green, blue);
        }
    }
    
    void createSelectionHighlightGradientColors(final Color start) {
        this.disposeSelectionHighlightGradientColors();
        if (start == null) {
            return;
        }
        final int fadeGradientSize = this.parent.tabHeight;
        final RGB from = start.getRGB();
        final RGB to = this.parent.selectionBackground.getRGB();
        this.selectionHighlightGradientColorsCache = new Color[fadeGradientSize];
        final int denom = fadeGradientSize - 1;
        for (int i = 0; i < fadeGradientSize; ++i) {
            final int propFrom = denom - i;
            final int propTo = i;
            final int red = (to.red * propTo + from.red * propFrom) / denom;
            final int green = (to.green * propTo + from.green * propFrom) / denom;
            final int blue = (to.blue * propTo + from.blue * propFrom) / denom;
            this.selectionHighlightGradientColorsCache[i] = new Color(red, green, blue);
        }
    }
    
    protected void dispose() {
        this.disposeAntialiasColors();
        this.disposeSelectionHighlightGradientColors();
        this.fillColor = null;
        if (this.chevronFont != null) {
            this.chevronFont.dispose();
            this.chevronFont = null;
        }
    }
    
    void disposeAntialiasColors() {
        final Color tabAreaColor = null;
        this.selectedOuterColor = tabAreaColor;
        this.selectedInnerColor = tabAreaColor;
        this.tabAreaColor = tabAreaColor;
    }
    
    void disposeSelectionHighlightGradientColors() {
        this.selectionHighlightGradientColorsCache = null;
    }
    
    protected void draw(final int part, final int state, final Rectangle bounds, final GC gc) {
        switch (part) {
            case -4: {
                this.drawBackground(gc, bounds, state);
                break;
            }
            case -1: {
                this.drawBody(gc, bounds, state);
                break;
            }
            case -2: {
                this.drawTabArea(gc, bounds, state);
                break;
            }
            case -5: {
                this.drawMaximize(gc, bounds, state);
                break;
            }
            case -6: {
                this.drawMinimize(gc, bounds, state);
                break;
            }
            case -7: {
                this.drawChevron(gc, bounds, state);
                break;
            }
            default: {
                if (0 > part) {
                    break;
                }
                if (part >= this.parent.getItemCount()) {
                    break;
                }
                if (bounds.width == 0 || bounds.height == 0) {
                    return;
                }
                if ((state & 0x2) != 0x0) {
                    this.drawSelected(part, gc, bounds, state);
                    break;
                }
                this.drawUnselected(part, gc, bounds, state);
                break;
            }
        }
    }
    
    void drawBackground(final GC gc, final Rectangle bounds, final int state) {
        final boolean selected = (state & 0x2) != 0x0;
        final Color defaultBackground = (selected && this.parent.shouldHighlight()) ? this.parent.selectionBackground : this.parent.getBackground();
        final Image image = selected ? this.parent.selectionBgImage : null;
        final Color[] colors = (selected & this.parent.shouldHighlight()) ? this.parent.selectionGradientColors : this.parent.gradientColors;
        final int[] percents = selected ? this.parent.selectionGradientPercents : this.parent.gradientPercents;
        final boolean vertical = selected ? this.parent.selectionGradientVertical : this.parent.gradientVertical;
        this.drawBackground(gc, null, bounds.x, bounds.y, bounds.width, bounds.height, defaultBackground, image, colors, percents, vertical);
    }
    
    void drawBackground(final GC gc, final int[] shape, final boolean selected) {
        final Color defaultBackground = (selected && this.parent.shouldHighlight()) ? this.parent.selectionBackground : this.parent.getBackground();
        final Image image = selected ? this.parent.selectionBgImage : null;
        final Color[] colors = (selected && this.parent.shouldHighlight()) ? this.parent.selectionGradientColors : this.parent.gradientColors;
        final int[] percents = selected ? this.parent.selectionGradientPercents : this.parent.gradientPercents;
        final boolean vertical = selected ? this.parent.selectionGradientVertical : this.parent.gradientVertical;
        final Point size = this.parent.getSize();
        int width = size.x;
        final int height = this.parent.tabHeight + (((this.parent.getStyle() & 0x800000) != 0x0) ? 1 : 3);
        int x = 0;
        final int borderLeft = this.parent.borderVisible ? 1 : 0;
        final int borderTop = this.parent.onBottom ? borderLeft : 0;
        final int borderBottom = this.parent.onBottom ? 0 : borderLeft;
        if (borderLeft > 0) {
            ++x;
            width -= 2;
        }
        final int y = this.parent.onBottom ? (size.y - borderBottom - height) : borderTop;
        this.drawBackground(gc, shape, x, y, width, height, defaultBackground, image, colors, percents, vertical);
    }
    
    void drawBackground(final GC gc, final int[] shape, final int x, int y, final int width, int height, final Color defaultBackground, final Image image, final Color[] colors, final int[] percents, final boolean vertical) {
        Region clipping = null;
        Region region = null;
        if (shape != null) {
            clipping = new Region();
            gc.getClipping(clipping);
            region = new Region();
            region.add(shape);
            region.intersect(clipping);
            gc.setClipping(region);
        }
        if (image != null) {
            gc.setBackground(defaultBackground);
            gc.fillRectangle(x, y, width, height);
            final Rectangle imageRect = image.getBounds();
            gc.drawImage(image, imageRect.x, imageRect.y, imageRect.width, imageRect.height, x, y, width, height);
        }
        else if (colors != null) {
            if (colors.length == 1) {
                final Color background = (colors[0] != null) ? colors[0] : defaultBackground;
                gc.setBackground(background);
                gc.fillRectangle(x, y, width, height);
            }
            else if (vertical) {
                if (this.parent.onBottom) {
                    int pos = 0;
                    if (percents[percents.length - 1] < 100) {
                        pos = (100 - percents[percents.length - 1]) * height / 100;
                        gc.setBackground(defaultBackground);
                        gc.fillRectangle(x, y, width, pos);
                    }
                    Color lastColor = colors[colors.length - 1];
                    if (lastColor == null) {
                        lastColor = defaultBackground;
                    }
                    for (int i = percents.length - 1; i >= 0; --i) {
                        gc.setForeground(lastColor);
                        lastColor = colors[i];
                        if (lastColor == null) {
                            lastColor = defaultBackground;
                        }
                        gc.setBackground(lastColor);
                        final int percentage = (i > 0) ? (percents[i] - percents[i - 1]) : percents[i];
                        final int gradientHeight = percentage * height / 100;
                        gc.fillGradientRectangle(x, y + pos, width, gradientHeight, true);
                        pos += gradientHeight;
                    }
                }
                else {
                    Color lastColor2 = colors[0];
                    if (lastColor2 == null) {
                        lastColor2 = defaultBackground;
                    }
                    int pos2 = 0;
                    for (int i = 0; i < percents.length; ++i) {
                        gc.setForeground(lastColor2);
                        lastColor2 = colors[i + 1];
                        if (lastColor2 == null) {
                            lastColor2 = defaultBackground;
                        }
                        gc.setBackground(lastColor2);
                        final int percentage = (i > 0) ? (percents[i] - percents[i - 1]) : percents[i];
                        final int gradientHeight = percentage * height / 100;
                        gc.fillGradientRectangle(x, y + pos2, width, gradientHeight, true);
                        pos2 += gradientHeight;
                    }
                    if (pos2 < height) {
                        gc.setBackground(defaultBackground);
                        gc.fillRectangle(x, pos2, width, height - pos2 + 1);
                    }
                }
            }
            else {
                y = 0;
                height = this.parent.getSize().y;
                Color lastColor2 = colors[0];
                if (lastColor2 == null) {
                    lastColor2 = defaultBackground;
                }
                int pos2 = 0;
                for (int i = 0; i < percents.length; ++i) {
                    gc.setForeground(lastColor2);
                    lastColor2 = colors[i + 1];
                    if (lastColor2 == null) {
                        lastColor2 = defaultBackground;
                    }
                    gc.setBackground(lastColor2);
                    final int gradientWidth = percents[i] * width / 100 - pos2;
                    gc.fillGradientRectangle(x + pos2, y, gradientWidth, height, false);
                    pos2 += gradientWidth;
                }
                if (pos2 < width) {
                    gc.setBackground(defaultBackground);
                    gc.fillRectangle(x + pos2, y, width - pos2, height);
                }
            }
        }
        else if ((this.parent.getStyle() & 0x40000) != 0x0 || !defaultBackground.equals(this.parent.getBackground())) {
            gc.setBackground(defaultBackground);
            gc.fillRectangle(x, y, width, height);
        }
        if (shape != null) {
            gc.setClipping(clipping);
            clipping.dispose();
            region.dispose();
        }
    }
    
    void drawBorder(final GC gc, final int[] shape) {
        gc.setForeground(this.parent.getDisplay().getSystemColor(18));
        gc.drawPolyline(shape);
    }
    
    void drawBody(final GC gc, final Rectangle bounds, final int state) {
        final Point size = new Point(bounds.width, bounds.height);
        final int selectedIndex = this.parent.selectedIndex;
        final int tabHeight = this.parent.tabHeight;
        final int borderLeft;
        final int borderRight = borderLeft = (this.parent.borderVisible ? 1 : 0);
        final int borderTop = this.parent.onBottom ? borderLeft : 0;
        final int borderBottom = this.parent.onBottom ? 0 : borderLeft;
        final int style = this.parent.getStyle();
        final int highlight_header = ((style & 0x800000) != 0x0) ? 1 : 3;
        final int highlight_margin = ((style & 0x800000) != 0x0) ? 0 : 2;
        if (!this.parent.minimized) {
            final int width = size.x - borderLeft - borderRight - 2 * highlight_margin;
            final int height = size.y - borderTop - borderBottom - tabHeight - highlight_header - highlight_margin;
            if (highlight_margin > 0) {
                int[] shape = null;
                if (this.parent.onBottom) {
                    final int x1 = borderLeft;
                    final int y1 = borderTop;
                    final int x2 = size.x - borderRight;
                    final int y2 = size.y - borderBottom - tabHeight - highlight_header;
                    shape = new int[] { x1, y1, x2, y1, x2, y2, x2 - highlight_margin, y2, x2 - highlight_margin, y1 + highlight_margin, x1 + highlight_margin, y1 + highlight_margin, x1 + highlight_margin, y2, x1, y2 };
                }
                else {
                    final int x1 = borderLeft;
                    final int y1 = borderTop + tabHeight + highlight_header;
                    final int x2 = size.x - borderRight;
                    final int y2 = size.y - borderBottom;
                    shape = new int[] { x1, y1, x1 + highlight_margin, y1, x1 + highlight_margin, y2 - highlight_margin, x2 - highlight_margin, y2 - highlight_margin, x2 - highlight_margin, y1, x2, y1, x2, y2, x1, y2 };
                }
                if (selectedIndex != -1 && this.parent.selectionGradientColors != null && this.parent.selectionGradientColors.length > 1 && !this.parent.selectionGradientVertical) {
                    this.drawBackground(gc, shape, true);
                }
                else if (selectedIndex == -1 && this.parent.gradientColors != null && this.parent.gradientColors.length > 1 && !this.parent.gradientVertical) {
                    this.drawBackground(gc, shape, false);
                }
                else {
                    gc.setBackground((selectedIndex != -1 && this.parent.shouldHighlight()) ? this.parent.selectionBackground : this.parent.getBackground());
                    gc.fillPolygon(shape);
                }
            }
            if ((this.parent.getStyle() & 0x40000) != 0x0) {
                gc.setBackground(this.parent.getBackground());
                final int marginWidth = this.parent.marginWidth;
                final int marginHeight = this.parent.marginHeight;
                final int xClient = borderLeft + marginWidth + highlight_margin;
                int yClient;
                if (this.parent.onBottom) {
                    yClient = borderTop + highlight_margin + marginHeight;
                }
                else {
                    yClient = borderTop + tabHeight + highlight_header + marginHeight;
                }
                gc.fillRectangle(xClient - marginWidth, yClient - marginHeight, width, height);
            }
        }
        else if ((this.parent.getStyle() & 0x40000) != 0x0) {
            final int height2 = borderTop + tabHeight + highlight_header + borderBottom;
            if (size.y > height2) {
                gc.setBackground(this.parent.getParent().getBackground());
                gc.fillRectangle(0, height2, size.x, size.y - height2);
            }
        }
        if (borderLeft > 0) {
            gc.setForeground(this.parent.getDisplay().getSystemColor(18));
            final int x3 = borderLeft - 1;
            final int x4 = size.x - borderRight;
            final int y3 = this.parent.onBottom ? (borderTop - 1) : (borderTop + tabHeight);
            final int y4 = this.parent.onBottom ? (size.y - tabHeight - borderBottom - 1) : (size.y - borderBottom);
            gc.drawLine(x3, y3, x3, y4);
            gc.drawLine(x4, y3, x4, y4);
            if (this.parent.onBottom) {
                gc.drawLine(x3, y3, x4, y3);
            }
            else {
                gc.drawLine(x3, y4, x4, y4);
            }
        }
    }
    
    void drawClose(final GC gc, final Rectangle closeRect, final int closeImageState) {
        if (closeRect.width == 0 || closeRect.height == 0) {
            return;
        }
        final int x = closeRect.x + Math.max(1, (closeRect.width - 8) / 2);
        int y = closeRect.y + Math.max(1, (closeRect.height - 8) / 2);
        y += (this.parent.onBottom ? -1 : 1);
        final int originalLineWidth = gc.getLineWidth();
        final Color originalForeground = gc.getForeground();
        switch (closeImageState & 0x2A) {
            case 0: {
                this.drawCloseLines(gc, x, y, 8, false);
                break;
            }
            case 32: {
                this.drawCloseLines(gc, x, y, 8, true);
                break;
            }
            case 2: {
                this.drawCloseLines(gc, x, y, 8, true);
                break;
            }
            case 8: {
                final int[] shape = { x, y, x + 10, y, x + 10, y + 10, x, y + 10 };
                this.drawBackground(gc, shape, false);
                break;
            }
        }
        gc.setLineWidth(originalLineWidth);
        gc.setForeground(originalForeground);
    }
    
    private void drawCloseLines(final GC gc, final int x, final int y, final int lineLength, final boolean hot) {
        if (hot) {
            gc.setLineWidth(gc.getLineWidth() + 2);
            gc.setForeground(this.getFillColor());
        }
        gc.setLineCap(2);
        gc.drawLine(x, y, x + lineLength, y + lineLength);
        gc.drawLine(x, y + lineLength, x + lineLength, y);
    }
    
    void drawChevron(final GC gc, final Rectangle chevronRect, final int chevronImageState) {
        if (chevronRect.width == 0 || chevronRect.height == 0) {
            return;
        }
        final Display display = this.parent.getDisplay();
        final Font font = this.getChevronFont(display);
        final int fontHeight = font.getFontData()[0].getHeight();
        final int indent = Math.max(2, (chevronRect.height - fontHeight - 4) / 2);
        final int x = chevronRect.x + 2;
        final int y = chevronRect.y + indent;
        final int count = this.parent.getChevronCount();
        final String chevronString = (count > 99) ? "99+" : String.valueOf(count);
        switch (chevronImageState & 0x22) {
            case 0: {
                final Color chevronBorder = this.parent.single ? this.parent.getSelectionForeground() : this.parent.getForeground();
                gc.setForeground(chevronBorder);
                gc.setFont(font);
                this.drawChevronContent(gc, x, y, chevronString);
                break;
            }
            case 32: {
                gc.setForeground(display.getSystemColor(17));
                gc.setBackground(display.getSystemColor(25));
                gc.setFont(font);
                this.drawRoundRectangle(gc, chevronRect);
                this.drawChevronContent(gc, x, y, chevronString);
                break;
            }
            case 2: {
                gc.setForeground(display.getSystemColor(17));
                gc.setBackground(display.getSystemColor(25));
                gc.setFont(font);
                this.drawRoundRectangle(gc, chevronRect);
                this.drawChevronContent(gc, x + 1, y + 1, chevronString);
                break;
            }
        }
    }
    
    private void drawRoundRectangle(final GC gc, final Rectangle chevronRect) {
        gc.fillRoundRectangle(chevronRect.x, chevronRect.y, chevronRect.width, chevronRect.height, 6, 6);
        gc.drawRoundRectangle(chevronRect.x, chevronRect.y, chevronRect.width - 1, chevronRect.height - 1, 6, 6);
    }
    
    private void drawChevronContent(final GC gc, final int x, final int y, final String chevronString) {
        gc.drawLine(x, y, x + 2, y + 2);
        gc.drawLine(x + 2, y + 2, x, y + 4);
        gc.drawLine(x + 1, y, x + 3, y + 2);
        gc.drawLine(x + 3, y + 2, x + 1, y + 4);
        gc.drawLine(x + 4, y, x + 6, y + 2);
        gc.drawLine(x + 6, y + 2, x + 4, y + 4);
        gc.drawLine(x + 5, y, x + 7, y + 2);
        gc.drawLine(x + 7, y + 2, x + 5, y + 4);
        gc.drawString(chevronString, x + 7, y + 3, true);
    }
    
    void drawHighlight(final GC gc, final Rectangle bounds, final int state, final int rightEdge) {
        if (this.parent.simple || this.parent.onBottom) {
            return;
        }
        if (this.selectionHighlightGradientBegin == null) {
            return;
        }
        final Color[] gradients = this.selectionHighlightGradientColorsCache;
        if (gradients == null) {
            return;
        }
        final int gradientsSize = gradients.length;
        if (gradientsSize == 0) {
            return;
        }
        final int x = bounds.x;
        final int y = bounds.y;
        gc.setForeground(gradients[0]);
        gc.drawLine(CTabFolderRenderer.TOP_LEFT_CORNER_HILITE[0] + x + 1, 1 + y, rightEdge - this.curveIndent, 1 + y);
        final int[] leftHighlightCurve = CTabFolderRenderer.TOP_LEFT_CORNER_HILITE;
        final int d = this.parent.tabHeight - this.topCurveHighlightEnd.length / 2;
        int lastX = 0;
        int lastY = 0;
        int lastColorIndex = 0;
        for (int i = 0; i < leftHighlightCurve.length / 2; ++i) {
            final int rawX = leftHighlightCurve[i * 2];
            final int rawY = leftHighlightCurve[i * 2 + 1];
            lastX = rawX + x;
            lastY = rawY + y;
            lastColorIndex = rawY - 1;
            gc.setForeground(gradients[lastColorIndex]);
            gc.drawPoint(lastX, lastY);
        }
        for (int i = lastColorIndex; i < gradientsSize; ++i) {
            gc.setForeground(gradients[i]);
            gc.drawPoint(lastX, 1 + lastY++);
        }
        final int rightEdgeOffset = rightEdge - this.curveIndent;
        for (int j = 0; j < this.topCurveHighlightStart.length / 2; ++j) {
            final int rawX2 = this.topCurveHighlightStart[j * 2];
            final int rawY2 = this.topCurveHighlightStart[j * 2 + 1];
            lastX = rawX2 + rightEdgeOffset;
            lastY = rawY2 + y;
            lastColorIndex = rawY2 - 1;
            if (lastColorIndex >= gradientsSize) {
                break;
            }
            gc.setForeground(gradients[lastColorIndex]);
            gc.drawPoint(lastX, lastY);
        }
        for (int j = lastColorIndex; j < lastColorIndex + d && j < gradientsSize; ++j) {
            gc.setForeground(gradients[j]);
            gc.drawPoint(1 + lastX++, 1 + lastY++);
        }
        for (int j = 0; j < this.topCurveHighlightEnd.length / 2; ++j) {
            final int rawX2 = this.topCurveHighlightEnd[j * 2];
            final int rawY2 = this.topCurveHighlightEnd[j * 2 + 1];
            lastX = rawX2 + rightEdgeOffset;
            lastY = rawY2 + y;
            lastColorIndex = rawY2 - 1;
            if (lastColorIndex >= gradientsSize) {
                break;
            }
            gc.setForeground(gradients[lastColorIndex]);
            gc.drawPoint(lastX, lastY);
        }
    }
    
    void drawLeftUnselectedBorder(final GC gc, final Rectangle bounds, final int state) {
        final int x = bounds.x;
        final int y = bounds.y;
        final int height = bounds.height;
        int[] shape = null;
        if (this.parent.onBottom) {
            final int[] left = this.parent.simple ? CTabFolderRenderer.SIMPLE_UNSELECTED_INNER_CORNER : CTabFolderRenderer.BOTTOM_LEFT_CORNER;
            shape = new int[left.length + 2];
            int index = 0;
            shape[index++] = x;
            shape[index++] = y - 1;
            for (int i = 0; i < left.length / 2; ++i) {
                shape[index++] = x + left[2 * i];
                shape[index++] = y + height + left[2 * i + 1] - 1;
            }
        }
        else {
            final int[] left = this.parent.simple ? CTabFolderRenderer.SIMPLE_UNSELECTED_INNER_CORNER : CTabFolderRenderer.TOP_LEFT_CORNER;
            shape = new int[left.length + 2];
            int index = 0;
            shape[index++] = x;
            shape[index++] = y + height;
            for (int i = 0; i < left.length / 2; ++i) {
                shape[index++] = x + left[2 * i];
                shape[index++] = y + left[2 * i + 1];
            }
        }
        this.drawBorder(gc, shape);
    }
    
    void drawMaximize(final GC gc, final Rectangle maxRect, final int maxImageState) {
        if (maxRect.width == 0 || maxRect.height == 0) {
            return;
        }
        final int x = maxRect.x + (maxRect.width - 10) / 2;
        final int y = maxRect.y + 3;
        gc.setForeground(this.parent.getForeground());
        switch (maxImageState & 0x22) {
            case 0: {
                if (!this.parent.getMaximized()) {
                    gc.drawRectangle(x, y, 9, 9);
                    gc.drawLine(x, y + 2, x + 9, y + 2);
                    break;
                }
                gc.drawRectangle(x, y + 3, 5, 4);
                gc.drawRectangle(x + 2, y, 5, 4);
                gc.drawLine(x + 2, y + 1, x + 7, y + 1);
                gc.drawLine(x, y + 4, x + 5, y + 4);
                break;
            }
            case 32: {
                this.drawRoundRectangle(gc, maxRect);
                if (!this.parent.getMaximized()) {
                    gc.drawRectangle(x, y, 9, 9);
                    gc.drawLine(x, y + 2, x + 9, y + 2);
                    break;
                }
                gc.drawRectangle(x, y + 3, 5, 4);
                gc.drawRectangle(x + 2, y, 5, 4);
                gc.drawLine(x + 2, y + 1, x + 7, y + 1);
                gc.drawLine(x, y + 4, x + 5, y + 4);
                break;
            }
            case 2: {
                this.drawRoundRectangle(gc, maxRect);
                if (!this.parent.getMaximized()) {
                    gc.drawRectangle(x + 1, y + 1, 9, 9);
                    gc.drawLine(x + 1, y + 3, x + 10, y + 3);
                    break;
                }
                gc.drawRectangle(x + 1, y + 4, 5, 4);
                gc.drawRectangle(x + 3, y + 1, 5, 4);
                gc.drawLine(x + 3, y + 2, x + 8, y + 2);
                gc.drawLine(x + 1, y + 5, x + 6, y + 5);
                break;
            }
        }
    }
    
    void drawMinimize(final GC gc, final Rectangle minRect, final int minImageState) {
        if (minRect.width == 0 || minRect.height == 0) {
            return;
        }
        final int x = minRect.x + (minRect.width - 10) / 2;
        final int y = minRect.y + 3;
        gc.setForeground(this.parent.getForeground());
        switch (minImageState & 0x22) {
            case 0: {
                if (!this.parent.getMinimized()) {
                    gc.drawRectangle(x, y, 9, 3);
                    break;
                }
                gc.drawRectangle(x, y + 3, 5, 4);
                gc.drawRectangle(x + 2, y, 5, 4);
                gc.drawLine(x + 3, y + 1, x + 6, y + 1);
                gc.drawLine(x + 1, y + 4, x + 4, y + 4);
                break;
            }
            case 32: {
                this.drawRoundRectangle(gc, minRect);
                if (!this.parent.getMinimized()) {
                    gc.drawRectangle(x, y, 9, 3);
                    break;
                }
                gc.drawRectangle(x, y + 3, 5, 4);
                gc.drawRectangle(x + 2, y, 5, 4);
                gc.drawLine(x + 3, y + 1, x + 6, y + 1);
                gc.drawLine(x + 1, y + 4, x + 4, y + 4);
                break;
            }
            case 2: {
                this.drawRoundRectangle(gc, minRect);
                if (!this.parent.getMinimized()) {
                    gc.drawRectangle(x + 1, y + 1, 9, 3);
                    break;
                }
                gc.drawRectangle(x + 1, y + 4, 5, 4);
                gc.drawRectangle(x + 3, y + 1, 5, 4);
                gc.drawLine(x + 4, y + 2, x + 7, y + 2);
                gc.drawLine(x + 2, y + 5, x + 5, y + 5);
                break;
            }
        }
    }
    
    void drawRightUnselectedBorder(final GC gc, final Rectangle bounds, final int state) {
        final int x = bounds.x;
        final int y = bounds.y;
        final int width = bounds.width;
        final int height = bounds.height;
        int[] shape = null;
        final int startX = x + width - 1;
        if (this.parent.onBottom) {
            final int[] right = this.parent.simple ? CTabFolderRenderer.SIMPLE_UNSELECTED_INNER_CORNER : CTabFolderRenderer.BOTTOM_RIGHT_CORNER;
            shape = new int[right.length + 2];
            int index = 0;
            for (int i = 0; i < right.length / 2; ++i) {
                shape[index++] = startX + right[2 * i];
                shape[index++] = y + height + right[2 * i + 1] - 1;
            }
            shape[index++] = startX;
            shape[index++] = y - 1;
        }
        else {
            final int[] right = this.parent.simple ? CTabFolderRenderer.SIMPLE_UNSELECTED_INNER_CORNER : CTabFolderRenderer.TOP_RIGHT_CORNER;
            shape = new int[right.length + 2];
            int index = 0;
            for (int i = 0; i < right.length / 2; ++i) {
                shape[index++] = startX + right[2 * i];
                shape[index++] = y + right[2 * i + 1];
            }
            shape[index++] = startX;
            shape[index++] = y + height;
        }
        this.drawBorder(gc, shape);
    }
    
    void drawSelected(final int itemIndex, final GC gc, final Rectangle bounds, final int state) {
        final CTabItem item = this.parent.items[itemIndex];
        final int x = bounds.x;
        final int y = bounds.y;
        final int height = bounds.height;
        int width = bounds.width;
        if (!this.parent.simple && !this.parent.single) {
            width -= this.curveWidth - this.curveIndent;
        }
        final int borderLeft;
        final int borderRight = borderLeft = (this.parent.borderVisible ? 1 : 0);
        final int borderTop = this.parent.onBottom ? borderLeft : 0;
        final int borderBottom = this.parent.onBottom ? 0 : borderLeft;
        final Point size = this.parent.getSize();
        final int rightEdge = Math.min(x + width, this.parent.getRightItemEdge(gc));
        if ((state & 0x8) != 0x0) {
            final int highlight_header = ((this.parent.getStyle() & 0x800000) != 0x0) ? 1 : 3;
            int xx = borderLeft;
            int yy = this.parent.onBottom ? (size.y - borderBottom - this.parent.tabHeight - highlight_header) : (borderTop + this.parent.tabHeight + 1);
            int ww = size.x - borderLeft - borderRight;
            int hh = highlight_header - 1;
            int[] shape = { xx, yy, xx + ww, yy, xx + ww, yy + hh, xx, yy + hh };
            if (this.parent.selectionGradientColors != null && !this.parent.selectionGradientVertical) {
                this.drawBackground(gc, shape, this.parent.shouldHighlight());
            }
            else {
                gc.setBackground(this.parent.shouldHighlight() ? this.parent.selectionBackground : this.parent.getBackground());
                gc.fillRectangle(xx, yy, ww, hh);
            }
            if (this.parent.single) {
                if (!item.showing) {
                    return;
                }
            }
            else {
                if (!item.showing) {
                    final int x2 = Math.max(0, borderLeft - 1);
                    final int y2 = this.parent.onBottom ? (y - 1) : (y + height);
                    final int x3 = size.x - borderRight;
                    gc.setForeground(this.parent.getDisplay().getSystemColor(18));
                    gc.drawLine(x2, y2, x3, y2);
                    return;
                }
                shape = null;
                if (this.parent.onBottom) {
                    int[] left = this.parent.simple ? CTabFolderRenderer.SIMPLE_BOTTOM_LEFT_CORNER : CTabFolderRenderer.BOTTOM_LEFT_CORNER;
                    final int[] right = this.parent.simple ? CTabFolderRenderer.SIMPLE_BOTTOM_RIGHT_CORNER : this.curve;
                    if (borderLeft == 0 && itemIndex == this.parent.firstIndex) {
                        left = new int[] { x, y + height };
                    }
                    shape = new int[left.length + right.length + 8];
                    int index = 0;
                    shape[index++] = x;
                    shape[index++] = y - 1;
                    shape[index++] = x;
                    shape[index++] = y - 1;
                    for (int i = 0; i < left.length / 2; ++i) {
                        shape[index++] = x + left[2 * i];
                        shape[index++] = y + height + left[2 * i + 1] - 1;
                    }
                    for (int i = 0; i < right.length / 2; ++i) {
                        shape[index++] = (this.parent.simple ? (rightEdge - 1 + right[2 * i]) : (rightEdge - this.curveIndent + right[2 * i]));
                        shape[index++] = (this.parent.simple ? (y + height + right[2 * i + 1] - 1) : (y + right[2 * i + 1] - 2));
                    }
                    shape[index++] = (this.parent.simple ? (rightEdge - 1) : (rightEdge + this.curveWidth - this.curveIndent));
                    shape[index++] = y - 1;
                    shape[index++] = (this.parent.simple ? (rightEdge - 1) : (rightEdge + this.curveWidth - this.curveIndent));
                    shape[index++] = y - 1;
                }
                else {
                    int[] left = this.parent.simple ? CTabFolderRenderer.SIMPLE_TOP_LEFT_CORNER : CTabFolderRenderer.TOP_LEFT_CORNER;
                    final int[] right = this.parent.simple ? CTabFolderRenderer.SIMPLE_TOP_RIGHT_CORNER : this.curve;
                    if (borderLeft == 0 && itemIndex == this.parent.firstIndex) {
                        left = new int[] { x, y };
                    }
                    shape = new int[left.length + right.length + 8];
                    int index = 0;
                    shape[index++] = x;
                    shape[index++] = y + height + 1;
                    shape[index++] = x;
                    shape[index++] = y + height + 1;
                    for (int i = 0; i < left.length / 2; ++i) {
                        shape[index++] = x + left[2 * i];
                        shape[index++] = y + left[2 * i + 1];
                    }
                    for (int i = 0; i < right.length / 2; ++i) {
                        shape[index++] = (this.parent.simple ? (rightEdge - 1 + right[2 * i]) : (rightEdge - this.curveIndent + right[2 * i]));
                        shape[index++] = y + right[2 * i + 1];
                    }
                    shape[index++] = (this.parent.simple ? (rightEdge - 1) : (rightEdge + this.curveWidth - this.curveIndent));
                    shape[index++] = y + height + 1;
                    shape[index++] = (this.parent.simple ? (rightEdge - 1) : (rightEdge + this.curveWidth - this.curveIndent));
                    shape[index++] = y + height + 1;
                }
                final Rectangle clipping = gc.getClipping();
                final Rectangle clipBounds;
                final Rectangle rectangle2;
                final Rectangle bounds2 = rectangle2 = (clipBounds = item.getBounds());
                ++rectangle2.height;
                if (this.parent.onBottom) {
                    final Rectangle rectangle3;
                    final Rectangle rectangle = rectangle3 = clipBounds;
                    --rectangle3.y;
                }
                final boolean tabInPaint = clipping.intersects(clipBounds);
                if (tabInPaint) {
                    if (this.parent.selectionGradientColors != null && !this.parent.selectionGradientVertical) {
                        this.drawBackground(gc, shape, true);
                    }
                    else {
                        final Color defaultBackground = this.parent.shouldHighlight() ? this.parent.selectionBackground : this.parent.getBackground();
                        final Image image = this.parent.selectionBgImage;
                        final Color[] colors = this.parent.selectionGradientColors;
                        final int[] percents = this.parent.selectionGradientPercents;
                        final boolean vertical = this.parent.selectionGradientVertical;
                        xx = x;
                        yy = (this.parent.onBottom ? (y - 1) : (y + 1));
                        ww = width;
                        hh = height;
                        if (!this.parent.single && !this.parent.simple) {
                            ww += this.curveWidth - this.curveIndent;
                        }
                        this.drawBackground(gc, shape, xx, yy, ww, hh, defaultBackground, image, colors, percents, vertical);
                    }
                }
                this.drawHighlight(gc, bounds, state, rightEdge);
                shape[0] = Math.max(0, borderLeft - 1);
                if (borderLeft == 0 && itemIndex == this.parent.firstIndex) {
                    shape[1] = (this.parent.onBottom ? (y + height - 1) : y);
                    shape[5] = (shape[3] = shape[1]);
                }
                shape[shape.length - 2] = size.x - borderRight + 1;
                for (int j = 0; j < shape.length / 2; ++j) {
                    if (shape[2 * j + 1] == y + height + 1) {
                        final int[] array = shape;
                        final int n = 2 * j + 1;
                        final int[] array2 = array;
                        final int n2 = n;
                        --array2[n2];
                    }
                }
                final Color borderColor = this.parent.getDisplay().getSystemColor(18);
                if (!borderColor.equals(this.lastBorderColor)) {
                    this.createAntialiasColors();
                }
                this.antialias(shape, this.selectedInnerColor, this.selectedOuterColor, gc);
                gc.setForeground(borderColor);
                gc.drawPolyline(shape);
                if (!tabInPaint) {
                    return;
                }
            }
        }
        if ((state & 0x10) != 0x0) {
            final Rectangle trim = this.computeTrim(itemIndex, 0, 0, 0, 0, 0);
            int xDraw = x - trim.x;
            if (this.parent.single && (this.parent.showClose || item.showClose)) {
                xDraw += item.closeRect.width;
            }
            final Image image2 = item.getImage();
            if (image2 != null && !image2.isDisposed()) {
                final Rectangle imageBounds = image2.getBounds();
                int maxImageWidth = rightEdge - xDraw - (trim.width + trim.x);
                if (!this.parent.single && item.closeRect.width > 0) {
                    maxImageWidth -= item.closeRect.width + 4;
                }
                if (imageBounds.width < maxImageWidth) {
                    final int imageX = xDraw;
                    int imageY = y + (height - imageBounds.height) / 2;
                    imageY += (this.parent.onBottom ? -1 : 1);
                    gc.drawImage(image2, imageX, imageY);
                    xDraw += imageBounds.width + 4;
                }
            }
            int textWidth = rightEdge - xDraw - (trim.width + trim.x);
            if (!this.parent.single && item.closeRect.width > 0) {
                textWidth -= item.closeRect.width + 4;
            }
            if (textWidth > 0) {
                final Font gcFont = gc.getFont();
                gc.setFont((item.font == null) ? this.parent.getFont() : item.font);
                if (item.shortenedText == null || item.shortenedTextWidth != textWidth) {
                    item.shortenedText = this.shortenText(gc, item.getText(), textWidth);
                    item.shortenedTextWidth = textWidth;
                }
                final Point extent = gc.textExtent(item.shortenedText, 11);
                int textY = y + (height - extent.y) / 2;
                textY += (this.parent.onBottom ? -1 : 1);
                gc.setForeground((item.selectionForeground == null) ? this.parent.getSelectionForeground() : item.selectionForeground);
                gc.drawText(item.shortenedText, xDraw, textY, 11);
                gc.setFont(gcFont);
                if (this.parent.isFocusControl()) {
                    final Color orginalForeground = gc.getForeground();
                    final Color orginalBackground = gc.getBackground();
                    final Display display = this.parent.getDisplay();
                    if (this.parent.simple || this.parent.single) {
                        gc.setBackground(display.getSystemColor(2));
                        gc.setForeground(display.getSystemColor(1));
                        gc.drawFocus(xDraw - 1, textY - 1, extent.x + 2, extent.y + 2);
                    }
                    else {
                        gc.setForeground(display.getSystemColor(17));
                        gc.drawLine(xDraw, textY + extent.y + 1, xDraw + extent.x + 1, textY + extent.y + 1);
                    }
                    gc.setForeground(orginalForeground);
                    gc.setBackground(orginalBackground);
                }
            }
            if (this.parent.showClose || item.showClose) {
                this.drawClose(gc, item.closeRect, item.closeImageState);
            }
        }
    }
    
    void drawTabArea(final GC gc, final Rectangle bounds, final int state) {
        final Point size = this.parent.getSize();
        int[] shape = null;
        final Color borderColor = this.parent.getDisplay().getSystemColor(18);
        final int tabHeight = this.parent.tabHeight;
        final int style = this.parent.getStyle();
        final int borderLeft;
        final int borderRight = borderLeft = (this.parent.borderVisible ? 1 : 0);
        final int borderTop = this.parent.onBottom ? borderLeft : 0;
        final int borderBottom = this.parent.onBottom ? 0 : borderLeft;
        final int selectedIndex = this.parent.selectedIndex;
        final int highlight_header = ((style & 0x800000) != 0x0) ? 1 : 3;
        if (tabHeight != 0) {
            final int x = Math.max(0, borderLeft - 1);
            final int y = this.parent.onBottom ? (size.y - borderBottom - tabHeight) : borderTop;
            final int width = size.x - borderLeft - borderRight + 1;
            final int height = tabHeight - 1;
            final boolean simple = this.parent.simple;
            if (this.parent.onBottom) {
                int[] left;
                int[] right;
                if ((style & 0x800) != 0x0) {
                    left = (simple ? CTabFolderRenderer.SIMPLE_BOTTOM_LEFT_CORNER : CTabFolderRenderer.BOTTOM_LEFT_CORNER);
                    right = (simple ? CTabFolderRenderer.SIMPLE_BOTTOM_RIGHT_CORNER : CTabFolderRenderer.BOTTOM_RIGHT_CORNER);
                }
                else {
                    left = (simple ? CTabFolderRenderer.SIMPLE_BOTTOM_LEFT_CORNER_BORDERLESS : CTabFolderRenderer.BOTTOM_LEFT_CORNER_BORDERLESS);
                    right = (simple ? CTabFolderRenderer.SIMPLE_BOTTOM_RIGHT_CORNER_BORDERLESS : CTabFolderRenderer.BOTTOM_RIGHT_CORNER_BORDERLESS);
                }
                shape = new int[left.length + right.length + 4];
                int index = 0;
                shape[index++] = x;
                shape[index++] = y - highlight_header;
                for (int i = 0; i < left.length / 2; ++i) {
                    shape[index++] = x + left[2 * i];
                    shape[index++] = y + height + left[2 * i + 1];
                    if (borderLeft == 0) {
                        final int[] array = shape;
                        final int n = index - 1;
                        final int[] array3 = array;
                        final int n3 = n;
                        ++array3[n3];
                    }
                }
                for (int i = 0; i < right.length / 2; ++i) {
                    shape[index++] = x + width + right[2 * i];
                    shape[index++] = y + height + right[2 * i + 1];
                    if (borderLeft == 0) {
                        final int[] array2 = shape;
                        final int n2 = index - 1;
                        final int[] array4 = array2;
                        final int n4 = n2;
                        ++array4[n4];
                    }
                }
                shape[index++] = x + width;
                shape[index++] = y - highlight_header;
            }
            else {
                int[] left;
                int[] right;
                if ((style & 0x800) != 0x0) {
                    left = (simple ? CTabFolderRenderer.SIMPLE_TOP_LEFT_CORNER : CTabFolderRenderer.TOP_LEFT_CORNER);
                    right = (simple ? CTabFolderRenderer.SIMPLE_TOP_RIGHT_CORNER : CTabFolderRenderer.TOP_RIGHT_CORNER);
                }
                else {
                    left = (simple ? CTabFolderRenderer.SIMPLE_TOP_LEFT_CORNER_BORDERLESS : CTabFolderRenderer.TOP_LEFT_CORNER_BORDERLESS);
                    right = (simple ? CTabFolderRenderer.SIMPLE_TOP_RIGHT_CORNER_BORDERLESS : CTabFolderRenderer.TOP_RIGHT_CORNER_BORDERLESS);
                }
                shape = new int[left.length + right.length + 4];
                int index = 0;
                shape[index++] = x;
                shape[index++] = y + height + highlight_header + 1;
                for (int i = 0; i < left.length / 2; ++i) {
                    shape[index++] = x + left[2 * i];
                    shape[index++] = y + left[2 * i + 1];
                }
                for (int i = 0; i < right.length / 2; ++i) {
                    shape[index++] = x + width + right[2 * i];
                    shape[index++] = y + right[2 * i + 1];
                }
                shape[index++] = x + width;
                shape[index++] = y + height + highlight_header + 1;
            }
            final boolean single = this.parent.single;
            final boolean bkSelected = single && selectedIndex != -1;
            this.drawBackground(gc, shape, bkSelected);
            final Region r = new Region();
            r.add(new Rectangle(x, y, width + 1, height + 1));
            r.subtract(shape);
            gc.setBackground(this.parent.getParent().getBackground());
            this.fillRegion(gc, r);
            r.dispose();
            if (selectedIndex == -1) {
                final int x2 = borderLeft;
                final int y2 = this.parent.onBottom ? (size.y - borderBottom - tabHeight - 1) : (borderTop + tabHeight);
                final int x3 = size.x - borderRight;
                gc.setForeground(borderColor);
                gc.drawLine(x2, y2, x3, y2);
            }
            if (borderLeft > 0) {
                if (!borderColor.equals(this.lastBorderColor)) {
                    this.createAntialiasColors();
                }
                this.antialias(shape, null, this.tabAreaColor, gc);
                gc.setForeground(borderColor);
                gc.drawPolyline(shape);
            }
            return;
        }
        if ((style & 0x800000) != 0x0 && (style & 0x800) == 0x0) {
            return;
        }
        final int x4 = borderLeft - 1;
        final int x5 = size.x - borderRight;
        final int y3 = this.parent.onBottom ? (size.y - borderBottom - highlight_header - 1) : (borderTop + highlight_header);
        int y4 = this.parent.onBottom ? (size.y - borderBottom) : borderTop;
        if (borderLeft > 0 && this.parent.onBottom) {
            --y4;
        }
        shape = new int[] { x4, y3, x4, y4, x5, y4, x5, y3 };
        if (selectedIndex != -1 && this.parent.selectionGradientColors != null && this.parent.selectionGradientColors.length > 1 && !this.parent.selectionGradientVertical) {
            this.drawBackground(gc, shape, true);
        }
        else if (selectedIndex == -1 && this.parent.gradientColors != null && this.parent.gradientColors.length > 1 && !this.parent.gradientVertical) {
            this.drawBackground(gc, shape, false);
        }
        else {
            gc.setBackground((selectedIndex != -1 && this.parent.shouldHighlight()) ? this.parent.selectionBackground : this.parent.getBackground());
            gc.fillPolygon(shape);
        }
        if (borderLeft > 0) {
            gc.setForeground(borderColor);
            gc.drawPolyline(shape);
        }
    }
    
    void drawUnselected(final int index, final GC gc, final Rectangle bounds, final int state) {
        final CTabItem item = this.parent.items[index];
        final int x = bounds.x;
        final int y = bounds.y;
        final int height = bounds.height;
        final int width = bounds.width;
        if (!item.showing) {
            return;
        }
        final Rectangle clipping = gc.getClipping();
        if (!clipping.intersects(bounds)) {
            return;
        }
        if ((state & 0x8) != 0x0) {
            if (index > 0 && index < this.parent.selectedIndex) {
                this.drawLeftUnselectedBorder(gc, bounds, state);
            }
            if (index > this.parent.selectedIndex) {
                this.drawRightUnselectedBorder(gc, bounds, state);
            }
        }
        if ((state & 0x10) != 0x0) {
            final Rectangle trim = this.computeTrim(index, 0, 0, 0, 0, 0);
            int xDraw = x - trim.x;
            final Image image = item.getImage();
            if (image != null && !image.isDisposed() && this.parent.showUnselectedImage) {
                final Rectangle imageBounds = image.getBounds();
                int maxImageWidth = x + width - xDraw - (trim.width + trim.x);
                if (this.parent.showUnselectedClose && (this.parent.showClose || item.showClose)) {
                    maxImageWidth -= item.closeRect.width + 4;
                }
                if (imageBounds.width < maxImageWidth) {
                    final int imageX = xDraw;
                    final int imageHeight = imageBounds.height;
                    int imageY = y + (height - imageHeight) / 2;
                    imageY += (this.parent.onBottom ? -1 : 1);
                    final int imageWidth = imageBounds.width * imageHeight / imageBounds.height;
                    gc.drawImage(image, imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height, imageX, imageY, imageWidth, imageHeight);
                    xDraw += imageWidth + 4;
                }
            }
            int textWidth = x + width - xDraw - (trim.width + trim.x);
            if (this.parent.showUnselectedClose && (this.parent.showClose || item.showClose)) {
                textWidth -= item.closeRect.width + 4;
            }
            if (textWidth > 0) {
                final Font gcFont = gc.getFont();
                gc.setFont((item.font == null) ? this.parent.getFont() : item.font);
                if (item.shortenedText == null || item.shortenedTextWidth != textWidth) {
                    item.shortenedText = this.shortenText(gc, item.getText(), textWidth);
                    item.shortenedTextWidth = textWidth;
                }
                final Point extent = gc.textExtent(item.shortenedText, 11);
                int textY = y + (height - extent.y) / 2;
                textY += (this.parent.onBottom ? -1 : 1);
                gc.setForeground((item.foreground == null) ? this.parent.getForeground() : item.foreground);
                gc.drawText(item.shortenedText, xDraw, textY, 11);
                gc.setFont(gcFont);
            }
            if (this.parent.showUnselectedClose && (this.parent.showClose || item.showClose)) {
                this.drawClose(gc, item.closeRect, item.closeImageState);
            }
        }
    }
    
    void fillRegion(final GC gc, final Region region) {
        final Region clipping = new Region();
        gc.getClipping(clipping);
        region.intersect(clipping);
        gc.setClipping(region);
        gc.fillRectangle(region.getBounds());
        gc.setClipping(clipping);
        clipping.dispose();
    }
    
    Color getFillColor() {
        if (this.fillColor == null) {
            this.fillColor = new Color(CTabFolderRenderer.CLOSE_FILL);
        }
        return this.fillColor;
    }
    
    private Font getChevronFont(final Display display) {
        if (this.chevronFont == null) {
            final Point dpi = display.getDPI();
            final int fontHeight = 720 / dpi.y;
            final FontData fd = this.parent.getFont().getFontData()[0];
            fd.setHeight(fontHeight);
            this.chevronFont = new Font(display, fd);
        }
        return this.chevronFont;
    }
    
    boolean isSelectionHighlightColorsCacheHit(final Color start) {
        if (this.selectionHighlightGradientColorsCache == null) {
            return false;
        }
        if (this.selectionHighlightGradientColorsCache.length < 2) {
            return false;
        }
        final Color highlightBegin = this.selectionHighlightGradientColorsCache[0];
        final Color highlightEnd = this.selectionHighlightGradientColorsCache[this.selectionHighlightGradientColorsCache.length - 1];
        return highlightBegin.equals(start) && this.selectionHighlightGradientColorsCache.length == this.parent.tabHeight && highlightEnd.equals(this.parent.selectionBackground);
    }
    
    void resetChevronFont() {
        if (this.chevronFont != null) {
            this.chevronFont.dispose();
            this.chevronFont = null;
        }
    }
    
    void setSelectionHighlightGradientColor(final Color start) {
        this.selectionHighlightGradientBegin = null;
        if (start == null) {
            return;
        }
        if (this.parent.getDisplay().getDepth() < 15) {
            return;
        }
        if (this.parent.selectionGradientColors.length < 2) {
            return;
        }
        this.selectionHighlightGradientBegin = start;
        if (!this.isSelectionHighlightColorsCacheHit(start)) {
            this.createSelectionHighlightGradientColors(start);
        }
    }
    
    String shortenText(final GC gc, final String text, final int width) {
        return this.useEllipses() ? this.shortenText(gc, text, width, "...") : this.shortenText(gc, text, width, "");
    }
    
    String shortenText(final GC gc, String text, final int width, final String ellipses) {
        if (gc.textExtent(text, 11).x <= width) {
            return text;
        }
        final int ellipseWidth = gc.textExtent(ellipses, 11).x;
        final int length = text.length();
        final TextLayout layout = new TextLayout(this.parent.getDisplay());
        layout.setText(text);
        int end;
        for (end = layout.getPreviousOffset(length, 2); end > 0; end = layout.getPreviousOffset(end, 2)) {
            text = text.substring(0, end);
            final int l = gc.textExtent(text, 11).x;
            if (l + ellipseWidth <= width) {
                break;
            }
        }
        layout.dispose();
        return (end == 0) ? text.substring(0, 1) : (text + ellipses);
    }
    
    void updateCurves() {
        if (this.getClass().getName().equals("org.eclipse.e4.ui.workbench.renderers.swt.CTabRendering")) {
            return;
        }
        final int tabHeight = this.parent.tabHeight;
        if (tabHeight == this.lastTabHeight) {
            return;
        }
        this.lastTabHeight = tabHeight;
        if (this.parent.onBottom) {
            final int d = tabHeight - 12;
            this.curve = new int[] { 0, 13 + d, 0, 12 + d, 2, 12 + d, 3, 11 + d, 5, 11 + d, 6, 10 + d, 7, 10 + d, 9, 8 + d, 10, 8 + d, 11, 7 + d, 11 + d, 7, 12 + d, 6, 13 + d, 6, 15 + d, 4, 16 + d, 4, 17 + d, 3, 19 + d, 3, 20 + d, 2, 22 + d, 2, 23 + d, 1 };
            this.curveWidth = 26 + d;
            this.curveIndent = this.curveWidth / 3;
        }
        else {
            final int d = tabHeight - 12;
            this.curve = new int[] { 0, 0, 0, 1, 2, 1, 3, 2, 5, 2, 6, 3, 7, 3, 9, 5, 10, 5, 11, 6, 11 + d, 6 + d, 12 + d, 7 + d, 13 + d, 7 + d, 15 + d, 9 + d, 16 + d, 9 + d, 17 + d, 10 + d, 19 + d, 10 + d, 20 + d, 11 + d, 22 + d, 11 + d, 23 + d, 12 + d };
            this.curveWidth = 26 + d;
            this.curveIndent = this.curveWidth / 3;
            this.topCurveHighlightStart = new int[] { 0, 2, 1, 2, 2, 2, 3, 3, 4, 3, 5, 3, 6, 4, 7, 4, 8, 5, 9, 6, 10, 6 };
            this.topCurveHighlightEnd = new int[] { 10 + d, 6 + d, 11 + d, 7 + d, 12 + d, 8 + d, 13 + d, 8 + d, 14 + d, 9 + d, 15 + d, 10 + d, 16 + d, 10 + d, 17 + d, 11 + d, 18 + d, 11 + d, 19 + d, 11 + d, 20 + d, 12 + d, 21 + d, 12 + d, 22 + d, 12 + d };
        }
    }
    
    boolean useEllipses() {
        return this.parent.simple;
    }
    
    static {
        TOP_LEFT_CORNER_HILITE = new int[] { 5, 2, 4, 2, 3, 3, 2, 4, 2, 5, 1, 6 };
        TOP_LEFT_CORNER = new int[] { 0, 6, 1, 5, 1, 4, 4, 1, 5, 1, 6, 0 };
        TOP_RIGHT_CORNER = new int[] { -6, 0, -5, 1, -4, 1, -1, 4, -1, 5, 0, 6 };
        BOTTOM_LEFT_CORNER = new int[] { 0, -6, 1, -5, 1, -4, 4, -1, 5, -1, 6, 0 };
        BOTTOM_RIGHT_CORNER = new int[] { -6, 0, -5, -1, -4, -1, -1, -4, -1, -5, 0, -6 };
        SIMPLE_TOP_LEFT_CORNER = new int[] { 0, 2, 1, 1, 2, 0 };
        SIMPLE_TOP_RIGHT_CORNER = new int[] { -2, 0, -1, 1, 0, 2 };
        SIMPLE_BOTTOM_LEFT_CORNER = new int[] { 0, -2, 1, -1, 2, 0 };
        SIMPLE_BOTTOM_RIGHT_CORNER = new int[] { -2, 0, -1, -1, 0, -2 };
        SIMPLE_UNSELECTED_INNER_CORNER = new int[] { 0, 0 };
        TOP_LEFT_CORNER_BORDERLESS = new int[] { 0, 6, 1, 5, 1, 4, 4, 1, 5, 1, 6, 0 };
        TOP_RIGHT_CORNER_BORDERLESS = new int[] { -7, 0, -6, 1, -5, 1, -2, 4, -2, 5, -1, 6 };
        BOTTOM_LEFT_CORNER_BORDERLESS = new int[] { 0, -6, 1, -6, 1, -5, 2, -4, 4, -2, 5, -1, 6, -1, 6, 0 };
        BOTTOM_RIGHT_CORNER_BORDERLESS = new int[] { -7, 0, -7, -1, -6, -1, -5, -2, -3, -4, -2, -5, -2, -6, -1, -6 };
        SIMPLE_TOP_LEFT_CORNER_BORDERLESS = new int[] { 0, 2, 1, 1, 2, 0 };
        SIMPLE_TOP_RIGHT_CORNER_BORDERLESS = new int[] { -3, 0, -2, 1, -1, 2 };
        SIMPLE_BOTTOM_LEFT_CORNER_BORDERLESS = new int[] { 0, -3, 1, -2, 2, -1, 3, 0 };
        SIMPLE_BOTTOM_RIGHT_CORNER_BORDERLESS = new int[] { -4, 0, -3, -1, -2, -2, -1, -3 };
        CLOSE_FILL = new RGB(240, 64, 64);
    }
}
