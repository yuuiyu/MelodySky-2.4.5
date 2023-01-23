//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Click;

import xyz.Melody.GUI.Font.*;
import xyz.Melody.Event.value.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;

public class ValueButton
{
    public Value value;
    public String name;
    public boolean custom;
    public boolean change;
    public int x;
    public int y;
    public double opacity;
    CFontRenderer mfont;
    
    public ValueButton(final Value value, final int x, final int y) {
        this.mfont = FontLoaders.NMSL18;
        this.custom = false;
        this.opacity = 0.0;
        this.value = value;
        this.x = x;
        this.y = y;
        this.name = "";
        if (this.value instanceof Option) {
            this.change = (boolean)((Option)this.value).getValue();
        }
        else if (this.value instanceof Mode) {
            this.name = new StringBuilder().append(((Mode)this.value).getValue()).toString();
        }
        else if (value instanceof Numbers) {
            final Numbers v = (Numbers)value;
            this.name = String.valueOf(this.name) + ((Number)v.getValue()).doubleValue();
        }
        this.opacity = 0.0;
    }
    
    public void render(final int mouseX, final int mouseY) {
        if (!this.custom) {
            if (mouseX > this.x - 7 && mouseX < this.x + 85 && mouseY > this.y - 6 && mouseY < this.y + FontLoaders.NMSL18.getStringHeight(this.value.getName()) + 5) {
                if (this.opacity + 10.0 < 200.0) {
                    this.opacity += 10.0;
                }
                else {
                    this.opacity = 200.0;
                }
            }
            else if (this.opacity - 6.0 > 0.0) {
                this.opacity -= 6.0;
            }
            else {
                this.opacity = 0.0;
            }
            if (this.value instanceof Option) {
                this.change = (boolean)((Option)this.value).getValue();
            }
            else if (this.value instanceof Mode) {
                this.name = new StringBuilder().append(((Mode)this.value).getValue()).toString();
            }
            else if (this.value instanceof Numbers) {
                final Numbers v = (Numbers)this.value;
                this.name = new StringBuilder().append(((Number)v.getValue()).doubleValue()).toString();
                if (mouseX > this.x - 7 && mouseX < this.x + 85 && mouseY > this.y - 6 && mouseY < this.y + this.mfont.getStringHeight(this.value.getName()) + 5 && Mouse.isButtonDown(0)) {
                    final double min = v.getMinimum().doubleValue();
                    final double max = v.getMaximum().doubleValue();
                    final double inc = v.getIncrement().doubleValue();
                    final double valAbs = mouseX - (this.x + 1.0);
                    double perc = valAbs / 68.0;
                    perc = Math.min(Math.max(0.0, perc), 1.0);
                    final double valRel = (max - min) * perc;
                    double val = min + valRel;
                    val = Math.round(val * (1.0 / inc)) / (1.0 / inc);
                    v.setValue((Object)val);
                }
            }
            Gui.drawRect(0, 0, 0, 0, 0);
            RenderUtil.drawFastRoundedRect((float)(this.x - 10), (float)(this.y - 4), (float)(this.x + 80), (float)(this.y + 11), 1.0f, new Color(220, 220, 220).getRGB());
            this.mfont.drawString(this.value.getName(), (float)(this.x - 5), (float)(this.y + 1), new Color(108, 108, 108).getRGB());
            this.mfont.drawString(this.name, (float)(this.x + 76 - this.mfont.getStringWidth(this.name)), (float)(this.y + 2), new Color(108, 108, 108).getRGB());
            if (this.value instanceof Numbers) {
                final Numbers v = (Numbers)this.value;
                final double render = 68.0f * (((Number)v.getValue()).floatValue() - v.getMinimum().floatValue()) / (v.getMaximum().floatValue() - v.getMinimum().floatValue());
                RenderUtil.drawFastRoundedRect((float)this.x, (float)(this.y + this.mfont.getStringHeight(this.value.getName()) + 3), (float)(this.x + render + 1.0), (float)(this.y + this.mfont.getStringHeight(this.value.getName()) + 4), 1.0f, new Color(68, 68, 68).getRGB());
            }
            if (this.change) {
                Gui.drawRect(this.x + 70, this.y, this.x + 77, this.y + 7, new Color(108, 108, 108).getRGB());
            }
        }
    }
    
    public void key(final char typedChar, final int keyCode) {
    }
    
    public void click(final int mouseX, final int mouseY, final int button) {
        if (!this.custom && mouseX > this.x - 7 && mouseX < this.x + 85 && mouseY > this.y - 6 && mouseY < this.y + FontLoaders.NMSL18.getStringHeight(this.value.getName()) + 5) {
            if (this.value instanceof Option) {
                final Option v = (Option)this.value;
                v.setValue((Object)!(boolean)v.getValue());
                return;
            }
            if (this.value instanceof Mode) {
                final Mode m = (Mode)this.value;
                final Enum current = (Enum)m.getValue();
                final int next = (current.ordinal() + 1 >= m.getModes().length) ? 0 : (current.ordinal() + 1);
                this.value.setValue((Object)m.getModes()[next]);
            }
        }
    }
}
