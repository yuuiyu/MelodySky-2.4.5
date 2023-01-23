//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import java.io.*;
import chrriis.dj.nativeswing.swtimpl.internal.*;

public abstract class NativeInterface
{
    private static ISWTNativeInterface swtNativeInterface;
    
    protected static NativeInterface getInstance() {
        return (NativeInterface)NativeInterface.swtNativeInterface;
    }
    
    public static boolean isOpen() {
        return NativeInterface.swtNativeInterface.isOpen_();
    }
    
    public static void close() {
        NativeInterface.swtNativeInterface.close_();
    }
    
    public static NativeInterfaceConfiguration getConfiguration() {
        return NativeInterface.swtNativeInterface.getConfiguration_();
    }
    
    public static boolean isInitialized() {
        return NativeInterface.swtNativeInterface.isInitialized_();
    }
    
    public static boolean isInProcess() {
        return NativeInterface.swtNativeInterface.isInProcess_();
    }
    
    static boolean isOutProcessNativeSide() {
        return NativeInterface.swtNativeInterface.isOutProcessNativeSide_();
    }
    
    public static void initialize() {
        NativeInterface.swtNativeInterface.initialize_();
    }
    
    public static void printStackTraces() {
        NativeInterface.swtNativeInterface.printStackTraces_();
    }
    
    public static void printStackTraces(final PrintStream printStream) {
        NativeInterface.swtNativeInterface.printStackTraces_(printStream);
    }
    
    public static void printStackTraces(final PrintWriter printWriter) {
        NativeInterface.swtNativeInterface.printStackTraces_(printWriter);
    }
    
    public static void open() {
        NativeInterface.swtNativeInterface.open_();
    }
    
    static Object syncSend(final boolean isTargetNativeSide, final Message message) {
        return NativeInterface.swtNativeInterface.syncSend_(isTargetNativeSide, message);
    }
    
    static void asyncSend(final boolean isTargetNativeSide, final Message message) {
        NativeInterface.swtNativeInterface.asyncSend_(isTargetNativeSide, message);
    }
    
    public static boolean isUIThread(final boolean isNativeSide) {
        return NativeInterface.swtNativeInterface.isUIThread_(isNativeSide);
    }
    
    public static void runEventPump() {
        NativeInterface.swtNativeInterface.runEventPump_();
    }
    
    public static boolean isEventPumpRunning() {
        return NativeInterface.swtNativeInterface.isEventPumpRunning_();
    }
    
    public static void addNativeInterfaceListener(final NativeInterfaceListener listener) {
        NativeInterface.swtNativeInterface.addNativeInterfaceListener_(listener);
    }
    
    public static void removeNativeInterfaceListener(final NativeInterfaceListener listener) {
        NativeInterface.swtNativeInterface.removeNativeInterfaceListener_(listener);
    }
    
    public static NativeInterfaceListener[] getNativeInterfaceListeners() {
        return NativeInterface.swtNativeInterface.getNativeInterfaceListeners_();
    }
    
    public static void setApplicationMessageHandler(final ApplicationMessageHandler applicationMessageHandler) {
        NativeInterface.swtNativeInterface.setApplicationMessageHandler_(applicationMessageHandler);
    }
    
    protected static NativeInterfaceConfiguration createConfiguration() {
        return new NativeInterfaceConfiguration();
    }
    
    protected static Object runMessageCommand(final LocalMessage commandMessage) {
        return commandMessage.runCommand();
    }
    
    protected static Object runMessageCommand(final CommandMessage commandMessage) throws Exception {
        return commandMessage.runCommand();
    }
    
    protected static boolean isMessageSyncExec(final Message message) {
        return message.isSyncExec();
    }
    
    protected static void setMessageSyncExec(final Message message, final boolean isSyncExec) {
        message.setSyncExec(isSyncExec);
    }
    
    protected static String[] getPeerVMParams(final NativeInterfaceConfiguration nativeInterfaceConfiguration) {
        return nativeInterfaceConfiguration.getPeerVMParams();
    }
    
    protected static Class<?>[] getNativeClassPathReferenceClasses(final NativeInterfaceConfiguration nativeInterfaceConfiguration) {
        return nativeInterfaceConfiguration.getNativeClassPathReferenceClasses();
    }
    
    protected static String[] getNativeClassPathReferenceResources(final NativeInterfaceConfiguration nativeInterfaceConfiguration) {
        return nativeInterfaceConfiguration.getNativeClassPathReferenceResources();
    }
    
    protected static int getMessageID(final Message message) {
        return message.getID();
    }
    
    protected static boolean isMessageValid(final Message message) {
        return message.isValid();
    }
    
    protected static void setMessageArgs(final CommandMessage message, final Object... args) {
        message.setArgs(args);
    }
    
    protected static void computeMessageID(final Message message, final boolean isTargetNativeSide) {
        message.computeID(isTargetNativeSide);
    }
    
    protected static void setMessageUI(final Message message, final boolean isUI) {
        message.setUI(isUI);
    }
    
    protected static boolean isMessageUI(final Message message) {
        return message.isUI();
    }
    
    public static void main(final String[] args) throws Exception {
        ((ISWTNativeInterface)getInstance()).main_(args);
    }
    
    static {
        NativeInterface.swtNativeInterface = (ISWTNativeInterface)NativeCoreObjectFactory.create((Class)ISWTNativeInterface.class, "chrriis.dj.nativeswing.swtimpl.core.SWTNativeInterface", new Class[0], new Object[0]);
    }
}
