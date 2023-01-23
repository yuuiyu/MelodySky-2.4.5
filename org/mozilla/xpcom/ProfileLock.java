//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

public class ProfileLock
{
    private long lock;
    
    public ProfileLock(final long lock) {
        this.lock = 0L;
        this.lock = lock;
    }
    
    public void release() {
        this.releaseNative(this.lock);
        this.lock = 0L;
    }
    
    private native void releaseNative(final long p0);
    
    public boolean isValid() {
        return this.lock != 0L;
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.release();
        super.finalize();
    }
}
