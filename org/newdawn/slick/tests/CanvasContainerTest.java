//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import java.awt.event.*;
import org.newdawn.slick.*;
import java.awt.*;

public class CanvasContainerTest extends BasicGame
{
    private Image tga;
    private Image scaleMe;
    private Image scaled;
    private Image gif;
    private Image image;
    private Image subImage;
    private float rot;
    
    public CanvasContainerTest() {
        super("Canvas Container Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        final Image image = new Image("testdata/logo.tga");
        this.tga = image;
        this.image = image;
        this.scaleMe = new Image("testdata/logo.tga", true, 2);
        this.gif = new Image("testdata/logo.gif");
        this.scaled = this.gif.getScaledCopy(120, 120);
        this.subImage = this.image.getSubImage(200, 0, 70, 260);
        this.rot = 0.0f;
    }
    
    public void render(final GameContainer container, final Graphics g) {
        this.image.draw(0.0f, 0.0f);
        this.image.draw(500.0f, 0.0f, 200.0f, 100.0f);
        this.scaleMe.draw(500.0f, 100.0f, 200.0f, 100.0f);
        this.scaled.draw(400.0f, 500.0f);
        final Image flipped = this.scaled.getFlippedCopy(true, false);
        flipped.draw(520.0f, 500.0f);
        final Image flipped2 = flipped.getFlippedCopy(false, true);
        flipped2.draw(520.0f, 380.0f);
        final Image flipped3 = flipped2.getFlippedCopy(true, false);
        flipped3.draw(400.0f, 380.0f);
        for (int i = 0; i < 3; ++i) {
            this.subImage.draw((float)(200 + i * 30), 300.0f);
        }
        g.translate(500.0f, 200.0f);
        g.rotate(50.0f, 50.0f, this.rot);
        g.scale(0.3f, 0.3f);
        this.image.draw();
        g.resetTransform();
    }
    
    public void update(final GameContainer container, final int delta) {
        this.rot += delta * 0.1f;
        if (this.rot > 360.0f) {
            this.rot -= 360.0f;
        }
    }
    
    public static void main(final String[] argv) {
        final class lIlI extends WindowAdapter
        {
            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: new             Lorg/newdawn/slick/tests/CanvasContainerTest;
        //     7: dup            
        //     8: invokespecial   org/newdawn/slick/tests/CanvasContainerTest.<init>:()V
        //    11: invokespecial   org/newdawn/slick/CanvasGameContainer.<init>:(Lorg/newdawn/slick/Game;)V
        //    14: astore_1        /* container */
        //    15: new             Ljava/awt/Frame;
        //    18: dup            
        //    19: ldc             "Test"
        //    21: invokespecial   java/awt/Frame.<init>:(Ljava/lang/String;)V
        //    24: astore_2        /* frame */
        //    25: aload_2         /* frame */
        //    26: new             Ljava/awt/GridLayout;
        //    29: dup            
        //    30: iconst_1       
        //    31: iconst_2       
        //    32: invokespecial   java/awt/GridLayout.<init>:(II)V
        //    35: invokevirtual   java/awt/Frame.setLayout:(Ljava/awt/LayoutManager;)V
        //    38: aload_2         /* frame */
        //    39: sipush          500
        //    42: sipush          500
        //    45: invokevirtual   java/awt/Frame.setSize:(II)V
        //    48: aload_2         /* frame */
        //    49: aload_1         /* container */
        //    50: invokevirtual   java/awt/Frame.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //    53: pop            
        //    54: aload_2         /* frame */
        //    55: new             Lorg/newdawn/slick/tests/lIlI;
        //    58: dup            
        //    59: invokespecial   org/newdawn/slick/tests/lIlI.<init>:()V
        //    62: invokevirtual   java/awt/Frame.addWindowListener:(Ljava/awt/event/WindowListener;)V
        //    65: aload_2         /* frame */
        //    66: iconst_1       
        //    67: invokevirtual   java/awt/Frame.setVisible:(Z)V
        //    70: aload_1         /* container */
        //    71: invokevirtual   org/newdawn/slick/CanvasGameContainer.start:()V
        //    74: goto            82
        //    77: astore_1        /* e */
        //    78: aload_1         /* e */
        //    79: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    82: return         
        //    StackMapTable: 00 02 F7 00 4D 07 00 78 04
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      74     77     82     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 57) {
            if (this.image == this.gif) {
                this.image = this.tga;
            }
            else {
                this.image = this.gif;
            }
        }
    }
}
