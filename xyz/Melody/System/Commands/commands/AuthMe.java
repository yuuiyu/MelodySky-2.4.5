//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands.commands;

import xyz.Melody.System.Commands.*;
import java.nio.charset.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;
import java.lang.invoke.*;
import xyz.Melody.System.Melody.Authentication.*;
import xyz.Melody.*;
import net.minecraft.client.*;
import xyz.Melody.module.*;

public class AuthMe extends Command
{
    private static /* synthetic */ String[] lllIlIl;
    private static final /* synthetic */ int[] llllllI;
    private static final /* synthetic */ String[] lllIlll;
    private static /* synthetic */ Class[] lllIllI;
    private static /* synthetic */ String[] llllIII;
    
    private static boolean lIIlllIll(final int lllIIIllIIIlllI) {
        return lllIIIllIIIlllI == 0;
    }
    
    private static String lIIlIllII(final String lllIIIlllIIIllI, final String lllIIIlllIIIlIl) {
        try {
            final SecretKeySpec lllIIIlllIIlIll = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIIIlllIIIlIl.getBytes(StandardCharsets.UTF_8)), AuthMe.llllllI[8]), "DES");
            final Cipher lllIIIlllIIlIlI = Cipher.getInstance("DES");
            lllIIIlllIIlIlI.init(AuthMe.llllllI[1], lllIIIlllIIlIll);
            return new String(lllIIIlllIIlIlI.doFinal(Base64.getDecoder().decode(lllIIIlllIIIllI.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIIIlllIIlIIl) {
            lllIIIlllIIlIIl.printStackTrace();
            return null;
        }
    }
    
    private static boolean lIIlllIIl(final int lllIIIllIIlIIII) {
        return lllIIIllIIlIIII != 0;
    }
    
    static {
        lIIllIlll();
        lIIlIllll();
        lIIlIlllI();
        lIIlIlIlI();
    }
    
    private static void lIIllIlll() {
        (llllllI = new int[27])[0] = ((0x2B ^ 0x24) << " ".length() & ~((0x7F ^ 0x70) << " ".length()));
        AuthMe.llllllI[1] = " ".length() << " ".length();
        AuthMe.llllllI[2] = " ".length();
        AuthMe.llllllI[3] = "   ".length();
        AuthMe.llllllI[4] = " ".length() << (" ".length() << " ".length());
        AuthMe.llllllI[5] = (0x57 ^ 0x52);
        AuthMe.llllllI[6] = "   ".length() << " ".length();
        AuthMe.llllllI[7] = (102 + 139 - 200 + 118 ^ (0xD3 ^ 0xC0) << "   ".length());
        AuthMe.llllllI[8] = " ".length() << "   ".length();
        AuthMe.llllllI[9] = (0x2B ^ 0x3A);
        AuthMe.llllllI[10] = (0x7D ^ 0x78) << " ".length();
        AuthMe.llllllI[11] = (0x89 ^ 0x80);
        AuthMe.llllllI[12] = (181 + 75 - 184 + 117 ^ (0x5C ^ 0x7) << " ".length());
        AuthMe.llllllI[13] = "   ".length() << (" ".length() << " ".length());
        AuthMe.llllllI[14] = (0x91 ^ 0x9C);
        AuthMe.llllllI[15] = " ".length() << (" ".length() << (" ".length() << " ".length()));
        AuthMe.llllllI[16] = ((0x67 ^ 0x72) << (" ".length() << " ".length()) ^ (0x92 ^ 0xC1)) << " ".length();
        AuthMe.llllllI[17] = (0x30 ^ 0x15 ^ (0x4E ^ 0x5B) << " ".length());
        AuthMe.llllllI[18] = ((0x2F ^ 0xE) << (" ".length() << " ".length()) ^ 0 + 77 - 74 + 138) << " ".length();
        AuthMe.llllllI[19] = (0x4F ^ 0x5C);
        AuthMe.llllllI[20] = (0x4A ^ 0x4F) << (" ".length() << " ".length());
        AuthMe.llllllI[21] = (0xF ^ 0x1A);
        AuthMe.llllllI[22] = (0x1F ^ 0x14) << " ".length();
        AuthMe.llllllI[23] = (0x99 ^ 0x8E);
        AuthMe.llllllI[24] = "   ".length() << "   ".length();
        AuthMe.llllllI[25] = (0x3F ^ 0x26);
        AuthMe.llllllI[26] = (0x75 ^ 0x78) << " ".length();
    }
    
    private static boolean lIIllllll(final int lllIIIllIIlIlll, final int lllIIIllIIlIllI) {
        return lllIIIllIIlIlll < lllIIIllIIlIllI;
    }
    
    private static String lIIlIlIll(final String lllIIIllIlllIIl, final String lllIIIllIlllIII) {
        try {
            final SecretKeySpec lllIIIllIlllllI = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIIIllIlllIII.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher lllIIIllIllllIl = Cipher.getInstance("Blowfish");
            lllIIIllIllllIl.init(AuthMe.llllllI[1], lllIIIllIlllllI);
            return new String(lllIIIllIllllIl.doFinal(Base64.getDecoder().decode(lllIIIllIlllIIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIIIllIllllII) {
            lllIIIllIllllII.printStackTrace();
            return null;
        }
    }
    
    private static void lIIlIllll() {
        final char lllIIIlllIlIIII = (char)new Exception().getStackTrace()[AuthMe.llllllI[0]].getFileName();
        AuthMe.llllIII = ((String)lllIIIlllIlIIII).substring(((String)lllIIIlllIlIIII).indexOf("\u00e4") + AuthMe.llllllI[2], ((String)lllIIIlllIlIIII).lastIndexOf("\u00fc")).split("\u00f6");
    }
    
    private static CallSite lIIlIlIIl(final MethodHandles.Lookup lllIIIlllIllIIl, final String lllIIIlllIllIll, final MethodType lllIIIlllIllIlI) throws IllegalAccessException, NoSuchMethodException {
        try {
            final String[] lllIIIllllIIIlI = AuthMe.lllIlIl[Integer.parseInt(lllIIIlllIllIll)].split(AuthMe.lllIlll[AuthMe.llllllI[8]]);
            final Class<?> lllIIIllllIIIIl = Class.forName(lllIIIllllIIIlI[AuthMe.llllllI[0]]);
            final String lllIIIllllIIIII = lllIIIllllIIIlI[AuthMe.llllllI[2]];
            MethodHandle lllIIIlllIlllll = null;
            final int lllIIIlllIllllI = lllIIIllllIIIlI[AuthMe.llllllI[3]].length();
            if (lIIllllIl(lllIIIlllIllllI, AuthMe.llllllI[1])) {
                final MethodType lllIIIllllIIlII = MethodType.fromMethodDescriptorString(lllIIIllllIIIlI[AuthMe.llllllI[1]], AuthMe.class.getClassLoader());
                if (lIIlllllI(lllIIIlllIllllI, AuthMe.llllllI[1])) {
                    lllIIIlllIlllll = lllIIIlllIllIIl.findVirtual(lllIIIllllIIIIl, lllIIIllllIIIII, lllIIIllllIIlII);
                    "".length();
                    if (" ".length() << (" ".length() << " ".length()) > " ".length() << (" ".length() << " ".length())) {
                        return null;
                    }
                }
                else {
                    lllIIIlllIlllll = lllIIIlllIllIIl.findStatic(lllIIIllllIIIIl, lllIIIllllIIIII, lllIIIllllIIlII);
                }
                "".length();
                if (-(0x9 ^ 0xD) >= 0) {
                    return null;
                }
            }
            else {
                final Class lllIIIllllIIIll = AuthMe.lllIllI[Integer.parseInt(lllIIIllllIIIlI[AuthMe.llllllI[1]])];
                if (lIIlllllI(lllIIIlllIllllI, AuthMe.llllllI[3])) {
                    lllIIIlllIlllll = lllIIIlllIllIIl.findGetter(lllIIIllllIIIIl, lllIIIllllIIIII, lllIIIllllIIIll);
                    "".length();
                    if ("   ".length() == 0) {
                        return null;
                    }
                }
                else if (lIIlllllI(lllIIIlllIllllI, AuthMe.llllllI[4])) {
                    lllIIIlllIlllll = lllIIIlllIllIIl.findStaticGetter(lllIIIllllIIIIl, lllIIIllllIIIII, lllIIIllllIIIll);
                    "".length();
                    if (" ".length() << " ".length() <= 0) {
                        return null;
                    }
                }
                else if (lIIlllllI(lllIIIlllIllllI, AuthMe.llllllI[5])) {
                    lllIIIlllIlllll = lllIIIlllIllIIl.findSetter(lllIIIllllIIIIl, lllIIIllllIIIII, lllIIIllllIIIll);
                    "".length();
                    if ((0x4F ^ 0x4A) == 0x0) {
                        return null;
                    }
                }
                else {
                    lllIIIlllIlllll = lllIIIlllIllIIl.findStaticSetter(lllIIIllllIIIIl, lllIIIllllIIIII, lllIIIllllIIIll);
                }
            }
            return new ConstantCallSite(lllIIIlllIlllll);
        }
        catch (Exception lllIIIlllIlllIl) {
            lllIIIlllIlllIl.printStackTrace();
            return null;
        }
    }
    
    private static boolean lIIlllllI(final int lllIIIllIIllIll, final int lllIIIllIIllIlI) {
        return lllIIIllIIllIll == lllIIIllIIllIlI;
    }
    
    private static boolean lIIllllIl(final int lllIIIllIIlIIll, final int lllIIIllIIlIIlI) {
        return lllIIIllIIlIIll <= lllIIIllIIlIIlI;
    }
    
    public AuthMe() {
        final String s = AuthMe.lllIlll[AuthMe.llllllI[0]];
        final String[] array = new String[AuthMe.llllllI[1]];
        array[AuthMe.llllllI[0]] = AuthMe.lllIlll[AuthMe.llllllI[2]];
        array[AuthMe.llllllI[2]] = AuthMe.lllIlll[AuthMe.llllllI[1]];
        super(s, array, AuthMe.lllIlll[AuthMe.llllllI[3]], AuthMe.lllIlll[AuthMe.llllllI[4]]);
    }
    
    private static void lIIlIlllI() {
        (lllIlll = new String[AuthMe.llllllI[26]])[AuthMe.llllllI[0]] = lIIlIlIll(AuthMe.llllIII[AuthMe.llllllI[0]], AuthMe.llllIII[AuthMe.llllllI[2]]);
        AuthMe.lllIlll[AuthMe.llllllI[2]] = lIIlIllII(AuthMe.llllIII[AuthMe.llllllI[1]], AuthMe.llllIII[AuthMe.llllllI[3]]);
        AuthMe.lllIlll[AuthMe.llllllI[1]] = lIIlIllII(AuthMe.llllIII[AuthMe.llllllI[4]], AuthMe.llllIII[AuthMe.llllllI[5]]);
        AuthMe.lllIlll[AuthMe.llllllI[3]] = lIIlIllII(AuthMe.llllIII[AuthMe.llllllI[6]], AuthMe.llllIII[AuthMe.llllllI[7]]);
        AuthMe.lllIlll[AuthMe.llllllI[4]] = lIIlIllII(AuthMe.llllIII[AuthMe.llllllI[8]], AuthMe.llllIII[AuthMe.llllllI[11]]);
        AuthMe.lllIlll[AuthMe.llllllI[5]] = lIIlIlIll(AuthMe.llllIII[AuthMe.llllllI[10]], AuthMe.llllIII[AuthMe.llllllI[12]]);
        AuthMe.lllIlll[AuthMe.llllllI[6]] = lIIlIllIl(AuthMe.llllIII[AuthMe.llllllI[13]], AuthMe.llllIII[AuthMe.llllllI[14]]);
        AuthMe.lllIlll[AuthMe.llllllI[7]] = lIIlIlIll(AuthMe.llllIII[AuthMe.llllllI[16]], AuthMe.llllIII[AuthMe.llllllI[17]]);
        AuthMe.lllIlll[AuthMe.llllllI[8]] = lIIlIlIll(AuthMe.llllIII[AuthMe.llllllI[15]], AuthMe.llllIII[AuthMe.llllllI[9]]);
        AuthMe.lllIlll[AuthMe.llllllI[11]] = lIIlIllII(AuthMe.llllIII[AuthMe.llllllI[18]], AuthMe.llllIII[AuthMe.llllllI[19]]);
        AuthMe.lllIlll[AuthMe.llllllI[10]] = lIIlIlIll("obKaSQUiYoss8NYbDPzqRHILdE/xmF5eaimHsmDNu8ymtYOJ807EwTBft5IxyDfpCh8gfoYemdhp/JRRGDvr7iR66vBiDJsq6/UEHV/Smzo=", "SQWsa");
        AuthMe.lllIlll[AuthMe.llllllI[12]] = lIIlIlIll("QRlZdabcLI5nJyvsgH+jDGwcSB3Os1slvvQGAwtWJyBjjfBxiNpWuWHina9IS4mcfXbkArIwN2sD17X5PvAQm8x5eSkou3zMDyOLLxSpNno=", "NpOwZ");
        AuthMe.lllIlll[AuthMe.llllllI[13]] = lIIlIllII("bMefooQbkLmq9HYfpO1C9JY+vBCxSopwhLWBR2azKnUVKK+88WQibwv0lfJK4MrvxhggTGXe20EUUyKXeqEfR+yE9YWAxEUw", "XmTqi");
        AuthMe.lllIlll[AuthMe.llllllI[14]] = lIIlIllIl("PSofYyc6IQ4uODIpH2MpPyYOIz59AgIjLzA9Cis+aSkeIykMflp9fmB9NARwe2YnIy8nYAYkJDYsGSwsJ2AeOSM/YDgoOSAmBCNxaW9L", "SOkMJ");
        AuthMe.lllIlll[AuthMe.llllllI[16]] = lIIlIllII("asAjsJFEh1PJ9ZWZG7uCFQMDjXebc0+++OTWHmuiq+fZ1VytFHtSYT5EKtrA4dNj", "qdBTK");
        AuthMe.lllIlll[AuthMe.llllllI[17]] = lIIlIlIll("6CemZY2gNfpyN4JS3CU5qeF8xeDKhVgZI+HCru1w87jndrPK36E8ow==", "FLQzy");
        AuthMe.lllIlll[AuthMe.llllllI[15]] = lIIlIllII("eL4CxT2Le9SCcdfu7B7sD41+hOLNX/m9axqGUAh+Lvzk+yyLgV/9Df4MbebHMSjK5OX/ObE3YJg=", "neQKo");
        AuthMe.lllIlll[AuthMe.llllllI[9]] = lIIlIllIl("NxUibSQqADcnEGE5LCoFPEIQJgU/CSp5GioCPA4MPB85JAx1RBQpCDkNdy8IIQt3DAslCTs3UmY6YmM=", "OlXCi");
        AuthMe.lllIlll[AuthMe.llllllI[18]] = lIIlIllII("wTkFKVb4ltS27+/sS8KN4fs7//K4DjA4/zgbxXj3BBuMsCfYg9hM6g==", "gRBqJ");
        AuthMe.lllIlll[AuthMe.llllllI[19]] = lIIlIlIll("zcu3cEbH5fie3v96SHRscjcZ7lxfG9d+wf2obzEfbXJBxc5QfrxnatMYHFiCQzVM", "Dhtxt");
        AuthMe.lllIlll[AuthMe.llllllI[20]] = lIIlIllII("lgVlkpcNYnKf0D32LoOnzAkCSuJc5gX/Et6f2FCWaYBJakeHc/aBIGXO/p9uKVJM", "Igaey");
        AuthMe.lllIlll[AuthMe.llllllI[21]] = lIIlIllII("WsrBUjpv36OFhKzqWXecX+ckwWHyVAajbyyTdBIbnXkMp64enBYD1Q==", "paVpy");
        AuthMe.lllIlll[AuthMe.llllllI[22]] = lIIlIllIl("CBQ7SjoVAS4ADl4+OBcDFQBvJxgdACAKEwNDIgsaHQwvAAReLDQQHz0IewkUSl57RFdQ", "pmAdw");
        AuthMe.lllIlll[AuthMe.llllllI[23]] = lIIlIllIl("Ow4DQRw3Cw8BFnYAGxsZNAgMQTY5DAs/AzcHBwMUYgYLGzg8W0ZGPTIAGA5eLRUHA14NNCcrSmJBTg==", "Xanoq");
        AuthMe.lllIlll[AuthMe.llllllI[24]] = lIIlIllII("SRG2huLDFsc2xnKmdpnalcXgGe3hpBrHN+8dQUfb6XNudk/SWLAfYAPsaGFEUC1Jm+Dn1oBJ894UuO5UzWhvyJC5b/zXo+U2", "hgsGX");
        AuthMe.lllIlll[AuthMe.llllllI[25]] = lIIlIllIl("Hy8/In8AOiAvfzw6LDEwASE7eT8QNj15eVwCIyInFGElIj8SYQYhOxAtPXhrVW4=", "uNICQ");
        AuthMe.llllIII = null;
    }
    
    private static void lIIlIlIlI() {
        (AuthMe.lllIlIl = new String[AuthMe.llllllI[9]])[AuthMe.llllllI[10]] = AuthMe.lllIlll[AuthMe.llllllI[11]];
        AuthMe.lllIlIl[AuthMe.llllllI[12]] = AuthMe.lllIlll[AuthMe.llllllI[10]];
        AuthMe.lllIlIl[AuthMe.llllllI[6]] = AuthMe.lllIlll[AuthMe.llllllI[12]];
        AuthMe.lllIlIl[AuthMe.llllllI[1]] = AuthMe.lllIlll[AuthMe.llllllI[13]];
        AuthMe.lllIlIl[AuthMe.llllllI[5]] = AuthMe.lllIlll[AuthMe.llllllI[14]];
        AuthMe.lllIlIl[AuthMe.llllllI[15]] = AuthMe.lllIlll[AuthMe.llllllI[16]];
        AuthMe.lllIlIl[AuthMe.llllllI[2]] = AuthMe.lllIlll[AuthMe.llllllI[17]];
        AuthMe.lllIlIl[AuthMe.llllllI[13]] = AuthMe.lllIlll[AuthMe.llllllI[15]];
        AuthMe.lllIlIl[AuthMe.llllllI[3]] = AuthMe.lllIlll[AuthMe.llllllI[9]];
        AuthMe.lllIlIl[AuthMe.llllllI[14]] = AuthMe.lllIlll[AuthMe.llllllI[18]];
        AuthMe.lllIlIl[AuthMe.llllllI[8]] = AuthMe.lllIlll[AuthMe.llllllI[19]];
        AuthMe.lllIlIl[AuthMe.llllllI[17]] = AuthMe.lllIlll[AuthMe.llllllI[20]];
        AuthMe.lllIlIl[AuthMe.llllllI[0]] = AuthMe.lllIlll[AuthMe.llllllI[21]];
        AuthMe.lllIlIl[AuthMe.llllllI[4]] = AuthMe.lllIlll[AuthMe.llllllI[22]];
        AuthMe.lllIlIl[AuthMe.llllllI[7]] = AuthMe.lllIlll[AuthMe.llllllI[23]];
        AuthMe.lllIlIl[AuthMe.llllllI[11]] = AuthMe.lllIlll[AuthMe.llllllI[24]];
        AuthMe.lllIlIl[AuthMe.llllllI[16]] = AuthMe.lllIlll[AuthMe.llllllI[25]];
        (AuthMe.lllIllI = new Class[AuthMe.llllllI[4]])[AuthMe.llllllI[2]] = AuthManager.class;
        AuthMe.lllIllI[AuthMe.llllllI[0]] = Client.class;
        AuthMe.lllIllI[AuthMe.llllllI[1]] = Boolean.TYPE;
        AuthMe.lllIllI[AuthMe.llllllI[3]] = Minecraft.class;
    }
    
    public String execute(final String[] lllIIIlllllIIII) {
        if (lIIlllIIl(invokedynamic(2:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Z, invokedynamic(1:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Authentication/AuthManager;, invokedynamic(0:()Lxyz/Melody/Client;))))) {
            // invokedynamic(3:(Ljava/lang/Object;)V, AuthMe.lllIlll[AuthMe.llllllI[5]])
            return null;
        }
        if (lIIlllIIl(invokedynamic(10:(Lxyz/Melody/System/Melody/Authentication/AuthManager;Ljava/lang/String;Ljava/lang/String;)Z, invokedynamic(1:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Authentication/AuthManager;, invokedynamic(0:()Lxyz/Melody/Client;)), invokedynamic(8:(Ljava/util/UUID;)Ljava/lang/String;, invokedynamic(7:(Lcom/mojang/authlib/GameProfile;)Ljava/util/UUID;, invokedynamic(6:(Lnet/minecraft/util/Session;)Lcom/mojang/authlib/GameProfile;, invokedynamic(5:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/util/Session;, invokedynamic(4:(Lxyz/Melody/System/Commands/commands/AuthMe;)Lnet/minecraft/client/Minecraft;, this))))), invokedynamic(9:(Lnet/minecraft/util/Session;)Ljava/lang/String;, invokedynamic(5:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/util/Session;, invokedynamic(4:(Lxyz/Melody/System/Commands/commands/AuthMe;)Lnet/minecraft/client/Minecraft;, this)))))) {
            // invokedynamic(3:(Ljava/lang/Object;)V, AuthMe.lllIlll[AuthMe.llllllI[6]])
            final String lllIIIllllIlllI = invokedynamic(12:(Ljava/util/List;)Ljava/util/Iterator;, invokedynamic(11:()Ljava/util/List;));
            while (lIIlllIIl(invokedynamic(13:(Ljava/util/Iterator;)Z, lllIIIllllIlllI))) {
                final Module lllIIIlllllIIlI = (Module)invokedynamic(14:(Ljava/util/Iterator;)Ljava/lang/Object;, lllIIIllllIlllI);
                if (lIIlllIll(invokedynamic(15:(Lxyz/Melody/module/Module;)Z, lllIIIlllllIIlI))) {
                    "".length();
                    if ("   ".length() > "   ".length()) {
                        return null;
                    }
                    continue;
                }
                else {
                    // invokedynamic(16:(Lxyz/Melody/module/Module;Z)V, lllIIIlllllIIlI, AuthMe.llllllI[2])
                    "".length();
                    if (" ".length() << " ".length() < 0) {
                        return null;
                    }
                    continue;
                }
            }
            "".length();
            if ("   ".length() <= " ".length()) {
                return null;
            }
        }
        else {
        }
        // invokedynamic(3:(Ljava/lang/Object;)V, AuthMe.lllIlll[AuthMe.llllllI[7]])
        return null;
    }
    
    private static String lIIlIllIl(String lllIIIllIlIIllI, final String lllIIIllIlIlIlI) {
        lllIIIllIlIIllI = new String(Base64.getDecoder().decode(lllIIIllIlIIllI.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder lllIIIllIlIlIIl = new StringBuilder();
        final char[] lllIIIllIlIlIII = lllIIIllIlIlIlI.toCharArray();
        int lllIIIllIlIIlll = AuthMe.llllllI[0];
        final int lllIIIllIlIIIIl = (Object)lllIIIllIlIIllI.toCharArray();
        final byte lllIIIllIlIIIII = (byte)lllIIIllIlIIIIl.length;
        double lllIIIllIIlllll = AuthMe.llllllI[0];
        while (lIIllllll((int)lllIIIllIIlllll, lllIIIllIlIIIII)) {
            final char lllIIIllIlIllII = lllIIIllIlIIIIl[lllIIIllIIlllll];
            lllIIIllIlIlIIl.append((char)(lllIIIllIlIllII ^ lllIIIllIlIlIII[lllIIIllIlIIlll % lllIIIllIlIlIII.length]));
            "".length();
            ++lllIIIllIlIIlll;
            ++lllIIIllIIlllll;
            "".length();
            if ("   ".length() != "   ".length()) {
                return null;
            }
        }
        return String.valueOf(lllIIIllIlIlIIl);
    }
}
