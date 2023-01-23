//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import net.minecraft.util.*;
import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.events.rendering.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.init.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.render.*;
import java.util.*;
import net.minecraft.block.*;
import xyz.Melody.Event.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FrozenTreasureESP extends Module
{
    public ArrayList<BlockPos> ices;
    
    public FrozenTreasureESP() {
        super("FrozenTreasureESP", new String[] { "ftesp", "frozenesp" }, ModuleType.QOL);
        this.ices = new ArrayList<BlockPos>();
        this.setColor(new Color(158, 205, 125).getRGB());
        this.setModInfo("FrozenTreasure Bounding Box.");
    }
    
    public void onDisable() {
        this.ices.clear();
        super.onDisable();
    }
    
    @EventHandler
    private void onR3D(final EventRender3D event) {
        if (ScoreboardUtils.scoreboardLowerContains("glacial cave")) {
            this.ices.clear();
            for (final Entity entity : this.mc.theWorld.loadedEntityList) {
                if (entity instanceof EntityArmorStand) {
                    final EntityArmorStand ass = (EntityArmorStand)entity;
                    if (ass.getEquipmentInSlot(4) == null) {
                        continue;
                    }
                    final Block dick = this.mc.theWorld.getBlockState(ass.getPosition().up()).getBlock();
                    if (dick != Blocks.ice && dick != Blocks.packed_ice) {
                        continue;
                    }
                    final int c = (dick == Blocks.ice) ? Colors.GREEN.c : Colors.ORANGE.c;
                    RenderUtil.drawSolidBlockESP(ass.getPosition().up(), c, 3.0f, event.getPartialTicks());
                    if (this.ices.contains(ass.getPosition().up())) {
                        continue;
                    }
                    this.ices.add(ass.getPosition().up());
                }
            }
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        this.ices.clear();
    }
}
