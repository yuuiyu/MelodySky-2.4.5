//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.client;

import net.minecraftforge.client.event.*;
import xyz.Melody.Performance.FoamFix.shared.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.*;
import xyz.Melody.Performance.FoamFix.*;
import xyz.Melody.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.client.resources.model.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FoamFixModelDeduplicate
{
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onModelBake(final ModelBakeEvent event) {
        if (FoamFixShared.config.clDeduplicate) {
            final ProgressManager.ProgressBar bakeBar = ProgressManager.push("FoamFix: deduplicating", ((RegistrySimple)event.modelRegistry).getKeys().size());
            if (ProxyClient.deduplicator == null) {
                ProxyClient.deduplicator = new Deduplicator();
            }
            Client.instance.logger.info("Deduplicating models...");
            ProxyClient.deduplicator.maxRecursion = FoamFixShared.config.clDeduplicateRecursionLevel;
            ProxyClient.deduplicator.addObjects((Collection)Block.blockRegistry.getKeys());
            ProxyClient.deduplicator.addObjects((Collection)Item.itemRegistry.getKeys());
            for (final ModelResourceLocation loc : ((RegistrySimple)event.modelRegistry).getKeys()) {
                final IBakedModel model = (IBakedModel)event.modelRegistry.getObject((Object)loc);
                final String modelName = loc.toString();
                bakeBar.step(String.format("[%s]", modelName));
                try {
                    ProxyClient.deduplicator.addObject((Object)loc);
                    ProxyClient.deduplicator.deduplicateObject((Object)model, 0);
                }
                catch (Exception ex) {}
            }
            ProgressManager.pop(bakeBar);
            Client.instance.logger.info("Deduplicated " + ProxyClient.deduplicator.successfuls + " objects.");
        }
        ProxyClient.deduplicator = null;
    }
}
