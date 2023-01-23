//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.misc;

import xyz.Melody.Event.*;
import net.minecraft.entity.player.*;

public class EventClickSlot extends Event
{
    private int windowID;
    private int slotNumber;
    private int button;
    private int mode;
    private EntityPlayer player;
    
    public EventClickSlot(final int windowID, final int slotNumber, final int button, final int mode, final EntityPlayer player) {
        this.windowID = windowID;
        this.slotNumber = slotNumber;
        this.button = button;
        this.mode = mode;
        this.player = player;
    }
    
    public int getWindowID() {
        return this.windowID;
    }
    
    public int getSlotNumber() {
        return this.slotNumber;
    }
    
    public int getButton() {
        return this.button;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
}
