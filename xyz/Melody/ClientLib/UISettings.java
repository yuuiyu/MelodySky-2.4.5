//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.ClientLib;

import java.lang.invoke.*;
import java.nio.charset.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;

public class UISettings
{
    private static /* synthetic */ String[] lIIlIll;
    private static /* synthetic */ String[] lIIlllI;
    private static final /* synthetic */ String[] lIIllIl;
    private static final /* synthetic */ int[] lIlIlIl;
    private static /* synthetic */ Class[] lIIllII;
    
    static {
        llIlIllI();
        llIIlIII();
        llIIIlll();
        llIIIIll();
    }
    // invokedynamic(2:(Ljava/util/ArrayList;)V, new ArrayList())
    // invokedynamic(3:(Z)V, UISettings.lIlIlIl[1])
    // invokedynamic(4:(Z)V, UISettings.lIlIlIl[1])
    // invokedynamic(5:(Z)V, UISettings.lIlIlIl[1])
    
    private static void llIIIlll() {
        (lIIllIl = new String[UISettings.lIlIlIl[10]])[UISettings.lIlIlIl[0]] = llIIIlII(UISettings.lIIlllI[UISettings.lIlIlIl[0]], UISettings.lIIlllI[UISettings.lIlIlIl[1]]);
        UISettings.lIIllIl[UISettings.lIlIlIl[1]] = llIIIlIl(UISettings.lIIlllI[UISettings.lIlIlIl[2]], UISettings.lIIlllI[UISettings.lIlIlIl[3]]);
        UISettings.lIIllIl[UISettings.lIlIlIl[2]] = llIIIllI(UISettings.lIIlllI[UISettings.lIlIlIl[4]], UISettings.lIIlllI[UISettings.lIlIlIl[5]]);
        UISettings.lIIllIl[UISettings.lIlIlIl[3]] = llIIIlII(UISettings.lIIlllI[UISettings.lIlIlIl[6]], UISettings.lIIlllI[UISettings.lIlIlIl[7]]);
        UISettings.lIIllIl[UISettings.lIlIlIl[4]] = llIIIllI(UISettings.lIIlllI[UISettings.lIlIlIl[8]], UISettings.lIIlllI[UISettings.lIlIlIl[9]]);
        UISettings.lIIllIl[UISettings.lIlIlIl[5]] = llIIIlIl(UISettings.lIIlllI[UISettings.lIlIlIl[10]], UISettings.lIIlllI[UISettings.lIlIlIl[11]]);
        UISettings.lIIllIl[UISettings.lIlIlIl[6]] = llIIIlII(UISettings.lIIlllI[UISettings.lIlIlIl[12]], UISettings.lIIlllI[UISettings.lIlIlIl[13]]);
        UISettings.lIIllIl[UISettings.lIlIlIl[7]] = llIIIlIl(UISettings.lIIlllI[UISettings.lIlIlIl[14]], UISettings.lIIlllI[UISettings.lIlIlIl[15]]);
        UISettings.lIIllIl[UISettings.lIlIlIl[8]] = llIIIlIl("IBvbH8mg5mmHTZgJqIdJkcAYqN5XUG4RZQr9/ymtmTcAWBz9M3V436La26zaP6J12HYUV4m86CVgLBCppAJ38A==", "KFWhO");
        UISettings.lIIllIl[UISettings.lIlIlIl[9]] = llIIIlII("e2HA3dc9Orz7MKR2psgUIcm5fmoc4Q/D/tnfBOZ0/+KK8oDgsoedDjebnHptNMqS8/A7Q88+Ik4=", "ZZxjm");
        UISettings.lIIlllI = null;
    }
    
    private static void llIIIIll() {
        (UISettings.lIIlIll = new String[UISettings.lIlIlIl[6]])[UISettings.lIlIlIl[0]] = UISettings.lIIllIl[UISettings.lIlIlIl[4]];
        UISettings.lIIlIll[UISettings.lIlIlIl[2]] = UISettings.lIIllIl[UISettings.lIlIlIl[5]];
        UISettings.lIIlIll[UISettings.lIlIlIl[1]] = UISettings.lIIllIl[UISettings.lIlIlIl[6]];
        UISettings.lIIlIll[UISettings.lIlIlIl[4]] = UISettings.lIIllIl[UISettings.lIlIlIl[7]];
        UISettings.lIIlIll[UISettings.lIlIlIl[5]] = UISettings.lIIllIl[UISettings.lIlIlIl[8]];
        UISettings.lIIlIll[UISettings.lIlIlIl[3]] = UISettings.lIIllIl[UISettings.lIlIlIl[9]];
        (UISettings.lIIllII = new Class[UISettings.lIlIlIl[2]])[UISettings.lIlIlIl[1]] = Boolean.TYPE;
        UISettings.lIIllII[UISettings.lIlIlIl[0]] = ArrayList.class;
    }
    
    private static CallSite llIIIIIl(final MethodHandles.Lookup lllIlIllIllIlII, final String lllIlIllIllIIII, final MethodType lllIlIllIllIIlI) throws IllegalAccessException, NoSuchMethodException {
        try {
            final String[] lllIlIllIlllIlI = UISettings.lIIlIll[Integer.parseInt(lllIlIllIllIIII)].split(UISettings.lIIllIl[UISettings.lIlIlIl[3]]);
            final Class<?> lllIlIllIlllIIl = Class.forName(lllIlIllIlllIlI[UISettings.lIlIlIl[0]]);
            final String lllIlIllIlllIII = lllIlIllIlllIlI[UISettings.lIlIlIl[1]];
            MethodHandle lllIlIllIllIlll = null;
            final int lllIlIllIllIllI = lllIlIllIlllIlI[UISettings.lIlIlIl[3]].length();
            if (llIlIlll(lllIlIllIllIllI, UISettings.lIlIlIl[2])) {
                final MethodType lllIlIllIllllII = MethodType.fromMethodDescriptorString(lllIlIllIlllIlI[UISettings.lIlIlIl[2]], UISettings.class.getClassLoader());
                if (llIllIII(lllIlIllIllIllI, UISettings.lIlIlIl[2])) {
                    lllIlIllIllIlll = lllIlIllIllIlII.findVirtual(lllIlIllIlllIIl, lllIlIllIlllIII, lllIlIllIllllII);
                    "".length();
                    if (" ".length() << (" ".length() << " ".length()) < " ".length() << " ".length()) {
                        return null;
                    }
                }
                else {
                    lllIlIllIllIlll = lllIlIllIllIlII.findStatic(lllIlIllIlllIIl, lllIlIllIlllIII, lllIlIllIllllII);
                }
                "".length();
                if (null != null) {
                    return null;
                }
            }
            else {
                final Class lllIlIllIlllIll = UISettings.lIIllII[Integer.parseInt(lllIlIllIlllIlI[UISettings.lIlIlIl[2]])];
                if (llIllIII(lllIlIllIllIllI, UISettings.lIlIlIl[3])) {
                    lllIlIllIllIlll = lllIlIllIllIlII.findGetter(lllIlIllIlllIIl, lllIlIllIlllIII, lllIlIllIlllIll);
                    "".length();
                    if ((" ".length() << "   ".length() & (" ".length() << "   ".length() ^ -" ".length())) != 0x0) {
                        return null;
                    }
                }
                else if (llIllIII(lllIlIllIllIllI, UISettings.lIlIlIl[4])) {
                    lllIlIllIllIlll = lllIlIllIllIlII.findStaticGetter(lllIlIllIlllIIl, lllIlIllIlllIII, lllIlIllIlllIll);
                    "".length();
                    if (-" ".length() > " ".length() << (" ".length() << " ".length())) {
                        return null;
                    }
                }
                else if (llIllIII(lllIlIllIllIllI, UISettings.lIlIlIl[5])) {
                    lllIlIllIllIlll = lllIlIllIllIlII.findSetter(lllIlIllIlllIIl, lllIlIllIlllIII, lllIlIllIlllIll);
                    "".length();
                    if (-"  ".length() > 0) {
                        return null;
                    }
                }
                else {
                    lllIlIllIllIlll = lllIlIllIllIlII.findStaticSetter(lllIlIllIlllIIl, lllIlIllIlllIII, lllIlIllIlllIll);
                }
            }
            return new ConstantCallSite(lllIlIllIllIlll);
        }
        catch (Exception lllIlIllIllIlIl) {
            lllIlIllIllIlIl.printStackTrace();
            return null;
        }
    }
    
    private static String llIIIlII(final String lllIlIlIllllIll, final String lllIlIlIllllIlI) {
        try {
            final SecretKeySpec lllIlIlIllllllI = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIlIlIllllIlI.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher lllIlIlIlllllIl = Cipher.getInstance("Blowfish");
            lllIlIlIlllllIl.init(UISettings.lIlIlIl[2], lllIlIlIllllllI);
            return new String(lllIlIlIlllllIl.doFinal(Base64.getDecoder().decode(lllIlIlIllllIll.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIlIlIlllllII) {
            lllIlIlIlllllII.printStackTrace();
            return null;
        }
    }
    
    private static boolean llIllIlI(final int lllIlIlIllIllll, final int lllIlIlIllIlllI) {
        return lllIlIlIllIllll < lllIlIlIllIlllI;
    }
    
    private static String llIIIlIl(final String lllIlIllIIIlIII, final String lllIlIllIIIIlll) {
        try {
            final SecretKeySpec lllIlIllIIIlIll = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIlIllIIIIlll.getBytes(StandardCharsets.UTF_8)), UISettings.lIlIlIl[8]), "DES");
            final Cipher lllIlIllIIIlIlI = Cipher.getInstance("DES");
            lllIlIllIIIlIlI.init(UISettings.lIlIlIl[2], lllIlIllIIIlIll);
            return new String(lllIlIllIIIlIlI.doFinal(Base64.getDecoder().decode(lllIlIllIIIlIII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIlIllIIIlIIl) {
            lllIlIllIIIlIIl.printStackTrace();
            return null;
        }
    }
    
    public static void init() {
        // invokedynamic(1:(Ljava/util/ArrayList;Ljava/lang/Object;)Z, invokedynamic(0:()Ljava/util/ArrayList;), UISettings.lIIllIl[UISettings.lIlIlIl[0]])
        "".length();
        // invokedynamic(1:(Ljava/util/ArrayList;Ljava/lang/Object;)Z, invokedynamic(0:()Ljava/util/ArrayList;), UISettings.lIIllIl[UISettings.lIlIlIl[1]])
        "".length();
        // invokedynamic(1:(Ljava/util/ArrayList;Ljava/lang/Object;)Z, invokedynamic(0:()Ljava/util/ArrayList;), UISettings.lIIllIl[UISettings.lIlIlIl[2]])
        "".length();
    }
    
    private static String llIIIllI(String lllIlIllIIllIII, final String lllIlIllIIlllII) {
        lllIlIllIIllIII = (boolean)new String(Base64.getDecoder().decode(((String)lllIlIllIIllIII).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder lllIlIllIIllIll = new StringBuilder();
        final char[] lllIlIllIIllIlI = lllIlIllIIlllII.toCharArray();
        int lllIlIllIIllIIl = UISettings.lIlIlIl[0];
        final long lllIlIllIIlIIll = (Object)((String)lllIlIllIIllIII).toCharArray();
        final boolean lllIlIllIIlIIlI = lllIlIllIIlIIll.length != 0;
        byte lllIlIllIIlIIIl = (byte)UISettings.lIlIlIl[0];
        while (llIllIlI(lllIlIllIIlIIIl, lllIlIllIIlIIlI ? 1 : 0)) {
            final char lllIlIllIIllllI = lllIlIllIIlIIll[lllIlIllIIlIIIl];
            lllIlIllIIllIll.append((char)(lllIlIllIIllllI ^ lllIlIllIIllIlI[lllIlIllIIllIIl % lllIlIllIIllIlI.length]));
            "".length();
            ++lllIlIllIIllIIl;
            ++lllIlIllIIlIIIl;
            "".length();
            if (" ".length() << (" ".length() << " ".length()) < " ".length() << " ".length()) {
                return null;
            }
        }
        return String.valueOf(lllIlIllIIllIll);
    }
    
    private static void llIIlIII() {
        final short lllIlIllIlIlIII = (short)new Exception().getStackTrace()[UISettings.lIlIlIl[0]].getFileName();
        UISettings.lIIlllI = ((String)lllIlIllIlIlIII).substring(((String)lllIlIllIlIlIII).indexOf("\u00e4") + UISettings.lIlIlIl[1], ((String)lllIlIllIlIlIII).lastIndexOf("\u00fc")).split("\u00f6");
    }
    
    private static boolean llIllIII(final int lllIlIlIlllIIll, final int lllIlIlIlllIIlI) {
        return lllIlIlIlllIIll == lllIlIlIlllIIlI;
    }
    
    private static void llIlIllI() {
        (lIlIlIl = new int[16])[0] = (((0x63 ^ 0x6E) << " ".length() ^ (0xAB ^ 0x8E)) & (114 + 136 - 164 + 69 ^ (0x96 ^ 0xBF) << (" ".length() << " ".length()) ^ -" ".length()));
        UISettings.lIlIlIl[1] = " ".length();
        UISettings.lIlIlIl[2] = " ".length() << " ".length();
        UISettings.lIlIlIl[3] = "   ".length();
        UISettings.lIlIlIl[4] = " ".length() << (" ".length() << " ".length());
        UISettings.lIlIlIl[5] = ((0x5E ^ 0x5) << " ".length() ^ 29 + 148 - 52 + 54);
        UISettings.lIlIlIl[6] = "   ".length() << " ".length();
        UISettings.lIlIlIl[7] = (0x66 ^ 0x61);
        UISettings.lIlIlIl[8] = " ".length() << "   ".length();
        UISettings.lIlIlIl[9] = ((0x83 ^ 0xB6) << " ".length() ^ (0xDA ^ 0xB9));
        UISettings.lIlIlIl[10] = ((0x3C ^ 0x2F) << " ".length() ^ (0x51 ^ 0x72)) << " ".length();
        UISettings.lIlIlIl[11] = (0x64 ^ 0x6F);
        UISettings.lIlIlIl[12] = "   ".length() << (" ".length() << " ".length());
        UISettings.lIlIlIl[13] = ((0x16 ^ 0x13) << (0x8E ^ 0x8B) ^ 146 + 15 - 98 + 110);
        UISettings.lIlIlIl[14] = (90 + 125 - 110 + 22 ^ (0xA6 ^ 0xA9) << "   ".length()) << " ".length();
        UISettings.lIlIlIl[15] = (0x6E ^ 0x47 ^ (0x2F ^ 0x3C) << " ".length());
    }
    
    private static boolean llIlIlll(final int lllIlIlIllIlIll, final int lllIlIlIllIlIlI) {
        return lllIlIlIllIlIll <= lllIlIlIllIlIlI;
    }
}
