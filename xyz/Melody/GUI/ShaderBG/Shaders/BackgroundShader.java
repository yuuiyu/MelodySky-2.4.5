//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.ShaderBG.Shaders;

import xyz.Melody.GUI.ShaderBG.*;
import org.lwjgl.opengl.*;

public final class BackgroundShader extends Shader
{
    public static int deltaTime;
    public static BackgroundShader BACKGROUND_SHADER;
    private float time;
    
    public BackgroundShader() {
        super("background.frag");
    }
    
    public void setupUniforms() {
        this.setupUniform("iResolution");
        this.setupUniform("iTime");
    }
    
    public void updateUniforms() {
        final int resolutionID = this.getUniform("iResolution");
        if (resolutionID > -1) {
            GL20.glUniform2f(resolutionID, (float)Display.getWidth(), (float)Display.getHeight());
        }
        final int timeID = this.getUniform("iTime");
        if (timeID > -1) {
            GL20.glUniform1f(timeID, this.time);
        }
        this.time += 0.01f;
    }
    
    static {
        BackgroundShader.BACKGROUND_SHADER = new BackgroundShader();
    }
}
