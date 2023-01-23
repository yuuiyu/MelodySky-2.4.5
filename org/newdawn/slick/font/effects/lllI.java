//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import javax.swing.*;

class lllI implements Runnable
{
    final /* synthetic */ JComponent val$component;
    final /* synthetic */ EffectUtil.DefaultValue this$0;
    
    lllI(final EffectUtil.DefaultValue this$0, final JComponent val$component) {
        this.this$0 = this$0;
        this.val$component = val$component;
    }
    
    @Override
    public void run() {
        JComponent focusComponent = this.val$component;
        if (focusComponent instanceof JSpinner) {
            focusComponent = ((JSpinner.DefaultEditor)((JSpinner)this.val$component).getEditor()).getTextField();
        }
        focusComponent.requestFocusInWindow();
    }
}
