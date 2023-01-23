//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.client;

import net.minecraft.world.chunk.*;
import net.minecraft.util.*;
import net.minecraft.block.state.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ Chunk.class })
public abstract class MixinChunk
{
    @Shadow
    public abstract IBlockState getBlockState(final BlockPos p0);
    
    @Inject(method = "setBlockState", at = { @At("HEAD") })
    private void onBlockChange(final BlockPos position, final IBlockState newBlock, final CallbackInfoReturnable<IBlockState> callbackInfoReturnable) {
        final IBlockState oldBlock = this.getBlockState(position);
        if (oldBlock != newBlock && Minecraft.getMinecraft().theWorld != null) {
            EventBus.getInstance().call((Event)new BlockChangeEvent(position, oldBlock, newBlock));
        }
    }
}
