//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.geom;

import org.newdawn.slick.opengl.*;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.renderer.*;

public final class ShapeRenderer
{
    private static SGL GL;
    private static LineStripRenderer LSR;
    
    public static final void draw(final Shape shape) {
        final Texture t = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        final float[] points = shape.getPoints();
        ShapeRenderer.LSR.start();
        for (int i = 0; i < points.length; i += 2) {
            ShapeRenderer.LSR.vertex(points[i], points[i + 1]);
        }
        if (shape.closed()) {
            ShapeRenderer.LSR.vertex(points[0], points[1]);
        }
        ShapeRenderer.LSR.end();
        if (t == null) {
            TextureImpl.bindNone();
        }
        else {
            t.bind();
        }
    }
    
    public static final void draw(final Shape shape, final ShapeFill fill) {
        final float[] points = shape.getPoints();
        final Texture t = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        final float[] center = shape.getCenter();
        ShapeRenderer.GL.glBegin(3);
        for (int i = 0; i < points.length; i += 2) {
            fill.colorAt(shape, points[i], points[i + 1]).bind();
            final Vector2f offset = fill.getOffsetAt(shape, points[i], points[i + 1]);
            ShapeRenderer.GL.glVertex2f(points[i] + offset.x, points[i + 1] + offset.y);
        }
        if (shape.closed()) {
            fill.colorAt(shape, points[0], points[1]).bind();
            final Vector2f offset2 = fill.getOffsetAt(shape, points[0], points[1]);
            ShapeRenderer.GL.glVertex2f(points[0] + offset2.x, points[1] + offset2.y);
        }
        ShapeRenderer.GL.glEnd();
        if (t == null) {
            TextureImpl.bindNone();
        }
        else {
            t.bind();
        }
    }
    
    public static boolean validFill(final Shape shape) {
        return shape.getTriangles() != null && shape.getTriangles().getTriangleCount() != 0;
    }
    
    public static final void fill(final Shape shape) {
        if (!validFill(shape)) {
            return;
        }
        final Texture t = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        fill(shape, (PointCallback)new llI());
        if (t == null) {
            TextureImpl.bindNone();
        }
        else {
            t.bind();
        }
    }
    
    private static final void fill(final Shape shape, final PointCallback callback) {
        final Triangulator tris = shape.getTriangles();
        ShapeRenderer.GL.glBegin(4);
        for (int i = 0; i < tris.getTriangleCount(); ++i) {
            for (int p = 0; p < 3; ++p) {
                final float[] pt = tris.getTrianglePoint(i, p);
                final float[] np = callback.preRenderPoint(shape, pt[0], pt[1]);
                if (np == null) {
                    ShapeRenderer.GL.glVertex2f(pt[0], pt[1]);
                }
                else {
                    ShapeRenderer.GL.glVertex2f(np[0], np[1]);
                }
            }
        }
        ShapeRenderer.GL.glEnd();
    }
    
    public static final void texture(final Shape shape, final Image image) {
        texture(shape, image, 0.01f, 0.01f);
    }
    
    public static final void textureFit(final Shape shape, final Image image) {
        textureFit(shape, image, 1.0f, 1.0f);
    }
    
    public static final void texture(final Shape shape, final Image image, final float scaleX, final float scaleY) {
        final class lIIl implements PointCallback
        {
            final /* synthetic */ float val$scaleX;
            final /* synthetic */ float val$scaleY;
            final /* synthetic */ Image val$image;
            
            lIIl(final float val$scaleX, final float val$scaleY, final Image val$image) {
                this.val$scaleX = val$scaleX;
                this.val$scaleY = val$scaleY;
                this.val$image = val$image;
            }
            
            @Override
            public float[] preRenderPoint(final Shape shape, final float x, final float y) {
                float tx = x * this.val$scaleX;
                float ty = y * this.val$scaleY;
                tx = this.val$image.getTextureOffsetX() + this.val$image.getTextureWidth() * tx;
                ty = this.val$image.getTextureOffsetY() + this.val$image.getTextureHeight() * ty;
                ShapeRenderer.GL.glTexCoord2f(tx, ty);
                return null;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    org/newdawn/slick/geom/ShapeRenderer.validFill:(Lorg/newdawn/slick/geom/Shape;)Z
        //     4: ifne            8
        //     7: return         
        //     8: invokestatic    org/newdawn/slick/opengl/TextureImpl.getLastBind:()Lorg/newdawn/slick/opengl/Texture;
        //    11: astore          t
        //    13: aload_1         /* image */
        //    14: invokevirtual   org/newdawn/slick/Image.getTexture:()Lorg/newdawn/slick/opengl/Texture;
        //    17: invokeinterface org/newdawn/slick/opengl/Texture.bind:()V
        //    22: aload_0         /* shape */
        //    23: new             Lorg/newdawn/slick/geom/lIIl;
        //    26: dup            
        //    27: fload_2         /* scaleX */
        //    28: fload_3         /* scaleY */
        //    29: aload_1         /* image */
        //    30: invokespecial   org/newdawn/slick/geom/lIIl.<init>:(FFLorg/newdawn/slick/Image;)V
        //    33: invokestatic    org/newdawn/slick/geom/ShapeRenderer.fill:(Lorg/newdawn/slick/geom/Shape;Lorg/newdawn/slick/geom/ShapeRenderer$PointCallback;)V
        //    36: aload_0         /* shape */
        //    37: invokevirtual   org/newdawn/slick/geom/Shape.getPoints:()[F
        //    40: astore          points
        //    42: aload           t
        //    44: ifnonnull       53
        //    47: invokestatic    org/newdawn/slick/opengl/TextureImpl.bindNone:()V
        //    50: goto            60
        //    53: aload           t
        //    55: invokeinterface org/newdawn/slick/opengl/Texture.bind:()V
        //    60: return         
        //    StackMapTable: 00 03 08 FD 00 2C 07 00 38 07 00 3A 06
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static final void textureFit(final Shape shape, final Image image, final float scaleX, final float scaleY) {
        final class lIll implements PointCallback
        {
            final /* synthetic */ float val$scaleX;
            final /* synthetic */ float val$scaleY;
            final /* synthetic */ Image val$image;
            
            lIll(final float val$scaleX, final float val$scaleY, final Image val$image) {
                this.val$scaleX = val$scaleX;
                this.val$scaleY = val$scaleY;
                this.val$image = val$image;
            }
            
            @Override
            public float[] preRenderPoint(final Shape shape, float x, float y) {
                x -= shape.getMinX();
                y -= shape.getMinY();
                x /= shape.getMaxX() - shape.getMinX();
                y /= shape.getMaxY() - shape.getMinY();
                float tx = x * this.val$scaleX;
                float ty = y * this.val$scaleY;
                tx = this.val$image.getTextureOffsetX() + this.val$image.getTextureWidth() * tx;
                ty = this.val$image.getTextureOffsetY() + this.val$image.getTextureHeight() * ty;
                ShapeRenderer.GL.glTexCoord2f(tx, ty);
                return null;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    org/newdawn/slick/geom/ShapeRenderer.validFill:(Lorg/newdawn/slick/geom/Shape;)Z
        //     4: ifne            8
        //     7: return         
        //     8: aload_0         /* shape */
        //     9: invokevirtual   org/newdawn/slick/geom/Shape.getPoints:()[F
        //    12: astore          points
        //    14: invokestatic    org/newdawn/slick/opengl/TextureImpl.getLastBind:()Lorg/newdawn/slick/opengl/Texture;
        //    17: astore          t
        //    19: aload_1         /* image */
        //    20: invokevirtual   org/newdawn/slick/Image.getTexture:()Lorg/newdawn/slick/opengl/Texture;
        //    23: invokeinterface org/newdawn/slick/opengl/Texture.bind:()V
        //    28: aload_0         /* shape */
        //    29: invokevirtual   org/newdawn/slick/geom/Shape.getX:()F
        //    32: fstore          minX
        //    34: aload_0         /* shape */
        //    35: invokevirtual   org/newdawn/slick/geom/Shape.getY:()F
        //    38: fstore          minY
        //    40: aload_0         /* shape */
        //    41: invokevirtual   org/newdawn/slick/geom/Shape.getMaxX:()F
        //    44: fload           minX
        //    46: fsub           
        //    47: fstore          maxX
        //    49: aload_0         /* shape */
        //    50: invokevirtual   org/newdawn/slick/geom/Shape.getMaxY:()F
        //    53: fload           minY
        //    55: fsub           
        //    56: fstore          maxY
        //    58: aload_0         /* shape */
        //    59: new             Lorg/newdawn/slick/geom/lIll;
        //    62: dup            
        //    63: fload_2         /* scaleX */
        //    64: fload_3         /* scaleY */
        //    65: aload_1         /* image */
        //    66: invokespecial   org/newdawn/slick/geom/lIll.<init>:(FFLorg/newdawn/slick/Image;)V
        //    69: invokestatic    org/newdawn/slick/geom/ShapeRenderer.fill:(Lorg/newdawn/slick/geom/Shape;Lorg/newdawn/slick/geom/ShapeRenderer$PointCallback;)V
        //    72: aload           t
        //    74: ifnonnull       83
        //    77: invokestatic    org/newdawn/slick/opengl/TextureImpl.bindNone:()V
        //    80: goto            90
        //    83: aload           t
        //    85: invokeinterface org/newdawn/slick/opengl/Texture.bind:()V
        //    90: return         
        //    StackMapTable: 00 03 08 FF 00 4A 00 0A 07 00 2B 07 00 AA 02 02 07 00 3A 07 00 38 02 02 02 02 00 00 06
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static final void fill(final Shape shape, final ShapeFill fill) {
        if (!validFill(shape)) {
            return;
        }
        final Texture t = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        final float[] center = shape.getCenter();
        fill(shape, (PointCallback)new lIlI(fill));
        if (t == null) {
            TextureImpl.bindNone();
        }
        else {
            t.bind();
        }
    }
    
    public static final void texture(final Shape shape, final Image image, final float scaleX, final float scaleY, final ShapeFill fill) {
        final class lIII implements PointCallback
        {
            final /* synthetic */ ShapeFill val$fill;
            final /* synthetic */ float[] val$center;
            final /* synthetic */ float val$scaleX;
            final /* synthetic */ float val$scaleY;
            final /* synthetic */ Image val$image;
            
            lIII(final ShapeFill val$fill, final float[] val$center, final float val$scaleX, final float val$scaleY, final Image val$image) {
                this.val$fill = val$fill;
                this.val$center = val$center;
                this.val$scaleX = val$scaleX;
                this.val$scaleY = val$scaleY;
                this.val$image = val$image;
            }
            
            @Override
            public float[] preRenderPoint(final Shape shape, float x, float y) {
                this.val$fill.colorAt(shape, x - this.val$center[0], y - this.val$center[1]).bind();
                final Vector2f offset = this.val$fill.getOffsetAt(shape, x, y);
                x += offset.x;
                y += offset.y;
                float tx = x * this.val$scaleX;
                float ty = y * this.val$scaleY;
                tx = this.val$image.getTextureOffsetX() + this.val$image.getTextureWidth() * tx;
                ty = this.val$image.getTextureOffsetY() + this.val$image.getTextureHeight() * ty;
                ShapeRenderer.GL.glTexCoord2f(tx, ty);
                return new float[] { offset.x + x, offset.y + y };
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    org/newdawn/slick/geom/ShapeRenderer.validFill:(Lorg/newdawn/slick/geom/Shape;)Z
        //     4: ifne            8
        //     7: return         
        //     8: invokestatic    org/newdawn/slick/opengl/TextureImpl.getLastBind:()Lorg/newdawn/slick/opengl/Texture;
        //    11: astore          t
        //    13: aload_1         /* image */
        //    14: invokevirtual   org/newdawn/slick/Image.getTexture:()Lorg/newdawn/slick/opengl/Texture;
        //    17: invokeinterface org/newdawn/slick/opengl/Texture.bind:()V
        //    22: aload_0         /* shape */
        //    23: invokevirtual   org/newdawn/slick/geom/Shape.getCenter:()[F
        //    26: astore          center
        //    28: aload_0         /* shape */
        //    29: new             Lorg/newdawn/slick/geom/lIII;
        //    32: dup            
        //    33: aload           fill
        //    35: aload           center
        //    37: fload_2         /* scaleX */
        //    38: fload_3         /* scaleY */
        //    39: aload_1         /* image */
        //    40: invokespecial   org/newdawn/slick/geom/lIII.<init>:(Lorg/newdawn/slick/ShapeFill;[FFFLorg/newdawn/slick/Image;)V
        //    43: invokestatic    org/newdawn/slick/geom/ShapeRenderer.fill:(Lorg/newdawn/slick/geom/Shape;Lorg/newdawn/slick/geom/ShapeRenderer$PointCallback;)V
        //    46: aload           t
        //    48: ifnonnull       57
        //    51: invokestatic    org/newdawn/slick/opengl/TextureImpl.bindNone:()V
        //    54: goto            64
        //    57: aload           t
        //    59: invokeinterface org/newdawn/slick/opengl/Texture.bind:()V
        //    64: return         
        //    StackMapTable: 00 03 08 FD 00 30 07 00 38 07 00 3A 06
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static final void texture(final Shape shape, final Image image, final TexCoordGenerator gen) {
        final class lll implements PointCallback
        {
            final /* synthetic */ TexCoordGenerator val$gen;
            
            lll(final TexCoordGenerator val$gen) {
                this.val$gen = val$gen;
            }
            
            @Override
            public float[] preRenderPoint(final Shape shape, final float x, final float y) {
                final Vector2f tex = this.val$gen.getCoordFor(x, y);
                ShapeRenderer.GL.glTexCoord2f(tex.x, tex.y);
                return new float[] { x, y };
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_3        /* t */
        //     4: aload_1         /* image */
        //     5: invokevirtual   org/newdawn/slick/Image.getTexture:()Lorg/newdawn/slick/opengl/Texture;
        //     8: invokeinterface org/newdawn/slick/opengl/Texture.bind:()V
        //    13: aload_0         /* shape */
        //    14: invokevirtual   org/newdawn/slick/geom/Shape.getCenter:()[F
        //    17: astore          center
        //    19: aload_0         /* shape */
        //    20: new             Lorg/newdawn/slick/geom/lll;
        //    23: dup            
        //    24: aload_2         /* gen */
        //    25: invokespecial   org/newdawn/slick/geom/lll.<init>:(Lorg/newdawn/slick/geom/TexCoordGenerator;)V
        //    28: invokestatic    org/newdawn/slick/geom/ShapeRenderer.fill:(Lorg/newdawn/slick/geom/Shape;Lorg/newdawn/slick/geom/ShapeRenderer$PointCallback;)V
        //    31: aload_3         /* t */
        //    32: ifnonnull       41
        //    35: invokestatic    org/newdawn/slick/opengl/TextureImpl.bindNone:()V
        //    38: goto            47
        //    41: aload_3         /* t */
        //    42: invokeinterface org/newdawn/slick/opengl/Texture.bind:()V
        //    47: return         
        //    StackMapTable: 00 02 FD 00 29 07 00 38 07 00 3A 05
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        ShapeRenderer.GL = Renderer.get();
        ShapeRenderer.LSR = Renderer.getLineStripRenderer();
    }
    
    private interface PointCallback
    {
        float[] preRenderPoint(final Shape p0, final float p1, final float p2);
    }
}
