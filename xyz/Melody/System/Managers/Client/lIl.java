//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Managers.Client;

import com.google.common.base.*;
import net.minecraft.scoreboard.*;

class lIl implements Predicate<Score>
{
    final /* synthetic */ ModuleManager this$0;
    
    lIl(final ModuleManager this$0) {
        this.this$0 = this$0;
    }
    
    public boolean apply(final Score p_apply_1_) {
        return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
    }
}
