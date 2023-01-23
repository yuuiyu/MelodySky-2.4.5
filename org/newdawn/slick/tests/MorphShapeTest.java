//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class MorphShapeTest extends BasicGame
{
    private Shape a;
    private Shape b;
    private Shape c;
    private MorphShape morph;
    private float time;
    
    public MorphShapeTest() {
        super("MorphShapeTest");
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.a = (Shape)new Rectangle(100.0f, 100.0f, 50.0f, 200.0f);
        this.a = this.a.transform(Transform.createRotateTransform(0.1f, 100.0f, 100.0f));
        this.b = (Shape)new Rectangle(200.0f, 100.0f, 50.0f, 200.0f);
        this.b = this.b.transform(Transform.createRotateTransform(-0.6f, 100.0f, 100.0f));
        this.c = (Shape)new Rectangle(300.0f, 100.0f, 50.0f, 200.0f);
        this.c = this.c.transform(Transform.createRotateTransform(-0.2f, 100.0f, 100.0f));
        (this.morph = new MorphShape(this.a)).addShape(this.b);
        this.morph.addShape(this.c);
        container.setVSync(true);
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        this.time += delta * 0.001f;
        this.morph.setMorphTime(this.time);
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.setColor(Color.green);
        g.draw(this.a);
        g.setColor(Color.red);
        g.draw(this.b);
        g.setColor(Color.blue);
        g.draw(this.c);
        g.setColor(Color.white);
        g.draw((Shape)this.morph);
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new MorphShapeTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
