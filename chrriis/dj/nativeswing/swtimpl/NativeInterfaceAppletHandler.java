//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import java.applet.*;
import chrriis.dj.nativeswing.*;
import java.util.concurrent.atomic.*;
import chrriis.dj.nativeswing.common.*;
import java.util.*;

public class NativeInterfaceAppletHandler
{
    private static final Object INITIALIZATION_LOCK;
    private static boolean isInterfaceToOpen;
    private static Set<Applet> activeAppletSet;
    
    private NativeInterfaceAppletHandler() {
    }
    
    public static void activateAppletMode() {
        NSSystemProperty.DEPLOYMENT_TYPE.set("applet");
        if (NativeInterface.isInProcess()) {
            final AtomicBoolean isInitialized = new AtomicBoolean(false);
            synchronized (isInitialized) {
                final Thread eventPumpThread = (Thread)new lIIII("NativeSwing event pump thread", isInitialized);
                eventPumpThread.setDaemon(true);
                eventPumpThread.start();
                while (!isInitialized.get()) {
                    try {
                        isInitialized.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
        else {
            NativeInterface.initialize();
        }
    }
    
    public static void init(final Applet applet) {
        checkAppletMode();
    }
    
    public static void start(final Applet applet) {
        checkAppletMode();
        synchronized (NativeInterfaceAppletHandler.INITIALIZATION_LOCK) {
            NativeInterfaceAppletHandler.activeAppletSet.add(applet);
            if (NativeInterfaceAppletHandler.isInterfaceToOpen) {
                NativeInterface.open();
            }
        }
    }
    
    public static void stop(final Applet applet) {
        checkAppletMode();
        synchronized (NativeInterfaceAppletHandler.INITIALIZATION_LOCK) {
            NativeInterfaceAppletHandler.activeAppletSet.remove(applet);
            if (NativeInterfaceAppletHandler.activeAppletSet.isEmpty()) {
                NativeInterfaceAppletHandler.isInterfaceToOpen = NativeInterface.isOpen();
                stopActivity();
            }
        }
    }
    
    public static void destroy(final Applet applet) {
        checkAppletMode();
        synchronized (NativeInterfaceAppletHandler.INITIALIZATION_LOCK) {
            NativeInterfaceAppletHandler.activeAppletSet.remove(applet);
            if (NativeInterfaceAppletHandler.activeAppletSet.isEmpty()) {
                NativeInterfaceAppletHandler.isInterfaceToOpen = false;
                stopActivity();
            }
        }
    }
    
    private static void stopActivity() {
        NativeInterface.close();
        WebServer.stopDefaultWebServer();
    }
    
    private static void checkAppletMode() {
        if (!"applet".equals(NSSystemProperty.DEPLOYMENT_TYPE.get())) {
            throw new IllegalStateException(NativeInterfaceAppletHandler.class.getName() + ".activateAppletMode() was not called! This code has to be placed first in the applet subclass in a static initializer.");
        }
    }
    
    static {
        INITIALIZATION_LOCK = new Object();
        NativeInterfaceAppletHandler.activeAppletSet = new HashSet<Applet>();
    }
}
