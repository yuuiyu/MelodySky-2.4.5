//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.client.gui.*;
import java.util.stream.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraftforge.fml.relauncher.*;
import com.google.common.collect.*;
import net.minecraft.world.*;
import net.minecraft.scoreboard.*;

public class PlayerListUtils
{
    private static Minecraft mc;
    private static final Ordering<NetworkPlayerInfo> playerOrdering;
    public static final Ordering<NetworkPlayerInfo> playerInfoOrdering2;
    
    public static GuiPlayerTabOverlay getTabList() {
        return PlayerListUtils.mc.ingameGUI.getTabList();
    }
    
    public static List<NetworkPlayerInfo> getTabEntries() {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return Collections.emptyList();
        }
        return (List<NetworkPlayerInfo>)PlayerListUtils.playerInfoOrdering2.sortedCopy((Iterable)Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfoMap());
    }
    
    public static List<String> getTabListListStr() {
        return getTabEntries().stream().map(playerInfo -> Minecraft.getMinecraft().ingameGUI.getTabList().getPlayerName(playerInfo)).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
    }
    
    public static boolean tabContains(final String str) {
        final List<NetworkPlayerInfo> players = (List<NetworkPlayerInfo>)PlayerListUtils.playerOrdering.sortedCopy((Iterable)Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfoMap());
        for (final NetworkPlayerInfo info : players) {
            final String name = StringUtils.stripControlCodes(Minecraft.getMinecraft().ingameGUI.getTabList().getPlayerName(info));
            if (name.contains(str)) {
                return true;
            }
        }
        return false;
    }
    
    public static String copyContainsLine(final String str) {
        final List<NetworkPlayerInfo> players = (List<NetworkPlayerInfo>)PlayerListUtils.playerOrdering.sortedCopy((Iterable)Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfoMap());
        for (final NetworkPlayerInfo info : players) {
            final String name = Minecraft.getMinecraft().ingameGUI.getTabList().getPlayerName(info);
            if (name.contains(str)) {
                return name;
            }
        }
        return null;
    }
    
    static {
        PlayerListUtils.mc = Minecraft.getMinecraft();
        playerOrdering = Ordering.from((Comparator)new PlayerComparator(null));
        playerInfoOrdering2 = (Ordering)new llI();
    }
    
    @SideOnly(Side.CLIENT)
    static class PlayerComparator implements Comparator<NetworkPlayerInfo>
    {
        private PlayerComparator() {
        }
        
        @Override
        public int compare(final NetworkPlayerInfo o1, final NetworkPlayerInfo o2) {
            final ScorePlayerTeam team1 = o1.getPlayerTeam();
            final ScorePlayerTeam team2 = o2.getPlayerTeam();
            return ComparisonChain.start().compareTrueFirst(o1.getGameType() != WorldSettings.GameType.SPECTATOR, o2.getGameType() != WorldSettings.GameType.SPECTATOR).compare((Comparable)((team1 != null) ? team1.getRegisteredName() : ""), (Comparable)((team2 != null) ? team2.getRegisteredName() : "")).compare((Comparable)o1.getGameProfile().getName(), (Comparable)o2.getGameProfile().getName()).result();
        }
    }
}
