//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account;

import java.lang.invoke.*;
import xyz.Melody.injection.mixins.client.*;
import net.minecraft.util.*;
import com.mojang.authlib.yggdrasil.*;
import com.mojang.authlib.exceptions.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;
import java.nio.charset.*;
import java.net.*;
import com.mojang.authlib.*;
import java.io.*;
import com.google.gson.*;
import xyz.Melody.System.Melody.Account.altimpl.*;

public final class AltManager
{
    private final /* synthetic */ Gson gson;
    private static /* synthetic */ Class[] lIIlIII;
    private static /* synthetic */ String[] lIIIlll;
    private final /* synthetic */ ArrayList<Alt> altList;
    private static final /* synthetic */ int[] lIIllll;
    private final /* synthetic */ JsonParser parser;
    private static final /* synthetic */ String[] lIIlIIl;
    private static /* synthetic */ String[] lIIlIlI;
    private final /* synthetic */ File ALT_FILE;
    
    public AltManager() {
        this.ALT_FILE = new File(invokedynamic(0:()Ljava/io/File;), AltManager.lIIlIIl[AltManager.lIIllll[0]]);
        this.altList = new ArrayList<Alt>();
        this.parser = new JsonParser();
        this.gson = invokedynamic(2:(Lcom/google/gson/GsonBuilder;)Lcom/google/gson/Gson;, invokedynamic(1:(Lcom/google/gson/GsonBuilder;)Lcom/google/gson/GsonBuilder;, new GsonBuilder()));
    }
    
    public ArrayList<Alt> getAltList() {
        return invokedynamic(3:(Lxyz/Melody/System/Melody/Account/AltManager;)Ljava/util/ArrayList;, this);
    }
    
    private static CallSite lIlllIll(final MethodHandles.Lookup lllIllIIlIlIlIl, final String lllIllIIlIlIlII, final MethodType lllIllIIlIlIllI) throws NoSuchMethodException, IllegalAccessException {
        try {
            final String[] lllIllIIlIllllI = AltManager.lIIIlll[Integer.parseInt(lllIllIIlIlIlII)].split(AltManager.lIIlIIl[AltManager.lIIllll[42]]);
            final Class<?> lllIllIIlIlllIl = Class.forName(lllIllIIlIllllI[AltManager.lIIllll[0]]);
            final String lllIllIIlIlllII = lllIllIIlIllllI[AltManager.lIIllll[1]];
            MethodHandle lllIllIIlIllIll = null;
            final int lllIllIIlIllIlI = lllIllIIlIllllI[AltManager.lIIllll[3]].length();
            if (llIIllII(lllIllIIlIllIlI, AltManager.lIIllll[2])) {
                final MethodType lllIllIIllIIIII = MethodType.fromMethodDescriptorString(lllIllIIlIllllI[AltManager.lIIllll[2]], AltManager.class.getClassLoader());
                if (llIIllIl(lllIllIIlIllIlI, AltManager.lIIllll[2])) {
                    lllIllIIlIllIll = lllIllIIlIlIlIl.findVirtual(lllIllIIlIlllIl, lllIllIIlIlllII, lllIllIIllIIIII);
                    "".length();
                    if (null != null) {
                        return null;
                    }
                }
                else {
                    lllIllIIlIllIll = lllIllIIlIlIlIl.findStatic(lllIllIIlIlllIl, lllIllIIlIlllII, lllIllIIllIIIII);
                }
                "".length();
                if (null != null) {
                    return null;
                }
            }
            else {
                final Class lllIllIIlIlllll = AltManager.lIIlIII[Integer.parseInt(lllIllIIlIllllI[AltManager.lIIllll[2]])];
                if (llIIllIl(lllIllIIlIllIlI, AltManager.lIIllll[3])) {
                    lllIllIIlIllIll = lllIllIIlIlIlIl.findGetter(lllIllIIlIlllIl, lllIllIIlIlllII, lllIllIIlIlllll);
                    "".length();
                    if (" ".length() << (" ".length() << " ".length()) != " ".length() << (" ".length() << " ".length())) {
                        return null;
                    }
                }
                else if (llIIllIl(lllIllIIlIllIlI, AltManager.lIIllll[4])) {
                    lllIllIIlIllIll = lllIllIIlIlIlIl.findStaticGetter(lllIllIIlIlllIl, lllIllIIlIlllII, lllIllIIlIlllll);
                    "".length();
                    if ((0xB4 ^ 0xAB ^ (0x3F ^ 0x32) << " ".length()) == 0x0) {
                        return null;
                    }
                }
                else if (llIIllIl(lllIllIIlIllIlI, AltManager.lIIllll[5])) {
                    lllIllIIlIllIll = lllIllIIlIlIlIl.findSetter(lllIllIIlIlllIl, lllIllIIlIlllII, lllIllIIlIlllll);
                    "".length();
                    if (null != null) {
                        return null;
                    }
                }
                else {
                    lllIllIIlIllIll = lllIllIIlIlIlIl.findStaticSetter(lllIllIIlIlllIl, lllIllIIlIlllII, lllIllIIlIlllll);
                }
            }
            return new ConstantCallSite(lllIllIIlIllIll);
        }
        catch (Exception lllIllIIlIllIIl) {
            lllIllIIlIllIIl.printStackTrace();
            return null;
        }
    }
    
    private void writeStringToFile(final String lllIllIlIIIIIIl, final File lllIllIlIIIIIlI) throws IOException {
        final FileOutputStream lllIllIlIIIIlIl = new FileOutputStream(lllIllIlIIIIIlI);
        float lllIllIIllllllI = (float)null;
        try {
            // invokedynamic(51:(Ljava/io/FileOutputStream;[B)V, lllIllIlIIIIlIl, invokedynamic(50:(Ljava/lang/String;Ljava/nio/charset/Charset;)[B, lllIllIlIIIIIIl, invokedynamic(8:()Ljava/nio/charset/Charset;)))
            if (llIIlIll(lllIllIlIIIIlIl)) {
                if (llIIlIll(lllIllIIllllllI)) {
                    try {
                        // invokedynamic(52:(Ljava/io/FileOutputStream;)V, lllIllIlIIIIlIl)
                        "".length();
                        if (" ".length() >= " ".length() << (" ".length() << " ".length())) {
                            return;
                        }
                        return;
                    }
                    catch (Throwable lllIllIIlllllIl) {
                        // invokedynamic(53:(Ljava/lang/Throwable;Ljava/lang/Throwable;)V, lllIllIIllllllI, lllIllIIlllllIl)
                        "".length();
                        if (-(129 + 46 - 136 + 124 ^ (0x2E ^ 0x7D) << " ".length()) >= 0) {
                            return;
                        }
                        return;
                    }
                }
                // invokedynamic(52:(Ljava/io/FileOutputStream;)V, lllIllIlIIIIlIl)
                "".length();
                if ((((0xAE ^ 0xA1) << (" ".length() << " ".length()) ^ (0x97 ^ 0xA2)) & ((0x91 ^ 0x84) << (" ".length() << " ".length()) ^ (0x57 ^ 0xA) ^ -" ".length())) != 0x0) {
                    return;
                }
            }
        }
        catch (Throwable lllIllIIlllllIl) {
            lllIllIIllllllI = (float)lllIllIIlllllIl;
            throw lllIllIIlllllIl;
        }
        finally {
            Label_0291: {
                if (llIIlIll(lllIllIlIIIIlIl)) {
                    if (llIIlIll(lllIllIIllllllI)) {
                        try {
                            // invokedynamic(52:(Ljava/io/FileOutputStream;)V, lllIllIlIIIIlIl)
                            "".length();
                            if (" ".length() << " ".length() == 0) {
                                return;
                            }
                            break Label_0291;
                        }
                        catch (Throwable lllIllIIllllIll) {
                            // invokedynamic(53:(Ljava/lang/Throwable;Ljava/lang/Throwable;)V, lllIllIIllllllI, lllIllIIllllIll)
                            "".length();
                            if (-" ".length() > 0) {
                                return;
                            }
                            break Label_0291;
                        }
                    }
                }
                // invokedynamic(52:(Ljava/io/FileOutputStream;)V, lllIllIlIIIIlIl)
            }
        }
    }
    
    private static boolean llIIlIlI(final int lllIllIIIIIlIlI) {
        return lllIllIIIIIlIlI != 0;
    }
    
    public static LoginStatus loginAlt(final String lllIllIIlllIIlI, final String lllIllIIllIllll) throws AuthenticationException {
        if (llIIlIlI(invokedynamic(54:(Ljava/lang/String;)Z, lllIllIIllIllll))) {
            // invokedynamic(56:(Lxyz/Melody/injection/mixins/client/MCA;Lnet/minecraft/util/Session;)V, (MCA)invokedynamic(55:()Lnet/minecraft/client/Minecraft;), new Session(lllIllIIlllIIlI, AltManager.lIIlIIl[AltManager.lIIllll[37]], AltManager.lIIlIIl[AltManager.lIIllll[38]], AltManager.lIIlIIl[AltManager.lIIllll[39]]))
            return invokedynamic(57:()Lxyz/Melody/System/Melody/Account/AltManager$LoginStatus;);
        }
        final YggdrasilAuthenticationService lllIllIIlllIlII = new YggdrasilAuthenticationService(invokedynamic(58:()Ljava/net/Proxy;), AltManager.lIIlIIl[AltManager.lIIllll[40]]);
        final YggdrasilUserAuthentication lllIllIIlllIIll = (YggdrasilUserAuthentication)invokedynamic(60:(Lcom/mojang/authlib/yggdrasil/YggdrasilAuthenticationService;Lcom/mojang/authlib/Agent;)Lcom/mojang/authlib/UserAuthentication;, lllIllIIlllIlII, invokedynamic(59:()Lcom/mojang/authlib/Agent;));
        // invokedynamic(61:(Lcom/mojang/authlib/yggdrasil/YggdrasilUserAuthentication;Ljava/lang/String;)V, lllIllIIlllIIll, lllIllIIlllIIlI)
        // invokedynamic(62:(Lcom/mojang/authlib/yggdrasil/YggdrasilUserAuthentication;Ljava/lang/String;)V, lllIllIIlllIIll, lllIllIIllIllll)
        try {
            // invokedynamic(63:(Lcom/mojang/authlib/yggdrasil/YggdrasilUserAuthentication;)V, lllIllIIlllIIll)
            // invokedynamic(56:(Lxyz/Melody/injection/mixins/client/MCA;Lnet/minecraft/util/Session;)V, (MCA)invokedynamic(55:()Lnet/minecraft/client/Minecraft;), new Session(invokedynamic(65:(Lcom/mojang/authlib/GameProfile;)Ljava/lang/String;, invokedynamic(64:(Lcom/mojang/authlib/yggdrasil/YggdrasilUserAuthentication;)Lcom/mojang/authlib/GameProfile;, lllIllIIlllIIll)), invokedynamic(67:(Ljava/util/UUID;)Ljava/lang/String;, invokedynamic(66:(Lcom/mojang/authlib/GameProfile;)Ljava/util/UUID;, invokedynamic(64:(Lcom/mojang/authlib/yggdrasil/YggdrasilUserAuthentication;)Lcom/mojang/authlib/GameProfile;, lllIllIIlllIIll))), invokedynamic(68:(Lcom/mojang/authlib/yggdrasil/YggdrasilUserAuthentication;)Ljava/lang/String;, lllIllIIlllIIll), AltManager.lIIlIIl[AltManager.lIIllll[41]]))
            return invokedynamic(57:()Lxyz/Melody/System/Melody/Account/AltManager$LoginStatus;);
        }
        catch (AuthenticationException lllIllIIlllIlIl) {
            throw lllIllIIlllIlIl;
        }
    }
    
    private static void llIIlIIl() {
        (lIIllll = new int[114])[0] = ((14 + 161 - 117 + 133 ^ (0x87 ^ 0xA4) << (" ".length() << " ".length())) & (122 + 135 - 145 + 59 ^ (0x80 ^ 0x93) << "   ".length() ^ -" ".length()));
        AltManager.lIIllll[1] = " ".length();
        AltManager.lIIllll[2] = " ".length() << " ".length();
        AltManager.lIIllll[3] = "   ".length();
        AltManager.lIIllll[4] = " ".length() << (" ".length() << " ".length());
        AltManager.lIIllll[5] = (0xA9 ^ 0xAC);
        AltManager.lIIllll[6] = "   ".length() << " ".length();
        AltManager.lIIllll[7] = (0xBE ^ 0xB9);
        AltManager.lIIllll[8] = " ".length() << "   ".length();
        AltManager.lIIllll[9] = (0x75 ^ 0x62 ^ (0xB8 ^ 0xB7) << " ".length());
        AltManager.lIIllll[10] = (0x6 ^ 0x3) << " ".length();
        AltManager.lIIllll[11] = (0x1D ^ 0x16);
        AltManager.lIIllll[12] = "   ".length() << (" ".length() << " ".length());
        AltManager.lIIllll[13] = (" ".length() << (0xA3 ^ 0xA6) ^ (0x7A ^ 0x57));
        AltManager.lIIllll[14] = (0xAD ^ 0xC4 ^ (0xA1 ^ 0x96) << " ".length()) << " ".length();
        AltManager.lIIllll[15] = (0x48 ^ 0x55 ^ (0xBD ^ 0xB4) << " ".length());
        AltManager.lIIllll[16] = " ".length() << (" ".length() << (" ".length() << " ".length()));
        AltManager.lIIllll[17] = ((0x1E ^ 0x33) << " ".length() ^ (0x3 ^ 0x48));
        AltManager.lIIllll[18] = (0x58 ^ 0x51) << " ".length();
        AltManager.lIIllll[19] = (9 + 185 - 45 + 66 ^ (0x6E ^ 0x5F) << (" ".length() << " ".length()));
        AltManager.lIIllll[20] = (0x94 ^ 0x91) << (" ".length() << " ".length());
        AltManager.lIIllll[21] = (106 + 124 - 120 + 49 ^ (0x6A ^ 0x2F) << " ".length());
        AltManager.lIIllll[22] = (0xCF ^ 0xB2 ^ (0xAA ^ 0x91) << " ".length()) << " ".length();
        AltManager.lIIllll[23] = (0xBA ^ 0x89 ^ (0x51 ^ 0x58) << (" ".length() << " ".length()));
        AltManager.lIIllll[24] = "   ".length() << "   ".length();
        AltManager.lIIllll[25] = (0x57 ^ 0x4E);
        AltManager.lIIllll[26] = (0xBE ^ 0xB3) << " ".length();
        AltManager.lIIllll[27] = ((0x28 ^ 0x1D) << " ".length() ^ (0xC8 ^ 0xB9));
        AltManager.lIIllll[28] = (0x1D ^ 0x1A) << (" ".length() << " ".length());
        AltManager.lIIllll[29] = (28 + 3 - 19 + 115 ^ (0x85 ^ 0xB4) << " ".length());
        AltManager.lIIllll[30] = (0xBF ^ 0xB0) << " ".length();
        AltManager.lIIllll[31] = (0x21 ^ 0x3E);
        AltManager.lIIllll[32] = " ".length() << (55 + 101 - 104 + 79 ^ (0xD0 ^ 0x93) << " ".length());
        AltManager.lIIllll[33] = ((0xED ^ 0xAA) << " ".length() ^ 47 + 145 - 57 + 40);
        AltManager.lIIllll[34] = (0x53 ^ 0x42) << " ".length();
        AltManager.lIIllll[35] = (0xBA ^ 0x99);
        AltManager.lIIllll[36] = (0x6A ^ 0x63) << (" ".length() << " ".length());
        AltManager.lIIllll[37] = (0x5E ^ 0x7B);
        AltManager.lIIllll[38] = (0xBE ^ 0xAD) << " ".length();
        AltManager.lIIllll[39] = ((0x4 ^ 0x3) << "   ".length() ^ (0x5 ^ 0x1A));
        AltManager.lIIllll[40] = (0x15 ^ 0x10) << "   ".length();
        AltManager.lIIllll[41] = (0x2D ^ 0x4);
        AltManager.lIIllll[42] = (0xD5 ^ 0xC0) << " ".length();
        AltManager.lIIllll[43] = (0x44 ^ 0x67) << " ".length();
        AltManager.lIIllll[44] = (0x27 ^ 0x2C) << (" ".length() << " ".length());
        AltManager.lIIllll[45] = (0x96 ^ 0xBD);
        AltManager.lIIllll[46] = (0x99 ^ 0xA0 ^ (0x17 ^ 0x12) << (" ".length() << " ".length()));
        AltManager.lIIllll[47] = (0x2D ^ 0x36) << " ".length();
        AltManager.lIIllll[48] = ((0x35 ^ 0x24) << " ".length() ^ (0x90 ^ 0xA5)) << " ".length();
        AltManager.lIIllll[49] = (" ".length() << (" ".length() << (" ".length() << " ".length())) ^ (0x9A ^ 0xA5));
        AltManager.lIIllll[50] = "   ".length() << (" ".length() << (" ".length() << " ".length()));
        AltManager.lIIllll[51] = ((0x85 ^ 0xA8) << " ".length() ^ (0xDB ^ 0xB0));
        AltManager.lIIllll[52] = (0xA3 ^ 0xB2) << (" ".length() << " ".length());
        AltManager.lIIllll[53] = (114 + 64 - 162 + 143 ^ (0x0 ^ 0x43) << " ".length()) << " ".length();
        AltManager.lIIllll[54] = (0x27 ^ 0x16 ^ " ".length() << " ".length());
        AltManager.lIIllll[55] = (0x2C ^ 0x21) << (" ".length() << " ".length());
        AltManager.lIIllll[56] = (6 + 28 - 27 + 136 ^ (0x16 ^ 0x7) << "   ".length()) << "   ".length();
        AltManager.lIIllll[57] = ((0x28 ^ 0x39) << "   ".length() ^ 158 + 137 - 145 + 39);
        AltManager.lIIllll[58] = (0x4 ^ 0x3 ^ "   ".length() << (" ".length() << (" ".length() << " ".length())));
        AltManager.lIIllll[59] = (0x67 ^ 0x1C ^ " ".length() << ("   ".length() << " ".length()));
        AltManager.lIIllll[60] = (0x92 ^ 0xAB);
        AltManager.lIIllll[61] = (0x96 ^ 0x8B) << " ".length();
        AltManager.lIIllll[62] = (0x7E ^ 0x5F) << " ".length();
        AltManager.lIIllll[63] = ((0x54 ^ 0x53) << "   ".length() ^ (0xAE ^ 0x99)) << (" ".length() << " ".length());
        AltManager.lIIllll[64] = (0x45 ^ 0x78);
        AltManager.lIIllll[65] = (0x5E ^ 0x7D ^ (0x84 ^ 0x8B) << (" ".length() << " ".length())) << " ".length();
        AltManager.lIIllll[66] = ((0xEA ^ 0xB9) << " ".length() ^ 136 + 134 - 140 + 23);
        AltManager.lIIllll[67] = " ".length() << ("   ".length() << " ".length());
        AltManager.lIIllll[68] = ((0x11 ^ 0xE) << "   ".length() ^ 178 + 138 - 243 + 112);
        AltManager.lIIllll[69] = ((0xA0 ^ 0xA9) << " ".length() ^ (0xC ^ 0x5D));
        AltManager.lIIllll[70] = (0xCF ^ 0x8A);
        AltManager.lIIllll[71] = ((0x3B ^ 0x30) << "   ".length() ^ (0x63 ^ 0x7C));
        AltManager.lIIllll[72] = ((0x7B ^ 0x42) << " ".length() ^ (0xEB ^ 0x90)) << "   ".length();
        AltManager.lIIllll[73] = (0x6B ^ 0x22);
        AltManager.lIIllll[74] = (0xE ^ 0x29 ^ " ".length() << " ".length()) << " ".length();
        AltManager.lIIllll[75] = ((0xDE ^ 0xC3) << "   ".length() ^ 123 + 145 - 215 + 110);
        AltManager.lIIllll[76] = ((0xF2 ^ 0x99) << " ".length() ^ 155 + 21 - 69 + 90) << (" ".length() << " ".length());
        AltManager.lIIllll[77] = (0x5 ^ 0x48);
        AltManager.lIIllll[78] = (0x2E ^ 0x9) << " ".length();
        AltManager.lIIllll[79] = (0x6 ^ 0x49);
        AltManager.lIIllll[80] = (0x4D ^ 0x48) << (" ".length() << (" ".length() << " ".length()));
        AltManager.lIIllll[81] = (0x14 ^ 0x11 ^ (0x49 ^ 0x5C) << (" ".length() << " ".length()));
        AltManager.lIIllll[82] = ((0x5E ^ 0x5B) << " ".length() ^ (0x15 ^ 0x36)) << " ".length();
        AltManager.lIIllll[83] = (0xEB ^ 0xB8);
        AltManager.lIIllll[84] = (0x58 ^ 0x4D) << (" ".length() << " ".length());
        AltManager.lIIllll[85] = ((0xE5 ^ 0x90) << " ".length() ^ 63 + 100 - 47 + 75);
        AltManager.lIIllll[86] = ((0x3C ^ 0x6F) << " ".length() ^ 38 + 36 + 49 + 18) << " ".length();
        AltManager.lIIllll[87] = (0x27 ^ 0x70);
        AltManager.lIIllll[88] = (0x2 ^ 0x9) << "   ".length();
        AltManager.lIIllll[89] = (0x2E ^ 0x77);
        AltManager.lIIllll[90] = (0x4E ^ 0x59 ^ (0xBC ^ 0xA1) << " ".length()) << " ".length();
        AltManager.lIIllll[91] = (0x61 ^ 0x34 ^ (0x8F ^ 0x88) << " ".length());
        AltManager.lIIllll[92] = (0x8E ^ 0x99) << (" ".length() << " ".length());
        AltManager.lIIllll[93] = ((0x42 ^ 0x65) << (" ".length() << " ".length()) ^ 101 + 141 - 168 + 119);
        AltManager.lIIllll[94] = (0x6A ^ 0x45) << " ".length();
        AltManager.lIIllll[95] = (0x9D ^ 0xC2);
        AltManager.lIIllll[96] = "   ".length() << ((0x50 ^ 0x7F) << (" ".length() << " ".length()) ^ 44 + 80 - 120 + 181);
        AltManager.lIIllll[97] = (0x6A ^ 0xB);
        AltManager.lIIllll[98] = (0x1F ^ 0x2E) << " ".length();
        AltManager.lIIllll[99] = (0x1F ^ 0x7C);
        AltManager.lIIllll[100] = ((0xDB ^ 0x9C) << " ".length() ^ 117 + 68 - 112 + 78) << (" ".length() << " ".length());
        AltManager.lIIllll[101] = (0xA3 ^ 0xC6);
        AltManager.lIIllll[102] = ((0x74 ^ 0x39) << " ".length() ^ 64 + 31 + 24 + 50) << " ".length();
        AltManager.lIIllll[103] = (0xD5 ^ 0xB2);
        AltManager.lIIllll[104] = (0x4B ^ 0x46) << "   ".length();
        AltManager.lIIllll[105] = (51 + 51 + 87 + 34 ^ (0x7E ^ 0x25) << " ".length());
        AltManager.lIIllll[106] = ((0x4A ^ 0x67) << (" ".length() << " ".length()) ^ 8 + 102 - 26 + 45) << " ".length();
        AltManager.lIIllll[107] = (0x59 ^ 0x6E ^ (0x81 ^ 0x96) << (" ".length() << " ".length()));
        AltManager.lIIllll[108] = (0x92 ^ 0x89) << (" ".length() << " ".length());
        AltManager.lIIllll[109] = (0x40 ^ 0x2D);
        AltManager.lIIllll[110] = (0xBF ^ 0x88) << " ".length();
        AltManager.lIIllll[111] = (0x6C ^ 0x3);
        AltManager.lIIllll[112] = (0x2A ^ 0x2D) << (" ".length() << (" ".length() << " ".length()));
        AltManager.lIIllll[113] = (0xAB ^ 0xC6 ^ (0xC5 ^ 0xC2) << (" ".length() << " ".length()));
    }
    
    private static String lIlllllI(final String lllIllIIIIlllll, final String lllIllIIIIlllII) {
        try {
            final SecretKeySpec lllIllIIIlIIIlI = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIllIIIIlllII.getBytes(StandardCharsets.UTF_8)), AltManager.lIIllll[8]), "DES");
            final Cipher lllIllIIIlIIIIl = Cipher.getInstance("DES");
            lllIllIIIlIIIIl.init(AltManager.lIIllll[2], lllIllIIIlIIIlI);
            return new String(lllIllIIIlIIIIl.doFinal(Base64.getDecoder().decode(lllIllIIIIlllll.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIllIIIlIIIII) {
            lllIllIIIlIIIII.printStackTrace();
            return null;
        }
    }
    
    private static boolean llIIllII(final int lllIllIIIIIllll, final int lllIllIIIIIlllI) {
        return lllIllIIIIIllll <= lllIllIIIIIlllI;
    }
    
    private static boolean llIIlIll(final Object lllIllIIIIIllII) {
        return lllIllIIIIIllII != null;
    }
    
    private static void lIllllII() {
        (AltManager.lIIIlll = new String[AltManager.lIIllll[43]])[AltManager.lIIllll[44]] = AltManager.lIIlIIl[AltManager.lIIllll[45]];
        AltManager.lIIIlll[AltManager.lIIllll[21]] = AltManager.lIIlIIl[AltManager.lIIllll[44]];
        AltManager.lIIIlll[AltManager.lIIllll[18]] = AltManager.lIIlIIl[AltManager.lIIllll[46]];
        AltManager.lIIIlll[AltManager.lIIllll[47]] = AltManager.lIIlIIl[AltManager.lIIllll[48]];
        AltManager.lIIIlll[AltManager.lIIllll[40]] = AltManager.lIIlIIl[AltManager.lIIllll[49]];
        AltManager.lIIIlll[AltManager.lIIllll[8]] = AltManager.lIIlIIl[AltManager.lIIllll[50]];
        AltManager.lIIIlll[AltManager.lIIllll[1]] = AltManager.lIIlIIl[AltManager.lIIllll[51]];
        AltManager.lIIIlll[AltManager.lIIllll[52]] = AltManager.lIIlIIl[AltManager.lIIllll[53]];
        AltManager.lIIIlll[AltManager.lIIllll[29]] = AltManager.lIIlIIl[AltManager.lIIllll[54]];
        AltManager.lIIIlll[AltManager.lIIllll[39]] = AltManager.lIIlIIl[AltManager.lIIllll[55]];
        AltManager.lIIIlll[AltManager.lIIllll[56]] = AltManager.lIIlIIl[AltManager.lIIllll[57]];
        AltManager.lIIIlll[AltManager.lIIllll[24]] = AltManager.lIIlIIl[AltManager.lIIllll[47]];
        AltManager.lIIIlll[AltManager.lIIllll[58]] = AltManager.lIIlIIl[AltManager.lIIllll[58]];
        AltManager.lIIIlll[AltManager.lIIllll[20]] = AltManager.lIIlIIl[AltManager.lIIllll[56]];
        AltManager.lIIIlll[AltManager.lIIllll[59]] = AltManager.lIIlIIl[AltManager.lIIllll[60]];
        AltManager.lIIIlll[AltManager.lIIllll[37]] = AltManager.lIIlIIl[AltManager.lIIllll[61]];
        AltManager.lIIIlll[AltManager.lIIllll[62]] = AltManager.lIIlIIl[AltManager.lIIllll[59]];
        AltManager.lIIIlll[AltManager.lIIllll[49]] = AltManager.lIIlIIl[AltManager.lIIllll[63]];
        AltManager.lIIIlll[AltManager.lIIllll[15]] = AltManager.lIIlIIl[AltManager.lIIllll[64]];
        AltManager.lIIIlll[AltManager.lIIllll[61]] = AltManager.lIIlIIl[AltManager.lIIllll[65]];
        AltManager.lIIIlll[AltManager.lIIllll[51]] = AltManager.lIIlIIl[AltManager.lIIllll[66]];
        AltManager.lIIIlll[AltManager.lIIllll[42]] = AltManager.lIIlIIl[AltManager.lIIllll[67]];
        AltManager.lIIIlll[AltManager.lIIllll[3]] = AltManager.lIIlIIl[AltManager.lIIllll[68]];
        AltManager.lIIIlll[AltManager.lIIllll[14]] = AltManager.lIIlIIl[AltManager.lIIllll[62]];
        AltManager.lIIIlll[AltManager.lIIllll[19]] = AltManager.lIIlIIl[AltManager.lIIllll[69]];
        AltManager.lIIIlll[AltManager.lIIllll[6]] = AltManager.lIIlIIl[AltManager.lIIllll[52]];
        AltManager.lIIIlll[AltManager.lIIllll[32]] = AltManager.lIIlIIl[AltManager.lIIllll[70]];
        AltManager.lIIIlll[AltManager.lIIllll[28]] = AltManager.lIIlIIl[AltManager.lIIllll[43]];
        AltManager.lIIIlll[AltManager.lIIllll[66]] = AltManager.lIIlIIl[AltManager.lIIllll[71]];
        AltManager.lIIIlll[AltManager.lIIllll[12]] = AltManager.lIIlIIl[AltManager.lIIllll[72]];
        AltManager.lIIIlll[AltManager.lIIllll[31]] = AltManager.lIIlIIl[AltManager.lIIllll[73]];
        AltManager.lIIIlll[AltManager.lIIllll[63]] = AltManager.lIIlIIl[AltManager.lIIllll[74]];
        AltManager.lIIIlll[AltManager.lIIllll[38]] = AltManager.lIIlIIl[AltManager.lIIllll[75]];
        AltManager.lIIIlll[AltManager.lIIllll[34]] = AltManager.lIIlIIl[AltManager.lIIllll[76]];
        AltManager.lIIIlll[AltManager.lIIllll[67]] = AltManager.lIIlIIl[AltManager.lIIllll[77]];
        AltManager.lIIIlll[AltManager.lIIllll[64]] = AltManager.lIIlIIl[AltManager.lIIllll[78]];
        AltManager.lIIIlll[AltManager.lIIllll[33]] = AltManager.lIIlIIl[AltManager.lIIllll[79]];
        AltManager.lIIIlll[AltManager.lIIllll[50]] = AltManager.lIIlIIl[AltManager.lIIllll[80]];
        AltManager.lIIIlll[AltManager.lIIllll[4]] = AltManager.lIIlIIl[AltManager.lIIllll[81]];
        AltManager.lIIIlll[AltManager.lIIllll[10]] = AltManager.lIIlIIl[AltManager.lIIllll[82]];
        AltManager.lIIIlll[AltManager.lIIllll[5]] = AltManager.lIIlIIl[AltManager.lIIllll[83]];
        AltManager.lIIIlll[AltManager.lIIllll[27]] = AltManager.lIIlIIl[AltManager.lIIllll[84]];
        AltManager.lIIIlll[AltManager.lIIllll[69]] = AltManager.lIIlIIl[AltManager.lIIllll[85]];
        AltManager.lIIIlll[AltManager.lIIllll[60]] = AltManager.lIIlIIl[AltManager.lIIllll[86]];
        AltManager.lIIIlll[AltManager.lIIllll[55]] = AltManager.lIIlIIl[AltManager.lIIllll[87]];
        AltManager.lIIIlll[AltManager.lIIllll[70]] = AltManager.lIIlIIl[AltManager.lIIllll[88]];
        AltManager.lIIIlll[AltManager.lIIllll[41]] = AltManager.lIIlIIl[AltManager.lIIllll[89]];
        AltManager.lIIIlll[AltManager.lIIllll[65]] = AltManager.lIIlIIl[AltManager.lIIllll[90]];
        AltManager.lIIIlll[AltManager.lIIllll[22]] = AltManager.lIIlIIl[AltManager.lIIllll[91]];
        AltManager.lIIIlll[AltManager.lIIllll[2]] = AltManager.lIIlIIl[AltManager.lIIllll[92]];
        AltManager.lIIIlll[AltManager.lIIllll[13]] = AltManager.lIIlIIl[AltManager.lIIllll[93]];
        AltManager.lIIIlll[AltManager.lIIllll[26]] = AltManager.lIIlIIl[AltManager.lIIllll[94]];
        AltManager.lIIIlll[AltManager.lIIllll[46]] = AltManager.lIIlIIl[AltManager.lIIllll[95]];
        AltManager.lIIIlll[AltManager.lIIllll[48]] = AltManager.lIIlIIl[AltManager.lIIllll[96]];
        AltManager.lIIIlll[AltManager.lIIllll[16]] = AltManager.lIIlIIl[AltManager.lIIllll[97]];
        AltManager.lIIIlll[AltManager.lIIllll[68]] = AltManager.lIIlIIl[AltManager.lIIllll[98]];
        AltManager.lIIIlll[AltManager.lIIllll[17]] = AltManager.lIIlIIl[AltManager.lIIllll[99]];
        AltManager.lIIIlll[AltManager.lIIllll[45]] = AltManager.lIIlIIl[AltManager.lIIllll[100]];
        AltManager.lIIIlll[AltManager.lIIllll[11]] = AltManager.lIIlIIl[AltManager.lIIllll[101]];
        AltManager.lIIIlll[AltManager.lIIllll[25]] = AltManager.lIIlIIl[AltManager.lIIllll[102]];
        AltManager.lIIIlll[AltManager.lIIllll[0]] = AltManager.lIIlIIl[AltManager.lIIllll[103]];
        AltManager.lIIIlll[AltManager.lIIllll[36]] = AltManager.lIIlIIl[AltManager.lIIllll[104]];
        AltManager.lIIIlll[AltManager.lIIllll[54]] = AltManager.lIIlIIl[AltManager.lIIllll[105]];
        AltManager.lIIIlll[AltManager.lIIllll[7]] = AltManager.lIIlIIl[AltManager.lIIllll[106]];
        AltManager.lIIIlll[AltManager.lIIllll[9]] = AltManager.lIIlIIl[AltManager.lIIllll[107]];
        AltManager.lIIIlll[AltManager.lIIllll[23]] = AltManager.lIIlIIl[AltManager.lIIllll[108]];
        AltManager.lIIIlll[AltManager.lIIllll[57]] = AltManager.lIIlIIl[AltManager.lIIllll[109]];
        AltManager.lIIIlll[AltManager.lIIllll[30]] = AltManager.lIIlIIl[AltManager.lIIllll[110]];
        AltManager.lIIIlll[AltManager.lIIllll[35]] = AltManager.lIIlIIl[AltManager.lIIllll[111]];
        AltManager.lIIIlll[AltManager.lIIllll[53]] = AltManager.lIIlIIl[AltManager.lIIllll[112]];
        (AltManager.lIIlIII = new Class[AltManager.lIIllll[9]])[AltManager.lIIllll[4]] = Charset.class;
        AltManager.lIIlIII[AltManager.lIIllll[7]] = Proxy.class;
        AltManager.lIIlIII[AltManager.lIIllll[5]] = int[].class;
        AltManager.lIIlIII[AltManager.lIIllll[1]] = ArrayList.class;
        AltManager.lIIlIII[AltManager.lIIllll[6]] = LoginStatus.class;
        AltManager.lIIlIII[AltManager.lIIllll[2]] = JsonParser.class;
        AltManager.lIIlIII[AltManager.lIIllll[8]] = Agent.class;
        AltManager.lIIlIII[AltManager.lIIllll[3]] = Gson.class;
        AltManager.lIIlIII[AltManager.lIIllll[0]] = File.class;
    }
    
    public void addAlt(final Alt lllIllIlIlllIlI) {
        // invokedynamic(4:(Ljava/util/ArrayList;Ljava/lang/Object;)Z, invokedynamic(3:(Lxyz/Melody/System/Melody/Account/AltManager;)Ljava/util/ArrayList;, this), lllIllIlIlllIlI)
        "".length();
    }
    
    private static void llIIIIlI() {
        final boolean lllIllIIlIIllII = (boolean)new Exception().getStackTrace()[AltManager.lIIllll[0]].getFileName();
        AltManager.lIIlIlI = ((String)lllIllIIlIIllII).substring(((String)lllIllIIlIIllII).indexOf("\u00e4") + AltManager.lIIllll[1], ((String)lllIllIIlIIllII).lastIndexOf("\u00fc")).split("\u00f6");
    }
    
    private static String lIllllll(final String lllIllIIlIIIlII, final String lllIllIIlIIIIIl) {
        try {
            final SecretKeySpec lllIllIIlIIIlll = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIllIIlIIIIIl.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher lllIllIIlIIIllI = Cipher.getInstance("Blowfish");
            lllIllIIlIIIllI.init(AltManager.lIIllll[2], lllIllIIlIIIlll);
            return new String(lllIllIIlIIIllI.doFinal(Base64.getDecoder().decode(lllIllIIlIIIlII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIllIIlIIIlIl) {
            lllIllIIlIIIlIl.printStackTrace();
            return null;
        }
    }
    
    static {
        llIIlIIl();
        llIIIIlI();
        llIIIIII();
        lIllllII();
    }
    
    private static boolean llIIllIl(final int lllIllIIIIlIlll, final int lllIllIIIIlIllI) {
        return lllIllIIIIlIlll == lllIllIIIIlIllI;
    }
    
    public void saveAlt() {
        final JsonArray lllIllIlIIlIIll = new JsonArray();
        // invokedynamic(25:(Ljava/util/ArrayList;Ljava/util/Comparator;)V, invokedynamic(3:(Lxyz/Melody/System/Melody/Account/AltManager;)Ljava/util/ArrayList;, this), invokedynamic(24:(Ljava/util/function/ToDoubleFunction;)Ljava/util/Comparator;, lllIllIIllIlIIl -> (double)-invokedynamic(69:(Ljava/lang/String;)I, invokedynamic(28:(Lxyz/Melody/System/Melody/Account/AccountEnum;)Ljava/lang/String;, invokedynamic(27:(Lxyz/Melody/System/Melody/Account/Alt;)Lxyz/Melody/System/Melody/Account/AccountEnum;, lllIllIIllIlIIl)))))
        final short lllIllIlIIlIIII = invokedynamic(26:(Ljava/util/ArrayList;)Ljava/util/Iterator;, invokedynamic(3:(Lxyz/Melody/System/Melody/Account/AltManager;)Ljava/util/ArrayList;, this));
        while (llIIlIlI(invokedynamic(14:(Ljava/util/Iterator;)Z, lllIllIlIIlIIII))) {
            final Alt lllIllIlIIlIlll = (Alt)invokedynamic(15:(Ljava/util/Iterator;)Ljava/lang/Object;, lllIllIlIIlIIII);
            final JsonObject lllIllIlIIllIII = new JsonObject();
            // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[19]], invokedynamic(28:(Lxyz/Melody/System/Melody/Account/AccountEnum;)Ljava/lang/String;, invokedynamic(27:(Lxyz/Melody/System/Melody/Account/Alt;)Lxyz/Melody/System/Melody/Account/AccountEnum;, lllIllIlIIlIlll)))
            // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[20]], invokedynamic(30:(Lxyz/Melody/System/Melody/Account/Alt;)Ljava/lang/String;, lllIllIlIIlIlll))
            if (llIIlIlI((lllIllIlIIlIlll instanceof MicrosoftAlt) ? 1 : 0)) {
                final MicrosoftAlt lllIllIlIIllIll = (MicrosoftAlt)lllIllIlIIlIlll;
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[21]], invokedynamic(31:(Lxyz/Melody/System/Melody/Account/altimpl/MicrosoftAlt;)Ljava/lang/String;, lllIllIlIIllIll))
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[22]], invokedynamic(32:(Lxyz/Melody/System/Melody/Account/altimpl/MicrosoftAlt;)Ljava/lang/String;, lllIllIlIIllIll))
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[23]], invokedynamic(33:(Lxyz/Melody/System/Melody/Account/altimpl/MicrosoftAlt;)Ljava/lang/String;, lllIllIlIIllIll))
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[24]], invokedynamic(34:(Lxyz/Melody/System/Melody/Account/altimpl/MicrosoftAlt;)Ljava/lang/String;, lllIllIlIIllIll))
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[25]], invokedynamic(35:(Lxyz/Melody/System/Melody/Account/altimpl/MicrosoftAlt;)Ljava/lang/String;, lllIllIlIIllIll))
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[26]], invokedynamic(36:(Lxyz/Melody/System/Melody/Account/altimpl/MicrosoftAlt;)Ljava/lang/String;, lllIllIlIIllIll))
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[27]], invokedynamic(37:(Lxyz/Melody/System/Melody/Account/altimpl/MicrosoftAlt;)Ljava/lang/String;, lllIllIlIIllIll))
                "".length();
                if ((0xC4 ^ 0xC1) <= 0) {
                    return;
                }
            }
            else if (llIIlIlI((lllIllIlIIlIlll instanceof OriginalAlt) ? 1 : 0)) {
                final OriginalAlt lllIllIlIIllIlI = (OriginalAlt)lllIllIlIIlIlll;
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[28]], invokedynamic(38:(Lxyz/Melody/System/Melody/Account/altimpl/OriginalAlt;)Ljava/lang/String;, lllIllIlIIllIlI))
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[29]], invokedynamic(39:(Lxyz/Melody/System/Melody/Account/altimpl/OriginalAlt;)Ljava/lang/String;, lllIllIlIIllIlI))
                // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[30]], invokedynamic(40:(Lxyz/Melody/System/Melody/Account/altimpl/OriginalAlt;)Ljava/lang/String;, lllIllIlIIllIlI))
                "".length();
                if (null != null) {
                    return;
                }
            }
            else if (llIIlIlI((lllIllIlIIlIlll instanceof XBLTokenAlt) ? 1 : 0)) {
                final XBLTokenAlt lllIllIlIIllIIl = (XBLTokenAlt)lllIllIlIIlIlll;
            }
            // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[31]], invokedynamic(41:(Lxyz/Melody/System/Melody/Account/altimpl/XBLTokenAlt;)Ljava/lang/String;, lllIllIlIIllIIl))
            // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[32]], invokedynamic(42:(Lxyz/Melody/System/Melody/Account/altimpl/XBLTokenAlt;)Ljava/lang/String;, lllIllIlIIllIIl))
            // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[33]], invokedynamic(43:(Lxyz/Melody/System/Melody/Account/altimpl/XBLTokenAlt;)Ljava/lang/String;, lllIllIlIIllIIl))
            // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[34]], invokedynamic(44:(Lxyz/Melody/System/Melody/Account/altimpl/XBLTokenAlt;)Ljava/lang/String;, lllIllIlIIllIIl))
            // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[35]], invokedynamic(45:(Lxyz/Melody/System/Melody/Account/altimpl/XBLTokenAlt;)Ljava/lang/String;, lllIllIlIIllIIl))
            // invokedynamic(29:(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)V, lllIllIlIIllIII, AltManager.lIIlIIl[AltManager.lIIllll[36]], invokedynamic(46:(Lxyz/Melody/System/Melody/Account/altimpl/XBLTokenAlt;)Ljava/lang/String;, lllIllIlIIllIIl))
            // invokedynamic(47:(Lcom/google/gson/JsonArray;Lcom/google/gson/JsonElement;)V, lllIllIlIIlIIll, lllIllIlIIllIII)
            "".length();
            if (null != null) {
                return;
            }
        }
        try {
            final String lllIllIlIIlIllI = invokedynamic(49:(Lcom/google/gson/Gson;Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(48:(Lxyz/Melody/System/Melody/Account/AltManager;)Lcom/google/gson/Gson;, this), lllIllIlIIlIIll);
            this.writeStringToFile(lllIllIlIIlIllI, invokedynamic(6:(Lxyz/Melody/System/Melody/Account/AltManager;)Ljava/io/File;, this));
            "".length();
            if (" ".length() <= 0) {
                return;
            }
        }
        catch (Exception lllIllIlIIlIlIl) {
        }
        // invokedynamic(23:(Ljava/lang/Exception;)V, lllIllIlIIlIlIl)
    }
    
    public void readAlt() {
        // invokedynamic(5:(Ljava/util/ArrayList;)V, invokedynamic(3:(Lxyz/Melody/System/Melody/Account/AltManager;)Ljava/util/ArrayList;, this))
        if (llIIlIlI(invokedynamic(7:(Ljava/io/File;)Z, invokedynamic(6:(Lxyz/Melody/System/Melody/Account/AltManager;)Ljava/io/File;, this)))) {
            try {
                final String lllIllIlIlIlIll = invokedynamic(9:(Ljava/io/InputStream;Ljava/nio/charset/Charset;)Ljava/lang/String;, new FileInputStream(invokedynamic(6:(Lxyz/Melody/System/Melody/Account/AltManager;)Ljava/io/File;, this)), invokedynamic(8:()Ljava/nio/charset/Charset;));
                final char lllIllIlIlIIllI = invokedynamic(13:(Lcom/google/gson/JsonArray;)Ljava/util/Iterator;, invokedynamic(12:(Lcom/google/gson/JsonElement;)Lcom/google/gson/JsonArray;, invokedynamic(11:(Lcom/google/gson/JsonParser;Ljava/lang/String;)Lcom/google/gson/JsonElement;, invokedynamic(10:(Lxyz/Melody/System/Melody/Account/AltManager;)Lcom/google/gson/JsonParser;, this), lllIllIlIlIlIll)));
                while (llIIlIlI(invokedynamic(14:(Ljava/util/Iterator;)Z, lllIllIlIlIIllI))) {
                    final JsonElement lllIllIlIlIllII = (JsonElement)invokedynamic(15:(Ljava/util/Iterator;)Ljava/lang/Object;, lllIllIlIlIIllI);
                    final JsonObject lllIllIlIlIllll = invokedynamic(16:(Lcom/google/gson/JsonElement;)Lcom/google/gson/JsonObject;, lllIllIlIlIllII);
                    final AccountEnum lllIllIlIlIlllI = invokedynamic(19:(Ljava/lang/String;)Lxyz/Melody/System/Melody/Account/AccountEnum;, invokedynamic(18:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(17:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIllIlIlIllll, AltManager.lIIlIIl[AltManager.lIIllll[1]])));
                    final String lllIllIlIlIllIl = invokedynamic(18:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(17:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIllIlIlIllll, AltManager.lIIlIIl[AltManager.lIIllll[2]]));
                    if (llIIlIll(lllIllIlIlIlllI)) {
                        switch (invokedynamic(20:()[I)[invokedynamic(21:(Lxyz/Melody/System/Melody/Account/AccountEnum;)I, lllIllIlIlIlllI)]) {
                            case 1: {
                                // invokedynamic(22:(Lxyz/Melody/System/Melody/Account/AltManager;Lxyz/Melody/System/Melody/Account/Alt;)V, this, new OfflineAlt(lllIllIlIlIllIl))
                                "".length();
                                if (" ".length() << (" ".length() << " ".length()) <= -" ".length()) {
                                    return;
                                }
                                break;
                            }
                            case 2: {
                                // invokedynamic(22:(Lxyz/Melody/System/Melody/Account/AltManager;Lxyz/Melody/System/Melody/Account/...
                                "".length();
                                if ("   ".length() <= 0) {
                                    return;
                                }
                                break;
                            }
                            case 3: {
                                // invokedynamic(22:(Lxyz/Melody/System/Melody/Account/AltManager;Lxyz/Melody/System/Melody/Account/Alt;)V, this, new OriginalAlt(lllIllIlIlIllIl, invokedynamic(18:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(17:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIllIlIlIllll, AltManager.lIIlIIl[AltManager.lIIllll[10]])), invokedynamic(18:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(17:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIllIlIlIllll, AltManager.lIIlIIl[AltManager.lIIllll[11]])), invokedynamic(18:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(17:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIllIlIlIllll, AltManager.lIIlIIl[AltManager.lIIllll[12]]))))
                                "".length();
                                if (-" ".length() > 0) {
                                    return;
                                }
                                break;
                            }
                            case 4: {
                                // invokedynamic(22:(Lxyz/Melody/System/Melody/Account/AltManager;Lxyz/Melody/System/Melody/Account/...
                                break;
                            }
                        }
                    }
                    "".length();
                    if ((20 + 19 + 25 + 65 ^ (0xAB ^ 0x8A) << (" ".length() << " ".length())) == 0x0) {
                        return;
                    }
                }
                "".length();
                if (((0xBA ^ 0x97) << " ".length() & ~((0x53 ^ 0x7E) << " ".length())) != ((0xA8 ^ 0xAD) << (" ".length() << (" ".length() << " ".length())) & ~((0x34 ^ 0x31) << (" ".length() << (" ".length() << " ".length()))))) {
                    return;
                }
            }
            catch (Exception lllIllIlIlIlIlI) {
            }
            // invokedynamic(23:(Ljava/lang/Exception;)V, lllIllIlIlIlIlI)
        }
    }
    
    private static boolean llIIlllI(final int lllIllIIIIlIIll, final int lllIllIIIIlIIlI) {
        return lllIllIIIIlIIll < lllIllIIIIlIIlI;
    }
    
    private static String lIllllIl(String lllIllIIIlIllll, final String lllIllIIIllIIll) {
        lllIllIIIlIllll = new String(Base64.getDecoder().decode(lllIllIIIlIllll.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder lllIllIIIllIIlI = new StringBuilder();
        final char[] lllIllIIIllIIIl = lllIllIIIllIIll.toCharArray();
        int lllIllIIIllIIII = AltManager.lIIllll[0];
        final char lllIllIIIlIlIlI = (Object)lllIllIIIlIllll.toCharArray();
        final boolean lllIllIIIlIlIIl = lllIllIIIlIlIlI.length != 0;
        long lllIllIIIlIlIII = AltManager.lIIllll[0];
        while (llIIlllI((int)lllIllIIIlIlIII, lllIllIIIlIlIIl ? 1 : 0)) {
            final char lllIllIIIllIlIl = lllIllIIIlIlIlI[lllIllIIIlIlIII];
            lllIllIIIllIIlI.append((char)(lllIllIIIllIlIl ^ lllIllIIIllIIIl[lllIllIIIllIIII % lllIllIIIllIIIl.length]));
            "".length();
            ++lllIllIIIllIIII;
            ++lllIllIIIlIlIII;
            "".length();
            if (null != null) {
                return null;
            }
        }
        return String.valueOf(lllIllIIIllIIlI);
    }
    
    private static void llIIIIII() {
        (lIIlIIl = new String[AltManager.lIIllll[113]])[AltManager.lIIllll[0]] = lIllllIl(AltManager.lIIlIlI[AltManager.lIIllll[0]], AltManager.lIIlIlI[AltManager.lIIllll[1]]);
        AltManager.lIIlIIl[AltManager.lIIllll[1]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[2]], AltManager.lIIlIlI[AltManager.lIIllll[3]]);
        AltManager.lIIlIIl[AltManager.lIIllll[2]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[4]], AltManager.lIIlIlI[AltManager.lIIllll[5]]);
        AltManager.lIIlIIl[AltManager.lIIllll[3]] = lIllllll(AltManager.lIIlIlI[AltManager.lIIllll[6]], AltManager.lIIlIlI[AltManager.lIIllll[7]]);
        AltManager.lIIlIIl[AltManager.lIIllll[4]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[8]], AltManager.lIIlIlI[AltManager.lIIllll[9]]);
        AltManager.lIIlIIl[AltManager.lIIllll[5]] = lIllllll(AltManager.lIIlIlI[AltManager.lIIllll[10]], AltManager.lIIlIlI[AltManager.lIIllll[11]]);
        AltManager.lIIlIIl[AltManager.lIIllll[6]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[12]], AltManager.lIIlIlI[AltManager.lIIllll[13]]);
        AltManager.lIIlIIl[AltManager.lIIllll[7]] = lIllllIl(AltManager.lIIlIlI[AltManager.lIIllll[14]], AltManager.lIIlIlI[AltManager.lIIllll[15]]);
        AltManager.lIIlIIl[AltManager.lIIllll[8]] = lIllllIl(AltManager.lIIlIlI[AltManager.lIIllll[16]], AltManager.lIIlIlI[AltManager.lIIllll[17]]);
        AltManager.lIIlIIl[AltManager.lIIllll[9]] = lIllllll(AltManager.lIIlIlI[AltManager.lIIllll[18]], AltManager.lIIlIlI[AltManager.lIIllll[19]]);
        AltManager.lIIlIIl[AltManager.lIIllll[10]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[20]], AltManager.lIIlIlI[AltManager.lIIllll[21]]);
        AltManager.lIIlIIl[AltManager.lIIllll[11]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[22]], AltManager.lIIlIlI[AltManager.lIIllll[23]]);
        AltManager.lIIlIIl[AltManager.lIIllll[12]] = lIllllll(AltManager.lIIlIlI[AltManager.lIIllll[24]], AltManager.lIIlIlI[AltManager.lIIllll[25]]);
        AltManager.lIIlIIl[AltManager.lIIllll[13]] = lIllllIl(AltManager.lIIlIlI[AltManager.lIIllll[26]], AltManager.lIIlIlI[AltManager.lIIllll[27]]);
        AltManager.lIIlIIl[AltManager.lIIllll[14]] = lIllllll(AltManager.lIIlIlI[AltManager.lIIllll[28]], AltManager.lIIlIlI[AltManager.lIIllll[29]]);
        AltManager.lIIlIIl[AltManager.lIIllll[15]] = lIllllll(AltManager.lIIlIlI[AltManager.lIIllll[30]], AltManager.lIIlIlI[AltManager.lIIllll[31]]);
        AltManager.lIIlIIl[AltManager.lIIllll[16]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[32]], AltManager.lIIlIlI[AltManager.lIIllll[33]]);
        AltManager.lIIlIIl[AltManager.lIIllll[17]] = lIllllIl(AltManager.lIIlIlI[AltManager.lIIllll[34]], AltManager.lIIlIlI[AltManager.lIIllll[35]]);
        AltManager.lIIlIIl[AltManager.lIIllll[18]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[36]], AltManager.lIIlIlI[AltManager.lIIllll[37]]);
        AltManager.lIIlIIl[AltManager.lIIllll[19]] = lIllllIl(AltManager.lIIlIlI[AltManager.lIIllll[38]], AltManager.lIIlIlI[AltManager.lIIllll[39]]);
        AltManager.lIIlIIl[AltManager.lIIllll[20]] = lIlllllI(AltManager.lIIlIlI[AltManager.lIIllll[40]], AltManager.lIIlIlI[AltManager.lIIllll[41]]);
        AltManager.lIIlIIl[AltManager.lIIllll[21]] = lIlllllI("jEmq8XP/6v+jGTxuLHrskA==", "PaLHW");
        AltManager.lIIlIIl[AltManager.lIIllll[22]] = lIlllllI("GUzMrOGduxG0q8bK15YuJQ==", "DbXfW");
        AltManager.lIIlIIl[AltManager.lIIllll[23]] = lIllllIl("Ngc2AjUFIBQ=", "nEzVZ");
        AltManager.lIIlIIl[AltManager.lIIllll[24]] = lIllllIl("GiIhGz5z", "BquHa");
        AltManager.lIIlIIl[AltManager.lIIllll[25]] = lIlllllI("n0DZKlzGCD8=", "xkSox");
        AltManager.lIIlIIl[AltManager.lIIllll[26]] = lIllllll("Jcv/2p33mDoGP/W8YHkT0w==", "CvgoH");
        AltManager.lIIlIIl[AltManager.lIIllll[27]] = lIlllllI("AgNf/0Pk2i0=", "hyLNu");
        AltManager.lIIlIIl[AltManager.lIIllll[28]] = lIllllll("EP6Nl4/NENg=", "oBBZV");
        AltManager.lIIlIIl[AltManager.lIIllll[29]] = lIllllll("Zkx7nJpY05c1jsvkSVMb9Q==", "IzMsO");
        AltManager.lIIlIIl[AltManager.lIIllll[30]] = lIllllIl("Lxs+Ig==", "zNwfP");
        AltManager.lIIlIIl[AltManager.lIIllll[31]] = lIllllll("PYfM2wuaNsw=", "FlSpS");
        AltManager.lIIlIIl[AltManager.lIIllll[32]] = lIllllll("cW+pnBueARI95Rkqp7URiA==", "SWZqe");
        AltManager.lIIlIIl[AltManager.lIIllll[33]] = lIllllIl("CR4DNSpg", "QMWfu");
        AltManager.lIIlIIl[AltManager.lIIllll[34]] = lIlllllI("CWjZika0Fmk=", "pvSul");
        AltManager.lIIlIIl[AltManager.lIIllll[35]] = lIllllll("NnY8vQxObv1+5S+xDWweOA==", "coxat");
        AltManager.lIIlIIl[AltManager.lIIllll[36]] = lIllllIl("EzcqDQ==", "FbcIG");
        AltManager.lIIlIIl[AltManager.lIIllll[37]] = lIlllllI("1TenR4bToGc=", "NHvab");
        AltManager.lIIlIIl[AltManager.lIIllll[38]] = lIlllllI("ZGmi4QopHmA=", "eqHAt");
        AltManager.lIIlIIl[AltManager.lIIllll[39]] = lIlllllI("GKyHa9prm5A=", "msXjX");
        AltManager.lIIlIIl[AltManager.lIIllll[40]] = lIllllll("zuRVyNgnYZI=", "boSLY");
        AltManager.lIIlIIl[AltManager.lIIllll[41]] = lIllllll("G//poMSDCEM=", "YzBEd");
        AltManager.lIIlIIl[AltManager.lIIllll[42]] = lIllllll("5I8B9GuR8JA=", "vZYGB");
        AltManager.lIIlIIl[AltManager.lIIllll[45]] = lIlllllI("VqzzfdvSy5VyK1jHLQJafb7lArbPyWZV/oLF96OrRbpAH4I4S4LLjp9uAM0k15kmW8oh1TeLjQ6nQ254IJWs+szLB7ayM6KRqrMxO6V8/QiHYUsgA4czf1aRNLiTn1co", "WTsry");
        AltManager.lIIlIIl[AltManager.lIIllll[44]] = lIlllllI("VhgFOzfrmSBgeqXhHrxAlxb0sRr4U3TLW3yNVk7+dfZbfI1WTv519mtve8Wpt8DfnZtqpLLI4K34TBMmJsXa2Q==", "vzGtA");
        AltManager.lIIlIIl[AltManager.lIIllll[46]] = lIllllIl("OjUnfQE2NS0/A3c9OTwIdxA5PAgcNi8+AzcucDQDLRs5ABIrMyQ0XHFzBjkHLztlPwc3PWUAEiszJDRdY3pq", "YZJSf");
        AltManager.lIIlIIl[AltManager.lIIllll[48]] = lIlllllI("cRl3166M2Cqu52EkN1A6ZE+kPzGR1F3nFiG7uKtVt7NEc2Dz70Mxrvgvdg6kddULWVLX46/c3ccBC9ffKdjYzl2JRGGs5N5i", "ipcgB");
        AltManager.lIIlIIl[AltManager.lIIllll[49]] = lIlllllI("pCvlCBodD0w//sPpLknC8z2vgfj9jC6bnBw/Ev438qulszY3IJKJMOM8MgQIXgkxLgI5Afb4WWKLtBwkb0xbeJWuopvEMgngEb/De+hTeXjqiK9F9+bMag==", "uPmbn");
        AltManager.lIIlIIl[AltManager.lIIllll[50]] = lIlllllI("wFFLPB8KB7uR2Sv+hQMXOdDrkyIdRd5JQ/dMDuZAvZ9e7X0mMJPsSsBERhICjx7/", "OJbaB");
        AltManager.lIIlIIl[AltManager.lIIllll[51]] = lIllllll("GbnRJgO9zXEI5FGREop+rKZ8FXv6Wim30elGp7G65zVGSAg7QnBjEC+DK2mjdScxCWZDizg3I0OhdAyxk3oX2B8LxQ4H3OKO0+1ZuA3xvczRb41QULxeMw==", "fclNr");
        AltManager.lIIlIIl[AltManager.lIIllll[53]] = lIlllllI("tnacYgfhXoRh/T0auNi5V255AuuH1bW49UWEa50zKa+oOb5TdrLiKARFnrnieoR0VYCCqpKujC2ZC6nzeRyqS48PISx8+OQPgbeHNPM1s5yJWFazTRCyfmVURW9m/WuLEd6HfsmvAKo=", "yVSdX");
        AltManager.lIIlIIl[AltManager.lIIllll[54]] = lIllllIl("BRg/Xw8JGDUdDUgQIR4GSD0hHgYpFTgUCxJNMxUMNgU9AQ0UAytLQCodMwcJSRszHw9JJCYDAQgQaT0CBwEzXgQHGTVeOxIFOx8PXV4ES0hG", "fwRqh");
        AltManager.lIIlIIl[AltManager.lIIllll[55]] = lIllllll("55jBjLwlMb0XhDio7k9+ECXyrTpsSlLG6JGvt3kuV4sLgL8zFSD7q1WWdkSTS+2dQFsTjpkhbqNAbFSro8P6kwrx7NN38nniXck95jEpSVz3iP4LWYgpNYJ40wAG/0GD", "PXKIS");
        AltManager.lIIlIIl[AltManager.lIIllll[57]] = lIllllIl("DggcXCwTHQkWGFgYCBgEFQUPHQ9YHA8KCBgCSBENHxQIBk87MidIEhMFNRcSBRgJHFtePQgXFVkcDxwEFQMHFBVZBBIbDVkiAwESHx4ISUggS0ZS", "vqfra");
        AltManager.lIIlIIl[AltManager.lIIllll[47]] = lIllllIl("BwcgO18YEj82Xy4JOyoQHwciNQNXBTk3AQwUPzQWKQkjOB0IXH4WGwwQN3UEGQ86dRcYCDUuGAIIeQ4eKQkjOB0IICM0EhkPOTRKRCo8OwcMSSMuGAFJFTUcHQckOwUCFG1gUQ==", "mfVZq");
        AltManager.lIIlIIl[AltManager.lIIllll[58]] = lIllllIl("FAYkRx8TDTUKABsFJEcRFgo1BwZULjkHFxkRMQ8GQAUlBxElVGFdQ0o8KFNaUy8+DAZVDjkHFxkRMQ8GVQA8ABcUF38kGxQGMxsTHBdrU1I=", "zcPir");
        AltManager.lIIlIIl[AltManager.lIIllll[56]] = lIllllIl("MzEOZSYuJBsvEmUbDTgfLiVaBg4nJxAyRQorFyQeJTxaJyICclAYHCI8FyMmKjhQMxIxbDkuByQsDW84MjsALgZvBREnBC8xUAoIKCcBJR9vCRcoBD4mAA4FPiVOflFraFRr", "KHtKk");
        AltManager.lIIlIIl[AltManager.lIIllll[60]] = lIllllll("2IhxYd21MROG89exCGml6GtRZn9mDPa5bRHhezVuP9vDuxcvU5U1ucJACh6V3+ac", "AAVbY");
        AltManager.lIIlIIl[AltManager.lIIllll[61]] = lIlllllI("G7QxcNV6wAlhkHJy0d8jJbH4X+Mp2qM/7fsKtkso9zxvZoJRq+Bx6ZzScEh6vCcMoCaPJhanyfKEhDljkmQfhEMEEQ1i+eUSssU36xqwt/ARygLfDVgz/Q==", "rbtHo");
        AltManager.lIIlIIl[AltManager.lIIllll[59]] = lIlllllI("AXT+mpU39oRYrl1/FWisbD4LejizknQEZq+6mTWq+xnTjEWhgisXpCUvvCvRURmKHunml7Zx0gxKe3M3htM5/A==", "SrZAk");
        AltManager.lIIlIIl[AltManager.lIIllll[63]] = lIlllllI("mykuhwLEHLf8xb15h5zVpOTE2lK/w4lYWl/SBLKcKG5EEXzUTVYy4dFh695X6T4x/zi1BtB07qDbElWorChNcZQnU4bleJBN", "oLykl");
        AltManager.lIIlIIl[AltManager.lIIllll[64]] = lIllllll("RhTm9mUDj8RKkDIAzzeWPcsrx5MxwXUjnEJ1uP7SSJ3vuf5ImMIKLmKkrHZSNqGV", "pnHdM");
        AltManager.lIIlIIl[AltManager.lIIllll[65]] = lIllllll("DAd5RXRvgLfxhbx5/vLGb+UabUhpMbKFtTc/BT0qNcA=", "ccFLT");
        AltManager.lIIlIIl[AltManager.lIIllll[66]] = lIllllll("EGWPGQpFXgvWYX01DHqj2dHCvb66lAG4Aed4xFyimkrrUD3ysJkMHMhaMvrun0Hrf8ke7EKYYGKkx2SzTLagBhf2k9375DpWPUS/8wCvWY/6PMfOkWwPtQ==", "oUVRG");
        AltManager.lIIlIIl[AltManager.lIIllll[67]] = lIllllll("gDngY25toflfiPUlTNdW4dywFIGpxn3ppqw5c+V4YcljaoLKoWdrBrY2xiq1cF63pcr2vOQKtIVFJpGx2OxR1gvAcg7ygqi3+aQV+iSuDTj8+AnpCCCTw8+D1QEYxUPi", "JBhzC");
        AltManager.lIIlIIl[AltManager.lIIllll[68]] = lIlllllI("vKdOptvIxJDz4hEVLtBiIAOyJjLv/BZ3cFg2+pCheIUZFsa7A2GwfNKSLbPuXEi3XPBh8mmkBp7Yry8VVCP5ow==", "jyWdp");
        AltManager.lIIlIIl[AltManager.lIIllll[62]] = lIlllllI("a7D0mZe54/kezfzTXOteM4cBgcmcdTmxhbRC+YMsG43CFScJKMzkKA==", "vQWSg");
        AltManager.lIIlIIl[AltManager.lIIllll[69]] = lIllllIl("NykdZQQqPAgvMGEDHjg9Kj1JBiwjPwMyZw4zBCQ8ISRJCiosPxIlPQo+EiZzPzEVOCx1eCshKDkxSCcoITdIGD09OQkscmYcHzIzYB0CJyYrKUgYMDwkAiZmAjULJC02fyYoKiAlCT9mDjMEJDwhJCIlPCJrXWs=", "OPgKI");
        AltManager.lIIlIIl[AltManager.lIIllll[52]] = lIllllIl("LAoRTCgxHwQGHHogEhERMR5FLwA4HA8bSxUQCA0QOgdFIwkgPgoMBDMWGVgkGCc0JCwYNlFSX3RTSw==", "Tskbe");
        AltManager.lIIlIIl[AltManager.lIIllll[70]] = lIllllll("PULO72BDZjLbZWqs3Q8u+DPs1Wy7InA/6rT/G5HfE1lpy72z+Nq3ip9LFKoU49RddaS3Gu2reK9wdfbXrO0VBOlNOZOr+dTAB2FJ8P+4lSGWoTKLffg6s1UKjjHHleoz", "ohmsd");
        AltManager.lIIlIIl[AltManager.lIIllll[43]] = lIllllIl("PjUqRAEjID8ONWgfKRk4IyF+JykqIzQTYgcvMwU5KDh+Ky8lIyUEOAMiJQd2ISkkPT4vODUkLSspakJlCiYxHC1pIDEEK2kfJBglKCtrUGxm", "FLPjL");
        AltManager.lIIlIIl[AltManager.lIIllll[71]] = lIllllIl("NSAfRys5JRMHIXguBx0uOiYQRz8xKBYbJyUmHkcfMSgWGyclJh48NTM9MxwyPiocHS81LgYAKTh1HgYhHyFIQW8AdVJJ", "VOriF");
        AltManager.lIIlIIl[AltManager.lIIllll[72]] = lIlllllI("jT6cdMXR9ydGiTb9xl+V0jVSbWjlZ2E010t2crjrDeUZ+H1vm5hfAWrxCi6X4mc+kXd9x+O46xy/MrizSKCWHS3AbVrZ7h6DScGZNrs486Y=", "bAouU");
        AltManager.lIIlIIl[AltManager.lIIllll[73]] = lIllllIl("DB0tWj8RCDgQC1o3LgcGEQl5ORcYCzMNXDUHNBsHGhB5FR4ADToEHlopPhcAGxc4EgY1CCNOFREQBREUBgEkHCYbDzIaSFxNGx4TAgV4GBMaA3gnBgYNORNJTkR3", "tdWtr");
        AltManager.lIIlIIl[AltManager.lIIllll[74]] = lIlllllI("XCYAxw/H1cj/Nr21+Kb0fZA/SEb2HdW3YH+PfjWyxj+RIv9qgXlA/8enwmjELzhZCW6nSKzq8zoGpK7Qw6NXdZJg0IAqFG1wx6fCaMQvOFnRXV/7O4p8XvLCN+5M0VPFxVig1Pr9Nz0l4q53AzcskMQl4LWTgsEvW14Dku5y/05XHBsFsY8iFIyyOLGHjpaF4NvNxb8Nanjae4MbA6bw1Q==", "wgnWY");
        AltManager.lIIlIIl[AltManager.lIIllll[75]] = lIllllll("BaSj0N/xZ0H0cIakNMpIB2gYIEF9XnGuIKXtK2iJ4e8XAhzUpNKZTKr/rOvM6lWuakxMpAC9ep//CsNhRlY0+Bgq1luqHzEz5UJk8BU0DvKs8Z43o1zuiw==", "wuKee");
        AltManager.lIIlIIl[AltManager.lIIllll[76]] = lIllllIl("FS81RBUIOiAOIUMFNhksCDthJz0BOSsTdiw1LAUtAyJhCzQZPyIaNEMbJgkqAiUgDCwsOjtQPwgiFxksHgIgAT0DCSlQcEQaJQsuDHkjCzYKeRweKgQ4KFFiTXY=", "mVOjX");
        AltManager.lIIlIIl[AltManager.lIIllll[77]] = lIllllIl("MzgnSCU/PSsIL342PxIgPD4oSDE3MC4UKSM+JkgRNzAuFCkjPiYzOzUlCxM8ODIkEiEzNj4PJz5tLQM8AzImAyskMi42Oj8xIwotan9jKis/OmULJzo2JAFnMSI+DiQ5NWUhKT0yGhQnNj4mA3Nqd2o=", "PWJfH");
        AltManager.lIIlIIl[AltManager.lIIllll[78]] = lIllllIl("JCEvaA8oJCMoBWkvNzIKKycgaBsgKSY0AzQnLmg7ICkmNAM0Jy4TESI8AzMWLyssMgskLzYvDSl0MSMWEj0nNAwmIyd8SgskIzADaCIjKAVoHTY0CykpeW80fW5i", "GNBFb");
        AltManager.lIIlIIl[AltManager.lIIllll[79]] = lIllllIl("OT09ajckKCggA28XPjcOJClpCR8tKyM9VAAnJCsPLzBpJRY1LSo0Fm8JLicILjcoIg4AKDN+HSQwHyYWFSssIRR7bG4IECAyJmsWICogayk1Ni4qHXp+Z2Q=", "ADGDz");
        AltManager.lIIlIIl[AltManager.lIIllll[80]] = lIllllll("S2oX/nOXERGem0DaBc2GlJKuQm5dl6RuUtJutCDEm73QISJ1q2TZX99Ozcyd7MjAstEkSDiJfRs=", "BlxZJ");
        AltManager.lIIlIIl[AltManager.lIIllll[81]] = lIllllll("tG0O+CfYaVpiDYzUh6hZbiHvQzMkGp9DYKLlnW1ko0ZkKg45p01sKiu4a7iPi8eruWLRfmiOG9g=", "OWYMx");
        AltManager.lIIlIIl[AltManager.lIIllll[82]] = lIllllIl("EhgAWCMPDRUSF0QyAwUaDwxUOwsGDh4PQCsCGRkbBBVUNwIeLBsYDw0ECEweCxMJExxQU0BWTko=", "jazvn");
        AltManager.lIIlIIl[AltManager.lIIllll[83]] = lIllllIl("ORI4GG0mBycVbRIBPBg6Hxo9DXkwHysYMWlbZy95c1M=", "SsNyC");
        AltManager.lIIlIIl[AltManager.lIIllll[84]] = lIllllIl("CigNeToXPRgzDlwCDiQDFzxZGhIePhMuWTMyFDgCHCVZFhsGaxAyAzMyFDgCHCUjLgcXa19+OwooDXg6Fz0YMw5dAg4kAxc8WBoSHj4TLlgzMhQ4AhwlWBYUET4COQM3PwI6TEhxVw==", "rQwWw");
        AltManager.lIIlIIl[AltManager.lIIllll[85]] = lIlllllI("iQ9X4B330XdYRrOHHjn9a+qLrvzKdYdJb3Kp1R8+N+UXd2dWNbeUypf5Lw2xgLR7", "KvgDY");
        AltManager.lIIlIIl[AltManager.lIIllll[86]] = lIllllll("4h77dsqgmNmCxJ/ERrOiw6t0SYjwrc4GZU9W9fdPTg0u5FwfsRYKf9ftIXlcgJWOSAKrJduOBLeR/ff2HO0O2g4MxLMlbC7F", "LKbPA");
        AltManager.lIIlIIl[AltManager.lIIllll[87]] = lIllllll("fpFDEGtBzXCZ37MPMrf+GWG36O1uwVmARPx1zdlQ9VDaw32/sbAaFw==", "ZRAEu");
        AltManager.lIIlIIl[AltManager.lIIllll[88]] = lIlllllI("/63x6sRutQ4lT/lZudhaNOug0f6ACt1ygQ12Bt6npxo=", "IYnML");
        AltManager.lIIlIIl[AltManager.lIIllll[89]] = lIllllIl("MiAwfgsvNSU0P2QKMyMyLzRkHSMmNi4paAs6KT8zJC1kMSo+MCcgKmQBCBwSJTIvPgcmLXA3Iz4NMyAjcHFjHCwrLyt/Kis3LX8VPisjPiFxY2pw", "JYJPF");
        AltManager.lIIlIIl[AltManager.lIIllll[90]] = lIllllll("NHGQh/MJ1R4m1cc+OWbCj9Fkx8udcXBhQrD0Lc1abLHW+MHgjm5sHxdm6OFlJfTnqU2rdjKZxF0JwXIQOpnB/SF2yDN/wT6Lm5JxDaWsRy8NM7JuqWZGSbohtwQqupsl", "dKXNS");
        AltManager.lIIlIIl[AltManager.lIIllll[91]] = lIllllll("6UvwAnUReCS/hGbZUdxAdhxbrGe5A5rzExyad6UjqhWYDG6xIn3d5MLbthiGd9b2NBlLS+XCnxbWwqTeDPOOZKR7NgSUYLprqoZXhsP0zell+jUaNYq6OA9FiXGY+jls", "KbNlP");
        AltManager.lIIlIIl[AltManager.lIIllll[92]] = lIllllll("pZb8COC1z156DbuitKm4zyTblMfx+UAbP/qHdm6G1ON86mc4xvuEAmufYzLU8C+Q4qbD/Q7nyPFupK3sKEi8mw==", "YLqKl");
        AltManager.lIIlIIl[AltManager.lIIllll[93]] = lIlllllI("G6BDEcQeDZVPvbP+J5c0hw1CdjHIxL2jATGXGMgzc2ljEctebPc0lvKudH4CntrU6e9IHhN//VBpI7exVCh3og==", "ZulfM");
        AltManager.lIIlIIl[AltManager.lIIllll[94]] = lIllllIl("Py4YC0wgOwcGTBQ9HAsbGSYdHlg8OwsYAyEgHFBKfAMECxQ0YBseCzlgJx4HJy4aBRBudU5K", "UOnjb");
        AltManager.lIIlIIl[AltManager.lIIllll[95]] = lIlllllI("NpaGhqO6E4WsKUtjsUoeb2sOkT4dQw6uYxwoLh3OZyy/CX+Dfm0lRj2d/XP4OqvHdWRTkN4nhjvkMq83Ngi8zlAb9/iHqdAYBQnOc5a8BE/mJcDSsF4g6j7CZEonJf8w", "aNypL");
        AltManager.lIIlIIl[AltManager.lIIllll[96]] = lIlllllI("EEGjzuRiPwctXrKCCPYB2yJAt1JCDEjJG7N22d2ARg4DS18keCX85ATxIOXqcLC1sJvguulfdcbcwZVdrnhhNHAkAP1k73xsukkTHBHgBAIFntHMApaSBw==", "iyasP");
        AltManager.lIIlIIl[AltManager.lIIllll[97]] = lIllllIl("ChYoRzAGFiIFMkceNgY5RzM2BjksFSAEMgcNfw4yHTg2IyQGFwoLPQwaMVN/QDUmBjpGHioGMAUcag4kBhdqIyQGFwoLPQwaMVJtSVk=", "iyEiW");
        AltManager.lIIlIIl[AltManager.lIIllll[98]] = lIllllIl("Og4ZTC42CxUMJHcAARYrNQgWTAQ4DBEyMTYHHQ4mYwYRFg04DBFYa3AtHgM1OE4YAy0+TicWMTAPE1l5eUE=", "YatbC");
        AltManager.lIIlIIl[AltManager.lIIllll[99]] = lIlllllI("0BAX+vjj/mR5WwfwSjON8CGeY8W/BfI63y/UG152wx3SIWfA8JapKVDFfYSZ4fyu3jyb70+GEBVn4hwuFrr6u1lBzYpc86ylgEfpNbk43j3675peXOH8Ig==", "DZKuh");
        AltManager.lIIlIIl[AltManager.lIIllll[100]] = lIllllll("ZC3GdqQXYunTVuoldBhtBHuvGQkJV8cJ620z7letdtWrk619ZPhTgO1dM7XdzIpDr9X15f9dlyqoYkbAXB94ybYzdashx+HPiwLo5n7jAeshK1sLgBM2IiAgGp/JG5+o", "ibopA");
        AltManager.lIIlIIl[AltManager.lIIllll[101]] = lIllllll("lKbMOw7d/15JXoQSZIMlKHf7cCKeM0W0rAS0ilYLxwYRx8VOzJdQUfvlQHOhpylJKg/Q8RyjLoGiHKBMzRyhKV5bteyPoci1s9EteKACxHOmvr7boEqcbw==", "YLRbO");
        AltManager.lIIlIIl[AltManager.lIIllll[102]] = lIllllll("AQbL7a9q8FjAVfiEZzgYeNaCnzqHu5eLjFYucMzK8sqYfZCIfnInOOCnSr+X8Qk19Lpzex1yqgI=", "apgcr");
        AltManager.lIIlIIl[AltManager.lIIllll[103]] = lIllllIl("PCkIfSUhPB03EWoDCyAcIT1cHgkqMRU2Gjd+MT8BIT4GfS4tPBceCSoxFTYafjcXJysoORc9HAA5AGlAbRwYMh4lfxs8RwI5HjZTfnA=", "DPrSh");
        AltManager.lIIlIIl[AltManager.lIIllll[104]] = lIllllIl("MhMOXAEvBhsWNWQ5DQE4LwdaPykmBRALYgsJFx05JB5aEyA+AxkCIGQnHRE+JRkbFDgLBgBIKy8eNREvLxkHJiMhDxpIZGMmHhM6K0UYEyItRScGPiMEE0l2ako=", "JjtrL");
        AltManager.lIIlIIl[AltManager.lIIllll[105]] = lIllllIl("LxETGGYsH0s/ISkVKgw8NQURKjw3FQQUcjICDA0tf1g+O2ETSkVZ", "EpeyH");
        AltManager.lIIlIIl[AltManager.lIIllll[106]] = lIllllll("k7CpBj2M9vV/AMS2n0xNlIpdlg1aTfUBcTLRzUg9z2E=", "FNbMK");
        AltManager.lIIlIIl[AltManager.lIIllll[107]] = lIllllll("DbM8pxYtFTw9himE6Jqm58p+KcWUGN6a2TSwntmheGGlrlKEjHUW/RgNkSf4CSr7Ks+dmKpgAkdfeCH+saQU1/RO9LR4R6blqKrDRyPhGB9pRGgBvA6UCazNIZ9+0zxfsxg7om94fF1nqLT5wsH3rqSXsdSCGDp1", "CntaV");
        AltManager.lIIlIIl[AltManager.lIIllll[108]] = lIllllll("+rofWGcB7CHLAVkrp9SYWeMyVIjvdTALgyL8s5ydkEaUeBlykyhFdbiq3Q7i9R5x", "DKfel");
        AltManager.lIIlIIl[AltManager.lIIllll[109]] = lIlllllI("FK9lqZYO4UXtIoLPsr1s6VAA9XNYiZPDBu58j/VFcKdld5elM7l9YbwIZWK5QWXuteAqP+d/+lVwfDqye29r4Q==", "JNAmc");
        AltManager.lIIlIIl[AltManager.lIIllll[110]] = lIllllll("w++YmUtubJTukX5ASOLuSSjVEYP3TgiYfXhaNx0udSU7D/bTXQRy4TE/TdWWPJ2wiB//5L/7BIOjvfLWpJ6tYQPHlQix3azlEqkDPyzeYkI=", "YXBRz");
        AltManager.lIIlIIl[AltManager.lIIllll[111]] = lIllllll("s1AcR9Fy3BvzuovE2lzUi0ThDFG6sFfUn5GkxDylLPY+4pbJbEzpMgJdmeR3kZRO21k/OMZwBI2A3+W6UKLPlUTBwi2Ujw8SCJH3Y/9MWQ4w7GbFSX17XKp/H+whnzWS", "fCCpz");
        AltManager.lIIlIIl[AltManager.lIIllll[112]] = lIlllllI("WTYl9p+GgyP0u8Bk6vRgnY+1Rpox0eM7bwx4KvlH4H0Ot65X8TUwC95bdvu1OwGNq0TZJ5MLjtoUpx7dgSGfJQ==", "YEGBa");
        AltManager.lIIlIlI = null;
    }
    
    public enum LoginStatus
    {
        private static final /* synthetic */ String[] llIlIll;
        
        FAILED, 
        SUCCESS;
        
        private static final /* synthetic */ int[] lIIIIlll;
        
        EXCEPTION(LoginStatus.llIlIll[LoginStatus.lIIIIlll[2]], LoginStatus.lIIIIlll[2]);
        
        private static /* synthetic */ String[] llIllII;
        
        static {
            lIlIlIIll();
            lIIIllIII();
            lIIIlIlll();
            final LoginStatus[] $values = new LoginStatus[LoginStatus.lIIIIlll[3]];
            $values[LoginStatus.lIIIIlll[0]] = LoginStatus.FAILED;
            $values[LoginStatus.lIIIIlll[1]] = LoginStatus.SUCCESS;
            $values[LoginStatus.lIIIIlll[2]] = LoginStatus.EXCEPTION;
            $VALUES = $values;
        }
        
        private static void lIlIlIIll() {
            (lIIIIlll = new int[7])[0] = ((0x45 ^ 0x50) << (" ".length() << " ".length()) & ~((0x1A ^ 0xF) << (" ".length() << " ".length())));
            LoginStatus.lIIIIlll[1] = " ".length();
            LoginStatus.lIIIIlll[2] = " ".length() << " ".length();
            LoginStatus.lIIIIlll[3] = "   ".length();
            LoginStatus.lIIIIlll[4] = " ".length() << (" ".length() << " ".length());
            LoginStatus.lIIIIlll[5] = (93 + 90 - 76 + 24 ^ (0xEC ^ 0xAF) << " ".length());
            LoginStatus.lIIIIlll[6] = " ".length() << "   ".length();
        }
        
        private static String lIIIlIlIl(final String lllIIIIIIlllIIl, final String lllIIIIIIlllIII) {
            try {
                final SecretKeySpec lllIIIIIIlllllI = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIIIIIIlllIII.getBytes(StandardCharsets.UTF_8)), LoginStatus.lIIIIlll[6]), "DES");
                final Cipher lllIIIIIIllllIl = Cipher.getInstance("DES");
                lllIIIIIIllllIl.init(LoginStatus.lIIIIlll[2], lllIIIIIIlllllI);
                return new String(lllIIIIIIllllIl.doFinal(Base64.getDecoder().decode(lllIIIIIIlllIIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            }
            catch (Exception lllIIIIIIllllII) {
                lllIIIIIIllllII.printStackTrace();
                return null;
            }
        }
        
        private static String lIIIlIllI(final String lllIIIIIlIIlIII, final String lllIIIIIlIIIlll) {
            try {
                final SecretKeySpec lllIIIIIlIIlIll = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIIIIIlIIIlll.getBytes(StandardCharsets.UTF_8)), "Blowfish");
                final Cipher lllIIIIIlIIlIlI = Cipher.getInstance("Blowfish");
                lllIIIIIlIIlIlI.init(LoginStatus.lIIIIlll[2], lllIIIIIlIIlIll);
                return new String(lllIIIIIlIIlIlI.doFinal(Base64.getDecoder().decode(lllIIIIIlIIlIII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            }
            catch (Exception lllIIIIIlIIlIIl) {
                lllIIIIIlIIlIIl.printStackTrace();
                return null;
            }
        }
        
        private static void lIIIllIII() {
            final boolean lllIIIIIlIlIIII = (boolean)new Exception().getStackTrace()[LoginStatus.lIIIIlll[0]].getFileName();
            LoginStatus.llIllII = ((String)lllIIIIIlIlIIII).substring(((String)lllIIIIIlIlIIII).indexOf("\u00e4") + LoginStatus.lIIIIlll[1], ((String)lllIIIIIlIlIIII).lastIndexOf("\u00fc")).split("\u00f6");
        }
        
        private static void lIIIlIlll() {
            (llIlIll = new String[LoginStatus.lIIIIlll[3]])[LoginStatus.lIIIIlll[0]] = lIIIlIlIl(LoginStatus.llIllII[LoginStatus.lIIIIlll[0]], LoginStatus.llIllII[LoginStatus.lIIIIlll[1]]);
            LoginStatus.llIlIll[LoginStatus.lIIIIlll[1]] = lIIIlIllI(LoginStatus.llIllII[LoginStatus.lIIIIlll[2]], LoginStatus.llIllII[LoginStatus.lIIIIlll[3]]);
            LoginStatus.llIlIll[LoginStatus.lIIIIlll[2]] = lIIIlIlIl(LoginStatus.llIllII[LoginStatus.lIIIIlll[4]], LoginStatus.llIllII[LoginStatus.lIIIIlll[5]]);
            LoginStatus.llIllII = null;
        }
    }
}
