//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import org.spongepowered.asm.mixin.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.module.modules.QOL.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ Container.class })
public class MixinContainer
{
    @Inject(method = "putStacksInSlots", at = { @At("RETURN") })
    public void putStacksInSlots(final ItemStack[] stacks, final CallbackInfo ci) {
        final AutoEnchantTable aet = (AutoEnchantTable)Client.instance.getModuleManager().getModuleByClass(AutoEnchantTable.class);
        aet.processInventoryContents();
    }
}
