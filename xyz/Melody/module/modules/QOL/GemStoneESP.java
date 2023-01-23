//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import java.util.concurrent.*;
import net.minecraft.util.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.init.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.render.*;
import java.util.*;
import java.awt.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.*;

public class GemStoneESP extends Module
{
    private Numbers<Double> radius;
    private Option<Boolean> jade;
    private Option<Boolean> amber;
    private Option<Boolean> topaz;
    private Option<Boolean> sapphire;
    private Option<Boolean> amethyst;
    private Option<Boolean> jasper;
    private Option<Boolean> ruby;
    private Option<Boolean> opal;
    private Option<Boolean> pane;
    private ConcurrentHashMap<BlockPos, Gemstone> gemstones;
    private BlockPos lastChecked;
    private boolean isScanning;
    private Thread thread;
    
    public GemStoneESP() {
        super("GemStoneESP", new String[] { "tags" }, ModuleType.Render);
        this.radius = (Numbers<Double>)new Numbers("Radius", (Number)100.0, (Number)1.0, (Number)500.0, (Number)1.0);
        this.jade = (Option<Boolean>)new Option("Jade", (Object)true);
        this.amber = (Option<Boolean>)new Option("Amber", (Object)true);
        this.topaz = (Option<Boolean>)new Option("Topaz", (Object)true);
        this.sapphire = (Option<Boolean>)new Option("Sapphire", (Object)true);
        this.amethyst = (Option<Boolean>)new Option("Amethyst", (Object)true);
        this.jasper = (Option<Boolean>)new Option("Jasper", (Object)true);
        this.ruby = (Option<Boolean>)new Option("Ruby", (Object)true);
        this.opal = (Option<Boolean>)new Option("Opal", (Object)true);
        this.pane = (Option<Boolean>)new Option("GlassPane", (Object)false);
        this.gemstones = new ConcurrentHashMap<BlockPos, Gemstone>();
        this.lastChecked = null;
        this.isScanning = false;
        this.addValues(new Value[] { (Value)this.radius, (Value)this.jade, (Value)this.amber, (Value)this.topaz, (Value)this.sapphire, (Value)this.amethyst, (Value)this.jasper, (Value)this.ruby, (Value)this.opal, (Value)this.pane });
        this.setModInfo("Just Gemstone ESP.");
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            this.gemstones.clear();
            this.isScanning = false;
            this.lastChecked = null;
            return;
        }
        if (!this.isScanning && (this.lastChecked == null || !this.lastChecked.equals((Object)this.mc.thePlayer.playerLocation))) {
            this.isScanning = true;
            final int gemstoneRadius = ((Double)this.radius.getValue()).intValue();
            BlockPos playerPosition;
            final int n;
            int x;
            int y;
            int z;
            BlockPos position;
            Gemstone gemstone;
            (this.thread = new Thread(() -> {
                for (playerPosition = (this.lastChecked = this.mc.thePlayer.getPosition()), x = playerPosition.getX() - n; x < playerPosition.getX() + n; ++x) {
                    for (y = playerPosition.getY() - n; y < playerPosition.getY() + n; ++y) {
                        for (z = playerPosition.getZ() - n; z < playerPosition.getZ() + n; ++z) {
                            position = new BlockPos(x, y, z);
                            if (!this.mc.theWorld.isAirBlock(position) && (gemstone = this.getGemstone(this.mc.theWorld.getBlockState(position))) != null) {
                                this.gemstones.put(position, gemstone);
                            }
                        }
                    }
                }
                this.isScanning = false;
            }, "MelodySky-GemStoneESP")).start();
        }
    }
    
    @EventHandler
    public void onBlockChange(final BlockChangeEvent event) {
        if (event.getNewBlock().getBlock() == Blocks.air) {
            this.gemstones.remove(event.getPosition());
        }
    }
    
    @EventHandler
    public void onRenderWorld(final EventRender3D event) {
        if (Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            return;
        }
        for (final Map.Entry<BlockPos, Gemstone> gemstone : this.gemstones.entrySet()) {
            if (this.isGemstoneEnabled(gemstone.getValue())) {
                final double distance;
                if ((distance = Math.sqrt(gemstone.getKey().distanceSq(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ))) > ((Double)this.radius.getValue()).intValue() + 2) {
                    continue;
                }
                final int alpha = (int)Math.abs(100.0 - distance / ((Double)this.radius.getValue()).intValue() * 100.0);
                final Color color = ColorUtils.addAlpha(gemstone.getValue().color, alpha);
                RenderUtil.drawSolidBlockESP(gemstone.getKey(), color.getRGB(), event.getPartialTicks());
            }
        }
    }
    
    private Gemstone getGemstone(final IBlockState block) {
        if (block.getBlock() != Blocks.stained_glass) {
            return null;
        }
        if ((boolean)this.pane.getValue() && block.getBlock() != Blocks.stained_glass_pane) {
            return null;
        }
        final EnumDyeColor color = this.firstNotNull((EnumDyeColor)block.getValue((IProperty)BlockStainedGlass.COLOR), (EnumDyeColor)block.getValue((IProperty)BlockStainedGlassPane.COLOR));
        if (color == Gemstone.RUBY.dyeColor) {
            return Gemstone.RUBY;
        }
        if (color == Gemstone.AMETHYST.dyeColor) {
            return Gemstone.AMETHYST;
        }
        if (color == Gemstone.JADE.dyeColor) {
            return Gemstone.JADE;
        }
        if (color == Gemstone.SAPPHIRE.dyeColor) {
            return Gemstone.SAPPHIRE;
        }
        if (color == Gemstone.AMBER.dyeColor) {
            return Gemstone.AMBER;
        }
        if (color == Gemstone.TOPAZ.dyeColor) {
            return Gemstone.TOPAZ;
        }
        if (color == Gemstone.JASPER.dyeColor) {
            return Gemstone.JASPER;
        }
        if (color == Gemstone.OPAL.dyeColor) {
            return Gemstone.OPAL;
        }
        return null;
    }
    
    private boolean isGemstoneEnabled(final Gemstone gemstone) {
        switch (l.$SwitchMap$xyz$Melody$module$modules$QOL$GemStoneESP$Gemstone[gemstone.ordinal()]) {
            case 1: {
                return (boolean)this.ruby.getValue();
            }
            case 2: {
                return (boolean)this.amethyst.getValue();
            }
            case 3: {
                return (boolean)this.jade.getValue();
            }
            case 4: {
                return (boolean)this.sapphire.getValue();
            }
            case 5: {
                return (boolean)this.amber.getValue();
            }
            case 6: {
                return (boolean)this.topaz.getValue();
            }
            case 7: {
                return (boolean)this.jasper.getValue();
            }
            case 8: {
                return (boolean)this.opal.getValue();
            }
            default: {
                return false;
            }
        }
    }
    
    @EventHandler
    public void onWorldChange(final EventTick event) {
        if (this.mc.theWorld == null || this.mc.thePlayer == null) {
            this.gemstones.clear();
            this.lastChecked = null;
        }
    }
    
    public <T> T firstNotNull(final T... args) {
        for (final T arg : args) {
            if (arg != null) {
                return arg;
            }
        }
        return null;
    }
    
    enum Gemstone
    {
        RUBY(new Color(188, 3, 29), EnumDyeColor.RED), 
        AMETHYST(new Color(137, 0, 201), EnumDyeColor.PURPLE), 
        JADE(new Color(157, 249, 32), EnumDyeColor.LIME), 
        SAPPHIRE(new Color(60, 121, 224), EnumDyeColor.LIGHT_BLUE), 
        AMBER(new Color(237, 139, 35), EnumDyeColor.ORANGE), 
        TOPAZ(new Color(249, 215, 36), EnumDyeColor.YELLOW), 
        JASPER(new Color(214, 15, 150), EnumDyeColor.MAGENTA), 
        OPAL(new Color(245, 245, 240), EnumDyeColor.WHITE);
        
        public Color color;
        public EnumDyeColor dyeColor;
        
        private Gemstone(final Color color, final EnumDyeColor dyeColor) {
            this.color = color;
            this.dyeColor = dyeColor;
        }
    }
}
