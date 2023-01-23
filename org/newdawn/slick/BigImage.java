//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import org.lwjgl.*;
import java.nio.*;
import java.io.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;

public class BigImage extends Image
{
    protected static SGL GL;
    private static Image lastBind;
    private Image[][] images;
    private int xcount;
    private int ycount;
    private int realWidth;
    private int realHeight;
    
    public static final int getMaxSingleImageSize() {
        final IntBuffer buffer = BufferUtils.createIntBuffer(16);
        BigImage.GL.glGetInteger(3379, buffer);
        return buffer.get(0);
    }
    
    private BigImage() {
        this.inited = true;
    }
    
    public BigImage(final String ref) throws SlickException {
        this(ref, 2);
    }
    
    public BigImage(final String ref, final int filter) throws SlickException {
        this.build(ref, filter, getMaxSingleImageSize());
    }
    
    public BigImage(final String ref, final int filter, final int tileSize) throws SlickException {
        this.build(ref, filter, tileSize);
    }
    
    public BigImage(final LoadableImageData data, final ByteBuffer imageBuffer, final int filter) {
        this.build(data, imageBuffer, filter, getMaxSingleImageSize());
    }
    
    public BigImage(final LoadableImageData data, final ByteBuffer imageBuffer, final int filter, final int tileSize) {
        this.build(data, imageBuffer, filter, tileSize);
    }
    
    public Image getTile(final int x, final int y) {
        return this.images[x][y];
    }
    
    private void build(final String ref, final int filter, final int tileSize) throws SlickException {
        try {
            final LoadableImageData data = ImageDataFactory.getImageDataFor(ref);
            final ByteBuffer imageBuffer = data.loadImage(ResourceLoader.getResourceAsStream(ref), false, null);
            this.build(data, imageBuffer, filter, tileSize);
        }
        catch (IOException e) {
            throw new SlickException("Failed to load: " + ref, e);
        }
    }
    
    private void build(final LoadableImageData data, final ByteBuffer imageBuffer, final int filter, final int tileSize) {
        class lIllI implements ImageData
        {
            final /* synthetic */ LoadableImageData val$data;
            final /* synthetic */ int val$dataHeight;
            final /* synthetic */ ByteBuffer val$imageBuffer;
            final /* synthetic */ int val$dataWidth;
            final /* synthetic */ BigImage this$0;
            
            lIllI(final BigImage this$0, final LoadableImageData val$data, final int val$dataHeight, final ByteBuffer val$imageBuffer, final int val$dataWidth) {
                this.this$0 = this$0;
                this.val$data = val$data;
                this.val$dataHeight = val$dataHeight;
                this.val$imageBuffer = val$imageBuffer;
                this.val$dataWidth = val$dataWidth;
            }
            
            @Override
            public int getDepth() {
                return this.val$data.getDepth();
            }
            
            @Override
            public int getHeight() {
                return this.val$dataHeight;
            }
            
            @Override
            public ByteBuffer getImageBufferData() {
                return this.val$imageBuffer;
            }
            
            @Override
            public int getTexHeight() {
                return this.val$dataHeight;
            }
            
            @Override
            public int getTexWidth() {
                return this.val$dataWidth;
            }
            
            @Override
            public int getWidth() {
                return this.val$dataWidth;
            }
        }
        class lIlll implements ImageData
        {
            final /* synthetic */ LoadableImageData val$data;
            final /* synthetic */ int val$imageHeight;
            final /* synthetic */ int val$imageWidth;
            final /* synthetic */ ByteBuffer val$subBuffer;
            final /* synthetic */ int val$ySize;
            final /* synthetic */ int val$xSize;
            final /* synthetic */ BigImage this$0;
            
            lIlll(final BigImage this$0, final LoadableImageData val$data, final int val$imageHeight, final int val$imageWidth, final ByteBuffer val$subBuffer, final int val$ySize, final int val$xSize) {
                this.this$0 = this$0;
                this.val$data = val$data;
                this.val$imageHeight = val$imageHeight;
                this.val$imageWidth = val$imageWidth;
                this.val$subBuffer = val$subBuffer;
                this.val$ySize = val$ySize;
                this.val$xSize = val$xSize;
            }
            
            @Override
            public int getDepth() {
                return this.val$data.getDepth();
            }
            
            @Override
            public int getHeight() {
                return this.val$imageHeight;
            }
            
            @Override
            public int getWidth() {
                return this.val$imageWidth;
            }
            
            @Override
            public ByteBuffer getImageBufferData() {
                return this.val$subBuffer;
            }
            
            @Override
            public int getTexHeight() {
                return this.val$ySize;
            }
            
            @Override
            public int getTexWidth() {
                return this.val$xSize;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokeinterface org/newdawn/slick/opengl/LoadableImageData.getTexWidth:()I
        //     6: istore          dataWidth
        //     8: aload_1         /* data */
        //     9: invokeinterface org/newdawn/slick/opengl/LoadableImageData.getTexHeight:()I
        //    14: istore          dataHeight
        //    16: aload_0         /* this */
        //    17: aload_0         /* this */
        //    18: aload_1         /* data */
        //    19: invokeinterface org/newdawn/slick/opengl/LoadableImageData.getWidth:()I
        //    24: dup_x1         
        //    25: putfield        org/newdawn/slick/BigImage.width:I
        //    28: putfield        org/newdawn/slick/BigImage.realWidth:I
        //    31: aload_0         /* this */
        //    32: aload_0         /* this */
        //    33: aload_1         /* data */
        //    34: invokeinterface org/newdawn/slick/opengl/LoadableImageData.getHeight:()I
        //    39: dup_x1         
        //    40: putfield        org/newdawn/slick/BigImage.height:I
        //    43: putfield        org/newdawn/slick/BigImage.realHeight:I
        //    46: iload           dataWidth
        //    48: iload           tileSize
        //    50: if_icmpgt       120
        //    53: iload           dataHeight
        //    55: iload           tileSize
        //    57: if_icmpgt       120
        //    60: aload_0         /* this */
        //    61: iconst_1       
        //    62: iconst_1       
        //    63: multianewarray  [[Lorg/newdawn/slick/Image;
        //    67: putfield        org/newdawn/slick/BigImage.images:[[Lorg/newdawn/slick/Image;
        //    70: new             Lorg/newdawn/slick/lIllI;
        //    73: dup            
        //    74: aload_0         /* this */
        //    75: aload_1         /* data */
        //    76: iload           dataHeight
        //    78: aload_2         /* imageBuffer */
        //    79: iload           dataWidth
        //    81: invokespecial   org/newdawn/slick/lIllI.<init>:(Lorg/newdawn/slick/BigImage;Lorg/newdawn/slick/opengl/LoadableImageData;ILjava/nio/ByteBuffer;I)V
        //    84: astore          tempData
        //    86: aload_0         /* this */
        //    87: getfield        org/newdawn/slick/BigImage.images:[[Lorg/newdawn/slick/Image;
        //    90: iconst_0       
        //    91: aaload         
        //    92: iconst_0       
        //    93: new             Lorg/newdawn/slick/Image;
        //    96: dup            
        //    97: aload           tempData
        //    99: iload_3         /* filter */
        //   100: invokespecial   org/newdawn/slick/Image.<init>:(Lorg/newdawn/slick/opengl/ImageData;I)V
        //   103: aastore        
        //   104: aload_0         /* this */
        //   105: iconst_1       
        //   106: putfield        org/newdawn/slick/BigImage.xcount:I
        //   109: aload_0         /* this */
        //   110: iconst_1       
        //   111: putfield        org/newdawn/slick/BigImage.ycount:I
        //   114: aload_0         /* this */
        //   115: iconst_1       
        //   116: putfield        org/newdawn/slick/BigImage.inited:Z
        //   119: return         
        //   120: aload_0         /* this */
        //   121: aload_0         /* this */
        //   122: getfield        org/newdawn/slick/BigImage.realWidth:I
        //   125: iconst_1       
        //   126: isub           
        //   127: iload           tileSize
        //   129: idiv           
        //   130: iconst_1       
        //   131: iadd           
        //   132: putfield        org/newdawn/slick/BigImage.xcount:I
        //   135: aload_0         /* this */
        //   136: aload_0         /* this */
        //   137: getfield        org/newdawn/slick/BigImage.realHeight:I
        //   140: iconst_1       
        //   141: isub           
        //   142: iload           tileSize
        //   144: idiv           
        //   145: iconst_1       
        //   146: iadd           
        //   147: putfield        org/newdawn/slick/BigImage.ycount:I
        //   150: aload_0         /* this */
        //   151: aload_0         /* this */
        //   152: getfield        org/newdawn/slick/BigImage.xcount:I
        //   155: aload_0         /* this */
        //   156: getfield        org/newdawn/slick/BigImage.ycount:I
        //   159: multianewarray  [[Lorg/newdawn/slick/Image;
        //   163: putfield        org/newdawn/slick/BigImage.images:[[Lorg/newdawn/slick/Image;
        //   166: aload_1         /* data */
        //   167: invokeinterface org/newdawn/slick/opengl/LoadableImageData.getDepth:()I
        //   172: bipush          8
        //   174: idiv           
        //   175: istore          components
        //   177: iconst_0       
        //   178: istore          x
        //   180: iload           x
        //   182: aload_0         /* this */
        //   183: getfield        org/newdawn/slick/BigImage.xcount:I
        //   186: if_icmpge       415
        //   189: iconst_0       
        //   190: istore          y
        //   192: iload           y
        //   194: aload_0         /* this */
        //   195: getfield        org/newdawn/slick/BigImage.ycount:I
        //   198: if_icmpge       409
        //   201: iload           x
        //   203: iconst_1       
        //   204: iadd           
        //   205: iload           tileSize
        //   207: imul           
        //   208: istore          finalX
        //   210: iload           y
        //   212: iconst_1       
        //   213: iadd           
        //   214: iload           tileSize
        //   216: imul           
        //   217: istore          finalY
        //   219: aload_0         /* this */
        //   220: getfield        org/newdawn/slick/BigImage.realWidth:I
        //   223: iload           x
        //   225: iload           tileSize
        //   227: imul           
        //   228: isub           
        //   229: iload           tileSize
        //   231: invokestatic    java/lang/Math.min:(II)I
        //   234: istore          imageWidth
        //   236: aload_0         /* this */
        //   237: getfield        org/newdawn/slick/BigImage.realHeight:I
        //   240: iload           y
        //   242: iload           tileSize
        //   244: imul           
        //   245: isub           
        //   246: iload           tileSize
        //   248: invokestatic    java/lang/Math.min:(II)I
        //   251: istore          imageHeight
        //   253: iload           tileSize
        //   255: istore          xSize
        //   257: iload           tileSize
        //   259: istore          ySize
        //   261: iload           tileSize
        //   263: iload           tileSize
        //   265: imul           
        //   266: iload           components
        //   268: imul           
        //   269: invokestatic    org/lwjgl/BufferUtils.createByteBuffer:(I)Ljava/nio/ByteBuffer;
        //   272: astore          subBuffer
        //   274: iload           x
        //   276: iload           tileSize
        //   278: imul           
        //   279: iload           components
        //   281: imul           
        //   282: istore          xo
        //   284: iload           xSize
        //   286: iload           components
        //   288: imul           
        //   289: newarray        B
        //   291: astore          byteData
        //   293: iconst_0       
        //   294: istore          i
        //   296: iload           i
        //   298: iload           ySize
        //   300: if_icmpge       356
        //   303: iload           y
        //   305: iload           tileSize
        //   307: imul           
        //   308: iload           i
        //   310: iadd           
        //   311: iload           dataWidth
        //   313: imul           
        //   314: iload           components
        //   316: imul           
        //   317: istore          yo
        //   319: aload_2         /* imageBuffer */
        //   320: iload           yo
        //   322: iload           xo
        //   324: iadd           
        //   325: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   328: pop            
        //   329: aload_2         /* imageBuffer */
        //   330: aload           byteData
        //   332: iconst_0       
        //   333: iload           xSize
        //   335: iload           components
        //   337: imul           
        //   338: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   341: pop            
        //   342: aload           subBuffer
        //   344: aload           byteData
        //   346: invokevirtual   java/nio/ByteBuffer.put:([B)Ljava/nio/ByteBuffer;
        //   349: pop            
        //   350: iinc            i, 1
        //   353: goto            296
        //   356: aload           subBuffer
        //   358: invokevirtual   java/nio/ByteBuffer.flip:()Ljava/nio/Buffer;
        //   361: pop            
        //   362: new             Lorg/newdawn/slick/lIlll;
        //   365: dup            
        //   366: aload_0         /* this */
        //   367: aload_1         /* data */
        //   368: iload           imageHeight
        //   370: iload           imageWidth
        //   372: aload           subBuffer
        //   374: iload           ySize
        //   376: iload           xSize
        //   378: invokespecial   org/newdawn/slick/lIlll.<init>:(Lorg/newdawn/slick/BigImage;Lorg/newdawn/slick/opengl/LoadableImageData;IILjava/nio/ByteBuffer;II)V
        //   381: astore          imgData
        //   383: aload_0         /* this */
        //   384: getfield        org/newdawn/slick/BigImage.images:[[Lorg/newdawn/slick/Image;
        //   387: iload           x
        //   389: aaload         
        //   390: iload           y
        //   392: new             Lorg/newdawn/slick/Image;
        //   395: dup            
        //   396: aload           imgData
        //   398: iload_3         /* filter */
        //   399: invokespecial   org/newdawn/slick/Image.<init>:(Lorg/newdawn/slick/opengl/ImageData;I)V
        //   402: aastore        
        //   403: iinc            y, 1
        //   406: goto            192
        //   409: iinc            x, 1
        //   412: goto            180
        //   415: aload_0         /* this */
        //   416: iconst_1       
        //   417: putfield        org/newdawn/slick/BigImage.inited:Z
        //   420: return         
        //    StackMapTable: 00 07 FD 00 78 01 01 FD 00 3B 01 01 FC 00 0B 01 FF 00 67 00 14 07 00 02 07 00 64 07 00 7A 01 01 01 01 01 01 01 01 01 01 01 01 01 07 00 7A 01 07 00 AC 01 00 00 3B FF 00 34 00 0A 07 00 02 07 00 64 07 00 7A 01 01 01 01 01 01 01 00 00 FA 00 05
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void bind() {
        throw new OperationNotSupportedException("Can't bind big images yet");
    }
    
    @Override
    public Image copy() {
        throw new OperationNotSupportedException("Can't copy big images yet");
    }
    
    @Override
    public void draw() {
        this.draw(0.0f, 0.0f);
    }
    
    @Override
    public void draw(final float x, final float y, final Color filter) {
        this.draw(x, y, (float)this.width, (float)this.height, filter);
    }
    
    @Override
    public void draw(final float x, final float y, final float scale, final Color filter) {
        this.draw(x, y, this.width * scale, this.height * scale, filter);
    }
    
    @Override
    public void draw(final float x, final float y, final float width, final float height, final Color filter) {
        final float sx = width / this.realWidth;
        final float sy = height / this.realHeight;
        BigImage.GL.glTranslatef(x, y, 0.0f);
        BigImage.GL.glScalef(sx, sy, 1.0f);
        float xp = 0.0f;
        float yp = 0.0f;
        for (int tx = 0; tx < this.xcount; ++tx) {
            yp = 0.0f;
            for (int ty = 0; ty < this.ycount; ++ty) {
                final Image image = this.images[tx][ty];
                image.draw(xp, yp, (float)image.getWidth(), (float)image.getHeight(), filter);
                yp += image.getHeight();
                if (ty == this.ycount - 1) {
                    xp += image.getWidth();
                }
            }
        }
        BigImage.GL.glScalef(1.0f / sx, 1.0f / sy, 1.0f);
        BigImage.GL.glTranslatef(-x, -y, 0.0f);
    }
    
    @Override
    public void draw(final float x, final float y, final float x2, final float y2, final float srcx, final float srcy, final float srcx2, final float srcy2) {
        final int srcwidth = (int)(srcx2 - srcx);
        final int srcheight = (int)(srcy2 - srcy);
        final Image subImage = this.getSubImage((int)srcx, (int)srcy, srcwidth, srcheight);
        final int width = (int)(x2 - x);
        final int height = (int)(y2 - y);
        subImage.draw(x, y, (float)width, (float)height);
    }
    
    @Override
    public void draw(final float x, final float y, final float srcx, final float srcy, final float srcx2, final float srcy2) {
        final int srcwidth = (int)(srcx2 - srcx);
        final int srcheight = (int)(srcy2 - srcy);
        this.draw(x, y, (float)srcwidth, (float)srcheight, srcx, srcy, srcx2, srcy2);
    }
    
    @Override
    public void draw(final float x, final float y, final float width, final float height) {
        this.draw(x, y, width, height, Color.white);
    }
    
    @Override
    public void draw(final float x, final float y, final float scale) {
        this.draw(x, y, scale, Color.white);
    }
    
    @Override
    public void draw(final float x, final float y) {
        this.draw(x, y, Color.white);
    }
    
    @Override
    public void drawEmbedded(final float x, final float y, final float width, final float height) {
        final float sx = width / this.realWidth;
        final float sy = height / this.realHeight;
        float xp = 0.0f;
        float yp = 0.0f;
        for (int tx = 0; tx < this.xcount; ++tx) {
            yp = 0.0f;
            for (int ty = 0; ty < this.ycount; ++ty) {
                final Image image = this.images[tx][ty];
                if (BigImage.lastBind == null || image.getTexture() != BigImage.lastBind.getTexture()) {
                    if (BigImage.lastBind != null) {
                        BigImage.lastBind.endUse();
                    }
                    (BigImage.lastBind = image).startUse();
                }
                image.drawEmbedded(xp + x, yp + y, (float)image.getWidth(), (float)image.getHeight());
                yp += image.getHeight();
                if (ty == this.ycount - 1) {
                    xp += image.getWidth();
                }
            }
        }
    }
    
    @Override
    public void drawFlash(final float x, final float y, final float width, final float height) {
        final float sx = width / this.realWidth;
        final float sy = height / this.realHeight;
        BigImage.GL.glTranslatef(x, y, 0.0f);
        BigImage.GL.glScalef(sx, sy, 1.0f);
        float xp = 0.0f;
        float yp = 0.0f;
        for (int tx = 0; tx < this.xcount; ++tx) {
            yp = 0.0f;
            for (int ty = 0; ty < this.ycount; ++ty) {
                final Image image = this.images[tx][ty];
                image.drawFlash(xp, yp, (float)image.getWidth(), (float)image.getHeight());
                yp += image.getHeight();
                if (ty == this.ycount - 1) {
                    xp += image.getWidth();
                }
            }
        }
        BigImage.GL.glScalef(1.0f / sx, 1.0f / sy, 1.0f);
        BigImage.GL.glTranslatef(-x, -y, 0.0f);
    }
    
    @Override
    public void drawFlash(final float x, final float y) {
        this.drawFlash(x, y, (float)this.width, (float)this.height);
    }
    
    @Override
    public void endUse() {
        if (BigImage.lastBind != null) {
            BigImage.lastBind.endUse();
        }
        BigImage.lastBind = null;
    }
    
    @Override
    public void startUse() {
    }
    
    @Override
    public void ensureInverted() {
        throw new OperationNotSupportedException("Doesn't make sense for tiled operations");
    }
    
    @Override
    public Color getColor(final int x, final int y) {
        throw new OperationNotSupportedException("Can't use big images as buffers");
    }
    
    @Override
    public Image getFlippedCopy(final boolean flipHorizontal, final boolean flipVertical) {
        final BigImage image = new BigImage();
        image.images = this.images;
        image.xcount = this.xcount;
        image.ycount = this.ycount;
        image.width = this.width;
        image.height = this.height;
        image.realWidth = this.realWidth;
        image.realHeight = this.realHeight;
        if (flipHorizontal) {
            final Image[][] images = image.images;
            image.images = new Image[this.xcount][this.ycount];
            for (int x = 0; x < this.xcount; ++x) {
                for (int y = 0; y < this.ycount; ++y) {
                    image.images[x][y] = images[this.xcount - 1 - x][y].getFlippedCopy(true, false);
                }
            }
        }
        if (flipVertical) {
            final Image[][] images = image.images;
            image.images = new Image[this.xcount][this.ycount];
            for (int x = 0; x < this.xcount; ++x) {
                for (int y = 0; y < this.ycount; ++y) {
                    image.images[x][y] = images[x][this.ycount - 1 - y].getFlippedCopy(false, true);
                }
            }
        }
        return image;
    }
    
    @Override
    public Graphics getGraphics() throws SlickException {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    @Override
    public Image getScaledCopy(final float scale) {
        return this.getScaledCopy((int)(scale * this.width), (int)(scale * this.height));
    }
    
    @Override
    public Image getScaledCopy(final int width, final int height) {
        final BigImage image = new BigImage();
        image.images = this.images;
        image.xcount = this.xcount;
        image.ycount = this.ycount;
        image.width = width;
        image.height = height;
        image.realWidth = this.realWidth;
        image.realHeight = this.realHeight;
        return image;
    }
    
    @Override
    public Image getSubImage(final int x, final int y, final int width, final int height) {
        final BigImage image = new BigImage();
        image.width = width;
        image.height = height;
        image.realWidth = width;
        image.realHeight = height;
        image.images = new Image[this.xcount][this.ycount];
        float xp = 0.0f;
        float yp = 0.0f;
        final int x2 = x + width;
        final int y2 = y + height;
        int startx = 0;
        int starty = 0;
        boolean foundStart = false;
        for (int xt = 0; xt < this.xcount; ++xt) {
            yp = 0.0f;
            starty = 0;
            foundStart = false;
            for (int yt = 0; yt < this.ycount; ++yt) {
                final Image current = this.images[xt][yt];
                final int xp2 = (int)(xp + current.getWidth());
                final int yp2 = (int)(yp + current.getHeight());
                final int targetX1 = (int)Math.max((float)x, xp);
                final int targetY1 = (int)Math.max((float)y, yp);
                final int targetX2 = Math.min(x2, xp2);
                final int targetY2 = Math.min(y2, yp2);
                final int targetWidth = targetX2 - targetX1;
                final int targetHeight = targetY2 - targetY1;
                if (targetWidth > 0 && targetHeight > 0) {
                    final Image subImage = current.getSubImage((int)(targetX1 - xp), (int)(targetY1 - yp), targetX2 - targetX1, targetY2 - targetY1);
                    foundStart = true;
                    image.images[startx][starty] = subImage;
                    ++starty;
                    image.ycount = Math.max(image.ycount, starty);
                }
                yp += current.getHeight();
                if (yt == this.ycount - 1) {
                    xp += current.getWidth();
                }
            }
            if (foundStart) {
                ++startx;
                final BigImage bigImage = image;
                ++bigImage.xcount;
            }
        }
        return image;
    }
    
    @Override
    public Texture getTexture() {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    @Override
    protected void initImpl() {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    @Override
    protected void reinit() {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    @Override
    public void setTexture(final Texture texture) {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    public Image getSubImage(final int offsetX, final int offsetY) {
        return this.images[offsetX][offsetY];
    }
    
    public int getHorizontalImageCount() {
        return this.xcount;
    }
    
    public int getVerticalImageCount() {
        return this.ycount;
    }
    
    @Override
    public String toString() {
        return "[BIG IMAGE]";
    }
    
    @Override
    public void destroy() throws SlickException {
        for (int tx = 0; tx < this.xcount; ++tx) {
            for (int ty = 0; ty < this.ycount; ++ty) {
                final Image image = this.images[tx][ty];
                image.destroy();
            }
        }
    }
    
    @Override
    public void draw(final float x, final float y, final float x2, final float y2, final float srcx, final float srcy, final float srcx2, final float srcy2, final Color filter) {
        final int srcwidth = (int)(srcx2 - srcx);
        final int srcheight = (int)(srcy2 - srcy);
        final Image subImage = this.getSubImage((int)srcx, (int)srcy, srcwidth, srcheight);
        final int width = (int)(x2 - x);
        final int height = (int)(y2 - y);
        subImage.draw(x, y, (float)width, (float)height, filter);
    }
    
    @Override
    public void drawCentered(final float x, final float y) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void drawEmbedded(final float x, final float y, final float x2, final float y2, final float srcx, final float srcy, final float srcx2, final float srcy2, final Color filter) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void drawEmbedded(final float x, final float y, final float x2, final float y2, final float srcx, final float srcy, final float srcx2, final float srcy2) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void drawFlash(final float x, final float y, final float width, final float height, final Color col) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void drawSheared(final float x, final float y, final float hshear, final float vshear) {
        throw new UnsupportedOperationException();
    }
    
    static {
        BigImage.GL = Renderer.get();
    }
}
