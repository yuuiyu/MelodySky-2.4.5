//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import net.minecraft.client.network.*;
import com.google.common.collect.*;
import net.minecraft.world.*;
import net.minecraft.scoreboard.*;

final class llI extends Ordering<NetworkPlayerInfo>
{
    public int compare(final NetworkPlayerInfo p_compare_1_, final NetworkPlayerInfo p_compare_2_) {
        final ScorePlayerTeam scoreplayerteam = p_compare_1_.getPlayerTeam();
        final ScorePlayerTeam scoreplayerteam2 = p_compare_2_.getPlayerTeam();
        return ComparisonChain.start().compareTrueFirst(p_compare_1_.getGameType() != WorldSettings.GameType.SPECTATOR, p_compare_2_.getGameType() != WorldSettings.GameType.SPECTATOR).compare((Comparable)((scoreplayerteam != null) ? scoreplayerteam.getRegisteredName() : ""), (Comparable)((scoreplayerteam2 != null) ? scoreplayerteam2.getRegisteredName() : "")).compare((Comparable)p_compare_1_.getGameProfile().getName(), (Comparable)p_compare_2_.getGameProfile().getName()).result();
    }
}
