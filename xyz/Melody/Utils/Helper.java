//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import net.minecraft.client.*;
import xyz.Melody.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.game.*;

public class Helper
{
    public static Minecraft mc;
    
    public static void sendMessageOLD(final String msg) {
        final Object[] arrobject = new Object[2];
        Client.instance.getClass();
        arrobject[0] = EnumChatFormatting.BLUE + "Melody" + EnumChatFormatting.GRAY + ": ";
        arrobject[1] = msg;
        Helper.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText(String.format("%s%s", arrobject)));
    }
    
    public static void sendMessage(final Object message) {
        new ChatUtils.ChatMessageBuilder(true, true).appendText(message + "").setColor(EnumChatFormatting.GRAY).build().displayClientSided();
    }
    
    public static void sendMessageWithoutPrefix(final Object message) {
        new ChatUtils.ChatMessageBuilder(false, true).appendText(message + "").setColor(EnumChatFormatting.GRAY).build().displayClientSided();
    }
    
    public static boolean onServer(final String server) {
        return !Helper.mc.isSingleplayer() && Helper.mc.getCurrentServerData().serverIP.toLowerCase().contains(server);
    }
    
    static {
        Helper.mc = Minecraft.getMinecraft();
    }
}
