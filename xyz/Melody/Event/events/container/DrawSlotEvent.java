//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.container;

import xyz.Melody.Event.*;
import net.minecraft.inventory.*;

public class DrawSlotEvent extends Event
{
    public Container container;
    public Slot slot;
    
    public DrawSlotEvent(final Container container, final Slot slot) {
        this.container = container;
        this.slot = slot;
    }
}
