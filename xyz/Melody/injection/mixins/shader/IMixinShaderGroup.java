//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.shader;

import org.spongepowered.asm.mixin.*;
import java.util.*;
import net.minecraft.client.shader.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ ShaderGroup.class })
public interface IMixinShaderGroup
{
    @Accessor("listShaders")
    List<Shader> getListShaders();
}
