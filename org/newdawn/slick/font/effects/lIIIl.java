//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import javax.swing.*;

final class lIIIl extends EffectUtil.DefaultValue
{
    final /* synthetic */ float val$currentValue;
    final /* synthetic */ float val$min;
    final /* synthetic */ float val$max;
    final /* synthetic */ String val$description;
    
    lIIIl(final String name, final String value, final float val$currentValue, final float val$min, final float val$max, final String val$description) {
        this.val$currentValue = val$currentValue;
        this.val$min = val$min;
        this.val$max = val$max;
        this.val$description = val$description;
        super(name, value);
    }
    
    public void showDialog() {
        final JSpinner spinner = new JSpinner(new SpinnerNumberModel(this.val$currentValue, this.val$min, this.val$max, 0.10000000149011612));
        if (this.showValueDialog((JComponent)spinner, this.val$description)) {
            this.value = String.valueOf(((Double)spinner.getValue()).floatValue());
        }
    }
    
    public Object getObject() {
        return Float.valueOf(this.value);
    }
}
