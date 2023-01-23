//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.client;

import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.*;
import xyz.Melody.*;
import xyz.Melody.Performance.FoamFix.*;
import net.minecraftforge.client.*;
import net.minecraft.client.renderer.*;
import java.lang.reflect.*;
import net.minecraft.client.resources.model.*;
import java.util.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import gnu.trove.map.hash.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FoamFixModelRegistryDuplicateWipe
{
    @SubscribeEvent
    public void onTextureStitchPost(final TextureStitchEvent.Post event) {
        final ItemModelMesher imm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        final BlockModelShapes bms = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes();
        final ModelManager mgr = bms.getModelManager();
        Field f = ReflectionHelper.findField((Class)ModelManager.class, new String[] { "modelRegistry", "modelRegistry" });
        try {
            final RegistrySimple<ModelResourceLocation, IBakedModel> registry = (RegistrySimple<ModelResourceLocation, IBakedModel>)f.get(mgr);
            Client.instance.logger.info("Clearing unnecessary model registry of size " + registry.getKeys().size() + ".");
            for (final ModelResourceLocation l : registry.getKeys()) {
                registry.putObject((Object)l, (Object)ProxyClient.DUMMY_MODEL);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        f = ReflectionHelper.findField((Class)BlockModelShapes.class, new String[] { "bakedModelStore", "bakedModelStore" });
        try {
            final Map<IBlockState, IBakedModel> modelStore = (Map<IBlockState, IBakedModel>)f.get(bms);
            Client.instance.logger.info("Clearing unnecessary model store of size " + modelStore.size() + ".");
            modelStore.clear();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (imm instanceof ItemModelMesherForge) {
            f = ReflectionHelper.findField((Class)ItemModelMesherForge.class, new String[] { "models" });
            try {
                final IdentityHashMap<Item, TIntObjectHashMap<IBakedModel>> modelStore2 = (IdentityHashMap<Item, TIntObjectHashMap<IBakedModel>>)f.get(imm);
                Client.instance.logger.info("Clearing unnecessary item shapes cache of size " + modelStore2.size() + ".");
                modelStore2.clear();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
