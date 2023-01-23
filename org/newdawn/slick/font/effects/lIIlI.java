//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import javax.swing.*;

final class lIIlI extends EffectUtil.DefaultValue
{
    final /* synthetic */ String[][] val$options;
    final /* synthetic */ String val$currentValue;
    final /* synthetic */ String val$description;
    
    lIIlI(final String name, final String value, final String[][] val$options, final String val$currentValue, final String val$description) {
        this.val$options = val$options;
        this.val$currentValue = val$currentValue;
        this.val$description = val$description;
        super(name, value);
    }
    
    public void showDialog() {
        int selectedIndex = -1;
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < this.val$options.length; ++i) {
            model.addElement(this.val$options[i][0]);
            if (this.getValue(i).equals(this.val$currentValue)) {
                selectedIndex = i;
            }
        }
        final JComboBox comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(selectedIndex);
        if (this.showValueDialog((JComponent)comboBox, this.val$description)) {
            this.value = this.getValue(comboBox.getSelectedIndex());
        }
    }
    
    private String getValue(final int i) {
        if (this.val$options[i].length == 1) {
            return this.val$options[i][0];
        }
        return this.val$options[i][1];
    }
    
    public String toString() {
        for (int i = 0; i < this.val$options.length; ++i) {
            if (this.getValue(i).equals(this.value)) {
                return this.val$options[i][0].toString();
            }
        }
        return "";
    }
    
    public Object getObject() {
        return this.value;
    }
}
