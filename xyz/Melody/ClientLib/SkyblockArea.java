//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.ClientLib;

import java.lang.invoke.*;
import java.nio.charset.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;

public class SkyblockArea
{
    private static final /* synthetic */ String[] lIlllIl;
    private static /* synthetic */ String[] lIlllll;
    private static /* synthetic */ String[] lIllIlI;
    private static final /* synthetic */ int[] llIIIIl;
    private static /* synthetic */ Class[] lIllIll;
    
    private static CallSite lllIIlII(final MethodHandles.Lookup lllIlIlIlIIIllI, final String lllIlIlIlIIlIII, final MethodType lllIlIlIlIIIlll) throws IllegalAccessException, NoSuchMethodException {
        try {
            final String[] lllIlIlIlIIllll = SkyblockArea.lIllIlI[Integer.parseInt(lllIlIlIlIIlIII)].split(SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[13]]);
            final Class<?> lllIlIlIlIIlllI = Class.forName(lllIlIlIlIIllll[SkyblockArea.llIIIIl[0]]);
            final String lllIlIlIlIIllIl = lllIlIlIlIIllll[SkyblockArea.llIIIIl[1]];
            MethodHandle lllIlIlIlIIllII = null;
            final int lllIlIlIlIIlIll = lllIlIlIlIIllll[SkyblockArea.llIIIIl[3]].length();
            if (llllIIll(lllIlIlIlIIlIll, SkyblockArea.llIIIIl[2])) {
                final MethodType lllIlIlIlIlIIIl = MethodType.fromMethodDescriptorString(lllIlIlIlIIllll[SkyblockArea.llIIIIl[2]], SkyblockArea.class.getClassLoader());
                if (llllIlII(lllIlIlIlIIlIll, SkyblockArea.llIIIIl[2])) {
                    lllIlIlIlIIllII = lllIlIlIlIIIllI.findVirtual(lllIlIlIlIIlllI, lllIlIlIlIIllIl, lllIlIlIlIlIIIl);
                    "".length();
                    if (" ".length() << " ".length() <= -" ".length()) {
                        return null;
                    }
                }
                else {
                    lllIlIlIlIIllII = lllIlIlIlIIIllI.findStatic(lllIlIlIlIIlllI, lllIlIlIlIIllIl, lllIlIlIlIlIIIl);
                }
                "".length();
                if (-" ".length() > " ".length()) {
                    return null;
                }
            }
            else {
                final Class lllIlIlIlIlIIII = SkyblockArea.lIllIll[Integer.parseInt(lllIlIlIlIIllll[SkyblockArea.llIIIIl[2]])];
                if (llllIlII(lllIlIlIlIIlIll, SkyblockArea.llIIIIl[3])) {
                    lllIlIlIlIIllII = lllIlIlIlIIIllI.findGetter(lllIlIlIlIIlllI, lllIlIlIlIIllIl, lllIlIlIlIlIIII);
                    "".length();
                    if (-"   ".length() >= 0) {
                        return null;
                    }
                }
                else if (llllIlII(lllIlIlIlIIlIll, SkyblockArea.llIIIIl[4])) {
                    lllIlIlIlIIllII = lllIlIlIlIIIllI.findStaticGetter(lllIlIlIlIIlllI, lllIlIlIlIIllIl, lllIlIlIlIlIIII);
                    "".length();
                    if (null != null) {
                        return null;
                    }
                }
                else if (llllIlII(lllIlIlIlIIlIll, SkyblockArea.llIIIIl[5])) {
                    lllIlIlIlIIllII = lllIlIlIlIIIllI.findSetter(lllIlIlIlIIlllI, lllIlIlIlIIllIl, lllIlIlIlIlIIII);
                    "".length();
                    if (-" ".length() >= 0) {
                        return null;
                    }
                }
                else {
                    lllIlIlIlIIllII = lllIlIlIlIIIllI.findStaticSetter(lllIlIlIlIIlllI, lllIlIlIlIIllIl, lllIlIlIlIlIIII);
                }
            }
            return new ConstantCallSite(lllIlIlIlIIllII);
        }
        catch (Exception lllIlIlIlIIlIlI) {
            lllIlIlIlIIlIlI.printStackTrace();
            return null;
        }
    }
    
    private static boolean lllIllll(final Object lllIlIIllllllIl) {
        return lllIlIIllllllIl != null;
    }
    
    public Areas[] getAllAreas() {
        return invokedynamic(3:()[Lxyz/Melody/ClientLib/SkyblockArea$Areas;);
    }
    
    private static String lllIIlll(String lllIlIlIIlIIIII, final String lllIlIlIIIlllll) {
        lllIlIlIIlIIIII = (char)new String(Base64.getDecoder().decode(((String)lllIlIlIIlIIIII).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder lllIlIlIIlIIIll = new StringBuilder();
        final char[] lllIlIlIIlIIIlI = lllIlIlIIIlllll.toCharArray();
        int lllIlIlIIlIIIIl = SkyblockArea.llIIIIl[0];
        final Exception lllIlIlIIIllIll = (Object)((String)lllIlIlIIlIIIII).toCharArray();
        final boolean lllIlIlIIIllIlI = lllIlIlIIIllIll.length != 0;
        Exception lllIlIlIIIllIIl = (Exception)SkyblockArea.llIIIIl[0];
        while (llllIlIl((int)lllIlIlIIIllIIl, lllIlIlIIIllIlI ? 1 : 0)) {
            final char lllIlIlIIlIIllI = lllIlIlIIIllIll[lllIlIlIIIllIIl];
            lllIlIlIIlIIIll.append((char)(lllIlIlIIlIIllI ^ lllIlIlIIlIIIlI[lllIlIlIIlIIIIl % lllIlIlIIlIIIlI.length]));
            "".length();
            ++lllIlIlIIlIIIIl;
            ++lllIlIlIIIllIIl;
            "".length();
            if (" ".length() << " ".length() <= 0) {
                return null;
            }
        }
        return String.valueOf(lllIlIlIIlIIIll);
    }
    
    public void setCurrentArea(final Areas lllIlIlIllIIIll) {
    }
    // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, lllIlIlIllIIIll)
    
    static {
        lllIlllI();
        lllIlIll();
        lllIlIlI();
        lllIIllI();
    }
    
    private static void lllIlllI() {
        (llIIIIl = new int[39])[0] = ((0x62 ^ 0x49) & ~(0xF ^ 0x24));
        SkyblockArea.llIIIIl[1] = " ".length();
        SkyblockArea.llIIIIl[2] = " ".length() << " ".length();
        SkyblockArea.llIIIIl[3] = "   ".length();
        SkyblockArea.llIIIIl[4] = " ".length() << (" ".length() << " ".length());
        SkyblockArea.llIIIIl[5] = (0x7F ^ 0x7A);
        SkyblockArea.llIIIIl[6] = "   ".length() << " ".length();
        SkyblockArea.llIIIIl[7] = ((0x57 ^ 0x72) << " ".length() ^ (0xF8 ^ 0xB5));
        SkyblockArea.llIIIIl[8] = " ".length() << "   ".length();
        SkyblockArea.llIIIIl[9] = (0x48 ^ 0xF ^ (0x2E ^ 0x9) << " ".length());
        SkyblockArea.llIIIIl[10] = (86 + 31 - 54 + 104 ^ (0x1E ^ 0x4F) << " ".length()) << " ".length();
        SkyblockArea.llIIIIl[11] = (0x84 ^ 0x8F);
        SkyblockArea.llIIIIl[12] = "   ".length() << (" ".length() << " ".length());
        SkyblockArea.llIIIIl[13] = (0x97 ^ 0x9A);
        SkyblockArea.llIIIIl[14] = "   ".length() << "   ".length();
        SkyblockArea.llIIIIl[15] = ((0x6D ^ 0x50) << " ".length() ^ (0x7C ^ 0x9));
        SkyblockArea.llIIIIl[16] = (0x7 ^ 0x0) << " ".length();
        SkyblockArea.llIIIIl[17] = " ".length() << (" ".length() << (" ".length() << " ".length()));
        SkyblockArea.llIIIIl[18] = (0x5A ^ 0x4B);
        SkyblockArea.llIIIIl[19] = (0x63 ^ 0x68 ^ " ".length() << " ".length()) << " ".length();
        SkyblockArea.llIIIIl[20] = ((0x3A ^ 0x19) << " ".length() ^ (0x1D ^ 0x48));
        SkyblockArea.llIIIIl[21] = (0xE0 ^ 0x89 ^ (0xB7 ^ 0xAC) << (" ".length() << " ".length())) << (" ".length() << " ".length());
        SkyblockArea.llIIIIl[22] = (0x60 ^ 0x75);
        SkyblockArea.llIIIIl[23] = (0xBF ^ 0xB4) << " ".length();
        SkyblockArea.llIIIIl[24] = (0x1E ^ 0x9);
        SkyblockArea.llIIIIl[25] = (0xBB ^ 0xA2);
        SkyblockArea.llIIIIl[26] = ((0xAD ^ 0xA6) << " ".length() ^ (0x8F ^ 0x94)) << " ".length();
        SkyblockArea.llIIIIl[27] = (0x99 ^ 0x82);
        SkyblockArea.llIIIIl[28] = (41 + 27 - 11 + 72 ^ (0xE0 ^ 0xA3) << " ".length()) << (" ".length() << " ".length());
        SkyblockArea.llIIIIl[29] = (0x4F ^ 0x52);
        SkyblockArea.llIIIIl[30] = (0x19 ^ 0x36 ^ " ".length() << (0x7E ^ 0x7B)) << " ".length();
        SkyblockArea.llIIIIl[31] = ((0x54 ^ 0x7D) << " ".length() ^ (0xDB ^ 0x96));
        SkyblockArea.llIIIIl[32] = " ".length() << (0x74 ^ 0x71);
        SkyblockArea.llIIIIl[33] = (5 + 37 - 5 + 92 ^ (0x88 ^ 0x8D) << (0x69 ^ 0x6C));
        SkyblockArea.llIIIIl[34] = (0x97 ^ 0x86) << " ".length();
        SkyblockArea.llIIIIl[35] = (90 + 27 - 44 + 70 ^ (0x34 ^ 0x1F) << (" ".length() << " ".length()));
        SkyblockArea.llIIIIl[36] = ((0x43 ^ 0x1C) << " ".length() ^ 88 + 182 - 180 + 93) << (" ".length() << " ".length());
        SkyblockArea.llIIIIl[37] = (0x4 ^ 0x9 ^ (0x94 ^ 0x91) << "   ".length());
        SkyblockArea.llIIIIl[38] = (0x55 ^ 0x46) << " ".length();
    }
    
    private static void lllIIllI() {
        (SkyblockArea.lIllIlI = new String[SkyblockArea.llIIIIl[14]])[SkyblockArea.llIIIIl[15]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[16]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[8]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[15]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[13]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[17]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[4]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[18]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[9]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[19]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[6]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[20]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[7]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[21]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[2]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[22]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[17]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[23]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[20]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[24]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[0]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[14]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[11]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[25]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[19]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[26]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[21]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[27]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[16]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[28]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[24]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[29]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[3]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[30]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[1]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[31]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[10]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[32]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[18]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[33]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[22]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[34]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[5]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[35]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[23]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[36]];
        SkyblockArea.lIllIlI[SkyblockArea.llIIIIl[12]] = SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[37]];
        (SkyblockArea.lIllIll = new Class[SkyblockArea.llIIIIl[4]])[SkyblockArea.llIIIIl[2]] = EntityPlayerSP.class;
        SkyblockArea.lIllIll[SkyblockArea.llIIIIl[0]] = Areas.class;
        SkyblockArea.lIllIll[SkyblockArea.llIIIIl[3]] = Boolean.TYPE;
        SkyblockArea.lIllIll[SkyblockArea.llIIIIl[1]] = WorldClient.class;
    }
    
    public void updateCurrentArea() {
        if (!lllIllll(invokedynamic(5:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/multiplayer/WorldClient;, invokedynamic(4:()Lnet/minecraft/client/Minecraft;))) || llllIIII(invokedynamic(6:(Lnet/minecraft/client/Minecraft;)Lnet/minecraft/client/entity/EntityPlayerSP;, invokedynamic(4:()Lnet/minecraft/client/Minecraft;)))) {
            return;
        }
        if (llllIIIl(invokedynamic(7:()Z))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(0:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[0]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(9:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[1]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(10:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[2]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(11:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[3]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(12:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[4]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(13:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[5]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(14:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[6]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(15:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[7]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(16:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[8]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(17:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[9]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(18:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[10]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(19:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[11]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(20:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(8:(Ljava/lang/String;)Z, SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[12]]))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(21:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
        if (llllIIlI(invokedynamic(22:()Z))) {
            // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(23:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
            return;
        }
    }
    // invokedynamic(1:(Lxyz/Melody/ClientLib/SkyblockArea;Lxyz/Melody/ClientLib/SkyblockArea$Areas;)V, this, invokedynamic(0:()Lxyz/Melody/ClientLib/SkyblockArea$Areas;))
    
    private static boolean llllIIlI(final int lllIlIIlllllIIl) {
        return lllIlIIlllllIIl != 0;
    }
    
    private static void lllIlIll() {
        final String lllIlIlIIllllIl = new Exception().getStackTrace()[SkyblockArea.llIIIIl[0]].getFileName();
        SkyblockArea.lIlllll = lllIlIlIIllllIl.substring(lllIlIlIIllllIl.indexOf("\u00e4") + SkyblockArea.llIIIIl[1], lllIlIlIIllllIl.lastIndexOf("\u00fc")).split("\u00f6");
    }
    
    private static boolean llllIIll(final int lllIlIlIIIIIIII, final int lllIlIIllllllll) {
        return lllIlIlIIIIIIII <= lllIlIIllllllll;
    }
    
    private static String lllIlIIl(final String lllIlIlIIllIlIl, final String lllIlIlIIllIIlI) {
        try {
            final SecretKeySpec lllIlIlIIlllIII = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIlIlIIllIIlI.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher lllIlIlIIllIlll = Cipher.getInstance("Blowfish");
            lllIlIlIIllIlll.init(SkyblockArea.llIIIIl[2], lllIlIlIIlllIII);
            return new String(lllIlIlIIllIlll.doFinal(Base64.getDecoder().decode(lllIlIlIIllIlIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIlIlIIllIllI) {
            lllIlIlIIllIllI.printStackTrace();
            return null;
        }
    }
    
    public Areas getCurrentArea() {
        return invokedynamic(2:(Lxyz/Melody/ClientLib/SkyblockArea;)Lxyz/Melody/ClientLib/SkyblockArea$Areas;, this);
    }
    
    private static void lllIlIlI() {
        (lIlllIl = new String[SkyblockArea.llIIIIl[38]])[SkyblockArea.llIIIIl[0]] = lllIIlll(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[0]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[1]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[1]] = lllIlIII(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[2]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[3]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[2]] = lllIlIIl(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[4]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[5]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[3]] = lllIlIII(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[6]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[7]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[4]] = lllIlIII(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[8]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[9]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[5]] = lllIlIIl(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[10]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[11]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[6]] = lllIlIII(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[12]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[13]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[7]] = lllIlIIl(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[16]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[15]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[8]] = lllIlIII(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[17]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[18]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[9]] = lllIlIIl(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[19]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[20]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[10]] = lllIlIIl(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[21]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[22]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[11]] = lllIlIIl(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[23]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[24]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[12]] = lllIlIIl(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[14]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[25]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[13]] = lllIIlll(SkyblockArea.lIlllll[SkyblockArea.llIIIIl[26]], SkyblockArea.lIlllll[SkyblockArea.llIIIIl[27]]);
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[16]] = lllIlIIl("Aluv+QHtjrtRjV7B/4fGLvke9gKb7uZPniv2k1NSdlxyGyO1MDFHGNkN0NaYsWdJ7ZBXNoHvZTC0rgp/MhVt6g==", "GuokO");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[15]] = lllIlIIl("MnqW80sz+qQCXzWZ5797DafFA5+ng3aCUq4fc5/9Pm1yKJNEHMiVefFY31Xti57Q9cNsxrWoxDY6OAPiO0A9pY+ElVgjXgl2", "nWzgU");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[17]] = lllIlIIl("Zup5Inj9xCog62qoVkOWvG9K2L0EJEtbnp/KwCE0LT0cx4TuQbGUnPDDexr9yApfodOBollpCA4=", "UiQeM");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[18]] = lllIlIIl("uuHidhW5SylGG4GzWJRtYFkA+4qrEqKv0hGceZawt/fu+bb5X0RdDnilnbBWLzDLdNxVsxKGRwFXiZOAYMpABNzspLpPNSfK0U1kykLgDOkh7z4weog0Wg==", "MrkyE");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[19]] = lllIlIII("nKzVndxZl4o2E8DU2zl2gxNZqYQDp9Kyd+8tKKVO8IkqW46amUqoZkMUrO+tfjxU/vj7IiVebAU=", "lamIm");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[20]] = lllIIlll("LwgyTDUoAyMBKiALMkw7LQQjDCxvIC8MPSIfJwQsewsvBzQlMnFTbHJUGQVic1dmQng=", "AmFbX");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[21]] = lllIIlll("HRE3agYABCIgMksrIS0uCxx3LSU2AzQmJwoLJn54X0htZGs=", "ehMDK");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[22]] = lllIlIIl("uBu2rtTE0+tRMk/vaCRfbsaD4EecwyZBgbwJflqeypYoeX+1hhZcLWGei45YRFRpgmynlLaewBg=", "toxgU");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[23]] = lllIlIII("1UrlCLFueUvUflJuGAciBcybhZKFn7v9x8WXu7SyfFgXqMsU6W8tc3eq7S0oo/pq/jRNvGGDqMV+lIGA8DUivA==", "outBc");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[24]] = lllIlIIl("nnI2y3cUJUl1nyDSL0PMWe1y4sIBCpxI6ihz8PGpW9hpFIy+GzYJDv10vmoEu/QL78jd9s1+xLJRkIJzOTyrgepIvmDsM/rj", "wqDLp");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[14]] = lllIlIIl("8E4k6yY1IweF+4DspusGKlxrsXODcRMdc5EIk1m1nxh6ioVOw5xCWlj3iam64O1Ml8uLDCYKACk=", "nnqll");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[25]] = lllIlIIl("vgzwx2Npi07O4C/RAjGATABUmD4aEBv7/9dSGSYaBeN5C67RPNrHrQe6jiCO8gWXQ4X5NUs6mEZxLq1r/0Mg6w==", "MeccS");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[26]] = lllIIlll("AjQYbAEfIQ0mNVQODispFDkuKy5UHgk7LhYiASkNCCgDZg0IKAMxdj0iDiYTNyQMJ3ZKd0JibFo=", "zMbBL");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[27]] = lllIlIIl("7h6Ut4EQ/a8EUQaIJ5RxKWOofojgKrOxzQlRirmaVXgECiEAmMn/rm+MPyd8Kpyrmdo7GUK6+rTa4pKrlADjHQ==", "wXkIw");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[28]] = lllIlIII("iEnp5HsFIYM8zjQuGwiFavc57aJBy9xmbFeVZ3iv6y286H3gVdtPYvq3kjpfRyYrJoKeY34gAS4=", "tdiIs");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[29]] = lllIlIIl("Kw+ccwYOtrV4E6WR6x3jbvNoW9ly9+8jg+s/emy2dVBypuwIPyaAZ8gqyZSHGt0FccT+xMcDZdHv08QL9ZziMw==", "McXzF");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[30]] = lllIIlll("ACM2ZAMdNiMuN1YZICMrFi4AIyxWCSczLBQ1LyEPCj8tbg8KPy05dA47ID8rC2BkYxU0IjUwYTU/ICUqAXUPJicdNDgGJxp1HyE3GjYjKSU5KCkrajkoKSs9Q2Bs", "xZLJN");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[31]] = lllIIlll("FAsraicJHj4gE0IxPS0PAgYdLQhCITo9CAAdMi8rHhcwfgkZACMhBBgzIyELVkJrZEpMUnE=", "lrQDj");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[32]] = lllIIlll("HD4reAkBKz4yPUoEPT8hCjMdPyZKFDovJggoMj0FFiIwcgUWIjAlfi4iIyQ9FxgGOTYPFDk5NF53a3ZkRGc=", "dGQVD");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[33]] = lllIlIIl("nTrb4K6uzfrtadSfMDkNORUqPspIt/cnYbKlJI+L9Fc6v0qGNwXR/74iv9+DX6Z9XwW/FH21gNl25T+jn08jdA==", "xYEXN");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[34]] = lllIlIII("YDEJ4RN60ozzR4o0wJcOKOurkn7CMZeLwbkvVSBhxcNO0LENGrSBPRPiSX6EEFdmTDhI0Umfs8hxn1eDc3PbPg==", "KIpFo");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[35]] = lllIlIIl("iSSWOfNNkRD3kpgio+bY7bgk8LNXFjkmpuMc09ctgJ6qnRVhcn3WJp0wm1tVsi0JSX0CZ3CMkoo=", "fBfBc");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[36]] = lllIlIII("z43oDY5s58AlCxxmxEsC7r8FW/aMW4w/lsNQhn/LsfG2C/Ag4QPrTA==", "dwRNy");
        SkyblockArea.lIlllIl[SkyblockArea.llIIIIl[37]] = lllIIlll("HwkCRhkCHBcMLUkzFAExCQQ0ATZJIxMRNgsfGwMVFRUZTBUVFRkbbjQAEQwxFS88DTpdQEJIdEdQ", "gpxhT");
        SkyblockArea.lIlllll = null;
    }
    
    private static boolean llllIIIl(final int lllIlIIllllIlll) {
        return lllIlIIllllIlll == 0;
    }
    
    private static String lllIlIII(final String lllIlIlIIIlIIII, final String lllIlIlIIIIllll) {
        try {
            final SecretKeySpec lllIlIlIIIlIIll = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIlIlIIIIllll.getBytes(StandardCharsets.UTF_8)), SkyblockArea.llIIIIl[8]), "DES");
            final Cipher lllIlIlIIIlIIlI = Cipher.getInstance("DES");
            lllIlIlIIIlIIlI.init(SkyblockArea.llIIIIl[2], lllIlIlIIIlIIll);
            return new String(lllIlIlIIIlIIlI.doFinal(Base64.getDecoder().decode(lllIlIlIIIlIIII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIlIlIIIlIIIl) {
            lllIlIlIIIlIIIl.printStackTrace();
            return null;
        }
    }
    
    private static boolean llllIlIl(final int lllIlIlIIIIIlII, final int lllIlIlIIIIIIll) {
        return lllIlIlIIIIIlII < lllIlIlIIIIIIll;
    }
    
    private static boolean llllIlII(final int lllIlIlIIIIlIII, final int lllIlIlIIIIIlll) {
        return lllIlIlIIIIlIII == lllIlIlIIIIIlll;
    }
    
    private static boolean llllIIII(final Object lllIlIIlllllIll) {
        return lllIlIIlllllIll == null;
    }
    
    public enum Areas
    {
        Jerrys_WorkShop, 
        HUB, 
        Spider_Den;
        
        private static /* synthetic */ String[] lIlIIll;
        
        Gold_Mine, 
        The_Farming_Island, 
        Crystal_Hollows, 
        Dungeon_HUB, 
        Deep_Caverns, 
        Crimson_Island, 
        Private_Island;
        
        private static final /* synthetic */ String[] lIlIIlI;
        
        The_End, 
        NULL, 
        The_Park, 
        In_Dungeon, 
        Dwarven_Mines;
        
        private static final /* synthetic */ int[] lIlIlII;
        
        private static void llIlIIll() {
            (lIlIIlI = new String[Areas.lIlIlII[15]])[Areas.lIlIlII[0]] = llIlIIII(Areas.lIlIIll[Areas.lIlIlII[0]], Areas.lIlIIll[Areas.lIlIlII[1]]);
            Areas.lIlIIlI[Areas.lIlIlII[1]] = llIlIIIl(Areas.lIlIIll[Areas.lIlIlII[2]], Areas.lIlIIll[Areas.lIlIlII[3]]);
            Areas.lIlIIlI[Areas.lIlIlII[2]] = llIlIIII(Areas.lIlIIll[Areas.lIlIlII[4]], Areas.lIlIIll[Areas.lIlIlII[5]]);
            Areas.lIlIIlI[Areas.lIlIlII[3]] = llIlIIII(Areas.lIlIIll[Areas.lIlIlII[6]], Areas.lIlIIll[Areas.lIlIlII[7]]);
            Areas.lIlIIlI[Areas.lIlIlII[4]] = llIlIIIl(Areas.lIlIIll[Areas.lIlIlII[8]], Areas.lIlIIll[Areas.lIlIlII[9]]);
            Areas.lIlIIlI[Areas.lIlIlII[5]] = llIlIIlI(Areas.lIlIIll[Areas.lIlIlII[10]], Areas.lIlIIll[Areas.lIlIlII[11]]);
            Areas.lIlIIlI[Areas.lIlIlII[6]] = llIlIIlI(Areas.lIlIIll[Areas.lIlIlII[12]], Areas.lIlIIll[Areas.lIlIlII[13]]);
            Areas.lIlIIlI[Areas.lIlIlII[7]] = llIlIIIl(Areas.lIlIIll[Areas.lIlIlII[14]], Areas.lIlIIll[Areas.lIlIlII[15]]);
            Areas.lIlIIlI[Areas.lIlIlII[8]] = llIlIIII(Areas.lIlIIll[Areas.lIlIlII[16]], Areas.lIlIIll[Areas.lIlIlII[17]]);
            Areas.lIlIIlI[Areas.lIlIlII[9]] = llIlIIII(Areas.lIlIIll[Areas.lIlIlII[18]], Areas.lIlIIll[Areas.lIlIlII[19]]);
            Areas.lIlIIlI[Areas.lIlIlII[10]] = llIlIIlI(Areas.lIlIIll[Areas.lIlIlII[20]], Areas.lIlIIll[Areas.lIlIlII[21]]);
            Areas.lIlIIlI[Areas.lIlIlII[11]] = llIlIIII(Areas.lIlIIll[Areas.lIlIlII[22]], Areas.lIlIIll[Areas.lIlIlII[23]]);
            Areas.lIlIIlI[Areas.lIlIlII[12]] = llIlIIlI(Areas.lIlIIll[Areas.lIlIlII[24]], Areas.lIlIIll[Areas.lIlIlII[25]]);
            Areas.lIlIIlI[Areas.lIlIlII[13]] = llIlIIlI(Areas.lIlIIll[Areas.lIlIlII[26]], Areas.lIlIIll[Areas.lIlIlII[27]]);
            Areas.lIlIIlI[Areas.lIlIlII[14]] = llIlIIlI(Areas.lIlIIll[Areas.lIlIlII[28]], Areas.lIlIIll[Areas.lIlIlII[29]]);
            Areas.lIlIIll = null;
        }
        
        private static String llIlIIII(final String lllIlIlllllIlII, final String lllIlIlllllIlIl) {
            try {
                final SecretKeySpec lllIlIllllllIIl = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIlIlllllIlIl.getBytes(StandardCharsets.UTF_8)), "Blowfish");
                final Cipher lllIlIllllllIII = Cipher.getInstance("Blowfish");
                lllIlIllllllIII.init(Areas.lIlIlII[2], lllIlIllllllIIl);
                return new String(lllIlIllllllIII.doFinal(Base64.getDecoder().decode(lllIlIlllllIlII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            }
            catch (Exception lllIlIlllllIlll) {
                lllIlIlllllIlll.printStackTrace();
                return null;
            }
        }
        
        private static String llIlIIlI(String lllIlIlllIlIlII, final String lllIlIlllIllIII) {
            lllIlIlllIlIlII = (double)new String(Base64.getDecoder().decode(((String)lllIlIlllIlIlII).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            final StringBuilder lllIlIlllIlIlll = new StringBuilder();
            final char[] lllIlIlllIlIllI = lllIlIlllIllIII.toCharArray();
            int lllIlIlllIlIlIl = Areas.lIlIlII[0];
            final long lllIlIlllIIllll = (Object)((String)lllIlIlllIlIlII).toCharArray();
            final Exception lllIlIlllIIlllI = (Exception)lllIlIlllIIllll.length;
            boolean lllIlIlllIIllIl = Areas.lIlIlII[0] != 0;
            while (llIllIIl(lllIlIlllIIllIl ? 1 : 0, (int)lllIlIlllIIlllI)) {
                final char lllIlIlllIllIlI = lllIlIlllIIllll[lllIlIlllIIllIl];
                lllIlIlllIlIlll.append((char)(lllIlIlllIllIlI ^ lllIlIlllIlIllI[lllIlIlllIlIlIl % lllIlIlllIlIllI.length]));
                "".length();
                ++lllIlIlllIlIlIl;
                ++lllIlIlllIIllIl;
                "".length();
                if (" ".length() << (" ".length() << " ".length()) == 0) {
                    return null;
                }
            }
            return String.valueOf(lllIlIlllIlIlll);
        }
        
        private static void llIlIlII() {
            final long lllIlIllllllllI = (long)new Exception().getStackTrace()[Areas.lIlIlII[0]].getFileName();
            Areas.lIlIIll = ((String)lllIlIllllllllI).substring(((String)lllIlIllllllllI).indexOf("\u00e4") + Areas.lIlIlII[1], ((String)lllIlIllllllllI).lastIndexOf("\u00fc")).split("\u00f6");
        }
        
        private static String llIlIIIl(final String lllIlIllllIlIIl, final String lllIlIllllIIllI) {
            try {
                final SecretKeySpec lllIlIllllIllII = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIlIllllIIllI.getBytes(StandardCharsets.UTF_8)), Areas.lIlIlII[8]), "DES");
                final Cipher lllIlIllllIlIll = Cipher.getInstance("DES");
                lllIlIllllIlIll.init(Areas.lIlIlII[2], lllIlIllllIllII);
                return new String(lllIlIllllIlIll.doFinal(Base64.getDecoder().decode(lllIlIllllIlIIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            }
            catch (Exception lllIlIllllIlIlI) {
                lllIlIllllIlIlI.printStackTrace();
                return null;
            }
        }
        
        static {
            llIlIlIl();
            llIlIlII();
            llIlIIll();
            final Areas[] $values = new Areas[Areas.lIlIlII[15]];
            $values[Areas.lIlIlII[0]] = Areas.NULL;
            $values[Areas.lIlIlII[1]] = Areas.Jerrys_WorkShop;
            $values[Areas.lIlIlII[2]] = Areas.Private_Island;
            $values[Areas.lIlIlII[3]] = Areas.HUB;
            $values[Areas.lIlIlII[4]] = Areas.Spider_Den;
            $values[Areas.lIlIlII[5]] = Areas.The_End;
            $values[Areas.lIlIlII[6]] = Areas.Dungeon_HUB;
            $values[Areas.lIlIlII[7]] = Areas.The_Park;
            $values[Areas.lIlIlII[8]] = Areas.Deep_Caverns;
            $values[Areas.lIlIlII[9]] = Areas.Dwarven_Mines;
            $values[Areas.lIlIlII[10]] = Areas.Gold_Mine;
            $values[Areas.lIlIlII[11]] = Areas.The_Farming_Island;
            $values[Areas.lIlIlII[12]] = Areas.Crimson_Island;
            $values[Areas.lIlIlII[13]] = Areas.Crystal_Hollows;
            $values[Areas.lIlIlII[14]] = Areas.In_Dungeon;
            $VALUES = $values;
        }
        
        private static boolean llIllIIl(final int lllIlIlllIIlIIl, final int lllIlIlllIIlIII) {
            return lllIlIlllIIlIIl < lllIlIlllIIlIII;
        }
        
        private static void llIlIlIl() {
            (lIlIlII = new int[30])[0] = ((0xE0 ^ 0x9B ^ "   ".length() << "   ".length()) & (0xFD ^ 0x98 ^ "   ".length() << " ".length() ^ -" ".length()));
            Areas.lIlIlII[1] = " ".length();
            Areas.lIlIlII[2] = " ".length() << " ".length();
            Areas.lIlIlII[3] = "   ".length();
            Areas.lIlIlII[4] = " ".length() << (" ".length() << " ".length());
            Areas.lIlIlII[5] = ((0x27 ^ 0x6A) << " ".length() ^ 142 + 1 - 103 + 119);
            Areas.lIlIlII[6] = "   ".length() << " ".length();
            Areas.lIlIlII[7] = (0xB4 ^ 0xB3);
            Areas.lIlIlII[8] = " ".length() << "   ".length();
            Areas.lIlIlII[9] = (0x47 ^ 0xC ^ (0x63 ^ 0x42) << " ".length());
            Areas.lIlIlII[10] = (0x8A ^ 0x8F) << " ".length();
            Areas.lIlIlII[11] = (0xA9 ^ 0x90 ^ (0x12 ^ 0xB) << " ".length());
            Areas.lIlIlII[12] = "   ".length() << (" ".length() << " ".length());
            Areas.lIlIlII[13] = ((0x64 ^ 0x6F) << "   ".length() ^ (0x33 ^ 0x66));
            Areas.lIlIlII[14] = ((0x2C ^ 0x33) << " ".length() ^ (0xB ^ 0x32)) << " ".length();
            Areas.lIlIlII[15] = (0x8B ^ 0x84);
            Areas.lIlIlII[16] = " ".length() << (" ".length() << (" ".length() << " ".length()));
            Areas.lIlIlII[17] = ((0xA7 ^ 0xAC) << (" ".length() << (" ".length() << " ".length())) ^ 92 + 125 - 108 + 52);
            Areas.lIlIlII[18] = (0x55 ^ 0x5C) << " ".length();
            Areas.lIlIlII[19] = ((0x54 ^ 0x51) << (" ".length() << " ".length()) ^ (0x2E ^ 0x29));
            Areas.lIlIlII[20] = ((0x7D ^ 0x78) << "   ".length() ^ (0x6F ^ 0x42)) << (" ".length() << " ".length());
            Areas.lIlIlII[21] = (0x20 ^ 0x35);
            Areas.lIlIlII[22] = (0x91 ^ 0x9A) << " ".length();
            Areas.lIlIlII[23] = (0x21 ^ 0x36);
            Areas.lIlIlII[24] = "   ".length() << "   ".length();
            Areas.lIlIlII[25] = (90 + 16 + 8 + 25 ^ (0xCF ^ 0x86) << " ".length());
            Areas.lIlIlII[26] = (0x3 ^ 0xE) << " ".length();
            Areas.lIlIlII[27] = (0x4C ^ 0x57);
            Areas.lIlIlII[28] = (0x8F ^ 0x88) << (" ".length() << " ".length());
            Areas.lIlIlII[29] = (0x6D ^ 0x70);
        }
    }
}
