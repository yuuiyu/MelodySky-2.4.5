//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.gui.*;

class llII implements ComponentListener
{
    final /* synthetic */ GUITest this$0;
    
    llII(final GUITest this$0) {
        this.this$0 = this$0;
    }
    
    public void componentActivated(final AbstractComponent source) {
        GUITest.access$002(this.this$0, "Entered2: " + GUITest.access$200(this.this$0).getText());
        GUITest.access$100(this.this$0).setFocus(true);
    }
}
