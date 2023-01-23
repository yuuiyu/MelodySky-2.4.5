//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.game;

import net.minecraft.client.*;
import org.lwjgl.util.vector.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.util.*;

public class RayCastUtils
{
    private static Minecraft mc;
    
    public static boolean isFacingBlock(final BlockPos block, final float range) {
        final float stepSize = 0.15f;
        if (RayCastUtils.mc.thePlayer != null && RayCastUtils.mc.theWorld != null) {
            final Vector3f position = new Vector3f((float)RayCastUtils.mc.thePlayer.posX, (float)RayCastUtils.mc.thePlayer.posY + RayCastUtils.mc.thePlayer.getEyeHeight(), (float)RayCastUtils.mc.thePlayer.posZ);
            final Vec3 look = RayCastUtils.mc.thePlayer.getLook(0.0f);
            final Vector3f step = new Vector3f((float)look.xCoord, (float)look.yCoord, (float)look.zCoord);
            step.scale(stepSize / step.length());
            for (int i = 0; i < Math.floor(range / stepSize) - 2.0; ++i) {
                final BlockPos blockAtPos = new BlockPos((double)position.x, (double)position.y, (double)position.z);
                if (blockAtPos.equals((Object)block)) {
                    return true;
                }
                position.translate(step.x, step.y, step.z);
            }
        }
        return false;
    }
    
    public static <T extends Entity> List<T> getFacedEntityOfType(final Class<T> _class, final float range) {
        final float stepSize = 0.5f;
        if (RayCastUtils.mc.thePlayer != null && RayCastUtils.mc.theWorld != null) {
            final Vector3f position = new Vector3f((float)RayCastUtils.mc.thePlayer.posX, (float)RayCastUtils.mc.thePlayer.posY + RayCastUtils.mc.thePlayer.getEyeHeight(), (float)RayCastUtils.mc.thePlayer.posZ);
            final Vec3 look = RayCastUtils.mc.thePlayer.getLook(0.0f);
            final Vector3f step = new Vector3f((float)look.xCoord, (float)look.yCoord, (float)look.zCoord);
            step.scale(stepSize / step.length());
            for (int i = 0; i < Math.floor(range / stepSize) - 2.0; ++i) {
                final List<T> entities = (List<T>)RayCastUtils.mc.theWorld.getEntitiesWithinAABB((Class)_class, new AxisAlignedBB(position.x - 0.5, position.y - 0.5, position.z - 0.5, position.x + 0.5, position.y + 0.5, position.z + 0.5));
                if (!entities.isEmpty()) {
                    return entities;
                }
                position.translate(step.x, step.y, step.z);
            }
        }
        return null;
    }
    
    static {
        RayCastUtils.mc = Minecraft.getMinecraft();
    }
}
