//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.misc.*;
import xyz.Melody.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Event.*;

public class AntiLobbyCommand extends Module
{
    private Option<Boolean> od;
    
    public AntiLobbyCommand() {
        super("AntiLobbyCommands", new String[] { "alc", "asc", "lobby" }, ModuleType.Others);
        this.od = (Option<Boolean>)new Option("DungeonOnly", (Object)true);
        this.addValues(new Value[] { (Value)this.od });
        this.setModInfo("Prevents You using /l or /spawn.");
    }
    
    @EventHandler
    private void onChat(final EventChat e) {
        if (!Client.inDungeons && (boolean)this.od.getValue()) {
            return;
        }
        final String msg = e.getMessage();
        if (msg.toLowerCase().contains("/lobby")) {
            Helper.sendMessage(EnumChatFormatting.GREEN + "[AntiLobbyCommand] " + EnumChatFormatting.DARK_GREEN + "Prevented you from using " + EnumChatFormatting.RED + "/lobby " + EnumChatFormatting.DARK_GREEN + "Command.");
            e.setCancelled(true);
            return;
        }
        if (msg.toLowerCase().equals("/l")) {
            Helper.sendMessage(EnumChatFormatting.GREEN + "[AntiLobbyCommand] " + EnumChatFormatting.DARK_GREEN + "Prevented you from using " + EnumChatFormatting.RED + "/l " + EnumChatFormatting.DARK_GREEN + "Command.");
            e.setCancelled(true);
            return;
        }
        if (msg.toLowerCase().contains("/spawn")) {
            Helper.sendMessage(EnumChatFormatting.GREEN + "[AntiLobbyCommand] " + EnumChatFormatting.DARK_GREEN + "Prevented you from using " + EnumChatFormatting.RED + "/spawn " + EnumChatFormatting.DARK_GREEN + "Command.");
            e.setCancelled(true);
        }
    }
}
