//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt;

public class SWTError extends Error
{
    public int code;
    public Throwable throwable;
    static final long serialVersionUID = 3833467327105808433L;
    
    public SWTError() {
        this(1);
    }
    
    public SWTError(final String message) {
        this(1, message);
    }
    
    public SWTError(final int code) {
        this(code, SWT.findErrorText(code));
    }
    
    public SWTError(final int code, final String message) {
        super(message);
        this.code = code;
    }
    
    @Override
    public Throwable getCause() {
        return this.throwable;
    }
    
    @Override
    public String getMessage() {
        if (this.throwable == null) {
            return super.getMessage();
        }
        return super.getMessage() + " (" + this.throwable.toString();
    }
    
    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
