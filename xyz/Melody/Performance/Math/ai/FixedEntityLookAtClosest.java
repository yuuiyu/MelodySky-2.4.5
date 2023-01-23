//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.Math.ai;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.*;

public class FixedEntityLookAtClosest extends EntityAIWatchClosest
{
    public FixedEntityLookAtClosest(final EntityLiving entity, final Class cz, final float distance) {
        super(entity, cz, distance);
    }
    
    public FixedEntityLookAtClosest(final EntityLiving entity, final Class cz, final float distance, final float updateRndTrigger) {
        super(entity, cz, distance, updateRndTrigger);
    }
}
