//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import javax.swing.*;
import java.awt.*;

final class lIIll extends EffectUtil.DefaultValue
{
    lIIll(final String name, final String value) {
        super(name, value);
    }
    
    public void showDialog() {
        final Color newColor = JColorChooser.showDialog(null, "Choose a color", EffectUtil.fromString(this.value));
        if (newColor != null) {
            this.value = EffectUtil.toString(newColor);
        }
    }
    
    public Object getObject() {
        return EffectUtil.fromString(this.value);
    }
}
