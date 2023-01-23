//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.Utils.*;
import xyz.Melody.*;

public class NameCommand extends Command
{
    public NameCommand() {
        super(".name", new String[] { "name" }, "", "sketit");
    }
    
    public String execute(final String[] args) {
        if (args.length < 1) {
            Helper.sendMessage("Correct Useage: .name [name] / .name reset");
            return null;
        }
        if (args[0].toLowerCase().equals("reset")) {
            Client.playerName = null;
            Helper.sendMessage("Reset Custom Name to " + Client.playerName + ".");
            Client.instance.saveCustomName();
            return null;
        }
        String name = "";
        for (int i = 0; i < args.length; ++i) {
            if (i == args.length - 1) {
                name += args[i];
            }
            else {
                name = name + args[i] + " ";
            }
        }
        final String finalName = Client.playerName = name.replaceAll("&", "§");
        Helper.sendMessage("Set Custom Name to " + Client.playerName + ".");
        Client.instance.saveCustomName();
        return null;
    }
}
