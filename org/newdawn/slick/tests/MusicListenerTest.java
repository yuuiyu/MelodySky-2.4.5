//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class MusicListenerTest extends BasicGame implements MusicListener
{
    private boolean musicEnded;
    private boolean musicSwapped;
    private Music music;
    private Music stream;
    
    public MusicListenerTest() {
        super("Music Listener Test");
        this.musicEnded = false;
        this.musicSwapped = false;
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.music = new Music("testdata/restart.ogg", false);
        this.stream = new Music("testdata/restart.ogg", false);
        this.music.addListener((MusicListener)this);
        this.stream.addListener((MusicListener)this);
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void musicEnded(final Music music) {
        this.musicEnded = true;
    }
    
    public void musicSwapped(final Music music, final Music newMusic) {
        this.musicSwapped = true;
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.drawString("Press M to play music", 100.0f, 100.0f);
        g.drawString("Press S to stream music", 100.0f, 150.0f);
        if (this.musicEnded) {
            g.drawString("Music Ended", 100.0f, 200.0f);
        }
        if (this.musicSwapped) {
            g.drawString("Music Swapped", 100.0f, 250.0f);
        }
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 50) {
            this.musicEnded = false;
            this.musicSwapped = false;
            this.music.play();
        }
        if (key == 31) {
            this.musicEnded = false;
            this.musicSwapped = false;
            this.stream.play();
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new MusicListenerTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
