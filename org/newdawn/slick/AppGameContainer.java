//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import org.lwjgl.input.*;
import java.nio.*;
import org.newdawn.slick.openal.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.openal.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.opengl.*;
import java.security.*;
import java.io.*;

public class AppGameContainer extends GameContainer
{
    protected DisplayMode originalDisplayMode;
    protected DisplayMode targetDisplayMode;
    protected boolean updateOnlyOnVisible;
    protected boolean alphaSupport;
    
    public AppGameContainer(final Game game) throws SlickException {
        this(game, 640, 480, false);
    }
    
    public AppGameContainer(final Game game, final int width, final int height, final boolean fullscreen) throws SlickException {
        super(game);
        this.updateOnlyOnVisible = true;
        this.alphaSupport = false;
        this.originalDisplayMode = Display.getDisplayMode();
        this.setDisplayMode(width, height, fullscreen);
    }
    
    public boolean supportsAlphaInBackBuffer() {
        return this.alphaSupport;
    }
    
    public void setTitle(final String title) {
        Display.setTitle(title);
    }
    
    public void setDisplayMode(final int width, final int height, final boolean fullscreen) throws SlickException {
        if (this.width == width && this.height == height && this.isFullscreen() == fullscreen) {
            return;
        }
        try {
            this.targetDisplayMode = null;
            if (fullscreen) {
                final DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;
                for (int i = 0; i < modes.length; ++i) {
                    final DisplayMode current = modes[i];
                    if (current.getWidth() == width && current.getHeight() == height) {
                        if ((this.targetDisplayMode == null || current.getFrequency() >= freq) && (this.targetDisplayMode == null || current.getBitsPerPixel() > this.targetDisplayMode.getBitsPerPixel())) {
                            this.targetDisplayMode = current;
                            freq = this.targetDisplayMode.getFrequency();
                        }
                        if (current.getBitsPerPixel() == this.originalDisplayMode.getBitsPerPixel() && current.getFrequency() == this.originalDisplayMode.getFrequency()) {
                            this.targetDisplayMode = current;
                            break;
                        }
                    }
                }
            }
            else {
                this.targetDisplayMode = new DisplayMode(width, height);
            }
            if (this.targetDisplayMode == null) {
                throw new SlickException("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
            }
            this.width = width;
            this.height = height;
            Display.setDisplayMode(this.targetDisplayMode);
            Display.setFullscreen(fullscreen);
            if (Display.isCreated()) {
                this.initGL();
                this.enterOrtho();
            }
            if (this.targetDisplayMode.getBitsPerPixel() == 16) {
                InternalTextureLoader.get().set16BitMode();
            }
        }
        catch (LWJGLException e) {
            throw new SlickException("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen, (Throwable)e);
        }
        this.getDelta();
    }
    
    @Override
    public boolean isFullscreen() {
        return Display.isFullscreen();
    }
    
    @Override
    public void setFullscreen(final boolean fullscreen) throws SlickException {
        if (this.isFullscreen() == fullscreen) {
            return;
        }
        Label_0062: {
            if (!fullscreen) {
                try {
                    Display.setFullscreen(fullscreen);
                    break Label_0062;
                }
                catch (LWJGLException e) {
                    throw new SlickException("Unable to set fullscreen=" + fullscreen, (Throwable)e);
                }
            }
            this.setDisplayMode(this.width, this.height, fullscreen);
        }
        this.getDelta();
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
            final Cursor cursor = CursorLoader.get().getCursor(buffer, hotSpotX, hotSpotY, temp.getWidth(), image.getHeight());
            Mouse.setNativeCursor(cursor);
        }
        catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        }
    }
    
    @Override
    public void reinit() throws SlickException {
        InternalTextureLoader.get().clear();
        SoundStore.get().clear();
        this.initSystem();
        this.enterOrtho();
        try {
            this.game.init(this);
        }
        catch (SlickException e) {
            Log.error(e);
            this.running = false;
        }
    }
    
    private void tryCreateDisplay(final PixelFormat format) throws LWJGLException {
        if (AppGameContainer.SHARED_DRAWABLE == null) {
            Display.create(format);
        }
        else {
            Display.create(format, AppGameContainer.SHARED_DRAWABLE);
        }
    }
    
    public void start() throws SlickException {
        try {
            this.setup();
            this.getDelta();
            while (this.running()) {
                this.gameLoop();
            }
        }
        finally {
            this.destroy();
        }
        if (this.forceExit) {
            System.exit(0);
        }
    }
    
    protected void setup() throws SlickException {
        class lIIll implements PrivilegedAction
        {
            final /* synthetic */ AppGameContainer this$0;
            
            lIIll(final AppGameContainer this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public Object run() {
                try {
                    final PixelFormat format = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0, this.this$0.samples);
                    this.this$0.tryCreateDisplay(format);
                    this.this$0.supportsMultiSample = true;
                }
                catch (Exception e4) {
                    Display.destroy();
                    try {
                        final PixelFormat format2 = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0);
                        this.this$0.tryCreateDisplay(format2);
                        this.this$0.alphaSupport = false;
                    }
                    catch (Exception e5) {
                        Display.destroy();
                        try {
                            this.this$0.tryCreateDisplay(new PixelFormat());
                        }
                        catch (Exception e3) {
                            Log.error(e3);
                        }
                    }
                }
                return null;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/newdawn/slick/AppGameContainer.targetDisplayMode:Lorg/lwjgl/opengl/DisplayMode;
        //     4: ifnonnull       18
        //     7: aload_0         /* this */
        //     8: sipush          640
        //    11: sipush          480
        //    14: iconst_0       
        //    15: invokevirtual   org/newdawn/slick/AppGameContainer.setDisplayMode:(IIZ)V
        //    18: aload_0         /* this */
        //    19: getfield        org/newdawn/slick/AppGameContainer.game:Lorg/newdawn/slick/Game;
        //    22: invokeinterface org/newdawn/slick/Game.getTitle:()Ljava/lang/String;
        //    27: invokestatic    org/lwjgl/opengl/Display.setTitle:(Ljava/lang/String;)V
        //    30: new             Ljava/lang/StringBuilder;
        //    33: dup            
        //    34: invokespecial   java/lang/StringBuilder.<init>:()V
        //    37: ldc_w           "LWJGL Version: "
        //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    43: invokestatic    org/lwjgl/Sys.getVersion:()Ljava/lang/String;
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    49: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    52: invokestatic    org/newdawn/slick/util/Log.info:(Ljava/lang/String;)V
        //    55: new             Ljava/lang/StringBuilder;
        //    58: dup            
        //    59: invokespecial   java/lang/StringBuilder.<init>:()V
        //    62: ldc_w           "OriginalDisplayMode: "
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: aload_0         /* this */
        //    69: getfield        org/newdawn/slick/AppGameContainer.originalDisplayMode:Lorg/lwjgl/opengl/DisplayMode;
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    75: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    78: invokestatic    org/newdawn/slick/util/Log.info:(Ljava/lang/String;)V
        //    81: new             Ljava/lang/StringBuilder;
        //    84: dup            
        //    85: invokespecial   java/lang/StringBuilder.<init>:()V
        //    88: ldc_w           "TargetDisplayMode: "
        //    91: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    94: aload_0         /* this */
        //    95: getfield        org/newdawn/slick/AppGameContainer.targetDisplayMode:Lorg/lwjgl/opengl/DisplayMode;
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   101: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   104: invokestatic    org/newdawn/slick/util/Log.info:(Ljava/lang/String;)V
        //   107: new             Lorg/newdawn/slick/lIIll;
        //   110: dup            
        //   111: aload_0         /* this */
        //   112: invokespecial   org/newdawn/slick/lIIll.<init>:(Lorg/newdawn/slick/AppGameContainer;)V
        //   115: invokestatic    java/security/AccessController.doPrivileged:(Ljava/security/PrivilegedAction;)Ljava/lang/Object;
        //   118: pop            
        //   119: invokestatic    org/lwjgl/opengl/Display.isCreated:()Z
        //   122: ifne            136
        //   125: new             Lorg/newdawn/slick/SlickException;
        //   128: dup            
        //   129: ldc_w           "Failed to initialise the LWJGL display"
        //   132: invokespecial   org/newdawn/slick/SlickException.<init>:(Ljava/lang/String;)V
        //   135: athrow         
        //   136: aload_0         /* this */
        //   137: invokevirtual   org/newdawn/slick/AppGameContainer.initSystem:()V
        //   140: aload_0         /* this */
        //   141: invokevirtual   org/newdawn/slick/AppGameContainer.enterOrtho:()V
        //   144: aload_0         /* this */
        //   145: invokevirtual   org/newdawn/slick/AppGameContainer.getInput:()Lorg/newdawn/slick/Input;
        //   148: invokevirtual   org/newdawn/slick/Input.initControllers:()V
        //   151: goto            171
        //   154: astore_1        /* e */
        //   155: ldc_w           "Controllers not available"
        //   158: invokestatic    org/newdawn/slick/util/Log.info:(Ljava/lang/String;)V
        //   161: goto            171
        //   164: astore_1        /* e */
        //   165: ldc_w           "Controllers not available"
        //   168: invokestatic    org/newdawn/slick/util/Log.info:(Ljava/lang/String;)V
        //   171: aload_0         /* this */
        //   172: getfield        org/newdawn/slick/AppGameContainer.game:Lorg/newdawn/slick/Game;
        //   175: aload_0         /* this */
        //   176: invokeinterface org/newdawn/slick/Game.init:(Lorg/newdawn/slick/GameContainer;)V
        //   181: goto            194
        //   184: astore_1        /* e */
        //   185: aload_1         /* e */
        //   186: invokestatic    org/newdawn/slick/util/Log.error:(Ljava/lang/Throwable;)V
        //   189: aload_0         /* this */
        //   190: iconst_0       
        //   191: putfield        org/newdawn/slick/AppGameContainer.running:Z
        //   194: return         
        //    Exceptions:
        //  throws org.newdawn.slick.SlickException
        //    StackMapTable: 00 07 12 FB 00 75 51 07 00 16 49 07 00 A9 06 4C 07 00 16 09
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  144    151    154    164    Lorg/newdawn/slick/SlickException;
        //  144    151    164    171    Ljava/lang/Throwable;
        //  171    181    184    194    Lorg/newdawn/slick/SlickException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void gameLoop() throws SlickException {
        final int delta = this.getDelta();
        if (!Display.isVisible() && this.updateOnlyOnVisible) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        else {
            try {
                this.updateAndRender(delta);
            }
            catch (SlickException e) {
                Log.error(e);
                this.running = false;
                return;
            }
        }
        this.updateFPS();
        Display.update();
        if (Display.isCloseRequested() && this.game.closeRequested()) {
            this.running = false;
        }
    }
    
    @Override
    public void setUpdateOnlyWhenVisible(final boolean updateOnlyWhenVisible) {
        this.updateOnlyOnVisible = updateOnlyWhenVisible;
    }
    
    @Override
    public boolean isUpdatingOnlyWhenVisible() {
        return this.updateOnlyOnVisible;
    }
    
    @Override
    public void setIcon(final String ref) throws SlickException {
        this.setIcons(new String[] { ref });
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
    public boolean hasFocus() {
        return Display.isActive();
    }
    
    @Override
    public int getScreenHeight() {
        return this.originalDisplayMode.getHeight();
    }
    
    @Override
    public int getScreenWidth() {
        return this.originalDisplayMode.getWidth();
    }
    
    public void destroy() {
        Display.destroy();
        AL.destroy();
    }
    
    @Override
    public void setIcons(final String[] refs) throws SlickException {
        final ByteBuffer[] bufs = new ByteBuffer[refs.length];
        for (int i = 0; i < refs.length; ++i) {
            boolean flip = true;
            LoadableImageData data;
            if (refs[i].endsWith(".tga")) {
                data = new TGAImageData();
            }
            else {
                flip = false;
                data = new ImageIOImageData();
            }
            try {
                bufs[i] = data.loadImage(ResourceLoader.getResourceAsStream(refs[i]), flip, false, null);
            }
            catch (Exception e) {
                Log.error(e);
                throw new SlickException("Failed to set the icon");
            }
        }
        Display.setIcon(bufs);
    }
    
    @Override
    public void setDefaultMouseCursor() {
        try {
            Mouse.setNativeCursor((Cursor)null);
        }
        catch (LWJGLException e) {
            Log.error("Failed to reset mouse cursor", (Throwable)e);
        }
    }
    
    static {
        AccessController.doPrivileged((PrivilegedAction<Object>)new lIIII());
    }
    
    private class NullOutputStream extends OutputStream
    {
        @Override
        public void write(final int b) throws IOException {
        }
    }
}
