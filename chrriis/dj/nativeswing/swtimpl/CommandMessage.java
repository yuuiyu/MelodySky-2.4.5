//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import chrriis.dj.nativeswing.common.*;

public abstract class CommandMessage extends Message
{
    private Object[] args;
    private static final Object[] EMPTY_ARGS;
    
    void setArgs(Object... args) {
        if (args.length == 0) {
            args = null;
        }
        this.args = args;
    }
    
    public void asyncExec(final boolean isTargetNativeSide, final Object... args) {
        this.setArgs(args);
        this.asyncSend(isTargetNativeSide);
    }
    
    public Object syncExec(final boolean isTargetNativeSide, final Object... args) {
        this.setArgs(args);
        return this.syncSend(isTargetNativeSide);
    }
    
    protected Object runCommand() throws Exception {
        return this.run((this.args == null) ? CommandMessage.EMPTY_ARGS : this.args);
    }
    
    public abstract Object run(final Object[] p0) throws Exception;
    
    @Override
    public String toString() {
        final String s = super.toString();
        if (this.args == null || this.args.length == 0) {
            return s + "()";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s).append('(');
        for (int i = 0; i < this.args.length; ++i) {
            final Object arg = this.args[i];
            if (i > 0) {
                sb.append(", ");
            }
            if (arg != null && arg.getClass().isArray()) {
                sb.append(Utils.arrayDeepToString(arg));
            }
            else {
                sb.append(arg);
            }
        }
        sb.append(')');
        return sb.toString();
    }
    
    static {
        EMPTY_ARGS = new Object[0];
    }
}
