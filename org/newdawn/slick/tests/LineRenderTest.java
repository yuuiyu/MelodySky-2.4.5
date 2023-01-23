//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.*;

public class LineRenderTest extends BasicGame
{
    private Polygon polygon;
    private Path path;
    private float width;
    private boolean antialias;
    
    public LineRenderTest() {
        super("LineRenderTest");
        this.polygon = new Polygon();
        this.path = new Path(100.0f, 100.0f);
        this.width = 10.0f;
        this.antialias = true;
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.polygon.addPoint(100.0f, 100.0f);
        this.polygon.addPoint(200.0f, 80.0f);
        this.polygon.addPoint(320.0f, 150.0f);
        this.polygon.addPoint(230.0f, 210.0f);
        this.polygon.addPoint(170.0f, 260.0f);
        this.path.curveTo(200.0f, 200.0f, 200.0f, 100.0f, 100.0f, 200.0f);
        this.path.curveTo(400.0f, 100.0f, 400.0f, 200.0f, 200.0f, 100.0f);
        this.path.curveTo(500.0f, 500.0f, 400.0f, 200.0f, 200.0f, 100.0f);
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        if (container.getInput().isKeyPressed(57)) {
            this.antialias = !this.antialias;
        }
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.setAntiAlias(this.antialias);
        g.setLineWidth(50.0f);
        g.setColor(Color.red);
        g.draw((Shape)this.path);
    }
    
    public static void main(final String[] argv) {
        try {
            Renderer.setLineStripRenderer(4);
            Renderer.getLineStripRenderer().setLineCaps(true);
            final AppGameContainer container = new AppGameContainer((Game)new LineRenderTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
