//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Chat;

import by.radioegor146.nativeobfuscator.*;
import java.nio.charset.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;
import java.lang.invoke.*;
import xyz.Melody.*;

@Native
public class IRCThread extends Thread
{
    private static /* synthetic */ String[] llIllIl;
    private static /* synthetic */ String[] lllIIII;
    private static /* synthetic */ Class[] llIlllI;
    private static final /* synthetic */ String[] llIllll;
    private static final /* synthetic */ int[] lIIIlIll;
    
    @Override
    public void run() {
        // invokedynamic(4:(Lxyz/Melody/System/Melody/Chat/IRC;IZ)V, invokedynamic(2:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Chat/IRC;, invokedynamic(1:()Lxyz/Melody/Client;)), IRCThread.lIIIlIll[1], invokedynamic(3:(Lxyz/Melody/System/Melody/Chat/IRCThread;)Z, this))
        while (lIlIlllIl(invokedynamic(5:(Lxyz/Melody/Client;)Z, invokedynamic(1:()Lxyz/Melody/Client;)))) {
            if (lIlIllllI(invokedynamic(6:()Z))) {
                "".length();
                if (" ".length() << " ".length() != " ".length() << " ".length()) {
                    return;
                }
                break;
            }
            else if (lIlIllllI(invokedynamic(7:(Lxyz/Melody/System/Melody/Chat/IRC;)Z, invokedynamic(2:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Chat/IRC;, invokedynamic(1:()Lxyz/Melody/Client;))))) {
                "".length();
                if (null != null) {
                    return;
                }
                break;
            }
            else {
                // invokedynamic(8:(Lxyz/Melody/System/Melody/Chat/IRC;)V, invokedynamic(2:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Chat/IRC;, invokedynamic(1:()Lxyz/Melody/Client;)))
                "".length();
                if (((0x67 ^ 0x4A) & ~(0xBD ^ 0x90)) != 0x0) {
                    return;
                }
                continue;
            }
        }
    }
    
    private static void lIlIlllII() {
        (lIIIlIll = new int[15])[0] = (((0x18 ^ 0x1D) << "   ".length() ^ (0x73 ^ 0x6A)) & ((0xB4 ^ 0x9D) << (" ".length() << " ".length()) ^ 38 + 78 - 37 + 70 ^ -" ".length()));
        IRCThread.lIIIlIll[1] = 19939 + 23596 - 22609 + 4639;
        IRCThread.lIIIlIll[2] = " ".length();
        IRCThread.lIIIlIll[3] = "   ".length();
        IRCThread.lIIIlIll[4] = " ".length() << " ".length();
        IRCThread.lIIIlIll[5] = " ".length() << (" ".length() << " ".length());
        IRCThread.lIIIlIll[6] = (0x8C ^ 0x89);
        IRCThread.lIIIlIll[7] = (0x1E ^ 0x17);
        IRCThread.lIIIlIll[8] = " ".length() << "   ".length();
        IRCThread.lIIIlIll[9] = "   ".length() << " ".length();
        IRCThread.lIIIlIll[10] = (0xBE ^ 0xB9);
        IRCThread.lIIIlIll[11] = (0x31 ^ 0x34) << " ".length();
        IRCThread.lIIIlIll[12] = (0x91 ^ 0x82 ^ "   ".length() << "   ".length());
        IRCThread.lIIIlIll[13] = "   ".length() << (" ".length() << " ".length());
        IRCThread.lIIIlIll[14] = (0x38 ^ 0x35);
    }
    
    private static String lIIIlllII(final String llIllllIlIIlIIl, final String llIllllIlIIIllI) {
        try {
            final SecretKeySpec llIllllIlIIllII = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(llIllllIlIIIllI.getBytes(StandardCharsets.UTF_8)), IRCThread.lIIIlIll[8]), "DES");
            final Cipher llIllllIlIIlIll = Cipher.getInstance("DES");
            llIllllIlIIlIll.init(IRCThread.lIIIlIll[4], llIllllIlIIllII);
            return new String(llIllllIlIIlIll.doFinal(Base64.getDecoder().decode(llIllllIlIIlIIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception llIllllIlIIlIlI) {
            llIllllIlIIlIlI.printStackTrace();
            return null;
        }
    }
    
    private static String lIIIlllIl(String llIllllIlIllIIl, final String llIllllIlIlllIl) {
        llIllllIlIllIIl = (boolean)new String(Base64.getDecoder().decode(((String)llIllllIlIllIIl).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder llIllllIlIlllII = new StringBuilder();
        final char[] llIllllIlIllIll = llIllllIlIlllIl.toCharArray();
        int llIllllIlIllIlI = IRCThread.lIIIlIll[0];
        final boolean llIllllIlIlIlII = (Object)((String)llIllllIlIllIIl).toCharArray();
        final char llIllllIlIlIIll = (char)llIllllIlIlIlII.length;
        char llIllllIlIlIIlI = (char)IRCThread.lIIIlIll[0];
        while (lIllIIIIl(llIllllIlIlIIlI, llIllllIlIlIIll)) {
            final char llIllllIlIlllll = llIllllIlIlIlII[llIllllIlIlIIlI];
            llIllllIlIlllII.append((char)(llIllllIlIlllll ^ llIllllIlIllIll[llIllllIlIllIlI % llIllllIlIllIll.length]));
            "".length();
            ++llIllllIlIllIlI;
            ++llIllllIlIlIIlI;
            "".length();
            if (((0x3F ^ 0x66) & ~(0x40 ^ 0x19)) == -" ".length()) {
                return null;
            }
        }
        return String.valueOf(llIllllIlIlllII);
    }
    
    IRCThread(final boolean llIlllllIIlIllI) {
    }
    // invokedynamic(0:(Lxyz/Melody/System/Melody/Chat/IRCThread;Z)V, this, IRCThread.lIIIlIll[0])
    // invokedynamic(0:(Lxyz/Melody/System/Melody/Chat/IRCThread;Z)V, this, llIlllllIIlIllI)
    
    private static boolean lIllIIIIl(final int llIllllIIllllIl, final int llIllllIIllllII) {
        return llIllllIIllllIl < llIllllIIllllII;
    }
    
    private static boolean lIlIllllI(final int llIllllIIllIllI) {
        return llIllllIIllIllI != 0;
    }
    
    private static void lIIIllllI() {
        (llIllll = new String[IRCThread.lIIIlIll[11]])[IRCThread.lIIIlIll[0]] = lIIIllIll(IRCThread.lllIIII[IRCThread.lIIIlIll[0]], IRCThread.lllIIII[IRCThread.lIIIlIll[2]]);
        IRCThread.llIllll[IRCThread.lIIIlIll[2]] = lIIIlllII(IRCThread.lllIIII[IRCThread.lIIIlIll[4]], IRCThread.lllIIII[IRCThread.lIIIlIll[3]]);
        IRCThread.llIllll[IRCThread.lIIIlIll[4]] = lIIIllIll(IRCThread.lllIIII[IRCThread.lIIIlIll[5]], IRCThread.lllIIII[IRCThread.lIIIlIll[6]]);
        IRCThread.llIllll[IRCThread.lIIIlIll[3]] = lIIIlllII(IRCThread.lllIIII[IRCThread.lIIIlIll[9]], IRCThread.lllIIII[IRCThread.lIIIlIll[10]]);
        IRCThread.llIllll[IRCThread.lIIIlIll[5]] = lIIIlllII(IRCThread.lllIIII[IRCThread.lIIIlIll[8]], IRCThread.lllIIII[IRCThread.lIIIlIll[7]]);
        IRCThread.llIllll[IRCThread.lIIIlIll[6]] = lIIIlllII(IRCThread.lllIIII[IRCThread.lIIIlIll[11]], IRCThread.lllIIII[IRCThread.lIIIlIll[12]]);
        IRCThread.llIllll[IRCThread.lIIIlIll[9]] = lIIIlllIl(IRCThread.lllIIII[IRCThread.lIIIlIll[13]], IRCThread.lllIIII[IRCThread.lIIIlIll[14]]);
        IRCThread.llIllll[IRCThread.lIIIlIll[10]] = lIIIlllIl("CTQjYD0UITYqCV8eID0EFCB3AxUdIj03XjIlODpeOB8adAMZIiwiFCUlKysRFR4tIQBLfWNuUFE=", "qMYNp");
        IRCThread.llIllll[IRCThread.lIIIlIll[8]] = lIIIllIll("KFhuKjNc3pxJwwzHW4l0Y74DmwmEj6zrBPBsUO6QHo7hUoawldR1fu53Yy7imJiS+SzQxF4c46E=", "vAkRT");
        IRCThread.llIllll[IRCThread.lIIIlIll[7]] = lIIIlllII("5MkPblWrbArsHnav8gHLqzYdDHXsFhVzp6luUw/hVOVmEKyy6tB+EQ==", "mRHYD");
        IRCThread.lllIIII = null;
    }
    
    private static boolean lIllIIIII(final int llIllllIlIIIIIl, final int llIllllIlIIIIII) {
        return llIllllIlIIIIIl == llIllllIlIIIIII;
    }
    
    static {
        lIlIlllII();
        lIIIlllll();
        lIIIllllI();
        lIIIllIlI();
    }
    
    private static CallSite lIIIllIIl(final MethodHandles.Lookup llIlllllIIIIIlI, final String llIllllIllllllI, final MethodType llIlllllIIIIIII) throws NoSuchMethodException, IllegalAccessException {
        try {
            final String[] llIlllllIIIlIII = IRCThread.llIllIl[Integer.parseInt(llIllllIllllllI)].split(IRCThread.llIllll[IRCThread.lIIIlIll[0]]);
            final Class<?> llIlllllIIIIlll = Class.forName(llIlllllIIIlIII[IRCThread.lIIIlIll[0]]);
            final String llIlllllIIIIllI = llIlllllIIIlIII[IRCThread.lIIIlIll[2]];
            MethodHandle llIlllllIIIIlIl = null;
            final int llIlllllIIIIlII = llIlllllIIIlIII[IRCThread.lIIIlIll[3]].length();
            if (lIlIlllll(llIlllllIIIIlII, IRCThread.lIIIlIll[4])) {
                final MethodType llIlllllIIIlIlI = MethodType.fromMethodDescriptorString(llIlllllIIIlIII[IRCThread.lIIIlIll[4]], IRCThread.class.getClassLoader());
                if (lIllIIIII(llIlllllIIIIlII, IRCThread.lIIIlIll[4])) {
                    llIlllllIIIIlIl = llIlllllIIIIIlI.findVirtual(llIlllllIIIIlll, llIlllllIIIIllI, llIlllllIIIlIlI);
                    "".length();
                    if ("   ".length() <= " ".length()) {
                        return null;
                    }
                }
                else {
                    llIlllllIIIIlIl = llIlllllIIIIIlI.findStatic(llIlllllIIIIlll, llIlllllIIIIllI, llIlllllIIIlIlI);
                }
                "".length();
                if (-"   ".length() > 0) {
                    return null;
                }
            }
            else {
                final Class llIlllllIIIlIIl = IRCThread.llIlllI[Integer.parseInt(llIlllllIIIlIII[IRCThread.lIIIlIll[4]])];
                if (lIllIIIII(llIlllllIIIIlII, IRCThread.lIIIlIll[3])) {
                    llIlllllIIIIlIl = llIlllllIIIIIlI.findGetter(llIlllllIIIIlll, llIlllllIIIIllI, llIlllllIIIlIIl);
                    "".length();
                    if ("   ".length() < "   ".length()) {
                        return null;
                    }
                }
                else if (lIllIIIII(llIlllllIIIIlII, IRCThread.lIIIlIll[5])) {
                    llIlllllIIIIlIl = llIlllllIIIIIlI.findStaticGetter(llIlllllIIIIlll, llIlllllIIIIllI, llIlllllIIIlIIl);
                    "".length();
                    if (" ".length() << " ".length() < 0) {
                        return null;
                    }
                }
                else if (lIllIIIII(llIlllllIIIIlII, IRCThread.lIIIlIll[6])) {
                    llIlllllIIIIlIl = llIlllllIIIIIlI.findSetter(llIlllllIIIIlll, llIlllllIIIIllI, llIlllllIIIlIIl);
                    "".length();
                    if (-" ".length() >= " ".length() << " ".length()) {
                        return null;
                    }
                }
                else {
                    llIlllllIIIIlIl = llIlllllIIIIIlI.findStaticSetter(llIlllllIIIIlll, llIlllllIIIIllI, llIlllllIIIlIIl);
                }
            }
            return new ConstantCallSite(llIlllllIIIIlIl);
        }
        catch (Exception llIlllllIIIIIll) {
            llIlllllIIIIIll.printStackTrace();
            return null;
        }
    }
    
    private static String lIIIllIll(final String llIllllIllIllII, final String llIllllIllIlIll) {
        try {
            final SecretKeySpec llIllllIlllIIIl = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(llIllllIllIlIll.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher llIllllIlllIIII = Cipher.getInstance("Blowfish");
            llIllllIlllIIII.init(IRCThread.lIIIlIll[4], llIllllIlllIIIl);
            return new String(llIllllIlllIIII.doFinal(Base64.getDecoder().decode(llIllllIllIllII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception llIllllIllIllll) {
            llIllllIllIllll.printStackTrace();
            return null;
        }
    }
    
    private static void lIIIllIlI() {
        (IRCThread.llIllIl = new String[IRCThread.lIIIlIll[7]])[IRCThread.lIIIlIll[2]] = IRCThread.llIllll[IRCThread.lIIIlIll[2]];
        IRCThread.llIllIl[IRCThread.lIIIlIll[8]] = IRCThread.llIllll[IRCThread.lIIIlIll[4]];
        IRCThread.llIllIl[IRCThread.lIIIlIll[4]] = IRCThread.llIllll[IRCThread.lIIIlIll[3]];
        IRCThread.llIllIl[IRCThread.lIIIlIll[5]] = IRCThread.llIllll[IRCThread.lIIIlIll[5]];
        IRCThread.llIllIl[IRCThread.lIIIlIll[0]] = IRCThread.llIllll[IRCThread.lIIIlIll[6]];
        IRCThread.llIllIl[IRCThread.lIIIlIll[9]] = IRCThread.llIllll[IRCThread.lIIIlIll[9]];
        IRCThread.llIllIl[IRCThread.lIIIlIll[10]] = IRCThread.llIllll[IRCThread.lIIIlIll[10]];
        IRCThread.llIllIl[IRCThread.lIIIlIll[3]] = IRCThread.llIllll[IRCThread.lIIIlIll[8]];
        IRCThread.llIllIl[IRCThread.lIIIlIll[6]] = IRCThread.llIllll[IRCThread.lIIIlIll[7]];
        (IRCThread.llIlllI = new Class[IRCThread.lIIIlIll[3]])[IRCThread.lIIIlIll[0]] = Boolean.TYPE;
        IRCThread.llIlllI[IRCThread.lIIIlIll[4]] = IRC.class;
        IRCThread.llIlllI[IRCThread.lIIIlIll[2]] = Client.class;
    }
    
    private static boolean lIlIlllll(final int llIllllIIlllIIl, final int llIllllIIlllIII) {
        return llIllllIIlllIIl <= llIllllIIlllIII;
    }
    
    private static void lIIIlllll() {
        final Exception llIllllIlllIllI = (Exception)new Exception().getStackTrace()[IRCThread.lIIIlIll[0]].getFileName();
        IRCThread.lllIIII = ((String)llIllllIlllIllI).substring(((String)llIllllIlllIllI).indexOf("\u00e4") + IRCThread.lIIIlIll[2], ((String)llIllllIlllIllI).lastIndexOf("\u00fc")).split("\u00f6");
    }
    
    private static boolean lIlIlllIl(final int llIllllIIllIlII) {
        return llIllllIIllIlII == 0;
    }
}
