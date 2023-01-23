//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.fills.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class GradientImageTest extends BasicGame
{
    private Image image1;
    private Image image2;
    private GradientFill fill;
    private Shape shape;
    private Polygon poly;
    private GameContainer container;
    private float ang;
    private boolean rotating;
    
    public GradientImageTest() {
        super("Gradient Image Test");
        this.rotating = false;
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.container = container;
        this.image1 = new Image("testdata/grass.png");
        this.image2 = new Image("testdata/rocks.png");
        this.fill = new GradientFill(-64.0f, 0.0f, new Color(1.0f, 1.0f, 1.0f, 1.0f), 64.0f, 0.0f, new Color(0, 0, 0, 0));
        this.shape = (Shape)new Rectangle(336.0f, 236.0f, 128.0f, 128.0f);
        (this.poly = new Polygon()).addPoint(320.0f, 220.0f);
        this.poly.addPoint(350.0f, 200.0f);
        this.poly.addPoint(450.0f, 200.0f);
        this.poly.addPoint(480.0f, 220.0f);
        this.poly.addPoint(420.0f, 400.0f);
        this.poly.addPoint(400.0f, 390.0f);
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.drawString("R - Toggle Rotationg", 10.0f, 50.0f);
        g.drawImage(this.image1, 100.0f, 236.0f);
        g.drawImage(this.image2, 600.0f, 236.0f);
        g.translate(0.0f, -150.0f);
        g.rotate(400.0f, 300.0f, this.ang);
        g.texture(this.shape, this.image2);
        g.texture(this.shape, this.image1, (ShapeFill)this.fill);
        g.resetTransform();
        g.translate(0.0f, 150.0f);
        g.rotate(400.0f, 300.0f, this.ang);
        g.texture((Shape)this.poly, this.image2);
        g.texture((Shape)this.poly, this.image1, (ShapeFill)this.fill);
        g.resetTransform();
    }
    
    public void update(final GameContainer container, final int delta) {
        if (this.rotating) {
            this.ang += delta * 0.1f;
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new GradientImageTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 19) {
            this.rotating = !this.rotating;
        }
        if (key == 1) {
            this.container.exit();
        }
    }
}
