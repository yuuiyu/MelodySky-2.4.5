//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.internal;

import java.io.*;
import chrriis.dj.nativeswing.swtimpl.*;

public interface ISWTNativeInterface
{
    boolean isOpen_();
    
    void close_();
    
    NativeInterfaceConfiguration getConfiguration_();
    
    boolean isInitialized_();
    
    boolean isInProcess_();
    
    void initialize_();
    
    void printStackTraces_();
    
    void printStackTraces_(final PrintStream p0);
    
    void printStackTraces_(final PrintWriter p0);
    
    void open_();
    
    Object syncSend_(final boolean p0, final Message p1);
    
    void asyncSend_(final boolean p0, final Message p1);
    
    boolean isOutProcessNativeSide_();
    
    boolean isUIThread_(final boolean p0);
    
    void runEventPump_();
    
    boolean isEventPumpRunning_();
    
    void addNativeInterfaceListener_(final NativeInterfaceListener p0);
    
    void removeNativeInterfaceListener_(final NativeInterfaceListener p0);
    
    NativeInterfaceListener[] getNativeInterfaceListeners_();
    
    void setApplicationMessageHandler_(final ApplicationMessageHandler p0);
    
    void main_(final String[] p0) throws Exception;
}
