//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.module.modules.QOL.Swappings.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.Utils.*;
import xyz.Melody.System.Managers.Client.*;

public class CustomItemSwitch extends Command
{
    public CustomItemSwitch() {
        super("is", new String[] { "itemswitcher" }, "", "FUCK YOU!");
    }
    
    public String execute(final String[] args) {
        if (args.length >= 1) {
            final ItemSwitcher m = (ItemSwitcher)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)ItemSwitcher.class);
            if (m != null) {
                m.setCustomItemID(args[0].toUpperCase());
                Helper.sendMessage("Set Custom ItemSwitcher Item to " + args[0].toUpperCase());
                final String id = args[0].toUpperCase();
                FileManager.save("CustomIS.txt", id, false);
                Client.instance.logger.info("[Melody] [Config] CustomItemSwitcher ItemID Saved!");
            }
            else {
                Helper.sendMessage("Unexpected Error.");
            }
        }
        else {
            Helper.sendMessageWithoutPrefix("§bCorrect usage:§7 .is [ItemID]");
        }
        return null;
    }
}
