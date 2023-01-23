//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class PackedSheetTest extends BasicGame
{
    private PackedSpriteSheet sheet;
    private GameContainer container;
    private float r;
    private Image rocket;
    private Animation runner;
    private float ang;
    
    public PackedSheetTest() {
        super("Packed Sprite Sheet Test");
        this.r = -500.0f;
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.container = container;
        this.sheet = new PackedSpriteSheet("testdata/testpack.def", 2);
        this.rocket = this.sheet.getSprite("rocket");
        final SpriteSheet anim = this.sheet.getSpriteSheet("runner");
        this.runner = new Animation();
        for (int y = 0; y < 2; ++y) {
            for (int x = 0; x < 6; ++x) {
                this.runner.addFrame(anim.getSprite(x, y), 50);
            }
        }
    }
    
    public void render(final GameContainer container, final Graphics g) {
        this.rocket.draw((float)(int)this.r, 100.0f);
        this.runner.draw(250.0f, 250.0f);
        g.scale(1.2f, 1.2f);
        this.runner.draw(250.0f, 250.0f);
        g.scale(1.2f, 1.2f);
        this.runner.draw(250.0f, 250.0f);
        g.resetTransform();
        g.rotate(670.0f, 470.0f, this.ang);
        this.sheet.getSprite("floppy").draw(600.0f, 400.0f);
    }
    
    public void update(final GameContainer container, final int delta) {
        this.r += delta * 0.4f;
        if (this.r > 900.0f) {
            this.r = -500.0f;
        }
        this.ang += delta * 0.1f;
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new PackedSheetTest());
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
    }
}
