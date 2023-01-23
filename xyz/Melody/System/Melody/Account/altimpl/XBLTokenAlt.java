//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.altimpl;

import xyz.Melody.System.Melody.Account.*;

public class XBLTokenAlt extends Alt
{
    private String xblToken;
    private String xstsToken_f;
    private String xstsToken_s;
    private String accessToken;
    private String uuid;
    private String type;
    
    public XBLTokenAlt(final String userName, final String xblToken, final String xsts1, final String xsts2, final String accessToken, final String uuid, final String type) {
        super(userName, AccountEnum.XBLTOKEN);
        this.xblToken = xblToken;
        this.xstsToken_f = xsts1;
        this.xstsToken_s = xsts2;
        this.accessToken = accessToken;
        this.uuid = uuid;
        this.type = type;
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
    
    public String getAccessToken() {
        return this.accessToken;
    }
    
    public String getUUID() {
        return this.uuid;
    }
    
    public String getType() {
        return this.type;
    }
}
