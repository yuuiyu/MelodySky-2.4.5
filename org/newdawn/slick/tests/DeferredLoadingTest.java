//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.loading.*;
import java.io.*;
import org.newdawn.slick.*;

public class DeferredLoadingTest extends BasicGame
{
    private Music music;
    private Sound sound;
    private Image image;
    private Font font;
    private DeferredResource nextResource;
    private boolean started;
    
    public DeferredLoadingTest() {
        super("Deferred Loading Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        LoadingList.setDeferredLoading(true);
        new Sound("testdata/cbrown01.wav");
        new Sound("testdata/engine.wav");
        this.sound = new Sound("testdata/restart.ogg");
        new Music("testdata/testloop.ogg");
        this.music = new Music("testdata/SMB-X.XM");
        new Image("testdata/cursor.png");
        new Image("testdata/cursor.tga");
        new Image("testdata/cursor.png");
        new Image("testdata/cursor.png");
        new Image("testdata/dungeontiles.gif");
        new Image("testdata/logo.gif");
        this.image = new Image("testdata/logo.tga");
        new Image("testdata/logo.png");
        new Image("testdata/rocket.png");
        new Image("testdata/testpack.png");
        this.font = (Font)new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
    }
    
    public void render(final GameContainer container, final Graphics g) {
        if (this.nextResource != null) {
            g.drawString("Loading: " + this.nextResource.getDescription(), 100.0f, 100.0f);
        }
        final int total = LoadingList.get().getTotalResources();
        final int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
        final float bar = loaded / (float)total;
        g.fillRect(100.0f, 150.0f, (float)(loaded * 40), 20.0f);
        g.drawRect(100.0f, 150.0f, (float)(total * 40), 20.0f);
        if (this.started) {
            this.image.draw(100.0f, 200.0f);
            this.font.drawString(100.0f, 500.0f, "LOADING COMPLETE");
        }
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        if (this.nextResource != null) {
            try {
                this.nextResource.load();
                try {
                    Thread.sleep(50L);
                }
                catch (Exception ex) {}
            }
            catch (IOException e) {
                throw new SlickException("Failed to load: " + this.nextResource.getDescription(), (Throwable)e);
            }
            this.nextResource = null;
        }
        if (LoadingList.get().getRemainingResources() > 0) {
            this.nextResource = LoadingList.get().getNext();
        }
        else if (!this.started) {
            this.started = true;
            this.music.loop();
            this.sound.play();
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new DeferredLoadingTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    public void keyPressed(final int key, final char c) {
    }
}
