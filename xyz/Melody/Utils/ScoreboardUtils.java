//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import net.minecraft.util.*;
import net.minecraft.client.*;
import java.util.stream.*;
import com.google.common.collect.*;
import net.minecraft.scoreboard.*;
import java.util.*;
import xyz.Melody.Utils.other.*;

public class ScoreboardUtils
{
    public static String cleanSB(final String scoreboard) {
        final char[] nvString = StringUtils.stripControlCodes(scoreboard).toCharArray();
        final StringBuilder cleaned = new StringBuilder();
        for (final char c : nvString) {
            if (c > '\u0014') {
                if (c < '\u007f') {
                    cleaned.append(c);
                }
            }
        }
        return cleaned.toString();
    }
    
    public static List<String> getScoreboard() {
        final ArrayList<String> lines = new ArrayList<String>();
        if (Minecraft.getMinecraft().theWorld == null) {
            return lines;
        }
        final Scoreboard scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
        if (scoreboard == null) {
            return lines;
        }
        final ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);
        if (objective == null) {
            return lines;
        }
        Collection<Score> scores = (Collection<Score>)scoreboard.getSortedScores(objective);
        final List<Score> list = scores.stream().filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#")).collect((Collector<? super Score, ?, List<Score>>)Collectors.toList());
        scores = ((list.size() > 15) ? Lists.newArrayList(Iterables.skip((Iterable)list, scores.size() - 15)) : list);
        for (final Score score : scores) {
            final ScorePlayerTeam team = scoreboard.getPlayersTeam(score.getPlayerName());
            lines.add(ScorePlayerTeam.formatPlayerName((Team)team, score.getPlayerName()));
        }
        return lines;
    }
    
    public static int getLinesNumber() {
        return getScoreboard().size();
    }
    
    public static boolean scoreboardContains(final String string) {
        boolean result = false;
        final List<String> scoreboard = getScoreboard();
        for (String line : scoreboard) {
            line = cleanSB(line);
            if (!(line = StringUtil.removeFormatting(line)).contains(string)) {
                continue;
            }
            result = true;
            break;
        }
        return result;
    }
    
    public static boolean scoreboardLowerContains(final String string) {
        boolean result = false;
        final List<String> scoreboard = getScoreboard();
        for (String line : scoreboard) {
            line = cleanSB(line).toLowerCase();
            if (!(line = StringUtil.removeFormatting(line)).contains(string)) {
                continue;
            }
            result = true;
            break;
        }
        return result;
    }
    
    public static String getLineThatContains(final String string) {
        String result = null;
        final List<String> scoreboard = getScoreboard();
        for (String line : scoreboard) {
            if (!(line = cleanSB(line)).contains(string)) {
                continue;
            }
            result = line;
            break;
        }
        return result;
    }
}
