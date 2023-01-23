//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.utilities.core;

import org.eclipse.swt.program.*;
import chrriis.dj.nativeswing.swtimpl.utilities.internal.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import chrriis.dj.nativeswing.swtimpl.core.*;
import org.eclipse.swt.graphics.*;
import chrriis.dj.nativeswing.swtimpl.*;
import java.util.*;

class NativeFileTypeLauncherStatic implements INativeFileTypeLauncherStatic
{
    private static Map<Integer, FileTypeLauncherInfo> idToFileTypeLauncherInfoMap;
    private static Map<Program, FileTypeLauncherInfo> programToFileTypeLauncherInfoMap;
    private static boolean isNativeInitialized;
    private static boolean hasInitializedLaunchers;
    private static boolean hasInitializedExtensions;
    private static Map<Integer, NativeFileTypeLauncher> idToFileTypeLauncherMap;
    private static boolean isDefaultIconLoaded;
    private static ImageIcon defaultIcon;
    
    private static boolean isProgramValid(final Program program) {
        final String name = program.getName();
        return name != null && name.length() > 0;
    }
    
    private static void initNative() {
        if (NativeFileTypeLauncherStatic.isNativeInitialized) {
            return;
        }
        NativeFileTypeLauncherStatic.isNativeInitialized = true;
        NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap = new HashMap<Program, FileTypeLauncherInfo>();
        NativeFileTypeLauncherStatic.idToFileTypeLauncherInfoMap = new HashMap<Integer, FileTypeLauncherInfo>();
    }
    
    @Override
    public void load() {
        this.initializeExtensions();
        initializeLaunchers();
    }
    
    private static void initializeLaunchers() {
        if (NativeFileTypeLauncherStatic.hasInitializedLaunchers) {
            return;
        }
        NativeFileTypeLauncherStatic.hasInitializedLaunchers = true;
        new CMN_initializeLaunchers(null).syncExec(true, new Object[0]);
    }
    
    void initializeExtensions() {
        if (NativeFileTypeLauncherStatic.hasInitializedExtensions) {
            return;
        }
        NativeFileTypeLauncherStatic.hasInitializedExtensions = true;
        new CMN_initializeExtensions(null).syncExec(true, new Object[0]);
    }
    
    @Override
    public String[] getAllRegisteredExtensions() {
        this.initializeExtensions();
        return (String[])new CMN_getAllRegisteredExtensions(null).syncExec(true, new Object[0]);
    }
    
    @Override
    public INativeFileTypeLauncher getLauncher(final String fileName) {
        final int index = fileName.lastIndexOf(46);
        if (index == -1) {
            return null;
        }
        final String extension = fileName.substring(index);
        final Integer id = (Integer)new CMN_getLauncherID(null).syncExec(true, new Object[] { extension });
        if (id == null) {
            return null;
        }
        NativeFileTypeLauncher fileTypeLauncher = NativeFileTypeLauncherStatic.idToFileTypeLauncherMap.get(id);
        if (fileTypeLauncher == null) {
            fileTypeLauncher = new NativeFileTypeLauncher(this, (int)id);
            NativeFileTypeLauncherStatic.idToFileTypeLauncherMap.put(id, fileTypeLauncher);
        }
        return (INativeFileTypeLauncher)fileTypeLauncher;
    }
    
    @Override
    public INativeFileTypeLauncher[] getLaunchers() {
        this.load();
        final int[] ids = (int[])new CMN_getLauncherIDs(null).syncExec(true, new Object[0]);
        if (NativeFileTypeLauncherStatic.idToFileTypeLauncherMap == null) {
            NativeFileTypeLauncherStatic.idToFileTypeLauncherMap = new HashMap<Integer, NativeFileTypeLauncher>();
        }
        final NativeFileTypeLauncher[] fileTypeLaunchers = new NativeFileTypeLauncher[ids.length];
        for (int i = 0; i < ids.length; ++i) {
            final int id = ids[i];
            NativeFileTypeLauncher fileTypeLauncher = NativeFileTypeLauncherStatic.idToFileTypeLauncherMap.get(id);
            if (fileTypeLauncher == null) {
                fileTypeLauncher = new NativeFileTypeLauncher(this, id);
                NativeFileTypeLauncherStatic.idToFileTypeLauncherMap.put(id, fileTypeLauncher);
            }
            fileTypeLaunchers[i] = fileTypeLauncher;
        }
        return (INativeFileTypeLauncher[])fileTypeLaunchers;
    }
    
    @Override
    public ImageIcon getDefaultIcon() {
        if (!NativeFileTypeLauncherStatic.isDefaultIconLoaded) {
            NativeFileTypeLauncherStatic.isDefaultIconLoaded = true;
            Icon defaultIcon_;
            try {
                final File tmpFile = File.createTempFile("~djn", "~.qwertyuiop");
                tmpFile.deleteOnExit();
                defaultIcon_ = FileSystemView.getFileSystemView().getSystemIcon(tmpFile);
                tmpFile.delete();
            }
            catch (Exception e) {
                defaultIcon_ = UIManager.getIcon("FileView.fileIcon");
            }
            if (!(defaultIcon_ instanceof ImageIcon)) {
                final int width = defaultIcon_.getIconWidth();
                final int height = defaultIcon_.getIconHeight();
                final BufferedImage image = new BufferedImage(width, height, 2);
                final Graphics gc = image.getGraphics();
                defaultIcon_.paintIcon(null, gc, 0, 0);
                gc.dispose();
                defaultIcon_ = new ImageIcon(image);
            }
            NativeFileTypeLauncherStatic.defaultIcon = (ImageIcon)defaultIcon_;
        }
        return NativeFileTypeLauncherStatic.defaultIcon;
    }
    
    @Override
    public Dimension getIconSize() {
        final ImageIcon defaultIcon = this.getDefaultIcon();
        return (defaultIcon == null) ? new Dimension(16, 16) : new Dimension(defaultIcon.getIconWidth(), defaultIcon.getIconHeight());
    }
    
    static FileTypeLauncherInfo getFileTypeLauncherInfo(final Integer id) {
        return NativeFileTypeLauncherStatic.idToFileTypeLauncherInfoMap.get(id);
    }
    
    static class FileTypeLauncherInfo
    {
        public static int nextID;
        private int id;
        private Program program;
        private List<String> registeredExtensionList;
        private boolean isIconInitialized;
        private ImageIcon icon;
        
        public FileTypeLauncherInfo(final Program program) {
            this.id = FileTypeLauncherInfo.nextID++;
            this.program = program;
            NativeFileTypeLauncherStatic.idToFileTypeLauncherInfoMap.put(this.getID(), this);
        }
        
        private void addExtension(final String extension) {
            if (this.registeredExtensionList == null) {
                this.registeredExtensionList = new ArrayList<String>(1);
            }
            if (!this.registeredExtensionList.contains(extension)) {
                this.registeredExtensionList.add(extension);
            }
        }
        
        public int getID() {
            return this.id;
        }
        
        public String[] getRegisteredExtensions() {
            return (this.registeredExtensionList == null) ? new String[0] : this.registeredExtensionList.toArray(new String[0]);
        }
        
        public Program getProgram() {
            return this.program;
        }
        
        public ImageIcon getIcon() {
            if (!this.isIconInitialized) {
                this.isIconInitialized = true;
                final ImageData imageData = this.program.getImageData();
                this.icon = ((imageData == null) ? null : new ImageIcon(SWTUtils.convertSWTImage(imageData)));
            }
            return this.icon;
        }
        
        static {
            FileTypeLauncherInfo.nextID = 1;
        }
    }
    
    private static class CMN_initializeLaunchers extends CommandMessage
    {
        public Object run(final Object[] args) {
            for (final Program program : Program.getPrograms()) {
                if (!NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap.containsKey(program) && isProgramValid(program) && program.getImageData() != null) {
                    NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap.put(program, new FileTypeLauncherInfo(program));
                }
            }
            return null;
        }
    }
    
    private static class CMN_initializeExtensions extends CommandMessage
    {
        public Object run(final Object[] args) {
            for (final String extension : Program.getExtensions()) {
                final Program program = Program.findProgram(extension);
                if (program != null) {
                    initNative();
                    FileTypeLauncherInfo fileTypeLauncherInfo = NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap.get(program);
                    if (fileTypeLauncherInfo == null && isProgramValid(program)) {
                        fileTypeLauncherInfo = new FileTypeLauncherInfo(program);
                        NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap.put(program, fileTypeLauncherInfo);
                    }
                    if (fileTypeLauncherInfo != null) {
                        fileTypeLauncherInfo.addExtension(extension);
                    }
                }
            }
            return null;
        }
    }
    
    private static class CMN_getAllRegisteredExtensions extends CommandMessage
    {
        public Object run(final Object[] args) {
            final List<String> extensionList = new ArrayList<String>();
            for (final FileTypeLauncherInfo launcherInfo : NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap.values()) {
                for (final String registeredExtension : launcherInfo.getRegisteredExtensions()) {
                    extensionList.add(registeredExtension);
                }
            }
            return extensionList.toArray(new String[0]);
        }
    }
    
    private static class CMN_getLauncherID extends CommandMessage
    {
        public Object run(final Object[] args) {
            final String extension = (String)args[0];
            final Program program = Program.findProgram(extension);
            if (program == null) {
                return null;
            }
            initNative();
            FileTypeLauncherInfo fileTypeLauncher = NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap.get(program);
            if (fileTypeLauncher == null && isProgramValid(program)) {
                fileTypeLauncher = new FileTypeLauncherInfo(program);
                NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap.put(program, fileTypeLauncher);
            }
            if (fileTypeLauncher != null) {
                if (!NativeFileTypeLauncherStatic.hasInitializedExtensions) {
                    fileTypeLauncher.addExtension(extension);
                }
                return fileTypeLauncher.getID();
            }
            return null;
        }
    }
    
    private static class CMN_getLauncherIDs extends CommandMessage
    {
        public Object run(final Object[] args) {
            initNative();
            final FileTypeLauncherInfo[] fileTypeLaunchers = (FileTypeLauncherInfo[])NativeFileTypeLauncherStatic.programToFileTypeLauncherInfoMap.values().toArray(new FileTypeLauncherInfo[0]);
            Arrays.sort(fileTypeLaunchers, (Comparator<? super FileTypeLauncherInfo>)new ll(this));
            final int[] ids = new int[fileTypeLaunchers.length];
            for (int i = 0; i < fileTypeLaunchers.length; ++i) {
                ids[i] = fileTypeLaunchers[i].getID();
            }
            return ids;
        }
    }
}
