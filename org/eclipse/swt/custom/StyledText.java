//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.*;
import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.widgets.*;
import java.util.stream.*;
import org.eclipse.swt.dnd.*;
import java.util.function.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.printing.*;
import java.util.*;

public class StyledText extends Canvas
{
    static final char TAB = '\t';
    static final String PlatformLineDelimiter;
    static final int BIDI_CARET_WIDTH = 3;
    static final int DEFAULT_WIDTH = 64;
    static final int DEFAULT_HEIGHT = 64;
    static final int V_SCROLL_RATE = 50;
    static final int H_SCROLL_RATE = 10;
    static final int PREVIOUS_OFFSET_TRAILING = 0;
    static final int OFFSET_LEADING = 1;
    static final String STYLEDTEXT_KEY = "org.eclipse.swt.internal.cocoa.styledtext";
    static final Comparator<Point> SELECTION_COMPARATOR;
    Color selectionBackground;
    Color selectionForeground;
    StyledTextContent content;
    StyledTextRenderer renderer;
    Listener listener;
    TextChangeListener textChangeListener;
    int verticalScrollOffset;
    int horizontalScrollOffset;
    boolean alwaysShowScroll;
    int ignoreResize;
    int topIndex;
    int topIndexY;
    int clientAreaHeight;
    int clientAreaWidth;
    int tabLength;
    int[] tabs;
    int leftMargin;
    int topMargin;
    int rightMargin;
    int bottomMargin;
    Color marginColor;
    int columnX;
    Caret[] carets;
    int[] caretOffsets;
    int caretAlignment;
    Point[] selection;
    int[] selectionAnchors;
    Point clipboardSelection;
    Point doubleClickSelection;
    boolean editable;
    boolean wordWrap;
    boolean visualWrap;
    boolean hasStyleWithVariableHeight;
    boolean hasVerticalIndent;
    boolean doubleClickEnabled;
    boolean overwrite;
    int textLimit;
    Map<Integer, Integer> keyActionMap;
    Color background;
    Color foreground;
    boolean customBackground;
    boolean customForeground;
    boolean enabled;
    boolean insideSetEnableCall;
    Clipboard clipboard;
    int clickCount;
    int autoScrollDirection;
    int autoScrollDistance;
    int lastTextChangeStart;
    int lastTextChangeNewLineCount;
    int lastTextChangeNewCharCount;
    int lastTextChangeReplaceLineCount;
    int lastTextChangeReplaceCharCount;
    int lastCharCount;
    int lastLineBottom;
    boolean bidiColoring;
    Image leftCaretBitmap;
    Image rightCaretBitmap;
    int caretDirection;
    int caretWidth;
    Caret defaultCaret;
    boolean updateCaretDirection;
    boolean dragDetect;
    IME ime;
    Cursor cursor;
    int alignment;
    boolean justify;
    int indent;
    int wrapIndent;
    int lineSpacing;
    int alignmentMargin;
    int newOrientation;
    int accCaretOffset;
    Accessible acc;
    AccessibleControlAdapter accControlAdapter;
    AccessibleAttributeAdapter accAttributeAdapter;
    AccessibleEditableTextListener accEditableTextListener;
    AccessibleTextExtendedAdapter accTextExtendedAdapter;
    AccessibleAdapter accAdapter;
    MouseNavigator mouseNavigator;
    boolean middleClickPressed;
    boolean blockSelection;
    int blockXAnchor;
    int blockYAnchor;
    int blockXLocation;
    int blockYLocation;
    static final boolean IS_MAC;
    static final boolean IS_GTK;
    
    private static int getX(final Point p) {
        return p.x;
    }
    
    public StyledText(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.verticalScrollOffset = 0;
        this.horizontalScrollOffset = 0;
        this.alwaysShowScroll = true;
        this.ignoreResize = 0;
        this.topIndex = 0;
        this.clientAreaHeight = 0;
        this.clientAreaWidth = 0;
        this.tabLength = 4;
        this.caretOffsets = new int[] { 0 };
        this.selection = new Point[] { new Point(0, 0) };
        this.selectionAnchors = new int[] { 0 };
        this.editable = true;
        this.wordWrap = false;
        this.visualWrap = false;
        this.hasStyleWithVariableHeight = false;
        this.hasVerticalIndent = false;
        this.doubleClickEnabled = true;
        this.overwrite = false;
        this.textLimit = -1;
        this.keyActionMap = new HashMap<Integer, Integer>();
        this.background = null;
        this.foreground = null;
        this.enabled = true;
        this.autoScrollDirection = 0;
        this.autoScrollDistance = 0;
        this.lastCharCount = 0;
        this.bidiColoring = false;
        this.leftCaretBitmap = null;
        this.rightCaretBitmap = null;
        this.caretDirection = 0;
        this.caretWidth = 0;
        this.defaultCaret = null;
        this.updateCaretDirection = true;
        this.dragDetect = true;
        this.newOrientation = 0;
        this.blockXAnchor = -1;
        this.blockYAnchor = -1;
        this.blockXLocation = -1;
        this.blockYLocation = -1;
        super.setForeground(this.getForeground());
        super.setDragDetect(false);
        final Display display = this.getDisplay();
        if ((style & 0x8) != 0x0) {
            this.setEditable(false);
        }
        final int n = this.isBidiCaret() ? 2 : 0;
        this.rightMargin = n;
        this.leftMargin = n;
        if ((style & 0x4) != 0x0 && (style & 0x800) != 0x0) {
            final int n2 = 2;
            this.bottomMargin = 2;
            this.rightMargin = 2;
            this.topMargin = 2;
            this.leftMargin = 2;
        }
        this.alignment = (style & 0x1024000);
        if (this.alignment == 0) {
            this.alignment = 16384;
        }
        this.clipboard = new Clipboard(display);
        this.installDefaultContent();
        (this.renderer = new StyledTextRenderer(this.getDisplay(), this)).setContent(this.content);
        this.renderer.setFont(this.getFont(), this.tabLength);
        this.ime = new IME(this, 0);
        this.defaultCaret = new Caret(this, 0);
        if ((style & 0x40) != 0x0) {
            this.setWordWrap(true);
        }
        if (this.isBidiCaret()) {
            this.createCaretBitmaps();
            final int direction;
            final Runnable runnable = () -> {
                direction = ((BidiUtil.getKeyboardLanguage() == 1) ? 131072 : 16384);
                if (direction == this.caretDirection) {
                    return;
                }
                else if (this.getCaret() != this.defaultCaret) {
                    return;
                }
                else {
                    this.setCaretLocations(Arrays.stream(this.caretOffsets).mapToObj((IntFunction<?>)this::getPointAtOffset).toArray(Point[]::new), direction);
                    return;
                }
            };
            BidiUtil.addLanguageListener(this, runnable);
        }
        this.setCaret(this.defaultCaret);
        this.calculateScrollBars();
        this.createKeyBindings();
        super.setCursor(display.getSystemCursor(19));
        this.installListeners();
        this.initializeAccessible();
        this.setData("DEFAULT_DROP_TARGET_EFFECT", new StyledTextDropTargetEffect(this));
        if (StyledText.IS_MAC) {
            this.setData("org.eclipse.swt.internal.cocoa.styledtext");
        }
    }
    
    public void addExtendedModifyListener(final ExtendedModifyListener extendedModifyListener) {
        this.checkWidget();
        if (extendedModifyListener == null) {
            SWT.error(4);
        }
        final StyledTextListener typedListener = new StyledTextListener((SWTEventListener)extendedModifyListener);
        this.addListener(3000, typedListener);
    }
    
    public void addBidiSegmentListener(final BidiSegmentListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.addListener(3007, new StyledTextListener((SWTEventListener)listener));
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void addCaretListener(final CaretListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.addListener(3011, new StyledTextListener((SWTEventListener)listener));
    }
    
    public void addLineBackgroundListener(final LineBackgroundListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (!this.isListening(3001)) {
            this.renderer.clearLineBackground(0, this.content.getLineCount());
        }
        this.addListener(3001, new StyledTextListener((SWTEventListener)listener));
    }
    
    public void addLineStyleListener(final LineStyleListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (!this.isListening(3002)) {
            this.setStyleRanges(0, 0, null, null, true);
            this.renderer.clearLineStyle(0, this.content.getLineCount());
        }
        this.addListener(3002, new StyledTextListener((SWTEventListener)listener));
        this.setCaretLocations();
    }
    
    public void addModifyListener(final ModifyListener modifyListener) {
        this.checkWidget();
        if (modifyListener == null) {
            SWT.error(4);
        }
        this.addListener(24, new TypedListener(modifyListener));
    }
    
    public void addPaintObjectListener(final PaintObjectListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.addListener(3008, new StyledTextListener((SWTEventListener)listener));
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.addListener(13, new TypedListener(listener));
    }
    
    public void addVerifyKeyListener(final VerifyKeyListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.addListener(3005, new StyledTextListener(listener));
    }
    
    public void addVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            SWT.error(4);
        }
        this.addListener(25, new TypedListener(verifyListener));
    }
    
    public void addWordMovementListener(final MovementListener movementListener) {
        this.checkWidget();
        if (movementListener == null) {
            SWT.error(4);
        }
        this.addListener(3009, new StyledTextListener((SWTEventListener)movementListener));
        this.addListener(3010, new StyledTextListener((SWTEventListener)movementListener));
    }
    
    public void append(final String string) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        final int lastChar = Math.max(this.getCharCount(), 0);
        this.replaceTextRange(lastChar, 0, string);
    }
    
    void calculateScrollBars() {
        final ScrollBar horizontalBar = this.getHorizontalBar();
        final ScrollBar verticalBar = this.getVerticalBar();
        this.setScrollBars(true);
        if (verticalBar != null) {
            verticalBar.setIncrement(this.getVerticalIncrement());
        }
        if (horizontalBar != null) {
            horizontalBar.setIncrement(this.getHorizontalIncrement());
        }
    }
    
    void calculateTopIndex(int delta) {
        final int oldDelta = delta;
        final int oldTopIndex = this.topIndex;
        final int oldTopIndexY = this.topIndexY;
        if (this.isFixedLineHeight()) {
            final int verticalIncrement = this.getVerticalIncrement();
            if (verticalIncrement == 0) {
                return;
            }
            this.topIndex = Compatibility.ceil(this.getVerticalScrollOffset(), verticalIncrement);
            if (this.topIndex >= 0) {
                if (this.clientAreaHeight > 0) {
                    final int bottomPixel = this.getVerticalScrollOffset() + this.clientAreaHeight;
                    this.topIndexY = this.getLinePixel(this.topIndex);
                    final int fullLineTopPixel = this.topIndex * verticalIncrement;
                    final int fullLineVisibleHeight = bottomPixel - fullLineTopPixel;
                    if (fullLineVisibleHeight < verticalIncrement) {
                        this.topIndex = this.getVerticalScrollOffset() / verticalIncrement;
                    }
                }
                else if (this.topIndex >= this.content.getLineCount()) {
                    this.topIndex = this.content.getLineCount() - 1;
                }
            }
        }
        else if (delta >= 0) {
            int lineIndex;
            int lineCount;
            for (delta -= this.topIndexY, lineIndex = this.topIndex, lineCount = this.content.getLineCount(); lineIndex < lineCount && delta > 0; delta -= this.renderer.getCachedLineHeight(lineIndex++)) {}
            if (lineIndex < lineCount && -delta + this.renderer.getCachedLineHeight(lineIndex) <= this.clientAreaHeight - this.topMargin - this.bottomMargin) {
                this.topIndex = lineIndex;
                this.topIndexY = -delta;
            }
            else {
                this.topIndex = lineIndex - 1;
                this.topIndexY = -this.renderer.getCachedLineHeight(this.topIndex) - delta;
            }
        }
        else {
            delta -= this.topIndexY;
            int lineIndex;
            for (lineIndex = this.topIndex; lineIndex > 0; --lineIndex) {
                final int lineHeight = this.renderer.getCachedLineHeight(lineIndex - 1);
                if (delta + lineHeight > 0) {
                    break;
                }
                delta += lineHeight;
            }
            if (lineIndex == 0 || -delta + this.renderer.getCachedLineHeight(lineIndex) <= this.clientAreaHeight - this.topMargin - this.bottomMargin) {
                this.topIndex = lineIndex;
                this.topIndexY = -delta;
            }
            else {
                this.topIndex = lineIndex - 1;
                this.topIndexY = -this.renderer.getCachedLineHeight(this.topIndex) - delta;
            }
        }
        if (this.topIndex < 0) {
            System.err.println("StyledText: topIndex was " + this.topIndex + ", isFixedLineHeight() = " + this.isFixedLineHeight() + ", delta = " + delta + ", content.getLineCount() = " + this.content.getLineCount() + ", clientAreaHeight = " + this.clientAreaHeight + ", oldTopIndex = " + oldTopIndex + ", oldTopIndexY = " + oldTopIndexY + ", getVerticalScrollOffset = " + this.getVerticalScrollOffset() + ", oldDelta = " + oldDelta + ", getVerticalIncrement() = " + this.getVerticalIncrement());
            this.topIndex = 0;
        }
        if (this.topIndex != oldTopIndex || oldTopIndexY != this.topIndexY) {
            final int width = this.renderer.getWidth();
            this.renderer.calculateClientArea();
            if (width != this.renderer.getWidth()) {
                this.setScrollBars(false);
            }
        }
    }
    
    static int checkStyle(int style) {
        if ((style & 0x4) != 0x0) {
            style &= 0xFFFFFCBD;
        }
        else {
            style |= 0x2;
            if ((style & 0x40) != 0x0) {
                style &= 0xFFFFFEFF;
            }
        }
        style |= 0x20140000;
        return style & 0xFEFFFFFF;
    }
    
    void claimBottomFreeSpace() {
        if (this.ime.getCompositionOffset() != -1) {
            return;
        }
        if (this.isFixedLineHeight()) {
            final int newVerticalOffset = Math.max(0, this.renderer.getHeight() - this.clientAreaHeight);
            if (newVerticalOffset < this.getVerticalScrollOffset()) {
                this.scrollVertical(newVerticalOffset - this.getVerticalScrollOffset(), true);
            }
        }
        else {
            final int bottomIndex = this.getPartialBottomIndex();
            final int height = this.getLinePixel(bottomIndex + 1);
            if (this.clientAreaHeight > height) {
                this.scrollVertical(-this.getAvailableHeightAbove(this.clientAreaHeight - height), true);
            }
        }
    }
    
    void claimRightFreeSpace() {
        final int newHorizontalOffset = Math.max(0, this.renderer.getWidth() - this.clientAreaWidth);
        if (newHorizontalOffset < this.horizontalScrollOffset) {
            this.scrollHorizontal(newHorizontalOffset - this.horizontalScrollOffset, true);
        }
    }
    
    void clearBlockSelection(final boolean reset, final boolean sendEvent) {
        if (reset) {
            this.resetSelection();
        }
        final int n = -1;
        this.blockYAnchor = -1;
        this.blockXAnchor = -1;
        final int n2 = -1;
        this.blockYLocation = -1;
        this.blockXLocation = -1;
        this.caretDirection = 0;
        this.updateCaretVisibility();
        super.redraw();
        if (sendEvent) {
            this.sendSelectionEvent();
        }
    }
    
    void clearSelection(final boolean sendEvent) {
        final int selectionStart = this.selection[0].x;
        final int selectionEnd = this.selection[0].y;
        this.resetSelection();
        if (selectionEnd - selectionStart > 0) {
            final int length = this.content.getCharCount();
            final int redrawStart = Math.min(selectionStart, length);
            final int redrawEnd = Math.min(selectionEnd, length);
            if (redrawEnd - redrawStart > 0) {
                this.internalRedrawRange(redrawStart, redrawEnd - redrawStart);
            }
            if (sendEvent) {
                this.sendSelectionEvent();
            }
        }
    }
    
    @Override
    public Point computeSize(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        final int lineCount = ((this.getStyle() & 0x4) != 0x0) ? 1 : this.content.getLineCount();
        int width = 0;
        int height = 0;
        if (wHint == -1 || hHint == -1) {
            final Display display = this.getDisplay();
            final int maxHeight = display.getClientArea().height;
            for (int lineIndex = 0; lineIndex < lineCount; ++lineIndex) {
                final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                final int wrapWidth = layout.getWidth();
                if (this.wordWrap) {
                    layout.setWidth((wHint == 0) ? 1 : ((wHint == -1) ? -1 : Math.max(1, wHint - this.leftMargin - this.rightMargin)));
                }
                final Rectangle rect = layout.getBounds();
                height += rect.height;
                width = Math.max(width, rect.width);
                layout.setWidth(wrapWidth);
                this.renderer.disposeTextLayout(layout);
                if (this.isFixedLineHeight() && height > maxHeight) {
                    break;
                }
            }
            if (this.isFixedLineHeight()) {
                height = lineCount * this.renderer.getLineHeight();
            }
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
        final int wTrim = this.getLeftMargin() + this.rightMargin + this.getCaretWidth();
        final int hTrim = this.topMargin + this.bottomMargin;
        final Rectangle rect2 = this.computeTrim(0, 0, width + wTrim, height + hTrim);
        return new Point(rect2.width, rect2.height);
    }
    
    public void copy() {
        this.checkWidget();
        this.copySelection(1);
    }
    
    public void copy(final int clipboardType) {
        this.checkWidget();
        this.copySelection(clipboardType);
    }
    
    boolean copySelection(final int type) {
        if (type != 1 && type != 2) {
            return false;
        }
        try {
            if (this.blockSelection && this.blockXLocation != -1) {
                final String text = this.getBlockSelectionText(StyledText.PlatformLineDelimiter);
                if (text.length() > 0) {
                    final TextTransfer plainTextTransfer = TextTransfer.getInstance();
                    final Object[] data = { text };
                    final Transfer[] types = { plainTextTransfer };
                    this.clipboard.setContents(data, types, type);
                    return true;
                }
            }
            else if (this.getSelectionRanges().length > 2) {
                final StringBuilder text2 = new StringBuilder();
                final int[] ranges = this.getSelectionRanges();
                for (int i = 0; i < ranges.length; i += 2) {
                    final int offset = ranges[i];
                    final int length = ranges[i + 1];
                    text2.append((length == 0) ? "" : this.getText(offset, offset + length - 1));
                    text2.append(StyledText.PlatformLineDelimiter);
                }
                text2.delete(text2.length() - StyledText.PlatformLineDelimiter.length(), text2.length());
                if (text2.length() > 0) {
                    this.clipboard.setContents(new Object[] { text2.toString() }, new Transfer[] { TextTransfer.getInstance() }, type);
                    return true;
                }
            }
            else {
                final int length2 = this.selection[0].y - this.selection[0].x;
                if (length2 > 0) {
                    this.setClipboardContent(this.selection[0].x, length2, type);
                    return true;
                }
            }
        }
        catch (SWTError error) {
            if (error.code != 2002) {
                throw error;
            }
        }
        return false;
    }
    
    public int getAlignment() {
        this.checkWidget();
        return this.alignment;
    }
    
    public boolean getAlwaysShowScrollBars() {
        this.checkWidget();
        return this.alwaysShowScroll;
    }
    
    int getAvailableHeightAbove(final int height) {
        int maxHeight = this.verticalScrollOffset;
        if (maxHeight == -1) {
            int lineIndex = this.topIndex - 1;
            maxHeight = -this.topIndexY;
            if (this.topIndexY > 0) {
                maxHeight += this.renderer.getLineHeight(lineIndex--);
            }
            while (height > maxHeight && lineIndex >= 0) {
                maxHeight += this.renderer.getLineHeight(lineIndex--);
            }
        }
        return Math.min(height, maxHeight);
    }
    
    int getAvailableHeightBellow(final int height) {
        final int partialBottomIndex = this.getPartialBottomIndex();
        final int topY = this.getLinePixel(partialBottomIndex);
        final int lineHeight = this.renderer.getLineHeight(partialBottomIndex);
        int availableHeight = 0;
        final int clientAreaHeight = this.clientAreaHeight - this.topMargin - this.bottomMargin;
        if (topY + lineHeight > clientAreaHeight) {
            availableHeight = lineHeight - (clientAreaHeight - topY);
        }
        for (int lineIndex = partialBottomIndex + 1, lineCount = this.content.getLineCount(); height > availableHeight && lineIndex < lineCount; availableHeight += this.renderer.getLineHeight(lineIndex++)) {}
        return Math.min(height, availableHeight);
    }
    
    public Color getMarginColor() {
        this.checkWidget();
        return (this.marginColor != null) ? this.marginColor : this.getBackground();
    }
    
    String getModelDelimitedText(final String text) {
        final int length = text.length();
        if (length == 0) {
            return text;
        }
        int crIndex = 0;
        int lfIndex = 0;
        int i = 0;
        final StringBuilder convertedText = new StringBuilder(length);
        final String delimiter = this.getLineDelimiter();
        while (i < length) {
            if (crIndex != -1) {
                crIndex = text.indexOf(13, i);
            }
            if (lfIndex != -1) {
                lfIndex = text.indexOf(10, i);
            }
            if (lfIndex == -1 && crIndex == -1) {
                break;
            }
            if ((crIndex < lfIndex && crIndex != -1) || lfIndex == -1) {
                convertedText.append(text.substring(i, crIndex));
                if (lfIndex == crIndex + 1) {
                    i = lfIndex + 1;
                }
                else {
                    i = crIndex + 1;
                }
            }
            else {
                convertedText.append(text.substring(i, lfIndex));
                i = lfIndex + 1;
            }
            if (this.isSingleLine()) {
                break;
            }
            convertedText.append(delimiter);
        }
        if (i < length && (!this.isSingleLine() || convertedText.length() == 0)) {
            convertedText.append(text.substring(i));
        }
        return convertedText.toString();
    }
    
    boolean checkDragDetect(final Event event) {
        if (!this.isListening(29)) {
            return false;
        }
        if (event.button != 1) {
            return false;
        }
        if (this.blockSelection && this.blockXLocation != -1) {
            final Rectangle rect = this.getBlockSelectionRectangle();
            if (rect.contains(event.x, event.y)) {
                return this.dragDetect(event);
            }
        }
        else {
            if (this.selection[0].x == this.selection[0].y) {
                return false;
            }
            final int offset = this.getOffsetAtPoint(event.x, event.y, null, true);
            if (this.selection[0].x <= offset && offset < this.selection[0].y) {
                return this.dragDetect(event);
            }
        }
        return false;
    }
    
    void createKeyBindings() {
        final int nextKey = this.isMirrored() ? 16777219 : 16777220;
        final int previousKey = this.isMirrored() ? 16777220 : 16777219;
        this.setKeyBinding(16777217, 16777217);
        this.setKeyBinding(16777218, 16777218);
        if (StyledText.IS_MAC) {
            this.setKeyBinding(previousKey | SWT.MOD1, 16777223);
            this.setKeyBinding(nextKey | SWT.MOD1, 16777224);
            this.setKeyBinding(16777223, 17039367);
            this.setKeyBinding(16777224, 17039368);
            this.setKeyBinding(0x1000001 | SWT.MOD1, 17039367);
            this.setKeyBinding(0x1000002 | SWT.MOD1, 17039368);
            this.setKeyBinding(nextKey | SWT.MOD3, 17039364);
            this.setKeyBinding(previousKey | SWT.MOD3, 17039363);
        }
        else {
            this.setKeyBinding(16777223, 16777223);
            this.setKeyBinding(16777224, 16777224);
            this.setKeyBinding(0x1000007 | SWT.MOD1, 17039367);
            this.setKeyBinding(0x1000008 | SWT.MOD1, 17039368);
            this.setKeyBinding(nextKey | SWT.MOD1, 17039364);
            this.setKeyBinding(previousKey | SWT.MOD1, 17039363);
        }
        this.setKeyBinding(16777221, 16777221);
        this.setKeyBinding(16777222, 16777222);
        this.setKeyBinding(0x1000005 | SWT.MOD1, 17039365);
        this.setKeyBinding(0x1000006 | SWT.MOD1, 17039366);
        this.setKeyBinding(nextKey, 16777220);
        this.setKeyBinding(previousKey, 16777219);
        this.setKeyBinding(0x1000001 | SWT.MOD2, 16908289);
        this.setKeyBinding(0x1000002 | SWT.MOD2, 16908290);
        if (StyledText.IS_MAC) {
            this.setKeyBinding(previousKey | SWT.MOD1 | SWT.MOD2, 16908295);
            this.setKeyBinding(nextKey | SWT.MOD1 | SWT.MOD2, 16908296);
            this.setKeyBinding(0x1000007 | SWT.MOD2, 17170439);
            this.setKeyBinding(0x1000008 | SWT.MOD2, 17170440);
            this.setKeyBinding(0x1000001 | SWT.MOD1 | SWT.MOD2, 17170439);
            this.setKeyBinding(0x1000002 | SWT.MOD1 | SWT.MOD2, 17170440);
            this.setKeyBinding(nextKey | SWT.MOD2 | SWT.MOD3, 17170436);
            this.setKeyBinding(previousKey | SWT.MOD2 | SWT.MOD3, 17170435);
        }
        else {
            this.setKeyBinding(0x1000007 | SWT.MOD2, 16908295);
            this.setKeyBinding(0x1000008 | SWT.MOD2, 16908296);
            this.setKeyBinding(0x1000007 | SWT.MOD1 | SWT.MOD2, 17170439);
            this.setKeyBinding(0x1000008 | SWT.MOD1 | SWT.MOD2, 17170440);
            this.setKeyBinding(nextKey | SWT.MOD1 | SWT.MOD2, 17170436);
            this.setKeyBinding(previousKey | SWT.MOD1 | SWT.MOD2, 17170435);
        }
        this.setKeyBinding(0x1000005 | SWT.MOD2, 16908293);
        this.setKeyBinding(0x1000006 | SWT.MOD2, 16908294);
        this.setKeyBinding(0x1000005 | SWT.MOD1 | SWT.MOD2, 17170437);
        this.setKeyBinding(0x1000006 | SWT.MOD1 | SWT.MOD2, 17170438);
        this.setKeyBinding(nextKey | SWT.MOD2, 16908292);
        this.setKeyBinding(previousKey | SWT.MOD2, 16908291);
        this.setKeyBinding(0x58 | SWT.MOD1, 131199);
        this.setKeyBinding(0x43 | SWT.MOD1, 17039369);
        this.setKeyBinding(0x56 | SWT.MOD1, 16908297);
        if (StyledText.IS_MAC) {
            this.setKeyBinding(0x7F | SWT.MOD2, 127);
            this.setKeyBinding(0x8 | SWT.MOD3, 262152);
            this.setKeyBinding(0x7F | SWT.MOD3, 262271);
        }
        else {
            this.setKeyBinding(0x7F | SWT.MOD2, 131199);
            this.setKeyBinding(0x1000009 | SWT.MOD1, 17039369);
            this.setKeyBinding(0x1000009 | SWT.MOD2, 16908297);
        }
        this.setKeyBinding(0x8 | SWT.MOD2, 8);
        this.setKeyBinding(8, 8);
        this.setKeyBinding(127, 127);
        this.setKeyBinding(0x8 | SWT.MOD1, 262152);
        this.setKeyBinding(0x7F | SWT.MOD1, 262271);
        this.setKeyBinding(16777225, 16777225);
    }
    
    void createCaretBitmaps() {
        final int caretWidth = 3;
        final Display display = this.getDisplay();
        if (this.leftCaretBitmap != null) {
            if (this.defaultCaret != null && this.leftCaretBitmap.equals(this.defaultCaret.getImage())) {
                this.defaultCaret.setImage(null);
            }
            this.leftCaretBitmap.dispose();
        }
        final int lineHeight = this.renderer.getLineHeight();
        this.leftCaretBitmap = new Image(display, 3, lineHeight);
        GC gc = new GC(this.leftCaretBitmap);
        gc.setBackground(display.getSystemColor(2));
        gc.fillRectangle(0, 0, 3, lineHeight);
        gc.setForeground(display.getSystemColor(1));
        gc.drawLine(0, 0, 0, lineHeight);
        gc.drawLine(0, 0, 2, 0);
        gc.drawLine(0, 1, 1, 1);
        gc.dispose();
        if (this.rightCaretBitmap != null) {
            if (this.defaultCaret != null && this.rightCaretBitmap.equals(this.defaultCaret.getImage())) {
                this.defaultCaret.setImage(null);
            }
            this.rightCaretBitmap.dispose();
        }
        this.rightCaretBitmap = new Image(display, 3, lineHeight);
        gc = new GC(this.rightCaretBitmap);
        gc.setBackground(display.getSystemColor(2));
        gc.fillRectangle(0, 0, 3, lineHeight);
        gc.setForeground(display.getSystemColor(1));
        gc.drawLine(2, 0, 2, lineHeight);
        gc.drawLine(0, 0, 2, 0);
        gc.drawLine(2, 1, 1, 1);
        gc.dispose();
    }
    
    public void cut() {
        this.checkWidget();
        if (this.copySelection(1)) {
            if (this.blockSelection && this.blockXLocation != -1) {
                this.insertBlockSelectionText('\0', 0);
            }
            else {
                this.doDelete();
            }
        }
    }
    
    void doAutoScroll(final Event event) {
        final int caretLine = this.getFirstCaretLine();
        if (event.y > this.clientAreaHeight - this.bottomMargin && caretLine != this.content.getLineCount() - 1) {
            this.doAutoScroll(1024, event.y - (this.clientAreaHeight - this.bottomMargin));
        }
        else if (event.y < this.topMargin && caretLine != 0) {
            this.doAutoScroll(128, this.topMargin - event.y);
        }
        else if (event.x < this.leftMargin && !this.wordWrap) {
            this.doAutoScroll(16777219, this.leftMargin - event.x);
        }
        else if (event.x > this.clientAreaWidth - this.rightMargin && !this.wordWrap) {
            this.doAutoScroll(16777220, event.x - (this.clientAreaWidth - this.rightMargin));
        }
        else {
            this.endAutoScroll();
        }
    }
    
    void doAutoScroll(final int direction, final int distance) {
        this.autoScrollDistance = distance;
        if (this.autoScrollDirection == direction) {
            return;
        }
        Runnable timer = null;
        final Display display = this.getDisplay();
        if (direction == 128) {
            timer = (Runnable)new llIIIl(this, display);
            this.autoScrollDirection = direction;
            display.timerExec(50, timer);
        }
        else if (direction == 1024) {
            timer = (Runnable)new lIIlIlI(this, display);
            this.autoScrollDirection = direction;
            display.timerExec(50, timer);
        }
        else if (direction == 16777220) {
            timer = (Runnable)new lllIII(this, display);
            this.autoScrollDirection = direction;
            display.timerExec(10, timer);
        }
        else if (direction == 16777219) {
            timer = (Runnable)new lIIllIl(this, display);
            this.autoScrollDirection = direction;
            display.timerExec(10, timer);
        }
    }
    
    void doBackspace() {
        final Event event = new Event();
        event.text = "";
        if (Arrays.stream(this.selection).anyMatch(p -> p.x != p.y)) {
            for (int i = this.selection.length - 1; i >= 0; --i) {
                final Point sel = this.selection[i];
                event.start = sel.x;
                event.end = sel.y;
                this.sendKeyEvent(event);
            }
        }
        else {
            for (int i = this.caretOffsets.length - 1; i >= 0; --i) {
                final int caretOffset = this.caretOffsets[i];
                if (caretOffset > 0) {
                    final int lineIndex = this.content.getLineAtOffset(caretOffset);
                    int lineOffset = this.content.getOffsetAtLine(lineIndex);
                    if (caretOffset == lineOffset) {
                        lineOffset = this.content.getOffsetAtLine(lineIndex - 1);
                        event.start = lineOffset + this.content.getLine(lineIndex - 1).length();
                        event.end = caretOffset;
                    }
                    else {
                        boolean isSurrogate = false;
                        final String lineText = this.content.getLine(lineIndex);
                        char ch = lineText.charAt(caretOffset - lineOffset - 1);
                        if ('\udc00' <= ch && ch <= '\udfff' && caretOffset - lineOffset - 2 >= 0) {
                            ch = lineText.charAt(caretOffset - lineOffset - 2);
                            isSurrogate = ('\ud800' <= ch && ch <= '\udbff');
                        }
                        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                        final int start = layout.getPreviousOffset(caretOffset - lineOffset, isSurrogate ? 2 : 1);
                        this.renderer.disposeTextLayout(layout);
                        event.start = start + lineOffset;
                        event.end = caretOffset;
                    }
                    this.sendKeyEvent(event);
                }
            }
        }
    }
    
    void doBlockColumn(final boolean next) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffsets[0], false);
        }
        int x = this.blockXLocation - this.horizontalScrollOffset;
        final int y = this.blockYLocation - this.getVerticalScrollOffset();
        final int[] trailing = { 0 };
        int offset = this.getOffsetAtPoint(x, y, trailing, true);
        if (offset != -1) {
            offset += trailing[0];
            final int lineIndex = this.content.getLineAtOffset(offset);
            int newOffset;
            if (next) {
                newOffset = this.getClusterNext(offset, lineIndex);
            }
            else {
                newOffset = this.getClusterPrevious(offset, lineIndex);
            }
            offset = ((newOffset != offset) ? newOffset : -1);
        }
        if (offset != -1) {
            this.setBlockSelectionOffset(offset, true);
            this.showCaret();
        }
        else {
            final int width = next ? this.renderer.averageCharWidth : (-this.renderer.averageCharWidth);
            final int maxWidth = Math.max(this.clientAreaWidth - this.rightMargin - this.leftMargin, this.renderer.getWidth());
            x = Math.max(0, Math.min(this.blockXLocation + width, maxWidth)) - this.horizontalScrollOffset;
            this.setBlockSelectionLocation(x, y, true);
            final Rectangle rect = new Rectangle(x, y, 0, 0);
            this.showLocation(rect, true);
        }
    }
    
    void doBlockContentStartEnd(final boolean end) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffsets[0], false);
        }
        final int offset = end ? this.content.getCharCount() : 0;
        this.setBlockSelectionOffset(offset, true);
        this.showCaret();
    }
    
    void doBlockWord(final boolean next) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffsets[0], false);
        }
        int x = this.blockXLocation - this.horizontalScrollOffset;
        final int y = this.blockYLocation - this.getVerticalScrollOffset();
        final int[] trailing = { 0 };
        int offset = this.getOffsetAtPoint(x, y, trailing, true);
        if (offset != -1) {
            offset += trailing[0];
            final int lineIndex = this.content.getLineAtOffset(offset);
            final int lineOffset = this.content.getOffsetAtLine(lineIndex);
            final String lineText = this.content.getLine(lineIndex);
            final int lineLength = lineText.length();
            int newOffset = offset;
            if (next) {
                if (offset < lineOffset + lineLength) {
                    newOffset = this.getWordNext(offset, 4);
                }
            }
            else if (offset > lineOffset) {
                newOffset = this.getWordPrevious(offset, 4);
            }
            offset = ((newOffset != offset) ? newOffset : -1);
        }
        if (offset != -1) {
            this.setBlockSelectionOffset(offset, true);
            this.showCaret();
        }
        else {
            final int width = (next ? this.renderer.averageCharWidth : (-this.renderer.averageCharWidth)) * 6;
            final int maxWidth = Math.max(this.clientAreaWidth - this.rightMargin - this.leftMargin, this.renderer.getWidth());
            x = Math.max(0, Math.min(this.blockXLocation + width, maxWidth)) - this.horizontalScrollOffset;
            this.setBlockSelectionLocation(x, y, true);
            final Rectangle rect = new Rectangle(x, y, 0, 0);
            this.showLocation(rect, true);
        }
    }
    
    void doBlockLineVertical(final boolean up) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffsets[0], false);
        }
        int y = this.blockYLocation - this.getVerticalScrollOffset();
        final int lineIndex = this.getLineIndex(y);
        if (up) {
            if (lineIndex > 0) {
                y = this.getLinePixel(lineIndex - 1);
                this.setBlockSelectionLocation(this.blockXLocation - this.horizontalScrollOffset, y, true);
                if (y < this.topMargin) {
                    this.scrollVertical(y - this.topMargin, true);
                }
            }
        }
        else {
            final int lineCount = this.content.getLineCount();
            if (lineIndex + 1 < lineCount) {
                y = this.getLinePixel(lineIndex + 2) - 1;
                this.setBlockSelectionLocation(this.blockXLocation - this.horizontalScrollOffset, y, true);
                final int bottom = this.clientAreaHeight - this.bottomMargin;
                if (y > bottom) {
                    this.scrollVertical(y - bottom, true);
                }
            }
        }
    }
    
    void doBlockLineHorizontal(final boolean end) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffsets[0], false);
        }
        int x = this.blockXLocation - this.horizontalScrollOffset;
        final int y = this.blockYLocation - this.getVerticalScrollOffset();
        final int lineIndex = this.getLineIndex(y);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final String lineText = this.content.getLine(lineIndex);
        final int lineLength = lineText.length();
        final int[] trailing = { 0 };
        int offset = this.getOffsetAtPoint(x, y, trailing, true);
        if (offset != -1) {
            int newOffset = offset += trailing[0];
            if (end) {
                if (offset < lineOffset + lineLength) {
                    newOffset = lineOffset + lineLength;
                }
            }
            else if (offset > lineOffset) {
                newOffset = lineOffset;
            }
            offset = ((newOffset != offset) ? newOffset : -1);
        }
        else if (!end) {
            offset = lineOffset + lineLength;
        }
        if (offset != -1) {
            this.setBlockSelectionOffset(offset, true);
            this.showCaret();
        }
        else {
            final int maxWidth = Math.max(this.clientAreaWidth - this.rightMargin - this.leftMargin, this.renderer.getWidth());
            x = (end ? maxWidth : 0) - this.horizontalScrollOffset;
            this.setBlockSelectionLocation(x, y, true);
            final Rectangle rect = new Rectangle(x, y, 0, 0);
            this.showLocation(rect, true);
        }
    }
    
    void doBlockSelection(final boolean sendEvent) {
        if (this.caretOffsets[0] > this.selectionAnchors[0]) {
            this.selection[0].x = this.selectionAnchors[0];
            this.selection[0].y = this.caretOffsets[0];
        }
        else {
            this.selection[0].x = this.caretOffsets[0];
            this.selection[0].y = this.selectionAnchors[0];
        }
        this.updateCaretVisibility();
        this.setCaretLocations();
        super.redraw();
        if (sendEvent) {
            this.sendSelectionEvent();
        }
        this.sendAccessibleTextCaretMoved();
    }
    
    void doContent(final char key) {
        if (this.blockSelection && this.blockXLocation != -1) {
            this.insertBlockSelectionText(key, 0);
            return;
        }
        for (int i = this.selection.length - 1; i >= 0; --i) {
            final Point sel = this.selection[i];
            final Event event = new Event();
            event.start = sel.x;
            event.end = sel.y;
            if (key == '\r' || key == '\n') {
                if (!this.isSingleLine()) {
                    event.text = this.getLineDelimiter();
                }
            }
            else if (sel.x == sel.y && this.overwrite && key != '\t') {
                final int lineIndex = this.content.getLineAtOffset(event.end);
                final int lineOffset = this.content.getOffsetAtLine(lineIndex);
                final String line = this.content.getLine(lineIndex);
                if (event.end < lineOffset + line.length()) {
                    final Event event3;
                    final Event event2 = event3 = event;
                    ++event3.end;
                }
                event.text = new String(new char[] { key });
            }
            else {
                event.text = new String(new char[] { key });
            }
            if (event.text != null) {
                if (this.textLimit > 0 && this.content.getCharCount() - (event.end - event.start) >= this.textLimit) {
                    return;
                }
                this.sendKeyEvent(event);
            }
        }
    }
    
    void doContentEnd() {
        if (this.isSingleLine()) {
            this.doLineEnd();
        }
        else {
            final int length = this.content.getCharCount();
            this.setCaretOffsets(new int[] { length }, -1);
            this.showCaret();
        }
    }
    
    void doContentStart() {
        this.setCaretOffsets(new int[] { 0 }, -1);
        this.showCaret();
    }
    
    void doCursorPrevious() {
        if (Arrays.stream(this.selection).anyMatch(p -> p.x != p.y)) {
            this.setCaretOffsets(Arrays.stream(this.selection).mapToInt(sel -> sel.x).toArray(), 1);
            this.showCaret();
        }
        else {
            this.doSelectionCursorPrevious();
        }
    }
    
    void doCursorNext() {
        if (Arrays.stream(this.selection).anyMatch(p -> p.x != p.y)) {
            this.setCaretOffsets(Arrays.stream(this.selection).mapToInt(sel -> sel.y).toArray(), 0);
            this.showCaret();
        }
        else {
            this.doSelectionCursorNext();
        }
    }
    
    void doDelete() {
        final Event event = new Event();
        event.text = "";
        Point sel2 = null;
        if (Arrays.stream(this.selection).anyMatch(sel1 -> sel1.x != sel1.y)) {
            final Point[] selection = this.selection;
            for (int length = selection.length, j = 0; j < length; ++j) {
                sel2 = selection[j];
                event.start = sel2.x;
                event.end = sel2.y;
                this.sendKeyEvent(event);
            }
        }
        else {
            for (int i = this.caretOffsets.length - 1; i >= 0; --i) {
                final int caretOffset = this.caretOffsets[i];
                if (caretOffset < this.content.getCharCount()) {
                    final int line = this.content.getLineAtOffset(caretOffset);
                    final int lineOffset = this.content.getOffsetAtLine(line);
                    final int lineLength = this.content.getLine(line).length();
                    if (caretOffset == lineOffset + lineLength) {
                        event.start = caretOffset;
                        event.end = this.content.getOffsetAtLine(line + 1);
                    }
                    else {
                        event.start = caretOffset;
                        event.end = this.getClusterNext(caretOffset, line);
                    }
                    this.sendKeyEvent(event);
                }
            }
        }
    }
    
    void doDeleteWordNext() {
        if (Arrays.stream(this.selection).anyMatch(sel -> sel.x != sel.y)) {
            this.doDelete();
        }
        else {
            for (int i = this.caretOffsets.length - 1; i >= 0; --i) {
                final int caretOffset = this.caretOffsets[i];
                final Event event = new Event();
                event.text = "";
                event.start = caretOffset;
                event.end = this.getWordNext(caretOffset, 4);
                this.sendKeyEvent(event);
            }
        }
    }
    
    void doDeleteWordPrevious() {
        if (Arrays.stream(this.selection).anyMatch(sel -> sel.x != sel.y)) {
            this.doBackspace();
        }
        else {
            for (int i = this.caretOffsets.length - 1; i >= 0; --i) {
                final int caretOffset = this.caretOffsets[i];
                final Event event = new Event();
                event.text = "";
                event.start = this.getWordPrevious(caretOffset, 4);
                event.end = caretOffset;
                this.sendKeyEvent(event);
            }
        }
    }
    
    void doLineDown(final boolean select) {
        final int[] newCaretOffsets = new int[this.caretOffsets.length];
        final int lineCount = this.content.getLineCount();
        final int[] alignment = { 0 };
        for (int i = 0; i < this.caretOffsets.length; ++i) {
            final int caretOffset = this.caretOffsets[i];
            int caretLine = this.content.getLineAtOffset(caretOffset);
            final int x = (this.caretOffsets.length == 1) ? this.columnX : this.getPointAtOffset(caretOffset).x;
            int y = 0;
            boolean lastLine = false;
            if (this.isWordWrap()) {
                final int lineOffset = this.content.getOffsetAtLine(caretLine);
                final int offsetInLine = caretOffset - lineOffset;
                final TextLayout layout = this.renderer.getTextLayout(caretLine);
                final int lineIndex = this.getVisualLineIndex(layout, offsetInLine);
                final int layoutLineCount = layout.getLineCount();
                if (lineIndex == layoutLineCount - 1) {
                    lastLine = (caretLine == lineCount - 1);
                    ++caretLine;
                }
                else {
                    y = layout.getLineBounds(lineIndex + 1).y;
                    ++y;
                }
                this.renderer.disposeTextLayout(layout);
            }
            else {
                lastLine = (caretLine == lineCount - 1);
                ++caretLine;
            }
            if (lastLine) {
                newCaretOffsets[i] = this.content.getCharCount();
            }
            else {
                newCaretOffsets[i] = this.getOffsetAtPoint(x, y, caretLine, alignment);
            }
        }
        final boolean hitLastLine = this.content.getLineAtOffset(newCaretOffsets[newCaretOffsets.length - 1]) == lineCount - 1;
        this.setCaretOffsets(newCaretOffsets, hitLastLine ? -1 : alignment[0]);
        final int oldColumnX = this.columnX;
        final int oldHScrollOffset = this.horizontalScrollOffset;
        if (select) {
            this.setMouseWordSelectionAnchor();
            this.doSelection(16777220);
        }
        this.showCaret();
        final int hScrollChange = oldHScrollOffset - this.horizontalScrollOffset;
        this.columnX = oldColumnX + hScrollChange;
    }
    
    void doLineEnd() {
        final int[] newOffsets = new int[this.caretOffsets.length];
        for (int i = 0; i < this.caretOffsets.length; ++i) {
            final int caretOffset = this.caretOffsets[i];
            final int caretLine = this.content.getLineAtOffset(caretOffset);
            final int lineOffset = this.content.getOffsetAtLine(caretLine);
            int lineEndOffset;
            if (this.isWordWrap()) {
                final TextLayout layout = this.renderer.getTextLayout(caretLine);
                final int offsetInLine = caretOffset - lineOffset;
                final int lineIndex = this.getVisualLineIndex(layout, offsetInLine);
                final int[] offsets = layout.getLineOffsets();
                lineEndOffset = lineOffset + offsets[lineIndex + 1];
                this.renderer.disposeTextLayout(layout);
            }
            else {
                final int lineLength = this.content.getLine(caretLine).length();
                lineEndOffset = lineOffset + lineLength;
            }
            newOffsets[i] = lineEndOffset;
        }
        this.setCaretOffsets(newOffsets, 0);
        this.showCaret();
    }
    
    void doLineStart() {
        final int[] newCaretOffsets = new int[this.caretOffsets.length];
        for (int i = 0; i < this.caretOffsets.length; ++i) {
            final int caretOffset = this.caretOffsets[i];
            final int caretLine = this.content.getLineAtOffset(caretOffset);
            int lineOffset = this.content.getOffsetAtLine(caretLine);
            if (this.isWordWrap()) {
                final TextLayout layout = this.renderer.getTextLayout(caretLine);
                final int offsetInLine = caretOffset - lineOffset;
                final int lineIndex = this.getVisualLineIndex(layout, offsetInLine);
                final int[] offsets = layout.getLineOffsets();
                lineOffset += offsets[lineIndex];
                this.renderer.disposeTextLayout(layout);
            }
            newCaretOffsets[i] = lineOffset;
        }
        this.setCaretOffsets(newCaretOffsets, 1);
        this.showCaret();
    }
    
    void doLineUp(final boolean select) {
        final int[] newCaretOffsets = new int[this.caretOffsets.length];
        final int[] alignment = { 0 };
        for (int i = 0; i < this.caretOffsets.length; ++i) {
            final int caretOffset = this.caretOffsets[i];
            int caretLine = this.content.getLineAtOffset(caretOffset);
            final int x = (this.caretOffsets.length == 1) ? this.columnX : this.getPointAtOffset(caretOffset).x;
            int y = 0;
            boolean firstLine = false;
            if (this.isWordWrap()) {
                final int lineOffset = this.content.getOffsetAtLine(caretLine);
                final int offsetInLine = caretOffset - lineOffset;
                final TextLayout layout = this.renderer.getTextLayout(caretLine);
                final int lineIndex = this.getVisualLineIndex(layout, offsetInLine);
                if (lineIndex == 0) {
                    firstLine = (caretLine == 0);
                    if (!firstLine) {
                        --caretLine;
                        y = this.renderer.getLineHeight(caretLine) - 1;
                        --y;
                    }
                }
                else {
                    y = layout.getLineBounds(lineIndex - 1).y;
                    ++y;
                }
                this.renderer.disposeTextLayout(layout);
            }
            else {
                firstLine = (caretLine == 0);
                --caretLine;
            }
            if (firstLine) {
                newCaretOffsets[i] = 0;
            }
            else {
                newCaretOffsets[i] = this.getOffsetAtPoint(x, y, caretLine, alignment);
            }
        }
        this.setCaretOffsets(newCaretOffsets, (newCaretOffsets[0] == 0) ? -1 : alignment[0]);
        final int oldColumnX = this.columnX;
        final int oldHScrollOffset = this.horizontalScrollOffset;
        if (select) {
            this.setMouseWordSelectionAnchor();
        }
        this.showCaret();
        if (select) {
            this.doSelection(16777219);
        }
        final int hScrollChange = oldHScrollOffset - this.horizontalScrollOffset;
        this.columnX = oldColumnX + hScrollChange;
    }
    
    void doMouseLinkCursor() {
        final Display display = this.getDisplay();
        Point point = display.getCursorLocation();
        point = display.map(null, this, point);
        this.doMouseLinkCursor(point.x, point.y);
    }
    
    void doMouseLinkCursor(final int x, final int y) {
        final int offset = this.getOffsetAtPoint(x, y, null, true);
        final Display display = this.getDisplay();
        Cursor newCursor = this.cursor;
        if (this.renderer.hasLink(offset)) {
            newCursor = display.getSystemCursor(21);
        }
        else if (this.cursor == null) {
            final int type = this.blockSelection ? 2 : 19;
            newCursor = display.getSystemCursor(type);
        }
        if (newCursor != this.getCursor()) {
            super.setCursor(newCursor);
        }
    }
    
    void doMouseLocationChange(int x, int y, final boolean select) {
        final int line = this.getLineIndex(y);
        this.updateCaretDirection = true;
        if (this.blockSelection) {
            x = Math.max(this.leftMargin, Math.min(x, this.clientAreaWidth - this.rightMargin));
            y = Math.max(this.topMargin, Math.min(y, this.clientAreaHeight - this.bottomMargin));
            if (this.doubleClickEnabled && this.clickCount > 1) {
                final boolean wordSelect = (this.clickCount & 0x1) == 0x0;
                if (wordSelect) {
                    final Point left = this.getPointAtOffset(this.doubleClickSelection.x);
                    final int[] trailing = { 0 };
                    int offset = this.getOffsetAtPoint(x, y, trailing, true);
                    if (offset != -1) {
                        if (x > left.x) {
                            offset = this.getWordNext(offset + trailing[0], 8);
                            this.setBlockSelectionOffset(this.doubleClickSelection.x, offset, true);
                        }
                        else {
                            offset = this.getWordPrevious(offset + trailing[0], 16);
                            this.setBlockSelectionOffset(this.doubleClickSelection.y, offset, true);
                        }
                    }
                    else if (x > left.x) {
                        this.setBlockSelectionLocation(left.x, left.y, x, y, true);
                    }
                    else {
                        final Point right = this.getPointAtOffset(this.doubleClickSelection.y);
                        this.setBlockSelectionLocation(right.x, right.y, x, y, true);
                    }
                }
                else {
                    this.setBlockSelectionLocation(this.blockXLocation, y, true);
                }
                return;
            }
            if (select) {
                if (this.blockXLocation == -1) {
                    this.setBlockSelectionOffset(this.caretOffsets[0], false);
                }
            }
            else {
                this.clearBlockSelection(true, false);
            }
            final int[] trailing2 = { 0 };
            final int offset2 = this.getOffsetAtPoint(x, y, trailing2, true);
            if (offset2 == -1) {
                if (this.isFixedLineHeight() && this.renderer.fixedPitch) {
                    final int avg = this.renderer.averageCharWidth;
                    x = (x + avg / 2 - this.leftMargin + this.horizontalScrollOffset) / avg * avg + this.leftMargin - this.horizontalScrollOffset;
                }
                this.setBlockSelectionLocation(x, y, true);
                return;
            }
            if (select) {
                this.setBlockSelectionOffset(offset2 + trailing2[0], true);
                return;
            }
        }
        if (line < 0 || (this.isSingleLine() && line > 0)) {
            return;
        }
        final int[] alignment = { 0 };
        int newCaretOffset = this.getOffsetAtPoint(x, y, alignment);
        final int newCaretAlignemnt = alignment[0];
        if (this.doubleClickEnabled && this.clickCount > 1) {
            newCaretOffset = this.doMouseWordSelect(x, newCaretOffset, line);
        }
        final int newCaretLine = this.content.getLineAtOffset(newCaretOffset);
        final boolean vchange = (0 <= y && y < this.clientAreaHeight) || newCaretLine == 0 || newCaretLine == this.content.getLineCount() - 1;
        final boolean hchange = (0 <= x && x < this.clientAreaWidth) || this.wordWrap || newCaretLine != this.content.getLineAtOffset(this.caretOffsets[0]);
        if (vchange && hchange && (newCaretOffset != this.caretOffsets[0] || newCaretAlignemnt != this.caretAlignment)) {
            this.setCaretOffsets(new int[] { newCaretOffset }, newCaretAlignemnt);
            if (select) {
                this.doMouseSelection();
            }
            this.showCaret();
        }
        if (!select) {
            this.setCaretOffsets(new int[] { newCaretOffset }, newCaretAlignemnt);
            this.clearSelection(true);
        }
    }
    
    void doMouseSelection() {
        if (this.caretOffsets[0] <= this.selection[0].x || (this.caretOffsets[0] > this.selection[0].x && this.caretOffsets[0] < this.selection[0].y && this.selectionAnchors[0] == this.selection[0].x)) {
            this.doSelection(16777219);
        }
        else {
            this.doSelection(16777220);
        }
    }
    
    int doMouseWordSelect(final int x, int newCaretOffset, final int line) {
        if (newCaretOffset < this.selectionAnchors[0] && this.selectionAnchors[0] == this.selection[0].x) {
            this.selectionAnchors[0] = this.doubleClickSelection.y;
        }
        else if (newCaretOffset > this.selectionAnchors[0] && this.selectionAnchors[0] == this.selection[0].y) {
            this.selectionAnchors[0] = this.doubleClickSelection.x;
        }
        if (0 <= x && x < this.clientAreaWidth) {
            final boolean wordSelect = (this.clickCount & 0x1) == 0x0;
            if (this.caretOffsets[0] == this.selection[0].x) {
                if (wordSelect) {
                    newCaretOffset = this.getWordPrevious(newCaretOffset, 16);
                }
                else {
                    newCaretOffset = this.content.getOffsetAtLine(line);
                }
            }
            else if (wordSelect) {
                newCaretOffset = this.getWordNext(newCaretOffset, 8);
            }
            else {
                int lineEnd = this.content.getCharCount();
                if (line + 1 < this.content.getLineCount()) {
                    lineEnd = this.content.getOffsetAtLine(line + 1);
                }
                newCaretOffset = lineEnd;
            }
        }
        return newCaretOffset;
    }
    
    void doPageDown(final boolean select, int height) {
        if (this.isSingleLine()) {
            return;
        }
        final int oldColumnX = this.columnX;
        final int oldHScrollOffset = this.horizontalScrollOffset;
        if (this.isFixedLineHeight()) {
            final int lineCount = this.content.getLineCount();
            final int caretLine = this.getFirstCaretLine();
            if (caretLine < lineCount - 1) {
                final int lineHeight = this.renderer.getLineHeight();
                final int lines = ((height == -1) ? this.clientAreaHeight : height) / lineHeight;
                int scrollLines = Math.min(lineCount - caretLine - 1, lines);
                scrollLines = Math.max(1, scrollLines);
                final int[] alignment = { 0 };
                final int offset = this.getOffsetAtPoint(this.columnX, this.getLinePixel(caretLine + scrollLines), alignment);
                this.setCaretOffsets(new int[] { offset }, alignment[0]);
                if (select) {
                    this.doSelection(16777220);
                }
                final int verticalMaximum = lineCount * this.getVerticalIncrement();
                final int pageSize = this.clientAreaHeight;
                final int verticalScrollOffset = this.getVerticalScrollOffset();
                int scrollOffset = verticalScrollOffset + scrollLines * this.getVerticalIncrement();
                if (scrollOffset + pageSize > verticalMaximum) {
                    scrollOffset = verticalMaximum - pageSize;
                }
                if (scrollOffset > verticalScrollOffset) {
                    this.scrollVertical(scrollOffset - verticalScrollOffset, true);
                }
            }
        }
        else {
            final int lineCount = this.content.getLineCount();
            if (height == -1) {
                final int lineIndex = this.getPartialBottomIndex();
                final int topY = this.getLinePixel(lineIndex);
                final int lineHeight2 = this.renderer.getLineHeight(lineIndex);
                height = topY;
                if (topY + lineHeight2 <= this.clientAreaHeight) {
                    height += lineHeight2;
                }
                else if (this.isWordWrap()) {
                    final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                    final int y = this.clientAreaHeight - topY;
                    for (int i = 0; i < layout.getLineCount(); ++i) {
                        final Rectangle bounds = layout.getLineBounds(i);
                        if (bounds.contains(bounds.x, y)) {
                            height += bounds.y;
                            break;
                        }
                    }
                    this.renderer.disposeTextLayout(layout);
                }
            }
            else {
                final int lineIndex = this.getLineIndex(height);
                final int topLineY = this.getLinePixel(lineIndex);
                if (this.isWordWrap()) {
                    final TextLayout layout2 = this.renderer.getTextLayout(lineIndex);
                    final int y2 = height - topLineY;
                    for (int j = 0; j < layout2.getLineCount(); ++j) {
                        final Rectangle bounds2 = layout2.getLineBounds(j);
                        if (bounds2.contains(bounds2.x, y2)) {
                            height = topLineY + bounds2.y + bounds2.height;
                            break;
                        }
                    }
                    this.renderer.disposeTextLayout(layout2);
                }
                else {
                    height = topLineY + this.renderer.getLineHeight(lineIndex);
                }
            }
            int caretHeight = height;
            if (this.isWordWrap()) {
                for (final int caretOffset : this.caretOffsets) {
                    final int caretLine2 = this.content.getLineAtOffset(caretOffset);
                    final TextLayout layout3 = this.renderer.getTextLayout(caretLine2);
                    final int offsetInLine = caretOffset - this.content.getOffsetAtLine(caretLine2);
                    final int lineIndex2 = this.getVisualLineIndex(layout3, offsetInLine);
                    caretHeight += layout3.getLineBounds(lineIndex2).y;
                    this.renderer.disposeTextLayout(layout3);
                }
            }
            int lineIndex3;
            for (lineIndex3 = this.getFirstCaretLine(), int lineHeight2 = this.renderer.getLineHeight(lineIndex3); caretHeight - lineHeight2 >= 0 && lineIndex3 < lineCount - 1; caretHeight -= lineHeight2, lineHeight2 = this.renderer.getLineHeight(++lineIndex3)) {}
            final int[] alignment2 = { 0 };
            final int offset2 = this.getOffsetAtPoint(this.columnX, caretHeight, lineIndex3, alignment2);
            this.setCaretOffsets(new int[] { offset2 }, alignment2[0]);
            if (select) {
                this.doSelection(16777220);
            }
            height = this.getAvailableHeightBellow(height);
            this.scrollVertical(height, true);
            if (height == 0) {
                this.setCaretLocations();
            }
        }
        this.showCaret();
        final int hScrollChange = oldHScrollOffset - this.horizontalScrollOffset;
        this.columnX = oldColumnX + hScrollChange;
    }
    
    void doPageEnd() {
        if (this.isSingleLine()) {
            this.doLineEnd();
        }
        else if (this.caretOffsets.length == 1) {
            int bottomOffset;
            if (this.isWordWrap()) {
                final int lineIndex = this.getPartialBottomIndex();
                final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                final int y = this.clientAreaHeight - this.bottomMargin - this.getLinePixel(lineIndex);
                int index;
                for (index = layout.getLineCount() - 1; index >= 0; --index) {
                    final Rectangle bounds = layout.getLineBounds(index);
                    if (y >= bounds.y + bounds.height) {
                        break;
                    }
                }
                if (index == -1 && lineIndex > 0) {
                    bottomOffset = this.content.getOffsetAtLine(lineIndex - 1) + this.content.getLine(lineIndex - 1).length();
                }
                else {
                    bottomOffset = this.content.getOffsetAtLine(lineIndex) + Math.max(0, layout.getLineOffsets()[index + 1] - 1);
                }
                this.renderer.disposeTextLayout(layout);
            }
            else {
                final int lineIndex = this.getBottomIndex();
                bottomOffset = this.content.getOffsetAtLine(lineIndex) + this.content.getLine(lineIndex).length();
            }
            if (this.caretOffsets[0] < bottomOffset) {
                this.setCaretOffsets(new int[] { bottomOffset }, 1);
                this.showCaret();
            }
        }
    }
    
    void doPageStart() {
        int topOffset;
        if (this.isWordWrap()) {
            int lineIndex;
            int y;
            if (this.topIndexY > 0) {
                lineIndex = this.topIndex - 1;
                y = this.renderer.getLineHeight(lineIndex) - this.topIndexY;
            }
            else {
                lineIndex = this.topIndex;
                y = -this.topIndexY;
            }
            final TextLayout layout = this.renderer.getTextLayout(lineIndex);
            int index;
            int lineCount;
            for (index = 0, lineCount = layout.getLineCount(); index < lineCount; ++index) {
                final Rectangle bounds = layout.getLineBounds(index);
                if (y <= bounds.y) {
                    break;
                }
            }
            if (index == lineCount) {
                topOffset = this.content.getOffsetAtLine(lineIndex + 1);
            }
            else {
                topOffset = this.content.getOffsetAtLine(lineIndex) + layout.getLineOffsets()[index];
            }
            this.renderer.disposeTextLayout(layout);
        }
        else {
            topOffset = this.content.getOffsetAtLine(this.topIndex);
        }
        if (this.caretOffsets[0] > topOffset) {
            this.setCaretOffsets(new int[] { topOffset }, 1);
            this.showCaret();
        }
    }
    
    void doPageUp(final boolean select, int height) {
        if (this.isSingleLine()) {
            return;
        }
        final int oldHScrollOffset = this.horizontalScrollOffset;
        final int oldColumnX = this.columnX;
        if (this.isFixedLineHeight()) {
            int caretLine = this.getFirstCaretLine();
            if (caretLine > 0) {
                final int lineHeight = this.renderer.getLineHeight();
                final int lines = ((height == -1) ? this.clientAreaHeight : height) / lineHeight;
                final int scrollLines = Math.max(1, Math.min(caretLine, lines));
                caretLine -= scrollLines;
                final int[] alignment = { 0 };
                final int offset = this.getOffsetAtPoint(this.columnX, this.getLinePixel(caretLine), alignment);
                this.setCaretOffsets(new int[] { offset }, alignment[0]);
                if (select) {
                    this.doSelection(16777219);
                }
                final int verticalScrollOffset = this.getVerticalScrollOffset();
                final int scrollOffset = Math.max(0, verticalScrollOffset - scrollLines * this.getVerticalIncrement());
                if (scrollOffset < verticalScrollOffset) {
                    this.scrollVertical(scrollOffset - verticalScrollOffset, true);
                }
            }
        }
        else {
            if (height == -1) {
                if (this.topIndexY == 0) {
                    height = this.clientAreaHeight;
                }
                else {
                    int lineIndex;
                    int lineHeight2;
                    int y;
                    if (this.topIndex > 0) {
                        lineIndex = this.topIndex - 1;
                        lineHeight2 = this.renderer.getLineHeight(lineIndex);
                        height = this.clientAreaHeight - this.topIndexY;
                        y = lineHeight2 - this.topIndexY;
                    }
                    else {
                        lineIndex = this.topIndex;
                        lineHeight2 = this.renderer.getLineHeight(lineIndex);
                        height = this.clientAreaHeight - (lineHeight2 + this.topIndexY);
                        y = -this.topIndexY;
                    }
                    if (this.isWordWrap()) {
                        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                        for (int i = 0; i < layout.getLineCount(); ++i) {
                            final Rectangle bounds = layout.getLineBounds(i);
                            if (bounds.contains(bounds.x, y)) {
                                height += lineHeight2 - (bounds.y + bounds.height);
                                break;
                            }
                        }
                        this.renderer.disposeTextLayout(layout);
                    }
                }
            }
            else {
                final int lineIndex = this.getLineIndex(this.clientAreaHeight - height);
                final int topLineY = this.getLinePixel(lineIndex);
                if (this.isWordWrap()) {
                    final TextLayout layout2 = this.renderer.getTextLayout(lineIndex);
                    final int y2 = topLineY;
                    for (int j = 0; j < layout2.getLineCount(); ++j) {
                        final Rectangle bounds2 = layout2.getLineBounds(j);
                        if (bounds2.contains(bounds2.x, y2)) {
                            height = this.clientAreaHeight - (topLineY + bounds2.y);
                            break;
                        }
                    }
                    this.renderer.disposeTextLayout(layout2);
                }
                else {
                    height = this.clientAreaHeight - topLineY;
                }
            }
            int caretHeight = height;
            if (this.isWordWrap()) {
                for (final int caretOffset : this.caretOffsets) {
                    final int caretLine2 = this.content.getLineAtOffset(caretOffset);
                    final TextLayout layout3 = this.renderer.getTextLayout(caretLine2);
                    final int offsetInLine = caretOffset - this.content.getOffsetAtLine(caretLine2);
                    final int lineIndex2 = this.getVisualLineIndex(layout3, offsetInLine);
                    caretHeight += layout3.getBounds().height - layout3.getLineBounds(lineIndex2).y;
                    this.renderer.disposeTextLayout(layout3);
                }
            }
            int lineIndex3 = this.getFirstCaretLine();
            for (int lineHeight3 = this.renderer.getLineHeight(lineIndex3); caretHeight - lineHeight3 >= 0 && lineIndex3 > 0; caretHeight -= lineHeight3, lineHeight3 = this.renderer.getLineHeight(--lineIndex3)) {}
            final int lineHeight4 = this.renderer.getLineHeight(lineIndex3);
            final int[] alignment2 = { 0 };
            final int offset2 = this.getOffsetAtPoint(this.columnX, lineHeight4 - caretHeight, lineIndex3, alignment2);
            this.setCaretOffsets(new int[] { offset2 }, alignment2[0]);
            if (select) {
                this.doSelection(16777219);
            }
            height = this.getAvailableHeightAbove(height);
            this.scrollVertical(-height, true);
            if (height == 0) {
                this.setCaretLocations();
            }
        }
        this.showCaret();
        final int hScrollChange = oldHScrollOffset - this.horizontalScrollOffset;
        this.columnX = oldColumnX + hScrollChange;
    }
    
    void doSelection(final int direction) {
        if (this.caretOffsets.length != this.selection.length) {
            return;
        }
        if (this.selectionAnchors.length != this.selection.length) {
            Arrays.fill(this.selectionAnchors = new int[this.selection.length], -1);
        }
        boolean selectionChanged = false;
        Point p2 = null;
        final Point[] newSelection = Arrays.stream(this.selection).map(p1 -> new Point(p1.x, p1.y)).toArray(Point[]::new);
        final boolean[] caretAtBeginning = new boolean[newSelection.length];
        for (int i = 0; i < this.caretOffsets.length; ++i) {
            final int caretOffset = this.caretOffsets[i];
            final Point currentSelection = newSelection[i];
            int selectionAnchor = this.selectionAnchors[i];
            if (selectionAnchor == -1) {
                final int[] selectionAnchors = this.selectionAnchors;
                final int n = i;
                final int x = currentSelection.x;
                selectionAnchors[n] = x;
                selectionAnchor = x;
            }
            int redrawStart = -1;
            int redrawEnd = -1;
            if (direction == 16777219) {
                if (caretOffset < currentSelection.x) {
                    caretAtBeginning[i] = true;
                    redrawEnd = currentSelection.x;
                    final Point point = currentSelection;
                    final int x2 = caretOffset;
                    point.x = x2;
                    redrawStart = x2;
                    if (currentSelection.y != selectionAnchor) {
                        redrawEnd = currentSelection.y;
                        currentSelection.y = selectionAnchor;
                    }
                }
                else if (selectionAnchor == currentSelection.x && caretOffset < currentSelection.y) {
                    redrawEnd = currentSelection.y;
                    final Point point2 = currentSelection;
                    final int y = caretOffset;
                    point2.y = y;
                    redrawStart = y;
                }
            }
            else if (caretOffset > currentSelection.y) {
                redrawStart = currentSelection.y;
                final Point point3 = currentSelection;
                final int y2 = caretOffset;
                point3.y = y2;
                redrawEnd = y2;
                if (currentSelection.x != selectionAnchor) {
                    redrawStart = currentSelection.x;
                    currentSelection.x = selectionAnchor;
                }
            }
            else if (selectionAnchor == currentSelection.y && caretOffset > currentSelection.x) {
                caretAtBeginning[i] = true;
                redrawStart = currentSelection.x;
                final Point point4 = currentSelection;
                final int x3 = caretOffset;
                point4.x = x3;
                redrawEnd = x3;
            }
            if (redrawStart != -1 && redrawEnd != -1) {
                this.internalRedrawRange(redrawStart, redrawEnd - redrawStart);
                selectionChanged = true;
            }
        }
        if (selectionChanged) {
            final int[] regions = new int[newSelection.length * 2];
            for (int j = 0; j < newSelection.length; ++j) {
                p2 = newSelection[j];
                if (caretAtBeginning[j]) {
                    regions[2 * j] = p2.y;
                    regions[2 * j + 1] = p2.x - p2.y;
                }
                else {
                    regions[2 * j] = p2.x;
                    regions[2 * j + 1] = p2.y - p2.x;
                }
            }
            this.setSelection(regions, false, this.blockSelection);
            this.sendSelectionEvent();
        }
        this.sendAccessibleTextCaretMoved();
    }
    
    void doSelectionCursorNext() {
        final int[] newCarets = Arrays.copyOf(this.caretOffsets, this.caretOffsets.length);
        int newAlignment = Integer.MIN_VALUE;
        for (int i = 0; i < this.caretOffsets.length; ++i) {
            final int caretOffset = this.caretOffsets[i];
            int caretLine = this.content.getLineAtOffset(caretOffset);
            final int lineOffset = this.content.getOffsetAtLine(caretLine);
            int offsetInLine = caretOffset - lineOffset;
            if (offsetInLine < this.content.getLine(caretLine).length()) {
                final TextLayout layout = this.renderer.getTextLayout(caretLine);
                offsetInLine = layout.getNextOffset(offsetInLine, 2);
                final int lineStart = layout.getLineOffsets()[layout.getLineIndex(offsetInLine)];
                this.renderer.disposeTextLayout(layout);
                final int offset = offsetInLine + lineOffset;
                newAlignment = ((offsetInLine == lineStart) ? 1 : 0);
                newCarets[i] = offset;
            }
            else if (caretLine < this.content.getLineCount() - 1 && !this.isSingleLine()) {
                ++caretLine;
                final int offset2 = this.content.getOffsetAtLine(caretLine);
                newAlignment = 0;
                newCarets[i] = offset2;
            }
        }
        if (newAlignment > Integer.MIN_VALUE) {
            this.setCaretOffsets(newCarets, newAlignment);
            this.showCaret();
        }
    }
    
    void doSelectionCursorPrevious() {
        final int[] newCarets = Arrays.copyOf(this.caretOffsets, this.caretOffsets.length);
        for (int i = 0; i < this.caretOffsets.length; ++i) {
            final int caretOffset = this.caretOffsets[i];
            int caretLine = this.content.getLineAtOffset(caretOffset);
            int lineOffset = this.content.getOffsetAtLine(caretLine);
            final int offsetInLine = caretOffset - lineOffset;
            if (offsetInLine > 0) {
                newCarets[i] = this.getClusterPrevious(caretOffset, caretLine);
            }
            else if (caretLine > 0) {
                --caretLine;
                lineOffset = this.content.getOffsetAtLine(caretLine);
                newCarets[i] = lineOffset + this.content.getLine(caretLine).length();
            }
        }
        if (!Arrays.equals(this.caretOffsets, newCarets)) {
            this.setCaretOffsets(newCarets, 1);
            this.showCaret();
        }
    }
    
    void doSelectionLineDown() {
        final int x = this.getPointAtOffset(this.caretOffsets[0]).x;
        this.columnX = x;
        final int oldColumnX = x;
        this.doLineDown(true);
        this.columnX = oldColumnX;
    }
    
    void doSelectionLineUp() {
        final int x = this.getPointAtOffset(this.caretOffsets[0]).x;
        this.columnX = x;
        final int oldColumnX = x;
        this.doLineUp(true);
        this.columnX = oldColumnX;
    }
    
    void doSelectionPageDown(final int pixels) {
        final int x = this.getPointAtOffset(this.caretOffsets[0]).x;
        this.columnX = x;
        final int oldColumnX = x;
        this.doPageDown(true, pixels);
        this.columnX = oldColumnX;
    }
    
    void doSelectionPageUp(final int pixels) {
        if (this.caretOffsets.length > 1) {
            return;
        }
        final int x = this.getPointAtOffset(this.caretOffsets[0]).x;
        this.columnX = x;
        final int oldColumnX = x;
        this.doPageUp(true, pixels);
        this.columnX = oldColumnX;
    }
    
    void doSelectionWordNext() {
        final int[] offsets = Arrays.stream(this.caretOffsets).map(offset -> this.getWordNext(offset, 4)).toArray();
        if (!this.isSingleLine()) {
            this.setCaretOffsets(offsets, 1);
            this.showCaret();
        }
        else {
            final int[] linesForCurrentCarets = Arrays.stream(this.caretOffsets).map(offset -> this.content.getLineAtOffset(offset)).toArray();
            final int[] linesForNewCarets = Arrays.stream(offsets).map(offset -> this.content.getLineAtOffset(offset)).toArray();
            if (Arrays.equals(linesForCurrentCarets, linesForNewCarets)) {
                this.setCaretOffsets(offsets, 1);
                this.showCaret();
            }
        }
    }
    
    void doSelectionWordPrevious() {
        this.setCaretOffsets(Arrays.stream(this.caretOffsets).map(offset -> this.getWordPrevious(offset, 4)).toArray(), 1);
        this.showCaret();
    }
    
    void doVisualPrevious() {
        this.setCaretOffsets(Arrays.stream(this.caretOffsets).map(offset -> this.getClusterPrevious(offset, this.content.getLineAtOffset(offset))).toArray(), -1);
        this.showCaret();
    }
    
    void doVisualNext() {
        this.setCaretOffsets(Arrays.stream(this.caretOffsets).map(offset -> this.getClusterNext(offset, this.content.getLineAtOffset(offset))).toArray(), -1);
        this.showCaret();
    }
    
    void doWordNext() {
        if (Arrays.stream(this.selection).anyMatch(p -> p.x != p.y)) {
            this.setCaretOffsets(Arrays.stream(this.selection).mapToInt(sel -> sel.y).toArray(), -1);
            this.showCaret();
        }
        else {
            this.doSelectionWordNext();
        }
    }
    
    void doWordPrevious() {
        if (Arrays.stream(this.selection).anyMatch(p -> p.x != p.y)) {
            this.setCaretOffsets(Arrays.stream(this.selection).mapToInt(sel -> sel.x).toArray(), -1);
            this.showCaret();
        }
        else {
            this.doSelectionWordPrevious();
        }
    }
    
    void endAutoScroll() {
        this.autoScrollDirection = 0;
    }
    
    @Override
    public Color getBackground() {
        this.checkWidget();
        if (this.background == null) {
            return this.getDisplay().getSystemColor(25);
        }
        return this.background;
    }
    
    public int getBaseline() {
        this.checkWidget();
        return this.renderer.getBaseline();
    }
    
    public int getBaseline(final int offset) {
        this.checkWidget();
        if (0 > offset || offset > this.content.getCharCount()) {
            SWT.error(6);
        }
        if (this.isFixedLineHeight()) {
            return this.renderer.getBaseline();
        }
        final int lineIndex = this.content.getLineAtOffset(offset);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
        final int lineInParagraph = layout.getLineIndex(Math.min(offset - lineOffset, layout.getText().length()));
        final FontMetrics metrics = layout.getLineMetrics(lineInParagraph);
        this.renderer.disposeTextLayout(layout);
        return metrics.getAscent() + metrics.getLeading();
    }
    
    @Deprecated
    public boolean getBidiColoring() {
        this.checkWidget();
        return this.bidiColoring;
    }
    
    public boolean getBlockSelection() {
        this.checkWidget();
        return this.blockSelection;
    }
    
    Rectangle getBlockSelectionPosition() {
        int firstLine = this.getLineIndex(this.blockYAnchor - this.getVerticalScrollOffset());
        int lastLine = this.getLineIndex(this.blockYLocation - this.getVerticalScrollOffset());
        if (firstLine > lastLine) {
            final int temp = firstLine;
            firstLine = lastLine;
            lastLine = temp;
        }
        int left = this.blockXAnchor;
        int right = this.blockXLocation;
        if (left > right) {
            left = this.blockXLocation;
            right = this.blockXAnchor;
        }
        return new Rectangle(left - this.horizontalScrollOffset, firstLine, right - this.horizontalScrollOffset, lastLine);
    }
    
    public Rectangle getBlockSelectionBounds() {
        Rectangle rect;
        if (this.blockSelection && this.blockXLocation != -1) {
            rect = this.getBlockSelectionRectangle();
        }
        else {
            final Point startPoint = this.getPointAtOffset(this.selection[0].x);
            final Point endPoint = this.getPointAtOffset(this.selection[0].y);
            final int height = this.getLineHeight(this.selection[0].y);
            rect = new Rectangle(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y + height - startPoint.y);
            if (this.selection[0].x == this.selection[0].y) {
                rect.width = this.getCaretWidth();
            }
        }
        final Rectangle rectangle3;
        final Rectangle rectangle = rectangle3 = rect;
        rectangle3.x += this.horizontalScrollOffset;
        final Rectangle rectangle4;
        final Rectangle rectangle2 = rectangle4 = rect;
        rectangle4.y += this.getVerticalScrollOffset();
        return rect;
    }
    
    Rectangle getBlockSelectionRectangle() {
        final Rectangle rect = this.getBlockSelectionPosition();
        rect.y = this.getLinePixel(rect.y);
        final Rectangle rectangle = rect;
        rectangle.width -= rect.x;
        rect.height = this.getLinePixel(rect.height + 1) - rect.y;
        return rect;
    }
    
    String getBlockSelectionText(final String delimiter) {
        final Rectangle rect = this.getBlockSelectionPosition();
        final int firstLine = rect.y;
        final int lastLine = rect.height;
        final int left = rect.x;
        final int right = rect.width;
        final StringBuilder buffer = new StringBuilder();
        for (int lineIndex = firstLine; lineIndex <= lastLine; ++lineIndex) {
            int start = this.getOffsetAtPoint(left, 0, lineIndex, null);
            int end = this.getOffsetAtPoint(right, 0, lineIndex, null);
            if (start > end) {
                final int temp = start;
                start = end;
                end = temp;
            }
            final String text = this.content.getTextRange(start, end - start);
            buffer.append(text);
            if (lineIndex < lastLine) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }
    
    int getBottomIndex() {
        int bottomIndex;
        if (this.isFixedLineHeight()) {
            int lineCount = 1;
            final int lineHeight = this.renderer.getLineHeight();
            if (lineHeight != 0) {
                final int partialTopLineHeight = this.topIndex * lineHeight - this.getVerticalScrollOffset();
                lineCount = (this.clientAreaHeight - partialTopLineHeight) / lineHeight;
            }
            bottomIndex = Math.min(this.content.getLineCount() - 1, this.topIndex + Math.max(0, lineCount - 1));
        }
        else {
            final int clientAreaHeight = this.clientAreaHeight - this.bottomMargin;
            bottomIndex = this.getLineIndex(clientAreaHeight);
            if (bottomIndex > 0) {
                final int linePixel = this.getLinePixel(bottomIndex);
                final int lineHeight2 = this.renderer.getLineHeight(bottomIndex);
                if (linePixel + lineHeight2 > clientAreaHeight && this.getLinePixel(bottomIndex - 1) >= this.topMargin) {
                    --bottomIndex;
                }
            }
        }
        return bottomIndex;
    }
    
    public int getBottomMargin() {
        this.checkWidget();
        return this.bottomMargin;
    }
    
    Rectangle getBoundsAtOffset(final int offset) {
        final int lineIndex = this.content.getLineAtOffset(offset);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final String line = this.content.getLine(lineIndex);
        Rectangle bounds;
        if (line.length() != 0) {
            final TextLayout layout = this.renderer.getTextLayout(lineIndex);
            int offsetInLine = Math.min(layout.getText().length(), Math.max(0, offset - lineOffset));
            bounds = layout.getBounds(offsetInLine, offsetInLine);
            if (this.getListeners(3007).length > 0 && this.caretAlignment == 0 && offsetInLine != 0) {
                offsetInLine = layout.getPreviousOffset(offsetInLine, 2);
                final Point point = layout.getLocation(offsetInLine, true);
                bounds = new Rectangle(point.x, point.y, 0, bounds.height);
            }
            this.renderer.disposeTextLayout(layout);
        }
        else {
            bounds = new Rectangle(0, 0, 0, this.renderer.getLineHeight());
        }
        if (Arrays.binarySearch(this.caretOffsets, offset) >= 0 && !this.isWordWrap()) {
            final int lineEnd = lineOffset + line.length();
            if (offset == lineEnd) {
                final Rectangle rectangle4;
                final Rectangle rectangle = rectangle4 = bounds;
                rectangle4.width += this.getCaretWidth();
            }
        }
        final Rectangle rectangle5;
        final Rectangle rectangle2 = rectangle5 = bounds;
        rectangle5.x += this.leftMargin - this.horizontalScrollOffset;
        final Rectangle rectangle6;
        final Rectangle rectangle3 = rectangle6 = bounds;
        rectangle6.y += this.getLinePixel(lineIndex);
        return bounds;
    }
    
    public int getCaretOffset() {
        this.checkWidget();
        return this.caretOffsets[0];
    }
    
    int getCaretWidth() {
        final Caret caret = this.getCaret();
        if (caret == null) {
            return 0;
        }
        return caret.getSize().x;
    }
    
    Object getClipboardContent(final int clipboardType) {
        final TextTransfer plainTextTransfer = TextTransfer.getInstance();
        return this.clipboard.getContents(plainTextTransfer, clipboardType);
    }
    
    int getClusterNext(int offset, final int lineIndex) {
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
        offset -= lineOffset;
        offset = layout.getNextOffset(offset, 2);
        offset += lineOffset;
        this.renderer.disposeTextLayout(layout);
        return offset;
    }
    
    int getClusterPrevious(int offset, final int lineIndex) {
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
        offset -= lineOffset;
        offset = layout.getPreviousOffset(offset, 2);
        offset += lineOffset;
        this.renderer.disposeTextLayout(layout);
        return offset;
    }
    
    public StyledTextContent getContent() {
        this.checkWidget();
        return this.content;
    }
    
    @Override
    public boolean getDragDetect() {
        this.checkWidget();
        return this.dragDetect;
    }
    
    public boolean getDoubleClickEnabled() {
        this.checkWidget();
        return this.doubleClickEnabled;
    }
    
    public boolean getEditable() {
        this.checkWidget();
        return this.editable;
    }
    
    @Override
    public Color getForeground() {
        this.checkWidget();
        if (this.foreground == null) {
            return this.getDisplay().getSystemColor(24);
        }
        return this.foreground;
    }
    
    int getHorizontalIncrement() {
        return this.renderer.averageCharWidth;
    }
    
    public int getHorizontalIndex() {
        this.checkWidget();
        return this.horizontalScrollOffset / this.getHorizontalIncrement();
    }
    
    public int getHorizontalPixel() {
        this.checkWidget();
        return this.horizontalScrollOffset;
    }
    
    public int getIndent() {
        this.checkWidget();
        return this.indent;
    }
    
    public boolean getJustify() {
        this.checkWidget();
        return this.justify;
    }
    
    public int getKeyBinding(final int key) {
        this.checkWidget();
        final Integer action = this.keyActionMap.get(key);
        return (action == null) ? 0 : action;
    }
    
    public int getCharCount() {
        this.checkWidget();
        return this.content.getCharCount();
    }
    
    public String getLine(final int lineIndex) {
        this.checkWidget();
        if (lineIndex < 0 || (lineIndex > 0 && lineIndex >= this.content.getLineCount())) {
            SWT.error(6);
        }
        return this.content.getLine(lineIndex);
    }
    
    public int getLineAlignment(final int index) {
        this.checkWidget();
        if (index < 0 || index > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.renderer.getLineAlignment(index, this.alignment);
    }
    
    public int getLineAtOffset(final int offset) {
        this.checkWidget();
        if (offset < 0 || offset > this.getCharCount()) {
            SWT.error(6);
        }
        return this.content.getLineAtOffset(offset);
    }
    
    public Color getLineBackground(final int index) {
        this.checkWidget();
        if (index < 0 || index > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3001) ? null : this.renderer.getLineBackground(index, null);
    }
    
    public Bullet getLineBullet(final int index) {
        this.checkWidget();
        if (index < 0 || index > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3002) ? null : this.renderer.getLineBullet(index, null);
    }
    
    StyledTextEvent getLineBackgroundData(final int lineOffset, final String line) {
        return this.sendLineEvent(3001, lineOffset, line);
    }
    
    public int getLineCount() {
        this.checkWidget();
        return this.content.getLineCount();
    }
    
    int getLineCountWhole() {
        if (this.isFixedLineHeight()) {
            final int lineHeight = this.renderer.getLineHeight();
            return (lineHeight != 0) ? (this.clientAreaHeight / lineHeight) : 1;
        }
        return this.getBottomIndex() - this.topIndex + 1;
    }
    
    public String getLineDelimiter() {
        this.checkWidget();
        return this.content.getLineDelimiter();
    }
    
    public int getLineHeight() {
        this.checkWidget();
        return this.renderer.getLineHeight();
    }
    
    public int getLineHeight(final int offset) {
        this.checkWidget();
        if (0 > offset || offset > this.content.getCharCount()) {
            SWT.error(6);
        }
        if (this.isFixedLineHeight()) {
            return this.renderer.getLineHeight();
        }
        final int lineIndex = this.content.getLineAtOffset(offset);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
        final int lineInParagraph = layout.getLineIndex(Math.min(offset - lineOffset, layout.getText().length()));
        final int height = layout.getLineBounds(lineInParagraph).height;
        this.renderer.disposeTextLayout(layout);
        return height;
    }
    
    public int getLineIndent(final int index) {
        this.checkWidget();
        if (index < 0 || index > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3002) ? 0 : this.renderer.getLineIndent(index, this.indent);
    }
    
    public int getLineVerticalIndent(final int index) {
        this.checkWidget();
        if (index < 0 || index >= this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3002) ? 0 : this.renderer.getLineVerticalIndent(index);
    }
    
    public boolean getLineJustify(final int index) {
        this.checkWidget();
        if (index < 0 || index > this.content.getLineCount()) {
            SWT.error(5);
        }
        return !this.isListening(3002) && this.renderer.getLineJustify(index, this.justify);
    }
    
    public int getLineSpacing() {
        this.checkWidget();
        return this.lineSpacing;
    }
    
    StyledTextEvent getLineStyleData(final int lineOffset, final String line) {
        return this.sendLineEvent(3002, lineOffset, line);
    }
    
    public int getLinePixel(int lineIndex) {
        this.checkWidget();
        final int lineCount = this.content.getLineCount();
        lineIndex = Math.max(0, Math.min(lineCount, lineIndex));
        if (this.isFixedLineHeight()) {
            final int lineHeight = this.renderer.getLineHeight();
            return lineIndex * lineHeight - this.getVerticalScrollOffset() + this.topMargin;
        }
        if (lineIndex == this.topIndex) {
            return this.topIndexY + this.topMargin;
        }
        int height = this.topIndexY;
        if (lineIndex > this.topIndex) {
            for (int i = this.topIndex; i < lineIndex; ++i) {
                height += this.renderer.getLineHeight(i);
            }
        }
        else {
            for (int i = this.topIndex - 1; i >= lineIndex; --i) {
                height -= this.renderer.getLineHeight(i);
            }
        }
        return height + this.topMargin;
    }
    
    public int getLineIndex(int y) {
        this.checkWidget();
        y -= this.topMargin;
        if (this.isFixedLineHeight()) {
            final int lineHeight = this.renderer.getLineHeight();
            int lineIndex = (y + this.getVerticalScrollOffset()) / lineHeight;
            final int lineCount = this.content.getLineCount();
            lineIndex = Math.max(0, Math.min(lineCount - 1, lineIndex));
            return lineIndex;
        }
        if (y == this.topIndexY) {
            return this.topIndex;
        }
        int line = this.topIndex;
        if (y < this.topIndexY) {
            while (y < this.topIndexY && line > 0) {
                y += this.renderer.getLineHeight(--line);
            }
        }
        else {
            for (int lineCount2 = this.content.getLineCount(), lineHeight2 = this.renderer.getLineHeight(line); y - lineHeight2 >= this.topIndexY && line < lineCount2 - 1; y -= lineHeight2, lineHeight2 = this.renderer.getLineHeight(++line)) {}
        }
        return line;
    }
    
    public int[] getLineTabStops(final int index) {
        this.checkWidget();
        if (index < 0 || index > this.content.getLineCount()) {
            SWT.error(5);
        }
        if (this.isListening(3002)) {
            return null;
        }
        int[] tabs = this.renderer.getLineTabStops(index, null);
        if (tabs == null) {
            tabs = this.tabs;
        }
        if (tabs == null) {
            return new int[] { this.renderer.tabWidth };
        }
        final int[] result = new int[tabs.length];
        System.arraycopy(tabs, 0, result, 0, tabs.length);
        return result;
    }
    
    public int getLineWrapIndent(final int index) {
        this.checkWidget();
        if (index < 0 || index > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3002) ? 0 : this.renderer.getLineWrapIndent(index, this.wrapIndent);
    }
    
    public int getLeftMargin() {
        this.checkWidget();
        return this.leftMargin - this.alignmentMargin;
    }
    
    public Point getLocationAtOffset(final int offset) {
        this.checkWidget();
        if (offset < 0 || offset > this.getCharCount()) {
            SWT.error(6);
        }
        return this.getPointAtOffset(offset);
    }
    
    public boolean getMouseNavigatorEnabled() {
        this.checkWidget();
        return this.mouseNavigator != null;
    }
    
    public int getOffsetAtLine(final int lineIndex) {
        this.checkWidget();
        if (lineIndex < 0 || (lineIndex > 0 && lineIndex >= this.content.getLineCount())) {
            SWT.error(6);
        }
        return this.content.getOffsetAtLine(lineIndex);
    }
    
    @Deprecated
    public int getOffsetAtLocation(final Point point) {
        this.checkWidget();
        if (point == null) {
            SWT.error(4);
        }
        final int[] trailing = { 0 };
        final int offset = this.getOffsetAtPoint(point.x, point.y, trailing, true);
        if (offset == -1) {
            SWT.error(5);
        }
        return offset + trailing[0];
    }
    
    public int getOffsetAtPoint(final Point point) {
        this.checkWidget();
        if (point == null) {
            SWT.error(4);
        }
        final int[] trailing = { 0 };
        final int offset = this.getOffsetAtPoint(point.x, point.y, trailing, true);
        return (offset != -1) ? (offset + trailing[0]) : -1;
    }
    
    int getOffsetAtPoint(final int x, int y, final int[] alignment) {
        final int lineIndex = this.getLineIndex(y);
        y -= this.getLinePixel(lineIndex);
        return this.getOffsetAtPoint(x, y, lineIndex, alignment);
    }
    
    int getOffsetAtPoint(int x, final int y, final int lineIndex, final int[] alignment) {
        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
        x += this.horizontalScrollOffset - this.leftMargin;
        final int[] trailing = { 0 };
        int offsetInLine = layout.getOffset(x, y, trailing);
        if (alignment != null) {
            alignment[0] = 1;
        }
        if (trailing[0] != 0) {
            final int lineInParagraph = layout.getLineIndex(offsetInLine + trailing[0]);
            final int lineStart = layout.getLineOffsets()[lineInParagraph];
            if (offsetInLine + trailing[0] == lineStart) {
                offsetInLine += trailing[0];
                if (alignment != null) {
                    alignment[0] = 0;
                }
            }
            else {
                final String line = this.content.getLine(lineIndex);
                int level = 0;
                if (alignment != null) {
                    int offset;
                    for (offset = offsetInLine; offset > 0 && Character.isDigit(line.charAt(offset)); --offset) {}
                    if (offset == 0 && Character.isDigit(line.charAt(offset))) {
                        level = (this.isMirrored() ? 1 : 0);
                    }
                    else {
                        level = (layout.getLevel(offset) & 0x1);
                    }
                }
                offsetInLine += trailing[0];
                if (alignment != null) {
                    final int trailingLevel = layout.getLevel(offsetInLine) & 0x1;
                    if (level != trailingLevel) {
                        alignment[0] = 0;
                    }
                    else {
                        alignment[0] = 1;
                    }
                }
            }
        }
        this.renderer.disposeTextLayout(layout);
        return offsetInLine + this.content.getOffsetAtLine(lineIndex);
    }
    
    int getOffsetAtPoint(int x, int y, final int[] trailing, final boolean inTextOnly) {
        if ((inTextOnly && y + this.getVerticalScrollOffset() < 0) || x + this.horizontalScrollOffset < 0) {
            return -1;
        }
        final int bottomIndex = this.getPartialBottomIndex();
        final int height = this.getLinePixel(bottomIndex + 1);
        if (inTextOnly && y > height) {
            return -1;
        }
        final int lineIndex = this.getLineIndex(y);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
        x += this.horizontalScrollOffset - this.leftMargin;
        y -= this.getLinePixel(lineIndex);
        final int offset = layout.getOffset(x, y, trailing);
        final Rectangle rect = layout.getLineBounds(layout.getLineIndex(offset));
        this.renderer.disposeTextLayout(layout);
        if (inTextOnly && (rect.x > x || x > rect.x + rect.width)) {
            return -1;
        }
        return offset + lineOffset;
    }
    
    @Override
    public int getOrientation() {
        return super.getOrientation();
    }
    
    int getPartialBottomIndex() {
        if (this.isFixedLineHeight()) {
            final int lineHeight = this.renderer.getLineHeight();
            final int partialLineCount = Compatibility.ceil(this.clientAreaHeight, lineHeight);
            return Math.max(0, Math.min(this.content.getLineCount(), this.topIndex + partialLineCount) - 1);
        }
        return this.getLineIndex(this.clientAreaHeight - this.bottomMargin);
    }
    
    int getPartialTopIndex() {
        if (this.isFixedLineHeight()) {
            final int lineHeight = this.renderer.getLineHeight();
            return this.getVerticalScrollOffset() / lineHeight;
        }
        return (this.topIndexY <= 0) ? this.topIndex : (this.topIndex - 1);
    }
    
    String getPlatformDelimitedText(final TextWriter writer) {
        final int end = writer.getStart() + writer.getCharCount();
        final int startLine = this.content.getLineAtOffset(writer.getStart());
        final int endLine = this.content.getLineAtOffset(end);
        final String endLineText = this.content.getLine(endLine);
        final int endLineOffset = this.content.getOffsetAtLine(endLine);
        for (int i = startLine; i <= endLine; ++i) {
            writer.writeLine(this.content.getLine(i), this.content.getOffsetAtLine(i));
            if (i < endLine) {
                writer.writeLineDelimiter(StyledText.PlatformLineDelimiter);
            }
        }
        if (end > endLineOffset + endLineText.length()) {
            writer.writeLineDelimiter(StyledText.PlatformLineDelimiter);
        }
        writer.close();
        return writer.toString();
    }
    
    public int[] getRanges() {
        this.checkWidget();
        if (!this.isListening(3002)) {
            final int[] ranges = this.renderer.getRanges(0, this.content.getCharCount());
            if (ranges != null) {
                return ranges;
            }
        }
        return new int[0];
    }
    
    public int[] getRanges(final int start, final int length) {
        this.checkWidget();
        final int contentLength = this.getCharCount();
        final int end = start + length;
        if (start > end || start < 0 || end > contentLength) {
            SWT.error(6);
        }
        if (!this.isListening(3002)) {
            final int[] ranges = this.renderer.getRanges(start, length);
            if (ranges != null) {
                return ranges;
            }
        }
        return new int[0];
    }
    
    public int getRightMargin() {
        this.checkWidget();
        return this.rightMargin;
    }
    
    public Point getSelection() {
        this.checkWidget();
        return new Point(this.selection[0].x, this.selection[0].y);
    }
    
    public Point getSelectionRange() {
        this.checkWidget();
        return new Point(this.selection[0].x, this.selection[0].y - this.selection[0].x);
    }
    
    public int[] getSelectionRanges() {
        this.checkWidget();
        if (this.blockSelection && this.blockXLocation != -1) {
            final Rectangle rect = this.getBlockSelectionPosition();
            final int firstLine = rect.y;
            final int lastLine = rect.height;
            final int left = rect.x;
            final int right = rect.width;
            final int[] ranges = new int[(lastLine - firstLine + 1) * 2];
            int index = 0;
            for (int lineIndex = firstLine; lineIndex <= lastLine; ++lineIndex) {
                int start = this.getOffsetAtPoint(left, 0, lineIndex, null);
                int end = this.getOffsetAtPoint(right, 0, lineIndex, null);
                if (start > end) {
                    final int temp = start;
                    start = end;
                    end = temp;
                }
                ranges[index++] = start;
                ranges[index++] = end - start;
            }
            return ranges;
        }
        final int[] res = new int[2 * this.selection.length];
        int index2 = 0;
        for (final Point p : this.selection) {
            res[index2++] = p.x;
            res[index2++] = p.y - p.x;
        }
        return res;
    }
    
    public Color getSelectionBackground() {
        this.checkWidget();
        if (this.selectionBackground == null) {
            return this.getDisplay().getSystemColor(26);
        }
        return this.selectionBackground;
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        if (this.blockSelection && this.blockXLocation != -1) {
            return this.getBlockSelectionText(this.content.getLineDelimiter()).length();
        }
        return Arrays.stream(this.selection).collect(Collectors.summingInt(sel -> sel.y - sel.x));
    }
    
    public Color getSelectionForeground() {
        this.checkWidget();
        if (this.selectionForeground == null) {
            return this.getDisplay().getSystemColor(27);
        }
        return this.selectionForeground;
    }
    
    public String getSelectionText() {
        this.checkWidget();
        if (this.blockSelection && this.blockXLocation != -1) {
            return this.getBlockSelectionText(this.content.getLineDelimiter());
        }
        return Arrays.stream(this.selection).map(sel -> this.content.getTextRange(sel.x, sel.y - sel.x)).collect((Collector<? super Object, ?, String>)Collectors.joining());
    }
    
    StyledTextEvent getBidiSegments(final int lineOffset, final String line) {
        if (!this.isListening(3007)) {
            if (!this.bidiColoring) {
                return null;
            }
            final StyledTextEvent event = new StyledTextEvent(this.content);
            event.segments = this.getBidiSegmentsCompatibility(line, lineOffset);
            return event;
        }
        else {
            final StyledTextEvent event = this.sendLineEvent(3007, lineOffset, line);
            if (event == null || event.segments == null || event.segments.length == 0) {
                return null;
            }
            final int lineLength = line.length();
            final int[] segments = event.segments;
            if (segments[0] > lineLength) {
                SWT.error(5);
            }
            final char[] segmentsChars = event.segmentsChars;
            final boolean hasSegmentsChars = segmentsChars != null;
            for (int i = 1; i < segments.length; ++i) {
                Label_0182: {
                    if (hasSegmentsChars) {
                        if (segments[i] < segments[i - 1]) {
                            break Label_0182;
                        }
                    }
                    else if (segments[i] <= segments[i - 1]) {
                        break Label_0182;
                    }
                    if (segments[i] <= lineLength) {
                        continue;
                    }
                }
                SWT.error(5);
            }
            if (hasSegmentsChars && !this.visualWrap) {
                for (final char segmentsChar : segmentsChars) {
                    if (segmentsChar == '\n' || segmentsChar == '\r') {
                        this.visualWrap = true;
                        break;
                    }
                }
            }
            return event;
        }
    }
    
    int[] getBidiSegmentsCompatibility(final String line, final int lineOffset) {
        final int lineLength = line.length();
        StyleRange[] styles = null;
        final StyledTextEvent event = this.getLineStyleData(lineOffset, line);
        if (event != null) {
            styles = event.styles;
        }
        else {
            styles = this.renderer.getStyleRanges(lineOffset, lineLength, true);
        }
        if (styles == null || styles.length == 0) {
            return new int[] { 0, lineLength };
        }
        int k = 0;
        int count = 1;
        while (k < styles.length && styles[k].start == 0 && styles[k].length == lineLength) {
            ++k;
        }
        final int[] offsets = new int[(styles.length - k) * 2 + 2];
        for (int i = k; i < styles.length; ++i) {
            final StyleRange style = styles[i];
            final int styleLineStart = Math.max(style.start - lineOffset, 0);
            int styleLineEnd = Math.max(style.start + style.length - lineOffset, styleLineStart);
            styleLineEnd = Math.min(styleLineEnd, line.length());
            if (i > 0 && count > 1 && ((styleLineStart >= offsets[count - 2] && styleLineStart <= offsets[count - 1]) || (styleLineEnd >= offsets[count - 2] && styleLineEnd <= offsets[count - 1])) && style.similarTo(styles[i - 1])) {
                offsets[count - 2] = Math.min(offsets[count - 2], styleLineStart);
                offsets[count - 1] = Math.max(offsets[count - 1], styleLineEnd);
            }
            else {
                if (styleLineStart > offsets[count - 1]) {
                    offsets[count] = styleLineStart;
                    ++count;
                }
                offsets[count] = styleLineEnd;
                ++count;
            }
        }
        if (lineLength > offsets[count - 1]) {
            offsets[count] = lineLength;
            ++count;
        }
        if (count == offsets.length) {
            return offsets;
        }
        final int[] result = new int[count];
        System.arraycopy(offsets, 0, result, 0, count);
        return result;
    }
    
    public StyleRange getStyleRangeAtOffset(final int offset) {
        this.checkWidget();
        if (offset < 0 || offset >= this.getCharCount()) {
            SWT.error(5);
        }
        if (!this.isListening(3002)) {
            final StyleRange[] ranges = this.renderer.getStyleRanges(offset, 1, true);
            if (ranges != null) {
                return ranges[0];
            }
        }
        return null;
    }
    
    public StyleRange[] getStyleRanges() {
        this.checkWidget();
        return this.getStyleRanges(0, this.content.getCharCount(), true);
    }
    
    public StyleRange[] getStyleRanges(final boolean includeRanges) {
        this.checkWidget();
        return this.getStyleRanges(0, this.content.getCharCount(), includeRanges);
    }
    
    public StyleRange[] getStyleRanges(final int start, final int length) {
        this.checkWidget();
        return this.getStyleRanges(start, length, true);
    }
    
    public StyleRange[] getStyleRanges(final int start, final int length, final boolean includeRanges) {
        this.checkWidget();
        final int contentLength = this.getCharCount();
        final int end = start + length;
        if (start > end || start < 0 || end > contentLength) {
            SWT.error(6);
        }
        if (!this.isListening(3002)) {
            final StyleRange[] ranges = this.renderer.getStyleRanges(start, length, includeRanges);
            if (ranges != null) {
                return ranges;
            }
        }
        return new StyleRange[0];
    }
    
    public int getTabs() {
        this.checkWidget();
        return this.tabLength;
    }
    
    public int[] getTabStops() {
        this.checkWidget();
        if (this.tabs == null) {
            return new int[] { this.renderer.tabWidth };
        }
        final int[] result = new int[this.tabs.length];
        System.arraycopy(this.tabs, 0, result, 0, this.tabs.length);
        return result;
    }
    
    public String getText() {
        this.checkWidget();
        return this.content.getTextRange(0, this.getCharCount());
    }
    
    public String getText(final int start, final int end) {
        this.checkWidget();
        final int contentLength = this.getCharCount();
        if (start < 0 || start >= contentLength || end < 0 || end >= contentLength || start > end) {
            SWT.error(6);
        }
        return this.content.getTextRange(start, end - start + 1);
    }
    
    public Rectangle getTextBounds(final int start, final int end) {
        this.checkWidget();
        final int contentLength = this.getCharCount();
        if (start < 0 || start >= contentLength || end < 0 || end >= contentLength || start > end) {
            SWT.error(6);
        }
        final int lineStart = this.content.getLineAtOffset(start);
        final int lineEnd = this.content.getLineAtOffset(end);
        int y = this.getLinePixel(lineStart);
        int height = 0;
        int left = Integer.MAX_VALUE;
        int right = 0;
        for (int i = lineStart; i <= lineEnd; ++i) {
            final int lineOffset = this.content.getOffsetAtLine(i);
            final TextLayout layout = this.renderer.getTextLayout(i);
            final int length = layout.getText().length();
            if (length > 0) {
                Rectangle rect;
                if (i == lineStart) {
                    if (i == lineEnd) {
                        rect = layout.getBounds(start - lineOffset, end - lineOffset);
                    }
                    else {
                        rect = layout.getBounds(start - lineOffset, length);
                    }
                    y += rect.y;
                }
                else if (i == lineEnd) {
                    rect = layout.getBounds(0, end - lineOffset);
                }
                else {
                    rect = layout.getBounds();
                }
                left = Math.min(left, rect.x);
                right = Math.max(right, rect.x + rect.width);
                height += rect.height;
            }
            else {
                height += this.renderer.getLineHeight();
            }
            this.renderer.disposeTextLayout(layout);
        }
        final Rectangle rect2;
        final Rectangle rectangle2;
        final Rectangle rectangle = rectangle2 = (rect2 = new Rectangle(left, y, right - left, height));
        rectangle2.x += this.leftMargin - this.horizontalScrollOffset;
        return rect2;
    }
    
    public String getTextRange(final int start, final int length) {
        this.checkWidget();
        final int contentLength = this.getCharCount();
        final int end = start + length;
        if (start > end || start < 0 || end > contentLength) {
            SWT.error(6);
        }
        return this.content.getTextRange(start, length);
    }
    
    public int getTextLimit() {
        this.checkWidget();
        return this.textLimit;
    }
    
    public int getTopIndex() {
        this.checkWidget();
        return this.topIndex;
    }
    
    public int getTopMargin() {
        this.checkWidget();
        return this.topMargin;
    }
    
    public int getTopPixel() {
        this.checkWidget();
        return this.getVerticalScrollOffset();
    }
    
    int getVerticalIncrement() {
        return this.renderer.getLineHeight();
    }
    
    int getVerticalScrollOffset() {
        if (this.verticalScrollOffset == -1) {
            this.renderer.calculate(0, this.topIndex);
            int height = 0;
            for (int i = 0; i < this.topIndex; ++i) {
                height += this.renderer.getCachedLineHeight(i);
            }
            height -= this.topIndexY;
            this.verticalScrollOffset = height;
        }
        return this.verticalScrollOffset;
    }
    
    int getVisualLineIndex(final TextLayout layout, final int offsetInLine) {
        int lineIndex = layout.getLineIndex(offsetInLine);
        final int[] offsets = layout.getLineOffsets();
        final Caret caret = this.getCaret();
        if (caret != null && lineIndex != 0 && offsetInLine == offsets[lineIndex]) {
            final int lineY = layout.getLineBounds(lineIndex).y;
            final int caretY = caret.getLocation().y - this.getLinePixel(this.getFirstCaretLine());
            if (lineY > caretY) {
                --lineIndex;
            }
            this.caretAlignment = 1;
        }
        return lineIndex;
    }
    
    int getCaretDirection() {
        if (!this.isBidiCaret()) {
            return -1;
        }
        if (this.ime.getCompositionOffset() != -1) {
            return -1;
        }
        if (!this.updateCaretDirection && this.caretDirection != 0) {
            return this.caretDirection;
        }
        this.updateCaretDirection = false;
        final int caretLine = this.getFirstCaretLine();
        final int lineOffset = this.content.getOffsetAtLine(caretLine);
        final String line = this.content.getLine(caretLine);
        int offset = this.caretOffsets[0] - lineOffset;
        final int lineLength = line.length();
        if (lineLength == 0) {
            return this.isMirrored() ? 131072 : 16384;
        }
        if (this.caretAlignment == 0 && offset > 0) {
            --offset;
        }
        if (offset == lineLength && offset > 0) {
            --offset;
        }
        while (offset > 0 && Character.isDigit(line.charAt(offset))) {
            --offset;
        }
        if (offset == 0 && Character.isDigit(line.charAt(offset))) {
            return this.isMirrored() ? 131072 : 16384;
        }
        final TextLayout layout = this.renderer.getTextLayout(caretLine);
        final int level = layout.getLevel(offset);
        this.renderer.disposeTextLayout(layout);
        return ((level & 0x1) != 0x0) ? 131072 : 16384;
    }
    
    int getFirstCaretLine() {
        return this.content.getLineAtOffset(this.caretOffsets[0]);
    }
    
    int getWrapWidth() {
        if (this.wordWrap && !this.isSingleLine()) {
            final int width = this.clientAreaWidth - this.leftMargin - this.rightMargin;
            return (width > 0) ? width : 1;
        }
        return -1;
    }
    
    int getWordNext(final int offset, final int movement) {
        return this.getWordNext(offset, movement, false);
    }
    
    int getWordNext(final int offset, final int movement, final boolean ignoreListener) {
        int newOffset;
        int lineOffset;
        String lineText;
        if (offset >= this.getCharCount()) {
            newOffset = offset;
            final int lineIndex = this.content.getLineCount() - 1;
            lineOffset = this.content.getOffsetAtLine(lineIndex);
            lineText = this.content.getLine(lineIndex);
        }
        else {
            final int lineIndex = this.content.getLineAtOffset(offset);
            lineOffset = this.content.getOffsetAtLine(lineIndex);
            lineText = this.content.getLine(lineIndex);
            final int lineLength = lineText.length();
            if (offset >= lineOffset + lineLength) {
                newOffset = this.content.getOffsetAtLine(lineIndex + 1);
            }
            else {
                final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                newOffset = lineOffset + layout.getNextOffset(offset - lineOffset, movement);
                this.renderer.disposeTextLayout(layout);
            }
        }
        if (ignoreListener) {
            return newOffset;
        }
        return this.sendWordBoundaryEvent(3009, movement, offset, newOffset, lineText, lineOffset);
    }
    
    int getWordPrevious(final int offset, final int movement) {
        return this.getWordPrevious(offset, movement, false);
    }
    
    int getWordPrevious(final int offset, final int movement, final boolean ignoreListener) {
        int newOffset;
        int lineOffset;
        String lineText;
        if (offset <= 0) {
            newOffset = 0;
            final int lineIndex = this.content.getLineAtOffset(newOffset);
            lineOffset = this.content.getOffsetAtLine(lineIndex);
            lineText = this.content.getLine(lineIndex);
        }
        else {
            final int lineIndex = this.content.getLineAtOffset(offset);
            lineOffset = this.content.getOffsetAtLine(lineIndex);
            lineText = this.content.getLine(lineIndex);
            if (offset == lineOffset) {
                final String nextLineText = this.content.getLine(lineIndex - 1);
                final int nextLineOffset = this.content.getOffsetAtLine(lineIndex - 1);
                newOffset = nextLineOffset + nextLineText.length();
            }
            else {
                final int layoutOffset = Math.min(offset - lineOffset, lineText.length());
                final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                newOffset = lineOffset + layout.getPreviousOffset(layoutOffset, movement);
                this.renderer.disposeTextLayout(layout);
            }
        }
        if (ignoreListener) {
            return newOffset;
        }
        return this.sendWordBoundaryEvent(3010, movement, offset, newOffset, lineText, lineOffset);
    }
    
    public boolean getWordWrap() {
        this.checkWidget();
        return this.wordWrap;
    }
    
    public int getWrapIndent() {
        this.checkWidget();
        return this.wrapIndent;
    }
    
    Point getPointAtOffset(final int offset) {
        final int lineIndex = this.content.getLineAtOffset(offset);
        final String line = this.content.getLine(lineIndex);
        final int lineOffset = this.content.getOffsetAtLine(lineIndex);
        int offsetInLine = Math.max(0, offset - lineOffset);
        final int lineLength = line.length();
        if (lineIndex < this.content.getLineCount() - 1) {
            final int endLineOffset = this.content.getOffsetAtLine(lineIndex + 1) - 1;
            if (lineLength < offsetInLine && offsetInLine <= endLineOffset) {
                offsetInLine = lineLength;
            }
        }
        final TextLayout layout = this.renderer.getTextLayout(lineIndex);
        Point point;
        if (lineLength != 0 && offsetInLine <= lineLength) {
            if (offsetInLine == lineLength) {
                offsetInLine = layout.getPreviousOffset(offsetInLine, 2);
                point = layout.getLocation(offsetInLine, true);
            }
            else {
                switch (this.caretAlignment) {
                    case 1: {
                        point = layout.getLocation(offsetInLine, false);
                        break;
                    }
                    default: {
                        boolean lineBegin = offsetInLine == 0;
                        if (this.wordWrap && !lineBegin && (Arrays.binarySearch(this.caretOffsets, offset) < 0 || Arrays.stream(this.selection).allMatch(p -> p.x == p.y))) {
                            final int[] offsets;
                            final int[] array;
                            final int[] lineOffsets = array = (offsets = layout.getLineOffsets());
                            for (final int i : array) {
                                if (i == offsetInLine) {
                                    lineBegin = true;
                                    break;
                                }
                            }
                        }
                        if (lineBegin) {
                            point = layout.getLocation(offsetInLine, false);
                            break;
                        }
                        offsetInLine = layout.getPreviousOffset(offsetInLine, 2);
                        point = layout.getLocation(offsetInLine, true);
                        break;
                    }
                }
            }
        }
        else {
            point = new Point(layout.getIndent(), layout.getVerticalIndent());
        }
        this.renderer.disposeTextLayout(layout);
        final Point point4;
        final Point point2 = point4 = point;
        point4.x += this.leftMargin - this.horizontalScrollOffset;
        final Point point5;
        final Point point3 = point5 = point;
        point5.y += this.getLinePixel(lineIndex);
        return point;
    }
    
    public void insert(final String string) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        if (this.blockSelection) {
            this.insertBlockSelectionText(string, false);
        }
        else {
            final Point sel = this.getSelectionRange();
            this.replaceTextRange(sel.x, sel.y, string);
        }
    }
    
    int insertBlockSelectionText(final String text, final boolean fillWithSpaces) {
        int lineCount = 1;
        for (int i = 0; i < text.length(); ++i) {
            final char ch = text.charAt(i);
            if (ch == '\n' || ch == '\r') {
                ++lineCount;
                if (ch == '\r' && i + 1 < text.length() && text.charAt(i + 1) == '\n') {
                    ++i;
                }
            }
        }
        final String[] lines = new String[lineCount];
        int start = 0;
        lineCount = 0;
        for (int j = 0; j < text.length(); ++j) {
            final char ch2 = text.charAt(j);
            if (ch2 == '\n' || ch2 == '\r') {
                lines[lineCount++] = text.substring(start, j);
                if (ch2 == '\r' && j + 1 < text.length() && text.charAt(j + 1) == '\n') {
                    ++j;
                }
                start = j + 1;
            }
        }
        lines[lineCount++] = text.substring(start);
        if (fillWithSpaces) {
            int maxLength = 0;
            for (final String line : lines) {
                final int length = line.length();
                maxLength = Math.max(maxLength, length);
            }
            for (int k = 0; k < lines.length; ++k) {
                final String line2 = lines[k];
                final int length2 = line2.length();
                if (length2 < maxLength) {
                    final int numSpaces = maxLength - length2;
                    final StringBuilder buffer = new StringBuilder(length2 + numSpaces);
                    buffer.append(line2);
                    for (int l = 0; l < numSpaces; ++l) {
                        buffer.append(' ');
                    }
                    lines[k] = buffer.toString();
                }
            }
        }
        int firstLine;
        int lastLine;
        int left;
        int right;
        if (this.blockXLocation != -1) {
            final Rectangle rect = this.getBlockSelectionPosition();
            firstLine = rect.y;
            lastLine = rect.height;
            left = rect.x;
            right = rect.width;
        }
        else {
            firstLine = (lastLine = this.getFirstCaretLine());
            left = (right = this.getPointAtOffset(this.caretOffsets[0]).x);
        }
        start = this.caretOffsets[0];
        final int caretLine = this.getFirstCaretLine();
        int index = 0;
        int lineIndex;
        for (lineIndex = firstLine; lineIndex <= lastLine; ++lineIndex) {
            final String string = (index < lineCount) ? lines[index++] : "";
            final int lineStart = this.sendTextEvent(left, right, lineIndex, string, fillWithSpaces);
            if (lineIndex == caretLine) {
                start = lineStart;
            }
        }
        while (index < lineCount) {
            final int lineStart2 = this.sendTextEvent(left, left, lineIndex, lines[index++], fillWithSpaces);
            if (lineIndex == caretLine) {
                start = lineStart2;
            }
            ++lineIndex;
        }
        return start;
    }
    
    void insertBlockSelectionText(final char key, final int action) {
        if (key == '\r' || key == '\n') {
            return;
        }
        final Rectangle rect = this.getBlockSelectionPosition();
        final int firstLine = rect.y;
        final int lastLine = rect.height;
        final int left = rect.x;
        final int right = rect.width;
        final int[] trailing = { 0 };
        int offset = 0;
        int delta = 0;
        final String text = (key != '\0') ? new String(new char[] { key }) : "";
        final int length = text.length();
        for (int lineIndex = firstLine; lineIndex <= lastLine; ++lineIndex) {
            final String line = this.content.getLine(lineIndex);
            final int lineOffset = this.content.getOffsetAtLine(lineIndex);
            final int lineEndOffset = lineOffset + line.length();
            final int linePixel = this.getLinePixel(lineIndex);
            int start = this.getOffsetAtPoint(left, linePixel, trailing, true);
            final boolean outOfLine = start == -1;
            if (outOfLine) {
                start = ((left < this.leftMargin) ? lineOffset : lineEndOffset);
            }
            else {
                start += trailing[0];
            }
            int end = this.getOffsetAtPoint(right, linePixel, trailing, true);
            if (end == -1) {
                end = ((right < this.leftMargin) ? lineOffset : lineEndOffset);
            }
            else {
                end += trailing[0];
            }
            if (start > end) {
                final int temp = start;
                start = end;
                end = temp;
            }
            if (start == end && !outOfLine) {
                switch (action) {
                    case 8: {
                        if (start > lineOffset) {
                            start = this.getClusterPrevious(start, lineIndex);
                            break;
                        }
                        break;
                    }
                    case 127: {
                        if (end < lineEndOffset) {
                            end = this.getClusterNext(end, lineIndex);
                            break;
                        }
                        break;
                    }
                }
            }
            if (outOfLine) {
                if (line.length() >= delta) {
                    delta = line.length();
                    offset = lineEndOffset + length;
                }
            }
            else {
                offset = start + length;
                delta = this.content.getCharCount();
            }
            final Event event = new Event();
            event.text = text;
            event.start = start;
            event.end = end;
            this.sendKeyEvent(event);
        }
        final int x = this.getPointAtOffset(offset).x;
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        this.setBlockSelectionLocation(x, this.blockYAnchor - verticalScrollOffset, x, this.blockYLocation - verticalScrollOffset, false);
    }
    
    void installDefaultContent() {
        this.textChangeListener = (TextChangeListener)new lllIIl(this);
        (this.content = (StyledTextContent)new DefaultContent()).addTextChangeListener(this.textChangeListener);
    }
    
    void installListeners() {
        final ScrollBar verticalBar = this.getVerticalBar();
        final ScrollBar horizontalBar = this.getHorizontalBar();
        this.addListener(12, this.listener = (event -> {
            switch (event.type) {
                case 12: {
                    this.handleDispose(event);
                    break;
                }
                case 1: {
                    this.handleKeyDown(event);
                    break;
                }
                case 2: {
                    this.handleKeyUp(event);
                    break;
                }
                case 35: {
                    this.handleMenuDetect(event);
                    break;
                }
                case 3: {
                    this.handleMouseDown(event);
                    break;
                }
                case 4: {
                    this.handleMouseUp(event);
                    break;
                }
                case 5: {
                    this.handleMouseMove(event);
                    break;
                }
                case 9: {
                    this.handlePaint(event);
                    break;
                }
                case 11: {
                    this.handleResize(event);
                    break;
                }
                case 31: {
                    this.handleTraverse(event);
                    break;
                }
            }
            return;
        }));
        this.addListener(1, this.listener);
        this.addListener(2, this.listener);
        this.addListener(35, this.listener);
        this.addListener(3, this.listener);
        this.addListener(4, this.listener);
        this.addListener(5, this.listener);
        this.addListener(9, this.listener);
        this.addListener(11, this.listener);
        this.addListener(31, this.listener);
        this.ime.addListener(43, event -> {
            if (!this.editable) {
                event.doit = false;
                event.start = 0;
                event.end = 0;
                event.text = "";
                return;
            }
            else {
                switch (event.detail) {
                    case 3: {
                        this.handleCompositionSelection(event);
                        break;
                    }
                    case 1: {
                        this.handleCompositionChanged(event);
                        break;
                    }
                    case 2: {
                        this.handleCompositionOffset(event);
                        break;
                    }
                }
                return;
            }
        });
        if (verticalBar != null) {
            verticalBar.addListener(13, this::handleVerticalScroll);
        }
        if (horizontalBar != null) {
            horizontalBar.addListener(13, this::handleHorizontalScroll);
        }
    }
    
    void internalRedrawRange(int start, final int length) {
        if (length <= 0) {
            return;
        }
        int end = start + length;
        int startLine = this.content.getLineAtOffset(start);
        int endLine = this.content.getLineAtOffset(end);
        final int partialBottomIndex = this.getPartialBottomIndex();
        final int partialTopIndex = this.getPartialTopIndex();
        if (startLine > partialBottomIndex || endLine < partialTopIndex) {
            return;
        }
        if (partialTopIndex > startLine) {
            startLine = partialTopIndex;
            start = 0;
        }
        else {
            start -= this.content.getOffsetAtLine(startLine);
        }
        if (partialBottomIndex < endLine) {
            endLine = partialBottomIndex + 1;
            end = 0;
        }
        else {
            end -= this.content.getOffsetAtLine(endLine);
        }
        TextLayout layout = this.renderer.getTextLayout(startLine);
        final int lineX = this.leftMargin - this.horizontalScrollOffset;
        final int startLineY = this.getLinePixel(startLine);
        int[] offsets = layout.getLineOffsets();
        final int startIndex = layout.getLineIndex(Math.min(start, layout.getText().length()));
        if (this.isWordWrap() && startIndex > 0 && offsets[startIndex] == start) {
            final Rectangle rect = layout.getLineBounds(startIndex - 1);
            rect.x = rect.width;
            rect.width = this.clientAreaWidth - this.rightMargin - rect.x;
            final Rectangle rectangle8;
            final Rectangle rectangle = rectangle8 = rect;
            rectangle8.x += lineX;
            final Rectangle rectangle9;
            final Rectangle rectangle2 = rectangle9 = rect;
            rectangle9.y += startLineY;
            super.redraw(rect.x, rect.y, rect.width, rect.height, false);
        }
        if (startLine == endLine) {
            final int endIndex = layout.getLineIndex(Math.min(end, layout.getText().length()));
            if (startIndex == endIndex) {
                final Rectangle rect2;
                final Rectangle rectangle10;
                final Rectangle bounds3 = rectangle10 = (rect2 = layout.getBounds(start, end - 1));
                rectangle10.x += lineX;
                final Rectangle rectangle11;
                final Rectangle rectangle3 = rectangle11 = rect2;
                rectangle11.y += startLineY;
                super.redraw(rect2.x, rect2.y, rect2.width, rect2.height, false);
                this.renderer.disposeTextLayout(layout);
                return;
            }
        }
        final Rectangle startRect = layout.getBounds(start, offsets[startIndex + 1] - 1);
        if (startRect.height == 0) {
            final Rectangle bounds4 = layout.getLineBounds(startIndex);
            startRect.x = bounds4.width;
            startRect.y = bounds4.y;
            startRect.height = bounds4.height;
        }
        final Rectangle rectangle12;
        final Rectangle rectangle4 = rectangle12 = startRect;
        rectangle12.x += lineX;
        final Rectangle rectangle13;
        final Rectangle rectangle5 = rectangle13 = startRect;
        rectangle13.y += startLineY;
        startRect.width = this.clientAreaWidth - this.rightMargin - startRect.x;
        super.redraw(startRect.x, startRect.y, startRect.width, startRect.height, false);
        if (startLine != endLine) {
            this.renderer.disposeTextLayout(layout);
            layout = this.renderer.getTextLayout(endLine);
            offsets = layout.getLineOffsets();
        }
        final int endIndex2 = layout.getLineIndex(Math.min(end, layout.getText().length()));
        final Rectangle endRect = layout.getBounds(offsets[endIndex2], end - 1);
        if (endRect.height == 0) {
            final Rectangle bounds5 = layout.getLineBounds(endIndex2);
            endRect.y = bounds5.y;
            endRect.height = bounds5.height;
        }
        final Rectangle rectangle14;
        final Rectangle rectangle6 = rectangle14 = endRect;
        rectangle14.x += lineX;
        final Rectangle rectangle15;
        final Rectangle rectangle7 = rectangle15 = endRect;
        rectangle15.y += this.getLinePixel(endLine);
        super.redraw(endRect.x, endRect.y, endRect.width, endRect.height, false);
        this.renderer.disposeTextLayout(layout);
        final int y = startRect.y + startRect.height;
        if (endRect.y > y) {
            super.redraw(this.leftMargin, y, this.clientAreaWidth - this.rightMargin - this.leftMargin, endRect.y - y, false);
        }
    }
    
    void handleCompositionOffset(final Event event) {
        final int[] trailing = { 0 };
        event.index = this.getOffsetAtPoint(event.x, event.y, trailing, true);
        event.count = trailing[0];
    }
    
    void handleCompositionSelection(final Event event) {
        if (event.start != event.end) {
            final int charCount = this.getCharCount();
            event.start = Math.max(0, Math.min(event.start, charCount));
            event.end = Math.max(0, Math.min(event.end, charCount));
            if (event.text != null) {
                this.setSelection(event.start, event.end);
            }
            else {
                event.text = this.getTextRange(event.start, event.end - event.start);
            }
        }
        else {
            event.start = this.selection[0].x;
            event.end = this.selection[0].y;
            event.text = this.getSelectionText();
        }
    }
    
    void handleCompositionChanged(final Event event) {
        final String text = event.text;
        int start = event.start;
        int end = event.end;
        final int charCount = this.content.getCharCount();
        start = Math.min(start, charCount);
        end = Math.min(end, charCount);
        final int length = text.length();
        if (length == this.ime.getCommitCount()) {
            this.content.replaceTextRange(start, end - start, "");
            this.setCaretOffsets(new int[] { this.ime.getCompositionOffset() }, -1);
            this.caretWidth = 0;
            this.caretDirection = 0;
        }
        else {
            this.content.replaceTextRange(start, end - start, text);
            int alignment = -1;
            if (this.ime.getWideCaret()) {
                start = this.ime.getCompositionOffset();
                for (final int caretOffset : this.caretOffsets) {
                    final int lineIndex = this.content.getLineAtOffset(caretOffset);
                    final int lineOffset = this.content.getOffsetAtLine(lineIndex);
                    final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                    this.caretWidth = layout.getBounds(start - lineOffset, start + length - 1 - lineOffset).width;
                    this.renderer.disposeTextLayout(layout);
                }
                alignment = 1;
            }
            this.setCaretOffsets(new int[] { this.ime.getCaretOffset() }, alignment);
        }
        this.resetSelection();
        this.showCaret();
    }
    
    void handleDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.clipboard.dispose();
        if (this.renderer != null) {
            this.renderer.dispose();
            this.renderer = null;
        }
        if (this.content != null) {
            this.content.removeTextChangeListener(this.textChangeListener);
            this.content = null;
        }
        if (this.defaultCaret != null) {
            this.defaultCaret.dispose();
            this.defaultCaret = null;
        }
        if (this.leftCaretBitmap != null) {
            this.leftCaretBitmap.dispose();
            this.leftCaretBitmap = null;
        }
        if (this.rightCaretBitmap != null) {
            this.rightCaretBitmap.dispose();
            this.rightCaretBitmap = null;
        }
        if (this.carets != null) {
            for (final Caret caret : this.carets) {
                if (caret != null) {
                    caret.dispose();
                }
            }
            this.carets = null;
        }
        if (this.isBidiCaret()) {
            BidiUtil.removeLanguageListener(this);
        }
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.marginColor = null;
        this.textChangeListener = null;
        this.selection = null;
        this.doubleClickSelection = null;
        this.keyActionMap = null;
        this.background = null;
        this.foreground = null;
        this.clipboard = null;
        this.tabs = null;
    }
    
    void handleHorizontalScroll(final Event event) {
        final int scrollPixel = this.getHorizontalBar().getSelection() - this.horizontalScrollOffset;
        this.scrollHorizontal(scrollPixel, false);
    }
    
    void handleKey(final Event event) {
        this.caretAlignment = 0;
        int action;
        if (event.keyCode != 0) {
            action = this.getKeyBinding(event.keyCode | event.stateMask);
        }
        else {
            action = this.getKeyBinding(event.character | event.stateMask);
            if (action == 0 && (event.stateMask & 0x40000) != 0x0 && event.character <= '\u001f') {
                final int c = event.character + '@';
                action = this.getKeyBinding(c | event.stateMask);
            }
        }
        if (action == 0) {
            boolean ignore = false;
            if (StyledText.IS_MAC) {
                ignore = ((event.stateMask & 0x440000) != 0x0);
            }
            else {
                ignore = (event.stateMask == 65536 || event.stateMask == 262144 || event.stateMask == 196608 || event.stateMask == 393216);
            }
            if ((!ignore && event.character > '\u001f' && event.character != '\u007f') || event.character == '\r' || event.character == '\n' || event.character == '\t') {
                this.doContent(event.character);
                this.update();
            }
        }
        else {
            this.invokeAction(action);
        }
    }
    
    void handleKeyDown(final Event event) {
        if (this.clipboardSelection == null) {
            this.clipboardSelection = new Point(this.selection[0].x, this.selection[0].y);
        }
        this.newOrientation = 0;
        event.stateMask &= SWT.MODIFIER_MASK;
        final Event verifyEvent = new Event();
        verifyEvent.character = event.character;
        verifyEvent.keyCode = event.keyCode;
        verifyEvent.keyLocation = event.keyLocation;
        verifyEvent.stateMask = event.stateMask;
        verifyEvent.doit = event.doit;
        this.notifyListeners(3005, verifyEvent);
        if (verifyEvent.doit) {
            if ((event.stateMask & SWT.MODIFIER_MASK) == 0x40000 && event.keyCode == 131072 && this.isBidiCaret()) {
                this.newOrientation = ((event.keyLocation == 16384) ? 33554432 : 67108864);
            }
            this.handleKey(event);
        }
    }
    
    void handleKeyUp(final Event event) {
        if (this.clipboardSelection != null && (this.clipboardSelection.x != this.selection[0].x || this.clipboardSelection.y != this.selection[0].y)) {
            this.copySelection(2);
        }
        this.clipboardSelection = null;
        if (this.newOrientation != 0) {
            if (this.newOrientation != this.getOrientation()) {
                final Event e = new Event();
                e.doit = true;
                this.notifyListeners(44, e);
                if (e.doit) {
                    this.setOrientation(this.newOrientation);
                }
            }
            this.newOrientation = 0;
        }
    }
    
    void handleMenuDetect(final Event event) {
        if (event.detail == 1) {
            final Point point = this.getDisplay().map(this, null, this.getPointAtOffset(this.caretOffsets[0]));
            event.x = point.x;
            event.y = point.y + this.getLineHeight(this.caretOffsets[0]);
        }
    }
    
    void handleMouseDown(final Event event) {
        this.forceFocus();
        if (this.dragDetect && this.checkDragDetect(event)) {
            return;
        }
        if (event.button == 2) {
            if (StyledText.IS_GTK && this.mouseNavigator != null) {
                this.middleClickPressed = true;
                final boolean click;
                this.getDisplay().timerExec(200, () -> {
                    click = this.middleClickPressed;
                    this.middleClickPressed = false;
                    if (click && this.mouseNavigator != null) {
                        this.mouseNavigator.onMouseDown(event);
                    }
                    else {
                        this.pasteOnMiddleClick(event);
                    }
                });
                return;
            }
            this.pasteOnMiddleClick(event);
        }
        if (event.button != 1 || (StyledText.IS_MAC && (event.stateMask & SWT.MOD4) != 0x0)) {
            return;
        }
        this.clickCount = event.count;
        final boolean addSelection = (event.stateMask & SWT.MOD3) != 0x0;
        if (this.clickCount == 1) {
            if (addSelection && !this.blockSelection) {
                final int offset = this.getOffsetAtPoint(event.x, event.y, null);
                this.addSelection(offset, 0);
                this.sendSelectionEvent();
            }
            else {
                final boolean expandSelection = (event.stateMask & SWT.MOD2) != 0x0;
                this.doMouseLocationChange(event.x, event.y, expandSelection);
            }
        }
        else if (this.doubleClickEnabled) {
            final boolean wordSelect = (this.clickCount & 0x1) == 0x0;
            int offset2 = this.getOffsetAtPoint(event.x, event.y, null);
            final int lineIndex = this.content.getLineAtOffset(offset2);
            final int lineOffset = this.content.getOffsetAtLine(lineIndex);
            if (wordSelect) {
                final String line = this.content.getLine(lineIndex);
                final int lineLength = line.length();
                final int min = this.blockSelection ? lineOffset : 0;
                final int max = this.blockSelection ? (lineOffset + lineLength) : this.content.getCharCount();
                final Point offsetPoint = this.getPointAtOffset(offset2);
                if (event.x > offsetPoint.x && offset2 < Math.min(max, lineOffset + lineLength) && !Character.isWhitespace(line.charAt(offset2 - lineOffset))) {
                    ++offset2;
                }
                final int start = Math.max(min, this.getWordPrevious(offset2, 16));
                final int end = Math.min(max, this.getWordNext(start, 8));
                int[] regions = new int[2];
                if (addSelection) {
                    final int[] current = this.getSelectionRanges();
                    regions = Arrays.copyOf(current, current.length + 2);
                }
                regions[regions.length - 2] = start;
                regions[regions.length - 1] = end - start;
                this.setSelection(regions, false, true);
                this.sendSelectionEvent();
            }
            else if (this.blockSelection) {
                this.setBlockSelectionLocation(this.leftMargin, event.y, this.clientAreaWidth - this.rightMargin, event.y, true);
            }
            else {
                int lineEnd = this.content.getCharCount();
                if (lineIndex + 1 < this.content.getLineCount()) {
                    lineEnd = this.content.getOffsetAtLine(lineIndex + 1);
                }
                int[] regions2 = new int[2];
                if (addSelection) {
                    final int[] current2 = this.getSelectionRanges();
                    regions2 = Arrays.copyOf(current2, current2.length + 2);
                }
                regions2[regions2.length - 2] = lineOffset;
                regions2[regions2.length - 1] = lineEnd - lineOffset;
                this.setSelection(regions2, false, false);
                this.sendSelectionEvent();
            }
            this.doubleClickSelection = new Point(this.selection[0].x, this.selection[0].y);
            this.showCaret();
        }
    }
    
    void addSelection(final int offset, final int length) {
        int[] ranges = this.getSelectionRanges();
        ranges = Arrays.copyOf(ranges, ranges.length + 2);
        ranges[ranges.length - 2] = offset;
        ranges[ranges.length - 1] = length;
        this.setSelection(ranges, true, true);
    }
    
    void handleMouseMove(final Event event) {
        if (this.clickCount > 0) {
            this.update();
            this.doAutoScroll(event);
            this.doMouseLocationChange(event.x, event.y, true);
        }
        if (this.renderer.hasLinks) {
            this.doMouseLinkCursor(event.x, event.y);
        }
    }
    
    void handleMouseUp(final Event event) {
        this.middleClickPressed = false;
        this.clickCount = 0;
        this.endAutoScroll();
        if (event.button == 1) {
            this.copySelection(2);
        }
    }
    
    void handlePaint(final Event event) {
        if (event.width == 0 || event.height == 0) {
            return;
        }
        if (this.clientAreaWidth == 0 || this.clientAreaHeight == 0) {
            return;
        }
        final int startLine = this.getLineIndex(event.y);
        int y = this.getLinePixel(startLine);
        final int endY = event.y + event.height;
        final GC gc = event.gc;
        final Color background = this.getBackground();
        final Color foreground = this.getForeground();
        if (endY > 0) {
            for (int lineCount = this.isSingleLine() ? 1 : this.content.getLineCount(), x = this.leftMargin - this.horizontalScrollOffset, i = startLine; y < endY && i < lineCount; y += this.renderer.drawLine(i, x, y, gc, background, foreground), ++i) {}
            if (y < endY) {
                gc.setBackground(background);
                this.drawBackground(gc, 0, y, this.clientAreaWidth, endY - y);
            }
        }
        if (this.blockSelection && this.blockXLocation != -1) {
            gc.setBackground(this.getSelectionBackground());
            final Rectangle rect = this.getBlockSelectionRectangle();
            gc.drawRectangle(rect.x, rect.y, Math.max(1, rect.width - 1), Math.max(1, rect.height - 1));
            gc.setAdvanced(true);
            if (gc.getAdvanced()) {
                gc.setAlpha(100);
                gc.fillRectangle(rect);
                gc.setAdvanced(false);
            }
        }
        if (this.carets != null) {
            for (int j = 1; j < this.carets.length; ++j) {
                final Caret caret = this.carets[j];
                if (caret.isVisible()) {
                    if (caret.getImage() != null) {
                        gc.drawImage(caret.getImage(), caret.getBounds().x, caret.getBounds().y);
                    }
                    else {
                        gc.drawRectangle(caret.getBounds().x, caret.getBounds().y, caret.getBounds().width, this.getLineHeight(this.caretOffsets[j]));
                    }
                }
            }
        }
        gc.setBackground((this.marginColor != null) ? this.marginColor : background);
        if (this.topMargin > 0) {
            this.drawBackground(gc, 0, 0, this.clientAreaWidth, this.topMargin);
        }
        if (this.bottomMargin > 0) {
            this.drawBackground(gc, 0, this.clientAreaHeight - this.bottomMargin, this.clientAreaWidth, this.bottomMargin);
        }
        if (this.leftMargin - this.alignmentMargin > 0) {
            this.drawBackground(gc, 0, 0, this.leftMargin - this.alignmentMargin, this.clientAreaHeight);
        }
        if (this.rightMargin > 0) {
            this.drawBackground(gc, this.clientAreaWidth - this.rightMargin, 0, this.rightMargin, this.clientAreaHeight);
        }
    }
    
    void handleResize(final Event event) {
        final int oldHeight = this.clientAreaHeight;
        final int oldWidth = this.clientAreaWidth;
        final Rectangle clientArea = this.getClientArea();
        this.clientAreaHeight = clientArea.height;
        this.clientAreaWidth = clientArea.width;
        if (!this.alwaysShowScroll && this.ignoreResize != 0) {
            return;
        }
        this.redrawMargins(oldHeight, oldWidth);
        if (this.wordWrap) {
            if (oldWidth != this.clientAreaWidth) {
                this.renderer.reset(0, this.content.getLineCount());
                this.verticalScrollOffset = -1;
                this.renderer.calculateIdle();
                super.redraw();
            }
            if (oldHeight != this.clientAreaHeight) {
                if (oldHeight == 0) {
                    this.topIndexY = 0;
                }
                this.setScrollBars(true);
            }
            this.setCaretLocations();
        }
        else {
            this.renderer.calculateClientArea();
            this.setScrollBars(true);
            this.claimRightFreeSpace();
            if (this.clientAreaWidth != 0) {
                final ScrollBar horizontalBar = this.getHorizontalBar();
                if (horizontalBar != null && horizontalBar.getVisible() && this.horizontalScrollOffset != horizontalBar.getSelection()) {
                    horizontalBar.setSelection(this.horizontalScrollOffset);
                    this.horizontalScrollOffset = horizontalBar.getSelection();
                }
            }
        }
        this.updateCaretVisibility();
        this.claimBottomFreeSpace();
        this.setAlignment();
    }
    
    void handleTextChanged(final TextChangedEvent event) {
        final int offset = this.ime.getCompositionOffset();
        if (offset != -1 && this.lastTextChangeStart < offset) {
            this.ime.setCompositionOffset(offset + this.lastTextChangeNewCharCount - this.lastTextChangeReplaceCharCount);
        }
        final int firstLine = this.content.getLineAtOffset(this.lastTextChangeStart);
        this.resetCache(firstLine, 0);
        if (!this.isFixedLineHeight() && this.topIndex > firstLine) {
            this.topIndex = firstLine;
            if (this.topIndex < 0) {
                System.err.println("StyledText: topIndex was " + this.topIndex + ", lastTextChangeStart = " + this.lastTextChangeStart + ", content.getClass() = " + this.content.getClass());
                this.topIndex = 0;
            }
            this.topIndexY = 0;
            super.redraw();
        }
        else {
            final int lastLine = firstLine + this.lastTextChangeNewLineCount;
            final int firstLineTop = this.getLinePixel(firstLine);
            final int newLastLineBottom = this.getLinePixel(lastLine + 1);
            if (this.lastLineBottom != newLastLineBottom) {
                super.redraw();
            }
            else {
                super.redraw(0, firstLineTop, this.clientAreaWidth, newLastLineBottom - firstLineTop, false);
                this.redrawLinesBullet(this.renderer.redrawLines);
            }
        }
        this.renderer.redrawLines = null;
        if (!this.blockSelection || this.blockXLocation == -1) {
            this.updateSelection(this.lastTextChangeStart, this.lastTextChangeReplaceCharCount, this.lastTextChangeNewCharCount);
        }
        if (this.lastTextChangeReplaceLineCount > 0 || this.wordWrap || this.visualWrap) {
            this.claimBottomFreeSpace();
        }
        if (this.lastTextChangeReplaceCharCount > 0) {
            this.claimRightFreeSpace();
        }
        this.sendAccessibleTextChanged(this.lastTextChangeStart, this.lastTextChangeNewCharCount, 0);
        this.lastCharCount += this.lastTextChangeNewCharCount;
        this.lastCharCount -= this.lastTextChangeReplaceCharCount;
        this.setAlignment();
    }
    
    void handleTextChanging(final TextChangingEvent event) {
        if (event.replaceCharCount < 0) {
            event.start += event.replaceCharCount;
            event.replaceCharCount *= -1;
        }
        this.lastTextChangeStart = event.start;
        this.lastTextChangeNewLineCount = event.newLineCount;
        this.lastTextChangeNewCharCount = event.newCharCount;
        this.lastTextChangeReplaceLineCount = event.replaceLineCount;
        this.lastTextChangeReplaceCharCount = event.replaceCharCount;
        final int lineIndex = this.content.getLineAtOffset(event.start);
        final int srcY = this.getLinePixel(lineIndex + event.replaceLineCount + 1);
        final int destY = this.getLinePixel(lineIndex + 1) + event.newLineCount * this.renderer.getLineHeight();
        this.lastLineBottom = destY;
        if (srcY < 0 && destY < 0) {
            this.lastLineBottom += srcY - destY;
            this.verticalScrollOffset += destY - srcY;
            this.calculateTopIndex(destY - srcY);
            this.setScrollBars(true);
        }
        else {
            this.scrollText(srcY, destY);
        }
        this.sendAccessibleTextChanged(this.lastTextChangeStart, 0, this.lastTextChangeReplaceCharCount);
        this.renderer.textChanging(event);
        int newEndOfText;
        int tooBigOffsets;
        for (newEndOfText = this.content.getCharCount() - event.replaceCharCount + event.newCharCount, tooBigOffsets = 0; tooBigOffsets < this.caretOffsets.length && this.caretOffsets[this.caretOffsets.length - 1 - tooBigOffsets] > newEndOfText; ++tooBigOffsets) {}
        if (tooBigOffsets != 0) {
            final int[] newCaretOffsets = Arrays.copyOf(this.caretOffsets, this.caretOffsets.length - tooBigOffsets + 1);
            newCaretOffsets[newCaretOffsets.length - 1] = newEndOfText;
            this.setCaretOffsets(newCaretOffsets, -1);
        }
    }
    
    void handleTextSet(final TextChangedEvent event) {
        this.reset();
        final int newCharCount = this.getCharCount();
        this.sendAccessibleTextChanged(0, newCharCount, this.lastCharCount);
        this.lastCharCount = newCharCount;
        this.setAlignment();
    }
    
    void handleTraverse(final Event event) {
        switch (event.detail) {
            case 2:
            case 256:
            case 512: {
                event.doit = true;
                break;
            }
            case 4:
            case 8:
            case 16: {
                if ((this.getStyle() & 0x4) != 0x0) {
                    event.doit = true;
                    break;
                }
                if (!this.editable || (event.stateMask & SWT.MODIFIER_MASK) != 0x0) {
                    event.doit = true;
                    break;
                }
                break;
            }
        }
    }
    
    void handleVerticalScroll(final Event event) {
        final int scrollPixel = this.getVerticalBar().getSelection() - this.getVerticalScrollOffset();
        this.scrollVertical(scrollPixel, false);
    }
    
    void initializeAccessible() {
        this.acc = this.getAccessible();
        this.accAdapter = (AccessibleAdapter)new lIIllII(this);
        this.acc.addAccessibleListener((AccessibleListener)this.accAdapter);
        this.accTextExtendedAdapter = (AccessibleTextExtendedAdapter)new llIlIl(this);
        this.acc.addAccessibleTextListener((AccessibleTextListener)this.accTextExtendedAdapter);
        this.accEditableTextListener = (AccessibleEditableTextListener)new lIIIIIl(this);
        this.acc.addAccessibleEditableTextListener(this.accEditableTextListener);
        this.accAttributeAdapter = (AccessibleAttributeAdapter)new llIllI(this);
        this.acc.addAccessibleAttributeListener((AccessibleAttributeListener)this.accAttributeAdapter);
        this.accControlAdapter = (AccessibleControlAdapter)new llIlll(this);
        this.acc.addAccessibleControlListener((AccessibleControlListener)this.accControlAdapter);
        this.addListener(15, event -> this.acc.setFocus(-1));
    }
    
    @Override
    public void dispose() {
        if (!this.isDisposed()) {
            this.acc.removeAccessibleControlListener((AccessibleControlListener)this.accControlAdapter);
            this.acc.removeAccessibleAttributeListener((AccessibleAttributeListener)this.accAttributeAdapter);
            this.acc.removeAccessibleEditableTextListener(this.accEditableTextListener);
            this.acc.removeAccessibleTextListener((AccessibleTextListener)this.accTextExtendedAdapter);
            this.acc.removeAccessibleListener((AccessibleListener)this.accAdapter);
        }
        super.dispose();
    }
    
    String getAssociatedLabel() {
        final Control[] siblings = this.getParent().getChildren();
        int i = 0;
        while (i < siblings.length) {
            if (siblings[i] == this) {
                if (i <= 0) {
                    break;
                }
                final Control sibling = siblings[i - 1];
                if (sibling instanceof Label) {
                    return ((Label)sibling).getText();
                }
                if (sibling instanceof CLabel) {
                    return ((CLabel)sibling).getText();
                }
                break;
            }
            else {
                ++i;
            }
        }
        return null;
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
    
    public void invokeAction(final int action) {
        this.checkWidget();
        if (this.blockSelection && this.invokeBlockAction(action)) {
            return;
        }
        this.updateCaretDirection = true;
        switch (action) {
            case 16777217: {
                this.doLineUp(false);
                this.clearSelection(true);
                break;
            }
            case 16777218: {
                this.doLineDown(false);
                this.clearSelection(true);
                break;
            }
            case 16777223: {
                this.doLineStart();
                this.clearSelection(true);
                break;
            }
            case 16777224: {
                this.doLineEnd();
                this.clearSelection(true);
                break;
            }
            case 16777219: {
                this.doCursorPrevious();
                this.clearSelection(true);
                break;
            }
            case 16777220: {
                this.doCursorNext();
                this.clearSelection(true);
                break;
            }
            case 16777221: {
                this.doPageUp(false, -1);
                this.clearSelection(true);
                break;
            }
            case 16777222: {
                this.doPageDown(false, -1);
                this.clearSelection(true);
                break;
            }
            case 17039363: {
                this.doWordPrevious();
                this.clearSelection(true);
                break;
            }
            case 17039364: {
                this.doWordNext();
                this.clearSelection(true);
                break;
            }
            case 17039367: {
                this.doContentStart();
                this.clearSelection(true);
                break;
            }
            case 17039368: {
                this.doContentEnd();
                this.clearSelection(true);
                break;
            }
            case 17039365: {
                this.doPageStart();
                this.clearSelection(true);
                break;
            }
            case 17039366: {
                this.doPageEnd();
                this.clearSelection(true);
                break;
            }
            case 16908289: {
                this.doSelectionLineUp();
                break;
            }
            case 262209: {
                this.selectAll();
                break;
            }
            case 16908290: {
                this.doSelectionLineDown();
                break;
            }
            case 16908295: {
                this.doLineStart();
                this.doSelection(16777219);
                break;
            }
            case 16908296: {
                this.doLineEnd();
                this.doSelection(16777220);
                break;
            }
            case 16908291: {
                this.doSelectionCursorPrevious();
                this.doSelection(16777219);
                break;
            }
            case 16908292: {
                this.doSelectionCursorNext();
                this.doSelection(16777220);
                break;
            }
            case 16908293: {
                this.doSelectionPageUp(-1);
                break;
            }
            case 16908294: {
                this.doSelectionPageDown(-1);
                break;
            }
            case 17170435: {
                this.doSelectionWordPrevious();
                this.doSelection(16777219);
                break;
            }
            case 17170436: {
                this.doSelectionWordNext();
                this.doSelection(16777220);
                break;
            }
            case 17170439: {
                this.doContentStart();
                this.doSelection(16777219);
                break;
            }
            case 17170440: {
                this.doContentEnd();
                this.doSelection(16777220);
                break;
            }
            case 17170437: {
                this.doPageStart();
                this.doSelection(16777219);
                break;
            }
            case 17170438: {
                this.doPageEnd();
                this.doSelection(16777220);
                break;
            }
            case 131199: {
                this.cut();
                break;
            }
            case 17039369: {
                this.copy();
                break;
            }
            case 16908297: {
                this.paste();
                break;
            }
            case 8: {
                this.doBackspace();
                break;
            }
            case 127: {
                this.doDelete();
                break;
            }
            case 262152: {
                this.doDeleteWordPrevious();
                break;
            }
            case 262271: {
                this.doDeleteWordNext();
                break;
            }
            case 16777225: {
                this.overwrite = !this.overwrite;
                break;
            }
            case 16777226: {
                this.setBlockSelection(!this.blockSelection);
                break;
            }
        }
    }
    
    boolean invokeBlockAction(final int action) {
        switch (action) {
            case 16777217:
            case 16777218:
            case 16777219:
            case 16777220:
            case 16777221:
            case 16777222:
            case 16777223:
            case 16777224:
            case 17039363:
            case 17039364:
            case 17039365:
            case 17039366:
            case 17039367:
            case 17039368: {
                this.clearBlockSelection(false, false);
                return false;
            }
            case 16908289: {
                this.doBlockLineVertical(true);
                return true;
            }
            case 16908290: {
                this.doBlockLineVertical(false);
                return true;
            }
            case 16908295: {
                this.doBlockLineHorizontal(false);
                return true;
            }
            case 16908296: {
                this.doBlockLineHorizontal(true);
                return false;
            }
            case 16908291: {
                this.doBlockColumn(false);
                return true;
            }
            case 16908292: {
                this.doBlockColumn(true);
                return true;
            }
            case 17170435: {
                this.doBlockWord(false);
                return true;
            }
            case 17170436: {
                this.doBlockWord(true);
                return true;
            }
            case 262209: {
                return false;
            }
            case 17170439: {
                this.doBlockContentStartEnd(false);
                break;
            }
            case 17170440: {
                this.doBlockContentStartEnd(true);
                break;
            }
            case 16908293:
            case 16908294:
            case 17170437:
            case 17170438: {
                return true;
            }
            case 131199:
            case 16908297:
            case 17039369: {
                return false;
            }
            case 8:
            case 127: {
                if (this.blockXLocation != -1) {
                    this.insertBlockSelectionText('\0', action);
                    return true;
                }
                return false;
            }
            case 262152:
            case 262271: {
                return this.blockXLocation != -1;
            }
        }
        return false;
    }
    
    boolean isBidiCaret() {
        return BidiUtil.isBidiPlatform();
    }
    
    boolean isFixedLineHeight() {
        return !this.isWordWrap() && this.lineSpacing == 0 && this.renderer.lineSpacingProvider == null && !this.hasStyleWithVariableHeight && !this.hasVerticalIndent;
    }
    
    boolean isLineDelimiter(final int offset) {
        final int line = this.content.getLineAtOffset(offset);
        final int lineOffset = this.content.getOffsetAtLine(line);
        final int offsetInLine = offset - lineOffset;
        return offsetInLine > this.content.getLine(line).length();
    }
    
    boolean isMirrored() {
        return (this.getStyle() & 0x8000000) != 0x0;
    }
    
    public boolean isTextSelected() {
        this.checkWidget();
        if (this.blockSelection && this.blockXLocation != -1) {
            final Rectangle rect = this.getBlockSelectionPosition();
            return !rect.isEmpty();
        }
        return Arrays.stream(this.selection).anyMatch(range -> range.x != range.y);
    }
    
    boolean isSingleLine() {
        return (this.getStyle() & 0x4) != 0x0;
    }
    
    void modifyContent(final Event event, final boolean updateCaret) {
        event.doit = true;
        this.notifyListeners(25, event);
        if (event.doit) {
            StyledTextEvent styledTextEvent = null;
            final int replacedLength = event.end - event.start;
            if (this.isListening(3000)) {
                styledTextEvent = new StyledTextEvent(this.content);
                styledTextEvent.start = event.start;
                styledTextEvent.end = event.start + event.text.length();
                styledTextEvent.text = this.content.getTextRange(event.start, replacedLength);
            }
            if (updateCaret && event.text.length() == 0) {
                final int lineIndex = this.content.getLineAtOffset(event.start);
                int lineOffset = this.content.getOffsetAtLine(lineIndex);
                TextLayout layout = this.renderer.getTextLayout(lineIndex);
                final int levelStart = layout.getLevel(event.start - lineOffset);
                final int lineIndexEnd = this.content.getLineAtOffset(event.end);
                if (lineIndex != lineIndexEnd) {
                    this.renderer.disposeTextLayout(layout);
                    lineOffset = this.content.getOffsetAtLine(lineIndexEnd);
                    layout = this.renderer.getTextLayout(lineIndexEnd);
                }
                final int levelEnd = layout.getLevel(event.end - lineOffset);
                this.renderer.disposeTextLayout(layout);
                if (levelStart != levelEnd) {
                    this.caretAlignment = 0;
                }
                else {
                    this.caretAlignment = 1;
                }
            }
            this.content.replaceTextRange(event.start, replacedLength, event.text);
            if (updateCaret && (!this.blockSelection || this.blockXLocation == -1)) {
                this.setSelection(Arrays.stream(this.selection).map(sel -> {
                    if (sel.y < event.start || sel.x > event.end) {
                        return sel;
                    }
                    else {
                        return new Point(event.start + event.text.length(), event.start + event.text.length());
                    }
                }).flatMapToInt(p -> IntStream.of(p.x, p.y - p.x)).toArray(), true, false);
                this.showCaret();
            }
            this.notifyListeners(24, event);
            if (this.isListening(3000)) {
                this.notifyListeners(3000, styledTextEvent);
            }
        }
    }
    
    void paintObject(final GC gc, final int x, final int y, final int ascent, final int descent, final StyleRange style, final Bullet bullet, final int bulletIndex) {
        if (this.isListening(3008)) {
            final StyledTextEvent event = new StyledTextEvent(this.content);
            event.gc = gc;
            event.x = x;
            event.y = y;
            event.ascent = ascent;
            event.descent = descent;
            event.style = style;
            event.bullet = bullet;
            event.bulletIndex = bulletIndex;
            this.notifyListeners(3008, event);
        }
    }
    
    public void paste() {
        this.checkWidget();
        final String text = (String)this.getClipboardContent(1);
        if (text != null && text.length() > 0) {
            if (this.blockSelection) {
                final boolean fillWithSpaces = this.isFixedLineHeight() && this.renderer.fixedPitch;
                final int offset = this.insertBlockSelectionText(text, fillWithSpaces);
                this.setCaretOffsets(new int[] { offset }, -1);
                this.clearBlockSelection(true, true);
                this.setCaretLocations();
                return;
            }
            if (this.getSelectionRanges().length / 2 > 1) {
                this.insertMultiSelectionText(text);
                this.setCaretLocations();
                return;
            }
            final Event event = new Event();
            event.start = this.selection[0].x;
            event.end = this.selection[0].y;
            String delimitedText = this.getModelDelimitedText(text);
            if (this.textLimit > 0) {
                final int uneditedTextLength = this.getCharCount() - (this.selection[0].y - this.selection[0].x);
                if (uneditedTextLength + delimitedText.length() > this.textLimit) {
                    final int endIndex = this.textLimit - uneditedTextLength;
                    delimitedText = delimitedText.substring(0, Math.max(endIndex, 0));
                }
            }
            event.text = delimitedText;
            this.sendKeyEvent(event);
        }
    }
    
    private void insertMultiSelectionText(final String text) {
        final String[] blocks = text.split(StyledText.PlatformLineDelimiter);
        final int[] ranges = this.getSelectionRanges();
        for (int i = ranges.length / 2 - 1; i >= 0; --i) {
            final int offset = ranges[2 * i];
            final int length = ranges[2 * i + 1];
            final String toPaste = (blocks.length > i) ? blocks[i] : blocks[blocks.length - 1];
            final Event event = new Event();
            event.start = offset;
            event.end = offset + length;
            event.text = toPaste;
            this.sendKeyEvent(event);
        }
    }
    
    private void pasteOnMiddleClick(final Event event) {
        final String text = (String)this.getClipboardContent(2);
        if (text != null && text.length() > 0) {
            this.doMouseLocationChange(event.x, event.y, false);
            final Event e = new Event();
            e.start = this.selection[0].x;
            e.end = this.selection[0].y;
            e.text = this.getModelDelimitedText(text);
            this.sendKeyEvent(e);
        }
    }
    
    public void print() {
        this.checkWidget();
        final Printer printer = new Printer();
        final StyledTextPrintOptions options = new StyledTextPrintOptions();
        options.printTextForeground = true;
        options.printTextBackground = true;
        options.printTextFontStyle = true;
        options.printLineBackground = true;
        new Printing(this, printer, options).run();
        printer.dispose();
    }
    
    public Runnable print(final Printer printer) {
        this.checkWidget();
        if (printer == null) {
            SWT.error(4);
        }
        final StyledTextPrintOptions options = new StyledTextPrintOptions();
        options.printTextForeground = true;
        options.printTextBackground = true;
        options.printTextFontStyle = true;
        options.printLineBackground = true;
        return this.print(printer, options);
    }
    
    public Runnable print(final Printer printer, final StyledTextPrintOptions options) {
        this.checkWidget();
        if (printer == null || options == null) {
            SWT.error(4);
        }
        return new Printing(this, printer, options);
    }
    
    @Override
    public void redraw() {
        super.redraw();
        final int itemCount = this.getPartialBottomIndex() - this.topIndex + 1;
        this.renderer.reset(this.topIndex, itemCount);
        this.renderer.calculate(this.topIndex, itemCount);
        this.setScrollBars(false);
        this.doMouseLinkCursor();
    }
    
    @Override
    public void redraw(final int x, final int y, final int width, final int height, final boolean all) {
        super.redraw(x, y, width, height, all);
        if (height > 0) {
            final int firstLine = this.getLineIndex(y);
            final int lastLine = this.getLineIndex(y + height);
            this.resetCache(firstLine, lastLine - firstLine + 1);
            this.doMouseLinkCursor();
        }
    }
    
    void redrawLines(int startLine, final int lineCount, final boolean bottomChanged) {
        int endLine = startLine + lineCount - 1;
        final int partialBottomIndex = this.getPartialBottomIndex();
        final int partialTopIndex = this.getPartialTopIndex();
        if (startLine > partialBottomIndex || endLine < partialTopIndex) {
            return;
        }
        if (startLine < partialTopIndex) {
            startLine = partialTopIndex;
        }
        if (endLine > partialBottomIndex) {
            endLine = partialBottomIndex;
        }
        final int redrawTop = this.getLinePixel(startLine);
        int redrawBottom = this.getLinePixel(endLine + 1);
        if (bottomChanged) {
            redrawBottom = this.clientAreaHeight - this.bottomMargin;
        }
        final int redrawWidth = this.clientAreaWidth - this.leftMargin - this.rightMargin;
        super.redraw(this.leftMargin, redrawTop, redrawWidth, redrawBottom - redrawTop, true);
    }
    
    void redrawLinesBullet(final int[] redrawLines) {
        if (redrawLines == null) {
            return;
        }
        final int topIndex = this.getPartialTopIndex();
        final int bottomIndex = this.getPartialBottomIndex();
        for (final int redrawLine : redrawLines) {
            final int lineIndex = redrawLine;
            if (topIndex <= lineIndex && lineIndex <= bottomIndex) {
                int width = -1;
                final Bullet bullet = this.renderer.getLineBullet(lineIndex, null);
                if (bullet != null) {
                    final StyleRange style = bullet.style;
                    final GlyphMetrics metrics = style.metrics;
                    width = metrics.width;
                }
                if (width == -1) {
                    width = this.getClientArea().width;
                }
                final int height = this.renderer.getLineHeight(lineIndex);
                final int y = this.getLinePixel(lineIndex);
                super.redraw(0, y, width, height, false);
            }
        }
    }
    
    void redrawMargins(final int oldHeight, final int oldWidth) {
        if (oldWidth != this.clientAreaWidth && this.rightMargin > 0) {
            final int x = ((oldWidth < this.clientAreaWidth) ? oldWidth : this.clientAreaWidth) - this.rightMargin;
            super.redraw(x, 0, this.rightMargin, oldHeight, false);
        }
        if (oldHeight != this.clientAreaHeight && this.bottomMargin > 0) {
            final int y = ((oldHeight < this.clientAreaHeight) ? oldHeight : this.clientAreaHeight) - this.bottomMargin;
            super.redraw(0, y, oldWidth, this.bottomMargin, false);
        }
    }
    
    public void redrawRange(final int start, final int length, final boolean clearBackground) {
        this.checkWidget();
        final int end = start + length;
        final int contentLength = this.content.getCharCount();
        if (start > end || start < 0 || end > contentLength) {
            SWT.error(6);
        }
        final int firstLine = this.content.getLineAtOffset(start);
        final int lastLine = this.content.getLineAtOffset(end);
        this.resetCache(firstLine, lastLine - firstLine + 1);
        this.internalRedrawRange(start, length);
        this.doMouseLinkCursor();
    }
    
    public void removeBidiSegmentListener(final BidiSegmentListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(3007, (SWTEventListener)listener);
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void removeCaretListener(final CaretListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(3011, (SWTEventListener)listener);
    }
    
    public void removeExtendedModifyListener(final ExtendedModifyListener extendedModifyListener) {
        this.checkWidget();
        if (extendedModifyListener == null) {
            SWT.error(4);
        }
        this.removeListener(3000, (SWTEventListener)extendedModifyListener);
    }
    
    public void removeLineBackgroundListener(final LineBackgroundListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(3001, (SWTEventListener)listener);
    }
    
    public void removeLineStyleListener(final LineStyleListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(3002, (SWTEventListener)listener);
        this.setCaretLocations();
    }
    
    public void removeModifyListener(final ModifyListener modifyListener) {
        this.checkWidget();
        if (modifyListener == null) {
            SWT.error(4);
        }
        this.removeListener(24, modifyListener);
    }
    
    public void removePaintObjectListener(final PaintObjectListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(3008, (SWTEventListener)listener);
    }
    
    public void removeSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(13, listener);
    }
    
    public void removeVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            SWT.error(4);
        }
        this.removeListener(25, verifyListener);
    }
    
    public void removeVerifyKeyListener(final VerifyKeyListener listener) {
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(3005, listener);
    }
    
    public void removeWordMovementListener(final MovementListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(3009, (SWTEventListener)listener);
        this.removeListener(3010, (SWTEventListener)listener);
    }
    
    public void replaceStyleRanges(final int start, final int length, final StyleRange[] ranges) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (ranges == null) {
            SWT.error(4);
        }
        this.setStyleRanges(start, length, null, ranges, false);
    }
    
    public void replaceTextRange(final int start, final int length, final String text) {
        this.checkWidget();
        if (text == null) {
            SWT.error(4);
        }
        final int contentLength = this.getCharCount();
        final int end = start + length;
        if (start > end || start < 0 || end > contentLength) {
            SWT.error(6);
        }
        final Event event = new Event();
        event.start = start;
        event.end = end;
        event.text = text;
        this.modifyContent(event, false);
    }
    
    void reset() {
        final ScrollBar verticalBar = this.getVerticalBar();
        final ScrollBar horizontalBar = this.getHorizontalBar();
        this.setCaretOffsets(new int[] { 0 }, -1);
        this.topIndex = 0;
        this.topIndexY = 0;
        this.verticalScrollOffset = 0;
        this.horizontalScrollOffset = 0;
        this.resetSelection();
        this.renderer.setContent(this.content);
        if (verticalBar != null) {
            verticalBar.setSelection(0);
        }
        if (horizontalBar != null) {
            horizontalBar.setSelection(0);
        }
        this.resetCache(0, 0);
        this.setCaretLocations();
        super.redraw();
    }
    
    void resetBidiData() {
        this.resetCache(this.caretDirection = 0, this.content.getLineCount());
        this.setCaretLocations();
        this.keyActionMap.clear();
        this.createKeyBindings();
        super.redraw();
    }
    
    void resetCache(final SortedSet<Integer> lines) {
        if (lines == null || lines.isEmpty()) {
            return;
        }
        final int maxLineIndex = this.renderer.maxWidthLineIndex;
        this.renderer.reset(lines);
        this.renderer.calculateClientArea();
        if (0 <= maxLineIndex && maxLineIndex < this.content.getLineCount()) {
            this.renderer.calculate(maxLineIndex, 1);
        }
        this.setScrollBars(true);
        if (!this.isFixedLineHeight()) {
            if (this.topIndex > lines.iterator().next()) {
                this.verticalScrollOffset = -1;
            }
            this.renderer.calculateIdle();
        }
    }
    
    void resetCache(final int firstLine, final int count) {
        final int maxLineIndex = this.renderer.maxWidthLineIndex;
        this.renderer.reset(firstLine, count);
        this.renderer.calculateClientArea();
        if (0 <= maxLineIndex && maxLineIndex < this.content.getLineCount()) {
            this.renderer.calculate(maxLineIndex, 1);
        }
        this.setScrollBars(true);
        if (!this.isFixedLineHeight()) {
            if (this.topIndex > firstLine) {
                this.verticalScrollOffset = -1;
            }
            this.renderer.calculateIdle();
        }
    }
    
    void resetSelection() {
        this.selection = Arrays.stream(this.caretOffsets).mapToObj(offset -> new Point(offset, offset)).toArray(Point[]::new);
        this.selectionAnchors = Arrays.copyOf(this.caretOffsets, this.caretOffsets.length);
        this.sendAccessibleTextCaretMoved();
    }
    
    @Override
    public void scroll(final int destX, final int destY, final int x, final int y, final int width, final int height, final boolean all) {
        super.scroll(destX, destY, x, y, width, height, false);
        if (all) {
            final int deltaX = destX - x;
            final int deltaY = destY - y;
            for (final Control child : this.getChildren()) {
                final Rectangle rect = child.getBounds();
                child.setLocation(rect.x + deltaX, rect.y + deltaY);
            }
        }
    }
    
    boolean scrollHorizontal(final int pixels, final boolean adjustScrollBar) {
        if (pixels == 0) {
            return false;
        }
        if (this.wordWrap) {
            return false;
        }
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (horizontalBar != null && adjustScrollBar) {
            horizontalBar.setSelection(this.horizontalScrollOffset + pixels);
        }
        final int scrollHeight = this.clientAreaHeight - this.topMargin - this.bottomMargin;
        if (pixels > 0) {
            final int sourceX = this.leftMargin + pixels;
            final int scrollWidth = this.clientAreaWidth - sourceX - this.rightMargin;
            if (scrollWidth > 0) {
                this.scroll(this.leftMargin, this.topMargin, sourceX, this.topMargin, scrollWidth, scrollHeight, true);
            }
            if (sourceX > scrollWidth) {
                super.redraw(this.leftMargin + scrollWidth, this.topMargin, pixels - scrollWidth, scrollHeight, true);
            }
        }
        else {
            final int destinationX = this.leftMargin - pixels;
            final int scrollWidth = this.clientAreaWidth - destinationX - this.rightMargin;
            if (scrollWidth > 0) {
                this.scroll(destinationX, this.topMargin, this.leftMargin, this.topMargin, scrollWidth, scrollHeight, true);
            }
            if (destinationX > scrollWidth) {
                super.redraw(this.leftMargin + scrollWidth, this.topMargin, -pixels - scrollWidth, scrollHeight, true);
            }
        }
        this.horizontalScrollOffset += pixels;
        this.setCaretLocations();
        return true;
    }
    
    boolean scrollVertical(final int pixels, final boolean adjustScrollBar) {
        if (pixels == 0) {
            return false;
        }
        if (this.verticalScrollOffset != -1) {
            final ScrollBar verticalBar = this.getVerticalBar();
            if (verticalBar != null && adjustScrollBar) {
                verticalBar.setSelection(this.verticalScrollOffset + pixels);
            }
            int deltaY = 0;
            if (pixels > 0) {
                final int sourceY = this.topMargin + pixels;
                final int scrollHeight = this.clientAreaHeight - sourceY - this.bottomMargin;
                if (scrollHeight > 0) {
                    deltaY = -pixels;
                }
            }
            else {
                final int destinationY = this.topMargin - pixels;
                final int scrollHeight = this.clientAreaHeight - destinationY - this.bottomMargin;
                if (scrollHeight > 0) {
                    deltaY = -pixels;
                }
            }
            final Control[] children3;
            final Control[] array;
            final Control[] children2 = array = (children3 = this.getChildren());
            for (final Control child : array) {
                final Rectangle rect = child.getBounds();
                child.setLocation(rect.x, rect.y + deltaY);
            }
            this.verticalScrollOffset += pixels;
            this.calculateTopIndex(pixels);
            super.redraw();
        }
        else {
            this.calculateTopIndex(pixels);
            super.redraw();
        }
        this.setCaretLocations();
        return true;
    }
    
    void scrollText(final int srcY, final int destY) {
        if (srcY == destY) {
            return;
        }
        final int deltaY = destY - srcY;
        final int scrollWidth = this.clientAreaWidth - this.leftMargin - this.rightMargin;
        int scrollHeight;
        if (deltaY > 0) {
            scrollHeight = this.clientAreaHeight - srcY - this.bottomMargin;
        }
        else {
            scrollHeight = this.clientAreaHeight - destY - this.bottomMargin;
        }
        this.scroll(this.leftMargin, destY, this.leftMargin, srcY, scrollWidth, scrollHeight, true);
        if (0 < srcY + scrollHeight && this.topMargin > srcY) {
            super.redraw(this.leftMargin, deltaY, scrollWidth, this.topMargin, false);
        }
        if (0 < destY + scrollHeight && this.topMargin > destY) {
            super.redraw(this.leftMargin, 0, scrollWidth, this.topMargin, false);
        }
        if (this.clientAreaHeight - this.bottomMargin < srcY + scrollHeight && this.clientAreaHeight > srcY) {
            super.redraw(this.leftMargin, this.clientAreaHeight - this.bottomMargin + deltaY, scrollWidth, this.bottomMargin, false);
        }
        if (this.clientAreaHeight - this.bottomMargin < destY + scrollHeight && this.clientAreaHeight > destY) {
            super.redraw(this.leftMargin, this.clientAreaHeight - this.bottomMargin, scrollWidth, this.bottomMargin, false);
        }
    }
    
    void sendAccessibleTextCaretMoved() {
        if (Arrays.stream(this.caretOffsets).noneMatch(caretOffset -> caretOffset == this.accCaretOffset)) {
            this.accCaretOffset = this.caretOffsets[0];
            this.getAccessible().textCaretMoved(this.caretOffsets[0]);
        }
    }
    
    void sendAccessibleTextChanged(final int start, final int newCharCount, final int replaceCharCount) {
        final Accessible accessible = this.getAccessible();
        if (replaceCharCount != 0) {
            accessible.textChanged(1, start, replaceCharCount);
        }
        if (newCharCount != 0) {
            accessible.textChanged(0, start, newCharCount);
        }
    }
    
    public void selectAll() {
        this.checkWidget();
        if (this.blockSelection) {
            this.renderer.calculate(0, this.content.getLineCount());
            this.setScrollBars(false);
            final int verticalScrollOffset = this.getVerticalScrollOffset();
            final int left = this.leftMargin - this.horizontalScrollOffset;
            final int top = this.topMargin - verticalScrollOffset;
            final int right = this.renderer.getWidth() - this.rightMargin - this.horizontalScrollOffset;
            final int bottom = this.renderer.getHeight() - this.bottomMargin - verticalScrollOffset;
            this.setBlockSelectionLocation(left, top, right, bottom, false);
            return;
        }
        this.setSelection(0, Math.max(this.getCharCount(), 0));
    }
    
    void sendKeyEvent(final Event event) {
        if (this.editable) {
            this.modifyContent(event, true);
        }
    }
    
    StyledTextEvent sendLineEvent(final int eventType, final int lineOffset, final String line) {
        StyledTextEvent event = null;
        if (this.isListening(eventType)) {
            event = new StyledTextEvent(this.content);
            event.detail = lineOffset;
            event.text = line;
            event.alignment = this.alignment;
            event.indent = this.indent;
            event.wrapIndent = this.wrapIndent;
            event.justify = this.justify;
            this.notifyListeners(eventType, event);
        }
        return event;
    }
    
    void sendSelectionEvent() {
        this.getAccessible().textSelectionChanged();
        final Event event = new Event();
        event.x = this.selection[0].x;
        event.y = this.selection[this.selection.length - 1].y;
        this.notifyListeners(13, event);
    }
    
    int sendTextEvent(final int left, final int right, final int lineIndex, final String text, boolean fillWithSpaces) {
        int lineWidth = 0;
        final StringBuilder buffer = new StringBuilder();
        int start;
        int end;
        if (lineIndex < this.content.getLineCount()) {
            final int[] trailing = { 0 };
            start = this.getOffsetAtPoint(left, this.getLinePixel(lineIndex), trailing, true);
            if (start == -1) {
                final int lineOffset = this.content.getOffsetAtLine(lineIndex);
                final int lineLegth = this.content.getLine(lineIndex).length();
                start = (end = lineOffset + lineLegth);
                if (fillWithSpaces) {
                    final TextLayout layout = this.renderer.getTextLayout(lineIndex);
                    lineWidth = layout.getBounds().width;
                    this.renderer.disposeTextLayout(layout);
                }
            }
            else {
                start += trailing[0];
                end = ((left == right) ? start : this.getOffsetAtPoint(right, 0, lineIndex, null));
                fillWithSpaces = false;
            }
        }
        else {
            start = (end = this.content.getCharCount());
            buffer.append(this.content.getLineDelimiter());
        }
        if (start > end) {
            final int temp = start;
            start = end;
            end = temp;
        }
        if (fillWithSpaces) {
            final int spacesWidth = left - lineWidth + this.horizontalScrollOffset - this.leftMargin;
            for (int spacesCount = spacesWidth / this.renderer.averageCharWidth, i = 0; i < spacesCount; ++i) {
                buffer.append(' ');
            }
        }
        buffer.append(text);
        final Event event = new Event();
        event.start = start;
        event.end = end;
        event.text = buffer.toString();
        this.sendKeyEvent(event);
        return event.start + event.text.length();
    }
    
    int sendWordBoundaryEvent(final int eventType, final int movement, int offset, final int newOffset, final String lineText, final int lineOffset) {
        if (this.isListening(eventType)) {
            final StyledTextEvent event = new StyledTextEvent(this.content);
            event.detail = lineOffset;
            event.text = lineText;
            event.count = movement;
            event.start = offset;
            event.end = newOffset;
            this.notifyListeners(eventType, event);
            offset = event.end;
            if (offset != newOffset) {
                final int length = this.getCharCount();
                if (offset < 0) {
                    offset = 0;
                }
                else if (offset > length) {
                    offset = length;
                }
                else if (this.isLineDelimiter(offset)) {
                    SWT.error(5);
                }
            }
            return offset;
        }
        return newOffset;
    }
    
    void setAlignment() {
        if ((this.getStyle() & 0x4) == 0x0) {
            return;
        }
        final int alignment = this.renderer.getLineAlignment(0, this.alignment);
        int newAlignmentMargin = 0;
        if (alignment != 16384) {
            this.renderer.calculate(0, 1);
            final int width = this.renderer.getWidth() - this.alignmentMargin;
            newAlignmentMargin = this.clientAreaWidth - width;
            if (newAlignmentMargin < 0) {
                newAlignmentMargin = 0;
            }
            if (alignment == 16777216) {
                newAlignmentMargin /= 2;
            }
        }
        if (this.alignmentMargin != newAlignmentMargin) {
            this.leftMargin -= this.alignmentMargin;
            this.leftMargin += newAlignmentMargin;
            this.alignmentMargin = newAlignmentMargin;
            this.resetCache(0, 1);
            this.setCaretLocations();
            super.redraw();
        }
    }
    
    public void setAlignment(int alignment) {
        this.checkWidget();
        alignment &= 0x1024000;
        if (alignment == 0 || this.alignment == alignment) {
            return;
        }
        this.alignment = alignment;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        this.setAlignment();
        super.redraw();
    }
    
    public void setAlwaysShowScrollBars(final boolean show) {
        this.checkWidget();
        if (show == this.alwaysShowScroll) {
            return;
        }
        this.alwaysShowScroll = show;
        this.setScrollBars(true);
    }
    
    @Override
    public void setBackground(Color color) {
        this.checkWidget();
        boolean backgroundDisabled = false;
        if (!this.enabled && color == null && this.background != null) {
            final Color disabledBg = this.getDisplay().getSystemColor(38);
            if (this.background.equals(disabledBg)) {
                return;
            }
            color = new Color(disabledBg.getRGBA());
            backgroundDisabled = true;
        }
        this.customBackground = (color != null && !this.insideSetEnableCall && !backgroundDisabled);
        super.setBackground(this.background = color);
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setBlockSelection(final boolean blockSelection) {
        this.checkWidget();
        if ((this.getStyle() & 0x4) != 0x0) {
            return;
        }
        if (blockSelection == this.blockSelection) {
            return;
        }
        if (this.wordWrap) {
            return;
        }
        this.blockSelection = blockSelection;
        if (this.cursor == null) {
            final Display display = this.getDisplay();
            final int type = blockSelection ? 2 : 19;
            super.setCursor(display.getSystemCursor(type));
        }
        if (blockSelection) {
            final int start = this.selection[0].x;
            final int end = this.selection[0].y;
            if (start != end) {
                this.setBlockSelectionOffset(start, end, false);
            }
        }
        else {
            this.clearBlockSelection(false, false);
        }
    }
    
    public void setBlockSelectionBounds(final Rectangle rect) {
        this.checkWidget();
        if (rect == null) {
            SWT.error(4);
        }
        this.setBlockSelectionBounds(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void setBlockSelectionBounds(int x, int y, final int width, final int height) {
        this.checkWidget();
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        if (!this.blockSelection) {
            x -= this.horizontalScrollOffset;
            y -= verticalScrollOffset;
            final int start = this.getOffsetAtPoint(x, y, null);
            final int end = this.getOffsetAtPoint(x + width - 1, y + height - 1, null);
            this.setSelection(new int[] { start, end - start }, false, false);
            this.setCaretLocations();
            return;
        }
        final int minY = this.topMargin;
        final int minX = this.leftMargin;
        final int maxY = this.renderer.getHeight() - this.bottomMargin;
        final int maxX = Math.max(this.clientAreaWidth, this.renderer.getWidth()) - this.rightMargin;
        int anchorX = Math.max(minX, Math.min(maxX, x)) - this.horizontalScrollOffset;
        final int anchorY = Math.max(minY, Math.min(maxY, y)) - verticalScrollOffset;
        int locationX = Math.max(minX, Math.min(maxX, x + width)) - this.horizontalScrollOffset;
        final int locationY = Math.max(minY, Math.min(maxY, y + height - 1)) - verticalScrollOffset;
        if (this.isFixedLineHeight() && this.renderer.fixedPitch) {
            final int avg = this.renderer.averageCharWidth;
            anchorX = (anchorX - this.leftMargin + this.horizontalScrollOffset) / avg * avg + this.leftMargin - this.horizontalScrollOffset;
            locationX = (locationX + avg / 2 - this.leftMargin + this.horizontalScrollOffset) / avg * avg + this.leftMargin - this.horizontalScrollOffset;
        }
        this.setBlockSelectionLocation(anchorX, anchorY, locationX, locationY, false);
    }
    
    void setBlockSelectionLocation(final int x, final int y, final boolean sendEvent) {
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        this.blockXLocation = x + this.horizontalScrollOffset;
        this.blockYLocation = y + verticalScrollOffset;
        final int[] alignment = { 0 };
        final int offset = this.getOffsetAtPoint(x, y, alignment);
        this.setCaretOffsets(new int[] { offset }, alignment[0]);
        if (this.blockXAnchor == -1) {
            this.blockXAnchor = this.blockXLocation;
            this.blockYAnchor = this.blockYLocation;
            this.selectionAnchors[0] = this.caretOffsets[0];
        }
        this.doBlockSelection(sendEvent);
    }
    
    void setBlockSelectionLocation(final int anchorX, final int anchorY, final int x, final int y, final boolean sendEvent) {
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        this.blockXAnchor = anchorX + this.horizontalScrollOffset;
        this.blockYAnchor = anchorY + verticalScrollOffset;
        this.selectionAnchors[0] = this.getOffsetAtPoint(anchorX, anchorY, null);
        this.setBlockSelectionLocation(x, y, sendEvent);
    }
    
    void setBlockSelectionOffset(final int offset, final boolean sendEvent) {
        final Point point = this.getPointAtOffset(offset);
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        this.blockXLocation = point.x + this.horizontalScrollOffset;
        this.blockYLocation = point.y + verticalScrollOffset;
        this.setCaretOffsets(new int[] { offset }, -1);
        if (this.blockXAnchor == -1) {
            this.blockXAnchor = this.blockXLocation;
            this.blockYAnchor = this.blockYLocation;
            this.selectionAnchors[0] = this.caretOffsets[0];
        }
        this.doBlockSelection(sendEvent);
    }
    
    void setBlockSelectionOffset(final int anchorOffset, final int offset, final boolean sendEvent) {
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        final Point anchorPoint = this.getPointAtOffset(anchorOffset);
        this.blockXAnchor = anchorPoint.x + this.horizontalScrollOffset;
        this.blockYAnchor = anchorPoint.y + verticalScrollOffset;
        this.selectionAnchors[0] = anchorOffset;
        this.setBlockSelectionOffset(offset, sendEvent);
    }
    
    @Override
    public void setCaret(final Caret caret) {
        this.checkWidget();
        super.setCaret(caret);
        this.caretDirection = 0;
        if (caret != null) {
            this.setCaretLocations();
            if (this.carets != null) {
                for (int i = 1; i < this.carets.length; ++i) {
                    this.carets[i].dispose();
                }
            }
            this.carets = new Caret[] { caret };
        }
        else {
            this.carets = null;
        }
    }
    
    @Deprecated
    public void setBidiColoring(final boolean mode) {
        this.checkWidget();
        this.bidiColoring = mode;
    }
    
    public void setBottomMargin(final int bottomMargin) {
        this.checkWidget();
        this.setMargins(this.getLeftMargin(), this.topMargin, this.rightMargin, bottomMargin);
    }
    
    void setCaretLocations() {
        final Point[] newCaretPos = Arrays.stream(this.caretOffsets).mapToObj((IntFunction<?>)this::getPointAtOffset).toArray(Point[]::new);
        this.setCaretLocations(newCaretPos, this.getCaretDirection());
    }
    
    void setCaretLocations(final Point[] locations, int direction) {
        final Caret firstCaret = this.getCaret();
        if (firstCaret != null) {
            if (this.carets == null || this.carets.length == 0) {
                this.carets = new Caret[] { firstCaret };
            }
            final boolean isDefaultCaret = firstCaret == this.defaultCaret;
            if (locations.length > this.carets.length) {
                final int formerCaretCount = this.carets.length;
                this.carets = Arrays.copyOf(this.carets, locations.length);
                for (int i = formerCaretCount; i < this.carets.length; ++i) {
                    (this.carets[i] = new Caret(this, firstCaret.getStyle())).setImage(firstCaret.getImage());
                    this.carets[i].setFont(firstCaret.getFont());
                    this.carets[i].setSize(firstCaret.getSize());
                }
            }
            else if (locations.length < this.carets.length) {
                for (int j = locations.length; j < this.carets.length; ++j) {
                    this.carets[j].dispose();
                }
                this.carets = Arrays.copyOf(this.carets, locations.length);
            }
            for (int j = Math.min(this.caretOffsets.length, locations.length) - 1; j >= 0; --j) {
                final Caret caret = this.carets[j];
                final int caretOffset = this.caretOffsets[j];
                final Point location = locations[j];
                final StyleRange styleAtOffset = (this.content.getCharCount() > 0) ? ((caretOffset < this.content.getCharCount()) ? this.getStyleRangeAtOffset(caretOffset) : this.getStyleRangeAtOffset(this.content.getCharCount() - 1)) : null;
                final int caretLine = this.content.getLineAtOffset(caretOffset);
                int graphicalLineHeight = this.getLineHeight();
                final int lineStartOffset;
                int graphicalLineFirstOffset = lineStartOffset = this.getOffsetAtLine(caretLine);
                final int lineEndOffset;
                int graphicalLineLastOffset = lineEndOffset = lineStartOffset + this.getLine(caretLine).length();
                if (caretLine < this.getLineCount() && this.renderer.getLineHeight(caretLine) != this.getLineHeight()) {
                    graphicalLineHeight = this.getLineHeight(caretOffset);
                    final Rectangle characterBounds = this.getBoundsAtOffset(caretOffset);
                    graphicalLineFirstOffset = this.getOffsetAtPoint(new Point(this.leftMargin, characterBounds.y));
                    graphicalLineLastOffset = this.getOffsetAtPoint(new Point(this.leftMargin, characterBounds.y + graphicalLineHeight)) - 1;
                    if (graphicalLineLastOffset < graphicalLineFirstOffset) {
                        graphicalLineLastOffset = this.getCharCount();
                    }
                }
                int caretHeight = this.getLineHeight();
                boolean isTextAlignedAtBottom = true;
                if (graphicalLineFirstOffset >= 0) {
                    for (final StyleRange style : this.getStyleRanges(graphicalLineFirstOffset, graphicalLineLastOffset - graphicalLineFirstOffset)) {
                        isTextAlignedAtBottom &= ((style.font == null || Objects.equals(style.font, this.getFont())) && style.rise >= 0 && (style.metrics == null || style.metrics.descent <= 0));
                    }
                }
                if (!isTextAlignedAtBottom || (styleAtOffset != null && styleAtOffset.isVariableHeight())) {
                    if (isDefaultCaret) {
                        direction = -1;
                        caretHeight = graphicalLineHeight;
                    }
                    else {
                        caretHeight = caret.getSize().y;
                    }
                }
                if (isTextAlignedAtBottom && caretHeight < graphicalLineHeight) {
                    final Point point3;
                    final Point point = point3 = location;
                    point3.y += graphicalLineHeight - caretHeight;
                }
                int imageDirection = direction;
                if (this.isMirrored()) {
                    if (imageDirection == 16384) {
                        imageDirection = 131072;
                    }
                    else if (imageDirection == 131072) {
                        imageDirection = 16384;
                    }
                }
                if (isDefaultCaret && imageDirection == 131072) {
                    final Point point4;
                    final Point point2 = point4 = location;
                    point4.x -= caret.getSize().x - 1;
                }
                if (isDefaultCaret) {
                    caret.setBounds(location.x, location.y, this.caretWidth, caretHeight);
                }
                else {
                    caret.setLocation(location);
                }
                if (direction != this.caretDirection) {
                    this.caretDirection = direction;
                    if (isDefaultCaret) {
                        if (imageDirection == -1) {
                            this.defaultCaret.setImage(null);
                        }
                        else if (imageDirection == 16384) {
                            this.defaultCaret.setImage(this.leftCaretBitmap);
                        }
                        else if (imageDirection == 131072) {
                            this.defaultCaret.setImage(this.rightCaretBitmap);
                        }
                    }
                    if (this.caretDirection == 16384) {
                        BidiUtil.setKeyboardLanguage(0);
                    }
                    else if (this.caretDirection == 131072) {
                        BidiUtil.setKeyboardLanguage(1);
                    }
                }
            }
            this.updateCaretVisibility();
            super.redraw();
        }
        this.columnX = locations[0].x;
    }
    
    public void setCaretOffset(int offset) {
        this.checkWidget();
        final int length = this.getCharCount();
        if (length > 0 && !Arrays.equals(this.caretOffsets, new int[] { offset })) {
            if (offset < 0) {
                offset = 0;
            }
            else if (offset > length) {
                offset = length;
            }
            else if (this.isLineDelimiter(offset)) {
                SWT.error(5);
            }
            this.setCaretOffsets(new int[] { offset }, 0);
            if (this.blockSelection) {
                this.clearBlockSelection(true, false);
            }
            else {
                this.clearSelection(false);
            }
        }
        this.setCaretLocations();
    }
    
    void setCaretOffsets(int[] newOffsets, final int alignment) {
        if (newOffsets.length > 1) {
            newOffsets = Arrays.stream(newOffsets).distinct().sorted().toArray();
        }
        if (!Arrays.equals(this.caretOffsets, newOffsets)) {
            this.caretOffsets = newOffsets;
            if (this.isListening(3011)) {
                final StyledTextEvent event = new StyledTextEvent(this.content);
                event.end = this.caretOffsets[this.caretOffsets.length - 1];
                this.notifyListeners(3011, event);
            }
        }
        if (alignment != -1) {
            this.caretAlignment = alignment;
        }
    }
    
    void setClipboardContent(final int start, final int length, final int clipboardType) throws SWTError {
        if (clipboardType == 2 && !StyledText.IS_GTK) {
            return;
        }
        final TextTransfer plainTextTransfer = TextTransfer.getInstance();
        final TextWriter plainTextWriter = new TextWriter(start, length);
        final String plainText = this.getPlatformDelimitedText(plainTextWriter);
        Object[] data;
        Transfer[] types;
        if (clipboardType == 2) {
            data = new Object[] { plainText };
            types = new Transfer[] { plainTextTransfer };
        }
        else {
            final RTFTransfer rtfTransfer = RTFTransfer.getInstance();
            final RTFWriter rtfWriter = new RTFWriter(start, length);
            final String rtfText = this.getPlatformDelimitedText(rtfWriter);
            data = new Object[] { rtfText, plainText };
            types = new Transfer[] { rtfTransfer, plainTextTransfer };
        }
        this.clipboard.setContents(data, types, clipboardType);
    }
    
    public void setContent(final StyledTextContent newContent) {
        this.checkWidget();
        if (newContent == null) {
            SWT.error(4);
        }
        if (this.content != null) {
            this.content.removeTextChangeListener(this.textChangeListener);
        }
        (this.content = newContent).addTextChangeListener(this.textChangeListener);
        this.reset();
    }
    
    @Override
    public void setCursor(final Cursor cursor) {
        this.checkWidget();
        if (cursor != null && cursor.isDisposed()) {
            SWT.error(5);
        }
        if ((this.cursor = cursor) == null) {
            final Display display = this.getDisplay();
            final int type = this.blockSelection ? 2 : 19;
            super.setCursor(display.getSystemCursor(type));
        }
        else {
            super.setCursor(cursor);
        }
    }
    
    public void setDoubleClickEnabled(final boolean enable) {
        this.checkWidget();
        this.doubleClickEnabled = enable;
    }
    
    @Override
    public void setDragDetect(final boolean dragDetect) {
        this.checkWidget();
        this.dragDetect = dragDetect;
    }
    
    public void setEditable(final boolean editable) {
        this.checkWidget();
        this.editable = editable;
    }
    
    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        final Display display = this.getDisplay();
        this.enabled = enabled;
        this.insideSetEnableCall = true;
        try {
            if (enabled && this.editable) {
                if (!this.customBackground) {
                    this.setBackground(display.getSystemColor(25));
                }
                if (!this.customForeground) {
                    this.setForeground(display.getSystemColor(24));
                }
            }
            else if (!enabled) {
                if (!this.customBackground) {
                    this.setBackground(display.getSystemColor(38));
                }
                if (!this.customForeground) {
                    this.setForeground(display.getSystemColor(39));
                }
            }
            else if (!this.editable) {
                if (!this.customBackground) {
                    this.setBackground(display.getSystemColor(38));
                }
                if (!this.customForeground) {
                    this.setForeground(display.getSystemColor(24));
                }
            }
        }
        finally {
            this.insideSetEnableCall = false;
        }
    }
    
    @Override
    public boolean setFocus() {
        final boolean focusGained = super.setFocus();
        if (focusGained && this.hasMultipleCarets()) {
            this.setCaretLocations();
        }
        return focusGained;
    }
    
    private boolean hasMultipleCarets() {
        return this.carets != null && this.carets.length > 1;
    }
    
    @Override
    public void setFont(final Font font) {
        this.checkWidget();
        final int oldLineHeight = this.renderer.getLineHeight();
        super.setFont(font);
        this.renderer.setFont(this.getFont(), this.tabLength);
        if (this.isFixedLineHeight()) {
            final int lineHeight = this.renderer.getLineHeight();
            if (lineHeight != oldLineHeight) {
                final int vscroll = this.getVerticalScrollOffset() * lineHeight / oldLineHeight - this.getVerticalScrollOffset();
                this.scrollVertical(vscroll, true);
            }
        }
        this.resetCache(0, this.content.getLineCount());
        this.claimBottomFreeSpace();
        this.calculateScrollBars();
        if (this.isBidiCaret()) {
            this.createCaretBitmaps();
        }
        this.caretDirection = 0;
        this.setCaretLocations();
        super.redraw();
    }
    
    @Override
    public void setForeground(Color color) {
        this.checkWidget();
        boolean foregroundDisabled = false;
        if (!this.enabled && color == null && this.foreground != null) {
            final Color disabledFg = this.getDisplay().getSystemColor(39);
            if (this.foreground.equals(disabledFg)) {
                return;
            }
            color = new Color(disabledFg.getRGBA());
            foregroundDisabled = true;
        }
        this.customForeground = (color != null && !this.insideSetEnableCall && !foregroundDisabled);
        super.setForeground(this.foreground = color);
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setHorizontalIndex(int offset) {
        this.checkWidget();
        if (this.getCharCount() == 0) {
            return;
        }
        if (offset < 0) {
            offset = 0;
        }
        offset *= this.getHorizontalIncrement();
        if (this.clientAreaWidth > 0) {
            final int width = this.renderer.getWidth();
            if (offset > width - this.clientAreaWidth) {
                offset = Math.max(0, width - this.clientAreaWidth);
            }
        }
        this.scrollHorizontal(offset - this.horizontalScrollOffset, true);
    }
    
    public void setHorizontalPixel(int pixel) {
        this.checkWidget();
        if (this.getCharCount() == 0) {
            return;
        }
        if (pixel < 0) {
            pixel = 0;
        }
        if (this.clientAreaWidth > 0) {
            final int width = this.renderer.getWidth();
            if (pixel > width - this.clientAreaWidth) {
                pixel = Math.max(0, width - this.clientAreaWidth);
            }
        }
        this.scrollHorizontal(pixel - this.horizontalScrollOffset, true);
    }
    
    public void setIndent(final int indent) {
        this.checkWidget();
        if (this.indent == indent || indent < 0) {
            return;
        }
        this.indent = indent;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setJustify(final boolean justify) {
        this.checkWidget();
        if (this.justify == justify) {
            return;
        }
        this.justify = justify;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setKeyBinding(final int key, final int action) {
        this.checkWidget();
        final int modifierValue = key & SWT.MODIFIER_MASK;
        final int keyInt = key & 0x100FFFF;
        final char keyChar = (char)keyInt;
        if (Character.isDefined(keyInt) && Character.isLetter(keyChar)) {
            char ch = Character.toUpperCase(keyChar);
            int newKey = ch | modifierValue;
            if (action == 0) {
                this.keyActionMap.remove(newKey);
            }
            else {
                this.keyActionMap.put(newKey, action);
            }
            ch = Character.toLowerCase(keyChar);
            newKey = (ch | modifierValue);
            if (action == 0) {
                this.keyActionMap.remove(newKey);
            }
            else {
                this.keyActionMap.put(newKey, action);
            }
        }
        else if (action == 0) {
            this.keyActionMap.remove(key);
        }
        else {
            this.keyActionMap.put(key, action);
        }
    }
    
    public void setLeftMargin(final int leftMargin) {
        this.checkWidget();
        this.setMargins(leftMargin, this.topMargin, this.rightMargin, this.bottomMargin);
    }
    
    public void setLineAlignment(final int startLine, final int lineCount, final int alignment) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (startLine < 0 || startLine + lineCount > this.content.getLineCount()) {
            SWT.error(5);
        }
        this.renderer.setLineAlignment(startLine, lineCount, alignment);
        this.resetCache(startLine, lineCount);
        this.redrawLines(startLine, lineCount, false);
        final IntStream stream = Arrays.stream(this.caretOffsets);
        final StyledTextContent content = this.content;
        Objects.requireNonNull(content);
        if (stream.map(content::getLineAtOffset).anyMatch(caretLine -> startLine <= caretLine && caretLine < startLine + lineCount)) {
            this.setCaretLocations();
        }
        this.setAlignment();
    }
    
    public void setLineBackground(final int startLine, final int lineCount, final Color background) {
        this.checkWidget();
        if (this.isListening(3001)) {
            return;
        }
        if (startLine < 0 || startLine + lineCount > this.content.getLineCount()) {
            SWT.error(5);
        }
        if (background != null) {
            this.renderer.setLineBackground(startLine, lineCount, background);
        }
        else {
            this.renderer.clearLineBackground(startLine, lineCount);
        }
        this.redrawLines(startLine, lineCount, false);
    }
    
    public void setLineBullet(final int startLine, final int lineCount, final Bullet bullet) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (startLine < 0 || startLine + lineCount > this.content.getLineCount()) {
            SWT.error(5);
        }
        final int oldBottom = this.getLinePixel(startLine + lineCount);
        this.renderer.setLineBullet(startLine, lineCount, bullet);
        this.resetCache(startLine, lineCount);
        final int newBottom = this.getLinePixel(startLine + lineCount);
        this.redrawLines(startLine, lineCount, oldBottom != newBottom);
        final IntStream stream = Arrays.stream(this.caretOffsets);
        final StyledTextContent content = this.content;
        Objects.requireNonNull(content);
        if (stream.map(content::getLineAtOffset).anyMatch(caretLine -> startLine <= caretLine && caretLine < startLine + lineCount)) {
            this.setCaretLocations();
        }
    }
    
    boolean isWordWrap() {
        return this.wordWrap || this.visualWrap;
    }
    
    public void setLineIndent(final int startLine, final int lineCount, final int indent) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (startLine < 0 || startLine + lineCount > this.content.getLineCount()) {
            SWT.error(5);
        }
        final int oldBottom = this.getLinePixel(startLine + lineCount);
        this.renderer.setLineIndent(startLine, lineCount, indent);
        this.resetCache(startLine, lineCount);
        final int newBottom = this.getLinePixel(startLine + lineCount);
        this.redrawLines(startLine, lineCount, oldBottom != newBottom);
        final IntStream stream = Arrays.stream(this.caretOffsets);
        final StyledTextContent content = this.content;
        Objects.requireNonNull(content);
        if (stream.map(content::getLineAtOffset).anyMatch(caretLine -> startLine <= caretLine && caretLine < startLine + lineCount)) {
            this.setCaretLocations();
        }
    }
    
    public void setLineVerticalIndent(final int lineIndex, final int verticalLineIndent) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (lineIndex < 0 || lineIndex >= this.content.getLineCount()) {
            SWT.error(5);
        }
        final int previousVerticalIndent = this.renderer.getLineVerticalIndent(lineIndex);
        if (verticalLineIndent == previousVerticalIndent) {
            return;
        }
        final int initialTopPixel = this.getTopPixel();
        final int initialTopIndex = this.getPartialTopIndex();
        final int initialBottomIndex = this.getPartialBottomIndex();
        final int verticalIndentDiff = verticalLineIndent - previousVerticalIndent;
        this.renderer.setLineVerticalIndent(lineIndex, verticalLineIndent);
        this.hasVerticalIndent = (verticalLineIndent != 0 || this.renderer.hasVerticalIndent());
        final ScrollBar verticalScrollbar = this.getVerticalBar();
        if (lineIndex < initialTopIndex) {
            this.verticalScrollOffset += verticalIndentDiff;
            if (verticalScrollbar != null) {
                verticalScrollbar.setSelection(this.verticalScrollOffset);
                verticalScrollbar.setMaximum(verticalScrollbar.getMaximum() + verticalIndentDiff);
            }
        }
        else if (lineIndex > initialBottomIndex) {
            if (verticalScrollbar != null) {
                verticalScrollbar.setMaximum(verticalScrollbar.getMaximum() + verticalIndentDiff);
            }
        }
        else {
            this.resetCache(lineIndex, 1);
            if (initialTopIndex == 0 && initialBottomIndex == this.content.getLineCount() - 1) {
                this.setCaretLocations();
                this.redrawLines(lineIndex, this.getBottomIndex() - lineIndex + 1, true);
            }
            else if (this.getFirstCaretLine() >= initialTopIndex && this.getFirstCaretLine() <= initialBottomIndex) {
                if (this.getFirstCaretLine() < lineIndex) {
                    this.redrawLines(lineIndex, this.getPartialBottomIndex() - lineIndex + 1, true);
                }
                else {
                    this.setTopPixel(initialTopPixel + verticalIndentDiff);
                }
            }
            else if (lineIndex - this.getTopIndex() < this.getBottomIndex() - lineIndex) {
                this.setTopPixel(initialTopPixel + verticalIndentDiff);
            }
            else {
                this.redrawLines(lineIndex, this.getPartialBottomIndex() - lineIndex + 1, true);
            }
            this.setScrollBars(true);
        }
    }
    
    public void setLineJustify(final int startLine, final int lineCount, final boolean justify) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (startLine < 0 || startLine + lineCount > this.content.getLineCount()) {
            SWT.error(5);
        }
        this.renderer.setLineJustify(startLine, lineCount, justify);
        this.resetCache(startLine, lineCount);
        this.redrawLines(startLine, lineCount, false);
        final IntStream stream = Arrays.stream(this.caretOffsets);
        final StyledTextContent content = this.content;
        Objects.requireNonNull(content);
        if (stream.map(content::getLineAtOffset).anyMatch(caretLine -> startLine <= caretLine && caretLine < startLine + lineCount)) {
            this.setCaretLocations();
        }
    }
    
    public void setLineSpacing(final int lineSpacing) {
        this.checkWidget();
        if (this.lineSpacing == lineSpacing || lineSpacing < 0) {
            return;
        }
        this.lineSpacing = lineSpacing;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setLineSpacingProvider(final StyledTextLineSpacingProvider lineSpacingProvider) {
        this.checkWidget();
        final boolean wasFixedLineHeight = this.isFixedLineHeight();
        if ((this.renderer.getLineSpacingProvider() == null && lineSpacingProvider == null) || (this.renderer.getLineSpacingProvider() != null && this.renderer.getLineSpacingProvider().equals(lineSpacingProvider))) {
            return;
        }
        this.renderer.setLineSpacingProvider(lineSpacingProvider);
        if (lineSpacingProvider == null) {
            if (!wasFixedLineHeight) {
                this.resetCache(0, this.content.getLineCount());
            }
        }
        else if (wasFixedLineHeight) {
            int firstLine = -1;
            for (int i = 0; i < this.content.getLineCount(); ++i) {
                final Integer lineSpacing = lineSpacingProvider.getLineSpacing(i);
                if (lineSpacing != null && lineSpacing > 0) {
                    this.renderer.reset(i, 1);
                    if (firstLine == -1) {
                        firstLine = i;
                    }
                }
            }
            if (firstLine != -1) {
                this.resetCache(firstLine, 0);
            }
        }
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setLineTabStops(final int startLine, final int lineCount, final int[] tabStops) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (startLine < 0 || startLine + lineCount > this.content.getLineCount()) {
            SWT.error(5);
        }
        if (tabStops != null) {
            int pos = 0;
            final int[] newTabs = new int[tabStops.length];
            for (int i = 0; i < tabStops.length; ++i) {
                if (tabStops[i] < pos) {
                    SWT.error(5);
                }
                final int[] array = newTabs;
                final int n = i;
                final int n2 = tabStops[i];
                array[n] = n2;
                pos = n2;
            }
            this.renderer.setLineTabStops(startLine, lineCount, newTabs);
        }
        else {
            this.renderer.setLineTabStops(startLine, lineCount, null);
        }
        this.resetCache(startLine, lineCount);
        this.redrawLines(startLine, lineCount, false);
        final IntStream stream = Arrays.stream(this.caretOffsets);
        final StyledTextContent content = this.content;
        Objects.requireNonNull(content);
        if (stream.map(content::getLineAtOffset).anyMatch(caretLine -> startLine <= caretLine && caretLine < startLine + lineCount)) {
            this.setCaretLocations();
        }
    }
    
    public void setLineWrapIndent(final int startLine, final int lineCount, final int wrapIndent) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (startLine < 0 || startLine + lineCount > this.content.getLineCount()) {
            SWT.error(5);
        }
        final int oldBottom = this.getLinePixel(startLine + lineCount);
        this.renderer.setLineWrapIndent(startLine, lineCount, wrapIndent);
        this.resetCache(startLine, lineCount);
        final int newBottom = this.getLinePixel(startLine + lineCount);
        this.redrawLines(startLine, lineCount, oldBottom != newBottom);
        final IntStream stream = Arrays.stream(this.caretOffsets);
        final StyledTextContent content = this.content;
        Objects.requireNonNull(content);
        if (stream.map(content::getLineAtOffset).anyMatch(caretLine -> startLine <= caretLine && caretLine < startLine + lineCount)) {
            this.setCaretLocations();
        }
    }
    
    public void setMarginColor(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        this.marginColor = color;
        super.redraw();
    }
    
    public void setMargins(final int leftMargin, final int topMargin, final int rightMargin, final int bottomMargin) {
        this.checkWidget();
        this.leftMargin = Math.max(0, leftMargin) + this.alignmentMargin;
        this.topMargin = Math.max(0, topMargin);
        this.rightMargin = Math.max(0, rightMargin);
        this.bottomMargin = Math.max(0, bottomMargin);
        this.resetCache(0, this.content.getLineCount());
        this.setScrollBars(true);
        this.setCaretLocations();
        this.setAlignment();
        super.redraw();
    }
    
    public void setMouseNavigatorEnabled(final boolean enabled) {
        this.checkWidget();
        if ((enabled && this.mouseNavigator != null) || (!enabled && this.mouseNavigator == null)) {
            return;
        }
        if (enabled) {
            this.mouseNavigator = new MouseNavigator(this);
        }
        else {
            this.mouseNavigator.dispose();
            this.mouseNavigator = null;
        }
    }
    
    void setMouseWordSelectionAnchor() {
        if (this.doubleClickEnabled && this.clickCount > 1) {
            if (this.caretOffsets[0] < this.doubleClickSelection.x) {
                this.selectionAnchors[0] = this.doubleClickSelection.y;
            }
            else if (this.caretOffsets[0] > this.doubleClickSelection.y) {
                this.selectionAnchors[0] = this.doubleClickSelection.x;
            }
        }
    }
    
    @Override
    public void setOrientation(final int orientation) {
        final int oldOrientation = this.getOrientation();
        super.setOrientation(orientation);
        final int newOrientation = this.getOrientation();
        if (oldOrientation != newOrientation) {
            this.resetBidiData();
        }
    }
    
    public void setRightMargin(final int rightMargin) {
        this.checkWidget();
        this.setMargins(this.getLeftMargin(), this.topMargin, rightMargin, this.bottomMargin);
    }
    
    void setScrollBar(final ScrollBar bar, final int clientArea, final int maximum, final int margin) {
        final int inactive = 1;
        if (clientArea < maximum) {
            bar.setMaximum(maximum - margin);
            bar.setThumb(clientArea - margin);
            bar.setPageIncrement(clientArea - margin);
            if (!this.alwaysShowScroll) {
                bar.setVisible(true);
            }
        }
        else if (bar.getThumb() != 1 || bar.getMaximum() != 1) {
            bar.setValues(bar.getSelection(), bar.getMinimum(), 1, 1, bar.getIncrement(), 1);
        }
    }
    
    void setScrollBars(boolean vertical) {
        ++this.ignoreResize;
        if (!this.isFixedLineHeight() || !this.alwaysShowScroll) {
            vertical = true;
        }
        final ScrollBar verticalBar = vertical ? this.getVerticalBar() : null;
        final ScrollBar horizontalBar = this.getHorizontalBar();
        final int oldHeight = this.clientAreaHeight;
        final int oldWidth = this.clientAreaWidth;
        if (!this.alwaysShowScroll) {
            if (verticalBar != null) {
                verticalBar.setVisible(false);
            }
            if (horizontalBar != null) {
                horizontalBar.setVisible(false);
            }
        }
        if (verticalBar != null) {
            this.setScrollBar(verticalBar, this.clientAreaHeight, this.renderer.getHeight(), this.topMargin + this.bottomMargin);
        }
        if (horizontalBar != null && !this.wordWrap) {
            this.setScrollBar(horizontalBar, this.clientAreaWidth, this.renderer.getWidth(), this.leftMargin + this.rightMargin);
            if (!this.alwaysShowScroll && horizontalBar.getVisible() && verticalBar != null) {
                this.setScrollBar(verticalBar, this.clientAreaHeight, this.renderer.getHeight(), this.topMargin + this.bottomMargin);
                if (verticalBar.getVisible()) {
                    this.setScrollBar(horizontalBar, this.clientAreaWidth, this.renderer.getWidth(), this.leftMargin + this.rightMargin);
                }
            }
        }
        if (!this.alwaysShowScroll) {
            this.redrawMargins(oldHeight, oldWidth);
        }
        --this.ignoreResize;
    }
    
    public void setSelection(final int start) {
        this.setSelection(start, start);
    }
    
    public void setSelection(final Point point) {
        this.checkWidget();
        if (point == null) {
            SWT.error(4);
        }
        this.setSelection(point.x, point.y);
    }
    
    public void setSelectionBackground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        this.selectionBackground = color;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setSelectionForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        this.selectionForeground = color;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setSelection(final int start, final int end) {
        this.setSelectionRange(start, end - start);
        this.showSelection();
    }
    
    void setSelection(final int[] regions, final boolean sendEvent, final boolean doBlock) {
        if (regions.length == 2 && this.selection.length == 1) {
            int start = regions[0];
            final int length = regions[1];
            int end = start + length;
            if (start > end) {
                final int temp = end;
                end = start;
                start = temp;
            }
            int selectionAnchor = this.selectionAnchors[0];
            if (this.selection[0].x != start || this.selection[0].y != end || (length > 0 && selectionAnchor != this.selection[0].x) || (length < 0 && selectionAnchor != this.selection[0].y)) {
                if (this.blockSelection && doBlock) {
                    if (length < 0) {
                        this.setBlockSelectionOffset(end, start, sendEvent);
                    }
                    else {
                        this.setBlockSelectionOffset(start, end, sendEvent);
                    }
                }
                else {
                    final int oldStart = this.selection[0].x;
                    final int oldLength = this.selection[0].y - this.selection[0].x;
                    final int charCount = this.content.getCharCount();
                    int redrawX = Math.min(this.selection[0].x, charCount);
                    int redrawY = Math.min(this.selection[0].y, charCount);
                    if (length < 0) {
                        final int[] selectionAnchors = this.selectionAnchors;
                        final int n = 0;
                        final Point point = this.selection[0];
                        final int y = end;
                        point.y = y;
                        selectionAnchor = y;
                        selectionAnchors[0] = y;
                        this.selection[0].x = start;
                        this.setCaretOffsets(new int[] { start }, 0);
                    }
                    else {
                        final int[] selectionAnchors2 = this.selectionAnchors;
                        final int n2 = 0;
                        final Point point2 = this.selection[0];
                        final int x = start;
                        point2.x = x;
                        selectionAnchor = x;
                        selectionAnchors2[0] = x;
                        this.selection[0].y = end;
                        this.setCaretOffsets(new int[] { end }, 0);
                    }
                    redrawX = Math.min(redrawX, this.selection[0].x);
                    redrawY = Math.max(redrawY, this.selection[0].y);
                    if (redrawY - redrawX > 0) {
                        this.internalRedrawRange(redrawX, redrawY - redrawX);
                    }
                    if (sendEvent && (oldLength != end - start || (oldLength != 0 && oldStart != start))) {
                        this.sendSelectionEvent();
                    }
                    this.sendAccessibleTextCaretMoved();
                }
            }
        }
        else if (!this.blockSelection || !doBlock) {
            final boolean caretAtEndOfSelection = regions[1] > 0;
            final int charCount2 = this.content.getCharCount();
            final Point[] newRanges = new Point[regions.length / 2];
            for (int i = 0; i < regions.length; i += 2) {
                int start2 = regions[i];
                final int length2 = regions[i + 1];
                int end2 = start2 + length2;
                if (start2 > end2) {
                    final int temp2 = end2;
                    end2 = start2;
                    start2 = temp2;
                }
                newRanges[i / 2] = new Point(start2, end2);
            }
            Arrays.sort(newRanges, StyledText.SELECTION_COMPARATOR);
            int newRangeIndex = 0;
            for (final Point range : newRanges) {
                if (newRangeIndex > 0) {
                    final Point previousRange = newRanges[newRangeIndex - 1];
                    if (previousRange.y >= range.x) {
                        previousRange.y = Math.max(previousRange.y, range.y);
                    }
                    else {
                        newRanges[newRangeIndex] = range;
                        ++newRangeIndex;
                    }
                }
                else {
                    newRanges[newRangeIndex] = range;
                    ++newRangeIndex;
                }
            }
            final Point[] toRedraw = new Point[newRangeIndex + this.selection.length];
            System.arraycopy(newRanges, 0, toRedraw, 0, newRangeIndex);
            System.arraycopy(this.selection, 0, toRedraw, newRangeIndex, this.selection.length);
            Arrays.sort(toRedraw, StyledText.SELECTION_COMPARATOR);
            final Point[] formerSelection = this.selection;
            this.selection = Arrays.copyOf(newRanges, newRangeIndex);
            Point currentToRedraw = null;
            final Point[] array2 = toRedraw;
            Point p2 = null;
            for (int length3 = array2.length, k = 0; k < length3; ++k) {
                p2 = array2[k];
                if (currentToRedraw == null) {
                    currentToRedraw = new Point(p2.x, p2.y);
                }
                else if (currentToRedraw.y >= p2.x - 1) {
                    currentToRedraw = new Point(currentToRedraw.x, Math.max(p2.y, currentToRedraw.y));
                }
                else {
                    currentToRedraw = new Point(Math.max(0, currentToRedraw.x), Math.min(charCount2, currentToRedraw.y));
                    this.internalRedrawRange(currentToRedraw.x, currentToRedraw.y - currentToRedraw.x);
                    currentToRedraw = null;
                }
            }
            if (currentToRedraw != null) {
                currentToRedraw = new Point(Math.max(0, currentToRedraw.x), Math.min(charCount2, currentToRedraw.y));
                this.internalRedrawRange(currentToRedraw.x, currentToRedraw.y - currentToRedraw.x);
            }
            if (!caretAtEndOfSelection) {
                this.selectionAnchors = Arrays.stream(this.selection).mapToInt(p1 -> p1.y).toArray();
                this.setCaretOffsets(Arrays.stream(this.selection).mapToInt(p1 -> p1.x).toArray(), 0);
            }
            else {
                this.selectionAnchors = Arrays.stream(this.selection).mapToInt(p1 -> p1.x).toArray();
                this.setCaretOffsets(Arrays.stream(this.selection).mapToInt(p1 -> p1.y).toArray(), 0);
            }
            this.setCaretLocations();
            if (sendEvent && !Arrays.equals(formerSelection, this.selection)) {
                this.sendSelectionEvent();
            }
            this.sendAccessibleTextCaretMoved();
        }
    }
    
    public void setSelectionRange(final int start, final int length) {
        this.setSelectionRanges(new int[] { start, length });
    }
    
    public void setSelectionRanges(final int[] ranges) {
        this.checkWidget();
        final int contentLength = this.getCharCount();
        if (ranges.length % 2 != 0) {
            SWT.error(5);
        }
        final int[] fixedRanges = Arrays.copyOf(ranges, ranges.length);
        for (int i = 0; i < ranges.length; i += 2) {
            int start = ranges[i];
            start = Math.max(0, Math.min(start, contentLength));
            int length = ranges[i + 1];
            final int end = start + length;
            if (end < 0) {
                length = -start;
            }
            else if (end > contentLength) {
                length = contentLength - start;
            }
            if (this.isLineDelimiter(start) || this.isLineDelimiter(start + length)) {
                SWT.error(5);
            }
            fixedRanges[i] = start;
            fixedRanges[i + 1] = length;
        }
        this.setSelection(fixedRanges, false, true);
        this.setCaretLocations();
    }
    
    public void setStyleRange(final StyleRange range) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (range != null) {
            if (range.isUnstyled()) {
                this.setStyleRanges(range.start, range.length, null, null, false);
            }
            else {
                this.setStyleRanges(range.start, 0, null, new StyleRange[] { range }, false);
            }
        }
        else {
            this.setStyleRanges(0, 0, null, null, true);
        }
    }
    
    public void setStyleRanges(final int start, final int length, final int[] ranges, final StyleRange[] styles) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (ranges == null || styles == null) {
            this.setStyleRanges(start, length, null, null, false);
        }
        else {
            this.setStyleRanges(start, length, ranges, styles, false);
        }
    }
    
    public void setStyleRanges(final int[] ranges, final StyleRange[] styles) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (ranges == null || styles == null) {
            this.setStyleRanges(0, 0, null, null, true);
        }
        else {
            this.setStyleRanges(0, 0, ranges, styles, true);
        }
    }
    
    void setStyleRanges(int start, int length, final int[] ranges, final StyleRange[] styles, final boolean reset) {
        final int charCount = this.content.getCharCount();
        if (reset) {
            start = 0;
            length = charCount;
        }
        final int[] formerRanges = this.getRanges(start, length);
        final StyleRange[] formerStyles = this.getStyleRanges(start, length);
        final int end = start + length;
        final boolean wasFixedLineHeight = this.isFixedLineHeight();
        if (start > end || start < 0) {
            SWT.error(6);
        }
        if (styles != null) {
            if (end > charCount) {
                SWT.error(6);
            }
            if (ranges != null && ranges.length != styles.length << 1) {
                SWT.error(5);
            }
            int lastOffset = 0;
            for (int i = 0; i < styles.length; ++i) {
                if (styles[i] == null) {
                    SWT.error(5);
                }
                int rangeStart;
                int rangeLength;
                if (ranges != null) {
                    rangeStart = ranges[i << 1];
                    rangeLength = ranges[(i << 1) + 1];
                }
                else {
                    rangeStart = styles[i].start;
                    rangeLength = styles[i].length;
                }
                if (rangeLength < 0) {
                    SWT.error(5);
                }
                if (0 > rangeStart || rangeStart + rangeLength > charCount) {
                    SWT.error(5);
                }
                if (lastOffset > rangeStart) {
                    SWT.error(5);
                }
                this.hasStyleWithVariableHeight |= styles[i].isVariableHeight();
                lastOffset = rangeStart + rangeLength;
            }
        }
        int rangeStart2 = start;
        int rangeEnd = end;
        if (styles != null && styles.length > 0) {
            if (ranges != null) {
                rangeStart2 = ranges[0];
                rangeEnd = ranges[ranges.length - 2] + ranges[ranges.length - 1];
            }
            else {
                rangeStart2 = styles[0].start;
                rangeEnd = styles[styles.length - 1].start + styles[styles.length - 1].length;
            }
        }
        int expectedBottom = 0;
        if (!this.isFixedLineHeight() && !reset) {
            final int lineEnd = this.content.getLineAtOffset(Math.max(end, rangeEnd));
            final int partialTopIndex = this.getPartialTopIndex();
            final int partialBottomIndex = this.getPartialBottomIndex();
            if (partialTopIndex <= lineEnd && lineEnd <= partialBottomIndex) {
                expectedBottom = this.getLinePixel(lineEnd + 1);
            }
        }
        if (reset) {
            this.renderer.setStyleRanges(null, null);
        }
        else {
            this.renderer.updateRanges(start, length, length);
        }
        if (styles != null && styles.length > 0) {
            this.renderer.setStyleRanges(ranges, styles);
        }
        this.hasStyleWithVariableHeight = false;
        for (final StyleRange style : this.getStyleRanges(false)) {
            this.hasStyleWithVariableHeight = style.isVariableHeight();
            if (this.hasStyleWithVariableHeight) {
                break;
            }
        }
        final SortedSet<Integer> modifiedLines = this.computeModifiedLines(formerRanges, formerStyles, ranges, styles);
        this.resetCache(modifiedLines);
        if (reset) {
            super.redraw();
        }
        else {
            final int lineStart = this.content.getLineAtOffset(Math.min(start, rangeStart2));
            final int lineEnd2 = this.content.getLineAtOffset(Math.max(end, rangeEnd));
            final int partialTopIndex2 = this.getPartialTopIndex();
            final int partialBottomIndex2 = this.getPartialBottomIndex();
            if (lineStart <= partialBottomIndex2 && lineEnd2 >= partialTopIndex2) {
                int top = 0;
                int bottom = this.clientAreaHeight;
                if (partialTopIndex2 <= lineStart && lineStart <= partialBottomIndex2) {
                    top = Math.max(0, this.getLinePixel(lineStart));
                }
                if (partialTopIndex2 <= lineEnd2 && lineEnd2 <= partialBottomIndex2) {
                    bottom = this.getLinePixel(lineEnd2 + 1);
                }
                if ((!wasFixedLineHeight || !this.isFixedLineHeight()) && bottom != expectedBottom) {
                    bottom = this.clientAreaHeight;
                }
                super.redraw(0, top, this.clientAreaWidth, bottom - top, false);
            }
        }
        final int oldColumnX = this.columnX;
        this.setCaretLocations();
        this.columnX = oldColumnX;
        this.doMouseLinkCursor();
    }
    
    private SortedSet<Integer> computeModifiedLines(int[] referenceRanges, StyleRange[] referenceStyles, int[] newRanges, StyleRange[] newStyles) {
        if (referenceStyles == null) {
            referenceStyles = new StyleRange[0];
        }
        if (referenceRanges == null) {
            referenceRanges = this.createRanges(referenceStyles);
        }
        if (newStyles == null) {
            newStyles = new StyleRange[0];
        }
        if (newRanges == null) {
            newRanges = this.createRanges(newStyles);
        }
        if (referenceRanges.length != 2 * referenceStyles.length) {
            throw new IllegalArgumentException();
        }
        if (newRanges.length != 2 * newStyles.length) {
            throw new IllegalArgumentException();
        }
        final SortedSet<Integer> res = new TreeSet<Integer>();
        int referenceRangeIndex = 0;
        int newRangeIndex = 0;
        final StyleRange defaultStyle = new StyleRange();
        defaultStyle.foreground = this.foreground;
        defaultStyle.background = this.background;
        defaultStyle.font = this.getFont();
        int currentOffset = (referenceRanges.length > 0) ? referenceRanges[0] : Integer.MAX_VALUE;
        if (newRanges.length > 0) {
            currentOffset = Math.min(currentOffset, newRanges[0]);
        }
        while (currentOffset < this.content.getCharCount() && (referenceRangeIndex < referenceStyles.length || newRangeIndex < newRanges.length)) {
            int nextMilestoneOffset = Integer.MAX_VALUE;
            while (referenceRangeIndex < referenceStyles.length && this.endRangeOffset(referenceRanges, referenceRangeIndex) <= currentOffset) {
                ++referenceRangeIndex;
            }
            StyleRange referenceStyleAtCurrentOffset = defaultStyle;
            if (this.isInRange(referenceRanges, referenceRangeIndex, currentOffset)) {
                referenceStyleAtCurrentOffset = referenceStyles[referenceRangeIndex];
                nextMilestoneOffset = this.endRangeOffset(referenceRanges, referenceRangeIndex);
            }
            else if (referenceRangeIndex < referenceStyles.length) {
                nextMilestoneOffset = referenceRanges[2 * referenceRangeIndex];
            }
            while (newRangeIndex < newStyles.length && this.endRangeOffset(newRanges, newRangeIndex) <= currentOffset) {
                ++newRangeIndex;
            }
            StyleRange newStyleAtCurrentOffset = defaultStyle;
            if (this.isInRange(newRanges, newRangeIndex, currentOffset)) {
                newStyleAtCurrentOffset = newStyles[newRangeIndex];
                nextMilestoneOffset = Math.min(nextMilestoneOffset, this.endRangeOffset(newRanges, newRangeIndex));
            }
            else if (newRangeIndex < newStyles.length) {
                nextMilestoneOffset = Math.min(nextMilestoneOffset, newRanges[2 * newRangeIndex]);
            }
            if (!referenceStyleAtCurrentOffset.similarTo(newStyleAtCurrentOffset)) {
                final int fromLine = this.getLineAtOffset(currentOffset);
                final int toLine = this.getLineAtOffset(nextMilestoneOffset - 1);
                for (int line = fromLine; line <= toLine; ++line) {
                    res.add(line);
                }
                currentOffset = ((toLine + 1 < this.getLineCount()) ? this.getOffsetAtLine(toLine + 1) : this.content.getCharCount());
            }
            else {
                currentOffset = nextMilestoneOffset;
            }
        }
        return res;
    }
    
    private int[] createRanges(final StyleRange[] referenceStyles) {
        final int[] referenceRanges = new int[2 * referenceStyles.length];
        for (int i = 0; i < referenceStyles.length; ++i) {
            referenceRanges[2 * i] = referenceStyles[i].start;
            referenceRanges[2 * i + 1] = referenceStyles[i].length;
        }
        return referenceRanges;
    }
    
    private boolean isInRange(final int[] ranges, final int styleIndex, final int offset) {
        if (ranges == null || ranges.length == 0 || styleIndex < 0 || 2 * styleIndex + 1 > ranges.length) {
            return false;
        }
        final int start = ranges[2 * styleIndex];
        final int length = ranges[2 * styleIndex + 1];
        return offset >= start && offset < start + length;
    }
    
    private int endRangeOffset(final int[] ranges, final int styleIndex) {
        if (styleIndex < 0 || 2 * styleIndex > ranges.length) {
            throw new IllegalArgumentException();
        }
        final int start = ranges[2 * styleIndex];
        final int length = ranges[2 * styleIndex + 1];
        return start + length;
    }
    
    public void setStyleRanges(final StyleRange[] ranges) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (ranges == null) {
            SWT.error(4);
        }
        this.setStyleRanges(0, 0, null, ranges, true);
    }
    
    public void setTabs(final int tabs) {
        this.checkWidget();
        this.tabLength = tabs;
        this.renderer.setFont(null, tabs);
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setTabStops(final int[] tabs) {
        this.checkWidget();
        if (tabs != null) {
            int pos = 0;
            final int[] newTabs = new int[tabs.length];
            for (int i = 0; i < tabs.length; ++i) {
                if (tabs[i] < pos) {
                    SWT.error(5);
                }
                final int[] array = newTabs;
                final int n = i;
                final int n2 = tabs[i];
                array[n] = n2;
                pos = n2;
            }
            this.tabs = newTabs;
        }
        else {
            this.tabs = null;
        }
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            SWT.error(4);
        }
        final Event event = new Event();
        event.start = 0;
        event.end = this.getCharCount();
        event.text = text;
        event.doit = true;
        this.notifyListeners(25, event);
        if (event.doit) {
            StyledTextEvent styledTextEvent = null;
            if (this.isListening(3000)) {
                styledTextEvent = new StyledTextEvent(this.content);
                styledTextEvent.start = event.start;
                styledTextEvent.end = event.start + event.text.length();
                styledTextEvent.text = this.content.getTextRange(event.start, event.end - event.start);
            }
            this.content.setText(event.text);
            this.notifyListeners(24, event);
            if (styledTextEvent != null) {
                this.notifyListeners(3000, styledTextEvent);
            }
        }
    }
    
    @Override
    public void setTextDirection(final int textDirection) {
        this.checkWidget();
        final int oldStyle = this.getStyle();
        super.setTextDirection(textDirection);
        if (this.isAutoDirection() || oldStyle != this.getStyle()) {
            this.resetBidiData();
        }
    }
    
    public void setTextLimit(final int limit) {
        this.checkWidget();
        if (limit == 0) {
            SWT.error(7);
        }
        this.textLimit = limit;
    }
    
    public void setTopIndex(int topIndex) {
        this.checkWidget();
        if (this.getCharCount() == 0) {
            return;
        }
        final int lineCount = this.content.getLineCount();
        int pixel;
        if (this.isFixedLineHeight()) {
            final int pageSize = Math.max(1, Math.min(lineCount, this.getLineCountWhole()));
            if (topIndex < 0) {
                topIndex = 0;
            }
            else if (topIndex > lineCount - pageSize) {
                topIndex = lineCount - pageSize;
            }
            pixel = this.getLinePixel(topIndex);
        }
        else {
            topIndex = Math.max(0, Math.min(lineCount - 1, topIndex));
            pixel = this.getLinePixel(topIndex);
            if (pixel > 0) {
                pixel = this.getAvailableHeightBellow(pixel);
            }
            else {
                pixel = this.getAvailableHeightAbove(pixel);
            }
        }
        this.scrollVertical(pixel, true);
    }
    
    public void setTopMargin(final int topMargin) {
        this.checkWidget();
        this.setMargins(this.getLeftMargin(), topMargin, this.rightMargin, this.bottomMargin);
    }
    
    public void setTopPixel(int pixel) {
        this.checkWidget();
        if (this.getCharCount() == 0) {
            return;
        }
        if (pixel < 0) {
            pixel = 0;
        }
        final int lineCount = this.content.getLineCount();
        final int height = this.clientAreaHeight - this.topMargin - this.bottomMargin;
        final int verticalOffset = this.getVerticalScrollOffset();
        if (this.isFixedLineHeight()) {
            final int maxTopPixel = Math.max(0, lineCount * this.getVerticalIncrement() - height);
            if (pixel > maxTopPixel) {
                pixel = maxTopPixel;
            }
            pixel -= verticalOffset;
        }
        else {
            pixel -= verticalOffset;
            if (pixel > 0) {
                pixel = this.getAvailableHeightBellow(pixel);
            }
        }
        this.scrollVertical(pixel, true);
    }
    
    public void setWordWrap(final boolean wrap) {
        this.checkWidget();
        if ((this.getStyle() & 0x4) != 0x0) {
            return;
        }
        if (this.wordWrap == wrap) {
            return;
        }
        if (this.wordWrap && this.blockSelection) {
            this.setBlockSelection(false);
        }
        this.wordWrap = wrap;
        this.resetCache(0, this.content.getLineCount());
        this.horizontalScrollOffset = 0;
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (horizontalBar != null) {
            horizontalBar.setVisible(!this.wordWrap);
        }
        this.setScrollBars(true);
        this.setCaretLocations();
        super.redraw();
    }
    
    public void setWrapIndent(final int wrapIndent) {
        this.checkWidget();
        if (this.wrapIndent == wrapIndent || wrapIndent < 0) {
            return;
        }
        this.wrapIndent = wrapIndent;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocations();
        super.redraw();
    }
    
    boolean showLocation(final Rectangle rect, final boolean scrollPage) {
        boolean scrolled = false;
        if (rect.y < this.topMargin) {
            scrolled = this.scrollVertical(rect.y - this.topMargin, true);
        }
        else if (rect.y + rect.height > this.clientAreaHeight - this.bottomMargin) {
            if (this.clientAreaHeight - this.topMargin - this.bottomMargin <= 0) {
                scrolled = this.scrollVertical(rect.y - this.topMargin, true);
            }
            else {
                scrolled = this.scrollVertical(rect.y + rect.height - (this.clientAreaHeight - this.bottomMargin), true);
            }
        }
        final int width = this.clientAreaWidth - this.rightMargin - this.leftMargin;
        if (width > 0) {
            final int minScroll = scrollPage ? (width / 4) : 0;
            if (rect.x < this.leftMargin) {
                final int scrollWidth = Math.max(this.leftMargin - rect.x, minScroll);
                final int maxScroll = this.horizontalScrollOffset;
                scrolled = this.scrollHorizontal(-Math.min(maxScroll, scrollWidth), true);
            }
            else if (rect.x + rect.width > this.clientAreaWidth - this.rightMargin) {
                final int scrollWidth = Math.max(rect.x + rect.width - (this.clientAreaWidth - this.rightMargin), minScroll);
                final int maxScroll = this.renderer.getWidth() - this.horizontalScrollOffset - this.clientAreaWidth;
                scrolled = this.scrollHorizontal(Math.min(maxScroll, scrollWidth), true);
            }
        }
        return scrolled;
    }
    
    void showCaret() {
        final Rectangle bounds = this.getBoundsAtOffset(this.caretOffsets[0]);
        if (!this.showLocation(bounds, true) || (this.carets != null && this.caretOffsets.length != this.carets.length)) {
            this.setCaretLocations();
        }
    }
    
    public void showSelection() {
        this.checkWidget();
        final boolean rightToLeft = this.caretOffsets[0] == this.selection[0].x;
        int startOffset;
        int endOffset;
        if (rightToLeft) {
            startOffset = this.selection[0].y;
            endOffset = this.selection[0].x;
        }
        else {
            startOffset = this.selection[0].x;
            endOffset = this.selection[0].y;
        }
        final Rectangle startBounds = this.getBoundsAtOffset(startOffset);
        Rectangle endBounds = this.getBoundsAtOffset(endOffset);
        final int w = this.clientAreaWidth - this.leftMargin - this.rightMargin;
        final boolean selectionFits = rightToLeft ? (startBounds.x - endBounds.x <= w) : (endBounds.x - startBounds.x <= w);
        if (selectionFits) {
            if (this.showLocation(startBounds, false)) {
                endBounds = this.getBoundsAtOffset(endOffset);
            }
            endBounds.width = ((endOffset == this.caretOffsets[0]) ? this.getCaretWidth() : 0);
            this.showLocation(endBounds, false);
        }
        else {
            this.showLocation(endBounds, true);
        }
    }
    
    void updateCaretVisibility() {
        final Caret caret = this.getCaret();
        if (caret != null) {
            if (this.carets == null || this.carets.length == 0) {
                this.carets = new Caret[] { caret };
            }
            if (this.blockSelection && this.blockXLocation != -1) {
                Arrays.stream(this.carets).forEach(c -> c.setVisible(false));
            }
            else {
                final Point location;
                final Point size;
                final boolean visible;
                Arrays.stream(this.carets).forEach(c -> {
                    location = c.getLocation();
                    size = c.getSize();
                    visible = (this.topMargin <= location.y + size.y && location.y <= this.clientAreaHeight - this.bottomMargin && this.leftMargin <= location.x + size.x && location.x <= this.clientAreaWidth - this.rightMargin);
                    c.setVisible(visible);
                });
            }
        }
    }
    
    void updateSelection(final int startOffset, final int replacedLength, final int newLength) {
        if (this.selection[this.selection.length - 1].y <= startOffset) {
            if (this.isWordWrap()) {
                this.setCaretLocations();
            }
            return;
        }
        Arrays.stream(this.selection).filter(sel -> sel.y > startOffset).filter(sel -> sel.x < startOffset).forEach(sel -> this.internalRedrawRange(sel.x, startOffset - sel.x));
        final int netNewLength;
        final int redrawStart;
        Arrays.stream(this.selection).filter(sel -> sel.y > startOffset).filter(sel -> sel.y > startOffset + replacedLength && sel.x < startOffset + replacedLength).forEach(sel -> {
            netNewLength = newLength - replacedLength;
            redrawStart = startOffset + newLength;
            this.internalRedrawRange(redrawStart, sel.y + netNewLength - redrawStart);
            return;
        });
        final Point[] dick = this.selection;
        for (int i = 0; i < this.selection.length; ++i) {
            final Point sel2 = this.selection[i];
            if (sel2.y <= startOffset) {
                dick[i] = sel2;
            }
            else if (sel2.x == startOffset && sel2.y == startOffset + replacedLength) {
                dick[i] = new Point(startOffset + newLength, startOffset + newLength);
            }
            else if (sel2.y > startOffset && sel2.x < startOffset + replacedLength) {
                dick[i] = new Point(startOffset + newLength, startOffset + newLength);
            }
            else {
                final int x = sel2.x + newLength - replacedLength;
                final int y = sel2.x + newLength - replacedLength + (sel2.y - sel2.x);
                dick[i] = new Point((x < 0) ? 0 : x, (y < 0) ? 0 : y);
            }
        }
        this.setSelection(Arrays.stream(dick).flatMapToInt(p -> IntStream.of(p.x, p.y - p.x)).toArray(), true, false);
        this.setCaretLocations();
    }
    
    static {
        PlatformLineDelimiter = System.lineSeparator();
        SELECTION_COMPARATOR = Comparator.comparingInt((ToIntFunction<? super Point>)StyledText::getX).thenComparingInt(p1 -> p1.y);
        final String platform = SWT.getPlatform();
        IS_MAC = "cocoa".equals(platform);
        IS_GTK = "gtk".equals(platform);
    }
    
    static class Printing implements Runnable
    {
        static final int LEFT = 0;
        static final int CENTER = 1;
        static final int RIGHT = 2;
        Printer printer;
        StyledTextRenderer printerRenderer;
        StyledTextPrintOptions printOptions;
        Rectangle clientArea;
        FontData fontData;
        Font printerFont;
        Map<Resource, Resource> resources;
        int tabLength;
        GC gc;
        int pageWidth;
        int startPage;
        int endPage;
        int scope;
        int startLine;
        int endLine;
        boolean singleLine;
        Point[] selection;
        boolean mirrored;
        int lineSpacing;
        int printMargin;
        
        Printing(final StyledText styledText, final Printer printer, final StyledTextPrintOptions printOptions) {
            this.selection = new Point[] { new Point(0, 0) };
            this.printer = printer;
            this.printOptions = printOptions;
            this.mirrored = ((styledText.getStyle() & 0x8000000) != 0x0);
            this.singleLine = styledText.isSingleLine();
            this.startPage = 1;
            this.endPage = Integer.MAX_VALUE;
            final PrinterData data = printer.getPrinterData();
            this.scope = data.scope;
            if (this.scope == 1) {
                this.startPage = data.startPage;
                this.endPage = data.endPage;
                if (this.endPage < this.startPage) {
                    final int temp = this.endPage;
                    this.endPage = this.startPage;
                    this.startPage = temp;
                }
            }
            else if (this.scope == 2) {
                this.selection = Arrays.copyOf(styledText.selection, styledText.selection.length);
            }
            (this.printerRenderer = new StyledTextRenderer(printer, null)).setContent(this.copyContent(styledText.getContent()));
            this.cacheLineData(styledText);
        }
        
        void cacheLineData(final StyledText styledText) {
            final StyledTextRenderer renderer = styledText.renderer;
            renderer.copyInto(this.printerRenderer);
            this.fontData = styledText.getFont().getFontData()[0];
            this.tabLength = styledText.tabLength;
            final int lineCount = this.printerRenderer.lineCount;
            if (styledText.isListening(3001) || styledText.isListening(3007) || styledText.isListening(3002)) {
                final StyledTextContent content = this.printerRenderer.content;
                for (int i = 0; i < lineCount; ++i) {
                    final String line = content.getLine(i);
                    final int lineOffset = content.getOffsetAtLine(i);
                    StyledTextEvent event = styledText.getLineBackgroundData(lineOffset, line);
                    if (event != null && event.lineBackground != null) {
                        this.printerRenderer.setLineBackground(i, 1, event.lineBackground);
                    }
                    event = styledText.getBidiSegments(lineOffset, line);
                    if (event != null) {
                        this.printerRenderer.setLineSegments(i, 1, event.segments);
                        this.printerRenderer.setLineSegmentChars(i, 1, event.segmentsChars);
                    }
                    event = styledText.getLineStyleData(lineOffset, line);
                    if (event != null) {
                        this.printerRenderer.setLineIndent(i, 1, event.indent);
                        this.printerRenderer.setLineAlignment(i, 1, event.alignment);
                        this.printerRenderer.setLineJustify(i, 1, event.justify);
                        this.printerRenderer.setLineBullet(i, 1, event.bullet);
                        final StyleRange[] styles = event.styles;
                        if (styles != null && styles.length > 0) {
                            this.printerRenderer.setStyleRanges(event.ranges, styles);
                        }
                    }
                }
            }
            final Point screenDPI = styledText.getDisplay().getDPI();
            final Point printerDPI = this.printer.getDPI();
            this.resources = new HashMap<Resource, Resource>();
            for (int j = 0; j < lineCount; ++j) {
                final Color color = this.printerRenderer.getLineBackground(j, null);
                if (color != null) {
                    if (this.printOptions.printLineBackground) {
                        Color printerColor = this.resources.get(color);
                        if (printerColor == null) {
                            printerColor = new Color(color.getRGB());
                            this.resources.put(color, printerColor);
                        }
                        this.printerRenderer.setLineBackground(j, 1, printerColor);
                    }
                    else {
                        this.printerRenderer.setLineBackground(j, 1, null);
                    }
                }
                final int indent = this.printerRenderer.getLineIndent(j, 0);
                if (indent != 0) {
                    this.printerRenderer.setLineIndent(j, 1, indent * printerDPI.x / screenDPI.x);
                }
            }
            final StyleRange[] styles2 = this.printerRenderer.styles;
            for (int k = 0; k < this.printerRenderer.styleCount; ++k) {
                final StyleRange style = styles2[k];
                final Font font = style.font;
                if (style.font != null) {
                    Font printerFont = this.resources.get(font);
                    if (printerFont == null) {
                        printerFont = new Font(this.printer, font.getFontData());
                        this.resources.put(font, printerFont);
                    }
                    style.font = printerFont;
                }
                Color color2 = style.foreground;
                if (color2 != null) {
                    Color printerColor2 = this.resources.get(color2);
                    if (this.printOptions.printTextForeground) {
                        if (printerColor2 == null) {
                            printerColor2 = new Color(color2.getRGB());
                            this.resources.put(color2, printerColor2);
                        }
                        style.foreground = printerColor2;
                    }
                    else {
                        style.foreground = null;
                    }
                }
                color2 = style.background;
                if (color2 != null) {
                    Color printerColor2 = this.resources.get(color2);
                    if (this.printOptions.printTextBackground) {
                        if (printerColor2 == null) {
                            printerColor2 = new Color(color2.getRGB());
                            this.resources.put(color2, printerColor2);
                        }
                        style.background = printerColor2;
                    }
                    else {
                        style.background = null;
                    }
                }
                if (!this.printOptions.printTextFontStyle) {
                    style.fontStyle = 0;
                }
                style.rise = style.rise * printerDPI.y / screenDPI.y;
                final GlyphMetrics metrics = style.metrics;
                if (metrics != null) {
                    metrics.ascent = metrics.ascent * printerDPI.y / screenDPI.y;
                    metrics.descent = metrics.descent * printerDPI.y / screenDPI.y;
                    metrics.width = metrics.width * printerDPI.x / screenDPI.x;
                }
            }
            this.lineSpacing = styledText.lineSpacing * printerDPI.y / screenDPI.y;
            if (this.printOptions.printLineNumbers) {
                this.printMargin = 3 * printerDPI.x / screenDPI.x;
            }
        }
        
        StyledTextContent copyContent(final StyledTextContent original) {
            final StyledTextContent printerContent = (StyledTextContent)new DefaultContent();
            int insertOffset = 0;
            for (int i = 0; i < original.getLineCount(); ++i) {
                int insertEndOffset;
                if (i < original.getLineCount() - 1) {
                    insertEndOffset = original.getOffsetAtLine(i + 1);
                }
                else {
                    insertEndOffset = original.getCharCount();
                }
                printerContent.replaceTextRange(insertOffset, 0, original.getTextRange(insertOffset, insertEndOffset - insertOffset));
                insertOffset = insertEndOffset;
            }
            return printerContent;
        }
        
        void dispose() {
            if (this.gc != null) {
                this.gc.dispose();
                this.gc = null;
            }
            if (this.resources != null) {
                for (final Resource resource : this.resources.values()) {
                    resource.dispose();
                }
                this.resources = null;
            }
            if (this.printerFont != null) {
                this.printerFont.dispose();
                this.printerFont = null;
            }
            if (this.printerRenderer != null) {
                this.printerRenderer.dispose();
                this.printerRenderer = null;
            }
        }
        
        void init() {
            final Rectangle trim = this.printer.computeTrim(0, 0, 0, 0);
            final Point dpi = this.printer.getDPI();
            this.printerFont = new Font(this.printer, this.fontData.getName(), this.fontData.getHeight(), 0);
            this.clientArea = this.printer.getClientArea();
            this.pageWidth = this.clientArea.width;
            this.clientArea.x = dpi.x + trim.x;
            this.clientArea.y = dpi.y + trim.y;
            final Rectangle clientArea6;
            final Rectangle clientArea = clientArea6 = this.clientArea;
            clientArea6.width -= this.clientArea.x + trim.width;
            final Rectangle clientArea7;
            final Rectangle clientArea2 = clientArea7 = this.clientArea;
            clientArea7.height -= this.clientArea.y + trim.height;
            final int style = this.mirrored ? 67108864 : 33554432;
            (this.gc = new GC(this.printer, style)).setFont(this.printerFont);
            this.printerRenderer.setFont(this.printerFont, this.tabLength);
            final int lineHeight = this.printerRenderer.getLineHeight();
            if (this.printOptions.header != null) {
                final Rectangle clientArea8;
                final Rectangle clientArea3 = clientArea8 = this.clientArea;
                clientArea8.y += lineHeight * 2;
                final Rectangle clientArea9;
                final Rectangle clientArea4 = clientArea9 = this.clientArea;
                clientArea9.height -= lineHeight * 2;
            }
            if (this.printOptions.footer != null) {
                final Rectangle clientArea10;
                final Rectangle clientArea5 = clientArea10 = this.clientArea;
                clientArea10.height -= lineHeight * 2;
            }
            final StyledTextContent content = this.printerRenderer.content;
            this.startLine = 0;
            this.endLine = (this.singleLine ? 0 : (content.getLineCount() - 1));
            if (this.scope == 1) {
                final int pageSize = this.clientArea.height / lineHeight;
                this.startLine = (this.startPage - 1) * pageSize;
            }
            else if (this.scope == 2) {
                this.startLine = content.getLineAtOffset(this.selection[0].x);
                if (this.selection[0].y > 0) {
                    this.endLine = content.getLineAtOffset(this.selection[0].y);
                }
                else {
                    this.endLine = this.startLine - 1;
                }
            }
        }
        
        void print() {
            final Color background = this.gc.getBackground();
            final Color foreground = this.gc.getForeground();
            int paintY = this.clientArea.y;
            int paintX = this.clientArea.x;
            int width = this.clientArea.width;
            int page = this.startPage;
            final int pageBottom = this.clientArea.y + this.clientArea.height;
            final int orientation = this.gc.getStyle() & 0x6000000;
            TextLayout printLayout = null;
            if (this.printOptions.printLineNumbers || this.printOptions.header != null || this.printOptions.footer != null) {
                printLayout = new TextLayout(this.printer);
                printLayout.setFont(this.printerFont);
            }
            if (this.printOptions.printLineNumbers) {
                int numberingWidth = 0;
                int count = this.endLine - this.startLine + 1;
                final String[] lineLabels = this.printOptions.lineLabels;
                if (lineLabels != null) {
                    for (int i = this.startLine; i < Math.min(count, lineLabels.length); ++i) {
                        if (lineLabels[i] != null) {
                            printLayout.setText(lineLabels[i]);
                            final int lineWidth = printLayout.getBounds().width;
                            numberingWidth = Math.max(numberingWidth, lineWidth);
                        }
                    }
                }
                else {
                    final StringBuilder buffer = new StringBuilder("0");
                    while ((count /= 10) > 0) {
                        buffer.append("0");
                    }
                    printLayout.setText(buffer.toString());
                    numberingWidth = printLayout.getBounds().width;
                }
                numberingWidth += this.printMargin;
                if (numberingWidth > width) {
                    numberingWidth = width;
                }
                paintX += numberingWidth;
                width -= numberingWidth;
            }
            for (int j = this.startLine; j <= this.endLine && page <= this.endPage; ++j) {
                if (paintY == this.clientArea.y) {
                    this.printer.startPage();
                    this.printDecoration(page, true, printLayout);
                }
                final TextLayout layout = this.printerRenderer.getTextLayout(j, orientation, width, this.lineSpacing);
                final Color lineBackground = this.printerRenderer.getLineBackground(j, background);
                int paragraphBottom = paintY + layout.getBounds().height;
                if (paragraphBottom <= pageBottom) {
                    this.printLine(paintX, paintY, this.gc, foreground, lineBackground, layout, printLayout, j);
                    paintY = paragraphBottom;
                }
                else {
                    int lineCount;
                    for (lineCount = layout.getLineCount(); paragraphBottom > pageBottom && lineCount > 0; --lineCount, paragraphBottom -= layout.getLineBounds(lineCount).height + layout.getSpacing()) {}
                    if (lineCount == 0) {
                        this.printDecoration(page, false, printLayout);
                        this.printer.endPage();
                        if (++page <= this.endPage) {
                            this.printer.startPage();
                            this.printDecoration(page, true, printLayout);
                            paintY = this.clientArea.y;
                            this.printLine(paintX, paintY, this.gc, foreground, lineBackground, layout, printLayout, j);
                            paintY += layout.getBounds().height;
                        }
                    }
                    else {
                        final int height = paragraphBottom - paintY;
                        this.gc.setClipping(this.clientArea.x, paintY, this.clientArea.width, height);
                        this.printLine(paintX, paintY, this.gc, foreground, lineBackground, layout, printLayout, j);
                        this.gc.setClipping((Rectangle)null);
                        this.printDecoration(page, false, printLayout);
                        this.printer.endPage();
                        if (++page <= this.endPage) {
                            this.printer.startPage();
                            this.printDecoration(page, true, printLayout);
                            paintY = this.clientArea.y - height;
                            final int layoutHeight = layout.getBounds().height;
                            this.gc.setClipping(this.clientArea.x, this.clientArea.y, this.clientArea.width, layoutHeight - height);
                            this.printLine(paintX, paintY, this.gc, foreground, lineBackground, layout, printLayout, j);
                            this.gc.setClipping((Rectangle)null);
                            paintY += layoutHeight;
                        }
                    }
                }
                this.printerRenderer.disposeTextLayout(layout);
            }
            if (page <= this.endPage && paintY > this.clientArea.y) {
                this.printDecoration(page, false, printLayout);
                this.printer.endPage();
            }
            if (printLayout != null) {
                printLayout.dispose();
            }
        }
        
        void printDecoration(final int page, final boolean header, final TextLayout layout) {
            final String text = header ? this.printOptions.header : this.printOptions.footer;
            if (text == null) {
                return;
            }
            int lastSegmentIndex = 0;
            for (int i = 0; i < 3; ++i) {
                final int segmentIndex = text.indexOf("\t", lastSegmentIndex);
                if (segmentIndex == -1) {
                    final String segment = text.substring(lastSegmentIndex);
                    this.printDecorationSegment(segment, i, page, header, layout);
                    break;
                }
                final String segment = text.substring(lastSegmentIndex, segmentIndex);
                this.printDecorationSegment(segment, i, page, header, layout);
                lastSegmentIndex = segmentIndex + "\t".length();
            }
        }
        
        void printDecorationSegment(String segment, final int alignment, final int page, final boolean header, final TextLayout layout) {
            final int pageIndex = segment.indexOf("<page>");
            if (pageIndex != -1) {
                final int pageTagLength = "<page>".length();
                final StringBuilder buffer = new StringBuilder(segment.substring(0, pageIndex));
                buffer.append(page);
                buffer.append(segment.substring(pageIndex + pageTagLength));
                segment = buffer.toString();
            }
            if (segment.length() > 0) {
                layout.setText(segment);
                final int segmentWidth = layout.getBounds().width;
                final int segmentHeight = this.printerRenderer.getLineHeight();
                int drawX = 0;
                if (alignment == 0) {
                    drawX = this.clientArea.x;
                }
                else if (alignment == 1) {
                    drawX = (this.pageWidth - segmentWidth) / 2;
                }
                else if (alignment == 2) {
                    drawX = this.clientArea.x + this.clientArea.width - segmentWidth;
                }
                int drawY;
                if (header) {
                    drawY = this.clientArea.y - segmentHeight * 2;
                }
                else {
                    drawY = this.clientArea.y + this.clientArea.height + segmentHeight;
                }
                layout.draw(this.gc, drawX, drawY);
            }
        }
        
        void printLine(final int x, final int y, final GC gc, final Color foreground, final Color background, final TextLayout layout, final TextLayout printLayout, final int index) {
            if (background != null) {
                final Rectangle rect = layout.getBounds();
                gc.setBackground(background);
                gc.fillRectangle(x, y, rect.width, rect.height);
            }
            if (this.printOptions.printLineNumbers) {
                final FontMetrics metrics = layout.getLineMetrics(0);
                printLayout.setAscent(metrics.getAscent() + metrics.getLeading());
                printLayout.setDescent(metrics.getDescent());
                final String[] lineLabels = this.printOptions.lineLabels;
                if (lineLabels != null) {
                    if (0 <= index && index < lineLabels.length && lineLabels[index] != null) {
                        printLayout.setText(lineLabels[index]);
                    }
                    else {
                        printLayout.setText("");
                    }
                }
                else {
                    printLayout.setText(String.valueOf(index));
                }
                final int paintX = x - this.printMargin - printLayout.getBounds().width;
                printLayout.draw(gc, paintX, y);
                printLayout.setAscent(-1);
                printLayout.setDescent(-1);
            }
            gc.setForeground(foreground);
            layout.draw(gc, x, y);
        }
        
        @Override
        public void run() {
            String jobName = this.printOptions.jobName;
            if (jobName == null) {
                jobName = "Printing";
            }
            if (this.printer.startJob(jobName)) {
                this.init();
                this.print();
                this.dispose();
                this.printer.endJob();
            }
        }
    }
    
    class RTFWriter extends TextWriter
    {
        static final int DEFAULT_FOREGROUND = 0;
        static final int DEFAULT_BACKGROUND = 1;
        List<Color> colorTable;
        List<Font> fontTable;
        
        public RTFWriter(final int start, final int length) {
            super(start, length);
            this.colorTable = new ArrayList<Color>();
            this.fontTable = new ArrayList<Font>();
            this.colorTable.add(StyledText.this.getForeground());
            this.colorTable.add(StyledText.this.getBackground());
            this.fontTable.add(StyledText.this.getFont());
        }
        
        @Override
        public void close() {
            if (!this.isClosed()) {
                this.writeHeader();
                this.write("\n}}\u0000");
                super.close();
            }
        }
        
        int getColorIndex(final Color color, final int defaultIndex) {
            if (color == null) {
                return defaultIndex;
            }
            int index = this.colorTable.indexOf(color);
            if (index == -1) {
                index = this.colorTable.size();
                this.colorTable.add(color);
            }
            return index;
        }
        
        int getFontIndex(final Font font) {
            int index = this.fontTable.indexOf(font);
            if (index == -1) {
                index = this.fontTable.size();
                this.fontTable.add(font);
            }
            return index;
        }
        
        void write(final String string, int start, final int end) {
            for (int index = start; index < end; ++index) {
                final char ch = string.charAt(index);
                if (ch > '\u007f') {
                    if (index > start) {
                        this.write(string.substring(start, index));
                    }
                    this.write("\\u");
                    this.write(Integer.toString((short)ch));
                    this.write('?');
                    start = index + 1;
                }
                else if (ch == '}' || ch == '{' || ch == '\\') {
                    if (index > start) {
                        this.write(string.substring(start, index));
                    }
                    this.write('\\');
                    this.write(ch);
                    start = index + 1;
                }
            }
            if (start < end) {
                this.write(string.substring(start, end));
            }
        }
        
        void writeHeader() {
            final StringBuilder header = new StringBuilder();
            final FontData fontData = StyledText.this.getFont().getFontData()[0];
            header.append("{\\rtf1\\ansi");
            String cpg = System.getProperty("file.encoding").toLowerCase();
            if (cpg.startsWith("cp") || cpg.startsWith("ms")) {
                cpg = cpg.substring(2, cpg.length());
                header.append("\\ansicpg");
                header.append(cpg);
            }
            header.append("\\uc1\\deff0{\\fonttbl{\\f0\\fnil ");
            header.append(fontData.getName());
            header.append(";");
            for (int i = 1; i < this.fontTable.size(); ++i) {
                header.append("\\f");
                header.append(i);
                header.append(" ");
                final FontData fd = this.fontTable.get(i).getFontData()[0];
                header.append(fd.getName());
                header.append(";");
            }
            header.append("}}\n{\\colortbl");
            for (final Color color : this.colorTable) {
                header.append("\\red");
                header.append(color.getRed());
                header.append("\\green");
                header.append(color.getGreen());
                header.append("\\blue");
                header.append(color.getBlue());
                header.append(";");
            }
            header.append("}\n{\\f0\\fs");
            header.append(fontData.getHeight() * 2);
            header.append(" ");
            this.write(header.toString(), 0);
        }
        
        @Override
        public void writeLine(final String line, final int lineOffset) {
            if (this.isClosed()) {
                SWT.error(39);
            }
            final int lineIndex = StyledText.this.content.getLineAtOffset(lineOffset);
            StyledTextEvent event = StyledText.this.getLineStyleData(lineOffset, line);
            int lineAlignment;
            int lineIndent;
            boolean lineJustify;
            int[] ranges;
            StyleRange[] styles;
            if (event != null) {
                lineAlignment = event.alignment;
                lineIndent = event.indent;
                lineJustify = event.justify;
                ranges = event.ranges;
                styles = event.styles;
            }
            else {
                lineAlignment = StyledText.this.renderer.getLineAlignment(lineIndex, StyledText.this.alignment);
                lineIndent = StyledText.this.renderer.getLineIndent(lineIndex, StyledText.this.indent);
                lineJustify = StyledText.this.renderer.getLineJustify(lineIndex, StyledText.this.justify);
                ranges = StyledText.this.renderer.getRanges(lineOffset, line.length());
                styles = StyledText.this.renderer.getStyleRanges(lineOffset, line.length(), false);
            }
            if (styles == null) {
                styles = new StyleRange[0];
            }
            Color lineBackground = StyledText.this.renderer.getLineBackground(lineIndex, null);
            event = StyledText.this.getLineBackgroundData(lineOffset, line);
            if (event != null && event.lineBackground != null) {
                lineBackground = event.lineBackground;
            }
            this.writeStyledLine(line, lineOffset, ranges, styles, lineBackground, lineIndent, lineAlignment, lineJustify);
        }
        
        @Override
        public void writeLineDelimiter(final String lineDelimiter) {
            if (this.isClosed()) {
                SWT.error(39);
            }
            this.write(lineDelimiter, 0, lineDelimiter.length());
            this.write("\\par ");
        }
        
        void writeStyledLine(final String line, final int lineOffset, final int[] ranges, final StyleRange[] styles, final Color lineBackground, final int indent, final int alignment, final boolean justify) {
            final int lineLength = line.length();
            final int startOffset = this.getStart();
            final int writeOffset = startOffset - lineOffset;
            if (writeOffset >= lineLength) {
                return;
            }
            int lineIndex = Math.max(0, writeOffset);
            this.write("\\fi");
            this.write(indent);
            switch (alignment) {
                case 16384: {
                    this.write("\\ql");
                    break;
                }
                case 16777216: {
                    this.write("\\qc");
                    break;
                }
                case 131072: {
                    this.write("\\qr");
                    break;
                }
            }
            if (justify) {
                this.write("\\qj");
            }
            this.write(" ");
            if (lineBackground != null) {
                this.write("{\\chshdng0\\chcbpat");
                this.write(this.getColorIndex(lineBackground, 1));
                this.write(" ");
            }
            final int endOffset = startOffset + super.getCharCount();
            final int lineEndOffset = Math.min(lineLength, endOffset - lineOffset);
            for (int i = 0; i < styles.length; ++i) {
                final StyleRange style = styles[i];
                int start;
                int end;
                if (ranges != null) {
                    start = ranges[i << 1] - lineOffset;
                    end = start + ranges[(i << 1) + 1];
                }
                else {
                    start = style.start - lineOffset;
                    end = start + style.length;
                }
                if (end >= writeOffset) {
                    if (start >= lineEndOffset) {
                        break;
                    }
                    if (lineIndex < start) {
                        this.write(line, lineIndex, start);
                        lineIndex = start;
                    }
                    this.write("{\\cf");
                    this.write(this.getColorIndex(style.foreground, 0));
                    final int colorIndex = this.getColorIndex(style.background, 1);
                    if (colorIndex != 1) {
                        this.write("\\chshdng0\\chcbpat");
                        this.write(colorIndex);
                    }
                    int fontStyle = style.fontStyle;
                    final Font font = style.font;
                    if (font != null) {
                        final int fontIndex = this.getFontIndex(font);
                        this.write("\\f");
                        this.write(fontIndex);
                        final FontData fontData = font.getFontData()[0];
                        this.write("\\fs");
                        this.write(fontData.getHeight() * 2);
                        fontStyle = fontData.getStyle();
                    }
                    if ((fontStyle & 0x1) != 0x0) {
                        this.write("\\b");
                    }
                    if ((fontStyle & 0x2) != 0x0) {
                        this.write("\\i");
                    }
                    if (style.underline) {
                        this.write("\\ul");
                    }
                    if (style.strikeout) {
                        this.write("\\strike");
                    }
                    this.write(" ");
                    int copyEnd = Math.min(end, lineEndOffset);
                    copyEnd = Math.max(copyEnd, lineIndex);
                    this.write(line, lineIndex, copyEnd);
                    if ((fontStyle & 0x1) != 0x0) {
                        this.write("\\b0");
                    }
                    if ((style.fontStyle & 0x2) != 0x0) {
                        this.write("\\i0");
                    }
                    if (style.underline) {
                        this.write("\\ul0");
                    }
                    if (style.strikeout) {
                        this.write("\\strike0");
                    }
                    this.write("}");
                    lineIndex = copyEnd;
                }
            }
            if (lineIndex < lineEndOffset) {
                this.write(line, lineIndex, lineEndOffset);
            }
            if (lineBackground != null) {
                this.write("}");
            }
        }
    }
    
    static class TextWriter
    {
        private StringBuilder buffer;
        private int startOffset;
        private int endOffset;
        private boolean isClosed;
        
        public TextWriter(final int start, final int length) {
            this.isClosed = false;
            this.buffer = new StringBuilder(length);
            this.startOffset = start;
            this.endOffset = start + length;
        }
        
        public void close() {
            if (!this.isClosed) {
                this.isClosed = true;
            }
        }
        
        public int getCharCount() {
            return this.endOffset - this.startOffset;
        }
        
        public int getStart() {
            return this.startOffset;
        }
        
        public boolean isClosed() {
            return this.isClosed;
        }
        
        @Override
        public String toString() {
            return this.buffer.toString();
        }
        
        void write(final String string) {
            this.buffer.append(string);
        }
        
        void write(final String string, final int offset) {
            if (offset < 0 || offset > this.buffer.length()) {
                return;
            }
            this.buffer.insert(offset, string);
        }
        
        void write(final int i) {
            this.buffer.append(i);
        }
        
        void write(final char i) {
            this.buffer.append(i);
        }
        
        public void writeLine(final String line, final int lineOffset) {
            if (this.isClosed) {
                SWT.error(39);
            }
            final int writeOffset = this.startOffset - lineOffset;
            final int lineLength = line.length();
            if (writeOffset >= lineLength) {
                return;
            }
            int lineIndex;
            if (writeOffset > 0) {
                lineIndex = writeOffset;
            }
            else {
                lineIndex = 0;
            }
            final int copyEnd = Math.min(lineLength, this.endOffset - lineOffset);
            if (lineIndex < copyEnd) {
                this.write(line.substring(lineIndex, copyEnd));
            }
        }
        
        public void writeLineDelimiter(final String lineDelimiter) {
            if (this.isClosed) {
                SWT.error(39);
            }
            this.write(lineDelimiter);
        }
    }
}
