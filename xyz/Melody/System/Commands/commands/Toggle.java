//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.Utils.*;
import xyz.Melody.*;
import net.minecraft.util.*;
import xyz.Melody.module.*;

public class Toggle extends Command
{
    public Toggle() {
        super("t", new String[] { "toggle", "togl", "turnon", "enable" }, "", "Toggles a specified Module");
    }
    
    public String execute(final String[] args) {
        if (args.length <= 1) {
            if (args.length < 1) {
                Helper.sendMessageWithoutPrefix("§bCorrect usage:§7 .t <module>");
            }
        }
        boolean found = false;
        final Module m = Client.instance.getModuleManager().getAlias(args[0]);
        if (m != null) {
            if (!m.isEnabled()) {
                m.setEnabled(true);
            }
            else {
                m.setEnabled(false);
            }
            found = true;
            if (m.isEnabled()) {
                Helper.sendMessage("> " + m.getName() + EnumChatFormatting.GRAY + " was" + EnumChatFormatting.GREEN + " enabled");
            }
            else {
                Helper.sendMessage("> " + m.getName() + EnumChatFormatting.GRAY + " was" + EnumChatFormatting.RED + " disabled");
            }
        }
        if (!found) {
            Helper.sendMessage("> Module name " + EnumChatFormatting.RED + args[0] + EnumChatFormatting.GRAY + " is invalid");
        }
        return null;
    }
}
