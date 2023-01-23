//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import javax.swing.*;

final class llIl extends EffectUtil.DefaultValue
{
    final /* synthetic */ int val$currentValue;
    final /* synthetic */ String val$description;
    
    llIl(final String name, final String value, final int val$currentValue, final String val$description) {
        this.val$currentValue = val$currentValue;
        this.val$description = val$description;
        super(name, value);
    }
    
    public void showDialog() {
        final JSpinner spinner = new JSpinner(new SpinnerNumberModel(this.val$currentValue, -32768, 32767, 1));
        if (this.showValueDialog((JComponent)spinner, this.val$description)) {
            this.value = String.valueOf(spinner.getValue());
        }
    }
    
    public Object getObject() {
        return Integer.valueOf(this.value);
    }
}
