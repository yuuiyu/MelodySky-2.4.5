//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.client;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.*;
import org.lwjgl.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.Event.*;
import xyz.Melody.*;
import xyz.Melody.module.balance.*;
import xyz.Melody.module.*;
import org.spongepowered.asm.mixin.injection.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.injection.mixins.entity.*;
import net.minecraft.item.*;
import org.lwjgl.input.*;
import xyz.Melody.Event.events.misc.*;
import net.minecraft.crash.*;
import xyz.Melody.Event.events.world.*;
import org.apache.logging.log4j.*;

@SideOnly(Side.CLIENT)
@Mixin({ Minecraft.class })
public abstract class MixinMinecraft
{
    @Shadow
    public GuiScreen currentScreen;
    @Shadow
    private static final Logger logger;
    @Shadow
    private int leftClickCounter;
    private long lastFrame;
    
    public MixinMinecraft() {
        this.lastFrame = this.getTime();
    }
    
    public long getTime() {
        return Sys.getTime() * 1000L / Sys.getTimerResolution();
    }
    
    @Inject(method = "clickMouse", at = { @At("HEAD") }, cancellable = true)
    private void onLeftClick(final CallbackInfo ci) {
        final EventClick ce = new EventClick();
        final EventBus instance = EventBus.getInstance();
        final EventClick eventClick = ce;
        eventClick.getClass();
        final EventClick.LeftClick left = (EventClick.LeftClick)instance.call((Event)new EventClick.LeftClick(eventClick));
        EventBus.getInstance().call((Event)ce);
        if (left.isCancelled()) {
            ci.cancel();
        }
        if (Client.instance.getModuleManager().getModuleByClass(NoHitDelay.class).isEnabled()) {
            this.leftClickCounter = 0;
        }
    }
    
    @Inject(method = "rightClickMouse", at = { @At("HEAD") })
    public void rightClickMouse(final CallbackInfo ci) {
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass(OldAnimations.class);
        if (anim.isEnabled() && (boolean)anim.punching.getValue() && ((PlayerControllerAccessor)Minecraft.getMinecraft().playerController).isHittingBlock() && Minecraft.getMinecraft().thePlayer.getHeldItem() != null && (Minecraft.getMinecraft().thePlayer.getHeldItem().getItemUseAction() != EnumAction.NONE || Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() instanceof ItemBlock)) {
            Minecraft.getMinecraft().playerController.resetBlockRemoving();
        }
    }
    
    @Inject(method = "rightClickMouse", at = { @At("HEAD") }, cancellable = true)
    private void onRightClick(final CallbackInfo ci) {
        final EventClick ce = new EventClick();
        final EventBus instance = EventBus.getInstance();
        final EventClick eventClick = ce;
        eventClick.getClass();
        final EventClick.RightClick right = (EventClick.RightClick)instance.call((Event)new EventClick.RightClick(eventClick));
        EventBus.getInstance().call((Event)ce);
        if (right.isCancelled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = "startGame", at = { @At("HEAD") })
    private void run(final CallbackInfo ci) {
        Client.preCharset();
    }
    
    @Inject(method = "startGame", at = { @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.BEFORE) })
    private void startGame(final CallbackInfo ci) {
        Client.instance.start();
    }
    
    @Inject(method = "runTick", at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER) })
    private void onKey(final CallbackInfo ci) {
        if (Keyboard.getEventKeyState() && this.currentScreen == null) {
            EventBus.getInstance().call((Event)new EventKey((Keyboard.getEventKey() == 0) ? (Keyboard.getEventCharacter() + '\u0100') : Keyboard.getEventKey()));
        }
    }
    
    @Inject(method = "shutdown", at = { @At("HEAD") })
    private void onShutdown(final CallbackInfo ci) {
        Client.instance.stop();
    }
    
    @Inject(method = "displayCrashReport", at = { @At("HEAD") })
    public void displayCrashReport(final CrashReport crashReportIn, final CallbackInfo ci) {
        Client.instance.stop();
    }
    
    @Inject(method = "runTick", at = { @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;joinPlayerCounter:I", shift = At.Shift.BEFORE) })
    private void onTick(final CallbackInfo callbackInfo) {
        final EventTick e = new EventTick();
        EventBus.getInstance().call((Event)e);
    }
    
    static {
        logger = LogManager.getLogger();
    }
}
