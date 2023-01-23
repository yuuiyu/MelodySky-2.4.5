//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import java.awt.*;
import java.util.*;
import org.newdawn.slick.*;

public class TrueTypeFontPerformanceTest extends BasicGame
{
    private Font awtFont;
    private TrueTypeFont font;
    private String text;
    private ArrayList lines;
    private boolean visible;
    
    public TrueTypeFontPerformanceTest() {
        super("Font Performance Test");
        this.text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin bibendum. Aliquam ac sapien a elit congue iaculis. Quisque et justo quis mi mattis euismod. Donec elementum, mi quis aliquet varius, nisi leo volutpat magna, quis ultricies eros augue at risus. Integer non magna at lorem sodales molestie. Integer diam nulla, ornare sit amet, mattis quis, euismod et, mauris. Proin eget tellus non nisl mattis laoreet. Nunc at nunc id elit pretium tempor. Duis vulputate, nibh eget rhoncus eleifend, tellus lectus sollicitudin mi, rhoncus tincidunt nisi massa vitae ipsum. Praesent tellus diam, luctus ut, eleifend nec, auctor et, orci. Praesent eu elit. Pellentesque ante orci, volutpat placerat, ornare eget, cursus sit amet, eros. Duis pede sapien, euismod a, volutpat pellentesque, convallis eu, mauris. Nunc eros. Ut eu risus et felis laoreet viverra. Curabitur a metus.";
        this.lines = new ArrayList();
        this.visible = true;
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.awtFont = new Font("Verdana", 0, 16);
        this.font = new TrueTypeFont(this.awtFont, false);
        for (int j = 0; j < 2; ++j) {
            for (int lineLen = 90, i = 0; i < this.text.length(); i += lineLen) {
                if (i + lineLen > this.text.length()) {
                    lineLen = this.text.length() - i;
                }
                this.lines.add(this.text.substring(i, i + lineLen));
            }
            this.lines.add("");
        }
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.setFont((org.newdawn.slick.Font)this.font);
        if (this.visible) {
            for (int i = 0; i < this.lines.size(); ++i) {
                this.font.drawString(10.0f, (float)(50 + i * 20), this.lines.get(i), (i > 10) ? Color.red : Color.green);
            }
        }
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 1) {
            System.exit(0);
        }
        if (key == 57) {
            this.visible = !this.visible;
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new TrueTypeFontPerformanceTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
