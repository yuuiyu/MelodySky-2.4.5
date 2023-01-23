//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Click;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.GUI.Font.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import org.lwjgl.input.*;

public class KeyBindButton extends ValueButton
{
    public Module cheat;
    public double opacity;
    public boolean bind;
    CFontRenderer font;
    
    public KeyBindButton(final Module cheat, final int x, final int y) {
        super(null, x, y);
        this.opacity = 0.0;
        this.font = FontLoaders.NMSL18;
        this.custom = true;
        this.bind = false;
        this.cheat = cheat;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY) {
        Gui.drawRect(0, 0, 0, 0, 0);
        Gui.drawRect(this.x - 10, this.y - 4, this.x + 80, this.y + 11, new Color(220, 220, 220).getRGB());
        this.mfont.drawString("Bind", (float)(this.x - 5), (float)(this.y + 2), new Color(108, 108, 108).getRGB());
        this.mfont.drawString(String.valueOf(this.bind ? "" : "") + Keyboard.getKeyName(this.cheat.getKey()), (float)(this.x + 76 - this.mfont.getStringWidth(Keyboard.getKeyName(this.cheat.getKey()))), (float)(this.y + 2), new Color(108, 108, 108).getRGB());
    }
    
    @Override
    public void key(final char typedChar, final int keyCode) {
        if (this.bind) {
            this.cheat.setKey(keyCode);
            ClickUi.binding = false;
            this.bind = false;
        }
        super.key(typedChar, keyCode);
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int button) {
        if (mouseX > this.x - 7 && mouseX < this.x + 85 && mouseY > this.y - 6 && mouseY < this.y + this.font.getStringHeight(this.cheat.getName()) + 5 && button == 0) {
            final boolean b = !this.bind;
            this.bind = b;
            ClickUi.binding = b;
        }
        if (mouseX > this.x - 7 && mouseX < this.x + 85 && mouseY > this.y - 6 && mouseY < this.y + this.font.getStringHeight(this.cheat.getName()) + 5 && button == 1) {
            this.cheat.setKey(0);
        }
        super.click(mouseX, mouseY, button);
    }
}
