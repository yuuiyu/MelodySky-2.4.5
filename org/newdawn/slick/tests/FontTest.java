//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.util.*;
import org.newdawn.slick.*;

public class FontTest extends BasicGame
{
    private AngelCodeFont font;
    private AngelCodeFont font2;
    private Image image;
    private static AppGameContainer container;
    
    public FontTest() {
        super("Font Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
        this.font2 = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
        this.image = new Image("testdata/demo2_00.tga", false);
    }
    
    public void render(final GameContainer container, final Graphics g) {
        this.font.drawString(80.0f, 5.0f, "A Font Example", Color.red);
        this.font.drawString(100.0f, 32.0f, "We - AV - Here is a more complete line that hopefully");
        this.font.drawString(100.0f, (float)(36 + this.font.getHeight("We Here is a more complete line that hopefully")), "will show some kerning.");
        this.font2.drawString(80.0f, 85.0f, "A Font Example", Color.red);
        this.font2.drawString(100.0f, 132.0f, "We - AV - Here is a more complete line that hopefully");
        this.font2.drawString(100.0f, (float)(136 + this.font2.getHeight("We - Here is a more complete line that hopefully")), "will show some kerning.");
        this.image.draw(100.0f, 400.0f);
        final String testStr = "Testing Font";
        this.font2.drawString(100.0f, 300.0f, testStr);
        g.setColor(Color.white);
        g.drawRect(100.0f, (float)(300 + this.font2.getYOffset(testStr)), (float)this.font2.getWidth(testStr), (float)(this.font2.getHeight(testStr) - this.font2.getYOffset(testStr)));
        this.font.drawString(500.0f, 300.0f, testStr);
        g.setColor(Color.white);
        g.drawRect(500.0f, (float)(300 + this.font.getYOffset(testStr)), (float)this.font.getWidth(testStr), (float)(this.font.getHeight(testStr) - this.font.getYOffset(testStr)));
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 1) {
            System.exit(0);
        }
        if (key == 57) {
            try {
                FontTest.container.setDisplayMode(640, 480, false);
            }
            catch (SlickException e) {
                Log.error((Throwable)e);
            }
        }
    }
    
    public static void main(final String[] argv) {
        try {
            (FontTest.container = new AppGameContainer((Game)new FontTest())).setDisplayMode(800, 600, false);
            FontTest.container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
