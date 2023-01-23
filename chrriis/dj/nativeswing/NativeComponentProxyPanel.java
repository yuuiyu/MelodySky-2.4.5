//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import chrriis.dj.nativeswing.common.*;
import chrriis.dj.nativeswing.jna.platform.*;
import java.awt.geom.*;

class NativeComponentProxyPanel extends NativeComponentProxy
{
    private static final boolean IS_DEBUGGING_SHAPE;
    private boolean isProxiedFiliation;
    private AWTEventListener shapeAdjustmentEventListener;
    private boolean isDestructionOnFinalization;
    private boolean isVisibilityConstrained;
    private HierarchyBoundsListener hierarchyBoundsListener;
    private MouseAdapter mouseListener;
    private volatile boolean isInvoking;
    private Rectangle[] lastArea;
    private EmbeddedPanel embeddedPanel;
    private HierarchyListener hierarchyListener;
    
    NativeComponentProxyPanel(final NativeComponentWrapper nativeComponentWrapper, final boolean isVisibilityConstrained, final boolean isDestructionOnFinalization, final boolean isProxiedFiliation) {
        super(nativeComponentWrapper);
        this.hierarchyBoundsListener = (HierarchyBoundsListener)new lIllII(this);
        this.mouseListener = (MouseAdapter)new llllI(this);
        this.lastArea = new Rectangle[] { new Rectangle(this.getSize()) };
        this.isDestructionOnFinalization = isDestructionOnFinalization;
        this.isVisibilityConstrained = isVisibilityConstrained;
        this.hierarchyListener = (HierarchyListener)new lllll(this);
        if (isVisibilityConstrained) {
            this.shapeAdjustmentEventListener = (AWTEventListener)new lIlIll(this);
        }
        this.isProxiedFiliation = isProxiedFiliation;
    }
    
    private void adjustEmbeddedPanelBounds() {
        if (this.embeddedPanel == null) {
            return;
        }
        if (!this.isVisibilityConstrained) {
            final boolean isShowing = this.isShowing();
            if (isShowing != this.embeddedPanel.isVisible()) {
                this.embeddedPanel.setVisible(isShowing);
            }
        }
        final Container parent = this.embeddedPanel.getParent();
        if (parent != null) {
            final Point location = SwingUtilities.convertPoint((Component)this, new Point(0, 0), parent);
            final Dimension size = this.getSize();
            final Rectangle bounds = new Rectangle(location.x, location.y, size.width, size.height);
            final Rectangle clip = this.embeddedPanel.getRectangularClip();
            if (clip != null) {
                final Rectangle rectangle = bounds;
                rectangle.x += clip.x;
                final Rectangle rectangle2 = bounds;
                rectangle2.y += clip.y;
                bounds.width = clip.width;
                bounds.height = clip.height;
            }
            if (!this.embeddedPanel.getBounds().equals(bounds)) {
                this.embeddedPanel.setBounds(bounds);
                this.embeddedPanel.invalidate();
                this.embeddedPanel.validate();
                this.embeddedPanel.repaint();
                if (this.isVisibilityConstrained) {
                    this.adjustEmbeddedPanelShape();
                }
            }
        }
    }
    
    private void adjustEmbeddedPanelShape() {
        class lllIl implements Runnable
        {
            final /* synthetic */ NativeComponentProxyPanel this$0;
            
            lllIl(final NativeComponentProxyPanel this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                this.this$0.isInvoking = false;
                this.this$0.adjustEmbeddedPanelShape_();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.isInvoking:Z
        //     4: ifeq            8
        //     7: return         
        //     8: aload_0         /* this */
        //     9: iconst_1       
        //    10: putfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.isInvoking:Z
        //    13: new             Lchrriis/dj/nativeswing/lllIl;
        //    16: dup            
        //    17: aload_0         /* this */
        //    18: invokespecial   chrriis/dj/nativeswing/lllIl.<init>:(Lchrriis/dj/nativeswing/NativeComponentProxyPanel;)V
        //    21: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    24: return         
        //    StackMapTable: 00 01 08
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
    
    private void adjustEmbeddedPanelShape_() {
        if (this.embeddedPanel == null) {
            return;
        }
        final Rectangle[] rectangles = this.computePeerShapeArea();
        if (Arrays.equals(this.lastArea, rectangles)) {
            this.embeddedPanel.nativeComponentWrapper.getNativeComponent().repaint();
            return;
        }
        this.lastArea = rectangles;
        if (rectangles.length == 0) {
            this.embeddedPanel.setVisible(false);
        }
        else {
            if (!this.embeddedPanel.isVisible()) {
                this.embeddedPanel.setVisible(true);
            }
            this.embeddedPanel.applyShape(rectangles);
        }
    }
    
    private Rectangle[] computePeerShapeArea() {
        class lIIIII implements Filter<Component>
        {
            final /* synthetic */ NativeComponentProxyPanel this$0;
            
            lIIIII(final NativeComponentProxyPanel this$0) {
                this.this$0 = this$0;
            }
            
            public Filter.Acceptance accept(final Component c) {
                if (c instanceof EmbeddedPanel) {
                    return Filter.Acceptance.NO;
                }
                final UIUtils.TransparencyType transparency = UIUtils.getComponentTransparency(c);
                switch (lIIIll.$SwitchMap$chrriis$dj$nativeswing$common$UIUtils$TransparencyType[transparency.ordinal()]) {
                    case 1: {
                        return Filter.Acceptance.TEST_CHILDREN;
                    }
                    case 2: {
                        return Filter.Acceptance.NO;
                    }
                    default: {
                        if (NativeComponentProxyPanel.IS_DEBUGGING_SHAPE) {
                            final Rectangle intersectionRectangle = SwingUtilities.convertRectangle(c, new Rectangle(c.getSize()), (Component)this.this$0).intersection(new Rectangle(this.this$0.getSize()));
                            if (!intersectionRectangle.isEmpty()) {
                                System.err.println("  -> Subtracting [" + intersectionRectangle.x + "," + intersectionRectangle.y + "," + intersectionRectangle.width + "x" + intersectionRectangle.height + "] " + c);
                            }
                        }
                        return Filter.Acceptance.YES;
                    }
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifeq            61
        //     6: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //     9: new             Ljava/lang/StringBuilder;
        //    12: dup            
        //    13: invokespecial   java/lang/StringBuilder.<init>:()V
        //    16: ldc             "Computing shape: ["
        //    18: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    21: aload_0         /* this */
        //    22: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.getWidth:()I
        //    25: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    28: ldc             "x"
        //    30: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    33: aload_0         /* this */
        //    34: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.getHeight:()I
        //    37: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    40: ldc             "] "
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    45: aload_0         /* this */
        //    46: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.nativeComponentWrapper:Lchrriis/dj/nativeswing/NativeComponentWrapper;
        //    49: invokevirtual   chrriis/dj/nativeswing/NativeComponentWrapper.getComponentDescription:()Ljava/lang/String;
        //    52: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    55: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    58: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //    61: aload_0         /* this */
        //    62: new             Lchrriis/dj/nativeswing/lIIIII;
        //    65: dup            
        //    66: aload_0         /* this */
        //    67: invokespecial   chrriis/dj/nativeswing/lIIIII.<init>:(Lchrriis/dj/nativeswing/NativeComponentProxyPanel;)V
        //    70: invokestatic    chrriis/dj/nativeswing/common/UIUtils.getComponentVisibleArea:(Ljava/awt/Component;Lchrriis/dj/nativeswing/common/Filter;)[Ljava/awt/Rectangle;
        //    73: astore_1        /* shape */
        //    74: aload_1         /* shape */
        //    75: areturn        
        //    StackMapTable: 00 01 3D
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
    
    public void paint(final Graphics g) {
        super.paint(g);
        for (final NativeComponentWrapper ncw : NativeSwing.getNativeComponentWrappers()) {
            final NativeComponentProxy nativeComponentProxy = ncw.getNativeComponentProxy();
            if (nativeComponentProxy instanceof NativeComponentProxyPanel && ((NativeComponentProxyPanel)nativeComponentProxy).isVisibilityConstrained) {
                ((NativeComponentProxyPanel)nativeComponentProxy).adjustEmbeddedPanelShape();
            }
        }
    }
    
    public void reshape(final int x, final int y, final int w, final int h) {
        if (x == this.getX() && y == this.getY() && w == this.getWidth() && h == this.getHeight()) {
            return;
        }
        super.reshape(x, y, w, h);
        this.adjustEmbeddedPanelBounds();
    }
    
    public void addNotify() {
        class lIlIIl implements Runnable
        {
            final /* synthetic */ NativeComponentProxyPanel this$0;
            
            lIlIIl(final NativeComponentProxyPanel this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                this.this$0.addHierarchyBoundsListener(this.this$0.hierarchyBoundsListener);
                this.this$0.adjustEmbeddedPanelBounds();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   chrriis/dj/nativeswing/NativeComponentProxy.addNotify:()V
        //     4: aload_0         /* this */
        //     5: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.hierarchyListener:Ljava/awt/event/HierarchyListener;
        //     8: ifnull          19
        //    11: aload_0         /* this */
        //    12: aload_0         /* this */
        //    13: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.hierarchyListener:Ljava/awt/event/HierarchyListener;
        //    16: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.addHierarchyListener:(Ljava/awt/event/HierarchyListener;)V
        //    19: aload_0         /* this */
        //    20: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.shapeAdjustmentEventListener:Ljava/awt/event/AWTEventListener;
        //    23: ifnull          39
        //    26: invokestatic    java/awt/Toolkit.getDefaultToolkit:()Ljava/awt/Toolkit;
        //    29: aload_0         /* this */
        //    30: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.shapeAdjustmentEventListener:Ljava/awt/event/AWTEventListener;
        //    33: ldc2_w          3
        //    36: invokevirtual   java/awt/Toolkit.addAWTEventListener:(Ljava/awt/event/AWTEventListener;J)V
        //    39: aconst_null    
        //    40: astore_1        /* layeredPane */
        //    41: aload_0         /* this */
        //    42: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.isProxiedFiliation:Z
        //    45: ifeq            53
        //    48: aload_0         /* this */
        //    49: invokestatic    chrriis/dj/nativeswing/NativeComponentProxyPanel.findLayeredPane:(Ljava/awt/Component;)Ljavax/swing/JLayeredPane;
        //    52: astore_1        /* layeredPane */
        //    53: aload_0         /* this */
        //    54: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //    57: ifnull          64
        //    60: iconst_1       
        //    61: goto            65
        //    64: iconst_0       
        //    65: istore_2        /* isEmbeddedPanelCreated */
        //    66: iload_2         /* isEmbeddedPanelCreated */
        //    67: ifeq            227
        //    70: aload_0         /* this */
        //    71: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.isProxiedFiliation:Z
        //    74: ifeq            263
        //    77: aload_0         /* this */
        //    78: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //    81: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel.getParent:()Ljava/awt/Container;
        //    84: ifnonnull       115
        //    87: aload_1         /* layeredPane */
        //    88: aload_0         /* this */
        //    89: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //    92: ldc_w           -2147483648
        //    95: invokevirtual   javax/swing/JLayeredPane.setLayer:(Ljava/awt/Component;I)V
        //    98: aload_1         /* layeredPane */
        //    99: aload_0         /* this */
        //   100: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   103: invokevirtual   javax/swing/JLayeredPane.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   106: pop            
        //   107: aload_1         /* layeredPane */
        //   108: invokevirtual   javax/swing/JLayeredPane.invalidate:()V
        //   111: aload_1         /* layeredPane */
        //   112: invokevirtual   javax/swing/JLayeredPane.validate:()V
        //   115: aload_0         /* this */
        //   116: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   119: invokestatic    chrriis/dj/nativeswing/NativeComponentProxyPanel.findLayeredPane:(Ljava/awt/Component;)Ljavax/swing/JLayeredPane;
        //   122: astore_3        /* oldLayeredPane */
        //   123: aload_1         /* layeredPane */
        //   124: aload_3         /* oldLayeredPane */
        //   125: if_acmpeq       224
        //   128: aload_0         /* this */
        //   129: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   132: iconst_1       
        //   133: invokestatic    chrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel.access$702:(Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;Z)Z
        //   136: pop            
        //   137: aload_0         /* this */
        //   138: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.nativeComponentWrapper:Lchrriis/dj/nativeswing/NativeComponentWrapper;
        //   141: invokevirtual   chrriis/dj/nativeswing/NativeComponentWrapper.storeInHiddenParent:()V
        //   144: aload_0         /* this */
        //   145: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   148: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel.getParent:()Ljava/awt/Container;
        //   151: astore          oldParent
        //   153: aload           oldParent
        //   155: aload_0         /* this */
        //   156: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   159: invokevirtual   java/awt/Container.remove:(Ljava/awt/Component;)V
        //   162: aload           oldParent
        //   164: invokestatic    chrriis/dj/nativeswing/common/UIUtils.revalidate:(Ljava/awt/Component;)V
        //   167: aload           oldParent
        //   169: invokevirtual   java/awt/Container.repaint:()V
        //   172: aload_1         /* layeredPane */
        //   173: aload_0         /* this */
        //   174: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   177: ldc_w           -2147483648
        //   180: invokevirtual   javax/swing/JLayeredPane.setLayer:(Ljava/awt/Component;I)V
        //   183: aload_1         /* layeredPane */
        //   184: aload_0         /* this */
        //   185: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   188: invokevirtual   javax/swing/JLayeredPane.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   191: pop            
        //   192: aload_0         /* this */
        //   193: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.nativeComponentWrapper:Lchrriis/dj/nativeswing/NativeComponentWrapper;
        //   196: invokevirtual   chrriis/dj/nativeswing/NativeComponentWrapper.restoreFromHiddenParent:()V
        //   199: aload_0         /* this */
        //   200: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   203: iconst_0       
        //   204: invokestatic    chrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel.access$702:(Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;Z)Z
        //   207: pop            
        //   208: aload_1         /* layeredPane */
        //   209: invokestatic    chrriis/dj/nativeswing/common/UIUtils.revalidate:(Ljava/awt/Component;)V
        //   212: aload_1         /* layeredPane */
        //   213: invokevirtual   javax/swing/JLayeredPane.repaint:()V
        //   216: aload_0         /* this */
        //   217: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.revalidate:()V
        //   220: aload_0         /* this */
        //   221: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.repaint:()V
        //   224: goto            263
        //   227: aload_0         /* this */
        //   228: new             Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   231: dup            
        //   232: aload_0         /* this */
        //   233: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.nativeComponentWrapper:Lchrriis/dj/nativeswing/NativeComponentWrapper;
        //   236: aload_0         /* this */
        //   237: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.isDestructionOnFinalization:Z
        //   240: invokespecial   chrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel.<init>:(Lchrriis/dj/nativeswing/NativeComponentWrapper;Z)V
        //   243: putfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   246: aload_0         /* this */
        //   247: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   250: aload_0         /* this */
        //   251: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.nativeComponentWrapper:Lchrriis/dj/nativeswing/NativeComponentWrapper;
        //   254: invokevirtual   chrriis/dj/nativeswing/NativeComponentWrapper.getNativeComponent:()Ljava/awt/Component;
        //   257: ldc_w           "Center"
        //   260: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel.add:(Ljava/awt/Component;Ljava/lang/Object;)V
        //   263: aload_0         /* this */
        //   264: aconst_null    
        //   265: putfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.lastArea:[Ljava/awt/Rectangle;
        //   268: aload_0         /* this */
        //   269: invokespecial   chrriis/dj/nativeswing/NativeComponentProxyPanel.adjustEmbeddedPanelBounds:()V
        //   272: new             Lchrriis/dj/nativeswing/lIlIIl;
        //   275: dup            
        //   276: aload_0         /* this */
        //   277: invokespecial   chrriis/dj/nativeswing/lIlIIl.<init>:(Lchrriis/dj/nativeswing/NativeComponentProxyPanel;)V
        //   280: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //   283: aload_0         /* this */
        //   284: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.nativeComponentWrapper:Lchrriis/dj/nativeswing/NativeComponentWrapper;
        //   287: invokevirtual   chrriis/dj/nativeswing/NativeComponentWrapper.getNativeComponent:()Ljava/awt/Component;
        //   290: aload_0         /* this */
        //   291: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.mouseListener:Ljava/awt/event/MouseAdapter;
        //   294: invokevirtual   java/awt/Component.addMouseListener:(Ljava/awt/event/MouseListener;)V
        //   297: iload_2         /* isEmbeddedPanelCreated */
        //   298: ifne            356
        //   301: aload_0         /* this */
        //   302: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.isProxiedFiliation:Z
        //   305: ifeq            339
        //   308: aload_1         /* layeredPane */
        //   309: aload_0         /* this */
        //   310: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   313: ldc_w           -2147483648
        //   316: invokevirtual   javax/swing/JLayeredPane.setLayer:(Ljava/awt/Component;I)V
        //   319: aload_1         /* layeredPane */
        //   320: aload_0         /* this */
        //   321: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   324: invokevirtual   javax/swing/JLayeredPane.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   327: pop            
        //   328: aload_1         /* layeredPane */
        //   329: invokestatic    chrriis/dj/nativeswing/common/UIUtils.revalidate:(Ljava/awt/Component;)V
        //   332: aload_1         /* layeredPane */
        //   333: invokevirtual   javax/swing/JLayeredPane.repaint:()V
        //   336: goto            356
        //   339: aload_0         /* this */
        //   340: aload_0         /* this */
        //   341: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.embeddedPanel:Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;
        //   344: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   347: pop            
        //   348: aload_0         /* this */
        //   349: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.revalidate:()V
        //   352: aload_0         /* this */
        //   353: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.repaint:()V
        //   356: return         
        //    StackMapTable: 00 0B 13 13 FC 00 0D 07 01 45 0A 40 01 FC 00 31 01 FC 00 6C 07 01 45 FA 00 02 23 FB 00 4B 10
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
    
    public void removeNotify() {
        class lIIlll implements Runnable
        {
            final /* synthetic */ NativeComponentProxyPanel this$0;
            
            lIIlll(final NativeComponentProxyPanel this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                this.this$0.removeHierarchyBoundsListener(this.this$0.hierarchyBoundsListener);
                this.this$0.adjustEmbeddedPanelBounds();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   chrriis/dj/nativeswing/NativeComponentProxy.removeNotify:()V
        //     4: aload_0         /* this */
        //     5: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.hierarchyListener:Ljava/awt/event/HierarchyListener;
        //     8: ifnull          19
        //    11: aload_0         /* this */
        //    12: aload_0         /* this */
        //    13: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.hierarchyListener:Ljava/awt/event/HierarchyListener;
        //    16: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.removeHierarchyListener:(Ljava/awt/event/HierarchyListener;)V
        //    19: aload_0         /* this */
        //    20: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.shapeAdjustmentEventListener:Ljava/awt/event/AWTEventListener;
        //    23: ifnull          36
        //    26: invokestatic    java/awt/Toolkit.getDefaultToolkit:()Ljava/awt/Toolkit;
        //    29: aload_0         /* this */
        //    30: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.shapeAdjustmentEventListener:Ljava/awt/event/AWTEventListener;
        //    33: invokevirtual   java/awt/Toolkit.removeAWTEventListener:(Ljava/awt/event/AWTEventListener;)V
        //    36: aload_0         /* this */
        //    37: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.isDestructionOnFinalization:Z
        //    40: ifeq            87
        //    43: new             Lchrriis/dj/nativeswing/lIIlll;
        //    46: dup            
        //    47: aload_0         /* this */
        //    48: invokespecial   chrriis/dj/nativeswing/lIIlll.<init>:(Lchrriis/dj/nativeswing/NativeComponentProxyPanel;)V
        //    51: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    54: aload_0         /* this */
        //    55: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.nativeComponentWrapper:Lchrriis/dj/nativeswing/NativeComponentWrapper;
        //    58: invokevirtual   chrriis/dj/nativeswing/NativeComponentWrapper.getNativeComponent:()Ljava/awt/Component;
        //    61: aload_0         /* this */
        //    62: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.mouseListener:Ljava/awt/event/MouseAdapter;
        //    65: invokevirtual   java/awt/Component.removeMouseListener:(Ljava/awt/event/MouseListener;)V
        //    68: aload_0         /* this */
        //    69: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel.isVisibilityConstrained:Z
        //    72: ifeq            82
        //    75: aload_0         /* this */
        //    76: invokespecial   chrriis/dj/nativeswing/NativeComponentProxyPanel.adjustEmbeddedPanelShape:()V
        //    79: goto            86
        //    82: aload_0         /* this */
        //    83: invokespecial   chrriis/dj/nativeswing/NativeComponentProxyPanel.adjustEmbeddedPanelBounds:()V
        //    86: return         
        //    87: aload_0         /* this */
        //    88: invokevirtual   chrriis/dj/nativeswing/NativeComponentProxyPanel.dispose:()V
        //    91: return         
        //    StackMapTable: 00 05 13 10 2D 03 00
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
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
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
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.embeddedPanel != null) {
            SwingUtilities.invokeLater((Runnable)new lllII(this));
        }
    }
    
    public void dispose() {
        if (this.embeddedPanel == null) {
            return;
        }
        final EmbeddedPanel panel = this.embeddedPanel;
        this.embeddedPanel = null;
        final Container parent = panel.getParent();
        if (parent != null) {
            panel.isRemovingFromParent = true;
            parent.remove(panel);
            parent.invalidate();
            parent.validate();
            parent.repaint();
        }
    }
    
    public Dimension getPreferredSize() {
        if (this.embeddedPanel != null) {
            return this.embeddedPanel.getPreferredSize();
        }
        return super.getPreferredSize();
    }
    
    static {
        IS_DEBUGGING_SHAPE = Boolean.parseBoolean(NSSystemProperty.COMPONENTS_DEBUG_PRINTSHAPECOMPUTING.get());
    }
    
    private static class EmbeddedPanel extends Panel implements NativeComponentWrapper.NativeComponentHolder
    {
        private NativeComponentWrapper nativeComponentWrapper;
        private boolean isDestructionOnFinalization;
        private boolean isCrossWindowReparenting;
        private boolean isHiddenReparenting;
        private boolean isRemovingFromParent;
        private Rectangle clip;
        private static final boolean RESTRICT_SHAPE_TO_SINGLE_RECTANGLE;
        
        public EmbeddedPanel(final NativeComponentWrapper nativeComponentWrapper, final boolean isDestructionOnFinalization) {
            super((LayoutManager)new ClipLayout());
            this.nativeComponentWrapper = nativeComponentWrapper;
            this.isDestructionOnFinalization = isDestructionOnFinalization;
            this.enableEvents(131072L);
        }
        
        @Override
        public boolean contains(final int x, final int y) {
            return false;
        }
        
        @Override
        public boolean contains(final Point p) {
            return false;
        }
        
        @Override
        public void removeNotify() {
            if (this.isRemovingFromParent) {
                super.removeNotify();
                return;
            }
            if (this.isDestructionOnFinalization && !this.isCrossWindowReparenting) {
                try {
                    this.nativeComponentWrapper.storeInHiddenParent();
                    this.isHiddenReparenting = true;
                }
                catch (Exception ex) {}
                super.removeNotify();
                this.isRemovingFromParent = true;
                final Container parent = this.getParent();
                if (parent != null) {
                    parent.remove(this);
                    parent.invalidate();
                    parent.validate();
                }
                this.isRemovingFromParent = false;
            }
            else {
                super.removeNotify();
            }
        }
        
        @Override
        public void addNotify() {
            super.addNotify();
            if (this.isHiddenReparenting) {
                this.isHiddenReparenting = false;
                this.nativeComponentWrapper.restoreFromHiddenParent();
            }
        }
        
        @Override
        protected void finalize() throws Throwable {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        chrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel.isHiddenReparenting:Z
            //     4: ifeq            18
            //     7: new             Lchrriis/dj/nativeswing/lIIlIl;
            //    10: dup            
            //    11: aload_0         /* this */
            //    12: invokespecial   chrriis/dj/nativeswing/lIIlIl.<init>:(Lchrriis/dj/nativeswing/NativeComponentProxyPanel$EmbeddedPanel;)V
            //    15: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
            //    18: return         
            //    Exceptions:
            //  throws java.lang.Throwable
            //    StackMapTable: 00 01 12
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
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        public void applyShape(Rectangle[] rectangles) {
            if (!Utils.IS_MAC && !EmbeddedPanel.RESTRICT_SHAPE_TO_SINGLE_RECTANGLE) {
                if (rectangles.length > 0) {
                    final Point2D.Double scaledFactor = UIUtils.getScaledFactor((Component)this);
                    if (scaledFactor.x != 1.0 || scaledFactor.y != 1.0) {
                        final Rectangle[] newRectangles = new Rectangle[rectangles.length];
                        for (int i = 0; i < rectangles.length; ++i) {
                            newRectangles[i] = new Rectangle((int)(rectangles[i].x * scaledFactor.x), (int)(rectangles[i].y * scaledFactor.y), (int)(rectangles[i].width * scaledFactor.x), (int)(rectangles[i].height * scaledFactor.y));
                        }
                        rectangles = newRectangles;
                    }
                }
                WindowUtils.setComponentMask((Component)this, rectangles);
                this.nativeComponentWrapper.getNativeComponent().repaint();
                return;
            }
            Rectangle clip;
            if (rectangles.length == 0) {
                clip = null;
            }
            else {
                clip = new Rectangle(rectangles[0]);
                if (rectangles.length > 1) {
                    System.err.println("Non-rectangular clip detected on a system that does not support this feature.");
                    for (int j = 1; j < rectangles.length; ++j) {
                        clip = clip.union(rectangles[j]);
                    }
                }
            }
            if (Utils.equals((Object)this.clip, (Object)clip)) {
                return;
            }
            final int oldOffsetX = (this.clip == null) ? 0 : this.clip.x;
            final int oldOffsetY = (this.clip == null) ? 0 : this.clip.y;
            this.clip = clip;
            final int offsetX = (clip == null) ? 0 : clip.x;
            final int offsetY = (clip == null) ? 0 : clip.y;
            final NativeComponentProxy nativeComponentProxy = this.nativeComponentWrapper.getNativeComponentProxy();
            if (nativeComponentProxy != null) {
                ((ClipLayout)this.getLayout()).setClip((clip == null) ? null : new Rectangle(-clip.x, -clip.y, nativeComponentProxy.getWidth(), nativeComponentProxy.getHeight()));
            }
            final Container parent = this.getParent();
            if (parent != null) {
                final LayoutManager layout = parent.getLayout();
                if (layout instanceof ClipLayout) {
                    ((ClipLayout)layout).setClip(clip);
                }
                else {
                    final int diffX = offsetX - oldOffsetX;
                    final int diffY = offsetY - oldOffsetY;
                    this.setBounds(this.getX() + diffX, this.getY() + diffY, this.getWidth() - diffX, this.getHeight() - diffY);
                }
                this.doLayout();
                UIUtils.revalidate((Component)parent);
            }
        }
        
        public Rectangle getRectangularClip() {
            return this.clip;
        }
        
        static {
            RESTRICT_SHAPE_TO_SINGLE_RECTANGLE = Boolean.parseBoolean(NSSystemProperty.COMPONENTS_FORCESINGLERECTANGLESHAPES.get());
        }
    }
}
