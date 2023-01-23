//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.render;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.shader.*;

@Mixin({ EntityRenderer.class })
public interface ERA
{
    @Accessor("torchFlickerX")
    float getTorchFlickerX();
    
    @Accessor("lightmapColors")
    int[] getLightmapColors();
    
    @Accessor("lightmapTexture")
    DynamicTexture getLightmapTexture();
    
    @Accessor("theShaderGroup")
    void setShaderGroup(final ShaderGroup p0);
}
