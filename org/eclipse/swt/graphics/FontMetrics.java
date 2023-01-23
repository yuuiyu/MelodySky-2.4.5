//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;

public final class FontMetrics
{
    public TEXTMETRIC handle;
    
    FontMetrics() {
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof FontMetrics)) {
            return false;
        }
        final TEXTMETRIC metric = ((FontMetrics)object).handle;
        return this.handle.tmHeight == metric.tmHeight && this.handle.tmAscent == metric.tmAscent && this.handle.tmDescent == metric.tmDescent && this.handle.tmInternalLeading == metric.tmInternalLeading && this.handle.tmExternalLeading == metric.tmExternalLeading && this.handle.tmAveCharWidth == metric.tmAveCharWidth && this.handle.tmMaxCharWidth == metric.tmMaxCharWidth && this.handle.tmWeight == metric.tmWeight && this.handle.tmOverhang == metric.tmOverhang && this.handle.tmDigitizedAspectX == metric.tmDigitizedAspectX && this.handle.tmDigitizedAspectY == metric.tmDigitizedAspectY && this.handle.tmItalic == metric.tmItalic && this.handle.tmUnderlined == metric.tmUnderlined && this.handle.tmStruckOut == metric.tmStruckOut && this.handle.tmPitchAndFamily == metric.tmPitchAndFamily && this.handle.tmCharSet == metric.tmCharSet;
    }
    
    public int getAscent() {
        return DPIUtil.autoScaleDown(this.handle.tmAscent - this.handle.tmInternalLeading);
    }
    
    public double getAverageCharacterWidth() {
        return this.getAverageCharWidth();
    }
    
    @Deprecated
    public int getAverageCharWidth() {
        return DPIUtil.autoScaleDown(this.handle.tmAveCharWidth);
    }
    
    public int getDescent() {
        return DPIUtil.autoScaleDown(this.handle.tmDescent);
    }
    
    public int getHeight() {
        return DPIUtil.autoScaleDown(this.handle.tmHeight);
    }
    
    public int getLeading() {
        return this.getHeight() - this.getAscent() - this.getDescent();
    }
    
    @Override
    public int hashCode() {
        return this.handle.tmHeight ^ this.handle.tmAscent ^ this.handle.tmDescent ^ this.handle.tmInternalLeading ^ this.handle.tmExternalLeading ^ this.handle.tmAveCharWidth ^ this.handle.tmMaxCharWidth ^ this.handle.tmWeight ^ this.handle.tmOverhang ^ this.handle.tmDigitizedAspectX ^ this.handle.tmDigitizedAspectY ^ this.handle.tmItalic ^ this.handle.tmUnderlined ^ this.handle.tmStruckOut ^ this.handle.tmPitchAndFamily ^ this.handle.tmCharSet;
    }
    
    public static FontMetrics win32_new(final TEXTMETRIC handle) {
        final FontMetrics fontMetrics = new FontMetrics();
        fontMetrics.handle = handle;
        return fontMetrics;
    }
}
