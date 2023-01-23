//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import net.minecraft.nbt.*;
import xyz.Melody.Event.*;

public class NoNBTUpdate extends Module
{
    public NoNBTUpdate() {
        super("NoNBTUpdate", new String[] { "nbt" }, ModuleType.QOL);
        this.setColor(new Color(191, 191, 191).getRGB());
        this.setModInfo("Anti NBT Updates on Drill/Gauntlets.");
    }
    
    @EventHandler
    private void onPacketRCV(final EventPacketRecieve e) {
        if (e.getPacket() instanceof S2FPacketSetSlot) {
            final S2FPacketSetSlot pack = (S2FPacketSetSlot)e.getPacket();
            final NBTTagCompound extraAttributes;
            if (pack.func_149174_e() != null && (extraAttributes = pack.func_149174_e().getSubCompound("ExtraAttributes", false)) != null && extraAttributes.hasKey("id")) {
                if (extraAttributes.getString("id").contains("GEMSTONE_GAUNTLET")) {
                    e.setCancelled(true);
                }
                if (pack.func_149174_e().getItem() == Items.prismarine_shard) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
