//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.packets;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.network.*;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.module.modules.QOL.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ NetHandlerPlayClient.class })
public class MixinNetHandlerPlayClient
{
    @Inject(method = "handleSetSlot", at = { @At("RETURN") })
    public void handleSetSlot(final S2FPacketSetSlot packetIn, final CallbackInfo ci) {
        final AutoEnchantTable aet = (AutoEnchantTable)Client.instance.getModuleManager().getModuleByClass(AutoEnchantTable.class);
        aet.processInventoryContents();
    }
}
