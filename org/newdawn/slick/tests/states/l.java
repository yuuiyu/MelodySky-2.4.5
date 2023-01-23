//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.states;

import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.state.*;

class l extends CrossStateTransition
{
    final /* synthetic */ long val$start;
    final /* synthetic */ TestState1 this$0;
    
    l(final TestState1 this$0, final GameState secondState, final long val$start) {
        this.this$0 = this$0;
        this.val$start = val$start;
        super(secondState);
    }
    
    public boolean isComplete() {
        return System.currentTimeMillis() - this.val$start > 2000L;
    }
    
    public void init(final GameState firstState, final GameState secondState) {
    }
}
