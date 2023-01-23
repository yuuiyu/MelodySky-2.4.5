//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.entity;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.item.*;
import xyz.Melody.Event.events.misc.*;

@Mixin({ PlayerControllerMP.class })
public class MixinPlayerControllerMP
{
    @Inject(method = "attackEntity", at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;syncCurrentPlayItem()V") })
    private void attackEntity(final EntityPlayer entityPlayer, final Entity targetEntity, final CallbackInfo callbackInfo) {
        if (targetEntity == null) {
            return;
        }
        EventBus.getInstance().call((Event)new EventAttackEntity(targetEntity));
    }
    
    @Inject(method = "windowClick", at = { @At("HEAD") })
    private void windowClick(final int windowid, final int slot, final int button, final int mode, final EntityPlayer entityPlayer, final CallbackInfoReturnable<ItemStack> ci) {
        EventBus.getInstance().call((Event)new EventClickSlot(windowid, slot, button, mode, entityPlayer));
    }
}
