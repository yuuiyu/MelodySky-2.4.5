//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Swappings;

import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.Item.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Event.*;

public class EndStoneSword extends Module
{
    public EndStoneSword() {
        super("EndStoneSword", new String[] { "ess" }, ModuleType.Swapping);
        this.setModInfo("Auto Swap EndStone Sword.");
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        if (ItemUtils.useSBItem("END_STONE_SWORD")) {
            Helper.sendMessage(EnumChatFormatting.RED + "Used EndStoneSword!");
        }
        this.setEnabled(false);
    }
}
