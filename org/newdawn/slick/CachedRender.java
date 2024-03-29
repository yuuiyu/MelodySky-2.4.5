//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;

public class CachedRender
{
    protected static SGL GL;
    private Runnable runnable;
    private int list;
    
    public CachedRender(final Runnable runnable) {
        this.list = -1;
        this.runnable = runnable;
        this.build();
    }
    
    private void build() {
        if (this.list == -1) {
            this.list = CachedRender.GL.glGenLists(1);
            SlickCallable.enterSafeBlock();
            CachedRender.GL.glNewList(this.list, 4864);
            this.runnable.run();
            CachedRender.GL.glEndList();
            SlickCallable.leaveSafeBlock();
            return;
        }
        throw new RuntimeException("Attempt to build the display list more than once in CachedRender");
    }
    
    public void render() {
        if (this.list == -1) {
            throw new RuntimeException("Attempt to render cached operations that have been destroyed");
        }
        SlickCallable.enterSafeBlock();
        CachedRender.GL.glCallList(this.list);
        SlickCallable.leaveSafeBlock();
    }
    
    public void destroy() {
        CachedRender.GL.glDeleteLists(this.list, 1);
        this.list = -1;
    }
    
    static {
        CachedRender.GL = Renderer.get();
    }
}
