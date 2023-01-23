//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.*;

public final class GCData
{
    public Device device;
    public int style;
    public int state;
    public int foreground;
    public int background;
    public Font font;
    public Pattern foregroundPattern;
    public long gdipFgPatternBrushAlpha;
    public Pattern backgroundPattern;
    public long gdipBgPatternBrushAlpha;
    public int lineStyle;
    public float lineWidth;
    public int lineCap;
    public int lineJoin;
    public float lineDashesOffset;
    public float[] lineDashes;
    public float lineMiterLimit;
    public int alpha;
    public Image image;
    public PAINTSTRUCT ps;
    public int layout;
    public long hPen;
    public long hOldPen;
    public long hBrush;
    public long hOldBrush;
    public long hNullBitmap;
    public long hwnd;
    public long gdipGraphics;
    public long gdipPen;
    public long gdipBrush;
    public long gdipFgBrush;
    public long gdipBgBrush;
    public long gdipFont;
    public long hGDIFont;
    public float gdipXOffset;
    public float gdipYOffset;
    public int uiState;
    public boolean focusDrawn;
    
    public GCData() {
        this.state = -1;
        this.foreground = -1;
        this.background = -1;
        this.lineStyle = 1;
        this.lineCap = 1;
        this.lineJoin = 1;
        this.lineMiterLimit = 10.0f;
        this.alpha = 255;
        this.layout = -1;
        this.uiState = 0;
    }
}
