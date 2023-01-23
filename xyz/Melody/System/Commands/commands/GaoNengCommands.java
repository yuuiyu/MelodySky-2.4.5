//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.Utils.*;
import xyz.Melody.System.Managers.GaoNeng.*;
import net.minecraft.util.*;

public class GaoNengCommands extends Command
{
    public GaoNengCommands() {
        super(".checkgaoneng", new String[] { "cgn", "gn" }, "", "sketit");
    }
    
    public String execute(final String[] args) {
        if (args.length >= 1) {
            String az = args[0];
            az = az.replaceAll("-", "");
            Helper.sendMessage("Checking BlackList For UUID " + az);
            final GaoNengManager.GaoNeng gao = GaoNengManager.getIfIsGaoNeng(az);
            if (gao != null) {
                Helper.sendMessage("Player UUID: " + gao.getUuid());
                Helper.sendMessage("Rank: " + gao.getRank() + " [" + gao.getRank().replaceAll("&", "§") + EnumChatFormatting.GRAY + "]");
                Helper.sendMessage("Reason: " + gao.getReason());
                Helper.sendMessage("Real: " + gao.isRealBlackList());
                Helper.sendMessage("Checker: " + gao.getChecker());
                Helper.sendMessage("QQ: " + gao.getQQ());
                Helper.sendMessage("Phone: " + gao.getPhone());
                Helper.sendMessage("Time: " + gao.getTime());
            }
            else {
                Helper.sendMessage("No Info Found.");
            }
        }
        else {
            String az = this.mc.getSession().getSessionID().toString();
            az = az.replaceAll("-", "");
            final GaoNengManager.GaoNeng gao = GaoNengManager.getIfIsGaoNeng(az);
            if (gao != null) {
                Helper.sendMessage("Player UUID: " + gao.getUuid());
                Helper.sendMessage("Rank: " + gao.getRank() + " [" + gao.getRank().replaceAll("&", "§") + EnumChatFormatting.GRAY + "]");
                Helper.sendMessage("Reason: " + gao.getReason());
                Helper.sendMessage("Real: " + gao.isRealBlackList());
                Helper.sendMessage("Checker: " + gao.getChecker());
                Helper.sendMessage("QQ: " + gao.getQQ());
                Helper.sendMessage("Phone: " + gao.getPhone());
                Helper.sendMessage("Time: " + gao.getTime());
            }
            else {
                Helper.sendMessage("\ufffd\u3cbb\ufffd\ufffd\ufffd\ufffd");
            }
        }
        return null;
    }
}
