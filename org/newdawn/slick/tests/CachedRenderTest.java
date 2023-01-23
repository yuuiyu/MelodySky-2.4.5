//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class CachedRenderTest extends BasicGame
{
    private Runnable operations;
    private CachedRender cached;
    private boolean drawCached;
    
    public CachedRenderTest() {
        super("Cached Render Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        class lIIIl implements Runnable
        {
            final /* synthetic */ GameContainer val$container;
            final /* synthetic */ CachedRenderTest this$0;
            
            lIIIl(final CachedRenderTest this$0, final GameContainer val$container) {
                this.this$0 = this$0;
                this.val$container = val$container;
            }
            
            @Override
            public void run() {
                for (int i = 0; i < 100; ++i) {
                    final int c = i + 100;
                    this.val$container.getGraphics().setColor(new Color(c, c, c, c));
                    this.val$container.getGraphics().drawOval((float)(i * 5 + 50), (float)(i * 3 + 50), 100.0f, 100.0f);
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Lorg/newdawn/slick/tests/lIIIl;
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: aload_1         /* container */
        //     7: invokespecial   org/newdawn/slick/tests/lIIIl.<init>:(Lorg/newdawn/slick/tests/CachedRenderTest;Lorg/newdawn/slick/GameContainer;)V
        //    10: putfield        org/newdawn/slick/tests/CachedRenderTest.operations:Ljava/lang/Runnable;
        //    13: aload_0         /* this */
        //    14: new             Lorg/newdawn/slick/CachedRender;
        //    17: dup            
        //    18: aload_0         /* this */
        //    19: getfield        org/newdawn/slick/tests/CachedRenderTest.operations:Ljava/lang/Runnable;
        //    22: invokespecial   org/newdawn/slick/CachedRender.<init>:(Ljava/lang/Runnable;)V
        //    25: putfield        org/newdawn/slick/tests/CachedRenderTest.cached:Lorg/newdawn/slick/CachedRender;
        //    28: return         
        //    Exceptions:
        //  throws org.newdawn.slick.SlickException
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        if (container.getInput().isKeyPressed(57)) {
            this.drawCached = !this.drawCached;
        }
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawString("Press space to toggle caching", 10.0f, 130.0f);
        if (this.drawCached) {
            g.drawString("Drawing from cache", 10.0f, 100.0f);
            this.cached.render();
        }
        else {
            g.drawString("Drawing direct", 10.0f, 100.0f);
            this.operations.run();
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new CachedRenderTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
