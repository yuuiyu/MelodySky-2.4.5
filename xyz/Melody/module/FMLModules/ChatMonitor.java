//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.FMLModules;

import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.*;
import xyz.Melody.module.modules.macros.*;
import xyz.Melody.Utils.*;
import java.util.*;
import net.minecraftforge.event.world.*;
import xyz.Melody.module.modules.QOL.Dungeons.*;
import xyz.Melody.module.modules.QOL.*;
import net.minecraft.util.*;

public class ChatMonitor
{
    public static boolean shouldShow;
    
    @SubscribeEvent(receiveCanceled = true)
    public void onNecron(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (message.equals("[BOSS] Necron: Goodbye.")) {
            event.message = (IChatComponent)new ChatComponentText(event.message.getFormattedText().replaceAll("Goodbye.", "Goldor, Fuck You!"));
        }
        if (message.equals("[BOSS] Necron: ARGH!")) {
            event.message = (IChatComponent)new ChatComponentText(event.message.getFormattedText().replaceAll("ARGH!", "NMSL!"));
        }
        if (message.equals("[BOSS] Necron: All this, for nothing...")) {
            event.message = (IChatComponent)new ChatComponentText(event.message.getFormattedText().replaceAll("All this, for nothing...", "No handle for you..."));
        }
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onChat(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (message.contains("Mining Speed Boost is now available!")) {
            Client.pickaxeAbilityReady = true;
        }
        if (message.contains("You used your Mining Speed Boost Pickaxe Ability!")) {
            Client.pickaxeAbilityReady = false;
        }
        if (message.contains("You have successfully picked the lock on this chest!")) {
            PowderChestMacro.nextRotation = null;
            PowderChestMacro.done.add(PowderChestMacro.chestPos);
            PowderChestMacro.chest = null;
        }
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onOMG(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        final String msg = event.message.getFormattedText();
        if (!message.contains("XJC") && !message.contains("Guild >") && (message.startsWith("PUZZLE FAIL") || message.contains("You were killed by") || message.contains("You were crushed") || message.contains("You fell into the void") || message.contains("You suffocated") || message.contains("You burnt to death") || message.contains("You died"))) {
            Music.playSound(this.getClass(), "kill_it.WAV");
            Client.instance.irc.sendPrefixMsg(msg, true);
        }
        if (!message.contains("XJC") && !message.contains("Guild >") && (message.startsWith("RARE REWARD") || message.startsWith("PET DROP"))) {
            final boolean b = new Random().nextBoolean();
            if (b) {
                Music.playSound(this.getClass(), "gg.WAV");
            }
            else {
                Music.playSound(this.getClass(), "iron_punch.WAV");
            }
            Client.instance.irc.sendPrefixMsg(msg, true);
        }
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onBossSay(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (message.contains("[BOSS]") && message.contains(":") && !message.contains("The Watcher")) {
            Client.instance.dungeonUtils.inBoss = true;
        }
        if (message.contains("[BOSS]") && message.contains(":") && message.contains("The Watcher")) {
            Client.instance.dungeonUtils.inBoss = false;
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        if (PowderChestMacro.autoClear.getValue()) {
            PowderChestMacro.done.clear();
        }
        LeverAura.allLevers.clear();
        LeverAura.clicked.clear();
        LeverAura.blockPos = null;
        GhostBlock.blockposs.clear();
        Client.pickaxeAbilityReady = false;
    }
    
    static {
        ChatMonitor.shouldShow = false;
    }
}
