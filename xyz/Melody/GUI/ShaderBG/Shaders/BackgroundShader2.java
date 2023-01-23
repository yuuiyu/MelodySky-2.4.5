//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.ShaderBG.Shaders;

import xyz.Melody.GUI.ShaderBG.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;

public final class BackgroundShader2 extends Shader
{
    public static final BackgroundShader2 BACKGROUND_SHADER;
    private float time;
    
    public BackgroundShader2() {
        super("background2.frag");
    }
    
    public void setupUniforms() {
        this.setupUniform("iResolution");
        this.setupUniform("iTime");
    }
    
    public void updateUniforms() {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final int resolutionID = this.getUniform("iResolution");
        if (resolutionID > -1) {
            GL20.glUniform2f(resolutionID, scaledResolution.getScaledWidth() * 2.0f, scaledResolution.getScaledHeight() * 2.0f);
        }
        final int timeID = this.getUniform("iTime");
        if (timeID > -1) {
            GL20.glUniform1f(timeID, this.time);
        }
        this.time += 0.1f;
    }
    
    static {
        BACKGROUND_SHADER = new BackgroundShader2();
    }
}
