//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.Utils.*;
import net.minecraft.entity.player.*;
import com.mojang.realmsclient.dto.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.util.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import xyz.Melody.*;

public class AntiBot extends Module
{
    private TimeHelper timer;
    private List<EntityPlayer> invalid;
    private List<EntityPlayer> whitelist;
    private List<EntityPlayer> hurtTimeCheck;
    private List<PlayerInfo> playerList;
    public Value<Boolean> remove;
    public Value<Double> hurttime;
    public Value<Boolean> touchedGround;
    public Value<Boolean> toolCheck;
    private static List<EntityPlayer> removed;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public List<Integer> onAirInvalid;
    
    public AntiBot() {
        super("AntiBot", ModuleType.Balance);
        this.timer = new TimeHelper();
        this.invalid = new ArrayList<EntityPlayer>();
        this.whitelist = new ArrayList<EntityPlayer>();
        this.hurtTimeCheck = new ArrayList<EntityPlayer>();
        this.playerList = new ArrayList<PlayerInfo>();
        this.remove = (Value<Boolean>)new Option("AutoRemove", (Object)true);
        this.hurttime = (Value<Double>)new Numbers("HurtTimeCheck", (Number)10000.0, (Number)5000.0, (Number)20000.0, (Number)100.0);
        this.touchedGround = (Value<Boolean>)new Option("GroundCheck", (Object)true);
        this.toolCheck = (Value<Boolean>)new Option("ToolCheck", (Object)true);
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        this.onAirInvalid = new ArrayList<Integer>();
        this.addValues(this.remove, this.hurttime, this.touchedGround, this.toolCheck);
    }
    
    @EventHandler
    public void onUpdate(final EventPreUpdate e) {
        if (!this.hurtTimeCheck.isEmpty() && this.timer3.isDelayComplete(((Double)this.hurttime.getValue()).longValue())) {
            this.hurtTimeCheck.clear();
            this.timer3.reset();
        }
        if (!this.whitelist.isEmpty() && this.timer2.isDelayComplete(3000L)) {
            this.whitelist.clear();
            this.timer2.reset();
        }
        if (!this.invalid.isEmpty() && this.timer.isDelayComplete(1000L)) {
            this.invalid.clear();
            this.timer.reset();
        }
        for (final Object o : this.mc.theWorld.getLoadedEntityList()) {
            if (o instanceof EntityPlayer) {
                final EntityPlayer ent = (EntityPlayer)o;
                if (ent == this.mc.thePlayer || this.invalid.contains(ent)) {
                    continue;
                }
                final String formated = ent.getDisplayName().getFormattedText();
                final String custom = ent.getCustomNameTag();
                final String name = ent.getName();
                if (!formated.startsWith("\ufffd\ufffd") && formated.endsWith("\ufffd\ufffdr")) {
                    this.invalid.add(ent);
                }
                if (!this.isInTablist(ent)) {
                    this.invalid.add(ent);
                }
                if (ent.hurtTime > 0) {
                    this.hurtTimeCheck.add(ent);
                }
                if (this.hurtTimeCheck.contains(ent) && !this.whitelist.contains(ent)) {
                    this.whitelist.add(ent);
                }
                if (ent.getHeldItem() != null) {
                    this.whitelist.add(ent);
                }
                if (ent.getHeldItem() == null && !this.whitelist.contains(ent) && (boolean)this.toolCheck.getValue()) {
                    this.invalid.add(ent);
                }
                if (ent.isInvisible() && !custom.equalsIgnoreCase("") && custom.toLowerCase().contains("\ufffd\ufffdc\ufffd\ufffdc") && name.contains("\ufffd\ufffdc")) {
                    this.invalid.add(ent);
                }
                if (!custom.equalsIgnoreCase("") && custom.toLowerCase().contains("\ufffd\ufffdc") && custom.toLowerCase().contains("\ufffd\ufffdr")) {
                    this.invalid.add(ent);
                }
                if (formated.contains("\ufffd\ufffd8[NPC]")) {
                    this.invalid.add(ent);
                }
                if (formated.contains("\ufffd\ufffdc") || custom.equalsIgnoreCase("")) {
                    continue;
                }
                this.invalid.add(ent);
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.onAirInvalid.clear();
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        this.onAirInvalid.clear();
        super.onDisable();
    }
    
    @EventHandler
    public void onReceivePacket(final EventPacketRecieve event) {
        if (this.mc.theWorld == null || this.mc.thePlayer == null) {
            return;
        }
        if (event.getPacket() instanceof S14PacketEntity) {
            final S14PacketEntity packet = (S14PacketEntity)event.getPacket();
            final Entity entity;
            if ((entity = packet.getEntity((World)this.mc.theWorld)) instanceof EntityPlayer && !packet.getOnGround() && !this.onAirInvalid.contains(entity.getEntityId())) {
                this.onAirInvalid.add(entity.getEntityId());
            }
        }
    }
    
    public boolean isInTablist(final EntityPlayer player) {
        if (Minecraft.getMinecraft().isSingleplayer()) {
            return true;
        }
        for (final Object o : Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap()) {
            final NetworkPlayerInfo playerInfo = (NetworkPlayerInfo)o;
            if (playerInfo.getGameProfile().getName().equalsIgnoreCase(player.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isEntityBot(final Entity e) {
        if (!Client.instance.getModuleManager().getModuleByClass(AntiBot.class).isEnabled()) {
            return false;
        }
        if (!(e instanceof EntityPlayer) || !Client.instance.getModuleManager().getModuleByClass(AntiBot.class).isEnabled()) {
            return false;
        }
        final EntityPlayer player = (EntityPlayer)e;
        return this.invalid.contains(player) || !this.onAirInvalid.contains(player.getEntityId());
    }
    
    static {
        AntiBot.removed = new ArrayList<EntityPlayer>();
    }
}
