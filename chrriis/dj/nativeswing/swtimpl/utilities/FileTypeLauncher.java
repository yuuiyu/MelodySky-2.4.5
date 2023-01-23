//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.utilities;

import chrriis.dj.nativeswing.swtimpl.utilities.internal.*;
import chrriis.dj.nativeswing.swtimpl.internal.*;
import javax.swing.*;
import java.awt.*;

public class FileTypeLauncher
{
    private static INativeFileTypeLauncherStatic fileTypeLauncherStatic;
    private INativeFileTypeLauncher fileTypeLauncher;
    
    public FileTypeLauncher() {
        this((INativeFileTypeLauncher)NativeCoreObjectFactory.create((Class)INativeFileTypeLauncher.class, "chrriis.dj.nativeswing.swtimpl.utilities.core.NativeFileTypeLauncher", new Class[0], new Object[0]));
    }
    
    FileTypeLauncher(final INativeFileTypeLauncher fileTypeLauncher) {
        this.fileTypeLauncher = fileTypeLauncher;
    }
    
    public static void load() {
        FileTypeLauncher.fileTypeLauncherStatic.load();
    }
    
    public static String[] getAllRegisteredExtensions() {
        return FileTypeLauncher.fileTypeLauncherStatic.getAllRegisteredExtensions();
    }
    
    public String[] getRegisteredExtensions() {
        return this.fileTypeLauncher.getRegisteredExtensions();
    }
    
    public String getName() {
        return this.fileTypeLauncher.getName();
    }
    
    public ImageIcon getIcon() {
        return this.fileTypeLauncher.getIcon();
    }
    
    @Override
    public boolean equals(final Object o) {
        return this.fileTypeLauncher.equals(((FileTypeLauncher)o).fileTypeLauncher);
    }
    
    @Override
    public int hashCode() {
        return this.fileTypeLauncher.hashCode();
    }
    
    public void launch(final String filePath) {
        this.fileTypeLauncher.launch(filePath);
    }
    
    public static FileTypeLauncher getLauncher(final String fileName) {
        final INativeFileTypeLauncher launcher = FileTypeLauncher.fileTypeLauncherStatic.getLauncher(fileName);
        return (launcher == null) ? null : new FileTypeLauncher(launcher);
    }
    
    public static FileTypeLauncher[] getLaunchers() {
        final INativeFileTypeLauncher[] nLaunchers = FileTypeLauncher.fileTypeLauncherStatic.getLaunchers();
        final FileTypeLauncher[] launchers = new FileTypeLauncher[nLaunchers.length];
        for (int i = 0; i < launchers.length; ++i) {
            launchers[i] = new FileTypeLauncher(nLaunchers[i]);
        }
        return launchers;
    }
    
    public static ImageIcon getDefaultIcon() {
        return FileTypeLauncher.fileTypeLauncherStatic.getDefaultIcon();
    }
    
    public static Dimension getIconSize() {
        return FileTypeLauncher.fileTypeLauncherStatic.getIconSize();
    }
    
    static {
        FileTypeLauncher.fileTypeLauncherStatic = (INativeFileTypeLauncherStatic)NativeCoreObjectFactory.create((Class)INativeFileTypeLauncherStatic.class, "chrriis.dj.nativeswing.swtimpl.utilities.core.NativeFileTypeLauncherStatic", new Class[0], new Object[0]);
    }
}
