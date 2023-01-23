//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.rendering.*;
import net.minecraft.init.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.util.*;
import xyz.Melody.Event.*;

public class BlockOverlay extends Module
{
    private Numbers<Double> r;
    private Numbers<Double> g;
    private Numbers<Double> b;
    private Numbers<Double> a;
    private Option<Boolean> rb;
    private Numbers<Double> width;
    
    public BlockOverlay() {
        super("BlockOverlay", new String[] { "boverlay", "overlay" }, ModuleType.Render);
        this.r = (Numbers<Double>)new Numbers("Red", (Number)255.0, (Number)0.0, (Number)255.0, (Number)1.0);
        this.g = (Numbers<Double>)new Numbers("Green", (Number)255.0, (Number)0.0, (Number)255.0, (Number)1.0);
        this.b = (Numbers<Double>)new Numbers("Blue", (Number)255.0, (Number)0.0, (Number)255.0, (Number)1.0);
        this.a = (Numbers<Double>)new Numbers("Alpha", (Number)255.0, (Number)0.0, (Number)255.0, (Number)1.0);
        this.rb = (Option<Boolean>)new Option("Rainbow", (Object)false);
        this.width = (Numbers<Double>)new Numbers("Width", (Number)3.0, (Number)1.0, (Number)8.0, (Number)0.5);
        this.setModInfo("Aimming Block Overlay.");
        this.addValues(new Value[] { (Value)this.r, (Value)this.g, (Value)this.b, (Value)this.a, (Value)this.rb, (Value)this.width });
    }
    
    @EventHandler
    private void on3D(final EventRender3D e) {
        if (this.mc.objectMouseOver == null || this.mc.objectMouseOver.getBlockPos() == null || this.mc.objectMouseOver.entityHit != null) {
            return;
        }
        final BlockPos pos = this.mc.objectMouseOver.getBlockPos();
        if (this.mc.theWorld.getBlockState(pos).getBlock() == Blocks.air) {
            return;
        }
        final Color col = this.rb.getValue() ? this.addAlpha(ColorUtils.rainbow(0L, 1.0f), ((Double)this.a.getValue()).intValue()) : new Color(((Double)this.r.getValue()).intValue(), ((Double)this.g.getValue()).intValue(), ((Double)this.b.getValue()).intValue(), ((Double)this.a.getValue()).intValue());
        RenderUtil.drawSolidBlockESP(pos, col.getRGB(), ((Double)this.width.getValue()).floatValue(), e.getPartialTicks());
    }
    
    private Color addAlpha(final Color c, final int alpha) {
        final int r = c.getRed();
        final int g = c.getGreen();
        final int b = c.getBlue();
        return new Color(r, g, b, alpha);
    }
}
