//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.rendering;

import xyz.Melody.Event.*;
import net.minecraft.entity.*;
import net.minecraft.client.model.*;

public class EventRenderEntityModel extends Event
{
    private EntityLivingBase entity;
    private float limbSwing;
    private float limbSwingAmount;
    private float ageInTicks;
    private float headYaw;
    private float headPitch;
    private float scaleFactor;
    private ModelBase model;
    
    public EventRenderEntityModel(final EntityLivingBase entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float headYaw, final float headPitch, final float scaleFactor, final ModelBase model) {
        this.entity = entity;
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.ageInTicks = ageInTicks;
        this.headYaw = headYaw;
        this.headPitch = headPitch;
        this.scaleFactor = scaleFactor;
        this.model = model;
    }
    
    public float getAgeInTicks() {
        return this.ageInTicks;
    }
    
    public EntityLivingBase getEntity() {
        return this.entity;
    }
    
    public float getHeadPitch() {
        return this.headPitch;
    }
    
    public float getHeadYaw() {
        return this.headYaw;
    }
    
    public float getLimbSwing() {
        return this.limbSwing;
    }
    
    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }
    
    public ModelBase getModel() {
        return this.model;
    }
    
    public float getScaleFactor() {
        return this.scaleFactor;
    }
}
