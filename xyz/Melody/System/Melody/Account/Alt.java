//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account;

public abstract class Alt
{
    private final String userName;
    private final AccountEnum accountType;
    
    public Alt(final String userName, final AccountEnum accountType) {
        this.userName = userName;
        this.accountType = accountType;
    }
    
    public AccountEnum getAccountType() {
        return this.accountType;
    }
    
    public String getUserName() {
        return this.userName;
    }
}
