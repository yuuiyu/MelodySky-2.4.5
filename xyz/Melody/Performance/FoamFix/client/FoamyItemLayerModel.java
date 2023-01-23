//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.client;

import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.texture.*;
import com.google.common.base.*;
import net.minecraft.util.*;
import net.minecraftforge.client.model.*;
import com.google.common.collect.*;
import net.minecraft.client.renderer.block.model.*;
import java.lang.invoke.*;
import net.minecraftforge.fml.relauncher.*;
import java.lang.ref.*;
import org.apache.commons.lang3.tuple.*;
import javax.vecmath.*;
import java.util.*;

public class FoamyItemLayerModel implements IRetexturableModel<ItemLayerModel>
{
    private static final ResourceLocation MISSINGNO;
    private static final MethodHandle BUILD_QUAD;
    private static final MethodHandle TEXTURES_GET;
    private final ItemLayerModel parent;
    
    public FoamyItemLayerModel(final ItemLayerModel parent) {
        this.parent = parent;
    }
    
    public IModel retexture(final ImmutableMap<String, String> textures) {
        return (IModel)new FoamyItemLayerModel((ItemLayerModel)this.parent.retexture((ImmutableMap)textures));
    }
    
    public Collection<ResourceLocation> getDependencies() {
        return (Collection<ResourceLocation>)this.parent.getDependencies();
    }
    
    public Collection<ResourceLocation> getTextures() {
        return (Collection<ResourceLocation>)this.parent.getTextures();
    }
    
    public IFlexibleBakedModel bake(final IModelState state, final VertexFormat format, final Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return bake(this.parent, state, format, bakedTextureGetter);
    }
    
    public static IFlexibleBakedModel bake(final ItemLayerModel parent, final IModelState state, final VertexFormat format, final Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        final ImmutableList.Builder<BakedQuad> builder = (ImmutableList.Builder<BakedQuad>)ImmutableList.builder();
        final Optional<TRSRTransformation> transform = (Optional<TRSRTransformation>)state.apply(Optional.absent());
        List<ResourceLocation> textures;
        try {
            textures = (List<ResourceLocation>)FoamyItemLayerModel.TEXTURES_GET.invoke(parent);
        }
        catch (Throwable t2) {
            return ItemLayerModel.instance.bake(state, format, (Function)bakedTextureGetter);
        }
        final ImmutableList.Builder<TextureAtlasSprite> textureAtlas = (ImmutableList.Builder<TextureAtlasSprite>)new ImmutableList.Builder();
        if (FoamyItemLayerModel.BUILD_QUAD != null) {
            for (int i = 0; i < textures.size(); ++i) {
                final TextureAtlasSprite sprite = (TextureAtlasSprite)bakedTextureGetter.apply((Object)textures.get(i));
                textureAtlas.add((Object)sprite);
                try {
                    builder.add((Object)FoamyItemLayerModel.BUILD_QUAD.invokeExact(format, (Optional)transform, EnumFacing.SOUTH, i, 0.0f, 0.0f, 0.53125f, sprite.getMinU(), sprite.getMaxV(), 1.0f, 0.0f, 0.53125f, sprite.getMaxU(), sprite.getMaxV(), 1.0f, 1.0f, 0.53125f, sprite.getMaxU(), sprite.getMinV(), 0.0f, 1.0f, 0.53125f, sprite.getMinU(), sprite.getMinV()));
                }
                catch (Throwable t) {
                    throw new RuntimeException(t);
                }
            }
        }
        else {
            for (int i = 0; i < textures.size(); ++i) {
                final TextureAtlasSprite sprite = (TextureAtlasSprite)bakedTextureGetter.apply((Object)textures.get(i));
                for (final BakedQuad quad : ItemLayerModel.instance.getQuadsForSprite(i, sprite, format, (Optional)transform)) {
                    if (quad.getFace() == EnumFacing.SOUTH) {
                        builder.add((Object)quad);
                    }
                }
            }
        }
        final TextureAtlasSprite particle = (TextureAtlasSprite)bakedTextureGetter.apply((Object)(textures.isEmpty() ? FoamyItemLayerModel.MISSINGNO : textures.get(0)));
        final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> map = (ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation>)IPerspectiveAwareModel.MapWrapper.getTransforms(state);
        return new DynamicItemModel((ImmutableList<BakedQuad>)builder.build(), particle, map, (List<TextureAtlasSprite>)textureAtlas.build(), format, transform).otherModel;
    }
    
    public IModelState getDefaultState() {
        return (IModelState)TRSRTransformation.identity();
    }
    
    static {
        MISSINGNO = new ResourceLocation("missingno");
        MethodHandle handle = null;
        try {
            handle = MethodHandles.lookup().unreflect(ReflectionHelper.findMethod((Class)ItemLayerModel.class, (Object)null, new String[] { "buildQuad" }, new Class[] { VertexFormat.class, Optional.class, EnumFacing.class, Integer.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE }));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        BUILD_QUAD = handle;
        handle = null;
        try {
            handle = MethodHandles.lookup().unreflectGetter(ReflectionHelper.findField((Class)ItemLayerModel.class, new String[] { "textures" }));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        TEXTURES_GET = handle;
    }
    
    public static class Dynamic3DItemModel implements IPerspectiveAwareModel, IFlexibleBakedModel
    {
        private final DynamicItemModel parent;
        private SoftReference<List<BakedQuad>> quadsSoft;
        
        public Dynamic3DItemModel(final DynamicItemModel parent) {
            this.quadsSoft = null;
            this.parent = parent;
        }
        
        public Pair<? extends IFlexibleBakedModel, Matrix4f> handlePerspective(final ItemCameraTransforms.TransformType type) {
            final Pair<? extends IFlexibleBakedModel, Matrix4f> pair = (Pair<? extends IFlexibleBakedModel, Matrix4f>)IPerspectiveAwareModel.MapWrapper.handlePerspective((IFlexibleBakedModel)this, this.parent.transforms, type);
            if (type == ItemCameraTransforms.TransformType.GUI && pair.getRight() == null) {
                return (Pair<? extends IFlexibleBakedModel, Matrix4f>)Pair.of((Object)this.parent, (Object)null);
            }
            return pair;
        }
        
        public List<BakedQuad> getFaceQuads(final EnumFacing facing) {
            return (List<BakedQuad>)Collections.EMPTY_LIST;
        }
        
        public List<BakedQuad> getGeneralQuads() {
            if (this.quadsSoft == null || this.quadsSoft.get() == null) {
                final ImmutableList.Builder<BakedQuad> builder = (ImmutableList.Builder<BakedQuad>)new ImmutableList.Builder();
                for (int i = 0; i < this.parent.textures.size(); ++i) {
                    final TextureAtlasSprite sprite = this.parent.textures.get(i);
                    builder.addAll((Iterable)ItemLayerModel.instance.getQuadsForSprite(i, sprite, this.parent.format, this.parent.transform));
                }
                this.quadsSoft = new SoftReference<List<BakedQuad>>((List<BakedQuad>)builder.build());
            }
            return this.quadsSoft.get();
        }
        
        public boolean isAmbientOcclusion() {
            return true;
        }
        
        public boolean isGui3d() {
            return false;
        }
        
        public boolean isBuiltInRenderer() {
            return false;
        }
        
        public TextureAtlasSprite getParticleTexture() {
            return this.parent.particle;
        }
        
        public ItemCameraTransforms getItemCameraTransforms() {
            return ItemCameraTransforms.DEFAULT;
        }
        
        public VertexFormat getFormat() {
            return this.parent.getFormat();
        }
    }
    
    public static class DynamicItemModel implements IPerspectiveAwareModel, IFlexibleBakedModel
    {
        private final List<TextureAtlasSprite> textures;
        private final TextureAtlasSprite particle;
        private final ImmutableList<BakedQuad> fastQuads;
        private final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms;
        private final VertexFormat format;
        private final Optional<TRSRTransformation> transform;
        private final IFlexibleBakedModel otherModel;
        
        public DynamicItemModel(final ImmutableList<BakedQuad> quads, final TextureAtlasSprite particle, final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms, final List<TextureAtlasSprite> textures, final VertexFormat format, final Optional<TRSRTransformation> transform) {
            this.fastQuads = quads;
            this.particle = particle;
            this.transforms = transforms;
            this.textures = textures;
            this.format = format;
            this.transform = transform;
            this.otherModel = (IFlexibleBakedModel)new Dynamic3DItemModel(this);
        }
        
        public List<BakedQuad> getFaceQuads(final EnumFacing facing) {
            return Collections.emptyList();
        }
        
        public List<BakedQuad> getGeneralQuads() {
            return (List<BakedQuad>)this.fastQuads;
        }
        
        public boolean isAmbientOcclusion() {
            return true;
        }
        
        public boolean isGui3d() {
            return false;
        }
        
        public boolean isBuiltInRenderer() {
            return false;
        }
        
        public TextureAtlasSprite getParticleTexture() {
            return this.particle;
        }
        
        public ItemCameraTransforms getItemCameraTransforms() {
            return ItemCameraTransforms.DEFAULT;
        }
        
        public Pair<? extends IFlexibleBakedModel, Matrix4f> handlePerspective(final ItemCameraTransforms.TransformType type) {
            final Pair<? extends IFlexibleBakedModel, Matrix4f> pair = (Pair<? extends IFlexibleBakedModel, Matrix4f>)IPerspectiveAwareModel.MapWrapper.handlePerspective((IFlexibleBakedModel)this, (ImmutableMap)this.transforms, type);
            if (type != ItemCameraTransforms.TransformType.GUI) {
                return (Pair<? extends IFlexibleBakedModel, Matrix4f>)Pair.of((Object)this.otherModel, pair.getRight());
            }
            return pair;
        }
        
        public VertexFormat getFormat() {
            return this.format;
        }
    }
}
