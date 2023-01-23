//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Swappings;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.Item.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Event.*;

public class AutoZombieSword extends Module
{
    private Mode<Enum> type;
    
    public AutoZombieSword() {
        super("AutoHeal", new String[] { "aheal" }, ModuleType.Swapping);
        this.type = (Mode<Enum>)new Mode("Sword", (Enum[])swordType.values(), (Enum)swordType.FloridZombie);
        this.addValues(new Value[] { (Value)this.type });
        this.setModInfo("Auto Swap Zombie Swords and Use 5 Times.");
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        for (int i = 0; i < 5; ++i) {
            final String upperCase = ((Enum)this.type.getValue()).toString().toUpperCase();
            switch (upperCase) {
                case "ZOMBIE": {
                    ItemUtils.useSBItem("ZOMBIE_SWORD");
                    break;
                }
                case "ORNATEZOMBIE": {
                    ItemUtils.useSBItem("ORNATE_ZOMBIE_SWORD");
                    break;
                }
                case "FLORIDZOMBIE": {
                    ItemUtils.useSBItem("FLORID_ZOMBIE_SWORD");
                    break;
                }
            }
        }
        Helper.sendMessage("AutoZombieSword Disabled.");
        this.setEnabled(false);
    }
    
    enum swordType
    {
        FloridZombie, 
        OrnateZombie, 
        Zombie;
    }
}
