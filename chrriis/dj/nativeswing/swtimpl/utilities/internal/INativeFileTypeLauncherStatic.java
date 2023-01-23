//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.utilities.internal;

import javax.swing.*;
import java.awt.*;

public interface INativeFileTypeLauncherStatic
{
    void load();
    
    String[] getAllRegisteredExtensions();
    
    INativeFileTypeLauncher getLauncher(final String p0);
    
    INativeFileTypeLauncher[] getLaunchers();
    
    ImageIcon getDefaultIcon();
    
    Dimension getIconSize();
}
