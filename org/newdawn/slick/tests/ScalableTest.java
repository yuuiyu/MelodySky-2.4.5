//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class ScalableTest extends BasicGame
{
    public ScalableTest() {
        super("Scalable Test For Widescreen");
    }
    
    public void init(final GameContainer container) throws SlickException {
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.setColor(new Color(0.4f, 0.6f, 0.8f));
        g.fillRect(0.0f, 0.0f, 1024.0f, 568.0f);
        g.setColor(Color.white);
        g.drawRect(5.0f, 5.0f, 1014.0f, 558.0f);
        g.setColor(Color.white);
        g.drawString(container.getInput().getMouseX() + "," + container.getInput().getMouseY(), 10.0f, 400.0f);
        g.setColor(Color.red);
        g.fillOval((float)(container.getInput().getMouseX() - 10), (float)(container.getInput().getMouseY() - 10), 20.0f, 20.0f);
    }
    
    public static void main(final String[] argv) {
        try {
            final ScalableGame game = (ScalableGame)new llIl((Game)new ScalableTest(), 1024, 568, true);
            final AppGameContainer container = new AppGameContainer((Game)game);
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
