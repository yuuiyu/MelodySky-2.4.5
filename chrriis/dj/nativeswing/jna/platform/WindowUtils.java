//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.jna.platform;

import java.awt.peer.*;
import java.lang.reflect.*;
import java.awt.image.*;
import com.sun.jna.platform.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.*;
import com.sun.jna.platform.unix.*;
import java.awt.*;
import java.awt.geom.*;
import com.sun.jna.*;

public class WindowUtils
{
    private static final String TRANSPARENT_OLD_BG = "transparent-old-bg";
    private static final String TRANSPARENT_OLD_OPAQUE = "transparent-old-opaque";
    private static final String TRANSPARENT_ALPHA = "transparent-alpha";
    public static final Shape MASK_NONE;
    
    private static NativeWindowUtils getInstance() {
        return Holder.INSTANCE;
    }
    
    public static void setWindowMask(final Window w, final Shape mask) {
        getInstance().setWindowMask(w, mask);
    }
    
    public static void setComponentMask(final Component c, final Shape mask) {
        getInstance().setWindowMask(c, mask);
    }
    
    public static void setWindowMask(final Window w, final Icon mask) {
        getInstance().setWindowMask(w, mask);
    }
    
    public static boolean isWindowAlphaSupported() {
        return getInstance().isWindowAlphaSupported();
    }
    
    public static GraphicsConfiguration getAlphaCompatibleGraphicsConfiguration() {
        return getInstance().getAlphaCompatibleGraphicsConfiguration();
    }
    
    public static void setWindowAlpha(final Window w, final float alpha) {
        getInstance().setWindowAlpha(w, Math.max(0.0f, Math.min(alpha, 1.0f)));
    }
    
    public static void setWindowTransparent(final Window w, final boolean transparent) {
        getInstance().setWindowTransparent(w, transparent);
    }
    
    public static void setComponentMask(final Component w, final Rectangle[] rectangles) {
        getInstance().setWindowMask(w, rectangles);
    }
    
    public static void setWindowMask(final Window w, final Rectangle[] rectangles) {
        getInstance().setWindowMask(w, rectangles);
    }
    
    public static void setWindowClickThrough(final Window w, final boolean isClickThrough) {
        getInstance().setWindowClickThrough(w, isClickThrough);
    }
    
    @Deprecated
    public static ComponentPeer getPeer(final Component c) {
        try {
            final Field peerField = Component.class.getDeclaredField("peer");
            peerField.setAccessible(true);
            return (ComponentPeer)peerField.get(c);
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex2) {
            final Exception ex;
            final Exception e = ex;
            throw new RuntimeException(e);
        }
    }
    
    static {
        MASK_NONE = null;
    }
    
    private static class HeavyweightForcer extends Window
    {
        private final boolean packed;
        
        public HeavyweightForcer(final Window parent) {
            super(parent);
            this.pack();
            this.packed = true;
        }
        
        @Override
        public boolean isVisible() {
            return this.packed;
        }
        
        @Override
        public Rectangle getBounds() {
            return this.getOwner().getBounds();
        }
    }
    
    protected static class RepaintTrigger extends JComponent
    {
        private final Listener listener;
        private final JComponent content;
        private Rectangle dirty;
        
        public RepaintTrigger(final JComponent content) {
            this.listener = this.createListener();
            this.content = content;
        }
        
        @Override
        public void addNotify() {
            super.addNotify();
            final Window w = SwingUtilities.getWindowAncestor(this);
            this.setSize(this.getParent().getSize());
            w.addComponentListener(this.listener);
            w.addWindowListener(this.listener);
            Toolkit.getDefaultToolkit().addAWTEventListener(this.listener, 48L);
        }
        
        @Override
        public void removeNotify() {
            Toolkit.getDefaultToolkit().removeAWTEventListener(this.listener);
            final Window w = SwingUtilities.getWindowAncestor(this);
            w.removeComponentListener(this.listener);
            w.removeWindowListener(this.listener);
            super.removeNotify();
        }
        
        @Override
        protected void paintComponent(final Graphics g) {
            final Rectangle bounds = g.getClipBounds();
            if (this.dirty == null || !this.dirty.contains(bounds)) {
                if (this.dirty == null) {
                    this.dirty = bounds;
                }
                else {
                    this.dirty = this.dirty.union(bounds);
                }
                this.content.repaint(this.dirty);
            }
            else {
                this.dirty = null;
            }
        }
        
        protected Listener createListener() {
            return new Listener();
        }
        
        protected class Listener extends WindowAdapter implements ComponentListener, HierarchyListener, AWTEventListener
        {
            @Override
            public void windowOpened(final WindowEvent e) {
                RepaintTrigger.this.repaint();
            }
            
            @Override
            public void componentHidden(final ComponentEvent e) {
            }
            
            @Override
            public void componentMoved(final ComponentEvent e) {
            }
            
            @Override
            public void componentResized(final ComponentEvent e) {
                RepaintTrigger.this.setSize(RepaintTrigger.this.getParent().getSize());
                RepaintTrigger.this.repaint();
            }
            
            @Override
            public void componentShown(final ComponentEvent e) {
                RepaintTrigger.this.repaint();
            }
            
            @Override
            public void hierarchyChanged(final HierarchyEvent e) {
                RepaintTrigger.this.repaint();
            }
            
            @Override
            public void eventDispatched(final AWTEvent e) {
                if (e instanceof MouseEvent) {
                    final Component src = ((MouseEvent)e).getComponent();
                    if (src != null && SwingUtilities.isDescendingFrom(src, RepaintTrigger.this.content)) {
                        final MouseEvent me = SwingUtilities.convertMouseEvent(src, (MouseEvent)e, RepaintTrigger.this.content);
                        final Component c = SwingUtilities.getDeepestComponentAt(RepaintTrigger.this.content, me.getX(), me.getY());
                        if (c != null) {
                            RepaintTrigger.this.setCursor(c.getCursor());
                        }
                    }
                }
            }
        }
    }
    
    public abstract static class NativeWindowUtils
    {
        protected Window getWindow(final Component c) {
            return (Window)((c instanceof Window) ? c : SwingUtilities.getWindowAncestor(c));
        }
        
        protected void whenDisplayable(final Component w, final Runnable action) {
            if (w.isDisplayable() && (!Holder.requiresVisible || w.isVisible())) {
                action.run();
            }
            else if (Holder.requiresVisible) {
                this.getWindow(w).addWindowListener((WindowListener)new lllII(this, action));
            }
            else {
                w.addHierarchyListener((HierarchyListener)new llIII(this, action));
            }
        }
        
        protected Raster toRaster(final Shape mask) {
            Raster raster = null;
            if (mask != WindowUtils.MASK_NONE) {
                final Rectangle bounds = mask.getBounds();
                if (bounds.width > 0 && bounds.height > 0) {
                    final BufferedImage clip = new BufferedImage(bounds.x + bounds.width, bounds.y + bounds.height, 12);
                    final Graphics2D g = clip.createGraphics();
                    g.setColor(Color.black);
                    g.fillRect(0, 0, bounds.x + bounds.width, bounds.y + bounds.height);
                    g.setColor(Color.white);
                    g.fill(mask);
                    raster = clip.getRaster();
                }
            }
            return raster;
        }
        
        protected Raster toRaster(final Component c, final Icon mask) {
            Raster raster = null;
            if (mask != null) {
                final Rectangle bounds = new Rectangle(0, 0, mask.getIconWidth(), mask.getIconHeight());
                final BufferedImage clip = new BufferedImage(bounds.width, bounds.height, 2);
                final Graphics2D g = clip.createGraphics();
                g.setComposite(AlphaComposite.Clear);
                g.fillRect(0, 0, bounds.width, bounds.height);
                g.setComposite(AlphaComposite.SrcOver);
                mask.paintIcon(c, g, 0, 0);
                raster = clip.getAlphaRaster();
            }
            return raster;
        }
        
        protected Shape toShape(final Raster raster) {
            final Area area = new Area(new Rectangle(0, 0, 0, 0));
            RasterRangesUtils.outputOccupiedRanges(raster, (RasterRangesUtils.RangesOutput)new llllI(this, area));
            return area;
        }
        
        public void setWindowAlpha(final Window w, final float alpha) {
        }
        
        public boolean isWindowAlphaSupported() {
            return false;
        }
        
        public GraphicsConfiguration getAlphaCompatibleGraphicsConfiguration() {
            final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            final GraphicsDevice dev = env.getDefaultScreenDevice();
            return dev.getDefaultConfiguration();
        }
        
        public void setWindowTransparent(final Window w, final boolean transparent) {
        }
        
        public void setWindowClickThrough(final Window w, final boolean isClickThrough) {
        }
        
        protected void setDoubleBuffered(final Component root, final boolean buffered) {
            if (root instanceof JComponent) {
                ((JComponent)root).setDoubleBuffered(buffered);
            }
            if (root instanceof JRootPane && buffered) {
                ((JRootPane)root).setDoubleBuffered(true);
            }
            else if (root instanceof Container) {
                final Component[] kids = ((Container)root).getComponents();
                for (int i = 0; i < kids.length; ++i) {
                    this.setDoubleBuffered(kids[i], buffered);
                }
            }
        }
        
        protected void setLayersTransparent(final Window w, final boolean transparent) {
            Color bg = transparent ? new Color(0, 0, 0, 0) : null;
            if (w instanceof RootPaneContainer) {
                final RootPaneContainer rpc = (RootPaneContainer)w;
                final JRootPane root = rpc.getRootPane();
                final JLayeredPane lp = root.getLayeredPane();
                final Container c = root.getContentPane();
                final JComponent content = (c instanceof JComponent) ? ((JComponent)c) : null;
                if (transparent) {
                    lp.putClientProperty("transparent-old-opaque", lp.isOpaque());
                    lp.setOpaque(false);
                    root.putClientProperty("transparent-old-opaque", root.isOpaque());
                    root.setOpaque(false);
                    if (content != null) {
                        content.putClientProperty("transparent-old-opaque", content.isOpaque());
                        content.setOpaque(false);
                    }
                    root.putClientProperty("transparent-old-bg", root.getParent().getBackground());
                }
                else {
                    lp.setOpaque(Boolean.TRUE.equals(lp.getClientProperty("transparent-old-opaque")));
                    lp.putClientProperty("transparent-old-opaque", null);
                    root.setOpaque(Boolean.TRUE.equals(root.getClientProperty("transparent-old-opaque")));
                    root.putClientProperty("transparent-old-opaque", null);
                    if (content != null) {
                        content.setOpaque(Boolean.TRUE.equals(content.getClientProperty("transparent-old-opaque")));
                        content.putClientProperty("transparent-old-opaque", null);
                    }
                    bg = (Color)root.getClientProperty("transparent-old-bg");
                    root.putClientProperty("transparent-old-bg", null);
                }
            }
            w.setBackground(bg);
        }
        
        protected void setMask(final Component c, final Raster raster) {
            throw new UnsupportedOperationException("Window masking is not available");
        }
        
        protected void setWindowMask(final Component w, final Raster raster) {
            if (w.isLightweight()) {
                throw new IllegalArgumentException("Component must be heavyweight: " + w);
            }
            this.setMask(w, raster);
        }
        
        public void setWindowMask(final Component w, final Shape mask) {
            this.setWindowMask(w, this.toRaster(mask));
        }
        
        public void setWindowMask(final Component w, final Icon mask) {
            this.setWindowMask(w, this.toRaster(w, mask));
        }
        
        protected void setForceHeavyweightPopups(final Window w, final boolean force) {
            if (!(w instanceof HeavyweightForcer)) {
                final Window[] owned = w.getOwnedWindows();
                for (int i = 0; i < owned.length; ++i) {
                    if (owned[i] instanceof HeavyweightForcer) {
                        if (force) {
                            return;
                        }
                        owned[i].dispose();
                    }
                }
                final Boolean b = Boolean.valueOf(System.getProperty("jna.force_hw_popups", "true"));
                if (force && b) {
                    new HeavyweightForcer(w);
                }
            }
        }
        
        protected void setMask(final Component c, final Rectangle[] rectangles) {
            Area area;
            if (rectangles == null) {
                area = null;
            }
            else {
                area = new Area();
                for (int i = 0; i < rectangles.length; ++i) {
                    area.add(new Area(rectangles[i]));
                }
            }
            this.setWindowMask(c, area);
        }
        
        public void setWindowMask(final Component w, Rectangle[] rectangles) {
            if (w.isLightweight()) {
                throw new IllegalArgumentException("Component must be heavyweight: " + w);
            }
            if (rectangles == null) {
                rectangles = new Rectangle[] { new Rectangle(0, 0, w.getWidth(), w.getHeight()) };
            }
            this.setMask(w, rectangles);
        }
        
        protected abstract class TransparentContentPane extends JPanel implements AWTEventListener
        {
            private boolean transparent;
            
            public TransparentContentPane(final Container oldContent) {
                super(new BorderLayout());
                this.add(oldContent, "Center");
                this.setTransparent(true);
                if (oldContent instanceof JPanel) {
                    ((JComponent)oldContent).setOpaque(false);
                }
            }
            
            @Override
            public void addNotify() {
                super.addNotify();
                Toolkit.getDefaultToolkit().addAWTEventListener(this, 2L);
            }
            
            @Override
            public void removeNotify() {
                Toolkit.getDefaultToolkit().removeAWTEventListener(this);
                super.removeNotify();
            }
            
            public void setTransparent(final boolean transparent) {
                this.transparent = transparent;
                this.setOpaque(!transparent);
                this.setDoubleBuffered(!transparent);
                this.repaint();
            }
            
            @Override
            public void eventDispatched(final AWTEvent e) {
                if (e.getID() == 300 && SwingUtilities.isDescendingFrom(((ContainerEvent)e).getChild(), this)) {
                    final Component child = ((ContainerEvent)e).getChild();
                    NativeWindowUtils.this.setDoubleBuffered(child, false);
                }
            }
            
            @Override
            public void paint(final Graphics gr) {
                if (this.transparent) {
                    final Rectangle r = gr.getClipBounds();
                    final int w = r.width;
                    final int h = r.height;
                    if (this.getWidth() > 0 && this.getHeight() > 0) {
                        final BufferedImage buf = new BufferedImage(w, h, 3);
                        Graphics2D g = buf.createGraphics();
                        g.setComposite(AlphaComposite.Clear);
                        g.fillRect(0, 0, w, h);
                        g.dispose();
                        g = buf.createGraphics();
                        g.translate(-r.x, -r.y);
                        super.paint(g);
                        g.dispose();
                        this.paintDirect(buf, r);
                    }
                }
                else {
                    super.paint(gr);
                }
            }
            
            protected abstract void paintDirect(final BufferedImage p0, final Rectangle p1);
        }
    }
    
    private static class Holder
    {
        public static boolean requiresVisible;
        public static final NativeWindowUtils INSTANCE;
        
        static {
            if (Platform.isWindows()) {
                INSTANCE = new W32WindowUtils(null);
            }
            else if (Platform.isMac()) {
                INSTANCE = new MacWindowUtils(null);
            }
            else {
                if (!Platform.isX11()) {
                    final String os = System.getProperty("os.name");
                    throw new UnsupportedOperationException("No support for " + os);
                }
                INSTANCE = new X11WindowUtils(null);
                Holder.requiresVisible = System.getProperty("java.version").matches("^1\\.4\\..*");
            }
        }
    }
    
    private static class W32WindowUtils extends NativeWindowUtils
    {
        private WinDef.HWND getHWnd(final Component w) {
            final WinDef.HWND hwnd = new WinDef.HWND();
            hwnd.setPointer(Native.getComponentPointer(w));
            return hwnd;
        }
        
        @Override
        public boolean isWindowAlphaSupported() {
            return Boolean.getBoolean("sun.java2d.noddraw");
        }
        
        private boolean usingUpdateLayeredWindow(final Window w) {
            if (w instanceof RootPaneContainer) {
                final JRootPane root = ((RootPaneContainer)w).getRootPane();
                return root.getClientProperty("transparent-old-bg") != null;
            }
            return false;
        }
        
        private void storeAlpha(final Window w, final byte alpha) {
            if (w instanceof RootPaneContainer) {
                final JRootPane root = ((RootPaneContainer)w).getRootPane();
                final Byte b = (alpha == -1) ? null : new Byte(alpha);
                root.putClientProperty("transparent-alpha", b);
            }
        }
        
        private byte getAlpha(final Window w) {
            if (w instanceof RootPaneContainer) {
                final JRootPane root = ((RootPaneContainer)w).getRootPane();
                final Byte b = (Byte)root.getClientProperty("transparent-alpha");
                if (b != null) {
                    return b;
                }
            }
            return -1;
        }
        
        @Override
        public void setWindowAlpha(final Window w, final float alpha) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils.isWindowAlphaSupported:()Z
            //     4: ifne            17
            //     7: new             Ljava/lang/UnsupportedOperationException;
            //    10: dup            
            //    11: ldc             "Set sun.java2d.noddraw=true to enable transparent windows"
            //    13: invokespecial   java/lang/UnsupportedOperationException.<init>:(Ljava/lang/String;)V
            //    16: athrow         
            //    17: aload_0         /* this */
            //    18: aload_1         /* w */
            //    19: new             Lchrriis/dj/nativeswing/jna/platform/llIlI;
            //    22: dup            
            //    23: aload_0         /* this */
            //    24: aload_1         /* w */
            //    25: fload_2         /* alpha */
            //    26: invokespecial   chrriis/dj/nativeswing/jna/platform/llIlI.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils;Ljava/awt/Window;F)V
            //    29: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils.whenDisplayable:(Ljava/awt/Component;Ljava/lang/Runnable;)V
            //    32: return         
            //    StackMapTable: 00 01 11
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
        
        @Override
        public void setWindowTransparent(final Window w, final boolean transparent) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: instanceof      Ljavax/swing/RootPaneContainer;
            //     4: ifne            17
            //     7: new             Ljava/lang/IllegalArgumentException;
            //    10: dup            
            //    11: ldc             "Window must be a RootPaneContainer"
            //    13: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    16: athrow         
            //    17: aload_0         /* this */
            //    18: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils.isWindowAlphaSupported:()Z
            //    21: ifne            34
            //    24: new             Ljava/lang/UnsupportedOperationException;
            //    27: dup            
            //    28: ldc             "Set sun.java2d.noddraw=true to enable transparent windows"
            //    30: invokespecial   java/lang/UnsupportedOperationException.<init>:(Ljava/lang/String;)V
            //    33: athrow         
            //    34: aload_1         /* w */
            //    35: invokevirtual   java/awt/Window.getBackground:()Ljava/awt/Color;
            //    38: ifnull          55
            //    41: aload_1         /* w */
            //    42: invokevirtual   java/awt/Window.getBackground:()Ljava/awt/Color;
            //    45: invokevirtual   java/awt/Color.getAlpha:()I
            //    48: ifne            55
            //    51: iconst_1       
            //    52: goto            56
            //    55: iconst_0       
            //    56: istore_3        /* isTransparent */
            //    57: iload_2         /* transparent */
            //    58: iload_3         /* isTransparent */
            //    59: if_icmpne       63
            //    62: return         
            //    63: aload_0         /* this */
            //    64: aload_1         /* w */
            //    65: new             Lchrriis/dj/nativeswing/jna/platform/lIlII;
            //    68: dup            
            //    69: aload_0         /* this */
            //    70: aload_1         /* w */
            //    71: iload_2         /* transparent */
            //    72: invokespecial   chrriis/dj/nativeswing/jna/platform/lIlII.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils;Ljava/awt/Window;Z)V
            //    75: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils.whenDisplayable:(Ljava/awt/Component;Ljava/lang/Runnable;)V
            //    78: return         
            //    StackMapTable: 00 05 11 10 14 40 01 FC 00 06 01
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
        
        @Override
        public void setWindowClickThrough(final Window w, final boolean isClickThrough) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: instanceof      Ljavax/swing/RootPaneContainer;
            //     4: ifne            17
            //     7: new             Ljava/lang/IllegalArgumentException;
            //    10: dup            
            //    11: ldc             "Window must be a RootPaneContainer"
            //    13: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    16: athrow         
            //    17: aload_0         /* this */
            //    18: aload_1         /* w */
            //    19: new             Lchrriis/dj/nativeswing/jna/platform/lIIIl;
            //    22: dup            
            //    23: aload_0         /* this */
            //    24: aload_1         /* w */
            //    25: iload_2         /* isClickThrough */
            //    26: invokespecial   chrriis/dj/nativeswing/jna/platform/lIIIl.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils;Ljava/awt/Window;Z)V
            //    29: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils.whenDisplayable:(Ljava/awt/Component;Ljava/lang/Runnable;)V
            //    32: return         
            //    StackMapTable: 00 01 11
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
        
        @Override
        public void setWindowMask(final Component w, final Shape mask) {
            if (mask instanceof Area && ((Area)mask).isPolygonal()) {
                this.setMask(w, (Area)mask);
            }
            else {
                super.setWindowMask(w, mask);
            }
        }
        
        private void setWindowRegion(final Component w, final WinDef.HRGN hrgn) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: aload_1         /* w */
            //     2: new             Lchrriis/dj/nativeswing/jna/platform/lIlIl;
            //     5: dup            
            //     6: aload_0         /* this */
            //     7: aload_1         /* w */
            //     8: aload_2         /* hrgn */
            //     9: invokespecial   chrriis/dj/nativeswing/jna/platform/lIlIl.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils;Ljava/awt/Component;Lcom/sun/jna/platform/win32/WinDef$HRGN;)V
            //    12: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils.whenDisplayable:(Ljava/awt/Component;Ljava/lang/Runnable;)V
            //    15: return         
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
        
        private void setMask(final Component w, final Area area) {
            final GDI32 gdi = GDI32.INSTANCE;
            final PathIterator pi = area.getPathIterator(null);
            final int mode = (pi.getWindingRule() == 1) ? 2 : 1;
            final float[] coords = new float[6];
            final List points = new ArrayList();
            int size = 0;
            final List sizes = new ArrayList();
            while (!pi.isDone()) {
                final int type = pi.currentSegment(coords);
                if (type == 0) {
                    size = 1;
                    points.add(new WinUser.POINT((int)coords[0], (int)coords[1]));
                }
                else if (type == 1) {
                    ++size;
                    points.add(new WinUser.POINT((int)coords[0], (int)coords[1]));
                }
                else {
                    if (type != 4) {
                        throw new RuntimeException("Area is not polygonal: " + area);
                    }
                    sizes.add(new Integer(size));
                }
                pi.next();
            }
            final WinUser.POINT[] lppt = (WinUser.POINT[])new WinUser.POINT().toArray(points.size());
            final WinUser.POINT[] pts = points.toArray(new WinUser.POINT[points.size()]);
            for (int i = 0; i < lppt.length; ++i) {
                lppt[i].x = pts[i].x;
                lppt[i].y = pts[i].y;
            }
            final int[] counts = new int[sizes.size()];
            for (int j = 0; j < counts.length; ++j) {
                counts[j] = sizes.get(j);
            }
            final WinDef.HRGN hrgn = gdi.CreatePolyPolygonRgn(lppt, counts, counts.length, mode);
            this.setWindowRegion(w, hrgn);
        }
        
        @Override
        protected void setMask(final Component w, final Raster raster) {
            final GDI32 gdi = GDI32.INSTANCE;
            final WinDef.HRGN region = (raster != null) ? gdi.CreateRectRgn(0, 0, 0, 0) : null;
            if (region != null) {
                final WinDef.HRGN tempRgn = gdi.CreateRectRgn(0, 0, 0, 0);
                try {
                    RasterRangesUtils.outputOccupiedRanges(raster, (RasterRangesUtils.RangesOutput)new lllIl(this, tempRgn, region));
                }
                finally {
                    gdi.DeleteObject((WinNT.HANDLE)tempRgn);
                }
            }
            this.setWindowRegion(w, region);
        }
        
        @Override
        protected void setMask(final Component w, final Rectangle[] rectangles) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: aload_1         /* w */
            //     2: new             Lchrriis/dj/nativeswing/jna/platform/llIIl;
            //     5: dup            
            //     6: aload_0         /* this */
            //     7: aload_1         /* w */
            //     8: aload_2         /* rectangles */
            //     9: invokespecial   chrriis/dj/nativeswing/jna/platform/llIIl.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils;Ljava/awt/Component;[Ljava/awt/Rectangle;)V
            //    12: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$W32WindowUtils.whenDisplayable:(Ljava/awt/Component;Ljava/lang/Runnable;)V
            //    15: return         
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
        
        private class W32TransparentContentPane extends TransparentContentPane
        {
            private WinDef.HDC memDC;
            private WinDef.HBITMAP hBitmap;
            private Pointer pbits;
            private Dimension bitmapSize;
            
            public W32TransparentContentPane(final Container content) {
                super(content);
            }
            
            private void disposeBackingStore() {
                final GDI32 gdi = GDI32.INSTANCE;
                if (this.hBitmap != null) {
                    gdi.DeleteObject((WinNT.HANDLE)this.hBitmap);
                    this.hBitmap = null;
                }
                if (this.memDC != null) {
                    gdi.DeleteDC(this.memDC);
                    this.memDC = null;
                }
            }
            
            @Override
            public void removeNotify() {
                super.removeNotify();
                this.disposeBackingStore();
            }
            
            @Override
            public void setTransparent(final boolean transparent) {
                super.setTransparent(transparent);
                if (!transparent) {
                    this.disposeBackingStore();
                }
            }
            
            @Override
            protected void paintDirect(final BufferedImage buf, final Rectangle bounds) {
                final Window win = SwingUtilities.getWindowAncestor(this);
                final GDI32 gdi = GDI32.INSTANCE;
                final User32 user = User32.INSTANCE;
                final int x = bounds.x;
                final int y = bounds.y;
                final Point origin = SwingUtilities.convertPoint(this, x, y, win);
                final int w = bounds.width;
                final int h = bounds.height;
                final int ww = win.getWidth();
                final int wh = win.getHeight();
                final WinDef.HDC screenDC = user.GetDC((WinDef.HWND)null);
                WinNT.HANDLE oldBitmap = null;
                try {
                    if (this.memDC == null) {
                        this.memDC = gdi.CreateCompatibleDC(screenDC);
                    }
                    if (this.hBitmap == null || !win.getSize().equals(this.bitmapSize)) {
                        if (this.hBitmap != null) {
                            gdi.DeleteObject((WinNT.HANDLE)this.hBitmap);
                            this.hBitmap = null;
                        }
                        final WinGDI.BITMAPINFO bmi = new WinGDI.BITMAPINFO();
                        bmi.bmiHeader.biWidth = ww;
                        bmi.bmiHeader.biHeight = wh;
                        bmi.bmiHeader.biPlanes = 1;
                        bmi.bmiHeader.biBitCount = 32;
                        bmi.bmiHeader.biCompression = 0;
                        bmi.bmiHeader.biSizeImage = ww * wh * 4;
                        final PointerByReference ppbits = new PointerByReference();
                        this.hBitmap = gdi.CreateDIBSection(this.memDC, bmi, 0, ppbits, (Pointer)null, 0);
                        this.pbits = ppbits.getValue();
                        this.bitmapSize = new Dimension(ww, wh);
                    }
                    oldBitmap = gdi.SelectObject(this.memDC, (WinNT.HANDLE)this.hBitmap);
                    final Raster raster = buf.getData();
                    final int[] pixel = new int[4];
                    final int[] bits = new int[w];
                    for (int row = 0; row < h; ++row) {
                        for (int col = 0; col < w; ++col) {
                            raster.getPixel(col, row, pixel);
                            final int alpha = (pixel[3] & 0xFF) << 24;
                            final int red = pixel[2] & 0xFF;
                            final int green = (pixel[1] & 0xFF) << 8;
                            final int blue = (pixel[0] & 0xFF) << 16;
                            bits[col] = (alpha | red | green | blue);
                        }
                        final int v = wh - (origin.y + row) - 1;
                        this.pbits.write((long)((v * ww + origin.x) * 4), bits, 0, bits.length);
                    }
                    final WinUser.SIZE winSize = new WinUser.SIZE();
                    winSize.cx = win.getWidth();
                    winSize.cy = win.getHeight();
                    final WinUser.POINT winLoc = new WinUser.POINT();
                    winLoc.x = win.getX();
                    winLoc.y = win.getY();
                    final WinUser.POINT srcLoc = new WinUser.POINT();
                    final WinUser.BLENDFUNCTION blend = new WinUser.BLENDFUNCTION();
                    final WinDef.HWND hWnd = W32WindowUtils.this.getHWnd(win);
                    final ByteByReference bref = new ByteByReference();
                    final IntByReference iref = new IntByReference();
                    byte level = W32WindowUtils.this.getAlpha(win);
                    try {
                        if (user.GetLayeredWindowAttributes(hWnd, (IntByReference)null, bref, iref) && (iref.getValue() & 0x2) != 0x0) {
                            level = bref.getValue();
                        }
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
                    blend.SourceConstantAlpha = level;
                    blend.AlphaFormat = 1;
                    user.UpdateLayeredWindow(hWnd, screenDC, winLoc, winSize, this.memDC, srcLoc, 0, blend, 2);
                }
                finally {
                    user.ReleaseDC((WinDef.HWND)null, screenDC);
                    if (this.memDC != null && oldBitmap != null) {
                        gdi.SelectObject(this.memDC, oldBitmap);
                    }
                }
            }
        }
    }
    
    private static class MacWindowUtils extends NativeWindowUtils
    {
        private static final String WDRAG = "apple.awt.draggableWindowBackground";
        
        @Override
        public boolean isWindowAlphaSupported() {
            return true;
        }
        
        private OSXMaskingContentPane installMaskingPane(final Window w) {
            OSXMaskingContentPane content;
            if (w instanceof RootPaneContainer) {
                final RootPaneContainer rpc = (RootPaneContainer)w;
                final Container oldContent = rpc.getContentPane();
                if (oldContent instanceof OSXMaskingContentPane) {
                    content = (OSXMaskingContentPane)oldContent;
                }
                else {
                    content = new OSXMaskingContentPane(oldContent);
                    rpc.setContentPane(content);
                }
            }
            else {
                final Component oldContent2 = (w.getComponentCount() > 0) ? w.getComponent(0) : null;
                if (oldContent2 instanceof OSXMaskingContentPane) {
                    content = (OSXMaskingContentPane)oldContent2;
                }
                else {
                    content = new OSXMaskingContentPane(oldContent2);
                    w.add(content);
                }
            }
            return content;
        }
        
        @Override
        public void setWindowTransparent(final Window w, final boolean transparent) {
            final boolean isTransparent = w.getBackground() != null && w.getBackground().getAlpha() == 0;
            if (transparent != isTransparent) {
                this.setBackgroundTransparent(w, transparent, "setWindowTransparent");
            }
        }
        
        private void fixWindowDragging(final Window w, final String context) {
            if (w instanceof RootPaneContainer) {
                final JRootPane p = ((RootPaneContainer)w).getRootPane();
                final Boolean oldDraggable = (Boolean)p.getClientProperty("apple.awt.draggableWindowBackground");
                if (oldDraggable == null) {
                    p.putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE);
                    if (w.isDisplayable()) {
                        System.err.println(context + "(): To avoid content dragging, " + context + "() must be called before the window is realized, or " + "apple.awt.draggableWindowBackground" + " must be set to Boolean.FALSE before the window is realized.  If you really want content dragging, set " + "apple.awt.draggableWindowBackground" + " on the window's root pane to Boolean.TRUE before calling " + context + "() to hide this message.");
                    }
                }
            }
        }
        
        @Override
        public void setWindowAlpha(final Window w, final float alpha) {
            if (w instanceof RootPaneContainer) {
                final JRootPane p = ((RootPaneContainer)w).getRootPane();
                p.putClientProperty("Window.alpha", new Float(alpha));
                this.fixWindowDragging(w, "setWindowAlpha");
            }
            this.whenDisplayable(w, (Runnable)new llIll(this, w, alpha));
        }
        
        @Override
        protected void setWindowMask(final Component w, final Raster raster) {
            if (raster != null) {
                this.setWindowMask(w, this.toShape(raster));
            }
            else {
                this.setWindowMask(w, new Rectangle(0, 0, w.getWidth(), w.getHeight()));
            }
        }
        
        @Override
        public void setWindowMask(final Component c, final Shape shape) {
            if (c instanceof Window) {
                final Window w = (Window)c;
                final OSXMaskingContentPane content = this.installMaskingPane(w);
                content.setMask(shape);
                this.setBackgroundTransparent(w, shape != WindowUtils.MASK_NONE, "setWindowMask");
            }
        }
        
        private void setBackgroundTransparent(final Window w, final boolean transparent, final String context) {
            final JRootPane rp = (w instanceof RootPaneContainer) ? ((RootPaneContainer)w).getRootPane() : null;
            if (transparent) {
                if (rp != null) {
                    rp.putClientProperty("transparent-old-bg", w.getBackground());
                }
                w.setBackground(new Color(0, 0, 0, 0));
            }
            else if (rp != null) {
                Color bg = (Color)rp.getClientProperty("transparent-old-bg");
                if (bg != null) {
                    bg = new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), bg.getAlpha());
                }
                w.setBackground(bg);
                rp.putClientProperty("transparent-old-bg", null);
            }
            else {
                w.setBackground(null);
            }
            this.fixWindowDragging(w, context);
        }
        
        private static class OSXMaskingContentPane extends JPanel
        {
            private Shape shape;
            
            public OSXMaskingContentPane(final Component oldContent) {
                super(new BorderLayout());
                if (oldContent != null) {
                    this.add(oldContent, "Center");
                }
            }
            
            public void setMask(final Shape shape) {
                this.shape = shape;
                this.repaint();
            }
            
            @Override
            public void paint(final Graphics graphics) {
                Graphics2D g = (Graphics2D)graphics.create();
                g.setComposite(AlphaComposite.Clear);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.dispose();
                if (this.shape != null) {
                    g = (Graphics2D)graphics.create();
                    g.setClip(this.shape);
                    super.paint(g);
                    g.dispose();
                }
                else {
                    super.paint(graphics);
                }
            }
        }
    }
    
    private static class X11WindowUtils extends NativeWindowUtils
    {
        private boolean didCheck;
        private long[] alphaVisualIDs;
        private static final long OPAQUE = 4294967295L;
        private static final String OPACITY = "_NET_WM_WINDOW_OPACITY";
        
        private X11WindowUtils() {
            this.alphaVisualIDs = new long[0];
        }
        
        private static X11.Pixmap createBitmap(final X11.Display dpy, final X11.Window win, final Raster raster) {
            final X11 x11 = X11.INSTANCE;
            final Rectangle bounds = raster.getBounds();
            final int width = bounds.x + bounds.width;
            final int height = bounds.y + bounds.height;
            final X11.Pixmap pm = x11.XCreatePixmap(dpy, (X11.Drawable)win, width, height, 1);
            final X11.GC gc = x11.XCreateGC(dpy, (X11.Drawable)pm, new NativeLong(0L), (X11.XGCValues)null);
            if (gc == null) {
                return null;
            }
            x11.XSetForeground(dpy, gc, new NativeLong(0L));
            x11.XFillRectangle(dpy, (X11.Drawable)pm, gc, 0, 0, width, height);
            final List rlist = new ArrayList();
            try {
                RasterRangesUtils.outputOccupiedRanges(raster, (RasterRangesUtils.RangesOutput)new lIIIIl(rlist));
                final X11.XRectangle[] rects = (X11.XRectangle[])new X11.XRectangle().toArray(rlist.size());
                for (int i = 0; i < rects.length; ++i) {
                    final Rectangle r = rlist.get(i);
                    rects[i].x = (short)r.x;
                    rects[i].y = (short)r.y;
                    rects[i].width = (short)r.width;
                    rects[i].height = (short)r.height;
                    final Pointer p = rects[i].getPointer();
                    p.setShort(0L, (short)r.x);
                    p.setShort(2L, (short)r.y);
                    p.setShort(4L, (short)r.width);
                    p.setShort(6L, (short)r.height);
                    rects[i].setAutoSynch(false);
                }
                final int UNMASKED = 1;
                x11.XSetForeground(dpy, gc, new NativeLong(1L));
                x11.XFillRectangles(dpy, (X11.Drawable)pm, gc, rects, rects.length);
            }
            finally {
                x11.XFreeGC(dpy, gc);
            }
            return pm;
        }
        
        @Override
        public boolean isWindowAlphaSupported() {
            return this.getAlphaVisualIDs().length > 0;
        }
        
        private static long getVisualID(final GraphicsConfiguration config) {
            try {
                final Object o = config.getClass().getMethod("getVisual", (Class<?>[])null).invoke(config, (Object[])null);
                return ((Number)o).longValue();
            }
            catch (Exception e) {
                e.printStackTrace();
                return -1L;
            }
        }
        
        @Override
        public GraphicsConfiguration getAlphaCompatibleGraphicsConfiguration() {
            if (this.isWindowAlphaSupported()) {
                final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                final GraphicsDevice[] devices = env.getScreenDevices();
                for (int i = 0; i < devices.length; ++i) {
                    final GraphicsConfiguration[] configs = devices[i].getConfigurations();
                    for (int j = 0; j < configs.length; ++j) {
                        final long visualID = getVisualID(configs[j]);
                        final long[] ids = this.getAlphaVisualIDs();
                        for (int k = 0; k < ids.length; ++k) {
                            if (visualID == ids[k]) {
                                return configs[j];
                            }
                        }
                    }
                }
            }
            return super.getAlphaCompatibleGraphicsConfiguration();
        }
        
        private synchronized long[] getAlphaVisualIDs() {
            if (this.didCheck) {
                return this.alphaVisualIDs;
            }
            this.didCheck = true;
            final X11 x11 = X11.INSTANCE;
            final X11.Display dpy = x11.XOpenDisplay((String)null);
            if (dpy == null) {
                return this.alphaVisualIDs;
            }
            X11.XVisualInfo info = null;
            try {
                final int screen = x11.XDefaultScreen(dpy);
                final X11.XVisualInfo template = new X11.XVisualInfo();
                template.screen = screen;
                template.depth = 32;
                template.c_class = 4;
                final NativeLong mask = new NativeLong(14L);
                final IntByReference pcount = new IntByReference();
                info = x11.XGetVisualInfo(dpy, mask, template, pcount);
                if (info != null) {
                    final List list = new ArrayList();
                    final X11.XVisualInfo[] infos = (X11.XVisualInfo[])info.toArray(pcount.getValue());
                    for (int i = 0; i < infos.length; ++i) {
                        final X11.Xrender.XRenderPictFormat format = X11.Xrender.INSTANCE.XRenderFindVisualFormat(dpy, infos[i].visual);
                        if (format.type == 1 && format.direct.alphaMask != 0) {
                            list.add(infos[i].visualid);
                        }
                    }
                    this.alphaVisualIDs = new long[list.size()];
                    for (int i = 0; i < this.alphaVisualIDs.length; ++i) {
                        this.alphaVisualIDs[i] = list.get(i).longValue();
                    }
                    return this.alphaVisualIDs;
                }
            }
            finally {
                if (info != null) {
                    x11.XFree(info.getPointer());
                }
                x11.XCloseDisplay(dpy);
            }
            return this.alphaVisualIDs;
        }
        
        private static X11.Window getContentWindow(final Window w, final X11.Display dpy, X11.Window win, final Point offset) {
            if ((w instanceof Frame && !((Frame)w).isUndecorated()) || (w instanceof Dialog && !((Dialog)w).isUndecorated())) {
                final X11 x11 = X11.INSTANCE;
                final X11.WindowByReference rootp = new X11.WindowByReference();
                final X11.WindowByReference parentp = new X11.WindowByReference();
                final PointerByReference childrenp = new PointerByReference();
                final IntByReference countp = new IntByReference();
                x11.XQueryTree(dpy, win, rootp, parentp, childrenp, countp);
                final Pointer p = childrenp.getValue();
                final int[] ids = p.getIntArray(0L, countp.getValue());
                final int i = 0;
                if (i < ids.length) {
                    final X11.Window child = new X11.Window((long)ids[i]);
                    final X11.XWindowAttributes xwa = new X11.XWindowAttributes();
                    x11.XGetWindowAttributes(dpy, child, xwa);
                    offset.x = -xwa.x;
                    offset.y = -xwa.y;
                    win = child;
                }
                if (p != null) {
                    x11.XFree(p);
                }
            }
            return win;
        }
        
        private static X11.Window getDrawable(final Component w) {
            final int id = (int)Native.getComponentID(w);
            if (id == 0) {
                return null;
            }
            return new X11.Window((long)id);
        }
        
        @Override
        public void setWindowAlpha(final Window w, final float alpha) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils.isWindowAlphaSupported:()Z
            //     4: ifne            18
            //     7: new             Ljava/lang/UnsupportedOperationException;
            //    10: dup            
            //    11: ldc_w           "This X11 display does not provide a 32-bit visual"
            //    14: invokespecial   java/lang/UnsupportedOperationException.<init>:(Ljava/lang/String;)V
            //    17: athrow         
            //    18: new             Lchrriis/dj/nativeswing/jna/platform/lIllI;
            //    21: dup            
            //    22: aload_0         /* this */
            //    23: aload_1         /* w */
            //    24: fload_2         /* alpha */
            //    25: invokespecial   chrriis/dj/nativeswing/jna/platform/lIllI.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils;Ljava/awt/Window;F)V
            //    28: astore_3        /* action */
            //    29: aload_0         /* this */
            //    30: aload_1         /* w */
            //    31: aload_3         /* action */
            //    32: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils.whenDisplayable:(Ljava/awt/Component;Ljava/lang/Runnable;)V
            //    35: return         
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
        
        @Override
        public void setWindowTransparent(final Window w, final boolean transparent) {
            if (!(w instanceof RootPaneContainer)) {
                throw new IllegalArgumentException("Window must be a RootPaneContainer");
            }
            if (!this.isWindowAlphaSupported()) {
                throw new UnsupportedOperationException("This X11 display does not provide a 32-bit visual");
            }
            if (!w.getGraphicsConfiguration().equals(this.getAlphaCompatibleGraphicsConfiguration())) {
                throw new IllegalArgumentException("Window GraphicsConfiguration '" + w.getGraphicsConfiguration() + "' does not support transparency");
            }
            final boolean isTransparent = w.getBackground() != null && w.getBackground().getAlpha() == 0;
            if (transparent == isTransparent) {
                return;
            }
            this.whenDisplayable(w, (Runnable)new lllll(this, w, transparent));
        }
        
        private void setWindowShape(final Window w, final PixmapSource src) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: aload_0         /* this */
            //     5: aload_1         /* w */
            //     6: aload_2         /* src */
            //     7: invokespecial   chrriis/dj/nativeswing/jna/platform/lIIll.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils;Ljava/awt/Window;Lchrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils$PixmapSource;)V
            //    10: astore_3        /* action */
            //    11: aload_0         /* this */
            //    12: aload_1         /* w */
            //    13: aload_3         /* action */
            //    14: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils.whenDisplayable:(Ljava/awt/Component;Ljava/lang/Runnable;)V
            //    17: return         
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
        
        @Override
        protected void setMask(final Component w, final Raster raster) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: aload_0         /* this */
            //     2: aload_1         /* w */
            //     3: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils.getWindow:(Ljava/awt/Component;)Ljava/awt/Window;
            //     6: new             Lchrriis/dj/nativeswing/jna/platform/lIIlI;
            //     9: dup            
            //    10: aload_0         /* this */
            //    11: aload_2         /* raster */
            //    12: invokespecial   chrriis/dj/nativeswing/jna/platform/lIIlI.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils;Ljava/awt/image/Raster;)V
            //    15: invokespecial   chrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils.setWindowShape:(Ljava/awt/Window;Lchrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils$PixmapSource;)V
            //    18: return         
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
        
        private X11.Pixmap createBitmap(final X11.Display dpy, final X11.Window win, final Rectangle[] rectangles) {
            final X11 x11 = X11.INSTANCE;
            final Rectangle bounds = new Rectangle();
            if (rectangles.length > 0) {
                bounds.setBounds(rectangles[0]);
                for (int i = 1; i < rectangles.length; ++i) {
                    Rectangle2D.union(bounds, rectangles[i], bounds);
                }
            }
            final int width = bounds.x + bounds.width;
            final int height = bounds.y + bounds.height;
            final X11.Pixmap pm = x11.XCreatePixmap(dpy, (X11.Drawable)win, width, height, 1);
            final X11.GC gc = x11.XCreateGC(dpy, (X11.Drawable)pm, new NativeLong(0L), (X11.XGCValues)null);
            if (gc == null) {
                return null;
            }
            x11.XSetForeground(dpy, gc, new NativeLong(0L));
            x11.XFillRectangle(dpy, (X11.Drawable)pm, gc, 0, 0, width, height);
            final int UNMASKED = 1;
            x11.XSetForeground(dpy, gc, new NativeLong(1L));
            final X11.XWindowAttributes atts = new X11.XWindowAttributes();
            final int status = x11.XGetWindowAttributes(dpy, win, atts);
            if (status == 0) {
                return null;
            }
            try {
                for (int j = 0; j < rectangles.length; ++j) {
                    final Rectangle rect = rectangles[j];
                    x11.XFillRectangle(dpy, (X11.Drawable)pm, gc, rect.x, rect.y, rect.width, rect.height);
                }
            }
            finally {
                x11.XFreeGC(dpy, gc);
            }
            return pm;
        }
        
        @Override
        protected void setMask(final Component w, final Rectangle[] rectangles) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: aload_0         /* this */
            //     5: aload_1         /* w */
            //     6: aload_2         /* rectangles */
            //     7: invokespecial   chrriis/dj/nativeswing/jna/platform/lIlll.<init>:(Lchrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils;Ljava/awt/Component;[Ljava/awt/Rectangle;)V
            //    10: astore_3        /* action */
            //    11: aload_0         /* this */
            //    12: aload_1         /* w */
            //    13: aload_3         /* action */
            //    14: invokevirtual   chrriis/dj/nativeswing/jna/platform/WindowUtils$X11WindowUtils.whenDisplayable:(Ljava/awt/Component;Ljava/lang/Runnable;)V
            //    17: return         
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
        
        private class X11TransparentContentPane extends TransparentContentPane
        {
            private Memory buffer;
            private int[] pixels;
            private final int[] pixel;
            
            public X11TransparentContentPane(final Container oldContent) {
                super(oldContent);
                this.pixel = new int[4];
            }
            
            @Override
            protected void paintDirect(final BufferedImage buf, final Rectangle bounds) {
                final Window window = SwingUtilities.getWindowAncestor(this);
                final X11 x11 = X11.INSTANCE;
                final X11.Display dpy = x11.XOpenDisplay((String)null);
                X11.Window win = getDrawable(window);
                final Point offset = new Point();
                win = getContentWindow(window, dpy, win, offset);
                final X11.GC gc = x11.XCreateGC(dpy, (X11.Drawable)win, new NativeLong(0L), (X11.XGCValues)null);
                final Raster raster = buf.getData();
                final int w = bounds.width;
                final int h = bounds.height;
                if (this.buffer == null || this.buffer.size() != w * h * 4) {
                    this.buffer = new Memory((long)(w * h * 4));
                    this.pixels = new int[w * h];
                }
                for (int y = 0; y < h; ++y) {
                    for (int x12 = 0; x12 < w; ++x12) {
                        raster.getPixel(x12, y, this.pixel);
                        final int alpha = this.pixel[3] & 0xFF;
                        final int red = this.pixel[2] & 0xFF;
                        final int green = this.pixel[1] & 0xFF;
                        final int blue = this.pixel[0] & 0xFF;
                        this.pixels[y * w + x12] = (alpha << 24 | blue << 16 | green << 8 | red);
                    }
                }
                final X11.XWindowAttributes xwa = new X11.XWindowAttributes();
                x11.XGetWindowAttributes(dpy, win, xwa);
                final X11.XImage image = x11.XCreateImage(dpy, xwa.visual, 32, 2, 0, (Pointer)this.buffer, w, h, 32, w * 4);
                this.buffer.write(0L, this.pixels, 0, this.pixels.length);
                final Point point = offset;
                point.x += bounds.x;
                final Point point2 = offset;
                point2.y += bounds.y;
                x11.XPutImage(dpy, (X11.Drawable)win, gc, image, 0, 0, offset.x, offset.y, w, h);
                x11.XFree(image.getPointer());
                x11.XFreeGC(dpy, gc);
                x11.XCloseDisplay(dpy);
            }
        }
        
        private interface PixmapSource
        {
            X11.Pixmap getPixmap(final X11.Display p0, final X11.Window p1);
        }
    }
}
