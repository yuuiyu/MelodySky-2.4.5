//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class TexturePaintTest extends BasicGame
{
    private Polygon poly;
    private Image image;
    private Rectangle texRect;
    private TexCoordGenerator texPaint;
    
    public TexturePaintTest() {
        super("Texture Paint Test");
        this.poly = new Polygon();
        this.texRect = new Rectangle(50.0f, 50.0f, 100.0f, 100.0f);
    }
    
    public void init(final GameContainer container) throws SlickException {
        class lllI implements TexCoordGenerator
        {
            final /* synthetic */ TexturePaintTest this$0;
            
            lllI(final TexturePaintTest this$0) {
                this.this$0 = this$0;
            }
            
            public Vector2f getCoordFor(final float x, final float y) {
                final float tx = (this.this$0.texRect.getX() - x) / this.this$0.texRect.getWidth();
                final float ty = (this.this$0.texRect.getY() - y) / this.this$0.texRect.getHeight();
                return new Vector2f(tx, ty);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/newdawn/slick/tests/TexturePaintTest.poly:Lorg/newdawn/slick/geom/Polygon;
        //     4: ldc             120.0
        //     6: ldc             120.0
        //     8: invokevirtual   org/newdawn/slick/geom/Polygon.addPoint:(FF)V
        //    11: aload_0         /* this */
        //    12: getfield        org/newdawn/slick/tests/TexturePaintTest.poly:Lorg/newdawn/slick/geom/Polygon;
        //    15: ldc             420.0
        //    17: ldc             100.0
        //    19: invokevirtual   org/newdawn/slick/geom/Polygon.addPoint:(FF)V
        //    22: aload_0         /* this */
        //    23: getfield        org/newdawn/slick/tests/TexturePaintTest.poly:Lorg/newdawn/slick/geom/Polygon;
        //    26: ldc             620.0
        //    28: ldc             420.0
        //    30: invokevirtual   org/newdawn/slick/geom/Polygon.addPoint:(FF)V
        //    33: aload_0         /* this */
        //    34: getfield        org/newdawn/slick/tests/TexturePaintTest.poly:Lorg/newdawn/slick/geom/Polygon;
        //    37: ldc             300.0
        //    39: ldc             320.0
        //    41: invokevirtual   org/newdawn/slick/geom/Polygon.addPoint:(FF)V
        //    44: aload_0         /* this */
        //    45: new             Lorg/newdawn/slick/Image;
        //    48: dup            
        //    49: ldc             "testdata/rocks.png"
        //    51: invokespecial   org/newdawn/slick/Image.<init>:(Ljava/lang/String;)V
        //    54: putfield        org/newdawn/slick/tests/TexturePaintTest.image:Lorg/newdawn/slick/Image;
        //    57: aload_0         /* this */
        //    58: new             Lorg/newdawn/slick/tests/lllI;
        //    61: dup            
        //    62: aload_0         /* this */
        //    63: invokespecial   org/newdawn/slick/tests/lllI.<init>:(Lorg/newdawn/slick/tests/TexturePaintTest;)V
        //    66: putfield        org/newdawn/slick/tests/TexturePaintTest.texPaint:Lorg/newdawn/slick/geom/TexCoordGenerator;
        //    69: return         
        //    Exceptions:
        //  throws org.newdawn.slick.SlickException
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.texture((Shape)this.poly, this.image);
        ShapeRenderer.texture((Shape)this.poly, this.image, this.texPaint);
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new TexturePaintTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
