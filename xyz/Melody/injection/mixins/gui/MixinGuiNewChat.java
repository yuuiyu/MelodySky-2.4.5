//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;
import net.minecraft.client.gui.*;
import com.google.common.collect.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.ClientLib.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiNewChat.class })
public abstract class MixinGuiNewChat
{
    @Shadow
    private Minecraft mc;
    @Shadow
    private int scrollPos;
    @Shadow
    private boolean isScrolled;
    @Shadow
    private final List<ChatLine> drawnChatLines;
    
    public MixinGuiNewChat() {
        this.drawnChatLines = (List<ChatLine>)Lists.newArrayList();
    }
    
    @Shadow
    public abstract int getLineCount();
    
    @Shadow
    public abstract boolean getChatOpen();
    
    @Shadow
    public abstract float getChatScale();
    
    @Shadow
    public abstract int getChatWidth();
    
    @Inject(method = "drawChat", at = { @At("HEAD") }, cancellable = true)
    public void drawChat(final int updateCounter, final CallbackInfo ci) {
        if (this.mc.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
            final int i = this.getLineCount();
            boolean flag = false;
            final int k = this.drawnChatLines.size();
            final float f = this.mc.gameSettings.chatOpacity * 0.9f + 0.1f;
            if (k > 0) {
                if (this.getChatOpen()) {
                    flag = true;
                }
                final float f2 = this.getChatScale();
                final int l = MathHelper.ceiling_float_int(this.getChatWidth() / f2);
                GlStateManager.pushMatrix();
                GlStateManager.translate(2.0f, 20.0f, 0.0f);
                GlStateManager.scale(f2, f2, 1.0f);
                for (int i2 = 0; i2 + this.scrollPos < this.drawnChatLines.size() && i2 < i; ++i2) {
                    final ChatLine chatline = this.drawnChatLines.get(i2 + this.scrollPos);
                    if (chatline != null) {
                        final int j1;
                        if ((j1 = updateCounter - chatline.getUpdatedCounter()) < 200 || flag) {
                            double d0 = j1 / 200.0;
                            d0 = 1.0 - d0;
                            d0 *= 10.0;
                            d0 = MathHelper.clamp_double(d0, 0.0, 1.0);
                            d0 *= d0;
                            int l2 = (int)(255.0 * d0);
                            if (flag) {
                                l2 = 255;
                            }
                            l2 *= (int)f;
                            if (l2 > 3) {
                                final int i3 = 0;
                                final int j2 = -i2 * 9;
                                if (UISettings.chatBackground) {
                                    GuiNewChat.drawRect(i3, j2 - 9, i3 + l + 4, j2, l2 / 2 << 24);
                                }
                                final String s = chatline.getChatComponent().getFormattedText();
                                GlStateManager.enableBlend();
                                if (UISettings.chatTextShadow) {
                                    this.mc.fontRendererObj.drawStringWithShadow(s, (float)i3, (float)(j2 - 8), 16777215 + (l2 << 24));
                                }
                                else {
                                    this.mc.fontRendererObj.drawString(s, i3, j2 - 8, 16777215 + (l2 << 24));
                                }
                                GlStateManager.disableAlpha();
                                GlStateManager.disableBlend();
                            }
                        }
                    }
                }
                if (flag) {
                    GlStateManager.translate(-3.0f, 0.0f, 0.0f);
                }
                GlStateManager.popMatrix();
            }
        }
        ci.cancel();
    }
}
