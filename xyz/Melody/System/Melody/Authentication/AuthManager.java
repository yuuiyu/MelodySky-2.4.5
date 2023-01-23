//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Authentication;

import xyz.Melody.System.Managers.*;
import by.radioegor146.nativeobfuscator.*;
import net.minecraft.client.*;
import java.nio.charset.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;
import java.net.*;
import java.io.*;
import xyz.Melody.*;
import org.apache.logging.log4j.*;
import java.lang.invoke.*;

@Native
public class AuthManager implements Manager
{
    private static final /* synthetic */ String[] lllIlII;
    private static /* synthetic */ Class[] lllIIll;
    private static final /* synthetic */ int[] lIIIllII;
    private static /* synthetic */ String[] lllIIlI;
    private static /* synthetic */ String[] lIIIlIIl;
    
    private static boolean lIllIIllI(final int llIlllIlIIIllll, final int llIlllIlIIIlllI) {
        return llIlllIlIIIllll <= llIlllIlIIIlllI;
    }
    
    private static boolean lIllIIlll(final int llIlllIlIIlIlll, final int llIlllIlIIlIllI) {
        return llIlllIlIIlIlll == llIlllIlIIlIllI;
    }
    
    private static void lIllIIIlI() {
        (lIIIllII = new int[52])[0] = ((0x10 ^ 0x3B) << " ".length() & ~((0xA9 ^ 0x82) << " ".length()));
        AuthManager.lIIIllII[1] = " ".length();
        AuthManager.lIIIllII[2] = " ".length() << " ".length();
        AuthManager.lIIIllII[3] = "   ".length();
        AuthManager.lIIIllII[4] = " ".length() << (" ".length() << " ".length());
        AuthManager.lIIIllII[5] = (0xED ^ 0x80 ^ (0x9A ^ 0x97) << "   ".length());
        AuthManager.lIIIllII[6] = "   ".length() << " ".length();
        AuthManager.lIIIllII[7] = (0x10 ^ 0x17);
        AuthManager.lIIIllII[8] = " ".length() << "   ".length();
        AuthManager.lIIIllII[9] = ((0xA6 ^ 0xBF) << "   ".length() ^ 116 + 25 - 79 + 131);
        AuthManager.lIIIllII[10] = (105 + 110 - 99 + 53 ^ (0x51 ^ 0x7A) << (" ".length() << " ".length())) << " ".length();
        AuthManager.lIIIllII[11] = (0x4A ^ 0x4F) << "   ".length();
        AuthManager.lIIIllII[12] = (0x95 ^ 0x9E);
        AuthManager.lIIIllII[13] = (0x82 ^ 0x93) << " ".length();
        AuthManager.lIIIllII[14] = "   ".length() << (" ".length() << " ".length());
        AuthManager.lIIIllII[15] = (0x41 ^ 0x62 ^ (0xBE ^ 0xA9) << " ".length());
        AuthManager.lIIIllII[16] = (0x84 ^ 0x83) << " ".length();
        AuthManager.lIIIllII[17] = (0x47 ^ 0x48);
        AuthManager.lIIIllII[18] = " ".length() << (" ".length() << (" ".length() << " ".length()));
        AuthManager.lIIIllII[19] = ((0x94 ^ 0x93) << "   ".length() ^ (0xDF ^ 0xC2));
        AuthManager.lIIIllII[20] = (0x75 ^ 0x64);
        AuthManager.lIIIllII[21] = " ".length() << (0x48 ^ 0x4D);
        AuthManager.lIIIllII[22] = (0x53 ^ 0x5A) << " ".length();
        AuthManager.lIIIllII[23] = (0x61 ^ 0x78 ^ "   ".length() << (" ".length() << " ".length()));
        AuthManager.lIIIllII[24] = ((0x67 ^ 0x2A) << " ".length() ^ 5 + 78 - 10 + 64);
        AuthManager.lIIIllII[25] = (0x26 ^ 0x21) << (" ".length() << " ".length());
        AuthManager.lIIIllII[26] = (182 + 45 - 221 + 183 ^ (0x50 ^ 0x47) << "   ".length()) << (" ".length() << " ".length());
        AuthManager.lIIIllII[27] = (0x78 ^ 0x65);
        AuthManager.lIIIllII[28] = (0x62 ^ 0x69) << " ".length();
        AuthManager.lIIIllII[29] = (27 + 98 - 112 + 132 ^ (0x80 ^ 0x93) << "   ".length()) << (" ".length() << " ".length());
        AuthManager.lIIIllII[30] = (77 + 52 - 126 + 126 ^ (0x13 ^ 0x58) << " ".length());
        AuthManager.lIIIllII[31] = "   ".length() << "   ".length();
        AuthManager.lIIIllII[32] = (0x6A ^ 0x73 ^ ("   ".length() << (0x40 ^ 0x45) & ~("   ".length() << (0x43 ^ 0x46))));
        AuthManager.lIIIllII[33] = (0x22 ^ 0x2F) << " ".length();
        AuthManager.lIIIllII[34] = ((0x8D ^ 0xC6) << " ".length() ^ 101 + 112 - 95 + 19);
        AuthManager.lIIIllII[35] = ((0xBB ^ 0xB4) << (" ".length() << " ".length()) ^ (0x35 ^ 0x12));
        AuthManager.lIIIllII[36] = ((0x59 ^ 0x7A) << " ".length() ^ (0x47 ^ 0xE)) << " ".length();
        AuthManager.lIIIllII[37] = (0x7A ^ 0x5B);
        AuthManager.lIIIllII[38] = (0x60 ^ 0x3 ^ (0x57 ^ 0x46) << (" ".length() << " ".length()));
        AuthManager.lIIIllII[39] = (0x9 ^ 0x2A);
        AuthManager.lIIIllII[40] = ((0x56 ^ 0x5B) << (" ".length() << (" ".length() << " ".length())) ^ 84 + 130 - 81 + 62) << " ".length();
        AuthManager.lIIIllII[41] = (0x52 ^ 0x7B);
        AuthManager.lIIIllII[42] = ((0xB4 ^ 0xB3) << " ".length() ^ (0x96 ^ 0x8D)) << " ".length();
        AuthManager.lIIIllII[43] = ((0x8D ^ 0x9C) << "   ".length() ^ 131 + 29 - 130 + 133);
        AuthManager.lIIIllII[44] = (0xA ^ 0x1) << (" ".length() << " ".length());
        AuthManager.lIIIllII[45] = (0x9 ^ 0x24);
        AuthManager.lIIIllII[46] = (0x85 ^ 0x92) << " ".length();
        AuthManager.lIIIllII[47] = ((0x7B ^ 0x76) << "   ".length() ^ (0xE ^ 0x49));
        AuthManager.lIIIllII[48] = "   ".length() << (" ".length() << (" ".length() << " ".length()));
        AuthManager.lIIIllII[49] = (0x81 ^ 0xB0);
        AuthManager.lIIIllII[50] = (0xA4 ^ 0xBD) << " ".length();
        AuthManager.lIIIllII[51] = (0xC ^ 0x3F);
    }
    
    private static void lIlIllIIl() {
        final double llIlllIllIIllII = (double)new Exception().getStackTrace()[AuthManager.lIIIllII[0]].getFileName();
        AuthManager.lIIIlIIl = ((String)llIlllIllIIllII).substring(((String)llIlllIllIIllII).indexOf("\u00e4") + AuthManager.lIIIllII[1], ((String)llIlllIllIIllII).lastIndexOf("\u00fc")).split("\u00f6");
    }
    
    private static String lIIlIIlll(final String llIlllIllIIIIlI, final String llIlllIllIIIIIl) {
        try {
            final SecretKeySpec llIlllIllIIIlll = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(llIlllIllIIIIIl.getBytes(StandardCharsets.UTF_8)), AuthManager.lIIIllII[8]), "DES");
            final Cipher llIlllIllIIIllI = Cipher.getInstance("DES");
            llIlllIllIIIllI.init(AuthManager.lIIIllII[2], llIlllIllIIIlll);
            return new String(llIlllIllIIIllI.doFinal(Base64.getDecoder().decode(llIlllIllIIIIlI.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception llIlllIllIIIlIl) {
            llIlllIllIIIlIl.printStackTrace();
            return null;
        }
    }
    
    private static String lIIlIlIII(final String llIlllIlIIlllIl, final String llIlllIlIIllllI) {
        try {
            final SecretKeySpec llIlllIlIlIIIlI = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(llIlllIlIIllllI.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher llIlllIlIlIIIIl = Cipher.getInstance("Blowfish");
            llIlllIlIlIIIIl.init(AuthManager.lIIIllII[2], llIlllIlIlIIIlI);
            return new String(llIlllIlIlIIIIl.doFinal(Base64.getDecoder().decode(llIlllIlIIlllIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception llIlllIlIlIIIII) {
            llIlllIlIlIIIII.printStackTrace();
            return null;
        }
    }
    
    public void setUser(final UserObj llIllllIIlIlIll) {
    }
    // invokedynamic(3:(Lxyz/Melody/System/Melody/Authentication/AuthManager;Lxyz/Melody/System/Melody/Authentication/UserObj;)V, this, llIllllIIlIlIll)
    
    public void init() {
        // invokedynamic(7:(Lxyz/Melody/System/Melody/Authentication/AuthManager;Ljava/lang/String;Ljava/lang/String;)Z, this, invokedynamic(5:(Lxyz/Melody/System/Melody/Authentication/UserObj;)Ljava/lang/String;, invokedynamic(4:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Lxyz/Melody/System/Melody/Authentication/UserObj;, this)), invokedynamic(6:(Lxyz/Melody/System/Melody/Authentication/UserObj;)Ljava/lang/String;, invokedynamic(4:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Lxyz/Melody/System/Melody/Authentication/UserObj;, this)))
        "".length();
    }
    
    public String getChecked() throws Exception {
        return invokedynamic(28:(Lxyz/Melody/System/Melody/Authentication/AuthManager;Ljava/lang/String;)Ljava/lang/String;, this, invokedynamic(27:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Ljava/lang/String;, this));
    }
    
    public String get(final String llIlllIllllIIll) throws IOException {
        String llIlllIllllIIlI = AuthManager.lllIlII[AuthManager.lIIIllII[4]];
        try {
            final String llIlllIlllllllI = llIlllIllllIIll;
            final URL llIlllIllllllIl = new URL("127.0.0.1");
            final HttpURLConnection llIlllIllllllII = (HttpURLConnection)invokedynamic(29:(Ljava/net/URL;)Ljava/net/URLConnection;, llIlllIllllllIl);
            // invokedynamic(30:(Ljava/net/HttpURLConnection;Z)V, llIlllIllllllII, AuthManager.lIIIllII[1])
            // invokedynamic(31:(Ljava/net/HttpURLConnection;Ljava/lang/String;)V, llIlllIllllllII, AuthManager.lllIlII[AuthManager.lIIIllII[5]])
            // invokedynamic(32:(Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)V, llIlllIllllllII, AuthManager.lllIlII[AuthManager.lIIIllII[6]], AuthManager.lllIlII[AuthManager.lIIIllII[7]])
            final InputStream llIlllIlllllIll = invokedynamic(33:(Ljava/net/HttpURLConnection;)Ljava/io/InputStream;, llIlllIllllllII);
            final InputStreamReader llIlllIlllllIlI = new InputStreamReader(llIlllIlllllIll, AuthManager.lllIlII[AuthManager.lIIIllII[8]]);
            final BufferedReader llIlllIlllllIIl = new BufferedReader(llIlllIlllllIlI);
            String llIlllIlllllIII = invokedynamic(34:(Ljava/io/BufferedReader;)Ljava/lang/String;, llIlllIlllllIIl);
            while (lIllIIlIl(llIlllIlllllIII)) {
                llIlllIllllIIlI = invokedynamic(22:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), invokedynamic(35:(Ljava/lang/Object;)Ljava/lang/String;, invokedynamic(35:(Ljava/lang/Object;)Ljava/lang/String;, llIlllIllllIIlI))), llIlllIlllllIII), AuthManager.lllIlII[AuthManager.lIIIllII[9]]));
                llIlllIlllllIII = invokedynamic(34:(Ljava/io/BufferedReader;)Ljava/lang/String;, llIlllIlllllIIl);
                "".length();
                if (-"  ".length() >= 0) {
                    return null;
                }
            }
            // invokedynamic(36:(Ljava/io/BufferedReader;)V, llIlllIlllllIIl)
            // invokedynamic(37:(Ljava/io/InputStreamReader;)V, llIlllIlllllIlI)
            // invokedynamic(38:(Ljava/io/InputStream;)V, llIlllIlllllIll)
            // invokedynamic(39:(Ljava/net/HttpURLConnection;)V, llIlllIllllllII)
            "".length();
            if (" ".length() << (" ".length() << " ".length()) > " ".length() << (" ".length() << " ".length())) {
                return null;
            }
        }
        catch (Exception llIlllIllllIlIl) {
            final Throwable llIlllIllllIlll = new Throwable(invokedynamic(16:(Ljava/lang/Exception;)Ljava/lang/String;, llIlllIllllIlIl));
            final StackTraceElement[] llIlllIllllIllI = new StackTraceElement[AuthManager.lIIIllII[0]];
        }
        // invokedynamic(17:(Ljava/lang/Throwable;[Ljava/lang/StackTraceElement;)V, llIlllIllllIlll, llIlllIllllIllI)
        // invokedynamic(18:(Ljava/lang/Throwable;)V, llIlllIllllIlll)
        return llIlllIllllIIlI;
    }
    
    static {
        lIllIIIlI();
        lIlIllIIl();
        lIlIllIII();
        lIIlIIlIl();
    }
    
    private static boolean lIllIlIII(final int llIlllIlIIlIIll, final int llIlllIlIIlIIlI) {
        return llIlllIlIIlIIll < llIlllIlIIlIIlI;
    }
    
    private static void lIlIllIII() {
        (lllIlII = new String[AuthManager.lIIIllII[51]])[AuthManager.lIIIllII[0]] = lIIlIIllI(AuthManager.lIIIlIIl[AuthManager.lIIIllII[0]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[1]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[1]] = lIIlIIlll(AuthManager.lIIIlIIl[AuthManager.lIIIllII[2]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[3]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[2]] = lIIlIIllI(AuthManager.lIIIlIIl[AuthManager.lIIIllII[4]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[5]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[3]] = lIIlIIllI(AuthManager.lIIIlIIl[AuthManager.lIIIllII[6]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[7]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[4]] = lIIlIlIII(AuthManager.lIIIlIIl[AuthManager.lIIIllII[8]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[9]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[5]] = lIIlIlIII(AuthManager.lIIIlIIl[AuthManager.lIIIllII[10]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[12]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[6]] = lIIlIIlll(AuthManager.lIIIlIIl[AuthManager.lIIIllII[14]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[15]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[7]] = lIIlIIllI(AuthManager.lIIIlIIl[AuthManager.lIIIllII[16]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[17]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[8]] = lIIlIlIII(AuthManager.lIIIlIIl[AuthManager.lIIIllII[18]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[20]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[9]] = lIIlIIlll(AuthManager.lIIIlIIl[AuthManager.lIIIllII[22]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[24]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[10]] = lIIlIIllI(AuthManager.lIIIlIIl[AuthManager.lIIIllII[26]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[23]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[12]] = lIIlIlIII(AuthManager.lIIIlIIl[AuthManager.lIIIllII[28]], AuthManager.lIIIlIIl[AuthManager.lIIIllII[30]]);
        AuthManager.lllIlII[AuthManager.lIIIllII[14]] = lIIlIIlll("mzqKCieAQGidK24qEVy5crhYPr9Q3ONDwGiArLQHdbzW3pXGw1lfhEbx+CPcT8jX2uVVdiq6QDw=", "ErVCM");
        AuthManager.lllIlII[AuthManager.lIIIllII[15]] = lIIlIlIII("SnMSvRmFnN3ERGTgUVg2kHy80PjdvJg+PzRdQkuYQHy3ycur6tPoZxT6Jt8r/ePoERs2FGW6YFjoR2TuUMGDFD1dPMVKjLjglWulmRf+kNK8gs3FpdcAZbsXs/fgCzvFPm1YzKwWfa0=", "szSHX");
        AuthManager.lllIlII[AuthManager.lIIIllII[16]] = lIIlIlIII("miYv5gSP/MtSIZrvDVS3igW7laM0pdvog5cVJWcHdnGEu9f9yCxdengu0VnjH1kw", "KZHyy");
        AuthManager.lllIlII[AuthManager.lIIIllII[17]] = lIIlIIlll("KdZd6l36amY4NhFI7JES+fzjgCRPVqc3s0kD7hVrFXkSxeGC9PsSmGiglj9CgKLWuAZuH88WVE4osi3ugR2x2j70DtDJLIpcnKxG9zf02iY=", "MTKgU");
        AuthManager.lllIlII[AuthManager.lIIIllII[18]] = lIIlIIlll("57mpDIEHYHW48EoIZmHSLGeOHej7YAi78rU6GFHZ6wCV9F1zG0N0Cuq9EFIlfJIl", "QPLDQ");
        AuthManager.lllIlII[AuthManager.lIIIllII[20]] = lIIlIIlll("V+Bz6GXuCSSd1jYtk/9qwg7/mBO0yJ6SCfbbrGPQJUToCDy5YskLHA==", "DmQtO");
        AuthManager.lllIlII[AuthManager.lIIIllII[22]] = lIIlIlIII("JWvF7vATrL7zDNNQyteG3ncara79na3XHyWlY2IaR8BBjdQVq3oVF8hc4DUFiDXriYQs7t6DTKSJ/dsOzbJvwGBfbSpNlTm6j9b6isww5vl7LQVS+Uoy7RirsEuXBosc", "OjLJc");
        AuthManager.lllIlII[AuthManager.lIIIllII[24]] = lIIlIlIII("+qD3EyhwkSoeVEek97tDNkTMziZjunzbFrLSLXffCc6po1A3DAyYgZVNfm+bhfsHL2loP5YFjeb8AsoW0K5xCw==", "YGWSJ");
        AuthManager.lllIlII[AuthManager.lIIIllII[26]] = lIIlIlIII("yJbRSa8iJ52gikJiJ2d5SeomlIkMZyfCSfLlvrUpMauLu+OOsg3EWS3wfN7+wIQUjAmxM3IGW4zoP7zCgPiewrprx+8eAgf0nmhP0GG4Z1WcqygPIfBmcBzQnXtuZlUjeiyc+II19HQ=", "XIUzY");
        AuthManager.lllIlII[AuthManager.lIIIllII[23]] = lIIlIIlll("KCXxiiwgiliWRwsXujIxEdOdNWAtIsYwGg0F+kNrgsM3CJZJwq+7VefMIYZj7yStCGfSN6WVaazM9x8ngl4RbA==", "YcKHX");
        AuthManager.lllIlII[AuthManager.lIIIllII[28]] = lIIlIIllI("LCgjAnsqKDsEexIhJwY0InMmDzAjOW9LH28fb0M=", "FIUcU");
        AuthManager.lllIlII[AuthManager.lIIIllII[30]] = lIIlIIlll("sWCWY+08KcYMBXHGjiq/VNUKKNN/yBfAaN9kWzRfYUdBamDKhEfAKw==", "rYStQ");
        AuthManager.lllIlII[AuthManager.lIIIllII[31]] = lIIlIlIII("HRMQtTrXDT37ZvYrbGWi7gvZjgvf8DvNLJ01/hTqo7ip9BYbrdoVUgM+dV0nKNos5VWLaMjGn/Kt2e2AVffu/w==", "DjItJ");
        AuthManager.lllIlII[AuthManager.lIIIllII[32]] = lIIlIIlll("L3TxW/XfIxapmC1H++2t6Ilz2rT/JKSJbty751/fRm1iu2/Pm22pD2ZIDUa3Gju3", "EyARm");
        AuthManager.lllIlII[AuthManager.lIIIllII[33]] = lIIlIlIII("jomw5+rqemzdqeeI7Y9XzSt7oGTvd4BDBa8PHkk4dbkYxfjscnIh3b6zngWoPc6nByRhjvSD9ieKrDu5eT7rgzjI9QKVLQaCo3CJvveypm0IsmmzRDL02w==", "aOyWi");
        AuthManager.lllIlII[AuthManager.lIIIllII[35]] = lIIlIIllI("ISQGMVolIAR+PD8xAAUmBwYfPhouJgQ5GyV/AzUAGSABJRE4MT01ACMqFGpcBy8RJhVkKRE+E2QWBCIdJSJLeSJxZVA=", "KEpPt");
        AuthManager.lllIlII[AuthManager.lIIIllII[25]] = lIIlIlIII("DiSKeuj8YLMjZzUv21SVKrSvdQddscCHsY22VHzGmXs=", "XmIAn");
        AuthManager.lllIlII[AuthManager.lIIIllII[27]] = lIIlIlIII("/hPmtZvBe8PRuRWzQFw2nbk//pDli3vrM4d8SU+1xw1uAVN4OHY2HL0acMAV90fs0nATcXYYIxuO9lUUqz88uvWE41pjrkIb", "UsCNV");
        AuthManager.lllIlII[AuthManager.lIIIllII[36]] = lIIlIlIII("FViQ3zYXUk0I/eebhx6dMbveLnUHr7QwvLNOF7WSIKM=", "XJZqN");
        AuthManager.lllIlII[AuthManager.lIIIllII[34]] = lIIlIIlll("kmOHCZus6rWwKSbmhDL6gQv4YB5Vcc0+RtGQSWxkgD+fBlodqT6d/NGtr4qbKG7h9k6iWm7sihSlCl1dV3LSpw==", "SnNSq");
        AuthManager.lllIlII[AuthManager.lIIIllII[21]] = lIIlIlIII("g9GxgiiyDmPsUE2iWHRa+g8IzKZ9Juwl+HpSNwMH+oylFsMqf0YfIK+bwgev9z97/aEzNRODe8rMvgrfYYcWe1yXawfW5NfS", "IQDgy");
        AuthManager.lllIlII[AuthManager.lIIIllII[37]] = lIIlIIlll("lMSP+88YH+hII67raXZ7OS/ZLHMTcc78ul0jaDq2KbdTRNK5qQlM/7pqjVWsSoLExWJGj1A7kBV0XHaNAj/gzrlNK0BJ9bOR", "dTBgr");
        AuthManager.lllIlII[AuthManager.lIIIllII[13]] = lIIlIIlll("1Sq9FYTyh7Fj+MbzEESOPJ43bOKF89p05hYym+M3M38IjtAPqkiGKHUpY/o9kUIS", "RZBBX");
        AuthManager.lllIlII[AuthManager.lIIIllII[39]] = lIIlIIlll("uhoIVPIACkZMsZVpBZDD+6VmLtDmxMphm5EJZK33czgO/xh3yZB01N3WwTC0Dw35", "SzGjp");
        AuthManager.lllIlII[AuthManager.lIIIllII[29]] = lIIlIlIII("r+uKYMg68hPv1+IVIvFsuY0PwCAaNhvughVzaLuc7LAemfnJ3KXa7lCikQv1UbFCLcBFvPNUnoRoPDLIwIX0a4FzbgvLZHiV", "vYAEu");
        AuthManager.lllIlII[AuthManager.lIIIllII[19]] = lIIlIIlll("Hxxa+AntnGcEhTrMOsVdgMWAT6/U8kPMHE5kpeZy/ydvlA0tkm6MLb8DViK/Ie9mfsPGhIjARizXDWvFt4tvLXQsIZvQ8zql", "rpOie");
        AuthManager.lllIlII[AuthManager.lIIIllII[40]] = lIIlIlIII("cQlazNCTQwsEnKWOAvA48nQL6zSImbtbPcr+RMrht+yzyKa2FiR7eg==", "VpyES");
        AuthManager.lllIlII[AuthManager.lIIIllII[38]] = lIIlIlIII("u+LVJbBvK2E5dNY9U/2jE+km2mN7OXJ6E/3yWVbI8SRAALBMzJtSZg==", "Yviwl");
        AuthManager.lllIlII[AuthManager.lIIIllII[11]] = lIIlIIlll("9oVE1DNhBHQHyuJoDn4afPgFPPcsTIP4WIIjo5FtaKfhbmlSIbu5oVkSKyTyAifrWIIjo5FtaKdMkkUTkNm6XFrqtn6xitpc", "Ehnmk");
        AuthManager.lllIlII[AuthManager.lIIIllII[41]] = lIIlIlIII("upd4JjpJojMhZZXIrEDFAOZZLVvSv9V235BLY28qI8lHXepuciQ5pq1hoYwUv6WFJVZ4R6OFWaA=", "ByWqt");
        AuthManager.lllIlII[AuthManager.lIIIllII[42]] = lIIlIIllI("IhkeBmAkGQYAYBsMGg4gL0ILCCA8GQEJPXJQJA0vPhlHCy8mH0ckJikKOwI/PR0GBCtzUTJdbmg=", "HxhgN");
        AuthManager.lllIlII[AuthManager.lIIIllII[43]] = lIIlIlIII("43N7mSfhohMEXCZSq9BerdmlWn1L97rTzNcinud0z1g7kb1ozORJl31jOoYldJga9M+liQrkuVO3ttRgureupw==", "zpVui");
        AuthManager.lllIlII[AuthManager.lIIIllII[44]] = lIIlIIlll("At1vR2U4RIMRtadgnC49+NMvTeEyWyCbAEHwPmMnnmPuwFmr51zNBC2Rlc4Y7domndnB9H4aimnohBHqetrpWEZ8x8iW/OWP26TbPgF5lYo=", "ZSYTP");
        AuthManager.lllIlII[AuthManager.lIIIllII[45]] = lIIlIlIII("VIbHlbGAa+jagIBoqr9DUSDOJvI2Lgs0y+Ze+ixKwCu1jgQyTYTvfIpi3wF4DDIigQ2S/Lo7Bi8Sgo8iRePSMbn4fehB4nLfOF68t5WVsaO8yyRhY/xdNg==", "bijBF");
        AuthManager.lllIlII[AuthManager.lIIIllII[46]] = lIIlIIllI("ARAIdywcBR09GFc6CyoVHARcFAQVBhYgTzgcBjEEFx0bOgANAB03TzgcBjEsGAcTPgQLUwcqBAtTQGNBWUk=", "yirYa");
        AuthManager.lllIlII[AuthManager.lIIIllII[47]] = lIIlIlIII("d26gCbsU47yMhPnPEzXwGmCDS7X92age677tUIHPmQhf8xs092iro8ruHDxu82eVRbyixDtgfXW9OsUCL8kRWw==", "gQSHq");
        AuthManager.lllIlII[AuthManager.lIIIllII[48]] = lIIlIIlll("bYCSsW7A9oUEBfOJXdxXh6JTTkj74bT/jh6WbSrrnfs/+R4JvflD7w==", "KJTDt");
        AuthManager.lllIlII[AuthManager.lIIIllII[49]] = lIIlIIlll("22XP83x9uagkQ3w9vZLoyIgoS00Qt68EAaSP5tHJ5AqXDDyKmj4gGhGMAp3qutTm5HLZQw3R07ZeBSlz10bGMA==", "PhHSL");
        AuthManager.lllIlII[AuthManager.lIIIllII[50]] = lIIlIIlll("XQjxFYg6SeEH9v+nTmiV61OrWg4QpjXAVxs54BJwsxYoLZj4lTm9cq24ZsadDCbt", "DRgYr");
        AuthManager.lIIIlIIl = null;
    }
    
    public UserObj getUser() {
        return invokedynamic(4:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Lxyz/Melody/System/Melody/Authentication/UserObj;, this);
    }
    
    private static boolean lIllIIlIl(final Object llIlllIlIIIllII) {
        return llIlllIlIIIllII != null;
    }
    
    private static void lIIlIIlIl() {
        (AuthManager.lllIIlI = new String[AuthManager.lIIIllII[11]])[AuthManager.lIIIllII[5]] = AuthManager.lllIlII[AuthManager.lIIIllII[12]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[13]] = AuthManager.lllIlII[AuthManager.lIIIllII[14]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[7]] = AuthManager.lllIlII[AuthManager.lIIIllII[15]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[9]] = AuthManager.lllIlII[AuthManager.lIIIllII[16]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[6]] = AuthManager.lllIlII[AuthManager.lIIIllII[17]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[10]] = AuthManager.lllIlII[AuthManager.lIIIllII[18]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[19]] = AuthManager.lllIlII[AuthManager.lIIIllII[20]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[21]] = AuthManager.lllIlII[AuthManager.lIIIllII[22]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[23]] = AuthManager.lllIlII[AuthManager.lIIIllII[24]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[25]] = AuthManager.lllIlII[AuthManager.lIIIllII[26]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[27]] = AuthManager.lllIlII[AuthManager.lIIIllII[23]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[15]] = AuthManager.lllIlII[AuthManager.lIIIllII[28]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[29]] = AuthManager.lllIlII[AuthManager.lIIIllII[30]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[2]] = AuthManager.lllIlII[AuthManager.lIIIllII[31]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[22]] = AuthManager.lllIlII[AuthManager.lIIIllII[32]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[31]] = AuthManager.lllIlII[AuthManager.lIIIllII[33]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[34]] = AuthManager.lllIlII[AuthManager.lIIIllII[35]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[24]] = AuthManager.lllIlII[AuthManager.lIIIllII[25]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[33]] = AuthManager.lllIlII[AuthManager.lIIIllII[27]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[14]] = AuthManager.lllIlII[AuthManager.lIIIllII[36]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[1]] = AuthManager.lllIlII[AuthManager.lIIIllII[34]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[37]] = AuthManager.lllIlII[AuthManager.lIIIllII[21]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[3]] = AuthManager.lllIlII[AuthManager.lIIIllII[37]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[38]] = AuthManager.lllIlII[AuthManager.lIIIllII[13]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[12]] = AuthManager.lllIlII[AuthManager.lIIIllII[39]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[39]] = AuthManager.lllIlII[AuthManager.lIIIllII[29]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[17]] = AuthManager.lllIlII[AuthManager.lIIIllII[19]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[16]] = AuthManager.lllIlII[AuthManager.lIIIllII[40]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[40]] = AuthManager.lllIlII[AuthManager.lIIIllII[38]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[20]] = AuthManager.lllIlII[AuthManager.lIIIllII[11]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[18]] = AuthManager.lllIlII[AuthManager.lIIIllII[41]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[32]] = AuthManager.lllIlII[AuthManager.lIIIllII[42]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[28]] = AuthManager.lllIlII[AuthManager.lIIIllII[43]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[26]] = AuthManager.lllIlII[AuthManager.lIIIllII[44]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[0]] = AuthManager.lllIlII[AuthManager.lIIIllII[45]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[4]] = AuthManager.lllIlII[AuthManager.lIIIllII[46]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[30]] = AuthManager.lllIlII[AuthManager.lIIIllII[47]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[8]] = AuthManager.lllIlII[AuthManager.lIIIllII[48]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[35]] = AuthManager.lllIlII[AuthManager.lIIIllII[49]];
        AuthManager.lllIIlI[AuthManager.lIIIllII[36]] = AuthManager.lllIlII[AuthManager.lIIIllII[50]];
        (AuthManager.lllIIll = new Class[AuthManager.lIIIllII[7]])[AuthManager.lIIIllII[3]] = Client.class;
        AuthManager.lllIIll[AuthManager.lIIIllII[5]] = Thread.class;
        AuthManager.lllIIll[AuthManager.lIIIllII[4]] = Boolean.TYPE;
        AuthManager.lllIIll[AuthManager.lIIIllII[0]] = Minecraft.class;
        AuthManager.lllIIll[AuthManager.lIIIllII[6]] = Logger.class;
        AuthManager.lllIIll[AuthManager.lIIIllII[2]] = UserObj.class;
        AuthManager.lllIIll[AuthManager.lIIIllII[1]] = String.class;
    }
    
    private static boolean lIllIIIll(final int llIlllIlIIIlIlI) {
        return llIlllIlIIIlIlI != 0;
    }
    
    public boolean authMe(final String llIllllIIIlIIII, final String llIllllIIIlIlII) {
        // invokedynamic(9:(Lxyz/Melody/Client;Z)V, invokedynamic(8:()Lxyz/Melody/Client;), AuthManager.lIIIllII[1])
        boolean llIllllIIIlIIll = AuthManager.lIIIllII[0] != 0;
        boolean llIllllIIIlIIlI = AuthManager.lIIIllII[0] != 0;
        try {
            // invokedynamic(10:()V)
            while (lIllIIIll(invokedynamic(12:(Ljava/lang/Thread;)Z, invokedynamic(11:()Ljava/lang/Thread;)))) {
                // invokedynamic(13:(J)V, 10L)
                "".length();
                if (((88 + 81 - 124 + 102 ^ (0x77 ^ 0x62) << "   ".length()) & (20 + 119 + 77 + 39 ^ (0x8B ^ 0xBA) << (" ".length() << " ".length()) ^ -" ".length())) != 0x0) {
                    return (((0x7A ^ 0x77) << "   ".length() ^ (0xB5 ^ 0x92)) & ((0xBB ^ 0xAA) << (" ".length() << " ".length()) ^ (0x8E ^ 0x85) ^ -" ".length())) != 0x0;
                }
            }
            // invokedynamic(14:(Lxyz/Melody/Client;)V, invokedynamic(8:()Lxyz/Melody/Client;))
            llIllllIIIlIIll = invokedynamic(15:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Z, this);
            "".length();
            if ("   ".length() <= " ".length()) {
                return ((0x2E ^ 0x7F) & ~(0x41 ^ 0x10)) != 0x0;
            }
        }
        catch (Exception llIllllIIIllIll) {
            final Throwable llIllllIIIlllIl = new Throwable(invokedynamic(16:(Ljava/lang/Exception;)Ljava/lang/String;, llIllllIIIllIll));
            final StackTraceElement[] llIllllIIIlllII = new StackTraceElement[AuthManager.lIIIllII[0]];
        }
        // invokedynamic(17:(Ljava/lang/Throwable;[Ljava/lang/StackTraceElement;)V, llIllllIIIlllIl, llIllllIIIlllII)
        // invokedynamic(18:(Ljava/lang/Throwable;)V, llIllllIIIlllIl)
        // invokedynamic(23:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(19:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(8:()Lxyz/Melody/Client;)), invokedynamic(22:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(21:(Ljava/lang/StringBuilder;Z)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), AuthManager.lllIlII[AuthManager.lIIIllII[1]]), llIllllIIIlIIll)))
        if (lIllIIlII(llIllllIIIlIIll ? 1 : 0)) {
            // invokedynamic(23:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(19:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(8:()Lxyz/Melody/Client;)), AuthManager.lllIlII[AuthManager.lIIIllII[2]])
            try {
                final String llIllllIIIllIlI = invokedynamic(24:(Lxyz/Melody/System/Melody/Authentication/AuthManager;)Ljava/lang/String;, this);
                // invokedynamic(14:(Lxyz/Melody/Client;)V, invokedynamic(8:()Lxyz/Melody/Client;))
                if (lIllIIIll(invokedynamic(25:(Ljava/lang/String;Ljava/lang/CharSequence;)Z, llIllllIIIllIlI, llIllllIIIlIIII))) {
                    llIllllIIIlIIlI = (AuthManager.lIIIllII[1] != 0);
                }
                "".length();
                if ("   ".length() < "   ".length()) {
                    return ((0x1 ^ 0x18 ^ (0x4F ^ 0x44) << (" ".length() << " ".length())) & ((0x4 ^ 0x1) << (0x9 ^ 0xC) ^ 76 + 117 - 70 + 26 ^ -" ".length())) != 0x0;
                }
            }
            catch (Exception llIllllIIIlIlll) {
                final Throwable llIllllIIIllIIl = new Throwable(invokedynamic(16:(Ljava/lang/Exception;)Ljava/lang/String;, llIllllIIIlIlll));
                final StackTraceElement[] llIllllIIIllIII = new StackTraceElement[AuthManager.lIIIllII[0]];
            }
            // invokedynamic(17:(Ljava/lang/Throwable;[Ljava/lang/StackTraceElement;)V, llIllllIIIllIIl, llIllllIIIllIII)
            // invokedynamic(18:(Ljava/lang/Throwable;)V, llIllllIIIllIIl)
        }
        // invokedynamic(23:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(19:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(8:()Lxyz/Melody/Client;)), invokedynamic(22:(Ljava/lang/StringBuilder;)Ljava/lang/String;, invokedynamic(21:(Ljava/lang/StringBuilder;Z)Ljava/lang/StringBuilder;, invokedynamic(20:(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;, new StringBuilder(), AuthManager.lllIlII[AuthManager.lIIIllII[3]]), llIllllIIIlIIlI)))
        if (!lIllIIlII(llIllllIIIlIIll ? 1 : 0) || lIllIIIll(llIllllIIIlIIlI ? 1 : 0)) {
            // invokedynamic(26:(Lxyz/Melody/System/Melody/Authentication/AuthManager;Z)V, this, AuthManager.lIIIllII[1])
            // invokedynamic(9:(Lxyz/Melody/Client;Z)V, invokedynamic(8:()Lxyz/Melody/Client;), AuthManager.lIIIllII[0])
            return AuthManager.lIIIllII[1] != 0;
        }
        // invokedynamic(9:(Lxyz/Melody/Client;Z)V, invokedynamic(8:()Lxyz/Melody/Client;), AuthManager.lIIIllII[0])
        return AuthManager.lIIIllII[0] != 0;
    }
    
    private static boolean lIllIIlII(final int llIlllIlIIIlIII) {
        return llIlllIlIIIlIII == 0;
    }
    
    private static CallSite lIIlIIlII(final MethodHandles.Lookup llIlllIllIlIlIl, final String llIlllIllIlIlll, final MethodType llIlllIllIlIllI) throws IllegalAccessException, NoSuchMethodException {
        try {
            final String[] llIlllIllIllllI = AuthManager.lllIIlI[Integer.parseInt(llIlllIllIlIlll)].split(AuthManager.lllIlII[AuthManager.lIIIllII[10]]);
            final Class<?> llIlllIllIlllIl = Class.forName(llIlllIllIllllI[AuthManager.lIIIllII[0]]);
            final String llIlllIllIlllII = llIlllIllIllllI[AuthManager.lIIIllII[1]];
            MethodHandle llIlllIllIllIll = null;
            final int llIlllIllIllIlI = llIlllIllIllllI[AuthManager.lIIIllII[3]].length();
            if (lIllIIllI(llIlllIllIllIlI, AuthManager.lIIIllII[2])) {
                final MethodType llIlllIlllIIIII = MethodType.fromMethodDescriptorString(llIlllIllIllllI[AuthManager.lIIIllII[2]], AuthManager.class.getClassLoader());
                if (lIllIIlll(llIlllIllIllIlI, AuthManager.lIIIllII[2])) {
                    llIlllIllIllIll = llIlllIllIlIlIl.findVirtual(llIlllIllIlllIl, llIlllIllIlllII, llIlllIlllIIIII);
                    "".length();
                    if ((0x7 ^ 0x2) == 0x0) {
                        return null;
                    }
                }
                else {
                    llIlllIllIllIll = llIlllIllIlIlIl.findStatic(llIlllIllIlllIl, llIlllIllIlllII, llIlllIlllIIIII);
                }
                "".length();
                if (((0x98 ^ 0xBB) & ~(0x83 ^ 0xA0)) == " ".length() << (" ".length() << " ".length())) {
                    return null;
                }
            }
            else {
                final Class llIlllIllIlllll = AuthManager.lllIIll[Integer.parseInt(llIlllIllIllllI[AuthManager.lIIIllII[2]])];
                if (lIllIIlll(llIlllIllIllIlI, AuthManager.lIIIllII[3])) {
                    llIlllIllIllIll = llIlllIllIlIlIl.findGetter(llIlllIllIlllIl, llIlllIllIlllII, llIlllIllIlllll);
                    "".length();
                    if (" ".length() << (" ".length() << " ".length()) == 0) {
                        return null;
                    }
                }
                else if (lIllIIlll(llIlllIllIllIlI, AuthManager.lIIIllII[4])) {
                    llIlllIllIllIll = llIlllIllIlIlIl.findStaticGetter(llIlllIllIlllIl, llIlllIllIlllII, llIlllIllIlllll);
                    "".length();
                    if (-" ".length() < -" ".length()) {
                        return null;
                    }
                }
                else if (lIllIIlll(llIlllIllIllIlI, AuthManager.lIIIllII[5])) {
                    llIlllIllIllIll = llIlllIllIlIlIl.findSetter(llIlllIllIlllIl, llIlllIllIlllII, llIlllIllIlllll);
                    "".length();
                    if ("   ".length() < " ".length()) {
                        return null;
                    }
                }
                else {
                    llIlllIllIllIll = llIlllIllIlIlIl.findStaticSetter(llIlllIllIlllIl, llIlllIllIlllII, llIlllIllIlllll);
                }
            }
            return new ConstantCallSite(llIlllIllIllIll);
        }
        catch (Exception llIlllIllIllIIl) {
            llIlllIllIllIIl.printStackTrace();
            return null;
        }
    }
    
    private static String lIIlIIllI(String llIlllIlIlIllll, final String llIlllIlIlIlllI) {
        llIlllIlIlIllll = new String(Base64.getDecoder().decode(llIlllIlIlIllll.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder llIlllIlIllIIlI = new StringBuilder();
        final char[] llIlllIlIllIIIl = llIlllIlIlIlllI.toCharArray();
        int llIlllIlIllIIII = AuthManager.lIIIllII[0];
        final short llIlllIlIlIlIlI = (Object)llIlllIlIlIllll.toCharArray();
        final float llIlllIlIlIlIIl = llIlllIlIlIlIlI.length;
        boolean llIlllIlIlIlIII = AuthManager.lIIIllII[0] != 0;
        while (lIllIlIII(llIlllIlIlIlIII ? 1 : 0, (int)llIlllIlIlIlIIl)) {
            final char llIlllIlIllIlIl = llIlllIlIlIlIlI[llIlllIlIlIlIII];
            llIlllIlIllIIlI.append((char)(llIlllIlIllIlIl ^ llIlllIlIllIIIl[llIlllIlIllIIII % llIlllIlIllIIIl.length]));
            "".length();
            ++llIlllIlIllIIII;
            ++llIlllIlIlIlIII;
            "".length();
            if (" ".length() <= 0) {
                return null;
            }
        }
        return String.valueOf(llIlllIlIllIIlI);
    }
}
