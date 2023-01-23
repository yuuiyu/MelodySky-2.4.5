//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.items;

import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ ItemSword.class })
public interface ItemSwordAccessor
{
    @Accessor("attackDamage")
    float getAttackDamage();
}
