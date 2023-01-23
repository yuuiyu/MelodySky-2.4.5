//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.shader;

import by.radioegor146.nativeobfuscator.*;
import net.minecraft.client.shader.*;
import net.minecraft.client.*;
import org.lwjgl.*;
import xyz.Melody.Utils.math.*;
import org.lwjgl.opengl.*;
import java.nio.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.Utils.render.gl.*;

@Native
public class GaussianBlur
{
    private ShaderUtils blurShader;
    private Framebuffer framebuffer;
    private Minecraft mc;
    
    public GaussianBlur() {
        this.blurShader = new ShaderUtils("Melody/GLSL/Shaders/gaussian.frag");
        this.framebuffer = new Framebuffer(1, 1, false);
        this.mc = Minecraft.getMinecraft();
    }
    
    private void setupUniforms(final float dir1, final float dir2, final float radius) {
        this.blurShader.setUniformi("textureIn", 0);
        this.blurShader.setUniformf("texelSize", 1.0f / this.mc.displayWidth, 1.0f / this.mc.displayHeight);
        this.blurShader.setUniformf("direction", dir1, dir2);
        this.blurShader.setUniformf("radius", radius);
        final FloatBuffer weightBuffer = BufferUtils.createFloatBuffer(256);
        for (int i = 0; i <= radius; ++i) {
            weightBuffer.put(MathUtil.calculateGaussianValue((float)i, radius / 2.0f));
        }
        weightBuffer.rewind();
        GL20.glUniform1(this.blurShader.getUniform("weights"), weightBuffer);
    }
    
    public void renderBlur(final float radius) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        (this.framebuffer = GLUtils.createFrameBuffer(this.framebuffer)).framebufferClear();
        this.framebuffer.bindFramebuffer(true);
        this.blurShader.init();
        this.setupUniforms(1.0f, 0.0f, radius);
        GLUtils.bindTexture(this.mc.getFramebuffer().framebufferTexture);
        ShaderUtils.drawQuads();
        this.framebuffer.unbindFramebuffer();
        this.blurShader.unload();
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.blurShader.init();
        this.setupUniforms(0.0f, 1.0f, radius);
        GLUtils.bindTexture(this.framebuffer.framebufferTexture);
        ShaderUtils.drawQuads();
        this.blurShader.unload();
        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
        GlStateManager.popMatrix();
    }
    
    public void renderBlur(final float radius, final int fhd, final int fvd, final int shd, final int svd) {
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        (this.framebuffer = GLUtils.createFrameBuffer(this.framebuffer)).framebufferClear();
        this.framebuffer.bindFramebuffer(true);
        this.blurShader.init();
        this.setupUniforms((float)fhd, (float)fvd, radius);
        GLUtils.bindTexture(this.mc.getFramebuffer().framebufferTexture);
        ShaderUtils.drawQuads();
        this.framebuffer.unbindFramebuffer();
        this.blurShader.unload();
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.blurShader.init();
        this.setupUniforms((float)shd, (float)svd, radius);
        GLUtils.bindTexture(this.framebuffer.framebufferTexture);
        ShaderUtils.drawQuads();
        this.blurShader.unload();
        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
}
