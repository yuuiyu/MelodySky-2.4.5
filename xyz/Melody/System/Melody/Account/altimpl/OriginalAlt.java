//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.altimpl;

import xyz.Melody.System.Melody.Account.*;

public class OriginalAlt extends Alt
{
    private final String accessToken;
    private final String uuid;
    private final String type;
    
    public OriginalAlt(final String userName, final String accessToken, final String uuid, final String type) {
        super(userName, AccountEnum.ORIGINAL);
        this.accessToken = accessToken;
        this.uuid = uuid;
        this.type = type;
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
