//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.Math;

import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.entity.*;
import net.minecraft.entity.ai.*;
import xyz.Melody.injection.mixins.entity.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AIImprovements
{
    public void postInit(final FMLPostInitializationEvent event) {
        FastTrig.init();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinWorldEvent event) {
        final Entity entity = event.entity;
        if (entity instanceof EntityLiving) {
            final EntityLiving living = (EntityLiving)entity;
            final Iterator<EntityAITasks.EntityAITaskEntry> it = living.tasks.taskEntries.iterator();
            while (it.hasNext()) {
                final Object obj = it.next();
                if (obj instanceof EntityAITasks.EntityAITaskEntry) {
                    final EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
                    if (task.action instanceof EntityAIWatchClosest) {
                        it.remove();
                    }
                    else {
                        if (!(task.action instanceof EntityAILookIdle)) {
                            continue;
                        }
                        it.remove();
                    }
                }
            }
            if (living.getLookHelper() == null || living.getLookHelper().getClass() == EntityLookHelper.class) {
                final EntityLookHelper oldHelper = living.getLookHelper();
                ((EntityLivingAccessor)living).setLookHelper((EntityLookHelper)new FixedEntityLookHelper(living));
                ((EntityLookHelperAccessor)living.getLookHelper()).setPosX(((EntityLookHelperAccessor)oldHelper).getPosX());
                ((EntityLookHelperAccessor)living.getLookHelper()).setPosY(((EntityLookHelperAccessor)oldHelper).getPosY());
                ((EntityLookHelperAccessor)living.getLookHelper()).setPosZ(((EntityLookHelperAccessor)oldHelper).getPosZ());
                ((EntityLookHelperAccessor)living.getLookHelper()).setLooking(((EntityLookHelperAccessor)oldHelper).isLooking());
                ((EntityLookHelperAccessor)living.getLookHelper()).setDeltaLookPitch(((EntityLookHelperAccessor)oldHelper).getDeltaLookPitch());
                ((EntityLookHelperAccessor)living.getLookHelper()).setDeltaLookYaw(((EntityLookHelperAccessor)oldHelper).getDeltaLookYaw());
            }
        }
    }
}
