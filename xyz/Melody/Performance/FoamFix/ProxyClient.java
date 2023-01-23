//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix;

import net.minecraft.client.resources.model.*;
import xyz.Melody.Performance.FoamFix.shared.*;
import net.minecraftforge.common.*;
import xyz.Melody.Performance.FoamFix.client.*;

public class ProxyClient extends ProxyCommon
{
    public static Deduplicator deduplicator;
    public static final IBakedModel DUMMY_MODEL;
    
    @Override
    public void preInit() {
        super.preInit();
        if (!FoamFixShared.config.clDeduplicate) {
            ProxyClient.deduplicator = null;
        }
    }
    
    @Override
    public void init() {
        super.init();
        MinecraftForge.EVENT_BUS.register((Object)new FoamFixModelDeduplicate());
        if (FoamFixShared.config.clCleanRedundantModelRegistry) {
            MinecraftForge.EVENT_BUS.register((Object)new FoamFixModelRegistryDuplicateWipe());
        }
    }
    
    @Override
    public void postInit() {
        super.postInit();
        if (ProxyClient.deduplicator != null) {
            ProxyClient.deduplicator.successfuls = 0;
        }
    }
    
    static {
        ProxyClient.deduplicator = new Deduplicator();
        DUMMY_MODEL = (IBakedModel)new l();
    }
}
