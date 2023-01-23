//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class AnimationTest extends BasicGame
{
    private Animation animation;
    private Animation limited;
    private Animation manual;
    private Animation pingPong;
    private GameContainer container;
    private int start;
    
    public AnimationTest() {
        super("Animation Test");
        this.start = 5000;
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.container = container;
        final SpriteSheet sheet = new SpriteSheet("testdata/homeranim.png", 36, 65);
        this.animation = new Animation();
        for (int i = 0; i < 8; ++i) {
            this.animation.addFrame(sheet.getSprite(i, 0), 150);
        }
        this.limited = new Animation();
        for (int i = 0; i < 8; ++i) {
            this.limited.addFrame(sheet.getSprite(i, 0), 150);
        }
        this.limited.stopAt(7);
        this.manual = new Animation(false);
        for (int i = 0; i < 8; ++i) {
            this.manual.addFrame(sheet.getSprite(i, 0), 150);
        }
        (this.pingPong = new Animation(sheet, 0, 0, 7, 0, true, 150, true)).setPingPong(true);
        container.getGraphics().setBackground(new Color(0.4f, 0.6f, 0.6f));
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.drawString("Space to restart() animation", 100.0f, 50.0f);
        g.drawString("Til Limited animation: " + this.start, 100.0f, 500.0f);
        g.drawString("Hold 1 to move the manually animated", 100.0f, 70.0f);
        g.drawString("PingPong Frame:" + this.pingPong.getFrame(), 600.0f, 70.0f);
        g.scale(-1.0f, 1.0f);
        this.animation.draw(-100.0f, 100.0f);
        this.animation.draw(-200.0f, 100.0f, 144.0f, 260.0f);
        if (this.start < 0) {
            this.limited.draw(-400.0f, 100.0f, 144.0f, 260.0f);
        }
        this.manual.draw(-600.0f, 100.0f, 144.0f, 260.0f);
        this.pingPong.draw(-700.0f, 100.0f, 72.0f, 130.0f);
    }
    
    public void update(final GameContainer container, final int delta) {
        if (container.getInput().isKeyDown(2)) {
            this.manual.update((long)delta);
        }
        if (this.start >= 0) {
            this.start -= delta;
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new AnimationTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 1) {
            this.container.exit();
        }
        if (key == 57) {
            this.limited.restart();
        }
    }
}
