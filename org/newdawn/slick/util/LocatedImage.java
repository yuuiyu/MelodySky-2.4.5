//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.util;

import org.newdawn.slick.*;

public class LocatedImage
{
    private Image image;
    private int x;
    private int y;
    private Color filter;
    private float width;
    private float height;
    
    public LocatedImage(final Image image, final int x, final int y) {
        this.filter = Color.white;
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = (float)image.getWidth();
        this.height = (float)image.getHeight();
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public void setHeight(final float height) {
        this.height = height;
    }
    
    public void setWidth(final float width) {
        this.width = width;
    }
    
    public void setColor(final Color c) {
        this.filter = c;
    }
    
    public Color getColor() {
        return this.filter;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void draw() {
        this.image.draw((float)this.x, (float)this.y, this.width, this.height, this.filter);
    }
}
