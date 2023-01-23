//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;

public class SayMimicKilled extends Module
{
    public Mode<Enum> mode;
    
    public SayMimicKilled() {
        super("SayMimicKilled", new String[] { "as" }, ModuleType.Dungeons);
        this.mode = (Mode<Enum>)new Mode("Mode", (Enum[])Chats.values(), (Enum)Chats.Party);
        this.addValues(new Value[] { (Value)this.mode });
        this.setModInfo("Say Mimic Killed When Mimic dead.");
    }
    
    public enum Chats
    {
        Party, 
        All;
    }
}
