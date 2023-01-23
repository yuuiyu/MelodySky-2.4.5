//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.rendering;

import xyz.Melody.Event.*;
import net.minecraft.scoreboard.*;
import net.minecraft.client.gui.*;

public class EventRenderScoreboard extends Event
{
    private ScoreObjective objective;
    private ScaledResolution scaledRes;
    
    public EventRenderScoreboard(final ScoreObjective objective, final ScaledResolution scaledRes) {
        this.objective = objective;
        this.scaledRes = scaledRes;
    }
    
    public ScoreObjective getObjective() {
        return this.objective;
    }
    
    public ScaledResolution getScaledRes() {
        return this.scaledRes;
    }
}
