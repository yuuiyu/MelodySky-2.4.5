//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.fakemc;

import net.minecraft.client.network.*;
import net.minecraft.client.*;
import net.minecraft.network.*;
import java.util.*;

public class FakeNetHandlerPlayClient extends NetHandlerPlayClient
{
    private NetworkPlayerInfo playerInfo;
    
    public FakeNetHandlerPlayClient(final Minecraft mcIn) {
        super(mcIn, mcIn.currentScreen, (NetworkManager)new FakeNetworkManager(EnumPacketDirection.CLIENTBOUND), mcIn.getSession().getProfile());
        this.playerInfo = new NetworkPlayerInfo(mcIn.getSession().getProfile());
    }
    
    public NetworkPlayerInfo getPlayerInfo(final UUID uniqueId) {
        return this.playerInfo;
    }
    
    public NetworkPlayerInfo getPlayerInfo(final String name) {
        return this.playerInfo;
    }
}
