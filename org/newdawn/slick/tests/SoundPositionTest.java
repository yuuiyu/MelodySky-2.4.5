//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.openal.*;
import org.newdawn.slick.*;

public class SoundPositionTest extends BasicGame
{
    private GameContainer myContainer;
    private Music music;
    private int[] engines;
    
    public SoundPositionTest() {
        super("Music Position Test");
        this.engines = new int[3];
    }
    
    public void init(final GameContainer container) throws SlickException {
        SoundStore.get().setMaxSources(32);
        this.myContainer = container;
        (this.music = new Music("testdata/kirby.ogg", true)).play();
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.setColor(Color.white);
        g.drawString("Position: " + this.music.getPosition(), 100.0f, 100.0f);
        g.drawString("Space - Pause/Resume", 100.0f, 130.0f);
        g.drawString("Right Arrow - Advance 5 seconds", 100.0f, 145.0f);
    }
    
    public void update(final GameContainer container, final int delta) {
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 57) {
            if (this.music.playing()) {
                this.music.pause();
            }
            else {
                this.music.resume();
            }
        }
        if (key == 205) {
            this.music.setPosition(this.music.getPosition() + 5.0f);
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new SoundPositionTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
