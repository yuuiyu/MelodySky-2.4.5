//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;

public class CLabel extends Canvas
{
    private static final int GAP = 5;
    private static final int DEFAULT_MARGIN = 3;
    private static final String ELLIPSIS = "...";
    private int align;
    private int leftMargin;
    private int topMargin;
    private int rightMargin;
    private int bottomMargin;
    private String text;
    private Image image;
    private String appToolTipText;
    private boolean ignoreDispose;
    private Image backgroundImage;
    private Color[] gradientColors;
    private int[] gradientPercents;
    private boolean gradientVertical;
    private Color background;
    private static int DRAW_FLAGS;
    
    public CLabel(final Composite parent, int style) {
        super(parent, checkStyle(style));
        this.align = 16384;
        this.leftMargin = 3;
        this.topMargin = 3;
        this.rightMargin = 3;
        this.bottomMargin = 3;
        if ((style & 0x1020000) == 0x0) {
            style |= 0x4000;
        }
        if ((style & 0x1000000) != 0x0) {
            this.align = 16777216;
        }
        if ((style & 0x20000) != 0x0) {
            this.align = 131072;
        }
        if ((style & 0x4000) != 0x0) {
            this.align = 16384;
        }
        this.addPaintListener(this::onPaint);
        this.addTraverseListener(event -> {
            if (event.detail == 128) {
                this.onMnemonic(event);
            }
            return;
        });
        this.addListener(12, this::onDispose);
        this.initAccessible();
    }
    
    private static int checkStyle(int style) {
        if ((style & 0x800) != 0x0) {
            style |= 0x4;
        }
        final int mask = 100663340;
        style &= 0x600002C;
        return style |= 0x20080000;
    }
    
    @Override
    public Point computeSize(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        final Point e = this.getTotalSize(this.image, this.text);
        if (wHint == -1) {
            final Point point3;
            final Point point = point3 = e;
            point3.x += this.leftMargin + this.rightMargin;
        }
        else {
            e.x = wHint;
        }
        if (hHint == -1) {
            final Point point4;
            final Point point2 = point4 = e;
            point4.y += this.topMargin + this.bottomMargin;
        }
        else {
            e.y = hHint;
        }
        return e;
    }
    
    private void drawBevelRect(final GC gc, final int x, final int y, final int w, final int h, final Color topleft, final Color bottomright) {
        gc.setForeground(bottomright);
        gc.drawLine(x + w, y, x + w, y + h);
        gc.drawLine(x, y + h, x + w, y + h);
        gc.setForeground(topleft);
        gc.drawLine(x, y, x + w - 1, y);
        gc.drawLine(x, y, x, y + h - 1);
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
    
    public int getAlignment() {
        return this.align;
    }
    
    public int getBottomMargin() {
        return this.bottomMargin;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public int getLeftMargin() {
        return this.leftMargin;
    }
    
    public int getRightMargin() {
        return this.rightMargin;
    }
    
    private Point getTotalSize(final Image image, final String text) {
        final Point size = new Point(0, 0);
        if (image != null) {
            final Rectangle r = image.getBounds();
            final Point point5;
            final Point point = point5 = size;
            point5.x += r.width;
            final Point point6;
            final Point point2 = point6 = size;
            point6.y += r.height;
        }
        final GC gc = new GC(this);
        if (text != null && text.length() > 0) {
            final Point e = gc.textExtent(text, CLabel.DRAW_FLAGS);
            final Point point7;
            final Point point3 = point7 = size;
            point7.x += e.x;
            size.y = Math.max(size.y, e.y);
            if (image != null) {
                final Point point8;
                final Point point4 = point8 = size;
                point8.x += 5;
            }
        }
        else {
            size.y = Math.max(size.y, gc.getFontMetrics().getHeight());
        }
        gc.dispose();
        return size;
    }
    
    @Override
    public int getStyle() {
        int style = super.getStyle();
        switch (this.align) {
            case 131072: {
                style |= 0x20000;
                break;
            }
            case 16777216: {
                style |= 0x1000000;
                break;
            }
            case 16384: {
                style |= 0x4000;
                break;
            }
        }
        return style;
    }
    
    public String getText() {
        return this.text;
    }
    
    @Override
    public String getToolTipText() {
        this.checkWidget();
        return this.appToolTipText;
    }
    
    public int getTopMargin() {
        return this.topMargin;
    }
    
    private void initAccessible() {
        class llllll extends AccessibleAdapter
        {
            final /* synthetic */ CLabel this$0;
            
            llllll(final CLabel this$0) {
                this.this$0 = this$0;
            }
            
            public void getName(final AccessibleEvent e) {
                e.result = this.this$0.getText();
            }
            
            public void getHelp(final AccessibleEvent e) {
                e.result = this.this$0.getToolTipText();
            }
            
            public void getKeyboardShortcut(final AccessibleEvent e) {
                final char mnemonic = this.this$0._findMnemonic(this.this$0.text);
                if (mnemonic != '\0') {
                    e.result = "Alt+" + mnemonic;
                }
            }
        }
        class lIlllI extends AccessibleControlAdapter
        {
            final /* synthetic */ CLabel this$0;
            
            lIlllI(final CLabel this$0) {
                this.this$0 = this$0;
            }
            
            public void getChildAtPoint(final AccessibleControlEvent e) {
                e.childID = -1;
            }
            
            public void getLocation(final AccessibleControlEvent e) {
                final Rectangle rect = this.this$0.getDisplay().map(this.this$0.getParent(), null, this.this$0.getBounds());
                e.x = rect.x;
                e.y = rect.y;
                e.width = rect.width;
                e.height = rect.height;
            }
            
            public void getChildCount(final AccessibleControlEvent e) {
                e.detail = 0;
            }
            
            public void getRole(final AccessibleControlEvent e) {
                e.detail = 41;
            }
            
            public void getState(final AccessibleControlEvent e) {
                e.detail = 64;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/eclipse/swt/custom/CLabel.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //     4: astore_1        /* accessible */
        //     5: aload_1         /* accessible */
        //     6: new             Lorg/eclipse/swt/custom/llllll;
        //     9: dup            
        //    10: aload_0         /* this */
        //    11: invokespecial   org/eclipse/swt/custom/llllll.<init>:(Lorg/eclipse/swt/custom/CLabel;)V
        //    14: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //    17: aload_1         /* accessible */
        //    18: new             Lorg/eclipse/swt/custom/lIlllI;
        //    21: dup            
        //    22: aload_0         /* this */
        //    23: invokespecial   org/eclipse/swt/custom/lIlllI.<init>:(Lorg/eclipse/swt/custom/CLabel;)V
        //    26: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleControlListener:(Lorg/eclipse/swt/accessibility/AccessibleControlListener;)V
        //    29: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void onDispose(final Event event) {
        if (this.ignoreDispose) {
            this.ignoreDispose = false;
            return;
        }
        this.ignoreDispose = true;
        this.notifyListeners(event.type, event);
        event.type = 0;
        this.gradientColors = null;
        this.gradientPercents = null;
        this.backgroundImage = null;
        this.text = null;
        this.image = null;
        this.appToolTipText = null;
    }
    
    void onMnemonic(final TraverseEvent event) {
        final char mnemonic = this._findMnemonic(this.text);
        if (mnemonic == '\0') {
            return;
        }
        if (Character.toLowerCase(event.character) != mnemonic) {
            return;
        }
        for (Composite control = this.getParent(); control != null; control = control.getParent()) {
            Control[] children;
            int index;
            for (children = control.getChildren(), index = 0; index < children.length && children[index] != this; ++index) {}
            if (++index < children.length && children[index].setFocus()) {
                event.doit = true;
                event.detail = 0;
            }
        }
    }
    
    void onPaint(final PaintEvent event) {
        final Rectangle rect = this.getClientArea();
        if (rect.width == 0 || rect.height == 0) {
            return;
        }
        boolean shortenText = false;
        final String t = this.text;
        Image img = this.image;
        final int availableWidth = Math.max(0, rect.width - (this.leftMargin + this.rightMargin));
        Point extent = this.getTotalSize(img, t);
        if (extent.x > availableWidth) {
            img = null;
            extent = this.getTotalSize(img, t);
            if (extent.x > availableWidth) {
                shortenText = true;
            }
        }
        final GC gc = event.gc;
        final String[] lines = (String[])((this.text == null) ? null : this.splitString(this.text));
        if (shortenText) {
            extent.x = 0;
            for (int i = 0; i < lines.length; ++i) {
                final Point e = gc.textExtent(lines[i], CLabel.DRAW_FLAGS);
                if (e.x > availableWidth) {
                    lines[i] = this.shortenText(gc, lines[i], availableWidth);
                    extent.x = Math.max(extent.x, this.getTotalSize(null, lines[i]).x);
                }
                else {
                    extent.x = Math.max(extent.x, e.x);
                }
            }
            if (this.appToolTipText == null) {
                super.setToolTipText(this.text);
            }
        }
        else {
            super.setToolTipText(this.appToolTipText);
        }
        int x = rect.x + this.leftMargin;
        if (this.align == 16777216) {
            x = (rect.width - extent.x) / 2;
        }
        if (this.align == 131072) {
            x = rect.width - this.rightMargin - extent.x;
        }
        try {
            if (this.backgroundImage != null) {
                final Rectangle imageRect = this.backgroundImage.getBounds();
                gc.setBackground(this.getBackground());
                gc.fillRectangle(rect);
                for (int xPos = 0; xPos < rect.width; xPos += imageRect.width) {
                    for (int yPos = 0; yPos < rect.height; yPos += imageRect.height) {
                        gc.drawImage(this.backgroundImage, xPos, yPos);
                    }
                }
            }
            else if (this.gradientColors != null) {
                final Color oldBackground = gc.getBackground();
                if (this.gradientColors.length == 1) {
                    if (this.gradientColors[0] != null) {
                        gc.setBackground(this.gradientColors[0]);
                    }
                    gc.fillRectangle(0, 0, rect.width, rect.height);
                }
                else {
                    final Color oldForeground = gc.getForeground();
                    Color lastColor = this.gradientColors[0];
                    if (lastColor == null) {
                        lastColor = oldBackground;
                    }
                    int pos = 0;
                    for (int j = 0; j < this.gradientPercents.length; ++j) {
                        gc.setForeground(lastColor);
                        lastColor = this.gradientColors[j + 1];
                        if (lastColor == null) {
                            lastColor = oldBackground;
                        }
                        gc.setBackground(lastColor);
                        if (this.gradientVertical) {
                            final int gradientHeight = this.gradientPercents[j] * rect.height / 100 - pos;
                            gc.fillGradientRectangle(0, pos, rect.width, gradientHeight, true);
                            pos += gradientHeight;
                        }
                        else {
                            final int gradientWidth = this.gradientPercents[j] * rect.width / 100 - pos;
                            gc.fillGradientRectangle(pos, 0, gradientWidth, rect.height, false);
                            pos += gradientWidth;
                        }
                    }
                    if (this.gradientVertical && pos < rect.height) {
                        gc.setBackground(this.getBackground());
                        gc.fillRectangle(0, pos, rect.width, rect.height - pos);
                    }
                    if (!this.gradientVertical && pos < rect.width) {
                        gc.setBackground(this.getBackground());
                        gc.fillRectangle(pos, 0, rect.width - pos, rect.height);
                    }
                    gc.setForeground(oldForeground);
                }
                gc.setBackground(oldBackground);
            }
            else if ((this.background != null || (this.getStyle() & 0x20000000) == 0x0) && this.background.getAlpha() > 0) {
                gc.setBackground(this.getBackground());
                gc.fillRectangle(rect);
            }
        }
        catch (SWTException e2) {
            if ((this.getStyle() & 0x20000000) == 0x0) {
                gc.setBackground(this.getBackground());
                gc.fillRectangle(rect);
            }
        }
        final int style = this.getStyle();
        if ((style & 0x4) != 0x0 || (style & 0x8) != 0x0) {
            this.paintBorder(gc, rect);
        }
        Rectangle imageRect2 = null;
        int lineHeight = 0;
        int textHeight = 0;
        int imageHeight = 0;
        if (img != null) {
            imageRect2 = img.getBounds();
            imageHeight = imageRect2.height;
        }
        if (lines != null) {
            lineHeight = gc.getFontMetrics().getHeight();
            textHeight = lines.length * lineHeight;
        }
        int imageY = 0;
        int midPoint = 0;
        int lineY = 0;
        if (imageHeight > textHeight) {
            if (this.topMargin == 3 && this.bottomMargin == 3) {
                imageY = rect.y + (rect.height - imageHeight) / 2;
            }
            else {
                imageY = this.topMargin;
            }
            midPoint = imageY + imageHeight / 2;
            lineY = midPoint - textHeight / 2;
        }
        else {
            if (this.topMargin == 3 && this.bottomMargin == 3) {
                lineY = rect.y + (rect.height - textHeight) / 2;
            }
            else {
                lineY = this.topMargin;
            }
            midPoint = lineY + textHeight / 2;
            imageY = midPoint - imageHeight / 2;
        }
        if (img != null) {
            gc.drawImage(img, 0, 0, imageRect2.width, imageHeight, x, imageY, imageRect2.width, imageHeight);
            x += imageRect2.width + 5;
            final Point point2;
            final Point point = point2 = extent;
            point2.x -= imageRect2.width + 5;
        }
        if (lines != null) {
            gc.setForeground(this.getForeground());
            for (final String line : lines) {
                int lineX = x;
                if (lines.length > 1) {
                    if (this.align == 16777216) {
                        final int lineWidth = gc.textExtent(line, CLabel.DRAW_FLAGS).x;
                        lineX = x + Math.max(0, (extent.x - lineWidth) / 2);
                    }
                    if (this.align == 131072) {
                        final int lineWidth = gc.textExtent(line, CLabel.DRAW_FLAGS).x;
                        lineX = Math.max(x, rect.x + rect.width - this.rightMargin - lineWidth);
                    }
                }
                gc.drawText(line, lineX, lineY, CLabel.DRAW_FLAGS);
                lineY += lineHeight;
            }
        }
    }
    
    private void paintBorder(final GC gc, final Rectangle r) {
        final Display disp = this.getDisplay();
        Color c1 = null;
        Color c2 = null;
        final int style = this.getStyle();
        if ((style & 0x4) != 0x0) {
            c1 = disp.getSystemColor(18);
            c2 = disp.getSystemColor(20);
        }
        if ((style & 0x8) != 0x0) {
            c1 = disp.getSystemColor(19);
            c2 = disp.getSystemColor(18);
        }
        if (c1 != null && c2 != null) {
            gc.setLineWidth(1);
            this.drawBevelRect(gc, r.x, r.y, r.width - 1, r.height - 1, c1, c2);
        }
    }
    
    public void setAlignment(final int align) {
        this.checkWidget();
        if (align != 16384 && align != 131072 && align != 16777216) {
            SWT.error(5);
        }
        if (this.align != align) {
            this.align = align;
            this.redraw();
        }
    }
    
    @Override
    public void setBackground(final Color color) {
        super.setBackground(color);
        if (this.backgroundImage == null && this.gradientColors == null && this.gradientPercents == null) {
            if (color == null) {
                if (this.background == null) {
                    return;
                }
            }
            else if (color.equals(this.background)) {
                return;
            }
        }
        this.background = color;
        this.backgroundImage = null;
        this.gradientColors = null;
        this.gradientPercents = null;
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
            if (this.getDisplay().getDepth() < 15) {
                colors = new Color[] { colors[colors.length - 1] };
                percents = new int[0];
            }
            for (int i = 0; i < percents.length; ++i) {
                if (percents[i] < 0 || percents[i] > 100) {
                    SWT.error(5);
                }
                if (i > 0 && percents[i] < percents[i - 1]) {
                    SWT.error(5);
                }
            }
        }
        final Color background = this.getBackground();
        if (this.backgroundImage == null) {
            if (this.gradientColors != null && colors != null && this.gradientColors.length == colors.length) {
                boolean same = false;
                for (int j = 0; j < this.gradientColors.length; ++j) {
                    same = (this.gradientColors[j] == colors[j] || (this.gradientColors[j] == null && colors[j] == background) || (this.gradientColors[j] == background && colors[j] == null));
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
        }
        else {
            this.backgroundImage = null;
        }
        if (colors == null) {
            this.gradientColors = null;
            this.gradientPercents = null;
            this.gradientVertical = false;
        }
        else {
            this.gradientColors = new Color[colors.length];
            for (int k = 0; k < colors.length; ++k) {
                this.gradientColors[k] = ((colors[k] != null) ? colors[k] : background);
            }
            this.gradientPercents = new int[percents.length];
            for (int k = 0; k < percents.length; ++k) {
                this.gradientPercents[k] = percents[k];
            }
            this.gradientVertical = vertical;
        }
        this.redraw();
    }
    
    public void setBackground(final Image image) {
        this.checkWidget();
        if (image == this.backgroundImage) {
            return;
        }
        if (image != null) {
            this.gradientColors = null;
            this.gradientPercents = null;
        }
        this.backgroundImage = image;
        this.redraw();
    }
    
    public void setBottomMargin(final int bottomMargin) {
        this.checkWidget();
        if (this.bottomMargin == bottomMargin || bottomMargin < 0) {
            return;
        }
        this.bottomMargin = bottomMargin;
        this.redraw();
    }
    
    @Override
    public void setFont(final Font font) {
        super.setFont(font);
        this.redraw();
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != this.image) {
            this.image = image;
            this.redraw();
        }
    }
    
    public void setLeftMargin(final int leftMargin) {
        this.checkWidget();
        if (this.leftMargin == leftMargin || leftMargin < 0) {
            return;
        }
        this.leftMargin = leftMargin;
        this.redraw();
    }
    
    public void setMargins(final int leftMargin, final int topMargin, final int rightMargin, final int bottomMargin) {
        this.checkWidget();
        this.leftMargin = Math.max(0, leftMargin);
        this.topMargin = Math.max(0, topMargin);
        this.rightMargin = Math.max(0, rightMargin);
        this.bottomMargin = Math.max(0, bottomMargin);
        this.redraw();
    }
    
    public void setRightMargin(final int rightMargin) {
        this.checkWidget();
        if (this.rightMargin == rightMargin || rightMargin < 0) {
            return;
        }
        this.rightMargin = rightMargin;
        this.redraw();
    }
    
    public void setText(String text) {
        this.checkWidget();
        if (text == null) {
            text = "";
        }
        if (!text.equals(this.text)) {
            this.text = text;
            this.redraw();
        }
    }
    
    @Override
    public void setToolTipText(final String string) {
        super.setToolTipText(string);
        this.appToolTipText = super.getToolTipText();
    }
    
    public void setTopMargin(final int topMargin) {
        this.checkWidget();
        if (this.topMargin == topMargin || topMargin < 0) {
            return;
        }
        this.topMargin = topMargin;
        this.redraw();
    }
    
    protected String shortenText(final GC gc, final String t, final int width) {
        if (t == null) {
            return null;
        }
        final int w = gc.textExtent("...", CLabel.DRAW_FLAGS).x;
        if (width <= w) {
            return t;
        }
        final int l = t.length();
        int max = l / 2;
        int min = 0;
        int mid = (max + min) / 2 - 1;
        if (mid <= 0) {
            return t;
        }
        final TextLayout layout = new TextLayout(this.getDisplay());
        layout.setText(t);
        mid = this.validateOffset(layout, mid);
        while (min < mid && mid < max) {
            final String s1 = t.substring(0, mid);
            final String s2 = t.substring(this.validateOffset(layout, l - mid), l);
            final int l2 = gc.textExtent(s1, CLabel.DRAW_FLAGS).x;
            final int l3 = gc.textExtent(s2, CLabel.DRAW_FLAGS).x;
            if (l2 + w + l3 > width) {
                max = mid;
                mid = this.validateOffset(layout, (max + min) / 2);
            }
            else if (l2 + w + l3 < width) {
                min = mid;
                mid = this.validateOffset(layout, (max + min) / 2);
            }
            else {
                min = max;
            }
        }
        final String result = (mid == 0) ? t : (t.substring(0, mid) + "..." + t.substring(this.validateOffset(layout, l - mid), l));
        layout.dispose();
        return result;
    }
    
    int validateOffset(final TextLayout layout, final int offset) {
        final int nextOffset = layout.getNextOffset(offset, 2);
        if (nextOffset != offset) {
            return layout.getPreviousOffset(nextOffset, 2);
        }
        return offset;
    }
    
    private String[] splitString(final String text) {
        String[] lines = { null };
        int start = 0;
        int pos;
        do {
            pos = text.indexOf(10, start);
            if (pos == -1) {
                lines[lines.length - 1] = text.substring(start);
            }
            else {
                final boolean crlf = pos > 0 && text.charAt(pos - 1) == '\r';
                lines[lines.length - 1] = text.substring(start, pos - (crlf ? 1 : 0));
                start = pos + 1;
                final String[] newLines = new String[lines.length + 1];
                System.arraycopy(lines, 0, newLines, 0, lines.length);
                lines = newLines;
            }
        } while (pos != -1);
        return lines;
    }
    
    static {
        CLabel.DRAW_FLAGS = 15;
    }
}
