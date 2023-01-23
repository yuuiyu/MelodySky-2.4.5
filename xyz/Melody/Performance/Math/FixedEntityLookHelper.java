//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.Math;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.*;
import xyz.Melody.injection.mixins.entity.*;
import net.minecraft.util.*;

public class FixedEntityLookHelper extends EntityLookHelper
{
    public FixedEntityLookHelper(final EntityLiving entity) {
        super(entity);
    }
    
    public void onUpdateLook() {
        ((EntityLookHelperAccessor)this).getEntity().rotationPitch = 0.0f;
        if (((EntityLookHelperAccessor)this).isLooking()) {
            ((EntityLookHelperAccessor)this).setLooking(false);
            final double d0 = ((EntityLookHelperAccessor)this).getPosX() - ((EntityLookHelperAccessor)this).getEntity().posX;
            final double d2 = ((EntityLookHelperAccessor)this).getPosY() - (((EntityLookHelperAccessor)this).getEntity().posY + ((EntityLookHelperAccessor)this).getEntity().getEyeHeight());
            final double d3 = ((EntityLookHelperAccessor)this).getPosZ() - ((EntityLookHelperAccessor)this).getEntity().posZ;
            final double d4 = MathHelper.sqrt_double(d0 * d0 + d3 * d3);
            final float f = (float)(tan(d3, d0) * 180.0 / 3.141592653589793) - 90.0f;
            final float f2 = (float)(-(tan(d2, d4) * 180.0 / 3.141592653589793));
            ((EntityLookHelperAccessor)this).getEntity().rotationPitch = this.updateRotation(((EntityLookHelperAccessor)this).getEntity().rotationPitch, f2, ((EntityLookHelperAccessor)this).getDeltaLookPitch());
            ((EntityLookHelperAccessor)this).getEntity().rotationYawHead = this.updateRotation(((EntityLookHelperAccessor)this).getEntity().rotationYawHead, f, ((EntityLookHelperAccessor)this).getDeltaLookYaw());
        }
        else {
            ((EntityLookHelperAccessor)this).getEntity().rotationYawHead = this.updateRotation(((EntityLookHelperAccessor)this).getEntity().rotationYawHead, ((EntityLookHelperAccessor)this).getEntity().renderYawOffset, 10.0f);
        }
        final float f3 = MathHelper.wrapAngleTo180_float(((EntityLookHelperAccessor)this).getEntity().rotationYawHead - ((EntityLookHelperAccessor)this).getEntity().renderYawOffset);
        if (!((EntityLookHelperAccessor)this).getEntity().getNavigator().noPath()) {
            if (f3 < -75.0f) {
                ((EntityLookHelperAccessor)this).getEntity().rotationYawHead = ((EntityLookHelperAccessor)this).getEntity().renderYawOffset - 75.0f;
            }
            if (f3 > 75.0f) {
                ((EntityLookHelperAccessor)this).getEntity().rotationYawHead = ((EntityLookHelperAccessor)this).getEntity().renderYawOffset + 75.0f;
            }
        }
    }
    
    private float updateRotation(final float a, final float b, final float c) {
        float f = MathHelper.wrapAngleTo180_float(b - a);
        if (f > c) {
            f = c;
        }
        if (f < -c) {
            f = -c;
        }
        return a + f;
    }
    
    public static float tan(final double a, final double b) {
        return FastTrig.atan2(a, b);
    }
}
