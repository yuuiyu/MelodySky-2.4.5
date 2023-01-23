//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Menu;

import xyz.Melody.Utils.animate.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

class l extends Animation
{
    final /* synthetic */ MainMenu this$0;
    
    l(final MainMenu this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int getMaxTime() {
        return 0;
    }
    
    @Override
    public void render() {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0f, 1.0f, 1.0f, this.time / 10.0f);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(0.0, this.this$0.height + 1.0, 0.0).endVertex();
        worldrenderer.pos((double)this.this$0.width, this.this$0.height + 1.0, 0.0).endVertex();
        worldrenderer.pos((double)this.this$0.width, 0.0, 0.0).endVertex();
        worldrenderer.pos(0.0, 0.0, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
