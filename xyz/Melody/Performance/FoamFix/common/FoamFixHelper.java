//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.common;

import xyz.Melody.Performance.FoamFix.api.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraftforge.common.property.*;

public class FoamFixHelper implements IFoamFixHelper
{
    public BlockState createBlockState(final Block block, final IProperty... properties) {
        return new FoamyBlockStateContainer(block, (IProperty<?>[])properties);
    }
    
    public BlockState createExtendedBlockState(final Block block, final IProperty[] properties, final IUnlistedProperty[] unlistedProperties) {
        return (BlockState)new FoamyExtendedBlockStateContainer(block, (IProperty<?>[])properties, (IUnlistedProperty<?>[])unlistedProperties);
    }
}
