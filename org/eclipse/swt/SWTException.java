//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt;

public class SWTException extends RuntimeException
{
    public int code;
    public Throwable throwable;
    static final long serialVersionUID = 3257282552304842547L;
    
    public SWTException() {
        this(1);
    }
    
    public SWTException(final String message) {
        this(1, message);
    }
    
    public SWTException(final int code) {
        this(code, SWT.findErrorText(code));
    }
    
    public SWTException(final int code, final String message) {
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
