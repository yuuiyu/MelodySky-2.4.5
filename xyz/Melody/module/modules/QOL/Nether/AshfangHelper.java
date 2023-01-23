//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Nether;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.*;
import java.util.*;
import xyz.Melody.Event.*;
import xyz.Melody.Utils.math.*;
import net.minecraft.entity.player.*;

public class AshfangHelper extends Module
{
    private TimerUtil timer;
    
    public AshfangHelper() {
        super("AshfangHelper", new String[] { "mbe" }, ModuleType.Nether);
        this.timer = new TimerUtil();
        this.setColor(new Color(158, 205, 125).getRGB());
        this.setModInfo("Auto Shoot Orbs to Ashfang.");
    }
    
    @EventHandler
    public void onRenderEntity(final EventPreUpdate event) {
        for (final Entity ent : this.mc.theWorld.loadedEntityList) {
            if (ent instanceof EntityArmorStand) {
                final EntityArmorStand entity = (EntityArmorStand)ent;
                if (!entity.hasCustomName()) {
                    return;
                }
                final String entityName = StringUtils.stripControlCodes(entity.getCustomNameTag());
                if (entityName.equals("Blazing Soul")) {
                    this.onRenderOrb(ent, event);
                    return;
                }
                continue;
            }
        }
    }
    
    public boolean onRenderOrb(final Entity entityToInteract, final EventPreUpdate event) {
        Entity ashfang = null;
        for (final Entity entity : this.mc.theWorld.loadedEntityList) {
            if (entity instanceof EntityArmorStand && entity.hasCustomName() && StringUtils.stripControlCodes(entity.getCustomNameTag()).contains("Lv200] Ashfang")) {
                ashfang = entity;
                break;
            }
        }
        if (ashfang != null) {
            final float[] rotations = RotationUtil.getRotations(ashfang);
            event.setYaw(rotations[0]);
            event.setPitch(rotations[1]);
            if (this.timer.hasReached(100.0)) {
                this.mc.playerController.attackEntity((EntityPlayer)this.mc.thePlayer, entityToInteract);
                this.timer.reset();
                return true;
            }
        }
        return false;
    }
}
