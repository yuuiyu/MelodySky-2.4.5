//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Chat;

import by.radioegor146.nativeobfuscator.*;
import java.net.*;
import xyz.Melody.Utils.*;
import net.minecraft.client.*;
import java.io.*;
import net.minecraft.client.entity.*;
import xyz.Melody.*;
import org.apache.logging.log4j.*;
import net.minecraft.client.multiplayer.*;
import xyz.Melody.System.Melody.Authentication.*;
import java.nio.charset.*;
import java.util.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.lang.invoke.*;
import net.minecraft.util.*;

@Native
public class IRC
{
    private static final /* synthetic */ int[] llIIIlI;
    private static /* synthetic */ String[] llIIIII;
    private static /* synthetic */ Class[] lIllIII;
    private static final /* synthetic */ String[] lIllIIl;
    private static /* synthetic */ String[] lIlIlll;
    
    public void sendPrefixMsg(final String lllIlIIlIlllIll, final boolean lllIlIIlIlllIlI) {
        String lllIlIIlIlllIIl = IRC.lIllIIl[IRC.llIIIlI[53]];
        int lllIlIIlIllllIl = IRC.llIIIlI[0];
        while (llllllll(lllIlIIlIllllIl, invokedynamic(57:(Ljava/lang/String;)I, lllIlIIlIlllIll))) {
            lllIlIIlIlllIIl = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(59:(Ljava/lang/StringBuilder;C)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), lllIlIIlIlllIIl), invokedynamic(58:(Ljava/lang/String;I)C, lllIlIIlIlllIll, lllIlIIlIllllIl)));
            ++lllIlIIlIllllIl;
            "".length();
            if (" ".length() << " ".length() == 0) {
                return;
            }
        }
        if (lllllIIl(invokedynamic(25:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Z, invokedynamic(24:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Authentication/AuthManager;, invokedynamic(4:()Lxyz/Melody/Client;))))) {
            if (llllIlll(invokedynamic(33:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))) && llllIlll(invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))) {
            }
            // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[54]])
            // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[55]])
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[56]])
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[57]])
            return;
        }
        if (lllllIII(invokedynamic(2:()Ljava/io/PrintWriter;))) {
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[58]])
            // invokedynamic(12:(Lxyz/Melody/Client;Z)V, invokedynamic(4:()Lxyz/Melody/Client;), IRC.llIIIlI[1])
            return;
        }
        String lllIlIIlIlllIII = IRC.lIllIIl[IRC.llIIIlI[59]];
        final String lllIlIIlIllIlll = invokedynamic(29:(Ljava/util/UUID;)Ljava/lang/String;, invokedynamic(28:(Lcom/mojang/authlib/GameProfile;)Ljava/util/UUID;, invokedynamic(27:(Lnet/minecraft/util/Session;)Lcom/mojang/authlib/GameProfile;, invokedynamic(22:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/util/Session;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))));
        final String lllIlIIlIllIllI = invokedynamic(60:(Lnet/minecraft/client/entity/EntityPlayerSP;)Ljava/lang/String;, invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)));
        if (lllllIll(invokedynamic(61:(Ljava/lang/String;Ljava/lang/CharSequence;)Z, lllIlIIlIllIlll, IRC.lIllIIl[IRC.llIIIlI[60]]))) {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[61]]), lllIlIIlIllIllI));
            "".length();
            if (((0x2 ^ 0x9) & ~(0x17 ^ 0x1C)) != ((0x48 ^ 0x59) & ~(0x4B ^ 0x5A))) {
                return;
            }
        }
        else if (lllllIll(invokedynamic(61:(Ljava/lang/String;Ljava/lang/CharSequence;)Z, lllIlIIlIllIlll, IRC.lIllIIl[IRC.llIIIlI[62]]))) {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[63]]), lllIlIIlIllIllI));
            "".length();
            if (" ".length() << " ".length() <= (" ".length() << "   ".length() & (" ".length() << "   ".length() ^ -" ".length()))) {
                return;
            }
        }
        else if (lllllIll(invokedynamic(61:(Ljava/lang/String;Ljava/lang/CharSequence;)Z, lllIlIIlIllIlll, IRC.lIllIIl[IRC.llIIIlI[64]]))) {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[65]]), lllIlIIlIllIllI));
            "".length();
            if (" ".length() == -" ".length()) {
                return;
            }
        }
        else if (lllllIll(invokedynamic(61:(Ljava/lang/String;Ljava/lang/CharSequence;)Z, lllIlIIlIllIlll, IRC.lIllIIl[IRC.llIIIlI[66]]))) {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[67]]), lllIlIIlIllIllI));
            "".length();
            if (-" ".length() >= " ".length()) {
                return;
            }
        }
        else if (lllllIll(invokedynamic(61:(Ljava/lang/String;Ljava/lang/CharSequence;)Z, lllIlIIlIllIlll, IRC.lIllIIl[IRC.llIIIlI[68]]))) {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[69]]), lllIlIIlIllIllI));
            "".length();
            if ("   ".length() == " ".length()) {
                return;
            }
        }
        else if (lllllIll(invokedynamic(61:(Ljava/lang/String;Ljava/lang/CharSequence;)Z, lllIlIIlIllIlll, IRC.lIllIIl[IRC.llIIIlI[70]]))) {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[71]]), lllIlIIlIllIllI));
            "".length();
            if (null != null) {
                return;
            }
        }
        else if (lllllIll(invokedynamic(61:(Ljava/lang/String;Ljava/lang/CharSequence;)Z, lllIlIIlIllIlll, IRC.lIllIIl[IRC.llIIIlI[72]]))) {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[73]]), lllIlIIlIllIllI));
            "".length();
            if (-(0x75 ^ 0x70) >= 0) {
                return;
            }
        }
        else {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[74]]), invokedynamic(60:(Lnet/minecraft/client/entity/EntityPlayerSP;)Ljava/lang/String;, invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))));
        }
        if (llllIlll(invokedynamic(62:()Ljava/lang/String;))) {
            lllIlIIlIlllIII = invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[75]]), invokedynamic(62:()Ljava/lang/String;)), IRC.lIllIIl[IRC.llIIIlI[76]]), lllIlIIlIllIllI));
        }
    }
    // invokedynamic(32:(Ljava/io/PrintWriter;Ljava/lang/String;)V, invokedynamic(2:()Ljava/io/PrintWriter;), invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[77]]), lllIlIIlIlllIII), IRC.lIllIIl[IRC.llIIIlI[78]]), lllIlIIlIlllIIl)))
    
    public void connect() {
    }
    
    private static boolean lllllIll(final int lllIlIIIlIlIIII) {
        return lllIlIIIlIlIIII != 0;
    }
    
    public void exit() {
        if (llllIlll(invokedynamic(2:()Ljava/io/PrintWriter;))) {
        }
        // invokedynamic(3:(Lxyz/Melody/System/Melody/Chat/IRC;Ljava/lang/String;)V, this, IRC.lIllIIl[IRC.llIIIlI[0]])
    }
    
    private static void lllIllII() {
        (lIllIIl = new String[IRC.llIIIlI[144]])[IRC.llIIIlI[0]] = lllIIIIl(IRC.llIIIII[IRC.llIIIlI[0]], IRC.llIIIII[IRC.llIIIlI[1]]);
        IRC.lIllIIl[IRC.llIIIlI[1]] = lllIIIIl(IRC.llIIIII[IRC.llIIIlI[3]], IRC.llIIIII[IRC.llIIIlI[4]]);
        IRC.lIllIIl[IRC.llIIIlI[3]] = lllIIIlI(IRC.llIIIII[IRC.llIIIlI[5]], IRC.llIIIII[IRC.llIIIlI[6]]);
        IRC.lIllIIl[IRC.llIIIlI[4]] = lllIIIIl(IRC.llIIIII[IRC.llIIIlI[7]], IRC.llIIIII[IRC.llIIIlI[8]]);
        IRC.lIllIIl[IRC.llIIIlI[5]] = lllIIIIl(IRC.llIIIII[IRC.llIIIlI[9]], IRC.llIIIII[IRC.llIIIlI[10]]);
        IRC.lIllIIl[IRC.llIIIlI[6]] = lllIIIlI(IRC.llIIIII[IRC.llIIIlI[11]], IRC.llIIIII[IRC.llIIIlI[12]]);
        IRC.lIllIIl[IRC.llIIIlI[7]] = lllIIIlI(IRC.llIIIII[IRC.llIIIlI[13]], IRC.llIIIII[IRC.llIIIlI[14]]);
        IRC.lIllIIl[IRC.llIIIlI[8]] = lllIIIlI(IRC.llIIIII[IRC.llIIIlI[15]], IRC.llIIIII[IRC.llIIIlI[16]]);
        IRC.lIllIIl[IRC.llIIIlI[9]] = lllIIIIl(IRC.llIIIII[IRC.llIIIlI[17]], IRC.llIIIII[IRC.llIIIlI[18]]);
        IRC.lIllIIl[IRC.llIIIlI[10]] = lllIIIIl(IRC.llIIIII[IRC.llIIIlI[19]], IRC.llIIIII[IRC.llIIIlI[20]]);
        IRC.lIllIIl[IRC.llIIIlI[11]] = lllIIIlI(IRC.llIIIII[IRC.llIIIlI[21]], IRC.llIIIII[IRC.llIIIlI[22]]);
        IRC.lIllIIl[IRC.llIIIlI[12]] = lllIIIlI(IRC.llIIIII[IRC.llIIIlI[23]], IRC.llIIIII[IRC.llIIIlI[24]]);
        IRC.lIllIIl[IRC.llIIIlI[13]] = lllIIIll(IRC.llIIIII[IRC.llIIIlI[25]], IRC.llIIIII[IRC.llIIIlI[26]]);
        IRC.lIllIIl[IRC.llIIIlI[14]] = lllIIIll(IRC.llIIIII[IRC.llIIIlI[28]], IRC.llIIIII[IRC.llIIIlI[29]]);
        IRC.lIllIIl[IRC.llIIIlI[15]] = lllIIIll(IRC.llIIIII[IRC.llIIIlI[30]], IRC.llIIIII[IRC.llIIIlI[31]]);
        IRC.lIllIIl[IRC.llIIIlI[16]] = lllIIIIl(IRC.llIIIII[IRC.llIIIlI[32]], IRC.llIIIII[IRC.llIIIlI[33]]);
        IRC.lIllIIl[IRC.llIIIlI[17]] = lllIIIll(IRC.llIIIII[IRC.llIIIlI[34]], IRC.llIIIII[IRC.llIIIlI[35]]);
        IRC.lIllIIl[IRC.llIIIlI[18]] = lllIIIIl(IRC.llIIIII[IRC.llIIIlI[36]], IRC.llIIIII[IRC.llIIIlI[37]]);
        IRC.lIllIIl[IRC.llIIIlI[19]] = lllIIIlI(IRC.llIIIII[IRC.llIIIlI[38]], IRC.llIIIII[IRC.llIIIlI[39]]);
        IRC.lIllIIl[IRC.llIIIlI[20]] = lllIIIll(IRC.llIIIII[IRC.llIIIlI[40]], IRC.llIIIII[IRC.llIIIlI[41]]);
        IRC.lIllIIl[IRC.llIIIlI[21]] = lllIIIlI("KiIWKSEVFi5lFTg9MBhuNSonAA0lKjdlAD4hNmgYND06Awc0K1MQHTQ9X2UNPiE9AA0lJjwLbjUqPQwLNUE=", "qosEN");
        IRC.lIllIIl[IRC.llIIIlI[22]] = lllIIIll("xb9VmDdBvZs=", "sQJMU");
        IRC.lIllIIl[IRC.llIIIlI[23]] = lllIIIlI("Hg4ELhVlAzMZLSYzMwloES83GWgcKCNNKTcidgxoCwgYKGUTIiQELiwiMk0dNiIkQWg2KHY0JzBnAQQkKWc4AjxlBDkDJiAkIk08KmcfPwtlFDMfPiA1eA==", "EGVmH");
        IRC.lIllIIl[IRC.llIIIlI[24]] = lllIIIll("WdWIBuwmvjyseTrM0vOuZQ==", "pDyQh");
        IRC.lIllIIl[IRC.llIIIlI[25]] = lllIIIll("xVS4EBU28Eol2/gLW6Uy4OLHPrM8CGniajl6LsH6ScB+kjqOouQtOVpZmvv6HMxq", "ONzFa");
        IRC.lIllIIl[IRC.llIIIlI[26]] = lllIIIll("UqdSk051M/q7CWb3Pjie3UUWaCFSJHjFtn1ab/Ij6pY+casFM8D1/5A47qxSP5V9", "hdfed");
        IRC.lIllIIl[IRC.llIIIlI[28]] = lllIIIlI("IwQrDApYCxgmOx0pWTs4WBscPT4eNFkMOxEoFzt3LT4cPXk=", "xMyOW");
        IRC.lIllIIl[IRC.llIIIlI[29]] = lllIIIll("6XMO5YFosepdfniJ/CGDCM358Ud5CQBY9ZxyQeJUBObpDIMviR93sj9Y4E9w1HoCgs/bO/z5OGOIfeN6W66GOw==", "mnvsh");
        IRC.lIllIIl[IRC.llIIIlI[30]] = lllIIIIl("4248CkX/JndUqTGD5T1KpnBiMqCbAdVTRbVxFvaLo6LwOA8cYy5XIKPkJKLOws+A", "LuBnR");
        IRC.lIllIIl[IRC.llIIIlI[31]] = lllIIIll("Iwb0nHlQrWEl4fwwO5JqdCcnTemH5djAFxYwOhlos/ar9fodTpcj5t2r41yj+zHUD8lrG6WYiXTFqfRlAd87C7+bEPNW3nAj", "TTRcf");
        IRC.lIllIIl[IRC.llIIIlI[32]] = lllIIIIl("rFV0CuZZRs4=", "vqgDU");
        IRC.lIllIIl[IRC.llIIIlI[33]] = lllIIIll("BcUSwcg5pN9NH+vv6X+b7w==", "dUvSE");
        IRC.lIllIIl[IRC.llIIIlI[34]] = lllIIIIl("VF39CLi6yeU=", "OLsdb");
        IRC.lIllIIl[IRC.llIIIlI[35]] = lllIIIll("Y5TPFVf6Yfg=", "AeiBN");
        IRC.lIllIIl[IRC.llIIIlI[36]] = lllIIIIl("Vp+kKGZGIug=", "ycPCm");
        IRC.lIllIIl[IRC.llIIIlI[37]] = lllIIIlI("Lw==", "oFntp");
        IRC.lIllIIl[IRC.llIIIlI[38]] = lllIIIll("l9WtiVFv3m8=", "ExHdY");
        IRC.lIllIIl[IRC.llIIIlI[39]] = lllIIIIl("0pYU9OSQ0gRfry7aWnpDog==", "GPAAs");
        IRC.lIllIIl[IRC.llIIIlI[40]] = lllIIIll("3U2f8NxdFe4=", "jrDdc");
        IRC.lIllIIl[IRC.llIIIlI[41]] = lllIIIlI("YkJAXUI=", "Pltsw");
        IRC.lIllIIl[IRC.llIIIlI[42]] = lllIIIlI("LjECUiYIDS8XBhMGJVM=", "gcAre");
        IRC.lIllIIl[IRC.llIIIlI[43]] = lllIIIIl("2/yPPggFSON5YFDEbT1UmTqzstL6iNaDTPR81Xj2b8xKs0nQnNzV3A==", "LHThN");
        IRC.lIllIIl[IRC.llIIIlI[44]] = lllIIIIl("76nDTILqDr7x2Mk/BpDg0JmZyT3KPC/fOet/WQrzgLgq7fTy6awc+Q==", "RCUbT");
        IRC.lIllIIl[IRC.llIIIlI[45]] = lllIIIIl("9pTl8jiMAsg=", "SPiRq");
        IRC.lIllIIl[IRC.llIIIlI[46]] = lllIIIIl("qFfDESkDV3f622hHyHh6D74DLIZW8ROdY5fgv+46EzE=", "ZBSlF");
        IRC.lIllIIl[IRC.llIIIlI[47]] = lllIIIIl("6XEgsuWICBPneeOL38qo9ic8aXC67Q2DTLKm4Hrd7FMEixv8sHSKWA==", "jEeFS");
        IRC.lIllIIl[IRC.llIIIlI[48]] = lllIIIIl("Xd54e8u1RcyoiQB8lQcM7r08wVdze9KI7aRrWHbM+9uJh8CfYiHZtQ==", "osqIe");
        IRC.lIllIIl[IRC.llIIIlI[49]] = lllIIIIl("ShuWhMIKbGzM9r+/K/jbvpojE0hQSby4BztorBfX7aG4I8X4VZzNo8Ipkd91W6/Bzb/LdZFQ7WI2DXQtcU5bIg==", "uunQi");
        IRC.lIllIIl[IRC.llIIIlI[50]] = lllIIIIl("xJLX7Bw8T7NAtobkl+8+/xY/eqt/jlVESUmOTJRXUzDo/JNE4WOnHaKO1GqKXuwV", "ttURV");
        IRC.lIllIIl[IRC.llIIIlI[51]] = lllIIIll("OwvGeANt4Bg3DQeu09+tbiIIM6B+rpERQJBJfvuqaOdmQYP8bWmZDq05/MHgOHoeNhhla3ZbhBYlr+Asn9Ym4RdfclzjAQDC", "fmRke");
        IRC.lIllIIl[IRC.llIIIlI[52]] = lllIIIlI("HicqCAohExJEPgw4DDlFEAQqHBUgCTsBAWUvPRYKN0Q=", "EjOde");
        IRC.lIllIIl[IRC.llIIIlI[53]] = lllIIIlI("", "ZwVsR");
        IRC.lIllIIl[IRC.llIIIlI[54]] = lllIIIll("kBTymIvcb3ZL/SATxeGl9IRGpL/4/VU26tm0xDh5NMLG04lpR0qDig==", "mIWwK");
        IRC.lIllIIl[IRC.llIIIlI[55]] = lllIIIlI("PQ4CBC0jAQMZJicbHh8rO28VPAoFJDI0RT8gIiJFJSA5PgAFOz4/C0Y7OHARDip3GTclbwQ1FxAqJX4=", "fOWPe");
        IRC.lIllIIl[IRC.llIIIlI[56]] = lllIIIlI("DykjKD8wHRtkCx02BRlwEgUvKDUwRDIrcAIBNC02LUQFKDkxCjJkBScBNGo=", "TdFDP");
        IRC.lIllIIl[IRC.llIIIlI[57]] = lllIIIIl("cR5tyW4TDwDgsFMNNCP7h7laTpOF3F/+oRNU1UXnkDrq65KIf5xDCTQZz4X5SgFDMTpYhHB/BnvuJ2Wq0lZU1W0ZYySdsbU9", "pyMtQ");
        IRC.lIllIIl[IRC.llIIIlI[58]] = lllIIIIl("FsrIym+8aYF9+da1rujB3XrMBE73qaEn4bnbiblid3+noCYza7SwEw==", "oDEzx");
        IRC.lIllIIl[IRC.llIIIlI[59]] = lllIIIIl("ZLc95hOLV5U=", "MHaKG");
        IRC.lIllIIl[IRC.llIIIlI[60]] = lllIIIIl("htRHpqz0oN4W03yAh1Dxz+s2sFWHn5cM05lG0P4c7CwQKrwWNEOzVA==", "XtzXW");
        IRC.lIllIIl[IRC.llIIIlI[61]] = lllIIIlI("w7ImPQQpOS0CMBHDsiA=", "UBfIL");
        IRC.lIllIIl[IRC.llIIIlI[62]] = lllIIIIl("iR5ms93luc7OfTt3cTm4hmJUKeFCaMHguIJkY7BU5LuYlQKLF73W6Q==", "qchZI");
        IRC.lIllIIl[IRC.llIIIlI[63]] = lllIIIll("N+P+RHdewSJYNLjVAnxYJw==", "FrSMY");
        IRC.lIllIIl[IRC.llIIIlI[64]] = lllIIIIl("8oFoqTZVraaigtHXup6ciQXhZ9ztS+vrwLx0i4OkUEyNS2SmdnGNYA==", "devsv");
        IRC.lIllIIl[IRC.llIIIlI[65]] = lllIIIIl("xXzOwyc6+BlPxXKJ+NyIjA==", "YOuIm");
        IRC.lIllIIl[IRC.llIIIlI[66]] = lllIIIIl("UqE4rnYqblJ/8L+iRtrFZW0Hcv4tfIaL4RlSpQGX10sve855wBuiqg==", "pKqVi");
        IRC.lIllIIl[IRC.llIIIlI[67]] = lllIIIlI("w6ATKOeRi+S7q+WpmyzDlAM=", "Gqsaq");
        IRC.lIllIIl[IRC.llIIIlI[68]] = lllIIIIl("kIUKCzLb6W+yHpvpXqzAjDopzW6Q6wCSXyjeb/ibvbwf7XoxUYKCyA==", "MUYas");
        IRC.lIllIIl[IRC.llIIIlI[69]] = lllIIIlI("w49TAueKsQ7DjwM=", "haYfS");
        IRC.lIllIIl[IRC.llIIIlI[70]] = lllIIIll("qPXDnltlN5y7uO4q1TXCN0NxcDBoRky57RYtDgCJaV9TM8s8lv44gA==", "GKoUK");
        IRC.lIllIIl[IRC.llIIIlI[71]] = lllIIIlI("w7QbN+aJlOS5veaZvOiYiuekshjDlzE=", "SalEp");
        IRC.lIllIIl[IRC.llIIIlI[72]] = lllIIIll("dVBzJS4hZ1MjZOk5qAkuDDxxR2T+c9Fi2n67M97eO6js3m//WI5UCg==", "UUKyR");
        IRC.lIllIIl[IRC.llIIIlI[73]] = lllIIIll("CSOzNPSusKNQ7LmJhqPuxA==", "IRQFz");
        IRC.lIllIIl[IRC.llIIIlI[74]] = lllIIIll("UcDT2PZ8Tb2+a/Phhn4bFA==", "oYASC");
        IRC.lIllIIl[IRC.llIIIlI[75]] = lllIIIll("6X4PY1/DRcI=", "dAemR");
        IRC.lIllIIl[IRC.llIIIlI[76]] = lllIIIIl("nWLUPT/q4oY=", "EROzD");
        IRC.lIllIIl[IRC.llIIIlI[77]] = lllIIIIl("+jweqFlWVAlOd/MCvtLAEA==", "gMQKr");
        IRC.lIllIIl[IRC.llIIIlI[78]] = lllIIIll("/tWAyXu5XqE=", "lcVdp");
        IRC.lIllIIl[IRC.llIIIlI[79]] = lllIIIIl("SQcntwRIpfM=", "Rkfgq");
        IRC.lIllIIl[IRC.llIIIlI[80]] = lllIIIll("2Lq1FJcozmDl0tB4INSstZ3FSUNcW35dLcBAkbJB04OA0OITHGiyTgqBDqy/F708Pwvaxcqhu/blA//7mZOXECJXvm+7TWExrpg3iEzcyTbhqGYXY3mWvA==", "gOYrn");
        IRC.lIllIIl[IRC.llIIIlI[81]] = lllIIIIl("5fMXwbVn9fOCGEv3Il7tnJ1wsLy4t2G76o4X4QsR187Iocyj3x/9fzdqBNH/jNOD", "SwuGq");
        IRC.lIllIIl[IRC.llIIIlI[82]] = lllIIIIl("TkH/1riS5Ov+hhCqyz7oFHt+AsQedegSUwlGVg80KFdjm0WC4dZlJBK8c/PB0Jz22Lck7WewPVkfxgSXK271sMxzJKptx0Dr", "sJiIn");
        IRC.lIllIIl[IRC.llIIIlI[83]] = lllIIIlI("GikRMlgFPA4/WCUdLhdMBCc0JwQZJgBpXlkEDTIAEWcLMhgXZzQnBBkmAGhMUGg=", "pHgSv");
        IRC.lIllIIl[IRC.llIIIlI[84]] = lllIIIIl("czKYS9U4+QvUt7OjRcdf41jHfNl77qxqshbZf6ng2lI+mzvJtO/7Nu1vm2II8i1B", "dwuxC");
        IRC.lIllIIl[IRC.llIIIlI[85]] = lllIIIll("ukykeUaCaaQPRNwse6OfsjNI5mDF9kJCfNKDuoJkOsowldVen2Dbdgi2IJvayKFU0hGpCTheQ+U=", "nQKxL");
        IRC.lIllIIl[IRC.llIIIlI[86]] = lllIIIlI("DAINVCwLCRwZMwMBDVQiDg4cFDVMKhAUJAEVGBw1WAEQHy0GOE5LdVZWJh97W11ZWmE=", "bgyzA");
        IRC.lIllIIl[IRC.llIIIlI[87]] = lllIIIIl("S8BLaBn1P20MHhhOmXNGHj5X8UJ0sHNtJI8BPHBcoAI=", "PqKuy");
        IRC.lIllIIl[IRC.llIIIlI[88]] = lllIIIll("ZVshHEQlq97bRSfStEKMk6VAaK3IZgj7HHV79HVTfzX13Pf7HT6xkEKOp6e3zlmEUEDKZ1wtIRM=", "xMYYa");
        IRC.lIllIIl[IRC.llIIIlI[89]] = lllIIIIl("iDTn2l5gNECn77rM6R3ixdvWVDtcXl7LTIFStF2lKDiiTksNeeMu/ko0dTd+E9tcF8ZlOtH/9G8=", "sEYgP");
        IRC.lIllIIl[IRC.llIIIlI[90]] = lllIIIIl("5s3kLaYpNCMshd8xpiEALiJ5pnJgMJJycl4r2SLjkLptCUH0sE9BuQ==", "oklfF");
        IRC.lIllIIl[IRC.llIIIlI[91]] = lllIIIlI("AB8TQBUdCgYKIVYlBQc9FhJTByobMgEcPRkCU1piWEZJ", "xfinX");
        IRC.lIllIIl[IRC.llIIIlI[92]] = lllIIIll("t9LZhnbYmpzSVif2slDezsiuspCL7pxyFUucbkD/DJeVSrxxMm1rKg==", "ALsPV");
        IRC.lIllIIl[IRC.llIIIlI[93]] = lllIIIll("nk35mv/O93Q1JHARy8mgiO1B6Yu2sXZEAlmYxT2/mK1Jv3JS1w4TKoezDfoPYuLMBtOJ6HzNZgI=", "JLqMT");
        IRC.lIllIIl[IRC.llIIIlI[94]] = lllIIIlI("MBweVCctCQseE2YmCBMPJhFeGx88DSkbBCkCAQhQcF9EWko=", "Hedzj");
        IRC.lIllIIl[IRC.llIIIlI[95]] = lllIIIll("cqYV1jJ8dmX9FMlBtO168Iwhnl1ZXaqwOcMtrDjITxIgsjiypGnp5ktVaqS4W/3z", "JsQDP");
        IRC.lIllIIl[IRC.llIIIlI[96]] = lllIIIIl("BVtMkUSzP60XFMqJwu/wOmDxIWjmYydcnutWDY1Vdeo=", "hLPAi");
        IRC.lIllIIl[IRC.llIIIlI[97]] = lllIIIll("emzQRHrut5SUJIs6I4BoaxwF6NhsP2FKJ4M3DRKqcnPhXp8aikKU/nOG3tLXG5dbAZ52ZPFeNM93W/hbPe2h8Q==", "iVvxO");
        IRC.lIllIIl[IRC.llIIIlI[98]] = lllIIIIl("t0qOMgn1zKzQvsAvSucWdb4HtiF/dHPjNe0IxXpuDgn+aZtRyQ7DtmWb6vZfPk9VWPrC/Gx3ax7zeKvayX85W6w+tFnvkoLh", "LSwss");
        IRC.lIllIIl[IRC.llIIIlI[99]] = lllIIIlI("ExE1dwYOBCA9MkU7Nio/DgVhFC4HBysgZSgALi1lIjoMYzgDBzo1Lz8APTwqDzs7NjtRWXV5a0s=", "khOYK");
        IRC.lIllIIl[IRC.llIIIlI[100]] = lllIIIlI("DRoSeB8QDwcyK1swESUmEA5GGzcZDAwvfDQWHD43GxcBNTMBCgc4fDQWHD4fFA0JMTcHWQ8zJiAQDSRoXUokLisPTCUzPhoHEXkBDBAcMz9aLg06PREaRxcnAQsNOCYcAAkiOxoNRwMhEBEnNDhOWUh2", "uchVR");
        IRC.lIllIIl[IRC.llIIIlI[101]] = lllIIIll("rXroQiCYDwqK7fP86Q7PwYU67iUJzu43bigCQxxmndRBSfc60sB4cwiaB6I8lvYnmMJoaXzHPNYWw5yGiuwEsywOpli95j4Z", "cwqDO");
        IRC.lIllIIl[IRC.llIIIlI[102]] = lllIIIll("1bMp6AO5k+GzlHYTNPN5wPXNI3UTbi7JT0sN5WlnAE9nKsF91wwEzPbj6L5994d5", "YtWhx");
        IRC.lIllIIl[IRC.llIIIlI[103]] = lllIIIlI("HQgebTwAHQsnCEsiHTAFABxKDhQJHgA6XyYZBTdfLCMneRgLS1F5UUVRRA==", "eqdCq");
        IRC.lIllIIl[IRC.llIIIlI[104]] = lllIIIlI("Cg8zViAXGiYcFFwlMAsZFxtnNQgeGS0BQzEeKAxDOyQKQh4dFSIdGUhHeEJNUlZp", "rvIxm");
        IRC.lIllIIl[IRC.llIIIlI[105]] = lllIIIIl("Zrh4fvJy076eGQkU0CKd8LfeGiqb2eVyxmUPamBkYzLEHB5iZf3Q2CYG2nk38f8poMIlAU5izMOIHPbbCBhm6Q==", "mRooy");
        IRC.lIllIIl[IRC.llIIIlI[106]] = lllIIIlI("BBgDEEMAHAFfPgEaHhQZVB4QBSQACQAFPhoLEBAAVFFcPQcPDxReBAFWPB8dGw0mBR8LGBhKV05Z", "nyuqm");
        IRC.lIllIIl[IRC.llIIIlI[107]] = lllIIIIl("2IL4eRCJfIVBWaojfwXNOkVzhRiv/eL8h0Tniec8II88g9fFAZWrkCKEw2BNNfsOpDgUx79lEAXmTB/1ODGeFA==", "EUUkU");
        IRC.lIllIIl[IRC.llIIIlI[108]] = lllIIIll("suhHWVn27TIpBJVMGGNKunBOU0bne5UhXfZJe8/jp+HBqEL7zR2JTY9fncn6lT9aEwtPv9ufIZSWdZg3pp/k+AuNAAmrVpNWteqk2OtZWUGoXOGQ6E9/4g==", "eBTyt");
        IRC.lIllIIl[IRC.llIIIlI[109]] = lllIIIlI("HQ0/WTkAGCoTDUs3KR4RCwB/HgYGMT0SBBEdKhlOVE5lV1RFVA==", "etEwt");
        IRC.lIllIIl[IRC.llIIIlI[110]] = lllIIIIl("+RY5NuAyWpegb7T40Q3m0tWij+NfdCq8bhbjL8FzDf7/TASP+z002VOyRzK8R3ESikx+wqf3j+Y=", "RfseU");
        IRC.lIllIIl[IRC.llIIIlI[111]] = lllIIIIl("Ae1rDWFYkgIyxE78lDZg55/S2RzyyVzRZjMdxoTU1tzg8nHqQWIWLWLxLHiuPWu8", "PPCaZ");
        IRC.lIllIIl[IRC.llIIIlI[112]] = lllIIIIl("cgPqssdS//gOpAR3LZlzutggXDDn7HGmgEMV7dbm0/bVHb6pWs5UlNQEjH4a0QJFB+XnOWV8xRitVfrgl0BIoA==", "PcHPk");
        IRC.lIllIIl[IRC.llIIIlI[113]] = lllIIIll("90H5eKOQ/UVHs3LqfkhiVw6PkgP15JcwSPbc8mpxJJC0E6wxQ8yXfpriNqFCZ9Xo+VQ56NSKf+ZfUjx++9QzMA8R8iaR378HevprpZx+J8XXGDthGiXyBg==", "tunWz");
        IRC.lIllIIl[IRC.llIIIlI[114]] = lllIIIll("EYvge+DyNqwia8QoHfk+82GdCQVWCVq0bnH0f3a8gVdBIoXOXCdEb5gg4YFq6aRPH0WMIIqIuPg=", "AMCqZ");
        IRC.lIllIIl[IRC.llIIIlI[115]] = lllIIIlI("LCs1LUUqKy0rRRU+MSUFIXAgIwUyKyoiGHxiDyYKMCtsIAooLWwPAyc4ECkaMy8tLw59Yxl2S2Y=", "FJCLk");
        IRC.lIllIIl[IRC.llIIIlI[116]] = lllIIIIl("okQ5itgtglwb66aGbJbsq5bBZDIn4gGh1zf0vEqCKxzyCr9Vc9cS6/WKTR0SAiSWpp96fHKf8fZxlr3bIKyKNr4jEMPUsSrA7mYdTfWmy3Y=", "UiCAD");
        IRC.lIllIIl[IRC.llIIIlI[117]] = lllIIIlI("LzUME3wpNRQVfAAsGRciMT0VHGg1JhMcJhYgGxE5ESYbETd/fFMkaGV0", "ETzrR");
        IRC.lIllIIl[IRC.llIIIlI[118]] = lllIIIll("Lwr/zLPod8lsuG08J6HiGKbRNTgBIRcgbwIKOxsBeoLuBp+MT0yKlpEu/vuGijz1+gnQEOGVhCllL9InmSwnjw==", "ABPDp");
        IRC.lIllIIl[IRC.llIIIlI[119]] = lllIIIIl("Kps2ZOVw8DUx5kPvcdOwICAQXepRPsZHQkiGM/WGkEIMLq3NwsDtVg==", "QJSbX");
        IRC.lIllIIl[IRC.llIIIlI[120]] = lllIIIlI("JgAkIGogADwmahgJICQlKFshNSU+FWhpbRpbcmE=", "LaRAD");
        IRC.lIllIIl[IRC.llIIIlI[121]] = lllIIIll("OXzCsJnRYVISoJZDGmuzBJHlqV7X8gPPpLF+0aIgJ9BlZUf3kRIFwA==", "bVntN");
        IRC.lIllIIl[IRC.llIIIlI[122]] = lllIIIlI("LxAfazkoGw4mJiATH2shNRwHawckBhgsOy9PDTA6IipacWxzQF8aMHtdQgk+IAMKajggGwxqBzUHAiszek9LZQ==", "AukET");
        IRC.lIllIIl[IRC.llIIIlI[123]] = lllIIIll("hXmG4qPKFN4lWM8BHrdQstG6hozcfuL8bFIautoizAdH/2DgpCP4f8k98gSRRh5vfVycETChzh5pa0hRWNA0Wg==", "wFxVv");
        IRC.lIllIIl[IRC.llIIIlI[124]] = lllIIIlI("OTEYNn86P0AeHhYoDTIhJzkBOWsjIgc5JQAkDzQ6ByIPNDRpeEcBa3Nw", "SPnWQ");
        IRC.lIllIIl[IRC.llIIIlI[125]] = lllIIIIl("Zl0FSH8tSEpnNaK5FkGcJmfLkrYxD+7aqOdKLgRcNVqs7+nspP1a1EdyojR9FVS3TH9Sl9e88DqHZajhsyH3jQ==", "ghZAj");
        IRC.lIllIIl[IRC.llIIIlI[126]] = lllIIIIl("aeygssPN3D18/20PA/JSQ8h3fJ8G0ngDbZv5kG9aY9045NC4f2Fgag==", "lTziV");
        IRC.lIllIIl[IRC.llIIIlI[127]] = lllIIIll("hia3+awzTEwTRbta6JLEbi1u+z0cClNHT5DJRYkvbZ2w39nDpTKsjXMdICpbc3CpDCIg7951cjazy3FX6U48K9J8LpXf873k", "zmNeV");
        IRC.lIllIIl[IRC.llIIIlI[128]] = lllIIIlI("CCIUDW0LLEwlLRI2Fj83ECYDAXkBLw0fJlhrSzp5QmM=", "bCblC");
        IRC.lIllIIl[IRC.llIIIlI[129]] = lllIIIll("eIjSjkd/7CmaSyoOfMpzETZjFXnia98S4/ROSppaFXjK8RYCUHIZ/gRJ1xaWARmfCi2y76n55tM=", "gVHlj");
        IRC.lIllIIl[IRC.llIIIlI[130]] = lllIIIll("gvwPZ3h/ufbxvHPMXxQcXpTM5rwdMQEagYIonEkISUE=", "qrgVc");
        IRC.lIllIIl[IRC.llIIIlI[131]] = lllIIIlI("MTQdVB0sIQgeKWcOCxM1JzldGSU6OQgXAigjDEBhe3dHWnBp", "IMgzP");
        IRC.lIllIIl[IRC.llIIIlI[132]] = lllIIIIl("uitvUreKrzQ7UI4u0saWKVDyJq/uF4uqExxAVbL4yqcYmIIperMlyDntqFgCNyi83bIpJbhfPM/SJQFd4yHdcw==", "nyOOB");
        IRC.lIllIIl[IRC.llIIIlI[133]] = lllIIIlI("MAYHMms2Bh80awkTAzorPSUEOik+AgNpJCoXFD0hYE8yegkwBgcyajYGHzRqCRMDOis9JQQ6KT4CA2h/ekc=", "ZgqSE");
        IRC.lIllIIl[IRC.llIIIlI[134]] = lllIIIlI("PSAubysgNTslH2sMICgKNnczIAsgdxMgCyAMICgKNmMzJBIWPCY3AzcQBHtObA9uYQ==", "EYTAf");
        IRC.lIllIIl[IRC.llIIIlI[135]] = lllIIIIl("Q+sSF74iC/WAdbUL/DStDnWQTBxCVRCekBcnrfu2JjA=", "XxYGi");
        IRC.lIllIIl[IRC.llIIIlI[136]] = lllIIIIl("moaXat2sLo0sHF49pMZsCbQS1cW7qSzrEPtjn9pJIAGthSQrTzYMI3JtSZeo4S3iAPtEsXGNn1Ct2/5BBZ/aQe4fP32GqM7J96yEavyxN1I=", "RJNFN");
        IRC.lIllIIl[IRC.llIIIlI[137]] = lllIIIIl("vwIvdoDmKSfXBzxRDICmTaxw+6kfsb19C6586jo1N8KeZjJNENarRLnmcdibaA7rq+/NCLDLnmIpTi5FPQjLtBgEOxktpGkf", "npiQj");
        IRC.lIllIIl[IRC.llIIIlI[138]] = lllIIIIl("410xzyMMG5yfzjQmKMEJ5eiJPPycDLz/Ppo6eSAxn8UWF+ISuJ16RzBw5ShsG+6B4WWIyySioaw=", "LjwOI");
        IRC.lIllIIl[IRC.llIIIlI[139]] = lllIIIll("zXFiLcx1RP3TpvbYv5djFNWZpxM7u3In2HDFhtfKLMzpXT2jxV4p3BlVnUXCFVls", "QdVDn");
        IRC.lIllIIl[IRC.llIIIlI[140]] = lllIIIlI("HBwjRgEbFzILHhMfI0YPHhAyBhhcHDkcBQYAeS0CBhAjETweGC4NHiEpbQ4ZHBoIWVhHTmNfMxNDfyQCFw14BQUcHDQaDRQNeB0YGxV4IS8aGCMrAx8JOAYJHA1sQTpIWXc=", "ryWhl");
        IRC.lIllIIl[IRC.llIIIlI[141]] = lllIIIIl("DzBrUndZN2VoUQOSksMlIiboqb5qoSdBwCcnH8X/fupP+q/vC8bb+goPbUqukrhIGz7LWj0tTPzYpSx5GGvHfhFCb6SV+boi30hOv7qSkuo=", "mijEl");
        IRC.lIllIIl[IRC.llIIIlI[142]] = lllIIIIl("o/aRsxIBH0/gwzIPLpcPmdq17LW2J8RY/E+1aAvbRwfVPSKtt9UIlI4bxu8AEQ9K2jas5FDF73r2JC8jVIWeSxNGAkcA9arJRraB6TQxkNI=", "RBqKE");
        IRC.lIllIIl[IRC.llIIIlI[143]] = lllIIIll("emHN9RuZr+xEpi5fjy7vCpRte/g0qu8wiVZEzsnRqVoQvcUv9wjSvXo1cEKgY27ryf2eTL1fQR7HJ8uKRBFLHwwCBYIoOhsI0lpWqRw4ZEcCj0I5Krm6Ag==", "UkeQi");
        IRC.llIIIII = null;
    }
    
    public void sendMessage(final String lllIlIIllIIIIll) {
        if (lllllIIl(invokedynamic(25:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Z, invokedynamic(24:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Authentication/AuthManager;, invokedynamic(4:()Lxyz/Melody/Client;))))) {
            if (llllIlll(invokedynamic(33:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))) && llllIlll(invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))) {
            }
            // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[48]])
            // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[49]])
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[50]])
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[51]])
            return;
        }
        if (lllllIII(invokedynamic(2:()Ljava/io/PrintWriter;))) {
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[52]])
            // invokedynamic(12:(Lxyz/Melody/Client;Z)V, invokedynamic(4:()Lxyz/Melody/Client;), IRC.llIIIlI[1])
            return;
        }
    }
    // invokedynamic(32:(Ljava/io/PrintWriter;Ljava/lang/String;)V, invokedynamic(2:()Ljava/io/PrintWriter;), lllIlIIllIIIIll)
    
    private static void lllIllIl() {
        final double lllIlIIlIIlIlII = (double)new Exception().getStackTrace()[IRC.llIIIlI[0]].getFileName();
        IRC.llIIIII = ((String)lllIlIIlIIlIlII).substring(((String)lllIlIIlIIlIlII).indexOf("\u00e4") + IRC.llIIIlI[1], ((String)lllIlIIlIIlIlII).lastIndexOf("\u00fc")).split("\u00f6");
    }
    
    private static boolean lIIIIIIlI(final int lllIlIIIlIlIlll, final int lllIlIIIlIlIllI) {
        return lllIlIIIlIlIlll <= lllIlIIIlIlIllI;
    }
    
    private static boolean lllllIII(final Object lllIlIIIlIlIIlI) {
        return lllIlIIIlIlIIlI == null;
    }
    
    public void connect(final int lllIlIIllIlIIlI, final boolean lllIlIIllIlIIIl) {
        if (lllllIIl(invokedynamic(25:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Z, invokedynamic(24:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Authentication/AuthManager;, invokedynamic(4:()Lxyz/Melody/Client;))))) {
            if (llllIlll(invokedynamic(33:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))) && llllIlll(invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))) {
            }
            // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[28]])
            // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[29]])
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[30]])
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[31]])
            // invokedynamic(36:(Lxyz/Melody/System/Melody/Chat/IRC;)V, this)
            // invokedynamic(1:(Lxyz/Melody/System/Melody/Chat/IRC;Z)V, this, IRC.llIIIlI[1])
            return;
        }
        if (lllllIll(lllIlIIllIlIIIl ? 1 : 0)) {
        }
        // invokedynamic(43:(Ljava/lang/String;)V, IRC.lIllIIl[IRC.llIIIlI[32]])
        // invokedynamic(44:()V)
        try {
            // invokedynamic(45:(Ljava/net/Socket;)V, new Socket(IRC.lIllIIl[IRC.llIIIlI[33]], lllIlIIllIlIIlI))
            // invokedynamic(48:(Ljava/io/InputStream;)V, invokedynamic(47:(Ljava/net/Socket;)Ljava/io/InputStream;, invokedynamic(46:()Ljava/net/Socket;)))
            // invokedynamic(50:(Ljava/io/PrintWriter;)V, new PrintWriter((Writer)new OutputStreamWriter(invokedynamic(49:(Ljava/net/Socket;)Ljava/io/OutputStream;, invokedynamic(46:()Ljava/net/Socket;)), IRC.lIllIIl[IRC.llIIIlI[34]]), (boolean)IRC.llIIIlI[1]))
            // invokedynamic(32:(Ljava/io/PrintWriter;Ljava/lang/String;)V, invokedynamic(2:()Ljava/io/PrintWrit...
            // invokedynamic(1:(Lxyz/Melody/System/Melody/Chat/IRC;Z)V, this, IRC.llIIIlI[0])
            if (llllIlll(invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))) && llllIlll(invokedynamic(33:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))) {
            }
            // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[42]])
            // invokedynamic(38:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[43]])
            // invokedynamic(12:(Lxyz/Melody/Client;Z)V, invokedynamic(4:()Lxyz/Melody/Client;), IRC.llIIIlI[0])
            "".length();
            if (-"  ".length() >= 0) {
                return;
            }
        }
        catch (IOException lllIlIIllIlIlII) {
            if (llllIlll(invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))) && llllIlll(invokedynamic(33:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))) {
            }
            // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[44]])
        }
        // invokedynamic(12:(Lxyz/Melody/Client;Z)V, invokedynamic(4:()Lxyz/Melody/Client;), IRC.llIIIlI[1])
        // invokedynamic(39:(Ljava/io/IOException;)V, lllIlIIllIlIlII)
    }
    
    static {
        llllIllI();
        lllIllIl();
        lllIllII();
        lllIIIII();
    }
    // invokedynamic(63:(Lnet/minecraft/client/Minecraft;)V, invokedynamic(21:()Lnet/minecraft/client/Minecraft;))
    
    private static boolean llllIlll(final Object lllIlIIIlIlIlII) {
        return lllIlIIIlIlIlII != null;
    }
    
    private static boolean llllllll(final int lllIlIIIlIllIll, final int lllIlIIIlIllIlI) {
        return lllIlIIIlIllIll < lllIlIIIlIllIlI;
    }
    
    private static void lllIIIII() {
        (IRC.lIlIlll = new String[IRC.llIIIlI[66]])[IRC.llIIIlI[62]] = IRC.lIllIIl[IRC.llIIIlI[80]];
        IRC.lIlIlll[IRC.llIIIlI[50]] = IRC.lIllIIl[IRC.llIIIlI[81]];
        IRC.lIlIlll[IRC.llIIIlI[42]] = IRC.lIllIIl[IRC.llIIIlI[82]];
        IRC.lIlIlll[IRC.llIIIlI[31]] = IRC.lIllIIl[IRC.llIIIlI[83]];
        IRC.lIlIlll[IRC.llIIIlI[65]] = IRC.lIllIIl[IRC.llIIIlI[84]];
        IRC.lIlIlll[IRC.llIIIlI[20]] = IRC.lIllIIl[IRC.llIIIlI[85]];
        IRC.lIlIlll[IRC.llIIIlI[35]] = IRC.lIllIIl[IRC.llIIIlI[86]];
        IRC.lIlIlll[IRC.llIIIlI[60]] = IRC.lIllIIl[IRC.llIIIlI[87]];
        IRC.lIlIlll[IRC.llIIIlI[44]] = IRC.lIllIIl[IRC.llIIIlI[88]];
        IRC.lIlIlll[IRC.llIIIlI[8]] = IRC.lIllIIl[IRC.llIIIlI[89]];
        IRC.lIlIlll[IRC.llIIIlI[5]] = IRC.lIllIIl[IRC.llIIIlI[90]];
        IRC.lIlIlll[IRC.llIIIlI[7]] = IRC.lIllIIl[IRC.llIIIlI[91]];
        IRC.lIlIlll[IRC.llIIIlI[16]] = IRC.lIllIIl[IRC.llIIIlI[92]];
        IRC.lIlIlll[IRC.llIIIlI[47]] = IRC.lIllIIl[IRC.llIIIlI[93]];
        IRC.lIlIlll[IRC.llIIIlI[25]] = IRC.lIllIIl[IRC.llIIIlI[94]];
        IRC.lIlIlll[IRC.llIIIlI[0]] = IRC.lIllIIl[IRC.llIIIlI[95]];
        IRC.lIlIlll[IRC.llIIIlI[11]] = IRC.lIllIIl[IRC.llIIIlI[96]];
        IRC.lIlIlll[IRC.llIIIlI[30]] = IRC.lIllIIl[IRC.llIIIlI[97]];
        IRC.lIlIlll[IRC.llIIIlI[24]] = IRC.lIllIIl[IRC.llIIIlI[98]];
        IRC.lIlIlll[IRC.llIIIlI[15]] = IRC.lIllIIl[IRC.llIIIlI[99]];
        IRC.lIlIlll[IRC.llIIIlI[53]] = IRC.lIllIIl[IRC.llIIIlI[100]];
        IRC.lIlIlll[IRC.llIIIlI[26]] = IRC.lIllIIl[IRC.llIIIlI[101]];
        IRC.lIlIlll[IRC.llIIIlI[3]] = IRC.lIllIIl[IRC.llIIIlI[102]];
        IRC.lIlIlll[IRC.llIIIlI[10]] = IRC.lIllIIl[IRC.llIIIlI[103]];
        IRC.lIlIlll[IRC.llIIIlI[48]] = IRC.lIllIIl[IRC.llIIIlI[104]];
        IRC.lIlIlll[IRC.llIIIlI[37]] = IRC.lIllIIl[IRC.llIIIlI[105]];
        IRC.lIlIlll[IRC.llIIIlI[49]] = IRC.lIllIIl[IRC.llIIIlI[106]];
        IRC.lIlIlll[IRC.llIIIlI[40]] = IRC.lIllIIl[IRC.llIIIlI[107]];
        IRC.lIlIlll[IRC.llIIIlI[17]] = IRC.lIllIIl[IRC.llIIIlI[108]];
        IRC.lIlIlll[IRC.llIIIlI[13]] = IRC.lIllIIl[IRC.llIIIlI[109]];
        IRC.lIlIlll[IRC.llIIIlI[38]] = IRC.lIllIIl[IRC.llIIIlI[110]];
        IRC.lIlIlll[IRC.llIIIlI[19]] = IRC.lIllIIl[IRC.llIIIlI[111]];
        IRC.lIlIlll[IRC.llIIIlI[1]] = IRC.lIllIIl[IRC.llIIIlI[112]];
        IRC.lIlIlll[IRC.llIIIlI[55]] = IRC.lIllIIl[IRC.llIIIlI[113]];
        IRC.lIlIlll[IRC.llIIIlI[36]] = IRC.lIllIIl[IRC.llIIIlI[114]];
        IRC.lIlIlll[IRC.llIIIlI[63]] = IRC.lIllIIl[IRC.llIIIlI[115]];
        IRC.lIlIlll[IRC.llIIIlI[21]] = IRC.lIllIIl[IRC.llIIIlI[116]];
        IRC.lIlIlll[IRC.llIIIlI[43]] = IRC.lIllIIl[IRC.llIIIlI[117]];
        IRC.lIlIlll[IRC.llIIIlI[51]] = IRC.lIllIIl[IRC.llIIIlI[118]];
        IRC.lIlIlll[IRC.llIIIlI[14]] = IRC.lIllIIl[IRC.llIIIlI[119]];
        IRC.lIlIlll[IRC.llIIIlI[9]] = IRC.lIllIIl[IRC.llIIIlI[120]];
        IRC.lIlIlll[IRC.llIIIlI[58]] = IRC.lIllIIl[IRC.llIIIlI[121]];
        IRC.lIlIlll[IRC.llIIIlI[32]] = IRC.lIllIIl[IRC.llIIIlI[122]];
        IRC.lIlIlll[IRC.llIIIlI[33]] = IRC.lIllIIl[IRC.llIIIlI[123]];
        IRC.lIlIlll[IRC.llIIIlI[41]] = IRC.lIllIIl[IRC.llIIIlI[124]];
        IRC.lIlIlll[IRC.llIIIlI[28]] = IRC.lIllIIl[IRC.llIIIlI[125]];
        IRC.lIlIlll[IRC.llIIIlI[6]] = IRC.lIllIIl[IRC.llIIIlI[126]];
        IRC.lIlIlll[IRC.llIIIlI[45]] = IRC.lIllIIl[IRC.llIIIlI[127]];
        IRC.lIlIlll[IRC.llIIIlI[57]] = IRC.lIllIIl[IRC.llIIIlI[128]];
        IRC.lIlIlll[IRC.llIIIlI[34]] = IRC.lIllIIl[IRC.llIIIlI[129]];
        IRC.lIlIlll[IRC.llIIIlI[59]] = IRC.lIllIIl[IRC.llIIIlI[130]];
        IRC.lIlIlll[IRC.llIIIlI[64]] = IRC.lIllIIl[IRC.llIIIlI[131]];
        IRC.lIlIlll[IRC.llIIIlI[12]] = IRC.lIllIIl[IRC.llIIIlI[132]];
        IRC.lIlIlll[IRC.llIIIlI[61]] = IRC.lIllIIl[IRC.llIIIlI[133]];
        IRC.lIlIlll[IRC.llIIIlI[46]] = IRC.lIllIIl[IRC.llIIIlI[134]];
        IRC.lIlIlll[IRC.llIIIlI[56]] = IRC.lIllIIl[IRC.llIIIlI[135]];
        IRC.lIlIlll[IRC.llIIIlI[29]] = IRC.lIllIIl[IRC.llIIIlI[136]];
        IRC.lIlIlll[IRC.llIIIlI[4]] = IRC.lIllIIl[IRC.llIIIlI[137]];
        IRC.lIlIlll[IRC.llIIIlI[18]] = IRC.lIllIIl[IRC.llIIIlI[138]];
        IRC.lIlIlll[IRC.llIIIlI[52]] = IRC.lIllIIl[IRC.llIIIlI[139]];
        IRC.lIlIlll[IRC.llIIIlI[39]] = IRC.lIllIIl[IRC.llIIIlI[140]];
        IRC.lIlIlll[IRC.llIIIlI[54]] = IRC.lIllIIl[IRC.llIIIlI[141]];
        IRC.lIlIlll[IRC.llIIIlI[23]] = IRC.lIllIIl[IRC.llIIIlI[142]];
        IRC.lIlIlll[IRC.llIIIlI[22]] = IRC.lIllIIl[IRC.llIIIlI[143]];
        (IRC.lIllIII = new Class[IRC.llIIIlI[14]])[IRC.llIIIlI[1]] = Boolean.TYPE;
        IRC.lIllIII[IRC.llIIIlI[11]] = EntityPlayerSP.class;
        IRC.lIllIII[IRC.llIIIlI[4]] = Client.class;
        IRC.lIllIII[IRC.llIIIlI[0]] = TimerUtil.class;
        IRC.lIllIII[IRC.llIIIlI[6]] = InputStream.class;
        IRC.lIllIII[IRC.llIIIlI[8]] = Minecraft.class;
        IRC.lIllIII[IRC.llIIIlI[3]] = PrintWriter.class;
        IRC.lIllIII[IRC.llIIIlI[5]] = Thread.class;
        IRC.lIllIII[IRC.llIIIlI[7]] = Logger.class;
        IRC.lIllIII[IRC.llIIIlI[10]] = WorldClient.class;
        IRC.lIllIII[IRC.llIIIlI[12]] = Socket.class;
        IRC.lIllIII[IRC.llIIIlI[13]] = String.class;
        IRC.lIllIII[IRC.llIIIlI[9]] = AuthManager.class;
    }
    
    public void start(final boolean lllIlIIlllIlIll) {
    }
    // invokedynamic(1:(Lxyz/Melody/System/Melody/Chat/IRC;Z)V, this, IRC.llIIIlI[0])
    // invokedynamic(5:(Lxyz/Melody/Client;Ljava/lang/Thread;)V, invokedynamic(4:()Lxyz/Melody/Client;), new IRCThread(lllIlIIlllIlIll))
    // invokedynamic(7:(Ljava/lang/Thread;Ljava/lang/String;)V, invokedynamic(6:(Lxyz/Melody/Client;)Ljava/lang/Thread;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[1]])
    // invokedynamic(8:(Ljava/lang/Thread;)V, invokedynamic(6:(Lxyz/Melody/Client;)Ljava/lang/Thread;, invokedynamic(4:()Lxyz/Melody/Client;)))
    
    private static String lllIIIlI(String lllIlIIIlllIlll, final String lllIlIIIllllIll) {
        lllIlIIIlllIlll = (Exception)new String(Base64.getDecoder().decode(((String)lllIlIIIlllIlll).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder lllIlIIIllllIlI = new StringBuilder();
        final char[] lllIlIIIllllIIl = lllIlIIIllllIll.toCharArray();
        int lllIlIIIllllIII = IRC.llIIIlI[0];
        final float lllIlIIIlllIIlI = (Object)((String)lllIlIIIlllIlll).toCharArray();
        final int lllIlIIIlllIIIl = lllIlIIIlllIIlI.length;
        short lllIlIIIlllIIII = (short)IRC.llIIIlI[0];
        while (llllllll(lllIlIIIlllIIII, lllIlIIIlllIIIl)) {
            final char lllIlIIIlllllIl = lllIlIIIlllIIlI[lllIlIIIlllIIII];
            lllIlIIIllllIlI.append((char)(lllIlIIIlllllIl ^ lllIlIIIllllIIl[lllIlIIIllllIII % lllIlIIIllllIIl.length]));
            "".length();
            ++lllIlIIIllllIII;
            ++lllIlIIIlllIIII;
            "".length();
            if ("   ".length() <= -" ".length()) {
                return null;
            }
        }
        return String.valueOf(lllIlIIIllllIlI);
    }
    
    public void disconnect() {
        if (llllIlll(invokedynamic(46:()Ljava/net/Socket;)) && llllIlll(invokedynamic(9:()Ljava/io/InputStream;)) && llllIlll(invokedynamic(2:()Ljava/io/PrintWriter;))) {
            // invokedynamic(1:(Lxyz/Melody/System/Melody/Chat/IRC;Z)V, this, IRC.llIIIlI[1])
            try {
                // invokedynamic(3:(Lxyz/Melody/System/Melody/Chat/IRC;Ljava/lang/String;)V, this, IRC.lIllIIl[IRC.llIIIlI[45]])
                // invokedynamic(54:(Ljava/net/Socket;)V, invokedynamic(46:()Ljava/net/Socket;))
                // invokedynamic(55:(Ljava/io/InputStream;)V, invokedynamic(9:()Ljava/io/InputStream;))
                // invokedynamic(56:(Ljava/io/PrintWriter;)V, invokedynamic(2:()Ljava/io/PrintWriter;))
                // invokedynamic(45:(Ljava/net/Socket;)V, null)
                // invokedynamic(48:(Ljava/io/InputStream;)V, null)
                // invokedynamic(50:(Ljava/io/PrintWriter;)V, null)
                if (llllIlll(invokedynamic(33:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))) && llllIlll(invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))) {
                }
                // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[46]])
                "".length();
                if (((0xD ^ 0x20) << " ".length() & ~((0x2E ^ 0x3) << " ".length())) != 0x0) {
                    return;
                }
                return;
            }
            catch (IOException lllIlIIllIIlIlI) {
                // invokedynamic(39:(Ljava/io/IOException;)V, lllIlIIllIIlIlI)
                "".length();
                if (-"   ".length() > 0) {
                    return;
                }
                return;
            }
        }
    }
    // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[47]])
    
    private static boolean lllllIIl(final int lllIlIIIlIIlllI) {
        return lllIlIIIlIIlllI == 0;
    }
    
    private static boolean lIIIIIIll(final int lllIlIIIlIlllll, final int lllIlIIIlIllllI) {
        return lllIlIIIlIlllll == lllIlIIIlIllllI;
    }
    
    private static void llllIllI() {
        (llIIIlI = new int[145])[0] = ((0xFC ^ 0xC3) & ~(0x91 ^ 0xAE));
        IRC.llIIIlI[1] = " ".length();
        IRC.llIIIlI[2] = " ".length() << ((0xB9 ^ 0xBC) << " ".length());
        IRC.llIIIlI[3] = " ".length() << " ".length();
        IRC.llIIIlI[4] = "   ".length();
        IRC.llIIIlI[5] = " ".length() << (" ".length() << " ".length());
        IRC.llIIIlI[6] = ((0x44 ^ 0x13) << " ".length() ^ 18 + 41 + 73 + 39);
        IRC.llIIIlI[7] = "   ".length() << " ".length();
        IRC.llIIIlI[8] = (0xB5 ^ 0xB2);
        IRC.llIIIlI[9] = " ".length() << "   ".length();
        IRC.llIIIlI[10] = ((0x7A ^ 0x69) << (" ".length() << " ".length()) ^ (0xC7 ^ 0x82));
        IRC.llIIIlI[11] = (" ".length() << "   ".length() ^ (0xB8 ^ 0xB5)) << " ".length();
        IRC.llIIIlI[12] = (0x42 ^ 0x49);
        IRC.llIIIlI[13] = "   ".length() << (" ".length() << " ".length());
        IRC.llIIIlI[14] = ("   ".length() << " ".length() ^ (0x39 ^ 0x32));
        IRC.llIIIlI[15] = (97 + 117 - 132 + 81 ^ (0x1B ^ 0x32) << (" ".length() << " ".length())) << " ".length();
        IRC.llIIIlI[16] = (0x35 ^ 0x3A);
        IRC.llIIIlI[17] = " ".length() << (" ".length() << (" ".length() << " ".length()));
        IRC.llIIIlI[18] = (0x6E ^ 0x7F);
        IRC.llIIIlI[19] = (91 + 127 - 91 + 38 ^ (0x63 ^ 0x48) << (" ".length() << " ".length())) << " ".length();
        IRC.llIIIlI[20] = (0x3B ^ 0x28);
        IRC.llIIIlI[21] = (0x94 ^ 0x91) << (" ".length() << " ".length());
        IRC.llIIIlI[22] = (" ".length() << (" ".length() << (" ".length() << " ".length())) ^ (0x51 ^ 0x54));
        IRC.llIIIlI[23] = ((0x26 ^ 0x1) << " ".length() ^ (0xD6 ^ 0x93)) << " ".length();
        IRC.llIIIlI[24] = (0x32 ^ 0x25);
        IRC.llIIIlI[25] = "   ".length() << "   ".length();
        IRC.llIIIlI[26] = (0x4A ^ 0x53);
        IRC.llIIIlI[27] = (2241 + 3608 - 1959 + 119 << (" ".length() << " ".length())) + (8135 + 6237 - 10558 + 4323) - (1142 + 445 - 882 + 480 << " ".length()) + (1209 + 205 - 516 + 983 << " ".length());
        IRC.llIIIlI[28] = (0x87 ^ 0x8A) << " ".length();
        IRC.llIIIlI[29] = (0x5B ^ 0x40);
        IRC.llIIIlI[30] = (0x23 ^ 0x24) << (" ".length() << " ".length());
        IRC.llIIIlI[31] = (0x1F ^ 0x2);
        IRC.llIIIlI[32] = (0xF4 ^ 0xBB ^ " ".length() << ("   ".length() << " ".length())) << " ".length();
        IRC.llIIIlI[33] = (0xB0 ^ 0xAF);
        IRC.llIIIlI[34] = " ".length() << (0x6D ^ 0x68);
        IRC.llIIIlI[35] = (4 + 107 + 37 + 1 ^ (0x2B ^ 0x6) << (" ".length() << " ".length()));
        IRC.llIIIlI[36] = ("   ".length() << (0xA1 ^ 0xA4) ^ (0x52 ^ 0x23)) << " ".length();
        IRC.llIIIlI[37] = (0x58 ^ 0x7B);
        IRC.llIIIlI[38] = ((0x71 ^ 0x74) << (" ".length() << (" ".length() << " ".length())) ^ (0x26 ^ 0x7F)) << (" ".length() << " ".length());
        IRC.llIIIlI[39] = (0xB5 ^ 0x90);
        IRC.llIIIlI[40] = ((0x42 ^ 0x7B) << " ".length() ^ (0x21 ^ 0x40)) << " ".length();
        IRC.llIIIlI[41] = (151 + 148 - 155 + 9 ^ (0x10 ^ 0x4F) << " ".length());
        IRC.llIIIlI[42] = (121 + 170 - 236 + 138 ^ (0x2E ^ 0x1F) << (" ".length() << " ".length())) << "   ".length();
        IRC.llIIIlI[43] = (0x36 ^ 0x1F);
        IRC.llIIIlI[44] = (0x12 ^ 0x25 ^ (0x5A ^ 0x4B) << " ".length()) << " ".length();
        IRC.llIIIlI[45] = ((0x45 ^ 0x1C) << " ".length() ^ 127 + 89 - 115 + 52);
        IRC.llIIIlI[46] = (0x82 ^ 0x89) << (" ".length() << " ".length());
        IRC.llIIIlI[47] = (0x5B ^ 0x76);
        IRC.llIIIlI[48] = (0x68 ^ 0x7F) << " ".length();
        IRC.llIIIlI[49] = ((0x46 ^ 0x53) << (" ".length() << " ".length()) ^ (0x7 ^ 0x7C));
        IRC.llIIIlI[50] = "   ".length() << (" ".length() << (" ".length() << " ".length()));
        IRC.llIIIlI[51] = (0x16 ^ 0x51 ^ (0x7 ^ 0x3C) << " ".length());
        IRC.llIIIlI[52] = (0x14 ^ 0x4B ^ (0xAD ^ 0x8E) << " ".length()) << " ".length();
        IRC.llIIIlI[53] = ((0x91 ^ 0x96) << "   ".length() ^ (0x2A ^ 0x21));
        IRC.llIIIlI[54] = (0x4C ^ 0x41) << (" ".length() << " ".length());
        IRC.llIIIlI[55] = ("   ".length() << (" ".length() << (" ".length() << " ".length())) ^ (0x84 ^ 0x81));
        IRC.llIIIlI[56] = (0x7F ^ 0x64) << " ".length();
        IRC.llIIIlI[57] = ((0xD8 ^ 0xC1) << (" ".length() << " ".length()) ^ (0xE3 ^ 0xB0));
        IRC.llIIIlI[58] = (95 + 89 - 148 + 91 ^ (0x2D ^ 0x22) << "   ".length()) << "   ".length();
        IRC.llIIIlI[59] = (0x9C ^ 0xA5);
        IRC.llIIIlI[60] = ((0x52 ^ 0x11) << " ".length() ^ 65 + 21 + 53 + 16) << " ".length();
        IRC.llIIIlI[61] = (0xA5 ^ 0x9E);
        IRC.llIIIlI[62] = (0xA ^ 0x5) << (" ".length() << " ".length());
        IRC.llIIIlI[63] = (0xB5 ^ 0x88);
        IRC.llIIIlI[64] = (0x4B ^ 0x54) << " ".length();
        IRC.llIIIlI[65] = (0xA ^ 0x35);
        IRC.llIIIlI[66] = " ".length() << ("   ".length() << " ".length());
        IRC.llIIIlI[67] = ((0xBD ^ 0xC0) << " ".length() ^ 11 + 64 + 17 + 95);
        IRC.llIIIlI[68] = (0x41 ^ 0xC ^ (0x20 ^ 0x3B) << (" ".length() << " ".length())) << " ".length();
        IRC.llIIIlI[69] = (0x3C ^ 0x7F);
        IRC.llIIIlI[70] = ((0xA1 ^ 0xAA) << (" ".length() << (" ".length() << " ".length())) ^ 86 + 46 - 42 + 71) << (" ".length() << " ".length());
        IRC.llIIIlI[71] = ((0x8 ^ 0x3D) << (" ".length() << " ".length()) ^ 122 + 9 - 112 + 126);
        IRC.llIIIlI[72] = ((0x4 ^ 0x3B) << " ".length() ^ (0xEA ^ 0xB7)) << " ".length();
        IRC.llIIIlI[73] = (0x2D ^ 0x6A);
        IRC.llIIIlI[74] = (0x54 ^ 0x5D) << "   ".length();
        IRC.llIIIlI[75] = (0x17 ^ 0x5E);
        IRC.llIIIlI[76] = (0x4E ^ 0x4B ^ " ".length() << (0x5E ^ 0x5B)) << " ".length();
        IRC.llIIIlI[77] = (0x58 ^ 0x13);
        IRC.llIIIlI[78] = (0x58 ^ 0x4B) << (" ".length() << " ".length());
        IRC.llIIIlI[79] = (0xBE ^ 0x91 ^ (0x57 ^ 0x66) << " ".length());
        IRC.llIIIlI[80] = (0x6B ^ 0x4C) << " ".length();
        IRC.llIIIlI[81] = (" ".length() << (0x2 ^ 0x7) ^ (0x28 ^ 0x47));
        IRC.llIIIlI[82] = ((0xB4 ^ 0x91) << " ".length() ^ (0x46 ^ 0x9)) << (" ".length() << (" ".length() << " ".length()));
        IRC.llIIIlI[83] = (0x6D ^ 0x1A ^ (0x23 ^ 0x30) << " ".length());
        IRC.llIIIlI[84] = (0x1B ^ 0x4C ^ (0xA8 ^ 0x97) << " ".length()) << " ".length();
        IRC.llIIIlI[85] = (0xC9 ^ 0x9A);
        IRC.llIIIlI[86] = (0xA2 ^ 0xB7) << (" ".length() << " ".length());
        IRC.llIIIlI[87] = ((0x58 ^ 0x4B) << " ".length() ^ (0xE3 ^ 0x90));
        IRC.llIIIlI[88] = (0xA3 ^ 0x88) << " ".length();
        IRC.llIIIlI[89] = ((0x63 ^ 0x5A) << " ".length() ^ (0x22 ^ 0x7));
        IRC.llIIIlI[90] = (0x67 ^ 0x6C) << "   ".length();
        IRC.llIIIlI[91] = (0x16 ^ 0x4F);
        IRC.llIIIlI[92] = (69 + 61 - 64 + 83 ^ (0xB4 ^ 0xA3) << "   ".length()) << " ".length();
        IRC.llIIIlI[93] = ((0x5B ^ 0x54) << "   ".length() ^ (0xBE ^ 0x9D));
        IRC.llIIIlI[94] = (0x48 ^ 0x5F) << (" ".length() << " ".length());
        IRC.llIIIlI[95] = ((0x39 ^ 0x36) << (" ".length() << (" ".length() << " ".length())) ^ 170 + 84 - 242 + 161);
        IRC.llIIIlI[96] = (0x8A ^ 0xA5) << " ".length();
        IRC.llIIIlI[97] = (0xE ^ 0x47 ^ (0x50 ^ 0x5B) << " ".length());
        IRC.llIIIlI[98] = "   ".length() << (100 + 85 - 85 + 69 ^ (0x20 ^ 0xB) << (" ".length() << " ".length()));
        IRC.llIIIlI[99] = (0x0 ^ 0x61);
        IRC.llIIIlI[100] = (0x1F ^ 0x2E) << " ".length();
        IRC.llIIIlI[101] = ((0x5F ^ 0x52) << "   ".length() ^ (0xB ^ 0x0));
        IRC.llIIIlI[102] = (0x24 ^ 0x3D ^ ((0x7 ^ 0x2) << (" ".length() << (" ".length() << " ".length())) & ~((0x1B ^ 0x1E) << (" ".length() << (" ".length() << " ".length()))))) << (" ".length() << " ".length());
        IRC.llIIIlI[103] = (0x4B ^ 0x2E);
        IRC.llIIIlI[104] = ((0xBE ^ 0x83) << " ".length() ^ (0x5E ^ 0x17)) << " ".length();
        IRC.llIIIlI[105] = (42 + 149 - 31 + 73 ^ (0x43 ^ 0x4) << " ".length());
        IRC.llIIIlI[106] = (0x43 ^ 0x4E) << "   ".length();
        IRC.llIIIlI[107] = ((0x36 ^ 0x33) << "   ".length() ^ (0x79 ^ 0x38));
        IRC.llIIIlI[108] = (0xF ^ 0x3A) << " ".length();
        IRC.llIIIlI[109] = ((0x87 ^ 0xBE) << (" ".length() << " ".length()) ^ 110 + 87 - 70 + 16);
        IRC.llIIIlI[110] = (0x33 ^ 0x48 ^ "   ".length() << (0x16 ^ 0x13)) << (" ".length() << " ".length());
        IRC.llIIIlI[111] = (244 + 73 - 212 + 140 ^ (0x87 ^ 0x94) << "   ".length());
        IRC.llIIIlI[112] = (0x86 ^ 0xB1) << " ".length();
        IRC.llIIIlI[113] = (0xF0 ^ 0x9F);
        IRC.llIIIlI[114] = (0x64 ^ 0x6B ^ " ".length() << "   ".length()) << (" ".length() << (" ".length() << " ".length()));
        IRC.llIIIlI[115] = (" ".length() << (" ".length() << (" ".length() << " ".length())) ^ (0x49 ^ 0x28));
        IRC.llIIIlI[116] = (0x46 ^ 0x7F) << " ".length();
        IRC.llIIIlI[117] = (0x33 ^ 0x40);
        IRC.llIIIlI[118] = ((0x46 ^ 0x59) << " ".length() ^ (0x9B ^ 0xB8)) << (" ".length() << " ".length());
        IRC.llIIIlI[119] = (0xC8 ^ 0xBD);
        IRC.llIIIlI[120] = (0x41 ^ 0x7A) << " ".length();
        IRC.llIIIlI[121] = (200 + 71 - 175 + 123 ^ (0xBF ^ 0x94) << (" ".length() << " ".length()));
        IRC.llIIIlI[122] = (0x89 ^ 0x86) << "   ".length();
        IRC.llIIIlI[123] = ((0x98 ^ 0xBD) << " ".length() ^ (0xBF ^ 0x8C));
        IRC.llIIIlI[124] = (0x3D ^ 0x60 ^ "   ".length() << (0x7A ^ 0x7F)) << " ".length();
        IRC.llIIIlI[125] = (0x3F ^ 0x44);
        IRC.llIIIlI[126] = ((0x2D ^ 0x38) << (" ".length() << " ".length()) ^ (0x57 ^ 0x1C)) << (" ".length() << " ".length());
        IRC.llIIIlI[127] = (0x3B ^ 0x20 ^ (0xB5 ^ 0x86) << " ".length());
        IRC.llIIIlI[128] = (0x1D ^ 0x22) << " ".length();
        IRC.llIIIlI[129] = ((0xB2 ^ 0xA9) << (" ".length() << " ".length())) + (0x55 ^ 0x14) - (0xB1 ^ 0xC2) + (0xC4 ^ 0x81);
        IRC.llIIIlI[130] = " ".length() << ((0x8D ^ 0x96) << (" ".length() << " ".length()) ^ (0x25 ^ 0x4E));
        IRC.llIIIlI[131] = (0xD6 ^ 0x87) + (0xCC ^ 0xA1) - ((0x6B ^ 0x7C) << (" ".length() << " ".length())) + (0x78 ^ 0x67);
        IRC.llIIIlI[132] = ((0xCE ^ 0xA3) << " ".length() ^ 69 + 142 - 148 + 92) << " ".length();
        IRC.llIIIlI[133] = ((0x3B ^ 0x16) << " ".length()) + (0x73 ^ 0x76) - ((0x48 ^ 0x4F) << (" ".length() << " ".length())) + (" ".length() << ("   ".length() << " ".length()));
        IRC.llIIIlI[134] = (0x7 ^ 0x26) << (" ".length() << " ".length());
        IRC.llIIIlI[135] = (0x17 ^ 0x1A) + (" ".length() << " ".length()) - -(0x0 ^ 0x52) + ((0x81 ^ 0x88) << (" ".length() << " ".length()));
        IRC.llIIIlI[136] = ((0x59 ^ 0x42) << (" ".length() << " ".length()) ^ (0xBF ^ 0x90)) << " ".length();
        IRC.llIIIlI[137] = 125 + 70 - 99 + 39;
        IRC.llIIIlI[138] = (0x78 ^ 0x69) << "   ".length();
        IRC.llIIIlI[139] = (" ".length() << " ".length()) + ((0x74 ^ 0x71) << " ".length()) - -(0x1B ^ 0x25) + (0xBA ^ 0x85);
        IRC.llIIIlI[140] = (0xC2 ^ 0x87) << " ".length();
        IRC.llIIIlI[141] = ((0x9E ^ 0x97) << "   ".length()) + ((0x1B ^ 0xA) << " ".length()) - ((0xBB ^ 0xAC) << (" ".length() << " ".length())) + (0x58 ^ 0x25);
        IRC.llIIIlI[142] = (0x95 ^ 0xB6) << (" ".length() << " ".length());
        IRC.llIIIlI[143] = (0x52 ^ 0x1B) + (0x98 ^ 0xB9) - (" ".length() << " ".length()) + (0x41 ^ 0x64);
        IRC.llIIIlI[144] = ((0x78 ^ 0xD) << " ".length() ^ 76 + 17 - 72 + 152) << " ".length();
    }
    
    private static String lllIIIIl(final String lllIlIIlIIIlIlI, final String lllIlIIlIIIlIll) {
        try {
            final SecretKeySpec lllIlIIlIIIllll = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIlIIlIIIlIll.getBytes(StandardCharsets.UTF_8)), IRC.llIIIlI[9]), "DES");
            final Cipher lllIlIIlIIIlllI = Cipher.getInstance("DES");
            lllIlIIlIIIlllI.init(IRC.llIIIlI[3], lllIlIIlIIIllll);
            return new String(lllIlIIlIIIlllI.doFinal(Base64.getDecoder().decode(lllIlIIlIIIlIlI.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIlIIlIIIllIl) {
            lllIlIIlIIIllIl.printStackTrace();
            return null;
        }
    }
    
    private static String lllIIIll(final String lllIlIIIllIIlIl, final String lllIlIIIllIIllI) {
        try {
            final SecretKeySpec lllIlIIIllIlIlI = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIlIIIllIIllI.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher lllIlIIIllIlIIl = Cipher.getInstance("Blowfish");
            lllIlIIIllIlIIl.init(IRC.llIIIlI[3], lllIlIIIllIlIlI);
            return new String(lllIlIIIllIlIIl.doFinal(Base64.getDecoder().decode(lllIlIIIllIIlIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIlIIIllIlIII) {
            lllIlIIIllIlIII.printStackTrace();
            return null;
        }
    }
    
    private static CallSite llIlllll(final MethodHandles.Lookup lllIlIIlIIlllIl, final String lllIlIIlIIlllII, final MethodType lllIlIIlIIllllI) throws NoSuchMethodException, IllegalAccessException {
        try {
            final String[] lllIlIIlIlIIllI = IRC.lIlIlll[Integer.parseInt(lllIlIIlIIlllII)].split(IRC.lIllIIl[IRC.llIIIlI[79]]);
            final Class<?> lllIlIIlIlIIlIl = Class.forName(lllIlIIlIlIIllI[IRC.llIIIlI[0]]);
            final String lllIlIIlIlIIlII = lllIlIIlIlIIllI[IRC.llIIIlI[1]];
            MethodHandle lllIlIIlIlIIIll = null;
            final int lllIlIIlIlIIIlI = lllIlIIlIlIIllI[IRC.llIIIlI[4]].length();
            if (lIIIIIIlI(lllIlIIlIlIIIlI, IRC.llIIIlI[3])) {
                final MethodType lllIlIIlIlIlIII = MethodType.fromMethodDescriptorString(lllIlIIlIlIIllI[IRC.llIIIlI[3]], IRC.class.getClassLoader());
                if (lIIIIIIll(lllIlIIlIlIIIlI, IRC.llIIIlI[3])) {
                    lllIlIIlIlIIIll = lllIlIIlIIlllIl.findVirtual(lllIlIIlIlIIlIl, lllIlIIlIlIIlII, lllIlIIlIlIlIII);
                    "".length();
                    if (" ".length() << " ".length() < " ".length()) {
                        return null;
                    }
                }
                else {
                    lllIlIIlIlIIIll = lllIlIIlIIlllIl.findStatic(lllIlIIlIlIIlIl, lllIlIIlIlIIlII, lllIlIIlIlIlIII);
                }
                "".length();
                if (null != null) {
                    return null;
                }
            }
            else {
                final Class lllIlIIlIlIIlll = IRC.lIllIII[Integer.parseInt(lllIlIIlIlIIllI[IRC.llIIIlI[3]])];
                if (lIIIIIIll(lllIlIIlIlIIIlI, IRC.llIIIlI[4])) {
                    lllIlIIlIlIIIll = lllIlIIlIIlllIl.findGetter(lllIlIIlIlIIlIl, lllIlIIlIlIIlII, lllIlIIlIlIIlll);
                    "".length();
                    if (" ".length() << " ".length() < 0) {
                        return null;
                    }
                }
                else if (lIIIIIIll(lllIlIIlIlIIIlI, IRC.llIIIlI[5])) {
                    lllIlIIlIlIIIll = lllIlIIlIIlllIl.findStaticGetter(lllIlIIlIlIIlIl, lllIlIIlIlIIlII, lllIlIIlIlIIlll);
                    "".length();
                    if (" ".length() << (" ".length() << " ".length()) != " ".length() << (" ".length() << " ".length())) {
                        return null;
                    }
                }
                else if (lIIIIIIll(lllIlIIlIlIIIlI, IRC.llIIIlI[6])) {
                    lllIlIIlIlIIIll = lllIlIIlIIlllIl.findSetter(lllIlIIlIlIIlIl, lllIlIIlIlIIlII, lllIlIIlIlIIlll);
                    "".length();
                    if (((0x87 ^ 0x8E) << (" ".length() << " ".length()) & ~((0x4 ^ 0xD) << (" ".length() << " ".length()))) < 0) {
                        return null;
                    }
                }
                else {
                    lllIlIIlIlIIIll = lllIlIIlIIlllIl.findStaticSetter(lllIlIIlIlIIlIl, lllIlIIlIlIIlII, lllIlIIlIlIIlll);
                }
            }
            return new ConstantCallSite(lllIlIIlIlIIIll);
        }
        catch (Exception lllIlIIlIlIIIIl) {
            lllIlIIlIlIIIIl.printStackTrace();
            return null;
        }
    }
    
    public void handleInput() {
        final byte[] lllIlIIlllIIIII = new byte[IRC.llIIIlI[2]];
        if (!llllIlll(invokedynamic(2:()Ljava/io/PrintWriter;)) || lllllIII(invokedynamic(9:()Ljava/io/InputStream;))) {
            // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[3]])
            // invokedynamic(12:(Lxyz/Melody/Client;Z)V, invokedynamic(4:()Lxyz/Melody/Client;), IRC.llIIIlI[1])
            return;
        }
        if (!lllllIIl(invokedynamic(13:()Z)) || lllllIll(invokedynamic(14:(Lxyz/Melody/System/Melody/Chat/IRC;)Z, this))) {
            return;
        }
        try {
            final int lllIlIIlllIIllI = invokedynamic(15:(Ljava/io/InputStream;[B)I, invokedynamic(9:()Ljava/io/InputStream;), lllIlIIlllIIIII);
            String lllIlIIlllIIlIl = new String(lllIlIIlllIIIII, IRC.llIIIlI[0], lllIlIIlllIIllI);
            lllIlIIlllIIlIl = invokedynamic(16:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, lllIlIIlllIIlIl, IRC.lIllIIl[IRC.llIIIlI[4]], IRC.lIllIIl[IRC.llIIIlI[5]]);
            lllIlIIlllIIlIl = invokedynamic(16:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, lllIlIIlllIIlIl, IRC.lIllIIl[IRC.llIIIlI[6]], IRC.lIllIIl[IRC.llIIIlI[7]]);
            lllIlIIlllIIlIl = invokedynamic(16:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, lllIlIIlllIIlIl, IRC.lIllIIl[IRC.llIIIlI[8]], IRC.lIllIIl[IRC.llIIIlI[9]]);
            if (lllllIll(invokedynamic(17:(Ljava/lang/String;Ljava/lang/Object;)Z, lllIlIIlllIIlIl, IRC.lIllIIl[IRC.llIIIlI[10]]))) {
                // invokedynamic(19:(Lnet/minecraft/client/Minecraft;)V, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))
                return;
            }
            if (lllllIll(invokedynamic(17:(Ljava/lang/String;Ljava/lang/Object;)Z, lllIlIIlllIIlIl, IRC.lIllIIl[IRC.llIIIlI[11]]))) {
                // invokedynamic(32:(Ljava/io/PrintWriter;Ljava/lang/String;)V, invokedynamic(2:()Ljava/io/PrintWrit...
                return;
            }
            if (lllllIll(invokedynamic(17:(Ljava/lang/String;Ljava/lang/Object;)Z, lllIlIIlllIIlIl, IRC.lIllIIl[IRC.llIIIlI[20]]))) {
                // invokedynamic(12:(Lxyz/Melody/Client;Z)V, invokedynamic(4:()Lxyz/Melody/Client;), IRC.llIIIlI[0])
                return;
            }
            if (lllllIIl(invokedynamic(25:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Z, invokedynamic(24:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Authentication/AuthManager;, invokedynamic(4:()Lxyz/Melody/Client;))))) {
                // invokedynamic(11:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[21]])
                // invokedynamic(3:(Lxyz/Melody/System/Melody/Chat/IRC;Ljava/lang/String;)V, this, IRC.lIllIIl[IRC.llIIIlI[22]])
                if (llllIlll(invokedynamic(33:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))) && llllIlll(invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))) {
                }
                // invokedynamic(35:(Ljava/lang/Object;)V, IRC.lIllIIl[IRC.llIIIlI[23]])
                // invokedynamic(36:(Lxyz/Melody/System/Melody/Chat/IRC;)V, this)
                return;
            }
            if (llllIlll(invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;))) && llllIlll(invokedynamic(33:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)))) {
            }
            // invokedynamic(37:(Lnet/minecraft/client/entity/EntityPlayerSP;Lnet/minecraft/util/IChatComponent;)V, invokedynamic(34:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(18:()Lnet/minecraft/client/Minecraft;)), new ChatComponentText(lllIlIIlllIIlIl))
            // invokedynamic(38:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), invokedynamic(31:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), IRC.lIllIIl[IRC.llIIIlI[24]]), lllIlIIlllIIlIl)))
            "".length();
            if (((0x6D ^ 0x4E) & ~(0x35 ^ 0x16)) != 0x0) {
                return;
            }
        }
        catch (IOException lllIlIIlllIIlII) {
            // invokedynamic(12:(Lxyz/Melody/Client;Z)V, invokedynamic(4:()Lxyz/Melody/Client;), IRC.llIIIlI[1])
            // invokedynamic(38:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[25]])
            // invokedynamic(39:(Ljava/io/IOException;)V, lllIlIIlllIIlII)
            "".length();
            if (" ".length() == " ".length() << " ".length()) {
                return;
            }
        }
        catch (StringIndexOutOfBoundsException lllIlIIlllIIIll) {
            // invokedynamic(12:(Lxyz/Melody/Client;Z)V, invokedynamic(4:()Lxyz/Melody/Client;), IRC.llIIIlI[1])
            // invokedynamic(38:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(10:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(4:()Lxyz/Melody/Client;)), IRC.lIllIIl[IRC.llIIIlI[26]])
            // invokedynamic(40:(Ljava/lang/StringIndexOutOfBoundsException;)V, lllIlIIlllIIIll)
            "".length();
            if (null != null) {
                return;
            }
        }
        catch (Exception lllIlIIlllIIIlI) {
        }
        // invokedynamic(41:(Ljava/lang/Exception;)V, lllIlIIlllIIIlI)
    }
}
