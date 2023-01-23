//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import javax.swing.*;

final class lIll extends EffectUtil.DefaultValue
{
    final /* synthetic */ boolean val$currentValue;
    final /* synthetic */ String val$description;
    
    lIll(final String name, final String value, final boolean val$currentValue, final String val$description) {
        this.val$currentValue = val$currentValue;
        this.val$description = val$description;
        super(name, value);
    }
    
    public void showDialog() {
        final JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(this.val$currentValue);
        if (this.showValueDialog((JComponent)checkBox, this.val$description)) {
            this.value = String.valueOf(checkBox.isSelected());
        }
    }
    
    public Object getObject() {
        return Boolean.valueOf(this.value);
    }
}
