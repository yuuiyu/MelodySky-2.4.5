//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Authentication;

import by.radioegor146.nativeobfuscator.*;
import java.lang.invoke.*;
import java.nio.charset.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;

@Native
public class UserObj
{
    private static /* synthetic */ Class[] lllllII;
    private static /* synthetic */ String[] lIIIIIII;
    private static final /* synthetic */ String[] lllllIl;
    private static final /* synthetic */ int[] lIIIIIIl;
    private static /* synthetic */ String[] llllIll;
    
    public String getName() {
        return invokedynamic(3:(Lxyz/Melody/System/Melody/Authentication/UserObj;)Ljava/lang/String;, this);
    }
    
    private static boolean lIlIIIlIl(final int lllIIIlIIlIIIll, final int lllIIIlIIlIIIlI) {
        return lllIIIlIIlIIIll < lllIIIlIIlIIIlI;
    }
    
    private static CallSite lIIllIIlI(final MethodHandles.Lookup lllIIIlIllIlIII, final String lllIIIlIllIIlll, final MethodType lllIIIlIllIIllI) throws NoSuchMethodException, IllegalAccessException {
        try {
            final String[] lllIIIlIllIlllI = UserObj.llllIll[Integer.parseInt(lllIIIlIllIIlll)].split(UserObj.lllllIl[UserObj.lIIIIIIl[0]]);
            final Class<?> lllIIIlIllIllIl = Class.forName(lllIIIlIllIlllI[UserObj.lIIIIIIl[0]]);
            final String lllIIIlIllIllII = lllIIIlIllIlllI[UserObj.lIIIIIIl[1]];
            MethodHandle lllIIIlIllIlIll = null;
            final int lllIIIlIllIlIlI = lllIIIlIllIlllI[UserObj.lIIIIIIl[2]].length();
            if (lIlIIIIll(lllIIIlIllIlIlI, UserObj.lIIIIIIl[3])) {
                final MethodType lllIIIlIlllIIII = MethodType.fromMethodDescriptorString(lllIIIlIllIlllI[UserObj.lIIIIIIl[3]], UserObj.class.getClassLoader());
                if (lIlIIIlII(lllIIIlIllIlIlI, UserObj.lIIIIIIl[3])) {
                    lllIIIlIllIlIll = lllIIIlIllIlIII.findVirtual(lllIIIlIllIllIl, lllIIIlIllIllII, lllIIIlIlllIIII);
                    "".length();
                    if (((0xF9 ^ 0xA6 ^ " ".length() << (" ".length() << (" ".length() << " ".length()))) & (0x58 ^ 0x3 ^ (0x49 ^ 0x4C) << (" ".length() << " ".length()) ^ -" ".length())) != ((0xA2 ^ 0xC5 ^ (0x3C ^ 0x1) << " ".length()) << " ".length() & ((0x77 ^ 0x4 ^ (0xF2 ^ 0xC5) << " ".length()) << " ".length() ^ -" ".length()))) {
                        return null;
                    }
                }
                else {
                    lllIIIlIllIlIll = lllIIIlIllIlIII.findStatic(lllIIIlIllIllIl, lllIIIlIllIllII, lllIIIlIlllIIII);
                }
                "".length();
                if (((0xBE ^ 0xB5) << "   ".length() & ~((0x1B ^ 0x10) << "   ".length())) != 0x0) {
                    return null;
                }
            }
            else {
                final Class lllIIIlIllIllll = UserObj.lllllII[Integer.parseInt(lllIIIlIllIlllI[UserObj.lIIIIIIl[3]])];
                if (lIlIIIlII(lllIIIlIllIlIlI, UserObj.lIIIIIIl[2])) {
                    lllIIIlIllIlIll = lllIIIlIllIlIII.findGetter(lllIIIlIllIllIl, lllIIIlIllIllII, lllIIIlIllIllll);
                    "".length();
                    if ("   ".length() == 0) {
                        return null;
                    }
                }
                else if (lIlIIIlII(lllIIIlIllIlIlI, UserObj.lIIIIIIl[4])) {
                    lllIIIlIllIlIll = lllIIIlIllIlIII.findStaticGetter(lllIIIlIllIllIl, lllIIIlIllIllII, lllIIIlIllIllll);
                    "".length();
                    if (((0x45 ^ 0x4C) & ~(0xA0 ^ 0xA9)) != 0x0) {
                        return null;
                    }
                }
                else if (lIlIIIlII(lllIIIlIllIlIlI, UserObj.lIIIIIIl[5])) {
                    lllIIIlIllIlIll = lllIIIlIllIlIII.findSetter(lllIIIlIllIllIl, lllIIIlIllIllII, lllIIIlIllIllll);
                    "".length();
                    if (" ".length() <= -" ".length()) {
                        return null;
                    }
                }
                else {
                    lllIIIlIllIlIll = lllIIIlIllIlIII.findStaticSetter(lllIIIlIllIllIl, lllIIIlIllIllII, lllIIIlIllIllll);
                }
            }
            return new ConstantCallSite(lllIIIlIllIlIll);
        }
        catch (Exception lllIIIlIllIlIIl) {
            lllIIIlIllIlIIl.printStackTrace();
            return null;
        }
    }
    
    private static String lIIllIllI(final String lllIIIlIlIIIlll, final String lllIIIlIlIIIllI) {
        try {
            final SecretKeySpec lllIIIlIlIIlIlI = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIIIlIlIIIllI.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher lllIIIlIlIIlIIl = Cipher.getInstance("Blowfish");
            lllIIIlIlIIlIIl.init(UserObj.lIIIIIIl[3], lllIIIlIlIIlIlI);
            return new String(lllIIIlIlIIlIIl.doFinal(Base64.getDecoder().decode(lllIIIlIlIIIlll.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIIIlIlIIlIII) {
            lllIIIlIlIIlIII.printStackTrace();
            return null;
        }
    }
    
    public String getToken() {
        return invokedynamic(5:(Lxyz/Melody/System/Melody/Authentication/UserObj;)Ljava/lang/String;, this);
    }
    
    private static String lIIllIlII(final String lllIIIlIlIlIlII, final String lllIIIlIlIlIIIl) {
        try {
            final SecretKeySpec lllIIIlIlIlIlll = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIIIlIlIlIIIl.getBytes(StandardCharsets.UTF_8)), UserObj.lIIIIIIl[8]), "DES");
            final Cipher lllIIIlIlIlIllI = Cipher.getInstance("DES");
            lllIIIlIlIlIllI.init(UserObj.lIIIIIIl[3], lllIIIlIlIlIlll);
            return new String(lllIIIlIlIlIllI.doFinal(Base64.getDecoder().decode(lllIIIlIlIlIlII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIIIlIlIlIlIl) {
            lllIIIlIlIlIlIl.printStackTrace();
            return null;
        }
    }
    
    public String getUuid() {
        return invokedynamic(4:(Lxyz/Melody/System/Melody/Authentication/UserObj;)Ljava/lang/String;, this);
    }
    
    public UserObj(final String lllIIIllIIIlIII, final String lllIIIllIIIIlll, final String lllIIIllIIIIIlI) {
    }
    // invokedynamic(0:(Lxyz/Melody/System/Melody/Authentication/UserObj;Ljava/lang/String;)V, this, lllIIIllIIIlIII)
    // invokedynamic(1:(Lxyz/Melody/System/Melody/Authentication/UserObj;Ljava/lang/String;)V, this, lllIIIllIIIIlll)
    // invokedynamic(2:(Lxyz/Melody/System/Melody/Authentication/UserObj;Ljava/lang/String;)V, this, lllIIIllIIIIIlI)
    
    private static void lIlIIIIlI() {
        (lIIIIIIl = new int[12])[0] = ((0xA7 ^ 0xB8) << " ".length() & ~((0xD9 ^ 0xC6) << " ".length()));
        UserObj.lIIIIIIl[1] = " ".length();
        UserObj.lIIIIIIl[2] = "   ".length();
        UserObj.lIIIIIIl[3] = " ".length() << " ".length();
        UserObj.lIIIIIIl[4] = " ".length() << (" ".length() << " ".length());
        UserObj.lIIIIIIl[5] = ((0x6D ^ 0x34) << " ".length() ^ 105 + 10 - 79 + 147);
        UserObj.lIIIIIIl[6] = "   ".length() << " ".length();
        UserObj.lIIIIIIl[7] = ((0x90 ^ 0xBD) << " ".length() ^ (0xE5 ^ 0xB8));
        UserObj.lIIIIIIl[8] = " ".length() << "   ".length();
        UserObj.lIIIIIIl[9] = (137 + 37 - 110 + 91 ^ (0x57 ^ 0x1E) << " ".length());
        UserObj.lIIIIIIl[10] = (0x4F ^ 0x4A) << " ".length();
        UserObj.lIIIIIIl[11] = (0x48 ^ 0x43);
    }
    
    private static String lIIllIlIl(String lllIIIlIIllIIlI, final String lllIIIlIIllIIIl) {
        lllIIIlIIllIIlI = (float)new String(Base64.getDecoder().decode(((String)lllIIIlIIllIIlI).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder lllIIIlIIllIlIl = new StringBuilder();
        final char[] lllIIIlIIllIlII = lllIIIlIIllIIIl.toCharArray();
        int lllIIIlIIllIIll = UserObj.lIIIIIIl[0];
        final String lllIIIlIIlIllIl = (Object)((String)lllIIIlIIllIIlI).toCharArray();
        final boolean lllIIIlIIlIllII = lllIIIlIIlIllIl.length != 0;
        int lllIIIlIIlIlIll = UserObj.lIIIIIIl[0];
        while (lIlIIIlIl(lllIIIlIIlIlIll, lllIIIlIIlIllII ? 1 : 0)) {
            final char lllIIIlIIlllIII = lllIIIlIIlIllIl[lllIIIlIIlIlIll];
            lllIIIlIIllIlIl.append((char)(lllIIIlIIlllIII ^ lllIIIlIIllIlII[lllIIIlIIllIIll % lllIIIlIIllIlII.length]));
            "".length();
            ++lllIIIlIIllIIll;
            ++lllIIIlIIlIlIll;
            "".length();
            if ((((0x3F ^ 0x38) << (" ".length() << " ".length()) ^ (0x1F ^ 0x3E)) & ((0x5B ^ 0x18) << " ".length() ^ 125 + 145 - 200 + 117 ^ -" ".length())) != 0x0) {
                return null;
            }
        }
        return String.valueOf(lllIIIlIIllIlIl);
    }
    
    private static boolean lIlIIIIll(final int lllIIIlIIIlllll, final int lllIIIlIIIllllI) {
        return lllIIIlIIIlllll <= lllIIIlIIIllllI;
    }
    
    private static void lIIlllIII() {
        (lllllIl = new String[UserObj.lIIIIIIl[7]])[UserObj.lIIIIIIl[0]] = lIIllIlII(UserObj.lIIIIIII[UserObj.lIIIIIIl[0]], UserObj.lIIIIIII[UserObj.lIIIIIIl[1]]);
        UserObj.lllllIl[UserObj.lIIIIIIl[1]] = lIIllIlIl(UserObj.lIIIIIII[UserObj.lIIIIIIl[3]], UserObj.lIIIIIII[UserObj.lIIIIIIl[2]]);
        UserObj.lllllIl[UserObj.lIIIIIIl[3]] = lIIllIlIl(UserObj.lIIIIIII[UserObj.lIIIIIIl[4]], UserObj.lIIIIIII[UserObj.lIIIIIIl[5]]);
        UserObj.lllllIl[UserObj.lIIIIIIl[2]] = lIIllIlIl(UserObj.lIIIIIII[UserObj.lIIIIIIl[6]], UserObj.lIIIIIII[UserObj.lIIIIIIl[7]]);
        UserObj.lllllIl[UserObj.lIIIIIIl[4]] = lIIllIlII(UserObj.lIIIIIII[UserObj.lIIIIIIl[8]], UserObj.lIIIIIII[UserObj.lIIIIIIl[9]]);
        UserObj.lllllIl[UserObj.lIIIIIIl[5]] = lIIllIllI(UserObj.lIIIIIII[UserObj.lIIIIIIl[10]], UserObj.lIIIIIII[UserObj.lIIIIIIl[11]]);
        UserObj.lllllIl[UserObj.lIIIIIIl[6]] = lIIllIlIl("Fy0cZjoKOAksDkEHHzsDCjlIBRIDOwIxWS4hEiASASAPKxYbPQkmWTonAzo4DT5cPQIGMFx4TU90Rg==", "oTfHw");
        UserObj.lIIIIIII = null;
    }
    
    private static boolean lIlIIIlII(final int lllIIIlIIlIIlll, final int lllIIIlIIlIIllI) {
        return lllIIIlIIlIIlll == lllIIIlIIlIIllI;
    }
    
    private static void lIIllIIll() {
        (UserObj.llllIll = new String[UserObj.lIIIIIIl[6]])[UserObj.lIIIIIIl[2]] = UserObj.lllllIl[UserObj.lIIIIIIl[1]];
        UserObj.llllIll[UserObj.lIIIIIIl[1]] = UserObj.lllllIl[UserObj.lIIIIIIl[3]];
        UserObj.llllIll[UserObj.lIIIIIIl[5]] = UserObj.lllllIl[UserObj.lIIIIIIl[2]];
        UserObj.llllIll[UserObj.lIIIIIIl[0]] = UserObj.lllllIl[UserObj.lIIIIIIl[4]];
        UserObj.llllIll[UserObj.lIIIIIIl[3]] = UserObj.lllllIl[UserObj.lIIIIIIl[5]];
        UserObj.llllIll[UserObj.lIIIIIIl[4]] = UserObj.lllllIl[UserObj.lIIIIIIl[6]];
        (UserObj.lllllII = new Class[UserObj.lIIIIIIl[1]])[UserObj.lIIIIIIl[0]] = String.class;
    }
    
    static {
        lIlIIIIlI();
        lIIllllII();
        lIIlllIII();
        lIIllIIll();
    }
    
    private static void lIIllllII() {
        final double lllIIIlIlIlllII = (double)new Exception().getStackTrace()[UserObj.lIIIIIIl[0]].getFileName();
        UserObj.lIIIIIII = ((String)lllIIIlIlIlllII).substring(((String)lllIIIlIlIlllII).indexOf("\u00e4") + UserObj.lIIIIIIl[1], ((String)lllIIIlIlIlllII).lastIndexOf("\u00fc")).split("\u00f6");
    }
}
