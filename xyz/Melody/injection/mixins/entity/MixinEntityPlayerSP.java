//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.entity;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.network.*;
import net.minecraft.util.*;
import xyz.Melody.Event.events.misc.*;
import xyz.Melody.Event.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import xyz.Melody.module.balance.*;
import xyz.Melody.module.*;
import org.spongepowered.asm.mixin.injection.*;

@SideOnly(Side.CLIENT)
@Mixin({ EntityPlayerSP.class })
public abstract class MixinEntityPlayerSP extends MixinEntityPlayer
{
    private double cachedX;
    private double cachedY;
    private double cachedZ;
    private float cachedRotationPitch;
    private float cachedRotationYaw;
    @Shadow
    @Final
    public NetHandlerPlayClient sendQueue;
    @Shadow
    public MovementInput movementInput;
    @Shadow
    public float renderArmYaw;
    @Shadow
    public float renderArmPitch;
    @Shadow
    public float prevRenderArmYaw;
    @Shadow
    public float prevRenderArmPitch;
    
    @Shadow
    protected abstract boolean isCurrentViewEntity();
    
    @Overwrite
    public void sendChatMessage(final String message) {
        final EventChat event = new EventChat(message);
        EventBus.getInstance().call((Event)event);
        if (event.isCancelled()) {
            return;
        }
        this.sendQueue.addToSendQueue((Packet)new C01PacketChatMessage(message));
    }
    
    @Inject(method = "onUpdateWalkingPlayer", at = { @At("HEAD") }, cancellable = true)
    private void onUpdateWalkingPlayerPre(final CallbackInfo ci) {
        final EventPreUpdate event = new EventPreUpdate(this.rotationYaw, this.rotationPitch, this.posX, this.posY, this.posZ, this.onGround);
        EventBus.getInstance().call((Event)event);
        if (event.isCancelled()) {
            EventBus.getInstance().call((Event)new EventPostUpdate(this.rotationYaw, this.rotationPitch));
            ci.cancel();
        }
        this.cachedX = this.posX;
        this.cachedY = this.posY;
        this.cachedZ = this.posZ;
        this.cachedRotationYaw = this.rotationYaw;
        this.cachedRotationPitch = this.rotationPitch;
        this.posX = event.getX();
        this.posY = event.getY();
        this.posZ = event.getZ();
        this.rotationYaw = event.getYaw();
        this.rotationPitch = event.getPitch();
    }
    
    @Inject(method = "onUpdateWalkingPlayer", at = { @At("RETURN") })
    private void onUpdateWalkingPlayerPost(final CallbackInfo ci) {
        this.posX = this.cachedX;
        this.posY = this.cachedY;
        this.posZ = this.cachedZ;
        this.rotationYaw = this.cachedRotationYaw;
        this.rotationPitch = this.cachedRotationPitch;
        EventBus.getInstance().call((Event)new EventPostUpdate(this.rotationYaw, this.rotationPitch));
    }
    
    @Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isUsingItem()Z"))
    public boolean isUsingItem(final EntityPlayerSP instance) {
        return (!Client.instance.getModuleManager().getModuleByClass(NoSlowDown.class).isEnabled() || !instance.isUsingItem()) && instance.isUsingItem();
    }
    
    @Overwrite
    public void updateEntityActionState() {
        super.updateEntityActionState();
        if (this.isCurrentViewEntity()) {
            this.moveStrafing = this.movementInput.moveStrafe;
            this.moveForward = this.movementInput.moveForward;
            this.isJumping = this.movementInput.jump;
            this.prevRenderArmYaw = this.renderArmYaw;
            this.prevRenderArmPitch = this.renderArmPitch;
            this.renderArmPitch += (this.rotationPitch - this.renderArmPitch) * 0.5f;
            this.renderArmYaw += (this.rotationYaw - this.renderArmYaw) * 0.5f;
            Client.instance.rotationPitchHead = this.rotationPitch;
        }
    }
}
