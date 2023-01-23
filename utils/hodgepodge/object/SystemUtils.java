//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object;

public class SystemUtils
{
    public static SystemEnum getSystem() {
        final String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            return SystemEnum.WINDOWS;
        }
        if (osName.contains("linux")) {
            return SystemEnum.LINUX;
        }
        if (osName.contains("mac")) {
            return SystemEnum.MACOS;
        }
        return SystemEnum.UNKNOWN;
    }
    
    public enum SystemEnum
    {
        WINDOWS, 
        LINUX, 
        MACOS, 
        UNKNOWN;
    }
}
