//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.state.transition;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.renderer.*;

public class HorizontalSplitTransition implements Transition
{
    protected static SGL GL;
    private GameState prev;
    private float offset;
    private boolean finish;
    private Color background;
    
    public HorizontalSplitTransition() {
    }
    
    public HorizontalSplitTransition(final Color background) {
        this.background = background;
    }
    
    @Override
    public void init(final GameState firstState, final GameState secondState) {
        this.prev = secondState;
    }
    
    @Override
    public boolean isComplete() {
        return this.finish;
    }
    
    @Override
    public void postRender(final StateBasedGame game, final GameContainer container, final Graphics g) throws SlickException {
        g.translate(-this.offset, 0.0f);
        g.setClip((int)(-this.offset), 0, container.getWidth() / 2, container.getHeight());
        if (this.background != null) {
            final Color c = g.getColor();
            g.setColor(this.background);
            g.fillRect(0.0f, 0.0f, (float)container.getWidth(), (float)container.getHeight());
            g.setColor(c);
        }
        HorizontalSplitTransition.GL.glPushMatrix();
        this.prev.render(container, game, g);
        HorizontalSplitTransition.GL.glPopMatrix();
        g.clearClip();
        g.translate(this.offset * 2.0f, 0.0f);
        g.setClip((int)(container.getWidth() / 2 + this.offset), 0, container.getWidth() / 2, container.getHeight());
        if (this.background != null) {
            final Color c = g.getColor();
            g.setColor(this.background);
            g.fillRect(0.0f, 0.0f, (float)container.getWidth(), (float)container.getHeight());
            g.setColor(c);
        }
        HorizontalSplitTransition.GL.glPushMatrix();
        this.prev.render(container, game, g);
        HorizontalSplitTransition.GL.glPopMatrix();
        g.clearClip();
        g.translate(-this.offset, 0.0f);
    }
    
    @Override
    public void preRender(final StateBasedGame game, final GameContainer container, final Graphics g) throws SlickException {
    }
    
    @Override
    public void update(final StateBasedGame game, final GameContainer container, final int delta) throws SlickException {
        this.offset += delta * 1.0f;
        if (this.offset > container.getWidth() / 2) {
            this.finish = true;
        }
    }
    
    static {
        HorizontalSplitTransition.GL = Renderer.get();
    }
}
