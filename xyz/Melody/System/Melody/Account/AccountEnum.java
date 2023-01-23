//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account;

public enum AccountEnum
{
    OFFLINE("OFFLINE"), 
    XBLTOKEN("XBOXLIVE"), 
    MICROSOFT("MICROSOFT"), 
    ORIGINAL("ORIGINAL");
    
    private final String writeName;
    
    private AccountEnum(final String name) {
        this.writeName = name;
    }
    
    public String getWriteName() {
        return this.writeName;
    }
    
    public static AccountEnum parse(final String str) {
        for (final AccountEnum value : values()) {
            if (value.writeName.equals(str)) {
                return value;
            }
        }
        return null;
    }
}
