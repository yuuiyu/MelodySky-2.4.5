//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.FML;

import net.minecraftforge.fml.common.network.handshake.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;
import net.minecraftforge.fml.common.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import xyz.Melody.*;
import java.util.stream.*;
import java.util.function.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { FMLHandshakeMessage.ModList.class }, remap = false)
public abstract class MixinFMLHandshakeMessage
{
    @Shadow
    private Map<String, String> modTags;
    
    @Inject(method = "<init>(Ljava/util/List;)V", at = { @At("RETURN") })
    private void removeMod(final List<ModContainer> modList, final CallbackInfo ci) {
        if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
            this.modTags.remove("melodysky");
            this.modTags = this.modTags.entrySet().stream().filter(e -> Client.isWhitelisted((String)e.getKey())).collect(Collectors.toMap((Function<? super Object, ? extends String>)Map.Entry::getKey, (Function<? super Object, ? extends String>)Map.Entry::getValue));
        }
    }
}
