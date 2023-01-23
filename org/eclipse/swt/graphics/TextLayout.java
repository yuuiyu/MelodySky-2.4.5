//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.gdip.*;
import java.util.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public final class TextLayout extends Resource
{
    Font font;
    String text;
    String segmentsText;
    int lineSpacingInPoints;
    int ascentInPixels;
    int descentInPixels;
    int alignment;
    int wrapWidth;
    int orientation;
    int textDirection;
    int indent;
    int wrapIndent;
    boolean justify;
    int[] tabs;
    int[] segments;
    char[] segmentsChars;
    StyleItem[] styles;
    int stylesCount;
    StyleItem[] allRuns;
    StyleItem[][] runs;
    int[] lineOffset;
    int[] lineY;
    int[] lineWidth;
    IMLangFontLink2 mLangFontLink2;
    int verticalIndentInPoints;
    static final char LTR_MARK = '\u200e';
    static final char RTL_MARK = '\u200f';
    static final int SCRIPT_VISATTR_SIZEOF = 2;
    static final int GOFFSET_SIZEOF = 8;
    static final int MERGE_MAX = 512;
    static final int TOO_MANY_RUNS = 1024;
    static final int MAX_RUN_LENGTH = 32000;
    static final int MAX_SEARCH_RUN_BREAK = 1000;
    static final int UNDERLINE_IME_DOT = 65536;
    static final int UNDERLINE_IME_DASH = 131072;
    static final int UNDERLINE_IME_THICK = 196608;
    
    public TextLayout(final Device device) {
        super(device);
        final int wrapWidth = -1;
        this.descentInPixels = -1;
        this.ascentInPixels = -1;
        this.wrapWidth = -1;
        this.lineSpacingInPoints = 0;
        this.verticalIndentInPoints = 0;
        this.orientation = 33554432;
        this.textDirection = 33554432;
        (this.styles = new StyleItem[2])[0] = new StyleItem();
        this.styles[1] = new StyleItem();
        this.stylesCount = 2;
        this.text = "";
        final long[] ppv = { 0L };
        OS.OleInitialize(0L);
        if (COM.CoCreateInstance(COM.CLSID_CMultiLanguage, 0L, 1, COM.IID_IMLangFontLink2, ppv) == 0) {
            this.mLangFontLink2 = new IMLangFontLink2(ppv[0]);
        }
        this.init();
    }
    
    RECT addClipRect(final StyleItem run, RECT clipRect, final RECT rect, final int selectionStart, final int selectionEnd) {
        if (rect != null) {
            if (clipRect == null) {
                clipRect = new RECT();
                OS.SetRect(clipRect, -1, rect.top, -1, rect.bottom);
            }
            final boolean isRTL = (this.orientation & 0x4000000) != 0x0;
            if (run.start <= selectionStart && selectionStart <= run.start + run.length) {
                if (run.analysis.fRTL ^ isRTL) {
                    clipRect.right = rect.left;
                }
                else {
                    clipRect.left = rect.left;
                }
            }
            if (run.start <= selectionEnd && selectionEnd <= run.start + run.length) {
                if (run.analysis.fRTL ^ isRTL) {
                    clipRect.left = rect.right;
                }
                else {
                    clipRect.right = rect.right;
                }
            }
        }
        return clipRect;
    }
    
    void breakRun(final StyleItem run) {
        if (run.psla != 0L) {
            return;
        }
        final char[] chars = new char[run.length];
        this.segmentsText.getChars(run.start, run.start + run.length, chars, 0);
        final long hHeap = OS.GetProcessHeap();
        run.pslaAllocSize = SCRIPT_LOGATTR.sizeof * chars.length;
        run.psla = OS.HeapAlloc(hHeap, 8, SCRIPT_LOGATTR.sizeof * chars.length);
        if (run.psla == 0L) {
            SWT.error(2);
        }
        OS.ScriptBreak(chars, chars.length, run.analysis, run.psla);
    }
    
    void checkLayout() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
    }
    
    void computeRuns(final GC gc) {
        if (this.runs != null) {
            return;
        }
        final long hDC = (gc != null) ? gc.handle : this.device.internal_new_GC((GCData)null);
        final long srcHdc = OS.CreateCompatibleDC(hDC);
        this.allRuns = this.itemize();
        for (int i = 0; i < this.allRuns.length - 1; ++i) {
            final StyleItem run = this.allRuns[i];
            OS.SelectObject(srcHdc, this.getItemFont(run));
            this.shape(srcHdc, run);
        }
        final SCRIPT_LOGATTR logAttr = new SCRIPT_LOGATTR();
        final SCRIPT_PROPERTIES properties = new SCRIPT_PROPERTIES();
        int lineWidth = this.indent;
        int lineStart = 0;
        int lineCount = 1;
        for (int j = 0; j < this.allRuns.length - 1; ++j) {
            StyleItem run2 = this.allRuns[j];
            if (this.tabs != null && run2.tab) {
                int tabsLength;
                int k;
                for (tabsLength = this.tabs.length, k = 0; k < tabsLength; ++k) {
                    if (this.tabs[k] > lineWidth) {
                        run2.width = this.tabs[k] - lineWidth;
                        break;
                    }
                }
                if (k == tabsLength) {
                    int tabX = this.tabs[tabsLength - 1];
                    final int lastTabWidth = (tabsLength > 1) ? (this.tabs[tabsLength - 1] - this.tabs[tabsLength - 2]) : this.tabs[0];
                    if (lastTabWidth > 0) {
                        while (tabX <= lineWidth) {
                            tabX += lastTabWidth;
                        }
                        run2.width = tabX - lineWidth;
                    }
                }
                int length = run2.length;
                if (length > 1) {
                    final int stop = k + length - 1;
                    if (stop < tabsLength) {
                        final StyleItem styleItem10;
                        final StyleItem styleItem = styleItem10 = run2;
                        styleItem10.width += this.tabs[stop] - this.tabs[k];
                    }
                    else {
                        if (k < tabsLength) {
                            final StyleItem styleItem11;
                            final StyleItem styleItem2 = styleItem11 = run2;
                            styleItem11.width += this.tabs[tabsLength - 1] - this.tabs[k];
                            length -= tabsLength - 1 - k;
                        }
                        final int lastTabWidth2 = (tabsLength > 1) ? (this.tabs[tabsLength - 1] - this.tabs[tabsLength - 2]) : this.tabs[0];
                        final StyleItem styleItem12;
                        final StyleItem styleItem3 = styleItem12 = run2;
                        styleItem12.width += lastTabWidth2 * (length - 1);
                    }
                }
            }
            if (this.wrapWidth != -1 && lineWidth + run2.width > this.wrapWidth && !run2.tab && !run2.lineBreak) {
                int start = 0;
                final int[] piDx = new int[run2.length];
                if (run2.style != null && run2.style.metrics != null) {
                    piDx[0] = run2.width;
                }
                else {
                    OS.ScriptGetLogicalWidths(run2.analysis, run2.length, run2.glyphCount, run2.advances, run2.clusters, run2.visAttrs, piDx);
                }
                for (int width = 0, maxWidth = this.wrapWidth - lineWidth; width + piDx[start] < maxWidth; width += piDx[start++]) {}
                final int firstStart = start;
                final int firstIndice = j;
                while (j >= lineStart) {
                    this.breakRun(run2);
                    while (start >= 0) {
                        OS.MoveMemory(logAttr, run2.psla + start * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                        if (logAttr.fSoftBreak) {
                            break;
                        }
                        if (logAttr.fWhiteSpace) {
                            break;
                        }
                        --start;
                    }
                    if (start == 0 && j != lineStart && !run2.tab && logAttr.fSoftBreak && !logAttr.fWhiteSpace) {
                        OS.MoveMemory(properties, this.device.scripts[run2.analysis.eScript], SCRIPT_PROPERTIES.sizeof);
                        final int langID = properties.langid;
                        final StyleItem pRun = this.allRuns[j - 1];
                        OS.MoveMemory(properties, this.device.scripts[pRun.analysis.eScript], SCRIPT_PROPERTIES.sizeof);
                        if (properties.langid == langID || langID == 0 || properties.langid == 0) {
                            this.breakRun(pRun);
                            OS.MoveMemory(logAttr, pRun.psla + (pRun.length - 1) * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                            if (!logAttr.fWhiteSpace) {
                                start = -1;
                            }
                        }
                    }
                    if (start >= 0) {
                        break;
                    }
                    if (j == lineStart) {
                        break;
                    }
                    run2 = this.allRuns[--j];
                    start = run2.length - 1;
                }
                boolean wrapEntireRun = start == 0 && j != lineStart && !run2.tab;
                if (wrapEntireRun) {
                    this.breakRun(run2);
                    OS.MoveMemory(logAttr, run2.psla + start * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                    wrapEntireRun = !logAttr.fWhiteSpace;
                }
                if (wrapEntireRun) {
                    run2 = this.allRuns[--j];
                    start = run2.length;
                }
                else if (start <= 0 && j == lineStart) {
                    if (firstStart == 0 && firstIndice > lineStart) {
                        j = firstIndice - 1;
                        run2 = this.allRuns[j];
                        start = run2.length;
                    }
                    else {
                        j = firstIndice;
                        run2 = this.allRuns[j];
                        start = Math.max(1, firstStart);
                    }
                }
                this.breakRun(run2);
                while (start < run2.length) {
                    OS.MoveMemory(logAttr, run2.psla + start * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                    if (!logAttr.fWhiteSpace) {
                        break;
                    }
                    ++start;
                }
                if (0 < start && start < run2.length) {
                    final StyleItem newRun = new StyleItem();
                    newRun.start = run2.start + start;
                    newRun.length = run2.length - start;
                    newRun.style = run2.style;
                    newRun.analysis = this.cloneScriptAnalysis(run2.analysis);
                    run2.free();
                    run2.length = start;
                    OS.SelectObject(srcHdc, this.getItemFont(run2));
                    run2.analysis.fNoGlyphIndex = false;
                    this.shape(srcHdc, run2);
                    OS.SelectObject(srcHdc, this.getItemFont(newRun));
                    newRun.analysis.fNoGlyphIndex = false;
                    this.shape(srcHdc, newRun);
                    final StyleItem[] newAllRuns = new StyleItem[this.allRuns.length + 1];
                    System.arraycopy(this.allRuns, 0, newAllRuns, 0, j + 1);
                    System.arraycopy(this.allRuns, j + 1, newAllRuns, j + 2, this.allRuns.length - j - 1);
                    (this.allRuns = newAllRuns)[j + 1] = newRun;
                }
                if (j != this.allRuns.length - 2) {
                    final StyleItem styleItem4 = run2;
                    final StyleItem styleItem5 = run2;
                    final boolean b = true;
                    styleItem5.lineBreak = true;
                    styleItem4.softBreak = true;
                }
            }
            lineWidth += run2.width;
            if (run2.lineBreak) {
                lineStart = j + 1;
                lineWidth = (run2.softBreak ? this.wrapIndent : this.indent);
                ++lineCount;
            }
        }
        lineWidth = 0;
        this.runs = new StyleItem[lineCount][];
        this.lineOffset = new int[lineCount + 1];
        this.lineY = new int[lineCount + 1];
        this.lineWidth = new int[lineCount];
        int lineRunCount = 0;
        int line = 0;
        int ascentInPoints = Math.max(0, DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.ascentInPixels));
        int descentInPoints = Math.max(0, DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.descentInPixels));
        final StyleItem[] lineRuns = new StyleItem[this.allRuns.length];
        for (int l = 0; l < this.allRuns.length; ++l) {
            final StyleItem run3 = this.allRuns[l];
            lineRuns[lineRunCount++] = run3;
            lineWidth += run3.width;
            ascentInPoints = Math.max(ascentInPoints, run3.ascentInPoints);
            descentInPoints = Math.max(descentInPoints, run3.descentInPoints);
            if (run3.lineBreak || l == this.allRuns.length - 1) {
                if (lineRunCount == 1 && (l == this.allRuns.length - 1 || !run3.softBreak)) {
                    final TEXTMETRIC lptm = new TEXTMETRIC();
                    OS.SelectObject(srcHdc, this.getItemFont(run3));
                    OS.GetTextMetrics(srcHdc, lptm);
                    run3.ascentInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm.tmAscent);
                    run3.descentInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm.tmDescent);
                    ascentInPoints = Math.max(ascentInPoints, run3.ascentInPoints);
                    descentInPoints = Math.max(descentInPoints, run3.descentInPoints);
                }
                System.arraycopy(lineRuns, 0, this.runs[line] = new StyleItem[lineRunCount], 0, lineRunCount);
                if (this.justify && this.wrapWidth != -1 && run3.softBreak && lineWidth > 0) {
                    int lineIndent = this.wrapIndent;
                    if (line == 0) {
                        lineIndent = this.indent;
                    }
                    else {
                        final StyleItem[] previousLine = this.runs[line - 1];
                        final StyleItem previousRun = previousLine[previousLine.length - 1];
                        if (previousRun.lineBreak && !previousRun.softBreak) {
                            lineIndent = this.indent;
                        }
                    }
                    lineWidth += lineIndent;
                    final long hHeap = OS.GetProcessHeap();
                    int newLineWidth = 0;
                    for (final StyleItem item : this.runs[line]) {
                        final int iDx = item.width * this.wrapWidth / lineWidth;
                        if (iDx != item.width) {
                            item.justify = OS.HeapAlloc(hHeap, 8, item.glyphCount * 4);
                            if (item.justify == 0L) {
                                SWT.error(2);
                            }
                            OS.ScriptJustify(item.visAttrs, item.advances, item.glyphCount, iDx - item.width, 2, item.justify);
                            item.width = iDx;
                        }
                        newLineWidth += item.width;
                    }
                    lineWidth = newLineWidth;
                }
                this.lineWidth[line] = lineWidth;
                StyleItem lastRun = this.runs[line][lineRunCount - 1];
                final int lastOffset = lastRun.start + lastRun.length;
                this.runs[line] = this.reorder(this.runs[line], l == this.allRuns.length - 1);
                lastRun = this.runs[line][lineRunCount - 1];
                if (run3.softBreak && run3 != lastRun) {
                    final StyleItem styleItem6 = run3;
                    final StyleItem styleItem7 = run3;
                    final boolean b2 = false;
                    styleItem7.lineBreak = false;
                    styleItem6.softBreak = false;
                    final StyleItem styleItem8 = lastRun;
                    final StyleItem styleItem9 = lastRun;
                    final boolean b3 = true;
                    styleItem9.lineBreak = true;
                    styleItem8.softBreak = true;
                }
                lineWidth = this.getLineIndent(line);
                for (final StyleItem run4 : this.runs[line]) {
                    run4.x = lineWidth;
                    lineWidth += run4.width;
                }
                ++line;
                this.lineY[line] = this.lineY[line - 1] + ascentInPoints + descentInPoints + this.lineSpacingInPoints;
                this.lineOffset[line] = lastOffset;
                lineRunCount = (lineWidth = 0);
                ascentInPoints = Math.max(0, DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.ascentInPixels));
                descentInPoints = Math.max(0, DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.descentInPixels));
            }
        }
        if (srcHdc != 0L) {
            OS.DeleteDC(srcHdc);
        }
        if (gc == null) {
            this.device.internal_dispose_GC(hDC, (GCData)null);
        }
    }
    
    void destroy() {
        this.freeRuns();
        this.font = null;
        this.text = null;
        this.segmentsText = null;
        this.tabs = null;
        this.styles = null;
        this.runs = null;
        this.lineOffset = null;
        this.lineY = null;
        this.lineWidth = null;
        this.segments = null;
        this.segmentsChars = null;
        if (this.mLangFontLink2 != null) {
            this.mLangFontLink2.Release();
            this.mLangFontLink2 = null;
        }
        OS.OleUninitialize();
    }
    
    SCRIPT_ANALYSIS cloneScriptAnalysis(final SCRIPT_ANALYSIS src) {
        final SCRIPT_ANALYSIS dst = new SCRIPT_ANALYSIS();
        dst.eScript = src.eScript;
        dst.fRTL = src.fRTL;
        dst.fLayoutRTL = src.fLayoutRTL;
        dst.fLinkBefore = src.fLinkBefore;
        dst.fLinkAfter = src.fLinkAfter;
        dst.fLogicalOrder = src.fLogicalOrder;
        dst.fNoGlyphIndex = src.fNoGlyphIndex;
        dst.s = new SCRIPT_STATE();
        dst.s.uBidiLevel = src.s.uBidiLevel;
        dst.s.fOverrideDirection = src.s.fOverrideDirection;
        dst.s.fInhibitSymSwap = src.s.fInhibitSymSwap;
        dst.s.fCharShape = src.s.fCharShape;
        dst.s.fDigitSubstitute = src.s.fDigitSubstitute;
        dst.s.fInhibitLigate = src.s.fInhibitLigate;
        dst.s.fDisplayZWG = src.s.fDisplayZWG;
        dst.s.fArabicNumContext = src.s.fArabicNumContext;
        dst.s.fGcpClusters = src.s.fGcpClusters;
        dst.s.fReserved = src.s.fReserved;
        dst.s.fEngineReserved = src.s.fEngineReserved;
        return dst;
    }
    
    int[] computePolyline(final int left, final int top, final int right, final int bottom) {
        final int height = bottom - top;
        final int width = 2 * height;
        int peaks = Compatibility.ceil(right - left, width);
        if (peaks == 0 && right - left > 2) {
            peaks = 1;
        }
        final int length = (2 * peaks + 1) * 2;
        if (length < 0) {
            return new int[0];
        }
        final int[] coordinates = new int[length];
        for (int i = 0; i < peaks; ++i) {
            final int index = 4 * i;
            coordinates[index] = left + width * i;
            coordinates[index + 1] = bottom;
            coordinates[index + 2] = coordinates[index] + width / 2;
            coordinates[index + 3] = top;
        }
        coordinates[length - 2] = left + width * peaks;
        coordinates[length - 1] = bottom;
        return coordinates;
    }
    
    long createGdipBrush(final int pixel, final int alpha) {
        final int argb = (alpha & 0xFF) << 24 | (pixel >> 16 & 0xFF) | (pixel & 0xFF00) | (pixel & 0xFF) << 16;
        return Gdip.SolidBrush_new(argb);
    }
    
    long createGdipBrush(final Color color, final int alpha) {
        return this.createGdipBrush(color.handle, alpha);
    }
    
    public void draw(final GC gc, final int x, final int y) {
        this.checkLayout();
        this.drawInPixels(gc, DPIUtil.autoScaleUp((Drawable)this.getDevice(), x), DPIUtil.autoScaleUp((Drawable)this.getDevice(), y));
    }
    
    void drawInPixels(final GC gc, final int x, final int y) {
        this.drawInPixels(gc, x, y, -1, -1, null, null);
    }
    
    public void draw(final GC gc, final int x, final int y, final int selectionStart, final int selectionEnd, final Color selectionForeground, final Color selectionBackground) {
        this.checkLayout();
        this.drawInPixels(gc, DPIUtil.autoScaleUp((Drawable)this.getDevice(), x), DPIUtil.autoScaleUp((Drawable)this.getDevice(), y), selectionStart, selectionEnd, selectionForeground, selectionBackground);
    }
    
    void drawInPixels(final GC gc, final int x, final int y, final int selectionStart, final int selectionEnd, final Color selectionForeground, final Color selectionBackground) {
        this.drawInPixels(gc, x, y, selectionStart, selectionEnd, selectionForeground, selectionBackground, 0);
    }
    
    public void draw(final GC gc, final int x, final int y, final int selectionStart, final int selectionEnd, final Color selectionForeground, final Color selectionBackground, final int flags) {
        this.checkLayout();
        this.drawInPixels(gc, DPIUtil.autoScaleUp((Drawable)this.getDevice(), x), DPIUtil.autoScaleUp((Drawable)this.getDevice(), y), selectionStart, selectionEnd, selectionForeground, selectionBackground, flags);
    }
    
    void drawInPixels(final GC gc, final int x, int y, int selectionStart, int selectionEnd, final Color selectionForeground, final Color selectionBackground, final int flags) {
        this.computeRuns(gc);
        if (gc == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        if (selectionForeground != null && selectionForeground.isDisposed()) {
            SWT.error(5);
        }
        if (selectionBackground != null && selectionBackground.isDisposed()) {
            SWT.error(5);
        }
        final int length = this.text.length();
        if (length == 0 && flags == 0) {
            return;
        }
        y += this.getScaledVerticalIndent();
        final long hdc = gc.handle;
        final Rectangle clip = gc.getClippingInPixels();
        final GCData data = gc.data;
        final long gdipGraphics = data.gdipGraphics;
        final int foreground = data.foreground;
        final int linkColor = OS.GetSysColor(26);
        final int alpha = data.alpha;
        final boolean gdip = gdipGraphics != 0L;
        long gdipForeground = 0L;
        long gdipLinkColor = 0L;
        int state = 0;
        if (gdip) {
            gc.checkGC(1);
            gdipForeground = gc.getFgBrush();
        }
        else {
            state = OS.SaveDC(hdc);
            if ((data.style & 0x8000000) != 0x0) {
                OS.SetLayout(hdc, OS.GetLayout(hdc) | 0x1);
            }
        }
        final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
        long gdipSelBackground = 0L;
        long gdipSelForeground = 0L;
        long gdipFont = 0L;
        long lastHFont = 0L;
        long selBackground = 0L;
        int selForeground = 0;
        if (hasSelection || ((flags & 0x100000) != 0x0 && (flags & 0x30000) != 0x0)) {
            final int fgSel = (selectionForeground != null) ? selectionForeground.handle : OS.GetSysColor(14);
            final int bgSel = (selectionBackground != null) ? selectionBackground.handle : OS.GetSysColor(13);
            if (gdip) {
                gdipSelBackground = this.createGdipBrush(bgSel, alpha);
                gdipSelForeground = this.createGdipBrush(fgSel, alpha);
            }
            else {
                selBackground = OS.CreateSolidBrush(bgSel);
                selForeground = fgSel;
            }
            if (hasSelection) {
                selectionStart = this.translateOffset(Math.min(Math.max(0, selectionStart), length - 1));
                selectionEnd = this.translateOffset(Math.min(Math.max(0, selectionEnd), length - 1));
            }
        }
        final RECT rect = new RECT();
        OS.SetBkMode(hdc, 1);
        for (int line = 0; line < this.runs.length; ++line) {
            int drawX = x + this.getLineIndent(line);
            final int drawY = y + DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[line]);
            final StyleItem[] lineRuns = this.runs[line];
            final int lineHeight = DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[line + 1] - this.lineY[line] - this.lineSpacingInPoints);
            if ((flags & 0x30000) != 0x0 && (hasSelection || (flags & 0x100000) != 0x0)) {
                boolean extents = false;
                if (line == this.runs.length - 1 && (flags & 0x100000) != 0x0) {
                    extents = true;
                }
                else {
                    final StyleItem run = lineRuns[lineRuns.length - 1];
                    if (run.lineBreak && !run.softBreak) {
                        if (selectionStart <= run.start && run.start <= selectionEnd) {
                            extents = true;
                        }
                    }
                    else {
                        final int endOffset = run.start + run.length - 1;
                        if (selectionStart <= endOffset && endOffset < selectionEnd && (flags & 0x10000) != 0x0) {
                            extents = true;
                        }
                    }
                }
                if (extents) {
                    int width;
                    if ((flags & 0x10000) != 0x0) {
                        width = 117440511;
                    }
                    else {
                        width = lineHeight / 3;
                    }
                    if (gdip) {
                        Gdip.Graphics_FillRectangle(gdipGraphics, gdipSelBackground, drawX + this.lineWidth[line], drawY, width, lineHeight);
                    }
                    else {
                        OS.SelectObject(hdc, selBackground);
                        OS.PatBlt(hdc, drawX + this.lineWidth[line], drawY, width, lineHeight, 15728673);
                    }
                }
            }
            if (drawX <= clip.x + clip.width && drawX + this.lineWidth[line] >= clip.x) {
                final int alignmentX = drawX;
                for (final StyleItem run2 : lineRuns) {
                    if (run2.length != 0) {
                        if (drawX > clip.x + clip.width) {
                            break;
                        }
                        if (drawX + run2.width >= clip.x && (!run2.lineBreak || run2.softBreak)) {
                            OS.SetRect(rect, drawX, drawY, drawX + run2.width, drawY + lineHeight);
                            if (gdip) {
                                this.drawRunBackgroundGDIP(run2, gdipGraphics, rect, selectionStart, selectionEnd, alpha, gdipSelBackground, hasSelection);
                            }
                            else {
                                this.drawRunBackground(run2, hdc, rect, selectionStart, selectionEnd, selBackground, hasSelection);
                            }
                        }
                        drawX += run2.width;
                    }
                }
                int baselineInPixels = Math.max(0, this.ascentInPixels);
                int lineUnderlinePos = 0;
                for (final StyleItem run3 : lineRuns) {
                    baselineInPixels = Math.max(baselineInPixels, DPIUtil.autoScaleUp((Drawable)this.getDevice(), run3.ascentInPoints));
                    lineUnderlinePos = Math.min(lineUnderlinePos, run3.underlinePos);
                }
                RECT borderClip = null;
                RECT underlineClip = null;
                RECT strikeoutClip = null;
                RECT pRect = null;
                drawX = alignmentX;
                for (int i = 0; i < lineRuns.length; ++i) {
                    final StyleItem run4 = lineRuns[i];
                    final TextStyle style = run4.style;
                    final boolean hasAdorners = style != null && (style.underline || style.strikeout || style.borderStyle != 0);
                    if (run4.length != 0) {
                        if (drawX > clip.x + clip.width) {
                            break;
                        }
                        if (drawX + run4.width >= clip.x) {
                            final boolean skipTab = run4.tab && !hasAdorners;
                            if (!skipTab && (!run4.lineBreak || run4.softBreak) && (style == null || style.metrics == null)) {
                                OS.SetRect(rect, drawX, drawY, drawX + run4.width, drawY + lineHeight);
                                if (gdip) {
                                    final long hFont = this.getItemFont(run4);
                                    if (hFont != lastHFont) {
                                        lastHFont = hFont;
                                        if (gdipFont != 0L) {
                                            Gdip.Font_delete(gdipFont);
                                        }
                                        final long oldFont = OS.SelectObject(hdc, hFont);
                                        gdipFont = Gdip.Font_new(hdc, hFont);
                                        OS.SelectObject(hdc, oldFont);
                                        if (gdipFont == 0L) {
                                            SWT.error(2);
                                        }
                                        if (!Gdip.Font_IsAvailable(gdipFont)) {
                                            Gdip.Font_delete(gdipFont);
                                            gdipFont = 0L;
                                        }
                                    }
                                    long gdipFg = gdipForeground;
                                    if (style != null && style.underline && style.underlineStyle == 4) {
                                        if (gdipLinkColor == 0L) {
                                            gdipLinkColor = this.createGdipBrush(linkColor, alpha);
                                        }
                                        gdipFg = gdipLinkColor;
                                    }
                                    if (gdipFont != 0L && !run4.analysis.fNoGlyphIndex) {
                                        pRect = this.drawRunTextGDIP(gdipGraphics, run4, rect, gdipFont, baselineInPixels, gdipFg, gdipSelForeground, selectionStart, selectionEnd, alpha);
                                    }
                                    else {
                                        final int fg = (style != null && style.underline && style.underlineStyle == 4) ? linkColor : foreground;
                                        pRect = this.drawRunTextGDIPRaster(gdipGraphics, run4, rect, baselineInPixels, fg, selForeground, selectionStart, selectionEnd);
                                    }
                                    underlineClip = this.drawUnderlineGDIP(gdipGraphics, x, drawY + baselineInPixels, lineUnderlinePos, drawY + lineHeight, lineRuns, i, gdipFg, gdipSelForeground, underlineClip, pRect, selectionStart, selectionEnd, alpha, clip);
                                    strikeoutClip = this.drawStrikeoutGDIP(gdipGraphics, x, drawY + baselineInPixels, lineRuns, i, gdipFg, gdipSelForeground, strikeoutClip, pRect, selectionStart, selectionEnd, alpha, clip);
                                    borderClip = this.drawBorderGDIP(gdipGraphics, x, drawY, lineHeight, lineRuns, i, gdipFg, gdipSelForeground, borderClip, pRect, selectionStart, selectionEnd, alpha, clip);
                                }
                                else {
                                    final int fg2 = (style != null && style.underline && style.underlineStyle == 4) ? linkColor : foreground;
                                    pRect = this.drawRunText(hdc, run4, rect, baselineInPixels, fg2, selForeground, selectionStart, selectionEnd);
                                    underlineClip = this.drawUnderline(hdc, x, drawY + baselineInPixels, lineUnderlinePos, drawY + lineHeight, lineRuns, i, fg2, selForeground, underlineClip, pRect, selectionStart, selectionEnd, clip);
                                    strikeoutClip = this.drawStrikeout(hdc, x, drawY + baselineInPixels, lineRuns, i, fg2, selForeground, strikeoutClip, pRect, selectionStart, selectionEnd, clip);
                                    borderClip = this.drawBorder(hdc, x, drawY, lineHeight, lineRuns, i, fg2, selForeground, borderClip, pRect, selectionStart, selectionEnd, clip);
                                }
                            }
                        }
                        drawX += run4.width;
                    }
                }
            }
        }
        if (gdipSelBackground != 0L) {
            Gdip.SolidBrush_delete(gdipSelBackground);
        }
        if (gdipSelForeground != 0L) {
            Gdip.SolidBrush_delete(gdipSelForeground);
        }
        if (gdipLinkColor != 0L) {
            Gdip.SolidBrush_delete(gdipLinkColor);
        }
        if (gdipFont != 0L) {
            Gdip.Font_delete(gdipFont);
        }
        if (state != 0) {
            OS.RestoreDC(hdc, state);
        }
        if (selBackground != 0L) {
            OS.DeleteObject(selBackground);
        }
    }
    
    RECT drawBorder(final long hdc, final int x, final int y, final int lineHeight, final StyleItem[] line, final int index, int color, final int selectionColor, RECT clipRect, final RECT pRect, final int selectionStart, final int selectionEnd, final Rectangle drawClip) {
        final StyleItem run = line[index];
        final TextStyle style = run.style;
        if (style == null) {
            return null;
        }
        if (style.borderStyle == 0) {
            return null;
        }
        clipRect = this.addClipRect(run, clipRect, pRect, selectionStart, selectionEnd);
        final boolean lastRunVisible = drawClip != null && x + run.x + run.width > drawClip.x + drawClip.width;
        if (index + 1 >= line.length || lastRunVisible || line[index + 1].lineBreak || !style.isAdherentBorder(line[index + 1].style)) {
            int left = run.x;
            int start = run.start;
            int end = run.start + run.length - 1;
            for (int i = index; i > 0 && style.isAdherentBorder(line[i - 1].style); --i) {
                left = line[i - 1].x;
                start = Math.min(start, line[i - 1].start);
                end = Math.max(end, line[i - 1].start + line[i - 1].length - 1);
            }
            final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
            final boolean fullSelection = hasSelection && selectionStart <= start && end <= selectionEnd;
            if (style.borderColor != null) {
                color = style.borderColor.handle;
                clipRect = null;
            }
            else if (fullSelection) {
                color = selectionColor;
                clipRect = null;
            }
            else if (style.foreground != null) {
                color = style.foreground.handle;
            }
            final int lineWidth = 1;
            int pattern = 1;
            int lineStyle = 0;
            switch (style.borderStyle) {
                case 2: {
                    lineStyle = 1;
                    pattern = 4;
                    break;
                }
                case 4: {
                    lineStyle = 2;
                    pattern = 2;
                    break;
                }
            }
            final long oldBrush = OS.SelectObject(hdc, OS.GetStockObject(5));
            final LOGBRUSH logBrush = new LOGBRUSH();
            logBrush.lbStyle = 0;
            logBrush.lbColor = color;
            final long newPen = OS.ExtCreatePen(lineStyle | 0x10000, 1, logBrush, 0, null);
            long oldPen = OS.SelectObject(hdc, newPen);
            final RECT drawRect = new RECT();
            OS.SetRect(drawRect, x + left, y, x + run.x + run.width, y + lineHeight);
            if (drawClip != null) {
                if (drawRect.left < drawClip.x) {
                    final int remainder = drawRect.left % pattern;
                    drawRect.left = drawClip.x / pattern * pattern + remainder - pattern;
                }
                if (drawRect.right > drawClip.x + drawClip.width) {
                    final int remainder = drawRect.right % pattern;
                    drawRect.right = (drawClip.x + drawClip.width) / pattern * pattern + remainder + pattern;
                }
            }
            OS.Rectangle(hdc, drawRect.left, drawRect.top, drawRect.right, drawRect.bottom);
            OS.SelectObject(hdc, oldPen);
            OS.DeleteObject(newPen);
            if (clipRect != null) {
                final int state = OS.SaveDC(hdc);
                if (clipRect.left == -1) {
                    clipRect.left = 0;
                }
                if (clipRect.right == -1) {
                    clipRect.right = 524287;
                }
                OS.IntersectClipRect(hdc, clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
                logBrush.lbColor = selectionColor;
                final long selPen = OS.ExtCreatePen(lineStyle | 0x10000, 1, logBrush, 0, null);
                oldPen = OS.SelectObject(hdc, selPen);
                OS.Rectangle(hdc, drawRect.left, drawRect.top, drawRect.right, drawRect.bottom);
                OS.RestoreDC(hdc, state);
                OS.SelectObject(hdc, oldPen);
                OS.DeleteObject(selPen);
            }
            OS.SelectObject(hdc, oldBrush);
            return null;
        }
        return clipRect;
    }
    
    RECT drawBorderGDIP(final long graphics, final int x, final int y, final int lineHeight, final StyleItem[] line, final int index, final long color, final long selectionColor, RECT clipRect, final RECT pRect, final int selectionStart, final int selectionEnd, final int alpha, final Rectangle drawClip) {
        final StyleItem run = line[index];
        final TextStyle style = run.style;
        if (style == null) {
            return null;
        }
        if (style.borderStyle == 0) {
            return null;
        }
        clipRect = this.addClipRect(run, clipRect, pRect, selectionStart, selectionEnd);
        final boolean lastRunVisible = drawClip != null && x + run.x + run.width > drawClip.x + drawClip.width;
        if (index + 1 >= line.length || lastRunVisible || line[index + 1].lineBreak || !style.isAdherentBorder(line[index + 1].style)) {
            int left = run.x;
            int start = run.start;
            int end = run.start + run.length - 1;
            for (int i = index; i > 0 && style.isAdherentBorder(line[i - 1].style); --i) {
                left = line[i - 1].x;
                start = Math.min(start, line[i - 1].start);
                end = Math.max(end, line[i - 1].start + line[i - 1].length - 1);
            }
            final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
            final boolean fullSelection = hasSelection && selectionStart <= start && end <= selectionEnd;
            long brush = color;
            if (style.borderColor != null) {
                brush = this.createGdipBrush(style.borderColor, alpha);
                clipRect = null;
            }
            else if (fullSelection) {
                brush = selectionColor;
                clipRect = null;
            }
            else if (style.foreground != null) {
                brush = this.createGdipBrush(style.foreground, alpha);
            }
            final int lineWidth = 1;
            int lineStyle = 0;
            switch (style.borderStyle) {
                case 2: {
                    lineStyle = 1;
                    break;
                }
                case 4: {
                    lineStyle = 2;
                    break;
                }
            }
            final long pen = Gdip.Pen_new(brush, 1.0f);
            Gdip.Pen_SetDashStyle(pen, lineStyle);
            Gdip.Graphics_SetPixelOffsetMode(graphics, 3);
            final int smoothingMode = Gdip.Graphics_GetSmoothingMode(graphics);
            Gdip.Graphics_SetSmoothingMode(graphics, 3);
            if (clipRect != null) {
                int gstate = Gdip.Graphics_Save(graphics);
                if (clipRect.left == -1) {
                    clipRect.left = 0;
                }
                if (clipRect.right == -1) {
                    clipRect.right = 524287;
                }
                final Rect gdipRect = new Rect();
                gdipRect.X = clipRect.left;
                gdipRect.Y = clipRect.top;
                gdipRect.Width = clipRect.right - clipRect.left;
                gdipRect.Height = clipRect.bottom - clipRect.top;
                Gdip.Graphics_SetClip(graphics, gdipRect, 4);
                Gdip.Graphics_DrawRectangle(graphics, pen, x + left, y, run.x + run.width - left - 1, lineHeight - 1);
                Gdip.Graphics_Restore(graphics, gstate);
                gstate = Gdip.Graphics_Save(graphics);
                Gdip.Graphics_SetClip(graphics, gdipRect, 1);
                final long selPen = Gdip.Pen_new(selectionColor, 1.0f);
                Gdip.Pen_SetDashStyle(selPen, lineStyle);
                Gdip.Graphics_DrawRectangle(graphics, selPen, x + left, y, run.x + run.width - left - 1, lineHeight - 1);
                Gdip.Pen_delete(selPen);
                Gdip.Graphics_Restore(graphics, gstate);
            }
            else {
                Gdip.Graphics_DrawRectangle(graphics, pen, x + left, y, run.x + run.width - left - 1, lineHeight - 1);
            }
            Gdip.Graphics_SetPixelOffsetMode(graphics, 4);
            Gdip.Graphics_SetSmoothingMode(graphics, smoothingMode);
            Gdip.Pen_delete(pen);
            if (brush != selectionColor && brush != color) {
                Gdip.SolidBrush_delete(brush);
            }
            return null;
        }
        return clipRect;
    }
    
    void drawRunBackground(final StyleItem run, final long hdc, final RECT rect, final int selectionStart, final int selectionEnd, final long selBrush, final boolean hasSelection) {
        final int end = run.start + run.length - 1;
        final boolean fullSelection = hasSelection && selectionStart <= run.start && selectionEnd >= end;
        if (fullSelection) {
            OS.SelectObject(hdc, selBrush);
            OS.PatBlt(hdc, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
        }
        else {
            if (run.style != null && run.style.background != null) {
                final int bg = run.style.background.handle;
                final long hBrush = OS.CreateSolidBrush(bg);
                final long oldBrush = OS.SelectObject(hdc, hBrush);
                OS.PatBlt(hdc, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
                OS.SelectObject(hdc, oldBrush);
                OS.DeleteObject(hBrush);
            }
            final boolean partialSelection = hasSelection && selectionStart <= end && run.start <= selectionEnd;
            if (partialSelection) {
                this.getPartialSelection(run, selectionStart, selectionEnd, rect);
                OS.SelectObject(hdc, selBrush);
                OS.PatBlt(hdc, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
            }
        }
    }
    
    void drawRunBackgroundGDIP(final StyleItem run, final long graphics, final RECT rect, final int selectionStart, final int selectionEnd, final int alpha, final long selBrush, final boolean hasSelection) {
        final int end = run.start + run.length - 1;
        final boolean fullSelection = hasSelection && selectionStart <= run.start && selectionEnd >= end;
        if (fullSelection) {
            Gdip.Graphics_FillRectangle(graphics, selBrush, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
        }
        else {
            if (run.style != null && run.style.background != null) {
                final long brush = this.createGdipBrush(run.style.background, alpha);
                Gdip.Graphics_FillRectangle(graphics, brush, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
                Gdip.SolidBrush_delete(brush);
            }
            final boolean partialSelection = hasSelection && selectionStart <= end && run.start <= selectionEnd;
            if (partialSelection) {
                this.getPartialSelection(run, selectionStart, selectionEnd, rect);
                if (rect.left > rect.right) {
                    final int tmp = rect.left;
                    rect.left = rect.right;
                    rect.right = tmp;
                }
                Gdip.Graphics_FillRectangle(graphics, selBrush, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
            }
        }
    }
    
    RECT drawRunText(final long hdc, final StyleItem run, final RECT rect, final int baselineInPixels, int color, final int selectionColor, final int selectionStart, final int selectionEnd) {
        final int end = run.start + run.length - 1;
        final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
        final boolean fullSelection = hasSelection && selectionStart <= run.start && selectionEnd >= end;
        final boolean partialSelection = hasSelection && !fullSelection && selectionStart <= end && run.start <= selectionEnd;
        final int offset = ((this.orientation & 0x4000000) != 0x0) ? -1 : 0;
        final int x = rect.left + offset;
        final int y = rect.top + (baselineInPixels - DPIUtil.autoScaleUp((Drawable)this.getDevice(), run.ascentInPoints));
        final long hFont = this.getItemFont(run);
        OS.SelectObject(hdc, hFont);
        if (fullSelection) {
            color = selectionColor;
        }
        else if (run.style != null && run.style.foreground != null) {
            color = run.style.foreground.handle;
        }
        OS.SetTextColor(hdc, color);
        OS.ScriptTextOut(hdc, run.psc, x, y, 0, null, run.analysis, 0L, 0, run.glyphs, run.glyphCount, run.advances, run.justify, run.goffsets);
        if (partialSelection) {
            this.getPartialSelection(run, selectionStart, selectionEnd, rect);
            OS.SetTextColor(hdc, selectionColor);
            OS.ScriptTextOut(hdc, run.psc, x, y, 4, rect, run.analysis, 0L, 0, run.glyphs, run.glyphCount, run.advances, run.justify, run.goffsets);
        }
        return (fullSelection || partialSelection) ? rect : null;
    }
    
    RECT drawRunTextGDIP(final long graphics, final StyleItem run, final RECT rect, final long gdipFont, final int baselineInPixels, final long color, final long selectionColor, final int selectionStart, final int selectionEnd, final int alpha) {
        final int end = run.start + run.length - 1;
        final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
        final boolean fullSelection = hasSelection && selectionStart <= run.start && selectionEnd >= end;
        final boolean partialSelection = hasSelection && !fullSelection && selectionStart <= end && run.start <= selectionEnd;
        int drawY = rect.top + baselineInPixels;
        if (run.style != null && run.style.rise != 0) {
            drawY -= DPIUtil.autoScaleUp((Drawable)this.getDevice(), run.style.rise);
        }
        final int drawX = rect.left;
        long brush = color;
        if (fullSelection) {
            brush = selectionColor;
        }
        else if (run.style != null && run.style.foreground != null) {
            brush = this.createGdipBrush(run.style.foreground, alpha);
        }
        int gstate = 0;
        Rect gdipRect = null;
        if (partialSelection) {
            gdipRect = new Rect();
            this.getPartialSelection(run, selectionStart, selectionEnd, rect);
            gdipRect.X = rect.left;
            gdipRect.Y = rect.top;
            gdipRect.Width = rect.right - rect.left;
            gdipRect.Height = rect.bottom - rect.top;
            gstate = Gdip.Graphics_Save(graphics);
            Gdip.Graphics_SetClip(graphics, gdipRect, 4);
        }
        int gstateMirrored = 0;
        final boolean isMirrored = (this.orientation & 0x4000000) != 0x0;
        if (isMirrored) {
            switch (Gdip.Brush_GetType(brush)) {
                case 4: {
                    Gdip.LinearGradientBrush_ScaleTransform(brush, -1.0f, 1.0f, 0);
                    Gdip.LinearGradientBrush_TranslateTransform(brush, (float)(-2 * drawX - run.width), 0.0f, 0);
                    break;
                }
                case 2: {
                    Gdip.TextureBrush_ScaleTransform(brush, -1.0f, 1.0f, 0);
                    Gdip.TextureBrush_TranslateTransform(brush, (float)(-2 * drawX - run.width), 0.0f, 0);
                    break;
                }
            }
            gstateMirrored = Gdip.Graphics_Save(graphics);
            Gdip.Graphics_ScaleTransform(graphics, -1.0f, 1.0f, 0);
            Gdip.Graphics_TranslateTransform(graphics, (float)(-2 * drawX - run.width), 0.0f, 0);
        }
        final int[] advances = new int[run.glyphCount];
        final float[] points = new float[run.glyphCount * 2];
        C.memmove(advances, (run.justify != 0L) ? run.justify : run.advances, run.glyphCount * 4);
        int glyphX = drawX;
        int h = 0;
        int j = 0;
        while (h < advances.length) {
            points[j++] = (float)glyphX;
            points[j++] = (float)drawY;
            glyphX += advances[h];
            ++h;
        }
        Gdip.Graphics_DrawDriverString(graphics, run.glyphs, run.glyphCount, gdipFont, brush, points, 0, 0L);
        if (partialSelection) {
            if (isMirrored) {
                Gdip.Graphics_Restore(graphics, gstateMirrored);
            }
            Gdip.Graphics_Restore(graphics, gstate);
            gstate = Gdip.Graphics_Save(graphics);
            Gdip.Graphics_SetClip(graphics, gdipRect, 1);
            if (isMirrored) {
                gstateMirrored = Gdip.Graphics_Save(graphics);
                Gdip.Graphics_ScaleTransform(graphics, -1.0f, 1.0f, 0);
                Gdip.Graphics_TranslateTransform(graphics, (float)(-2 * drawX - run.width), 0.0f, 0);
            }
            Gdip.Graphics_DrawDriverString(graphics, run.glyphs, run.glyphCount, gdipFont, selectionColor, points, 0, 0L);
            Gdip.Graphics_Restore(graphics, gstate);
        }
        if (isMirrored) {
            switch (Gdip.Brush_GetType(brush)) {
                case 4: {
                    Gdip.LinearGradientBrush_ResetTransform(brush);
                    break;
                }
                case 2: {
                    Gdip.TextureBrush_ResetTransform(brush);
                    break;
                }
            }
            Gdip.Graphics_Restore(graphics, gstateMirrored);
        }
        if (brush != selectionColor && brush != color) {
            Gdip.SolidBrush_delete(brush);
        }
        return (fullSelection || partialSelection) ? rect : null;
    }
    
    RECT drawRunTextGDIPRaster(final long graphics, final StyleItem run, final RECT rect, final int baselineInPixels, final int color, final int selectionColor, final int selectionStart, final int selectionEnd) {
        long clipRgn = 0L;
        Gdip.Graphics_SetPixelOffsetMode(graphics, 3);
        final long rgn = Gdip.Region_new();
        if (rgn == 0L) {
            SWT.error(2);
        }
        Gdip.Graphics_GetClip(graphics, rgn);
        if (!Gdip.Region_IsInfinite(rgn, graphics)) {
            clipRgn = Gdip.Region_GetHRGN(rgn, graphics);
        }
        Gdip.Region_delete(rgn);
        Gdip.Graphics_SetPixelOffsetMode(graphics, 4);
        float[] lpXform = null;
        final long matrix = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        if (matrix == 0L) {
            SWT.error(2);
        }
        Gdip.Graphics_GetTransform(graphics, matrix);
        if (!Gdip.Matrix_IsIdentity(matrix)) {
            lpXform = new float[6];
            Gdip.Matrix_GetElements(matrix, lpXform);
        }
        Gdip.Matrix_delete(matrix);
        final long hdc = Gdip.Graphics_GetHDC(graphics);
        final int state = OS.SaveDC(hdc);
        if (lpXform != null) {
            OS.SetGraphicsMode(hdc, 2);
            OS.SetWorldTransform(hdc, lpXform);
        }
        if (clipRgn != 0L) {
            OS.SelectClipRgn(hdc, clipRgn);
            OS.DeleteObject(clipRgn);
        }
        if ((this.orientation & 0x4000000) != 0x0) {
            OS.SetLayout(hdc, OS.GetLayout(hdc) | 0x1);
        }
        OS.SetBkMode(hdc, 1);
        final RECT pRect = this.drawRunText(hdc, run, rect, baselineInPixels, color, selectionColor, selectionStart, selectionEnd);
        OS.RestoreDC(hdc, state);
        Gdip.Graphics_ReleaseHDC(graphics, hdc);
        return pRect;
    }
    
    RECT drawStrikeout(final long hdc, final int x, final int baselineInPixels, final StyleItem[] line, final int index, int color, final int selectionColor, RECT clipRect, final RECT pRect, final int selectionStart, final int selectionEnd, final Rectangle drawClip) {
        final StyleItem run = line[index];
        final TextStyle style = run.style;
        if (style == null) {
            return null;
        }
        if (!style.strikeout) {
            return null;
        }
        clipRect = this.addClipRect(run, clipRect, pRect, selectionStart, selectionEnd);
        final boolean lastRunVisible = drawClip != null && x + run.x + run.width > drawClip.x + drawClip.width;
        if (index + 1 >= line.length || lastRunVisible || line[index + 1].lineBreak || !style.isAdherentStrikeout(line[index + 1].style)) {
            int left = run.x;
            int start = run.start;
            int end = run.start + run.length - 1;
            for (int i = index; i > 0 && style.isAdherentStrikeout(line[i - 1].style); --i) {
                left = line[i - 1].x;
                start = Math.min(start, line[i - 1].start);
                end = Math.max(end, line[i - 1].start + line[i - 1].length - 1);
            }
            final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
            final boolean fullSelection = hasSelection && selectionStart <= start && end <= selectionEnd;
            if (style.strikeoutColor != null) {
                color = style.strikeoutColor.handle;
                clipRect = null;
            }
            else if (fullSelection) {
                color = selectionColor;
                clipRect = null;
            }
            else if (style.foreground != null) {
                color = style.foreground.handle;
            }
            final RECT rect = new RECT();
            final int riseInPixels = DPIUtil.autoScaleUp((Drawable)this.getDevice(), style.rise);
            OS.SetRect(rect, x + left, baselineInPixels - run.strikeoutPos - riseInPixels, x + run.x + run.width, baselineInPixels - run.strikeoutPos + run.strikeoutThickness - riseInPixels);
            final long brush = OS.CreateSolidBrush(color);
            OS.FillRect(hdc, rect, brush);
            OS.DeleteObject(brush);
            if (clipRect != null) {
                final long selBrush = OS.CreateSolidBrush(selectionColor);
                if (clipRect.left == -1) {
                    clipRect.left = 0;
                }
                if (clipRect.right == -1) {
                    clipRect.right = 524287;
                }
                OS.SetRect(clipRect, Math.max(rect.left, clipRect.left), rect.top, Math.min(rect.right, clipRect.right), rect.bottom);
                OS.FillRect(hdc, clipRect, selBrush);
                OS.DeleteObject(selBrush);
            }
            return null;
        }
        return clipRect;
    }
    
    RECT drawStrikeoutGDIP(final long graphics, final int x, final int baselineInPixels, final StyleItem[] line, final int index, final long color, final long selectionColor, RECT clipRect, final RECT pRect, final int selectionStart, final int selectionEnd, final int alpha, final Rectangle drawClip) {
        final StyleItem run = line[index];
        final TextStyle style = run.style;
        if (style == null) {
            return null;
        }
        if (!style.strikeout) {
            return null;
        }
        clipRect = this.addClipRect(run, clipRect, pRect, selectionStart, selectionEnd);
        final boolean lastRunVisible = drawClip != null && x + run.x + run.width > drawClip.x + drawClip.width;
        if (index + 1 >= line.length || lastRunVisible || line[index + 1].lineBreak || !style.isAdherentStrikeout(line[index + 1].style)) {
            int left = run.x;
            int start = run.start;
            int end = run.start + run.length - 1;
            for (int i = index; i > 0 && style.isAdherentStrikeout(line[i - 1].style); --i) {
                left = line[i - 1].x;
                start = Math.min(start, line[i - 1].start);
                end = Math.max(end, line[i - 1].start + line[i - 1].length - 1);
            }
            final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
            final boolean fullSelection = hasSelection && selectionStart <= start && end <= selectionEnd;
            long brush = color;
            if (style.strikeoutColor != null) {
                brush = this.createGdipBrush(style.strikeoutColor, alpha);
                clipRect = null;
            }
            else if (fullSelection) {
                brush = selectionColor;
                clipRect = null;
            }
            else if (style.foreground != null) {
                brush = this.createGdipBrush(style.foreground, alpha);
            }
            final int riseInPixels = DPIUtil.autoScaleUp((Drawable)this.getDevice(), style.rise);
            if (clipRect != null) {
                int gstate = Gdip.Graphics_Save(graphics);
                if (clipRect.left == -1) {
                    clipRect.left = 0;
                }
                if (clipRect.right == -1) {
                    clipRect.right = 524287;
                }
                final Rect gdipRect = new Rect();
                gdipRect.X = clipRect.left;
                gdipRect.Y = clipRect.top;
                gdipRect.Width = clipRect.right - clipRect.left;
                gdipRect.Height = clipRect.bottom - clipRect.top;
                Gdip.Graphics_SetClip(graphics, gdipRect, 4);
                Gdip.Graphics_FillRectangle(graphics, brush, x + left, baselineInPixels - run.strikeoutPos - riseInPixels, run.x + run.width - left, run.strikeoutThickness);
                Gdip.Graphics_Restore(graphics, gstate);
                gstate = Gdip.Graphics_Save(graphics);
                Gdip.Graphics_SetClip(graphics, gdipRect, 1);
                Gdip.Graphics_FillRectangle(graphics, selectionColor, x + left, baselineInPixels - run.strikeoutPos - riseInPixels, run.x + run.width - left, run.strikeoutThickness);
                Gdip.Graphics_Restore(graphics, gstate);
            }
            else {
                Gdip.Graphics_FillRectangle(graphics, brush, x + left, baselineInPixels - run.strikeoutPos - riseInPixels, run.x + run.width - left, run.strikeoutThickness);
            }
            if (brush != selectionColor && brush != color) {
                Gdip.SolidBrush_delete(brush);
            }
            return null;
        }
        return clipRect;
    }
    
    RECT drawUnderline(final long hdc, final int x, final int baselineInPixels, final int lineUnderlinePos, final int lineBottom, final StyleItem[] line, final int index, int color, final int selectionColor, RECT clipRect, final RECT pRect, final int selectionStart, final int selectionEnd, final Rectangle drawClip) {
        final StyleItem run = line[index];
        final TextStyle style = run.style;
        if (style == null) {
            return null;
        }
        if (!style.underline) {
            return null;
        }
        clipRect = this.addClipRect(run, clipRect, pRect, selectionStart, selectionEnd);
        final boolean lastRunVisible = drawClip != null && x + run.x + run.width > drawClip.x + drawClip.width;
        if (index + 1 >= line.length || lastRunVisible || line[index + 1].lineBreak || !style.isAdherentUnderline(line[index + 1].style)) {
            int left = run.x;
            int start = run.start;
            int end = run.start + run.length - 1;
            for (int i = index; i > 0 && style.isAdherentUnderline(line[i - 1].style); --i) {
                left = line[i - 1].x;
                start = Math.min(start, line[i - 1].start);
                end = Math.max(end, line[i - 1].start + line[i - 1].length - 1);
            }
            final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
            final boolean fullSelection = hasSelection && selectionStart <= start && end <= selectionEnd;
            if (style.underlineColor != null) {
                color = style.underlineColor.handle;
                clipRect = null;
            }
            else if (fullSelection) {
                color = selectionColor;
                clipRect = null;
            }
            else if (style.foreground != null) {
                color = style.foreground.handle;
            }
            final RECT rect = new RECT();
            final int riseInPixels = DPIUtil.autoScaleUp((Drawable)this.getDevice(), style.rise);
            OS.SetRect(rect, x + left, baselineInPixels - lineUnderlinePos - riseInPixels, x + run.x + run.width, baselineInPixels - lineUnderlinePos + run.underlineThickness - riseInPixels);
            if (clipRect != null) {
                if (clipRect.left == -1) {
                    clipRect.left = 0;
                }
                if (clipRect.right == -1) {
                    clipRect.right = 524287;
                }
                OS.SetRect(clipRect, Math.max(rect.left, clipRect.left), rect.top, Math.min(rect.right, clipRect.right), rect.bottom);
            }
            switch (style.underlineStyle) {
                case 2:
                case 3: {
                    final int squigglyThickness = 1;
                    final int squigglyHeight = 2;
                    final int squigglyY = Math.min(rect.top - 1, lineBottom - 2 - 1);
                    final int[] points = this.computePolyline(rect.left, squigglyY, rect.right, squigglyY + 2);
                    long pen = OS.CreatePen(0, 1, color);
                    long oldPen = OS.SelectObject(hdc, pen);
                    int state = OS.SaveDC(hdc);
                    OS.IntersectClipRect(hdc, rect.left, squigglyY, rect.right + 1, squigglyY + 2 + 1);
                    OS.Polyline(hdc, points, points.length / 2);
                    final int length = points.length;
                    if (length >= 2) {
                        OS.SetPixel(hdc, points[length - 2], points[length - 1], color);
                    }
                    OS.SelectObject(hdc, oldPen);
                    OS.DeleteObject(pen);
                    OS.RestoreDC(hdc, state);
                    if (clipRect != null) {
                        pen = OS.CreatePen(0, 1, selectionColor);
                        oldPen = OS.SelectObject(hdc, pen);
                        state = OS.SaveDC(hdc);
                        OS.IntersectClipRect(hdc, clipRect.left, squigglyY, clipRect.right + 1, squigglyY + 2 + 1);
                        OS.Polyline(hdc, points, points.length / 2);
                        if (length >= 2) {
                            OS.SetPixel(hdc, points[length - 2], points[length - 1], selectionColor);
                        }
                        OS.SelectObject(hdc, oldPen);
                        OS.DeleteObject(pen);
                        OS.RestoreDC(hdc, state);
                        break;
                    }
                    break;
                }
                case 0:
                case 1:
                case 4:
                case 196608: {
                    if (style.underlineStyle == 196608) {
                        final RECT rect4;
                        final RECT rect2 = rect4 = rect;
                        rect4.top -= run.underlineThickness;
                        if (clipRect != null) {
                            final RECT rect5;
                            final RECT rect3 = rect5 = clipRect;
                            rect5.top -= run.underlineThickness;
                        }
                    }
                    final int bottom = (style.underlineStyle == 1) ? (rect.bottom + run.underlineThickness * 2) : rect.bottom;
                    if (bottom > lineBottom) {
                        OS.OffsetRect(rect, 0, lineBottom - bottom);
                        if (clipRect != null) {
                            OS.OffsetRect(clipRect, 0, lineBottom - bottom);
                        }
                    }
                    final long brush = OS.CreateSolidBrush(color);
                    OS.FillRect(hdc, rect, brush);
                    if (style.underlineStyle == 1) {
                        OS.SetRect(rect, rect.left, rect.top + run.underlineThickness * 2, rect.right, rect.bottom + run.underlineThickness * 2);
                        OS.FillRect(hdc, rect, brush);
                    }
                    OS.DeleteObject(brush);
                    if (clipRect != null) {
                        final long selBrush = OS.CreateSolidBrush(selectionColor);
                        OS.FillRect(hdc, clipRect, selBrush);
                        if (style.underlineStyle == 1) {
                            OS.SetRect(clipRect, clipRect.left, rect.top, clipRect.right, rect.bottom);
                            OS.FillRect(hdc, clipRect, selBrush);
                        }
                        OS.DeleteObject(selBrush);
                        break;
                    }
                    break;
                }
                case 65536:
                case 131072: {
                    final int penStyle = (style.underlineStyle == 131072) ? 1 : 2;
                    long pen2 = OS.CreatePen(penStyle, 1, color);
                    long oldPen2 = OS.SelectObject(hdc, pen2);
                    final int descentInPixels = DPIUtil.autoScaleUp((Drawable)this.getDevice(), run.descentInPoints);
                    OS.SetRect(rect, rect.left, baselineInPixels + descentInPixels, rect.right, baselineInPixels + descentInPixels + run.underlineThickness);
                    OS.MoveToEx(hdc, rect.left, rect.top, 0L);
                    OS.LineTo(hdc, rect.right, rect.top);
                    OS.SelectObject(hdc, oldPen2);
                    OS.DeleteObject(pen2);
                    if (clipRect != null) {
                        pen2 = OS.CreatePen(penStyle, 1, selectionColor);
                        oldPen2 = OS.SelectObject(hdc, pen2);
                        OS.SetRect(clipRect, clipRect.left, rect.top, clipRect.right, rect.bottom);
                        OS.MoveToEx(hdc, clipRect.left, clipRect.top, 0L);
                        OS.LineTo(hdc, clipRect.right, clipRect.top);
                        OS.SelectObject(hdc, oldPen2);
                        OS.DeleteObject(pen2);
                        break;
                    }
                    break;
                }
            }
            return null;
        }
        return clipRect;
    }
    
    RECT drawUnderlineGDIP(final long graphics, final int x, final int baselineInPixels, final int lineUnderlinePos, final int lineBottom, final StyleItem[] line, final int index, final long color, final long selectionColor, RECT clipRect, final RECT pRect, final int selectionStart, final int selectionEnd, final int alpha, final Rectangle drawClip) {
        final StyleItem run = line[index];
        final TextStyle style = run.style;
        if (style == null) {
            return null;
        }
        if (!style.underline) {
            return null;
        }
        clipRect = this.addClipRect(run, clipRect, pRect, selectionStart, selectionEnd);
        final boolean lastRunVisible = drawClip != null && x + run.x + run.width > drawClip.x + drawClip.width;
        if (index + 1 >= line.length || lastRunVisible || line[index + 1].lineBreak || !style.isAdherentUnderline(line[index + 1].style)) {
            int left = run.x;
            int start = run.start;
            int end = run.start + run.length - 1;
            for (int i = index; i > 0 && style.isAdherentUnderline(line[i - 1].style); --i) {
                left = line[i - 1].x;
                start = Math.min(start, line[i - 1].start);
                end = Math.max(end, line[i - 1].start + line[i - 1].length - 1);
            }
            final boolean hasSelection = selectionStart <= selectionEnd && selectionStart != -1 && selectionEnd != -1;
            final boolean fullSelection = hasSelection && selectionStart <= start && end <= selectionEnd;
            long brush = color;
            if (style.underlineColor != null) {
                brush = this.createGdipBrush(style.underlineColor, alpha);
                clipRect = null;
            }
            else if (fullSelection) {
                brush = selectionColor;
                clipRect = null;
            }
            else if (style.foreground != null) {
                brush = this.createGdipBrush(style.foreground, alpha);
            }
            final RECT rect = new RECT();
            final int riseInPixels = DPIUtil.autoScaleUp((Drawable)this.getDevice(), style.rise);
            OS.SetRect(rect, x + left, baselineInPixels - lineUnderlinePos - riseInPixels, x + run.x + run.width, baselineInPixels - lineUnderlinePos + run.underlineThickness - riseInPixels);
            Rect gdipRect = null;
            if (clipRect != null) {
                if (clipRect.left == -1) {
                    clipRect.left = 0;
                }
                if (clipRect.right == -1) {
                    clipRect.right = 524287;
                }
                OS.SetRect(clipRect, Math.max(rect.left, clipRect.left), rect.top, Math.min(rect.right, clipRect.right), rect.bottom);
                gdipRect = new Rect();
                gdipRect.X = clipRect.left;
                gdipRect.Y = clipRect.top;
                gdipRect.Width = clipRect.right - clipRect.left;
                gdipRect.Height = clipRect.bottom - clipRect.top;
            }
            int gstate = 0;
            Gdip.Graphics_SetPixelOffsetMode(graphics, 3);
            final int smoothingMode = Gdip.Graphics_GetSmoothingMode(graphics);
            Gdip.Graphics_SetSmoothingMode(graphics, 3);
            switch (style.underlineStyle) {
                case 2:
                case 3: {
                    final int squigglyThickness = 1;
                    final int squigglyHeight = 2;
                    final int squigglyY = Math.min(rect.top - 1, lineBottom - 2 - 1);
                    final int[] points = this.computePolyline(rect.left, squigglyY, rect.right, squigglyY + 2);
                    final long pen = Gdip.Pen_new(brush, 1.0f);
                    gstate = Gdip.Graphics_Save(graphics);
                    if (gdipRect != null) {
                        Gdip.Graphics_SetClip(graphics, gdipRect, 4);
                    }
                    else {
                        final Rect r = new Rect();
                        r.X = rect.left;
                        r.Y = squigglyY;
                        r.Width = rect.right - rect.left;
                        r.Height = 3;
                        Gdip.Graphics_SetClip(graphics, r, 1);
                    }
                    Gdip.Graphics_DrawLines(graphics, pen, points, points.length / 2);
                    if (gdipRect != null) {
                        final long selPen = Gdip.Pen_new(selectionColor, 1.0f);
                        Gdip.Graphics_Restore(graphics, gstate);
                        gstate = Gdip.Graphics_Save(graphics);
                        Gdip.Graphics_SetClip(graphics, gdipRect, 1);
                        Gdip.Graphics_DrawLines(graphics, selPen, points, points.length / 2);
                        Gdip.Pen_delete(selPen);
                    }
                    Gdip.Graphics_Restore(graphics, gstate);
                    Gdip.Pen_delete(pen);
                    if (gstate != 0) {
                        Gdip.Graphics_Restore(graphics, gstate);
                        break;
                    }
                    break;
                }
                case 0:
                case 1:
                case 4:
                case 196608: {
                    if (style.underlineStyle == 196608) {
                        final RECT rect3;
                        final RECT rect2 = rect3 = rect;
                        rect3.top -= run.underlineThickness;
                    }
                    final int bottom = (style.underlineStyle == 1) ? (rect.bottom + run.underlineThickness * 2) : rect.bottom;
                    if (bottom > lineBottom) {
                        OS.OffsetRect(rect, 0, lineBottom - bottom);
                    }
                    if (gdipRect != null) {
                        gdipRect.Y = rect.top;
                        if (style.underlineStyle == 196608) {
                            gdipRect.Height = run.underlineThickness * 2;
                        }
                        if (style.underlineStyle == 1) {
                            gdipRect.Height = run.underlineThickness * 3;
                        }
                        gstate = Gdip.Graphics_Save(graphics);
                        Gdip.Graphics_SetClip(graphics, gdipRect, 4);
                    }
                    Gdip.Graphics_FillRectangle(graphics, brush, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
                    if (style.underlineStyle == 1) {
                        Gdip.Graphics_FillRectangle(graphics, brush, rect.left, rect.top + run.underlineThickness * 2, rect.right - rect.left, rect.bottom - rect.top);
                    }
                    if (gdipRect != null) {
                        Gdip.Graphics_Restore(graphics, gstate);
                        gstate = Gdip.Graphics_Save(graphics);
                        Gdip.Graphics_SetClip(graphics, gdipRect, 1);
                        Gdip.Graphics_FillRectangle(graphics, selectionColor, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
                        if (style.underlineStyle == 1) {
                            Gdip.Graphics_FillRectangle(graphics, selectionColor, rect.left, rect.top + run.underlineThickness * 2, rect.right - rect.left, rect.bottom - rect.top);
                        }
                        Gdip.Graphics_Restore(graphics, gstate);
                        break;
                    }
                    break;
                }
                case 65536:
                case 131072: {
                    final long pen2 = Gdip.Pen_new(brush, 1.0f);
                    final int dashStyle = (style.underlineStyle == 65536) ? 2 : 1;
                    Gdip.Pen_SetDashStyle(pen2, dashStyle);
                    if (gdipRect != null) {
                        gstate = Gdip.Graphics_Save(graphics);
                        Gdip.Graphics_SetClip(graphics, gdipRect, 4);
                    }
                    final int descentInPixels = DPIUtil.autoScaleUp((Drawable)this.getDevice(), run.descentInPoints);
                    Gdip.Graphics_DrawLine(graphics, pen2, rect.left, baselineInPixels + descentInPixels, run.width - run.length, baselineInPixels + descentInPixels);
                    if (gdipRect != null) {
                        Gdip.Graphics_Restore(graphics, gstate);
                        gstate = Gdip.Graphics_Save(graphics);
                        Gdip.Graphics_SetClip(graphics, gdipRect, 1);
                        final long selPen2 = Gdip.Pen_new(brush, 1.0f);
                        Gdip.Pen_SetDashStyle(selPen2, dashStyle);
                        Gdip.Graphics_DrawLine(graphics, selPen2, rect.left, baselineInPixels + descentInPixels, run.width - run.length, baselineInPixels + descentInPixels);
                        Gdip.Graphics_Restore(graphics, gstate);
                        Gdip.Pen_delete(selPen2);
                    }
                    Gdip.Pen_delete(pen2);
                    break;
                }
            }
            if (brush != selectionColor && brush != color) {
                Gdip.SolidBrush_delete(brush);
            }
            Gdip.Graphics_SetPixelOffsetMode(graphics, 4);
            Gdip.Graphics_SetSmoothingMode(graphics, smoothingMode);
            return null;
        }
        return clipRect;
    }
    
    void freeRuns() {
        if (this.allRuns == null) {
            return;
        }
        for (final StyleItem run : this.allRuns) {
            run.free();
        }
        this.allRuns = null;
        this.runs = null;
        this.segmentsText = null;
    }
    
    public int getAlignment() {
        this.checkLayout();
        return this.alignment;
    }
    
    public int getAscent() {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.ascentInPixels);
    }
    
    public Rectangle getBounds() {
        this.checkLayout();
        this.computeRuns(null);
        int width = 0;
        if (this.wrapWidth != -1) {
            width = this.wrapWidth;
        }
        else {
            for (int line = 0; line < this.runs.length; ++line) {
                width = Math.max(width, this.lineWidth[line] + this.getLineIndent(line));
            }
        }
        return new Rectangle(0, 0, DPIUtil.autoScaleDown((Drawable)this.getDevice(), width), this.lineY[this.lineY.length - 1] + this.getScaledVerticalIndent());
    }
    
    public Rectangle getBounds(final int start, final int end) {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.getBoundsInPixels(start, end));
    }
    
    Rectangle getBoundsInPixels(int start, int end) {
        this.computeRuns(null);
        int length = this.text.length();
        if (length == 0) {
            return new Rectangle(0, 0, 0, 0);
        }
        if (start > end) {
            return new Rectangle(0, 0, 0, 0);
        }
        start = Math.min(Math.max(0, start), length - 1);
        end = Math.min(Math.max(0, end), length - 1);
        start = this.translateOffset(start);
        end = this.translateOffset(end);
        length = this.segmentsText.length();
        char ch = this.segmentsText.charAt(start);
        if ('\udc00' <= ch && ch <= '\udfff' && start - 1 >= 0) {
            ch = this.segmentsText.charAt(start - 1);
            if ('\ud800' <= ch && ch <= '\udbff') {
                --start;
            }
        }
        ch = this.segmentsText.charAt(end);
        if ('\ud800' <= ch && ch <= '\udbff' && end + 1 < length) {
            ch = this.segmentsText.charAt(end + 1);
            if ('\udc00' <= ch && ch <= '\udfff') {
                ++end;
            }
        }
        int left = Integer.MAX_VALUE;
        int right = 0;
        int top = Integer.MAX_VALUE;
        int bottom = 0;
        final boolean isRTL = (this.orientation & 0x4000000) != 0x0;
        for (int i = 0; i < this.allRuns.length - 1; ++i) {
            final StyleItem run = this.allRuns[i];
            final int runEnd = run.start + run.length;
            if (runEnd > start) {
                if (run.start > end) {
                    break;
                }
                int runLead = run.x;
                int runTrail = run.x + run.width;
                if (run.start <= start && start < runEnd) {
                    int cx = 0;
                    if (run.style != null && run.style.metrics != null) {
                        final GlyphMetrics metrics = run.style.metrics;
                        cx = metrics.getWidthInPixels() * (start - run.start);
                    }
                    else if (!run.tab) {
                        final int iX = this.ScriptCPtoX(start - run.start, false, run);
                        cx = (isRTL ? (run.width - iX) : iX);
                    }
                    if (run.analysis.fRTL ^ isRTL) {
                        runTrail = run.x + cx;
                    }
                    else {
                        runLead = run.x + cx;
                    }
                }
                if (run.start <= end && end < runEnd) {
                    int cx = run.width;
                    if (run.style != null && run.style.metrics != null) {
                        final GlyphMetrics metrics = run.style.metrics;
                        cx = metrics.getWidthInPixels() * (end - run.start + 1);
                    }
                    else if (!run.tab) {
                        final int iX = this.ScriptCPtoX(end - run.start, true, run);
                        cx = (isRTL ? (run.width - iX) : iX);
                    }
                    if (run.analysis.fRTL ^ isRTL) {
                        runLead = run.x + cx;
                    }
                    else {
                        runTrail = run.x + cx;
                    }
                }
                int lineIndex;
                for (lineIndex = 0; lineIndex < this.runs.length && this.lineOffset[lineIndex + 1] <= run.start; ++lineIndex) {}
                left = Math.min(left, runLead);
                right = Math.max(right, runTrail);
                top = Math.min(top, DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[lineIndex]));
                bottom = Math.max(bottom, DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[lineIndex + 1] - this.lineSpacingInPoints));
            }
        }
        return new Rectangle(left, top, right - left, bottom - top + this.getScaledVerticalIndent());
    }
    
    public int getDescent() {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.descentInPixels);
    }
    
    public Font getFont() {
        this.checkLayout();
        return this.font;
    }
    
    public int getIndent() {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.getIndentInPixels());
    }
    
    int getIndentInPixels() {
        return this.indent;
    }
    
    public boolean getJustify() {
        this.checkLayout();
        return this.justify;
    }
    
    long getItemFont(final StyleItem item) {
        if (item.fallbackFont != 0L) {
            return item.fallbackFont;
        }
        if (item.style != null && item.style.font != null) {
            return item.style.font.handle;
        }
        if (this.font != null) {
            return this.font.handle;
        }
        return this.device.systemFont.handle;
    }
    
    public int getLevel(int offset) {
        this.checkLayout();
        this.computeRuns(null);
        final int length = this.text.length();
        if (0 > offset || offset > length) {
            SWT.error(6);
        }
        offset = this.translateOffset(offset);
        for (int i = 1; i < this.allRuns.length; ++i) {
            if (this.allRuns[i].start > offset) {
                return this.allRuns[i - 1].analysis.s.uBidiLevel;
            }
        }
        return ((this.resolveTextDirection() & 0x4000000) != 0x0) ? 1 : 0;
    }
    
    public Rectangle getLineBounds(final int lineIndex) {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.getLineBoundsInPixels(lineIndex));
    }
    
    Rectangle getLineBoundsInPixels(final int lineIndex) {
        this.computeRuns(null);
        if (0 > lineIndex || lineIndex >= this.runs.length) {
            SWT.error(6);
        }
        final int x = this.getLineIndent(lineIndex);
        final int y = DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[lineIndex]);
        final int width = this.lineWidth[lineIndex];
        final int height = DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[lineIndex + 1] - this.lineY[lineIndex] - this.lineSpacingInPoints);
        return new Rectangle(x, y, width, height);
    }
    
    public int getLineCount() {
        this.checkLayout();
        this.computeRuns(null);
        return this.runs.length;
    }
    
    int getLineIndent(final int lineIndex) {
        int lineIndent = this.wrapIndent;
        if (lineIndex == 0) {
            lineIndent = this.indent;
        }
        else {
            final StyleItem[] previousLine = this.runs[lineIndex - 1];
            final StyleItem previousRun = previousLine[previousLine.length - 1];
            if (previousRun.lineBreak && !previousRun.softBreak) {
                lineIndent = this.indent;
            }
        }
        if (this.wrapWidth != -1) {
            boolean partialLine = true;
            if (this.justify) {
                final StyleItem[] lineRun = this.runs[lineIndex];
                if (lineRun[lineRun.length - 1].softBreak) {
                    partialLine = false;
                }
            }
            if (partialLine) {
                final int lineWidth = this.lineWidth[lineIndex] + lineIndent;
                switch (this.alignment) {
                    case 16777216: {
                        lineIndent += (this.wrapWidth - lineWidth) / 2;
                        break;
                    }
                    case 131072: {
                        lineIndent += this.wrapWidth - lineWidth;
                        break;
                    }
                }
            }
        }
        return lineIndent;
    }
    
    public int getLineIndex(int offset) {
        this.checkLayout();
        this.computeRuns(null);
        final int length = this.text.length();
        if (0 > offset || offset > length) {
            SWT.error(6);
        }
        offset = this.translateOffset(offset);
        for (int line = 0; line < this.runs.length; ++line) {
            if (this.lineOffset[line + 1] > offset) {
                return line;
            }
        }
        return this.runs.length - 1;
    }
    
    public FontMetrics getLineMetrics(final int lineIndex) {
        this.checkLayout();
        this.computeRuns(null);
        if (0 > lineIndex || lineIndex >= this.runs.length) {
            SWT.error(6);
        }
        final long hDC = this.device.internal_new_GC((GCData)null);
        final long srcHdc = OS.CreateCompatibleDC(hDC);
        final TEXTMETRIC lptm = new TEXTMETRIC();
        OS.SelectObject(srcHdc, (this.font != null) ? this.font.handle : this.device.systemFont.handle);
        OS.GetTextMetrics(srcHdc, lptm);
        OS.DeleteDC(srcHdc);
        this.device.internal_dispose_GC(hDC, (GCData)null);
        int ascentInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), Math.max(lptm.tmAscent, this.ascentInPixels));
        int descentInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), Math.max(lptm.tmDescent, this.descentInPixels));
        int leadingInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm.tmInternalLeading);
        if (this.text.length() != 0) {
            for (final StyleItem run : this.runs[lineIndex]) {
                if (run.ascentInPoints > ascentInPoints) {
                    ascentInPoints = run.ascentInPoints;
                    leadingInPoints = run.leadingInPoints;
                }
                descentInPoints = Math.max(descentInPoints, run.descentInPoints);
            }
        }
        lptm.tmAscent = DPIUtil.autoScaleUp((Drawable)this.getDevice(), ascentInPoints);
        lptm.tmDescent = DPIUtil.autoScaleUp((Drawable)this.getDevice(), descentInPoints);
        lptm.tmHeight = DPIUtil.autoScaleUp((Drawable)this.getDevice(), ascentInPoints + descentInPoints);
        lptm.tmInternalLeading = DPIUtil.autoScaleUp((Drawable)this.getDevice(), leadingInPoints);
        lptm.tmAveCharWidth = 0;
        return FontMetrics.win32_new(lptm);
    }
    
    public int[] getLineOffsets() {
        this.checkLayout();
        this.computeRuns(null);
        final int[] offsets = new int[this.lineOffset.length];
        for (int i = 0; i < offsets.length; ++i) {
            offsets[i] = this.untranslateOffset(this.lineOffset[i]);
        }
        return offsets;
    }
    
    public Point getLocation(final int offset, final boolean trailing) {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.getLocationInPixels(offset, trailing));
    }
    
    Point getLocationInPixels(int offset, final boolean trailing) {
        this.computeRuns(null);
        int length = this.text.length();
        if (0 > offset || offset > length) {
            SWT.error(6);
        }
        length = this.segmentsText.length();
        int line;
        for (offset = this.translateOffset(offset), line = 0; line < this.runs.length && this.lineOffset[line + 1] <= offset; ++line) {}
        line = Math.min(line, this.runs.length - 1);
        if (offset == length) {
            return new Point(this.getLineIndent(line) + this.lineWidth[line], DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[line]));
        }
        char ch = this.segmentsText.charAt(offset);
        if (trailing) {
            if ('\ud800' <= ch && ch <= '\udbff' && offset + 1 < length) {
                ch = this.segmentsText.charAt(offset + 1);
                if ('\udc00' <= ch && ch <= '\udfff') {
                    ++offset;
                }
            }
        }
        else if ('\udc00' <= ch && ch <= '\udfff' && offset - 1 >= 0) {
            ch = this.segmentsText.charAt(offset - 1);
            if ('\ud800' <= ch && ch <= '\udbff') {
                --offset;
            }
        }
        int low = -1;
        int high = this.allRuns.length;
        while (high - low > 1) {
            final int index = (high + low) / 2;
            final StyleItem run = this.allRuns[index];
            if (run.start > offset) {
                high = index;
            }
            else {
                if (run.start + run.length > offset) {
                    int width;
                    if (run.style != null && run.style.metrics != null) {
                        final GlyphMetrics metrics = run.style.metrics;
                        width = metrics.getWidthInPixels() * (offset - run.start + (trailing ? 1 : 0));
                    }
                    else if (run.tab) {
                        width = ((trailing || offset == length) ? run.width : 0);
                    }
                    else {
                        final int runOffset = offset - run.start;
                        final int iX = this.ScriptCPtoX(runOffset, trailing, run);
                        width = (((this.orientation & 0x4000000) != 0x0) ? (run.width - iX) : iX);
                    }
                    return new Point(run.x + width, DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[line]) + this.getScaledVerticalIndent());
                }
                low = index;
            }
        }
        return new Point(0, 0);
    }
    
    private int ScriptCPtoX(final int characterPosition, final boolean trailing, final StyleItem run) {
        final int[] piX = { 0 };
        final long advances = (run.justify != 0L) ? run.justify : run.advances;
        OS.ScriptCPtoX(characterPosition, trailing, run.length, run.glyphCount, run.clusters, run.visAttrs, advances, run.analysis, piX);
        return piX[0];
    }
    
    public int getNextOffset(final int offset, final int movement) {
        this.checkLayout();
        return this._getOffset(offset, movement, true);
    }
    
    int _getOffset(int offset, final int movement, final boolean forward) {
        this.computeRuns(null);
        int length = this.text.length();
        if (0 > offset || offset > length) {
            SWT.error(6, null, " [offset value: " + offset);
        }
        if (forward && offset == length) {
            return length;
        }
        if (!forward && offset == 0) {
            return 0;
        }
        final int step = forward ? 1 : -1;
        if ((movement & 0x1) != 0x0) {
            return offset + step;
        }
        length = this.segmentsText.length();
        offset = this.translateOffset(offset);
        final SCRIPT_LOGATTR logAttr = new SCRIPT_LOGATTR();
        final SCRIPT_PROPERTIES properties = new SCRIPT_PROPERTIES();
        int i = forward ? 0 : (this.allRuns.length - 1);
        offset = this.validadeOffset(offset, step);
        do {
            final StyleItem run = this.allRuns[i];
            if (run.start <= offset && offset < run.start + run.length) {
                if (run.lineBreak && !run.softBreak) {
                    return this.untranslateOffset(run.start);
                }
                if (run.tab) {
                    return this.untranslateOffset(run.start);
                }
                OS.MoveMemory(properties, this.device.scripts[run.analysis.eScript], SCRIPT_PROPERTIES.sizeof);
                final boolean isComplex = properties.fNeedsCaretInfo || properties.fNeedsWordBreaking;
                if (isComplex) {
                    this.breakRun(run);
                }
                while (run.start <= offset && offset < run.start + run.length) {
                    if (isComplex) {
                        OS.MoveMemory(logAttr, run.psla + (offset - run.start) * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                    }
                    switch (movement) {
                        case 2: {
                            if (!properties.fNeedsCaretInfo || (!logAttr.fInvalid && logAttr.fCharStop)) {
                                char ch = this.segmentsText.charAt(offset);
                                if ('\udc00' <= ch && ch <= '\udfff' && offset > 0) {
                                    ch = this.segmentsText.charAt(offset - 1);
                                    if ('\ud800' <= ch && ch <= '\udbff') {
                                        offset += step;
                                    }
                                }
                                return this.untranslateOffset(offset);
                            }
                            break;
                        }
                        case 4:
                        case 16: {
                            if (properties.fNeedsWordBreaking) {
                                if (!logAttr.fInvalid && logAttr.fWordStop) {
                                    return this.untranslateOffset(offset);
                                }
                                break;
                            }
                            else {
                                if (offset <= 0) {
                                    break;
                                }
                                final boolean letterOrDigit = Character.isLetterOrDigit(this.segmentsText.charAt(offset));
                                final boolean previousLetterOrDigit = Character.isLetterOrDigit(this.segmentsText.charAt(offset - 1));
                                if ((letterOrDigit != previousLetterOrDigit || !letterOrDigit) && !Character.isWhitespace(this.segmentsText.charAt(offset))) {
                                    return this.untranslateOffset(offset);
                                }
                                break;
                            }
                            break;
                        }
                        case 8: {
                            if (offset <= 0) {
                                break;
                            }
                            final boolean isLetterOrDigit = Character.isLetterOrDigit(this.segmentsText.charAt(offset));
                            final boolean previousLetterOrDigit = Character.isLetterOrDigit(this.segmentsText.charAt(offset - 1));
                            if (!isLetterOrDigit && previousLetterOrDigit) {
                                return this.untranslateOffset(offset);
                            }
                            break;
                        }
                    }
                    offset = this.validadeOffset(offset, step);
                }
            }
            i += step;
        } while (0 <= i && i < this.allRuns.length - 1 && 0 <= offset && offset < length);
        return forward ? this.text.length() : 0;
    }
    
    public int getOffset(final Point point, final int[] trailing) {
        this.checkLayout();
        if (point == null) {
            SWT.error(4);
        }
        return this.getOffsetInPixels(DPIUtil.autoScaleUp((Drawable)this.getDevice(), point), trailing);
    }
    
    int getOffsetInPixels(final Point point, final int[] trailing) {
        return this.getOffsetInPixels(point.x, point.y, trailing);
    }
    
    public int getOffset(final int x, final int y, final int[] trailing) {
        this.checkLayout();
        return this.getOffsetInPixels(DPIUtil.autoScaleUp((Drawable)this.getDevice(), x), DPIUtil.autoScaleUp((Drawable)this.getDevice(), y), trailing);
    }
    
    int getOffsetInPixels(int x, final int y, final int[] trailing) {
        this.computeRuns(null);
        if (trailing != null && trailing.length < 1) {
            SWT.error(5);
        }
        int lineCount;
        int line;
        for (lineCount = this.runs.length, line = 0; line < lineCount && DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.lineY[line + 1]) <= y; ++line) {}
        line = Math.min(line, this.runs.length - 1);
        final StyleItem[] lineRuns = this.runs[line];
        final int lineIndent = this.getLineIndent(line);
        if (x >= lineIndent + this.lineWidth[line]) {
            x = lineIndent + this.lineWidth[line] - 1;
        }
        if (x < lineIndent) {
            x = lineIndent;
        }
        int low = -1;
        int high = lineRuns.length;
        while (high - low > 1) {
            final int index = (high + low) / 2;
            final StyleItem run = lineRuns[index];
            if (run.x > x) {
                high = index;
            }
            else if (run.x + run.width <= x) {
                low = index;
            }
            else {
                if (run.lineBreak && !run.softBreak) {
                    return this.untranslateOffset(run.start);
                }
                int xRun = x - run.x;
                if (run.style != null && run.style.metrics != null) {
                    final GlyphMetrics metrics = run.style.metrics;
                    if (metrics.getWidthInPixels() > 0) {
                        if (trailing != null) {
                            trailing[0] = ((xRun % metrics.getWidthInPixels() >= metrics.getWidthInPixels() / 2) ? 1 : 0);
                        }
                        return this.untranslateOffset(run.start + xRun / metrics.getWidthInPixels());
                    }
                }
                if (run.tab) {
                    if (trailing != null) {
                        trailing[0] = ((x >= run.x + run.width / 2) ? 1 : 0);
                    }
                    return this.untranslateOffset(run.start);
                }
                final int cChars = run.length;
                final int cGlyphs = run.glyphCount;
                final int[] piCP = { 0 };
                final int[] piTrailing = { 0 };
                if ((this.orientation & 0x4000000) != 0x0) {
                    xRun = run.width - xRun;
                }
                final long advances = (run.justify != 0L) ? run.justify : run.advances;
                OS.ScriptXtoCP(xRun, cChars, cGlyphs, run.clusters, run.visAttrs, advances, run.analysis, piCP, piTrailing);
                int offset = run.start + piCP[0];
                final int length = this.segmentsText.length();
                char ch = (offset < length) ? this.segmentsText.charAt(offset) : '\0';
                if ('\ud800' <= ch && ch <= '\udbff' && piTrailing[0] <= 1) {
                    if (offset + 1 < length) {
                        ch = this.segmentsText.charAt(offset + 1);
                        if ('\udc00' <= ch && ch <= '\udfff' && trailing != null) {
                            trailing[0] = 0;
                        }
                    }
                }
                else if ('\udc00' <= ch && ch <= '\udfff' && piTrailing[0] <= 1) {
                    if (offset - 1 >= 0) {
                        ch = this.segmentsText.charAt(offset - 1);
                        if ('\ud800' <= ch && ch <= '\udbff') {
                            --offset;
                            if (trailing != null) {
                                trailing[0] = 2;
                            }
                        }
                    }
                }
                else if (trailing != null) {
                    trailing[0] = piTrailing[0];
                }
                return this.untranslateOffset(offset);
            }
        }
        if (trailing != null) {
            trailing[0] = 0;
        }
        if (lineRuns.length == 1) {
            final StyleItem run2 = lineRuns[0];
            if (run2.lineBreak && !run2.softBreak) {
                return this.untranslateOffset(run2.start);
            }
        }
        return this.untranslateOffset(this.lineOffset[line + 1]);
    }
    
    public int getOrientation() {
        this.checkLayout();
        return this.orientation;
    }
    
    void getPartialSelection(final StyleItem run, final int selectionStart, final int selectionEnd, final RECT rect) {
        final int end = run.start + run.length - 1;
        final int selStart = Math.max(selectionStart, run.start) - run.start;
        final int selEnd = Math.min(selectionEnd, end) - run.start;
        final int x = rect.left;
        int iX = this.ScriptCPtoX(selStart, false, run);
        int runX = ((this.orientation & 0x4000000) != 0x0) ? (run.width - iX) : iX;
        rect.left = x + runX;
        iX = this.ScriptCPtoX(selEnd, true, run);
        runX = (((this.orientation & 0x4000000) != 0x0) ? (run.width - iX) : iX);
        rect.right = x + runX;
    }
    
    public int getPreviousOffset(final int offset, final int movement) {
        this.checkLayout();
        return this._getOffset(offset, movement, false);
    }
    
    public int[] getRanges() {
        this.checkLayout();
        int[] result = new int[this.stylesCount * 2];
        int count = 0;
        for (int i = 0; i < this.stylesCount - 1; ++i) {
            if (this.styles[i].style != null) {
                result[count++] = this.styles[i].start;
                result[count++] = this.styles[i + 1].start - 1;
            }
        }
        if (count != result.length) {
            final int[] newResult = new int[count];
            System.arraycopy(result, 0, newResult, 0, count);
            result = newResult;
        }
        return result;
    }
    
    public int[] getSegments() {
        this.checkLayout();
        return this.segments;
    }
    
    public char[] getSegmentsChars() {
        this.checkLayout();
        return this.segmentsChars;
    }
    
    String getSegmentsText() {
        final int length = this.text.length();
        if (length == 0) {
            return this.text;
        }
        if (this.segments == null) {
            return this.text;
        }
        final int nSegments = this.segments.length;
        if (nSegments == 0) {
            return this.text;
        }
        if (this.segmentsChars == null) {
            if (nSegments == 1) {
                return this.text;
            }
            if (nSegments == 2 && this.segments[0] == 0 && this.segments[1] == length) {
                return this.text;
            }
        }
        final char[] oldChars = new char[length];
        this.text.getChars(0, length, oldChars, 0);
        final char[] newChars = new char[length + nSegments];
        int charCount = 0;
        int segmentCount = 0;
        final char defaultSeparator = ((this.resolveTextDirection() & 0x4000000) != 0x0) ? '\u200f' : '\u200e';
        while (charCount < length) {
            if (segmentCount < nSegments && charCount == this.segments[segmentCount]) {
                final char separator = (this.segmentsChars != null && this.segmentsChars.length > segmentCount) ? this.segmentsChars[segmentCount] : defaultSeparator;
                newChars[charCount + segmentCount++] = separator;
            }
            else {
                newChars[charCount + segmentCount] = oldChars[charCount++];
            }
        }
        while (segmentCount < nSegments) {
            this.segments[segmentCount] = charCount;
            final char separator = (this.segmentsChars != null && this.segmentsChars.length > segmentCount) ? this.segmentsChars[segmentCount] : defaultSeparator;
            newChars[charCount + segmentCount++] = separator;
        }
        return new String(newChars, 0, newChars.length);
    }
    
    public int getSpacing() {
        this.checkLayout();
        return this.lineSpacingInPoints;
    }
    
    public int getVerticalIndent() {
        this.checkLayout();
        return this.verticalIndentInPoints;
    }
    
    private int getScaledVerticalIndent() {
        if (this.verticalIndentInPoints == 0) {
            return this.verticalIndentInPoints;
        }
        return DPIUtil.autoScaleUp((Drawable)this.getDevice(), this.verticalIndentInPoints);
    }
    
    public TextStyle getStyle(final int offset) {
        this.checkLayout();
        final int length = this.text.length();
        if (0 > offset || offset >= length) {
            SWT.error(6);
        }
        for (int i = 1; i < this.stylesCount; ++i) {
            if (this.styles[i].start > offset) {
                return this.styles[i - 1].style;
            }
        }
        return null;
    }
    
    public TextStyle[] getStyles() {
        this.checkLayout();
        TextStyle[] result = new TextStyle[this.stylesCount];
        int count = 0;
        for (int i = 0; i < this.stylesCount; ++i) {
            if (this.styles[i].style != null) {
                result[count++] = this.styles[i].style;
            }
        }
        if (count != result.length) {
            final TextStyle[] newResult = new TextStyle[count];
            System.arraycopy(result, 0, newResult, 0, count);
            result = newResult;
        }
        return result;
    }
    
    public int[] getTabs() {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.getTabsInPixels());
    }
    
    int[] getTabsInPixels() {
        return this.tabs;
    }
    
    public String getText() {
        this.checkLayout();
        return this.text;
    }
    
    public int getTextDirection() {
        this.checkLayout();
        return this.resolveTextDirection();
    }
    
    public int getWidth() {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.getWidthInPixels());
    }
    
    int getWidthInPixels() {
        return this.wrapWidth;
    }
    
    public int getWrapIndent() {
        this.checkLayout();
        return DPIUtil.autoScaleDown((Drawable)this.getDevice(), this.getWrapIndentInPixels());
    }
    
    int getWrapIndentInPixels() {
        return this.wrapIndent;
    }
    
    public boolean isDisposed() {
        return this.device == null;
    }
    
    StyleItem[] itemize() {
        this.segmentsText = this.getSegmentsText();
        final int length = this.segmentsText.length();
        final SCRIPT_CONTROL scriptControl = new SCRIPT_CONTROL();
        final SCRIPT_STATE scriptState = new SCRIPT_STATE();
        final int MAX_ITEM = length + 1;
        if ((this.resolveTextDirection() & 0x4000000) != 0x0) {
            scriptState.uBidiLevel = 1;
            scriptState.fArabicNumContext = true;
        }
        OS.ScriptApplyDigitSubstitution(0L, scriptControl, scriptState);
        final long hHeap = OS.GetProcessHeap();
        final long pItems = OS.HeapAlloc(hHeap, 8, (1 + MAX_ITEM) * SCRIPT_ITEM.sizeof);
        if (pItems == 0L) {
            SWT.error(2);
        }
        final int[] pcItems = { 0 };
        final char[] chars = new char[length];
        this.segmentsText.getChars(0, length, chars, 0);
        scriptControl.fMergeNeutralItems = true;
        if (BidiUtil.resolveTextDirection(this.text) != 67108864) {
            int i = 0;
            int latestNeutralIndex = -2;
            int latestUnicodeIndex = -2;
            while (i < length) {
                final char c = chars[i];
                Label_0227: {
                    if (c >= ' ' && c <= '~' && !Character.isAlphabetic(c)) {
                        latestNeutralIndex = i;
                    }
                    else {
                        if (c <= '\u00ff') {
                            break Label_0227;
                        }
                        latestUnicodeIndex = i;
                    }
                    if (Math.abs(latestNeutralIndex - latestUnicodeIndex) == 1) {
                        chars[latestNeutralIndex] = 'A';
                    }
                }
                ++i;
            }
        }
        OS.ScriptItemize(chars, length, MAX_ITEM, scriptControl, scriptState, pItems, pcItems);
        final StyleItem[] runs = this.merge(pItems, pcItems[0]);
        OS.HeapFree(hHeap, 0, pItems);
        return runs;
    }
    
    StyleItem[] merge(final long items, final int itemCount) {
        if (this.styles.length > this.stylesCount) {
            final StyleItem[] newStyles = new StyleItem[this.stylesCount];
            System.arraycopy(this.styles, 0, newStyles, 0, this.stylesCount);
            this.styles = newStyles;
        }
        final int end = this.segmentsText.length();
        int start = 0;
        int itemIndex = 0;
        int styleIndex = 0;
        final List<StyleItem> runs = new ArrayList<StyleItem>(itemCount + this.stylesCount + (end + 32000 - 1) / 32000);
        final SCRIPT_ITEM scriptItem = new SCRIPT_ITEM();
        int itemLimit = -1;
        int nextItemIndex = 0;
        boolean linkBefore = false;
        final boolean merge = itemCount > 1024;
        final SCRIPT_PROPERTIES sp = new SCRIPT_PROPERTIES();
        while (start < end) {
            final StyleItem item = new StyleItem();
            item.start = start;
            item.style = this.styles[styleIndex].style;
            runs.add(item);
            OS.MoveMemory(scriptItem, items + itemIndex * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
            item.analysis = scriptItem.a;
            scriptItem.a = new SCRIPT_ANALYSIS();
            if (linkBefore) {
                item.analysis.fLinkBefore = true;
                linkBefore = false;
            }
            final char ch = this.segmentsText.charAt(start);
            switch (ch) {
                case '\n':
                case '\r': {
                    item.lineBreak = true;
                    break;
                }
                case '\t': {
                    item.tab = true;
                    break;
                }
            }
            if (itemLimit == -1) {
                nextItemIndex = itemIndex + 1;
                OS.MoveMemory(scriptItem, items + nextItemIndex * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
                itemLimit = scriptItem.iCharPos;
                if (nextItemIndex < itemCount && ch == '\r' && this.segmentsText.charAt(itemLimit) == '\n') {
                    nextItemIndex = itemIndex + 2;
                    OS.MoveMemory(scriptItem, items + nextItemIndex * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
                    itemLimit = scriptItem.iCharPos;
                }
                if (nextItemIndex < itemCount && merge && !item.lineBreak) {
                    OS.MoveMemory(sp, this.device.scripts[item.analysis.eScript], SCRIPT_PROPERTIES.sizeof);
                    if (!sp.fComplex || item.tab) {
                        for (int i = 0; i < 512; ++i) {
                            if (nextItemIndex == itemCount) {
                                break;
                            }
                            final char c = this.segmentsText.charAt(itemLimit);
                            if (c == '\n') {
                                break;
                            }
                            if (c == '\r') {
                                break;
                            }
                            if (c == '\t' != item.tab) {
                                break;
                            }
                            OS.MoveMemory(sp, this.device.scripts[scriptItem.a.eScript], SCRIPT_PROPERTIES.sizeof);
                            if (!item.tab && sp.fComplex) {
                                break;
                            }
                            ++nextItemIndex;
                            OS.MoveMemory(scriptItem, items + nextItemIndex * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
                            itemLimit = scriptItem.iCharPos;
                        }
                    }
                }
            }
            boolean mayNeedSplit = true;
            final int styleLimit = this.translateOffset(this.styles[styleIndex + 1].start);
            if (styleLimit <= itemLimit) {
                final int runLen = styleLimit - start;
                if (runLen < 32000) {
                    mayNeedSplit = false;
                    ++styleIndex;
                    start = styleLimit;
                    if (start < itemLimit && 0 < start && start < end) {
                        final char pChar = this.segmentsText.charAt(start - 1);
                        final char tChar = this.segmentsText.charAt(start);
                        if (Character.isLetter(pChar) && Character.isLetter(tChar)) {
                            item.analysis.fLinkAfter = true;
                            linkBefore = true;
                        }
                    }
                }
            }
            final int runLen = itemLimit - start;
            if (mayNeedSplit && runLen > 32000) {
                start += this.splitLongRun(item);
            }
            else if (itemLimit <= styleLimit) {
                itemIndex = nextItemIndex;
                start = itemLimit;
                itemLimit = -1;
            }
            item.length = start - item.start;
        }
        final StyleItem item = new StyleItem();
        item.start = end;
        OS.MoveMemory(scriptItem, items + itemCount * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
        item.analysis = scriptItem.a;
        runs.add(item);
        return (StyleItem[])runs.toArray();
    }
    
    int splitLongRun(final StyleItem run) {
        run.length = 32000;
        this.breakRun(run);
        final SCRIPT_LOGATTR logAttr = new SCRIPT_LOGATTR();
        int best = 32000;
        for (int i = 31999; i >= 31000; --i) {
            final int memoryIndex = i * SCRIPT_LOGATTR.sizeof;
            if (memoryIndex + SCRIPT_LOGATTR.sizeof > run.pslaAllocSize) {
                throw new IndexOutOfBoundsException();
            }
            OS.MoveMemory(logAttr, run.psla + memoryIndex, SCRIPT_LOGATTR.sizeof);
            if (logAttr.fSoftBreak || logAttr.fWhiteSpace || logAttr.fWordStop) {
                best = i;
                break;
            }
        }
        if (Character.isHighSurrogate(this.segmentsText.charAt(run.start + best - 1))) {
            --best;
        }
        return best;
    }
    
    int resolveTextDirection() {
        return (this.textDirection == 100663296) ? BidiUtil.resolveTextDirection(this.text) : this.textDirection;
    }
    
    StyleItem[] reorder(final StyleItem[] runs, final boolean terminate) {
        int length = runs.length;
        if (length <= 1) {
            return runs;
        }
        final byte[] bidiLevels = new byte[length];
        for (int i = 0; i < length; ++i) {
            bidiLevels[i] = (byte)(runs[i].analysis.s.uBidiLevel & 0x1F);
        }
        final StyleItem lastRun = runs[length - 1];
        if (lastRun.lineBreak && !lastRun.softBreak) {
            bidiLevels[length - 1] = 0;
        }
        final int[] log2vis = new int[length];
        OS.ScriptLayout(length, bidiLevels, null, log2vis);
        final StyleItem[] result = new StyleItem[length];
        for (int j = 0; j < length; ++j) {
            result[log2vis[j]] = runs[j];
        }
        if ((this.orientation & 0x4000000) != 0x0) {
            if (terminate) {
                --length;
            }
            for (int j = 0; j < length / 2; ++j) {
                final StyleItem tmp = result[j];
                result[j] = result[length - j - 1];
                result[length - j - 1] = tmp;
            }
        }
        return result;
    }
    
    public void setAlignment(int alignment) {
        this.checkLayout();
        final int mask = 16924672;
        alignment &= 0x1024000;
        if (alignment == 0) {
            return;
        }
        if ((alignment & 0x4000) != 0x0) {
            alignment = 16384;
        }
        if ((alignment & 0x20000) != 0x0) {
            alignment = 131072;
        }
        if (this.alignment == alignment) {
            return;
        }
        this.freeRuns();
        this.alignment = alignment;
    }
    
    public void setAscent(int ascent) {
        this.checkLayout();
        if (ascent < -1) {
            SWT.error(5);
        }
        ascent = DPIUtil.autoScaleUp((Drawable)this.getDevice(), ascent);
        if (this.ascentInPixels == ascent) {
            return;
        }
        this.freeRuns();
        this.ascentInPixels = ascent;
    }
    
    public void setDescent(int descent) {
        this.checkLayout();
        if (descent < -1) {
            SWT.error(5);
        }
        descent = DPIUtil.autoScaleUp((Drawable)this.getDevice(), descent);
        if (this.descentInPixels == descent) {
            return;
        }
        this.freeRuns();
        this.descentInPixels = descent;
    }
    
    public void setFont(final Font font) {
        this.checkLayout();
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        final Font oldFont = this.font;
        if (oldFont == font) {
            return;
        }
        this.font = font;
        if (oldFont != null && oldFont.equals((Object)font)) {
            return;
        }
        this.freeRuns();
    }
    
    public void setIndent(final int indent) {
        this.checkLayout();
        this.setIndentInPixels(DPIUtil.autoScaleUp((Drawable)this.getDevice(), indent));
    }
    
    void setIndentInPixels(final int indent) {
        if (indent < 0) {
            return;
        }
        if (this.indent == indent) {
            return;
        }
        this.freeRuns();
        this.indent = indent;
    }
    
    public void setJustify(final boolean justify) {
        this.checkLayout();
        if (this.justify == justify) {
            return;
        }
        this.freeRuns();
        this.justify = justify;
    }
    
    public void setOrientation(int orientation) {
        this.checkLayout();
        final int mask = 100663296;
        orientation &= 0x6000000;
        if (orientation == 0) {
            return;
        }
        if ((orientation & 0x2000000) != 0x0) {
            orientation = 33554432;
        }
        if (this.orientation == orientation) {
            return;
        }
        final int n = orientation;
        this.orientation = n;
        this.textDirection = n;
        this.freeRuns();
    }
    
    public void setSegments(final int[] segments) {
        this.checkLayout();
        if (this.segments == null && segments == null) {
            return;
        }
        if (this.segments != null && segments != null && this.segments.length == segments.length) {
            int i;
            for (i = 0; i < segments.length && this.segments[i] == segments[i]; ++i) {}
            if (i == segments.length) {
                return;
            }
        }
        this.freeRuns();
        this.segments = segments;
    }
    
    public void setSegmentsChars(final char[] segmentsChars) {
        this.checkLayout();
        if (this.segmentsChars == null && segmentsChars == null) {
            return;
        }
        if (this.segmentsChars != null && segmentsChars != null && this.segmentsChars.length == segmentsChars.length) {
            int i;
            for (i = 0; i < segmentsChars.length && this.segmentsChars[i] == segmentsChars[i]; ++i) {}
            if (i == segmentsChars.length) {
                return;
            }
        }
        this.freeRuns();
        this.segmentsChars = segmentsChars;
    }
    
    public void setSpacing(final int spacing) {
        this.checkLayout();
        if (spacing < 0) {
            SWT.error(5);
        }
        if (this.lineSpacingInPoints == spacing) {
            return;
        }
        this.freeRuns();
        this.lineSpacingInPoints = spacing;
    }
    
    public void setVerticalIndent(final int verticalIndent) {
        this.checkLayout();
        if (verticalIndent < 0) {
            SWT.error(5);
        }
        if (this.verticalIndentInPoints == verticalIndent) {
            return;
        }
        this.verticalIndentInPoints = verticalIndent;
    }
    
    public void setStyle(final TextStyle style, int start, int end) {
        this.checkLayout();
        final int length = this.text.length();
        if (length == 0) {
            return;
        }
        if (start > end) {
            return;
        }
        start = Math.min(Math.max(0, start), length - 1);
        end = Math.min(Math.max(0, end), length - 1);
        int low = -1;
        int high = this.stylesCount;
        while (high - low > 1) {
            final int index = (high + low) / 2;
            if (this.styles[index + 1].start > start) {
                high = index;
            }
            else {
                low = index;
            }
        }
        if (0 <= high && high < this.stylesCount) {
            final StyleItem item = this.styles[high];
            if (item.start == start && this.styles[high + 1].start - 1 == end) {
                if (style == null) {
                    if (item.style == null) {
                        return;
                    }
                }
                else if (style.equals(item.style)) {
                    return;
                }
            }
        }
        this.freeRuns();
        int modifyStart;
        int modifyEnd;
        for (modifyEnd = (modifyStart = high); modifyEnd < this.stylesCount && this.styles[modifyEnd + 1].start <= end; ++modifyEnd) {}
        if (modifyStart == modifyEnd) {
            final int styleStart = this.styles[modifyStart].start;
            final int styleEnd = this.styles[modifyEnd + 1].start - 1;
            if (styleStart == start && styleEnd == end) {
                this.styles[modifyStart].style = style;
                return;
            }
            if (styleStart != start && styleEnd != end) {
                final int newLength = this.stylesCount + 2;
                if (newLength > this.styles.length) {
                    final int newSize = Math.min(newLength + 1024, Math.max(64, newLength * 2));
                    final StyleItem[] newStyles = new StyleItem[newSize];
                    System.arraycopy(this.styles, 0, newStyles, 0, this.stylesCount);
                    this.styles = newStyles;
                }
                System.arraycopy(this.styles, modifyEnd + 1, this.styles, modifyEnd + 3, this.stylesCount - modifyEnd - 1);
                StyleItem item2 = new StyleItem();
                item2.start = start;
                item2.style = style;
                this.styles[modifyStart + 1] = item2;
                item2 = new StyleItem();
                item2.start = end + 1;
                item2.style = this.styles[modifyStart].style;
                this.styles[modifyStart + 2] = item2;
                this.stylesCount = newLength;
                return;
            }
        }
        if (start == this.styles[modifyStart].start) {
            --modifyStart;
        }
        if (end == this.styles[modifyEnd + 1].start - 1) {
            ++modifyEnd;
        }
        final int newLength2 = this.stylesCount + 1 - (modifyEnd - modifyStart - 1);
        if (newLength2 > this.styles.length) {
            final int newSize2 = Math.min(newLength2 + 1024, Math.max(64, newLength2 * 2));
            final StyleItem[] newStyles2 = new StyleItem[newSize2];
            System.arraycopy(this.styles, 0, newStyles2, 0, this.stylesCount);
            this.styles = newStyles2;
        }
        System.arraycopy(this.styles, modifyEnd, this.styles, modifyStart + 2, this.stylesCount - modifyEnd);
        final StyleItem item3 = new StyleItem();
        item3.start = start;
        item3.style = style;
        this.styles[modifyStart + 1] = item3;
        this.styles[modifyStart + 2].start = end + 1;
        this.stylesCount = newLength2;
    }
    
    public void setTabs(final int[] tabs) {
        this.checkLayout();
        if (this.tabs == null && tabs == null) {
            return;
        }
        this.setTabsInPixels(DPIUtil.autoScaleUp((Drawable)this.getDevice(), tabs));
    }
    
    void setTabsInPixels(final int[] tabs) {
        if (Arrays.equals(this.tabs, tabs)) {
            return;
        }
        this.freeRuns();
        this.tabs = tabs;
    }
    
    public void setText(final String text) {
        this.checkLayout();
        if (text == null) {
            SWT.error(4);
        }
        if (text.equals(this.text)) {
            return;
        }
        this.freeRuns();
        this.text = text;
        (this.styles = new StyleItem[2])[0] = new StyleItem();
        this.styles[1] = new StyleItem();
        this.styles[1].start = text.length();
        this.stylesCount = 2;
    }
    
    public void setTextDirection(int textDirection) {
        this.checkLayout();
        final int mask = 100663296;
        textDirection &= 0x6000000;
        if (textDirection == 0) {
            return;
        }
        if (textDirection != 100663296) {
            if ((textDirection & 0x2000000) != 0x0) {
                textDirection = 33554432;
            }
            if (this.textDirection == textDirection) {
                return;
            }
        }
        this.textDirection = textDirection;
        this.freeRuns();
    }
    
    public void setWidth(final int width) {
        this.checkLayout();
        this.setWidthInPixels((width != -1) ? DPIUtil.autoScaleUp((Drawable)this.getDevice(), width) : width);
    }
    
    void setWidthInPixels(final int width) {
        if (width < -1 || width == 0) {
            SWT.error(5);
        }
        if (this.wrapWidth == width) {
            return;
        }
        this.freeRuns();
        this.wrapWidth = width;
    }
    
    public void setWrapIndent(final int wrapIndent) {
        this.checkLayout();
        this.setWrapIndentInPixels(DPIUtil.autoScaleUp((Drawable)this.getDevice(), wrapIndent));
    }
    
    void setWrapIndentInPixels(final int wrapIndent) {
        if (wrapIndent < 0) {
            return;
        }
        if (this.wrapIndent == wrapIndent) {
            return;
        }
        this.freeRuns();
        this.wrapIndent = wrapIndent;
    }
    
    boolean shape(final long hdc, final StyleItem run, final char[] chars, final int[] glyphCount, final int maxGlyphs, final SCRIPT_PROPERTIES sp) {
        final boolean useCMAPcheck = !sp.fComplex && !run.analysis.fNoGlyphIndex;
        if (useCMAPcheck) {
            final short[] glyphs = new short[chars.length];
            if (OS.ScriptGetCMap(hdc, run.psc, chars, chars.length, 0, glyphs) != 0) {
                if (run.psc != 0L) {
                    OS.ScriptFreeCache(run.psc);
                    glyphCount[0] = 0;
                    OS.MoveMemory(run.psc, new long[1], C.PTR_SIZEOF);
                }
                return false;
            }
        }
        final int scriptShaprHr = OS.ScriptShape(hdc, run.psc, chars, chars.length, maxGlyphs, run.analysis, run.glyphs, run.clusters, run.visAttrs, glyphCount);
        if (scriptShaprHr == 0) {
            run.glyphCount = glyphCount[0];
            if (useCMAPcheck) {
                return true;
            }
            if (run.analysis.fNoGlyphIndex) {
                return true;
            }
            final SCRIPT_FONTPROPERTIES fp = new SCRIPT_FONTPROPERTIES();
            fp.cBytes = SCRIPT_FONTPROPERTIES.sizeof;
            OS.ScriptGetFontProperties(hdc, run.psc, fp);
            final short[] glyphs2 = new short[glyphCount[0]];
            OS.MoveMemory(glyphs2, run.glyphs, glyphs2.length * 2);
            int i;
            for (i = 0; i < glyphs2.length && glyphs2[i] != fp.wgDefault; ++i) {}
            if (i == glyphs2.length) {
                return true;
            }
        }
        if (run.psc != 0L) {
            OS.ScriptFreeCache(run.psc);
            glyphCount[0] = 0;
            OS.MoveMemory(run.psc, new long[1], C.PTR_SIZEOF);
        }
        run.glyphCount = 0;
        return false;
    }
    
    long createMetafileWithChars(final long hdc, final long hFont, final char[] chars, final int charCount) {
        final long hHeap = OS.GetProcessHeap();
        final int nativeStringSize = charCount * 2;
        final long nativeString = OS.HeapAlloc(hHeap, 8, nativeStringSize);
        OS.MoveMemory(nativeString, chars, nativeStringSize);
        final long ssa = OS.HeapAlloc(hHeap, 8, OS.SCRIPT_STRING_ANALYSIS_sizeof());
        final long metaFileDc = OS.CreateEnhMetaFile(hdc, null, null, null);
        final long oldMetaFont = OS.SelectObject(metaFileDc, hFont);
        final int flags = 6304;
        if (OS.ScriptStringAnalyse(metaFileDc, nativeString, charCount, 0, -1, 6304, 0, null, null, 0L, 0L, 0L, ssa) == 0) {
            OS.ScriptStringOut(ssa, 0, 0, 0, null, 0, 0, false);
            OS.ScriptStringFree(ssa);
        }
        OS.HeapFree(hHeap, 0, nativeString);
        OS.HeapFree(hHeap, 0, ssa);
        OS.SelectObject(metaFileDc, oldMetaFont);
        return OS.CloseEnhMetaFile(metaFileDc);
    }
    
    void shape(final long hdc, final StyleItem run) {
        if (run.lineBreak) {
            return;
        }
        if (run.glyphs != 0L) {
            return;
        }
        final int[] buffer = { 0 };
        final char[] chars = new char[run.length];
        this.segmentsText.getChars(run.start, run.start + run.length, chars, 0);
        final int maxGlyphs = chars.length * 3 / 2 + 16;
        final long hHeap = OS.GetProcessHeap();
        run.glyphs = OS.HeapAlloc(hHeap, 8, maxGlyphs * 2);
        if (run.glyphs == 0L) {
            SWT.error(2);
        }
        run.clusters = OS.HeapAlloc(hHeap, 8, maxGlyphs * 2);
        if (run.clusters == 0L) {
            SWT.error(2);
        }
        run.visAttrs = OS.HeapAlloc(hHeap, 8, maxGlyphs * 2);
        if (run.visAttrs == 0L) {
            SWT.error(2);
        }
        run.psc = OS.HeapAlloc(hHeap, 8, C.PTR_SIZEOF);
        if (run.psc == 0L) {
            SWT.error(2);
        }
        final short script = run.analysis.eScript;
        final SCRIPT_PROPERTIES sp = new SCRIPT_PROPERTIES();
        OS.MoveMemory(sp, this.device.scripts[script], SCRIPT_PROPERTIES.sizeof);
        boolean shapeSucceed = this.shape(hdc, run, chars, buffer, maxGlyphs, sp);
        if (!shapeSucceed && sp.fPrivateUseArea) {
            run.analysis.fNoGlyphIndex = true;
            shapeSucceed = this.shape(hdc, run, chars, buffer, maxGlyphs, sp);
        }
        if (!shapeSucceed) {
            final long hFont = OS.GetCurrentObject(hdc, 6);
            long newFont = 0L;
            final char[] sampleChars = new char[Math.min(chars.length, 2)];
            final SCRIPT_LOGATTR logAttr = new SCRIPT_LOGATTR();
            this.breakRun(run);
            int count = 0;
            for (int i = 0; i < chars.length; ++i) {
                OS.MoveMemory(logAttr, run.psla + i * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                if (!logAttr.fWhiteSpace) {
                    sampleChars[count++] = chars[i];
                    if (count == sampleChars.length) {
                        break;
                    }
                }
            }
            if (count > 0) {
                final long metaFile = this.createMetafileWithChars(hdc, hFont, sampleChars, count);
                final EMREXTCREATEFONTINDIRECTW emr = new EMREXTCREATEFONTINDIRECTW();
                class 1MetaFileEnumProc
                {
                    long metaFileEnumProc(final long hDC, final long table, final long record, final long nObj, final long lpData) {
                        OS.MoveMemory(emr.emr, record, EMR.sizeof);
                        switch (emr.emr.iType) {
                            case 82: {
                                OS.MoveMemory(emr, record, EMREXTCREATEFONTINDIRECTW.sizeof);
                                break;
                            }
                            case 84: {
                                return 0L;
                            }
                        }
                        return 1L;
                    }
                }
                final 1MetaFileEnumProc object = new 1MetaFileEnumProc();
                final boolean compilerWarningWorkaround = false;
                final Callback callback = new Callback(object, "metaFileEnumProc", 5);
                OS.EnumEnhMetaFile(0L, metaFile, callback.getAddress(), 0L, null);
                OS.DeleteEnhMetaFile(metaFile);
                callback.dispose();
                newFont = OS.CreateFontIndirect(emr.elfw.elfLogFont);
            }
            else {
                int index = 0;
                while (index < this.allRuns.length - 1) {
                    if (this.allRuns[index] == run) {
                        if (index > 0) {
                            final StyleItem pRun = this.allRuns[index - 1];
                            if (pRun.analysis.eScript == run.analysis.eScript) {
                                final long pFont = this.getItemFont(pRun);
                                final LOGFONT logFont = new LOGFONT();
                                OS.GetObject(pFont, LOGFONT.sizeof, logFont);
                                newFont = OS.CreateFontIndirect(logFont);
                            }
                        }
                        if (newFont != 0L || index + 1 >= this.allRuns.length - 1) {
                            break;
                        }
                        final StyleItem nRun = this.allRuns[index + 1];
                        if (nRun.analysis.eScript == run.analysis.eScript) {
                            OS.SelectObject(hdc, this.getItemFont(nRun));
                            this.shape(hdc, nRun);
                            final long nFont = this.getItemFont(nRun);
                            final LOGFONT logFont = new LOGFONT();
                            OS.GetObject(nFont, LOGFONT.sizeof, logFont);
                            newFont = OS.CreateFontIndirect(logFont);
                            break;
                        }
                        break;
                    }
                    else {
                        ++index;
                    }
                }
            }
            if (newFont != 0L) {
                OS.SelectObject(hdc, newFont);
                if (shapeSucceed = this.shape(hdc, run, chars, buffer, maxGlyphs, sp)) {
                    run.fallbackFont = newFont;
                }
            }
            if (!shapeSucceed && !sp.fComplex) {
                run.analysis.fNoGlyphIndex = true;
                if (shapeSucceed = this.shape(hdc, run, chars, buffer, maxGlyphs, sp)) {
                    run.fallbackFont = newFont;
                }
                else {
                    run.analysis.fNoGlyphIndex = false;
                }
            }
            if (!shapeSucceed && this.mLangFontLink2 != null) {
                final long[] hNewFont = { 0L };
                final int[] dwCodePages = { 0 };
                final int[] cchCodePages = { 0 };
                this.mLangFontLink2.GetStrCodePages(chars, chars.length, 0, dwCodePages, cchCodePages);
                if (this.mLangFontLink2.MapFont(hdc, dwCodePages[0], chars[0], hNewFont) == 0) {
                    final LOGFONT logFont2 = new LOGFONT();
                    OS.GetObject(hNewFont[0], LOGFONT.sizeof, logFont2);
                    this.mLangFontLink2.ReleaseFont(hNewFont[0]);
                    final long mLangFont = OS.CreateFontIndirect(logFont2);
                    final long oldFont = OS.SelectObject(hdc, mLangFont);
                    if (shapeSucceed = this.shape(hdc, run, chars, buffer, maxGlyphs, sp)) {
                        run.fallbackFont = mLangFont;
                    }
                    else {
                        OS.SelectObject(hdc, oldFont);
                        OS.DeleteObject(mLangFont);
                    }
                }
            }
            if (!shapeSucceed) {
                OS.SelectObject(hdc, hFont);
            }
            if (newFont != 0L && newFont != run.fallbackFont) {
                OS.DeleteObject(newFont);
            }
        }
        if (!shapeSucceed) {
            OS.ScriptShape(hdc, run.psc, chars, chars.length, maxGlyphs, run.analysis, run.glyphs, run.clusters, run.visAttrs, buffer);
            run.glyphCount = buffer[0];
        }
        final int[] abc = new int[3];
        run.advances = OS.HeapAlloc(hHeap, 8, run.glyphCount * 4);
        if (run.advances == 0L) {
            SWT.error(2);
        }
        run.goffsets = OS.HeapAlloc(hHeap, 8, run.glyphCount * 8);
        if (run.goffsets == 0L) {
            SWT.error(2);
        }
        OS.ScriptPlace(hdc, run.psc, run.glyphs, run.glyphCount, run.visAttrs, run.analysis, run.advances, run.goffsets, abc);
        run.width = abc[0] + abc[1] + abc[2];
        final TextStyle style = run.style;
        if (style != null) {
            OUTLINETEXTMETRIC lotm = null;
            if (style.underline || style.strikeout) {
                lotm = new OUTLINETEXTMETRIC();
                if (OS.GetOutlineTextMetrics(hdc, OUTLINETEXTMETRIC.sizeof, lotm) == 0) {
                    lotm = null;
                }
            }
            if (style.metrics != null) {
                final GlyphMetrics metrics = style.metrics;
                run.width = metrics.getWidthInPixels() * Math.max(1, run.glyphCount);
                run.ascentInPoints = metrics.ascent;
                run.descentInPoints = metrics.descent;
                run.leadingInPoints = 0;
            }
            else {
                TEXTMETRIC lptm = null;
                if (lotm != null) {
                    lptm = lotm.otmTextMetrics;
                }
                else {
                    lptm = new TEXTMETRIC();
                    OS.GetTextMetrics(hdc, lptm);
                }
                run.ascentInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm.tmAscent);
                run.descentInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm.tmDescent);
                run.leadingInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm.tmInternalLeading);
            }
            if (lotm != null) {
                run.underlinePos = lotm.otmsUnderscorePosition;
                run.underlineThickness = Math.max(1, lotm.otmsUnderscoreSize);
                run.strikeoutPos = lotm.otmsStrikeoutPosition;
                run.strikeoutThickness = Math.max(1, lotm.otmsStrikeoutSize);
            }
            else {
                run.underlinePos = 1;
                run.underlineThickness = 1;
                run.strikeoutPos = DPIUtil.autoScaleUp((Drawable)this.getDevice(), run.ascentInPoints) / 2;
                run.strikeoutThickness = 1;
            }
            run.ascentInPoints += style.rise;
            run.descentInPoints -= style.rise;
        }
        else {
            final TEXTMETRIC lptm2 = new TEXTMETRIC();
            OS.GetTextMetrics(hdc, lptm2);
            run.ascentInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm2.tmAscent);
            run.descentInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm2.tmDescent);
            run.leadingInPoints = DPIUtil.autoScaleDown((Drawable)this.getDevice(), lptm2.tmInternalLeading);
        }
    }
    
    int validadeOffset(int offset, final int step) {
        offset = this.untranslateOffset(offset);
        return this.translateOffset(offset + step);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "TextLayout {*DISPOSED*}";
        }
        return "TextLayout {}";
    }
    
    int translateOffset(int offset) {
        final int length = this.text.length();
        if (length == 0) {
            return offset;
        }
        if (this.segments == null) {
            return offset;
        }
        final int nSegments = this.segments.length;
        if (nSegments == 0) {
            return offset;
        }
        if (this.segmentsChars == null) {
            if (nSegments == 1) {
                return offset;
            }
            if (nSegments == 2 && this.segments[0] == 0 && this.segments[1] == length) {
                return offset;
            }
        }
        for (int i = 0; i < nSegments && offset - i >= this.segments[i]; ++offset, ++i) {}
        return offset;
    }
    
    int untranslateOffset(int offset) {
        final int length = this.text.length();
        if (length == 0) {
            return offset;
        }
        if (this.segments == null) {
            return offset;
        }
        final int nSegments = this.segments.length;
        if (nSegments == 0) {
            return offset;
        }
        if (this.segmentsChars == null) {
            if (nSegments == 1) {
                return offset;
            }
            if (nSegments == 2 && this.segments[0] == 0 && this.segments[1] == length) {
                return offset;
            }
        }
        for (int i = 0; i < nSegments && offset > this.segments[i]; --offset, ++i) {}
        return offset;
    }
    
    public void setDefaultTabWidth(final int tabLength) {
    }
    
    class StyleItem
    {
        TextStyle style;
        int start;
        int length;
        boolean lineBreak;
        boolean softBreak;
        boolean tab;
        SCRIPT_ANALYSIS analysis;
        long psc;
        long glyphs;
        int glyphCount;
        long clusters;
        long visAttrs;
        long advances;
        long goffsets;
        int width;
        int ascentInPoints;
        int descentInPoints;
        int leadingInPoints;
        int x;
        int underlinePos;
        int underlineThickness;
        int strikeoutPos;
        int strikeoutThickness;
        long justify;
        int pslaAllocSize;
        long psla;
        long fallbackFont;
        
        StyleItem() {
            this.psc = 0L;
        }
        
        void free() {
            final long hHeap = OS.GetProcessHeap();
            if (this.psc != 0L) {
                OS.ScriptFreeCache(this.psc);
                OS.HeapFree(hHeap, 0, this.psc);
                this.psc = 0L;
            }
            if (this.glyphs != 0L) {
                OS.HeapFree(hHeap, 0, this.glyphs);
                this.glyphs = 0L;
                this.glyphCount = 0;
            }
            if (this.clusters != 0L) {
                OS.HeapFree(hHeap, 0, this.clusters);
                this.clusters = 0L;
            }
            if (this.visAttrs != 0L) {
                OS.HeapFree(hHeap, 0, this.visAttrs);
                this.visAttrs = 0L;
            }
            if (this.advances != 0L) {
                OS.HeapFree(hHeap, 0, this.advances);
                this.advances = 0L;
            }
            if (this.goffsets != 0L) {
                OS.HeapFree(hHeap, 0, this.goffsets);
                this.goffsets = 0L;
            }
            if (this.justify != 0L) {
                OS.HeapFree(hHeap, 0, this.justify);
                this.justify = 0L;
            }
            if (this.psla != 0L) {
                OS.HeapFree(hHeap, 0, this.psla);
                this.psla = 0L;
            }
            if (this.fallbackFont != 0L) {
                OS.DeleteObject(this.fallbackFont);
                this.fallbackFont = 0L;
            }
            final int n = 0;
            this.x = 0;
            this.descentInPoints = 0;
            this.ascentInPoints = 0;
            this.width = 0;
            final boolean b = false;
            this.softBreak = false;
            this.lineBreak = false;
        }
        
        @Override
        public String toString() {
            return "StyleItem {" + this.start + ", " + this.style;
        }
    }
}
