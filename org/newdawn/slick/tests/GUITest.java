//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.gui.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;

public class GUITest extends BasicGame implements ComponentListener
{
    private Image image;
    private MouseOverArea[] areas;
    private GameContainer container;
    private String message;
    private TextField field;
    private TextField field2;
    private Image background;
    private Font font;
    private AppGameContainer app;
    
    public GUITest() {
        super("GUI Test");
        this.areas = new MouseOverArea[4];
        this.message = "Demo Menu System with stock images";
    }
    
    public void init(final GameContainer container) throws SlickException {
        class lIIII implements ComponentListener
        {
            final /* synthetic */ GUITest this$0;
            
            lIIII(final GUITest this$0) {
                this.this$0 = this$0;
            }
            
            public void componentActivated(final AbstractComponent source) {
                this.this$0.message = "Entered1: " + this.this$0.field.getText();
                this.this$0.field2.setFocus(true);
            }
        }
        class llII implements ComponentListener
        {
            final /* synthetic */ GUITest this$0;
            
            llII(final GUITest this$0) {
                this.this$0 = this$0;
            }
            
            public void componentActivated(final AbstractComponent source) {
                this.this$0.message = "Entered2: " + this.this$0.field2.getText();
                this.this$0.field.setFocus(true);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: instanceof      Lorg/newdawn/slick/AppGameContainer;
        //     4: ifeq            24
        //     7: aload_0         /* this */
        //     8: aload_1         /* container */
        //     9: checkcast       Lorg/newdawn/slick/AppGameContainer;
        //    12: putfield        org/newdawn/slick/tests/GUITest.app:Lorg/newdawn/slick/AppGameContainer;
        //    15: aload_0         /* this */
        //    16: getfield        org/newdawn/slick/tests/GUITest.app:Lorg/newdawn/slick/AppGameContainer;
        //    19: ldc             "testdata/icon.tga"
        //    21: invokevirtual   org/newdawn/slick/AppGameContainer.setIcon:(Ljava/lang/String;)V
        //    24: aload_0         /* this */
        //    25: new             Lorg/newdawn/slick/AngelCodeFont;
        //    28: dup            
        //    29: ldc             "testdata/demo2.fnt"
        //    31: ldc             "testdata/demo2_00.tga"
        //    33: invokespecial   org/newdawn/slick/AngelCodeFont.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    36: putfield        org/newdawn/slick/tests/GUITest.font:Lorg/newdawn/slick/Font;
        //    39: aload_0         /* this */
        //    40: new             Lorg/newdawn/slick/gui/TextField;
        //    43: dup            
        //    44: aload_1         /* container */
        //    45: aload_0         /* this */
        //    46: getfield        org/newdawn/slick/tests/GUITest.font:Lorg/newdawn/slick/Font;
        //    49: sipush          150
        //    52: bipush          20
        //    54: sipush          500
        //    57: bipush          35
        //    59: new             Lorg/newdawn/slick/tests/lIIII;
        //    62: dup            
        //    63: aload_0         /* this */
        //    64: invokespecial   org/newdawn/slick/tests/lIIII.<init>:(Lorg/newdawn/slick/tests/GUITest;)V
        //    67: invokespecial   org/newdawn/slick/gui/TextField.<init>:(Lorg/newdawn/slick/gui/GUIContext;Lorg/newdawn/slick/Font;IIIILorg/newdawn/slick/gui/ComponentListener;)V
        //    70: putfield        org/newdawn/slick/tests/GUITest.field:Lorg/newdawn/slick/gui/TextField;
        //    73: aload_0         /* this */
        //    74: new             Lorg/newdawn/slick/gui/TextField;
        //    77: dup            
        //    78: aload_1         /* container */
        //    79: aload_0         /* this */
        //    80: getfield        org/newdawn/slick/tests/GUITest.font:Lorg/newdawn/slick/Font;
        //    83: sipush          150
        //    86: bipush          70
        //    88: sipush          500
        //    91: bipush          35
        //    93: new             Lorg/newdawn/slick/tests/llII;
        //    96: dup            
        //    97: aload_0         /* this */
        //    98: invokespecial   org/newdawn/slick/tests/llII.<init>:(Lorg/newdawn/slick/tests/GUITest;)V
        //   101: invokespecial   org/newdawn/slick/gui/TextField.<init>:(Lorg/newdawn/slick/gui/GUIContext;Lorg/newdawn/slick/Font;IIIILorg/newdawn/slick/gui/ComponentListener;)V
        //   104: putfield        org/newdawn/slick/tests/GUITest.field2:Lorg/newdawn/slick/gui/TextField;
        //   107: aload_0         /* this */
        //   108: getfield        org/newdawn/slick/tests/GUITest.field2:Lorg/newdawn/slick/gui/TextField;
        //   111: getstatic       org/newdawn/slick/Color.red:Lorg/newdawn/slick/Color;
        //   114: invokevirtual   org/newdawn/slick/gui/TextField.setBorderColor:(Lorg/newdawn/slick/Color;)V
        //   117: aload_0         /* this */
        //   118: aload_1         /* container */
        //   119: putfield        org/newdawn/slick/tests/GUITest.container:Lorg/newdawn/slick/GameContainer;
        //   122: aload_0         /* this */
        //   123: new             Lorg/newdawn/slick/Image;
        //   126: dup            
        //   127: ldc             "testdata/logo.tga"
        //   129: invokespecial   org/newdawn/slick/Image.<init>:(Ljava/lang/String;)V
        //   132: putfield        org/newdawn/slick/tests/GUITest.image:Lorg/newdawn/slick/Image;
        //   135: aload_0         /* this */
        //   136: new             Lorg/newdawn/slick/Image;
        //   139: dup            
        //   140: ldc             "testdata/dungeontiles.gif"
        //   142: invokespecial   org/newdawn/slick/Image.<init>:(Ljava/lang/String;)V
        //   145: putfield        org/newdawn/slick/tests/GUITest.background:Lorg/newdawn/slick/Image;
        //   148: aload_1         /* container */
        //   149: ldc             "testdata/cursor.tga"
        //   151: iconst_0       
        //   152: iconst_0       
        //   153: invokevirtual   org/newdawn/slick/GameContainer.setMouseCursor:(Ljava/lang/String;II)V
        //   156: iconst_0       
        //   157: istore_2        /* i */
        //   158: iload_2         /* i */
        //   159: iconst_4       
        //   160: if_icmpge       245
        //   163: aload_0         /* this */
        //   164: getfield        org/newdawn/slick/tests/GUITest.areas:[Lorg/newdawn/slick/gui/MouseOverArea;
        //   167: iload_2         /* i */
        //   168: new             Lorg/newdawn/slick/gui/MouseOverArea;
        //   171: dup            
        //   172: aload_1         /* container */
        //   173: aload_0         /* this */
        //   174: getfield        org/newdawn/slick/tests/GUITest.image:Lorg/newdawn/slick/Image;
        //   177: sipush          300
        //   180: bipush          100
        //   182: iload_2         /* i */
        //   183: bipush          100
        //   185: imul           
        //   186: iadd           
        //   187: sipush          200
        //   190: bipush          90
        //   192: aload_0         /* this */
        //   193: invokespecial   org/newdawn/slick/gui/MouseOverArea.<init>:(Lorg/newdawn/slick/gui/GUIContext;Lorg/newdawn/slick/Image;IIIILorg/newdawn/slick/gui/ComponentListener;)V
        //   196: aastore        
        //   197: aload_0         /* this */
        //   198: getfield        org/newdawn/slick/tests/GUITest.areas:[Lorg/newdawn/slick/gui/MouseOverArea;
        //   201: iload_2         /* i */
        //   202: aaload         
        //   203: new             Lorg/newdawn/slick/Color;
        //   206: dup            
        //   207: fconst_1       
        //   208: fconst_1       
        //   209: fconst_1       
        //   210: ldc             0.8
        //   212: invokespecial   org/newdawn/slick/Color.<init>:(FFFF)V
        //   215: invokevirtual   org/newdawn/slick/gui/MouseOverArea.setNormalColor:(Lorg/newdawn/slick/Color;)V
        //   218: aload_0         /* this */
        //   219: getfield        org/newdawn/slick/tests/GUITest.areas:[Lorg/newdawn/slick/gui/MouseOverArea;
        //   222: iload_2         /* i */
        //   223: aaload         
        //   224: new             Lorg/newdawn/slick/Color;
        //   227: dup            
        //   228: fconst_1       
        //   229: fconst_1       
        //   230: fconst_1       
        //   231: ldc             0.9
        //   233: invokespecial   org/newdawn/slick/Color.<init>:(FFFF)V
        //   236: invokevirtual   org/newdawn/slick/gui/MouseOverArea.setMouseOverColor:(Lorg/newdawn/slick/Color;)V
        //   239: iinc            i, 1
        //   242: goto            158
        //   245: return         
        //    Exceptions:
        //  throws org.newdawn.slick.SlickException
        //    StackMapTable: 00 03 18 FC 00 85 01 FA 00 56
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void render(final GameContainer container, final Graphics g) {
        this.background.draw(0.0f, 0.0f, 800.0f, 500.0f);
        for (int i = 0; i < 4; ++i) {
            this.areas[i].render((GUIContext)container, g);
        }
        this.field.render((GUIContext)container, g);
        this.field2.render((GUIContext)container, g);
        g.setFont(this.font);
        g.drawString(this.message, 200.0f, 550.0f);
    }
    
    public void update(final GameContainer container, final int delta) {
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 1) {
            System.exit(0);
        }
        if (key == 60) {
            this.app.setDefaultMouseCursor();
        }
        if (key == 59 && this.app != null) {
            try {
                this.app.setDisplayMode(640, 480, false);
            }
            catch (SlickException e) {
                Log.error((Throwable)e);
            }
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new GUITest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    public void componentActivated(final AbstractComponent source) {
        System.out.println("ACTIVL : " + source);
        for (int i = 0; i < 4; ++i) {
            if (source == this.areas[i]) {
                this.message = "Option " + (i + 1) + " pressed!";
            }
        }
        if (source == this.field2) {}
    }
}
