//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.geom;

import org.newdawn.slick.*;

final class lIlI implements ShapeRenderer.PointCallback
{
    final /* synthetic */ ShapeFill val$fill;
    
    lIlI(final ShapeFill val$fill) {
        this.val$fill = val$fill;
    }
    
    @Override
    public float[] preRenderPoint(final Shape shape, final float x, final float y) {
        this.val$fill.colorAt(shape, x, y).bind();
        final Vector2f offset = this.val$fill.getOffsetAt(shape, x, y);
        return new float[] { offset.x + x, offset.y + y };
    }
}
