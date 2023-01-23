//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import net.minecraft.client.gui.inventory.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.inventory.*;
import xyz.Melody.Utils.animate.*;
import xyz.Melody.GUI.ClickNew.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.module.modules.QOL.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.injection.*;
import xyz.Melody.module.modules.render.*;
import xyz.Melody.Event.events.container.*;
import xyz.Melody.Event.*;
import java.util.concurrent.atomic.*;

@Mixin({ GuiContainer.class })
public abstract class MixinGuiContainer extends MixinGuiScreen
{
    @Shadow
    public Container inventorySlots;
    @Shadow
    private Slot theSlot;
    public Translate translate;
    public Opacity opacity;
    private static final String TARGET_GETSTACK = "Lnet/minecraft/inventory/Slot;getStack()Lnet/minecraft/item/ItemStack;";
    
    public MixinGuiContainer() {
        this.translate = new Translate(0.0f, 0.0f);
        this.opacity = new Opacity(1);
    }
    
    @Inject(method = "initGui", at = { @At("HEAD") })
    private void init(final CallbackInfo callbackInfo) {
        this.opacity = new Opacity(1);
        super.initGui();
    }
    
    @Inject(method = "drawSlot", at = { @At("HEAD") }, cancellable = true)
    public void drawSlot(final Slot slot, final CallbackInfo ci) {
        if (slot == null) {
            return;
        }
        final ItemStack stack = slot.getStack();
        if (stack != null) {
            final AutoEnchantTable aet = (AutoEnchantTable)Client.instance.getModuleManager().getModuleByClass(AutoEnchantTable.class);
            if (aet.onStackRender(stack, slot.inventory, slot.getSlotIndex(), slot.xDisplayPosition, slot.yDisplayPosition)) {
                ci.cancel();
                return;
            }
        }
        RenderHelper.enableGUIStandardItemLighting();
    }
    
    @Redirect(method = "drawSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Slot;getStack()Lnet/minecraft/item/ItemStack;"))
    public ItemStack drawSlot_getStack(final Slot slot) {
        ItemStack stack = slot.getStack();
        if (stack != null) {
            final AutoEnchantTable aet = (AutoEnchantTable)Client.instance.getModuleManager().getModuleByClass(AutoEnchantTable.class);
            final ItemStack newStack = aet.overrideStack(slot.inventory, slot.getSlotIndex(), stack);
            if (newStack != null) {
                stack = newStack;
            }
        }
        return stack;
    }
    
    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Slot;getStack()Lnet/minecraft/item/ItemStack;"))
    public ItemStack drawScreen_getStack(final Slot slot) {
        if (this.theSlot != null && this.theSlot == slot && this.theSlot.getStack() != null) {
            final AutoEnchantTable aet = (AutoEnchantTable)Client.instance.getModuleManager().getModuleByClass(AutoEnchantTable.class);
            final ItemStack newStack = aet.overrideStack(this.theSlot.inventory, this.theSlot.getSlotIndex(), this.theSlot.getStack());
            if (newStack != null) {
                return newStack;
            }
        }
        return slot.getStack();
    }
    
    @Inject(method = "drawScreen", at = { @At("HEAD") })
    private void drawScreen(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo callbackInfo) {
        final HUD hud = (HUD)Client.instance.getModuleManager().getModuleByClass(HUD.class);
        this.dick(true);
        hud.handleContainer(this.translate, this.opacity, (float)this.width, (float)this.height);
    }
    
    @Inject(method = "drawSlot", at = { @At("HEAD") }, cancellable = true)
    private void beforeDrawSlot(final Slot slot, final CallbackInfo callbackInfo) {
        final DrawSlotEvent event = new DrawSlotEvent(this.inventorySlots, slot);
        EventBus.getInstance().call((Event)event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = "handleMouseClick", at = { @At("HEAD") }, cancellable = true)
    public void handleMouseClick(final Slot slotIn, final int slotId, final int clickedButton, final int clickType, final CallbackInfo ci) {
        final GuiContainer $this = (GuiContainer)this;
        final AtomicBoolean ret = new AtomicBoolean(false);
        if (ret.get()) {
            return;
        }
        if (slotIn != null && slotIn.getStack() != null) {
            final AutoEnchantTable aet = (AutoEnchantTable)Client.instance.getModuleManager().getModuleByClass(AutoEnchantTable.class);
            if (aet.onStackClick(slotIn.getStack(), $this.inventorySlots.windowId, slotId, clickedButton, clickType)) {
                ci.cancel();
            }
        }
    }
    
    @Override
    protected void drawDefaultBackground() {
    }
    
    private void dick(final boolean truefalse) {
        if (!truefalse) {
            return;
        }
        final HUD hud = (HUD)Client.instance.getModuleManager().getModuleByClass(HUD.class);
        if (!hud.isEnabled() || !(boolean)hud.blur.getValue()) {
            this.drawWorldBackground(0);
        }
    }
}
