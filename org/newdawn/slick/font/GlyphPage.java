//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font;

import java.awt.font.*;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.font.effects.*;
import java.awt.image.*;
import java.util.*;
import org.newdawn.slick.opengl.renderer.*;
import java.nio.*;
import java.awt.*;

public class GlyphPage
{
    private static final SGL GL;
    public static final int MAX_GLYPH_SIZE = 256;
    private static ByteBuffer scratchByteBuffer;
    private static IntBuffer scratchIntBuffer;
    private static BufferedImage scratchImage;
    private static Graphics2D scratchGraphics;
    public static FontRenderContext renderContext;
    private final UnicodeFont unicodeFont;
    private final int pageWidth;
    private final int pageHeight;
    private final Image pageImage;
    private int pageX;
    private int pageY;
    private int rowHeight;
    private boolean orderAscending;
    private final List pageGlyphs;
    
    public static Graphics2D getScratchGraphics() {
        return GlyphPage.scratchGraphics;
    }
    
    public GlyphPage(final UnicodeFont unicodeFont, final int pageWidth, final int pageHeight) throws SlickException {
        this.pageGlyphs = new ArrayList(32);
        this.unicodeFont = unicodeFont;
        this.pageWidth = pageWidth;
        this.pageHeight = pageHeight;
        this.pageImage = new Image(pageWidth, pageHeight);
    }
    
    public int loadGlyphs(final List glyphs, final int maxGlyphsToLoad) throws SlickException {
        if (this.rowHeight != 0 && maxGlyphsToLoad == -1) {
            int testX = this.pageX;
            int testY = this.pageY;
            int testRowHeight = this.rowHeight;
            final Iterator iter = this.getIterator(glyphs);
            while (iter.hasNext()) {
                final Glyph glyph = iter.next();
                final int width = glyph.getWidth();
                final int height = glyph.getHeight();
                if (testX + width >= this.pageWidth) {
                    testX = 0;
                    testY += testRowHeight;
                    testRowHeight = height;
                }
                else if (height > testRowHeight) {
                    testRowHeight = height;
                }
                if (testY + testRowHeight >= this.pageWidth) {
                    return 0;
                }
                testX += width;
            }
        }
        Color.white.bind();
        this.pageImage.bind();
        int i = 0;
        final Iterator iter2 = this.getIterator(glyphs);
        while (iter2.hasNext()) {
            final Glyph glyph2 = iter2.next();
            final int width2 = Math.min(256, glyph2.getWidth());
            final int height2 = Math.min(256, glyph2.getHeight());
            if (this.rowHeight == 0) {
                this.rowHeight = height2;
            }
            else if (this.pageX + width2 >= this.pageWidth) {
                if (this.pageY + this.rowHeight + height2 >= this.pageHeight) {
                    break;
                }
                this.pageX = 0;
                this.pageY += this.rowHeight;
                this.rowHeight = height2;
            }
            else if (height2 > this.rowHeight) {
                if (this.pageY + height2 >= this.pageHeight) {
                    break;
                }
                this.rowHeight = height2;
            }
            this.renderGlyph(glyph2, width2, height2);
            this.pageGlyphs.add(glyph2);
            this.pageX += width2;
            iter2.remove();
            if (++i == maxGlyphsToLoad) {
                this.orderAscending = !this.orderAscending;
                break;
            }
        }
        TextureImpl.bindNone();
        this.orderAscending = !this.orderAscending;
        return i;
    }
    
    private void renderGlyph(final Glyph glyph, final int width, final int height) throws SlickException {
        GlyphPage.scratchGraphics.setComposite(AlphaComposite.Clear);
        GlyphPage.scratchGraphics.fillRect(0, 0, 256, 256);
        GlyphPage.scratchGraphics.setComposite(AlphaComposite.SrcOver);
        GlyphPage.scratchGraphics.setColor(java.awt.Color.white);
        final Iterator iter = this.unicodeFont.getEffects().iterator();
        while (iter.hasNext()) {
            iter.next().draw(GlyphPage.scratchImage, GlyphPage.scratchGraphics, this.unicodeFont, glyph);
        }
        glyph.setShape((Shape)null);
        final WritableRaster raster = GlyphPage.scratchImage.getRaster();
        final int[] row = new int[width];
        for (int y = 0; y < height; ++y) {
            raster.getDataElements(0, y, width, 1, row);
            GlyphPage.scratchIntBuffer.put(row);
        }
        GlyphPage.GL.glTexSubImage2D(3553, 0, this.pageX, this.pageY, width, height, 32993, 5121, GlyphPage.scratchByteBuffer);
        GlyphPage.scratchIntBuffer.clear();
        glyph.setImage(this.pageImage.getSubImage(this.pageX, this.pageY, width, height));
    }
    
    private Iterator getIterator(final List glyphs) {
        class l implements Iterator
        {
            final /* synthetic */ ListIterator val$iter;
            final /* synthetic */ GlyphPage this$0;
            
            l(final GlyphPage this$0, final ListIterator val$iter) {
                this.this$0 = this$0;
                this.val$iter = val$iter;
            }
            
            @Override
            public boolean hasNext() {
                return this.val$iter.hasPrevious();
            }
            
            @Override
            public Object next() {
                return this.val$iter.previous();
            }
            
            @Override
            public void remove() {
                this.val$iter.remove();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/newdawn/slick/font/GlyphPage.orderAscending:Z
        //     4: ifeq            14
        //     7: aload_1         /* glyphs */
        //     8: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    13: areturn        
        //    14: aload_1         /* glyphs */
        //    15: aload_1         /* glyphs */
        //    16: invokeinterface java/util/List.size:()I
        //    21: invokeinterface java/util/List.listIterator:(I)Ljava/util/ListIterator;
        //    26: astore_2        /* iter */
        //    27: new             Lorg/newdawn/slick/font/l;
        //    30: dup            
        //    31: aload_0         /* this */
        //    32: aload_2         /* iter */
        //    33: invokespecial   org/newdawn/slick/font/l.<init>:(Lorg/newdawn/slick/font/GlyphPage;Ljava/util/ListIterator;)V
        //    36: areturn        
        //    StackMapTable: 00 01 0E
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public List getGlyphs() {
        return this.pageGlyphs;
    }
    
    public Image getImage() {
        return this.pageImage;
    }
    
    static {
        GL = Renderer.get();
        (GlyphPage.scratchByteBuffer = ByteBuffer.allocateDirect(262144)).order(ByteOrder.LITTLE_ENDIAN);
        GlyphPage.scratchIntBuffer = GlyphPage.scratchByteBuffer.asIntBuffer();
        GlyphPage.scratchImage = new BufferedImage(256, 256, 2);
        (GlyphPage.scratchGraphics = (Graphics2D)GlyphPage.scratchImage.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GlyphPage.scratchGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        GlyphPage.scratchGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        GlyphPage.renderContext = GlyphPage.scratchGraphics.getFontRenderContext();
    }
}
