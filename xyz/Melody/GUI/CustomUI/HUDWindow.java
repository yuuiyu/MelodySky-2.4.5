//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI;

import java.awt.*;
import xyz.Melody.Utils.render.*;
import org.lwjgl.input.*;

public class HUDWindow
{
    public HUDApi api;
    public boolean drag;
    public int x;
    public int y;
    public int dragX;
    public int dragY;
    
    public HUDWindow(final HUDApi api, final int x2, final int y2) {
        this.api = api;
        this.x = x2;
        this.y = y2;
    }
    
    public void render(final int mouseX, final int mouseY) {
        if (this.api.isEnabled()) {
            RenderUtil.drawFilledCircle((float)(this.api.x - 4), (float)(this.api.y - 4), 4.0f, new Color(0, 177, 35));
        }
        else {
            RenderUtil.drawFilledCircle((float)(this.api.x - 4), (float)(this.api.y - 4), 4.0f, new Color(45, 49, 45));
        }
        if (this.drag) {
            if (!Mouse.isButtonDown(0)) {
                this.drag = false;
            }
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
            this.api.setXY(this.x, this.y);
        }
    }
    
    public void mouseScroll(final int mouseX, final int mouseY, final int amount) {
    }
    
    public void click(final int mouseX, final int mouseY, final int button) {
        if (mouseX > this.api.x - 8 && mouseX < this.api.x && mouseY > this.api.y - 8 && mouseY < this.api.y) {
            if (button == 0) {
                this.drag = true;
                this.dragX = mouseX - this.x;
                this.dragY = mouseY - this.y;
            }
            if (button == 1) {
                this.api.setEnabled(!this.api.isEnabled());
            }
        }
    }
}
