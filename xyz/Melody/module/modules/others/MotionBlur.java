//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import net.minecraft.util.*;
import net.minecraft.client.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.*;
import com.google.gson.*;
import java.io.*;
import xyz.Melody.injection.mixins.shader.*;
import net.minecraft.client.shader.*;

public class MotionBlur extends Module
{
    private ResourceLocation location;
    private Minecraft mc;
    private ShaderGroup shader;
    private float shaderBlur;
    public Numbers<Double> size;
    
    public MotionBlur() {
        super("MotionBlur", ModuleType.Others);
        this.location = new ResourceLocation("minecraft:shaders/post/motion_blur.json");
        this.mc = Minecraft.getMinecraft();
        this.size = (Numbers<Double>)new Numbers("Blur Amount", (Number)5.0, (Number)1.0, (Number)10.0, (Number)0.5);
        this.addValues(new Value[] { (Value)this.size });
    }
    
    public ShaderGroup getShader() {
        if (this.shader == null) {
            this.shaderBlur = Float.NaN;
            try {
                (this.shader = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), this.location)).createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            }
            catch (JsonSyntaxException | IOException ex2) {
                final Exception ex;
                final Exception error = ex;
                Client.instance.logger.error("Could not load motion blur shader", (Throwable)error);
                return null;
            }
        }
        final float size = ((Double)this.size.getValue()).floatValue() / 12.0f;
        if (this.shaderBlur != size) {
            final ShaderUniform blendFactorUniform;
            final float n;
            ((IMixinShaderGroup)this.shader).getListShaders().forEach(shader -> {
                blendFactorUniform = shader.getShaderManager().getShaderUniform("BlurFactor");
                if (blendFactorUniform != null) {
                    blendFactorUniform.set(n);
                }
                return;
            });
            this.shaderBlur = size;
        }
        return this.shader;
    }
}
