//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.util.*;
import org.newdawn.slick.*;

public class SpriteSheetFontTest extends BasicGame
{
    private Font font;
    private static AppGameContainer container;
    
    public SpriteSheetFontTest() {
        super("SpriteSheetFont Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        final SpriteSheet sheet = new SpriteSheet("testdata/spriteSheetFont.png", 32, 32);
        this.font = (Font)new SpriteSheetFont(sheet, ' ');
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.setBackground(Color.gray);
        this.font.drawString(80.0f, 5.0f, "A FONT EXAMPLE", Color.red);
        this.font.drawString(100.0f, 50.0f, "A MORE COMPLETE LINE");
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 1) {
            System.exit(0);
        }
        if (key == 57) {
            try {
                SpriteSheetFontTest.container.setDisplayMode(640, 480, false);
            }
            catch (SlickException e) {
                Log.error((Throwable)e);
            }
        }
    }
    
    public static void main(final String[] argv) {
        try {
            (SpriteSheetFontTest.container = new AppGameContainer((Game)new SpriteSheetFontTest())).setDisplayMode(800, 600, false);
            SpriteSheetFontTest.container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
