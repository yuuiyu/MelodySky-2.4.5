//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.*;
import xyz.Melody.Event.*;
import net.minecraft.init.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.render.*;
import java.util.*;
import xyz.Melody.Utils.math.*;
import java.awt.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.tileentity.*;
import com.mojang.authlib.properties.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.event.world.*;

public class StonkLessStonk extends Module
{
    private Option<Boolean> auto;
    private Numbers<Double> range;
    private Numbers<Double> scanRange;
    private Option<Boolean> sneak;
    private Option<Boolean> remove;
    private HashMap<BlockPos, Block> blockList;
    private BlockPos selectedBlock;
    private BlockPos currentBlock;
    private BlockPos lastCheckedPosition;
    private HashSet<BlockPos> usedBlocks;
    private String witherEssenceSkin;
    private int essenceSkinHash;
    private TimerUtil timer;
    private TimerUtil autoTimer;
    int ticks;
    
    public StonkLessStonk() {
        super("StonkLessStonk", new String[] { "sls" }, ModuleType.Dungeons);
        this.auto = (Option<Boolean>)new Option("Auto", (Object)false);
        this.range = (Numbers<Double>)new Numbers("Range", (Number)5.0, (Number)0.0, (Number)6.0, (Number)0.5);
        this.scanRange = (Numbers<Double>)new Numbers("ScanRange", (Number)6.0, (Number)0.0, (Number)15.0, (Number)1.0);
        this.sneak = (Option<Boolean>)new Option("Sneak", (Object)false);
        this.remove = (Option<Boolean>)new Option("Remove", (Object)true);
        this.blockList = new HashMap<BlockPos, Block>();
        this.selectedBlock = null;
        this.currentBlock = null;
        this.lastCheckedPosition = null;
        this.usedBlocks = new HashSet<BlockPos>();
        this.witherEssenceSkin = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0=";
        this.essenceSkinHash = this.witherEssenceSkin.hashCode();
        this.timer = new TimerUtil();
        this.autoTimer = new TimerUtil();
        this.ticks = 0;
        this.addValues(new Value[] { (Value)this.auto, (Value)this.range, (Value)this.scanRange, (Value)this.sneak, (Value)this.remove });
        this.setModInfo("Click Secrets Thru walls.");
    }
    
    private boolean shoulddick() {
        if (this.mc.thePlayer != null && Client.inDungeons && !Client.instance.dungeonUtils.inBoss) {
            if ((boolean)this.sneak.getValue() && this.mc.thePlayer.isSneaking()) {
                return true;
            }
            if (!(boolean)this.sneak.getValue()) {
                return true;
            }
        }
        return false;
    }
    
    @EventHandler
    private void tickDungeon(final EventTick event) {
        if (this.mc.thePlayer == null) {
            return;
        }
        if (!Client.inDungeons || Client.instance.dungeonUtils.inBoss) {
            return;
        }
        final BlockPos playerPosition = this.mc.thePlayer.getPosition();
        if (this.lastCheckedPosition == null || !this.lastCheckedPosition.equals((Object)playerPosition)) {
            this.lastCheckedPosition = playerPosition;
            this.loadSecrets();
        }
    }
    
    private void loadSecrets() {
        final int r = ((Double)this.scanRange.getValue()).intValue();
        BlockPos playerPos = this.mc.thePlayer.getPosition();
        playerPos = playerPos.add(0, 1, 0);
        final Vec3i vec3i = new Vec3i(r, r, r);
        this.blockList.clear();
        if (playerPos != null) {
            for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                final Block block = this.mc.theWorld.getBlockState(blockPos).getBlock();
                if (this.shouldEspBlock(block, blockPos)) {
                    this.blockList.put(blockPos, block);
                }
            }
        }
    }
    
    private BlockPos getClosestSecret() {
        final int r = ((Double)this.scanRange.getValue()).intValue();
        BlockPos playerPos = this.mc.thePlayer.getPosition();
        playerPos = playerPos.add(0, 1, 0);
        final Vec3i vec3i = new Vec3i(r, r, r);
        final ArrayList<BlockPos> poses = new ArrayList<BlockPos>();
        if (playerPos != null) {
            for (final BlockPos blockPos : BlockPos.getAllInBox(playerPos.add(vec3i), playerPos.subtract(vec3i))) {
                final Block block = this.mc.theWorld.getBlockState(blockPos).getBlock();
                if (block == Blocks.lever && this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.bedrock) {
                    continue;
                }
                final Block left = this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX() + 1, blockPos.getY(), blockPos.getZ())).getBlock();
                final Block right = this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX() + 1, blockPos.getY(), blockPos.getZ())).getBlock();
                final Block left2 = this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX() - 1, blockPos.getY(), blockPos.getZ())).getBlock();
                final Block right2 = this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX() - 1, blockPos.getY(), blockPos.getZ())).getBlock();
                final Block left3 = this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1)).getBlock();
                final Block right3 = this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1)).getBlock();
                final Block left4 = this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1)).getBlock();
                final Block right4 = this.mc.theWorld.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1)).getBlock();
                final boolean isWaterButton = block == Blocks.lever && (left == Blocks.diamond_block || left == Blocks.quartz_block || left == Blocks.gold_block || left == Blocks.emerald_block || left == Blocks.coal_block || left == Blocks.stained_hardened_clay || right == Blocks.diamond_block || right == Blocks.quartz_block || right == Blocks.gold_block || right == Blocks.emerald_block || right == Blocks.coal_block || right == Blocks.stained_hardened_clay || left2 == Blocks.diamond_block || left2 == Blocks.quartz_block || left2 == Blocks.gold_block || left2 == Blocks.emerald_block || left2 == Blocks.coal_block || left2 == Blocks.stained_hardened_clay || right2 == Blocks.diamond_block || right2 == Blocks.quartz_block || right2 == Blocks.gold_block || right2 == Blocks.emerald_block || right2 == Blocks.coal_block || right2 == Blocks.stained_hardened_clay || left3 == Blocks.diamond_block || left3 == Blocks.quartz_block || left3 == Blocks.gold_block || left3 == Blocks.emerald_block || left3 == Blocks.coal_block || left3 == Blocks.stained_hardened_clay || right3 == Blocks.diamond_block || right3 == Blocks.quartz_block || right3 == Blocks.gold_block || right3 == Blocks.emerald_block || right3 == Blocks.coal_block || right3 == Blocks.stained_hardened_clay || left4 == Blocks.diamond_block || left4 == Blocks.quartz_block || left4 == Blocks.gold_block || left4 == Blocks.emerald_block || left4 == Blocks.coal_block || left4 == Blocks.stained_hardened_clay || right4 == Blocks.diamond_block || right4 == Blocks.quartz_block || right4 == Blocks.gold_block || right4 == Blocks.emerald_block || right4 == Blocks.coal_block || right4 == Blocks.stained_hardened_clay);
                if (isWaterButton) {
                    continue;
                }
                if (!this.shouldEspBlock(block, blockPos) || this.usedBlocks.contains(blockPos)) {
                    continue;
                }
                poses.add(blockPos);
            }
            poses.sort(Comparator.comparingDouble(pos -> this.mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ())));
        }
        if (!poses.isEmpty()) {
            return poses.get(0);
        }
        return null;
    }
    
    @EventHandler
    private void onDraw(final EventRender3D event) {
        if (this.currentBlock != null) {
            RenderUtil.drawSolidBlockESP(this.currentBlock, Colors.MAGENTA.c, event.getPartialTicks());
        }
        if (this.shoulddick()) {
            for (final Map.Entry<BlockPos, Block> block : this.blockList.entrySet()) {
                if (this.usedBlocks.contains(block.getKey())) {
                    continue;
                }
                if (this.selectedBlock == null) {
                    if (RayMarchUtils.isFacingBlock(block.getKey(), ((Double)this.range.getValue()).floatValue())) {
                        this.selectedBlock = block.getKey();
                    }
                }
                else if (!RayMarchUtils.isFacingBlock(this.selectedBlock, ((Double)this.range.getValue()).floatValue())) {
                    this.selectedBlock = null;
                }
                Color color = new Color(192, 192, 192, 75);
                if (block.getValue() instanceof BlockSkull) {
                    color = new Color(51, 0, 118, 75);
                }
                if (block.getValue() instanceof BlockLever) {
                    color = new Color(51, 208, 118, 75);
                }
                if (block.getValue() instanceof BlockChest && ((BlockChest)block.getValue()).chestType == 1) {
                    color = new Color(211, 0, 118, 75);
                }
                if (block.getKey().equals((Object)this.selectedBlock)) {
                    color = new Color(65, 105, 255, 100);
                }
                RenderUtil.drawSolidBlockESP(block.getKey(), color.getRGB(), event.getPartialTicks());
            }
        }
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (!(boolean)this.auto.getValue()) {
            return;
        }
        if (this.ticks % 20 != 0) {
            ++this.ticks;
            return;
        }
        if (!Client.inDungeons || Client.instance.dungeonUtils.inBoss) {
            return;
        }
        if (!this.autoTimer.hasReached(100.0)) {
            return;
        }
        if (this.currentBlock == null) {
            this.currentBlock = this.getClosestSecret();
        }
        if (this.currentBlock != null && this.mc.playerController.onPlayerRightClick(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.inventory.getCurrentItem(), this.currentBlock, EnumFacing.fromAngle((double)this.mc.thePlayer.rotationYaw), new Vec3(Math.random(), Math.random(), Math.random()))) {
            this.mc.getNetHandler().addToSendQueue((Packet)new C0APacketAnimation());
            this.usedBlocks.add(this.currentBlock);
            this.currentBlock = this.getClosestSecret();
            this.autoTimer.reset();
        }
    }
    
    @SubscribeEvent
    public void onInteract(final PlayerInteractEvent event) {
        if (this.timer.hasReached(150.0) && this.isEnabled() && this.selectedBlock != null && !this.usedBlocks.contains(this.selectedBlock)) {
            if (this.mc.objectMouseOver != null && this.selectedBlock.equals((Object)this.mc.objectMouseOver.getBlockPos())) {
                return;
            }
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                if (this.remove.getValue()) {
                    this.usedBlocks.add(this.selectedBlock);
                }
                if (this.mc.playerController.onPlayerRightClick(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.inventory.getCurrentItem(), this.selectedBlock, EnumFacing.fromAngle((double)this.mc.thePlayer.rotationYaw), new Vec3(Math.random(), Math.random(), Math.random()))) {
                    this.mc.thePlayer.swingItem();
                    this.timer.reset();
                }
            }
        }
    }
    
    @EventHandler
    public void onBlockChange(final BlockChangeEvent event) {
        if (this.mc.theWorld == null || this.mc.thePlayer == null) {
            return;
        }
        if (event.getPosition().distanceSq((Vec3i)this.mc.thePlayer.getPosition()) > (double)this.range.getValue()) {
            return;
        }
        if (this.usedBlocks.contains(event.getPosition())) {
            return;
        }
        if (!this.shouldEspBlock(event.getNewBlock().getBlock(), event.getPosition())) {
            return;
        }
        this.blockList.put(event.getPosition(), event.getNewBlock().getBlock());
    }
    
    public boolean shouldEspBlock(final Block block, final BlockPos position) {
        if (block instanceof BlockChest || block instanceof BlockLever) {
            return true;
        }
        if (block instanceof BlockSkull) {
            final TileEntitySkull tileEntity = (TileEntitySkull)this.mc.theWorld.getTileEntity(position);
            if (tileEntity.getSkullType() == 3) {
                final Property property = ArrayUtils.firstOrNull((Iterable<Property>)tileEntity.getPlayerProfile().getProperties().get((Object)"textures"));
                return property != null && property.getValue().hashCode() == this.essenceSkinHash;
            }
        }
        return false;
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        this.blockList.clear();
        this.usedBlocks.clear();
        this.selectedBlock = null;
        this.lastCheckedPosition = null;
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.blockList.clear();
        this.usedBlocks.clear();
        this.selectedBlock = null;
        this.currentBlock = null;
        this.lastCheckedPosition = null;
        super.onDisable();
    }
}
