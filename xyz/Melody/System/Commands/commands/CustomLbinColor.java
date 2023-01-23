//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.Utils.*;

public class CustomLbinColor extends Command
{
    public CustomLbinColor() {
        super("CustomLbinColor", new String[] { "clbc" }, "", "FUCK YOU!");
    }
    
    public String execute(final String[] args) {
        if (args.length >= 1) {
            FetchLBinData.colorPrefix = args[0];
            Helper.sendMessage("Set Custom LBin Color to " + FetchLBinData.colorPrefix);
        }
        else {
            Helper.sendMessageWithoutPrefix("§bCorrect usage:§7 .clbc [ColorPrefix]");
        }
        return null;
    }
}
