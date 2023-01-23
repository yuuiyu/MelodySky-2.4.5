//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Managers.GaoNeng;

import by.radioegor146.nativeobfuscator.*;
import net.minecraft.client.entity.*;
import org.apache.logging.log4j.*;
import xyz.Melody.*;
import xyz.Melody.System.Melody.Authentication.*;
import java.nio.charset.*;
import java.lang.invoke.*;
import com.google.gson.*;
import java.io.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;

@Native
public class GaoNengManager
{
    private static final /* synthetic */ String[] lllllll;
    private static /* synthetic */ String[] llllIIl;
    private static /* synthetic */ Class[] llllIlI;
    private static final /* synthetic */ int[] lIIIIIll;
    private static /* synthetic */ String[] lIIIIIlI;
    
    private static void lIlIIIllI() {
        (lllllll = new String[GaoNengManager.lIIIIIll[63]])[GaoNengManager.lIIIIIll[0]] = lIIlllIlI(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[0]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[1]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[1]] = lIIlllIlI(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[2]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[3]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[2]] = lIlIIIIII(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[4]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[5]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[3]] = lIlIIIIIl(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[6]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[7]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[4]] = lIlIIIIIl(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[8]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[9]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[5]] = lIlIIIIII(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[10]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[11]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[6]] = lIIlllIlI(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[12]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[13]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[7]] = lIIlllIlI(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[14]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[15]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[8]] = lIIlllIlI(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[16]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[17]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[9]] = lIlIIIIII(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[18]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[20]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[10]] = lIlIIIIII(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[21]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[24]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[11]] = lIlIIIIIl(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[23]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[26]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[12]] = lIlIIIIII(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[27]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[28]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[13]] = lIlIIIIIl(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[30]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[31]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[14]] = lIlIIIIIl(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[29]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[32]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[15]] = lIIlllIlI(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[33]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[34]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[16]] = lIIlllIlI(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[35]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[37]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[17]] = lIlIIIIII(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[36]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[38]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[18]] = lIlIIIIII(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[39]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[41]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[20]] = lIlIIIIII(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[40]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[42]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[21]] = lIlIIIIIl(GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[25]], GaoNengManager.lIIIIIlI[GaoNengManager.lIIIIIll[22]]);
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[24]] = lIIlllIlI("dPfKnK72M6zj5c7gaCGE/JI9GNwPfhcIej0r6FSBoog25obsIv8bWrF89uIEPW2jNPHdI1jcfVygsodXBkrpFA==", "ncNXO");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[23]] = lIlIIIIIl("0N1f1W6n5YMjlbCWkVElpxwIjcaQD2wXQoW7v+/7kV9GLGlToVS3FmTzmUTmPTrpJv7pDh8KJws=", "eMamh");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[26]] = lIlIIIIII("MzIuZAAuJzsuNGUYLTk5LiZ6BywlKjMvPzhlEysiBS46LWMMKjsEKCUsGSsjKiwxOHctJCYfPydxYHBta2t0am0=", "KKTJM");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[27]] = lIIlllIlI("517ElcGKpNYgQVvcLnMilORQ22bs5QrDMrZ8eU0jvz+1zbxTGEyl3gF37t854jiRtzFKMVEnJP++QIek87XF4LzXNAcwJJD2n7x3H6S3YSw=", "MrESP");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[28]] = lIlIIIIII("ABgXbAgdDQImPFYyFDExHQxDDyQWAAonNwtPKiMqNgQDJWs/AAIMIBYGICMrGQYIMH8LAgwsEREMCDAREBMIIyFCUVdiZVhB", "xamBE");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[30]] = lIIlllIlI("0zo+8SDNvOtUtiAGAJytp617FPBm9scxEa7O/IbFS8E56iNZCD4iKyhRJpz/HJA1", "VgtBY");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[31]] = lIlIIIIIl("y0e1PFIM+BIToiuNZoCa6kJgadx8O58N7r1DrW5zS/7crn4uvBQz5Q==", "JIDsG");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[29]] = lIIlllIlI("pp0ECC7SCwOIY8eq/cNebpSBMuwsxlQoDtsDyUvV3TwYavvg80zXIKAYdFoLQR+pxRQffLUrA3ns3gpbmh8ODA==", "QXOTk");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[32]] = lIlIIIIIl("WG4h/CRr2zNAqLPECSArtxwTqmLTIcRciOkZ76ToC14=", "dUSdq");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[33]] = lIlIIIIII("MA4CWj08DggYP30GHBs0fSscGzQDAB0HPyFbHxUoIARVXBY5ABkVdT8AARN1ABUdHTQ0WkY4OTwMQBM1PAYDEXU0EgAadRkSABofPwQCETQnWlVUeg==", "SaotZ");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[34]] = lIlIIIIII("GQ4RfQgEGwQ3PE8kEiAxBBpFHiQPFgw2NxJZLDIqLxIFNGsmFgQdIA8QJjIrABAOIX8NGAo3AgAYJTYrBgRRe2w3TUs=", "awkSE");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[35]] = lIlIIIIIl("pkgMD5lgcg4aR50sNDZEVnyhE++s9igCLnENLL7gUpR2TdHTOjRXbhrwGXwRqqtyn/Gxkk+XU2oooIAbWqkD9YR3W6RVncuc", "bfWqZ");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[37]] = lIlIIIIII("NQI4VC85AjIWLXgKJhUmeCcmFSYTATAXLTgZbx0tIiwmOCc5ATAbJmxFfCBydk0=", "VmUzH");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[36]] = lIlIIIIII("GhQUdiYHAQE8EkwuAjEODBlUMQURGQ82CAdXXGJLQk1O", "bmnXk");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[38]] = lIlIIIIIl("vCp4wMoJcoCF0c298Dg3gCZWMfQqP7ecSJgQbi7vuwL+nY2BO+cZFfEd6XCDEblQr8XD46M2Qf8=", "hxZzP");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[39]] = lIIlllIlI("jdi4cQZaCEf8CWbb2Mztu0TH5+F2e9n/FSF0/rMP/EkxOKqIx0PxZQDijh9kW8iNDA5G9dKQ2+rcWKRxHv0Jh/3JS6JeeaaLRHDjWJPh208XCu+Yg9/R9K2nbBmXoWHlhMKbJnjvKTU=", "nIvoH");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[41]] = lIIlllIlI("2Js+NQ6hLGnSGOsa+IRhwqMWmctL4aMbsfekXhNArkAEuVxfkpri2ka0h1w329ULJRDc9DU3wheLEc/PrbIUuw==", "UeNpw");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[40]] = lIlIIIIII("JyEhaB4gKjAlASgiIWgQJS0wKAdnITsyGj09ewMdPS0hPyMlJSwjARoUbyAGJycKd0d/dGxyLCh+fQoQJil6KxwjJTshXCgxIS4fICZ6ARIkIQU0HC8tOSNIYAg/JwUoayAyGiVrABM6DX9vZg==", "IDUFs");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[42]] = lIIlllIlI("N6i331gVyEK4RPjMFxZBV3/t7TsjErpgK2BnUgArmV6lRi9LSaihyCEJSixS/e95K45mdk8G+Ls=", "XSuzp");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[25]] = lIlIIIIII("ETw8RDMdPDYGMVw0IgU6XBkiBTo9MTsPNwZpNAQgACoCDyBIe3gmPhMlMEUhBjo9RQcXJ2pQdFI=", "rSQjT");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[22]] = lIIlllIlI("0UJizkcT5M+w2hh9l0yMj4B6DLAAwdpLojHYRODaTw8wIk4w9qoCHYywtDyJ+CptH1w97FWe/WhHBGCFT6swMNTcWkVFe9eneRbo4pH7DxcNZj4+RWWT6ezli/RekJhtxC3WPpt47Bf/pRz1u3D9Qg==", "YVeQa");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[43]] = lIIlllIlI("fz/dquieFEfmXF79qDnyhwqjuxgshLvlygfQ2gcJVpI8+344HNoCeBht1nUmGoXNTIWWyp4pJIk=", "pZwiz");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[44]] = lIlIIIIIl("bdl7NB5mOSgUj2SxOg/05KFdNzp8TFUvksKkySx91lS5xHafntspo5bmqaiZG8U4m01EYRq6XgdnK9NFR7Xnsx7+XpEXvuTuF7BrZcvs6hDtzyoc2LflLw==", "ZxckV");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[45]] = lIlIIIIIl("cZCE9HWcOThr95ONfLXlpMKKYwChoE9DNG3NetDnBcLWnYp3hUrPkSC6z6bqnF9Ls0idTUmlcTEeb9gdNI2P8rhozUoyYk4XwySm13OFjIw=", "SBGiG");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[46]] = lIlIIIIIl("SZZpjyoZdZO1UsWcig3zexLihqVpCvtmKOnAKhx2SnZEcvsb4hr3l0Yex+s5bAx6g3ZuIE9RYTXIkOhp33lVIWG64CVCsYfd6eNCtnyNo0a96/VyZP66+hVzuumc9KThoJWI07/vAi4=", "RHerJ");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[47]] = lIIlllIlI("M2Q1LPdQlGSgxbSaqeGZcMx5wOqZqodb6x89B4USpWY=", "dApZI");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[48]] = lIlIIIIII("PwQleSc4DzQ0ODAHJXkpPQg0OT5/BD8jIyUYfxIkJQglLho9ACgyOAIxazE/PwIOZn5nUGFkFTMpa39jHQI+OmU8Djs2JDZOMCI+OQ04NWUWADwyGiMONz4mNFprd2o=", "QaQWJ");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[49]] = lIlIIIIII("KBsYMXY3Dgc8dg8bHmo/Jw5UeBQoGxgxdy4bADd3DRgENTs2QUccMiMMD380IxQJfxcgEAszLHlATnA=", "BznPX");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[50]] = lIlIIIIII("ExIDA0gMBxwOSDAHEBAHDRwHWA4YADsHHg1JXUs8Q1NV", "ysubf");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[51]] = lIIlllIlI("ySpuJi/QKkNvs4bLaIHIb5zpw4ougAG0ctKvrBnDdqaVfwdQMQNjsbq5fwRhgGSAUmRgWHsf/wcBxsC8ysrbimDvtGfLKR6A02jGGQR137E=", "ffDFV");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[52]] = lIlIIIIII("KiwdQg83OQgIO3wWCwUnPCFdAC01MgIeeGdvR0xi", "RUglB");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[53]] = lIlIIIIIl("xAznoZ+kCm/UYYDl9Fr8yUwN+JKHBRvjYOfLvxKPAqBn2BU7qhGlvKAoLKpR47JeIvUDV/WbO972pU7ES/x85w==", "xQJPD");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[54]] = lIlIIIIII("Gxg7ZQoGDS4vPk0yODgzBgxvBiYNACYuNRBPBiooLQQvLGkkAC4FIg0GDCopAgYkOX0EAC4FIg0GMnF2WUFha2dDQQ==", "caAKG");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[55]] = lIlIIIIII("KBAGBVkuEB4DWREFAg0ZJUsCAQcuEBMBNi4dSkw7KBAGBVguEB4DWBEFAg0ZJUo8DhY0EF8IFiwWXzcDMBgeA0xrPRoFASNeHAUZJV4jEAUrHxdfTWJR", "Bqpdw");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[56]] = lIlIIIIIl("oHHqIicUC4YnRfxKUAU1gmsSjhHkMiseAoAFlvdBgaZ2UCMJ6KlluUHrdYD1RMEQ", "jmuFG");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[57]] = lIIlllIlI("15i5qx6z0QPaObOyeLur7CBq0uYm6R+jxkoRVovMdUEgatLmJukfo5HbPbWKJg/b/TD3acwtZywlgdP0G3s0xMom/hfNj+iSLJE+2KiZSxU=", "nfBGL");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[58]] = lIIlllIlI("HxKxm04xKKDTu1Phrtu2PC36231nPxOcEgri+WoKowQzsqETqd3zdnGwcgJ9fz8CljhZgicddJNCzC6EQCSRb+w2+2zrfkrSYOh/znI0Dug=", "BylOO");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[59]] = lIlIIIIII("EBgeM38WGAY1fz8BCzchDhAHPGsKCwE8JSkNCTE6LgsJMTRAUUEEa1pZ", "zyhRQ");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[60]] = lIlIIIIIl("JpozdRFGhdza7EWAm8IVrvNPIUIqx0n2zSFolxK2Gr2ec9ySMh2uPlJQ3mpSEAYjLQakrYlDer6wyOw40f3Nlv18KwfxbTGyoLXBlCF0Pns=", "NfWWs");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[61]] = lIIlllIlI("4+RvQyDOs21ujcrI8z4JZVm6HDJIAv3EL9BtW/8wp7KYIS7pa5aHCtqUlt4vPROaj++ujJgKcZXZ2ousCH100EirWkvA4e4Z/8pNIkG2DsPFpMD2yVT6SoG0WRh3XNyDrqCt6Nrb0C4BeZlgURKExSQPBSTjGd+apNC4XEmczV2uREItiqf7tQ==", "gCkub");
        GaoNengManager.lllllll[GaoNengManager.lIIIIIll[62]] = lIlIIIIII("CCcmF3cXMjkady8nIFIcDDIiD2MFIyQgOA4zNUxxSwo6Fy8DaTwXNwVpHxQzByUkTWNCZg==", "bFPvY");
        GaoNengManager.lIIIIIlI = null;
    }
    
    private static boolean lIlIIlIll(final int lllIIIIIllIlIll, final int lllIIIIIllIlIlI) {
        return lllIIIIIllIlIll <= lllIIIIIllIlIlI;
    }
    
    private static String getPlayerUUID(final EntityOtherPlayerMP lllIIIIllIIllIl) {
        if (lIlIIlIlI(lllIIIIllIIllIl)) {
            return GaoNengManager.lllllll[GaoNengManager.lIIIIIll[16]];
        }
        final String lllIIIIllIIllll = invokedynamic(15:(Ljava/util/UUID;)Ljava/lang/String;, invokedynamic(37:(Lcom/mojang/authlib/GameProfile;)Ljava/util/UUID;, invokedynamic(36:(Lnet/minecraft/client/entity/EntityOtherPlayerMP;)Lcom/mojang/authlib/GameProfile;, lllIIIIllIIllIl)));
        final String lllIIIIllIIlllI = invokedynamic(16:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, lllIIIIllIIllll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[17]], GaoNengManager.lllllll[GaoNengManager.lIIIIIll[18]]);
        return lllIIIIllIIlllI;
    }
    
    public static GaoNeng getIfIsGaoNeng(final EntityPlayerSP lllIIIlIIIlIIlI) {
        if (lIlIIlIIl(invokedynamic(11:(Ljava/util/Map;Ljava/lang/Object;)Z, invokedynamic(9:()Ljava/util/Map;), invokedynamic(16:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, invokedynamic(15:(Ljava/util/UUID;)Ljava/lang/String;, invokedynamic(14:(Lcom/mojang/authlib/GameProfile;)Ljava/util/UUID;, invokedynamic(13:(Lnet/minecraft/client/entity/EntityPlayerSP;)Lcom/mojang/authlib/GameProfile;, lllIIIlIIIlIIlI))), GaoNengManager.lllllll[GaoNengManager.lIIIIIll[1]], GaoNengManager.lllllll[GaoNengManager.lIIIIIll[2]])))) {
            return (GaoNeng)invokedynamic(12:(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;, invokedynamic(9:()Ljava/util/Map;), invokedynamic(16:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, invokedynamic(15:(Ljava/util/UUID;)Ljava/lang/String;, invokedynamic(14:(Lcom/mojang/authlib/GameProfile;)Ljava/util/UUID;, invokedynamic(13:(Lnet/minecraft/client/entity/EntityPlayerSP;)Lcom/mojang/authlib/GameProfile;, lllIIIlIIIlIIlI))), GaoNengManager.lllllll[GaoNengManager.lIIIIIll[3]], GaoNengManager.lllllll[GaoNengManager.lIIIIIll[4]]));
        }
        return null;
    }
    
    private static void lIIllIIIl() {
        (GaoNengManager.llllIIl = new String[GaoNengManager.lIIIIIll[22]])[GaoNengManager.lIIIIIll[23]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[24]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[2]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[23]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[25]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[26]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[5]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[27]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[1]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[28]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[29]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[30]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[18]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[31]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[20]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[29]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[3]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[32]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[26]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[33]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[4]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[34]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[9]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[35]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[36]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[37]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[17]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[36]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[32]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[38]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[21]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[39]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[40]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[41]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[14]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[40]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[30]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[42]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[28]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[25]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[41]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[22]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[11]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[43]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[35]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[44]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[7]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[45]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[39]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[46]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[42]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[47]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[13]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[48]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[12]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[49]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[31]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[50]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[38]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[51]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[24]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[52]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[37]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[53]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[6]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[54]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[16]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[55]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[15]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[56]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[27]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[57]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[34]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[58]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[8]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[59]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[0]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[60]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[10]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[61]];
        GaoNengManager.llllIIl[GaoNengManager.lIIIIIll[33]] = GaoNengManager.lllllll[GaoNengManager.lIIIIIll[62]];
        (GaoNengManager.llllIlI = new Class[GaoNengManager.lIIIIIll[6]])[GaoNengManager.lIIIIIll[0]] = Thread.class;
        GaoNengManager.llllIlI[GaoNengManager.lIIIIIll[1]] = Map.class;
        GaoNengManager.llllIlI[GaoNengManager.lIIIIIll[5]] = Logger.class;
        GaoNengManager.llllIlI[GaoNengManager.lIIIIIll[2]] = Client.class;
        GaoNengManager.llllIlI[GaoNengManager.lIIIIIll[4]] = String.class;
        GaoNengManager.llllIlI[GaoNengManager.lIIIIIll[3]] = AuthManager.class;
    }
    
    public static void load() {
        try {
            // invokedynamic(4:()V)
            // invokedynamic(6:(Ljava/util/Map;)V, invokedynamic(5:()Ljava/util/Map;))
            // invokedynamic(7:(Ljava/util/Map;)V, new HashMap())
            "".length();
            if ((0x74 ^ 0x37 ^ (0xBE ^ 0x9D) << " ".length()) <= 0) {
                return;
            }
        }
        catch (Exception lllIIIlIIIllIIl) {
        }
        // invokedynamic(8:(Ljava/lang/Exception;)V, lllIIIlIIIllIIl)
    }
    
    private static boolean lIlIIllII(final int lllIIIIIlllIIll, final int lllIIIIIlllIIlI) {
        return lllIIIIIlllIIll == lllIIIIIlllIIlI;
    }
    
    private static String lIlIIIIII(String lllIIIIlIIllIII, final String lllIIIIlIIlllII) {
        lllIIIIlIIllIII = new String(Base64.getDecoder().decode(lllIIIIlIIllIII.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder lllIIIIlIIllIll = new StringBuilder();
        final char[] lllIIIIlIIllIlI = lllIIIIlIIlllII.toCharArray();
        int lllIIIIlIIllIIl = GaoNengManager.lIIIIIll[0];
        final long lllIIIIlIIlIIll = (Object)lllIIIIlIIllIII.toCharArray();
        final long lllIIIIlIIlIIlI = lllIIIIlIIlIIll.length;
        double lllIIIIlIIlIIIl = GaoNengManager.lIIIIIll[0];
        while (lIlIIllIl((int)lllIIIIlIIlIIIl, (int)lllIIIIlIIlIIlI)) {
            final char lllIIIIlIIllllI = lllIIIIlIIlIIll[lllIIIIlIIlIIIl];
            lllIIIIlIIllIll.append((char)(lllIIIIlIIllllI ^ lllIIIIlIIllIlI[lllIIIIlIIllIIl % lllIIIIlIIllIlI.length]));
            "".length();
            ++lllIIIIlIIllIIl;
            ++lllIIIIlIIlIIIl;
            "".length();
            if (" ".length() << " ".length() != " ".length() << " ".length()) {
                return null;
            }
        }
        return String.valueOf(lllIIIIlIIllIll);
    }
    
    public static GaoNeng getIfIsGaoNeng(final EntityOtherPlayerMP lllIIIlIIIlIlIl) {
        if (lIlIIlIIl(invokedynamic(11:(Ljava/util/Map;Ljava/lang/Object;)Z, invokedynamic(9:()Ljava/util/Map;), invokedynamic(10:(Lnet/minecraft/client/entity/EntityOtherPlayerMP;)Ljava/lang/String;, lllIIIlIIIlIlIl)))) {
            return (GaoNeng)invokedynamic(12:(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;, invokedynamic(9:()Ljava/util/Map;), invokedynamic(10:(Lnet/minecraft/client/entity/EntityOtherPlayerMP;)Ljava/lang/String;, lllIIIlIIIlIlIl));
        }
        return null;
    }
    
    private static CallSite lIIllIIII(final MethodHandles.Lookup lllIIIIlIllIlII, final String lllIIIIlIllIIII, final MethodType lllIIIIlIllIIlI) throws IllegalAccessException, NoSuchMethodException {
        try {
            final String[] lllIIIIlIlllIlI = GaoNengManager.llllIIl[Integer.parseInt(lllIIIIlIllIIII)].split(GaoNengManager.lllllll[GaoNengManager.lIIIIIll[21]]);
            final Class<?> lllIIIIlIlllIIl = Class.forName(lllIIIIlIlllIlI[GaoNengManager.lIIIIIll[0]]);
            final String lllIIIIlIlllIII = lllIIIIlIlllIlI[GaoNengManager.lIIIIIll[1]];
            MethodHandle lllIIIIlIllIlll = null;
            final int lllIIIIlIllIllI = lllIIIIlIlllIlI[GaoNengManager.lIIIIIll[3]].length();
            if (lIlIIlIll(lllIIIIlIllIllI, GaoNengManager.lIIIIIll[2])) {
                final MethodType lllIIIIlIllllII = MethodType.fromMethodDescriptorString(lllIIIIlIlllIlI[GaoNengManager.lIIIIIll[2]], GaoNengManager.class.getClassLoader());
                if (lIlIIllII(lllIIIIlIllIllI, GaoNengManager.lIIIIIll[2])) {
                    lllIIIIlIllIlll = lllIIIIlIllIlII.findVirtual(lllIIIIlIlllIIl, lllIIIIlIlllIII, lllIIIIlIllllII);
                    "".length();
                    if (" ".length() << (" ".length() << " ".length()) <= ((0x30 ^ 0x5F ^ (0x59 ^ 0x6C) << " ".length()) << "   ".length() & ((" ".length() ^ " ".length() << (" ".length() << " ".length())) << "   ".length() ^ -" ".length()))) {
                        return null;
                    }
                }
                else {
                    lllIIIIlIllIlll = lllIIIIlIllIlII.findStatic(lllIIIIlIlllIIl, lllIIIIlIlllIII, lllIIIIlIllllII);
                }
                "".length();
                if (-" ".length() == (((0xB0 ^ 0xA5) << "   ".length() ^ 93 + 71 - 153 + 164) << (" ".length() << " ".length()) & ((" ".length() << (" ".length() << (" ".length() << " ".length())) ^ (0xB8 ^ 0xAF)) << (" ".length() << " ".length()) ^ -" ".length()))) {
                    return null;
                }
            }
            else {
                final Class lllIIIIlIlllIll = GaoNengManager.llllIlI[Integer.parseInt(lllIIIIlIlllIlI[GaoNengManager.lIIIIIll[2]])];
                if (lIlIIllII(lllIIIIlIllIllI, GaoNengManager.lIIIIIll[3])) {
                    lllIIIIlIllIlll = lllIIIIlIllIlII.findGetter(lllIIIIlIlllIIl, lllIIIIlIlllIII, lllIIIIlIlllIll);
                    "".length();
                    if (null != null) {
                        return null;
                    }
                }
                else if (lIlIIllII(lllIIIIlIllIllI, GaoNengManager.lIIIIIll[4])) {
                    lllIIIIlIllIlll = lllIIIIlIllIlII.findStaticGetter(lllIIIIlIlllIIl, lllIIIIlIlllIII, lllIIIIlIlllIll);
                    "".length();
                    if (" ".length() << " ".length() < " ".length()) {
                        return null;
                    }
                }
                else if (lIlIIllII(lllIIIIlIllIllI, GaoNengManager.lIIIIIll[5])) {
                    lllIIIIlIllIlll = lllIIIIlIllIlII.findSetter(lllIIIIlIlllIIl, lllIIIIlIlllIII, lllIIIIlIlllIll);
                    "".length();
                    if (" ".length() == 0) {
                        return null;
                    }
                }
                else {
                    lllIIIIlIllIlll = lllIIIIlIllIlII.findStaticSetter(lllIIIIlIlllIIl, lllIIIIlIlllIII, lllIIIIlIlllIll);
                }
            }
            return new ConstantCallSite(lllIIIIlIllIlll);
        }
        catch (Exception lllIIIIlIllIlIl) {
            lllIIIIlIllIlIl.printStackTrace();
            return null;
        }
    }
    
    private static boolean lIlIIllIl(final int lllIIIIIllIllll, final int lllIIIIIllIlllI) {
        return lllIIIIIllIllll < lllIIIIIllIlllI;
    }
    
    public static void loadGaoNengs() throws IOException {
        final String lllIIIIlllIlIll = invokedynamic(20:(Lxyz/Melody/System/Melody/Authentication/AuthManager;Ljava/lang/String;)Ljava/lang/String;, invokedynamic(18:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Authentication/AuthManager;, invokedynamic(17:()Lxyz/Melody/Client;)), invokedynamic(19:()Ljava/lang/String;));
        final String lllIIIIlllIlIlI = invokedynamic(20:(Lxyz/Melody/System/Melody/Authentication/AuthManager;Ljava/lang/String;)Ljava/lang/String;, invokedynamic(18:(Lxyz/Melody/Client;)Lxyz/Melody/System/Melody/Authentication/AuthManager;, invokedynamic(17:()Lxyz/Melody/Client;)), lllIIIIlllIlIll);
        if (lIlIIlIlI(lllIIIIlllIlIlI)) {
            // invokedynamic(22:(Lorg/apache/logging/log4j/Logger;Ljava/lang/String;)V, invokedynamic(21:(Lxyz/Melody/Client;)Lorg/apache/logging/log4j/Logger;, invokedynamic(17:()Lxyz/Melody/Client;)), GaoNengManager.lllllll[GaoNengManager.lIIIIIll[5]])
            return;
        }
        final JsonObject lllIIIIlllIlIIl = (JsonObject)invokedynamic(23:(Lcom/google/gson/JsonParser;Ljava/lang/String;)Lcom/google/gson/JsonElement;, new JsonParser(), lllIIIIlllIlIlI);
        if (lIlIIlIlI(lllIIIIlllIlIIl)) {
            return;
        }
        final JsonObject lllIIIIlllIlIII = invokedynamic(24:(Lcom/google/gson/JsonObject;)Lcom/google/gson/JsonObject;, lllIIIIlllIlIIl);
        final Exception lllIIIIlllIIIll = invokedynamic(26:(Ljava/util/Set;)Ljava/util/Iterator;, invokedynamic(25:(Lcom/google/gson/JsonObject;)Ljava/util/Set;, lllIIIIlllIlIII));
        while (lIlIIlIIl(invokedynamic(27:(Ljava/util/Iterator;)Z, lllIIIIlllIIIll))) {
            final Map.Entry<String, JsonElement> lllIIIIlllIllII = (Map.Entry<String, JsonElement>)(Map.Entry)invokedynamic(28:(Ljava/util/Iterator;)Ljava/lang/Object;, lllIIIIlllIIIll);
            final String lllIIIIlllllIlI = (String)invokedynamic(29:(Ljava/util/Map$Entry;)Ljava/lang/Object;, lllIIIIlllIllII);
            GaoNeng lllIIIIlllllIIl = (GaoNeng)invokedynamic(12:(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;, invokedynamic(5:()Ljava/util/Map;), lllIIIIlllllIlI);
            int n;
            if (lIlIIlIlI(lllIIIIlllllIIl)) {
                n = GaoNengManager.lIIIIIll[1];
                "".length();
                if (" ".length() << " ".length() == (((0x99 ^ 0x96) << (" ".length() << (" ".length() << " ".length())) ^ 152 + 24 - 155 + 140) & (0x4 ^ 0x5F ^ (0x8E ^ 0x8B) << " ".length() ^ -" ".length()))) {
                    return;
                }
            }
            else {
                n = GaoNengManager.lIIIIIll[0];
            }
            final boolean lllIIIIlllllIII = n != 0;
            final JsonObject lllIIIIllllIlll = invokedynamic(31:(Lcom/google/gson/JsonElement;)Lcom/google/gson/JsonObject;, (JsonElement)invokedynamic(30:(Ljava/util/Map$Entry;)Ljava/lang/Object;, lllIIIIlllIllII));
            final String lllIIIIllllIllI = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[6]]));
            final String lllIIIIllllIlIl = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[7]]));
            final String lllIIIIllllIlII = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[8]]));
            final String lllIIIIllllIIll = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[9]]));
            final String lllIIIIllllIIlI = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[10]]));
            final String lllIIIIllllIIIl = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[11]]));
            final String lllIIIIllllIIII = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[12]]));
            final String lllIIIIlllIllll = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[13]]));
            final String lllIIIIlllIlllI = invokedynamic(33:(Lcom/google/gson/JsonElement;)Ljava/lang/String;, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[14]]));
            final boolean lllIIIIlllIllIl = invokedynamic(34:(Lcom/google/gson/JsonElement;)Z, invokedynamic(32:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;, lllIIIIllllIlll, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[15]]));
            if (lIlIIlIIl(lllIIIIlllllIII ? 1 : 0)) {
                lllIIIIlllllIIl = new GaoNeng(lllIIIIllllIllI, lllIIIIllllIlIl, lllIIIIllllIlII, lllIIIIllllIIll, lllIIIIllllIIlI, lllIIIIllllIIIl, lllIIIIllllIIII, lllIIIIlllIllll, lllIIIIlllIlllI, lllIIIIlllIllIl);
                // invokedynamic(35:(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, invokedynamic(5:()Ljava/util/Map;), invokedynamic(29:(Ljava/util/Map$Entry;)Ljava/lang/Object;, lllIIIIlllIllII), lllIIIIlllllIIl)
                "".length();
            }
            "".length();
            if (((0xD8 ^ 0x8D ^ (0x14 ^ 0x2F) << " ".length()) << " ".length() & (((0x64 ^ 0x4F) << (" ".length() << " ".length()) ^ 48 + 122 - 156 + 129) << " ".length() ^ -" ".length())) > " ".length() << " ".length()) {
                return;
            }
        }
    }
    
    private static boolean lIlIIlIlI(final Object lllIIIIIllIlIII) {
        return lllIIIIIllIlIII == null;
    }
    
    private static boolean lIlIIlIIl(final int lllIIIIIllIIllI) {
        return lllIIIIIllIIllI != 0;
    }
    
    private static void lIlIIlIII() {
        (lIIIIIll = new int[64])[0] = (" ".length() & ~" ".length());
        GaoNengManager.lIIIIIll[1] = " ".length();
        GaoNengManager.lIIIIIll[2] = " ".length() << " ".length();
        GaoNengManager.lIIIIIll[3] = "   ".length();
        GaoNengManager.lIIIIIll[4] = " ".length() << (" ".length() << " ".length());
        GaoNengManager.lIIIIIll[5] = (0xC0 ^ 0x87 ^ (0x6C ^ 0x4D) << " ".length());
        GaoNengManager.lIIIIIll[6] = "   ".length() << " ".length();
        GaoNengManager.lIIIIIll[7] = (0x3F ^ 0x38);
        GaoNengManager.lIIIIIll[8] = " ".length() << "   ".length();
        GaoNengManager.lIIIIIll[9] = (0xAB ^ 0xA2);
        GaoNengManager.lIIIIIll[10] = (57 + 138 - 181 + 147 ^ (0x29 ^ 0x0) << (" ".length() << " ".length())) << " ".length();
        GaoNengManager.lIIIIIll[11] = ((0x93 ^ 0xB6) << (" ".length() << " ".length()) ^ 91 + 107 - 175 + 136);
        GaoNengManager.lIIIIIll[12] = "   ".length() << (" ".length() << " ".length());
        GaoNengManager.lIIIIIll[13] = (0xAC ^ 0xA1);
        GaoNengManager.lIIIIIll[14] = (0x96 ^ 0xAB ^ (0x3A ^ 0x27) << " ".length()) << " ".length();
        GaoNengManager.lIIIIIll[15] = (0x54 ^ 0x9 ^ (0x1 ^ 0x28) << " ".length());
        GaoNengManager.lIIIIIll[16] = " ".length() << (" ".length() << (" ".length() << " ".length()));
        GaoNengManager.lIIIIIll[17] = (" ".length() << ("   ".length() << " ".length()) ^ (0x62 ^ 0x33));
        GaoNengManager.lIIIIIll[18] = (0x19 ^ 0x10) << " ".length();
        GaoNengManager.lIIIIIll[19] = 189 + 1 - 99 + 192 + (739 + 1444 - 38 + 272) - -(855 + 1115 - 1554 + 1966) + (1923 + 4197 - 3511 + 1684) << (" ".length() << "   ".length() ^ (0x8E ^ 0x83));
        GaoNengManager.lIIIIIll[20] = (150 + 148 - 173 + 28 ^ (0xE ^ 0x4B) << " ".length());
        GaoNengManager.lIIIIIll[21] = (0x90 ^ 0x95) << (" ".length() << " ".length());
        GaoNengManager.lIIIIIll[22] = (0x1F ^ 0x36);
        GaoNengManager.lIIIIIll[23] = (0xF ^ 0x4) << " ".length();
        GaoNengManager.lIIIIIll[24] = (0x35 ^ 0x20);
        GaoNengManager.lIIIIIll[25] = ((0x4E ^ 0x73) << " ".length() ^ 81 + 65 - 116 + 97) << "   ".length();
        GaoNengManager.lIIIIIll[26] = (0x1 ^ 0x16);
        GaoNengManager.lIIIIIll[27] = "   ".length() << "   ".length();
        GaoNengManager.lIIIIIll[28] = (14 + 105 + 12 + 16 ^ (0x41 ^ 0x4) << " ".length());
        GaoNengManager.lIIIIIll[29] = (0xB8 ^ 0xBF) << (" ".length() << " ".length());
        GaoNengManager.lIIIIIll[30] = (0x43 ^ 0x4E) << " ".length();
        GaoNengManager.lIIIIIll[31] = (40 + 98 - 35 + 60 ^ (0x8 ^ 0x1F) << "   ".length());
        GaoNengManager.lIIIIIll[32] = (0xDF ^ 0xC2);
        GaoNengManager.lIIIIIll[33] = (0x9D ^ 0x92) << " ".length();
        GaoNengManager.lIIIIIll[34] = ((0x1C ^ 0xD) << " ".length() ^ (0x45 ^ 0x78));
        GaoNengManager.lIIIIIll[35] = " ".length() << (" ".length() << ("   ".length() << " ".length()) ^ (0xDA ^ 0x9F));
        GaoNengManager.lIIIIIll[36] = (0x76 ^ 0x67) << " ".length();
        GaoNengManager.lIIIIIll[37] = (0x14 ^ 0x35);
        GaoNengManager.lIIIIIll[38] = (0x66 ^ 0x45);
        GaoNengManager.lIIIIIll[39] = (0x24 ^ 0x61 ^ (0x75 ^ 0x66) << (" ".length() << " ".length())) << (" ".length() << " ".length());
        GaoNengManager.lIIIIIll[40] = (" ".length() << "   ".length() ^ (0xA6 ^ 0xBD)) << " ".length();
        GaoNengManager.lIIIIIll[41] = ((" ".length() << (" ".length() << (" ".length() << " ".length())) & ~(" ".length() << (" ".length() << (" ".length() << " ".length())))) ^ (0xB3 ^ 0x96));
        GaoNengManager.lIIIIIll[42] = (0xD5 ^ 0x8E ^ (0xA ^ 0x15) << (" ".length() << " ".length()));
        GaoNengManager.lIIIIIll[43] = ((0x6D ^ 0x4C) << (" ".length() << " ".length()) ^ 113 + 32 - 75 + 75) << " ".length();
        GaoNengManager.lIIIIIll[44] = (0xBA ^ 0x91);
        GaoNengManager.lIIIIIll[45] = ((0x43 ^ 0x2) << " ".length() ^ 65 + 86 - 76 + 62) << (" ".length() << " ".length());
        GaoNengManager.lIIIIIll[46] = (0x71 ^ 0x5C);
        GaoNengManager.lIIIIIll[47] = (0x3F ^ 0x28) << " ".length();
        GaoNengManager.lIIIIIll[48] = ((0xED ^ 0xA2) << " ".length() ^ 150 + 100 - 105 + 32);
        GaoNengManager.lIIIIIll[49] = "   ".length() << (" ".length() << (" ".length() << " ".length()));
        GaoNengManager.lIIIIIll[50] = (0x6 ^ 0x37);
        GaoNengManager.lIIIIIll[51] = (0x8 ^ 0x1B ^ (0x7C ^ 0x79) << " ".length()) << " ".length();
        GaoNengManager.lIIIIIll[52] = (61 + 103 - 43 + 54 ^ (0xE7 ^ 0xC0) << (" ".length() << " ".length()));
        GaoNengManager.lIIIIIll[53] = (0x9A ^ 0xAB ^ (0xE ^ 0x1) << (" ".length() << " ".length())) << (" ".length() << " ".length());
        GaoNengManager.lIIIIIll[54] = ((0x5D ^ 0x60) << (" ".length() << " ".length()) ^ 156 + 134 - 136 + 39);
        GaoNengManager.lIIIIIll[55] = (0x3E ^ 0x25) << " ".length();
        GaoNengManager.lIIIIIll[56] = (0x51 ^ 0x66);
        GaoNengManager.lIIIIIll[57] = (0x5A ^ 0x5D) << "   ".length();
        GaoNengManager.lIIIIIll[58] = (0xAD ^ 0x94);
        GaoNengManager.lIIIIIll[59] = (0xDE ^ 0xC3) << " ".length();
        GaoNengManager.lIIIIIll[60] = ((0x7E ^ 0x77) << (" ".length() << (" ".length() << " ".length())) ^ 84 + 138 - 170 + 119);
        GaoNengManager.lIIIIIll[61] = (15 + 126 - 13 + 39 ^ (0x2F ^ 0x3A) << "   ".length()) << (" ".length() << " ".length());
        GaoNengManager.lIIIIIll[62] = (0x8B ^ 0xB6);
        GaoNengManager.lIIIIIll[63] = (0x2D ^ 0x32) << " ".length();
    }
    
    public static GaoNeng getIfIsGaoNeng(final String lllIIIlIIIIllll) {
        if (lIlIIlIIl(invokedynamic(11:(Ljava/util/Map;Ljava/lang/Object;)Z, invokedynamic(9:()Ljava/util/Map;), lllIIIlIIIIllll))) {
            return (GaoNeng)invokedynamic(12:(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;, invokedynamic(9:()Ljava/util/Map;), lllIIIlIIIIllll);
        }
        return null;
    }
    
    private static String lIlIIIIIl(final String lllIIIIIllllIll, final String lllIIIIIllllIII) {
        try {
            final SecretKeySpec lllIIIIIllllllI = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(lllIIIIIllllIII.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher lllIIIIIlllllIl = Cipher.getInstance("Blowfish");
            lllIIIIIlllllIl.init(GaoNengManager.lIIIIIll[2], lllIIIIIllllllI);
            return new String(lllIIIIIlllllIl.doFinal(Base64.getDecoder().decode(lllIIIIIllllIll.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIIIIIlllllII) {
            lllIIIIIlllllII.printStackTrace();
            return null;
        }
    }
    
    private static void lIlIIIlll() {
        final String lllIIIIlIlIlIII = new Exception().getStackTrace()[GaoNengManager.lIIIIIll[0]].getFileName();
        GaoNengManager.lIIIIIlI = lllIIIIlIlIlIII.substring(lllIIIIlIlIlIII.indexOf("\u00e4") + GaoNengManager.lIIIIIll[1], lllIIIIlIlIlIII.lastIndexOf("\u00fc")).split("\u00f6");
    }
    
    public static void registerTimer() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokedynamic   BootstrapMethod #1, run:()Ljava/lang/Runnable;
        //     9: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;)V
        //    12: invokedynamic   BootstrapMethod #0, 0:(Ljava/lang/Thread;)V
        //    17: invokedynamic   BootstrapMethod #0, 1:()Ljava/lang/Thread;
        //    22: getstatic       xyz/Melody/System/Managers/GaoNeng/GaoNengManager.lllllll:[Ljava/lang/String;
        //    25: getstatic       xyz/Melody/System/Managers/GaoNeng/GaoNengManager.lIIIIIll:[I
        //    28: iconst_0       
        //    29: iaload         
        //    30: aaload         
        //    31: invokedynamic   BootstrapMethod #0, 2:(Ljava/lang/Thread;Ljava/lang/String;)V
        //    36: invokedynamic   BootstrapMethod #0, 1:()Ljava/lang/Thread;
        //    41: invokedynamic   BootstrapMethod #0, 3:(Ljava/lang/Thread;)V
        //    46: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Unsupported node type: com.strobel.decompiler.ast.Lambda
        //     at com.strobel.decompiler.ast.Error.unsupportedNode(Error.java:32)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:612)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:586)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:598)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:586)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:590)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:586)
        //     at com.strobel.decompiler.ast.GotoRemoval.transformLeaveStatements(GotoRemoval.java:625)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotosCore(GotoRemoval.java:57)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotos(GotoRemoval.java:53)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:276)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static String lIIlllIlI(final String lllIIIIlIIIlIII, final String lllIIIIlIIIIlIl) {
        try {
            final SecretKeySpec lllIIIIlIIIlIll = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(lllIIIIlIIIIlIl.getBytes(StandardCharsets.UTF_8)), GaoNengManager.lIIIIIll[8]), "DES");
            final Cipher lllIIIIlIIIlIlI = Cipher.getInstance("DES");
            lllIIIIlIIIlIlI.init(GaoNengManager.lIIIIIll[2], lllIIIIlIIIlIll);
            return new String(lllIIIIlIIIlIlI.doFinal(Base64.getDecoder().decode(lllIIIIlIIIlIII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception lllIIIIlIIIlIIl) {
            lllIIIIlIIIlIIl.printStackTrace();
            return null;
        }
    }
    
    static {
        lIlIIlIII();
        lIlIIIlll();
        lIlIIIllI();
        lIIllIIIl();
    }
    // invokedynamic(6:(Ljava/util/Map;)V, new HashMap())
    // invokedynamic(7:(Ljava/util/Map;)V, new HashMap())
    // invokedynamic(40:(Ljava/lang/String;)V, GaoNengManager.lllllll[GaoNengManager.lIIIIIll[20]])
    
    public static class GaoNeng
    {
        private static /* synthetic */ Class[] llIlIII;
        private static final /* synthetic */ int[] lIIIlIII;
        private static /* synthetic */ String[] llIlIlI;
        private static final /* synthetic */ String[] llIlIIl;
        private static /* synthetic */ String[] llIIlll;
        
        public String getName() {
            return invokedynamic(10:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        private static String lIIIlIIlI(final String llIllllllIlIIlI, final String llIllllllIlIIIl) {
            try {
                final SecretKeySpec llIllllllIlIlIl = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(llIllllllIlIIIl.getBytes(StandardCharsets.UTF_8)), "Blowfish");
                final Cipher llIllllllIlIlII = Cipher.getInstance("Blowfish");
                llIllllllIlIlII.init(GaoNeng.lIIIlIII[3], llIllllllIlIlIl);
                return new String(llIllllllIlIlII.doFinal(Base64.getDecoder().decode(llIllllllIlIIlI.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            }
            catch (Exception llIllllllIlIIll) {
                llIllllllIlIIll.printStackTrace();
                return null;
            }
        }
        
        public String getPhone() {
            return invokedynamic(18:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        private static void lIIIlIIll() {
            (llIlIIl = new String[GaoNeng.lIIIlIII[21]])[GaoNeng.lIIIlIII[0]] = lIIIlIIII(GaoNeng.llIlIlI[GaoNeng.lIIIlIII[0]], GaoNeng.llIlIlI[GaoNeng.lIIIlIII[1]]);
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[1]] = lIIIlIIII(GaoNeng.llIlIlI[GaoNeng.lIIIlIII[3]], GaoNeng.llIlIlI[GaoNeng.lIIIlIII[2]]);
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[3]] = lIIIlIIIl(GaoNeng.llIlIlI[GaoNeng.lIIIlIII[4]], GaoNeng.llIlIlI[GaoNeng.lIIIlIII[5]]);
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[2]] = lIIIlIIII(GaoNeng.llIlIlI[GaoNeng.lIIIlIII[10]], GaoNeng.llIlIlI[GaoNeng.lIIIlIII[13]]);
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[4]] = lIIIlIIIl(GaoNeng.llIlIlI[GaoNeng.lIIIlIII[14]], GaoNeng.llIlIlI[GaoNeng.lIIIlIII[8]]);
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[5]] = lIIIlIIlI("9sqxzAgHOsxGXsvvP92JheYNgNvXUw9PxZ6G0zGmfEdPLV9LxbxFMOmVhA3T+F2l5ziPSfz3IgrwbKTw4CaH/tTsb5g5dHMp", "KvFga");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[10]] = lIIIlIIlI("aVEZvz8xgZF4jbPiawqF9FbSq5l/BCwPD7HDSMd0p8JbVzXIkmgwm9wOXmE4kCH8ogtURIx+zeJ6i/hpUJbHjYIWJFoMFVuH", "UNUPT");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[13]] = lIIIlIIIl("rfGdN4Br2GNqS5nA2BNmMDXtGtLvybRv6ZKAZ5GYyi/hM/1BZhVY4UqmKGe48+IH278spm6ODuNIL4Vh6qpmhc+6SJkugLgE", "Yscdm");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[14]] = lIIIlIIIl("kCEZ1dmCtLz3dVnsr3fl+Cf5nVRDPtSe+0s5bHI+rg6VbgCVbJs1dcmuvSChh0cSb4+BqJOyFq6BpZEL2SyWIg+YVURnB/Aw", "BOrrc");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[8]] = lIIIlIIII("PBY/aQ4hAyojOmo8PDQ3IQJrCiIqDiIiMTdBAiYsCgorIG0DDioJJioICCYtJQggNWcDDioJJioIfzYyfl9/Z2NkT2U=", "DoEGC");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[7]] = lIIIlIIIl("nQ7QY+u8ZPtkV7+LW/isgpvBnipqEXjmmAEvVvLCHc0UNXm3+isrOLzhauvGGCKcKt4k5aQcpcMK46J+2ZzbbB28s9FqFVen92nwXFQFLMQ=", "HNiAd");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[16]] = lIIIlIIlI("pSH3c0Gjd0pzasFjL692Ova4FgJXJv6OZ8as9t6V9C2szCXivVGMwvEyJgMnf34pHAzv5XQBPYk146VaGFWmCKflnQhw5mPx", "aznFO");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[12]] = lIIIlIIIl("14rrWHhI+N7r0iNcBoukEVLonhCed9YnFp3v5kNpRIR216nlMYIAuTjGUhWODcovo1SCECEKTaC63m9ZKN9MsNGZ5cXyxQn9", "diopa");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[19]] = lIIIlIIIl("0TcuK1hfwDF1KvM+VpUwVx5cyVOsDCX/aTnVVGFoUl8edrGYsdPeKrL3/R0ZreEg9UpX5BaE/387o63crG06RZ9j+jcQryTh", "NkaoQ");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[18]] = lIIIlIIIl("RFq4cLOv8WMSUk6rQRtLg4qgnoxkFkGbaIvCFNkVArJrn31a47LCVGeVPBwT2E+MJuQRVjKsRmTIypeDDz4ofsfOcit4fpAyRG2YuHpag6M=", "kzSsj");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[9]] = lIIIlIIII("KikUbQM3PAEnN3wDFzA6Nz1ADi88MQkmPCF+KSIhHDUAJGAVMQENKzw3IyIgMzcLMWoVMQENKzw3VC0vPzVUc3RycE5jbg==", "RPnCN");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[20]] = lIIIlIIII("LQk3bCowHCImHnsjNDETMB1jDwY7ESonFSZeCiMIGxUjJUkSESIMAjsXACMJNBcoMEMSESIMAjsXdzAGOxt3cl11UG1iRw==", "UpMBg");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[17]] = lIIIlIIIl("Fmdmy7fmQXqBRSNFaMJgT2Ce+q3zk0tXKFb3baMpK40dWpj7Vdob9taxwKRvPWkQ7j+a6MiEuswQZxEU4uYC8X1sJInkujX8vkk1nm+f5hM=", "jpxkL");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[11]] = lIIIlIIIl("NDlHiJUCi0MMt7m5HwTYNk7PnB2IZNqly9zIzOkTXTi7B8qFJMxLOrsDGU2qrNvYg5HMaogHFR2VrkRMWMRcm+7mmvyV1OOE", "SWdOF");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[15]] = lIIIlIIIl("5BPLuP3FAG9Zw4Ea39eic3l65yKRSSXVLdueTpjSow8uuxKGsBKqO4v/mzA+uK43Rohqj5qETwUrRGOOi5NR//m5X8yBhUSK", "IqStw");
            GaoNeng.llIlIIl[GaoNeng.lIIIlIII[6]] = lIIIlIIlI("9ApVRFnn/G3vEthMemEzIXa3WHYdntNpKgPsmIlLXNMVWWJHCQ8KrRGvcONhKZojx0glsd018+9Kd/AwS5t3G94+CO5oS+P4168QZ3trb04=", "XBuhe");
            GaoNeng.llIlIlI = null;
        }
        
        public String getBilibili() {
            return invokedynamic(16:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        private static boolean lIlIlIlIl(final int llIlllllIIlllIl, final int llIlllllIIlllII) {
            return llIlllllIIlllIl <= llIlllllIIlllII;
        }
        
        public GaoNeng(final String lllIIIIIIlIlIIl, final String lllIIIIIIIlllIl, final String lllIIIIIIIlllII, final String lllIIIIIIlIIllI, final String lllIIIIIIIllIlI, final String lllIIIIIIIllIIl, final String lllIIIIIIIllIII, final String lllIIIIIIlIIIlI, final String lllIIIIIIlIIIIl, final boolean lllIIIIIIlIIIII) {
        }
        // invokedynamic(0:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIlIlIIl)
        // invokedynamic(1:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIIlllIl)
        // invokedynamic(2:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIIlllII)
        // invokedynamic(3:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIlIIllI)
        // invokedynamic(4:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIIllIlI)
        // invokedynamic(5:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIIllIIl)
        // invokedynamic(6:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIIllIII)
        // invokedynamic(7:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIlIIIlI)
        // invokedynamic(8:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Ljava/lang/String;)V, this, lllIIIIIIlIIIIl)
        // invokedynamic(9:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;Z)V, this, lllIIIIIIlIIIII)
        
        private static void lIIIlIlII() {
            final int llIllllllIllIlI = (int)new Exception().getStackTrace()[GaoNeng.lIIIlIII[0]].getFileName();
            GaoNeng.llIlIlI = ((String)llIllllllIllIlI).substring(((String)llIllllllIllIlI).indexOf("\u00e4") + GaoNeng.lIIIlIII[1], ((String)llIllllllIllIlI).lastIndexOf("\u00fc")).split("\u00f6");
        }
        
        static {
            lIlIlIlII();
            lIIIlIlII();
            lIIIlIIll();
            lIIIIllll();
        }
        
        public String getQQ() {
            return invokedynamic(17:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        public String getTime() {
            return invokedynamic(15:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        private static boolean lIlIlIlll(final int llIlllllIlIIIIl, final int llIlllllIlIIIII) {
            return llIlllllIlIIIIl < llIlllllIlIIIII;
        }
        
        private static boolean lIlIlIllI(final int llIlllllIlIIlIl, final int llIlllllIlIIlII) {
            return llIlllllIlIIlIl == llIlllllIlIIlII;
        }
        
        public String getRank() {
            return invokedynamic(14:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        private static void lIIIIllll() {
            (GaoNeng.llIIlll = new String[GaoNeng.lIIIlIII[6]])[GaoNeng.lIIIlIII[7]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[1]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[8]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[3]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[9]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[2]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[10]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[4]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[11]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[5]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[1]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[10]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[12]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[13]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[5]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[14]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[13]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[8]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[15]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[7]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[16]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[16]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[17]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[12]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[18]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[19]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[3]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[18]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[0]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[9]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[4]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[20]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[20]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[17]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[14]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[11]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[19]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[15]];
            GaoNeng.llIIlll[GaoNeng.lIIIlIII[2]] = GaoNeng.llIlIIl[GaoNeng.lIIIlIII[6]];
            (GaoNeng.llIlIII = new Class[GaoNeng.lIIIlIII[3]])[GaoNeng.lIIIlIII[1]] = Boolean.TYPE;
            GaoNeng.llIlIII[GaoNeng.lIIIlIII[0]] = String.class;
        }
        
        public String getUuid() {
            return invokedynamic(11:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        private static String lIIIlIIIl(final String llIlllllIlIllIl, final String llIlllllIlIlIlI) {
            try {
                final SecretKeySpec llIlllllIllIIII = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(llIlllllIlIlIlI.getBytes(StandardCharsets.UTF_8)), GaoNeng.lIIIlIII[14]), "DES");
                final Cipher llIlllllIlIllll = Cipher.getInstance("DES");
                llIlllllIlIllll.init(GaoNeng.lIIIlIII[3], llIlllllIllIIII);
                return new String(llIlllllIlIllll.doFinal(Base64.getDecoder().decode(llIlllllIlIllIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            }
            catch (Exception llIlllllIlIlllI) {
                llIlllllIlIlllI.printStackTrace();
                return null;
            }
        }
        
        private static void lIlIlIlII() {
            (lIIIlIII = new int[22])[0] = ((0xF5 ^ 0xAE) & ~(0x59 ^ 0x2));
            GaoNeng.lIIIlIII[1] = " ".length();
            GaoNeng.lIIIlIII[2] = "   ".length();
            GaoNeng.lIIIlIII[3] = " ".length() << " ".length();
            GaoNeng.lIIIlIII[4] = " ".length() << (" ".length() << " ".length());
            GaoNeng.lIIIlIII[5] = ((0x16 ^ 0xD) << (" ".length() << " ".length()) ^ (0x4D ^ 0x24));
            GaoNeng.lIIIlIII[6] = ((0x54 ^ 0x5B) << (" ".length() << " ".length()) ^ (0x63 ^ 0x5A)) << (" ".length() << " ".length());
            GaoNeng.lIIIlIII[7] = (0x6D ^ 0x68) << " ".length();
            GaoNeng.lIIIlIII[8] = (0x28 ^ 0x21);
            GaoNeng.lIIIlIII[9] = (0x2 ^ 0xD);
            GaoNeng.lIIIlIII[10] = "   ".length() << " ".length();
            GaoNeng.lIIIlIII[11] = (0x2 ^ 0xB) << " ".length();
            GaoNeng.lIIIlIII[12] = "   ".length() << (" ".length() << " ".length());
            GaoNeng.lIIIlIII[13] = ((0xE ^ 0x1) << " ".length() ^ (0xA4 ^ 0xBD));
            GaoNeng.lIIIlIII[14] = " ".length() << "   ".length();
            GaoNeng.lIIIlIII[15] = (0x28 ^ 0x3B);
            GaoNeng.lIIIlIII[16] = (0x2B ^ 0x20);
            GaoNeng.lIIIlIII[17] = (0x87 ^ 0x96);
            GaoNeng.lIIIlIII[18] = (" ".length() << (" ".length() << " ".length()) ^ "   ".length()) << " ".length();
            GaoNeng.lIIIlIII[19] = (0x84 ^ 0x89);
            GaoNeng.lIIIlIII[20] = " ".length() << (" ".length() << (" ".length() << " ".length()));
            GaoNeng.lIIIlIII[21] = (0xBB ^ 0xAE);
        }
        
        public String getReason() {
            return invokedynamic(12:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        private static String lIIIlIIII(String llIlllllIllllIl, final String llIllllllIIIIIl) {
            llIlllllIllllIl = (boolean)new String(Base64.getDecoder().decode(((String)llIlllllIllllIl).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            final StringBuilder llIllllllIIIIII = new StringBuilder();
            final char[] llIlllllIllllll = llIllllllIIIIIl.toCharArray();
            int llIlllllIlllllI = GaoNeng.lIIIlIII[0];
            final float llIlllllIlllIII = (Object)((String)llIlllllIllllIl).toCharArray();
            final String llIlllllIllIlll = (String)llIlllllIlllIII.length;
            double llIlllllIllIllI = GaoNeng.lIIIlIII[0];
            while (lIlIlIlll((int)llIlllllIllIllI, (int)llIlllllIllIlll)) {
                final char llIllllllIIIIll = llIlllllIlllIII[llIlllllIllIllI];
                llIllllllIIIIII.append((char)(llIllllllIIIIll ^ llIlllllIllllll[llIlllllIlllllI % llIlllllIllllll.length]));
                "".length();
                ++llIlllllIlllllI;
                ++llIlllllIllIllI;
                "".length();
                if ("   ".length() <= 0) {
                    return null;
                }
            }
            return String.valueOf(llIllllllIIIIII);
        }
        
        private static CallSite lIIIIlllI(final MethodHandles.Lookup llIlllllllIIllI, final String llIlllllllIIlIl, final MethodType llIlllllllIIlII) throws NoSuchMethodException, IllegalAccessException {
            try {
                final String[] llIlllllllIllII = GaoNeng.llIIlll[Integer.parseInt(llIlllllllIIlIl)].split(GaoNeng.llIlIIl[GaoNeng.lIIIlIII[0]]);
                final Class<?> llIlllllllIlIll = Class.forName(llIlllllllIllII[GaoNeng.lIIIlIII[0]]);
                final String llIlllllllIlIlI = llIlllllllIllII[GaoNeng.lIIIlIII[1]];
                MethodHandle llIlllllllIlIIl = null;
                final int llIlllllllIlIII = llIlllllllIllII[GaoNeng.lIIIlIII[2]].length();
                if (lIlIlIlIl(llIlllllllIlIII, GaoNeng.lIIIlIII[3])) {
                    final MethodType llIlllllllIlllI = MethodType.fromMethodDescriptorString(llIlllllllIllII[GaoNeng.lIIIlIII[3]], GaoNeng.class.getClassLoader());
                    if (lIlIlIllI(llIlllllllIlIII, GaoNeng.lIIIlIII[3])) {
                        llIlllllllIlIIl = llIlllllllIIllI.findVirtual(llIlllllllIlIll, llIlllllllIlIlI, llIlllllllIlllI);
                        "".length();
                        if (" ".length() << (" ".length() << " ".length()) <= 0) {
                            return null;
                        }
                    }
                    else {
                        llIlllllllIlIIl = llIlllllllIIllI.findStatic(llIlllllllIlIll, llIlllllllIlIlI, llIlllllllIlllI);
                    }
                    "".length();
                    if (((109 + 61 - 80 + 73 ^ (0xB4 ^ 0x95) << (" ".length() << " ".length())) & (0x2A ^ 0x61 ^ (0x78 ^ 0x63) << (" ".length() << " ".length()) ^ -" ".length())) != ((" ".length() << ("   ".length() << " ".length()) ^ " ".length()) & ((0x60 ^ 0x7F) << " ".length() ^ 66 + 57 - 67 + 71 ^ -" ".length()))) {
                        return null;
                    }
                }
                else {
                    final Class llIlllllllIllIl = GaoNeng.llIlIII[Integer.parseInt(llIlllllllIllII[GaoNeng.lIIIlIII[3]])];
                    if (lIlIlIllI(llIlllllllIlIII, GaoNeng.lIIIlIII[2])) {
                        llIlllllllIlIIl = llIlllllllIIllI.findGetter(llIlllllllIlIll, llIlllllllIlIlI, llIlllllllIllIl);
                        "".length();
                        if ("   ".length() >= " ".length() << (" ".length() << " ".length())) {
                            return null;
                        }
                    }
                    else if (lIlIlIllI(llIlllllllIlIII, GaoNeng.lIIIlIII[4])) {
                        llIlllllllIlIIl = llIlllllllIIllI.findStaticGetter(llIlllllllIlIll, llIlllllllIlIlI, llIlllllllIllIl);
                        "".length();
                        if (((0xAF ^ 0x8E) << " ".length() & ~((0xBC ^ 0x9D) << " ".length())) < -" ".length()) {
                            return null;
                        }
                    }
                    else if (lIlIlIllI(llIlllllllIlIII, GaoNeng.lIIIlIII[5])) {
                        llIlllllllIlIIl = llIlllllllIIllI.findSetter(llIlllllllIlIll, llIlllllllIlIlI, llIlllllllIllIl);
                        "".length();
                        if (((0xBB ^ 0xB2) & ~(0x13 ^ 0x1A)) > 0) {
                            return null;
                        }
                    }
                    else {
                        llIlllllllIlIIl = llIlllllllIIllI.findStaticSetter(llIlllllllIlIll, llIlllllllIlIlI, llIlllllllIllIl);
                    }
                }
                return new ConstantCallSite(llIlllllllIlIIl);
            }
            catch (Exception llIlllllllIIlll) {
                llIlllllllIIlll.printStackTrace();
                return null;
            }
        }
        
        public String getChecker() {
            return invokedynamic(13:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Ljava/lang/String;, this);
        }
        
        public boolean isRealBlackList() {
            return invokedynamic(19:(Lxyz/Melody/System/Managers/GaoNeng/GaoNengManager$GaoNeng;)Z, this);
        }
    }
}
