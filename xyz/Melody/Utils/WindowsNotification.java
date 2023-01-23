//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import xyz.Melody.*;
import java.awt.*;

public class WindowsNotification
{
    private static Image image;
    private static TrayIcon trayIcon;
    private static SystemTray tray;
    
    public static void init() {
        try {
            WindowsNotification.trayIcon.setImageAutoSize(true);
            WindowsNotification.trayIcon.setToolTip("MelodySky - Windows Notification");
            WindowsNotification.tray.add(WindowsNotification.trayIcon);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public static void show(final String title, final String message) {
        if (SystemTray.isSupported()) {
            displayTray(title, message);
        }
        else {
            Client.instance.logger.error("System tray not supported!");
        }
    }
    
    private static void displayTray(final String title, final String message) {
        WindowsNotification.trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
    }
    
    public static void stop() {
        if (WindowsNotification.trayIcon != null && WindowsNotification.tray != null) {
            WindowsNotification.tray.remove(WindowsNotification.trayIcon);
        }
    }
    
    static {
        WindowsNotification.image = Toolkit.getDefaultToolkit().createImage("icon.png");
        WindowsNotification.trayIcon = new TrayIcon(WindowsNotification.image);
        WindowsNotification.tray = SystemTray.getSystemTray();
    }
}
