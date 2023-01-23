//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.FMLModules;

import net.minecraftforge.client.event.*;
import net.minecraft.util.*;
import xyz.Melody.*;
import xyz.Melody.module.modules.QOL.Dungeons.*;
import xyz.Melody.module.*;
import xyz.Melody.Utils.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AlertsListener
{
    public static boolean shouldShowWatcherReady;
    public static boolean shouldShowSpiritMaskPoped;
    public static boolean shouldShowBonzoMaskPoped;
    public static boolean shouldShowBonzoMask2Poped;
    
    @SubscribeEvent(receiveCanceled = true)
    public void onChat(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (!Client.inDungeons) {
            return;
        }
        final Alerts omg = (Alerts)Client.instance.getModuleManager().getModuleByClass(Alerts.class);
        if (message.equals("[BOSS] The Watcher: That will be enough for now.") && omg.isEnabled() && (boolean)omg.watcher.getValue()) {
            AlertsListener.shouldShowWatcherReady = true;
            if (omg.rases.getValue()) {
                Music.playSound(this.getClass(), "wc.WAV");
            }
            else {
                Minecraft.getMinecraft().thePlayer.playSound("random.orb", 1.0f, 0.5f);
            }
        }
        if (message.equals("Second Wind Activated! Your Spirit Mask saved your life!") && omg.isEnabled() && (boolean)omg.spirit.getValue()) {
            AlertsListener.shouldShowSpiritMaskPoped = true;
            if (omg.rases.getValue()) {
                Music.playSound(this.getClass(), "wc.WAV");
            }
            else {
                Minecraft.getMinecraft().thePlayer.playSound("random.orb", 1.0f, 0.5f);
            }
        }
        if (message.equals("Your Bonzo's Mask saved your life!") && omg.isEnabled() && (boolean)omg.bonzo.getValue()) {
            AlertsListener.shouldShowBonzoMaskPoped = true;
            if (omg.rases.getValue()) {
                Music.playSound(this.getClass(), "wc.WAV");
            }
            else {
                Minecraft.getMinecraft().thePlayer.playSound("random.orb", 1.0f, 0.5f);
            }
        }
        if ((message.equals("Your \u269a Bonzo's Mask saved your life!") || message.equals("Your \u269a Bonzo's Mask saved your life!")) && omg.isEnabled() && (boolean)omg.bonzo.getValue()) {
            AlertsListener.shouldShowBonzoMask2Poped = true;
            if (omg.rases.getValue()) {
                Music.playSound(this.getClass(), "wc.WAV");
            }
            else {
                Minecraft.getMinecraft().thePlayer.playSound("random.orb", 1.0f, 0.5f);
            }
        }
    }
    
    static {
        AlertsListener.shouldShowWatcherReady = false;
        AlertsListener.shouldShowSpiritMaskPoped = false;
        AlertsListener.shouldShowBonzoMaskPoped = false;
        AlertsListener.shouldShowBonzoMask2Poped = false;
    }
}
