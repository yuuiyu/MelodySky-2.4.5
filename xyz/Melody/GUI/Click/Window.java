//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Click;

import xyz.Melody.Utils.*;
import xyz.Melody.GUI.Font.*;
import xyz.Melody.System.Managers.Client.*;
import xyz.Melody.module.*;
import xyz.Melody.module.modules.render.*;
import java.util.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.input.*;

public class Window
{
    CFontRenderer font;
    public ModuleType category;
    public ArrayList<Button> buttons;
    public boolean drag;
    public boolean extended;
    public int x;
    public int y;
    public int expand;
    public int dragX;
    public int dragY;
    public int max;
    public int scroll;
    public int scrollTo;
    public double angel;
    private TimerUtil timer;
    
    public Window(final ModuleType category, final int x, final int y) {
        this.timer = new TimerUtil();
        this.font = FontLoaders.NMSL18;
        this.buttons = new ArrayList<Button>();
        this.category = category;
        this.x = x;
        this.y = y;
        this.max = 120;
        int y2 = y + 22;
        for (final Module c : ModuleManager.getModules()) {
            if (c.getType() != category) {
                continue;
            }
            if (c instanceof HUD) {
                continue;
            }
            this.buttons.add(new Button(c, x + 5, y2));
            y2 += 15;
        }
        for (final Button b2 : this.buttons) {
            b2.setParent(this);
        }
    }
    
    public void render(final int mouseX, final int mouseY) {
        int current = 0;
        for (final Button b3 : this.buttons) {
            if (b3.expand) {
                for (final ValueButton v : b3.buttons) {
                    current += 15;
                }
            }
            current += 15;
        }
        final int height = 15 + current;
        if (this.extended) {
            this.expand = ((this.expand + 5 < height) ? (this.expand += 5) : height);
            this.angel = ((this.angel + 20.0 < 180.0) ? (this.angel += 20.0) : 180.0);
        }
        else {
            this.expand = ((this.expand - 5 > 0) ? (this.expand -= 5) : 0);
            this.angel = ((this.angel - 20.0 > 0.0) ? (this.angel -= 20.0) : 0.0);
        }
        RenderUtil.drawFastRoundedRect((float)(this.x - 2), (float)this.y, (float)(this.x + 92), (float)(this.y + 17), 1.0f, new Color(255, 255, 255).getRGB());
        GlStateManager.resetColor();
        this.font.drawString(this.category.name(), (float)(this.x + 15), (float)(this.y + 6), new Color(108, 108, 108).getRGB());
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(this.x + 90 - 10), (float)(this.y + 5), 0.0f);
        GlStateManager.rotate((float)this.angel, 0.0f, 0.0f, -1.0f);
        GlStateManager.translate((float)(-this.x + 90 - 10), (float)(-this.y + 5), 0.0f);
        GlStateManager.popMatrix();
        if (this.expand > 0) {
            this.buttons.forEach(b2 -> b2.render(mouseX, mouseY));
        }
        if (this.drag) {
            if (!Mouse.isButtonDown(0)) {
                this.drag = false;
            }
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
            this.buttons.get(0).y = this.y + 22 - this.scroll;
            for (final Button b4 : this.buttons) {
                b4.x = this.x + 5;
            }
        }
    }
    
    public void key(final char typedChar, final int keyCode) {
        this.buttons.forEach(b2 -> b2.key(typedChar, keyCode));
    }
    
    public void mouseScroll(final int mouseX, final int mouseY, final int amount) {
        if (mouseX > this.x - 2 && mouseX < this.x + 92 && mouseY > this.y - 2 && mouseY < this.y + 17 + this.expand) {
            this.scrollTo -= (int)(float)(amount / 120 * 28);
        }
    }
    
    public void click(final int mouseX, final int mouseY, final int button) {
        if (mouseX > this.x - 2 && mouseX < this.x + 92 && mouseY > this.y - 2 && mouseY < this.y + 17) {
            if (button == 1) {
                this.extended = !this.extended;
            }
            if (button == 0) {
                this.drag = true;
                this.dragX = mouseX - this.x;
                this.dragY = mouseY - this.y;
            }
        }
        if (this.extended) {
            this.buttons.stream().filter(b2 -> b2.y < this.y + this.expand).forEach(b2 -> b2.click(mouseX, mouseY, button));
        }
    }
}
