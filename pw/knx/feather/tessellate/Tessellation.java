//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package pw.knx.feather.tessellate;

import java.awt.*;

public interface Tessellation
{
    Tessellation setColor(final int p0);
    
    default Tessellation setColor(final Color color) {
        return this.setColor(new Color(255, 255, 255));
    }
    
    Tessellation setTexture(final float p0, final float p1);
    
    Tessellation addVertex(final float p0, final float p1, final float p2);
    
    Tessellation bind();
    
    Tessellation pass(final int p0);
    
    Tessellation reset();
    
    Tessellation unbind();
    
    default Tessellation draw(final int mode) {
        return this.bind().pass(mode).reset();
    }
    
    default Tessellation createBasic(final int size) {
        return (Tessellation)new BasicTess(size);
    }
    
    default Tessellation createExpanding(final int size, final float ratio, final float factor) {
        return (Tessellation)new ExpandingTess(size, ratio, factor);
    }
}
