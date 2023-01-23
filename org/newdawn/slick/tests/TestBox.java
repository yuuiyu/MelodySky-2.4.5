//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import java.util.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.*;

public class TestBox extends BasicGame
{
    private ArrayList games;
    private BasicGame currentGame;
    private int index;
    private AppGameContainer container;
    
    public TestBox() {
        super("Test Box");
        this.games = new ArrayList();
    }
    
    public void addGame(final Class game) {
        this.games.add(game);
    }
    
    private void nextGame() {
        if (this.index == -1) {
            return;
        }
        ++this.index;
        if (this.index >= this.games.size()) {
            this.index = 0;
        }
        this.startGame();
    }
    
    private void startGame() {
        try {
            this.currentGame = this.games.get(this.index).newInstance();
            this.container.getGraphics().setBackground(Color.black);
            this.currentGame.init((GameContainer)this.container);
            this.currentGame.render((GameContainer)this.container, this.container.getGraphics());
        }
        catch (Exception e) {
            Log.error(e);
        }
        this.container.setTitle(this.currentGame.getTitle());
    }
    
    public void init(final GameContainer c) throws SlickException {
        if (this.games.size() == 0) {
            (this.currentGame = (BasicGame)new lIIl(this, "NULL")).init(c);
            this.index = -1;
        }
        else {
            this.index = 0;
            this.container = (AppGameContainer)c;
            this.startGame();
        }
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        this.currentGame.update(container, delta);
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        SlickCallable.enterSafeBlock();
        this.currentGame.render(container, g);
        SlickCallable.leaveSafeBlock();
    }
    
    public void controllerButtonPressed(final int controller, final int button) {
        this.currentGame.controllerButtonPressed(controller, button);
    }
    
    public void controllerButtonReleased(final int controller, final int button) {
        this.currentGame.controllerButtonReleased(controller, button);
    }
    
    public void controllerDownPressed(final int controller) {
        this.currentGame.controllerDownPressed(controller);
    }
    
    public void controllerDownReleased(final int controller) {
        this.currentGame.controllerDownReleased(controller);
    }
    
    public void controllerLeftPressed(final int controller) {
        this.currentGame.controllerLeftPressed(controller);
    }
    
    public void controllerLeftReleased(final int controller) {
        this.currentGame.controllerLeftReleased(controller);
    }
    
    public void controllerRightPressed(final int controller) {
        this.currentGame.controllerRightPressed(controller);
    }
    
    public void controllerRightReleased(final int controller) {
        this.currentGame.controllerRightReleased(controller);
    }
    
    public void controllerUpPressed(final int controller) {
        this.currentGame.controllerUpPressed(controller);
    }
    
    public void controllerUpReleased(final int controller) {
        this.currentGame.controllerUpReleased(controller);
    }
    
    public void keyPressed(final int key, final char c) {
        this.currentGame.keyPressed(key, c);
        if (key == 28) {
            this.nextGame();
        }
    }
    
    public void keyReleased(final int key, final char c) {
        this.currentGame.keyReleased(key, c);
    }
    
    public void mouseMoved(final int oldx, final int oldy, final int newx, final int newy) {
        this.currentGame.mouseMoved(oldx, oldy, newx, newy);
    }
    
    public void mousePressed(final int button, final int x, final int y) {
        this.currentGame.mousePressed(button, x, y);
    }
    
    public void mouseReleased(final int button, final int x, final int y) {
        this.currentGame.mouseReleased(button, x, y);
    }
    
    public void mouseWheelMoved(final int change) {
        this.currentGame.mouseWheelMoved(change);
    }
    
    public static void main(final String[] argv) {
        try {
            final TestBox box = new TestBox();
            box.addGame(AnimationTest.class);
            box.addGame(AntiAliasTest.class);
            box.addGame(BigImageTest.class);
            box.addGame(ClipTest.class);
            box.addGame(DuplicateEmitterTest.class);
            box.addGame(FlashTest.class);
            box.addGame(FontPerformanceTest.class);
            box.addGame(FontTest.class);
            box.addGame(GeomTest.class);
            box.addGame(GradientTest.class);
            box.addGame(GraphicsTest.class);
            box.addGame(ImageBufferTest.class);
            box.addGame(ImageReadTest.class);
            box.addGame(ImageTest.class);
            box.addGame(KeyRepeatTest.class);
            box.addGame(MusicListenerTest.class);
            box.addGame(PackedSheetTest.class);
            box.addGame(PedigreeTest.class);
            box.addGame(PureFontTest.class);
            box.addGame(ShapeTest.class);
            box.addGame(SoundTest.class);
            box.addGame(SpriteSheetFontTest.class);
            box.addGame(TransparentColorTest.class);
            final AppGameContainer container = new AppGameContainer((Game)box);
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
