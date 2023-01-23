//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import java.io.*;

public class Message implements Serializable
{
    private static int nextID;
    private int id;
    private boolean isSyncExec;
    private boolean isUI;
    
    public Message() {
        this.isUI = true;
    }
    
    void setUI(final boolean isUI) {
        this.isUI = isUI;
    }
    
    boolean isUI() {
        return this.isUI;
    }
    
    int getID() {
        return this.id;
    }
    
    void setSyncExec(final boolean isSyncExec) {
        this.isSyncExec = isSyncExec;
    }
    
    boolean isSyncExec() {
        return this.isSyncExec;
    }
    
    public void asyncSend(final boolean isTargetNativeSide) {
        NativeInterface.asyncSend(isTargetNativeSide, this);
    }
    
    public Object syncSend(final boolean isTargetNativeSide) {
        return NativeInterface.syncSend(isTargetNativeSide, this);
    }
    
    void computeID(final boolean isTargetNativeSide) {
        if (this.id != 0) {
            return;
        }
        if (isTargetNativeSide) {
            this.id = Message.nextID++;
        }
        else {
            this.id = -(Message.nextID++);
        }
    }
    
    protected boolean isValid() {
        return true;
    }
    
    @Override
    public String toString() {
        String name = this.getClass().getName();
        if (name.startsWith("chrriis.dj.nativeswing.swtimpl.")) {
            name = name.substring("chrriis.dj.nativeswing.swtimpl.".length());
        }
        return name;
    }
    
    static {
        Message.nextID = 1;
    }
}
