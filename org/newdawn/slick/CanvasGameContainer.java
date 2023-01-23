//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.awt.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.util.*;

public class CanvasGameContainer extends Canvas
{
    protected Container container;
    protected Game game;
    
    public CanvasGameContainer(final Game game) throws SlickException {
        this(game, false);
    }
    
    public CanvasGameContainer(final Game game, final boolean shared) throws SlickException {
        this.game = game;
        this.setIgnoreRepaint(true);
        this.requestFocus();
        this.setSize(500, 500);
        (this.container = new Container(game, shared)).setForceExit(false);
    }
    
    public void start() throws SlickException {
        class llIlI implements Runnable
        {
            final /* synthetic */ CanvasGameContainer this$0;
            
            llIlI(final CanvasGameContainer this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                try {
                    Input.disableControllers();
                    try {
                        Display.setParent((Canvas)this.this$0);
                    }
                    catch (LWJGLException e) {
                        throw new SlickException("Failed to setParent of canvas", (Throwable)e);
                    }
                    this.this$0.container.setup();
                    this.this$0.scheduleUpdate();
                }
                catch (SlickException e2) {
                    e2.printStackTrace();
                    System.exit(0);
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: invokespecial   org/newdawn/slick/llIlI.<init>:(Lorg/newdawn/slick/CanvasGameContainer;)V
        //     8: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    11: return         
        //    Exceptions:
        //  throws org.newdawn.slick.SlickException
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void scheduleUpdate() {
        class lIIlI implements Runnable
        {
            final /* synthetic */ CanvasGameContainer this$0;
            
            lIIlI(final CanvasGameContainer this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                try {
                    this.this$0.container.gameLoop();
                }
                catch (SlickException e) {
                    e.printStackTrace();
                }
                this.this$0.container.checkDimensions();
                this.this$0.scheduleUpdate();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/newdawn/slick/CanvasGameContainer.isVisible:()Z
        //     4: ifne            8
        //     7: return         
        //     8: new             Lorg/newdawn/slick/lIIlI;
        //    11: dup            
        //    12: aload_0         /* this */
        //    13: invokespecial   org/newdawn/slick/lIIlI.<init>:(Lorg/newdawn/slick/CanvasGameContainer;)V
        //    16: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    19: return         
        //    StackMapTable: 00 01 08
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void dispose() {
    }
    
    private class Container extends AppGameContainer
    {
        public Container(final Game game, final boolean shared) throws SlickException {
            super(game, CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
            this.width = CanvasGameContainer.this.getWidth();
            this.height = CanvasGameContainer.this.getHeight();
            if (shared) {
                enableSharedContext();
            }
        }
        
        protected void updateFPS() {
            super.updateFPS();
        }
        
        protected boolean running() {
            return super.running() && CanvasGameContainer.this.isDisplayable();
        }
        
        public int getHeight() {
            return CanvasGameContainer.this.getHeight();
        }
        
        public int getWidth() {
            return CanvasGameContainer.this.getWidth();
        }
        
        public void checkDimensions() {
            if (this.width == CanvasGameContainer.this.getWidth()) {
                if (this.height == CanvasGameContainer.this.getHeight()) {
                    return;
                }
            }
            try {
                this.setDisplayMode(CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
            }
            catch (SlickException e) {
                Log.error(e);
            }
        }
    }
}
