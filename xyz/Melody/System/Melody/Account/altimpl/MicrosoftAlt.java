//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.altimpl;

import xyz.Melody.System.Melody.Account.*;

public class MicrosoftAlt extends Alt
{
    private String refreshToken;
    private String msToken;
    private String xblToken;
    private String xstsToken_f;
    private String xstsToken_s;
    private String accessToken;
    private String uuid;
    private String type;
    
    public MicrosoftAlt(final String userName, final String refreshToken, final String msToken, final String xblToken, final String xsts1, final String uhs, final String accessToken, final String uuid) {
        super(userName, AccountEnum.MICROSOFT);
        this.refreshToken = refreshToken;
        this.msToken = msToken;
        this.xblToken = xblToken;
        this.xstsToken_f = xsts1;
        this.xstsToken_s = uhs;
        this.accessToken = accessToken;
        this.uuid = uuid;
    }
    
    public String getAccessToken() {
        return this.accessToken;
    }
    
    public String getMsToken() {
        return this.msToken;
    }
    
    public String getUuid() {
        return this.uuid;
    }
    
    public String getXblToken() {
        return this.xblToken;
    }
    
    public String getXstsToken_f() {
        return this.xstsToken_f;
    }
    
    public String getXstsToken_s() {
        return this.xstsToken_s;
    }
    
    public String getUUID() {
        return this.uuid;
    }
    
    public String getRefreshToken() {
        return this.refreshToken;
    }
}
