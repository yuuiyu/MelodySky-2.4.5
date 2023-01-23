//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.gdip;

import org.eclipse.swt.internal.*;

public class Gdip extends Platform
{
    public static final float FlatnessDefault = 0.25f;
    public static final int BrushTypeSolidColor = 0;
    public static final int BrushTypeHatchFill = 1;
    public static final int BrushTypeTextureFill = 2;
    public static final int BrushTypePathGradient = 3;
    public static final int BrushTypeLinearGradient = 4;
    public static final int ColorAdjustTypeBitmap = 1;
    public static final int ColorMatrixFlagsDefault = 0;
    public static final int CombineModeReplace = 0;
    public static final int CombineModeIntersect = 1;
    public static final int CombineModeUnion = 2;
    public static final int CombineModeXor = 3;
    public static final int CombineModeExclude = 4;
    public static final int CombineModeComplement = 5;
    public static final int FillModeAlternate = 0;
    public static final int FillModeWinding = 1;
    public static final int DashCapFlat = 0;
    public static final int DashCapRound = 2;
    public static final int DashCapTriangle = 3;
    public static final int DashStyleSolid = 0;
    public static final int DashStyleDash = 1;
    public static final int DashStyleDot = 2;
    public static final int DashStyleDashDot = 3;
    public static final int DashStyleDashDotDot = 4;
    public static final int DashStyleCustom = 5;
    public static final int DriverStringOptionsRealizedAdvance = 4;
    public static final int FontStyleRegular = 0;
    public static final int FontStyleBold = 1;
    public static final int FontStyleItalic = 2;
    public static final int FontStyleBoldItalic = 3;
    public static final int FontStyleUnderline = 4;
    public static final int FontStyleStrikeout = 8;
    public static final int PaletteFlagsHasAlpha = 1;
    public static final int FlushIntentionFlush = 0;
    public static final int FlushIntentionSync = 1;
    public static final int HotkeyPrefixNone = 0;
    public static final int HotkeyPrefixShow = 1;
    public static final int HotkeyPrefixHide = 2;
    public static final int LineJoinMiter = 0;
    public static final int LineJoinBevel = 1;
    public static final int LineJoinRound = 2;
    public static final int LineCapFlat = 0;
    public static final int LineCapSquare = 1;
    public static final int LineCapRound = 2;
    public static final int MatrixOrderPrepend = 0;
    public static final int MatrixOrderAppend = 1;
    public static final int QualityModeDefault = 0;
    public static final int QualityModeLow = 1;
    public static final int QualityModeHigh = 2;
    public static final int InterpolationModeDefault = 0;
    public static final int InterpolationModeLowQuality = 1;
    public static final int InterpolationModeHighQuality = 2;
    public static final int InterpolationModeBilinear = 3;
    public static final int InterpolationModeBicubic = 4;
    public static final int InterpolationModeNearestNeighbor = 5;
    public static final int InterpolationModeHighQualityBilinear = 6;
    public static final int InterpolationModeHighQualityBicubic = 7;
    public static final int PathPointTypeStart = 0;
    public static final int PathPointTypeLine = 1;
    public static final int PathPointTypeBezier = 3;
    public static final int PathPointTypePathTypeMask = 7;
    public static final int PathPointTypePathDashMode = 16;
    public static final int PathPointTypePathMarker = 32;
    public static final int PathPointTypeCloseSubpath = 128;
    public static final int PathPointTypeBezier3 = 3;
    public static final int PixelFormatIndexed = 65536;
    public static final int PixelFormatGDI = 131072;
    public static final int PixelFormatAlpha = 262144;
    public static final int PixelFormatPAlpha = 524288;
    public static final int PixelFormatExtended = 1048576;
    public static final int PixelFormatCanonical = 2097152;
    public static final int PixelFormat1bppIndexed = 196865;
    public static final int PixelFormat4bppIndexed = 197634;
    public static final int PixelFormat8bppIndexed = 198659;
    public static final int PixelFormat16bppGrayScale = 1052676;
    public static final int PixelFormat16bppRGB555 = 135173;
    public static final int PixelFormat16bppRGB565 = 135174;
    public static final int PixelFormat16bppARGB1555 = 397319;
    public static final int PixelFormat24bppRGB = 137224;
    public static final int PixelFormat32bppRGB = 139273;
    public static final int PixelFormat32bppARGB = 2498570;
    public static final int PixelFormat32bppPARGB = 925707;
    public static final int PixelFormat48bppRGB = 1060876;
    public static final int PixelFormat64bppARGB = 3424269;
    public static final int PixelFormat64bppPARGB = 1851406;
    public static final int PixelFormat32bppCMYK = 8207;
    public static final int PixelFormatMax = 16;
    public static final int PixelOffsetModeNone = 3;
    public static final int PixelOffsetModeHalf = 4;
    public static final int SmoothingModeDefault = 0;
    public static final int SmoothingModeHighSpeed = 1;
    public static final int SmoothingModeHighQuality = 2;
    public static final int SmoothingModeNone = 3;
    public static final int SmoothingModeAntiAlias8x4 = 4;
    public static final int SmoothingModeAntiAlias = 4;
    public static final int SmoothingModeAntiAlias8x8 = 5;
    public static final int StringFormatFlagsDirectionRightToLeft = 1;
    public static final int StringFormatFlagsDirectionVertical = 2;
    public static final int StringFormatFlagsNoFitBlackBox = 4;
    public static final int StringFormatFlagsDisplayFormatControl = 32;
    public static final int StringFormatFlagsNoFontFallback = 1024;
    public static final int StringFormatFlagsMeasureTrailingSpaces = 2048;
    public static final int StringFormatFlagsNoWrap = 4096;
    public static final int StringFormatFlagsLineLimit = 8192;
    public static final int StringFormatFlagsNoClip = 16384;
    public static final int TextRenderingHintSystemDefault = 0;
    public static final int TextRenderingHintSingleBitPerPixelGridFit = 1;
    public static final int TextRenderingHintSingleBitPerPixel = 2;
    public static final int TextRenderingHintAntiAliasGridFit = 3;
    public static final int TextRenderingHintAntiAlias = 4;
    public static final int TextRenderingHintClearTypeGridFit = 5;
    public static final int UnitPixel = 2;
    public static final int WrapModeTile = 0;
    public static final int WrapModeTileFlipX = 1;
    public static final int WrapModeTileFlipY = 2;
    public static final int WrapModeTileFlipXY = 3;
    public static final int WrapModeClamp = 4;
    
    public static final native int ColorPalette_sizeof();
    
    public static final native int GdiplusStartupInput_sizeof();
    
    public static final native int GdiplusStartup(final long[] p0, final GdiplusStartupInput p1, final long p2);
    
    public static final native void GdiplusShutdown(final long p0);
    
    public static final native long Bitmap_new(final long p0, final long p1);
    
    public static final native long Bitmap_new(final long p0);
    
    public static final native long Bitmap_new(final int p0, final int p1, final int p2, final int p3, final long p4);
    
    public static final native long Bitmap_new(final char[] p0, final boolean p1);
    
    public static final native void Bitmap_delete(final long p0);
    
    public static final native int Bitmap_GetHBITMAP(final long p0, final int p1, final long[] p2);
    
    public static final native int Bitmap_GetHICON(final long p0, final long[] p1);
    
    public static final native long BitmapData_new();
    
    public static final native void BitmapData_delete(final long p0);
    
    public static final native int Bitmap_LockBits(final long p0, final long p1, final int p2, final int p3, final long p4);
    
    public static final native int Bitmap_UnlockBits(final long p0, final long p1);
    
    public static final native long Brush_Clone(final long p0);
    
    public static final native int Brush_GetType(final long p0);
    
    public static final native long PrivateFontCollection_new();
    
    public static final native void PrivateFontCollection_delete(final long p0);
    
    public static final native int PrivateFontCollection_AddFontFile(final long p0, final char[] p1);
    
    public static final native long Font_new(final long p0, final long p1);
    
    public static final native long Font_new(final long p0, final float p1, final int p2, final int p3);
    
    public static final native long Font_new(final char[] p0, final float p1, final int p2, final int p3, final long p4);
    
    public static final native void Font_delete(final long p0);
    
    public static final native int Font_GetFamily(final long p0, final long p1);
    
    public static final native float Font_GetSize(final long p0);
    
    public static final native int Font_GetStyle(final long p0);
    
    public static final native int Font_GetLogFontW(final long p0, final long p1, final long p2);
    
    public static final native boolean Font_IsAvailable(final long p0);
    
    public static final native long FontFamily_new();
    
    public static final native long FontFamily_new(final char[] p0, final long p1);
    
    public static final native void FontFamily_delete(final long p0);
    
    public static final native int FontFamily_GetFamilyName(final long p0, final char[] p1, final char p2);
    
    public static final native boolean FontFamily_IsAvailable(final long p0);
    
    public static final native long Graphics_new(final long p0);
    
    public static final native void Graphics_delete(final long p0);
    
    public static final native int Graphics_DrawArc(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5, final float p6, final float p7);
    
    public static final native int Graphics_DrawDriverString(final long p0, final long p1, final int p2, final long p3, final long p4, final PointF p5, final int p6, final long p7);
    
    public static final native int Graphics_DrawDriverString(final long p0, final long p1, final int p2, final long p3, final long p4, final float[] p5, final int p6, final long p7);
    
    public static final native int Graphics_DrawEllipse(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_DrawImage(final long p0, final long p1, final int p2, final int p3);
    
    public static final native int Graphics_DrawImage(final long p0, final long p1, final Rect p2, final int p3, final int p4, final int p5, final int p6, final int p7, final long p8, final long p9, final long p10);
    
    public static final native int Graphics_DrawLine(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_DrawLines(final long p0, final long p1, final int[] p2, final int p3);
    
    public static final native int Graphics_DrawPath(final long p0, final long p1, final long p2);
    
    public static final native int Graphics_DrawPolygon(final long p0, final long p1, final int[] p2, final int p3);
    
    public static final native int Graphics_DrawRectangle(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_DrawString(final long p0, final char[] p1, final int p2, final long p3, final PointF p4, final long p5);
    
    public static final native int Graphics_DrawString(final long p0, final char[] p1, final int p2, final long p3, final PointF p4, final long p5, final long p6);
    
    public static final native int Graphics_FillEllipse(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_FillPath(final long p0, final long p1, final long p2);
    
    public static final native void Graphics_Flush(final long p0, final int p1);
    
    public static final native int Graphics_FillPie(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5, final float p6, final float p7);
    
    public static final native int Graphics_FillPolygon(final long p0, final long p1, final int[] p2, final int p3, final int p4);
    
    public static final native int Graphics_FillRectangle(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_GetClipBounds(final long p0, final RectF p1);
    
    public static final native int Graphics_GetClipBounds(final long p0, final Rect p1);
    
    public static final native int Graphics_GetClip(final long p0, final long p1);
    
    public static final native long Graphics_GetHDC(final long p0);
    
    public static final native void Graphics_ReleaseHDC(final long p0, final long p1);
    
    public static final native int Graphics_GetInterpolationMode(final long p0);
    
    public static final native int Graphics_GetSmoothingMode(final long p0);
    
    public static final native int Graphics_GetTextRenderingHint(final long p0);
    
    public static final native int Graphics_GetTransform(final long p0, final long p1);
    
    public static final native int Graphics_GetVisibleClipBounds(final long p0, final Rect p1);
    
    public static final native int Graphics_MeasureDriverString(final long p0, final long p1, final int p2, final long p3, final float[] p4, final int p5, final long p6, final RectF p7);
    
    public static final native int Graphics_MeasureString(final long p0, final char[] p1, final int p2, final long p3, final PointF p4, final RectF p5);
    
    public static final native int Graphics_MeasureString(final long p0, final char[] p1, final int p2, final long p3, final PointF p4, final long p5, final RectF p6);
    
    public static final native int Graphics_ResetClip(final long p0);
    
    public static final native int Graphics_Restore(final long p0, final int p1);
    
    public static final native int Graphics_Save(final long p0);
    
    public static final native int Graphics_ScaleTransform(final long p0, final float p1, final float p2, final int p3);
    
    public static final native int Graphics_SetClip(final long p0, final long p1, final int p2);
    
    public static final native int Graphics_SetClip(final long p0, final Rect p1, final int p2);
    
    public static final native int Graphics_SetClipPath(final long p0, final long p1);
    
    public static final native int Graphics_SetClipPath(final long p0, final long p1, final int p2);
    
    public static final native int Graphics_SetCompositingQuality(final long p0, final int p1);
    
    public static final native int Graphics_SetPageUnit(final long p0, final int p1);
    
    public static final native int Graphics_SetPixelOffsetMode(final long p0, final int p1);
    
    public static final native int Graphics_SetSmoothingMode(final long p0, final int p1);
    
    public static final native int Graphics_SetTransform(final long p0, final long p1);
    
    public static final native int Graphics_SetInterpolationMode(final long p0, final int p1);
    
    public static final native int Graphics_SetTextRenderingHint(final long p0, final int p1);
    
    public static final native int Graphics_TranslateTransform(final long p0, final float p1, final float p2, final int p3);
    
    public static final native long GraphicsPath_new(final int p0);
    
    public static final native long GraphicsPath_new(final int[] p0, final byte[] p1, final int p2, final int p3);
    
    public static final native void GraphicsPath_delete(final long p0);
    
    public static final native int GraphicsPath_AddArc(final long p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6);
    
    public static final native int GraphicsPath_AddBezier(final long p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6, final float p7, final float p8);
    
    public static final native int GraphicsPath_AddLine(final long p0, final float p1, final float p2, final float p3, final float p4);
    
    public static final native int GraphicsPath_AddPath(final long p0, final long p1, final boolean p2);
    
    public static final native int GraphicsPath_AddRectangle(final long p0, final RectF p1);
    
    public static final native int GraphicsPath_AddString(final long p0, final char[] p1, final int p2, final long p3, final int p4, final float p5, final PointF p6, final long p7);
    
    public static final native int GraphicsPath_CloseFigure(final long p0);
    
    public static final native long GraphicsPath_Clone(final long p0);
    
    public static final native int GraphicsPath_Flatten(final long p0, final long p1, final float p2);
    
    public static final native int GraphicsPath_GetBounds(final long p0, final RectF p1, final long p2, final long p3);
    
    public static final native int GraphicsPath_GetLastPoint(final long p0, final PointF p1);
    
    public static final native int GraphicsPath_GetPathPoints(final long p0, final float[] p1, final int p2);
    
    public static final native int GraphicsPath_GetPathTypes(final long p0, final byte[] p1, final int p2);
    
    public static final native int GraphicsPath_GetPointCount(final long p0);
    
    public static final native boolean GraphicsPath_IsOutlineVisible(final long p0, final float p1, final float p2, final long p3, final long p4);
    
    public static final native boolean GraphicsPath_IsVisible(final long p0, final float p1, final float p2, final long p3);
    
    public static final native int GraphicsPath_SetFillMode(final long p0, final int p1);
    
    public static final native int GraphicsPath_StartFigure(final long p0);
    
    public static final native int GraphicsPath_Transform(final long p0, final long p1);
    
    public static final native long HatchBrush_new(final int p0, final int p1, final int p2);
    
    public static final native void Image_delete(final long p0);
    
    public static final native long Image_Clone(final long p0);
    
    public static final native int Image_GetLastStatus(final long p0);
    
    public static final native int Image_GetPixelFormat(final long p0);
    
    public static final native int Image_GetWidth(final long p0);
    
    public static final native int Image_GetHeight(final long p0);
    
    public static final native int Image_GetPalette(final long p0, final long p1, final int p2);
    
    public static final native int Image_GetPaletteSize(final long p0);
    
    public static final native long ImageAttributes_new();
    
    public static final native void ImageAttributes_delete(final long p0);
    
    public static final native int ImageAttributes_SetWrapMode(final long p0, final int p1);
    
    public static final native int ImageAttributes_SetColorMatrix(final long p0, final float[] p1, final int p2, final int p3);
    
    public static final native void HatchBrush_delete(final long p0);
    
    public static final native long LinearGradientBrush_new(final PointF p0, final PointF p1, final int p2, final int p3);
    
    public static final native void LinearGradientBrush_delete(final long p0);
    
    public static final native int LinearGradientBrush_SetInterpolationColors(final long p0, final int[] p1, final float[] p2, final int p3);
    
    public static final native int LinearGradientBrush_SetWrapMode(final long p0, final int p1);
    
    public static final native int LinearGradientBrush_ResetTransform(final long p0);
    
    public static final native int LinearGradientBrush_ScaleTransform(final long p0, final float p1, final float p2, final int p3);
    
    public static final native int LinearGradientBrush_TranslateTransform(final long p0, final float p1, final float p2, final int p3);
    
    public static final native long Matrix_new(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    public static final native void Matrix_delete(final long p0);
    
    public static final native int Matrix_GetElements(final long p0, final float[] p1);
    
    public static final native int Matrix_Invert(final long p0);
    
    public static final native boolean Matrix_IsIdentity(final long p0);
    
    public static final native int Matrix_Multiply(final long p0, final long p1, final int p2);
    
    public static final native int Matrix_Rotate(final long p0, final float p1, final int p2);
    
    public static final native int Matrix_Scale(final long p0, final float p1, final float p2, final int p3);
    
    public static final native int Matrix_Shear(final long p0, final float p1, final float p2, final int p3);
    
    public static final native int Matrix_TransformPoints(final long p0, final PointF p1, final int p2);
    
    public static final native int Matrix_TransformPoints(final long p0, final float[] p1, final int p2);
    
    public static final native int Matrix_TransformVectors(final long p0, final PointF p1, final int p2);
    
    public static final native int Matrix_Translate(final long p0, final float p1, final float p2, final int p3);
    
    public static final native int Matrix_SetElements(final long p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6);
    
    public static final native void MoveMemory(final ColorPalette p0, final long p1, final int p2);
    
    public static final native void MoveMemory(final BitmapData p0, final long p1);
    
    public static final native long PathGradientBrush_new(final long p0);
    
    public static final native void PathGradientBrush_delete(final long p0);
    
    public static final native int PathGradientBrush_SetCenterColor(final long p0, final int p1);
    
    public static final native int PathGradientBrush_SetCenterPoint(final long p0, final PointF p1);
    
    public static final native int PathGradientBrush_SetInterpolationColors(final long p0, final int[] p1, final float[] p2, final int p3);
    
    public static final native int PathGradientBrush_SetSurroundColors(final long p0, final int[] p1, final int[] p2);
    
    public static final native int PathGradientBrush_SetGraphicsPath(final long p0, final long p1);
    
    public static final native int PathGradientBrush_SetWrapMode(final long p0, final int p1);
    
    public static final native long Pen_new(final long p0, final float p1);
    
    public static final native void Pen_delete(final long p0);
    
    public static final native long Pen_GetBrush(final long p0);
    
    public static final native int Pen_SetBrush(final long p0, final long p1);
    
    public static final native int Pen_SetDashOffset(final long p0, final float p1);
    
    public static final native int Pen_SetDashPattern(final long p0, final float[] p1, final int p2);
    
    public static final native int Pen_SetDashStyle(final long p0, final int p1);
    
    public static final native int Pen_SetLineCap(final long p0, final int p1, final int p2, final int p3);
    
    public static final native int Pen_SetLineJoin(final long p0, final int p1);
    
    public static final native int Pen_SetMiterLimit(final long p0, final float p1);
    
    public static final native int Pen_SetWidth(final long p0, final float p1);
    
    public static final native long Point_new(final int p0, final int p1);
    
    public static final native void Point_delete(final long p0);
    
    public static final native long Region_new(final long p0);
    
    public static final native long Region_newGraphicsPath(final long p0);
    
    public static final native long Region_new();
    
    public static final native void Region_delete(final long p0);
    
    public static final native long Region_GetHRGN(final long p0, final long p1);
    
    public static final native boolean Region_IsInfinite(final long p0, final long p1);
    
    public static final native long SolidBrush_new(final int p0);
    
    public static final native void SolidBrush_delete(final long p0);
    
    public static final native void StringFormat_delete(final long p0);
    
    public static final native long StringFormat_Clone(final long p0);
    
    public static final native long StringFormat_GenericDefault();
    
    public static final native long StringFormat_GenericTypographic();
    
    public static final native int StringFormat_GetFormatFlags(final long p0);
    
    public static final native int StringFormat_SetHotkeyPrefix(final long p0, final int p1);
    
    public static final native int StringFormat_SetFormatFlags(final long p0, final int p1);
    
    public static final native int StringFormat_SetTabStops(final long p0, final float p1, final int p2, final float[] p3);
    
    public static final native long TextureBrush_new(final long p0, final int p1, final float p2, final float p3, final float p4, final float p5);
    
    public static final native long TextureBrush_new(final long p0, final Rect p1, final long p2);
    
    public static final native void TextureBrush_delete(final long p0);
    
    public static final native int TextureBrush_SetTransform(final long p0, final long p1);
    
    public static final native int TextureBrush_ResetTransform(final long p0);
    
    public static final native int TextureBrush_ScaleTransform(final long p0, final float p1, final float p2, final int p3);
    
    public static final native int TextureBrush_TranslateTransform(final long p0, final float p1, final float p2, final int p3);
    
    public static final native long TextureBrush_GetImage(final long p0);
    
    static {
        Library.loadLibrary("swt-gdip");
    }
}
