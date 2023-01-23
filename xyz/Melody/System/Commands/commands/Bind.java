//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import xyz.Melody.*;
import org.lwjgl.input.*;
import xyz.Melody.Utils.*;
import xyz.Melody.module.*;

public class Bind extends Command
{
    public Bind() {
        super("Bind", new String[] { "b" }, "", "sketit");
    }
    
    public String execute(final String[] args) {
        if (args.length >= 2) {
            final Module m = Client.instance.getModuleManager().getAlias(args[0]);
            if (m != null) {
                final int k = Keyboard.getKeyIndex(args[1].toUpperCase());
                m.setKey(k);
                final Object[] arrobject = { m.getName(), (k == 0) ? "none" : args[1].toUpperCase() };
                Helper.sendMessage(String.format("> Bound %s to %s", arrobject));
            }
            else {
                Helper.sendMessage("> Invalid module name, double check spelling.");
            }
        }
        else {
            Helper.sendMessageWithoutPrefix("§bCorrect usage:§7 .bind <module> <key>");
        }
        return null;
    }
}
