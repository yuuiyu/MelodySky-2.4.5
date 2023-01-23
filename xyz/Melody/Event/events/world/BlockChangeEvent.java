//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.world;

import xyz.Melody.Event.*;
import net.minecraft.util.*;
import net.minecraft.block.state.*;

public class BlockChangeEvent extends Event
{
    private BlockPos position;
    private IBlockState oldBlock;
    private IBlockState newBlock;
    
    public BlockChangeEvent(final BlockPos position, final IBlockState oldBlock, final IBlockState newBlock) {
        this.position = position;
        this.oldBlock = oldBlock;
        this.newBlock = newBlock;
    }
    
    public BlockPos getPosition() {
        return this.position;
    }
    
    public IBlockState getOldBlock() {
        return this.oldBlock;
    }
    
    public IBlockState getNewBlock() {
        return this.newBlock;
    }
}
