//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix;

import net.minecraft.client.resources.model.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.block.model.*;

final class l implements IBakedModel
{
    public List<BakedQuad> getFaceQuads(final EnumFacing facing) {
        return Collections.emptyList();
    }
    
    public List<BakedQuad> getGeneralQuads() {
        return Collections.emptyList();
    }
    
    public boolean isAmbientOcclusion() {
        return false;
    }
    
    public boolean isGui3d() {
        return false;
    }
    
    public boolean isBuiltInRenderer() {
        return false;
    }
    
    public TextureAtlasSprite getParticleTexture() {
        return Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(TextureMap.LOCATION_MISSING_TEXTURE.toString());
    }
    
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }
}
