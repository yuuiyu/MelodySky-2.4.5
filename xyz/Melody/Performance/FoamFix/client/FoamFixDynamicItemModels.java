//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.client;

import xyz.Melody.Performance.FoamFix.util.*;
import java.lang.invoke.*;
import java.util.*;
import net.minecraft.client.renderer.vertex.*;
import com.google.common.base.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraftforge.client.model.*;
import net.minecraft.client.resources.*;

public class FoamFixDynamicItemModels
{
    public static void register() {
        final MethodHandle LOADERS = MethodHandleHelper.findFieldGetter(ModelLoaderRegistry.class, "loaders");
        try {
            final Set<ICustomModelLoader> loaders = (Set<ICustomModelLoader>)LOADERS.invoke();
            loaders.remove(ItemLayerModel.Loader.instance);
            loaders.add((ICustomModelLoader)Loader.INSTANCE);
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    
    public IFlexibleBakedModel bake(final IModelState state, final VertexFormat format, final Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return FoamyItemLayerModel.bake((ItemLayerModel)this, state, format, bakedTextureGetter);
    }
    
    public enum Loader implements ICustomModelLoader
    {
        INSTANCE;
        
        private static final IModel model;
        
        public void onResourceManagerReload(final IResourceManager resourceManager) {
            ItemLayerModel.Loader.instance.onResourceManagerReload(resourceManager);
        }
        
        public boolean accepts(final ResourceLocation modelLocation) {
            return ItemLayerModel.Loader.instance.accepts(modelLocation);
        }
        
        public IModel loadModel(final ResourceLocation modelLocation) {
            return Loader.model;
        }
        
        static {
            model = (IModel)new FoamyItemLayerModel(ItemLayerModel.instance);
        }
    }
}
