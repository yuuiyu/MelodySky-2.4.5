//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

public abstract class LocalMessage extends CommandMessage
{
    protected Object runCommand() {
        try {
            return super.runCommand();
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
    
    public abstract Object run(final Object[] p0);
}
