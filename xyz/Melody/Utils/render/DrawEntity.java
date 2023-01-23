//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.render;

import net.minecraft.client.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import xyz.Melody.Utils.fakemc.*;
import net.minecraft.client.network.*;
import net.minecraft.stats.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.injection.mixins.render.*;
import net.minecraft.util.*;

public class DrawEntity
{
    private Minecraft mc;
    public WorldClient world;
    public EntityPlayerSP player;
    
    public DrawEntity() {
        this.mc = Minecraft.getMinecraft();
    }
    
    public void draw(final int x, final int y, final int scale, final float mouseX, final float mouseY) {
        GlStateManager.pushMatrix();
        try {
            if (this.player == null || this.player.worldObj == null) {
                this.init();
            }
            if (this.mc.getRenderManager().worldObj == null || this.mc.getRenderManager().livingPlayer == null) {
                this.mc.getRenderManager().cacheActiveRenderInfo((World)this.world, this.mc.fontRendererObj, (Entity)this.player, (Entity)this.player, this.mc.gameSettings, 0.0f);
            }
            if (this.world != null && this.player != null) {
                this.mc.thePlayer = this.player;
                this.mc.theWorld = this.world;
                this.drawEntityOnScreen(x, y, (float)scale, mouseX, mouseY, (EntityLivingBase)this.player);
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
            this.player = null;
            this.world = null;
        }
        GlStateManager.popMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        this.mc.thePlayer = null;
        this.mc.theWorld = null;
    }
    
    private void init() {
        try {
            final boolean createNewWorld = this.world == null;
            final WorldSettings worldSettings = new WorldSettings(0L, WorldSettings.GameType.NOT_SET, true, false, WorldType.DEFAULT);
            final FakeNetHandlerPlayClient netHandler = new FakeNetHandlerPlayClient(this.mc);
            if (createNewWorld) {
                this.world = (WorldClient)new FakeWorld(worldSettings, netHandler);
            }
            if (createNewWorld || this.player == null) {
                this.player = new EntityPlayerSP(this.mc, (World)this.world, (NetHandlerPlayClient)netHandler, (StatFileWriter)null);
                int ModelParts = 0;
                for (final EnumPlayerModelParts enumplayermodelparts : this.mc.gameSettings.getModelParts()) {
                    ModelParts |= enumplayermodelparts.getPartMask();
                }
                this.player.getDataWatcher().updateObject(10, (Object)(byte)ModelParts);
                this.player.dimension = 0;
                this.player.movementInput = (MovementInput)new MovementInputFromOptions(this.mc.gameSettings);
            }
            this.updateLightmap(this.mc, (World)this.world);
            this.mc.getRenderManager().cacheActiveRenderInfo((World)this.world, this.mc.fontRendererObj, (Entity)this.player, (Entity)this.player, this.mc.gameSettings, 0.0f);
        }
        catch (Throwable e) {
            e.printStackTrace();
            this.player = null;
            this.world = null;
        }
    }
    
    private void drawEntityOnScreen(final int posX, final int posY, final float scale, final float mouseX, final float mouseY, final EntityLivingBase ent) {
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableColorMaterial();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GuiInventory.drawEntityOnScreen(posX, posY, (int)scale, mouseX, mouseY, ent);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.translate(0.0f, 0.0f, 20.0f);
    }
    
    private void updateLightmap(final Minecraft mc, final World world) {
        final float f = world.getSunBrightness(1.0f);
        final float f2 = f * 0.95f + 0.05f;
        for (int i = 0; i < 256; ++i) {
            final float f3 = world.provider.getLightBrightnessTable()[i / 16] * f2;
            final float f4 = world.provider.getLightBrightnessTable()[i % 16] * (((ERA)mc.entityRenderer).getTorchFlickerX() * 0.1f + 1.5f);
            final float f5 = f3 * (f * 0.65f + 0.35f);
            final float f6 = f3 * (f * 0.65f + 0.35f);
            final float f7 = f4 * ((f4 * 0.6f + 0.4f) * 0.6f + 0.4f);
            final float f8 = f4 * (f4 * f4 * 0.6f + 0.4f);
            float f9 = f5 + f4;
            float f10 = f6 + f7;
            float f11 = f3 + f8;
            f9 = f9 * 0.96f + 0.03f;
            f10 = f10 * 0.96f + 0.03f;
            f11 = f11 * 0.96f + 0.03f;
            if (f9 > 1.0f) {
                f9 = 1.0f;
            }
            if (f10 > 1.0f) {
                f10 = 1.0f;
            }
            if (f11 > 1.0f) {
                f11 = 1.0f;
            }
            final float f12 = mc.gameSettings.gammaSetting;
            float f13 = 1.0f - f9;
            float f14 = 1.0f - f10;
            float f15 = 1.0f - f11;
            f13 = 1.0f - f13 * f13 * f13 * f13;
            f14 = 1.0f - f14 * f14 * f14 * f14;
            f15 = 1.0f - f15 * f15 * f15 * f15;
            f9 = f9 * (1.0f - f12) + f13 * f12;
            f10 = f10 * (1.0f - f12) + f14 * f12;
            f11 = f11 * (1.0f - f12) + f15 * f12;
            f9 = f9 * 0.96f + 0.03f;
            f10 = f10 * 0.96f + 0.03f;
            f11 = f11 * 0.96f + 0.03f;
            if (f9 > 1.0f) {
                f9 = 1.0f;
            }
            if (f10 > 1.0f) {
                f10 = 1.0f;
            }
            if (f11 > 1.0f) {
                f11 = 1.0f;
            }
            if (f9 < 0.0f) {
                f9 = 0.0f;
            }
            if (f10 < 0.0f) {
                f10 = 0.0f;
            }
            if (f11 < 0.0f) {
                f11 = 0.0f;
            }
            final int k = (int)(f9 * 255.0f);
            final int l = (int)(f10 * 255.0f);
            final int i2 = (int)(f11 * 255.0f);
            ((ERA)mc.entityRenderer).getLightmapColors()[i] = (0xFF000000 | k << 16 | l << 8 | i2);
        }
        ((ERA)mc.entityRenderer).getLightmapTexture().updateDynamicTexture();
    }
}
