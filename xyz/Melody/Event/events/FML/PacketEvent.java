//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.FML;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.*;

@Cancelable
public class PacketEvent extends Event
{
    public Direction direction;
    public Packet<?> packet;
    
    public PacketEvent(final Packet<?> packet) {
        this.packet = packet;
    }
    
    public Packet<?> getPacket() {
        return this.packet;
    }
    
    public static class ReceiveEvent extends PacketEvent
    {
        public ReceiveEvent(final Packet<?> packet) {
            super(packet);
            this.direction = Direction.INBOUND;
        }
    }
    
    public static class SendEvent extends PacketEvent
    {
        public SendEvent(final Packet<?> packet) {
            super(packet);
            this.direction = Direction.OUTBOUND;
        }
    }
    
    enum Direction
    {
        INBOUND, 
        OUTBOUND;
    }
}
