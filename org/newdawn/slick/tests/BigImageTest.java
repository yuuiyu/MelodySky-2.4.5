//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class BigImageTest extends BasicGame
{
    private Image original;
    private Image image;
    private Image imageX;
    private Image imageY;
    private Image sub;
    private Image scaledSub;
    private float x;
    private float y;
    private float ang;
    private SpriteSheet bigSheet;
    
    public BigImageTest() {
        super("Big Image Test");
        this.ang = 30.0f;
    }
    
    public void init(final GameContainer container) throws SlickException {
        final BigImage bigImage = new BigImage("testdata/bigimage.tga", 2, 512);
        this.image = (Image)bigImage;
        this.original = (Image)bigImage;
        this.sub = this.image.getSubImage(210, 210, 200, 130);
        this.scaledSub = this.sub.getScaledCopy(2.0f);
        this.image = this.image.getScaledCopy(0.3f);
        this.imageX = this.image.getFlippedCopy(true, false);
        this.imageY = this.imageX.getFlippedCopy(true, true);
        this.bigSheet = new SpriteSheet(this.original, 16, 16);
    }
    
    public void render(final GameContainer container, final Graphics g) {
        this.original.draw(0.0f, 0.0f, new Color(1.0f, 1.0f, 1.0f, 0.4f));
        this.image.draw(this.x, this.y);
        this.imageX.draw(this.x + 400.0f, this.y);
        this.imageY.draw(this.x, this.y + 300.0f);
        this.scaledSub.draw(this.x + 300.0f, this.y + 300.0f);
        this.bigSheet.getSprite(7, 5).draw(50.0f, 10.0f);
        g.setColor(Color.white);
        g.drawRect(50.0f, 10.0f, 64.0f, 64.0f);
        g.rotate(this.x + 400.0f, this.y + 165.0f, this.ang);
        g.drawImage(this.sub, this.x + 300.0f, this.y + 100.0f);
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new BigImageTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        this.ang += delta * 0.1f;
        if (container.getInput().isKeyDown(203)) {
            this.x -= delta * 0.1f;
        }
        if (container.getInput().isKeyDown(205)) {
            this.x += delta * 0.1f;
        }
        if (container.getInput().isKeyDown(200)) {
            this.y -= delta * 0.1f;
        }
        if (container.getInput().isKeyDown(208)) {
            this.y += delta * 0.1f;
        }
    }
}
