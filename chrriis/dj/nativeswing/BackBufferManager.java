//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.image.*;
import chrriis.dj.nativeswing.common.*;
import java.awt.*;

class BackBufferManager
{
    private NativeComponentWrapper nativeComponent;
    private Component paintingComponent;
    private final Object backBufferLock;
    private BufferedImage backBuffer;
    
    public BackBufferManager(final NativeComponentWrapper nativeComponent, final Component paintingComponent) {
        this.backBufferLock = new Object();
        this.nativeComponent = nativeComponent;
        this.paintingComponent = paintingComponent;
    }
    
    public void updateBackBufferOnVisibleTranslucentAreas() {
        final int width = this.paintingComponent.getWidth();
        final int height = this.paintingComponent.getHeight();
        if (width <= 0 || height <= 0) {
            if (this.backBuffer != null) {
                this.backBuffer.flush();
            }
            this.backBuffer = null;
            return;
        }
        this.updateBackBuffer(this.getTranslucentOverlays());
    }
    
    protected Rectangle[] getTranslucentOverlays() {
        class lIIIlI implements Filter<Component>
        {
            final /* synthetic */ BackBufferManager this$0;
            
            lIIIlI(final BackBufferManager this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public Acceptance accept(final Component c) {
                if (c.isOpaque()) {
                    return Acceptance.YES;
                }
                return Acceptance.TEST_CHILDREN;
            }
        }
        class lIllll implements Filter<Component>
        {
            final /* synthetic */ BackBufferManager this$0;
            
            lIllll(final BackBufferManager this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public Acceptance accept(final Component c) {
                if (!c.isOpaque()) {
                    return Acceptance.YES;
                }
                return Acceptance.NO;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: anewarray       Ljava/awt/Rectangle;
        //     4: dup            
        //     5: iconst_0       
        //     6: new             Ljava/awt/Rectangle;
        //     9: dup            
        //    10: iconst_0       
        //    11: iconst_0       
        //    12: aload_0         /* this */
        //    13: getfield        chrriis/dj/nativeswing/BackBufferManager.paintingComponent:Ljava/awt/Component;
        //    16: invokevirtual   java/awt/Component.getWidth:()I
        //    19: aload_0         /* this */
        //    20: getfield        chrriis/dj/nativeswing/BackBufferManager.paintingComponent:Ljava/awt/Component;
        //    23: invokevirtual   java/awt/Component.getHeight:()I
        //    26: invokespecial   java/awt/Rectangle.<init>:(IIII)V
        //    29: aastore        
        //    30: astore_1        /* boundsArea */
        //    31: aload_1         /* boundsArea */
        //    32: aload_0         /* this */
        //    33: getfield        chrriis/dj/nativeswing/BackBufferManager.paintingComponent:Ljava/awt/Component;
        //    36: new             Lchrriis/dj/nativeswing/lIIIlI;
        //    39: dup            
        //    40: aload_0         /* this */
        //    41: invokespecial   chrriis/dj/nativeswing/lIIIlI.<init>:(Lchrriis/dj/nativeswing/BackBufferManager;)V
        //    44: invokestatic    chrriis/dj/nativeswing/common/UIUtils.getComponentVisibleArea:(Ljava/awt/Component;Lchrriis/dj/nativeswing/common/Filter;)[Ljava/awt/Rectangle;
        //    47: invokestatic    chrriis/dj/nativeswing/common/UIUtils.subtract:([Ljava/awt/Rectangle;[Ljava/awt/Rectangle;)[Ljava/awt/Rectangle;
        //    50: astore_1        /* boundsArea */
        //    51: aload_1         /* boundsArea */
        //    52: aload_0         /* this */
        //    53: getfield        chrriis/dj/nativeswing/BackBufferManager.paintingComponent:Ljava/awt/Component;
        //    56: new             Lchrriis/dj/nativeswing/lIllll;
        //    59: dup            
        //    60: aload_0         /* this */
        //    61: invokespecial   chrriis/dj/nativeswing/lIllll.<init>:(Lchrriis/dj/nativeswing/BackBufferManager;)V
        //    64: invokestatic    chrriis/dj/nativeswing/common/UIUtils.getComponentVisibleArea:(Ljava/awt/Component;Lchrriis/dj/nativeswing/common/Filter;)[Ljava/awt/Rectangle;
        //    67: invokestatic    chrriis/dj/nativeswing/common/UIUtils.subtract:([Ljava/awt/Rectangle;[Ljava/awt/Rectangle;)[Ljava/awt/Rectangle;
        //    70: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void createBackBuffer() {
        this.updateBackBuffer(new Rectangle[] { new Rectangle(this.paintingComponent.getWidth(), this.paintingComponent.getHeight()) });
    }
    
    public void updateBackBuffer(final Rectangle[] rectangles) {
        if (rectangles == null || rectangles.length == 0) {
            return;
        }
        final int width = this.paintingComponent.getWidth();
        final int height = this.paintingComponent.getHeight();
        if (width <= 0 || height <= 0) {
            if (this.backBuffer != null) {
                this.backBuffer.flush();
            }
            this.backBuffer = null;
            return;
        }
        BufferedImage image;
        if (this.backBuffer != null && this.backBuffer.getWidth() == width && this.backBuffer.getHeight() == height) {
            image = this.backBuffer;
        }
        else {
            image = new BufferedImage(width, height, 2);
        }
        this.nativeComponent.paintNativeComponent(image, rectangles);
        synchronized (this.backBufferLock) {
            if (this.backBuffer != null && this.backBuffer != image) {
                synchronized (this.backBuffer) {
                    final Graphics g = image.getGraphics();
                    g.drawImage(this.backBuffer, 0, 0, null);
                    g.dispose();
                }
                this.backBuffer.flush();
            }
            this.backBuffer = image;
        }
        if (this.paintingComponent != this.nativeComponent.getNativeComponent()) {
            final Rectangle bounds = UIUtils.getBounds(rectangles);
            this.paintingComponent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }
    
    public boolean hasBackBuffer() {
        synchronized (this.backBufferLock) {
            return this.backBuffer != null;
        }
    }
    
    public void destroyBackBuffer() {
        synchronized (this.backBufferLock) {
            if (this.backBuffer != null) {
                this.backBuffer.flush();
            }
            this.backBuffer = null;
        }
    }
    
    public void paintBackBuffer(final Graphics g) {
        synchronized (this.backBufferLock) {
            if (this.backBuffer != null) {
                synchronized (this.backBuffer) {
                    g.drawImage(this.backBuffer, 0, 0, this.paintingComponent);
                }
            }
        }
    }
}
