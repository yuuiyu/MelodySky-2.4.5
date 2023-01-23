//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;

public final class GlyphMetrics
{
    public int ascent;
    public int descent;
    public int width;
    
    public GlyphMetrics(final int ascent, final int descent, final int width) {
        if (ascent < 0 || descent < 0 || width < 0) {
            SWT.error(5);
        }
        this.ascent = ascent;
        this.descent = descent;
        this.width = width;
    }
    
    int getAscentInPixels() {
        return DPIUtil.autoScaleUp(this.ascent);
    }
    
    int getDescentInPixels() {
        return DPIUtil.autoScaleUp(this.descent);
    }
    
    int getWidthInPixels() {
        return DPIUtil.autoScaleUp(this.width);
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof GlyphMetrics)) {
            return false;
        }
        final GlyphMetrics metrics = (GlyphMetrics)object;
        return metrics.ascent == this.ascent && metrics.descent == this.descent && metrics.width == this.width;
    }
    
    @Override
    public int hashCode() {
        return this.ascent ^ this.descent ^ this.width;
    }
    
    @Override
    public String toString() {
        return "GlyphMetrics {" + this.ascent + ", " + this.descent + ", " + this.width;
    }
}
