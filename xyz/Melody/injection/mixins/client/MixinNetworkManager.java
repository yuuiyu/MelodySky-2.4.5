//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.client;

import org.spongepowered.asm.mixin.*;
import io.netty.channel.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraftforge.common.*;
import xyz.Melody.Event.events.FML.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.Event.*;
import org.spongepowered.asm.mixin.injection.*;
import xyz.Melody.Event.events.world.*;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = "channelRead0", at = { @At("HEAD") }, cancellable = true)
    private void read(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callback) {
        if (MinecraftForge.EVENT_BUS.post((Event)new PacketEvent.ReceiveEvent((Packet)packet))) {
            callback.cancel();
        }
        final EventPacketRecieve event = new EventPacketRecieve((Packet)packet);
        EventBus.getInstance().call((xyz.Melody.Event.Event)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = { @At("HEAD") }, cancellable = true)
    private void send(final Packet<?> packet, final CallbackInfo callback) {
        if (MinecraftForge.EVENT_BUS.post((Event)new PacketEvent.SendEvent((Packet)packet))) {
            callback.cancel();
        }
        final EventPacketSend event = new EventPacketSend((Packet)packet);
        EventBus.getInstance().call((xyz.Melody.Event.Event)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
