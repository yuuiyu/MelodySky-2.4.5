//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Swappings;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.Item.*;
import xyz.Melody.Event.*;

public class WitherImpact extends Module
{
    private Mode<Enum> mode;
    
    public WitherImpact() {
        super("WitherImpact", new String[] { "wi" }, ModuleType.Swapping);
        this.mode = (Mode<Enum>)new Mode("Sword", (Enum[])swords.values(), (Enum)swords.Hyperion);
        this.addValues(new Value[] { (Value)this.mode });
        this.setModInfo("Auto Swap Wither Impact Swords.");
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        final String upperCase = ((Enum)this.mode.getValue()).toString().toUpperCase();
        switch (upperCase) {
            case "HYPERION": {
                ItemUtils.useSBItem("HYPERION");
                break;
            }
            case "SCYLLA": {
                ItemUtils.useSBItem("SCYLLA");
                break;
            }
            case "ASTRAEA": {
                ItemUtils.useSBItem("ASTRAEA");
                break;
            }
            case "VALKYRIE": {
                ItemUtils.useSBItem("VALKYRIE");
                break;
            }
            case "NECRONBLADE": {
                ItemUtils.useSBItem("NECRON_BLADE");
                break;
            }
        }
        this.setEnabled(false);
    }
    
    enum swords
    {
        Hyperion, 
        Scylla, 
        Astraea, 
        Valkyrie, 
        NecronBlade;
    }
}
