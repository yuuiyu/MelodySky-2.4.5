//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.particles.*;
import java.io.*;
import org.newdawn.slick.imageout.*;
import org.newdawn.slick.*;

public class ImageOutTest extends BasicGame
{
    private GameContainer container;
    private ParticleSystem fire;
    private Graphics g;
    private Image copy;
    private String message;
    
    public ImageOutTest() {
        super("Image Out Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.container = container;
        try {
            this.fire = ParticleIO.loadConfiguredSystem("testdata/system.xml");
        }
        catch (IOException e) {
            throw new SlickException("Failed to load particle systems", (Throwable)e);
        }
        this.copy = new Image(400, 300);
        final String[] formats = ImageOut.getSupportedFormats();
        this.message = "Formats supported: ";
        for (int i = 0; i < formats.length; ++i) {
            this.message += formats[i];
            if (i < formats.length - 1) {
                this.message += ",";
            }
        }
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.drawString("T - TGA Snapshot", 10.0f, 50.0f);
        g.drawString("J - JPG Snapshot", 10.0f, 70.0f);
        g.drawString("P - PNG Snapshot", 10.0f, 90.0f);
        g.setDrawMode(Graphics.MODE_ADD);
        g.drawImage(this.copy, 200.0f, 300.0f);
        g.setDrawMode(Graphics.MODE_NORMAL);
        g.drawString(this.message, 10.0f, 400.0f);
        g.drawRect(200.0f, 0.0f, 400.0f, 300.0f);
        g.translate(400.0f, 250.0f);
        this.fire.render();
        this.g = g;
    }
    
    private void writeTo(final String fname) throws SlickException {
        this.g.copyArea(this.copy, 200, 0);
        ImageOut.write(this.copy, fname);
        this.message = "Written " + fname;
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        this.fire.update(delta);
        if (container.getInput().isKeyPressed(25)) {
            this.writeTo("ImageOutTest.png");
        }
        if (container.getInput().isKeyPressed(36)) {
            this.writeTo("ImageOutTest.jpg");
        }
        if (container.getInput().isKeyPressed(20)) {
            this.writeTo("ImageOutTest.tga");
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new ImageOutTest());
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
