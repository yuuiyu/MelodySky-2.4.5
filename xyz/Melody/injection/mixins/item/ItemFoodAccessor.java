//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.item;

import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ ItemFood.class })
public interface ItemFoodAccessor
{
    @Accessor("alwaysEdible")
    boolean getAlwaysEdible();
}
