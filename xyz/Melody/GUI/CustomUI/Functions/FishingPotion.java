//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import xyz.Melody.Utils.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import xyz.Melody.injection.mixins.gui.*;
import net.minecraft.util.*;
import java.util.regex.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;

public class FishingPotion extends HUDApi
{
    private TimerUtil timer;
    private String shabi;
    
    public FishingPotion() {
        super("FishingPotion", 50, 30);
        this.timer = new TimerUtil();
        this.shabi = "";
        this.setEnabled(true);
    }
    
    @EventHandler
    public void tick(final EventTick event) {
        if (!Client.inSkyblock) {
            return;
        }
        if (this.timer.hasReached(250.0)) {
            final IChatComponent tabFooterChatComponent = ((GuiPlayerTabAccessor)this.mc.ingameGUI.getTabList()).getFooter();
            String tabFooterString = null;
            String strippedTabFooterString = null;
            if (tabFooterChatComponent != null) {
                tabFooterString = tabFooterChatComponent.getFormattedText();
                strippedTabFooterString = Pattern.compile("(?i)§[0-9A-FK-ORZ]").matcher(tabFooterString).replaceAll("");
            }
            if (tabFooterString == null) {
                return;
            }
            final Pattern sb = Pattern.compile("Tonic I (?<min>[0-9.]+) Minutes");
            final Pattern sb2 = Pattern.compile("Tonic I (?<hour>[0-9.]+)h (?<min>[0-9.]+)m");
            final Pattern sb3 = Pattern.compile("Tonic I (?<min>[0-9.]+)m (?<sec>[0-9.]+)s");
            final Pattern sb4 = Pattern.compile("Tonic I (?<sec>[0-9.]+)s");
            final Matcher matcher = sb.matcher(strippedTabFooterString);
            final Matcher matcher2 = sb2.matcher(strippedTabFooterString);
            final Matcher matcher3 = sb3.matcher(strippedTabFooterString);
            final Matcher matcher4 = sb4.matcher(strippedTabFooterString);
            String hour = null;
            String minute = null;
            String sec = null;
            while (matcher.find()) {
                minute = matcher.group("min");
            }
            while (matcher2.find()) {
                minute = matcher2.group("min");
                hour = matcher2.group("hour");
            }
            while (matcher3.find()) {
                minute = matcher3.group("min");
                sec = matcher3.group("sec");
            }
            while (matcher4.find()) {
                sec = matcher4.group("sec");
            }
            String ps = null;
            if (minute != null) {
                ps = EnumChatFormatting.GREEN + "Mushed Glowy Tonic: " + EnumChatFormatting.LIGHT_PURPLE + minute + EnumChatFormatting.GREEN + " Min Left.";
                if (hour != null) {
                    final int htm = Integer.parseInt(hour) * 60;
                    final int m = Integer.parseInt(minute);
                    ps = EnumChatFormatting.GREEN + "Mushed Glowy Tonic: " + EnumChatFormatting.LIGHT_PURPLE + (htm + m) + EnumChatFormatting.GREEN + " Min Left.";
                }
                if (sec != null) {
                    final int mts = Integer.parseInt(minute) * 60;
                    final int s = Integer.parseInt(sec);
                    ps = EnumChatFormatting.GREEN + "Mushed Glowy Tonic: " + EnumChatFormatting.LIGHT_PURPLE + (mts + s) + EnumChatFormatting.GREEN + " Seconds Left.";
                }
            }
            if (minute == null && hour == null && sec != null) {
                final int s2 = Integer.parseInt(sec);
                ps = EnumChatFormatting.GREEN + "Mushed Glowy Tonic: " + EnumChatFormatting.RED + s2 + EnumChatFormatting.GREEN + " Seconds Left.";
            }
            if ((this.shabi = ps) == null) {
                this.shabi = EnumChatFormatting.RED + "Mushed Glowy Tonic Effect Not Actived.";
            }
            this.timer.reset();
        }
    }
    
    @EventHandler
    public void onRender(final EventRender2D event) {
        if (this.mc.currentScreen instanceof HUDScreen) {
            return;
        }
        this.render();
    }
    
    @Override
    public void InScreenRender() {
        this.render();
    }
    
    private void render() {
        if (this.shabi != null) {
            final CFontRenderer cfr = FontLoaders.NMSL22;
            RenderUtil.drawFastRoundedRect((float)(this.x + 1), (float)this.y, (float)(this.x + cfr.getStringWidth(this.shabi) + 6), (float)(this.y + 16), 2.0f, new Color(30, 30, 30, 130).getRGB());
            cfr.drawString(this.shabi, (float)(this.x + 4), (float)(this.y + 4), -1);
        }
    }
}
