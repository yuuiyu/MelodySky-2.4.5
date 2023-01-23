//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import java.awt.event.*;
import org.newdawn.slick.*;
import java.awt.*;

public class CanvasSizeTest extends BasicGame
{
    public CanvasSizeTest() {
        super("Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        System.out.println(container.getWidth() + ", " + container.getHeight());
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public static void main(final String[] args) {
        final class llll extends WindowAdapter
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
        //     4: new             Lorg/newdawn/slick/tests/CanvasSizeTest;
        //     7: dup            
        //     8: invokespecial   org/newdawn/slick/tests/CanvasSizeTest.<init>:()V
        //    11: invokespecial   org/newdawn/slick/CanvasGameContainer.<init>:(Lorg/newdawn/slick/Game;)V
        //    14: astore_1        /* container */
        //    15: aload_1         /* container */
        //    16: sipush          640
        //    19: sipush          480
        //    22: invokevirtual   org/newdawn/slick/CanvasGameContainer.setSize:(II)V
        //    25: new             Ljava/awt/Frame;
        //    28: dup            
        //    29: ldc             "Test"
        //    31: invokespecial   java/awt/Frame.<init>:(Ljava/lang/String;)V
        //    34: astore_2        /* frame */
        //    35: aload_2         /* frame */
        //    36: new             Ljava/awt/GridLayout;
        //    39: dup            
        //    40: iconst_1       
        //    41: iconst_2       
        //    42: invokespecial   java/awt/GridLayout.<init>:(II)V
        //    45: invokevirtual   java/awt/Frame.setLayout:(Ljava/awt/LayoutManager;)V
        //    48: aload_2         /* frame */
        //    49: aload_1         /* container */
        //    50: invokevirtual   java/awt/Frame.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //    53: pop            
        //    54: aload_2         /* frame */
        //    55: invokevirtual   java/awt/Frame.pack:()V
        //    58: aload_2         /* frame */
        //    59: new             Lorg/newdawn/slick/tests/llll;
        //    62: dup            
        //    63: invokespecial   org/newdawn/slick/tests/llll.<init>:()V
        //    66: invokevirtual   java/awt/Frame.addWindowListener:(Ljava/awt/event/WindowListener;)V
        //    69: aload_2         /* frame */
        //    70: iconst_1       
        //    71: invokevirtual   java/awt/Frame.setVisible:(Z)V
        //    74: aload_1         /* container */
        //    75: invokevirtual   org/newdawn/slick/CanvasGameContainer.start:()V
        //    78: goto            86
        //    81: astore_1        /* e */
        //    82: aload_1         /* e */
        //    83: invokestatic    org/newdawn/slick/util/Log.error:(Ljava/lang/Throwable;)V
        //    86: return         
        //    StackMapTable: 00 02 F7 00 51 07 00 47 04
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      78     81     86     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
