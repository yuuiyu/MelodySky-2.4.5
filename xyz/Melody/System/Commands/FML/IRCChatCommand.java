//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.FML;

import net.minecraft.command.*;
import xyz.Melody.Utils.*;
import xyz.Melody.*;

public class IRCChatCommand extends CommandBase
{
    public String getCommandName() {
        return "kc";
    }
    
    public int getRequiredPermissionLevel() {
        return -1;
    }
    
    public boolean canSenderUseCommand(final ICommandSender sender) {
        return true;
    }
    
    public void processCommand(final ICommandSender sender, final String[] args) {
        if (args.length == 0) {
            Helper.sendMessage("&cInvalid Syntax. Use &3/kc [message]");
        }
        else {
            String msg = "";
            for (int i = 0; i < args.length; ++i) {
                if (i == args.length - 1) {
                    msg += args[i];
                }
                else {
                    msg = msg + args[i] + " ";
                }
            }
            final String finalMsg = msg.replaceAll("&", "§");
            Client.instance.irc.sendPrefixMsg(finalMsg, true);
        }
    }
    
    public String getCommandUsage(final ICommandSender sender) {
        return "kc";
    }
}
