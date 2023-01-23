//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.rendering;

import xyz.Melody.Event.*;

public class EventRender3D extends Event
{
    private float ticks;
    private boolean isUsingShaders;
    
    public EventRender3D() {
    }
    
    public EventRender3D(final float ticks) {
        this.ticks = ticks;
    }
    
    public float getPartialTicks() {
        return this.ticks;
    }
    
    public boolean isUsingShaders() {
        return this.isUsingShaders;
    }
}
