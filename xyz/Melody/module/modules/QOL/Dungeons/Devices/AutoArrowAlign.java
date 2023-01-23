//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons.Devices;

import net.minecraft.util.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import net.minecraft.entity.item.*;
import xyz.Melody.Event.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.item.*;

public class AutoArrowAlign extends Module
{
    private final Set<BlockPos> clickedItemFrames;
    private static final Map<BlockPos, Integer> requiredClicksForEntity;
    private int ticks;
    private boolean foundPattern;
    
    public AutoArrowAlign() {
        super("AutoArrowAlign", new String[] { "aaa" }, ModuleType.Dungeons);
        this.clickedItemFrames = new HashSet<BlockPos>();
        this.ticks = 1;
        this.setModInfo("Auto Do A.A. When CrossHair Hovered.");
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        if (!Client.inDungeons || this.mc.currentScreen != null || this.mc.objectMouseOver == null) {
            return;
        }
        if (this.foundPattern && this.mc.objectMouseOver.entityHit instanceof EntityItemFrame) {
            final EntityItemFrame itemFrame = (EntityItemFrame)this.mc.objectMouseOver.entityHit;
            if (itemFrame.getDisplayedItem() != null && itemFrame.getDisplayedItem().getItem() == Items.arrow) {
                final BlockPos itemFrameFixedPos = new BlockPos(itemFrame.posX, itemFrame.posY, itemFrame.posZ);
                if (!this.clickedItemFrames.contains(itemFrameFixedPos) && AutoArrowAlign.requiredClicksForEntity.containsKey(itemFrameFixedPos)) {
                    final int requiredRotation = AutoArrowAlign.requiredClicksForEntity.get(itemFrameFixedPos);
                    final int currentRotation = itemFrame.getRotation();
                    if (currentRotation != requiredRotation) {
                        for (int clickAmount = (currentRotation < requiredRotation) ? (requiredRotation - currentRotation) : (requiredRotation - currentRotation + 8), i = 0; i < clickAmount; ++i) {
                            Client.rightClick();
                        }
                    }
                    this.clickedItemFrames.add(itemFrameFixedPos);
                }
            }
        }
        if (this.ticks % 70 == 0) {
            this.calculatePattern();
            this.ticks = 0;
        }
        ++this.ticks;
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        this.foundPattern = false;
        this.clickedItemFrames.clear();
        AutoArrowAlign.requiredClicksForEntity.clear();
    }
    
    private void calculatePattern() {
        AutoArrowAlign.requiredClicksForEntity.clear();
        final Map<BlockPos, Entity> itemFrames = new HashMap<BlockPos, Entity>();
        final List<BlockPos> currentItemFrames = new ArrayList<BlockPos>();
        final List<BlockPos> startItemFrames = new ArrayList<BlockPos>();
        final Set<BlockPos> endItemFrames = new HashSet<BlockPos>();
        for (final Entity entity : this.mc.theWorld.loadedEntityList) {
            if (entity instanceof EntityItemFrame) {
                final ItemStack displayed = ((EntityItemFrame)entity).getDisplayedItem();
                if (displayed == null) {
                    continue;
                }
                final Item item = displayed.getItem();
                if (item == Items.arrow) {
                    itemFrames.put(new BlockPos(entity.posX, entity.posY, entity.posZ), entity);
                }
                else {
                    if (item != Item.getItemFromBlock(Blocks.wool)) {
                        continue;
                    }
                    if (EnumDyeColor.byMetadata(displayed.getItemDamage()) == EnumDyeColor.LIME) {
                        startItemFrames.add(new BlockPos(entity.posX, entity.posY, entity.posZ));
                    }
                    else {
                        endItemFrames.add(new BlockPos(entity.posX, entity.posY, entity.posZ));
                    }
                }
            }
        }
        if (itemFrames.size() >= 9 && startItemFrames.size() != 0) {
            for (final BlockPos pos : startItemFrames) {
                BlockPos adjacent = pos.up();
                if (itemFrames.containsKey(adjacent)) {
                    currentItemFrames.add(adjacent);
                }
                adjacent = pos.down();
                if (itemFrames.containsKey(adjacent)) {
                    currentItemFrames.add(adjacent);
                }
                adjacent = pos.south();
                if (itemFrames.containsKey(adjacent)) {
                    currentItemFrames.add(adjacent);
                }
                adjacent = pos.north();
                if (itemFrames.containsKey(adjacent)) {
                    currentItemFrames.add(adjacent);
                }
            }
            for (int i = 0; i < 200; ++i) {
                if (currentItemFrames.size() == 0) {
                    if (!this.foundPattern) {
                        this.foundPattern = true;
                    }
                    return;
                }
                final List<BlockPos> list = new ArrayList<BlockPos>(currentItemFrames);
                currentItemFrames.clear();
                for (final BlockPos pos2 : list) {
                    BlockPos adjacent2 = pos2.up();
                    if (endItemFrames.contains(adjacent2)) {
                        AutoArrowAlign.requiredClicksForEntity.put(pos2, 7);
                    }
                    else {
                        adjacent2 = pos2.down();
                        if (endItemFrames.contains(adjacent2)) {
                            AutoArrowAlign.requiredClicksForEntity.put(pos2, 3);
                        }
                        else {
                            adjacent2 = pos2.south();
                            if (endItemFrames.contains(adjacent2)) {
                                AutoArrowAlign.requiredClicksForEntity.put(pos2, 5);
                            }
                            else {
                                adjacent2 = pos2.north();
                                if (endItemFrames.contains(adjacent2)) {
                                    AutoArrowAlign.requiredClicksForEntity.put(pos2, 1);
                                }
                                else {
                                    if (AutoArrowAlign.requiredClicksForEntity.containsKey(pos2)) {
                                        continue;
                                    }
                                    adjacent2 = pos2.up();
                                    if (itemFrames.containsKey(adjacent2) && !AutoArrowAlign.requiredClicksForEntity.containsKey(adjacent2)) {
                                        currentItemFrames.add(adjacent2);
                                        AutoArrowAlign.requiredClicksForEntity.put(pos2, 7);
                                    }
                                    else {
                                        adjacent2 = pos2.down();
                                        if (itemFrames.containsKey(adjacent2) && !AutoArrowAlign.requiredClicksForEntity.containsKey(adjacent2)) {
                                            currentItemFrames.add(adjacent2);
                                            AutoArrowAlign.requiredClicksForEntity.put(pos2, 3);
                                        }
                                        else {
                                            adjacent2 = pos2.south();
                                            if (itemFrames.containsKey(adjacent2) && !AutoArrowAlign.requiredClicksForEntity.containsKey(adjacent2)) {
                                                currentItemFrames.add(adjacent2);
                                                AutoArrowAlign.requiredClicksForEntity.put(pos2, 5);
                                            }
                                            else {
                                                adjacent2 = pos2.north();
                                                if (!itemFrames.containsKey(adjacent2)) {
                                                    continue;
                                                }
                                                if (AutoArrowAlign.requiredClicksForEntity.containsKey(adjacent2)) {
                                                    continue;
                                                }
                                                currentItemFrames.add(adjacent2);
                                                AutoArrowAlign.requiredClicksForEntity.put(pos2, 1);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            this.foundPattern = false;
        }
    }
    
    static {
        requiredClicksForEntity = new HashMap<BlockPos, Integer>();
    }
}
