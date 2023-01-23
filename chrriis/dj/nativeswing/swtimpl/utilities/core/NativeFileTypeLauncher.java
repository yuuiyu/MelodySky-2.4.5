//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.utilities.core;

import chrriis.dj.nativeswing.swtimpl.utilities.internal.*;
import javax.swing.*;
import chrriis.dj.nativeswing.swtimpl.*;

class NativeFileTypeLauncher implements INativeFileTypeLauncher
{
    private NativeFileTypeLauncherStatic fileTypeLauncherStatic;
    private int id;
    private String[] registeredExtensions;
    private String name;
    private ImageIcon icon;
    private boolean isIconInitialized;
    private Integer hashCode;
    
    NativeFileTypeLauncher(final NativeFileTypeLauncherStatic fileTypeLauncherStatic, final int id) {
        this.fileTypeLauncherStatic = fileTypeLauncherStatic;
        this.id = id;
    }
    
    @Override
    public String[] getRegisteredExtensions() {
        if (this.registeredExtensions == null) {
            this.fileTypeLauncherStatic.initializeExtensions();
            this.registeredExtensions = (String[])new CMN_getRegisteredExtensions(null).syncExec(true, new Object[] { this.id });
        }
        return this.registeredExtensions;
    }
    
    @Override
    public String getName() {
        if (this.name == null) {
            this.name = (String)new CMN_getName(null).syncExec(true, new Object[] { this.id });
        }
        return this.name;
    }
    
    @Override
    public ImageIcon getIcon() {
        if (!this.isIconInitialized) {
            this.isIconInitialized = true;
            this.icon = (ImageIcon)new CMN_getIcon(null).syncExec(true, new Object[] { this.id });
        }
        return (this.icon == null) ? this.fileTypeLauncherStatic.getDefaultIcon() : this.icon;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o;
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == null) {
            this.hashCode = (Integer)new CMN_hashCode(null).syncExec(true, new Object[] { this.id });
        }
        return this.hashCode;
    }
    
    @Override
    public void launch(final String filePath) {
        new CMN_launch(null).asyncExec(true, new Object[] { this.id, filePath });
    }
    
    private static class CMN_getRegisteredExtensions extends CommandMessage
    {
        public Object run(final Object[] args) {
            return NativeFileTypeLauncherStatic.getFileTypeLauncherInfo((Integer)args[0]).getRegisteredExtensions();
        }
    }
    
    private static class CMN_getName extends CommandMessage
    {
        public Object run(final Object[] args) {
            return NativeFileTypeLauncherStatic.getFileTypeLauncherInfo((Integer)args[0]).getProgram().getName();
        }
    }
    
    private static class CMN_getIcon extends CommandMessage
    {
        public Object run(final Object[] args) {
            return NativeFileTypeLauncherStatic.getFileTypeLauncherInfo((Integer)args[0]).getIcon();
        }
    }
    
    private static class CMN_hashCode extends CommandMessage
    {
        public Object run(final Object[] args) {
            return NativeFileTypeLauncherStatic.getFileTypeLauncherInfo((Integer)args[0]).getProgram().hashCode();
        }
    }
    
    private static class CMN_launch extends CommandMessage
    {
        public Object run(final Object[] args) {
            NativeFileTypeLauncherStatic.getFileTypeLauncherInfo((Integer)args[0]).getProgram().execute((String)args[1]);
            return null;
        }
    }
}
