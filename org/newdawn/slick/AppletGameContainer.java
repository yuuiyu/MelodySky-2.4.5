//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.applet.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.openal.*;
import org.lwjgl.input.*;
import org.lwjgl.*;
import java.nio.*;
import org.newdawn.slick.opengl.*;
import org.lwjgl.opengl.*;
import java.io.*;
import java.awt.*;

public class AppletGameContainer extends Applet
{
    protected ContainerPanel canvas;
    protected Container container;
    protected Canvas displayParent;
    protected Thread gameThread;
    protected boolean alphaSupport;
    
    public AppletGameContainer() {
        this.alphaSupport = true;
    }
    
    @Override
    public void destroy() {
        if (this.displayParent != null) {
            this.remove(this.displayParent);
        }
        super.destroy();
        Log.info("Clear up");
    }
    
    private void destroyLWJGL() {
        this.container.stopApplet();
        try {
            this.gameThread.join();
        }
        catch (InterruptedException e) {
            Log.error(e);
        }
    }
    
    @Override
    public void start() {
    }
    
    public void startLWJGL() {
        class lIlIl extends Thread
        {
            final /* synthetic */ AppletGameContainer this$0;
            
            lIlIl(final AppletGameContainer this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                try {
                    this.this$0.canvas.start();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (Display.isCreated()) {
                        Display.destroy();
                    }
                    this.this$0.displayParent.setVisible(false);
                    this.this$0.add(this.this$0.new ConsolePanel(e));
                    this.this$0.validate();
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/newdawn/slick/AppletGameContainer.gameThread:Ljava/lang/Thread;
        //     4: ifnull          8
        //     7: return         
        //     8: aload_0         /* this */
        //     9: new             Lorg/newdawn/slick/lIlIl;
        //    12: dup            
        //    13: aload_0         /* this */
        //    14: invokespecial   org/newdawn/slick/lIlIl.<init>:(Lorg/newdawn/slick/AppletGameContainer;)V
        //    17: putfield        org/newdawn/slick/AppletGameContainer.gameThread:Ljava/lang/Thread;
        //    20: aload_0         /* this */
        //    21: getfield        org/newdawn/slick/AppletGameContainer.gameThread:Ljava/lang/Thread;
        //    24: invokevirtual   java/lang/Thread.start:()V
        //    27: return         
        //    StackMapTable: 00 01 08
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void stop() {
    }
    
    @Override
    public void init() {
        class llIII extends Canvas
        {
            final /* synthetic */ AppletGameContainer this$0;
            
            llIII(final AppletGameContainer this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public final void addNotify() {
                super.addNotify();
                this.this$0.startLWJGL();
            }
            
            @Override
            public final void removeNotify() {
                this.this$0.destroyLWJGL();
                super.removeNotify();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/newdawn/slick/AppletGameContainer.removeAll:()V
        //     4: aload_0         /* this */
        //     5: new             Ljava/awt/BorderLayout;
        //     8: dup            
        //     9: invokespecial   java/awt/BorderLayout.<init>:()V
        //    12: invokevirtual   org/newdawn/slick/AppletGameContainer.setLayout:(Ljava/awt/LayoutManager;)V
        //    15: aload_0         /* this */
        //    16: iconst_1       
        //    17: invokevirtual   org/newdawn/slick/AppletGameContainer.setIgnoreRepaint:(Z)V
        //    20: aload_0         /* this */
        //    21: ldc             "game"
        //    23: invokevirtual   org/newdawn/slick/AppletGameContainer.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    26: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //    29: invokevirtual   java/lang/Class.newInstance:()Ljava/lang/Object;
        //    32: checkcast       Lorg/newdawn/slick/Game;
        //    35: astore_1        /* game */
        //    36: aload_0         /* this */
        //    37: new             Lorg/newdawn/slick/AppletGameContainer$Container;
        //    40: dup            
        //    41: aload_0         /* this */
        //    42: aload_1         /* game */
        //    43: invokespecial   org/newdawn/slick/AppletGameContainer$Container.<init>:(Lorg/newdawn/slick/AppletGameContainer;Lorg/newdawn/slick/Game;)V
        //    46: putfield        org/newdawn/slick/AppletGameContainer.container:Lorg/newdawn/slick/AppletGameContainer$Container;
        //    49: aload_0         /* this */
        //    50: new             Lorg/newdawn/slick/AppletGameContainer$ContainerPanel;
        //    53: dup            
        //    54: aload_0         /* this */
        //    55: aload_0         /* this */
        //    56: getfield        org/newdawn/slick/AppletGameContainer.container:Lorg/newdawn/slick/AppletGameContainer$Container;
        //    59: invokespecial   org/newdawn/slick/AppletGameContainer$ContainerPanel.<init>:(Lorg/newdawn/slick/AppletGameContainer;Lorg/newdawn/slick/AppletGameContainer$Container;)V
        //    62: putfield        org/newdawn/slick/AppletGameContainer.canvas:Lorg/newdawn/slick/AppletGameContainer$ContainerPanel;
        //    65: aload_0         /* this */
        //    66: new             Lorg/newdawn/slick/llIII;
        //    69: dup            
        //    70: aload_0         /* this */
        //    71: invokespecial   org/newdawn/slick/llIII.<init>:(Lorg/newdawn/slick/AppletGameContainer;)V
        //    74: putfield        org/newdawn/slick/AppletGameContainer.displayParent:Ljava/awt/Canvas;
        //    77: aload_0         /* this */
        //    78: getfield        org/newdawn/slick/AppletGameContainer.displayParent:Ljava/awt/Canvas;
        //    81: aload_0         /* this */
        //    82: invokevirtual   org/newdawn/slick/AppletGameContainer.getWidth:()I
        //    85: aload_0         /* this */
        //    86: invokevirtual   org/newdawn/slick/AppletGameContainer.getHeight:()I
        //    89: invokevirtual   java/awt/Canvas.setSize:(II)V
        //    92: aload_0         /* this */
        //    93: aload_0         /* this */
        //    94: getfield        org/newdawn/slick/AppletGameContainer.displayParent:Ljava/awt/Canvas;
        //    97: invokevirtual   org/newdawn/slick/AppletGameContainer.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   100: pop            
        //   101: aload_0         /* this */
        //   102: getfield        org/newdawn/slick/AppletGameContainer.displayParent:Ljava/awt/Canvas;
        //   105: iconst_1       
        //   106: invokevirtual   java/awt/Canvas.setFocusable:(Z)V
        //   109: aload_0         /* this */
        //   110: getfield        org/newdawn/slick/AppletGameContainer.displayParent:Ljava/awt/Canvas;
        //   113: invokevirtual   java/awt/Canvas.requestFocus:()V
        //   116: aload_0         /* this */
        //   117: getfield        org/newdawn/slick/AppletGameContainer.displayParent:Ljava/awt/Canvas;
        //   120: iconst_1       
        //   121: invokevirtual   java/awt/Canvas.setIgnoreRepaint:(Z)V
        //   124: aload_0         /* this */
        //   125: iconst_1       
        //   126: invokevirtual   org/newdawn/slick/AppletGameContainer.setVisible:(Z)V
        //   129: goto            147
        //   132: astore_1        /* e */
        //   133: aload_1         /* e */
        //   134: invokestatic    org/newdawn/slick/util/Log.error:(Ljava/lang/Throwable;)V
        //   137: new             Ljava/lang/RuntimeException;
        //   140: dup            
        //   141: ldc             "Unable to create game container"
        //   143: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   146: athrow         
        //   147: return         
        //    StackMapTable: 00 02 F7 00 84 07 00 55 FC 00 0E 07 00 75
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  20     129    132    147    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public class ContainerPanel
    {
        private Container container;
        
        public ContainerPanel(final Container container) {
            this.container = container;
        }
        
        private void createDisplay() throws Exception {
            try {
                Display.create(new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0));
                AppletGameContainer.this.alphaSupport = true;
            }
            catch (Exception e) {
                AppletGameContainer.this.alphaSupport = false;
                Display.destroy();
                Display.create();
            }
        }
        
        public void start() throws Exception {
            Display.setParent(AppletGameContainer.this.displayParent);
            Display.setVSyncEnabled(true);
            try {
                this.createDisplay();
            }
            catch (LWJGLException e) {
                e.printStackTrace();
                Thread.sleep(1000L);
                this.createDisplay();
            }
            this.initGL();
            AppletGameContainer.this.displayParent.requestFocus();
            this.container.runloop();
        }
        
        protected void initGL() {
            try {
                InternalTextureLoader.get().clear();
                SoundStore.get().clear();
                this.container.initApplet();
            }
            catch (Exception e) {
                Log.error(e);
                this.container.stopApplet();
            }
        }
    }
    
    public class Container extends GameContainer
    {
        public Container(final Game game) {
            super(game);
            this.width = AppletGameContainer.this.getWidth();
            this.height = AppletGameContainer.this.getHeight();
        }
        
        public void initApplet() throws SlickException {
            this.initSystem();
            this.enterOrtho();
            try {
                this.getInput().initControllers();
            }
            catch (SlickException e) {
                Log.info("Controllers not available");
            }
            catch (Throwable e2) {
                Log.info("Controllers not available");
            }
            this.game.init(this);
            this.getDelta();
        }
        
        public boolean isRunning() {
            return this.running;
        }
        
        public void stopApplet() {
            this.running = false;
        }
        
        @Override
        public int getScreenHeight() {
            return 0;
        }
        
        @Override
        public int getScreenWidth() {
            return 0;
        }
        
        public boolean supportsAlphaInBackBuffer() {
            return AppletGameContainer.this.alphaSupport;
        }
        
        @Override
        public boolean hasFocus() {
            return true;
        }
        
        public Applet getApplet() {
            return AppletGameContainer.this;
        }
        
        @Override
        public void setIcon(final String ref) throws SlickException {
        }
        
        @Override
        public void setMouseGrabbed(final boolean grabbed) {
            Mouse.setGrabbed(grabbed);
        }
        
        @Override
        public boolean isMouseGrabbed() {
            return Mouse.isGrabbed();
        }
        
        @Override
        public void setMouseCursor(final String ref, final int hotSpotX, final int hotSpotY) throws SlickException {
            try {
                final Cursor cursor = CursorLoader.get().getCursor(ref, hotSpotX, hotSpotY);
                Mouse.setNativeCursor(cursor);
            }
            catch (Throwable e) {
                Log.error("Failed to load and apply cursor.", e);
                throw new SlickException("Failed to set mouse cursor", e);
            }
        }
        
        private int get2Fold(final int fold) {
            int ret;
            for (ret = 2; ret < fold; ret *= 2) {}
            return ret;
        }
        
        @Override
        public void setMouseCursor(final Image image, final int hotSpotX, final int hotSpotY) throws SlickException {
            try {
                final Image temp = new Image(this.get2Fold(image.getWidth()), this.get2Fold(image.getHeight()));
                final Graphics g = temp.getGraphics();
                final ByteBuffer buffer = BufferUtils.createByteBuffer(temp.getWidth() * temp.getHeight() * 4);
                g.drawImage(image.getFlippedCopy(false, true), 0.0f, 0.0f);
                g.flush();
                g.getArea(0, 0, temp.getWidth(), temp.getHeight(), buffer);
                final Cursor cursor = CursorLoader.get().getCursor(buffer, hotSpotX, hotSpotY, temp.getWidth(), temp.getHeight());
                Mouse.setNativeCursor(cursor);
            }
            catch (Throwable e) {
                Log.error("Failed to load and apply cursor.", e);
                throw new SlickException("Failed to set mouse cursor", e);
            }
        }
        
        @Override
        public void setIcons(final String[] refs) throws SlickException {
        }
        
        @Override
        public void setMouseCursor(final ImageData data, final int hotSpotX, final int hotSpotY) throws SlickException {
            try {
                final Cursor cursor = CursorLoader.get().getCursor(data, hotSpotX, hotSpotY);
                Mouse.setNativeCursor(cursor);
            }
            catch (Throwable e) {
                Log.error("Failed to load and apply cursor.", e);
                throw new SlickException("Failed to set mouse cursor", e);
            }
        }
        
        @Override
        public void setMouseCursor(final Cursor cursor, final int hotSpotX, final int hotSpotY) throws SlickException {
            try {
                Mouse.setNativeCursor(cursor);
            }
            catch (Throwable e) {
                Log.error("Failed to load and apply cursor.", e);
                throw new SlickException("Failed to set mouse cursor", e);
            }
        }
        
        @Override
        public void setDefaultMouseCursor() {
        }
        
        @Override
        public boolean isFullscreen() {
            return Display.isFullscreen();
        }
        
        @Override
        public void setFullscreen(final boolean fullscreen) throws SlickException {
            if (fullscreen == this.isFullscreen()) {
                return;
            }
            try {
                if (fullscreen) {
                    final int screenWidth = Display.getDisplayMode().getWidth();
                    final int screenHeight = Display.getDisplayMode().getHeight();
                    final float gameAspectRatio = this.width / (float)this.height;
                    final float screenAspectRatio = screenWidth / (float)screenHeight;
                    int newWidth;
                    int newHeight;
                    if (gameAspectRatio >= screenAspectRatio) {
                        newWidth = screenWidth;
                        newHeight = (int)(this.height / (this.width / (float)screenWidth));
                    }
                    else {
                        newWidth = (int)(this.width / (this.height / (float)screenHeight));
                        newHeight = screenHeight;
                    }
                    final int xoffset = (screenWidth - newWidth) / 2;
                    final int yoffset = (screenHeight - newHeight) / 2;
                    GL11.glViewport(xoffset, yoffset, newWidth, newHeight);
                    this.enterOrtho();
                    this.getInput().setOffset(-xoffset * (float)this.width / newWidth, -yoffset * (float)this.height / newHeight);
                    this.getInput().setScale(this.width / (float)newWidth, this.height / (float)newHeight);
                    this.width = screenWidth;
                    this.height = screenHeight;
                    Display.setFullscreen(true);
                }
                else {
                    this.getInput().setOffset(0.0f, 0.0f);
                    this.getInput().setScale(1.0f, 1.0f);
                    this.width = AppletGameContainer.this.getWidth();
                    this.height = AppletGameContainer.this.getHeight();
                    GL11.glViewport(0, 0, this.width, this.height);
                    this.enterOrtho();
                    Display.setFullscreen(false);
                }
            }
            catch (LWJGLException e) {
                Log.error((Throwable)e);
            }
        }
        
        public void runloop() throws Exception {
            while (this.running) {
                final int delta = this.getDelta();
                this.updateAndRender(delta);
                this.updateFPS();
                Display.update();
            }
            Display.destroy();
        }
    }
    
    public class ConsolePanel extends Panel
    {
        TextArea textArea;
        
        public ConsolePanel(final Exception e) {
            this.textArea = new TextArea();
            this.setLayout(new BorderLayout());
            this.setBackground(Color.black);
            this.setForeground(Color.white);
            final Font consoleFont = new Font("Arial", 1, 14);
            final Label slickLabel = new Label("SLICK CONSOLE", 1);
            slickLabel.setFont(consoleFont);
            this.add(slickLabel, "First");
            final StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            this.textArea.setText(sw.toString());
            this.textArea.setEditable(false);
            this.add(this.textArea, "Center");
            this.add(new Panel(), "Before");
            this.add(new Panel(), "After");
            final Panel bottomPanel = new Panel();
            bottomPanel.setLayout(new GridLayout(0, 1));
            final Label infoLabel1 = new Label("An error occured while running the applet.", 1);
            final Label infoLabel2 = new Label("Plese contact support to resolve this issue.", 1);
            infoLabel1.setFont(consoleFont);
            infoLabel2.setFont(consoleFont);
            bottomPanel.add(infoLabel1);
            bottomPanel.add(infoLabel2);
            this.add(bottomPanel, "Last");
        }
    }
}
