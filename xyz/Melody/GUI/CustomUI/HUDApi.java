//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI;

import net.minecraft.client.*;
import xyz.Melody.Event.*;
import net.minecraftforge.common.*;

public class HUDApi
{
    public String name;
    public int x;
    public int y;
    private boolean enabled;
    public static boolean useISR;
    public Minecraft mc;
    
    public HUDApi(final String name, final int x, final int y) {
        this.mc = Minecraft.getMinecraft();
        this.name = name;
        this.x = x;
        this.y = y;
    }
    
    public void InScreenRender() {
        HUDApi.useISR = true;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean b) {
        if (b) {
            EventBus.getInstance().register(new Object[] { this });
            this.regFML(this);
        }
        else {
            EventBus.getInstance().unregister(new Object[] { this });
            this.unregFML(this);
        }
        this.enabled = b;
    }
    
    private void regFML(final Object obj) {
        MinecraftForge.EVENT_BUS.register(obj);
    }
    
    private void unregFML(final Object obj) {
        MinecraftForge.EVENT_BUS.unregister(obj);
    }
    
    public void setXY(final int newX, final int newY) {
        this.x = newX;
        this.y = newY;
    }
}
