//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.Utils.*;
import xyz.Melody.*;

public class RankCommand extends Command
{
    public RankCommand() {
        super(".rank", new String[] { "rank" }, "", "sketit");
    }
    
    public String execute(final String[] args) {
        if (args.length < 1) {
            Helper.sendMessage("Correct Useage: .rank [rank] / .rank reset");
            return null;
        }
        if (args[0].toLowerCase().equals("reset")) {
            Client.customRank = "\ufffd\u0379\ufffd\ufffd\ufffd";
            Helper.sendMessage("Reset Custom Rank to " + Client.customRank + ".");
            Client.instance.saveCustomRank();
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
        final String finalName = Client.customRank = name.replaceAll("&", "§");
        Helper.sendMessage("Set Custom Rank to " + Client.customRank + ".");
        Client.instance.saveCustomRank();
        return null;
    }
}
