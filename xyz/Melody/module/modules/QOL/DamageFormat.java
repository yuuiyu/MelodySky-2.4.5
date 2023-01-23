//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.*;
import xyz.Melody.Event.*;
import java.util.*;

public class DamageFormat extends Module
{
    private String[] colors;
    private static final NavigableMap<Long, String> suffixes;
    
    public DamageFormat() {
        super("DamageFormat", new String[] { "gb" }, ModuleType.QOL);
        this.colors = new String[] { "§f", "§e", "§6", "§c", "§f", "§f", "§f", "§f" };
        this.setModInfo("Change The Damage Numbers to xxxM or xxxk.");
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        if (this.mc.theWorld == null) {
            return;
        }
        for (final Entity entity : this.mc.theWorld.loadedEntityList) {
            if (this.mc.thePlayer.getDistanceToEntity(entity) > 24.0f) {
                continue;
            }
            if (!(entity instanceof EntityArmorStand)) {
                continue;
            }
            final EntityArmorStand as = (EntityArmorStand)entity;
            final char idk = '\"';
            if (!as.hasCustomName() || (!as.getCustomNameTag().contains("\u2727") && !as.getCustomNameTag().contains("\u2727") && !as.getCustomNameTag().contains("\u272f") && !as.getCustomNameTag().contains("\u272f")) || as.getCustomNameTag().contains("M") || as.getCustomNameTag().contains("b") || as.getCustomNameTag().contains("k")) {
                continue;
            }
            final String num = StringUtils.stripControlCodes(as.getName().replaceAll(",", "").replaceAll("\u2727", "").replaceAll("\u272f", "").replaceAll("\u272f", "").replaceAll("\u2764", "").replaceAll("\u2727", "").replaceAll(String.valueOf(idk), "").replaceAll("'", "").replaceAll("text", "").replaceAll(":", "").replaceAll("}", "").replaceFirst("\\{", ""));
            final long number = Long.parseLong(num);
            final String finalName = this.format(number);
            String formattedName = "";
            for (int i = 0; i < finalName.length(); ++i) {
                final char c = finalName.charAt(i);
                String colorPrefix = this.colors[0];
                if (i >= this.colors.length) {
                    colorPrefix = this.colors[0];
                }
                else {
                    colorPrefix = this.colors[i];
                }
                formattedName = formattedName + colorPrefix + String.valueOf(c);
            }
            as.setCustomNameTag("§r\u2727" + formattedName + "§e\u2727§r");
        }
    }
    
    public String format(final long value) {
        if (value == Long.MIN_VALUE) {
            return this.format(-9223372036854775807L);
        }
        if (value < 0L) {
            return "-" + this.format(-value);
        }
        if (value < 1000L) {
            return Long.toString(value);
        }
        final Map.Entry<Long, String> e = DamageFormat.suffixes.floorEntry(value);
        final Long divideBy = e.getKey();
        final String suffix = e.getValue();
        final long truncated = value / (divideBy / 10L);
        final boolean hasDecimal = truncated < 100L && truncated / 10.0 != truncated / 10L;
        return hasDecimal ? (truncated / 10.0 + suffix) : (truncated / 10L + suffix);
    }
    
    static {
        (suffixes = new TreeMap<Long, String>()).put(1000L, "k");
        DamageFormat.suffixes.put(1000000L, "M");
        DamageFormat.suffixes.put(1000000000L, "b");
    }
}
