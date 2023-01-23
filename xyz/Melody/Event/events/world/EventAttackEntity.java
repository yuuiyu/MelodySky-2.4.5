//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.world;

import xyz.Melody.Event.*;
import net.minecraft.entity.*;

public class EventAttackEntity extends Event
{
    private Entity entity;
    
    public EventAttackEntity(final Entity entity) {
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
}
