//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.module.modules.macros.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.Utils.*;
import net.minecraft.util.*;
import xyz.Melody.System.Managers.Client.*;
import java.util.*;

public class AutoRubyCMD extends Command
{
    public AutoRubyCMD() {
        super(".autoruby", new String[] { "ar" }, "", "sketit");
    }
    
    public String execute(final String[] args) {
        if (args.length >= 1) {
            final AutoRuby ar = (AutoRuby)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AutoRuby.class);
            if (args[0].toLowerCase().contains("start")) {
                ar.started = true;
                Helper.sendMessage("AutoRuby: Started.");
            }
            else if (args[0].toLowerCase().contains("stop")) {
                ar.started = false;
                Helper.sendMessage("AutoRuby: Stopped.");
            }
            else if (args[0].toLowerCase().contains("remove")) {
                try {
                    ar.wps.remove(Integer.parseInt(args[1]) - 1);
                }
                catch (Exception e) {
                    Helper.sendMessage(e.getMessage());
                }
            }
            else if (args[0].toLowerCase().contains("add")) {
                try {
                    final int x = Integer.parseInt(args[2]);
                    final int y = Integer.parseInt(args[3]);
                    final int z = Integer.parseInt(args[4]);
                    final BlockPos append = new BlockPos(x, y, z);
                    ar.wps.add(Integer.parseInt(args[1]), append);
                }
                catch (Exception e) {
                    Helper.sendMessage(e.getMessage());
                }
            }
            else if (args[0].toLowerCase().contains("clear")) {
                ar.wps.clear();
                Helper.sendMessage("AutoRuby: Waypoints Cleared.");
            }
            else if (args[0].toLowerCase().contains("save")) {
                if (args[1] == null) {
                    Helper.sendMessage("Correct Useage: .ar save [configName]");
                    return null;
                }
                if (args[1].contains("/") || args[1].contains(":") || args[1].contains("*") || args[1].contains("?") || args[1].contains(String.valueOf('\"')) || args[1].contains("<") || args[1].contains(">") || args[1].contains("|")) {
                    Helper.sendMessage("Config Name can not be '/', ':', '*', '?', " + String.valueOf('\"') + ", '<', '>', '|'.");
                    return null;
                }
                String all = "";
                int count = 0;
                for (final BlockPos pos : ar.wps) {
                    final String sus = pos.getX() + ":" + pos.getY() + ":" + pos.getZ() + "%";
                    all += sus;
                    ++count;
                }
                FileManager.save(args[1] + ".txt", all, false);
                Helper.sendMessage("[AutoRuby] Saved " + count + " Waypoints.");
            }
            else if (args[0].toLowerCase().contains("load")) {
                if (args[1] == null) {
                    Helper.sendMessage("Correct Useage: .ar load [configName]");
                    return null;
                }
                final List<String> poses = FileManager.read(args[1] + ".txt");
                String all2 = "";
                for (final String str : poses) {
                    all2 += str;
                }
                int count2 = 0;
                final String[] ps = all2.split("%");
                final ArrayList<BlockPos> pornhub = new ArrayList<BlockPos>();
                for (final String s : ps) {
                    final StringTokenizer st = new StringTokenizer(s, ":");
                    final int x2 = Integer.parseInt(st.nextToken());
                    final int y2 = Integer.parseInt(st.nextToken());
                    final int z2 = Integer.parseInt(st.nextToken());
                    pornhub.add(new BlockPos(x2, y2, z2));
                    ++count2;
                }
                ar.wps.clear();
                ar.wps.addAll(pornhub);
                Helper.sendMessage("[AutoRuby] Loaded " + count2 + " Waypoints.");
            }
            else if (args[0].toLowerCase().contains("help")) {
                Helper.sendMessageWithoutPrefix("====================== AutoRuby ======================");
                Helper.sendMessageWithoutPrefix("AutoGemstone:> .ar start - Start Mining.");
                Helper.sendMessageWithoutPrefix("AutoGemstone:> .ar stop - Stop Mining.");
                Helper.sendMessageWithoutPrefix("AutoGemstone:> .ar add [index] [x] [y] [z] - Add a Waypoint After 'index'.");
                Helper.sendMessageWithoutPrefix("AutoGemstone:> .ar remove [index] - Remove a waypoint.");
                Helper.sendMessageWithoutPrefix("AutoGemstone:> .ar save [name] - Save Current Waypoints as 'name'.");
                Helper.sendMessageWithoutPrefix("AutoGemstone:> .ar load [name] - Load saved Waypoints as 'name'.");
            }
        }
        else {
            Helper.sendMessage("useage: .AutoRuby start/stop");
        }
        return null;
    }
}
